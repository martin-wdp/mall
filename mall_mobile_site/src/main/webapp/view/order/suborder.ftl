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
<link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
<link href="${basePath}/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="${basePath}/css/font-awesome.min.css"/>
<link rel="stylesheet" href="${basePath}/css/mui.min.css"/>
<link rel="stylesheet" href="${basePath}/css/orderstyle.css"/>
<link rel="stylesheet" href="${basePath}/css/address.css"/>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="${basePath}/js/html5shiv.min.js"></script>
<script src="${basePath}/js/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="${basePath}/js/shoppingcart/jsOperation.js"></script>

</script>
</head>
<body style="background:#f0f2f5;">
<div class="mui-appbar">
    <h2 class="mui-text-center">填写订单</h2>
    <a href="javascript:history.go(-1);" class="back-btn"><i class="fa fa-angle-left"></i></a>
</div>
    <!--计算总商品数量-->
<#assign num=0>
    <!--计算boss商品数量-->
<#assign bossNum=0>
    <!--记录第三方商品数量-->
<#assign thirdNum=0>

    <!--商品优惠价格-->
<#assign preprice=0>
<#if map??&& map.orderMarketings??&&map.shoplist??>
    <#list  map.orderMarketings as orderMarketings>
    <#list map.shoplist as cart>
        <#if cart.goodsDetailBean.productVo.thirdId?? && cart.goodsDetailBean.productVo.thirdId==orderMarketings.thirdId>
            <#if orderMarketings.thirdId!=0>
                <#assign thirdNum=thirdNum+1>
            <#elseif orderMarketings.thirdId==0>
                <#assign bossNum=bossNum+1>
            </#if>
        </#if>
    </#list>
</#list>
</#if>

<form action="${basePath}/submitorder.htm" method="post" id="subForm">
        <input type="hidden"name="ch_pay" value="${ch_pay!'1'}"/>
        <#--目前第三方只支持在线付款-->
        <input type="hidden"name="ch_paythird" value="1"/>

        <input type="hidden" id="bosssumPrice" value="<#if map??>${map.bossSumPrice!0}</#if>"/>
        <input type="hidden"name="typeId" value="${typeId!'0'}" id="typeId"/>
        <input type="hidden"name="codeNo" value="<#if coupon??>${coupon.codeNo!''}</#if>" id="typeId"/>
        <#if coupon??>
        <!--购物券类型-->
        <input type="hidden" value="${coupon.couponRulesType!''}" id="couponType"/>
        <!--购物券直降类型-->
        <input type="hidden" value="<#if coupon.couponStraightDown??>${coupon.couponStraightDown.downPrice!0}</#if>" id="downPrice"/>
        <!--购物券满减类型-->
        <input type="hidden" value="<#if coupon.couponFullReduction??> ${coupon.couponFullReduction.reductionPrice!0}</#if>" id="reductionPrice"/>
        </#if>

        <input type="hidden"name="deliveryPointId" value="${deliveryPointId!'0'}" id="deliveryPointId"/>
        <input type="hidden" value="${invoiceType!'0'}" name="invoiceType" id="invoiceType">
        <input type="hidden" value="${invoiceTitle!''}" name="invoiceTitle" id="invoiceTitle">
    <#if orderAddress??>
    <input type="hidden"name="addressId" value="${orderAddress.addressId!''}" id="addressId"/>
        <div class="mui-panel order-panel address-item">
            <div class="panel-item">


                    <span class="address-info"><i class="fa fa-user"></i>${(orderAddress.addressName)!""}</span>
                    <span class="address-info"><i class="fa fa-mobile"></i>	${(orderAddress.addressPhone)!""}</span>
                    <p>${(orderAddress.addressDetail)!""}&nbsp;
                    ${(orderAddress.addressDetailInfo)!""}</p>

    <a href="${basePath}/addresslist.htm">

                <i class="fa fa-angle-right"></i>
    </a>
            </div>
        </div>
    </#if>


        <div class="mui-panel order-panel">
            <div class="panel-item">

                    <div class="mt-new">
                        <#if payList??>
                         <#list payList as pl>
                            <#if pl.isOpen=="1">
                                <#if ch_pay??>
                                        <#if ch_pay==pl.paymentId>
                                        ${pl.name}
                                        </#if>
                                <#else>
                                    <#if pl_index==0>
                                    ${pl.name}
                                    </#if>
                                </#if>
                            </#if>
                         </#list>
                        </#if>
                    </div>

<#if map?? && map.freightlist?size!=0 &&typeId==0>
    <#list map.freightlist as freight>
                                <div class="mc">
        <#if freight_index==0>${freight.freightTemplateName}</#if>
                                </div>
                                </#list>
                            <div class="mc">平台会再24小时内发货，请注意查看！</div>
        <#elseif typeId==1>
        <div class="mc">
        上门自提
        </div>
        <div class="mc"><#if dps??>${dps.temp3!''}${dps.address!''}</#if></div>
        </#if>

