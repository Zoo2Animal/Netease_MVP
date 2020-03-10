package com.mayue.neteasemvp.base;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivityView<P extends BaseActivityPresenter,CONTRACT> extends AppCompatActivity {

	//<editor-fold desc="fields">
	private P presenter;

	private Handler handler = new Handler(getMainLooper());
	//</editor-fold>

	//<editor-fold desc="lifecycle">
	@SuppressWarnings("unchecked")
	// Unchecked call to bindView(V) as a member of raw type 'com.mayue.module.neteasemvp.base.BasePresenter'
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		presenter = setPresenter();
		// 在 V 层创建的时候，需要 P 绑定
		presenter.bindView(this);
	}

	// 在 V 层销毁的时候，需要 P 层释放对 V 层对象的持有
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 解绑
		presenter.UnbindView();
		// handler 释放
		if (handler != null) {
			handler.removeCallbacksAndMessages(null);
			handler = null;
		}
	}
	//</editor-fold>



	//<editor-fold desc="sub to do">
	/**
	 * 由子类决定自己的 P 层对象
	 * @return presenter 对象
	 */
	protected abstract P setPresenter();

	/**
	 * 由子类决定自己的契约接口
	 * @return CONTRACT 契约接口
	 */
	protected abstract CONTRACT getContract();
	//</editor-fold>


	//<editor-fold desc="getter">
	public P getPresenter() {
		return presenter;
	}

	public Handler getHandler() {
		return handler;
	}
	//</editor-fold>
}
