<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl" />
<title>小组成员-${topmap.systembase.bsetName}</title>
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
<div class="wrapper clearfix">
    	<div class="left_cont fl">
        	<div class="leaderWp">
            	<h3 class="m_tit">组长</h3>
                <ul class="memberList mt20 clearfix">
                	<li class="head_box clearfix">
                    	<a class="m_head fl" href="${basePath}/customerhomepage/${groupLeader.customerId}.html">
                    	<#if (groupLeader.infoHeadimg)?? && (groupLeader.infoHeadimg)!="">
                    		<img  height="50px" width="50px" alt="${groupLeader.customerName}" src="${groupLeader.infoHeadimg}" title="${groupLeader.customerName}" /></a>
                    	<#else>
                    		<img  height="50px" width="50px" alt="${groupLeader.customerName}" src="${basePath}/images/default_head3.jpg" title="${groupLeader.customerName}" /></a>
                    	</#if>
                        <div class="m_info fl ml10">
                        	<a class="m_name" href="${basePath}/customerhomepage/${groupLeader.customerId}.html">${groupLeader.customerName}</a>
                            <p class="mt10">${(groupLeader.provinceName)!''} ${(groupLeader.cityName)!''}</p>
                        </div><!--/m_info-->
                    </li>
                   
                </ul><!--/memberList-->
            </div><!--/leaderWp-->
            
            <div class="managerWp mt10">
            	<h3 class="m_tit">管理员</h3>
                <ul class="memberList mt20 clearfix">
                    <#if groupManager??>
                      <#list groupManager as man>
                        <li class="head_box clearfix">
		                	<a class="m_head fl" href="${basePath}/customerhomepage/${(man.customerId)!''}.html">
		                	<#if (man.infoHeadimg)?? && (man.infoHeadimg)!="">
                    			<img  height="50px" width="50px" alt="${groupLeader.customerName}" src="${groupLeader.infoHeadimg}" title="${groupLeader.customerName}" /></a>
                    	   <#else>
                    			<img  height="50px" width="50px" alt="${groupLeader.customerName}" src="${basePath}/images/default_head3.jpg" title="${groupLeader.customerName}" /></a>
                    	   </#if>
		                    <div class="m_info fl ml10">
		                    	<a class="m_name" href="${basePath}/customerhomepage/${(man.customerId)!''}.html">${(man.customerName)!''}</a>
		                        <p class="mt10">${(man.provinceName)!''} ${(man.cityName)!''}</p>
		                    </div><!--/m_info-->
                        </li>
                      </#list>
                    </#if>
                </ul><!--/memberList-->
            </div><!--/managerWp-->
            
            <div class="otherWp mt10">
            	<h3 class="m_tit">其他成员</h3>
            	<form action="${basePath}/groupmemberlist/${group.groupId }.html" method="post">
                <div class="om_wp clearfix mt10">
                	<span class="fl mt5">${group.groupName}小组中共有${(group.groupmember)!'0'}个成员</span>
                    <div class="topic_search clearfix fr">
                    	<input class="ts_text fl" type="text" name="customerName"  value="${(customerName)!''}" placeholder="输入昵称查找" />
                        <input class="ts_btn fl" type="submit" value=""/>
                    </div><!--/topic_search-->
                </div><!--/om_wp-->
                </form>
                <ul class="memberList mt20 clearfix">
                   <#if pb.list??>
                	 <#list pb.list as member>
	                    <li class="head_box clearfix">
		                	<a class="m_head fl" href="${basePath}/customerhomepage/${(member.customerId)!''}.html">
		                	<#if (member.infoHeadimg)?? && (member.infoHeadimg)!="">
			                	<img  height="50px" width="50px" alt="${(member.customerName)!''}" src="${member.infoHeadimg}" title="${(man.customerName)!''}" /></a>
		                	 <#else>
			                	<img  height="50px" width="50px" alt="${(member.customerName)!''}" src="${basePath}/images/default_head3.jpg" title="${(man.customerName)!''}" /></a>
		                	</#if>
		                    <div class="m_info fl ml10">
		                    	<a class="m_name" href="${basePath}/customerhomepage/${(member.customerId)!''}.html">
		                    	 <#if (member.customerName)?length gt 8>
		                          ${(member.customerName)?substring(0,8)}
		                         <#else>${member.customerName}
		                         </#if>
		                    	</a>
		                        <p class="mt10">${(member.provinceName)!''} ${(member.cityName)!''}</p>
		                    </div><!--/m_info-->
                        </li>
                	 </#list>
                	</#if>
                </ul><!--/memberList-->
                
          <form action="${basePath}/groupmemberlist/${group.groupId}.html" method="post" name="group_page_form" id="group_page_form">
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
             
            </div><!--/otherWp-->
        </div><!--/left_cont-->
        
        <div class="right_cont fr">
        	<div class="team_box clearfix">
            	<a class="fl" href="${basePath}/groupdetail/${group.groupId}.html">
            	<#if (group.groupHead)?? && (group.groupHead)!="">
            	  <img width="50px" height="50px" alt="${group.groupName}" src="${group.groupHead}" title="${group.groupName}" /></a>
				<#else>            	
            	  <img width="50px" height="50px" alt="${group.groupName}" src="${basePath}/images/default_head3.jpg" title="${group.groupName}" /></a>
            	</#if>
                <div class="tb_info fl pt5 ml10">
                	<a href="${basePath}/groupdetail/${group.groupId}.html">${group.groupName}</a>
                    <p>本小组共有${(group.groupmember)!''}个成员</p>
                </div><!--/tb_info-->
            </div><!--/team_box-->
            
            <div class="latest_add mt20">
            	<h3>最新加入<a href="${basePath}/groupmemberlist/${group.groupId}.html">（所有${(group.groupmember)!''}个成员）</a></h3>
                <ul class="la_list clearfix mt20" style="width:300px;">
                    <#if groupmember??>
                      <#list groupmember as lastestmem>
                         <li><a href="${basePath}/customerhomepage/${lastestmem.customerId}.html">
                         <#if (lastestmem.infoHeadimg)?? && (lastestmem.infoHeadimg)!="">
    	                     <img width="50px" height="50px" alt="${(lastestmem.customerName)!''}" src="${lastestmem.infoHeadimg}" title="${(lastestmem.customerName)!''}"/>
                           <#else>
	                         <img width="50px" height="50px" alt="${(lastestmem.customerName)!''}" src="${basePath}/images/default_head3.jpg" title="${(lastestmem.customerName)!''}"/>
                         </#if>
                           <#if (lastestmem.customerName)?length gt 6>
		                          ${(lastestmem.customerName)?substring(0,6)}
		                    <#else>${lastestmem.customerName}
		                    </#if>
	                       </a></li>
                      </#list>
                    </#if>
                </ul><!--/la_list-->
            </div><!--/latest_add-->
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
<script type="text/javascript" src="${basePath}/js/social/group_detail.js"></script>
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

