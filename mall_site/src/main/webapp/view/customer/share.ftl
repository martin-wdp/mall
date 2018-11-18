<#include "../include/common.ftl">
<@htmlHead title="商品晒单-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<style>
    .text_area{width:600px;height:100px;border-top:1px solid #ABADB3;border-left:1px solid #ABADB3;border-bottom:1px solid #E3E9EF;border-right:1px solid #E3E9EF;padding:5px;}
    .s_dia .no_tc  {padding-left:30px;}
    .dia_cont em {font-style:normal;}
</style>
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#assign basePath=request.contextPath>
<base href="${basePath}/">
<title>商品晒单-${topmap.systembase.bsetName}</title>
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
.text_area{width:600px;height:100px;border-top:1px solid #ABADB3;border-left:1px solid #ABADB3;border-bottom:1px solid #E3E9EF;border-right:1px solid #E3E9EF;padding:5px;}
    .s_dia .no_tc  {padding-left:30px;}
    .dia_cont em {font-style:normal;}
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
            <span>商品晒单</span>
    </div>
	<#include "leftmenu.ftl" />
    <div class="member_right fl ml10">
    	<div class="memebr_title mb20">
                    <h2 class="f14 fb">商品晒单</h2>
        </div>
        <div class="evaluate_goods">
        <#if order??>
        	<table class="common_table">
            	<thead>
                	<tr>
                    	<th width="50%">商品信息</th>
                        <th width="10%">购买时间</th>
                        <th width="30%">晒单内容</th>
                        <th width="10%">晒单</th>
                    </tr>
                </thead>
                <tbody>
                
                <#if order.goods?size!=0>
	                <#list order.goods as good>
	                	<tr>
	                    	<td>
	                        	<a class="img fl" target="_blank" href="${basePath}/item/${(good.goodsId)!''}.html"><img alt="${(good.goodsName)!'已下架'}" src="${(good.goodsImg)!''}" width="60" height="60" /></a>
	                            <p class="name fl ml10"><a target="_blank" href="${basePath}/item/${(good.goodsId)!''}.html">
	                             <#if good.goodsName?length gt 18>
	                        				 ${good.goodsName?substring(0,18)}
	                            <#else>
	                        				${(good.goodsName)!'已下架'}
	                        	</#if>
	                            </a></p>
	                        </td>
	                        <td>
	                        	<p><#if good.buyTime??>${good.buyTime?string("yyyy-MM-dd")}</#if></p>
	                        	<p><#if good.buyTime??>${good.buyTime?string("HH:mm:ss")}</#if></p>
	                        </td>
	                         <td>
	                         	<#if good.shareFlag?? && good.shareFlag=='1'>
		                         	<#if ((good.imageList)?? && good.imageList?size!=0)>
		                         		<#list good.imageList as img>
		                         			<#if (img.imageName?? & img.imageName?index_of('!') != -1 )>
		                         				<img width="50" alt="" src="${img.imageName?substring(0,img.imageName?index_of("!")+1)+56}" />
		                         			<#else>
		                         				<img width="50" alt="" src="${img.imageName}" />
		                         			</#if>
		                            	</#list>
		                        	</#if>
	                        	</#if>
	                        </td>
	                        <td>
	                        	<div class="evaluate_box">
	                        		<#if good.shareFlag?? && good.shareFlag=='1'>
	                            		<#--<a href="${basePath}/share/detail-${good.shareId}.html" >已晒单 </a>-->
	                            		<a href="${basePath}/item/${good.goodsId}.html#comment"" >已晒单 </a>
	                        		</#if>
	                        		<#if !good.shareFlag?? || good.shareFlag=='0'>
	                            	<a class="evaluate_link" href="javascript:void(0);" data-f="0">发表晒单</a>
	                                <div class="evalate_cont">
	                                	<div class="coner"></div>
	                                	<div class="common_form">
	                                        <dl>
	                                        	<dt><span class="red">*</span>标题：</dt>
	                                            <dd>
	                                            	<input type="text" id="share_titile${good.goodsId}" style="width:300px;height:20px;" maxLength="20" minLength="5"/>5-20字
	                                            	<p><font id="share_titile_tip${good.goodsId}" style="display:none;color:red;">请输入晒单标题！</font></p>
	                                            </dd>
	                                        </dl>
	                                        <dl>
	                                        	<dt><span class="red">*</span>心得：</dt>
	                                            <dd>
	                                            	<textarea class="text_area" id="content${good.goodsId}"></textarea>
	                                            	<font id="share_content_tip${good.goodsId}" style="display:none;color:red;">填点心得吧！</font>
	                                                <p class="tr">10-500字</p>
	                                            </dd>
	                                        </dl>
	                                    	<form id="upload_form${good.goodsId}" name="upload_form" method="post" enctype="multipart/form-data" action="${basePath}/share/upload.htm?orderGoodsId=${good.goodsId}&CSRFToken=${token}" target="hidden_frame">
	                                        <dl>
	                                        	<dt></dt>
	                                            <dd>
	                                            	<div class="ctrl"> 
	                                                    <a class="up_photo fl" href="javascript:void(0)">上传图片
	                                                		<input type="file" class="upload_file" id="imageFile" order_goods_id="${good.goodsId}" name="shareFile" style="width:80px;margin-left:-80px;filter:alpha(opacity=0);-moz-opacity:0.5;-khtml-opacity: 0;opacity: 0; ">
	                                                    </a>
	                                                    <span class="fl light ml10">最多5张</span>
	                                                    <ul class="fl ml10" id="share_imgs${good.goodsId}">
	                                                    </ul>
	                                                    <a class="order_btn fl delete_btn" order_goods_id="${good.goodsId}" href="javascript:void(0)"><span>删除</span></a><span class="ml5">选中的图片</span>
	                                                    <font color="red" id="err_img_${good.goodsId}" style="display:none;color:red;">最多上传5张图片哦!</font>
	                                                    <div class="cb"></div>
	                                                    <font color="red" id="share_img_tip${good.goodsId}" style="display:none;color:red;">至少上传三张图片哦！</font>
	                                                </div>
	                                            </dd>
	                                        </dl>
	                                        </form>
	                                        <iframe name="hidden_frame" style="display:none"></iframe>
	                                        <dl>
	                                        	<dt></dt>
	                                            <dd>
	                                            	<input type="button" class="common_btn_red share_btn" value="晒单" order_goods_id="${good.goodsId}" />
	                                            </dd>
	                                        </dl>
	                                    </div>
	                                </div>
	                                </#if>
	                            </div>
	                        </td>
	                    </tr>
	                    </#list>
	                    <input type="hidden" id="orderId" value="${order.orderId}"/>
                    <#else>
                    	 <tr>
	                         <td colspan="3" style="text-align:center;">暂无可晒单商品！</td>
	                     </tr>
                    </#if>
                </tbody>
            </table>
            <#else>
            	<div class="reg_success">
			    	<div class="notice2">
			        	<img alt="" src="${basePath}/images/failed.png">参数异常！<span>
			        </div>
			        <div class="notice3">
			            <strong><span id="time">5</span>秒自动进入<a href="${basePath}/index.html">“首页”</a></strong></span> 
			        </div>
			    </div>
            </#if>
        </div><!-- /evaluate_goods -->  <!-- /common_form --><!-- /evaluate_box -->
        <div class="prompt mt20">
        	<h2>评价</h2>
             <div class="body">
            	<h4>评价说明：</h4>
            	<#if explain??>
            		<#list "${explain.content}"?split("|") as con>
					<p>${con_index + 1}.${con}</p>
					</#list>
            	</#if>
            </div>
        </div><!-- /prompt -->
    </div>
    <div class="cb"></div>
</div>

<input type="hidden" value="${token!''}" id="hi_token" />
 <div class="dialog s_dia dia4">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" alt="" src="${basePath}/images/mod_err.png" />
            	<em id="titleerr">图片格式错误！</em>
            </div>
            <div class="dia_ops mt20 tr">       
                 <a class="go_pay" id="go_pay_00" href="javascript:cls();">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
 <div class="dialog s_dia dia5">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" alt="" src="${basePath}/images/mod_err.png" />
            	<em id="titleerr">图片太大！</em>
            </div>
            <div class="dia_ops mt20 tr">       
                 <a class="go_pay" id="go_pay_00" href="javascript:cls();">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
    <div class="dialog s_dia dia6">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" alt="" src="${basePath}/images/mod_err.png" />
            	<em id="titleerr">请先选择要删除的图片！</em>
            </div>
            <div class="dia_ops mt20 tr">       
                 <a class="go_pay" id="go_pay_00" href="javascript:cls();">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
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
<script type="text/javascript" src="${basePath}/js/customer/member.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/customer_share.js"></script>
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
    $(".memeber_left div:eq(0) ul li:eq(0)").addClass("cur");
    $(".pro_sort").addClass("pro_sort_close");
});

setTimeout(countDown, 1000);
	function countDown(){
		var time = $("#time").text();
		$("#time").text(time - 1);
		if (time == 1) {
			location.href='${basePath}/index.html';
		} else {
			setTimeout(countDown, 1000);
		}
	}
</script>
</@htmlBody>
