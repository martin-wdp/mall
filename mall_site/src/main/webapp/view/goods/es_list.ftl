<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#assign basePath=request.contextPath>
    <title><#if nowcate.catSeoTitle??&&nowcate.catSeoTitle!=''&&nowcate.catSeoTitle!=' '>${nowcate.catSeoTitle}<#else>${nowcate.catName!''}</#if>列表页-${topmap.systembase.bsetName}</title>
    <meta name="description" content="<#if nowcate.catSeoDesc??&&nowcate.catSeoDesc!=''&&nowcate.catSeoDesc!=' '>${nowcate.catSeoDesc!''}<#else>${nowcate.catName!''}</#if>-${topmap.seo.meteDes}">
    <meta name="Keywords" content="<#if nowcate.catSeoKeyword??&&nowcate.catSeoKeyword!=''&&nowcate.catSeoKeyword!=' '>${nowcate.catSeoKeyword}<#else>${nowcate.catName!''}</#if>-${topmap.seo.meteKey}">
<#if (topmap.systembase.bsetHotline)??>
    <link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
    <link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
    <script type="text/javascript" src="${basePath}/js/jquery-1.11.1.min.js"></script>
</head>

<body  style="background: #FFFFFF;">
<#--一引入头部 <#include "/index/topnew.ftl" /> -->
<#include "../index/newheader7.ftl">
<#--绝对路径-->
<input type="hidden" id="appBasePath" value="${basePath}"/>
<div class="container">
    <div class="content clearfix">
    <#-- 2015.10.12 wuyanbo 屏蔽热销商品 -->
    <#if hotProduct??&&hotProduct?size!=0&&false>
        <div class="best_sellers" style="display: none;">
            <ul class="sellers_list clearfix mt10">

            <#list hotProduct as product>
                <input type="hidden" class="inputdiv">
                <li>
                    <a class="sellers_img fl" target="_blank" alt="${product.goodsInfoName}" title="${product.goodsInfoName}" href="${basePath}/item/${product.goodsInfoId}.html"><img class="lazy" title="${product.goodsInfoName}" alt="${product.goodsInfoName}" data-original="${product.goodsInfoImgId!''}"  width="90" height="90" /></a>
                    <div class="sellers_info fl ml5">
                        <p class="sellers_name"><a target="_blank" href="${basePath}/item/${product.goodsInfoId}.html" title="${product.goodsInfoName}" alt="${product.goodsInfoName}">${product.goodsInfoName}</a></p>
                        <p class="sellers_price">特价：<em>¥ <#if vip?? && vip=="1">${product.goodsInfoVipPrice?string("0.00")}<#else>${product.goodsInfoPreferPrice?string("0.00")}</#if></em></p>
                        <a class="sellers_btn" target="_blank" href="${basePath}/item/${product.goodsInfoId}.html">立即抢购</a>
                    </div><!--/sellers_info-->
                </li>
            </#list>

            </ul><!--/sellers_list-->
        </div><!--/best_sellers-->
    </#if>
        <div class="page_locate mt10">
        <#if nowcate.parentCat.catGrade?? &&  nowcate.parentCat.catGrade!=1 >
            <em><a href="${basePath}/list/${cate.catId}-${cate.catId}.html">${cate.catName}</a></em>
            &nbsp;&gt;&nbsp;
        </#if>
        <#if !nowcate.parentCat.catGrade?? || nowcate.parentCat.catGrade==1><em></#if>
        <#if nowcate.parentCat.catId==0>
        ${nowcate.parentCat.catName}
        <#else>
            <a href="${basePath}/list/${nowcate.parentCat.catId}-${cate.catId}.html">${nowcate.parentCat.catName}</a>
        </#if>
        <#if !nowcate.parentCat.catGrade?? || nowcate.parentCat.catGrade==1></em></#if>
            &nbsp;&gt;&nbsp;
            <span>${nowcate.catName}</span>
        </div><!--/page_locate-->

        <div class="right_wp fr">

            <div class="pro_filter mt10">
                <div class="filterBox">
                    <dl id="selected_filter" <#if (params?? && params?size &gt; 0)||(brands?? && brands?size &gt; 0)>style="display:block"<#else>style="display:none"</#if> class="filter_wp clearfix">
                        <dt>已选条件：</dt>
                        <dd>
                            <form action="${basePath}/list/${nowcate.catId}-${cate.catId}.html" id="searchForm" method="post">
                                <div class="filterList">
                                    <ul class="clearfix">
                                        <input type="hidden" name="AutoStyle" id="AutoStyle" >
                                        <input type="hidden" name="pageNo" id="pageBeanShowPage" class="pageNo" value="${map.pb.pageNo}">
                                        <input type="hidden" name="sort" class="list_sort" value="${sort!''}">
                                        <input type="hidden" name="showStock" class="show_stock" value="${showStock!''}">
                                        <input type="hidden" name="isThird" class="is_third" value="${isThird!''}">
                                    <#--已选的品牌与扩展属性-->
                                    <#if brands??>
                                        <#list brands as brand>
                                            <li data-num="brand"><input type="hidden" name="brands" value="${brand}"><a href="javascript:;">品牌：<em>${brand}</em><b></b></a></li>
                                        </#list>
                                    </#if>
                                    <#if params??>
                                        <#list params as param>
                                            <li data-num="param"><input type="hidden" name="params" value="${param}"><a href="javascript:;">${(param?split(":")[0])!''}：<em>${(param?split(":")[1])!''}</em><b></b></a></li>
                                        </#list>
                                    </#if>
                                    </ul>
                                </div><!--/filterList-->
                            </form>
                            <a class="cancel_filter" href="javascript:;">全部撤销</a>
                        </dd>
                    </dl><!--/filter_wp-->
                <#if map.brands?? && map.brands?size &gt;0>
                    <dl class="filter_item clearfix" data-num="brand">
                        <dt>品牌：</dt>
                        <dd>
                            <div class="filterList">
                                <ul class="clearfix">
                                    <#list map.brands as brand>
                                        <li><a role="brand" param="${brand.brandName!''}" href="javascript:;">${brand.brandName}</a></li>
                                    </#list>
                                </ul>
                            </div><!--/filterList-->
                            <a class="filter_op f_more none" href="javascript:;">更多<b></b></a>
                            <a class="filter_op f_less none" href="javascript:;">收起<b></b></a>
                        </dd>
                    </dl><!--/filter_wp-->
                </#if>
                <#if map.params?? && map.params?size &gt;0>
                    <#list map.params as param>
                        <#assign displayClass = (param.paramName??&&param.paramName == 'OE件')?string('hideTag','showTag')>
                        <dl class="filter_item clearfix ${displayClass}" data-num="${param.paramName}">
                            <dt>${param.paramName}：</dt>
                            <dd>
                                <div class="filterList">
                                    <ul class="clearfix">
                                        <#if param.valueVoList?? && param.valueVoList?size &gt;0>
                                            <#list param.valueVoList as value>
                                                <li><a role="eparam" param="${param.paramName}:${value.valueName}" href="javascript:;">${value.valueName}</a></li>
                                            </#list>
                                        </#if>
                                    </ul>
                                </div><!--/filterList-->
                                <a class="filter_op f_more none" href="javascript:;">更多<b></b></a>
                                <a class="filter_op f_less none" href="javascript:;">收起<b></b></a>
                            </dd>
                        </dl><!--/filter_wp-->
                    </#list>
                </#if>

                </div><!--/filterBox-->
            </div><!--/pro_filter-->
        <#if  map.brands??&&map.params??&&map.brands?size+map.params?size &gt;4>
            <div class="filter_handle tc">
                <div class="show_more" style="margin-bottom:8px;"><a href="javascript:;">展开<b></b></a></div>
                <div class="show_less" style="margin-bottom:8px;"><a href="javascript:;">收起<b></b></a></div>
            </div><!--/filter_handle-->
        </#if>

            <div class=" new_operation_bar">
                <div class="operation_wp clearfix">
                    <div class="sorting_box fl clearfix">
                        <em>排序：</em>
                        <a val="2D" attr="${sort!''}"   class="change_sort  <#if sort??><#if sort='22D' || sort='2D'>checked</#if> <#if sort='22D'> s_up<#elseif sort='2D'> s_down</#if></#if>" href="javascript:;"><span>销量</span></a>
                        <a val="1D" attr="${sort!''}"   class="change_sort  <#if sort??><#if sort='11D' || sort='1D'>checked</#if> <#if sort='11D'> s_up<#elseif sort='1D'> s_down</#if></#if>" href="javascript:;"><span>价格</span></a>
                        <a val="4D" attr="${sort!''}"   class="change_sort  <#if sort??><#if sort='44D' || sort='4D'>checked</#if> <#if sort='44D'>s_up<#elseif sort='4D'>s_down</#if></#if>" href="javascript:;"><span>人气</span></a>
                        <a val="3D" attr="${sort!''}"   class="change_sort  <#if sort??><#if sort='33D' || sort='3D'>checked</#if> <#if sort='33D'>s_up<#elseif sort='3D'>s_down</#if></#if>" href="javascript:;"><span>上架时间</span></a>
                    </div><!--/sorting_box-->
                    <div class="op_pages fr">
                        <span class="mr10"><b>${map.pb.pageNo}</b>/${map.pb.lastPageNo}</span>
					<#if (map.pb.pageNo==1)>
                        <a class="op_prev no_pages" href="javascript:;">上一页</a>
					<#else>
                        <a class="op_prev changePages" pages="${map.pb.prePageNo}" href="javascript:;">上一页</a>
					</#if>
					<#if (map.pb.lastPageNo > map.pb.pageNo)>
                        <a class="op_next  changePages" pages="${map.pb.nextPageNo}" href="javascript:;">下一页</a>
					<#else>
                        <a class="op_next no_pages" href="javascript:void(0);">下一页</a>
					</#if>
                    </div><!--/op_pages-->
                    <span class="goods_num fr">共<b>${map.pb.rows}</b>个商品</span>
                </div><!--/operation_wp-->
                <div class="goodsTip ml15">
                    <label class="m_check mr20 <#if showStock??&&showStock='1'>checked</#if>"><input class="vm mr5 check_show_stock" type="checkbox">仅显示有货</label>
                   <label class="m_check mr20 <#if isThird??&&isThird='0'>checked</#if>"><input class="vm mr5 check_show_third" type="checkbox" />自营商品</label>
                </div><!--/goodsTip-->
            </div><!--/operation_bar-->

            <div class="goods_wp mt20">
			<#if map.pb.data?? && (map.pb.data?size>0)>
                <ul class="goodsList clearfix">
                    <#if map.pb?? && map.pb.data??>
                        <#list map.pb.data as product>
                            <input type="hidden" id="disId" value="${distinctId}"/>
                            <li class="goodsBox">
                                <div class="gd_wp">
                                    <div class="g-img">
                                        <a target="_blank" href="${basePath}/item/${product.goodsInfoId}.html" alt="${product.goodsInfoName!''}" title="${product.goodsInfoName!''}"><img class="lazy" alt="${product.goodsInfoName!''}" title="${product.goodsInfoName!''}" data-original="<#if product.imgList?size &gt; 0 >${product.imgList[0].imageBigName!''}</#if>"  width="220" height="220" /></a>
                                    </div><!--/g-img-->
                                    <div class="g-scroll mt5 clearfix">
                                        <a class="g-scroll-prev disabled" href="javascript:;"></a>
                                        <div class="g-scroll-wrap">
                                            <ul class="clearfix">
                                                <#list product.imgList as image>
                                                    <li data-big="${image.imageBigName!''}"><a href="javascript:;"><img alt="${product.goodsInfoName!''}" title="${product.goodsInfoName!''}" src="${image.imageThumName!''}" /></a></li>
                                                </#list>
                                            </ul>
                                        </div><!--/g-scroll-wrap-->
                                        <a class="g-scroll-next" href="javascript:;"></a>
                                    </div><!--/g-scroll-->


                                    <div  class="b-price mt10">
                                        <span  >¥

                                        </span>
                                        <#if product.wareList??&&product.wareList?size &gt;0>
                                            <#if vip?? && vip=="1">${product.wareList[0].wareVipPrice?string('0.00')}<#else>${product.wareList[0].warePrice?string('0.00')}</#if>
                                        <#else>
                                            <#if vip?? && vip=="1">${product.goodsInfoVipPrice?string('0.00')}<#else>${product.goodsInfoPreferPrice?string('0.00')}</#if>
                                        </#if>

                                        <div class="clearfix fr">
                                        <#--设置该商品的价格与库存-->
                                            <#if vip?? && vip=="1">
                                                <#assign productPrice=product.goodsInfoVipPrice>
                                                <#assign stock=product.goodsInfoStock>
                                                <#if product.wareList??&&product.wareList?size &gt;0>
                                                    <#assign productPrice=product.wareList[0].wareVipPrice>
                                                    <#assign stock=product.wareList[0].wareStock>
                                                </#if>
                                            <#else>
                                                <#assign productPrice=product.goodsInfoPreferPrice>
                                                <#assign stock=product.goodsInfoStock>
                                                <#if product.wareList??&&product.wareList?size &gt;0>
                                                    <#assign productPrice=product.wareList[0].warePrice>
                                                    <#assign stock=product.wareList[0].wareStock>
                                                </#if>
                                            </#if>

                                            <span class="<#if stock &gt;0><#else>no-goods</#if> fr have_goods">
                                                <#if stock &gt;0>有货<#else>无货</#if>
                                            </span>

                                        </div>

                                    </div>
                                    <p class="g-name mt5"><a target="_blank" class="goods_name_wa_repl" href="${basePath}/item/${product.goodsInfoId}.html" alt="${product.goodsInfoName!''}" title="${product.goodsInfoName!''}">${product.goodsInfoName!''}</a></p>

                                    <div class="nw-btn clearfix mt10">
                                        <a href="javascript:;" ><label class="m_check n_check compare" id="compare${product.goodsInfoId}" product_id="${product.goodsInfoId}">对比</label></a>

                                        <a class="attentionG  <#if goodsIds ?? && goodsIds?size!=0>12
                                          <#if goodsIds?seq_contains('${product.goodsInfoId?string}')>liked</#if>
                                        </#if>" onclick="cllectbtnlist(${product.goodsInfoId},
                                            <#if product.wareList??&&product.wareList?size &gt;0>
                                                <#if vip?? && vip=="1">${product.wareList[0].wareVipPrice?string('0.00')}<#else>${product.wareList[0].warePrice?string('0.00')}</#if>
                                            <#else>
                                                <#if vip?? && vip=="1">${product.goodsInfoVipPrice?string('0.00')}<#else>${product.goodsInfoPreferPrice?string('0.00')}</#if>
                                            </#if>
                                               ,this )" product_id="${product.goodsInfoId}" href="javascript:;"><i></i>关注</a>
                                        <a class="add_shop_cart Joincart" product_id="${product.goodsInfoId}" product_stock="${stock}" distinct_id="${distinctId}" href="javascript:;"><i></i>加入购物车</a>


                                    </div><!--/good-operation-->
                                </div><!--/gd_wp-->
                            </li><!--/goodsBox-->
                        </#list>
                    </#if>
                </ul><!--/goodsList-->

                <div class="paging_area">
                    <div class="paging">

					<#if ((map.pb.pageNo-2)>0)>
						<#assign pageNo="${map.pb.pageNo-2}" />
					<#else>
						<#assign pageNo="${map.pb.firstPageNo}" />
					</#if>
					<#if ((map.pb.lastPageNo-1)>0)>
						<#assign endNo="${map.pb.lastPageNo-2}" />
					<#else>
						<#assign endNo="1" />
					</#if>
					<#if (map.pb.pageNo==1)>
                        <a class=" no_pages" href="javascript:;">上一页</a>
					<#else>
                        <a class="changePages" pages="${map.pb.prePageNo}" href="javascript:;">上一页</a>
					</#if>
					<#if (map.pb.pageNo>3)>
                        <a class="changePages" pages="1" href="javascript:;">1</a>
						<#if (map.pb.pageNo>4)>
                            ...
						</#if>
					</#if>
					<#list pageNo?number .. map.pb.endNo as item>
						<#if (item_index<=4)>
							<#if (item=map.pb.pageNo)>
                                <a class="num_cur prev" href="javascript:;">${item}</a>
							<#else>
                                <a class="changePages" pages="${item}" href="javascript:;">${item}</a>
							</#if>
						</#if>
					</#list>
					<#if map.pb.pageNo!=map.pb.lastPageNo>
						<#if ((map.pb.lastPageNo-map.pb.pageNo)>3) >
							<#if (map.pb.lastPageNo>5)>
                                ...
							</#if>
						</#if>
					</#if>
					<#if (map.pb.pageNo == map.pb.lastPageNo || map.pb.endNo <= 1)>
						<#if (map.pb.lastPageNo > map.pb.pageNo)>
                            <a class="num_cur prev" href="javascript:;">${map.pb.lastPageNo}</a>
						</#if>
                        <a class=" no_pages" href="javascript:void(0);">下一页</a>
					<#else>
						<#if ((map.pb.lastPageNo - map.pb.pageNo)>=3)>
							<#if (map.pb.lastPageNo>5)>
                                <a class="changePages" pages="${map.pb.lastPageNo}" href="javascript:;">${map.pb.lastPageNo}</a>
							</#if>
						</#if>
                        <a class="changePages"  pages="${map.pb.nextPageNo}"  href="javascript:void(0);">下一页</a>
					</#if>
                </div><!--/pages-->
                </div>
			<#else>
                <center>暂无商品</center>
			</#if>

            </div><!--/goods_wp-->



        </div><!--/right_wp-->

        <div class="left_wp fl">
            <div class="sortList mt10">
			<#list cate.cateVos as cate>
                <div class="sortItem <#if cate.catId==nowcate.catParentId || cate.catId==nowcate.catId>current</#if>">
                    <h3><b></b><a href="${basePath}/list/${cate.catId}-${cate.catParentId}.html">${cate.catName}</h3>
                    <ul class="clearfix">
						<#list cate.cateVos as cat>
                            <li <#if nowcate.catId==cat.catId>class='cur'</#if> >
                                <a alt="${cat.catName}" title="${cat.catName}" href="${basePath}/list/${cat.catId}-${cate.catParentId}.html">${cat.catName}</a>
                            </li>
						</#list>
                    </ul>
                </div><!--/sortItem-->
			</#list>
            </div><!--/sortList-->

        <#if finalBuy??&&finalBuy?size!=0>
            <div class="left_box">
                <h3>浏览该类别<em><!--${nowcate.catName}--></em>最终购买</h3>
                <ul class="show_list mt10">
				<#list finalBuy as product>
                    <li>
                        <div class="browse_img"><a alt="${product.product.goodsInfoName!''}" title="${product.product.goodsInfoName!''}" target="_blank" href="${basePath}/item/${product.product.goodsInfoId}.html"><img class="lazy" alt="${product.product.goodsInfoName!''}" title="${product.product.goodsInfoName!''}" data-original="${product.product.goodsInfoImgId!''}"  width="120" height="120" /></a></div>
                        <p class="browse_name mt5"><!--<span>${product.precent}%会买：</span>--><a alt="${product.product.goodsInfoName!''}" title="${product.product.goodsInfoName!''}" target="_blank" href="${basePath}/item/${product.product.goodsInfoId}.html">${product.product.goodsInfoName!''}</a></p>
                        <p class="browse_price mt5">¥ <#if vip??&&vip=="1">${product.product.goodsInfoVipPrice?string("0.00")}<#else>${product.product.goodsInfoPreferPrice?string("0.00")}</#if></p>
                    </li>
				</#list>
                </ul><!--/show_list-->
            </div><!--/left_box-->
                </#if>
		<#if hotSales?? && hotSales?size!=0>
            <div class="left_box">
                <h3>热销排行榜</h3>
                <ul class="ranking_list mt10">
					<#list hotSales as product>
                        <li>
                            <a class="ranking_img fl" alt="${product.goodsInfoName!''}" title="${product.goodsInfoName!''}" target="_blank" href="${basePath}/item/${product.goodsInfoId}.html"><img class="lazy" alt="${product.goodsInfoName!''}" title="${product.goodsInfoName!''}" data-original="${product.goodsInfoImgId!''}"  width="76" height="76" /></a>
                            <div class="ranking_info fl ml10">
                                <a class="ranking_name" alt="${product.goodsInfoName!''}" title="${product.goodsInfoName!''}" target="_blank" href="${basePath}/item/${product.goodsInfoId}.html">${product.goodsInfoName!''}</a>
                                <p class="browse_price mt5">¥ <#if vip??&&vip=="1">${product.goodsInfoVipPrice?string("0.00")}<#else>${product.goodsInfoPreferPrice?string("0.00")}</#if></p>
                            </div><!--/ranking_info-->
                        </li>
					</#list>
                </ul><!--/ranking_list-->
            </div><!--/left_box-->
		</#if>
            <div class="left_box">
                <h3>浏览过的商品</h3>
                <ul class="show_list mt10">
				<#if browserProduct??&&(browserProduct.type==1)>
					<#if browserProduct.browHist??>
						<#list browserProduct.browHist as product>
							<#if (product?? && product_index &lt; 4)>
                                <li>
                                    <div class="browse_img"><a alt="${product.goods.goodsName!''}" title="${product.goods.goodsName!''}" target="_blank" href="${basePath}/item/${product.goodsId}.html"><img class="lazy" alt="${product.goods.goodsName!''}" title="${product.goods.goodsName!''}" data-original="${product.goods.goodsImg!''}"  width="182" height="182" /></a></div>
                                    <p class="browse_name mt5"><a alt="${product.goods.goodsName!''}" title="${product.goods.goodsName!''}" target="_blank" href="${basePath}/item/${product.goodsId}.html">${product.goods.goodsName!''}</a></p>
                                    <p class="browse_price mt5">¥ ${product.goods.goodsPrice?string("0.00")}</p>
                                </li>
							</#if>
						</#list>
					</#if>
				<#else>
					<#if browserProduct??>
						<#if browserProduct.productList??>
							<#list browserProduct.productList as product>
								<#if (product?? && product_index &lt; 4)>
                                    <li>
                                        <div class="browse_img"><a alt="${product.productName!''}" title="${product.productName!''}" target="_blank" href="${basePath}/item/${product.goodsInfoId}.html"><img class="lazy" alt="${product.productName!''}" title="${product.productName!''}" data-original="<#if  product.imageList?? && product.imageList?size &gt; 0 >${product.imageList[0].imageBigName!''}</#if>"  width="182" height="182" /></a></div>
                                        <p class="browse_name mt5"><a alt="${product.productName!''}" title="${product.productName!''}" target="_blank" href="${basePath}/item/${product.goodsInfoId}.html">${product.productName!''}</a></p>
                                        <p class="browse_price mt5">¥ <#if vip??&&vip=="1">${product.goodsInfoVipPrice?string("0.00")}<#else>${product.goodsInfoPreferPrice?string("0.00")}</#if></p>
                                    </li>
								</#if>
							</#list>
						</#if>
					</#if>
				</#if>


                </ul><!--/show_list-->
            </div><!--/left_box-->
        </div><!--/left_wp-->
    </div><!--/content-->
