<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl"> 
<title>发布话题-${topmap.systembase.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/> 
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/js/plugin/kindeditor/themes/default/default.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/js/plugin/kindeditor/plugins/code/prettify.css" />

<style type="text/css">
.ke-toolbar {
    background-color: #fff!important;
    }

.ke-statusbar {
   background-color: #fff!important;
    }
    
.pub_title_a em {
	font-size: 14px;
	color: #333;
	line-height: 30px;
}
.pub_title_a span {
	color: #666;
	line-height: 30px;
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
<div class="wrapper clearfix">
  <form id="topic_form" method="post" action="">
	  <input type="hidden" id="groupId" name="groupId" value="${group.groupId}"/>
	  <#if (group.limitReplyType)=='0'>
	      <input  type="hidden" value="0" name="topicReplyFlag"/>
	  </#if>
	  <#if (group.limitReplyType)=='1'>
	      <input  type="hidden" value="1" name="topicReplyFlag"/>
	  </#if>
      <input  type="hidden" value="0" name="topicEssence"/>
      <input  type="hidden" value="0" name="topicFever"/>
      <input  type="hidden" value="0" name="topicTopView"/>
      <input  type="hidden" value="0" name="topicIndexView"/>
      <input  type="hidden" value="0" name="topicDelFlag"/>
      <input  type="hidden" value="0" name="topicHot"/>
    	<div class="left_cont fl">
        	<h3 class="m_tit">${group.groupName} 小组话题发表</h3>
            <div class="pub_title mt30 clearfix" >
            	<em class="fl">标题：</em>
                <div class="rowElem clearfix fl"><input class="pub_text" type="text" maxlength="30" onKeyUp="length30()" name="topicTitle" id="topicTitle"  placeHolder="写下你所知道的一句话技巧（要求置顶）" /></div>
                <span id="ab">可输入30字</span>
            </div><!--/pub_title-->
           <#-- <div class="pub_title_a clearfix mt10">
            	<em class="fl">话题分类：</em>
            	<div class="rowElem clearfix fl"><select name="topicCateId" style="color:#666666;" >
            	  	<option value="0">客厅</option>
					<option value="1">卧室 </option>
            	  	<option value="2">书房</option>
					<option value="3">厨房</option>
            	  	<option value="4">餐厅</option>
					<option value="5">阳台花园</option>
            	  	<option value="6">卫浴间</option>
					<option value="7">飘窗</option>
            	  	<option value="8">套房</option>
					<option value="9">其他</option>
            	</select></div>
            </div><!--/pub_title-->
            <div class="pub_wp mt20">
            	<div style="width:750px;margin:0;"><textarea class="ff_textarea" id="topicContent" name="topicContent" style="width:auto;"></textarea></div>
                <div class="dont_hh">话题内容请勿超过10000字(注:上传图片大小不超过1M)</div>
            </div><!--/pub_wp-->
            <div class="rowElem pub_btns clearfix mt15">
            	<a id ="but" onclick="pub()"><input type="button" value="发表话题" /></a>
                <a onclick="returns()"><input type="button" value="返回" /></a>
            </div><!--/rowElem-->
        </div><!--/left_cont-->
      </form>
        <#--<div class="user_detail clearfix">
            <a class="uhead fl" href="javascript:;"><img alt="" src="images/images_09.jpg" /></a>
            <div class="uinfo fl ml10 pt5">
                <a class="uname" href="javascript:;">爱果果</a>
                <p class="mt10">江苏 南京</p>
                <p class="mt10"><span>座驾：凯美瑞</span><span class="ml10">驾龄：5年</span></p>
            </div>
            <a class="attention fr" href="javascript:;">加关注</a>
        </div><!--/user_detail-->
        -
        
        <div class="right_cont fr">
        	<div class="topic_group clearfix">
            	<a class="fl" href="groupdetail/${group.groupId}.html">
            	<#if (group.groupHead)?? && (group.groupHead)!="">
	            	<img alt="${group.groupName}" src="${group.groupHead}" width="50px" height="50px"/></a>
   				<#else>        	  
	            	<img alt="${group.groupName}" src="${basePath}/images/default_head3.jpg" width="50px" height="50px"/></a>
            	</#if>
                <div class="fl ml10 tb_info">
                	<a class="tg_name" href="groupdetail/${group.groupId}.html"> 
                	<#if (group.groupName)?length gt 6>
                      ${(group.groupName[0..6])?default("")}...
              			<#else>${group.groupName}
          			 </#if> </a>
                    <p>本小组共有${group.groupmember}个成员</p>
                </div><!--/tb_info-->
            </div><!--/topic_group-->
            
            <div class="other_topic mt30">
            	<h4 class="m_tit">其他话题</h4>
                <div class="otp_wp mt20">
                	<ul class="otp_list">
                    	<#if othertopics??>
                          <#list othertopics as topic>
                           <li>
                        	  <a class="otp_cont" href="topicdetail/${topic.groupId}-${topic.topicId}.html">${(topic.topicTitle)!''}</a>
                              <div class="otp_info clearfix"><a class="fl" href="customerhomepage/${topic.customerId}.html">${topic.customerName}</a>
                              <span class="fr">${(topic.topicCreateTime)?string('yyyy-MM-dd HH:mm:ss')}</span></div>
                           </li>
                          </#list>
                        </#if>
                    </ul><!--/otp_list-->
                </div><!--/otp_wp-->
            </div><!--/other_topic-->
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
     <div class="dialog1 dia1" style="width:450px; height:170px;">
    	<a class="close" href="javascript:cls1();"></a>
        <h4>系统提示</h4>
        <p class="tc f14 mt20" style="padding:20px;" id ="message"></p>  
        <div class="tc mt10 d_lk"><a class="cancel_btn" href="javascript:cls1();">关闭</a></div>
   </div><!--/dialog-->
  
<script charset="utf-8" src="${basePath}/js/plugin/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${basePath}/js/plugin/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="${basePath}/js/plugin/kindeditor/plugins/code/prettify.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/social/default.js"></script>
<script type="text/javascript" src="${basePath}/js/slides.min.jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.jqtransform.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath}/js/topic/topic_comm.js"></script>

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


