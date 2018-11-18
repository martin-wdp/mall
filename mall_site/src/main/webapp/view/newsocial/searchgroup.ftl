<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl" />
<title>搜索小组-${topmap.systembase.bsetName}</title>
<link type="text/css" rel="stylesheet" href="${basePath}/css/base.min.css"/>
<link type="text/css" rel="stylesheet" href="${basePath}/css/group.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>

<style type="text/css">
.group_name:hover{ text-decoration:none;}
.s_team_name:hover{ text-decoration:none;}
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
	<input  type="hidden" id="groupName" value="${groupName!''}"/>
    	<div class="left_cont fl">
        	<div class="groupBox">
            	<p><em>社区：</em>共<span>${groupcount!'0'}</span>个小组，<span>${groupmember!'0'}</span>个成员。欢迎加入我们。</p>
                <form id="group_search_form" action="" method="get" target="_blank">
                <div class="group_search mt10">
                	<select style="height:27px; border:1px solid #ddd;" id="flagSearch" class="selector">
                      <option value="1">小组搜索</option>
                      <option value="2">话题搜索</option>
                    </select>
                    <input class="g_search_text" type="text" name="groupName" value="${groupName!''}" id="searchValue"/>
                    <input type="hidden"  id="searchFlag"/>
                    <input class="g_search_btn" type="submit" value="搜索" onclick="search()" />
                </div><!--/group_search-->
                </form>
            </div><!--/groupBox-->
            
            <div class="search_wp mt20">
               <#if groupName??>
            	<h3 class="search_tit"><em>“${groupName}”</em>的搜索结果</h3>
            	<#else><h3 class="search_tit">全部的搜索结果</h3>
               </#if>
                
                <ul class="search_list mt20">
                    <#if pb.list??>
                     <#list pb.list as group>
	                      <li class="clearfix">
	                    	<a class="fl s_team_img" href="groupdetail/${group.groupId }.html" target="_blank">
	                    	<#if (group.groupHead)??>
	                    	<img alt="${group.groupName}" height="120px" width="120px" src="${group.groupHead}" />
	                    	<#else>
	                    	<img alt="${group.groupName}" height="120px" width="120px" src="${basePath}/images/default_head2.jpg" />
	                    	</#if>
	                    	</a>
	                        <div class="s_team_info fl ml10">
	                        	<#assign str="${groupName!''}"/>
	                        	<#assign restr="<em>${groupName!''}</em>"/>
	                        	<a class="s_team_name" href="groupdetail/${group.groupId }.html" target="_blank">${(group.groupName)?replace(str,restr) }</a>
	                            <div class="g_info mt15">
	                             	<a class="m_num" href="groupdetail/${group.groupId }.html" target="_blank">${(group.groupmember)!'0' }</a>
	                                <a class="c_num" href="groupdetail/${group.groupId }.html" target="_blank">${(group.topicCount)!"0"}</a>
	                            </div><!--/g_info-->
	                            <div class="s_team_dp"> 
	                            <#if (group.groupRemark)?length gt 70>
		                                ${(group.groupRemark)?substring(0,70)}
		                             <#else>${group.groupRemark}
		                         </#if>
	                            </div>
	                        </div><!--/s_team_info-->
	                        <div id="added${group.groupId }">
	                        <#if !cusId??>
	                          <a class="add_team fr" href="javascript:void(0);"  onclick="addGroup('${group.limitAddType!''}','${group.groupId}')">加入小组</a>
	                        </#if>
	                        <#if cusId?? >
		                    	 <#if (group.customerFlag)?? && (cusId==group.customerFlag)>
			                    	  <div class="added_team fr">已加入小组
				                    	  <#if  group.customerPower !='2'>
				                    	 	 <a class="quit_team" href="javascript:void(0);" onclick="outGroup('${cusId!''}','${group.groupId}')">退出</a>
				                    	  </#if>
			                    	  </div>
			                    	 <#else>
		                       		  <a class="add_team fr" href="javascript:void(0);"  onclick="addGroup('${group.limitAddType!''}','${group.groupId}')">加入小组</a>
		                    	  </#if>
	                    	 </#if>
	                       </div>
	                    </li>
                     </#list>
                    </#if>
                </ul><!--/search_list-->
                
               
                 <#if (pb.list?size>0)>
                <form action="groupsearchlist.html" method="get" name="group_page_form" id="group_page_form">
                 <input id="page" name="pageNo" type="hidden" value="${pb.pageNo }"/>
                 <input name="groupName" id= "page_keyword" type="hidden" value="${groupName!''}"/>
                  <div class="pages tc mt30">
                   <!--判断上一页--> 
                     <a class="pg_prev" href="javascript:void(0)" <#if ((pb.pageNo)>1)> onclick="page('${pb.pageNo-1}')"
                       <#else>  style="color:#999;visibility:hidden" </#if>
                       >&lt;上一页</a>
                     <#if ((pb.startNo)>1)> 
                      <a href="javascript:page('1');" >1</a>
									...
                     </#if>
                     <#list pb.startNo..pb.endNo as i>
                      <a  href="javascript:void(0)" <#if pb.pageNo==i> class="cur"</#if> onclick="page(${i})">${i}</a>
                     </#list>
                     <#if (pb.totalPages > pb.endNo)>
                        ...
                        <a href="javascript:page('${pb.totalPages}')"${pb.totalPages}></a>
                     </#if>
                     <!--判断下一页-->
                     <a class="pg_next"  href="javascript:void(0)" 
                       <#if ((pb.pageNo+1) > pb.totalPages)>
                      style="color:#999;visibility:hidden"
                       <#else> onclick="page('${pb.pageNo+1}')"
                       </#if>>下一页&gt;
                     </a>
                  </div>
                 </form>
                </#if>
            </div><!--/search_wp-->
        </div><!--/left_cont-->
        
        <div class="right_cont fr">
        	<a class="create_team" href="javascript:void(0);" onclick="creategroup()">创建小组</a>
            <#if cusId??>
            <div class="my_group"><a href="myjoinedgroup.html" target="_blank">我管理/加入的小组</a></div>
            </#if>
            <div class="group_sort mt30">
            	<h3>小组分类</h3>
                <div class="g_sort mt10">
                	<ul class="g_sort_list clearfix">
                    	<li><a href="groupList.html" target="_blank">全部</a></li>
                    	<#if grouptypelist??>
	                        <#list grouptypelist as groupType>
	                        	<li><a href="groupList.html?groupTypeId=${groupType.groupTypeId}" target="_blank">${groupType.groupTypeName}</a></li>
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
               			 </div><!--/slides_container-->
           			 </div><!--/gg_slides-->
				        </#list>
				<#else>
				 <div id="gg_slides" class="mt20">
            		<div class="slides_container">
				      <div class="slide"><a href="javascript:void(0);"><img alt="" src="${basePath}/images/images_17.jpg" width="290" height="200" /></a></div>
			         </div>
			     </div> 
			 </#if>
            
        </div><!--/right_cont-->
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
				
		 $(function(){
			//选中首页社区
			 $(".sort_list li").removeClass("cur");
			 $(".sort_list li:eq(1)").addClass("cur");
         });
        
         function search(){
	       var searchFlag =  $("#flagSearch").val();
	       if(searchFlag == 1){
	          $(".selector").find("option[value='1']").attr("selected",true);
	          $("#searchValue").attr("name","groupName");      
	          $("#group_search_form").attr("action","groupsearchlist.html");
	       }else{
	          $(".selector").find("option[value='2']").attr("selected",true);
	          $("#searchValue").attr("name","keyword");      
	          $("#searchFlag").attr("name","flag");      
	          $("#searchFlag").val("1");      
	          $("#group_search_form").attr("action","topiclist.html");
	       }
	        $("#group_search_form").submit();
	 }
</script>
</body>
</html>
