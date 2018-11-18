 <#assign basePath=request.contextPath> 
<div id="wrapper">      
        <div id="content">
            <div class="section-01">
                <div class="popular-brands">
                    <div class="title">
                        <h2>热门品牌</h2>
                        <a id="replace-btn" href="javascript:;"><b></b>换一批</a>
                    </div>
                    <div class="cont">
                    	<#if pageAdvs?? && (pageAdvs?size>0)>
                    		<#list pageAdvs as pageAdv>
                    		<#if (pageAdv.adverSort==2)>
                        		<div class="popular-left"><a href="${pageAdv.adverHref}"><img alt="" src="${pageAdv.adverPath}"/></a></div>
                        	</#if>
                        	<#if (pageAdv.adverSort==3)>
                        		<div class="popular-right"><a href="${pageAdv.adverHref}"><img alt="" src="${pageAdv.adverPath}"/></a></div>
                        	</#if>
                        	</#list>
                        </#if>
                        <div class="brands-cont">
                            <ul class="brands-list">
                            <#if trademarkList?? &&(trademarkList?size>0) >
                            	<#list trademarkList as trademark >	
                                <li><a href="${trademark.url!''}"><img alt="" src="${trademark.logoSrc}"/></a></li>
                                </#list>
                            </#if>
                            </ul>
                        </div>
                    </div>
                </div>
                <#if pageAdvs?? && (pageAdvs?size>0)>
                  <#list pageAdvs as pageAdv>
                    <#if (pageAdv.adverSort==4)>
                <div class="hotBar"><a href="${pageAdv.adverHref}"><img alt="" src="${pageAdv.adverPath}"/></a></div>
                	</#if>
                  </#list>
                </#if>  
            </div>
<#if (floor.floorList)?? && (floor.floorList?size > 0)>
            <div class="section-02">
 <#list floor.floorList as floors>
                 <div class="proLists" id="proLists0${floors.floorId}">
                    <div class="proBox">
                        <div class="title">
                            <span>${floors.floorId}F</span>
                            <h1><img alt="" src="${floors.storeyImg}"/>${floors.storeyName}</h1>
                        </div>
                        <#if (floors.indexAdvertList)?? && (floors.indexAdvertList?size > 0)>
                        <#list floors.indexAdvertList as adv>
                      		<#if adv.adverSort==1>
                        		<a href="${adv.adverHref}"><img alt="" src="${adv.adverPath}"/></a>
                        	</#if>
                        </#list>
                       	</#if>	
                        
                        <#if (floors.indexCateList)?? && (floors.indexCateList?size > 0)>
                        <div class="plinks">
                        	<#list floors.indexCateList as cates>
                            <a href="list/${cates.id?c}-${floors.goodsCatId?c}.html">${cates.name}</a>
                            </#list>                            
                        </div>
                        </#if>
                    </div><!--proBox-->
                    <div class="proWp">
                    	<#if (floors.indexAdvertList)?? && (floors.indexAdvertList?size > 0)>
                        <#list floors.indexAdvertList as adv>
                      		<#if adv.adverSort==2>
                        		<a class="hot-pro" href="${adv.adverHref}"><img alt="" src="${adv.adverPath}"/></a>
                        	</#if>
                        </#list>
                       	</#if>	
                        
                        <div class="proList">
                            <ul>
                           	<#if (floors.indexAdvertList)?? && (floors.indexAdvertList?size > 0)>
                        	<#list floors.indexAdvertList as adv>
                      		<#if (adv.adverSort>=3) && (adv.adverSort<=8)>
                                <li class="pro01"><a href="${adv.adverHref}"><img src="${adv.adverPath}" alt=""/></a></li>
                            </#if>
                            </#list>
                            </#if>
                            </ul>
                        </div>
                    </div><!--proWp-->
                </div><!--proLists01-->
</#list>
            </div><!--section-02-->
</#if>
        </div><!--content -->
    </div><!--wrapper-->
<script src="${basePath}/index_eight/js/angular.min.js"></script>
<script src="${basePath}/index_eight/js/template.js"></script>
