<#include "../include/common.ftl">
<@htmlHead title="购买咨询-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css" />
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
                    <div class="air-info-consult">
                        <div class="n-title">购买咨询</div>
                        <div class="tagMenu air-consult-menu">
                            <ul class="menu clearfix">
                                <li onclick="showcon();" <#if flag=='2'>class="current"</#if>>全部咨询</li>
                                <li onclick="showyes();" <#if flag=='1'>class="current"</#if>>已回复咨询</li>
                                <li onclick="showno();" <#if flag=='0'>class="current"</#if>>未回复咨询</li>
                            </ul>
                        </div>
                        <div class="content">
                            <div class="layout">
                                <table class="air-consult-table mt10">
                                    <thead>
                                    <tr>
                                        <th width="480">商品信息</th>
                                        <th>咨询内容及回复</th>
                                        <th>最后更新时间</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#if (pb.list?size!=0)>
                                        <#list pb.list as con>
                                        <tr class="air-consult-bd">
                                            <td class="baobei">
                                                <a class="pic" title="${(con.goodsName)!''}"  href="${basePath}/item/${con.goodsId}.html">
                                                    <img width="100" height="100" alt="${(con.goodsName)!''}" src="${(con.goodsImg)!''}" />
                                                </a>
                                                <div class="desc ml20">
                                                    <a title="${(con.goodsName)!''}" href="${basePath}/item/${con.goodsId}.html" target="_blank" >
                                                        <#if con.goodsName?? >
                                                        ${(con.goodsName)!''}
                                                        </#if>
                                                    </a>
                                                    <p class="col9">
                                                        <#list con.specVo as specVo>
                                                            <#if specVo.spec.specName??&&specVo.spec.specName == '.'>
                                                            <#else>
                                                                ${specVo.spec.specName}:<#if specVo.specValueRemark??>${specVo.specValueRemark}<#else>${specVo.goodsSpecDetail.specDetailName!''} </#if>&nbsp;
                                                            </#if>
                                                        </#list>
                                                    </p>
                                                </div>
                                            </td>
                                            <td class="quest">
                                                <p>咨询内容：${(con.commentContent)!''}</p>
                                                 <#list con.replays as rep>
                                                  <p class="red mt10">商城回复：${rep.commentContent}</p>
                                                 </#list>

                                            </td>
                                            <td>
                                                <p>${con.publishTime?string("yyyy-MM-dd HH:mm:ss")}</p>

                                                <#list con.replays as rep>
                                                    <p class="red mt10">${rep.publishTime?string("yyyy-MM-dd HH:mm:ss")}</p>
                                                </#list>
                                            </td>
                                        </tr>

                                        </#list>
                                    <#else>
                                        <tr>
                                            <td colspan="3" style="height:80px; font-size: 16px; text-align:center;">暂无咨询记录</td>
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
    $(".new_member_left div:eq(4) ul li:eq(1)").addClass("cur");
    $(".pro_sort").addClass("pro_sort_close");
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