</div><!--/container-->

<#if (topmap.temp)??>
	<#if (topmap.temp.tempId==1)>
		<#include "../index/bottom.ftl">
	<#else>
		<#include "../index/newbottom.ftl" />
	</#if>
</#if>
<!-- 对比页面 -->
<#include "compare_box.ftl">
<!-- 提示框-->
<#include "../infotips.ftl">

<div class="side_tools">
    <a class="backtotop" href="javascript:;"><em>返回顶部</em><b></b></a>
</div><!--/side_tools-->

<script type="text/javascript" src="${basePath}/js/jquery.slides.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/goods_compare.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/es_goods_list.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/new_list_common.js"></script>

<script>
    $(document).ready(function(){

        var  st=$(".inputdiv").length;

        if(st>=1){
            $(".best_sellers").show();

        }
        else{
            $(".best_sellers").hide();
        }

        $(".pro_sort").addClass("pro_sort_close");


        //列表商品图
        $(".goodsBox").each(function(){
            var _this = $(this);
            var _next = _this.find(".g-scroll-next");
            var _prev = _this.find(".g-scroll-prev");
            var _ul = _this.find(".g-scroll-wrap ul");
            var _width = _ul.find("li").length *31;
            var n = _ul.find("li").length - 5;
            var _step = 0;
            var _time = 0;
            _ul.find("li").mouseover(function(){
                var _li = $(this);
                var _src = _li.attr("data-big");
                _time = setTimeout(function(){
                    _li.siblings(".cur").removeClass("cur");
                    _li.addClass("cur");
                    _this.find(".g-img img").attr({"src":_src},{"data-original":_src});
                },200);
            }).mouseout(function(){
                clearTimeout(_time);
            });
            if(n <= 0) {
                _this.find(".g-scroll-prev, .g-scroll-next").hide();
            } else {
                _this.find(".g-scroll-wrap ul").width(_width);

                _next.click(function(){
                    _prev.removeClass("disabled");
                    if(_step < n) {
                        _step++;
                        go_next();
                    }
                });

                _prev.click(function(){
                    _next.removeClass("disabled");
                    if(_step > 0) {
                        _step--;
                        go_prev();
                    }
                });

                function go_next(){
                    _ul.animate({
                        left: -31*_step
                    });
                    if(_step == n) {
                        _next.addClass("disabled");
                    }
                }
                function go_prev(){
                    _ul.animate({
                        left: -31*_step
                    });
                    if(_step == 0) {
                        _prev.addClass("disabled");
                    }
                }
            }
        });
    });
</script>
</body>
</html>