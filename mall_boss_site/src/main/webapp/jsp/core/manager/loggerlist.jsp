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
    <title>操作日志</title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
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
   <jsp:include page="../../page/header.jsp"></jsp:include>
   
    <div class="page_body container-fluid">
      <div class="row">
		<jsp:include page="../../page/left.jsp"></jsp:include>

        <div class="col-lg-20 col-md-19 col-sm-18 main">
          <div class="main_cont">
              <jsp:include page="../../page/breadcrumbs.jsp"></jsp:include>

            <h2 class="main_title">${pageNameChild} <small>(共${pageBean.rows }条)</small></h2>
            <div class="common_data p20">
                <div class="filter_area">
                <form role="form"  action="initoperalog.htm" method="post" id="searchForm" class="form-inline">
                            <input type="hidden" value="${token }" name="CSRFToken">
							 <input type="hidden" value="searchForm" id="formId"> 
			                 <input type="hidden" value="initoperalog.htm" id="formName">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">操作内容</span>
                                    <input type="text" name="opContent" class="form-control"
                                           value="${operationLog.opContent}">
                                </div>
                            </div>
                    <div class="form-group">
                        <div class="input-group date form_datetime" id="startpicker">
                            <span class="input-group-addon">开始时间</span>
                            <input class="form-control" style="width:140px;" type="text" value="${startTime }"
                                   name="startTime" id="startTime" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group date form_datetime" id="endpicker">
                            <span class="input-group-addon">结束时间</span>
                            <input class="form-control" type="text" style="width:130px;" value="${endTime }"
                                   name="endTime" id="endTime" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon">操作人</span>
                            <select class="cate_selector w150" data-live-search="true" name="opName" id="selectName">
                                <option value="">-请选择-</option>
                                <c:forEach items="${operationLogList}" var="log">
                                    <option value="${log.opName }"
                                            <c:if test="${log.opName==operationLog.opName }"> selected="selected" </c:if>>
                                            ${log.opName }</option>
                                </c:forEach>
                            </select>
                        </div>

                    </div>


                    <div class="form-group">
                                <button type="submit" class="btn btn-primary">搜索</button>
                            </div>
                        </form>

                    </div>
                    
                  
              <div class="data_ctrl_area mb20">
                <div class="data_ctrl_search pull-right"></div>
                <div class="data_ctrl_brns pull-left">
                  <div class="btn-group">
                    <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                     <i class="icon iconfont">&#xe613;</i> 导出日志 <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
	                    <li><a href="javascript:;" onclick="exportExcel(15);">半个月内</a></li>
						<li><a href="javascript:;" onclick="exportExcel(30);">一个月内</a></li>
						<li><a href="javascript:;" onclick="exportExcel(90);">三个月内</a></li>
						<li><a href="javascript:;" onclick="exportExcel(0);">所有</a></li>
                    </ul>
                  </div>
                </div>
                <div class="clr"></div>
              </div>
                <form action="deletelog.htm" method="post" id="delForm">
               <input type="hidden" value="${token }" name="CSRFToken">
                    <input type="hidden" id="opName" name="opName">
              <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th width="50"><input type="checkbox" onclick="allunchecked(this,'parameterIds');"></th>
                  <th width="200">操作人</th>
                  <th class="w100">操作时间</th>
                  <th>操作内容</th>
                </tr>
                </thead>
                <tbody>
                   <c:forEach items="${pageBean.list}" var="log" varStatus="i">
                    <fmt:formatDate value="${log.opTime }" pattern="yyyy-MM-dd HH:mm:ss" var="opTime" />
		                <tr>
                            <td width="50"><input type="checkbox" name="parameterIds" value="${log.opId}"></td>
		                  <td> ${log.opName }</td>
		                  <td>${opTime }</td>
		                  <td style="text-align:left;">  <a title="${log.opContent }" style="cursor:pointer;" onclick="operation(${log.opId})">
                                    ${log.opContent}
                            </a></td>
		                </tr>
          			</c:forEach>
                </tbody>
              </table>
				</form>
              <div class="table_foot"> 
               	<c:import url="../../page/searchPag.jsp">
				     <c:param name="pageBean" value="${pageBean }"/> 
				     <c:param name="path" value="../../"></c:param> 
				</c:import>
              </div>
  
            </div>

          </div>
          
        </div>
      </div> 
    </div>
<!-- Modal -->
    <div class="modal fade" id="logger"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">日志详细</h4>
          </div>
          <div class="modal-body">
          </div>  
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
        </div>
      </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    <script src="<%=basePath %>js/common/common_alert.js"></script>
    <script src="<%=basePath %>js/common/common_checked.js"></script>
 <script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
 <script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="<%=basePath %>/js/select2.min.js"></script>
    <script type="text/javascript">
    $(function(){
    	 $('select[data-live-search="true"]').select2();
        /* 下面是表单里面的日期时间选择相关的 */
        $('.form_datetime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii:00:00',
            weekStart: 1,
            autoclose: true,
            language: 'zh-CN',
            pickerPosition: 'bottom-left',
            todayBtn: true,
            viewSelect: 'hour'
        });
        $('.form_date').datetimepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            language: 'zh-CN',
            pickerPosition: 'bottom-left',
            minView: 2,
            todayBtn: true
        });
        /* 下面是表单里面的日期时间选择相关的 END */
        /*下面是时间选择器开始时间不能大于结束时间设置  START*/
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        $('#endpicker').datetimepicker('setStartDate', startTime);
        $('#startpicker').datetimepicker('setEndDate', endTime);
        $('#endpicker')
                .datetimepicker()
                .on('show', function (ev) {
                    startTime = $("#startTime").val();
                    endTime = $("#endTime").val();
                    $('#endpicker').datetimepicker('setStartDate', startTime);
                    $('#startpicker').datetimepicker('setEndDate', endTime);
                });
        $('#startpicker')
                .datetimepicker()
                .on('show', function (ev) {
                    endTime = $("#endTime").val();
                    startTime = $("#startTime").val();
                    $('#startpicker').datetimepicker('setEndDate', endTime);
                    $('#endpicker').datetimepicker('setStartDate', startTime);
                });
        /*下面是时间选择器开始时间不能大于结束时间设置  END*/
    });
    function exportExcel(obj){
    	if((obj != null || obj != "") && ! isNaN(obj)){
    		var url = 'exportexcel.htm?days='+obj+'&CSRFToken=${token}';
    		location.href=url;
    	}
    }
    
  //查看操作日志的详细内容
    function operation(opid) {  
        $.ajax({
            type : 'post',
            url : 'selectLogById.htm?CSRFToken=${token}',
            data:{"opId":opid},
            success : function(data) {
                $("#logger").find('.modal-body').html(data.opContent); 
                $('#logger').modal('show');
            }
        });
    }
  
  function batchAllLog(){
      var name = $("#selectName").val();
      $("#opName").val(name)
      showDeleteBatchConfirmAlert('delForm', 'parameterIds', '确定要删除所选中的日志吗？');
  }
    </script>
  </body>
</html>
