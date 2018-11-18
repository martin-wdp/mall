<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl"> 
<title>访问权限-${topmap.systembase.bsetName}</title>
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
        	<h3 class="rc_tit"><a href="groupdetail/${groupInfo.groupId}.html">返回</a>访问权限</h3>
          <form id="accesslimit" action="">
            <input type="hidden" value="${groupInfo.groupId}" name="groupId" />
            <div class="manage_cont">
            	<div class="access_wp">
                	<h4>成员加入方式</h4>
                   <#if (groupInfo.groupSecret)?? && (groupInfo.groupSecret)=='0'>
                    <div class="join_way mt30">
                       <div class="rowElem clearfix">
                            <input type="radio" name="limitAddType" value="0" id="limitAddType1"<#if (groupInfo.limitAddType)?? && (groupInfo.limitAddType)=='0'>checked="checked"</#if>/>
                            <label>允许任何人加入小组</label>
                        </div><!--/rowElem-->
                        <div class="rowElem clearfix">
                            <input type="radio" name="limitAddType" value="1" <#if (groupInfo.limitAddType)?? && (groupInfo.limitAddType)=='1'>checked="checked"</#if>/>
                            <label>需要小组管理员批准后才能加入小组</label>
                        </div><!--/rowElem--> 
                        <div class="rowElem clearfix">
                            <input type="radio" name="limitAddType" value="2" id="limitAddType3"<#if (groupInfo.limitAddType)?? && (groupInfo.limitAddType)=='2'>checked="checked"</#if>/>
                            <label>只有接受组员邀请才能加入小组</label>
                        </div><!--/rowElem-->
                    </div><!--/join_way-->
                   </#if>
                    <#if (groupInfo.groupSecret)?? && (groupInfo.groupSecret)=='1'>
                    <div class="join_way mt30"> 
                        <div class="rowElem clearfix">
                            <input type="radio" name="limitAddType" value="2" <#if (groupInfo.limitAddType)?? && (groupInfo.limitAddType)=='2'>checked="checkeed"</#if>/>
                            <label>只有接受组员邀请才能加入小组</label>
                        </div><!--/rowElem-->
                    </div><!--/join_way-->
                    </#if>
                    <div class="private_warning rowElem clearfix" <#if (groupInfo.groupSecret)?? && (groupInfo.groupSecret)=='0'>style="display:block"<#else>style="display:none" </#if>>
                    	<input type="checkbox" name="groupSecret" value="1" id="groupSecret" onclick="groupsecret()"/>
                        <label>设为<em>私密小组</em>
                        	<div class="pv_wp">
                            	<div class="pv_arrow"></div>
                            	<div class="pv_box">
                                	作为一个私密小组：
                                    <p>1.只有小组成员知道小组的存在；</p>
                                    <p>2.只有接受组员邀请才能加入小组。</p>
                                </div><!--/pv_box-->
                            </div><!--/pv_wp-->
                        </label>
                        <span>（一旦设置成为私密小组，以后<em>永远不能</em>变为公开小组，请谨慎选择）</span>
                    </div><!--/private_warning-->
                    <div class="rowElem clearfix ml30 mt50">
                    	<a  onclick="updateAccess()"><input type="button" value="确认修改" /></a>
                    </div><!--/rowElem-->
                </div><!--/access_wp-->
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
<script type="text/javascript" charset="utf-8" src="${basePath}/js/social/group_comm.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}/js/social/group_info.js"></script>
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


