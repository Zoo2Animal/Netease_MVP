package com.mayue.neteasemvp.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public abstract class BaseFragmentActivity<I extends IFragmentCallbacks> extends AppCompatActivity {

	//<editor-fold desc="fields">
	private FragmentManager fm;
	//</editor-fold>


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResId());
		fm = getSupportFragmentManager();
	}



	//<editor-fold desc="sub to do">
	@LayoutRes
	protected abstract int getLayoutResId();

	protected abstract I impCallbacks();
	//</editor-fold>


	//<editor-fold desc="getter">
	public FragmentManager getFm() {
		return fm;
	}
	//</editor-fold>




}
