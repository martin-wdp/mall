<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl"> 
<title>话题列表-${topmap.systembase.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/> 
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>

<style type="text/css">
 .topic_show {height:88px; line-height:88px; background:url(${basePath}/images/topic_bg.gif) repeat-x; border:1px solid #dfdfdf; font-family:"微软雅黑"; font-size:14px;}
.topicNum, .replyNum {display:inline-block; zoom:1; *display:inline; padding-left:45px; margin:0 20px;}
.topicNum {background:url(${basePath}/images/ntp_01.gif) no-repeat left center;}
.replyNum {background:url(${basePath}/images/ntp_02.gif) no-repeat left center;}
.topicNum em, .replyNum em {font-size:18px; color:#336699;}
.tp_hd .topic_name img {margin-left:5px;}
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
    <div class="topic_show tc">
    	<span class="topicNum">话题:&nbsp;&nbsp;<em>${(topicCount)!'0'}</em></span>
        <span class="replyNum">回复:&nbsp;&nbsp;<em>${topicReplyCount!'0'}</em></span>
    </div>        
        
    <div class="featured_topics mt30">
            	<div class="topic_title clearfix">
                	<h3 class="tit fl">话题列表</h3>
                	<form action="topiclist.html?groupTypeId=${groupTypeId!''}" id="topic_search_form" name ="topic_search_form" method="get">
                    <div class="topic_search clearfix fr">
            	       <div class="rowElem clearfix fl">
                	       <select name="flag">
                	       <option value="1" <#if flag??&&flag=='1'>selected="selected" </#if>>标题</option>
                	       <option value="2" <#if flag??&&flag=='2'>selected="selected" </#if>>内容</option>
                	       <option value="3" <#if flag??&&flag=='3'>selected="selected" </#if>>作者</option>
                	       </select>
                       </div><!--/rowElem-->
                       <div class="rowElem clearfix fl ml10 mr10">
                       <input type="text" name="keyword" style="width:300px;" value="${keyvalue!''}" /></div>
                       <div class="rowElem clearfix fl"><input type="submit" value="搜索话题" /></div>
                    </div><!--/topic_search-->
                   </form>
                </div><!--/topic_title-->
                <div class="topics_wp mt20">
                    
                  <#if pb.list??>
                   <#list pb.list as topic>
                    <div class="topic_box clearfix">
                    	<div class="head_box fl"><a class="u_head" href="customerhomepage/${topic.customerId}.html" target="_blank">
                    	<#if (topic.customerHead)?? && (topic.customerHead)!="">
    	                	<img width="50px" height="50px" alt="${topic.customerName}" src="${baseUrl}${topic.customerHead}" />
                    	  <#else>
	                    	<img width="50px" height="50px" alt="${topic.customerName}" src="${basePath}/images/default_head3.jpg" />
                    	</#if>
                    	<#if topic.customerName?length gt 8>
                    	  ${(topic.customerName)?substring(0,8)}
                    	<#else>${topic.customerName}
                    	</#if>
                    	</a></div>
                        <div class="topic_cont fr">
                        	<span class="t_arrow"></span>
                            <div class="tp_hd clearfix">
                            	<a class="topic_name fl" href="topicdetail/${topic.groupId}-${topic.topicId}.html" target="_blank">${(topic.topicTitle)!''}
                            	<#if topic.topicFever =='1'>
                            	  <img alt="" src="${basePath}/images/hot_ico.gif" />
                            	</#if>
                            	<#if topic.topicEssence =='1'>
                            	  <img alt="" src="${basePath}/images/fine_ico.gif" />
                            	</#if></a>
                                <div class="tp_info clearfix fr">
                                	<a class="fl" href="topicdetail/${topic.groupId}-${topic.topicId}.html" target="_blank">热度（${(topic.topicHot)!'0'}）</a>
                                    <a class="fl" href="topicdetail/${topic.groupId}-${topic.topicId}.html" target="_blank">评论（${(topic.replyCount)!'0'}）</a>
                                    <!-- Baidu Button BEGIN -->
                                    <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare fl ml10" 
                                    data="{'pic':'
                                    <#if (topic.topicImgs)??>
                                    <#list  (topic.topicImgs) as topicImg>
                                    <#if topicImg.topicType=='0'>${topicImg.topicImgUrl} </#if>
                                    </#list></#if>',
									'url':'${basePath}/topicdetail/${topic.groupId}-${topic.topicId }.html','text':'
									《${topic.topicTitle }》,qpmall热帖大推荐。'}"> 
  
									    <span class="bds_more">更多</span>
									</div>
                                    <script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=3618405" ></script>
                                    <script type="text/javascript" id="bdshell_js"></script>
                                    <script type="text/javascript">
                                    	document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
                                    </script>
                                    <!-- Baidu Button END -->
                                </div><!--/tp_info-->
                          </div><!--/tp_hd-->
                            <div class="tp_cont mt15 clearfix">
                            <#if (topic.topicImgs)??>
                              <#list topic.topicImgs as topicImg>
                                <#if topicImg.topicType=='0' && topicImg_index lt 2>
                                <a class="fl" href="topicdetail.html/${topic.groupId}-${topic.topicId }" target="_blank"><img width="160" height="120" src="${(topicImg.topicImgUrl)!'' }" /></a>
                                </#if>
                              </#list>
                            </#if>
                            <#assign content=(topic.topicContent)?replace("<.*?>","","r")>
                            <#assign str=(content)?trim>
                             <#-- /如果字符长度>100,并且有1图片，那就截取140字符(大约4行) -->
                            <#if (topic.topicImgs)??&& (topic.topicImgs?size == 1)>
                             <#if str?length gt 250>
                               ${str?substring(0,250)}...
                               <#else>${str}
                              </#if>
                            </#if>
                             <#-- /如果字符长度>100,并且有2图片，那就截取100字符(大约4行) -->
                            <#if (topic.topicImgs)??&& (topic.topicImgs?size gt 1)>
                             <#if str?length gt 200>
                               ${str?substring(0,200)}...
                               <#else>${str}
                              </#if>
                            </#if>
                             <#-- /如果字符长度>100没有图片，那就截取220(大约4行) -->
                            <#if !(topic.topicImgs)?? ||(topic.topicImgs?size == 0) >
                              <#if str?length gt 270>
                               ${str?substring(0,270)}...
                               <#else>${str}
                              </#if>
                            </#if>
                         
                            </div><!--/tp_cont-->
                            <div class="tp_bt mt15">
                            	来自：<a href="groupdetail/${topic.groupId}.html" target="_blank">${(topic.groupName)!''}</a>小组
                                <span class="date ml30">${(topic.topicCreateTime)?string('yyyy-MM-dd HH:mm:ss')}</span>
                            </div><!--/tp_bt-->
                      </div><!--/topic_cont-->
                  </div><!--/topic_box-->
                   </#list>
                  </#if>
                  
               <#if (pb.list?size>0)>
                <form action="topiclist.html" method="post" name="group_page_form" id="group_page_form">
                 <input id="page" name="pageNo" type="hidden" value="${pb.pageNo }"/>
                 <input id="flag" name="flag" type="hidden" value="${flag!'' }"/>
                 <input id="keyword" name="keyword" type="hidden" value="${keyvalue!'' }"/>
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
               </div><!--/topics_wp-->
      </div><!--/featured_topics-->
    </div><!--/left_cont-->
        
        <div class="right_cont fr">
        	<div class="hot_team">
            	<h3 class="m_it">热门小组</h3>
                <ul class="ht_list clearfix mt20" style="width:305px;">
                	<#if hotlist??>
                	  <#list hotlist as hot>
                		<li><a href="groupdetail/${hot.groupId}.html">
                		<#if (hot.groupHead)?? && (hot.groupHead)!="">
                		   <img height="50px" width="50px" alt="${hot.groupName}" src="${baseUrl}${hot.groupHead}" title="${hot.groupName}"/>
                		<#else>
                		   <img height="50px" width="50px" alt="${hot.groupName}" src="${basePath}/images/default_head3.jpg" title="${hot.groupName}"/>
                		</#if>
                		${hot.groupName}</a></li>
                	  </#list>
                	</#if>
                </ul><!--/ht_list-->
            </div><!--/hot_team-->
            
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
        </div><!--/right_cont fr-->
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
<script type="text/javascript" src="${basePath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath}/js/topic/topic_list.js"></script>
<script type="text/javascript">
	$(function() {
		$(".rowElem").jqTransform();
	});


  $(function(){
		$(".bgj_sort ul").each(function(){
			var cur = $(this);
			cur.find("a").click(function(){
				cur.find(".hover").removeClass("hover");
				$(this).addClass("hover");
			});
		});
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
</script>
</body>
</html>


