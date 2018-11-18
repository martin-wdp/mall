<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商家对账列表</title>

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
        <!-- 引用左边 -->
        <jsp:include page="../page/left.jsp"></jsp:include>

        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild}<small><span>(共${pb.rows }条)</span></small>
                </h2>

                <div class="common_data p20">
                    <div class="filter_area">
                        <form method="post" class="form-inline" id="search-form">
                            <input type="hidden" value="search-form" id="formId">
                            <input type="hidden" value="checkReport.htm" id="formName">

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">店铺名称</span>
                                    <input type="text" class="form-control" name="storeName" id="storeName" value="${storeName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">总销售额大于</span>
                                    <input class="form-control" type="text" name="totalOrderMoney" id="totalOrderMoney" value="${totalOrderMoney}">
                                </div>
                            </div>
                            <div class="form-group">
                                <button id="search-report" class="btn btn-primary">搜索</button>
                            </div>
                        </form>
                    </div>
                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="$('#getReport').modal('show')">
                                生成报表
                            </button>


                            <button type="button" class="btn btn-info" onclick="delReportBatch()">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                            <button type="button" class="btn btn-info" onclick="$('#outReport').modal('show')">
                                <i class="glyphicon glyphicon-log-out"></i> 导出报表
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>

                    <table class="table table-striped table-hover">
                        <form  method="post" id="delReportForm">
                            <input type="hidden" name="CSRFToken" value="${token}" id="token">
                            <input type="hidden" name="pageNo" value="${pb.pageNo}" id="pageNo">

                        <thead>
                        <tr>
                            <th width="50"><input onclick="allunchecked(this,'storeIds')" type="checkbox"></th>
                            <th>商家名称</th>
                            <th>总销售额</th>
                            <th>总订单优惠</th>
                            <th>总商品优惠</th>
                            <th width="200">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pb.list}" var="report" varStatus="i">
                            <tr>
                                <td ><input type="checkbox" name="storeIds"  value="${report.storeId }"></td>
                                <td >${report.storeName }</td>
                                <td><fmt:formatNumber type="number" value="${report.totalOrderMoney}"    maxFractionDigits="2"/></td>
                                <td><fmt:formatNumber type="number" value="${report.totalOrderPrePrice}" maxFractionDigits="2"/></td>
                                <td><fmt:formatNumber type="number" value="${report.totalGoodsPrePrice}" maxFractionDigits="2"/></td>
                                <%--<td >${report.totalOrderMoney }</td>
                                <td >${report.totalOrderPrePrice }</td>
                                <td >${report.totalGoodsPrePrice }</td>--%>

                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default" onclick="window.location.href='queryReportCate.htm?CSRFToken=${token }&storeId=${report.storeId }'">分类对账</button>
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                            <span class="caret"></span>
                                            <span class="sr-only">Toggle Dropdown</span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="javascript:;" onclick="doSettlement(${report.storeId});">设为已结算</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>

                        </c:forEach>

                        </tbody>
                            </form>
                    </table>
                    <c:import url="../page/searchPag.jsp">
                        <c:param name="pageBean" value="${pb}"/>
                    </c:import>
                </div>

            </div>
        </div>
    </div>
</div>




<!-- Modal -->
<div class="modal fade" id="getReport"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal" action="generatReport.htm" method="post" id="genReportForm">
                <input type="hidden" name="CSRFToken" value="${token}">
                <input type="hidden" id="today" value="${dateMap.today}">
                <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">生成报表</h4>
                </div>
            <div class="modal-body" >
                    <div class="form-group">
                        <label class="control-label col-sm-5">时间段：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <label class="radio-inline">
                                <input type="radio" name="time" class="time" value="0" time_end="${dateMap.recentOneMonth }"  checked="checked"> 近一个月
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="time" class="time" value="1" time_end="${dateMap.recentThreeMonth }"> 近三个月
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="time" class="time" value="2" time_end="${dateMap.recentSixMonth }"> 近半年
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">开始时间：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <div class="input-group date form_date" id="startpicker">
                                <input class="form-control" type="text" readonly id="startDate" name="startDate"
                                       value="${dateMap.recentOneMonth}">
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">结束时间：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <div class="input-group date form_date" id="endpicker">
                                <input class="form-control" type="text" id="endDate" name="endDate"
                                       value="${ dateMap.today}" readonly>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitExc()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
            </form>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="outReport"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">导出报表</h4>
            </div>
            <div id="modalExp">
            <div class="modal-body" >
                <form class="form-horizontal" action="exportSumReport.htm" method="post" id="expReportForm">
                    <input type="hidden" name="CSRFToken" value="${token}">

                    <div class="form-group">
                        <label class="control-label col-sm-5">导出类型：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <label class="radio-inline">
                                <input type="radio" name="exportType" value="1" class="export_type" checked="checked"> 详细
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="exportType" value="0" class="export_type"> 汇总
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">时间段：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <label class="radio-inline">
                                <input type="radio" name="time" value="0" time_end="${dateMap.recentOneMonth }" class="time_exp" checked="checked"> 近一个月
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="time" value="1" time_end="${dateMap.recentThreeMonth }" class="time_exp"> 近三个月
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="time" value="2" time_end="${dateMap.recentSixMonth }" class="time_exp"> 近半年
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">开始时间：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <div class="input-group date form_date" id="startpicker2">
                                <input class="form-control" type="text" id="startDateExp" name="startDate"
                                       value="${dateMap.recentOneMonth }" readonly>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">结束时间：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <div class="input-group date form_date" id="endpicker2">
                                <input class="form-control" type="text" id="endDateExp" name="endDate"
                                       value="${dateMap.today}" readonly>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="excReport()" >确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
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
<script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
<script type="text/javascript" src="<%=basePath%>js/customer/customer.js"></script>
<script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/plugins/iframeTools.js"></script>
<script type="text/javascript" src="<%=basePath%>js/report/report.js"></script>
<script src="<%=basePath %>js/common.js"></script>
<script>
    $(function () {


        /* 下面是表单里面的日期时间选择相关的 */
        $('.form_datetime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii:00',
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
        //生成报表开始结束时间配置
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

        /*下面是时间选择器开始时间不能大于结束时间设置  START*/
        //导出报表开始结束时间配置
        var startTime2 = $("#startTime2").val();
        var endTime2 = $("#endTime2").val();
        $('#endpicker2').datetimepicker('setStartDate', startTime2);
        $('#startpicker2').datetimepicker('setEndDate', endTime2);
        $('#endpicker2')
                .datetimepicker()
                .on('show', function (ev) {
                    startTime2 = $("#startTime2").val();
                    endTime2 = $("#endTime2").val();
                    $('#endpicker2').datetimepicker('setStartDate', startTime2);
                    $('#startpicker2').datetimepicker('setEndDate', endTime2);
                });
        $('#startpicker2')
                .datetimepicker()
                .on('show', function (ev) {
                    endTime2 = $("#endTime2").val();
                    startTime2 = $("#startTime2").val();
                    $('#startpicker2').datetimepicker('setEndDate', endTime2);
                    $('#endpicker2').datetimepicker('setStartDate', startTime2);
                });
        /*下面是时间选择器开始时间不能大于结束时间设置  END*/

    });
</script>
</body>
</html>
