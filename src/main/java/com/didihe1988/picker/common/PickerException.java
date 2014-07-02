package com.didihe1988.picker.common;

public class PickerException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public PickerException(int status) {
		this.status = status;
	}

	public PickerException(int status, String message) {
		super(message);
		this.status = status;
	}

}
