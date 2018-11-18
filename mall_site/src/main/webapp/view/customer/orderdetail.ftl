<#include "../include/common.ftl">
<@htmlHead title="${topmap.seo.mete!''}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>订单详情-${topmap.systembase.bsetName}</title>
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
            <a href="${basePath}/customer/myorder.html"">我的订单</a><span>&gt;</span>
            <span>订单详情</span>
        </div>
        <div class="member_box mb20">
        	<#if order??>
            <div class="order_state p10">
            	<div class="state_top">
                	<div class="btns fr">
                	<#--
                	<#if order.orderStatus!="4">
                    	<a class="red_btn" href="#">订单打印</a>
                    	<#if order.orderStatus=="3">
                        <a class="red_btn" href="#">评价</a>
                        <#elseif order.orderStatus!="3">
                        <a class="red_btn" href="#">修改</a>
                        <a class="red_btn" href="#">取消</a>
                        </#if>
                     </#if>-->
                    </div>
                    <div class="f14 fb">
                    	<span>订单号：
                    	<#if order.orderNo??>
                    		${order.orderNo}
                    	</#if>
                    	</span>&nbsp;&nbsp;&nbsp;&nbsp;
                        <span>状态：<b class="orange">
                        <#if order.orderStatus=="0">
                            <#if order.orderLinePay??>
                                <#if order.orderLinePay=='0'>
                                   待发货
                                <#else>
                                    待付款
                                </#if>
                            </#if>
						<#elseif (order.orderStatus=="1" | order.orderStatus=="5" | order.orderStatus=="6") >
							未发货
						<#elseif order.orderStatus=="2">
							已发货
						<#elseif order.orderStatus=="3">
							已完成
						<#elseif order.orderStatus=="4">
							已取消
						<#elseif order.orderStatus=="7">
					    	退单审核中
					    <#elseif order.orderStatus=="8">
					    	同意退货
					    <#elseif order.orderStatus=="9">
					    	拒绝退货	
					    <#elseif order.orderStatus=="10">
					    	待商家收货
					    <#elseif order.orderStatus=="11">
					                    退单结束
					    <#elseif order.orderStatus=="12">
					                        退款审核中
					    <#elseif order.orderStatus=="13">
					                        拒绝退款
                        <#elseif order.orderStatus=="14">
                            已提交退货审核
                        <#elseif order.orderStatus=="16">
                            商家收货失败
                        <#elseif order.orderStatus=="17">
                            已退款
                        <#elseif order.orderStatus=="18">
                            退款成功
                        <#elseif order.orderStatus=="15">
                            退款审核中
						</#if>
						 <#if order.orderStatus=="0" && order.orderLinePay='1'>
		                     <a target="_black" href="${basePath}/gopayorder-${order.orderId}.html">去付款</a>
		                 </#if>
                        </b></span>
                    </div>
                </div>
                 <#if order.orderStatus=="0">
                 	<div >
                 		<#--<#if unpay??>-->
			            	<#--<#list "${unpay.content}"?split("|") as con>-->
							<#--<p>${con}</p>-->
							<#--</#list>-->
			            <#--</#if>-->
                		<#--<p>尊敬的客户，我们还未收到该订单的款项，请您尽快付款（在线支付帮助），如果您已经付款，请务必填写付款确认。<br/>
							该订单会为您保留24小时（从下单之日算起），24小时之后如果还未付款，系统将自动取消该订单。<a href="#">发表评价</a>
						</p>-->
                	</div>
                 </#if>
                 <#if order.orderStatus=="3">
                <div class="state_bottom">
                	<#if pay??>
			            	<#list "${pay.content}"?split("|") as con>
							<p>${con}</p>
							</#list>
					</#if>
                	<#--<p>订单已经完成，感谢您在商城购物，欢迎您对本次交易及所购商品进行评价。<a href="#">发表评价</a></p>-->
                </div>
                </#if>
            </div><!-- /order_state -->
            <div class="order_track mt20">
            	<div class="tagMenu">
                   <ul class="menu">
                       <li class="current">物流跟踪</li>
                       <li>付款信息</li>
                    </ul>
                </div>
                   
                <div class="content order_details">
                    <div class="layout p_list">
                    <#if order.orderStatus??>
                        <#if (order.orderStatus=="2"|order.orderStatus=="3")>
                        <table border="1" cellpadding="0" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <td width="30%"><strong>包裹信息</strong></td>
                                    <td width="70%"><strong>物流信息</strong></td>
                                </tr>
                            </thead>
                            <tbody id="tbody_track">
                                <#if relations??>
                                    <#list relations as relation>
                                    <tr>
                                        <td style="text-align:left;">
                                            <#if relation.containers??>
                                                <#list relation.containers as container>
                                                    <#if container.containerStatus=='0'>
                                                        <a class="img-box" title="${container.goodsProductDetailViewVo.goodsInfoName!''}"  target="_blank" href="${basePath}/item/${container.goodsProductDetailViewVo.goodsInfoId}.html" style="width:260px;white-space:nowrap;overflow:hidden;height:50px;display:inline-block;text-overflow:ellipsis;" ><img height="50px" style="width:50px;max-width:70px;" src="${container.goodsProductDetailViewVo.goodsInfoImgId}"/>
                                                        
                                                        ${container.goodsProductDetailViewVo.goodsInfoName!''}</a>
                                                    <#else>
                                                        ${container.gift.giftName!''}
                                                    </#if>
                                                    <br/>
                                                </#list>
                                            </#if>
                                        </td>
                                        <td style="text-align:left;">
                                                                                                                                                        物流公司：${relation.expressName!''}&nbsp;&nbsp;&nbsp;&nbsp;物流单号：${relation.expressNo!''} &nbsp;&nbsp;&nbsp;&nbsp;
                                            <a href="http://www.kuaidi100.com/" target="_Blank" style="color:#666666;"  >快递100官网查询</a>
                                            <iframe frameborder="no" border="0" width="534" height="340" style="border:0px;" src="${relation.expressInfoUrl!''}"></iframe>
                                        </td>
                                    </tr>
                                    </#list>
                                </#if>
                            </tbody>
                         </table>
                         <#else>
                         <table cellpadding="0" cellspacing="0" width="100%">
                            <tbody id="tbody_track">
                                <tr>
                                   <th width="15%">
                                        <strong>处理时间</strong>
                                    </th>
                                    <th width="50%">
                                        <strong>处理信息</strong>
                                    </th>
                                </tr>
                            </tbody>
                            <tbody class="eTbody">
                                <tr>
                                    <td>
                                        ${(order.addTime?string('yyyy-MM-dd HH:mm:ss'))!''}
                                    </td>
                                    <td> 您提交了订单，请等待系统确认</td>
                                </tr>
                            </tbody>
                        </table>
                        </#if>
                    </#if> 
                         <#--<table cellpadding="0" cellspacing="0" width="100%">
                            
                            <tbody id="tbody_track">
                                <tr>
                                   <th width="15%">
                                        <strong>
                                            处理时间
                                        </strong>
                                    </th>
                                    <th width="50%">
                                        <strong>
                                            处理信息
                                        </strong>
                                    </th>
                                    <th width="35%">
                                        <strong>
                                      		<#if order.orderStatus??>
								   		<#if (order.orderStatus=="2"|order.orderStatus=="3")>
											物流信息
								   		</#if>
								   	</#if> 
                                        </strong>
                                    </th>
                                  
                                </tr>
                            </tbody>
                            <tbody class="eTbody">
                                <tr>
                                    <td>
                                    	${(order.addTime?string('yyyy-MM-dd HH:mm:ss'))!''}
                                    </td>
                                    <td>
                                        您提交了订单，请等待系统确认
                                    </td>
                                    <td>
                                 			<#if order.orderStatus??>
								   		<#if (order.orderStatus=="2"|order.orderStatus=="3")>
										      <a href="http://www.kuaidi100.com/" target="_Blank" style="color:#666666;"  >
										        <#if order.expressno??>
										       		<#list order.expressno as express>
										       			 <p>物流单号:${(express.expressNo)!'   无单号'}</p>
										       		</#list>
										       </#if>
										      </a>
								   		</#if>
								   	</#if> 
                                    </td>
                                </tr>
                           
                            </tbody>
                        </table>-->
                      <div class="extra">
                            <span id="jdshfs">
                                
                            </span>
                            <span>
                             
                            </span>
                            <span>
                                
                            </span>
                        </div> 
                        
                    </div>
	                 
                    <div class="layout">
                        <table cellpadding="0" cellspacing="0" width="100%">
                            <tbody>
                                <tr>
                                    <td  style="font-family: arial;">
                                        商品金额：￥<#if order.oldPrice??> ${(order.oldPrice)?string('0.00')} <#else>0.00</#if>
                                    </td>
                                    <td  style="font-family: arial;">
                                        运费金额：￥<#if order.shippingFee??> ${(order.shippingFee)?string('0.00')} <#else>0.00</#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td  style="font-family: arial;">
                                        优惠金额：￥<#if order.prePrice??> ${(order.prePrice)?string('0.00')}<#else>0.00</#if>
                                    </td>
                                    <td  style="font-family: arial;">
                                        实际运费：￥<#if order.shippingFee??> ${(order.shippingFee)?string('0.00')} <#else>0.00</#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td  style="font-family: arial;">
                                        应支付金额：￥<#if order.moneyPaid??> ${(order.moneyPaid)?string('0.00')}<#else>0.00</#if>
                                    </td>
                                    <#--
                                    <td  style="font-family: arial;">
                                        交易余额：￥ 0.00
                                    </td>-->
                                </tr>
                                <#if order.payTime??>
                                	<tr>
	                                    <td> 
	                                        付款时间：${(order.payTime?string('yyyy-MM-dd HH:mm:ss'))!'未付款'}
	                                    </td>
	                                </tr>
                                </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div><!-- /order_track -->
            <div class="order_details mt20">
            	<div class="title mb10"><strong class="f14">订单信息</strong></div>
                <div class="body">
                	<dl class="fore">
	                	<#if order.orderCargoStatus??>
	                		<#if order.orderCargoStatus=='4'>
					    	<dt>
					    		配送信息
					    	</dt>
					    	<dd>
                            	<ul>
	                                <li>
	                                    	配送方式：上门自提
	                                </li>
                               </ul>
                            </dd>
						<#else>
				                        <dt>
				                            收货人信息
				                        </dt>
				                        <dd>
				                            <ul>
				                                <li>
				                                    &nbsp;收货人：
											 		<#if order.shippingPerson??>
														${order.shippingPerson}
													</#if>
				                                </li>
				                                <li>
				                                    地&nbsp;&nbsp;址：
				                                    <#if order.shippingProvince??>${order.shippingProvince?default('')}</#if><#if order.shippingCity??>${order.shippingCity?default('')}</#if><#if order.shippingCounty??>${order.shippingCounty?default('')}</#if><#if order.shippingAddress??>${order.shippingAddress?default('')}</#if>
				                                </li>
                                                <li>
                                                    邮&nbsp;&nbsp;编：
                                                    <#if order.shippingPostcode??>
                                                        ${order.shippingPostcode? default('')}
                                                    </#if>
                                                </li>
				                                <li>
				                                    固定电话：
				                                      <#if order.shippingPhone??>
														${order.shippingPhone? default('')}
													</#if>
				                                </li>
				                                <li>
				                                    手机号码：
				                                      <#if order.shippingMobile??>
														${order.shippingMobile?default('')}
													</#if>
				                                </li>
                                                <li>
                                                    配送方式：
                                                    <#if order.expressName??>
                                                    ${order.expressName?default('')}
                                                    <#elseif order.orderExpressType=='1'>
                                                    上门自提
                                                    </#if>

                                                </li>
				                                <#--
				                                <li>
				                                    电子邮件：
													 <#if order.address??>
				                                    	${order.address.email?default('')}
				                                     </#if>
				                                </li>-->
				                            </ul>
				                        </dd>
				                    
				          </#if>
				          </#if>
                    </dl>
                    
                    <dl>
                        <dt>
                            支付信息
                        </dt>
                        <dd>
                            <ul>
                                <li>
                                    支付方式：
                                <#if order.orderLinePay??>
                                    <#if order.orderLinePay=='0'>
										货到付款	                                  	
                                    <#else>
                                    	在线支付
                                    </#if>
                               </#if> 
                               
                                </li>
                                <#--<li>
                                    运&nbsp;&nbsp;&nbsp;&nbsp;费：￥
                                    <#if order.express??>
                                    	 ${order.express.expPrice?string('0.00')?default('')}
                                    </#if>
                                </li>
                                <li>
                                    送货日期：2014-03-06
                                </li>-->
                            </ul>
                        </dd>
                    </dl>
                    <#--积分使用情况-->
              <#if order.orderIntegral??>
                    <dl>
                        <dt>
                            积分使用信息
                        </dt>
                        <dd>
                            <ul>
                                <li>
                                    积分使用：
                                    ${order.orderIntegral}
                                </li>

                            </ul>
                        </dd>
                    </dl>
                </#if>
                    <#if order.couponNo??&&order.couponNo!=''>
                        <dl>
                            <dt>
                                优惠券使用信息
                            </dt>
                            <dd>
                                <ul>
                                    <li>
                                        优惠券券码：
                                    ${order.couponNo}
                                    </li>
                                    <#--2016-02-02 wuyanbo 修改-->
                                    <#--<li>
                                        优惠券名称：
                                    ${order.couponName}
                                    </li>-->

                                </ul>
                            </dd>
                        </dl>
                    </#if>
                    <dl>
                        <dt>
                            发票信息
                        </dt>
                        <dd>
                            <ul>
                                <#if order.invoiceType??>
                                <li>
                                    发票类型：
                                    	<#if order.invoiceType=='0'>
                                    		无需发票
                                    	<#elseif order.invoiceType=='1'>
                                    		普通
                                    	<#elseif order.invoiceType=='2'>
                                    		增值
                                    	</#if>

                                </li>
                <#if order.invoiceType != '0'>
                                <li>
                                    发票抬头：
                                    <#if order.invoiceTitle??>
                                    	${order.invoiceTitle?default('')}
                                    <#else>
                                    		无
                                    </#if>
                                </li>
                                <li>
                                    发票内容：
                                    <#if order.invoiceContent??>
                                    	${order.invoiceContent?default('')}
                                    <#else>
                                    		无
                                    </#if>
                                </li>
                </#if>
                                </#if>
                            </ul>
                        </dd>
                    </dl>
                    <#if order.backRemark??>
                    <dl>
                        <dt>订单留言</dt>
                        <dd>${order.backRemark}</dd>
                    </dl>
                    </#if>
                    <dl>
                        <dt>
                            <span class="i-mt">
                                商品清单
                            </span>
                        </dt>
                        <dd class="p_list">
                            <table cellpadding="0" cellspacing="0" width="100%">
                                <tbody>
                                    <tr>
                                        <th width="10%">
                                            商品编号
                                        </th>
                                        <th width="12%">
                                            商品图片
                                        </th>
                                        <th width="12%">
                                            商品规格
                                        </th>
                                        <th width="42%">
                                            商品名称
                                        </th>
                                        <th width="10%">
                                            <#if vip??&&vip=="1">商品会员价<#else>商品零售价</#if>
                                        </th>
                                        <th width="7%">
                                            商品数量
                                        </th>
                                        <th width="11%">
                                            操作
                                        </th>
                                    </tr>
                                    <#list order.goods as good>
                                    	<tr>
                                        <td>
                                           	<#if good.goodsNo??>
												${good.goodsNo}
										 	</#if>
                                        </td>
                                        <td>
                                            <div class="img-list">
                                                <a class="img-box"
                                                 <#if good.goodsName??>
								   					alt="${good.goodsName}"
								 	         	 </#if>
                                                 target="_blank" href="${basePath}/item/${good.goodsId}.html">
                                                    <img width="50" height="50" src="${(good.goodsImg)!''}"
                                                    <#if good.goodsName??>
								   						title="${good.goodsName}"
								 			 	 	</#if> />
								 			 	 	<input type="hidden" id="goodsImg${good.goodsId}"  value="${(good.goodsImg)!''}">
                                                </a>
                                            </div>
                                        </td>
                                        <td>
                                            <#if good.specVo??>

                                                <#list good.specVo as specVo>
                                                    <#if specVo.spec.specName??&&specVo.spec.specName == '.'>
                                                    <#else>
                                                        <#if specVo.specValueRemark??>${specVo.spec.specName}:${specVo.specValueRemark}<br/></#if>
                                                    </#if>
                                                </#list>
                                            </#if>
                                        </td>
                                        <td>
                                            <div class="al fl">
                                                <a class="flk13" target="_blank" href="${basePath}/item/${good.goodsId}.html" >
                                                    <#if good.goodsName??>
									   					${good.goodsName}
									 			 	 </#if>
                                                </a>
                                            </div>
                                            <div class="clr">
                                            </div>
                                            <div id="coupon_697667" class="fl">
                                            </div>
                                        </td>
                                        <td>
                                            <span class="ftx04" style="font-family: '微软雅黑'">
                                                ￥
                                                <#if vip??&&vip=="1">
                                                    <#if good.goodsVipPrice??>
                                                        ${good.goodsVipPrice?string('0.00')}
                                                    </#if>
                                                <#else>
                                                    <#if good.goodsPrice??>
                                                        ${good.goodsPrice?string('0.00')}
                                                    </#if>
                                                </#if>

                                            </span>
                                        </td>
                                        <td>
                                            <#if good.goodsNum??>
									   			${good.goodsNum}
									 		</#if>
                                        </td>
                                        <td>
                                            <span id="iwo697667" class="flk13">
                                            	<#if ((order.orderStatus)!'') == '3' >
                                            		<#if ((good.evaluateFlag)!'0') == "0">
		                                                 <a target="_blank" href="${basePath}/comment-${order.orderId}.html" >
		                                                    评价
		                                                </a>
		                                            <#elseif  ((good.evaluateFlag)!'0') == "1"><#--   ${basePath}/viewcomment-${good.goodsId}-${order.orderId}.html -->
		                                            	<a target="_blank" href="${basePath}/item/${good.goodsId}.html#comment" >
		                                                    查看评价
		                                                </a>
	                                                </#if>
												</#if>
                                                <#--
                                                |
                                                <a clstag="click|keycount|orderinfo|product_show" href="http://club.jd.com/orderbbs/697667-1.html?orderId=1160562364"
                                                target="_blank">
                                                    晒单
                                                </a>
                                                <br>
                                                <a href="http://myjd.jd.com/repair/ordersearchlist.action?searchString=1160562364"
                                                target="_blank" clstag="click|keycount|orderinfo|product_repair">
                                                    申请返修/退换货
                                                </a>-->
                                            </span>
                                        </td>
                                    </tr>
                                    </#list>
                                </tbody>
                            </table>
                        </dd>
                    </dl>
                </div>
                <div class="total">
                    <ul>
                        <li>
                            <span>
                                总商品金额：
                            </span>
                            ￥<#if order.oldPrice??> ${(order.oldPrice)?string('0.00')} </#if>
                        </li>
                        <li>
                            <span>
                                - 返现：
                            </span>
                            ￥<#if order.prePrice??> ${order.prePrice?string('0.00')} </#if>
                        </li>
                        <li>
                            <span>
                                + 运费：
                            </span>
                            ￥${order.shippingFee?string('0.00')}
                        </li>
                    </ul>
                    <span style="color:#EDEDED;">
                    </span>
                    <div class="extra">
                        应付总额：
                        <span class="red">
                            <b>
                                ￥<#if order.moneyPaid??> ${order.moneyPaid?string('0.00')} </#if>
                            </b>
                        </span>
                    </div>
                </div>
            </div><!-- /order_details -->
        </div><!-- END OF member_box -->
        <input type="hidden" value="${order.orderId}" class="order_id">
		<input type="hidden" value="${order.orderStatus}" class="order_status">
        <#else>
        	<div class="reg_success">
		    	<div class="notice2">
		        	<img alt="" src="${basePath}/images/failed.png">订单不存在！<span>
		        </div>
		        <div class="notice3">
		            <strong><span id="time">5</span>秒自动进入<a href="${basePath}/index.html">“首页”</a></strong></span> 
		        </div>
		    </div>
       	</#if>
    </div>
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
	$(".pro_sort").addClass("pro_sort_close");
	
});
/**
if($(".order_status").val()=='2'||$(".order_status").val()=='3'){
	oId= $(".order_id").val();
	$.post("${basePath}/queryorderexpress.htm", { orderId:oId },
	   function(data){
	   for(var j=0;j<data.length;j++){
		   var goodshtml='';
		   goodshtml+='<div class="logistics_info">';
		   goodshtml+='<div class="lgt_tit"><em>物流跟踪</em><span>物流公司：'+data[j].expressName+'</span><span>运单号：'+data[j].containerRelations.expressNo+'</span></div>';
			goodshtml+="</tbody></table></div></div></div>";
	  		$(".orderC").append(goodshtml);
			
	   }
	  
	 });
}*/
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
