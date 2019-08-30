package com.email.controller.email;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.email.po.Email;
import com.email.po.EmailId;
import com.email.po.Save;
import com.email.po.User;
import com.email.service.EmailService;
import com.email.service.SaveService;
import com.email.service.UserService;

import sun.security.provider.MD5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private SaveService saveService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/upload/{type}",method=RequestMethod.POST)
	public String upLoad(Email email,Save save,String userid,HttpServletRequest request,HttpSession session,@PathVariable int type,
			@RequestParam(value="file") MultipartFile pic) {
		User u = (User)session.getAttribute("user");
		if(u==null)
			return "login";
		//检查收件人地址是否为空
		if(email.getAddresserId()==null||email.getAddresserId().trim().equals("")) {
			request.setAttribute("error", "请输入收件人邮箱");
			return "send";
		}
		//检查收件人地址是否存在
		boolean flag = userService.checkItExist(userid);
		if(flag==true) {
			request.setAttribute("error", "该收件人不存在！");
			return "send";
		}
		if(email.getTitle()==null||email.getTitle().trim().equals("")) {
			request.setAttribute("error", "主题填写不完整！");
			return "send";
		}
		String originalFileName;
		String path;
		if(pic==null||pic.getSize()==0) {
			originalFileName = "无附件";
			path = "";
		}else {
			//获取文件的原始名称
			originalFileName = pic.getOriginalFilename();
			//上传文件，返回的path是存放在服务器中的文件路径
			path = upLoadFile(pic);
			//所有检验完毕后把email信息填写完整之后添加到数据库	
		}
		if(type==0) {
			Date date=new Date();
		    email.setAddresseeId(u.getUserid());
			email.setTime(date);
			email.setFilename(originalFileName);
			email.setFilepath(path);
			//获取ID
			int id = emailService.getTheLastId();
			email.setId(id);
			email.setReaded(0);
			emailService.addMail(email);
		}else {
			Date date=new Date();
		    save.setAddresseeId(u.getUserid());
			save.setTime(date);
			save.setFilename(originalFileName);
			save.setFilepath(path);
			//获取ID
			int id = saveService.getTheLastId();
			save.setId(id);
			save.setReaded(0);
			saveService.addSave(save);
		}
		request.setAttribute("success", "操作成功！");
		request.setAttribute("email", null);
		request.setAttribute("save", null);
		return "send";
	}
	
	//附件上传方法
	public String upLoadFile(MultipartFile file){
		String path = null;
		//首先获取文件原始名称,目的在于获取上传文件的后缀名
		String originalName = file.getOriginalFilename();
		//获取文件后缀名，要求是png、jpg、gif、jpeg
		String suffix = originalName.substring(originalName.lastIndexOf('.'));
		if(suffix.equalsIgnoreCase(".bat")||suffix.equalsIgnoreCase(".exe")){
			return null;
		}
		//创建新的文件名
		String newFileName = UUID.randomUUID()+originalName.substring(originalName.lastIndexOf('.'));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		File f;
		//设置路径
		//创建文件夹路径，创建的文件夹路径有级数限制。注意
			path = "D:\\TomcatFile\\"+sdf.format(new Date())+"\\";
			File pat = new File(path);
			pat.mkdirs();
			path = path+newFileName;
			f = new File(path);
		try {
			file.transferTo(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	
	/*
	 * 该方法用来查看我的收件箱
	 * 
	 * */
	
	@RequestMapping("/myReceive/{userid}")
	public String myReceive(HttpSession session,HttpServletRequest request,@PathVariable String userid) {
		//检查用户是否已经注销，或者浏览器已清除用户缓存，如果清除，则需要重新登录
		User u = (User)session.getAttribute("user");
		if(u==null)
			return "login";
		System.out.println(userid);
		//获取指定收件人的所有邮件
		List<Email> list = emailService.selectByErId(userid+".com");
		List<Email> finalList = new ArrayList<Email>();
		for (Email email : list) {
			if(email.getReaded()!=2) {
				finalList.add(email);
			}
		}
		//按已读和未读来排序，未读的优先显示
		finalList.sort(new Comparator<Email>() {
			public int compare(Email o1, Email o2) {
				return o1.getReaded()-o2.getReaded();
			}
			
		});
		request.setAttribute("emails", finalList);
		return "receiveEmail";
	}
	
	/*
	 * 该方法完成邮件查看的功能
	 * 
	 * */
	@RequestMapping(value="/look/{id}",method=RequestMethod.GET)
	public String look(@PathVariable int id,HttpSession session,HttpServletRequest request) {
		//检查用户是否已经注销，或者浏览器已清除用户缓存，如果清除，则需要重新登录
		User u = (User)session.getAttribute("user");
		if(u==null)
			return "login";
		
		Email email = emailService.getEmailById(id);
		request.setAttribute("email", email);
		
		return "email_info";
	}
	
	/*
	 *  完成下载附件的功能。也就是当用户查看邮件时可以点击下载
	 * 
	 * */
	@RequestMapping(value="/downloadFile/{id}",method=RequestMethod.GET)
	public void downloadFile(HttpServletRequest request,HttpServletResponse response,@PathVariable int id) throws IOException {
		Email email=emailService.getEmailById(id);
        File file = new File(email.getFilepath());
        //如果文件不存在
        if(!file.exists()){
        	//则向前台报告文件已被删除
        	response.getWriter().println("<script type='text/javascript'>alert('您要下载的资源被删除啦！')</script>");
        	return;
        }
        //处理文件名，用真实的文件名导出
        String realname=email.getFilename();
        //设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        //读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(email.getFilepath());
        //创建输出流
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while((len=in.read(buffer))>0){
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();
	}
	
	//按条件查询满足条件的邮件
	/*
	 * 1.发件人
	 * 2.标题
	 * 3.未读、已读
	 * 
	 * */
	@RequestMapping("/select/{userid}")
	public String select(Email email,HttpServletRequest request,@PathVariable String userid) {
		List<Email> list = emailService.selectByOptions(email,(userid+".com"));
		request.setAttribute("emails", list);
		return "receiveEmail";
	}
	
	//查看自己的发件箱，userid是自己的邮箱账号
	@RequestMapping("/send/{userid}")
	public String send(HttpServletRequest request,@PathVariable String userid) {
		//获取自己以往所有发送过的邮件，查询条件是根据自己的邮箱账号。
		List<Email> list = emailService.selectByEeId(userid+".com");
		for (Email email : list) {
			System.out.println(email.getTitle());
		}
		List<Email> finalList = new ArrayList<Email>();
		for (Email email : list) {
			if(email.getReaded()!=3&&email.getReaded()!=4) {
				finalList.add(email);
			}
		}
		request.setAttribute("emails", finalList);
		return "sendEmails";
		
	}
	
	/*
	 * 该方法用来删除指定的（收件）邮件,
	 * 这里有要注意的地方，因为邮件是一份的，且是发件人和收件人共享的，如果直接执行删除数据库中的操作，那么势必会影响另一方，
	 * 这里的处理方法是利用邮件的readed字段来进行处理，readed字段值为0时表示未读，1表示已读，2表示收件人删除，3表示发件人
	 * 删除且邮件未读，4表示发件人删除且邮件已读
	 * */
	@RequestMapping("reDelete/{id}")
	public String reDelete(HttpServletRequest request,@PathVariable int id) {
		//调用service里的delete方法。
		emailService.reDelete(id);
		//获取指定收件人的所有邮件
		User currentUser  = (User)request.getSession().getAttribute("user");
		String userid = currentUser.getUserid();
		List<Email> list = emailService.selectByErId(userid);
		List<Email> finalList = new ArrayList<Email>();
		for (Email email : list) {
			if(email.getReaded()!=2) {
				finalList.add(email);
			}
		}
		//按已读和未读来排序，未读的优先显示
		finalList.sort(new Comparator<Email>() {
			public int compare(Email o1, Email o2) {
				return o1.getReaded()-o2.getReaded();
			}
		});
		request.setAttribute("emails", finalList);
		return "receiveEmail";
	}
	
	/*
	 * 该方法用来删除指定的（发件）邮件
	 * */
	@RequestMapping("seDelete/{id}")
	public String seDelete(HttpServletRequest request,@PathVariable int id) {
		//调用service里的delete方法。
		emailService.sedelete(id);
		//获取指定发件人的所有邮件
		User currentUser  = (User)request.getSession().getAttribute("user");
		String userid = currentUser.getUserid();
		List<Email> list = emailService.selectByEeId(userid);
		List<Email> finalList = new ArrayList<Email>();
		for (Email email : list) {
			if(email.getReaded()!=3&&email.getReaded()!=4) {
				finalList.add(email);
			}
		}
		//按已读和未读来排序，未读的优先显示
		finalList.sort(new Comparator<Email>() {

			public int compare(Email o1, Email o2) {
				return o1.getReaded()-o2.getReaded();
			}
			
		});
		request.setAttribute("emails", finalList);
		return "sendEmails";
	}
	
	//设置邮件为已读
	@RequestMapping("/setReaded/{id}")
	public String setReaded(HttpServletRequest request,@PathVariable int id) {
		emailService.setEmailReaded(id);
		//获取指定收件人的所有邮件
		User currentUser  = (User)request.getSession().getAttribute("user");
		String userid = currentUser.getUserid();
		List<Email> list = emailService.selectByErId(userid);
		List<Email> finalList = new ArrayList<Email>();
		for (Email email : list) {
			if(email.getReaded()!=2) {
				finalList.add(email);
			}
		}
		//按已读和未读来排序，未读的优先显示
		finalList.sort(new Comparator<Email>() {
			public int compare(Email o1, Email o2) {
				return o1.getReaded()-o2.getReaded();
			}
			
		});
		request.setAttribute("emails", finalList);
		return "receiveEmail";
	}
	
	//批量删除收件箱
	@RequestMapping("/reListdel")
	public String ids(HttpServletRequest request,EmailId ids) {
		List<Integer> list = ids.getId();
		emailService.listDelete(list);
		//获取指定收件人的所有邮件
		User currentUser  = (User)request.getSession().getAttribute("user");
		String userid = currentUser.getUserid();
		List<Email> alllist = emailService.selectByErId(userid);
		List<Email> finalList = new ArrayList<Email>();
		for (Email email : alllist) {
			if(email.getReaded()!=2) {
				finalList.add(email);
			}
		}
		//按已读和未读来排序，未读的优先显示
		finalList.sort(new Comparator<Email>() {
			public int compare(Email o1, Email o2) {
				return o1.getReaded()-o2.getReaded();
			}
		});
		request.setAttribute("emails", finalList);
		return "receiveEmail";
	}
	//批量删除发件箱
	@RequestMapping("/seListdel")
	public String seListdel(HttpServletRequest request,EmailId ids) {
		List<Integer> list = ids.getId();
		emailService.seListDelete(list);
		//获取指定发件人的所有邮件
		User currentUser  = (User)request.getSession().getAttribute("user");
		String userid = currentUser.getUserid();
		List<Email> alllist = emailService.selectByEeId(userid);
		List<Email> finalList = new ArrayList<Email>();
		for (Email email : alllist) {
			if(email.getReaded()!=3&&email.getReaded()!=4) {
				finalList.add(email);
			}
		}
		//按已读和未读来排序，未读的优先显示
		finalList.sort(new Comparator<Email>() {
			public int compare(Email o1, Email o2) {
				return o1.getReaded()-o2.getReaded();
			}
		});
		request.setAttribute("emails", finalList);
		return "sendEmails";
	}
	//转发路由
	@RequestMapping("/transmit/{id}")
	public String transmit(HttpServletRequest request,@PathVariable int id) {
		request.setAttribute("id", id);
		return "transmit";
	}
	//转发邮件
	@RequestMapping("/toTrans")
	public String toTrans(HttpServletRequest request,int id,String userid) {
		Email email = emailService.getEmailById(id);
		email.setAddresserId(userid);
		int lastId = emailService.getTheLastId();
		email.setId(lastId);
		email.setReaded(0);
		emailService.addMail(email);
		request.setAttribute("msg", "转发成功！");
		return "transmit";
	}
}
