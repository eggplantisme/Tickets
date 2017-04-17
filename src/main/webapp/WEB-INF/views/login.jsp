<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">  
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<form name="login_form" action="login.do" method="post" role = "form">
		    	登录窗口
		    <div class = "form-group">
		            <label for = "name">用户名</label>
		            <input class="form-control" type="text" name="username">
		    </div>
		    <div class = "form-group">
		            <label for = "pass">密码</label>
		            <input class="form-control" type="password" name="password">
		    </div>
		    <div class = "form-group">
		        <input type="submit" class="form-control" name="submit" value="登录" class="btn btn-default"/> 
		        <a href="register.do"><button class="form-control"> 注册新用户</button></a>
		    </div>
	</form>
</body>
</html>