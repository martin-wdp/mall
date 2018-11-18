 <#assign basePath=request.contextPath>
 <div id="content">
        <div class="show-box">
            <div id="slides">
            <#if avc?? && (avc?size>0)>
        		<#list avc as bigAdvert>
                <a class="slide" href="${bigAdvert.adverHref}" style="background-image:url(${bigAdvert.adverPath});"></a>
                </#list>
            </#if>    
            </div>
        </div><!--show-box-->
        <div class="notice-con">
        	<#if pageAdvs?? && (pageAdvs?size>0)>
        	<#list pageAdvs as pageAdv>
	        	<#if pageAdv.adverSort==2>
	            <a href="${pageAdv.adverHref}"><img  class="lazy" data-original="${pageAdv.adverPath}"/></a>
	            </#if>
            </#list>
            </#if>
            <div class="notice-title">
                ${temp.expFleid2!'公告'}
            </div>
            <#if infoList?? && (infoList?size>0)>
            <ul>
            <#list infoList as info>
            <#if (info_index >= 0 ) && (info_index <= 8 ) >
                <li><a href="information/${info.infoId?c}">
                	<#if info.title?length gt 18>
                		${info.title?substring(0,18)}
                	<#else>
               			${info.title}
               		 </#if>
               		 </a>
               </li>
            </#if>
           	</#list>
            </ul>
            </#if>
        </div><!--notice-con-->
        <div class="clear"></div>
        <div class="section-00">
            <div class="hotBrand">
                <div class="title">热门品牌<span>HOT BRAND</span></div>
                <div class="Brand-con">
                    <ul>
                        <li class="title">
                            品牌推荐
                        </li>
                        <#if trademarkList?? &&(trademarkList?size>0) > 
                        <#list trademarkList as trademark > 
                        <#if (trademark.sort>=1) && (trademark.sort<=13)>
                        <li><a href="#"><img class="lazy" data-original="${trademark.logoSrc}" width="132" height="58"/></a></li>
                        </#if>
                        </#list>
                        </#if>                        
                    </ul>
                    <#if pageAdvs?? && (pageAdvs?size>0)>
        			<#list pageAdvs as pageAdv>
	        			<#if pageAdv.adverSort==3>
                    		<a href="${pageAdv.adverHref}" class="ad"><img class="lazy" data-original="${pageAdv.adverPath}" width="267" height="117"/></a>
                    	</#if>
                   </#list>
                   </#if> 	
                </div><!--Brand-con-->
            </div><!--hotBrand-->
        </div><!--section-00-->
        <div class="section-01">
            <div class="recommend">
                <h2 class="title">热门推荐<span>HOT Recommend</span></h2>
                <div class="travel-left">
                <#if hotSale?? && (hotSale?size>0)>
                    <ul>
                    <#list hotSale as hotgoods>
                        <li>
                            <a href="item/${(hotgoods.goodsproductId?c)!''}" class="m-img"><img class="lazy" data-original="${(hotgoods.goodsproductImgsrc)!''}" width="224" height="300"/></a>
                            <div class="detail">
                                <a href="item/${(hotgoods.goodsproductId?c)!''}" title="${(hotgoods.goodsproductName)!''}">${(hotgoods.goodsproductName)!''}</a>
                                <p title="${(hotgoods.temp3)!''}">${(hotgoods.temp3)!''}</p>
                            </div>
                        </li> 
                    </#list>                     
                    </ul>
               </#if>     
                </div><!--travel-left-->

            </div><!--recommend-->

        </div><!--section-01-->
<#if (floor.floorList)?? && (floor.floorList?size > 0)>
        <div class="section-02">
