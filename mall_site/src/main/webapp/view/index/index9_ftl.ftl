<#assign basePath=request.contextPath>
<input type="hidden" id="isIndex" value="1"/>
<div id="content">
            <div class="section-01">
                <div id="slides">
                <#if avc?? && (avc?size>0)>
	        		<#list avc as bigAdvert>
		        		<#if (bigAdvert.adverSort>=1 && bigAdvert.adverSort<=5)>
		                    <a href="${bigAdvert.adverHref}" class="slide"><img src="${bigAdvert.adverPath}" alt=""/></a>
		                </#if>
	                 </#list>
            	</#if>                      
                </div>
                <div class="hd-show">
                    <ul>
                    <#if avs?? && (avs?size>0)>
                      <#list avs as smallAdvert>
                        <li><a href="${smallAdvert.adverHref}"><img alt="" src="${smallAdvert.adverPath}"/></a></li>
                      </#list>
                    </#if>    
                    </ul>
                    <a href="javascript:;" class="s-prev"></a>
                    <a href="javascript:;" class="s-next"></a>
                </div>
            </div><!--section-01-->

            <div class="section-02">
                <h3>热销商品<span>Best selling</span></h3>
                <div class="switch-box">
                    <a href="javascript:;" class="t-prev"><i class="iconfont">&#xe609;</i></a>
                    <a href="javascript:;" class="t-next"><i class="iconfont">&#xe608;</i></a>
                </div>
                <div class="best-selling">
                    <div class="selling-wp">
                        <ul>
                        <#if hotSale?? && (hotSale?size>0)>
                        	<#list hotSale as hotgoods>
                            <li>
                                <div class="sell-box">
                                    <a href="item/${(hotgoods.goodsproductId?c)!''}" class="pro-img"><img alt="" src="${(hotgoods.goodsproductImgsrc)!''}" width="185" height="185"/></a>
                                    
                                    <p class="pro-name">
                                        <a href="item/${(hotgoods.goodsproductId?c)!''}" title="${(hotgoods.goodsproductName)!''}">
                                                ${(hotgoods.goodsproductName)!''}
                                    	</a>
                                    </p>
                                    <p class="pro-des">${(hotgoods.temp3)!''}</p>
                                </div>
                            </li>
                            </#list>
                         </#if> 
                        </ul>
                    </div><!--selling-wp-->
                </div><!--best-selling-->
            </div><!--section-02-->
            
