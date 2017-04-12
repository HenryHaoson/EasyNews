package cn.henryzhuhao.easynews.business.newsscan;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import cn.henryzhuhao.easynews.R;
import cn.henryzhuhao.easynews.business.newsscan.adapter.NewsAdapter;
import cn.henryzhuhao.easynews.business.newsscan.adapter.NewsDate;
import cn.henryzhuhao.easynews.business.newsscan.presenter.NewsScanPresenter;
import cn.henryzhuhao.easynews.business.newsscan.view.NewsScanView;
import cn.henryzhuhao.mainframe.frame.base.BaseFragment;

/**
 * Created by HenryZhuhao on 2017/4/6.
 */

public class NewsScanFragment extends BaseFragment implements NewsScanView{
    private RecyclerView rcviewNews;
    private NewsScanPresenter presenter;
    private NewsAdapter mAdapter;


    public static NewsScanFragment newInstance() {
        
        Bundle args = new Bundle();
        
        NewsScanFragment fragment = new NewsScanFragment();
        fragment.setArguments(args);
        return fragment;    
    }
    @Override
    public void initView() {
        rcviewNews=(RecyclerView)view.findViewById(R.id.rcview_news);
        rcviewNews.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rcviewNews.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        presenter=new NewsScanPresenter(this);
        presenter.scannews();

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void showContentView() {

    }

    @Override
    public void initListener() {

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
    public void scanNews(final List<NewsDate> date) {
        rcviewNews.post(new Runnable() {
            @Override
            public void run() {
                mAdapter=new NewsAdapter(getContext(),date);
                rcviewNews.setAdapter(mAdapter);
            }
        });

    }

    @Override
    public void error(String error) {

    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_newsscan;
    }
}
