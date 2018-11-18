<#include "../include/common.ftl">
<@htmlHead title="操作成功-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/member.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<style>
    .tips_error{display:none;position:absolute; left:0;top:47px;height:16px;line-height:16px;padding-left:25px;background:url(../images/tips_icon.png) no-repeat left bottom;color:#F15A21;}
    .text_error{border:1px solid #F15A21;color:#F15A21;}
</style>
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>操作成功-${topmap.systembase.bsetName}</title>
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
<link rel="stylesheet" type="text/css" href="${basePath}/css/member.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<style>
	.tips_error{display:none;position:absolute; left:0;top:47px;height:16px;line-height:16px;padding-left:25px;background:url(../images/tips_icon.png) no-repeat left bottom;color:#F15A21;}
	.text_error{border:1px solid #F15A21;color:#F15A21;}
</style>
</head>-->
<@htmlBody>
    <div class="container">
    <#--一引入头部 <#include "/index/topnew.ftl" /> -->
    <#if (topmap.temp)??>
        <#if (topmap.temp.tempId==1)>
            <#include "../index/topnew.ftl">
        <#elseif (topmap.temp.tempId==3)>
            <#include "../index/newheader.ftl">
        <#elseif (topmap.temp.tempId==9)>
            <#include "../index/newheader4.ftl">
        <#elseif (topmap.temp.tempId==10)>
            <#include "../index/newheader5.ftl">
        <#elseif (topmap.temp.tempId==11)>
            <#include "../index/newheader6.ftl">
        <#elseif (topmap.temp.tempId==12)>
            <#include "../index/newheader7.ftl">
        <#elseif (topmap.temp.tempId==13)>
            <#include "../index/newheader8s.ftl">
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
            <#include "../index/newheader3.ftl">
        </#if>
    </#if>
        <div class="location">
        	<a href="${basePath}/customer/index.html"><strong>会员中心</strong></a><span>&gt;</span>
            <a href="${basePath}/customer/myinfo.html">账户中心</a><span>&gt;</span>
            <span>账户安全</span>
        </div>
        <div class="member_box mb20">
        	<#include "leftmenu.ftl" />
	         	<div class="member_right fl ml10">
	                <div class="memebr_title mb10">
	                    <h2 class="f14 fb">
	                    	<#if type??>
	                    		<#if (type=='pwd')>
	                    			修改密码
	                    		</#if>
	                    		<#if (type=='mobile')>
	                    			修改手机
	                    		</#if>
	                    		<#if (type=='email')>
	                    			修改邮箱
	                    		</#if>
	                    	</#if>
	                    </h2>
	                </div>
	                <input type="hidden" value="${customerId}" id="hi_id" />
	                <div class="order_list switch_border">
	                    <div class="content">
	                        <div class="layout">
	                            <div class="set_password member_info mb20">
	                                 <div class="success mb20">
	                                 	<div class="scs_box">
		                                	<p class="scs_word">恭喜您，操作成功！</p>
		                                	<p class="safe_level mt10">
		                                		最新安全评级：
		                                		<#if  cust.isEmail == '0' &&  cust.isMobile == '0'>
						                        	<em>低级</em>
						                        	<span class="safe_bar low_sf"></span>
						                        <#elseif cust.isEmail == '1' &&  cust.isMobile == '0'>
						                        	<em>中级</em>
						                        	<span class="safe_bar md_sf"></span>
						                        <#elseif cust.isEmail == '0' &&  cust.isMobile == '1'>
						                        	<em>中级</em>
						                        	<span class="safe_bar md_sf"></span>
						                        <#elseif cust.isEmail == '1' &&  cust.isMobile == '1'>
						                        	<em>高级</em>
						                        	<span class="safe_bar hg_sf"></span>
						                        </#if>
		                                	</p>
		                                	<p class="mt20">您的帐户安全级还能提升哦，快去<a href="${basePath}/customer/securitycenter.html" style="color:#dd6330;">账户安全</a>完善安全设置吧！</p>
		                                </div>
		                            </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                 <!--<div class="explain mt30">
	                    <h2 class="f13 fb">安全服务提示</h2>
	                    <div class="body">
	                        <p>1.确认您登录的是我们商城的网址：http://www.xxx.com，注意防范进入钓鱼网站，不要轻信各种即时通讯工具发送的商品或支付链接，谨防网购诈骗。</p>
	                        <p>2.建议您安装杀毒软件，并定期更新操作系统等软件补丁，确保账户及交易安全。</p>
	                    </div>
	                </div>-->
	            </div><!-- END OF member_right -->
            <div class="cb"></div>
        </div><!-- END OF member_box -->
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
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/validatepwd.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('.item_title').each(function(){
		$(this).click(function(){
			$(this).next().toggle('fast',function(){
				if($(this).is(':visible')){
					$(this).prev().removeClass('up');
					$(this).prev().addClass('down');
				}
				else{
					$(this).prev().removeClass('down');
					$(this).prev().addClass('up');
				}
			});
		});
	});
    $(".pro_sort").addClass("pro_sort_close");
    $(".memeber_left div:eq(2) ul li:eq(1)").addClass("cur");
});
</script>
</@htmlBody>
