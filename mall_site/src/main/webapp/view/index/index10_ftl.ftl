<#assign basePath=request.contextPath>
<input type="hidden" id="isIndex" value="1"/>
<input type="hidden" id="basePath" value="${basePath}"/>
<div id="content">
            <div class="section-01">
                <div id="slides">
                <#if avc?? && (avc?size>0)>
	        		<#list avc as bigAdvert>
	        		<#if (bigAdvert.adverSort<=4)>
	                    <a class="slide" href="${bigAdvert.adverHref}" style="background-image:url(${bigAdvert.adverPath});"></a>
	               	</#if>
	                </#list>
                </#if>
                </div>
            </div>
            <div class="section-02 container">
                <div class="news_notice">
                    <div class="BreakingNewsController easing" id="breakingnews2">
                        <div class="bn-title"></div>
                        <ul id="abc">
                        	<#if infoList?? && (infoList?size>0)>
                       		<#list infoList as info>
                            <li><a href="information/${info.infoId?c}" class="left">${info_index+1}
                          				<#if info.title?length gt 18>
						                   ${info.title?substring(0,18)}
						                <#else>
						                   ${info.title}
						                </#if>
                            </a>
                            <span class="right">${info.createDate?string("yyyy-MM-dd")}</span></li>
                            </#list>
                            </#if>
                        </ul>
                        <div class="bn-arrows"><span class="bn-arrows-left"></span><span class="bn-arrows-right"></span></div>
                    </div>
                </div>
            </div><!--section-02-->
            <div class="section-03 container">
                <div class="recommend">
                    <div class="title">精品推荐</div>
                    <div class="body">
                        <div id="proScroll" style="position:relative;">
                            <ul>
                            <#if avs?? && (avs?size>0)>
                            <#list avs as smallAdvert>
                                <li>
                                    <a href="${smallAdvert.adverHref}"><img class="lazy" data-original="${smallAdvert.adverPath}" width="270" height="270"></a>
                                    <div class="brand_bot">
                                        <p class="brand_title">${smallAdvert.adverName}</p>
                                        <p>${smallAdvert.temp2}</p>
                                    </div>
                                </li>
                            </#list>
                            </#if>
                            </ul>
                        </div><!--proScroll-->
                        <a class="j-prev" href="javascript:;"></a>
                        <a class="j-next" href="javascript:;"></a>
                    </div>
                </div><!--recommend-->
            </div><!--section-03-->
<#if (floor.floorList)?? && (floor.floorList?size > 0)>
 <#list floor.floorList as floors>            
            <div class="section-04 container ">
                <div class="floor_title proLists" id="proLists0${floors.floorId}">${floors.storeyName}</div>
                <div class="floor_con">
                    <ul style="width:1250px;">
                     <#if (floors.indexGoodsList)??  && (floors.indexGoodsList?size > 0) >
                     <#list floors.indexGoodsList as goods>
                     <#if goods_index==0>
                        <li>
                           <a href="item/${goods.id}"><img class="lazy" data-original="${goods.urlpic}" width="270" height="271"/></a>
                            <p class="name"><a href="item/${goods.id}">${goods.name}</a></p>
                            <p class="price">RMB&nbsp;${goods.price}</p>
                        </li>
                     </#if>
                     </#list>
                     </#if>
                        <li class="p-slides">
                        	<#if (floors.indexAdvertList)?? &&(floors.indexAdvertList?size>0)>
                        	<#list floors.indexAdvertList as adv>
                            <#if floors.indexAdvertList?size==1>
                                <a id="showflag"  href="${adv.adverHref}"><img class="lazy" data-original="${adv.adverPath}" height="332" width="580"/></a>
                            <#else>
                                <a class="slide" href="${adv.adverHref}"><img class="lazy" data-original="${adv.adverPath}" height="332" width="580"/></a>
                            </#if>

                            </#list>
                            </#if>
                        </li>
                        <#if (floors.indexGoodsList)??  && (floors.indexGoodsList?size > 0) >
                     	<#list floors.indexGoodsList as goods>
                     	<#if (goods_index>=1) && (goods_index<=5)>
                        <li>
                            <a href="item/${goods.id}"><img class="lazy" data-original="${goods.urlpic}" width="270" height="271"/></a>
                            <p class="name"><a href="item/${goods.id}">${goods.name}</a></p>
                            <p class="price">RMB&nbsp;${goods.price}</p>
                        </li>
                        </#if>
                        </#list>
                        </#if>
                    </ul>
                    <div class="pLinks">
                    <#if (floors.indexCateList)?? && (floors.indexCateList?size > 0)>
                        <ul>                        
                         <#list floors.indexCateList as cates> 
                            <li><a href="list/${cates.id?c}-${floors.goodsCatId?c}.html">${cates.name}</a></li>
                         </#list>                          
                        </ul>
                    </#if>
                    </div>
                </div><!--floor_con-->
            </div><!--section-04-->
</#list>
</#if>

            <div class="section-05 container">
                <div class="brand_title">品牌街</div>
                <#if trademarkList?? &&(trademarkList?size>0) >
	                <ul class="brand_list">
	                <#list trademarkList as trademark >	
	                	<#if (trademark.sort<=7)>
	                    <li class="first_b">
	                        <a href="${trademark.url!''}"><img class="lazy" data-original="${trademark.logoSrc}" width="108" height="36"/></a>
	                    </li>
	                    </#if>
	                	<#if (trademark.sort==8)>
	                    <li class="second_b">
	                        <a href="${trademark.url!''}"><img class="lazy" data-original="${trademark.logoSrc}" width="108" height="36"/></a>
	                    </li>
	                    </#if>
	                	<#if (trademark.sort>=9)>                                                           
	                    <li>
	                        <a href="${trademark.url!''}"><img class="lazy" data-original="${trademark.logoSrc}" width="108" height="36"/></a>
	                    </li>
	                    </#if>
	               </#list>    
	                </ul>
                </#if>
            </div><!--section-05-->
        </div>
        <#if (floor.floorList)?? && (floor.floorList?size > 0)>
        <div class="sideBar">
        <#list floor.floorList as floors>
        <a class="sideItem" href="#proLists0${floors.floorId}">${floors.floorId}F<span>${floors.storeyName}</span></a>
        </#list>       
        <a id="backtoTop" href="javascript:;"></a>
        </div>
        </#if>
  
<script>
$(function() {
        	//图片延迟加载
			$("img.lazy").lazyload({
				threshold: 200,
				effect: "fadeIn",
				failurelimit : 10,
				placeholder: "${basePath}/index_seven/images/lazy_img.png",
				skip_invisible: false
			});

    $("#showflag").removeAttr("style")
			
			
});
</script>