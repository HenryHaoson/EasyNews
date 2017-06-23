package cn.henryzhuhao.easynews.business.publish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cn.henryzhuhao.easynews.MainActivity;
import cn.henryzhuhao.easynews.R;
import cn.henryzhuhao.mainframe.frame.base.BaseFragment;
import cn.henryzhuhao.mainframe.utils.ScreenUtils;
import cn.henryzhuhao.mainframe.view.photoview.HphotoFragment;
import me.iwf.photopicker.PhotoPicker;

import static android.app.Activity.RESULT_OK;

/**
 * Created by HenryZhuhao on 2017/6/20.
 */

public class PublishFragment extends BaseFragment {
    public LinearLayout images;
    public Toolbar toolbar;
    public static PublishFragment newInstance() {
        
        Bundle args = new Bundle();
        
        PublishFragment fragment = new PublishFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initView() {
        toolbar=(Toolbar)view.findViewById(R.id.publish_toolbar);
        images= (LinearLayout) view.findViewById(R.id.images);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {
        view.findViewById(R.id.image_picker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPicker.builder()
                        .setPhotoCount(9)
                        .setShowCamera(true)
                        .setShowGif(true)
                        .setPreviewEnabled(false)
                        .start(getActivity(),PublishFragment.this, PhotoPicker.REQUEST_CODE);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });
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
        return R.layout.fragment_publish;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            Log.e("tag","getbackImages");
            if (data != null) {
                final ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                for (int i = 0; i <photos.size() ; i++) {
                    RelativeLayout.LayoutParams paramsImage = new RelativeLayout.
                            LayoutParams(ScreenUtils.dp2px(getContext(),80),ScreenUtils.dp2px(getContext(),80));
                    ImageView img=new ImageView(getContext());
                    img.setLayoutParams(paramsImage);
                    img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((MainActivity)getActivity()).startfragment(R.id.activity_container,HphotoFragment.newInstance(photos));
                        }
                    });
                    images.addView(img);
                    Glide.with(getContext()).load(photos.get(i)).into(img);
                }
            }
        }
    }
}
