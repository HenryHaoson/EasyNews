package cn.henryzhuhao.easynews.business.newsscan;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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

    private List<ZhihuNewDate> list;
    private RecyclerView rcview_zhihulist;
    private ZhihuNewsListPresenter presenter;
    private ZhihuNewsAdapter adapter;
    private Boolean isFirst=true;
    private SwipeRefreshLayout mRefresh;

    public static ZhihuNewsListFragment newInstance() {

        Bundle args = new Bundle();

        ZhihuNewsListFragment fragment = new ZhihuNewsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView() {
        mRefresh= (SwipeRefreshLayout) view.findViewById(R.id.zhihulist_refresh);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getZhihuList();
            }
        });
        rcview_zhihulist = (RecyclerView) view.findViewById(R.id.rcview_zhihulist);
        rcview_zhihulist.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcview_zhihulist.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));
        rcview_zhihulist.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isVisBottom(recyclerView)){
                    presenter.loadMoreZhihuNewsList();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

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

    public void getZhihuList(){
        presenter.getZhihuNewsList();
    }
    @Override
    public void loadsuccess(final List<ZhihuNewDate> list) {
        this.list=list;
        if(isFirst){
            adapter = new ZhihuNewsAdapter(this, getContext(), this.list);
            rcview_zhihulist.post(new Runnable() {
                @Override
                public void run() {
                    rcview_zhihulist.setAdapter(adapter);
                }
            });
            isFirst=false;
        }else {
            rcview_zhihulist.post(new Runnable() {
                @Override
                public void run() {
                    adapter.updateData(list);
                    adapter.notifyDataSetChanged();
                }
            });

        }
        mRefresh.post(new Runnable() {
            @Override
            public void run() {
                mRefresh.setRefreshing(false);
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

    @Override
    public void loadMore(final List<ZhihuNewDate> list) {
        rcview_zhihulist.post(new Runnable() {
            @Override
            public void run() {
                adapter.loadMoreData(list);
                adapter.notifyDataSetChanged();
            }
        });
    }
    public static boolean isVisBottom(RecyclerView recyclerView){
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //屏幕中最后一个可见子项的position
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        //当前屏幕所看到的子项个数
        int visibleItemCount = layoutManager.getChildCount();
        //当前RecyclerView的所有子项个数
        int totalItemCount = layoutManager.getItemCount();
        //RecyclerView的滑动状态
        int state = recyclerView.getScrollState();
        if(visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1 && state == recyclerView.SCROLL_STATE_IDLE){
            return true;
        }else {
            return false;
        }
    }
}
