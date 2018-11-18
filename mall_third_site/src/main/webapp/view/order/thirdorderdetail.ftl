<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城第三方后台-订单管理</title>
<#assign basePath=request.contextPath>
<link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
<link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
<link href="${basePath}/css/material.css" rel="stylesheet">
<link href="${basePath}/css/ripples.css" rel="stylesheet">
<link rel="stylesheet" href="${basePath}/css/style.css"/>
<script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript"src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
</head>
<body>
<#--引入头部-->
<#include "../index/indextop.ftl">
<div class="wp">
    <div class="ui-sidebar">
        <div class="sidebar-nav">
        <#import "../index/indexleft.ftl" as leftmenu>
            <@leftmenu.leftmenu '${basePath}' />
        </div>
    </div>

    <div class="app show_text" style="display: none;"">
        <div class="app-container">
            <div class="app-content">
                <div class="order-num">订单号：${order.orderCode}</div>

                <div class="order-ex-info" style="margin-bottom:20px;">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#ex01" aria-controls="ex01" data-toggle="tab">订单信息</a>
                        </li>
                        <li>
                            <a href="#ex02" aria-controls="ex02" data-toggle="tab">物流信息</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="ex01">
                            <div class="order-det">
                            <div class="od-cont">
                                <table>
                                    <tbody>
                                    <tr>
                                        <td><em>订单状态：</em>
                                        <#if order.orderStatus??>
                                            <#if order.orderStatus=="0">
                                                待付款
                                            <#elseif (order.orderStatus=="1" || order.orderStatus=="5" || order.orderStatus=="6") >
                                                未发货
                                            <#elseif order.orderStatus=="2">
                                                已发货
                                            <#elseif order.orderStatus=="3">
                                                已完成
                                            <#elseif order.orderStatus=="4">
                                                已取消
                                            <#elseif order.orderStatus=="14">
                                                退货审核
                                            <#elseif order.orderStatus=="8">
                                                同意退货
                                            <#elseif order.orderStatus=="10">
                                                待商家收货
                                            <#elseif order.orderStatus=="11">
                                                退单结束
                                            <#elseif order.orderStatus=="9">
                                                拒绝退货
                                            <#elseif order.orderStatus=="15">
                                                退款审核
                                            <#else>
                                                退货中
                                            </#if>
                                        </#if>
                                        </td>
                                        <td><em>订单原始金额：</em><#if order.orderOldPrice??>${order.orderOldPrice?string("0.00") }</#if></td>
                                        <td><em>发票类型：</em>
                                        <#if order.invoiceType??>
                                            普通发票
                                        <#else>
                                            无
                                        </#if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><em>支付方式：</em><#if order.payId?? && order.payId==1>在线支付<#else>货到付款</#if></td>
                                        <td><em>订单优惠金额：</em><#if order.orderPrePrice??>${order.orderPrePrice?string("0.00")}</#if></td>
                                        <td><em>发票抬头：</em><#if order.invoiceTitle??>${order.invoiceTitle }</#if></td>
                                    </tr>
                                    <tr>
                                        <td><em>下单时间：</em><#if order.createTime??>${order.createTime?string("yyyy-MM-dd HH:mm:ss") }</#if></td>
                                        <td><em>运&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费：</em><#if order.expressPrice??>${order.expressPrice?string("0.00")}</#if></td>
                                        <td><em>发票内容：</em><#if order.invoiceContent??>${order.invoiceContent }</#if></td>
                                    </tr>
                                    <tr>
                                        <td><em>支付日期：</em><#if order.payTime??>${order.payTime?string("yyyy-MM-dd HH:mm:ss")}</#if></td>
                                        <td><em>订单交易金额：</em><#if order.orderPrice??>${order.orderPrice?string("0.00")}</#if></td>
                                        <td><em>客户留言：</em><#if order.customerRemark??>${order.customerRemark }</#if></td>
                                    </tr>
                                    <tr>
                                        <td><em>优惠劵码：</em>
                                        <#if order.couponNo??>${order.couponNo }<#else>
                                            无
                                        </#if>
                                        </td>
                                        <td>
                                            <em>优惠券名称：</em>
                                            <#if couponName??>
                                                ${couponName}
                                            </#if>
                                        </td>

                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            </div>
                        </div>
                        <div class="tab-pane" id="ex02">
                            <div class="order-det">
                                <div class="od-cont">
                                <table>
                                    <tbody>
                                        <tr>
                                            <td><em>收货地址：</em>${order.shippingProvince !''}  ${order.shippingCity!'' }   ${order.shippingCounty !''}</td>
                                            <td><em>联系电话：</em><#if order.shippingPhone??>${order.shippingPhone } </#if></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td><em>详细地址：</em><#if order.shippingAddress??>${order.shippingAddress }</#if></td>
                                            <td><em>手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：</em><#if order.shippingMobile??>${order.shippingMobile }</#if></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td><em>收&nbsp;&nbsp;货&nbsp;&nbsp;人：</em>${order.shippingPerson!'' }</td>
                                            <td><em>运&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费：</em><#if order.expressPrice??>${order.expressPrice?string("0.00")}</#if></td>
                                            <td></td>
                                        </tr>
                                        <#if order.orderContainerRelations?size gt 0>
                                            <#list order.orderContainerRelations as relation>
                                                <tr>
                                                    <td><em>物流公司${relation_index+1}：</em> ${relation.expressName!''}</td>
                                                    <td><em>物流单号：</em> ${relation.expressNo!''}</td>
                                                </tr>
                                            </#list>
                                        </#if>
                                        <tr>
                                            <td><em>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编：</em><#if order.shippingPostcode??>${order.shippingPostcode}</#if></td>
                                            <td></td>
                                        </tr>
                                    </tbody>
                                </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <#if order.orderGoodsList??>
                    <#list order.orderGoodsList as orderGoods>
                        <table class="table">
                            <thead>
                            <tr>
                                <th width="400">商品名称</th>
                                <th>商品个数</th>
                                <th>商品单价</th>
                                <th>交易总格</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <span class="">

                                </span>
                            </tr>
                            <#--
                            <tr>
                                <#if orderGoods.marketing ??>
                                    <td colspan="5" style="text-align:left;">
                                        <span class="label label-danger">
                                            ${orderGoods.marketing.marketingName}
                                        </span>&nbsp;&nbsp;
                                        <#if orderGoods.marketing.codexType=="1">
                                            直降金额   <#if orderGoods.marketing.priceOffMarketing.offValue??>${orderGoods.marketing.priceOffMarketing.offValue}</#if>
                                        </#if>
                                        <#if orderGoods.marketing.codexType=="2">
                                            赠送商品
                                            <#if orderGoods.orderGoodsInfoGiftList??>
                                                <#list orderGoods.orderGoodsInfoGiftList as gifts>
                                                ${gifts.gift.giftName }
                                                </#list>
                                            </#if>
                                        </#if>
                                        <#if orderGoods.marketing.codexType=="3">
                                            赠送优惠劵
                                            <#if orderGoods.orderGoodsInfoCouponList??>
                                                <#list orderGoods.orderGoodsInfoCouponList as coupons>
                                                ${coupons.coupon.couponName }
                                                </#list>
                                            </#if>
                                        </#if>
                                        <#if orderGoods.marketing.codexType=="4">
                                            折扣   <#if orderGoods.marketing.discountMarketing.discountValue??>
                                            ${orderGoods.marketing.discountMarketing.discountValue}
                                        </#if>
                                        </#if>
                                        <#if orderGoods.marketing.codexType=="5">
                                            满${orderGoods.marketing.fullbuyReduceMarketing.fullPrice }减 ${orderGoods.marketing.fullbuyReduceMarketing.reducePrice }
                                        </#if>
                                        <#if orderGoods.marketing.codexType=="6">
                                            满${orderGoods.marketing.fullbuyPresentMarketing.fullPrice } 赠
                                            <#list orderGoods.marketing.giftList as gift>
                                            ${gift.giftName }
                                            </#list>
                                        </#if>
                                        <#if orderGoods.marketing.codexType=="7">
                                            满${orderGoods.marketing.fullbuyCouponMarketing.fullPrice } 赠
                                            <#list orderGoods.orderGoodsInfoCouponList as coupon>
                                            ${coupon.couponName }
                                            </#list>
                                        </#if>
                                        <#if orderGoods.marketing.codexType=="8">
                                            满${orderGoods.marketing.fullbuyDiscountMarketing.fullPrice } 折扣
                                            ${orderGoods.marketing.fullbuyDiscountMarketing.fullbuyDiscount }
                                        </#if>
                                    </td>
                                </tr>
                                </#if>
                            </tr>
                                <#if orderGoods.haveGiftStatus=="1">
                                <tr>
                                    <td colspan="2">
                                        商品促销的赠品
                                    </td>
                                    <td colspan="4">
                                        <#if orderGoods.orderGoodsInfoGiftList??>
                                            <#list orderGoods.orderGoodsInfoGiftList as giftlist>
                                                赠品编号：${giftlist.gift.giftName }
                                                赠品名称：${giftlist.gift.giftCode }
                                            </#list>
                                        </#if>
                                    </td>
                                </tr>
                                </#if>

                                <#if orderGoods.haveCouponStatus=="1">
                                <tr>
                                    <td colspan="2">
                                        商品赠送的优惠券
                                    </td>
                                    <td colspan="4">
                                        <#if orderGoods.orderGoodsInfoCouponList??>
                                            <#list orderGoods.orderGoodsInfoCouponList as couponlist>
                                                优惠券名称：${couponlist.coupon.couponName }
                                                优惠券卷码：${couponlist.couponNo }
                                            </#list>
                                        </#if>
                                    </td>
                                </tr>
                                </#if>
                                <tr>
                                    <#if order.orderMarketingList??>
                                        <table class="table">
                                            <tr>
                                                <td class="tc fb" width="30%">订单促销信息</td>
                                                <td claas="tl"></td>
                                            </tr>

                                            <#list order.orderMarketingList as orderMarketing>
                                                <#if orderMarketing.marketing??>
                                                    <tr>
                                                        <td class="tc" width="30%"><#if orderMarketing.marketing.marketingName??>${orderMarketing.marketing.marketingName }</#if></td>
                                                        <td claas="tl">
                                                            <#if orderMarketing.marketing.codexType=="1">
                                                                直降金额 ${orderMarketing.marketing.priceOffMarketing.offValue}
                                                            </#if>
                                                            <#if orderMarketing.marketing.codexType=="2">
                                                                赠送商品
                                                                <#if orderMarketing.marketing.giftList??>
                                                                    <#list orderMarketing.marketing.giftList as gift>
                                                                    ${gift.giftName }
                                                                    </#list>
                                                                </#if>
                                                            </#if>
                                                            <#if orderMarketing.marketing.codexType=="3">
                                                                赠送优惠券
                                                                <#if orderMarketing.marketing.couponList??>
                                                                    <#list orderMarketing.marketing.couponList as coupon>
                                                                    ${coupon.couponName }
                                                                    </#list>
                                                                </#if>
                                                            </#if>
                                                            <#if orderMarketing.marketing.codexType=="4">
                                                                折扣${orderMarketing.marketing.discountMarketing.discountValue }
                                                            </#if>
                                                            <#if orderMarketing.marketing.codexType=="5">
                                                                满${orderMarketing.marketing.fullbuyReduceMarketing.fullPrice }
                                                                减${orderMarketing.marketing.fullbuyReduceMarketing.reducePrice }
                                                            </#if>
                                                            <#if orderMarketing.marketing.codexType=="6">
                                                                满${orderMarketing.marketing.fullbuyPresentMarketing.fullPrice }
                                                                赠
                                                                <#if orderMarketing.marketing.giftList??>
                                                                    <#list orderMarketing.marketing.giftList as gift>
                                                                    ${gift.giftName }
                                                                    </#list>
                                                                </#if>
                                                            </#if>
                                                            <#if orderMarketing.marketing.codexType=="7">
                                                                满${orderMarketing.marketing.fullbuyCouponMarketing.fullPrice }
                                                                赠
                                                                <#if orderMarketing.marketing.couponList??>
                                                                    <#list orderMarketing.marketing.couponList as coupon>
                                                                    ${coupon.couponName }
                                                                    </#list>
                                                                </#if>
                                                            </#if>
                                                            <#if orderMarketing.marketing.codexType=="8">
                                                                满${orderMarketing.marketing.fullbuyDiscountMarketing.fullPrice }
                                                                折 ${orderMarketing.marketing.fullbuyDiscountMarketing.fullbuyDiscount }
                                                            </#if>
                                                        </td>
                                                    </tr>
                                                </#if>
                                            </#list>
                                            <#if order.orderMarketingList??>
                                                <tr>
                                                    <td class="tc fb" width="30%">订单促销赠送赠品</td>
                                                    <td claas="tl"></td>
                                                </tr>
                                                <#list order.orderMarketingList as orderMarketing>
                                                    <#if orderMarketing.orderGiftList??>
                                                        <#list orderMarketing.orderGiftList as giftlist >
                                                            <tr>
                                                                <td class="tc" width="30%">赠品编号:${giftlist.gift.giftCode }</td>
                                                                <td claas="tl">赠品名称:${giftlist.gift.giftName }</td>
                                                            </tr>
                                                        </#list>
                                                    <#else>
                                                        <td class="tc fb" width="30%"></td>
                                                        <td claas="tl">无赠品信息</td>
                                                    </#if>
                                                </#list>

                                                <tr>
                                                    <td class="tc fb" width="30%">订单促销送优惠券</td>
                                                    <td claas="tl"></td>
                                                </tr>
                                                <#list order.orderMarketingList as orderMarketing>
                                                    <#if orderMarketing.orderCouponList??>
                                                        <#list orderMarketing.orderCouponList as couponlist >
                                                            <tr>
                                                                <td class="tc" width="30%">优惠券卷码：${couponlist.couponNo }</td>
                                                                <td claas="tl">优惠劵名称:${couponlist.coupon.couponName }</td>
                                                            </tr>
                                                        </#list>
                                                    <#else>
                                                        <td class="tc fb" width="30%"></td>
                                                        <td claas="tl">无优惠劵信息</td>
                                                    </#if>
                                                </#list>
                                            </#if>
                                        </table>
                                    </#if>
                                </tr>
                                -->
                            <tr>
                                <td>
                                    <div class="goods-intro">
                                        <img alt="" class="gds-img" src="<#if orderGoods.goodsProductVo??>${orderGoods.goodsProductVo.goodsInfoImgId}<#else></#if>" />
                                        <div class="gds-info">
                                            <p class="gds-name"><#if orderGoods.goodsProductVo??>${orderGoods.goodsProductVo.goodsInfoName }<#else></#if></p>
                                        </div>
                                    </div>
                                </td>
                                <td>${orderGoods.goodsInfoNum}</td>
                                <td>${orderGoods.goodsInfoOldPrice?string("0.00")}</td>
                                <td>${orderGoods.goodsInfoSumPrice?string("0.00")}</td>
                            </tr>
                            </tbody>
                        </table>
                    </#list>
                </#if>
            </div>
        </div>
    </div>
