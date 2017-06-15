package cn.henryzhuhao.easynews.business.videos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import cn.henryzhuhao.easynews.R;

/**
 * Created by HenryZhuhao on 2017/5/17.
 */

public class VideoListAdapter extends RecyclerView.Adapter<VideoItemViewHolder> {

    private Context context;

    public VideoListAdapter(Context context){
        this.context=context;
    }
    @Override
    public VideoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        VideoItemViewHolder holder = new VideoItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_video, parent,false),context);
        return holder;
    }

    @Override
    public void onBindViewHolder(VideoItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
