<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${(seo.meteKey)!''}">
    <meta name="description" content="${(seo.meteDes)!''}">
    <#if (sys.bsetName)??>
    	<title>个人中心-${(sys.bsetName)!''}</title>
    	<input type="hidden" id="bsetName" value="${(sys.bsetName)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    <#else>
	    <title>个人中心-${(seo.mete)!''}</title>
	    <input type="hidden" id="bsetName" value="${(seo.mete)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    </#if>

	<#assign basePath=request.contextPath>
    <!-- Bootstrap -->
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]> 
      <script src="js/html5shiv.min.js"></script>
      <script src="js/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    
    <div class="member_title">
      <img width="100%" alt="" src="${basePath}/images/images_25.jpg">
      <div class="member_name">
        <h3>${(cust.customerNickname)!''}</h3>
      </div>
      <div class="avatar">
        <img alt="" src="${(cust.customerImg)!'${basePath}/images/avatar.jpg'}">
      </div>
    </div><!-- /login_title -->
    
    <div class="member_menu">
      <ul>
        <li>
          <a href="${basePath}/customer/myorder.html">
          	<span class="glyphicon glyphicon-file"></span>
            订单查询
          </a>
        </li>
        <li>
          <a href="${basePath}/customer/myorder-0-1.html">
          	<span class="glyphicon glyphicon-list-alt"></span>
            待付款订单
          </a>
        </li>
        <li>
          <a href="${basePath}/customer/myorder-3-1.html">
          	<span class="glyphicon glyphicon-bookmark"></span>
            已发货物流查询
          </a>
        </li>
      </ul>
    </div><!-- /member_menu -->
    
    <div class="member_menu">
      <ul>
        <li>
          <a href="${basePath}/customer/coupon.html">
          	<span class="glyphicon glyphicon-tags"></span>
            我的优惠券
          </a>
        </li>
        <li>
          <a href="${basePath}/addresslist.htm">
          	<span class="glyphicon glyphicon-map-marker"></span>
            收货地址管理
          </a>
        </li>
      </ul>
    </div><!-- /member_menu -->
    <#if !isWx??>
    	 <div style="padding:1em 0.5em">
	       <button type="button" class="btn btn-danger btn-lg btn-block logout">退出登录状态</button>
	     </div>
    </#if>
   
    <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->
    
    <#include '/common/smart_menu.ftl' />
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/jquery.keleyi.js"></script>
    <script src="${basePath}/js/customer/wxforward.js"></script>
    <script>
	  $(function(){
		FastClick.attach(document.body);
          $("#keleyi-menu").keleyi({
              width: '100%',
              item_background_color: '#FAFAFA',
              item_background_color_hover: '#FAFAFA',
              item_margin: '0',
              bar_background_color: '#FAFAFA'
          });
          var _t = setTimeout(function(){
              $(".keleyi-menu").show();
          },100)
		$(".logout").click(function(){
			window.location.href="${basePath}/logout.html";
		});
	  });
	</script>
  </body>
</html>
