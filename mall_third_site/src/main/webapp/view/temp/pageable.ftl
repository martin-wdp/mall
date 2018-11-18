<div class="footer-operation">
    <#if (map.pb.list)??&&(map.pb.list?size gt 0)>
    <div class="ops-left">
        <button class="btn btn-primary btn-xs" type="button" id="muilty-delete-btn">批量删除</button>
    </div>
    <div class="ops-right">
        <nav>
            <ul class="pagination">
                <li <#if map.pb.pageNo<=1>class="disabled"</#if>>
                    <a <#if map.pb.pageNo<=1><#else>onclick="changePageNo(this);"</#if> href="javascript:;" aria-label="Previous" data-role="${map.pb.prePageNo}" data-role="${map.pb.prePageNo}">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            <#if (map.pb.startNo?number>1)>
                <li><a href="javascript:void(0);" onclick="changePageNo(this);" data-role="1">1</a></li><li><a href="javascript:;">...</a></li>
            </#if>
            <#list map.pb.startNo?number .. map.pb.endNo as item>
                <li <#if item == map.pb.pageNo>class="active"</#if>><a href="javascript:;" <#if item == map.pb.pageNo><#else>onclick="changePageNo(this);"</#if> data-role="${item}">${item}</a></li>
            </#list>
            <#if (map.pb.totalPages?number>map.pb.endNo)>
                <li><a href="javascript:;">...</a></li><li><a href="javascript:void(0);" onclick="changePageNo(this);" data-role="${map.pb.totalPages}">${map.pb.totalPages}</a></li>
            </#if>
                <li <#if map.pb.pageNo gte map.pb.endNo> class="disabled"</#if>>
                    <a <#if map.pb.pageNo gte map.pb.endNo><#else>onclick="changePageNo(this);"</#if> data-role="${map.pb.nextPageNo}" href="javascript:;" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
    <#else>
        <center>暂无数据</center>
    </#if>
</div>

<script>
    /*分页*/
    function changePageNo(obj){
        $("#pageNo").val($(obj).attr("data-role"));
        $("#infoForm").submit();
    }
</script>