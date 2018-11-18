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
    <title><#if map.detailBean.productVo.goods.goodsSeoTitle?length &gt; 0> ${map.detailBean.productVo.goods.goodsSeoTitle!''}<#else>${map.detailBean.productVo.productName!''}</#if>${sys.bsetName}</title>
<#else>
    <title><#if map.detailBean.productVo.goods.goodsSeoTitle?length &gt; 0> ${map.detailBean.productVo.goods.goodsSeoTitle!''}<#else>${map.detailBean.productVo.productName!''}</#if>${seo.mete}</title>
</#if>

    <script src="${basePath}/js/jquery-1.11.1.min.js"></script>
    <script src="${basePath}/js/goods/addcartype.js"></script>


    <!-- Bootstrap -->
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css?v=201606091615" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${basePath}/js/html5shiv.min.js"></script>
    <script src="${basePath}/js/respond.min.js"></script>
    <![endif]-->
    <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script>
 <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
<body style="background:#eee;">
<input type="hidden" id="basePath" value="${basePath!''}">
<a href="<#if backLevUrl??>${basePath}/${backLevUrl}<#else>javascript:history.go(-1);</#if>" class="backBtn"><img src="${basePath}/images/qp_sf.png"></a>
<div class="roll_banner">
    <div class="swiper-container">
        <div class="swiper-wrapper">
        <#if map.detailBean.productVo.imageList??>
            <#list map.detailBean.productVo.imageList as image>
                <div class="swiper-slide"><a href="#"><img src="${image.imageBigName!''}"></a></div>
            </#list>
        </#if>
        </div>
        <div class="radiusNav"><ul class="rad"><li></li><li></li><li></li></ul><ul class="nav dis" id="startmenu"><li>搜索</li><li style="border-bottom: none;">首页</li><li id="goodsshare">分享</li></ul></div>
    </div>
    <div class="swiper-pagination"></div>
</div>
<!-- /roll_banner -->

<div class="good_simple_info">
    <input type="hidden" id="goodsId" value="${map.detailBean.productVo.goodsId}"/>
    <input type="hidden" id="productId" value="${map.detailBean.productVo.goodsInfoId}"/>
    <input type="hidden" id="brandId" value="${map.detailBean.productVo.goods.brandId}"/>
    <input type="hidden" id="productStock" value="${map.detailBean.productVo.goodsInfoStock}"/>
    <input type="hidden" id="catId" value="${map.detailBean.productVo.goods.catId}"/>
    <input type="hidden" id="goodsInfoAdded" value="${map.detailBean.productVo.goodsInfoAdded}"/>

    <h1>${map.detailBean.productVo.productName!''}</h1>
    <h2>${map.detailBean.productVo.subtitle!''}</h2>
    <input type="hidden" id="oldPrice" value="<#if vip?? && vip=='1'>${map.detailBean.productVo.goodsInfoVipPrice?string('0.00')}<#else>${map.detailBean.productVo.goodsInfoPreferPrice?string('0.00')}</#if>" />

    <div class="promotions" style="min-height:38px;margin-left:-16px;">
        <input type="hidden" name="maxbuynum" id="buynum"value=""/>
        <h4 style="text-align: left">促&nbsp;&nbsp;销：</h4>
        <ul class="pro_market">
        </ul>
    </div>

    <div class="price">
        <span style="font-size: 22px;color:#df1738;" class="main_price"><small>￥</small><#if vip?? && vip=="1">${map.detailBean.productVo.goodsInfoVipPrice?string("0.00")}<#else>${map.detailBean.productVo.goodsInfoPreferPrice?string("0.00")}</#if></span>
        <em><#if vip?? && vip=="1">会员价<#else>零售价</#if></em>
    </div>
    <div class="cxyj price" hidden="hidden">
        <span class="oldprice" style="text-decoration: line-through"></span>
        <em><#if vip?? && vip=='1'>会员价<#else>零售价</#if></em>
    </div>
    <div class="cxj price" hidden="hidden"><span class="mark_price" style="color:#df1738;font-size: 26px;"></span><em id="priceflr">促&nbsp;销&nbsp;价</em></div>
    <div class="choose_item container-fluid">
        <div class="row">
            <h4 style="text-align: left">数量</h4>

            <div class="countBtn col-xs-12">
                <div class="count">
                    <a class="minus num_minus" href="javascript:"><span>-</span></a>
                    <input type="text" value="1" id="produsubctcount" class="count_num product_count">
                    <a class="plus num_plus" href="javascript:"><span>+</span></a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /good_simple_info -->
