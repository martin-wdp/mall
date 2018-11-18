<#assign basePath=request.contextPath>
	<input type="hidden" id="isIndex" value="1"/>
<div class="banner pr">
    <div id="slides" class="slides">
                	<#if avc?? && (avc?size>0)>
                	<#list avc as bigAdvert>
                   		<a href="${bigAdvert.adverHref}" style="background:url(${bigAdvert.adverPath}) no-repeat center center; height:340px; display:block;"></a>
                	</#list>
                	</#if>
    </div><!--/slides-->
    <div class="cs_notice">
        <div class="cs_notice_tp">
            <p>${temp.expFleid2!'公告'}</p>
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
	                        			</a>
	                        		</li>
		                    	</#if>
		             </#list>
               </#if>
            </ul>
        </div>
        <div class="cs_notice_tp">
            <p>微信订阅</p>
            <div class="clearfix mb20">
                <img src="${basePath}/index_three/images/weixin.gif" class="fl ml20 mr20 mt10"/>
                <span class="guanzhu">关注我们微信扫一扫更多优惠</span>                
            </div>
        </div>
        <div class="cs_notice_tp">
            <p>掌上商城</p>
            <div class="anzhuo mb10">
                <img src="${basePath}/index_three/images/adro_btn.gif" class="fl ml10 mr10"/>安卓手机
            </div>
            <div class="anzhuo mb10">
                <img src="${basePath}/index_three/images/app_btn.gif" class="fl ml10 mr10"/>苹果手机
            </div> 
        </div>
        <div class="cs_notice_tp" style="border-bottom:none;">
            <p>用购物卡更优惠</p>
            <div class="clearfix mb10">
                <img src="${basePath}/index_three/images/erbai.jpg" class="fl ml10 mr10"/>
                <div class="song fl">送6%购物礼金价值212 <a href="javascript:;">[购买]</a></div>
            </div>
            <div class="clearfix mb10">
                <img src="${basePath}/index_three/images/wubai.jpg" class="fl ml10 mr10"/>
                <div class="song fl">送6%购物礼金价值212 <a href="javascript:;">[购买]</a></div>
            </div>
            
        </div>
    </div><!--cs_notice-->  
</div><!--banner-->


<div class="container">
    <div class="jscroll pr">
        <p class="title">天天特价</p>
        <div class="jscroll_wp">
            <div class="jscroll_box">
                <ul class="jscroll_list clearfix">
                    <#if avs?? && (avs?size>0)>
			              	<#list avs as smallAdvert>
			               		<li id="${smallAdvert_index}">
			               			<a href="${smallAdvert.adverHref}" class="fl m10"><img alt="" src="${smallAdvert.adverPath}" /></a>
			               			<div class="fl jinlong">
			                            <a href="${smallAdvert.adverHref}" class="p-name">${smallAdvert.adverName}</a>
			                            <p class="p-price">￥${smallAdvert.des}</p>
			                            <a href="${smallAdvert.adverHref}" class="p-buy">抢购</a>
			                        </div>
			               		</li>
			               	</#list>
			        </#if>
                </ul><!--/jscroll_list-->
            </div><!--/jscroll_box-->
        </div><!--/jscroll_wp-->
        <div class="l_bg"><a class="j_prev" href="javascript:;"></a></div>
        <div class="r_bg"><a class="j_next" href="javascript:;"></a></div>
    </div><!--/jscroll-->


    <div class="hot_recommend mt15">
            <h2>热门推荐</h2>
            <ul class="recommend_list clearfix mt10">
                <#if hotSale?? && (hotSale?size>0)>
            	<#list hotSale as hotgoods>
                <li>
                    <div class="rec_wp">
                        <a href="item/${(hotgoods.goodsproductId?c)!''}">
                            <img class="lazy" alt="" data-original="${(hotgoods.goodsproductImgsrc)!''}"  width="160" height="160" />
                        </a>
                        <p class="rec_name mt10"><a href="item/${(hotgoods.goodsproductId?c)!''}" title="${(hotgoods.goodsproductName)!''}">
                        	<#if (hotgoods.goodsproductName?length>20)>
	                        	${((hotgoods.goodsproductName)!'')?substring(0,20)}
                        	<#else>
	                        	${(hotgoods.goodsproductName)!''}
                        	</#if>
                        	</a></p>
                        <p class="rec_info mt10" title="${(hotgoods.temp3)!''}">
                        	<#if (((hotgoods.temp3)!'')?length>20)>
	                        	${((hotgoods.temp3)!'')?substring(0,20)}
                        	<#else>
	                        	${(hotgoods.temp3)!''}
                        	</#if>
                        </p>
                    </div><!--/rec_wp-->
                </li>
            	</#list>
            	</#if>
            </ul><!--/recommend_list-->
            <div class="rec_show mt10 clearfix">
                <#if pageAdvs?? && (pageAdvs?size>0)>
	                <a href="${pageAdvs[0].adverHref}">
	                    <img class="lazy" alt="" data-original="${pageAdvs[0].adverPath}"  width="394" height="70" />
	                </a>
                </#if>
            	<#if pageAdvs?? && (pageAdvs?size>1)>
	                <a href="${pageAdvs[1].adverHref}">
	                    <img class="lazy" alt="" data-original="${pageAdvs[1].adverPath}"  width="394" height="70" />
	                </a>
                </#if>
            	<#if pageAdvs?? && (pageAdvs?size>2)>
	                <a href="${pageAdvs[2].adverHref}">
	                    <img class="lazy" alt="" data-original="${pageAdvs[2].adverPath}"  width="394" height="70" />
	                </a>
                </#if>
            </div><!--/rec_show-->
        </div><!--/hot_recommend-->
