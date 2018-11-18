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
                <li>我的店铺</a></li>
                <li class="active" style="color: #07d;">店铺模板</li>
            </ol>

            <div class="app-content">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="javascript:;">店铺模板设置</a>
                    </li>
                </ul>
                <div class="cont-item">
                    <div class="title">
                        <h3>店铺模板</h3>
                    </div>
                    <div class="info-cont">
                    <#list indexList as temp>
                        <div class="template-view">
                            <#--<a class="view-img" data-toggle="modal" data-target="#bigImg">-->
                                <#--<img alt="" src="${temp.tempImageUrl}"/>-->
                                <#--<span><i class="glyphicon glyphicon-fullscreen"></i></span>-->
                            <#--</a>-->
                            <div class="temp-ops">
                                <a class="btn btn-primary" href="${basePath}/tempclassifybarlist.html?tempId=${temp.tempId}">店铺首页内容配置</a>
                               <#--<a class="btn btn-primary" href="${basePath}/refurbishThird.htm">刷新模板</a>-->
                            </div>
                        </div>
                        <#--<div class="modal fade" id="bigImg" role="dialog" aria-hidden="true">-->
                            <#--<div class="modal-dialog modal-lg">-->
                                <#--<div class="modal-content">-->
                                    <#--<div class="modal-body">-->
                                        <#--<img src="${temp.tempImageUrl}" alt="" width="100%"/>-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                        <#--</div>-->
                    </#list>
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