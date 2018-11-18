<#include "../include/common.ftl">
<@htmlHead title="${topmap.seo.mete!''}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css" />
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
                        <div class="n-title">我的积分</div>
                        <div class="ex-bar-total">
                            您当前的积分：<span><#if customer.infoPointSum??> ${customer.infoPointSum}</#if></span>
                        </div>
                        <div class="tagMenu order-menu">
                            <ul class="menu clearfix">
                                <li class="current">积分记录</li>
                            </ul>
                        </div>

                        <div class="content">
                            <div class="layout">
                                <table class="bought-table mt10">
                                    <thead>
                                    <tr>
                                        <th>日期</th>
                                        <th>获取积分</th>
                                        <th>消耗积分</th>
                                        <th>详细说明</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <#if (pb.list?size!=0)>
                                            <#list pb.list as point>
                                            <#if point.point!=0 && point.point??>
                                            <tr class="order-bd">
                                                <td>
                                                    <#if point.createTime??>
													${point.createTime?string("yyyy-MM-dd HH:mm:ss")}
												</#if>
                                                </td>
                                                <#if point.pointType??>
                                                    <#if point.pointType=='1'>
                                                        <td>
															${point.point}
                                                        </td>
                                                        <td>-</td>
                                                    <#else>
                                                        <td>-</td>
                                                        <td>
															${point.point}
                                                        </td>
                                                    </#if>
                                                </#if>
                                                <td>
                                                    <#if point.pointDetail??>
													${point.pointDetail}
												</#if>
                                                </td>
                                            </tr>
                                            </#if>
                                            </#list>
                                        <#else>
                                            <tr>
                                                <td colspan="4" style="font-size:18px; height:60px; text-align:center;">暂无积分记录！</td>
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
                        </div>

                    </div>
                </div>
            </div>
        </div>

        </div><!-- END OF guess_goods -->
    </div>
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
<script type="text/javascript" src="${basePath}/js/newapp.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/findcode.js"></script>
<script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
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
    $(".new_member_left div:eq(3) ul li:eq(3)").addClass("cur");
    $(".pro_sort").addClass("pro_sort_close");
});
</script>
</@htmlBody>
