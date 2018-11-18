<#include "../include/common.ftl">
<@htmlHead title="浏览历史-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css" />
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
    <script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css" />

</head>-->
<@htmlBody>
    <input type="hidden" value="${token!''}" id="hi_token" />
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
          <#include "newleftmenu.ftl"/>
            <div class="new_member-right">
                <div class="new_order_list">
                    <div class="n-title">浏览历史</div>
                    <div class="brw-history mt20">
                        <#if browses??&& browses?size!=0>
                            <#assign time=""/>
                            <#assign indexNum=0/>
                                <#list browses as browse>
                                    <#if browse.goods?? >
                                        <#if browse.createTime?string('yyyy-MM-dd')!=time>
                                            <#assign time=(browse.createTime?string('yyyy-MM-dd')) />
                                            <div class="title <#if indexNum % 2 ==0>today-look</#if>">

                                                <#--<span class="f16 fb mr20">上周五</span>-->
                                            ${time}
                                                <#--<a href="#" class="ml20">删除</a>-->
                                            </div>
                                        <div class="brw-history-list <#if indexNum % 2 ==0>n-tiao</#if> ">
                                            <#assign indexNum=indexNum+1/>
                                        <ul class="clearfix">
                                            <#list browses as brow>
                                                <#if brow.createTime?string('yyyy-MM-dd')==time&&brow.goods??>
                                                    <li>
                                                        <a href="${basePath}/item/${brow.goodsId}.html">
                                                            <img style="width:204px;height:204px;" alt="<#if brow.goods.goodsName??> ${brow.goods.goodsName}</#if>" src="<#if brow.goods.goodsImg??> ${brow.goods.goodsImg}</#if>" />
                                                            <p title="<#if brow.goods.goodsName??> ${brow.goods.goodsName}</#if>"><#if brow.goods.goodsName??&&(brow.goods.goodsName?length>13)> ${brow.goods.goodsName?substring(0,13)}<#else>${brow.goods.goodsName}</#if> </p>
                                                            <#--<p title="<#if brow.goods.goodsName??> ${brow.goods.goodsName}</#if>"><#if brow.goods.goodsName??> ${brow.goods.goodsName}</#if></p>-->
                                                            <p class="price">￥${brow.goods.goodsPrice?string('0.00')}</p>
                                                        </a>
                                                        <div class="story-bg">
                                                            <a href="javascript:void(0)"  onclick="cancelbrowse('${basePath}/customer/cancelbrowse-${brow.likeId}.html')" class="fr mr20"><img src="${basePath}/images/l-delete.jpg" width="16" height="19"></a>
                                                            <a href="javascript:void(0)" onclick="buys(${brow.goodsId})" class="fr mr20"><img src="${basePath}/images/shop-car.png" width="21" height="17"></a>
                                                            <input type="hidden" value="${browse.goodsId}" />
                                                            <a href="javascript:void(0)" onclick="addattr(this,${browse.goodsId})" class="fr mr20"><img src="${basePath}/images/love.png" width="21" height="17"></a>
                                                        </div>

                                                    </li>
                                                </#if>
                                            </#list>
                                        </ul>
                                        </div>
                                        </#if>

                                    </#if>
                                </#list>
                            <#else>
                            <div style="margin-top:10px;border:1px #e8e8e8  solid; height:40px; text-align: center; font-size: 18px;line-height:40px;;">
                                暂无浏览记录！
                            </div>

                        </#if>
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
                        <p class="f16 red">加入购物车失败！</p>
                        <div class="m-btn mt20">
                            <a  id="go_pay_01" href="javascript:cls();">确定</a>
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
                        <p class="f16 red" id="con_00">确定确认收货吗？</p>
                        <div class="m-btn mt20">
                            <a  id="go_pay_01" href="javascript:cls();">确定</a>
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
    <script type="text/javascript" src="${basePath}/js/customer/findcode.js"></script>
    <script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
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
    $(".pro_sort").addClass("pro_sort_close");
    $(".new_member_left div:eq(1) ul li:eq(3)").addClass("cur");
});
</script>
</@htmlBody>
