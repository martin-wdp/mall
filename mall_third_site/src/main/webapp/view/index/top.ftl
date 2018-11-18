<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>店铺</title>
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
<body>
<div class="header-title-wp clearfix">
    <div class="account">
        <div class="dropdown hover">
            <span><#if customer.customerUsername??>${customer.customerUsername}</#if></span>
                -
            <span class="select-store hide">
                <a href="javascript:;">选择店铺</a>
                -
            </span>
            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
                <span>帮助</span>
            </a>
                -
            <a href="${basePath}/logout.html">退出</a>
            <ul class="dropdown-menu dropdown-menu-right">
                <li><a href="javascript:;">
                        <#if help??>
                            <#list help as he>
                                <#if he??>
                                    <li><a href="<#if siteUrl??>${siteUrl.bsetAddress!''}</#if>/help/${he.helpId!''}-666" target="_blank">${(he.helpTitle)!''}</a></li>
                                </#if>
                            </#list>
                        </#if>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <h1 id="logo">
        <#--<a href="sellerinfo.html?n=2&l=17">-->
            <#if siteUrl.bsetThirdLogo??>
                <img alt="" src="${siteUrl.bsetThirdLogo}" style="max-width:130px;max-height:35px;"/>
            <#else>
                <img alt="" src="${basePath}/images/logo.jpg" style="max-width:130px;max-height:35px;"/>
            </#if>
        <#--</a>-->
    </h1>
    <div class="header-title">店铺</div>
</div>
</body>
</html>