<div><!--/container-->

	<#if (floor.floorList)?? && (floor.floorList?size > 0)>
        <div class="floors_list mt10">
	<!--楼层开始-->
	<#list floor.floorList as floors>
	            <div class="floor_wp">
                <div class="flr_title clearfix">
                     <h2 class="fl"><span>${floors.floorId}F</span>${floors.storeyName}</h2>
                    <div class="tabs_box pr fl">
                        	<#if (floors.indexStoreyTagList)?? && (floors.indexStoreyTagList?size > 0)>
                        <ul class="flr_tabs clearfix">
                        	<#list floors.indexStoreyTagList as storeyTag>
				        		<li><a href="javascript:;">${storeyTag.name}</a></li>
					        </#list>
                        </ul><!--/flr_tabs-->
                        <span class="tabs_arrow pa"></span>
                        	</#if>   
                    </div><!--/tabs_box-->
                </div><!--/flr_title-->
                <div class="flr_wp clearfix">
                    <div class="flr_left fl">
                        <div class="flr_img pr">
                            <#list floors.indexAdvertList as adv>
                            	<#if adv.adverSort==1>
                            	<a href="${adv.adverHref}">
                                <img class="lazy" alt="" data-original="${adv.adverPath}"  width="200" height="260" />
                            	</a>
                            	</#if>
                            	</#list>
                        </div><!--/flr_img-->
                        <div class="flr_menu">
                            <ul class="clearfix">
                                <#list floors.indexCateList as cates>  
	                        		<li><a href="list/${cates.id?c}-${floors.goodsCatId?c}.html">${cates.name}</a></li>
	                         	</#list>
                            </ul>
                        </div><!--/flr_menu-->
                        <div class="flr_promotion">
                          <#--  <a href="javascript:;">
                                <img class="lazy" alt="" data-original="../images/images_13.jpg"  width="200" height="100" />
                            </a>-->
                            <#list floors.indexAdvertList as adv>
                            	<#if adv.adverSort==2>
                           		<a href="${adv.adverHref}">
                                <img class="lazy" alt="" data-original="${adv.adverPath}"  width="200" height="100" />
                            	</a>
                            	</#if>
                            	</#list>
                        </div><!--/flr_promotion-->
                    </div><!--/flr_left-->
                    <div class="flr_cont fl">
