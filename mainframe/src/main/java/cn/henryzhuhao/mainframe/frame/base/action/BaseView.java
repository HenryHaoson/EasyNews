package cn.henryzhuhao.mainframe.frame.base.action;

/**
 * Created by HenryZhuhao on 2017/4/6.
 */

public interface BaseView {
    /**
     * 隐藏主体视图
     */
    void hideContentView();

    /**
     * 加载主体视图
     */
    void showContentView();

    /**
     * 设置presenter
     */
    void setPresenter();

    /**
     * 返回请求信息
     * @param value
     */
    void showReqResult(String value);

}
