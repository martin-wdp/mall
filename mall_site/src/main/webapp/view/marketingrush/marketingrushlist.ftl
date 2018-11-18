<#include "../include/common.ftl">
<@htmlHead title='${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/index_two/css/header.css" />
<style type="text/css">
    .yi_over{background: Gray;}
</style>
<style>
    body {
        font-family: "Helvetica Neue", "Helvetica", "tahoma", "arial", "\5FAE\8F6F\96C5\9ED1", "\5b8b\4f53"!important;
        font-size: 12px;
        color: #666;
    }

    .content-wp {
        width: 1090px;
        margin: 0 auto;
    }
    .container{ width: 1200px; margin: 0px auto;}
    a{color:#666;}
    .show-boxbb {
        position: relative;
        height: 380px;
    }

    .show-boxbb #slidesbb {
        width: 100%;
        height: 380px;
        position: absolute;
        top: 0;
        right: auto;
        bottom: auto;
        left: 0;
        z-index: 90
    }

    .show-boxbb #slidesbb .slide {
        display: block;
        width: 100%;
        height: 380px;
        background-repeat: no-repeat;
        background-position: center top
    }

    .show-boxbb #slidesbb .slidesjs-pagination {
        position: absolute;
        top: auto;
        right: auto;
        bottom: 10px;
        left: 0;
        width: 100%;
        text-align: center!important;
        z-index: 99
    }

    .show-boxbb #slidesbb .slidesjs-pagination li {
        display: inline-block;
        *display: inline;
        *zoom: 1;
        vertical-align: middle;
        margin: 0 5px
    }

    .show-boxbb #slidesbb .slidesjs-pagination li a {
        display: inline-block;
        *display: inline;
        *zoom: 1;
        vertical-align: middle;
        width: 12px;
        height: 12px;
        line-height: 12px;
        background: rgba(0,0,0,0.3);
        border-radius: 50%;
        color: #fff;
        text-indent: -999999px
    }

    .slidesjs-pagination li{
        float: none;
    }
    .show-boxbb #slidesbb .slidesjs-pagination li a.active {
        background: #f0375e;
    }
    .brandList .title{
        color: #333;
        text-align: center;
        font-size: 24px;
        padding: 30px 0;
    }
    .proIntro{
        width: 950px;
        height:300px;
        margin:0 auto;
        margin-top: 20px;
        border: 1px solid #e3e3e3;
        border-radius:10px;
        /*box-shadow: 1px 1px 4px #e3e3e3;*/
        background: #fff;
        position: relative;
    }
    .proLeft{
        width: 430px;
        float: left;
        margin:20px;
    }
    .proLeft a{
        display: block;
        /*height: 338px;
        width: 588px;*/
    }
    .proRight{
        width: 412px;
        float: right;
        margin-right: 19px;
    }
    .flagBox{
        padding: 20px 0px;
        border-bottom: 1px dashed #333;
        /*background: url(images/time.jpg) no-repeat left center;*/
        font-size: 16px;
        color: #333;
        padding-left: 32px;
    }
    .numtimer{
        display: inline-block;
        *display: inline;
        *zoom:1;
        font-size: 16px;
        color: #f0375e;
        font-weight: bold;
    }
    .flagCon .flagtit{
        font-size: 16px;
        color: #f0375e;
        padding-top: 20px;
        height: 18px;

    }
    .flagCon .pro_des{
        font-size: 22px;
        color: #666;
        line-height: 24px;
        word-wrap: break-word;
        word-break: break-all;
        height: 80px;
        margin-top: 30px;
    }
    .bigPrice{
        font-family: "Verdana";
        font-size: 32px;
        color: #db5858;
    }
    .bigPrice em{
        font-size: 36px;
    }
    .p-original{
        font-size: 12px;
        font-family: "Verdana";
        text-decoration: line-through;
    }
    .flagPri a{
        display: inline-block;
        width: 160px;
        height: 50px;
        color: #fff;
        background: #f0375e;
        font-size: 22px;
        line-height: 50px;
        text-align: center;
        float: right;
        *margin-top: 20px;
    }
    .advanceTip{
        font-size: 16px;
        position:absolute;
        top:260px;
    }
    .advanceTip span{
        color: #f0375e;
    }
    .proIntro:hover{
        box-shadow:0 2px 17px rgba(000,000,000,.3);
    }
    .pro_column{
        width: 348px;
        height: 494px;
        float: left;
        margin-right: 19px;
        border: 1px solid #e3e3e3;
        box-shadow: 1px 1px 4px #e3e3e3;
        background: #fff;
        margin-bottom: 20px;
    }
    .pro_column:hover{
        box-shadow:0 2px 17px rgba(000,000,000,.3);
    }
    .pro_column .proImg{
        display: block;
        height: 348px;
        width: 348px;
        position: relative;
    }
    .pro_column .m-over{
        display: block;
        position: absolute;
        top:105px;
        left:105px;
        z-index: 99;
    }
    .pro_column .proName{
        font-size: 14px;
        line-height: 22px;
        padding: 15px 20px;
    }
    .pro_column .proName span{
        color: #f0375e;
    }
    .pro_column .proName a{
        display: block;
        height: 44px;
        overflow:hidden;
    }
    .flagPri a.small{
        width: 100px;
        height: 30px;
        line-height: 30px;
        margin-top: -2px;
    }
    .proTime em{
        color: #f0375e;
    }
    .proTime .numtimer{ font-size: 12px; font-weight: bold;}
    /*返回顶部样式*/
    .side_tools {
        width: 40px;
        background: #fff;
        position: fixed;
        right: 20px;
        display: none;
    }

    .backtotop {
        border: 1px solid #ddd;
        cursor: pointer;
        display: block;
        width: 38px;
        height: 33px;
        text-align: center;
    }

    .backtotop em {
        display: none;
    }

    .backtotop b {
        display: block;
        width: 38px;
        height: 33px;
        background: url(images/backtotop.gif) no-repeat;
    }

    .backtotop:hover b {
        display: none;
    }

    .backtotop:hover em {
        display: block;
        padding: 3px 5px;
        line-height: 14px;
        color: #fff !important;
        background: #f0375e;
    }

    .mt30 {
        margin-top: 30px;
    }
    /*春节替换图片及背景色*/
    .pbuy{width:100%;min-width:1200px;background:#eadbb4/*#950914*/ url(/images/pbuy_bj.jpg) no-repeat center top;padding-top:390px;padding-bottom:30px;}
</style>
</@htmlHead>
<@htmlBody>
<#--一引入头部 <#include "/index/topnew.ftl" /> -->
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

<!--
    <div class="show-boxbb">
        <div id="slidesbb">
        <#if avs?? && (avs?size>0)>
            <#list avs as bigAdvert>
                <a class="slide" href="${bigAdvert.adverHref}" style="background-image:url(${bigAdvert.adverPath});"></a>
            </#list>
        </#if>
        </div>
    </div>-->
<div class="pbuy">
    <div class="content-wp">
        <div class="brandList mt20">
            <#list pb.list as rush>
                <div class="proIntro clearfix">
                    <div class="proLeft">
                        <a href="${basePath}/item/${rush.goodsProductVo.goodsInfoId}.html"><img src="${rush.marketing.rushs[0].rushImage!''}" width="480" height="260"/></a>
                    </div>
                    <div class="proRight">
                        <div class="flagBox">
                            <font class="status">距离结束还有：</font>
                            <div id="count${rush_index+1}" class="numtimer">
                                <span class="day"></span><font></font>
                                <span class="hour"></span><font></font>
                                <span class="minute"></span><font></font>
                                <span class="second"></span><font></font>
                            </div>
                        </div>
                        <div class="flagCon">
                            <p class="flagtit">
                            ${rush.goodsProductVo.productName}

                            </p>
                            <p class="pro_des">
                            ${rush.goodsProductVo.subtitle!''}
                            </p>
                            <div class="flagPri clearfix mt20">
                            <span class="bigPrice">
                                <em>￥</em>
                                <#if vip?? && vip=='1'>
                                    <#if rush.marketing.rushs[0].rushVipPrice??>
                                    ${((rush.marketing.rushs[0].rushVipPrice?number)?string("0.00"))!""}
                                    <#else>
                                    ${((rush.marketing.rushs[0].rushPrice?number)?string("0.00"))!""}
                                    </#if>

                                <#elseif vip?? && vip=='0'>
                                ${((rush.marketing.rushs[0].rushPrice?number)?string("0.00"))!""}
                                <#else>
                                ${((rush.marketing.rushs[0].rushPrice?number)?string("0.00"))!""}
                                </#if>

                            </span>
                            <span class="p-original">￥
                                <#if vip?? && vip=='1'>
                                ${(rush.goodsProductVo.goodsInfoVipPrice?string("0.00"))!""}
                                <#elseif vip?? && vip=='0'>
                                ${(rush.goodsProductVo.goodsInfoPreferPrice?string("0.00"))!""}
                                <#else>
                                ${(rush.goodsProductVo.goodsInfoPreferPrice?string("0.00"))!""}
                                </#if>
                                </span>
                                <a href="${basePath}/item/${rush.goodsProductVo.goodsInfoId}.html" class="fr">立即抢购</a>
                            </div>
                            <div class="advanceTip clearfix">
                                <div class="fl">为您节省<span>
                                    <#if vip?? && vip=='1'>
                                <#if rush.marketing.rushs[0].rushVipPrice??>
                                    ${((rush.goodsProductVo.goodsInfoVipPrice?number-rush.marketing.rushs[0].rushVipPrice?number)?string("0.00"))!""}
                                    <#else>
                                    ${((rush.goodsProductVo.goodsInfoPreferPrice?number-rush.marketing.rushs[0].rushPrice?number)?string("0.00"))!""}
                                    </#if>

                            <#elseif vip?? && vip=='0'>
                                    ${((rush.goodsProductVo.goodsInfoPreferPrice?number-rush.marketing.rushs[0].rushPrice?number)?string("0.00"))!""}
                                    <#else>
                                    ${((rush.goodsProductVo.goodsInfoPreferPrice?number-rush.marketing.rushs[0].rushPrice?number)?string("0.00"))!""}
                                    </#if>


                                </span>元</div>
                            <#--<div class="fr"><span>1657</span>人已购买</div>-->
                            </div>
                        </div>
                    </div>
                </div><!--proIntro-->
                <input type="hidden" value="${rush.rushTime}" name="rushTime" id="rush${rush_index+1}">
                <input type="hidden" value="${rush.marketing.marketingBegin?string("yyyy-MM-dd HH:mm:ss")}" id="timebegin${rush_index+1}">
                <input type="hidden" class="alltimes" idattr="${rush_index+1}" value="${rush.marketing.marketingEnd?string("yyyy-MM-dd HH:mm:ss")}" id="time${rush_index+1}">
            </#list>
        </div><!--brandList-->
    </div><!--content-wp-->
</div>
<!--
    <#--分页-->
    <div class="container" >
        <div class="pss_tuan pt20">
            <div class="pages mt10 tr">
            <#if (pb.list?size!=0)>
    <#-- 分页 -->
    <#import "../pagination/pageBean.ftl" as page>
    <@page.pagination pb />
    </#if>
            </div>
        </div><#--pss_tuan-->

    </div>
    <div class="side_tools" style="bottom: 20%; display: block;">
        <a class="backtotop" href="javascript:;"><em>返回顶部</em><b></b></a>
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
<script type="text/javascript" src="${basePath}/js/jquery.slides.min.js"></script>
<script type="text/javascript" src="${basePath}/js/cloud-zoom.1.0.2.min.js"></script>
<script type="text/javascript">

    if($("#slidesbb").length > 0 && $("#slidesbb a").length > 1) {
        $("#slidesbb").slidesjs({
            play: {
                active: false,
                effect: "fade",
                auto: true,
                interval: 4000,
                pauseOnHover: true,
                restartDelay: 2500
            },
            navigation: {
                active: false
            },
            pagination: {
                active: true,
                effect: "fade"
            }
        });
    }
    $(".side_tools").css("bottom","20%");
    $(window).scroll(function() {
        if ($(document.documentElement).scrollTop() > 300 || $(document.body).scrollTop() > 300) {
            $(".side_tools").css('display','block');
            $(".backtotop").on("click",
                    function() {
                        $("body,html").animate({
                            scrollTop: 0
                        },0);
                    });
        } else {
            $(".side_tools").css('display','none');
        }
    });

    $(function(){

        $(".alltimes").each(function(){
            var id=$(this).attr("idattr");
            countDown($("#time"+id).val(),$("#timebegin"+id).val(),'#count'+id,$("#rush"+id).val());
            if($("#rush"+id).val()==3){
                $('#count'+id).prev().html("已结束");
                $('#count'+id).html("");
            }
        });
    });
    //date是结束日期，例如"2014/05/19";count是容器
    function countDown(dateend,datebegin,count,rush){
        var now = Date.parse(new Date());
        //alert(now);
        var date;
        if(rush==1){
            date=datebegin;
        }else if(rush==2){
            date=dateend;
        }else if(rush ==3){
            date=dateend;
        }
        var str =  date.replace(/-/g,"/");
        var target = Date.parse(new Date(str));
        var time = target - now;
        time = parseInt(time / 1000);
        var day = Math.floor(time / (60*60*24));
        time -= day * (60*60*24);
        var hour = Math.floor(time /(60*60));
        time -= hour * (60*60);
        var minute = Math.floor(time / 60);
        var second = time - (minute * 60);
        if(day<10){
            if(day<0){
                return;
            }else{
                day="0"+day;
            }
        }
        if(hour<10){
            hour="0"+hour;
        }
        if(minute<10){
            minute="0"+minute;
        }
        if(second<10){
            second="0"+second;
        }
        if(day==0&&hour==0&&minute==0&&second==0){
            if(rush==1){
                rush= 2;
                countDown(dateend,datebegin,count,rush);

            }else if(rush==2){
                $(count).prev().html("已结束");
                $(count).html("");

            }
        }else{
            if(rush==1){
                $(count).prev().html("距离开始还有:");
                $(count).find('.day').html(day);
                $(count).find('.day').next().html(" 天");
                $(count).find('.hour').html(hour);
                $(count).find('.hour').next().html(" 时");
                $(count).find('.minute').html(minute);
                $(count).find('.minute').next().html(" 分");
                $(count).find('.second').html(second);
                $(count).find('.second').next().html(" 秒");
            }else if(rush==2){
                $(count).prev().html("距离结束还有:");
                $(count).find('.day').html(day);
                $(count).find('.day').next().html(" 天");
                $(count).find('.hour').html(hour);
                $(count).find('.hour').next().html(" 时");
                $(count).find('.minute').html(minute);
                $(count).find('.minute').next().html(" 分");
                $(count).find('.second').html(second);
                $(count).find('.second').next().html(" 秒");
            }else if(rush ==3){
                $(count).prev().html("已结束");
                $(count).html("");
            }
            window.setTimeout(function(){countDown(dateend,datebegin,count,rush);},1000);
        }
    }
</script>
</@htmlBody>