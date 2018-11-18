<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="MobileOptimized" content="320">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/top.css">
    <link rel="stylesheet" href="${basePath}/css/browseRecords.css">
    <title>我的浏览记录</title>
 <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script> <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
<body>
<#include "../publicHeader2_ftl.ftl" />
<#if (browseRecordList?? &&browseRecordList?size>0)>

<div class="clear">清空</div>
</#if>
<#if (browseRecordList?? &&browseRecordList?size>0)>
<div class="goods">
    <ul>
        <#list browseRecordList as brows>
            <li>
                <input type="hidden" class="goodsId" value="${brows.goodsId}" />
                <img src="${brows.goods.goodsImg!}">
                <p>
                    <a>${brows.goods.goodsName!}</a>
                    <strong>￥<em><#if cust.isEnterprise=="1">${brows.goods.goodsVipPrice?number?string("0.00")}<#else>
                    ${brows.goods.goodsPrice?number?string("0.00")}
                    </#if></em></strong>
                    <button class="addshoppingcat">加入购物车</button>
                </p>
            </li>
        </#list>
    </ul>
</div>
<#else>
<div  class="no_goods">
    <img src="${basePath}/images/no_goods.png">
    <a href="${basePath}/main">去首页逛逛</a>
</div>
</#if>
<div class="box dis" >
    <div class="mesBox">
        <h3>清空后没办法找回喽~<br/>真的要清空吗？</h3>
        <button id="leave" style="color:#000;background:none;border:1px solid #EDEDED;">确定</button>
        <button id="continue" style="background:#F6AB00;">算了</button>
    </div>
</div>
<div id="boxAdd" class="boxAdd dis" >
    <div class="mesBox">
        <h3>该商品已成功添加到购物车</h3>
        <button id="continueAdd" style="color:#000;background:none;border:1px solid #EDEDED;">继续添加商品</button>
        <button id="gotoShoppingcat" style="background:#F6AB00;">去购物车</button>
    </div>
</div>
</body>
<script src="${basePath}/js/customer/jquery-1.11.3.min.js"></script>
<script>
    $("#continue").click(function(){
        $(this).parents(".box").hide();
    });
    $("#gotoShoppingcat").bind("click",function(){
        window.location.href = "${basePath}/myshoppingmcart.htm?tag=3";
    });
    $("#continueAdd").bind("click",function(){
        $(this).parents(".boxAdd").hide();
    });
    $(".addshoppingcat").bind("click",function(){
        var goodsid = $(this).parent().parent().find('input[class="goodsId"]').val();
        var url = "${basePath}/addproducttoshopcarnewAJAX.htm";
        //ajax 加入购物车
        $.ajax({
            url: url,
            type: 'post',
            async: false,
            data:{productId:goodsid},
            success: function (data) {
                if(data!=null&&data.addFlag >= 1){
                    $("#boxAdd").show();
                }
            }
        });
    });

    $(".clear").click(function(){
        $(".box").show();
    });
    $("#leave").click(function(){
        $.ajax({
            url: "${basePath}/clearBrowserecord.htm",
            type: 'post',
            async: false,
            success: function (data) {
                if(data >= 1){
                    window.location.href = "${basePath}/customer/toBrowserecordList.html?tag=4";
                }
            }
        });
    });
</script>
</html>