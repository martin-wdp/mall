<!doctype html>
<html ng-app>
<head>
<#assign basePath=request.contextPath>
	<meta charset="UTF-8">
    <title>${topmap.seo.mete!''}</title>
    <meta name="description" content="${(topmap.seo.meteDes)!''}">
    <meta name="keywords" content="${(topmap.seo.meteKey)!''}">
    <meta name="renderer" content="webkit">
    <link type="text/css" rel="stylesheet" href="${basePath}/index_ten/css/BreakingNews.css" />
    <link type="text/css" rel="stylesheet" href="${basePath}/index_ten/css/style.css" />
    <#if (topmap.systembase.bsetHotline)??>
    <link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
    <#else>
    <link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
    </#if>
    <style>
        body,html{ background: #f5f5f5!important;}
    </style>
</head>
<body>
<#include "newheader10.ftl">
<#include "index10_ftl.ftl"/>
<#include "newbottom.ftl">
<script>
    $('#breakingnews2').BreakingNews({
        title: '[滚动新闻]',
        titlebgcolor: '#fff',
        linkhovercolor: '#00cccb',
        timer: 4000,
        effect: 'slide'
    });
</script>
</body>
</html>