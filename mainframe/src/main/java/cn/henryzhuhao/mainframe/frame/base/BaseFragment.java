package cn.henryzhuhao.mainframe.frame.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.henryzhuhao.henryfragmentation.SupportFragment;
import cn.henryzhuhao.mainframe.frame.base.action.BaseAction;

/**
 * Created by HenryZhuhao on 2017/3/29.
 */

public abstract class BaseFragment extends SupportFragment implements BaseAction{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(savedInstanceState);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initView();
        initData(savedInstanceState);
        initListener();
    }
}
