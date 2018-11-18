<#include "../include/common.ftl">
<@htmlHead title='${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/index_two/css/header.css" />
<style>
    .agreement_dia {
        width: 910px!important;
        height: auto;
        border: 5px solid rgba(238,238,238,.5);
        padding: 0;
    }
    .agreement_wp {
        height: 360px;
        overflow-y: scroll;
        padding: 0 20px;
    }
    .agreement_dia .dia_tit {
        background: #eee;
        text-align: center;
        font-size: 14px;
        font-weight: 700;
        color: #666;
    }
    .agreement_dia .dia_close {
        position: absolute;
        top: 8px;
        right: 20px;
        margin-top: 0;
        background: url(${basePath}/images/agree_close.gif) no-repeat;
    }
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
	</div>
	
	
	<div style="font-family: arial;">
    <div class="container">
	<#if (topmap.temp.tempId!=10)>
	<div class = "logo fl head2">
            		<!--
        <a href="${basePath}/index.html"><img src="${basePath}/index_two/images/logo.png" alt="" /></a>
		-->
        <a href="${topmap.systembase.bsetAddress}"><img src="${topmap.systembase.bsetLogo}" alt="" /></a>
    </div>
    </#if>
        <div class="head_s mb20">
            <div class="fr w700 pt10">
                <div class="flow_progress3">
                    <ul>
                           
                    </ul>
                </div> 
            </div>
            <div class="cb"></div>
        </div><!-- /head_s -->
        <#if order??>
        <form action="payorder.html" class="payOrder" method="post" target="_blank">
        <div class="order_success">
        	<div class="success_notice">
            	<span class="success_icon"></span>
                <h3 class="f20 mb10">订单提交成功，请您尽快付款！</h3>
                <ul>
                	<input type="hidden" value="${order.orderCode}" name="orderCode"/>
                		<input type="hidden" value="${order.orderId}" name="orderId"/>
                	<li>订单号：${order.orderCode}</li>
                    <li>应付金额：<b><font color="red"><span class='ct_price'><b>¥</b></span>${order.orderPrice?string("0.00")}</font></b></li>
                </ul>
            </div>
            <div class="pay_it">
            	<h2><span class="f14 fb">立即支付，即可完成订单</span></h2>
                <div class="pay_content">
                	<h4 class="f13 fb">您选择的支付方式是：</h4>
                    <div class="pay_icon">
                    	
                    	<#if payList??>
                    		<#list payList as pay>
                    		      <#if pay.payDefault=="1">
                    				<input type="hidden" value="${pay.payId}" name="payId" id="payId"/>
                    			 </#if>

                                <#if (pay.payType='1'&& pay.isOpen='1')><#--支付宝支付-->
                                    <img
                                            onclick="changethis(this,${pay.payId});"
                                        <#if pay.payDefault=="1">
                                            class="selected"
                                        </#if>
                                            alt="" src="${pay.payImage!''}" />
                                </#if>
                                <#if (pay.payType='7' && pay.isOpen='1')><#--微信支付-->
                                    <img alt="" onclick="changethis(this,${pay.payId});"
                                        <#if pay.payDefault=="1">
                                         class="selected"
                                        </#if>
                                         src="${pay.payImage!''}" />
                                </#if>
                                <#if (pay.payType='8' && pay.isOpen='1')><#--网银支付-->
                                    <img alt="" onclick="changethis(this,${pay.payId});"
                                        <#if pay.payDefault=="1">
                                         class="selected"
                                        </#if>
                                         src="${pay.payImage!''}" />
                                </#if>
                                <#if (pay.payType='2'&& pay.isOpen='1')><#--快捷支付-->
                                    <img alt=""
                                        <#if pay.payDefault=="1">
                                         class="selected"
                                        </#if>
                                         onclick="changethis(this,${pay.payId});"
                                         src="${pay.payImage!''}" />
                                </#if>

                                <#if (pay.payType='5' && pay.isOpen='1')>
                                    <img alt="" onclick="changethis(this,${pay.payId});"
                                        <#if pay.payDefault=="1">
                                         class="selected"
                                        </#if>
                                         src="${pay.payImage!''}" />
                                </#if>
                                <#if (pay.payType='6' && pay.isOpen='1')>
                                    <img alt="" onclick="changethis(this,${pay.payId});"
                                        <#if pay.payDefault=="1">
                                         class="selected"
                                        </#if>
                                         src="${pay.payImage!''}" />
                                </#if>
                    		</#list>
                    	</#if>
                    	
                        <input type="button" onclick="payOrder()" class="pay_now" value="确定支付" />
                    </div>
                </div>
            </div>
            <div class="other_do">
            	<p class="f14">完成支付后，您可以：<a href="${basePath}/customer/detail-${order.orderId}.html">查看订单详情</a><a href="${basePath}/index.html">继续购物</a></p>
            </div>
        </div><!-- /order_success -->
        </form>
    </div><!-- /container -->
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
                <a class="go_shopping" href="javaScript:void(0)" onclick="showHelp(this)" date-value="" id="payhelp">付款遇到问题</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
    
    <div class="mask"></div>
     <div class="dialog dia2 agreement_dia">
        <div class="dia_tit clearfix">
            	支付问题描述
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
            <div class="agreement_wp no_tc pt30" id="payHelpDesc">
            	
            </div>
            <div class="dia_ops mt20 tc">       
               
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
<script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/goods_comm.js"></script>
<script type="text/javascript" src="${basePath}/index_two/js/index.js"></script>
<script type="text/javascript">
 $(function(){
      <#if payList??>
		<#list payList as pay>
		      <#if pay.payDefault=="1">
		        $("#payhelp").attr("date-value",${pay.payId});
		      </#if>
	   </#list>
	  </#if> 	      
 
 });
	function changethis(obj,id){  
		$("#payId").val(id); 
		$(".pay_icon").find(".selected").removeClass("selected");
		$(obj).addClass("selected");
		$("#payhelp").attr("date-value",id);
	}
	function payOrder(){
	if($("#payId").val()==undefined){
 		return ;
 		}
 		dia(1);
        if($("#payId").val()=='42'){
            $(".payOrder").attr("action","payorderwx.htm");
            //window.open("${basePath}/payorderwx.htm");
        }else{
            $(".payOrder").attr("action","payorder.html");
        }
        $(".payOrder").submit();
 	}
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
    function showHelp(obj){
       $(".dia1").hide();
      $("#payhelp").attr("date-value");
      var payId = ( $("#payhelp").attr("date-value"));
        $.ajax({
        type:"POST",
        url:"${basePath}/findpayone.htm",
        data:"payid="+payId,
        success:function(data){
            $("#payHelpDesc").html(data.payHelp);
            var _wd = $(window).width();
	        var _hd = $(window).height();
            $(".dia2").css("top",(_hd - $(".dia2").height())/2).css("left",(_wd - $(".dia2").width())/2);
     		$(".dia2").show();
     		}
        });
    }
</script>
</@htmlBody>