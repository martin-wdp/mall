<#include "../include/common.ftl">
<@htmlHead title='注册-${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/jd.base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/jd.style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css"/>
<style>
    /* 弹窗 */
    .mask {
        width: 100%;
        height: 100%;
        background: #000;
        opacity: 0.5;
        filter: alpha(opacity=50);
        position: fixed;
        top: 0;
        left: 0;
        z-index: 9998;
        display: none;
    }
    .dialog {
        position: fixed;
        background: #fff;
        z-index: 9999;
        width: 440px;
        min-height: 230px;
        padding: 5px;
        display: none;
    }
    .dia_tit {
        height: 30px;
        line-height: 30px;
        padding: 0 20px;
        font-family: microsoft YaHei;
        font-size: 14px;
        color: #fff;
    }
    .dia_close {
        width: 15px;
        height: 15px;
        background: url(${basePath}/images/dia_close.png) no-repeat;
        margin-top: 7px;
    }
    .dia_intro em {
        display: inline-block;
        font-family: microsoft YaHei;
        font-size: 18px;
        color: #575757;
        -webkit-transform: rotate(-10deg);
        -moz-transform: rotate(-10deg);
    }
    .go_shopping, .go_pay {
        display: inline-block;
        zoom: 1;
        *display: inline;
        width: 100px;
        height: 28px;
        text-align: center;
        line-height: 27px;
        font-family: microsoft YaHei;
        font-size: 14px;
        color: #fff !important;
        margin: 0 5px;
    }
    .go_shopping {
        background: url(${basePath}/images/grey_btn.gif) no-repeat;
    }
    .go_pay {
        background: url(${basePath}/images/org_btn.gif) no-repeat;
    }
    .agreement_dia {
        width: 910px;
        height: 490px;
        border: 5px solid rgba(238, 238, 238, .5);
        padding: 0;
    }
    .agreement_dia .dia_tit {
        background: #eee;
        text-align: center;
        font-size: 14px;
        font-weight: 700;
        color: #666;
    }
    .agreement_dia .dia_close {
        position: absolute;
        top: 8px;
        right: 20px;
        margin-top: 0;
        background: url(${basePath}/images/agree_close.gif) no-repeat;
    }
    .agreement_wp {
        height: 360px;
        overflow-y: scroll;
        padding: 0 20px;
    }
    .agreement_wp h4 {
        font-weight: 700;
        line-height: 180%;
    }
    .agreement_wp p {
        line-height: 180%;
    }
    .agree_btn {
        display: inline-block;
        zoom: 1;
        *display: inline;
        width: 200px;
        height: 30px;
        line-height: 29px;
        background: url(${basePath}/images/agree_btn.gif) no-repeat;
        font-family: microsoft YaHei;
        font-size: 16px;
        color: #fff !important;
    }
    .bluee {
        color: #005aa0;
    }
    #login_name {
        width: 18px;
        height: 18px;
        background: url(${basePath}/images/user.png) no-repeat;
        position: absolute;
        top: 10px;
        left: 10px;
    }
    #login_code {
        width: 18px;
        height: 22px;
        background: url(${basePath}/images/code.png) no-repeat;
        position: absolute;
        top: 8px;
        left: 10px;
    }
    input:-webkit-autofill {
        -webkit-box-shadow: 0 0 0px 1000px white inset;
        -webkit-text-fill-color: #333;
    }
    .n_rg {
        position: absolute;
        width: 74px;
        height: 38px;
        line-height: 38px;
        top: 0px;
        left: 10px;
        color: #666;
        font-size: 14px;
    }
    .n_row .n_text {
        width: 100%;
        height: 36px;
        border: 1px solid #d3d3d3;
        border-radius: 3px;
        line-height: 36px;
        text-indent: 74px;
        *width: 250px;
        *text-indent: 0;
    }
    .n_row .form_tips {
        display: none;
        position: absolute;
        left: 0;
        top: 41px;
        height: 16px;
        line-height: 16px;
        padding-left: 25px;
        background: url(${basePath}/images/tips_icon.png) no-repeat left top;
        color: #969696;
    }
    .sendbutton {
        background-color: #a6a6a6;
        border-radius: 5px;
        color: #fff;
        text-align: center;
        line-height: 40px;
        font-size: 16px;
        border-radius: 3px;
        border: none;
        display: block;
        font-family: "微软雅黑";
        cursor: pointer;
        width:100px;
    }
