package com.mayue.neteasemvp.base;

import java.lang.ref.WeakReference;

public abstract class BaseFragmentPresenter<V extends BaseFragmentView,M extends BaseModel,CONTRACT> {

	private WeakReference<V> vWeakReference;

	private M model;

	public BaseFragmentPresenter() {
		model = setModel();
	}

	public void bindView(V v) {
		vWeakReference = new WeakReference<>(v);
	}


	public void unbindView() {
		if (vWeakReference != null) {
			vWeakReference.clear();
			vWeakReference = null;
			System.gc();
		}
	}


	protected abstract M setModel();

	protected abstract CONTRACT getContract();

	public M getModel(){
		return model;
	}
}