<div class="spec">
<#if (map.openSpec??)>

    <#list map.openSpec as sp>
        <#assign displayClass = (sp.spec.specName??&&sp.spec.specName == '.')?string('hideTag','showTag')>
    <div class="type ${displayClass}">
        <span>${sp.spec.specName}：</span>
        <ul>
        <#list sp.specValList as detail>
            <li id="selectSpecId${detail.specDetail.specDetailId}" <#if map.specIds?? && map.specIds?seq_contains('${detail.specDetail.specDetailId?string}')> isselect="1" style="border: 2px solid rgb(245, 172, 2); color: rgb(245, 172, 2);
                    background-image: url(${basePath}/images/ok.png); background-color: rgb(255, 255, 255);"<#else >isselect="0"</#if>
                    data-parent="${detail.specDetail.specId}"
                    data-value="${detail.specDetail.specDetailId}" >${detail.specValueRemark!} </li>
        </#list>
        </ul>
    </div>
    </#list>
    </#if>
   <#-- <div class="size">
        <span>大小：</span>
        <ul>
            <li style="border: 2px solid rgb(245, 172, 2); color: rgb(245, 172, 2); background-image: url(${basePath}/images/ok.png); background-color: rgb(255, 255, 255);">大</li>
            <li>中</li>
            <li>小</li>
        </ul>
    </div>-->
</div>
<div class="buy_choose">
    <input type="hidden" id="allSpecLength" value="${map.detailBean.productVo.specVo?size}"/>
<#--<#list map.openSpec as spec>
    <#assign displayClass = (spec.spec.specName??&&spec.spec.specName == '.')?string('hideTag','showTag')>
    <div class="choose_item container-fluid ${displayClass}">
        <div class="row">
            <h4 style="text-align: left">${spec.spec.specName}</h4>
        </div>
        <div class="row">
            <#list spec.specValList as specvalue>
                <div class="col-xs-4">
                    <a onClick="clickSpecDetail(this,false);" data-parent="${spec.spec.specId}"
                       data-value="${specvalue.specDetail.specDetailId}" class="choose_item _sku attr"
                       href="javascript:">${specvalue.specValueRemark!''}</a>
                </div>
            </#list>
        </div>
    </div>
</#list>-->

    <!-- 保存已经选择的规格值 -->
    <div class="hide" id="initspacValue">
    <#if (map.detailBean.productVo.specVo??)>
        <#list map.detailBean.productVo.specVo as spec>
            <input type="hidden" id="specid${spec.spec.specId}" class="chooseParamId" value="${spec.goodsSpecDetail.specDetailId}"
                   data-spec="${spec.spec.specName}" data-detail="${spec.specValueRemark!''}"/>
        </#list>
    </#if>
    </div>
<style>
    .choose_item .row h4, .promotions h4 {
        text-indent: 15px;
    }

    .promotions, .choose_item .row {
        padding-left: 2.5em;
    }

    /*.promotions, .choose_item .row h4{text-indent:0;}*/
</style>
<!-- /promotions -->

<#--<div class="buy_choose">
    <input type="hidden" id="allSpecLength" value="${map.detailBean.productVo.specVo?size}"/>
