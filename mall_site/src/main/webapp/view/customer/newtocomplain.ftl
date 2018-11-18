<#include "../include/common.ftl">
<@htmlHead title="${topmap.seo.mete!''}">
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
                                <li class="current">填写投诉信息</li>
                            </ul>
                        </div>
                        <div class="content">
                            <div class="layout">
                                <table class="complain-table">
                                    <tr>
                                        <td class="tr">涉及订单：</td>
                                        <td> <label id="od">${orderNo}</label></td>
                                    </tr>
                                    <tr>
                                        <td class="tr"><span>*</span>投诉类型：</td>
                                        <td>
                                            <select  id="complainty" class="select">
                                                <option>产品相关</option>
                                                <option>价格相关</option>
                                                <option>服务相关</option>
                                                <option>物流相关</option>
                                                <option>售后相关</option>
                                                <option>财务相关</option>
                                                <option>活动相关</option>
                                                <option>网站相关</option>
                                                <option>预约相关</option>
                                                <option>其他方面</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="tr" valign="top"><span>*</span>问题描述：</td>
                                        <td><textarea id="complaincon"></textarea><br/>
                                            <p>请您如实填写原因及商品情况，字数在10-500字内且不要包含特殊字符。</p>
                                            <a class="complain-submit" onclick="addcomplain()">提交</a>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <div class="mask"></div>

<div class="member-dialog dia3">
    <div class="member-dialog-body">
        <div class="title"><a href="javascript:;" onclick="cls()">×</a>提示</div>
        <div class="tc">
            <div class="que-delete clearfix">
                <img src="${basePath}/images/images_l6.png"/>
                <div class="fl tl">
                    <p class="f16 red" id="errorContent"></p>

                    <div class="m-btn mt20">
                        <a href="javascript:;" onclick="cls()">确定</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<div class="member-dialog dia2">
    <div class="member-dialog-body">
        <div class="title"><a href="javascript:;" onclick="cls()">×</a>提示</div>
        <div class="tc">
            <div class="que-delete clearfix">
                <img src="${basePath}/images/images_l6.png"/>
                <div class="fl tl">
                    <p class="f16 red" ">投诉失败！</p>

                    <div class="m-btn mt20">
                        <a href="javascript:;" onclick="cls()">确定</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

    <input type="hidden" value="${token!''}" id="hi_token" />
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
    $(".new_member_left div:eq(2) ul li:eq(1)").addClass("cur");
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
</script>
</@htmlBody>