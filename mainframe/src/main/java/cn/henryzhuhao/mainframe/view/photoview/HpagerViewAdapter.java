package cn.henryzhuhao.mainframe.view.photoview;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by HenryZhuhao on 2017/6/12.
 */

public class HpagerViewAdapter extends PagerAdapter {
    public List<String> mImages;
    public Context context;


    public HpagerViewAdapter(Context context,List<String> images){
        this.context=context;
        this.mImages=images;
    }
    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final PhotoView photoView = new PhotoView(container.getContext());
        Glide.with(context).load(mImages.get(position)).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                photoView.setImageBitmap(resource);
            }
        });

        // Now just add PhotoView to ViewPager and return it
        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }


}
