<#include "../include/common.ftl">
<@htmlHead title='${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/index_two/css/header.css" />
<style type="text/css">
    .area_text {margin-top: 5px;}
    .locate_cont{top:30px;}
</style>
<script type="text/javascript" src="${basePath}/js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="${basePath}/js/cloud-zoom.1.0.2.min.js"></script>
<script type="text/javascript">
    function checkOne(obj){
        //获取外部的DIV 是否是促销的商品
        var codexType = $(obj).parents(".marketgroup").attr("attr-codextype");
        if(codexType!='0'){
            if(obj.checked){

                if(codexType=='1'){
                    var youhui = 0 ;
                    var xiaoji = 0;
                    $(obj).parents(".marketgroup").find(".cart_goods").each(function(){
                        var man= $(this).find(".count").find(".marketPrice").val();
                        var jian = $(this).find(".count").find(".text").val();
                        var onesumprice =$(this).find(".smprice").val();
                        youhui = accAdd(youhui,accMul(man,jian));
                        xiaoji = accAdd(onesumprice,xiaoji);
                    });
                    $(obj).parents(".marketgroup").find(".youhui").val(youhui);

                    $(obj).parents(".marketgroup").find(".xiaoji").val(xiaoji);

                }
//                    else if(codexType=='10'){//团购
//                        var youhui = 0 ;
//                        var xiaoji = 0;
//                        $(obj).parents(".marketgroup").find(".cart_goods").each(function(){
//                            //金额
//                            var man= $(this).find(".count").find(".groupPrice").val();
//                            //件数
//                            var jian = $(this).find(".count").find(".text").val();
//                            var onesumprice =$(this).find(".smprice").val();
//                            youhui = accAdd(youhui,accMul(man,jian));
//                            xiaoji = accAdd(onesumprice,xiaoji);
//                        })
//                        $(obj).parents(".marketgroup").find(".youhui").val(youhui);
//
//                        $(obj).parents(".marketgroup").find(".xiaoji").val(xiaoji);
//                    }

                else if(codexType=='11'){//抢购
                    var youhui = 0 ;
                    var xiaoji = 0;
                    $(obj).parents(".marketgroup").find(".cart_goods").each(function(){
                        //金额
                        var man= $(this).find(".count").find(".rushPrice").val();
                        //件数
                        var jian = $(this).find(".count").find(".text").val();
                        var onesumprice =$(this).find(".smprice").val();
                        youhui = accAdd(youhui,accMul(man,jian));
                        xiaoji = accAdd(onesumprice,xiaoji);
                    });;
//                        折扣的优惠不算在返现中 edit by 付陈林 2015年12月9日
//                        $(obj).parents(".marketgroup").find(".youhui").val(youhui);
                    $(obj).parents(".marketgroup").find(".youhui").val(0);
                    $(obj).parents(".marketgroup").find(".xiaoji").val(xiaoji);
                }


                else if(codexType=='5'){
                    var mj_sumprice = 0;
                    var mj_end=0;
                    //满减
                    $(obj).parents(".marketgroup").find(".mjchecked").each(function(){
                        //判断是否选中
                        if($(this)[0].checked){
                            mj_sumprice = accAdd($(this).parents(".cart_goods").find(".smprice").val(),mj_sumprice);
                        }
                    });


                    $(obj).parents(".marketgroup").find(".manjian_reducePrice").each(function(){
                        var man = $(this).val().split(",")[0];
                        var jian = $(this).val().split(",")[1];
                        if(Subtr(mj_sumprice,man)>=0){
                            mj_end = jian;
                        }

                    });
                    $(obj).parents(".marketgroup").find(".xiaoji").val(mj_sumprice);
                    $(obj).parents(".marketgroup").find(".youhui").val(mj_end);

                }else if(codexType=='8'){
                    //满折
                    var mz_sumprice = 0;
                    var mz_end=0;
                    $(obj).parents(".marketgroup").find(".mjchecked").each(function(){
                        //判断是否选中
                        if($(this)[0].checked){
                            mz_sumprice = accAdd($(this).parents(".cart_goods").find(".smprice").val(),mz_sumprice);
                        }
                    });

                    $(obj).parents(".marketgroup").find(".manzhe_fullbuyDiscount").each(function(){
                        var man = $(this).val().split(",")[0];
                        var zhe = $(this).val().split(",")[1];
                        if(Subtr(mz_sumprice,man)>=0){
                            mz_end = accMul(mz_sumprice,Subtr(1,zhe));
                        }

                    });
                    $(obj).parents(".marketgroup").find(".xiaoji").val(mz_sumprice);
                    $(obj).parents(".marketgroup").find(".youhui").val(mz_end);

                }else if(codexType=='-1'){
                    //-1代表是套装
                    var num = $(obj).parents(".cart_item").find(".decrement").next().val();
                    var xiaoji = 0 ;
                    $(obj).parents(".marketgroup").find(".cart_item_goods").each(function(){
                        var isprice = accMul($(this).find(".oneprice").val(),num);
                        xiaoji = accAdd(xiaoji,isprice);
                    });
                    var groupPreferamount = $(obj).parents(".cart_item").find(".groupPreferamount").val();
                    var youhui = accMul(num,groupPreferamount);
                    $(obj).parents(".marketgroup").find(".xiaoji").val(xiaoji);
                    $(obj).parents(".marketgroup").find(".youhui").val(youhui);
                }
            }else{
                if(codexType=='1'){
                    var man= $(obj).parents(".cart_goods").find(".marketPrice").val();
                    var jian = $(obj).parents(".cart_goods").find(".text").val();
                    var you= $(obj).parents(".marketgroup").find(".youhui").val();
                    youhui=Subtr(you,accMul(man,jian));
                    $(obj).parents(".marketgroup").find(".youhui").val(youhui);
                    //直降
                    var thisprice=$(obj).parents(".cart_goods").find(".smprice").val();
                    //总计+单品小计
                    var oldxiaoji = $(obj).parents(".marketgroup").find(".xiaoji").val();
                    var xiaoji = Subtr(oldxiaoji,thisprice);
                    $(obj).parents(".marketgroup").find(".xiaoji").val(xiaoji);


                }
//                    else if(codexType=='10'){//团购
//                        var groupPrice= $(obj).parents(".cart_goods").find(".groupPrice").val();
//                        var jian = $(obj).parents(".cart_goods").find(".text").val();
//                        var you= $(obj).parents(".marketgroup").find(".youhui").val();
//                        youhui=Subtr(you,accMul(groupPrice,jian));
//                        $(obj).parents(".marketgroup").find(".youhui").val(youhui);
//                        //团购
//                        var thisprice=$(obj).parents(".cart_goods").find(".smprice").val();
//                        //总计+单品小计
//                        var oldxiaoji = $(obj).parents(".marketgroup").find(".xiaoji").val();
//                        var xiaoji = Subtr(oldxiaoji,thisprice);
//                        $(obj).parents(".marketgroup").find(".xiaoji").val(xiaoji);
//
//                    }

                else if(codexType=='11'){//抢购
                    //edit by 付陈林，抢购无优惠价格 2015年12月9日
//                        var rushPrice= $(obj).parents(".cart_goods").find(".rushPrice").val();
                    var rushPrice= 0;
//                        edit end
                    var jian = $(obj).parents(".cart_goods").find(".text").val();

                    var you= $(obj).parents(".marketgroup").find(".youhui").val();
                    youhui=Subtr(you,accMul(rushPrice,jian));
                    $(obj).parents(".marketgroup").find(".youhui").val(youhui);
                    //抢购
                    var thisprice=$(obj).parents(".cart_goods").find(".smprice").val();
                    //总计+单品小计
                    var oldxiaoji = $(obj).parents(".marketgroup").find(".xiaoji").val();
                    var xiaoji = Subtr(oldxiaoji,thisprice);
                    $(obj).parents(".marketgroup").find(".xiaoji").val(xiaoji);

                }

                else if(codexType=='5'){
                    //满减
                    var mj_sumprice = 0;
                    var mj_end=0;
                    //满减
                    $(obj).parents(".marketgroup").find(".mjchecked").each(function(){
                        //判断是否选中
                        if($(this)[0].checked){
                            mj_sumprice = accAdd($(this).parents(".cart_goods").find(".smprice").val(),mj_sumprice);
                        }
                    });


                    $(obj).parents(".marketgroup").find(".manjian_reducePrice").each(function(){
                        var man = $(this).val().split(",")[0];
                        var jian = $(this).val().split(",")[1];
                        if(Subtr(mj_sumprice,man)>=0){
                            mj_end = jian;
                        }

                    });
                    $(obj).parents(".marketgroup").find(".xiaoji").val(mj_sumprice);
                    $(obj).parents(".marketgroup").find(".youhui").val(mj_end);




                }else if(codexType=='8'){
                    //满折
                    var mz_sumprice = 0;
                    var mz_end=0;
                    $(obj).parents(".marketgroup").find(".mjchecked").each(function(){
                        //判断是否选中
                        if($(this)[0].checked){
                            mz_sumprice = accAdd($(this).parents(".cart_goods").find(".smprice").val(),mz_sumprice);
                        }
                    });

                    $(obj).parents(".marketgroup").find(".manzhe_fullbuyDiscount").each(function(){
                        var man = $(this).val().split(",")[0];
                        var zhe = $(this).val().split(",")[1];
                        if(Subtr(mz_sumprice,man)>=0){
                            mz_end = accMul(mz_sumprice,Subtr(1,zhe));
                        }

                    });
                    $(obj).parents(".marketgroup").find(".xiaoji").val(mz_sumprice);
                    $(obj).parents(".marketgroup").find(".youhui").val(mz_end);

                }else if(codexType=='-1'){
                    //-1代表是套装
                    $(obj).parents(".marketgroup").find(".xiaoji").val(0);
                    $(obj).parents(".marketgroup").find(".youhui").val(0);
                }


            }
        }else{

            $(obj).parents(".marketgroup").find(".mjchecked").each(function(){
                //判断是否选中
                if($(this)[0].checked){
                    var oldxiaoji = $(obj).parents(".marketgroup").find(".smprice").val();
                    $(obj).parents(".marketgroup").find(".xiaoji").val(oldxiaoji);
                }else{
                    $(obj).parents(".marketgroup").find(".xiaoji").val(0);
                }
            });

        }
        lastsum();
    }
    function lastsum(){
        var zongji = 0;
        var fanxian = 0;
        var allcount = 0;
        $(".xiaoji").each(function(){
            zongji = accAdd(zongji,$(this).val());
        });
        $(".youhui").each(function(){
            fanxian = accAdd(fanxian,$(this).val());
        });
        $(".mjchecked").each(function(){
            if($(this)[0].checked){
                allcount = accAddInt($(this).parents(".cart_goods").find(".decrement").next().val(),allcount);
            }
        });
        $(".allcount").html(allcount);
        $(".zongji").html("￥"+zongji);
        $(".fanxian").html("-￥"+fanxian);
        $(".payPrice").html("￥"+Subtr(zongji,fanxian));
    }
