package cn.henryzhuhao.easynews.business.newsscan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.henryzhuhao.easynews.R;
import cn.henryzhuhao.easynews.app.App;
import cn.henryzhuhao.easynews.business.newsscan.ZhihuNewsBodyFragment;
import cn.henryzhuhao.mainframe.frame.base.BaseFragment;

/**
 * Created by HenryZhuhao on 2017/4/10.
 */

public class ZhihuNewsAdapter extends RecyclerView.Adapter<ZhihuNewsAdapter.MyViewHolder> {
    private List<ZhihuNewDate> list;
    private Context context;
    private LayoutInflater mInflater;
    private BaseFragment fragment;

    public ZhihuNewsAdapter(BaseFragment fragment, Context context, List<ZhihuNewDate> list) {
        this.context = context;
        this.fragment = fragment;
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(ZhihuNewsAdapter.MyViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getPicUrl()).into(holder.newsPic);
        holder.newsText.setText(list.get(position).getTitle());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public ZhihuNewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(R.layout.item_news, null));
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public int position;
        ImageView newsPic;
        TextView newsText;

        public MyViewHolder(View itemView) {
            super(itemView);
            newsPic = (ImageView) itemView.findViewById(R.id.item_news_img);
            newsText = (TextView) itemView.findViewById(R.id.item_news_text);
            initLisenter(itemView);
        }

        public void initLisenter(View itemView) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    App.getInstance().setZhihuId(list.get(position).getId());
                    fragment.startfragment(R.id.fg_container, ZhihuNewsBodyFragment.newInstance());

                    Log.e("tag", list.get(position).getId());
                }
            });
        }
    }
}
