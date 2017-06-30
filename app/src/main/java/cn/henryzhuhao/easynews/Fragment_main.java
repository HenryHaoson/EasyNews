package cn.henryzhuhao.easynews;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.henryzhuhao.easynews.app.App;
import cn.henryzhuhao.easynews.app.AppContants;
import cn.henryzhuhao.easynews.business.login.LoginFragment;
import cn.henryzhuhao.easynews.business.newsscan.TopNewsFragment;
import cn.henryzhuhao.easynews.business.newsscan.ZhihuNewsListFragment;
import cn.henryzhuhao.easynews.business.publish.PublishFragment;
import cn.henryzhuhao.easynews.business.translate.TranslateFragment;
import cn.henryzhuhao.easynews.business.videos.VideoListFragment;
import cn.henryzhuhao.mainframe.frame.base.BaseFragment;
import de.hdodenhof.circleimageview.CircleImageView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import me.iwf.photopicker.PhotoPicker;

import static android.app.Activity.RESULT_OK;

/**
 * Created by HenryZhuhao on 2017/4/10.
 */
public class Fragment_main extends BaseFragment {
    public DrawerLayout drawerLayout;
    public ViewPager viewPager;
    public TabLayout tabLayout;
    public Toolbar toolbar;
    public List<Fragment> fragments;
    public NavigationView nav;
    public CircleImageView headimage;

    public static String[] title = new String[]{"体育", "娱乐", "音乐", "财经", "科技"};

    public static Fragment_main newInstance() {

        Bundle args = new Bundle();

        Fragment_main fragment = new Fragment_main();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initPresenter();
    }

    @Override
    public void initView() {
        setHasOptionsMenu(true);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbaritem);
        nav= (NavigationView) view.findViewById(R.id.nav);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        View headview=nav.getHeaderView(0);
        headimage= (CircleImageView) headview.findViewById(R.id.nav_headimg);
        Glide.with(getContext()).load(App.getInstance().getHeadImageUrl()).error(R.drawable.appicon).into(headimage);
        ZhihuNewsListFragment fragment1 = ZhihuNewsListFragment.newInstance();
        VideoListFragment fragment2 = VideoListFragment.newInstance();
        TopNewsFragment fragment3 = TopNewsFragment.newInstance();
        ZhihuNewsListFragment fragment4 = ZhihuNewsListFragment.newInstance();
        TranslateFragment fragment5 = TranslateFragment.newInstance();

        fragments = new ArrayList<>();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        fragments.add(fragment5);
        //不能用是因为tag已经被设置了
//        getFragmentManager().beginTransaction().add(fragment1,fragment1.getTag());
//        getFragmentManager().beginTransaction().add(fragment2,fragment2.getTag());
//        getFragmentManager().beginTransaction().add(fragment1,fragment1.getTag());
        //       getFragmentManager().beginTransaction().commit();
        //初始化的时候用getChildFragmentManager(),不然返回后viewpager会显示空白页
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });
        viewPager.setOffscreenPageLimit(5);
        tabLayout.setupWithViewPager(viewPager);

        // startfragment(R.id.fg_container, ZhihuNewsListFragment.newInstance());
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_logout:App.getInstance().setLoginStatus(AppContants.LOGINSTATUS_LOGOUT);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_publish:activity.startfragment(R.id.activity_container, PublishFragment.newInstance());
                }
                return false;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                drawerLayout.openDrawer(Gravity.LEFT);
                if (App.getInstance().getLoginStatus() == AppContants.LOGINSTATUS_LOGOUT) {
                    activity.startfragment(R.id.activity_container, LoginFragment.newInstance());
                }else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });

        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = drawerLayout.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                AnimatorSet set = new AnimatorSet();
                set.playTogether(
                        ObjectAnimator.ofFloat(mContent, "TranslationX", mMenu.getMeasuredWidth() * (1 - scale))
                );
                set.setDuration(0);
                set.start();
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        headimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(true)
                        .setShowGif(true)
                        .setPreviewEnabled(false)
                        .start(getActivity(),Fragment_main.this, PhotoPicker.REQUEST_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            Log.e("tag","getbackImages");
            if (data != null) {
                final ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                Glide.with(getContext()).load(photos.get(0)).into(headimage);
                App.getInstance().setHeadImageUrl(photos.get(0));
                }
            }
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
        return R.layout.fragment_main;
    }

    private long firstTime = 0;
    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            Log.e("tag", JCVideoPlayer.backPress() + "");
            return;
        }
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {                                         //如果两次按键时间间隔大于2秒，则不退出
            Toast.makeText(getContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;//更新firstTime
             return ;
        } else {                                                    //两次按键小于2秒时，退出应用
             activity.finish();
        }
    }




}
