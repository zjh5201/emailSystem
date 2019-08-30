<%@ page import="com.email.po.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
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
<link href="${pageContext.request.contextPath }/resources/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/select-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/editor/kindeditor.js"></script>

<script type="text/javascript">
    KE.show({
        id : 'content7',
        cssPath : './index.css'
    });
  </script>
  
<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
	});
});
</script>
</head>

<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">邮件详细信息</a></li>
    </ul>
    </div>
    <div class="email_head">
    	<div class="content"> 
    		<span id="title"></span>
    		<p style="height: 10px;"></p>
    		<table>
    			<tr><td><span class="describe">发件人：</span></td><td><span id="sender">${email.addresseeId }</span></td></tr>
    			<tr><td><span class="describe">时&nbsp;&nbsp;&nbsp;间：</span></td><td><span id="time">${email.time }</span></td></tr>
    			<tr><td><span class="describe">收件人：</span></td><td><span id="reciver">${email.addresserId }</span></td></tr>
    			<tr><td><span class="describe">附&nbsp;&nbsp;&nbsp;件：</span></td><td><span id="file"><a <c:if test="${email.filename ne '无附件' }">href="${pageContext.request.contextPath }/email/downloadFile/${email.id }"</c:if>>${email.filename }</a></span></td></tr>
    		</table>
    	</div>
    </div>
    <p></p>
    <div class="content">
    	${email.content }
    </div>

 
	<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
    </script>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
    
    
    
    
    
    </div>

</body>
</html>


