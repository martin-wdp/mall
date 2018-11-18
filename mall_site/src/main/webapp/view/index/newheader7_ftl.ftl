<#assign basePath=request.contextPath>

<style>
    .divhide {
        display: none;
    }

    .mallSearch-input input[class="inputSearch"] {
        outline: medium;
    }
</style>
<input type="hidden" id="basePath" value="${basePath}"/>
<div class="section-header">
    <div class="container" style="position:relative;">
        <#--<h1 id="logo" style="height:80px;">
            <a href="${topmap.systembase.bsetAddress}">
                <img alt="" style="margin-top:16px;margin-left:0px;" src="${topmap.systembase.bsetLogo}" width="185" height="50">
                <img src="${basePath}/images/logo_font.png" style="position:absolute;left:0;top:70px;">
            </a>
        </h1>-->
        <h1 id="logo" style="height:80px;">
            <a href="${topmap.systembase.bsetAddress}">
                <#--2015-12-30 wuyanbo close christmas-->
                <#--<div style="position: absolute; left: -45px;top:5px; z-index: 998"><img src="${basePath}/images/christmas.png"></div>-->
                <img alt="" style="margin-top:16px;margin-left:0px;" src="${topmap.systembase.bsetLogo}" width="185" height="50">
                <img src="${basePath}/images/logo_font.png" style="position:absolute;left:0;top:70px;">
            </a>
        </h1>
