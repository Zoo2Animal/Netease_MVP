package com.mayue.neteasemvp.bean;

public class BaseEntity {
	private boolean success;
	private String error;

	public BaseEntity(boolean success, String error) {
		this.success = success;
		this.error = error;
	}

	public BaseEntity(boolean success) {
		this(success,null);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
