package cn.henryzhuhao.mainframe.imageLoader.HenryBitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by HenryZhuhao on 2017/4/13.
 */

public class SupportBitmap {
    public static Bitmap decodeSampledBitmapFromResource(Resources resources, int resId
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
        options.inSampleSize=calculateInSampleSize(options,reqwidth,reqheight);
        options.inJustDecodeBounds=false;
        return BitmapFactory.decodeResource(resources,resId,options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqwidth, int reqheight) {
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
