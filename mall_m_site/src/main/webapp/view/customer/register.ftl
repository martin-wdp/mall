<!DOCTYPE HTML>
<head>
    <#assign basePath = request.contextPath>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width,initial-scale=0.9,user-scalable=no"/>
        <meta name="MobileOptimized" content="320">
        <meta name="format-detection" content="telephone=no">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <link rel="stylesheet" href="${basePath}/css/register.css">
<link rel="stylesheet" href="${basePath}/css/top.css">
 <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script> <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
	<body>
    <#include "../publicHeader2_ftl.ftl" />
    <input type="hidden" id="basePath" value="${basePath!''}">
		<form>
			<div class="enroll">
				<p class="must" data-mes="请输入手机号" style="border-top:none;"><label>手机号：</label><input id="mobileRegig" name="customerUsername" type="text" maxlength=11 placeholder="请输入您的手机号"></p>
				<p class="must" data-mes="请输入密码"><label>密码：</label><input type="password" name="customerPassword" id="r_pass" maxlength=12 placeholder="6-12位字母数字下划线组合"></p>
				<p class="must" data-mes="请输入确认密码"><label>确认密码：</label><input id="r_pass2" type="password" maxlength=12 placeholder="再输一次吧"></p>
				<p class="must" data-mes="请输入验证码"><label>验证码：</label><input name="code" type="text" id="capform" class="en-inp" maxlength=6 placeholder="短信验证码"><button type="button" id="code" sendtype="1">获取验证码</button></p>
			</div>
			<p class="check"><input id="ck" type="checkbox"><label for="ck">同意</label><a href="${basePath}/getXieYi.htm">《京华亿家商城用户协议》</a></p>
		</form>
		<button id="sub" class="btn">注册</button>
		<div class="w-suc dis">
			<div class="suc">
				<img src="${basePath}/images/qp_cxcg.png">
				<h3>恭喜您注册成功</h3>
				<p>企业用户请及时通过认证，可享受更优惠价格</p>
				<div class="suc-btn">
					<a href="${basePath}/customer/newindexm.htm" class="btn-left">我是个人用户</a>
					<a href="${basePath}/customer/toAttestation.htm" class="btn-right">马上认证</a>
				</div>
			</div>
		</div>
		<script src="${basePath}/js/customer/jquery-1.11.3.min.js"></script>
		<script src="${basePath}/js/customer/public.js"></script>
		<script src="${basePath}/js/customer/register.js"></script>
	</body>
</html>
