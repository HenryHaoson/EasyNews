package cn.henryzhuhao.mainframe.view.photoview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.henryzhuhao.mainframe.R;

/**
 * Created by HenryZhuhao on 2017/6/12.
 */

public class HphotoFragment extends Fragment {
    public Context context;
    public View view;
    public ViewPager mViewPager;
    public List<String> mImagesUrls;

    public static HphotoFragment newInstance(ArrayList<String> mImages) {
        
        Bundle args = new Bundle();
        HphotoFragment fragment = new HphotoFragment();
        args.putSerializable("mImagesUrl",mImages);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImagesUrls=getArguments().getStringArrayList("mImagesUrl");
            Log.e("tag",mImagesUrls.toString());
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context=getContext();
        init();
        return view;
    }

    private void init() {
        initView();
    }


    private void initView() {
        view=LayoutInflater.from(context).inflate(R.layout.hphotofragment,null);
        mViewPager= (ViewPager) view.findViewById(R.id.vp_photo);
        mViewPager.setAdapter(new HpagerViewAdapter(context,mImagesUrls));
    }
}
