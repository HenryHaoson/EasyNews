package cn.henryzhuhao.easynews.business.translate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import cn.henryzhuhao.easynews.R;
import cn.henryzhuhao.easynews.app.App;
import cn.henryzhuhao.easynews.business.translate.adapter.WordAdapter;
import cn.henryzhuhao.easynews.business.translate.entity.Cidian;
import cn.henryzhuhao.mainframe.frame.base.BaseFragment;

/**
 * Created by HenryZhuhao on 2017/6/5.
 */

public class TranslateFragment extends BaseFragment {
    public Button addWord;
    public EditText word;
    public EditText translation;
    public RecyclerView rcTranslation;
    public WordAdapter adapter;
    public List<Cidian> list;

    public static TranslateFragment newInstance() {
        
        Bundle args = new Bundle();
        
        TranslateFragment fragment = new TranslateFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initView() {
        addWord= (Button) view.findViewById(R.id.addword);
        word= (EditText) view.findViewById(R.id.word);
        translation= (EditText) view.findViewById(R.id.translation);
        rcTranslation= (RecyclerView) view.findViewById(R.id.translate);
        rcTranslation.setLayoutManager(new LinearLayoutManager(getContext()));
        list=App.getInstance().getDaoSession().getCidianDao().loadAll();
        adapter=new WordAdapter(getContext(), list);
        rcTranslation.setAdapter(adapter);

        addWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewWord(word.getText().toString(),translation.getText().toString());
            }
        });
    }



    public void addNewWord(@NonNull String word,@NonNull String translation){
        Cidian cidian=new Cidian();
        cidian.setCh(translation);
        cidian.setEn(word);
        App.getInstance().getDaoSession().getCidianDao().insert(cidian);
        list=App.getInstance().getDaoSession().getCidianDao().loadAll();
        adapter.updateData(list);
        adapter.notifyDataSetChanged();
        Log.e("tag",list.get(0).getEn());
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
        return R.layout.fragment_translate;
    }
}
