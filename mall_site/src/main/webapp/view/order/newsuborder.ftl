<#include "../include/common.ftl">
<@htmlHead title='${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/suborder/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/suborder/style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/index_two/css/header.css"/>
<style>
    html {
        overflow-x: hidden;
    }

    .cust_allAddress {
        position: relative;
    }

    input:-webkit-autofill {
        -webkit-box-shadow: 0 0 0 1000px white inset;
    }

    .goods-suit .goods-suit-tit p {
        font-family: verdana;
        color: #e4393c;
        display: inline-block;
        vertical-align: middle;
        width: 90px;
        font-weight: bold;
    }

    .wp a {
        color: #555;
        outline: none;
    }

    .wp a:hover {
        text-decoration: none;
        color: #F63;
    }

    .dia_tit {
        height: 30px;
        line-height: 30px;
        padding: 0 0px;
        font-family: microsoft YaHei;
        font-size: 14px;
        color: #fff;
        background: #eb6122;
        border-radius: 5px 5px 0 0;
    }

    .dia_tit h4 {
        line-height: 30px;
    }

    .picksite-box .pick-sites-more {
        max-height: 276px;
        _height: expression(this.scrollHeight>276?'276px':'auto');
        position: relative;
        top: 0;
        left: 0;
    }

    .overflow {
        overflow-y: scroll;
    }

    .picksite-box .pick-sites {
        width: 660px;
    }

    .picksite-box .site-item {
        margin: 0 0 10px;
    }

    .picksite-box .site-item-selected .site-in-short {
        border: 2px solid #e4393c;
        padding: 7px 10px;
    }

    .picksite-box .site-in-short {
        float: left;
        position: relative;
        top: 0;
        left: 0;
        border: 1px solid #ebebeb;
        height: 22px;
        line-height: 22px;
        padding: 4px 10px;
        width: 250px;
        margin-right: 10px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        cursor: pointer;
    }

    .picksite-box .site-item-selected .site-in-short b {
        display: block;
        position: absolute;
        right: 0;
        bottom: 0;
        width: 12px;
        height: 12px;
        overflow: hidden;
        background: url(http://misc.360buyimg.com/user/purchase/2.0.0/css/i/selected-icon.png) no-repeat;
    }

    .picksite-box .field {
        float: left;
        position: relative;
        top: 0;
        left: 0;
        width: 295px;
        line-height: 18px;
        padding-right: 55px;
    }

    .picksite-box .field .tip {
        color: #b3b3b3;
        display: block;
        height: 36px;
        line-height: 18px;
        overflow: hidden;
    }

    .picksite-box .field .map-link {
        display: block;
        position: absolute;
        right: 0;
        height: 18px;
        line-height: 18px;
        top: 0;
    }

    .clear, .clr {
        display: block;
        overflow: hidden;
        clear: both;
        height: 0;
        line-height: 0;
        font-size: 0;
    }

    .shopping-list .dis-modes .mode-tab-nav li.current {
        border: 2px solid #e4393c;
        padding: 4px 0;
        width: 146px;
    }

    .shopping-list .dis-modes .mode-tab-nav li.current b {
        display: block;
        position: absolute;
        right: 0;
        bottom: 0;
        width: 12px;
        height: 12px;
        overflow: hidden;
        background: url(http://misc.360buyimg.com/user/purchase/css/i/user_purchase20150409154845.png?__sprite) no-repeat;
        background-position: 0 0;
    }

    .dia_tip {
        background: #fff;
        padding-bottom: 20px;
        min-height: 120px;
    }
</style>
</@htmlHead>
    <title></title>

</head>
<@htmlBody>
<#include "../index/newtop7.ftl">
<input type="hidden" id="currentProvince" value="${chProvince!''}"/>
<input type="hidden" id="basePath" value="${basePath}"/>
<input type="hidden" id="isOpen" value="${pointSet.isOpen!''}"/>
<input type="hidden" id="vip" value="${vip!''}"/>

<div style="font-family: arial;">
    <div class="container">
    <#if (topmap.temp.tempId!=10)>
        <div class="logo fl head2">
            <!--
        <a href="${basePath}/index.html"><img src="${basePath}/index_two/images/logo.png" alt="" /></a>
		-->
            <a href="${topmap.systembase.bsetAddress}"><img src="${topmap.systembase.bsetLogo}" alt=""
                                                            style="width:165px;height:40px;"/></a>
        </div>
    </#if>
        <div class="head_s mb20">
            <div class="fr w700 pt10">
                <div class="flow_progress2">
                    <ul>
                        <li class="step1">1.查看购物车</li>
                        <li class="step2">2.填写核对订单信息</li>
                        <li class="step3">3.提交订单成功</li>
                    </ul>
                </div>
            </div>
            <div id="logo">
                <!--<a class="logo fl" href="${basePath}/index.html"><img alt="" src="${basePath}/images/logo.gif" /></a>-->
            </div>
            <div class="cb"></div>
        </div>
        <!-- /head_s -->
    <#--自提点名称-->
    <#assign pickflag='0'>
    <#--自提点id中间变量-->
    <#assign pickIdflag=0>
    <#--运费存储隐藏域-->
        <input id="yfprice" type="hidden"/>
    <#--boss运费存储隐藏域-->
        <input id="bossyfprice" type="hidden"/>
    <#--应付总额隐藏域-->
        <input id="sumpriceflag" type="hidden"/>

        <div class="jd-dialog  dia8 z-dialog">
            <div class="jd-dialog-title">选择自提点<a href="#" class="jd-dialog-close" onclick="cls()">×</a></div>
            <div id="selfpick_siteDiv" class="p10"><!-- 选择自提点 -->

                <div class="form picksite-box">
                    <div class="item clearfix">
                        <span class="label">选择自提点：</span>

                        <div class="fl since">


                        </div>
                    </div>
                    <div class="item">
                        <span class="label">&nbsp;</span>

                        <div class="fl">
                            <div class="op-btns">
                                <a class="btn-9" onclick="doSaveDialogPickSite()">确定</a>
                                <a class="btn-9 ml10" href="javascript:;" onclick="cls()">取消</a>
                            </div>
                            <div class="ftx-03 mt10">自提时付款，支持现金、POS刷卡、支票支付</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="w990">
            <div class="jd_tit">
                <h2>填写并核对订单信息</h2>
            </div>
            <form name="subForm" id="sub_order" action="${basePath}/order/submitOrder.html" method="post">

                <input type="hidden" id="csrfNo" name="CSRFToken" value="<#if sx??>${sx}</#if>"/>
                <input type="hidden" name="deliveryPointId" id="deliveryPointId">
                <input type="hidden" name="typeId" id="typeId"/>

                <div class="jd_order_det">
                    <div class="step-tit clearfix">
                        <h3>收货人信息</h3>
                        <a href="javascript:void(0);" onclick="dia(1);setCustAdressDefault();">新增收货地址</a>
                    </div>
                    <div class="step-cont">
                        <div class="consignee-addr">
                            <div class="consignee-cont">
                                <input type="hidden" name="custAddress" class="ch_address" value=""/>
                                <input type="hidden" name="distinctId" class="ch_distinctId" value=""/>


                                <ul class="cust_allAddress">

                                </ul>
                            </div>
                            <!--consignee-cont-->
                            <div id="addr-up" class="addr-up disabled"></div>
                            <div id="addr-down" class="addr-down"></div>
                        </div>
                        <!--consignee-addr-->
                    </div>
                    <!--step_cont-->

                    <div class="step-tit clearfix">
                        <a href="../myshoppingcart.html">返回修改购物车</a>

                        <h3>送货清单</h3>
                    </div>
                    <!--step-tit-->
                    <div class="step-cont">
                    <#--后台传过来的优惠金额-->
                    <#assign freePrice=0>
                    <#--原总金额-->
                    <#assign sumOldPrice="${sumOldPrice!0}">
                    <#--应付金额-->
                    <#assign sumPrice="${sumPrice!0}">
                    <#--boss商品总价格-->
                    <#assign bosssumPrice="${bossSumPrice!0}">
                    <#--add by 付陈林 判读是否单一的抢购商品 2015年12月10日-->
                    <#--定义两个变量，商品总数，以及抢购商品的总数-->
                    <#--add end    -->
                    <#assign freePrice="${sumOldPrice?number-sumPrice?number}">

                    <#assign sumcount=0>
                    <#if cartMap??>
                        <#if cartMap.thirds??&&cartMap.thirds?size!=0>
                            <#list cartMap.thirds as third>


                                <div class="shopping-list clearfix mt20">
                                    <div class="goods-list fr">
                                        <h4>商家：<#if third.thirdId==0>商城自营<#else>${third.thirdName} </#if></h4>

                                        <div class="goods-items">
                                            <input type="hidden" value="${third.thirdId?string('0')}" name="thirdIds"
                                                   id="thirdIds"/>
                                            <#if cartMap.marketinglist??&&cartMap.marketinglist?size!=0>
                                                <#list cartMap.marketinglist as market>
                                                    <#if market.businessId??&&market.businessId==third.thirdId>
                                                        <div class="goods-suit goods-last clearfix">
                                                        <#--一件货品有多个促销,得到选择的一个促销展示出来-->
                                                            <div class="goods-suit-tit" <#assign haveGoods="0">
                                                                <#if cartMap.shoplist??&&cartMap.shoplist?size!=0>
                                                                    <#list cartMap.shoplist as cars>
                                                                        <#if cars.marketingActivityId??&&cars.marketingActivityId!=0&&cars.marketingActivityId==market.marketingId>
                                                                            <#assign haveGoods="1">
                                                                        <#elseif cars.goodsGroupId??&&cars.goodsGroupId!=0&&cars.goodsGroupId==market.marketingId>
                                                                            <#assign haveGoods="1">
                                                                        <#--<#elseif cars.marketingId??&&cars.marketingId!=0&&cars.marketingId==market.marketingId>
                                                                            <#assign haveGoods="1">-->
                                                                        </#if>
                                                                    </#list>
                                                                </#if>
                                                                <#if haveGoods=="0">
                                                                 style="display:none;"
                                                                </#if>>


                                                                <strong style="color:red ">
                                                                    <!--直降-->
                                                                    <#if market.codexType=='1'>
                                                                        直降<#if vip?? && vip == "1">${market.priceOffMarketing.offVipValue}<#else>${market.priceOffMarketing.offValue}</#if>元
                                                                    </#if>

                                                                    <!--满减-->
                                                                    <#if market.codexType=='5'>
                                                                        <#list market.fullbuyReduceMarketings as fr>
                                                                            <#if vip?? && vip == "1">
                                                                                满 ${fr.vipFullPrice}
                                                                                减${fr.vipReducePrice}元 &nbsp;
                                                                            <#else>
                                                                                满 ${fr.fullPrice}
                                                                                减${fr.reducePrice}元 &nbsp;
                                                                            </#if>
                                                                        </#list>
                                                                    </#if>

                                                                    <!--满折-->
                                                                    <#if market.codexType=='8'>
                                                                        <#list market.fullbuyDiscountMarketings as mz>
                                                                            <#if vip?? && vip == "1">
                                                                                满 ${mz.vipFullPrice}
                                                                                打 ${mz.vipFullbuyDiscount*10}折
                                                                                &nbsp;
                                                                            <#else>
                                                                                满 ${mz.fullPrice}
                                                                                打 ${mz.fullbuyDiscount*10}折
                                                                                &nbsp;
                                                                            </#if>
                                                                        </#list>
                                                                    </#if>

                                                                <#--团购-->
                                                                    <#if market.codexType=='10'>
                                                                        <#--团购不显示折扣 add by 付陈林 edit start-->
                                                                        <#--<#if vip?? && vip == "1">${market.groupon.grouponVipDiscount}<#else>${market.groupon.grouponDiscount}</#if>折团购-->
                                                                        ${market.marketingName}
                                                                        <#--edit end-->
                                                                    </#if>

                                                                <#--抢购-->
                                                                    <#if market.codexType=='11'>
                                                                        <#--抢购不显示商品 edit by 付陈林-->
                                                                        <#--<#if vip?? && vip == "1"> ${(market.rushs[0].rushVipDiscount*10)?string('0.#')}<#else> ${(market.rushs[0].rushDiscount*10)?string('0.#')}</#if>-->
                                                                        <#--折抢购-->
                                                                        ${market.marketingName}
                                                                        <#--edit end-->
                                                                    </#if>
                                                                    <!--包邮-->
                                                                    <#if market.codexType=='12'>
                                                                        <#if vip?? && vip == "1">
                                                                            <#if market.vipShippingMoney??>满 ${market.vipShippingMoney} 包邮</#if>
                                                                        <#else>
                                                                            <#if market.shippingMoney??>满 ${market.shippingMoney} 包邮</#if>
                                                                        </#if>
                                                                    </#if>

                                                                </strong>
                                                            </div>
                                                            <#if cartMap.shoplist??&&cartMap.shoplist?size!=0>
                                                                <#list cartMap.shoplist as cars>
                                                                    <#if (cars.marketingActivityId??&&cars.marketingActivityId!=0&&cars.marketingActivityId==market.marketingId)
                                                                    ||(cars.goodsGroupId??&&cars.goodsGroupId!=0&&cars.goodsGroupId==market.marketingId)
                                                                    <#--||(cars.marketingId??&&cars.marketingId!=0&&cars.marketingId==market.marketingId)-->>
                                                                        <#if cars.goodsDetailBean.productVo.thirdId==third.thirdId>
                                                                            <input type="hidden"
                                                                                   class="shoppingCartId${cars.thirdId}"
                                                                                   name="shoppingCartId"
                                                                                   value="${cars.shoppingCartId?string('0')}"/>
                                                                            <input type="hidden" name="box" class="box"
                                                                                   value="${cars.shoppingCartId?string('0')}"
                                                                                   productNum="${cars.goodsNum}"/>
                                                                            <#assign sumcount="${sumcount?number+cars.goodsNum?number}">

                                                                            <div class="goods-item clearfix">
                                                                                <div class="p-img">
                                                                                    <a target="_blank" href="${basePath}/item/${cars.goodsDetailBean.productVo.goodsInfoId}.html">
                                                                                        <img src="<#if cars.goodsDetailBean.productVo.goodsInfoImgId??>${cars.goodsDetailBean.productVo.goodsInfoImgId}</#if>"
                                                                                             alt="" width="80" height="80">
                                                                                    </a>
                                                                                </div>
                                                                                <div class="goods-msg">
                                                                                    <div class="p-name">
                                                                                        <a href="${basePath}/item/${cars.goodsDetailBean.productVo.goodsInfoId}.html" target="_blank">
                                                                                        ${cars.goodsDetailBean.productVo.productName}
                                                                                        </a>
                                                                                    </div>
                                                                                    <div class="p-price">

                                                                                        <font style="color:red" class="price_${cars.goodsDetailBean.productVo.goodsInfoId}">
                                                                                            <#assign price=0>
                                                                                            <#if cars.marketingId??>
                                                                                                <#if cars.marketingList??&&cars.marketingList?size!=0>
                                                                                                    <!--判断是否参与折扣促销-->
                                                                                                    <#assign mflag=0>

                                                                                                    <#if vip??&&vip=="1">
                                                                                                        <#assign price="${cars.goodsDetailBean.productVo.goodsInfoVipPrice}">
                                                                                                    <#else>
                                                                                                        <#assign price="${cars.goodsDetailBean.productVo.goodsInfoPreferPrice}">
                                                                                                    </#if>
                                                                                                    <!--循环促销-->
                                                                                                    <#list cars.marketingList as market>
                                                                                                        <!--判断是否是相同促销，并且是折扣促销-->
                                                                                                        <#if cars.marketingId==market.marketingId&&market.codexType=='15'>
                                                                                                            <!--促销范围-->
                                                                                                            <#list market.preDiscountMarketings as m>
                                                                                                                <!--是否是当前的商品-->
                                                                                                                <#if m.goodsId ==cars.goodsInfoId >
                                                                                                                    <!--显示折后价格-->
                                                                                                                    <#if vip??&&vip=="1">
                                                                                                                        <strong>￥ ${m.vipdiscountPrice?string("0.00")}</strong>
                                                                                                                        <#assign price="${m.vipdiscountPrice}">
                                                                                                                    <#else>
                                                                                                                        <strong>￥ ${m.discountPrice?string("0.00")}</strong>
                                                                                                                        <#assign price="${m.discountPrice}">
                                                                                                                    </#if>
                                                                                                                    <#assign mflag=1>
                                                                                                                </#if>
                                                                                                            </#list>
                                                                                                        <#elseif cars.goodsGroupId==market.marketingId&&market.codexType=='10'><!--团购促销-->
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
                                                                                                        <#elseif cars.marketingId==market.marketingId&&market.codexType=='11'><!--抢购促销-->
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
                                                                                                <#else>
                                                                                                    <#if vip??&&vip=="1">
                                                                                                        ￥  ${cars.goodsDetailBean.productVo.goodsInfoVipPrice?string("0.00")}
                                                                                                        <#assign price="${cars.goodsDetailBean.productVo.goodsInfoVipPrice}">
                                                                                                    <#else>
                                                                                                        ￥  ${cars.goodsDetailBean.productVo.goodsInfoPreferPrice?string("0.00")}
                                                                                                        <#assign price="${cars.goodsDetailBean.productVo.goodsInfoPreferPrice}">
                                                                                                    </#if>

                                                                                                </#if>
                                                                                            <#else>
                                                                                                <#if vip??&&vip=="1">
                                                                                                    ￥  ${cars.goodsDetailBean.productVo.goodsInfoVipPrice?string("0.00")}
                                                                                                    <#assign price="${cars.goodsDetailBean.productVo.goodsInfoVipPrice}">
                                                                                                <#else>
                                                                                                    ￥  ${cars.goodsDetailBean.productVo.goodsInfoPreferPrice?string("0.00")}
                                                                                                    <#assign price="${cars.goodsDetailBean.productVo.goodsInfoPreferPrice}">
                                                                                                </#if>

                                                                                            </#if>

                                                                                        </font>



                                                                                    <#--计算各个商家的费用-->
                                                                                        <#assign payPrice=0>
                                                                                        <#assign  payPrice="${payPrice+(price?number*cars.goodsNum?number)}">
                                                                                        <span class="ml20">x<#if cars.goodsNum??>${cars.goodsNum}</#if></span>
                                                                                        <span class="ml20 p-inventory stock_${cars.goodsDetailBean.productVo.goodsInfoId!'0'}"
                                                                                              skuid="335154"
                                                                                              id="product${cars.goodsDetailBean.productVo.goodsInfoId!'0'}">
                                                                                            <#if (cars.goodsDetailBean.productVo.goodsInfoStock>0)>
                                                                                                现货
                                                                                            <#else>
                                                                                                无货
                                                                                            </#if>
                                                                                        </span>
                                                                                    </div>
                                                                                <#--<div><i class="p-icon p-icon-w"></i><span class="ftx-04">7天无理由退货</span></div>-->
                                                                                </div>
                                                                            </div>

                                                                            <!--good-suit-->

                                                                        </#if>
                                                                    </#if>
                                                                </#list>

                                                            </#if>
                                                        </div>
                                                    </#if>
                                                </#list>

                                            </#if>
                                        <#--没有参与促销-->
                                            <#if cartMap.shoplist??&&cartMap.shoplist?size!=0>
                                                <#list cartMap.shoplist as cars>
                                                    <!--是套装-->
                                                    <#if cars.fitId??>
                                                        <#if cars.thirdId==third.thirdId>
                                                            <input type="hidden" class="shoppingCartId${cars.thirdId}"
                                                                   name="shoppingCartId"
                                                                   value="${cars.shoppingCartId?string('0')}"/>
                                                            <input type="hidden" name="box" class="box"
                                                                   value="${cars.shoppingCartId?string('0')}"
                                                                   productNum="${cars.goodsNum}"/>
                                                            <#assign sumcount="${sumcount?number+cars.goodsNum?number}">
                                                            <div class="goods-suit goods-last clearfix">
                                                                <div class="goods-suit-tit">

                                                                    <strong>【套装】${cars.goodsGroupVo.groupName}
                                                                        每套优惠${cars.goodsGroupVo.groupPreferamount}
                                                                        元<input type="hidden"
                                                                                value="${cars.goodsGroupVo.groupPreferamount}"
                                                                                class="groupPreferamount"/></strong>
                                                                </div>
                                                                <#if cars.goodsGroupVo.productList??&&cars.goodsGroupVo.productList?size!=0>
                                                                    <#list cars.goodsGroupVo.productList as pro>
                                                                        <div class="goods-item clearfix">
                                                                            <div class="p-img">
                                                                                <a href="${basePath}/item/${pro.productDetail.goodsInfoId}.html"
                                                                                   target="_blank"
                                                                                   title="${basePath}/item/${pro.productDetail.goodsInfoId}.html">
                                                                                    <img style="width:50px;height:50px;"
                                                                                         title="${pro.productDetail.goodsInfoName}"
                                                                                         alt="${pro.productDetail.goodsInfoName}"
                                                                                         src="<#if pro.productDetail.goodsInfoImgId??>${pro.productDetail.goodsInfoImgId}</#if>"/></a>


                                                                            </div>
                                                                            <div class="goods-msg">
                                                                                <div class="p-name">
                                                                                    <a href="javascript:void(0);"
                                                                                       target="_blank">
                                                                                    ${pro.productDetail.goodsInfoName}
                                                                                    </a>
                                                                                </div>
                                                                                <div class="p-price">
                                                                                <#--计算各个商家的费用-->
                                                                                    <#assign payPrice=0>
                                                                                    <#if vip??&&vip=="1">
                                                                                        <#assign payPrice="${payPrice+(pro.productDetail.goodsInfoVipPrice?number*cars.goodsNum?number)}">
                                                                                        <span class="pv_smprice price_${pro.productDetail.goodsInfoId}" style="color:red">
                                                                                        ￥ ${pro.productDetail.goodsInfoVipPrice?number*cars.goodsNum?number}
                                                                                        </span>
                                                                                    <#else>
                                                                                        <#assign payPrice="${payPrice+(pro.productDetail.goodsInfoPreferPrice?number*cars.goodsNum?number)}">
                                                                                        <span class="pv_smprice price_${pro.productDetail.goodsInfoId}" style="color:red">
                                                                                        ￥ ${pro.productDetail.goodsInfoPreferPrice?number*cars.goodsNum?number}
                                                                                        </span>
                                                                                    </#if>

                                                                                    <span class="ml20">1件/套 x<#if cars.goodsNum??>${cars.goodsNum}</#if></span>
                                                                                    <span class="ml20 p-inventory stock_${pro.productDetail.goodsInfoId}"
                                                                                          skuid="335154"
                                                                                          id="product${cars.goodsGroupVo.stock!'0'}">
                                                                                        <#if (cars.goodsGroupVo.stock>0)>
                                                                                            现货
                                                                                        <#else>
                                                                                            无货
                                                                                        </#if>
                                                                                    </span>
                                                                                </div>
                                                                            <#--<div><i class="p-icon p-icon-w"></i><span class="ftx-04">7天无理由退货</span></div>-->
                                                                            </div>
                                                                        </div>
                                                                    </#list>
                                                                </#if>

                                                            </div>
                                                            <!--good-suit-->



                                                        </#if>
                                                    <#else>
                                                    <#--不是套装-->
                                                        <#if (cars.marketingActivityId??&&cars.marketingActivityId!=0)
                                                        ||(cars.goodsGroupId??&&cars.goodsGroupId!=0)
                                                        <#--||(cars.marketingId??&&cars.marketingId!=0)-->>
                                                        <#else >
                                                            <#if cars.goodsDetailBean.productVo.thirdId==third.thirdId>

                                                                <input type="hidden"
                                                                       class="shoppingCartId${cars.thirdId}"
                                                                       name="shoppingCartId"
                                                                       value="${cars.shoppingCartId?string('0')}"/>
                                                                <input type="hidden" name="box" class="box"
                                                                       value="${cars.shoppingCartId?string('0')}"
                                                                       productNum="${cars.goodsNum}"/>
                                                                <#assign sumcount="${sumcount?number+cars.goodsNum?number}">
                                                                <div class="goods-suit goods-last clearfix">
                                                                <#--<div class="goods-suit-tit">-->
                                                                <#--<strong>-->
                                                                <#--普通商品-->
                                                                <#--</strong>-->
                                                                <#--</div>-->

                                                                    <div class="goods-item clearfix">
                                                                        <div class="p-img">
                                                                            <a target="_blank"
                                                                               href="${basePath}/item/${cars.goodsDetailBean.productVo.goodsInfoId}.html"><img
                                                                                    src="<#if cars.goodsDetailBean.productVo.goodsInfoImgId??>${cars.goodsDetailBean.productVo.goodsInfoImgId}</#if>"
                                                                                    alt="" width="80" height="80"></a>
                                                                        </div>
                                                                        <div class="goods-msg">
                                                                            <div class="p-name">
                                                                                <a href="${basePath}/item/${cars.goodsDetailBean.productVo.goodsInfoId}.html"
                                                                                   target="_blank">
                                                                                ${cars.goodsDetailBean.productVo.productName}
                                                                                </a>
                                                                            </div>
                                                                            <div class="p-price">

                                                                                <font style="color: red"
                                                                                      class="price_${cars.goodsDetailBean.productVo.goodsInfoId}">
                                                                                    <#assign price=0>
                                                                                    <#if cars.marketingId??>
                                                                                        <#if cars.marketingList??&&cars.marketingList?size!=0>
                                                                                            <!--判断是否参与折扣促销-->
                                                                                            <#assign mflag=0>

                                                                                            <#if vip??&&vip=="1">
                                                                                                <#assign price="${cars.goodsDetailBean.productVo.goodsInfoVipPrice}">
                                                                                            <#else>
                                                                                                <#assign price="${cars.goodsDetailBean.productVo.goodsInfoPreferPrice}">
                                                                                            </#if>

                                                                                            <!--循环促销-->
                                                                                            <#list cars.marketingList as market>
                                                                                                <!--判断是否是相同促销，并且是折扣促销-->
                                                                                                <#if cars.marketingId==market.marketingId&&market.codexType=='15'>
                                                                                                    <!--促销范围-->
                                                                                                    <#list market.preDiscountMarketings as m>
                                                                                                        <!--是否是当前的商品-->
                                                                                                        <#if m.goodsId ==cars.goodsInfoId >
                                                                                                            <!--显示折后价格-->
                                                                                                            <#if vip??&&vip=="1">
                                                                                                                <strong>￥ ${m.vipdiscountPrice?string("0.00")}</strong>
                                                                                                                <#assign price="${m.vipdiscountPrice}">
                                                                                                            <#else>
                                                                                                                <strong>￥ ${m.discountPrice?string("0.00")}</strong>
                                                                                                                <#assign price="${m.discountPrice}">
                                                                                                            </#if>
                                                                                                            <#assign mflag=1>
                                                                                                        </#if>
                                                                                                    </#list>
                                                                                                <#elseif cars.goodsGroupId==market.marketingId&&market.codexType=='10'><!--团购促销-->
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
                                                                                                <#elseif cars.marketingId==market.marketingId&&market.codexType=='11'><!--抢购促销-->
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
                                                                                        <#else>
                                                                                            <#if vip??&&vip=="1">
                                                                                                ￥  ${cars.goodsDetailBean.productVo.goodsInfoVipPrice?string("0.00")}
                                                                                                <#assign price="${cars.goodsDetailBean.productVo.goodsInfoVipPrice}">
                                                                                            <#else>
                                                                                                ￥  ${cars.goodsDetailBean.productVo.goodsInfoPreferPrice?string("0.00")}
                                                                                                <#assign price="${cars.goodsDetailBean.productVo.goodsInfoPreferPrice}">
                                                                                            </#if>

                                                                                        </#if>
                                                                                    <#else>
                                                                                        <#if vip??&&vip=="1">
                                                                                            ￥  ${cars.goodsDetailBean.productVo.goodsInfoVipPrice?string("0.00")}
                                                                                            <#assign price="${cars.goodsDetailBean.productVo.goodsInfoVipPrice}">
                                                                                        <#else>
                                                                                            ￥  ${cars.goodsDetailBean.productVo.goodsInfoPreferPrice?string("0.00")}
                                                                                            <#assign price="${cars.goodsDetailBean.productVo.goodsInfoPreferPrice}">
                                                                                        </#if>

                                                                                    </#if>
                                                                                </font>



                                                                            <#--计算各个商家的费用-->
                                                                                <#assign payPrice=0>
                                                                                <#assign  payPrice="${payPrice+(price?number*cars.goodsNum?number)}">
                                                                            <#--<strong>￥${cart.goodsGroupVo.groupPreferamount?string("0.00")}</strong>-->
                                                                                <span class="ml20">x<#if cars.goodsNum??>${cars.goodsNum}</#if></span>
                                                                                <span class="ml20 p-inventory stock_${cars.goodsDetailBean.productVo.goodsInfoId}"
                                                                                      skuid="335154"
                                                                                      id="product${cars.goodsDetailBean.productVo.goodsInfoStock!'0'}">
                                                                                    <#if (cars.goodsDetailBean.productVo.goodsInfoStock>0)>
                                                                                        现货
                                                                                    <#else>
                                                                                        无货
                                                                                    </#if>
                                                                                </span>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <!--good-suit-->
                                                            </#if>
                                                        </#if>
                                                    </#if>
                                                </#list>
                                            </#if>
                                            <input id="third_${third.thirdId}" value="${payPrice!''}" type="hidden"/>
                                        </div>
                                        <!--goods-itmes-->
                                    </div>
                                    <!--goods-list-->
                                    <div class="dis-modes">

                                        <div class="mode-item mode-tab pr">

                                            <#if third.thirdId==0>

                                                <h4>支付方式:</h4>

                                                <!--step-tit-->
                                                <div class="step-cont pt10">
                                                    <div class="payment-list" style="padding-left:0px;">
                                                        <div class="list-cont clearfix">
                                                            <input type="hidden" name="ch_pay" class="ch_pay"/>
                                                            <ul class="clearfix">
                                                                <#if payList??>
                                                                    <#list payList as pl>
                                                                        <#if pl.isOpen=="1">
                                                                            <li>
                                                                                <div class="payment-item bossitem  <#if pl_index==0> item-selected</#if> payset_radio_${pl.paymentId}" <#--<#if pl.temp4=='1'> onclick="dia(5)"</#if>-->
                                                                                     data-id=${pl.paymentId}>
                                                                                    <b></b>
                                                <span class="qmark-icon qmark-tip"
                                                      data-tips="${(pl.temp1)!''} <a href='${basePath}/${(pl.temp3)!''}' target='_blank'>${(pl.temp2)!''}</a>"></span>
                                                                                    <span>${(pl.name)!''}</span>
                                                                                </div>
                                                                            </li>
                                                                        </#if>
                                                                    </#list>
                                                                </#if>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            <#else>

                                                <h4>支付方式:</h4>
                                                <!--step-tit-->
                                                <div class="step-cont pt10">
                                                    <div class="payment-list" style="padding-left:0px;">
                                                        <div class="list-cont clearfix">
                                                            <input type="hidden" name="ch_paythird"
                                                                   class="ch_paythird"/>
                                                            <ul class="clearfix">


                                                                <li>
                                                                    <div class="payment-item thirditem item-selected "
                                                                         data-id="1">
                                                                        <b></b>
                                                <span class="qmark-icon qmark-tip"
                                                      data-tips="即时到帐，支持绝大数银行借记卡及部分银行信用卡"></span>
                                                                        <span>在线支付</span>
                                                                    </div>
                                                                </li>


                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </#if>

                                            <h4 class="pt20">
                                                配送方式：<#--（<a class="ftx-05 alink" href="#">对应商品</a>）--></h4>

                                            <div class="mode-tab-nav">
                                                <ul class=" clearfix">
                                                    <#if cartMap??&& cartMap.frightlist?? &&(cartMap.frightlist?size>0)>
                                                        <#list cartMap.frightlist as freight>
                                                            <#if freight.freightThirdId==third.thirdId>
                                                                <li class="mode-tab-item item-selected  curr <#if third.thirdId==0>fretype bossfretype</#if>"
                                                                    id="jd_shipment_item">
                                                                    <b></b>
														<span id="jdShip-span-tip" class="m-txt">
                                                        <i class="qmark-icon qmark-tip" data-tips="商城自营配送方式"></i>
                                                        ${freight.freightTemplateName}
                                                        </span>
                                                                </li>
                                                            </#if>


                                                        </#list>
                                                    <#else>
                                                        <li class="mode-tab-item  curr"
                                                            id="jd_shipment_item">
                                                            <input type="hidden" name="distributionId"
                                                                   id="distributionId" value="" expressname=""/>
                                                            <span id="jdShip-span-tip"
                                                                  class="m-txt"><i class="qmark-icon qmark-tip"
                                                                                   data-tips="商城自营配送方式"></i>快递运输</span><b></b>
                                                        </li>

                                                    </#if>
                                                    <!--屏蔽上门自提-->
                                                    <#if third.thirdId==0 && false>
                                                        <li class="mode-tab-item item-selected fretype"
                                                            id="ziti">
                                                            <b></b>
														<span id="jdShip-span-tip" class="m-txt">
                                                          <i class="qmark-icon qmark-tip ziti"
                                                             data-tips="上门自提只支持商城自营商品在线付款"></i>
                                                            上门自提
                                                        </li>
                                                    </#if>

                                                </ul>
                                            </div>


                                            <div class="mode-tab-con layout" id="selfpick_shipment"
                                                 style="display:none">
                                                <ul class="mode-list">
                                                    <li class="clearfix">
                                                        <div class="fore1"><span class="ftx-03">自提地点：</span><b
                                                                id="selfpick_name"></b></div>
                                                        <div class="fore2"><a href="javascript:;" onclick="dia(8)"
                                                                              class="ftx-05 picksite-edit">修改</a></div>
                                                    </li>
                                                </ul>
                                            </div>

                                            <input type="hidden" id="bosspaySum" name="bosspaySum"
                                                   value="${paySum!'0'}"/>
                                        </div>

                                    </div>
                                </div>


                            </#list>
                        </#if>
                    </#if>
                        <div class="order-remarks hide" id="orderRemarkItem" style="display: block;">
                            <div class="remark-tit">添加订单备注</div>
                            <div id="remarkId" style="margin-bottom:7px">
                                <div class="form remark-cont">
                                    <textarea style="width: 300px;height: 60px;" id="message_left" name="customerRemark"
                                              size="15" class="itxt itxt01" placeholder="限45个字"
                                              onblur="if(this.value==''||this.value=='限45个字'){this.value='限45个字';this.style.color='#cccccc'}"
                                              onfocus="if(this.value=='限45个字') {this.value='';};this.style.color='#000000';"></textarea>
                                    <span class="ftx-03 ml10">&nbsp;&nbsp;提示：请勿填写有关支付、收货、发票方面的信息</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!--step-cont-->
                    <div class="step-tit clearfix">
                        <h3>发票信息</h3>
                    </div>
                    <div class="step-content">
                        <div id="part-inv" class="invoice-cont">
                            <span class="mr10 ch_invoiceType"> 不需要发票 &nbsp; </span>
                            <input type="hidden" name="ch_voinceType" class="ch_invoceTypeValue" value="0">
                            <span class="mr10 tr_invoiceTitleView" style="display:none"> 个人&nbsp; </span>
                            <input type="hidden" name="chInvoiceTitle" class="ch_invoceTitleValue" value="">
                            <span class="mr10 invoiceContentMing" style="display:none"> 明细&nbsp; </span>
                            <input type="hidden" name="chInvoiceContent" class="ch_invoiceContentValue" value="">
                            <a href="javascript:void(0)" class="ftx-05 invoice-edit" onclick="dia(4)">修改</a>
                        </div>
                    </div>
                    <div class="coupon ml20">
                        <a class="part_open" href="javascript:;">使用优惠券抵消部分金额</a>

                        <div class="coupon_cont" style="display:none;">
                            <h4>可用优惠券<span class="red cpl">(0)</span></h4>

                            <div class="coupon_choice">
                                <input type="hidden" value="" id="codeNo" name="codeNo"/>
                            <#if cartMap??&&cartMap.couponlist??&&(cartMap.couponlist?size>0)>
                                <input type="hidden" value=${cartMap.couponlist?size} id="cpLength">

                                <p><input type="radio" name="couponNo" value="" onclick="couponChange(this,0);"/><label>不使用优惠劵</label>
                                </p>
                                <#list cartMap.couponlist as coupon>

                                    <#if coupon.couponRulesType=='1'>
                                        <p><input type="radio" name="couponNo" value="${coupon.codeNo}"
                                                  onclick="couponChange(this,${coupon.couponStraightDown.downPrice});"/><label>${coupon.couponName}</label>
                                        </p>
                                    </#if>
                                    <#if coupon.couponRulesType=='2'>
                                    <#--<#if (payPrice?number>=coupon.couponFullReduction.fullPrice?number) >-->
                                        <p><input type="radio" name="couponNo" value="${coupon.codeNo}"
                                                  onclick="couponChange(this,${coupon.couponFullReduction.reductionPrice});"/><label>${coupon.couponName}</label>
                                        </p>
                                    <#--</#if>-->
                                    </#if>
                                </#list>
                            <#else>
                                <p class="coupon_empty light_9">
                                    您账户中没有可使用的优惠券。
                                    <a href="${basePath}/help/114" target="_blank"  class="ml10 red">[了解优惠券使用规则]</a>
                                </p>
                            </#if>

                            </div>
                            <div class="entity" id="inputCoupon">
                                <span>有优惠券券码？</span>
                                <a class="open_link red" href="javascript:;">[点此输入优惠券券码]</a>

                                <div class="mt10" style="display:none;">
                                    <input type="text" class="text fl" id="useCoupon" style="width: 200px"
                                           maxlength="50"/>
                                    <input type="button" class="common_btn" value="使用" onclick="checkCoupon()"
                                           style="width: 60px; height: 25px; display:inline-block; *display:inline; *zoom:1;"/>
                                    <input type="button" class="common_btn" value="清空" onclick="clearCoupon()"
                                           style="width: 60px; height: 25px; display:inline-block; *display:inline; *zoom:1;"/>
                                    <span id="errorTips" style="color:red"></span>

                                    <div class="cb"></div>
                                </div>
                            </div>
                            <div class="coupon_total">
                                <p>共使用了<span class="red us">0</span>张优惠券&nbsp;&nbsp;可以优惠<span class="red up">0.00</span>元
                                </p>
                            </div>
                        </div>
                    </div>
                    <!-- /coupon -->
                    <div class="remark">
                        <a class="part_open" href="javascript:void(0)">使用会员积分抵消部分金额</a>

                        <div class="money_card_cont" style="display: none; margin-left:20px;">
                            <div class="beans">
                                <!-- 未使用过京豆 -->
                            <#if cartMap??&&cartMap.customerPoint?? && cartMap.pointSet?? >
                                <div class="bs-t">
                                    <span>本次使用</span>
                                    <input id="amount" style="IME-MODE: disabled; WIDTH: 60px; HEIGHT: 15px"
                                           onkeyup="this.value=this.value.replace(/\D/g,'')"
                                           onafterpaste="this.value=this.value.replace(/\D/g,'')"
                                           maxlength="10" size="14" name="amount" fmax="40" type="text"/>
                                    <span>积分用于兑换</span>
                                    <input type="button" id="shiyong" value="使用"
                                           style="width: 60px; height: 25px; background-color:#B87070"
                                           onclick="jifen()" class="btn-add btn-1">

                                    <input type="button" id="shiyong" value="清零"
                                           style="width: 60px; height: 25px; background-color:#B87070"
                                           onclick="changeScre()" class="btn-add btn-1">
                                    <span id="tishi" style="color:red"></span>
                                    <input type="hidden" id="customerPoint" value="${cartMap.customerPoint}"/>
                                    <input type="hidden" id="pointSet" name="pointSet" value="${cartMap.pointSet}"/>
                                <#--<input type="hidden" id="zongjiage" name="zongjiage" value=""/>-->
                                <#--<input type="hidden" id="duihuanjifen" name="duihuanjifen" value=""/>-->
                                    <input type="hidden" id="jifen1" value="${cartMap.customerPoint}"/>
                                    <input type="hidden" id="jifen2" value="0"/>
                                    <input type="hidden" id="bosssumPrice"/>

                                    <input type="hidden" id="bossNum" name="bossNum" value="0"/>
                                    <label class="error" for="userBeans">
                                        您本次最多可以使用<span
                                            style="font-size: 14px; text-decoration:underline;font-weight:bold;">
                                    ${cartMap.customerPoint}</span>积分</label>
                                </div>
                                <div class="bs-m">
                                    <!-- 使用过京豆 -->
									<span class="rest">您有积分<s id="muqianjifen"
                                                              style="font-size: 14px; text-decoration:underline;font-weight:bold;">
                                    ${(cartMap.customerPoint)?string('0')}</s>个，本次可用<b
                                            id="muqianjifen1">${(cartMap.customerPoint)?string('0')}</b>积分</span>,
                                    每10积分兑换人民币￥${cartMap.pointSet}元。
                                </div>
                            </#if>

                            </div>
                        </div>
                        <!--money_card_cont-->
                    </div>
                    <!-- /remark -->
                    <div class="order-summary clearfix" style="padding-right:10px;">
                        <div class="statistic fr">

                            <div class="list">

                                <span><font color="red">${(sumcount?number)?string("0")} </font>件商品，总商品金额：</span>
                                <em class="price" id="sumOldPrice">${(sumOldPrice?number)?string('0.00')}</em>
                            </div>
                            <div class="list">
                                <span>运费：</span>
                                <em class="price" id="ep"> ￥0.00</em>
                            </div>
                            <div class="list">
                                <span>优惠券：</span>
                                <em class="price" id="yh"> ￥0.00</em>
                                <input type="hidden" id="youhui">
                            </div>
                            <div class="list">
                                <span>返现金额：</span>
                                <em class="price" id="yhprice"></em>

                            </div>
                            <div class="list">
                                <span>会员折扣：</span>
                                <em class="price" id="discount">￥0.00</em>

                            </div>
                            <div class="list">
                                <span>积分兑换金额：</span>
                                <em class="price" id="jf"> ￥0.00</em>
                            </div>

                            <div class="list">
                                <span>应付总额：</span>
                                <em class="price" id="lastpay"></em>
                            </div>
                        </div>
                    </div>
                    <!--order-summary-->

                    <div class="trade-foot">
                        <div id="checkout-floatbar" class="group">
                            <div class="ui-ceilinglamp checkout-buttons ui-ceilinglamp-current">
                                <div class="sticky-placeholder hide" style="display: none;">
                                </div>
                                <div class="sticky-wrap">
                                    <div class="inner">
                                        <button type="button" class="checkout-submit btn-1" onclick="submitForm(this);">
                                            提交订单<b></b>
                                        </button>
                                        <input type="hidden" id="sumPrice"/><!--记录未使用优惠券和积分兑换之前的总价格-->

                                        <input type="hidden" id="payPrice"/>
                                        <span class="total">应付总额：<strong id="lastpays"></strong>
                                            <label class="noShowMoney hide" id="giftBuyHidePriceDiv">
                                                <input type="checkbox" id="giftBuyHidePrice" checked="">隐藏礼品价格
                                            </label>
                                        </span>
                                        <span id="checkCodeDiv"></span>

                                        <div class="checkout-submit-tip" id="changeAreaAndPrice" style="display: none;">
                                            由于价格可能发生变化，请核对后再提交订单
                                        </div>
                                        <div style="display:none" id="factoryShipCodShowDivBottom" class="dispatching">
                                            部分商品货到付款方式：先由京东配送“提货单”并收款，然后厂商发货。
                                        </div>
                                    </div>
                                    <span id="submit_message" style="display:none" class="submit-error"></span>

                                    <div class="submit-check-info" id="submit_check_info_message"
                                         style="display:none"></div>
                                </div>
                            </div>
                        </div>

                        <div class="consignee-foot">

                        </div>
                        <!--/ /widget/checkout-floatbar/checkout-floatbar.tpl -->
                    </div>
                </div>
                <!--jd_order_det-->
            </form>
        </div>
        <!--w990-->

    </div>
    <!--container-->
</div>
<!--font-family: arial;-->
<div class="mask"></div>
<div class="jd-dialog dia1 address_dia">
    <div class="jd-dialog-title">新增收货人信息<a href="javascript:void(0)" class="jd-dialog-close"
                                           onclick="cls(),setDefaultForm();">×</a></div>
    <div class="jd-dialog-con clearfix p10">
        <div class="form" id="consignee-form" name="consignee-form">
            <div class="item clearfix" id="name_div">
                <span class="label"><span style="color:red">*</span>&nbsp;收货人：</span>

                <div class="fl">
                    <input type="hidden" id="cust_address_default" name="isDefault" value="0"/>
                    <input type="hidden" class="save_update_add_id">
                    <input type="hidden" id="consignee_form_id" name="consigneeParam.id" value="">
                    <input type="hidden" id="consignee_type" name="consigneeParam.type" value="">
                    <input type="hidden" id="consignee_ceshi1" name="consignee_ceshi1" value="">
                    <input type="text" class="itxt save_add_name" id="consignee_name" onblur="checkAddressName();"
                           name="addressName" maxlength="20" value="" tabindex="1">
                    <span class="error-msg addressNameTip" id="name_div_error"></span>
                </div>
            </div>
            <div class="item clearfix" id="area_div">
                <span class="label"><span style="color:red">*</span>&nbsp;所在地区：</span>

                <div class="fl">
			<span id="span_area">
			  <span id="span_province"><select class="selt" name="infoProvince" id=infoProvince tabindex="2">
                  <option value="">请选择：</option>
              </select></span>
			   <span id="span_city"><select class="selt" name="infoCity" id=infoCity tabindex="3">
                   <option value="">请选择：</option>
               </select></span>
			   <span id="span_county"><select class="selt" name="infoCounty" id=infoCounty tabindex="4">
                   <option value="">请选择：</option>
               </select></span>
	        </span>
                </div>
            </div>
            <div class="item clearfix">
                <span class="label" id="address_div"><span style="color:red">*</span>&nbsp;详细地址：</span>

                <div class="fl">
                    <!--span id="areaNameTxt"></span-->
                    <input type="text" class="itxt itxt02 save_add_detail" name="addressDetail"
                           onblur="checkAddressDetail();" maxlength="50" onblur="check_Consignee('address_div')"
                           value="" tabindex="6">
                    <span class="error-msg addressDetailTip" id="address_div_error"></span>
                </div>
            </div>
            <div class="item clearfix" id="call_div">
                <span class="label"><span style="color:red">*</span>&nbsp;手机号码：</span>

                <div class="fl">
                    <input type="text" class="itxt save_add_mobile" onblur="checkAddressMobile();" name="addressMoblie"
                           onblur="check_Consignee('call_mobile_div')" onfocus="if(value == defaultValue){value='';}"
                           maxlength="11" value="" tabindex="7">
                </div>
                <div class="fl">
                    <span class="label">固定电话：</span>
                    <input type="text" class="itxt save_add_phone" onblur="checkAddressPhone();" size="30"
                           name="addressPhone" onblur="check_Consignee('call_phone_div')"
                           onfocus="if(value == defaultValue){value='';}" maxlength="20" value="" tabindex="8">
                </div>
                <span class="error-msg addressPhoneTip" id="call_div_error"></span>
            </div>

            <div class="item clearfix" id="addressZip_div">
                <span class="label">邮政编码：</span>

                <div class="fl">
                    <input type="text" class="itxt save_add_post" name="addressZip" maxlength="50"
                           onblur="checkAddressPost();" value="" onfocus="if(value == defaultValue){value='';}"
                           tabindex="9">
                    <span class="error-msg addPostTips" id="addressZip_div_error"></span>
                </div>
            </div>
            <div class="item clearfix">
                <span class="label">&nbsp;</span>

                <div class="fl">
                    <a href="javascript:void(0)" class="btn-9 save_address"><span
                            id="saveConsigneeTitleDiv">保存收货人信息</span></a>

                    <div class="loading loading-1" style="display:none"><b></b>正在提交信息，请等待！</div>
                </div>
                <div style="display:none"><input id="consignee_form_reset" name="" type="reset"></div>
            </div>
        </div>
    </div>
    <!--jd-dialog-con-->
</div>
<!--jd-dialog-->

<div class="jd-dialog dia2 time_dia">
    <div class="jd-dialog-title">配送时间<a href="javascript:void(0)" class="jd-dialog-close" onclick="cls()">×</a></div>
    <div class="jd-dialog-con clearfix p10">
        <div class="date-thickbox" id="delivery-tab-311">
            <div class="tab-nav">
                <ul class="clearfix">
                    <li class="tab-nav-item tab-item-selected" id="li_311_id" onclick="doSwith311Tab('311')"> 指定时间
                        <b> </b></li>
                    <li class="tab-nav-item hide" id="li_411_id" onclick="doSwith311Tab('411')"> 极速达 <b> </b></li>

                </ul>
            </div>
            <div class="tab-con" id="tab_311_div">
                <div class="date-delivery" id="date-delivery1">
                    <div class="inner">
                        <dl class="th">
                            <dt>时间段</dt>
                            <dd class="date"><span row="-1" col="0">4-27<br>今天</span> <span row="-1" col="1">4-28<br>周二</span>
                                <span row="-1" col="2">4-29<br>周三</span> <span row="-1" col="3">4-30<br>周四</span> <span
                                        row="-1" col="4">5-1<br>周五</span> <span row="-1" col="5">5-2<br>周六</span> <span
                                        row="-1" col="6">5-3<br>周日</span> <span class="last" row="-1" col="7">5-4<br>周一</span>
                            </dd>
                            <dd class="time"><span row="0" col="-1">09:00-15:00</span> <span class="last" row="1"
                                                                                             col="-1">15:00-19:00</span>
                            </dd>
                        </dl>
                        <div class="data">
                            <ul>
                                <li class="checkbox disabled" data-status="0" row="0" col="0"></li>
                                <li class="checkbox disabled" data-status="0" row="1" col="0"></li>
                            </ul>
                            <ul>
                                <li class="checkbox selected" data-status="1" row="0" col="1"
                                    val="4-28 (周二) 09:00-15:00" day="2015-4-28" range="09:00-15:00"
                                    date-range="{&quot;1&quot;:1,&quot;35&quot;:1,&quot;30&quot;:1}">已选
                                </li>
                                <li class="checkbox last" data-status="1" row="1" col="1" val="4-28 (周二) 15:00-19:00"
                                    day="2015-4-28" range="15:00-19:00"
                                    date-range="{&quot;1&quot;:1,&quot;35&quot;:2,&quot;30&quot;:1}">可选
                                </li>
                            </ul>
                            <ul>
                                <li class="checkbox" data-status="1" row="0" col="2" val="4-29 (周三) 09:00-15:00"
                                    day="2015-4-29" range="09:00-15:00"
                                    date-range="{&quot;1&quot;:1,&quot;35&quot;:1,&quot;30&quot;:1}">可选
                                </li>
                                <li class="checkbox last" data-status="1" row="1" col="2" val="4-29 (周三) 15:00-19:00"
                                    day="2015-4-29" range="15:00-19:00"
                                    date-range="{&quot;1&quot;:1,&quot;35&quot;:2,&quot;30&quot;:1}">可选
                                </li>
                            </ul>
                            <ul>
                                <li class="checkbox" data-status="1" row="0" col="3" val="4-30 (周四) 09:00-15:00"
                                    day="2015-4-30" range="09:00-15:00"
                                    date-range="{&quot;1&quot;:1,&quot;35&quot;:1,&quot;30&quot;:1}">可选
                                </li>
                                <li class="checkbox last" data-status="1" row="1" col="3" val="4-30 (周四) 15:00-19:00"
                                    day="2015-4-30" range="15:00-19:00"
                                    date-range="{&quot;1&quot;:1,&quot;35&quot;:2,&quot;30&quot;:1}">可选
                                </li>
                            </ul>
                            <ul>
                                <li class="checkbox" data-status="1" row="0" col="4" val="5-1 (周五) 09:00-15:00"
                                    day="2015-5-1" range="09:00-15:00"
                                    date-range="{&quot;1&quot;:1,&quot;35&quot;:1,&quot;30&quot;:1}">可选
                                </li>
                                <li class="checkbox last" data-status="1" row="1" col="4" val="5-1 (周五) 15:00-19:00"
                                    day="2015-5-1" range="15:00-19:00"
                                    date-range="{&quot;1&quot;:1,&quot;35&quot;:2,&quot;30&quot;:1}">可选
                                </li>
                            </ul>
                            <ul>
                                <li class="checkbox" data-status="1" row="0" col="5" val="5-2 (周六) 09:00-15:00"
                                    day="2015-5-2" range="09:00-15:00"
                                    date-range="{&quot;1&quot;:1,&quot;35&quot;:1,&quot;30&quot;:1}">可选
                                </li>
                                <li class="checkbox last" data-status="1" row="1" col="5" val="5-2 (周六) 15:00-19:00"
                                    day="2015-5-2" range="15:00-19:00"
                                    date-range="{&quot;1&quot;:1,&quot;35&quot;:2,&quot;30&quot;:1}">可选
                                </li>
                            </ul>
                            <ul>
                                <li class="checkbox" data-status="1" row="0" col="6" val="5-3 (周日) 09:00-15:00"
                                    day="2015-5-3" range="09:00-15:00"
                                    date-range="{&quot;1&quot;:1,&quot;35&quot;:1,&quot;30&quot;:1}">可选
                                </li>
                                <li class="checkbox last" data-status="1" row="1" col="6" val="5-3 (周日) 15:00-19:00"
                                    day="2015-5-3" range="15:00-19:00"
                                    date-range="{&quot;1&quot;:1,&quot;35&quot;:2,&quot;30&quot;:1}">可选
                                </li>
                            </ul>
                            <ul class="last">
                                <li class="checkbox" data-status="1" row="0" col="7" val="5-4 (周一) 09:00-15:00"
                                    day="2015-5-4" range="09:00-15:00"
                                    date-range="{&quot;1&quot;:1,&quot;35&quot;:1,&quot;30&quot;:1}">可选
                                </li>
                                <li class="checkbox last" data-status="1" row="1" col="7" val="5-4 (周一) 15:00-19:00"
                                    day="2015-5-4" range="15:00-19:00"
                                    date-range="{&quot;1&quot;:1,&quot;35&quot;:2,&quot;30&quot;:1}">可选
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="ftx-03 mt20">
                    温馨提示：我们会努力按照您指定的时间配送，但因天气、交通等各类因素影响，您的订单有可能会有延误现象！
                </div>
                <div class="op-btns mt10 ac"><a id="timeSave311" href="javascript:void(0);" class="btn-9"> 保存 </a> <a
                        href="javascript:void(0)" class="btn-9 ml10"> 取消 </a></div>
            </div>
            <div class="tab-con hide" id="tab_411_div">
                <div> 下单后或支付成功后3小时送达，运费 <span class="ftx-01"> 49 </span> 元</div>
                <div class="ftx-03 mt20" id="message_show_411">
                    温馨提示：我们会努力按照您指定的时间配送，但因天气、交通等各类因素影响，您的订单有可能会有延误现象！
                </div>
                <div class="op-btns mt10 ac"><a id="timeSave411" class="btn-9"> 保存 </a> <a href="javascript:void(0);"
                                                                                           class="btn-9 ml10"> 取消 </a>
                </div>
            </div>
        </div>
    </div>
    <!--jd-dialog-con-->
</div>

<div class="jd-dialog dia4 ticket_dia">
    <div class="jd-dialog-title">发票信息<a href="javascript:void(0)" class="jd-dialog-close" onclick="cls()">×</a></div>
    <div class="jd-dialog-con clearfix p10">
        <div class="invoice-thickbox" id="invoice-tab">
            <div class="tab-nav clearfix">
                <ul class="clearfix">
                    <li id="click_1" class="tab-nav-item invoiceType  tab-item-selected" name="invoiceType" value="0">
                        不需要发票<b></b></li>
                    <li id="click_2" class="tab-nav-item " value="1" name="invoiceType">普通发票<b></b></li>
                </ul>
            </div>
            <div id="fapiao" class="form" style="display:none">
                <div class="item">
                    <span class="label">发票抬头：</span>

                    <div class="fl">
                        <div class="invoice-list invoice-tit-list" id="invoice-tit-list">
                            <div class="invoice-item invoice-item-selected" style="cursor:pointer">
                                <input type="hidden" id="commonInvoiceSize">

                                <div id="invoice-1" style="cursor:pointer">
                                    <span class="hide"><input type="hidden" value="4"></span>
										<span class="fore2" id="" name="usualInvoiceList" value="1100">
										<input type="text" style="cursor:pointer" class="itxt " data-r="个人" value="个人"
                                               readonly="true"><b></b>
										</span>
                                </div>
                            </div>

                            <div id="save-invoice" class="invoice-item hide new_add">
                                <div class="add-invoice-tit">
                                    <input type="text" name="invoiceTitle" class="itxt itxt04 invoice_title"
                                           placeholder="新增单位发票抬头" autocomplete="off">

                                    <div class="btns">
                                        <a href="javascript:void(0)" class="ftx-05 update-tit hide">编辑</a>
                                        <a href="javascript:void(0)" class="ftx-05 save-tit">保存</a>&nbsp;
                                    <#--<a href="#none" class="ftx-05 delete-tit hide">删除</a>-->
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" name="chInvoiceTitle" class="ch_invoceTitleValue" value=""/>
                        </div>
                        <div id="add-invoice" class="add-invoice"><a href="javascript:void(0)"
                                                                     class="ftx-05 add-invoice-btn"
                                                                     onclick="add_save()">新增单位发票</a></div>

                    </div>
                </div>
            </div>
            <div class="tab-box">
                <div class="tab-con ui-switchable-panel-selected">
                    <div class="form">
                        <div class="item invoice_con" style="display:none">
                            <span class="label">发票内容：</span>

                            <div class="fl">
                                <div class="invoice-list">
                                    <ul id="electro_book_content_radio">
                                        <li class="invoice-item invoice-item-selected" id="electro-invoice-content-1"
                                            name="invoiceContent" value="1" style="cursor:pointer">
                                            明细<b></b>
                                        </li>
                                    <#--<li class="invoice-item" id="electro-invoice-content-2" name="invoiceContent"
                                        value="2" style="cursor:pointer">
                                        办公用品<b></b>
                                    </li>
                                    <li class="invoice-item" id="electro-invoice-content-3" name="invoiceContent"
                                        value="3" style="cursor:pointer">
                                        电脑配件<b></b>
                                    </li>
                                    <li class="invoice-item" id="electro-invoice-content-19" name="invoiceContent"
                                        value="19" style="cursor:pointer">
                                        耗材<b></b>
                                    </li>-->
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <div class="item">

                            <div class="fl">
                                <div class="invoice-list">
                                    <ul>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <div class="item">
                            <span class="label">&nbsp;</span>

                            <div class="fl mt30">
                                <div class="op-btns">
                                    <a href="javascript:void(0)" class="btn-9 save_invoice">保存发票信息</a>
                                    <a href="javascript:void(0)" class="btn-9 ml10" onclick="quxiao()">取消</a>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>
    </div>
    <!--jd-dialog-con-->
</div>
<!--jd-dialog-->
<div class="jd-dialog ok-pay dia5">
    <div class="jd-dialog-title">请确认支付方式<a href="javascript:void(0)" class="jd-dialog-close" onclick="cls()">×</a></div>
    <div class="jd-dialog-con p10">
        <div class="payment-dialog">
            <div class="dialog-item dialog-item-activate">
                <div class="dialog-item-tit">以下商品<strong class="ftx-02">支持货到付款</strong></div>
                <div class="dialog-item-inner">
                    <div class="dialog-item-list">
                        <div class="dialog-goods">
                            <ul>
                                <li>
                                    <div class="p-img">
                                        <img src="http://img10.360buyimg.com/n4/jfs/t1054/115/424728135/429902/93b957f5/55225adfN7f79107d.jpg"
                                             title="静哲2015春夏季韩版学院风休闲可爱牛仔连衣裙女j8877 图片色 M"
                                             alt="静哲2015春夏季韩版学院风休闲可爱牛仔连衣裙女j8877 图片色 M">
                                    </div>
                                </li>
                                <li>
                                    <div class="p-img">
                                        <img src="http://img10.360buyimg.com/n4/jfs/t568/356/554503580/57036/5d2a2e84/5472e503Nd065333b.jpg"
                                             title="水之密语（AQUAIR） 凝润水护 洗发露 600ml（资生堂授权正品）"
                                             alt="水之密语（AQUAIR） 凝润水护 洗发露 600ml（资生堂授权正品）">
                                    </div>
                                </li>
                                <li>
                                    <div class="p-img">
                                        <img src="http://img10.360buyimg.com/n4/jfs/t724/29/1288837885/130475/5d3694d1/552e3dabNae909bdc.jpg"
                                             title="创维酷开（coocaa）A43 43英寸 智能酷开系统高清平板液晶电视(银色)"
                                             alt="创维酷开（coocaa）A43 43英寸 智能酷开系统高清平板液晶电视(银色)">
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="dialog-item dialog-item-deactivate">
                <div class="dialog-item-tit">以下商品<strong class="ftx-01">不支持货到付款，将使用在线支付</strong></div>
                <div class="dialog-item-inner">
                    <div class="dialog-item-list">
                        <div class="dialog-goods">
                            <ul>
                                <li>
                                    <div class="p-img">
                                        <img src="http://img10.360buyimg.com/n4/jfs/t1177/164/677013689/83117/2004a8e9/55385071N01ce83bc.jpg"
                                             title="康夫（Kangfu）KF8894 专业电吹风机冷热风大功率2200W吹风筒理发店专用"
                                             alt="康夫（Kangfu）KF8894 专业电吹风机冷热风大功率2200W吹风筒理发店专用">
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="op-btns ac">
                <a href="javascript:save_Pay(1);closeDialog();" class="btn-9">确定</a>
                <a href="javascript:closeDialog();" class="btn-9 ml10">取消</a>
            </div>
        </div>

    </div>
    <!--jd-dialog-con-->
</div>
<!--jd-dialog-->

<div class="jd-dialog tip_dia dia6">
    <input type="hidden" id="addressflag">

    <div class="dia_tit clearfix">
        <h4 class="fl">提示</h4>
        <a class="dia_close fr" href="javascript:void(0)" onclick="cls()"></a>
    </div>
    <!--/dia_tit-->
    <div class="dia_tip">
        <div class="dia_intro no_tc pt30 pl30">
            <img class="vm mr10" id="f_img" alt="" src="${basePath}/images/mod_war.png"/>
            <span id="con_00">修改成功！</span>
        </div>
        <div class="dia_ops mt20 tc">
            <a class="go_pay" id="go_pay_00" href="javascript:cls();" style='margin:auto'>确定</a>
        </div>
        <!--/dia_ops-->
    </div>
    <!--/dia_cont-->
</div>

<div class="jd-dialog tip_dia dia10">
    <input type="hidden" id="addressflag">

    <div class="dia_tit clearfix">
        <h4 class="fl">提示</h4>
        <a class="dia_close fr" href="javascript:void(0)" onclick="closeAddresswindow()"></a>
    </div>
    <!--/dia_tit-->
    <div class="dia_tip">
        <div class="dia_intro no_tc pt30 pl30">
            <img class="vm mr10" id="f_img" alt="" src="${basePath}/images/mod_war.png"/>
            <span id="con_flag">修改成功！</span>
        </div>
        <div class="dia_ops mt20 tc">
            <a class="go_pay" onclick="closeAddresswindow()" style='margin:auto'>确定</a>
        </div>
        <!--/dia_ops-->
    </div>
    <!--/dia_cont-->
</div>
<!--/dialog-->
<div class="jd-dialog tip_dia dia7">
    <div class="dia_tit clearfix">
        <input type="hidden" id="deladdress"/>
        <h4 class="fl">提示</h4>
        <a class="dia_close fr" href="javascript:void(0)" onclick="cls()"></a>
    </div>
    <!--/dia_tit-->
    <div class="dia_tip">
        <div class="dia_intro no_tc pt30 pl30">
            <img class="vm mr10" id="f_img" alt="" src="${basePath}/images/mod_war.png"/>
            <span id="con_01">确定删除？</span>
        </div>
        <div class="dia_ops mt20 tc">
            <a class="go_pay" id="go_pay_01" onclick="delAddress()" href="javascript:cls();">确定</a>
            <a class="go_pay" href="javascript:cls();">取消</a>
        </div>
        <!--/dia_ops-->
    </div>
    <!--/dia_cont-->
</div>

<#include "../index/newbottom.ftl" />

<script type="text/javascript" src="${basePath}/js/dialog-min.js"></script>
<script type="text/javascript" src="${basePath}/js/order/loadAddress.js"></script>
<script type="text/javascript" src="${basePath}/js/order/newSuborder.js"></script>
<script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
<script type="text/javascript" src="${basePath}/index_two/js/index.js"></script>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript">
    $(function () {
        loadProvice();
        if ($("#cpLength").val() > 0) {
            $(".cpl").html("(" + $("#cpLength").val() + ")");
        }
        //是否需要发票
        $("li[name=invoiceType]").click(function () {
            if ($(this).val() == 0) {
                $(".invoice_con").hide();
                $("#fapiao").hide();
            } else {
                $(".invoice_con").show();
                $("#fapiao").show();
            }
        });


        $(".qmark-tip").mouseover(function () {
            var a = $(this).parents("li").width();
            a = a - parseInt($(this).css("right"));
            var x = a;
            var y = $(this).css("top");
            x = parseInt(x) - 35;
            y = parseInt(y) + 21;
            var dd = $(this).attr("data-tips");
            $(this).after(
                    "<div class='online_tips'><div class='pr sanjiao'><span><img src='${basePath}/images/sanjiao.png'/></span>" + dd + "</div></div>"
            );
            $(".online_tips").css("left", x);
            $(".online_tips").css("top", y);
        });


        $('.part_open').click(function () {

            if ($(this).attr('class').indexOf('close') < 0) {
                $(this).addClass('part_close');
                $(this).next().show();
            }
            else {
                $(this).removeClass('part_close');
                $(this).next().hide();
            }
        });

        $('.open_link').click(function () {
            $(this).next().toggle();
        });


        //商品详情导航条
        if ($(".checkout-buttons").length > 0) {
            var a = $(".coupon_cont").height() + $(".money_card_cont").height();
            var b = 700;
            var _y = $("#checkout-floatbar").offset().top;

            function onScroll(e) {
                if ($(document.body).scrollTop() + b >= _y || $(document.documentElement).scrollTop() + b >= _y || window.scrollY + b >= _y) {
                    $(".checkout-buttons").removeClass("ui-ceilinglamp-current");
                } else {
                    $(".checkout-buttons").addClass("ui-ceilinglamp-current");
                }
                ;
            };
            $(window).scroll(function () {
                onScroll();
            });
        }

        $(".bossitem ,.mode-tab-item ").mouseleave(function () {
            $(".online_tips").remove();
        });
        //第三方
        $(".thirditem ,.mode-tab-item ").mouseleave(function () {
            $(".online_tips").remove();
        });

        //$('#fapiao').hide();
        $('#fff').focus();
        $('.invoice-list .invoice-item').bind('click', function () {
            $(this).addClass('invoice-item-selected').siblings().removeClass('invoice-item-selected');
        });
        $(".invoice-thickbox .tab-nav-item").bind('click', function () {
            $(this).addClass('tab-item-selected').siblings().removeClass('tab-item-selected');
        });


    });

    //第一个地址设置为默认地址
    function setCustAdressDefault() {
        var liNum = $(".cust_allAddress").find("li").length;
        //alert(liNum);
        if (liNum > 0) {
            $("input[id='cust_address_default']").val("0");
        } else {
            $("input[id='cust_address_default']").val("1");
        }
    }
</script>
<script type="text/javascript">

    //确定选择的
    function doSaveDialogPickSite() {
        $("#deliveryPointId").val($(".site-item-selected").attr('pickid'));
        $("#selfpick_name").html($(".site-item-selected").attr('pickname'));
        cls();
    }
    //选择自提点
    function doSelectPicksite(thisElement) {
        if ($(thisElement).hasClass("site-item-disabled")) {
            return;
        }
        $(".site-item").removeClass("site-item-selected");
        $(thisElement).addClass("site-item-selected");
        $("#deliveryPointId").val($(thisElement).attr('pickid'));

    }
    function win() {
        var _wd = $(window).width();
        var _hd = $(window).height();
        $(".z-dialog").css("top", (_hd - $(".z-dialog").height()) / 2).css("left", (_wd - $(".z-dialog").width()) / 2);
        $(".ok-pay").css("top", (_hd - $(".ok-pay").height()) / 2).css("left", (_wd - $(".ok-pay").width()) / 2);
        $(".ticket_dia").css("top", (_hd - $(".ticket_dia").height()) / 2).css("left", (_wd - $(".ticket_dia").width()) / 2);
        $(".time_dia").css("top", (_hd - $(".time_dia").height()) / 2).css("left", (_wd - $(".time_dia").width()) / 2);
        $(".address_dia").css("top", (_hd - $(".address_dia").height()) / 2).css("left", (_wd - $(".address_dia").width()) / 2);
        $(".tip_dia").css("top", (_hd - $(".tip_dia").height()) / 2).css("left", (_wd - $(".tip_dia").width()) / 2);
    }
    ;

    function dia(n) {
        win();
        $(".mask").fadeIn();
        $(".dia" + n).fadeIn();
    }
    ;

    function cls() {
        $(".dialog").fadeOut();
        $(".jd-dialog").fadeOut();
        $(".mask").fadeOut();
    }
    ;
    function add_save() {
        $(".add-invoice-btn").hide();
        $(".new_add").show();
    }

    $(".alink").click(function () {
        if ($(this).parent().next().is(':hidden')) {
            $(this).parent().next().show();
            $(".close").click(function () {
                $(this).parents(".tooltip-goods").parent().hide();
            })
        }
        else {
            $(this).parent().next().hide();
        }
    })


    function couponChange(obj, num) {
        if (num == 0) {
            $("#inputCoupon").show();
        } else {
            $("#inputCoupon").hide();
        }
        var discountPrice = $("#discount").val()    //会员折扣价
        var amount = $('#amount').val();                //需要兑换的积分
        var pointSet = $('#pointSet').val();          //积分兑换规则
        var zhekou = amount * pointSet / 10;                //积分兑换的折扣价格
        $("#yh").html("-￥" + num + ".00");
        $("#youhui").val(num);
        var sumPrice = $("#sumPrice").val();
        var sum = Subtr(Subtr(sumPrice, num), discountPrice);
        var lastpay = sum;
        //判断是否有积分兑换 如果有，总价格减去积分兑换的金额
        if (zhekou != 0) {
            lastpay = lastpay - zhekou;
        }

        $("#lastpay").html("￥" + lastpay);
        $("#lastpays").html("￥" + lastpay);
        if (num == 0) {
            $(".us").html('0');
        } else {
            $(".us").html('1');
        }
        $(".up").html(num);
        $("#codeNo").val($(obj).val());
        //订单总金额
        var sum = $('#lastpay').html();
        //金额小于1  设置总订单的价格为1分钱
        if (sum < 1) {
            $('#lastpay').html('0.01')
        }
    }

    var issubmit = 0;
    function submitForm(obj) {
        var regexp = new RegExp("[''\\[\\]<>?\\\\!]");
        if ($("#message_left").val().length > 45) {
            $("#con_00").html("客户留言长度限45个字符！");
            dia(6);
            return null;
        }
        if (regexp.test($("#message_left").val())) {
            $("#con_00").html("客户留言存在特殊字符！");
            dia(6);
            return null;
        }
        if ($(".curr").hasClass("fretype")) {
            if ($("#deliveryPointId").val() == 0) {
                if ($(this).attr("id") == 'ziti') {
                    $("#typeId").val('');
                    $("#con_00").html("自提点不能为空!");
                    dia(6);
                    return null;
                }

            }
        }

        if (!$(".consignee-item").hasClass("item-selected")) {
            $("#con_00").html("请选择收货人信息！");
            dia(6);
        } else {
            var thirdIds = document.getElementsByName("thirdIds");
            issubmit = 1;
            var amount = $('#amount').val();                //需要兑换的积分
            var sum = $('#lastpay').html();                 //订单总价格
            var pointSet = $('#pointSet').val();            //积分兑换规则
            //判断积分是否超额
            if (jifen() == false) {
                return;
            }
            $(obj).attr("disabled", "disabled");
            $("#sub_order").submit();
        }
    }
</script>
</@htmlBody>