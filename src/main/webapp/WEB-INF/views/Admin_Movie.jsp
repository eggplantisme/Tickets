<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Admin-Movie</title>
	
	<c:choose>
			<c:when test="${movie != null}">
				<link rel="icon" href="../images/icon.jpg">
				<link rel="stylesheet" href="../css/bootstrap.min.css">  
				<script src="../js/jquery-3.2.1.min.js"></script>
				<script src="../js/bootstrap.min.js"></script>
			</c:when>
			<c:otherwise>
				<link rel="icon" href="./images/icon.jpg">
				<link rel="stylesheet" href="./css/bootstrap.min.css">  
				<script src="./js/jquery-3.2.1.min.js"></script>
				<script src="./js/bootstrap.min.js"></script>
			</c:otherwise>
	</c:choose>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.username != 'eggplant'}">
			只有管理员才可以使用此界面。
		</c:when>
		<c:otherwise>
			<div class= "well">
					<c:choose>
						<c:when test="${movie != null}">
							<form method="post">
							<div class="col-md-6 column">
								  <div class="form-group">
								    <label for="name">电影名称</label>
								    <input type="text" class="form-control" name="movie_name" placeholder="请输入名称" value="${movie.movie_name}">
								  </div>
								  <div class="form-group">
								    <label for="name">主要演员</label>
								    <input type="text" class="form-control" name="main_actors" placeholder="请输入演员" value="${movie.movie_name}">
								  </div>
								  <div class="form-group">
								    <label for="name">详细描述</label>
								    <textarea class="form-control" name="movie_description" placeholder="请输入描述">${movie.movie_description}</textarea>
								  </div>
								  <div class="form-group">
								    <label for="name">一句话描述</label>
								    <input type="text" class="form-control" name="movie_intro" placeholder="请输入一句话" value="${movie.movie_intro}">
								  </div>
								  <div class="form-group">
								    <label for="name">导演</label>
								    <input type="text" class="form-control" name="movie_director" placeholder="请输入导演" value="${movie.movie_director}">
								  </div>
								  <div class="form-group">
								    <label for="name">电影类型</label>
								    <input type="text" class="form-control" name="movie_style" placeholder="请输入电影类型" value="${movie.movie_style}">
								  </div>
								  <div class="form-group">
								    <label for="name">电影时长</label>
								    <input type="text" class="form-control" name="movie_span" placeholder="请输入电影时长" value="${movie.movie_span}">
								  </div>
							 </div>
							 <div class="col-md-6 column">
								  <div class="form-group">
								    <label for="name">电影上映时间</label>
								    <input type="date" class="form-control" name="On_time" value="${movie.on_time}">
								  </div>
								  <div class="form-group">
								    <label for="name">电影下线时间</label>
								    <input type="date" class="form-control" name="End_time" value="${movie.end_time}">
								  </div>
								  <button type="submit" class="btn btn-default">提交</button>
							 </div>
							 </form>
						</c:when>
						<c:otherwise>
							<form method="post" enctype="multipart/form-data">
							<div class="col-md-6 column">
								  <div class="form-group">
								    <label for="name">电影名称</label>
								    <input type="text" class="form-control" name="movie_name" placeholder="请输入名称">
								  </div>
								  <div class="form-group">
								    <label for="name">主要演员</label>
								    <input type="text" class="form-control" name="main_actors" placeholder="请输入演员">
								  </div>
								  <div class="form-group">
								    <label for="name">详细描述</label>
								    <textarea class="form-control" name="movie_description" placeholder="请输入描述"></textarea>
								  </div>
								  <div class="form-group">
								    <label for="name">一句话描述</label>
								    <input type="text" class="form-control" name="movie_intro" placeholder="请输入一句话">
								  </div>
								  <div class="form-group">
								    <label for="name">导演</label>
								    <input type="text" class="form-control" name="movie_director" placeholder="请输入导演">
								  </div>
								  <div class="form-group">
								    <label for="name">电影类型</label>
								    <input type="text" class="form-control" name="movie_style" placeholder="请输入电影类型">
								  </div>
								  <div class="form-group">
								    <label for="name">电影时长</label>
								    <input type="text" class="form-control" name="movie_span" placeholder="请输入电影时长">
								  </div>
							 </div>
							 <div class="col-md-6 column">
								  <div class="form-group">
								    <label for="name">电影上映时间</label>
								    <input type="date" class="form-control" name="On_time">
								  </div>
								  <div class="form-group">
								    <label for="name">电影下线时间</label>
								    <input type="date" class="form-control" name="End_time">
								  </div>
								  <div class="form-group">
								    <label for="name">横版海报</label>
								    <input type="file" class="form-control" name="Post_big">
								  </div>
								  <div class="form-group">
								    <label for="name">海报</label>
								    <input type="file" class="form-control" name="Post">
								  </div>
								  <div class="form-group">
								    <label for="name">预告片</label>
								    <input type="file" class="form-control" name="Trailer">
								  </div>
								  <button type="submit" class="btn btn-default">提交</button>
							 </div>
							 </form>
						</c:otherwise>
					</c:choose>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>