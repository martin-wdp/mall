<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl"> 
<title>管理员权限-${topmap.systembase.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/> 
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>
<style type="text/css">
 
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
        <input type="hidden" value="${groupInfo.groupId}" id="groupId"/>
        <div class="rig_cont fl">
        	<h3 class="rc_tit"><a href="groupdetail/${groupInfo.groupId}.html">返回</a>管理员权限</h3>
            <div class="manage_cont">
            	<div class="manager_wp">
                	<h4 class="mg_tit">请选择要操作的管理员</h4>
                    
                   <form action="groupaddmanager.html?groupId=${groupInfo.groupId}" name="searchForm" id="searchForm" method="post">
                    <div class="search_manager mt20">
                    	<div class="rowElem clearfix">
                        	<input class="sm_text" type="text" name="customerName" value="${(customerName)!''}"/>
                            <input class="sm_btn" type="submit" value="查找" />
                        </div><!--/rowElem-->
                    </div><!--/search_manager-->
                   </form> 
                   
                   <form name="updateForm" id="updateForm" method="post" action="">
            	    <input  type="hidden" name="groupId" value="${groupInfo.groupId }"/>
                    <ul class="add_list mt20 clearfix">
                        <#if ((pb.list)?size>0)>
                         <#list pb.list as groupcustomer>
	                         <li class="clearfix">
	                        	<div class="rowElem clearfix fl"><input type="radio" value="${groupcustomer.customerId}" name="customerId"/></div>
	                              <#if (groupcustomer.infoHeadimg)?? && (groupcustomer.infoHeadimg)!="">
		                            <img class="fl" width="48px" height="48px" alt="${groupcustomer.customerName}" src="${groupcustomer.infoHeadimg}" />
	                               <#else>
		                            <img class="fl" width="48px" height="48px" alt="${groupcustomer.customerName}" src="${basePath}/images/default_head3.jpg" />
	                             </#if>
	                            <div class="manager_info fl ml10">
	                            	<span>
	                            	 <#if (groupcustomer.customerName)?? &&(groupcustomer.customerName)?length gt 8>
	                             		 ${(groupcustomer.customerName)?substring(0,8)}
	                             	  <#else>${(groupcustomer.customerName)!''}
	                         		</#if>
	                            	</span>
	                            	<p class="mt10">${(groupcustomer.provinceName)!''} ${(groupcustomer.cityName)!''}</p>
	                            </div><!--/manager_info-->
	                        </li>
                         </#list>
                       </#if>
                    </ul><!--/manager_list-->
                   </form>
                  <form action="groupaddmanager.html?groupId=${groupInfo.groupId}" method="post" name="group_page_form" id="group_page_form">
		             <input id="page" name="pageNo" type="hidden" value="${pb.pageNo }"/>
		              <div class="pages tc mt30" id="pages">
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
		                  <#if ((pb.list)?size>0)> 
		                     <#list pb.startNo..pb.endNo as i>
		                      <a <#if pb.pageNo==i> class="cur"</#if> href="javascript:page('${i}');">${i}</a>
		                     </#list>
		                 </#if>
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
                    <p class="error_info">对不起，只能升级4个用户为管理员</p>
                    
                    <div class="manager_operating mt10 clearfix">
                    	<div class="rowElem clearfix fl mr10"><a onclick="becomemanager()"><input type="button" value="升级为管理员"/></a></div>
                        <div class="rowElem clearfix fl mr10"><a onclick="returnback()"><input type="button" value="返回" /></a></div>
                    </div><!--/manager_operating-->
                </div><!--/manager_wp-->
            </div><!--/manage_cont-->
        </div><!--/rig_cont-->
    </div>

 <div class="mask1"></div>
     <div class="dialog1 dia1"  style="width: 300px; height: 120px;">
    	<a class="close" href="javascript:cls1();" ></a>
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
					$(".rowElem").each(function(){
					var _this = $(this);
					_this.find(".jqTransformCheckbox").click(function(){
						if($(this).hasClass("jqTransformChecked")) {
							$(this).removeClass("jqTransformChecked");
							_this.find("input").prop("checked","");
						}else {
							$(this).addClass("jqTransformChecked");
							_this.find("input").prop("checked","true");
						};
					});
					_this.parent("li").click(function(){
						if(_this.find(".jqTransformChecked").length>0){
							_this.find("input").prop("checked","true");
						} else {
							_this.find("input").prop("checked","");
						};
					});
				});
				});
				
		$(function(){
			//选中首页社区
			$(".sort_list li").removeClass("cur");
			$(".sort_list li:eq(1)").addClass("cur");
        });	
</script>
</body>
</html>