</script>
</@htmlHead>
<@htmlBody>
<#include "../index/newtop7.ftl">
<div id="shopCartContainer" class="container" style="margin-bottom: 20px !important;">
    <input type="hidden" id="currentProvince" value="${chProvince!''}" />
    <input type="hidden" id="basePath" value="${basePath}" />
    <div class = "logo fl head2" style="margin-top:20px;">
    <#if (topmap.temp)??>
        <#if (topmap.temp.tempId!=10)>
            <a href="${topmap.systembase.bsetAddress}"><img src="${topmap.systembase.bsetLogo}" alt="" style="width:165px;height:40px;"/></a>
        </#if>
    </#if>

    </div>
    <div style="font-family: arial;">
        <div class="head_s mb20">
            <div class="fr w700 pt10">
                <div class="flow_progress1">
                    <ul>
                        <li class="step1">1.查看购物车</li>
                        <li class="step2">2.填写核对订单信息</li>
                        <li class="step3">3.提交订单成功</li>
                    </ul>
                </div>
            </div>

            <div class="cb"></div>
        </div><!-- /head_s -->
        <div class="flow_title">
            <b></b>
        	<span>
            <#if customerId??>
            <#else>
                建议您立即<a href="login.html">登录</a>，以确保顺利进行购物。
            </#if>
            </span>
        </div>
        <div class="cart_box">
            <div class="cart_head">
                <div class="thead t_check">
                    <input type="checkbox" class="check_all" checked="checked" onclick="oncheckAll(this);"/>
                    <label>全选</label>
                </div>
                <div class="thead t_goods">商品</div>
                <div class="thead t_price"><#if vip?? && vip=="1">会员价<#else>零售价</#if></div>
                <div class="thead t_promotion">小计</div>
                <div class="thead t_stock">库存</div>
                <div class="thead t_count">数量</div>
                <div class="thead t_action">操作</div>
            </div><!-- /cart_head -->
            <form name="subForm" id="subForm" action="${basePath}/order/newsuborder.html" method="post">
            <#assign sumPrice=0>
            <#assign fxPrice=0>
            <#assign sumcount=0>

            <#if cartMap??>
                <#if cartMap.thirds??&&cartMap.thirds?size!=0>
                    <!--根据商家进行分组-->
                    <#list cartMap.thirds as third>
                        <div>
                            <div class="activity" style="background:none;border-bottom:2px solid #9E9E9E;line-height:30px;">
                                <div class="fl w500 ml15">
                                    <strong>${third.thirdName}</strong>
                                </div>
                            </div><!-- /activity -->

                            <#if cartMap.marketinglist??&&cartMap.marketinglist?size!=0>
                                <#list cartMap.marketinglist as market>
                                    <#assign zjPrice=0>
                                    <#if market.businessId??&&market.businessId==third.thirdId>
                                        <div attr-codexType="${market.codexType}" class="marketgroup"
                                            <#assign haveGoods="0">
                                            <#if cartMap.shoplist??&&cartMap.shoplist?size!=0>
                                                <#list cartMap.shoplist as cars>
                                                    <#if cars.marketingActivityId??&&cars.marketingActivityId!=0&&cars.marketingActivityId==market.marketingId>
                                                        <#assign haveGoods="1">
                                                    <#elseif cars.goodsGroupId??&&cars.goodsGroupId!=0&&cars.goodsGroupId==market.marketingId>
                                                        <#assign haveGoods="1">
                                                    <#--<#elseif cars.marketingId??&&cars.marketingId!=0&&cars.marketingId==market.marketingId>
                                                        <#assign haveGoods="1">-->
                                                    </#if>
                                                </#list>
                                            </#if>
                                            <#if haveGoods=="0">
                                             style="display:none;"
                                            </#if>
                                                >
                                            <!--直降-->
                                            <#if market.codexType=='1'>
                                                <div class="activity">
                                                    <div class="fl w500 ml15"   style="height:31px; line-height:31px;overflow:hidden;">
                                                    ${market.marketingName}  直降<#if vip?? && vip == "1">${market.priceOffMarketing.offVipValue}<#else>${market.priceOffMarketing.offValue}</#if>元;
                                                        <input type="hidden" value="<#if vip?? && vip == "1">${market.priceOffMarketing.offVipValue}<#else>${market.priceOffMarketing.offValue}</#if>" id="zhijiang_offValue"/>
                                                    </div>
                                                    <div class="fl">
                                                        <!--<strong>￥5196.00</strong>-->
                                                    </div>
                                                </div><!-- /activity -->
                                            </#if>

                                            <!--满减-->
                                            <#if market.codexType=='5'>
                                                <div class="activity">
                                                    <div class="fl w500 ml15"   style="height:31px; line-height:31px;overflow:hidden;">
                                                    ${market.marketingName}
                                                        <#list market.fullbuyReduceMarketings as fr>
                                                            <#if vip?? && vip == "1">
                                                                满 ${fr.vipFullPrice}减${fr.vipReducePrice}元 &nbsp;
                                                                <input type="hidden" value="${fr.vipFullPrice},${fr.vipReducePrice}" class="manjian_reducePrice"/>
                                                            <#else>
                                                                满 ${fr.fullPrice}减${fr.reducePrice}元 &nbsp;
                                                                <input type="hidden" value="${fr.fullPrice},${fr.reducePrice}" class="manjian_reducePrice"/>
                                                            </#if>
                                                        </#list>
                                                    </div>
                                                    <div class="fl">
                                                        <!--<strong>￥5196.00</strong>-->
                                                    </div>
                                                </div><!-- /activity -->
                                            </#if>
                                            <!--满折-->
                                            <#if market.codexType=='8'>
                                                <div class="activity">
                                                    <div class="fl w500 ml15"  style="height:31px; line-height:31px;overflow:hidden;">
                                                    ${market.marketingName}
                                                        <#list market.fullbuyDiscountMarketings as mz>
                                                            <#if vip?? && vip == "1">
                                                                满 ${mz.vipFullPrice}打 ${mz.vipFullbuyDiscount*10}折 &nbsp;
                                                                <input type="hidden" value="${mz.vipFullPrice},${mz.vipFullbuyDiscount}" class="manzhe_fullbuyDiscount"/>
                                                            <#else>
                                                                满 ${mz.fullPrice}打 ${mz.fullbuyDiscount*10}折 &nbsp;
                                                                <input type="hidden" value="${mz.fullPrice},${mz.fullbuyDiscount}" class="manzhe_fullbuyDiscount"/>
                                                            </#if>
                                                        </#list>
                                                    </div>
                                                    <div class="fl">
                                                        <!--<strong>￥5196.00</strong>-->
                                                    </div>
                                                </div><!-- /activity -->
                                            </#if>

                                            <#--团购促销-->
                                            <#if market.codexType=='10'>
                                                <div class="activity">
                                                    <div class="fl w500 ml15">
                                                        <input type="hidden" value="<#if vip?? && vip == "1">${market.groupon.grouponVipDiscount}<#else>${market.groupon.grouponDiscount}</#if>" class="bbb"/>
                                                        ${market.marketingName}
                                                        <#--去掉折扣显示 add by 付陈林-->
                                                        <#--<#if vip?? && vip == "1">${market.groupon.grouponVipDiscount}<#else>${market.groupon.grouponDiscount}</#if>折团购-->
                                                        <#--edit end-->
                                                    </div>
                                                    <div class="fl">
                                                    </div>
                                                </div><!-- /activity -->
                                            </#if>

                                            <#--抢购促销促销-->
                                            <#if market.codexType=='11'>
                                                <div class="activity">
                                                    <div class="fl w500 ml15" style="height:31px; line-height:31px;overflow:hidden;">
                                                        ${market.marketingName}
                                                        <#--去掉折扣显示 add by 付陈林-->
                                                        <#--<#if vip?? && vip == "1">${(market.rushs[0].rushVipDiscount*10)?string('0.#')}<#else>${(market.rushs[0].rushDiscount*10)?string('0.#')}</#if>折抢购-->
                                                        <#--去掉折扣 end -->
                                                    </div>
                                                    <div class="fl">
                                                    </div>
                                                </div><!-- /activity -->
                                            </#if>


                                            <#assign marPrice=0>
                                            <#if cartMap.shoplist??&&cartMap.shoplist?size!=0>
                                                <#list cartMap.shoplist as cars>
                                                    <#if (cars.marketingActivityId??&&cars.marketingActivityId!=0&&cars.marketingActivityId==market.marketingId)
                                                    || (cars.goodsGroupId??&&cars.goodsGroupId!=0&&cars.goodsGroupId==market.marketingId)
                                                    <#--|| (cars.marketingId??&&cars.marketingId!=0&&cars.marketingId==market.marketingId)-->>

                                                        <#setting number_format="0">  <div class="cart_goods" id="cart_goods_${cars.shoppingCartId}">
                                                        <div class="cart_item">
                                                            <div class="cell g_check">
                                                                <input type="checkbox" class="g_ckeck mjchecked"  name="box" checked="checked" value="${cars.shoppingCartId}" onclick="checkOne(this);"/>
                                                            </div>
                                                            <div class="cell g_goods">
                                                                <div class="img">
                                                                    <a href="${basePath}/item/${cars.goodsDetailBean.productVo.goodsInfoId}.html" target="_blank" title="${basePath}/item/${cars.goodsDetailBean.productVo.goodsInfoId}.html"><img style="width:50px;height:50px;" title="${cars.goodsDetailBean.productVo.productName}" alt="${cars.goodsDetailBean.productVo.productName}" src="<#if cars.goodsDetailBean.productVo.goodsInfoImgId??>${cars.goodsDetailBean.productVo.goodsInfoImgId}</#if>" /></a>
                                                                </div>
                                                                <div class="name">
                                                                    <a href="${basePath}/item/${cars.goodsDetailBean.productVo.goodsInfoId}.html" target="_blank" title="${basePath}/item/${cars.goodsDetailBean.productVo.goodsInfoId}.html">${cars.goodsDetailBean.productVo.productName}</a>
                                                                </div>
                                                                <div class="cb"></div>
                                                                <div class="gift mt10">
                                                                    <!--<p class="light">[赠品] 罗凡尼 非凡透明系列手机保护膜 适用于苹果5C+5S+5</p>
                                                                    <p class="light">[赠品] 奇克摩克 TPU硅胶自带防尘塞手机壳/保护壳 适用于苹果iPhone5s 白色</p>-->
                                                                </div>
                                                            </div>
                                                            <div class="cell g_price">

                                                                <#setting number_format="0.00">
                                                                <#--后台已经处理过的实际金额-->
                                                                <#if vip??&&vip=="1">
                                                                    <#assign price=cars.goodsDetailBean.productVo.goodsInfoVipPrice>
                                                                <#else>
                                                                    <#assign price=cars.goodsDetailBean.productVo.goodsInfoPreferPrice>
                                                                </#if>

                                                                ￥${price}

                                                            </div>

                                                            <div class="cell g_promotion">

                                                                <#assign activeFlag="0"/>
                                                                <#assign oneprice="0"/>
                                                                <!--判断参与的促销-->
                                                                <#if (cars.marketingActivityId??&&cars.marketingActivityId!=0)
                                                                || (cars.goodsGroupId??&&cars.goodsGroupId!=0)
                                                                <#--|| (cars.marketingId??&&cars.marketingId!=0)-->>

                                                                    <#if cars.marketingList??&&cars.marketingList?size!=0>
                                                                        <!--循环促销-->
                                                                        <#list cars.marketingList as market>

                                                                            <!--判断是否是相同促销，并且是折扣促销-->
                                                                            <!--直降-->
                                                                            <#if market.codexType=='1'&& cars.marketingActivityId?? && market.marketingId?? && cars.marketingActivityId==market.marketingId>
                                                                                <#assign oneprice="${price?number}"/>
                                                                                <#assign manPrice="${price?number*cars.goodsNum}"/>
                                                                                <#assign marPrice="${marPrice?number+manPrice?number}">
                                                                                <#if vip?? && vip == "1">
                                                                                    <#assign zjPrice="${zjPrice?number+market.priceOffMarketing.offVipValue?number*cars.goodsNum}">
                                                                                <#else>
                                                                                    <#assign zjPrice="${zjPrice?number+market.priceOffMarketing.offValue?number*cars.goodsNum}">
                                                                                </#if>
                                                                                <#assign smprice="${manPrice}"/>
                                                                            </#if>
                                                                        <#--团购促销-->
                                                                            <#if market.codexType=='10'&&cars.goodsGroupId==market.marketingId>
                                                                                <#assign oneprice="${price?number}"/>
                                                                                <#assign manPrice="${price?number*cars.goodsNum}"/>
                                                                                <#assign marPrice="${marPrice?number+manPrice?number}">
                                                                            <#--优惠的金额加上团购优惠的金额-->
                                                                            <#--<#assign zjPrice="${zjPrice?number+(1-market.groupon.grouponDiscount?number)*manPrice?number}">-->
                                                                                <#assign smprice="${manPrice}"/>
                                                                            </#if>

                                                                        <#--抢购促销-->
                                                                            <#if market.codexType=='11'&&cars.marketingActivityId==market.marketingId>
                                                                                <#assign oneprice="${price?number}"/>
                                                                                <#assign manPrice="${price?number*cars.goodsNum}"/>
                                                                                <#assign marPrice="${marPrice?number+manPrice?number}">
                                                                            <#--优惠的金额加上抢购的金额-->
                                                                                <#--如果是抢购在返现中不体现与实际商品价格的差价，edit by 付陈林 2015年12月7日。-->
                                                                                <#--<#if vip?? && vip == "1">-->
                                                                                    <#--<#assign zjPrice="${zjPrice?number+(1-market.rushs[0].rushVipDiscount?number)*manPrice?number}">-->
                                                                                <#--<#else>-->
                                                                                    <#--<#assign zjPrice="${zjPrice?number+(1-market.rushs[0].rushDiscount?number)*manPrice?number}">-->
                                                                                <#--</#if>-->
                                                                                <#if vip?? && vip == "1">
                                                                                    <#assign zjPrice="0">
                                                                                <#else>
                                                                                    <#assign zjPrice="0">
                                                                                </#if>
                                                                                <#--edit end-->
                                                                                <#assign smprice="${manPrice}"/>
                                                                            </#if>

                                                                            <!--满减-->
                                                                            <#if market.codexType=='5'&&cars.marketingActivityId==market.marketingId>
                                                                                <#assign oneprice="${price?number}"/>
                                                                                <#assign manPrice="${price?number*cars.goodsNum}"/>
                                                                                <#assign marPrice="${marPrice?number+manPrice?number}">
                                                                                <#assign smprice="${manPrice}"/>

                                                                            </#if>
                                                                            <!--满折-->
                                                                            <#if market.codexType=='8'&&cars.marketingActivityId==market.marketingId>
                                                                                <#assign oneprice="${price?number}"/>
                                                                                <#assign manPrice="${price?number*cars.goodsNum}"/>
                                                                                <#assign marPrice="${marPrice?number+manPrice?number}">
                                                                                <#assign smprice="${manPrice}"/>

                                                                            </#if>

                                                                        </#list>

                                                                    </#if>
                                                                </#if>
                                                                <input type="hidden" value="${oneprice}" class="oneprice"/>

                                                                <input type="hidden" value="${smprice!''}" class="smprice"/>
                                                                <#setting number_format="0.00">   ￥<span class="pv_smprice">  ${smprice!''}  </span>
                                                                <#setting number_format="0">

                                                            </div>
                                                            <div class="cell g_stock">

                                                                <#if cars.goodsDetailBean.productVo.goodsInfoDelflag='1' || cars.goodsDetailBean.productVo.goodsInfoAdded!='1' >
                                                                    <span class="light" style="color:red;">已下架</span><input type="hidden" class="noProduct" value="0"/>
                                                                <#else>
                                                                    <#if (cars.goodsDetailBean.productVo.goodsInfoStock>0)>
                                                                        <span class="light">有货</span><input type="hidden" class="noProduct" value="1"/>
                                                                    <#else>
                                                                        <span class="light" style="color:red;">无货</span><input type="hidden" class="noProduct" value="0"/>
                                                                    </#if>
                                                                </#if>

                                                            </div>
                                                            <div class="cell g_count">
                                                                <div class="count">
                                                                    <#setting number_format="0.00">
                                                                    <#if market.codexType=='1'&&cars.marketingActivityId==market.marketingId>
                                                                        <#if vip?? && vip == "1">
                                                                            <input type="hidden" value="${market.priceOffMarketing.offVipValue}" class="marketPrice"/>
                                                                        <#else>
                                                                            <input type="hidden" value="${market.priceOffMarketing.offValue}" class="marketPrice"/>
                                                                        </#if>
                                                                    </#if>
                                                                <#--团购-->
                                                                    <#if market.codexType=='10'&&cars.goodsGroupId==market.marketingId>
                                                                        <#if vip?? && vip == "1">
                                                                            <input type="hidden" value="${price?number - market.groupon.grouponVipPrice?number}" class="groupPrice"/>
                                                                        <#else>
                                                                            <input type="hidden" value="${price?number - market.groupon.grouponPrice?number}" class="groupPrice"/>
                                                                        </#if>

                                                                    </#if>
                                                                <#--是否进行抢购-->
                                                                    <#assign isrushing="0">
                                                                <#--抢购-->
                                                                    <#if market.codexType=='11'&&cars.marketingActivityId==market.marketingId>
                                                                        <#if vip?? && vip == "1">
                                                                            <input type="hidden" value="${price?number - market.rushs[0].rushVipPrice?number}" class="rushPrice"/>
                                                                        <#else>
                                                                            <input type="hidden" value="${price?number - market.rushs[0].rushPrice?number}" class="rushPrice"/>
                                                                        </#if>

                                                                        <#assign isrushing="1">
                                                                    </#if>

                                                                    <#setting number_format="0">
                                                                <#--允许购买数量-->
                                                                    <#assign rushflag="0">
                                                                <#--购物车传过来的货品数量-->
                                                                    <#assign rushnum="0">

                                                                    <#if market.codexType=='11'&&cars.marketingActivityId==market.marketingId>
                                                                        <#if market.customerbuynum ?? && cars.goodsNum?number &gt;market.customerbuynum!1>
                                                                            <#assign rushnum="${market.customerbuynum!1}">

                                                                        <#else >
                                                                            <#assign rushnum="${cars.goodsNum}">
                                                                        </#if>

                                                                    <#--抢购限制数量-->
                                                                        <#if market.customerbuynum ?? && cars.goodsDetailBean.productVo.goodsInfoStock?number &gt;market.customerbuynum >
                                                                            <#assign rushflag="${market.customerbuynum!1}">
                                                                        <#else>
                                                                            <#assign rushflag="${cars.goodsDetailBean.productVo.goodsInfoStock}">
                                                                        </#if>
                                                                    <#else>
                                                                        <#assign rushflag="${cars.goodsDetailBean.productVo.goodsInfoStock}">
                                                                        <#assign rushnum="${cars.goodsNum}">
                                                                    </#if>
                                                                    <a href="javascript:void(0);" class="decrement" onclick="mit(this,${cars.shoppingCartId});">-</a>
                                                                    <input type="text" class="text w30"  oldgoodsnum="${cars.goodsNum}" value="${rushnum}" attr-maxnum="${rushflag}"
                                                                           onblur="opblur(this,${cars.shoppingCartId});"/>
                                                                    <#assign sumcount="${sumcount?number+cars.goodsNum?number}">
                                                                    <a href="javascript:void(0);" class="increment" onclick="add(this,${cars.shoppingCartId});">+</a>
                                                                </div>

                                                                <div class="red">

                                                                    <#if market.codexType=='11'&&cars.marketingActivityId==market.marketingId>
                                                                        <#if market.customerbuynum ?? && market.customerbuynum &lt;=0>
                                                                            达到限制购买件数
                                                                            <input type="hidden" value="1" class="rushlimit"/>
                                                                        <#else>
                                                                            <#if  cars.goodsDetailBean.productVo.goodsInfoStock<=20 >

                                                                                仅剩${cars.goodsDetailBean.productVo.goodsInfoStock}件！
                                                                            </#if>
                                                                        </#if>
                                                                    <#else>
                                                                        <#if  cars.goodsDetailBean.productVo.goodsInfoStock<=20 >

                                                                            仅剩${cars.goodsDetailBean.productVo.goodsInfoStock}件！
                                                                        </#if>
                                                                    </#if>

                                                                </div>

                                                            </div>

                                                            <div class="cell g_action">
                                                                <div>
                                                                    <a class="g_delete" href="javascript:void(0);" onclick="del(${cars.shoppingCartId},${cars.goodsInfoId})">删除</a>
                                                                </div>

                                                                <div class="">
                                                                    <a class="change_promotion" href="javascript:void(0);"
                                                                       <#if isrushing=='1'>style="display: none" </#if>
                                                                            >修改优惠</a>
                                                                    <div class="dialog promotion_dialog">
                                                                        <div class="dialog-outer">
											                                    <span class="dialog-bg dialog-bg-n">
											                                    </span>
											                                    <span class="dialog-bg dialog-bg-ne">
											                                    </span>
											                                    <span class="dialog-bg dialog-bg-e">
											                                    </span>
											                                    <span class="dialog-bg dialog-bg-se">
											                                    </span>
											                                    <span class="dialog-bg dialog-bg-s">
											                                    </span>
											                                    <span class="dialog-bg dialog-bg-sw">
											                                    </span>
											                                    <span class="dialog-bg dialog-bg-w">
											                                    </span>
											                                    <span class="dialog-bg dialog-bg-nw">
											                                    </span>
                                                                            <div class="dialog-inner">
                                                                                <div class="dialog-toolbar clearfix">
                                                                                    <a class="dialog-close" href="javascript:void(0);" title="关闭" onclick="$('.promotion_dialog').hide();">
                                                                                        关闭
                                                                                    </a>
                                                                                    <h3 class="dialog-title">
                                                                                        请选择一项优惠
                                                                                    </h3>
                                                                                </div>
                                                                                <div class="dialog-content clearfix">
                                                                                    <div class="p10 tc w300">
                                                                                        <label>活动优惠：</label>
                                                                                        <select class="chooseMarket" name="chooseMarket">
                                                                                            <option value="0">不使用活动优惠</option>
                                                                                            <#setting number_format="0">
                                                                                            <#if cars.marketingList??&&cars.marketingList?size!=0>
                                                                                                <!--循环促销-->
                                                                                                <#list cars.marketingList as market>
                                                                                                    <#if market.codexType!='15'&&market.codexType!='12'&& market.codexType!='10'>
                                                                                                        <#if market.codexType=='5' || market.codexType=='8' || market.codexType=='1' || market.codexType=='11'>
                                                                                                            <option <#if cars.marketingActivityId ==market.marketingId > selected="selected"</#if> value="${market.marketingId}">${market.marketingName}</option>
                                                                                                        <#else >
                                                                                                            <option <#if cars.marketingId ==market.marketingId > selected="selected"</#if> value="${market.marketingId}">${market.marketingName}</option>
                                                                                                        </#if>
                                                                                                    </#if>
                                                                                                </#list>
                                                                                            </#if>

                                                                                        </select>
                                                                                    </div>
                                                                                    <div class="tc p15">
                                                                                        <a class="red_btn" href="javascript:void(0);" onclick="changemarketing(this,${cars.shoppingCartId},${cars.goodsGroupId!0});">确定</a>
                                                                                        <a class="light_btn2" href="javascript:void(0);" onclick="$('.promotion_dialog').hide();">取消</a>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div><!-- /dialog -->
                                                                </div>
                                                            </div>
                                                            <div class="cb"></div>
                                                        </div>
                                                    </div><!-- /cart_goods -->
                                                        <#setting number_format="0.00">
                                                    </#if>

                                                </#list>

                                            </#if>

                                            <input type="hidden" class="xiaoji" value="${marPrice}"/>

                                            <!--判断满减-->

                                            <#assign yhprice=zjPrice>
                                            <!--满减-->
                                            <#if market.codexType=='5'>

                                                <#list market.fullbuyReduceMarketings as fr>
                                                    <#if vip?? && vip == "1">
                                                        <#if (marPrice?number>=fr.vipFullPrice?number)>
                                                            <#assign yhprice="${fr.vipReducePrice}">
                                                        </#if>
                                                        <!-- 满 ${fr.vipFullPrice}减${fr.vipReducePrice}元 &nbsp;-->
                                                    <#else>
                                                        <#if (marPrice?number>=fr.fullPrice?number)>
                                                            <#assign yhprice="${fr.reducePrice}">
                                                        </#if>
                                                        <!-- 满 ${fr.fullPrice}减${fr.reducePrice}元 &nbsp;-->
                                                    </#if>

                                                </#list>
                                            </#if>
                                            <!--满折-->
                                            <#if market.codexType=='8'>
                                                <#list market.fullbuyDiscountMarketings as mz>
                                                    <#if vip?? && vip == "1">
                                                        <#if (marPrice?number>=mz.vipFullPrice?number)>
                                                            <#assign yhprice="${marPrice?number*(1-mz.vipFullbuyDiscount?number)}">
                                                        </#if>
                                                    <#else>
                                                        <#if (marPrice?number>=mz.fullPrice?number)>
                                                            <#assign yhprice="${marPrice?number*(1-mz.fullbuyDiscount?number)}">
                                                        </#if>
                                                    </#if>

                                                </#list>
                                            </#if>

                                            <input type="hidden" class="youhui" value="${yhprice}"/>
                                            <!--小计累加-->
                                            <#assign sumPrice="${sumPrice?number+marPrice?number}">
                                            <#assign fxPrice="${fxPrice?number+yhprice?number}">
                                        </div>

                                    </#if>
                                </#list>
                            </#if>
                            <!--加载没参加促销的-->
                            <#if cartMap.shoplist??&&cartMap.shoplist?size!=0>

                                <#list cartMap.shoplist as cars>
                                    <!--判断是否是套装-->
                                    <#if cars.fitId??&&cars.thirdId==third.thirdId>

                                        <#setting number_format="0">
                                        <div attr-codexType="-1" class="marketgroup carts_${cars.shoppingCartId}" attr-group="1">
                                            <div class="cart_goods">
                                                <div class="cart_item">
                                                    <div class="cell g_check" style="padding-top:2px;">
                                                        <input type="checkbox" class="g_ckeck mjchecked" name="box" checked="checked" value="${cars.shoppingCartId}"  onclick="checkOne(this);"/>
                                                    </div>
                                                    <div class="cell g_goods">
                                                        <strong>【套装】${cars.goodsGroupVo.groupName} 每套优惠${cars.goodsGroupVo.groupPreferamount}元<input type="hidden" value="${cars.goodsGroupVo.groupPreferamount}" class="groupPreferamount"/></strong>
                                                    </div>
                                                    <div class="cell g_price">
                                                        &nbsp;
                                                    </div>
                                                    <div class="cell g_promotion">
                                                        &nbsp;
                                                    </div>
                                                    <div class="cell g_stock">
							                        <span class="light">
                                                        <#if (cars.goodsGroupVo.stock>0)>
                                                            <span class="light">有货</span><input type="hidden" class="noProduct" value="1"/>
                                                        <#else>
                                                            <span class="light red">无货</span><input type="hidden" class="noProduct" value="0"/>
                                                        </#if>
                                                    </span>
                                                    </div>
                                                    <div class="cell g_count">
                                                        <div class="count">
                                                            <a href="javascript:void(0);" class="decrement" onclick="mit(this,${cars.shoppingCartId});">-</a>
                                                            <input type="text" class="text w30" value="${cars.goodsNum}" attr-maxnum="${cars.goodsGroupVo.stock}" onblur="opblur(this,${cars.shoppingCartId});"/>
                                                            <#assign sumcount="${sumcount?number+cars.goodsNum?number}">
                                                            <a href="javascript:void(0);" class="increment" onclick="add(this,${cars.shoppingCartId});">+</a>
                                                        </div>
                                                        <div class="red">
                                                            <#if  cars.goodsGroupVo.stock<=20 >

                                                                仅剩${cars.goodsGroupVo.stock}件！
                                                            </#if>
                                                        </div>
                                                    </div>
                                                    <div class="cell g_action">
                                                        <div>
                                                            <a class="g_delete" href="javascript:void(0);" onclick="del(${cars.shoppingCartId},${cars.goodsInfoId})">删除</a>
                                                        </div>
                                                    </div>
                                                    <div class="cb"></div>
                                                </div>
                                                <#assign tzsumprice="0">
                                                <#if cars.goodsGroupVo.productList??&&cars.goodsGroupVo.productList?size!=0>
                                                    <#list cars.goodsGroupVo.productList as pro>
                                                        <#if pro.productDetail??>
                                                            <div class="cart_item mb10 cart_item_goods">
                                                                <div class="cell g_check"></div>
                                                                <div class="cell g_goods">
                                                                    <div class="img">
                                                                        <a href="${basePath}/item/${pro.productDetail.goodsInfoId}.html" target="_blank" title="${basePath}/item/${pro.productDetail.goodsInfoId}.html"><img style="width:50px;height:50px;" title="${pro.productDetail.goodsInfoName}" alt="${pro.productDetail.goodsInfoName}" src="<#if pro.productDetail.goodsInfoImgId??>${pro.productDetail.goodsInfoImgId}</#if>" /></a>
                                                                    </div>
                                                                    <div class="name">
                                                                        <a href="${basePath}/item/${pro.productDetail.goodsInfoId}.html" target="_blank" title="${basePath}/item/${pro.productDetail.goodsInfoId}.html">${pro.productDetail.goodsInfoName}</a>
                                                                    </div>
                                                                    <div class="cb"></div>
                                                                </div>
                                                                <div class="cell g_price">
                                                                    <#setting number_format="0.00"><#if vip??&&vip=="1">￥${pro.productDetail.goodsInfoVipPrice}<#else>￥${pro.productDetail.goodsInfoPreferPrice}</#if>
                                                                </div>
                                                                <div class="cell g_promotion">
                                                                    <#if vip??&&vip=="1">
                                                                        ￥  <span class="pv_smprice"> ${pro.productDetail.goodsInfoVipPrice?number*cars.goodsNum?number} </span>
                                                                        <#assign tzsumprice="${(pro.productDetail.goodsInfoVipPrice?number*cars.goodsNum?number)+tzsumprice?number}">
                                                                        <input type="hidden" value="${pro.productDetail.goodsInfoVipPrice}" class="oneprice">
                                                                        <input type="hidden" value="${pro.productDetail.goodsInfoVipPrice?number*cars.goodsNum?number}" class="smprice">
                                                                    <#else>
                                                                        ￥  <span class="pv_smprice"> ${pro.productDetail.goodsInfoPreferPrice?number*cars.goodsNum?number} </span>
                                                                        <#assign tzsumprice="${(pro.productDetail.goodsInfoPreferPrice?number*cars.goodsNum?number)+tzsumprice?number}">
                                                                        <input type="hidden" value="${pro.productDetail.goodsInfoPreferPrice}" class="oneprice">
                                                                        <input type="hidden" value="${pro.productDetail.goodsInfoPreferPrice?number*cars.goodsNum?number}" class="smprice">
                                                                    </#if>
                                                                </div>
                                                                <#setting number_format="0">
                                                                <div class="cell g_stock">
                                                                    <span class="light">&nbsp;</span>
                                                                </div>
                                                                <div class="cell g_count">
                                                                    <div class="count">
                                                                        1件/套 X<span class="buyNum">${cars.goodsNum}</span>
                                                                    </div>

                                                                </div>
                                                                <div class="cell g_action">
                                                                    <div>

                                                                    </div>
                                                                </div>
                                                                <div class="cb"></div>
                                                            </div>
                                                        </#if>
                                                    </#list>
                                                </#if>
                                                <#setting number_format="0.00">
                                                <input type="hidden" class="xiaoji" value="${tzsumprice}"/> 	<input type="hidden" class="youhui" value="${cars.goodsGroupVo.groupPreferamount?number*cars.goodsNum}"/>

                                                <#assign sumPrice="${tzsumprice?number+sumPrice?number}">
                                                <#assign fxPrice="${fxPrice?number+(cars.goodsGroupVo.groupPreferamount?number*cars.goodsNum?number)}">
                                            </div><!-- /cart_goods -->

                                        </div>

                                        <!--不是套装-->
                                    <#else>
                                        <#if (cars.marketingActivityId??&&cars.marketingActivityId!=0)
                                        || (cars.goodsGroupId??&&cars.goodsGroupId!=0)
                                        <#--|| (cars.marketingId??&&cars.marketingId!=0)-->>

                                        <#elseif cars.goodsDetailBean??&&cars.goodsDetailBean.productVo??&&cars.goodsDetailBean.productVo.thirdId==third.thirdId>

                                            <div attr-codexType="0" class="marketgroup carts_${cars.shoppingCartId}" attr-group="0">

                                                <#setting number_format="0">
                                                <div class="cart_goods" id="cart_goods_${cars.shoppingCartId}">
                                                    <div class="cart_item">
                                                        <div class="cell g_check">
                                                            <input type="checkbox" class="g_ckeck mjchecked" name="box" checked="checked" value="${cars.shoppingCartId}"  onclick="checkOne(this);"/>
                                                        </div>
                                                        <div class="cell g_goods">
                                                            <div class="img">
                                                                <a href="${basePath}/item/${cars.goodsDetailBean.productVo.goodsInfoId}.html" target="_blank" title="${basePath}/item/${cars.goodsDetailBean.productVo.goodsInfoId}.html"><img style="width:50px;height:50px;" title="${cars.goodsDetailBean.productVo.productName}" alt="${cars.goodsDetailBean.productVo.productName}" src="<#if cars.goodsDetailBean.productVo.goodsInfoImgId??>${cars.goodsDetailBean.productVo.goodsInfoImgId}</#if>" /></a>
                                                            </div>
                                                            <div class="name">
                                                                <a href="${basePath}/item/${cars.goodsDetailBean.productVo.goodsInfoId}.html" target="_blank" title="${basePath}/item/${cars.goodsDetailBean.productVo.goodsInfoId}.html">${cars.goodsDetailBean.productVo.productName}</a>
                                                            </div>
                                                            <div class="cb"></div>
                                                            <div class="gift mt10">
                                                                <!--<p class="light">[赠品] 罗凡尼 非凡透明系列手机保护膜 适用于苹果5C+5S+5</p>
                                                                <p class="light">[赠品] 奇克摩克 TPU硅胶自带防尘塞手机壳/保护壳 适用于苹果iPhone5s 白色</p>-->
                                                            </div>
                                                        </div>
                                                        <div class="cell g_price">
                                                            <#setting number_format="0.00">
                                                        <#--后台进行过金额处理-->
                                                            <#if vip??&&vip=="1">
                                                                <#assign price=cars.goodsDetailBean.productVo.goodsInfoVipPrice>
                                                            <#else>
                                                                <#assign price=cars.goodsDetailBean.productVo.goodsInfoPreferPrice>
                                                            </#if>

                                                            ￥ ${price}

                                                        </div>
                                                        <div class="cell g_promotion">
                                                            <#assign manPrice="${price?number*cars.goodsNum}"/>
                                                            <#assign smprice="${manPrice}"/>
                                                            ￥  <span class="pv_smprice"> ${smprice}  </span>
                                                            <input type="hidden" value="${price}" class="oneprice">
                                                            <input type="hidden" value="${smprice!''}" class="smprice"/>
                                                        </div>
                                                        <#setting number_format="0">
                                                        <div class="cell g_stock">
                                                            <#if cars.goodsDetailBean.productVo.goodsInfoDelflag='1' || cars.goodsDetailBean.productVo.goodsInfoAdded!='1' >
                                                                <span class="light" style="color:red;">已下架</span><input type="hidden" class="noProduct" value="0"/>
                                                            <#else>
                                                                <#if (cars.goodsDetailBean.productVo.goodsInfoStock>0)>
                                                                    <span class="light">有货</span><input type="hidden" class="noProduct" value="1"/>
                                                                <#else>
                                                                    <span class="light" style="color:red;">无货</span><input type="hidden" class="noProduct" value="0"/>
                                                                </#if>
                                                            </#if>
                                                        </div>
                                                        <div class="cell g_count">
                                                            <div class="count">
                                                                <a href="javascript:void(0);" class="decrement" onclick="mit(this,${cars.shoppingCartId});">-</a>
                                                                <input type="text" class="text w30" value="${cars.goodsNum}" attr-maxnum="${cars.goodsDetailBean.productVo.goodsInfoStock}" onblur="opblur(this,${cars.shoppingCartId});"/>
                                                                <#assign sumcount="${sumcount?number+cars.goodsNum?number}">
                                                                <a href="javascript:void(0);" class="increment" onclick="add(this,${cars.shoppingCartId});">+</a>

                                                            </div>
                                                            <div class="red">
                                                                <#if  cars.goodsDetailBean.productVo.goodsInfoStock<=20 >

                                                                    仅剩${cars.goodsDetailBean.productVo.goodsInfoStock}件！
                                                                </#if>
                                                            </div>
                                                        </div>

                                                        <div class="cell g_action">
                                                            <div>
                                                                <a class="g_delete" href="javascript:void(0);" onclick="del(${cars.shoppingCartId},${cars.goodsInfoId})">删除</a>
                                                            </div>

                                                            <div class="">
                                                                <a class="change_promotion" href="javascript:void(0);">修改优惠</a>
                                                                <div class="dialog promotion_dialog">
                                                                    <div class="dialog-outer">
								                                    <span class="dialog-bg dialog-bg-n">
								                                    </span>
								                                    <span class="dialog-bg dialog-bg-ne">
								                                    </span>
								                                    <span class="dialog-bg dialog-bg-e">
								                                    </span>
								                                    <span class="dialog-bg dialog-bg-se">
								                                    </span>
								                                    <span class="dialog-bg dialog-bg-s">
								                                    </span>
								                                    <span class="dialog-bg dialog-bg-sw">
								                                    </span>
								                                    <span class="dialog-bg dialog-bg-w">
								                                    </span>
								                                    <span class="dialog-bg dialog-bg-nw">
								                                    </span>
                                                                        <div class="dialog-inner">
                                                                            <div class="dialog-toolbar clearfix">
                                                                                <a class="dialog-close" href="javascript:void(0);" title="关闭" onclick="$('.promotion_dialog').hide();">
                                                                                    关闭
                                                                                </a>
                                                                                <h3 class="dialog-title">
                                                                                    请选择一项优惠
                                                                                </h3>
                                                                            </div>
                                                                            <div class="dialog-content clearfix">
                                                                                <div class="p10 tc w300">
                                                                                    <label>活动优惠：</label>
                                                                                    <select class="chooseMarket" name="chooseMarket">
                                                                                        <option value="0">不使用活动优惠</option>
                                                                                        <#if cars.marketingList??&&cars.marketingList?size!=0>
                                                                                            <!--循环促销-->
                                                                                            <#list cars.marketingList as market>
                                                                                                <#if market.codexType!='15'&&market.codexType!='12' && market.codexType!='10'>
                                                                                                    <option <#if cars.marketingActivityId??&&cars.marketingActivityId!=0&&cars.marketingActivityId ==market.marketingId > selected="selected"</#if> value="${market.marketingId}">${market.marketingName}</option>
                                                                                                </#if>
                                                                                            </#list>
                                                                                        </#if>

                                                                                    </select>
                                                                                </div>
                                                                                <div class="tc p15">
                                                                                    <a class="red_btn" href="javascript:void(0);" onclick="changemarketing(this,${cars.shoppingCartId},${cars.goodsGroupId!0});">确定</a>
                                                                                    <a class="light_btn2" href="javascript:void(0);" onclick="$('.promotion_dialog').hide();">取消</a>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div><!-- /dialog -->
                                                            </div>
                                                        </div>
                                                        <div class="cb"></div>
                                                    </div>
                                                </div><!-- /cart_goods -->
                                                <#setting number_format="0.00">
                                                <input type="hidden" class="xiaoji" value="${smprice}"/> 	<input type="hidden" class="youhui" value="0"/>
                                                <#assign sumPrice="${sumPrice?number+smprice?number}">
                                            </div>

                                        </#if>

                                    </#if>

                                </#list>
                            </#if>
                        </div>
                    </#list>
                </#if>
            </#if>
            </form>

            <div class="cart_tools p10 lh180">
                <div class="fr w200">
                    <input type="hidden" id="zongji" value="${sumPrice!''}"/>
                    <p><span class="fr zongji">￥ ${sumPrice!''}</span>总计：</p>
                    <input type="hidden" id="fanxian" value="${fxPrice!''}"/>
                    <p><span class="fr fanxian">-￥${fxPrice!''}</span>返现：</p>
                </div>
                <div class="fr mr10">
                    <b class="red allcount">${sumcount}</b>件商品
                </div>

                <div class="fl">
                    <!--<a href="javascript:void(0);" class="delete">删除所选商品</a>-->
                    <!--<a href="javascript:void(0);" class="piece">凑单商品</a>-->
                </div>
                <div class="cb"></div>
            </div><!-- /cart_tools -->

            <div class="cart_total">
                <div class="fr f14 fb mr20 w300">
                    <span class="fr f20 red payPrice">￥   ${sumPrice?number-fxPrice?number}</span>
                    总计（不含运费）：
                </div>
            </div><!-- /cart_total -->

        </div><!-- /cart_box -->

        <div class="cart_btn mt10">
            <a class="check_btn fr" href="javascript:void(0);" onclick="onpay();">去结算</a>
            <a class="continue_shopping" href="${basePath}/index.html">继续购物</a>
        </div><!-- /cart_btn -->
    </div><!-- /container -->
    <script type="text/javascript">
        //初始化购物车客服咨询窗口
        function initShoppingCartServiceChat(){
            var goodsItems = [];
            var cartItems = $("div.cart_goods .cart_item");
            for(var i=0; i<cartItems.length; i++){
                var cartItem = cartItems[i];
                var goodsId = $(cartItem).find("input[id='goodsId']").val();
                var goodsCount = $(cartItem).find("input[class='text w30']").val();
                var goodsName = $(cartItem).find("input[id='goodsName']").val();
                var goodsImg = $(cartItem).find("input[id='goodsImg']").val();
                var goodsUrl = $(cartItem).find("input[id='goodsUrl']").val();
                var oneprice = $(cartItem).find("input[class='oneprice']").val();
                var goodsItem = {
                    'id':goodsId,//商品id
                    'name':goodsName,//商品名称
                    'count':goodsCount,//购买商品个数
                    'imageurl':goodsImg,//商品图片
                    'url':goodsUrl,//商品url
                    'siteprice':oneprice//商品当前价格
                };;
                goodsItems.push(goodsItem);
            }
            NTKF_PARAM.ntalkerparam={};
            NTKF_PARAM.ntalkerparam.item=goodsItems;
            NTKF_PARAM.ntalkerparam.cartprice=$("div[class^='cart_tools']").find("input[id='zongji']").val();
        }
        initShoppingCartServiceChat();
    </script>

    <div id="delete_dialog" class="dialog">
        <div class="dialog-outer">
        <span class="dialog-bg dialog-bg-n">
        </span>
        <span class="dialog-bg dialog-bg-ne">
        </span>
        <span class="dialog-bg dialog-bg-e">
        </span>
        <span class="dialog-bg dialog-bg-se">
        </span>
        <span class="dialog-bg dialog-bg-s">
        </span>
        <span class="dialog-bg dialog-bg-sw">
        </span>
        <span class="dialog-bg dialog-bg-w">
        </span>
        <span class="dialog-bg dialog-bg-nw">
        </span>
            <div class="dialog-inner">
                <div class="dialog-toolbar clearfix">
                    <a class="dialog-close" href="javascript:void(0);" title="关闭" onclick="hideDia()">
                        关闭
                    </a>
                    <h3 class="dialog-title">
                        删除商品
                    </h3>
                </div>
                <div class="dialog-content clearfix">
                    <div class="p10 tc red w200" id="diaText">
                        确定从购物车中删除此商品？
                    </div>
                    <div class="tc p15">
                        <form method="post" action="delshoppingcatgoodsgroup.htm" id="delGroupFil">
                            <input type="hidden" name="shoppingCartId" id="shoppingCartId"/>
                            <input type="hidden" name="goodsInfoId" id="goodsInfoId"/>
                            <input type="hidden" name="fitId" id="fitId"/>
                        </form>
                        <a class="red_btn" href="javascript:void(0);" onclick="dodel();">确定</a>
                        <a class="light_btn2" href="javascript:void(0);" onclick="hideDia()">取消</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="dialog s_dia dia2" style="text-align: center;background: #f2f2f2">
    <div class="dia_tit clearfix">
        <h4 class="fl">提示</h4>
        <a class="dia_close fr" href="javascript:" onclick="cls()"></a>
    </div><!--/dia_tit-->
    <div class="dia_cont">
        <div class="dia_intro no_tc pt30">
            <img class="vm mr10" id="f_img" alt="" src="${basePath}/images/mod_war.png" />
            <em id="con_00">修改成功！</em>
        </div>
        <div class="dia_ops mt50 tc" style="text-align: center;">
            <a class="go_pay" id="go_pay_00" href="javascript:cls();">确定</a>
        </div><!--/dia_ops-->
    </div><!--/dia_cont-->
