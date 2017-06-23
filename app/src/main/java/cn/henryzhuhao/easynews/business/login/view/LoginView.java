package cn.henryzhuhao.easynews.business.login.view;

/**
 * Created by HenryZhuhao on 2017/6/22.
 */

public interface LoginView {
    void loginSucess(Boolean isSuccess);
    void loginFailed(String errorMsg);
}
