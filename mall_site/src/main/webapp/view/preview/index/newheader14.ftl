<#assign basePath=request.contextPath>
<script>
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
<#include "newtop14.ftl"/>
<#include "newheader14_ftl.ftl"/>
<style>
.showlist {z-index:9997!important;}
</style>
<input type="hidden" id="basePath" value="${basePath}"/>
<input type="hidden" id="currentProvince" value="${chProvince!''}" />
<input type="hidden" id="oldsearchtext" value="${(map.searchBean.title)!''}"/>