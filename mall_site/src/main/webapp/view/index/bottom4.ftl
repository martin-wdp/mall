<#assign basePath=request.contextPath>
<div class="section-footer">
    <div class="foot_service">
     <#if (bottom.helpCates)?? && (bottom.helpCates?size>0)>
     <#list bottom.helpCates as helpcate>
       <dl class="fs01">
      
           <dt><b></b>${(helpcate.helpcateName)!''}</dt>
           <dd>
           		<#if (helpcate.helpCenters)?? && (helpcate.helpCenters?size>0)>
	                <#list helpcate.helpCenters as helpcenter>
	                <a href="${basePath}/help/${helpcenter.helpId}">${(helpcenter.helpTitle)!''}</a>
	                </#list>
                </#if>               
           </dd>
       </dl>
    </#list>
    </#if>
      
    </div>
    <div id="footnavigationer">
        <div  id="footnavigation">
        ${topmap.systembase.bsetCopyright}
            
        </div>
    </div>
    <!--<div id="footer" class="box">
        <div class="c-right">
            <p>北京市公安局朝阳分局备案编号110105014669&nbsp;&nbsp;<s></s>&nbsp;&nbsp;京ICP证070359号&nbsp;&nbsp;<s></s>&nbsp;&nbsp;互联网药品信息服务资格证编号(京)-非经营性-2011-0034</p>
            <p>Copyright © 2004-2014 版权所有</p>
        </div>
        <div class="footerImg">
            <a class="footerImg01" href="javascript:;"></a>
            <a class="footerImg02" href="javascript:;"></a>
            <a class="footerImg03" href="javascript:;"></a>
            <a class="footerImg04" href="javascript:;"></a>
        </div>
    </div>-->
</div>