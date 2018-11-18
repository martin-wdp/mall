<#include "../include/common.ftl">
<@htmlHead title='${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/index_two/css/header.css" />
<style>
    html {overflow-x:hidden;}
</style>
</@htmlHead>
<@htmlBody>
	<#if (topmap.temp)??>
			<#if (topmap.temp.tempId==8)>
				<#include "../index/newtop3.ftl">
			<#elseif (topmap.temp.tempId==9)>
				<#include "../index/newtop4.ftl">
			<#elseif (topmap.temp.tempId==10)>
				<#include "../index/newtop5.ftl">
			<#elseif (topmap.temp.tempId==11)>
				<#include "../index/newtop6.ftl">
			<#elseif (topmap.temp.tempId==12)>
				<#include "../index/newtop7.ftl">
			<#elseif (topmap.temp.tempId==13)>
				<#include "../index/newtop8.ftl">
			<#elseif (topmap.temp.tempId==14)>
				<#include "../index/newtop9.ftl">
			<#elseif (topmap.temp.tempId==15)>
				<#include "../index/newtop10.ftl">
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
	 <div style="font-family: arial;">
    <div class="container">
	<div class = "logo fl head2">
            		<!--
        <a href="${basePath}/index.html"><img src="${basePath}/index_two/images/logo.png" alt="" /></a>
		-->
        <a href="${topmap.systembase.bsetAddress}"><img src="${topmap.systembase.bsetLogo}" alt="" /></a>
    </div>
        <div class="head_s mb20">
            <div class="fr w700 pt10">
                <div class="flow_progress2">
                    <ul>
                        <li class="step2">2.填写核对订单信息</li>
                        <li class="step3">3.提交订单成功</li>
                    </ul>
                </div>
            </div>
           <div id = "logo">
            <!--<a class="logo fl" href="${basePath}/index.html"><img alt="" src="${basePath}/images/logo.gif" /></a>-->
           </div>
            <div class="cb"></div>
        </div><!-- /head_s -->
       <form  id="subForm" action="${basePath}/order/submitmrorder.html" method="post">
        <div class="order_fill" style="position:static;">
        	<h2 class="f14 fb">填写核对订单信息</h2>
            <div class="fill_content">
            	<div class="order_fill_item">
                	<div class="consignee_show fill_item_show">
                    	<h4>收货人信息<a class="consignee_edit_btn" href="javascript:void(0)">[修改]</a></h4>
                        <table>
                        	<tr>
                        		<input type="hidden" name="custAddress" class="ch_address" />
                            	<td width="85" align="right">收货人姓名：</td>
                                <td class="addressName"></td>
                            </tr>
                            <tr>
                            	<td align="right">省市地区：</td>
                                <td class="addressCity"></td>
                            </tr>
                            <tr>
                            	<td align="right">详细地址：</td>
                                <td class="addressDetail"></td>
                            </tr>
                            <tr>
                            	<td align="right">手机号码：</td>
                                <td class="addressMobi"></td>
                            </tr>
                            <tr>
                            	<td align="right">固定电话：</td>
                                <td class="addressPhone"></td>
                            </tr>
                            <tr>
                            	<td align="right">邮政编码：</td>
                                <td class="addressPost"></td>
                            </tr>
                        </table>
                    </div>
                    <div class="consignee_edit fill_item_edit">
                    	<h4>收货人信息<a class="consignee_close_btn" href="javascript:void(0)">[关闭]</a></h4>
                        <div class="address_in">
                        	<h5 class="fb">常用地址</h5>
                            <ul class="cust_allAddress">
                            	
                            </ul>
                            <ul>
                            	<li>
									<input type="button" class="order_fill_sub set_del_address" value="设置默认收货人" />
									<input type="button" class="order_fill_sub add_ch_addressinfo" value="添加收货人信息" />
								</li>
                            </ul>
                        </div>
                        <!--save_tb_div-->
                        <div class="save_tb_div">
                        <table class="save_address_tb">
                        	<input type="hidden" class="save_update_add_id">
                        	<tr>
                            	<td width="85" align="right"><font color="red">*</font>收货人姓名：</td>
                                <td><input type="text" class="text save_add_name" onblur="checkAddressName();" name="addressName" size="10" /><label class="addressNameTip"></label></td>
                            </tr>
                            <tr>
                            	<td align="right"><font color="red">*</font>省市地区：</td>
                                <td>
                                	<select class="select" name="infoProvince" id=infoProvince >
									</select>
									<select class="select" name="infoCity" id=infoCity>
										<option selected value="">请选择</option>
									</select>
									<select class="select" name="infoCounty" id=infoCounty>
											<option selected value="">请选择</option>
									</select>
									<select class="select" name="infoStreet" id=infoStreet>
											<option selected value="">请选择</option>
									</select>
                                </td>
                            </tr>
                            <tr>
                            	<td align="right"><font color="red">*</font>详细地址：</td>
                                <td><input type="text" name="addressDetail" onblur="checkAddressDetail();" class="text save_add_detail" size="50" /><label class="addressDetailTip"></label></td>
                            </tr>
                            <tr>
                            	<td align="right"><font color="red">*</font>手机号码：</td>
                                <td><input type="text" class="text save_add_mobile" onblur="checkAddressMobile();" name="addressMoblie" size="30" /><label class="addressMobiTip">用于接收发货通知短信及送货前确认</label></td>
                            </tr>
                            <tr>
                            	<td align="right">固定电话：</td>
                                <td><input type="text" class="text save_add_phone" onblur="checkAddressPhone();" size="30" name="addressPhone" /><label class="addressPhoneTip">格式:XXXX-XXXXXXXX</label></td>
                            </tr>
                            <tr>
                            	<td align="right">邮政编码：</td>
                                <td><input type="text" class="text save_add_post" size="20" name="addressZip" onblur="checkAddressPost();" /><label class="addPostTips">有助于快速确定送货地址</label></td>
                            </tr>
                            <tr>
                            	<td align="right">地址别名：</td>
                                <td><input type="text" class="text save_add_alias" size="20" name="addressAlias" /><label> 例如“自己家”“公司地址”等</label></td>
                            </tr>
                            <tr>
                            	<td colspan="2"><input type="button" class="order_fill_sub save_address" value="保存收货地址" /></td>
                            </tr>
                        </table>
                        </div>
                         <!--save_tb_div-->
                    </div>
                </div>
                <div class="order_fill_item">
                	<div class="invoice_show fill_item_show">
                        <h4>发票信息<a class="invoice_edit_btn" href="javascript:void(0)">[修改]</a></h4>
                        <table>
                            <tr>
                                <td width="85" align="right">发票类型：</td>
                                <td class="ch_invoiceType">普通发票</td>
                                <input type="hidden" name="chInvoiceType" class="ch_invoceTypeValue" value="1" />
                            </tr>
                            <tr>
                                <td align="right">发票抬头：</td>
                                <td class="ch_invoiceTitle">个人</td>
                                <input type="hidden" name="chInvoiceTitle" class="ch_invoceTitleValue" value="" />
                            </tr>
                            <tr>
                                <td align="right">发票内容：</td>
                                <td class="invoiceContent">明细</td>
                                <input type="hidden" name="chInvoiceContent" class="ch_invoiceContentValue" value="明细"/>
                            </tr>
                        </table>
                    </div>
                    <div class="invoice_edit fill_item_edit">
                        <h4>发票信息<a class="invoice_close_btn" href="javascript:void(0)">[关闭]</a></h4>
                        <table>
                        	<tr>
                            	<td width="85" align="right">发票类型</td>
                                <td>
                                	<input type="radio" name="invoiceType" class="invoiceType invoiceType_1" value="1" checked="checked" /><label>普通发票</label>&nbsp;&nbsp;
                                </td>
                            </tr>
                            <tr>
                            	<td width="85" align="right">发票抬头</td>
                                <td>
                                	<input type="radio" name="invoiceTit" class="invoiceTit invoiceTit_1" value="1" onclick="checkGeRen(this);" checked="checked" /><label>个人</label>&nbsp;&nbsp;
                                    <input type="radio" name="invoiceTit" class="invoiceTit invoiceTit_2" value="2" onclick="checkDanWei(this);" /><label>单位</label>
                                    <input type="text" name="invoiceTitle" value="" class="text invoiceTitle none" />
                                    <label class="invoiceTitleTip"></label>
                                </td>
                            </tr>
                            <tr>
                            	<td width="85" align="right">发票内容</td>
                                <td>
                                	<input type="radio" name="invoiceContent" class="invoiceContent invoiceContent_1" value="1" checked="checked" /><label>明细</label>&nbsp;&nbsp;
                                    <input type="radio" name="invoiceContent" class="invoiceContent invoiceContent_2" value="2" /><label>办公用品（附购物清单）</label>&nbsp;&nbsp;
                                    <input type="radio" name="invoiceContent" class="invoiceContent invoiceContent_3" value="3" /><label>电脑配件</label>&nbsp;&nbsp;
                                    <input type="radio" name="invoiceContent" class="invoiceContent invoiceContent_4" value="4" /><label>耗材</label>
                                </td>
                            </tr>
                            <tr>
                            	<td colspan="2"><input type="button" class="order_fill_sub save_invoice" value="保存发票信息" /></td>
                            </tr>
                        </table>
                    </div>
                </div>
                
                <div>	
                	
                </div>
         		<div class="order_fill_item">
                	<div class="goods_show fill_item_show">
                        <h4>商品清单</h4>
                        <table class="goods_list mb10">
                            <tr class="t_head">
                                <td width="100">商品编号</td>
                                <td width="400">抢购商品信息</td>
                                <td>单价</td>
                                <td>数量</td>
                                <td>总价</td>
                                
                            </tr>
                            <tr>
                                <td>${rushs.goodsProductVo.goodsInfoItemNo}</td>
                                <td><a href="${basePath}/groupon/${rushs.goodsProductVo.goodsInfoId}">${rushs.goodsProductVo.productName}</a></td>
                                <td style="color:#e64f25;">￥${(rushs.goodsProductVo.goodsInfoPreferPrice*rushs.marketing.rushs[0].rushDiscount)?string("0.00")}</td>
                                <input type="hidden" id="goodsPrice" value="${rushs.goodsProductVo.goodsInfoPreferPrice*rushs.marketing.rushs[0].rushDiscount}">
                                <input type="hidden" id="goodsStock" value="${rushs.goodsProductVo.goodsInfoStock}">
                                <td>
                                	<div class="cell g_count">
					                    <div class="count">
											<input type="hidden" value="${rushs.goodsProductVo.goodsInfoId}" name="productId">
					                        <a href="javascript:void(0);" class="decrement amount_reduce">-</a>
					                        <input type="text" id="goodNum" name="productNum" class="amount_num" value="1" readonly="readonly">
					                        <a href="javascript:void(0);" class="increment amount_add">+</a>
					                    </div>
					                </div>
					            </td>
                                <td style="color:#e64f25;">￥<span class="goods_priceh">￥${(rushs.goodsProductVo.goodsInfoPreferPrice*rushs.marketing.rushs[0].rushDiscount)?string("0.00")}</span></td>
                            </tr>
                            
                        </table>
                    </div>
                </div>
                

                <div class="order_fill_item">
                	<div class="ware_show fill_item_show">
                        <h4>结算信息</h4>
                        <div class="ware_info">
                        	<div class="money_count mb10">
                            	<p class="f16"><span>商品金额：	<span class="goods_priceh">0</span>元</span></p>
                            </div>
                            
                            
                        </div>
                        <div class="money_total f16 fb tr mt20">应付总额：<font color="red">￥<span class="goods_priceh">0</span></font>元</div>
                    </div>
                </div>
                
                <div class="order_submit m30"><input type="submit" class="order_sub_btn" value="提交订单" /></div>
            </div>
          
        </div><!-- /order_fill -->
    </div><!-- /container -->
    </div>	
    <#--引入底部-->
 	<#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
	
    
	</form>
	</div>
	<div class="dialog s_dia dia2">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" id="f_img" alt="" src="${basePath}/images/mod_war.png" />
            	<span id="con_00">库存不足！</span>
            </div>
            <div class="dia_ops mt20 tr">       
                 <a class="go_pay" id="go_pay_00" href="javascript:cls();">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
    <div class="dialog s_dia dia1">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" id="f_img" alt="" src="${basePath}/images/mod_succ.png" />
            	<em id="con_01">添加成功！</em>
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
        		<img class="vm mr10" id="f_img" alt="" src="${basePath}/images/mod_war.png" />
            	<em id="con_01">手机已存在！</em>
            </div>
            <div class="dia_ops mt20 tr">       
                 <a class="go_pay" id="go_pay_00" href="javascript:cls();">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/customaddress.js"></script>