<#list floor.floorList as floors>       
            <div class="proLists" id="proLists0${floors.floorId}">
                <div class="title">
                        <div class="pro-title">
                            <h2><span>${floors.floorId}F</span>${floors.storeyName}</h2>
                        </div>
                        <#if (floors.indexStoreyTagList)?? && (floors.indexStoreyTagList?size > 0)>
	                        <ul class="proTabs">
	                        <#list floors.indexStoreyTagList as storeyTag>
	                        	<#if (storeyTag.sort==1)>
	                            <li class="cur"><a href="javascript:;">${storeyTag.name}</a></li>
	                            <#else>
	                            <li><a href="javascript:;">${storeyTag.name}</a></li>
	                            </#if>
	                       	</#list>
	                        </ul>
                        </#if>
                </div><!--title-->
                <div class="proWp">
                    <div class="proBox">
                    <#if (floors.indexAdvertList)?? && (floors.indexAdvertList?size > 0)> 
	                    <#list floors.indexAdvertList as adv>
		                    <#if adv.adverSort==1>
	                        <a href="${adv.adverHref}"><img class="lazy" data-original="${adv.adverPath}" style="display: inline;" width="210" height="300"></a>
	                        </#if>
                        </#list>
                    </#if>    
                        <div class="pLinks">
                            <ul>
                            <#list floors.indexCateList as cates>
                                <li><a href="list/${cates.id?c}-${floors.goodsCatId?c}.html">${cates.name}</a></li>
                            </#list>
                            </ul>
                        </div><!--pLinks -->
                    </div><!--proBox-->
                    <div class="proCon">
                    <#list floors.indexStoreyTagList as storeyTag>
                    <#if (storeyTag.sort%3==1)>
                        <div class="proItem">
                            <ul class="proList">
                            <#if (storeyTag.indexGoodsList)?? && (storeyTag.indexGoodsList?size>0)>
			                	<#list storeyTag.indexGoodsList as goods>
			                    	<#if (goods_index>=0) && (goods_index<4)>
		                                <li class="big-wine">
		                                    <p class="p-name"><a href="item/${goods.id}" title="${goods.name}">
								                      ${goods.name} 
		                                    </a>		                                    
		                                    </p>
		                                    <p class="p-price">￥${goods.price}</p>
		                                    <a class="p-img" href="item/${goods.id}"><img class="lazy" alt="" data-original="${goods.urlpic}" width="210" height="210"/></a>
		                                </li>
                                	</#if>
                                </#list>
                          	</#if>
                                <#if (storeyTag.indexAdvertList)?? && (storeyTag.indexAdvertList?size>0)>
                                <#list storeyTag.indexAdvertList as adv>
                                	<#if adv.adverSort<=4>
	                                <li class="wine-ad">
	                                    <a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"/></a>
	                                </li>
	                                </#if>
                                </#list>
                                </#if>
                            </ul>
                        </div>
					</#if>
					<#if (storeyTag.sort%3==2)>
                        <div class="proItem">
                            <ul class="proList">
                            <#if (storeyTag.indexGoodsList)?? && (storeyTag.indexGoodsList?size>0)>
			                <#list storeyTag.indexGoodsList as goods>
			                	<#if (goods_index==0)>
                                <li class="big-wine">
                                    <p class="p-name"><a href="item/${goods.id}" title="${goods.name}">
                                   	 ${goods.name}</a></p>
                                    <p class="p-price">￥${goods.price}</p>
                                    <a class="p-img" href="item/${goods.id}"><img class="lazy" alt="" data-original="${goods.urlpic}" width="210" height="210"/></a>
                                </li>
                                </#if>
                            </#list>
                            </#if>
                            <#if (storeyTag.indexAdvertList)?? && (storeyTag.indexAdvertList?size>0)>
	                            <#list storeyTag.indexAdvertList as adv>
		                            <#if adv.adverSort==1>
	                                <li class="big-wine big-tu">
	                                    <a href="${adv.adverHref}"><img class="lazy" data-original="${adv.adverPath}" width="740" height="298"/></a>
	                                </li>
	                                </#if>
                                </#list>
                             </#if> 
                             
                             <#if (storeyTag.indexGoodsList)?? && (storeyTag.indexGoodsList?size>0)>
			                	<#list storeyTag.indexGoodsList as goods>
				                	<#if (goods_index>=1) && (goods_index<=4)>
	                                <li class="small-wine">
	                                    <a class="s-img" href="item/${goods.id}"><img class="lazy" alt="" data-original="${goods.urlpic}" width="100" height="100"/></a>
	                                    <p class="p-name">
	                                    <a href="item/${goods.id}" title="${goods.name}">
	                                    	${goods.name}
	                                    </a>
	                                    </p>
	                                    <p class="p-price">￥${goods.price}<!--<span>￥336</span>--></p>
	                                </li>
	                                </#if>
                                </#list>
                             </#if>   
                            </ul>
                        </div>
                    </#if>
                    <#if (storeyTag.sort%3==0)>
                        <div class="proItem">
                            <ul class="proList">
                            
                            	<#if (storeyTag.indexGoodsList)?? && (storeyTag.indexGoodsList?size>0)>
			                    <#list storeyTag.indexGoodsList as goods>
			                    	<#if (goods_index>=0) && (goods_index<4)>
	                                <li class="big-wine">
	                                    <p class="p-name"><a href="item/${goods.id}" title="${goods.name}">					                       
						                                 ${goods.name}
	                                    </a></p>
	                                    <p class="p-price">￥${goods.price}</p>
	                                    <a class="p-img" href="javascript:;"><img class="lazy" alt="" data-original="${goods.urlpic}" width="210" height="210"/></a>
	                                </li>
	                                </#if>
                                </#list>
                                </#if>                               
                                <#if (storeyTag.indexGoodsList)?? && (storeyTag.indexGoodsList?size>0)>
			                	<#list storeyTag.indexGoodsList as goods>
				                	<#if (goods_index>=4) && (goods_index<=7)>
	                                <li class="small-wine">
	                                    <a class="s-img" href="item/${goods.id}"><img class="lazy" alt="" data-original="${goods.urlpic}" width="100" height="100"/></a>
	                                    <p class="p-name">
	                                    <a href="item/${goods.id}" title="${goods.name}">
	                                    	${goods.name}
	                                    </a>
	                                    </p>
	                                    <p class="p-price">￥${goods.price}<!--<span>￥336</span>--></p>
	                                </li>
	                                </#if>
                                </#list>
                             </#if>  
                            </ul>
                        </div>
                    </#if>
                    </#list>
                    </div><!--proCon-->
                </div><!--proWp-->
            </div><!--proLists01-->
</#list>            
        </div><!--section-02--> 
</#if>       
<div class="sideBar">
    <#if (floor.floorList)?? && (floor.floorList?size > 0)>
		<#list floor.floorList as floors>     
	    <a class="sideItem" href="#proLists0${floors.floorId}">${floors.floorId}F<span>${floors.storeyName}</span></a>
	    </#list>
	</#if>    
    <a id="backtoTop" href="javascript:;"></a>
</div>

 <script>
$(function() {
        	//图片延迟加载
			$("img.lazy").lazyload({
				threshold: 200,
				effect: "fadeIn",
				failurelimit : 10,
				placeholder: "${basePath}/index_fifteen/images/lazy_img.png",
				skip_invisible: false
			});
			
			
});
</script>