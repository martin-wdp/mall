<#include "../include/common.ftl">
<@htmlHead title="${topmap.seo.mete!''}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我的投诉-${topmap.systembase.bsetName}</title>
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
            <a href="${basePath}/customer/refundlist.html">客户服务</a><span>&gt;</span>
            <span>我的投诉</span>
        </div>
        <div class="member_box mb20">
            <#include "leftmenu.ftl" >
            <div class="member_right fl ml10">
                <div class="memebr_title mb20">
                    <h2 class="f14 fb">我的投诉</h2>
                </div>
                <div class="order_list switch_border">
                    <div class="tagMenu">
                        <ul class="menu">
                           <li onclick="showleft()"><em></em><span></span>选择订单</li>
                           <li onclick="showright()" ><em></em><span></span>我的投诉记录</li>
                        </ul>
                    </div>
                    <div class="content">
                        <div class="layout">
                        	<#if (id=='0')>
                            <table class="collection_goods common_table">
                                <tr>
                                    <th width="100">订单编号</th>
                                    <th>订单商品</th>
                                    <th width="110">下单时间</th>
                                    <th width="100">操作</th>
                                </tr>
	                                <#if (pb.list?size!=0)>
		                                <#list pb.list as order>
			                                <tr>
			                                    <td>
			                                    	<#if order.orderNo??>
														${order.orderNo}
													 </#if>
			                                    </td>
			                                    <td >
				                                    <#list order.goods as good>
				                                    	<a title="${(good.goodsName)!''}" href="${basePath}/item/${(good.goodsId)!''}.html" class="fl"><img width="50" height="50" alt="${(good.goodsName)!''}" src="${(good.goodsImg)!''}" /></a>
				                                    </#list>
			                                    </td>
			                                    <td>
			                                    	<font color="grey">
				                                    	<#if order.addTime??>
																${order.addTime?string("yyyy-MM-dd HH:mm:ss")?substring(0,10)}
				                                    		<br/>
				                                    		${order.addTime?string("yyyy-MM-dd HH:mm:ss")?substring(11)}
														</#if>
			                                    	</font>
			                                    </td>
			                                    <td>
			                                        <a class="member_common_btn" href="${basePath}/customer/tocomplain-${order.orderNo}.html">我要投诉</a>
			                                    </td>
		                                </#list>
	                                <#else>
		                                <tr>
		                                    <td colspan="7">暂无订单！</td>
		                                </tr>
	                                 </#if>
	                            </table>
	                          	<#if (pb.list?size!=0)>
		                            <#-- 分页 -->
		                            <#import "../pagination/pageBean.ftl" as page>
		                            <@page.pagination pb />
	                            </#if>
                            </#if>
                        </div>
                        <div class="layout">
                        	<#if (id=='1')>
                            <table class="returns common_table">
                                <tr>
                                    <th width="250">投诉内容</th>
                                    <th width="70">投诉类型</th>
                                    <th width="90">涉及订单</th>
                                    <th width="110">投诉时间</th>
                                    <th>回复或处理</th>
                                </tr>
                                 </tr>
	                               
		                                <#if (pb.list?size!=0)>
			                                <#list pb.list as com>
				                                <tr>
					                                <td>
					                                	<#if com.complainContext??>
															${com.complainContext}
														</#if>
					                                </td>
					                                <td>
					                                	<#if com.complainType??>
															${com.complainType}
														</#if>
					                                </td>
					                                <td>
					                                	<#if com.orderNo??>
															${com.orderNo}
														</#if>
					                                </td>
					                                <td>
					                                	<font color="grey">
					                                    	<#if com.complainTime??>
																	${com.complainTime?string("yyyy-MM-dd HH:mm:ss")?substring(0,10)}
					                                    		<br/>
					                                    		${com.complainTime?string("yyyy-MM-dd HH:mm:ss")?substring(11)}
															</#if>
				                                    	</font>
					                                </td>
					                                 <td>
														<#if com.replayContext??>
															<font color="#ff6600">商城回复:${com.replayContext}</font>
														<#else>
															投诉处理中...
														</#if>
					                                </td>
				                                </tr>
			                                </#list>
		                                <#else>
			                                <tr>
	                                    		<td colspan="5">暂无投诉记录！</td>
	                                		</tr>
		                                 </#if>
		                            </table>
		                          	<#if (pb.list?size!=0)>
			                            <#-- 分页 -->
			                            <#import "../pagination/pageBean.ftl" as page>
			                            <@page.pagination pb />
		                            </#if>
	                            </#if>
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
    $(".memeber_left div:eq(1) ul li:eq(1)").addClass("cur");
    $(".pro_sort").addClass("pro_sort_close");
   if(${id}=="1"){
   		
   	 	$(".layout:eq(0)").hide();
   		$(".layout:eq(1)").show();
   		$(".menu li:eq(1)").addClass("current");
   		$(".menu li:eq(0)").removeClass("current");
   }
});
function showright(){
	location.href="../customer/complainlist.html"
}
function showleft(){
	location.href="../customer/ordercomplain.html";
}
</script>
</@htmlBody>
