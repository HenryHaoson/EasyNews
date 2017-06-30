package cn.henryzhuhao.easynews.business.newsscan.model;

import cn.henryzhuhao.easynews.business.Api.TopNewsApi;
import cn.henryzhuhao.easynews.entity.ResponseBean.TopNewsList;
import cn.henryzhuhao.mainframe.frame.base.action.BaseModel;
import cn.henryzhuhao.mainframe.net.ApiFactory;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HenryZhuhao on 2017/6/26.
 */

public class TopNewsListModel {
    public void getTopNewsList(final BaseModel.LoadDateCallBack<TopNewsList> callBack){
        TopNewsApi api= ApiFactory.newInstance().createApi(TopNewsApi.class);
        Observable<TopNewsList> observable=api.getTopNewsList();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TopNewsList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TopNewsList value) {
                        callBack.loadDateSucceed(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
