<#assign basePath=request.contextPath>
<style>
    .divhide{
        display: none;
    }
    </style>
<input type="hidden" id="basePath" value="${basePath}"/>
        <div class="mainnav">
            <div class="navLinks">
               <ul class="sort_list clearfix">
                <#if (topmap.navList)?? && (topmap.navList?size>0)>
                <#list topmap.navList as nav>
                <#if (nav_index < 8)>
                    <#if (nav.barName=="首页") || (nav.barUrl=="index.html")>
                    <li class="cur"><a   onclick="clickNav('${(nav.barUrl)!''}','${nav_index+1}');" href="javascript:void(0);">${(nav.barName)!''}</a></li>
                    <#else>
                        <#if (nav.openChannel)?? && (nav.openChannel=='0')>
                            <li  class=""><a   onclick="clickNav('${(nav.barUrl)!''}','${nav_index+1}')"  href="javascript:void(0);">${(nav.barName)!''}</a></li>
                        <#else>
                            <li class=""><a onclick="clickNav('barchannelview/${(nav.barId?c)!''}','${nav_index+1}')"  href="javascript:void(0);">${(nav.barName)!''}</a></li>
                        </#if>
                    </#if>
                </#if>
                </#list>
                </#if>
            </ul><!--/sort_list-->
            </div>
            <input type="hidden" id="tempcbshowflag" value="${(topmap.temp.expFleid5)!''}">
    <#if (topmap.temp.expFleid5)?? && (topmap.temp.expFleid5=='0')>

    <div class="showlist divhide">
    <#else>
    <div class="showlist">
    </#if>
                <strong class="btnnav menucat"><a class="hover" href="javascript:;">全部商品分类</a></strong>
                <ul class="list dropdown-menu">
                <#if (topmap.classifyBar.classifyBarList)?? && (topmap.classifyBar.classifyBarList?size>0)>
			    <#list topmap.classifyBar.classifyBarList as cBar>
			    <#if (cBar_index < 7)>
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
                                                <a href="${basePath}/list/${bcate.cateId?c}-${bcate.cateId?c}.html">${bcate.cateName}</a>
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
                </#if>
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
                        	<#if !cbtwo.url??|| cbtwo.url=="">
	                           	<h3><a href="">${cbtwo.name}</a></h3>
	                    	<#else>
	                             <h3><a href="${basePath}/${cbtwo.url!'0'}">${cbtwo.name}</a></h3>
	                     	</#if>
	                     <#else>
                            <h3><a href="${basePath}/list/${cbtwo.goodsCatId?c}-${cbtwo.temp3!'0'}.html">${cbtwo.name}</a></h3>
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
           
            </div><!--showlist-->
        </div><!--mainnav-->
    </div><!--content-->
</div><!--section-header-->
<#--<div class="bh-mask"></div>-->
    <#--<div id="ctDia" class="bh-dialog">-->
        <#--<div class="dia-tit">-->
            <#--<h4>加入收藏</h4>-->
            <#--<a class="dia-close" href="javascript:;" onclick="scls(this)"></a>-->
        <#--</div>-->
        <#--<div class="dia-cont">-->
            <#--<p>加入收藏失败，请使用菜单栏或Ctrl+D进行添加！</p>-->
        <#--</div>-->
        <#--<div class="dia-btn"><a href="javascript:;" onclick="scls(this)">确定</a></div>-->
    <#--</div>-->
   <#--<script> -->
 <#--//加入收藏-->
	<#--function addToFavorite(siteName){-->
		<#--try {   -->
			<#--window.external.AddFavorite($("#basePath").val(),siteName);-->
	    <#--} catch (e) {   -->
	        <#--try {   -->
	            <#--window.sidebar.addPanel($("#basePath").val(), siteName, "");   -->
	        <#--} catch (e) {   -->
	            <#--$(".collect_tip_cancel").click(function(){-->
	            	<#--cls();-->
	            <#--});-->
	            <#--sdia('ctDia');-->
	            <#---->
	        <#--}   -->
	    <#--}   -->
	<#--}	-->
<#--</script>-->
    <script type="text/javascript">
        <#--$(function(){-->
           <#--$.ajax({ url:"${basePath}/getnavsort.htm", async:false ,success: function(data){-->
                    <#--$(".sort_list").find("li").each(function(){-->
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
        <script type="text/javascript" src="${basePath}/index_two/js/jquery.slides.min.js"></script>
    <script type="text/javascript" src="${basePath}/index_two/js/jcarousellite_1.0.1.js"></script>
    <script type="text/javascript" src="${basePath}/index_two/js/jquery.lazyload.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
    <script type="text/javascript" src="${basePath}/index_two/js/index.js"></script>
