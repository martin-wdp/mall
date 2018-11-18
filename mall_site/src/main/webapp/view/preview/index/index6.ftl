<!doctype html>
<html ng-app>
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <title>${topmap.seo.mete!''}</title>
    <meta name="description" content="${(topmap.seo.meteDes)!''}">
    <meta name="keywords" content="">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${basePath}/index_six/css/style.css"/>
    <style>
        .dropdown-menu {display:block!important;}
    </style>
</head>

<body>
<#include "newheader6.ftl">
<#include "index6_ftl.ftl"/>
<#include "newbottom.ftl">
</body>
</html>