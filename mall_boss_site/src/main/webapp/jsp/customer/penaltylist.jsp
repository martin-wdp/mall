<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>规则列表</title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
   <jsp:include page="../page/header.jsp"></jsp:include>
  
    <div class="page_body container-fluid">
      <div class="row">
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
          <div class="main_cont">
            <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>
            <input type="hidden" value="${token }" id="hi_token"></input>
            <h2 class="main_title">${pageNameChild} <small>(共${pageBean.rows }条)</small></h2>

            <div class="common_data p20">
				<form role="form" class="form-inline" action="queryAllPenaltylist.htm" method="post" id="searchForm">
                  <input type="hidden" value="searchForm" id="formId">
                  <input type="hidden" value="queryAllPenaltylist.htm" id="formName">
                </form>
            <!-- <div class="data_ctrl_area mb20">
              <div class="data_ctrl_search pull-right"></div>
              <div class="data_ctrl_brns pull-left">
                <button type="button" class="btn btn-info" onclick="delallpublish()">
                  <i class="glyphicon glyphicon-trush"></i> 删除
                </button>
              </div>
              <div class="clr"></div>
            </div> -->

         <form action="deletenewPunishInfo.htm" id="delallForm" method="post">
            <input type="hidden" value="delallForm" id="formId">
            <input type="hidden" value="deletenewPunishInfo.htm" id="formName">
            <table class="table table-striped table-hover">
            <thead>
            <tr>
              <!--  <th width="30"><input type="checkbox" onclick="allunchecked(this,'id')"></th>-->
               <th width="50">序号</th>
              <th  class="">处罚规则</th>
              <th  class="">规则标识</th>
              <th class="">创建时间</th>
              <th class="">修改时间</th>
             <!--  <th width="100">操作</th> -->
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageBean.list}" var="customerPunish" varStatus="i">
				<fmt:formatDate value="${customerPunish.modifiedTime }"
					pattern="yyyy-MM-dd HH:mm:ss" var="modifiedTime" />
				<fmt:formatDate value="${customerPunish.createTime }"
					pattern="yyyy-MM-dd HH:mm:ss" var="createTime" />
				<tr class="tableListTr">
					<!-- <td><input type="checkbox" name="id" value="${customerPunish.id}"></td>-->
					<td>${i.count}</td>
					<td>
					<c:if test="${customerPunish.rule==0 }">关店</c:if>
					<c:if test="${customerPunish.rule==1 }">扣积分</c:if>
					<c:if test="${customerPunish.rule==2 }">扣违约金</c:if>
					<c:if test="${customerPunish.rule==3 }">限制上传商品</c:if>
					</td>
					<td>${customerPunish.rule}</td>
					<td>${createTime}</td>
					<td>${modifiedTime}</td>
					<%-- <td>
	                	<button type="button" class="btn btn-default" onclick="delonepublish(${customerPunish.id})">删除</button>
	                </td> --%>
				</tr>
			</c:forEach>
            </tbody>
            </table>
            </form>
              <div class="table_foot">
         		<c:import url="../page/searchPag.jsp">
				     <c:param name="pageBean" value="${pageBean }"/>
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
    <script src="<%=basePath %>js/common/common_alert.js"></script>
    <script src="<%=basePath %>js/common/common_checked.js"></script>
    <script>
      $(function(){
        /* 表单项的值点击后转换为可编辑状态 */
        $('.form_value').click(function(){
          var formItem = $(this);
          if(!$('.form_btns').is(':visible')) {
            formItem.parent().addClass('form_open');
            $('.form_btns').show();
            $('.form_btns').css({
              'left': formItem.next().offset().left + 70 + 'px',
              'top': formItem.next().offset().top - 30 + 'px'
            });
            $('.form_sure,.form_cancel').click(function () {
              $('.form_btns').hide();
              formItem.parent().removeClass('form_open');
            });
          }
        });
      });
      
     /*  //删除规则
      function delallpublish(){
    	  showAjaxDeleteBatchConfirmAlert("delallForm","id");
      }                                                                                                                                                                                                                                                                                 
      
      //删除单个规则
      function delonepublish(publishId){
    	  showDeleteOneConfirmAlert('deletenewPunishInfo.htm?CSRFToken=${token}&id='+publishId+'');
      } */
    </script>
  </body>
</html>