<!--标签1开始-->                    
                    <#if (floors.indexStoreyTagList)?? && (floors.indexStoreyTagList?size > 0)>
                       	<#list floors.indexStoreyTagList as storeyTag>
                       		<#if (storeyTag.sort==1)>
                        <div class="flr_box">
                            <div class="flr_line_01 clearfix">
                                <div class="f_slides fl">
                                    <#if (storeyTag.indexAdvertList)?? && (storeyTag.indexAdvertList?size>0)>
	                                	<#list storeyTag.indexAdvertList as adv>
	                                		<#if (adv.adverSort>=1) && (adv.adverSort<5)>
		                                    <a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"   width="600" height="300"></a>
	                                		</#if>
	                                	</#list>
                                	</#if>
                                </div><!--/f_slides-->
                                <ul class="f_item clearfix fl">
                                    <#--<li>
                                        <a href="javascript:;"><img class="lazy" alt="" data-original="../images/images_15.jpg"  width="200" height="300" /></a>
                                    </li>
                                    <li>
                                        <a href="javascript:;"><img class="lazy" alt="" data-original="../images/images_16.jpg"  width="200" height="300" /></a>
                                    </li>-->
                                    <li>
                                    	<#if (storeyTag.indexAdvertList)?? && (storeyTag.indexAdvertList?size>0)>
	                                	<#list storeyTag.indexAdvertList as adv>
	                                		<#if (adv.adverSort>=5) && (adv.adverSort<6)>
	                                        <a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"  width="200" height="300" /></a>
	                                		</#if>
	                                	</#list>
	                                	</#if>
                                    </li>
                                    <li>
                                    	<#if (storeyTag.indexAdvertList)?? && (storeyTag.indexAdvertList?size>0)>
	                                	<#list storeyTag.indexAdvertList as adv>
	                                		<#if (adv.adverSort>=6) && (adv.adverSort<7)>
	                                        <a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"  width="200" height="300" /></a>
	                                		</#if>
	                                	</#list>
	                                	</#if>
                                    </li>
                                </ul><!--/f_item-->
                            </div><!--/flr_line_01-->
                            <div class="flr_line_02">
                                <ul class="f_item clearfix">
                                    <#if (storeyTag.indexAdvertList)?? && (storeyTag.indexAdvertList?size>0)>
	                                <#list storeyTag.indexAdvertList as adv>
	                                	<#if (adv.adverSort>=7) && (adv.adverSort<12)>
	                                	<li>
	                                        <a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"  width="200" height="200" /></a>
	                                    </li>
	                                	</#if>
	                                </#list>
	                                </#if>
                                </ul><!--/f_item-->
                            </div><!--/flr_line_02-->
                        </div><!--/flr_box-->
                        </#if>
					    </#list>
                        </#if>
	<!--标签1结束-->
	<!--标签2、3、4开始-->
                        <#if (floors.indexStoreyTagList)?? && (floors.indexStoreyTagList?size > 1)>
                       	<#list floors.indexStoreyTagList as storeyTag>
                       		<#if (storeyTag.sort>1) && (storeyTag.sort<5)>
                        <div class="flr_box">
                            <ul class="goods_list clearfix">
                                <#if (storeyTag.indexGoodsList)?? && (storeyTag.indexGoodsList?size>0)>
                            	<#list storeyTag.indexGoodsList as goods>
                            		<#if (goods_index>=0) && (goods_index<10)>
	                                <li>
	                                    <div class="good_img tc">
	                                        <a href="item/${goods.id}"><img class="lazy" alt="" data-original="${goods.urlpic}"  width="160" height="160"></a>
	                                    </div><!--/good_img-->
	                                    <p class="good_name mt5"><a href="item/${goods.id}" title="${goods.name}">
	                                    	<#if (goods.name?length>30)>
		                                    	${goods.name?substring(0,30)}
	                                    	<#else>
		                                    	${goods.name}
	                                    	</#if>
	                                    </a></p>
	                                    <p class="good_price mt5">¥ ${goods.price}</p>
	                                </li>
                            		</#if>
                            	</#list>
                            	</#if>
                            </ul><!--/goods_list-->
                        </div><!--/flr_box-->
                        </#if>
					    </#list>
                        </#if>
	<!--标签2、3、4结束-->
	<!--标签5开始-->
						<#if (floors.indexStoreyTagList)?? && (floors.indexStoreyTagList?size > 4)>
                       	<#list floors.indexStoreyTagList as storeyTag>
                       		<#if storeyTag.sort==5>
                        <div class="flr_box">
                            <div class="brands_wp clearfix">
                                <div class="act_left fl">
                                    <div class="up_slides s_slides">
                                   		<#if (storeyTag.indexAdvertList)?? && (storeyTag.indexAdvertList?size>0)>
                                    	<#list storeyTag.indexAdvertList as adv>
                                    		<#if (adv.adverSort>=1) && (adv.adverSort<5)>
		                                        <a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"  width="400" height="250"></a>
                                    		</#if>
                                    	</#list>
                                    	</#if>
                                    </div><!--/up_slides-->
                                    <div class="below_slides s_slides">
                                  		<#if (storeyTag.indexAdvertList)?? && (storeyTag.indexAdvertList?size>0)>
                                    	<#list storeyTag.indexAdvertList as adv>
                                    		<#if (adv.adverSort>=5) && (adv.adverSort<9)>
		                                        <a href="${adv.adverHref}"><img class="lazy" alt="" data-original="${adv.adverPath}"  width="400" height="250"></a>
                                    		</#if>
                                    	</#list>
                                    	</#if>
                                    </div><!--/below_slides-->
                                </div><!--/act_left-->
                                <ul class="brands_list clearfix fl">
                                    <#if (storeyTag.indexBrandList)?? && (storeyTag.indexBrandList?size>0)>
                                    	<#list storeyTag.indexBrandList as brand>
                                    		<#if (brand_index>=0) && (brand_index<23)>
		                                    <li>
		                                        <a href="#"><img class="lazy" alt="" data-original="${brand.logoSrc}"  width="120" height="40"></a>
		                                    </li>
                                    		</#if>
                                    	</#list>
                                    <li>
                                        <a href="javascript:;"><img class="lazy" alt="" data-original="${basePath}/index_two/images/bd_more.gif"  width="120" height="40"></a>
                                    </li>
                                    </#if>
                                </ul><!--/brands_list-->
                            </div><!--/brands_wp-->
                        </div><!--/flr_box-->
                        </#if>
					    </#list>
                        </#if>
