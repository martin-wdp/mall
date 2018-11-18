<#assign basePath=request.contextPath>
<input type="hidden" id="basePath" value="${basePath}"/>
<div id="sectionHeader" class="section-header">
            <div class="content">
                <h1 id="logo">
                    <a href="${topmap.systembase.bsetAddress}"><img alt="" src="${topmap.systembase.bsetLogo}" /></a>
                </h1>
                <div class="cartfd">
                    <s class="cartBanner"></s>
                    <div class="cartit">
                        <span><i class="iconfont">&#xe601;</i>购物车</span>
                        <strong class="cartNum">(0)</strong>
                    </div>
                    <div class="miniCart hide">
                        <div class="mCartBox">
                            <div class="mcBoxTop clearfix">
                                <#--<div class="mcChk"><input type="checkbox"/></div>
                                <label for="" class="mcElect">全选</label>-->
                            </div>
                            <div class="mcBoxList">                              
                            </div><!--mcBoxList-->
                            <div class="emCartBox hide">
                                <span>购物车中还没有商品，再去逛逛吧！</span>
                            </div>
                        </div><!--mCartBox-->
                        <div class="mcGenius bmcGenius"></div>
                        <div class="mCartError hide">
                            <p>正在为您加载数量！</p>
                        </div>
                        <div class="mCartHandler clearfix">
                            <span class="mcCashier">
                                <span class="mcTotal">
                                    <span class="mcRmb">¥</span>
                                    <span class="mcTotalFee">0.00</span>
                                </span>
                                <span class="${basePath}/myshoppingcart.html">结算</span>
                            </span>
                            <h3>
                                <span class="mc_e1">购物车</span>
                                <span class="mc_e2">共</span>
                                <strong class="mcNumTotal">0</strong>
                                <strong class="mcNumChecked">0</strong>
                                <span class="mc_e2">件</span>
                            </h3>
                        </div><!--clearfix -->
                    </div><!--miniCart-->
                </div><!--cartfd-->
                <div class="search searchdiv">
                    <form action="${basePath}/goods/searchproduct2.html" class="mallSearch-form" method="post">
                        <label for=""></label>
                        <input type="text" class="inputSearch" name="title" placeholder="搜索您需要的商品" value="${(map.searchBean.title)!''}" />
                        <button class="btnSearch" type="button" onclick="checkSearch()"><i class="iconfont">&#xe600;</i></button>
                    </form>
                    <div class="search_link">
                        <#if (topmap.hotsearch)?? && (topmap.hotsearch?size>0)>
                    	<#list topmap.hotsearch as hots>
	                    	<#if (hots.sort<=3)>
		                    	<#if (hots.keyword?length>4)>
		                        <a href="javascript:;" onclick="changeSearchKey(this);">${(hots.keyword)?substring(0,4)}</a>
		                        <#else>
		                        <a href="javascript:;" onclick="changeSearchKey(this);">${(hots.keyword)!''}</a>
		                        </#if>
	                        </#if>
                        </#list>
                        </#if>         
                    </div>
                </div><!--searchdiv-->
                <div class="header-news">
                <#if (topmap.infoList)?? && (topmap.infoList?size>0)>
                <#list topmap.infoList as info>
                <#if info_index==0>
	                <a href="${basePath}/information/${info.infoId?c}"><i class="iconfont">&#xe606;</i>
	                <#if info.title?length gt 18>
	               	   ${info.title?substring(0,18)}
					<#else>
					   ${info.title}
					</#if>                
	                </a>
                </#if>
                </#list>
                </#if>
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
                    </div><!--navLinks-->
                    <div class="showlist">
                        <strong class="btnnav menucat">
                            <a href="javascript:;" class="hover">全部商品分类<em class="all"></em></a>
                        </strong>
                    </div><!--showlist -->
                </div><!--mainnav -->
            </div><!--content-->
        </div><!--sectionHeader-->
        
        <div class="bh-mask"></div>
	    <div id="ctDia" class="bh-dialog">
	        <div class="dia-tit">
	            <h4>加入收藏</h4>
	            <a class="dia-close" href="javascript:;" onclick="scls(this)"></a>
	        </div>
	        <div class="dia-cont">
	            <p>加入收藏失败，请使用菜单栏或Ctrl+D进行添加！</p>
	        </div>
	        <div class="dia-btn"><a href="javascript:;" onclick="scls(this)">确定</a></div>
	    </div>
	    
  <script id="dropdownMenu" type="text/html">
        <ul class="list dropdown-menu">
            {{each sort as value i}}
            <li>
                {{each value.menuSort as value i}}
                <a href="{{value.sortHref}}">{{value.sortName}}</a>
                {{/each}}
                <div class="links">
                    {{each value.links as value i}}
                    <a href="{{value.linkHref}}">{{value.linkName}}</a>
                    {{/each}}
                </div>
            </li>
            {{/each}}
        </ul>
    </script>
    <script id="menuView" type="text/html">
        {{each menu as value i}}
        <div class="menuView hide" id="{{value.menuId}}">
            <ul>
                {{each value.menuList as value i}}
                <li>
                    <h3><a href="{{value.mainHref}}">{{value.mainName}}</a></h3>
                    <p>
                        {{each value.menus as value i}}
                        <a href="{{value.secHref}}">{{value.secName}}</a>
                        {{/each}}
                    </p>
                </li>
                {{/each}}
            </ul>
            <div class="menuImg"><img alt="" src="{{value.menuImg}}" /></div>
        </div>
        {{/each}}
    </script>
    <script>
        var data = {
            sort: [
            <#if (topmap.classifyBar.classifyBarList)?? && (topmap.classifyBar.classifyBarList?size>0)>
           	<#list topmap.classifyBar.classifyBarList as cBar>
            	<#if (cBar.barCate)?? && (cBar.barCate?size>0)>
            	<#list cBar.barCate as bcate>
		            <#if bcate.cateId==-1>
		            	<#if bcate.temp2=="">
		                {"menuSort": [{"sortHref": "javascript:;", "sortName": "${bcate.cateName}"}],
		                </else>
		                {"menuSort": [{"sortHref": "${basePath}/${bcate.temp2!'0'}", "sortName": "${bcate.cateName}"}],
		                </#if>
		            <#elseif bcate.cateId??>
		            	{"menuSort": [{"sortHref": "${basePath}/list/${bcate.temp1!'0'}-${bcate.cateId?c}.html", "sortName": "${bcate.cateName}"}],
		            </#if>
                </#list>
                </#if>
                    "links": [
                    <#if (cBar.barQuick)?? && (cBar.barQuick?size>0)>
                	<#list cBar.barQuick as bquick>
                    	<#if bquick.cateId==0>
                    		<#if bquick.temp2=="">
                    		{"linkHref": "javascript:;", "linkName": "${bquick.cateName}"}
                    		<#else>
                    		{"linkHref": "${basePath}/${bquick.temp2!'0'}", "linkName": "${bquick.cateName}"}
                    		</#if>
                    	<#elseif bquick.cateId??>
                    		{"linkHref": "${basePath}/list/${bquick.cateId?c}-${bquick.temp1!'0'}.html", "linkName": "${bquick.cateName}"}
                    	</#if>
						<#if bquick_has_next>,</#if>
                     </#list>
                     </#if>
                    ]}
                <#if cBar_has_next>,</#if>     
            </#list>
            </#if>
            ],
            menu: [
            <#if (topmap.classifyBar.classifyBarList)?? && (topmap.classifyBar.classifyBarList?size>0)>
			<#list topmap.classifyBar.classifyBarList as cBar>
                {"menuId": "submenu-${cBar_index+1}", "menuImg": "../images/menuImg.png", "menuList": [
                
                <#list cBar.childs as cbtwo>
	            	<#if cbtwo.goodsCatId==-1>
		            	<#if cbtwo.url=="">
	                    {"mainHref": "javascript:;", "mainName": "${cbtwo.name}", 
	                    <#else>
	                     {"mainHref": "${basePath}/${cbtwo.url!'0'}", "mainName": "${cbtwo.name}", 
	                    </#if>
                    <#else>
                    {"mainHref": "${basePath}/list/${cbtwo.goodsCatId?c}-${cbtwo.temp3!'0'}.html", "mainName": "${cbtwo.name}", 
                    </#if>
                    
                    
                    
                    "menus": [
                    <#if (cbtwo.childs)?? && (cbtwo.childs?size>0)>
			        <#list cbtwo.childs as cbthird> 
                    <#if cbthird.goodsCatId==-1>
                    	<#if cbthird.url=="">
                    	{"secHref": "javascript:;", "secName": "${cbthird.name}"}
                    	<#else>
                    	{"secHref": "${basePath}/${cbthird.url!'0'}", "secName": "${cbthird.name}"}
                    	</#if>
                    
                    <#else>
                   		{"secHref": "${basePath}/list/${cbthird.goodsCatId?c}-${cbthird.temp3!'0'}.html", "secName": "${cbthird.name}"}
                    </#if>
                    	<#if cbthird_has_next>,</#if>
                    </#list>
                    </#if>
                    ]}
                    <#if cbtwo_has_next>,</#if>
                    </#list>
                    
                ]}
             <#if cBar_has_next>,</#if>  
            </#list>
            </#if>
            
            ]<!--menu-->
        };

        var sort = template('dropdownMenu', data),
            menu = template("menuView", data);
        $(".menucat").after(sort);
        $(".showlist").append(menu);
    </script>   
    
    <script type="text/javascript">
        $(function(){
           $.ajax({ url:"${basePath}/getnavsort.htm", async:false ,success: function(data){
                    $(".navLinks ul").find("li").each(function(){
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
        
        