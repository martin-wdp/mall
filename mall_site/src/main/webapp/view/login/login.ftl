<#include "../include/common.ftl">
<@htmlHead title='登录${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
    <#if (topmap.temp.tempId==14)>
    <link rel="stylesheet" type="text/css" href="${basePath}/index_nine/css/template.css"/>
    </#if>
<style>
    .parter_login a{margin-right:5px;}
</style>
</@htmlHead>
<@htmlBody>
<div class="head2">
	<!--
	<a href="${basePath}/index.html"><img id="logo_pic" alt="" src="" /></a><h1>欢迎登录</h1>
	-->
	<a href="${topmap.systembase.bsetAddress}"><img id="logo_pic" alt="" src="" style="height:45px;width:auto;"/></a><h1>欢迎登录</h1>
</div>
<div class="login_box" style="overflow:hidden;">
	<div class="box_l fl">
		<!--
    	<a href="${basePath}/index.html"><img alt="" src="${basePath}/images/images_30.jpg" style="margin:-2px 0 0 -2px;" /></a>
		-->
    	<a href="${topmap.systembase.bsetAddress}"><img id="loginImg" alt="" src="" style="margin:-2px 0 0 -2px;" /></a>
    </div>
    <div class="box_r fl">
    	<div class="login_form">
            <form id="login_form" name="" action="${basePath}/login.html" method="post">
                <div class="form_row">
                    <input type="text" maxLength="20" name="username" value="${username!''}" class="text" id="log_user" />
                    <div class="form_tips">请输入用户名、邮箱或手机号</div>
                </div>
                <div class="form_row">
                    <input maxLength="20" type="password" name="password" class="text" id="log_psd"  oncopy="return false;" oncut="return false;" onpaste="return false"/>
                    <div class="form_tips">请输入密码</div>
                </div>
                <div class="form_row">
                    <div class="fr">
                        <a href="${basePath}/findpwd.html">忘记密码？</a>
                        <span>|</span>
                        <a href="${basePath}/register.html">立即注册</a>
                    </div>
                    <input type="checkbox" name="remember" class="re" /><label>记住账号</label>
                    <#--<input type="checkbox" name="autologin" class="au"  /><label style="text-decoration:line-through;">自动登录</label>-->
                </div>
                <input type=hidden id="urlhide" value="${url}" />
                <div class="form_row">
                    <input type="button" name="login" class="login_btn" value="登录" />
                </div>
                <#--<#if isTemp??&&isTemp=='1'>
                <div class="form_row">
                    <input type="button" name="suorder" class="suborder_btn" value="一键下单" />
                </div>
                <form  action="order/suborder.html" method="post" class="suborder_form">
                    <#if box??>
	                    <#list box as b>
	                        <input type="hidden" value="${b!''}">
	                    </#list>
                    </#if>
                    <input type="hidden" value="1" name="isTemp">
                </form>
                </#if>-->
            </form>
        </div>
        <div class="parter_login">
        	<h5>社交账号登录</h5>
        	<#if t??>
	        	<#list t as tt >
	        		<#if tt.authType == '1'>
	        			<a class="qq fl" href="loginqq.html">腾讯QQ登录</a>
	        		<#elseif tt.authType == '2'>
	        			<a class="weibo fl" href="loginsina.html">新浪微博登录</a>
	        		<#elseif tt.authType == '7'>
	        			<!--<a class="weibo fl" href="loginweixin.html">微信登录</a>-->
	        		<#elseif tt.authType == '11'>
	        		    <!--<a class="taobao fl" href="logintaobao.html">淘宝登录</a>-->
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
    <div class="cb"></div>
    
     <#if isTemp??&&isTemp=='1'>
                <form  action="order/suborder.html" method="post" class="suborder_form">
                    <#if box??>
	                    <#list box as b>
	                        <input type="hidden" name="box" value="${b!''}">
	                    </#list>
                    </#if>
                    <input type="hidden" value="1" name="isTemp">
     </#if>
</div>
	<#--引入底部 <#include "/index/bottom.ftl" /> -->
    <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
