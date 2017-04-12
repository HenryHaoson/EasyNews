package cn.henryzhuhao.easynews.business.newsscan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.henryzhuhao.easynews.R;

/**
 * Created by HenryZhuhao on 2017/4/7.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>{
    private List<NewsDate> list;
    private Context context;
    private LayoutInflater mInflater;
    public NewsAdapter(Context context,List<NewsDate> list){
        this.context=context;
        this.list=list;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getPicUrl()).into(holder.newsPic);
        holder.newsText.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder=new MyViewHolder(mInflater.inflate(R.layout.item_news,null));
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView newsPic;
        TextView newsText;

        public MyViewHolder(View itemView) {
            super(itemView);
            newsPic=(ImageView)itemView.findViewById(R.id.item_news_img);
            newsText=(TextView) itemView.findViewById(R.id.item_news_text);
        }
    }
}
