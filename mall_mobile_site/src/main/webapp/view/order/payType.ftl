<!doctype html>
<#assign basePath=request.contextPath>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>选择支付配送方式</title>
    <link rel="stylesheet" href="${basePath}/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/mui.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/orderstyle.css"/>
    <link rel="stylesheet" href="${basePath}/css/address.css"/>
</head>
<body style="background:#f0f2f5;">
<div class="mui-appbar">
    <h2 class="mui-text-center">选择支付配送方式</h2>
    <a href="javascript:history.go(-1);" class="back-btn"><i class="fa fa-angle-left"></i></a>
</div>
<#--<div class="mui-panel way-panel">-->
    <#--<h3>支付方式</h3>-->
    <#--<div class="way-item">-->
        <#--<div class="way-btns">-->
            <#--<#if payList??>-->
                <#--<#list payList as pl>-->
                    <#--<#if pl.isOpen=="1">-->
                        <#--<button  class="mui-btn mui-btn-default mui-btn-flat ch_pay <#if ch_pay2??><#if ch_pay2?? && ch_pay2==pl.paymentId>checked</#if><#else><#if pl_index==0>checked</#if></#if> " type="button"><i class="fa fa-check"></i>${pl.name}</button>-->
                        <#--<input type="hidden"  value="${pl.paymentId}">-->
                    <#--</#if>-->
                <#--</#list>-->
            <#--</#if>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->

<div class="mui-panel way-panel">
    <h3>支付-配送方式</h3>
<#--boss商品-->
<#list map.orderMarketings as orderMarketings>
    <div class="way-item">
        <div class="order-pros">
            <!--计算boss商品数量-->
        <#assign bossNum=0>
            <!--记录第三方商品数量-->
        <#assign thirdNum=0>

            <#list map.shoplist as cart>
                <#if cart.goodsDetailBean.productVo.thirdId?? && cart.goodsDetailBean.productVo.thirdId==orderMarketings.thirdId>
                    <#--<#if orderMarketings.thirdId!=0>-->
                    <#--<#elseif orderMarketings.thirdId>-->
                        <#assign bossNum=bossNum+1>
                        <img src="${cart.goodsDetailBean.productVo.goodsInfoImgId}" alt=""/>

                    <#--</#if>-->
                </#if>
            </#list>
            </div>

        </div>
    <div class="way-btns" >
        <#if map?? && map.freightlist?size!=0>
            <#list map.freightlist as freight>
                <#if freight.freightThirdId??&&freight.freightThirdId==orderMarketings.thirdId&&freight.freightThirdId==0>
                <button class="mui-btn mui-btn-default mui-btn-flat typeId <#if typeId??&&typeId==0>checked</#if>
            " attr-type="0" type="button" data-way="intro01"><i class="fa fa-check"></i>
               ${freight.freightTemplateName}</button>
                <#--自提点是否启用-->
                    <#if expressConf?? && expressConf.usedStatus=='1'>
                        <#if dps??&&dps?size!=0>
                            <button class="mui-btn mui-btn-default mui-btn-flat typeId
                                <#if typeId??&&typeId==1>
                                    checked
                                </#if>"
                                    attr-type="1" type="button" data-way="intro02">
                                <i class="fa fa-check"></i>
                                ${expressConf.name!''}
                            </button>
                        </#if>
                    </#if>

                </#if>
                <#if freight.freightThirdId==orderMarketings.thirdId&&freight.freightThirdId!=0>
                    <button class="mui-btn mui-btn-default mui-btn-flat checked" type="button"><i class="fa fa-check"></i>
                        <#if freight.freightThirdId==orderMarketings.thirdId&&freight.freightThirdId!=0>${freight.freightTemplateName}</#if>
                    </button>
                </#if>
            </#list>



        </#if>
    </div>
            <div class="way-btns" >
                <#if payList??>
                    <#list payList as pl>
                        <#if pl.isOpen=="1">
                        <#if orderMarketings.thirdId==0>
                            <button  class="mui-btn mui-btn-default mui-btn-flat ch_pay  <#if ch_pay??><#if ch_pay?? && ch_pay==pl.paymentId>checked</#if><#else><#if pl_index==0>checked</#if></#if> "  type="button"><i class="fa fa-check"></i>${pl.name}</button>
                            <input type="hidden"  value="${pl.paymentId}">

                        </#if>
                        </#if>
                    </#list>
                    <#if orderMarketings.thirdId!=0>
                    <button  class="mui-btn mui-btn-default mui-btn-flat ch_paythird checked" type="button"><i class="fa fa-check"></i>在线支付</button>
                    <input type="hidden"  value="1">
                    </#if>
                </#if>
            </div>


    <#if orderMarketings.thirdId==0>
    <div class="way-intro since-way" id="intro02">
        <div class="mt-new">自提地点</div>
        <div class="since-list">
            <#if dps??&&dps?size!=0>
            <#list dps as de>
            <div class="since-item <#if deliveryPointId??><#if deliveryPointId==de.deliveryPointId>selected</#if><#else><#if de_index==0>selected</#if></#if>">
                <p class="mc">${de.name!''}</p>
                <p class="mc">地址：${de.temp1!''}${de.temp2!''}${de.temp3!''}${de.address!''}</p>
            </div>
                <input type="hidden" value="${de.deliveryPointId!''}">
            </#list>
            </#if>
        </div>
    </div>
    </#if>
