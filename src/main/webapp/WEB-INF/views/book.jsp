<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>   
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Book</title>
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
        			<li><a href="../movie/${id}">电影详情</a></li>
        			<li class="active">预订影票</li>
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
	
	<div class="row clearfix">
		<div class="col-md-2 column">
			<img src="../images/movie/${id}.jpg" alt="海报" class="img-responsive center-block">
		</div>
		<div class="col-md-10 column">
			<table class="table table-hover tabel-striped">
				<thead>
					<tr>
						<th>SId</th>
						<th>影院名</th>
						<th>放映厅</th>
						<th>价钱</th>
						<th>开始时间</th>
						<th>开始日期</th>
						<th>选座购票</th>
					</tr>
				</thead>
				<tbody>
					<sql:setDataSource var="database" driver="com.mysql.jdbc.Driver"
								url="jdbc:mysql://localhost:3306/tickets"
								user="root" password="root"/>
					<c:forEach var="schedule" items="${schedules}">
						<tr>
							<td><c:out value="${schedule.sId}"></c:out></td>
							
							<sql:query var="cinemas" dataSource="${database}">
								SELECT * from cinemaInfo where cId = ${schedule.cId};
							</sql:query>
							<c:forEach var="cinema" items="${cinemas.rows}">
								<td><c:out value="${cinema.cinemaName}"></c:out></td>
							</c:forEach><!-- 实际上只有一个 -->
							<td><c:out value="${schedule.hallName}"></c:out></td>
							<td><c:out value="${schedule.price}"></c:out></td>
							<td><c:out value="${schedule.startTime}"></c:out></td>
							<td><c:out value="${schedule.startDate}"></c:out></td>
							<td><a href="../choose_seat/${schedule.sId}"><button>选座购票</button></a></td><!-- 页面还没做 -->
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>