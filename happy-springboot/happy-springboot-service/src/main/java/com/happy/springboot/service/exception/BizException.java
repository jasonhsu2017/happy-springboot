package com.happy.springboot.service.exception;

public class BizException extends RuntimeException {

	

	private static final long serialVersionUID = 1L;


	private int code;

	public BizException() {

	}

	public BizException(String message) {
		super(message);
	}

	public BizException(int code, String message) {
		super(message);
		this.code = code;
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public BizException(int code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String toString() {
		return "code:" + code + ", msg:" + getMessage();
	}

	
}
