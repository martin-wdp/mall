<#include "../include/common.ftl">
<@htmlHead title="找回密码">
<link rel="stylesheet" type="text/css" href="${basePath}/css/jd.base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/jd.style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css"/>
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script src="${basePath}/js/jsOperation.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/findcode.js"></script>
</@htmlHead>
<#--<html lang="en">
<head>
<#assign basePath=request.contextPath>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<title>找回密码</title>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/jd.base.min.css"/>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/jd.style.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css"/>
	<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
	<script type="text/javascript" src="${basePath}/js/app.js"></script>
	<script src="${basePath}/js/jsOperation.js"></script>
	<script type="text/javascript" src="${basePath}/js/customer/findcode.js"></script>
</head>-->
<@htmlBody>
<input type="hidden" id="basePath" value="${basePath}"/>

    <#if (topmap.temp)??>
    <#if (topmap.temp.tempId==8)>
        <#include "../index/newtop3.ftl">
    <#elseif (topmap.temp.tempId==9)>
        <#include "../index/newtop4.ftl">
    <#elseif (topmap.temp.tempId==10)>
        <#include "../index/newtop5.ftl">
    <#elseif (topmap.temp.tempId==11)>
        <#include "../index/newtop6.ftl">
    <#elseif (topmap.temp.tempId==12)>
        <#include "../index/newtop7.ftl">
    <#elseif (topmap.temp.tempId==13)>
        <#include "../index/newtop8.ftl">
    <#elseif (topmap.temp.tempId==14)>
        <#include "../index/newtop9.ftl">
    <#elseif (topmap.temp.tempId==15)>
        <#include "../index/newtop8.ftl">
    <#elseif (topmap.temp.tempId==16)>
        <#include "../index/newtop11.ftl">
    <#elseif (topmap.temp.tempId==17)>
        <#include "../index/newtop12.ftl">
    <#elseif (topmap.temp.tempId==18)>
        <#include "../index/newtop13.ftl">
    <#elseif (topmap.temp.tempId==19)>
        <#include "../index/newtop14.ftl">
    <#elseif (topmap.temp.tempId==20)>
        <#include "../index/newtop15.ftl">
    <#else>
        <#include "../index/newtop.ftl"/>
    </#if>
</#if>

<#include "newtop.ftl"/>
    <div class="container pb50">
    	<div class="n_step">
    		<p class="title">找回密码</p>
    		<div class="n_step_con">
    			<div class="n_step1"></div>
    			<ul class="ml10 clearfix">
    				<li class="p100 cur">填写账户名</li>
    				<li class="p130">验证身份</li>
    				<li class="p130">设置新密码</li>
    				<li>完成</li>
    			</ul>
    		</div>
    		<div class="n_password">
    			<div class="n_item clearfix mb20">
    				<span class="label fl">账户名：</span>
    				<div class="fl">
    					<input tabindex="1" type="text" placeholder="已验证的手机号" class="long_text" id="username"/>
    					<div class="ne_tips uesrname_tips hide">您输入的手机号有误</div>
    				</div>
    			</div>
    			<div class="n_item clearfix mb20">
    				<span class="label fl">验证码：</span>
    				<div class="fl">
    					<input tabindex="2" type="text" id="code" placeholder="请输入验证码" class="short_text mr20"/>
    					<img id="checkCodeImg" src="${basePath}/patchca.htm" alt="验证码" style="cursor:pointer;" onclick="this.src=this.src+'?'+Math.random(); "/>
    					<a href="javascript:void(0)" id="checkCodeA" class="ml20 ju_s">换一张</a>
    					<div class="ne_tips patchca_tips hide">您输入的验证码有误</div>
    				</div>
    			</div>
    			<div class="n_item clearfix mb20">
    				<span class="label fl">&nbsp;</span>
    				<div class="fl">
    					<button class="n_nextstep" onclick="gofindcode();">下一步</button>
    				</div>
    			</div>
    		</div>
    	</div><!--n_step-->
    </div>
    
    <div class="bh-mask"></div>
	<div id="ctDia" class="bh-dialog dia0">
	    <div class="dia-tit">
	        <h4>加入收藏</h4>
	        <a class="dia-close" href="javascript:;" onclick="scls(this)"></a>
	    </div>
	    <div class="dia-cont">
	        <p style="text-align: center">请使用菜单栏或Ctrl+D进行添加！</p>
	    </div>
	    <div class="dia-btn"><a href="javascript:;" onclick="scls(this)">确定</a></div>
	</div>
     <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>

<script type="text/javascript">
    document.onkeydown = function (event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 13) { // enter 键
            gofindcode();
        }
    };
</script>
</@htmlBody>