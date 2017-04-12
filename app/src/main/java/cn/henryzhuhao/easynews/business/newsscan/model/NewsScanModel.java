package cn.henryzhuhao.easynews.business.newsscan.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.henryzhuhao.easynews.app.App;
import cn.henryzhuhao.easynews.business.newsscan.adapter.NewsDate;
import cn.henryzhuhao.easynews.business.newsscan.bean.NewsBean;
import cn.henryzhuhao.mainframe.frame.base.action.BaseModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by HenryZhuhao on 2017/4/7.
 */

public class NewsScanModel {
    private RequestBody SetRequestBody(){
        RequestBody body=null;
        okhttp3.FormBody.Builder formEncodingBuilder=new okhttp3.FormBody.Builder();
        formEncodingBuilder.add("type","");
        formEncodingBuilder.add("key","c78dd3f6823f9fe39ccf71087684f2bb");
        body=formEncodingBuilder.build();
        return body;
    }
    public Request NewsScanRequest(){
        Request request=new Request.Builder().url("http://v.juhe.cn/toutiao/index").post(SetRequestBody()).build();
        return request;
    }
    public void getNewsScan(final BaseModel.LoadDateCallBack<List<NewsDate>> callBack){
        Call call= App.getInstance().okHttpClient.newCall(NewsScanRequest());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson=new Gson();
                NewsBean newsBean=gson.fromJson(response.body().string(),NewsBean.class);
                List<NewsBean.ResultBean.DataBean> list=newsBean.getResult().getData();
                List<NewsDate> date=new ArrayList<NewsDate>();
                for(NewsBean.ResultBean.DataBean dataBean :list){
                    NewsDate newsDate =new NewsDate();
                    newsDate.setPicUrl(dataBean.getThumbnail_pic_s());
                    newsDate.setTitle(dataBean.getTitle());
                    newsDate.setContent(dataBean.getTitle());
                    date.add(newsDate);
                    callBack.loadDateSucceed(date);
                }
            }
        });
    }
}
