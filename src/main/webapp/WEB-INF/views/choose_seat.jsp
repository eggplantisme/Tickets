<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Choose_Seat</title>
	<link rel="stylesheet" href="../css/bootstrap.min.css">
	<script src="../js/jquery-3.2.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/choose_seat.js"></script>
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
        			<li class="active">选择座位</li>
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
			<img src="../images/movie/${movieId}.jpg" alt="海报" class="img-responsive center-block">
		</div>
		<div class="col-md-10 column">
			<sql:setDataSource var="database" driver="com.mysql.jdbc.Driver"
								url="jdbc:mysql://localhost:3306/tickets"
								user="root" password="root"/>
			<div class="panel panel-default">
				<!-- 之前的订单信息，除了座位之外的 -->
			    <div class="panel-heading">
			       <sql:query var="schedules" dataSource="${database}">
						SELECT * from scheduleInfo where sId = ${sId};
				   </sql:query>
				   <c:forEach var="schedule" items="${schedules.rows}">
				   		<sql:query var="cinemas" dataSource="${database}">
							SELECT * from cinemaInfo where cId = ${schedule.cId};
						</sql:query>
						<c:forEach var="cinema" items="${cinemas.rows}">
							<td><c:out value="${cinema.cinemaName} "></c:out></td>
						</c:forEach><!-- 实际上只有一个 -->
						<c:out value="${schedule.hallName}"></c:out>
						<c:out value="${schedule.startTime}"></c:out>
						<c:out value="${schedule.startDate}"></c:out>
					</c:forEach><!-- 实际上只有一个 -->
					
					<div class="pull-right">
						未选择的座位
						<button type="button" class="btn btn-default btn-sm" name="${i}_${j}">
				          	<span class="glyphicon glyphicon-inbox"></span>
				        </button>
						已选择的座位
						<button type="button" class="btn btn-danger btn-sm" name="${i}_${j}">
				          	<span class="glyphicon glyphicon-inbox"></span>
				        </button>
				      	 您选择的座位
						<button type="button" class="btn btn-success btn-sm" name="${i}_${j}">
				          	<span class="glyphicon glyphicon-inbox"></span>
				        </button>
					</div>
			    </div>
			    <div class="panel-body">
			    	<div class="center-block" style="text-align:center; width:410px;border-color: grey; border-top-style:solid; border-top-width:10px ;border-radius: 400px;">
			    		银幕
			    	</div>
			    	<div class="center-block" style="width:410px;background-color:#ccc;">
				    	<ul class="list-group">
					    	<c:forEach var="i" begin="1" end="10"><!-- row -->
				    			<li class="list-group-item">
					    			<c:forEach var="j" begin="1" end="10"><!--column -->
					    				<c:set var="used" value="0"/>
					    				<c:forEach var="seat" items="${seats}">
											<c:if test="${i == seat.seatRow && j == seat.seatColumn}">
												<c:set var="used" value="1"/>
											</c:if>
										</c:forEach><!-- 判断是否已经被占用了 -->
					    				<c:choose>
					    					<c:when test="${used ==  1}">
					    						<button type="button" class="btn btn-danger btn-sm" disabled name="${i}_${j}">
										          	<span class="glyphicon glyphicon-inbox"></span>
										        </button>
					    					</c:when>
					    					<c:otherwise>
					    						<button type="button" class="btn btn-default btn-sm seats" name="${i}_${j}">
										          	<span class="glyphicon glyphicon-inbox"></span>
										        </button>
					    					</c:otherwise>
					    				</c:choose>
					    			</c:forEach>
				    			</li>
					    	</c:forEach>
				    	</ul>
			    	</div>
				</div>
				
				<div class="panel-footer">
					<button id="choose_seat_submit" class="btn btn-primary center-block" onclick="choose_seat('${sId}', ${name == null ? 0 : 1})">我选好了</button>
				</div>
			</div>
		
		</div>
	</div>
</body>
</html>