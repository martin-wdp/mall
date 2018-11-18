<#assign basePath=request.contextPath>
<#include "newtop11.ftl"/>
<#include "newheader11_ftl.ftl"/>
<input type="hidden" id="basePath" value="${basePath}"/>
<input type="hidden" id="oldsearchtext" value="${(map.searchBean.title)!''}"/>
