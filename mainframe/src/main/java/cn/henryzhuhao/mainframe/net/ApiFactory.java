package cn.henryzhuhao.mainframe.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HenryZhuhao on 2017/4/8.
 */

public class ApiFactory {
    private Retrofit mRetrofit;


    private ApiFactory (){

    };
    public static ApiFactory newInstance(){
        return new ApiFactory();
    }



    public <T> T createApi(Class<T> clz){
        initRetrofit();
        T api=null;
        try{
            Class<T> clazz=(Class<T>) Class.forName(clz.getName());
            api=mRetrofit.create(clazz);
        }catch (Exception e){
            e.printStackTrace();
        }
        return api;
    }

    private void initRetrofit(){
        mRetrofit=new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkClient())
                .build();
    }
    private OkHttpClient getOkClient(){
        OkHttpClient okHttpClient;
        okhttp3.OkHttpClient.Builder ClientBuilder=new okhttp3.OkHttpClient.Builder();
        ClientBuilder.readTimeout(30, TimeUnit.SECONDS);//读取超时
        ClientBuilder.connectTimeout(10, TimeUnit.SECONDS);//连接超时
        ClientBuilder.writeTimeout(60, TimeUnit.SECONDS);//写入超时
        okHttpClient=ClientBuilder.build();
        return okHttpClient;
    }
}
