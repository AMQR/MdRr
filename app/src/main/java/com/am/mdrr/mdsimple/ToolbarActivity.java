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
import android.widget.TextView;
import android.widget.Toast;

import com.am.mdrr.MainActivity;
import com.am.mdrr.R;

public class ToolbarActivity extends AppCompatActivity {
    private Toolbar mToolBar;
    private TextView mTvTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        mToolBar = (Toolbar) findViewById(R.id.mToolBar);
        mToolBar.setTitle("Title");
        setSupportActionBar(mToolBar);
        setTip();
        mToolBar.setNavigationIcon(R.mipmap.l1);
        mToolBar.setLogo(R.mipmap.l2);
        mToolBar.setSubtitle("SubTit");
        // 设置菜单点击监听
        mToolBar.setOnMenuItemClickListener(onMenuItemClick);
    }

    private void setTip() {
        mTvTip = (TextView) findViewById(R.id.mTvTip);
        mTvTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ToolbarActivity.this);
                // 设置标题
                builder.setTitle("提示：");
                // 设置描述信息
                builder.setMessage("Toolbar 是一个应用程序使用中的标准工具栏，Toolbar可以被嵌套在任意一个层级的View中。\n" +
                        "* 相对ActionBar而言，Toolbar提供了更多牛逼的特性。ToolBar是api21 新增的组件，其主要用来弥补actionbar带来的不足，其继承自viewgroup，自由的属性包括：\n" +
                        "* 导航按钮（类似向上按钮） （A navigation button）\n" +
                        "* logo图片 （A branded logo image.）\n" +
                        "* 标题和子标题 （A title and subtitle）\n" +
                        "* 一个或者多个自定义view （One or more custom views.）\n" +
                        "* 菜单。 （An action menu.）"
                );
                builder.show();
            }
        });
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