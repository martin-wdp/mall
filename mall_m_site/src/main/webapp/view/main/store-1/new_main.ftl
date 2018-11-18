<#assign basePath=request.contextPath>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${(seo.meteKey)!''}">
    <meta name="description" content="${(seo.meteDes)!''}">
<#if (sys.bsetName)??>
    <title>${sys.bsetName}</title>
    <input type="hidden" id="bsetName" value="${(sys.bsetName)!''}">
    <input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
<#else>
    <title>${(seo.mete)!''}</title>
    <input type="hidden" id="bsetName" value="${(seo.mete)!''}">
    <input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
</#if>
    <input type="hidden" id="basePath" value="${(basePath)!''}">
    <link href="${basePath}/mobile_home_page/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${basePath}/mobile_home_page/css/style.css" rel="stylesheet"/>
    <!-- Bootstrap -->
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${basePath}/js/jquery.js"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="${basePath}/js/html5shiv.min.js"></script>
      <script src="${basePath}/js/respond.min.js"></script>
    <![endif]-->
    <style>
    <#if (mobSiteBasic.isShowBuffer)?? && (mobSiteBasic.isShowBuffer=='1')>
		.curtain_wp {background:url(${(mobSiteBasic.backgroudImage)!''}) ${(mobSiteBasic.backgroudColor)!''} no-repeat center 30%; background-size:50% auto;}
  	<#else>
		.curtain_wp {background:url(${basePath}/images/curtain_word.png) #2589c9 no-repeat center 30%; background-size:50% auto;}
  	</#if>
		.curtain_wp p {position:absolute; width:100%; left:0; bottom:10%; text-align:center; color:#fff; font-family:Arial; font-size:16px;}
    </style>
    <script type="text/javascript">
        //basePath值为''时，重新静态化
        //if($("#basePath").val()==""){
        //	location = "toStaticizeIndex.htm";
        //}
        function browserRedirect() {
            var sUserAgent = navigator.userAgent.toLowerCase();
            var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
            var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
            var bIsMidp = sUserAgent.match(/midp/i) == "midp";
            var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
            var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
            var bIsAndroid = sUserAgent.match(/android/i) == "android";
            var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
            var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
            if(bIsIpad){
            <#if siteProjectUrl?? && (siteProjectUrl?length>0)>
                location = "${(siteProjectUrl)!''}";
            </#if>
            }
            if (!(bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM)) {
                //跳转到pc版
            <#if siteProjectUrl?? && (siteProjectUrl?length>0)>
                //location = "${(siteProjectUrl)!''}";
            </#if>
            }
        }
        //根据设备跳转
        browserRedirect();
        //显示主体
        function showmain() {
            $(".ps-head").removeAttr("style");
            $("#main").fadeIn(function () {
                //设置全屏轮播
                var fullRolls = $(".fullRoll");
                if (fullRolls.length > 0) {
                    $(".more_goods").hide();
                    $(".foot").hide();
                    $(".smart_menu").hide();

                    var item = $(".app_item");
                    for (var i = 0; i < item.length; i++) {
                        var n = $(item[i]).find(".fullRoll").length;
                        if (!n) {
                            $(item[i]).hide();
                        }
                    }
                    for (var i = 0; i < fullRolls.length; i++) {
                        var sd = $(fullRolls[i]).find(".fullRollSD").val();
                        if (sd == "sh") {
                            mySwiper = new Swiper('.full-swiper-container', {
                                mode: 'horizontal',
                                loop: true,
                                pagination: '.full-pagination',
                                paginationClickable: true,
                            });
                            $(fullRolls[i]).find(".full-pagination").show();
                            $(fullRolls[i]).find(".vtc-img").hide();
                        } else {
                            mySwiper = new Swiper('.full-swiper-container', {
                                mode: 'vertical',
                                loop: true,
                                pagination: '.full-pagination',
                                paginationClickable: true,
                            });
                            $(fullRolls[i]).find(".full-pagination").hide();
                            $(fullRolls[i]).find(".vtc-img").show().delay(3000).fadeOut(500);
                        }
                        //控制音乐播放
                        $(".bgd_music a").click(function () {
                            var obj = $(this),
                                    audio = document.getElementById('bg_music');
                            if (audio.paused) {
                                audio.play();
                                obj.html('暂停');
                                obj.removeClass("music_pause");
                                return;
                            }
                            audio.pause();
                            obj.html('播放');
                            obj.addClass('music_pause');
                        });
                    }
                }
                //轮播
                var rolls = $(".roll_adv");
                for (var i = 0; i < rolls.length; i++) {
                    var rollId = "#" + $(rolls[i]).prop("id");
                    $(rollId + ' .roll_banner,' + rollId + ' .roll_banner a img').css('height', parseInt(($(window).width() * 35) / 56) + 'px');
                    var mySwiper = new Swiper(rollId + ' .swiper-container', {
                        pagination: '.swiper-pagination',
                        loop: true,
                        grabCursor: true,
                        autoplay: 3000
                    });
                }
                //文字
                var texts = $(".text_app");
                for (var i = 0; i < texts.length; i++) {
                    var text = $(texts[i]);
                    var textid = text.prop("id");
                    var textCont = $("#textCont" + textid.substring(8)).text();
                    text.html(textCont);
                }
            });
            //添加搜索栏
            $("#roll_adv1 .roll_banner").append("<div class='search-box search-box2'>" +
            "<div class='search-cont'>" +
           /* "<p class='qpmall-logo'><img src='images/qpmall_logo.png'/></p>" +*/
            "<form action='${basePath}/searchProduct.htm' method='get' id='searchProductForm' style='margin-bottom:0;'>" +
            "<p class='inp'>" +
            "<input id='title' type='text' placeholder='关键字' name='keyWords' /><input type='hidden' value='0' name='storeId' id='storeId'>" +
            "<button id='searchBtn' class='btn_search'></button>" +
            "</p>" +
            "</form>" +
                //"<p class='scanning'><img src='images/scanning.png'/></p>" +
            "</div>" +
            "</div>");

           /* $("#roll_adv1").append("<div class='changeCar changeCar2'></div>");*/

            /***********************************/
            /*      判断是否已选车型           */
            /*      没有选车型传参数0          */
            /*      已选车型传参数1           */
            /***********************************/
            //changeCarFun(0,"","");
        }
        //添加选车型
        function changeCarFun(n,selectVal,liyaID) {
            if(!n) {
                $(".changeCar").html("<p><i>选择车型</i><a href='${basePath}/selectcarindex.html'>选车型</a></p>");
            }else{
                $(".changeCar").html("<p><i>已选车型："+selectVal+"</i><a href='${basePath}/selectcarindex.html'>选车型</a><input type=\"hidden\" id=\"liyaID\" value=\""+liyaID+"\"></p>");
            }
        }
        //站点设置信息不显示缓冲页
        <#if (mobSiteBasic.isShowBuffer)?? && (mobSiteBasic.isShowBuffer=='0')>
        //不显示缓冲页
        $(function(){
            showmain();
        });
        <#else>
        //没有站点设置，或显示缓冲页
        $(function(){
            var count;
            $.get("getCount.htm",function(data){
                count = data + 1;
                $.post("setCount.htm",{count:count},function(){
                });
                if(data>=1){
                    showmain();
                }else{
                    $(".curtain_wp").width($(window).width()).height($(window).height()).delay(3000).fadeOut(500,function(){
                        showmain();
                    });
                }
            });
        });
        </#if>
    </script>
 <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
<body>
<#--新加头部－加搜索-->
<style>
    .ps-head{padding:0.5em;background: url(http://st.360buyimg.com/m/images/index/header-bg.png?v=2) repeat-x #efefef;  background-size: 100% 44px;}
    .ps-back{width: 15%; float: left; text-align: center;}
    .ps-back a{ display: block; background: url(images/arrow_left.png) no-repeat center center; width: 100%; background-size: auto 50%; height: 2.8em;  text-indent: -99999px;}

    .ps-search{border:1px solid #d6d6d6; border-radius: 4px; height: 2.3em; margin-top: 0.2em; width:100%;}
    .ps-search input{ width: 85%;height: 2.1em;
        background:#fff; border:none;margin-top:0;float:left;padding-left:0.5em}
    .ps-search button{float:right;width:15%;background: url(${basePath}/images/search.png) no-repeat center center; text-indent:1.8em;
        background-size: auto 50%; border:none; cursor: pointer; height: 2.1em; border-left:1px solid #d6d6d6;text-indent: -99999px;}
    .ps-clk{ width: 15%; float: left; text-align: center; height: 2.8em;}
    .ps-clk a{display: block; background: url(imagesst.png) no-repeat center center; width: 100%; background-size: auto 50%; height: 2.8em; text-indent: -99999px;}
</style>
<script type="text/javascript">
    $(function(){
        $("#searchBtn").click(function(){
            $("#storeId").val($("#storeId").val());
            if($("#title").val()==''){
                $("#searchProductForm").submit();
            }
        });
    });
</script>

<#--
<div id="index" style="background:url(${basePath}/images/roll_1.jpg) #e16434 no-repeat center center;background-size:100% auto; height: inherit;"></div>
<div id="index" style="background:url(${basePath}/images/roll_1_2.png) #e16434 no-repeat center center;background-size:100% 100%;"></div>
-->
<div class="curtain_wp">
<#if (mobSiteBasic)??>
    <p>© ${(mobSiteBasic.copyright)!''}</p>
<#else>
    <p>© NINGPAI</p>
</#if>
</div><!--/curtain_wp-->
<div id="main" style="display: none;">
    <div class="contact-information btn_ph">
        <img src="${basePath}/images/index_talk.png"/>
        <a href="tel:96008" class="btn-phone" alt="电话"></a>
        <span class="btn-customerService" alt="客服"></span>

    </div>
    <input class="storeId" type="hidden" value="0">
    <!--引用静态页面-->
<#include "9gdemo.html"/>
    <div class="more_goods">
        <a href="${basePath}/list/allproduct.html">查看更多商品&gt;&gt;</a>
    </div><!-- /more_goods -->


    <div class="foot">
        <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->

<#include "../../common/smart_menu.ftl"/>
<#--2016-01-28 wuyanbo 屏蔽-->
<#--<#include "../pathinner.ftl"/>-->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/jquery.keleyi.js"></script>
    <script src="${basePath}/js/jquery.lazyload.js"></script>
    <script src="${basePath}/js/customer/wxforward.js"></script>
    <script src="${basePath}/js/flushMenu.js"></script>
    <script src="${basePath}/js/touchmove.js"></script>
    <script>
        $(function(){
            $('img.lazy').lazyload({
                threshold: 200,
                event:'scroll',
                effect: "fadeIn",
                failurelimit : 10,
                palceholder : 'images/loading.gif',
                skip_invisible: false
            });

            $("#img_adv_9").append("<p class='countDown'><i id='day'>00</i>&nbsp;天&nbsp;<i id='hour'>00</i>&nbsp;时&nbsp;<i id='minute'>00</i>&nbsp;分&nbsp;<i id='second'>00</i>&nbsp;秒&nbsp;</p>");

            //###############倒计时######################
            showCountDown('${basePath}');

            //处理魔方高度
            Array.ExistsSameValues = function(a1, a2) {
                var exists = false;
                if(a1 instanceof Array && a2 instanceof Array){
                    for (var i=0,iLen=a1.length; i<iLen; i++){
                        for (var j=0,jLen=a2.length; j<jLen; j++){
                            if (a1[i]===a2[j]){
                                return true;
                            }
                        }
                    }
                }
                return exists;
            };
            $(".app_cont").each(function(){
                var _this = $(this),
                        _hg = "",
                        arr1 = [],
                        arr2 = [],
                        _th = "",
                        _tp = "",
                        _c = $(window).width()/4;
                _this.find("img").each(function(){
                    _th = $(window).width()*(parseInt($(this).attr("heightb"))/100);
                    var styleval = $(this).attr("style");
                    var topval = styleval.substring((styleval.indexOf("top:")+4),styleval.lastIndexOf("%"));
                    _tp = $(window).width()*(parseInt(topval)/100);
                    $(this).height(_th);
                    $(this).css("top",_tp+"px");
                    _hg = _tp + _th;
                    arr1.push(_hg);
                    arr2.push(_tp);
                });
                if(Array.ExistsSameValues(arr1, arr2) == true) {
                    var _top = Math.max.apply(null, arr1);
                    _this.height(_top);
                } else {
                    _this.find("img").each(function(){
                        var _t = $(this),
                                _th = parseInt($(this).attr("heightb"))/100*$(window).width(),
                                _tp = parseInt($(this).css("top")),
                                _tr = _tp/_c+1,
                                _rowspan = _th/_c;
                        for(var r=_tr;r<_rowspan+_tr;r++) {
                            _t.addClass("tr"+r+" ");
                        }
                    });
                    var arr3 = [];
                    for(var n=1;n<5;n++) {
                        if(_this.find(".tr"+n).length == 0) {
                            arr3.push(n);
                        }
                    }
                    _this.height($(window).width()-_c*arr3.length);
                    for(x in arr3) {
                        _this.find("img").each(function(){
                            var _t = $(this),
                                    _n = _t.prop("class").substring(9,10),
                                    _top = parseInt(_t.css("top"));
                            if(_n > arr3[x]) {
                                _t.css("top",_top-_c+"px");
                            }
                        });
                    }
                }
            });
        <#--
            $(".col-sm-12").load(function(){
                  $(".col-sm-12").css("height",$(".col-sm-12").parent().parent().prev().css("height")/2);

            });
            var obj = new Image();
            obj.src = $(".img2 img").attr("src");
            obj.onload = function(){
              $(".img_s img").css("height",obj.height/2);
            };
        -->

        });
        var app_cont = $(".app_cont*");
        for(var i=0;i<app_cont.length;i++){
            $(app_cont[i]).height($(window).width());
        }

        $(function(){
            $(".gd-02 li").css("width",$(window).width()/2 - 6);
        });

        function GetQueryString(name)
        {
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if(r!=null)return  decodeURI(r[2]); return null;
        }


    </script>
</div><!--/main-->
<script src="${basePath}/js/publicModel.js"></script>
</body>
</html>