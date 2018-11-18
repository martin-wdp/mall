<#assign basePath=request.contextPath>
<input type="hidden" id="basePath" value="${basePath}"/>
<div class="section-header">
    <div class="content">
        <h1 id="logo">
            <a href="${topmap.systembase.bsetAddress}"><img alt="" src="${topmap.systembase.bsetLogo}" width="165" height="40"></a>
            <#if (topmap.logoAdv)?? &&((topmap.logoAdv)?size>0)>
            <div class="logoBanner"><a href="${topmap.logoAdv.adverHref}"><img alt="" src="${topmap.logoAdv.adverPath}" width="150" height="80"></a></div>
            </#if>
        </h1>

        <div class="cartfd">
            <s class="cartBanner"></s>
            <div class="cartit">
                <span><s></s><a href="${basePath}/myshoppingcart.html">我的购物车</a></span>
                <strong class="cartNum">0</strong>
            </div>
            <div class="miniCart hide">
                <div class="mCartBox">
                    <!-- mcBoxTop 居上，滚动时依然居上mcFloat展示 -->
                    <div class="mcBoxTop clearfix">
                        <!--<div class="mcChk"><input type="checkbox" /></div>
                        <label for="" class="mcElect">全选</label>-->
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
                                 <a href="${basePath}/myshoppingcart.html" class="mcGo" style="color:#fff;">结算</a>
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
            <form class="mallSearch-form" action="${basePath}/goods/searchproduct2.html" method="get">
                <div class="mallSearch-input">
                    <label for=""></label>
                    <#if (keyWorlds)??>
                    <input class="inputSearch" type="text" name="title" autocomplete="off" value="${(keyWorlds)!''}" />
                    <#else>
	                    <#if (topmap.hotsearch)?? && (topmap.hotsearch?size>0)>
	                    <input class="inputSearch" type="text" name="title" autocomplete="off" value="" placeholder="${(topmap.hotsearch[0].keyword)!''}"/>
                        <#else>
                            <input class="inputSearch" type="text" name="title" autocomplete="off" value=""/>
	                    </#if>                    
                    </#if>
                    <button class="btnSearch" type="button" onclick="checkSearch()" ></button>
                </div>
            </form>
            <div class="search_link">
                <#if (topmap.hotsearch)?? && (topmap.hotsearch?size>0)>
	               <#list topmap.hotsearch as hots>
		                <#if (hots.sort==1)>
			               <#if (hots.keyword?length>7)>
				                <a class="hot" href="javascript:;" onclick="changeSearchKey(this);">${(hots.keyword)?substring(0,7)}</a>
				            <#else>
				            	<a class="hot" href="javascript:;" onclick="changeSearchKey(this);">${(hots.keyword)!''}</a>
				            </#if>	
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
                    <#if (nav_index < 8)>
	                 	<#if (nav.barName=="首页") || (nav.barUrl=="index.html")>
	                    	<li><a class="navLink on" onclick="clickNav('${(nav.barUrl)!''}','${nav_index+1}');"  href="javascript:;">${(nav.barName)!''}</a></li>	                  
	                    <#else>
	                    	<#if (nav.openChannel)?? && (nav.openChannel=='0')>
	                    	<li><a class="navLink" onclick="clickNav('${(nav.barUrl)!''}','${nav_index+1}')" href="javascript:;">${(nav.barName)!''}</a></li>
	                    	<#else>
	                    	<li><a class="navLink" onclick="clickNav('barchannelview/${(nav.barId?c)!''}','${nav_index+1}')" href="javascript:;">${(nav.barName)!''}</a></li>
	                    	</#if>
	                    </#if>
                    </#if>
	                </#list>
                </#if> 
                </ul>
            </div>

            <div class="showlist">
                <strong class="btnnav menucat">
                    <a class="hover" href="javascript:;">全部商品分类</a>
                </strong>
            </div>
        </div><!--mainnav-->
    </div>
</div>

<div class="show-box">
	<div id="slides"></div>
</div>

<#--<div class="bh-mask"></div>
    <div id="ctDia" class="bh-dialog">
        <div class="dia-tit">
            <h4>加入收藏</h4>
            <a class="dia-close" href="javascript:;" onclick="scls(this)"></a>
        </div>
        <div class="dia-cont">
            <p>加入收藏失败，请使用菜单栏或Ctrl+D进行添加！</p>
        </div>
        <div class="dia-btn"><a href="javascript:;" onclick="scls(this)">确定</a></div>
    </div>-->

<script id="sort" type="text/html">
    <ul class="list dropdown-menu">
        {{each list as value i}}
        <li>
            <img alt="" src="{{value.mimg}}"/>
            <div class="links">
                {{each value.links as v index}}
                <a href="{{v.lhref}}">{{v.lname}}</a>
                {{/each}}
            </div>
        </li>
        {{/each}}
    </ul>
</script>
<script id="menu" type="text/html">
    {{each menu as value i}}
    <div class="menuView hide" id="{{value.mid}}">
        <ul>
            {{each value.mlist as value i}}
            <li>
                <h3><a href="{{value.mhref}}">{{value.msort}}</a></h3>
                <p>
                    {{each value.menus as value i}}
                    <a href="{{value.shref}}">{{value.sname}}</a>
                    {{/each}}
                </p>
            </li>
            {{/each}}
        </ul>
        <div class="menuImg"><img src="{{value.mimg}}" alt="" /></div>
    </div>
    {{/each}}
