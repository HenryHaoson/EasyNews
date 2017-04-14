package cn.henryzhuhao.easynews;

import android.os.Bundle;

import cn.henryzhuhao.easynews.business.newsscan.ZhihuNewsListFragment;
import cn.henryzhuhao.mainframe.frame.base.BaseFragment;

/**
 * Created by HenryZhuhao on 2017/4/10.
 */

public class Fragment_main extends BaseFragment {
    public static Fragment_main newInstance() {
        
        Bundle args = new Bundle();
        
        Fragment_main fragment = new Fragment_main();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initView() {
        startfragment(R.id.fg_container, ZhihuNewsListFragment.newInstance());
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
        return R.layout.fragment_main;
    }
}
