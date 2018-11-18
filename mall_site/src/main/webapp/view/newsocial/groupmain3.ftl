<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl" />
<title>社区-${topmap.systembase.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" /> 
<style type="text/css">
.slides_tt {width:880px; height:300px; position:relative;z-index:1;}
.slides_tt .slide_item{display:block;width:100%;height:400px;}
.slides_tt .slidesjs-pagination{display:block;position:absolute;bottom:10px;z-index:99999; width:100%; text-align:center;}
.slides_tt .slidesjs-pagination li{display:inline-block; margin-right:10px; vertical-align:middle; zoom:1; *display:inline;}
.slides_tt .slidesjs-pagination li a{display:block;width:10px;height:10px; background:url(${basePath}/images/quan.png) no-repeat; text-indent:-9999px;}
.slides_tt .slidesjs-pagination li a.active{background:url(${basePath}/images/quan_hover.png) no-repeat; width:14px; height:14px;}
.slides_tt .slidesjs-previous{ position:absolute; background:url(${basePath}/images/lb_left.png) no-repeat; width:50px; height:60px; left:10px; top:170px; text-indent:-9999px; z-index:999; display:none;}
.slides_tt .slidesjs-next{ position:absolute; background:url(${basePath}/images/lb_rig.png) no-repeat; width:50px; height:60px; right:10px; top:170px; text-indent:-9999px; z-index:999; display:none;}
.jd-title{ font-size: 18px; font-family: "微软雅黑"; color:#080808; border-bottom: 2px solid #999999; line-height: 30px; font-weight: bold;}
.jd-title a{ line-height:30px; color:#005EA7; font-size:12px;}
.jd-hy{background:url(${basePath}/images/jd-yin.png) repeat; padding: 10px; position: absolute; left: 0px; bottom: 0px;}
.up-tu li{ width: 205px; height: 205px; overflow: hidden; position: relative; float: left; margin-right: 20px;}
.jd-hy a span{ display:block;  font-size: 18px; font-family: "微软雅黑"; color: #fff;} 
.jd-hy a:hover{text-decoration:none;}
.jd-hy p{ font-size: 12px; color: #fff; line-height: 15px; padding-top: 5px;}
.jd-list{width: 420px; float: left; margin-right: 40px; margin-top: 20px; height:120px; overflow: hidden;} 
.jd-huo li{ width: 205px; float: left; margin-right: 20px; margin-top: 20px;}
.qt-group dl{float: left; margin-right: 10px;}
.qt-group dl dt{ width: 120px; height: 120px; float: left; position: relative;}
.qt-group dl dt p{ height: 20px; background:url(${basePath}/images/jd-yin.png) repeat; position: absolute; width: 120px; left: 0; bottom: 0; color: #fff; line-height: 20px; text-align: center;}
.pbl-list{ margin-top:10px;width: 196px; border: 1px solid #f0eeee; padding: 9px; font-family: "微软雅黑"; color: #333;}
.pbl-top{ font-size: 14px;}
.pbl-detail a{ color: #ff0000; display:block; margin-top:5px;}
.bt_title a:hover{text-decoration:none;} 
.slidesjs-pagination li {float:none;  margin-left:10px;}
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
    <input type="hidden" id="customerid" value="${cusId!''}"/>
	<input type="hidden" id="basePath" value="${basePath!''}"/>
<div class="wrapper clearfix">
    	<div class="left_cont fl">
            <div class="slides_tt ">
              <#if topadvlist??>
                <#list topadvlist as adv>
                  <a href="${adv.advUrl}" target="_blank"><img src="${adv.advImg}" width="880" height="300" alt="${adv.advName}"/></a>
               </#list>
                 <#else>
                  <a href="#"><img src="${basePath}/images/banner2.jpg" width="880" height="400" alt=""/></a>
               </#if>
            </div>

        	<div class="groupBox mt20">
            	<p><em>社区：</em>共<span>${groupcount!'0'}</span>个小组，<span>${groupmember!'0'}</span>个成员。欢迎加入我们。</p>
                <form id="group_search_form" action="" method="get" target="_blank">
                <div class="group_search mt10">
                	<select style="height:27px; border:1px solid #ddd;" id="flagSearch" class="selector">
                      <option value="1">小组搜索</option>
                      <option value="2">话题搜索</option>
                    </select>
                    <input class="g_search_text" type="text" name="groupName" id="searchValue"/>
                    <input type="hidden"  id="searchFlag"/>
                    <input class="g_search_btn" type="button" value="搜索" onclick="search()" />
                </div><!--/group_search-->
                </form>
            </div><!--/groupBox--> 
            
            <div class="jd-intro mt10">
                <div class="jd-title">
                    特别推荐
                </div>
                <#if recommendlist?size gt 0 >
                 <ul class="up-tu mt20 clearfix" style="width:900px;">
                    <#list recommendlist as group>
                    <li>
                        <a href="groupdetail/${group.groupId}.html" target="_blank">
                         <#if (group.groupHead)??>
 			                 <img  width="205px" height="205px" alt="${group.groupName}" src="${group.groupHead}" title="${group.groupName}"/></a>
		                <#else>
 			                 <img  width="205px" height="205px" alt="${group.groupName}" src="${basePath}/images/default_head2.jpg" title="${group.groupName}"/></a>
		                </#if>
                        <div class="jd-hy">
                            <a  href="groupdetail/${group.groupId}.html" target="_blank"> 
                            <span>
                             <#if (group.groupName)?length gt 8>
		                           ${(group.groupName)?substring(0,8)}
		                         <#else>${group.groupName}
		                      </#if>
                            </span>
                            </a>
                             <a  href="groupdetail/${group.groupId}.html" target="_blank"> 
                            <p>
                             <#if (group.groupRemark)?length gt 50>
		                          ${(group.groupRemark)?substring(0,50)}
		                       <#else>${group.groupRemark}
		                    </#if>
                            </p>
                            </a>
                        </div>
                    </li>
                    </#list>
                </ul>
				</#if>
            </div><!--jd-intro-->    
            
            <div class="jd-intro mt10">
                <div class="jd-title">热门小组</div>
                <div class="jd-group clearfix" style="width:950px;">
                    <#if hotlist?size gt 0>
	                     <#list hotlist as group>
	                       <div class="jd-list">
	                         <a href="groupdetail/${group.groupId}.html" class="fl" target="_blank">
	                         <#if (group.groupHead)??>
	 			                 <img  width="120px" height="120px" alt="${group.groupName}" src="${group.groupHead}" title="${group.groupName}"/></a>
			                <#else>
	 			                 <img  width="120px" height="120px" alt="${group.groupName}" src="${basePath}/images/default_head2.jpg" title="${group.groupName}"/></a>
			                </#if>
	                        </a>
	                        <div class="group_info ml10 fl" style="width:290px;">
	                            <a class="group_name" href="groupdetail/${group.groupId}.html" target="_blank"> 
	                               <#if (group.groupName)?length gt 10>
			                           ${(group.groupName)?substring(0,10)}
			                         <#else>${group.groupName}
			                      </#if></a>
	                            <div class="g_info mt5">
	                                <a class="m_num" href="groupdetail/${group.groupId}.html" target="_blank">${(group.groupmember)!'0'}</a>
	                                <a class="c_num" href="groupdetail/${group.groupId}.html" target="_blank">${(group.topicCount)!"0"}</a>
	                            </div>
	                            <a  href="groupdetail/${group.groupId}.html" target="_blank"> 
	                            <p style="margin-top:5px;">
	                             <#if (group.groupRemark)?length gt 60>
		                          ${(group.groupRemark)?substring(0,60)}...
		                         <#else>${group.groupRemark}
		                         </#if>
								</p>
								</a>
	                        </div>
	                    </div><!--jd-list-->
                     </#list>
                    </#if>
                </div>
            </div><!--jd-intro-->
            <div class="jd-intro mt10">
                <div class="jd-title">活跃小组</div>
                <#if activelist?size gt 0>
                <ul class="jd-huo clearfix" style="width:900px;">
                   <#list activelist as active>
                     <li>
                        <a href="groupdetail/${active.groupId}.html" target="_blank">
                        <#if (active.groupHead)??>
 			                 <img  height="205px" width="205px" alt="${active.groupName}" src="${active.groupHead}" title="${active.groupName}"/></a>
		                   <#else>
 			                  <img  height="205px" width="205px" alt="${active.groupName}" src="${basePath}/images/default_head2.jpg" title="${active.groupName}"/></a>
		                </#if>
                        <a class="group_name mt10" href="groupdetail/${active.groupId}.html" style="display:block;" target="_blank">  
                          <#if (active.groupName)?length gt 10>
		                      ${(active.groupName)?substring(0,10)}
		                  <#else>${active.groupName}
		                  </#if></a>
                        <div class="g_info mt5">
                           <a class="m_num" href="groupdetail/${active.groupId}.html" target="_blank">${(active.groupmember)!'0'}</a>
		                   <a class="c_num" href="groupdetail/${active.groupId}.html" target="_blank">${(active.topicCount)!"0"}</a>
                        </div>
                     </li>
                   </#list>
                </ul>
               </#if> 
            </div><!--jd-intro-->
          
         
            <div class="jd-intro mt10">
                <div class="jd-title">热门话题</div>
                <div class="pbl-con infinite_scroll" id="items">
                    <#if  (topicEssence.list)??>
                      <#list topicEssence.list as topic>
                        <div class="pbl-list">
	                        <#if (topic.topicImgs)??>
	                          <#list topic.topicImgs as topicImg>
	                            <#if topicImg.topicType=='0' && topicImg_index lt 1>
	                            <a  href="topicdetail/${topic.groupId}-${topic.topicId}.html" target="_blank"><img class="lodingimg" width="196px"  src="${(topicImg.topicImgUrl)!'' }" /></a>
	                            </#if>
	                          </#list>
	                         </#if>
	                         <div class="pbl-detail clearfix">
	                             <p class="pbl-top mt10"> 
	                              <a class="u_head" href="customerhomepage/${topic.customerId}.html" target="_blank" style="color:#333">
	                             <#if (topic.customerHead)?? && (topic.customerHead)!="">
		                      		  <img title="${topic.customerName}" width="20" height="20" alt="${topic.customerName}" src="${topic.customerHead}"/>
	                             <#else>
		                        	  <img title="${topic.customerName}" width="20" height="20" alt="${topic.customerName}" src="${basePath}/images/default_head3.jpg"/>
	                        	</#if>
	                            <#if topic.customerName?length gt 8>
                    	          ${(topic.customerName)?substring(0,8)}
                    	         <#else>${topic.customerName}
                    	         </#if>
                    	         </a></p>
                    	          <p class="bt_title" style="text-align:center; font-weight:bold; font-size:14px;"><a style="color:#333!important;" href="topicdetail/${topic.groupId}-${topic.topicId}.html" title="${topic.topicTitle}">
	                    	         <#if topic.topicTitle?length gt 7>
		                    	          ${(topic.topicTitle)?substring(0,7)}...
		                    	         <#else>${topic.topicTitle}
	                    	            </#if>
	                    	         </a></p>
                    	        <#assign content=(topic.topicContent)?replace("<.*?>","","r")>
		                        <#assign str=(content)?trim>
	                            <p class="ml30" style="line-height:20px;">
	                              <a href="topicdetail/${topic.groupId}-${topic.topicId}.html" target="_blank" style="color:#333">
	                                <#if str?length gt 100>
	                                ${str?substring(0,100)}...
		                              <#else>
		                              ${str}
	                                </#if>
	                            </a> 
	                           </p>
	                            <a class="fr" href="topicdetail/${topic.groupId}-${topic.topicId}.html" target="_blank">评论（${(topic.replyCount)!'0'}）</a>
                            </div>
                        </div>
                      </#list>
                    </#if>
                </div><!--pbl-con-->

            </div><!--jd-intro-->
            <p class="tc f16" id="showmore" onclick="show()"></p>
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
                    	<li><a href="grouplist.html" target="_blank">全部</a></li>
                    	<#if grouptypelist??>
	                        <#list grouptypelist as groupType>
	                        	<li><a href="grouplist.html?groupTypeId=${groupType.groupTypeId}" target="_blank">${groupType.groupTypeName}</a></li>
	                        </#list>
                        </#if>
                    </ul><!--/g_sort_list-->
                </div><!--/g_sort-->
            </div><!--/group_sort-->
            
             <#if advlist??>
    		   <#list advlist as adv>
    	        <div id="gg_slides" class="mt20">
    	          <div class="slides_container">
	       	        <div class="slide">
	       	        <a href="${adv.advUrl}" target="_blank"><img src="${adv.advImg}" width="290" height="200" alt="${adv.advName}"/></a>
	       	        </div>
                  </div><!--/slides_container-->
                </div><!--/gg_slides-->
		      </#list>
		    <#else>
		       <div id="gg_slides" class="mt20">
    	          <div class="slides_container">
		            <div class="slide"><a href="javascript:void(0);">
		   				 <img alt="" src="${basePath}/images/images_17.jpg" width="290" height="200" /></a></div>
		    		</div><!--/slides_container-->
               </div><!--/gg_slides-->
           </#if>
        </div><!--/right_cont-->
    </div>

  <div class="mask"></div>
     <div class="dialog dia1" style="width:450px; height:170px;">
    	<a class="close" href="javascript:cls1();"></a>
        <h4>系统提示</h4>
        <p class="tc f14 mt20" style="padding:20px;" id ="message"></p>   
          
        <div class="tc mt10 d_lk"><a class="cancel_btn" href="javascript:cls1();">关闭</a></div>
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
<script type="text/javascript" src="${basePath}/js/jquery.slides.min.js"></script>
<script type="text/javascript" src="${basePath}/js/social/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.masonry.js"></script>
<script type="text/javascript" src="${basePath}/js/dataformat.js"></script>
<script type="text/javascript" src="${basePath}/js/social/group_main1.js"></script>
<script type="text/javascript" src="${basePath}/js/social/group_comm.js"></script>
<!--<script type="text/javascript" src="..js/jquery.infinitescroll.js"></script>-->
<script type="text/javascript"> 


    $(function(){
	    $('.slides_tt').slidesjs({
	        width: 880,
	        height: 400,
	        play: {
	          active: false,
	          effect: "fade",
	          auto: true,
	          interval: 4000
	        },
	        pagination: {
	            effect: "fade"
	        }
	    });
	    	masonryTopic();
   })


	$(".jd-hy").hide();
    $(".up-tu li").hover(function(){
        $(this).find(".jd-hy").slideDown();
    },

     function(){
    	 $(this).find(".jd-hy").slideUp();
     });
    
	$(function(){
	    $(".qt-group dl").find("dd").hide();
	    $(".qt-group dl dd:first").show();
	    $(".qt-group dl").hover(function(){
	        $(this).find("dd").show();
	        $(this).siblings("dl").find("dd").hide();
	    });
	});

	function masonryTopic(){
		var $container = $('.infinite_scroll');  
		$container.imagesLoaded( function(){  
		  $container.masonry({  
		    itemSelector: '.pbl-list',
		    columnWidth:211,
		     gutterWidth:10  
			  });  
		});  
	}
	
     
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
