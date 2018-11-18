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
    .order_list table tr td{  border-bottom: 10px solid #F6F6F6;}
    #backtable tr {
        padding-top:5px;
    }
    .red{color:red;}
    .black{color:black;}
</style>
</@htmlHead>
<#--
<html xmlns="http://www.w3.org/1999/xhtml">
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
    .order_list table tr td{  border-bottom: 10px solid #F6F6F6;}
	#backtable tr {   
		padding-top:5px;
	}
	.red{color:red;}
	.black{color:black;}
</style>
</head>
-->
<@htmlBody>
    	<#--一引入头部 <#include "/index/topnew.ftl" />  -->	
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
            <span>我的订单</span>
        </div>
        <div class="member_box mb20">
            <#include "leftmenu.ftl" >
            <div class="member_right fl ml10">
                <div class="memebr_title mb20">
                    <h2 class="f14 fb">订单中心</h2>
                </div>
                <div class="order_list switch_border">
                	<div class="tagMenu tagMenu1">
                        <ul class="groupon_tabs clearfix abb">
                           <li <#if (!type?? || type == '0') >class="cur"</#if> data-val="0"><a href="javascript:;" >团购订单</a></li>
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
                            <select class="select s1 groupon">
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
                            <input type="hidden" id="hi_type" value="${type!''}" />
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
                                    <th width="80">订单金额</th>
                                    <th width="100">下单时间</th>
                                    <th width="90">订单状态</th>
                                    <th width="70">操作</th>
                                </tr>
                                <#if (pb.list?size!=0)>
	                                <#list pb.list as order>
		                                <tr>
		                                    <td>
		                                    	<#if order.orderCode??>
													${order.orderCode}
												 </#if>
		                                    </td>
		                                    <td class="goods">
			                                <a target="black" title="${order.goodsProductVo.goodsInfoId!''}" href="${basePath}/item/${order.goodsProductVo.goodsInfoId}"><img width="50" height="50" title="${(order.goodsProductVo.goodsInfoName)!''}" alt="${(order.goodsProductVo.goodsInfoName)!''}" src="${order.goodsProductVo.goodsInfoImgId}"/></a>
                        					 <#--  	<a target="black" href="${basePath}/item/${order.goodsProductVo.goodsInfoId}"><img width="300px" height="260px"  src="<#if order.goodsProductVo.imageList?? && order.goodsProductVo.imageList[0]??>${groupons.goodsProductVo.imageList[0].imageInName!''}</#if>"/></a>
			                                    	<a target="black" title="${good.goodsName!''}" href="${basePath}/item/${good.goodsId}.html"><img width="50" height="50" title="${(good.goodsName)!''}" alt="${(good.goodsName)!''}" src="${(good.goodsImg)!''}" /></a>
		                                   -->
		                                    </td>
		                                    <td>
			                                    <#if order.shippingPerson??>
													${order.shippingPerson}
												</#if>
		                                    </td>
		                                    <td>
			                                    <font color="red" style="font-family: '微软雅黑'">￥
				                                    <#if order.orderPrice??>
														${order.orderPrice?string('0.00')}
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
			                                    		<#if order.orderStatus??>
															<#if order.orderStatus=="0">
																未付款
															<#elseif order.orderStatus=="1">
																已付款
															<#elseif order.orderStatus=="2">
																已发货
															<#elseif order.orderStatus=="3">
																已完成
															<#elseif order.orderStatus=="4">
																已取消
															</#if>
														</#if>
			                                    	</font>
		                                    	</b></td>
		                                    <td>
		                                        <#if order.orderStatus=="0">
		                                        		<a class="order_btn1" style="display:block;"  target="_blank" href="${basePath}/gopayorder-${order.orderId}.html">去付款</a>
		                                         <#elseif order.orderStatus=="2">
		                                        	<a style="display:block;" class="order_btn1"  href="javascript:void(0)" onclick="comfirmgoods('${basePath}/customer/comfirmofmarket-marketordergrounp-${order.orderId}.html')"  >确认收货</a>
		                                       </#if>
		                                        <!--商品id-->
		                                        <a  style="display:initial;" target="_blank" href="${basePath}/customer/selectrushdetails-${order.orderId}.html">查看</a><br/>
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
    
    
    <!--退货-->
    <div id="dialog-form" class="dialog s_dia dia5" style=" height:240px; width:350px;">
    	<div class="dia_tit clearfix">
            <h4 class="fl">信息提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<form id="returnGoods_from" method="post">
        		<div style=" padding-top: 10px; padding-left:20px; font-size: 12px; font-weight:bold;">亲！请填写您要退单的原因！</div>
        		<table style="height:100px;margin-top:20px; margin-left:10px;" id="backtable">
        			<tr>
        				<td>
        					<textarea  rows="4" cols="45" onBlur="jiaodianyuanyin()" style=" padding-left:10px; border-radius:8px;" name="returnyuanyin" maxlength="100" id="returnyuanyin" ></textarea>
        				</td>
        			<tr/>
        			<tr>
        				<td style="font-size: 12px; margin-left: 180px;" class="err_yuanyin pl10"></td>
        			<tr/>
        		</table>
	            <div class="dia_ops mt20 tr" style="text-align: center;" >      
	            	<input type="button" class="go_pay" id="go_pay_01"  onclick="returngoods_queding();" value="确定"/> 
	            	<input type="button" class="go_pay" id="go_pay_00" onclick="quxiao()"  value="取消"/> 
	            </div>
            </form>
        </div><!--/dia_cont-->
    </div>
    
    
    
    
    
    <div class="dialog s_dia dia2" style="width:400px;">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30" style="height:200px;margin-left:20px;">
        		<img class="vm mr10" alt="" src="${basePath}/images/q_mark.png" />
            	<em>取消订单原因:</em>
            	<div class="mn_sel"></div>
            	<div class="selCont">
            		<label><input class="vm mr5" name="res" type="radio" value="现在不想买" />现在不想买</label>
            		<label><input class="vm mr5" name="res" type="radio" value="商品价格较贵" />商品价格较贵</label>
            		<label><input class="vm mr5" name="res" type="radio" value="价格波动"/>价格波动</label>
            		<label><input class="vm mr5" name="res" type="radio" value="商品缺货"/>商品缺货</label>
            		<label><input class="vm mr5" name="res" type="radio" value="重复下单"/>重复下单</label>
            		<label><input class="vm mr5" name="res" type="radio" value="添加或删除商品"/>添加或删除商品</label>
            		<label><input class="vm mr5" name="res" type="radio" value="收货人信息有误"/>收货人信息有误</label>
            		<label><input class="vm mr5" name="res" type="radio" value="发票信息有误/发票未开"/>发票信息有误/发票未开</label>
            		<label><input class="vm mr5" name="res" type="radio" value="送货时间过长"/>送货时间过长</label>
            		<label><input class="vm mr5" name="res" id="other_yy" type="radio" value="其他原因" />其他原因</label>
            	</div>
            	<div class="err_tip" style="color:red;width: 350px;text-align:right;margin-top: 10px;display:none;">请选择取消原因!</div>
            	<textarea class="sel_txa"></textarea>
            	<div class="input_tip" style="color:red;width: 350px;text-align:right;margin-top: 10px;display:none;">请输入10个以上字符!</div>
            </div>
            <div class="dia_ops mt20 tr">       
                <a class="go_pay" id="go_pay_00" href="javascript:;" onclick="changeUrl();">确定</a>
                <a class="go_shopping" href="javascript:cls();">取消</a>
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
<script type="text/javascript" src="${basePath}/js/customer/customer.js"></script>
<script type="text/javascript">
$(".s2 option[value='"+$("#hi_type").val()+"']").prop("selected","selected"); 
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
    $(".memeber_left div:eq(0) ul li:eq(3)").addClass("cur");
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
