<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login</title>
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
				<form name="login_form" action="login.do" method="post">
				    <div class="well text-center">欢迎登录</div>
				    <div class = "form-group">
				            <label for = "name">用户名</label>
				            <input class="form-control" type="text" name="username" placeholder="用户名">
				    </div>
				    <div class = "form-group">
				            <label for = "pass">密码</label>
				            <input class="form-control" type="password" name="password" placeholder="密码">
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