<!--
        <div class="carTypeSearch">
            <div class="mallSearch-input">
                <i class="car-icon"></i>
                <span class="inputSearch2" id="selCarType">请先选择车型，方便选择商品！</span>
                <i class="clear-car" title="清除当前车型"></i>
                <button class="btnSearch add-car" type="button"></button>
            </div>
        </div>
        -->
        <div class="cartfd">
            <s class="cartBanner"></s>
            <a href="${basePath}/myshoppingcart.html" class="cartit">
                <span><s></s>购物车</span>
                <strong class="cartNum">0</strong>
            </a>

            <div class="miniCart hide">
                <div class="mCartBox">
                    <div class="mcBoxTop clearfix">
                    </div>
                    <div class="mcBoxList">
                    </div>
                    <div class="emCartBox hide">
                        <span>购物车中还没有商品，再去逛逛吧！</span>
                    </div>
                </div>
                <div class="mcGenius bmcGenius"></div>
                <div class="mCartError">
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

                    <h3>
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
                    <input type="hidden" name="AutoStyle" id="carstyleID" >
                <#-- <#if (keyWorlds)??>
                     <input class="inputSearch" type="text" name="title" autocomplete="off" value="${keyWorlds!''}"/>
                 <#else>
                  #--<#if (topmap.hotsearch)?? && (topmap.hotsearch?size>0)>
                         <input class="inputSearch" type="text" name="title" autocomplete="off" value=""
                                placeholder="${(topmap.hotsearch[0].keyword)!''}"/>
                     <#else>-->
                    <span class="carChange"></span>
                    <button class="btnSearch" type="button" onclick="checkSearch()"></button>
                        <#--<input id="inpSear" class="inputSearch" type="text" name="title" autocomplete="off" value=""-->
                             <#--num="0"  placeholder="请输入商品名称、OE码"/>-->
                    <input id="inpSear" class="inputSearch" type="text" name="title" autocomplete="off" value="请输入商品名称、OE码"
                           num="0" style="line-height: 38px" />
       <#--</#if>
   </#if>-->

                </div>
            </form>
            <div class="search_link">
                热门搜索：
            <#if (topmap.hotsearch)?? && (topmap.hotsearch?size>0)>
                <#list topmap.hotsearch as hots>
                    <#if (hots_index==0)>
                        <#if (hots.keyword?length>7)>
                            <a class="hot"
                               onclick="changeSearchKey(this);">${(hots.keyword)?substring(0,7)}</a>
                        <#else>
                            <a class="hot"  onclick="changeSearchKey(this);">${(hots.keyword)!''}</a>
                        </#if>
                    </#if>
                </#list>
                <#list topmap.hotsearch as hots>
                    <#if (hots_index>=1) && (hots_index<=5)>
                        <#if (hots.keyword?length>7)>
                            <a  onclick="changeSearchKey(this);">${(hots.keyword)?substring(0,7)}</a>
                        <#else>
                            <a  onclick="changeSearchKey(this);">${(hots.keyword)!''}</a>
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
                                <li><a class="navLink" id="mynavLink${nav_index+1}" name="mynavLink"
                                       onclick="clickNav('${(nav.barUrl)!''}','${nav_index+1}');"
                                       >${(nav.barName)!''}</a></li>
                            <#else>
                                <#if (nav.openChannel)?? && (nav.openChannel=='0')>
                                    <li><a class="navLink" id="mynavLink${nav_index+1}" name="mynavLink"
                                           onclick="clickNav('${(nav.barUrl)!''}','${nav_index+1}')"
                                           >${(nav.barName)!''}</a><#if (nav_index > 4 && nav_index <7)><span></span></#if></li>
                                <#else>
                                    <li><a class="navLink" id="mynavLink${nav_index+1}" name="mynavLink"
                                           onclick="clickNav('barchannelview/${(nav.barId?c)!''}','${nav_index+1}')"
                                           >${(nav.barName)!''}</a><span></span></li>
                                </#if>
                            </#if>
                        </#if>
                    </#list>
                </#if>
                    <li class="last"></li>
                </ul>
            </div>
            <input type="hidden" id="tempcbshowflag" value="${(topmap.temp.expFleid5)!''}">
        <#if (topmap.temp.expFleid5)?? && (topmap.temp.expFleid5=='0')>

        <div class="showlist divhide">
        <#else>
        <div class="showlist">
        </#if>
            <strong class="btnnav menucat">
                <a class="hover" href="javascript:" target="_self">全部商品分类<em class="all"></em></a>
            </strong>
        </div>
        </div>
        </div>
    </div>

    <script id="sort" type="text/html">
        <ul class="list dropdown-menu">
            {{each list as value i}}
            <li>
                {{each value.menuSort as value j}}
                <#--<a href="{{value.mhref}}"><img src="${basePath}/images/goods_list_1.png">{{value.msort}}</a>-->
                <a href="{{value.mhref}}"><img src="${basePath}/images/goods_list_{{i+1}}.png">{{value.msort}}</a>
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
            <div class="hotLinks">
                {{each value.hotLinks as value i}}
                <a href="{{value.hLinks}}">{{value.htValue}}<b><i></i></b></a>
                {{/each}}
            </div>
            <ul class="listSec">
                {{each value.mlist as value i}}
                <li>
                    <h3><a href="{{value.mhref}}">{{value.msort}}<b></b></a></h3>
                </li>
                {{/each}}
            </ul>
            <div class="listGoods">
                {{each value.mlist as value i}}
                <p>
                    {{each value.menus as value i}}
                    <a href="{{value.shref}}">{{value.sname}}</a>
                    {{/each}}
                </p>
                {{/each}}
            </div>
            <div class="menuImg">
                <ul class="bd-list">
                    {{each value.brand as value i}}
                    <li><a href="{{value.bdHref}}"><img alt="" src="{{value.bdImg}}"/></a></li>
                    {{/each}}
                </ul>
                <a class="bd-pro" href="{{value.tHref}}"><img src="{{value.tImg}}" alt=""/></a>
                <a class="bd-pro" href="{{value.bHref}}"><img src="{{value.bImg}}" alt=""/></a>
            </div>
        </div>
        {{/each}}
    </script>
    <script>
        var s = 0;
        var data = {
            list: [
            <#if (topmap.classifyBar.classifyBarList)?? && (topmap.classifyBar.classifyBarList?size>0)>
                <#list topmap.classifyBar.classifyBarList as cBar>
                    <#if (cBar.barCate)?? && (cBar.barCate?size>0)>
                    {
                        "menuSort": [

                            <#list cBar.barCate as bcate>
                                <#if bcate.cateId==-1>
                                    <#if bcate.temp2=="">
                                        {"msort": "${bcate.cateName}", "mhref": "javascript:;","imgsrc":"${cBar.imgSrc}"}
                                    <#else>
                                        {"msort": "${bcate.cateName}", "mhref": "${basePath}/${bcate.temp2!'0'}","imgsrc":"${cBar.imgSrc}"}
                                    </#if>
                                <#elseif bcate.cateId??>
                                    {
                                        "msort": "${bcate.cateName}",
                                        "mhref": "${basePath}/list/${bcate.temp1!'0'}-${bcate.cateId?c}.html",
                                        "imgsrc":"${cBar.imgSrc}"
                                    }
                                </#if>
                                <#if bcate_has_next>,</#if>
                            </#list>

                        ],
                    </#if>
                    "links": [
                        <#if (cBar.barQuick)?? && (cBar.barQuick?size>0)>
                            <#list cBar.barQuick as bquick>
                                <#if bquick.cateId==0>
                                    <#if bquick.temp2=="">
                                        {"lname": "${bquick.cateName}", "lhref": "javascript:;"}
                                    <#else>
                                        {"lname": "${bquick.cateName}", "lhref": "${basePath}/${bquick.temp2!'0'}"}
                                    </#if>
                                <#elseif bquick.cateId??>
                                    {
                                        "lname": "${bquick.cateName}",
                                        "lhref": "${basePath}/list/${bquick.cateId?c}-${bquick.temp1!'0'}.html"
                                    }
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

                    {
                        "mid": "submenu-${cBar_index+1}",

                        <#if (cBar.indexAdvertList)?? && (cBar.indexAdvertList?size>0)>
                            <#list cBar.indexAdvertList as cbAdvert>
                                <#if (cbAdvert.adverSort==1)>
                                    "tHref": "${cbAdvert.adverHref}", "tImg": "${cbAdvert.adverPath}",
                                </#if>
                                <#if (cbAdvert.adverSort==2)>
                                    "bHref": "${cbAdvert.adverHref}", "bImg": "${cbAdvert.adverPath}",
                                </#if>
                            </#list>
                        </#if>
                        "hotLinks": [], "brand": [
                        <#if (cBar.indexBrandList)?? && (cBar.indexBrandList?size>0)>
                            <#list cBar.indexBrandList as cbBrand>
                                <#if (cbBrand.sort>=1) && (cbBrand.sort<=11)>
                                    {"bdHref": "${(cbBrand.url)!''}", "bdImg": "${cbBrand.logoSrc}"}
                                </#if>
                                <#if cbBrand_has_next>,</#if>
                            </#list>
                        </#if>
                    ], "mlist": [
                        <#if (cBar.childs)?? && (cBar.childs?size>0)>
                            <#list cBar.childs as cbtwo>
                                <#if cbtwo.goodsCatId==-1>
                                    <#if !cbtwo.url?? || cbtwo.url=="">
                                        {"mhref": "javascript:;", "msort": "${cbtwo.name}",
                                    <#else>
                                        {"mhref": "${basePath}/${cbtwo.url!'0'}", "msort": "${cbtwo.name}",
                                    </#if>
                                <#else>
                                {"mhref": "${basePath}/list/${cbtwo.goodsCatId?c}-${cbtwo.temp3!'0'}.html",
                                    "msort": "${cbtwo.name}",
                                </#if>
                                "menus": [
                                    <#if (cbtwo.childs)?? && (cbtwo.childs?size>0)>
                                        <#list cbtwo.childs as cbthird>
                                            <#if cbthird.goodsCatId==-1>
                                                <#if cbthird.url=="">
                                                    {"shref": "javascript:;", "sname": "${cbthird.name}"}
                                                <#else>
                                                    {
                                                        "shref": "${basePath}/${cbthird.url!'0'}",
                                                        "sname": "${cbthird.name}"
                                                    }
                                                </#if>
                                            <#else>
                                                {
                                                    "shref": "${basePath}/list/${cbthird.goodsCatId?c}-${cbthird.temp3!'0'}.html",
                                                    "sname": "${cbthird.name}"
                                                }
                                            </#if>
                                            <#if cbthird_has_next>,</#if>
                                        </#list>
                                    </#if>
                                ]}
                                <#if cbtwo_has_next>,</#if>
                            </#list>
                        </#if>

                    ]
                    }
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
        window.onload = function () {
            $.ajax({
                url: '${basePath}/getNavsortinfo.htm',
                success: function (navsort) {
                    document.getElementById("mynavLink" + navsort).className = "navLink on";
                }
            });
        };
        function clickNav(url, sort) {
            $.ajax({
                url: "${basePath}/navsort.htm?navsort=" + sort, async: false, success: function (date) {
                }
            });

            if (url.indexOf("ttp://") != -1) {

                window.location.href = url;

            } else {

                window.location.href = "${basePath}/" + url;

            }
            return false;
        }

        var htmStr = "<i></i><em id='selCarType'>请先选车型</em>";
        $(".carChange").click(function(){
            $(".vinBox").show();
            $("#errmsg").html("");
            $(".navTop li").removeClass("carHover");
            $("#vin").addClass("carHover");
            $(".vinBox").show();
            $(".handBox").hide();
            var htm = $(this).children("em").html();
            if(!htm){
                //$(".carChange").css("background","#FFAF02").html(htmStr);
            }
        });

        $("#inpSear").focus(function(){
            if($(this).val() == "请输入商品名称、OE码"){
                $(this).val("");
            }
        }).blur(function(){
            if($(this).val() ==""){
                $(this).val("请输入商品名称、OE码");
            }
        });


    </script>
