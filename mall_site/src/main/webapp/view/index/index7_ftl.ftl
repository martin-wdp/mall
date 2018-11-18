 <#assign basePath=request.contextPath>
 <input type="hidden" id="isIndex" value="1"/>
 <div id="wrapper">
    <div class="show-box">
        <div id="slides">
        	<#if avc?? && (avc?size>0)>
        		<#list avc as bigAdvert>
            		<a class="slide" href="${bigAdvert.adverHref}" style="background-image:url(${bigAdvert.adverPath});"></a>
            	</#list>
            </#if>
        </div>
        <div class="content" style="width: 1200px;margin: 0 auto;">
            <div class="new-side">
            	<#if pageAdvs?? && (pageAdvs?size>0)>
            		<#list pageAdvs as pageAdv>
            			<#if (pageAdv.adverSort==2) >
                		<a href="${pageAdv.adverHref}"><img class="lazy" alt="" data-original="${pageAdv.adverPath}"/></a>
                		</#if>
                	</#list>
                </#if>
                <div class="news">
                    <div class="tit">
                        <h3>${temp.expFleid2!'公告'}</h3>
                        <a class="more" href="information/list/"><s>&nbsp;</s><s>&nbsp;</s><s>&nbsp;</s></a>
                    </div>
                    <ul class="news-list">
                    <#if infoList?? && (infoList?size>0)>
                       <#list infoList as info>
                       	<#if (info_index >= 0 ) && (info_index <= 5 ) >
                        	<li><a href="information/${info.infoId?c}"><span>【${temp.expFleid2!'公告'}】</span>
                        				<#if info.title?length gt 18>
						                   ${info.title?substring(0,18)}
						                <#else>
						                   ${info.title}
						                </#if>
						         </a>
						     </li>
                        </#if>
                       </#list>
                    <#else>
                        <#list infoList?size..6 as i>
                        <li><a href="javascript:;" target="_self"><span>&nbsp;&nbsp;</span></a></li>
                        </#list>
                    </#if>
                    </ul>
                </div><!--news-->
                <div class="news">
                    <div class="tit">
                        <h3>扫二维码</h3>
                        <a class="more" href="#" target="_self"><s >&nbsp;</s><s >&nbsp;</s><s >&nbsp;</s></a>
                    </div>
                    <ul class="news-list">
                        <li class="app"></li>
                    </ul>
                </div>
            </div><!--new-side-->
        </div><!--content-->
    </div><!--show-box1-->

    <div id="content" >
        <div class="section-01">
            <#-- 2015.10.12 wuyanbo 屏蔽今日推荐功能 -->
            <div class="recommend" style="display:none;">
                <h2 class="title">今日推荐</h2>
                <div class="cont">
                    <div id="proScroll">
                        <ul>
                        	<#if avs?? && (avs?size>0)>
                             	<#list avs as smallAdvert>
                                <li style="width:280px;height:280px;"><a href="${smallAdvert.adverHref!''}"><img  alt="" src="${smallAdvert.adverPath}" width="280" height="280"/></a></li>
                                </#list>
                             </#if>
                        </ul>
                    </div>
                    <a class="j-prev" href="javascript:;" target="_self"></a>
                    <a class="j-next" href="javascript:;" target="_self"></a>
                </div>
            </div>
            <div class="likes">
                <div class="title">
                    <h2>热门推荐<span></span></h2>
                    <a id="replace-btn" href="javascript:;" target="_self">换一批<b></b></a>
                </div>
                <div class="cont"></div>
            </div>
        </div>

