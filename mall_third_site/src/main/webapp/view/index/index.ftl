<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>店铺</title>
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css">
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
</head>
<body>
    <#include "../index/indextop.ftl">
<div class="wp">
    <div class="ui-sidebar">
        <div class="sidebar-nav">
            <ul>
                <li class="active"><a href="javascript:;">整体概况</a></li>
                <li><a href="javascript:;">掌柜任务</a></li>
                <li class="nav-caption">新手起步：</li>
                <li><a href="javascript:;">新手引导</a></li>
                <li><a href="javascript:;">帮助中心</a></li>
            </ul>
        </div>
        <div class="ui-footer"><a href="javascript:;"><#if (sysBasic.temp1)??&&sysBasic??>${sysBasic.temp1}</#if></a></div>
    </div>

    <div class="app show_text" style="display: none;"">
        <div class="app-container">
            <div class="ui-box">
                <ul>
                    <li>
                        <h5><a href="javascript:;">${(yesOrder.orderCount)!'0'}</a></h5>
                        <h6>昨日下单笔数</h6>
                    </li>
                    <li>
                        <h5><a href="javascript:;">&yen;${((yesOrder.orderTotal)!'0.00')}</a></h5>
                        <h6>昨日交易额</h6>
                    </li>
                    <li>
                        <h5><a href="javascript:;">&yen;</a></h5>
                        <h6>可提现金额</h6>
                    </li>
                    <li class="noData">
                        <h5>暂无</h5>
                        <h6>待发货订单</h6>
                    </li>
                </ul>
            </div>

            <div class="ui-block clearfix">
                <div class="page-read">
                    <a href="javascript:;"><img alt="" src="../images/images_01.jpg"/></a>
                </div>

                <ul class="page-boards">
                    <li>
                        <a href="javascript:;">
                            <h5>4月2日 新功能发布</h5>
                            <div class="page-desc">宝贝券已合并至优惠券</div>
                            <span class="hot"></span>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:;">
                            <h5>北京本地品鉴活动</h5>
                            <div class="page-desc">百万流量等你来拿</div>
                            <span class="hot"></span>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:;">
                            <h5>千米会员俱乐部</h5>
                            <div class="page-desc">互通有无，互相帮助</div>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:;">
                            <h5>千米小报</h5>
                            <div class="page-desc">玩法资讯任你看</div>
                        </a>
                    </li>
                </ul>

                <div class="page-faq">
                    <div class="faq-header">
                        <h3>千米神厨</h3>
                        <i>-</i>
                        <a href="javascript:;">更多</a>
                    </div>
                    <ul>
                        <li class="hot"><a href="javascript:;">敬亭分享: 《如何经营粉丝》</a></li>
                        <li class="hot"><a href="javascript:;">千米首页案例墙，等你来上榜！</a></li>
                        <li><a href="javascript:;">千米签到，你用对了吗？</a></li>
                        <li><a href="javascript:;">良品铺子：单日订单破万的秘密</a></li>
                        <li><a href="javascript:;">一个有调性的店铺如何打造？</a></li>
                        <li><a href="javascript:;">汇报我们对“消费者保障”的想法</a></li>
                        <li><a href="javascript:;">白鸦：我们为什么把“免费”去掉</a></li>
                        <li><a href="javascript:;">白鸦：微电商进入“经营人”时代</a></li>
                        <li><a href="javascript:;">交易手续费全额补贴计划</a></li>
                    </ul>
                </div>

                <div class="page-help">
                    <ul>
                        <li>
                            <a href="javascript:;">
                                <i class="case-icon case-icon01"></i>
                                <div class="case-content">
                                    <h4>帮助中心</h4>
                                    <p>教你如何玩转千米</p>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:;">
                                <i class="case-icon case-icon02"></i>
                                <div class="case-content">
                                    <h4>产品教程</h4>
                                    <p>海量教程，再不用担心</p>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:;">
                                <i class="case-icon case-icon03"></i>
                                <div class="case-content">
                                    <h4>千米学堂</h4>
                                    <p>视频教学，你值得拥有</p>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:;">
                                <i class="case-icon case-icon04"></i>
                                <div class="case-content">
                                    <h4>服务市场</h4>
                                    <p>找人帮忙，选好工具</p>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="ui-block pageData">
                <div class="dataHeader">
                    <h3>整体引流统计</h3>
                    <a class="detailLink" href="javascript:;">详细》</a>
                    <div class="dataHelp">
                        <a href="javascript:;" class="help-notes">?</a>
                    </div>
                </div>
                <div class="dataContent">
                    <ul class="chart-info">
                        <li>
                            <h5>3</h5>
                            <h6>昨日页面访问人数</h6>
                        </li>
                        <li>
                            <h5><a href="javascript:;">1</a></h5>
                            <h6>昨日下单笔数</h6>
                        </li>
                        <li>
                            <h5><a href="javascript:;">1</a></h5>
                            <h6>昨日付款订单</h6>
                        </li>
                        <li>
                            <h5><a href="javascript:;">0</a></h5>
                            <h6>昨日发货订单</h6>
                        </li>
                    </ul>
                    <div class="chart-content" id="chartBox">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="service-wrap">
    <span class="service-close">×</span>
    <a href="javascript:;" class="service-box">联系客服</a>
</div>

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
<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<script src="http://echarts.baidu.com/build/dist/echarts-all.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script>
    $(function(){
        var popoverTEmplate = ['<div class="timePickerWrapper popover">',
            '<div class="arrow"></div>',
            '<div class="popover-content">',
            '</div>',
            '</div>'].join('');
        var content = ['<p><strong>页面访问人数：</strong>所有页面的访问人数；</p>',
            '<p><strong>下单笔数：</strong>所有用户的下单总数。</p>',
            '<p><strong>付款订单：</strong>已付款的订单总数；</p>',
            '<p><strong>发货订单：</strong>已发货的订单总数。</p>', ].join('');
        $(".help-notes").popover({
            container: '.dataHelp',
            template: popoverTEmplate,
            content: content,
            placement: 'bottom',
            trigger: 'hover',
            html: true
        });
    });
</script>
<script>
    var myChart = echarts.init(document.getElementById('chartBox'));
    option = {
        legend: {
            data:['页面访问人数','下单笔数','付款订单','发货时间']
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ['2015-04-21','2015-04-22','2015-04-23','2015-04-24','2015-04-25','2015-04-26','2015-04-27']
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'页面访问人数',
                type:'line',
                stack: '总量',
                data:[120, 132, 101, 134, 90, 230, 210]
            },
            {
                name:'下单笔数',
                type:'line',
                stack: '总量',
                data:[220, 182, 191, 234, 290, 330, 310]
            },
            {
                name:'付款订单',
                type:'line',
                stack: '总量',
                data:[150, 232, 201, 154, 190, 330, 410]
            },
            {
                name:'发货时间',
                type:'line',
                stack: '总量',
                data:[320, 332, 301, 334, 390, 330, 320]
            }
        ]
    };

    myChart.setOption(option);

    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);

</script>
</html>
