package cn.henryzhuhao.easynews.app;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by HenryZhuhao on 2017/4/7.
 */

public class App extends Application {
    private static App instance;
    public OkHttpClient okHttpClient;
    public String ZhihuId;

    public String getZhihuId() {
        return ZhihuId;
    }

    public void setZhihuId(String zhihuId) {
        ZhihuId = zhihuId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        initOkHttp();
    }
    private void initOkHttp() {
        okhttp3.OkHttpClient.Builder ClientBuilder=new okhttp3.OkHttpClient.Builder();
        ClientBuilder.readTimeout(30, TimeUnit.SECONDS);//读取超时
        ClientBuilder.connectTimeout(10, TimeUnit.SECONDS);//连接超时
        ClientBuilder.writeTimeout(60, TimeUnit.SECONDS);//写入超时
        okHttpClient=ClientBuilder.build();
    }
    public static App getInstance() {
        return instance;
    }
}
