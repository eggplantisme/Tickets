<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Order</title>
	<link rel="icon" href="../images/icon.jpg">
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
        			<li><a href="../movie/${movieId}">电影详情</a></li>
        			<li><a href="../book/${movieId}">预订影票</a></li>
        			<li><a href="../choose_seat/${sId}">选择座位</a></li>
        			<li class="active">生成订单</li>
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
				                    <li><a href="../info">个人中心</a></li><!-- TODO 订单页面 -->
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
	
	<div class="row clearfix">
		<div class="col-md-2 column">
			<img src="../images/movie/${movieId}.jpg" alt="海报" class="img-responsive center-block">
		</div>
		<div class="col-md-10 column">
			<sql:setDataSource var="database" driver="com.mysql.jdbc.Driver"
								url="jdbc:mysql://localhost:3306/tickets"
								user="root" password="root"/>
			<table class="table">
				<sql:query var="movies" dataSource="${database}">
					SELECT * from movieInfo where mId = ${movieId};
				</sql:query>
				<c:forEach var="movie" items="${movies.rows}">
					<tr><td><c:out value="${movie.movieName} "></c:out></td><td></td><td></td><td></td></tr>
				</c:forEach><!-- 实际上只有一个 -->
				
				<sql:query var="schedules" dataSource="${database}">
					SELECT * from scheduleInfo where sId = ${sId};
				</sql:query>
				<c:forEach var="schedule" items="${schedules.rows}">
					<sql:query var="cinemas" dataSource="${database}">
						SELECT * from cinemaInfo where cId = ${schedule.cId};
					</sql:query>
					<c:forEach var="cinema" items="${cinemas.rows}">
						<tr>
							<td><c:out value="影院名:${cinema.cinemaName}"></c:out></td>
							<td><c:out value="影院地址:${cinema.cinemaLocation}"></c:out></td>
							<td><c:out value="影院联系电话:${cinema.cinemaPhoneNumber}"></c:out></td>
						</tr>
					</c:forEach><!-- 实际上只有一个 -->
					<tr><td><c:out value="放映厅:${schedule.hallName}"></c:out></td><td></td><td></td></tr>
					
					<tr><td><c:out value="观影时间:${schedule.startDate}  ${schedule.startTime}"></c:out></td><td></td><td></td><td></td></tr>
				</c:forEach><!-- 实际上只有一个 -->
				
				
				<sql:query var="seats" dataSource="${database}">
					SELECT * from seatInfo where seatId = ${sessionScope.seatId};
				</sql:query>
				<c:forEach var="seat" items="${seats.rows}">
					<tr><td><c:out value="${seat.seatRow} 行	${seat.seatColumn} 列"></c:out></td><td></td><td></td><td></td></tr>
				</c:forEach><!-- 实际上只有一个 -->
				<tr><td><c:out value="价格:${order.orderPrice}"></c:out></td><td></td><td></td><td></td></tr>
				<tr><td><c:out value="订单号:${order.oId}"></c:out></td><td></td><td></td><td></td></tr>
				<tr><td><c:out value="订单状态:${order.status}"></c:out></td><td></td><td></td><td></td></tr>
			</table>
		</div>
	</div>
</body>
</html>