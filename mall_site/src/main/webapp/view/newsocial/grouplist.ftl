<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl" />
<title>小组列表-${topmap.systembase.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/> 
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>

<style type="text/css">
.group_fenlei .content ul li{ padding:0px 1px; float:left; line-height:26px;margin-right: 5px; cursor:pointer;}
.group_fenlei .content ul li a:hover{ text-decoration:none;}
.group_fenlei .content ul li.current { color:#df242a; font-weight:bold;}
.team_intro {line-height:150%; margin-top:5px; height:36px; overflow:hidden;}
.team_name:hover{ text-decoration:none;}

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
<div class="wrapper clearfix">
    	<div class="left_cont fl">
    	  <form action="grouplist.html" method="post" id="labelform">
    	   <input type="hidden" name="groupLabelIds" id="groupLabelId" value="${labelIds !''}">
    	   <input type="hidden" name="groupTypeId" id="gTypeId" value="${groupTypeId !''}">
            <div class="group_fenlei">
                <div class="tagMenu">
                    <span>分类筛选:</span>
                    <ul class="fenll_list fl menu" style="overflow:hidden;width:750px;">
                        <li <#if !groupTypeId??>class="current"</#if> onclick="window.location.href='groupList.html'">全部</li>
                        <#if grouptypelist??>
	                        <#list grouptypelist as groupType>  
	                        	<li 
	                        	<#if groupTypeId??><#if groupTypeId==(groupType.groupTypeId)> class="current"<#else>class=''</#if></#if>
	                        	onclick="addType('${groupType.groupTypeId}')">${groupType.groupTypeName}</li>
	                        </#list>
                        </#if>
                    </ul>
                  <div class="cl">&nbsp;</div>
                </div>
                <div class="content" style="overflow:hidden;">
                    <div class="layout">
                    <span>标签筛选:</span>
                        <ul class="ml30">
                            <#if grouplabellist??>
	                            <#list grouplabellist as label>
		                            <li <#if labelIdlist??> 
		                                <#list labelIdlist as lab>
		                                	<#assign i=0>	
				                                  <#if lab==label.groupLabelId&&i==0>
					                                  class="current"
					                                  <#assign i=1>
				                                 </#if>
		                                </#list>
		                               </#if>
		                            id="${label.groupLabelId}" onclick="addlabel('${label.groupLabelId}')">${label.groupLabelName}</li>
			                     </#list>
	                        </#if>
                        </ul>
                        <div class="cl">&nbsp;</div>
                    </div>
                </div>
            </div><!--group_fenlei-->
          </form>
        	<div class="groupBox">
            	<p><em>社区：</em>共<span>${groupcount!'0'}</span>个小组，<span>${groupmember!'0'}</span>个成员。欢迎加入我们。</p>
                <form id="group_search_form" action="groupsearchlist.html" method="get" target="_blank">
                <div class="group_search mt10">
                	<em>小组搜索：</em>
                    <input class="g_search_text" type="text" name="groupName"/>
                    <input class="g_search_btn" type="submit" value="搜索" />
                </div><!--/group_search-->
                </form>
            </div><!--/groupBox-->
            
            <div class="all_team mt20">
              <form action="grouplist.html" method="post" name="group_sort_form" id="group_sort_form">
             	<input id="sort" name="sort" type="hidden" value="${sort!''}"/>
             	<input id="groupTypeId" name="groupTypeId" type="hidden" value="${(groupType.groupTypeId)!''}"/>
            	<div class="tit_wp clearfix">
            		<#if groupType??>
            		  <h3 class="fl">${groupType.groupTypeName }小组</h3>
            		<#else><h3 class="fl">全部小组</h3>
            		</#if>
                    <div class="team_filter down_wp fr">
                    	<em>成员最多</em>
                        <ul class="tf_list down_list mt5 mb5">
                        	<li <#if !sort??>class="cur"<#else>''</#if>><a href="javascript:void(0);" onclick="groupSort('')">成员最多</a></li>
                            <li <#if sort??><#if sort=='1'>class="cur"<#else>''</#if></#if> ><a href="javascript:void(0);" onclick="groupSort('1')">话题最多</a></li>
                            <li <#if sort??><#if sort=='2'>class="cur"<#else>''</#if></#if> ><a href="javascript:void(0);" onclick="groupSort('2')">最新创建</a></li>
                        </ul><!--/tf_list-->
                    </div><!--/team_filter-->
                </div><!--/tit_wp-->
               </form> 
                <ul class="team_list clearfix mt20">
                    <#if pb.list??>
                      <#list pb.list as group>
	                	<li class="clearfix">
	                    	<a class="fl team_img" href="groupdetail/${group.groupId}.html" target="_blank">
	                    	<#if (group.groupHead)??>
	                    	 <img height="78px" width="78px" alt="${group.groupName}" src="${group.groupHead}" /></a>
	                    	<#else>
	                    	 <img height="78px" width="78px" alt="${group.groupName}" src="${basePath}/images/default_head2.jpg" /></a>
	                    	</#if>
	                        <div class="team_info fl ml10">
	                        	<a class="team_name" href="groupdetail/${group.groupId}.html" target="_blank">
	                        		<#if (group.groupName)?length gt 7>
		                                ${(group.groupName)?substring(0,7)}
		                             <#else>${group.groupName}
		                           </#if>
	                        	</a>
	                            <p class="member_num mt5"><a href="groupdetail/${group.groupId}.html" target="_blank">${(group.groupmember)!'0'}</a></p>
	                            <a href="groupdetail/${group.groupId}.html" target="_blank" style="color: #333;">
	                            <p class="team_intro">
	                            	<#if (group.groupRemark)?length gt 20>
		                                ${(group.groupRemark)?substring(0,20)}
		                            <#else>
		                             ${group.groupRemark}
		                           
		                           </#if></p>
		                        </a>
	                        </div><!--/team_info-->
	                    </li>
                      </#list>
                    </#if>
                </ul><!--/team_list-->
                
                <#if (pb.list?size>0)>
                <form action="grouplist.html" method="post" name="group_page_form" id="group_page_form">
                 <input id="page" name="pageNo" type="hidden" value="${pb.pageNo }"/>
                 <input name="groupName" id= "page_keyword" type="hidden"/>
                 <input name="groupTypeId" id= "page_groupCategoryId" type="hidden" />
                 <input id="page_sort" name="sort" type="hidden" value="${sort!''}"/>
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
            </div><!--/all_team-->
        </div><!--/left_cont-->
        
        <div class="right_cont fr">
        	<a class="create_team" href="javascript:void(0);" onclick="creategroup()">创建小组</a>
             <#if cusId??>
            <div class="my_group"><a href="myjoinedgroup.html">我管理/加入的小组</a></div>
            </#if>
            <div class="group_sort mt30">
            	<h3>小组分类</h3>
                <div class="g_sort mt10">
                	<ul class="g_sort_list clearfix">
                    	<li><a href="grouplist.html">全部</a></li>
                    	<#if grouptypelist??>
	                        <#list grouptypelist as groupType>
	                        	<li><a href="grouplist.html?groupTypeId=${groupType.groupTypeId}">${groupType.groupTypeName}</a></li>
	                        </#list>
                        </#if>
                    </ul><!--/g_sort_list-->
                </div><!--/g_sort-->
            </div><!--/group_sort-->
            
            <#if advlist??>
              <#list advlist as adv>
		          <div id="gg_slides" class="mt20">
		             <div class="slides_container">
				       	 <div class="slide"><a href="${adv.advUrl}" target="_blank"><img src="${adv.advImg}" width="290" height="200" alt="${adv.advName}"/></a></div>
				     </div>
				   </div>
				</#list>
			<#else>
				   <div id="gg_slides" class="mt20">
		             <div class="slides_container">
				        <div class="slide"><a href="javascript:void(0);"><img alt="" src="${basePath}/images/images_17.jpg" width="290" height="200" /></a></div>
		                </div><!--/slides_container-->
		            </div><!--/gg_slides-->
		     </#if>
        </div><!--/right_cont-->
    </div>  
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
<script type="text/javascript">
		$(function() {
			$(".rowElem").jqTransform();
		});
				
		$(function(){
			var startSlide = 1;
			$('#gg_slides').slides({
				preload: true,
				generatePagination: true,
				play: 5000,
				pause: 2500,
				hoverPause: true,
				start: startSlide
				});
		});
		
     
     function addlabel(labelId){
       var grouplabelIds = $("#groupLabelId").val();
       var groupLabelId = new Array();
       groupLabelId = grouplabelIds.split(","); 
       for(var lId in groupLabelId){
         if(labelId == groupLabelId[lId]){
               if(groupLabelId.length==1){
                  groupLabelId.shift();
                  grouplabelIds = '';
               }else{
                  groupLabelId.splice(lId,1);
                  grouplabelIds = groupLabelId.join(',');
               }
	            $("#groupLabelId").val(grouplabelIds);
	            $("#labelform").submit();
	            return false;
         }else{
	          if(grouplabelIds !=''){
		           $("#groupLabelId").val(grouplabelIds+","+labelId);
	           }else{
		           $("#groupLabelId").val(labelId);
	           }
	          $("#labelform").submit();
         }
       }
    }
     
     function addType(tyepId){
       $("#gTypeId").val(tyepId);
       $("#labelform").submit();
     }
     
     	$(function(){
			//选中首页社区
			$(".sort_list li").removeClass("cur");
			$(".sort_list li:eq(1)").addClass("cur");
        });
</script>
</body>
</html>
