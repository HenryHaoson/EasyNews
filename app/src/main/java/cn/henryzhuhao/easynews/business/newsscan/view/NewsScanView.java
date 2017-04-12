package cn.henryzhuhao.easynews.business.newsscan.view;

import java.util.List;

import cn.henryzhuhao.easynews.business.newsscan.adapter.NewsDate;

/**
 * Created by HenryZhuhao on 2017/4/7.
 */

public interface NewsScanView {
    void scanNews(List<NewsDate> date);
    void error(String error);
}
