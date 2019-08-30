package com.email.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
public class SystemExceptionResolver implements HandlerExceptionResolver{
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		//判断异常是否是系统自定义异常，如果是自定义异常就获取自定义异常信息，然后跳转到错误页面
		CustomException customException=null;
		if(ex instanceof CustomException){//异常是自定义异常
			customException=(CustomException) ex;
		}else{//异常非自定义异常，在此写入异常信息为“未知错误”
			customException=new CustomException("哎呦~ 服务器居然累倒了!");
		}
		
		//提取异常信息
		String message=customException.getMessage();
		//定义modelandview
		ModelAndView modelAndView=new ModelAndView();
		//把错误信息存在modelandview
		modelAndView.addObject("message", message);
		//跳转到错误页面
		modelAndView.setViewName("error.jsp");
		return modelAndView;
	}

	
}
