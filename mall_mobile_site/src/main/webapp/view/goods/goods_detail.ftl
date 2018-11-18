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


    <!-- Bootstrap -->
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">
        <script type="text/javascript" src="${basePath}/js/jquery-1.11.1.min.js"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]> 
      <script src="${basePath}/js/html5shiv.min.js"></script>
      <script src="${basePath}/js/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <div class="roll_banner">
      <div class="swiper-container">
      	<div class="swiper-wrapper">
      		<#if map.detailBean.productVo.imageList??>
	      		<#list map.detailBean.productVo.imageList as image>
	      			<div class="swiper-slide"><a href="#"><img alt="javascript:;" src="${image.imageBigName!''}"></a></div>
				</#list>
			</#if>
        </div>
      </div>
      <div class="swiper-pagination"></div>
    </div><!-- /roll_banner -->
    
    <div class="good_simple_info">
      <input type="hidden" id="goodsId" value="${map.detailBean.productVo.goodsId}" />
      <input type="hidden" id="productId" value="${map.detailBean.productVo.goodsInfoId}" />
      <input type="hidden" id="brandId" value="${map.detailBean.productVo.goods.brandId}" />
      <input type="hidden" id="productStock" value="${map.detailBean.productVo.goodsInfoStock}" />
      <input type="hidden" id="catId" value="${map.detailBean.productVo.goods.catId}" />
      <input type="hidden" id="goodsInfoAdded" value="${map.detailBean.productVo.goodsInfoAdded}" />
      <h1>${map.detailBean.productVo.productName!''}</h1>
      <h2>${map.detailBean.productVo.subtitle!''}</h2>
        <input type="hidden" id="oldPrice"  value="${map.detailBean.productVo.goodsInfoPreferPrice?string("0.00")}">
      <div class="price">价&nbsp;&nbsp;格：<span style="font-size: 26px;color:#df1738;">￥${map.detailBean.productVo.goodsInfoPreferPrice?string("0.00")}</span></div>
      <div hidden="hidden">原&nbsp;&nbsp;&nbsp;价：<span class="oldprice" style="text-decoration: line-through"></span></div>
      <div  hidden="hidden">促销价：<span class="mark_price" style="color:#df1738;font-size: 26px;"></span></div>
    </div><!-- /good_simple_info -->

    <style>
        .choose_item .row h4, .promotions h4{width:3.5em;text-indent:5px;}
        .promotions, .choose_item .row{padding-left:3.5em;}
        .promotions, .choose_item .row h4{text-indent:0;}
    </style>
    
    <div class="promotions">
      <h4 style="text-align: left">促销</h4>
      <ul class="pro_market">
      </ul>
    </div><!-- /promotions -->
    
    <div class="buy_choose">
      	<input type="hidden" id="allSpecLength" value="${map.detailBean.productVo.specVo?size}" />
      	<#list map.openSpec as spec>
	      <div class="choose_item container-fluid">
		      	<div class="row">
		          <h4 style="text-align: left">${spec.spec.specName}</h4>
		        </div>
		        <div class="row">
		        	<#list spec.specValList as specvalue>
			          <div class="col-xs-4">
			            <a onClick="clickSpecDetail(this,false);" data-parent="${spec.spec.specId}" data-value="${specvalue.specDetail.specDetailId}" class="choose_item _sku attr" href="javascript:;">${specvalue.specValueRemark!''}</a>
			          </div>
			        </#list>
		        </div>
	      </div>
	    </#list>
      
      <!-- 保存已经选择的规格值 -->
		<div class="hide">
			<#if (map.detailBean.productVo.specVo??)>
				<#list map.detailBean.productVo.specVo as spec>
					<input type="hidden" class="chooseParamId" value="${spec.goodsSpecDetail.specDetailId}" data-spec="${spec.spec.specName}" data-detail="${spec.specValueRemark!''}" />
				</#list>
			</#if>
		</div>
      <div class="choose_item container-fluid">
      	<div class="row">
         <h4 style="text-align: left">数量</h4>
          <div class="col-xs-12">
            <div class="count">
              <a class="minus num_minus" href="javascript:;"><span>-</span></a>
              <input type="text" value="1" class="count_num product_count">
              <a class="plus num_plus" href="javascript:;"><span>+</span></a>
            </div>
          </div>
        </div>
      </div>
    <#if map.detailBean.productVo.thirdId!=0>
        <div class="promotions">
            <h4 style="text-align: left">商家</h4>
            <ul class="pro_market">
                <a href="${basePath}/thirdStoreIndex.htm?storeId=${map.detailBean.productVo.thirdId}">${map.detailBean.productVo.thirdName!''}</a>
            </ul>
        </div><!-- /promotions -->

    </#if>
        <#--<a href="#" onclick="WeiXinAddContact('ningpaiwx')">联系商家</a>-->
    </div><!-- /buy_choose -->

    <div class="good_intro">
      <div class="navbar-wrapper">
        <ul class="nav nav-tabs" role="tablist">
          <li class="active"><a href="#details" role="tab" data-toggle="tab">商品介绍</a></li>
          <li><a href="#profile" role="tab" data-toggle="tab">规格参数</a></li>
          <li class="load_comment_tit" role="1"><a href="#comment" role="tab" data-toggle="tab">商品评论</a></li>
        </ul>
      </div>
      <div class="tab-content">
        <div class="tab-pane active" id="details">
          ${map.detailBean.productVo.goods.mobileDesc!''}
        </div>
        <div class="tab-pane" id="profile">
          <div class="table-responsive">
            <table class="table table-striped table-bordered" width="100%">
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
          <div class="comment_filter">
            <a class="cur load_comment" role="0" href="javascript:;">好评(<span class="hao_count">0</span>)</a>
            <a class="load_comment" role="1" href="javascript:;">中评(<span class="zhong_count">0</span>)</a>
            <a class="load_comment" role="2" href="javascript:;">差评(<span class="cha_count">0</span>)</a>
          </div>
          <div class="comment_list comment_item_list">
          </div>
          <div class="pages comment_pages container-fluid">
          </div><!-- /pages -->
        </div>
      </div>
    </div><!-- /good_intro -->

    <div class="buy_area container-fluid">
      <div class="row">
      	<div class="col-xs-6">
          <a href="javascript:;" class="buy_btn buy_now" >立即购买</a>
          <a href="javascript:;" class="none buy_now_tip"  data-toggle="modal" data-target="#goods_tips">立即购买</a>
        </div>
        <div class="col-xs-6">
          <a href="javascript:;" class="cart_btn add_cart">加入购物车</a>
        </div>
      </div>
    </div><!-- /buy_area -->
    
     
     <div class="modal fade" role="dialog" id="goods_tips">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">商城消息</h4>
          </div>
          <div class="modal-body">
            <p class="tip_text"></p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onClick="$('#goods_tips').modal('hide');">确定</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    
    
    
    
    <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/jquery.keleyi.js"></script>
    <script src="${basePath}/js/jquery.lazyload.js"></script>
    <script src="${basePath}/js/goods/goods_detail.js"></script>
    <!--<script src="${basePath}/js/customer/wxforward.js"></script>-->
    <script>
        $(function(){
            FastClick.attach(document.body);
            $('.roll_banner,.roll_banner img').css('height',$(window).width() + 'px');
            var mySwiper = new Swiper('.swiper-container',{
                pagination: '.swiper-pagination',
                loop:true,
                grabCursor: true,
                autoplay : 3000
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


            <!-- 加载规格值区域 -->
            var productList=null;
            $.get("${basePath}/all/"+$("#goodsId").val()+".html",function(data){
                productList=data;
                <!-- 把查询到的数据传递到js方法中 -->
                loadAllProduct(productList);
                loadChooseParam();
            });
            <!-- 加载商品促销的信息 -->
            loadGoodsMark();

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
        function WeiXinAddContact(name){
            WeixinJSBridge.invoke("addContact", {webtype: "1",username: name}, function(e) {
                WeixinJSBridge.log(e.err_msg);
            //e.err_msg:add_contact:added 已经添加
            // e.err_msg:add_contact:cancel 取消添加
            // e.err_msg:add_contact:ok 添加成功
           if(e.err_msg == 'add_contact:added' || e.err_msg == 'add_contact:ok'){
              //关注成功，或者已经关注过
            }
             })
        }


                var dataForWeixin={
                    MsgImg:"<#if  map.detailBean.productVo.imageList?? && map.detailBean.productVo.imageList?size &gt; 0 >${map.detailBean.productVo.imageList[0].imageInName!''}</#if>",
                    url:"http://shop.ningpai.com/mobile/getwxcode3.htm?toUrl=item/${map.detailBean.productVo.goodsInfoId}.html",
                    title:"发现一个好商品",
                    desc:"${map.detailBean.productVo.productName!''}",
                    callback:function(
                            //这里是分享成功后的回调功能
                    ){}
                };

	</script>
  </body>
</html>
