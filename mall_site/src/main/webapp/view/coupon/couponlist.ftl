<#include "../include/common.ftl">
<@htmlHead title="优惠券">
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css" />
<link rel="stylesheet" href="${basePath}/css/coupon/base.min.css">
<link rel="stylesheet" href="${basePath}/css/coupon.css">
</@htmlHead>
<@htmlBody>
<#--一引入头部 <#include "/index/topnew.ftl" />  -->
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
        <#include "../index/newtop8.ftl">
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

<#include "../customer/newtop.ftl"/>

<!--优惠券头部，爱用不用-->
<div class="coupon-header clearfix">
    <div class="ch-wp">
        <h1 class="logo"><a href="javascript:;"><img src="${basePath}/images/logo.jpg" alt=""></a></h1>
        <div class="coupon-intro"></div>
        <ul class="quan-nav clearfix">
            <li class="fore1"><a href="javascript:;"><i></i>我的优惠券</a></li>
            <li class="fore2"><a href="javascript:;"><i></i>我的关注品牌</a></li>
        </ul>
    </div>
</div>
<!--/优惠券头部，爱用不用-->

<div class="coupon-slides" id="coupon-slides">
    <a href="javascript:;" class="slide" style="background-image:url(${basePath}/images/banner_01.jpg)"></a>
    <a href="javascript:;" class="slide" style="background-image:url(${basePath}/images/banner_01.jpg)"></a>
    <a href="javascript:;" class="slide" style="background-image:url(${basePath}/images/banner_01.jpg)"></a>
</div>

