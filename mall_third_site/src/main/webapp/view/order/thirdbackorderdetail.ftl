<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>商城第三方后台-订单管理</title>
<#assign basePath=request.contextPath>

<link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
<link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
<link rel="stylesheet" href="${basePath}/css/style.css"/>

<script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript"src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/angular.min.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
</head>
<style>
    .track-info{padding-left:300px;color:#afafaf}
    .track-info .track-date,.track-info i,.track-info .track-cont{display:inline-block;*display:inline;*zoom:1;vertical-align:middle;vertical-align:bottom}
    .track-info .track-date{width:160px;text-align:right}
    .track-info i{background:url(../images/track-bg.png) no-repeat left bottom;width:14px;height:40px;margin:0 20px}
    .track-info .current .track-date{color:#e64f25}
    .track-info .current .track-cont{color:#333}
    .track-info .current .track-cont em{color:#e64f25;font-style:normal}
    .track-info .latest-track i{background:url(../images/track-circle.png) no-repeat;width:14px;height:14px}
    .back-orders {padding:0 30px;color:#666;}
    .details-item {margin-bottom:20px;}
    .details-item .code-num {margin-right:100px;}
    .details-item h3, .details-item em {margin-bottom:20px;font-size:14px;font-weight:700;}
    .track-info p{margin:0 0 }
</style>

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
                <div class="order-num">退单号：${backorder.backOrderCode }</div>
                <div class="order-det">
                    <div class="od-tit"><h3>退单详情</h3></div>
                    <div class="od-cont">
                        <table>
                            <tbody>
                            <tr>
                                <td style="width: 30%"><em>退单单号：</em>${backorder.backOrderCode }</td>
                                <td style="width: 30%"><em>收货地址信息：</em>${backorder.order.shippingProvince!''}  ${backorder.order.shippingCity!''}   ${backorder.order.shippingCounty!''}</td>
                                <td ><em>退货原因：</em>
                                    <#if backorder.backReason??>
                                        <#if backorder.backReason=='1'>不想买了</#if>
                                        <#if backorder.backReason=='2'>收货人信息有误</#if>
                                        <#if backorder.backReason=='3'>商品损坏</#if>
                                        <#if backorder.backReason=='4'>其他</#if>
                                    </#if>
                                </td>
                            </tr>
                            <tr>
                                <td><em>退单时间：</em><#if backorder.backTime??>${backorder.backTime?string("yyyy-MM-dd HH:mm:ss") }<#else></#if></td>
                                <td><em>详细地址：</em><#if backorder.order.shippingAddress??>${backorder.order.shippingAddress!''}<#else></#if></td>
                                <td><em>上传凭证：</em>
                                    <#if backorder.applyCredentials??>
                                        <#if backorder.applyCredentials=='3'>
                                            没有任何凭证
                                        <#else>
                                            <#if imglist??>
                                                <#list imglist as imgs>
                                                   <#if imgs??>
                                                    <img src="${imgs}" alt="" style="width:30px;height:30px;"/>
                                                   </#if>
                                                </#list>
                                            </#if>
                                        </#if>
                                    </#if>
                                </td>
                            </tr>
                            <tr>
                                <td><em>订单状态：</em>
                                <#if backorder.backCheck??&&backorder.backCheck=="0">
                                    退货申请
                                </#if>
                                <#if backorder.backCheck??&&backorder.backCheck=="1">
                                    同意退货
                                </#if>
                                <#if backorder.backCheck??&&backorder.backCheck=="2">
                                    拒绝退货
                                </#if>
                                <#if backorder.backCheck??&&backorder.backCheck=="3">
                                    待收货
                                </#if>
                                <#if backorder.backCheck??&&backorder.backCheck=="4">
                                    订单结束
                                </#if>
                                <#if backorder.backCheck??&&backorder.backCheck=="6">
                                    审核退款
                                </#if>
                                <#if backorder.backCheck??&&backorder.backCheck=="7">
                                    拒绝退款
                                </#if>
                                <#if backorder.backCheck??&&backorder.backCheck=="9">
                                    待客户填写物流地址
                                </#if>
                                <#if backorder.backCheck??&&backorder.backCheck=="10">
                                    退款完成
                                </#if>
                                </td>
                                <td><em>收货人姓名：</em>${backorder.order.shippingPerson}</td>
                                <td><em>退单说明：</em>
                                   <#if backorder.backRemark??>${backorder.backRemark}</#if>
                                </td>
                            </tr>
                            <#if backOrderGeneral ??>
                                <tr>
                                    <td><em>退货物流名称:</em>
                                        ${backOrderGeneral.ogisticsName}
                                    </td>
                                    <td><em>退货物流单号:</em>
                                        ${backOrderGeneral.ogisticsNo}
                                    </td>
                                </tr>
                            </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th width="300">退单商品</th>
                            <th>商品个数</th>
                            <#--<th>原始价格</th>
                            <th>交易价格</th>-->
                            <th>退单总价</th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <#if backorder.orderGoodslistVo??>
                                        <#list backorder.orderGoodslistVo as orderGoods>
                                            <a href="javascript:;"><img alt="" width="30px" height="30px" src="${orderGoods.goodsInfoImgId!'' }" /></a>
                                        </#list>
                                    </#if>
                                </td>
                                <td>（<#if backorder.orderGoodslistVo??>${backorder.orderGoodslistVo?size}<#else></#if>件）</td>
                                <#--<td>¥${backorder.backPrice!''}</td>
                                <td>¥${backorder.backPrice!''}</td>-->
                                <td>¥${backorder.backPrice!''}</td>
                            </tr>
                        </tbody>
                        <tfoot><#--
                            <#if orderGoods.marketing ??>
                            <tr>
                                <td class="tc" colspan="2">促销信息</td>
                                <td class="tl" colspan="4"></td>
                            </tr>
                            <tr>
                                <td class="tc" colspan="2">${orderGoods.marketing.marketingName }</td>
                                <td class="tl" colspan="4">
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
                        --></tfoot>
                    </table>


            <#if backorder.orderMarketingList??>
                <table class="table">
                    <tr>
                        <td class="tc fb" width="30%">订单促销信息</td>
                        <td claas="tl"></td>
                    </tr>
                    <#if backorder.order.orderMarketingList??>
                        <#list backorder.order.orderMarketingList as orderMarketing>
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
                    </#if>
                    <#if backorder.order.orderMarketingList??>
                        <tr>
                            <td class="tc fb" width="30%">订单促销赠送赠品</td>
                            <td claas="tl"></td>
                        </tr>
                        <#list backorder.order.orderMarketingList as orderMarketing>
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
                        <#list backorder.order.orderMarketingList as orderMarketing>
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
            </div>
            <div>
                <div class="app-content">
                    <h3 style="font-size:14px;margin-left:10px;">跟踪信息</h3>
                    <div class="item-cont">
                        <div class="track-info" style="padding-left:300px;color: :#afafaf;">
                        <#list backOrderLogs as  backOrderLog>
                            <#if backOrderLog.backLogStatus??>
                                <#if backOrderLog.backLogStatus == '8'>
                                    <p>
                                        <span class="track-date">${backOrderLog.backLogTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                                        <i></i>
                                        <span class="track-cont">退款成功 (操作：商家)<#if backOrderLog.backRemark??>留言：${backOrderLog.backRemark}</#if></span>
                                    </p>
                                </#if>
                                <#if backOrderLog.backLogStatus == '6'>
                                    <p>
                                        <span class="track-date">${backOrderLog.backLogTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                                        <i></i>
                                        <span class="track-cont">收货失败 (操作：商家)<#if backOrderLog.backRemark??>留言：${backOrderLog.backRemark}</#if></span>
                                    </p>
                                </#if>
                                <#if backOrderLog.backLogStatus == '5'>
                                    <p>
                                        <span class="track-date">${backOrderLog.backLogTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                                        <i></i>
                                        <span class="track-cont">确认收货(操作：商家)<#if backOrderLog.backRemark??>留言：${backOrderLog.backRemark}</#if></span>
                                    </p>
                                </#if>
                                <#if backOrderLog.backLogStatus == '4'>
                                    <p>
                                        <span class="track-date">${backOrderLog.backLogTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                                        <i></i>
                                        <span class="track-cont">填写快递信息(操作：顾客)<#if backOrderLog.backRemark??>留言：${backOrderLog.backRemark}</#if></span>
                                    </p>
                                </#if>
                                <#if backOrderLog.backLogStatus == '3'>
                                    <p>
                                        <span class="track-date">${backOrderLog.backLogTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                                        <i></i>
                                        <span class="track-cont">申请审核不通过(操作：商家)<#if backOrderLog.backRemark??>留言：${backOrderLog.backRemark}</#if></span>
                                    </p>
                                </#if>
                                <#if backOrderLog.backLogStatus == '2'>
                                    <p>
                                        <span class="track-date">${backOrderLog.backLogTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                                        <i></i>
                                        <span class="track-cont">申请审核通过(操作：商家)<#if backOrderLog.backRemark??>留言：${backOrderLog.backRemark}</#if></span>
                                    </p>
                                </#if>
                                <#if backOrderLog.backLogStatus == '1'>
                                    <p class="latest-track">
                                        <span class="track-date">${backOrderLog.backLogTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                                        <i></i>
                                        <span class="track-cont">申请退单审核(操作：顾客)<#if backOrderLog.backRemark??>留言：${backOrderLog.backRemark}</#if></span>
                                    </p>
                                </#if>
                            </#if>
                        </#list>
                        </div>
                    </div>
                </div>
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
</body>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<script>
    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);
</script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
</html>
