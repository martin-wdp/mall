<#include "../include/common.ftl">
<@htmlHead title="我的收藏-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css" />
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>我的收藏-${topmap.systembase.bsetName}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="Keywords" content="${topmap.seo.meteKey}">
    <meta name="description" content="${topmap.seo.meteDes}">
<#assign basePath=request.contextPath>
<#if (topmap.systembase.bsetHotline)??>
    <link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
    <link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css" />
    <script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
</head>-->
<@htmlBody>
<#--一引入头部 <#include "/index/topnew.ftl" />  -->
<#if (topmap.temp)??>
    <#if (topmap.temp.tempId==8)>
        <#include "../index/newtop3.ftl">
    <#elseif (topmap.temp.tempId==9)>
        <#include "../index/newtop4.ftl">
    <#elseif (topmap.temp.tempId==10)>
        <#include "../index/newtop7.ftl">
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

<#include "newtop.ftl"/>

<div style="background: #f5f5f5;">
    <div class="container clearfix pt20 pb10">
    <#include "newleftmenu.ftl">
        <div class="new_member-right">
            <div class="new_order_list">
                <div class="n-title">我的收藏</div>
                <div class="tagMenu order-menu">
                    <ul class="menu clearfix">
                        <li  <#if pb??&&pb.url=="customer/myfollw">class="current"</#if> onclick="gohref('myfollw.html');"><em></em><span></span>我收藏的商品</li>
                        <li <#if pb??&&pb.url=="customer/sellermyfollw">class="current"</#if>onclick="gohref('sellermyfollw.html');"><em>&nbsp;</em><span>&nbsp;</span>我收藏的商家</li>
                    </ul>
                </div>
            <#--<div class="simple mt10 clearfix">-->
            <#--<div class="fl">-->
            <#--<select>-->
            <#--<option>最近三个月</option>-->
            <#--</select>-->
            <#--</div>-->
            <#--<div class="fr">-->
            <#--<input type="text" class="auction" placeholder="商品名称、商品编号"/>-->
            <#--<button>查询</button>-->
            <#--</div>-->
            <#--</div>-->
                <div class="content">
                    <div class="layout">
                        <#if (pb.list?size!=0)>
                            <#list pb.list as follow>
                                <#if follow??&&follow_index<8>
                                    <div class="fav-list clearfix">
                                        <div class="shop-card fl">
                                            <#--<div class="logo">-->
                                                <#--<a class="fl" href="${basePath}/thirdstoreindex/${follow.storeId}.html" target="_blank"><img src="${follow.companyResearchUrl!''}" width="80" height="80"/></a>-->
                                            <#--</div>-->
                                            <div class="shop-card-main">
                                                <div class="shop-name">
                                                    <a class="fl" href="${basePath}/thirdstoreindex/${follow.storeId}.html" target="_blank">${follow.storeName?default('')}</a>
                                                </div>
                                                <ul class="shop-renqi" style="width:145px;">
                                                    <li>关注人气：<span>${follow.collectionCount!"0"}人</span></li>
                                                    <#--<li class="clearfix"><div class="fl">服务评价：</div><div class="put" style="margin-left: 0;">-->
                                                            <#--<span class="commstar clearfix" style="margin-top: 3px;">-->
                                                                    <#--<a href="javascript:;" class="star1"></a>-->
                                                                    <#--<a href="javascript:;" class="star2"></a>-->
                                                                    <#--<a href="javascript:;" class="star3 selected"></a>-->
                                                                    <#--<a href="javascript:;" class="star4"></a>-->
                                                                    <#--<a href="javascript:;" class="star5"></a>-->
                                                            <#--</span>-->
                                                    <#--</div></li>-->
                                                    <li>关注时间：<span>${follow.collectionCreateTime?string("yyyy-MM-dd HH:mm:ss")?substring(0,10)}</span></li>
                                                    <li>联系方式：<span>${follow.companyTel!''}</span></li>
                                                    <li>联 系 人：<span>${follow.companyContactName}</span></li>
                                                    <li>商家地址：<span>${follow.bussAddr}</span></li>

                                                </ul>
                                                <div class="shop-btn clearfix">
                                                    <a class="fl mr10" href="${basePath}/thirdstoreindex/${follow.storeId}.html" target="_blank">进去店铺</a>
                                                    <a href="javascript:void(0)" onclick="cancelsellerfollow('${basePath}/customer/delmyfollw.htm?collectionSellerId=${(follow.collectionSellerId)!''}')" >取消关注</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="discount-select fr">
                                            <#--<div class="discount-select-title">-->
                                                <#--<ul class="clearfix">-->
                                                    <#--<li>热销（10）</li>-->
                                                    <#--<li>新品（8）</li>-->
                                                <#--</ul>-->
                                            <#--</div>-->
                                            <div class="item-list-wp clearfix pr">
                                                <div class="item-scroll-wp">
                                                    <ul class="clearfix  pr">

                                                            <#list follow.goods as good>
                                                                <li>
                                                                    <a href="${basePath}/item/${good.goodsInfoId!''}"><img src="${good.goodsInfoImgId!''}" width="183" height="183"/></a>
                                                                    <p>￥${good.goodsInfoPreferPrice!''}</p>
                                                                </li>
                                                            </#list>

                                                    </ul>
                                                </div>
                                                <a href="#" class="item_scroll_prev"></a>
                                                <a href="#" class="item_scroll_next"></a>
                                            </div>
                                        </div>
                                    </div>
                                </#if>
                            </#list>
                            <#else>
                                <div  style="margin-top:10px;border:1px #e8e8e8 solid; height:40px; text-align: center; font-size: 18px;line-height:40px;;">
                                    暂无收藏商家记录！
                                </div>
                        </#if>
                    </div>
                <#if pb.list?? && (pb.list?size!=0)>
                <#-- 分页 -->
                    <#import "../pagination/pageBean.ftl" as page>
                    <@page.pagination pb />
                </#if>
                </div>

            </div>

        </div>
    </div>
