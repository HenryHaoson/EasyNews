package cn.henryzhuhao.easynews.business.login.presenter;

import cn.henryzhuhao.easynews.app.App;
import cn.henryzhuhao.easynews.business.login.model.LoginModel;
import cn.henryzhuhao.easynews.business.login.view.LoginView;
import cn.henryzhuhao.easynews.entity.ResponseBean.LoginBean;
import cn.henryzhuhao.mainframe.frame.base.action.BaseModel;

/**
 * Created by HenryZhuhao on 2017/6/22.
 */

public class LoginPresenter {
    private LoginBean bean;
    private LoginView view;
    private LoginModel model;
    public LoginPresenter(LoginView view){
        this.view=view;
        model=new LoginModel();
    }
    public void login(String userName, String password){
        model.login(userName, password, new BaseModel.LoadDateCallBack<LoginBean>() {
            @Override
            public void loadDateSucceed(LoginBean date) {
                if(date.getMsg().equals("success")){
                    view.loginSucess(true);
                    App.getInstance().setUser(date.getData().getId());
                }
            }

            @Override
            public void loadDateFailed(String error) {

            }
        });
    }
}
