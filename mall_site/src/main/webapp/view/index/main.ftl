<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<head>
<#assign basePath=request.contextPath>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>${sys.bsetName}</title>
    <link rel="stylesheet" href="${basePath}/store/css/base.min.css" type="text/css" />
    <link rel="stylesheet" href="${basePath}/store/css/style.css" type="text/css" />
    <script type="text/javascript" src="${basePath}/store/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="${basePath}/store/js/jquery.slides.min.js"></script>
    <script type="text/javascript" src="${basePath}/store/js/default.js"></script>
</head>
<body>
<div>
<#include "topnew.ftl">
<#if pageAdvs?? && (pageAdvs?size>0) &&
(avc)?? && (avc?size>0) &&
(avs)?? && (avs?size>0) &&
(classifyBar.classifyBarList)?? && (classifyBar.classifyBarList?size>0) &&
(channelGoodsFlag1)?? && (channelGoodsFlag1?size>0) &&
(channelGoodsFlag2)?? && (channelGoodsFlag2?size>0) &&
(floor.floorList)?? && (floor.floorList?size>0)
>
</div>
<div class="bh_wp">
<#--
<h3 style="font-size: 16px;font-weight: bolder;">${storeinfo.storeName}</h3>
-->
    <div class="clearfix">
        <div class="left_sort fl">
            <#list classifyBar.classifyBarList as cb>
                <div class="sort_box">
                    <h3><a href="javascript:;">${cb.name}</a></h3>
                    <ul class="st_list clearfix mt5">
                        <#list cb.childs as cbChild>
                            <li><a href="javascript:;">${cbChild.name}</a></li>
                        </#list>
                    </ul><!--/st_list-->
                </div><!--/sort_box-->
            </#list>
        </div><!--/left_sort-->

        <div id="slides" class="slides ml10 fl" style="width: 760px; height: 350px;">
            <#if (avc??)>
                <#list avc as bigAdvert>
                    <#if (bigAdvert_index >= 0 )>
                        <#if (bigAdvert_index < 3 )>
                            <a href="${bigAdvert.adverHref}">
                                <img alt="" src="${bigAdvert.adverPath}" width="760px" height="350px"/></a>
                        </#if>
                    </#if>
                </#list>
            </#if>
        </div><!--/slides-->

        <div class="rig_show fr">
            <div id="r_slides" class="r_slides mb10">
                <#if (avs??)>
                    <#list avs as smallAdvert>
                        <#if (smallAdvert_index >= 0 )>
                            <#if (smallAdvert_index < 3 )>
                                <a href="${smallAdvert.adverHref}">
                                    <img alt="" src="${smallAdvert.adverPath}" width="210px" height="170px"/></a>
                            </#if>
                        </#if>
                    </#list>
                </#if>
            </div><!--/r_slides-->
            <#if (pageAdvs??)>
                <#list pageAdvs as pageAdvert>
                    <#if (pageAdvert_index >= 0 )>
                        <#if (pageAdvert_index < 1 )>
                            <a href="${pageAdvert.adverHref}">
                                <img alt="" src="${pageAdvert.adverPath}" width="210px" height="170px"/></a>
                        </#if>
                    </#if>
                </#list>
            </#if>
        </div><!--/rig_show-->
    </div>
    <!-- 楼层1F开始 -->
    <#if (floor.floorList)?? && (floor.floorList?size>0)>
        <#list floor.floorList as floore>
            <#if (floore.floorId==1)>
                <div class=" mt10">
                    <h2 style="color:#ff7200;font-size: 16px;font-weight: bolder;">${floore.storeyName!''}</h2>
                    <div class="clearfix mt10">
                        <div class="act_left fl">
                            <ul class="act_list clearfix">
                                <#if (floore.indexAdvertList)?? && (floore.indexAdvertList?size>0)>
                                    <#list floore.indexAdvertList as storeyAdvert>
                                        <#if (storeyAdvert_index >= 0)>
                                            <#if (storeyAdvert_index < 12)>
                                                <li>
                                                    <img src="${storeyAdvert.adverPath}" alt="" width="155px" height="155px"/>
                                                    <div class="act_info">
                                                        <p>${storeyAdvert.adverName}</p>
                                                        <p class="ainfo">${storeyAdvert.temp2}</p>
                                                        <p class="ainfo">${storeyAdvert.des}</p>
                                                        <a class="sg_btn" href="javascript:;">我要报名</a>
                                                    </div><!--/act_info-->
                                                </li>
                                            </#if>
                                        </#if>
                                    </#list>
                                </#if>

                            </ul>
                        </div><!--/act_left-->

                        <div class="may_like fr">
                            <h3 style="font-size: 16px;font-weight: bolder;">${thirdTemp.expFleid2}</h3>
                            <ul class="ml_list">
                                <#list infoList as info>
                                    <li>
                                        <a href="javascript:;">
                                            <#if info.title?length gt 6>
                                                <span>${info.title?substring(0,6)}...</span>
                                            <#else>
                                                <span>${info.title}</span>
                                            </#if>
                                            <span style="float: right;">${info.createDate?string("yyyy-MM-dd")}</span>
                                        </a>
                                    </li>
                                </#list>

                            </ul><!--/ml_list-->
                        </div><!--/may_like-->
                    </div>
                </div><!--/pc_act-->
            </#if>
        </#list>
    </#if>
    <!-- 楼层2F开始 -->
    <#if (floor.floorList)?? && (floor.floorList?size>0)>
        <#list floor.floorList as floore>
            <#if (floore.floorId>1)>
                <div class="bh_wp">
                    <div class="bh_box">
                        <div class="bh_title"><h2>${floore.storeyName!''}</h2></div>
                        <div class="bh_cont clearfix">
                            <div class="bh_left fl">
                                <#if (floore.indexAdvertList)?? && (floore.indexAdvertList?size>0)>
                                    <#list floore.indexAdvertList as storeyAdvert>
                                        <#if (storeyAdvert_index >= 0)>
                                            <#if (storeyAdvert_index < 2)>
                                                <a class="img_top" href="javascript:;"><img src="${storeyAdvert.adverPath}" alt="" width="208px" height="225px"/></a>
                                            </#if>
                                        </#if>
                                    </#list>
                                </#if>
                            </div><!--/bh_center-->

                            <!-- 标签开始 -->
                            <div class="bh_center fl">
                                <#if (floore.indexStoreyTagList)?? && (floore.indexStoreyTagList?size>0)>
                                    <#list floore.indexStoreyTagList as tag>
                                        <div class="b_slides b_slides_12">
                                            <#if (tag.indexAdvertList)?? && (tag.indexAdvertList?size>0)>
                                                <#list tag.indexAdvertList as tagAdvert>
                                                    <#if (tagAdvert_index >= 0)>
                                                        <#if (tagAdvert_index < 3)>
                                                            <a href="javascript:;"><img src="${tagAdvert.adverPath}" alt="" width="365px" height="210px"/></a>
                                                        </#if>
                                                    </#if>
                                                </#list>
                                            </#if>
                                        </div><!--/b_slides-->
                                        <ul class="blist clearfix">
                                            <#if (tag.indexGoodsList)?? && (tag.indexGoodsList?size>0)>
                                                <#list tag.indexGoodsList as tagGoods>
                                                    <#if (tagGoods_index >= 0)>
                                                        <#if (tagGoods_index < 6)>
                                                            <li class="bl_0${tagGoods_index+1}">
                                                                <a href="javascript:;"><img src="${tagGoods.urlpic}" alt="" width="167px" height="150px"/></a>
                                                                <p class="bname"><a href="javascript:;">
                                                                    <#if tagGoods.name?length gt 20>
                                                                        <span>${tagGoods.name?substring(0,20)}...</span>
                                                                    <#else>
                                                                        <span>${tagGoods.name}</span>
                                                                    </#if>
                                                                </a></p>
                                                                <span class="b_price">￥${tagGoods.price}</span>
                                                            </li>
                                                        </#if>
                                                    </#if>
                                                </#list>
                                            </#if>
                                        </ul><!--/blist-->
                                    </#list>
                                </#if>
                            </div><!-- 标签结束 -->

                            <div class="bh_right fr">
                                <#if (floore.indexAdvertList)?? && (floore.indexAdvertList?size>0)>
                                    <#list floore.indexAdvertList as storeyAdvert>
                                        <#if (storeyAdvert_index >= 2)>
                                            <#if (storeyAdvert_index < 4)>
                                                <a class="img_top" href="javascript:;"><img src="${storeyAdvert.adverPath}" alt=""  width="208px" height="225px"/></a>
                                            </#if>
                                        </#if>
                                    </#list>
                                </#if>
                            </div><!--/bh_center-->
                        </div><!--/bh_cont-->
                    </div><!--/bh_box-->
                </div><!--/bh_wp-->
                <img src="${floore.storeyImg}" width="1200px" height="300px"/>
            </#if>
        </#list>
    </#if>
</#if>
<#include "bottom.ftl">

    <script>
        //头部分类导航显示和隐藏
        $(".pro_sort").addClass("pro_sort_close");
        $(function() {
            $('#slides').slidesjs({
                width: 760,
                height: 350,
                play: {
                    active: true,
                    auto: true,
                    interval: 4000
                }
            });
            $('#r_slides').slidesjs({
                width: 210,
                height: 170,
                play: {
                    active: true,
                    auto: true,
                    interval: 4000
                }
            });
            $('.b_slides_01, .b_slides_02, .b_slides_03, .b_slides_04, .b_slides_05, .b_slides_06, .b_slides_07, .b_slides_08, .b_slides_09, .b_slides_10, .b_slides_11, .b_slides_12').slidesjs({
                width: 365,
                height: 210,
                slide: {
                    speed: 400
                }
            });
            $('.ranking_wp').slidesjs({
                width: 188,
                height: 160,
                slide: {
                    speed: 400
                }
            });
            $('#cs_slides').slidesjs({
                width: 208,
                height: 257,
                slide: {
                    speed: 400
                }
            });
        });
    </script>
</body>
</html>