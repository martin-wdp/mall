
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
    <title>关注详细</title>

   
    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="<%=basePath %>stylesheet" href="css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">
    <script src="<%=basePath %>js/jquery.min.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    body{background:none;}
    </style>
  </head>
  <body>
  
  
             <form role="form" class="form-inline" action="findGoodsAtteByProductId.htm" method="post" id="searchForm">
              	  		<input type="hidden" value="searchForm" id="formId">
                        <input type="hidden" value="findGoodsAtteByProductId.htm" id="formName">
                        <input type="hidden" value="${token }" name="CSRFToken"/>
        			   <input type="hidden" name="productId" value="${pb.list[0].productId}" />
       
            <table class="table table-striped table-hover">
              <thead>
              <tr>
                <th>Email</th>
                <th>会员用户名</th>
                <th>手机</th>
                <th>关注时间</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach items="${pb.list }" var="goodsAtte" varStatus="sta">
              <fmt:formatDate value="${goodsAtte.createTime}" pattern="yyyy-MM-dd HH:mm:ss" var="atteTime"/>
              <tr>
                <td>${goodsAtte.infoEmail }</td>
                <td>${goodsAtte.customerUsername}</td>
                <td>${goodsAtte.infoMobile}</td>
                <td>${atteTime}</td>
              </tr>
             </c:forEach>
              </tbody>
            </table>
            </form>
            <div class="table_foot">
            		<c:import url="../page/searchPag.jsp">
                        <c:param name="pageBean" value="${pb}"/>
                    </c:import>
            </div>
          
        </body>
        
        