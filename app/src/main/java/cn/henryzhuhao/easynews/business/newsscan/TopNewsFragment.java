package cn.henryzhuhao.easynews.business.newsscan;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import cn.henryzhuhao.easynews.R;
import cn.henryzhuhao.easynews.business.newsscan.adapter.TopNewsAdapter;
import cn.henryzhuhao.easynews.business.newsscan.presenter.TopNewsListPresenter;
import cn.henryzhuhao.easynews.business.newsscan.view.TopNewsListView;
import cn.henryzhuhao.easynews.entity.ResponseBean.TopNewsList;
import cn.henryzhuhao.mainframe.frame.base.BaseFragment;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;

import static cn.henryzhuhao.easynews.business.newsscan.ZhihuNewsListFragment.isVisBottom;

/**
 * Created by HenryZhuhao on 2017/6/26.
 */

public class TopNewsFragment extends BaseFragment implements TopNewsListView{
    private List<TopNewsList.DataBean> list;
    private RecyclerView rcview_topnewslist;
    private TopNewsListPresenter presenter;
    private TopNewsAdapter adapter;
    private Boolean isFirst=true;
    private SwipeRefreshLayout mRefresh;

    public static TopNewsFragment newInstance() {

        Bundle args = new Bundle();

        TopNewsFragment fragment = new TopNewsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initView() {
        mRefresh= (SwipeRefreshLayout) view.findViewById(R.id.topnews_refresh);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getTopNewsList();
            }
        });
        rcview_topnewslist = (RecyclerView) view.findViewById(R.id.rcview_topnewslist);

        rcview_topnewslist.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcview_topnewslist.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));
        rcview_topnewslist.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isVisBottom(recyclerView)){
                   // presenter.loadMoreZhihuNewsList();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        presenter.getTopNewsList();
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
        presenter=new TopNewsListPresenter(this);
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_topnewslist;
    }

    @Override
    public void getTopNewsListSuccess(List<TopNewsList.DataBean> list) {
        this.list=list;
        if(isFirst){
            adapter = new TopNewsAdapter(getContext(), this.list);
            SlideInRightAnimationAdapter animAdapter = new SlideInRightAnimationAdapter(adapter);
            rcview_topnewslist.setAdapter(animAdapter);
            isFirst=false;
        }else {
                    adapter.update(list);
                    adapter.notifyDataSetChanged();
        }

                mRefresh.setRefreshing(false);
    }

    @Override
    public void getTopNewsListFailed(String error) {

    }
}
