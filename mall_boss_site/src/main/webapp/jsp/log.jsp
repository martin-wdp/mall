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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/time.css" />
    <style type="text/css">
    .his_wp {padding:20px 50px; font-size:12px;}
	.history h2 {font-size:30px; color:#369; font-family:"寰蒋闆呴粦";}
	.history h2 a {color:#369; font-size:26px; background:url(<%=basePath %>/images/history_date.png) no-repeat right center; padding-right:20px; margin-right:50px;}
	.history h2 a:hover {text-decoration:none;}
	.his_box {background:url(<%=basePath %>/images/history_line.png) repeat-y 150px top; padding:30px 0 30px 0; margin-top:20px; display:none;}
	.log_list {margin-bottom:100px; background:url(<%=basePath %>/images/history_node.png) no-repeat 144px 5px;}
	.log_list:hover {background:url(<%=basePath %>/images/history_node_hover.png) no-repeat 144px 5px;}
	.log_list dt {float:left; font-size:15px; width:100px; text-align:right;}
	.log_list dd {margin-left:200px;}
	.log_list dd h4 {font-size:14px; font-weight:700; margin-bottom:5px;}
	.log_list dd em {font-weight:700; line-height:180%;}
	.log_list dd p {line-height:150%; color:#999;}
    </style>
  </head>
<body>
	
   <!-- 引用头 -->
   <jsp:include page="page/header.jsp"></jsp:include>
   
   
    <div class="container-fluid page_body">
      <div class="row">
      			<!-- 引用昨天导航 -->
    		<jsp:include page="page/left.jsp"></jsp:include>
				
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            
          <div class="main_cont">
				 <ol class="breadcrumb">
	              <li><a href="#">首页</a></li>
	              <li class="active">版本日志</li>
	            </ol>
            <div class="simple_statistics mb20">
		        	<div class="history">
		        	<h2><a class="log_time" href="javascript:;">
		        	<c:if test="${fn:length(verList)==0 }">
		        	2015年
		        	</c:if>
		        		<c:forEach items="${verList}" var="version" varStatus="i">
		        			<c:if test="${i.index==0 }"><fmt:formatDate value="${version.versionTime }" pattern="yyyy" var="versionTime" />${versionTime }年</c:if>
		        		</c:forEach>
		        	</a>系统更新日志</h2>
		            <div class="his_box">
		            	<c:forEach items="${verList}" var="version" varStatus="i">
					    <fmt:formatDate value="${version.versionTime }" pattern="yyyy-MM-dd HH:mm:ss" var="versionTime" />
					    	<dl class="log_list clearfix">
		                	<dt>${versionTime }</dt>
		                    <dd>
		                    	<h4>${version.versionCode }</h4>
								${version.versionContent }
		                    </dd>
		                </dl><!--/log_list-->
					    </c:forEach>
		            </div><!--/his_box-->
		        </div><!--/history-->	
         	 </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script> 
	<script type="text/javascript" src="<%=basePath%>js/log/log.js"></script>
   
   
  </body>
</html>
