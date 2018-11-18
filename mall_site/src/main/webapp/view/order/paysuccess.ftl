<#include "../include/common.ftl">
<@htmlHead title='${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/index_two/css/header.css" />
<style type="text/css">
    .rp_name {display: block;height: 40px;line-height: 20px;overflow: hidden;}
</style>
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
        <a href="${topmap.systembase.bsetAddress}"><img src="${topmap.systembase.bsetLogo}" alt="" /></a>
    </div>
    </#if>
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
				<p><span class="success_word">您已成功付款<span class='ct_price'><b>¥</b></span><span>${price?string("0.00")}</span>元</span>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<#if order??>
					<#list order as order>
					  <#if order_index==0>
                          <span>
							  货物寄送至：
							${order.shippingProvince}省
							${order.shippingCity}市
							${order.shippingAddress}
							${(order.shippingCounty)!""}
							${order.shippingPerson} 收
							${order.shippingMobile} 默认地址
						</span>
					  </#if>
					</#list>
				</#if>
                </p>

				<#if order??>
					<#list order as order>
						<p class="order_address mt15">
							<ul>
								<li>订单号：<span>${order.orderCode}</span></li>
								<li>金额：<span>${order.orderPrice}</span></li>
								<li><span><a href="${basePath}/customer/detail-${order.orderId}.html">查看订单详情</a></span></li>

							</ul>
						</p>

					</#list>
				</#if>
            <#--<input type="hidden" value="${order.orderId}" id="order_id">-->
            <#--<input type="hidden" value="0" id="status">-->


        </div><!-- /order_success -->
        

        <div class="other_pro mt10">
        	<div class="oth_tit clearfix">
        		<h3 class="fl" style="  height: 40px;line-height: 40px;">购买此宝贝的顾客还购买以下产品</h3>
        		<a class="fr" onclick="changeGoodsList()" href="javascript:;">换一组</a>
        	</div><!--/oth_tit-->
        	<div class="oth_cont mt10">
        		<ul class="clearfix" id="goods_list">
        		<#list gs as g>
        			<li>
        				<div class="rp_img tc">
        					<a href="${basePath}/item/${g.goodsInfoId}.html"><img alt="" src="${g.goodsInfoImgId}" width="120" height="120" /></a>
        				</div>
        				<p class="rp_name mt20"><a href="javascript:;" alt="${g.goodsInfoName}">
        				<#if g.goodsInfoName??>
	        				<#if g.goodsInfoName?length gt 33>
	        					${g.goodsInfoName[0..33]}...
	        				<#else>
	        					${g.goodsInfoName}
	        				</#if>
        				</#if></a></p>
        				<p class="rp_price mt5">￥${g.goodsInfoPreferPrice?string("0.00")}</p>
        			</li>
        		 </#list>
        		</ul>
        	</div><!--/oth_cont-->
        </div><!--/other_pro-->
    </div><!-- /container -->
    <script type="text/javascript">
        //支付成功页 2015.10.13 wuyanbo add
        NTKF_PARAM.orderprice="${price}";
    </script>
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
</@htmlBody>