<%--
  新增云销功能授权页
  User: liangck
  Date: 15/6/23
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title></title>

  <!-- Bootstrap -->
  <link href="<%=basePath %>/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=basePath %>/iconfont/iconfont.css" rel="stylesheet">
  <link href="<%=basePath %>/css/style.css" rel="stylesheet">
</head>
<body>

<!-- 引用头 -->
<jsp:include page="../page/header.jsp"></jsp:include>
<div class="page_body container-fluid">
  <div class="row">
    <jsp:include page="../page/left.jsp"></jsp:include>

    <div class="col-lg-20 col-md-19 col-sm-18 main">
      <div class="main_cont">
        <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>
        <div class="main_cont" style="background:#F5F5F5;">

          <div style="width:500px;margin:200px auto 0 auto;text-align: center">
            <img alt="" src="<%=basePath %>/images/logo.png">
            <p class="mt20">很抱歉，调用开放平台接口时出错嘞</p>
            <p>错误原因：${msg}</p>
          </div>

        </div>
      </div>
    </div>
  </div>
</div>

<script src="<%=basePath %>/js/bootstrap.min.js"></script>
<script src="<%=basePath %>/js/summernote.min.js"></script>
<script src="<%=basePath %>/js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>/js/common.js"></script>
</body>
</html>
