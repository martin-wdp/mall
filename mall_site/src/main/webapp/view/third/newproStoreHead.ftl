
<#--<div class="headlogobox">-->
    <#--<div  class="comwidth ">-->
    	<#---->
        <#--<div class="headlogo">-->
        	<#--<#if map.pageAdvs??>-->
        	<#--<#list map.pageAdvs as sadv>-->
        	<#--<#if sadv.adverSort==1>-->
            <#--<a href="${(sadv.adverHref)!'#'}" class="headlogolp fl"><img src="${(sadv.adverPath)!''}"/></a>-->
            <#--</#if>-->
            <#--</#list>-->
            <#--</#if>-->
            <#---->
            <#---->
            <#---->
            <#--<form action="${basePath}/thirdstoreindex/${map.thirdId}.html"  id="searchForm" method = "POST" >-->
                <#--<input class="inputsearch fr" type="text" name="title" value="<#if map.search.title??>${(map.search.title)?html}</#if>" onblur="searchTitleHead(this);" placeholder="请输入您要搜索的商品" />-->
                <#--<button id="headsearch" type="button" ></button>-->
            <#--</form>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->

<div class="shopheadbox clearfix">
    <div class="comwidth shopnavs">
    <#if map.classBar??>
        <ul>
        <#list map.classBar as bar>
        	<#--<#if (nav.barName=="首页") >
            <li class="shopnav current" ><a href="#">${(bar.name)!''}</a></li>
            </#if>-->
            <li class="shopnav"><a href="<#if (bar.url?lower_case)?contains("http://")>${(bar.url)!''}<#else>${basePath}/${(bar.url)!''}</#if>">${(bar.name)!''}</a></li>
       </#list>
        </ul>
     </#if>
    </div>
</div>