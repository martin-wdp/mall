<#include "../include/common.ftl">
<@htmlHead title='${sys.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/channel/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/channel/style.css" />
</@htmlHead>
<#--<head>
<#assign basePath=request.contextPath>
<base href="${basePath}/">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>${sys.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/channel/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/channel/style.css" />
</head>-->
<@htmlBody>
<body>
<!--页面头-->
<#include "../index/topnew.ftl">
<#if pageAdvs?? && (pageAdvs?size>0) &&
(avc)?? && (avc?size>0) &&
(classifyBar.classifyBarList)?? && (classifyBar.classifyBarList?size>0) &&
(channelGoodsFlag0)?? && (channelGoodsFlag0?size>0) &&
(channelGoodsFlag1)?? && (channelGoodsFlag1?size>0) && 
(channelGoodsFlag2)?? && (channelGoodsFlag2?size>0) &&
(floor.floorList)?? && (floor.floorList?size>0)
>

<!--频道大图广告-->
<#list pageAdvs as adv>
        <#if adv.adverSort==1>
        <div class="c_banner mb10">
        	<#--
			<a href="<#if adv.adverHref??>${adv.adverHref}</#if>" title="${adv.adverName}" style="background:url(${adv.adverPath}) no-repeat center center;width:100%;height:300px;">
        	-->
			<a href="<#if adv.adverHref??>${adv.adverHref}</#if>" title="${adv.adverName}">
				<img src="${adv.adverPath}" width="100%" height="400px"/>
			</a>
		</div><!-- /c_banner -->
		</#if>
</#list>

<div class="w1200 mb10">
	<div class="c1_col3 fr">
    	    	<div class="m_notice red_border">
        	<div class="title">
            	<h2>资讯公告</h2>
                <a class="more" href="${basePath}/information/list">更多资讯&gt;&gt;</a>
            </div>
            <div class="body">
            	<ul>
                      <#list infoList as info>
		                 <#if (info_index < 5 )>
	                        <li><a href="information/${info.infoId}">${info.title}</a></li>
		                  </#if>                   	
		        		</#list>
                </ul>
            </div>
        </div><!-- /notice -->
        
         <!--页面右下角广告3-->
	<#list pageAdvs as adv>
	        <#if adv.adverSort==3>
	           <div class="ad mt10">
        		<a  href="<#if adv.adverHref??>${adv.adverHref}</#if>" title="${adv.adverName}"><img alt="${adv.adverName}" src="${adv.adverPath}" width="210px" height="175px"/></a>
        		</div>
			</#if>
	</#list>
         <!--页面右下角广告4-->
	<#list pageAdvs as adv>
	        <#if adv.adverSort==4>
	           <div class="ad mt10">
        		<a  href="<#if adv.adverHref??>${adv.adverHref}</#if>" title="${adv.adverName}"><img alt="${adv.adverName}" src="${adv.adverPath}" width="210px" height="175px"/></a>
        		</div>
			</#if>
	</#list>
      
    </div><!-- /c1_col3 -->
    <div class="c1_col1 fl">
    	<div class="c_cate">
        	<h2>${channel.channelName}</h2>
        	<#list classifyBar.classifyBarList as cate1>
			<dl>
            	<dt><a href="list/${cate1.goodsCatId}-${channel.goodsCatId}.html">${cate1.name}</a></dt>
            	<#if (cate1.childs)?? && (cate1.childs)?size gt 0>
                <dd>
                	<#list cate1.childs as scate>
                		<a href="list/${scate.goodsCatId}-${cate1.goodsCatId}.html">${scate.name}</a>
                	</#list>
                </dd>
                </#if>
            </dl>
			</#list>
        </div>
        <!--页面广告2-->
	<#list pageAdvs as adv>
	        <#if adv.adverSort==2>
			  <div class="mt10">
	        	<a href="<#if adv.adverHref??>${adv.adverHref}</#if>" title="${adv.adverName}"><img alt="${adv.adverName}" src="${adv.adverPath}" width="210px" height="199px"/></a>
	         </div>
			</#if>
	</#list>
    </div><!-- /c1_col1 -->
    
    
    <div class="c1_col2 fl ml10">
    	<div class="c_slider">
        	<ul id="c_slider">
	        	<#list avc as adv>
		         	<li><a href="<#if adv.adverHref??>${adv.adverHref}</#if>" title="${adv.adverName}"><img width="760" height="260" alt="${adv.adverName}" src="${adv.adverPath}"/></a></li>
				</#list>
            </ul>
        </div><!-- /c_slider -->
        <div class="c_top_rec mt10">
        	<div class="tagMenu">
               <ul class="menu">
                   <li>今日特价</li>
                   <li>新品推荐</li>
                   <li>人气明星</li>
                </ul>
            </div>
            <div class="content">
                <div class="layout">
                    <div class="c_top_rec_goods">
                    <#list channelGoodsFlag0 as channelGoods>
					    <div class="c_top_rec_item">
                        	<a class="img" href="item/${channelGoods.goodsProductId}.html"><img alt="${channelGoods.goodsProductName}" src="${channelGoods.goodsProductImg}" width="170px" height="150px"/></a>
                            <p class="name"><a href="item/${channelGoods.goodsProductId}.html"><#if channelGoods.goodsProductName?length gt 25>${channelGoods.goodsProductName?substring(0,25)}...<#else>${channelGoods.goodsProductName}</#if></a></p>
                            <p class="price">￥${channelGoods.goodsProductPrice}</p>
                        </div>
					</#list>
                    <div class="cb"></div>
                    </div>
                </div>
                <div class="layout">
                    <div class="c_top_rec_goods">
                    	 <#list channelGoodsFlag1 as channelGoods>
					    <div class="c_top_rec_item">
                        	<a class="img" href="item/${channelGoods.goodsProductId}.html"><img alt="${channelGoods.goodsProductName}" src="${channelGoods.goodsProductImg}"  width="170px" height="150px"/></a>
                            <p class="name"><a href="item/${channelGoods.goodsProductId}.html"><#if channelGoods.goodsProductName?length gt 25>${channelGoods.goodsProductName?substring(0,25)}...<#else>${channelGoods.goodsProductName}</#if></a></p>
                            <p class="price">￥${channelGoods.goodsProductPrice}</p>
                        </div>
					</#list>
                        <div class="cb"></div>
                    </div>
                </div>
                <div class="layout">
                    <div class="c_top_rec_goods">
                    <#list channelGoodsFlag2 as channelGoods>
					    <div class="c_top_rec_item">
                        	<a class="img" href="item/${channelGoods.goodsProductId}.html"><img alt="${channelGoods.goodsProductName}" src="${channelGoods.goodsProductImg}"  width="170px" height="150px"/></a>
                            <p class="name"><a href="item/${channelGoods.goodsProductId}.html"><#if channelGoods.goodsProductName?length gt 25>${channelGoods.goodsProductName?substring(0,25)}...<#else>${channelGoods.goodsProductName}</#if></a></p>
                            <p class="price">￥${channelGoods.goodsProductPrice}</p>
                        </div>
					</#list>
                        <div class="cb"></div>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- /c1_col2 -->
    <div class="cb"></div>
</div>

<!--1F 商品促销-->
<#list floor.floorList as floors>
<#if floors.floorId == 1>
<div class="c_cuxiao w1200 mb10">
	<h2>${floors.storeyName}</h2>
    <div class="body">
	<#list floors.indexAdvertList as adv>
        <#if adv_index == 0>
    	<div style="left:0;top:0;">
    	<a href="${adv.adverHref}"><img alt="${adv.adverName}" src="${adv.adverPath}" width="474px" height="310px"/></a>
        </div>
        <#elseif adv_index == 1>
        <div style="left:484px;top:0;">
        <a href="${adv.adverHref}"><img alt="${adv.adverName}" src="${adv.adverPath}"  width="232px" height="310px"/></a>
        </div>
        <#elseif adv_index == 2>
        <div style="left:726px;top:0;">
        <a href="${adv.adverHref}"><img alt="${adv.adverName}" src="${adv.adverPath}"   width="232px" height="310px"/></a>
        </div>
        <#elseif adv_index == 3>
        <div style="left:968px;top:0;">
        <a href="${adv.adverHref}"><img alt="${adv.adverName}" src="${adv.adverPath}"  width="232px" height="150px" /></a>
        </div>
        <#elseif adv_index == 4>
        <div style="left:968px;top:160px;">
        <a href="${adv.adverHref}"><img alt="${adv.adverName}" src="${adv.adverPath}"  width="232px" height="150px" /></a>
        </div>
        <#elseif adv_index == 5>
        <div style="left:0;top:320px;">
        <a href="${adv.adverHref}"><img alt="${adv.adverName}" src="${adv.adverPath}"  width="232px" height="150px" /></a>
        </div>
        <#elseif adv_index == 6>
        <div style="left:0;top:480px;">
        <a href="${adv.adverHref}"><img alt="${adv.adverName}" src="${adv.adverPath}"   width="232px" height="150px"/></a>
        </div>
        <#elseif adv_index == 7>
        <div style="left:242px;top:320px;">
        <a href="${adv.adverHref}"><img alt="${adv.adverName}" src="${adv.adverPath}"   width="232px" height="310px"/></a>
        </div>
        <#elseif adv_index == 8>
        <div style="left:484px;top:320px;">
        <a href="${adv.adverHref}"><img alt="${adv.adverName}" src="${adv.adverPath}"   width="474px" height="310px"/></a>
        </div>
        <#elseif adv_index == 9>
        <div style="left:968px;top:320px;">
        <a href="${adv.adverHref}"><img alt="${adv.adverName}" src="${adv.adverPath}"   width="232px" height="310px"/></a>
        </div>
        </#if>
     </#list>
    </div>
</div><!-- /c_cuxiao -->
</#if>
</#list>

    
<!--2F 手机通讯-->
<#list floor.floorList as floors>
<#if floors.floorId gt 1>
<div class="w1200 mb10">
	<div class="c_rank fr">
    	<div class="tagMenu">
           <ul class="menu">
               <li>排行榜</li>
               <li>最新降价</li>
               <li>新品</li>
            </ul>
        </div>
        <div class="content">
            <div class="layout">
                <ul class="rank_list">
                <!--推荐排行-->
                	<#list floors.indexGoodsList as recommend>
                	<#if recommend.storeyGoodsFlag??>
                	<#if recommend.storeyGoodsFlag == '0'>
                        <li>
                    	<i>${recommend_index+1}</i>
                        <a  href="item/${recommend.id}.html" class="img"><img alt="${recommend.name}" src="${recommend.urlpic}" width="50px" height="50px"/></a>
                        <p class="name"><a href="item/${recommend.id}.html"><#if recommend.name?length gt 12>${recommend.name?substring(0,12)}<#else>${recommend.name}</#if></a></p>
                        <p class="price fr">￥${recommend.price}</p>
                        <p class="cost">￥${recommend.price}</p>
                    </li>
                    </#if>
                    </#if>
                    </#list>
                </ul>
            </div>
            <div class="layout">
                <ul class="rank_list">
                	<!--最新降价-->
                	<#list floors.indexGoodsList as recommend>
                	<#if recommend.storeyGoodsFlag??>
                	<#if recommend.storeyGoodsFlag == '1'>
                        <li>
                    	<i>${recommend_index+1}</i>
                        <a  href="item/${recommend.id}.html" class="img"><img alt="${recommend.name}" src="${recommend.urlpic}" width="50px" height="50px"/></a>
                        <p class="name"><a href="item/${recommend.id}.html"><#if recommend.name?length gt 12>${recommend.name?substring(0,12)}<#else>${recommend.name}</#if></a></p>
                        <p class="price fr">￥${recommend.price}</p>
                        <p class="cost">￥${recommend.price}</p>
                    </li>
                    </#if>
                    </#if>
                    </#list>
                </ul>
            </div>
            <div class="layout">
                <ul class="rank_list">
                	<!--新品-->
                	<#list floors.indexGoodsList as recommend>
                	<#if recommend.storeyGoodsFlag?? >
                	<#if recommend.storeyGoodsFlag == '2'>
                        <li>
                    	<i>${recommend_index+1}</i>
                        <a  href="item/${recommend.id}.html" class="img"><img alt="${recommend.name}" src="${recommend.urlpic}" width="50px" height="50px"/></a>
                        <p class="name"><a href="item/${recommend.id}.html"><#if recommend.name?length gt 12>${recommend.name?substring(0,12)}<#else>${recommend.name}</#if></a></p>
                        <p class="price fr">￥${recommend.price}</p>
                        <p class="cost">￥${recommend.price}</p>
                    </li>
                    </#if>
                    </#if>
                    </#list>
                </ul>
            </div>
        </div>
    </div><!-- /c_rank -->

    <div class="c_floor">
    	<div class="title">
        	<div class="floor_sublinks fr">
        	<#list floors.indexStoreyTagList as storeyTag>
        		<a <#if storeyTag_index == 0> class="red"</#if> href="javascript:void(0);" data-val="${storeyTag.channelStoreyTagId}">${storeyTag.name}</a>
                <#if storeyTag_has_next><span>|</span></#if>
	         </#list>
            </div>
        	<h2>${floors.storeyName}</h2>
        </div>
        <div class="c_floor_goods fr">
        
        <!--默认第一个标签商品-->
        <#list floors.indexStoreyTagList as storeyTag>
        		<#if storeyTag_index == 0>
        		<#list storeyTag.indexGoodsList as goods>
			        <div class="c_floor_item">
		            	<a class="img" href="item/${goods.id}.html"><img alt="${goods.name}" src="<#if goods.urlpic?exists>${goods.urlpic}</#if>" width="170px" height="150px"/></a>
		                <p class="name"><a href="item/${goods.id}.html"><#if goods.name?length gt 12>${goods.name?substring(0,12)}<#else>${goods.name}</#if></a></p>
		                <p class="price fr">￥${goods.price}</p>
		                <p class="cost">￥${goods.price}</p>
		            </div>
		          </#list>
        		</#if>
	     </#list>
      
            <div class="cb"></div>
        </div><!-- /c_floor_goods -->
        <div class="c_floor_ads fl">
        	<ul class="c_floor_slider">
        	<#list floors.indexAdvertList as adv>
            	<li><a href="${adv.adverHref}"><img alt="${adv.adverName}" src="${adv.adverPath}" width="210px" height="460px"/></a></li>
             </#list>
            </ul>
        </div><!-- /c_floor_ads -->
        <div class="cb"></div>
    </div><!-- /c_floor -->
    <div class="c_b"></div>
</div>
</#if>
</#list>


<#list pageAdvs as adv>
        <#if adv.adverSort==5>
		<!--底部广告-->
	        <div class="bottom_ad">
	       	 <a style="background-image:url(${adv.adverPath});" href="<#if adv.adverHref??>${adv.adverHref}</#if>">${adv.adverName}</a>
			</div><!-- /top_ad -->
		</#if>
</#list>
</#if>
<!--页脚-->
<#--
<#include "/common/footer.ftl">
-->
<#include "../index/bottom.ftl">
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.slides.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.bxslider.min.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>

<script type="text/javascript">
//头部分类导航显示和隐藏
$(".pro_sort").addClass("pro_sort_close");
$(function(){
	$('#c_slider').bxSlider({
		speed : 800,
		auto : true
	});
	$('.c_floor_slider').bxSlider({
		speed : 800,
		auto : true
	});
	
	//绑定鼠标悬停到标签上事件
	$('.floor_sublinks a').mouseover(function(){
		$(this).parents('.floor_sublinks').find('a').removeClass('red');
		$(this).addClass('red');
		loadTagProduct(this);
	});
});

//加载标签商品
function loadTagProduct(tag){
	//标签商品
	$.ajax({
		url: 'loadstoreytagproduct.htm?tagId='+$(tag).attr('data-val'),
		success: function(data){
			var html = "";
			for(var i=0;i<data.length;i++){
				html += '<div class="c_floor_item">';
            	html += '<a class="img" href="item/'+data[i].id+'.html"><img alt="'+data[i].name+'" src="'+data[i].urlpic+'" width="170px" height="150px"/></a>';
                html += '<p class="name"><a href="item/'+data[i].id+'.html">'+data[i].name.length > 12 ? data[i].name.substr(0,12)+'...':data[i].name+'</a></p>';
                html += '<p class="price fr">￥'+data[i].price+'</p>';
                html += '<p class="cost">￥'+data[i].price+'</p>';
                html += '</div>';
			}
			$(tag).parents(".c_floor").find(".c_floor_goods").html(html);
		}
	});
}
</script>
</body>
</@htmlBody>
