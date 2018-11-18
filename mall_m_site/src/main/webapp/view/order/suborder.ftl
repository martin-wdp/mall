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
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/mui.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/orderstyle.css"/>
    <link rel="stylesheet" href="${basePath}/css/address.css"/>

    <link rel="stylesheet" href="${basePath}/css/sub-Addr.css"/>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${basePath}/js/html5shiv.min.js"></script>
    <script src="${basePath}/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${basePath}/js/shoppingcart/jsOperation.js"></script>

 <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
<body style="background:#f0f2f5;">
<#include "../publicHeader2_ftl.ftl" />
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
    <input type="hidden" name="ch_pay" value="${ch_pay!'1'}"/>
<#--目前第三方只支持在线付款-->
    <input type="hidden" name="ch_paythird" value="1"/>

    <input type="hidden" id="bosssumPrice" value="<#if map??>${map.bossSumPrice!0}</#if>"/>
    <input type="hidden" name="typeId" value="${typeId!'0'}" id="typeId"/>
    <input type="hidden" name="codeNo" value="<#if coupon??>${coupon.codeNo!''}</#if>" id="typeId"/>
<#if coupon??>
    <!--购物券类型-->
    <input type="hidden" value="${coupon.couponRulesType!''}" id="couponType"/>
    <!--购物券直降类型-->
    <input type="hidden" value="<#if coupon.couponStraightDown??>${coupon.couponStraightDown.downPrice!0}</#if>"
           id="downPrice"/>
    <!--购物券满减类型-->
    <input type="hidden" value="<#if coupon.couponFullReduction??> ${coupon.couponFullReduction.reductionPrice!0}</#if>"
           id="reductionPrice"/>
</#if>

    <input type="hidden" name="deliveryPointId" value="${deliveryPointId!'0'}" id="deliveryPointId"/>
    <input type="hidden" value="${invoiceType!'0'}" name="invoiceType" id="invoiceType">
    <input type="hidden" value="${invoiceTitle!''}" name="invoiceTitle" id="invoiceTitle">
    <input type="hidden" value="" name="invoiceContent" id="invoiceContent">
