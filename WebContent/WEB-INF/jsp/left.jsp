<%@ page import="com.email.po.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<base href="<%=basePath%>">
<%
	User user = (User)session.getAttribute("user");
	if(user==null)
		response.sendRedirect("/WEB-INF/jsp/login.jsp");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath }/resources/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath }/resources/js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>


</head>

<body style="background:#f0f9fd;">
	<div class="lefttop">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮件管理</div>
    
    <dl class="leftmenu">
        
    <dd>
    <div class="title">
    	<span><img src="${pageContext.request.contextPath }/resources/images/leftico01.png" /></span>邮件信息
    </div>
    
    	<ul class="menuson">
    	<li><cite></cite><a href="${pageContext.request.contextPath }/index" target="rightFrame">首页</a><i></i></li>
    	<li><cite></cite><a href="${pageContext.request.contextPath }/send" target="rightFrame">写信</a><i></i></li>
        <li><cite></cite><a href="${pageContext.request.contextPath }/email/myReceive/${user.userid}" target="rightFrame">收件箱</a><i></i></li>
        <li><cite></cite><a href="${pageContext.request.contextPath }/email/send/${user.userid}" target="rightFrame">已发送</a><i></i></li>
        </ul>    
    </dd>
        
    
    <dd>
    <div class="title">
    <span><img src="${pageContext.request.contextPath }/resources/images/leftico02.png" /></span>我的草稿箱
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="${pageContext.request.contextPath }/save/mySave/${user.userid}" target="rightFrame">草稿箱</a><i></i></li>
        </ul>     
    </dd> 
    
    
    <dd><div class="title"><span><img src="${pageContext.request.contextPath }/resources/images/leftico03.png" /></span>通讯录</div>
    <ul class="menuson">
        <li><cite></cite><a href="friend/myfriend/${user.userid }" target="rightFrame">通讯录</a><i></i></li>
        <li><cite></cite><a href="user/editInfo/${user.userid }" target="rightFrame">账户设置</a><i></i></li>
    </ul>    
    </dd>    
    
    </dl>
</body>
</html>
