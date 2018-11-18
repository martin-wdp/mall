<#assign basePath=request.contextPath>
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
        <div class="section-00">
            <div class="now_go">
                <div class="title">马上出发<span>人生需要说走就走的旅行</span></div>
                <ul>
                <#if pageAdvs?? && (pageAdvs?size>0)>
                <#list pageAdvs as pageAdv>
                <#if (pageAdv.adverSort>=2)&&(pageAdv.adverSort<=4)>
                    <li>
                        <img src="${pageAdv.adverPath}" width="386" height="200"/>
                        <div class="travel-info">
                            <a href="${pageAdv.adverHref}" class="bg-01 fl">
                               ${pageAdv.adverName}
                            </a>
                            <a href="${pageAdv.adverHref}" class="bg-02 fl">
                                ${pageAdv.temp2}
                            </a>
                        </div><!--travel-info-->
                    </li>
                </#if>
                </#list>
         		</#if>           
                    
                </ul>
            </div><!--now_go-->
        </div><!--section-00-->
        <div class="section-01">
            <div class="recommend">
                <h2 class="title">热门推荐</h2>
                <div class="travel-left">
                    <ul>
                    <#if pageAdvs?? && (pageAdvs?size>0)>
                	<#list pageAdvs as pageAdv>
                	<#if (pageAdv.adverSort>=5)&&(pageAdv.adverSort<=8)>
                        <li>
                            <a href="${pageAdv.adverHref}"><img src="${pageAdv.adverPath}" width="240" height="174"/></a>
                            <div class="detail">
                                <a href="#">${pageAdv.adverName}</a>
                                <p>${pageAdv.temp2}</p>
                            </div>
                        </li>
                    </#if>
                    </#list>
                    </#if>
                    </ul>
                </div><!--travel-left-->
                <div class="travel-rig">
                    <div class="tagMenu">
                        <ul class="menu">
                            <li class="current">品牌</li>
                            <li>商城公告</li>
                        </ul>
                    </div>
                    <div class="content">
                        <div class="layout">
                            <ul class="brand-list">
                            <#if trademarkList?? &&(trademarkList?size>0) > 
                            <#list trademarkList as trademark > 
                             <#if (trademark.sort>=1) && (trademark.sort<=10)>
                                <li>
                                    <a href="${trademark.url!''}"><img src="${trademark.logoSrc}" width="88" height="28"/></a>
                                </li>
                            </#if>
                            </#list>
                           	</#if>
                            </ul>
                        </div><!--layout-->
                        <div class="layout">
                            <ul class="notice-list">
                            <#if infoList?? && (infoList?size>0)>
                            <#list infoList as info>
                       			<#if (info_index >= 0 ) && (info_index <= 7 ) >
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
                        </div><!--layout-->
                    </div><!--content-->
                </div><!--travel-rig-->
            </div><!--recommend-->
        </div><!--section-01-->
