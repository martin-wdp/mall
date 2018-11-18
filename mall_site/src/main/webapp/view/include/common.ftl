<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<#assign basePath=request.contextPath>
<#macro htmlHead title>
<html xmlns="http://www.w3.org/1999/xhtml" ng-app>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>${title}</title>
    <#if (onePage.keyword)?? && (onePage.keyword?length>0)>
        <meta name="Keywords" content="${onePage.keyword}-${topmap.seo.meteKey}">
    <#else>
        <meta name="Keywords" content="${topmap.seo.meteKey}">
    </#if>
    <#if (onePage.description)?? && (onePage.description?length>0)>
        <meta name="description" content="${onePage.description}-${topmap.seo.meteDes}">
    <#else>
        <meta name="description" content="${topmap.seo.meteDes}">
    </#if>
    <meta name="renderer" content="webkit">
<#if (topmap.systembase.bsetHotline)??>
    <link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
    <link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
<script type="text/javascript" src="${basePath}/js/jquery-1.11.1.min.js"></script>
<#nested>
</head>
</#macro>

<#macro htmlBody>
    <body>
        <#nested>
    </body>
</html>
</#macro>