<div class="coupon-content">
    <div class="coupon-recommend">
        <h2 class="cp-tit">热门推荐<span>RECOMMENDATIONS</span></h2>
        <ul class="clearfix">
            <li><a href="javascript:;"><img src="${basePath}/images/images_01.jpg" alt=""></a></li>
            <li><a href="javascript:;"><img src="${basePath}/images/images_02.jpg" alt=""></a></li>
            <li><a href="javascript:;"><img src="${basePath}/images/images_03.jpg" alt=""></a></li>
            <li><a href="javascript:;"><img src="${basePath}/images/images_04.jpg" alt=""></a></li>
        </ul>
    </div>

    <div class="brand-coupons">
        <h2 class="cp-tit">品牌优惠券专区<span>BRAND COUPONS</span></h2>
        <div class="coupons-filter">
            <div class="filter-item">
                <dl>
                    <dt>分类：</dt>
                    <dd>
                        <a href="javascript:;">全部</a>
                        <a class="active" href="javascript:;">男装、女装、内衣</a>
                        <a href="javascript:;">鞋靴、箱包</a>
                        <a href="javascript:;">首饰、手表</a>
                        <a href="javascript:;">运动、户外</a>
                        <a href="javascript:;">手机、配件</a>
                        <a href="javascript:;">家用电器</a>
                        <a href="javascript:;">电脑办公</a>
                        <a href="javascript:;">相机、影音</a>
                        <a href="javascript:;">化妆品</a>
                        <a href="javascript:;">酒饮、食品</a>
                        <a href="javascript:;">母婴、玩具</a>
                        <a href="javascript:;">居家生活</a>
                        <a href="javascript:;">图书音像</a>
                    </dd>
                </dl>
                <dl>
                    <dt>&nbsp;</dt>
                    <dd>
                        <a class="active" href="javascript:;">全部</a>
                        <a href="javascript:;">女装</a>
                        <a href="javascript:;">男装</a>
                        <a href="javascript:;">内衣</a>
                        <a href="javascript:;">眼镜配饰</a>
                        <a href="javascript:;">设计师</a>
                    </dd>
                </dl>
            </div>
            <div class="filter-item">
                <dl>
                    <dt>金额：</dt>
                    <dd>
                        <a href="javascript:;">全部</a>
                        <a href="javascript:;">0-20</a>
                        <a href="javascript:;">21-50</a>
                        <a href="javascript:;">51-100</a>
                        <a href="javascript:;">101-200</a>
                        <a href="javascript:;">200以上</a>
                        <div class="price-input">
                            <input type="text">&nbsp;-&nbsp;<input type="text">
                            <input type="button" value="确定">
                        </div>
                    </dd>
                </dl>
            </div>
        </div>
        <dl class="selected-sort clearfix">
            <dt>已选条件：</dt>
            <dd>
                <a href="javascript:;">男装、女装、内衣<i></i></a>
                <a href="javascript:;">51-100<i></i></a>
            </dd>
        </dl>

        <div class="filter-bar clearfix">
            <div class="fl">
                <a class="ftr-01 active" href="javascript:;">最优推荐<i></i></a>
                <a class="ftr-02" href="javascript:;">最大折扣<i></i></a>
            </div>
            <div class="fr">
                <div class="cp-search">
                    <input type="text">
                    <input type="button" value="搜索">
                </div>
            </div>
        </div>

        <div class="coupons-list clearfix">
            <div class="col cp-fore1">
                <#if couponLists??>
                    <#list couponLists as couponlist>
                    <div class="coupon-item">
                        <div class="item-logo">
                            <a href="javascript:;">
                                <img src="${couponlist.couponPic!''}" alt="" width="120" height="45">
                            </a>
                        </div>
                        <h3 class="item-name">${couponlist.couponName!''}</h3>
                        <a href="javascript:;" class="item-link">浏览活动&nbsp;></a>
                        <ul class="mt5">
                            <li class="item-quan">
                                <a href="javascript:;">
                                    <span class="item-amount">
                                        <i>&yen;</i>
                                        <#if (couponlist.couponRulesType)?? && couponlist.couponRulesType=='1'>
                                            <#if couponlist.couponStraightDown??>
                                                ${couponlist.couponStraightDown.downPrice}
                                            </#if>
                                        <#else >
                                            ${couponlist.reductionPrice!''}
                                        </#if>
                                    </span>
                                    <span class="item-detail" >
                                        <i>
                                            <#if (couponlist.couponRulesType)?? && couponlist.couponRulesType=='2'>
                                                <#if couponlist.couponFullReduction??>
                                                    满${couponlist.couponFullReduction.fullPrice!''}元使用
                                                </#if>
                                            <#else >
                                                不受限制
                                            </#if>
                                        </i>
                                        <em>
                                            <#if couponlist.couponEndTime??>
                                                ${couponlist.couponEndTime?string("yyyy.MM.dd")}
                                            </#if>
                                        前领取</em>
                                        <b>立即领取></b>
                                    </span>
                                </a>
                            </li>

                        </ul>
                    </div>
                    </#list>
                </#if>
                <#--<div class="coupon-item">
                    <div class="item-logo">
                        <a href="javascript:;">
                            <img src="./images/images_06.jpg" alt="" width="120" height="45">
                        </a>
                    </div>
                    <h3 class="item-name">大牌强势归来</h3>
                    <a href="javascript:;" class="item-link">浏览活动&nbsp;></a>
                    <ul class="mt5">
                        <li class="item-quan">
                            <a href="javascript:;">
                                <span class="item-amount"><i>&yen;</i>50</span>
                                    <span class="item-detail">
                                        <i>满399元可用</i>
                                        <em>2015.08.28前领取</em>
                                        <b>立即领取></b>
                                    </span>
                            </a>
                        </li>
                    </ul>
                </div>-->
            </div>

            <#--<div class="col cp-fore2">
                <div class="coupon-item">
                    <div class="item-logo">
                        <a href="javascript:;">
                            <img src="./images/images_07.jpg" alt="" width="120" height="45">
                        </a>
                    </div>
                    <h3 class="item-name">大牌强势归来</h3>
                    <a href="javascript:;" class="item-link">浏览活动&nbsp;></a>
                    <ul class="mt5">
                        <li class="item-quan">
                            <a href="javascript:;">
                                <span class="item-amount"><i>&yen;</i>50</span>
                                    <span class="item-detail">
                                        <i>满399元可用</i>
                                        <em>2015.08.28前领取</em>
                                        <b>立即领取></b>
                                    </span>
                            </a>
                        </li>
                        <li class="item-quan">
                            <a href="javascript:;">
                                <span class="item-amount"><i>&yen;</i>10</span>
                                    <span class="item-detail">
                                        <i>满12元可用</i>
                                        <em>2015.08.28前领取</em>
                                        <b>立即领取></b>
                                    </span>
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="coupon-item">
                    <div class="item-logo">
                        <a href="javascript:;">
                            <img src="./images/images_08.jpg" alt="" width="120" height="45">
                        </a>
                    </div>
                    <h3 class="item-name">大牌强势归来</h3>
                    <a href="javascript:;" class="item-link">浏览活动&nbsp;></a>
                    <ul class="mt5">
                        <li class="item-quan">
                            <a href="javascript:;">
                                <span class="item-amount"><i>&yen;</i>50</span>
                                    <span class="item-detail">
                                        <i>满399元可用</i>
                                        <em>2015.08.28前领取</em>
                                        <b>立即领取></b>
                                    </span>
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="coupon-item">
                    <div class="item-logo">
                        <a href="javascript:;">
                            <img src="./images/images_06.jpg" alt="" width="120" height="45">
                        </a>
                    </div>
                    <h3 class="item-name">大牌强势归来</h3>
                    <a href="javascript:;" class="item-link">浏览活动&nbsp;></a>
                    <ul class="mt5">
                        <li class="item-quan">
                            <a href="javascript:;">
                                <span class="item-amount"><i>&yen;</i>50</span>
                                    <span class="item-detail">
                                        <i>满399元可用</i>
                                        <em>2015.08.28前领取</em>
                                        <b>立即领取></b>
                                    </span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="col cp-fore3">
                <div class="coupon-item">
                    <div class="item-logo">
                        <a href="javascript:;">
                            <img src="./images/images_07.jpg" alt="" width="120" height="45">
                        </a>
                    </div>
                    <h3 class="item-name">大牌强势归来</h3>
                    <a href="javascript:;" class="item-link">浏览活动&nbsp;></a>
                    <ul class="mt5">
                        <li class="item-quan">
                            <a href="javascript:;">
                                <span class="item-amount"><i>&yen;</i>50</span>
                                    <span class="item-detail">
                                        <i>满399元可用</i>
                                        <em>2015.08.28前领取</em>
                                        <b>立即领取></b>
                                    </span>
                            </a>
                        </li>
                        <li class="item-quan">
                            <a href="javascript:;">
                                <span class="item-amount"><i>&yen;</i>10</span>
                                    <span class="item-detail">
                                        <i>满12元可用</i>
                                        <em>2015.08.28前领取</em>
                                        <b>立即领取></b>
                                    </span>
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="coupon-item">
                    <div class="item-logo">
                        <a href="javascript:;">
                            <img src="./images/images_08.jpg" alt="" width="120" height="45">
                        </a>
                    </div>
                    <h3 class="item-name">大牌强势归来</h3>
                    <a href="javascript:;" class="item-link">浏览活动&nbsp;></a>
                    <ul class="mt5">
                        <li class="item-quan">
                            <a href="javascript:;">
                                <span class="item-amount"><i>&yen;</i>50</span>
                                    <span class="item-detail">
                                        <i>满399元可用</i>
                                        <em>2015.08.28前领取</em>
                                        <b>立即领取></b>
                                    </span>
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="coupon-item">
                    <div class="item-logo">
                        <a href="javascript:;">
                            <img src="./images/images_06.jpg" alt="" width="120" height="45">
                        </a>
                    </div>
                    <h3 class="item-name">大牌强势归来</h3>
                    <a href="javascript:;" class="item-link">浏览活动&nbsp;></a>
                    <ul class="mt5">
                        <li class="item-quan">
                            <a href="javascript:;">
                                <span class="item-amount"><i>&yen;</i>50</span>
                                    <span class="item-detail">
                                        <i>满399元可用</i>
                                        <em>2015.08.28前领取</em>
                                        <b>立即领取></b>
                                    </span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="col cp-fore4">
                <div class="coupon-item">
                    <div class="item-logo">
                        <a href="javascript:;">
                            <img src="./images/images_07.jpg" alt="" width="120" height="45">
                        </a>
                    </div>
                    <h3 class="item-name">大牌强势归来</h3>
                    <a href="javascript:;" class="item-link">浏览活动&nbsp;></a>
                    <ul class="mt5">
                        <li class="item-quan">
                            <a href="javascript:;">
                                <span class="item-amount"><i>&yen;</i>50</span>
                                    <span class="item-detail">
                                        <i>满399元可用</i>
                                        <em>2015.08.28前领取</em>
                                        <b>立即领取></b>
                                    </span>
                            </a>
                        </li>
                        <li class="item-quan">
                            <a href="javascript:;">
                                <span class="item-amount"><i>&yen;</i>10</span>
                                    <span class="item-detail">
                                        <i>满12元可用</i>
                                        <em>2015.08.28前领取</em>
                                        <b>立即领取></b>
                                    </span>
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="coupon-item">
                    <div class="item-logo">
                        <a href="javascript:;">
                            <img src="./images/images_08.jpg" alt="" width="120" height="45">
                        </a>
                    </div>
                    <h3 class="item-name">大牌强势归来</h3>
                    <a href="javascript:;" class="item-link">浏览活动&nbsp;></a>
                    <ul class="mt5">
                        <li class="item-quan">
                            <a href="javascript:;">
                                <span class="item-amount"><i>&yen;</i>50</span>
                                    <span class="item-detail">
                                        <i>满399元可用</i>
                                        <em>2015.08.28前领取</em>
                                        <b>立即领取></b>
                                    </span>
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="coupon-item">
                    <div class="item-logo">
                        <a href="javascript:;">
                            <img src="./images/images_06.jpg" alt="" width="120" height="45">
                        </a>
                    </div>
                    <h3 class="item-name">大牌强势归来</h3>
                    <a href="javascript:;" class="item-link">浏览活动&nbsp;></a>
                    <ul class="mt5">
                        <li class="item-quan">
                            <a href="javascript:;">
                                <span class="item-amount"><i>&yen;</i>50</span>
                                    <span class="item-detail">
                                        <i>满399元可用</i>
                                        <em>2015.08.28前领取</em>
                                        <b>立即领取></b>
                                    </span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="col cp-fore5">
                <div class="coupon-item">
                    <div class="item-logo">
                        <a href="javascript:;">
                            <img src="./images/images_08.jpg" alt="" width="120" height="45">
                        </a>
                    </div>
                    <h3 class="item-name">大牌强势归来</h3>
                    <a href="javascript:;" class="item-link">浏览活动&nbsp;></a>
                    <ul class="mt5">
                        <li class="item-quan">
                            <a class="coupon-disabled" href="javascript:;">
                                <span class="item-amount"><i>&yen;</i>10</span>
                                    <span class="item-detail">
                                        <i>满12元可用</i>
                                        <em>2015.08.28前领取</em>
                                        <b>已领取></b>
                                    </span>
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="coupon-item">
                    <div class="item-logo">
                        <a href="javascript:;">
                            <img src="./images/images_08.jpg" alt="" width="120" height="45">
                        </a>
                    </div>
                    <h3 class="item-name">大牌强势归来</h3>
                    <a href="javascript:;" class="item-link">浏览活动&nbsp;></a>
                    <ul class="mt5">
                        <li class="item-quan">
                            <a href="javascript:;">
                                <span class="item-amount"><i>&yen;</i>50</span>
                                    <span class="item-detail">
                                        <i>满399元可用</i>
                                        <em>2015.08.28前领取</em>
                                        <b>立即领取></b>
                                    </span>
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="coupon-item">
                    <div class="item-logo">
                        <a href="javascript:;">
                            <img src="./images/images_06.jpg" alt="" width="120" height="45">
                        </a>
                    </div>
                    <h3 class="item-name">大牌强势归来</h3>
                    <a href="javascript:;" class="item-link">浏览活动&nbsp;></a>
                    <ul class="mt5">
                        <li class="item-quan">
                            <a href="javascript:;">
                                <span class="item-amount"><i>&yen;</i>50</span>
                                    <span class="item-detail">
                                        <i>满399元可用</i>
                                        <em>2015.08.28前领取</em>
                                        <b>立即领取></b>
                                    </span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>-->
        </div>
    </div>