<#if (floor.floorList)?? && (floor.floorList?size > 0)>
        <div class="section-02">
	 <#list floor.floorList as floors>       
            <div class="proLists" id="proLists0${floors.floorId}">
                <div class="title">
                    <div class="bg-0${floors.floorId}"></div>
                    <div class="bg-menu-0${floors.floorId}">
                        <div class="pro-title">
                            <h2>${floors.storeyName}</h2>
                            <p><!--就是要抢先尝--></p>
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
                     </div><!--bg-menu-01-->
                </div><!--title-->
                
                <div class="proWp">
                <#if (floors.indexStoreyTagList)?? && (floors.indexStoreyTagList?size > 0)>
                    <div class="proCon">
                    <#list floors.indexStoreyTagList as storeyTag>
                  	<#if (storeyTag.sort%3==1)>  
                    <#if (storeyTag.indexGoodsList)?? && (storeyTag.indexGoodsList?size>0)>
                        <div class="proItem">
                            <ul class="proList">
                            <#list storeyTag.indexGoodsList as goods>
                                <li>
                                    <a class="p-img" href="item/${goods.id}"><img alt="" src="${goods.urlpic}" style="height:120px;width:232px"/></a>
                                    <p class="p-name"><a href="item/${goods.id}">
                                    			<#if (goods.name?length>30)>
			                                    	${goods.name?substring(0,30)}
				                                 <#else>
					                                 ${goods.name}
				                                 </#if>
                                    </a>
                                      <span><!--两大升级，赏樱花，乐天世界超值爆款--></span>
                                    </p>
                                    <p class="p-price"><span></span>&yen;<strong>${goods.price}</strong>起</p>
                                </li> 
                             </#list>
                            </ul>
                        </div><!--proItem-->
                     </#if>
                    </#if>
                    <#if (storeyTag.sort%3==2)>
                    <div class="proItem">
                            <ul class="proList">
                            <#if (storeyTag.indexGoodsList)?? && (storeyTag.indexGoodsList?size>0)>
                            <#list storeyTag.indexGoodsList as goods>
	                            <#if (goods_index=0)>
	                                <li>
	                                    <a class="p-img" href="item/${goods.id}"><img alt="" src="${goods.urlpic}" style="height:120px;width:232px"/></a>
	                                    <p class="p-name"><a href="item/${goods.id}">
	                                    		<#if (goods.name?length>30)>
			                                    	${goods.name?substring(0,30)}
				                                 <#else>
					                                 ${goods.name}
				                                 </#if>
	                                    	</a>
	                                        <span><!--两大升级，赏樱花，乐天世界超值爆款--></span>
	                                    </p>
	                                    <p class="p-price"><span></span>&yen;<strong>${goods.price}</strong>起</p>
	                                </li>
	                            </#if>
                            </#list>
                            </#if>
 								<#if (storeyTag.indexAdvertList)?? && (storeyTag.indexAdvertList?size>0)>
                            	<#list storeyTag.indexAdvertList as adv>
	                            	<#if adv.adverSort==1>
		                                <li class="normal-img">
		                                    <a href="${adv.adverHref}"><img src="${adv.adverPath}" width="485" height="215"/></a>
		                                </li>
	                                </#if>
                            	</#list>
                            	</#if>
                            	<#if (storeyTag.indexGoodsList)?? && (storeyTag.indexGoodsList?size>0)>
                           		<#list storeyTag.indexGoodsList as goods>
		                            <#if (goods_index>=1) &&(goods_index<=2)>
	                                <li>
	                                    <a class="p-img" href="item/${goods.id}"><img alt="" src="${goods.urlpic}" style="height:120px;width:232px"/></a>
	                                    <p class="p-name"><a href="item/${goods.id}">
	                                    	<#if (goods.name?length>30)>
			                                    	${goods.name?substring(0,30)}
				                            <#else>
					                                 ${goods.name}
				                            </#if>
	                                    	</a>
	                                        <span><!--米哈斯，诺坎普球场，大城小镇之旅--></span>
	                                    </p>
	                                    <p class="p-price"><span></span>&yen;<strong>${goods.price}</strong>起</p>
	                                </li>
	                                </#if>
                            	</#list>
                            	</#if>
                            	
                               <#if (storeyTag.indexAdvertList)?? && (storeyTag.indexAdvertList?size>0)>
                            	<#list storeyTag.indexAdvertList as adv>
	                            	<#if adv.adverSort==2> 
		                                <li class="normal-img">
		                                    <a href="${adv.adverHref}"><img src="${adv.adverPath}" width="485" height="215"/></a>
		                                </li>
                                	</#if>
                            	</#list>
                            	</#if>
                            	
                            	<#if (storeyTag.indexGoodsList)?? && (storeyTag.indexGoodsList?size>0)>
                           		<#list storeyTag.indexGoodsList as goods>
		                            <#if (goods_index==3)>
		                                <li>
		                                    <a class="p-img" href="item/${goods.id}"><img alt="" src="${goods.urlpic}" style="height:120px;width:232px"/></a>
		                                    <p class="p-name"><a href="item/${goods.id}">
		                                    	<#if (goods.name?length>30)>
			                                    	${goods.name?substring(0,30)}
				                            	<#else>
					                                 ${goods.name}
				                            	</#if>
		                                    </a>
		                                        <span><!--两大升级，赏樱花，乐天世界超值爆款--></span>
		                                    </p>
		                                    <p class="p-price"><span></span>&yen;<strong>${goods.price}</strong>起</p>
		                                </li>
                                	</#if>
                               	</#list>
                               	</#if>	
                            </ul>
                        </div><!--proItem--> 
                  </#if>
                    <#if (storeyTag.sort%3==0)>    
                        <div class="proItem">
                            <ul class="proList">
                            <#if (storeyTag.indexAdvertList)?? && (storeyTag.indexAdvertList?size>0)>
                            	<#list storeyTag.indexAdvertList as adv>
	                            	<#if adv.adverSort==1>
                                	<li class="big-img"><a href="${adv.adverHref}"><img src="${adv.adverPath}" width="733" height="440"/></a></li>
                                	</#if>
                               	</#list>
                           </#if>
                           <#if (storeyTag.indexGoodsList)?? && (storeyTag.indexGoodsList?size>0)>
                           		<#list storeyTag.indexGoodsList as goods>
		                            <#if (goods_index>=0) &&(goods_index<=1)>
		                                <li>
		                                    <a class="p-img" href="item/${goods.id}"><img alt="" src="${goods.urlpic}" style="height:120px;width:232px"/></a>
		                                    <p class="p-name"><a href="item/${goods.id}">
		                                    	<#if (goods.name?length>30)>
			                                    	${goods.name?substring(0,30)}
				                            	<#else>
					                                 ${goods.name}
				                            	</#if>
		                                    </a>
		                                        <span><!--4晚6日自助游，上海新航凌晨机款--></span>
		                                    </p>
		                                    <p class="p-price"><span></span>&yen;<strong>20131</strong>起</p>
		                                </li>
                                	</#if>
                               	</#list>
                           </#if>
                            </ul>
                        </div><!--proItem-->
                     </#if> 
         			</#list>
                    </div><!--proCon-->
            </#if>
            </div><!--proWp-->
            </div><!--proLists01-->  
       </#list>               
        </div><!--section-02-->
</#if>        
    </div><!--content-->
    
    <div class="sideBar">
	<#if (floor.floorList)?? && (floor.floorList?size > 0)>
		<#list floor.floorList as floors>     
	    <a class="sideItem" href="#proLists0${floors.floorId}">${floors.floorId}F<span>${floors.storeyName}</span></a>
	    </#list>
	</#if>    
    <a id="backtoTop" href="javascript:;"></a>
	</div>