package com.mayue.neteasemvp.bean;

public class DemoBean extends BaseEntity {

	private String value;


	public DemoBean(boolean result, String error, String value) {
		super(result, error);
		this.value = value;
	}

	public DemoBean(boolean result, String value) {
		super(result);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
