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
                	<form id="topSearchForm" action = "${basePath}/goods/searchproduct2.html" method = "POST">
            		<#if (map.searchBean.title)??>
	                	<input class="search_text fl" type="text" name="title" autocomplete="off" value="${(map.searchBean.title)!''}"/>
            		<#else>
            			<#if (topmap.hotsearch)?? && (topmap.hotsearch?size>0)>
	                	<input class="search_text fl" type="text" name="title" autocomplete="off" value="" placeholder="${(topmap.hotsearch[0].keyword)!''}"/>
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
       </div><!--container-->
    </div>
    <script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