</style>
<script type="text/javascript" src="${basePath}/js/register.js"></script>
</@htmlHead>
<@htmlBody>
<div class="top-container">
    <div class="head2">
        <a href="${topmap.systembase.bsetAddress}"><img id="logo_pic" alt="" src="" style="height:45px;width:auto;"></a>
        <h1>欢迎注册</h1>
    </div>
</div>
<div class="n_login_bg"
     style=" background-position:top center; background-repeat: no-repeat;height:480px; background-size:100% 100%;">
    <div class="container" style="width:900px;">
        <div class="new_login clearfix" style="height:426px;">
            <div class="new_login_con" style="height:386px;">
                <div class="n_title clearfix">
                    <span>会员注册</span>

                    <p>已有帐号在此<a href="${basePath}/login.html">登录</a></p>
                </div>
                <form action="${basePath}/addcustomer.html" method="post" id="userform">
                    <input type="hidden" name="cusId" id="cusId" value=""/>

                    <div class="mt20">
                        <div class="n_row" >
                            <input type="hidden" value="" name="username" class="hi_name"/>
                            <input type="text" maxLength="20" name="customerUsername" class="n_text user_chk"
                                   id="act_user" autocomplete="off"/>
                            <span class="n_rg">用户名：</span>

                            <div class="form_tips">请填写手机号码</div>
                            <div class="n_tips">您输入的用户名不正确</div>

                        </div>

                        <div class="n_row">
                            <#--<input type="hidden" value="" name="password" class="hi_pwd"/>-->
                            <input type="password" style="display:none">
                            <input type="password" maxLength="20" name="customerPassword" class="n_text psd_chk"
                                   id="set_psd" autocomplete="off"/>
                            <span class="n_rg">设置密码：</span>

                            <div class="form_tips">请填写6-20位数字和英文字母组合的密码</div>
                            <div class="n_tips">您输入的密码不正确</div>
                        </div>
                        <div class="n_row">
                            <input type="password" maxLength="20" name="psdConfirm" class="n_text psd_conf"
                                   id="conf_psd" autocomplete="off"/>
                            <span class="n_rg">确认密码：</span>

                            <div class="form_tips">请再次输入密码</div>
                            <div class="n_tips">您输入的密码不正确</div>
                        </div>
                        <div class="n_row" style="height: 40px; line-height: 40px;">
                            <div style="float: left;width:170px;">
                                <input type="text" style="width:170px;" name="varification" class="n_text code_text" id="varification" onfocus="getPatcha()"/>
                                <span class="n_rg">验证码：</span>
                                <div class="form_tips form_tips">请输入正确的手机验证码</div>
                                <div class="n_tips code_error"></div>
                            </div>
                            <div style="float: right;">
                                <input class="sendbutton" type="button" name="button" id="sendPost" disabled="true"
                                       value="获取验证码" onclick="sendMessage()" style="width:115px;"/>
                            </div>
                        </div>
                        <div class="n_row clearfix">

                            <input type="checkbox" checked="checked" name="remember" class="re" id="readme"
                                   onclick="agreeonProtocol();">
                            <label>&nbsp;&nbsp;我已阅读并同意<a href="javascript:void(0)" onclick="showpro();"
                                                         class="bluee"
                                                         id="protocol">《商城用户注册协议》</a></label>

                            <div id="protocol_error" class="n_tips" style="position:static;">请接受服务条款</div>
                        </div>
                        <div class="n_row">
                            <input id="register" type="button" class="n_btn" onclick="reg()" data-num="0" value="同意并注册"/>
                        </div>

                    </div>
                    <!--mt20-->
                </form>
            </div>
        </div>
    </div>
</div>

<#--引入底部 <#include "/index/bottom.ftl" /> -->
<#if (topmap.temp)??>
    <#if (topmap.temp.tempId==1)>
        <#include "../index/bottom.ftl">
    <#else>
        <#include "../index/newbottom.ftl" />
    </#if>
</#if>

<div class="mask" style="display:none"></div>
<div class="dialog dia1 agreement_dia" style="display:none">
    <div class="dia_tit">
    ${(topmap.systembase.bsetName)!''}用户协议
        <a class="dia_close" href="javascript:;" onclick="cls()"></a>
    </div>
    <!--/dia_tit-->
    <div class="dia_cont">
        <div class="agreement_wp mt15">
        <#if (topmap.systembase.bsetUseragreement)??>
        ${(topmap.systembase.bsetUseragreement)!''}
        </#if>
        </div>
        <!--/agreement_wp-->
        <div class="mt20 tc"><a class="agree_btn" href="javascript:;" onclick="agreeonProtocol1();">同意并继续</a></div>
    </div>
    <!--/dia_cont-->
