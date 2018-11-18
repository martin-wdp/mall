<!DOCTYPE HTML>
<head>
<#assign basePath = request.contextPath>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=0.9,user-scalable=no"/>
    <meta name="MobileOptimized" content="320">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <#if (sys.bsetName)??>
        <title>登录-${(sys.bsetName)!''}</title>
    <#else>
        <title>登录-${(seo.mete)!''}</title>
    </#if>
    <link rel="stylesheet" href="${basePath}/css/login.css">
    <link rel="stylesheet" href="${basePath}/css/root.css">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <style>
        ul.keleyi-menu >li a{font-size:14px;}
        ul.keleyi-menu >li a img{margin-top:-4px;}
    </style>
 <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script> <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
<body>
<input type="hidden" id="basePath" value="${basePath!''}">

<div class="logo">
    <a href="${basePath}/register.html">快速注册</a>
    <img src="${basePath}/images/newimage/logo.png">
</div>
<div class="enroll">
    <img src="${basePath}/images/newimage/linelog.png" class="enr-img">

    <div class="enr-p">
        <form>
            <input type='hidden' id="urlhide" value="${url}"/>

            <p class="must" style="border-bottom: none;" data-mes="请输入手机号"><label><img
                    src="${basePath}/images/newimage/yhu.png"></label>
                <input id="mobile" type="text" placeholder="请输入手机号码"
                        value="<#if MerUser?? && (MerUser.isMerPwd!'0')=="1">${MerUser.qp_username!}</#if>"></p>

            <p class="must" data-mes="请输入密码"><label><img src="${basePath}/images/newimage/mima.png"></label>
                <!--added by ly 16-02-03 记住密码的功能 start-->
            <#if !(MerUser?? && (MerUser.isMerPwd!'0')=="1")><input type="password" value="novalue" style="display:none"></#if>
                <!--added by ly 16-02-03 记住密码的功能 end-->
                <input id="pass"  type="password" value="<#if MerUser?? && (MerUser.isMerPwd!'0')=="1">${MerUser.qp_password!}</#if>" placeholder="请输入密码"  /></p>
        </form>
    </div>
    <p style="display: none;color: #ff0000 ;width: 130px;color: rgb(255, 0, 0);margin: 10px auto;" id="login_err">用户名或密码错误</p>
    <p class="login"><input id="check" <#if MerUser?? && (MerUser.isMerPwd!'0')=="1">checked="checked" </#if> type="checkbox" value="1"><label for="check">记住密码</label><a
            href="${basePath}/customer/toUpdatePwd.htm">忘记密码？</a></p>
    <button id="sub">登录</button>
</div>
<div class="foot">
    <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
</div>
<#include '../common/smart_menu.ftl' />
</body>
<script src="${basePath}/js/customer/jquery-1.11.3.min.js"></script>
<script src="${basePath}/js/customer/public.js"></script>
<script src="${basePath}/js/customer/login.js"></script>
<script src="${basePath}/js/fastclick.min.js"></script>
<script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
<script src="${basePath}/js/jquery.keleyi.js"></script>
<script src="${basePath}/js/customer/wxforward.js"></script>
<script src="${basePath}/js/publicModel.js"></script>
<script>
    $("#me img").attr("src","${basePath}/images/qp_zd.png");
    $("#me a").css("color","#E05D03");
</script>
</html>
