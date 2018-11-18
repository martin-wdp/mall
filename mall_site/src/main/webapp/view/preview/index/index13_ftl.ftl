<#assign basePath=request.contextPath>
<div class="show-box">
        <div id="slides">
        	<#if avc?? && (avc?size>0)>
        	<#list avc as bigAdvert>
            <a class="slide" href="${bigAdvert.adverHref}" style="background-image:url(${bigAdvert.adverPath});"></a>
            </#list>
            </#if>
        </div><!--slides-->
</div><!--show-box-->

    <div id="content">
        <div class="Bargain">
        	<#if pageAdvs?? && (pageAdvs?size>0)>
        		<#list pageAdvs as pageAdv>
        			<#if (pageAdv.adverSort>=2) && (pageAdv.adverSort<=4) >
            		<div class="item-img"><a href="${pageAdv.adverHref}"><img src="${pageAdv.adverPath}" width="290" height="198"/></a></div>
            		</#if>
            	</#list>
            </#if>
            
            <div class="item-img">
                <h1>${temp.expFleid2!'公告'}</h1>
                <ul>
                <#if infoList?? && (infoList?size>0)>
                	<#list infoList as info>
                	<#if (info_index >= 0 ) && (info_index <=4 ) >
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
                </#if>
                </ul>
            </div>
        </div><!--Bargain-->
        
 <#if (floor.floorList)?? && (floor.floorList?size > 0)>
 <#list floor.floorList as floors>
 <#if (floors.floorId%2==1)>   
        <div class="proLists" id="proLists0${floors.floorId}">
            <div class="title">
                <h1>${floors.storeyName}</h1>
                <!--<p>居家必备 低价来袭</p>-->
            </div>
            <div class="pro-con">
                <div class="pro-left">
                    <div class="proItem-m">
                    <#if (floors.indexAdvertList)?? &&(floors.indexAdvertList?size>0)>
                    <#list floors.indexAdvertList as adv>
                    <#if adv.adverSort==1>
                        <a href="${adv.adverHref}"><img src="${adv.adverPath}" width="891" height="282"/></a>
                    </#if>
                    </#list>
                    </#if>
                    </div>
                    <div class="proItem left">
                        <div class="clearfix">
                        	<#if (floors.indexAdvertList)?? &&(floors.indexAdvertList?size>0)>
                    		<#list floors.indexAdvertList as adv>
                    		<#if (adv.adverSort>=2) &&(adv.adverSort<=3)>
                            <div class="proItem-s left">
                                <a href="${adv.adverHref}"><img src="${adv.adverPath}" width="286" height="282"/></a>
                            </div>
                            </#if>
                            </#list>
                            </#if>
                        </div>
                        
                        <div class="proItem-l">
                        	<#if (floors.indexAdvertList)?? &&(floors.indexAdvertList?size>0)>
                    		<#list floors.indexAdvertList as adv>
                    		<#if (adv.adverSort==4)>
                            <a href="${adv.adverHref}"><img src="${adv.adverPath}" width="588" height="282"/></a>
                            </#if>
                            </#list>
                            </#if>
                            
                        </div>
                    </div><!--proItem-->
                    <div class="proItem-r left">
                    	<#if (floors.indexAdvertList)?? &&(floors.indexAdvertList?size>0)>
                    	<#list floors.indexAdvertList as adv>
                    	<#if (adv.adverSort==5)>
                        <a href="${adv.adverHref}"><img src="${adv.adverPath}" width="287" height="582"/></a>
                        </#if>
                        </#list>
                        </#if>
                    </div><!--proItem-r-->
                </div><!--pro-left-->
                <div class="pro-rig">
                	<#if (floors.indexAdvertList)?? &&(floors.indexAdvertList?size>0)>
                    	<#list floors.indexAdvertList as adv>
                    	<#if (adv.adverSort>=6) &&(adv.adverSort<=8)>
                    <div class="proItem-s">
                        <a href="${adv.adverHref}"><img src="${adv.adverPath}" width="286" height="282"/></a>
                    </div>
                    	</#if>
                        </#list>
                    </#if>
                </div><!--pro-rig-->
            </div><!--pro-con-->
        </div><!--proLists01-->
