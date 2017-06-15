package cn.henryzhuhao.easynews.business.translate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.henryzhuhao.easynews.R;
import cn.henryzhuhao.easynews.business.translate.entity.Cidian;

/**
 * Created by HenryZhuhao on 2017/6/6.
 */

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.TranslateHolder> {
    private List<Cidian> list;
    private Context context;


    public WordAdapter(Context context,List<Cidian> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public TranslateHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TranslateHolder holder=new TranslateHolder(LayoutInflater.from(context)
                    .inflate(R.layout.item_translation,parent,false));
        return holder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(TranslateHolder holder, int position) {
        holder.word.setText(list.get(position).getEn());

        holder.translation.setText(list.get(position).getCh());

    }
    public void updateData(List<Cidian> list) {
        this.list = list;
    }

    class TranslateHolder extends RecyclerView.ViewHolder {
        //public int position;
        TextView word;
        TextView translation;

         TranslateHolder(View itemView) {
            super(itemView);
            word= (TextView) itemView.findViewById(R.id.item_translation_word);
            translation= (TextView) itemView.findViewById(R.id.item_translation_translation);
        }

    }
}
