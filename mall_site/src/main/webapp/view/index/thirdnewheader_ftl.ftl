<#assign basePath=request.contextPath>
<style>
.divhide{
	 display: none;
}
</style>
	<div class="container">
    	<div class="header clearfix">
    		<div class="logo fl">
    			<a href="${topmap.systembase.bsetAddress}"><img src="${topmap.systembase.bsetLogo}" alt="" /></a>
    			<a class="ml30" href="${basePath}/${(topmap.logoAdv.adverHref)!'#'}">
    				<img src="${(topmap.logoAdv.adverPath)!'${basePath}/index_two/images/hd_img.gif'}" alt="" 
    				width="150px" height="80px"/></a>
    		</div><!--/logo-->
			<div class="search_wp fl mt15 pr">
            	<div class="search_box clearfix">
            		<form id="topSearchForm" action = "${basePath}/goods/searchproduct2.html" method = "POST">
	                	<input class="search_text fl c999" type="text" name="title" autocomplete="off" />
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
            				<!--<#if cust?? >
            				<p>您的购物车是空的<br /></p>
            				<#else>
            				<p>您的购物车是空的<br />如您已添加过商品，可<a href="${basePath}/login.html">登录</a>后查看</p>
            				</#if>-->
            				<p>您的购物车是空的<br />如您已添加过商品，可<a href="${basePath}/login.html">登录</a>后查看</p>
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
