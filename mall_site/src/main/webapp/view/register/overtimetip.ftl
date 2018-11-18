<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统提示-${topmap.systembase.bsetName}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="description" content="${topmap.systembase.bsetName}">
<#assign aa=topmap.systembase.bsetDesc >
<#if (aa?index_of('>',0) != -1)>
	<#assign bb=aa?substring(aa?index_of('>',0)+1,aa?index_of('<',1)) >
<#else>
	<#assign bb=aa >
</#if>
<meta name="Keywords" content="${bb}">
<#assign basePath=request.contextPath>
<#if (topmap.systembase.bsetHotline)??>
	<link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
	<link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />

</head>

<body>
<div class="head2">
	<a href="${topmap.systembase.bsetAddress}"><img id="logo_pic" alt="" src="" /></a><h1>系统提示</h1>
</div>
<div class="reg_box">
	<div class="reg_success">
    	<div class="notice2">
        	<img alt="" src="${basePath}/images/success.png">温馨提示！
        </div>
        <div class="notice3">
        	<a class="common_btn">您的店铺已过期！</a>
            <span><strong><span id="time">10</span>秒自动跳转<a href="${topmap.systembase.bsetAddress}">首页</a></strong></span>
        </div>
    </div>
        </form>
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
<script>
	setTimeout(countDown, 1000);
	function countDown(){
		var time = $("#time").text();
		$("#time").text(time - 1);
		if (time == 1) {
			location.href='${topmap.systembase.bsetAddress}';
		} else {
			setTimeout(countDown, 1000);
		}
	}
	$.ajax({
		url: 'loadlogo.htm', 	
		success: function(data){
			$("#logo_pic").prop("src",data.logo.bsetLogo);
		}
	});
</script>
</body>
</html>
