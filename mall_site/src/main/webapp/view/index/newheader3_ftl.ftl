<#assign basePath=request.contextPath>
<style>
.divhide{
	 display: none;
}
</style>

<div class="container">
    	<div class="header clearfix">
    		<div class="logo fl mt10">
    			<#--<a href="javascript:;"><img src="${basePath}/index_three/images/logo.png" alt="" /></a>-->
    			<a href="${topmap.systembase.bsetAddress}"><img src="${topmap.systembase.bsetLogo}" alt="" /></a>
    		</div><!--/logo-->
			<div class="search_wp fl mt15 pr">
            	<div class="search_box clearfix">
                	<#--<input class="search_text fl" type="text" placeholder="热门搜索" />
                	<input class="search_btn fl" type="button" value="" />-->
                	<form id="topSearchForm" action = "${basePath}/goods/searchproduct2.html" method = "get">
            		<#if (map.searchBean.title)??>
	                	<input class="search_text fl" type="text" name="title" autocomplete="off" value="${(map.searchBean.title)!''}"/>
            		<#else>
            			<#if (topmap.hotsearch)?? && (topmap.hotsearch?size>0)>
	                	<input class="search_text fl" type="text" name="title" autocomplete="off" value="" placeholder="${(topmap.hotsearch[0].keyword)!''}"/>
                        <#else>
                            <input class="search_text fl" type="text" name="title" autocomplete="off" value=""/>
            			</#if>
            		</#if>
                	<input class="search_btn fl" type="button" onclick="checkSearch()" value="" />
            		</form>
                </div><!--/search_box-->
                <div class="hot_word mt10">
                	热门搜索：
                    <#if (topmap.hotsearch)?? && (topmap.hotsearch?size>0)>
                	<input type="hidden" id="firsthotsearch" value="${(topmap.hotsearch[0].keyword)!''}"/>
                	<#list topmap.hotsearch as hots>
                	<#if (hots_index>=0) && (hots_index<6)></#if>
                    <#if (hots.keyword?length>7)>
                    <a href="javascript:;" title="${hots.keyword}" onclick='changeSearchKey(this);'>${hots.keyword?substring(0,7)}</a>
                    <#else>
                    <a href="javascript:;" onclick='changeSearchKey(this);'>${hots.keyword}</a>
                    </#if>
                	</#list>
                	</#if>
                </div><!--/hot_word-->
                <div class="ex_search pa" >
                    <ul id="searchList">
                       <#--<li><a href="javascript:;"><span>搜索历史</span>手机套</a><a class="del_history" href="javascript:;">删除</a></li>
                        <li><a href="javascript:;"><span>搜索历史</span>手机壳</a><a class="del_history" href="javascript:;">删除</a></li>
                        <li><a href="javascript:;"><span>搜索历史</span>手机卡</a><a class="del_history" href="javascript:;">删除</a></li>--> 
                    </ul>
                    <p class="tr"><a class="close_ex" href="javascript:;">关闭</a></p>
                </div><!--/ex_search-->
               <#-- <div class="ex_search pa eq_sera" style="display:none;">
                    <ul>
                        <li><a href="javascript:;">美的</a></li>
                        <li><a href="javascript:;">美的</a></li>
                    </ul>
                    <p class="tr"><a class="close_ex" href="javascript:;">关闭</a></p>
                </div>--><!--/ex_search-->

            </div><!--/search_wp-->
            <div class="shopping_cart_a fr ml20 mt20">
            	<dl class="pr fl mr10 sc-01">
            		<dt class="pr center">
            			<a href="${basePath}/customer/index.html">个人中心</a>
            		</dt>
            		<dd style="width:312px; right:1px;">
            			<div class="center_detail pt10">
            				<p class="title pl10">
            					<#if topmap.cust??>
            						${topmap.cust.customerNickname!''}
            					<#else>
            						<a href="${basePath}/login.html">请登录</a>
            					</#if>
            				</p>                         
                            <div class="order_deal clearfix">
                            	<ul class="fl">
                            		<li>
                            			<a 
                                    	<#if topmap.notice.pendingNum != 0>
                                    		class="active" target="_blank" href="${basePath}/customer/myorder-0-0-1.html"
                                    	<#else>
                                    		href="javascript:void(0)"
                                    	</#if>
                                    	>待处理订单<span>(${topmap.notice.pendingNum})</span></a>
                            		</li>
                            		<li>
                            			<a
                                    	<#if topmap.notice.noReadNum != 0>
                                    		class="active" href="javascript:;"
                                    	<#else>
                                    		href="javascript:void(0)"
                                    	</#if> 
                                    	>咨询回复<span>(${topmap.notice.noReadNum})</span></a>
                            		</li>
                            		<li>
                            			<a 
                                    	<#if topmap.notice.reduceNum != 0>
                                    		class="active" target="_blank" href="${basePath}/customer/myfollw.html"
                                    	<#else>
                                    		href="javascript:void(0)"
                                    	</#if>
                                    	>降价商品<span>(${topmap.notice.reduceNum})</span></a>
                            		</li>
                            		<li>
                            			<a href="javascript:;">返修退换货</a><span>(0)</span>
                            		</li>
                            		<li>
                            			<a 
                            				<#if topmap.couponNum != 0>
	                                    		class="active" target="_blank" href="${basePath}/mycoupon/1.html"
	                                    	<#else>
	                                    		href="javascript:;"
	                                    	</#if>
                            			>优惠券</a><span>(${topmap.couponNum})</span>
                            		</li>
                            	</ul>
                            	<ul class="fl" style="border-right:none;">
                            		<li><a href="${basePath}/customer/browserecord.html">我的关注》</a></li>
                            		<li><a href="${basePath}/customer/myintegral.html">我的积分》</a></li>
                            		<li><a href="javascript:;">我的理财》</a></li>
                            		<li><a href="${basePath}/customer/myfollw.html">我的收藏》</a></li>
                            	</ul>
                            </div><!--order_deal-->
                            
            			</div><!--center_detail-->

            		</dd>
            	</dl>
            	
            	<dl class="pr fl sc-02" id="loadcart">
            		<dt class="pr" id="mminicart">
            			<span class="sc_num"><em></em></span>
                    	<a href="${basePath}/myshoppingcart.html">我的购物车</a>
            		</dt>
            		<dd>
            			<div class="cart_empty none">
            				<p>购物车中还没有商品，赶紧选购吧！</p>
            			</div><!--/cart_empty-->
            			<div class="cart_cont">
            				<div class="cart_tit fb mt5 pl10">
            					最新加入的商品
            				</div><!--/cart_tit-->
            				<ul class="cart_list mt10">
            					<#--<li class="clearfix">
									<a class="ct_img fl ml5" href="javascript:;"><img alt="" src="../images/images_01.jpg" /></a>
									<div class="ct_info fl ml10">
										<p class="ct_name"><a href="javascript:;">咔哟YOYO4.0蓝牙音箱低音</a></p>
									</div>
									<div class="fr"><span class="ct_price"><b>¥</b>2878.00</span>×1</div>
									<div class="cout_text fr"><a href="###">删除</a></div>
            					</li>-->
            				</ul><!--/cart_list-->
            				<div class="p10" style="background:#f5f5f5;">
	            				<div class="settle_accounts tr mt10">
		            					<span class="mr10">共1件商品</span>
	            					共计
	            					<em><b>¥</b>2878.00</em>
	            				</div><!--/settle_accounts-->
	            				<div class="tr mt10 mb10"><a class="settle_btn" href="${basePath}/myshoppingcart.html">去购物车结算</a></div>
            			    </div>
            			</div><!--/cart_cont-->
            		</dd>
            	</dl>
            </div><!--/shopping_cart-->
    	</div><!--/header-->
    </div><!--container-->
    
    
    <div class="headwid"> 
        <div class="container">   
        <div class="nav clearfix">
        	<input type="hidden" id="tempcbshowflag" value="${(topmap.temp.expFleid5)!''}">
    		<#if (topmap.temp.expFleid5)?? && (topmap.temp.expFleid5=='0')>
    		<div class="pro_sort fl pr divhide">
    		<#else>
    		<div class="pro_sort fl pr">
    		</#if>
    		<#--
    		<div class="pro_sort fl pr ">
			-->
    			<h2><a href="javascript:;">全部商品分类</a><b></b></h2>
    			<div class="sort_cont pa none">
    				<#if (topmap.classifyBar.classifyBarList)?? && (topmap.classifyBar.classifyBarList?size>0)>
    				<#list topmap.classifyBar.classifyBarList as cBar>
    				<#if (cBar_index<9)>
    				<dl>
    					<dt>
    						<#--<i class="iconfont">${cBar.imgString!''}</i>-->
                            <img src="${(cBar.imgSrc)!''}" width="35" height="35"/>
    						<a href="javascript:;">${cBar.name}</a>
    					</dt>
    					<dd>
    						<#if (cBar.barQuick)?? && (cBar.barQuick?size>0)>
    						<#list cBar.barQuick as bquick>
    						<#--<a href="${basePath}/list/${bquick.cateId?c}-${bquick.temp1!'0'}.html">${bquick.cateName}</a>-->
    						<!--判断是否自定义分类导航-->
    						<#if bquick.cateId==0>
    							<!--是否有自定义URL-->
    							<#if bquick.temp2=="">
    								<a href="javascript:;">${bquick.cateName}</a>
    							<#else>
    								<a href="${basePath}/${bquick.temp2!'0'}">${bquick.cateName}</a>
    							</#if>
    						<#elseif bquick.cateId??>
    							<a href="${basePath}/list/${bquick.cateId?c}-${bquick.temp1!'0'}.html">${bquick.cateName}</a>
    						</#if>
    						</#list>
    						</#if>
    					</dd>
    				</dl>
    					<!--分类导航不足时，显示图片-->
    					<#if !(cBar_has_next)>
    						<#if (cBar_index)<8>
	    						<#if (topmap.classifyBar.indexAdvertList)?? && (topmap.classifyBar.indexAdvertList?size>0)>
			    					<#list topmap.classifyBar.indexAdvertList as adver>
			    					<#--取第一个广告-->
			    					<#if adver_index==0>
			    						<img alt="${adver.adverName}" src="${adver.adverPath}"  height="50px" width="100%"/>
			    					</#if>
			    					</#list>
			    				</#if>
		    				</#if>
	    				</#if>
    				</#if>
    				</#list>
    				</#if>
    			</div><!--/sort_cont-->
    			
    			<div class="sort_open pa">
                   <#-- <div class="ct_item clearfix">
        				<div class="sub_category clearfix fl">
                            <div class="category_box fl">
                                <dl class="clearfix">
                                    <dt><a href="javascript:;">客厅家具</a></dt>
                                    <dd>
                                        <a href="javascript:;">精品沙发</a>
                                        <a href="javascript:;">休闲沙发</a>
                                    </dd>
                                </dl>
                            </div>--><!--/category_box-->
                            
               		<#if (topmap.classifyBar.classifyBarList)?? && (topmap.classifyBar.classifyBarList?size>0)>
    				<#list topmap.classifyBar.classifyBarList as cBar>
                    <#if (cBar_index<9)>
                    <div class="ct_item clearfix">
        				<div class="sub_category clearfix fl">
                            <#if (cBar.childs)?? && (cBar.childs?size>0)>
                            <#assign cbtwoRows="${((cBar.childs?size)/2)?round}"/>
                            <div class="category_box fl">
                            	<#list cBar.childs as cbtwo>
                            	<#if (cbtwo_index < cbtwoRows?number)>
                            	<dl class="clearfix">
                                    <dt>
                                    <#--<a href="${basePath}/list/${cbtwo.temp4!'0'}-${cbtwo.temp3!'0'}.html">${cbtwo.name}</a></dt>-->
                                    	<!--判断是否自定义分类导航-->
                                    	<#if cbtwo.goodsCatId==-1>
                                    		<!--是否有自定义URL-->
			    							<#if !cbtwo.url??||cbtwo.url=="">
			    								<a href="javascript:;">${cbtwo.name}</a>
			    							<#else>
			    								<a href="${basePath}/${cbtwo.url!'0'}">${cbtwo.name}</a>
			    							</#if>
                                    	<#else>
                                    		<a href="${basePath}/list/${cbtwo.goodsCatId?c}-${cbtwo.temp3!'0'}.html">${cbtwo.name}</a>
                                    	</#if>
                                   	</dt>
                                    <dd>
	                            	<#if (cbtwo.childs)?? && (cbtwo.childs?size>0)>
	                            	<#list cbtwo.childs as cbthird>
                                        <#--<a href="${basePath}/list/${cbthird.goodsCatId?c}-${cbthird.temp3!'0'}.html">${cbthird.name}</a>
                                        <a href="#">${cbthird.name}</a>-->
                                         <!--判断是否自定义分类导航-->
                                        <#if cbthird.goodsCatId==-1>
                                        	<!--是否有自定义URL-->
			    							<#if cbthird.url=="">
			    								<a href="javascript:;">${cbthird.name}</a>
			    							<#else>
			    								<a href="${basePath}/${cbthird.url!'0'}">${cbthird.name}</a>
			    							</#if>
                                    	<#else>
                                    		<a href="${basePath}/list/${cbthird.goodsCatId?c}-${cbthird.temp3!'0'}.html">${cbthird.name}</a>
                                    	</#if>
	                            	</#list>
	                            	</#if>
                                    </dd>
                                </dl>
                            	</#if>
                            	</#list>
                            </div><!--/category_box-->
                            <div class="category_box fl">
                                <#list cBar.childs as cbtwo>
                            	<#if (cbtwo_index >= cbtwoRows?number)>
                            	<dl class="clearfix">
                                    <dt>
                                    	<#--<a href="${basePath}/list/${cbtwo.temp4!'0'}-${cbtwo.temp3!'0'}.html">${cbtwo.name}</a>-->
                                    	<!--判断是否自定义分类导航-->
                                    	<#if cbtwo.goodsCatId==-1>
                                    		<!--是否有自定义URL-->
			    							<#if !cbtwo.url??|| cbtwo.url=="">
			    								<a href="javascript:;">${cbtwo.name}</a>
			    							<#else>
			    								<a href="${basePath}/${cbtwo.url!'0'}">${cbtwo.name}</a>
			    							</#if>
                                    	<#else>
                                    		<a href="${basePath}/list/${cbtwo.goodsCatId?c}-${cbtwo.temp3!'0'}.html">${cbtwo.name}</a>
                                    	</#if>
                                    </dt>
                                    <dd>
	                            	<#if (cbtwo.childs)?? && (cbtwo.childs?size>0)>
	                            	<#list cbtwo.childs as cbthird>
	                            	<#--<a href="${basePath}/list/${cbthird.goodsCatId?c}-${cbthird.temp3!'0'}.html">${cbthird.name}</a>
                                        <a href="#">${cbthird.name}</a>-->
                                        <!--判断是否自定义分类导航-->
                                        <#if cbthird.goodsCatId==-1>
                                    		<!--是否有自定义URL-->
			    							<#if cbthird.url=="">
			    								<a href="javascript:;">${cbthird.name}</a>
			    							<#else>
			    								<a href="${basePath}/${cbthird.url!'0'}">${cbthird.name}</a>
			    							</#if>
                                    	<#else>
                                    		<a href="${basePath}/list/${cbthird.goodsCatId?c}-${cbthird.temp3!'0'}.html">${cbthird.name}</a>
                                    	</#if>
	                            	</#list>
	                            	</#if>
                                    </dd>
                                </dl>
                            	</#if>
                            	</#list>
                            </div><!--/category_box-->
                            </#if>
                        </div><!--/sub_category-->
                        <div class="sub_brands fl">
                          <#-- <div class="brand_img mt10"><a href="javascript:;"><img alt="" src="../images/images_02.jpg" /></a></div>
                            <div class="brand_img mt10"><a href="javascript:;"><img alt="" src="../images/images_03.jpg" /></a></div>
                            --> 
                            <#if (cBar.indexAdvertList)?? && (cBar.indexAdvertList?size>0)>
                            <div class="brand_img mt10">
                            <#list cBar.indexAdvertList as cbAdvert>
                            <#if (cbAdvert.adverSort>0)&&(cbAdvert.adverSort<3)>
                            <a href="${cbAdvert.adverHref}"><img alt="" src="${cbAdvert.adverPath}"/></a>
                            </#if>
                            </#list>
                            </div>
                            </#if>
                            <dl>
                                <dt>推荐品牌</dt>
                                <dd>
                                    <#if (cBar.indexBrandList)?? && (cBar.indexBrandList?size>0)>
                                	<#list cBar.indexBrandList as cbBrand>
	                                    <#if (cbBrand.sort>0) && (cbBrand.sort<11)>
	                                    	<a href="${(cbBrand.url)!''}">${cbBrand.title}</a>
                                		</#if>
                                	</#list>
                                	</#if>
                                </dd>
                            </dl>
                        </div><!--/sub_brands-->
                    </div><!--/ct_item-->
                 	</#if>
                    </#list>
                    </#if> 
                    <a class="close_category pa" href="javascript:;"></a>
    			</div><!--/sort_open-->
    		</div><!--/pro_sort-->
    		
    		
    		<ul class="sort_list clearfix fl">
    			<#if (topmap.navList)?? && (topmap.navList?size>0)>
    			<#list topmap.navList as nav>
                <#if (nav_index < 8)>
    				    <#if (nav.barName=="首页") || (nav.barUrl=="index.html")>
                    <li class="cur"><a   onclick="clickNav('${(nav.barUrl)!''}','${nav_index+1}');" href="javascript:void(0);">${(nav.barName)!''}</a></li>
                    <#else>
                        <#if (nav.openChannel)?? && (nav.openChannel=='0')>
                            <li  class=""><a   onclick="clickNav('${(nav.barUrl)!''}','${nav_index+1}')"  href="javascript:void(0);">${(nav.barName)!''}</a></li>
                        <#else>
                            <li class=""><a onclick="clickNav('barchannelview/${(nav.barId?c)!''}','${nav_index+1}')"  href="javascript:void(0);">${(nav.barName)!''}</a></li>
                        </#if>
    				</#if>
                </#if>
    			</#list>
    			</#if>
    		</ul><!--/sort_list-->
            <div class="hot_line">025-68586888</div>
    	</div><!--/nav-->
    </div><!--container-->
    </div>
    <script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
        <script type="text/javascript">
        <#--$(function(){-->
            <#--$.ajax({ url:"${basePath}/getnavsort.htm", async:false ,success: function(data){-->
                    <#--$(".sort_list").find("li").each(function(){-->
                    <#--$(this).removeClass("cur");-->
                        <#--if($(this).index()+1==data){-->
                            <#--$(this).addClass("cur");-->
                        <#--}-->
                    <#--});-->
            <#--}-->
            <#--});-->
        <#--});-->
       function clickNav(url,sort){ 
            $.ajax({ url:"${basePath}/navsort.htm?navsort="+sort, async:false ,success: function(date){
                }
            }); 
            //window.location.href="${basePath}/"+url;
           if(url.indexOf("ttp://")!=-1){
               window.open(url);
           }else{
               window.open("${basePath}/"+url);
           }
        }
        </script>
