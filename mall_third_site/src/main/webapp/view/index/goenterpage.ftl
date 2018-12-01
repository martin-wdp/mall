<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>店铺</title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
</head>
<body>
<div class="container">
    <div class="store">
        <div class="wrapper-app">
            <div id="header">
                <div class="header-title-wp clearfix">
                <#include "../index/top.ftl">
                </div>
                <div class="addition">
                    <div class="user-info clearfix">
                        <span class="avatar" style="background-image:url(../images/avatar.png);"></span>
                        <div class="user-info-content">
                            <strong class="user-name"><#if objectMap.customer??>${objectMap.customer.customerNickname!!}<#else>${objectMap.customer.customerUsername!!}</#if></strong>
                            <p class="extra-info">
                                <span>账号：<#if objectMap.customer??>${objectMap.customer.customerUsername!!}<#else></#if></span>
                            <#--<a href="javascript:;">设置</a>-->
                            </p>
                            <div class="store-operation">
                            <#if objectMap.status??>
                                <#if (objectMap.status==0||objectMap.status==3)>
                                    <a href="showprotocol.html" >创建新店铺</a>
                                </#if>`
                            </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="content">
                <div class="store-select">


                    <div class="select-panel clearfix" >

                    <#if (objectMap.status??&&(objectMap.status==0||objectMap.status==3))>
                        <a><div class="store-item new-item" style="cursor:pointer;" onclick="javascript:window.location.href='showprotocol.html'">+创建店铺</div></a>
                    <#else>
                    <#--<div class="store-item "  onclick="javascript:window.location.href='index.html'">-->
                        <div class="store-item ">
                            <div class="store-opt">
                                <a href="sellerinfo.html?n=2&l=17">修改</a>
                            <#--<a href="javascript:;">删除</a>-->
                            </div>
                            <div class="store-user">
                                <p><#if storeName??>${storeName}<#else></#if></p>
                                <span></span>
                            </div>
                            <div class="store-owner">
                            <#--<strong><#if objectMap.customer??>${objectMap.customer.customerNickname}<#else></#if></strong>-->
                                <span style="text-align:center; display:block;">地址：<#if objectMap.storeInfo??&&objectMap.storeInfo.companyAddrDel??>${objectMap.storeInfo.companyAddrDel}<#else></#if></span>
                            <#--<a href="javascript:;" class="edit-company">设置</a>-->
                            </div>
                            <a class="mamage-store-btn" href="${firstUrl!'sellerinfo.html?n=2&l=17'}">管理店铺</a>
                        </div>
                        <#if (objectMap??&&objectMap.days??&& objectMap.days>0&&objectMap.days<30)>
                            <div style="text-align:center;color:red;">您的店铺还有${objectMap.days!''}天到期，请尽快联系平台！</div>
                        <#elseif objectMap??&&objectMap.days??&&objectMap.days==0>
                            <div style="text-align:center;color:red;">今天是您的店铺到期的最后一天，请尽快联系平台！</div>
                        </#if>

                    </#if>
                    <#--<div class="store-item new-item">+创建分销商</div>
                    <div class="store-item new-item">+创建供应商</div>-->
                    </div>
                </div>
            </div>
        </div>
        <div class="footer">
        <#include "foot.ftl">
        </div>
    </div>
</div>
</body>
<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="${basePath}/js/third.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<script>
    $(function(){
        $.material.init();
    });
</script>
</html>
