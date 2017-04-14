package cn.henryzhuhao.mainframe.imageLoader;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StatFs;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import cn.henryzhuhao.mainframe.R;

/**
 * Created by HenryZhuhao on 2017/4/13.
 */

public class ImageLoader {
    private final String Tag = "ImageLoader";
    private static final int DISK_CACHE_SIZE = 1024 * 1024 * 50;//磁盘缓存大小50M
    private static final int DISK_CACHE_INDEX = 0;//open方法中一个节点只有一个数据所以设置为0
    private static final int IO_BUFFER_SIZE = 8 * 1024;//图片网络请求输入流大小

    //线程池相关
    private static final int CPU_COUNT=Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE=CPU_COUNT+1;
    private static final int MAXIMUM_POOL_SIZE=CPU_COUNT*2+1;
    private static final long KEEP_ALIVE=10L;

    //private static final int TAG_IMAGE_URI= 1;

    private static final int MESSAGE_POST_RESULT=1;

    private ImageResizer mImageResizer = new ImageResizer();
    private Boolean mIsDiskLruCacheCreated = false;
    private Context mContext;
    private LruCache<String, Bitmap> mMemoryCache;
    private DiskLruCache mDiskLruCache;

    private ImageLoader(Context context) {

        /**
         * 设置内存缓存大小为当前应用的内存的1/8
         */
        mContext = context.getApplicationContext();
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.e(Tag,maxMemory+"");
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
        /**
         * 设置磁盘缓存大小，默认为50M，
         * 创建磁盘缓存目录
         */
        File diskCacheDir = getDiskCacheDir(context, "bitmap");
        if (!diskCacheDir.exists()) {
            diskCacheDir.mkdirs();
        }
        if (getUsableSpace(diskCacheDir) > DISK_CACHE_SIZE) {
            try {
                mDiskLruCache = DiskLruCache.open(diskCacheDir, 1, 1, DISK_CACHE_SIZE);
                mIsDiskLruCacheCreated = true;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static ImageLoader build(Context context){
        return new ImageLoader(context);
    }

    public static final ThreadFactory sThreadFactory=new ThreadFactory() {
        private final AtomicInteger mCount=new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable,"ImageLoader#"+mCount.getAndIncrement());
        }
    };

    public static final Executor THREAD_POOL_EXECUTOOR=new ThreadPoolExecutor(CORE_POOL_SIZE
            ,MAXIMUM_POOL_SIZE,KEEP_ALIVE, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(),sThreadFactory);

    public Handler mMainHandler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            Log.e(Tag,"handle get"+msg.toString());
            LoaderResult result= (LoaderResult) msg.obj;
            ImageView imageView=result.imageView;
            String uri= (String) imageView.getTag(R.id.TAG_IMAGEVIEW_URL);
            if(uri.equals(result.uri)){
                imageView.setImageBitmap(result.bitmap);

            }else {
                Log.e(Tag,"Url不匹配，取消加载");
            }
        }
    };


    public void bindBitmap(String uri,final ImageView imageView){
        bindBitmap(uri,imageView,0,0);
    }

    public void bindBitmap(final String uri, final ImageView imageView, final int reqwidth, final int reqheight) {
        imageView.setTag(R.id.TAG_IMAGEVIEW_URL,uri);
        Bitmap bitmap=loadBitmapFromMemCache(uri);
        if(bitmap!=null){
            imageView.setImageBitmap(bitmap);
            return;
        }

        Runnable loadBitmapTask=new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap=loadBitmap(uri,reqwidth,reqheight);
                if(bitmap!=null){
                    LoaderResult loaderResult=new LoaderResult(imageView,uri,bitmap);
                    mMainHandler.obtainMessage(MESSAGE_POST_RESULT,loaderResult).sendToTarget();

                }
            }
        };
        THREAD_POOL_EXECUTOOR.execute(loadBitmapTask);
    }


    /**
     * 同步加载
     * @param uri
     * @param reqwidth
     * @param reqheight
     * @return
     */
    private Bitmap loadBitmap(String uri, int reqwidth, int reqheight) {
        Bitmap bitmap = loadBitmapFromMemCache(uri);
        if (bitmap != null) {
            Log.e(Tag, "loadBitmapFromMemCache,url" + uri);
            return bitmap;
        }else {
            Log.e(Tag, "loadBitmapFromMemCache,url" + "is null");
        }
        try {
            bitmap = loadBitmapfromDiskCache(uri, reqwidth, reqheight);
            if (bitmap != null) {
                Log.e(Tag, "loadBitmapfromDiskCache,url" + uri);
                return bitmap;
            }
            bitmap = loadBitmapfromHttp(uri, reqwidth, reqheight);
            Log.e(Tag, "loadBitmapfromHttp,url" + uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bitmap == null && !mIsDiskLruCacheCreated) {
            bitmap = downloadBitmapFromUrl(uri);
        }
        return bitmap;
    }


    /**
     * 从内存缓存读取和添加bitmap
     *
     * @param key
     * @param bitmap
     */
    private void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    private Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
    private Bitmap loadBitmapFromMemCache(String url) {
        final String key = hashKeyFromUrl(url);
        Bitmap bitmap = getBitmapFromMemCache(key);
        return bitmap;
    }

    /**
     * 当从网络下载图片的时候的一系列操作
     * 体会设计，显示内存缓存，再磁盘缓存，再下载
     * 这是最后一步，实际操作是下载好之后存到磁盘缓存，然后再调用磁盘缓存的load加载。好棒棒哦
     *
     * @param url
     * @param reqwidth
     * @param reqheight
     * @return
     * @throws IOException
     */
    private Bitmap loadBitmapfromHttp(String url, int reqwidth, int reqheight) throws IOException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new RuntimeException("不能再ui线程访问网络");
        }
        /**
         * 还没初始化缓存或者初始化失败，就不执行缓存。
         */
        if (mDiskLruCache == null) {
            return null;
        }
        String key = hashKeyFromUrl(url);
        DiskLruCache.Editor editor = mDiskLruCache.edit(key);
        if (editor != null) {
            OutputStream outputStream = editor.newOutputStream(DISK_CACHE_INDEX);
            if (downloadUrlToStream(url, outputStream)) {
                editor.commit();
            } else {
                editor.abort();
            }
            mDiskLruCache.flush();
        }
        return loadBitmapfromDiskCache(url, reqwidth, reqheight);
    }

    /**
     * 读取磁盘缓存
     *
     * @param url
     * @param reqwidth
     * @param reqheight
     * @return
     * @throws IOException
     */
    private Bitmap loadBitmapfromDiskCache(String url, int reqwidth, int reqheight) throws IOException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new RuntimeException("不能再ui线程访问网络");
        }
        /**
         * 还没初始化缓存或者初始化失败，就不执行缓存。
         */
        if (mDiskLruCache == null) {
            return null;
        }
        Bitmap bitmap = null;
        String key = hashKeyFromUrl(url);
        DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
        if (snapShot != null) {
            FileInputStream fileInputStream = (FileInputStream) snapShot.getInputStream(DISK_CACHE_INDEX);
            FileDescriptor fileDescriptor = fileInputStream.getFD();
            bitmap = mImageResizer.decodeSampledBitmapFromFileDescripter(fileDescriptor, reqwidth, reqheight);
            if (bitmap != null) {
                addBitmapToMemoryCache(key, bitmap);
            }
        }
        return bitmap;//可能为空
    }

    /**
     * 将下载流传入输出流，在网络加载图片的时候用到，用于传给磁盘缓存对象
     *
     * @param urlString
     * @param outputStream
     * @return
     */
    public Boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), IO_BUFFER_SIZE);
            out = new BufferedOutputStream(outputStream, IO_BUFFER_SIZE);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    /**
     * 下载不缓存
     *
     * @param urlString
     * @return
     */
    private Bitmap downloadBitmapFromUrl(String urlString) {
        Bitmap bitmap = null;
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        final URL url;
        try {
            url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), IO_BUFFER_SIZE);
            bitmap = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(Tag, "DownLoad Failed from Web");
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return bitmap;
        }

    }

    public File getDiskCacheDir(Context context, String uniqueName) {
        Boolean externalStorageAvailable = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        final String cachePath;
        if (externalStorageAvailable) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);

    }

    /**
     * 获取磁盘缓存路径容量
     *
     * @param path
     * @return
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private long getUsableSpace(File path) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return path.getUsableSpace();
        }
        final StatFs statFs = new StatFs(path.getPath());
        return (long) statFs.getBlockSize() * (long) statFs.getAvailableBlocks();
    }

    /**
     * url加密
     *
     * @param url
     * @return
     */
    private String hashKeyFromUrl(String url) {
        String cacheKey;
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(url.getBytes());
            cacheKey = bytesToHexString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            cacheKey = String.valueOf(url.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            String hex = Integer.toHexString(0xFF & digest[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }


    private static class LoaderResult {
        public ImageView imageView;
        public String uri;
        public Bitmap bitmap;

        public LoaderResult(ImageView imageView, String uri, Bitmap bitmap) {
            this.imageView = imageView;
            this.uri = uri;
            this.bitmap = bitmap;
        }
    }
}
