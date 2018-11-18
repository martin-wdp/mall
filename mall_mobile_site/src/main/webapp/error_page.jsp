<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title>Paistore</title>

<!-- Bootstrap -->
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>css/idangerous.swiper.css" rel="stylesheet">
<link href="<%=basePath%>css/style.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]> 
      <script src="js/html5shiv.min.js"></script>
      <script src="js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="error_page">
		<div class="text-center">
			<img width="30%" alt="" src="<%=basePath%>images/error_icon.png">
		</div>
		<h4>意外出现错误，请返回首页继续浏览。</h4>
		<a href="<%=basePath%>main.html"
			class="btn btn-warning btn-lg btn-block">返回首页</a>
	</div>

	<div class="foot">
		<p>由paistore提供技术支持</p>
	</div>
	<!-- /foot -->


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="<%=basePath%>js/jquery.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<%=basePath%>js/bootstrap.min.js"></script>
	<script src="<%=basePath%>js/idangerous.swiper-2.1.min.js"></script>
	<script src="<%=basePath%>js/fastclick.min.js"></script>
	<script src="<%=basePath%>js/jquery.keleyi.js"></script>
	<script>
	  $(function(){
		FastClick.attach(document.body);
	  });
	</script>
</body>
</html>
