<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>
  <meta charset="UTF-8">
  <title>会员中心 - 我的账户</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta content="telephone=no" name="format-detection">
  <link rel="stylesheet" href="${basePath}/css/style.css"/>
  <script src="http://pic.ofcard.com/astore/wei-store/js/jquery-1.10.1.js"></script>
  <script src="http://pic.ofcard.com/astore/wei-store/js/idangerous.swiper.js"></script>
</head>
<body>

<div class="member_form">
  <dl>
    <dt>设置昵称</dt>
    <dd>
      <input type="text" value="${customer.customerNickname!''}" class="text">
    </dd>
  </dl>
  <p class="help_block" style="display:none;" id="nickName_msg"></p>
  <p class="help_block">昵称由中文、英文、数字、-和_组成</p>
</div>

<div class="p10">
  <a href="#" class="btn btn-full">保存</a>
</div>

<script>
    $(".text").change(function(){
        $("#nickName_msg").html("");
        $("#nickName_msg").hide();
    });
    $(".btn-full").click(function(){
        var nickName = $(".text").val();
        var reg = new RegExp("^([a-zA-Z0-9_-]|[\\u4E00-\\u9FFF])+$", "g");
    var reg_number = /^[0-9]+$/; // 判断是否为数字的正则表达式
    if (reg_number.test(nickName)) {
        $("#nickName_msg").html("昵称不能设置为手机号等纯数字格式，请您更换哦^^");
        $("#nickName_msg").css("color","red");
        $("#nickName_msg").show();
        return false;
    } 
    //else if (nickName.replace(/[^\x00-\xff]/g, "**").length < 4
    //        || nickName.replace(/[^\x00-\xff]/g, "**").length > 20) {
    //    $("#nickName_msg").html("4-20个字符，可由中英文、数字、“_”、“-”组成");
    //       $("#nickName_msg").css("color","red");
    //    $("#nickName_msg").show();
    //    return false;
    //} 
    else if (!reg.test(nickName)) {
        $("#nickName_msg").html("昵称格式不正确");
        $("#nickName_msg").css("color","red");
        $("#nickName_msg").show();
        return false;
    }
      window.location.href="${basePath}/savenickname.html?changeName="+nickName;
    });
</script>
</body>
</html>