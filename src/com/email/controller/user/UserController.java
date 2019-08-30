package com.email.controller.user;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.email.po.User;
import com.email.service.EmailService;
import com.email.service.UserService;
import com.email.utils.SendEmailUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	//用户注册
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public String regist(User user,String repassword,HttpServletRequest request) {
		
		//校验用户的输入
		if(user.getUserid()==null||user.getUserid().equals("")||user.getUserid().length()<3||user.getUserid().length()>20
				||user.getUserid().indexOf(".")!=-1||user.getUserid().indexOf(" ")!=-1||user.getUserid().indexOf("@")!=-1) {
			request.setAttribute("error", "您的账号输入有误！请重新注册");
			return "regist";
		}
		if(user.getPassword()==null||user.getPassword().trim().equals("")) {
			request.setAttribute("error", "请输入正确的密码！");
			return "regist";
		}
		if(!user.getPassword().equals(repassword)) {
			request.setAttribute("error", "两次输入的密码不一致！请重新注册");
			return "regist";
		}
		//这里检测账号是否存在
		String userId = user.getUserid();
		boolean flag = userService.checkItExist(userId+"@365.com");
		if(flag==false) {
			request.setAttribute("error", "该账号已存在！请重新输入账号");
			return "regist";
		}
		//如果检测全部通过，将用户信息添加到数据库，之后跳到登录界面进行登录
		request.setAttribute("userId", user.getUserid());
		user.setUserid(user.getUserid()+"@365.com");
		userService.addUser(user);
		request.setAttribute("success", "注册成功！");
		return "login";
	}
	
	//用户登录
	@RequestMapping("/login")
	public String login(User user,HttpServletRequest request,HttpSession session) {
		//校验用户的输入
		if(user.getUserid()==null||user.getUserid().equals("")||user.getUserid().length()<3||user.getUserid().length()>20
				||user.getUserid().indexOf(".")!=-1||user.getUserid().indexOf(" ")!=-1||user.getUserid().indexOf("@")!=-1) {
			request.setAttribute("error", "您的账号不正确！请重新输入");
			return "login";
		}
		if(user.getPassword()==null||user.getPassword().trim().equals("")) {
			request.setAttribute("error", "请输入正确的密码！");
			return "login";
		}
		//这里检测账号是否存在
		String userId = user.getUserid();
		boolean flag = userService.checkItExist(userId+"@365.com");
		if(flag!=false) {
			request.setAttribute("error", "该账户不存在,请先注册");
			return "login";
		}
		User dbUser = userService.getUserByUserid(userId+"@365.com");
		if(!user.getPassword().equals(dbUser.getPassword())) {
			request.setAttribute("error", "密码不正确！请重新登录");
			return "login";
		}
		//如果登录成功，则跳到主页面，并保存到session
		request.setAttribute("user", user);
		session.setAttribute("user", dbUser);
		//获取当前用户
		int count = emailService.getCountsNoReaded(user.getUserid());
		System.out.println(count);
		session.setAttribute("count", count);
		return "main";
	}
	//忘记密码发送邮件操作
	@RequestMapping("/forgetPwd")
	public String forgetPwd(HttpServletRequest request,String mail,User user) {
		//先检车用户是否存在于系统中
		boolean u = userService.checkItExist(user.getUserid()+"@365.com");
		if(u==true) {
			request.setAttribute("msg", "您所输入的账号不存在！");
			return "forgetPassword";
		}
		User us = userService.getUserByUserid(user.getUserid()+"@365.com");
		String desc = java.util.Base64.getEncoder().encodeToString(user.getUserid().getBytes(StandardCharsets.UTF_8));
		//设置邮件主题和邮件内容
		String emailTitle = "365系统密码找回";
		String emailBody = "您好,"+ us.getUsername()+"，欢迎您使用365邮箱密码找回功能<br/>,请点击以下网址进行重设密码(如果打不开网址，请手动复制粘贴到浏览器地址栏进行访问)<br/>"
				+ "http://localhost:8080/emailSys/user/resetPassword/"+desc;
		//调用utils里的类发送邮件
		SendEmailUtil.sendEmail(mail, emailTitle, emailBody);
		request.setAttribute("mail", mail);
		String userId = us.getUserid().substring(0,us.getUserid().indexOf("@"));
		request.setAttribute("userid", userId);
		request.setAttribute("msg", "(发送成功,如果没有收到邮件!请先检查自己的邮箱是否输入正确,如果正确却没收到,请重新提交)");
		return "forgetPassword";
	}
	
	//重新设置密码
	@RequestMapping("/resetPassword/{code}")
	public String resetPassword(HttpServletRequest request,@PathVariable String code) {
		String userid = new String(java.util.Base64.getDecoder().decode(code),StandardCharsets.UTF_8);
		User user = userService.getUserByUserid(userid+"@365.com");
		request.setAttribute("user", user);
		return "fpage";
	}
	//修改密码
	@RequestMapping("/editPwd/{userid}")
	public String editPwd(HttpServletRequest request,@PathVariable String userid,String password,String repassword) {
		userid = userid + ".com";
		System.out.println("isis:"+userid);
		if(!password.equals(repassword)) {
			User user = userService.getUserByUserid(userid);
			request.setAttribute("user", user);
			request.setAttribute("msg", "两次输入的密码不一致！");
			return "fpage";
		}
		userService.editPassword(userid,password);
		request.setAttribute("success", "修改成功！");
		return "fpage";
	}
	
	//路由
	@RequestMapping("/editInfo/{userid}")
	public String editInfo(HttpServletRequest request) {
		return "editInfo";
	}
	
	//修改账户信息
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request,User user) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if(user.getPassword().contains(" ")) {
			request.setAttribute("msg", "密码不允许包含空格！");
			request.setAttribute("user", sessionUser);
			return "editInfo";
		}
		if(user.getPassword().length()<3||user.getPassword().length()>20) {
			request.setAttribute("msg", "密码的长度应在3-20位之间");
			request.setAttribute("user", sessionUser);
			return "editInfo";
		}
		if(user.getUsername().contains(" ")||user.getUsername().length()<2) {
			request.setAttribute("msg", "用户名输入不正确");
			request.setAttribute("user", sessionUser);
			return "editInfo";
		}
		userService.editInfo(user);
		request.setAttribute("user", user);
		//重新设置session中user
		request.getSession().setAttribute("user", user);
		request.setAttribute("msg", "修改成功！");
		return "editInfo";
	}
}
