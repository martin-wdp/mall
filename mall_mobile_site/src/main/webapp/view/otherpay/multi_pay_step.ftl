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

</script>
  </head>
  <body>
	<div class="single_ask">
        <p>${(map.orderSchedule.orderRemark)!""}</p> 
        <div class="ask_figure">
            <img src="${(map.cust.customerImg)!""}" width="70" height="70"/><br/>
            <div class="ask_talk">${(map.cust.customerUsername)!""}</div>
        </div><!--ask_figure-->
        <div class="down_arrow">
            <div class="arrow-down"></div>
        </div> 
    </div><!--single_ask-->

    <div class="pros">
        <div class="progress">
         <div class="progress-bar" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="100" style="width: ${(1-(map.orderSchedule.orderResiduePrice/map.order.orderPrice)?number)*100}%;">${(1-(map.orderSchedule.orderResiduePrice/map.order.orderPrice)?number)*100}%
        </div>
        
    </div>
    <div class="done clearfix">
            <div class="already fl">
                已完成：<span>${(1-(map.orderSchedule.orderResiduePrice/map.order.orderPrice)?number)*100}%</span>
            </div>
            <div class="already fr">
                还差：<span>${(map.orderSchedule.orderResiduePrice)?string("0.00")}元</span>
            </div>
        </div>
    </div><!--pros-->
    <div class="pay_area" style="margin-top:2em;">
      <a href="javascript:;" class="btn-success  btn-block" role="button" onClick="showTips('.share_tips')">找小伙伴帮忙付款</a>
    </div>
    
    

     <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->
    <div class="share_tips" onClick="shutTips('.share_tips')">
        
    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/jquery.lazyload.js"></script>
    <script src="${basePath}/js/share.js"></script>
    <script>
      function showTips(pid){
        $(pid).show().height($('body').height()).css('backgroundPositionY',$(window).scrollTop() + 10 + 'px');
      }
      function shutTips(pid){
        $(pid).hide();
      }
    </script>
</body>
</html>