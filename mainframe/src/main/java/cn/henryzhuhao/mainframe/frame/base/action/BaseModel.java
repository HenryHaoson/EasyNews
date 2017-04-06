package cn.henryzhuhao.mainframe.frame.base.action;

/**
 * Created by HenryZhuhao on 2017/4/6.
 */

public interface BaseModel {
    /**
     * model层请求数据参数，成功获取回调loadDateSucceed
     * 获取失败loadDateFailed
     * @param <T>
     */
    interface LoadDateCallBack<T>{
        /**
         * 获取成功返回获取的数据
         * @param date
         */
        void loadDateSucceed(T date);

        /**
         * 获取失败返回失败信息
         * @param error
         */
        void loadDateFailed(String error);
    }
}
