<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>邮箱登录</title>
    <link href="${pageContext.request.contextPath}/resources/css/demo.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js" type="text/javascript"></script>
    <!--Framework-->
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.js" type="text/javascript"></script>
    <!--End Framework-->
     <script src="${pageContext.request.contextPath}/resources/js/jquery.ffform.js" type="text/javascript"></script>

</head>
<body class="bounceIn animated">
    <section id="getintouch" class="rotateInUpLeft animated">
        <div class="container" style="border-bottom: 0;">
            <h1 style="color:black;">
                <span>365邮箱登录</span>
            </h1>
        </div>
        <div class="container">
        <p style="color:red;font-size:14px;">${error }</p>
        <c:if test="${success ne null }">
        	<p style="color:red;font-size:14px;">${success }</p>
        </c:if>
            <form class="contact" action="/emailSys/user/login" method="POST" id="form">
            <div class="row clearfix">
                <div class="lbl">
                    <label for="name">
                        账号：</label>
                </div>
                <div class="ctrl">
                    <input type="text" id="name" name="userid" data-required="true" data-validation="text"
                        data-msg="Invalid Name" style="width:50%;" value="${user.userid }" placeholder="请填写您的账号"><span style="font-weight:bold;">@365.com</span>
                </div>
            </div>
            <div class="row clearfix">
                <div class="lbl">
                    <label for="email">
                        密码：</label>
                </div>
                <div class="ctrl">
                    <input type="password" id="password" name="password"  style="width:93%;height:32px;border:1px solid rgb(225,225,225)"
                    >
                </div>
                <a href="${pageContext.request.contextPath }/forgetPassword" style="font-size:14px;">忘记密码</a>
            </div>
            <script>
            	function goToRegist(){
            		window.location.href="regist.jsp";
            	}
            </script>
            <div class="row  clearfix">
                <div class="span10 offset2">
                    <input type="submit" name="submit" id="submit" class="submit" value="登   录">
                    </form>
                    <form action="/emailSys/regist" method="GET" style="display:inline-block;">
                    	<input type="submit" class="submit" style="background-color:skyblue;" value="去 注 册">
                	</form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
