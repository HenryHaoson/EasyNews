package cn.henryzhuhao.mainframe.view.photoview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

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
        PhotoView photoView = new PhotoView(container.getContext());
        Glide.with(context).load(mImages.get(position)).into(photoView);

        // Now just add PhotoView to ViewPager and return it
        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }


}
