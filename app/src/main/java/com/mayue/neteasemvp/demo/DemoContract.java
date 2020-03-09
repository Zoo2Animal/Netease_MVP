package com.mayue.neteasemvp.demo;


import com.mayue.neteasemvp.bean.BaseEntity;

/**
 * created by matthew,2020-03-08
 * demo 包的契约接口，规定了 m v p 各层的具体业务
 * 主要业务描述：
 * 点击当前页面的 TextView ，如果当前显示的是 “Hello world!”,则从网络获取新的字符串数据，并通知！
 * 如果当前显示的是 “Hello Net!”,则从本地获取新的字符串数据，并通知！
 * 如果当前显示的是 “Hello Disk”,则直接修改为 “Hello world!”，并通知！
 * 这里为了模拟项目中使用 bean 对象而做了泛型 T，其实只是传递一个 String
 */
interface DemoContract {

	interface DemoModel {
		// 模拟从网络获取数据
		void requestNetData();

		// 模拟从本地获取数据
		void requestDiskData();

		// 当访问网络数据时的事件
		void onRequestNet();

		// 当访问本地时的事件
		void onRequestDisk();
	}

	interface DemoPresenter<T extends BaseEntity> {
		// 获取数据的具体业务
		void getData(String s);

		// 获取数据请求的结果
		void onRequestResult(T t);

		// 当开始请求数据时的事件，一般是 V 层展示通知
		void onRequest(String message);
	}

	interface DemoView<T extends BaseEntity> {

		// View 层当有事件发生时的通知展示
		void toastNotify(String message);

		// View 层当请求获得结果时更新 view
		void updateOnResult(T t);
	}
}
