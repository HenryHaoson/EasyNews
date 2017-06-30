package cn.henryzhuhao.easynews.business.login.presenter;

import cn.henryzhuhao.easynews.business.login.model.RegisterModel;
import cn.henryzhuhao.easynews.business.login.view.RegisterView;
import cn.henryzhuhao.easynews.entity.RegisterBean;
import cn.henryzhuhao.easynews.entity.ResponseBean.CommonBean;
import cn.henryzhuhao.mainframe.frame.base.action.BaseModel;

/**
 * Created by HenryZhuhao on 2017/6/23.
 */

public class RegisterPresenter {
    private CommonBean bean;
    private RegisterView view;
    private RegisterModel model;
    public RegisterPresenter(RegisterView view){
        this.view=view;
        model=new RegisterModel();
    }
    public void register(RegisterBean body){
        model.register(body, new BaseModel.LoadDateCallBack<CommonBean>() {
            @Override
            public void loadDateSucceed(CommonBean date) {

                    if(date.getMsg().equals("success")){
                        view.registerSuccess(true);
                    }
            }

            @Override
            public void loadDateFailed(String error) {
                    view.registerFailed(error);
            }
        });
    }
}
