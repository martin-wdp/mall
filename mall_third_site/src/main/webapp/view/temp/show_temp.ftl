<!doctype html>
<#assign basePath=request.contextPath>
<html lang="zh-CN">
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
    <style>
    	.table.template-tb th {font-weight:700!important;}
    	.table.template-tb th, .table.template-tb td {border:1px solid #dfdfdf!important;}
    </style>
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
                <li><a href="javascript:;">我的店铺</a></li>
                <li class="active">店铺模板</li>
            </ol>

            <div class="app-content">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="javascript:;">查看模板</a>
                    </li>
                </ul>
                <div class="cont-item">
                    <table class="table table-bordered template-tb">
                        <tbody>
                        <tr>
                            <th>模板图片：</th>
                            <td colspan="3"><img alt="" src="${temp.tempImageUrl}"/></td>
                        </tr>
                        <tr>
                            <th>模板名称：</th>
                            <td>${temp.tempName}</td>
                            <th>轮播广告大图：</th>
                            <td>
                                <#if temp.rollBigAdImage=='1'>
                                    显示
                                <#else>
                                    不显示
                                </#if>
                            </td>
                        </tr>
                        <tr>
                            <th>模板URL：</th>
                            <td>${temp.tempUrl}</td>
                            <th>轮播广告小图：</th>
                            <td>
                                <#if temp.rollSmallAdImage=='1'>
                                    显示
                                <#else>
                                    不显示
                                </#if>
                            </td>
                        </tr>
                        <tr>
                            <th>描述信息：</th>
                            <td>${temp.des}</td>
                            <th>页面图片：</th>
                            <td>
                                <#if temp.indexImage=='1'>
                                    显示
                                <#else>
                                    不显示
                                </#if>
                            </td>
                        </tr>
                        <tr>
                            <th>版本信息：</th>
                            <td>${temp.version}</td>
                            <th>商品分类区域：</th>
                            <td>
                                <#if temp.goodClassifyArea=='1'>
                                    显示
                                <#else>
                                    不显示
                                </#if>
                            </td>
                        </tr>
                        <tr>
                            <th>模板类型：</th>
                            <td>商家首页模板</td>
                            <th>商品分类级数：</th>
                            <td>${temp.goodClassifyLevel}</td>
                        </tr>
                        <tr>
                            <th>楼层：</th>
                            <td>
                                <#if temp.floor=='1'>
                                    显示
                                <#else>
                                    不显示
                                </#if>
                            </td>
                            <th>启用：</th>
                            <td>
                                <#if temp.usedStatus=='1'>
                                    启用
                                <#else>
                                    不启用
                                </#if>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="text-center">
                        <a class="btn btn-primary" href="${basePath}/templist.html">返回</a>
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