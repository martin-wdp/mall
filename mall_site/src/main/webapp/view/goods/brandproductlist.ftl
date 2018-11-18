<#include "../include/common.ftl">
<@htmlHead title="品牌商品列表页">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
</@htmlHead>
<@htmlBody>
<#--一引入头部 <#include "/index/topnew.ftl" /> -->	
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
		<div class="content clearfix pt10 container">
		<div class="right_wp fr">
		 <div class="best_sellers">
	             	 <h3>热卖推荐</h3>
	             	 <#if map.hotSales??>
	                  <ul class="sellers_list clearfix mt10">	                  
		                  <#list map.hotProduct as product>
			                  <li>
			                     <a class="sellers_img fl" target="_blank" alt="${product.goodsInfoName}" title="${product.goodsInfoName}" href="${basePath}/item/${product.goodsInfoId}.html"><img class="lazy" title="${product.goodsInfoName}" alt="${product.goodsInfoName}" data-original="${product.goodsInfoImgId!''}"  width="90" height="90" /></a>
			                     <div class="sellers_info fl ml5">
			                     <p class="sellers_name"><a target="_blank" href="${basePath}/item/${product.goodsInfoId}.html" title="${product.goodsInfoName}" alt="${product.goodsInfoName}">${product.goodsInfoName}</a></p>
			                     <p class="sellers_price">特价：<em>¥ ${product.goodsInfoPreferPrice?string("0.00")}</em></p>
			                         <a class="sellers_btn" target="_blank" href="${basePath}/item/${product.goodsInfoId}.html">立即抢购</a>
			                      </div><!--/sellers_info-->
			                   </li>
		                   </#list>
	                    </ul><!--/sellers_list-->
	               </#if>
	                </div><!--/best_sellers-->
                
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
	                                    <span class="g-price fl">¥<#if product.warePrice??> ${product.warePrice?string('0.00')}</#if></span>
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
	                                    		<#if product.goodsInfoStock &gt;0>${product.goodsInfoStock}有货<#else>${product.goodsInfoStock}无货</#if>
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
	                            <#--<div class="shop_name tc"><a href="javascript:;"><#if product.thirdId='0'>${topmap.systembase.bsetName!''}<#else>${product.thirdName!''}</#if></a></div>-->
	                        </li><!--/goodsBox-->
	                     </#list>
                    </ul><!--/goodsList-->
						<form action="${basePath}/brandProductsList/${map.brandId}.html" id="searchForm" method="post">	                            
	                                <input type="hidden" name="pageNo" class="pageNo" value="${map.pb.pageNo}">

	                    </form>
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
                <div class="left_box">
                    <h3>浏览<em><#--${map.nowcate.catName}--></em>最终购买</h3>
                    <#if map.finalBuy ??>
                    <ul class="show_list mt10">
                    	<#list map.finalBuy as product>
	                        <li>
	                            <div class="browse_img"><a alt="${product.product.goodsInfoName!''}" title="${product.product.goodsInfoName!''}" target="_blank" href="${basePath}/item/${product.product.goodsInfoId}.html"><img class="lazy" alt="${product.product.goodsInfoName!''}" title="${product.product.goodsInfoName!''}" data-original="${product.product.goodsInfoImgId!''}"  width="182" height="182" /></a></div>
	                            <p class="browse_name mt5"><span>${product.precent}%会买：</span><a alt="${product.product.goodsInfoName!''}" title="${product.product.goodsInfoName!''}" target="_blank" href="${basePath}/item/${product.product.goodsInfoId}.html">${product.product.goodsInfoName!''}</a></p>
	                            <p class="browse_price mt5">¥ ${product.product.goodsInfoPreferPrice?string("0.00")}</p>
	                        </li>
                        </#list>
                    </ul><!--/show_list-->
                    </#if>
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
		                                <p class="browse_price mt5">¥ ${product.goodsInfoPreferPrice?string("0.00")}</p>
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
				                            <p class="browse_price mt5">¥ ${product.goodsInfoPreferPrice?string("0.00")}</p>
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
	<script type="text/javascript" src="${basePath}/js/index.js"></script>
	<script type="text/javascript" src="${basePath}/js/goods/new_list_common.js"></script>
</@htmlBody>