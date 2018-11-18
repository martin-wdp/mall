<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <#assign basePath=request.contextPath>
	<title>店铺首页</title>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
	<link rel="stylesheet" type="text/css" href="${basePath}/css/thirdindex/style.css"/>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/index_two/css/header.css" />
    <style type="text/css">
        .third_search {}
        .third_search input{ width:148px; height: 28px; border: 1px solid #ddd; background: #fff; float: left; border-radius:4px 0px 0px 4px; text-indent:5px; color:#666;}
        .third_search button{ width: 60px; height: 30px; background: #b92b37; border:none; cursor: pointer; color: #fff; font-family: "微软雅黑"; line-height: 30px; text-align: center; font-size: 14px; float: left;}
        .third_key{ padding-top: 8px; font-family: "微软雅黑"; font-size: 12px; color: #fff;}
        .third_key a{ color: #fff; display: inline; padding-right: 5px;}
        .new_floor_title{ border-bottom: 2px solid #b92b37; position: relative; height: 20px;padding-top:20px;}
        .new_floor_title span{ display: block; position: absolute; width: 220px; height: 40px; background: #b92b37; line-height: 40px; text-align: center; color: #fff; font-family: "微软雅黑"; font-size: 18px; left: 490px; top: 20px;}
        .new_floor_det{ border: 6px solid #eeeeee; margin-top: 40px; }
        .new_floor_det ul li{ width: 289px; height: 323px; overflow:hidden; padding-right: 10px; float: left;}
        .new_floor_det ul li .img_det{ display: block; width: 289px; height: 289px; overflow: hidden; position: relative;}
        .new_floor_det ul li .guanzhu{ width: 254px; height: 30px; padding-top: 10px; background: #e2e1e1; position: absolute; bottom: 0px; left: 0px; color: #333; font-family: "微软雅黑"; font-size: 14px; padding-left: 35px; display: none;}
        .new_floor_det ul li .guanzhu a {color:#333;}
        .new_floor_det ul li .guanzhu a i{ float: left; padding-right: 5px; padding-left: 10px; cursor: pointer;}
        .sp-price{ color: #888888; font-size:14px; font-family: "Arial"; font-weight: bold; }
        .red{ color: #b92b37; font-size: 18px; }
        .page_left dl .cur{color: #eb6122;}
        .page_left .close dd{display:none;}
        .dia2{
            top: 304.5px;
            left: 491.5px;
        }
        .dia1{
            top: 304.5px;
            left: 491.5px;
        }
        .slidesjs-pagination li {float:none;  margin-left:10px;}
        .today_new{ width:65px; height: 20px; color: #fff; font-size: 12px; line-height: 20px; background: url(../images/newset.png) no-repeat; position: absolute; left: -2px; top: 15px; text-indent: 5px;}
        .today_bk{width:65px; height: 20px; color: #fff; font-size: 12px; line-height: 20px; background: url(../images/baok.png) no-repeat; position: absolute; left: -2px; top: 15px; text-indent: 5px;}
        .sy_search{ width: 172px; height: 30px; background: #fff; border-radius: 5px; margin-left: 20px; margin-top: 5px; float: left;}
        .sy_search b{ width: 24px; height: 22px; background: url(../images/sus.png) no-repeat left center; float: left; margin-top: 4px; margin-left: 8px;}
        .sy_search input{ width: 130px; border: none; height: 25px; color: #999999; font-family: "微软雅黑"; line-height: 25px; font-size: 14px; margin-top: 2px; float: left; margin-left: 5px;}
    </style>
</head>
<body>
		 <#if (topmap.temp)??>
            <#if (topmap.temp.tempId==1)>
                <#include "../index/thirdtopnew.ftl">
            <#elseif (topmap.temp.tempId==3)>
                <#include "../index/thirdnewheader.ftl">
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
                <#include "../index/thirdnewheader3.ftl">
            </#if>
        </#if>
	<#include "storeHead.ftl">
	<div class="third_slide" id="third_slide">
		<#if map.channelAdvs??>
		  <#list map.channelAdvs as adv>
			<a href="${(adv.adverHref)!''}" style="background:url(${(adv.adverPath)!''}) no-repeat center center;"></a>
		  </#list>
		</#if>
	</div>


	<div class="container clearfix">
	 <#if map.hotSale?? && (map.hotSale?size>0)>
		<ul class="lastest_list clearfix" style="width:1225px;">
			<#list map.hotSale as hotgoods>
			<li>
				<a href="${basePath}/item/${(hotgoods.goodsproductId)!''}" class="la_tu"><img src="<#if hotgoods.goodsproductImgsrc??>${(hotgoods.goodsproductImgsrc)?replace('!160','')}</#if>
				" width="213" height="213"/></a>
				<div class="gifts_details">
					<p class="pr clearfix gifts_sales">
						<span class="fr">
							¥${hotgoods.goodsproductPrice?string("0.00")}
						</span>
						<a href="${basePath}/item/${(hotgoods.goodsproductId)!''}" title="${(hotgoods.goodsproductName)!''}" target="_blank" class="fl f14" style="color:#666;">
						    <#if (hotgoods.goodsproductName?length>14)>
	                        	${((hotgoods.goodsproductName)!'')?substring(0,14)}
                        	<#else>
	                        	${(hotgoods.goodsproductName)!''}
                        	</#if>
						</a>
						<!--<a href="##" class="gif_delete"><i></i>删除</a>-->
					</p>
					<!--<div class="gifts_bottom clearfix">
						<span class="fl title"><a href="${basePath}/item/${(hotgoods.goodsproductId)!''}" title="${(hotgoods.goodsproductName)!''}" target="_blank">
						   <#if (hotgoods.goodsproductName?length>20)>
	                        	${((hotgoods.goodsproductName)!'')?substring(0,20)}
                        	<#else>
	                        	${(hotgoods.goodsproductName)!''}
                        	</#if>
                       </a></span>
						<p class="rec_info mt10" title="${(hotgoods.temp3)!''}">
                        	<#if (((hotgoods.temp3)!'')?length>20)>
	                        	${((hotgoods.temp3)!'')?substring(0,20)}
                        	<#else>
	                        	${(hotgoods.temp3)!''}
                        	</#if>
                        </p>
					</div>-->
				</div>
				<span class="today_new">热销推荐1</span>
			</li>
			</#list>
		</ul>
	</#if>
	</div>

	
<div class="container clearfix pt20">
 <#if (map.floor.floorList)?? && (map.floor.floorList?size > 0)>
		<div class="new_floor pb20">
			<#list map.floor.floorList as floors>
			<div class="new_floor_title pr">
				<span>${floors.storeyName!''}</span>
				<a class="fr" style="font-size:12px;font-weight: bold;" href="${basePath}/storegoodslist/${map.thirdId}.html">更多>></a>
			</div><!--new_floor_title-->
			<div class="new_floor_det">
				<ul class="clearfix gg_list" style="width:1200px;">
				
				<#if (floors.indexGoodsList)?? &&(floors.indexGoodsList?size > 0)>
					<#list floors.indexGoodsList as goods>
						<li>
							<div href="#" class="img_det"><a href="${basePath}/item/${goods.id}.html"><img src="<#if goods.urlpic??>${(goods.urlpic)?replace('!160','')}</#if>" width="289" height="289" alt="${(goods.name)!''}"/></a>
		                        <div class="guanzhu clearfix">
		                        	<!--<a href="${basePath}/item/${goods.id}.html" class="fl" target="_blank"><i><img src="${basePath}/images/guanzhu.png"/></i>关注</a>
		                        	<a href="#" class="fl"><i><img src="${basePath}/images/shop.png"/></i>购物车</a>-->
		                        	<a href="${basePath}/item/${goods.id}.html" class="fl" target="_blank"><i><img src="${basePath}/images/buy.png"/></i>立即购买</a>
		                        </div> 
							</div>
							<div class="clearfix sp-price pt10">
							<div class="fl" title="${(goods.name)!''}">
								<#if goods.name??>
									<#if goods.name?length gt 12>
	                        				${goods.name?substring(0,12)}
	                        			<#else>
	                        				${(goods.name)!''}
	                        			</#if>
	                        			</a>
								</#if>
									
								</div>
								<div class="fr">
									RMB:<span class="red">${goods.price}</span>
								</div>
							</div>
						</li>
					</#list>
					</#if>
			
				</ul>
			</div><!--new_floor_det-->
		  </#list>
		</div><!--new_floor-->
	</#if>


     <div class="third_floor pt10 clearfix">
         <div class="page_left fl">
         <#if map.thirdCate??>
             <div class="sub_cate mb10">
                 <div class="page_left fl">
                     <#assign indexs=0>
                     <#list map.thirdCate as cate>
                         <div class="sub_cate">
                             <#assign indexs=indexs?number+1>
                             <div class="fir_level"> <a href="javascript:void(0);" onclick="searchone(${map.thirdId},${cate.catId});">${cate.catName}</a></div>
                             <#if cate.cateVos??>
                                 <#list cate.cateVos as cat>
                                     <#assign indexs=indexs?number+1>
                                     <dl <#if (map.nowcate.catGrade==1 || map.nowcate.catGrade==0) && indexs==2>class="open" <#elseif map.nowcate??&& map.nowcate.catId!=0 &&(cat.catId==map.nowcate.catId || cat.catId==map.nowcate.catParentId  )>class="open"<#else>class="close" </#if>>
                                         <dt <#if (map.nowcate.catGrade==1 || map.nowcate.catGrade==0) && indexs==2>class="open" <#elseif map.nowcate??&& map.nowcate.catId!=0 &&(cat.catId==map.nowcate.catId || cat.catId==map.nowcate.catParentId  )>class="open"<#else>class="close" </#if> style="text-indent: 30px; font-size:14px;">
                                             <a alt="${cat.catName}" title="${cat.catName}" href="javascript:void(0);" onclick="searchtwo(${map.thirdId},${cat.catId},${cate.catId});">
                                             ${cat.catName}</a>
                                         </dt>
                                         <dd  <#if (map.nowcate.catGrade==1 || map.nowcate.catGrade==0) && indexs==2><#elseif map.nowcate??&& map.nowcate.catId!=0 &&(cat.catId==map.nowcate.catId || cat.catId==map.nowcate.catParentId  )><#else>style="display:none;" </#if>>
                                             <#if cate.cateVos??>
                                                 <#list cat.cateVos as ca>

                                                     <a alt="${ca.catName}" <#if map.nowcate?? &&  map.nowcate.catId==ca.catId>class='cur' </#if> title="${ca.catName}" href="javascript:void(0);" onclick="searchtwo(${map.thirdId},${ca.catId},${cat.catId});">
                                                     ${ca.catName}</a>
                                                 </#list>
                                             </#if>
                                         </dd>
                                     </dl>
                                 </#list>
                             </#if>
                         </div>
                     </#list>
                 </div><!--page_left-->
				<#--<#list map.thirdCate as cate>-->
		            <#--<dl>-->
		                <#--<dt>   <a href="javascript:void(0);" onclick="searchone(${map.thirdId},${cate.catId});">${cate.catName}</a></dt>-->
		                <#--<dd>-->
		                <#--<#if cate.cateVos??>-->
		                <#--<#list cate.cateVos as cat>-->
		                           		<#---->
		                   <#---->
		                   <#--<a alt="${cat.catName}" title="${cat.catName}" href="javascript:void(0);" onclick="searchtwo(${map.thirdId},${cat.catId},${cate.catId});">-->
		                  <#---->
		                           			<#--${cat.catName}</a>-->
		                 <#--</#list>   			-->
		                  <#--</#if>-->
		                <#--</dd>-->
		            <#--</dl>-->
		            <#---->
		            <#--</#list>-->
		        </div>
		        </#if>

			</div><!--page_left-->
			<div class="page_right fr">
				<div class="selecter mb10">
		        	<div class="row">
		            	<div class="locate fl">
		                	<strong>所有分类：</strong>
		                	
		                         <span>&gt;</span>
		                   		 
							    <a href="javascript:void(0);" onclick="searchAll(${map.thirdId})" >所有商品</a></em>
					            <#if (map.nowcate.parentThirdCateVo)?? && (map.nowcate.parentThirdCateVo.catName!="所有")>
					            &nbsp;&gt;&nbsp;
					            <a href="javascript:void(0);" onclick="searchone(${map.thirdId},${map.nowcate.parentThirdCateVo.catId});">${(map.nowcate.parentThirdCateVo.catName)!''}</a>
					            </#if>
                                 <#if (map.nowcate.catName)?? && (map.nowcate.parentThirdCateVo)?? && (map.nowcate.parentThirdCateVo.catName=="所有")>
                                      &nbsp;&gt;&nbsp;
                                     <a href="javascript:void(0);" onclick="searchone(${map.thirdId},${map.nowcate.catId});">${(map.nowcate.catName)!''}</a>
                                 <#elseif (map.nowcate.catName)?? && (map.nowcate.catName!="所有")>
                                     &nbsp;&gt;&nbsp;
                                     <span>${(map.nowcate.catName)!''}</span>
                                 </#if>

		                </div>
		                <div class="selecter_search fr">
		                 <form action="${basePath}/thirdstoreindex/${map.thirdId}.html" id="searchForm" method = "POST">
	                   <input type="hidden" name="pageNo" class="pageNo" value="${map.pb.pageNo}">
		               <input type="hidden" name="sort" class="list_sort" value="${map.search.sort!''}">
		               <input type="hidden" name="showStock" class="show_stock" autocomplete="off" value="${map.search.showStock!''}">
                             <input type="hidden" name="top" id="top"  value="${map.top!''}">
	            		 <input type="hidden" name="thirdId" id="thirdId"  value="${map.thirdId!''}">
	            		  <input type="hidden" name="cateId" id="cateId"  value="${map.search.cateId!''}">
	            		   <input type="hidden" name="parentCatId" id="parentCatId" value="">
	            		   
	            		<input class="selcter_sch_text" type="text" id="title" name="title" value="${(map.search.title)!''}"  placeholder="请输入商品名称">
		                <input class="selcter_sch_btn" type="submit" value="">
	            
	       				 </form>  
		                
		                </div>
		            </div>
		        	<div class="row">
		            	<div class="page_s fr">
		                	<span class="red">共${map.pb.rows}个商品</span>
		                    <span><em class="red">${map.pb.pageNo}</em>/${map.pb.totalPages}</span>
		                    
		                    <#if (map.pb.pageNo==1)>
                            	<a href="javascript:void(0);" class="page_prev disabled">上一页<i></i></a>
                            <#else>
                            	<a class="page_prev" pages="${map.pb.prePageNo}" href="javascript:void(0);" onclick="prevpage(${map.pb.prePageNo});">上一页</a>
                            </#if>
		                     <#if (map.pb.lastPageNo > map.pb.pageNo)>
                           		<a href="javascript:void(0);" class="page_next" pages="${map.pb.nextPageNo}" onclick="nextpage(${map.pb.nextPageNo})">下一页<i></i></a>
                            <#else>
							    <a href="javascript:void(0);" class="page_next disabled">下一页<i></i></a>
							</#if>
		                    
		                  
		                </div><!-- /page_s -->
		                <div class="sort fl">
		                	<dl>
		                    	<dt>排序：</dt>
		                         <dd class="<#if map.search.sort='22D' || map.search.sort='2D'>cur</#if> <#if map.search.sort='22D'> up<#elseif map.search.sort='2D'> down</#if>">
		                        	<i></i>
		                            <a val="2D" attr="${map.search.sort}" class="change_sort " href="javascript:;"><span>销量</span></a>
		                        </dd>
		                       <dd class="<#if map.search.sort='11D' || map.search.sort='1D'>cur</#if> <#if map.search.sort='11D'> up<#elseif map.search.sort='1D'> down</#if>">
		                        	<i></i>
		                            <a val="1D" attr="${map.search.sort}" class="change_sort  " href="javascript:;"><span>价格</span></a>
		                        </dd>
		                       <dd class=" <#if map.search.sort='44D' || map.search.sort='4D'>cur</#if> <#if map.search.sort='44D'> up<#elseif map.search.sort='4D'> down</#if>">
		                        	<i></i>
		                             <a val="4D" attr="${map.search.sort}" class="change_sort " href="javascript:;"><span>人气</span></a>
		                        </dd>
		                      <dd class="<#if map.search.sort='33D' || map.search.sort='3D'>cur</#if> <#if map.search.sort='33D'> up<#elseif map.search.sort='3D'> down</#if>">
		                        	<i></i>
		                             <a val="3D" attr="${map.search.sort}" class="change_sort  " href="javascript:;"><span>上架时间</span></a>
		                        </dd>
		                    </dl>
		                </div><!-- /sort -->
		            </div>
		           
		        </div><!--select-->
		          <div class="goodsTip ml15"> 
                        <label class="m_check mr20 <#if map.search.showStock='1'>checked</#if>"><input class="vm mr5 check_show_stock" type="checkbox">仅显示有货</label>
                        <!--<label class="m_check mr20"><input class="vm mr5" type="checkbox" />自营商品</label>-->
                   </div><!--/goodsTip-->
		         <#if (map.pb.list)?? && (map.pb.list?size>0)>
		         	<#list map.pb.list as product>
			        <div class="official_goods ">
			        	<div class="official_item">
			            	<a class="img" target="_blank" href="${basePath}/item/${product.goodsInfoId}.html" alt="${product.productName!''}" title="${product.productName!''}"><img class="lazy" alt="${product.productName!''}" title="${product.productName!''}" data-original="<#if  product.imageList?? && product.imageList?size &gt; 0 >${product.imageList[0].imageBigName!''}</#if>"  width="215" height="215" ></a>
			            	<p class="price">&yen; ${product.goodsInfoPreferPrice?string('0.00')}</p>
			                <p class="name">
			                	<a target="_blank" href="${basePath}/item/${product.goodsInfoId}.html">${product.productName!''}</a></p>
			               
			                <div class="cb"></div>
			               <!--<div class="rate_new"><span class="star star5 fl"></span><span class="blue fl ml5">已有2500人评价</span></div>-->
			                <div class="good-operation tc mt15">
			                	<a  class="add_cart_new add_shop_cart"  product_id="${product.goodsInfoId}" product_stock="${product.goodsInfoStock}" distinct_id="${(map.distinctId)!''}" href="javascript:;">加入购物车</a>
			                    <a  class="focus_on cllect_btn" product_id="${product.goodsInfoId}" href="javascript:;">关注</a>  
			                    <a href="javascript:;"><label class="m_check compare" id="compare${product.goodsInfoId}" product_id="${product.goodsInfoId}">对比</label></a>
			                </div>
			            </div>
			           </div>
			          </#list>
		           </#if>
      
		            <div class="cb"></div>		      
		           <!-- 对比页面 -->
				<#include "../goods/compare_box.ftl">
		        </div><!--official_goods-->
			</div>
		</div>
		
	
	<!-- 提示框-->
	<#include "../infotips.ftl">
	
    <div class="side_tools">
        <a class="backtotop" href="javascript:;"><em>返回顶部</em><b></b></a>
    </div><!--/side_tools-->
	<#include "storeBottom.ftl">
	
	
	<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${basePath}/js/jquery.slides.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery.lazyload.min.js"></script>
	<script type="text/javascript" src="${basePath}/js/index.js"></script>
	<script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
	<script type="text/javascript" src="${basePath}/js/default.js"></script>
	<script type="text/javascript" src="${basePath}/js/goods/goods_compare.js"></script>
	<script type="text/javascript" src="${basePath}/js/goods/new_list_common.js"></script>
         <script type="text/javascript">
             $(".sub_cate").each(function(){
                 $(this).find("dl").last().css("border-bottom","none")
             })

             $('.sub_cate dt').click(function(){
                 if($(this).attr('class') == 'open'){
                     $(this).removeClass('open');
                     $(this).prepend().addClass('close');
                     $(this).addClass('close').next().hide();
                 }
                 else{
                     $('.sub_cate dt[class=open]').removeClass('open').addClass('close').next().hide();;
                     $(this).addClass('open');
                     $(this).prepend().removeClass('close');
                     $(this).removeClass('close').next().show();
                 }
             });
             $(function(){
                 if($("#third_slide").length > 0 && $("#third_slide a").length > 1) {
                     $("#third_slide").slidesjs({
                         play: {
                             active: false,
                             effect: "fade",
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
                             effect: "fade"
                         }
                     });
                 };

                 $(".gg_list li").hover(function() {
                     $(this).find(".guanzhu").slideDown();
                 }, function() {
                     $(this).find(".guanzhu").slideUp();
                 });

                <#-- $(".compare").click(function() {
                     if ($(this).hasClass('on')) {
                         $(this).removeClass('on');
                         $(".compare_box").hide();
                     }
                     else{
                         $(this).addClass('on');
                         $(".compare_box").show();
                     }
                 });
                <#-- $(".compare_box h4").click(function() {
                     $(".compare_box").hide();
                 });-->
             })


             function win1(){
                 var _wd = $(window).width();
                 var _hd = $(window).height();
                 $(".git_dialog").css("top",(_hd - $(".git_dialog").height())/2).css("left",(_wd - $(".git_dialog").width())/2);

             };

             function dia1(n){
                 win();
                 $(".mask").fadeIn();
                 $(".dia"+n).fadeIn();
             };

             function cls1(){
                 $(".git_dialog").fadeOut();
                 $(".mask").fadeOut();
             };


             $(".extra_info").hide();
             $(".third_store").hover(function() {
                 $(".extra_info").show();
             }, function() {
                 $(".extra_info").hide();
             });
         </script>
	<script type="text/javascript">
         $(function(){
             $(document).scrollTop(parseFloat("${map.top}"));
              if($("#third_slide").length > 0 && $("#third_slide a").length > 1) {
			        $("#third_slide").slidesjs({
			            play: {
			                active: false,
			                effect: "fade",
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
			                effect: "fade"
			            }
			        });
			    };
			    
			     <#-- $(".compare").click(function() {
			    	if ($(this).hasClass('on')) {
			    		$(this).removeClass('on');
			    		$(".compare_box").hide();
			    	}
			    	else{
			    		$(this).addClass('on');
			    		$(".compare_box").show();
			    	}
			    });
			   <#-- $(".compare_box h4").click(function() {
			    	$(".compare_box").hide();
			    });-->
         })

   $(".gg_list li").hover(function() {
			    	$(this).find(".guanzhu").slideDown();
			    }, function() {
			    	$(this).find(".guanzhu").slideUp();
			    });
			    
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
			function searchAll(thirdId){
                $("#thirdId").val(thirdId);
                $("#cateId").val("0");

                var h=$(document).scrollTop();
                $("#top").val(h);
                $("#searchForm").submit();
            }
			function searchone(thirdId,cateId){
					$("#thirdId").val(thirdId);
					$("#cateId").val(cateId);

                var h=$(document).scrollTop();
                $("#top").val(h);
					$("#searchForm").submit();
	
			}
			function searchtwo(thirdId,cateId,catId){
					$("#thirdId").val(thirdId);
					$("#cateId").val(cateId);
					$("#parentCatId").val(catId);
                   var h=$(document).scrollTop();
                    $("#top").val(h);
					$("#searchForm").submit();
			}
			
			function nextpage(pageNo){
				$(".pageNo").val(pageNo);
				$("#searchForm").submit();
			}
			
			function prevpage(pageNo){
				$(".pageNo").val(pageNo);
				$("#searchForm").submit();
			}
			function searchTitle(){
				$("#title").val($("#titletwo").val());
				$("#searchForm").submit();
			}
			
			function searchTitleHead(obj){
				$("#title").val($(obj).val());
				$("#searchForm").submit();
			}
	</script>
        
	
</body>
</html>