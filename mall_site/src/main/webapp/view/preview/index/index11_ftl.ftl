 <div class="show-box">
        <div id="slides">
        <#if avc?? && (avc?size>0)>
         <#list avc as bigAdvert>
         	<a class="slide" href="${bigAdvert.adverHref}" style="background-image:url(${bigAdvert.adverPath});"></a>
         </#list>
        </#if>
        </div>
        <div class="content">
            <div class="new-side">
            	<#if pageAdvs?? && (pageAdvs?size>1)>
                    <#list pageAdvs as pageAdv>
                        <#if pageAdv.adverSort==2>
                            <div class="img-box"><a href="${pageAdv.adverHref}"><img alt="" src="${pageAdv.adverPath}" width="200" height="400"/></a></div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="on-sale">
            	<#if avs?? && (avs?size>0)>
            	<#list avs as smallAdvert>
            	<#if (smallAdvert.adverSort>=1) &&(smallAdvert.adverSort<=4) >
                <a href="${smallAdvert.adverHref}"><img src="${smallAdvert.adverPath}" width="240" height="260"/></a>
                </#if>
                </#list>
                </#if>
            </div>
        </div>
    </div><!--show-box-->

    <div id="content">
        <div class="section-01">
        <#--<#if trademarkList?? &&(trademarkList?size>0) >-->
            <ul class="big_brand">
            <#--<#list trademarkList as trademark >
            	<#if (trademark.sort<=2)>
                <li>
                    <a href="${trademark.logoSrc}">
                        <img src="${trademark.logoSrc}" width="180" height="138"/>
                    </a>
                </li>
                </#if>
			</#list>
                <li class="mid">

                </li>
                <#list trademarkList as trademark >
                <#if (trademark.sort>=3) &&(trademark.sort<=6)>
                <li>
                    <a href="${trademark.logoSrc}">
                        <img src="${trademark.logoSrc}" width="180" height="138"/>
                    </a>
                </li>
                </#if>
                </#list>

                <li class="mid">

                </li>
                <#list trademarkList as trademark >
                <#if (trademark.sort>=7) &&(trademark.sort<=8)>
                <li>
                    <a href="${trademark.logoSrc}">
                        <img src="${trademark.logoSrc}" width="180" height="138"/>
                    </a>
                </li>
                </#if>

                <#if (trademark.sort==9)>
                <li class="mid_brand">
                    <a href="${trademark.logoSrc}"><img src="${trademark.logoSrc}"/></a>
                </li>
                </#if>
           		</#list>-->


            <#if pageAdvs?? && (pageAdvs?size>2)>
                <#list pageAdvs as pageAdv>
                    <#if (pageAdv.adverSort>=3) && (pageAdv.adverSort<=4) >
                        <li>
                            <a href="${(pageAdv.adverHref)!''}">
                                <img src="${(pageAdv.adverPath)!''}" width="180" height="90"/>
                                <span class="name">${(pageAdv.adverName)!''}</span>
                                <span class="court">${(pageAdv.temp2)!''}</span>
                            </a>
                        </li>
                    </#if>
                </#list>
                <li class="mid">

                </li>

                <#list pageAdvs as pageAdv>
                    <#if (pageAdv.adverSort>=5) && (pageAdv.adverSort<=8) >
                        <li>
                            <a href="${(pageAdv.adverHref)!''}">
                                <img src="${(pageAdv.adverPath)!''}" width="180" height="90"/>
                                <span class="name">${(pageAdv.adverName)!''}</span>
                                <span class="court">${(pageAdv.temp2)!''}</span>
                            </a>
                        </li>
                    </#if>
                </#list>
                <li class="mid">

                </li>
                <#list pageAdvs as pageAdv>
                    <#if (pageAdv.adverSort>=9) && (pageAdv.adverSort<=10) >
                        <li>
                            <a href="${(pageAdv.adverHref)!''}">
                                <img src="${(pageAdv.adverPath)!''}" width="180" height="90" />
                                <span class="name">${(pageAdv.adverName)!''}</span>
                                <span class="court">${(pageAdv.temp2)!''}</span>
                            </a>
                        </li>
                    </#if>
                </#list>

                <#list pageAdvs as pageAdv>
                    <#if (pageAdv.adverSort==11) >
                        <li class="mid_brand">
                            <a href="${(pageAdv.adverHref)!''}"><img src="${(pageAdv.adverPath)!''}"/></a>
                        </li>
                    </#if>
                </#list>
            </#if>

            </ul>
        <#--</#if>-->
        </div><!--section-01-->
