package com.am.mdrr.mdsimple;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.am.mdrr.R;

/**
 * Created by AM on 2016/7/16.
 */
public class ThreeOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_one);
        ImageView imageView = (ImageView) findViewById(R.id.ivImage);
        imageView.setImageResource(R.mipmap.op);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        //设置工具栏标题
        CollapsingToolbarLayout collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("波特卡斯·D·艾斯");
    }
}