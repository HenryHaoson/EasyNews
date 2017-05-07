package cn.henryzhuhao.mainframe.net;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HenryZhuhao on 2017/4/8.
 */

public class BaseApi {

    public String baseUrl;
    protected Retrofit mRetrofit;
    private final String Tag=getClass().getSimpleName();

    public BaseApi(){
        mRetrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public BaseApi (String baseUrl){
        mRetrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public class RetrofitCallback<T> implements Callback<T>{

        private ApiCallback<T> mCallback;

        public RetrofitCallback() {
            super();
        }

        public RetrofitCallback(ApiCallback<T> callback){
            mCallback=callback;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response.isSuccessful()){
                mCallback.onSuccess(((T) response.body()));
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
                mCallback.onFailure();
        }
    }
    public interface ApiCallback<T>{
        void onSuccess(T response);
        void onError(int errorCode,String errorMsg);
        void onFailure();
    }
}
