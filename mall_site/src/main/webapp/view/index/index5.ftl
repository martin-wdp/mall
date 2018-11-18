<!doctype html>
<html ng-app>
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <title>${topmap.seo.mete!''}</title>
    <meta name="description" content="${(topmap.seo.meteDes)!''}">
    <meta name="keywords" content="${(topmap.seo.meteKey)!''}">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${basePath}/index_five/css/style.css"/>
<#if (topmap.systembase.bsetHotline)??>
    <link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
    <link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
    <style>
        .dropdown-menu {display:block!important;}
        .section-header {
            height: 130px;
            background: url("${basePath}/index_five/css/nav_bg.jpg") no-repeat center bottom!important;
        }
    </style>
</head>

<body>
<#include "newheader5.ftl">
<#include "index5.html"/>
<#include "newbottom.ftl">
</body>
</html>