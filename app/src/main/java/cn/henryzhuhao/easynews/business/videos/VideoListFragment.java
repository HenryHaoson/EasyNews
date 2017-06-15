package cn.henryzhuhao.easynews.business.videos;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import cn.henryzhuhao.easynews.R;
import cn.henryzhuhao.easynews.business.videos.adapter.VideoListAdapter;
import cn.henryzhuhao.mainframe.frame.base.BaseFragment;

/**
 * Created by HenryZhuhao on 2017/5/17.
 */

public class VideoListFragment extends BaseFragment {
    private RecyclerView recyclerView;


    public static VideoListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        VideoListFragment fragment = new VideoListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initView() {
        recyclerView= (RecyclerView) view.findViewById(R.id.video_list);
        VideoListAdapter adapter=new VideoListAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        Log.e("tag",adapter.getItemCount()+"");
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
        return R.layout.fragment_videolist;
    }


}