<#if (floor.floorList)?? && (floor.floorList?size > 0)>
            <div class="section-03">
  <#list floor.floorList as floors> 
  <#if (floors.floorId%2==1) >        
                <div class="proLists" id="proLists0${floors.floorId}">
                    <div class="title">
                        <h3>${floors.storeyName}</h3>
                        <a href="${floors.storeyImgHref!''}" class="more">更多<i class="iconfont">&#xe608;</i></a>
                    </div>
                    <div class="proWp">
                        <div class="proCont">
                            <div class="proItem proItem-l">
                                <div class="item">
                                    <div class="proBox">
                                    <#list floors.indexAdvertList as adv>
	                                    <#if adv.adverSort==1>
	                                        <a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"/></a>
	                                    </#if>
                                    </#list>
                                    </div>
                                </div>
                            </div><!--proItem-l-->
                            <#if (floors.indexGoodsList)??  && (floors.indexGoodsList?size > 0) >
                            <div class="proItem proItem-m">
                            	<#list floors.indexGoodsList as goods>
                            	<#if goods_index==0>
                                <div class="item">
                                    <div class="proBox">
                                        <p class="proName"><a href="item/${goods.id}">${goods.name}</a></p>
                                        <p class="proPrice">${goods.price}元</p>
                                        <a href="item/${goods.id}" class="proImg"><img class="lazy" alt="" data-original="${goods.urlpic}"/></a>
                                    </div>
                                </div>
                                </#if>
                                </#list>
                            </div><!--proItem-m-->
                            
                           
                            <div class="proItem proItem-s">
                            <#list floors.indexGoodsList as goods>
                            	<#if goods_index==1>
                                <div class="item">
                                    <div class="proBox">
                                        <p class="proName"><a href="item/${goods.id}">${goods.name}</a></p>
                                        <p class="proPrice">${goods.price}元</p>
                                        <a href="item/${goods.id}" class="proImg"><img class="lazy" alt="" data-original="${goods.urlpic}"/></a>
                                    </div>
                                </div>
                                </#if>
                                <#if goods_index==2>
                                <div class="item">
                                    <div class="proBox">
                                        <p class="proName"><a href="item/${goods.id}">${goods.name}</a></p>
                                        <p class="proPrice">${goods.price}元</p>
                                        <a href="item/${goods.id}" class="proImg"><img class="lazy" alt="" data-original="${goods.urlpic}"/></a>
                                    </div>
                                </div>
                                </#if>
                            </#list>
                            </div><!--proItem-s-->
                            
                            <div class="proItem proItem-s">
                                <div class="item item-sort">
                                    <ul class="proSort">
                                    <#list floors.indexCateList as cates>
                                        <li><a href="list/${cates.id?c}-${floors.goodsCatId?c}.html">${cates.name}</a></li>
                                        <#if cates_index==8><#break></#if>
                                    </#list>
                                    </ul>
                                </div>
                            </div><!--proItem-s-->
                           
                            <#list floors.indexGoodsList as goods>
                            <#if (goods_index>=3) && (goods_index<=5) >
                            <div class="proItem proItem-m">
                                <div class="item">
                                    <div class="proBox">
                                        <p class="proName"><a href="item/${goods.id}">${goods.name}</a></p>
                                        <p class="proPrice">${goods.price}元</p>
                                        <a href="item/${goods.id}" class="proImg"><img class="lazy" alt="" data-original="${goods.urlpic}"/></a>
                                    </div>
                                </div>
                            </div><!--proItem-m-->
                             </#if>
                            </#list>
                            
                            </#if> 
                        </div><!--proCont-->
                    </div><!--proWp-->
                </div><!--proLists-->
             </#if>
 <#if (floors.floorId%2==0) >  
                <div class="proLists" id="proLists0${floors.floorId}">
                    <div class="title">
                        <h3>${floors.storeyName}</h3>
                        <a href="${floors.storeyImgHref!''}" class="more">更多<i class="iconfont">&#xe608;</i></a>
                    </div>
                    <div class="proWp">
                        <div class="proCont">
                            <div class="proItem proItem-l">
                                <div class="item">
                                    <div class="proBox">
                                    <#list floors.indexAdvertList as adv>
                                    	<#if adv.adverSort==1>
                                        <a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"/></a>
                                        </#if>
                                    </#list>
                                    </div>
                                </div>
                            </div>
                            <#if (floors.indexGoodsList)??  && (floors.indexGoodsList?size > 0) >
                            <div class="proItem proItem-m">
                            <#list floors.indexGoodsList as goods>
                            	<#if goods_index==0>
                                <div class="item">
                                    <div class="proBox">
                                        <p class="proName"><a href="item/${goods.id}">${goods.name}</a></p>
                                        <p class="proPrice">${goods.price}元</p>
                                        <a href="item/${goods.id}" class="proImg"><img class="lazy" alt="" data-original="${goods.urlpic}"/></a>
                                    </div>
                                </div>
                                </#if>
                             </#list>   
                            </div>
                            <div class="proItem proItem-s">
                            <#list floors.indexGoodsList as goods>
	                            <#if (goods_index>=1) && (goods_index<=2)>
	                                <div class="item">
	                                    <div class="proBox">
	                                        <p class="proName"><a href="item/${goods.id}">${goods.name}</a></p>
	                                        <p class="proPrice">${goods.price}元</p>
	                                        <a href="item/${goods.id}" class="proImg"><img class="lazy" alt="" data-original="${goods.urlpic}"/></a>
	                                    </div>
	                                </div>
	                           </#if>
                           </#list>
                               <#-- <div class="item">
                                    <div class="proBox">
                                        <p class="proName"><a href="javascript:;">荣耀 畅玩4X全网通版</a></p>
                                        <p class="proPrice">1599元</p>
                                        <a href="javascript:;" class="proImg"><img alt="" src="../images/images_22.jpg"/></a>
                                    </div>
                                </div>-->
                            </div>
                            <div class="proItem proItem-s">
                            	<#list floors.indexGoodsList as goods>
		                            <#if (goods_index>=3) && (goods_index<=4)>
		                                <div class="item">
		                                    <div class="proBox">
		                                        <p class="proName"><a href="item/${goods.id}">${goods.name}</a></p>
		                                        <p class="proPrice">${goods.price}元</p>
		                                        <a href="item/${goods.id}" class="proImg"><img class="lazy" alt="" data-original="${goods.urlpic}"/></a>
		                                    </div>
		                                </div>
		                           </#if>
                                 </#list>   
                            </div>
	                            <#list floors.indexGoodsList as goods>
	                            <#if (goods_index>=5) && (goods_index<=7)>
	                            <div class="proItem proItem-m">
	                                <div class="item">
	                                    <div class="proBox">
	                                        <p class="proName"><a href="item/${goods.id}">${goods.name}</a></p>
	                                        <p class="proPrice">${goods.price}元</p>
	                                        <a href="item/${goods.id}" class="proImg"><img class="lazy" alt="" data-original="${goods.urlpic}"/></a>
	                                    </div>
	                                </div>
	                            </div>
	                            </#if>
	                            </#list>
                            </#if>
                        </div><!--proCont-->
                    </div><!--proWp-->
                </div><!--proLists02-->
          </#if>
 </#list>               
            </div><!--section-03-->
