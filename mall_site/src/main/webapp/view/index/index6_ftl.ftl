 <#assign basePath=request.contextPath>
 <input type="hidden" id="isIndex" value="1"/>
    <div id="wrapper">        
        <div id="content">
            <div class="section-01">
                <div class="show-box">
                    <div id="slides">
                    	<#if avc?? && (avc?size>0)>
                    		<#list avc as bigAdvert>
                        	<a class="slide" href="${bigAdvert.adverHref}"><img class="lazy" alt="" data-original="${bigAdvert.adverPath}"></a>
                        	</#list>
                        </#if>
                    </div>
                    <div id="proScroll">
                        <div class="jscroll-wp">
                            <ul>
                            <#if avs?? && (avs?size>0)>
                             	<#list avs as smallAdvert>
                                <li><a href="${smallAdvert.adverHref}"><img class="lazy" alt="" data-original="${smallAdvert.adverPath}"/></a></li>
                                </#list>
                             </#if>                          
                            </ul>
                        </div>
                        <a class="j-prev" href="javascript:;"></a>
                        <a class="j-next" href="javascript:;"></a>
                    </div>
                </div><!--show-box -->

                <div class="right">
                    <div class="service-information">
                        <div class="title">服务信息</div>
                      <#if pageAdvs?? && (pageAdvs?size>0)>
                      	<#list pageAdvs as pageAdv>
                      	<#if (pageAdv.adverSort>=2) && (pageAdv.adverSort<=3) >
                        <div class="info-01"><a href="${pageAdv.adverHref}"><img class="lazy" alt="" data-original="${pageAdv.adverPath}"/></a></div>
                        </#if>
                        </#list>
                      </#if>
                       <!-- <div class="info-02"><a href="javascript:;"><img alt="" src="../images/images_06.jpg"/></a></div>-->
                    </div>
                    <div class="mall-news">
                        <div class="title">
                            <h4>${temp.expFleid2!'公告'}</h4>
                            <a class="more" href="information/list/">更多&gt;&gt;</a>
                        </div>
                        <div class="cont">
                            <ul class="mall-news-list">
                            <#if infoList?? && (infoList?size>0)>
                            	<#list infoList as info>
                            		<#if (info_index >= 0 ) && (info_index < 5 )>
                                		<li><a href="information/${info.infoId?c}">
												<#if info.title?length gt 18>
						                        		${info.title?substring(0,18)}
						                        <#else>
						                        		${info.title}
						                        </#if>
                                		</a></li>                                	
                                	</#if>
                                </#list>                                
                             </#if>  
                            </ul>
                        </div><!--cont-->
                    </div><!-mall-news-->
                </div><!--right-->
            </div><!--section-01-->

            <div class="section-02">
                <div class="hot-recommend">
                 <#if pageAdvs?? && (pageAdvs?size>0)>
                    <#list pageAdvs as pageAdv>
                    <#if (pageAdv.adverSort>=4) && (pageAdv.adverSort<=7) >
                    	<a href="${pageAdv.adverHref}"><img class="lazy" alt="" data-original="${pageAdv.adverPath}"/></a>
                   	</#if>
                    </#list>
                 </#if>
                </div>
               <#-- <div class="popular-brands">
                    <div class="title">
                        <h3>热门品牌</h3>
                        <a id="replace-btn" href="javascript:;"><b></b>换一批</a>
                        
                    </div>
                    <div class="cont">
                    <#if trademarkList?? &&(trademarkList?size>0) >
                    	<#list trademarkList as trademark >
                    	<#if (trademark.sort==1)>
                        	<div class="popular-left"><a href="${trademark.logoSrc}"><img class="lazy" alt="" data-original="${trademark.logoSrc}"/></a></div>
                        </#if>
                        </#list>
                    </#if>
                    <#if trademarkList?? &&(trademarkList?size>0) >
                    	<#list trademarkList as trademark >
                    	<#if (trademark.sort==2)>
                        	<div class="popular-right"><a href="${trademark.logoSrc}"><img class="lazy" alt="" data-original="${trademark.logoSrc}"/></a></div>
                        </#if>
                        </#list>
                    </#if>                       
                        <div class="brands-cont">
                            <ul class="brands-list">
                            <#if trademarkList?? && (trademarkList?size>0)>
                        	<#list trademarkList as trademark >
                        	<#if (trademark.sort>=3)>
                            <li><a href="${trademark.logoSrc}"><img  class="lazy" class="lazy" alt="" data-original="${trademark.logoSrc}"></a></li>
                            </#if>
                        	</#list> 
                        	</#if>
                            </ul>
                        </div>
                    </div><!--cont-->
                <#--</div>-->
            </div><!--section-02-->
            
