package cn.henryzhuhao.easynews.business.newsscan.view;

import java.util.List;

import cn.henryzhuhao.easynews.entity.ResponseBean.TopNewsList;

/**
 * Created by HenryZhuhao on 2017/6/26.
 */

public interface TopNewsListView {
    void getTopNewsListSuccess(List<TopNewsList.DataBean> list);
    void getTopNewsListFailed(String error);

}
