package cn.henryzhuhao.mainframe.frame.base.action;

import android.os.Bundle;

/**
 * Created by HenryZhuhao on 2017/3/29.
 */

public interface BaseAction {
    void init();
    void initView();
    void initData(Bundle savedInstanceState);
    void initListener();
    void showContentView();
    void hideContentView();
    void showLoadingView();
    void removeLoadingView();
    void initPresenter();
}
