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

<div class="member_info">
  <dl>
    <dt>用户名</dt>
    <dd>
      <a href="#">
        <span>${cust.customerUsername!''}</span>
      </a>
    </dd>
  </dl>
  <dl>
    <dt>会员级别</dt>
    <dd>
      <a href="#">
        <span class="label">${cust.pointLevelName!''}</span>
      </a>
    </dd>
  </dl>
  <dl>
    <dt>昵称</dt>
    <dd>
      <a href="${basePath}/changenickname.html">
        <span>${cust.customerNickname!''}</span>
        <i class="ion-ios-arrow-right"></i>
      </a>
    </dd>
  </dl>
  <dl>
    <dt>姓名</dt>
    <dd>
      <a href="${basePath}/changename.html">
        <span>${cust.infoRealname!''}</span>
        <i class="ion-ios-arrow-right"></i>
      </a>
    </dd>
  </dl>
  <dl>
    <dt>性别</dt>
    <dd>
      <a href="javascript:;" class="gender_set">
        <span><#if cust.infoGender??>
                <#switch cust.infoGender>
                <#case '0'>
                                                                保密
                <#break>
                <#case '1'>
                                                               男
                <#break>
                <#case '2'>
                                                                女
                <#break>
                <#default>
                </#switch>
              </#if>
        </span>
        <i class="ion-ios-arrow-right"></i>
      </a>
    </dd>
  </dl>
  <dl>
    <dt>手机号码</dt>
    <dd>
      <a href="my_account_phone_validate1.html">
        <span>${cust.infoMobile!''}</span>
        <i class="ion-ios-arrow-right"></i>
      </a>
    </dd>
  </dl>
  <dl>
    <dt>QQ号码</dt>
    <dd>
      <a href="#">
        <span>${cust.infoQQ!''}</span>
        <i class="ion-ios-arrow-right"></i>
      </a>
    </dd>
  </dl>
</div>

<div class="member_info">
  <dl>
    <dt>收货地址管理</dt>
    <dd>
      <a href="${basePath}/addresslist.htm">
        <i class="ion-ios-arrow-right"></i>
      </a>
    </dd>
  </dl>
  <dl>
    <dt>账号安全</dt>
    <dd>
      <a href="my_account_safe.html">
        <i class="ion-ios-arrow-right"></i>
      </a>
    </dd>
  </dl>
  <dl>
    <dt>账号绑定</dt>
    <dd>
      <a href="my_account_phone_validate3.html">
        <i class="ion-ios-arrow-right"></i>
      </a>
    </dd>
  </dl>
</div>

<div class="p10">
  <a href="#" class="btn btn-full" id="loginout">退出当前登录</a>
</div>

<div class="gender_choose" style="display:none;">
  <a href="${basePath}/changegender.html?gender=1" class="btn btn-full-grey">
    <i class="iconfont icon-unie040"></i> 男
  </a>
  <a href="${basePath}/changegender.html?gender=2" class="btn btn-full-grey">
    <i class="iconfont icon-unie041"></i> 女
  </a>
  <a href="javascript:;" class="btn btn-full gender_close">取消</a>
</div>

<script>
  $(function(){

    /* 选择性别 */
    $('.gender_set').click(function(){
      if($('.opacity-bg-3').length == 0){
        $('body').append('<div class="opacity-bg-3"></div>');
        $('.gender_choose').show();
      }
    });
    $('a.gender_close').click(function(){
      $('.opacity-bg-3').remove();
      $('.gender_choose').hide();
    });

  });
  $("#loginout").click(function(){
            window.location.href="${basePath}/logout.html";
        });
</script>
</body>
</html>