<div class="login_title">
    <!--<h4>欢迎登录${(mobSiteBasic.ename)!''}</h4>-->
    <!--<nav>
        <ul>
            <li alt="logo"><img src="${basePath}/images/logo.png" height="32"/></li>
            <li alt="电话、客服">
                <img src="${basePath}/images/index_talk.png" width="170" height="22"/>
                <span class="btn-phone" alt="电话"></span>
                <span class="btn-customerService" alt="客服"></span>
            </li>
        </ul>
    </nav>-->
    <div class='search-box search-box-bg'>
        <div class='search-cont'>
            <p class='qpmall-logo qpmall-logo2' style="width:10%;"><a id="go" href="${basePath}/cates.html?tag=2"><img src='${basePath}/images/btn_back_icon.png'/></a></p>
            <form action="${basePath}/searchProduct.htm" method="get" id="searchProductForm" >
                <p class='inp' style="width:85%;">
                    <input style="height:36px;" type='text' placeholder='关键字' name="keyWords" value="<#if keyWords??>${keyWords}</#if>" />
                    <input type="hidden" value="0" name="storeId" id="storeId">
                    <button id="searchBtn" style="height: 2.3em;" class='btn_search'></button></p>
            </form>
            <!--  <p class='scanning'><img src='${basePath}/images/scanning.png'/></p>
            <p style="line-height:30px;font-size:1.4em;color:#fff;">商品列表</p>-->
        </div>
    </div>
</div>
<input type="hidden" id="vip" value="${vip!'0'}">
<input type="hidden" id="basePath" value="${basePath!''}">
<div style="height:50px;"></div>
<script src="${basePath}/js/jquery.js"></script>
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
<!-- /login_title -->