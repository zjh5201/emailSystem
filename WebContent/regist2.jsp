<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>365邮箱注册</title>
    <link href="${pageContext.request.contextPath }/resources/css/demo.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath }/resources/js/jquery-1.10.2.min.js" type="text/javascript"></script>
    <!--Framework-->
    <script src="${pageContext.request.contextPath }/resources/js/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath }/resources/js/jquery-ui.js" type="text/javascript"></script>
    <!--End Framework-->
     <script src="${pageContext.request.contextPath }/resources/js/jquery.ffform.js" type="text/javascript"></script>

</head>
<body class="bounceIn animated">
	
    <section id="getintouch" class="rotateInUpLeft animated">
    	
        <div class="container" style="border-bottom: 0;">
            <h1 style="color:black;">
                <span>张家豪注册</span>
            </h1>
        </div>
        <div class="container">
        	<p style="color:red;font-size:14px;">${error }</p>
            <form class="contact" action="/emailSys/user/regist" method="post" id="form">
            <div class="row clearfix">
                <div class="lbl">
                    <label for="name">
                        账号：</label>
                </div>
                <div class="ctrl">
                    <input type="text" id="userid" name="userid" data-required="true" data-validation="text"
                        style="width:50%;" placeholder="请输入3-20为数字或字母"/>
                </div>
            </div>
           <div class="row clearfix">
                <div class="lbl">
                    <label for="name">
                        用户名：</label>
                </div>
                <div class="ctrl">
                    <input type="text" id="name" name="name" 
                        style="width:90%;" placeholder="请输入您的昵称"/>
                </div>
            </div>
            <div class="row clearfix">
                <div class="lbl">
                    <label for="password">
                        密码：</label>
                </div>
                <div class="ctrl">
                    <input type="password" id="password" name="password"  style="width:90%;height:32px;border:1px solid rgb(225,225,225)"/>
                </div>
            </div>
			<div class="row clearfix">
                <div class="lbl">
                    <label for="repassword">
                        性别：</label>
                </div>
                <div class="ctrl">
                    <input type="text" id="repassword" name="gender" style="width:90%;height:32px;border:1px solid rgb(225,225,225)"/>
                </div>
            </div>
            	<div class="row clearfix">
                <div class="lbl">
                    <label for="repassword">
                        邮箱：</label>
                </div>
                <div class="ctrl">
                    <input type="text"  name="email" style="width:90%;height:32px;border:1px solid rgb(225,225,225)"/>
                </div>
            </div>
            <script>
            	function goToLogin(){
            		window.location.href="login.jsp"; 
            	}
            </script>
            <div class="row  clearfix">
                <div class="span10 offset2">
                    <input type="submit" name="submit" id="submit" class="submit" value="注   册">
                     </form>
                    <form action="/emailSys/login" style="display:inline-block;">
                    	<input type="submit" class="submit" style="background-color:skyblue;" value="去 登 录">
                	</form>
                </div>
            </div>
           
        </div>
    </section>
</body>
</html>