<#list map.openSpec as spec>
    <#assign displayClass = (spec.spec.specName??&&spec.spec.specName == '.')?string('hideTag','showTag')>
    <div class="choose_item container-fluid ${displayClass}">
        <div class="row">
            <h4 style="text-align: left">${spec.spec.specName}</h4>
        </div>
        <div class="row">
            <#list spec.specValList as specvalue>
                1111
                <div class="col-xs-4">
                    <a onClick="clickSpecDetail(this,false);" data-parent="${spec.spec.specId}"
                       data-value="${specvalue.specDetail.specDetailId}" class="choose_item _sku attr"
                       href="javascript:">${specvalue.specValueRemark!''}</a>
                </div>
            </#list>
        </div>
    </div>
</#list>

    <!-- 保存已经选择的规格值 &ndash;&gt;
    <div class="hide">
    <#if (map.detailBean.productVo.specVo??)>
        <#list map.detailBean.productVo.specVo as spec>
            <input type="hidden" class="chooseParamId" value="${spec.goodsSpecDetail.specDetailId}"
                   data-spec="${spec.spec.specName}" data-detail="${spec.specValueRemark!''}"/>
        </#list>
    </#if>
    </div>-->

<#--<div class="choose_item container-fluid" onclick="addFollow()">
    <div id="addFollow" class="row" num="0" style="margin-left:-2.5em;">
    &lt;#&ndash; <h4 style="text-align: left">点击添加关注</h4>&ndash;&gt;
        <i><img id="isFollow" src="${basePath}/images/s-love.png"/></i> 收藏
    </div>
</div>-->
<#if map.detailBean.productVo.thirdId!=0>
    <div class="promotions">
        <h4 style="text-align: left">商家</h4>
        <ul class="pro_market">
            <a href="${basePath}/thirdStoreIndex.htm?storeId=${map.detailBean.productVo.thirdId}">${map.detailBean.productVo.thirdName!''}</a>
        </ul>
    </div>
    <!-- /promotions -->

</#if>
<#--<a href="#" onclick="WeiXinAddContact('ningpaiwx')">联系商家</a>-->
</div>
<!-- /buy_choose -->

<#--<div class="adapCarType">
    <span>适配车型</span><i></i>
</div>

<div class="carTypeBox dis">
    <div class="adap">
        <h3><i></i>适配车型</h3>

    </div>
</div>-->

<div class="goods_comment">
    <div class="goods_navtop">
        <a href="${basePath}/toEvaluateList.htm?goodsId=${map.detailBean.productVo.goodsInfoId}"><span>商品评价</span><small>好评率<em>
        <#if (commDetail.sumNumber>0)>${(((commDetail.goodSumNumber?number)/(commDetail.sumNumber?number))*100)?string("0")}
        </#if>%</em></small><strong><em>${commDetail.userSumNumber!'0'}</em>人评价</strong><i></i></a>
    </div>
    <div class="commentList">
        <ul>
            <#if (commDetail.commList?? && commDetail.commList?size>0)>
            <#list commDetail.commList as list  >
                <#if (list_index>=0) &&(list_index<2)>
                <li>
                    <div class="myMes" score="${list.commentScore?string("0")}">
                        <span><img src="${list.customerImg!}"></span>
                        <strong style="font-size:16px;">${list.customerNickname!}</strong><br>
                        <em style="font-size:12px;">${list.publishTime?string("yyyy年MM月dd日 HH时mm分")}</em>
                        <ol><li></li><li></li><li></li><li></li><li></li></ol>
                    </div>
                    <div class="talk">
                        <p style="word-wrap:break-word;">${list.commentContent!}</p>
                        <#if (list.imageList?? && list.imageList?size>0)>
                            <#list list.imageList as imga>
                                <img src="${imga.imageName}">
                            </#list></#if>
                    </div>
                </li>
                </#if>
            </#list>

            <#else>
           <span style="margin-left:20px;">暂无评论!</span>
            </#if>
        </ul>
        <div class="btnComment">
            <p><a href="${basePath}/toEvaluateList.htm?goodsId=${map.detailBean.productVo.goodsInfoId}&share=1">晒单评价(<em>${commDetail.SDSumNumber}</em>)</a></p>
            <p><a href="${basePath}/toEvaluateList.htm?goodsId=${map.detailBean.productVo.goodsInfoId}" class="allComment">全部评价(<em class="count">${commDetail.sumNumber}</em>)</a></p>
        </div>
    </div>
