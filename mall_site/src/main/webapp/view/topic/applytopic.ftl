<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl"> 
<title>处理话题恢复申诉-${topmap.systembase.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/> 

<style type="text/css">
    .com_plain{}
    .plain_title{ border-bottom: 2px solid #336699; padding-bottom: 10px; color:#336699;}
    .plain_title span{ color:#000; font-weight: bold;}
    .plain_left{ width: 146px; height: 144px; padding: 7px; border: 1px solid #d0d0d0; float: left;}
    .plain_rig{ width: 1020px; float: right;}
    .sandy{ font-family: "微软雅黑"; font-size: 20px; color: #323232; float: left;}
    .dispose{ padding-right: 120px;}
    .plain_body ul{ padding-top: 10px;}
    .plain_body ul li{ line-height: 30px; color: #666; font-size: 14px;}
    .plain_body ul li span{width: 105px; float: left; display: block; text-align: right; font-size: 16px; color: #666; font-weight: bold;}
    .plain_body ul li div{width: 910px; float: left;}
 .group_btn {
background: url(${basePath}/images/group_btn.gif) no-repeat;
width: 97px;
height: 34px;
border: none;
cursor: pointer;
color: #fff;
margin: 90px 0 0 0px;
font-family: "微软雅黑";
font-size: 14px;
}
 .apply_btn {
background: url(${basePath}/images/group_btn.gif) no-repeat;
width: 97px;
height: 34px;
border: none;
cursor: pointer;
color: #fff;
margin: 10px 0 0 110px;
font-family: "微软雅黑";
font-size: 14px;
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
<div class="container pt20">
    <div class="com_plain">
        <div class="plain_title">
            未处理申诉 > <span>申诉详情</span>
        </div><!--plain_title-->
        <div class="plain_detail pt10 clearfix">
            <div class="plain_left">
            <a href="${basePath}/customerhomepage/${customerId}.html" alt="${cust.customerNickname}" target="_blank">
              <#if (topic.customerHead)??>
                  <img src="${baseUrl}${cust.customerImg}" width="146px" height="144px">
              <#else>
                  <img  width="160px" height="162px" alt="${cust.customerNickname}" src="${basePath}/images/default_head2.jpg" />
               </#if>
            </a>
            </div><!--plain_left-->
          <form id="applydetail" action="">  
          <input type="hidden" value="${topic.topicId}" name="topicId">
            <div class="plain_rig">
                <div class="plain_top clearfix">
                    <div class="sandy"><a style="color:#336699;" href="${basePath}/customerhomepage/${customerId}.html" alt="${cust.customerNickname}" target="_blank">${cust.customerNickname!''}</a></div>
                    <div class="dispose fr color6 f14">
                        <span class="f16 color6">处理操作：&nbsp;&nbsp;</span><input type="radio" name="topicDelFlag" value="1"/>&nbsp;&nbsp;拒绝&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="topicDelFlag" value="0"/>&nbsp;&nbsp;恢复
                    </div>
                </div><!--plain_top-->
                <div class="plain_body">
                    <ul>
                        <li class="clearfix">
                            <span>删帖操作人：</span>
                            <div class="fl">${topic.topicDelCustomerName}</div>
                        </li>
                        <li class="clearfix">
                            <span>申诉理由：</span>
                            <div class="fl">
                            <#if (topic.topicApplyReason)==''>
                            	无
                            <#else>${(topic.topicApplyReason)!''}
                            </#if>
                            </div>
                        </li>
                        <li class="clearfix">
                            <span>帖子作者：</span>
                            <div class="fl">
                             ${topic.customerName}
                            </div>
                        </li>
                        <li class="clearfix">
                            <span>帖子内容：</span>
                            <div class="fl">
                             ${topic.topicContent}
                            </div>
                        </li>
                    </ul>
                     <a onclick="applydetail()"><input type="button" class="group_btn" value="提交"></a>
                </div><!--plain_body-->
            </div><!--plain_rig-->
          </form>  
        </div><!--plain_detail-->
    </div>
</div>

 <div class="dialog1 dia1"  style="width: 300px; height: 120px;">
    	<a class="close" href="javascript:cls1();"></a>
        <h4>系统提示</h4>
        <p class="tc f14 mt20" style="padding:20px;" id ="message"></p>   
 </div><!--/dialog-->
    
<form id="refuseapply" action="">
 <input type="hidden" value="${topic.topicId}" name="topicId">
 <input type="hidden" value="1" name="topicDelFlag">
     <div class="dialog1 dia2"  style="width: 300px; height: 180px;">
    	<a class="close" href="javascript:cls1();"></a>
        <h4>拒绝理由</h4>
        <textarea name="topicApplyRefuseReason" style="width:295px;height:100px"></textarea>
        <a onclick="refuseapply()"><input type="button" class="apply_btn" value="提交"></a>
        <p class="tc f14 mt20" style="padding:20px;" id ="message1"></p>   
    </div><!--/dialog-->
    </form>
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
<script type="text/javascript" src="${basePath}/js/topic/apply_topic.js"></script>
<script type="text/javascript">
	  $(function(){
			//选中首页社区
			 $(".sort_list li").removeClass("cur");
			 $(".sort_list li:eq(1)").addClass("cur");
         });
</script>
</body>
</html>


