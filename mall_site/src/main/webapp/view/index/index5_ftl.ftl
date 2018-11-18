<#assign basePath=request.contextPath>
<input type="hidden" id="isIndex" value="1"/>
<div id="wrapper">
   <div id="content">
		<div id="slides">
			<#if avc?? && (avc?size>0)>
				<#list avc as bigAdvert>
                <a class="slide" href="${bigAdvert.adverHref}" style="background-image:url(${bigAdvert.adverPath});"></a>
                </#list>
            </#if>   
        </div>

		<div class="section-01">
                <div class="left">
                    <div class="topShows">
                    <#if pageAdvs?? && (pageAdvs?size>0)>                    
                    	<#list pageAdvs as pageAdv>
                    		<#if (pageAdv.adverSort>=2) && (pageAdv.adverSort<=5) >
                        		<a href="${pageAdv.adverHref}"><img class="lazy" alt="" data-original="${pageAdv.adverPath}"></a>
                        	</#if>
                        </#list>
                    </#if>
                                            
                    </div>
                    <div class="brands">
                        <h3><strong>品牌</strong>BRANDS</h3>
                        <ul>
                        <#if trademarkList?? &&(trademarkList?size>0) >
	                        <#list trademarkList as trademark >	                                        
	                        	<#if (trademark.sort>=1) && (trademark.sort<=8)>
	                            	<li><a href="${trademark.url!''}"><img class="lazy" alt="" data-original="${trademark.logoSrc}" width="150" height="50" style="margin: 20px 45px;"></a></li>
	                            </#if>
	                        </#list>
	                    </#if>                            
                        </ul>
                    </div>
                </div>
                <div class="right">
                    <div class="news">
                        <div class="tit"><h3>${temp.expFleid2!'公告'}</h3></div>
                        <div class="cont">
                            <ul>
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
                        </div>
                    </div>
                    <div class="firAdv">
                    <#if pageAdvs?? && (pageAdvs?size>0)>
                        <#list pageAdvs as pageAdv>
                            <#if (pageAdv.adverSort==6)  >
                                <a href="${pageAdv.adverHref}"><img class="lazy" alt="" data-original="${pageAdv.adverPath}"></a>
                            </#if>
                        </#list>
                    </#if>
                    </div>
                    <div class="secAdv">
                    <#if pageAdvs?? && (pageAdvs?size>0)>
                        <#list pageAdvs as pageAdv>
                            <#if (pageAdv.adverSort==7)  >
                                <a href="${pageAdv.adverHref}"><img class="lazy" alt="" data-original="${pageAdv.adverPath}"></a>
                            </#if>
                        </#list>
                    </#if>
                    </div>
                </div>
            </div>

			 <div class="section-02">
                <div class="title">
                    <h3>热门推荐</h3>
                    <a class="changeBtn" href="javascript:;">换一组</a>
                </div>
                <div class="recommend-wp">
                <#if pageAdvs?? && (pageAdvs?size>7)>
                <ul>

                    	<#list pageAdvs as pageAdv>
                    		<#if (pageAdv.adverSort>=8) && (pageAdv.adverSort<=12) >
                    			<#if (pageAdv.adverSort==8)>
                        			<li class="main-recommend"><a href="${pageAdv.adverHref}"><img class="lazy" alt="" data-original="${pageAdv.adverPath}"></a></li>
                        		<#else>
                        			<li><a href="${pageAdv.adverHref}"><img class="lazy" alt="" data-original="${pageAdv.adverPath}"></a></a></li>
                        		</#if>
                        	</#if>
                        </#list>

	                   <#--<#if hotSale?? && (hotSale?size>0)>
	                      	
					      <#list hotSale as hotgoods> 
                     			<#if (hotgoods.sort>=1) &&(hotgoods.sort<=5)  > 
		                    	  <#if (hotgoods.sort==4)>                 
		                        	<li class="main-recommend"><a href="item/${(hotgoods.goodsproductId?c)!''}"><img class="lazy" alt="" data-original="${(hotgoods.goodsproductImgsrc)!''}"></a></li>
		                          <#else>
		                        	<li><a href="item/${(hotgoods.goodsproductId?c)!''}"><img class="lazy" alt="" data-original="${(hotgoods.goodsproductImgsrc)!''}"></a></li>
		                          </#if>
		                       	</#if> 
	                    </#list>
	                    </#if>-->
	                    </ul>
                </#if>
                <#if pageAdvs?? && (pageAdvs?size>12)>
                <ul>

                    	<#list pageAdvs as pageAdv>
                    		<#if (pageAdv.adverSort>=13) && (pageAdv.adverSort<=17) >
                    			<#if (pageAdv.adverSort==13)>
                        			<li class="main-recommend"><a href="${pageAdv.adverHref}"><img class="lazy" alt="" data-original="${pageAdv.adverPath}"></a></li>
                        		<#else>
                        			<li><a href="${pageAdv.adverHref}"><img class="lazy" alt="" data-original="${pageAdv.adverPath}"></a></a></li>
                        		</#if>
                        	</#if>
                        </#list>

                    </ul>
                </#if>
                <#if pageAdvs?? && (pageAdvs?size>17)>
                    <ul>                    

                    	<#list pageAdvs as pageAdv>
                    		<#if (pageAdv.adverSort>=18) && (pageAdv.adverSort<=22) >
                    			<#if (pageAdv.adverSort==18)>
                        			<li class="main-recommend"><a href="${pageAdv.adverHref}"><img class="lazy" alt="" data-original="${pageAdv.adverPath}"></a></li>
                        		<#else>
                        			<li><a href="${pageAdv.adverHref}"><img class="lazy" alt="" data-original="${pageAdv.adverPath}"></a></a></li>
                        		</#if>
                        	</#if>
                        </#list>

                    </ul>
                </#if>
                </div>
            </div>

