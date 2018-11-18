<!DOCTYPE html>
<html lang="zh-cn">
  <head>
  	<#assign basePath=request.contextPath>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${seo.meteKey}">
    <meta name="description" content="${seo.meteDes}">
    <#if (sys.bsetName)??>
    	<title>${(sys.bsetName)!''}</title>
    	<input type="hidden" id="bsetName" value="${(sys.bsetName)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    <#else>
	    <title>${(seo.mete)!''}</title>
	    <input type="hidden" id="bsetName" value="${(seo.mete)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
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
 <script type="text/javascript" src="${basePath}/js/shoppingcart/jsOperation.js"></script>

</script>
  </head>
  <body  style="background:#fff;">
    
    
      <div class="error_page">
      <div class="text-center"><img width="30%" alt="" src="${basePath}/images/201305122023451952.gif"></div>
      <h4>${message!""}</h4>
      <div class="buy_area container-fluid">
        <div class="row">
          <div class="col-xs-12">
            <a href="${basePath}/main.html" class="btn btn-danger btn-sm btn-block">继续购物</a>
          </div>
        </div>
      </div>
    </div>
	
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/jquery.keleyi.js"></script>
    <script src="${basePath}/js/customer/wxforward.js"></script>
  </body>
   <script>
	  $(function(){
		FastClick.attach(document.body);
		$('.error_page').css({
		  paddingTop: ($(window).height()-$('.error_page').height())/2+'px'
		});
		$(window).resize(function(){
		  $('.error_page').css({
			paddingTop: ($(window).height()-$('.error_page').height())/2+'px'
		  });
		});
	  });
	</script>
  
</html>
