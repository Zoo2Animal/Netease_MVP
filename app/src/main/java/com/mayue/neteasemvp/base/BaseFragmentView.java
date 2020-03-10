package com.mayue.neteasemvp.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public abstract class BaseFragmentView<A extends BaseFragmentActivity,
		P extends BaseFragmentPresenter, CONTRACT> extends Fragment {

	private A hostActivity;

	private P presenter;

	private IFragmentCallbacks callbacks;

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		hostActivity = (A) getActivity();
		callbacks = Objects.requireNonNull(hostActivity).impCallbacks();
		presenter = setPresenter();
		presenter.bindView(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (callbacks != null) {
			callbacks = null;
		}
		presenter.unbindView();
	}

	protected abstract CONTRACT getContract();
	protected abstract P setPresenter();
}
