<#include "../include/common.ftl">
<@htmlHead title="我的优惠券-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/member.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<style>
    .dialog {position:fixed; background:#fff; z-index:9999; width:300px!important; min-height:100px!important; padding:0 0 10px!important; border:5px solid #f7f7f7; display:none;}
    .dia_tit {height:30px; line-height:30px; padding:0 20px; font-family:microsoft YaHei; font-size:14px; color:#fff;background: #eb6122;}
    .dia_close {width:15px; height:15px; background:url(../images/dia_close.png) no-repeat; margin-top:7px;}
    .dia_intro{height:auto!important; text-align:left; padding-left:50px; padding-top:20px!important;}
    .info_ok {display:inline-block!important; zoom:1; *display:inline; width:100px; height:28px; text-align:center; line-height:27px; font-family:microsoft YaHei; font-size:14px; color:#fff!important; margin:0 5px;}
    .info_ok {background:url(../images/org_btn.gif) no-repeat;}

</style>
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我的优惠券-${topmap.systembase.bsetName}</title>
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
<style>
.dialog {position:fixed; background:#fff; z-index:9999; width:300px!important; min-height:100px!important; padding:0 0 10px!important; border:5px solid #f7f7f7; display:none;}
.dia_tit {height:30px; line-height:30px; padding:0 20px; font-family:microsoft YaHei; font-size:14px; color:#fff;background: #eb6122;}
.dia_close {width:15px; height:15px; background:url(../images/dia_close.png) no-repeat; margin-top:7px;}
.dia_intro{height:auto!important; text-align:left; padding-left:50px; padding-top:20px!important;}
.info_ok {display:inline-block!important; zoom:1; *display:inline; width:100px; height:28px; text-align:center; line-height:27px; font-family:microsoft YaHei; font-size:14px; color:#fff!important; margin:0 5px;}
.info_ok {background:url(../images/org_btn.gif) no-repeat;}

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
            <a href="#">账户中心</a><span>&gt;</span>
            <span>我的优惠券</span>
        </div>
        <div class="member_box mb20">
        <#--引入左侧导航-->
        <#include "leftmenu.ftl" />
            <div class="member_right fl ml10">
                <div class="memebr_title mb20">
                    <h2 class="f14 fb">我的优惠券</h2>
                </div>
                <div class="order_list switch_border">
                    <div class="tagMenu">
                    	<input type="hidden" id="codeStatus" value="${codeStatus}"/>
                        <ul class="menu">
                           <li id="tag1" onclick="javascript:location='${basePath}/mycoupon/1'"><em></em><span></span>未使用优惠券</li>
                           <li id="tag2" onclick="javascript:location='${basePath}/mycoupon/2'"><em></em><span></span>已使用优惠券</li>
                           <li id="tag3" onclick="javascript:location='${basePath}/mycoupon/3'"><em></em><span></span>已过期优惠券</li>
                        </ul>
                    </div>
                	<form>
                	</form>
                    <div class="content">
                        <div class="layout">
                            <table class="orders common_table">
                                <tr>
                                    <th width="10%">编号</th>
                                    <th width="10%">类别</th>
                                    <th width="10%">面值</th>
                                    <th width="10%">所需消费金额</th>
                                    <#if (codeStatus=="2")>
                                    	<th width="30%">订单编号</th>
                                    	<th width="30%">获取时间</th>
                                    <#else>
	                                    <th width="30%">有效期</th>
	                                    <th width="30%">使用范围</th>
                                    </#if>
                                </tr>
                            	<#if (pb.list?size>0) >
		                        	<#list pb.list as coupen>
									    <tr>
									    	<td>${coupen.codeNo}</td>
									    	<#if (coupen.couponRulesType=="1" ||coupen.couponRulesType=="3")>
									    		<td>直减券</td>
									    		<td><span style="color:red;">
									    			<#if (coupen.couponStraightDown.downPrice)??>
									    			${coupen.couponStraightDown.downPrice?string.currency}
									    			</#if>
									    			<#if !(coupen.couponStraightDown.downPrice)??>
									    			￥0.00
									    			</#if>
									    		</td>
									    		<td>￥0.00</td>
									    	<#else>
									    		<td>满减券</td>
									    		<td><span style="color:red;">
									    			<#if (coupen.couponFullReduction.reductionPrice)??>
									    			${coupen.couponFullReduction.reductionPrice?string.currency}
									    			</#if>
									    			<#if !(coupen.couponFullReduction.reductionPrice)??>
									    			￥0.00
									    			</#if>
									    			</span></td>
									    		<td>
									    			<#if (coupen.couponFullReduction.fullPrice)??>
									    			${coupen.couponFullReduction.fullPrice?string.currency}
									    			</#if>
									    			<#if !(coupen.couponFullReduction.fullPrice)??>
									    			￥0.00
									    			</#if>
									    		</td>
									    	</#if>
		                                    <#if (codeStatus=="2")>
		                                    	<td>
		                                    		<#if (coupen.codeUseOrderId??)>
		                                    			${coupen.codeUseOrderId}
		                                    		</#if>
		                                    	</td>
		                                    	<td>
		                                    		<#if (coupen.codeGetTime??)>
			                                    		${coupen.codeGetTime?string("yyyy-MM-dd HH:mm:ss")}
		                                    		</#if>
		                                    	</td>
		                                    <#else>
			                                    <td>${coupen.couponStartTime?string("yyyy-MM-dd")}&nbsp;至&nbsp;${coupen.couponEndTime?string("yyyy-MM-dd")}</td>
										    	<td>
													
													<#if (coupen.gplist?? && coupen.gplist?size>0)>
														   <a class="change_promotion" href="javascript:void(0);" onclick="showDialog(${coupen_index});">查看范围</a>
							                               <div class="dialog promotion_dialog_${coupen_index}">
														        <div class="dia_tit clearfix">
														            <h3 class="fl info_title">优惠券使用范围</h3>
														            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
														        </div><!--/dia_tit-->
														        <div class="dia_cont">
														            <div class="dia_intro pt30">
														            <#list coupen.gplist as gp>
																		<a title="${gp.goodsInfoName}" target="_blank" href="${basePath}/item/${gp.goodsInfoId}.html">
																		<#if (gp.goodsInfoName?length>16)>
																			${gp.goodsInfoName?substring(0,16)}...&nbsp;
																		<#else>
																			${gp.goodsInfoName}&nbsp;
																		</#if>
																		</a>
																	</#list>
														            </div>
														            <div class="dia_ops mt20 tc dia_btn_ok">
														                <a class="info_ok info_tip_cancel" href="javascript:;" onclick="cls()">确定</a>
														            </div><!--/dia_ops-->
														        </div><!--/dia_cont-->
														    </div><!--/dialog-->
													
													<#elseif (coupen.gclist?? && coupen.gclist?size>0)>
															<a class="change_promotion" href="javascript:void(0);" onclick="showDialog(${coupen_index});">查看范围</a>
															<div class="dialog promotion_dialog_${coupen_index}">
														        <div class="dia_tit clearfix">
														            <h3 class="fl info_title">优惠券使用范围</h3>
														            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
														        </div><!--/dia_tit-->
														        <div class="dia_cont">
														            <div class="dia_intro pt30">
														            <#list coupen.gclist as gc>
														            	<span title="${(gc.catName)!''}">
																		<#if (gc.catName)?? && (gc.catName?length>16)>
																			${gc.catName?substring(0,16)}...&nbsp;
																		<#else>
																			${gc.catName}&nbsp;
																		</#if>
														            	</span>
																	</#list>
														            </div>
														            <div class="dia_ops mt20 tc dia_btn_ok">
														                <a class="info_ok info_tip_cancel" href="javascript:;" onclick="cls()">确定</a>
														            </div><!--/dia_ops-->
														        </div><!--/dia_cont-->
														    </div><!--/dialog-->
													<#elseif (coupen.gblist?? && coupen.gblist?size>0)>
														<a class="change_promotion" href="javascript:void(0);" onclick="showDialog(${coupen_index});">查看范围</a>
														<div class="dialog promotion_dialog_${coupen_index}">
														        <div class="dia_tit clearfix">
														            <h3 class="fl info_title">优惠券使用范围</h3>
														            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
														        </div><!--/dia_tit-->
														        <div class="dia_cont">
														            <div class="dia_intro pt30">
														            <#list coupen.gblist as gb>
														            	<span title="${(gb.brandName)!''}">
																		<#if (gb.brandName)?? && (gb.brandName?length>16)>
																			${gb.brandName?substring(0,16)}...&nbsp;
																		<#else>
																			${gb.brandName}&nbsp;
																		</#if>
														            	</span>
																	</#list>
														            </div>
														            <div class="dia_ops mt20 tc dia_btn_ok">
														                <a class="info_ok info_tip_cancel" href="javascript:;" onclick="cls()">确定</a>
														            </div><!--/dia_ops-->
														        </div><!--/dia_cont-->
														    </div><!--/dialog-->
													<#else>
														没有可使用范围
													</#if>
												</td>
		                                    </#if>
										</tr>
									</#list>
								<#else>
	                                <tr>
								    	<td colspan="6">暂无优惠券</td>
									</tr>
                            	</#if>
                            </table>
                            <#if (pb.list?size!=0)>
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
    <div class="mask"></div><!--/mask-->
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
	//选择标签
	var codeStatus = $("#codeStatus").val();
	if(codeStatus==1){
		$("#tag1").addClass('current');
		$("#tag2").removeClass('current');
		$("#tag3").removeClass('current');
	}else if(codeStatus==2){
		$("#tag1").removeClass('current');
		$("#tag2").addClass('current');
		$("#tag3").removeClass('current');
	}else if(codeStatus==3){
		$("#tag1").removeClass('current');
		$("#tag2").removeClass('current');
		$("#tag3").addClass('current');
	}
	$(".pro_sort").addClass("pro_sort_close");
	$(".memeber_left div:eq(2) ul li:eq(4)").addClass("cur");
});
function showDialog(n){
	$(".mask").fadeIn();
	$(".promotion_dialog_"+n).fadeIn();
	};
function cls(){
	$(".dialog").fadeOut();
	$(".mask").fadeOut();
	};
</script>
</@htmlBody>