</div><!--/dialog-->

<div class="dialog s_dia dia3" style="text-align: center;background: #f2f2f2">
    <div class="dia_tit clearfix">
        <h4 class="fl">提示</h4>
        <a class="dia_close fr" href="javascript:" onclick="cls()"></a>
    </div><!--/dia_tit-->
    <div class="dia_cont">
        <div class="dia_intro no_tc pt30">
            <em>购物车内没有商品，是否跳转到首页!</em>
        </div>
        <div class="dia_ops mt50 tc" style="text-align: center;">
            <a class="go_pay" id="go_pay_00" href="javascript:location.href='index.html';">确定</a>
            <a class="go_shopping" href="javascript:cls();">取消</a>
        </div><!--/dia_ops-->
    </div><!--/dia_cont-->
</div><!--/dialog-->

<#--参与抢购达到限制的购买数量时-->
<div class="dialog s_dia dia6" style="text-align: center;background: #f2f2f2">
    <div class="dia_tit clearfix">
        <h4 class="fl">提示</h4>
        <a class="dia_close fr" href="javascript:" onclick="cls()"></a>
    </div><!--/dia_tit-->
    <div class="dia_cont">
        <div class="dia_intro no_tc pt30">
            <em>参与的抢购达到该账号限制的购买数量</em>
            <em>请删除！</em>
        </div>
        <div class="dia_ops mt50 tc" style="text-align: center;">
            <a class="go_pay" id="go_pay_00" href="javascript:cls();">确定</a>
            <a class="go_shopping" href="javascript:cls();">取消</a>
        </div><!--/dia_ops-->
    </div><!--/dia_cont-->
