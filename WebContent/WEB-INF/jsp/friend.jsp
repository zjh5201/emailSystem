<%@ page import="com.email.po.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <li><a href="#">通讯录</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    
    <div id="usual1" class="usual"> 
    
    <div class="itab">
  	<ul> 
    <li><a href="#tab2">通讯录</a></li> 
  	</ul>
    </div> 
    </ul>
    
    </div> 
    
    
  	<div id="tab2" class="tabson">
    
    <form action="${pageContext.request.contextPath }/friend/add" name="search" method="post">
    <ul class="seachform">
    <li><label>邮箱账号</label><input name="friendId" type="text" class="scinput" /><label>&nbsp;</label></li>
    <li><input name="" type="submit" class="scbtn" value="添加"/></li>
    <li><span style="color:red;">${error }</span><span style="color:red;">${success }</span></li>
    </ul>
    </form>
    </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
    		<th style="text-align:center;">编号</th>
	        <th style="text-align:center;">邮箱账号</th>
	        <th style="text-align:center;">姓名</th>
	        <th style="text-align:center;">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${friends }" var="friend" varStatus="status">
          	<tr>
        		<td style="text-align:center;">${status.count }</td>
        		<td style="text-align:center;">${friend.friendId}</td>
        		<td style="text-align:center;">${friend.friendName }</td>
        		<td style="text-align:center;"><a href="${pageContext.request.contextPath }/friend/write/${friend.friendId }" style="color:blue;">发邮件</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/friend/delete/${friend.friendId}" style="color:blue;">删除</a></td>
        	</tr>
        </c:forEach>
       
        </tbody>
    </table>
    
   
  
    
    </div>  
       
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

