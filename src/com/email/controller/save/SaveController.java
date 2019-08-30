package com.email.controller.save;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.email.po.Save;
import com.email.service.SaveService;

@Controller
@RequestMapping("/save")
public class SaveController {
	
	@Autowired
	private SaveService saveService;
	
	//查看我的草稿箱
	@RequestMapping("/mySave/{userid}")
	public String mySave(HttpServletRequest request,@PathVariable String userid) {
		List<Save> list = saveService.selectByEE(userid+".com");
		request.setAttribute("saves", list);
		return "save";
	}
	
	//查看草稿箱具体邮件内容
	@RequestMapping("/look/{id}")
	public String look(HttpServletRequest request,@PathVariable int id) {
		Save save = saveService.selectBySaveId(id);
		request.setAttribute("save", save);
		return "send";
	}
}
