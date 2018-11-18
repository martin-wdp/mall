<#include "../include/common.ftl">
<@htmlHead title="用户反馈">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/new.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/index_two/css/header.css" />
<script type="text/javascript" src="${basePath}/js/new.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/feedback.js"></script>
<style type="text/css">
    $(".dialog").css("top",(_hd - $(".dialog").height())/2).css("left",(_wd - $(".dialog").width())/2);
</style>
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<title>用户反馈</title>
	<#assign basePath=request.contextPath>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
	<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
	<link rel="stylesheet" type="text/css" href="${basePath}/css/new.css" />
	<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
	<link rel="stylesheet" type="text/css" href="${basePath}/index_two/css/header.css" />
	<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${basePath}/js/new.js"></script>
	<script type="text/javascript" src="${basePath}/js/customer/feedback.js"></script>
	<style type="text/css">
		$(".dialog").css("top",(_hd - $(".dialog").height())/2).css("left",(_wd - $(".dialog").width())/2);
	</style>
</head>-->
<@htmlBody>
	<div class="head2">
		<a href="${basePath}/index.html"><img id="logo_pic" alt="" src="" /></a><h1>欢迎反馈</h1>
	</div>
	<form method="post">
	<input type="hidden" name="CSRFToken" value="${token}">
		<div class="fb_wp">
			<div class="feedback_box">
				<dl class="feedback_input clearfix">
					<dt>反馈内容关于：</dt>
					<dd>
						<label class="fb_check mr30"><input class="vm mr10" type="checkbox"  name="feedbacktype" value="界面视觉"/>界面视觉</label>
						<label class="fb_check mr30"><input class="vm mr10" type="checkbox"  name="feedbacktype" value="功能"/>功能</label>
						<label class="fb_check mr30"><input class="vm mr10" type="checkbox"  name="feedbacktype" value="出错"/>出错</label>
						<label class="fb_check mr30"><input class="vm mr10" type="checkbox"  name="feedbacktype" value="其他"/>其他</label>
					</dd>
					<dt>反馈内容：</dt>
					<dd><textarea class="fb_txa" name="feedbackcontent"></textarea><p class="word_num tr">限定输入10~250字</p>
						<input type="hidden" name="feedbackname" value="${cust.customerNickname}" >
					</dd>
					<#--上传截图
					<dt>上传截图：</dt>
					<dd><input type="file" /><label class="upload_word ml10">请上传jpg格式</label></dd>
					<dt>&nbsp;</dt>
					-->
					<dd><input class="sub_fb" type="button" value="提交反馈" onclick="sendEmailToStore()"/></dd>
				</dl><!--/feedback_input-->
			</div><!--/feedback_box-->
		</div><!--/fb_wp-->
	</form>
	
	<div class="mask"></div>
	<div class="dialog s_dia dia2">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" alt=""  id ="idnum_img" src="${basePath}/images/mod_succ.png" />
            	<em>感谢您的回馈<br>我们一定尽快解决！</em>
            </div>
            <div class="dia_ops mt20 tr">       
                <a class="go_pay" href="javascript:cls();" onclick="javascript:location.href='../indexview.htm'">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
    <div class="dialog s_dia dia3">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" alt=""  id ="idnum_img" src="${basePath}/images/mod_err.png" />
            	<em></em>
            	<em>限定输入10-250字<br/>且不能包含特殊字符！</em>
            </div>
            <div class="dia_ops mt20 tr">       
                <a class="go_pay" href="javascript:cls();" >确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
    <div class="dialog s_dia dia4">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" alt=""  id ="idnum_img" src="${basePath}/images/mod_err.png" />
            	<em>反馈失败！</em>
            </div>
            <div class="dia_ops mt20 tr">       
                <a class="go_pay" href="javascript:cls();">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
	<#--引入底部 <#include "/index/bottom.ftl" /> -->
    <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
<script>
    $.ajax({
        url: '../loadlogo.htm',
        success: function(data){
            $("#logo_pic").prop("src",data.logo.bsetLogo);
        }
    });
</script>
</@htmlBody>
