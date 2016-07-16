package com.am.mdrr.mdsimple;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.am.mdrr.R;

public class SnackbarActivity extends AppCompatActivity {

    private TextView mTv;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);
        other();

        mTv = (TextView) findViewById(R.id.mTv);
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(mTv, "小时不识月", Snackbar.LENGTH_SHORT)
                        .setAction("下句", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(SnackbarActivity.this, "呼作白玉盘", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }).show();
            }
        });
    }

    private void other() {
        toolbar = (Toolbar) findViewById(R.id.mToolbar);
        toolbar.setTitle("Snackbar");
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
                AlertDialog.Builder builder = new AlertDialog.Builder(SnackbarActivity.this);
                // 设置标题
                builder.setTitle("提示：");
                // 设置描述信息
                builder.setMessage("Material Design之前，提示都是由Toast来完成的。" +
                        "\nToast的缺点在于不能提供交互，而Material Design包中的SnackBar很好地解决了这个问题。"

                );
                builder.show();
            }
        });
    }
}
