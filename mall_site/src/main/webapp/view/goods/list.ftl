<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#assign basePath=request.contextPath>
<title><#if map.nowcate.catSeoTitle??&&map.nowcate.catSeoTitle!=''&&map.nowcate.catSeoTitle!=' '>${map.nowcate.catSeoTitle}<#else>${map.nowcate.catName!''}</#if>列表页-${topmap.systembase.bsetName}</title>
<meta name="description" content="<#if map.nowcate.catSeoDesc??&&map.nowcate.catSeoDesc!=''&&map.nowcate.catSeoDesc!=' '>${map.nowcate.catSeoDesc!''}<#else>${map.nowcate.catName!''}</#if>-${topmap.seo.meteDes}">
<meta name="Keywords" content="<#if map.nowcate.catSeoKeyword??&&map.nowcate.catSeoKeyword!=''&&map.nowcate.catSeoKeyword!=' '>${map.nowcate.catSeoKeyword}<#else>${map.nowcate.catName!''}</#if>-${topmap.seo.meteKey}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<#if (topmap.systembase.bsetHotline)??>
	<link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
	<link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
</head>

<body>
<#--一引入头部 <#include "/index/topnew.ftl" /> -->
<#include "../index/newheader7.ftl">
		
    <div class="container">
        <div class="page_locate mt10">
            <#if map.nowcate.parentCat.catGrade?? &&  map.nowcate.parentCat.catGrade!=1 >
                <em><a href="${basePath}/list/${map.cate.catId}-${map.cate.catId}.html">${map.cate.catName}</a></em>
                &nbsp;&gt;&nbsp;
            </#if>
            <#if !map.nowcate.parentCat.catGrade?? || map.nowcate.parentCat.catGrade==1><em></#if>
            <#if map.nowcate.parentCat.catId==0>
                ${map.nowcate.parentCat.catName}
            <#else>
                <a href="${basePath}/list/${map.nowcate.parentCat.catId}-${map.cate.catId}.html">${map.nowcate.parentCat.catName}</a>
            </#if>
            <#if !map.nowcate.parentCat.catGrade?? || map.nowcate.parentCat.catGrade==1></em></#if>
            &nbsp;&gt;&nbsp;
            <span>${map.nowcate.catName}</span>
        </div><!--/page_locate-->

        <div class="content clearfix mt10">
            <div class="right_wp fr">
	            <div class="best_sellers">
	             	 <h3>热卖推荐</h3>
	                  <ul class="sellers_list clearfix mt10">
		                  <#list map.hotProduct as product>
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
                <div class="pro_filter mt10">
                    <div class="filterBox">
                        <dl id="selected_filter" <#if map.params?? && map.params?size &gt; 0><#else>style="display:none"</#if> class="filter_wp clearfix">
                        <dt>已选条件：</dt>
                        <dd>
                         <form action="${basePath}/list/${map.nowcate.catId}-${map.cate.catId}.html" id="searchForm" method="post">
	                            <div class="filterList"> 
	                                <ul class="clearfix">
	                                <input type="hidden" name="pageNo" class="pageNo" value="${map.pb.pageNo}">
	                                <input type="hidden" name="sort" class="list_sort" value="${map.searchBean.sort!''}">
	                                <input type="hidden" name="showStock" class="show_stock" value="${map.searchBean.showStock!''}">
	                                <#if map.params??>
	                                    <#list map.params as param>
	                                    	<#if param.type="brand">
	                                    		<li data-num="brand"><input type="hidden" name="params" value="b${param.value}"><a href="javascript:;">${param.title}：<em>${param.text}</em><b></b></a></li>
	                                    	</#if>
	                                    	<#if param.type="expand">
	                                    		<li data-num="e${param.parentId}"><input type="hidden" name="params" value="e${param.value}"><a href="javascript:;">${param.title}：<em>${param.text}</em><b></b></a></li>
	                                    	</#if>
	                                    	<#if param.type="spec">
	                                    				<li data-num="s${param.parentId}"><input type="hidden" name="params" value="s${param.value}"><a href="javascript:;">${param.title}：<em>${param.text}</em><b></b></a></li>
	                                    	</#if>
	                                    </#list>
	                                </#if>
			                        </ul>
			                     </div><!--/filterList-->
		                   </form>
		                   <a class="cancel_filter" href="javascript:;">全部撤销</a>
		                 </dd>
		                 </dl><!--/filter_wp-->
                        <#if map.type??&&map.type.brands?? && map.type.brands?size &gt;0>
	                        <dl class="filter_item clearfix" data-num="brand">
	                            <dt>品牌：</dt>
	                            <dd>
	                                <div class="filterList">
	                                    <ul class="clearfix">
		                                     <#list map.type.brands as typeBrand>
		                                        <li><a role="brand" param="${typeBrand.brand.brandId}" href="javascript:;">${typeBrand.brand.brandName}</a></li>
		                                     </#list>
	                                    </ul>
	                                </div><!--/filterList-->
	                                <a class="filter_op f_more none" href="javascript:;">更多<b></b></a>
	                                <a class="filter_op f_less none" href="javascript:;">收起<b></b></a>
	                            </dd>
	                        </dl><!--/filter_wp-->
                        </#if>
                        <#if map.type??&&map.type.expandParams??>
	                        <#list map.type.expandParams as expandParams>
		                        <dl class="filter_item clearfix" data-num="e${expandParams.expandparamId}">
		                            <dt>${expandParams.expandparamName}：</dt>
		                            <dd>
		                                <div class="filterList">
		                                    <ul class="clearfix">
		                                    	<#list expandParams.valueList as values>
		                                       		 <li><a role="eparam" param="${values.expandparamValueId}" href="javascript:;">${values.expandparamValueName}</a></li>
		                                        </#list>
		                                    </ul>
		                                </div><!--/filterList-->
		                                <a class="filter_op f_more none" href="javascript:;">更多<b></b></a>
		                                <a class="filter_op f_less none" href="javascript:;">收起<b></b></a>
		                            </dd>
		                        </dl><!--/filter_wp-->
		                    </#list>
	                    </#if>
	                    <#--<#if map.type??&& map.type.specVos??>
		                    <#list map.type.specVos as spec>
		                    <!-- 如果规格的显示方式是显示到列表页,就显示到页面 ,否则就不显示&ndash;&gt;
		                    	<#if spec.goodsSpec??&&spec.goodsSpec.specShowmode=="1">
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
                <#if  map.type??&&map.type.specVos?size+map.type.expandParams?size &gt;4>
	                <div class="filter_handle tc">
	                    <div class="show_more"><a href="javascript:;">展开<b></b></a></div>
	                    <div class="show_less"><a href="javascript:;">收起<b></b></a></div>
	                </div><!--/filter_handle-->
				</#if>
                <div class="operation_bar mt10">
                    <div class="operation_wp clearfix">
                        <div class="sorting_box fl clearfix">
                            <em>排序：</em>
                            <a val="2D" attr="${map.searchBean.sort}" class="change_sort  <#if map.searchBean.sort='22D' || map.searchBean.sort='2D'>checked</#if> <#if map.searchBean.sort='22D'> s_up<#elseif map.searchBean.sort='2D'> s_down</#if>" href="javascript:;"><span>销量</span></a>
                            <a val="1D" attr="${map.searchBean.sort}" class="change_sort  <#if map.searchBean.sort='11D' || map.searchBean.sort='1D'>checked</#if> <#if map.searchBean.sort='11D'> s_up<#elseif map.searchBean.sort='1D'> s_down</#if>" href="javascript:;"><span>价格</span></a>
                            <a val="4D" attr="${map.searchBean.sort}" class="change_sort  <#if map.searchBean.sort='44D' || map.searchBean.sort='4D'>checked</#if> <#if map.searchBean.sort='44D'>s_up<#elseif map.searchBean.sort='4D'>s_down</#if>" href="javascript:;"><span>人气</span></a>
                            <a val="3D" attr="${map.searchBean.sort}" class="change_sort  <#if map.searchBean.sort='33D' || map.searchBean.sort='3D'>checked</#if> <#if map.searchBean.sort='33D'>s_up<#elseif map.searchBean.sort='3D'>s_down</#if>" href="javascript:;"><span>上架时间</span></a>
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
                        <label class="m_check mr20 <#if map.searchBean.showStock='1'>checked</#if>"><input class="vm mr5 check_show_stock" type="checkbox">仅显示有货</label>
                        <!--<label class="m_check mr20"><input class="vm mr5" type="checkbox" />自营商品</label>-->
                    </div><!--/goodsTip-->
                </div><!--/operation_bar-->

                <div class="goods_wp mt20">
                <#if map.pb.list?? && (map.pb.list?size>0)>
                    <ul class="goodsList clearfix">
                    	<#list map.pb.list as product>
	                        <li class="goodsBox">
	                            <div class="gd_wp">
	                                <div class="g-img">
	                                    <a target="_blank" href="${basePath}/item/${product.goodsInfoId}.html" alt="${product.productName!''}" title="${product.productName!''}"><img class="lazy" alt="${product.productName!''}" title="${product.productName!''}" data-original="<#if  product.imageList?? && product.imageList?size &gt; 0 >${product.imageList[0].imageBigName!''}</#if>"  width="226" height="226" /></a>
	                                </div><!--/g-img-->
	                                <div class="g-scroll mt20 clearfix">
	                                    <a class="g-scroll-prev disabled" href="javascript:;"></a>
	                                    <div class="g-scroll-wrap">
	                                        <ul class="clearfix">
	                                        	<#list product.imageList as image>
	                                            	<li data-big="${image.imageBigName!''}"><a href="javascript:;"><img alt="${product.productName!''}" title="${product.productName!''}" src="${image.imageThumName!''}" /></a></li>
	                                            </#list>
	                                        </ul>
	                                    </div><!--/g-scroll-wrap-->
	                                    <a class="g-scroll-next" href="javascript:;"></a>
	                                </div><!--/g-scroll-->
	                                <p class="g-name mt5"><a target="_blank" href="${basePath}/item/${product.goodsInfoId}.html" alt="${product.productName!''}" title="${product.productName!''}">${product.productName!''}</a></p>
	                                <div class="clearfix mt5 lh200">
	                                    <span class="g-price fl">¥<#if vip?? && vip=="1"><#if product.wareVipPrice??> ${product.wareVipPrice?string('0.00')}</#if><#else><#if product.warePrice??> ${product.warePrice?string('0.00')}</#if></#if></span>
	                                     <#if product.isThird??&&product.isThird ==1>
	                                        <span class="<#if product.goodsInfoStockThird &gt;0><#else>no-goods</#if> fr">
	                                    		<#if product.goodsInfoStockThird &gt;0>
	                                    			有货
	                                    		<#else>
	                                    			无货
	                                    		</#if>
										   </span>
	                                    <#else>
	                                    	  <span class="<#if product.goodsInfoStock &gt;0><#else>no-goods</#if> fr">
	                                    		<#if product.goodsInfoStock &gt;0>有货<#else>无货</#if>
	                                    	  </span>
	                                   	</#if>
	                                </div>
	                                <div class="good-operation tc mt15">
	                                	<#if product.isThird ==1>
	                                    		 <a class="add_shop_cart" product_id="${product.goodsInfoId}" product_stock="${product.goodsInfoStockThird}" distinct_id="${map.distinctId}" href="javascript:;">加入购物车</a>
	                                    	<#else>
	                                    		 <a class="add_shop_cart" product_id="${product.goodsInfoId}" product_stock="${product.goodsInfoStock}" distinct_id="${map.distinctId}" href="javascript:;">加入购物车</a>
	                                    	</#if>
	                                    <a class="cllect_btn" product_id="${product.goodsInfoId}" href="javascript:;">收藏</a>
	                                    <a href="javascript:;"><label class="m_check compare" id="compare${product.goodsInfoId}" product_id="${product.goodsInfoId}">对比</label></a>
	                                </div><!--/good-operation-->
	                            </div><!--/gd_wp-->
	                            <div class="shop_name tc"><a href="javascript:;"><#if product.thirdId='0'>${topmap.systembase.bsetName!''}<#else>${product.thirdName!''}</#if></a></div>
	                        </li><!--/goodsBox-->
	                     </#list>
                    </ul><!--/goodsList-->

                    	<div class="pages mt10 tr">
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
			                        <a class="pg_prev no_pages" href="javascript:;">上一页</a>
			                    <#else>
			                    	<a class="pg_prev changePages" pages="${map.pb.prePageNo}" href="javascript:;">上一页</a>
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
							               		<a class="cur" href="javascript:;">${item}</a>
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
							    	 	<a class="cur" href="javascript:;">${map.pb.lastPageNo}</a>
							         </#if>
							         	<a class="pg_next no_pages" href="javascript:void(0);">下一页</a>
								<#else>
									 <#if ((map.pb.lastPageNo - map.pb.pageNo)>=3)>
									 	<#if (map.pb.lastPageNo>5)>
									 		<a class="pg_next changePages" pages="${map.pb.lastPageNo}" href="javascript:;">${map.pb.lastPageNo}</a>
									 	</#if>
									 </#if>
									 <a class="pg_next changePages"  pages="${map.pb.nextPageNo}"  href="javascript:void(0);">下一页</a>
								</#if>
                  	  </div><!--/pages-->
                  	 <#else>
							<center>暂无商品</center>	                    	
                     </#if>
                </div><!--/goods_wp-->
            </div><!--/right_wp-->

            <div class="left_wp fl">
                <div class="sortList">
                	<#list map.cate.cateVos as cate>
	                    <div class="sortItem <#if cate.catId==map.nowcate.catParentId || cate.catId==map.nowcate.catId>current</#if>">
	                        <h3><b></b><a href="${basePath}/list/${cate.catId}-${map.cate.catId}.html">${cate.catName}</h3>
	                        <ul class="clearfix">
	                        	<#list cate.cateVos as cat>
	                           		<li <#if map.nowcate.catId==cat.catId>class='cur'</#if> >
	                           			<a alt="${cat.catName}" title="${cat.catName}" href="${basePath}/list/${cat.catId}-${map.cate.catId}.html">${cat.catName}</a>
	                           		</li>
	                            </#list>
	                        </ul>
	                    </div><!--/sortItem-->
                    </#list>
                </div><!--/sortList-->

                <div class="left_box">
                    <h3>浏览<em>${map.nowcate.catName}</em>最终购买</h3>
                    <ul class="show_list mt10">
                    	<#list map.finalBuy as product>
	                        <li>
	                            <div class="browse_img"><a alt="${product.product.goodsInfoName!''}" title="${product.product.goodsInfoName!''}" target="_blank" href="${basePath}/item/${product.product.goodsInfoId}.html"><img class="lazy" alt="${product.product.goodsInfoName!''}" title="${product.product.goodsInfoName!''}" data-original="${product.product.goodsInfoImgId!''}"  width="182" height="182" /></a></div>
	                            <p class="browse_name mt5"><span>${product.precent}%会买：</span><a alt="${product.product.goodsInfoName!''}" title="${product.product.goodsInfoName!''}" target="_blank" href="${basePath}/item/${product.product.goodsInfoId}.html">${product.product.goodsInfoName!''}</a></p>
	                            <p class="browse_price mt5">¥ <#if vip?? && vip=="1">${product.product.goodsInfoVipPrice?string("0.00")}<#else>${product.product.goodsInfoPreferPrice?string("0.00")}</#if></p>
	                        </li>
                        </#list>
                    </ul><!--/show_list-->
                </div><!--/left_box-->
				<#if map.hotSales??>
	                <div class="left_box">
	                    <h3>热销排行榜</h3>
	                    <ul class="ranking_list mt10">
	                    	<#list map.hotSales as product>
		                        <li>
		                            <a class="ranking_img fl" alt="${product.goodsInfoName!''}" title="${product.goodsInfoName!''}" target="_blank" href="${basePath}/item/${product.goodsInfoId}.html"><img class="lazy" alt="${product.goodsInfoName!''}" title="${product.goodsInfoName!''}" data-original="${product.goodsInfoImgId!''}"  width="76" height="76" /></a>
		                            <div class="ranking_info fl ml10">
		                                <a class="ranking_name" alt="${product.goodsInfoName!''}" title="${product.goodsInfoName!''}" target="_blank" href="${basePath}/item/${product.goodsInfoId}.html">${product.goodsInfoName!''}</a>
		                                <p class="browse_price mt5">¥ <#if vip?? && vip=="1">${product.goodsInfoVipPrice?string("0.00")}<#else>${product.goodsInfoPreferPrice?string("0.00")}</#if></p>
		                            </div><!--/ranking_info-->
		                        </li>
	                        </#list>
	                    </ul><!--/ranking_list-->
	                </div><!--/left_box-->
				</#if>
                <div class="left_box">
                    <h3>浏览过的商品</h3>
                    <ul class="show_list mt10">
                       <#if map??&&map.browserProduct??&&(map.browserProduct.type==1)>
	                	<#if map.browserProduct.browHist??>
		                	<#list map.browserProduct.browHist as product>
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
			       		<#if map.browserProduct??>
			       			<#if map.browserProduct.productList??>
			                	<#list map.browserProduct.productList as product>
			                		<#if (product?? && product_index &lt; 4)>
						             	 <li>
				                            <div class="browse_img"><a alt="${product.productName!''}" title="${product.productName!''}" target="_blank" href="${basePath}/item/${product.goodsInfoId}.html"><img class="lazy" alt="${product.productName!''}" title="${product.productName!''}" data-original="<#if  product.imageList?? && product.imageList?size &gt; 0 >${product.imageList[0].imageBigName!''}</#if>"  width="182" height="182" /></a></div>
				                            <p class="browse_name mt5"><a alt="${product.productName!''}" title="${product.productName!''}" target="_blank" href="${basePath}/item/${product.goodsInfoId}.html">${product.productName!''}</a></p>
				                            <p class="browse_price mt5">¥ <#if vip?? && vip=="1">${product.goodsInfoVipPrice?string("0.00")}<#else>${product.goodsInfoPreferPrice?string("0.00")}</#if></p>
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
	
	
	<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery.slides.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery.lazyload.min.js"></script>
	<script type="text/javascript" src="${basePath}/js/goods/goods_compare.js"></script>
	<script type="text/javascript" src="${basePath}/js/index.js"></script>
	<script type="text/javascript" src="${basePath}/js/goods/new_list_common.js"></script>
	<script>
	$(document).ready(function(){
		$(".pro_sort").addClass("pro_sort_close");
	});
</script>
</body>
</html>