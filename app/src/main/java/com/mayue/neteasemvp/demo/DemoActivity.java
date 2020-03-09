package com.mayue.neteasemvp.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.mayue.neteasemvp.R;
import com.mayue.neteasemvp.base.BaseView;
import com.mayue.neteasemvp.bean.DemoBean;

/**
 * created by matthew,2020-03-09
 * V 层具体的实现，这里就是 Activity，也可以是 Fragment，后续版本添加 Fragment 的实现
 */
public class DemoActivity extends BaseView<DemoPresenter,DemoContract.DemoView> {

	private TextView textView;
	private Handler handler = new Handler(Looper.getMainLooper());

	//<editor-fold desc="lifecycle">
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = findViewById(R.id.text);
		textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 点击事件驱动 P 层去获取数据，也是从 P 层对象的合同接口里获取方式
				getPresenter().getContract().getData(textView.getText().toString());
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (handler != null) {
			handler.removeCallbacksAndMessages(null);
			handler = null;
		}
	}
	//</editor-fold>

	//<editor-fold desc="presenter creator">
	@Override
	protected DemoPresenter setPresenter() {
		// 指定 V 层所依赖的哪一个具体的 P 对象
		return new DemoPresenter();
	}
	//</editor-fold>

	//<editor-fold desc="getter">
	@Override
	protected DemoContract.DemoView getContract() {
		// 实现 V 层的合同接口
		return new DemoContract.DemoView<DemoBean>() {
			@Override
			public void toastNotify(String message) {
				Toast.makeText(DemoActivity.this,message,Toast.LENGTH_LONG).show();
			}

			@Override
			public void updateOnResult(DemoBean demoBean) {
				if (demoBean != null) {
				textView.setText(demoBean.getValue());
				}
			}


		};
	}

	public Handler getHandler() {
		return handler;
	}
	//</editor-fold>
}