</div>

<#--<div class="service-wrap">-->
    <#--<span class="service-close">×</span>-->
    <#--<a href="javascript:;" class="service-box">联系客服</a>-->
<#--</div>-->

<div class="back-to-top">
    <a href="javascript:;"><i></i>返回顶部</a>
</div>

<#--<div class="notice-center">-->
    <#--<div class="notice-center-ring"><i></i></div>-->
    <#--<div class="notice-center-wrapper">-->
        <#--<div class="nt-header">-->
            <#--<h3>系统通知（<span>1</span>）</h3>-->
            <#--<a href="javascript:;" class="nt-close">收起》</a>-->
        <#--</div>-->
        <#--<ul class="nt-content">-->
            <#--<li>-->
                <#--<div class="nt-item unread">-->
                    <#--<p>刘仙升于2015-04-08 15:41:23申请提现1.00元，已提现成功，请注意查询您的银行账户。</p>-->
                    <#--<a href="javascript:;">查看提现记录 》</a>-->
                <#--</div>-->
            <#--</li>-->
        <#--</ul>-->
        <#--<div class="nt-footer">-->
            <#--<a href="javascript:;" class="mark-read">全部标记已读</a>-->
            <#--<a href="javascript:;" class="nt-all">查看全部信息</a>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->

<#--<div class="page-help-btn">帮助</div>-->
<div class="page-help-container">
    <div class="page-help-content">
        <p style="color:#f00;">不知道从哪里开始？</p>
        <p>完成掌柜任务，简简单单开店铺！</p>
        <p>点击开始》》<a href="javascript:;">掌柜成长之旅</a></p>
    </div>
    <div class="page-help-operation">
        <a href="javascript:;" class="btn btn-primary btn-sm">进入帮助中心</a>
        <a href="javascript:;" class="btn btn-default btn-sm">建议反馈</a>
    </div>
</div>
<script>
    $(function(){
        $.material.init();
    });
    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);

</script>
</body>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
</html>
