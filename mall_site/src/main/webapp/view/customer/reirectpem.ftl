<#include "../include/common.ftl">
<@htmlHead title="账户安全-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<style>
    .tips_error{display:none;position:absolute; left:0;top:47px;height:16px;line-height:16px;padding-left:25px;background:url(../images/tips_icon.png) no-repeat left bottom;color:#F15A21;}
    .text_error{border:1px solid #F15A21;color:#F15A21;}
    #sendCode {width:105px;height:20px;background:#dd6330;color:#fff!important;border:none;cursor:pointer;border-radius:2px;}
    .vd_input dd {position:relative; height:50px;}
    .vd_tip {position:absolute; left:0; top:45px; line-height:normal;}
</style>
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>账户安全-${topmap.systembase.bsetName}</title>
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
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<style>
	.tips_error{display:none;position:absolute; left:0;top:47px;height:16px;line-height:16px;padding-left:25px;background:url(../images/tips_icon.png) no-repeat left bottom;color:#F15A21;}
	.text_error{border:1px solid #F15A21;color:#F15A21;}
	#sendCode {width:105px;height:20px;background:#dd6330;color:#fff!important;border:none;cursor:pointer;border-radius:2px;}
	.vd_input dd {position:relative; height:50px;}
	.vd_tip {position:absolute; left:0; top:45px; line-height:normal;}
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
		 <div class="container">
        <div class="location">
        	<a href="${basePath}/customer/index.html"><strong>会员中心</strong></a><span>&gt;</span>
            <a href="${basePath}/customer/myinfo.html">账户中心</a><span>&gt;</span>
            <span>账户安全</span>
        </div>
        <div class="member_box mb20">
        	<#include "leftmenu.ftl" />
	         	<div class="member_right fl ml10">
	                <div class="memebr_title">
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
	                
	                <div class="tips">
                        <strong class="f16 fb mr20">安全级别：</strong>
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
                        <span>建议您启动全部安全设置，以保障账户及资金安全。</span>
                    </div>
                    
                    <ul class="step_bar step_02 clearfix">
                    	<li>1.验证身份</li>
                    	<li class="cur">2.修改<#if type??><#if (type=='pwd')>登录密码</#if><#if (type=='mobile')>手机</#if><#if (type=='email')>邮箱</#if></#if>
                    	</li>
                    	<li>3.完成</li>
                    </ul>
	                <input type="hidden" value="${customerId}" id="hi_id" />
	                <div class="order_list switch_border">
	                    <div class="content">
	                        <div class="layout">
	                            <div class="set_password member_info mb20">
	                                <form method="post" action="${basePath}/validate/modifypem.htm" id="upMobile">
	                                <input value="${(type)!''}" type="hidden" id="e_type" name="type" />
	                                <input type="hidden" id="CSRFToken" name="csrFToken" value="${CSRFToken!''}"/>
	                                <#if (type=='pwd')>
		                                <dl class="vd_input">
		                                    <dt>请输入新密码：</dt>
		                                    <dd>
		                                    	<input class="text yz_text" tabindex="1" name="newStr" id="npwd" type="password" />
			                                    <div class="npwd_tip vd_tip" style="display:none;color:#dd6330">密码错误</div>
		                                    </dd>
		                                </dl>
		                                <dl class="vd_input">
		                                    <dt>请再次输入新密码：</dt>
		                                    <dd>
		                                    	<input class="text yz_text" tabindex="2" id="repwd" type="password" />
		                                    	<div class="repwd_tip vd_tip" style="display:none;color:#dd6330">密码错误</div>
		                                    </dd>
		                                </dl>
		                                <dl class="vd_input">
		                                    <dt>请输入验证码：</dt>
		                                    <dd>
		                                    	<input class="text yz_text"  tabindex="3"id="code" type="text" style="width:110px;" />
		                                    	<label>
						                        	<img id="checkCodeImg" class="vm ml5" src="${basePath}/patchca.htm" alt="验证码" style="cursor:pointer;" onclick="this.src=this.src+'?'+Math.random(); " />
						                        </label>
						                        <label class="small">看不清？<a id="checkCodeA" href="javascript:void(0)">换一张</a></label>
			                                    <div class="code_tip vd_tip" style="display:none;color:#dd6330">验证码错误</div>
		                                    </dd>
		                                </dl>
		                                <dl>
		                                    <dt></dt>
		                                    <dd><input type="button" class="sub_btn" value="提交" onclick="updatepem('${type}')" /></dd>
		                                </dl>
		                                <script>
		                                	document.onkeydown=function(event){
												var e = event || window.event || arguments.callee.caller.arguments[0];
											  	if(e && e.keyCode==13){ // enter 键
											     	updatepem('${type}');
											    }
											}; 
		                                </script>
		                            </#if>
	                            	<#if  (type=='mobile')>
		                                <dl class="vd_input">
		                                    <dt>我的手机号：</dt>
		                                    <dd>
			                                    <input class="text yz_text" name="newStr" maxLength="11" tabindex="1" id="mobile" type="text" />
			                                    <div class="mobile_tip vd_tip" style="display:block;color:#cc0000;line-height:normal;">&nbsp;</div>
			                                    <input type="button" id="sendCode" class="" value="发送短信验证码" onclick="getCode()" /><span  class="resend"></span>
			                                    <div id="m_tip" class="m_tip vd_tip" style="left:0px;top:46px;">
			                                    	校验码已发出，请注意查收短信，如果没有收到，您可以在<span class="timeleft">59</span>秒后要求系统重新发送
			                                    </div>
		                                    </dd>
		                                </dl>
		                                <dl class="vd_input">
		                                    <dt>请输入手机验证码：</dt>
		                                    <dd>
		                                    	<input class="text yz_text" tabindex="2" id="mcode" type="text" style="width:110px;" disabled="disabled" />
			                                    <div class="mcode_tip vd_tip" style="display:none;color:#cc0000">手机验证码错误</div>
		                                    </dd>
		                                </dl>
		                                <dl class="vd_input">
		                                    <dt>请输入验证码：</dt>
		                                    <dd>
		                                    	<input class="text yz_text" tabindex="3" id="code" style="width:110px;" type="text" />
		                                    	<label>
						                        	<img id="checkCodeImg" class="vm ml5" src="${basePath}/patchca.htm" alt="验证码" style="cursor:pointer;" onclick="this.src=this.src+'?'+Math.random(); " />
						                        </label>
						                        <label class="small">看不清？<a id="checkCodeA" href="javascript:void(0)">换一张</a></label>
			                                    <div class="code_tip vd_tip" style="display:none;color:#cc0000">验证码错误</div>
		                                    </dd>
		                                </dl>
		                                <dl>
		                                    <dt></dt>
		                                    <dd><input type="button" class="sub_btn" value="提交" onclick="updatepem('${type}')" disabled="disabled" /></dd>
		                                </dl>
		                                
		                                <script>
		                                	document.onkeydown=function(event){
												var e = event || window.event || arguments.callee.caller.arguments[0];
											  	if(e && e.keyCode==13){ // enter 键
											     	updatepem('${type}');
											    }
											}; 
		                                </script>
		                            </#if>
		                            <#if  (type=='email')>
		                            		<dl class="vd_input">
			                                    <dt>我的邮箱：</dt>
			                                    <dd>
			                                    	<input class="text yz_text" id="email" type="text" />
			                                    	<div class="email_tip vd_tip" style="display:none;color:#cc0000">&nbsp;</div>
		                                    	</dd>
		                                    	<input value="${(custInfo.infoEmail)!''}" type="hidden" id="e_hide" />
			                                </dl>
			                                <dl class="vd_input">
			                                    <dt>请输入验证码：</dt>
			                                    <dd>
			                                    	<input class="text yz_text" id="code" type="text"  style="width:110px;" />
			                                    	<label>
							                        	<img id="checkCodeImg" class="vm ml5" src="${basePath}/patchca.htm" alt="验证码" style="cursor:pointer;" onclick="this.src=this.src+'?'+Math.random(); " />
							                        </label>
							                        <label class="small">看不清？<a id="checkCodeA" href="javascript:void(0)">换一张</a></label>
				                                    <div class="code_tip vd_tip" style="display:none;color:#cc0000">验证码错误</div>
			                                    </dd>
			                                </dl>
			                                <dl>
			                                    <dt></dt>
			                                    <dd><input type="button" id="sendCode" value="发送验证邮件" onclick="updatepem('${type}')" /></dd>
			                                </dl>
			                                <script>
			                                	document.onkeydown=function(event){
													var e = event || window.event || arguments.callee.caller.arguments[0];
												  	if(e && e.keyCode==13){ // enter 键
												     	updatepem('${type}');
												    }
												}; 
			                                </script>
		                            </#if>
		                            </form>
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
    <div class="dialog s_dia dia2">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" alt="" src="${basePath}/images/mod_war.png" />
            	<em>网络连接超时，请您稍后重试</em>
            </div>
            <div class="dia_ops mt20 tr">       
                <a class="go_pay" href="javascript:cls();">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
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
