<!DOCTYPE HTML>
<head>
<#assign basePath=request.contextPath>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=0.9,user-scalable=no"/>
<meta name="MobileOptimized" content="320">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="${basePath}/css/suppliers.css">
<link href="${basePath}/css/style.css" rel="stylesheet">
 <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
<body>
<#include "../publicHeader2_ftl.ftl" />
<div class="enterCert">
    <#if submitFlag=='0'>
        <div class="prompt">
            <div class="box">
                </br>
                </br>
                <h3>${enInfo!''}</h3>
                <button onclick="jumpUrl('${basePath}/${jumpUrl}')">${jumpName!''}</button>
            </div>
        </div>
    </#if>
<#if submitFlag=='1'>
<div class="prompt">
	<div class="box">
		<img src="images/qp_cxcg.png"><br>
		<h3>恭喜您成功提交认证</h3>
		<p>我们将在24小时内完成审核，请您耐心等待</p>
		<span><i id="time">2</i>s后自动跳转我的认证信息</span>
	</div>
</div>
</#if>

</body>
<script src="${basePath}/js/customer/jquery-1.11.3.min.js"></script>
<script src="${basePath}/mobile_home_page/js/jquery1.7.2.js"></script>
<script src="${basePath}/js/customer/ajaxMutiUploadFile.js"></script>
<script src="${basePath}/js/customer/public.js"></script>
<script src="${basePath}/js/customer/newjs/suppliers/suppliers-logic.js"></script>
<script src="${basePath}/js/customer/newjs/suppliers/suppliers-effect.js"></script>


<script>
    function jumpUrl(url){
        window.location.href=url;
    }
    $(function(){
        var submitFlag = '${submitFlag!'0'}';
        var jumpUrl = '${jumpUrl}';
        if(submitFlag=="1"){
            var t = $("#time").text();
            var timer = setInterval(function () {
                t--;
                $("#time").text(t);
                if (t == 0) {
                    clearInterval(timer);
                    window.location.href=jumpUrl;
                }
            }, 1000);
        }
    });
</script>