</div><!--/dialog-->


<div class="dialog s_dia dia55" style="text-align: center;background: #f2f2f2">
    <div class="dia_tit clearfix">
        <h4 class="fl">提示</h4>
        <a class="dia_close fr" href="javascript:" onclick="cls()"></a>
    </div><!--/dia_tit-->
    <div class="dia_cont">
        <div class="dia_intro no_tc pt30">
            <em>包含无货或下架商品，请重新选择!</em>
        </div>
        <div class="dia_ops mt50 tr"  style="text-align: center;">
            <a class="go_pay" id="go_pay_00" href="javascript:cls();">确定</a>
            <a class="go_shopping" href="javascript:cls();">取消</a>
        </div><!--/dia_ops-->
    </div><!--/dia_cont-->
</div><!--/dialog-->


<div class="dialog s_dia dia4"  style="text-align: center;background: #f2f2f2">
    <div class="dia_tit clearfix">
        <h4 class="fl">提示</h4>
        <a class="dia_close fr" href="javascript:" onclick="cls()"></a>
    </div><!--/dia_tit-->
    <div class="dia_cont">
        <div class="dia_intro no_tc pt30" style="text-align: center;">
            <em>是否一键下单？</em>
        </div>
        <div class="dia_ops mt50 tr" style="text-align: center;">
            <a class="go_pay" id="go_pay_00" href="javascript:" onclick="goForm()">确定</a>
            <a class="go_shopping" href="javascript:cls();">取消</a>
        </div><!--/dia_ops-->
    </div><!--/dia_cont-->
