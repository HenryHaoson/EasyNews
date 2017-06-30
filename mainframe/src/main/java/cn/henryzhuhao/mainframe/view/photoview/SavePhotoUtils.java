package cn.henryzhuhao.mainframe.view.photoview;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileOutputStream;

import io.reactivex.functions.Consumer;

/**
 * Created by HenryZhuhao on 2017/6/26.
 */
public class SavePhotoUtils {
    private Context context;

    public SavePhotoUtils() {
    }


    public SavePhotoUtils(Context context) {
        super();
        this.context = context;
    }


    public void savePicture(final String fileName, final String url) {
        RxPermissions rxPermissions = new RxPermissions((Activity) context);
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Log.e("photoview", "permission accepted");
                            Glide.with(context).load(url).asBitmap().toBytes().into(new SimpleTarget<byte[]>() {
                                @Override

                                public void onResourceReady(byte[] bytes, GlideAnimation<? super byte[]> glideAnimation) {
                                    try {
                                        savaFileToSD(fileName, bytes);
                                        Log.e("photoview", "Glide loadSuccess");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        } else {
                            Log.e("photoview", "permission disaccepted");
                        }
                    }
                });

    }

    public void savaFileToSD(String filename, byte[] bytes) throws Exception {

        String filePath = Environment.getExternalStorageDirectory().getCanonicalPath() + "/saveImages";
        File dir1 = new File(filePath);
        if (!dir1.exists()) {
            dir1.mkdirs();
        }
        filename = filePath + "/" + filename;
        //这里就不要用openFileOutput了,那个是往手机内存中写数据的
        FileOutputStream output = new FileOutputStream(filename);
        output.write(bytes);
        //将bytes写入到输出流中
        output.close();
        Log.e("photoview", "saveFileToSD is diaoyongle");
        Toast.makeText(context, "图片已成功保存到" + filePath, Toast.LENGTH_SHORT).show();

    }

}