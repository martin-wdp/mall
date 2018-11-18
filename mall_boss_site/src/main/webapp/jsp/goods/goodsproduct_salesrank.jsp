<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <title>商品销售排行</title>

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

                <h2 class="main_title">${pageNameChild}<small>(共${pageBean.rows }条)</small></h2>

                <div class="common_data p20">
                    <div class="filter_area">
                        <form role="form" class="form-inline" action="goodsProductSalesRank.htm" method="post" id="search_from">

                            <input type="hidden" value="search_from" id="formId">
                            <input type="hidden" value="goodsProductSalesRank.htm" id="formName">


                            <div class="form-group">
                                <div class="input-group date form_date" id="startpicker">
                                    <span class="input-group-addon">开始时间</span>
                                    <input class="form-control" style="width:110px;"  type="text" value="${startTime }" name="startTime" id="startTime" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group date form_date" id="endpicker">
                                    <span class="input-group-addon">结束时间</span>
                                    <input class="form-control" type="text" style="width:110px;"  value="${endTime }" name="endTime" id="endTime"readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <a class="btn btn-primary" onclick="sumitForm();">搜索</a>
                            </div>
                        </form>
                    </div>

                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>

                            <th>图片</th>
                            <th>货品名称</th>
                            <th>货品编号</th>
                            <%--<th>货品销售额</th>--%>
                            <th>货品销量</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageBean.list}" var="pb">
                            <tr>

                                <td ><img height="50px" src="${pb.goodsInfoImgId }"/></td>
                                <td style="text-align: left;">${pb.goodsInfoName}</td>
                                <td>${pb.goodsInfoItemNo}</td>
                                <%--<td >${pb.goodsInfoPreferPrice*pb.goodsCount}</td>--%>
                                <td>${pb.goodsCount}</td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <c:import url="../page/searchPag.jsp">
                        <c:param name="pageBean" value="${pageBean}"/>
                    </c:import>
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
<script src="<%=basePath %>js/report/report.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
<script>

    function sumitForm(){
        $("#pageNo").attr("value","1");
        var d1=$("#startTime").val();
        var d2=$("#endTime").val();
        if(date_day(d1,d2)==false){
            showTipAlert("开始时间不能大于结束时间,请重新选择!");
            return false;
        }
        else{
            $('#search_from').submit();
        }

    }
    //跳转首页
    function jumpIndex(){
        window.parent.location.href='index.htm';
    }
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
</script>
</body>
</html>
