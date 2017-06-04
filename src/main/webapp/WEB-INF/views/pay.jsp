<%@page import="bean.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>个人中心</title>
	<link rel="icon" href="images/icon.jpg">
	<link rel="stylesheet" href="css/bootstrap.min.css">  
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/info.js" charset="UTF-8"></script>
</head>
<body>
	<!-- 导航栏 -->
        <nav class = "nav navbar-default navbar-static-top" role = "navigation">
        	<div class="container-fluid">
	        	<div class="navbar-header">
	        		<ol class="breadcrumb">
	        			<li><a href="./index">Home</a></li>
	        			<li class="active">支付</li>
	        		</ol>
	        	</div>
	        	<div>
	        		<ul class="nav navbar-nav navbar-right">
	        			<c:set var="name" value="${name}"/>
	        			<c:choose>
	        				<c:when test="${name != null}">
	        					<li>
	        						<a href="#" id = "welcome">
	        							Welcome ${name}
	        						</a>
	        					</li>
	        				</c:when>
	        				<c:otherwise>
			        			<li><a href="./login"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
			        			<li><a href="./regist"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
	        				</c:otherwise>
	        			</c:choose>
	        		</ul>
	        	</div>
	        </div>
        </nav>
		<div class="container">
			<p>请扫描二维码</p>
			<img src="./images/pay.jpg"></img>
		</div>
</body>
</html>