<#if (floor.floorList)?? && (floor.floorList?size > 0)>
        <div class="section-03">
     <#list floor.floorList as floors>   
            <div class="proLists proLists0${floors_index+1}" id="lists0${floors_index+1}">                    
				<div class="title">
						 <#if (floors.indexStoreyTagList)?? && (floors.indexStoreyTagList?size > 0)>
	                        <ul class="flr-tabs">
	                        <#list floors.indexStoreyTagList as storeyTag>
	                           <#if (storeyTag.sort==1)>
	                            <li class="cur"><a href="javascript:;">${storeyTag.name}</a></li>
	                            <#else>
	                            <li><a href="javascript:;">${storeyTag.name}</a></li>
	                          </#if>
	                        </#list>                           
	                        </ul>
	                     </#if>
                </div>
              <div class="proWp">
              	<#if (floors.indexStoreyTagList)?? && (floors.indexStoreyTagList?size > 0)>
              		<#list floors.indexStoreyTagList as storeyTag>
				 <div class="proCon">
					<div class="proAd">
								<#if (storeyTag.indexAdvertList)?? && (storeyTag.indexAdvertList?size>0)>
									<#list storeyTag.indexAdvertList as adv>
										<#if (adv.adverSort==1)>
	                                <a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"></a>
	                                	</#if>
	                                </#list>
	                            </#if>
                                <div class="adCon">
                                    <dl>
                                        <dt>热门推荐</dt>
                                        <dd>
                                        <#list floors.indexCateList as cates>
                                            <a href="list/${cates.id?c}-${floors.goodsCatId?c}.html">${cates.name}</a>
                                        </#list>                                            
                                        </dd>                                       
                                    </dl>
                                </div>
                    </div><!--proAd-->

					<div class="pros">
                                <ul>
                                <#if (storeyTag.indexGoodsList)?? && (storeyTag.indexGoodsList?size>0)>
                                	<#list storeyTag.indexGoodsList as goods>
                                	   <#if (goods_index>=0)&&(goods_index<=7)>
		                                    <li>
		                                        <a class="proImg" href="item/${goods.id}"><img class="lazy" alt="" data-original="${goods.urlpic}" width="150" height="150"></a>
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
                     </div><!--pros-->

					 <div class="othAd">
					 <#if (storeyTag.indexAdvertList)?? && (storeyTag.indexAdvertList?size>0)>
						<#list storeyTag.indexAdvertList as adv>
					        <#if (adv.adverSort>1) &&(adv.adverSort<=5)>
                          		<a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"></a>
                          	</#if>
                         </#list>
                     </#if>
                     </div><!--othAd-->				
					</div><!--proCon-->
                    </#list>
        		</#if> 
                </div><!--proWp -->
            </div><!--proLists -->
</#list>
        </div><!--section-03-->
</#if>
    </div><!--content-->
</div><!--wrapper -->
<div class="side-bar">
            <b></b>
            <div class="cartfd">
                <s class="cartBanner"></s>
                <div class="cartit">
                    <span><s></s>购物车</span>
                    <strong class="cartNum">0</strong>
                </div>
                <div class="miniCart hide">
                    <div class="mCartBox">
                        <!-- mcBoxTop 居上，滚动时依然居上mcFloat展示 -->
                        <div class="mcBoxTop clearfix">
                            <div class="mcChk"><input type="checkbox" /></div>
                            <label for="" class="mcElect">全选</label>
                        </div>
                        <div class="mcBoxList cart_list">
                          
                          
                            
                        </div>
                        
                        
                        <div class="emCartBox hide">
                            <span>购物车中还没有商品，再去逛逛吧！</span>
                        </div>
                    </div>
                    <div class="mcGenius bmcGenius"></div><!-- 展开加class"bmcGenius" -->
                    <div class="mCartError"><!-- 显示错误时bottom:41px -->
                        <p>正在为您加载数量！</p>
                    </div>
                    <div class="mCartHandler clearfix">
                <span class="mcCashier">
                    <span class="mcTotal">
                        <span class="mcRmb">¥</span>
                        <span class="mcTotalFee">0.00</span>
                    </span>
                    <span class="mcGo"><a href="${basePath}/myshoppingcart.html">结算</a></span><!-- no-mcGo置灰状态 -->
                </span>
                        <h3><!-- "mc_e1与mcNumTotal与展开时的emCartBox"/"mc_e2与mcNumChecked与mcCashier"同时显示 -->
                            <span class="mc_e1">购物车</span>
                            <span class="mc_e2">共</span>
                            <strong class="mcNumTotal">0</strong>
                            <strong class="mcNumChecked">0</strong>
                            <span class="mc_e2">件</span>
                        </h3>
                    </div>
                </div>
            </div>
            <ul class="side-nav">
            <#if (floor.floorList)?? && (floor.floorList?size > 0)>
            <#list floor.floorList as floors>	
                <li>
                    <b></b>
                    <a href="#lists0${floors_index+1}"><img alt="" src="${floors.storeyRightImg!''}"><strong>${floors.storeyName}</strong></a>
                </li>
            </#list>
            </#if>
                           
            </ul>
            <div id="backtotop"><b></b><a><s></s><strong>返回顶部</strong></a></div>
        </div>

    </div>
<script>
$(function() {
        	//图片延迟加载
			$("img.lazy").lazyload({
				threshold: 200,
				effect: "fadeIn",
				failurelimit : 10,
				placeholder: "${basePath}/index_five/images/lazy_img.png",
				skip_invisible: false
			});
});



</script>










