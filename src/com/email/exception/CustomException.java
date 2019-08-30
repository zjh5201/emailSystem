package com.email.exception;

public class CustomException extends Exception{
	//异常信息
	public String message;
	//通过构造函数传入异常信息
	public CustomException(String message){
		super();
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
