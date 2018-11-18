<#--
	pb:分页对象
 -->
<#macro pagination pb>
<#-- 取得 应用的绝对根路径 -->
    <#assign basePath=request.contextPath>
    <#assign firstUrl="${basePath}/${pb.url}-1.html" />
    <#assign lastUrl="${basePath}/${pb.url}?pageNo=${pb.lastPageNo}" />
    <#assign prevUrl="${basePath}/${pb.url}?pageNo=${pb.pageNo - 1}" />
    <#assign nextUrl="${basePath}/${pb.url}?pageNo=${pb.pageNo + 1}" />



            <#--<li class="disabled">
                <a href="javascript:;" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <li class="active"><a href="javascript:;">1</a></li>
            <li><a href="javascript:;">2</a></li>
            <li><a href="javascript:;">3</a></li>
            <li><a href="javascript:;">4</a></li>
            <li><a href="javascript:;">5</a></li>
            <li>
                <a href="javascript:;" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>-->



<#if (pb.list)??&&(pb.list?size>0)>
<div class="ops-right">
    <nav>
        <ul class="pagination">
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
            <li class="disabled">
                <a href="javascript:void(0);" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        <#else>
            <li>
                <a href="${prevUrl?string}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <#if (pb.pageNo >= 4)>
                <li><a href="${basePath}/${pb.url}?pageNo=1">${pb.firstPageNo}</a></li>
            </#if>
        </#if>
        <#if ((pb.pageNo-3)>1) >
            <#--<span>...</span>-->
            <li><a href="javascript:;">...</a></li>
        </#if>
        <#list pageNo?number .. pb.endNo as item>
            <#if item_index<=4 && (item>0)>
                <#if (item=pb.pageNo)>
                    <li class="active"><a href="javascript:;">${item}</a></li>
                <#else>
                    <li><a href="${basePath}/${pb.url}?pageNo=${item}">${item}</a></li>
                </#if>
            </#if>
        </#list>
        <#if pb.pageNo!=pb.lastPageNo>
            <#if ((pb.lastPageNo-pb.pageNo)>5) >
                <#--<span>...</span>-->
                <li><a href="javascript:;">...</a></li>
            </#if>
        </#if>
        <#if (pb.pageNo == pb.lastPageNo || pb.endNo <= 1)>
            <#if (pb.lastPageNo > pb.pageNo)>
                <li class="active"><a href="javascript:;">${pb.lastPageNo}</a></li>
            </#if>
            <li class="disabled">
                <a href="javascript:void(0);" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        <#else>
            <#if ((pb.lastPageNo - pb.pageNo)>=5)>
                <li><a href="${lastUrl?string}">${pb.lastPageNo}</a></li>
            </#if>
            <li>
                <a href="${nextUrl}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </#if>
        </ul>
    </nav>
</div>
<#else>
    <center>暂无数据</center>
</#if>
</#macro>