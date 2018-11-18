<!doctype html>
<html lang="en">
<head>
	<#assign basePath=request.contextPath>
	<meta charset="UTF-8">
	<!--<title>${topmap.systembase.bsetName}</title>-->
	<title>${topmap.seo.mete!''}</title>
	<!--DEMO QQ LOGIN <meta property="qc:admins" content="27416764457661670116375" />-->
	
	<!-- SHOP QQ LOGIN -->
	<meta property="qc:admins" content="27416763170661670116375" />
	<!-- SINA LOGIN -->
	<meta property="wb:webmaster" content="0c64ebf2c46c59c1" />
	
	<meta name="Keywords" content="${(topmap.seo.meteKey)!''}">
	<meta name="description" content="${(topmap.seo.meteDes)!''}">
	<#if (topmap.systembase.bsetHotline)??>
		<link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
	<#else>
		<link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
	</#if>
	
</head>
<body>
	<#include "newheader3.ftl"/> 
	<#include "index3_ftl.ftl"/>  

	<!--引入底部-->
	<#include "newbottom.ftl"/>

    <script type="text/javascript" src="${basePath}/index_three/js/jquery.slides.min.js"></script>
    <script type="text/javascript" src="${basePath}/index_three/js/jcarousellite_1.0.1.js"></script>
    <script type="text/javascript" src="${basePath}/index_three/js/jquery.lazyload.min.js"></script>
    <script type="text/javascript" src="${basePath}/index_three/js/index.js"></script>
</body>
</html>