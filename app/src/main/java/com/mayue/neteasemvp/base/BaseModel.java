package com.mayue.neteasemvp.base;

/**
 * created by matthew ,2020/03/08
 * <BaseModel>
 *     M 模型层基类，和 P presenter 层互相依赖；核心思想，M 层只与 P 层交互，与 V View层无关。主要负责提供数据模型；
 * 但是，在谷歌官方的 mvp 案例中，模型层并没有很明显的业务体现，而是直接由 P 层去各个通讯模块（比如，数据库模块，网络模块）直接获取数据。
 * 各人理解： 没有必要纠结两种方案的实现，可以依据项目的实际情况取舍，如果数据来源比较单一，只有一到二种，可以省略 M 层的业务；
 * M 层也可以说是数据模型的管理者，比如做分页管理等。
 *      </BaseModel>
 *
 * @param <P>           presenter 对象，M 层依赖的 P 层对象，继承于 BasePresenter
 * @param <CONTRACT>    CONTRACT 接口，即 MVP 中的契约接口，其规定了各层之间需要实现的具体业务
 */
public abstract class BaseModel<P extends BasePresenter,CONTRACT> {

	// 持有的 presenter 对象
	private P presenter;

	// 构造器必须传入 presenter 对象
	public BaseModel(P presenter) {
		this.presenter = presenter;
	}

	// p.getter
	public P getPresenter() {
		return presenter;
	}

	// 由子类决定自己的契约接口
	protected abstract CONTRACT getContract();


}
