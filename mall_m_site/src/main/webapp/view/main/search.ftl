<#assign basePath=request.contextPath>
<style>
    .ps-head{padding:0.5em;background: url(http://st.360buyimg.com/m/images/index/header-bg.png?v=2) repeat-x #efefef;  background-size: 100% 44px;}
    .ps-back{width: 15%; float: left; text-align: center;}
    .ps-back a{ display: block; background: url(images/arrow_left.png) no-repeat center center; width: 100%; background-size: auto 50%; height: 2.8em;  text-indent: -99999px;}

    .ps-search{border:1px solid #d6d6d6; border-radius: 4px; height: 2.3em; margin-top: 0.2em; width:100%;}
    .ps-search input{ width: 85%;height: 2.1em;
        background:#fff; border:none;margin-top:0;float:left;padding-left:0.5em}
    .ps-search button{float:right;width:15%;background: url(${basePath}/images/search.png) no-repeat center center; text-indent:1.8em;
        background-size: auto 50%; border:none; cursor: pointer; height: 2.1em; border-left:1px solid #d6d6d6;text-indent: -99999px;}
    .ps-clk{ width: 15%; float: left; text-align: center; height: 2.8em;}
    .ps-clk a{display: block; background: url(imagesst.png) no-repeat center center; width: 100%; background-size: auto 50%; height: 2.8em; text-indent: -99999px;}

</style>
<script src="${basePath}/js/jquery.js"></script>
<div class="ps-head">
    <div class="ps-search">
        <form action="${basePath}/searchProduct.htm" method="get" id="searchProductForm" style="margin-bottom:0;">
            <input type="text" value="<#if keyWords??>${keyWords}</#if>" placeholder="搜索商品" id="title" name="keyWords"/>
            <input type="hidden" value="0" name="storeId" id="storeId">
        </form>
        <button id="searchBtn">搜索</button>
    </div>

</div>
<script type="text/javascript">
    $(function(){
        $("#searchBtn").click(function(){
            $("#storeId").val($(".storeId").val());
            if($("#title").val()==''){
                $("#title").val($("#title").attr("placeholder"));
            }
            $("#searchProductForm").submit();
        })
    })
</script>