<!--标签5结束-->
                    </div><!--/flr_cont-->
                </div><!--/flr_wp-->
            </div><!--/floor_wp-->

            <div class="flr_banner"><a href="${floors.storeyImgHref}"><img class="lazy" alt="" data-original="${floors.storeyImg}"  width="1200" height="100"></a></div>
</#list>
<!--楼层结束-->
        </div><!--/floors_list-->
</#if>



<script>
        $(function() {
        	//图片延迟加载
			$("img.lazy").lazyload({
				threshold: 200,
				effect: "fadeIn",
				failurelimit : 10,
				placeholder: "${basePath}/index_two/images/lazy_img.png",
				skip_invisible: false
			});
			<#if (floor.floorList)?? && (floor.floorList?size > 0)>
        	//右侧导航
			$(".side_tools li").each(function(n){
				var cur = $(this);
				var t_top = $(".floor_wp:eq("+n+")").offset().top;
				cur.mouseover(function(){
					cur.addClass("hover");
				}).mouseout(function(){
					cur.removeClass("hover");
				});
				cur.click(function(){
					$("body,html").animate({
		                    scrollTop: t_top
		                },500);
				});
			});
			$(".side_tools").css("top",($(window).height() - $(".side_tools").height()) / 2);
			var dis = $(".floor_wp:first").offset().top / 2;
			$(window).scroll(function() {
				if ($(document.documentElement).scrollTop() > dis || $(document.body).scrollTop() > dis) {
		            $(".side_tools").css('display','block');
		            $(".floor_wp").each(function(n){
		            	var _this = $(this);
		            	var to_top = $(".side_tools").offset().top;
		            	if(to_top > _this.offset().top && to_top < _this.next().offset().top) {
		            		$(".side_tools").find(".cur").removeClass("cur");
		            		$(".side_tools li:eq("+n+")").addClass("cur");
		            	};
		            });
		            $(".backtotop").die().live("click",
		            function() {
		                $("body,html").animate({
		                    scrollTop: 0
		                },0);
		             	$(".side_tools").find(".cur").removeClass("cur");
		            });
		        } else {
		            $(".side_tools").css('display','none');
		        }
		    });
		   	</#if>
        	//轮播大广告
        	if($("#slides").find("a").length > 1) {
	            $('#slides').slidesjs({
	                width: 770,
	                height: 330,
	                play: {
	                    active: false,
	                    effect: "fade",
	                    auto: true,
	                    interval: 4000,
	                    pauseOnHover: true,
	                    restartDelay: 2500
	                },
	                navigation: {
	                    active: false
	                },
	                pagination: {
	                    active: true,
	                    effect: "fade"
	                }
	            });
        	}
            //轮播小广告的数量
            var jscroll_list_size = $(".jscroll_list").find("li").length;
            if(jscroll_list_size>4){
            	jscroll_list_size=4;	
            }
            if($(".jscroll_list").find("li").length>1){
	            $(".jscroll_box").jCarouselLite({
	            	
	                btnNext: ".j_next",
	                btnPrev: ".j_prev",
	                auto: 3600,
	                speed: 500,
	                visible: jscroll_list_size,
	                onMouse: true,
	                scroll: jscroll_list_size
	            });
            }

            $(".floor_wp").each(function(){
                var cur = $(this);
                if(cur.find(".f_slides a").length > 1) {
                	cur.find(".f_slides").slidesjs({
	                    width: 600,
	                    height: 300,
	                    navigation: {
	                        active: false
	                    },
	                    pagination: {
	                        active: true,
	                        effect: "slide"
	                    }
	                });
	                
                }; 
                if(cur.find(".up_slides a").length > 1) {
                	cur.find(".up_slides").slidesjs({
	                    width: 400,
	                    height: 250,
	                    navigation: {
	                        active: false
	                    },
	                    pagination: {
	                        active: true,
	                        effect: "slide"
	                    }
	                });
                
                }        
                if(cur.find(".below_slides a").length > 1) {
                	cur.find(".below_slides").slidesjs({
	                    width: 400,
	                    height: 250,
	                    navigation: {
	                        active: false
	                    },
	                    pagination: {
	                        active: true,
	                        effect: "slide"
	                    }
	                });
                
                }        
            });
        });
        
    </script>