package cn.henryzhuhao.easynews;

import android.content.IntentFilter;
import android.os.Bundle;

import cn.henryzhuhao.easynews.business.Broadcast.MyReceiver;
import cn.henryzhuhao.mainframe.frame.base.BaseActivity;

public class MainActivity extends BaseActivity {
    @Override
    public void initView() {
        startfragment(R.id.activity_container, Fragment_main.newInstance());
         // startfragment(R.id.activity_container, VideoListFragment.newInstance());
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
    public void reReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.henry.call.LAUNCH");
        registerReceiver(new MyReceiver(),intentFilter);
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
