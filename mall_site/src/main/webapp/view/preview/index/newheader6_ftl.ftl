<#assign basePath=request.contextPath>
<input type="hidden" id="basePath" value="${basePath}"/>
<div class="section_headerTop">
    <div class="slot slot_headerTop01">
        <ul class="left">
            <li><a class="favorite" href="javascript:;" onclick="addToFavorite('${topmap.systembase.bsetName}');"><s></s>加入收藏</a></li>
            <li><span class="welcome">您好<#if cust??>${cust.customerNickname!''}</#if>，欢迎来到${topmap.systembase.bsetName}!</span></li>
            <li class="login_info">
                <a class="site_login" href="${basePath}/login.html">请登录</a>
                <a class="site_register" href="${basePath}/register.html">免费注册</a>
            </li>
            <li class="loginout_info hide">
                <a class="site_loginout" href="${basePath}/logout.html">退出</a>
            </li>
        </ul>
        <ul class="siteNav">
            <li class="nav_item"><a href="${basePath}/customer/myorder.html">我的订单</a></li>
            <li class="nav_item"><a href="javascript:;">会员俱乐部</a></li>
            <li class="nav_item navCenter">
                <a href="javascript:;">收藏夹<s></s></a>
                <div class="navInfo">
                    <div class="navPanel">
                        <a href="javascript:;">收藏的店铺</a>
                        <a href="javascript:;">收藏的商品</a>
                    </div>
                </div>
            </li>
            <li class="nav_item navCenter">
                <a href="javascript:;">客户服务<s></s></a>
                <div class="navInfo">
                    <div class="navPanel">
                        <a href="${basePath}/help/2">帮助中心</a>
                        <a href="javascript:;">售后服务</a>
                        <a href="javascript:;">在线客服</a>
                        <a href="javascript:;">投诉中心</a>
                        <a href="javascript:;">客服邮箱</a>
                    </div>
                </div>
            </li>
        </ul>
    </div> 
    <div class="slot slot_headerTop02"></div>
</div>

<div class="section-header">
    <div class="content">
        <h1 id="logo">
            <a href="${topmap.systembase.bsetAddress}"><img alt="" src="${topmap.systembase.bsetLogo}"></a>

            <div class="logoBanner"><a href="${basePath}/${(topmap.logoAdv.adverHref)!'#'}"><img alt="" src="${(topmap.logoAdv.adverPath)!'${basePath}/index_six/images/logoImg.jpg'}"></a></div>
        </h1>

        <div class="cartfd">
            <s class="cartBanner"></s>
            <div class="cartit">
                <span><s></s>我的购物车</span>
                <strong class="cartNum">0</strong>
            </div>
            <div class="miniCart hide">
                <div class="mCartBox">
                    <!-- mcBoxTop 居上，滚动时依然居上mcFloat展示 -->
                    <div class="mcBoxTop clearfix">                        
                    </div>
                    <div class="mcBoxList">
                                               
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

        <div class="search searchdiv">
            <form class="mallSearch-form" action="${basePath}/goods/searchproduct2.html">
                <div class="mallSearch-input">
                    <label for=""></label>
                    <#if (map.searchBean.title)??>
                    <input class="inputSearch" type="text" value="${(map.searchBean.title)!''}" />
                    <#else>
                    <input class="inputSearch" type="text"/>
                    </#if>
                    <button class="btnSearch" type="submit"></button>
                </div>
            </form>
            <div class="search_link">
                热门搜索：
                <#if (topmap.hotsearch)?? && (topmap.hotsearch?size>0)>
	               <#list topmap.hotsearch as hots>
		                <#if (hots.sort==1)>
			                <a class="hot" href="javascript:;" onclick="changeSearchKey(this);">${(hots.keyword)!''}</a>	
			            </#if>                
	                </#list>
		            <#list topmap.hotsearch as hots>
		                <#if (hots.sort>=2) && (hots.sort<=6)>
			                <#if (hots.keyword?length>7)>
			                	<a href="javascript:;" onclick="changeSearchKey(this);">${(hots.keyword)?substring(0,7)}</a>
			                <#else>
			                	<a href="javascript:;" onclick="changeSearchKey(this);">${(hots.keyword)!''}</a>
			                </#if>	
		                </#if>
		            </#list>
	            </#if>
            </div>
        </div>

        <div class="mainnav">
            <div class="navLinks">
                <ul>
                <#if (topmap.navList)?? && (topmap.navList?size>0)>
	                <#list topmap.navList as nav>
	                 	<#if (nav.barName=="首页") || (nav.barUrl=="index.html")>
	                    	<li><a class="navLink on" onclick="clickNav('${(nav.barUrl)!''}','${nav_index+1}');"  href="javascript:;">${(nav.barName)!''}</a></li>	                  
	                    <#else>
	                    	<#if (nav.openChannel)?? && (nav.openChannel=='0')>
	                    	<li><a class="navLink" onclick="clickNav('${(nav.barUrl)!''}','${nav_index+1}')" href="javascript:;">${(nav.barName)!''}</a></li>
	                    	<#else>
	                    	<li><a class="navLink" onclick="clickNav('barchannelview/${(nav.barId?c)!''}','${nav_index+1}')" href="javascript:;">${(nav.barName)!''}</a></li>
	                    	</#if>
	                    </#if>
	                </#list>
                </#if>                   
                </ul>
            </div>

            <div class="showlist">
                <strong class="btnnav menucat">
                    <a class="hover" href="javascript:;">
                        <em class="all"></em>
                        全部商品分类
                    </a>
                </strong>
                <ul class="list dropdown-menu">
                <#if (topmap.classifyBar.classifyBarList)?? && (topmap.classifyBar.classifyBarList?size>0)>
                <#list topmap.classifyBar.classifyBarList as cBar>
                    <li>
                    	<#if (cBar.barCate)?? && (cBar.barCate?size>0)>
                    	<#list cBar.barCate as bcate>
                            <#if bcate.cateId==-1>
                                <#if bcate.temp2=="">
                                    <a href="javascript:;">${bcate.cateName}</a>
                                <#else>
                                    <a href="${basePath}/${bcate.temp2!'0'}">${bcate.cateName}</a>
                                </#if>
                            <#elseif bcate.cateId??>
                                <a href="${basePath}/list/${bcate.temp1!'0'}-${bcate.cateId?c}.html">${bcate.cateName}</a>
                            </#if>
                        </#list>
                        </#if>
                        <div class="links">
                        	<#if (cBar.barQuick)?? && (cBar.barQuick?size>0)>
                        	<#list cBar.barQuick as bquick>
                        		<#if bquick.cateId==0>
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
                        </div>
                    </li>
                 </#list>
                 </#if>
                </ul> 
