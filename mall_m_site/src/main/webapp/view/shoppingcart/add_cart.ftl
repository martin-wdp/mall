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
   <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
    <body>
    <#include "../publicHeader2_ftl.ftl" />
    <div class="cart_success">
      <div class="tips"><img alt="" src="images/success_icon.png"><span>商品已成功加入购物车！</span></div>
      <div class="container-fluid">
        <div class="row">
          <div class="col-xs-4">
          	<a href="main.html" class="continu_buy">继续购物</a>
          </div>
          <div class="col-xs-8">
            <a href="myshoppingmcart.htm?tag=3" class="go_cart">去购物车结算&gt;</a>
          </div>
        </div>
      </div>
    </div><!-- /cart_success -->
    <div class="col-xs-12" style="background:#F2F2F2;"><h4 style="height:40px;line-height:40px;font-size:20px;">购买该商品的用户还购买了</h4></div>
    <div class="goods_list rec_goods container-fluid">
      <#list list as goodsProduct>
      <#if goodsProduct??>
      <div class="col-xs-6">
        <a href="${basePath}/item/${goodsProduct.goodsInfoId}.html">
          	<img class="lazy" alt=""  data-original="<#if goodsProduct.goodsInfoImgId??>${goodsProduct.goodsInfoImgId}</#if>">
          <noscript><img alt=""  title="${goodsProduct.goodsInfoName}" alt="${goodsProduct.goodsInfoName}" src="<#if goodsProduct.goodsInfoImgId??>${goodsProduct.goodsInfoImgId}</#if>"></noscript>
          <span>${goodsProduct.goodsInfoName}</span>
          <span>￥ ${goodsProduct.goodsInfoPreferPrice?string("0.00")}</span>
        </a> 
      </div>
      </#if>
      </#list>
    </div><!-- /goods_list -->
    
    <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/fastclick.min.js"></script>
    <script src="js/idangerous.swiper-2.1.min.js"></script>
    <script src="js/jquery.keleyi.js"></script>
    <script src="js/jquery.lazyload.js"></script>
    <script src="${basePath}/js/customer/wxforward.js"></script>
    <script src="${basePath}/js/publicModel.js"></script>
    <script>
	  $(function(){
		$('img.lazy').lazyload({
		  palceholder : 'images/loading.gif',
		  effect : 'fadeIn'
		});
	  });
	</script>
  </body>
</html>
