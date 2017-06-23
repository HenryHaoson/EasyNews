package cn.henryzhuhao.easynews.business.login.view;

/**
 * Created by HenryZhuhao on 2017/6/23.
 */

public interface RegisterView {
    void registerSuccess(Boolean isSuccess);
    void registerFailed(String errorMsg);
}
