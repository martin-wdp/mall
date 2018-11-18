<#include "../include/common.ftl">
<@htmlHead title="我的收藏-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<style>
    .switch_border table tr .goods a img{cursor: pointer;}
</style>
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我的收藏-${topmap.systembase.bsetName}</title>
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
    <style>
        .switch_border table tr .goods a img{cursor: pointer;}

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
            <span>我的收藏</span>
        </div>
        <div class="member_box mb20">
            <#include "leftmenu.ftl" >
            <div class="member_right fl ml10">
                <div class="memebr_title mb20">
                    <h2 class="f14 fb">我的收藏</h2>
                </div>
                <div class="order_list switch_border">
                    <div class="tagMenu">
                        <ul class="menu">
                           <li onclick="gohref('myfollw.html');"><em></em><span></span>我收藏的商品</li>
                           <li onclick="gohref('sellermyfollw.html');"><em>&nbsp;</em><span>&nbsp;</span>我收藏的商家</li>
                        </ul>
                    </div>
                    <div class="content">
                        <div class="layout">
                            <table class="collection_goods common_table">
                                <tr>
                                    <#--<th width="70"><input class="checkbox" type="checkbox" /><label>全选</label></th>-->
                                    <th width="320">商品名称</th>
                                    <th width="120">网友反馈</th>
                                    <th width="100">价格</th>
                                    <th width="70">库存情况</th>
                                    <th width="100">操作</th>
                                </tr>
                                 <#if (pb.list?size!=0)>
			                    	<#list pb.list as follow>
			                    		<#if follow.good??>
			                                <tr>
			                                    <#--<td><input class="checkbox" type="checkbox" /></td>-->
			                                    <td class="goods">
			                                        <a class="pic fl" target="_black" onclick="checkgoods('${follow.goodsId}')">
			                                        	<img width="100" height="100" alt="<#if follow.good.goodsName??>${follow.good.goodsName}</#if>" title="<#if follow.good.goodsName??>${follow.good.goodsName}</#if>" 
			                                        		src="<#if follow.good.goodsImg??>${follow.good.goodsImg}</#if>" /></a>
			                                        <div class="fl" style="width:300px;">		
				                                        <a class="name" target="_black" onclick="checkgoods('${follow.goodsId}')" title="<#if follow.good.goodsName??>${follow.good.goodsName}</#if>"> <#if follow.good.goodsName??>${follow.good.goodsName}</#if></a>
				                                        <a class="score" target="_black" onclick="checkgoods('${follow.goodsId}')">
				                                        <div class="star_wp"><span class="star star_${(follow.good.goodsScore)!'0'}"></span></span></div>
				                                        </a>
			                                        </div>
			                                        <div class="cb"></div>
			                                    </td>
			                                    <td>
			                                        <a onclick="checkComment('${follow.goodsId}')" target="_black">评论：<#if follow.good.commentCount??>${follow.good.commentCount}</#if>条</a>
			                                        <a onclick="checkComment('${follow.goodsId}')" target="_black">咨询：<#if follow.good.consultCount??>${follow.good.consultCount}</#if>条</a>
			                                    </td>
			                                    <td><span class="ftx04" style="font-family: '微软雅黑'">￥<#if follow.good.goodsPrice??>${follow.good.goodsPrice?string('0.00')}</#if></span></td>
			                                    <td>
				                                    <font color="grey">
					                                    <#if follow.good.goodStock?? >
						                                    <#if (follow.good.goodStock>0) >
						                                    	现货
						                                    <#else>
						                                    	可预订
						                                    </#if>
						                                </#if>
				                                    </font>
				                                </td>
			                                    <td>
			                                        <a class="cart_btn add_car" href="javascript:void(0)" onclick="buy(this)">加入购物车</a>
			                                        <input type="hidden" value="${follow.goodsId}" />
			                                        <a href="javascript:void(0)" onclick="cancelfollow('${basePath}/customer/cancelfollow-${follow.followId}.html')">取消收藏</a>
			                                    </td>
			                                </tr>
		                                </#if>
	                                </#list>
	                                <input type="hidden" value="${token!''}" id="hi_token" />
			                    <#else>
			                    	<tr>
	                                    <td colspan="6">暂无收藏记录！</td>
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
     <div class="dialog s_dia dia2">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" alt="" src="${basePath}/images/q_mark.png" />
            	<em>确定取消收藏该商品吗？</em>
            </div>
            <div class="dia_ops mt20 tr">       
                <a class="go_pay" href="javascript:;">确定</a>
                <a class="go_shopping" href="javascript:cls();">取消</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
     <div class="dialog s_dia dia3">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" alt="" src="${basePath}/images/mod_war.png" />
            	<em>请重试...</em>
            </div>
            <div class="dia_ops mt20 tr">       
                <a class="go_pay" href="javascript:cls();">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
        <div class="dialog s_dia dia22">
            <div class="dia_tit clearfix">
                <h4 class="fl">提示</h4>
                <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
            </div><!--/dia_tit-->
            <div class="dia_cont">
                <div class="dia_intro no_tc pt30">
                    <img class="vm mr10" alt="" src="${basePath}/images/mod_err.png" />
                    <em>系统错误,请重试!</em>
                </div>
                <div class="dia_ops mt20 tr">
                    <a class="go_pay" href="javascript:cls();">确定</a>
                </div><!--/dia_ops-->
            </div><!--/dia_cont-->
        </div><!--/dialog-->
        <div class="dialog s_dia dia23">
            <div class="dia_tit clearfix">
                <h4 class="fl">提示</h4>
                <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
            </div><!--/dia_tit-->
            <div class="dia_cont">
                <div class="dia_intro no_tc pt30">
                    <img class="vm mr10" alt="" src="${basePath}/images/mod_err.png" />
                    <em>该商品已下架，请选择其他商品</em>
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
    function checkgoods(id){
        $.ajax({
            type:'post',
            url:'${basePath}/checkgoods.htm?goodsId='+id,
            async:false,
            error:function(request) {
                dia(22);
            },
            success:function(data){
                if(data == 1){
                    window.location.href="${basePath}/item/"+id+".html"
                }else{
                   dia(23);
                }
            }
        });
    }
    function checkbuy(id){
        $.ajax({
            type:'post',
            url:'${basePath}/checkgoods.htm?goodsId='+id,
            async:false,
            error:function(request) {
                dia(22);
            },
            success:function(data){
                if(data == 1){
                    $.get("../goods/addProductToShopCar.html?productId=" + $(obj).next().val()
                    + "&goodsNum=1", function(data) {
                        if (data) {
                            location.href = "../myshoppingcart.html";
                        } else {
                            dia(3);
                        }
                    });
                }else{
                    dia(23);
                }
            }
        });
    }
    function checkComment(id){
        $.ajax({
            type:'post',
            url:'${basePath}/checkgoods.htm?goodsId='+id,
            async:false,
            error:function(request) {
                dia(22);
            },
            success:function(data){
                if(data == 1){
                    window.location.href="${basePath}/item/"+id+".html#comment"
                }else{
                   dia(23);
                }
            }
        });
    }
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
    $(".memeber_left div:eq(0) ul li:eq(4)").addClass("cur");
});

	function gohref(sd){
		window.location.href="${basePath}/customer/"+sd;
	}
</script>
</@htmlBody>
