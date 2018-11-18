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
            <span></span>
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

                    </a>
                </li>
            </ul>
        </div>
    </div>
    <h1 id="logo">
        <#--<a href="sellerinfo.html?n=2&l=17">-->

        <#--</a>-->
    </h1>
    <div class="header-title">企业认证</div>
</div>
</body>
</html>
