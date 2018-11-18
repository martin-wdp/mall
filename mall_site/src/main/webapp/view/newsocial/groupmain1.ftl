<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl" />
<title>${topmap.systembase.bsetName}</title>
<link type="text/css" rel="stylesheet" href="${basePath}/css/base.min.css"/>
<link type="text/css" rel="stylesheet" href="${basePath}/css/style.css"/>
<script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=2391630" ></script>
<script type="text/javascript" id="bdshell_js"></script>
<style type="text/css">
.group_name:hover{ text-decoration:none;}
.other_group_list li a:hover{ text-decoration:none;}
.all_group:hover{ text-decoration:none;}
</style>
</head>

<body>
	<#include "../common/header.ftl" />
	<input type="hidden" id="customerid" value="${cusId!''}"/>
	<input type="hidden" id="basePath" value="${basePath!''}"/>
	<input type="hidden" id="baseUrl" value="${baseUrl!''}"/>
	<div class="wrapper clearfix">
    	<div class="left_cont fl">
        	<div class="groupBox">
            	<p><em>社区：</em>共<span>${groupcount!'0'}</span>个小组，<span>${groupmember!'0'}</span>个成员。欢迎加入我们。</p>
                <form id="group_search_form" action="groupsearchlist.html" method="get" target= "_blank">
                <div class="group_search mt10">
                	<em>小组搜索：</em>
                    <input class="g_search_text" type="text" name="groupName" />
                    <input class="g_search_btn" type="submit" value="搜索" />
                </div><!--/group_search-->
                </form>
            </div><!--/groupBox-->  
            <div class="hot_group mt20">
            	<h3 class="tit">活跃小组</h3>
            	 <div class="group_wp mt20 clearfix">
                   <#if activelist??>
                    <#list activelist as active>
		                	<div class="group_box clearfix fl">
		                    	<a class="group_img fl" href="groupdetail/${active.groupId}.html">
		                    	<#if (active.groupHead)??>
 			                    	<img  height="120px" width="120px" alt="${active.groupName}" src="${baseUrl}${active.groupHead}" title="${active.groupName}"/></a>
		                    	<#else>
 			                    	<img  height="120px" width="120px" alt="${active.groupName}" src="${basePath}/images/default_head2.jpg" title="${active.groupName}"/></a>
		                    	</#if>
		                        <div class="group_info fl ml10">
		                        	<a class="group_name" href="groupdetail/${active.groupId}.html">${active.groupName}</a>
		                            <div class="g_info mt10">
		                            	<a class="m_num" href="groupdetail/${active.groupId}.html">${(active.groupmember)!'0'}</a>
		                                <a class="c_num" href="groupdetail/${active.groupId}.html">${(active.topicCount)!"0"}</a>
		                            </div><!--/g_info-->
		                            <p>
		                           <#if (active.groupRemark)?length gt 50>
		                                ${(active.groupRemark)?substring(0,50)}
		                             <#else>${active.groupRemark}
		                           </#if>
		                            </p>
		                        </div><!--/group_info-->
		                    </div><!--/group_box-->
		            </#list>
                   </#if>  
                </div><!--/group_wp-->
      
            </div><!--/hot_group-->
            
           <div class="featured_topics mt20">
                <h3 class="tit">精选话题</h3>
                <div class="topics_wp mt20" id="items">  
                  <#if (topicEssence.list)??>
                    <#list topicEssence.list as topic>
	                    <div class="topic_box clearfix ">
	                        <div class="fl tc" style="width:50px;">
	                        <a class="u_head" href="customerhomepage/${topic.customerId}.html" target="_blank">
	                        <#if (topic.customerHead)?? && (topic.customerHead)!="">
		                        <img title="${topic.customerName}" width="50" height="50" alt="${topic.customerName}" src="${baseUrl}${topic.customerHead}"/>
	                         <#else>
		                        <img title="${topic.customerName}" width="50" height="50" alt="${topic.customerName}" src="${basePath}/images/default_head3.jpg"/>
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
	                                </a>
	                                <div class="tp_info clearfix fr">
	                                	<a class="fl" href="topicdetail/${topic.groupId}-${topic.topicId}.html" target="_blank">热度（${(topic.topicHot)!'0'}）</a>
                                    	<a class="fl" href="topicdetail/${topic.groupId}-${topic.topicId}.html" target="_blank">评论（${(topic.replyCount)!'0'}）</a>
	                                    <!-- Baidu Button BEGIN -->
	                                    <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare fl ml10"  data="{'url':'http://localhost:8080/bbw/topicdetail/${topic.groupId}-${topic.topicId }.html',
	                                    																				'text':'《${topic.topicTitle }》,摆摆网热帖大推荐。 (分享来自@摆摆网)'}">
	                                        			
	                                        <span class="bds_more">分享</span>
	                                    </div>
										<script type="text/javascript">
										document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
										</script>
	                                    <!-- Baidu Button END -->  
	                                </div><!--/tp_info-->
	                            </div><!--/tp_hd-->
	                            <div class="tp_cont mt15 clearfix">
	                              <div class="tp_imgs mr10 fl clearfix">
	                                <#if (topic.topicImgs)??>
		                              <#list topic.topicImgs as topicImg>
		                                <#if topicImg.topicType=='0' && topicImg_index lt 2>
		                                <a class="fl" href="topicdetail${topic.groupId}-${topic.topicId }.html" target="_blank"><img class="lodingimg" width="160" height="120" src="${(topicImg.topicImgUrl)!'' }" /></a>
		                                </#if>
		                              </#list>
		                            </#if>
		                            </div>
		                            <#assign content=(topic.topicContent)?replace("<.*?>","","r")>
		                            <#assign str=(content)?trim>
		                             <#-- /如果字符长度>100,并且有1图片，那就截取140字符(大约4行) -->
		                            <#if (topic.topicImgs)??&& (topic.topicImgs?size == 1)>
		                             <#if str?length gt 250>
		                               <p>${str?substring(0,250)}...</p>
		                               <#else><p>${str}</p>
		                              </#if>
		                            </#if>
		                             <#-- /如果字符长度>100,并且有2图片，那就截取100字符(大约4行) -->
		                            <#if (topic.topicImgs)??&& (topic.topicImgs?size gt 1)>
		                             <#if str?length gt 200>
		                                 <p>${str?substring(0,200)}...</p>
		                               <#else><p>${str}</p>
		                              </#if>
		                            </#if>
		                             <#-- /如果字符长度>100没有图片，那就截取220(大约4行) -->
		                            <#if !(topic.topicImgs)?? ||(topic.topicImgs?size == 0) >
		                              <#if str?length gt 270>
		                                 <p>${str?substring(0,270)}...</p>
		                               <#else><p>${str}</p>
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
                </div><!--/topics_wp-->
            </div>
            <p class="tc f16" id="showmore" onclick="show()">加载更多话题...</p>
              <div class="pages tc mt30"></div>
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
                    	<li><a href="groupList.html">全部</a></li>
                    	<#if grouptypelist??>
	                        <#list grouptypelist as groupType>
	                        	<li><a href="groupList.html?groupTypeId=${groupType.groupTypeId}">${groupType.groupTypeName}</a></li>
	                        </#list>
                        </#if>
                    </ul><!--/g_sort_list-->
                </div><!--/g_sort-->
            </div><!--/group_sort-->
            
          
            	
    	<#if advlist??>
    	    <div id="gg_slides" class="mt20">
    	    <div class="slides_container">
    		<#list advlist as adv>
	       	   <div class="slide"><a href="${adv.advUrl}"><img src="${baseUrl}${adv.advImg}" width="290" height="200" alt="${adv.advName}"/></a></div>
               </div><!--/slides_container-->
               </div><!--/gg_slides-->
		     </#list>
		  <#else><div class="slide"><a href="javascript:void(0);"><img alt="" src="${basePath}/images/images_17.jpg" width="290" height="200" /></a></div>
       </#if>
            
     
        </div><!--/right_cont-->
    </div><!--/wrapper-->
    
     <div class="mask"></div>
     <div class="dialog dia1" style="width:450px; height:170px;">
    	<a class="close" href="javascript:cls();"></a>
        <h4>系统提示</h4>
        <p class="tc f14 mt20" style="padding:20px;" id ="message"></p>   
          
        <div class="tc mt10 d_lk"><a class="cancel_btn" href="javascript:cls();">关闭</a></div>
    </div><!--/dialog-->
    
   <#include "../common/footer.ftl" />
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/slides.min.jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/dataformat.js"></script>

<script type="text/javascript" src="${basePath}/js/social/group_main.js"></script>
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
				
		
	  </script>
</body>
</html>