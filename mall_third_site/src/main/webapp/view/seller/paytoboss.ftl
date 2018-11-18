<!doctype html>
<html lang="zh-CN">
<#assign basePath=request.contextPath>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>我的店铺</title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet"> 
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
</head>
<body>

<#--引入头部-->
<#include "../index/indextop.ftl">
<input type="hidden" value="${basePath}" id="basePath"/>

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
                <li><a href="javascript:;">我的店铺</a></li>
                <li class="active">银行账号验证</li>
            </ol>

            <div class="app-content">
                <ul class="process-nav process-04 clearfix">
                    <li>申请验证</li>
                    <li class="current">待打款</li>
                    <li>已打款</li>
                    <li>验证结果</li>
                </ul>

                <div class="box-wp">
                    <div class="cont-item">
                        <div class="title">
                            <h3>账户信息</h3>
                        </div>
                        <div class="info-cont">
                            <p class="text-success">请打款，以验证账户的真实性。</p>
                            <div class="bg-info account-info-box">
                                <h4>收款银行结算账号信息</h4>
                                <div class="info-item">
                                    <label class="control-label">银行开户名：</label>
                                    <div class="controls">江苏欧飞电子商务有限公司</div>
                                </div>
                                <div class="info-item">
                                    <label class="control-label">公司银行账号：</label>
                                    <div class="controls">01560120210007177</div>
                                </div>
                                <div class="info-item">
                                    <label class="control-label">打款金额：</label>
                                    <div class="controls">1.00</div>
                                </div>
                                <div class="info-item">
                                    <label class="control-label">开户行支行名称：</label>
                                    <div class="controls">南京银行珠江支行</div>
                                </div>
                                <div class="info-item">
                                    <label class="control-label">开户银行所在地：</label>
                                    <div class="controls">江苏南京市</div>
                                </div>

                                <#--<div class="info-item">
                                    <label class="control-label">开户支行联行号：</label>
                                    <div class="controls">622756923157457</div>
                                </div>
                                <div class="info-item">
                                    <label class="control-label">开户行支行名称：</label>
                                    <div class="controls">南京银行</div>
                                </div>
                                <div class="info-item">
                                    <label class="control-label">开户银行所在地：</label>
                                    <div class="controls">江苏 南京</div>
                                </div>-->
                            </div>

                            <div class="bt-operation">
                                <button class="btn btn-primary" type="button" onclick="javascript:window.location.href='paytoboss.html'">确认打款</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#--引入右部与底部-->
<#include "../common/leftfooter.ftl">

<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<!--模板相关END-->
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
<script>
    $(function(){
    	$.material.init();
    });
    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);

</script>
</body>
</html>