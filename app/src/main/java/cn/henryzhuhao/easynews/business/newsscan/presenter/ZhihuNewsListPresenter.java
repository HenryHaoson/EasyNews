package cn.henryzhuhao.easynews.business.newsscan.presenter;

import java.util.List;

import cn.henryzhuhao.easynews.business.newsscan.adapter.ZhihuNewDate;
import cn.henryzhuhao.easynews.business.newsscan.model.ZhihuScanModel;
import cn.henryzhuhao.easynews.business.newsscan.view.ZhihuNewsListView;
import cn.henryzhuhao.mainframe.frame.base.action.BaseModel;

/**
 * Created by HenryZhuhao on 2017/4/10.
 */

public class ZhihuNewsListPresenter {
    public ZhihuScanModel model;
    public ZhihuNewsListView view;

    public ZhihuNewsListPresenter(ZhihuNewsListView view){
        this.view=view;
        model=new ZhihuScanModel();
    }
    public void getZhihuNewsList(){
        model.getZhihuNewsList(new BaseModel.LoadDateCallBack<List<ZhihuNewDate>>() {
            @Override
            public void loadDateSucceed(List<ZhihuNewDate> date) {
                view.loadsuccess(date);
            }

            @Override
            public void loadDateFailed(String error) {
            view.loadFailure(error);
            }
        });
    }
    public void loadMoreZhihuNewsList(){
        model.getZhihuNewsList(new BaseModel.LoadDateCallBack<List<ZhihuNewDate>>() {
            @Override
            public void loadDateSucceed(List<ZhihuNewDate> date) {
                view.loadMore(date);
            }

            @Override
            public void loadDateFailed(String error) {

            }
        });
    }
}
