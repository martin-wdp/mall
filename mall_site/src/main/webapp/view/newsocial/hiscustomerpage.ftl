<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl"> 
<title>个人主页-${topmap.systembase.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>
    <style type="text/css">
	    .topic_cont {
			border: 1px solid #e9e9e9;
			width: 615px;
			padding: 20px;
			position: relative;
		}
		.d_lk .ok_btn, .d_lk .cancel_btn {display:inline-block; zoom:1; *display:inline; background:url(../images/g_search_btn.gif) no-repeat; width:62px; height:24px; color:#fff; text-align:center; line-height:24px; font-weight:700;}
		.create_albums dt {float:left; width:70px; line-height:30px;}
		.create_albums dd {margin-bottom:15px; margin-left:65px; line-height:30px;}
		.ca_text {width:228px; height:28px; border:1px solid #e1e1e1; padding:0 5px;line-height:28px;} 
		.ca_txa {width:228px; height:58px; border:1px solid #e1e1e1; padding:5px;}
		.create_albums label {display:block; line-height:20px;}
		.comment_list .user_detail, .mood_list .user_detail {top:17px; left:0;}
		.create_albums .LV_validation_message {position:static;}
		
    </style>
</head>
<body>
<input type="hidden" id="customerId" value="${cusId!''}">
<input type="hidden" id="basePath" value="${basePath}">
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
    <div class="container clearfix pt30">
        <div class="left_wp fl">
            <div class="personal_wp clearfix">
                <div class="myinfo fl">
                    <div class="myimg">
                        <a href="javascript:void(0);">
                    	<#if (map.cc.customerImg)?? && (map.cc.customerImg)!="">
                    	<img alt="${(map.cc.customerNickname)!''}" src="${map.cc.customerImg}" width="180" height="180"/>
                    	<#else>
                    	<img alt="${(map.cc.customerNickname)!''}" src="${basePath}/images/default_head3.jpg" width="180" height="180"/>
                    	</#if>
                    	</a>
                    </div><!--/myimg-->
                    <ul class="user_atten clearfix mt10">
                        <li><a href="${basePath}/mymutual/${map.cc.customerId}-1.html" target="_blank"><strong>${map.cb.guanzhu}</strong><span>关注</span></a></li>
                        <li><a href="${basePath}/mymutual/${map.cc.customerId}-0.html" target="_blank"><strong>${map.cb.fansCount}</strong><span>粉丝</span></a></li>
                        <li class="no_border"><a href="${basePath}/moodlist/${map.cc.customerId}.html" target="_blank"><strong>${map.cb.moodCount}</strong><span>心情</span></a></li>
                    </ul><!--/user_atten-->
                </div><!--/myinfo-->
                
                <div class="mycont fl ml20">
                    <div class="my_tit"><h2> ${(map.cc.customerNickname)!''}</h2></div>
                    <p class="my_mood mt5">心情：<a href="${basePath}/moodlist/${map.cc.customerId}.html" target="_blank"><span class="ml10">${(mood.moodContent)!''}</span></a></p>
                    <div class="my_tags t_tags">
                      <span class="my_sex female"></span>
                        <span>${(map.cc.province)!''}${(map.cc.city)!''}</span>
                        <#if (map.cc.province)?? || (map.cc.city)??>
                        <span class="mt_line">|</span>
                        </#if>
                        <span>积分：${map.cc.infoCount}</span>
                    </div><!--/my_tags-->
                    <!--<div class="tags_wp mt10 clearfix">
                        <em class="fl">标签：</em>
                        <ul class="tags_list fl clearfix">
                            <li><span>摄影</span></li>
                            <li><span>自信</span></li>
                            <li><span>纯真</span></li>
                            <li><span>天然萌物</span></li>
                            <li><span>巨蟹座</span></li>
                        </ul><!--/tags_list-->
                   <!-- </div><!--/tags_wp-->
                    <div class="mt30 clearfix ta_op">
                         <#if !fansFlag?? || fansFlag=='0'>
                        	<a class="m_green_btn" href="javascript:void(0);" onclick="guanzhu('${fanscustomerId}','${(fansFlag)!''}')"><span>加关注</span></a>
                           </#if>
                           <#if fansFlag?? && fansFlag=='1'>
                        	<div class="mutual fl">已关注<a href="javascript:cancelguanzhu('${fanscustomerId }');">取消</a></div>
                           </#if>
                           <#if fansFlag?? && fansFlag=='2'>
                        	<div class="mutual fl" >互相关注<a href="javascript:cancelguanz('${fanscustomerId}');">取消</a></div>
                           </#if>
                           <a class="m_gray_btn" href="javascript:sendmessage();"><span>发私信</span></a>
                    </div>
                   <!-- <div class="add_blacklist tr">
                        <span>加入黑名单</span>
                    </div><!--/add_blacklist-->
                </div><!--/mycont-->
            </div><!--/personal_wp-->
            
            <ul class="per_nav mt20 clearfix">
                <li><a class="pn_01" href="javascript:;">TA的主页</a></li>
                <!--<li><a class="pn_02" href="javascript:;">TA的资料</a></li>
                <li><a class="pn_03" href="javascript:;">共同关注</a></li>
                <li class="cur"><a class="pn_04" href="javascript:;">TA的相册</a></li>-->
            </ul><!--/per_nav-->
            
            <div class="friends_news mt20">
                <div class="topics_wp mt20">
                   <#if pbsome.list??>
                     	<#list pbsome.list as topic> 
                        <div class="topic_box clearfix">
                            <div class="head_box fl ml20"><a class="u_head" href="${basePath}/customerhomepage/${map.cc.customerId}.html" target="_blank">
                             <#if (map.cc.customerImg)?? && (map.cc.customerImg)!="">
                              <img alt="${(map.cc.customerNickname)!''}" src="${map.cc.customerImg}"  width="50" height="40"/>
                            <#else>
                              <img alt="${(map.cc.customerNickname)!''}" src="${basePath}/images/default_head3.jpg"  width="50" height="40"/>
                            </#if>
                            </a></div>
                            <div class="topic_cont fr">
                                <span class="t_arrow">&nbsp;</span>
                                <div class="fn_hd">
                                    <a class="mr10" href="${basePath}/customerhomepage/${map.cc.customerId}.html" target="_blank">
                                  		  ${(map.cc.customerNickname)!''}</a>在${topic.groupName}发表了新的话题<a class="ml10" href="${basePath}/topicdetail/${topic.groupId}-${topic.topicId}.html" target="_blank">${topic.topicTitle}</a>
                                </div><!--/fn_hd-->
                                <div class="fn_cont mt15 clearfix"> 
                                    <div class="fn_info fl">  
                                        <i class="q_marks_01">&nbsp;</i>
                                          <#assign content=(topic.topicContent)?replace("<.*?>","","r")>
                                    <#if content?length gt 250>
		                               ${content?substring(0,250)}...
		                               <#else>
		                               ${content} 
		                              </#if>
											  
                                        <i class="q_marks_02">&nbsp;</i>    
                                    </div><!--/fn_info-->  
                                    <div class="fn_imgs fr">  
                                    <#if topic.piclist??>
                                    		<#list topic.piclist as pic>
                                    			<#if (pic_index<3)>
                                                <a href="${basePath}/topicdetail/${topic.groupId}-${pic.topicId}.html" target="_blank"><img alt="" src="${pic.topicImgUrl}" width="60" height="auto" /></a>
                                        		</#if>
                                        	</#list>
                                      </#if>
                                    </div><!--/fn_imgs-->
                                </div><!--/fn_cont-->
                                <div class="fn_bt mt15 clearfix">
                                    <span class="date light fl">${topic.topicCreateTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                                    <div class="tp_info clearfix fr">
                                        <a class="fl" href="javascript:;">热度（${topic.topicHot}）</a>
                                        <a class="fl" href="javascript:;">评论（${(topic.replyCount)!'0'}）</a>
                                        <!-- Baidu Button BEGIN -->
                                        <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare fl ml10">
                                            <span class="bds_more">分享</span>
                                        </div>
                                        <script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=3618405" ></script>
                                        <script type="text/javascript" id="bdshell_js"></script>
                                        <script type="text/javascript">
                                            document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
                                        </script>
                                        <!-- Baidu Button END -->
                                    </div><!--/tp_info-->
                                </div><!--/fn_bt-->
                            </div><!--/topic_cont-->
                        </div><!--/topic_box-->
                        </#list>
                   </#if>
                </div><!--/topics_wp-->
            </div><!--/friends_news-->
            
              <#if (pbsome.list?size>0)>
                <form action="${basePath}/customerhomepage/${map.cc.customerId }.html" method="post" name="group_page_form" id="group_page_form">
                 <input id="page" name="pageNo" type="hidden" value="${pbsome.pageNo }"/>
                
                  <div class="pages tc mt30">
                   <!--判断上一页-->
                     <a class="pg_prev" 
                      <#if ((pbsome.pageNo)>1)>
                       href="javascript:page('${pbsome.pageNo-1}')"
                       <#else>href="javascript:void(0)" style="color:#999;visibility:hidden"</#if>
                       >&lt;上一页</a>
                     <#if ((pbsome.startNo)>1)> 
                      <a href="javascript:page('1');" >1</a>
									...
                     </#if>
                     <#list pbsome.startNo..pbsome.endNo as i>
                      <a <#if pbsome.pageNo==i> class="cur"</#if> href="javascript:page('${i}');">${i}</a>
                     </#list>
                     <#if (pbsome.totalPages > pbsome.endNo)>
                        ...
                        <a href="javascript:page('${pbsome.totalPages}')"${pbsome.totalPages}></a>
                     </#if>
                     <!--判断下一页-->
                     <a class="pg_next" 
                       <#if ((pbsome.pageNo+1) > pbsome.totalPages)>
                       href="javascript:void(0)" style="color:#999;visibility:hidden"
                       <#else>href="javascript:page('${pbsome.pageNo+1}')"
                       </#if>>下一页&gt;
                     </a>
                  </div>
                 </form>
                </#if>
        </div><!--/left_wp-->
        
        <div class="right_wp fr">
            <div class="ta_group ml5">
                <h3 class="m_tit">TA加入的小组</h3>
                <ul class="la_list clearfix mt20">
                     <#if joinedgroup??>
                     <#list joinedgroup as group>
                      <li>
                    	<a href="${basePath}/groupdetail/${group.groupId}.html"  target="_blank">
                    	<#if (group.groupHead)?? && (group.groupHead)!="">
                    		<img width="50px" height="50px" alt="${group.groupName}" src="${group.groupHead}" /></a>
                    	 <#else>
                    		<img width="50px" height="50px" alt="${group.groupName}" src="${basePath}/images/default_head3.jpg" /></a>
                    	</#if>
                        <a class="name" href="${basePath}/groupdetail/${group.groupId}.html"  target="_blank">${group.groupName}</a>
                    </li>
                     </#list>
                    </#if>
                </ul><!--/la_list-->
            </div><!--/ta_group-->
            
            <div class="m_focus mt30 ml5">
                <div class="my_title clearfix">
                    <h3 class="m_tit fl">TA的关注</h3>
                    <a class="fr more" href="${basePath}/mymutual/${map.cc.customerId}-1.html" target="_blank">更多>></a>
                </div><!--/my_title-->
                <ul class="la_list clearfix mt20">
                     <#if fanslist??>
                     <#list fanslist as fans>
                    <li>
                    	<a href="${basePath}/customerhomepage/${fans.customerId}.html"  target="_blank">
                    	<#if (fans.infoHeadimg)?? && fans.infoHeadimg!="" >
                    		<img  width="50px" height="50px" alt="${fans.customerName}" src="${fans.infoHeadimg}" />
                    	<#else>
                    	    <img  width="50px" height="50px" alt="${fans.customerName}" src="${basePath}/images/default_head3.jpg" />
                    	</#if>
                    	</a>
                        <a class="name" href="${basePath}/customerhomepage/${fans.customerId}.html"  target="_blank">${fans.customerName}</a>               
                     </li>
                     </#list>
                    </#if>
                </ul><!--/la_list-->
            </div><!--/m_focus-->
            
            <div id="gg_slides" class="mt50" style="width:245px; height:375px; overflow:hidden;">
                <div class="slides_container">
                    <div class="slide"><a href="javascript:;"><img alt="" src="${basePath}/images/images_17.jpg" width="243" height="360" /></a></div>
                    <div class="slide"><a href="javascript:;"><img alt="" src="${basePath}/images/images_17.jpg" width="243" height="360" /></a></div>
                    <div class="slide"><a href="javascript:;"><img alt="" src="${basePath}/images/images_17.jpg" width="243" height="360" /></a></div>
                </div><!--/slides_container-->
            </div><!--/gg_slides-->
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
        </div><!--/right_wp-->
    </div><!--/container-->
     <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>

 <div class="mask1"></div>
 <div class="dialog1 dia1" style="width:450px; height:220px;">
	<a class="close" href="javascript:cls1();"></a>
    <h4>私信</h4>
    <form id="sendForm" method="post">
    <dl class="create_albums mt20 ml20 clearfix">
    	<dt>发给：</dt>
    		<input type="hidden" name="messageAuthorId" value="${cusId!'' }"/>
    		<input type="hidden" name="messageRecCustomerId" value="${(fanscustomerId)!'' }"/>
    		
        <dd><input class="ca_text" type="text" id="" name="customerUsername" readonly value="${(map.cc.customerNickname)!''}"/></dd>
        <dt>内容：</dt>
        <dd><textarea class="ca_txa" id="messageContent" maxlength="300" onkeyup="length300();" name="messageContent"></textarea><span id="ab">可输入300字</span></dd>
       
    </dl><!--/create_albums--> 
    </form>
    <div class="tc mt10 d_lk"><a class="ok_btn" href="javascript:dosendmessage();">确定</a><a class="cancel_btn" href="javascript:cls1();">取消</a></div>
</div><!--/dialog-->

 <div class="dialog1 dia2"  style="width:300px; height:120px;">
	<a class="close" href="javascript:cls1();"></a> 
    <h4>提示</h4>
    <div class="create_albums mt20  clearfix" style="text-align:center; line-height:60px;">
    	<span id="message" ></span>
    </div><!--/create_albums-->
</div><!--/dialog-->
	
	<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
	<script type="text/javascript" src="${basePath}/js/common.js"></script>
	<script type="text/javascript" src="${basePath}/js/slides.min.jquery.js"></script>
	<script type="text/javascript" src="${basePath}/js/jquery.jqtransform.js"></script>
	<script type="text/javascript" src="${basePath}/js/social/default.js"></script>
    <script type="text/javascript" src="${basePath}/js/message/fans_comm.js"></script>
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