package com.email.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.email.po.EmailId;

@Controller
@RequestMapping("/")
public class ViewController {
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("send")
	public String send() {
		return "send";
	}
	
	@RequestMapping("regist")
	public String regist() {
		return "regist";
	}
	
	@RequestMapping("left")
	public String left() {
		return "left";
	}
	@RequestMapping("top")
	public String top() {
		return "top";
	}
	@RequestMapping("index")
	public String index() {
		return "index";
	}
	@RequestMapping("forgetPassword")
	public String forgetPassword() {
		return "forgetPassword";
	}
	
	@RequestMapping("idsDelete")
	public String listDelete(List<Integer> id) {
		for (Integer integer : id) {
			System.out.println("我好冷"+integer);
		}
		return "index";
	}
}