<#if orderAddress??>
    <input type="hidden" name="addressId" value="${orderAddress.addressId!''}" id="addressId"/>

   <#-- <div class="mui-panel order-panel address-item">
        <div class="panel-item">
            <span class="address-info"><i class="fa fa-user"></i>${(orderAddress.addressName)!""}</span>
            <span class="address-info"><i class="fa fa-mobile"></i>    ${(orderAddress.addressPhone)!""}</span>

            <p>${(orderAddress.addressDetail)!""}&nbsp;${(orderAddress.addressDetailInfo)!""}</p>

            <a href="${basePath}/addresslist.htm?needRetrun=1">
                <i class="fa fa-angle-right"></i>
            </a>
        </div>
    </div>-->
    <div class="inpbox">
        <p class="add1">
            <span class="sp1">${(orderAddress.addressName)!""}</span>
            <span class="sp2">${(orderAddress.addressPhone)!""}</span><br>
            <span class="sp3">${(orderAddress.addressDetail)!""}&nbsp;${(orderAddress.addressDetailInfo)!""}</span>
        </p>
        <#--<a href="#" class="more_add"><img src="${basePath}/images/qp_sxq.png"></a>-->
        <span class="more_sp"><img src="${basePath}/images/qp_sxq.png"></span>
    </div>
    <div class="bg-addr" >
        <div class="sub_addr">
            <p class="rep-p">选择收货地址</p>
            <#--<div class="rep-addr">
                <p class="rep-p1">
                    <span class="rep-sp1">a</span>
                    <span class="rep-sp2">a</span></br>
                    <span class="rep-sp3">a</span>
                </p>
                <p class="add-p">
                    <input type="radio" class="radio-btn" name="radioName" value="111"><label >收货地址</label>
                    &lt;#&ndash;<button>编辑</button><button>删除</button>&ndash;&gt;
                </p>
            </div>
            <div class="rep-addr">
                <p class="rep-p1">
                    <span class="rep-sp1">a</span>
                    <span class="rep-sp2">a</span></br>
                    <span class="rep-sp3">a</span>
                </p>
            <p class="add-p">
                <input type="radio" class="radio-btn" name="radioName"><label >收货地址</label>
            &lt;#&ndash;<button>编辑</button><button>删除</button>&ndash;&gt;
            </p>
            </div>
            <div class="rep-addr">
                <p class="rep-p1">
                    <span class="rep-sp1">a</span>
                    <span class="rep-sp2">a</span></br>
                    <span class="rep-sp3">a</span>
                </p>
                <p class="add-p">
                    <input type="radio" class="radio-btn" name="radioName"><label >收货地址</label>
                &lt;#&ndash;<button>编辑</button><button>删除</button>&ndash;&gt;
                </p>
            </div>
            <div class="rep-addr">
                <p class="rep-p1">
                    <span class="rep-sp1">a</span>
                    <span class="rep-sp2">a</span></br>
                    <span class="rep-sp3">a</span>
                </p>
                <p class="add-p">
                    <input type="radio" class="radio-btn" name="radioName"><label >收货地址</label>
                &lt;#&ndash;<button>编辑</button><button>删除</button>&ndash;&gt;
                </p>
            </div>-->
        </div>
    </div>
    <div class="bill">
        <a class="billa" href="#">购物清单</a>
        <div class="mui-panel order-panel pros-item">
            <div class="panel-item mui-clearfix">

                <div class="sitem-l">

                    <#list map.orderMarketings as orderMarketings>
                        <#list map.shoplist as cart>
                            <#if cart.goodsDetailBean.productVo.thirdId?? && cart.goodsDetailBean.productVo.thirdId==orderMarketings.thirdId>
                                <img src="${cart.goodsDetailBean.productVo.goodsInfoImgId}" alt=""/>
                                <input type="hidden" value="${orderMarketings.thirdId}" name="thirdId"/>
                                <input type="hidden" value="${cart.shoppingCartId}" name="shoppingCartId">
                                <#assign num=num+(cart.goodsNum)?number>
                            </#if>
                        </#list>
                    </#list>

                </div>
                <a href="${basePath}/toproductsList.htm">
                    <div class="sitem-r">共${num}件</div>
                </a>
                <img class="bil-img sxq-img" src="${basePath}/images/qp_sxq.png">
            </div>
        </div>
        <!--<div class="camer">
            <p>
                <img src="../images/camer.jpg">
                <i>x1</i><br/>
                <span>￥2880.00</span>
            </p>
            <p class="cam-p">
                <img src="../images/camer.jpg">
                <i>x1</i><br/>
                <span>￥2880.00</span>
            </p>
            <p class="cam-p">
                <img src="../images/camer.jpg">
                <i>x1</i><br/>
                <span>￥2880.00</span>
            </p>
            <p class="cam-r">全部10件<br/>...</p>
        </div>-->
    </div>
    <div class="bill">
        <p class="leave">留言</p>
        <textarea placeholder="写下您要嘱咐的话吧。" name="customerRemark"></textarea>
    </div>
    <div class="bill">
        <a class="pay-info" id="payselect" href="#" style="border-bottom: none;">支付方式<span>在线支付</span></a>
       <#-- <img src="${basePath}/images/qp_sxq.png" class="bil-img" >-->
        <div id="showPayInfo" style="display: none">
            <div id="onlinePay"><span>在线支付</span></a></div>
            <div id="outlinePay"><span>在线支付</span></a></div>
        </div>

    </div>
    <div class="bill">
        <a class="pay-info" href="#" style="border-bottom: none;">配送方式<span>物流配送</span></a>
       <#-- <img src="${basePath}/images/qp_sxq.png" class="bil-img" >-->
    </div>
    <div class="bill">
        <#--<a class="pay-info"
           href="${basePath}/tochangeInvoice.htm?invoiceTitle=${invoiceTitle!''}&invoiceType=${invoiceType!''}&addressId=${orderAddress.addressId}&typeId=${typeId!''}&ch_pay=${ch_pay!''}&deliveryPointId=${deliveryPointId!''}&codeNo=<#if coupon??>${coupon.codeNo!''}</#if>">-->
            <a id="pay_info" class="pay-info" style="border-bottom: none;">
            发票信息<span><#if invoiceType??&&invoiceType=="不开发票">不需要发票<#else >纸质发票</#if></span></a>
        <img src="${basePath}/images/qp_sxq.png" class="bil-img">
    </div>

