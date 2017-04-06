package cn.henryzhuhao.mainframe.frame.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cn.henryzhuhao.henryfragmentation.SupportActivity;
import cn.henryzhuhao.mainframe.frame.base.action.BaseAction;

/**
 * Created by HenryZhuhao on 2017/3/29.
 */

public abstract class BaseActivity extends SupportActivity implements BaseAction {
    private int rootView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(rootView);
        init(savedInstanceState);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initView();
        initData(savedInstanceState);
        initListener();
    }

}
