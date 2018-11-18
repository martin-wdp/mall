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
    	<div class="head2">
		<a href="${basePath}/index.html"><img id="logo_pic" alt="" src="" /></a><h1>找回密码</h1>
	</div>
        <div class="member_box mb20">
	         	<div class="member_right ml10" style="width:1040px; margin:0 auto;">
	                <div class="memebr_title">
	                    <h2 class="f16 fb">
	                    	找回密码
	                    </h2>
	                </div>
                    <ul class="step_bar step_01 clearfix">
                    	<li class="cur">1.验证身份</li>
                    	<li>2.修改登录密码</li>
                    	<li>3.完成</li>
                    </ul>
	                <div class="order_list switch_border">
	                    <div class="content">
	                        <div class="layout">
	                            <div class="set_password member_info mb20">
		                                <dl class="vd_input">
		                                    <dt>请输入用户名：</dt>
		                                    <dd>
		                                    	<input tabindex="1" class="text yz_text" id="username"  />
			                                    <div class="username_tip vd_tip" style="display:none;color:#cc0000">用户名不存在!</div>
		                                    </dd>
		                                </dl>
		                                <dl class="vd_input">
		                                    <dt>请输入验证码：</dt>
		                                    <dd>
		                                    	<input tabindex="2" class="text yz_text" id="code" type="text" style="width:110px;" />
		                                    	<label>
						                        	<img id="checkCodeImg" class="vm ml5" src="${basePath}/patchca.htm" alt="验证码" style="cursor:pointer;" onclick="this.src=this.src+'?'+Math.random(); " />
						                        </label>
						                        <label class="small">看不清？<a id="checkCodeA" href="javascript:void(0)">换一张</a></label>
			                                    <div class="code_tipnew vd_tip" style="display:none;color:#cc0000">验证码错误</div>
		                                    </dd>
		                                </dl>
		                                <dl>
		                                    <dt></dt>
		                                    <dd><input type="button" class="sub_btn" value="提交" onclick="gofindpwd();" /></dd>
		                                </dl>
	                            </div>
	                        </div>
	                    </div>
	                </div>
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
	$.ajax({
		url: '${basePath}/loadlogo.htm', 	
		success: function(data){
		
			$("#logo_pic").prop("src",data.logo.bsetLogo);
		}
	});
	document.onkeydown=function(event){
		var e = event || window.event || arguments.callee.caller.arguments[0];
	  	if(e && e.keyCode==13){ // enter 键
	     	gofindpwd();
	    }
	}; 
</script>
</@htmlBody>
