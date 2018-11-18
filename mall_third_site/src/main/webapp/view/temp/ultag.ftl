<#assign basePath=request.contextPath>
<#macro ultag tempId>
    <ul class="nav nav-tabs" id="ultag">
    <li id="li2" role="presentation" class="active">
        <a href="${basePath}/tempclassifybarlist.html?tempId=${tempId}">页面导航</a>
    </li>
      <li id="li3" role="presentation">
            <a href="${basePath}/tempadvertlist.html?tempId=${tempId}&atId=157">轮播大广告</a>
     </li>
    <#--<li id="li4" role="presentation">-->
        <#--<a href="${basePath}/tempadvertlist.html?tempId=${tempId}&atId=159">轮播小广告</a>-->
    <#--</li>-->
    <li id="li5" role="presentation">
        <a href="${basePath}/tempadvertlist.html?tempId=${tempId}&atId=161"">页面广告</a>
    </li>
    <#--<li id="li6" role="presentation">-->
        <#--<a href="${basePath}/toupdatetempnews.html?tempId=${tempId}">新闻公告</a>-->
    <#--</li>-->
   <#-- <li id="li7" role="presentation">
        <a href="configure05.html">品牌</a>
    </li>-->
    <li id="li8" role="presentation">
        <a href="${basePath}/tempstoreylist.html?tempId=${tempId}">楼层</a>
    </li>
    <#--<li id="li9" role="presentation">-->
        <#--<a href="${basePath}/tempstoreyhotgoods.html?tempId=${tempId}">热销推荐</a>-->
    <#--</li>-->
</ul>
</#macro>