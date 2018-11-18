<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城第三方后台-订单数据分析</title>
<#assign basePath=request.contextPath>
<link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" >
<link href="${basePath}/css/material.css" rel="stylesheet">
<link href="${basePath}/css/ripples.css" rel="stylesheet">
<link rel="stylesheet" href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css"/>
<link rel="stylesheet" href="${basePath}/css/summernote.css"/>
<link rel="stylesheet" href="${basePath}/css/style.css"/>


<script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/build/dist/echarts-all.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/third.js"></script>
<script charset="utf-8" src="${basePath}/plugin/kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="${basePath}/plugin/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="${basePath}/plugin/kindeditor/lang/kindeditor.js"></script>
<script type="text/javascript" src="${basePath}/js/esl.js"></script>
<script type="text/javascript" src="${basePath}/js/expand/expand.js"></script>
<script data-require-id="echarts" src="http://echarts.baidu.com/build/echarts.js" async=""></script>
<script data-require-id="echarts/chart/bar" src="http://echarts.baidu.com/build/echarts.js" async=""></script>
<script>
    $(function(){
		$.material.init();

        /*循环判断所有的下拉列表 相等就选中*/
        var obj = document.getElementsByTagName("option")
        for(var i=0;i<obj.length;i++){
            if(obj[i].value== $('#select_index').val()){
                obj[i].selected=true;  //相等则选中
            }
        }

        $('.datepicker').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            language: 'zh-CN'
        });
    });
</script>
</head>

<body>
<#--引入头部-->
<#include "../index/indextop.ftl">

<div class="wp">
    <div class="ui-sidebar">
        <div class="sidebar-nav">
        <#import "../index/indexleft.ftl" as leftmenu>
            <@leftmenu.leftmenu '${basePath}' />
        </div>
    </div>

    <div class="app">
        <div class="app-container">
            <ol class="breadcrumb">
                <li>您所在的位置</li>
                <li>数据分析</li>
                <li style="color: #07d;">订单数据分析</li>
            </ol>

            <div class="app-content">
                <div class="chart-operation">
                    <form action="queryOrderCountByDay.htm" method="post" id="downform">
                        <input type="hidden" value="${isthird?default('')}" name="isthird" id="isthird"/>
                        <input type="hidden" value="${selectindex?default('')}"  id="select_index"/>
                        选择分类：
                        <select class="form-control sm-sel" id="search_by_time" name="select_index" onChange="searchbytime()">
                            <option value="0">-请选择-</option>
                            <option value="1">本周</option>
                            <option value="2">上周</option>
                            <option value="3">本月</option>
                            <option value="4">上月</option>
                            <option value="5">全网</option>
                            <option value="6">本店</option>
                        </select>
                        <input class="form-control sm-input datepicker" value="${startTime!''}" name="startTime" id="d4311" data-provide="datepicker"
                               onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'2099-10-01\'}'}"
                                />
                        -
                        <input class="form-control sm-input datepicker" value="${endTime!''}" name="endTime" id="d4312"  data-provide="datepicker"
                               onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})"
                                />
                        <button class="btn btn-primary btn-sm" onclick="queding();" type="button">搜索</button>
                    </form>
                </div>

                <h3 class="chart-caption">订单数据分析</h3>

                <div class="data-chart-cont" id="chartCont">
                    <div class="mt15 mb20 ml20 mr20" id="main" style="width: 960px; height: 330px; cursor: default; background-color: rgba(0, 0, 0, 0);" _echarts_instance_="1418354017304">
                        <div style="position: relative; overflow: hidden; width: 960px; height: 330px;"><div width="960" height="330" data-zr-dom-id="bg" style="position: absolute; left: 0px; top: 0px; width: 960px; height: 330px; -webkit-user-select: none;"></div>
                            <canvas width="960" height="330" data-zr-dom-id="0" style="position: absolute; left: 0px; top: 0px; width: 960px; height: 330px; -webkit-user-select: none;"></canvas>
                            <canvas width="960" height="330" data-zr-dom-id="1" style="position: absolute; left: 0px; top: 0px; width: 960px; height: 330px; -webkit-user-select: none;"></canvas>
                            <canvas width="960" height="330" data-zr-dom-id="2" style="position: absolute; left: 0px; top: 0px; width: 960px; height: 330px; -webkit-user-select: none;"></canvas>
                            <canvas width="960" height="330" data-zr-dom-id="3" style="position: absolute; left: 0px; top: 0px; width: 960px; height: 330px; -webkit-user-select: none;"></canvas>
                            <canvas width="960" height="330" data-zr-dom-id="8" style="position: absolute; left: 0px; top: 0px; width: 960px; height: 330px; -webkit-user-select: none;"></canvas>
                            <canvas width="960" height="330" data-zr-dom-id="_zrender_hover_" style="position: absolute; left: 0px; top: 0px; width: 960px; height: 330px; -webkit-user-select: none;">

                            </canvas><div style="position: absolute; display: none; border: 0px solid rgb(51, 51, 51); white-space: nowrap; transition: left 0.4s, top 0.4s; -webkit-transition: left 0.4s, top 0.4s; border-top-left-radius: 4px; border-top-right-radius: 4px; border-bottom-right-radius: 4px; border-bottom-left-radius: 4px; color: rgb(255, 255, 255); text-decoration: none; font-family: Arial, Verdana, sans-serif; font-size: 12px; line-height: 18px; font-style: normal; font-weight: normal; padding: 5px; left: 291px; top: 77px; background-color: rgba(0, 0, 0, 0.701961);">
                                2014-04-15<br>
                                搜索引擎 : 60<br>
                                直接访问 : 100<br>
                                视频广告 : 20<br>
                                联盟广告 : 50<br>
                                邮件营销 : 20
                            </div>
                        </div>
                    </div>
                    <script type="text/javascript">
                        // 路径配置
                        require.config({
                            paths:{
                                'echarts' : 'http://echarts.baidu.com/build/echarts',
                                'echarts/chart/bar' : 'http://echarts.baidu.com/build/echarts'
                            }
                        });
                        require(
                                [
                                    'echarts',
                                    'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
                                ],
                                function (ec) {
                                    // 基于准备好的dom，初始化echarts图表
                                    var myChart = ec.init(document.getElementById('chartCont'));

                                    var option = {
                                        tooltip : {
                                            trigger: 'axis'
                                        },

                                        calculable : true,
                                        xAxis : [
                                            {
                                                type : 'category',
                                                boundaryGap : false,
                                                data : ${times}
                                            }
                                        ],
                                        yAxis : [
                                            {
                                                type : 'value'
                                            }
                                        ],
                                        series :  [
                                            {
                                                name:'订单',
                                                type:'line',
                                                stack: '总量',
                                                data:${ccounts}
                                            }
                                        ]
                                    };
                                    // 为echarts对象加载数据
                                    myChart.setOption(option);
                                }
                        );
                    </script>
                </div>

                <table class="table">
                    <thead>
                    <tr>
                        <th>排名</th>
                        <th>时间</th>
                        <th>数量</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#if orderList??&&orderList?size!=0>
                            <#list orderList as order>
                            <tr class="neir">
                                <td>${order_index+1}</td>
                                <td>${order.ctime!''}</td>
                                <td>${order.countSum}</td>
                                <td>&nbsp;</td>
                            </tr>
                            </#list>
                        </#if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<#--<div class="service-wrap">-->
    <#--<span class="service-close">×</span>-->
    <#--<a href="javascript:;" class="service-box">联系客服</a>-->