</#if>

    <div class="fp dis">
        <div class="fp_box">
            <div class="sel_btn">
                <ul>
                    <li class="select">开发票</li>
                    <li>不开发票</li>
                </ul>
            </div>
            <div class="invoice">
                <div class="inv_type">
                    <p>发票类型</p>
                    <ul>
                        <li class="select" value="1">纸质发票</li>
                    </ul>
                </div>
                <div class="inv_tt">
                    <p>发票抬头</p>
                    <input type="text" placeholder="请输入抬头" id="title"/>
                </div>
                <div class="inv_cont">
                    <p>发票内容</p>
                    <ul>
                        <li class="select">明细</li>
                    </ul>
                </div>
            </div>

            <div class="fp_btn">
                <#--<a class="cancel">取消</a>-->
                <a class="ok">确定</a>
            </div>
        </div>
    </div>
    <#--<div class="mui-panel order-panel">
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
            <!-- <div class="mc">平台会在24小时内发货，请注意查看！</div>
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
    </div>-->


  <#--  <div class="mui-panel order-panel pros-item">
        <div class="panel-item mui-clearfix">
            <div class="mt-new">纸质发票：<span id="invoiceTitle">${invoiceTitle!'不使用'}</span></div>
            <div class="mc" id="invoiceType">${invoiceType!''}</div>
            <a href="${basePath}/tochangeInvoice.htm?invoiceTitle=${invoiceTitle!''}&invoiceType=${invoiceType!''}&addressId=${orderAddress.addressId}&typeId=${typeId!''}&ch_pay=${ch_pay!''}&deliveryPointId=${deliveryPointId!''}&codeNo=<#if coupon??>${coupon.codeNo!''}</#if>">
                <i class="fa fa-angle-right"></i>
            </a>
        </div>
    </div>-->



    <div class="mui-panel" style="padding:15px 0;display:none;">
        <div class="panel-item mui-clearfix panelMes" style="width:90%;margin:0 5%;">

        <!--<div class="sitem-l" style="width:4em;line-height: 2em;">
            优惠券
        </div>-->
            <p class="coup1">优惠券<label class="lab1"><span>${map.couponlist?size}</span> 张可用</label></p>
            <p class="coup2" style="height:40px;"><label>选择优惠券：</label><select  onchange="changeCoupon(this)">
                <option value="">请选择优惠券</option>
            <#list  map.couponlist as coupon>
                <option value="${coupon.codeNo}###<#if coupon.couponRulesType=='1'>${coupon.couponStraightDown.downPrice}
                    </#if><#if coupon.couponRulesType=='2'>${coupon.couponFullReduction.reductionPrice}###${coupon.couponFullReduction.fullPrice}
                        </#if>">
                    <#if coupon.couponRulesType=='1'>
                    ${coupon.couponName}(直减${coupon.couponStraightDown.downPrice})
                    </#if>
                    <#if coupon.couponRulesType=='2'>
                    ${coupon.couponName}(满${coupon.couponFullReduction.fullPrice}
                        减${coupon.couponFullReduction.reductionPrice})
                    </#if></option>
            </#list>
            </select><br/>
              <!--<a href="#">点此输入优惠券码</a>-->
            </p>

            <#--<#if (map.couponlist??&&map.couponlist?size>0)><i>${map.couponlist?size}张可用</i>
            <select  onchange="changeCoupon(this)">
                <option value="">选择优惠券</option>
                <#list  map.couponlist as coupon>
                    <option value="${coupon.codeNo}###<#if coupon.couponRulesType=='1'>${coupon.couponStraightDown.downPrice}
                    </#if><#if coupon.couponRulesType=='2'>${coupon.couponFullReduction.reductionPrice}###${coupon.couponFullReduction.fullPrice}
                        </#if>">
                        <#if coupon.couponRulesType=='1'>
                    ${coupon.couponName}(直减${coupon.couponStraightDown.downPrice})
                    </#if>
                        <#if coupon.couponRulesType=='2'>
                        ${coupon.couponName}(满${coupon.couponFullReduction.fullPrice}
                            减${coupon.couponFullReduction.reductionPrice})
                        </#if></option>
                </#list>
            </select><#else><i style="position:relative;top:.4em;">无</i></#if>-->
        <#--<a style="display:inline" href="${basePath}/tocouponlist.htm?addressId=${orderAddress.addressId}&typeId=${typeId!''}&ch_pay=${ch_pay!''}&deliveryPointId=${deliveryPointId!''}&codeNo=<#if coupon??>${coupon.codeNo!''}</#if>">-->

        <#--<div class="sitem-r">选择</div>-->


        <#-- <i class="fa fa-angle-right"></i>-->
        <#--</a>-->
        </div>

        <!--<div class="panel-item mui-clearfix switch-item"<#if bossNum==0>style="display:none"</#if> >
            <input type="hidden" value="${map.customerPoint!'0'}" id="pointSum">
            <input type="hidden" value="${map.pointSet!'0'}" id="pointSet">

            <div class="sitem-l">
                您有${map.customerPoint!'0'}积分
            <span class="mc">
            本次可用<b id="muqianjifen" style="font-size:16px"></b>积分，使用
            <input class="integral-input" type="text" name="point" id="point" onblur="checkjifen()"/>
            积分
            </span> <br/><span id="errorPoint" class="mc" style="color:red;"></span>
            </div>
            <div class="sitem-l">
                <span class="mc"> 每10积分兑换人民币￥${map.pointSet}元。</span>
            </div>
        </div>-->
    </div>
    <div class="mui-panel" style="padding:15px 0;display: none;">
        <div class="panel-item mui-clearfix panelMes" style="width:90%;margin:0 5%;">
            <div class="points" >
                <input type="hidden" value="${map.customerPoint!'0'}" id="pointSum">
                <#--每消费10积分兑换多少金额-->
                <input type="hidden" value="${map.pointSet!'0'}" id="pointSet">
                <p class="coup1">积分<label class="lab1"><span>${map.customerPoint!'0'}</span> 积分可用</label></p>
                <p class="coup2">
                    <label id="labb">本次使用：</label>
                    <label class="lab2"><span onclick="jifen('0');">-100</span><input type="text" name="point" id="point" onblur="checkjifen();" placeholder="输入要兑换积分"/><span onclick="jifen('1');">+100</span></label>
                    <br/>
                    <em id="errorPoint"></em>
                    <a href="javascript:" onclick="jifen('3')">全部兑换</a>
                </p>
            </div>
        </div>
    </div>

    <input type="hidden" value="${map.sumPrice}" id="sumPrice" / >
    <div class="inform">
        <div class="number_info" style="width:90%;margin:0 5%;">
            <span class="info-sp">支付信息</span>
            <p class="numb-a">
                <label>商品总额：</label><span>${map.sumPrice?string("0.00")}</span><br/>
                <label>优惠券:</label><span id="couponprice">-￥0.00</span><br/>
                <label>返现：</label><span>-￥0.00</span>
            </p>
            <p class="numb-b">
                <label>运费:</label><span id="ep">￥0.00</span><br/>
                <label>积分：</label><span id="pointPrice" >-￥0.00</span>


            </p>
        </div>
    </div>
    <#--<div class="mui-panel order-panel total-item">
        <div class="mui-clearfix">
            <div class="sitem-l">商品金额</div>
            <div class="sitem-r">${map.sumPrice?string("0.00")}</div>
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

        <div class="mui-clearfix" style="height:100px">


        </div>

        <input type="hidden"/>

        <div class="pay-bar mui-clearfix">
            <input type="hidden" id="payPrice2"/>

            <div class="pay-cont">
                实付款：&yen;&nbsp;
                <span id="payPrice"></span>
            </div>
            <button class="mui-btn mui-btn-danger" type="button" onclick="checkForm()">提交订单</button>
        </div>
    </div>-->
