<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城第三方后台-对账报表</title>
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
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/report/thirdreport.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script>
    $(function(){
    	$.material.init();
    	
        $('.datepicker').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            minViewMode: 'months',
            language: 'zh-CN'
        });
    });

    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);
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

    <div class="app show_text" style="display: none;"">
        <div class="app-container">
            <ol class="breadcrumb">
                <li>您所在的位置</li>
                <li>数据分析</li>
                <li style="color: #07d;">对账报表</li>
            </ol>

            <div class="app-content">
                <div class="search-block">
                    <form action="" class="" method="get" id="reportForm">
                        <div class="filter-groups">
                            <div class="control-group">
                                <label class="control-label">分类名称：</label>
                                <div class="controls">
                                    <input class="form-control" name="cateName" value="${map.report.cateName!''}" type="text"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">报表时间：</label>
                                <div class="controls">
                                    <input class="form-control datepicker" value="${map.month!''}" name="month" data-provide="datepicker"/>
                                </div>
                            </div>
                        </div>
                        <div class="search-operation">
                            <button class="btn btn-primary btn-sm" onclick="queryReport()" type="button">查询<i class="glyphicon glyphicon-search"></i></button>
                            <button class="btn btn-default btn-sm" onclick="resetReportForm()" type="button">重置<i class="glyphicon glyphicon-refresh"></i></button>
                            <button class="btn btn-default btn-sm" onclick="exportReport()" type="button">导出<i class="glyphicon glyphicon-save"></i></button>
                        </div>
                    </form>
                </div>

                <div class="cfg-content">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>分类名称</th>
                            <th>总销售额（元）</th>
                            <th>类目扣率</th>
                            <th>应扣金额（元）</th>
                            <th>总商品优惠（元）</th>
                            <th>总订单优惠（元）</th>
                            <th>报表时间</th>
                            <th>结算状态</th>
                        </tr>
                        </thead>
                            <form class="simple_search" action="<#if map.pb.url??>${map.pb.url}<#else></#if>" method="post">
                            </form>
                            <form class="high_search" action="<#if map.pb.url??>${map.pb.url}<#else></#if>" method="post">
                                <#if map.pb.list?size gt 0>
                                    <#list map.pb.list as report>
                                        <tbody>
                                        <tr>
                                            <td>${report_index+1}</td>
                                            <td>${report.cateName!'' }</td>
                                            <td><#if report.totalOrderMoney??>${report.totalOrderMoney?string("0.000") }</#if></td>
                                            <td>${(report.cateRate*100)?string(0.00) }%</td>
                                            <td><#if report.shouldBuckle??>${report.shouldBuckle?string("0.000") }</#if></td>
                                            <td><#if report.totalOrderPrePrice??>${report.totalOrderPrePrice?string("0.000") }</#if></td>
                                            <td><#if report.totalGoodsPrePrice??>${report.totalGoodsPrePrice?string("0.000") }</#if></td>
                                            <td>${report.startTime?string("yyyy-MM-dd") }~${report.endTime?string("yyyy-MM-dd")  }</td>
                                            <td>
                                                <#if report.settleStatus=='0'>
                                                    <i class="glyphicon glyphicon-remove"></i>
                                                <#else>
                                                    <i class="glyphicon glyphicon-ok"></i>
                                                </#if>

                                            </td>
                                        </tr>
                                        </tbody>
                                    </#list>
                            </form>
                    </table>
                    <div class="footer-operation">
                        <div class="ops-left">

                        </div>
                        <div class="ops-right">
                            <nav>
                                <ul class="pagination">
                                    <li>
                                        <a href="javascript:;" aria-label="Previous" onclick="changePageNo(this);" data-role="${map.pb.prePageNo}">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <#if (map.pb.startNo?number>1)>
                                        <li><a href="javascript:;">1</a></li>
                                    </#if>
                                    <#list map.pb.startNo?number .. map.pb.endNo as item>
                                        <li <#if item == map.pb.pageNo>class="active"</#if>><a href="javascript:;" onclick="changePageNo(this);" data-role="${item}">${item}</a></li>
                                    </#list>
                                    <#if (map.pb.totalPages?number>map.pb.endNo)>
                                        <li><a href="javascript:;" onclick="changePageNo(this);" data-role="${map.pb.totalPages}">${pageBean.totalPages}</a></li>
                                    </#if>
                                    <li>
                                        <a href="javascript:;" aria-label="Next" onclick="changePageNo(this);" data-role="${map.pb.nextPageNo}">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                    <script>
                                        /**
                                         * 分页
                                         * author IT_kang
                                         */
                                        function changePageNo(obj){
                                            /*获取查询的方式标记*/
                                            var show_flag=$(".show_flag").val();
                                            if(show_flag==1){
                                                $(".simple_search").append("<input type='hidden' name='pageNo' value='"+$(obj).attr("data-role")+"' />").submit();
                                            }else{
                                                $(".high_search").append("<input type='hidden' name='pageNo' value='"+$(obj).attr("data-role")+"' />").submit();
                                            }
                                        }
                                    </script>
                                </ul>
                            </nav>
                        </div>
                    </div>
                    <#else>
                        <tr>
                            <td colspan="12">暂无信息~</td>
                        </tr>
                    </#if>
                </div>
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
</body>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
</html>
