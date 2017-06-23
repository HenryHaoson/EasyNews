package cn.henryzhuhao.easynews.business.login.model;

import cn.henryzhuhao.easynews.business.Api.TopNewsApi;
import cn.henryzhuhao.easynews.entity.RegisterBean;
import cn.henryzhuhao.easynews.entity.ResponseBean.CommonBean;
import cn.henryzhuhao.mainframe.frame.base.action.BaseModel;
import cn.henryzhuhao.mainframe.net.ApiFactory;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HenryZhuhao on 2017/6/23.
 */

public class RegisterModel implements BaseModel {
    public void register(RegisterBean body, final LoadDateCallBack<CommonBean> callBack){
        TopNewsApi api= ApiFactory.newInstance().createApi(TopNewsApi.class);
        Observable<CommonBean> observable=api.register(body.getUsername(),body.getPassword(),body.getNickname()
        ,body.getSex(),body.getBirthday(),body.getIntroduction(),body.getImageUrl());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommonBean value) {
                        callBack.loadDateSucceed(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.loadDateFailed(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
