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
    <title>会员积分查询</title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
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

            <h2 class="main_title">${pageNameChild}<small>(共${pageBean.rows}条)</small></h2>

            <div class="common_data p20">

              <div class="filter_area">
                <form role="form" class="form-inline" action="querycustpointbycustpoint.htm" method="post" id="searchForm">
                 	 <input type="hidden" value="searchForm" id="formId">
                  <input type="hidden" value="querycustpointbycustpoint.htm" id="formName">
                  <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-addon">用户名</span>
                      <input type="text" class="form-control" name="customerUsername" value="${point.customerUsername}" >
                    </div>
                  </div>
                  <div class="form-group">
                      <div class="input-group date form_date" id="startpicker">
                      <span class="input-group-addon">开始时间</span>
                        <input class="form-control" style="width:110px;" id="startTime" readonly value="${logindatet}"
                               type="text" name="createTimeF">
                      <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                      <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>
                  </div> 
              
                  <div class="form-group">
                      <div class="input-group date form_date" id="endpicker">
                      <span class="input-group-addon">结束时间</span>
                        <input class="form-control" readonly value="${logindateto}" id="endTime" type="text"
                               style="width:110px;" name="createTimeT">
                      <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                      <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>
                  </div>
                  <div class="form-group">
                      <button type="button" class="btn btn-primary" onclick="dosubmit()">搜索</button>
                  </div>
                </form>
              </div>

              <div class="data_ctrl_area mb20">
                <div class="data_ctrl_search pull-right"></div>
                <div class="data_ctrl_brns pull-left">
                  <button type="button" class="btn btn-info" onclick="delAllPoint();">
                    <i class="glyphicon glyphicon-trash"></i> 删除
                  </button>
                </div>
                <div class="clr"></div>
              </div>
		<form action="deletenewallcustomerpoint.htm?CSRFToken=${token}" method="post" id="delForm">
              <table class="table table-striped table-hover">
                <thead>
                <tr>
                  <th width="10"><input type="checkbox" onclick="allunchecked(this,'pointId');"></th>
                  <th>用户名</th>
                  <th>积分</th>
                  <th>积分详细</th>
                  <th class="w100">日期</th>
                  <th class="w100">操作</th>
                </tr>
                </thead>
                <tbody>
            <c:forEach items="${pageBean.list}" var="point" varStatus="i">
		   	 <fmt:formatDate value="${point.createTime }" pattern="yyyy-MM-dd HH:mm:ss" var="logindate" />
                <tr>
                  <td><input type="checkbox" name="pointId"  value="${point.pointId }"></td>
                  <%--<td>${point.customerUsername}</td>--%>
                  <td><c:if test="${!empty point.companyName}">
                      ${point.companyName }
                  </c:if><c:if test="${empty point.companyName}">
                      ${point.customerNickname }
                  </c:if></td>
                  <td>
                  <c:if test="${point.pointType ==1 }">
                      ${point.point}
                  </c:if>
                      <c:if test="${point.pointType !=1 }">
                          -${point.point}
                      </c:if>
                  </td>
                  <td>
						<c:if test="${fn:length(point.pointDetail)>10 }">
							${fn:substring(point.pointDetail,0,9) }...
						</c:if>
						<c:if test="${fn:length(point.pointDetail)<=10 }">
							${point.pointDetail}
						</c:if>				
					</td>
                  <td>${logindate }</td>
                  <td>
                    <button type="button" class="btn btn-default" onclick="delOnePoint(${point.pointId });">删除</button>
                  </td>
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
    <script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    <script src="<%=basePath %>js/common/common_alert.js"></script>
   <script src="<%=basePath %>js/report/report.js"></script>
    <script src="<%=basePath %>js/common/common_checked.js"></script>
    <script>
      $(function(){


        /* 下面是表单里面的日期时间选择相关的 */
        $('.form_datetime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii:00:00',
          weekStart : 1,
          autoclose : true,
          language : 'zh-CN',
          pickerPosition : 'bottom-left',
          todayBtn : true,
          viewSelect : 'hour'
        });
        $('.form_date').datetimepicker({
          format : 'yyyy-mm-dd',
          weekStart : 1,
          autoclose : true,
          language : 'zh-CN',
          pickerPosition : 'bottom-left',
          minView : 2,
          todayBtn : true
        });
        /* 下面是表单里面的日期时间选择相关的 END */

          /*下面是时间选择器开始时间不能大于结束时间设置  START*/
          var start = $("#startTime").val();
          var end = $("#endTime").val();
          $('#endpicker').datetimepicker('setStartDate', start);
          $('#startpicker').datetimepicker('setEndDate', end);
          $('#endpicker')
                  .datetimepicker()
                  .on('show', function (ev) {
                      var startTime = $("#startTime").val();
                      var endTime = $("#endTime").val();
                      $('#endpicker').datetimepicker('setStartDate', startTime);
                      $('#startpicker').datetimepicker('setEndDate', endTime);
                  });
          $('#startpicker')
                  .datetimepicker()
                  .on('show', function (ev) {
                      var endTime = $("#endTime").val();
                      var startTime = $("#startTime").val();
                      $('#startpicker').datetimepicker('setEndDate', endTime);
                      $('#endpicker').datetimepicker('setStartDate', startTime);
                  });
          /*下面是时间选择器开始时间不能大于结束时间设置  END*/

          /* 为选定的select下拉菜单开启搜索提示 */

        /* 下面是表单里面的填写项提示相关的 */
        $('.zhekoulv').popover({
          content : '有效值0~1,如果输入0.85,表示该会员等级以销售价85折购买商品',
          trigger : 'hover'
        });
        $('.morendengji').popover({
          content : '如果选择"是",顾客注册会员时,初始等级为当前等级',
          trigger : 'hover'
        });
        $('.suoxujifen').popover({
          content : '按积分升级时,会员积分达到此标准后会自动升级为当前等级',
          trigger : 'hover'
        });
      });
      function dosubmit() {
          var startTime = $("#startTime").val();
          var endTime = $("#endTime").val();
          if (date_day(startTime, endTime) == false) {
              showTipAlert("开始时间不能大于结束时间,请重新选择!");
              return false;
          }
          $("#searchForm").submit();
      }
      //删除会员信息
      function delOnePoint(pointId){
            showDeleteOneConfirmAlert('deletenewcustomerpoint.htm?CSRFToken=${token}&pointId='+pointId,'确定要删除此记录吗？');    
      }
      
      //删除等级信息s
      function delAllPoint(){
          showDeleteBatchConfirmAlert('delForm','pointId','确定要删除所选中的记录吗？');  
      }
    </script>
  </body>
</html>
