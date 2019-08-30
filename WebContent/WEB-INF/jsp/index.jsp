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
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.js"></script>

</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    </ul>
    </div>
    
    <div class="mainindex">
    
    
    <div class="welinfo">
    <span><img src="${pageContext.request.contextPath }/resources/images/sun.png" alt="天气" /></span>
    <b>${user.username},&lt;<b style="text-decoration: underline;">${user.userid }</b>&gt; 欢迎使用365邮箱！</b>
    <a href="#">帐号设置</a>
    </div>
    
    <div class="welinfo">
    <span><img src="${pageContext.request.contextPath }/resources/images/time.png" alt="时间" /></span>
    <i>您当前未读邮件数：${count }</i>  
    </div>
    
    <div class="xline"></div>
    
    <ul class="iconlist">
    
    <li><p><a href="${pageContext.request.contextPath }/email/myReceive/${user.userid}"><img src="${pageContext.request.contextPath }/resources/images/ico01.png" style="display:block;"/>收件箱</a></p></li>
    <li><p><a href="${pageContext.request.contextPath }/send"><img src="${pageContext.request.contextPath }/resources/images/ico02.png"  style="display:block;"/>写信</a></p></li>
            
    </ul>
    
    <div class="xline"></div>
    <div class="box"></div>
    
    <div class="welinfo">
    <span><img src="${pageContext.request.contextPath }/resources/images/dp.png" alt="提醒" /></span>
    <b>365邮箱使用指南</b>
    </div>
    
    <ul class="infolist">
    <li><span>您可以快速写信</span><a class="ibtn" href="${pageContext.request.contextPath }/send">发布或管理文章</a></li>
    <li><span>您可以管理邮件</span><a class="ibtn" href="${pageContext.request.contextPath }/email/myReceive/${user.userid}">发布或管理产品</a></li>
    <li><span>您可以管理通讯录</span><a class="ibtn" href="friend/myfriend/${user.userid }">账户管理</a></li>
    </ul>
    
    <div class="xline"></div>
    
    
    
    </div>
</body>
</html>
