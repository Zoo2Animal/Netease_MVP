package com.mayue.neteasemvp.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseView<P extends BasePresenter,CONTRACT> extends AppCompatActivity {

	private P presenter;

	//<editor-fold desc="lifecycle">
	@SuppressWarnings("unchecked")
	// Unchecked call to bindView(V) as a member of raw type 'com.mayue.module.neteasemvp.base.BasePresenter'
	// 在 V 层创建的时候，需要 P 层绑定
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		presenter = setPresenter();
		presenter.bindView(this);
	}

	// 在 V 层销毁的时候，需要 P 层释放对 V 层对象的持有
	@Override
	protected void onDestroy() {
		super.onDestroy();
		presenter.UnbindView();

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


	// p.getter
	public P getPresenter() {
		return presenter;
	}
}
