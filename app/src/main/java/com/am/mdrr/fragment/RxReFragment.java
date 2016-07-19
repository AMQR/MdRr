package com.am.mdrr.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.am.mdrr.R;
import com.am.mdrr.adapter.RecyclerViewAdapter;
import com.am.mdrr.entity.ContentlistBean;
import com.am.mdrr.entity.ShowapiResBodyBean;
import com.am.mdrr.net.HttpMethods;
import com.am.mdrr.utils.AppConstant;
import com.am.mdrr.utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.Subscriber;

/**
 * Created by AM on 2016/7/15.
 */
public class RxReFragment extends Fragment {

    private View rootView;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    boolean isLoading;
    private List<ContentlistBean> mDatas = new ArrayList<>();
    private RecyclerViewAdapter adapter;
    private Handler handler = new Handler();

    private int currPage = 0;
    private int allPage = 0;
    private Subscriber<ShowapiResBodyBean> subscribe;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_rxre,null);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        getTxtJoke(0,true);
        //adapter = new RecyclerViewAdapter(getActivity(), mDatas);
        initView();

        return rootView;
    }

    public void initView() {
        swipeRefreshLayout.setColorSchemeResources(
                R.color.google_blue,
                R.color.google_green,
                R.color.google_red,
                R.color.google_yellow
        );
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() { // 下拉加载新数据
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("=========总页码："+allPage);
                        if (allPage == 0) {
                            currPage = 0;
                        }else{
                            currPage = new Random().nextInt(allPage);
                            System.out.println("=========随机页码："+currPage);
                        }


                        System.out.println("=========下拉刷新的页码："+currPage);
                        getTxtJoke(currPage,true);
                        adapter.notifyDataSetChanged();


                    }
                }, 2000);
            }
        });
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("test", "StateChanged = " + newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("test", "onScrolled");

                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
                    Log.d("test", "loading executed");

                    boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        adapter.notifyItemRemoved(adapter.getItemCount());
                        return;
                    }
                    if (!isLoading) {
                        isLoading = true;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (allPage == currPage) {
                                    Toast.makeText(getActivity(), "数据展示完毕", Toast.LENGTH_SHORT).show();
                                } else {
                                    currPage = currPage + 1;
                                    getTxtJoke(currPage,false);
                                    Toast.makeText(getActivity(), "正在加载"+currPage+"页面数据", Toast.LENGTH_SHORT).show();
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }, 1000);
                    }
                }
            }
        });

        //添加点击事件
        /*adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("test", "item position = " + position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });*/
    }

    private void getTxtJoke(final int currPageNum, final boolean isNeedClean) {

        subscribe = new Subscriber<ShowapiResBodyBean>() {
            //onStart() 可以用作流程开始前的初始化  在 subscribe() 被调用时的线程
            @Override
            public void onStart() {
                super.onStart();
                //loadHelpView.showLoading("加载中");
                swipeRefreshLayout.setRefreshing(true);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("===============error " + e.toString());
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onNext(ShowapiResBodyBean showapiResBodyBean) {
                System.out.println("======= subscribe onNext " + showapiResBodyBean.getCurrentPage());
                if (isNeedClean) {
                    mDatas.clear();
                    mDatas = showapiResBodyBean.getContentlist();
                    allPage = showapiResBodyBean.getAllPages();
                    adapter = new RecyclerViewAdapter(getActivity(),mDatas);
                    recyclerView.setAdapter(adapter);

                } else {
                    System.out.println("========== dataList.size先" + mDatas.size());
                    mDatas.addAll(showapiResBodyBean.getContentlist());
                    System.out.println("========== dataList.size后" + mDatas.size());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCompleted() {
                System.out.println("=======subscribe onCompleted ");
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(),"刷新完成",Toast.LENGTH_SHORT).show();
            }
        };

        HttpMethods.getInstance().getJokeText(subscribe, 20, currPageNum, AppConstant.APP_ID,
                AppConstant.getTimeTamp(), AppConstant.API_SIGN);
    }

}
