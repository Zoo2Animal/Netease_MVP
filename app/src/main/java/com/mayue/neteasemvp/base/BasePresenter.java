package com.mayue.neteasemvp.base;

import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * created by Matthew, 2020-03-08
 * <BasePresenter>
 * P 层基类，会持有 V 层和 M 层的引用。
 * 其主要职责是解耦 M 层和 V 层，使 View 层（Activity，Fragment）不再臃肿，同时分离 M 层，抽取业务逻辑，方便管理与测试。
 * 但由于持有了 V 层对象，在执行异步耗时操作时，会有内存泄漏风险，一般处理方式是弱引用结合 V 层的生命周期回调进行反注册；
 * 核心： 用弱引用封装 V 层，提供绑定 <bindView(V view)></> 与解绑 <UnbindView()></> 方法在 V 层生命周期回调中使用；
 * 个人理解：
 * 谷歌的 todomvp sample 中，P 层并没有持有 V 层，但是应用在项目中，双向持有往往是无法避免的。主要是因为由非客户端（APP）
 * 产生的事件驱动画面变更，比如服务器主动下发了某个指令，或者异步延迟请求获取了结果，需要在画面上弹框提示。
 * 这里也可以看出 mvvm 架构的进步，观察者模式解耦的能力的一种体现。
 * </BasePresenter>
 *
 * @param <V>        V 层对象
 * @param <M>        M 层对象
 * @param <CONTRACT> 契约接口，规定子 Presenter 对象具体的业务逻辑
 */
public abstract class BasePresenter<V extends BaseView, M extends BaseModel, CONTRACT> {

	//<editor-fold desc="fields">
	/**
	 * 对 V 层对象的弱引用,使用弱引用是为了在 万一在 activity
	 */
	private WeakReference<V> vWeakReference;

	/**
	 * 对 M 层对象的引用，注意，p 层 和 M 层是应该互相持有的，而不应该使用弱引用；
	 * 比如这样一个场景：V 层请求了下载一张图片，P 层和 M 层去执行各自的逻辑，此时 V 层退出栈顶了，
	 * 但是下载的任务应该继续执行直到完成，只是不将用将结果展示到 V 层而已。
	 */
	private M model;
	//</editor-fold>


	//<editor-fold desc="constructor">

	/**
	 * 需要初始化 M 层对象，但具体的是哪个对象由子类决定创建
	 */
	public BasePresenter() {
		model = setModel();
	}
	//</editor-fold>


	//<editor-fold desc="lifecycle callback">

	/**
	 * 给 V 层生命周期回调时提供的方法，一般在 onCreated（）回调中执行绑定，即初始化弱引用 V 层对象。
	 *
	 * @param view V 层对象
	 */
	void bindView(V view) {
		vWeakReference = new WeakReference<>(view);
	}

	/**
	 * 给 V 层生命周期回调时提供的方法，一般在 onDestroyed（）回调中释放对 V 层对象的引用。
	 */
	void UnbindView() {
		if (vWeakReference != null) {
			vWeakReference.clear();
			vWeakReference = null;
			System.gc();
		}
	}
	//</editor-fold>

	//<editor-fold desc="sub to do">

	/**
	 * 由子类决定自己的 M 层对象
	 *
	 * @return M 模型层对象
	 */
	protected abstract M setModel();

	/**
	 * 由子类决定自己的契约接口
	 *
	 * @return CONTRACT 契约接口
	 */
	protected abstract CONTRACT getContract();
	//</editor-fold>


	//<editor-fold desc="getter">
	public V getView() {
		if (vWeakReference != null) {
			return vWeakReference.get();
		} else {
			Log.e("Presenter", "getView: is null!");
		}
		return null;
	}

	public M getModel() {
		return model;
	}
	//</editor-fold>
}