<#if (floor.floorList)?? && (floor.floorList?size > 0)>
        <div class="section-02">
     <#list floor.floorList as floors>
       <#if (floors.floorId%3==1)>
            <div class="proLists" id="proLists0${floors.floorId}">
                <div class="title">
                    <h2><span><em>${floors.floorId}F</em></span>${floors.storeyName}</h2>
                    <#if (floors.indexStoreyTagList)?? && (floors.indexStoreyTagList?size > 0)>
                    <ul class="proTabs">
                    <#list floors.indexStoreyTagList as storeyTag>
                    	 <#if (storeyTag.sort==1)>
                        	<li class="cur"><a href="javascript:;" target="_self">${storeyTag.name}</a></li>
                         <#else>
                        	<li><a href="javascript:;" target="_self">${storeyTag.name}</a></li>
                        </#if>
                    </#list>
                    </ul>
                    </#if>
                </div><!--title -->
                <div class="proWp">
                    <div class="proBox">
                     <#if (floors.indexAdvertList)?? && (floors.indexAdvertList?size > 0)>
	                    <#list floors.indexAdvertList as adv>
	                      <#if adv.adverSort==1>
	                        <a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"/></a>
	                      </#if>
	                    </#list>
                    </#if>
                        <div class="pLinks">
                            <ul>
                            	<#list floors.indexCateList as cates>
                                <li><a href="list/${cates.id?c}-${floors.goodsCatId?c}.html">${cates.name}</a></li>
                               	</#list>
                            </ul>
                        </div><!--pLinks -->
                    </div><!--proBox -->
                    <#if (floors.indexStoreyTagList)?? && (floors.indexStoreyTagList?size > 0)>
                    <div class="proCon">
                    <#list floors.indexStoreyTagList as storeyTag>
                        <div class="proItem">
                            <ul class="proList">
                            <#if (storeyTag.indexGoodsList)?? && (storeyTag.indexGoodsList?size>0)>
		                       <#list storeyTag.indexGoodsList as goods>
		                       		<#if (goods_index>=0) && (goods_index<8)>
		                                <li>
		                                    <a class="p-img" href="item/${goods.id}"><img class="lazy" alt="" data-original="${goods.urlpic}" width="150" height="150"/></a>
		                                    <p class="p-name"><a href="item/${goods.id}">
		                                   		 <#if (goods.name?length>30)>
			                                    	${goods.name?substring(0,30)}
				                                 <#else>
					                                 ${goods.name}
				                                 </#if>

		                                    </a></p>
		                                    <p class="p-price">&yen;<strong>${goods.price}</strong></p>
		                                </li>
                                	</#if>
                            	</#list>
                            </#if>
                            </ul><!--proList-->
                        </div><!--proItem-->
                     </#list>
                    </div><!--proCon-->
                </#if>
                </div><!--proWp -->
            </div><!--proLists01-->

            <div class="brands-bar">
                <ul>
                <#if (floors.indexBrandList)?? && (floors.indexBrandList?size>0) >
                <#list floors.indexBrandList as brand>
                <#if (brand.sort>=1) &&(brand.sort<=10) >
                    <li><img class="lazy" data-original="${brand.logoSrc}" alt=""/></li>
                </#if>
                </#list>
                </#if>
                </ul>
            </div>
</#if>

