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
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">
    

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]> 
      <script src="${basePath}/js/html5shiv.min.js"></script>
      <script src="${basePath}/js/respond.min.js"></script>
    <![endif]-->
 <script type="text/javascript" src="${basePath}/js/shoppingcart/jsOperation.js"></script>

</script>
   <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
  <body>
    
    <div class="check_goods">
   	<!--计算boss商品数量-->
    <#assign bossNum=0>
      <!--记录第三方商品数量-->
    <#assign thirdNum=0>
      <!--商品的总价格-->
    <#assign sumprice=0>
      <!--商品优惠价格-->
    <#assign preprice=0>
     <form action="" method="post" class="sub_mit">
     <input type="hidden" value="" class="couponIdX" name="codeNo">
    <input type="hidden" value="1" id="ch_express" name="ch_express">
 <#list map.thirdIds as thirdId>
    <#list map.shoplist as cart>
        <#if cart.goodsDetailBean.productVo.thirdId?? && cart.goodsDetailBean.productVo.thirdId==thirdId>
        <#if thirdId!=0>
            <#assign thirdNum=thirdNum+1>
        <#elseif thirdId==0>
            <#assign bossNum=bossNum+1>
        </#if>
    <input type="hidden" value="${thirdId}" name="thirdId">
       <input type="hidden" value="${cart.isBaoyou}" name="isBaoyou">
        <input type="hidden" value="${cart.shoppingCartId}" name="box">
      <div class="check_goods_item">
       <input type="hidden" value="${cart.shoppingCartId}" name="shoppingCartId">
        <div class="img"> <a href="${basePath}/item/${cart.goodsDetailBean.productVo.goodsInfoId}.html" target="_blank" title="${basePath}/item/${cart.goodsDetailBean.productVo.goodsInfoId}.html"><img  title="${cart.goodsDetailBean.productVo.productName}" alt="${cart.goodsDetailBean.productVo.productName}" src="<#if cart.goodsDetailBean.productVo.goodsInfoImgId??>${cart.goodsDetailBean.productVo.goodsInfoImgId}</#if>" /> </a></div>
        <div class="word">
          <p><a href="${basePath}/item/${cart.goodsDetailBean.productVo.goodsInfoId}.html">${cart.goodsDetailBean.productVo.productName}</a></p>
      	  <p class="text-muted">
	          <#if cart.marketing??>
	          	优惠信息：${cart.marketing.marketingName}
	          </#if>
      	 	</p>
        </div>
        <div class="price">
          <p>
           <#assign straight=0>
	          <#if cart.marketing??>
		             <#if (cart.marketing.codexType='1')>
			         <#if ((cart.goodsDetailBean.productVo.goodsInfoPreferPrice-cart.marketing.priceOffMarketing.offValue)?number &gt; 0 ) >
			         	     ￥ ${(cart.goodsDetailBean.productVo.goodsInfoPreferPrice-cart.marketing.priceOffMarketing.offValue)?string("0.00")}
			               <#assign straight="${straight?number+cart.marketing.priceOffMarketing.offValue}">
					 <#else>
                            ￥0.01
							<#assign straight="${cart.goodsDetailBean.productVo.goodsInfoPreferPrice-0.01}">
					 </#if>	
						
					 <#elseif cart.marketing.codexType='4'>
                         ${((cart.goodsDetailBean.productVo.goodsInfoPreferPrice-((1-cart.marketing.discountMarketing.discountValue)*cart.goodsDetailBean.productVo.goodsInfoPreferPrice)))?string("0.00")}
                         <#assign straight="${straight?number+(((1-cart.marketing.discountMarketing.discountValue)*cart.goodsDetailBean.productVo.goodsInfoPreferPrice))}">
					<#else>
                     ￥${cart.goodsDetailBean.productVo.goodsInfoPreferPrice?string("0.00")}
			        </#if>   
			        
			 <#else>
			                      ￥${cart.goodsDetailBean.productVo.goodsInfoPreferPrice?string("0.00")}
			</#if>  
          </p>
          <#assign sumprice="${sumprice?number+(cart.goodsDetailBean.productVo.goodsInfoPreferPrice-(straight)?number)*cart.goodsNum}">
          <p>×${cart.goodsNum}</p>


        </div>
      </div>
      <#if cart.marketing??>
       	<#if cart.marketing.codexType='5'>
			<#if cart.marketing.fullbuyReduceMarketing.fullPrice<=(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)>
				 <#if (((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-cart.marketing.fullbuyReduceMarketing.reducePrice)?number &gt; 0 ) > 
			  		   <#assign preprice="${preprice?number+(cart.marketing.fullbuyReduceMarketing.reducePrice)}">
				 <#else>
					   <#assign preprice="${straight?number+((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-0.01)}">
				</#if>	
			<#else>
			</#if>
  	  <#elseif cart.marketing.codexType='8'>
				<#if ((cart.goodsDetailBean.productVo.goodsInfoPreferPrice-(straight)?number)*cart.goodsNum>=cart.marketing.fullbuyDiscountMarketing.fullPrice)>
					<#if (cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum-(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum*(1-cart.marketing.fullbuyDiscountMarketing.fullbuyDiscount)))?number!=0>
						<#assign preprice="${preprice?number+(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum*(1-cart.marketing.fullbuyDiscountMarketing.fullbuyDiscount))}">
					<#else>
						  <#assign preprice="${straight?number+((cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-0.01)}">
					</#if>
          		</#if>
       </#if>
       </#if>
        </#if>
     </#list>
 </#list>
     <div class="total_price">
        <span>商品总价：</span>
        <p>￥${(sumprice?number)?string("0.00")}</p>
      </div>
      <div class="cut_price">
        <span>优惠金额：</span>
        <p>-￥${(preprice?number)?string("0.00")}</p>
      </div>
    </div><!-- /check_goods -->
    
   <div class="ship">
      <a href="javascript:;" class="ship_type">
        <span>支付方式：</span>
        <p class="pay_text"> <#list payList as pl>
      	<#if pl.isOpen=="1">
        	  <#if ch_pay2??><#if ch_pay2==pl.paymentId>${pl.name}</#if><#else><#if pl_index==0>${pl.name}</#if></#if>
        </#if>
        </#list></p>
      </a>
      <ul class="ship_select">
        <#list payList as pl>
      	<#if pl.isOpen=="1">
        	<li> 
	          <div class="radio">
		            <label class="pay_type"><input type="radio"  value="${pl.paymentId}" 
		            <#if ch_pay2??&&ch_pay2==pl.paymentId>checked<#else><#if pl_index==0>checked</#if></#if>
		            pay-val="${pl.name}" name='ch_pay'  >${pl.name}</label>
	          </div>
	        </li>
        </#if>
        </#list>
       
      </ul>
    </div><!-- /ship -->
  <#if bossNum!=0>
  <div class="ship">
  <a href="javascript:;" class="ship_type">
  <span>配送方式：</span>
  <p class="pr_text">
  <#if frightlist?size!=0>
  <#list frightlist as freight>

          <#if freight_index==0>${freight.freightTemplate.freightTemplateName}</#if>

  </#list>
  <#else>
  快递配送
  </#if>
  </p>
  </a>
  <ul class="ship_select">
 <#if frightlist?size!=0>
  <#list frightlist as freight>
     <li>
      <div class="radio">
      <label class="fr_type"><input type="radio"  value="${freight.distributionId}"
          <#if freight_index==0>checked</#if>
      pay-val="${freight.freightTemplate.freightTemplateName}"name="ch_expres" class='ch_express'  >${freight.freightTemplate.freightTemplateName}</label>
      </div>
      </li>

  </#list>
 <#else>
 <li>
 <div class="radio">
 <label class="fr_type"><input type="radio" name="ch_expres" value=""
    checked
 pay-val="快递配送"  >快递配送</label>
 </div>
 </li>
 </#if>
  </ul>
  </div><!-- /ship -->
  </#if>
