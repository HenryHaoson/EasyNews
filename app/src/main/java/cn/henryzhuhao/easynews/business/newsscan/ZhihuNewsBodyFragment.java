package cn.henryzhuhao.easynews.business.newsscan;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import cn.henryzhuhao.easynews.R;
import cn.henryzhuhao.easynews.app.App;
import cn.henryzhuhao.easynews.business.newsscan.adapter.ZhihuNewsBody;
import cn.henryzhuhao.easynews.business.newsscan.presenter.ZhihuNewsBodyPresenter;
import cn.henryzhuhao.easynews.business.newsscan.view.ZhihuNewsBodyView;
import cn.henryzhuhao.mainframe.frame.base.BaseFragment;

/**
 * Created by HenryZhuhao on 2017/4/10.
 */

public class ZhihuNewsBodyFragment extends BaseFragment implements ZhihuNewsBodyView {
    public WebView webView;
    public ZhihuNewsBodyPresenter presenter;
    public String id;


    public static ZhihuNewsBodyFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ZhihuNewsBodyFragment fragment = new ZhihuNewsBodyFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initView() {
        webView = (WebView) view.findViewById(R.id.web_zhihu_body);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);//适应内容大小
        id= App.getInstance().getZhihuId();
        if(id==null){

        }else {
            Log.e("tag",id);
        }

        presenter=new ZhihuNewsBodyPresenter(this);
        presenter.getZhihuNewsBody(id);
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
        return R.layout.fragment_zhihunewsbody;
    }

    @Override
    public void loadSuccess(final ZhihuNewsBody body) {
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadDataWithBaseURL(null, body.getBody(), "text/html", "utf-8", null);
            }
        });

    }
}