<script type="text/javascript">
$(function(){

	$("input[type='text']").blur(function(){
		$(this).next().hide();
	});
	$(".user_check").blur(function(){
		var x = $(this).val();
		if(x == ''){
			$(this).addClass('text_error');
			$(this).next().addClass('tips_error');
			$(this).next().html('输入框不能为空');
		}
		else if(!isUser(x)){
			$(this).addClass('text_error');
			$(this).next().addClass('tips_error');
			$(this).next().html('请输入4-20位数字、字母或下划线组成的用户名');
		}
		else{
			$(this).removeClass('text_error');
			$(this).next().hide();
		}
	});
	$.ajax({
		url: 'loadlogo.htm',
		success: function(data){
			$("#logo_pic").prop("src",data.logo.bsetLogo);
            $("#loginImg").prop("src",data.logo.siteLoginImg);
		}
	});
	$(".login_btn").click(function(){
		login();
	});
	$(".suborder_btn").click(function(){
		$(".suborder_form").submit();
	});

});
function login(){
	if(checkInput()){
			var type="";
			if($(".re").prop("checked")){
				type="0";
			}
			if($(".au").prop("checked")){
				if($(".re").prop("checked")){
					type="1";
				}
			}
			$("input[type='text']").next().hide();
			//var url="checklogin-"+$("#log_user").val()+"-"+$("#log_psd").val()+"-"+$("#urlhide").val()+type;
			$.ajax({
					url: "checklogin.htm",
					type:"POST",
					data:{username:$("#log_user").val(),
						password:$("#log_psd").val(),
						url:$("#urlhide").val(),
						type:type},
					success: function(data){
		    			if(data == 0){
		    				$("#log_psd").next().show().addClass('tips_error').html('密码和账户不匹配，请重新输入');
		    			}else if(isNaN(data)){
		    				window.location.href=data;
		    			}else if(data == 2){
		    				$("#log_user").next().show().addClass('tips_error').html('你输入的用户不存在，请重新输入');
                        } else if (data == 3) {
                            $("#log_user").next().show().addClass('tips_error').html('你输入的用户已经冻结,请联系管理员解锁');
                        }
		  			}
		  	});
		}
}
function checkInput(){
		var flag=false;
		var x = $("#log_user").val();
		if(x == ''){
			$("#log_user").addClass('text_error');
			$("#log_user").next().addClass('tips_error');
			$("#log_user").next().html('请输入4-20位数字、字母或下划线组成的用户名');
			$("#log_user").next().show();
		}
		else if(!isUser(x)){
			$("#log_user").addClass('text_error');
			$("#log_user").next().addClass('tips_error');
			$("#log_user").next().html('请输入4-20位数字、字母或下划线组成的用户名');
			$("#log_user").next().show();
		}
		else{
			$("#log_user").removeClass('text_error');
			$("#log_user").next().hide();
			flag=true;
		}
		var flagP=false;
		x = $("#log_psd").val();
        var regS=/\s/g;
        if(regS.test(x)){
            $("#log_psd").addClass('text_error');
            $("#log_psd").next().addClass('tips_error');
            $("#log_psd").next().html('请勿输入空格');
            $("#log_psd").val("");
            $("#log_psd").next().show();
        }
        else if(x == ''){
			$("#log_psd").addClass('text_error');
			$("#log_psd").next().addClass('tips_error');
			$("#log_psd").next().html('请输入密码');
			$("#log_psd").next().show();
		}
		else{
			$("#log_psd").removeClass('text_error');
			$("#log_psd").next().hide();
			flagP=true;
		}
		return flag&&flagP;
	}
function isUser(x){
	var reg = /^\w+[@\.]?$/;
	if((x.length >=4) && (x.length <= 20)){
		return true;
	}
	else{
		return false;
	}
}
document.onkeydown=function(event){
	var e = event || window.event || arguments.callee.caller.arguments[0];
  	if(e && e.keyCode==13){ // enter 键
     	login();
    }
};
</script>
</@htmlBody>