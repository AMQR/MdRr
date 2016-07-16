package com.am.mdrr.mdsimple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.am.mdrr.MainActivity;
import com.am.mdrr.R;

public class ToolbarActivity extends AppCompatActivity {
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        mToolBar = (Toolbar) findViewById(R.id.mToolBar);
        mToolBar.setTitle("Title");
        setSupportActionBar(mToolBar);

        mToolBar.setNavigationIcon(R.mipmap.l1);
        mToolBar.setLogo(R.mipmap.l2);
        mToolBar.setSubtitle("SubTit");
        // 设置菜单点击监听
        mToolBar.setOnMenuItemClickListener(onMenuItemClick);
    }

    // 菜单的点击回调
    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_ball:
                    msg += "Click ball";
                    break;
                case R.id.action_tip:
                    msg += "Click action_tip";
                    break;
                case R.id.action_menu:
                    msg += "Click setting";
                    break;
            }
            if(!msg.equals("")) {
                Toast.makeText(ToolbarActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };


    // 创建关联菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_simple,menu);
        return true;
    }
}