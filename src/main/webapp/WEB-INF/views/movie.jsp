<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Movie</title>
	<link rel="stylesheet" href="../css/bootstrap.min.css">  
	<script src="../js/jquery-3.2.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
</head>
<body>
	<!-- 导航栏 -->
	<nav class = "nav navbar-default navbar-static-top" role = "navigation">
        	<div class="container-fluid">
	        	<div class="navbar-header">
	        		<ol class="breadcrumb">
	        			<li><a href="../index">Home</a></li>
	        			<li class="active">电影详情</li>
	        		</ol>
	        	</div>
	        	<div>
	        		<ul class="nav navbar-nav">
	        			<li><a href="#">热映中</a></li>
	        			<li class="dropdown">
	        				<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
		        			广州   <span class="caret"></span>
			        		</a>
				        	<ul class="dropdown-menu" role="menu"><!-- 数据还没有 -->
				        		<li><a href="#">广州</a></li>
				        		<li><a href="#">北京</a></li>
				        		<li><a href="#">上海</a></li>
				        	</ul>
	        			</li>
	        			<li class="dropdown">
	        				<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
		        			选择影院   <span class="caret"></span>
			        		</a>
				        	<ul class="dropdown-menu" role="menu"><!-- 数据还没有 -->
				        		<li><a href="#">村头看电影</a></li>
				        		<li><a href="#">爱看不看</a></li>
				        		<li><a href="#">不看拉倒</a></li>
				        	</ul>
	        			</li>
	        		</ul>
	        	
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
	<!-- 主体 -->
	<div class="panel panel-default">
		<div class="panel-heading">
			<img src="../images/movie/row_poster.jpg" alt="海报" width="100%" class="img-responsive center-block">
		</div>
		<div class="panel-body">
			<div class="row clearfix">
				<div class="col-md-2 column">
					<img src="../images/movie/poster.jpg" alt="海报" class="img-responsive center-block">
				</div>
				<div class="col-md-4 column">
					<table class="table">
						<tbody>
							<tr>
								<td>导演</td>
								<td>eggplant</td>
							</tr>
							<tr>
								<td>演员</td>
								<td>eggplant</td>
							</tr>
							<tr>
								<td>电影类型</td>
								<td>eggplant</td>
							</tr>
							<tr>
								<td>时长</td>
								<td>1</td>
							</tr>
							<tr>
								<td>剧情简介</td>
								<td>命运石之门是主角部伦太郎（总称自己是凤凰园凶真）的一个坑货，为了完成自己的厨二病——制造时间机器而引发很多事情：
								在一次机缘巧合中，他改变了“时间线”，结果在一次又一次的尝试中，他通过D-mail（回到过去的信息）将时间线该到了不可逆的方向——害死了他善良可爱的青梅竹马。
								结果在将未来改回原样的途中，他又背信弃义的爱上了另一个女的，但是在最后他发现如果不改未来他青梅竹马会死，而改了未来那个女的会死。于是他无限的纠结之下竟然再一次抛弃了那个女的，
								去拯救他的青梅竹马。最后就在主角绝望的时候BUG的主角光环出现了，竟然在30年后的主角给三十年前的主角来了个“复活十字架”——他可以在不改变“时间线”的情况下救了那个女的，
								于是他最终用厨二病的方式解救了那个妹子。这个故事就没了，他与那个妹子还是没有在一起。不过我没看剧场版</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col-md-6 column panel">
					<div class="panel-heading">预告片</div>
					<div class="panel-body">
						<video width="100%" src="../video/stein.mp4" controls></video>
					</div>
					
				</div>
			</div>
		</div>
		<div class="panel-footer">
			<div class="panel panel-info" id = "comment">
				<div class="panel-heading">
					<h3 class="panel-title">
					<a data-toggle="collapse" data-parent="#comment" href="#comments">评论区</a>
					</h3>
				</div>
				<div class="panel-body" id ="comments">
					<ul>
						<li class="row clearfix">
							<div class="col-md-2 column">
								eggplant
							</div>
							<div class="col-md-1 column">
								<span class="glyphicon glyphicon-chevron-right"></span>
							</div>
							<div class="col-md-9 column">
								哇，瞬间爆炸
							</div>
						</li>
						<li class="row clearfix">
							<div class="col-md-2 column">
								eggplant2
							</div>
							<div class="col-md-1 column">
								<span class="glyphicon glyphicon-chevron-right"></span>
							</div>
							<div class="col-md-9 column">
								哇，我也瞬间爆炸
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>