</form>
<div class="custom">
    <p class="paym-s">实付款：<span id="payPrice">￥2880.00</span></p>
    <a class="cus-a1" href="#" onclick="checkForm()">提交订单</a>

</div>

<script src="${basePath}/js/jquery-1.11.1.min.js"></script>
<script src="${basePath}/js/mui.min.js"></script>
<script src="${basePath}/js/zepto.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script src="${basePath}/js/fastclick.min.js"></script>
<script src="${basePath}/js/jquery.keleyi.js"></script>
<script src="${basePath}/js/publicModel.js"></script>

<script>
    var isflag = true;
    var subNum = 0;
    function checkForm() {
        if (isflag && subNum == 0) {
            subNum = subNum + 1;
            $("#subForm").submit();
        }
    }
    /*$("#payselect").bind("click",function(){
        var status = $("#showPayInfo").css("display");
        if(status == "none"){
            $("#showPayInfo").css("display","block");
        } else {
            $("#showPayInfo").css("display","none");
        }
    });*/
    $("#pay_info").click(function(){
       $(".fp").show();
    });
    $(".sel_btn li").click(function(){
        //$(".sel_btn li").removeAttr("style");
        //$(this).css({"border":"1px solid #f6ac00","color":"#f6ac00"});
        $(".sel_btn li").removeClass("select");
        $(this).addClass("select");
       var n = $(this).index();
        if(n){
            $(".invoice").hide();
        }else{
            $(".invoice").show();
        }
    });

    $(".ok").click(function(){
        $(".sel_btn li").each(function(index,element){
            if($(this).hasClass("select")){
                if(index ==0){
                    $("#invoiceType").val($($(".inv_type li.select")[0]).attr("value"));
                    $("#invoiceTitle").val($("#title").val());
                    $("#invoiceContent").val($($(".inv_cont li.select")[0]).text());
                    $("#pay_info span").html($($(".inv_type li.select")[0]).text());
                }
                if(index ==1){
                    $("#invoiceType").val('0');
                    $("#invoiceTitle").val('');
                    $("#invoiceContent").val('');
                    $("#pay_info span").html("不需要发票");
                }
            }
        });
        $(".fp").hide();
    });

    /*$(".inpbox").click(function(){
       location.href="${basePath}/customer/showOrderAddressList.htm?needRetrun=0";
    });*/


    $("#onlinePay").bind("click",function(){
        $('input[name="ch_pay"]').val("1");
    });
    $("#outlinePay").bind("click",function(){
        $('input[name="ch_pay"]').val("2");
    });
    //使用积分兑换 ,0是-100,1是+100
    function jifen(type){
        var point = $("#point").val();//使用的积分
        var pointSum = parseInt($("#pointSum").val());//用户的总积分
        var newPoint=0;
        if(pointSum<100){
            if(point!=''){
                $("#point").val('');
                checkjifen();
            }
            $("#errorPoint").html("您的积分不足！");
            return false;
        }
        if(type =='3'){
            if(pointSum>100){
                newPoint = parseInt(pointSum/100)*100;
            }else{
                newPoint=0;
            }
        }
        if(point!=''){
            var newPoint=parseInt(point);
            if(type=='0'){
                if(newPoint<100){
//                    $("#errorPoint").html("最少使用100积分！");
                    $("#point").val('0');
                    checkjifen();
                    return false;
                }
                newPoint =newPoint-100;
            }else if(type=="1"){
                newPoint=newPoint+100;
                if(newPoint>pointSum){
                    $("#errorPoint").html("您的积分不足！");
                    return false;
                }
            }
        }else if(type=='0'){
            //当前积分为0，点击-100
            $("#errorPoint").html("您的积分不足！");
            return false;
        }else if(type =='1'){
            if(parseInt(pointSum)<100){
                //当前积分为0，点击-100
                $("#errorPoint").html("您的积分不足！");
                return false;
            }else{
                newPoint=100;
            }
        }
        $("#point").val(newPoint);
        $("#errorPoint").html("");
        checkjifen();
    }

    function checkjifen() {
        isflag = true;
        var point = $("#point").val();//使用的积分
        var pointSet = $("#pointSet").val();//积分兑换规则
//        var bossSumPrice = $('#bosssumPrice').val();	//boss商品总金额
        var bossSumPrice = parseFloat($('#bosssumPrice').val());	//实付金额
        var couponprice=$("#couponprice").html();//优惠券的金额
        if(couponprice != "-￥0.00" && couponprice){
            bossSumPrice =Subtr(bossSumPrice, parseFloat(couponprice))
        }
        var ep = $("#ep").html();//运费的金额
        if(ep && ep!='￥0.00'){
            bossSumPrice =accAdd(bossSumPrice, parseFloat(ep))
        }
        //可以使用的最大积分
        var keyishiyong = accDiv(accMul(bossSumPrice, 100), pointSet);
        keyishiyong = parseInt(parseInt(accDiv(parseInt(keyishiyong), 100)) + "0");

        var pointSum = $("#pointSum").val();//用户的总积分
//        var payPrice = $("#payPrice").html();//支付金额
        var pointPrice = point * pointSet; //pointSet是10积分0.1
        if (!isNaN(point) && point >= 0) {
            if (Subtr(pointSum, point) < 0) {
                isflag = false;
                //积分不足
                $("#errorPoint").html("您的积分不足！");
                $("#point").val("");
                $("#pointPrice").html("-0.00");
                $("#payPrice").html(bossSumPrice);
            } else if (point % 100 != 0) {
                isflag = false;
                $("#errorPoint").html('注：兑换的积分必须为100的倍数!');
                $('#point').val('');
                $("#pointPrice").html("-0.00");
                $("#payPrice").html(bossSumPrice);
            }
            else if (Subtr(bossSumPrice, accDiv(pointPrice, 10)) < 0) {
                isflag = false;
                //积分兑换金额大于订单金额
                $("#errorPoint").html("注：此次订单价格最多可以使用【" + keyishiyong + "】的积分！");
                $("#point").val("");
                $("#pointPrice").html("-0.00");
                $("#payPrice").html(bossSumPrice);
            } else {
                $("#errorPoint").html("");
                $("#pointPrice").html("-" + accDiv(pointPrice, 10));
                $("#payPrice").html(Subtr(bossSumPrice, accDiv(pointPrice, 10)));
            }
        } else {
            isflag = false;
            $("#errorPoint").html("请填写正整数！");
            $("#point").val("");
            $("#pointPrice").html("-0.00");
            $("#payPrice").html(bossSumPrice);
        }
        return isflag;

    }
    $(function () {

        var pointSet = $("#pointSet").val();//积分兑换规则
        var bossSumPrice = $('#bosssumPrice').val();	//boss商品总金额
        var keyishiyong = accDiv(accMul(bossSumPrice, 100), pointSet);
        keyishiyong = parseInt(parseInt(accDiv(parseInt(keyishiyong), 100)) + "0");
        var pointSum = $("#pointSum").val();//用户的总积分
//        if (bossSumPrice > 0) {
//            if (pointSum < keyishiyong) {
//                $('#muqianjifen').html(pointSum);          //设置目前可用的积分
//            } else {
//                $('#muqianjifen').html(keyishiyong);          //设置目前可用的积分
//            }
//        } else {
//            $('#muqianjifen').html(0);          //设置目前可用的积分
//        }


        var yfprice = 0;
        //boss运费
        var bossyfprice = 0;
        var payprice = $("#sumPrice").val();
        var addsId = $("#addressId").val();
        var boxs = [];
        //获取订单数量
        $('input[name="shoppingCartId"]').each(function () {
            boxs.push(parseInt($(this).val()));
        });

        //计算此次购买商品总运费价格
        $.ajax({
            type: "post",
            url: "${basePath}/getnewMexpressprice.htm",
            async: false,
            data: {addressId: addsId, shopIds: boxs},
            success: function (data) {
                //key值为Map的键值
                $.each(data, function (key, value) {
                    if (data != null && data != '') {
                        //总运费
                        if (key == "freightmoney") {
                            yfprice = accAdd(value, yfprice);
                        }
                        //boss运费
                        if (key == "bossfreight") {
                            bossyfprice = accAdd(value, bossyfprice);
                        }
                    }
                });
            }
        });
        var paysumprice = 0;
        var thirdprice = Subtr(yfprice, bossyfprice);
        //上门自提时免运费
        if ($("#typeId").val() == 1) {
            $("#ep").html("+" + thirdprice);
            $("#payPrice").html(accAdd(payprice, thirdprice));
//            $("#payPrice2").val(accAdd(payprice, thirdprice));
            paysumprice = accAdd(payprice, thirdprice);
        } else {
            $("#ep").html("+" + yfprice);
            $("#payPrice").html(accAdd(payprice, yfprice));
//            $("#payPrice2").val(accAdd(payprice, yfprice));
            paysumprice = accAdd(payprice, yfprice);
        }
        //使用购物券类型 1:直降 2:满减
        var couponType = $("#couponType").val();
        //购物券直降金额
        var downPrice = $("#downPrice").val();
        //购物券满减金额
        var reductionPrice = $("#reductionPrice").val();
        if (couponType == 1) {
            $("#couponprice").html("-" + downPrice);
            $("#payPrice").html(Subtr(paysumprice, downPrice));
//            $("#payPrice2").val(Subtr(paysumprice, downPrice));
        }
        if (couponType == 2) {
            $("#couponprice").html("-" + reductionPrice);
            $("#payPrice").html(Subtr(paysumprice, reductionPrice));
//            $("#payPrice2").val(Subtr(paysumprice, reductionPrice));
        }


        $.ajax({
            type: "post",
            url: "${basePath}/customer/getAllAddress.htm",
//            async: false,
//            data: {addressId: addsId, shopIds: boxs},
            success: function (data) {
                if(data.success && data.datas){
                    var address =data.datas;
                    for(var i =0;i < address.length;i++){
                        var html ='<div class="rep-addr">'+
                                '<p class="rep-p1">'+
                                '<span class="rep-sp1">'+address[i].addressName+'</span>'+
                                '<span class="rep-sp2">'+address[i].addressMoblie+'</span></br>'+
                                '<span class="rep-sp3">'+address[i].province.provinceName+' '+address[i].city.cityName+' '+address[i].district.districtName +' '+address[i].addressDetail+'</span>'+
                                '</p>'+
                                '<p class="add-p">'+
                                '<input type="radio" class="radio-btn" name="radioName" value="'+address[i].addressId+'"><label >收货地址</label>'+
                                '</p>'+
                                '</div>';
                        $(".sub_addr").append(html);
                    }
                }
            }
        });

        $(".inpbox").click(function(){
            $(".bg-addr").show();
        });
        $("body").on("click",".radio-btn",function(data){
            $("#addressId").val($(this).val());
            var oDiv =$(this).closest('.rep-addr');
            $('.inpbox').find(".sp1").text($(oDiv).find('.rep-sp1').text());
            $('.inpbox').find(".sp2").text($(oDiv).find('.rep-sp2').text());
            $('.inpbox').find(".sp3").text($(oDiv).find('.rep-sp3').text());
            $(".bg-addr").hide();
        });

    });
    function changeCoupon(obj) {
        var valu = obj.value;
        var sumPri = $('#bosssumPrice').val();
        if(valu==""){
            $("input[name='codeNo']").val("");
            $("#couponprice").text("-"+"0.00");
            $("#payPrice").html(Subtr(sumPri, 0));
//            $("#payPrice2").val(Subtr(sumPri, 0));
        }
        if(valu !=""&&valu.split("###").length==2){
            $("input[name='codeNo']").val(valu.split("###")[0]);
           // $("couponNo").val(valu.split("###")[0]);
            var pr = valu.split("###")[1];
            $("#couponprice").text("-"+pr);
            $("#payPrice").html(Subtr(sumPri, pr));
//            $("#payPrice2").val(Subtr(sumPri, pr));
        }
        if(valu !=""&&valu.split("###").length==3){
            if(Number(valu.split("###")[2])<=Number(sumPri)){
                $("input[name='codeNo']").val(valu.split("###")[0]);
                //$("couponNo").val(valu.split("###")[0]);
                var pr1 = valu.split("###")[1];
                $("#couponprice").text("-"+pr1);
                $("#payPrice").html(Subtr(sumPri, pr1));
//                $("#payPrice2").val(Subtr(sumPri, pr1));
            } else {
                $("input[name='codeNo']").val("");
                $("#couponprice").text("-"+"0.00");
                $("#payPrice").html(Subtr(sumPri, 0));
//                $("#payPrice2").val(Subtr(sumPri, 0));
            }

        }
    }
</script>
</body>

<#--<script src="${basePath}/js/customer/receiptAddr.js"></script>-->
</html>