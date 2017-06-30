package cn.henryzhuhao.mainframe.view.photoview;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.List;

import cn.henryzhuhao.henryfragmentation.SupportFragment;
import cn.henryzhuhao.mainframe.R;

/**
 * Created by HenryZhuhao on 2017/6/12.
 */

public class HphotoFragment extends SupportFragment {
    public Context context;
    public View view;
    public ViewPager mViewPager;
    public List<String> mImagesUrls;
    public ImageView saveImg;
    public TextView numCount;
    public int numCurrent=0;

    public static HphotoFragment newInstance(ArrayList<String> mImages) {
        Bundle args = new Bundle();
        HphotoFragment fragment = new HphotoFragment();
        args.putSerializable("mImagesUrl",mImages);
        fragment.setArguments(args);
        return fragment;
    }
    public static HphotoFragment newInstance(ArrayList<String> mImages,int numCurrent) {
        Bundle args = new Bundle();
        HphotoFragment fragment = new HphotoFragment();
        args.putSerializable("mImagesUrl",mImages);
        args.putInt("numCurrent",numCurrent);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImagesUrls=getArguments().getStringArrayList("mImagesUrl");
            numCurrent=getArguments().getInt("numCurrent");
            Log.e("tag",mImagesUrls.toString());
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context=getContext();
        init();
        return view;
    }

    private void init() {
        initView();
    }


    private void initView() {
        view=LayoutInflater.from(context).inflate(R.layout.hphotofragment,null);
        mViewPager= (ViewPager) view.findViewById(R.id.vp_photo);
        mViewPager.setAdapter(new HpagerViewAdapter(context,mImagesUrls));
        mViewPager.setCurrentItem(numCurrent);
        saveImg= (ImageView) view.findViewById(R.id.save);
        numCount= (TextView) view.findViewById(R.id.num_count);
        numCount.setText((numCurrent+1)+"/"+mImagesUrls.size());
        initListener();
    }
    private void initData(){

    }
    public void initListener(){
        saveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("photoview","imageviewClicked");
                save();
            }
        });
     mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
         @Override
         public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

         }

         @Override
         public void onPageSelected(int position) {
            numCurrent=position+1;
             numCount.setText(numCurrent+"/"+mImagesUrls.size());
         }

         @Override
         public void onPageScrollStateChanged(int state) {

         }
     });
    }
    public void save(){
        final int num=mViewPager.getCurrentItem();
//        SavePhotoUtils utils=new SavePhotoUtils(getContext());
//        utils.savePicture(mImagesUrls.get(num),mImagesUrls.get(num));
        Glide.with(context).load(mImagesUrls.get(num)).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                String url=MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), resource, mImagesUrls.get(num), "这是description");
                Uri uuUri= Uri.parse(url);
                String path =  getRealPathFromURI(uuUri);
                Toast.makeText(context,"图片保存在"+path+"下",Toast.LENGTH_SHORT).show();
                Log.e("photoview",url+"\n"+path);
            }
        });

        Log.e("photoview",num+mImagesUrls.get(num));
    }
    public String getRealPathFromURI(Uri contentUri) {//通过本地路经 content://得到URI路径
        Cursor cursor = null;
        String locationPath = null ;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor= getActivity().getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            locationPath = cursor.getString(column_index);
        } catch (Exception e) {
        }finally{
            if(cursor != null)
            {
                cursor.close();
            }
        }
        return locationPath;
    }
}
