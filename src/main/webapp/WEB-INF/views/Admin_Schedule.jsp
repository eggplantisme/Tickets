<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Admin-Schedule</title>
	
	<c:choose>
		<c:when test="${schedule != null}">
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
			<div class="container">
				<div class= "well">
					<form method="post">
						<c:choose>
							<c:when test="${schedule != null}">
								 <div class="form-group">
								    <label for="name">电影Id</label>
								    <input type="text" class="form-control" name="mId" placeholder="请输入movieId" value="${schedule.mId}">
								 </div>
								 <div class="form-group">
								    <label for="name">CinemaId</label>
								    <input type="text" class="form-control" name="cId" placeholder="请输入CinemaId" value="${schedule.cId}">
								 </div>
								 <div class="form-group">
								    <label for="name">price</label>
								    <input type="text" class="form-control" name="price" placeholder="请输入Price" value = "${schedule.price}">
								 </div>
								 <div class="form-group">
								    <label for="name">Hall</label>
								    <input type="text" class="form-control" name="hallName" placeholder="请输入HallName" value="${schedule.hallName}">
								 </div>
								 <div class="form-group">
								    <label for="name">Start Date</label>
								    <input type="date" class="form-control" name="startDate" value="${schedule.startDate}">
								 </div>
								 <div class="form-group">
								    <label for="name">Start Time</label>
								    <input type="time" class="form-control" name="startTime" value="${schedule.startTime}">
								 </div>
							</c:when>
							<c:otherwise>
								<div class="form-group">
								    <label for="name">电影Id</label>
								    <input type="text" class="form-control" name="mId" placeholder="请输入movieId">
								 </div>
								 <div class="form-group">
								    <label for="name">CinemaId</label>
								    <input type="text" class="form-control" name="cId" placeholder="请输入CinemaId">
								 </div>
								 <div class="form-group">
								    <label for="name">price</label>
								    <input type="text" class="form-control" name="price" placeholder="请输入Price">
								 </div>
								 <div class="form-group">
								    <label for="name">Hall</label>
								    <input type="text" class="form-control" name="hallName" placeholder="请输入HallName">
								 </div>
								 <div class="form-group">
								    <label for="name">Start Date</label>
								    <input type="date" class="form-control" name="startDate">
								 </div>
								 <div class="form-group">
								    <label for="name">Start Time</label>
								    <input type="time" class="form-control" name="startTime" value="13:59:00">
								 </div>
							</c:otherwise>
						</c:choose>
						 
						 <button type="submit" class="btn btn-default">提交</button>
					</form>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>