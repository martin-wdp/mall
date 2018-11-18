<div class="bottom_banner pt10">
	    <#if map.pageAdvs??>
	      <#list map.pageAdvs as sadv>
	        <#if sadv_index == 1>
			    <a href="${basePath}/${(sadv.adverHref)!''}" style="display:block; width: 100%; height: 200px; background: url(${(sadv.adverPath)!''}) no-repeat center center;"></a>
	        </#if>
	      </#list>
	    </#if>
	</div>
<#include "../index/newbottom.ftl"/>