</div>

<!--记得把底部加上哦-->

<div class="mask"></div>
<div class="coupon-dialog">
    <div class="coupon-dialog-header">
        <h3>提示</h3>
        <a href="javascript:;" class="close-dia"><i></i></a>
    </div>
    <div class="coupon-dialog-content">
        <div class="ticketcon">
            <div class="free-con">
                <p class="con-type">您正在免费领取</p>
                <h3 class="con-detail"><span>1</span>张 <span>同仁本草护肤专营店 20</span>元优惠券<em>（满300减20）</em></h3>
                <p class="con-intro">优惠券至2015.08.22</p>
                <p class="con-intro">每个ID限领1张</p>
                <div class="coupon-btns">
                    <p>领取优惠券后要关注该活动哦！</p>
                    <a class="cp-ok" href="javascript:;">确定</a>
                    <a class="cp-cancel" href="javascript:;">取消</a>
                </div>
            </div>
        </div>
    </div>
</div>
<#if (topmap.temp)??>
    <#if (topmap.temp.tempId==1)>
        <#include "../index/bottom.ftl">
    <#else>
        <#include "../index/newbottom.ftl" />
    </#if>
</#if>
<script type="text/javascript" src="${basePath}/js/jquery.slides.min.js"></script>
<script type="text/javascript" src="${basePath}/js/coupon/app.js"></script>
</@htmlBody>
