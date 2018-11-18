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
                    	<#if order.giftOrderCode??>
                    		${order.giftOrderCode}
                    	</#if>
                    	</span>&nbsp;&nbsp;&nbsp;&nbsp;
                        <span>状态：<b class="orange">
                        <#if order.giftOrderStatus=="0">
							待发货
						<#elseif order.giftOrderStatus=="1">
							已发货
						<#elseif order.giftOrderStatus=="2">
							已完成
						</#if>
						
                        </b></span>
                    </div>
                </div>
            </div><!-- /order_state -->
            <div class="order_track mt20">
            	<div class="tagMenu">
                   <ul class="menu">
                       <li>物流跟踪</li>
                    </ul>
                </div>
                   
                <div class="content order_details">
                    <div class="layout p_list">
                    <#if order.giftOrderStatus??>
                        <#if (order.giftOrderStatus=="1"|order.giftOrderStatus=="2")>
                        <table border="1" cellpadding="0" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <td width="70%"><strong>物流信息</strong></td>
                                </tr>
                            </thead>
                            <tbody id="tbody_track">
                                    <tr>
                                        <td style="text-align:left;">
                           					  物流公司：${order.temp4!''}&nbsp;&nbsp;&nbsp;&nbsp;物流单号：${order.temp3!''} &nbsp;&nbsp;&nbsp;&nbsp;
                                            <a href="http://www.kuaidi100.com/" target="_Blank" style="color:#666666;"  >快递100官网查询</a>
                                            <iframe frameborder="no" border="0" width="1172" height="340" style="border:0px;" src="${relations!''}"></iframe>
                                        </td>
                                    </tr>
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
                                        ${(order.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}
                                    </td>
                                    <td> 您提交了订单，请等待系统确认</td>
                                </tr>
                            </tbody>
                        </table>
                        </#if>
                    </#if> 
                        
                  <div class="extra">
                        <span id="jdshfs"></span>
                        <span></span>
                        <span></span>
                   </div> 
                </div>
	                 
                   
                </div>
            </div><!-- /order_track -->
            <div class="order_details mt20">
            	<div class="title mb10"><strong class="f14">订单信息</strong></div>
                <div class="body">
                	<dl class="fore">
	                	 <dt>收货人信息</dt>
				          <dd>
				              <ul>
                                <li>&nbsp;收货人：
							 		<#if order.shoppingPerson??>
										${order.shoppingPerson}
									</#if>
                                </li>
                                <li>地&nbsp;&nbsp;址：
                                    <#if order.provinceName??>${order.provinceName?default('')}</#if><#if order.cityName??>${order.cityName?default('')}</#if><#if order.districtName??>${order.districtName?default('')}</#if><#if order.shoppingAddress??>${order.shoppingAddress?default('')}</#if>
                                </li>
                                <li> 固定电话：
                                      <#if order.shoppingPhone??>
										${order.shoppingPhone?default('')}
									</#if>
                                </li>
                                <li>手机号码：
                                      <#if order.shoppingMobile??>
										${order.shoppingMobile?default('')}
									</#if>
                                </li>
                                <li>配送方式：

                                </li>
                            </ul>
                        </dd>
                    </dl>
                    
                 <dt>
                    <span class="i-mt">
                       	 商品清单
                    </span>
                </dt>
                <dd class="p_list">
                    <table cellpadding="0" cellspacing="0" width="100%">
                        <tbody>
                            <tr>
                                <th width="12%">
                                   	 商品图片1
                                </th>
                                <th width="42%">
                                   	商品名称
                                </th>
                                <th width="10%">
                                   	     商品积分
                                </th>
                                <th width="7%">
                                   	兑换数量
                                </th>
                            </tr>
                            <tr>
                                <td>
                                    <div class="img-list">
                                        <a class="img-box"
                                         <#if order.giftName??>
						   					alt="${order.giftName}" 
						 	         	 </#if>
                                         target="_blank" <#--href="${basePath}/giftdetail/${order.temp1}.html"-->>
                                             <img title="${(order.giftName)!''}" alt="${(order.giftName)!''}" 
                                               src="<#if order.giftPicList?? &&  order.giftPicList?size &gt; 0 >${(order.giftPicList[0].picBig)!''}</#if>"  width="50" height="50" />
						 			 	 	<input type="hidden" id="goodsImg${order.temp1}"  value="${(order.goodsImg)!''}">
                                        </a>
                                    </div>
                                </td>
                                <td>
                                    <div class="al fl">
                                        <a class="flk13" target="_blank" <#--href="${basePath}/giftdetail/${order.temp1}.html"--> >
                                            <#if  order.giftName??>
							   					${order.giftName}
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
                                        <#if order.giftIntegral??>
							   				${order.giftIntegral}
							 			</#if> 
                                        
                                    </span>
                                </td>
                                <td>
                                   ${order.orderNum}
                                </td>
                            </tr>
                           
                        </tbody>
                    </table>
                </dd>
            </dl>
        </div>
        
        <div class="total">
                   
                    <span style="color:#EDEDED;">
                    </span>
                    <div class="extra">
                                    应扣积分：
                        <span class="red">
                            <b>
                               ${(order.orderIntegral)!'0'}
                            </b>
                        </span>
                    </div>
                </div>
        
      </div><!-- /order_details -->
     </div><!-- END OF member_box -->
        <input type="hidden" value="${order.giftOrderId}" class="order_id">
		<input type="hidden" value="${order.giftOrderStatus}" class="order_status">
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
