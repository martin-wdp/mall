<#include "../include/common.ftl">
<@htmlHead title='${map.searchBean.title}搜索结果-${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/contrast.css" />
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
<div class="container">
        <div class="location">
        	<a target="_blank" href="${basePath}/index.html">全部结果</a><span>&gt;</span>
            <span>"${map.searchBean.title}"</span>
        </div>
        
        <div class="right fr">
        	<div class="goods_area">
                <div class="search_title mb20">
                	<p class="f14">"<b class="orange">${map.searchBean.title}</b>"的搜索结果</p>
                </div>
                <div class="sort_area">
                    <div class="page_top" style="display:none">
                    	<div class="fr choose_area">
                        	<a class="choose_btn" href="javascript:void(0);">请选择</a>
                            <div class="choose_city">
                            	<div class="locate_lv">
                                	<a href="javascript:void(0)">江苏</a>
                                    <a href="javascript:void(0)">安徽</a>
                                    <a href="javascript:void(0)">浙江</a>
                                    <a href="javascript:void(0)">内蒙古</a>
                                    <a href="javascript:void(0)">山东</a>
                                    <a href="javascript:void(0)">河南</a>
                                    <a href="javascript:void(0)">河北</a>
                                    <a href="javascript:void(0)">江西</a>
                                    <a href="javascript:void(0)">湖北</a>
                                </div>
                            </div>
                        </div>
                        <span>库存：</span>
                    </div>
                    <dl class="order">
                        <dt>排序：</dt>
                        <dd <#if map.searchBean.sort='22D' || map.searchBean.sort='2D'>class="selected"</#if>>
                        	<a <#if map.searchBean.sort='22D'>class="sort_up"<#elseif map.searchBean.sort='2D'>class="sort_down"</#if> href="javascript:void(0);" onClick="changeSearch(this,'searchSort','22D');">销量</a>
                        </dd>
                        <dd <#if map.searchBean.sort='11D' || map.searchBean.sort='1D'>class="selected"</#if> >
                        	<a <#if map.searchBean.sort='11D'>class="sort_up"<#elseif map.searchBean.sort='1D'>class="sort_down"</#if> href="javascript:void(0);" onClick="changeSearch(this,'searchSort','11D');">价格</a>
                        </dd>
                        <dd <#if map.searchBean.sort='44D' || map.searchBean.sort='4D'>class="selected"</#if> >
                        	<a <#if map.searchBean.sort='44D'>class="sort_up"<#elseif map.searchBean.sort='4D'>class="sort_down"</#if> href="javascript:void(0);" onClick="changeSearch(this,'searchSort','44D');">人气</a>
                        </dd>
                        <dd <#if map.searchBean.sort='33D' || map.searchBean.sort='3D'>class="selected"</#if> >
                       	    <a <#if map.searchBean.sort='33D'>class="sort_up"<#elseif map.searchBean.sort='3D'>class="sort_down"</#if> href="javascript:void(0);" onClick="changeSearch(this,'searchSort','33D');">上架时间</a>
                        </dd>
                    </dl>
                </div>
                
                	<!-- 保存查询参数 -->
					<form id="searchForm" method="post" action="${basePath}/goods/searchProduct.html">
								<input type="hidden" id="search_title" name="title" value="${map.searchBean.title}" />
								<input type="hidden" id="searchSort" name="sort" value="${map.searchBean.sort}"/>
								<input type="hidden" id="pageBeanShowSize" name="pageSize" value="${map.pb.pageSize}" />
								<input type="hidden" id="pageBeanShowPage" name="pageNo" value="${map.pb.pageNo}" />
					</form>
                
                <div class="goods_list">
                	<!-- 设置默认的购买数量 -->
                	<input type="hidden" class="product_buy_num" value="1" />
                	<#if map.pb.list??>
	                	<#list map.pb.list as product>
		                    <div class="goods_item">
		                        <ul>
		                            <li class="good_tips tip1"></li>
		                            <li class="img"><a title="${product.productName}" target="_blank" href="${basePath}/item/${product.goodsInfoId}.html"><img alt="${product.productName}" width="160px" height="160px" src="${product.goodsInfoImgId}" /></a></li>
		                            <li class="name" style="height:35px;">
		                            	<a title="${product.productName}" target="_blank" href="${basePath}/item/${product.goodsInfoId}.html">
		                            		<div class="goods_name_wa_repl">${product.productName}</div>
				                             <em><#if product.subtitle??>
				                             			<div class="goods_name_wa_repl">${product.subtitle}</div>
				                            	 </#if></em>
		                            	 </a>
		                             </li>
		                            <li class="price" style='font-family: "微软雅黑";'>￥${product.goodsInfoPreferPrice?string("0.00")}</li>
		                            <li class="comment"><a title="${product.productName}" target="_blank" href="${basePath}/item/${product.goodsInfoId}.html#comment"><i class="star star_<#if product.commentUtilBean??>${product.commentUtilBean.score?substring(0,1)}<#else>0</#if>"></i>已有<#if product.commentUtilBean??>${product.commentUtilBean.count}<#else>0</#if>人评价</a></li>
		                            <li class="btns">
		                                <a class="buy_now add_shop_cart" href="javascript:void(0);" data-role="${product.goodsInfoId}">加入购物车</a>
		                                <a href="javascript:;" class="compare_target"><label class="m_check compare" id="compare${product.goodsInfoId}" product_id="${product.goodsInfoId}">对比</label></a>
		                            </li>
		                        </ul>
		                    </div>
	                   	</#list>
                   	</#if>
                    <div class="cb"></div>
                    <div class="paging_area">
                    	<#if (map.pb.rows &gt; 0)>
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
	                        		<span class="prev_null">&lt;&nbsp;上一页</span>
	                            <#else>
	                            	<a class="prev" <#if (map.pb.nextPageNo>0)>onClick="changeSearch(this,'pageBeanShowPage','${map.pb.prePageNo}');"</#if> href="javascript:;">&lt;&nbsp;上一页</a>
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
							         	<a class="num_cur" href="javascript:void(0);">${map.pb.lastPageNo}</a>
							         </#if>
							         <a class="next_null" href="javascript:void(0);">下一页&nbsp;&gt;</a>
								<#else>
									 <#if ((map.pb.lastPageNo - map.pb.pageNo)>=3)>
									 	<#if (map.pb.lastPageNo>5)>
											 <a class="num" href="javascript:void(0);" onClick="changeSearch(this,'pageBeanShowPage','${map.pb.lastPageNo}');" >${map.pb.lastPageNo}</a>
										</#if>
									 </#if>
									 <a class="" href="javascript:void(0);" <#if (map.pb.nextPageNo>0)>onClick="changeSearch(this,'pageBeanShowPage','${map.pb.nextPageNo}');"</#if>>下一页&nbsp;&gt;</a>
								</#if>
	                        </div>
	                     <#else>
	                     	<center>暂无商品</center>
	                     </#if>
                    </div>
                </div>
            </div>
        </div>
        <div class="left fl">
            <div class="border">
            	<h2>最近浏览</h2>
                <ul>
                	<#if (map.browserProduct.type==1)>
	                	<#if map.browserProduct??>
		                	<#list map.browserProduct.browHist as product>
		                		<#if product??>
					             	<li>
					                    <div class="small_good">
					                        <p class="img"><a href="${basePath}/item/${product.goodsId}.html" target="_blank" title="${product.goods.goodsName}"><img width="50px" height="50px" title="${product.goods.goodsName}" alt="${product.goods.goodsName}" src="<#if product.goods.goodsImg??>${product.goods.goodsImg}</#if>" /></a></p>
					                        <p class="name"><a href="${basePath}/item/${product.goodsId}.html" target="_blank" title="${product.goods.goodsName}"><#if product.goods.goodsName?length gt 15>${product.goods.goodsName?substring(0,15)}<#else>${product.goods.goodsName}</#if></a></p>
					                        <p class="price" style='font-family: "微软雅黑";'>￥${product.goods.goodsPrice?string("0.00")}</p>
					                        <div class="cb"></div>
					                    </div>
					                </li>
				                </#if>
			               </#list>
			           </#if>
			       <#else>
			       		<#if map.browserProduct??>
			       			<#if map.browserProduct.productList??>
			                	<#list map.browserProduct.productList as product>
					             	<li>
					                    <div class="small_good">
					                        <p class="img"><a href="${basePath}/item/${product.goodsInfoId}.html" target="_blank" title="${product.productName}">
					                        <img width="50px" height="50px" title="${product.productName}" alt="${product.productName}" src="<#if product.goodsInfoImgId??>${product.goodsInfoImgId}</#if>" /></a></p>
					                        <p class="name"><a href="${basePath}/item/${product.goodsInfoId}.html" target="_blank" title="${product.productName}"><#if product.productName?length gt 15>${product.productName?substring(0,15)}<#else>${product.productName}</#if></a></p>
					                        <p class="price" style='font-family: "微软雅黑";'>￥${product.goodsInfoPreferPrice?string("0.00")}</p>
					                        <div class="cb"></div>
					                    </div>
					                </li>
				               </#list>
			               </#if>
			           </#if>
		           </#if>
                </ul>
            </div>
            <div class="pic_ad">
            	<a href="#"></a>
            </div>
        </div>
        <div class="cb"></div>
    </div>
<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
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
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/goods_comm.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/goods_compare.js"></script>
<script>
	$(document).ready(function(){
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
		$(".pro_sort").addClass("pro_sort_close");
	});
</script>
</@htmlBody>