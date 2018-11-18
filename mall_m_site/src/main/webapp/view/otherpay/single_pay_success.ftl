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
   <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
  <body>
	<div class="single_ask">
        <p>${(map.otherPay.orderRemark)!""}</p> 
        <div class="ask_figure">
            <img src="${(map.cust.customerImg)!""}" width="70" height="70"/><br/>
            <div class="ask_talk">${(map.cust.customerUsername)!""}</div>
        </div><!--ask_figure-->
        <div class="down_arrow">
        </div>
    </div><!--single_ask-->
    <div class="pros">
        
        <div class="done clearfix">
            <div class="center">
                	恭喜您，付款成功
            </div>
            
        </div> 
    </div><!--pros-->   
    </div>
    <div class="pay_detail">
        <div class="pay_title">来自${map.order.shippingPerson}的代付订单</div>
        <div class="check_goods">
      <#list map.order.orderGoodsList as orderGoods>
	      <div class="check_goods_item">
	        <div class="img"><a href="${basePath}/item/${orderGoods.goodsProductVo.goodsInfoId}.html"><img alt="" src="<#if orderGoods.goodsProductVo.goodsInfoImgId??>${orderGoods.goodsProductVo.goodsInfoImgId}</#if>"></a></div>
	        <div class="word">
	          <p><a href="${basePath}/item/${orderGoods.goodsProductVo.goodsInfoId}.html">${orderGoods.goodsProductVo.goodsInfoName}</a></p>
	        </div>
	        <div class="price">
	          <p>￥${(orderGoods.goodsInfoPrice)?string("0.00")}</p>
	          <p>×${orderGoods.goodsInfoNum}</p>
	        </div>
	      </div>
	   </#list>
      <div class="total_price">
        <span>商品总价：</span>
        <p>￥${(map.order.orderPrice)?string("0.00")}</p>
      </div>
    </div>
    </div><!--pay_detail-->
    <#if map.otherPay??>
    <div class="hot_heart">
        <img src="${(map.otherPay.customerImg)!""} width="46" height="46"/>
        <div class="hot_reply">
            <div class="reply_detail">
                <p><span><#if map.otherPay.orderPayName??&&map.otherPay.orderPayName!="">${map.otherPay.orderPayName}<#else>请叫我好心人</#if></span> 说：<span class="fr">10-11 11:45</span></p>
                <p style="border-bottom:1px solid #dfdfdf"> ${(map.otherPay.orderPayRemark)!""}</p>
                <p><span class="col6"></span>代付：<span class="yellow">￥${map.order.orderPrice}</span></p>
            </div>
            
        </div> <!--hot_replay-->
    </div><!--hot_heart-->
    </#if>
    
    <div class="foot">
      <p>已经没有更多了</p>
    </div>
     <div class="other_link">
        <a href="${basePath}/main.html">商城主页</a>
        <a href="${basePath}/customer/index.html">会员中心</a>
        <a href="###">联系我们</a>
    </div>
   <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->

    
    <div class="share_tips" onClick="shutTips('.share_tips')"></div>
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