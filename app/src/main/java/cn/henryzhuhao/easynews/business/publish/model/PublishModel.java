package cn.henryzhuhao.easynews.business.publish.model;

import java.io.File;
import java.util.ArrayList;

import cn.henryzhuhao.easynews.business.Api.TopNewsApi;
import cn.henryzhuhao.easynews.business.publish.utils.FileRenameUtils;
import cn.henryzhuhao.easynews.entity.ResponseBean.CommonBean;
import cn.henryzhuhao.mainframe.frame.base.action.BaseModel;
import cn.henryzhuhao.mainframe.net.ApiFactory;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by HenryZhuhao on 2017/6/26.
 */

public class PublishModel implements BaseModel {
    public void publish(ArrayList<String> imageUrls, String title, String content, int author,
                        final LoadDateCallBack<CommonBean> callBack) {
        ArrayList<String> list=FileRenameUtils.fileRename(imageUrls);
        TopNewsApi api = ApiFactory.newInstance().createApi(TopNewsApi.class);
        Observable<CommonBean> observable = api.publish(author, title, content,
                "http://106.15.194.192:8080/baoliao/"+ list.get(0),
                "http://106.15.194.192:8080/baoliao/"+ list.get(1),
                "http://106.15.194.192:8080/baoliao/"+ list.get(2));
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommonBean value) {
                        callBack.loadDateSucceed(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void uploadImages(ArrayList<String> imageUrls, final LoadDateCallBack<ResponseBody> callBack) {
        TopNewsApi api = ApiFactory.newInstance().createApi(TopNewsApi.class);
        for (int i = 0; i < imageUrls.size(); i++) {
            File file = new File(imageUrls.get(i));
            String s[]=imageUrls.get(i).split("/");
            String filename=s[s.length-1];

            RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("savePath", "c:\\baoliao")
                    .addFormDataPart("upload", "http://106.15.194.192:8080/baoliao/"+filename, RequestBody.create(MediaType.parse("image/*"), file))
                    .build();
            Observable<ResponseBody> observable = api.uploadImage(requestBody);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ResponseBody value) {
                            callBack.loadDateSucceed(value);

                        }

                        @Override
                        public void onError(Throwable e) {
                            callBack.loadDateFailed(e.toString());
//                            Log.e("upload", "model"+e.toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }

    }
}
