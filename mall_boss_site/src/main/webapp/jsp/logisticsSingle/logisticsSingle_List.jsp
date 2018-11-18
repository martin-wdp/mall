
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
    <title>物流单模板列表</title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
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

            <h2 class="main_title">${pageNameChild} <small>(共${pb.rows }条)</small></h2>

            <div class="common_data p20">
				<form id="searchForm" action="logisticssingle.htm" method="post">
				 	<input type="hidden" value="searchForm" id="formId">
                 	 <input type="hidden" value="logisticssingle.htm" id="formName">
				</form>
              <div class="data_ctrl_area mb20">
                <div class="data_ctrl_search pull-right"></div>
                <div class="data_ctrl_brns pull-left">
                  <button type="button" class="btn btn-info" onclick="toadd();">
                    <i class="glyphicon glyphicon-plus"></i> 添加
                  </button>
                  <!-- <button type="button" class="btn btn-info" onclick="delalllogistics();">
                    <i class="glyphicon glyphicon-trash"></i> 删除
                  </button> -->
                </div>
                <div class="clr"></div>
              </div>
              <table class="table table-striped table-hover">
                <thead> 
                <tr>
                  <th width="50"><!-- <input type="checkbox" onclick="allunchecked(this,'logisticsSingleId');" > -->序号</th>
                  <th>图片</th>
                  <th>物流公司名称</th>
                  <th>宽度</th>
                  <th>高度</th>
                  <th class="w100">创建时间</th>
                  <th width="200">操作</th>
                </tr>
                </thead>
                <tbody>
            <c:forEach var="infoVo" items="${pb.list }" varStatus="status"> 
                <tr>
                  <td width="50"><%-- <input type="checkbox"  name="logisticsSingleId"  value="${infoVo.logisticsSingleId }"> --%>${status.count }</td>
                  <td>	<c:if test="${infoVo.logisticsSingleImg!=null}">
				        	<img alt="" src="${infoVo.logisticsSingleImg }"  height="50">
			        	</c:if>
		        	</td>
                  <td>${infoVo.companyName }</td>
                  <td>${infoVo.logisticsSingleWidth }</td>
                  <td>${infoVo.logisticsSingleHeight }</td>
                  <td><fmt:formatDate value="${infoVo.createTime }" var="cdate" type="both"/>
					${cdate }</td>
                  <td>
                    <div class="btn-group">
                      <button type="button" class="btn btn-default" onclick="toEdit(${infoVo.logisticsSingleId });">编辑</button>
                      <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                      </button>
                      <ul class="dropdown-menu" role="menu">
                        <li><a href="javascript:void(0);"  onclick="toEdit(${infoVo.logisticsSingleId });">预览</a></li>
                        <li><a href="javascript:void(0);" onclick="delonelogistics(${infoVo.logisticsSingleId });" >删除</a></li>
                      </ul>
                    </div>
                  </td>
                </tr>
             </c:forEach>
             
                </tbody>
              </table>

              <div class="table_foot">
              		<c:import url="../page/searchPag.jsp">
				     <c:param name="pageBean" value="${pb }"/>
				     <c:param name="path" value="../"></c:param>
					</c:import>
              </div>

            </div>

          </div>
          
        </div>
      </div>
    </div>

   
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    <script src="<%=basePath %>js/common/common_checked.js"></script>
    <script src="<%=basePath %>js/common/common_alert.js"></script>
    <script src="<%=basePath %>/js/select2.min.js"></script>
    <script>
      $(function(){
        /* 为选定的select下拉菜单开启搜索提示 */
        $('select[data-live-search="true"]').select2();
        /* 为选定的select下拉菜单开启搜索提示 END */
      });
      
      //准备添加
      function toadd(){
      	 window.location="toAddLogisticsSingle.htm?CSRFToken=${token}";
      }
      
      //准备编辑
      function toEdit(logisticsSingleId){
          window.location="toUpdateLogisticsSingle.htm?CSRFToken=${token}&&logisticsSingleId="+logisticsSingleId;
      }
      
      //去删除
      function delonelogistics(logisticsSingleId){
          showDeleteOneConfirmAlert('dellogisticssingle.htm?CSRFToken=${token}&logisticsSingleId='+logisticsSingleId,'确定要删除此物流单吗？');
      }
      
   
    </script>
  </body>
</html>