</script>
<script id="show" type="text/html">
        {{each slides as value i}}
        <div class="slide">
            <a class="slide-item" href="{{value.shref}}" style="background-image:url({{value.simg}});"></a>
            <div class="slide-wp">
                <div class="side">
                    <a href="{{value.sideHref01}}"><img alt="" src="{{value.side01}}"/></a>
                    <a href="{{value.sideHref02}}"><img alt="" src="{{value.side02}}"/></a>
                </div>
            </div>
        </div>
        {{/each}}
    </script>
<script>
    var data = {
    
        list: [     
        <#if (topmap.classifyBar.classifyBarList)?? && (topmap.classifyBar.classifyBarList?size>0)>
           <#list topmap.classifyBar.classifyBarList as cBar>              
            {"mimg":"${cBar.imgSrc!''}","mhref":"javascript:;",            
            "links":[
            	  <#if (cBar.barCate)?? && (cBar.barCate?size>0)>
                  <#list cBar.barCate as bcate>
                  		<#if bcate.cateId==-1>
                  			<#if bcate.temp2=="">
                				{"lname":"${bcate.cateName}","lhref":"javascript:;"},
                			<#else>
                				{"lname":"${bcate.cateName}","lhref":"${basePath}/${bcate.temp2!'0'}"},
                			</#if>
                		<#elseif bcate.cateId??>
                			{"lname":"${bcate.cateName}","lhref":"${basePath}/list/${bcate.cateId?c}-${bcate.cateId?c}.html"},
                		</#if>
                  </#list>
                  </#if>
            	             
            ]},  
          </#list>
        </#if>    
        ],
        menu: [
        <#if (topmap.classifyBar.classifyBarList)?? && (topmap.classifyBar.classifyBarList?size>0)>
           <#list topmap.classifyBar.classifyBarList as cBar>  
            {"mid":"submenu-${cBar_index+1}","mimg":"${basePath}/index_eight/images/menuImg.png","mlist":[
             
            <#list cBar.childs as cbtwo>
	           <#if cbtwo.goodsCatId==-1>
	               <#if !cbtwo.url??|| cbtwo.url=="">            
                       {"mhref":"javascript:;","msort":"${cbtwo.name}",
                   <#else>
                   	   {"mhref":"${basePath}/${cbtwo.url!'0'}","msort":"${cbtwo.name}",
                	</#if>
               <#else> 
                 {"mhref":"${basePath}/list/${cbtwo.temp4!'0'}-${cbtwo.temp3!'0'}.html","msort":"${cbtwo.name}",
               
                "menus":[
                <#if (cbtwo.childs)?? && (cbtwo.childs?size>0)>
			    <#list cbtwo.childs as cbthird> 
		           <#if cbthird.goodsCatId==-1>
			              <#if cbthird.url=="">
		                    {"shref":"javascript:;","sname":"${cbthird.name}"},
		                  <#else>
		                   {"shref":"${basePath}/${cbthird.url!'0'}","sname":"${cbthird.name}"},
		                  </#if>
	               <#else>
	                {"shref":"${basePath}/list/${cbthird.goodsCatId?c}-${cbthird.temp3!'0'}.html","sname":"${cbthird.name}"},
               	   </#if>
               </#list>
               </#if>
                ]
                },
                
            </#if>
               </#list>                
            ]},
     </#list>
</#if>          
        ],
        slides: [
              <#if (topmap.classifyBar.classifyBarList)?? && (topmap.classifyBar.classifyBarList?size>0)>
				<#list topmap.classifyBar.classifyBarList as cBar>
					<#if (cBar.indexAdvertList)?? && (cBar.indexAdvertList?size>0)>
            		<#list cBar.indexAdvertList as cbAdvert>
            			<#if (cbAdvert.adverSort==1)>
		                {"shref":"${cbAdvert.adverHref}","simg":"${cbAdvert.adverPath}",
		                </#if>
		                <#if (cbAdvert.adverSort==2)>
		                "sideHref01":"${cbAdvert.adverHref}","side01":"${cbAdvert.adverPath}",
		                </#if>
		                <#if (cbAdvert.adverSort==3)>
		                "sideHref02":"${cbAdvert.adverHref}","side02":"${cbAdvert.adverPath}"},
		                </#if>
		            </#list>
		            </#if>
                </#list>
            </#if> 
            ]        
    }

    var html = template('sort', data);
    var menu = template('menu', data);
    var slide = template('show', data);
    $(".menucat").after(html);
    $(".showlist").append(menu);
    $("#slides").append(slide);
</script>

<script type="text/javascript">
        <#--$(function(){-->
           <#--$.ajax({ url:"${basePath}/getnavsort.htm", async:false ,success: function(data){-->
                    <#--$(".navLinks ul").find("li").each(function(){-->
                    <#--$(this).find("a").removeClass("on");-->
                        <#--if($(this).index()+1==data){-->
                            <#--$(this).find("a").addClass("on");-->
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



