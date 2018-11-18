<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title><#if map.detailBean.productVo.goods.goodsSeoTitle?length &gt; 0> ${map.detailBean.productVo.goods.goodsSeoTitle!''}<#else>${map.detailBean.productVo.productName!''}</#if>
        -${topmap.systembase.bsetName}</title>
    <meta name="description" content="${map.detailBean.productVo.goods.goodsSeoDesc!''}-${topmap.seo.meteDes}">
    <meta name="Keywords" content="${map.detailBean.productVo.goods.goodsSeoKeyword}-${topmap.seo.meteKey}">
<#assign basePath=request.contextPath>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/index.css"/>
    <link rel="stylesheet" href="${basePath}/css/ui-dialog.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/qp_style.css"/>
    <style type="text/css">
        .publish_cmt {width: 200px;}
        .recommend_point {margin-left: 25px;border-right: 1px solid #eee;width: 350px;height: 90px;padding: 0;}
    </style>
<#if (topmap.systembase.bsetHotline)??>
    <link rel="Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
    <link rel="Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
    <script type="text/javascript" src="${basePath}/js/jquery-1.11.1.min.js"></script>
</head>
<body>
<#--一引入头部 <#include "/index/topnew.ftl" /> -->
<#include "../index/newheader7.ftl">
<div style="background:#f5f5f5;" class="pb20">
    <div class="container clearfix">
        <div class="page_locate mt10">
            <input type="hidden" class="bread_crumb_cat_id" value="${map.detailBean.productVo.goods.catId}"
                   data-role="detail"/>
            <input type="hidden" class="first_catId" value=""/>
            <input type="hidden" class="index_url">
            <input type="hidden" value="${map.isFollow!''}" id="isFollow">
            &nbsp;&gt;&nbsp;
            <span>${map.detailBean.productVo.productName}</span>
        </div>
        <!--/page_locate-->
        <div class="product_wp mt10 clearfix">
            <div class="img_preview fl">
                <div class="big_img">
                    <a class="cloud-zoom" id="zoom" rel="adjustX:10,adjustY:0"
                       href="<#if map.detailBean.productVo.imageList?? && map.detailBean.productVo.imageList[0]??>${map.detailBean.productVo.imageList[0].imageArtworkName!''}</#if>">
                        <img alt=""
                             src="<#if map.detailBean.productVo.imageList?? && map.detailBean.productVo.imageList[0]??>${map.detailBean.productVo.imageList[0].imageBigName!''}</#if>"
                             width="350" height="350"/>
                    </a>
                </div>
                <!--/big_img-->
                <div class="thumb_img clearfix mt10 pr">
                    <div class="thumb_scroll_wp">
                        <ul class="clearfix">
                        <#if map.detailBean.productVo.imageList??>
                            <#list map.detailBean.productVo.imageList as image>
                                <li <#if image_index==0>class="cur"</#if>>
                                    <a class="cloud-zoom-gallery" href="${image.imageArtworkName!''}"
                                       rel="useZoom:'zoom',smallImage:'${image.imageBigName!''}'">
                                        <img alt="" src="${image.imageThumName!''}" width="50" height="50"/>
                                    </a>
                                </li>
                            </#list>
                        </#if>
                        </ul>
                    </div>
                    <!--/thumb_scroll_wp-->
                    <a class="thumb_scroll_prev disabled" href="javascript:"></a>
                    <a class="thumb_scroll_next disabled" href="javascript:"></a>
                </div>
                <!--/thumb_img-->
                <div class="thumb_op mt15 clearfix pr">
                <#--<a class="add_contrast fl compare" id="compare${map.detailBean.productVo.goodsInfoId}" product_id="${map.detailBean.productVo.goodsInfoId}" href="javascript:;" >加入对比</a>-->
                    <div class="share_wp fl">
                        <div class="bdsharebuttonbox">
                            <span class="vm">分享到：</span>
                            <a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
                            <a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
                            <a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
                        <#if map.detailBean.productVo.showMobile?? && map.detailBean.productVo.showMobile=='1'>
                            <a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
                        </#if>
                            <a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
                            <a href="#" class="bds_kaixin001" data-cmd="kaixin001" title="分享到开心网"></a>
                        <#--<a href="#" class="bds_taobao" data-cmd="taobao" title="分享到我的淘宝"></a>-->
                        <#--<a href="#" class="bds_fbook" data-cmd="fbook" title="分享到Facebook"></a>-->
                        <#--<a href="#" class="bds_twi" data-cmd="twi" title="分享到Twitter"></a>-->
                        </div>
                        <a class="more_share" href="javascript:"></a>
                        <script>window._bd_share_config = {
                            "common": {
                                "bdSnsKey": {},
                                "bdText": "",
                                "bdMini": "2",
                                "bdMiniList": false,
                                "bdPic": "",
                                "bdStyle": "1",
                                "bdSize": "16"
                            }, "share": {}
                        };
                        with (document)0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];
                        </script>
                    </div>
                    <!--.sahre_wp-->
                    <a class="store-goods fr cllect_btn" product_id="${map.detailBean.productVo.goodsInfoId}">
                        <i></i>
                        关注该商品
                    </a>
                </div>
                <!--/thumb_op-->
            </div>
            <!--/img_preview-->
            <div class="product_info fl ml20">
                <input type="hidden" id="goodsId" value="${map.detailBean.productVo.goodsId}"/>
                <input type="hidden" id="productId" value="${map.detailBean.productVo.goodsInfoId}"/>
                <input type="hidden" id="brandId" value="${map.detailBean.brand.brandId}"/>
                <input type="hidden" id="catId" value="${map.detailBean.productVo.goods.catId}"/>
                <input type="hidden" id="isCustomerDiscount" value="${map.detailBean.productVo.isCustomerDiscount}">
                <input type="hidden" class="bread_crumb_cat_id" value="${map.detailBean.productVo.goods.catId}"
                       data-role="detail"/>
                <div class="pd_title">
                    <h2>${map.detailBean.productVo.productName!''}</h2>
                    <strong>${map.detailBean.productVo.subtitle!''}</strong>
                </div>
                <!--/pd_title-->
                <input type="hidden" id="disId" value="${map.distinctId}"/>
                <input type="hidden" id="followPrice" value="<#if vip?? && vip == '1'>${map.detailBean.productVo.goodsInfoVipPrice}<#else>${map.detailBean.productVo.goodsInfoPreferPrice}</#if>"/>
                <dl class="pd_info clearfix mt10">
                    <div class="pd-bg clearfix">
                        <!--促销原价-->
                        <div class="cxyj" style="display:none;">
                            <dt><#if vip?? && vip == "1">会&nbsp;&nbsp;员&nbsp;&nbsp;价：<#else>零&nbsp;&nbsp;售&nbsp;&nbsp;价：</#if></dt>
                            <dd>
                                <#if vip?? && vip == "1">
                                    <del class="f14 col6">${map.detailBean.productVo.goodsInfoVipPrice?string("0.00")}</del>
                                <#else>
                                    <del class="f14 col6">${map.detailBean.productVo.goodsInfoPreferPrice?string("0.00")}</del>
                                </#if>
                            </dd>
                        </div>
                        <!--折扣-->
                        <#if vip?? && vip == "1">
                            <dt id="priceflr">会&nbsp;&nbsp;员&nbsp;&nbsp;价：</dt>
                            <dd><span
                                    class="main_price"><span>¥ </span>${map.detailBean.productVo.goodsInfoVipPrice?string("0.00")}</span><span
                                    style="color: red;" id="zk_price">&nbsp;</span><input type="hidden" id="mprice"
                                                                                          value="${map.detailBean.productVo.goodsInfoVipPrice?string("0.00")}"/>
                            </dd>
                        <#else>
                            <dt id="priceflr">零&nbsp;&nbsp;售&nbsp;&nbsp;价：</dt>
                            <dd><span
                                    class="main_price"><span>¥ </span>${map.detailBean.productVo.goodsInfoPreferPrice?string("0.00")}</span><span
                                    style="color: red;" id="zk_price">&nbsp;</span><input type="hidden" id="mprice"
                                                                                          value="${map.detailBean.productVo.goodsInfoPreferPrice?string("0.00")}"/>
                            </dd>
                        </#if>
                        <dt>促销信息：</dt>
                        <dd class="product_market "></dd>
                    </div>
                    <div class="pl20 pr20 pt10">
                        <dt>配&nbsp;&nbsp;送&nbsp;&nbsp;至：</dt>
                        <dd>
                            <div class="choose_area fl pr">
                                <div class="area_text choose_btn">${map.chAddress}<b></b></div>
                                <div class="locate_cont">
                                    <ul class="locate_tabs clearfix">
                                        <li class="cur show_province"><a href="javascript:"><span
                                                class="province_text">${map.chProvince}</span><b></b></a></li>
                                        <li class="show_city"><a href="javascript:"><span
                                                class="city_text">${map.chCity}</span><b></b></a></li>
                                        <li class="show_distinct"><a href="javascript:"><span
                                                class="distinct_text">${map.chDistinct}</span><b></b></a></li>
                                    </ul>
                                    <!--/locate_tabs-->
                                    <div class="locate_wp">
                                        <ul class="locate_list clearfix province_list">
                                        </ul>
                                        <!--/locate_list-->
                                        <ul class="locate_list clearfix city_list">
                                        </ul>
                                        <!--/locate_list-->
                                        <ul class="locate_list clearfix distinct_list">
                                        </ul>
                                        <!--/locate_list-->
                                    </div>
                                    <!--/locate_wp-->
                                    <a class="close_area" href="javascript:"></a>
                                </div>
                                <!--/locate_cont-->
                            </div>
                            <!--/choose_area-->
                            <div class="store-prompt fl ml10"><em>
                            <#if (map.detailBean.productVo.goodsInfoStock>0)>
                                有货
                            <#else>
                                无货
                            </#if></em><!--11:00前完成下单，预计今天（6月27日）送达--><span class="baoyou"></span></div>

                        </dd>
                        <dt>服&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务：</dt>
                        <dd>
                            由“<#if map.detailBean.productVo.isThird='0'>${topmap.systembase.bsetName!''}<#else>${map.detailBean.productVo.thirdName!''}</#if>
                            ”直接销售和发货，并提供售后服务
                        </dd>
                        <dt>温馨提示：</dt>
                        <dd>
                            <div class="wx_prompt clearfix">
                            <#if map.detailBean??&&map.detailBean.suppList??&&map.detailBean.suppList?size!=0>
                                <#list map.detailBean.suppList as supp>
                                    <#if supp.serviceSupp??>
                                        <#if supp.serviceSupp.serviceImageUrl??>
                                            <img src="${supp.serviceSupp.serviceImageUrl!''}" height="20px;"
                                                 width="20px;" style="float:left;"/>
                                        </#if>
                                        <#if supp.serviceSupp.helpId?? && supp.serviceSupp.serviceName??>
                                            <a class="fl" target="_blank"
                                               href="${basePath}/help/${supp.serviceSupp.helpId!''}">${supp.serviceSupp.serviceName!''}</a>
                                        </#if>
                                    </#if>
                                </#list>
                            </#if>
                            </div>
                            <!--/wx_prompt-->
                        </dd>
                    </div>
                </dl>
                <!--/pd_info-->


                <dl class="buy_info mt20 clearfix pl20">
                    <input type="hidden" id="allSpecLength" value="${map.detailBean.productVo.specVo?size}"/>

                <#list map.openSpec as spec>
                    <#assign displayClass = (spec.spec.specName??&&spec.spec.specName == '.')?string('hideTag','showTag')>
                    <dt class="${displayClass}">${spec.spec.specName!''}：</dt>
                    <dd class="${displayClass}" style="width:650px;">
                        <div class="choose_type clearfix">
                            <#list spec.specValList as specvalue>
                                <a class="choose_item _sku" onClick="clickSpecDetail(this,false);"
                                   data-parent="${spec.spec.specId}" data-value="${specvalue.specDetail.specDetailId}"
                                   title="${specvalue.specValueRemark}" href="javascript:">
                                    <#if specvalue.imgUrl?? && specvalue.imgUrl!=''>
                                        <img alt="" width="25px" height="25px" src="${specvalue.imgUrl}"/>
                                    </#if>
                                    <em>${specvalue.specValueRemark}</em><b></b></a>
                            </#list>
                        </div>
                        <!--/choose_img-->
                    </dd>
                </#list>
                    <!-- 保存已经选择的规格值 -->
                    <div class="hide">
                    <#if (map.detailBean.productVo.specVo??)>
                        <#list map.detailBean.productVo.specVo as spec>
                            <input type="hidden" class="chooseParamId" value="${spec.goodsSpecDetail.specDetailId!''}"
                                   data-spec="${spec.specName!''}" data-detail="${spec.specValueRemark!''}"/>
                        </#list>
                    </#if>
                    </div>
                    <dt>购买数量：</dt>
                    <dd>
                        <a class="minus num_minus fl" href="javascript:"></a>
                        <input class="min_text num_text fl product_buy_num" type="text" value="1"/>
                        <a class="plus num_plus fl" href="javascript:"></a>
                    </dd>
                </dl>
                <!--/buy_info-->
            <#if map.openSpec?size &gt; 0>
                <p class="selected_type pl20 f14 ${displayClass}">已选择 <span class="check_param"></span></p>
            </#if>

                <div class="by_cart mt20 clearfix">
                <#if (map.detailBean.productVo.goodsInfoStock>0)>
                    <a class="collect_pro mr20 go_buy" product_id="${map.detailBean.productVo.goodsInfoId}"
                       product_stock="${map.detailBean.productVo.goodsInfoStock}"
                       distinct_id="${map.distinctId}">立即购买</a>
                    <a class="add_cart" product_id="${map.detailBean.productVo.goodsInfoId}"
                       product_stock="${map.detailBean.productVo.goodsInfoStock}" distinct_id="${map.distinctId}"
                       href="javascript:"><i></i>加入购物车</a>


                <#else>
                    <a class="dis_cart" href="javascript:"><i></i>加入购物车</a>
                </#if>

                </div>
                <!--/by_cart-->
            </div>
            <!--/product_info-->
        </div>
        <!--/product_wp-->
    </div>
