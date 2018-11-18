<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>注册-${topmap.systembase.bsetName}</title>
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
<#if (topmap.temp.tempId==14)>
	<link rel="stylesheet" type="text/css" href="${basePath}/index_nine/css/template.css"/>
</#if>
<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/js/valid_form.js"></script>
<style>
/* 弹窗 */
.mask {width:100%; height:100%; background:#000; opacity:0.5; filter:alpha(opacity=50); position:fixed; top:0; left:0; z-index:9998; display:none;}
.dialog {position:fixed; background:#fff; z-index:9999; width:440px; min-height:230px; padding:5px; display:none;}
.dia_tit {height:30px; line-height:30px; padding:0 20px; font-family:microsoft YaHei; font-size:14px; color:#fff;}
.dia_close {width:15px; height:15px; background:url(${basePath}/images/dia_close.png) no-repeat; margin-top:7px;}
.dia_intro em {display:inline-block; font-family:microsoft YaHei; font-size:18px; color:#575757; -webkit-transform:rotate(-10deg); -moz-transform:rotate(-10deg);}
.go_shopping, .go_pay {display:inline-block; zoom:1; *display:inline; width:100px; height:28px; text-align:center; line-height:27px; font-family:microsoft YaHei; font-size:14px; color:#fff!important; margin:0 5px;}
.go_shopping {background:url(${basePath}/images/grey_btn.gif) no-repeat;}
.go_pay {background:url(${basePath}/images/org_btn.gif) no-repeat;}
.agreement_dia {width:910px; height:490px; border:5px solid rgba(238,238,238,.5); padding:0;}
.agreement_dia .dia_tit {background:#eee; text-align:center; font-size:14px; font-weight:700; color:#666;}
.agreement_dia .dia_close {position:absolute; top:8px; right:20px; margin-top:0; background:url(${basePath}/images/agree_close.gif) no-repeat;}
.agreement_wp {height:360px; overflow-y:scroll; padding:0 20px;}
.agreement_wp h4 {font-weight:700; line-height:180%;}
.agreement_wp p {line-height:180%;}
.agree_btn {display:inline-block; zoom:1; *display:inline; width:200px; height:30px; line-height:29px; background:url(${basePath}/images/agree_btn.gif) no-repeat; font-family:microsoft YaHei; font-size:16px; color:#fff!important;}
	
</style>
</head>

<body>
<div class="head2">
	<!--
	<a href="${basePath}/index.html"><img id="logo_pic" alt="" src="" /></a><h1>欢迎注册</h1>
	-->
	<a href="${topmap.systembase.bsetAddress}"><img id="logo_pic" alt="" src="" style="height:45px;width:auto;"/></a><h1>欢迎注册</h1>
</div>
<div class="w1000 tr">我已经注册，现在就<a href="${basePath}/login.html">登录</a></div>
<div class="reg_box">
	<div class="reg_form">
    	<form action="${basePath}/addcustomer.html" method="post" id="userform">
    	<input type="hidden" name="cusId" id="cusId" value="" />
    	<table class="form">
            <tr>
                <td align="right"><span>*</span><label>用户名：</label></td>
                <td>
                    <div class="form_row">
                        <input type="hidden" value="" name="username" class="hi_name" />
                        <input type="text" maxLength="20" name="customerUsername" class="text user_chk" id="act_user" />
                        <div class="form_tips">请填写6-20位字母、数字或下划线组成的用户名</div>
                        <div class="tips_error"></div>
                    </div>
                </td>
            </tr>
            <tr>
                <td align="right"><span>*</span><label>设置密码：</label></td>
                <td>
                    <div class="form_row">
                         <input type="hidden" value="" name="password" class="hi_psd" />
                        <input type="password" maxLength="20" name="customerPassword" class="text psd_chk" id="set_psd" name="password" />
                        <div class="form_tips">请填写密码</div>
                        <div class="tips_error"></div>
                    </div>
                </td>
            </tr>
            <tr>
                <td align="right"><span>*</span><label>确认密码：</label></td>
                <td>
                    <div class="form_row">
                        <input type="password" maxLength="20" name="psdConfirm" class="text psd_conf" id="conf_psd" />
                        <div class="form_tips">请再次输入密码</div>
                        <div class="tips_error"></div>
                    </div>
                </td>
            </tr>
            <tr>
                <td align="right"><span>*</span><label>验证码：</label></td>
                <td>
                    <div class="form_row">
                        <input type="text" name="varification" maxLength="5" class="text code_text" style="width:100px;" id="varification" onfocus="getPatcha()" />
                        <div class="form_tips">请输入右图中所示的验证码</div>
                        <div class="tips_error"></div>
                        <label>
                        	<img id="checkCodeImg" class="vm ml5" src="${basePath}/patchca.htm" alt="验证码" style="cursor:pointer;" onclick="this.src=this.src+'?'+Math.random(); " />
                        </label>
                        <label class="small">看不清？<a id="checkCodeA" href="javascript:void(0)">换一张</a></label>
                        <div class="tips_error code_error">验证码错误</div>
                    </div>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                	<div class="form_row" style="margin-bottom: 0px;">
                        <input type="checkbox" class="checkbox" checked="checked" id="readme" onclick="agreeonProtocol();">
                        我已阅读并同意<a href="javascript:void(0)" onclick="showpro();" class="blue" id="protocol">《商城用户注册协议》</a>
                        <div id="protocol_error" class="hide" style="position:static;">请接受服务条款</div>
                    </div>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <div class="form_row">
                        <input type="button" name="" class="login_btn" onclick="reg()" value="同意并注册" />
                    </div>
                </td>
            </tr>
        </table>
        </form>
    </div>
    <div class="mask" style="display:none"></div>
	<div class="dialog dia1 agreement_dia" style="display:none">
		<div class="dia_tit">
			${(topmap.systembase.bsetName)!''}用户协议
			<a class="dia_close" href="javascript:;" onclick="cls()"></a>
		</div><!--/dia_tit-->
		<div class="dia_cont">
			<div class="agreement_wp mt15">
				<#if (topmap.systembase.bsetUseragreement)??>
					${(topmap.systembase.bsetUseragreement)!''}
				<#else>
				<h4>福气商城用户注册协议</h4>
				<p>本协议是用户（以下简称“您”）与福气商城网站（网址：包括但不限于demo.ningpai.com等，简称“本站”）所有者及其关联公司（以下简称为“福气商城”）之间就福气商城网站服务等相关事宜所订立的契约，请您仔细阅读本注册协议，您点击"同意并继续"按钮后，即视为您接受并同意遵守本协议的约定。</p>
				<h4>第1条 本站服务条款的确认和接纳</h4>
				<p>1.1本站的各项电子服务的所有权和运作权归福气商城所有。您同意所有注册协议条款并完成注册程序，才能成为本站的正式用户。您确认：本协议条款是处理双方权利义务的依据，始终有效，法律另有强制性规定或双方另有特别约定的，依其规定或约定。</p>
				<p>1.2您点击同意本协议的，即视为您确认自己具有享受本站服务、下单购物等相应的权利能力和行为能力，能够独立承担法律责任。</p>
				<p>1.3您确认，如果您在18周岁以下，您只能在父母或其他监护人的监护参与下才能使用本站。</p>
				<p>1.4福气商城保留在中华人民共和国大陆地区施行之法律允许的范围内独自决定拒绝服务、关闭用户账户、清除或编辑内容或取消订单的权利。</p>
				<p>1.5您使用本站提供的服务时，应同时接受适用于本站特定服务、活动等的准则、条款和协议（以下统称为“其他条款”）；如果以下使用条件与“其他条款”有不一致之处，则以“其他条款”为准。</p>
				<p>1.6为表述便利，商品和服务简称为“商品”或“货物”。</p>
				<h4>第2条 本站服务</h4>
				<p>2.1福气商城通过互联网依法为您提供互联网信息等服务，您在完全同意本协议及本站相关规定的情况下，方有权使用本站的相关服务。</p>
				<p>2.2您必须自行准备如下设备和承担如下开支：（1）上网设备，包括并不限于电脑或者其他上网终端、调制解调器及其他必备的上网装置；（2）上网开支，包括并不限于网络接入费、上网设备租用费、手机流量费等。</p>
				<p>2.2.1上网设备，包括并不限于电脑或者其他上网终端、调制解调器及其他必备的上网装置；</p>
				<h4>第1条 本站服务条款的确认和接纳</h4>
				<p>1.1本站的各项电子服务的所有权和运作权归福气商城所有。您同意所有注册协议条款并完成注册程序，才能成为本站的正式用户。您确认：本协议条款是处理双方权利义务的依据，始终有效，法律另有强制性规定或双方另有特别约定的，依其规定或约定。</p>
				<p>1.2您点击同意本协议的，即视为您确认自己具有享受本站服务、下单购物等相应的权利能力和行为能力，能够独立承担法律责任。</p>
				<p>1.3您确认，如果您在18周岁以下，您只能在父母或其他监护人的监护参与下才能使用本站。</p>
				<p>1.4福气商城保留在中华人民共和国大陆地区施行之法律允许的范围内独自决定拒绝服务、关闭用户账户、清除或编辑内容或取消订单的权利。</p>
				<p>1.5您使用本站提供的服务时，应同时接受适用于本站特定服务、活动等的准则、条款和协议（以下统称为“其他条款”）；如果以下使用条件与“其他条款”有不一致之处，则以“其他条款”为准。</p>
				<p>1.6为表述便利，商品和服务简称为“商品”或“货物”。</p>
				<h4>第2条 本站服务</h4>
				<p>2.1福气商城通过互联网依法为您提供互联网信息等服务，您在完全同意本协议及本站相关规定的情况下，方有权使用本站的相关服务。</p>
				<p>2.2您必须自行准备如下设备和承担如下开支：（1）上网设备，包括并不限于电脑或者其他上网终端、调制解调器及其他必备的上网装置；（2）上网开支，包括并不限于网络接入费、上网设备租用费、手机流量费等。</p>
				<p>2.2.1上网设备，包括并不限于电脑或者其他上网终端、调制解调器及其他必备的上网装置；</p>
				</#if>
			</div><!--/agreement_wp-->
			<div class="mt20 tc"><a class="agree_btn" href="javascript:;" onclick="agreeonProtocol1();">同意并继续</a></div>
		</div><!--/dia_cont-->
	</div><!--/agreement_dia-->
    <div class="extra_login">
    	<div class="parter_login">
        	<h5>社交账号登录</h5>
            <#if t??>
	        	<#list t as tt >
	        		<#if tt.authType == '1'>
	        			<a class="qq fl" href="loginqq.html">腾讯QQ登录</a>
	        		<#elseif tt.authType == '2'>
	        			<a class="weibo fl" href="loginsina.html">新浪微博登录</a>
	        		</#if>
	        	</#list>
        	</#if>
            <div class="cb"></div>
        </div>
        <#--  
        <div class="activating">
        	<a href="#">电视会员，激活入口&gt;&gt;</a>
        </div>-->
    </div>
</div>
	<#--引入底部 <#include "/index/bottom.ftl" /> -->
    <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
	
<script>
document.onkeydown=function(event){
	var e = event || window.event || arguments.callee.caller.arguments[0];
  	if(e && e.keyCode==13){ // enter 键
     	reg();
    }
};

$.ajax({
		url: 'loadlogo.htm', 	
		success: function(data){
			$("#logo_pic").prop("src",data.logo.bsetLogo);
		}
	});
</script>
<script type="text/javascript">
		$(function(){
			win();
			$(window).resize(function(){
				win();
			});
		});
		function win(){
			var _wd = $(window).width();
			var _hd = $(window).height();
			$(".dialog").css("top",(_hd - $(".dialog").height())/2).css("left",(_wd - $(".dialog").width())/2);
		};

		function dia(n){
			$(".mask").fadeIn();
			$(".dia"+n).fadeIn();
			};
			
		function cls(){
			$(".dialog").fadeOut();
			$(".mask").fadeOut();
			};
			
		function showpro(){
			dia(1);
		} 
		function agreeonProtocol1(){
			$("#readme").prop("checked",true);
			cls();
		}
	</script>
	
	
	
	<div class="dialog dia2  agreement_dia" style=" width: 350px; height:20px; display:none">
		<div class="dia_tit">
			信息提示
		</div>
		<div style=" color: red; padding-left:30px; padding-top: 30px; font-size:12px;">
			<span>亲！此链接已经失效，您的好友将无法得到积分哦！</span><br/><br/>
		    <span>是否返回正常注册页面？</span>
		</div>
		<div class="mt20 tc" style=" padding-top: 35px;">
			<a style="width: 60px" class="agree_btn" href="javascript:;" onclick="registerUrl();">确定</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a style="width: 60px" class="agree_btn" href="javascript:;" onclick="cls();">取消</a>
		</div>
		<!--/dia_cont-->
	</div><!--/agreement_dia-->
	
</body>
</html>
