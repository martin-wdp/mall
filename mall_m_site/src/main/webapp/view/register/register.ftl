<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${(seo.meteKey)!''}">
    <meta name="description" content="${(seo.meteDes)!''}">
    <#if (sys.bsetName)??>
    	<title>注册-${(sys.bsetName)!''}</title>
    <#else>
	    <title>注册-${(seo.mete)!''}</title>
    </#if>
	<#assign basePath=request.contextPath>
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
  <div class="pc"><#include "../publicHeader2_ftl.ftl"/></div>
    <div class="reg_form wrap">
      <form role="form" method="post">
        <div class="form-group">
          <div class="input-group input-group-lg">
            <input type="number" name="customerUsername" class="form-control" id="mob" placeholder="手机号码">
            <span class="input-group-btn">
              <button class="btn btn-warning" id="mc_btn" onClick="return false;">获取验证码</button>
            </span>
          </div>
          <p class="help-block"></p>
        </div>

        <div class="form-group form-group-lg">
          <input type="text" class="form-control" name="code" id="mc" placeholder="短信验证码">
          <p class="help-block"></p>
        </div>

        <div class="form-group form-group-lg">
          <input type="password" name="customerPassword" class="form-control" id="pd" placeholder="设置密码">
          <p class="help-block"></p>
        </div>

        <div class="form-group form-group-lg">
          <input type="password" class="form-control" id="pdt" placeholder="再次输入密码">
          <p class="help-block"></p>
        </div>
          <p class="light"><input id="check" type="checkbox" checked /><label for="check">同意<a class="orange"  onclick="xieyi()">京华亿家商城用户协议</a></label></p>
        <a class="sub_btn reg" href="javascript:void(0);">注册</a>
      </form>
    </div>
    
    <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/jquery.keleyi.js"></script>
    <script src="${basePath}/js/customer/asvalidate.js"></script>
    <script src="${basePath}/js/customer/allvalid.js"></script>
    <script src="${basePath}/js/publicModel.js"></script>
    <script>
        //注册协议
        function xieyi(){
            window.location.href='getXieYi.htm';
        }
	  document.onkeydown = function(event) {
		var e = event || window.event || arguments.callee.caller.arguments[0];
		if (e && e.keyCode == 13) { // enter 键
			$(".reg").click();
		}
	 };
	</script>
  </body>
</html>
