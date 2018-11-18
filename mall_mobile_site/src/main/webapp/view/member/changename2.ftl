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
    <dt>设置姓名</dt>
    <dd>
      <input type="text" value="${customer.infoRealname!''}" class="text">
    </dd>
  </dl>
  <p class="help_block" style="display:none;" id="nickName_msg"></p>
  <p class="help_block">姓名由中文组成</p>
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
        var reg = new RegExp("^([\\u4E00-\\u9FFF])+$", "g");
    //if (nickName.replace(/[^\x00-\xff]/g, "**").length < 4
    //        || nickName.replace(/[^\x00-\xff]/g, "**").length > 20) {
    //    $("#nickName_msg").html("4-20个字符，可由中英文、数字、“_”、“-”组成");
    //       $("#nickName_msg").css("color","red");
    //    $("#nickName_msg").show();
    //    return false;
    //} 
    if (!reg.test(nickName)) {
        $("#nickName_msg").html("姓名格式不正确");
        $("#nickName_msg").css("color","red");
        $("#nickName_msg").show();
        return false;
    }
      window.location.href="${basePath}/savename.html?changeName="+nickName;
    });
</script>
</body>
</html>