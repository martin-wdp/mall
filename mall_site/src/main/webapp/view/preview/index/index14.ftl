<!doctype html>
<html ng-app>
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <title>${topmap.seo.mete!''}</title>
    <meta name="description" content="${(topmap.seo.meteDes)!''}">
    <meta name="keywords" content="${(topmap.seo.meteKey)!''}">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${basePath}/index_fourteen/css/style.css"/>
    <#if (topmap.systembase.bsetHotline)??>
    <link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
	<#else>
    <link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
	</#if>
    <style>
        .dropdown-menu {display:block!important;}
    </style>  
</head>
<body>
<#include "newheader14.ftl"/>
<#include "index14_ftl.ftl"/>
<#include "newbottom.ftl"/>
</body>
</html>
