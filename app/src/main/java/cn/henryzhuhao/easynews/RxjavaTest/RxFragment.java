package cn.henryzhuhao.easynews.RxjavaTest;

import android.os.Bundle;
import android.util.Log;

import cn.henryzhuhao.mainframe.frame.base.BaseFragment;
import rx.Observer;

/**
 * Created by HenryZhuhao on 2017/4/25.
 */

public class RxFragment extends BaseFragment {

    @Override
    public void initView() {

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
        return 0;
    }
    public void observer(){
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                Log.d("tag", "Item: " + s);
            }

            @Override
            public void onCompleted() {
                Log.d("tag", "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("tag", "Error!");
            }
        };
    }
}
