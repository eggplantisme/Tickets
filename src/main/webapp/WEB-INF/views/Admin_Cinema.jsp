<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Admin-Cinema</title>
	<c:choose>
		<c:when test="${cinema != null}">
			<link rel="stylesheet" href="../css/bootstrap.min.css">  
			<script src="../js/jquery-3.2.1.min.js"></script>
			<script src="../js/bootstrap.min.js"></script>
		</c:when>
		<c:otherwise>
			<link rel="stylesheet" href="./css/bootstrap.min.css">  
			<script src="./js/jquery-3.2.1.min.js"></script>
			<script src="./js/bootstrap.min.js"></script>
		</c:otherwise>
	</c:choose>
</head>
<body>
	<form role="form" method="post">
		<c:choose>
			<c:when test="${cinema != null}">
			  <div class="form-group">
			    <label for="name">影院名称</label>
			    <input type="text" class="form-control" name="cinemaName" placeholder="请输入名称" value="${cinema.cinemaName}">
			  </div>
			  <div class="form-group">
			    <label for="name">影院位置</label>
			    <input type="text" class="form-control" name="cinemaLocation" placeholder="请输入名称" value="${cinema.cinemaLocation}">
			  </div>
			  <div class="form-group">
			    <label for="name">影院电话</label>
			    <input type="text" class="form-control" name="cinemaPhoneNumber" placeholder="请输入名称" value="${cinema.cinemaPhoneNumber}">
			  </div>
			  <button type="submit" class="btn btn-default">提交</button>
			</c:when>
			<c:otherwise>
				<div class="form-group">
			    <label for="name">影院名称</label>
			    <input type="text" class="form-control" name="cinemaName" placeholder="请输入名称">
			  </div>
			  <div class="form-group">
			    <label for="name">影院位置</label>
			    <input type="text" class="form-control" name="cinemaLocation" placeholder="请输入名称">
			  </div>
			  <div class="form-group">
			    <label for="name">影院电话</label>
			    <input type="text" class="form-control" name="cinemaPhoneNumber" placeholder="请输入名称">
			  </div>
			  <button type="submit" class="btn btn-default">提交</button>
			</c:otherwise>
		</c:choose>
		
	</form>
</body>
</html>