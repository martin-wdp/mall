<#include "../include/common.ftl">
<@htmlHead title="我的投诉-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css" />
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
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css" />
    <script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
</head>-->
<@htmlBody>
<#--一引入头部 <#include "/index/topnew.ftl" />  -->
<#if (topmap.temp)??>
    <#if (topmap.temp.tempId==8)>
        <#include "../index/newtop3.ftl">
    <#elseif (topmap.temp.tempId==9)>
        <#include "../index/newtop4.ftl">
    <#elseif (topmap.temp.tempId==10)>
        <#include "../index/newtop7.ftl">
    <#elseif (topmap.temp.tempId==11)>
        <#include "../index/newtop6.ftl">
    <#elseif (topmap.temp.tempId==12)>
        <#include "../index/newtop7.ftl">
    <#elseif (topmap.temp.tempId==13)>
        <#include "../index/newtop8.ftl">
    <#elseif (topmap.temp.tempId==14)>
        <#include "../index/newtop9.ftl">
    <#elseif (topmap.temp.tempId==15)>
        <#include "../index/newtop8.ftl">
    <#elseif (topmap.temp.tempId==16)>
        <#include "../index/newtop11.ftl">
    <#elseif (topmap.temp.tempId==17)>
        <#include "../index/newtop12.ftl">
    <#elseif (topmap.temp.tempId==18)>
        <#include "../index/newtop13.ftl">
    <#elseif (topmap.temp.tempId==19)>
        <#include "../index/newtop14.ftl">
    <#elseif (topmap.temp.tempId==20)>
        <#include "../index/newtop15.ftl">
    <#else>
        <#include "../index/newtop.ftl"/>
    </#if>
</#if>

<#include "newtop.ftl"/>
        <div style="background: #f5f5f5;">
            <div class="container clearfix pt20 pb10">
                <!--new_member_left-->
                <#include "newleftmenu.ftl"/>
                <div class="new_member-right">
                    <div class="new_order_list">
                        <div class="n-title">我的投诉</div>
                        <div class="tagMenu order-menu">
                            <ul class="menu clearfix">
                                <li <#if id=='0'>class="current"</#if> onclick="showleft()">选择订单</li>
                                <li  <#if id=='1'>class="current"</#if>onclick="showright()">我的投诉记录</li>
                            </ul>
                        </div>
                        <#--<div class="simple mt10 clearfix">-->
                            <#--<div class="fl">-->
                                <#--<select>-->
                                    <#--<option>最近三个月</option>-->
                                <#--</select>-->
                            <#--</div>-->
                        <#--</div>-->
                        <div class="content">
                            <#if id=='0'>
                            <div class="layout">
                                <table class="bought-table mt10">
                                    <thead>
                                    <tr>
                                        <th>订单编号</th>
                                        <th width="480">订单商品</th>
                                        <th>下单时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#if (pb.list?size!=0)>
                                            <#list pb.list as order>
                                                <tr class="order-bd">
                                                    <td>
                                                        <#if order.orderNo??>
													    	${order.orderNo}
													    </#if>
                                                    </td>
                                                    <td>
                                                        <#list order.goods as good>
                                                            <#if good_index<3>
                                                            <a title="${(good.goodsName)!''}" href="${basePath}/item/${(good.goodsId)!''}.html" class="fl"><img width="100" height="100" alt="${(good.goodsName)!''}" src="${(good.goodsImg)!''}" /></a>
                                                            </#if>
                                                        </#list>
                                                    </td>
                                                    <td>
                                                        <#if order.addTime??>
                                                        ${order.addTime?string("yyyy-MM-dd HH:mm:ss")?substring(0,10)}
                                                            <br/>
                                                        ${order.addTime?string("yyyy-MM-dd HH:mm:ss")?substring(11)}
                                                        </#if>
                                                    </td>
                                                    <td>
                                                        <a href="${basePath}/customer/tocomplain-${order.orderNo}.html" class="buy-again">我要投诉</a><br/>
                                                    </td>
                                                </tr>
                                            </#list>
                                    <#else>
                                    <tr>
                                        <td colspan="4" style="font-size:18px; height:60px;text-align: center;">暂无订单！</td>
                                    </tr>
                                    </#if>


                                    </tbody>

                                </table>
                                <div class="paging_area">
                                        <#if pb.list?? && (pb.list?size!=0)>
                                        <#-- 分页 -->
                                            <#import "../pagination/pageBean.ftl" as page>
                                            <@page.pagination pb />
                                        </#if>
                                </div>
                            </div>
                            </#if>
                                <#if id=='1'>
                                    <div class="layout">
                                        <table class="bought-table mt10">
                                            <thead>
                                            <tr>
                                                <th width="285">投诉内容</th>
                                                <th width="110">投诉类型</th>
                                                <th>涉及订单</th>
                                                <th>投诉时间</th>
                                                <th>回复或处理</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <#if (pb.list?size!=0)>
                                                <#list pb.list as com>
                                                    <tr class="order-bd">
                                                        <td style="text-align: left">
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
                                                            <#if com.complainTime??>
                                                            ${com.complainTime?string("yyyy-MM-dd HH:mm:ss")?substring(0,10)}
                                                                <br/>
                                                            ${com.complainTime?string("yyyy-MM-dd HH:mm:ss")?substring(11)}
                                                            </#if>
                                                        </td>
                                                        <td>
                                                            <#if com.replayContext??>
                                                                <span class="red">商城回复:${com.replayContext}</span>
                                                            <#else>
                                                                投诉处理中...
                                                            </#if>
                                                        </td>
                                                    </tr>
                                                </#list>
                                            <#else>
                                            <tr>
                                                <td colspan="5"  style="font-size:18px; height:60px;text-align: center;">暂无投诉记录！</td>
                                            </tr>
                                            </#if>
                                            </tbody>

                                        </table>
                                        <div class="paging_area">
                                        <#if pb.list?? && (pb.list?size!=0)>
                                                <#-- 分页 -->
                                                    <#import "../pagination/pageBean.ftl" as page>
                                                    <@page.pagination pb />
                                                </#if>
                                        </div>
                                    </div>
                               </#if>
                        </div>

                    </div>
                </div>
            </div>
        </div>

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
<script type="text/javascript" src="${basePath}/js/newapp.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/findcode.js"></script>
<script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
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
    $(".new_member_left div:eq(2) ul li:eq(1)").addClass("cur");
    $(".pro_sort").addClass("pro_sort_close");

});
function showright(){
	location.href="../customer/complainlist.html"
}
function showleft(){
	location.href="../customer/ordercomplain.html";
}
</script>
</@htmlBody>
