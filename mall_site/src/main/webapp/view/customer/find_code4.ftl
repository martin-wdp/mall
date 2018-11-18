<#include "../include/common.ftl">
<@htmlHead title="找回密码">
<link rel="stylesheet" type="text/css" href="${basePath}/css/jd.base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/jd.style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css"/>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/app.js"></script>
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
	<#if (topmap.systembase.bsetHotline)??>
	<link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
	<#else>
	<link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
	</#if>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/app.js"></script>
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
            <#include "../index/newheader9.ftl">
        <#elseif (topmap.temp.tempId==15)>
            <#include "../index/newheader10.ftl">
        <#elseif (topmap.temp.tempId==16)>
            <#include "../index/newheader11.ftl">
        <#elseif (topmap.temp.tempId==17)>
            <#include "../index/newheader12.ftl">
        <#elseif (topmap.temp.tempId==18)>
            <#include "../index/newheader13.ftl">
        <#elseif (topmap.temp.tempId==19)>
            <#include "../index/newheader14.ftl">
        <#elseif (topmap.temp.tempId==20)>
            <#include "../index/newheader15.ftl">
        <#else>
            <#include "../index/newtop.ftl"/>
        </#if>
    </#if>
<#include "newtop.ftl"/>
    <div class="container pb50" id="findCodeContainer">
    	<#if user?? >
    	<div class="n_step">
    		<p class="title">找回密码</p>
    		<div class="n_step_con">
    			<div class="n_step4"></div>
    			<ul class="ml10 clearfix">
    				<li class="p100 prev">填写账户名</li>
    				<li class="p130 prev">验证身份</li>
    				<li class="p130 prev">设置新密码</li>
    				<li class="cur">完成</li>
    			</ul>
    		</div>
    		<div class="n_password">
    			<div class="n_set_over">
                    <p class="gx_nin">恭喜您，重置密码成功！</p> 
                    <p class="col9 pt10">请牢记您新设置的密码。<a href="${basePath}/login.html" class="f14 btnSty">返回登录页</a></p>
                </div>
    		</div>
    	</div><!--n_step-->
    	<#else>
    	<div class="reg_success">
			    	<div class="notice2">
			        	<img alt="" src="${basePath}/images/mod_war.png">参数异常！<span>
			        </div>
			        <div class="notice3">
			            <strong><span id="time">5</span>秒自动进入<a href="${basePath}/index.html">“首页”</a></strong></span>
			        </div>
		</div>
		</#if>
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
<#include "../index/newbottom.ftl" />
<script type="text/javascript">	
    setTimeout(countDown, 1000);
    setFindCodeContainerMinHeight();
	function countDown(){
		var time = $("#time").text();
		$("#time").text(time - 1);
		if (time == 1) {
			location.href='${basePath}/index.html';
		} else {
			setTimeout(countDown, 1000);
		}
	};

    /* *
     * 计算忘记密码的最小高度，避免网站底部出现空白区域
     * 2015.11.04 wuyanbo add
     * */
    function setFindCodeContainerMinHeight(){
        var bodyHeight = $(window).height();//当前屏幕的可视区域高度
        var topHeight = 120;
        var bottomHeight = 450;
        var findCodeHeight = (bodyHeight - topHeight- bottomHeight -30);
        if(findCodeHeight <= 350){
            findCodeHeight = 350;
        }
        $("div[id='findCodeContainer']").css({"min-height":findCodeHeight+"px"});
    }
</script>
</@htmlBody>