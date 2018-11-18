<#assign basePath=request.contextPath>
<div class="section-header">
    <div class="content">
        <h1 id="logo">
            <a href="${topmap.systembase.bsetAddress}"><img alt="" src="${topmap.systembase.bsetLogo}" width="165" height="40"></a>
            <div class="logoBanner"><a href="${basePath}/${(topmap.logoAdv.adverHref)!'#'}">
                <img alt="" src="${(topmap.logoAdv.adverPath)!'${basePath}/index_four/images/logoImg.jpg'}" width="120" height="38"/></a></div>
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
                    <div class="mcBoxList cart_list">
                    
                        
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
                                <span class="mcGo"><a href="../myshoppingcart.html">结算</a></span><!-- no-mcGo置灰状态 -->
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
            <form class="mallSearch-form" action="${basePath}/goods/searchproduct2.html" method="post">
                <div class="mallSearch-input">
                    <label for=""></label>
                    <#if (map.searchBean.title)??>
                    <input class="inputSearch" type="text"  name="title" value="${(map.searchBean.title)!''}"/>
                    <#else>
                    <input class="inputSearch" type="text"  name="title" paceholder="商品关键字" />
                    </#if>
                    <button class="btnSearch" type="submit"></button>
                </div>
            </form>
        </div>
        <div class="mainnav">
            <div class="navLinks">
                <ul>
               <#if (topmap.navList)?? && (topmap.navList?size>0)> 
	               	<#list topmap.navList as nav>
		               	<#if (nav.barName=="首页") || (nav.barUrl=="index.html")>
		                    <li><a class="navLink on" href="${basePath}/${(nav.barUrl)!''}">${(nav.barName)!''}</a></li>
		                <#else>
		                	<#if (nav.openChannel)?? && (nav.openChannel=='0')>
		                		<li><a class="navLink" href="${(nav.barUrl)!''}">${(nav.barName)!''}</a></li>
		                	<#else>
		                		<li><a class="navLink" href="${basePath}/barchannelview/${(nav.barId?c)!''}">${(nav.barName)!''}</a></li>
		                	</#if>
		                </#if>	                    
	                </#list>
               </#if>    
                </ul>
            </div>
            <input type="hidden" id="tempcbshowflag" value="${(topmap.temp.expFleid5)!''}">            
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
			                  <img alt="" src="${cBar.imgSrc!''}">
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
				                              <a href="${basePath}/item/${bquick.temp2!'0'}">${bquick.cateName}</a>
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
	                           		     <h3><a href="${basePath}/item/${cbtwo.url!'0'}">${cbtwo.name}</a></h3>
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
		                             		<a href="${basePath}/item/${cbthird.url!'0'}">${cbthird.name}</a>                             	
		                             	</#if>
		                             <#else>
		                             	<a href="${basePath}/list/${cbthird.goodsCatId?c}-${cbthird.temp3!'0'}.html">${cbthird.name}</a>
		                             </#if> 
		                       </#list>
		                      </#if> 	                                                         
	                            </p>
                        </li>
                     </#list><!--cbtwo list-->
                    </ul>       
                </div><!--submenu-1-->
 </#list>
 </#if>            
            </div><!--showlist-->
            
        </div><!--mainnav-->
    </div><!--content-->
</div><!--section-header-->

