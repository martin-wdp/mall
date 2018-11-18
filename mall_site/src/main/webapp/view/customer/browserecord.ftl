<#include "../include/common.ftl">
<@htmlHead title="浏览历史-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>浏览历史-${topmap.systembase.bsetName}</title>
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
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
</head>-->
<@htmlBody>
    <input type="hidden" value="${token!''}" id="hi_token" />
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
            <a href="${basePath}/customer/myorder.html">订单中心</a><span>&gt;</span>
            <span>浏览历史</span>
        </div>
        <div class="member_box mb20">
            <#include "leftmenu.ftl" >
            <div class="member_right fl ml10">
                <div class="memebr_title mb20">
                    <h2 class="f14 fb">浏览历史</h2>
                </div>
                <div class="timeline">
                <#list browses as browse>
	                <#if browse.goods?? >
	                	<div class="line_item">
	                    	<div class="time_date fl">
	                        	<p>
	                        	<#assign year=(browse.createTime?string('yyyy'))?number />
	                        	<#assign month=(browse.createTime?string('MM'))?number />
	                        	<#assign day=(browse.createTime?string('dd'))?number />
	                        	<#assign year1=(today?string('yyyy'))?number />
	                        	<#assign month1=(today?string('MM'))?number />
	                        	<#assign day1=(today?string('dd'))?number />
	                        	<#if year == year1>
	                        		<#if month == month1>
	                        			<#if day == day1>
	                        				今天
	                        				<br />${browse.createTime?string('HH:mm')}</p>
	                        			<#elseif (day - day1 = -1)>
	                        				昨天
	                        				<br />${browse.createTime?string('HH:mm')}</p>
	                        			<#else>
	                        				${browse.createTime?string('MM-dd')}
	                        				<br />${browse.createTime?string('HH:mm')}</p>
	                        			</#if>
	                        		<#else>
	                        			${browse.createTime?string('MM-dd')}
	                        			<br />${browse.createTime?string('HH:mm')}</p>
	                        		</#if>
	                        	<#else>
	                        		${browse.createTime?string('yyyy-MM-dd')}
	                        		<br />${browse.createTime?string('HH:mm')}</p>
	                        	</#if>
	                        </div>
	                        <div class="line_icon fl">
	                        </div>
	                        <div class="history_goods fl">
	                            <div class="img fl">
	                                <a href="${basePath}/item/${browse.goodsId}.html"><img style="width:150px;height:150px;" alt="<#if browse.goods.goodsName??> ${browse.goods.goodsName}</#if>" src="<#if browse.goods.goodsImg??> ${browse.goods.goodsImg}</#if>" /></a>
	                            </div>
	                            <div class="name fl">
	                                <h5><a class="f14 fb" href="${basePath}/item/${browse.goodsId}.html"><#if browse.goods.goodsName??> ${browse.goods.goodsName}</#if></a></h5>
	                                <p>
	                                    <span class="light">销售价：</span> 
	                                    <span class="f14 red fb" style="font-family: '微软雅黑'">￥${browse.goods.goodsPrice?string('0.00')}</span>
	                                </p>
	                                <p>
	                                    <span class="light">市场价：</span>
	                                    <span class="line_through light" style="font-family: '微软雅黑'">￥${browse.goods.goodsMarketPrice?string('0.00')}</span>
	                                </p>
	                            </div>
	                            <div class="actions fl">
	                                <a class="cart_btn add_car" href="javascript:void(0)" onclick="buy(this)">加入购物车</a>
	                                <input type="hidden" value="${browse.goodsId}" />
	                                <a class="collect_btn" href="javascript:void(0)" onclick="addattr(this,${browse.goodsId})">加收藏</a>
	                                <a class="hidden" href="javascript:void(0)" onclick="cancelbrowse('${basePath}/customer/cancelbrowse-${browse.likeId}.html')">清除该条记录</a>
	                            </div>
	                        </div>
	                        <div class="cb"></div>
	                    </div>
	                    </#if>
                    </#list>
                </div><!-- /timeline -->
            </div><!-- END OF member_right -->
            <div class="cb"></div>
        </div><!-- END OF member_box -->
    </div>
     <div class="dialog s_dia dia2">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" alt="" id="f_img" src="${basePath}/images/q_mark.png" />
            	<em id="con_00"></em>
            </div>
            <div class="dia_ops mt20 tr">       
                <a class="go_pay" href="javascript:cls();">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
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
$(".pro_sort").addClass("pro_sort_close");
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
    
    $(".memeber_left div:eq(0) ul li:eq(5)").addClass("cur");
    
});
</script>
</@htmlBody>
