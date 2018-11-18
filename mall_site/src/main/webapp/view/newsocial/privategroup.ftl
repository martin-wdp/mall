<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "../common/basepath.ftl" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>创建小组-${topmap.systembase.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>
<style type="text/css">
.LV_validation_message {
    position: absolute;
 	color: #cc0000;
	margin-left: 20px;
	padding: 0 15px;
	margin-top: 10px;
	bottom: -25px;
	left: -30px;
}

.create_ok {
	font-size: 18px;
	font-family: "微软雅黑";
}
</style>
</head>
<body>
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
  <form action="" id="creategroup" method="post">
   <div class="wp clearfix">
    	<div class="left_cont fl">
    	  <input type="hidden" name="type" value="${type}" />
    	  <input type="hidden" name="groupCreateAuthorId" value="${customeId}" />
        	<div class="create_wp">
        		<h3 class="m_tit">申请创建一个私密小组</h3>
                <dl class="team_dl clearfix mt30 ml10">
                	<dt>小组名称：</dt>
                    <dd>
                    	<div class="rowElem clearfix">
                        	<input class="t_text" type="text" name="groupName" id="groupName" onkeyup="textLimt(this,15)"/>
                        </div><!--/rowElem-->
                        <p style="display:none" id="namelength"><span id="namelimit" class="LV_validation_message LV_invalid"></span></p>
                    </dd>
                    <dt>小组介绍：</dt>
                    <dd>
                    	<div class="rowElem clearfix">
                        	<textarea class="t_ta" name="groupRemark" id="groupRemark" onkeyup="textLimt(this,500)" maxlength="500" rows="" cols=""></textarea>
                        </div><!--/rowElem-->
						<p class="" id="remarklength"><span id="remarklimit" class="LV_validation_message LV_invalid">简介不得超过500个字，你还可以输入 </span><span id="num" class="LV_validation_message LV_invalid" style="margin-left: 225px;">500</span></p></dd>
                  	<#--<dt>小组标签：</dt>
                    <dd><div class="rowElem clearfix"><input  type="text" name="groupLabel" style="width:300px" id="groupLabel"></div></dd>-->
                    <!--<dt>&nbsp;</dt>
                    <dd>
                    	<div class="rowElem">
                        	<input type="checkbox" checked="checked" id="agreement" onclick="showAgree()"/>
                            <label class="agreement">我已认真阅读并同意<a href="javascript:void(0);">《小组指导原则》</a></label><br/>
                            <span id="unagreement" class="LV_validation_message LV_invalid" style="display:none">您尚未同意《小组指导原则》条款！</span>
                        </div>
                    </dd>-->
                    <dt>&nbsp;</dt>
                    <dd><a onclick="create(1)"><input class="group_btn" type="button" value="提交申请"/></a></dd>
                </dl><!--/team_dl-->
              
            </div><!--/create_wp-->
        </div><!--/left_cont-->
        
        <div class="right_cont fr">
        	<div class="group_explain">
            	<h4>小组创建</h4>
                <p>创建小组申请需要审核通过后才能完成创建，管理员会在3个工作日内审核申请，审核结果会用邮件方式通知您，请耐心等待。</p>
                <p>小组的名称，介绍在创立后都可以随时更改。</p>
            </div><!--/group_explain-->
        </div><!--/right_cont-->
    </div>
  </form>
 <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
  <div class="mask1"></div>
    <div class="dialog1" style="height:255px;" id="successDialog">
    	<a class="r_close" href="javascript:cls1();" onclick="cls1()"></a>
    	<h4>提示</h4>
        <h3 class="m_tit tc mt30">恭喜您，小组创建申请已成功提交！</h3>
        <p class="tc f14 lh180 mt20">您的小组申请已成功提交，请耐心等待我们工作人员的审核，我们会在第一时间通过消息中心通知您审核结果，感谢您对qpmall的支持！</p>
        <div class="tc mt30 d_lk"><a href="${basePath}/group.html">返回小组列表</a><a href="${basePath}/index.html">返回qpmall首页</a></div>
    </div><!--/dialog-->
    
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/social/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.jqtransform.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}/js/social/group_comm.js"></script>
  <script type="text/javascript">
					$(function() {
						$(".rowElem").jqTransform();
					});
					
		 $(function(){
			//选中首页社区
			 $(".sort_list li").removeClass("cur");
			 $(".sort_list li:eq(1)").addClass("cur");
         });	
  </script>
</body>
</html>