<#include "../include/common.ftl">
<@htmlHead title="查看评价-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<style>
    .star {float:none!important; display:inline-block!important; zoom:1; *display:inline;}
</style>
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查看评价-${topmap.systembase.bsetName}</title>
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
<style>
	.star {float:none!important; display:inline-block!important; zoom:1; *display:inline;}
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
        <div class="location">
        	<a href="${basePath}/customer/index.html"><strong>会员中心</strong></a><span>&gt;</span>
            <a href="${basePath}/customer/myorder.html">订单中心</a><span>&gt;</span>
            <span>查看评价</span>
        </div>
        <div class="member_box mb20">
            <#include "leftmenu.ftl" >
            <div class="member_right fl ml10">
                <div class="memebr_title mb20">
                    <h2 class="f14 fb">查看评价</h2>
                </div>
                <div class="server_form switch_border mt10">
                	<h5>查看评论信息：</h5>
                    <div class="body">
                    	<dl>
                        	<dt>商品编号：</dt>
	                        <dd id="od">${(good.goodsNo)!'&nbsp;'}</dd>
	                        <dt>商品名称：</dt>
	                        <dd id="od">${(good.goodsName)!''}</dd>
                        </dl>
                        <#if (good.commentTag)??>
                        <#assign tags=good.commentTag?split(',')  />
                        	
                        	<dl>
	                        	<dt>标&nbsp; &nbsp; &nbsp; 签：</dt>
	                            <dd>
	                            	<#list tags as tag >
		                        		<label style="background: lightgray;padding:2px 5px;">${tag}</label>
		                        	</#list>
	                            </dd>
	                        </dl>
                        </#if>
                        <dl>
                        	<dt>商品评分：</dt>
                            <dd>
                            	<label><span class="star star_${(comment.commentScore)!'0'} vm"></span></label>
                            </dd>
                        </dl>
                        <dl>
                            <dt>评论内容：</dt>
                            <dd>
                                <p>${(comment.commentContent)!''}</p>
                            </dd>
                        </dl>
                    </div>
                </div><!-- /server_form -->
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
    $(".memeber_left div:eq(0) ul li:eq(0)").addClass("cur");
    $(".pro_sort").addClass("pro_sort_close");
});
</script>
</@htmlBody>
