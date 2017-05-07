package cn.henryzhuhao.easynews.business.Api;

import cn.henryzhuhao.easynews.business.newsscan.bean.ZhihuNewsList;
import retrofit2.http.GET;
import io.reactivex.Observable;

/**
 * Created by HenryZhuhao on 2017/4/25.
 */

public interface ZhihuNewsApi {
    @GET("api/3/news/latest")
    Observable<ZhihuNewsList> getZhihuNews();
    //public Observable<ZhihuNewsList> getZhihuNews(Callback<ZhihuNewsList> callback);
}