</div>
<!--/agreement_dia-->

<div class="dialog dia2  agreement_dia" style=" width: 350px; height:20px; display:none">
    <div class="dia_tit">
        信息提示
    </div>
    <div style=" color: red; padding-left:30px; padding-top: 30px; font-size:12px;">
        <span>亲！此链接已经失效，您的好友将无法得到积分哦！</span><br/><br/>
        <span>是否返回正常注册页面？</span>
    </div>
    <div class="mt20 tc" style=" padding-top: 35px;">
        <a style="width: 60px" class="agree_btn" href="javascript:;" onclick="registerUrl();">确定</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a style="width: 60px" class="agree_btn" href="javascript:;" onclick="cls();">取消</a>
    </div>
    <!--/dia_cont-->
</div>
<!--/agreement_dia-->
<script type="text/javascript">
    $(document).ready(function () {
//         var random_bg=Math.floor(Math.random()*5+1);
//         var bg='url(images/rebg_'+random_bg+'.jpg)';
//         $(".n_login_bg").css("background-image",bg);
        $.ajaxSetup({cache: false});
        $.ajax({
            url: 'loadlogo.htm',
            success: function (data) {
                $(".n_login_bg").css("background-image", 'url(' + data.logo.siteRegImg + ')');
                //$("#loginImg").prop("src",data.logo.siteLoginImg);
            }
        });
    });
    $(".n_text").each(function () {
        $(this).focus(function () {
            $(this).next().next(".form_tips").show();
        });
        $(this).blur(function () {
            $(this).next().next(".form_tips").hide();
        })
    })
</script>
<script>
    document.onkeydown = function (event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 13) { // enter 键
            reg();
        }
    };
    $.ajax({
        url: 'loadlogo.htm',
        success: function (data) {
            $("#logo_pic").prop("src", data.logo.bsetLogo);
        }
    });
    $(function () {
        win();
        $(window).resize(function () {
            win();
        });
    });
    function win() {
        var _wd = $(window).width();
        var _hd = $(window).height();
        $(".dialog").css("top", (_hd - $(".dialog").height()) / 2).css("left", (_wd - $(".dialog").width()) / 2);
    }
    function dia(n) {
        $(".mask").fadeIn();
        $(".dia" + n).fadeIn();
    }
    function cls() {
        $(".dialog").fadeOut();
        $(".mask").fadeOut();
    }
    function showpro() {
        dia(1);
    }
    function agreeonProtocol1() {
        $("#readme").prop("checked", true);
        cls();
    }
    //add by luyong
    function sendMessage() {
        //获得手机号码
        var phone = $("#act_user").val();//手机号码
        //alert(phone);
        //校验手机号码
        if (phone == "") {
            alert("手机号码不能为空！");
            return;
        }
        if (!checkMobileNew(phone)) {
            alert("手机号码格式错误！");
            return;
        }
        var send_obj = $("#sendPost");
        send_obj.attr('disabled', "disabled");
        //后台请求发送验证码--得到返回值 0--短信发送失败  1--短信发送成功  -1 --手机格式不对  -2 其他原因失败
        var result = "失败";
        $.ajax({
            url: "newRegisterSendcode.htm?mobile=" + phone,
            context: document.body,
            success: function (data) {
                if (data = 1) {
                    //当后台的返回结果为1的时候给出短信发送成功的提示
                    result = "成功";
                }
                start_sms_button(send_obj, result);
            }
        });
    }
    /*   //手机号码正则验证函数
       function checkMobileNew(mobilePhone) {
           var sMobile = mobilePhone;
           if (!(/^(1[358]\\d{9}|145\\d{8})$/.test(sMobile.trim()))) {
               return false;
           }
       }*/
    //延迟60秒钟再进行发送验证码的操作
    //但是在刷新浏览器后就会刷新该按钮的延迟效果
    function start_sms_button(obj, result) {
        var count = 1;
        var sum = 60;
        var i = setInterval(function () {
            if (count > 60) {
                obj.attr('disabled', false);
                obj.css("font-size", 14);
                document.getElementById("sendPost").style.backgroundColor = "#1592ed";
                obj.val('发送验证码');
                clearInterval(i);
            } else {
                obj.css("font-size", 14);
                document.getElementById("sendPost").style.backgroundColor = "#a6a6a6";
                obj.val(result + parseInt(sum - count) + '秒后再发');
            }
            count++;
        }, 1000);
    }
    //add bu luyong
</script>
</@htmlBody>