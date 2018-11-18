<#include "../include/common.ftl">
<@htmlHead title='${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/index_two/css/header.css" />
</@htmlHead>
<@htmlBody>
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
				<#include "../index/newtop7.ftl">
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
	</div>

        <input type="hidden" id="currentProvince" value="${chProvince!''}" />
        <input type="hidden" id="basePath" value="${basePath}" />
	<div style="font-family: arial;">
    <div class="container">
	<div class = "logo fl head2">
            		<!--
        <a href="${basePath}/index.html"><img src="${basePath}/index_two/images/logo.png" alt="" /></a>
		-->
        <a href="${topmap.systembase.bsetAddress}"><img src="${topmap.systembase.bsetLogo}" alt=""  style="width:165px;height:40px;"/></a>
    </div>
        <div class="head_s mb20">
            <div class="fr w700 pt10">
                <div class="flow_progress3">
                    <ul>
                           
                    </ul>
                </div> 
            </div>
            <div class="cb"></div>
        </div><!-- /head_s -->
        <form action="${basePath}/order/paygrouponorder.html" class="payOrder" method="post" target="_blank">
        <div class="order_success">
        	<div class="success_notice">
            	<span class="success_icon"></span>
                <h3 class="f20 mb10">订单提交成功，请您尽快付款！</h3>
                <ul>
                	<input type="hidden" value="${map.order.orderCode}" name="orderCode"/>
                		<input type="hidden" value="${map.order.orderId}" name="orderId"/>
                	<li>订单号：${map.order.orderCode}</li>
                    <li>应付金额：<b><font color="red"><span class='ct_price'><b>¥</b></span>${map.order.orderPrice?string("0.00")}</font></b></li>
                </ul>
            </div>
            <div class="pay_it">
            	<h2><span class="f14 fb">立即支付，即可完成订单</span></h2>
                <div class="pay_content">
                	<h4 class="f13 fb">您选择的支付方式是：</h4>
                    <div class="pay_icon">
                    	
                    	<#if map.payList??>
                    		<#list map.payList as pay>
                    		      <#if pay.payDefault=="1">
                    				<input type="hidden" value="${pay.payId}" name="payId" id="payId"/>
                    			 </#if> 		
                    			<#if (pay.payType='1')>
                    				 <img
                    				   onclick="changethis(this,${pay.payId});" 
                    					<#if pay.payDefault=="1">
                    				 		class="selected"
                    					</#if> 
                    				  alt="" src="${pay.payImage!''}" />
                    			</#if>
                    			
                    			<#if (pay.payType='2')>
                    				<img alt=""
                    				<#if pay.payDefault=="1">
                    				 	class="selected"
                    				</#if> 
                    				 onclick="changethis(this,${pay.payId});" 
                    				 	src="${pay.payImage!''}" />
                    			</#if>
                    		</#list>
                    	</#if>
                    	
                        <input type="button" onclick="payOrder()" class="pay_now" value="确定支付" />
                    </div>
                </div>
            </div>
        </div><!-- /order_success -->
        </form>
    </div><!-- /container -->
    
    <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>

<div class="mask"></div>
    <div class="dialog dia1">
        <div class="dia_tit clearfix">
            <h4 class="fl">支付提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
            <div class="dia_intro no_tc pt30">
            	<em>付款进行中...</em>
            	<div class="mt20">
            		<p class="lh180">支付完成前，请不要关闭此支付验证窗口。</p>
            		<p class="lh180">支付完成后，请根据您支付的情况点击下面按钮。</p>
            	</div>
            </div>
            <div class="dia_ops mt20 tc">       
                <a class="go_pay" href="${basePath}/customer/myorder.html">完成支付</a>
                <a class="go_shopping" href="${basePath}/help/2">付款遇到问题</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/goods_comm.js"></script>
<script type="text/javascript" src="${basePath}/index_two/js/index.js"></script>
<script type="text/javascript">
	function changethis(obj,id){  
		$("#payId").val(id); 
		$(".pay_icon").find(".selected").removeClass("selected");
		$(obj).addClass("selected");
	}
	function payOrder(){
 		dia(1);
 		$(".payOrder").submit();
 	}
</script>
</@htmlBody>