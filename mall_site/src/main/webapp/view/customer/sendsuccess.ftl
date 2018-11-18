<#include "../include/common.ftl">
<@htmlHead title="发送成功-${topmap.systembase.bsetName}">
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
<title>发送成功-${topmap.systembase.bsetName}</title>
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
    	<div class="head2">
			<a href="${basePath}/index.html"><img id="logo_pic" alt="" src="" /></a><h1>找回密码</h1>
		</div>
		<#if user??>
         <div class="member_box mb20">
	         	<div class="member_right ml10" style="width:1040px; margin:0 auto;">
	                <div class="memebr_title mb20">
	                    <h2 class="f14 fb">
	                    	<#if eFlag?? >
		                    	<#if (eFlag==-1) >
	                    			邮件失效
	                    		<#elseif (eFlag==-2)  >
	                    			数据异常
	                    		</#if>
	                    	<#else>
	                    		发送成功
	                    	</#if>
	                    	
	                    </h2>
	                </div>
	                <div class="order_list switch_border">
	                    <div class="content">
	                        <div class="layout">
	                            <div class="set_password member_info mb20">
	                                 <div class="success mb20">
		                                 <#if eFlag??>
		                                 	<#if (eFlag==-2) >
			                                 	<div class="reg_success">
											    	<div class="notice2">
											        	<img alt="" src="${basePath}/images/mod_war.png">参数异常！<span>
											        </div>
											        <div class="notice3">
											            <strong><span id="time">5</span>秒自动进入<a href="${basePath}/index.html">“首页”</a></strong></span> 
											        </div>
											    </div>
		                                 	</#if>
		                                 	<#if (eFlag==-1) >
				                                <div class="err_box" style="height: 80px;line-height: 80px;" >
				                                	<p class="scs_word">对不起,邮件已失效!</p>
				                                </div>
			                                </#if>
			                             <#else>
			                             	<div class="scs_box" style="height: 80px;line-height: 80px;" >
			                                	<p class="scs_word">恭喜您，邮件发送成功！</p>
			                                </div>
		                                 </#if>
		                                
		                            </div>
	                            </div>
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
	                       <#-- <p>1.确认您登录的是我们商城的网址：http://www.xxx.com，注意防范进入钓鱼网站，不要轻信各种即时通讯工具发送的商品或支付链接，谨防网购诈骗。</p>
	                        <p>2.建议您安装杀毒软件，并定期更新操作系统等软件补丁，确保账户及交易安全。</p>-->
	                    </div>
	                </div>
	            </div><!-- END OF member_right -->
            <div class="cb"></div>
        </div><!-- END OF member_box -->
        <#else>
        	<div class="reg_success">
		    	<div class="notice2">
		        	<img alt="" src="${basePath}/images/failed.png">参数异常！<span>
		        </div>
		        <div class="notice3">
		            <strong><span id="time">5</span>秒自动进入<a href="${basePath}/index.html">“首页”</a></strong></span> 
		        </div>
		    </div>
        </#if>
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
setTimeout(countDown, 1000);
	function countDown(){
		var time = $("#time").text();
		$("#time").text(time - 1);
		if (time == 1) {
			location.href='${basePath}/index.html';
		} else {
			setTimeout(countDown, 1000);
		}
	}
$.ajax({
		url: '${basePath}/loadlogo.htm', 	
		success: function(data){
		
			$("#logo_pic").prop("src",data.logo.bsetLogo);
		}
	});
</script>
</@htmlBody>