</div>
<div class="mask"></div>
<div class="member-dialog dia2">
    <div class="member-dialog-body">
        <div class="title"><a href="javascript:;" onclick="cls()">×</a>提示</div>
        <div class="tc">
            <div class="que-delete clearfix">
                <img src="${basePath}/images/images_l6.png"/>
                <div class="fl tl">
                    <p class="f16 red">确定取消收藏该店铺吗？</p>
                    <div class="m-btn mt20">
                        <a  href="javascript:;" onclick="confirm()">确定</a>
                        <a  href="javascript:cls();">取消</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<div class="member-dialog dia3">
    <div class="member-dialog-body">
        <div class="title"><a href="javascript:;" onclick="cls()">×</a>提示</div>
        <div class="tc">
            <div class="que-delete clearfix">
                <img src="${basePath}/images/images_l6.png"/>
                <div class="fl tl">
                    <p class="f16 red">请重试...</p>
                    <div class="m-btn mt20">
                        <a  href="javascript:cls();">确定</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<div class="member-dialog dia22">
    <div class="member-dialog-body">
        <div class="title"><a href="javascript:;" onclick="cls()">×</a>提示</div>
        <div class="tc">
            <div class="que-delete clearfix">
                <img src="${basePath}/images/images_l6.png"/>
                <div class="fl tl">
                    <p class="f16 red">系统错误,请重试!</p>
                    <div class="m-btn mt20">
                        <a  href="javascript:cls();">确定</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<div class="member-dialog dia23">
    <div class="member-dialog-body">
        <div class="title"><a href="javascript:;" onclick="cls()">×</a>提示</div>
        <div class="tc">
            <div class="que-delete clearfix">
                <img src="${basePath}/images/images_l6.png"/>
                <div class="fl tl">
                    <p class="f16 red">该商品已下架，请选择其他商品</p>
                    <div class="m-btn mt20">
                        <a  href="javascript:cls();">确定</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<#--引入底部 <#include "/index/bottom.ftl" /> -->
