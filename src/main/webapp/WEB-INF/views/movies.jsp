<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>   
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Movies</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">  
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
	<style>
		.expand {
			height: auto;
			min-height: 100px;
		}
	</style>
</head>
<body>
	<!--导航栏-->
	<nav class = "nav navbar-default navbar-static-top" role = "navigation">
        	<div class="container-fluid">
	        	<div class="navbar-header">
	        		<ol class="breadcrumb">
	        			<li><a href="./index">Home</a></li>
	        			<li class="active">Movie</li>
	        		</ol>
	        	</div>
	        	<div>
	        		<ul class="nav navbar-nav navbar-right">
	        			<c:set var="name" value="${name}"/>
	        			<c:choose>
	        				<c:when test="${name != null}">
	        					<li class="dropdown">
	        						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
	        							Welcome <c:out value="${name}"/>
	        						</a>
	        						<ul class="dropdown-menu">
					                    <li><a href="./info">个人中心</a></li><!-- TODO 订单页面 -->
					                </ul>
	        					</li>
	        				</c:when>
	        				<c:otherwise>
			        			<li><a href="../login"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
			        			<li><a href="../regist"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
	        				</c:otherwise>
	        			</c:choose>
	        		</ul>
	        	</div>
	        </div>
        </nav>

	<table class="table">
		<tbody>
			<c:forEach var="movie" items="${movies}">
				<tr>
					<td>
						<div class="col-md-1 column">
							<a href="./movie/${movie.id}">
								<img src="./images/movie/${movie.id}.jpg" alt="海报" class="img-responsive center-block">
							</a>
						</div>
						<div class="col-md-11 column">
							<div>
								<c:out value="${movie.movie_name}"></c:out>
							</div>
							<br/>
							<div>
								<c:out value="“${movie.movie_intro}”"></c:out>
							</div>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>