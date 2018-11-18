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
   
   
    <div class="container-fluid page_body">
      <div class="row">
      			<!-- 引用昨天导航 -->
    		<jsp:include page="../page/left.jsp"></jsp:include>
				
        <div class="col-lg-20 col-md-19 col-sm-18 main">
           
          <div class="main_cont">
	           <ol class="breadcrumb">
	              <li><a href="#">开发中</a></li>
	              <li class="active">测试进行</li>
	            </ol>     
      	  </div>
      
        </div>
      </div>
    </div>






    <div id="errorModel" class="modal fade" role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">友情提示</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
            		  此功能暂未开放，敬请期待。。。
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
	<script type="text/javascript">
	 $("#errorModel").modal('show');		
	</script>
   
  </body>
</html>
