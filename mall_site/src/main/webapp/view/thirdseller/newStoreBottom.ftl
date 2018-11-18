<#if map.pageAdvs??>
<#list map.pageAdvs as sadv>
<#if sadv.adverSort == 14>
<div class="bottom_banner comwidth"><img src="${(sadv.adverPath)!''}" /></div>
</#if>
</#list>
</#if>
<#include "../index/newbottom.ftl"/>