<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl"> 
<title>话题详情-${topmap.systembase.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/> 
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>
<link rel="stylesheet"  href="${basePath}/js/plugin/kindeditor/themes/default/default.css" />
<link rel="stylesheet"  href="${basePath}/js/plugin/kindeditor/plugins/code/prettify.css" />

<style type="text/css">
.atc_rec {background:url(../images/recommend_bg.gif) no-repeat left top; height:32px; padding-left:27px; line-height:32px;}   
.atc_rec a {float:left; background:url(../images/recommend_bg.gif) right bottom; color:#336699; padding-right:5px; padding-left: 0px; }
.ke-toolbar {
	background-color: #fff!important;
    }

.ke-statusbar {
   background-color: #fff!important;
    }
.ke-statusbar{
	display:none!important;
	}

.c_tx3{
    color:red!important;
}
.comment_list li .rowElem {position:absolute; top:15px; left:0px;}
.personal_reg {
    color: #E2393F;
    padding-left: 10px;
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
 <input type="hidden" id="topicId" name="topicId" value="${topic.topicId}" />
 <input type="hidden" id="custId" name="customerId" value="${custId!''}" />
 <input type="hidden" id="topicReplyFlag" name="topicReplyFlag" value="${(topic.topicReplyFlag)!''}" />
 <input type="hidden" id="customerPower" name="customerPower" value="${(custome.customerPower)!''}" />
 <input type="hidden" id="replyFalg" name="reply" value="${(group.limitReplyType)!''}" />
 <input type="hidden" id="topicCusId" name="topicCusId" value="${(topic.customerId)!''}" />
 <input type="hidden" id="topicRecommend" name="topicRecommend" value="${(topic.topicRecommend)!'0'}" />
<div class="wrapper clearfix">
    	<div class="left_cont fl">
        	<div class="l_title clearfix">
            	<h3 class="b_tit fl">${topic.topicTitle}
            	<#--<#if topic.topicCateId=='0'> <span style="font-size:16px; color:#666; font-weight:400;">(客厅)</span></#if>
            	<#if topic.topicCateId=='1'> <span style="font-size:16px; color:#666; font-weight:400;">(卧室)</span></#if>
            	<#if topic.topicCateId=='2'> <span style="font-size:16px; color:#666; font-weight:400;">(书房)</span></#if>
            	<#if topic.topicCateId=='3'> <span style="font-size:16px; color:#666; font-weight:400;">(厨房)</span></#if>
            	<#if topic.topicCateId=='4'> <span style="font-size:16px; color:#666; font-weight:400;">(餐厅)</span></#if>
            	<#if topic.topicCateId=='5'> <span style="font-size:16px; color:#666; font-weight:400;">(阳台花园)</span></#if>
            	<#if topic.topicCateId=='6'> <span style="font-size:16px; color:#666; font-weight:400;">(卫浴间)</span></#if>
            	<#if topic.topicCateId=='7'> <sapn style="font-size:16px; color:#666; font-weight:400;">(飘窗)</span></#if>
            	<#if topic.topicCateId=='8'> <span style="font-size:16px; color:#666; font-weight:400;">(套房)</span></#if>
            	<#if topic.topicCateId=='9'> <span style="font-size:16px; color:#666; font-weight:400;">(其他)</span></#if>-->
            	</h3>
                   <#if topic.topicEssence =='1'>
                     <img class="mt10" alt="" src="${basePath}/images/fine_ico.gif" />
                   </#if>
                   <#if topic.topicFever =='1'>
                    <img class="mt10" alt="" src="${basePath}/images/hot_ico.gif" />
                  </#if>
                <span class="b_info fr"><span id="hf">${topic.replyCount!'0'}</span>回复/${(topic.topicHot)!'0'}查看</span>
            </div><!--/l_title-->
            
            <div class="article_info mt30">
            	<a class="author_img fl" href="${basePath}/customerhomepage/${topic.customerId}.html">
            	<#if (topic.customerHead)?? && (topic.customerHead)!="">
	            	<img alt="${topic.customerName}" src="${topic.customerHead}" width="50px" height="50px"/>
            	<#else>
    	        	<img alt="${topic.customerName}" src="${basePath}/images/default_head3.jpg" width="50px" height="50px"/>
            	</#if>
    	       </a>
                <div class="articleInfo f14 fl ml10">
                	<p class="atc_from">来自：<a href="${basePath}/customerhomepage/${topic.customerId}.html" target="_blank">${topic.customerName}</a></p>
                    <p class="mt15"><span class="atc_time mr10">${(topic.topicCreateTime)?string('yyyy-MM-dd HH:mm:ss')}</span>|
                    <span id="zmem">
                    <#if !ta??>
                    <a class="m10" onclick="lookreply('${topic.customerId}','1')" href="javascript:void(0);">只看此用户</a>
	                </#if>
	                 <#if ta??>
                    <a class="m10" onclick="lookreply('','1')" href="javascript:void(0);">查看全部</a>
                    </#if>
                    </span></p>
                </div><!--/articleInfo-->
            </div><!--/article_info-->
            
            <div class="article mt20">
               ${topic.topicContent}
            </div><!--/article-->
            
            <div class="atc_wp clearfix mt10">
               <div class="leader_wp fl clearfix">
                 <#if custId?? || (cinfo?? && cinfo.isSiteManager=='1')>
                     <#if ((custome.customerPower)??&&(custome.customerPower)=='0')>
	                     <#if custId == topic.customerId>
	                       <a class="fl l_op" href="${basePath}/updatetopic.html?groupId=${group.groupId}&topicId=${topic.topicId}">修改</a>
	                     </#if>
                     </#if>
                     <#if !(custome.customerPower)?? >
	                     <#if (custId == topic.customerId)>
	                       <a class="fl l_op" href="${basePath}/updatetopic.html?groupId=${group.groupId}&topicId=${topic.topicId}">修改</a>
	                     </#if>
                     </#if>
                     <#if ((custome.customerPower)?? && (custome.customerPower == '1' || custome.customerPower == '2')) || (cinfo?? && cinfo.isSiteManager=='1')>
                       <a class="fl l_op" href="${basePath}/updatetopic.html?groupId=${group.groupId}&topicId=${topic.topicId}">修改</a>
                       <a class="fl l_op" href="javascript:void(0);" onclick="editTopic('del','1')">删除</a>
                    
                      <div id="replyDiv" class="fl pt5 pb5">
                      <#if topic.topicReplyFlag =='0'>
                         <a class="l_op" href="javascript:void(0);"onclick="editTopic('reply','1')">禁止回应</a>
                       </#if>
                       <#if topic.topicReplyFlag =='1'>
                         <a class="l_op" href="javascript:void(0);"onclick="editTopic('reply','0')">允许回应</a>
                       </#if>
                     </div>
                      <#--<#if (topic.topicIndexView)=='0'>
                       <a class="fl l_op" href="javascript:void(0);" id="recommendBut" onclick="editTopic('recommend','1')">申请到首页</a>
                     </#if>-->
                      <div id="essenceDiv" class="fl pt5 pb5">
                       <#if topic.topicEssence =='0'>
                         <a class="l_op" href="javascript:void(0);"onclick="editTopic('essence','1')">设精华</a>
                       </#if>
                       <#if topic.topicEssence =='1'>
                         <a class="l_op" href="javascript:void(0);"onclick="editTopic('essence','0')">去除精华</a>
                       </#if>
                       <#if topic.topicFever =='0'>
                         <a class="l_op" href="javascript:void(0);"onclick="editTopic('fever','1')">设热帖</a>
                       </#if>
                       <#if topic.topicFever =='1'>
                         <a class="l_op" href="javascript:void(0);"onclick="editTopic('fever','0')">去除热帖</a>
                       </#if>
                     </div>
                      <div class="leader_box fl">
                       <div class="down_wp">
                        	<em></em>
                            <ul class="down_list">
                            	<li <#if topic.topicTopView == '0'> class="cur" <#else>''</#if>><a href="javascript:void(0);"  onclick="editTopic('top','0')">不置顶</a></li>
                            	<li <#if topic.topicTopView == '1'> class="cur" <#else>''</#if>><a href="javascript:void(0);"  onclick="editTopic('top','1')">一般置顶</a></li>
                            	<li <#if topic.topicTopView == '2'> class="cur" <#else>''</#if>><a href="javascript:void(0);"  onclick="editTopic('top','2')">特别置顶</a></li>
                            </ul><!--/down_list-->
                        </div><!--/down_wp-->
                     </div>
                    </#if>
                 </#if>
               </div>
                <br />
                <br />
                <br />
                <br />
            	<!-- Baidu Button BEGIN -->
                    <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare mt20">
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
                    <script type="text/javascript">
                    document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
                    </script>
                    <!-- Baidu Button END -->
                <div class="atc_rec fr"><a href="javascript:void(0);" onclick="editTopic('recount','1')">推荐（<span id="goodCount">${(topic.topicRecommend)!'0'}</span>）</a></div>
            </div><!--/atc_wp-->
            
          <#--  <h3 class="m_tit">评论（<span id="pagecount">${(pb.rows)!'0'}</span>）</h3> -->
          <#if pb.list?size gt 0>
             <#if custome??>
               <#if custome.customerPower == '1' || custome.customerPower == '2'>
                 <div class="rowElem qx_wp mt20">
                       <label><input type="checkbox" onclick="switchAll(this,'replyId')" />全选</label>
                 </div><!--/topic_check-->
               </#if>
              </#if>
          </#if>
         <form  id="replyForm" method="post" action="">
            <input type="hidden" id="groupId" name="groupId" value="${group.groupId}" />
            <ul class="comment_list mt20">
                <#if pb.list??>
                  <#list pb.list as reply>
                  <li class="clearfix">
                   <#if custome??>
                   <#if custome.customerPower == '1' || custome.customerPower == '2'>
                      <div class="rowElem"><input type="checkbox" name="replyId" value="${reply.replyId }" /></div>
                   </#if>
                  </#if>
                	<div class="fl ml30"><a href="${basePath}/customerhomepage/${reply.customerId}.html">
                	<#if (reply.customerHeadimg)?? && (reply.customerHeadimg)!="">
    	            	<img alt="${reply.customerName}" width="50" height="50" src="${reply.customerHeadimg}"  /></a></div>
                	<#else>
	                	<img alt="${reply.customerName}" width="50" height="50" src="${basePath}/images/default_head3.jpg"  /></a></div>
                	</#if>
                    <div class="cmt_wp fl ml10" style="width:770px;">
                    	<div class="cmt_hd clearfix">
                        	<div class="head_box fl"><a class="cmt_name" href="${basePath}/customerhomepage/${reply.customerId}.html">${reply.customerName}</a></div>
                            <#if !ta??>
	                        <a class="cmt_only fl" href="javascript:onlyhe('${reply.customerId }');">只看TA</a>
	                        </#if>
	                        <#if ta??>
	                        <a class="cmt_only fl" href="javascript:onlyhe('');">全部</a>
	                        </#if>
                       <#if custome??>
		                   <#if custome.customerPower == '1' || custome.customerPower == '2'>
		                      <a class="cmt_del fr" href="javascript:void(0);" onclick="delreply('${reply.replyId}')">删除</a> 
		                       <#if custId?? && reply.customerId != custId>
		                        <a class="cmt_del fr mr10" href="javascript:void(0);" onclick="delBlack('${(group.groupId)!''}','${reply.replyId}','${(reply.customerId)!''}')">删除并拉黑</a>
		                      </#if>
		                   </#if>
		                   <#if custome.customerPower == '0' && group.limitReplyDelType == '0' && reply.customerId == custId>
		                      <a class="cmt_del fr" href="javascript:void(0);" onclick="delreply('${reply.replyId}')">删除</a> 
		                   </#if>
		                </#if>
                        </div><!--/cmt_hd-->
                        <p class="cmt_date">${(reply.replyTime)?string('yyyy-MM-dd HH:mm:ss')}</p>
                         ${(reply.replyRemark)!''}
                        <p class="cmt_cont"  id="hf${reply.replyId }">${(reply.replyContent)!''}</p>
                        <div class="cmt_op clearfix"><a class="fr " href="javascript:re('${reply.customerId}','${reply.customerName}','${reply.replyId}','1');">引用</a><a class="fr" href="javascript:re('${reply.customerId}','${reply.customerName}','${reply.replyId}','0');">回复</a></div>
                    </div><!--/cmt_wp-->
                </li>
                </#list>
               </#if>
            </ul><!--/comment_list-->
          </form>
          <form action="${basePath}/topicdetail/${group.groupId}-${topic.topicId}.html" method="post" name="group_page_form" id="group_page_form">
                 <input id="page" name="pageNo" type="hidden" value="${pb.pageNo }"/>
                 <input id="ta" name="ta" type="hidden" value="${ta!''}"/>
              <div class="pages tc mt30" id="pages">
               <!--判断上一页-->
                 <a class="pg_prev" 
                  <#if ((pb.pageNo)>1)>
                   href="javascript:page('${pb.pageNo-1}','${ta!''}')"
                   <#else>href="javascript:void(0)" style="color:#999;visibility:hidden"</#if>
                   >&lt;上一页</a>
                 <#if ((pb.startNo)>1)> 
                  <a href="javascript:page('1');" >1</a>
								...
                 </#if>
                  <#if ((pb.list)?size>0)> 
                     <#list pb.startNo..pb.endNo as i>
                      <a <#if pb.pageNo==i> class="cur"</#if> href="javascript:page('${i}','${ta!''}');">${i}</a>
                     </#list>
                 </#if>
                 <#if (pb.totalPages > pb.endNo)>
                    ...
                    <a href="javascript:page('${pb.totalPages}','${ta!''}')"${pb.totalPages}></a>
                 </#if>
                 <!--判断下一页-->
                 <a class="pg_next" 
                   <#if ((pb.pageNo+1) > pb.totalPages)>
                   href="javascript:void(0)" style="color:#999;visibility:hidden"
                   <#else>href="javascript:page('${pb.pageNo+1}','${ta!''}')"
                   </#if>>下一页&gt;
                 </a>
              </div>
             </form>
            
           <#if !custId??>
           <input type="hidden" value="${custId!''}" />
            <div class="quick_reply mt20">
                <div class="replyWp mt10">
                	<div><img alt="" src="${basePath}/images/reply_bg.gif"/></div><!--/更换为编辑器-->
                    <p class="noLogin">您目前尚未登录！请先<a class="mr5 ml5" href="${basePath}/login.html">登录</a>或<a class="ml5" href="${basePath}/register.html">注册</a></p>
                </div><!--/replyWp-->
                <div class="rowElem clearfix mt10"><a onclick="topicreply()"><input type="button" value="发表回复" /></a></div>
            </div><!--/quick_reply-->
          </#if>
           <#if custId??>
            <div class="quick_reply mt20">
            	 <form method="post" id="topicForm">
            	 		<div id="re">
                     	
                     	</div>
			            <div class="mood_wp mt20 clearfix">
			            	<textarea id="replyContent" name="replyContent"></textarea>
			            	<input type="hidden" name="replyShipId" id="replyShipId" value="${topic.topicId}"/>
			            	
			            	<input type="hidden" id="customerId" name="customerId" value="${custId!''}"/>
			            	<span id="ab" style="line-height:30px;color:red;" class="ml10"></span>
			            </div><!--/row_elem-->  
			          </br>
                    <div class="rowElem  clearfix">
                         <span class="fl" style="line-height:30px;font-family:'微软雅黑'; font-size:14px;">验证码：</span>
                     	 <input  type="text" style="width:120px;" name="varification" id="varification" maxLength="5" onfocus="getPatcha()"/>
			              <label>
			               <img id="checkCodeImg" class="vm ml5" src="${basePath}/patchca.htm" alt="验证码" style="cursor:pointer;" onclick="this.src=this.src+'?'+Math.random(); " />
			             </label>
			             <label class="small">看不清？<a id="checkCodeA" href="javascript:void(0)">换一张</a></label>
			             <span id="varification_error" class="personal_reg"></span>
                   </div>
                   </br>    
                <div class="rowElem clearfix mt10"><a onclick="topicreply()"> <input type="button" value="发表回复" /></a></div>
                </form>
            </div><!--/quick_reply-->
          </#if>
        </div><!--/left_cont-->
        
        <div class="right_cont fr">
        	<div class="topic_group clearfix">
            	<a class="fl" href="${basePath}/groupdetail/${group.groupId}.html">
            	<#if (group.groupHead)?? && (group.groupHead)!="">
    	        	<img width="50px" height="50px" alt="${group.groupName}" src="${group.groupHead}" /></a>
            	<#else>
	            	<img width="50px" height="50px" alt="${group.groupName}" src="${basePath}/images/default_head3.jpg" /></a>
            	</#if>
                <div class="fl ml10 tb_info">
                	<a class="tg_name" href="${basePath}/groupdetail/${group.groupId}.html">${group.groupName}</a>
                    <p>本小组共有${(group.groupmember)!''}个成员</p>
                </div><!--/tb_info-->
            </div><!--/topic_group-->
            
            <#if othertopics?size gt 0>
            <div class="other_topic mt30">
            	<h4 class="m_tit">其他话题</h4>
                <div class="otp_wp mt20">
                	<ul class="otp_list">
	                      <#list othertopics as topic>
	                       <li>
	                    	  <a class="otp_cont" href="${basePath}/topicdetail/${topic.groupId}-${topic.topicId}.html">${topic.topicTitle}</a>
	                          <div class="otp_info clearfix"><a class="fl" href="${basePath}/customerhomepage/${topic.customerId}.html">${topic.customerName}</a>
	                          <span class="fr">${(topic.topicCreateTime)?string('yyyy-MM-dd HH:mm:ss')}</span></div>
	                       </li>
	                      </#list>
                    </ul><!--/otp_list-->
                </div><!--/otp_wp-->
            </div><!--/other_topic-->
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
 <div class="mask1"></div>
      <div class="dialog1 dia1"  style="width:300px; height:120px;">
    	<a class="close" href="javascript:cls1();"></a> 
        <h4>提示</h4>
        <div class="create_albums mt20 clearfix"  style="text-align:center;">
        	<span id="message"></span>
        </div><!--/create_albums-->
    </div><!--/dialog1-->


<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/social/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath}/js/slides.min.jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.jqtransform.js"></script>
<script type="text/javascript" src="${basePath}/js/topic/topic_detail.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}/js/plugin/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}/js/plugin/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript">
	$(function() {
		$(".rowElem").jqTransform();
		$("#toc img ").css("max-width","700px");  
		$("#toc p ").css("padding-top","5px"); 
	});
	
	$(function(){ 
      KindEditor.ready(function(K) {
      editor= K.create('#replyContent', {
    			items : ['emoticons','image','multiimage'],
    			pasteType:1,
    			cssPath : '${basePath}/js/plugin/kindeditor/plugins/code/prettify.css',
    			uploadJson : '${basePath}/js/plugin/kindeditor/jsp/upload_json.jsp',
    			allowFileManager : true,
    			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['example'].submit(); 
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
			  }
    		});
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


