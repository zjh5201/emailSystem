<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<base href="<%=basePath%>">
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
	});  
</script> 

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  

    
    <div class="loginbody">
    
    <span class="" style="height:20%"></span> 
       
    <div class="loginbox">
    <form action="user/login.do" method="post" >
    <ul>
    <li><input name="userid" type="text" class="loginuser" onclick="JavaScript:this.value=''"/></li>
    <li><input name="username" type="text" class="loginuser"/></li>
    <li><input name="password" type="password" class="loginpwd" onclick="JavaScript:this.value=''"/></li>
    
    <li><input name="submit" type="submit" class="loginbtn" value="注册" /></li>
    </ul>
    </form>
    
    
    
    </div>
    
    </div>
    
    
    
    <div class="loginbm">版权所有  2018·xm javaweb课设</div>
</body>
</html>
