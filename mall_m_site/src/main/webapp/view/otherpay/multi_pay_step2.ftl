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
            <div class="progress-bar" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="100" style="width: ${(1-(map.orderSchedule.orderResiduePrice/map.order.orderPrice)?number)*100}%;">
            ${(1-(map.orderSchedule.orderResiduePrice/map.order.orderPrice)?number)*100}%
            </div>
        </div>
        <div class="done clearfix">
            <div class="already fl">
                已完成：<span>${(1-(map.orderSchedule.orderResiduePrice/map.order.orderPrice)?number)*100}%</span>
            </div>
            <div class="already fr">
                还差：<span>${(map.orderSchedule.orderResiduePrice)?string("0.00")}</span>
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
    <#list map.otherPays as pay>
    <div class="hot_heart">
        <img src="images/images_23.jpg" width="46" height="46"/>
        <div class="hot_reply">
            <div class="reply_detail">
                <p><span><#if pay.orderPayName??>${pay.orderPayName}<#else>请叫我好心人</#if></span> 说：<span class="fr">10-11 11:45</span></p>
                <p style="border-bottom:1px solid #dfdfdf">${pay.orderPayRemark}</p>
                <p><span class="col6">(${pay_index+1}楼)</span>代付：<span class="yellow">￥${pay.orderPayPrice}</span></p>
            </div>
            
            <div class="left_a">
                <img src="images/corner.png"/>
            </div>
            
        </div> <!--hot_replay-->
    </div><!--hot_heart-->
    </#list>
    <form action="gomultipay-${map.order.orderId}.html" method="post" class="subForm">
    	<input type="hidden" value="${map.order.orderId}" name="orderId">
    </form>
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

    <div class="pay_foot">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6"><a href="javascript:;" onclick="subForm()" class="btn btn-success btn-lg btn-block">帮TA支付</a></div>
                <div class="col-md-6">
                    <a href="javascript:;" class="btn-lg btn look_d"  role="button" onClick="showTips('.share_tips')">找小伙伴帮忙</a>
                </div>
            </div>
        </div>
    </div><!--pay_foot-->
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
      function subForm(){
      	$(".subForm").submit();
      }
      
    </script>
</body>
</html>