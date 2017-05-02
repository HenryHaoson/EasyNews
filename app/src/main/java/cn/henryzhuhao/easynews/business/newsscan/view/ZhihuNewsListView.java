package cn.henryzhuhao.easynews.business.newsscan.view;

import java.util.List;

import cn.henryzhuhao.easynews.business.newsscan.adapter.ZhihuNewDate;

/**
 * Created by HenryZhuhao on 2017/4/10.
 */

public interface ZhihuNewsListView {
    void loadsuccess(List<ZhihuNewDate> list);
    void loadFailure(String error);
    void loadMore(List<ZhihuNewDate> list);
}
