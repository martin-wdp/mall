<#include "../include/common.ftl">
<@htmlHead title="账户安全-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<style>
    .tips_error{display:none;position:absolute; left:0;top:47px;height:16px;line-height:16px;padding-left:25px;background:url(../images/tips_icon.png) no-repeat left bottom;color:#F15A21;}
    .text_error{border:1px solid #F15A21;color:#F15A21;}
    #sendCode {width:105px;height:20px;background:#dd6330;color:#fff;border:none;cursor:pointer;border-radius:2px;}
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
	#sendCode {width:105px;height:20px;background:#dd6330;color:#fff;border:none;cursor:pointer;border-radius:2px;}
	.vd_input dd {position:relative; height:50px;}
	.vd_tip {position:absolute; left:0; top:45px; line-height:normal;}
</style>
</head>-->
<@htmlBody>
    <div class="container">
    	 <div class="container">
    	<div class="container">
    	<div class="head2">
			<a href="${basePath}/index.html"><img id="logo_pic" alt="" src="" /></a><h1>找回密码</h1>
		</div>
		<#if (user?? && utype??) >
        <div class="member_box mb20">
	         	<div class="member_right ml10" style="width:1040px; margin:0 auto;">
	                <div class="memebr_title">
	                    <h2 class="f16 fb">
	                    	验证身份
	                    </h2>
	                </div>
                    <ul class="step_bar step_01 clearfix">
                    	<li class="cur">1.验证身份</li>
                    	<li>2.修改登录密码</li>
                    	<li>3.完成</li>
                    </ul>
	           
	                <input type="hidden" value="${user.customerId}" id="hi_id" />
	                <div class="order_list switch_border">
	                    <div class="content">
	                        <div class="layout">
	                            <div class="set_password member_info mb20">
	                            	<#if (utype=='mobile')>
	                            		<#if (user.isMobile=='1')>
		                                <dl class="vd_input" style="height:89px;">
		                                    <dt>已验证手机号：</dt>
		                                    <dd>
		                                    	<label>
		                                    		<#assign mo="${user.infoMobile?substring(3,user.infoMobile?length-3)}" />
													<#assign mob="${user.infoMobile?replace(mo,'*****')}" />
													${mob}
		                                    	</label>
		                                    	<#if ((user.isEmail=='1'))><a href="${basePath}/validuser.html?type=email">通过以验证邮箱验证</a></#if>
			                                    <#--<input class="text" id="mobile" type="hidden" value="${user.infoMobile!'123456'}" />-->
			                                    <br />
			                                    <div class="mobile_tip" style="display:block;color:#cc0000;line-height:normal;">&nbsp;</div>
			                                    <input type="button" id="sendCode" class="" value="发送短信验证码" onclick="getCode()" />
			                                    <div id="code_tip">
			                                    	校验码已发出，请注意查收短信，如果没有收到，您可以在<span class="timeleft">59</span>秒后要求系统重新发送
			                                    </div>
		                                    </dd>
		                                </dl>
		                                <dl class="vd_input">
		                                    <dt>请输入手机验证码：</dt>
		                                    <dd>
		                                    	<input class="text yz_text" id="mcode" type="text" style="width:110px;" disabled="disabled" />
			                                    <div class="mcode_tip vd_tip" style="display:none;color:#cc0000">手机验证码错误</div>
		                                    </dd>
		                                </dl>
		                                <dl class="vd_input">
		                                    <dt>请输入验证码：</dt>
		                                    <dd>
		                                    	<input class="text yz_text" id="code" type="text" style="width:110px;" />
		                                    	<label>
						                        	<img id="checkCodeImg" class="vm ml5" src="${basePath}/patchca.htm" alt="验证码" style="cursor:pointer;" onclick="this.src=this.src+'?'+Math.random(); " />
						                        </label>
						                        <label class="small">看不清？<a id="checkCodeA" href="javascript:void(0)">换一张</a></label>
			                                    <div class="code_tipnew vd_tip" style="display:none;color:#cc0000">验证码错误</div>
		                                    </dd>
		                                </dl>
		                                <dl>
		                                    <dt></dt>
		                                    <dd><input type="button" class="sub_btn" value="提交" onclick="tofindpwd()" disabled="disabled" /></dd>
		                                </dl>
		                                <script>
		                                	document.onkeydown=function(event){
												var e = event || window.event || arguments.callee.caller.arguments[0];
											  	if(e && e.keyCode==13){ // enter 键
											     	tofindpwd();
											    }
											}; 
		                                </script>
		                                <#else>
		                                	<#if ((user.isEmail=='1'))>
			                            		<dl>
				                                    <dt>已验证邮箱：</dt>
				                                    <dd>
				                                    	<span>
						                                    <#assign email="${user.infoEmail?substring(1,user.infoEmail?index_of('@')-1)}" />
															<#assign emailc="${user.infoEmail?replace(email,'*****')}" /> 
															${emailc}
														</span>
														<#if (user.isMobile=='1')><a href="${basePath}/validuser.html?type=mobile">通过以验证手机验证</a></#if>
														<#--<input value="${(user.infoEmail)!''}" type="hidden" id="e_hide" />-->
													</dd>
				                                </dl>
				                                <dl class="vd_input">
				                                    <dt>请输入验证码：</dt>
				                                    <dd>
				                                    	<input class="text" id="code" type="text" style="width:110px;" />
				                                    	<label>
								                        	<img id="checkCodeImg" class="vm ml5" src="${basePath}/patchca.htm" alt="验证码" style="cursor:pointer;" onclick="this.src=this.src+'?'+Math.random(); " />
								                        </label>
								                        <label class="small">看不清？<a id="checkCodeA" href="javascript:void(0)">换一张</a></label>
					                                    <div class="code_tipnew vd_tip" style="display:none;color:#cc0000">验证码错误</div>
				                                    </dd>
				                                </dl>
				                                <dl>
				                                    <dt></dt>
				                                    <dd><input type="button" id="sendCode" value="发送验证邮件" onclick="sendEmail()" /></dd>
				                                </dl>
				                                <script>
				                                	document.onkeydown=function(event){
														var e = event || window.event || arguments.callee.caller.arguments[0];
													  	if(e && e.keyCode==13){ // enter 键
													     	sendEmail();
													    }
													}; 
				                                </script>
				                            <#else>
				                            	<div class="reg_success" style="margin-top: 20px;">
											    	<div class="notice2">
											        	很抱歉,您没设置验证手机,也没设置验证邮箱,暂时无法找回密码！<span>
											        </div>
											    </div>
			                            	</#if>
		                                </#if>
		                            <#else>
		                            	<#if ((user.isEmail=='1'))>
		                            		<dl>
			                                    <dt>已验证邮箱：</dt>
			                                    <dd>
			                                    	<span>
					                                    <#assign email="${user.infoEmail?substring(1,user.infoEmail?index_of('@')-1)}" />
														<#assign emailc="${user.infoEmail?replace(email,'*****')}" /> 
														${emailc}
													</span>
													<#if (user.isMobile=='1')><a href="${basePath}/validuser.html?type=mobile">通过以验证手机验证</a></#if>
													<#--<input value="${(user.infoEmail)!''}" type="hidden" id="e_hide" />-->
												</dd>
			                                </dl>
			                                <dl class="vd_input">
			                                    <dt>请输入验证码：</dt>
			                                    <dd>
			                                    	<input class="text" id="code" type="text" style="width:110px;" />
			                                    	<label>
							                        	<img id="checkCodeImg" class="vm ml5" src="${basePath}/patchca.htm" alt="验证码" style="cursor:pointer;" onclick="this.src=this.src+'?'+Math.random(); " />
							                        </label>
							                        <label class="small">看不清？<a id="checkCodeA" href="javascript:void(0)">换一张</a></label>
				                                    <div class="code_tipnew vd_tip" style="display:none;color:#cc0000">验证码错误</div>
			                                    </dd>
			                                </dl>
			                                <dl>
			                                    <dt></dt>
			                                    <dd><input type="button" id="sendCode" value="发送验证邮件" onclick="sendEmail()" /></dd>
			                                </dl>
			                                <script>
			                                	document.onkeydown=function(event){
													var e = event || window.event || arguments.callee.caller.arguments[0];
												  	if(e && e.keyCode==13){ // enter 键
												     	sendEmail();
												    }
												}; 
			                                </script>
			                            <#else>
			                            	<#if (user.isMobile=='1')>
				                                <dl class="vd_input" style="height:89px;">
				                                    <dt>已验证手机号：</dt>
				                                    <dd>
				                                    	<label>
				                                    		<#assign mo="${user.infoMobile?substring(3,user.infoMobile?length-3)}" />
															<#assign mob="${user.infoMobile?replace(mo,'*****')}" />
															${mob}
				                                    	</label>
				                                    	<#if ((user.isEmail=='1'))><a href="${basePath}/validuser.html?type=email">通过以验证邮箱验证</a></#if>
					                                    <#--<input class="text" id="mobile" type="hidden" value="${user.infoMobile!'123456'}" />-->
					                                    <br />
					                                    <div class="mobile_tip" style="display:block;color:#cc0000;line-height:normal;">&nbsp;</div>
					                                    <input type="button" id="sendCode" class="" value="发送短信验证码" onclick="getCode()" />
					                                    <div id="code_tip">
					                                    	校验码已发出，请注意查收短信，如果没有收到，您可以在<span class="timeleft">59</span>秒后要求系统重新发送
					                                    </div>
				                                    </dd>
				                                </dl>
				                                <dl class="vd_input">
				                                    <dt>请输入手机验证码：</dt>
				                                    <dd>
				                                    	<input class="text yz_text" id="mcode" type="text" style="width:110px;" disabled="disabled" />
					                                    <div class="mcode_tip vd_tip" style="display:none;color:#cc0000">手机验证码错误</div>
				                                    </dd>
				                                </dl>
				                                <dl class="vd_input">
				                                    <dt>请输入验证码：</dt>
				                                    <dd>
				                                    	<input class="text yz_text" id="code" type="text" style="width:110px;" />
				                                    	<label>
								                        	<img id="checkCodeImg" class="vm ml5" src="${basePath}/patchca.htm" alt="验证码" style="cursor:pointer;" onclick="this.src=this.src+'?'+Math.random(); " />
								                        </label>
								                        <label class="small">看不清？<a id="checkCodeA" href="javascript:void(0)">换一张</a></label>
					                                    <div class="code_tipnew vd_tip" style="display:none;color:#cc0000">验证码错误</div>
				                                    </dd>
				                                </dl>
				                                <dl>
				                                    <dt></dt>
				                                    <dd><input type="button" class="sub_btn" value="提交" onclick="tofindpwd()" disabled="disabled" /></dd>
				                                </dl>
				                                <script>
				                                	document.onkeydown=function(event){
														var e = event || window.event || arguments.callee.caller.arguments[0];
													  	if(e && e.keyCode==13){ // enter 键
													     	tofindpwd();
													    }
													}; 
				                                </script>
				                            <#else>
				                            	<div class="reg_success" style="margin-top: 20px;">
											    	<div class="notice2">
											        	很抱歉,您没设置验证手机,也没设置验证邮箱,暂时无法找回密码！<span>
											        </div>
											    </div>
			                            	</#if>    
		                            	</#if>
		                            </#if>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                 <div class="explain mt30">
	                    <h2 class="f13 fb">为什么要进行身份验证？</h2>
	                    <div class="body">
	                      	<#if explain??>
			            		<#list "${explain.content}"?split("|") as con>
								<p>${con_index + 1}.${con}</p>
								</#list>
			            	</#if>
	                        <#--<p>1. 为保障您的账户信息安全，在变更账户中的重要信息时需要进行身份验证，感谢您的理解和支持。</p>
	                        <p>2. 验证身份遇到问题？请发送用户名、手机号、历史订单发票至s1028907588@fh.com，客服将尽快联系您。</p>-->
	                    </div>
	                </div>
	            </div><!-- END OF member_right -->
            <div class="cb"></div>
        </div><!-- END OF member_box -->
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
    <div class="mask"></div>
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
<script type="text/javascript" src="${basePath}/js/customer/findpwd.js"></script>
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
