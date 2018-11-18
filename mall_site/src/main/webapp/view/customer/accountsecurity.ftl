<#include "../include/common.ftl">
<@htmlHead title="账户中心-${topmap.systembase.bsetName}">
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
<title>账户中心-${topmap.systembase.bsetName}</title>
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
    <script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
<style>
	.tips_error{display:none;position:absolute; left:0;top:47px;height:16px;line-height:16px;padding-left:25px;background:url(../images/tips_icon.png) no-repeat left bottom;color:#F15A21;}
	.text_error{border:1px solid #F15A21;color:#F15A21;}
</style>
</head>-->
<@htmlBody>
	<#--一引入头部 <#include "/index/topnew.ftl" /> -->	
		<#if (topmap.temp)??>
			<#if (topmap.temp.tempId==1)>
				<#include "../index/topnew.ftl">
			<#elseif (topmap.temp.tempId==3)>
				<#include "../index/newheader.ftl">
			<#elseif (topmap.temp.tempId==9)>
				<#include "../index/newheader4.ftl">
			<#elseif (topmap.temp.tempId==10)>
				<#include "../index/newheader7.ftl">
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
    <div class="container">
        <div class="location">
        	<a href="${basePath}/customer/index.html"><strong>会员中心</strong></a><span>&gt;</span>
            <a href="${basePath}/customer/myinfo.html">账户中心</a><span>&gt;</span>
            <span>账户安全</span>
        </div>
        <div class="member_box mb20">
        	<#include "leftmenu.ftl" />
	         	<div class="member_right fl ml10">
	                <div class="memebr_title mb20">
	                    <h2 class="f14 fb">账户安全</h2>
	                </div>
	                <div class="safe">
	                    <div class="tips">
	                        <strong class="f16 fb mr20">安全级别：</strong>
	                        <#if  customer.isEmail == '0' &&  customer.isMobile == '0'>
	                        	<em>低级</em>
	                        	<span class="safe_bar low_sf"></span>
	                        <#elseif customer.isEmail == '1' &&  customer.isMobile == '0'>
	                        	<em>中级</em>
	                        	<span class="safe_bar md_sf"></span>
	                        <#elseif customer.isEmail == '0' &&  customer.isMobile == '1'>
	                        	<em>中级</em>
	                        	<span class="safe_bar md_sf"></span>
	                        <#elseif customer.isEmail == '1' &&  customer.isMobile == '1'>
	                        	<em>高级</em>
	                        	<span class="safe_bar hg_sf"></span>
	                        </#if>
	                        <span>建议您启动全部安全设置，以保障账户及资金安全。</span>
	                    </div>
	                    <div class="safe_content">
	                    	<#if !isThirdLogin??>
	                    		<div class="safe_item">
		                            <a class="fr" href="${basePath}/validate/validateidentity.html?type=pwd">修改</a> 
		                            <div class="status_off fl f16 fb">登录密码</div>
		                            <span class="fl"><font color="red">互联网账号存在被盗风险，建议您定期更改密码以保护账户安全。</font></span>
		                        </div>
	                    	</#if>
	                        <div class="safe_item">
	                        	<#if !isThirdLogin??>
		                        	<#if customer.isEmail == '1'>
										<a class="fr" href="${basePath}/validate/validateidentity.html?type=email">修改</a>
									<#else>
										<a class="fr" href="${basePath}/validate/validateidentity.html?type=email">立即验证</a>
									</#if>
								<#else>
									<#if customer.isEmail == '1'>
										<a class="fr" href="${basePath}/validate/reirectpem.html?type=email">修改</a>
									<#else>
										<a class="fr" href="${basePath}/validate/reirectpem.html?type=email">立即验证</a>
									</#if>
	                        	</#if>
	                            <div class="status_off fl f16 fb">邮箱验证</div>
	                            <span class="fl">
	                            	<font color="grey">
	                            		<#if customer.isEmail == '1'>
			                            	<#if customer.infoEmail??>
			                            			
													<#if (customer.infoEmail?trim)?length==0>	
														验证后，可用于快速找回登录密码。
													<#else>
														<#--<#assign email="${customer.infoEmail?substring(1,customer.infoEmail?index_of('@')+1)}" />
														<#assign emailc="${customer.infoEmail?replace(email,'*****')}" /> ${emailc} -->
														您的验证邮箱:${customer.infoEmail?substring(0,1)}*****${customer.infoEmail?substring(customer.infoEmail?index_of('@'),customer.infoEmail?length)}
													</#if>
											<#else>
												验证后，可用于快速找回登录密码。
											</#if>
											
										<#else>
											验证后，可用于快速找回登录密码。
										</#if>
										
									</font>
	                           </span>
	                        </div>
	                        <div class="safe_item">
								<#if !isThirdLogin??>
		                        	<#if customer.isMobile == '1'>
										<a class="fr" href="${basePath}/validate/validateidentity.html?type=mobile">修改</a>
									<#else>
										<a class="fr" href="${basePath}/validate/validateidentity.html?type=mobile">立即验证</a>
									</#if>
								<#else>
									<#if customer.isMobile == '1'>
										<a class="fr" href="${basePath}/validate/reirectpem.html?type=mobile">修改</a>
									<#else>
										<a class="fr" href="${basePath}/validate/reirectpem.html?type=mobile">立即验证</a>
									</#if>
	                        	</#if>
	                            <div class="status_on fl f16 fb">手机验证</div>
	                            <span class="fl">
	                            	<font color="grey">
		                            	<#if customer.isMobile == '1'>
			                            	<#if customer.infoMobile??>
												<#if (customer.infoMobile?trim)?length==0>	
													验证后，可用于快速找回登录密码。
												<#else>
													<#assign mo="${customer.infoMobile?substring(3,customer.infoMobile?length-3)}" />
													<#assign mob="${customer.infoMobile?replace(mo,'*****')}" /> 
													您的验证手机:${mob}
												</#if>
											<#else>
												验证后，可用于快速找回登录密码。
											</#if>
										<#else>
											验证后，可用于快速找回登录密码。
										</#if>
									</font>
	                            </span>
	                        </div>
	                    </div>
	                </div>
	                <div class="explain mt30">
	                    <h2 class="f13 fb">安全服务提示</h2>
	                    <div class="body">
	                    	<#if explain??>
			            		<#list "${explain.content}"?split("|") as con>
								<p>${con_index + 1}.${con}</p>
								</#list>
			            	</#if>
	                    </div>
	                </div>
	            </div><!-- END OF member_right -->
            <div class="cb"></div>
        </div><!-- END OF member_box -->
    </div>
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