package com.am.mdrr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mittsu.markedview.MarkedView;

import us.feras.mdv.MarkdownView;


/**
 * Created by AM on 2016/7/18.
 */
public class MarkDownActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private TextView mMdv;
    private Intent intent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markdown);
        intent = getIntent();

        toolbar = (Toolbar) findViewById(R.id.mToolbar);
        toolbar.setTitle(intent.getStringExtra("RxTitle"));
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        findViewById(R.id.mTvTip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.jianshu.com/p/44d789d8c09c");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });


        mMdv = (TextView) findViewById(R.id.mMdv);
        mMdv.setText(intent.getStringExtra("MdTxt"));
    }
}
