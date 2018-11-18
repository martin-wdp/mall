<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${(seo.meteKey)!''}">
    <meta name="description" content="${(seo.meteDes)!''}">
    <#if (sys.bsetName)??>
    	<title>设置新密码-${(sys.bsetName)!''}</title>
    <#else>
	    <title>设置新密码-${(seo.mete)!''}</title>
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
    
    <div class="login_title">
      <h4>设置新密码</h4>
    </div>
    
    <div class="reg_form wrap">
      <form role="form" method="post">
         <div class="form-group form-group-lg">
          <input type="password" name="userKey" class="form-control" id="pd" placeholder="设置新密码">
          <p class="help-block"></p>
        </div>
        <div class="form-group form-group-lg">
          <input type="password" class="form-control" id="pdt" placeholder="再次输入密码">
          <p class="help-block"></p>
        </div>
        <a class="sub_btn p_next" href="javascript:void(0);">下一步</a>
      </form>
    </div>
    
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
    <script src="${basePath}/js/findpwd/asvalidate.js"></script>
    <script src="${basePath}/js/findpwd/allvalid.js"></script>
    <script>
	  $(function(){
		FastClick.attach(document.body);
		$('.fill_item input').focus(function(){
		  $(this).parent().next().css('borderColor','#EB6122');
		});
		$('.fill_item input').blur(function(){
		  $(this).parent().next().css('borderColor','#EEEEEE');
		});
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
	  });
	</script>
  </body>
</html>
