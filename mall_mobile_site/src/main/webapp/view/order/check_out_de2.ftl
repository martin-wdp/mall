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
    </head>
    <body>
    <form action="order/payorder.html"  class="payOrder"  method="post">
    <#assign prefer=0>
    <input type="hidden" value="12" name="payId" id="payId" class="pay_id"/>
    <input type="hidden" value="${order.orderCode}" name="orderCode"/>
    <input type="hidden" value="${order.orderId}" name="orderId" id="orderId"/>
    <#assign sumprice=0>
    <div class="check_goods">
    <h4 class="text-center"><img height="20" alt="" src="images/success_icon2.png">&nbsp;订单提交成功！<small>请尽快完成付款</small></h4>
    <#list order.orderGoodsList as orderGoods>
    <div class="check_goods_item">
    <div class="img"><a href="${basePath}/item/${orderGoods.goodsProductVo.goodsInfoId}.html"><img alt=""  src="<#if orderGoods.goodsProductVo.goodsInfoImgId??>${orderGoods.goodsProductVo.goodsInfoImgId}</#if>"></a></div>
    <div class="word">
    <p><a href="${basePath}/item/${orderGoods.goodsProductVo.goodsInfoId}.html">${orderGoods.goodsProductVo.goodsInfoName}</a></p>
    <p class="light"><#if orderGoods.marketing??>${orderGoods.marketing.marketingName}</#if></p>
    </div>
    <div class="price">
    <p>￥${(orderGoods.goodsInfoPrice)?string("0.00")}</p>
    <p>×${orderGoods.goodsInfoNum}</p>
        <#assign sumprice="${sumprice?number+(orderGoods.goodsInfoPrice)*orderGoods.goodsInfoNum}">
    </div>

    </div>
    </#list>
    <div class="total_price">
    <span>商品总价：</span>
    <p>￥${(sumprice?number)?string("0.00")}</p>
    </div>
    </div><!-- /check_goods -->
    <div class="address">
    <#if order.orderExpressType='0'>
    <address>
    ${(order.shippingPerson)!""}<br>
    ${(order.shippingMobile)!""}<br>
    ${(order.shippingProvince)!""}省&nbsp;${(order.shippingCity)!""}市&nbsp;${(order.shippingCounty)!""}<br>
    ${(order.shippingAddress)!""}
    </address>
    <#else>
    <address>
    [自提点]${order.shippingPerson}<br>
    ${order.shippingProvince}省&nbsp;${order.shippingCity}市&nbsp;${order.shippingCounty}<br>
    ${order.shippingAddress}
    </address>
    </#if>
    </div><!-- /address -->
    <div class="settle">
    <p>￥${(sumprice?number)?string("0.00")} + ￥${(order.expressPrice)?string("0.00")}运费</p><p>-￥${(order.orderPrePrice)?string("0.00")}优惠金额</p>
    <p><strong>实付：￥${(order.orderPrice)?string("0.00")}</strong></p>
    </div><!-- /settle -->
    <#if order.payId??&&order.payId==1>
    <div class="wrap">
    <button type="button" id="wxPayBtn" onclick="getWXParam(${order.orderId});" class="btn btn-success btn-lg btn-block" >微信支付</button>
        <#if paylist??>
            <#list paylist as pay>
                <#if (pay.payType='1')>
                <button type="button" id="zfbPayBtn"  onclick="changethis(${pay.payId});" class="btn btn-warning btn-lg btn-block">支付宝支付</button>
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
    <script>

        function otherPay(obj){
            $(".payOrder").attr("action","order/queryother.html").submit();
        }
        $(function(){
            FastClick.attach(document.body);
            if(!isWeiXin()) {
                $("#wxPayBtn").hide();
            }

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
