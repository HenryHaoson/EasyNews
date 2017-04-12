package cn.henryzhuhao.easynews.business.newsscan.presenter;

import cn.henryzhuhao.easynews.business.newsscan.adapter.ZhihuNewsBody;
import cn.henryzhuhao.easynews.business.newsscan.model.ZhihuBodyModel;
import cn.henryzhuhao.easynews.business.newsscan.view.ZhihuNewsBodyView;
import cn.henryzhuhao.mainframe.frame.base.action.BaseModel;

/**
 * Created by HenryZhuhao on 2017/4/10.
 */

public class ZhihuNewsBodyPresenter {
    public ZhihuBodyModel model;
    public ZhihuNewsBodyView view;

    public ZhihuNewsBodyPresenter(ZhihuNewsBodyView view) {
        this.view=view;
        model=new ZhihuBodyModel();

    }

    public void getZhihuNewsBody(String id) {
        model.getZhihuBody(id, new BaseModel.LoadDateCallBack<ZhihuNewsBody>() {
            @Override
            public void loadDateSucceed(ZhihuNewsBody date) {
                view.loadSuccess(date);
            }

            @Override
            public void loadDateFailed(String error) {

            }
        });
    }
}
