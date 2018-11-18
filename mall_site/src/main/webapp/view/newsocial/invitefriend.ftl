<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl"> 
<title>邀请好友-${topmap.systembase.bsetName}</title>
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
<div class="wrapper clearfix">
    	<div class="left_cont fl">
        	<h3 class="m_tit">邀请好友</h3>
            <div class="om_wp clearfix mt10">
                <span class="fl mt5">你总共有${(pb.rows)!'0'}位相互专注的好友</span>
                 <form action="${basePath}/invitefriends-${group.groupId}.html" method="post">
                <div class="topic_search clearfix fr">
                    <input class="ts_text fl" type="text" name="keyvalue" id="customerName" value="${customerName!''}" placeholder="输入昵称查找好友" />
                    <input class="ts_btn fl" type="submit" value="" />
                </div><!--/topic_search-->
                </form>
            </div><!--/om_wp-->
            
           <#if pb.list??>
            <ul class="friends_list clearfix mt20">
                <#list pb.list as fans>
            		<li><a href="javascript:void(0);" class="clearfix">
            		<input type="hidden" name= "customerIds" value="${fans.customerId } "/>
            		<#if (customer.custoemrImg)?? && (customer.custoemrImg)!="">
	            		<img  width="50px" height="50px" alt="${fans.customerName}" src="${customer.custoemrImg}" />
            		<#else>
    	        		<img  width="50px" height="50px" alt="${fans.customerName}" src="${basePath}/images/default_head3.jpg" />
            		</#if>
            		${fans.customerName}</a>
            		</li>
                </#list>
            </ul><!--/friends_list-->
            </#if>    
             <#--<a href="javascript:void(0)" onclick="selectAll()"> 全选</a>
             <a href="javascript:void(0)" onclick="clear()"> 清除</a>-->
             <#if (pb.list?size>0)>
                <form action="${basePath}/invitefriends-${group.groupId}.html" method="post" name="group_page_form" id="group_page_form">
                 <input id="page" name="pageNo" type="hidden" value="${pb.pageNo }"/>
                 <input name="keyvalue" id= "page_keyword" type="hidden" value="${(customerName)!''}"/>
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
            
          <form id="inviteForm" method="post"> 
            <div class="selected_friends mt30">
            	<h5>你已经选择邀请的好友</h5>
                <div class="selected_wp mt20">
                	<div class="selected_box">
                    	<ul class="selected_list clearfix">
                        	
                        </ul><!--/selected_list-->
                    </div><!--/selected_box-->
                    <a class="s_prev no_li" href="javascript:;"></a>
                    <a class="s_next no_li" href="javascript:;"></a>
                </div><!--/selected_wp-->
            </div><!--/selected_friends-->
             <input  name="groupId" type="hidden" value="${group.groupId }"/>
            <div class="say_wp mt50">
            	<p>对上面这些邀请的好友说些什么</p>
                <div class="rowElem clearfix mt15"><textarea id="tmsg" style="width:870px; height:110px;" name="content" placeholder="我在小组${group.groupName}里，快来看看吧……"></textarea></div>
                <div class="clearfix mt10">
                	<span class="fl b_grey">不超过140个汉字</span>
                	<a onclick="sendMsg()"><input class="send_invite fr" type="button" value="发送邀请"/></a>
                </div>
            </div>
          </form>
        </div><!--/left_cont-->
        
        <div class="right_cont fr">
        	<div class="team_box clearfix">
            	<a class="fl" href="${basePath}/groupdetail/${group.groupId}.html">
            	<#if (group.groupHead)?? && (group.groupHead)!="">
            	  <img width="50px" height="50px" alt="${group.groupName}" src="${group.groupHead}" /></a>
            	<#else>
            	  <img width="50px" height="50px" alt="${group.groupName}" src="${basePath}/images/default_head3.jpg" /></a>
            	</#if>
                <div class="tb_info fl pt5 ml10">
                	<a href="${basePath}/groupdetail/${group.groupId}.html">${group.groupName}</a>
                    <p>本小组共有${group.groupmember}个成员</p>
                </div><!--/tb_info-->
            </div><!--/team_box-->
            
            <div class="latest_add mt20">
            	<h3>最新加入<a href="${basePath}/groupmemberlist/${group.groupId}.html">（所有${group.groupmember}个成员）</a></h3>
                <ul class="la_list clearfix mt20" style="width:310px;">
                    <#if groupmember??>
                      <#list groupmember as member>
	                	<li><a href="${basePath}/customerhomepage/${member.customerId}.html">
	                	<#if (member.infoHeadimg)?? && (member.infoHeadimg)!="">
	                		<img alt="${member.customerName}" src="${member.infoHeadimg}" width="50px" height="50px"/>
	                	<#else>
	                		<img alt="${member.customerName}" src="${basePath}/images/default_head3.jpg" width="50px" height="50px"/>
						</#if>
	                	 <#if (member.customerName)?length gt 6>
		                      ${(member.customerName)?substring(0,6)}
		                  <#else>${member.customerName}</#if></a></li>
                      </#list>
                    </#if>
                </ul><!--/la_list-->
            </div><!--/latest_add-->
        </div><!--/right_cont-->
        
       <#-- <div class="user_detail clearfix">
            <a class="uhead fl" href="javascript:;"><img alt="" src="../images/images_09.jpg" /></a>
            <div class="uinfo fl ml10 pt5">
                <a class="uname" href="javascript:;">爱果果</a>
                <p class="mt10">江苏 南京</p>
                <p class="mt10"><span>座驾：凯美瑞</span><span class="ml10">驾龄：5年</span></p>
            </div>
            <a class="attention fr" href="javascript:;">加关注</a>
        </div> -->
        
    </div>

  <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
