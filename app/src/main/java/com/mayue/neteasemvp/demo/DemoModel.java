package com.mayue.neteasemvp.demo;


import android.os.SystemClock;

import com.mayue.neteasemvp.base.BaseModel;
import com.mayue.neteasemvp.bean.DemoBean;

/**
 * created by matthew,2020-03-09
 * M 层对象具体实现，持有 P 层对象 DemoPresenter,合同接口 DemoContract.DemoModel
 * 持有的 P 层对象是构造器传入的，即在 P 层决定了要哪一个 Model 对象，体现了 P 层业务控制者的角色
 */
class DemoModel extends BaseModel<DemoPresenter, DemoContract.DemoModel> {


	DemoModel(DemoPresenter presenter) {
		super(presenter);

	}

	@SuppressWarnings("unchecked")
	@Override
	protected DemoContract.DemoModel getContract() {
		return new DemoContract.DemoModel() {
			@Override
			public void requestNetData() {
				// 开始请求时的事件通知 p 层
				onRequestNet();
				// 模拟异步耗时操作
				new Thread(new Runnable() {
					@Override
					public void run() {
						SystemClock.sleep(5000);
						// 返回结果，注意是从持有的 P 层对象获取其合同接口，调用对应的方法
						getPresenter().getContract().onRequestResult(new DemoBean(true, "Hello Net!"));
					}
				}).start();
			}

			@Override
			public void requestDiskData() {
				// 开始请求时的事件通知 p 层
				onRequestDisk();
				// 模拟异步耗时操作
				new Thread(new Runnable() {
					@Override
					public void run() {
						SystemClock.sleep(2000);
						// 返回结果，注意是从持有的 P 层对象获取其合同接口，调用对应的方法
						getPresenter().getContract().onRequestResult(new DemoBean(true, "Hello Disk!"));
					}
				}).start();
			}

			@Override
			public void onRequestNet() {
				// 从持有的 P 层对象获取其合同接口，调用对应的方法
				getPresenter().getContract().onRequest("getting data from net!");
			}

			@Override
			public void onRequestDisk() {
				// 从持有的 P 层对象获取其合同接口，调用对应的方法
				getPresenter().getContract().onRequest("getting data from disk!");
			}
		};
	}
}
