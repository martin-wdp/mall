<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl"> 
<title>删除权限-${topmap.systembase.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/> 
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>
<style type="text/css">
 .d_lk .ok_btn, .d_lk .cancel_btn {display:inline-block; zoom:1; *display:inline; background:url(images/g_search_btn.gif) no-repeat; width:62px; height:24px; color:#fff; text-align:center; line-height:24px; font-weight:700;}
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
<div class="container clearfix pt20">
        <#include "common/left.ftl"> 
         <div class="rig_cont fl">
        	<h3 class="rc_tit"><a href="groupdetail/${groupInfo.groupId}.html">返回</a>删除权限</h3>
            <form id="deletelimit" action=""> 
        	<input type="hidden" name="groupId" value="${groupInfo.groupId}" />
            <div class="manage_cont">
            	<div class="del_wp">
                	<div class="del_box">
                    	<h4>删除本人回复</h4>
                         <div class="rowElem clearfix"><input type="radio" name="limitReplyDelType" value="0" <#if (groupInfo.limitReplyDelType)?? && (groupInfo.limitReplyDelType) == '0'> checked="checked"</#if>/><label>开启</label></div>
                         <div class="rowElem clearfix"><input type="radio" name="limitReplyDelType" value="1" <#if (groupInfo.limitReplyDelType)?? && (groupInfo.limitReplyDelType) == '1'> checked="checked"</#if>/><label>关闭</label></div>
                    </div><!--/del_box-->
                    <div class="del_box">
                    	<h4>修改本人帖子内容</h4>
                        <div class="rowElem clearfix"><input type="radio" name="limitModifyTopicType" value="0" <#if (groupInfo.limitModifyTopicType)?? && (groupInfo.limitModifyTopicType) == '0'> checked="checked"</#if>/><label>开启</label></div>
                        <div class="rowElem clearfix"><input type="radio" name="limitModifyTopicType" value="1" <#if (groupInfo.limitModifyTopicType)?? && (groupInfo.limitModifyTopicType) == '1'> checked="checked"</#if>/><label>关闭</label></div>
                    </div><!--/del_box-->
                    <div class="del_box">
                    	<h4>删除本人小组图片</h4>
                       <div class="rowElem clearfix"><input type="radio" name="limitDelPicType" value="0" <#if (groupInfo.limitDelPicType)?? && (groupInfo.limitDelPicType) == '0'> checked="checked"</#if>/><label>开启</label></div>
                       <div class="rowElem clearfix"><input type="radio" name="limitDelPicType" value="1" <#if (groupInfo.limitDelPicType)?? && (groupInfo.limitDelPicType) == '1'> checked="checked"</#if>/><label>关闭</label></div>
                    </div><!--/del_box-->
                    <div class="del_box">
                    	<h4>仅小组成员回复</h4>
                        <div class="rowElem clearfix"><input type="radio" name="limitReplyType" value="1" <#if (groupInfo.limitReplyType)?? && (groupInfo.limitReplyType) == '1'> checked="checked"</#if>/><label>开启</label></div>
                        <div class="rowElem clearfix"><input type="radio" name="limitReplyType" value="0" <#if (groupInfo.limitReplyType)?? && (groupInfo.limitReplyType) == '0'> checked="checked"</#if>/><label>关闭</label></div>
                    </div><!--/del_box-->
                    <div class="rowElem clearfix">
                    <a onclick="updateDelete()"><input type="button" value="确认修改" /></a></div>
                </div><!--/del_wp-->
            </div><!--/manage_cont-->
          </form>
        </div><!--/rig_cont-->
    </div>

   <div class="mask1"></div>  
     <div class="dialog1 dia1" style="width:450px; height:170px;">
    	<a class="close" href="javascript:cls1();"></a>
        <h4>系统提示</h4>
        <p class="tc f14 mt20" style="padding:20px;" id ="message"></p>   
        <div class="tc mt10 d_lk"><a class="cancel_btn" href="javascript:cls1();">关闭</a></div>
    </div><!--/dialog-->
   <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/social/default.js"></script>
<script type="text/javascript" src="${basePath}/js/slides.min.jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.jqtransform.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath}/js/social/group_comm.js"></script>
<script type="text/javascript" src="${basePath}/js/social/group_info.js"></script>

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


