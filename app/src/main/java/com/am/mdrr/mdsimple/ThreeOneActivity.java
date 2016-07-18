package com.am.mdrr.mdsimple;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.am.mdrr.R;

/**
 * Created by AM on 2016/7/16.
 */
public class ThreeOneActivity extends AppCompatActivity {

    private TextView mTvTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_one);
        ImageView imageView = (ImageView) findViewById(R.id.ivImage);
        imageView.setImageResource(R.mipmap.op);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTip();

        //设置工具栏标题
        CollapsingToolbarLayout collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("波特卡斯·D·艾斯");
    }

    private void setTip() {
        mTvTip = (TextView) findViewById(R.id.mTvTip);
        mTvTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ThreeOneActivity.this);
                // 设置标题
                builder.setTitle("提示：");
                // 设置描述信息
                builder.setMessage("CoordinatorLayout 是一个加强版的FrameLayout。（继承自ViewGroup）\n" +
                        "\n" +
                        "主要有两个用途：\n" +
                        "1、作为顶层应用的装饰或者chrome布局\n" +
                        "2、作为一个容器来协调一个或多个子views"+
                        "\n================="+
                        "\nCollapsingToolbarLayout作用是提供了一个可以折叠的Toolbar" +
                        "（CollapsingToolbarLayout是一个实现了折叠工具栏效果Toolbar的包装器，它被设计为AppBarLayout的直接子View。）"+
                        "\n================="+
                        "\nNestedScrollView 简单而言可以理解为Md设计里面的ScrollView"
                );
                builder.show();
            }
        });

    }
}