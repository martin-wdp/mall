<#include "../include/common.ftl">
<@htmlHead title="我的订单-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<style>
    .mn_sel {display:inline-block;zoom:1;*display:inline;border:1px solid #ddd;width:205px;height:25px;line-height:25px;padding:0 5px;vertical-align:middle;background:url(${basePath}/images/area_arrow.gif) no-repeat 195px center;}
    .selCont {width:330px;min-height:50px;border:1px solid #ddd;padding:10px;margin-top:-4px;display:none;}
    .selCont label {display:inline-block;zoom:1;*display:inline;width:145px;margin:0 10px 10px 0;}
    .sel_txa {width:340px;height:100px;padding:5px;border:1px solid #ddd;display:none;margin-top:5px;}
    #backtable tr {
        padding-top:5px;
    }
    .red{color:red;}
    .black{color:black;}
</style>
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我的订单-${topmap.systembase.bsetName}</title>
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
	.mn_sel {display:inline-block;zoom:1;*display:inline;border:1px solid #ddd;width:205px;height:25px;line-height:25px;padding:0 5px;vertical-align:middle;background:url(${basePath}/images/area_arrow.gif) no-repeat 195px center;}
	.selCont {width:330px;min-height:50px;border:1px solid #ddd;padding:10px;margin-top:-4px;display:none;}
	.selCont label {display:inline-block;zoom:1;*display:inline;width:145px;margin:0 10px 10px 0;}
	.sel_txa {width:340px;height:100px;padding:5px;border:1px solid #ddd;display:none;margin-top:5px;}
	#backtable tr {   
		padding-top:5px;
	}
	.red{color:red;}
	.black{color:black;}
</style>
</head>-->
<@htmlBody>
    	<#--一引入头部 <#include "/index/topnew.ftl" />  -->	
		<#if (topmap.temp)??>
			<#if (topmap.temp.tempId==1)>
				<#include "../index/topnew.ftl">
			<#elseif (topmap.temp.tempId==3)>
				<#include "../index/newheader.ftl">
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
            <span>积分兑换订单</span>
        </div>
        <div class="member_box mb20">
            <#include "leftmenu.ftl" >
            <div class="member_right fl ml10">
                <div class="memebr_title mb20">
                    <h2 class="f14 fb">订单中心</h2>
                </div>
                <div class="order_list switch_border">
                	<div class="tagMenu tagMenu1">
                        <ul class="gift_tabs clearfix">
                           <li <#if (!type?? || type == '0') >class="cur"</#if> data-val="0"><a href="javascript:;" >所有订单</a></li>
                           <li <#if (type?? && type == '1')>class="cur"</#if> data-val="1"><a href="javascript:;" >待发货订单</a></li>
                           <li <#if (type?? && type == '2')>class="cur"</#if> data-val="2"><a href="javascript:;" >待收货订单</a></li>
                           <li <#if (type?? && type == '3')>class="cur"</#if> data-val="3"><a href="javascript:;" >已完成订单</a></li>
                        </ul>
                    </div>
                    <div class="list_filter">
                        <div class="filter_r fr">
                            <#--<input class="text t_param" type="text" value="${paramString!'商品名称或订单编号'}" onfocus="javascript:this.value = '';" />
                            <input class="sub_btn" type="submit" value="查询" /> -->
                        </div>
                        <div class="filter_l fl">
                            <select class="select s1 gifts">
                                <option value="0">近一个月订单</option>
                                <option value="1">一个月前订单</option>
                            </select>
                            <input type="hidden" id="hi_date" value="${date!'0'}" />
                            <input type="hidden" id="hi_type" value="${type!'0'}" />
                            <#--<select class="select s2 ss">
                                <option value="5">所有订单</option>
                                <option value="0">待处理订单</option>
                                <option value="3">已完成订单</option>
                                <option value="4">已取消订单</option>
                            </select>-->
                        </div>
                        <div class="cb"></div>
                    </div>
                    <div class="content">
                        <div class="layout">
                            <table class="orders common_table">
                                <tr>
                                    <th width="100">订单编号</th>
                                    <th width="180">订单商品</th>
                                    <th width="100">收货人</th>
                                    <th width="80">兑换积分</th>
                                    <th width="100">下单时间</th>
                                    <th width="90">订单状态</th>
                                    <th width="70">操作</th>
                                </tr>
                                <#if (pb.list?size!=0)>
	                                <#list pb.list as order>
		                                <tr>
		                                    <td>
		                                    	<#if order.giftOrderCode??>
													${order.giftOrderCode}
												 </#if>
		                                    </td>
		                                    <td class="goods">
			                                    	<a target="black" title="${(order.giftName)!''}" <#--href="${basePath}/giftdetail/${order.temp1}.html"-->>
			                                    	   <img title="${(order.giftName)!''}" alt="${(good.goodsName)!''}" src="<#if order.giftPicList?? &&  order.giftPicList?size &gt; 0 >${(order.giftPicList[0].picBig)!''}</#if>"  width="50" height="50" />
			                                    	</a>
		                                    </td>
		                                    <td>
			                                    <#if order.shoppingPerson??>
													${order.shoppingPerson}
												</#if>
		                                    </td>
		                                    <td>
			                                    <font color="red" style="font-family: '微软雅黑'">
				                                    <#if order.orderIntegral??>
														${(order.orderIntegral)!'0'}
													</#if>
												</font>
											</td> 
		                                    <td>
		                                    	<font color="grey">
			                                    	<#if order.createTime??>
															${order.createTime?string("yyyy-MM-dd HH:mm:ss")?substring(0,10)}
			                                    		<br/>
			                                    		${order.createTime?string("yyyy-MM-dd HH:mm:ss")?substring(11)}
													</#if>
		                                    	</font>
		                                    </td>
		                                    <td>
		                                    	<b>
			                                    	<font color="#FF6600">
			                                    		<#if order.giftOrderStatus??>
															<#if order.giftOrderStatus=="0">
																未发货
															<#elseif order.giftOrderStatus=="1">
																已发货
															<#elseif order.giftOrderStatus=="2">
																已完成
															</#if>
														</#if>
			                                    	</font>
		                                    	</b></td>
		                                    <td>
	                                            <#if order.giftOrderStatus=="1">
	                                        		<a style="display:block;" class="order_btn1"  href="javascript:void(0)" onclick="comfirmgift('${basePath}/customer/comfirmofgiftorder-giftorder-${(order.giftOrderId)!''}.html')"  >确认收货</a>
	                                            </#if>
		                                        <!--商品id-->
		                                        <a  style="display:initial;" target="_blank" href="${basePath}/customer/giftorderdetail-${(order.giftOrderId)!''}.html">查看</a><br/>
		                                        <!--isBackOrder：后台设置是否允许退单  0：不允许  1：允许-->
		                                    </td>
		                                </tr>
	                                </#list>
                                <#else>
	                                <tr>
	                                    <td colspan="7">暂无订单！</td>
	                                </tr>
                                 </#if>
                            </table>
                            <#if (pb.list?size!=0)>
	                            <#-- 分页 -->
	                            <#import "../pagination/pageBean.ftl" as page>
	                            <@page.pagination pb />
                            </#if>
                        </div>
                        <div class="layout">
                            <table class="orders common_table">
                                <tr>
                                    <th width="100">订单编号</th>
                                    <th width="270">订单商品</th>
                                    <th width="100">收货人</th>
                                    <th width="80">兑换积分</th>
                                    <th width="100">下单时间</th>
                                    <th width="90">订单状态</th>
                                    <th width="70">操作</th>
                                </tr>
                                <tr>
                                    <td colspan="7">暂无订单！</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div><!-- END OF member_right -->
            <div class="cb"></div>
        </div><!-- END OF member_box -->
    </div>
     
    <div class="dialog s_dia dia3">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" alt="" src="${basePath}/images/q_mark.png" />
            	<em>确定确认收货吗？小心钱货两空哦！</em>
            </div>
            <div class="dia_ops mt20 tr">       
                <a class="go_pay" id="go_pay_01" href="javascript:;">确定</a>
                <a class="go_shopping" href="javascript:cls();">取消</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
    
    
   
    
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
    $(".memeber_left div:eq(0) ul li:eq(1)").addClass("cur");
    $(".pro_sort").addClass("pro_sort_close");
    
    jQuery.fn.isChildAndSelfOf = function(b){ return (this.closest(b).length > 0); };
    $(".mn_sel").click(function(){
    	$(this).next(".selCont").show();
    	$(".sel_txa").hide();
    	$(".err_tip").hide();
    	$(".input_tip").hide();
    	$(document).click(function(event){
			if(!$(event.target).isChildAndSelfOf(".mn_sel, .selCont")) {
				$(".selCont").hide();
				if( $("#other_yy").prop("checked")){
					$(".sel_txa").show();
				}
			};
		});
    });
    $(".selCont input").click(function(){
    	$(".mn_sel").html($(this).val());
    	$("#rea_hid").val($(this).val());
    	$(".selCont").hide();
    	$(".sel_txa").hide();
    });
    $("#other_yy").click(function(){
    	$(".sel_txa").show();
    });
});
</script>
</@htmlBody>
