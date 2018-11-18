<#include "../include/common.ftl">
<@htmlHead title="我的积分-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/member.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我的积分-${topmap.systembase.bsetName}</title>
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
<link rel="stylesheet" type="text/css" href="${basePath}/css/member.css" />
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
            <a href="${basePath}/customer/myinfo.html">账户中心</a><span>&gt;</span>
            <span>我的积分</span>
        </div>
        <div class="member_box mb20">
        	<#include "leftmenu.ftl" />
            <div class="member_right fl ml10">
                <div class="memebr_title mb20">
                    <h2 class="f14 fb">我的积分</h2>
                </div>
                <div class="tips">
                	<p>您当前的积分：<span class="f16 fb red"><#if customer.infoPointSum??> ${customer.infoPointSum}</#if></span></p>
                </div>
                <!--2-->
                <div class="order_list switch_border">
                    <div class="tagMenu">
                        <ul class="menu">
                           <li><em></em><span></span>积分记录</li>
                        </ul>
                    </div>
                    <div class="list_filter">
                        <div class="filter_l fl">
                            <select class="select selUse" onchange="onselectMonth(this)">
                                <option selected="selected" value="1">近三个月记录</option>
                                <option value="2" >三个月前记录</option>
                            </select>
                        </div>
                        <div class="cb"></div>
                    </div>
                    <div class="content">
                        <div class="layout">
                            <table class="orders common_table">
                                <tr>
                                    <th width="20%">日期</th>
                                    <th width="20%">获取积分</th>
                                    <th width="20%">消耗积分</th>
                                    <th width="40%">详细说明</th>
                                </tr>
                                <#if (pb.list?size!=0)>
			                    	<#list pb.list as point>
			                    		<tr>
				                        	<td>	
				                        		<#if point.createTime??>
													${point.createTime?string("yyyy-MM-dd HH:mm:ss")}
												</#if>
				                        	</td>
				                        	<#if point.pointType??>
				                            	<#if point.pointType=='1'>
													<td>
														<#if point.point??>
															${point.point}
														</#if>
													</td>
			                            			<td>-</td>
												<#else>
													<td>-</td>
			                            			<td>
														<#if point.point??>
															${point.point}
														</#if>
													</td>
												</#if>
											</#if>
				                            <td>
				                           		<#if point.pointDetail??>
													${point.pointDetail}
												</#if>
				                            </td>
			                        	</tr>
			                    	</#list>
			                    <#else>
			                    	<tr>
	                                    <td colspan="4">暂无积分记录！</td>
	                                </tr>
			                    </#if>
                            </table>
                             <#if (pb.list?size!=0)>
	                            <#-- 分页 -->
	                            <#import "../pagination/pageBean.ftl" as page>
	                            <@page.pagination pb />
                            </#if>
                        </div>
                    </div>
                </div>
            </div><!-- END OF member_right -->
            <div class="cb"></div>
        </div><!-- END OF member_box -->
            </div>
        </div><!-- END OF guess_goods -->
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
    <input id="datehidden" type="hidden" value="${date}" /> 
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/customer.js"></script>
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
	$(".guess_goods_list").jCarouselLite({
        btnNext: ".next",
        btnPrev: ".prev",
		visible : 6,
		auto : 2000,
		speed : 800
    });
    if($("#datehidden").val()=="1"){
    	$(".selUse option[value='1']").prop("selected","selected"); 
    }else{
    	$(".selUse option[value='2']").prop("selected","selected"); 
    }
    $(".memeber_left div:eq(2) ul li:eq(3)").addClass("cur");
    $(".pro_sort").addClass("pro_sort_close");
});
</script>
</@htmlBody>
