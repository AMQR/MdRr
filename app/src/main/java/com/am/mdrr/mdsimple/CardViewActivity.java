package com.am.mdrr.mdsimple;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.am.mdrr.R;

public class CardViewActivity extends AppCompatActivity {


    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);
        other();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(CardViewActivity.this);
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
