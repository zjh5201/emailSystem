package com.email.controller.friend;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hslf.dev.SlideAndNotesAtomListing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.email.po.Email;
import com.email.po.Friend;
import com.email.po.User;
import com.email.service.FriendService;
import com.email.service.UserService;

@Controller
@RequestMapping("/friend")
public class FriendController {
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	private UserService userService;
	
	//查看我的通讯录
	@RequestMapping("/myfriend/{userid}")
	public String myfriend(HttpServletRequest request,@PathVariable String userid) {
		List<Friend> list = friendService.getAllFriends(userid+".com");
		request.setAttribute("friends", list);
		return "friend";
	}
	
	//添加朋友
	@RequestMapping("/add")
	public String add(HttpServletRequest request,HttpSession session,String friendId) {
		User user = (User)session.getAttribute("user");
		List<Friend> lists = friendService.getAllFriends(user.getUserid());
		for (Friend friend : lists) {
			if(friend.getFriendId().equals(friendId)) {
				request.setAttribute("error", "该用户已经存在通信录中！请勿重新添加");
				List<Friend> list = friendService.getAllFriends(user.getUserid());
				request.setAttribute("friends", list);
				return "friend";
			}
		}
		Friend friend = new Friend();
		friend.setUserid(user.getUserid());
		friend.setFriendId(friendId);
		User fri = userService.getUserByUserid(friendId);
		if(fri==null) {
			List<Friend> list = friendService.getAllFriends(user.getUserid());
			request.setAttribute("error", "您要添加的用户不存在！");
			request.setAttribute("friends", list);
			return "friend";
		}
		friend.setFriendName(fri.getUsername());
		friendService.add(friend);
		List<Friend> list = friendService.getAllFriends(user.getUserid());
		request.setAttribute("success", "添加成功！");
		request.setAttribute("friends", list);
		return "friend";
	}
	
	//删除通讯录好友
	@RequestMapping("/delete/{friendId}")
	public String delete(HttpServletRequest request,HttpSession session,@PathVariable String friendId) {
		User user = (User)session.getAttribute("user");
		friendService.delete(friendId+".com");
		List<Friend> list = friendService.getAllFriends(user.getUserid());
		request.setAttribute("friends", list);
		return "friend";
	}
	
	//给朋友写信，带着朋友信息跳转到写信页面
	@RequestMapping("/write/{friendId}")
	public String write(HttpServletRequest request,@PathVariable String friendId) {
		Email email = new Email();
		email.setAddresserId(friendId+".com");
		request.setAttribute("save", email);
		return "send";
	}
}
