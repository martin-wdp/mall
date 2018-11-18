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
    <title>已处罚商家列表</title>

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
            <h2 class="main_title">${pageNameChild}<small>(共${pageBean.rows }条)</small></h2>

            <div class="common_data p20">
                 <div class="filter_area">
                   <form role="form" class="form-inline" action="queryAllPunishedBusiness.htm" method="post" id="searchForm">
                      <input type="hidden" value="searchForm" id="formId">
                      <input type="hidden" value="queryAllPunishedBusiness" id="formName">
                      <input type="hidden" value="1"  name="condition">
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon">店铺名称</span>
                    <input type="text" class="form-control" name="searchText" value="${selectBean.searchText }">
                  </div>
                </div>
                <div class="form-group">
                  <button type="submit" class="btn btn-primary">搜索</button>
                </div>
              </form>
            </div>
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
              <!--   <th width="30"><input type="checkbox" onclick="allunchecked(this,'id')"></th>-->
               <th>店铺名称</th>
			   <th>处罚规则</th>
			   <th>处罚原因</th>
			   <!--<th>创建时间</th>-->
			   <th>关店开始时间</th>
			   <th>关店结束时间</th>
			   <th>扣金额（元）</th>
			   <th>扣钱的状态</th>
			   <th class="w100">限制上传开始时间</th>
			   <th class="w100">限制上传结束时间</th>
             <!--  <th width="100">操作</th> -->
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageBean.list}" var="punishRecord" varStatus="i">
				<fmt:formatDate value="${punishRecord.createTime}"
								pattern="yyyy-MM-dd HH:mm:ss" var="createTime" />
								
				<fmt:formatDate value="${punishRecord.closeStime}"
					pattern="yyyy-MM-dd HH:mm:ss" var="closeStime" />
					
				<fmt:formatDate value="${punishRecord.closeEtime}"
					pattern="yyyy-MM-dd HH:mm:ss" var="closeEtime" />
					
				<fmt:formatDate value="${punishRecord.limitStime}"
					pattern="yyyy-MM-dd HH:mm:ss" var="limitStime" />
					
				<fmt:formatDate value="${punishRecord.limitEtime}"
					pattern="yyyy-MM-dd HH:mm:ss" var="limitEtime" />
				<tr class="tableListTr">
					<!--<td><input type="checkbox" name="id" value="${punishRecord.id}"></td>-->
					<td class="tc">${punishRecord.storeName}</td>
					<td class="tc">
					<c:if test="${punishRecord.punishId==1}">关店</c:if>
					<c:if test="${punishRecord.punishId==9}">扣违约金</c:if>
					<c:if test="${punishRecord.punishId==7}">限制上传商品</c:if>
					</td>
					<td class="tc">${punishRecord.punishReason}</td>
					<!-- <td class="tc">${createTime}</td>-->
					<td class="tc">${closeStime}</td>
					<td class="tc">${closeEtime}</td>
					<td class="tc">${punishRecord.reduceMoney}</td>	
					<td class="tc">
					<c:if test="${punishRecord.rmoneyState==0}">未扣</c:if>
					<c:if test="${punishRecord.rmoneyState==1}">已扣</c:if>								
					</td>
					<td class="tc">${limitStime}</td>	
					<td class="tc">${limitEtime}</td>		
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
