<#include "../include/common.ftl">
<@htmlHead title='小组背景-${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>
<style type="text/css">
    html, body { height:100%; background-color: #ffffff;}
    #flashContent { width:100%; height:100%; }
    .ghead_box .save_change {background:url(images/save_pic_btn.png); width:126px; height:40px;}
    .d_lk .ok_btn, .d_lk .cancel_btn {display:inline-block; zoom:1; *display:inline; background:url(images/g_search_btn.gif) no-repeat; width:62px; height:24px; color:#fff; text-align:center; line-height:24px; font-weight:700;}
</style>
</head>
</@htmlHead>
<@htmlBody>
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
         <h3 class="rc_tit"><a href="groupdetail/${groupInfo.groupId}.html">返回</a>小组背景</h3>
        <form action="updategroupinfo.html" id="group_form" method="post" enctype="multipart/form-data" name="uploadForm" modelAttribute="uploadForm">
        <input type="hidden" name="groupId" value="${groupInfo.groupId }" />
        <input type="hidden" id="delPath" name="delPath" />
            <div class="manage_cont">
            	<div class="bg_cont mt10 tc clearfix">
            	   <div class="fl">
	                    <div class="cimg_wp mb10">  
	                    	<#if !(groupInfo.groupBackground)??>
	                    		<img alt="" height="200px" id="groupBackgroundImg" width="200px" style="display:none;"  src="${(groupInfo.groupBackground)!'' }"/>
	                    	</#if> 
	                    	<#if (groupInfo.groupBackground)??>
	                    		<img alt="" height="200px" id="groupBackgroundImg" width="200px" onerror="$('#groupBackgroundImg').hide();" src="${groupInfo.groupBackground }"/>
	                    	</#if>
	                    </div>
	                    <a class="b_blue" href="javascript:;" onclick="updatebg()">去除背景</a>  
                    </div>
                    <div class="bg_wp fl ml20">
                        <div class="clearfix">
                        	<div class="rowElem bg_way clearfix w200">
                            	<input type="checkbox" name="groupBackgroundType" value="1" <#if (groupInfo.groupBackgroundType)=='1'>checked </#if>/>
                                <label>平铺</label>
                                <span>（选择平铺后背景图）</span>
                            </div><!--/rowElem-->
                        </div>
                         <div class="clearfix mt20 mb10 ghead_box">
	                         <input class="save_change fl" type="button" value=""onclick="update()" />
	                       	 <a class="upload_bg fl ml10" href="javascript:void(0);">
	                         <input class="pic_upload" onchange="changeBackground()"  name="groupBackGroundPath" type="file" /></a>
                        </div>
                        <p class="pic_info">可以上传jpg、gif或png格式的图片，且图片文件小于5M；</p>
                        <p class="pic_info">保存成功后如果左边头像预览图不更新，可以刷新页面后查看。</p>
                    </div><!--/bg_wp-->
                </div><!--/bg_cont-->
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
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/social/default.js"></script>
<script type="text/javascript" src="${basePath}/js/slides.min.jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.jqtransform.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.easyui.min.js"></script>
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
</@htmlBody>