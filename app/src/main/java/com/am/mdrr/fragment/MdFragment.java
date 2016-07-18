package com.am.mdrr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.am.mdrr.R;
import com.am.mdrr.adapter.RecycAdapter;
import com.am.mdrr.entity.WidgetBean;
import com.am.mdrr.mdsimple.CardViewActivity;
import com.am.mdrr.mdsimple.FloatingActionButtonActivity;
import com.am.mdrr.mdsimple.SnackbarActivity;
import com.am.mdrr.mdsimple.TabLayoutActivity;
import com.am.mdrr.mdsimple.TextInputActivity;
import com.am.mdrr.mdsimple.ThreeOneActivity;
import com.am.mdrr.mdsimple.ToolbarActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by AM on 2016/7/15.
 */
public class MdFragment extends Fragment {

    private View rootView;
    private RecyclerView mRecycView;
    private List<WidgetBean> mDatas;
    private RecycAdapter mRecycAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_md,null);
        initData();
        mRecycAdapter = new RecycAdapter(getActivity(),mDatas);
        mRecycView = (RecyclerView) rootView.findViewById(R.id.mRecycView);
        mRecycView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecycAdapter.setOnItemClickLitener(new RecycAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        startActivity(new Intent(getActivity(), SnackbarActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), CardViewActivity.class));
                        break;
                    case 2:
                        Toast.makeText(getActivity(),"当前便是如此",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), TextInputActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), FloatingActionButtonActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(getActivity(), ToolbarActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(getActivity(), ThreeOneActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(getActivity(), TabLayoutActivity.class));
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
        addListData("Snackbar");
        addListData("CardView");
        addListData("RecyclerView");
        addListData("TextInputLayout");
        addListData("FloatingActionButton");
        addListData("ToolBar");
        addListData("CoordinatorLayout"
        +"\nCollapsingToolbarLayout"+"\nNestedScrollView");
        addListData("TabLayout");
    }

    private void addListData(String name){
        if(mDatas == null){
            mDatas = new ArrayList<>();
        }
        WidgetBean widgetBean = new WidgetBean();
        widgetBean.mTvItemName = name;
        widgetBean.mIvPic = R.mipmap.ic_launcher;
        mDatas.add(widgetBean);
    }
}
