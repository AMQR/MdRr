package com.am.mdrr.fragment;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.am.mdrr.MarkDownActivity;
import com.am.mdrr.R;
import com.am.mdrr.WebViewActivity;
import com.am.mdrr.adapter.RecycAdapter;
import com.am.mdrr.adapter.RecycRxJavaAdapter;
import com.am.mdrr.entity.WidgetBean;
import com.am.mdrr.mdsimple.CardViewActivity;
import com.am.mdrr.mdsimple.FloatingActionButtonActivity;
import com.am.mdrr.mdsimple.SnackbarActivity;
import com.am.mdrr.mdsimple.TabLayoutActivity;
import com.am.mdrr.mdsimple.TextInputActivity;
import com.am.mdrr.mdsimple.ThreeOneActivity;
import com.am.mdrr.mdsimple.ToolbarActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by AM on 2016/7/15.
 */
public class RxFragment extends Fragment {
    private View rootView;
    private RecyclerView mRecycView;
    private List<String> mDatas;
    private RecycRxJavaAdapter mRecycAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_rx,null);

        initData();
        mRecycAdapter = new RecycRxJavaAdapter(getActivity(),mDatas);
        mRecycView = (RecyclerView) rootView.findViewById(R.id.mRecycView);
        mRecycView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecycAdapter.setOnItemClickLitener(new RecycRxJavaAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        Intent openWeb = new Intent(getActivity(),WebViewActivity.class);
                        openWeb.putExtra("Title","RxJava初认识");
                        openWeb.putExtra("LoadUrl","http://www.jianshu.com/p/44d789d8c09c");
                        startActivity(openWeb);
                        break;
                    case 1:
                        Intent openMark1 = new Intent(getActivity(),MarkDownActivity.class);
                        openMark1.putExtra("RxTitle","Observer + Observable");
                        openMark1.putExtra("MdTxt",getAsset("rx1.txt"));
                        startActivity(openMark1);
                        break;

                    case 2:
                        Intent openMark2 = new Intent(getActivity(),MarkDownActivity.class);
                        openMark2.putExtra("RxTitle","Subscriber + Observable");
                        openMark2.putExtra("MdTxt",getAsset("rx2.txt"));
                        startActivity(openMark2);
                        break;
                    case 3:
                        Intent openMark3 = new Intent(getActivity(),MarkDownActivity.class);
                        openMark3.putExtra("RxTitle","快捷创建事件，just");
                        openMark3.putExtra("MdTxt",getAsset("rx3.txt"));
                        startActivity(openMark3);
                        break;
                    case 4:
                        Intent openMark4 = new Intent(getActivity(),MarkDownActivity.class);
                        openMark4.putExtra("RxTitle","快捷创建事件，from");
                        openMark4.putExtra("MdTxt",getAsset("rx4.txt"));
                        startActivity(openMark4);
                        break;
                    case 5:
                        Intent openMark5 = new Intent(getActivity(),MarkDownActivity.class);
                        openMark5.putExtra("RxTitle","不完整回调ActionX");
                        openMark5.putExtra("MdTxt",getAsset("rx5.txt"));
                        startActivity(openMark5);
                        break;
                    case 6:
                        Intent openMark6 = new Intent(getActivity(),MarkDownActivity.class);
                        openMark6.putExtra("RxTitle","Scheduler 调度器");
                        openMark6.putExtra("MdTxt",getAsset("rx6.txt"));
                        startActivity(openMark6);
                        break;
                    case 7:
                        Intent openMark7 = new Intent(getActivity(),MarkDownActivity.class);
                        openMark7.putExtra("RxTitle","Scheduler 切换线程");
                        openMark7.putExtra("MdTxt",getAsset("rx7.txt"));
                        startActivity(openMark7);
                        break;
                    case 8:
                        Intent openMark8 = new Intent(getActivity(),MarkDownActivity.class);
                        openMark8.putExtra("RxTitle","变换 入门");
                        openMark8.putExtra("MdTxt",getAsset("rx8.txt"));
                        startActivity(openMark8);
                        break;
                    case 9:
                        Intent openMark9 = new Intent(getActivity(),MarkDownActivity.class);
                        openMark9.putExtra("RxTitle","变换 flatMap");
                        openMark9.putExtra("MdTxt",getAsset("rx9.txt"));
                        startActivity(openMark9);
                        break;

                }
                //Toast.makeText(getActivity(),mDatas.get(position).mTvItemName,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        mRecycView.setAdapter(mRecycAdapter);
        return rootView;
    }

    private void initData() {
        mDatas = new ArrayList<>();
        addListData("RxJava初认识");
        addListData("Observer + Observable");
        addListData("Subscriber + Observable");
        addListData("快捷创建事件，just");
        addListData("快捷创建事件，from");
        addListData("不完整回调ActionX");
        addListData("Scheduler 调度器");
        addListData("Scheduler 切换线程");
        addListData("变换 入门");
        addListData("变换 flatMap");
    }

    private void addListData(String name){
        if(mDatas == null){
            mDatas = new ArrayList<String>();
        }
        mDatas.add(name);

    }

    private String getAsset(String fileName) {
        AssetManager am = getResources().getAssets();
        InputStream is = null;
        try {
            is = am.open(fileName, AssetManager.ACCESS_BUFFER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Scanner(is).useDelimiter("\\Z").next();
    }

}
