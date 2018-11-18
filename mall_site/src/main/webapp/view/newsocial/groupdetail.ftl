<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl" />
<title>小组详情-${topmap.systembase.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/> 
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>

<style type="text/css">
.invite_btn {background:url(../images/invite_btn.gif) no-repeat left 5px; width:110px; height:24px; line-height:24px; text-indent:27px;}
.invite_btn:hover{text-decoration:none; color:#336699;}
.all_img:hover{text-decoration:none; }
.add_team:hover{text-decoration:none; }
.mamage_group:hover{text-decoration:none; }
.topic_check {position:absolute; left:11px; top:10px; display:block;}
.leader_wp {margin-left:0px;}
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
	<input type="hidden" id="customerpow" value="${(custome.customerPower)!''}" />
    <input type="hidden" id="customerid" value="${(custome.customerId)!''}" />
    <input type="hidden" id="addtype" value="${(group.limitAddType)!''}" />
    <input type="hidden" id="addcondition" value="${(group.limitCondition)!''}" />
    <input type="hidden" id="createid" value="${(group.groupCreateAuthorId)!''}" />
    <input type="hidden" id="groupid" name="groupId" value="${(group.groupId)!''}" />
    <input type="hidden" id="isSiteManager" name="isSiteManager" value="${(cinfo.isSiteManager)!''}" />
	<div class="wrapper clearfix">
    	<div class="left_cont fl">
        	<div class="team_introduce clearfix" style="background-color:#fff;padding:10px;">
            	<div class="fl intro_left tc">
            	    <#if (group.groupHead)??>
            	       <img alt="${group.groupName}" src="${group.groupHead}" />
            	     <#else>
            	       <img alt="${group.groupName}" src="${basePath}/images/default_head2.jpg" />
            	    </#if>
                    <a class="mamage_group none" href="${basePath}/groupinfo.html?groupId=${group.groupId}" id="manager">管理小组</a>
                </div><!--/intro_left-->
                <div class="fl team_cont ml15">
                	<div class="tc_tit clearfix">
                    	<h3 class="teamName fl">${group.groupName}</h3>
                        <span class="t_num fl">（${group.groupmember}个成员）</span>     
                        <span class="fr mt5 mr5">创建于：${group.groupCreateTime?string('yyyy-MM-dd')}</span>
                        <div class="t_leader head_box fr mr20 mt5">组长：
                        
                        <a href="${basePath}/customerhomepage/${group.createId}.html">${group.groupCreateAuthorName}</a></div>
                    </div><!--/tc_tit-->
                    <p class="lh200 mt10">${group.groupRemark}</p>
                    <div class="addTeam mt20" id="join">
                    	<a class="add_team open_window" href="javascript:void(0);" onclick="addTeam()">加入小组</a>
                        <span>加入之后，你可以在这个小组发布分享和话题</span>
                    </div><!--/addTeam-->
                    
                    <a class="quit_group mt20" href="javascript:void(0);" style="display:none;" id="out" onclick="outTeam()">退出小组</a>
                    
                    <#if (group.groupSecret)??&&(group.groupSecret)=='0'>
                    <div class="fr mt10 shareWp">
                    	<!-- Baidu Button BEGIN -->
                        <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare" data="{'url':'${basePath}/groupdetail/${group.groupId }.html',
	                                    													  'text':'我喜欢qpmall的《${group.groupName}》小组。'}">
                            <span class="bds_more">分享到：</span>
                            <a class="bds_qzone"></a>
                            <a class="bds_tsina"></a>
                            <a class="bds_tqq"></a>
                            <a class="bds_renren"></a>
                            <a class="bds_t163"></a>
                            <a class="shareCount"></a>
                        </div>
                        <script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=3618405" ></script>
                        <script type="text/javascript" id="bdshell_js"></script>
                        <#assign groupDescription =group.groupRemark>
                        <#if groupDescription?length gt 20>
                        <#assign groupDes=groupDescription?substring(0,20)>
                        <#else> <#assign groupDes=groupDescription>
                        </#if>
                        <script type="text/javascript">
                        window._bd_share_config = {
							common : {		
								bdText : '我喜欢qpmall的《${group.groupName}》小组。(分享自@qpmall)',	
								bdUrl : '${basePath}/groupdetail/${group.groupId}.html', 	
								bdPic : '${(group.groupHead)!''}'		
							}
						}
                        document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
                        </script>
                        <!-- Baidu Button END -->
                    </div><!--/shareWp-->
                    </#if>
                    <#if (custome.customerPower)?? || (cinfo??&&cinfo.isSiteManager=='1')>
                    <a class="invite_btn fr mt10 mr10" href="${basePath}/invitefriends-${group.groupId}.html">邀请好友加入</a>
                    </#if>
                </div><!--/team_cont-->
            </div><!--/team_introduce-->
            
            <div class="group_albums mt20">
            	<div class="albums_tit clearfix">
                	<h3 class="tit fl">小组相册</h3>
                    <a class="all_img fl" href="${basePath}/groupimgalbums/${group.groupId}.html" target="_blank">（全部图片${imgcount}张）</a>
                    <#if (custome.customerId)?? && (custome.customerPower)?? || ((cinfo)?? && cinfo.isSiteManager=='1')>
                    <a class="update_img fr" href="${basePath}/toaddgroupimg/${group.groupId}.html" target="_blank">上传照片</a>
                    </#if>
                </div><!--/albums_tit-->
                <ul class="albums_list mt20 clearfix">
                    <#if groupImgs??>
                      <#list groupImgs as img>
	                	<li>
	                    	<a href="${basePath}/groupimgdetail/${img.groupId}-${img.groupImgId}-${img.customerId}.html" target="_blank">
	                    	<#if (img.groupImgUrl)??>
	                    	  <img alt="${(img.groupImgTitle)!''}" src="${img.groupImgUrl}" style="max-width:120px;max-height:200px;" /></a>
	                    	 <#else>
	                    	  <img alt="${(img.groupImgTitle)!''}" src="${basePath}/images/default_head2.jpg" style="max-width:120px;max-height:200px;" /></a>
	                    	</#if>
	                        <a class="ml10 mt5" href="${basePath}/groupimgdetail/${img.groupId}-${img.groupImgId}-${img.customerId}.html" target="_blank">
	                         <#if (img.groupImgTitle)?? &&(img.groupImgTitle)?length gt 15>
	                              ${(img.groupImgTitle)?substring(0,15)}
	                              <#else>${(img.groupImgTitle)!''}
	                         </#if>
	                        </a>
	                        <div class="head_box ml10 mt10">来自：<a class="from_user" href="${basePath}/customerhomepage/${img.customerId}.html">${(img.customerName)!''}</a></div>
	                    </li>
                      </#list>
                    </#if>
                </ul><!--/albums_list-->
            </div><!--/group_albums-->
            
            <div class="group_topic mt30">
            	<div class="topic_tit clearfix">
                	<h3 class="tit fl">小组话题</h3>
                    <div class="filter_wp fl">
                    	筛选：
                        <div class="down_wp">
                        	<em>全部</em>
                            <ul class="down_list">
                            	<li <#if !screening??> class='cur'<#else>'' </#if>><a href="javascript:void(0);" onclick="topicSearch('0','')">全部</a></li>
                                <li <#if screening?? && screening=='fever'> class='cur' </#if>><a href="javascript:void(0);" onclick="topicSearch('0','fever')">热帖</a></li>
                                <li <#if screening?? && screening=='essence'> class='cur' </#if>><a href="javascript:void(0);" onclick="topicSearch('0','essence')">精华</a></li>
                            </ul><!--/down_list-->
                        </div><!--/down_wp-->
                    </div><!--/filter_wp-->
                    <div class="sequence_wp fl">
                    	排序：
                        <div class="down_wp">
                        	<em>发布时间</em>
                            <ul class="down_list">
                            	<li <#if !sort?? || sort=='0'> class='cur'</#if>><a href="javascript:void(0);" onclick="topicSearch('1','0')">发布时间</a></li>
                                <li <#if sort?? && sort =='1'> class='cur' </#if>><a href="javascript:void(0);" onclick="topicSearch('1','1')">回复时间</a></li>
                               
                            </ul><!--/down_list-->
                        </div><!--/down_wp-->
                    </div><!--/sequence_wp-->
                   <#if (custome.customerId)?? && (custome.customerPower)?? || (cinfo?? && cinfo.isSiteManager=='1')>
                    <a class="post_topic fr" href="${basePath}/topubtopic.html?groupId=${group.groupId}" target="_blank">发表话题</a>
                   </#if>
                   <form action="${basePath}/groupdetail/${group.groupId}.html" id="topic_search_form" name ="topic_search_form" method="post">
                    <input type="hidden" name="screening" id="screening" value="${screening!'' }"/>
           		    <input type="hidden" name="sort" id="sort"  value="${sort !''}"/>
                    <div class="topic_search clearfix fr mr10">
                    	<input class="ts_text fl" type="text" name="topicTitle"  value="${topictitle!''}" placeholder="话题搜索" />
                        <input class="ts_btn fl" type="submit" value="" />
                    </div><!--/topic_search-->
                   </form>
                </div><!--/topic_tit-->
	            <#if (specialtopic??&&specialtopic?size gt 0) || (commontopic??&&commontopic?size gt 0) || (pb.list?size gt 0)>
	        	    <#if (custome?? &&(custome.customerPower == '1' || custome.customerPower == '2')) ||(cinfo?? && cinfo.isSiteManager=='1')>
	        	    <div class="rowElem qx_wp mt20 ml10">
	            		<label><input class="" type="checkbox"  onclick="switchAll(this,'topicId')"/>全选</label>
	            	</div>
	               </#if>
	            </#if>
	           <form id="topic_form" name="topic_form" method="post" action=''>
                <ul class="top_topic topic_list mt20">
                     <#if specialtopic??>
		               <li class="g_top">
		            	<div>
		            	   <#if (custome?? &&(custome.customerPower == '1' || custome.customerPower == '2')) ||(cinfo?? && cinfo.isSiteManager=='1')>
		                		<div>
		                		  <input type="checkbox" name="topicId" value="${specialtopic.topicId}"/>
		                		</div>
		                  </#if>
		                </div><!--/topic_check-->
		            	<a class="tp_caption" href="${basePath}/topicdetail/${group.groupId}-${specialtopic.topicId}.html" target="_blank">${(specialtopic.topicTitle)!''}
		            	<#if specialtopic.topicFever == '1' >
		            	   <img alt="" src="${basePath}/images/hot_ico.gif" />
		            	</#if> 
		            	<#if specialtopic.topicEssence == '1' >
		            	  <img alt="" src="${basePath}/images/fine_ico.gif" />
						</#if>
						 <#if (specialtopic.topicImgs)??> 
						   <#assign piccount=0 />
						   <#assign vidcount=0 />
                    	     <#list (specialtopic.topicImgs) as topicImg>
                    	        <#if topicImg.topicType=='0'>
                    	           <#assign piccount=1+piccount />
                    	        </#if>
                    	        <#if topicImg.topicType=='1'>
                    	            <#assign vidcount=1+vidcount />
                    	        </#if>
	                    	 <#if piccount??&&piccount==1>
	                    	    <img alt="" src="${basePath}/images/pic_ico.gif" />
	                    	  </#if>
	                    	  <#if vidcount??&&vidcount==1>
	                    	     <img alt="" src="${basePath}/images/video_ico.gif" />
	                    	  </#if>
                    	     </#list>
                    	 </#if>
		            	</a>
		                <div class="topicInfo clearfix mt10">
		                	<div class="tp_from head_box fl mr30">
		                    	来自：<a href="${basePath}/customerhomepage/${specialtopic.customerId}.html" target="_blank">${specialtopic.customerName}</a>
		                    </div><!--/tp_from-->
		                    <span class="fl tp_date">时间：${(specialtopic.topicCreateTime)?string('yyyy-MM-dd HH:mm:ss')}</span>
		                    <div class="fr reply_view">
		                    	<a href="${basePath}/topicdetail/${group.groupId}-${specialtopic.topicId}.html" target="_blank">${(specialtopic.replyCount)!'0'}回复</a> / <a href="${basePath}/topicdetail/${group.groupId}-${specialtopic.topicId}.html">${(specialtopic.topicHot)!'0'}查看</a>
		                    </div><!--/reply_view-->
		                </div><!--/topicInfo-->
		              </li>
                    </#if>
                   <#if commontopic??>
                     <#list commontopic as commtopic>
	                    <li>
	                    	<div>
	                        	 <#if (custome?? &&(custome.customerPower == '1' || custome.customerPower == '2')) ||(cinfo?? && cinfo.isSiteManager=='1')>
				                	  <div>
				                	  	<input type="checkbox" name="topicId" value="${commtopic.topicId}"/>
				                	  </div>
				                 </#if>
	                        </div><!--/topic_check-->
	                    	<a class="tp_caption" href="${basePath}/topicdetail/${group.groupId}-${commtopic.topicId}.html" target="_blank">${(commtopic.topicTitle)!''}
		                       <#if commtopic.topicFever == '1' >
			            	      <img alt="" src="${basePath}/images/hot_ico.gif" />
			            	   </#if> 
			            	  <#if commtopic.topicEssence == '1' >
			            	      <img alt="" src="${basePath}/images/fine_ico.gif" />
							  </#if>
	                    	   <#if (commtopic.topicImgs)??> 
	                    	     <#assign commpcount=0 />
	                    	     <#assign commvcount=0 />
	                    	     <#list (commtopic.topicImgs) as topicImg>
	                    	        <#if topicImg.topicType=='0'>
	                    	           <#assign commpcount=1+commpcount />
	                    	        </#if>
	                    	        <#if topicImg.topicType=='1'>
	                    	            <#assign commvcount=1+commvcount />
	                    	        </#if>
		                    	   <#if commpcount??&&commpcount==1>
		                    	     <img alt="" src="${basePath}/images/pic_ico.gif" />
		                    	   </#if>
		                    	   <#if commvcount??&&commvcount==1>
		                    	     <img alt="" src="${basePath}/images/video_ico.gif" />
		                    	   </#if>
	                    	     </#list>
	                    	   </#if>
	                    	</a>
	                        <div class="topicInfo clearfix mt10">
	                        	<div class="tp_from head_box fl mr30">
	                            	来自：<a href="${basePath}/customerhomepage/${commtopic.customerId}.html" target="_blank">${commtopic.customerName}</a>
	                            </div><!--/tp_from-->
	                            <span class="fl tp_date">时间：${(commtopic.topicCreateTime)?string('yyyy-MM-dd HH:mm:ss')}</span>
	                            <div class="fr reply_view">
	                            	<a href="${basePath}/topicdetail/${group.groupId}-${commtopic.topicId}.html" target="_blank">${(commtopic.replyCount)!'0'}回复</a> / <a href="${basePath}/topicdetail/${group.groupId}-${commtopic.topicId}.html">${(commtopic.topicHot)!'0'}查看</a>
	                            </div><!--/reply_view-->
	                        </div><!--/topicInfo-->
	                    </li>
                     </#list>
                   </#if>
                </ul><!--/top_topic-->
                
                <ul class="general_topic topic_list mt20">
                    <#if (pb.list)??>
                      <#list pb.list as topic>
	                	<li>
	                    	<div>
	                        	  <#if (custome?? &&(custome.customerPower == '1' || custome.customerPower == '2')) ||(cinfo?? && cinfo.isSiteManager=='1')>
				            	    <div >
				                		<input type="checkbox" name="topicId" value="${topic.topicId}"/>
				                	</div>
		                   		 </#if>
	                        </div><!--/topic_check-->
	                    	<a class="tp_caption" href="${basePath}/topicdetail/${group.groupId}-${topic.topicId}.html" target="_blank">${(topic.topicTitle)!''}
	                    	<#if topic.topicFever == '1' >
			            	      <img alt="" src="${basePath}/images/hot_ico.gif" />
			            	   </#if> 
			            	  <#if topic.topicEssence == '1' >
			            	      <img alt="" src="${basePath}/images/fine_ico.gif" />
							  </#if>
							   <#if (topic.topicImgs)??> 
                    	       <#assign  pcount=0 />
                    	       <#assign  vcount=0 />
	                    	     <#list (topic.topicImgs) as topicImg>
	                    	        <#if topicImg.topicType=='0'>
	                    	           <#assign  pcount=pcount+1 />
	                    	        </#if>
	                    	        <#if topicImg.topicType=='1'>
	                    	            <#assign  vcount=1+vcount />
	                    	        </#if>
		                    	    <#if pcount??&&pcount==1>
		                    	      <img alt="" src="${basePath}/images/pic_ico.gif" />
		                    	    </#if>
		                    	    <#if vcount??&&vcount==1>
		                    	     <img alt="" src="${basePath}/images/video_ico.gif" />
		                    	   </#if>
	                    	     </#list>
	                    	    </#if>
	                    	</a>
	                        <div class="topicInfo clearfix mt10">
	                        	<div class="tp_from head_box fl mr30">
	                            	来自：<a href="${basePath}/customerhomepage/${topic.customerId}" target="_blank">${topic.customerName}</a>
	                            </div><!--/tp_from-->
	                            <span class="fl tp_date">时间：${(topic.topicCreateTime)?string('yyyy-MM-dd HH:mm:ss')}</span>
	                            <div class="fr reply_view">
	                            	<a href="${basePath}/topicdetail/${group.groupId}-${topic.topicId}.html" target="_blank">${(topic.replyCount)!'0'}回复</a> / <a href="${basePath}/topicdetail/${group.groupId}-${topic.topicId}.html">${(topic.topicHot)!'0'}查看</a>
	                            </div><!--/reply_view-->
	                        </div><!--/topicInfo-->
	                    </li>
                        
                      </#list>
                    </#if>
                </ul><!--/general_topic-->
                <input type="hidden" name="essence" id="essenceH" />
                <input type="hidden" name="fever" id="hotH"/>
                <input type="hidden" name="top" id="topH" />
               </form> 
               
                <#if (custome?? &&(custome.customerPower == '1' || custome.customerPower == '2')) ||(cinfo?? && cinfo.isSiteManager=='1')>
                <div class="leader_wp mt20 clearfix">
                    <a class="fl l_op" href="javascript:void(0);" onclick="delBatchTopic()">删除</a>
                    <#--<a class="fl l_op" href="javascript:;">删除并拉黑</a>-->
                    <a class="fl l_op" href="javascript:void(0);" onclick="unreplyBatchTopic()">禁止回应</a>
                    <a class="fl l_op" href="javascript:void(0);" onclick="replyBatchTopic()">允许回应</a>
                   <#-- <a class="fl l_op" href="javascript:void(0);" id="recommendBut" onclick="recommendTopicBatch()">申请到首页</a>-->
                    <a class="fl l_op" href="javascript:void(0);"onclick="essenceTopicBatch('1')">设精华</a>
                   	<a class="fl l_op" href="javascript:void(0);"onclick="noEssenceTopicBatch('0')">除精华</a>
                	<a class="fl l_op" href="javascript:void(0);"onclick="hotTopicBatch('1')">设热帖</a>
                	<a class="fl l_op" href="javascript:void(0);"onclick="noHotTopicBatch('0')">除热帖</a>
                    <div class="leader_box fl">
                    	<div class="down_wp">
                        	<em>置顶</em>
                            <ul class="down_list">
                            	<li class="cur"><a href="javascript:void(0);"  onclick="topTopicBatch('0')">不置顶</a></li>
                                <li><a href="javascript:void(0);" onclick="topTopicBatch('1')">一般置顶</a></li>
                                <li><a href="javascript:void(0);" onclick="topTopicBatch('2')">特别置顶</a></li>
                            </ul><!--/down_list-->
                        </div><!--/down_wp-->
                    </div><!--/leader_box-->
                    
                </div><!--/leader_wp-->
               </#if> 
                
           <form action="${basePath}/groupdetail/${group.groupId}.html" method="post" name="group_page_form" id="group_page_form">
             <input id="page" name="pageNo" type="hidden" value="${pb.pageNo }"/>
             <input type="hidden" name="screening" value="${screening!'' }"/>
             <input type="hidden" name="sort" value="${sort !''}"/>
             <input type="hidden" name="topicTitle" id="topicTitle" value="${topictitle!''}"/>
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
             
           </div><!--/group_topic-->
        </div><!--/left_cont-->
        
        <div class="right_cont fr">
        	<div class="latest_add">
            	<h3>最新加入<a href="${basePath}/groupmemberlist/${group.groupId}.html" target="_blank">（所有${group.groupmember}个成员）</a></h3>
                <ul class="la_list clearfix mt20" style="width:300px;">
                    <#if groupmember??>
                      <#list groupmember as member>
	                	<li><a href="${basePath}/customerhomepage/${member.customerId}.html"  target="_blank">
	                	<#if (member.infoHeadimg)?? && (member.infoHeadimg)!="">
	                	  <img alt="${member.customerName}" src="${member.infoHeadimg}" width="50px" height="50px"/>
	                	<#else>
	                	  <img alt="${member.customerName}" src="${basePath}/images/default_head3.jpg" width="50px" height="50px"/>
	                	</#if>
	                	 <#if (member.customerName)?length gt 8>
		                      ${(member.customerName)?substring(0,6)}
		                  <#else>${member.customerName}</#if></a></li>
                      </#list>
                    </#if>
                </ul><!--/la_list-->
            </div><!--/latest_add-->
            
	    <#if advlist??>
    		<#list advlist as adv>
            <div id="gg_slides" class="mt20" style="width:290px; height:200px; overflow:hidden;">
            	<div class="slides_container">
				       	 <div class="slide"><a href="${adv.advUrl}" target="_blank"><img src="${adv.advImg}" width="290" height="200" alt="${adv.advName}"/></a></div>
                </div><!--/slides_container-->
            </div><!--/gg_slides-->
	        </#list>
	        <#else><div class="slide"><a href="javascript:void(0);"><img alt="" src="${basePath}/images/images_17.jpg" width="290" height="200" /></a></div>
	    </#if>
      
            <div class="hot_team mt20">
            	<h3>热门小组</h3>
                <ul class="ht_list clearfix mt20" style="width:300px;">
                	<#if hotlist??>
                	  <#list hotlist as hot>
                		<li><a href="${basePath}/groupdetail/${hot.groupId}.html">
                		<#if (hot.groupHead)?? && (hot.groupHead)!="">
                		     <img height="50px" width="50px" alt="${hot.groupName}" src="${hot.groupHead}" title="${hot.groupName}"/>
                		  <#else>
                		     <img height="50px" width="50px" alt="${hot.groupName}" src="${basePath}/images/default_head3.jpg" title="${hot.groupName}"/>
                		</#if>
                		      ${hot.groupName}</a></li>
                	  </#list>
                	</#if>
                </ul><!--/ht_list-->
            </div><!--/hot_team-->
        </div><!--/right_cont-->
    </div>

<#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
</#if>

  <div class="mask1"></div>
     <div class="dialog1 dia1" style="width:300px; height:120px;">
    	<a class="close" href="javascript:cls1();"></a>
        <h4>系统提示</h4>
        <p class="tc f14 mt20" style="padding:20px;" id ="message"></p>  
     </div><!--/dialog-->
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/social/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath}/js/slides.min.jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.jqtransform.js"></script>
<script type="text/javascript" src="${basePath}/js/social/group_detail.js"></script>

<script type="text/javascript">
			$(document).ready(function(){
			   var customerpower = $("#customerpow").val();
			   var customerid = $("#customerid").val();
			   var createid = $("#createid").val();
			   var isSiteManager = $("#isSiteManager").val();
				   if(customerid == '' && isSiteManager==0){
				     $("#join").show();
				     $("#out").hide();
				     $("#manager").hide();
				     $("#upload").hide();
				     $("#createtopic").hide();
				   }else if(customerid == '' && isSiteManager==1){
				         $("#join").show();
					     $("#out").hide();
					     $("#manager").show();
					     $("#upload").show();
					     $("#createtopic").show();
				   }else{
					   if(customerpower == 0 && customerpower != '' && isSiteManager==0){
					     $("#join").hide();
					     $("#out").show();
					     $("#manager").hide();
					     $("#upload").show();
					     $("#createtopic").show();
					   }else if(customerpower == 0 && customerpower != '' && isSiteManager==1){
					     $("#join").hide();
					     $("#out").show();
					     $("#manager").show();
					     $("#upload").show();
					     $("#createtopic").show();
					   }
					   else if(customerpower == 2 ){
					     $("#join").hide();
					     $("#out").hide();
					     $("#manager").show();
					     $("#upload").show();
					     $("#createtopic").show();
					   }else if(customerpower == 1 ){
					    $("#join").hide();
					     $("#out").show();
					     $("#manager").show();
					     $("#upload").show();
					     $("#createtopic").show();
					   }
				   }
			  });
				$(function() {
					$(".topic_list").jqTransform();
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
				var flag = "${group.groupBackgroundType}";
				var repeatflag;
				if(flag=='0'){
					repeatflag="no-repeat";
				}else{
					repeatflag="repeat";
				}
			var brack="${(group.groupBackground)!'' }";
			if(brack!=''){
					if(flag=='0'){
						$("html").css('background','url('+brack+')').css('background-repeat',''+repeatflag+'').css('background-position','center top');
					}else{
						$("html").css('background','url('+brack+')').css('background-position','center top');
					}
			}
		});
		
			$(function(){
			//选中首页社区
			$(".sort_list li").removeClass("cur");
			$(".sort_list li:eq(1)").addClass("cur");
        });		
</script>
</body>
</html>

