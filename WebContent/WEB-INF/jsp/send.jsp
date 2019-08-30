<%@ page import="com.email.po.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link href="${pageContext.request.contextPath }/resources/css/upload.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath }/resources/js/script.js"></script>
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
    <li><a href="#">写信</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    
    <div id="usual1" class="usual"> 
    
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">写邮件</a></li> 
  	</ul>
    </div> 
    
  	<div id="tab1" class="tabson">
    
    <div class="formtext">Hi，<b style="color:black;">${user.username }</b>，多发邮件，多联系！</div>
    <c:if test="${success ne null }"><p style="color:red;padding-left:20px;padding-bottom:10px;">${success }</p></c:if>
    <c:if test="${error ne null }"><p style="color:red;padding-left:20px;padding-bottom:10px;">${error }</p></c:if>
    <form action="email/upload/0" name="send" enctype="multipart/form-data" method="POST" >
    	<ul class="forminfo">
    		<input name="userid" type="hidden" value="${user.userid }" />
    		<li><label>收件人<b>*</b></label><input name="addresserId" type="text" class="dfinput" value="${save.addresserId }" style="width:518px;"/></li>
    		<li><label>主题<b>*</b></label><input name="title" type="text" class="dfinput" value="${save.title }"  style="width:518px;"/></li>
    		<li><label>添加附件<b></b></label><input type="file" name="file" onchange="fileSelected();"></li>
    		<li><textarea id="content7" name="content" style="width:700px;height:250px;visibility:hidden;"></textarea></li>
    		<li><span style="color:red;">注意保存草稿的时候邮件内容不能有样式哦！</span></li>
    		<li><input name="send" type="submit" class="btn" value="发送"/>
    		<button onclick="save()" class="btn"/>存草稿</button></li>
    	</ul>
    </form>
    </div>
    </div> 
    
     
	</div> 
 
	<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
      function save(){
    	  document.send.action="email/upload/1";
          document.send.submit();
      }
    </script>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	$('#content7').html("${save.content }");
	
	</script>
    

</body>
</html>
