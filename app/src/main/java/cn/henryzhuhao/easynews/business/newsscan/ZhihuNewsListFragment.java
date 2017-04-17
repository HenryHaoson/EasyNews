package cn.henryzhuhao.easynews.business.newsscan;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import cn.henryzhuhao.easynews.R;
import cn.henryzhuhao.easynews.business.newsscan.adapter.ZhihuNewDate;
import cn.henryzhuhao.easynews.business.newsscan.adapter.ZhihuNewsAdapter;
import cn.henryzhuhao.easynews.business.newsscan.presenter.ZhihuNewsListPresenter;
import cn.henryzhuhao.easynews.business.newsscan.view.ZhihuNewsListView;
import cn.henryzhuhao.mainframe.frame.base.BaseFragment;

/**
 * Created by HenryZhuhao on 2017/4/10.
 */

public class ZhihuNewsListFragment extends BaseFragment implements ZhihuNewsListView {

    private RecyclerView rcview_zhihulist;
    private ZhihuNewsListPresenter presenter;

    public static ZhihuNewsListFragment newInstance() {

        Bundle args = new Bundle();

        ZhihuNewsListFragment fragment = new ZhihuNewsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView() {
        rcview_zhihulist = (RecyclerView) view.findViewById(R.id.rcview_zhihulist);
        rcview_zhihulist.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcview_zhihulist.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));
        presenter.getZhihuNewsList();
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
        presenter = new ZhihuNewsListPresenter(this);
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_zhihunewslist;
    }

    @Override
    public void loadsuccess(final List<ZhihuNewDate> list) {
        final ZhihuNewsAdapter adapter = new ZhihuNewsAdapter((BaseFragment) getParentFragment(), getContext(), list);
        rcview_zhihulist.post(new Runnable() {
            @Override
            public void run() {
                rcview_zhihulist.setAdapter(adapter);
            }
        });

    }

    @Override
    public void loadFailure(final String error) {
        rcview_zhihulist.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
            }
        });
    }
}
