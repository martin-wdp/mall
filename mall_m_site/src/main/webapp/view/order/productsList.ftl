<!doctype html>
<#assign basePath=request.contextPath>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>货品详细</title>
    <link rel="stylesheet" href="${basePath}/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/mui.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/address.css"/>
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
 <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
<body style="background:#f0f2f5;">
<#include "../publicHeader2_ftl.ftl" />
<div class="wrap" style="padding:0;margin:0;">
<div class="mui-container" style="padding:0;">
<ul class="pros-list">
<#if map??&&map.shoplist??>
    <#list map.shoplist as cars>
        <li>
            <div class="goods-img"><img src="${cars.goodsDetailBean.productVo.goodsInfoImgId}" alt=""/></div>
            <div class="goods-cont">
                <p class="gds-name">${cars.goodsDetailBean.productVo.productName}</p>
            </div>
            <p style="position:absolute;right:0.5em;bottom:0em;">X<#if cars.goodsNum??>${cars.goodsNum}</#if></p>
            <div class="goods-price">
                <#if cars.marketingId??&&cars.marketingId!=0
                    ||cars.marketingActivityId??&&cars.marketingActivityId!=0
                    ||cars.goodsGroupId??&&cars.goodsGroupId!=0>
                    <#if cars.marketingList??&&cars.marketingList?size!=0>
                        <!--判断是否参与折扣促销-->
                        <#assign mflag=0>
                        <#if vip?? && vip == "1">
                            <#assign price="${cars.goodsDetailBean.productVo.goodsInfoVipPrice}">
                        <#else>
                            <#assign price="${cars.goodsDetailBean.productVo.goodsInfoPreferPrice}">
                        </#if>

                        <!--循环促销-->
                        <#list cars.marketingList as market>
                            <!--判断是否toproductsList是相同促销，并且是折扣促销-->
                            <#if cars.marketingId??&&cars.marketingId==market.marketingId&&market.codexType=='15'>
                                <#list market.preDiscountMarketings as m>
                                    <!--是否是当前的商品-->
                                    <#if m.goodsId ==cars.goodsInfoId >
                                        <!--显示折后价格-->
                                        <#if vip?? && vip == "1">
                                            <strong>￥ ${m.vipdiscountPrice?string("0.00")}</strong>
                                            <#assign price="${m.vipdiscountPrice}">
                                        <#else>
                                            <strong>￥ ${m.discountPrice?string("0.00")}</strong>
                                            <#assign price="${m.discountPrice}">
                                        </#if>
                                        <#assign mflag=1>
                                    </#if>
                                </#list>
                            </#if>
                            <!--团购-->
                            <#if cars.goodsGroupId??&&cars.goodsGroupId==market.marketingId&&market.codexType=='10'>
                                <!--促销范围-->
                                <#if market.groupon??>
                                    <#if vip??&&vip=="1">
                                        <strong>￥ ${market.groupon.grouponVipPrice?string("0.00")}</strong>
                                        <#assign price="${market.groupon.grouponVipPrice?string('0.00')}">
                                    <#else>
                                        <strong>￥ ${market.groupon.grouponPrice?string("0.00")}</strong>
                                        <#assign price="${market.groupon.grouponPrice?string('0.00')}">
                                    </#if>
                                    <#assign mflag=1>
                                </#if>
                            </#if>
                            <!--抢购-->
                            <#if cars.marketingActivityId??&&cars.marketingActivityId==market.marketingId&&market.codexType=='11'>
                                <#if market.rushs??>
                                    <#if vip??&&vip=="1">
                                        <strong>￥ ${market.rushs[0].rushVipPrice?string("0.00")}</strong>
                                        <#assign price="${market.rushs[0].rushVipPrice?string('0.00')}">
                                    <#else>
                                        <strong>￥ ${market.rushs[0].rushPrice?string("0.00")}</strong>
                                        <#assign price="${market.rushs[0].rushPrice?string('0.00')}">
                                    </#if>
                                    <#assign mflag=1>
                                </#if>
                            </#if>
                        </#list>
                        <#if mflag==0>
                            <strong>￥${price}</strong>
                        </#if>
                    </#if>
                <#else>
                    <#if vip?? && vip == "1">
                        ￥  ${cars.goodsDetailBean.productVo.goodsInfoVipPrice?string("0.00")}
                        <#assign price="${cars.goodsDetailBean.productVo.goodsInfoVipPrice}">
                    <#else>
                        ￥  ${cars.goodsDetailBean.productVo.goodsInfoPreferPrice?string("0.00")}
                        <#assign price="${cars.goodsDetailBean.productVo.goodsInfoPreferPrice}">
                    </#if>
                </#if>
            </div>
        </li>
    </#list>
</ul>
</div>
</div>

</#if>

<script src="${basePath}/js/jquery-1.11.1.min.js"></script>
<script src="${basePath}/js/mui.min.js"></script>

</body>
</html>