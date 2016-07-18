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
        toolbar.setTitle("FloatingActionButton");
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
                builder.setMessage("FloatingActionButton是继承至ImageView，所以FloatingActionButton拥有ImageView的所有属性。" +
                        "\nCoordinatorLayout可以用来配合FloatingActionButton浮动按钮，设置app：layout_anchor和app:layout_anchorGravity构建出特定的位置与效果的FloatingActionButton。" +
                        "* app:backgroundTint - 设置FAB的背景颜色。\n" +
                        "* app:rippleColor - 设置FAB点击时的背景颜色。\n" +
                        "* app:borderWidth - 该属性尤为重要，如果不设置0dp，那么在4.1的sdk上FAB会显示为正方形，而且在5.0以后的sdk没有阴影效果。所以设置为borderWidth=\"0dp\"。\n" +
                        "* app:elevation - 默认状态下FAB的阴影大小。\n" +
                        "* app:pressedTranslationZ - 点击时候FAB的阴影大小。\n" +
                        "* app:fabSize - 设置FAB的大小，该属性有两个值，分别为normal和mini，对应的FAB大小分别为56dp和40dp。\n" +
                        "* src - 设置FAB的图标，Google建议符合Design设计的该图标大小为24dp。\n" +
                        "* app:layout_anchor - 设置FAB的锚点，即以哪个控件为参照点设置位置。\n" +
                        "* app:layout_anchorGravity - 设置FAB相对锚点的位置，值有 bottom、center、right、left、top等。"
                );
                builder.show();
            }
        });
    }
}
