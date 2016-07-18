package com.am.mdrr.mdsimple;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.am.mdrr.R;
import com.am.mdrr.fragment.DetailFragment;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by AM on 2016/7/16.
 */
public class TabLayoutActivity  extends AppCompatActivity {

    private ViewPager mViewPager;
    private TextView mTvTip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getTip();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("海贼王");
        collapsingToolbar.setExpandedTitleColor(Color.BLUE);
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);

        ImageView ivImage = (ImageView) findViewById(R.id.ivImage);
        ivImage.setImageResource(R.mipmap.lf);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("基本资料"));
        tabLayout.addTab(tabLayout.newTab().setText("身世"));
        tabLayout.addTab(tabLayout.newTab().setText("性格"));
        tabLayout.addTab(tabLayout.newTab().setText("家谱"));
        tabLayout.addTab(tabLayout.newTab().setText("海贼团"));
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager mViewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(DetailFragment.newInstance(getAsset("book_content.txt")), "基本资料");
        adapter.addFragment(DetailFragment.newInstance(getAsset("book_author.txt")), "身世");
        adapter.addFragment(DetailFragment.newInstance(getAsset("book_menu.txt")), "性格");
        adapter.addFragment(DetailFragment.newInstance(getAsset("lufei_home.txt")), "家谱");
        adapter.addFragment(DetailFragment.newInstance(getAsset("lufei_friend.txt")), "海贼团");
        mViewPager.setAdapter(adapter);
    }

    private String getAsset(String fileName) {
        AssetManager am = getResources().getAssets();
        InputStream is = null;
        try {
            is = am.open(fileName, AssetManager.ACCESS_BUFFER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Scanner(is).useDelimiter("\\Z").next();
    }

    public void getTip() {
        mTvTip = (TextView) findViewById(R.id.mTvTip);
        mTvTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TabLayoutActivity.this);
                // 设置标题
                builder.setTitle("提示：");
                // 设置描述信息
                builder.setMessage("TabLayout提供了一个水平的布局用来展示Tabs。\n" +
                        "TabLayout通过 newTab() 可以创建多个tab标签。\n" +
                        "可以通过setText()和setIcon分别设置文本和icon，最后通过addTab将newTab处的选项卡添加上去即可 。\n" +
                        "但是这个是单一的选项卡使用 一般使用选项卡我们都是和viewpager相结合使用的。"
                );
                builder.show();
            }
        });
    }


    static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}