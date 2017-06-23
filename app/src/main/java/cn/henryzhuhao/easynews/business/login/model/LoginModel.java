package cn.henryzhuhao.easynews.business.login.model;

import cn.henryzhuhao.easynews.business.Api.TopNewsApi;
import cn.henryzhuhao.easynews.entity.ResponseBean.LoginBean;
import cn.henryzhuhao.mainframe.frame.base.action.BaseModel;
import cn.henryzhuhao.mainframe.net.ApiFactory;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HenryZhuhao on 2017/6/22.
 */

public class LoginModel implements BaseModel{
    public void login(String userName , String passowrd, final LoadDateCallBack<LoginBean> callBack){
        TopNewsApi api= ApiFactory.newInstance().createApi(TopNewsApi.class);
        Observable<LoginBean> observable=api.login(userName,passowrd);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean value) {
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
