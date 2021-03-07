<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.teacher.model.*"%>

<%
MembersVO membersVO = (MembersVO) session.getAttribute("membersVO");
%>

<jsp:useBean id="teacherSvc" scope="page" class="com.teacher.model.TeacherService" />

<!DOCTYPE html>
<html lang="en">

<head>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js"
	type="text/javascript"></script>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Fusion - Bootstrap 4 Template</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/members/assets/css/bootstrap.min.css">
<!-- Icon -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/members/assets/fonts/line-icons.css">
<!-- Owl carousel -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/members/assets/css/owl.carousel.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/members/assets/css/owl.theme.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/members/assets/css/magnific-popup.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/members/assets/css/nivo-lightbox.css">
<!-- Animate -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/members/assets/css/animate.css">
<!-- Main Style -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/members/assets/css/main.css">
<!-- Responsive Style -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/members/assets/css/responsive.css">
<!-- Font Awesome
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">-->
</head>
<style>
img#nav_icon{
width:36px;
height:36px;
}

</style>

<body>
	<!-- Header Area wrapper Starts -->
	<header id="header-wrap">
		<!-- Navbar Start -->
		<nav
			class="navbar navbar-expand-md bg-inverse fixed-top scrolling-navbar">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<a
					href="<%=request.getContextPath()%>/front-end/members/indexV1.jsp"
					class="navbar-brand"><img src="assets/img/logo.svg" alt="">
					<div id="logo">Xducation</div> </a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarCollapse" aria-controls="navbarCollapse"
					aria-expanded="false" aria-label="Toggle navigation">
					<i class="lni-menu"></i>
				</button>
				<div class="collapse navbar-collapse" id="navbarCollapse">
					<ul class="navbar-nav mr-auto w-100 justify-content-end clearfix">
						<li class="nav-item"><a class="nav-link" href="#">
								進入搜尋&nbsp;<i class="lni-search"></i>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#">
								精選課程&nbsp;<i class="lni-leaf"></i>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#">
								名人講座&nbsp;<i class="lni-bulb"></i>
						</a></li>
						<c:if test="${teacherSvc.getStatus(membersVO.memno) eq '已通過'}">
						<li class="nav-item"><a class="nav-link" href="#">
								我要開課&nbsp;<i class="lni-bulb"></i>
						</a></li>
						</c:if>
						
						<c:if test="${not empty membersVO}">
							
							
							<li class="nav-item">
								<div class="dropdown" id="dropdown">
									<button class="btn btn-secondary dropdown-toggle" type="button"
										id="dropdownMenuButton" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false">
										${membersVO.memname}</button>
									<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
										<a class="dropdown-item"
											href="<%=request.getContextPath()%>/front-end/members/updateMembersV2.jsp">個人檔案</a>
											
										
										<c:if test="${teacherSvc.getStatus(membersVO.memno) eq '待審核'}">
										<a class="dropdown-item" onclick="status()" >老師檔案</a> 
											
										</c:if>
										<c:if test="${teacherSvc.getStatus(membersVO.memno) eq '已通過'}">
										<a class="dropdown-item"
											href="*">老師檔案</a> 
											
										</c:if>
											<c:if test="${teacherSvc.getStatus(membersVO.memno) eq '未通過'}">
										<a class="dropdown-item"
											href="*">老師檔案</a>
											
										</c:if>
										<c:if test="${not empty membersVO}">
										<a class="dropdown-item"
											href='<%=request.getContextPath()%>/members/members.do?action=signout'>會員登出</a> 
											
										</c:if>
											
											
										
											
											
											
									</div>
								</div>
								
							</li>
							
							</c:if>
							<c:if test="${not empty membersVO}">
							<c:if test="${teacherSvc.getStatus(membersVO.memno) eq '已通過'}">
							<img id="nav_icon" src='<%=request.getContextPath()%>/front-end/members/assets/img/teacher.svg'>
							</c:if>
							<c:if test="${teacherSvc.getStatus(membersVO.memno) eq '未通過'}">
							<img id="nav_icon" src='<%=request.getContextPath()%>/front-end/members/assets/img/students.svg'>
							</c:if>
							<c:if test="${teacherSvc.getStatus(membersVO.memno) eq '待審核'}">
							<img id="nav_icon" src='<%=request.getContextPath()%>/front-end/members/assets/img/students.svg'>
							</c:if>
							
						</c:if>

						<c:if test="${empty membersVO}">
							<li class="nav-item"><a class='nav-link'
								href='<%=request.getContextPath()%>/front-end/members/signIn.jsp'>我要登入&nbsp;<i
									class='lni-bulb'></i></a></li>
						</c:if>




					</ul>
				</div>
			</div>
		</nav>
		<!-- Navbar End -->
		<!-- Hero Area Start -->
		<div id="hero-area" class="hero-area-bg">
			<div class="container">
				<div class="row">
					<div class="col-lg-7 col-md-12 col-sm-12 col-xs-12">
						<div class="contents">
							<div class="about-wrapper wow fadeInLeft" data-wow-delay="0.3s">
								<h2 class="head-title">陪你成長的學習好夥伴</h2>
								<i class="lni-rocket"></i>&nbsp; Xducation - 最有趣的線上學習平台
							</div>
							<br>
							<div class="about-wrapper wow fadeInLeft" data-wow-delay="0.4s">
								<div id="search">
									<table id="searchbox">
										<tr>
											<td><i class="lni-search"></i></td>
											<td><input type="text" size="30"
												placeholder="&nbsp;&nbsp;今天想學什麼呢？"></td>
										</tr>
									</table>
								</div>
								<div class="header-button">
									<a href="#" class="btn btn-common">探索課程</a> <a
										href="<%=request.getContextPath()%>/front-end/members/addMembersV2.jsp"
										id="register" class="btn btn-border video-popup">我要註冊</a>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-5 col-md-12 col-sm-12 col-xs-12">
						<div class="about-wrapper wow fadeInRight" data-wow-delay="0.3s">
							<div class="introimg">
								<img class="img-fluid" src="<%=request.getContextPath()%>/front-end/members/assets/img/head/intro.png" alt="">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Hero Area End -->
	</header>
	<!-- Header Area wrapper End -->
	<!-- Services Section Start -->
	<section id="services" class="section-padding">
		<div class="container">
			<div class="section-header text-center">
				<h2 class="section-title wow fadeInDown" data-wow-delay="0.3s">
					<i class="lni-rocket"></i> 精選課程
				</h2>
				<div class="shape wow fadeInDown" data-wow-delay="0.3s"></div>
			</div>
			<div class="row">
				<!-- Services item1 -->
				<div class="col-md-6 col-lg-4 col-xs-12">
					<div class="services-item wow fadeInRight" data-wow-delay="0.3s">
						<div class="icon">
							<img src="<%=request.getContextPath()%>/front-end/members/assets/img/course/img1.jpg">
						</div>
						<div class="services-content">
							<i class="lni-star-filled"></i><i class="lni-star-filled"></i><i
								class="lni-star-filled"></i><i class="lni-star-filled"></i><i
								class="lni-star-filled"></i> &nbsp;101則評價
							<h3>
								<a href="#">職人紅酒地圖：Mimmo帶你探索世界十大酒莊</a>
							</h3>
							<p>課程共300分鐘</p>
							<p>同學累計9487人</p>
							<h5>NT$1680</h5>
						</div>
					</div>
				</div>
				<!-- Services item2 -->
				<div class="col-md-6 col-lg-4 col-xs-12">
					<div class="services-item wow fadeInRight" data-wow-delay="0.3s">
						<div class="icon">
							<img src="<%=request.getContextPath()%>/front-end/members/assets/img/course/img2.jpg">
						</div>
						<div class="services-content">
							<i class="lni-star-filled"></i><i class="lni-star-filled"></i><i
								class="lni-star-filled"></i><i class="lni-star-filled"></i><i
								class="lni-star-filled"></i> &nbsp;101則評價
							<h3>
								<a href="#">和Diane一起拈花惹草，打造個人生活風格</a>
							</h3>
							<p>課程共300分鐘</p>
							<p>同學累計9487人</p>
							<h5>NT$1680</h5>
						</div>
					</div>
				</div>
				<!-- Services item3 -->
				<div class="col-md-6 col-lg-4 col-xs-12">
					<div class="services-item wow fadeInRight" data-wow-delay="0.3s">
						<div class="icon">
							<img src="<%=request.getContextPath()%>/front-end/members/assets/img/course/img3.jpg">
						</div>
						<div class="services-content">
							<i class="lni-star-filled"></i><i class="lni-star-filled"></i><i
								class="lni-star-filled"></i><i class="lni-star-filled"></i><i
								class="lni-star-filled"></i> &nbsp;101則評價
							<h3>
								<a href="#">職人紅酒地圖：Mimmo帶你探索世界十大酒莊</a>
							</h3>
							<p>課程共300分鐘</p>
							<p>同學累計9487人</p>
							<h5>NT$1680</h5>
						</div>
					</div>
				</div>
				<!-- Services item4 -->
				<div class="col-md-6 col-lg-4 col-xs-12">
					<div class="services-item wow fadeInRight" data-wow-delay="0.3s">
						<div class="icon">
							<img src="<%=request.getContextPath()%>/front-end/members/assets/img/course/img3.jpg">
						</div>
						<div class="services-content">
							<i class="lni-star-filled"></i><i class="lni-star-filled"></i><i
								class="lni-star-filled"></i><i class="lni-star-filled"></i><i
								class="lni-star-filled"></i> &nbsp;101則評價
							<h3>
								<a href="#">職人紅酒地圖：Mimmo帶你探索世界十大酒莊</a>
							</h3>
							<p>課程共300分鐘</p>
							<p>同學累計9487人</p>
							<h5>NT$1680</h5>
						</div>
					</div>
				</div>
				<!-- Services item5 -->
				<div class="col-md-6 col-lg-4 col-xs-12">
					<div class="services-item wow fadeInRight" data-wow-delay="0.3s">
						<div class="icon">
							<img src="<%=request.getContextPath()%>/front-end/members/assets/img/course/img1.jpg">
						</div>
						<div class="services-content">
							<i class="lni-star-filled"></i><i class="lni-star-filled"></i><i
								class="lni-star-filled"></i><i class="lni-star-filled"></i><i
								class="lni-star-filled"></i> &nbsp;101則評價
							<h3>
								<a href="#">職人紅酒地圖：Mimmo帶你探索世界十大酒莊</a>
							</h3>
							<p>課程共300分鐘</p>
							<p>同學累計9487人</p>
							<h5>NT$1680</h5>
						</div>
					</div>
				</div>
				<!-- Services item6 -->
				<div class="col-md-6 col-lg-4 col-xs-12">
					<div class="services-item wow fadeInRight" data-wow-delay="0.3s">
						<div class="icon">
							<img src="<%=request.getContextPath()%>/front-end/members/assets/img/course/img2.jpg">
						</div>
						<div class="services-content">
							<i class="lni-star-filled"></i><i class="lni-star-filled"></i><i
								class="lni-star-filled"></i><i class="lni-star-filled"></i><i
								class="lni-star-filled"></i> &nbsp;101則評價
							<h3>
								<a href="#">職人紅酒地圖：Mimmo帶你探索世界十大酒莊</a>
							</h3>
							<p>課程共300分鐘</p>
							<p>同學累計9487人</p>
							<h5>NT$1680</h5>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Services Section End -->
	<!-- About Section start -->
	<div class="about-area section-padding bg-gray">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-12 col-xs-12 wow fadeInLeft"
					data-wow-delay="0.3s">
					<img class="img-fluid" src="<%=request.getContextPath()%>/front-end/members/assets/img/head/tchr.png" alt="">
				</div>
				<div class="col-lg-1 col-md-12 col-xs-12"></div>
				<div class="col-lg-5 col-md-12 col-xs-12 info">
					<div class="about-wrapper wow fadeInRight" data-wow-delay="0.3s">
						<div>
							<div class="site-heading">
								<h2 class="section-title">成為我們的老師</h2>
							</div>
							<div class="content">
								<p>
									V&emsp;分享您的才華<br> V&emsp;創造被動收入<br> V&emsp;自我激勵成長
								</p>
								<a
									href="<%=request.getContextPath()%>/front-end/teacher/teacherIndex.jsp"
									class="btn btn-common mt-3">我想瞭解</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- About Section End -->
	<!-- Features Section Start -->
	<section id="features" class="section-padding">
		<div class="container">
			<div class="section-header text-center">
				<h2 class="section-title wow fadeInDown" data-wow-delay="0.3s">
					<i class="lni-rocket"></i> 名人講座
				</h2>
				<div class="shape wow fadeInDown" data-wow-delay="0.3s"></div>
			</div>
			<div class="row">
				<div class="col-lg-7 col-md-12 col-sm-12 col-xs-12">
					<div class="content-left">
						<div class="box-item wow fadeInLeft" data-wow-delay="0.3s">
							<!-- 講座日期 -->
							<div class="lec_day">
								<span>28<br> <span
									style="font-size: 16px; font-weight: 500;">SEP</span>
								</span>
							</div>
							<div class="text">
								<h4>主廚講堂：跳進嘴裡的豐盛義大利</h4>
								<p>【ANTICO FORNO主廚吳治君】現場示範3道義式經典料理 X 義大利飲食文化</p>
							</div>
						</div>
						<div class="box-item wow fadeInLeft" data-wow-delay="0.6s">
							<div class="lec_day">
								<span>28<br> <span
									style="font-size: 16px; font-weight: 500;">SEP</span>
								</span>
							</div>
							<div class="text">
								<h4>主廚講堂：跳進嘴裡的豐盛義大利</h4>
								<p>【ANTICO FORNO主廚吳治君】現場示範3道義式經典料理 X 義大利飲食文化</p>
							</div>
						</div>
						<div class="box-item wow fadeInLeft" data-wow-delay="0.9s">
							<div class="lec_day">
								<span>28<br> <span
									style="font-size: 16px; font-weight: 500;">SEP</span>
								</span>
							</div>
							<div class="text">
								<h4>主廚講堂：跳進嘴裡的豐盛義大利</h4>
								<p>【ANTICO FORNO主廚吳治君】現場示範3道義式經典料理 X 義大利飲食文化</p>
							</div>
						</div>
					</div>
				</div>
				<!-- 講座區圖片 -->
				<div class="col-lg-5 col-md-12 col-sm-12 col-xs-12">
					<div class="show-box wow fadeInUp" data-wow-delay="0.3s">
						<img src="<%=request.getContextPath()%>/front-end/members/assets/img/head/calendar.gif" alt="">
					</div>
				</div>
			</div>
		</div>

	</section>
	<!-- Features Section End -->
	<!-- Testimonial Section Start -->
	<section id="testimonial" class="testimonial section-padding">
		<div class="container">
			<div class="section-header text-center">
				<h2 class="section-title wow fadeInDown" data-wow-delay="0.3s"
					style="color: #fff">
					<i class="lni-rocket"></i>&nbsp;好評大聲說
				</h2>
				<div class="shape wow fadeInDown" data-wow-delay="0.3s"></div>
			</div>
			<div class="row justify-content-center">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div id="testimonials" class="owl-carousel wow fadeInUp"
						data-wow-delay="1.2s">
						<div class="item">
							<div class="testimonial-item">
								<div class="img-thumb">
									<img src="<%=request.getContextPath()%>/front-end/members/assets/img/testimonial/img1.jpg" alt="">
								</div>
								<div class="info">
									<h2>
										<a href="#">David Smith</a>
									</h2>
									<h3>
										<a href="#">Creative Head</a>
									</h3>
								</div>
								<div class="content">
									<p class="description">Praesent cursus nulla non arcu
										tempor, ut egestas elit tempus. In ac ex fermentum, gravida
										felis nec, tincidunt ligula.</p>
									<div class="star-icon mt-3">
										<span><i class="lni-star-filled"></i></span> <span><i
											class="lni-star-filled"></i></span> <span><i
											class="lni-star-filled"></i></span> <span><i
											class="lni-star-filled"></i></span> <span><i
											class="lni-star-half"></i></span>
									</div>
								</div>
							</div>
						</div>
						<div class="item">
							<div class="testimonial-item">
								<div class="img-thumb">
									<img src="<%=request.getContextPath()%>/front-end/members/assets/img/testimonial/img2.jpg" alt="">
								</div>
								<div class="info">
									<h2>
										<a href="#">Domeni GEsson</a>
									</h2>
									<h3>
										<a href="#">Awesome Technology co.</a>
									</h3>
								</div>
								<div class="content">
									<p class="description">Praesent cursus nulla non arcu
										tempor, ut egestas elit tempus. In ac ex fermentum, gravida
										felis nec, tincidunt ligula.</p>
									<div class="star-icon mt-3">
										<span><i class="lni-star-filled"></i></span> <span><i
											class="lni-star-filled"></i></span> <span><i
											class="lni-star-filled"></i></span> <span><i
											class="lni-star-half"></i></span> <span><i
											class="lni-star-half"></i></span>
									</div>
								</div>
							</div>
						</div>
						<div class="item">
							<div class="testimonial-item">
								<div class="img-thumb">
									<img src="<%=request.getContextPath()%>/front-end/members/assets/img/testimonial/img3.jpg" alt="">
								</div>
								<div class="info">
									<h2>
										<a href="#">Dommini Albert</a>
									</h2>
									<h3>
										<a href="#">Nesnal Design co.</a>
									</h3>
								</div>
								<div class="content">
									<p class="description">Praesent cursus nulla non arcu
										tempor, ut egestas elit tempus. In ac ex fermentum, gravida
										felis nec, tincidunt ligula.</p>
									<div class="star-icon mt-3">
										<span><i class="lni-star-filled"></i></span> <span><i
											class="lni-star-filled"></i></span> <span><i
											class="lni-star-filled"></i></span> <span><i
											class="lni-star-filled"></i></span> <span><i
											class="lni-star-half"></i></span>
									</div>
								</div>
							</div>
						</div>
						<div class="item">
							<div class="testimonial-item">
								<div class="img-thumb">
									<img src="<%=request.getContextPath()%>/front-end/members/assets/img/testimonial/img4.jpg" alt="">
								</div>
								<div class="info">
									<h2>
										<a href="#">Fernanda Anaya</a>
									</h2>
									<h3>
										<a href="#">Developer</a>
									</h3>
								</div>
								<div class="content">
									<p class="description">Praesent cursus nulla non arcu
										tempor, ut egestas elit tempus. In ac ex fermentum, gravida
										felis nec, tincidunt ligula.</p>
									<div class="star-icon mt-3">
										<span><i class="lni-star-filled"></i></span> <span><i
											class="lni-star-filled"></i></span> <span><i
											class="lni-star-half"></i></span> <span><i
											class="lni-star-half"></i></span> <span><i
											class="lni-star-half"></i></span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Testimonial Section End -->
	<!-- Footer Section Start -->
	<footer id="footer" class="footer-area section-padding">
		<div class="container">
			<div class="row">
				<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 col-mb-1"></div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6 col-mb-12">
					<div class="widget" style="text-align: center">
						<img src="<%=request.getContextPath()%>/front-end/members/assets/img/logo.svg" style="width: 100px; height: auto"
							alt=""> </a>
					</div>
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
					<h3 class="footer-titel">關於我們</h3>
					常見問題<br> 隱私政策<br> 功能更新
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
					<h3 class="footer-titel">其他服務</h3>
					企業方案<br> 異業合作<br> 廣告洽談
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
					<h3 class="footer-titel">網站地圖</h3>
					精選課程<br> 名人講座<br> 老師功能
				</div>
			</div>
		</div>
		<div class="col-md-12" style="padding-bottom: 100px"></div>
	</footer>
	<!-- Footer Section End -->
	<!-- Go to Top Link -->
	<a href="#" class="back-to-top"> <i class="lni-comments"></i>
	</a>
	<!-- Preloader -->
	<div id="preloader">
		<div class="loader" id="loader-1"></div>
	</div>
	<!-- End Preloader -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script type="text/javascript">
	function status(){
		
		 swal('老師資格狀態審核中', '請耐心等候1~3個工作天，一但審核完畢，即會立刻通知', 'info');
	}
	
	
	
	
	
