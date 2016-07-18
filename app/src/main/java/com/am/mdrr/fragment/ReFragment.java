package com.am.mdrr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.am.mdrr.R;
import com.am.mdrr.RetrofitSimpleActivity;
import com.am.mdrr.WebViewActivity;

/**
 * Created by AM on 2016/7/15.
 */
public class ReFragment extends Fragment {
    private TextView mTvRx;

    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_re,null);
        mTvRx = (TextView) rootView.findViewById(R.id.mTvRx);
        mTvRx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RetrofitSimpleActivity.class));
            }
        });
        return rootView;
    }
}
