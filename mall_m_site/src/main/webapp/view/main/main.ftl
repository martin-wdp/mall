<#assign basePath=request.contextPath>
<#include "main.html"/>
<#include "../common/smart_menu.ftl"/>
<#if sCodeList?? && (sCodeList?size>0)>
<div >
	<#list sCodeList as sCode>
		<#if (sCode.code)??>
			<#if (sCode.code?starts_with("<script") && sCode.code?ends_with("</script>"))>
				${(sCode.code)!''}
			<#else>
				<script>${(sCode.code)!''}</script>
			</#if>
		</#if>
	</#list>
</#if>
</div>
</div><!--/main-->
 </body>
</html>