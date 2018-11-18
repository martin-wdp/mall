<#--
	pb:分页对象
 -->
<#macro pagination pb>
<#-- 取得 应用的绝对根路径 -->
    <#assign basePath=request.contextPath>


<#if pb.endUrl??>
    <#assign firstUrl="${basePath}/${pb.url}-1.html${pb.endUrl}" />
    <#assign lastUrl="${basePath}/${pb.url}-${pb.lastPageNo}.html${pb.endUrl}" />
    <#assign prevUrl="${basePath}/${pb.url}-${pb.pageNo - 1}.html${pb.endUrl}" />
    <#assign nextUrl="${basePath}/${pb.url}-${pb.pageNo + 1}.html${pb.endUrl}" />
<#else>
    <#assign firstUrl="${basePath}/${pb.url}-1.html" />
    <#assign lastUrl="${basePath}/${pb.url}-${pb.lastPageNo}.html" />
    <#assign prevUrl="${basePath}/${pb.url}-${pb.pageNo - 1}.html" />
    <#assign nextUrl="${basePath}/${pb.url}-${pb.pageNo + 1}.html" />
</#if>

    <div class="paging_area">
         <div class="paging">
			<#if ((pb.pageNo-2)>0)>
				<#assign pageNo="${pb.pageNo-2}" />
			<#else>
				<#assign pageNo="${pb.firstPageNo}" />
			</#if>
			<#if ((pb.lastPageNo-1)>0)>
				<#assign endNo="${pb.lastPageNo-2}" />
			<#else>
				<#assign endNo="1" />
			</#if>
			<#if pb.pageNo == 1>
				<a class="prev_null" href="javascript:void(0);">&lt;&nbsp;上一页</a>
			<#else>
				<a class="" href="${prevUrl?string}">&lt;&nbsp;上一页</a>
				<#if (pb.pageNo >= 4)>
					<a class="num" href="${firstUrl?string}">${pb.firstPageNo}</a>
				</#if>
			</#if>
			<#if ((pb.pageNo-3)>1) >
		         <span class="ellipsis">...</span>
		    </#if>
	        <#list pageNo?number .. pb.endNo as item>
	             <#if item_index<=4>
		               <#if (item=pb.pageNo)>
		                   <a class="num_cur prev" href="javascript:void(0);">${item}</a>
		               <#else>

                           <#if pb.endUrl??>
                               <a class="num" href="${basePath}/${pb.url}-${item}.html${pb.endUrl}">${item}</a>
                           <#else>
                               <a class="num" href="${basePath}/${pb.url}-${item}.html">${item}</a>
                           </#if>
		               </#if>
	             </#if>
		    </#list>
		    <#if pb.pageNo!=pb.lastPageNo>
			     <#if ((pb.lastPageNo-pb.pageNo)>3) >
			           <span class="ellipsis">...</span>
			     </#if>
		    </#if>
		    <#if (pb.pageNo == pb.lastPageNo || pb.endNo <= 1)>
		    	 <#if (pb.lastPageNo > pb.pageNo)>
		         	<a class="num_cur" href="javascript:void(0);">${pb.lastPageNo}</a>
		         </#if>
		         <a class="next_null" href="javascript:void(0);">下一页&nbsp;&gt;</a>
			<#else>
				 <#if ((pb.lastPageNo - pb.pageNo)>=4)>
				 <a class="num" href="${lastUrl?string}">${pb.lastPageNo}</a>
				 </#if>
				 <a class="" href="${nextUrl}">下一页&nbsp;&gt;</a>
			</#if>
          </div>
     </div><!--/page-->
</#macro>						