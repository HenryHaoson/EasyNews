package cn.henryzhuhao.easynews;

import android.os.Bundle;

import cn.henryzhuhao.mainframe.frame.base.BaseActivity;

public class MainActivity extends BaseActivity {
    @Override
    public void initView() {
        startfragment(R.id.activity_container, Fragment_main.newInstance());
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void showContentView() {

    }

    @Override
    public void hideContentView() {

    }

    @Override
    public void showLoadingContentView() {

    }

    @Override
    public void removeLoadingContentView() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_main;
    }
}