</#if>
            <div class="brands-bar">
                <div class="brands-box">
                    <ul>
                    <#if trademarkList?? &&(trademarkList?size>0) >
	                <#list trademarkList as trademark >	    
	                  <li><a href="${trademark.url!''}"><img class="lazy" alt="" data-original="${trademark.logoSrc}"/></a></li>
                    </#list>
                    </#if>                        
                    </ul>
                </div>
                <a href="javascript:;" class="bd-prev"></a>
                <a href="javascript:;" class="bd-next"></a>
            </div><!--brands-bar-->
        </div><!--content-->
        
        
        <div class="sideBar">
        <#if (floor.floorList)?? && (floor.floorList?size > 0)>
        	<#list floor.floorList as floors> 
	        <a href="#proLists0${floors.floorId}" class="sideItem"><img alt="" data-original="${floors.storeyRightImg}"/>
            <#if floors.storeyName?length &gt; 4> ${floors.storeyName?substring(0,4)}<#else>${floors.storeyName}</#if>
           </a>
	        </#list>
	    </#if>
	    <#if customerService??&&customerService.onlineIndex=='Y'>
	        <a href="javascript:;" class="customer_service"><i class="iconfont">&#xe603;</i>${customerService.title}</a>
	    </#if>
	        <a href="${basePath}/customer/feedback.html" id="complaint"><i class="iconfont">&#xe604;</i>投诉建议</a>
	        <a href="javascript:;" id="backtoTop"><i class="iconfont">&#xe605;</i>回到顶部</a>
	        
	        <div class="customer-box">
            <span class="title">${customerService.title}</span>
            <hr>
            <a href="javascript:;" class="close-cs"></a>
            <#if QQs??>
            <#list QQs as qq>
            <p><span class="qq_name">${qq.name}</span><span class="qq_img"><a style="float:right;" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${qq.contactNumber}&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:${qq.contactNumber}:51" alt="点击这里给我发消息" title="点击这里给我发消息"/></a></span></p>
            </#list>
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
				placeholder: "${basePath}/index_nine/images/lazy_img.png",
				skip_invisible: false
			});
});
</script>
        
        
        