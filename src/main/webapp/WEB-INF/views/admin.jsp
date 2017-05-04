<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Admin</title>
	<link rel="stylesheet" href="./css/bootstrap.min.css">  
	<script src="./js/jquery-3.2.1.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
</head>
<body>
	<!-- 导航栏 -->
	<nav class = "nav navbar-default navbar-static-top" role = "navigation">
    	<div class="container-fluid">
	     	<div class="navbar-header">
	     		<ol class="breadcrumb">
	     			<li><a href="./index">Home</a></li>
	     			<li class="active">管理员</li>
	     		</ol>
	     	</div>
	     	<div>
	     		<ul class="nav nav-tabs">
	     			<li class="active">
						<a href="#movie" data-toggle="tab">
							 电影
						</a>
					</li>
					<li>
						<a href="#cinema" data-toggle="tab">
							 电影院
						</a>
					</li>
					<li>
						<a href="#schedule" data-toggle="tab">
							 排片
						</a>
					</li>
	     		</ul>
	     	</div>
       		<ul class="nav navbar-nav navbar-right">
       			<li>
       				<a href="./admin_movie">
						<span class="glyphicon glyphicon-film"></span> 添加电影
					</a>
       			</li>
       			<li>
       				<a href="./admin_cinema">
						<span class="glyphicon glyphicon-home"></span> 添加影院
					</a>
       			</li>
       			<li>
       				<a href="./admin_schedule">
						 <span class="glyphicon glyphicon-list-alt"></span> 添加排片
					</a>
       			</li>
       		</ul>
	     </div>
    </nav>
    
    <div class="tab-content">
    	<!-- 电影 -->
    	<div class="tab-pane fade in active" id="movie">
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
								<div class="col-md-10 column">
									<div>
										<c:out value="${movie.movie_name}"></c:out>
									</div>
									<br/>
									<div>
										<c:out value="“${movie.movie_intro}”"></c:out>
									</div>
									<br/>
									<div>
										<c:out value="“${movie.id}”"></c:out>
									</div>
								</div>
								<div class="col-md-1 column">
									<form action="./admin/movie_delete/${movie.id}" method="post">
										<input type = "hidden" name = "_method" value ="DELETE"/>
										<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-trash"></span></button>
									</form>
									<form action="./admin_movie/${movie.id}">
										<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-cog"></span></button>
									</form>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- 影院 -->
		<div class="tab-pane fade" id="cinema">
    		<table class="table">
    			<thead>
    				<tr>
    					<th>影院名称</th>
    					<th>影院位置</th>
    					<th>影院联系方式</th>
    					<th>影院ID</th>
    					<th>操作</th>
    				</tr>
    			</thead>
				<tbody>
		    		<c:forEach var="cinema" items="${cinemas}">
						<tr>
							<td>
								<c:out value="${cinema.cinemaName}"></c:out>
							</td>
							<td>
								<c:out value="${cinema.cinemaLocation}"></c:out>
							</td>
							<td>
								<c:out value="${cinema.cinemaPhoneNumber}"></c:out>
							</td>
							<td>
								<c:out value="${cinema.cId}"></c:out>
							</td>
							<td>
								<form action="./admin/cinema_delete/${cinema.cId}" method="post">
									<input type = "hidden" name = "_method" value ="DELETE"/>
									<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-trash"></span></button>
								</form>
								<form action="./admin_cinema/${cinema.cId}">
									<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-cog"></span></button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- 排片 -->
		<div class="tab-pane fade" id="schedule">
    		<table class="table">
    			<thead>
    				<tr>
    					<th>ID</th>
    					<th>电影ID</th>
    					<th>影院ID</th>
    					<th>价格</th>
    					<th>开始时间</th>
    					<th>放映厅名字</th>
    					<th>option</th>
    				</tr>
    			</thead>
				<tbody>
		    		<c:forEach var="schedule" items="${schedules}">
						<tr>
							<td>
								<c:out value="${schedule.sId}"></c:out>
							</td>
							<td>
								<c:out value="${schedule.mId}"></c:out>
							</td>
							<td>
								<c:out value="${schedule.cId}"></c:out>
							</td>
							<td>
								<c:out value="${schedule.price}"></c:out>
							</td>
							<td>
								<c:out value="${schedule.startDate} : ${schedule.startTime}"></c:out>
							</td>
							<td>
								<c:out value="${schedule.hallName}"></c:out>
							</td>
							<td>
								<form action="./admin/schedule_delete/${schedule.sId}" method="post">
									<input type = "hidden" name = "_method" value ="DELETE"/>
									<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-trash"></span></button>
								</form>
								<form action="./admin_schedule/${schedule.sId}">
									<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-cog"></span></button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
    </div>
</body>
</html>