<#if (floor.floorList)?? && (floor.floorList?size > 0)>
            <div class="section-03">
     <#list floor.floorList as floors>
                <div class="proLists" id="proLists0${floors_index+1}">
                	<div class="title">
                        <h3>${floors.storeyName}</h3>
                        <#if (floors.indexStoreyTagList)?? && (floors.indexStoreyTagList?size > 0)>
	                        <div class="proTabs">
	                            <#list floors.indexStoreyTagList as storeyTag> 
	                            	<#if (storeyTag.sort==1)>                  
	                            	<a class="cur" href="javascript:;">${storeyTag.name}</a>
	                            	<#else>
	                            	<a href="javascript:;">${storeyTag.name}</a>
	                            	</#if>
	                            </#list>
	                        </div>
	                    </#if>
                        <#--<a class="more" href="javascript:;">更多<b></b></a>-->
                    </div>
                    <div class="proWp">
   <#if (floors.indexStoreyTagList)?? && (floors.indexStoreyTagList?size > 0)>
   <#list floors.indexStoreyTagList as storeyTag>                                  
                        <div class="proCon">                                              
                        	<div class="proItem pro-01">
                     		<#list storeyTag.indexAdvertList as adv>                        	                        		
                        	 <#if adv.adverSort==1>
                                <a href="${adv.adverHref}"><img  class="lazy" alt="" data-original="${adv.adverPath}"/></a>
                             </#if>
                            </#list>                              
                                <div class="best-sellers">
                                    <h4>热卖</h4>
                                    <div class="sellers-cont">
                                    <#list floors.indexCateList as cates>
                                    	<#if cates_index==0 || cates_index==3 ||cates_index==6 ||cates_index==9>
                                        <a class="hot" href="list/${cates.id?c}-${floors.goodsCatId?c}.html">${cates.name}</a>
                                        <#else>
                                        <a href="list/${cates.id?c}-${floors.goodsCatId?c}.html">${cates.name}</a>
                                        </#if>
                                    </#list>                                        
                                    </div>
                                </div>
                            </div>
                            
                            <div class="proItem pro-02">
                            <#list storeyTag.indexAdvertList as adv>                        	                        		
                        	 <#if adv.adverSort==2>
                                <a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"/></a>
                             </#if>
                            </#list>                                 
                            </div>
                            
                            <div class="proItem pro-03">
                            <#list storeyTag.indexAdvertList as adv>                        	                        		
                        	 <#if adv.adverSort==3>
                                <a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"/></a>
                             </#if>
                            </#list>                                 
                            </div>
                            
                            <div class="proItem pro-04">
                            <#list storeyTag.indexAdvertList as adv>                        	                        		
                        	 <#if adv.adverSort==4>
                                <a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"/></a>
                             </#if>
                            </#list>                                 
                            </div>
                            
                            <div class="proItem pro-05">
                            <#list storeyTag.indexAdvertList as adv>                        	                        		
                        	 <#if adv.adverSort==5>
                                <a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"/></a>
                             </#if>
                            </#list>                               
                            </div>
                            
                            <div class="proItem pro-06">
                            <#list storeyTag.indexAdvertList as adv>                        	                        		
                        	 <#if (adv.adverSort>=6) && (adv.adverSort<=7)>
                                <a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"/></a>
                             </#if>
                            </#list> 
                            </div>
                            
                            <div class="proItem pro-07">
                            <#list storeyTag.indexAdvertList as adv>                        	                        		
                        	 <#if adv.adverSort==8>
                                <a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"/></a>
                             </#if>
                            </#list>                                 
                            </div>
                            
                            <div class="proItem pro-08">
                            <#list storeyTag.indexAdvertList as adv>                        	                        		
                        	 <#if adv.adverSort==9>
                                <a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"/></a>
                             </#if>
                            </#list>                                
                            </div>
                            
                        </div><!--proCon-->
                   </#list>
        		</#if>                       
                    </div><!--proWp-->
                </div><!--proLists01-->
 	</#list>               
            </div><!--section-03-->
</#if>
        </div><!--content-->
    </div><!--wrapper-->


    <div class="sideBar">
        <a class="sideItem" href="#proLists01">1F</a>
        <a class="sideItem" href="#proLists02">2F</a>
        <a class="sideItem" href="#proLists03">3F</a>
        <a class="sideItem" href="#proLists04">4F</a>
        <a id="backtoTop" href="javascript:;"></a>
    </div>
    
 <script>
$(function() {
        	//图片延迟加载
			$("img.lazy").lazyload({
				threshold: 200,
				effect: "fadeIn",
				failurelimit : 10,
				placeholder: "${basePath}/index_six/images/lazy_img.png",
				skip_invisible: false
			});
});



</script>