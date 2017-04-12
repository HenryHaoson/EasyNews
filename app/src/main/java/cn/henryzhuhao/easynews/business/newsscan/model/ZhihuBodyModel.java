package cn.henryzhuhao.easynews.business.newsscan.model;

import com.google.gson.Gson;

import java.io.IOException;

import cn.henryzhuhao.easynews.app.App;
import cn.henryzhuhao.easynews.business.newsscan.adapter.ZhihuNewsBody;
import cn.henryzhuhao.easynews.business.newsscan.bean.ZhihuNewsBean;
import cn.henryzhuhao.mainframe.frame.base.action.BaseModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HenryZhuhao on 2017/4/10.
 */

public class ZhihuBodyModel {

    public Request ZhihuBodyRequest(String id){
        Request request=new Request.Builder().url("http://news-at.zhihu.com/api/3/news/"+id).build();
        return request;
    }

    public void getZhihuBody(String id, final BaseModel.LoadDateCallBack<ZhihuNewsBody> callBack){
        Call call = App.getInstance().okHttpClient.newCall(ZhihuBodyRequest(id));
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson=new Gson();
                ZhihuNewsBean result=gson.fromJson(response.body().string(),ZhihuNewsBean.class);
                ZhihuNewsBody body=new ZhihuNewsBody();
                body.setBody(result.getBody());
//                body.setBody(response.body().string());
                callBack.loadDateSucceed(body);
            }
        });
    }
}