</div>

<!-- 保存商品参数部分相关参数 -->
<form id="paramGoodsForm" action="${map.detailBean.productVo.goodsInfoId}.html" method="post">
    <input type="hidden" name="chAddress" class="ch_address" value="${map.chAddress}"/>
    <input type="hidden" name="chProvince" class="ch_province" value="${map.chProvince}"/>
    <input type="hidden" name="chCity" class="ch_city" value="${map.chCity}"/>
    <input type="hidden" name="chDistinct" class="ch_distinct" value="${map.chDistinct}"/>
    <input type="hidden" name="distinctId" class="ch_distinctId" value="${map.distinctId}"/>
    <input type="hidden" class="productStock" value="${map.detailBean.productVo.goodsInfoStock}"/>
</form>
<div class="container">


<#list map.detailBean.groupVos as group>
    <#if group.groupPrefertype?? && group.groupPrefertype=="0">
        <#assign isT=0>
    </#if>
    <#if group.groupPrefertype?? && group.groupPrefertype=="1">
        <#assign isT=1>
    </#if>
</#list>

    <#if map.detailBean.groupVos?size &gt; 0>
        <div class="group_box popular_group">
            <ul class="groups_tabs pop_tabs clearfix">
                <#assign count=0>
                <#assign count2=0>
                <#list map.detailBean.groupVos as groups>
                    <#if groups.groupPrefertype=="1">
                        <#assign count=count+1>
                    </#if>
                    <#if groups.groupPrefertype=="0">
                        <#assign count2=count2+1>
                    </#if>
                </#list>
                <#if count!=0>
                    <li><a href="javascript:">人气组合</a></li>
                </#if>
                <#if count2!=0>
                    <li><a href="javascript:">优惠套餐</a></li>
                </#if>
            </ul>
            <!--/pop_tabs-->
            <div class="groups_wp pop_wp">
                <div class="groups_cont pop_cont clearfix">
                    <div class="tagMenu mobile-links">
                        <ul class="menu clearfix">
                            <#list map.detailBean.groupVos as group>
                                <#if group.groupPrefertype=="1">
                                    <li><a href="javascript:">${group.groupName}</a></li>
                                </#if>
                            </#list>
                        </ul>
                    </div>
                    <div class="content">
                        <#list map.detailBean.groupVos as group>
                            <#if group.groupPrefertype=="1">
                                <#assign preferPrice = 0>
                                <div class="layout">
                                    <ul class="gp_list fl clearfix">
                                        <li>
                                            <div class="pg_img">
                                                <a alt="${map.detailBean.productVo.productName}" title="${map.detailBean.productVo.productName}" target="_blank" href="${basePath}/item/${map.detailBean.productVo.goodsInfoId}.html"><img class="lazy" alt="${map.detailBean.productVo.productName}" title="${map.detailBean.productVo.productName}" data-original="${map.detailBean.productVo.goodsInfoImgId}"  width="120" height="120" /></a>
                                            </div>
                                            <p class="pg_name"><a alt="${map.detailBean.productVo.productName}" title="${map.detailBean.productVo.productName}" target="_blank" href="${basePath}/item/${map.detailBean.productVo.goodsInfoId}.html">${map.detailBean.productVo.productName}</a></p>
                                            <#assign  preferPrice="${map.detailBean.productVo.goodsInfoPreferPrice?number+preferPrice?number}">
                                        </li>
                                        <#list group.productList as product>
                                            <li <#if product_index+1==group.productList?size>class="last_item"</#if>>
                                                <div class="pg_img">
                                                    <a alt="${product.productDetail.goodsInfoName}" title="${product.productDetail.goodsInfoName}" target="_blank" href="${basePath}/item/${product.productDetail.goodsInfoId}.html"><img class="lazy" alt="${product.productDetail.goodsInfoName}" title="${product.productDetail.goodsInfoName}" data-original="${product.productDetail.goodsInfoImgId}"  width="120" height="120" /></a>
                                                </div>
                                                <p class="pg_name"><a alt="${product.productDetail.goodsInfoName}" title="${product.productDetail.goodsInfoName}" target="_blank" href="${basePath}/item/${product.productDetail.goodsInfoId}.html">${product.productDetail.goodsInfoName}</a></p>
                                                <div class="gp_check"><label class="qp_check getgroupGoods"></label><input class="vm mr5 check_group_product check_group_product_${group.groupId}" data-group="${group.groupId}" data-market="${product.productDetail.goodsInfoMarketPrice?string("0.00")}"  data-price="${product.productDetail.goodsInfoPreferPrice?string("0.00")}" value="${product.productDetail.goodsInfoId}" type="hidden">¥ ${product.productDetail.goodsInfoPreferPrice?string("0.00")}</div>
                                            </li>
                                        </#list>
                                    </ul>
                                    <!--/gp_list-->

                                    <div class="gp_result fr">
                                        <div class="gp_name"><a href="javascript:">购买${group.groupName}</a></div>
                                        <p>销售价：<b>¥</b> <span class="group_prefer_price_${group.groupId}">${map.detailBean.productVo.goodsInfoPreferPrice?string("0.00")}</span></p>
                                        <p>市场价：<del><b>¥</b> <span class="group_market_price_${group.groupId}">${map.detailBean.productVo.goodsInfoMarketPrice?string("0.00")}</span></del></p>
                                        <a class="buy_pak_group buy_group" data-group="${group.groupId}" href="javascript:">购买组合</a>
                                    </div>
                                    <!--/gp_result-->
                                </div>
                            </#if>
                        </#list>
                    </div>
                </div>
                <!--/groups_cont-->

                <div class="groups_cont pop_cont clearfix">
                    <div class="tagMenu mobile-links ulList">
                        <ul class="menu clearfix">
                            <#list map.detailBean.groupVos as group>
                                <#if group.groupPrefertype=="0">
                                    <li><a href="javascript:">${group.groupName}</a></li>
                                </#if>
                            </#list>
                        </ul>
                    </div>
                    <div class="content discount">
                        <#list map.detailBean.groupVos as group>
                            <#if group.groupPrefertype=="0">
                                <#assign preferPrice = 0>
                                <div class="layout">
                                    <ul class="gp_list fl clearfix">
                                        <li>
                                            <div class="pg_img">
                                                <a alt="${map.detailBean.productVo.productName}" title="${map.detailBean.productVo.productName}" target="_blank" href="${basePath}/item/${map.detailBean.productVo.goodsInfoId}.html"><img class="lazy" alt="${map.detailBean.productVo.productName}" title="${map.detailBean.productVo.productName}" data-original="${map.detailBean.productVo.goodsInfoImgId}"  width="120" height="120" /></a>
                                            </div>
                                            <p class="pg_name"><a alt="${map.detailBean.productVo.productName}" title="${map.detailBean.productVo.productName}" target="_blank" href="${basePath}/item/${map.detailBean.productVo.goodsInfoId}.html">${map.detailBean.productVo.productName}</a></p>
                                            <#assign  preferPrice="${map.detailBean.productVo.goodsInfoPreferPrice?number+preferPrice?number}">
                                        </li>
                                        <#list group.productList as product>
                                            <#if product??&&product.productDetail??>
                                                <li <#if product_index+1==group.productList?size>class="last_item"</#if>>
                                                    <div class="pg_img">
                                                        <a alt="${product.productDetail.goodsInfoName!''}" title="${product.productDetail.goodsInfoName!''}" target="_blank" href="${basePath}/item/${product.productDetail.goodsInfoId!''}.html"><img class="lazy" alt="${product.productDetail.goodsInfoName!''}" title="${product.productDetail.goodsInfoName!''}" data-original="${product.productDetail.goodsInfoImgId}"  width="120" height="120" /></a>
                                                    </div>
                                                    <p class="pg_name"><a alt="${product.productDetail.goodsInfoName!''}" title="${product.productDetail.goodsInfoName!''}" target="_blank" href="${basePath}/item/${product.productDetail.goodsInfoId}.html">${product.productDetail.goodsInfoName!''}</a></p>
                                                    <#assign  preferPrice="${product.productDetail.goodsInfoPreferPrice?number+preferPrice?number}">
                                                </li>
                                            </#if>
                                        </#list>
                                    </ul>
                                    <!--/gp_list-->
                                    <div class="gp_result fr">
                                        <div class="gp_name"><a href="javascript:">购买${group.groupName}</a></div>
                                        <p>销售价：<b>¥</b> ${((preferPrice?number-group.groupPreferamount?number)?number)?string("0.00")}</p>
                                        <p>市场价：<del><b>¥</b> ${preferPrice?number?string("0.00")}</del></p>
                                        <a class="buy_pre_group buy_group" data-group="${group.groupId}" href="javascript:">购买套装</a>
                                    </div>
                                    <!--/gp_result-->
                                </div>
                            </#if>
                        </#list>
                    </div>

                </div>
                <!--/groups_cont-->
            </div>
            <!--/groups_wp-->
        </div>
    </#if>

    <!--group_box-->
    <div class="content clearfix mt10">
        <div class="right_wp fr">

            <div class="product_detail">
                <div class="tit_wp">
                    <div class="det_title detail_nav clearfix">
                        <ul class="nav_tabs fl clearfix">
                            <li class="cur"><a href="javascript:">商品介绍</a></li>
                            <li><a href="javascript:">规格参数</a></li>
                            <li><a href="javascript:" id="autoStyle">适配车型</a></li>
                            <li class="product_comment"><a href="javascript:">商品评价</a></li>
                            <li class="product_ask"><a href="javascript:">商品咨询</a></li>
                        </ul>
                        <!--/nav_tabs-->
                    <#if (map.detailBean.productVo.goodsInfoStock>0)>
                        <a class="add-cart-btn fr " product_id="${map.detailBean.productVo.goodsInfoId}"
                           product_stock="${map.detailBean.productVo.goodsInfoStock}" distinct_id="${map.distinctId}"
                           href="javascript:"><i></i>加入购物车</a>
                    <#else>
                    <#--<a class="dis-cart-btn dis_cart fr"  href="javascript:;"><i></i>加入购物车</a>-->
                    </#if>
                    </div>
                    <!--/det_title-->
                </div>
                <!--/tit_wp-->

                <div class="details_box ">
                    <div class="details-more fl">
                        <a name="1f"></a>
                        <ul class="pro_introduce clearfix">
                            <li>商品名称：${map.detailBean.productVo.productName!''}</li>
                            <li>商品编号：${map.detailBean.productVo.goodsInfoItemNo}</li>
                        <#assign displayClass = (map.detailBean.brand.brandName??&&map.detailBean.brand.brandName == 'OEM')?string('hideTag','showTag')>
                            <li class="${displayClass}">品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;牌：${map.detailBean.brand.brandName!''}</li>
                            <li>上架时间：${map.detailBean.productVo.goodsInfoAddedTime?string("yyyy-MM-dd")}</li>
                        <#list map.detailBean.expandPrams as expandParam>
                            <#assign displayClass = (expandParam.expandParamVo.expandparamName??&&expandParam.expandParamVo.expandparamName == 'OE件')?string('hideTag','showTag')>
                            <li class="${displayClass}">
                                ${expandParam.expandParamVo.expandparamName} ：${expandParam.expangparamValue.expandparamValueName}
                            </li>
                        </#list>
                        </ul>
                        <!--/pro_introduce-->
                        <div class="intro_cont">
                            <!-- 2015.11.10 wuyanbo 屏蔽 -->
                            <div class="detail_show" style="display: none;">
                            <#if map.detailBean??&&map.detailBean.brand??>${map.detailBean.brand.brandDesc!''}</#if>
                            </div>
                            <div class="detail_show">
                                <!-- <img src="http://101.200.195.229:10000/M00/1B/6D/ZcjD5VaUdwOAESlkAAE20qsW2Rc808.jpg"
                                     onclick="window.open('${basePath}/subject/65')" style="cursor:pointer;" alt="" >-->
                                ${(map.detailBean.productVo.goods.goodsDetailDesc)!''}
                            </div>
                            <!--/detail_show-->
                        </div>
                        <!--/intro_cont-->
                    </div>
                    <!--details-more-->
                    <div class="goods-dg">
                        <ul>
                            <li><a href="#1f">商品详情</a></li>
                        <#--<li><a href="#2f">售后保障</a></li>-->
                        <#--<li><a href="#3f">责任声明</a></li>-->
                        </ul>
                    </div>
                </div>
                <!--/details_box-->

                <div class="details_box">
                    <table class="parameter_tb w">
                        <tr>
                            <th colspan="2">详细参数</th>
                        </tr>
                    <#list map.detailBean.param as param>
                        <#if (param.paramValue)??&&(param.paramValue)!=''>
                            <tr>
                                <td width="20%">${param.param.paramName}：</td>
                                <td>${param.paramValue}</td>
                            </tr>
                        </#if>
                    </#list>

                    </table>
                    <!--/parameter_tb-->
                </div>
                <!--/details_box-->

                <!--适配车型(各类品牌车列表)-->
                <div class="details_box auto_box" style="border:1px solid #eeeeee;">

                </div>
                <!--details_box-->

                <div class="details_box">
                    <div class="evaluation_info clearfix mt10">
                        <div class="rate fl tc">
                            <strong><span class="bigHaoPercent">95<b>%</b></strong></span>
                            <br/>
                            <span>好评度</span>
                        </div>
                        <!--/rate-->
                        <div class="percent fl ml20">
                            <dl class="per_info clearfix">
                                <dt>好评<em class="per">(<span class="haoPercent"></span>)</em></dt>
                                <dd>
                                    <div class="per_bar"><span class="haoPercentLine"></span></div>
                                </dd>
                            </dl>
                            <dl class="per_info clearfix">
                                <dt>中评<em class="per">(<span class="zhongPercent"></span>)</em></dt>
                                <dd>
                                    <div class="per_bar"><span class="zhongPercentLine"></span></div>
                                </dd>
                            </dl>
                            <dl class="per_info clearfix">
                                <dt>差评<em class="per">(<span class="chaPercent"></span>)</em></dt>
                                <dd>
                                    <div class="per_bar"><span class="chaPercentLine"></span></div>
                                </dd>
                            </dl>
                        </div>
                        <!--/percent-->
                        <div class="recommend_point fl">
                        <#if map.tags?size  &gt; 0>
                            推荐点：

                            <ul class="rec_points mt10 clearfix">
                                <#list map.tags as tag>
                                    <li>${tag.goodsTag.tagName!""}</li>
                                </#list>
                            </ul>
                        </#if>
                        </div>
                        <!--/recommend_point-->
                        <div class="publish_cmt fl">
                            <p>您可对已购的商品进行评价</p>
                            <a class="pb_cmt pub_comment" target="_blank" href="${basePath}/customer/myorder.html">发表评论拿积分</a>
                        <#--<p>1积分=1元&nbsp;&nbsp;<a class="cmt_rule" href="${basePath}/help/55">评论规则</a></p>-->
                        </div>
                        <!--/publish_cmt-->
                    </div>
                    <!--/evaluation_info-->

                    <div class="det_title mt10 clearfix">
                        <ul class="cmt_tabs fl clearfix">
                            <li class="cur"><a href="javascript:" class="commentTab" data-role="3">全部评论（<span
                                    class="allCount"></span>）</a></li>
                            <li><a href="javascript:" class="commentTab" data-role="0">好评（<span
                                    class="haoCount"></span>）</a></li>
                            <li><a href="javascript:" class="commentTab" data-role="1">中评（<span
                                    class="zhongCount"></span>）</a></li>
                            <li><a href="javascript:" class="commentTab" data-role="2">差评（<span
                                    class="chaCount"></span>）</a></li>
                        </ul>
                        <!--/cmt_tabs-->
                    </div>
                    <!--/det_title-->

                    <div class="comment_wp">
                        <div class="comment_cont" style="display:block">
                            <div class="comment_list">
                                <!-- 评论主体 -->
                            </div>
                            <div class="clearfix mt10">
                                <a class="all_cmt fl" target="_blank"
                                   href="${basePath}/comment/${map.detailBean.productVo.goodsInfoId}.html">[查看全部评价]</a>

                            </div>
                        </div>
                        <!--/comment_cont-->
                    </div>
                    <!--/comment_wp-->
                    <div class="paging_area">
                        <div class="paging comment_pages">

                        </div>
                    </div>
                    <!--/pages-->


                </div>
                <!--/details_box-->

                <div class="details_box">
                    <div class="det_title mt10 clearfix">
                        <ul class="consult_tabs fl clearfix">
                            <li class="cur" data-role="0"><a href="javascript:">全部购买咨询</a></li>
                            <li data-role="1"><a href="javascript:">商品咨询</a></li>
                            <li data-role="2"><a href="javascript:">库存配送</a></li>
                            <li data-role="3"><a href="javascript:">支付</a></li>
                            <li data-role="4"><a href="javascript:">发票及配送</a></li>
                            <li data-role="5"><a href="javascript:">促销及赠品</a></li>
                        </ul>
                        <!--/consult_tabs-->
                    </div>
                    <!--/det_title-->
                    <div class="consult_box clearfix">
                        <div class="consult_search fl">
                            咨询前请先搜索，方便又快捷：
                            <div class="consult_input mt10 clearfix">
                                <input class="consult_text fl" type="text" placeholder="请输入咨询关键词"/>
                                <input class="cs_search_btn fl" type="button" onclick="loadCommentAsk(1,null);"
                                       value="搜索"/>
                            </div>
                            <!--/consult_input-->
                        </div>
                        <!--/consult_search-->
                        <div class="consult_prompt fl">
                            <b>温馨提示：</b>因厂家更改产品包装、产地或者更换随机附件等没有任何提前通知，且每位咨询者购买情况、提问时间等不同，
                            为此以下回复仅对提问者3天内有效，其他网友仅供参考！若由此给您带来不便请多多谅解，谢谢！
                        </div>
                        <!--/consult_prompt-->
                    </div>
                    <!--/consult_box-->
                    <div class="consult_wp">
                        <div class="consult_cont" style="display:block">
                            <div class="consult_list">
                            </div>
                            <div class="all_consult clearfix tr">
                                <div class="fl">购买之前，如有问题，请<a class="issued_consult ml5" target="_blank"
                                                              href="${basePath}/cosult/${map.detailBean.productVo.goodsInfoId}.html#pub">[发表咨询]</a>
                                </div>
                                <div class="all_consult_tip tr">
                                    <em>共<b class="consult_count"></b>条</em><a class="ml20" target="_blank"
                                                                               href="${basePath}/cosult/${map.detailBean.productVo.goodsInfoId}.html">浏览所有咨询信息»</a>
                                </div>
                            </div>
                        </div>
                        <!--/consult_cont-->
                    </div>
                    <!--/consult_wp-->
                </div>
                <!--/details_box-->
            </div>
            <!--/product_detail-->
        </div>
        <!--/right_wp-->

        <div class="left_wp fl">

        <#if map.goodscates??&&map.goodscates?size!=0>
            <div class="relevant">
                <div class="title">相关分类</div>
                <ul class="clearfix pt10 pb10">
                    <#list map.goodscates as cate>
                        <li class="text-hide"><a class="aboutcate" href="${basePath}/list/${cate.catId}" title="${cate.catName}">${cate.catName}</a></li>
                    </#list>
                </ul>
            </div>
        </#if>

        <#if map.goodsBrands??&&map.goodsBrands?size!=0>
            <div class="relevant">
                <div class="title">相关品牌</div>
                <ul class="clearfix pt10 pb10">
                    <#list map.goodsBrands as brandvo>
                        <li class="text-hide"><a class="aboutbrand" href="${basePath}/list/" title="${brandvo.brand.brandName}">${brandvo.brand.brandName}</a></li>
                    </#list>
                </ul>
            </div>
        </#if>
        <#if map.finalBuy?? && map.finalBuy?size!=0>
            <div class="left_box">
                <h3>浏览该商品的用户最终购买</h3>
                <ul class="show_list mt10">
                    <#list map.finalBuy as product>
                        <li>
                            <div class="browse_img"><a alt="${product.product.goodsInfoName!''}"
                                                       title="${product.product.goodsInfoName!''}" target="_blank"
                                                       href="${basePath}/item/${product.product.goodsInfoId}.html"><img
                                    class="lazy" alt="${product.product.goodsInfoName!''}"
                                    title="${product.product.goodsInfoName!''}"
                                    data-original="${product.product.goodsInfoImgId!''}" width="120" height="120"/></a>
                            </div>
                            <p class="browse_name mt5"><!--<span>${product.precent}%会买：</span>--><a
                                    alt="${product.product.goodsInfoName!''}"
                                    title="${product.product.goodsInfoName!''}" target="_blank"
                                    href="${basePath}/item/${product.product.goodsInfoId}.html">${product.product.goodsInfoName!''}</a>
                            </p>

                            <#if vip?? && vip == "1">
                                <p class="browse_price mt5">¥ ${product.product.goodsInfoVipPrice?string("0.00")}</p>
                            <#else>
                                <p class="browse_price mt5">¥ ${product.product.goodsInfoPreferPrice?string("0.00")}</p>
                            </#if>
                        </li>
                    </#list>
                </ul>
                <!--/show_list-->
            </div>
            <!--/left_box-->
        </#if>

        <#if map.hotSales??>
            <div class="left_box">
                <h3>热销排行榜</h3>

                <div class="charts-list tagMenu">
                    <ul class="menu clearfix mt10">
                        <li class="typeshow current" attr_type="isShow1">同价位</li>
                        <li class="typeshow " attr_type="isShow2">同品牌</li>
                        <li class="typeshow " attr_type="isShow3">同类别</li>
                    </ul>
                </div>

            <#--同价位-->
                <#if map.pricehotSales?? && map.pricehotSales?size!=0>
                    <ul class="ranking_list mt10 isShow1">
                        <#list map.pricehotSales as pricehotSales>
                            <li>
                                <em <#if pricehotSales_index lt 3>class="pre-top"</#if>>${pricehotSales_index+1}</em>
                                <a class="ranking_img fl" alt="${pricehotSales.goodsInfoName!''}"
                                   title="${pricehotSales.goodsInfoName!''}" target="_blank"
                                   href="${basePath}/item/${pricehotSales.goodsInfoId}.html"><img class="lazy"
                                                                                                  alt="${pricehotSales.goodsInfoName!''}"
                                                                                                  title="${pricehotSales.goodsInfoName!''}"
                                                                                                  data-original="${pricehotSales.goodsInfoImgId!''}"
                                                                                                  width="50"
                                                                                                  height="50"/></a>

                                <div class="ranking_info fl ml10">
                                    <a class="ranking_name" alt="${pricehotSales.goodsInfoName!''}"
                                       title="${pricehotSales.goodsInfoName!''}" target="_blank"
                                       href="${basePath}/item/${pricehotSales.goodsInfoId}.html">${pricehotSales.goodsInfoName!''}</a>

                                    <p class="browse_price mt5">
                                    <#if vip?? && vip == "1">
                                        ¥ ${pricehotSales.goodsInfoVipPrice?string("0.00")}
                                    <#else>
                                        ¥ ${pricehotSales.goodsInfoPreferPrice?string("0.00")}
                                    </#if>
                                    </p>
                                </div>
                                <!--/ranking_info-->
                            </li>
                        </#list>
                    </ul>
                    <!--/ranking_list-->
                <#else>
                    <ul class="ranking_list mt10 isShow1">

                        <li>
                            <em class="pre-top">1</em>

                            <div class="ranking_info fl ml10">
                                <a class="ranking_name">近一个月同价位的货品暂无记录!</a>
                            </div>
                            <!--/ranking_info-->
                        </li>

                    </ul>
                </#if>

            <#--同品牌-->
                <#if map.brandhotSales?? && map.brandhotSales?size!=0>
                    <ul class="ranking_list mt10 isShow2" style="display: none">
                        <#list map.brandhotSales as brandhotSales>
                            <li>

                                <em <#if brandhotSales_index lt 3>class="pre-top"</#if>>${brandhotSales_index+1}</em>
                                <a class="ranking_img fl" alt="${brandhotSales.goodsInfoName!''}"
                                   title="${brandhotSales.goodsInfoName!''}" target="_blank"
                                   href="${basePath}/item/${brandhotSales.goodsInfoId}.html"><img class="lazy"
                                                                                                  alt="${brandhotSales.goodsInfoName!''}"
                                                                                                  title="${brandhotSales.goodsInfoName!''}"
                                                                                                  data-original="${brandhotSales.goodsInfoImgId!''}"
                                                                                                  width="50"
                                                                                                  height="50"/></a>

                                <div class="ranking_info fl ml10">
                                    <a class="ranking_name" alt="${brandhotSales.goodsInfoName!''}"
                                       title="${brandhotSales.goodsInfoName!''}" target="_blank"
                                       href="${basePath}/item/${brandhotSales.goodsInfoId}.html">${brandhotSales.goodsInfoName!''}</a>

                                    <p class="browse_price mt5">
                                    <#if vip?? && vip == "1">
                                        ¥ ${brandhotSales.goodsInfoVipPrice?string("0.00")}
                                    <#else>
                                        ¥ ${brandhotSales.goodsInfoPreferPrice?string("0.00")}
                                    </#if>
                                    </p>
                                </div>
                                <!--/ranking_info-->
                            </li>
                        </#list>
                    </ul>
                    <!--/ranking_list-->
                <#else>
                    <ul class="ranking_list mt10 isShow2" style="display: none">

                        <li>
                            <em class="pre-top">1</em>

                            <div class="ranking_info fl ml10">
                                <a class="ranking_name">近一个月同品牌的货品暂无记录!</a>
                            </div>
                            <!--/ranking_info-->
                        </li>

                    </ul>
                </#if>

            <#--同类别-->
                <#if map.hotSales?? && map.hotSales?size!=0>
                    <ul class="ranking_list mt10 isShow3" style="display: none">
                        <#list map.hotSales as product>
                            <li>
                                <em <#if product_index lt 3>class="pre-top"</#if>>${product_index+1}</em>
                                <a class="ranking_img fl" alt="${product.goodsInfoName!''}"
                                   title="${product.goodsInfoName!''}" target="_blank"
                                   href="${basePath}/item/${product.goodsInfoId}.html"><img class="lazy"
                                                                                            alt="${product.goodsInfoName!''}"
                                                                                            title="${product.goodsInfoName!''}"
                                                                                            data-original="${product.goodsInfoImgId!''}"
                                                                                            width="50" height="50"/></a>

                                <div class="ranking_info fl ml10">
                                    <a class="ranking_name" alt="${product.goodsInfoName!''}"
                                       title="${product.goodsInfoName!''}" target="_blank"
                                       href="${basePath}/item/${product.goodsInfoId}.html">${product.goodsInfoName!''}</a>

                                    <p class="browse_price mt5">
                                    <#if vip?? && vip == "1">
                                        ¥ ${product.goodsInfoVipPrice?string("0.00")}
                                    <#else>
                                        ¥ ${product.goodsInfoPreferPrice?string("0.00")}
                                    </#if>
                                    </p>
                                </div>
                                <!--/ranking_info-->
                            </li>
                        </#list>
                    </ul>
                    <!--/ranking_list-->
                <#else>
                    <ul class="ranking_list mt10 isShow3" style="display: none">

                        <li>
                            <em class="pre-top">1</em>

                            <div class="ranking_info fl ml10">
                                <a class="ranking_name">近一个月同类别的货品暂无记录!</a>
                            </div>
                            <!--/ranking_info-->
                        </li>

                    </ul>
                </#if>
            </div>
            <!--/left_box-->
        </#if>
        <#if map.detailBean.releProductList??>
            <div class="left_box">
                <h3>浏览了该商品的用户还浏览了</h3>
                <ul class="show_list mt10">
                    <#list map.detailBean.releProductList as product>
                        <#if product??>
                            <li>
                                <div class="browse_img"><a alt="${product.goodsInfoName}"
                                                           title="${product.goodsInfoName}" target="_blank"
                                                           href="${basePath}/item/${product.goodsInfoId}.html"><img
                                        class="lazy" alt="${product.goodsInfoName}" title="${product.goodsInfoName}"
                                        data-original="${product.goodsInfoImgId!''}" width="182" height="182"/></a>
                                </div>
                                <p class="browse_name mt5"><a alt="${product.goodsInfoName}"
                                                              title="${product.goodsInfoName}" target="_blank"
                                                              href="javascript:">${product.goodsInfoName}</a></p>

                                <p class="browse_price mt5">
                                <#if vip?? && vip == "1">
                                    ¥ ${product.goodsInfoVipPrice?string("0.00")}
                                <#else>
                                    ¥ ${product.goodsInfoPreferPrice?string("0.00")}
                                </#if>
                                </p>
                            </li>
                        </#if>
                    </#list>
                </ul>
                <!--/show_list-->
            </div>
            <!--/left_box-->
        </#if>
        </div>
        <!--/left_wp-->
        <div style="clear:both; height:0;"></div>
    </div>
    <!--/content-->
    <div style="clear:both; height:0;"></div>
    <script type="text/javascript">
        //商品详细页客服咨询参数设置 2015.10.13 wuyanbo add
        NTKF_PARAM.ntalkerparam={};
        NTKF_PARAM.ntalkerparam.item={
            'id':'${map.detailBean.productVo.goodsId}',//商品id
            'name':'${map.detailBean.productVo.goods.goodsName}',//商品名称
            'imageurl':'${map.detailBean.productVo.goods.goodsImg}',//商品图片
            'url':'${topmap.systembase.bsetAddress}/item/${map.detailBean.productVo.goodsInfoId}.html',//商品url
            'marketprice':'${map.detailBean.productVo.goodsInfoPreferPrice}',//商品市场价格
            'siteprice':<#if vip?? && vip == "1">'${map.detailBean.productVo.goodsInfoVipPrice}'<#else>'${map.detailBean.productVo.goodsInfoPreferPrice}'</#if>//商品当前价格
        }
    </script>
