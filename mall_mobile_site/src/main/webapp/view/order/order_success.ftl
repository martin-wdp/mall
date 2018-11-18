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
    <#include "../common/smart_menu.ftl"/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]> 
      <script src="${basePath}/js/html5shiv.min.js"></script>
      <script src="${basePath}/js/respond.min.js"></script>
    <![endif]-->
 <script type="text/javascript" src="${basePath}/js/shoppingcart/jsOperation.js"></script>

</script>
  </head>
  <body>
    <div class="wrap">
      <div class="success_info">
        <p>订单提交成功</p>
      </div>
      <#if pid?? && pid=='1'>
      	<p class="text-muted">请及时支付订单以确保正常收货。</p>
      </#if>
    </div>
     <form action="payorder.html"  class="payOrder"  method="post">
      <input type="hidden" value="12" name="payId" id="payId" class="pay_id"/>
     <#list map.orders as order>
	    <div class="order_info">
	    <input type="hidden" value="${order.orderCode}" name="orderCode"/>
        <input type="hidden" value="${order.orderId}" name="orderId"/>
	    <#assign pid="${order.payId}">
	      <p><span>订单号：</span><stong>${order.orderCode}</stong></p>
	      <p><span>支付金额：</span><strong class="orange">￥${order.orderPrice?string("0.00")}元</strong></p>
	    </div>
	    </#list>
	    <#if pid?? && pid=='1'>
			<button type="button" onclick="getWXParam(${order.orderId});" class="btn btn-success btn-lg btn-block">微信支付</button>
	    </#if>
    </from>
    <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/jquery.keleyi.js"></script>
    <script src="${basePath}/js/customer/wxforward.js"></script>
    <script>
	  $(function(){
			FastClick.attach(document.body);
	  });
	  function changethis(obj){  
	  	$(".pay_id").attr("value",obj);
	  	$(".payOrder").submit();
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
                  alert(res.err_code + res.err_desc + res.err_msg);
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