</#list>

</div>

<div class="operation-bar-wp">
    <div class="operation-bar">
        <button class="mui-btn mui-btn-danger" onclick="checkForm()" type="button">确定</button>
    </div>
</div>
<form id="subForm" method="post">
    <input type="hidden" name="typeId" value="${typeId!''}" id="typeId">
    <input type="hidden" name="ch_pay" id="ch_pay">
    <input type="hidden" name="ch_paythird" id="ch_paythird">
    <input type="hidden" name="addressId" id="addressId" value="${addressId!''}">
    <input type="hidden" name="deliveryPointId" id="deliveryPointId" value="${deliveryPointId!''}">
</form>
<script src="${basePath}/js/jquery-1.11.1.min.js"></script>
<script src="${basePath}/js/mui.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script>
    $(function(){

        $(".typeId").each(function(){
            var _this = $(this);
            //上门自提
            if(_this.attr("attr-type")==1){
                //货到付款不支持上门自提
                if(_this.hasClass("checked")){

                    $(".ch_pay").each(function(){
                        var _thispay=$(this);
                   if(_thispay.hasClass("checked")){
                    if(_thispay.next().val()==1){
                        $("#intro02").show();
                        $(_thispay).next().next().attr("disabled","disabled");
                    }
                   }
                    });
                }
            }else{
                $(".ch_pay").each(function(){

                   $(this).removeAttr("disabled");
                });
            }
        });


        //选中支付方式
        $(".ch_pay").click(function(){
            $(".ch_pay").removeClass("checked ");
            $(this).addClass("checked ");
            $("#ch_pay").val($(this).next().val());
            var paythis=$(this);
            var paytype=paythis.next().val();
                $(".typeId").each(function(){
                    var _this = $(this);
                    //上门自提
                    if(_this.attr("attr-type")==1){
                        //货到付款不支持上门自提
                        if(_this.hasClass("checked")){
                        if(paytype==1){
                            $(paythis).next().next().attr("disabled","disabled");
                        }else{
                            $("#intro02").show();
                        }
                    }
                    }else{
                        if(paytype==1){
                            _this.next().removeAttr("disabled");
                        }else{
                            _this.next().attr("disabled","disabled");
                            $(paythis).removeAttr("disabled");
                        }


                    }
                });

        })

        //第三方选中支付方式
        $(".ch_paythird").click(function(){
            $(".ch_paythird").removeClass("checked ");
            $(this).addClass("checked ");
        })
        //选中配送方式
        $(".typeId").click(function(){
            $(".typeId").removeClass("checked ");
            $(this).addClass("checked ");
            $("#typeId").val($(this).attr("attr-type"));
            if($(this).attr("attr-type")==1){
                var pay=$(".checked");
                var paytype=$(pay).next().val();
                if(paytype==1){
                    $("#intro02").show();
                    $(pay).next().next().attr("disabled","disabled");
                }else{
                    $("#intro02").hide();
                    $(pay).attr("disabled","disabled");
                }

            }else{
                $(".ch_pay").each(function(){

                    $(this).removeAttr("disabled");
                });
                $("#intro02").hide();
            }
        })
    })
    function checkForm(){
        //第三方目前写死
        $("#ch_paythird").val(1);
        $("#deliveryPointId").val($(".selected").next().val());

        $("#subForm").attr("action","suborder.htm").submit();
    }
</script>
</body>
</html>