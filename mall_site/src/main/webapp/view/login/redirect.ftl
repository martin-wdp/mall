<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#assign basePath=request.contextPath>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<#if (topmap.systembase.bsetHotline)??>
	<link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
	<link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
</head>
	
<body>
	<form id="loginfrom" method="post" action="${basePath}/login.html">
			<!--
			<input type="hidden" id="aaa" name="url" value="${url!'index.html'}" />
			-->
			<input type="hidden" id="aaa" name="url" value="${url!topmap.systembase.bsetAddress}" />
	</form>
</body>
<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
<script>
	//$("#loginfrom").submit();
	 var paramStr="url="+$("#aaa").val();
	 //$.ajax({
     //    type: 'post',
     //    url:'../login.html',
     //    data:paramStr,
     //    async:false,
     //    success: function(data) {
     //   	 window.location.href="../login.html";
     //    }
	 //});
	 
	window.location.href="../login.html";
</script>
</html>
