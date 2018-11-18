<#assign basePath=request.contextPath>
	<input type="hidden" id="isIndex" value="1"/>
    <div class="container">
        <div class="fst_screen clearfix">
            <div class="slides_wp fl">
                <div id="slides" class="slides">
                	<#if avc?? && (avc?size>0)>
                	<#list avc as bigAdvert>
                   		<a href="${bigAdvert.adverHref}"><img alt="" src="${bigAdvert.adverPath}" width="770px" height="330px"/></a>
                	</#list>
                	</#if>
                </div><!--/slides-->
                <div class="jscroll pr">
                    <div class="jscroll_wp">
                        <div class="jscroll_box">
                            <ul class="jscroll_list clearfix">
                            	<#if avs?? && (avs?size>0)>
			                	<#list avs as smallAdvert>
			                   		<li id="${smallAdvert_index}"><a href="${smallAdvert.adverHref}"><img alt="" src="${smallAdvert.adverPath}" width="191px" height="138px"/></a></li>
			                	</#list>
			                	</#if>
                            </ul><!--/jscroll_list-->
                        </div><!--/jscroll_box-->
                    </div><!--/jscroll_wp-->
                    <a class="j_prev" href="javascript:;"></a>
                    <a class="j_next" href="javascript:;"></a>
                </div><!--/jscroll-->
            </div><!--/slides_wp-->
            <div class="fst_rig fl">
                <div class="r_pic">
                	<#if pageAdvs?? && (pageAdvs?size>1)>
                	<a href="${pageAdvs[1].adverHref}"><img alt="" src="${pageAdvs[1].adverPath}" width="230" height="130"/></a>
                	</#if>
                	</div>
                <div class="r_notice">
                    <div class="notice_tit clearfix">
                        <h3 class="fl">${temp.expFleid2!'公告'}</h3>
                        <a class="n_more fr" href="information/list/">更多»</a>
                    </div><!--/notice_tit-->
                    <div class="notice_cont">
                        <ul class="notice_list">
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
                        </ul><!--/notice_list-->
                    </div><!--/notice_cont-->
                </div><!--/r_notice-->
                <div class="r_recharge">
                   <div style="height:178px;border:1px solid #ddd;">

                   <#if pageAdvs?? && (pageAdvs?size>5)>
                	<a href="${pageAdvs[5].adverHref}"><img alt="" src="${pageAdvs[5].adverPath}" width="228" height="177"/></a>
                	</#if>
                   </div>
                   
                </div><!--/r_recharge-->
            </div><!--/fst_rig-->
        </div><!--/fst_screen-->

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
            	<#if pageAdvs?? && (pageAdvs?size>2)>
	                <a href="${pageAdvs[2].adverHref}">
	                    <img class="lazy" alt="" data-original="${pageAdvs[2].adverPath}"  width="394" height="70" />
	                </a>
                </#if>
            	<#if pageAdvs?? && (pageAdvs?size>3)>
	                <a href="${pageAdvs[3].adverHref}">
	                    <img class="lazy" alt="" data-original="${pageAdvs[3].adverPath}"  width="394" height="70" />
	                </a>
                </#if>
            	<#if pageAdvs?? && (pageAdvs?size>4)>
	                <a href="${pageAdvs[4].adverHref}">
	                    <img class="lazy" alt="" data-original="${pageAdvs[4].adverPath}"  width="394" height="70" />
	                </a>
                </#if>
            </div><!--/rec_show-->
        </div><!--/hot_recommend-->
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
                            	<#--
                                <img class="lazy" alt="" data-original="${basePath}/index_two/images/flr_01.png"  width="225" height="260" />
                            	-->
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
	                        		<#if cates_index==9><#break></#if>
	                         	</#list>
                            </ul>
                        </div><!--/flr_menu-->
                        <div class="flr_promotion">
                            	<#--
                                <img class="lazy" alt="" data-original="${basePath}/index_two/images/images_13.jpg"  width="200" height="100" />
                            	-->
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
		                                        <a href="brandProductsList/${brand.brandId}"><img class="lazy" alt="" data-original="${brand.logoSrc}"  width="120" height="40"></a>
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

            <div class="flr_banner"><img class="lazy" alt="" data-original="${floors.storeyImg}"  width="1200" height="100"></div>
</#list>
<!--楼层结束-->
        </div><!--/floors_list-->
</#if>
        

	<div class="side_tools">
        <ul class="clearfix">
        	<#if (floor.floorList)?? && (floor.floorList?size > 0)>
			<#list floor.floorList as floors>
            <li 
            	<#if (floors_index==0)>
            	class="first_tool"
            	</#if>
            	<#if (floors_index==(floor.floorList?size-1))>
            	class="last_tool"
            	</#if>
            	<#--
            	><span><em>${floors.storeyName}</em><img alt="" src="${basePath}/index_two/right_bar_icon/icon_0${floors_index+1}.png" /></span></li>
            	-->
            	><span><em>${floors.storeyName}</em><img alt="" src="${floors.storeyRightImg!''}" width="20px" height="20px"/></span></li>
            </#list>
            </#if>
        </ul>
        <#if customerService.onlineIndex=='Y'>
        <a class="customer_service" href="javascript:;">
        	<em>${customerService.title}</em><b></b>
        </a>
        </#if>
        <a class="feedback" href="${basePath}/customer/feedback.html"><em>我要反馈</em><b></b></a>
        <a class="backtotop" href="javascript:;"><em>返回顶部</em><b></b></a>
        <div class="customer-box">
            <span class="title">${customerService.title}</span>
            <hr>
            <a href="javascript:;" class="close-cs"></a>
            <#list QQs as qq>
            <p><span class="qq_name">${qq.name}</span><span class="qq_img"><a style="float:right;" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${qq.contactNumber}&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:${qq.contactNumber}:51" alt="点击这里给我发消息" title="点击这里给我发消息"/></a></span></p>
            </#list>
        </div>
    </div><!--/side_tools-->

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
