<#assign basePath=request.contextPath>
<input type="hidden" id="basePath" value="${basePath}"/>
<div class="section_headerTop">
    <div class="slot slot_headerTop01">
        <ul class="left">
            <li><a class="favorite" href="javascript:;" onclick="addToFavorite('${topmap.systembase.bsetName}');"><s></s>加入收藏</a></li>
            <li><span class="welcome">您好<#if cust??>${cust.customerNickname!''}</#if>，欢迎来到${topmap.systembase.bsetName}!</span></li>
            <li class="login_info">
            	<#if cust??>
            	<#else>
                <a class="site_login" href="${basePath}/login.html">请登录</a>
                <a class="site_register" href="${basePath}/register.html">免费注册</a>
                </#if>
            </li>
            <li class="loginout_info">
            	<#if cust??>
                <a class="site_loginout" href="${basePath}/logout.html">退出</a>
                </#if>
            </li>
        </ul>
        <ul class="siteNav">
            <li class="nav_item"><a href="${basePath}/customer/myorder.html">我的订单</a></li>
            <li class="nav_item"><a href="${basePath}/customer/index.html">会员俱乐部</a></li>
            <li class="nav_item navCenter">
                <a href="${basePath}/customer/myfollw.html">收藏夹<s></s></a>
                <div class="navInfo">
                    <div class="navPanel">
                        <a href="${basePath}/customer/sellermyfollw.html">收藏的店铺</a>
                        <a href="${basePath}/customer/myfollw.html">收藏的商品</a>
                    </div>
                </div>
            </li>
            <li class="nav_item navCenter">
                <a href="${basePath}/help/2">客户服务<s></s></a>
                <div class="navInfo">
                    <div class="navPanel">
                        <a href="${basePath}/help/2">帮助中心</a>
                        <a href="${basePath}/help/65">售后服务</a>
                        <!--<a href="javascript:;">在线客服</a>-->
                        <a href="${basePath}/customer/ordercomplain.html">投诉中心</a>
                        <!--<a href="javascript:;">客服邮箱</a>-->
                    </div>
                </div>
            </li>
        </ul>
    </div>
    <div class="slot slot_headerTop02"></div>
</div>