<a href="${basePath}/toFreightAndPay.htm?addressId=${orderAddress.addressId}&typeId=${typeId!''}&ch_pay=${ch_pay!''}&deliveryPointId=${deliveryPointId!''}&codeNo=<#if coupon??>${coupon.codeNo!''}</#if>">

<i class="fa fa-angle-right"></i>

</a>
            </div>
        </div>



<div class="mui-panel order-panel pros-item">
<div class="panel-item mui-clearfix">
                    <div class="mt-new">纸质发票：<span id="invoiceTitle">${invoiceTitle!'不使用'}</span> </div>
                    <div class="mc" id="invoiceType">${invoiceType!''}</div>

    <a href="${basePath}/tochangeInvoice.htm?invoiceTitle=${invoiceTitle!''}&invoiceType=${invoiceType!''}&addressId=${orderAddress.addressId}&typeId=${typeId!''}&ch_pay=${ch_pay!''}&deliveryPointId=${deliveryPointId!''}&codeNo=<#if coupon??>${coupon.codeNo!''}</#if>">

    <i class="fa fa-angle-right"></i>
    </a>
</div>
</div>

        <div class="mui-panel order-panel pros-item">
            <div class="panel-item mui-clearfix">

                    <div class="sitem-l">

                    <#list map.orderMarketings as orderMarketings>
                                <#list map.shoplist as cart>
                                    <#if cart.goodsDetailBean.productVo.thirdId?? && cart.goodsDetailBean.productVo.thirdId==orderMarketings.thirdId>
                                    <img src="${cart.goodsDetailBean.productVo.goodsInfoImgId}" alt=""/>
                                    <input type = "hidden" value = "${orderMarketings.thirdId}"name = "thirdId" />
                                    <input type="hidden" value="${cart.shoppingCartId}" name="shoppingCartId">
                                        <#assign num=num+(cart.goodsNum)?number>
                                    </#if>
                                </#list>
                            </#list>

                    </div>
<a href="${basePath}/toproductsList.htm" >
                    <div class="sitem-r">共${num}件</div>

                <i class="fa fa-angle-right"></i>
</a>
            </div>
        </div>

        <div class="mui-panel order-panel">
          <div class="panel-item mui-clearfix">

            <div class="sitem-l" >
            优惠券
<#if coupon??&&coupon.couponName??><span class="tip">${coupon.couponName}</span></#if>

            </div>
<a style="display:inline" href="${basePath}/tocouponlist.htm?addressId=${orderAddress.addressId}&typeId=${typeId!''}&ch_pay=${ch_pay!''}&deliveryPointId=${deliveryPointId!''}&codeNo=<#if coupon??>${coupon.codeNo!''}</#if>">

<div class="sitem-r">选择</div>

            <i class="fa fa-angle-right"></i>
</a>
            </div>

            <div class="panel-item mui-clearfix switch-item"<#if bossNum==0>style="display:none"</#if> >
                <input type="hidden" value="${map.customerPoint!'0'}" id="pointSum">
                 <input type="hidden" value="${map.pointSet!'0'}" id="pointSet">

            <div class="sitem-l">
            您有${map.customerPoint!'0'}积分
            <span class="mc">
            本次可用<b id="muqianjifen" style="font-size:16px"></b>积分，使用
            <input class="integral-input" type="text"name="point" id="point" onblur="checkjifen()" />
            积分
            </span> <br/><span id="errorPoint" class="mc" style="color:red;"></span>
            </div>
            <div class="sitem-l">
                <span class="mc"> 每10积分兑换人民币￥${map.pointSet}元。</span>
            </div>
            </div>
        </div>

<input
type = "hidden"
value = "${map.sumPrice}"
id = "sumPrice" / >
        <div class="mui-panel order-panel total-item">
            <div class="mui-clearfix">
                <div class="sitem-l">商品金额</div>
<div class= "sitem-r" >${map.sumPrice?string("0.00")}</div>
            </div>
            <div class="mui-clearfix">
                <div class="sitem-l">运费</div>
                <div class="sitem-r" id="ep">5.00</div>
            </div>
        <#if bossNum!=0>
            <div class="mui-clearfix">
            <div class="sitem-l">积分兑换</div>
            <div class="sitem-r" id="pointPrice">-0.00</div>
            </div>
        </#if>
        <div class="mui-clearfix">
        <div class="sitem-l">优惠券</div>
        <div class="sitem-r" id="couponprice">-0.00</div>
        </div>

<div class="mui-clearfix" style="height:30px">


        </div>

        <input type="hidden"  />
    <div class="pay-bar mui-clearfix">
        <input type="hidden" id="payPrice2"/>
        <div class="pay-cont">
            实付款：&yen;&nbsp;
            <span id="payPrice"></span>
        </div>
        <button class="mui-btn mui-btn-danger" type="button" onclick="checkForm()">提交订单</button>
    </div>
</form>

<script src="${basePath}/js/jquery-1.11.1.min.js"></script>
<script src="${basePath}/js/mui.min.js"></script>
<script src="${basePath}/js/app.js"></script>

