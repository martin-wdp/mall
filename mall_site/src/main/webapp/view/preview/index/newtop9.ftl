<#assign basePath=request.contextPath>
<link rel="stylesheet" href="${basePath}/index_nine/css/header.css"/>
<input type="hidden" id="basePath" value="${basePath}"/>
<input type="hidden" id="currentProvince" value="${chProvince!''}" />
<div class="section_headerTop">
            <div class="slot slot_headerTop01">
                <ul class="left">
                    <li><a class="favorite" href="javascript:;" onclick="addToFavorite('${topmap.systembase.bsetName}');"><i class="iconfont">&#xe602;</i>加入收藏</a></li>
                    <li><span class="welcome">您好<#if cust??>${cust.customerNickname!''}</#if>，欢迎来到${topmap.systembase.bsetName}!</span></li>
                    <input type="hidden" id="customer_id" value="<#if cust??>${cust.customerId!''}</#if>" />
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
                    <li class="local-choose">
                        <a href="javascript:;"><span class="province_text"></span><i class="iconfont">&#xe607;</i></a>
                        <div class="localInfo">
                            <div class="localPanel">
                                <#--<a href="javascript:;">北京</a>
                                <a href="javascript:;">上海</a>
                                <a href="javascript:;">天津</a>
                                <a href="javascript:;">重庆</a>
                                <a href="javascript:;">江苏</a>
                                <a href="javascript:;">浙江</a>
                                <a href="javascript:;">广东</a>
                                <a href="javascript:;">安徽</a>-->
                            </div>
                        </div>
                    </li>
                </ul>
                <ul class="siteNav">
                    <#--<li class="nav_item"><a href="javascript:;" onclick="shareUrl()" >分享给好友</a></li>-->
                    <li class="nav_item"><a href="${basePath}/customer/myorder.html">我的订单</a></li>
                    <li class="nav_item navCenter">
                        <a href="${basePath}/customer/index.html">我的${topmap.systembase.bsetName}<i class="iconfont">&#xe607;</i></a>
                        <div class="navInfo">
                            <div class="navPanel">
                                <a href="${basePath}/customer/sellermyfollw.html">收藏的店铺</a>
                                <a href="${basePath}/customer/myfollw.html">收藏的商品</a>
                            </div>
                        </div>
                    </li>
                    <li class="nav_item"><a href="${basePath}/help/2">帮助中心</a></li>
                </ul>
            </div>
            <div class="slot slot_headerTop02"></div>
</div>