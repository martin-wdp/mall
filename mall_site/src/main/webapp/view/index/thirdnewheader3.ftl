<#assign basePath=request.contextPath>
	<link rel="stylesheet" type="text/css" href="${basePath}/index_three/css/base.min.css"/>
	<link rel="stylesheet" type="text/css" href="${basePath}/index_three/css/style.css"/>
	<#--
	<script src="http://siteapp.baidu.com/static/webappservice/uaredirect.js" type="text/javascript"></script>
	<#if ((mobProjectUrl??) && (mobProjectUrl?length>0))>
                	location = "${mobProjectUrl}";
	<script type="text/javascript">uaredirect(${mobProjectUrl})</script>
    </#if>
	-->
	<style>
	.c999{
		color: #999;
	}
	</style>
	<script>
	// 全选
	function selectAll(objName) {
		var checkboxs = document.getElementsByName(objName);
		var allcheck = document.getElementById("allcheck");
		for (var i = 0; i < checkboxs.length; i++) {
			var e = checkboxs[i];
			e.checked = allcheck.checked;
		}
	}
    function browserRedirect() {
       var url = window.location.href;
       if(url.indexOf("weixin")>-1){
       		url = url.replace(/item/,"mobile/getwxcode3.htm?toUrl=item");
       		if(url.lastIndexOf(".html")==-1){
       			url = url.replace(/#/,".html#");
       		}
       		url = url.substring(0,url.indexOf("#"));
       		location = url;
       }
            var sUserAgent = navigator.userAgent.toLowerCase();
            var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
            var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
            var bIsMidp = sUserAgent.match(/midp/i) == "midp";
            var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
            var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
            var bIsAndroid = sUserAgent.match(/android/i) == "android";
            var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
            var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
            if ( bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {
                //跳转到移动版
                <#if ((mobProjectUrl??) && (mobProjectUrl?length>0))>
                	location = "${mobProjectUrl}";
                </#if>
            }
        }

        browserRedirect();
	</script>
	<input type="hidden" id="basePath" value="${basePath}"/>
	<input type="hidden" id="oldsearchtext" value="${(map.searchBean.title)!''}"/>
	<#include "newtop3.ftl"/>
	
		<#include "thirdnewheader3_ftl.ftl"/>