<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl"> 
<title>禁止回应的话题-${topmap.systembase.bsetName}</title>
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
        	<h3 class="rc_tit"><a href="${basePath}/groupdetail/${groupInfo.groupId}.html">返回</a>禁止回应的话题</h3>
            <div class="manage_cont">
            <form id="topic_form" method="post" action="">
				<input type="hidden" value="${groupInfo.groupId}" id="groupId"/>
            	<ul class="ban_list">
                    <#if pb.list??>
                      <#list pb.list as topic>
	                      <li class="clearfix">
	                    	<div class="rowElem clearfix mt10 fl">
	                        	<input type="checkbox" name="topicId" value="${topic.topicId}"/>
	                        </div><!--/rowElem-->
	                        <div class="ban_cont fl ml10">
	                        	<a class="ban_name" href="${basePath}/topicdetail/${groupInfo.groupId}-${topic.topicId}.html" target="_blank">${topic.topicTitle}</a>
	                            <div class="ban_info mt10 clearfix">
	                            	<span class="fl mr20">来自：<a href="${basePath}/customerhomepage/${topic.customerId}.html">${topic.customerName}</a></span>
	                                <span class="fl">时间：${(topic.topicCreateTime)?string('yyyy-MM-dd HH:mm:ss')}</span>
	                                <span class="fr reply_num">${(topic.replyCount)!'0'}回复/${(topic.topicHot)!'0'}查看</span>
	                            </div><!--/ban_info-->
	                        </div><!--/ban_cont-->
	                      </li>
                      </#list>
                    </#if>
                </ul><!--/ban_list-->
              </form>  
                <div class="manager_operating mt20 clearfix">
                    <div class="rowElem clearfix fl mr10"><a onclick="selectAll('topicId')"><input type="button" value="全选" /></a></div>
                    <div class="rowElem clearfix fl mr10"><a onclick="response()"><input type="button" value="允许回应" /></a></div>
                </div><!--/manager_operating-->
                  
                    <#if (pb.list?size>0)>
	                <form action="${basePath}/noresponsetopic.html?groupId=${groupInfo.groupId}" method="post" name="group_page_form" id="group_page_form">
	                 <input id="page" name="pageNo" type="hidden" value="${pb.pageNo }"/>
	                  <div class="pages tc mt30">
	                   <!--判断上一页-->
	                     <a class="pg_prev" 
	                      <#if ((pb.pageNo)>1)>
	                       href="javascript:page('${pb.pageNo-1}')"
	                       <#else>href="javascript:void(0)" style="color:#999;visibility:hidden"</#if>
	                       >&lt;上一页</a>
	                     <#if ((pb.startNo)>1)> 
	                      <a href="javascript:page('1');" >1</a>
										...
	                     </#if>
	                     <#list pb.startNo..pb.endNo as i>
	                      <a <#if pb.pageNo==i> class="cur"</#if> href="javascript:page('${i}');">${i}</a>
	                     </#list>
	                     <#if (pb.totalPages > pb.endNo)>
	                        ...
	                        <a href="javascript:page('${pb.totalPages}')"${pb.totalPages}></a>
	                     </#if>
	                     <!--判断下一页-->
	                     <a class="pg_next" 
	                       <#if ((pb.pageNo+1) > pb.totalPages)>
	                       href="javascript:void(0)" style="color:#999;visibility:hidden"
	                       <#else>href="javascript:page('${pb.pageNo+1}')"
	                       </#if>>下一页&gt;
	                     </a>
	                  </div>
	                 </form>
                  </#if>
            </div><!--/manage_cont-->
        </div><!--/rig_cont-->
    </div>
    
  <div class="mask1"></div>
     <div class="dialog1 dia1"  style="width: 300px; height: 120px;">
    	<a class="close" href="javascript:cls1();" onclick="clopage();"></a>
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
<script type="text/javascript" src="${basePath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath}/js/social/group_comm.js"></script>
<script type="text/javascript" src="${basePath}/js/social/group_info.js"></script>
<script type="text/javascript">
			$(function() {
				$(".rowElem").jqTransform();
			});
				
		   //全/全不选
			function selectAll(name) {
				var checkboxs = document.getElementsByName(name);
				for ( var i = 0; i < checkboxs.length; i++) {
					var e = checkboxs[i];
						if(!e.checked){
						    e.checked = true;
							$(".jqTransformCheckbox").addClass("jqTransformChecked");
						}else{
						    e.checked = false;
							$(".jqTransformCheckbox").removeClass("jqTransformChecked");
						}
				} 
			}
			
			function clopage(){
			   window.location.href="noresponsetopic.html?groupId=${groupInfo.groupId}";
			}
			
	   $(function(){
			//选中首页社区
			 $(".sort_list li").removeClass("cur");
			 $(".sort_list li:eq(1)").addClass("cur");
         });
</script>
</body>
</html>


