package cn.henryzhuhao.easynews.business.newsscan.webview;

import android.content.Context;
import android.content.Intent;

/**
 * Created by HenryZhuhao on 2017/6/13.
 */

public class MJavascriptInterface {
    private Context context;
    private String [] imageUrls;
    public MJavascriptInterface(Context context,String[] imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }
    @android.webkit.JavascriptInterface
    public void openImage(String img) {
        Intent intent = new Intent();
        intent.putExtra("imageUrls", imageUrls);
        intent.putExtra("curImageUrl", img);
        context.startActivity(intent);
    }
}