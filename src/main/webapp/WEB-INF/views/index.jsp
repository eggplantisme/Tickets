<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>    
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="images/icon.jpg">
        <link rel="stylesheet" href="css/bootstrap.min.css">  
		<script src="js/jquery-3.2.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
        <title>Tickets</title>
        <style type="text/css">
        	.col-center-block {
        		width: 100%;
        		height: 0;
        		padding-bottom: 33.98%;
        		
        	}
        </style>
    </head>
    <body>
    	<!-- 导航栏 -->
        <nav class = "nav navbar-default navbar-static-top" role = "navigation">
        	<div class="container-fluid">
	        	<div class="navbar-header">
	        		<ol class="breadcrumb">
	        			<li class="active">Home</li>
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
			        			<li><a href="./login"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
			        			<li><a href="./regist"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
	        				</c:otherwise>
	        			</c:choose>
	        		</ul>
	        	</div>
	        </div>
        </nav>
        
        <div class="container">
        	<!-- 页面第二行 -->
	        <div class="well well-sm">
	        	<div class="row">
		        	<div class="col-sm-6 col-md-3">
		        		<img alt="Welcome" src="images/eggplant.jpg" class="image-rounded img-responsive" width="100px"/>
		        	</div>
		        	
		        	<div class="col-sm-6 col-md-3 btn-group">
		        		<button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
		        			广州   <span class="caret"></span>
		        		</button>
			        	<ul class="dropdown-menu" role="menu"><!-- 数据还没有 -->
			        		<li><a href="#">广州</a></li>
			        		<li><a href="#">北京</a></li>
			        		<li><a href="#">上海</a></li>
			        	</ul>
		        	</div>
		        	<ul class="nav nav-pills col-sm-6 col-md-3">
		        		<li class="active" data-toogle="tab"><a href="#">Home</a></li>
		        		<li><a href="#" data-toogle="tab">影片</a></li><!-- 页面还没有 -->
		        		<li><a href="#" data-toogle="tab">影院</a></li><!-- 页面还没有 -->
		        	</ul>
	        	</div>
	        </div>
        	<!-- 页面第三行 -->
        	<div class="btn-group">
        		<button type="button" class="btn btn-default">正在热映(数量)</button><!-- 数量要后台给数据 -->
        		<button type="button" class="btn btn-default">即将热映(数量)</button><!-- 数量要后台给数据 -->
        		<a href="./movies"><button type="button" class="btn btn-default">查看全部<span class="glyphicon glyphicon-chevron-right"></span></button></a>
        	</div>
        	<!-- 轮播-->
        	<div id = "carousel_for_main" class="carousel slide row">
        		<ol class="carousel-indicators">
        			<li data-target="#carousel_for_main" data-slide-to="0" class="active"></li>
        			<li data-target="#carousel_for_main" data-slide-to="1"></li>
        			<li data-target="#carousel_for_main" data-slide-to="2"></li>
        			<li data-target="#carousel_for_main" data-slide-to="3"></li>
        			<li data-target="#carousel_for_main" data-slide-to="4"></li>
        		</ol>
        		<div class="carousel-inner">
        			<div class="item active col-center-block">
        				<img src="images/miku.jpg" alt="First slide" class="img-responsive center-block">
        			</div>
        			<div class="item col-center-block">
        				<img src="images/raligun.jpg" alt="Second slide" class="img-responsive center-block">
        			</div>
        			<div class="item col-center-block">
        				<img src="images/six-flower.jpg" alt="Third slide" class="img-responsive center-block">
        			</div>
        			<div class="item col-center-block">
        				<a href="./movie/1"><img src="images/zyl.jpg" alt="Four slide" class="img-responsive center-block"></a>
        			</div>
        			<div class="item col-center-block">
        				<img src="images/夏娜.jpg" alt="Five slide" class="img-responsive center-block">
        			</div>
        		</div>
        		<a class="carousel-control left" href="#carousel_for_main" data-slide="prev">&lsaquo;</a>
        		<a class="carousel-control right" href="#carousel_for_main" data-slide="next">&rsaquo;</a>
        	</div>
        	<!-- 三个表格 -->
        	<div class="well well-lg">
        		<div class="row">
	        		<div class="col-xs-6 col-sm-4">
		        		<table class="table table-hover table-striped">
		        			<thead>
		        				<tr><th>票房排行榜</th></tr>
		        			</thead>
		        			<tbody>
		        				<tr><td><a href="./movie/1">Stein</a></td></tr>
		        			</tbody>
		        			<tbody>
		        				<tr><td>ice</td></tr>
		        			</tbody>
		        			<tbody>
		        				<tr><td>fire</td></tr>
		        			</tbody>
		        			<tbody>
		        				<tr><td>magic</td></tr>
		        			</tbody>
		        			<tbody>
		        				<tr><td>happy</td></tr>
		        			</tbody>
		        		</table>
		        	</div>
		        	<div class="col-xs-6 col-sm-4">
		        		<table class="table table-hover table-striped col-xs-6 col-sm-4">
		        			<thead>
		        				<tr><th>为您推荐的影院</th></tr>
		        			</thead>
		        			<tbody>
		        				<tr><td>Giacomo</td></tr>
		        			</tbody>
		        			<tbody>
		        				<tr><td>ice</td></tr>
		        			</tbody>
		        			<tbody>
		        				<tr><td>fire</td></tr>
		        			</tbody>
		        			<tbody>
		        				<tr><td>magic</td></tr>
		        			</tbody>
		        			<tbody>
		        				<tr><td>happy</td></tr>
		        			</tbody>
		        		</table>
	        		</div>
	        		<div class="col-xs-6 col-sm-4">
		        		<table class="table table-hover table-striped col-xs-6 col-sm-4">
		        			<thead>
		        				<tr><th>新片观影指南</th></tr>
		        			</thead>
		        			<tbody>
		        				<tr><td>Giacomo</td></tr>
		        			</tbody>
		        			<tbody>
		        				<tr><td>ice</td></tr>
		        			</tbody>
		        			<tbody>
		        				<tr><td>fire</td></tr>
		        			</tbody>
		        			<tbody>
		        				<tr><td>magic</td></tr>
		        			</tbody>
		        			<tbody>
		        				<tr><td>happy</td></tr>
		        			</tbody>
		        		</table>
	        		</div>
        		</div>
        	</div>
        	<!-- 页底导航 -->
        </div>
        
        <nav class = "nav navbar-default navbar-static-bottom" role = "navigation">
        	<div class="container-fluid">
        		<div class="row" align="center">
        			<div class="col-md-5"></div>
	        		<ul class="nav navbar-nav col-md-5">
		        			<li><a href="#"> 选座购票流程</a></li>
		        			<li><a href="#"> 取票流程</a></li>
		        			<li><a href="#"> 常见问题</a></li>
		        			<li><a href="#"> 联系我们</a></li>
	        		</ul>
	        		<div class="col-md-5"></div>
	        	</div>
	        </div>
        </nav>
        
        
    </body>    
</html>    