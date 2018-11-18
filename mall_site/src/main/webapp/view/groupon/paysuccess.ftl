<#include "../include/common.ftl">
<@htmlHead title='${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/index_two/css/header.css" />
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
	<div style="font-family: arial;">
    <div class="container">
	<div class = "logo fl head2">
            		<!--
        <a href="${basePath}/index.html"><img src="${basePath}/index_two/images/logo.png" alt="" /></a>
		-->
        <a href="${topmap.systembase.bsetAddress}"><img src="${topmap.systembase.bsetLogo}" alt="" /></a>
    </div>
        <div class="head_s mb20">
            <!--<div class="fr w700 pt10">
                <div class="flow_progress3">
                    <ul>
                      
                    </ul>
                </div>
            </div>-->
            
            <div class="cb"></div>
        </div><!-- /head_s -->
        <div class="order_success mt30">
        	<div class="success_notice">
            	<span class="success_icon"></span>
				<p class="success_word">您已成功付款<span class='ct_price'><b>¥</b></span><span>${order.orderPrice}</span>元</p>
				<p class="order_address mt15">货物寄送至：<span>${order.shippingAddress} ${order.shippingAddressDetail}   ${order.shippingPerson} 收 ${order.shippingMobile} 默认地址</span></p>
				<p class="mt20">您还可以：<a href="#">查看订单详情</a>
            <input type="hidden" value="${order.orderId}" id="order_id">
            <input type="hidden" value="0" id="status">
            
            <!--<div class="other_do">
            	<p class="f14">您可以：<a href="#">查看订单详情</a><a href="${basePath}/index.html">继续购物</a></p>
            </div>-->
        </div><!-- /order_success -->
        
        <div class="od_banner mt10"><a href="javascript:;"><img alt="" src="http://192.168.2.99:9999/M00/00/46/wKgCY1O7sO2AQxv7AACs0_sSx5M326.jpg" /></a></div>
    </div><!-- /container -->
    
<#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/goods_comm.js"></script>
<script type="text/javascript" src="${basePath}/index_two/js/index.js"></script>
<script type="text/javascript">
    function changeGoodsList(){
        var oId=$("#order_id").val();
        var sta=$("#status").val();
        $.post("changetgoods.htm", { orderId: oId, sataus:sta },
                function(data){
                    $("#status").attr("value",data.status);
                    var xHtml="";
                    for(var i=0;i<data.list.length;i++){
                        var name=(data.list[i].goodsInfoName);
                        if(name.length>33){
                            name=name.substring(0,33)+"...";
                        }
                        xHtml+='<li><div class="rp_img tc">'+
                        '<a href="${basePath}/item/'+data.list[i].goodsInfoId+'.html"><img alt="" src="'+data.list[i].goodsInfoImgId+'" width="120" height="120" /></a>'+
                        '</div>'+
                        '<p class="rp_name mt20"><a href="javascript:;" alt="'+data.list[i].goodsInfoName+'">'+name+'</a></p>'+
                        '<p class="rp_price mt5">￥'+data.list[i].goodsInfoPreferPrice.toFixed(2)+'</p></li>';
                    }
                    $("#goods_list").html("");
                    $("#goods_list").append(xHtml);
                });
    }
</script>
</@htmlBody>