</#if>
<#if (floors.floorId%2==0)>  
        <div class="proLists" id="proLists02">
            <div class="title">
                <h1>${floors.storeyName}</h1>
                <!--<p>当即必备 居家首选</p>-->
            </div>
            <div class="pro-con">
                <div class="pro-left">
                    <div class="proItem right">
                        <div class="proItem-l">
                        <#if (floors.indexAdvertList)?? &&(floors.indexAdvertList?size>0)>
                   		<#list floors.indexAdvertList as adv>
                   		<#if adv.adverSort==1>
                            <a href="${adv.adverHref}"><img src="${adv.adverPath}" width="588" height="282"/></a>
                        </#if>
                        </#list>
                        </#if>
                        </div>
                        <div class="clearfix">
                        <#if (floors.indexAdvertList)?? &&(floors.indexAdvertList?size>0)>
                   		<#list floors.indexAdvertList as adv>
	                   		<#if (adv.adverSort>=2) &&(adv.adverSort<=3)>
	                            <div class="proItem-s left">
	                                <a href="${adv.adverHref}"><img src="${adv.adverPath}" width="286" height="282"/></a>
	                            </div>
	                        </#if>
                        </#list>
                        </#if>
                            <#--<div class="proItem-s left">
                                <a href="#"><img src="../images/images_07.jpg" width="286" height="282"/></a>
                            </div>-->
                        </div><!--clearfix-->
                    </div><!--right-->
                    <div class="proItem-r left">
                    	<#if (floors.indexAdvertList)?? &&(floors.indexAdvertList?size>0)>
                   		<#list floors.indexAdvertList as adv>
                   		<#if (adv.adverSort==4)>
                        <a href="${adv.adverHref}"><img src="${adv.adverPath}" width="287" height="582"/></a>
                        </#if>
                        </#list>
                        </#if>
                    </div>
                    <div style="clear:both;"></div>
                    <div class="proItem-m">
                    	<#if (floors.indexAdvertList)?? &&(floors.indexAdvertList?size>0)>
                   		<#list floors.indexAdvertList as adv>
                   		<#if (adv.adverSort==5)>
                        <a href="${adv.adverHref}"><img src="${adv.adverPath}" width="891" height="282"/></a>
                        </#if>
                        </#list>
                        </#if>
                    </div>
                </div><!--pro-left-->
                <div class="pro-rig">
                    <div class="proItem-s">
                    	<#if (floors.indexAdvertList)?? &&(floors.indexAdvertList?size>0)>
                   		<#list floors.indexAdvertList as adv>
                   		<#if (adv.adverSort==6)>
                        <a href="${adv.adverHref}"><img src="${adv.adverPath}" width="286" height="282"/></a>
                        </#if>
                        </#list>
                        </#if>
                    </div>
                    <div class="proItem-r">
                    	<#if (floors.indexAdvertList)?? &&(floors.indexAdvertList?size>0)>
                   		<#list floors.indexAdvertList as adv>
                   		<#if (adv.adverSort==7)>
                        <a href="${adv.adverHref}"><img src="${adv.adverPath}" width="287" height="582"/></a>
                        </#if>
                        </#list>
                        </#if>
                    </div>
                </div><!--pro-rig-->
            </div><!--pro-con-->
        </div><!--proLists02-->
</#if>
</#list>
</#if>        
       <#-- <div class="proLists" id="proLists03">
            <div class="title">
                <h1>品牌推荐</h1>
                <p>品牌保证 放心选购</p>
            </div>
            <div class="pro-con">
            
                <div class="brand-list" id="slide-brand">
                <#if trademarkList?? &&(trademarkList?size>0) >              
                    <div class="slide">
                        <ul>
                        <#list trademarkList as trademark >
                		  <#if (trademark.sort>=1) && (trademark.sort<=16)>
                            <li>
                                <a href="#">
                                    <img src="${trademark.logoSrc}" width="130" height="88"/>
                                    ${trademark.trademarkName}
                                </a>
                            </li>
                          </#if>
                 		</#list>  
                        </ul>
                    </div>
               </#if>   
                 
                <#if trademarkList?? &&(trademarkList?size>16) >  
                    <div class="slide">
                        <ul>
                        <#list trademarkList as trademark >
                 			<#if (trademark.sort>=17) && (trademark.sort<=32)>
	                            <li>
	                                <a href="#">
	                                    <img src="${trademark.logoSrc}" width="130" height="88"/>
	                                    ${trademark.trademarkName}
	                                </a>
	                            </li>
                            </#if>
                  		</#list>
                        </ul>
                    </div>
                </#if>  
                  
                 <#if trademarkList?? &&(trademarkList?size>32) >  
                    <div class="slide">
                        <ul>
                        <#list trademarkList as trademark >
                  		<#if (trademark.sort>=33) && (trademark.sort<=48)>
                            <li>
                                <a href="#">
                                    <img src="${trademark.logoSrc}" width="130" height="88"/>
                                    ${trademark.trademarkName}
                                </a>
                            </li>
                        </#if>
                  		</#list>
                        </ul>
                    </div>
                 </#if>               
                </div><!--brand-list-->  
            <!--</div><!--pro-con-->
        <!--</div><!--proLists03-->
    </div><!--content-->
    
<#if (floor.floorList)?? && (floor.floorList?size > 0)>    
    <div class="sideBar">
    <#list floor.floorList as floors>
    <a class="sideItem" href="#proLists0${floors.floorId}">${floors.floorId}F<span>${floors.storeyName}</span></a>
    </#list>
    <a id="backtoTop" href="javascript:;"></a>
   </div>
</#if>