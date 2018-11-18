<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${(seo.meteKey)!''}">
    <meta name="description" content="${(seo.meteDes)!''}">
      <!-- Bootstrap -->
  <#assign basePath=request.contextPath>
      <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
      <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
      <link href="${basePath}/css/style.css" rel="stylesheet">
      <script src="${basePath}/js/jquery.js"></script>
      <script src="${basePath}/js/jquery.keleyi.js"></script>
      <style>
          #keleyi-menu {display:none;}
      </style>
    <#if (sys.bsetName)??>
    	<title>优惠券领取结果-${(sys.bsetName)!''}</title>
    	<input type="hidden" id="bsetName" value="${(sys.bsetName)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    <#else>
	    <title>优惠券领取结果-${(seo.mete)!''}</title>
	    <input type="hidden" id="bsetName" value="${(seo.mete)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    </#if>


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]> 
      <script src="${basePath}/js/html5shiv.min.js"></script>
      <script src="${basePath}/js/respond.min.js"></script>
    <![endif]-->
   <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script> <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
  <body>
  <#include "../publicHeader2_ftl.ftl" />

<p style="font-size:20px;text-align:center;margin-top:20px;"><#if count??&&count==1>领取成功<#else>已领完</#if></p>
  <br/>
  <div class="btnDiv" style="padding:0 10%;">
  <a href="${basePath}/customer/coupon.html" style="float:left;">去我的优惠券</a>
  <a href="${basePath}" style="float:right;">返回首页</a>
      </div>


    </div><!-- /order_list -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/customer/wxforward.js"></script>
    <script src="${basePath}/js/publicModel.js"></script>
  </body>
</html>
