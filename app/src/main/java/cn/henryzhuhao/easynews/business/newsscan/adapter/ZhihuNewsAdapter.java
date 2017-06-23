package cn.henryzhuhao.easynews.business.newsscan.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.henryzhuhao.easynews.MainActivity;
import cn.henryzhuhao.easynews.R;
import cn.henryzhuhao.easynews.business.Trasitions.DetailTransitions;
import cn.henryzhuhao.easynews.business.newsscan.ZhihuNewsBodyFragment;
import cn.henryzhuhao.mainframe.frame.base.BaseFragment;
import cn.henryzhuhao.mainframe.imageLoader.ImageLoader;

/**
 * Created by HenryZhuhao on 2017/4/10.
 */

public class ZhihuNewsAdapter extends RecyclerView.Adapter<ZhihuNewsAdapter.MyViewHolder> {
    private List<ZhihuNewDate> list;
    private Context context;
    private LayoutInflater mInflater;
    private BaseFragment fragment;
    public ImageLoader imageLoader;

    public ZhihuNewsAdapter(BaseFragment fragment, Context context, List<ZhihuNewDate> list) {
        this.context = context;
        this.fragment = fragment;
        this.list = list;
        mInflater = LayoutInflater.from(context);
        imageLoader = ImageLoader.build(context);
    }

    @Override
    public void onBindViewHolder(final ZhihuNewsAdapter.MyViewHolder holder, final int position) {
        ViewCompat.setTransitionName(holder.newsPic, String.valueOf(position) + "_image");
        // Glide.with(context).load(list.get(position).getPicUrl()).into(holder.newsPic);
        holder.newsPic.setTag(list.get(position).getPicUrl());
        imageLoader.bindBitmap(list.get(position).getPicUrl(), holder.newsPic, holder.newsPic.getMeasuredWidth(), holder.newsPic.getMeasuredHeight());
        //    imageLoader.bindBitmap(list.get(position).getPicUrl(),holder.newsPic,20,20);
        holder.newsText.setText(list.get(position).getTitle());
//        holder.newsDate.setText(list.get(position).getDate());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                holder.newsPic.setTransitionName(position + list.get(position).getPicUrl());
                ZhihuNewsBodyFragment bodyFragment = ZhihuNewsBodyFragment.newInstance(list.get(position).getId(),
                        list.get(position).getTitle(), list.get(position).getPicUrl(), holder.newsPic.getTransitionName());
                Slide slideTransition = new Slide(Gravity.RIGHT);
                slideTransition.setDuration(800);
                fragment.setEnterTransition(slideTransition);
                fragment.setExitTransition(slideTransition);
                fragment.setSharedElementReturnTransition(new DetailTransitions());
                bodyFragment.setEnterTransition(slideTransition);
                bodyFragment.setExitTransition(slideTransition);
                bodyFragment.setSharedElementReturnTransition(new DetailTransitions());
                bodyFragment.setSharedElementEnterTransition(new DetailTransitions());

                fragment.getActivity().getSupportFragmentManager().beginTransaction().addSharedElement(holder.newsPic,
                        "transition");
                ((MainActivity) fragment.getActivity()).startfragment(R.id.activity_container, bodyFragment);


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public ZhihuNewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(R.layout.item_news, parent, false));
        return holder;
    }

    public void updateData(List<ZhihuNewDate> list) {
        this.list = list;
    }

    public void loadMoreData(List<ZhihuNewDate> list) {
        this.list.addAll(list);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        //public int position;
        View content;
        ImageView newsPic;
        TextView newsText;
        TextView newsDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.item_news_content);
            newsPic = (ImageView) itemView.findViewById(R.id.item_news_img);
            newsText = (TextView) itemView.findViewById(R.id.item_news_text);
            newsDate= (TextView) itemView.findViewById(R.id.item_news_date);
        }

    }


}
