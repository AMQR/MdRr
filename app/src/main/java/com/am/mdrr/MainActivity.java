package com.am.mdrr;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

import fragment.AboutFragment;
import fragment.MdFragment;
import fragment.ReFragment;
import fragment.RxFragment;
import fragment.RxReFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);

        supportFragmentManager = getSupportFragmentManager();

        // 抽屉整体的一些设置，toggle同步，添加toggle同步  没卵用但是一定要加
        mDrawerLayout = (DrawerLayout) findViewById(R.id.mDrawerLayout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open,
                R.string.close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);

        // 菜单的头部
        mNavigationView = (NavigationView) findViewById(R.id.mNavigation);
        View headerView = mNavigationView.getHeaderView(0); // 得到菜单 的头部
        if(headerView!=null){
            headerView.findViewById(R.id.mIvPic).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchToRx();
                    mDrawerLayout.closeDrawers();
                    mNavigationView.getMenu().getItem(1).setChecked(true);
                }
            });
        }

        // 菜单的正式部分
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_item_rx:
                        switchToRx();
                        break;
                    case R.id.nav_item_re:
                        switchToRe();
                        break;
                    case R.id.nav_item_rxre:
                        switchToRxRe();
                        break;
                    case R.id.nav_item_md:
                        switchToMd();
                        break;
                    case R.id.nav_item_about:
                        switchToAbout();
                        break;
                }
                menuItem.setChecked(true); // 把当前菜单的item置为选中状态
                mDrawerLayout.closeDrawers(); // 抽屉合起来，隐藏菜单

                // 默认返回flase，自己实现逻辑需要返回true
                return true;
            }
        });

    }

    private void switchToRx() {
        supportFragmentManager.beginTransaction().replace(R.id.mFrameLayout, new RxFragment()).commit();
        mToolbar.setTitle("RxJava");
    }

    private void switchToRe() {
        supportFragmentManager.beginTransaction().replace(R.id.mFrameLayout, new ReFragment()).commit();
        mToolbar.setTitle("Retrofit");
    }

    private void switchToRxRe() {
        supportFragmentManager.beginTransaction().replace(R.id.mFrameLayout, new RxReFragment()).commit();
        mToolbar.setTitle("RxJava Retrofit");
    }

    private void switchToMd() {
        supportFragmentManager.beginTransaction().replace(R.id.mFrameLayout, new MdFragment()).commit();
        mToolbar.setTitle("Material Design");
    }

    private void switchToAbout() {
        supportFragmentManager.beginTransaction().replace(R.id.mFrameLayout, new AboutFragment()).commit();
        mToolbar.setTitle("About");
    }

}
