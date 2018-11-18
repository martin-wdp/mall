<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>注册成功</title>
    <#assign basePath=request.contextPath>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
    <style>
        .container{width:1200px; margin:0px auto;}
        .p_s_left{width:520px; height:520px;float:left;}
        body,html{background: #f8f8f8;}
        .pt150{ padding-top: 150px;}
        .p_s_rig{width:520px; float:right; padding-top: 0px;}
        .p_s_rig .title{ font-family: "微软雅黑"; font-size: 30px; font-weight: bold; text-align: center; line-height: 40px; color: #5d5d5b;}
        .p_s_rig .btn a{ display: block; width: 260px; height: 352px; border-radius:3px; text-align: center; line-height: 60px; float: left; margin-right:0px; }
        .p_s_rig .btn a:hover{ background-color: #EDEDED;}
        .p_s_rig .go_on { color: #666; font-family: "微软雅黑"; font-size: 16px;margin:20px auto 0px;text-align: center;vertical-align: middle;}
        .p_s_rig .go_on a{ color: #ca0928;}
        .dialog {position:fixed; background:#fff; z-index:9999; width:300px!important; min-height:100px!important; padding:0 0 10px!important; border:5px solid #f7f7f7; display:none;}
        .dia_tit {height:30px; line-height:30px; padding:0 20px; font-family:microsoft YaHei; font-size:14px; color:#fff;background: #eb6122;}
        .dia_closes {width:15px; height:15px; background:url(${basePath}/images/dia_close.png) no-repeat; margin-top:7px;}
        .dia_intro{height:auto!important; text-align:left; padding-left:50px; padding-top:20px!important;}
        .info_ok {display:inline-block!important; zoom:1; *display:inline; width:100px; height:28px; text-align:center; line-height:27px; font-family:microsoft YaHei; font-size:14px; color:#fff!important; margin:0 5px;}
        .info_ok {background:url(${basePath}/images/org_btn.gif) no-repeat;}
    </style>
</head>
<body>
<div class="container clearfix pt150">
    <div class="p_s_left">
        <img src="<#if basicSet??&&basicSet.siteRegSuccImg??>${basicSet.siteRegSuccImg}<#else >${basePath}/images/bg_success.jpg</#if>"/>
    </div>
    <div class="p_s_rig">
        <p class="title">恭喜您，注册成功</p>
        <p class="title">你的身份是？</p>
        <div class="btn clearfix">
            <a href="${basePath}/index.html"><img src="${basePath}/images/qpmalenterprise/qp_images/person.png"/></a>
            <a href="${basePath}/toValidateProtocol.htm">
                <img src="${basePath}/images/qpmalenterprise/qp_images/enterprise.png"/>
            </a>
        </div>
        <p class="go_on">温馨提示：注册礼包已发放至您的个人中心，<a href="${basePath}/customer/index.html">点此查看》</a></p>
    </div>
</div>


<div class="dialog promotion_dialog_1">
    <div class="dia_tit clearfix">
        <h3 class="fl info_title">温馨提示</h3>
        <a class="dia_closes fr" href="javascript:;" onclick="clsa()"></a>
    </div><!--/dia_tit-->
    <div class="dia_cont">
        <div class="dia_intro pt30" id="dialog_tip" style="text-align: center;margin-top: 19px; font-size: 14px;padding:20px;min-height:30px;">

        </div>
        <div class="dia_ops mt20 tc dia_btn_ok">
            <a class="info_ok info_tip_cancel" href="javascript:;" onclick="clsa()">确定</a>
        </div><!--/dia_ops-->
    </div><!--/dia_cont-->
</div><!--/dialog-->
<div class="mask"></div><!--/mask-->
<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script>
    function showaDialog(registerIntegral,couponId){
        $(".mask").fadeIn();
        $(".promotion_dialog_1").fadeIn();
        if(registerIntegral == 0 && couponId != 0) {
            $("#dialog_tip").html("恭喜您获得一张优惠券！");
        }
        if(registerIntegral != 0 && couponId == 0) {
            $("#dialog_tip").html("恭喜您获得"+registerIntegral+"积分！");
        }
        if(registerIntegral != 0 && couponId != 0) {
            $("#dialog_tip").html("恭喜您获得"+registerIntegral+"积分和一张优惠券！");
        }
    }

    function clsa(){
        $(".dialog").fadeOut();
        $(".mask").fadeOut();
    }
</script>
</body>
</html>