</div>
<!--/container-->
</div>


<!-- 对比页面 -->
<#include "compare_box.ftl">

<!-- 提示框-->
<#include "../infotips.ftl">

<div class="side_tools">
    <a class="backtotop" href="javascript:"><em>返回顶部</em><b></b></a>
</div>
<!--/side_tools-->

<script type="text/javascript" src="${basePath}/js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="${basePath}/js/cloud-zoom.1.0.2.min.js"></script>
<script type="text/javascript" src="${basePath}/js/index.js"></script>

<script>

    $(function () {
        $(".product_market").on("mouseover", "#cut", function () {
            var _this = $(this);
            var _cont = '<p>会员等级 会员折扣率</p><p>普通会员：99%</p><p>黄金会员：95%</p><p>白金会员：91%</p><p>砖石会员：85%</p><p>金牌会员：65%</p><p>超级会员：33%</p>';
            var d = dialog({
                width: 150,
                align: 'right',
                quickClose: true,
                skin: 'prm-tip',
                content: _cont
            });
            var _time = setTimeout(function () {
                d.show(_this[0]);
            }, 300);
        });
        $(".product_market").on("mouseout", "#cut", function () {
            clearTimeout(_time);
        });


        $(".typeshow").click(function () {

            $(this).siblings(".current").removeClass("current");
            $(this).addClass("current");
            var str = $(this).attr("attr_type");
            $(".ranking_list").hide();
            $("." + str).show();

        });



    });
    $(document).ready(function () {
        <!-- 加载规格值区域 -->
        var productList = null;
        $.get("${basePath}/all/" + $("#goodsId").val() + ".html", function (data) {
            productList = data;
            <!-- 把查询到的数据传递到js方法中 -->
            loadAllProduct(productList);
            loadChooseParam();
        });

        <!-- 加载商品促销的信息 -->
        loadGoodsMark();

        $(".pro_sort").addClass("pro_sort_close");
    });
