<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
	<link rel="icon" href="images/icon.jpg">
	<link rel="stylesheet" href="css/bootstrap.min.css">  
	<link rel="stylesheet" href="css/user.css">
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/Myjs.js"></script>
	<script src="js/login.js"></script>
	<style type="text/css">
		.col-center-block {
			float:none;
			display: block;
			margin-left: auto;
			margin-right: auto;
		}
	</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-6 col-md-4 col-center-block">
				<form name="login_form" action="login.do" method="post">
				    <div class="well text-center">欢迎登录</div>
				    <div class = "form-group dd">
				            <label for = "name">用户名</label>
				            <input class="form-control" type="text" name="username" placeholder="用户名">
				            <span class='info user_put'>请输入6个字符的用户名，可由汉字数字字母下划线组成（空格自动忽略）。</span>
							<span class='info_error user_error'>请输入正确用户名!</span>
							<span class='ok user_ok'></span>
				    </div>
				    <div class = "form-group dd">
				            <label for = "pass">密码</label>
				            <input class="form-control" type="password" name="password" placeholder="密码">
				            <span class ='info pass_put'>请输入6-18位密码，由数字字母下划线组成（不能出现空格）。</span>
				            <span class='info_error pass_error'>请输入正确密码!</span>
							<span class='ok pass_ok'></span>
				    </div>
				    <div class = "form-group">
				        <input type="submit" class="form-control" name="submit" value="登录" class="btn btn-default"/> 
				        
				    </div>
				</form>
				
				<a href="regist.do"><button class="form-control"> 注册新用户</button></a>
			</div>
		</div>
	</div>
	
</body>
</html>