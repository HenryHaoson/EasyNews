package cn.henryzhuhao.easynews.business.newsscan.presenter;

import java.util.List;

import cn.henryzhuhao.easynews.business.newsscan.adapter.NewsDate;
import cn.henryzhuhao.easynews.business.newsscan.model.NewsScanModel;
import cn.henryzhuhao.easynews.business.newsscan.view.NewsScanView;
import cn.henryzhuhao.mainframe.frame.base.action.BaseModel;

/**
 * Created by HenryZhuhao on 2017/4/7.
 */

public class NewsScanPresenter {
    public NewsScanModel model;
    public NewsScanView view;
    public NewsScanPresenter(NewsScanView view){
        this.view=view;
        model=new NewsScanModel();
    }
    public void scannews(){
        model.getNewsScan(new BaseModel.LoadDateCallBack<List<NewsDate>>() {
            @Override
            public void loadDateSucceed(List<NewsDate> date) {
                view.scanNews(date);
            }

            @Override
            public void loadDateFailed(String error) {

            }
        });
    }
}
