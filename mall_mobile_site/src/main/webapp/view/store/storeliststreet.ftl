<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
    <meta content="telephone=no" name="format-detection" />
    <link href="${basePath}/m_css/style.css" rel="stylesheet">
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">
    <style>
        .abc{cursor: pointer;}
        .swiper-container {width:100%;height:200px;}
    </style>
</head>
<body>
<input type="hidden" id="basePath" value="${basePath}"/>
<div class="st-wp">
    <div class="st-hd">
        <div class="st-header" >
            <a href="javascript:;" onclick="javascript:history.back(-1);" class="st-back iconfont">&#xe634;</a>
            <h2 style="margin-top: 0px;">店铺街</h2>
        </div>
    </div>
    <#--区域查询用的-->
    <#--<input type="hidden" value="<#if map.buss_province??>${map.buss_province}</#if>" class="province_map"/>
    <input type="hidden" value="<#if map.buss_city??>${map.buss_city}</#if>" class="city_map"/>
    <input type="hidden" value="<#if map.buss_district??>${map.buss_district}</#if>" class="district_map"/>-->

    <input type="hidden" value="<#if map.cateId??>${map.cateId}</#if>" id="cateId"/>
    <#if map.channelAdvers?? >
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <#list map.channelAdvers as bigAdvert>
                        <a class="swiper-slide" href="${bigAdvert.adverHref}"><img alt="" src="${bigAdvert.adverPath}"  height="200"></a>
                    </#list>
                </div>
            </div>

    </#if>
    <div class="st-nav">
        <input type="hidden" value="${map.cateId!''}"/>
        <#if map.cateGory?? >
            <ul id="catename" name="catename" >
                <li>
                    <#if map.cateId??>
                        <#if map.cateId == -1>
                            <a href="javascript:;"  style="color:red;" onclick="selectstorebycateid(-1);">精选</a>
                        <#else>
                            <a href="javascript:;" onclick="selectstorebycateid(-1);">精选</a>
                        </#if>
                    <#else>
                        <a href="javascript:;"  style="color:red;" onclick="selectstorebycateid(-1);">精选</a>
                    </#if>
                </li>
                <#list map.cateGory as cateGory>
                    <#if map.cateId??>
                        <#if map.cateId == cateGory.catId>
                             <li><a href="javascript:;" style="color:red;" onclick="selectstorebycateid('${cateGory.catId}');">${cateGory.catName}</a></li>
                        <#else>
                            <li><a href="javascript:;" onclick="selectstorebycateid('${cateGory.catId}');">${cateGory.catName}</a></li>
                        </#if>
                    <#else>
                            <li><a href="javascript:;" onclick="selectstorebycateid('${cateGory.catId}');">${cateGory.catName}</a></li>
                    </#if>
                </#list>
            </ul>
        </#if>
    </div>


    <div class="st-content">
        <#list map.pb.list as storelist>
            <div class="st-item">
                <a href="javascript:;">
                    <div class="shop-title">
                        <#if storelist.store_images??>
                            <#list storelist.store_images as store_images>
                                <#if store_images_index==0>
                                    <img src="${store_images.imageAddress}" class="shop-brand"/>
                                </#if>
                            </#list>
                        </#if>
                        <div class="shop-name">
                            <h3> <#if storelist.storeName??><a href="${basePath}/thirdStoreIndex.htm?storeId=${storelist.storeId}">${storelist.storeName}</a></#if></h3>
                            <p><#if storelist.companyAddrDel??>${storelist.companyAddrDel}</#if></p>
                        </div>
                        <i style="cursor:pointer;" class="iconfont st-operation">&#xe61b;</i>
                        <div class="ops-box">
                            <div class="ops-cont">
                                <#if storelist.isCollection gt 0  >
                                    <span style="cursor:pointer;" class="ops-attention"   onclick="addcollectionsellerStore('${storelist.storeId}')"><i  class="iconfont">&#xe661;</i>取消关注</span>
                                <#else>
                                    <span style="cursor:pointer;" id="collection_${storelist.storeId}" onclick="addcollectionsellerStore('${storelist.storeId}')" class="ops-attention"><i  class="iconfont">&#xe661;</i>关注此店</span>
                                </#if>
                            <#--
                            <span class="ops-line">|</span>
                            <span class="ops-remove"><i class="iconfont">&#xe6aa;</i>不看此店</span>-->
                            </div>
                            <i class="ops-arrow"></i>
                        </div>
                    </div>
                    <div class="shop-show">
                        <#list storelist.storeProductList as productList>
                            <#if productList_index lt 3>
                                <div class="show-img">
                                    <a href="${basePath}/item/${productList.goodsInfoId}.html">
                                        <img src="<#if productList.goodsInfoImgId??>${productList.goodsInfoImgId}</#if>" alt="<#if productList.goodsInfoName??>${productList.goodsInfoName}</#if>" height="100%" width="100%" />
                                    </a>
                                </div>
                            </#if>
                        </#list>
                    </div>
                    <div class="shop-activity">
                    <#--<span   class="act-tag tag01 abc" onclick="storenewproduct('${storelist.storeId}')">上新<br/><strong><#if storelist.storeNewProcudtCount?? && storelist??>${storelist.storeNewProcudtCount}</#if></strong></span>
                        <span class="act-tag tag02">促销<br/><strong>100+</strong></span>
                        <span class="act-tag tag03">优惠券<br/><strong>&yen;60</strong></span>
                          <div class="act-info">
                            <#list storelist.marketinglist as marketing>
                                <p>
                                    <#if (marketing_index==0) && marketing.marketingName??>
                                        ${marketing.marketingName}
                                    </#if>
                                </p>
                            </#list>

                            <#list storelist.couponlist as couponlist>
                                <p>
                                    <#if (couponlist_index==0) && couponlist.couponName??>
                                        <#if couponlist.couponStraightDown??>
                                             直降：${couponlist.couponStraightDown.downPrice}
                                        </#if>
                                        <#if couponlist.couponFullReduction??>
                                            满${couponlist.couponFullReduction.fullPrice}降${couponlist.couponFullReduction.reductionPrice}
                                        </#if>
                                    </#if>
                                </p>
                            </#list>-->
                        </div>
                    </div>
                </a>
            </div>
        </#list>
    </div>



    <div class="pages container-fluid">
    <#if (map.pb.pageNo==1)>
        <div class="col-xs-6">
            <a class="disabled"  href="javascript:;">&lt;&nbsp;上一页</a>
        </div>
    <#else>
        <div class="col-xs-6">
            <a class="changePages" pages="${map.pb.prePageNo}" href="javascript:;">&lt;&nbsp;上一页</a>
        </div>
    </#if>
    <#if (map.pb.lastPageNo > map.pb.pageNo)>
        <div class="col-xs-6">
            <a class="changePages" pages="${map.pb.nextPageNo}" href="javascript:;">下一页&nbsp;&gt;</a>
        </div>
    <#else>
        <div class="col-xs-6">
            <a class="disabled" href="javascript:;">下一页&nbsp;&gt;</a>
        </div>
    </#if>
    </div>
    <div class="hide">
        <form action="storeliststreet.htm" id="searchForm" method="post">
            <div class="filterList">
                <ul class="clearfix">
                    <#--商品分类ID-->
                    <input type="hidden" value="<#if map.cateId??>${map.cateId}</#if>" name="cateId"/>
                    <input type="hidden" name="pageNo" class="pageNo" value="${map.pb.pageNo}">
                </ul>
            </div>
        </form>
    </div>
</div>
<div class="foot">
    <p>由${(map.mobSiteBasic.technicalSupport)!''}提供技术支持</p>
</div>
<#include "../common/smart_menu.ftl"/>
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.keleyi.js"></script>
<script type="text/javascript" src="${basePath}/js/zepto.min.js"></script>
<script type="text/javascript" src="${basePath}/js/store/storeliststreet.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/address.js"></script>
<script type="text/javascript" src="${basePath}/js/fastclick.min.js"></script>
<script type="text/javascript" src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script>
    var mySwiper = new Swiper ('.swiper-container', {
        loop: true
    })


</script>
</body>
</html>
