<#include "../include/common.ftl">
<@htmlHead title="${(map.nowcate.catName)!''}列表页">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/thirdindex/style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/index_two/css/header.css" />
<style type="text/css">
    .pss_search{padding:12px 10px; border:1px solid #dfdfdf; background:#eeeeee;margin-top:20px; }
    .pss_s_b{width:200px; color:#999; background:#fff; height:24px; line-height:24px; padding:3px 0px; text-indent:5px; font-size:14px; border:solid 1px #dfdfdf;}
    .pss_search button{ border:none; width:73px; font-size:14px; font-weight:bold; letter-spacing:4px; cursor:pointer; line-height:32px; height:32px; overflow:hidden; background:#dd2b2f; margin-left:10px; border-radius:3px; color:#fff;}
</style>
</@htmlHead>
<@htmlBody>
<#if (topmap.temp)??>
    <#if (topmap.temp.tempId==1)>
        <#include "../index/thirdtopnew.ftl">
    <#elseif (topmap.temp.tempId==3)>
        <#include "../index/thirdnewheader.ftl">
    <#elseif (topmap.temp.tempId==12)>
        <#include "../index/newheader7.ftl">
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
        <#include "../index/thirdnewheader3.ftl">
    </#if>
</#if>
	<#include "storeHead.ftl">	
    <div class="container">
        <div class="pss_search">
	        <form action="${basePath}/storegoodslist/${map.thirdId}.html" id="searchForm" method = "POST">
	                   <input type="hidden" name="pageNo" class="pageNo" value="${data.pb.pageNo}">
		               <input type="hidden" name="sort" class="list_sort" value="${map.search.sort!''}">
		               <input type="hidden" name="showStock" class="show_stock" autocomplete="off" value="${map.search.showStock!''}">
	            <input type="text" class="pss_s_b" name="title" placeholder="请输入商品名称" value="${(map.search.title)!""}"/>
	            <button onclick="thirdSearch()">搜索</button>
	        </form>  
        </div>
        <div class="page_locate mt10">
            <em><a href="${basePath}/storegoodslist/${map.thirdId}.html">所有商品</a></em>
            <#if (map.nowcate.parentThirdCateVo)??>
            &nbsp;&gt;&nbsp;
            <a href="${basePath}/storegoodslist/${map.thirdId}-${map.nowcate.parentThirdCateVo.catId}.html">${(map.nowcate.parentThirdCateVo.catName)!''}</a>
            </#if>
            <#if (map.nowcate.catName)??>
            &nbsp;&gt;&nbsp;
            <span>${(map.nowcate.catName)!''}</span>
            </#if>
        </div><!--/page_locate-->

        <div class="content clearfix mt10">
            <div class="right_wp fr">
                <div class="operation_bar mt10">
                    <div class="operation_wp clearfix">
                        <div class="sorting_box fl clearfix">
                            <em>排序：</em>
                            <a val="2D" attr="${map.search.sort}" class="change_sort  <#if map.search.sort='22D' || map.search.sort='2D'>checked</#if> <#if map.search.sort='22D'> s_up<#elseif map.search.sort='2D'> s_down</#if>" href="javascript:;"><span>销量</span></a>
                            <a val="1D" attr="${map.search.sort}" class="change_sort  <#if map.search.sort='11D' || map.search.sort='1D'>checked</#if> <#if map.search.sort='11D'> s_up<#elseif map.search.sort='1D'> s_down</#if>" href="javascript:;"><span>价格</span></a>
                            <a val="4D" attr="${map.search.sort}" class="change_sort  <#if map.search.sort='44D' || map.search.sort='4D'>checked</#if> <#if map.search.sort='44D'>s_up<#elseif map.search.sort='4D'>s_down</#if>" href="javascript:;"><span>人气</span></a>
                            <a val="3D" attr="${map.search.sort}" class="change_sort  <#if map.search.sort='33D' || map.search.sort='3D'>checked</#if> <#if map.search.sort='33D'>s_up<#elseif map.search.sort='3D'>s_down</#if>" href="javascript:;"><span>上架时间</span></a>
                        </div><!--/sorting_box-->
                        <div class="op_pages fr">
                            <span class="mr10"><b>${data.pb.pageNo}</b>/${data.pb.lastPageNo}</span>
                            <#if (data.pb.pageNo==1)>
                            	<a class="op_prev no_pages" href="javascript:;">上一页</a>
                            <#else>
                            	<a class="op_prev changePages" pages="${data.pb.prePageNo}" href="javascript:;">上一页</a>
                            </#if>
                            <#if (data.pb.lastPageNo > data.pb.pageNo)>
                           		 <a class="op_next  changePages" pages="${data.pb.nextPageNo}" href="javascript:;">下一页</a>
                            <#else>
							     <a class="op_next no_pages" href="javascript:void(0);">下一页</a>
							</#if>
                        </div><!--/op_pages-->
                        <span class="goods_num fr">共<b>${data.pb.rows}</b>个商品</span>
                    </div><!--/operation_wp-->
                    <div class="goodsTip ml15"> 
                        <label class="m_check mr20 <#if map.search.showStock='1'>checked</#if>"><input class="vm mr5 check_show_stock" type="checkbox">仅显示有货</label>
                        <!--<label class="m_check mr20"><input class="vm mr5" type="checkbox" />自营商品</label>-->
                    </div><!--/goodsTip-->
                </div><!--/operation_bar-->
                <div class="goods_wp mt20">
                <#if (data.pb.data)?? && (data.pb.data?size>0)>
                    <ul class="goodsList clearfix">
                    	<#list data.pb.data as product>
	                        <li class="goodsBox">
	                            <div class="gd_wp">
	                                <div class="g-img">
	                                    <a target="_blank" href="${basePath}/item/${product.goodsInfoId}.html" alt="${product.goodsInfoName!''}" title="${product.goodsInfoName!''}"><img class="lazy" alt="${product.goodsInfoName!''}" title="${product.goodsInfoName!''}" data-original="<#if  product.imgList?? && product.imgList?size &gt; 0 >${product.imgList[0].imageBigName!''}</#if>"  width="226" height="226" /></a>
	                                </div><!--/g-img-->
	                                <div class="g-scroll mt20 clearfix">
	                                    <a class="g-scroll-prev disabled" href="javascript:;"></a>
	                                    <div class="g-scroll-wrap">
	                                        <ul class="clearfix">
	                                        	<#list product.imgList as image>
	                                            	<li data-big="${image.imageBigName!''}"><a href="javascript:;"><img alt="${product.productName!''}" title="${product.productName!''}" src="${image.imageThumName!''}" /></a></li>
	                                            </#list>
	                                        </ul>
	                                    </div><!--/g-scroll-wrap-->
	                                    <a class="g-scroll-next" href="javascript:;"></a>
	                                </div><!--/g-scroll-->
	                                <p class="g-name mt5"><a target="_blank" href="${basePath}/item/${product.goodsInfoId}.html" alt="${product.productName!''}" title="${product.productName!''}">${product.productName!''}</a></p>
	                                <div class="clearfix mt5 lh200">
	                                    <span class="g-price fl">¥ ${product.goodsInfoPreferPrice?string('0.00')}</span>
	                                    <span class="<#if product.goodsInfoStock &gt;0><#else>no-goods</#if> fr"><#if product.goodsInfoStock &gt;0>有货<#else>无货</#if></span>
	                                </div>
	                                <div class="good-operation tc mt15">
	                                    <a class="add_shop_cart" product_id="${product.goodsInfoId}" product_stock="${product.goodsInfoStock}" distinct_id="${(map.distinctId)!''}" href="javascript:;">加入购物车</a>
	                                    <a class="cllect_btn" product_id="${product.goodsInfoId}" href="javascript:;">收藏</a>
	                                    <a href="javascript:;"><label class="m_check compare" id="compare${product.goodsInfoId}" product_id="${product.goodsInfoId}">对比</label></a>
	                                </div><!--/good-operation-->
	                            </div><!--/gd_wp-->
	                            <div class="shop_name tc"><a href="javascript:;">${product.thirdName!''}</a></div>
	                        </li><!--/goodsBox-->
	                     </#list>
                    </ul><!--/goodsList-->
                   </#if> 
                    <#if (data.pb.data)?? && (data.pb.data?size>0)>
                    	<div class="pages mt10 tr">
                    		<#if ((data.pb.pageNo-2)>0)>
									<#assign pageNo="${data.pb.pageNo-2}" />
								<#else>
									<#assign pageNo="${data.pb.firstPageNo}" />
								</#if>
								<#if ((data.pb.lastPageNo-1)>0)>
									<#assign endNo="${data.pb.lastPageNo-2}" />
								<#else>
									<#assign endNo="1" />
								</#if>
	                        	<#if (data.pb.pageNo==1)>
			                        <a class="pg_prev no_pages" href="javascript:;">上一页</a>
			                    <#else>
			                    	<a class="pg_prev changePages" pages="${data.pb.prePageNo}" href="javascript:;">上一页</a>
			                    </#if>
			                    
			                    <#list pageNo?number .. data.pb.endNo as item>
										<#if (item_index<=4)>
							               <#if (item=data.pb.pageNo)>
							               		<a class="cur" href="javascript:;">${item}</a>
							               <#else>
							               		<a class="changePages" pages="${item}" href="javascript:;">${item}</a>
							               </#if>
					            		 </#if>
								</#list>
								<#if data.pb.pageNo!=data.pb.lastPageNo>
								     <#if ((data.pb.lastPageNo-data.pb.pageNo)>3) >
								     	<#if (data.pb.lastPageNo>5)>
								           ...
								        </#if>
								     </#if>
							    </#if>
							     <#if (data.pb.pageNo == data.pb.lastPageNo || data.pb.endNo <= 1)>
							    	 <#if (data.pb.lastPageNo > data.pb.pageNo)>
							    	 	<a class="cur" href="javascript:;">${data.pb.lastPageNo}</a>
							         </#if>
							         	<a class="pg_next no_pages" href="javascript:void(0);">下一页</a>
								<#else>
									 <#if ((data.pb.lastPageNo - data.pb.pageNo)>=3)>
									 	<#if (data.pb.lastPageNo>5)>
									 		<a class="pg_next changePages" pages="${data.pb.lastPageNo}" href="javascript:;">${data.pb.lastPageNo}</a>
									 	</#if>
									 </#if>
									 <a class="pg_next changePages"  pages="${data.pb.nextPageNo}"  href="javascript:void(0);">下一页</a>
								</#if>
                  	  </div><!--/pages-->
                  	 <#else>
							<center>暂无商品</center>	                    	
                     </#if>
                </div><!--/goods_wp-->
            </div><!--/right_wp-->

            <div class="left_wp fl">
                <div class="sortList">
                <#if map.thirdCate??>
                	<#list map.thirdCate as cate>
	                    <div class="sortItem <#if (map.nowcate.catParentId)??><#if cate.catId==map.nowcate.catParentId || cate.catId==map.nowcate.catId>current</#if></#if>">
	                       <a href="${basePath}/storegoodslist/${map.thirdId}-${cate.catId}.html">
	                         <h3><b></b>${cate.catName}</h3></a>
	                         <#if  cate.cateVos??>
		                        <ul class="clearfix">
		                        	<#list cate.cateVos as cat>
		                           		<li <#if (map.nowcate)??><#if map.nowcate.catId==cat.catId>class='cur'</#if></#if> >
		                           			<a alt="${cat.catName}" title="${cat.catName}" href="${basePath}/storegoodslist/${map.thirdId}-${cat.catId}-${cate.catId}.html">
		                           			${cat.catName}</a>
		                           		</li>
		                            </#list>
		                        </ul>
	                        </#if>
	                    </div><!--/sortItem-->
                    </#list>
                 </#if>
                </div><!--/sortList-->
            </div><!--/left_wp-->       
        </div><!--/content-->
    </div><!--/container-->
    	
     <!-- 对比页面 -->
	<#include "../goods/compare_box.ftl">
	<!-- 提示框-->
	<#include "../infotips.ftl">
	
    <#include "storeBottom.ftl">
    <div class="side_tools">
        <a class="backtotop" href="javascript:;"><em>返回顶部</em><b></b></a>
    </div><!--/side_tools-->
    <script type="text/javascript" src="${basePath}/js/jquery.slides.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery.lazyload.min.js"></script>
	<script type="text/javascript" src="${basePath}/js/index.js"></script>
	<script type="text/javascript" src="${basePath}/js/goods/new_list_common.js"></script>
	<script type="text/javascript" src="${basePath}/js/goods/goods_compare.js"></script>
	<script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
	<script>
	$(document).ready(function(){
		$(".pro_sort").addClass("pro_sort_close");
	});
	
	function thirdSearch(){
		var search=$.trim($(".pss_s_b").val());
		if(search==""){
			$(".pss_s_b").val($(".pss_s_b").attr("placeholder"));
		}else if(search.length>100){
			//限制长度，过长截取
			$(".pss_s_b").val(search.substring(0,100));
		}
	     $(".pageNo").val("1");
	     $("#searchForm").submit();
	}
	
	 /*收藏店铺*/
    $(".collectstore").click(function(){
    	$.post("${basePath}/addcollectionseller.htm?collectionThirdId=${map.thirdId}",function(data){
    		if(data=="2"){
    			/*初始化弹框样式*/
    			$(".info_tip_content2").html("您已经收藏过该商家！");
    			$(".info_tip_img2").attr("src","../images/collect.png");
    			$(".info_tip_cancel2").attr("href","javascript:;").hide();
    			$(".info_tip_submit2").attr("href","javascript:;").show().html("确定").unbind("click");
    			$(".info_tip_submit2").click(function(){
    				cls();
    			});
    			dia(2);
    		}else if(data=="3"){
    			/*初始化弹框样式*/
    			$(".info_tip_content2").html("是否跳转到登陆页?");
    			$(".info_tip_img2").attr("src","../images/index_ques.png");
    			$(".info_tip_cancel2").attr("href","javascript:;").html("取消").show();
    			$(".info_tip_submit2").attr("href","../login.html").show().html("确定").unbind("click");
    			$(".info_tip_cancel2").click(function(){
    				cls();
    			});
    			dia(2);
    		}else{
    			/*初始化弹框样式*/
    			$(".info_tip_content2").html("恭喜您收藏成功！");
    			$(".info_tip_img2").attr("src","../images/collect.png");
    			$(".info_tip_cancel2").attr("href","javascript:;").hide();
    			$(".info_tip_submit2").attr("href","javascript:;").show().html("确定").unbind("click");
    			$(".info_tip_submit2").click(function(){
    				cls();
    			});
    			dia(2);
    		}
    	});
    });
        $(".extra_info").hide(); 
			$(".third_store").hover(function() {
				$(".extra_info").show();
			}, function() {
				$(".extra_info").hide();
			});
</script>
</@htmlBody>