<#--</div>-->

<div class="back-to-top">
    <a href="javascript:;"><i></i>返回顶部</a>
</div>

<#--<div class="notice-center">-->
    <#--<div class="notice-center-ring"><i></i></div>-->
    <#--<div class="notice-center-wrapper">-->
        <#--<div class="nt-header">-->
            <#--<h3>系统通知（<span>1</span>）</h3>-->
            <#--<a href="javascript:;" class="nt-close">收起》</a>-->
        <#--</div>-->
        <#--<ul class="nt-content">-->
            <#--<li>-->
                <#--<div class="nt-item unread">-->
                    <#--<p>刘仙升于2015-04-08 15:41:23申请提现1.00元，已提现成功，请注意查询您的银行账户。</p>-->
                    <#--<a href="javascript:;">查看提现记录 》</a>-->
                <#--</div>-->
            <#--</li>-->
        <#--</ul>-->
        <#--<div class="nt-footer">-->
            <#--<a href="javascript:;" class="mark-read">全部标记已读</a>-->
            <#--<a href="javascript:;" class="nt-all">查看全部信息</a>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->

<#--<div class="page-help-btn">帮助</div>-->
<div class="page-help-container">
    <div class="page-help-content">
        <p style="color:#f00;">不知道从哪里开始？</p>
        <p>完成掌柜任务，简简单单开店铺！</p>
        <p>点击开始》》<a href="javascript:;">掌柜成长之旅</a></p>
    </div>
    <div class="page-help-operation">
        <a href="javascript:;" class="btn btn-primary btn-sm">进入帮助中心</a>
        <a href="javascript:;" class="btn btn-default btn-sm">建议反馈</a>
    </div>
</div>
<script type="text/javascript">

    //根据下拉列表值进行搜索
    function searchbytime(){
        var search_by_time = $("#search_by_time").val();
        if(search_by_time==1){
            benzhou(); //本周
        }else if(search_by_time==2){
            shangzhou();//上周
        }else if(search_by_time==3){
            benyue();//本月
        }else if(search_by_time==4){
            shangyue();//上月
        }else if(search_by_time==5){
            dianpu('1'); //店铺
        }else if(search_by_time==6){
            dianpu(); //店铺
        }
    }

    function queding(){
        $("#downform").submit();
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
        $('#d4311').val(starttime);
        $('#d4312').val(endtime);
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

    function benyue(){
        $('#d4311').val(getCurrentMonthFirst());
        $('#d4312').val(getCurrentMonthLast());
        queding();
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

    function shangyue(){
        $('#d4311').val(getLastMonthStartDate());
        $('#d4312').val(getLastMonthEndDate());
        queding();
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
        $('#d4311').val(getPreviousFirstDateOfWeek());
        $('#d4312').val(getPreviousLastDateOfWeek());
        queding();
    }

    function dianpu(isthird){
        $("#isthird").val(isthird);
        queding();

    }
</script>
</body>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
</html>
