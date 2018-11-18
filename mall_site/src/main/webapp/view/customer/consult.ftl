<#include "../include/common.ftl">
<@htmlHead title="购买咨询-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>购买咨询-${topmap.systembase.bsetName}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="Keywords" content="${topmap.seo.meteKey}">
<meta name="description" content="${topmap.seo.meteDes}">
<#assign basePath=request.contextPath>
<#if (topmap.systembase.bsetHotline)??>
	<link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
	<link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
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
        <div class="location">
        	<a href="${basePath}/customer/index.html"><strong>会员中心</strong></a><span>&gt;</span>
            <a href="${basePath}/customer/consult.html">消息中心</a><span>&gt;</span>
            <span>购买咨询</span>
        </div> 
        <div class="member_box mb20">
            <#include "leftmenu.ftl" >
            <div class="member_right fl ml10">
                <div class="memebr_title mb20">
                    <h2 class="f14 fb">购买咨询</h2>
                </div>
                <div class="order_list switch_border">
                    <div class="tagMenu">
                        <ul class="menu">
                           <li onclick="showcon();"><em></em><span></span>全部咨询</li>
		                   <li onclick="showyes();"><em></em><span></span>已回复咨询</li>
		                   <li onclick="showno();"><em></em><span></span>未回复咨询</li>
                        </ul>
                    </div>
                    <div class="content">
                        <div class="layout">
                            <table class="collection_goods common_table">
                                 <tr>
		                            <th width="30%">商品信息</th>
		                            <th width="45%">咨询内容及回复</th>
		                            <th width="25%">最后更新时间</th>
		                        </tr>
		                        <#if flag == '2'>
		                          <#if (pb.list?size!=0)>
		                                <#list pb.list as con>
			                                <tr>
			                                    <td>
			                                    	 <a style="padding-left:10px;float:left;" title="${(con.goodsName)!''}"  href="${basePath}/item/${con.goodsId}.html">
					                                    <img width="50" height="50" alt="${(con.goodsName)!''}" src="${(con.goodsImg)!''}" />
					                                </a>
					                                <div style="padding-left:10px;float:left;height:60px;text-align:center;line-height:50px;">
														<p>
															<a title="${(con.goodsName)!''}" href="${basePath}/item/${con.goodsId}.html" target="_blank" >
																<#if (con.goodsName?length > 20) >
																	${con.goodsName?substring(1,19)}...
																<#else>
																	${(con.goodsName)!''}
																</#if>
															</a>
														</p>
													</div>
			                                    </td>
			                                    <td>
				                                     <p class="tl">咨询内容：${(con.commentContent)!''}</p>
				                                      <#list con.replays as rep>
                                					 		<p class="red tl">商城回复：${rep.commentContent}</p>
                                					  </#list>
			                                    </td>
			                                    <td>
			                                    	 <p>${con.publishTime?string("yyyy-MM-dd HH:mm:ss")}</p>
                                					 
                                					  <#list con.replays as rep>
                                					 		<p class="red">${rep.publishTime?string("yyyy-MM-dd HH:mm:ss")}</p>
                                					  </#list>
			                                    </td>
		                                </#list>
	                                <#else>
		                                <tr>
		                                    <td colspan="7">暂无咨询！</td>
		                                </tr>
	                                 </#if>
	                            </table>
	                          	<#if (pb.list?size!=0)>
		                            <#-- 分页 -->
		                            <#import "../pagination/pageBean.ftl" as page>
		                            <@page.pagination pb />
	                            </#if>
	                          </#if>
	                        </table>
                        </div>
                        <div class="layout">
                            <table class="returns common_table">
                                <tr>
		                            <th width="30%">商品信息</th>
		                            <th width="45%">咨询内容及回复</th>
		                            <th width="25%">最后更新时间</th>
		                        </tr>
		                        <#if flag == '1'>
	                               <#if (pb.list?size!=0)>
		                                <#list pb.list as con>
			                                <tr>
			                                    <td>
			                                    	 <a style="padding-left:10px;float:left;" title="${(con.goodsName)!''}"  href="${basePath}/item/${con.goodsId}.html">
					                                    <img width="50" height="50" alt="${(con.goodsName)!''}" src="${(con.goodsImg)!''}" />
					                                </a>
					                                <div style="padding-left:10px;float:left;height:60px;text-align:center;line-height:50px;">
														<p>
															<a title="${(con.goodsName)!''}" href="${basePath}/item/${con.goodsId}.html" target="_blank" >
																<#if (con.goodsName?length > 20) >
																	${con.goodsName?substring(1,19)}...
																<#else>
																	${(con.goodsName)!''}
																</#if>
															</a>
														</p>
													</div>
			                                    </td>
			                                    <td>
				                                     <p class="tl">咨询内容：${(con.commentContent)!''}</p>
				                                      <#list con.replays as rep>
                                					 		<p class="red tl">商城回复：${rep.commentContent}</p>
                                					  </#list>
			                                    </td>
			                                    <td>
			                                    	 <p>${con.publishTime?string("yyyy-MM-dd HH:mm:ss")}</p>
                                					 
                                					  <#list con.replays as rep>
                                					 		<p class="red">${rep.publishTime?string("yyyy-MM-dd HH:mm:ss")}</p>
                                					  </#list>
			                                    </td>
		                                </#list>
	                                <#else>
		                                <tr>
		                                    <td colspan="7">暂无咨询！</td>
		                                </tr>
	                                 </#if>
	                            </table>
	                          	<#if (pb.list?size!=0)>
		                            <#-- 分页 -->
		                            <#import "../pagination/pageBean.ftl" as page>
		                            <@page.pagination pb />
	                            </#if>
	                          </#if>
		                   </table>
                        </div>
                        <div class="layout">
                            <table class="returns common_table">
                                <tr>
		                            <th width="30%">商品信息</th>
		                            <th width="45%">咨询内容及回复</th>
		                            <th width="25%">最后更新时间</th>
		                        </tr>
		                        <#if flag == '0'>
	                               <#if (pb.list?size!=0)>
		                                <#list pb.list as con>
			                                <tr>
			                                    <td>
			                                    	 <a style="padding-left:10px;float:left;" title="${(con.goodsName)!''}"  href="${basePath}/item/${con.goodsId}.html">
					                                    <img width="50" height="50" alt="${(con.goodsName)!''}" src="${(con.goodsImg)!''}" />
					                                </a>
					                                <div style="padding-left:10px;float:left;height:60px;text-align:center;line-height:50px;">
														<p>
															<a title="${(con.goodsName)!''}" href="${basePath}/item/${con.goodsId}.html" target="_blank" >
																<#if (con.goodsName?length > 20) >
																	${con.goodsName?substring(1,19)}...
																<#else>
																	${(con.goodsName)!''}
																</#if>
															</a>
														</p>
													</div>
			                                    </td>
			                                    <td>
				                                     <p class="tl">咨询内容：${(con.commentContent)!''}</p>
				                                      <#list con.replays as rep>
                                					 		<p class="red tl">商城回复：${rep.commentContent}</p>
                                					  </#list>
			                                    </td>
			                                    <td>
			                                    	 <p>${con.publishTime?string("yyyy-MM-dd HH:mm:ss")}</p>
                                					 
                                					  <#list con.replays as rep>
                                					 		<p class="red">${rep.publishTime?string("yyyy-MM-dd HH:mm:ss")}</p>
                                					  </#list>
			                                    </td>
		                                </#list>
	                                <#else>
		                                <tr>
		                                    <td colspan="7">暂无咨询！</td>
		                                </tr>
	                                 </#if>
	                            </table>
	                          	<#if (pb.list?size!=0)>
		                            <#-- 分页 -->
		                            <#import "../pagination/pageBean.ftl" as page>
		                            <@page.pagination pb />
	                            </#if>
	                         </#if>
		                   </table>
                        </div>
                    </div>
                </div>
            </div><!-- END OF member_right -->
            <div class="cb"></div>
        </div><!-- END OF member_box -->
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
<script type="text/javascript" src="${basePath}/js/customer/customer.js"></script>
<script type="text/javascript">
$(".s2 option[value='"+$("#hi_type").val()+"']").prop("selected","selected"); 
$(".s1 option[value='"+$("#hi_date").val()+"']").prop("selected","selected"); 
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
	$(".guess_goods_list").jCarouselLite({
        btnNext: ".next",
        btnPrev: ".prev",
		visible : 6,
		auto : 2000,
		speed : 800
    });
    $(".memeber_left div:eq(3) ul li:eq(1)").addClass("cur");
    $(".pro_sort").addClass("pro_sort_close");
    if(${flag}=="2"){
   	 	$(".layout:eq(0)").show();
   		$(".layout:eq(1)").hide();
   		$(".layout:eq(2)").hide();
   		$(".menu li:eq(0)").addClass("current");
   		$(".menu li:eq(1)").removeClass("current");
   		$(".menu li:eq(2)").removeClass("current");
    }else if(${flag}=="1"){
    	$(".layout:eq(0)").hide();
    	$(".layout:eq(2)").hide();
   		$(".layout:eq(1)").show();
   		$(".menu li:eq(1)").addClass("current");
   		$(".menu li:eq(0)").removeClass("current");
   		$(".menu li:eq(2)").removeClass("current");
    }else if(${flag}=="0"){
    	$(".layout:eq(0)").hide();
    	$(".layout:eq(1)").hide();
   		$(".layout:eq(2)").show();
   		$(".menu li:eq(2)").addClass("current");
   		$(".menu li:eq(0)").removeClass("current");
   		$(".menu li:eq(1)").removeClass("current");
    }
});
function showcon(){
	location.href="../customer/consult.html";
}
function showyes(){
	location.href="../customer/consult-1-1.html";
}
function showno(){
	location.href="../customer/consult-0-1.html";
}
</script>
</@htmlBody>