</script>
<script type="text/javascript" src="${basePath}/js/dialog-min.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/goods_compare.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/goods_comm.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/new_list_common.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/new_goods_detail.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/new_goods_autotype.js"></script>
<script type="text/javascript">
    $(function () {
        var st = $(".inputdiv").length;

        if (st >= 1) {
            $(".isshowdiv").show();

        }
        else {
            $(".isshowdiv").hide();
        }

        //商品套装
        $(".boxfl li").click(function () {
            var a = $(this).index();
            var ul = $(".groups_wp .boxfl_count:eq(" + a + ")").find(".gp_list");
            var li_width = ul.find("li").width() + 80;
            var ul_width = li_width * ul.find("li").length;
            ul.width(ul_width);
        });
        $(".boxfl li").click();

        //商品组合
        $(".groupfl li").click(function () {
            var bb = $(this).index();
            var ul = $(".groups_wp .groupfl_count:eq(" + bb + ")").find(".gp_list");
            var li_width = ul.find("li").width() + 80;
            var ul_width = li_width * ul.find("li").length;
            ul.width(ul_width);
        });
        $(".groupfl li").click();
    });
    $(function () {
//        var location = (window.location+'').split('/');
//        var basePath = location[0]+'//'+location[2]+'/'+location[3];
//        $("#autoStyle").click(function(){
//            $.getJSON(basePath+"GetAllAutoStyle.htm",{},function(data){
//                var tt="";
//                $.each(data, function(k, v) {
//                    tt+='';
//                })
//            })
//        })



        $(".collectstore").click(function () {
            $.post("${basePath}/addcollectionseller.htm?collectionThirdId=${map.detailBean.productVo.thirdId}", function (data) {
                if (data == "2") {
                    /*初始化弹框样式*/
                    $(".info_tip_content2").html("您已经收藏过该商家！");
                    $(".info_tip_img2").attr("src", "../images/collect.png");
                    $(".info_tip_cancel2").attr("href", "javascript:;").hide();
                    $(".info_tip_submit2").attr("href", "javascript:;").show().html("确定").unbind("click");
                    $(".info_tip_submit2").click(function () {
                        cls();
                    });
                    dia(2);

                } else if (data == "3") {
                    /*初始化弹框样式*/
                    $(".info_tip_content2").html("是否跳转到登录页?");
                    $(".info_tip_img2").attr("src", "../images/index_ques.png");
                    $(".info_tip_cancel2").attr("href", "javascript:;").html("取消").show();
                    $(".info_tip_submit2").attr("href", "../login.html").show().html("确定").unbind("click");
                    $(".info_tip_cancel2").click(function () {
                        cls();
                    });
                    dia(2);
                } else {
                    /*初始化弹框样式*/
                    $(".info_tip_content2").html("恭喜您收藏成功！");
                    $(".info_tip_img2").attr("src", "../images/collect.png");
                    $(".info_tip_cancel2").attr("href", "javascript:;").hide();
                    $(".info_tip_submit2").attr("href", "javascript:;").show().html("确定").unbind("click");
                    $(".info_tip_submit2").click(function () {
                        cls();
                    });
                    dia(2);
                }
            });
        });
        $(".qp_check").each(function () {
            var _this = $(this);
            _this.click(function () {
                if (_this.hasClass("checked")) {
                    _this.removeClass("checked");
                } else {
                    _this.addClass("checked");
                }
            })
        });




        $(".nav-mobile").hover(function () {
            $(this).addClass("hover");
        }, function () {
            $(this).removeClass("hover");
        });
    });

    $(".layout").hide();
    $(".layout:nth-child(1)").show();
    $(".tagMenu .menu li").bind("click",function(){
        var temp = null;
        $(".groups_tabs li").each(function(){
            if($(this).attr("class") == "cur"){
               temp = $(this).index();
            }
        });
        var n = $(this).index();
        $(".layout").hide();
        if(temp == 1){
            $(".discount .layout").hide();
            $($(".discount .layout")[n]).show();
        }else {
            $($(".layout")[n]).show();
        }
    });
    $(".groups_tabs li").mouseover(function(){
        var n = $(this).index();
        if(n == 0){
            $(".layout").hide();
            $($(".layout")[1]).show();
        }else{
            $(".discount .layout").hide();
            $($(".discount .layout")[1]).show();
        }
    });
</script>
<#include "../index/newbottom.ftl" />
</body>
</html>
