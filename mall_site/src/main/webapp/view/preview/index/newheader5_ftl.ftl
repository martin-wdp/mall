<#assign basePath=request.contextPath>
<input type="hidden" id="basePath" value="${basePath}"/>
<div class="section_headerTop">
    <div class="slot slot_headerTop01"></div>   
    <#if (topmap.logoAdv)?? &&((topmap.logoAdv)?size>0)>
    <div class="slot slot_headerTop02" style="background-image:url(${topmap.logoAdv.adverPath});"></div>
    </#if>
</div>

<div class="section-header">
    <div class="content">
        <h1 id="logo">
            <a href="${topmap.systembase.bsetAddress}"><img src="${topmap.systembase.bsetLogo}" alt=""/></a>
            <div class="logoBanner"></div>
        </h1>
        <div class="search searchdiv">
            <form class="mallSearch-form" action="${basePath}/goods/searchproduct2.html">
                <div class="mallSearch-input">
                    <label for=""></label>
                    <#if (map.searchBean.title)??>
                    <input class="inputSearch" type="text" value="${(map.searchBean.title)!''}"/>
                   	<#else>
                   	<input class="inputSearch" type="text" placeholder="iPhone 6 Plus"/>
                   	</#if>
                    <button class="btnSearch" type="submit"></button>                   
                </div>
            </form>
        </div>
        <ul class="hd_menu">
            <li><span class="welcome">您好<#if cust??>${cust.customerNickname!''}</#if>，欢迎来到${topmap.systembase.bsetName}</span></li>
            <li class="login_info">
                <a class="site_login" href="${basePath}/login.html">[请登录]</a>
                <a class="site_register" href="${basePath}/register.html">[免费注册]</a>
            </li>
            <li class="loginout_info hide">
                <a class="site_loginout" href="${basePath}/logout.html">[退出]</a>
            </li>
            <li class="navCenter">
                <a href="${basePath}/customer/index.html">会员中心<s class="toB"></s></a>
                <div class="navInfo">
                    <div class="navPanel">
                        <a href="${basePath}/customer/myorder.html">我的订单</a>
                        <a href="${basePath}/customer/myfollw.html">我的收藏</a>
                    </div>
                </div>
            </li>
            <li><a class="favorite" href="javascript:;" onclick="addToFavorite('${topmap.systembase.bsetName}');">加入收藏</a></li>
        </ul>
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
            <div class="showlist">
                <strong class="btnnav menucat"><a class="hover" href="javascript:;">全部商品分类</a></strong>
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
                    </#list> <!--cbtwo list--> 
                    </ul>
                    <div class="menuImg"><img src="${basePath}/images/menuImg.png" alt="" /></div>
                </div><!--menuView-->
</#list>
 </#if>                  
           
            </div><!--showlist-->
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
    
   <script type="text/javascript"> 
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