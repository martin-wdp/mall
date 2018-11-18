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
    <header class="text-center">
        <a id="go" href="<#--<#if backLevUrl??>${basePath}/${backLevUrl}<#else>-->javascript:history.go(-1);<#--</#if>-->" style="width:1.2em;height:1.8em;font-size:19px;position:absolute;top:7px;left:14px;">
            <img src="${basePath}/images/btn_back_icon.png" >
        </a>
        ${pageName!''}
    </header>
    <input type="hidden" id="basePath" value="${basePath!''}">
    <input type="hidden" id="vip" value="${vip!'0'}">
</div><!-- /login_title -->
<div style="height:50px;"></div>