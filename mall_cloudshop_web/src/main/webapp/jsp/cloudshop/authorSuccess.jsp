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
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Bootstrap 101 Template</title>

  <!-- Bootstrap -->
  <link href="css/bootstrap.min.css" rel="stylesheet">

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->

  <style>
    html,body{background:#F5F5F5;}
    .title{background:#357DC3;padding:20px 0;text-align:center;}
    .success_box{width:700px;height:350px;background:#fff;box-shadow:5px 5px 3px #eee;margin:20px auto;padding:80px 125px;}
    .success_info{padding-left:60px;background:url(<%=basePath %>/images/correct.png) no-repeat left top;}
    .success_info h4{font-size:16px;font-weight:bold;color:#f60;margin-bottom:20px;}
    .success_btn{margin-top:30px;padding-left:120px;}
  </style>

</head>
<body>

<div class="title">
  <img alt="" src="<%=basePath %>/images/logo.png">
</div>

<div class="success_box">
  <div class="success_info">
    <h4>授权成功！</h4>
    <p><span class="countdown">5</span>秒自动关闭本页面，请<%--在<a href="#">开放平台页面</a>--%>返回qpmall后台完成剩下的操作！</p>
  </div>
  <div class="success_btn">
    <button type="button" class="btn btn-primary" onclick="window.close()">立即关闭</button>
  </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script>
  $(function(){
    var sec = 5;
    setInterval(function(){
      sec--;
      $('.countdown').html(sec);
      if(sec == 0){
        window.close();
      }
    },1000);
  });
</script>
</body>
</html>
