package com.am.mdrr.mdsimple;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.am.mdrr.R;

public class FloatingActionButtonActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FloatingActionButton mFab;

    /**
     * TextInputLayout  有用于做错误检验的api
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floatbutton);
        other();

        mFab = (FloatingActionButton) findViewById(R.id.mFab);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"FAB",Snackbar.LENGTH_LONG)
                        .setAction("cancel", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //这里的单击事件代表点击消除Action后的响应事件
                            }
                        })
                        .show();
            }
        });
    }

    private void other() {
        toolbar = (Toolbar) findViewById(R.id.mToolbar);
        toolbar.setTitle("CardView");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolbar.findViewById(R.id.mTvTip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FloatingActionButtonActivity.this);
                // 设置标题
                builder.setTitle("提示：");
                // 设置描述信息
                builder.setMessage("可以将CardView看做是FrameLayout在自身之上添加了圆角和阴影效果。" +
                        "\nCardView被包装为一种布局，并且经常在ListView和RecyclerView的Item布局中，作为一种容器使用。"
                );
                builder.show();
            }
        });
    }
}