<#if (floors.floorId%3==2)>
<!-- 2015.10.21 wuyanbo 修改所有楼层样式为2楼样式 -->
            <div class="proLists" id="proLists0${floors_index}">
                <div class="title">
                    <h2><span><em>${floors_index+1}F</em></span>${floors.storeyName}</h2>
                    <#if (floors.indexStoreyTagList)?? && (floors.indexStoreyTagList?size > 0)>
                    <ul class="proTabs">
                    <#list floors.indexStoreyTagList as storeyTag>
                    	 <#if (storeyTag.sort==1)>
                        	<li class="cur"><a href="javascript:;" target="_self">${storeyTag.name}</a></li>
                         <#else>
                        	<li><a href="javascript:;" target="_self">${storeyTag.name}</a></li>
                        </#if>
                    </#list>
                    </ul>
                    </#if>
                </div><!--title -->

                <div class="proWp">
                    <div class="proBox">
                    <#list floors.indexAdvertList as adv>
                      <#if adv.adverSort==1>
                        <a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"/></a>
                      </#if>
                    </#list>
                        <div class="pLinks">
                            <ul>
                            	<#list floors.indexCateList as cates>
                                <li><a href="list/${cates.id?c}-${floors.goodsCatId?c}.html">${cates.name}</a></li>
                               	</#list>
                            </ul>
                        </div><!--pLinks -->
                    </div><!--proBox -->
                    <div class="proCon">
                    <#list floors.indexStoreyTagList as storeyTag>
                        <#if (storeyTag.sort==1)>
                        <div class="proItem">
                            <ul class="proList">
	                                <li class="p-slides">
	                                <#if (storeyTag.indexAdvertList)?? && (storeyTag.indexAdvertList?size>0)>
	                                <#list storeyTag.indexAdvertList as adv>
	                                	<#if adv.adverSort<=6>
	                                    <a class="slide" href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}" /></a>
	                                    </#if>
	                                </#list>
	                               	</#if>
	                                </li>

	                                <#if (storeyTag.indexGoodsList)?? && (storeyTag.indexGoodsList?size>0)>
			                       <#list storeyTag.indexGoodsList as goods>
			                       		<#if (goods_index>=0) && (goods_index<6)>
			                                <li>
			                                    <a class="p-img" href="item/${goods.id}"><img class="lazy" alt="" data-original="${goods.urlpic}" width="150" height="150"/></a>
			                                    <p class="p-name"><a href="item/${goods.id}">
			                                   		 <#if (goods.name?length>30)>
				                                    	${goods.name?substring(0,30)}
					                                 <#else>
						                                 ${goods.name}
					                                 </#if>
			                                    </a></p>
			                                    <p class="p-price">&yen;<strong>${goods.price}</strong></p>
			                                </li>
	                                	</#if>
	                            	</#list>
	                            </#if>
                            </ul>
                        </div><!--proItem-->
                        </#if>
                        </#list>

                        <#list floors.indexStoreyTagList as storeyTag>
                        <#if (storeyTag.sort>=2)>
                        <div class="proItem">
                            <ul class="proList">
                            <#if (storeyTag.indexGoodsList)?? && (storeyTag.indexGoodsList?size>0)>
		                       <#list storeyTag.indexGoodsList as goods>
		                       		<#if (goods_index>=0) && (goods_index<8)>
		                                <li>
		                                    <a class="p-img" href="item/${goods.id}"><img class="lazy" alt="" data-original="${goods.urlpic}" width="150" height="150"/></a>
		                                    <p class="p-name"><a href="item/${goods.id}">
		                                   		 <#if (goods.name?length>30)>
			                                    	${goods.name?substring(0,30)}
				                                 <#else>
					                                 ${goods.name}
				                                 </#if>
		                                    </a></p>
		                                    <p class="p-price">&yen;<strong>${goods.price}</strong></p>
		                                </li>
                                	</#if>
                            	</#list>
                            </#if>
                            </ul><!--proList-->
                        </div><!--proItem-->
                         </#if>
                     </#list>
                    </div><!--proCon-->
                </div><!--proWp-->
            </div><!--proLists02-->

            <div class="brands-bar">
                <ul>
	                <#if (floors.indexBrandList)?? && (floors.indexBrandList?size>0) >
	                <#list floors.indexBrandList as brand>
	                <#if (brand.sort>=1) &&(brand.sort<=10) >
	                    <li><img class="lazy" data-original="${brand.logoSrc}" alt=""/></a></li>
	                </#if>
	                </#list>
	                </#if>
                </ul>
            </div>
</#if>

