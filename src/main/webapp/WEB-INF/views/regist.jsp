<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Regist</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">  
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
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
				    <div class = "form-group">
				            <label for = "name">用户名</label>
				            <input class="form-control" type="text" name="username" placeholder="用户名">
				    		<span id="nameError"></span>
				    </div>
				    <div class = "form-group">
				            <label for = "pass">密码</label>
				            <input class="form-control" type="password" name="password" placeholder="密码">
				    		<span id="passwordError"></span>
				    </div>
				    <div class = "form-group">
				            <label for = "pass">确认密码</label>
				            <input class="form-control" type="password" name="COnfirmPassword" placeholder="确认密码">
				    		<span id="ConfirmPasswordError"></span>
				    </div>
				    <div class = "form-group">
				            <label for = "pass">电话</label>
				            <input class="form-control" type="text" name = "PhoneNumber" placeholder="电话">
				    		<span id="PhoneError"></span>
				    </div>
				    <div class = "from-group">
				    	<select class="form-control" name="UserLocation">
				    		<option>广州</option>
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