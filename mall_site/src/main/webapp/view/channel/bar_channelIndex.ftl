<#include "../include/common.ftl">
<@htmlHead title='${sys.bsetName}'>
<link rel="stylesheet" href="${basePath}/css/base.min.css" type="text/css" />
<link rel="stylesheet" href="${basePath}/css/main.css" type="text/css" />
<script type="text/javascript" src="${basePath}/js/jquery.slides.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.min.js"></script>
<script type="text/javascript" src="${basePath}/js/main.js"></script>
</@htmlHead>
<#--<html>
<head>
	<#assign basePath=request.contextPath>
	<base href="${basePath}/">
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<title>${sys.bsetName}</title>
	<link rel="stylesheet" href="${basePath}/css/base.min.css" type="text/css" />
	<link rel="stylesheet" href="${basePath}/css/main.css" type="text/css" />
	<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${basePath}/js/jquery.slides.min.js"></script>
	<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.min.js"></script>
	<script type="text/javascript" src="${basePath}/js/main.js"></script>

</head>-->
<@htmlBody>
<body>
	<#include "../index/topnew.ftl">
<#if pageAdvs?? && (pageAdvs?size>0) &&
(avc)?? && (avc?size>0) &&
(avs)?? && (avs?size>0) &&
(floor.floorList)?? && (floor.floorList?size>0)
>
	<div class="bh_container">
		<div class="bh_wrapper clearfix">
			<div class="bh_left fl">
			<#list pageAdvs as adv>
			    <#if (adv_index >= 0)>
			    <#if (adv_index < 2)>
		        	<a  href="${adv.adverHref!''}" title="${adv.adverName!''}"><img alt="${adv.adverName!''}" src="${adv.adverPath!''}" width="210px" height="193px"/></a>
				</#if>
				</#if>
			</#list>
			</div>
			
			<div class="bh_center fl ml10">
				<div id="bh_slides" class="bh_slides">
					<#list avc as bigAdvert>
					<#if (bigAdvert_index >= 0)>
			    	<#if (bigAdvert_index < 5)>
					<a href="${bigAdvert.adverHref!''}" title="${bigAdvert.adverName!''}"><img width="760" height="265" alt="${bigAdvert.adverName!''}" src="${bigAdvert.adverPath!''}"/></a>
					</#if>
					</#if>
					</#list>
				</div><!--/bh_slides-->
				<div class="roll_box pr">
					<div class="roll_wp">
						<ul class="roll_list clearfix">
							<#list avs as smallAdvert>
							<#if (smallAdvert_index >= 0)>
			    			<#if (smallAdvert_index < 8)>
							<li><a href="${smallAdvert.adverHref!''}"><img alt="" src="${smallAdvert.adverPath!''}" width="170" height="120" /></a></li>
							</#if>
							</#if>
							</#list>
						</ul><!--/roll_list-->
					</div><!--/roll_wp-->
					<a href="javascript:;" class="r_prev"></a>
					<a href="javascript:;" class="r_next"></a>
				</div><!--/roll_box-->
			</div><!--/bh_center-->

			<div class="bh_right fr">
				<#list pageAdvs as adv>
				    <#if (adv_index >= 2)>
				    <#if (adv_index < 4)>
			        	<a  href="${adv.adverHref!''}" title="${adv.adverName!''}"><img alt="${adv.adverName!''}" src="${adv.adverPath!''}" width="210px" height="193px"/></a>
					</#if>
					</#if>
				</#list>
			</div><!--/bh_right-->
		</div><!--/bh_wrapper-->
		
		<#list floor.floorList as floors>
		<div class="bh_content">
			<div class="bh_title clearfix">
				<h3 class="fl"><span>${floors.floorId!''}F</span>${floors.storeyName!''}</h3>
				<div class="ft_wp fl mt10 pr">
					<ul class="f_tabs tabs_0${floors.floorId!''} clearfix">
						<#list floors.indexStoreyTagList as storeyTag>
						<#if (storeyTag_index >= 0)>
				    	<#if (storeyTag_index < 4)>
						<li><a href="javascript:;">${storeyTag.name!''}</a></li>
						</#if>
						</#if>
						</#list>
					</ul><!--/f_tabs-->
					<span class="c_tabs pa"></span>
				</div><!--/ft_wp-->
			</div><!--/bh_title-->
			<div class="floor_cont clearfix mt10">
				<div class="fc_left fl">
					<#if (floors.indexAdvertList)?? && (floors.indexAdvertList?size>0)>
					<a href="${(floors.indexAdvertList[0].adverHref)!''}"><img src="${(floors.indexAdvertList[0].adverPath)!''}" alt="${(floors.indexAdvertList[0].adverName)!''}" width="210" height="460"/></a>
					</#if>
				</div><!--/fc_left-->
				<div class="fc_center fl ml10">
					<#list floors.indexStoreyTagList as storeyTag>
					<#if (storeyTag_index >= 0)>
				    <#if (storeyTag_index < 4)>
					<ul class="bh_list clearfix">
						<#list storeyTag.indexGoodsList as goods>
						<#if (goods_index >= 0)>
				    	<#if (goods_index < 8)>
						<li>
							<a class="pd_img" href="${basePath}/item/${goods.id}"><img src="${goods.urlpic}" alt="${goods.name}" width="175px" height="160px"/></a>
							<p class="pd_name mt5 mb5"><a href="${basePath}/item/${goods.id}" title="${goods.name}"><#if goods.name?length gt 16>${goods.name?substring(0,16)}...<#else>${goods.name}</#if></a></p>
							<span class="pd_price">¥ ${goods.price}</span>
						</li>
						</#if>
						</#if>
						</#list>
					</ul><!--/bh_list-->
					</#if>
					</#if>
					</#list>
				</div><!--/fc_center-->
				<div class="fc_right fr">
					<#if (floors.indexAdvertList)?? && (floors.indexAdvertList?size>1)>
					<#list floors.indexAdvertList as floorAdvert>
						<#if (floorAdvert_index >= 1)>
						<#if (floorAdvert_index < 5)>
						<a href="${floorAdvert.adverHref!''}"><img src="${floorAdvert.adverPath!''}" alt="${floorAdvert.adverName!''}" width="210" height="108"/></a>
						</#if>
						</#if>
					</#list>
					</#if>
				</div><!--/fc_right-->
			</div><!--/floor_cont-->		
		</div><!--/bh_content-->
		</#list>
	</div><!--/bh_container-->
</#if>
	<#include "../index/bottom.ftl">
	<script>
	//头部分类导航显示和隐藏
$(".pro_sort").addClass("pro_sort_close");
		$(function(){
			$("#bh_slides").slidesjs({
				width: 760,
				height: 265,
				play: {
			        active: true,
			        auto: true,
			        interval: 4000
			    }
			});
			$(".roll_wp").jCarouselLite({
				auto: 3000,
				speed: 500,
				visible: 4,
				btnNext: ".r_next",
				btnPrev: ".r_prev"
			});
		});
	</script>
</body>
</@htmlBody>