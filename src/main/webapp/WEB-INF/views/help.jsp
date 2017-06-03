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
     <style>
	/* Custom Styles */
	    ul.nav-tabs{
	        width: 140px;
	        margin-top: 20px;
	        border-radius: 4px;
	        border: 1px solid #ddd;
	        box-shadow: 0 1px 4px rgba(0, 0, 0, 0.067);
	    }
	    ul.nav-tabs li{
	        margin: 0;
	        border-top: 1px solid #ddd;
	    }
	    ul.nav-tabs li:first-child{
	        border-top: none;
	    }
	    ul.nav-tabs li a{
	        margin: 0;
	        padding: 8px 16px;
	        border-radius: 0;
	    }
	    ul.nav-tabs li.active a, ul.nav-tabs li.active a:hover{
	        color: #fff;
	        background: #0088cc;
	        border: 1px solid #0088cc;
	    }
	    ul.nav-tabs li:first-child a{
	        border-radius: 4px 4px 0 0;
	    }
	    ul.nav-tabs li:last-child a{
	        border-radius: 0 0 4px 4px;
	    }
	    ul.nav-tabs.affix{
	        top: 30px; /* Set the top position of pinned element */
	    }
	</style>
</head>
<body data-spy="scroll" data-target="#myScrollspy">
    	<!-- 导航栏 -->
    <nav class = "nav navbar-default navbar-static-top" role = "navigation">
        	<div class="container-fluid">
	        	<div class="navbar-header">
	        		<ol class="breadcrumb">
	        			<li class="./index">Home</li>
	        			<li class="active">帮助文档</li>
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
    	<div class="row">
	        <div class="col-xs-3" id="myScrollspy">
	            <ul class="nav nav-tabs nav-stacked" data-spy="affix" data-offset-top="125">
	                <li class="active"><a href="#order">选座购票流程</a></li>
	                <li><a href="#get-tickets">取票流程</a></li>
	                <li><a href="#question">常见问题</a></li>
	                <li><a href="#contact">联系我们</a></li>
	            </ul>
	        </div>
        <div class="col-xs-9">
            <h2 id="order">选座购票流程</h2>
            	<p>1. 选择影院和影片</p>
				<p>2. 选择场次后进入座位图</p>
				<p>3. 选择座位</p>
				<p>4. 确认订单信息</p>
				<p>5. 选择支付方式并成功付款</p>
				<p>6. 获取取票密码</p>
				<p>7. 到影院自助取票机使用密码打印纸质电影票，并凭票入场</p>
            <hr>
            <h2 id="get-tickets">取票流程</h2>
            <p>1. 到电影院售票口凭码取票</p>
            <p>2. 选择对应平台的取票机，扫码或凭验证码取票。</p>
            <hr>
            <h2 id="question">常见问题</h2>
				<ol>
					<li>银行/支付宝/财付通已扣款，订单仍显示未付款
						<p>
						出现此问题，可能是银行/支付宝/财付通的数据没有即时传输至系统，请您不要担心，稍后刷新页面查看。
						如半小时后仍显示"未付款"，请先联系银行/支付宝/财付通客服，获取您扣款的交易号，然后致电客服101xxxxxx，我们会协助您解决。
						</p>
					</li>
					<li>收不到/找不到/误删影票短信怎么办？
						<p>
						请您登录账户，进入"个人中心"，找到您购买的影票订单，点击详情获得兑换码信息。
						</p>
					</li>
					<li>收不到订单信息怎么办？
						<p>
						请您排查是否是以下情况导致：
						</p>
						<p>a、您可能不是在本网站购买的订单，是在其他网站购买，请您尝试登录其他网站查看。</p>
						<p>b、您可能是使用其他账号购买，请您尝试登录其他账号查看。</p>
						<p>
						如通过以上排查仍未找到您的订单，请您致电客服101xxxxxx，我们会协助您解决。
						</p>
					</li>
					<li>支持哪些支付方式？
						<p>
						本网站支持如下4种支付方式：各大银行的储蓄卡和信用卡、支付宝、财付通、微信。
						</p>
					</li>
					<li>网页展示不全/点击某按钮无反应怎么办？
						<p>
						可能是您的网速或浏览器问题导致，建议您刷新页面，推荐Chrome浏览器尝试。
						</p>
					</li>
				</ol>  	
            <hr>
            <h2 id="contact">联系我们</h2>
            	<p>	客户服务</p>
            	<ul>
            		<li>24小时全国服务热线：020-84115000</li>
					<li>客户服务邮箱： MZ_KF@sysu.mail2.com</li>
					<li>公司地址：广州市番禹区大学城中山大学，510000</li>
            	</ul>
				<p>媒体垂询、公关传播</p>
				<ul>
					<li>联系邮箱：PR@sysu.mail2.com</li>
				</ul>
				<p>国内市场合作</p>
				<ul>
					<li>市场推广及品牌合作业务咨询：mz-marketing@sysu.mail2.com</li>
					<li>认证影院加盟：020-84112828（接听服务时间：周一至周日9:00-12:00，13:30-18:00，法定节假日除外）</li>
				</ul>
				<p>知识产权</p>
				<ul>
					<li>知识产权事务相关建议、举报、投诉：IPR@sysu.mail2.com</li>
				</ul>
				
				<p>加入我们</p>
				<ul>
					<li>人才加盟、就业合作：zhaopin@sysu.mail2.com</li>
				</ul>
				
				<p>反商业贿赂</p>
				<ul>
					<li>本公司严禁员工向业务合作伙伴索贿或收取佣金、回扣或其他私人利益。</li>
					<li>如发现任何不廉洁或舞弊行为，请举报至：lianzheng@sysu.mail2.com 。</li>
					<li>对有具体线索的举报，本公司内控合规部门将开展独立调查并为举报人保密。</li>
				</ul>
				<p>广州市烤茄子的小仙女有限公司</p>
            <hr>
        </div>
    </div>
    </div>
</body>    
</html>    