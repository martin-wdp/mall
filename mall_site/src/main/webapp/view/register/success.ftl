<#include "../include/common.ftl">
<@htmlHead title='注册成功-${topmap.systembase.bsetName}'>
    <#assign aa=topmap.systembase.bsetDesc >
    <#if (aa?index_of('>',0) != -1)>
        <#assign bb=aa?substring(aa?index_of('>',0)+1,aa?index_of('<',1)) >
    <#else>
        <#assign bb=aa >
    </#if>
<meta name="Keywords" content="${bb}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
</@htmlHead>
<@htmlBody>
<div class="head2">
	<!--
	<a href="${basePath}/index.html"><img id="logo_pic" alt="" src="" /></a><h1>欢迎注册</h1>
	-->
	<a href="${basePath}/register.html"><img id="logo_pic" alt="" src="" /></a><h1>欢迎注册</h1>
</div>
<div class="w1000 tr">我已经注册，现在就<a href="${basePath}/login.html">登录</a></div>
<div class="reg_box">
	<div class="reg_success">
    	<div class="notice2">
        	<img alt="" src="${basePath}/images/success.png">恭喜您，已经注册成功！
        </div>
        <div class="notice3">
        	<!--
        	<a class="common_btn" href="${basePath}/index.html">立即购物</a>
            <span><strong><span id="time">5</span>秒自动跳转<a href="${basePath}/index.html">首页</a>或点击<a href="${basePath}/customer/index.html">会员中心</a>完善您的个人资料!</strong></span>
        	-->
            <a class="common_btn" href="${basePath}/toValidateProtocol.htm">企业认证</a>
        	<a class="common_btn" href="${basePath}/index.html">立即购物</a>
            <span><strong><span id="time">5</span>秒自动跳转<a href="${basePath}/index.html">首页</a>或点击<a href="${basePath}/customer/index.html">会员中心</a>完善您的个人资料!</strong></span>
        </div>
    </div>
    <#--
    <div class="reg_fill">
    	<div class="varify_tips"><span>温馨提示：95%</span>的用户选择验证手机，验证成功后您也可以使用手机号登录好享商城。</div>
        <form>
    	<table class="form">
            <tr>
                <td align="right"><span>*</span><label>手机号：</label></td>
                <td>
                    <div class="form_row">
                        <input type="text" name="user" class="text contact_chk" id="act_user" />
                        <div class="tips">请填写您常用的手机号码</div>
                        <div class="tips_error"></div>
                    </div>
                </td>
            </tr>
            <tr>
                <td align="right"><span>*</span><label>短信验证码：</label></td>
                <td>
                    <div class="form_row">
                        <input type="text" name="varification" class="text" style="width:100px;" id="varification" />
                        <div class="tips">请填写您手机收到的验证码</div>
                        <div class="tips_error"></div>
                        <input class="get_code" type="button" value="获取验证码" />
                    </div>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <div class="form_row">
                        <input type="submit" name="" class="common_btn" value="下一步" />
                        <label><a href="#">跳过</a></label>
                    </div>
                </td>
            </tr>
        </table>-->
        </form>
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
		url: 'loadlogo.htm', 	
		success: function(data){
			$("#logo_pic").prop("src",data.logo.bsetLogo);
		}
	});
</script>
</@htmlBody>