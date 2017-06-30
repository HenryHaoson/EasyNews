package cn.henryzhuhao.easynews.business.newsscan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import cn.henryzhuhao.easynews.MainActivity;
import cn.henryzhuhao.easynews.R;
import cn.henryzhuhao.easynews.business.newsscan.TopNewsBodyFragment;
import cn.henryzhuhao.easynews.entity.ResponseBean.TopNewsList;

/**
 * Created by HenryZhuhao on 2017/6/26.
 */

public class TopNewsAdapter extends RecyclerView.Adapter<TopNewsAdapter.MyViewHolder> {
    public List<TopNewsList.DataBean> list;
    private Context context;
    private LayoutInflater mInflater;

    public TopNewsAdapter(Context context,List<TopNewsList.DataBean> list){
        this.list=list;
        Collections.reverse(list);
        this.context=context;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(R.layout.item_news, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.newsText.setText(list.get(position).getTitle());
        holder.newsDate.setText("2017-06-27 19:31");
        Glide.with(context).load(list.get(position).getPicurl1()).placeholder(R.drawable.appicon).error(R.drawable.appicon).into(holder.newsPic);
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ( (MainActivity)context).startfragment(R.id.activity_container,new TopNewsBodyFragment().newInstance(list.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void update(List<TopNewsList.DataBean> list){
        this.list=list;
        Collections.reverse(list);
        notifyDataSetChanged();
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
            newsDate = (TextView) itemView.findViewById(R.id.item_news_date);
        }
    }
}
