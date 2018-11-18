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
    <title><#if map.nowcate?? >${map.nowcate.catName}<#else>所有商品</#if>列表页--${sys.bsetName}</title>
<#else>
    <title><#if map.nowcate?? >${map.nowcate.catName}<#else>所有商品</#if>列表页--${seo.mete}</title>
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
</head>
<body>
<#--新加头部－加搜索-->
<#include "/main/search.ftl"/>
<input class="storeId" type="hidden" value="${storeId}">
<div class="goods_filter container-fluid">
    <div class="col-xs-3">
        <a val="5D" attr="${sort!''}" class="change_sort <#if sort='' || sort='5D'>cur</#if>" href="javascript:;">默认</a>
    </div>
    <div class="col-xs-3">
        <a val="2D" attr="${sort!''}" class="change_sort <#if sort='22D' || sort='2D'>cur</#if> <#if sort='22D'> s_up<#elseif sort='2D'> s_down</#if>" href="javascript:;">销量</a>
    </div>
    <div class="col-xs-3">
        <a val="1D" attr="${sort!''}" class="change_sort <#if sort='11D' || sort='1D'>cur</#if> <#if sort='11D'> s_up<#elseif sort='1D'> s_down</#if>" href="javascript:;">价格</a>
    </div>
    <div class="col-xs-3">
        <a val="4D" attr="${sort!''}" class="change_sort <#if sort='44D' || sort='4D'>cur</#if> <#if sort='44D'>s_up<#elseif sort='4D'>s_down</#if>" href="javascript:;">人气</a>
    </div>
</div><!-- /goods_filter -->
<div class="goods_list container-fluid">
<#if map.pb.data??>
	<#list map.pb.data as product>
    <#--设置该商品的价格与库存-->
        <#assign productPrice=product.goodsInfoPreferPrice>
        <#assign stock=product.goodsInfoStock>
        <#if product.wareList??&&product.wareList?size &gt;0>
            <#list product.wareList as ware>
                <#if ware.wareId==wareId>
                    <#assign productPrice=ware.warePrice>
                    <#assign stock=ware.wareStock>
                    <#break>
                </#if>
            </#list>
        </#if>

        <div class="col-xs-6">
            <a href="${basePath}/item/${product.goodsInfoId}.html">
                <img class="lazy" alt="" data-original="<#if  product.imgList?? && product.imgList?size &gt; 0 >${product.imgList[0].imageBigName!''}</#if>">
                <noscript><img alt="" src="<#if  product.imgList?? && product.imgList?size &gt; 0 >${product.imgList[0].imageBigName!''}</#if>"></noscript>
                <span>${product.goodsInfoName!''}</span>
                <span>￥${productPrice?string('0.00')}</span>
            </a>
        </div>
	</#list>
</#if>
</div><!-- /goods_list -->

<div class="pages container-fluid">
<#if (map.pb.pageNo==1)>
    <div class="col-xs-6">
        <a class="disabled"  href="javascript:;">&lt;&nbsp;上一页</a>
    </div>
<#else>
    <div class="col-xs-6">
        <a class="changePages" pages="${map.pb.prePageNo}" href="javascript:;">&lt;&nbsp;上一页</a>
    </div>
</#if>
<#if (map.pb.lastPageNo > map.pb.pageNo)>

    <div class="col-xs-6">
        <a class="changePages" pages="${map.pb.nextPageNo}" href="javascript:;">下一页&nbsp;&gt;</a>
    </div>
<#else>
    <div class="col-xs-6">
        <a class="disabled" href="javascript:;">下一页&nbsp;&gt;</a>
    </div>
</#if>
</div><!-- /pages -->
<div class="hide">
    <form action="searchProduct.htm" id="searchForm" method="post">
        <div class="filterList">
            <ul class="clearfix">
			<#if storeId??&&storeId!=0>
                <input type="hidden" name="storeId" value="${storeId}">
			</#if>
                <input type="hidden" name="keyWords" class="title" value="${keyWords!''}">
                <input type="hidden" name="pageNo" class="pageNo" value="${map.pb.pageNo}">
                <input type="hidden" name="sort" class="list_sort" value="${sort!''}">
                <input type="hidden" name="showStock" class="show_stock" value="${showStock!''}">
            </ul>
        </div>
        <!--/filterList-->
    </form>
</div>


<div class="foot">
    <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
</div><!-- /foot -->

<!-- 引用公共脚部 -->
<#include "../common/smart_menu.ftl" />

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${basePath}/js/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${basePath}/js/bootstrap.min.js"></script>
<script src="${basePath}/js/fastclick.min.js"></script>
<script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
<script src="${basePath}/js/jquery.keleyi.js"></script>
<script src="${basePath}/js/jquery.lazyload.js"></script>
<script src="${basePath}/js/goods/goods_list.js"></script>
<!-- <script src="${basePath}/js/customer/wxforward.js"></script>-->
<script>
    $(function(){
        FastClick.attach(document.body);
        $('.cate').click(function(){
            if($(this).attr('class').indexOf('hover')>=0){
                $(this).removeClass('cate_hover');
            }
            else{
                $(this).addClass('cate_hover');
            }
        });
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
        $('img.lazy').lazyload({
            palceholder : 'images/loading.gif',
            effect : 'fadeIn'
        });
        <!-- 以下是分享部分 -->
        var onBridgeReady=function(){
            //发送给朋友
            WeixinJSBridge.on('menu:share:appmessage', function(argv){
                WeixinJSBridge.invoke('sendAppMessage',{
                    "img_url":dataForWeixin.MsgImg,
                    "img_width":"120",
                    "img_height":"120",
                    "link":dataForWeixin.url,
                    "desc":dataForWeixin.desc,
                    "title":dataForWeixin.title
                }, function(res){(dataForWeixin.callback)();});
            });
            //发送到朋友圈
            WeixinJSBridge.on('menu:share:timeline', function(argv){
                WeixinJSBridge.invoke('shareTimeline',{
                    "img_url":dataForWeixin.MsgImg,
                    "img_width":"120",
                    "img_height":"120",
                    "link":dataForWeixin.url,
                    "desc":dataForWeixin.desc,
                    "title":dataForWeixin.title
                }, function(res){(dataForWeixin.callback)();});});
        };
        if(document.addEventListener){
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        }else if(document.attachEvent){
            document.attachEvent('WeixinJSBridgeReady'   , onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady' , onBridgeReady);
        }
    });

    var dataForWeixin={
        MsgImg:"http://mobile.ningpai.com/app/h5/images/intro.jpg",
        url:"<#if map.nowcate?? >http://shop.ningpai.com/mobile/getwxcode3.htm?toUrl=list/${map.nowcate.catId}.html<#else>http://shop.ningpai.com/mobile/getwxcode3.htm?toUrl=list/allproduct.html</#if>",
        title:"发现一些好商品",
        desc:"<#if map.nowcate?? >${map.nowcate.catName}<#else>所有商品</#if>",
        callback:function(
                //这里是分享成功后的回调功能
        ){}
    };
</script>
</body>
</html>
