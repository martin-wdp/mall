<#include "../include/common.ftl">
<@htmlHead title='${topmap.systembase.bsetName}'>
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
    <#if (topmap.temp.tempId!=10)>
	<div class = "logo fl head2">
            		<!--
        <a href="${basePath}/index.html"><img src="${basePath}/index_two/images/logo.png" alt="" /></a>
		-->
        <a href="${topmap.systembase.bsetAddress}"><img src="${topmap.systembase.bsetLogo}" alt="" style="width:165px;height:40px;"/></a>
    </div>
    </#if>
        <div class="head_s mb20">
            <div class="fr w700 pt10">
                <div class="flow_progress2">
                    <ul>
                        <li class="step1">1.查看购物车</li>
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
       <form name="subForm" id="sub_order" action="${basePath}/order/submitorder.html" method="post">
       
       <input type="hidden" value="" name="marketingId" class="market_id">
 	   <input type="hidden" value="" name="thirdId" class="third_id">
 	   <input  type="hidden" id="csrfNo" name="CSRFToken" value="<#if sx??>${sx}</#if>" />
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
                    <div class="payment_show fill_item_show">
                        <h4>支付及配送方式<a class="payment_edit_btn" href="javascript:void(0)">[修改]</a></h4>
                        <table>
                            <tr>
                                <td width="85" align="right">支付方式：</td>
                                <td class="chectedPay">
                                 <#list payList as pl>
      							  <#if pl.isOpen=="1"><#if pl_index==0>${pl.name}</#if></#if>
                            	</#list>
                            	
                            	</td>
                                <input type="hidden" name="ch_pay" class="ch_pay" />
                            </tr>
                            <tr id="checkedExpressBoss">
                                <td align="right">配送方式：</td>
                                <td class="checkedExpress_boss"><#if  frightlist?size==0>快递运输</#if></td>
                                <input type="hidden" name="ch_express" class="ch_express" />
                            </tr>
                             <tr id="checkedExpressThird">
                                <td align="right">商家配送：</td>
                                <td class="checkedExpress_third"></td>
                            </tr>
                         <!--   <tr>
                                <td align="right">运费：</td>
                                <td class="expressPrice"></td>
                            </tr>
                            <tr>
                                <td align="right">送货日期：</td>
                                <td><font color="red" class="expressDate">只工作日配送（双休、节假日不送）</font></td>
                                <input type="hidden" name="expressDate" class="ch_expressDate" />
                                <input type="hidden" name="distTrue" class="ch_distTrue" />
                            </tr>-->
                        </table>
                    </div>
                    <div class="payment_edit fill_item_edit">
                        <h4>支付及配送方式<a class="payment_close_btn" href="javascript:void(0)">[关闭]</a></h4>
                        
                        <table class="pay_choice">
                        	<tr>
                            	<td><b class="f14 fb">支付方式</b></td>
                            	  <#list payList as pl>
      							  <#if pl.isOpen=="1">
	                            	<tr><td><input  type='radio'  class='payset_radio  payset_radio_${pl_index+1}' pay-name="${pl.name}" <#if pl_index==0>checked</#if> value="${pl.paymentId}" name='paysets'><label>${pl.name}</label></td></tr>
	                            	
                            	</#if>
                            	</#list>
                            </tr>
                        </table>
                        <table class="express_choice" id="express_boss">
                        	<tr>
                            	<td><b class="f14 fb">配送方式</b><span style="padding-left:5px;font-size: 12px;font-weight: normal;color: #185D94;" id="bossnum"><span></td>  
                            </tr>
                            <tr>
                            	<td><span id="bossnum"></span></td>  
                            </tr>
                           <#if frightlist??>
                            <#list frightlist as freight>
                             <tr>
                            	<td><input type="radio" name="distributionId" id="distributionId" value="${freight.distributionId}" expressname=${freight.freightTemplate.freightTemplateName} <#if freight_index==0>checked="checked"</#if>/>

                                    ${freight.freightTemplate.freightTemplateName}
                                </td>
                            </tr>
                            </#list>
                            </#if>
                           <#if frightlist?size==0>
                            <tr>
                                <td><input type="radio" name="distributionId" value="" checked="checked"/>快递运输</td>
                            </tr>
                        </#if>
                       </table> 
                     
                        <table class="express_choice" id="express_third">
                        	<tr>
                            	<td><b class="f14 fb">商家配送</b><span style="padding-left:5px;font-size: 12px;font-weight: normal;color: #185D94;" id="thirdnum"><span></td>  
                            </tr>
                            
                             <tr>
                            	<td><input type="radio" name="distri" id="distr" value="" checked="checked"/>快递运输</td>
                            </tr>
                            
                       </table> 
                     
                            
                        <input type="button" class="order_fill_sub save_express_pay" value="保存支付方式及配送方式" />
                        
                         
                    </div>
                </div>
                <div class="order_fill_item">
                	<div class="invoice_show fill_item_show">
                        <h4>发票信息<a class="invoice_edit_btn" href="javascript:void(0)">[修改]</a></h4>
                        <table>
                            <tr>
                                <td width="85" align="right">发票类型：</td>
                                <td class="ch_invoiceType">不使用发票</td>
                                <input type="hidden" name="ch_voinceType" class="ch_invoceTypeValue" value="0" />
                            </tr>
                            <tr class="tr_invoiceTitleView" style="display:none;">
                                <td align="right">发票抬头：</td>
                                <td class="ch_invoiceTitle">个人</td>
                                <input type="hidden" name="ch_invoiceTitle" class="ch_invoceTitleValue" value="" />
                            </tr>
                            <tr class="tr_invoiceContentView" style="display:none;">
                                <td align="right">发票内容：</td>
                                <td class="invoiceContent" id="invoiceContentMing">明细</td>
                                <input type="hidden" name="ch_invoiceContent" class="ch_invoiceContentValue" value="明细"/>
                            </tr>
                        </table>
                    </div>
                    <div class="invoice_edit fill_item_edit">
                        <h4>发票信息<a class="invoice_close_btn" href="javascript:void(0)">[关闭]</a></h4>
                        <table>
                        	<tr>
                            	<td width="85" align="right">发票类型</td>
                                <td>
                                    <input type="radio" name="invoiceType" class="invoiceType invoiceType_1" value="0" checked="checked" /><label>不需要发票</label>&nbsp;&nbsp;
                                	<input type="radio" name="invoiceType" class="invoiceType invoiceType_1" value="1"/><label>普通发票</label>&nbsp;&nbsp;
                                </td>
                            </tr>
                            <tr class="tr_invoiceTitle" style="display:none">
                            	<td width="85" align="right">发票抬头</td>
                                <td>
                                	<input type="radio" name="invoiceTit" class="invoiceTit" value="1" onclick="checkGeRen(this);" checked="checked" /><label>个人</label>&nbsp;&nbsp;
                                    <input type="radio" name="invoiceTit" class="invoiceTit" value="2" onclick="checkDanWei(this);" /><label>单位</label>
                                    <input type="text" name="invoiceTitle" value="" class="text invoiceTitle none" />
                                    <label class="invoiceTitleTip"></label>
                                </td>
                            </tr>
                            <tr class="tr_invoiceContent" style="display:none;">
                            	<td width="85" align="right">发票内容</td>
                                <td>
                                	<input type="radio" name="invoiceContent" class="invoiceContent invoiceContent_1" value="1" checked="checked" /><label class="invoiceContent_label0">明细</label>&nbsp;&nbsp;
                                    <input type="radio" name="invoiceContent" class="invoiceContent invoiceContent_2" value="2" /><label class="invoiceContent_label1">办公用品（附购物清单）</label>&nbsp;&nbsp;
                                    <input type="radio" name="invoiceContent" class="invoiceContent invoiceContent_3" value="3" /><label class="invoiceContent_label2">电脑配件</label>&nbsp;&nbsp;
                                    <input type="radio" name="invoiceContent" class="invoiceContent invoiceContent_4" value="4" /><label class="invoiceContent_label3">耗材</label>
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
                <#assign sumP=0>
                  <#assign prep=0>
                <#assign orderSumP=0>
                 <!--会员总折扣-->                             
             <#assign pointDiscounts=0>
             <#list map.orderMarketings as orderMarketings>
             
              <!--会员折扣-->
              <#assign pointDiscount=0>
              <#assign straight=0>
                  <#assign payPrice=0>
                <#assign preprice=0>
                <div class="order_fill_item">
                	
                       <h4>订单
                        		<!--${orderMarketings_index+1} &nbsp; 所属商家：<#if orderMarketings.infoRealname??>${orderMarketings.infoRealname}</#if>>-->
                        		<input type="hidden" value="${orderMarketings.thirdId}" name="thirdIds" id="thirdIds"/>
                <!--	<div class="goods_show fill_item_show" style="display:inline-block;zoom:1;*display:inline;">
                                	<a class="change_promotion chg_pm" href="javascript:void(0);">修改优惠</a>
                                	<span class="ch_des ml10">
                                	优惠内容：
                                	 <#if orderMarketings.marketing??>
                                		<#if  orderMarketings.marketing.giftList?? >
					              	   <#if orderMarketings.marketing.fullbuyPresentMarketing??>
										<#else>
											   	买送赠品
									 	</#if>
									 </#if>
									  <#if orderMarketings.marketing.couponList??  >
									  <#if orderMarketings.marketing.fullbuyCouponMarketing??>
									  <#else>
										    送优惠券
										    
									</#if>
				                	 </#if>
				            		 <#if orderMarketings.marketing.fullbuyPresentMarketing??>
										满￥${orderMarketings.marketing.fullbuyPresentMarketing.fullPrice } 送赠品
											                
									</#if>
									 <#if orderMarketings.marketing.fullbuyCouponMarketing?? >
										满￥${orderMarketings.marketing.fullbuyCouponMarketing.fullPrice } 送优惠券  
									</#if>
									<#if (orderMarketings.marketing.priceOffMarketing?? )>
										直降￥${orderMarketings.marketing.priceOffMarketing.offValue}
				              	 	 </#if>
				                    <#if orderMarketings.marketing.discountMarketing??> 
				                                                           折扣:${orderMarketings.marketing.discountMarketing.discountValue}

				              		 </#if>
				                 	<#if orderMarketings.marketing.fullbuyReduceMarketing??>
				                 	满￥${orderMarketings.marketing.fullbuyReduceMarketing.fullPrice}减￥${orderMarketings.marketing.fullbuyReduceMarketing.reducePrice}
					               </#if>
				            
				                    <#if orderMarketings.marketing.fullbuyDiscountMarketing??>
				              	        满￥${orderMarketings.marketing.fullbuyDiscountMarketing.fullPrice}折${orderMarketings.marketing.fullbuyDiscountMarketing.fullbuyDiscount}
					               			<input type="hidden" class="codexType" value="8"/>
					                		<input type="hidden" class="fullPrice" value="${orderMarketings.marketing.fullbuyDiscountMarketing.fullPrice}"/>
							  		 	 	<input type="hidden" class="fullbuyDiscount" value="${orderMarketings.marketing.fullbuyDiscountMarketing.fullbuyDiscount}"/>
					                </#if>
                                </#if>
                                </span>
                                     <div class="dialog yh_dialog promotion_dialog${orderMarketings.thirdId}">
                                    <div class="dialog-outer">
                                    <span class="dialog-bg dialog-bg-n">
                                    </span>
                                    <span class="dialog-bg dialog-bg-ne">
                                    </span>
                                    <span class="dialog-bg dialog-bg-e">
                                    </span>
                                    <span class="dialog-bg dialog-bg-se">
                                    </span>
                                    <span class="dialog-bg dialog-bg-s">
                                    </span>
                                    <span class="dialog-bg dialog-bg-sw">
                                    </span>
                                    <span class="dialog-bg dialog-bg-w">
                                    </span>
                                    <span class="dialog-bg dialog-bg-nw">
                                    </span>
                                    <div class="dialog-inner">
                                        <div class="dialog-toolbar clearfix">
                                            <a class="dialog-close" href="javascript:void(0);" title="关闭" onclick="javascript:$('.promotion_dialog${orderMarketings.thirdId}').hide();">
                                                关闭
                                            </a>
                                            <h3 class="dialog-title">
                                                请选择优惠:
                                                 
                                            </h3>
                                        </div>
                                        <div class="dialog-content clearfix">
                                            <div class="p10 tc w300">
                                                <label>修改订单优惠:</label>
                                               <select style="width:120px;" name="market_Ids"  id="market${orderMarketings.thirdId}">
                                                    		<option value="0">不使用优惠</option>
											                 
											                  <#if  (orderMarketings.marketings?? && orderMarketings.marketings?size>0)>
											                  <#list orderMarketings.marketings as marketing>
											                  	<#if marketing.fullbuyDiscountMarketing??></#if>
											                    				<option <#if orderMarketings.marketing?? &&orderMarketings.marketing.marketingId??  &&orderMarketings.marketing.marketingId==marketing.marketingId>selected="selected"</#if> value="${marketing.marketingId}">${marketing.marketingName}</option>
											                  </#list>
											                  </#if>
                                                </select>
                                            <div class="tc p15">
                                                <a class="light_btn2" href="javascript:void(0);" onclick="changemarketing(${orderMarketings.thirdId});">确定</a>
                                                <a class="light_btn2" href="javascript:void(0);" onclick="javascript:$('.promotion_dialog${orderMarketings.thirdId}').hide();">取消</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- /dialog -->
                        </h4>
                        <table class="goods_list mb10">
                            <tr class="t_head">
                                <td width="100">商品编号</td>
                                <td width="400">商品名称</td>
                                <td>价格</td>
                                <td>优惠金额</td>
                                <td>赠送积分</td>
                                <td>库存状态</td>
                                <td>数量</td>
                                <td>小计</td>
                            </tr>  
                               <#assign sumprice=0>
                            <#assign paySum=0>
                           <#list map.shoplist as cart>
                           <#if cart.fitId??>
                           <#if cart.goodsGroupVo.thirdId?? && cart.goodsGroupVo.thirdId==orderMarketings.thirdId>
                           <tr>
                                <td><input type="hidden" class="shoppingCartId${orderMarketings.thirdId}" name="shoppingCartId" value="${cart.shoppingCartId}"/>[套装] </td>
                                <input type="hidden"  name="box" class="box" value="${cart.shoppingCartId}" productPrice="${cart.goodsGroupVo.groupPreferamount}" productNum="${cart.goodsNum}"/>
                                 <input type="hidden"  name="isbaoyou" value="0"/>  
                                <td>${cart.goodsGroupVo.groupName}
                                </td>
                                <td>
	                                                                                             ￥${cart.goodsGroupVo.groupPreferamount?string("0.00")}
	                            </td>
                                <td>
					                		 ￥ -0.00
                               	 <#assign sumprice="${sumprice?number+cart.goodsGroupVo.groupPreferamount*cart.goodsNum}">
                               	 <#assign paySum='${paySum?number+(cart.goodsGroupVo.groupPreferamount*cart.goodsNum)?number}'>
                                </td>
                                <td>0</td>
                                <td>
                              	
                              </td>
                                <td><#if cart.goodsNum??>${cart.goodsNum}</#if></td>
                                <td>
                               			${(cart.goodsGroupVo.groupPreferamount*cart.goodsNum)?string("0.00")}
                                </td>
                            </tr>
                            <#list  cart.goodsGroupVo.productList as products>
                            	<tr>
                                <td>${products.productDetail.goodsInfoItemNo}</td>
                                <td>${products.productDetail.goodsInfoName}
                                </td>
                                <td></td>
                                <td>
					                		
                                
                                </td>
                                <td>0</td>
                                <td>
                             
                              	<#if (products.productDetail.goodsInfoStock>0)>
                        			现货
	                        	<#else>
	                        		无货
	                        	</#if>  
                              </td>
                                <td><#if cart.goodsNum??>${cart.goodsNum}</#if></td>
                                <td>
                               			
                                </td>
                            </tr>
                            </#list>
                            </#if>
                           <#else>
                           <#if cart.goodsDetailBean.productVo.thirdId?? && cart.goodsDetailBean.productVo.thirdId==orderMarketings.thirdId>
                            
                            <tr>
                                <td><input type="hidden" class="shoppingCartId${orderMarketings.thirdId}" name="shoppingCartId" value="${cart.shoppingCartId}"/> ${cart.goodsDetailBean.productVo.goodsInfoItemNo}</td>
                                <input type="hidden"  name="box" value="${cart.shoppingCartId}"/>  
                                <input type="hidden"  name="isbaoyou" value="${cart.isBaoyou}"/> 
                                <td>
                               		 <a href="${basePath}/item/${cart.goodsDetailBean.productVo.goodsInfoId}.html" target="_blank" title="${basePath}/item/${cart.goodsDetailBean.productVo.goodsInfoId}.html"><img style="width:30px;height:30px;" title="${cart.goodsDetailBean.productVo.productName}" alt="${cart.goodsDetailBean.productVo.productName}" src="<#if cart.goodsDetailBean.productVo.goodsInfoImgId??>${cart.goodsDetailBean.productVo.goodsInfoImgId}</#if>" /> ${cart.goodsDetailBean.productVo.productName}
                               		 <#if cart.isBaoyou=='1'>
                                		<img style="width:30px;heigth:30px;" title="包邮" alt="包邮" src="${basePath}/images/freepost.png" />
                                	</#if>
                               		 </a>
                                </td>
                                <td> 
                                  <#if cart.marketing??>
	                                <#if (cart.marketing.codexType='1')>
	              						   <#if ((cart.goodsDetailBean.productVo.goodsInfoPreferPrice-cart.marketing.priceOffMarketing.offValue)?number &gt; 0 ) > 
									                               ￥${(cart.goodsDetailBean.productVo.goodsInfoPreferPrice-cart.marketing.priceOffMarketing.offValue)?string("0.00")}
									           <#assign straight="${straight?number+(cart.goodsNum*cart.marketing.priceOffMarketing.offValue)}">
										 <#else>
											     ￥0.01
											   <#assign straight="${straight?number+(cart.goodsNum*(cart.goodsDetailBean.productVo.goodsInfoPreferPrice-0.01))}">
										 </#if>							           	
							        <#elseif cart.marketing.codexType='4'>
						               <#if (cart.goodsDetailBean.productVo.goodsInfoPreferPrice-((1-cart.marketing.discountMarketing.discountValue)*cart.goodsDetailBean.productVo.goodsInfoPreferPrice))?number!=0>
												￥ ${((cart.goodsDetailBean.productVo.goodsInfoPreferPrice-((1-cart.marketing.discountMarketing.discountValue)*cart.goodsDetailBean.productVo.goodsInfoPreferPrice)))?string("0.00")}
												<#assign straight="${straight?number+(cart.goodsNum*((1-cart.marketing.discountMarketing.discountValue)*cart.goodsDetailBean.productVo.goodsInfoPreferPrice))}">
											<#else>
												￥ ${cart.goodsDetailBean.productVo.goodsInfoPreferPrice?string("0.00")}
											</#if>
										<#else> 
		                                 	    ￥${cart.goodsDetailBean.productVo.goodsInfoPreferPrice?string("0.00")}                                                      
		                            </#if>   
		                            <#else>
		                            	     ￥${cart.goodsDetailBean.productVo.goodsInfoPreferPrice?string("0.00")}
		                             </#if>        
	                             </td>
                                <td>
                             
                                <#if cart.marketing??>
              							 <#if cart.marketing.codexType='5'>
						                 		<#if cart.marketing.fullbuyReduceMarketing.fullPrice<=(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)>
								                 <#if (((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-cart.marketing.fullbuyReduceMarketing.reducePrice)?number &gt; 0 ) > 
											   	   	     ￥-${(cart.marketing.fullbuyReduceMarketing.reducePrice)?string("0.00") }
											          <#assign preprice="${preprice?number+(cart.marketing.fullbuyReduceMarketing.reducePrice)}">
												 <#else>
												 	         ￥-${(straight?number+((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-0.01))?string("0.00")}
													   <#assign preprice="${straight?number+((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-0.01)}">
												 </#if>	
						                 		<#else>
						                			  ￥ -0.00
						                		</#if>
						                <#elseif cart.marketing.codexType='8'>
						                	<#if (cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum>=cart.marketing.fullbuyDiscountMarketing.fullPrice)>
						                		  
						                		<#if (cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum-(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum*(1-cart.marketing.fullbuyDiscountMarketing.fullbuyDiscount)))?number!=0>
													<#assign preprice="${preprice?number+(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum*(1-cart.marketing.fullbuyDiscountMarketing.fullbuyDiscount))}">
													￥- ${(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum*(1-cart.marketing.fullbuyDiscountMarketing.fullbuyDiscount))?string("0.00")}
												<#else>
														￥- ${(((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-0.01))?string("0.00")}
													  <#assign preprice="${straight?number+((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-0.01)}">
												</#if>
						                	<#else>
						                			  ￥ -0.00
						                	</#if>
						                	
						                <#elseif cart.marketing.codexType='13'>
							                <#if cart.marketing.fullbuyNoDiscountMarketing.isMeetCondition??>
						 				    	<#if cart.marketing.fullbuyNoDiscountMarketing.isMeetCondition = '1'>
						 				    		<#if (cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum-(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum*(1-cart.marketing.fullbuyNoDiscountMarketing.packagebuyDiscount)))?number!=0>
						 				    			<#assign preprice="${preprice?number+(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum*(1-cart.marketing.fullbuyNoDiscountMarketing.packagebuyDiscount))}">
														￥- ${(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum*(1-cart.marketing.fullbuyNoDiscountMarketing.packagebuyDiscount))?string("0.00")}
						 				    		<#else>
						 				    			￥- ${(((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-0.01))?string("0.00")}
													  <#assign preprice="${straight?number+((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-0.01)}">
						 				    		</#if>
						 				    	<#else>
						 				    		￥ -0.00
							 				    </#if>
							 				<#else>
							 					￥ -0.00    	
						 				    </#if>
						                <#elseif cart.marketing.codexType='15'>
                                            <#if cart.marketing.preDiscountMarketing.discountPrice??>
													<#if (cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum-(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum*(1-cart.marketing.preDiscountMarketing.discountInfo)))?number!=0>
							 				    			<#assign preprice="${preprice?number+((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-(cart.marketing.preDiscountMarketing.discountPrice*cart.goodsNum))}">
															￥- ${((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-(cart.marketing.preDiscountMarketing.discountPrice*cart.goodsNum))?string("0.00")}
						 				    		<#else>
						 				    			￥- ${(((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-0.01))?string("0.00")}
													  <#assign preprice="${straight?number+((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-0.01)}">
						 				    		</#if>
                                            <#else>
                                                <#if (cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum-(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum*(1-cart.marketing.preDiscountMarketing.discountInfo)))?number!=0>
                                                    <#assign preprice="${preprice?number+(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum*(1-cart.marketing.preDiscountMarketing.discountInfo))}">
                                                    ￥- ${(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum*(1-cart.marketing.preDiscountMarketing.discountInfo))?string("0.00")}
                                                <#else>
                                                    ￥- ${(((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-0.01))?string("0.00")}
                                                    <#assign preprice="${straight?number+((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-0.01)}">
                                                </#if>
                                            </#if>
						                <#else>
						             		  ￥ -0.00
					              	 	 </#if>
					             <#else> 
					                		 ￥ -0.00
                                 </#if>
                                
                                </td>
                                <td>0</td>
                                <td>
              						<#if (cart.goodsDetailBean.productVo.goodsInfoStock>0) >              
				        				  现货
				        			</#if>  
				        			 <#if (cart.goodsDetailBean.productVo.goodsInfoStock<=0) >              
				        				  无货
				        			</#if>                
                              <#assign sumprice="${sumprice?number+cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum}">
                                <#assign sumprice="${sumprice?number-straight?number}">
                              </td>
                                <td><#if cart.goodsNum??>${cart.goodsNum}</#if></td>
                                <td>
                               
                                  <#if cart.marketing??>
              							 <#if (cart.marketing.codexType='1')>
						                	 <#if ((cart.goodsDetailBean.productVo.goodsInfoPreferPrice-cart.marketing.priceOffMarketing.offValue)?number &gt; 0 ) > 
									                               ￥${((cart.goodsDetailBean.productVo.goodsInfoPreferPrice-cart.marketing.priceOffMarketing.offValue)*cart.goodsNum)?string("0.00")}
                                                 <#assign paySum='${paySum?number+((cart.goodsDetailBean.productVo.goodsInfoPreferPrice-cart.marketing.priceOffMarketing.offValue)*cart.goodsNum)?number}'>
											 <#else>
												    ￥${0.01*cart.goodsNum}
                                                 <#assign paySum='${paySum?number+0.01*cart.goodsNum}'>
											 </#if>	
						                <#elseif cart.marketing.codexType='4'>
						                	 <#if (cart.goodsDetailBean.productVo.goodsInfoPreferPrice-((1-cart.marketing.discountMarketing.discountValue)*cart.goodsDetailBean.productVo.goodsInfoPreferPrice))?number!=0>
												 ￥${((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-(cart.goodsNum*((1-cart.marketing.discountMarketing.discountValue)*cart.goodsDetailBean.productVo.goodsInfoPreferPrice)))?string("0.00")}
                                                 <#assign paySum='${paySum?number+((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-(cart.goodsNum*((1-cart.marketing.discountMarketing.discountValue)*cart.goodsDetailBean.productVo.goodsInfoPreferPrice)))?number}'>
                                             <#else>
												 ￥${0.01*cart.goodsNum}
                                                 <#assign paySum='${paySum?number+0.01*cart.goodsNum}'>
											</#if>
					                    		
										<#elseif cart.marketing.codexType='5'>
											<#if cart.marketing.fullbuyReduceMarketing.fullPrice<(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)>
						                 		<#if (((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-cart.marketing.fullbuyReduceMarketing.reducePrice)?number &gt; 0 ) > 
											   		   ￥ ${(((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)- (cart.marketing.fullbuyReduceMarketing.reducePrice))?number)?string("0.00")  }
                                                    <#assign paySum='${paySum?number+(((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)- (cart.marketing.fullbuyReduceMarketing.reducePrice))?number)?number}'>
						                 		 <#else>
												 	         ￥0.01
                                                     <#assign paySum='${paySum?number+0.01}'>
											 	</#if>	
						                 	<#else>
						                		￥ ${(cart.goodsDetailBean.productVo.goodsInfoPreferPrice?number)?string(0.00)}
                                                <#assign paySum='${paySum?number+(cart.goodsDetailBean.productVo.goodsInfoPreferPrice?number)?number}'>
						                	</#if>
						                <#elseif cart.marketing.codexType='8'>
						                	<#if cart.marketing.fullbuyDiscountMarketing.fullPrice<(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)>
						                		 <#if (cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum-(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum*(1-cart.marketing.fullbuyDiscountMarketing.fullbuyDiscount)))?number!=0>
													   ￥ ${((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-((1-cart.marketing.fullbuyDiscountMarketing.fullbuyDiscount)*(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum) )?number)?string("0.00") }
                                                     <#assign paySum='${paySum?number+((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-((1-cart.marketing.fullbuyDiscountMarketing.fullbuyDiscount)*(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum) )?number)?number}'>
                                                 <#else>
													￥0.01
                                                     <#assign paySum='${paySum?number+0.01}'>
												</#if>
						                		
						                	<#else>
						                		￥ ${((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)?number)?string("0.00") }
                                                <#assign paySum='${paySum?number+((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)?number)?number}'>
						                	</#if>
						                <#elseif cart.marketing.codexType='13'>
						                	<#if cart.marketing.fullbuyNoDiscountMarketing.isMeetCondition??>
							                	<#if cart.marketing.fullbuyNoDiscountMarketing.isMeetCondition = '1'>
							                		<#if (cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum-(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum*(1-cart.marketing.fullbuyNoDiscountMarketing.packagebuyDiscount)))?number!=0>
														   ￥ ${((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-((1-cart.marketing.fullbuyNoDiscountMarketing.packagebuyDiscount)*(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum) )?number)?string("0.00") }
                                                        <#assign paySum='${paySum?number+((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-((1-cart.marketing.fullbuyNoDiscountMarketing.packagebuyDiscount)*(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum) )?number)?number}'>
                                                    <#else>
														￥0.01
                                                        <#assign paySum='${paySum?number+0.01}'>
													</#if>
							                	<#else>
                                                    <#assign paySum='${paySum?number+((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)?number)?number}'>
							                		￥ ${((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)?number)?string("0.00") }
							                	</#if>
							                <#else>
                                                <#assign paySum='${paySum?number+((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)?number)?number}'>
							                	￥ ${((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)?number)?string("0.00") }
							                </#if>	
						                <#elseif cart.marketing.codexType='15'>
                                                <#if cart.marketing.preDiscountMarketing.discountPrice??>
						                			<#if (cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum-((cart.goodsDetailBean.productVo.goodsInfoPreferPrice-cart.marketing.preDiscountMarketing.discountPrice)*cart.goodsNum))?number!=0>
														   ￥ ${((cart.marketing.preDiscountMarketing.discountPrice*cart.goodsNum)?number)?string("0.00") }
                                                        <#assign paySum='${paySum?number+((cart.marketing.preDiscountMarketing.discountPrice*cart.goodsNum)?number)?number}'>
                                                    <#else>
														￥0.01
                                                        <#assign paySum='${paySum?number+0.01}'>
													</#if>
                                                <#else>
                                                    <#if (cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum-(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum*(1-cart.marketing.preDiscountMarketing.discountInfo)))?number!=0>
                                                        ￥ ${((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-((1-cart.marketing.preDiscountMarketing.discountInfo)*(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum) )?number)?string("0.00") }
                                                        <#assign paySum='${paySum?number+((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-((1-cart.marketing.preDiscountMarketing.discountInfo)*(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum) )?number)?number}'>
                                                    <#else>
                                                        ￥0.01
                                                        <#assign paySum='${paySum?number+0.01}'>
                                                    </#if>
                                                </#if>
						                <#else>
						             		  ￥ ${(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)?string("0.00")}
                                             <#assign paySum='${paySum?number+(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)?number}'>
					              	 	 </#if>
					             <#else> 
					                        <!--判断是否有会员折扣-->
                                            <#if cart.pointDiscount??>
                                                <!--会员折扣计算-->
                                                                                                                                                    ￥ ${(cart.pointDiscount*(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum))?string("0.00")}
                                                 <!--会员折扣记录-->                              <#assign paySum='${paySum?number+(cart.pointDiscount*(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum))?number}'>
											      <#assign pointDiscount="${pointDiscount?number+(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-(cart.pointDiscount*(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum))}">
                                            <#else>
    					                		 ￥ ${(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)?string("0.00")}
                                                <#assign paySum='${paySum?number+(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)?number}'>
                                            </#if>					                		
                                 </#if>
                                </td>
                            </tr>
                            </#if>
                            </#if>
                            </#list>
                        </table>
                        
                         <#assign payPrice='${payPrice?number+sumprice?number}'> 
                         <#assign payPrice='${payPrice?number-preprice?number}'>
                        <#if orderMarketings.marketing??>
									<#if (orderMarketings.marketing.priceOffMarketing?? )>
				 			  		 	 	<input type="hidden" class="codexType" value="1"/>
							   		 	 	<input type="hidden" class="offValue" value="${orderMarketings.marketing.priceOffMarketing.offValue}"/>
				              	 	 		<#assign payPrice='${payPrice?number-orderMarketings.marketing.priceOffMarketing.offValue?number}'> 
				              	 	 </#if>
				                    <#if orderMarketings.marketing.discountMarketing??> 
				                    		<input type="hidden" class="codexType" value="4"/>
							  		 	 	<input type="hidden" class="discountValue" value="${orderMarketings.marketing.discountMarketing.discountValue}"/>
							  		 	 	<#assign payPrice='${payPrice?number*orderMarketings.marketing.discountMarketing.discountValue?number}'> 
											
				              		 </#if>
				                 	<#if orderMarketings.marketing.fullbuyReduceMarketing??>
					                		<input type="hidden" class="codexType" value="5"/>
					                		<input type="hidden" class="fullPrice" value="${orderMarketings.marketing.fullbuyReduceMarketing.fullPrice}"/>
							  		 	 	<input type="hidden" class="reducePrice" value="${orderMarketings.marketing.fullbuyReduceMarketing.reducePrice}"/>
					                		 <#assign zk='${payPrice?number-orderMarketings.marketing.fullbuyReduceMarketing.fullPrice?number}'>
					                		 <#if ((zk?number)>0)>
					                			<#assign payPrice='${payPrice?number-orderMarketingzs.marketing.fullbuyReduceMarketing.reducePrice?number}'> 
					                		</#if>
					               </#if>
				            
				                    <#if orderMarketings.marketing.fullbuyDiscountMarketing??>
					               			<input type="hidden" class="codexType" value="8"/>
					                		<input type="hidden" class="fullPrice" value="${orderMarketings.marketing.fullbuyDiscountMarketing.fullPrice}"/>
							  		 	 	<input type="hidden" class="fullbuyDiscount" value="${orderMarketings.marketing.fullbuyDiscountMarketing.fullbuyDiscount}"/>
					                		<#assign zk='${payPrice?number-orderMarketings.marketing.fullbuyDiscountMarketing.fullPrice?number}'>
					                		<#if ((zk?number)>0)>
					                			<#assign payPrice='${payPrice?number*orderMarketings.marketing.fullbuyDiscountMarketing.fullbuyDiscount?number}'> 
					                		</#if>
					                </#if>
					                
					                <!--<#if orderMarketings.marketing.fullbuyNoDiscountMarketing??>
					                		<input type="hidden" class="codexType" value="13"/>
					                		<input type="hidden" class="packagesNo" value="${orderMarketings.marketing.fullbuyNoDiscountMarketing.packagesNo}">
					                		<input type="hidden" class="packagebuyDiscount" value="${orderMarketings.marketing.fullbuyNoDiscountMarketing.packagebuyDiscount}"/>
					                		<#assign zk='${orderMarketings.marketingNu-orderMarketings.marketing.fullbuyNoDiscountMarketing.packagesNo}'>
					                		<#if ((zk?number) >= 0)>
					                			<#assign payPrice='${payPrice?number*orderMarketings.marketing.fullbuyNoDiscountMarketing.packagebuyDiscount?number}'>
					                		</#if>
					                </#if>-->
                                </#if>
                                <#if orderMarketings.marketing??>
                                	 <#if  orderMarketings.marketing.giftList?? >
					              	   <#if orderMarketings.marketing.fullbuyPresentMarketing??>
										<#else>
										<a class="change_promotion" href="javascript:void(0);" onclick="$(this).next('.fullbuyPresentMarketing${orderMarketings.thirdId}').slideToggle();">查看赠品</a>
											<div style="display:none;" class=" fullbuyPresentMarketing${orderMarketings.thirdId}">
											      <#list orderMarketings.marketing.giftList as gift>
							                		<p class="light">[赠品]${gift.giftName }${gift.giftDesc }</p>
							                	  </#list>
							                 </div>
									 	</#if>
									 </#if>
									  <#if orderMarketings.marketing.couponList??  >
									  <#if orderMarketings.marketing.fullbuyCouponMarketing??>
									  <#else>
										 <a class="change_promotion" href="javascript:void(0);" onclick="$(this).next('.fullbuyPresentMarketing${orderMarketings.thirdId}').slideToggle();">查看优惠劵</a>
											<div style="display:none;" class=" fullbuyPresentMarketing${orderMarketings.thirdId}">
											     
											      <#list orderMarketings.marketing.couponList as coupon>
							                		<p class="light">[优惠券]${coupon.couponName }</p>
							                	  </#list>
							                 </div>
										    
									</#if>
				                	 </#if>
				            		 <#if orderMarketings.marketing.fullbuyPresentMarketing??>
										<a class="change_promotion" href="javascript:void(0);" onclick="$(this).next('.fullbuyPresentMarketing${orderMarketings.thirdId}').slideToggle();">查看赠品</a>
											<div style="display:none;" class=" fullbuyPresentMarketing${orderMarketings.thirdId}">
											      <#if orderMarketings.marketing.fullbuyPresentMarketing.fullPrice-orderpreprice &lt; payPrice  >
											      <#list orderMarketings.marketing.giftList as gift>
							                		<p class="light">[赠品]${gift.giftName }${gift.giftDesc }</p>
							                	  </#list>
							                	  </#if>
							                 </div>
											                
									</#if>
									 <#if orderMarketings.marketing.fullbuyCouponMarketing?? >
										 <a class="change_promotion" href="javascript:void(0);" onclick="$(this).next('.fullbuyPresentMarketing${orderMarketings.thirdId}').sldieToggle();">查看优惠劵</a>
											<div style="display:none;" class=" fullbuyPresentMarketing${orderMarketings.thirdId}">
									      <#assign zk='${payPrice?number-orderMarketings.marketing.fullbuyCouponMarketing.fullPrice?number}'>
											  <#if ((zk?number)>0)>    
											      <#list orderMarketings.marketing.couponList as coupon>
							                		<p class="light">[优惠券]${coupon.couponName }</p>
							                	  </#list>
							                 </div>
							                <#else>
							                	金额未满
							                </#if>
									</#if>
								</#if>
								<#assign orderpreprice=0>
                       			<#assign orderpreprice="${sumprice?number-payPrice?number}">
                       			<#assign orderpreprice="${orderpreprice?number-preprice?number}">
                       			<!--扣除会员折扣价格-->
                       			<#assign payPrice="${payPrice?number-pointDiscount?number}">
                       			<#assign pointDiscounts="${pointDiscounts?number+pointDiscount?number}">
                       			<div align="right">该子订单应付金额:￥${(paySum?number)?string("0.00")}元</div>
                       			<#assign sumP="${sumP?number+paySum?number}">
                       			<#assign prep="${prep?number+preprice?number}">
                       			<#assign orderSumP="${orderSumP?number+orderpreprice?number}">
                       			<input type="hidden" id="prep" value="${prep}"/>
                       			<#if orderMarketings.marketing??>
                       				<#if orderMarketings.marketing.fullbuyNoCouponMarketing?? >
                       					<#if map.countnum >= orderMarketings.marketing.fullbuyNoCouponMarketing.packagesNo>
                       						<#assign fullDiscount="${orderMarketings.marketing.fullbuyNoCouponMarketing.packagebuyDiscount}">
                       					</#if>
                       				</#if>
                       			</#if>
                            </#list>
                        <a href="../myshoppingcart.html">商品有误？返回购物车修改</a>
                    </div>
                    <div class="order_fill_item">
                	<div class="ware_show fill_item_show">
                        <h4>结算信息</h4>
                        <div class="ware_info">
                        	<div class="money_count mb10">
                                <#assign sumP='${(sumP?number+prep?number)?string("0.00")}'>
                                <#assign sumP='${(sumP?number+pointDiscounts?number)?string("0.00")}'>
                            	<p class="f16">
                            		<span>商品金额：${(sumP?number)?string("0.00")}元</span>+<span id="ep">运费：0.00元</span>- 优惠券:<span id="yh">0.00</span>元 -<span>订单优惠金额：${(orderSumP?number)?string("0.00")}元</span>-<span>活动优惠金额：<#if map.result??>${(prep?number+(map.result)?number)?string("0.00")}<#else>${(prep?number)?string("0.00")}</#if>元</span>-<span>会员折扣：${(pointDiscounts?number)?string("0.00")}元  - </span>积分兑换金额:<span id="jf">0.00</span>元
                            	</p>
                            </div>
                           
                               <input type="hidden" id="yfprice" name="yfprice" value=""/>
                            <div class="coupon">
                            	<a class="open_coupon f13 fb mb10" href="javascript:void(0)">使用优惠券抵消部分金额</a>
                                <div class="use_coupon">
                                	<p class="tips">提示：优惠券每次只能使用一张，不可混合使用</p>
                                    <h5 class="f13 fb">请选择要使用的优惠券：</h5>
                                    <ul class="coupons_list">
                                    <input type="hidden" value="" id="codeNo" name="codeNo"/>
                                    <#if map.couponlist??&&(map.couponlist?size>0)>
                                    <li><input type="radio" name="couponNo" value="" onclick="couponChange(this,0);" /><label>不使用优惠劵</label></li>
	                                    <#list map.couponlist as coupon> 
	                                    		<#if coupon.couponRulesType=='1'>
	                                    				<li><input type="radio" name="couponNo" value="${coupon.codeNo}" onclick="couponChange(this,${coupon.couponStraightDown.downPrice});" /><label>${coupon.couponName}</label></li>
	                                    		</#if>
	                                   			 <#if coupon.couponRulesType=='2'>  
	                                   			      <#if (payPrice?number>=coupon.couponFullReduction.fullPrice?number) >
	                                   			      		<li><input type="radio" name="couponNo" value="${coupon.codeNo}" onclick="couponChange(this,${coupon.couponFullReduction.reductionPrice});" /><label>${coupon.couponName}</label></li>
	                                   			      </#if>
	                                    		</#if>
	                                    </#list>
	                                <#else>  
	                                	<li>您账户中没有可使用的优惠券</li>
                                    </#if>	
                                    </ul>
                                    <p>有实体券？<!--<a class="use_ent" href="javascript:void(0)">点此输入实体券密码</a>--><!--<a href="#">了解优惠券使用规则</a>--></p>
                                    <div class="ent_coupon"><label>请输入您手中实体优惠券的密码：</label><input type="text" size="20" /><input type="button" class="add_coupon" value="添加" /></div>
                                    <div class="coupon_count tr">
                                    	<p>您一共使用了<font color="red" class="us">0</font>张优惠券，抵消金额<font color="red" class="up">0.00</font>元</p>
                                    </div>
                                    <span class="close_btn" href="javascript:void(0)">[关闭]</span>
                                </div>
                            </div>  
                            
                            
                            
                            <div class="coupon">
                            	<a class="open_coupon_point f13 fb mb10"  href="javascript:void(0)">使用会员积分抵消部分金额</a>
                                  <div class="use_coupon_point">
                                  <p class="tips">提示：使用的积分必须为10的倍数！</p>
                                	<#if map.customerPoint?? && map.pointSet?? >
                                		<p>本次使用
                                			<input id="amount" style="IME-MODE: disabled; WIDTH: 60px; HEIGHT: 15px"
                                			 onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')"
                                			 maxlength="10" size="14" name="amount" type="text" />
                                			积分用于兑换 <input type="button" id="shiyong" 
                                				style="width: 60px; height: 25px; background-color:#B87070"
                                			onclick="jifen()"  value="使用"> 
                                			
                                			<span id="tishi" style="color:red"></span>
                                		</p>
                                		<input type="hidden" id="customerPoint" value="${map.customerPoint}"/>
                                		<input type="hidden" id="pointSet" name="pointSet" value="${map.pointSet}"/>
                                		<input type="hidden" id="zongjiage" name="zongjiage" value=""/>
                                		<input type="hidden" id="duihuanjifen" name="duihuanjifen" value=""/>
                                        <input type="hidden"  id="jifen1"  value="${map.customerPoint}"/>
                                		<p>
                                			您目前有<span id="muqianjifen" style="font-size: 14px; text-decoration:underline;font-weight:bold;">${map.customerPoint}</span>积分，本次可使用积分<span id="muqianjifen1" style="font-size: 14px; text-decoration:underline;font-weight:bold;">${map.customerPoint}</span>,
                                			每10积分兑换人民币￥${map.pointSet}元。
                                		</p>
                                	<#else>
                                		<span>您目前没有可供消费的积分！</span>
                                	</#if>
                                	<br/>
                                    <span class="close_btn" href="javascript:void(0)">[关闭]</span>
                                </div>
                            </div>
                               <#assign odprice=0>
                                <#assign odprice="${sumP?number-prep?number}">
	                            <#assign odprice="${odprice?number-orderSumP?number}">
	                            <#assign odprice="${odprice?number-pointDiscounts?number}">
	                            <#if map.result??>
	                            	<#assign odprice="${odprice?number-(map.result)?number}">
	                            </#if>
                                <#--<#assign payP='${(sumP?number-pointDiscounts?number)?string("0.00")}'>-->
                                <input type="hidden" id="sumPrice" value=""/><!--记录未使用优惠券和积分兑换之前的总价格-->
                               <input type="hidden" id="payPrice" value="${odprice}"/>
                            <div class="money_total f16 fb"><span id="odPrice">应付总额：<font color="red">￥</font><font color="red" class="jine" id="lastpay">${odprice}</font>元</span></div>
                        </div>
                    </div>
                </div>
                
		            <div class="ware_show fill_item_show" style="margin-top:20px;">
					      <div class="form-group form-group-sm">
					        <label for="message_left" class="col-xs-3">订单留言：</label>
					          <input type="text" style="width:500px;" name="customerRemark" class="text form-control" id="message_left" placeholder="请简短的填写您的特殊要求">
					      </div>
		    				</div><!-- /message -->
		                <input type="hidden" id="yhprice" value="0"/>
		                <div class="order_submit m30"><input type="button" onclick="submitForm();" class="order_sub_btn" value="提交订单" /></div>
		            </div>
                </div>
            <div class="order_fill_lt"></div>
            <div class="order_fill_rt"></div>
            <div class="order_fill_lb"></div>
            <div class="order_fill_rb"></div>
        </div><!-- /order_fill -->
    </div><!-- /container -->
    </div>	
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
            	<em id="con_00">修改成功！</em>
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
        	<div class="dia_intro no_tc pt30" style="text-align: center;">
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
	<input type="hidden" class="backCheckInfos" role="paysets" value="<#if map.paysets??>${map.paysets}</#if>">
	<input type="hidden" class="backCheckInfos" role="express" value="<#if map.express??>${map.express}</#if>">
	<input type="hidden" class="backCheckInfos" role="sendTime" value="<#if map.sendTime??>${map.sendTime}</#if>">
	<input type="hidden" class="backCheckInfos" role="timeSure" value="<#if map.timeSure??>${map.timeSure}</#if>">
	<input type="hidden" class="backCheckInfos" role="invoiceType" value="<#if map.invoiceType??>${map.invoiceType}</#if>">
	<input type="hidden" class="backCheckInfos" role="invoiceTit" invoiceTitle="<#if map.invoiceTitle??>${map.invoiceTitle}</#if>" value="<#if map.invoiceTit??>${map.invoiceTit}</#if>">
	<input type="hidden" class="backCheckInfos" role="invoiceContent" value="<#if map.invoiceContent??>${map.invoiceContent}</#if>">
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/customaddress.js"></script>
<script type="text/javascript" src="${basePath}/js/order/loadAddress.js"></script>
<script type="text/javascript" src="${basePath}/js/order/order_common.js"></script>
<script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
<script type="text/javascript" src="${basePath}/index_two/js/index.js"></script>
<script type="text/javascript">
    $(function(){
    loadProvice();
    });
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
	function couponChange(obj,num){
		var amount = $('#amount').val();                //需要兑换的积分
		var pointSet = $('#pointSet').val();          //积分兑换规则
		var zhekou = amount * pointSet;                //积分兑换的折扣价格
		$("#yh").html(num);
		$("#yhprice").val(num);
		var payPrice = $("#payPrice").val();
		var sum = Subtr(payPrice,num);
		var lastpay = accAdd(sum,$("#yfprice").val());
		//判断是否有积分兑换 如果有总价格减去积分兑换的金额
		if(zhekou!=0){
			lastpay = lastpay - zhekou;
		}
		$("#lastpay").html(lastpay);
		$(".us").html('1');
		$(".up").html(num);
		$("#codeNo").val($(obj).val());
		//订单总金额
		var sum = $('#lastpay').html(); 
		//金额小于1  设置总订单的价格为1分钱
		if(sum<1){
			$('#lastpay').html('0.01')
		}
	}
</script>
<script>
var issubmit = 0;
    function submitForm(){
        var addressName = $(".addressName").html();
         var regexp=new RegExp("[''\\[\\]<>?\\\\!]");
         if( $("#message_left").val().length > 40){
            $("#con_00").html("客户留言长度过长！！");
            dia(2);
            return null;
         }
         if (regexp.test( $("#message_left").val())) {
            $("#con_00").html("客户留言存在特殊字符！！");
            dia(2);
            return null;
         }
        if(addressName==''){
            $("#con_00").html("请选择收货人信息！！");
            dia(2);
        }else{
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
            $(".market_id").attr("value",arrIds);
            $(".third_id").attr("value",arr);
            issubmit=1;
            
            var amount = $('#amount').val();                //需要兑换的积分
            var sum = $('#lastpay').html();                 //订单总价格
            $('#zongjiage').val(sum);
            var pointSet = $('#pointSet').val();            //积分兑换规则
            //判断积分是否超额
            if(parseInt(amount)>parseInt($("#jifen1").val())){
                $("#con_01").html("您的积分不够兑换！");
                dia(1);
                return;
            }
            $('#duihuanjifen').val(amount);
            $("#sub_order").submit();   
        }
    }
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