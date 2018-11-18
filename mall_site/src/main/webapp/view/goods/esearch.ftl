<#include "../include/common.ftl">
<@htmlHead title='${keyWorlds}搜索结果-${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<style>
    .price_con{margin-top: 12px; margin-left: 20px;}
    .price_con input{display:inline-block;width:50px; padding:0px 5px; height:24px; border:1px solid #ddd;color:#666; }
    .price_con button{border: none; background: #df1738;border-radius:3px; height:24px; width:40px; color:#fff; text-align: center; line-height: 24px; display: inline-block; margin-left: 10px;}
</style>
</@htmlHead>
<@htmlBody>
<body style="background: #FFFFFF">
<#--一引入头部 <#include "/index/topnew.ftl" /> -->
<#include "../index/newheader7.ftl">

<input  type="hidden" value="${keyWorlds}" id="keyWorlds"/>
<div class="container" >

    <div class="page_locate mt10">
        <label>全部结果</label><span>&gt;</span>
        <span>"<b style="color: #df1738">${keyWorlds}</b>"</span>
    </div><!--/page_locate-->
    <input type="hidden" id="appBasePath" value="${basePath}"/>
    <div class="content clearfix mt10">

        <div class="right_wp fr">

            <div class="pro_filter">
                <div class="filterBox">
                    <dl id="selected_filter" <#if (params?? && params?size &gt; 0)||(brands?? && brands?size &gt; 0)>style="display:block"<#else>style="display:none"</#if> class="filter_wp clearfix">
                        <dt>已选条件：</dt>
                        <dd>
                            <form id="searchForm" method="post" action="${basePath}/goods/searchproduct2.html?AutoStyle=${AutoStyle!''}&title=${keyWorlds!''}">
                            <#--<input type="hidden" name="AutoStyle" id="AutoStyle" >-->
                            <div class="filterList">
                                <ul class="clearfix">
                                <#--已选的品牌与扩展属性-->
                                <#if brands??>
                                    <#list brands as brand>
                                        <li data-num="brand"><input type="hidden" name="brands" value="${brand}"><a href="javascript:">品牌：<em>${brand}</em><b></b></a></li>
                                    </#list>
                                </#if>
                                <#if params??>
                                    <#list params as param>
                                        <li data-num="param"><input type="hidden" name="params" value="${param}"><a href="javascript:">${(param?split(":")[0])!''}：<em>${(param?split(":")[1])!''}</em><b></b></a></li>
                                    </#list>
                                </#if>
                                <#--<#if param.type="spec">
                                    <li data-num="s${param.parentId}"><input type="hidden" name="params" value="s${param.value}"><a href="javascript:;">${param.title}：<em>${param.text}</em><b></b></a></li>
                                </#if>-->
                                </ul>
                            </div><!--/filterList-->

                            <a class="cancel_filter" href="javascript:">全部撤销</a>
                        </dd>
                    </dl><!--/filter_wp-->
                <#if map.brands?? && map.brands?size &gt;0>
                    <dl class="filter_item clearfix" data-num="brand">
                        <dt>品牌：</dt>
                        <dd>
                            <div class="filterList">
                                <ul class="clearfix">
                                    <#list map.brands as brand>
                                        <li><a role="brand" param="${brand.brandName!''}" href="javascript:">${brand.brandName}</a></li>
                                    </#list>
                                </ul>
                            </div><!--/filterList-->
                            <a class="filter_op f_more none" href="javascript:">更多<b></b></a>
                            <a class="filter_op f_less none" href="javascript:">收起<b></b></a>
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
                                                <li><a role="eparam" param="${param.paramName}:${value.valueName}" href="javascript:">${value.valueName}</a></li>
                                            </#list>
                                        </#if>
                                    </ul>
                                </div><!--/filterList-->
                                <a class="filter_op f_more none" href="javascript:">更多<b></b></a>
                                <a class="filter_op f_less none" href="javascript:">收起<b></b></a>
                            </dd>
                        </dl><!--/filter_wp-->
                    </#list>
                </#if>
                <#--<#if map.type??&& map.type.specVos??>
                    <input type="hidden" name="cId" class="" value="${map.cate.catId!''}">
                    <#list map.type.specVos as spec>
                        <!-- 如果规格的显示方式是显示到列表页,就显示到页面 ,否则就不显示&ndash;&gt;
                        <#if spec.goodsSpec.specShowmode=="1">
                            <dl class="filter_item clearfix" data-num="s${spec.goodsSpec.specId}">
                                <dt>${spec.goodsSpec.specName}：</dt>
                                <dd>
                                    <div class="filterList">
                                        <ul class="clearfix">
                                            <#list spec.goodsSpec.specDetails as detail>
                                                <li><a role="spec" param="${detail.specDetailId}" href="javascript:;">${detail.specDetailName}</a></li>
                                            </#list>
                                        </ul>
                                    </div><!--/filterList&ndash;&gt;
                                    <a class="filter_op f_more none" href="javascript:;">更多<b></b></a>
                                    <a class="filter_op f_less none" href="javascript:;">收起<b></b></a>
                                </dd>
                            </dl><!--/filter_wp&ndash;&gt;
                        </#if>
                    </#list>
                </#if>-->
                </div><!--/filterBox-->
            </div><!--/pro_filter-->

		<#if map.brands??&&map.params??&&map.brands?size+map.params?size &gt;4>
            <div class="filter_handle tc">
                <div class="show_more"><a href="javascript:">展开<b></b></a></div>
                <div class="show_less"><a href="javascript:">收起<b></b></a></div>
            </div><!--/filter_handle-->
		</#if>
            <div class="operation_bar mt10">
                <div class="operation_wp clearfix" style="border: 1px solid #eee;">
                    <div class="sorting_box fl clearfix">
                        <em>排序：</em>
                        <a val="2D" attr="${sort!''}"  onClick="changeSearch(this,'searchSort','2D');" class="change_sort  <#if sort??><#if sort='22D' || sort='2D'>checked</#if> <#if sort='22D'> s_up<#elseif sort='2D'> s_down</#if></#if>" href="javascript:"><span>销量</span></a>
                        <a val="1D" attr="${sort!''}" onClick="changeSearch(this,'searchSort','1D');"  class="change_sort  <#if sort??><#if sort='11D' || sort='1D'>checked</#if> <#if sort='11D'> s_up<#elseif sort='1D'> s_down</#if></#if>" href="javascript:"><span>价格</span></a>
                        <a val="4D" attr="${sort!''}" onClick="changeSearch(this,'searchSort','4D');"  class="change_sort  <#if sort??><#if sort='44D' || sort='4D'>checked</#if> <#if sort='44D'>s_up<#elseif sort='4D'>s_down</#if></#if>" href="javascript:"><span>人气</span></a>
                        <a val="3D" attr="${sort!''}"  onClick="changeSearch(this,'searchSort','3D');" class="change_sort  <#if sort??><#if sort='33D' || sort='3D'>checked</#if> <#if sort='33D'>s_up<#elseif sort='3D'>s_down</#if></#if>" href="javascript:"><span>上架时间</span></a>
                    </div><!--/sorting_box-->
                    <!-- 保存查询参数 -->
                    <div class="price_con fl">
                        <input type="text" style=" font-family: 'Verdana';" placeholder="¥" id="priceMin" name="priceMin" <#if priceMin??>value="${priceMin}"</#if> onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>&nbsp;-&nbsp;
                        <input type="text"  style=" font-family: 'Verdana';" placeholder="¥" id="priceMax" name="priceMax" <#if priceMax??>value="${priceMax}"</#if> onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
                        <button onclick="confirmButton()">确定</button>
                    </div><!--price_con-->

                    <input type="hidden" name="showStock" class="show_stock" value="<#--${map.searchBean.showStock!''}-->">
                    <#--<input type="hidden" id="search_title" name="title" value="${keyWorlds}" />-->
                    <input type="hidden" id="searchSort" name="sort" value="${sort!''}"/>
                    <input type="hidden" id="pageBeanShowSize" name="pageSize" value="${map.pb.pageSize}" />
                    <input type="hidden" id="pageBeanShowPage" name="pageNo" value="${map.pb.pageNo}" />
                    </form>
                    <div class="op_pages fr">
                        <span class="mr10"><b>${map.pb.pageNo}</b>/${map.pb.lastPageNo}</span>
					<#if (map.pb.pageNo==1)>
                        <a class="op_prev no_pages" href="javascript:">上一页</a>
					<#else>
                        <a class="op_prev" <#if (map.pb.nextPageNo>0)>onClick="changeSearch(this,'pageBeanShowPage','${map.pb.prePageNo}');"</#if>  href="javascript:">上一页</a>
					</#if>
					<#if (map.pb.lastPageNo > map.pb.pageNo)>
                        <a class="op_next" <#if (map.pb.nextPageNo>0)>onClick="changeSearch(this,'pageBeanShowPage','${map.pb.nextPageNo}');"</#if>  href="javascript:">下一页</a>
					<#else>
                        <a class="no_pages op_next" href="javascript:void(0);">下一页</a>
					</#if>
                    </div><!--/op_pages-->
                    <span class="goods_num fr">共<b>${map.pb.rows}</b>个商品</span>
                </div><!--/operation_wp-->
                <div class="goodsTip ml15 none">
                    <label class="m_check mr20 <#--<#if map.searchBean.showStock='1'>checked</#if>"-->><input class="vm mr5 check_show_stock none" type="checkbox">仅显示有货</label>
                    <#--<!--<label class="m_check mr20"><input class="vm mr5" type="checkbox" />自营商品</label>&ndash;&gt;-->
                </div><!--/goodsTip-->
            </div><!--/operation_bar-->

            <div class="goods_wp mt20">
                <ul class="goodsList clearfix">
				<#if map.pb?? && map.pb.data??>
					<#list map.pb.data as product>
                        <li class="goodsBox">
                            <div class="gd_wp">
                                <div class="g-img">
                                    <a target="_blank" href="${basePath}/item/${product.goodsInfoId}.html" alt="${product.goodsInfoName!''}" title="${product.goodsInfoName!''}"><img class="lazy" alt="${product.goodsInfoName!''}" title="${product.goodsInfoName!''}" data-original="<#if product.imgList?size &gt; 0 >${product.imgList[0].imageBigName!''}</#if>"  width="226" height="226" /></a>
                                </div><!--/g-img-->
                                <div class="g-scroll mt20 clearfix">
                                    <a class="g-scroll-prev disabled" href="javascript:"></a>
                                    <div class="g-scroll-wrap">
                                        <ul class="clearfix">
											<#list product.imgList as image>
                                                <li data-big="${image.imageBigName!''}"><a href="javascript:"><img alt="${product.goodsInfoName!''}" title="${product.goodsInfoName!''}" src="${image.imageThumName!''}" /></a></li>
											</#list>
                                        </ul>
                                    </div><!--/g-scroll-wrap-->
                                    <a class="g-scroll-next" href="javascript:"></a>
                                </div><!--/g-scroll-->

                            <#--设置该商品的价格与库存-->
                                <#if vip??&&vip=="1">
                                    <#assign productPrice=product.goodsInfoVipPrice>
                                <#else>
                                    <#assign productPrice=product.goodsInfoPreferPrice>
                                </#if>
                                <#assign stock=product.goodsInfoStock>
                                <#if product.wareList??&&product.wareList?size &gt;0>
                                    <#list product.wareList as ware>
                                        <#if wareId?? && ware.wareId==wareId>
                                            <#if vip??&&vip=="1">
                                                <#assign productPrice=ware.wareVipPrice>
                                            <#else>
                                                <#assign productPrice=ware.warePrice>
                                            </#if>
                                            <#assign stock=ware.wareStock>
                                            <#break>
                                        </#if>
                                    </#list>
                                </#if>
                                <div  class="b-price ">
                                    <span class="fu">¥</span>
                                ${productPrice}
                                    <span class="<#if stock &gt;0><#else>no-goods</#if> fr have_goods">
                                        <#if stock &gt;0>有货<#else>无货</#if>
                                    </span>
                                </div>
                                <p class="g-name mt5"><a target="_blank" class="goods_name_wa_repl" href="${basePath}/item/${product.goodsInfoId}.html" alt="${product.goodsInfoName!''}" title="${product.goodsInfoName!''}"><#if product.highLightName??>${product.highLightName}<#else>${product.goodsInfoName!''}</#if></a></p>
                                <div class="clearfix">

                                    <#--<span class="g-price fl">已有<em>7026人评价</em></span>-->


                                </div>
                                <div class="nw-btn clearfix mt10">

                                    <a href="javascript:"><label class="m_check n_check compare" id="compare${product.goodsInfoId}" product_id="${product.goodsInfoId}">对比</label></a>
                                    <a class="attentionG  <#if goodsIds ?? && goodsIds?size!=0>12
                                          <#if goodsIds?seq_contains('${product.goodsInfoId?string}')>liked</#if>
                                        </#if>" onclick="cllectbtnlist(${product.goodsInfoId},
                                        <#if product.wareList??&&product.wareList?size &gt;0>
                                            <#if vip??&&vip=="1">${product.wareList[0].wareVipPrice?string('0.00')}<#else>${product.wareList[0].warePrice?string('0.00')}</#if>
                                        <#else>
                                            <#if vip??&&vip=="1">${product.goodsInfoVipPrice?string('0.00')}<#else>${product.goodsInfoPreferPrice?string('0.00')}</#if>
                                        </#if>
                                            ,this )" product_id="${product.goodsInfoId}" href="javascript:"><i></i>关注</a>
                                    <a class="add_shop_cart Joincart" product_id="${product.goodsInfoId}" product_stock="${stock}" distinct_id="${distinctId}" href="javascript:"><i></i>加入购物车</a>
                                </div><!--/good-operation-->
                            </div><!--/gd_wp-->
                            <#--<div class="shop_name tc"><a href="javascript:;"><#if product.thirdId=0>${topmap.systembase.bsetName!''}<#else>${product.thirdName!''}</#if></a></div>-->
                        </li><!--/goodsBox-->
					</#list>
				</#if>
                </ul><!--/goodsList-->
			<#if (map.pb.rows &gt; 0)>
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
                        <a class="no_pages" href="javascript:">上一页</a>
					<#else>
                        <a class="prev pg_prev" <#if (map.pb.nextPageNo>0)>onClick="changeSearch(this,'pageBeanShowPage','${map.pb.prePageNo}');"</#if> href="javascript:">上一页</a>
						<#if (map.pb.pageNo >= 4)>
                            <a class="num" href="javascript:void(0)" onClick="changeSearch(this,'pageBeanShowPage','${map.pb.firstPageNo}');">${map.pb.firstPageNo}</a>
						</#if>
					</#if>
					<#if ((map.pb.pageNo-3)>1) >
                        <span class="ellipsis">...</span>
					</#if>
					<#list pageNo?number .. map.pb.endNo as item>
						<#if (item_index<=4)>
							<#if (item=map.pb.pageNo)>
                                <a class="num_cur prev" href="javascript:void(0);">${item}</a>
							<#else>
                                <a class="num" href="javascript:void(0);" onClick="changeSearch(this,'pageBeanShowPage','${item}');">${item}</a>
							</#if>
						</#if>
					</#list>
					<#if map.pb.pageNo!=map.pb.lastPageNo>
						<#if ((map.pb.lastPageNo-map.pb.pageNo)>3) >
							<#if (map.pb.lastPageNo>5)>
                                <span class="ellipsis">...</span>
							</#if>
						</#if>
					</#if>

					<#if (map.pb.pageNo == map.pb.lastPageNo || map.pb.endNo <= 1)>
						<#if (map.pb.lastPageNo > map.pb.pageNo)>
                            <a class="num_cur prev" href="javascript:void(0);">${map.pb.lastPageNo}</a>
						</#if>
                        <a class="no_pages" href="javascript:void(0);">下一页</a>
					<#else>
						<#if ((map.pb.lastPageNo - map.pb.pageNo)>=3)>
							<#if (map.pb.lastPageNo>5)>
                                <a class="num" href="javascript:void(0);" onClick="changeSearch(this,'pageBeanShowPage','${map.pb.lastPageNo}');" >${map.pb.lastPageNo}</a>
							</#if>
						</#if>
                        <a class="pg_next" href="javascript:void(0);" <#if (map.pb.nextPageNo>0)>onClick="changeSearch(this,'pageBeanShowPage','${map.pb.nextPageNo}');"</#if>>下一页</a>
					</#if>
                    </div>
                </div>


            <#else>
            <div style="padding:10px 0 20px 0;text-align:center;font-weight:bold;color:#9E9E9E;">
                <img src="${basePath}/images/loading_03.gif"/>
                <h2 style="margin-top:20px;color:#000;">沒有搜索到商品</h2>
                <h3>修改一下搜索条件说不定会有意外收获哦！</h3><br/>
                <p>大家正在搜：
                    <#if (topmap.hotsearch)?? && (topmap.hotsearch?size>0)>
                        <#list topmap.hotsearch as hots>
                            <#if (hots.sort==1)>
                                <#if (hots.keyword?length>7)>
                                    <a class="hot" href="javascript:" onclick="changeSearchKey(this);" style="color:red;">${(hots.keyword)?substring(0,7)}</a>&nbsp;&nbsp;
                                <#else>
                                    <a class="hot" href="javascript:" onclick="changeSearchKey(this);" style="color:red;">${(hots.keyword)!''}</a>&nbsp;&nbsp;
                                </#if>
                            </#if>
                        </#list>
                        <#list topmap.hotsearch as hots>
                            <#if (hots.sort>=2) && (hots.sort<=6)>
                                <#if (hots.keyword?length>7)>
                                    <a href="javascript:" onclick="changeSearchKey(this);" style="color:red;">${(hots.keyword)?substring(0,7)}</a>&nbsp;&nbsp;
                                <#else>
                                    <a href="javascript:" onclick="changeSearchKey(this);" style="color:red;">${(hots.keyword)!''}</a>&nbsp;&nbsp;
                                </#if>
                            </#if>
                        </#list>
                    </#if>
                </p>
                <p>您也去看看吧！</p>
            </div>
			</#if>
            </div><!--/goods_wp-->
        </div><!--/right_wp-->

        <div class="left_wp fl">

            <div class="left_box">
                <h3>浏览过的商品</h3>
                <ul class="show_list mt10">
				<#if (browserProduct.type==1)>
					<#if browserProduct??>
						<#list browserProduct.browHist as product>
							<#if (product?? && product_index &lt; 4)>
                                <li>
                                    <div class="browse_img"><a alt="${product.goods.goodsName!''}" title="${product.goods.goodsName!''}" target="_blank" href="${basePath}/item/${product.goodsId}.html"><img class="lazy" alt="${product.goods.goodsName!''}" title="${product.goods.goodsName!''}" data-original="${product.goods.goodsImg!''}"  width="120" height="120" /></a></div>
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
                                        <div class="browse_img"><a alt="${product.productName!''}" title="${product.productName!''}" target="_blank" href="${basePath}/item/${product.goodsInfoId}.html"><img class="lazy" alt="${product.productName!''}" title="${product.productName!''}" data-original="${product.goodsInfoImgId!''}"  width="182" height="182" /></a></div>
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
    <a class="backtotop" href="javascript:"><em>返回顶部</em><b></b></a>
</div><!--/side_tools-->
<script type="text/javascript" src="${basePath}/js/jquery.slides.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/goods_compare.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/goods_param_filter.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/new_list_common.js"></script>
<#--<script type="text/javascript" src="${basePath}/js/goods/goods_comm.js"></script>-->
<script>
    $(function(){
        var keyWorlds=$("#keyWorlds").val();
        if(keyWorlds!=null){
            $(".hot").parent().find("a").each(function(){
                $(this).removeClass("hot");
                if($(this).html()==keyWorlds){
                    $(this).addClass("hot");
                }
            })
        }
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
    /**$(document).ready(function(){
		$(".pro_sort").addClass("pro_sort_close");
		<!--$("#catelist").hide();-->
    var goods_name_wa_repl = $(".goods_name_wa_repl");
    var search_text = $(".orange").html().replace(" ","");
    for(var i =0;i<search_text.length;i++){
        for(var j=0;j<goods_name_wa_repl.length;j++){
            var index=$(goods_name_wa_repl[j]).html().indexOf(search_text[i].trim());
            var ltIndex=$(goods_name_wa_repl[j]).html().indexOf("<");
            var gtIndex=$(goods_name_wa_repl[j]).html().indexOf(">");
            if(index<ltIndex || index>gtIndex){
                if(search_text[i].trim()!="" && $(goods_name_wa_repl[j]).html().indexOf(search_text[i].trim())!=-1){
                    $(goods_name_wa_repl[j]).html($(goods_name_wa_repl[j]).html().replace(search_text[i],'<span style="color:red">'+search_text[i]+'</span>'));
                }
            }
        }
    }
    })*/
</script>
</body>
</@htmlBody>