</div><!--/dialog-->
<form action ="updateprovince.htm" method="post" class="subDis">
    <!--存放地址信息-->
    <input type="hidden" value=<#if wareUtil.districtId??>${wareUtil.districtId}</#if> name="distinctId" class="ch_distinctId">
    <input type="hidden" value=<#if wareUtil.provinceName??>${wareUtil.provinceName}</#if> name="chProvince" class="ch_province">
    <input type="hidden" value=<#if wareUtil.cityName??>${wareUtil.cityName}</#if> name="chCity" class="ch_city">
    <input type="hidden" value=<#if wareUtil.distinctName??>${wareUtil.distinctName}</#if> name="chDistinct" class="ch_distinct">
    <input type="hidden" name="chAddress" class="ch_address">
</form>
<!--修改优惠-->
<form method="post" action="changeshoppingcartmarts.htm" class="change_shopping">
    <input type="hidden" name="shoppingCartId" class="shopping_cart_id">
    <input type="hidden" name="marketingActivityId" class="marketing_activity_id">
    <input type="hidden" name="marketingId" class="marketing_id">
    <input type="hidden" name="goodsGroupMarketingId" class="goods_group_marketing_id">
</form>
<input type="hidden" value="<#if customerId??>${customerId}</#if>" id="customerId"/>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/shoppingcart/shoppingcart.js"></script>
<script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
<script type="text/javascript" src="${basePath}/index_two/js/index.js"></script>
<script type="text/javascript">
    $(function(){
        $(".mjchecked").each(function(){
            if($(this)[0].checked){
                //获取外部的DIV 是否是促销的商品
                var obj=this;
                var codexType = $(obj).parents(".marketgroup").attr("attr-codextype");
                if(codexType!='0'){
                    if(obj.checked){
                        if(codexType=='11'){//抢购
                            var youhui = 0 ;
                            var xiaoji = 0;
                            $(obj).parents(".marketgroup").find(".cart_goods").each(function(){

                                //件数
                                var jian = $(this).find(".count").find(".text").val();

                                //当限购的数量大于购物车数量时调用当前blur
                                if(parseInt($(this).find(".count").find(".text").attr("attr-maxnum"))<parseInt($(this).find(".count").find(".text").attr("oldgoodsnum")) ){
                                    $($(this).find(".count").find(".text")).blur();
                                }

                            })

                        }


                    }
                }

            }
        });
        $('.g_delete').click(function(){
            if($('#delete_dialog').is(':hidden')){
                var p_x = $(this).offset().left;
                var p_y = $(this).offset().top;
                $('#delete_dialog').css({
                    left : p_x - 100 +'px',
                    top : p_y + 15 + 'px'
                });
                $('#delete_dialog').show();
            }
            else{
                $('#delete_dialog').hide();
            }
        });

        $('.change_promotion').click(function(){
            var p_x = ($(window).width()-1200)/2+1200-350;
            var p_y = $(this).offset().top;
            $(this).next().show();
            $(this).next(".promotion_dialog").css({
                left: p_x,
                top: p_y + 20 + 'px'
            });
        });

        //计算购物车主体的最小高度
        setShopCartContainerMinHeight();
    });
    /* *
     * 计算购物车主体的最小高度，避免网站底部出现空白区域
     * 2015.11.04 wuyanbo add
     * */
    function setShopCartContainerMinHeight(){
        var bodyHeight = $(window).height();//当前屏幕的可视区域高度
        var topHeight = 30;
        var bottomHeight = 450;
        var shopCartHeight = (bodyHeight - topHeight- bottomHeight - 20);
        if(shopCartHeight <= 350){
            shopCartHeight = 350;
        }
        $("div[id='shopCartContainer']").css({"min-height":shopCartHeight+"px"});
    }

    function mit(obj,cartId){
        if($(obj).parents(".marketgroup").attr("attr-codextype")!="-1"){
            //获取 可用的最大库存和最小
            var minNum = 1;

            var nowNum = $(obj).next().val();

            var setNum = 0;
            if(nowNum>minNum){
                setNum=SubtrInt(nowNum,1);
            }else{
                setNum=nowNum;
            }

            $(obj).next().val(setNum);
            var onePrice = $(obj).parents(".cart_goods").find(".oneprice").val();
            var xiaoji = accMul(onePrice,setNum);
            $(obj).parents(".cart_goods").find(".smprice").val(xiaoji);
            $(obj).parents(".cart_goods").find(".pv_smprice").text(xiaoji);
            changeNum(cartId,setNum);
            jisuan(obj);
        }else{
            //套装
            var nowNum = $(obj).next().val();
            var flag = 0;
            if(nowNum=='1'){
                flag ++;
            }

            if(flag == 0 ){
                var zongji = 0 ;
                $(obj).next().val(SubtrInt(nowNum,1));
                nowNum --;
                $(obj).parents(".marketgroup").find(".cart_item_goods").each(function(){
                    $(this).find(".buyNum").text(nowNum);
                    var onePrice = $(this).find(".oneprice").val();
                    var xiaoji = accMul(onePrice,nowNum);
                    zongji = accAdd(xiaoji,zongji);
                    $(this).find(".smprice").val(xiaoji);
                    $(this).find(".pv_smprice").text(xiaoji);
                });
                if($(obj).parents(".cart_item").find(".mjchecked")[0].checked){
                    $(obj).parents(".marketgroup").find(".xiaoji").val(zongji);
                    var oneyh = $(obj).parents(".cart_item").find(".groupPreferamount").val();
                    $(obj).parents(".marketgroup").find(".youhui").val(accMul(oneyh,nowNum));
                }
                changeNum(cartId,nowNum);
                lastsum();
            }

        }

    }

    function opblur(obj,cartId){

        if($(obj).parents(".marketgroup").attr("attr-codextype")!="-1"){
            var reg = new RegExp("^[0-9]*$");
            var nowNum = $(obj).val();
            var maxNum = $(obj).attr("attr-maxnum");
            var minNum = 1;
            var setNum = 0 ;
            if(nowNum=='0'){
                setNum = minNum ;
                nowNum =  minNum;
            }
            if(reg.test(nowNum)){

                if(SubtrInt(nowNum,maxNum)>0){
                    setNum = maxNum ;
                }else{
                    setNum = nowNum;
                }

            }else{
                setNum = minNum ;
            }

            $(obj).val(setNum);
            var onePrice = $(obj).parents(".cart_goods").find(".oneprice").val();
            var xiaoji = accMul(onePrice,setNum);
            $(obj).parents(".cart_goods").find(".smprice").val(xiaoji);
            $(obj).parents(".cart_goods").find(".pv_smprice").text(xiaoji);
            changeNum(cartId,setNum);
            jisuan(obj);
        }else{
            var flag= 0 ;
            //套装
            var reg = new RegExp("^[0-9]*$");
            var nowNum = $(obj).val();
            if(!reg.test(nowNum)){
                nowNum = 1;
                $(obj).val(nowNum);
            }
            var maxNum = $(obj).attr("attr-maxnum");
            if(SubtrInt(maxNum,nowNum)<0){
                nowNum = maxNum;
            }
            $(obj).val(nowNum);
            var zongji = 0 ;
            $(obj).parents(".marketgroup").find(".cart_item_goods").each(function(){
                $(this).find(".buyNum").text(nowNum);
                var onePrice = $(this).find(".oneprice").val();
                var xiaoji = accMul(onePrice,nowNum);
                zongji = accAdd(xiaoji,zongji);
                $(this).find(".smprice").val(xiaoji);
                $(this).find(".pv_smprice").text(xiaoji);
            });

            if($(obj).parents(".cart_item").find(".mjchecked")[0].checked){
                $(obj).parents(".marketgroup").find(".xiaoji").val(zongji);
                var oneyh = $(obj).parents(".cart_item").find(".groupPreferamount").val();
                $(obj).parents(".marketgroup").find(".youhui").val(accMul(oneyh,nowNum));
            }

            changeNum(cartId,nowNum);
            lastsum();

        }
    }

    function add(obj,cartId){
        //不等于-1 为套装
        if($(obj).parents(".marketgroup").attr("attr-codextype")!="-1"){
            //获取 可用的最大库存和最小
            var maxNum = $(obj).prev().attr("attr-maxnum");
            var nowNum = $(obj).prev().val();
            var setNum = 0;
            if(SubtrInt(maxNum,nowNum)<=0){
                setNum=nowNum;
            }else{
                setNum=accAddInt(nowNum,1);
            }

            $(obj).prev().val(setNum);
            var onePrice = $(obj).parents(".cart_goods").find(".oneprice").val();
            var xiaoji = accMul(onePrice,setNum);
            $(obj).parents(".cart_goods").find(".smprice").val(xiaoji);
            $(obj).parents(".cart_goods").find(".pv_smprice").text(xiaoji);
            changeNum(cartId,setNum);
            jisuan(obj);
        }else{
            //套装
            var nowNum = $(obj).prev().val();
            var flag = 0;
            var num = $(obj).prev().attr("attr-maxnum");
            if(SubtrInt(num,nowNum)<=0){
                flag ++ ;
            }

            if(flag == 0 ){
                var zongji = 0 ;
                $(obj).prev().val(accAddInt(nowNum,1));
                nowNum ++;
                $(obj).parents(".marketgroup").find(".cart_item_goods").each(function(){
                    $(this).find(".buyNum").text(nowNum);
                    var onePrice = $(this).find(".oneprice").val();
                    var xiaoji = accMul(onePrice,nowNum);
                    zongji = accAdd(xiaoji,zongji);
                    $(this).find(".smprice").val(xiaoji);
                    $(this).find(".pv_smprice").text(xiaoji);
                });
                if($(obj).parents(".cart_item").find(".mjchecked")[0].checked){
                    $(obj).parents(".marketgroup").find(".xiaoji").val(zongji);
                    var oneyh = $(obj).parents(".cart_item").find(".groupPreferamount").val();
                    $(obj).parents(".marketgroup").find(".youhui").val(accMul(oneyh,nowNum));
                }
                changeNum(cartId,nowNum);
                lastsum();
            }
        }
    }

    function jisuan(obj){
        //计算价格 总计和优惠
        var codexType =  $(obj).parents(".marketgroup").attr("attr-codextype");

        var xiaoji = 0 ;
        var youhui = 0 ;
        $(obj).parents(".marketgroup").find(".mjchecked").each(function(){
            if($(this)[0].checked){
                //获取小计
                var thisprice=$(this).parents(".cart_goods").find(".smprice").val();
                //总计+单品小计
                xiaoji = accAdd(thisprice,xiaoji);
            }
        });

        if(codexType=='1'){
            $(obj).parents(".marketgroup").find(".cart_goods").each(function(){
                var num= $(this).find(".count").find(".text").val();
                var zhijiang=$(this).parent().find(".marketPrice").val();
                youhui=accAdd(youhui,accMul(num,zhijiang));
            })

        }
//        else if(codexType=='10'){//团购
//            $(obj).parents(".marketgroup").find(".cart_goods").each(function(){
//                var num= $(this).find(".count").find(".text").val();
//                var tuangou=$(this).parent().find(".groupPrice").val();
//                youhui=accAdd(youhui,accMul(num,tuangou));
//            })
//        }

        else if(codexType=='11'){//抢购
            $(obj).parents(".marketgroup").find(".cart_goods").each(function(){
                var num= $(this).find(".count").find(".text").val();
                var qianggou=$(this).parent().find(".rushPrice").val();
                youhui=accAdd(youhui,accMul(num,qianggou));
            })
        }

        else if(codexType=='5'){
            $(obj).parents(".marketgroup").find(".manjian_reducePrice").each(function(){
                var man = $(this).val().split(",")[0];
                var jian = $(this).val().split(",")[1];
                if(Subtr(xiaoji,man)>=0){
                    youhui = jian;
                }
            });
        }else if(codexType=='8'){
            $(obj).parents(".marketgroup").find(".manzhe_fullbuyDiscount").each(function(){
                var man = $(this).val().split(",")[0];
                var zhe = $(this).val().split(",")[1];
                if(Subtr(xiaoji,man)>=0){
                    youhui = accMul(xiaoji,Subtr(1,zhe));
                }
            });
        }
        $(obj).parents(".marketgroup").find(".xiaoji").val(xiaoji);
        $(obj).parents(".marketgroup").find(".youhui").val(youhui);

        lastsum();
    }

    function del(id,infoId){
        $('#shoppingCartId').val(id);
        $('#goodsInfoId').val(infoId);
    }

    function dodel(){
        var id = $('#shoppingCartId').val();
        var infoId=$("#goodsInfoId").val();
        var fitId=$("#fitId").val();
        if(fitId==""){
            $.post("delshoppingcartbyid/"+id+"-"+infoId, function (data)
            {
                if (data==1)
                {
                    $('#shoppingCartId').val('');
                    $('#delete_dialog').hide();
                    var tg = $('#cart_goods_'+id).parents(".marketgroup").parent().find(".marketgroup:visible").length;
                    if(tg==1){
                        var nums = $('#cart_goods_'+id).parents(".marketgroup").find(".cart_goods").length;
                        if(nums==1){
                            $('#cart_goods_'+id).parents(".marketgroup").parent().remove();
                        }else{
                            $('#cart_goods_'+id).remove();
                        }
                    }else{
                        var lg = $('#cart_goods_'+id).parents(".marketgroup").find(".cart_goods").length;
                        if(lg==1){
                            $('#cart_goods_'+id).parents(".marketgroup").remove();
                        }else{

                            $('#cart_goods_'+id).find(".mjchecked").attr("checked", false);
                            checkOne($('#cart_goods_'+id).find(".mjchecked"));
                            $('#cart_goods_'+id).remove();
                        }
                    }
                    $(".mjchecked").each(function(){
                        if($(this)[0].checked){
                            checkOne(this);
                        }
                    });
                    lastsum();
                }else{
                    //删除失败
                    $("#con_00").html("删除失败！");
                    dia(2);
                }
            });
        }else{
            var inId="110012"+fitId;
            $.post("delshoppingcartbyid/"+id+"-"+inId, function (data)
            {
                if (data==1){
                    $("#delGroupFil").submit();
                }
            });
        }
    }

    function hideDia(){
        $("#fitId").attr("value","");
        $('#delete_dialog').hide();
    }

    function changemarketing(obj,id,goodsGroupId){
        var newMarId = $(obj).parents(".dialog-content").find(".chooseMarket").val();
        if(newMarId!=''){
            $(".marketing_activity_id").val(newMarId);;
            $(".goods_group_marketing_id").val(goodsGroupId);;
            $(".shopping_cart_id").val(id);
            $(".change_shopping").submit();
        }
    }

    function oncheckAll(obj){
        if($(obj)[0].checked){
            $(".mjchecked").each(function(){
                if(!$(this)[0].checked){
                    $(this).attr("checked", true);
                    checkOne(this);
                }
            });
        }else{
            $(".mjchecked").each(function(){
                if($(this)[0].checked){
                    $(this).attr("checked", false);
                    checkOne(this);
                }
            });
        }
        lastsum();
    }

    function changeNum(id,num){
        $.post("changeshoppingcartbyid/"+id+"-"+num, function (data)
        {
            if (data==1)
            {

            }
        });
    }

    function onpay(){
        var $sum = 0 ;
        var $nopro = 0;
        var limit=0;
        $(".mjchecked").each(function(){
            if($(this)[0].checked){
                if($(this).parents(".cart_goods").find(".noProduct").val()=='0'){
                    $nopro = 1;
                }
                //获取外部的DIV 是否是促销的商品
                var obj=this;
                var codexType = $(obj).parents(".marketgroup").attr("attr-codextype");
                if(codexType!='0'){
                    if(obj.checked){
                        if(codexType=='11'){//抢购
                            if($(".rushlimit").val()==1){
                                limit=1;
                            }

                        }


                    }
                }
                $sum++;
            }
        });

        if($nopro==0){
            if($sum==0){
                dia(3);
            }else{
                if(limit==1){
                    dia(6);
                }else{
                    $("#subForm").submit();
                }
            }
        }else{
            dia(55);
        }
    }
</script>
<#include "../index/newbottom.ftl" />
</@htmlBody>