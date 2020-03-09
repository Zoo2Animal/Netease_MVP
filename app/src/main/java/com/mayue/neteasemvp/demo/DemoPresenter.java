package com.mayue.neteasemvp.demo;


import com.mayue.neteasemvp.base.BasePresenter;
import com.mayue.neteasemvp.bean.DemoBean;

/**
 *  created by matthew,2020-03-09
 *  P 层对象具体实现，持有 V 层对象 DemoActivity, M 层对象 DemoModel, 合同接口 DemoContract.DemoPresenter
 *  具体的业务描述可以参考 DemoContract.DemoPresenter 接口注释
 *  可以看到，有了 合同接口 DemoContract.DemoPresenter ， P 层的实现类非常干净，实现对应的方法即可。
 */
public class DemoPresenter extends BasePresenter<DemoActivity, DemoModel, DemoContract.DemoPresenter> {

	@Override
	protected DemoModel setModel() {
		// 创建对应的 M 对象
		return new DemoModel(this);
	}

	@Override
	protected DemoContract.DemoPresenter getContract() {
		// 实现 合同接口
		return new DemoContract.DemoPresenter<DemoBean>() {
			@Override
			public void getData(String s) {
				// 这里可以认为是 P 层的业务逻辑，判断需要执行 M 层的哪一种获取数据的方式
				switch (s) {
					case "Hello World!":
						// 注意，是从所持有的 M 层对象获取其合同接口里规定的方法
						getModel().getContract().requestNetData();
						break;
					case "Hello Net!":
						// 注意，是从所持有的 M 层对象获取其合同接口里规定的方法
						getModel().getContract().requestDiskData();
						break;
					case "Hello Disk!":
						onRequest("directly update to 'Hello World!'");
						onRequestResult(new DemoBean(true, "Hello World!"));
						break;
				}
			}

			@SuppressWarnings("unchecked")
			@Override
			public void onRequestResult(final DemoBean demoBean) {
				// 这个判空很重要，有可能出现，数据返回时，V 层并不在交互中，并且被回收了
				if (getView() != null) {
					getView().getHandler().post(new Runnable() {
						@Override
						public void run() {
							// 注意，是从所持有的 V 层对象获取其合同接口里规定的方法
							getView().getContract().updateOnResult(demoBean);

						}
					});
				} else {
					cacheData(demoBean);
					// TODO: 2020/3/9 将数据缓存...
				}
			}


			@Override
			public void onRequest(final String message) {
				// 这个判空很重要，有可能出现，数据返回时，V 层并不在交互中，并且被回收了
				if (getView() != null) {
					getView().getHandler().post(new Runnable() {
						@Override
						public void run() {
							// 注意，是从所持有的 V 层对象获取其合同接口里规定的方法
							getView().getContract().toastNotify(message);
						}
					});
				}
			}
		};
	}

	private void cacheData(DemoBean demoBean) {
		// TODO: 2020/3/9 缓存数据就不写了
	}
}
