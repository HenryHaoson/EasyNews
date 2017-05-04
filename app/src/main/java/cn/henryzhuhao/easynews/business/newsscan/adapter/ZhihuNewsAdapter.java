package cn.henryzhuhao.easynews.business.newsscan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.henryzhuhao.easynews.MainActivity;
import cn.henryzhuhao.easynews.R;
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
    public void onBindViewHolder(ZhihuNewsAdapter.MyViewHolder holder, final int position) {
        // Glide.with(context).load(list.get(position).getPicUrl()).into(holder.newsPic);
        holder.newsPic.setTag(list.get(position).getPicUrl());
        imageLoader.bindBitmap(list.get(position).getPicUrl(), holder.newsPic, holder.newsPic.getMeasuredWidth(), holder.newsPic.getMeasuredHeight());
        //    imageLoader.bindBitmap(list.get(position).getPicUrl(),holder.newsPic,20,20);
        holder.newsText.setText(list.get(position).getTitle());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Bundle args = new Bundle();
                //args.putString(ZhihuNewsBodyFragment.BUNDLE_KEY_NEWS_ID, list.get(position).getId());
                ZhihuNewsBodyFragment.id=list.get(position).getId();
                ZhihuNewsBodyFragment.title=list.get(position).getTitle();
                //fragment.startfragment(R.id.fg_container, ZhihuNewsBodyFragment.newInstance());
                ((MainActivity)fragment.getActivity()).startfragment(R.id.activity_container,ZhihuNewsBodyFragment.newInstance());

            }
        });

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
    public void updateData(List<ZhihuNewDate> list){
        this.list=list;
    }
    public void loadMoreData(List<ZhihuNewDate> list){
        this.list.addAll(list);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        //public int position;
        View content;
        ImageView newsPic;
        TextView newsText;

        public MyViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.item_news_content);
            newsPic = (ImageView) itemView.findViewById(R.id.item_news_img);
            newsText = (TextView) itemView.findViewById(R.id.item_news_text);
            //initLisenter(itemView);
        }
//        public void setNewsItemClickedListener(onNewsItemClickedListener listener){
//
//        }

//        public void initLisenter(final View itemView) {
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Bundle args=new Bundle();
//                    args.putString(ZhihuNewsBodyFragment.BUNDLE_KEY_NEWS_ID,list.get(position).getId());
//                    fragment.startfragment(R.id.fg_container, ZhihuNewsBodyFragment.newInstance());
//                }
//            });
    }




}
