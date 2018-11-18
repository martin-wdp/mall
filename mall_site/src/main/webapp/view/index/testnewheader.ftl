	<#assign basePath=request.contextPath>
	<link rel="stylesheet" type="text/css" href="${basePath}/index_two/css/base.min.css" />
	<link rel="stylesheet" type="text/css" href="${basePath}/index_two/css/header.css" />
	<style>
	.c999{
		color: #999;
	}
	</style>
	<script>
	/*加入收藏
	function addToFavorite(siteName){
		try {   
			window.external.AddFavorite($("#basePath").val(),siteName);
	    } catch (e) {   
	        try {   
	            window.sidebar.addPanel($("#basePath").val(), siteName, "");   
	        } catch (e) {   
	            $(".collect_tip_cancel").click(function(){
	            	cls();
	            });
	            dia(9);
	            
	        }   
	    }   
	}
	*/
	// 全选
	function selectAll(objName) {
		var checkboxs = document.getElementsByName(objName);
		var allcheck = document.getElementById("allcheck");
		for (var i = 0; i < checkboxs.length; i++) {
			var e = checkboxs[i];
			e.checked = allcheck.checked;
		}
	}
		
	</script>
	<input type="hidden" id="basePath" value="${basePath}"/>
	<#include "newtop.ftl"/>
	<#--
	<div class="top">
    	<div class="top_wp clearfix">
        	<div class="top_left fl">
            	<a class="collect_me mr10" href="javascript:;" onclick="addToFavorite('${topmap.systembase.bsetName}');">收藏${topmap.systembase.bsetName}</a>
                <span class="t_line">|</span>
                <span class="ml10">您好<#if cust??>${cust.customerNickname!''}</#if>，欢迎来到${topmap.systembase.bsetName}！</span>
                <#if cust?? >
					<a class="t_login ml10" href="${basePath}/logout.html">退出</a>
				<#else>
					<a class="t_login ml10" href="${basePath}/login.html">请登录</a>
					<a class="t_reg ml10" href="${basePath}/register.html">免费注册</a>
				</#if>
            </div>
            
            <ul class="top_nav fr clearfix">
            	<li><a href="${basePath}/customer/myorder.html">我的订单</a></li>
                <li class="my_store pr">
                	<span class="t_line">|</span>
                    <span class="outline"></span>
                    <a class="m_store" href="${basePath}/customer/index.html">我的${topmap.systembase.bsetName}<b></b></a>
                    <div class="ms_cont pa">
                    	<a href="${basePath}/myshoppingcart.html">我的购物车</a>
                    	
                    </div>
                </li>
                <li><span class="t_line">|</span><a href="javascript:;" style="text-decoration:line-through;">服务中心</a></li>
                <li><span class="t_line">|</span><a href="javascript:;" style="text-decoration:line-through;">网站导航</a></li>
            </ul>
        </div>
    </div>
	-->

    <div class="container">
    	<div class="header clearfix">
    		<div class="logo fl">
    			<a href="${basePath}/index.html"><img src="${topmap.systembase.bsetLogo}" alt="" /></a>
    			<a class="ml30" href="${basePath}/${(topmap.logoAdv.adverHref)!'#'}">
    				<img src="${(topmap.logoAdv.adverPath)!'${basePath}/index_two/images/hd_img.gif'}" alt="" 
    				width="150px" height="80px"/></a>
    		</div><!--/logo-->
			<div class="search_wp fl mt15 pr">
            	<div class="search_box clearfix">
            		<form id="topSearchForm" action = "${basePath}/goods/searchproduct2.html" method = "POST">
            		<#if (map.searchBean.title)??>
	                	<input class="search_text fl c999" type="text" name="title" autocomplete="off" value="${(map.searchBean.title)!''}"/>
            		<#else>
            			<#if (topmap.hotsearch)?? && (topmap.hotsearch?size>0)>
	                	<input class="search_text fl c999" type="text" name="title" autocomplete="off" value="" placeholder="${(topmap.hotsearch[0].keyword)!''}"/>
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
                <div class="ex_search pa">
                    <ul id="searchList">
                    <#--
                        <li><a href="javascript:;"><span>搜索历史</span>手机套</a><a class="del_history" href="javascript:;">删除</a></li>
                        <li><a href="javascript:;"><span>搜索历史</span>手机壳</a><a class="del_history" href="javascript:;">删除</a></li>
                        <li><a href="javascript:;"><span>搜索历史</span>手机卡</a><a class="del_history" href="javascript:;">删除</a></li>
                    -->
                    </ul>
                    <p class="tr"><a class="close_ex" href="javascript:;">关闭</a></p>
                </div><!--/ex_search-->
            </div><!--/search_wp-->
            <div class="shopping_cart fr mt20">
            	<dl class="pr" id="loadcart">
            		<dt class="pr" id="mminicart">
            			<span class="sc_num"><em>0</em></span>
                    	<a href="${basePath}/myshoppingcart.html">我的购物车</a>
            		</dt>
            		<dd>
            			<div class="cart_empty">
            				<#if cust?? >
            				<p>您的购物车是空的<br /></p>
            				<#else>
            				<p>您的购物车是空的<br />如您已添加过商品，可<a href="${basePath}/login.html">登录</a>后查看</p>
            				</#if>
            			</div><!--/cart_empty-->
            			<div class="cart_cont none">
            				<div class="cart_tit clearfix mt5">
            					<#--
            					<label class="select_all fl ml10"><input class="vm mr5" type="checkbox" />全选，共1件商品</label>
            					<a class="cart_delete fr mr10" href="javascript:;">删除</a>
            					-->
            				</div><!--/cart_tit-->
            				<ul class="cart_list mt10">
            					<#--
            					<li class="clearfix">
									<input class="ct_check fl" type="checkbox" />
									<a class="ct_img fl ml5" href="javascript:;"><img alt="" src="${basePath}/index_two/images/images_01.jpg" /></a>
									<div class="ct_info fl ml10 mt15">
										<p class="ct_name"><a href="javascript:;">三星手机 I9500（皓月白）</a></p>
										<span class="ct_price"><b>¥</b>2878.00</span>
									</div>
									<div class="cart_cout clearfix pa">
										<a class="minus fl" href="javascript:;"></a>
										<input class="min_text fl" type="text" value="1" />
										<a class="plus fl" href="javascript:;"></a>
									</div>
									<div class="cout_text pa"></div>
									<a class="cart_del pa" href="javascript:;"></a>
            					</li>
            					-->
            				</ul><!--/cart_list-->
            				<div class="settle_accounts tr mt10">
            					<span class="mr10">共1件商品</span>
            					共计
            					<em><b>¥</b>2878.00</em>
            				</div><!--/settle_accounts-->
            				<div class="tr mt10 mb10"><a class="settle_btn" href="${basePath}/myshoppingcart.html">去购物车结算</a></div>
            			</div><!--/cart_cont-->
            		</dd>
            	</dl>
            </div><!--/shopping_cart-->
    	</div><!--/header-->

    	<div class="nav clearfix">
    		<div class="pro_sort fl pr">
    			<h2><a href="javascript:;">全部商品分类</a><b></b></h2>
    			<div class="sort_cont pa none">
    				<#if (topmap.classifyBar.classifyBarList)?? && (topmap.classifyBar.classifyBarList?size>0)>
    				<#list topmap.classifyBar.classifyBarList as cBar>
    				<#if (cBar_index<12)>
    				<dl>
    					<dt>
    						<img class="vm" alt="" src="${cBar.imgSrc!''}" width="20px" height="20px"/>
    						<#if (cBar.barCate)?? && (cBar.barCate?size>0)>
    						<#list cBar.barCate as bcate>
    						<a href="${basePath}/list/${bcate.temp1!'0'}-${bcate.cateId}.html">${bcate.cateName}</a>
    						</#list>
    						</#if>
    					</dt>
    					<dd>
    						<#if (cBar.barQuick)?? && (cBar.barQuick?size>0)>
    						<#list cBar.barQuick as bquick>
    						<a href="${basePath}/list/${bquick.cateId}-${bquick.temp1!'0'}.html">${bquick.cateName}</a>
    						</#list>
    						</#if>
    					</dd>
    					<dd class="sort_line"></dd>
    				</dl>
    				</#if>
    				</#list>
    				</#if>
    			</div><!--/sort_cont-->
    			<div class="sort_open pa">
    				<#if (topmap.classifyBar.classifyBarList)?? && (topmap.classifyBar.classifyBarList?size>0)>
    				<#list topmap.classifyBar.classifyBarList as cBar>
                    <#if (cBar_index<12)>
                    <div class="ct_item clearfix">
        				<div class="sub_category clearfix fl">
                            <#if (cBar.childs)?? && (cBar.childs?size>0)>
                            <div class="category_box fl">
                            	<#list cBar.childs as cbtwo>
                            	<#if (cbtwo_index < 6)>
                            	<dl>
                                    <dt><a href="${basePath}/list/${cbtwo.temp4!'0'}-${cbtwo.temp3!'0'}.html">${cbtwo.name}</a></dt>
                                    
                                    <dd>
	                            	<#if (cbtwo.childs)?? && (cbtwo.childs?size>0)>
	                            	<#list cbtwo.childs as cbthird>
                                        <a href="${basePath}/list/${cbthird.goodsCatId}-${cbthird.temp3!'0'}.html">${cbthird.name}</a>
	                            	</#list>
	                            	</#if>
                                    </dd>
                                </dl>
                            	</#if>
                            	</#list>
                            </div><!--/category_box-->
                            </#if>
                            <#if (cBar.childs)?? && (cBar.childs?size>6)>
                            <div class="category_box fl">
                                <#list cBar.childs as cbtwo>
                            	<#if (cbtwo_index >= 6)>
                            	<dl>
                                    <dt><a href="${basePath}/list/${cbtwo.temp4!'0'}-${cbtwo.temp3!'0'}.html">${cbtwo.name}</a></dt>
                                    
                                    <dd>
	                            	<#if (cbtwo.childs)?? && (cbtwo.childs?size>0)>
	                            	<#list cbtwo.childs as cbthird>
                                        <a href="${basePath}/list/${cbthird.goodsCatId}-${cbthird.temp3!'0'}.html">${cbthird.name}</a>
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
                            <dl>
                                <dt>推荐品牌</dt>
                                <dd>
                                	<#if (cBar.indexBrandList)?? && (cBar.indexBrandList?size>0)>
                                	<#list cBar.indexBrandList as cbBrand>
                                	<#if (cbBrand.sort>0) && (cbBrand.sort<7)>
	                                    <a href="#"><img alt="" src="${cbBrand.logoSrc}" width="90px" height="30px"/></a>
                                	</#if>
                                	</#list>
                                	</#if>
                                </dd>
                            </dl>
                            <#if (cBar.indexAdvertList)?? && (cBar.indexAdvertList?size>0)>
                            <div class="brand_img mt10">
                            <#list cBar.indexAdvertList as cbAdvert>
                            <#if (cbAdvert.adverSort==1)>
                            <a href="${cbAdvert.adverHref}"><img alt="" src="${cbAdvert.adverPath}" width="210px" height="330px"/></a>
                            </#if>
                            </#list>
                            </div>
                            </#if>
                        </div><!--/sub_brands-->
                    </div><!--/ct_item-->
                    </#if>
                    </#list>
                    </#if>
                    <a class="close_category pa" href="javascript:;"></a>
    			</div><!--/sort_open-->
    		</div><!--/pro_sort-->
    		<ul class="sort_list clearfix">
    			<#if (topmap.navList)?? && (topmap.navList?size>0)>
    			<#list topmap.navList as nav>
    				<#if (nav.barName=="首页") || (nav.barUrl=="index.html")>
	    			<li class="cur"><a href="${basePath}/${nav.barUrl}">${nav.barName}</a></li>
    				<#else>
    					<#if (nav.openChannel)?? && (nav.openChannel=='0')>
			    			<li><a href="${nav.barUrl}">${nav.barName}</a></li>
    					<#else>
			    			<li><a href="${basePath}/barchannelview/${nav.barId}">${nav.barName}</a></li>
    					</#if>
    				</#if>
    			</#list>
    			</#if>
    		</ul><!--/sort_list-->
    	</div><!--/nav-->
    </div><!--/container-->
    <#--
	<div class="mask"></div>
    <div class="dialog dia9">
        <div class="dia_tit clearfix">
            <h4 class="fl collect_title">加入收藏</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div>
        <div class="dia_cont">
            <div class="dia_collect_intro tc pt30"><img class="vm collect_img" alt="" src="" />
            <em class="collect_content">加入收藏失败，请使用菜单栏或Ctrl+D进行添加!</em></div>
            <div class="dia_ops mt20 tc">
                <a class="collect_ok collect_tip_cancel" href="javascript:;">确定</a>
            </div>
        </div>
    </div>
    -->
	<script type="text/javascript" src="${basePath}/index_two/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${basePath}/index_two/js/jquery.slides.min.js"></script>
    <script type="text/javascript" src="${basePath}/index_two/js/jcarousellite_1.0.1.js"></script>
    <script type="text/javascript" src="${basePath}/index_two/js/jquery.lazyload.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
	<script type="text/javascript" src="${basePath}/index_two/js/index.js"></script>
