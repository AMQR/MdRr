/*
Copyright 2015 shizhefei（LuckyJayce）
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
   http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.amqr.loadhelplib;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.amqr.loadhelplib.ReplaceInterface.IReplaceViewHelper;
import com.amqr.loadhelplib.ReplaceInterface.ReplaceViewHelper;


/**
 * 自定义要切换的布局，通过IVaryViewHelper实现真正的切换<br>
 * 使用者可以根据自己的需求，使用自己定义的布局样式
 */
public class LoadHelpView {

	private IReplaceViewHelper helper;
	private AnimationDrawable animationDrawable;

	public LoadHelpView(View view) {
		this(new ReplaceViewHelper(view));
	}

	public LoadHelpView(IReplaceViewHelper helper) {
		super();
		this.helper = helper;
	}

	// 数据异常
	public void showError(String errorText, String buttonText, int picResId,OnClickListener onClickListener) {
		View layout = helper.inflate(R.layout.load_error);
		// 设置图片
		ImageView mIvShowPic = (ImageView) layout.findViewById(R.id.mIvShowPic);
		mIvShowPic.setBackgroundResource(picResId);
		// 设置提示文字
		TextView mTvTip = (TextView) layout.findViewById(R.id.mTvTip);
		mTvTip.setText(errorText);

		// 设置按钮
		TextView mTvBtn = (TextView) layout.findViewById(R.id.mTvBtn);
		mTvBtn.setText(buttonText);
		mTvBtn.setOnClickListener(onClickListener);

		helper.showLayout(layout);
	}

	// 数据异常简易版
	public void showError(OnClickListener onClickListener) {
		View layout = helper.inflate(R.layout.load_error);
		TextView mTvBtn = (TextView) layout.findViewById(R.id.mTvBtn);
		mTvBtn.setOnClickListener(onClickListener);
		helper.showLayout(layout);
	}


	// 空数据
	public void showEmpty(String errorText, String buttonText, int picResId,OnClickListener onClickListener) {
		View layout = helper.inflate(R.layout.load_empty);

		ImageView mIvShowPic = (ImageView) layout.findViewById(R.id.mIvShowPic);
		mIvShowPic.setBackgroundResource(picResId);

		TextView mTvTip = (TextView) layout.findViewById(R.id.mTvTip);
		mTvTip.setText(errorText);

		TextView mTvBtn = (TextView) layout.findViewById(R.id.mTvBtn);
		mTvBtn.setText(buttonText);
		mTvBtn.setOnClickListener(onClickListener);

		helper.showLayout(layout);
	}

	// 空数据简易版
	public void showEmpty(OnClickListener onClickListener) {
		View layout = helper.inflate(R.layout.load_empty);
		TextView mTvBtn = (TextView) layout.findViewById(R.id.mTvBtn);
		mTvBtn.setOnClickListener(onClickListener);
		helper.showLayout(layout);
	}

	// 正在加载
	public void showLoading(String loadText) {
		View layout = helper.inflate(R.layout.load_ing);
		TextView mTvTip = (TextView) layout.findViewById(R.id.mTvTip);
		ImageView mIvAnim = (ImageView) layout.findViewById(R.id.mIvAnim);
		mIvAnim.setImageResource(R.drawable.loading_animation);
		animationDrawable = (AnimationDrawable) mIvAnim.getDrawable();
		animationDrawable.start(); // 开启帧动画
		mTvTip.setText(loadText);
		helper.showLayout(layout);
	}

	public void dismiss() {
		helper.dismissView();
	}
}
