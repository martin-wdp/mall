<#include "../include/common.ftl">
<@htmlHead title="领优惠券-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css" />
<link rel="stylesheet" href="${basePath}/css/coupon.css">
</@htmlHead>
<@htmlBody>
<#include "../index/newheader7.ftl">
<div class="content" id="couponContainer">
    <div class="coupon-header">
        <img src="${basePath}/images/coupon/coupon-header.png"/>
    </div>
    <div class="coupon-list">
        <div class="clearfix">
            <ul class="">
            <#list  couponLists as couponlist>
                <#assign cntadd=(couponlist.remainderCount+couponlist.receivedCount)>
                <#if (cntadd>couponlist.couponGetNo)>
                    <#assign cnt=couponlist.couponGetNo>
                <#else>
                    <#assign cnt=cntadd>
                </#if>
                <#if (couponlist.receivedCount>0)>
                <#list 1..couponlist.receivedCount as i>
                    <li class="coupon-item">
                        <div class="coupon-item-received-content"></div>
                        <#if couponlist.couponRulesType == "1">
                            <div class="coupon-item-reduc-money">
                                <span>${couponlist.couponStraightDown.downPrice!''}</span>
                            </div>
                            <div class="coupon-item-reduc-content">
                                购买对应商品时直接使用<br/>
                            </div>
                        <#elseif couponlist.couponRulesType == "2">
                            <div class="coupon-item-full-money">
                                <span>${couponlist.reductionPrice!''}</span>
                            </div>
                            <div class="coupon-item-reduc-content">
                                满<span>${couponlist.couponFullReduction.fullPrice}</span>元可用一张抵现金<br/>
                            </div>
                        </#if>
                        <div class="coupon-item-reduc-date reduc-date-1">
                            有效期至<span><#if couponlist.couponEndTime??>
                                                ${couponlist.couponEndTime?string("yyyy.MM.dd")}
                                            </#if></span>止
                        </div>
                        <div class="coupon-item-not-receive" onclick="receiveCoupon(this);" isReceive="0"
                             couponId="${couponlist.couponId}"></div>
                    </li>
                </#list>
                </#if>
                <#if ((cnt-couponlist.receivedCount)>0)>
                <#list 1..(cnt-couponlist.receivedCount) as j>
                    <li class="coupon-item">
                        <div class="coupon-item-received-content" style="display: none;"></div>
                        <#if couponlist.couponRulesType == "1">
                            <div class="coupon-item-reduc-money">
                                <span>${couponlist.couponStraightDown.downPrice!''}</span>
                            </div>
                            <div class="coupon-item-reduc-content">
                                购买对应商品时直接使用<br/>
                            </div>
                        <#elseif couponlist.couponRulesType == "2">
                            <div class="coupon-item-full-money">
                                <span>${couponlist.reductionPrice!''}</span>
                            </div>
                            <div class="coupon-item-reduc-content">
                                满<span>${couponlist.couponFullReduction.fullPrice}</span>元可用一张抵现金<br/>
                            </div>
                        </#if>
                        <div class="coupon-item-reduc-date reduc-date-1">
                            有效期至<span><#if couponlist.couponEndTime??>
                                                ${couponlist.couponEndTime?string("yyyy.MM.dd")}
                                            </#if></span>止
                        </div>
                        <div class="coupon-item-not-receive" onclick="receiveCoupon(this);" isReceive="0"
                             couponId="${couponlist.couponId}"></div>
                    </li>
                </#list>
                </#if>
            </#list>
            </ul>
        </div>
    </div>
</div>
<#include "../index/newbottom.ftl" />
<script type="text/javascript" src="${basePath}/js/jquery.slides.min.js"></script>
<script type="text/javascript" src="${basePath}/js/coupon/app.js"></script>
<script type="text/javascript">
    $(function () {
        setCouponContainerMinHeight();
    });
    /* *
     * 计算优惠券主体的最小高度，避免网站底部出现空白区域
     * 2015.11.12 wuyanbo add
     * */
    function setCouponContainerMinHeight() {
        var bodyHeight = $(window).height();//当前屏幕的可视区域高度
        var topHeight = 185;
        var bottomHeight = 450;
        var couponHeight = (bodyHeight - topHeight - bottomHeight - 20);
        if (couponHeight <= 350) {
            couponHeight = 350;
        }
        $("div[id='couponContainer']").css({"min-height": couponHeight + "px"});
    }
    function receiveCoupon(obj) {
        var isReceive = $(obj).attr("isReceive");
        var couponId = $(obj).attr("couponId");
        var url = "${basePath}/qpgetOffCouponAjax-" + couponId + ".html";
        $.ajax({
            url: url,
            async: false,
            success: function (data) {
                if (data == 1) {
                    //领取成功后，执行以下代码
                    $(obj).attr("isReceive", "1");
                    $(obj).siblings("div[class='coupon-item-received-content']").show();
                }
                if (data == 0) {
                    //没有操作权限 登录状态失效
                    var loginurl = "${basePath}/login.html";
                    window.location.href = loginurl;
                    return;
                }
                if (data == 2) {
                    //该优惠券已经领完 么有了
                    //TODO
                }
                if (data == 3) {
                    //用户已经领取过了自己可以领取的最大张数
                    //TODO
                }
            }
        });
    }
</script>
</@htmlBody>
