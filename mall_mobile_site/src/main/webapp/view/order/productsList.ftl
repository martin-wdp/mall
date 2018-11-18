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
</head>
<body style="background:#f0f2f5;">
<div class="mui-appbar">
    <h2 class="mui-text-center">商品清单</h2>
    <a href="javascript:history.go(-1);"  class="back-btn"><i class="fa fa-angle-left"></i></a>
</div>
<div class="wrap" style="padding:10px 0;">
    <div class="mui-container">
        <ul class="pros-list">
<#if map??&&map.shoplist??>
    <#list map.shoplist as cars>
            <li>
                <div class="goods-img"><img src="${cars.goodsDetailBean.productVo.goodsInfoImgId}" alt=""/></div>
                <div class="goods-cont">
                    <p class="gds-name">      ${cars.goodsDetailBean.productVo.productName}</p>
                    <p>X<#if cars.goodsNum??>${cars.goodsNum}</#if></p>
                </div>
                <div class="goods-price">
                    <#if cars.marketingId??>
                        <#if cars.marketingList??&&cars.marketingList?size!=0>
                            <!--判断是否参与折扣促销-->
                            <#assign mflag=0>

                            <#assign price="${cars.goodsDetailBean.productVo.goodsInfoPreferPrice}">
                            <!--循环促销-->
                            <#list cars.marketingList as market>
                                <!--判断是否是相同促销，并且是折扣促销-->
                                <#if cars.marketingId==market.marketingId&&market.codexType=='15'>
                                    <!--促销范围-->
                                    <#list market.preDiscountMarketings as m>
                                        <!--是否是当前的商品-->
                                        <#if m.goodsId ==cars.goodsInfoId >
                                            <!--显示折后价格-->
                                            <strong>￥ ${m.discountPrice?string("0.00")}</strong>
                                            <#assign price="${m.discountPrice}">
                                            <#assign mflag=1>
                                        </#if>
                                    </#list>

                                </#if>
                            </#list>
                            <#if mflag==0>
                                <strong>￥${price}</strong>
                            </#if>
                        </#if>
                    <#else>
                        ￥  ${cars.goodsDetailBean.productVo.goodsInfoPreferPrice?string("0.00")}
                        <#assign price="${cars.goodsDetailBean.productVo.goodsInfoPreferPrice}">
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
<script src="${basePath}/js/app.js"></script>
<script>
    $(function(){

        });


</script>
</body>
</html>