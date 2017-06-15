package cn.henryzhuhao.easynews;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.henryzhuhao.easynews.business.newsscan.ZhihuNewsListFragment;
import cn.henryzhuhao.easynews.business.translate.TranslateFragment;
import cn.henryzhuhao.easynews.business.videos.VideoListFragment;
import cn.henryzhuhao.mainframe.frame.base.BaseFragment;
import cn.henryzhuhao.mainframe.view.photoview.HphotoFragment;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by HenryZhuhao on 2017/4/10.
 */
public class Fragment_main extends BaseFragment implements View.OnClickListener {
    public DrawerLayout drawerLayout;
    public ViewPager viewPager;
    public TabLayout tabLayout;
    public Toolbar toolbar;
    public List<Fragment> fragments;
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
        //drawerLayout.setDrawerElevat      //drawerLayout.setScrimColor(Color.TRANSPARENT);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbaritem);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        ZhihuNewsListFragment fragment1 = ZhihuNewsListFragment.newInstance();
        VideoListFragment fragment2 = VideoListFragment.newInstance();
        ZhihuNewsListFragment fragment3 = ZhihuNewsListFragment.newInstance();
//        ZhihuNewsListFragment fragment4 = ZhihuNewsListFragment.newInstance();
        TranslateFragment fragment5 = TranslateFragment.newInstance();
        ArrayList<String> list=new ArrayList<>();
        list.add("http://01.imgmini.eastday.com/mobile/20170407/20170407140225_1c6021bb96a29c833e860198b4d869a9_1_mwpm_03200403.jpeg");
        list.add("http://01.imgmini.eastday.com/mobile/20170407/20170407140225_1c6021bb96a29c833e860198b4d869a9_1_mwpm_03200403.jpeg");
        list.add("http://01.imgmini.eastday.com/mobile/20170407/20170407140225_1c6021bb96a29c833e860198b4d869a9_1_mwpm_03200403.jpeg");
        list.add("http://01.imgmini.eastday.com/mobile/20170407/20170407140225_1c6021bb96a29c833e860198b4d869a9_1_mwpm_03200403.jpeg");
        list.add("http://01.imgmini.eastday.com/mobile/20170407/20170407140225_1c6021bb96a29c833e860198b4d869a9_1_mwpm_03200403.jpeg");
        list.add("http://01.imgmini.eastday.com/mobile/20170407/20170407140225_1c6021bb96a29c833e860198b4d869a9_1_mwpm_03200403.jpeg");
        HphotoFragment fragment4=HphotoFragment.newInstance(list);
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
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = drawerLayout.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
//                float rightScale = 0.8f + scale * 0.2f;


                //float leftScale = 1 - 0.3f * scale;
                AnimatorSet set = new AnimatorSet();
                set.playTogether(
                        //        ObjectAnimator.ofFloat(mMenu, "ScaleX", leftScale),
                        // ObjectAnimator.ofFloat(mMenu, "ScaleY", leftScale),
//                    ObjectAnimator.ofFloat(mMenu, "Alpha", 0.6f + 0.4f * (1 - scale)),
//                    ObjectAnimator.ofFloat(mContent, "TranslationX", mMenu.getMeasuredWidth() * (1 - scale)),
                        ObjectAnimator.ofFloat(mContent, "TranslationX", mMenu.getMeasuredWidth() * (1 - scale))
//                    ObjectAnimator.ofFloat(mContent, "PivotX", 0),
//                    ObjectAnimator.ofFloat(mContent, "PivotY", mContent.getMeasuredHeight() / 2)
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            Log.e("tag",JCVideoPlayer.backPress()+"");
            return;
        }
        super.onBackPressed();
    }

}