<div class="mask1"></div>
     <div class="dialog1 dia1" style="width: 300px; height: 120px;">
    	<a class="close" href="javascript:cls1();"></a>
        <h4>系统提示</h4>
        <p class="tc f14 mt20" style="padding:20px;" id ="message"></p>   
    </div><!--/dialog-->
	
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/social/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.jqtransform.js"></script>	
<script type="text/javascript" src="${basePath}/js/social/group_invite.js"></script>	

<script type="text/javascript">
		$(function() {
			$(".rowElem").jqTransform();
		});
		
function selectAll(){
	var n = $(".friends_list").find("li");
	var n1 = $(".friends_list li").length;
	$(".selected_list").width(n1*145+n1*20);
	$(".s_next")[n1<=4?'addClass':'removeClass']("no_li");
	for(var i=0;i<n.length;i++){
		var cur = $(n[i]);
		var l_n = cur.index();
		var isAdd=!cur.hasClass("choose");
		if(cur.hasClass("choose")) {
		} else {
			var c = $(n[i]).addClass("choose").append("<div class='choose_bg'></div>");
			c.clone().appendTo(".selected_list").attr('id','li_'+i);		
			$(".selected_list li").click(function(){
				var s_cur = $(this);
				var s_id = s_cur.attr("id");
				var s_b = parseInt(s_id.substr(3));
				var f_li=$(".friends_list li:eq("+s_b+")");
				(!f_li.hasClass("choose"))&&(f_li.addClass("choose"));	
				f_li.click();
			});
	 }}
 }
function clear(){
	var n = $(".friends_list").find("li");
	$(".selected_list").width("660px");
	$(".s_next, .s_prev").addClass("no_li");
	for(var i=0;i<n.length;i++){
		var cur = $(n[i]);
		var l_n = cur.index();
		var isAdd=!cur.hasClass("choose");
		if(cur.hasClass("choose")) {
			cur.removeClass("choose");
			cur.find(".choose_bg").remove();
			$(".selected_list").find("#li_"+i).remove();			
		}
	}
}
	
	 $(function(){
			//选中首页社区
			 $(".sort_list li").removeClass("cur");
			 $(".sort_list li:eq(1)").addClass("cur");
         });
</script>
</body>
</html>


