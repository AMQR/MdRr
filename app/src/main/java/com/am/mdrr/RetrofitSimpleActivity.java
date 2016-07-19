package com.am.mdrr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.am.mdrr.entity.WeatherBean;
import com.am.mdrr.net.WeatherApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AM on 2016/7/18.
 */
public class RetrofitSimpleActivity extends AppCompatActivity{

    private TextView mTvGet;
    private TextView mTvData;
    private Toolbar mToolbar;
    private TextView mTvTip;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ret_simple);
        mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        mToolbar.setTitle("Retrofit Simple");
        setSupportActionBar(mToolbar);

        mTvTip = (TextView) findViewById(R.id.mTvTip);
        mTvTip.setText("OP 历史正文");
        mTvTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openWeb = new Intent(RetrofitSimpleActivity.this,WebViewActivity.class);
                openWeb.putExtra("Title","Retrofit");
                openWeb.putExtra("LoadUrl","http://www.jianshu.com/p/c1b2af70f24b");
                startActivity(openWeb);
            }
        });

        mTvGet = (TextView) findViewById(R.id.mTvGet);
        mTvData = (TextView) findViewById(R.id.mTvData);

        mTvGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

    }

    private void getData() {
        String baseUrl = "http://apistore.baidu.com/";
        // 创建一个  retrofit ，baseUrl必须有
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()) // 用到了 com.squareup.retrofit2:adapter-rxjava:2.1.0'
                .build();
        // 利用 retrofit 创建一个接口
        WeatherApi apiService = retrofit.create(WeatherApi.class);

        // 利用接口创建一个Call对象
        Call<WeatherBean> weatherBeanCall = apiService.getWeather("beijing");
        // 请求入队，回调请求成功或者失败
        weatherBeanCall.enqueue(new Callback<WeatherBean>() {
            @Override
            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                System.out.println("====请求成功："+response.body().retData.weather);
                mTvData.setText(response.body().retData.weather);
            }
            @Override
            public void onFailure(Call<WeatherBean> call, Throwable t) {
                System.out.println("====请求失败");
                mTvData.setText(t.getMessage());
            }
        });
    }
}
