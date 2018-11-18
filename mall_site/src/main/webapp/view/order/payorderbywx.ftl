<#include "../include/common.ftl">
<@htmlHead title='${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/orderpay/wxpay.css" />
</@htmlHead>
<@htmlBody>
<#include "../index/newtop7.ftl">
<div class="head2">
    <a href="${topmap.systembase.bsetAddress}"><img id="logo_pic" alt="" src="${topmap.systembase.bsetLogo}" /></a><h1>收银台</h1>
</div>
<div class="container mb20">
    <input type="hidden" id="orderId" value="${orderCode}" />
    <div class="order-info">
        <div class="tip">
            <div class="fl">
                <h3 class="order-title">请您及时付款，以便订单尽快处理！ 订单号：${orderCode}</h3>
                <p class="order-tips">请您在提交订单后<span style="color: red;">24</span>小时内完成支付，否则订单会自动取消。</p>
            </div>
            <div class="fr">
                <div class="order-price">
                    <em>应付金额</em>
                    <strong>${order.orderPrice?string("0.00")}</strong>
                    <em>元</em>
                </div>
                <div class="order-detail">
                    <a href="#" onclick="showOrderDetail();">订单详情</a>
                </div>
            </div>
        </div>
        <div class="order-detail-content">
            收货地址：${order.shippingAddress}<br>
            收货人：${order.shippingPerson}&nbsp;${mobile}<br>
            商品名称：${orderName}<br>
        </div>
    </div>
    <div class="order-pay">
        <div class="pay-type-title">微信支付</div>

        <div class="pay-content">
            <div class="pay-content-box">
                <div class="pay-content-wxm" id="output2" ></div>
                <div class="pay-content-wxm-tip"><img src="${basePath}/images/orderpay/wxm-tip.png"/></div>
            </div>
            <div class="pay-content-sidebar">
                <img src="${basePath}/images/orderpay/wx-phone-tip.png"/>
            </div>
        </div>

        <#--<div class="clearfix other-pay-type">
            <a href="#" >
                <i></i><strong>选择其他支付方式</strong>
            </a>
        </div>-->
    </div>
</div>
<#include "../index/newbottom.ftl" />
<script type="text/javascript">
    var timename;
    $(function(){
        var basePath = '${basePath}';
        var str = window.location.href;
        var b = str.indexOf('/', str.indexOf('/') + 2);
        var local = str.substring(0, b);
        var logo=local+basePath+"/images/brand/logo.jpg";
        $("#output2").html("");
        $("#output2").qrcode({
            text: '${codeURL}',
            height: 260,
            width: 260
            //src: logo//这里配置Logo的地址即可。
        })
        timename=setInterval("GetOrderState();",1000);
    })
    function GetOrderState(){
        var orderId=$("#orderId").val();
        if(orderId==""){
            return;
        }
        $.get("${basePath}/GetOrderStateByWX.htm",{"orderCode":orderId},function(data){
            if(data=="1"){
                window.location.href="${basePath}/customer/myorder.html";
                clearInterval(timename);
            }
        },"text")
    }
    //展示订单详细
    function showOrderDetail(){
        $(".order-detail-content").toggle("2000");
    }
</script>
</@htmlBody>