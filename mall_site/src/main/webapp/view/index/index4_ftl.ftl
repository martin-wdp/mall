<#assign basePath=request.contextPath>
    <div id="wrapper">        
        <div id="content">
            <div class="section-01">
                <div id="slides">                    
                  <#if avc?? && (avc?size>0)>
	                	<#list avc as bigAdvert>
	                	<a class="slide" href="${bigAdvert.adverHref}" style="background-image:url(${bigAdvert.adverPath});"></a>                 		
	                	</#list>
                  </#if>  
                </div>
                <div class="content">
                    <div class="news">
                    <#if pageAdvs?? && (pageAdvs?size>0)>
                        <#list pageAdvs as pageAdv>
                            <#if (pageAdv.adverSort==2) >
                	             <a href="${pageAdv.adverHref}"><img alt="" src="${pageAdv.adverPath}"></a>
                            </#if>
                        </#list>
                	</#if>                       
                        <div class="notice-news">
                            <div class="tit">                               
                                <h3>${temp.expFleid2!'公告'}</h3>
                                <a class="more" href="javascript:;">更多&gt;&gt;</a>
                            </div>
                            <ul>
	                            <#if infoList?? && (infoList?size>0)>
		                            <#list infoList as info>
			                            <#if (info_index >= 0 ) && (info_index < 5 )>
			                                <li>
				                                <a href="information/${info.infoId?c}">
				                               	${info.title}
					                                <#if info.title?length gt 18>
						                        		${info.title?substring(0,18)}
						                        	<#else>
						                        		${info.title}
						                        	</#if>
				                                </a>	                         
			                                </li>
			                             </#if>
		                             </#list>
	                             </#if>                               
                            </ul>
                        </div>
                    </div>
                    <div class="hot-promotion">
                        <ul>
                        <#if avs?? && (avs?size>0)>
			                	<#list avs as smallAdvert>			                   		
			                   	<li id="${smallAdvert_index}"><a href="${smallAdvert.adverHref}"><img alt="" src="${smallAdvert.adverPath}"></a></li>
			                	</#list>
			             </#if>                            
                        </ul>
                        <a class="pm-prev"></a>
                        <a class="pm-next"></a>
                    </div>
                </div>
            </div>

            <div class="section-02">
                <div class="jp-recommend">
                    <h2>精品推荐</h2>
                    <div class="rc-box">
                        <ul>
                         <#if pageAdvs?? && (pageAdvs?size>0)>
                          <#list pageAdvs as pageAdv>
                            <#if (pageAdv.adverSort>=3 ) && (pageAdv.adverSort <= 6 )>
                         	<li><a href="${pageAdv.adverHref}"><img alt="" src="${pageAdv.adverPath}"></a></li>
                         </#if>
                         </#list>
                         </#if>    
                        </ul>
                    </div>
                </div>
                <div class="pp-recommend">
                    <h2>品牌推荐</h2>
                    <div class="rc-box">
                        <ul>
                         <#if trademarkList?? && (trademarkList?size>0)>
                        <#list trademarkList as trademark >
                            <li><a href="${trademark.url!''}"><img class="lazy" alt="" data-original="${trademark.logoSrc}"></a></li>
                        </#list> 
                        </#if>                           
                        </ul>
                    </div>
                </div>
            </div>
			<#if (floor.floorList)?? && (floor.floorList?size > 0)>
              <div class="section-03">
 			<#list floor.floorList as floors>             
                <div class="proLists proLists0${floors_index+1}">
                    <div class="title">
                        <h2><b>${floors.floorId}F</b>${floors.storeyName}</h2>
                        <#if (floors.indexStoreyTagList)?? && (floors.indexStoreyTagList?size > 0)>
	                        <ul class="flr-tabs">
	                        <#list floors.indexStoreyTagList as storeyTag>
	                       
		                        <#if (storeyTag.sort==1)>		                      
		                        <li class="cur"><a>${storeyTag.name}</a></li>
		                        <#else>		                        
		                        <li><a>${storeyTag.name}</a></li>
		                        </#if>	                            
	                        </#list>    
	                        </ul>
                      </#if>
                        <#--<a class="more" href="javascript:;">更多&gt;&gt;</a>-->
                    </div>
                    <div class="proWp">
                    <#if (floors.indexStoreyTagList)?? && (floors.indexStoreyTagList?size > 0)>                       
                           <#list floors.indexStoreyTagList as storeyTag>
                           		
                           		<div class="proCon">
		                            <div class="proAd">
		                            	<#list storeyTag.indexAdvertList as adv>
	                                		<#if adv.adverSort==1>
		                                	<a class="left-banner" href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"></a>
		                                	<#elseif adv.adverSort==2>
		                                	<a class="main-banner" href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"></a>
		                                	<#elseif adv.adverSort==3>
		                                	<a class="rig-banner" href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"></a>
		                                	<#elseif adv.adverSort==4>
		                                	<a class="rig-banner" href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"></a>
		                                	</#if>
		                                </#list>
		                                <div class="hot-pros">
		                                    <h3>热门产品</h3>
		                                    
			                                    <div class="hot-con">
			                                    <#list floors.indexCateList as cates>
				                                    <#if cates_index==0 || cates_index==4 ||cates_index==7>
				                                        <a class="hot" href="list/${cates.id?c}-${floors.goodsCatId?c}.html">${cates.name}</a>
				                                        <#else>
				                                        <a href="list/${cates.id?c}-${floors.goodsCatId?c}.html">${cates.name}</a>
				                                    </#if>
				                                </#list>
			                                    </div>
		                                    
		                                </div>
		                                <#list storeyTag.indexAdvertList as adv>
			                                <#if (adv.adverSort>=5)  && (adv.adverSort<=7) >
			                                <a class="bottom-banner" href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"></a>			                              
			                                </#if>
		                                </#list>
		                            </div><!--proAD-->
		                            <div class="pros">
		                                <ul>
		                                <#if (storeyTag.indexGoodsList)?? && (storeyTag.indexGoodsList?size>0)>
		                                	<#list storeyTag.indexGoodsList as goods>
			                                	<#if (goods_index>=0) && (goods_index<5)>
			                                    <li>
			                                        <a href="item/${goods.id}"><img alt="" src="${goods.urlpic}" style="height:150px;width:150px"></a>
			                                        <p class="proTitle"><a href="item/${goods.id}">
				                                        <#if (goods.name?length>30)>
			                                    			${goods.name?substring(0,30)}
				                                    	<#else>
					                                    	${goods.name}
				                                    	</#if>			                                        
			                                        </a></p>
			                                        <p class="proPrice">￥${goods.price}</p>
			                                    </li>
			                                    </#if>
		                                    </#list>
		                                </#if>
		                                    
		                                </ul>
		                            </div><!--pros -->
		                        </div><!--prcon-->
							</#list>
        			</#if>                                        
               </div><!--proWp-->
           </div><!--proLists01 --> 
 			</#list>               
            </div><!--section-03 -->
</#if>
        </div>
   </div>
<script>
$(function() {
        	//图片延迟加载
			$("img.lazy").lazyload({
				threshold: 200,
				effect: "fadeIn",
				failurelimit : 10,
				placeholder: "${basePath}/index_four/images/lazy_img.png",
				skip_invisible: false
			});
});
</script>