</div>

<div class="good_intro">
    <!--<div class="navbar-wrapper">
      <ul class="nav nav-tabs goodsList" role="tablist">
        <li class="active"><a href="#details" role="tab" data-toggle="tab">商品介绍</a></li>
        <li><a href="#profile" role="tab" data-toggle="tab">规格参数</a></li>
        <li class="load_comment_tit" role="1"><a href="#comment" role="tab" data-toggle="tab">商品评论</a></li>
      </ul>
    </div>-->
    <div class="">
        <ul class="goodsList">
            <li class="actcur"><a href="#details" role="tab" data-toggle="tab" style="color:#F6AB00;">商品信息</a></li>
            <li><a href="#profile" role="tab" data-toggle="tab">规格参数</a></li>
            <#--<li role="1"><a href="#comment" role="tab" data-toggle="tab">用户咨询</a></li>-->
        </ul>
    </div>
    <div class="tab-content">
        <div class="tab-pane active" id="details">
        ${map.detailBean.productVo.goods.mobileDesc!''}
        </div>
        <div class="tab-pane" id="profile">
            <div class="table-responsive">
                <table class="table table-striped table-bordered" width="100%">
                <#list map.detailBean.expandPrams as expandParam1>
                    <#if expandParam1.expangparamValue??&&expandParam1.expangparamValue!=''>
                        <tr>
                            <td width="30%" align="right">${expandParam1.expandParamVo.expandparamName}</td>
                            <td>${expandParam1.expangparamValue.expandparamValueName!''}</td>
                        </tr>
                    </#if>
                </#list>
                <#list map.detailBean.param as expandParam>
                    <#if expandParam.paramValue??&&expandParam.paramValue!=''>
                        <tr>
                            <td width="30%" align="right">${expandParam.param.paramName}</td>
                            <td>${expandParam.paramValue!''}</td>
                        </tr>
                    </#if>
                </#list>
                </table>
            </div>
        </div>
        <div class="tab-pane" id="comment">
            <ul>
                <#if (commDetailZX?? && commDetailZX?size>0)>
                    <#list commDetailZX as zxlist>
                        <li>
                            <p><strong>${zxlist.customerNickname!}</strong><em>${zxlist.publishTime?string("yyyy-MM-dd HH:mm:ss")}</em></p>
                            <p><i>Q</i><span>${zxlist.commentContent!}</span></p>
                            <p><i style="background:#F15353;">A</i>
                                <#if (zxlist.replays?? && zxlist.replays?size>0)>
                                <#list zxlist.replays as replayList>
                                    <span>${replayList.commentContent!}</span>
                                </#list>
                                <#else >
                                暂无
                                </#if>

                            </p>
                        </li>
                    </#list>
                </#if>
            </ul>
        </div>

    </div>
    <!-- /good_intro -->

    <div class="buy_area container-fluid">
        <div class="row botBtn">
            <div class="cent col-xs-2"><a class="kf" href="tel:96008"><img src="${basePath}/images/qp_ze0.png"><br>客服</a></div>
            <div class="cent col-xs-2" onclick="addFollow()"><img id="isFollow" src="${basePath}/images/qp_zf0.png"><br>收藏<span class="dis" id="followMes">收藏成功</span></div>
            <div class="cent col-xs-2"><a class="kf" href="${basePath}/myshoppingmcart.html?tag=-1"><img src="${basePath}/images/qp_zg0.png"><br>购物车</a><i id="count"></i></div>
            <div class="col-xs-6">
                <div class="cent brn col-xs-7">
                    <a href="javascript:" class="add_cart">加入购物车</a>
                </div>
                <div class="cent brn col-xs-5">
                    <a href="javascript:" class="buy_now">立即购买</a>
                    <a href="javascript:" class="none buy_now_tip" data-toggle="modal" data-target="#goods_tips">立即购买</a>
                </div>
            </div>
        </div>
    </div>
    <!-- /buy_area -->


    <div class="modal fade" role="dialog" id="goods_tips">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                            class="sr-only">Close</span></button>
                    <h4 class="modal-title">商城消息</h4>
                </div>
                <div class="modal-body">
                    <p class="tip_text"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onClick="$('#goods_tips').modal('hide');">确定</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->


    <div class="foot" style="padding-bottom:10em;">
        <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div>
    <!-- /foot -->


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/jquery.keleyi.js"></script>
    <script src="${basePath}/js/jquery.lazyload.js"></script>
    <script src="${basePath}/js/goods/goods_detail.js"></script>
    <script src="${basePath}/js/publicModel.js"></script>
    <script src="${basePath}/js/adapCarType.js"></script>
    <script src="${basePath}/js/customer/wxforward.js"></script>
    <script>

        var dataForWeixin = {
            MsgImg: "<#if  map.detailBean.productVo.imageList?? && map.detailBean.productVo.imageList?size &gt; 0 >${map.detailBean.productVo.imageList[0].imageInName!''}</#if>",
            url: "http://wap.yijia360.com/m/item/${map.detailBean.productVo.goodsInfoId}.html",
            title: "发现一个好商品",
            desc: "${map.detailBean.productVo.productName!''}",
            callback: function (//这里是分享成功后的回调功能
            ) {
            }
        };
        $(function () {

            var url = '${basePath}' + '/checkAtte.htm';
            var productId = $("#productId").val();
            $.ajax({
                url: url,
                data: {productId: productId},
                success: function (data) {
                    if (data) {
                        $("#isFollow").attr("src", "${basePath}/images/qp_zf.png");
                    } else {
                        $("#isFollow").attr("src", "${basePath}/images/qp_zf0.png");
                    }
                }
            });

            $(".goodsList li").click(function () {
                $(".goodsList li").removeClass(" actcur").children("a").css("color","#000");
                $(this).addClass("actcur").children("a").css("color","#F6AB00");
                var n = $(this).index();
                if (n == 2) {
                    $("#hao").click();
                }
            });

            /*分享事件*/
            $("#goodsshare").click(function(){
                myObj.share(dataForWeixin.title,dataForWeixin.desc,dataForWeixin.url,dataForWeixin.MsgImg);
            });

            $('.roll_banner,.roll_banner img').css('height', $(window).width() + 'px');
            var mySwiper = new Swiper('.swiper-container', {
                pagination: '.swiper-pagination',
                loop: true,
                grabCursor: true,
                autoplay: 3000
            });

            $('img.lazy').lazyload({
                palceholder: 'images/loading.gif',
                effect: 'fadeIn'
            });


            <!-- 加载规格值区域 -->
            var productList = null;
            $.get("${basePath}/all/" + $("#goodsId").val() + ".html", function (data) {
                productList = data;
                <!-- 把查询到的数据传递到js方法中 -->
                loadAllProduct(productList);
                loadChooseParam();
            });
            <!-- 加载商品促销的信息 -->
            loadGoodsMark();

            <!-- 以下是分享部分 -->
            var onBridgeReady = function () {
                //发送给朋友
                WeixinJSBridge.on('menu:share:appmessage', function (argv) {
                    WeixinJSBridge.invoke('sendAppMessage', {
                        "img_url": dataForWeixin.MsgImg,
                        "img_width": "120",
                        "img_height": "120",
                        "link": dataForWeixin.url,
                        "desc": dataForWeixin.desc,
                        "title": dataForWeixin.title
                    }, function (res) {
                        (dataForWeixin.callback)();
                    });
                });
                //发送到朋友圈
                WeixinJSBridge.on('menu:share:timeline', function (argv) {
                    WeixinJSBridge.invoke('shareTimeline', {
                        "img_url": dataForWeixin.MsgImg,
                        "img_width": "120",
                        "img_height": "120",
                        "link": dataForWeixin.url,
                        "desc": dataForWeixin.desc,
                        "title": dataForWeixin.title
                    }, function (res) {
                        (dataForWeixin.callback)();
                    });
                });
            };
            if (document.addEventListener) {
                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
            } else if (document.attachEvent) {
                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
            }

           //购物车的货品总数
            $.ajax({
                url: '${basePath}/getShoppingCartGoodsSum.htm',
                type: 'post',
                async: false,
                success: function (data) {
                    if(data!=0){
                        $("#count").text(data);
                    }else{
                        $("#count").hide();
                    }
                }
            });
        });
        function WeiXinAddContact(name) {
            WeixinJSBridge.invoke("addContact", {webtype: "1", username: name}, function (e) {
                WeixinJSBridge.log(e.err_msg);
                //e.err_msg:add_contact:added 已经添加
                // e.err_msg:add_contact:cancel 取消添加
                // e.err_msg:add_contact:ok 添加成功
                if (e.err_msg == 'add_contact:added' || e.err_msg == 'add_contact:ok') {
                    //关注成功，或者已经关注过
                }
            })
        }



        //初始化评分的星级
        $(".myMes").each(function(){
            var score = $(this).attr("score");
            for(i=1;i<=score;i++){
                $($(this).find("ol li")[i-1]).css("background-image","url(${basePath}/images/qp_xqpl.png)");
            }
            //n++;
        });

        function addFollow() {
            var url = '${basePath}' + '/saveAtte.htm';
            var productId = $("#productId").val();
            $.ajax({
                url: url,
                data: {productId: productId},
                success: function (data) {
                    if (data == 1) {
                        //添加成功
                        $("#isFollow").attr("src", "${basePath}/images/qp_zf.png");
                        $("#followMes").show().text("收藏成功");
                        setTimeout(function(){
                            $("#followMes").hide();
                        },1000);
                        //alert("成功关注改商品");
                    } else if (data == -1) {
                        //该商品已经关注
                        //alert("该商品已成功关注,详情请看我的收藏");
                        $("#isFollow").attr("src", "${basePath}/images/qp_zf0.png");
                        $("#followMes").show().text("取消收藏");
                        setTimeout(function(){
                            $("#followMes").hide();
                        },1000);
                    } else if (data == -2) {
                        //未登录
                        location.href = '${basePath}/login.html?url=/item/' + productId + '.html';
                    } else {
                        //失败
                        // alert("关注商品失败");
                    }

                }
            });
        }

        $("#startmenu li").click(function(){
            var n = $(this).index();
            switch(n){
                case 0:location.href="${basePath}/searchProduct.htm?keyWords=&storeId=0";break;
                case 1:location.href="${basePath}/main";break;
                default:return;
                //case 2:location.href="${basePath}/main";break;
            }
        });
        $(".radiusNav").click(function () {
            if($(this).attr("num") == "1"){
                $(".radiusNav .nav").hide();
                $(this).attr("num","0");
            }else{
                $(".radiusNav .nav").show();
                $(this).attr("num","1");
            }
        });
        SelectBtn(".spec .type li");
        function SelectBtn(id) {
            $(id).bind("click", function () {
                var _this = this;
                //选择的 规格 的类型的ID  比如 “颜色”的ID
                var parentId = $(_this).attr("data-parent");
                //选择的 规格 的详细的ID  比如 “红色”的ID
                var sepcId = $(_this).attr("data-value");
                //选择的 规格 的详细说明  比如 “红色”
                var specDetail = $(_this).text();
                // 选项的是否选中的属性 0 没有选中 1 已经选中
                var isselect =  $(_this).attr("isselect");
                //改变规格前的原始规格选项的元素ID
                var oldselectId = "selectSpecId"+sepcId;
                //改变规格前的原始规格存贮的隐藏域的元素ID
                var oldInputId = "specid"+parentId;
                //改变规格前的原始规格
                var oldSpecId = $('input[id="specid'+parentId+'"]').val();
                //------

                //（样式改变 input值得改变 商品的值得改变）
                if(isselect != "1"){
                    //input值得改变
                    $('input[id="'+oldInputId+'"]').val(sepcId);
                    $('input[id="'+oldInputId+'"]').attr("data-detail",specDetail);
                    //样式改变 当你选择的是该属性中没有选中的则把以前选中的规格去掉在把这个选中
                    //以前选中的规格去掉
                    $(this).parent().find("li").attr("isselect","0").removeAttr("style");
                    $(this).attr("isselect","1");
                    //把这个选中
                    $(this).css({
                        "border": "2px solid #F5AC02",
                        "background-color": "#fff",
                        "background-image": "url(${basePath}/images/ok.png)",
                        "color": "#F5AC02"
                    });
                    //调用方法来匹配你选中的商品 若能匹配到 则重新初始化该商品   我们这里是刷新页面来做到的
                    getNewSpec(_this,oldselectId,oldInputId,oldSpecId,specDetail);
                }


            });
        }
        //_this  oldselectId - 改变规格前的原始规格选项的元素ID
        // oldInputId 改变规格前的原始规格存贮的隐藏域的元素ID
        // oldSpecId 改变规格前的原始规格ID
        // 逻辑：1首选是每个规格都能匹配的商品   更新商品
        // 逻辑：2若这个匹配不到则会在商品循环中匹配到你所选的这个规格的其中的一个商品（能匹配到的第一个商品） 更新商品
        // 逻辑：3若上面的都匹配不到则不再去匹配而是直接把你选择的商品的规格还原到原始的规格不更新商品
        function getNewSpec(_this,oldselectId,oldInputId,oldSpecId,specDetail){
            var specList = $(".chooseParamId");
            var selSpecList = [];
            /*获取已经选中的规格值*/
            for (var i = 0; i < specList.length; i++) {
                selSpecList.push(specList[i].value);
            }
            var firstProductId = "";
            var pipei01 = false;
            /*根据已经选中的规格值循环所有的货品筛选出可以被选中的货品*/
            for (var j = 0; j < allProductList.length; j++) {
                //获取到货品的关联的规格
                var goodsSpec = allProductList[j].specVo;
                var count = 0;
                //循环货品的规格去选中的规格中匹配
                for (var k = 0; k < goodsSpec.length; k++) {
                    for (var i = 0; i < selSpecList.length; i++) {
                        //（逻辑：2）
                        //如果（逻辑：2）没有匹配到而这个商品的属性可以匹配（逻辑：2）则把这个商品存到 firstProductId
                        if(firstProductId =="" &&goodsSpec[k].goodsSpecDetail.specDetailId == $(_this).attr("data-value")){
                            firstProductId = allProductList[j].goodsInfoId;
                        }
                        //（逻辑：1）
                        if (goodsSpec[k].goodsSpecDetail.specDetailId == selSpecList[i]) {
                            //匹配成功
                            count++;
                        }
                    }
                }
                //alert($(_this).attr("data-value"));
                //（逻辑：1）匹配成功
                if (count >= selSpecList.length) {
                    if ($("#allSpecLength").val() == count) {
                        pipei01 = true ;
                        location.href = allProductList[j].goodsInfoId + ".html";
                    }
                }
            }
            //-----------------for end
            //如果 （逻辑：1）失败 开始匹配 （逻辑：2）
            if(!pipei01 && firstProductId != ""){
                location.href = firstProductId + ".html";
            }else{
                //都匹配失败  进入逻辑3.
                $('input[id="'+oldInputId+'"]').val(oldSpecId);
                $('input[id="'+oldInputId+'"]').attr("data-detail",specDetail);
                //样式改变 当你选择的是该属性中没有选中的则把以前选中的规格去掉在把这个选中
                //以前选中的规格去掉
                $(oldselectId).parent().find("li").attr("isselect","0").removeAttr("style");
                //把这个选中
                $(oldselectId).attr("isselect","1");
                $(oldselectId).css({
                    "border": "2px solid #F5AC02",
                    "background-color": "#fff",
                    "background-image": "url(${basePath}/images/ok.png)",
                    "color": "#F5AC02"
                });
            }

        }
    </script>
</body>
</html>
