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
    	<input type="hidden" id="b
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	setDesc" value="${(sys.bsetDesc)!''}">
    <#else>
	    <title>${(seo.mete)!''}</title>
	    <input type="hidden" id="bsetName" value="${(seo.mete)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
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

</script>
  </head>
  <body>
	<div class="single_ask">
        <p>${(map.orderSchedule.orderRemark)!""}</p> 
        <div class="ask_figure">
            <img src="${(map.cust.customerImg)!""}" width="70" height="70"/><br/>
            <div class="ask_talk">${(map.cust.customerUsername)!""}</div>
        </div><!--ask_figure-->
        <div class="down_arrow"> 
            <div class="arrow-down"></div>
        </div>
    </div><!--single_ask-->
    <div class="pay_detail">
		<!--代付订单-->
		<div class="pay_title">来自${map.order.shippingPerson}的代付订单</div>
		<div class="check_goods">
	   <#list map.order.orderGoodsList as orderGoods>
	      <div class="check_goods_item">
	        <div class="img"><a href="${basePath}/item/${orderGoods.goodsProductVo.goodsInfoId}.html"><img alt="" src="<#if orderGoods.goodsProductVo.goodsInfoImgId??>${orderGoods.goodsProductVo.goodsInfoImgId}</#if>"></a></div>
	        <div class="word">
	          <p><a href="${basePath}/item/${orderGoods.goodsProductVo.goodsInfoId}.html">${orderGoods.goodsProductVo.goodsInfoName}</a></p>
	        </div>
	        <div class="price">
	          <p>￥${(orderGoods.goodsInfoPrice)?string("0.00")}</p>
	          <p>×${orderGoods.goodsInfoNum}</p>
	        </div>
	      </div>
	     </#list>
      <div class="total_price">
        <span>商品总价：</span>
        <p>￥${(map.order.orderPrice)?string("0.00")}</p>
      </div>
    </div>
	</div><!--pay_detail-->
	<form method="post" action="order/inertotherpaysingle.html" class="subForm">
	 <input type="hidden" value="${map.order.orderCode}" name="orderCode">
     <input type="hidden" value="${map.order.orderId}" name="orderId">
    <div class="pay_infor">
        <div class="pay_title">付款人信息</div>
        <div class="infor_detail">
            <div class="fill_infor">
                <span>我的姓名</span>
                <input type="text" name="orderPayName"  placeholder="请输入您的姓名"/>
            </div><!--fill_infor-->
            <div class="fill_infor">
                <span>给他留言</span>
                <textarea  name="orderPayRemark">已帮你付了，记得下次请我吃饭</textarea>
            </div><!--fill_infor-->

        </div><!--infor_detail-->   
    </div><!--pay_infor-->
    <div class="infor_detail" style="margin-top:1em;">
        <div class="fill_infor">
             <span>付款金额</span>
             <input type="text" readonly="readonly"  value="${(map.order.orderPrice)?string("0.00")}"  class="cash"/>
        </div><!--fill_infor-->
    </div>
    <div class="pay_area">
      <a href="javascript:;" onclick="subForm()" class="btn-success btn-block" role="button" >微信安全支付</a>
    </div>
    <div class="pay_area">
        <a href="${basePath}/main.html" class="look_d">我也要去玩</a>
    </div>
    <div class="pay_area">
        <a href="###" class="look_d" onClick="showTips('.share_tips')">找小伙伴帮TA付款</a>
    </div>
    </form>
    <div class="other_link">
         <a href="${basePath}/main.html">商城主页</a>
        <a href="${basePath}/customer/index.html">会员中心</a>
        <a href="###">联系我们</a>
    </div>
     <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->
    
    <a href="javascript:;" class="none tan_k"  data-toggle="modal" data-target="#goods_tips"></a>
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
    <div class="share_tips" onClick="shutTips('.share_tips')"></div>
    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/jquery.lazyload.js"></script>
    <script src="${basePath}/js/share.js"></script>
    <script>
      function showTips(pid){
        $(pid).show().height($('body').height()).css('backgroundPositionY',$(window).scrollTop() + 10 + 'px');
      }
      function shutTips(pid){
        $(pid).hide();
      }
      $(".xiugai").click(function() {
          $(".cash").focus();
      });
      function subForm(){
		 $(".subForm").submit();
	  }
    </script>
</body>
</html>