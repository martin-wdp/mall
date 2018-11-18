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
            <p class='qpmall-logo qpmall-logo2' style="width:10%;"><a id="go" href="javascript:history.go(-1);"><img src='${basePath}/images/btn_back_icon.png'/></a></p>
            <!-- <p class='inp' style="width:73%;"><input type='text' placeholder='关键词：VIN、OEM'/><button class='btn_search'></button></p>
            <p class='scanning'><img src='${basePath}/images/scanning.png'/></p>-->
            <p style="line-height:30px;font-size:1.4em;color:#fff;">商品分类</p>
        </div>
    </div>
   <#-- <div class='changeCar'>
    <#if selectVal?exists>
        <p><i>已选车型：${selectVal}</i><a href='${basePath}/selectcarindex.htm'>选车型</a></p>
    <#else >
        <p><i>选择车型</i><a href='${basePath}/selectcarindex.html'> 选车型</a></p>
    </#if>
    </div>-->
</div>
<input type="hidden" id="vip" value="${vip!'0'}">
<input type="hidden" id="basePath" value="${basePath!''}">
<div style="height:50px;"></div>
<!-- /login_title -->