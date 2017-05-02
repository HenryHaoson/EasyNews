package cn.henryzhuhao.easynews;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.henryzhuhao.easynews.business.newsscan.NewsScanFragment;
import cn.henryzhuhao.easynews.business.newsscan.ZhihuNewsListFragment;
import cn.henryzhuhao.mainframe.frame.base.BaseFragment;

/**
 * Created by HenryZhuhao on 2017/4/10.
 */

public class Fragment_main extends BaseFragment implements View.OnClickListener{
    public DrawerLayout drawerLayout;
    public ViewPager viewPager;
    public TabLayout tabLayout;
    public Toolbar toolbar;
    public List<Fragment> fragments;
    public static String[] title=new String[]{"体育","娱乐","音乐","财经","科技"};
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
        drawerLayout= (DrawerLayout) view.findViewById(R.id.drawer);
        toolbar= (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbaritem);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        viewPager= (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout= (TabLayout) view.findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText(title[0]));
        tabLayout.addTab(tabLayout.newTab().setText(title[1]));
        tabLayout.addTab(tabLayout.newTab().setText(title[2]));
        ZhihuNewsListFragment fragment1=ZhihuNewsListFragment.newInstance();
        NewsScanFragment fragment2=NewsScanFragment.newInstance();
        ZhihuNewsListFragment fragment3=ZhihuNewsListFragment.newInstance();
        ZhihuNewsListFragment fragment4=ZhihuNewsListFragment.newInstance();
        ZhihuNewsListFragment fragment5=ZhihuNewsListFragment.newInstance();
        fragments=new ArrayList<>();
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
        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
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
        switch (view.getId()){

        }
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.toolbaritem,menu);
//    }
}
