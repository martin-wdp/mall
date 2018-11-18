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
    	<title>商品评价-${(sys.bsetName)!''}</title>
    	<input type="hidden" id="bsetName" value="${(sys.bsetName)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    <#else>
	    <title>商品评价-${(seo.mete)!''}</title>
	    <input type="hidden" id="bsetName" value="${(seo.mete)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    </#if>
	
    <!-- Bootstrap -->
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/rateit.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]> 
      <script src="${basePath}/js/html5shiv.min.js"></script>
      <script src="${basePath}/js/respond.min.js"></script>
    <![endif]-->
   <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script> <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
  <body>
  <#include "../publicHeader2_ftl.ftl" />
    <div class="order_evaluate">
    <#if order??>
      <div class="order_item">
        <div class="order_title">
          <h4>订单号：
          	<#if order.orderNo??>
                 ${order.orderNo}
            </#if>
            </h4>
        </div>
        
        <#if order.goods?size!=0>
	         <#list order.goods as good>
	      <form role="form" id="commForm${(good.goodsId)!''}" action="${basePath}/addgoodscomment.htm" method="post">
          <div class="order_good">
            <a href="${basePath}/item/${good.goodsId}.html">
              <img alt="${good.goodsName}" src="${good.goodsImg}" width="60" height="60" />
              <h5 class="name"><span style="display:block;height:38px;overflow:hidden;">${good.goodsName}</span>
              <small>comment
					<#if (good.specVo??)>
						<#list good.specVo as spec>
							<#if spec_index=0>
                                <#if spec.spec.specName??&&spec.spec.specName == '.'><#else>${spec.spec.specName!''}:${spec.specValueRemark!''}</#if>
							</#if>
						</#list>
					</#if>
				<#--规格：12个装-->
				</small></h5>
              <p class="pull-right text-right">
                <strong>￥<#if good.goodsPrice??>
												${good.goodsPrice?string('0.00')}
											</#if></strong><br>
                <span class="light">×${good.goodsNum}</span>
              </p>
            </a>
          </div>
          <#if good.evaluateFlag?? && good.evaluateFlag=='1' && good.shareFlag?? && good.shareFlag=="1">
	             <a target="_blank" href="${basePath}/item/${good.goodsId}.html#comment" >
	             	<h5 style="color:red;text-align:right;">已晒单</h5>
	             	<hr>
	              </a>
	      </#if>
	      <#if !good.evaluateFlag?? || good.evaluateFlag=='0'>
	          <label class="pull-left" id="commTip${(good.goodsId)!''}">&nbsp;</label>
	          <#--<button type="button" class="btn btn-warning pull-right btn_chk"  onclick="checkComment(${(good.goodsId)!''});">发表评价</button>-->
	          <button type="button" class="btn btn-warning pull-right btn_chk"  onclick="toAddComment(${(good.goodsId)!''},'${basePath}','${(order.orderId)!''}'
                  ,'${good.goodsPrice?string('0.00')}','${good.goodsName}','${order.orderNo}','${good.goodsImg}','${good.goodsNum!}','','${good.orderGoodsId!}');">评价晒单</button>
				<hr>
				<hr>
			</#if>
	      <#if (good.evaluateFlag?? && good.evaluateFlag=='1')&&(!good.shareFlag?? || good.shareFlag=='0')>
	          <label class="pull-left" id="commTip${(good.goodsId)!''}">&nbsp;</label>
	          <#--<button type="button" class="btn btn-warning pull-right btn_chk"  onclick="checkComment(${(good.goodsId)!''});">发表评价</button>-->
	          <button type="button" class="btn btn-warning pull-right btn_chk"  onclick="toAddComment(${(good.goodsId)!''},'${basePath}','${(order.orderId)!''}'
                      ,'${good.goodsPrice?string('0.00')}','${good.goodsName}','${order.orderNo}','${good.goodsImg}','${good.goodsNum}',
                      '${good.commentId!}','${good.orderGoodsId!}');">晒单</button>
				<hr>
				<hr>
			</#if>
		 </form>
		 </#list>
      	</#if>
      </div>
     <#else>
     	<div style="width:100%;text-align:center;padding:100px 10px;">
			    	<div class="notice2">
			        	参数异常！<span>
			        </div>
			        <div class="notice3">
			            <strong><span id="time">5</span>秒自动进入<a href="${basePath}/main.html">“首页”</a></strong></span> 
			        </div>
		 </div>
     </#if>
    </div><!-- /order_evaluate -->

    <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->
    
	<!--引入底部-->
	<#include '/common/smart_menu.ftl' />
	
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/jquery.keleyi.js"></script>
    <!--<script src="${basePath}/js/jquery.rateit.min.js"></script>-->
    <script src="${basePath}/js/customer/wxforward.js"></script>
    <script src="${basePath}/js/publicModel.js"></script>
    <script src="${basePath}/js/customer/memberorder.js"></script>
    <script>
    setTimeout(countDown, 1000);
	function countDown(){
		var time = $("#time").text();
		$("#time").text(time - 1);
		if (time == 1) {
			location.href='${basePath}/main.html';
		} else {
			setTimeout(countDown, 1000);
		}
	}
	</script>
  </body>
</html>