<#if (topmap.temp)??>
    <#if (topmap.temp.tempId==1)>
        <#include "../index/bottom.ftl">
    <#else>
        <#include "../index/newbottom.ftl" />
    </#if>
</#if>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/customer.js"></script>

<script type="text/javascript">
    //详情页商品图片展示
    $(function(){
        $(".discount-select").each(function(){
            var _this= $(this);
            var li_n = _this.find(".item-scroll-wp li").length - 4;
            var li_step = 0;
            if(li_n >=0) {
                _this.find(".item-scroll-wp ul").width(_this.find(".item-scroll-wp li").length*195);
                _this.find(".item_scroll_next").removeClass("disabled");
                _this.find(".item_scroll_next").click(function(){
                    _this.find(".item_scroll_prev").removeClass("disabled");
                    if(li_step < li_n) {
                        li_step++;
                        _this.find(".item-scroll-wp ul").animate({
                            left: -195*li_step
                        },300);
                        if(li_step == li_n) {
                            _this.find(".item_scroll_next").addClass("disabled");
                        };
                    };
                });
                _this.find(".item_scroll_prev").click(function(){
                    _this.find(".item_scroll_next").removeClass("disabled");
                    if(li_step > 0) {
                        li_step--;
                        _this.find(".item-scroll-wp ul").animate({
                            left: -195*li_step
                        },300);
                        if(li_step == 0) {
                            _this.find(".item_scroll_prev").addClass("disabled");
                        };
                    };
                });
            };
        });


    })

    //取消收藏店铺
    var ull;
    function cancelsellerfollow(url){
        ull = url;
        dia(2);
    }
    function confirm(){
        window.location.href=ull;
        ull = "";
    }
    function checkgoods(id){
        $.ajax({
            type:'post',
            url:'${basePath}/checkgoods.htm?goodsId='+id,
            async:false,
            error:function(request) {
                dia(22);
            },
            success:function(data){
                if(data == 1){
                    window.location.href="${basePath}/item/"+id+".html"
                }else{
                    dia(23);
                }
            }
        });
    }
    function checkbuy(id){
        $.ajax({
            type:'post',
            url:'${basePath}/checkgoods.htm?goodsId='+id,
            async:false,
            error:function(request) {
                dia(22);
            },
            success:function(data){
                if(data == 1){
                    $.get("../goods/addProductToShopCar.html?productId=" + $(obj).next().val()
                    + "&goodsNum=1", function(data) {
                        if (data) {
                            location.href = "../myshoppingcart.html";
                        } else {
                            dia(3);
                        }
                    });
                }else{
                    dia(23);
                }
            }
        });
    }
    function checkComment(id){
        $.ajax({
            type:'post',
            url:'${basePath}/checkgoods.htm?goodsId='+id,
            async:false,
            error:function(request) {
                dia(22);
            },
            success:function(data){
                if(data == 1){
                    window.location.href="${basePath}/item/"+id+".html#comment"
                }else{
                    dia(23);
                }
            }
        });
    }
    $(document).ready(function(){
        $('.item_title').each(function(){
            $(this).click(function(){
                $(this).next().toggle('fast',function(){
                    if($(this).is(':visible')){
                        $(this).prev().removeClass('up');
                        $(this).prev().addClass('down');
                    }
                    else{
                        $(this).prev().removeClass('down');
                        $(this).prev().addClass('up');
                    }
                });
            });
        });
        $(".guess_goods_list").jCarouselLite({
            btnNext: ".next",
            btnPrev: ".prev",
            visible : 6,
            auto : 2000,
            speed : 800
        });
        $(".pro_sort").addClass("pro_sort_close");
        $(".new_member_left div:eq(1) ul li:eq(4)").addClass("cur");
    });

    function gohref(sd){
        window.location.href="${basePath}/customer/"+sd;
    }
</script>
</@htmlBody>
