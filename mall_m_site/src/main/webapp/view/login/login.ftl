<!DOCTYPE html>
<html lang="zh-cn">
  <head>
  	<#assign basePath=request.contextPath>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${(seo.meteKey)!''}">
    <meta name="description" content="${(seo.meteDes)!''}">
    <#if (sys.bsetName)??>
    	<title>登录-${(sys.bsetName)!''}</title>
    <#else>
	    <title>登录-${(seo.mete)!''}</title>
    </#if>

    <!-- Bootstrap -->
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]> 
      <script src="${basePath}/js/html5shiv.min.js"></script>
      <script src="${basePath}/js/respond.min.js"></script>
    <![endif]-->
   <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
  <body>

 <div class="pc"> <#include "../publicHeader2_ftl.ftl"/></div>
    
    <div class="login_box">
      <div class="login_form">
        <form role="form">
          <div class="form-group form-group-lg">
            <input type="text" class="form-control" id="log_user" placeholder="手机号码">
            <p class="help-block" id="lu_err"></p>
          </div>
          <input type='hidden' id="urlhide" value="${url}" />
          <div class="form-group form-group-lg">
            <input type="password" class="form-control" id="log_psd" placeholder="密码" oncopy="return false;" oncut="return false;" onpaste="return false">
            <p class="help-block" id="pwd_err"></p>
          </div>
        </form>
      </div>
      <a class="sub_btn" href="javascript:void(0)" onclick="login()">登录</a><#--member_main.html-->
      <p class="text-right light">
        <a class="light" href="${basePath}/register.html">立即注册</a>
        <span>|</span>
        <a class="light" href="${basePath}/valididentity.html">忘记密码？</a>
      </p>
    </div><!-- /login_box -->
    
    <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->
    
    <#include '/common/smart_menu.ftl' />

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/jquery.keleyi.js"></script>
    <script src="${basePath}/js/login/login.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/publicModel.js"></script>
  <script>
      $("#me img").attr("src","${basePath}/images/qp_zd.png");
      $("#me a").css("color","#E05D03");
  </script>
  </body>
</html>
