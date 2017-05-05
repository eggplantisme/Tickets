<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Regist</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/user.css">
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/Myjs.js"></script>
	<script type="text/javascript" src="js/regist.js"></script>
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
				<form name="login_form" action="regist.do" method="post">
				    <div class="well text-center">欢迎注册</div>
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
							<span class='safe'>安全等级：<strong class='s s1'></strong> <strong class='s s2'></strong>
								<strong class='s s3'></strong> <strong class='word'></strong>
							</span>
							<span class='info_error pass_error'>请输入正确密码!</span>
							<span class='ok pass_ok'></span>
				    </div>
				    <div class = "form-group dd">
				            <label for = "pass">确认密码</label>
				            <input class="form-control" type="password" name="ConfirmPassword" placeholder="确认密码">
				    		<span class='info confirm_put'>请再次输入密码.</span>
							<span class='info_error confirm_error'>两次密码不一致！</span>
							<span class='ok confirm_ok'></span>
				    </div>
				    <div class = "form-group dd">
				            <label for = "pass">电话</label>
				            <input class="form-control" type="text" name = "PhoneNumber" placeholder="电话">
				    		<span class='info email_put'>请输入您的电话号码。</span>
							<span class='info_error email_error'>请输入正确电话号码！</span>
							<span class='ok email_ok'></span>
							<ul class='auto_complete'>
								
							</ul>
				    </div>
				    <div class = "from-group">
				    	<select class="form-control" name="UserLocation">
				    		<option selected="selected">广州</option>
				    		<option>北京</option>
				    		<option>上海</option>
				    	</select>
				    </div>
				    <div class = "form-group">
				        <input type="submit" class="form-control" name="submit" value="注册" class="btn btn-default"/> 
				    </div>
				</form>
				
				<a href="login"><button class="form-control"> 已有账户，登录</button></a>
			</div>
		</div>
	</div>
</body>
</html>