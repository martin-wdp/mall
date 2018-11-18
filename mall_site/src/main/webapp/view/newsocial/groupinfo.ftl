<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl"> 
<title>小组资料-${topmap.systembase.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/> 
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>
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
        	<h3 class="rc_tit"><a href="groupdetail/${groupInfo.groupId}.html">返回</a>小组资料</h3>
            <div class="manage_cont">
            <form id="updategroup">
             <input type="hidden" id="groupid" name="groupId" value="${(groupInfo.groupId)!''}">
            	<dl class="manage_dl clearfix ml20 mt10">
                	<dt>小组名称：</dt>
                    <dd>
                    	<div class="rowElem clearfix">
                        	<input class="mg_text" type="text" name="groupName" id="groupName" onkeyup="textLimt(this,15)" value="${groupInfo.groupName}"/>
                        </div><!--/rowElem-->
                        <p class="mg_info" style="display:none" id="namelength"><font color="red"><span id="namelimit"><span></font></p>
                    </dd>
                    <dt>小组简介：</dt>
                    <dd>
                    	<div class="rowElem clearfix">
                        	<textarea class="mg_ta"  name="groupRemark" id="groupRemark" onkeyup="textLimt(this,500)" maxlenth="500">${groupInfo.groupRemark}</textarea>
                        </div><!--/rowElem-->
                        <p class="mg_info" id="remarklength"><font color="red"><span id="remarklimit">简介不得超过500个字，你还可以输入 </span><span id="num">500</span></font></p>
                    </dd>
                    <dt>&nbsp;</dt>
                    <dd>
                    	<div class="rowElem clearfix">
                        	<a onclick="updateGroup()"><input class="mg_btn" type="button" value="确认修改" /></a>
                        </div><!--/rowElem-->
                    </dd>
                </dl><!--/groupInfo-->
              </form> 
            </div><!--/manage_cont-->
        </div><!--/rig_cont-->
    </div>
    
    <div class="mask1"></div>
     <div class="dialog1 dia1" style="width:300px; height:120px;">
    	<a class="close" href="javascript:cls1();"></a>
        <h4>系统提示</h4>
        <p class="tc f14 mt20" style="padding:20px;" id ="message"></p>   
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
<script type="text/javascript" src="${basePath}/js/social/group_comm.js"></script>
<script type="text/javascript" src="${basePath}/js/social/group_info.js"></script>
<script type="text/javascript">
	$(function() {
		$(".rowElem").jqTransform();
	    $("#num").html(500 - $("#groupRemark").val().length);
	});
	
		$(function(){
			//选中首页社区
			$(".sort_list li").removeClass("cur");
			$(".sort_list li:eq(1)").addClass("cur");
        });
</script>
</body>
</html>


