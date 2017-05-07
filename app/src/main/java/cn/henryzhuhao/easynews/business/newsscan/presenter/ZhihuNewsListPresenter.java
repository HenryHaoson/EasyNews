package cn.henryzhuhao.easynews.business.newsscan.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.henryzhuhao.easynews.business.Api.ZhihuNewsApi;
import cn.henryzhuhao.easynews.business.newsscan.adapter.ZhihuNewDate;
import cn.henryzhuhao.easynews.business.newsscan.bean.ZhihuNewsList;
import cn.henryzhuhao.easynews.business.newsscan.model.ZhihuScanModel;
import cn.henryzhuhao.easynews.business.newsscan.view.ZhihuNewsListView;
import cn.henryzhuhao.mainframe.net.ApiFactory;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by HenryZhuhao on 2017/4/10.
 */

public class ZhihuNewsListPresenter {
    private ZhihuScanModel model;
    private ZhihuNewsListView view;
    List<ZhihuNewDate> mdata = new ArrayList<>();

    public ZhihuNewsListPresenter(ZhihuNewsListView view) {
        this.view = view;
        model = new ZhihuScanModel();
    }

    //    public void getZhihuNewsList(){
//        model.getZhihuNewsList(new BaseModel.LoadDateCallBack<List<ZhihuNewDate>>() {
//            @Override
//            public void loadDateSucceed(List<ZhihuNewDate> date) {
//                view.loadsuccess(date);
//            }
//
//            @Override
//            public void loadDateFailed(String error) {
//            view.loadFailure(error);
//            }
//        });
//    }
//    public void loadMoreZhihuNewsList(){
//        model.getZhihuNewsList(new BaseModel.LoadDateCallBack<List<ZhihuNewDate>>() {
//            @Override
//            public void loadDateSucceed(List<ZhihuNewDate> date) {
//                view.loadMore(date);
//            }
//
//            @Override
//            public void loadDateFailed(String error) {
//
//            }
//        });
//    }
    public void getZhihuNewsList() {
        ZhihuNewsApi api = ApiFactory.newInstance().createApi(ZhihuNewsApi.class);
        Observable<ZhihuNewsList> observable = api.getZhihuNews();
        Log.e("tag","rxjava start");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhihuNewsList>() {
                    @Override
                    public void onError(Throwable e) {
                        Log.e("tag","error"+e.toString());
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        view.loadsuccess(mdata);
                        Log.e("tag","oncompleted");
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(ZhihuNewsList zhihuNewsList) {
                        List<ZhihuNewsList.StoriesBean> getlist = zhihuNewsList.getStories();
                        List<ZhihuNewDate> data = new ArrayList<>();
                        for (ZhihuNewsList.StoriesBean bean : getlist) {
                            ZhihuNewDate zhihuNewDate = new ZhihuNewDate();
                            zhihuNewDate.setId(bean.getId() + "");
                            zhihuNewDate.setTitle(bean.getTitle());
                            zhihuNewDate.setPicUrl(bean.getImages().get(0));
                            data.add(zhihuNewDate);
                        }
                        mdata=data;
                        view.loadsuccess(mdata);
                        Log.e("tag","onnext");
                    }
                });

    }
}
