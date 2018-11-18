<#include "../include/common.ftl">
<@htmlHead title="投诉成功-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>投诉成功-${topmap.systembase.bsetName}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="Keywords" content="${topmap.seo.meteKey}">
<meta name="description" content="${topmap.seo.meteDes}">
<#assign basePath=request.contextPath>
<#if (topmap.systembase.bsetHotline)??>
	<link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
	<link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />

</head>-->
<@htmlBody>
<div class="head2">
	<a href="${basePath}/index.html"><img alt="" id="logo_pic" src="" /></a><h1>投诉成功</h1>
</div>
<div class="reg_box">
	<div class="reg_success">
    	<div class="notice2">
        	<img alt="" src="${basePath}/images/success.png">投诉成功！
        </div>
        <div class="notice3">
        	<a class="common_btn" href="${basePath}/index.html"">立即购物</a>
            <span><strong><span id="time">5</span>秒自动进入<a href="${basePath}/customer/complainlist.html">“投诉列表”</a></strong></span>
        </div>
    </div>
</div>


	<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
	<#--引入底部 <#include "/index/bottom.ftl" /> -->
    <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
	<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script>
	$.ajax({
		url: '../loadlogo.htm', 	
		success: function(data){
			$("#logo_pic").prop("src",data.logo.bsetLogo);
		}
	});
	setTimeout(countDown, 1000);
	function countDown(){
		var time = $("#time").text();
		$("#time").text(time - 1);
		if (time == 1) {
			location.href='${basePath}/customer/complainlist.html';
		} else {
			setTimeout(countDown, 1000);
		}
	}
</script>
</script>
</@htmlBody>
