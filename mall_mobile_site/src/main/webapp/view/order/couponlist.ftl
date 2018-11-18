<!doctype html>
<#assign basePath=request.contextPath>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>优惠券列表</title>
    <link rel="stylesheet" href="${basePath}/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/mui.min.css"/>
    <#--<link rel="stylesheet" href="${basePath}/css/order/orderstyle.css"/>-->
    <#--<link rel="stylesheet" href="${basePath}/css/style.css"/>-->
    <link rel="stylesheet" href="${basePath}/css/order/style.css"/>
    <style>


    </style>
</head>
<body >
<div class="mui-appbar">
    <h2 class="mui-text-center">选择优惠券</h2>
    <a href="javascript:history.go(-1);" class="back-btn"><i class="fa fa-angle-left"></i></a>
</div>

<div class="wrap">
    <div class="mui-container">
        <ul class="coupon-list">
<#if couponlist??&&(couponlist?size>0)>
    <#list couponlist as coupon>

        <#if coupon.couponRulesType=='1'>
            <li class="switch-item">
            ${coupon.couponName}-直降${coupon.couponStraightDown.downPrice}
                <span class="switch-btn <#if codeNo??&&codeNo==coupon.codeNo> checked</#if>"  attr-codeNo="${coupon.codeNo}"><i></i></span>
            </li>

        </#if>
        <#if coupon.couponRulesType=='2'>
            <li class="switch-item">
            ${coupon.couponName}-满减${coupon.couponFullReduction.reductionPrice}
                <span class="switch-btn <#if codeNo??&&codeNo==coupon.codeNo> checked</#if>" attr-codeNo="${coupon.codeNo}"><i></i></span>
            </li>

        </#if>
    </#list><#else >
    <p class="coupon_empty light_9">
        您账户中没有可使用的优惠券,或没有达到优惠券使用要求!
    </p>

</#if>
        </ul>
        <button class="mui-btn mui-btn-danger" type="button" onclick="dosubmit()">确定</button>
    </div>
</div>

<form id="subForm" method="post">
    <input type="hidden" name="typeId" value="${typeId!''}" id="typeId">
    <input type="hidden" name="ch_pay" id="ch_pay" value="${ch_pay!''}">
    <input type="hidden" name="addressId" id="addressId" value="${addressId!''}">
    <input type="hidden" name="deliveryPointId" id="deliveryPointId" value="${deliveryPointId!''}">
    <input type="hidden" name="codeNo" id="codeNo" >

</form>
<script src="${basePath}/js/jquery-1.11.1.min.js"></script>
<script src="${basePath}/js/mui.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script>

    function dosubmit(){
        $("#subForm").attr("action","suborder.htm").submit();
    }
    $(function(){
        $(".switch-btn").click(function(){
            $(this).toggleClass("checked").parent().siblings(".switch-item").find(".switch-btn").removeClass("checked");
            if($(this).hasClass("checked")){
                $("#codeNo").val($(this).attr("attr-codeNo"));
            }
        });
    });

</script>
</body>
</html>