package cn.henryzhuhao.easynews.business.newsscan;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cn.henryzhuhao.easynews.R;
import cn.henryzhuhao.easynews.entity.ResponseBean.TopNewsList;
import cn.henryzhuhao.mainframe.frame.base.BaseFragment;

/**
 * Created by HenryZhuhao on 2017/6/26.
 */

public class TopNewsBodyFragment extends BaseFragment {
    TextView title;
    TextView content;
    ImageView pic1;
    ImageView pic2;
    ImageView pic3;
    ImageView topPic;
    Toolbar toolbar;
    public static TopNewsList.DataBean bodydata;

    public static TopNewsBodyFragment newInstance(TopNewsList.DataBean data) {

        Bundle args = new Bundle();
        bodydata=data;
        TopNewsBodyFragment fragment = new TopNewsBodyFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initView() {
        title= (TextView) view.findViewById(R.id.topnews_body_title);
        content= (TextView) view.findViewById(R.id.topnews_body_content);
        pic1= (ImageView) view.findViewById(R.id.topnews_body_pic1);
        pic2= (ImageView) view.findViewById(R.id.topnews_body_pic2);
        pic3= (ImageView) view.findViewById(R.id.topnews_body_pic3);
        topPic= (ImageView) view.findViewById(R.id.topnews_body_topPic);
        toolbar= (Toolbar) view.findViewById(R.id.topnews_toolbar);
        toolbar.setTitle(bodydata.getTitle());
        title.setText(bodydata.getTitle());
        content.setText(bodydata.getContent());
        Glide.with(getContext()).load(bodydata.getPicurl1()).error(R.drawable.appicon).into(topPic);
        Glide.with(getContext()).load(bodydata.getPicurl1()).error(R.drawable.appicon).into(pic1);
        Glide.with(getContext()).load(bodydata.getPicurl2()).error(R.drawable.appicon).into(pic2);
        Glide.with(getContext()).load(bodydata.getPicurl3()).error(R.drawable.appicon).into(pic3);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close();
            }
        });
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
        return R.layout.fragment_topnewsbody;
    }
}