//     var abc = document.getElementById("abc").value;
//     if(abc.length ===0){
<%--     	var sign = document.getElementById("sign").innerHTML ="<a class='nav-link' href='<%=request.getContextPath()%>/front-end/members/signIn.jsp'>我要登入&nbsp;<i class='lni-bulb'></i></a>"; --%>
//     	var dropdown = document.getElementById("dropdown").innerHTML ="<p> </p>";
    
    	
//     }else{
<%--     	var sign = document.getElementById("sign").innerHTML ="<a class='nav-link' id='signout' href='<%=request.getContextPath()%>/members/members.do?action=signout'>我要登出&nbsp;<i class='lni-bulb'></i></a>"; --%>
<%--     	var text = document.getElementById("text").innerHTML ="<img src='<%=request.getContextPath()%>/front-end/members/assets/img/students.svg'>"; --%>
    	
    	
//     }
   
   
    
    
    
    </script>
	<script
		src="<%=request.getContextPath()%>/front-end/members/assets/js/jquery-min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/members/assets/js/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/members/assets/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/members/assets/js/owl.carousel.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/members/assets/js/wow.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/members/assets/js/jquery.nav.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/members/assets/js/scrolling-nav.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/members/assets/js/jquery.easing.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/members/assets/js/jquery.counterup.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/members/assets/js/waypoints.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/members/assets/js/main.js"></script>
</body>

</html>