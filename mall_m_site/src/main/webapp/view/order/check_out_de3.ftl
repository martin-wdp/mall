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
<body style="background-color:#EFEFEF;">
<a style="position:absolute;top:1em;right:1em;color:#fff;z-index:10000;" href="customer/detail-${order.orderId}.html">查看订单</a>
<#include "../publicHeader2_ftl.ftl" />
<form action="order/payorder.html"  class="payOrder"  method="post">
<#assign prefer=0>
<input type="hidden" value="12" name="payId" id="payId" class="pay_id"/>
<input type="hidden" value="${order.orderCode}" name="orderCode"/>
<input type="hidden" value="${order.orderId}" name="orderId" id="orderId"/>
<input type="hidden" id="openid" value="${openid?default('暂无')}" >

<#if order.payId??&&order.payId==1>
<p class="moneyP"><span>支付金额</span><strong>￥${(order.orderPrice)?string("0.00")}</strong></p>


<!--<div class="wrap">-->

<!--<button type="button" id="wxPayBtn" onclick="getWXParam(${order.orderId});" class="btn btn-success btn-lg btn-block" ></button>-->
    <#if paylist??>
        <#list paylist as pay>
            <ul class="payList">
                <#if (pay.payType='1')>
                <!--<button type="button"   onclick="changethis(${pay.payId});" class="btn btn-warning btn-lg btn-block">支付宝支付</button>-->
                <li id="zfbPayBtn" onclick="alipay(${order.orderId});"><img src="${pay.payImage}"/>支付宝支付<i></i></li>
                </#if>
                <#--<#if (pay.payType='2')>
                <li id="ylPayBtn" onclick="changethis(${pay.payId});"><img src="${pay.payImage}"/>银联支付<i></i></li>
                </#if>-->
                <#if (pay.payType='3')>
                <li id="wxPayBtn" onclick="getWXParam(${order.orderId});"><img src="${pay.payImage}"/>微信支付<i></i></li>
                </#if>
            </ul>
        </#list>
    </#if>
<!--</div>-->
</#if>
</form>
<div class="foot">
<p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
</div><!-- /foot -->


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
function jsonToStr(json)
        {
            var s = "";
            $.each(json,function(i,n){
                s+= ",\"" + i + "\":\"" + n+"\"";
            });
            
            if(s != "")
            {
                s = s.substring(1);
            }
            
            return "{" + s + "}";
        }

    function otherPay(obj){
        $(".payOrder").attr("action","order/queryother.html").submit();
    }
    /*$(function(){
        if(!isWeiXin()) {
            $("#wxPayBtn").hide();
        }
        if(isWeiXin()) {
            $("#zfbPayBtn").hide();
        }

    });*/
    /*function changethis(obj){

        $(".pay_id").attr("value",obj);
        $(".payOrder").attr("action","order/payorder.html").submit();
    }*/

    function alipay(obj){
        $.ajax({
            type: "POST",
            /*url: "getwxparam.htm",*/
            url: "getorderinfo.htm",
            data: {"orderId":obj,"payType":"alipay"},
            success: function(msg){
                //callpay(msg.appId,msg.timeStamp,msg.nonceStr,msg.package,msg.sign);
                var order = {"orderNo":msg.order.orderCode,"title":msg.goodsName,"desc":"京华亿家"+msg.goodsName+"商品移动支付","amount":msg.order.orderPrice};
                //alert(order);
                myObj.createOrder("alipay",jsonToStr(order));
            }
        });
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
            /*url: "getwxparam.htm",*/
            url: "getorderinfo.htm",
            data: {"orderId":obj,"payType":"wechat"},
            success: function(msg){
                if(msg.wxPayJson.returncode=="SUCCESS"){
                    msg.wxPayJson.orderNo=msg.order.orderCode;
                    /*alert(jsonToStr(msg.wxPayJson));*/
                    /*var order = {"orderNo":msg.order.orderCode
                        ,"title":msg.goodsName
                        ,"desc":"京华亿家"+msg.goodsName+"商品移动支付"
                        ,"amount":msg.order.orderPrice
                        ,"prepayId":msg.wxOrder.prepay_id
                    };*/
                    myObj.createOrder("wxpay",jsonToStr(msg.wxPayJson));
                }else{
                    alert("生成预支付订单失败，请重新支付");
                }
                //callpay(msg.appId,msg.timeStamp,msg.nonceStr,msg.package,msg.sign);

            }
        });
    }

    function callback(obj){
	var data=eval('('+obj+')');
        if(data.paystatus=='true'){
            $.ajax({
                type: "POST",
                url: "callback.htm",
                data: "outTradeNo="+data.orderNo,
                success: function(msg){
                    if(msg=="true"){
                        alert("支付成功");
                        location.href="${basePath}/customer/myorder.html";
                    }else{
                        alert("支付失败");
                        location.href="${basePath}/customer/myorder.html";
                    }


                }
            });
        }else{

        }
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
                /*alert("支付成功！");*/
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
