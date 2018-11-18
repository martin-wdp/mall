<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "..//common/basepath.ftl" />
<title>我加入/管理的小组-${topmap.systembase.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/> 

<style type="text/css">
.group_name:hover{ text-decoration:none;}
.join_group_list a:hover{ text-decoration:none;}
.group_unchekced_name {
font-size: 14px;
color: #666666;
font-weight: 700;
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
<div class="wrapper clearfix">
    	<div class="left_cont fl">
        	<div class="groupBox">
            	<p><em>社区：</em>共<span>${groupcount!'0'}</span>个小组，<span>${groupmember!'0'}</span>个成员。欢迎加入我们。</p>
                <form id="group_search_form" action="${basePath}/groupsearchlist.html"  method="get">
                <div class="group_search mt10">
                	<em>小组搜索：</em>
                    <input class="g_search_text" type="text" name="groupName" />
                    <input class="g_search_btn" type="submit" value="搜索" />
                </div><!--/group_search-->
                </form>
            </div><!--/groupBox-->
            
            <div class="hot_group mt20">
            	<h3 class="tit" style="color:#3366bb;">我管理的小组</h3>
                <div class="group_wp mt20 clearfix">
                   <#if mymanagergroup??>
                     <#list mymanagergroup as manager>
	                    <#if manager.groupCheckFlag=='1'>
		                	<div class="group_box clearfix fl">
		                    	<a class="group_img fl" href="${basePath}/groupdetail/${manager.groupId}.html" target="_blank">
		                    	<#if (manager.groupHead)??>
		                    	<img height="120" width="120" alt="${manager.groupName}" src="${manager.groupHead}"/>
		                    	<#else>
		                    	<img height="120" width="120" alt="${manager.groupName}" src="${basePath}/images/default_head2.jpg" />
		                    	</#if>
		                    	</a>
		                        <div class="group_info fl ml10">
		                        	<a class="group_name" href="${basePath}/groupdetail/${manager.groupId}.html" target="_blank"><#if (manager.groupSecret)=='1'><span>(私)</span></#if> <#if (manager.groupName)?length gt 7>
			                                ${(manager.groupName)?substring(0,7)}
			                             <#else>${manager.groupName}
			                         </#if></a>
		                            <div class="g_info mt10">
		                            	<a class="m_num" href="${basePath}/groupdetail/${manager.groupId}.html" target="_blank">${(manager.groupmember)!'0'}</a>
		                            </div><!--/g_info-->
		                            <p>
		                            <#if (manager.groupRemark)?length gt 20>
			                                ${(manager.groupRemark)?substring(0,20)}
			                             <#else>${manager.groupRemark}
			                         </#if>
		                            </p>
		                        </div><!--/group_info-->
		                    </div><!--/group_box-->
	                   </#if> 
	                  <#if manager.groupCheckFlag=='0'>
		                	<div class="group_box clearfix fl">
		                    	<a class="group_img fl" href="javascript:void(0)">
		                    	<#if (manager.groupHead)??>
		                    	<img height="120" width="120" alt="${manager.groupName}" src="${manager.groupHead}"/>
		                    	<#else>
		                    	<img height="120" width="120" alt="${manager.groupName}" src="${basePath}/images/default_head2.jpg" />
		                    	</#if>
		                    	</a>
		                        <div class="group_info fl ml10">
		                        	<a class="group_unchekced_name" href="javascript:void(0)"><#if (manager.groupSecret)=='1'><span>(私)</span></#if> <#if (manager.groupName)?length gt 7>
			                                ${(manager.groupName)?substring(0,7)}
			                             <#else>${manager.groupName}
			                         </#if>(审核中)</a>
		                            <div class="g_info mt10">
		                            	<a class="m_num" href="javascript:void(0)">${(manager.groupmember)!'0'}</a>
		                            </div><!--/g_info-->
		                            <p>
		                            <#if (manager.groupRemark)?length gt 20>
			                                ${(manager.groupRemark)?substring(0,20)}
			                             <#else>${manager.groupRemark}
			                         </#if>
		                            </p>
		                        </div><!--/group_info-->
		                    </div><!--/group_box-->
	                    </#if> 
                     </#list>
                  </#if>
                    
                </div><!--/group_wp-->

            </div><!--/hot_group-->
            
            <div class="hot_group">
                <h3 class="tit" style="color:#3366bb;">我加入的小组</h3>
                <div class="join_group mt15">
                   <#if myjoinedgroup.list??>
                     <#list myjoinedgroup.list as join>
	                    <div class="join_group_list">
	                        <a href="${basePath}/groupdetail/${join.groupId}.html">
	                        <#if (join.groupHead)??>
	                    	<img height="78px" width="78px" alt="${join.groupName}" src="${join.groupHead}"/>
	                    	<#else>
	                    	<img height="78px" width="78px" alt="${join.groupName}" src="${basePath}/images/default_head2.jpg" />
	                    	</#if>
	                        </a>
	                        <a href="${basePath}/groupdetail/${join.groupId}.html"><#if (join.groupSecret)=='1'><span style="color: #ff0000;">(私)</span></#if><#if (join.groupName)?length gt 6>
		                                ${(join.groupName)?substring(0,6)}
		                             <#else>${join.groupName}
		                         </#if></a>
	                    </div>
                     </#list>
                   </#if>
                </div>
            </div>
             
              <#if (myjoinedgroup.list?size>0)>
                <form action="${basePath}/myjoinedgroup.html" method="post" name="group_page_form" id="group_page_form">
                 <input id="page" name="pageNo" type="hidden" value="${myjoinedgroup.pageNo }"/>
                  <div class="pages tc mt30">
                   <!--判断上一页-->
                     <a class="pg_prev" 
                      <#if ((myjoinedgroup.pageNo)>1)>
                       href="javascript:page('${myjoinedgroup.pageNo-1}')"
                       <#else>href="javascript:void(0)" style="color:#999;visibility:hidden"</#if>
                       >&lt;上一页</a>
                     <#if ((myjoinedgroup.startNo)>1)> 
                      <a href="javascript:page('1');" >1</a>
									...
                     </#if>
                     <#list myjoinedgroup.startNo..myjoinedgroup.endNo as i>
                      <a <#if myjoinedgroup.pageNo==i> class="cur"</#if> href="javascript:page('${i}');">${i}</a>
                     </#list>
                     <#if (myjoinedgroup.totalPages > myjoinedgroup.endNo)>
                        ...
                        <a href="javascript:page('${myjoinedgroup.totalPages}')"${myjoinedgroup.totalPages}></a>
                     </#if>
                     <!--判断下一页-->
                     <a class="pg_next" 
                       <#if ((myjoinedgroup.pageNo+1) > myjoinedgroup.totalPages)>
                       href="javascript:void(0)" style="color:#999;visibility:hidden"
                       <#else>href="javascript:page('${myjoinedgroup.pageNo+1}')"
                       </#if>>下一页&gt;
                     </a>
                  </div>
                 </form>
                </#if>
        </div><!--/left_cont-->
        
        <div class="right_cont fr">
        	<a class="create_team" href="javascript:void(0);" onclick="creategroup()">创建小组</a>
            <#if cusId??>
            <div class="my_group"><a href="${basePath}/myjoinedgroup.html">我管理/加入的小组</a></div>
            </#if>
            
           <div class="group_sort mt30">
            	<h3>小组分类</h3>
                <div class="g_sort mt10">
                	<ul class="g_sort_list clearfix">
                    	<li><a href="${basePath}/groupList.html">全部</a></li>
                    	<#if grouptypelist??>
	                        <#list grouptypelist as groupType>
	                        	<li><a href="${basePath}/groupList.html?groupTypeId=${groupType.groupTypeId}">${groupType.groupTypeName}</a></li>
	                        </#list>
                        </#if>
                    </ul><!--/g_sort_list-->
                </div><!--/g_sort-->
            </div><!--/group_sort-->
            
            
           	 <div id="gg_slides" class="mt20">
            	<div class="slides_container">
	              <#if advlist??>
                	<#list advlist as adv>
				     	  	<div class="slide"><a href="${adv.advUrl}" target="_blank"><img src="${adv.advImg}" width="290" height="200" alt="${adv.advName}"/></a></div>
				    </#list>
				  <#else>
				      <div class="slide"><a href="javascript:void(0);"><img alt="" src="${basePath}/images/images_17.jpg" width="290" height="200" /></a></div>
				      <div class="slide"><a href="javascript:void(0);"><img alt="" src="${basePath}/images/images_17.jpg" width="290" height="200" /></a></div>
				      <div class="slide"><a href="javascript:void(0);"><img alt="" src="${basePath}/images/images_17.jpg" width="290" height="200" /></a></div>
			   </#if>
               </div><!--/slides_container-->
           </div><!--/gg_slides-->
            
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
<script type="text/javascript" src="${basePath}/js/social/group_comm.js"></script>
<script type="text/javascript">
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
				
		 $(function(){
			//选中首页社区
			 $(".sort_list li").removeClass("cur");
			 $(".sort_list li:eq(1)").addClass("cur");
         });
</script>
</body>
</html>

