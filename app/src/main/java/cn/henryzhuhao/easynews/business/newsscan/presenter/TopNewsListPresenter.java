package cn.henryzhuhao.easynews.business.newsscan.presenter;

import cn.henryzhuhao.easynews.business.newsscan.model.TopNewsListModel;
import cn.henryzhuhao.easynews.business.newsscan.view.TopNewsListView;
import cn.henryzhuhao.easynews.entity.ResponseBean.TopNewsList;
import cn.henryzhuhao.mainframe.frame.base.action.BaseModel;

/**
 * Created by HenryZhuhao on 2017/6/26.
 */

public class TopNewsListPresenter {
    private TopNewsListView view;
    private TopNewsListModel model;
    public TopNewsListPresenter(TopNewsListView view){
        this.view=view;
        model=new TopNewsListModel();
    }
    public void getTopNewsList(){
        model.getTopNewsList(new BaseModel.LoadDateCallBack<TopNewsList>() {
            @Override
            public void loadDateSucceed(TopNewsList date) {
                view.getTopNewsListSuccess(date.getData());
            }

            @Override
            public void loadDateFailed(String error) {

            }
        });
    }
}