<#if (floor.floorList)?? && (floor.floorList?size > 0)>
        <div class="section-02">
        <#list floor.floorList as floors>
            <div class="proLists" id="proLists0${floors.floorId}">
                <div class="title">
                    <h2>${floors.storeyName}</h2>
                    <#--<div class="proTabs">
                        <a class="cur" href="javascript:;">国际大牌</a>
                        <a href="javascript:;">国妆名品</a>
                        <a href="javascript:;">个人护理</a>
                        <a href="javascript:;">香水彩妆</a>
                        <a href="javascript:;">热销面膜</a>
                        <a href="javascript:;">男士精品</a>
                    </div>-->
                </div><!--title-->
                <div class="proWp">
                    <div class="proBox">
                    <#if (floors.indexCateList)?? && (floors.indexCateList?size > 0)>
                        <div class="pLinks">
                        <#list floors.indexCateList as cates>
                            <a href="list/${cates.id?c}-${floors.goodsCatId?c}.html">${cates.name}</a>
                        </#list>
                        </div><!--pLinks-->
                    </#if>
                    <#if (floors.indexAdvertList)?? &&(floors.indexAdvertList?size>0)>
                    <#list floors.indexAdvertList as adv>
                    	<#if adv.adverSort==1>
                        <a href="${adv.adverHref}"><img src="${adv.adverPath}" alt=""/></a>
                        </#if>
                   	</#list>
                   	</#if>
                        <#if (floors.indexBrandList)?? && (floors.indexBrandList?size > 0)> 
                        <div class="pBrand">
                        	<#list floors.indexBrandList as brand>
                        	<#if brand.sort<=6>
                            <a href=""><img src="${brand.logoSrc}"  width="70" height="35"/></a>
                            </#if>
                            </#list>
                        </div><!--pBrand-->
                        </#if>
                    </div><!--proBox-->
                    <#if (floors.indexAdvertList)?? &&(floors.indexAdvertList?size>0)>
                    <div class="pro_new">
                        <div class="pro_upper">
                            <div class="left">
                            <#list floors.indexAdvertList as adv>
                            	<#if adv.adverSort==2>
                                <a href="${adv.adverHref}"><img src="${adv.adverPath}" width="542" height="298"/></a>
                                </#if>
                            </#list>
                            </div>
                            <div class="right">
                                <div class="upside">
                                <#list floors.indexAdvertList as adv>
	                                <#if adv.adverSort==3>
	                                <a href="${adv.adverHref}"><img src="${adv.adverPath}" width="395" height="138"/></a>
	                                </#if>
                                </#list>
                                </div>
                                <ul>
                                <#list floors.indexAdvertList as adv>
                                <#if (adv.adverSort>=4) && (adv.adverSort<=5) >
                                    <li><a href="${adv.adverHref}"><img src="${adv.adverPath}" width="197" height="159"/></a></li>
                                </#if>
                                </#list>
                                </ul>
                            </div>
                        </div><!--pro_upper-->
                        <div class="pro_under">
                            <ul>
                            <#list floors.indexAdvertList as adv>
                            	 <#if (adv.adverSort>=6) && (adv.adverSort<=8) >
                                <li><a href="${adv.adverHref}"><img src="${adv.adverPath}" width="180" height="207"/></a></li>
                                </#if>
                                <#if (adv.adverSort>=9) && (adv.adverSort<=10) >
                                <li class="rig_n"><a href="${adv.adverHref}"><img src="${adv.adverPath}" width="197" height="207"/></a></li>
                                </#if>
                            </#list>
                            </ul>
                        </div><!--pro_under-->
                    </div><!--pro_new-->
                </#if>
                </div><!--proWp-->
            </div><!--proLists01-->
        </#list>
        </div><!--section-02-->
</#if>
    </div><!--content-->

<#if (floor.floorList)?? && (floor.floorList?size > 0)>
<div class="sideBar">
<#list floor.floorList as floors>
    <a class="sideItem" href="#proLists0${floors.floorId}">${floors.floorId}F<span>${floors.storeyName}</span></a>
</#list>
    <a id="backtoTop" href="javascript:;"></a>
</div><!--sideBar-->
</#if>   
    
    