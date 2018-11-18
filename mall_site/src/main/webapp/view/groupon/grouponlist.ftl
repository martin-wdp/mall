<#include "../include/common.ftl">
<@htmlHead title='${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<style>
    .new_tlist li{ width: 280px;float: left; background: #fff; border:1px solid #ccc; margin: 20px 24px 0px 0px; height: 450px;position:relative;}
    .new_tlist li:hover{ border: 1px solid #dddddd; box-shadow:0 2px 5px #ddd; -moz-box-shadow:0 2px 5px #ddd; -webkit-box-shadow:0 2px 5px #ddd;*height:537px;}
    .new_tlist li .name{width:250px; font-family: "微软雅黑"; font-size: 16px; line-height: 24px; height: 50px;padding:6px 0 0 18px;overflow: hidden;}
    .new_tlist li .price{ font-size: "Arial"; font-size: 24px; color: #FDB832; display: inline;}
    .new_tlist li .price i{ font-size: 24px; font-style: normal;}
    .new_tlist li .price span{ color: #999; font-family: "微软雅黑"; font-size: 14px; margin-left: 10px; text-decoration: line-through;}
    .col9{ color: #999;}
    .pss_court_d{ margin-top: -2px; margin-right: 10px;margin-left: 18px;}
    .pss_count_down{font-size: 16px;background:url(../images/time2.png) no-repeat left center;height: 42px;width:100%;line-height:42px;color: #fff;font-size: 14px;font-family: "微软雅黑";text-indent: 48px;position:absolute;top:238px;left:0px;}
    .cont_nav{width:100%;height:60px;line-height:60px;border:1px solid #262626;margin-top:20px;}
    .cont_nav .all{height:30px;margin-top:15px;line-height:30px;border-right:1px solid #262626;padding:0 15px;color:#FA4742;float:left;cursor:pointer;}
    .cont_nav li{float:left;margin-left:15px;color:#333;cursor:pointer;}
    .cont_nav li:hover{color:#FA4742;}
    .screen{width:100%;height:40px;border:1px solid #262626;border-top:2px solid #262626;margin-top:10px;background:#f1f1f1;}
    .screen li{width:130px;height:40px;line-height:40px;float:left;text-align:center;border-right:1px solid #dcdcdc;cursor:pointer;background:#fff;}
    .screen li:nth-child(1){background:#262626;color:#FFBA15;}
    .screen li span{padding-right:20px;background-image:url(/images/arrowPoint.png); background-repeat:no-repeat; background-position:right center;}
    .screen li:nth-child(1) span{background-image:url(/images/arrowPoint_hover.png);}
</style>
</@htmlHead>
<@htmlBody>
<#if (topmap.temp)??>
    <#if (topmap.temp.tempId==1)>
        <#include "../index/topnew.ftl">
    <#elseif (topmap.temp.tempId==3)>
        <#include "../index/newheader.ftl">
    <#elseif (topmap.temp.tempId==9)>
        <#include "../index/newheader4.ftl">
    <#elseif (topmap.temp.tempId==10)>
        <#include "../index/newheader5.ftl">
    <#elseif (topmap.temp.tempId==11)>
        <#include "../index/newheader6.ftl">
    <#elseif (topmap.temp.tempId==12)>
        <#include "../index/newheader7.ftl">
    <#elseif (topmap.temp.tempId==13)>
        <#include "../index/newheader8s.ftl">
    <#elseif (topmap.temp.tempId==14)>
        <#include "../index/newheader9.ftl">
    <#elseif (topmap.temp.tempId==15)>
        <#include "../index/newheader10.ftl">
    <#elseif (topmap.temp.tempId==16)>
        <#include "../index/newheader11.ftl">
    <#elseif (topmap.temp.tempId==17)>
        <#include "../index/newheader12.ftl">
    <#elseif (topmap.temp.tempId==18)>
        <#include "../index/newheader13.ftl">
    <#elseif (topmap.temp.tempId==19)>
        <#include "../index/newheader14.ftl">
    <#elseif (topmap.temp.tempId==20)>
        <#include "../index/newheader15.ftl">
    <#else>
        <#include "../index/newheader3.ftl">
    </#if>
</#if>
<#if avs?? && (avs?size>0)>
    <#list avs as bigAdvert>
    <div style="text-align:center;"><a href="${bigAdvert.adverHref}"><img alt="" src="${bigAdvert.adverPath}" /></a></div>
    </#list>
</#if>
<div class="container">
    <div class="cont_nav">
        <span class="all"><a href="${basePath}/grouponlist-1.html<#if urlchange1??>${urlchange1?replace("&","?")}</#if>">全部</a></span>
        <ul>
        <#--<li><a>汽车用品</a></li>
        <li><a>快修保养</a></li>
        <li><a>底盘与悬挂</a></li>
        <li><a>电器与线束</a></li>
        <li><a>车门</a></li>
        <li><a>叶子板/底板/侧围</a></li>
        <li><a>车身</a></li>
        <li><a>内饰</a></li>
        <li><a>发动机</a></li>
        <li><a>空调/暖风/燃油</a></li>
        <li><a>变速箱</a></li>-->
        <#if firstGoodsCateList?? && (firstGoodsCateList?size>0)>
            <#list firstGoodsCateList as firstGoodsCate>
                <li><a href="${basePath}/grouponlist-1.html?parentId=${firstGoodsCate.catId}${urlchange1!}"
                       <#if parentId?? && firstGoodsCate.catId== parentId>style="color: #E54744"</#if>>${firstGoodsCate.catName}</a></li>
            </#list>
        </#if>
        </ul>

    </div>
    <div class="screen">
        <ul>
            <li num="1" sort="1"><span>价格</span></li>
            <li num="0" sort="1"><span>销量</span></li>
        </ul>
    </div>

    <div style="background:#fff;width:1230px;">
        <div></div>
        <ul class="clearfix new_tlist">
        <#list pb.list as groupons>
            <li>
                <a href="${basePath}/item/${groupons.goodsProductVo.goodsInfoId}.html"><img src="<#if groupons.goodsProductVo.imageList?? && groupons.goodsProductVo.imageList[0]??>${groupons.goodsProductVo.imageList[0].imageBigName!''}</#if>" width="280" height="280"/></a>
                <p class="name"><a href="${basePath}/item/${groupons.goodsProductVo.goodsInfoId}.html">${groupons.goodsProductVo.productName}</a></p>
                <div class="pt10 clearfix">
                    <div class="pss_court_d fl">
                        <#if groupons??&&groupons.marketing??&&groupons.marketing.groupon??&&groupons.marketing.groupon.grouponDiscount??>${groupons.marketing.groupon.grouponDiscount*10}<#else>0</#if>
                        折
                    </div>
                    <p class="price"><i>¥</i><#if vip??&&vip=="1">
                    ${(groupons.marketing.groupon.grouponVipPrice)!""} <span>${(groupons.goodsProductVo.goodsInfoVipPrice?string("0.00"))!""}</span>
                    <#else>
                    ${(groupons.marketing.groupon.grouponPrice)!""} <span>${(groupons.goodsProductVo.goodsInfoPreferPrice?string("0.00"))!""}</span></#if></p>
                <#--<span class="col9 pl10">销量：<#if  groupons??&&groupons.marketing??&&groupons.marketing.groupon??&&groupons.marketing.groupon.participateCount??>${groupons.marketing.groupon.participateCount}<#else>0</#if></span>-->
                </div>
                <a href="${basePath}/item/${groupons.goodsProductVo.goodsInfoId}.html" class="pss_hot_buy" >立即参团</a>
                <input type="hidden" class="alltimes" idattr="${groupons_index+1}"  value="${groupons.marketing.marketingEnd?string("yyyy-MM-dd HH:mm:ss")}" id="time${groupons_index+1}">
                <div class="pss_count_down">
                    <div id="count${groupons_index+1}">
                        <span class="day"></span>
                        <span class="hour"></span>&nbsp;时&nbsp;
                        <span class="minute"></span>&nbsp;分&nbsp;
                        <span class="second"></span>秒
                    </div>
                </div>
            </li>
        </#list>
        </ul>
        <div class="pages mt10 tr" style="margin-right: 20px;">
        <#if (pb.list?size!=0)>
                        <#-- 分页 -->
                        <#import "../pagination/pageBean.ftl" as page>
                        <@page.pagination pb />
                  </#if>
        </div>
    </div>
</div>
<!--这是底部-->
<#if (topmap.temp)??>
    <#if (topmap.temp.tempId==1)>
        <#include "../index/bottom.ftl">
    <#else>
        <#include "../index/newbottom.ftl" />
    </#if>
</#if>
<script type="text/javascript" src="${basePath}/js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="${basePath}/js/cloud-zoom.1.0.2.min.js"></script>
<script type="text/javascript" src="${basePath}/js/group.js"></script>
<script>
    $(".screen li").click(function(){
        var sort = $(this).attr("sort");
        var num  = $(this).attr("num");
        if($(this).index()=="0"){
            location.href="${basePath}/grouponlist-1.html${urlchange2}${urlchange!}";
        }
        $(".screen li").css({"background":"#fff","color":"#333"}).children("span").css("background-image","url(/images/arrowPoint.png)");
        $(this).css({"background":"#262626","color":"#FFBA15"}).children("span").css("background-image","url(/images/arrowPoint_hover.png)");

        if(sort == "0" && num =="0"){
            $(this).children("span").css("background-image","url(/images/arrowPoint_hover_down.png)");
        }
        $(".screen li").attr("num","0");
        $(this).attr("num","1");
        if(num=="1" && sort == "1"){
            $(this).attr("sort","0").children("span").css("background-image","url(/images/arrowPoint_hover_down.png)");
        }else if(num =="1" && sort == "0"){
            $(this).attr("sort","1").children("span").css("background-image","url(/images/arrowPoint_hover.png)");
        }
        $(".screen li").each(function(){
            if($(this).attr("num") == "0"){
                if($(this).attr("sort") == "1"){
                    $(this).children("span").css("background-image","url(/images/arrowPoint.png)");
                }else{
                    $(this).children("span").css("background-image","url(/images/arrowPoint_down.png)");
                }
            }
        });

    });
    $(".cont_nav li").click(function(){
        $(".all,.cont_nav li a").css("color","#333");
        $(this).children("a").css("color","#FA4742");
    });
    $(".all").click(function(){
        $(this).css("color","#FA4742");
        $(".cont_nav li").css("color","#333");
    });
    var isAsc = GetQueryString("isAsc");
    if(isAsc == 1){
        $(".screen li:nth-child(1)").attr("sort","0").children("span").css("background-image","url(/images/arrowPoint_hover_down.png)");
    }
    function GetQueryString(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null)return  unescape(r[2]); return null;
    }
</script>
</@htmlBody>