<script>
    var isflag=true;
    var subNum=0;
    function checkForm(){
      if(isflag&&subNum==0){
          subNum=subNum+1;
        $("#subForm").submit();
      }
    }
    //使用积分兑换
    function checkjifen(){
            isflag=true;
            var point=$("#point").val();//使用的积分
            var pointSet=$("#pointSet").val();//积分兑换规则
             var bossSumPrice=$('#bosssumPrice').val();	//boss商品总金额
            var keyishiyong = accDiv(accMul(bossSumPrice,10),pointSet);
            keyishiyong=parseInt(parseInt(accDiv(parseInt(keyishiyong),10))+"0");
            var pointSum=$("#pointSum").val();//用户的总积分
            var payPrice=$("#payPrice2").val();//支付金额
            var pointPrice=point*pointSet; //
            if(!isNaN(point)&&point>=0){
                if(Subtr(pointSum,point)<0){
                    isflag=false;
                    //积分不足
                    $("#errorPoint").html("您的积分不足！");
                    $("#point").val("");
                }else if(point %10 !=0){
                    isflag=false;
                    $("#errorPoint").html('注：兑换的积分必须为10的倍数!');
                    $('#point').val('');
                }
                else if(Subtr(bossSumPrice,accDiv(pointPrice,10))<0){
                    isflag=false;
                    //积分兑换金额大于订单金额
                    $("#errorPoint").html("注：此次订单价格最多可以使用【"+keyishiyong+"】的积分！");
                    $("#point").val("");
                }else{
                    $("#errorPoint").html("");
                    $("#pointPrice").html("-"+accDiv(pointPrice,10));
                    $("#payPrice").html(Subtr(payPrice,accDiv(pointPrice,10)));
                }
            }else{
                isflag=false;
                $("#errorPoint").html("请填写正整数！");
                $("#point").val("");
            }
        return isflag;

    }
    $(function(){

        var pointSet=$("#pointSet").val();//积分兑换规则
        var bossSumPrice=$('#bosssumPrice').val();	//boss商品总金额
        var keyishiyong = accDiv(accMul(bossSumPrice,10),pointSet);
        keyishiyong=parseInt(parseInt(accDiv(parseInt(keyishiyong),10))+"0");
        var pointSum=$("#pointSum").val();//用户的总积分
        if(bossSumPrice>0){
            if(pointSum<keyishiyong){
                $('#muqianjifen').html(pointSum);          //设置目前可用的积分
            }else{
                $('#muqianjifen').html(keyishiyong);          //设置目前可用的积分
            }
        }else{
            $('#muqianjifen').html(0);          //设置目前可用的积分
        }


        var yfprice = 0;
        //boss运费
        var bossyfprice=0;
        var payprice =$("#sumPrice").val();;
        var addsId = $("#addressId").val();
        var boxs=new Array();
        //获取订单数量
        $('input[name="shoppingCartId"]').each(function () {
            boxs.push(parseInt($(this).val()));
        });


        //计算此次购买商品总运费价格
            $.ajax({
                type:"post",
                url: "${basePath}/getnewMexpressprice.htm",
                async : false,
                data: {addressId: addsId, shopIds: boxs},
                success:function(data){
                    //key值为Map的键值
                    $.each(data,function(key,value){

                        if(data!=null&&data!=''){

                            //总运费
                            if(key=="freightmoney"){
                                yfprice = accAdd(value, yfprice);
                            }
                            //boss运费
                            if(key=="bossfreight"){
                                bossyfprice = accAdd(value, bossyfprice);
                            }

                        }
                    });
                }
            });
        var paysumprice=0;
        var thirdprice=Subtr(yfprice,bossyfprice);
        //上门自提时免运费
        if($("#typeId").val()==1){
            $("#ep").html("+"+thirdprice);
            $("#payPrice").html(accAdd(payprice,thirdprice));
            $("#payPrice2").val(accAdd(payprice,thirdprice));
            paysumprice=accAdd(payprice,thirdprice);
        }else{
            $("#ep").html("+"+yfprice);
            $("#payPrice").html(accAdd(payprice,yfprice));
            $("#payPrice2").val(accAdd(payprice,yfprice));
            paysumprice=accAdd(payprice,yfprice);
        }
        //使用购物券类型 1:直降 2:满减
        var couponType=$("#couponType").val();
        //购物券直降金额
        var downPrice=$("#downPrice").val();
        //购物券满减金额
        var reductionPrice=$("#reductionPrice").val();
        if(couponType==1){
            $("#couponprice").html("-"+downPrice);
            $("#payPrice").html(Subtr(paysumprice,downPrice));
            $("#payPrice2").val(Subtr(paysumprice,downPrice));
        }
        if(couponType==2){
            $("#couponprice").html("-"+reductionPrice);
            $("#payPrice").html(Subtr(paysumprice,reductionPrice));
            $("#payPrice2").val(Subtr(paysumprice,reductionPrice));
        }
    })
</script>
</body>
</html>