package cn.henryzhuhao.easynews.business.Api;

import cn.henryzhuhao.easynews.business.newsscan.bean.ZhihuNewsList;
import retrofit2.Callback;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by HenryZhuhao on 2017/4/25.
 */

public interface ZhihuNewsApi {
    @GET
    public Observable<ZhihuNewsList> getZhihuNews(Callback<ZhihuNewsList> callback);
}