<#if thirdNum!=0>
<div class="ship">
<a href="javascript:;" class="ship_type">
<span>商家配送：</span>
<p class="third_text">其中有${thirdNum}样商品,由商家选择合作快递为您配送</p>
</a>
<ul class="ship_select">

        <li>
        <div class="radio">
        <label class="third_type"><input type="radio"  value=""
            checked
        pay-val="快递配送" name='third_e'  >快递配送</label>
        </div>
        </li>


</ul>
</div><!-- /ship -->
</#if>
    
    <div class="ship ship_hover">
      <a href="javascript:;" class="ship_type edit">
        <span>配送地址：</span>
      </a>
    </div><!-- /ship -->
    <input value="${typeId}" name="typeId" type="hidden" class="type_id">
     <#if orderAddress??&typeId=0>
      <div class="address address1">
      <address>
	      	${(orderAddress.addressName)!""}<br>
	      	${(orderAddress.addressPhone)!""}<br>
	        ${(orderAddress.addressDetail)!""}<br>
	      	${(orderAddress.addressDetailInfo)!""}
	      	<input type="hidden" value="${(orderAddress.addressName)!""}" id="addressName" name="addressName"/>
		    <input type="hidden" value="${(orderAddress.addressPhone)!""}" id="addressPhone" name="addressPhone" />
		    <input type="hidden" value="${(orderAddress.addressDetail)!""}" id="addressDetail" name="addressDetail"/>
		    <input type="hidden" value="${(orderAddress.addressDetailInfo)!""}" id="addressDetailInfo" name="addressDetailInfo" />
		    <input type="hidden"  id="proviceFirstStageName" value="${(orderAddress.proviceFirstStageName)!""}" name="proviceFirstStageName" />
		    <input type="hidden"  id="addressCitySecondStageName" value="${(orderAddress.addressCitySecondStageName)!""}" name="addressCitySecondStageName" />
		    <input type="hidden"  id="addressCountiesThirdStageName" value="${(orderAddress.addressCountiesThirdStageName)!""}" name="addressCountiesThirdStageName" />
	      	<input type="hidden" class="addressId" name="addressId" value="0">
	   </address>
	   </div>
       </#if>
      <#if customer??>
    <div class="address address1">
      <#if typeId=0>
	    <address>
	       	${(customer.addressName)!''}  <br>
	        ${(customer.addressMoblie)!''}<br>
	        ${(customer.province.provinceName)!''}省&nbsp;${(customer.city.cityName)!''}市&nbsp;${(customer.district.districtName)!''}<br>
	        ${(customer.addressDetail)!''}<br>
     	 <span class="text-muted">若收货不便，可以选择到自提点上门自提)</span>
      </address>
   	   <input type="hidden" class="addressId" name="addressId" value="${(customer.addressId)!''}">	 
   	   </div><!-- /address -->
     	</#if>
    <#else>
    <#if dps??>
    	 <div class="address address2">
      <address>
        [自提点]${(dps.name)!""}<br>
        ${(dps.temp1)!""}省&nbsp;${(dps.temp2)!""}市&nbsp;${(dps.temp3)!""}<br>
        ${(dps.address)!""}<br>
        <span class="text-muted">(已选择到自提点上门自提)</span>
        <input type="hidden" class="addressId" name="addressId" value="${(dps.deliveryPointId)!''}">	 
      </address>
      </div><!-- /address -->
      </#if>
    	
    </#if>

    
   
    
    <div class="ship">
      <a href="#" class="ship_type">
        <span>选择优惠劵：</span>
        <p>不使用优惠劵</p>
      </a>
      <ul class="ship_select">
      
        <li>
          <div class="radio">
            <label><input type="radio" name="couponNo"  attr-name="不使用优惠劵" onclick="couponChange(this,0);"  value="" checked>不使用优惠劵</label>
          </div>
        </li>
        <#if map.couponlist??&&(map.couponlist?size>0)>
        <li> 
         <#list map.couponlist as coupon> 
          <div class="radio">
	         	<#if coupon.couponRulesType=='1'>
		             <label><input type="radio" name="couponNo" attr-name="${coupon.couponName}"  value="${coupon.codeNo}" onclick="couponChange(this,${coupon.couponStraightDown.downPrice});" />${coupon.couponName} --立减${coupon.couponStraightDown.downPrice}</label>
		        </#if>
		        <#if coupon.couponRulesType=='2'>  
		       		 <#if (sumprice?number>=coupon.couponFullReduction.fullPrice?number) >
		                   <label><input type="radio" name="couponNo" attr-name="${coupon.couponName}" value="${coupon.codeNo}" onclick="couponChange(this,${coupon.couponFullReduction.reductionPrice});" />${coupon.couponName} --满${coupon.couponFullReduction.fullPrice}减${coupon.couponFullReduction.reductionPrice}</label>
		        </#if>
		        </#if>
	       </div>
         </#list>
        </li>
        </#if>	
      </ul>
    </div><!-- /ship --> 
    
       <div class="message">
      <div class="form-group">
        <span>留言：</span>
        <input type="text" class="form-control" name="customerRemark" id="message_left" placeholder="请简短的填写您的特殊要求">
      </div>
    </div><!-- /message -->
    </form>
     <div class="settle">
     <p>￥${(sumprice?number)?string("0.00")} + ￥<span class="expressPrice">10.00</span>运费-￥<span class="prePriceTip">${(preprice?number)?string("0.00")}</span>优惠金额</p>
      <#assign sumprice="${sumprice?number-(preprice?number)}">
       <input type="hidden" value="${preprice}" id="prePrice">
      <input type="hidden" value="${sumprice}" id="expPrice">
       <input type="hidden" value="0" id="conponPrice">
      <input type="hidden" value="0" id="lastPrice">
      <p><strong>实付：￥<span class="pricesum">${(sumprice?number)?string("0.00")}</span></strong></strong></p>
    </div><!-- /settle -->
    <div class="pay_area">
      <button type="button" href="javascript:;" class="btn btn-warning btn-lg btn-block">提交订单</button>
    </div><!-- /pay_area -->
    
      <input type="hidden" value="0" id="lastPrice">
    
    <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->
    
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
    <a href="javascript:;" class="none tan_k"  data-toggle="modal" data-target="#goods_tips"></a>
 	 <#include "../common/smart_menu.ftl"/>
	
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/jquery.keleyi.js"></script>
    <script src="${basePath}/js/customer/wxforward.js"></script>
    <script src="${basePath}/js/publicModel.js"></script>
    <script>
	  $(function(){
          $(".ch_express").click(function(){
              changeExpress(this);

          })

          changeExpress($(".ch_express:checked"));

          $('.ship_type').click(function(){
		  if($(this).next().is(':visible')){
		    $(this).parent().removeClass('ship_hover');
		  }
		  else{
			$('.ship').removeClass('ship_hover');
			$(this).parent().addClass('ship_hover');
		  }
		});
		$('.ship_select label').click(function(){
	      var cont = $(this).find('input').attr("attr-name");
		  $(this).parent().parent().parent().prev().find('p').text(cont);
		});
		var typeId=$(".type_id").val();
	 	<#--<!--加载配送方式&ndash;&gt;-->
		  	<#--$.get("${basePath}/order/queryAllExpress.html",function(data){-->
					<#--var expressHtm="";-->
		  		<#--for(var i = 0;i<data.expresss.length;i++){-->
					<#--expressHtm+='<li><div class="radio">';-->
					<#--var express=data.expresss[i];-->
					<#--var price=(express.price).toFixed(2);-->
					<#--var ss=$("#expPrice").val();-->
						<#--/*循环匹配物流公司名称*/-->
					<#---->
								<#--expressHtm+='<label><input onchange="changeExpress(this)"  type="radio" express-price="'+price+'" name="shop"';-->
								<#--if(typeId==express.pickupFlag){-->
									<#--if(express.pickupFlag==1){-->
									<#--}-->
									<#--expressHtm+='checked="checked"';-->
									<#--$(".ch_express").attr("value",express.expressId);-->
									<#--$(".expressPrice").html(express.price);-->
									<#--var s=accAdd(express.price,ss);-->
									<#--if(s<0){-->
											<#--$(".pricesum").html(0.01);-->
										<#--}else{-->
											<#--$(".pricesum").html(s);-->
									<#--}-->
									<#--$("#lastPrice").val(s);-->
								<#--}-->
				<#--}-->
		  	<#--});-->
	  	<!--加载ps方式end-->
		$('.other_person_pay').click(function () {
		  var btn = $(this)
		  btn.button('loading')
		  $.ajax().always(function () {
			window.setTimeout("$('.other_person_pay').button('reset');location.href='other_pay.html';",500);
		  });
		  return false;
		});
		$('.pay_type').click(function(){
	      var cont = $(this).find('input').attr("pay-val");
		  $('.pay_text').text(cont);
		});
		$(".edit").click(function(){
			$(".sub_mit").attr("action","addresslist.html").submit();
		});
		$(".btn-block").click(function(){
			var dz=$(".addressId").attr("value");
			if(dz==""||dz==null){
				$(".tip_text").html("请选择收货人地址");
				$(".tan_k").click();
			}else{
				$(".sub_mit").attr("action","submitorder.html").submit();
			}
		});
	  });
	  
	  function changeExpress(obj){


          $(".pr_text").html($(obj).attr("pay-val"));
          /*获取所有的支付方式,并判断是否被选中,如果选中就更改页面上的元素值*/

          var distributionId = $(obj).val()

          var thirdIds= "";
          var cartIds = "";
          var bosscartIds="";
          var baoyoucartIds="";
          var bossnum=0;
          var thirdnum=0;

          var yfprice = 0;
          var payprice =$("#expPrice").val();;
          var addsId = $(".addressId").val();
          var boxs=new Array();
          //获取订单数量
          $('input[name="thirdId"]').each(function(){
              if("0"==$(this).val()){
                      //判断是否包邮
                      if($(this).next().val()=="0"){
                          bosscartIds+=$(this).next().next().val();
                          bosscartIds+="-";
                          boxs.push($(this).next().next().val());
                          bossnum+=1;
                      }


                  bosscartIds+=",";
              }else{
                  thirdIds+=$(this).val();
                  thirdIds+=",";
                      //判断是否包邮
                      if($(this).next().val()=="0") {
                          cartIds += $(this).next().next().val();
                          cartIds += "-";
                          boxs.push($(this).next().next().val());

                          thirdnum += 1;
                      }
                  cartIds+=",";
              }


          });
          if(bossnum=='0'){
              $("#checkedExpressBoss").remove();
              $("#express_boss").remove();
          }else{

              //计算此次购买商品总运费价格
              $.ajax({
                  type:"post",
                  url:"../getMexpressprice.htm",
                  async : false,
                  data  : {addressId:addsId,thirdIds:0,cartIds:bosscartIds,distributionId:distributionId,box:boxs},
                  success:function(data){
                      yfprice = accAdd(data,yfprice);
                  }
              });


          }

          if(thirdnum=='0'){
              $("#checkedExpressThird").remove();
              $("#express_third").remove();
          }else{
              //计算此次购买商品总运费价格
              $.ajax({
                  type:"post",
                  url:"../getMexpressprice.htm",
                  async : false,
                  data  : {addressId:addsId,thirdIds:thirdIds,cartIds:cartIds},
                  success:function(data){
                      yfprice = accAdd(data,yfprice);
                  }
              });
          }

          $(".expressPrice").html(yfprice);
          $(".pricesum").html(accAdd(payprice,yfprice));
	}
	function couponChange(obj,num){
		var price=$("#lastPrice").val();
		$("#conponPrice").val(num);
		var sum = Subtr(price,num);
		if(sum<0){
				$(".pricesum").html(0.01);
			}else{ 
				$(".pricesum").html(sum);
		}
		var prPrice=$(prePrice).val();
		
		$(".prePriceTip").html(accAdd(prPrice,num));
		$(".couponIdX").val($(obj).val());
	}
	</script> 
  </body>
</html>
