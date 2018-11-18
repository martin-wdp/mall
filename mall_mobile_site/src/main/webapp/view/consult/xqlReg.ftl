<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
<title>千米云销电商系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="http://top.qianmi.com/static/js/emailpop/emailpop.css"/>
<link rel="stylesheet" href="http://pic.ofcard.com/shop-admin/css/page.css"/>
<style type="text/css">
    body.reg {background: rgba(0, 0, 0, 0);*background: #29a8e2;background: #29a8e2\9;background-color:transparent\9\0;
        margin: 0 auto;height: 80%;width: 18em;font-size: 1em; }
    .reg .tabContent {border: none;padding: 0;}
    .reg .form {margin:0 auto;}
    .reg .form .label-td label {font-size: 0.8em;text-align: left;width: 100%;}
    .reg .form td.label-td { width: 4em; }
    .reg .form #mobile{ width:10em; }
    .reg .form #code{ width:4em; }
    .reg .form .btn{ width:7em;padding:0.4em 0.5em; }
    .reg .tabContent a.btn-reg, .reg .ks a.btn-reg { background-color: #189ef3;-webkit-border-radius: 0.25em;-moz-border-radius: 0.25em;
        border-radius: 0.25em;color: #fff;display: block;font-size: 1em;height: 2.125em;line-height: 2.125em;margin: 1.875em auto;text-align: center;width: 10em; }
    .reg .ks { padding-left: 2.0625em; }
    .reg .ks a.btn-reg.btns{ margin-left:0; }
    .reg .ks .ks-desc { font-size: 0.9375em;margin-top: 2.5em;padding-left: 0em;text-align: left;line-height: 1.4375em; }
    .reg form.ks a.btn-reg { margin-left: 7.8125em;width: 8.4375em; }
    .reg .pop-tit { width: 12em;font-size: 1.375em;text-align: center;margin: 2em auto 0;padding: 0 0 1.25em 0;border-bottom: 1px solid #ddd; }
    .reg .pop-desc { font-size: 1em;margin-top: 1.25em;text-align: left;line-height: 1.4375em; }
</style>
<script src="http://pic.ofcard.com/jslib/jquery/jquery-1.10.2.js"></script>
<script src="http://pic.ofcard.com/jslib/jquery/jquery-1.9.1.min.js"></script>
<script src="http://pic.ofcard.com/jslib/jquery/plugin/tab/jquery.idTabs.min.js"></script
<script type="text/javascript" src="http://pic.ofcard.com/themes/admin/js/jquery.form.js"></script>
<script type="text/javascript"
        src="http://pic.ofcard.com/themes/admin/plugins/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript"
        src="http://pic.ofcard.com/themes/admin/plugins/jquery-validation/jquery.validate.extends.js"></script>
<link rel="stylesheet" type="text/css"
      href="http://pic.ofcard.com/themes/admin/plugins/artDialog/skins/default.css"/>
<script type="text/javascript"
        src="http://pic.ofcard.com/themes/admin/plugins/artDialog/jquery.artDialog.js"></script>
<script type="text/javascript" src="/static/js/pagecommon.js" charset="UTF-8"></script>
<script src="/static/js/emailpop/emailpop.js"></script>

<script type="text/javascript">
    $(function () {
        // var iH = $(window).height();
        var iW = $(window).width();
        $(".reg").css({"font-size":iW/304+"em"});
        bindEvent();
    });

    //绑定事件
    function bindEvent() {
        $("#mobileForm").validate();
        jQuery.validator.addMethod("isMobile", function (value) {
            var length = value.length;
            return (length == 11 && /^((1)+\d{10})$/.test(value));
        }, "<span class='error'><s></s>手机号格式不正确</span>");
        jQuery.validator.addMethod("isVerifyCode", function (value) {
            return (/^[0-9]{6}$/.test(value));
        }, "<span class='error'><s></s>验证码为6位数字</span>");
    }

    var resetTime_mobile = 120;
    // 重发发送短信验证码
    function resetMobileSend() {
        if (resetTime_mobile == 0) {
            $('#resetTime-mobile-a').hide();
            $('#sendMobileBtn').text("重新发送").removeClass("disabled").prop("disabled", false);
            resetTime_mobile = 120;
        } else {
            resetTime_mobile--;
            $("#resetTime-mobile").text(resetTime_mobile);
            $('#resetTime-mobile-a').show();
            setTimeout(function () {
                resetMobileSend()
            }, 1000);
        }
    }

    // 发送手机验证码
    function sendMobileCode() {
        var mobile = $('#mobile').val();
        if (mobile == '') {
            art.dialog.tips('请输入手机号码');
            return;
        }
        var reg = /^((1)+\d{10})$/;
        if (mobile.length == 11 && reg.test(mobile)) {
            $.ajax({
                url: 'http://top.qianmi.com/register/potential/sendMobileCode?mobile=' + mobile,
                type: 'GET',
                dataType: 'json',
                success: function (json) {
                    if (json.result == 'ok') {
                        // 邮件发送成功页面
                        $("#sendMobileBtn").text("发送成功").prop("disabled", true);
                        resetMobileSend();
                    } else {
                        art.dialog.tips(json.msg);
                    }
                }
            });
        } else {
            art.dialog.tips('请输入正确的手机号码');
        }
    }
    // 验证验证码，成功则跳转到注册详情
    function verifyMobile() {
        var mobile = $('#mobile').val();
        if ($("#mobileForm").valid()) {
            $.ajax({
                url: 'http://top.qianmi.com/register/potential/verifyMobile',
                data: {
                    realName: $('#realName').val(),
                    mobile: mobile,
                    code: $('#code').val(),
                    regway: ''
                },
                type: 'POST',
                dataType: 'json',
                success: function (json) {
                    if (json.result == 'ok') {
                        
                        $('#content').hide();
                        $('#applyContent').show();
                        
                    } else {
                        art.dialog.tips(json.msg);
                    }
                }
            });
        }
    }
</script>
</head>
<body class="reg">
<div id="content">
    <h3 class="pop-tit">免费申请</h3>
    <form action="" onsubmit="return false;" id="mobileForm" method="get">
        <div class="tabContent" id="reg01">
            <table class="form">
                <tr>
                    <td class="label-td"><label for="">手&nbsp;机&nbsp;号&nbsp;码</label></td>
                    <td class="with-hint">
                        <input type="text" name="mobile" id="mobile"
                               data-rule-required="true" data-msg-required="手机号码不能为空" data-rule-isMobile="true"
                               placeholder="填写您的手机号码"/>
                    </td>
                </tr>
                <tr>
                    <td class="label-td"><label for="">短信验证码</label></td>
                    <td class="with-hint">
                        <input type="text" name="code" id="code" class="input-small"
                               data-rule-required="true" data-msg-required="验证码不能为空"
                               data-rule-isVerifyCode="true"
                                />
                        &nbsp;
                        <button id="sendMobileBtn" onclick="sendMobileCode()" type="button" class="btn">获取验证码</button>
                        <a class="btn disabled" href="javascript:void(0)" id="resetTime-mobile-a" style="display: none"><b
                                id="resetTime-mobile">120</b>秒后重新发送</a></li>
                    </td>
                </tr>
            </table>
            <a href="javascript:void(0)" onclick="verifyMobile()" class="btn-reg">立即注册</a>
        </div>
    </form>
</div>
<div id="applyContent" class="ks" style="display: none">
    <h3 class="pop-tit">申请成功</h3>
    <div class="ks-desc">我司会在24小时内联系您，为您服务，请耐心等待<br/>
        如有疑问，也可直接拨打我司025-68586888-2电话进行免费咨询。
    </div>
</div>
<div id="qpmallContent" class="ks" style="display: none">
    <h3 class="pop-tit">qpmall产品演示</h3>
    <div class="ks-desc">请点击演示版观看<br/>
        如有疑问，也可直接拨打我司025-68586888-2电话进行免费咨询。
    </div>
    <a href="http://qpmall.qianmi.com" class="btn-reg" target="_balnk">前台演示版</a>
    <a href="http://qpmall.qianmi.com/boss/" class="btn-reg" target="_balnk">后台演示版</a>
    </div>
<div id="qianshanghuiContent" class="ks" style="display: none">
    <h3 class="pop-tit">千商汇产品演示</h3>
    <div class="ks-desc">请点击演示版观看<br/>
        如有疑问，也可直接拨打我司025-68586888-2电话进行免费咨询。
    </div>
    <a href="http://store140031.b2c.1000.com/" class="btn-reg" target="_balnk">前台演示版</a>
</div>
<div id="xiaoqianliContent" class="ks" style="display: none">
    <h3 class="pop-tit">销千里产品演示</h3>
    <div class="ks-desc">点击演示版观看，也可直接拨打我司025-68586888-2电话进行免费咨询。
    </div>
    <a href="http://xiaoqianli.qianmi.com" class="btn-reg" target="_balnk">前台演示版</a>
    <a href="http://store140031.b2c.1000.com/" class="btn-reg" target="_balnk">母婴站体验</a>
</div>
<div id="weidianbangContent" class="ks" style="display: none">
    <h3 class="pop-tit">微店帮产品演示</h3>
    <div class="ks-desc">立即拿起手机扫一扫，体验微店帮。
    </div>
    <img src="http://pic.ofcard.com/qmyx/images/wdb_cord.jpg" alt="微店帮"/>
</div>

</body>
</html>