<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>销售量走势图</title>

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

                <h2 class="main_title">${pageNameChild}</h2>

                <div class="common_data p20">
                    <div class="filter_area">
                        <form role="form" class="form-inline" action="saleCount.htm" method="post" id="downform">
                            <div class="form-group">
                                <div class="input-group date form_date" id="startpicker">
                                    <span class="input-group-addon">开始时间</span>
                                    <input class="form-control" type="text" style="width:110px;"  value="${startTime }" name="startTime" id="startTime" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group date form_date" id="endpicker">
                                    <span class="input-group-addon">结束时间</span>
                                    <input class="form-control" type="text" style="width:110px;"  value="${endTime }" name="endTime" id="endTime" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <button type="button" class="btn btn-default" onclick="benzhou();">本周</button>
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-default" onclick="shangzhou();">上周</button>
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-default"  onclick="benyue();" >本月</button>
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-default" onclick="shangyue();">上月</button>
                            </div>
                            <div class="form-group">
                                <a href="javascript:;" class="btn btn-primary" onclick="queding();">确定</a>
                            </div>
                        </form>
                    </div>

                    <div class="map_area" id="main"
                         style="height: 500px; width: 950px; border: 1px solid #ccc; padding: 10px;">
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
<script src="<%=basePath %>js/report/report.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath%>js/esl.js"></script>
<script data-require-id="echarts"  src="<%=basePath%>js/echarts.js" async=""></script>
<script data-require-id="echarts/chart/bar" src="<%=basePath%>js/echarts.js" async=""></script>
<script>

    //跳转首页
    function jumpIndex(){
        window.parent.location.href='index.htm';
    }
    function queding(){
        var d1=$("#startTime").val();
        var d2=$("#endTime").val();
        if(date_day(d1,d2)==false){
            showTipAlert("开始时间不能大于结束时间,请重新选择!");
            return false;
        }
        else{
            $("#downform").submit();
        }
    }
    function benzhou(){
//按周日为一周的最后一天计算
        var date = new Date();
        var this_day = date.getDay(); //今天是这周的第几天
        var step_s = -this_day+1; //上周日距离今天的天数（负数表示）
        if (this_day == 0) {
            step_s = -7; // 如果今天是周日
        }
        var step_m = 7 - this_day; // 周日距离今天的天数（负数表示）
        var thisTime = date.getTime();
        var monday = new Date(thisTime +  step_s * 24 * 3600* 1000);
        var sunday = new Date(thisTime +  step_m * 24 * 3600* 1000);
//默认统计一周的时间
        var starttime = transferDate(monday); //本周一的日期 （起始日期）
        var endtime = transferDate(sunday);  //本周日的日期 （结束日期）
        $('#startTime').val(starttime);
        $('#endTime').val(endtime);
        queding();
    }

    function transferDate(date) {
        var yearTemp = date.getFullYear();
        var monthTemp = date.getMonth()+1;
        var dayTemp = date.getDate();
        if(parseInt(monthTemp) < 10) {
            monthTemp = "0" + monthTemp;
        }

        if(parseInt(dayTemp) < 10) {
            dayTemp = "0" + dayTemp;
        }
        return yearTemp + "-" + monthTemp + "-" + dayTemp;
    }


    function getCurrentMonthFirst(){
        var date = new Date();
        var yearTemp = date.getFullYear();
        var monthTemp = date.getMonth()+1;
        if(parseInt(monthTemp) < 10) {
            monthTemp = "0" + monthTemp;
        }
        return yearTemp + "-" + monthTemp + "-01";
    }
    function getCurrentMonthLast(){
        var date = new Date();
        var yearTemp = date.getFullYear();
        var monthTemp = date.getMonth()+1;
        if(parseInt(monthTemp) < 10) {
            monthTemp = "0" + monthTemp;
        }
        return yearTemp + "-" + monthTemp + "-"+date.getDate();
    }

    var now = new Date();                    //当前日期
    var nowDayOfWeek = now.getDay();         //今天本周的第几天
    var nowDay = now.getDate();              //当前日
    var nowMonth = now.getMonth();           //当前月
    var nowYear = now.getYear();             //当前年
    nowYear += (nowYear < 2000) ? 1900 : 0;  //

    var lastMonthDate = new Date();  //上月日期
    lastMonthDate.setDate(1);
    lastMonthDate.setMonth(lastMonthDate.getMonth()-1);
    var lastYear = lastMonthDate.getYear();
    var lastMonth = lastMonthDate.getMonth();



    //获得上月开始时间
    function getLastMonthStartDate(){
        var lastMonthStartDate = new Date(nowYear, lastMonth, 1);
        return formatDate(lastMonthStartDate);
    }
    //获得上月结束时间
    function getLastMonthEndDate(){
        var lastMonthEndDate = new Date(nowYear, lastMonth, getMonthDays(lastMonth));
        return formatDate(lastMonthEndDate);
    }

    function formatDate(date) {
        var myyear = date.getFullYear();
        var mymonth = date.getMonth()+1;
        var myweekday = date.getDate();

        if(mymonth < 10){
            mymonth = "0" + mymonth;
        }
        if(myweekday < 10){
            myweekday = "0" + myweekday;
        }
        return (myyear+"-"+mymonth + "-" + myweekday);
    }

    //获得某月的天数
    function getMonthDays(myMonth){
        var monthStartDate = new Date(nowYear, myMonth, 1);
        var monthEndDate = new Date(nowYear, myMonth + 1, 1);
        var   days   =   (monthEndDate   -   monthStartDate)/(1000   *   60   *   60   *   24);
        return   days;
    }
    function getPreviousFirstDateOfWeek(){
        var theDate = new Date();
        var firstDateOfWeek;
        theDate.setDate(theDate.getDate() - 6 - theDate.getDay());
        firstDateOfWeek = theDate;
        return formatDate(firstDateOfWeek);
    }
    //得到上周的最后一天(周日)
    function getPreviousLastDateOfWeek(){
        var theDate = new Date();
        var firstDateOfWeek;
        theDate.setDate(theDate.getDate() - 6 - theDate.getDay());
        var lastDateOfWeek;
        theDate.setDate(theDate.getDate() +7 - theDate.getDay());
        lastDateOfWeek = theDate;
        return formatDate(lastDateOfWeek);
    }

    function shangzhou(){
        $('#startTime').val(getPreviousFirstDateOfWeek());
        $('#endTime').val(getPreviousLastDateOfWeek());
        queding();
    }
    function benyue(){
        $('#startTime').val(getCurrentMonthFirst());
        $('#endTime').val(getCurrentMonthLast());
        queding();
    }
    function shangyue(){
        $('#startTime').val(getLastMonthStartDate());
        $('#endTime').val(getLastMonthEndDate());
        queding();
    }
    function queding(){
        var d1=$("#startTime").val();
        var d2=$("#endTime").val();
        if(date_day(d1,d2)==false){
            showTipAlert("开始时间不能大于结束时间,请重新选择!");
            return false;
        }
        else{
            $("#downform").submit();
        }
    }
    $(function(){

// 路径配置
        require.config({
            paths:{
                'echarts' : './js/build'//echarts.js的路径
            }
        });
        require(
                [
                    'echarts',
                    'echarts/chart/line'
                ],
                function (ec) {
                    // 基于准备好的dom，初始化echarts图表
                    var myChart = ec.init(document.getElementById('main'));

                    var option = {
                        tooltip : {
                            trigger: 'axis'
                        },

                        calculable : true,
                        xAxis : [
                            {
                                type : 'category',
                                boundaryGap : false,
                                data :${times},
                                name :"日期"
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value',
                                name:"销售量"
                            }
                        ],
                        series :  [
                            {
                                name:'订单',
                                type:'line',
                                data:${counts}
                            }
                        ]
                    };
                    // 为echarts对象加载数据
                    myChart.setOption(option);
                }
        );




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
