package cn.henryzhuhao.mainframe.imageLoader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileDescriptor;

/**
 * Created by HenryZhuhao on 2017/4/13.
 */

public class ImageResizer {
    public String Tag = "ImageResizer";

    public ImageResizer() {

    }

    public Bitmap decodeSampledBitmapFromResource(Resources resources, int resId
            , int reqwidth, int reqheight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        /**
         * 设置inJustDecodeBounds为true只会解析图片的原始高宽，不会真正加载图片，是轻量级操作。
         */
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resId, options);

        /**
         * 计算出insamplezize大小
         */
        options.inSampleSize = calculateInSampleSize(options, reqwidth, reqheight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, resId, options);
    }


    public Bitmap decodeSampledBitmapFromFileDescripter(FileDescriptor fileDescriptor,
                                                        int reqwidth, int reqheight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        /**
         * 设置inJustDecodeBounds为true只会解析图片的原始高宽，不会真正加载图片，是轻量级操作。
         */
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);

        /**
         * 计算出insamplezize大小
         */
        options.inSampleSize = calculateInSampleSize(options, reqwidth, reqheight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
    }

    public int calculateInSampleSize(BitmapFactory.Options options,
                                     int reqwidth, int reqheight) {
        if (reqheight == 0 || reqwidth == 0) {
            return 1;
        }
        int inSamleSize = 1;
        int height = options.outHeight;
        int width = options.outWidth;
        if (height > reqheight || width > reqwidth) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while ((halfHeight / inSamleSize) >= reqheight && (halfWidth / inSamleSize) >= width) {
                inSamleSize *= 2;
            }
        }
        return inSamleSize;

    }
}
