<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Movie</title>
	<link rel="stylesheet" href="../css/bootstrap.min.css">  
	<script src="../js/jquery-3.2.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="http://api.html5media.info/1.1.8/html5media.min.js"></script>
	<style type="text/css">
		.movieInfo {
			position: relative;
			left: 10px;
			bottom: 1px;
			top: 200px;
			padding-bottom: 30px;
			font-size: 16px;
			font-weight: normal;
		}
		.main_poster {
			background: url("../images/movie/${movie.id}_big.jpg") no-repeat center top;
			width: 100%;
			height: 300px;
		}
	</style>
	<script src="../js/movie_comment_submit.js"></script> 
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
	        				<a href="../book/${id}"> 
		        			订票   <span class="glyphicon glyphicon-barcode"></span>
		        			</a>
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
			<div  class="main_poster">
				<div class="movieInfo">
					<div class="movieName">
						<c:out value="${movie.movie_name}"></c:out>
					</div>
					<br/>
					<div class="one_word">
						<c:out value="“${movie.movie_intro}”"></c:out>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="row clearfix">
				<div class="col-md-2 column">
					<img src="../images/movie/${movie.id}.jpg" alt="海报" class="img-responsive center-block">
				</div>
				<div class="col-md-4 column">
					<table class="table">
						<tbody>
							<tr>
								<td>导演</td>
								<td><c:out value="${movie.movie_director}"></c:out></td>
							</tr>
							<tr>
								<td>演员</td>
								<td><c:out value="${movie.main_actors}"></c:out></td>
							</tr>
							<tr>
								<td>电影类型</td>
								<td><c:out value="${movie.movie_style}"></c:out></td>
							</tr>
							<tr>
								<td>时长</td>
								<td><c:out value="${movie.movie_span}"></c:out></td>
							</tr>
							<tr>
								<td>剧情简介</td>
								<td><c:out value="${movie.movie_description}"></c:out></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col-md-6 column panel">
					<div class="panel-heading">预告片</div>
					<div class="panel-body">
						<video width="100%" src="../video/${movie.id}.mp4" controls></video>
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
					<div class="pull-right form-inline">
						<input id="comment_content" class="form-control" name = "comment" type="text" placeholder="我也来水一波"/>
						<button class="btn btndefault" id="add_comment" onclick="ajax_submit('${id}')">提交</button>
					</div>
				</div>
				<div class="panel-body" id ="comments">
					<ul class="list-group"  id="comment_field">
						<c:forEach var="comment" items="${comments}">
							<li class="row clearfix list-group-item"  id="${comment.commentId}">
								<div class="col-md-2 column">
									${comment.userName}
								</div>
								<div class="col-md-1 column">
									<span class="glyphicon glyphicon-chevron-right"></span>
								</div>
								<div class="col-md-8 column">
									${comment.commentText}
								</div>
								<div class="col-md-1 column">
									<c:if test="${comment.userName == name}">
										<button onclick="delete_comment('${comment.commentId}', '${id}')">
											<span class="glyphicon glyphicon-trash"></span>
										</button>
									</c:if>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>