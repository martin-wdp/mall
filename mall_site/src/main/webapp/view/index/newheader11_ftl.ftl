<#assign basePath=request.contextPath>
<style>
    .divhide{
        display: none;
    }
    .hot{color:rgb(223, 23, 56)!important}
</style>

<input type="hidden" id="basePath" value="${basePath}"/>
<div class="section-header">
        <div class="content">
            <h1 id="logo"><a href="${topmap.systembase.bsetAddress}"><img alt="" src="${topmap.systembase.bsetLogo}"/></a>
                <div class="logoBanner"><a href="${basePath}/${(topmap.logoAdv.adverHref)!'#'}"><img src="${(topmap.logoAdv.adverPath)!'${basePath}/index_eleven/images/ad.jpg'}"/></a></div>
            </h1>

            <div class="cartfd">
                <s class="cartBanner"></s>
                <a class="cartit" href="javascript:;">
                    <span><s></s>去购物车结算</span>
                    <strong class="cartNum">0</strong>
                </a>
                <div class="miniCart hide">
                    <div class="mCartBox">
                        <!-- mcBoxTop 居上，滚动时依然居上mcFloat展示 -->
                        <div class="mcBoxTop clearfix">
                            <div class="mcChk"><input type="checkbox" /></div>
                            <label for="" class="mcElect">全选</label>
                        </div>
                        <div class="mcBoxList">
                            
                        </div><!--mcBoxList-->
                        <div class="emCartBox hide">
                            <span>购物车中还没有商品，再去逛逛吧！</span>
                        </div>
                    </div><!--mCartBox-->
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
                    </div><!--mCartHandler-->
                </div><!--miniCart-->
            </div><!--cartfd-->
            <div class="B-notice">
                <div id="gongao">
                    <div id="scroll_div" class="scroll_div">
                        <div id="scroll_begin">


                        <#if (topmap.infoList)?? && (topmap.infoList?size>0)>
                		<#list topmap.infoList as info>
                            <a href="information/${info.infoId}">
                          ${info.title}。。。
                        </a>
                		</#list>
                		</#if>    
                        </div>
                        <div id="scroll_end"></div>
                    </div>
                </div>
            </div>  <!--B-notice-->
            <div class="search searchdiv">
                <form class="mallSearch-form" action="${basePath}/goods/searchproduct2.html" method="get">
                    <div class="mallSearch-input">
                     	<#if (keyWorlds)??>
                        <input class="inputSearch" type="text" name="title" autocomplete="off" value="${(keyWorlds)!''}" />
                        <#else>                        
	                        <#if (topmap.hotsearch)?? && (topmap.hotsearch?size>0)>
	                        <input class="inputSearch" type="text" name="title" autocomplete="off" value="" placeholder="${(topmap.hotsearch[0].keyword)!''}"/>
                            <#else>
                                <input class="inputSearch" type="text" name="title" autocomplete="off" value=""/>
	                        </#if>
                        </#if>
                        <button class="btnSearch" type="button" onclick="checkSearch()">搜索</button>
                    </div>
                </form>
                <div class="search_link">
                <#if (topmap.hotsearch)?? && (topmap.hotsearch?size>0)>
                    <#list topmap.hotsearch as hots>
                        <#if (hots_index==0)>
                            <#if (hots.keyword?length>7)>
                                <a class="hot" href="javascript:;" onclick="changeSearchKey(this);">${(hots.keyword)?substring(0,7)}</a>
                            <#else>
                                <a class="hot" href="javascript:;" onclick="changeSearchKey(this);">${(hots.keyword)!''}</a>
                            </#if>
                        </#if>
                    </#list>
                    <#list topmap.hotsearch as hots>
                        <#if (hots_index>=1) && (hots_index<=5)>
                            <#if (hots.keyword?length>7)>
                                <a href="javascript:;" onclick="changeSearchKey(this);">${(hots.keyword)?substring(0,7)}</a>
                            <#else>
                                <a href="javascript:;" onclick="changeSearchKey(this);">${(hots.keyword)!''}</a>
                            </#if>
                        </#if>
                    </#list>
                </#if>
                </div>
            </div><!--searchdiv-->

            <div class="mainnav">
                <div class="navLinks">
                    <ul>
                    <#if (topmap.navList)?? && (topmap.navList?size>0)>
                    <#list topmap.navList as nav>
                    	<#if (nav.barName=="首页") || (nav.barUrl=="index.html")>
                        <li><a class="navLink on" onclick="clickNav('${(nav.barUrl)!''}','${nav_index+1}');" href="javascript:;">${(nav.barName)!''}</a></li>
                        <#else>
                        	<#if (nav.openChannel)?? && (nav.openChannel=='0')>
                        	<li><a class="navLink" onclick="clickNav('${(nav.barUrl)!''}','${nav_index+1}')"  href="javascript:;">${(nav.barName)!''}</a></li>
                        	<#else>
                        	<li><a class="navLink" onclick="clickNav('barchannelview/${(nav.barId?c)!''}','${nav_index+1}')" href="javascript:;">${(nav.barName)!''}</a></li>
                        	</#if>
                        </#if>
                    </#list>
                    </#if>
                    </ul>
                </div>

                <input type="hidden" id="tempcbshowflag" value="${(topmap.temp.expFleid5)!''}">
            <#if (topmap.temp.expFleid5)?? && (topmap.temp.expFleid5=='0')>

            <div class="showlist divhide">
            <#else>
            <div class="showlist">
            </#if>
                    <strong class="btnnav menucat">
                        <a class="hover" href="javascript:;">全部商品分类</a>
                    </strong>
                </div>
            </div><!--mainnav-->
        </div><!--content-->
    </div><!--section-header-->
    <script id="sort" type="text/html">
    <ul class="list dropdown-menu">
        {{each list as value i}}
        <li>
            <img alt="" src="{{value.simg}}"/>
            {{each value.sorts as v index}}
            <a href="{{v.mhref}}">{{v.msort}}</a>
            {{/each}}
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
<script>
    var data = {
        list: [
        <#if (topmap.classifyBar.classifyBarList)?? && (topmap.classifyBar.classifyBarList?size>0)>
        <#list topmap.classifyBar.classifyBarList as cBar>
           	<#if (cBar.barCate)?? && (cBar.barCate?size>0)>
            {"simg":"${cBar.imgSrc!''}",
            "sorts":[
            <#list cBar.barCate as bcate>
            <#if bcate.cateId==-1>
            	<#if bcate.temp2=="">
            	{"msort":"${bcate.cateName}","mhref":"javascript:;"}
            	<#else>
            	{"msort":"${bcate.cateName}","mhref":"${basePath}/${bcate.temp2!'0'}"}
            	</#if>
            <#elseif bcate.cateId??>
            {"msort":"${bcate.cateName}","mhref":"${basePath}/list/${bcate.cateId?c}-${bcate.cateId?c}.html"}
            </#if>
             <#if bcate_has_next>,</#if>  
            </#list>
            
            ],
            </#if>
            "links":[
            <#if (cBar.barQuick)?? && (cBar.barQuick?size>0)>
            <#list cBar.barQuick as bquick>
	            <#if bquick.cateId==0>
	            	<#if bquick.temp2=="">
	            		{"lname":"${bquick.cateName}","lhref":"javascript:;"}
	            	<#else>
	            		{"lname":"${bquick.cateName}","lhref":"${basePath}/${bquick.temp2!'0'}"}
	            	</#if>
	            <#elseif bquick.cateId??>
	            {"lname":"${bquick.cateName}","lhref":"${basePath}/list/${bquick.cateId?c}-${bquick.temp1!'0'}.html"}
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
            {"mid":"submenu-${cBar_index+1}","mimg":"../images/menuImg.png","mlist":[
             <#if (cBar.childs)?? && (cBar.childs?size>0)>
             <#list cBar.childs as cbtwo>
            	<#if cbtwo.goodsCatId==-1>
            		<#if !cbtwo.url?? ||  cbtwo.url=="">
                	{"mhref":"javascript:;","msort":"${cbtwo.name}",
                	<#else>
                	{"mhref":"${basePath}/${cbtwo.url!'0'}","msort":"${cbtwo.name}",                	
                	</#if>
                <#else>
                 {"mhref":"${basePath}/list/${cbtwo.goodsCatId?c}-${cbtwo.temp3!'0'}.html","msort":"${cbtwo.name}",
                </#if>
            
                "menus":[
                <#if (cbtwo.childs)?? && (cbtwo.childs?size>0)>
			    <#list cbtwo.childs as cbthird>
			    	<#if cbthird.goodsCatId==-1> 
			    		<#if cbthird.url=="">
                            {"shref":"javascript:;","sname":"${cbthird.name}"}
                    	<#else>
                    		{"shref":"${basePath}/${cbthird.url!'0'}","sname":"${cbthird.name}"}
                    	</#if>
                    <#else>
                    	{"shref":"${basePath}/list/${cbthird.goodsCatId?c}-${cbthird.temp3!'0'}.html","sname":"${cbthird.name}"}
                    </#if>
                    <#if cbthird_has_next>,</#if>
                </#list>
                </#if>    
                    
                ]}
                <#if cbtwo_has_next>,</#if>
                </#list>
             </#if> 
            ]}
            <#if cBar_has_next>,</#if> 
          </#list>
   		  </#if>	
        ]
    };
    var html = template('sort', data);
    var menu = template('menu', data);
    $(".menucat").after(html);
    $(".showlist").append(menu);
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