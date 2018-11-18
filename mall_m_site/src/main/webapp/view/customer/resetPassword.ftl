<!DOCTYPE HTML>
<html>
<head>
<#assign basePath=request.contextPath>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=0.9,user-scalable=no"/>
    <meta name="MobileOptimized" content="320">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/resetPassword.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/top.css">
    <link rel="stylesheet" href="${basePath}/css/suppliers.css">

 <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
<body>
<#include "../publicHeader2_ftl.ftl" />
    <div class="pas">
        <p class="pinp must" data-mes="请输入用户名"><label>用户名：</label><input id="mobileInfo" type="text" maxlength="11"
                                                                         value="<#if cust??>${cust.customerUsername}</#if>"></p>

        <p class="pinp must" data-mes="请输入短信验证码" style="border:none;"><label>短信验证：</label><input id="capform"
                                                                                                 maxlength="6"
                                                                                                 type="text"
                                                                                                 placeholder="填写验证码"
                                                                                                 style="width:120px;"><a
                id="code" sendtype="2">获取验证码</a></p>
    </div>
    <div class="pas">
        <p class="pinp must" data-mes="请输入新密码"><label>输入新密码：</label><input id="r_pass" type="password" maxlength="12"
                                                                           placeholder="6-12位字母数字下划线组合"></p>

        <p class="pinp must" data-mes="请输入确认密码" style="border:none;"><label>确认密码：</label><input id="r_pass2"
                                                                                                type="password"
                                                                                                maxlength="12"
                                                                                                placeholder="再输一次吧"></p>
    </div>
<button id="sub" class="mmbtn">提交</button>
    <div class="prompt updatetrue dis">
        <div class="box">
            <img src="${basePath}/images/qp_cxcg.png"><br>
            <h3>恭喜您成功修改密码</h3>
            <p>请用新密码进行登录并牢记您的新密码</p>
            <span><i id="time">2</i>s后自动跳转登录页面</span>
        </div>
    </div>
    <div class="prompt updatefalse dis">
        <div class="box">
            <img src="${basePath}/images/qp_cxcg0.png"><br>
            <h3>修改密码失败</h3>
            <p>~~~~</p>
            <span><i id="timeNo">2</i>s后自动隐藏</span>
        </div>
    </div>

</body>
<script src="${basePath}/js/customer/jquery-1.11.3.min.js"></script>
<script src="${basePath}/js/customer/public.js"></script>
<script src="${basePath}/js/customer/resetPassword.js"></script>
<script>
    $(function(){
        <#if cust??>
        $.ajax({
            type: 'post',
            url: "${basePath}/checkExistCustomerUsername.htm",
            timeout: 3000,
            async: false,
            data: {customerUsername: '${cust.customerUsername}'},
            dataType: 'json',
            success: function (data) {
                if(data=="1"){
                    $("#code").css("background", "#f6ab00").bind("click", Form.Captcha);
                    $("#mobileInfo").parents(".must").attr("num", "1");
                }
            },
            error: function () {
            }
        });
        </#if>

    });

</script>
</html>