<#if (floors.floorId%3==0)>
            <div class="proLists" id="proLists0${floors.floorId}">
                <div class="title">
                    <h2><span><em>${floors.floorId}F</em></span>${floors.storeyName}</h2>
                    <#if (floors.indexStoreyTagList)?? && (floors.indexStoreyTagList?size > 0)>
                    <ul class="proTabs">
                    <#list floors.indexStoreyTagList as storeyTag>
                    	 <#if (storeyTag.sort==1)>
                        	<li class="cur"><a href="javascript:;" target="_self">${storeyTag.name}</a></li>
                         <#else>
                        	<li><a href="javascript:;" target="_self">${storeyTag.name}</a></li>
                        </#if>
                    </#list>
                    </ul>
                    </#if>
                </div><!--title -->

                <div class="proWp">
                    <div class="proBox spBox">
                    <#list floors.indexAdvertList as adv>
                      <#if adv.adverSort==1>
                        <a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}" /></a>
                      </#if>
                    </#list>
                        <div class="pLinks">
                            <ul>
                            	<#list floors.indexCateList as cates>
                                <li><a href="list/${cates.id?c}-${floors.goodsCatId?c}.html">${cates.name}</a></li>
                               	</#list>
                            </ul>
                        </div><!--pLinks -->
                    </div><!--proBox -->
                    <div class="proCon bpCon">
                    <#list floors.indexStoreyTagList as storeyTag>
                        <#if (storeyTag.sort==1)>
                        <div class="proItem">
                            <ul class="proList spcList">
                                <li class="v-slides">
                                <#if (storeyTag.indexAdvertList)?? && (storeyTag.indexAdvertList?size>0)>
	                                <#list storeyTag.indexAdvertList as adv>
	                                	<#if adv.adverSort<=6>
                                    		<a class="slide" href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}" /></a>
                                    	</#if>
                                    </#list>
                                </#if>
                                </li>
                                <#if (storeyTag.indexGoodsList)?? && (storeyTag.indexGoodsList?size>0)>
		                       <#list storeyTag.indexGoodsList as goods>
		                       		<#if (goods_index>=0) && (goods_index<8)>
	                                <li class="spc-0${goods_index+1}">
		                                    <a class="p-img" href="item/${goods.id}"><img class="lazy" alt="" data-original="${goods.urlpic}" width="150" height="150"/></a>
		                                    <p class="p-name"><a href="item/${goods.id}">
		                                   		 <#if (goods.name?length>30)>
			                                    	${goods.name?substring(0,30)}
				                                 <#else>
					                                 ${goods.name}
				                                 </#if>
		                                    </a></p>
		                                    <p class="p-price">&yen;<strong>${goods.price}</strong></p>
		                                </li>
                                	</#if>
                                </#list>
                                </#if>
                            </ul><!--proList-->
                        </div><!--proItem-->
                      </#if>
                      </#list>

                       <#list floors.indexStoreyTagList as storeyTag>
                        <#if (storeyTag.sort>=2)>
                        	<div class="proItem">
                            <ul class="proList">
                            <#if (storeyTag.indexGoodsList)?? && (storeyTag.indexGoodsList?size>0)>
		                       <#list storeyTag.indexGoodsList as goods>
		                       		<#if (goods_index>=0) && (goods_index<10)>
		                                <li>
		                                    <a class="p-img" href="item/${goods.id}"><img class="lazy" alt="" data-original="${goods.urlpic}" width="150" height="150"/></a>
		                                    <p class="p-name"><a href="item/${goods.id}">
		                                   		 <#if (goods.name?length>30)>
			                                    	${goods.name?substring(0,30)}
				                                 <#else>
					                                 ${goods.name}
				                                 </#if>
		                                    </a></p>
		                                    <p class="p-price">&yen;<strong>${goods.price}</strong></p>
		                                </li>
                                	</#if>
                            	</#list>
                            </#if>
                            </ul><!--proList-->
                        </div><!--proItem-->
                         </#if>
                     </#list>
                    </div><!--proCon-->
                </div><!--proWp-->
            </div><!--proLists03-->

            <div class="brands-bar">
                <ul>
                	<#if (floors.indexBrandList)?? && (floors.indexBrandList?size>0) >
	                <#list floors.indexBrandList as brand>
	                <#if (brand.sort>=1) &&(brand.sort<=10) >
	                    <li><img class="lazy" data-original="${brand.logoSrc}" alt=""/></li>
	                </#if>
	                </#list>
	                </#if>
                </ul>
            </div>
</#if>
 </#list>
        </div><!--section-02-->

</#if>

<div class="sideBar">
	<#if customerService??>
	<span class="customer_service" onclick="openCustServiceChat();" ><!--<em>${customerService.title}</em>--><b title="在线客服"></b></span>
	</#if>
    <a class="feedback" href="${basePath}/customer/feedback.html" style="width:70px;height:50px;"><!--<em>我要反馈</em>--><b title="我要反馈"></b></a>
    <a id="backtoTop" href="javascript:;" target="_self" style="width:70px;height:30px;background:#fff url(${basePath}/index_seven/css/icons/icon_up.png) no-repeat center center;" title="回到顶部"></a>
</div>


<script id="likes" type="text/html">
    <ul class="like-pros">
        {{each likes as value i}}
        <li>
            <a class="lk-img" href="{{value.proHref}}"><img class="lazy" alt="" data-original="{{value.proImg}}"/></a>
            <p class="lk-name"><a href="{{value.proHref}}">{{value.proName}}</a></p>
            <p class="lk-price">&yen;<strong>{{value.proPrice}}</strong></p>
        </li>
        {{/each}}
    </ul>
</script>
<script>
    var data = {
        likes: [
        <#if hotSale?? && (hotSale?size>0)>
          <#list hotSale as hotgoods>
            {"proHref":"item/${(hotgoods.goodsproductId?c)!''}",
            "proImg":"${(hotgoods.goodsproductImgsrc)!''}",
	             "proName": "${(hotgoods.goodsproductName)!''}",
             "proPrice":"${(hotgoods.goodsproductPrice)!''}"}
             <#if hotgoods_has_next>,</#if>
          </#list>
        </#if>
        ]
    };
    var html = template('likes', data);
    $(".likes .cont").append(html);
</script>

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
    });
</script>
</div>
 <div id="btnFloor" class="sideBar sideBar2">
 <#if (floor.floorList)?? && (floor.floorList?size > 0)>
     <#list floor.floorList as floors>
         <!-- 2015.10.21 wuyanbo 修改所有楼层样式为2楼样式 -->
         <a class="sideItem" href="#proLists0${floors_index}" >${floors_index+1}F<span>${floors.storeyName}</span></a>
     </#list>
 </#if>
 </div>
      
