package cn.henryzhuhao.easynews.business.publish.presenter;

import java.util.ArrayList;

import cn.henryzhuhao.easynews.business.publish.model.PublishModel;
import cn.henryzhuhao.easynews.business.publish.view.PublishView;
import cn.henryzhuhao.easynews.entity.ResponseBean.CommonBean;
import cn.henryzhuhao.mainframe.frame.base.action.BaseModel;
import okhttp3.ResponseBody;

/**
 * Created by HenryZhuhao on 2017/6/26.
 */

public class PublishPresenter {
    private PublishView view;
    private PublishModel model;
    public PublishPresenter(PublishView view){
        this.view=view;
        model=new PublishModel();
    }
    public void publish(ArrayList<String> imageUrls, String title, String content, int author){
        model.publish(imageUrls, title, content, author, new BaseModel.LoadDateCallBack<CommonBean>() {
            @Override
            public void loadDateSucceed(CommonBean date) {
                if(date.getMsg().equals("success")){
                    view.publishSuccess(true);
                }
            }

            @Override
            public void loadDateFailed(String error) {
                    view.publishFailed(error);
            }
        });
    }
    public void upload(ArrayList<String> imageUrls){
        model.uploadImages(imageUrls, new BaseModel.LoadDateCallBack<ResponseBody>() {
            @Override
            public void loadDateSucceed(ResponseBody date) {
                view.uploadImageSuccess(true);
            }

            @Override
            public void loadDateFailed(String error) {

            }
        });
    }
}
