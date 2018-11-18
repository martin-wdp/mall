<#include "../include/common.ftl">
<@htmlHead title="商品评价-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<style>
    .text_area{width:600px;height:100px;border-top:1px solid #ABADB3;border-left:1px solid #ABADB3;border-bottom:1px solid #E3E9EF;border-right:1px solid #E3E9EF;padding:5px;}
</style>
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#assign basePath=request.contextPath>
<base href="${basePath}/">
<title>商品评价-${topmap.systembase.bsetName}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="Keywords" content="${topmap.seo.meteKey}">
<meta name="description" content="${topmap.seo.meteDes}">
<meta name="renderer" content="webkit">
<#if (topmap.systembase.bsetHotline)??>
	<link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
	<link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<style>
.text_area{width:600px;height:100px;border-top:1px solid #ABADB3;border-left:1px solid #ABADB3;border-bottom:1px solid #E3E9EF;border-right:1px solid #E3E9EF;padding:5px;}
</style>
</head>-->
<@htmlBody>
	<#--一引入头部 <#include "/index/topnew.ftl" /> -->	
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
		<div class="container">
		<input type="hidden" value="${token!''}" id="hi_token" />
	<div class="location">
        	<a href="${basePath}/customer/index.html"><strong>会员中心</strong></a><span>&gt;</span>
            <a href="${basePath}/customer/myorder.html">消息中心</a><span>&gt;</span>
            <span>商品评价</span>
    </div>
	<#include "leftmenu.ftl" />
    <div class="member_right fl ml10">
    	<div class="memebr_title mb20">
               <h2 class="f14 fb">商品评价</h2>
        </div>
        <div class="evaluate_goods">
        	<table class="common_table">
            	<thead>
                	<tr>
                    	<th >商品信息</th>
                        <th >购买时间</th>
                        <th >评论内容</th>
                    </tr>
                </thead>
                <tbody>
                <#if pb.list?size!=0>
	                <#list pb.list as comm>
	                	<tr>
	                    	<td>
	                        	<a class="img fl" target="_blank" href="${basePath}/item/${(comm.goodsId)!''}.html"><img alt="${(comm.goodsName)!'已下架'}" src="${(comm.goodsImg)!''}" width="60" height="60" /></a>
	                            <p class="name fl ml10"><a target="_blank" href="${basePath}/item/${(comm.goodsId)!''}.html">
	                             <#if  comm.goodsName?length gt 18>
	                        				 ${comm.goodsName?substring(0,18)}
	                            <#else>
	                        				${(comm.goodsName)!'已下架'}
	                        	</#if>
	                            </a></p>
	                        </td>
	                        <td>
	                        	<p><#if comm.publishTime??>${comm.publishTime?string("yyyy-MM-dd")}</#if></p>
	                        	<p><#if comm.publishTime??>${comm.publishTime?string("HH:mm:ss")}</#if></p>
	                        </td>
	                         <td>
	                         	<p>${(comm.commentContent)!''}</p>
	                        </td>
	                    </tr>
	                    </#list>
                    <#else>
                    	 <tr>
	                         <td colspan="3" style="text-align:center;">暂无商品评价！</td>
	                     </tr>
                    </#if>
                </tbody>
            </table>
            <#if (pb.list?size!=0)>
	             <#-- 分页 -->
	             <#import "../pagination/pageBean.ftl" as page>
	             <@page.pagination pb />
            </#if>
        </div><!-- /evaluate_goods -->  <!-- /common_form --><!-- /evaluate_box -->
        <div class="prompt mt20">
        	<h2>评价</h2>
            <div class="body">
            	<h4>评价说明：</h4>
            	<#if explain??>
            		<#list "${explain.content}"?split("|") as con>
					<p>${con_index + 1}.${con}</p>
					</#list>
            	</#if>
            </div>
        </div><!-- /prompt -->
    </div>
    <div class="cb"></div>
</div>
<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
<#--引入底部 <#include "/index/bottom.ftl" /> -->
    <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/member.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/customer_share.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('.item_title').each(function(){
		$(this).click(function(){
			$(this).next().toggle('fast',function(){
				if($(this).is(':visible')){
					$(this).prev().removeClass('up');
					$(this).prev().addClass('down');
				}
				else{
					$(this).prev().removeClass('down');
					$(this).prev().addClass('up');
				}
			});
		});
	});
    $(".memeber_left div:eq(3) ul li:eq(0)").addClass("cur");
    $(".pro_sort").addClass("pro_sort_close");
});
</script>
</@htmlBody>
