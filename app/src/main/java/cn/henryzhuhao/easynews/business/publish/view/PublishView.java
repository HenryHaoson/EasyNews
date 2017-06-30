package cn.henryzhuhao.easynews.business.publish.view;

/**
 * Created by HenryZhuhao on 2017/6/26.
 */

public interface PublishView {
    void publishSuccess(Boolean isSuccess);
    void publishFailed(String error);
    void uploadImageSuccess(Boolean isSuccess);
    void uploadImageFailed(String error);
}