<#if (topmap.classifyBar.classifyBarList)?? && (topmap.classifyBar.classifyBarList?size>0)>
<#list topmap.classifyBar.classifyBarList as cBar>
                <div class="menuView hide" id="submenu-${cBar_index+1}">
                    <ul>
                    <#list cBar.childs as cbtwo>
                        <li>
                        <#if cbtwo.goodsCatId==-1>
                        	<#if cbtwo.url=="">
	                           	<h3><a href="">${cbtwo.name}</a></h3>
	                    	<#else>
	                             <h3><a href="${basePath}/${cbtwo.url!'0'}">${cbtwo.name}</a></h3>
	                     	</#if>
	                     <#else>
                            <h3><a href="${basePath}/list/${cbtwo.temp4!'0'}-${cbtwo.temp3!'0'}.html">${cbtwo.name}</a></h3>
                        </#if>
                            <p>
	                           	<#if (cbtwo.childs)?? && (cbtwo.childs?size>0)>
			                   	<#list cbtwo.childs as cbthird>   
	                                <#if cbthird.goodsCatId==-1>
		                             	<#if cbthird.url=="">
		                             		<a href="">${cbthird.name}</a>
		                             	<#else>
		                             		<a href="${basePath}/${cbthird.url!'0'}">${cbthird.name}</a>                             	
		                             	</#if>
		                             <#else>
		                             	<a href="${basePath}/list/${cbthird.goodsCatId?c}-${cbthird.temp3!'0'}.html">${cbthird.name}</a>
		                             </#if> 
	                             </#list>
			                     </#if> 
                            </p>
                        </li>
                    </#list> <!--cbtwo list--> 
                    </ul>
                    <div class="menuImg"><img src="${basePath}/images/menuImg.png" alt="" /></div>
                </div><!--menuView-->
</#list>
 </#if> 
                   
            </div><!--showlist -->
            
        </div><!--mainnav-->
    </div><!--content-->
</div><!--section-header-->
<div class="bh-mask"></div>
    <div id="ctDia" class="bh-dialog">
        <div class="dia-tit">
            <h4>加入收藏</h4>
            <a class="dia-close" href="javascript:;" onclick="cls(this)"></a>
        </div>
        <div class="dia-cont">
            <p>加入收藏失败，请使用菜单栏或Ctrl+D进行添加！</p>
        </div>
        <div class="dia-btn"><a href="javascript:;" onclick="cls(this)">确定</a></div>
    </div>
<script> 
//加入收藏
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
	            dia('ctDia');
	            
	        }   
	    }   
	}	
</script>
 <script type="text/javascript">
        $(function(){
           $.ajax({ url:"${basePath}/getnavsort.htm", async:false ,success: function(data){
                    $(".sort_list").find("li").each(function(){
                    $(this).find("a").removeClass("on");
                        if($(this).index()+1==data){
                            $(this).find("a").addClass("on");
                        }
                    });
            }
            });
        });
       function clickNav(url,sort){ 
            $.ajax({ url:"${basePath}/navsort.htm?navsort="+sort, async:false ,success: function(date){
                }
            }); 
            window.location.href="${basePath}/"+url;
        }
</script>


