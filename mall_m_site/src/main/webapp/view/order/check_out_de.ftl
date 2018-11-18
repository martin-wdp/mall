<!DOCTYPE html>
<html lang="zh-cn">
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
   <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
  <body>
  <#include "../publicHeader2_ftl.ftl" />
  <form action="order/payorder.html"  class="payOrder"  method="post">
 	 <#assign prefer=0>
      <input type="hidden" value="12" name="payId" id="payId" class="pay_id"/>

 	 <#assign sumprice=0>
     <#assign eprice=0>
     <div class="check_goods">
      <h4 class="text-center"><img height="20" alt="" src="${basePath}/images/success_icon2.png">&nbsp;恭喜您，成功提交订单！<small>请尽快完成支付</small></h4>
<#list orders as order>
    <#assign eprice="${eprice?number+(order.expressPrice)?number}">
    <#assign prefer="${prefer?number+(order.orderPrePrice)?number}">

    <#if order.businessId==0>
        <#assign pid="${order.payId}">
    <#else >
        <#assign thirdpid="${order.payId}">
    </#if>
    <#if pid??&&pid=="1">
    <input type="hidden" value="${order.orderCode}" name="orderCode"/>
    <input type="hidden" value="${order.orderId}" name="orderId" id="orderId"/>
    </#if>
    <#if thirdpid??&&thirdpid=="1">
    <input type="hidden" value="${order.orderCode}" name="orderCode"/>
    <input type="hidden" value="${order.orderId}" name="orderId" id="orderId"/>
    </#if>

<p class="ptitle">确认订单信息</p>
       <#list order.orderGoodsList as orderGoods>
      <div class="check_goods_item">
        <div class="img"><a href="${basePath}/item/${orderGoods.goodsProductVo.goodsInfoId}.html"><img alt=""  src="<#if orderGoods.goodsProductVo.goodsInfoImgId??>${orderGoods.goodsProductVo.goodsInfoImgId}</#if>"></a></div>
        <div class="word">
          <p><a href="${basePath}/item/${orderGoods.goodsProductVo.goodsInfoId}.html">${orderGoods.goodsProductVo.goodsInfoName}</a></p>
          <p class="light"><#if orderGoods.marketing??>${orderGoods.marketing.marketingName}</#if></p>
        </div>
        <div class="price">
          <p style="height:30px;font-size:20px;">￥${(orderGoods.goodsInfoPrice)?string("0.00")}</p>
          <p style="color:#dcdcdc;">数量：${orderGoods.goodsInfoNum}</p>
            <p style="height:30px;">商品价格：<span style="font-size:20px;color:#F6AB00;">￥300.00</span></p>
        </div>
       
      </div> 
      </#list>
    <#assign sumprice="${sumprice?number+order.orderPrice}">
</#list>
      <#--<div class="total_price">-->
        <#--<span>商品总价：</span>-->
        <#--<p>￥${(sumprice?number)?string("0.00")}</p>-->
      <#--</div>-->
    <#--</div><!-- /check_goods &ndash;&gt;-->
  <p class="ptitle">确认收货地址</p>
  <#assign addressNum=0>
<#list orders as order>
    <div class="address">
    <#if order.orderExpressType='0'>
        <#if addressNum==0>
            <#assign addressNum=addressNum+1>
              <address>
         ${(order.shippingPerson)!""}<br>
                ${(order.shippingMobile)!""}<br>
                ${(order.shippingProvince)!""}&nbsp;${(order.shippingCity)!""}&nbsp;${(order.shippingCounty)!""}<br>
                ${(order.shippingAddress)!""}
              </address>
        </#if>
    <#else>
    	 <address>
	        [自提点]${order.shippingPerson}<br>
	        ${order.shippingProvince}&nbsp;${order.shippingCity}&nbsp;${order.shippingCounty}<br>
	        ${order.shippingAddress}
         </address>
    </#if>
    </div><!-- /address -->
</#list>
    <div class="settle">
            <p style="font-size:12px;">运费：￥10.00+商品总价：￥300.00-优惠金额：￥0.00</p>
	      <p><strong>实付：<span style="font-size:24px;">￥${(sumprice?number)?string("0.00")}</span></strong></p>
    </div><!-- /settle -->
<#if pid??&&pid=='1'  ||thirdpid??&&thirdpid=='1'>

    <div class="wrap">
        <#if order.payId??&&order.payId==1>
       <button type="button" id="wxPayBtn" onclick="getWXParam(${order.orderId});" class="btn btn-success btn-lg btn-block" >微信支付</button>
       </#if>
        <#if paylist??>
            <#list paylist as pay>
                <#if (pay.payType='1')>
                    <button type="button" id="zfbPayBtn"  onclick="changethis(${pay.payId});" class="btn btn-warning btn-lg btn-block">支 付</button>
                </#if>
             </#list>
        </#if>
    </div>
    </#if>
    </form>
    <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->
    
    <#include "../common/smart_menu.ftl"/>
        
    <input type="hidden" class="appId">
    <input type="hidden" class="timeStamp">
    <input type="hidden" class="nonceStr">
    <input type="hidden" class="package">
    <input type="hidden" class="sign">

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/jquery.keleyi.js"></script>
    <script src="${basePath}/js/customer/wxforward.js"></script>
    <script src="${basePath}/js/publicModel.js"></script>
    <script>
    
     function otherPay(obj){
     	$(".payOrder").attr("action","order/queryother.html").submit();
     }
	  $(function(){

		if(!isWeiXin()) {
            $("#wxPayBtn").hide();
        }
		if(isWeiXin()) {
			$("#zfbPayBtn").hide();
		}
			
	  });
	  function changethis(obj){  
	 
	  	$(".pay_id").attr("value",obj);
	  	$(".payOrder").attr("action","order/payorder.html").submit();
	   }
	   
	   function isWeiXin(){
		    var ua = window.navigator.userAgent.toLowerCase();
		    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
		        return true;
		    }else{
		        return false;
		    }
	 }
	 function getWXParam(obj){
	   
	   $.ajax({
           type: "POST",
           url: "getwxparam.htm",
           data: "orderId="+obj,
           success: function(msg){
               callpay(msg.appId,msg.timeStamp,msg.nonceStr,msg.package,msg.sign);
           }
        });
	 }
	</script>
	
	<script type="text/javascript">
    function callpay(appId,timeStamp,nonceStr,package,sign){
         WeixinJSBridge.invoke('getBrandWCPayRequest',{
          "appId" : appId,
          "timeStamp" : timeStamp, 
          "nonceStr" : nonceStr, 
          "package" : package,
          "signType" : "MD5", 
          "paySign" : sign 
                },function(res){
                    WeixinJSBridge.log(res.err_msg);
                    if(res.err_msg == "get_brand_wcpay_request:ok"){  
                        alert("支付成功！");
                       location.href="${basePath}/customer/myorder.html";
                    }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
                        alert("用户取消支付!");  
                    }else{  
                       alert("支付失败!");  
                    }  
                })
            }
      </script>
  
	
  </body>
</html>
