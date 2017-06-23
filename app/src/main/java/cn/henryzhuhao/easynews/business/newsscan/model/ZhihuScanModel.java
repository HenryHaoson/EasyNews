package cn.henryzhuhao.easynews.business.newsscan.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.henryzhuhao.easynews.app.App;
import cn.henryzhuhao.easynews.business.newsscan.adapter.ZhihuNewDate;
import cn.henryzhuhao.easynews.business.newsscan.bean.ZhihuNewsList;
import cn.henryzhuhao.mainframe.frame.base.action.BaseModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by HenryZhuhao on 2017/4/10.
 */

public class ZhihuScanModel {
    private RequestBody SetRequestBody() {
        RequestBody body = null;
        okhttp3.FormBody.Builder formEncodingBuilder = new okhttp3.FormBody.Builder();
        body = formEncodingBuilder.build();
        return body;
    }

    public Request ZhihuNewsListRequest() {
        /**
         * 没有参数加setrequestBody出错
         */
        Request request = new Request.Builder().url("http://news-at.zhihu.com/api/3/news/latest").build();
        return request;
    }


    public void getZhihuNewsList(final BaseModel.LoadDateCallBack<List<ZhihuNewDate>> callBack) {
        Call call= App.getInstance().okHttpClient.newCall(ZhihuNewsListRequest());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson=new Gson();
                //callBack.loadDateFailed(response.body().string());
                ZhihuNewsList list=gson.fromJson(response.body().string(),ZhihuNewsList.class);
                if (list!=null){
                    List<ZhihuNewsList.StoriesBean> getlist=list.getStories();
                    List<ZhihuNewDate> data=new ArrayList<ZhihuNewDate>();
                    for(ZhihuNewsList.StoriesBean bean:getlist){
                        ZhihuNewDate zhihuNewDate=new ZhihuNewDate();
                        zhihuNewDate.setId(bean.getId()+"");
                        zhihuNewDate.setTitle(bean.getTitle());
                        zhihuNewDate.setPicUrl(bean.getImages().get(0));
                        zhihuNewDate.setDate(list.getDate());
                        data.add(zhihuNewDate);
                    }
                    callBack.loadDateSucceed(data);
                }else {
                    if (null==response.body().toString()){
                        callBack.loadDateFailed("获取数据为空");
                    }else {
                        callBack.loadDateFailed(response.body().string());
                    }

                }

            }
        });

    }
    public void getZhihuRx(final BaseModel.LoadDateCallBack<List<ZhihuNewDate>> callBack){
        Call call=App.getInstance().okHttpClient.newCall(ZhihuNewsListRequest());
        
    }
}
