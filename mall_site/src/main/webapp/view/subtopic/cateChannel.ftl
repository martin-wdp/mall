<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <#assign basePath=request.contextPath>
	<title><#if cate.catName??>${cate.catName}</#if>频道</title>
	<link rel="stylesheet" type="text/css" href="${basePath}/view/subtopic/css/base.min.css"/>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/view/subtopic/css/brand.css"/>

<#--
<script type="text/javascript" src="${basePath}/view/subtopic/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/view/subtopic/js/jquery.slides.min.js"></script>
<script type="text/javascript" src="${basePath}/view/subtopic/js/jquery.lazyload.min.js"></script>
-->
    <style>
        html,body{
            background: #f5f5f5;
        }
        /*以下样式不用*/
        .container{ width: 1200px; margin: 0px auto;}
        a{color:#666;}
        .pages a {display:inline-block; zoom:1; *display:inline; height:26px; line-height:26px; border:1px solid #ccc; border-radius:3px; padding:0 10px; font-size:14px;}

        .pg_prev.no_pages {background-image:url(../images/no_prev.gif);}
        .pg_next.no_pages {background-image:url(../images/no_next.gif);}
        .goods_contrast {width:966px; border-width:2px; border-style:solid; background:#fff; padding:10px; position:fixed; bottom:0; z-index:9995; display:none;}
        .gc_tit {border-bottom:1px dotted #ddd; padding:0 5px 5px;}
        .gc_tit h3 {font-family:microsoft YaHei; font-size:14px;}
        .hide_ct {color:#005aa0;}
        .contrast_items dl {padding:5px 10px 5px 0; border-right-width:1px; border-right-style:dotted; margin-right:10px;}
        .contrast_items dl dt {float:left;}
        .contrast_items dl dd {float:left; margin-left:5px; width:140px; line-height:150%;}
        .ct-item-name {height:36px; overflow:hidden;}
        .ct-item-price {font-family:microsoft YaHei;}
        .del-ct-item {color:#005aa0; margin-left:10px; display:none;}
        .has_item:hover .del-ct-item {display:inline;}
        .empty_item dt {width:50px; height:50px; background:#f6f6f6; text-align:center; line-height:50px; color:#ccc; font-size:30px;}
        .empty_item dd {color:#ccc;}
        .ct_operation {width:85px; margin-left:10px; padding-top:5px;}
        .contrast_btn {display:inline-block;background-color:#f0375e; zoom:1; *display:inline; width:54px; height:24px; line-height:24px; border-width:1px; border-style:solid; border-radius:3px; font-weight:700; color:#fff!important;}
        .contrast_btn.dis_ct {background:#fff; border-color:#ddd!important; color:#ccc!important; cursor:default;}
        .ct_operation .del-items a {color:#005aa0;}
    </style>
<style>
	.top_con .top_lefta .sortList{z-index:0;}
</style>
</head>
<body>
<#if (topmap.temp)??>
	<#if (topmap.temp.tempId==1)>
		<#include "../index/topnew.ftl">
	<#elseif (topmap.temp.tempId==3)>
		<#include "../index/newheader.ftl">
	<#elseif (topmap.temp.tempId==9)>
		<#include "../index/newheader4.ftl">
	<#elseif (topmap.temp.tempId==10)>
		<#include "../index/newheader5.ftl">
	<#elseif (topmap.temp.tempId==11)>
		<#include "../index/newheader6.ftl">
	<#elseif (topmap.temp.tempId==12)>
		<#include "../index/newheader7.ftl">
	<#elseif (topmap.temp.tempId==13)>
		<#include "../index/newheader8s.ftl">
	<#elseif (topmap.temp.tempId==14)>
		<#include "../index/newheader9.ftl">
	<#elseif (topmap.temp.tempId==15)>
		<#include "../index/newheader10.ftl">
	<#elseif (topmap.temp.tempId==16)>
		<#include "../index/newheader11.ftl">
	<#elseif (topmap.temp.tempId==17)>
		<#include "../index/newheader12.ftl">
	<#elseif (topmap.temp.tempId==18)>
		<#include "../index/newheader13.ftl">
	<#elseif (topmap.temp.tempId==19)>
		<#include "../index/newheader14.ftl">
	<#elseif (topmap.temp.tempId==20)>
		<#include "../index/newheader15.ftl">
	<#else>
		<#include "../index/newheader3.ftl">
	</#if>
</#if>

<div class="container pb20">
    <div class="brandTop clearfix mt20">
        <div class="brandMenu fl">
            <#--<div class="title">${cate.parentCat.catName}&nbsp;&gt;&nbsp;<span>${cate.catName}</span></div>-->
            <#--<div class="brandMenu-list pl10 pr10 pb10">-->
               <#list classifyBar.classifyBarList as cate1>
                  <#if cate1_index==0>
                       <div class="title">所有&nbsp;&gt;&nbsp;<span>${cate1.name}</span></div>
                         <div class="brandMenu-list pl10 pr10 pb10">
                             <#list cate1.childs as cate2>
                                   <dl class="mt10">
                                       <dt><a href="#">${cate2.name}</a></dt>
                                       <dd>
                                           <#list cate2.childs as scate>
                                               <a href="${basePath}/list/${scate.goodsCatId}-${cate.catId}.html">${scate.name}</a>
                                           </#list>
                                       </dd>
                                   </dl>
                             </#list>
                         </div>
                       </div><!--brandMenu-->
                  </#if>
               </#list>
            <#--</div>-->
        <#--</div><!--brandMenu&ndash;&gt;-->
        <div class="brandAd fr">
            <div class="b-slide">
			<#list avc as adv>
			<#--<a href="<#if adv.adverHref??>${adv.adverHref}</#if>" style="background-image:url(${adv.adverPath});"></a>	-->
                <a href="<#if adv.adverHref??>${adv.adverHref}<#else>#</#if>"><img src="${adv.adverPath}" width="976" height="500"/></a>
			</#list>
                <#--<a href="#"><img src="${basePath}/view/subtopic/images/banner.jpg" width="976" height="500"/></a>-->
                <#--<a href="#"><img src="${basePath}/view/subtopic/images/banner.jpg" width="976" height="500"/></a>-->
                <#--<a href="#"><img src="${basePath}/view/subtopic/images/banner.jpg" width="976" height="500"/></a>-->
                <#--<a href="#"><img src="${basePath}/view/subtopic/images/banner.jpg" width="976" height="500"/></a>-->
            </div>
        </div><!--brandAd-->
    </div>
    <div class="brandList">
        <div class="title mt20">品牌专区 Brand Area</div>
        <div class="brandList-con">
            <ul class="clearfix">
               <#list floor.floorList as floors>
				   <#list floors.indexBrandList as adv>
                       <li><a href="<#if adv.url??>${adv.url}<#else>#</#if>"><img src="${adv.logoSrc}" width="110" height="40"/></a></li>
				   </#list>
               </#list>
            </ul>
        </div>
    </div><!--brandList-->
    <div class="brandImg clearfix">
        <#list floor.floorList as floors>
			<#list floors.indexAdvertList as adv>
                <a href="<#if adv.adverHref??>${adv.adverHref}<#else>#</#if>"><img src="${adv.adverPath}" width="285" height="180"/></a>
			</#list>
        </#list>
    </div><!--brandImg-->
    <form action="${basePath}/channelview/2570.html" id="searchForm" method="post">
                <input type="hidden" name="pageNo" class="pageNo" value="${floor.pageBean.pageNo}">
                <input type="hidden" name="sort" class="list_sort" value="${searchBean.sort!''}">
                <input type="hidden" name="showStock" class="show_stock" value="${searchBean.showStock!''}">
                <input type="hidden" name="goodsCateId" id="goodsCateId" value="${goodsCateId}">
    </form>
    <div class="brandCon">
        <div class="new_operation_bar mt10">
            <div class="operation_wp clearfix">
                <div class="sorting_box fl clearfix">
                    <em>排序：</em>
                    <a val="2D" attr="${searchBean.sort}" class="change_sort  <#if searchBean.sort='22D' || searchBean.sort='2D'>checked</#if> <#if searchBean.sort='22D'> s_up<#elseif searchBean.sort='2D'> s_down</#if>" href="javascript:;"><span>销量</span></a>
                    <a val="1D" attr="${searchBean.sort}" class="change_sort  <#if searchBean.sort='11D' || searchBean.sort='1D'>checked</#if> <#if searchBean.sort='11D'> s_up<#elseif searchBean.sort='1D'> s_down</#if>" href="javascript:;"><span>价格</span></a>
                    <a val="4D" attr="${searchBean.sort}" class="change_sort  <#if searchBean.sort='44D' || searchBean.sort='4D'>checked</#if> <#if searchBean.sort='44D'>s_up<#elseif searchBean.sort='4D'>s_down</#if>"  href="javascript:;" ><span>人气</span></a>
                    <a val="3D" attr="${searchBean.sort}" class="change_sort  <#if searchBean.sort='33D' || searchBean.sort='3D'>checked</#if> <#if searchBean.sort='33D'>s_up<#elseif searchBean.sort='3D'>s_down</#if>"  href="javascript:;" ><span>上架时间</span></a>
                </div><!--/sorting_box-->
                <div class="op_pages fr" id="shangPageBean">
                    <span class="mr10"><b>${floor.pageBean.pageNo}</b>/${floor.pageBean.lastPageNo}</span>
                    <#if (floor.pageBean.pageNo==1)>
                        <a class="op_prev no_pages" href="javascript:;">上一页</a>
                    <#else>
                        <a class="op_prev changePages" pages="${floor.pageBean.prePageNo}" href="javascript:;">上一页</a>
                    </#if>
                    <#if (floor.pageBean.lastPageNo > floor.pageBean.pageNo)>
                        <a class="op_next  changePages" pages="${floor.pageBean.nextPageNo}" href="javascript:;">下一页</a>
                    <#else>
                        <a class="op_next no_pages" href="javascript:void(0);">下一页</a>
                    </#if>
                </div><!--/op_pages-->
                <span class="goods_num fr">共<b>${floor.pageBean.rows}</b>个商品</span>
            </div><!--/operation_wp-->
            <div class="goodsTip" id="goodsTipCheck">
                <label class="m_check mr20"><input class="vm mr5" id="check_show_stock" type="checkbox"/>仅显示有货</label>
            </div><!--/goodsTip-->
        </div>
        <div class="brandGoods clearfix mt20" id="floorGoods">
            <#if floor.pageBean.list?? && (floor.pageBean.list?size>0)>
				<#list floor.pageBean.list as product>
                    <div class="brandItem">
                        <div class="b-img">
                            <a href="<#if product.goodsproductId??>${basePath}/item/${product.goodsproductId}.html<#else>#</#if>"><img src="${product.goodsproductImgsrc}"/></a>
                            <#--<a href="${basePath}/item/${goods.goodsInfoId}.html"><img src="${goods.goodsInfoImgId}"/></a>-->
                        </div>
                        <div class="ml20 mr20">
                            <div class="b-price mt10">
                                <span>￥</span>${product.goodsInfoPreferPrice}
                            </div>
                            <div class="b-name">
                                <a href="<#if product.goodsproductId??>${basePath}/item/${product.goodsproductId}.html<#else>#</#if>">${product.goodsproductName}</a>
                                <#--<a href="${basePath}/item/${goods.goodsInfoId}.html">${goods.productName}</a>-->
                            </div>
                            <div class="nw-btn clearfix mt10">
                                <a href="javascript:;"><label class="n_check compare" id="compare${product.goodsproductId}" product_id="${product.goodsproductId}">对比</label></a>
                                <a href="javascript:;" class="attentionG" product_id="${product.goodsproductId}"><i></i>关注</a>
                                <#--<a href="#" class="Joincart"><i></i>加入购物车</a>-->
                                <#if product.isThird ==1>
                                    <a class="add_shop_cart Joincart" product_id="${product.goodsproductId}" product_stock="${product.goodsInfoStockThird}" distinct_id="${distinctId}"  href="javascript:;"><i></i>加入购物车</a>
                                <#else>
                                    <a class="add_shop_cart Joincart" product_id="${product.goodsproductId}" product_stock="${product.goodsInfoStock}" distinct_id="${distinctId}"  href="javascript:;"><i></i>加入购物车</a>
                                </#if>
                            </div>
                        </div>
                    </div>
				</#list>
            </#if>

        </div>
        <div class="pages mt10 tr" id="xiaPageBeans">
                <#if ((floor.pageBean.pageNo-2)>0)>
                    <#assign pageNo="${floor.pageBean.pageNo-2}" />
                <#else>
                    <#assign pageNo="${floor.pageBean.firstPageNo}" />
                </#if>
                <#if ((floor.pageBean.lastPageNo-1)>0)>
                    <#assign endNo="${floor.pageBean.lastPageNo-2}" />
                <#else>
                    <#assign endNo="1" />
                </#if>
                <#if (floor.pageBean.pageNo==1)>
                    <a class="pg_prev no_pages" href="javascript:;">上一页</a>
                <#else>
                    <a class="pg_prev changePages" pages="${floor.pageBean.prePageNo}" href="javascript:;">上一页</a>
                </#if>
                <#if (floor.pageBean.pageNo>3)>
                    <a class="changePages" pages="1" href="javascript:;">1</a>
                    <#if (floor.pageBean.pageNo>4)>
                        ...
                    </#if>
                </#if>
                <#list pageNo?number .. floor.pageBean.endNo as item>
                    <#if (item_index<=4)>
                        <#if (item=floor.pageBean.pageNo)>
                            <a class="cur" href="javascript:;">${item}</a>
                        <#else>
                            <a class="changePages" pages="${item}" href="javascript:;">${item}</a>
                        </#if>
                    </#if>
                </#list>
                <#if floor.pageBean.pageNo!=floor.pageBean.lastPageNo>
                    <#if ((floor.pageBean.lastPageNo-floor.pageBean.pageNo)>3) >
                        <#if (floor.pageBean.lastPageNo>5)>
                            ...
                        </#if>
                    </#if>
                </#if>
                <#if (floor.pageBean.pageNo == floor.pageBean.lastPageNo || floor.pageBean.endNo <= 1)>
                    <#if (floor.pageBean.lastPageNo > floor.pageBean.pageNo)>
                        <a class="cur" href="javascript:;">${floor.pageBean.lastPageNo}</a>
                    </#if>
                    <a class="pg_next no_pages" href="javascript:void(0);">下一页</a>
                <#else>
                    <#if ((floor.pageBean.lastPageNo - floor.pageBean.pageNo)>=3)>
                        <#if (floor.pageBean.lastPageNo>5)>
                            <a class="pg_next changePages" pages="${floor.pageBean.lastPageNo}" href="javascript:;">${floor.pageBean.lastPageNo}</a>
                        </#if>
                    </#if>
                    <a class="pg_next changePages"  pages="${floor.pageBean.nextPageNo}"  href="javascript:void(0);">下一页</a>
                </#if>
        </div>
    </div><!--brandCon-->

</div>
<!--这是底部-->
<#if (topmap.temp)??>
	<#if (topmap.temp.tempId==1)>
		<#include "../index/bottom.ftl">
	<#else>
		<#include "../index/newbottom.ftl" />
	</#if>
</#if>
<!-- 对比页面 -->
<#include "../goods/compare_box.ftl">
<!-- 提示框-->
<#include "../infotips.ftl">

	<script type="text/javascript" src="${basePath}/view/subtopic/js/jquery.js"></script>
	<script type="text/javascript" src="${basePath}/view/subtopic/js/jquery.slides.min.js"></script>
	<script type="text/javascript" src="${basePath}/view/subtopic/js/index.js"></script>
    <script type="text/javascript" src="${basePath}/view/subtopic/js/cateChannel.js"></script>
    <script type="text/javascript" src="${basePath}/js/goods/goods_compare.js"></script>
    <script type="text/javascript">
		$(function () {
            //幻灯
            if($(".b-slide").length > 0 && $(".b-slide a").length > 1) {
                $(".b-slide").slidesjs({
                    width:976,
                    height:500,
                    play: {
                        active: false,
                        effect: "slide",
                        auto: true,
                        interval: 4000,
                        pauseOnHover: true,
                        restartDelay: 2500
                    },
                    navigation: {
                        active: false
                    },
                    pagination: {
                        active: true,
                        effect: "slide"
                    }
                });
            };



            //关注
//            $(".attentionG").click(function(){
//                if($(this).hasClass("liked")) {
//                    $(this).removeClass("liked");
//                } else {
//                    $(this).addClass("liked");}
//            });

//            $(".compare").click(function(){
//                if($(this).hasClass("checked")) {
//                    $(".goods_contrast").show();
//                } else {
//                    $(".goods_contrast").hide();
//                }
//            });
        })

	</script>
</body>
</html>