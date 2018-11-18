<#assign basePath=request.contextPath>
<input type="hidden" id="isIndex" value="1"/>
    <div class="show-box">
        <div id="slides">
        	<#if avc?? && (avc?size>0)>
        	<#list avc as bigAdvert>
            <a class="slide" href="${bigAdvert.adverHref}" style="background-image:url(${bigAdvert.adverPath});"></a>
            </#list>
            </#if>
        </div>
    </div><!--show-box-->

    <div id="content">
        <div class="section-01">
            <div class="f-brand">
                <div class="famous_brand left">
                    <div class="title">知名品牌 放心选购</div>
                    <#if trademarkList?? &&(trademarkList?size>0) >
                    <ul>
                    	<#list trademarkList as trademark >
                    	<#if (trademark.sort>=1) && (trademark.sort<=16)>
                        <li><a href="${trademark.url!''}"><img src="${trademark.logoSrc}" style="width:120px;height:40px;margin:16px 25px;"/></a></li>
                        </#if>
                        </#list>
                    </ul>
                    </#if>
                </div><!--famous_brand-->
                <div class="news-center right">
                    <div class="title"><a href="information/list/" class="right">更多<i></i></a>新闻中心</div>
                    <div class="pet-news">
                    	<#if pageAdvs?? && (pageAdvs?size>1)>
                        <a href="${pageAdvs[1].adverHref}"><img src="${pageAdvs[1].adverPath}" width="441" height="150"></a>
                        </#if>
                        <ul>
                        	<#if infoList?? && (infoList?size>0)>
                        	<#list infoList as info>
                        	<#if (info_index >= 0 ) && (info_index < 5 )>
                            <li><a href="information/${info.infoId?c}" title="${info.title}">
                          				<#if info.title?length gt 18>
	                        				${info.title?substring(0,18)}
	                        			<#else>
	                        				${info.title}
	                        			</#if>	
                            </a><span>${info.createDate?string("yyyy-MM-dd")}</span></li>
                            </#if>
                            </#list>
                            </#if>
                        </ul>
                    </div>
                </div><!--news-center-->
            </div><!--f-brand-->
        </div><!--section-01-->
<#if (floor.floorList)?? && (floor.floorList?size > 0)>
        <div class="section-02"> 
        <#list floor.floorList as floors>       
            <div class="proLists" id="proLists0${floors.floorId}">
                <div class="title">
                    <h2><img src="${floors.storeyImg}"/>${floors.storeyName}</h2>
                    <#--<div class="proTabs">
                        <a class="cur" href="javascript:;">主粮</a>
                        <a href="javascript:;">妙鲜包</a>
                        <a href="javascript:;">保健品</a>
                        <a href="javascript:;">零食</a>
                        <a href="javascript:;">日用品</a>
                        <a href="javascript:;">美容</a>
                        <a href="javascript:;">玩具</a>
                        <a href="javascript:;">衣服</a>
                        <a href="#" class="more">更多<i></i></a>
                    </div>-->
                </div><!--title-->
                <div class="proWp">
                    <div class="proBox">
                    	<#if (floors.indexAdvertList)?? &&(floors.indexAdvertList?size>0)>
                   		<#list floors.indexAdvertList as adv>
                   		<#if adv.adverSort==1>
                        <a href="${adv.adverHref}"><img src="${adv.adverPath}" alt=""/></a>
                        </#if>
                        </#list>
                        </#if>
                        <#if (floors.indexCateList)?? && (floors.indexCateList?size > 0)>
                        <div class="pLinks">
                            <#list floors.indexCateList as cates>
                            <a href="list/${cates.id?c}-${floors.goodsCatId?c}.html">${cates.name}</a>
                            </#list>
                        </div>
                       	</#if>
                    </div><!--proBox-->
                    <div class="pro_new">
                        <div class="pro_left left">
                            <div class="p_slides">
                            <#if (floors.indexAdvertList)?? &&(floors.indexAdvertList?size>0)>
                    		<#list floors.indexAdvertList as adv>
                    		<#if (adv.adverSort>=2) &&(adv.adverSort<=5)>
                                <a href="${adv.adverHref}" class="slide"><img src="${adv.adverPath}" width="480" height="459"></a>
                            </#if>
                            </#list>
                            </#if>
                            </div>
                        </div><!--pro_left-->
                        <div class="pro_rig right">
                            <ul>
                            <#if (floors.indexAdvertList)?? &&(floors.indexAdvertList?size>0)>
                    		<#list floors.indexAdvertList as adv>
                    		<#if (adv.adverSort>=6) &&(adv.adverSort<=9)>
                                <li><a href="${adv.adverHref}"><img src="${adv.adverPath}" width="229" height="229"/></a></li>
                            </#if>
                            </#list>
                            </#if>
                            </ul>
                        </div><!--pro_rig-->
                    </div><!--pro_new-->
                    <div class="clear"></div>
                    <div class="j-list">
                        <div class="jscroll-wp">
                            <ul>
                            <#if (floors.indexGoodsList)??  && (floors.indexGoodsList?size > 0) >
                            <#list floors.indexGoodsList as goods>
                                <li>
                                    <a class="sale-img" href="item/${goods.id}"><img alt="" src="${goods.urlpic}" width="118" height="125"/></a>
                                    <div class="sale-info">
                                        <p class="sale-name"><a href="item/${goods.id}">${goods.name}</a></p>
                                        <p class="sale-price">促销价&nbsp;<span>&yen;${goods.price}</span></p>
                                       <#-- <a class="sale-btn" href="javascript:;">已有35435人评论</a>-->
                                    </div>
                                </li>
                            </#list>
                            </#if>
                            </ul>
                        </div><!--jscroll-wp-->
                        <div class="j-prev"><a  href="javascript:;"></a></div>
                        <div class="j-next"><a  href="javascript:;"></a></div>
                    </div><!--j-list-->
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
    </div>
</#if>