<script type="text/javascript" src="${basePath}/js/order/loadAddress.js"></script>
<script type="text/javascript" src="${basePath}/js/order/order_common.js"></script>
<script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
<script type="text/javascript" src="${basePath}/index_two/js/index.js"></script>
<script type="application/javascript">

	$(function(){
	  check();
	 $('.amount_num').attr('readOnly',true);//设定为只读，不可输入
		var maxNum = 99;
		var stock=$(goodsStock).val();
		$('.amount_add').click(function(){
			var amount = parseInt($(this).prev().val()) + 1;
			if(amount <= maxNum){
			
				if(amount<=stock){
					$(this).prev().val(amount);
					check();
				}else{
					$("#con_00").html("库存不足");
					dia(2);
				}
			}
			else{
				$("#con_00").html("'最大购买数量不得超过'+maxNum+'个'");
				dia(2);
			}
		});
		$('.amount_reduce').click(function(){
			var amount = parseInt($(this).next().val()) - 1;
			if(amount > 0){
				$(this).next().val(amount);
				check();
			}
			else{
				$("#con_00").html("购买数量不得低于1");
				dia(2);
			}
		});  
			   
	 })
	function showMarketing(third){
		var cartIds=$(".shoppingCartId"+third);
		var arr=new Array(); 
		for(var i=0;i<cartIds.length;i++){
			arr.push(cartIds[i].value);
		}
		 $.post("../changeshoppingcartordermarkets/"+id+"-"+orderMarketId, function (data) 
		 { 
		     alert("Data Loaded: " + data);
		 });
	}
	
	function check(){
		var price=$(goodsPrice).val();
		var amount = $(goodNum).val();
		$(".goods_priceh").html(accMul(price,amount));
		
	}
	function changemarketing(id){
			var marketId = document.getElementsByName("market_Ids");
			var arrIds=new Array();
			for(var i=0;i<marketId.length;i++){
				arrIds.push(marketId[i].value);
			}
			
			var thirdIds=document.getElementsByName("thirdIds");
			var arr=new Array(); 
			for(var i=0;i<thirdIds.length;i++){
				arr.push(thirdIds[i].value);
			}
			
			$(".market_id").prop("value",arrIds);
			$(".third_id").prop("value",arr);
		  	$("#sub_order").submit();
			  
	}
	$(document).ready(function(){
		$('.consignee_edit_btn').click(function(){
			$('.consignee_show').hide();
			$('.consignee_edit').show();
		});
		$('.consignee_close_btn').click(function(){
			$('.consignee_show').show();
			$('.consignee_edit').hide();
			$(".save_tb_div").hide();
		});
		$('.payment_edit_btn').click(function(){
			$('.payment_show').hide();
			$('.payment_edit').show();
		});
		$('.payment_close_btn').click(function(){
			$('.payment_show').show();
			$('.payment_edit').hide();
		});
		$('.invoice_edit_btn').click(function(){
			$('.invoice_show').hide();
			$('.invoice_edit').show();
		});
		$('.invoice_close_btn').click(function(){
			$('.invoice_show').show();
			$('.invoice_edit').hide();
		});
		var str;
		var strstrs;
		var yz;
		$('.open_coupon').click(function(){
			str = $(this).attr("class");   
			strstrs = str.split(" "); 
			yz = $.inArray("close_coupon", strstrs);
			if(yz > 0){
				$(this).removeClass('close_coupon');
				$('.use_coupon').hide();
			}
			else{
				$(this).addClass('close_coupon');
				$('.use_coupon').show();
			}
		});
		$('.use_ent').click(function(){
			$('.ent_coupon').toggle('slow');
		});
		$('.use_coupon .close_btn').click(function(){
			$('.open_coupon').removeClass('close_coupon');
			$(this).parent().hide();
		});
		<!-- 加载省份 -->
		loadProvice();
	});
	
	function couponChange(obj,num){
		$("#yh").html(num);
		$("#yhprice").val(num);
		var payPrice = $("#payPrice").val();
		var sum = Subtr(payPrice,num);
		var lastpay = accAdd(sum,$("#yfprice").val());
		$("#lastpay").html(lastpay);
		$(".us").html('1');
		$(".up").html(num);
		$("#codeNo").val($(obj).val());
	}
	
	function submitForm(){
		var addressName = $(".addressName").html();
		if(addressName==''){
		    $("#con_00").html("请选择收货人信息！！");
			dia(2);
		}else{
			$("#subForm").prop("action","submitorder.html").submit();
		}
	}
</script>

<script>
	$(function(){
	
		$(".change_promotion").click(function(){
			var p_x = $(this).offset().left;
			var p_y = $(this).offset().top;
			$(this).next().next().show();
			$(this).next().next(".dialog").css({
				left: p_x,
				top: p_y + 20 + 'px'
			});
		});
	
	});
</script>
</@htmlBody>