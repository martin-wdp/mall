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
        <input type="hidden" id="groupId" value="${groupInfo.groupId}" />
      <div class="rig_cont fl">
           <div class="clearfix rc_tit">
        	 <h3 class="fl fb">管理员权限</h3>
        	 <a class="fr f12 mr10" href="groupdetail/${groupInfo.groupId}.html" style="font-weight:normal;">返回</a>
           </div>
            <div class="manage_cont">
            	<div class="manager_wp">
                	<h4 class="mg_tit">请选择要操作的管理员</h4>
                <#if ((pb.list)?size>0)>
                <form name="updateForm" id="updateForm" method="post">
                   <input type="hidden" value="${groupInfo.groupId}" name="groupId" />
				   <input type="hidden" id="authorId" value="${groupInfo.createId}" name="createAuthorId" />
                    <ul class="manager_list mt20 clearfix">
                         <#list pb.list as groupmaneger>
	                          <li class="clearfix">
	                        	<div class="rowElem clearfix fl"><input type="checkbox" name="customerId" value="${groupmaneger.customerId}"/></div>
	                            <#if (groupmaneger.infoHeadimg)?? && (groupmaneger.infoHeadimg)!="">
	                              <img class="fl" alt="${groupmaneger.customerName}" width="48px" height="48px" src="${groupmaneger.infoHeadimg}" />
	                            <#else>
	                              <img class="fl" alt="${groupmaneger.customerName}" width="48px" height="48px" src="${basePath}/images/default_head3.jpg" />
	                            </#if>
	                            <div class="manager_info fl ml10">
	                            	<span>
	                            	<#if (groupmaneger.customerName)?length gt 8>
		                              ${(groupmaneger.customerName)?substring(0,8)}
		                             <#else>${groupmaneger.customerName}
		                             </#if>
	                            	</span>
	                            	<p class="mt10">${(groupmaneger.provinceName)!''} ${(groupmaneger.cityName)!''}</p>
	                            </div><!--/manager_info-->
	                         </li>
                         </#list>
                    </ul><!--/manager_list-->
                    </form>
                    </#if>
                    <div class="manager_operating mt10 clearfix">
                     <#if ((pb.list)?size gt 0)>
                    	<div class="rowElem clearfix fl mr10"><a onclick="removeManagement()"><input type="button" value="免去已选择的管理员" /></a></div>
                    </#if>
                        <div class="rowElem clearfix fl mr10"><a onclick="addManagement()"><input type="button" value="添加新管理员" /></a></div>
                     <#if ((pb.list)?size gt 0)>
                        <div class="rowElem clearfix fl mr10"><a onclick="transferGroup()"><input type="button" value="转让本小组" /></a></div>
                     </#if>
                    </div><!--/manager_operating-->
                
                </div><!--/manager_wp-->
                <div class="manager_per mt50">
                	<h4 class="mg_tit">以下是管理员的权限</h4>
                    <ul class="per_list mt30">
                    	<li>删除帖子</li>
                        <li>删除图片</li>
                        <li>删除评论</li>
                        <li>帖子管理<span>（置顶、热帖、精选、取消）</span></li>
                        <li>成员管理<span>（除了提升管理员之外的全部权限，包括黑名单管理、提出成员、释放黑名单）</span></li>
                        <li>管理回收站<span>（删帖和恢复）</span></li>
                        <li>修改帖子回应权限<span>（禁止回应和恢复、删除回复权限、修改帖子权限）</span></li>
                        <li>管理小组设置<span>（小组头像、小组简介、小组背景图、访问小组方式、小组名称）</span></li>
                    </ul><!--/per_list-->
                </div><!--/manager_per-->
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
<script type="text/javascript" charset="utf-8" src="${basePath}/js/social/group_comm.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}/js/social/group_info.js"></script>
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
				function clopage(){
				    window.location.href="managerlimit.html?groupId=${groupInfo.groupId}";
				}
				
		 	$(function(){
			//选中首页社区
			$(".sort_list li").removeClass("cur");
			$(".sort_list li:eq(1)").addClass("cur");
        });
</script>
</body>
</html>


