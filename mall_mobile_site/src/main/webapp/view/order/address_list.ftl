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

        </head>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]> 
      <script src="${basePath}/js/html5shiv.min.js"></script>
      <script src="${basePath}/js/respond.min.js"></script>
    <![endif]-->
 <script type="text/javascript" src="${basePath}/js/shoppingcart/jsOperation.js"></script>

</script>

  <body>
	    <div class="address_choose">
	  	 <form method="post" action="" class="subForm">
	  	 	<input type="hidden"  name="typeId" value="0">
	  	 	<input type="hidden"  name="ch_pay" value="${ch_pay!""}">
		   <input type="hidden" name="addressId"  value="${(addressId)!""}" class="addressId" >
	  		<#list box as b>
	    		<input type="hidden" value="${b}" name="box"/>
	    	</#list>
	      <div class="address_list" >
	      	<#list addresses as add>
	        <div class="addr_item
		        <#if addressId?? && addressId==add.addressId>
		       		  addr_item_selected
		         </#if>
	         " >
	         <a  href="javascript:;" onclick="">
	          <input type="radio" type-id="0" value="${(add.addressId)!''}"
	            <#if addressId?? && addressId==add.addressId>
		       		 checked
		       	 </#if>
	          >
	          <p><strong>${(add.addressAlias)!''}(${(add.addressName)!''})</strong></p>
	          <p>${(add.province.provinceName)!''}${(add.city.cityName)!''}${(add.district.districtName)!''}${(add.street.streetName)!''}<br></p>
	          </a>
	          <a class="text-primary"  onclick="" href="javascript:subUpdate(${add.addressId});"><span  class="glyphicon glyphicon-edit" ></span></a>
	        </div>
	        </#list>
	     </div>
	     <div class="new_address">
		        <a href="javascript:;" class="wxaddr"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;使用微信地址</a>
    	  </div>
	      <div class="new_address">
	        <a href="javascript:;"  onclick="subUpdate()" ><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;新增地址</a>
	      </div>
		 
    </form>
    <form class="subF" method="post">
    	<input type="hidden"  name="typeId" value="1">
    	<input type="hidden" name="addressId"  class="addressId2">
    		<#list box as b>
	    		<input type="hidden" value="${b}" name="box"/>
	    	</#list>
	    	 <div class="address_list">
			    <#if dps??>
			    <a  href="javascript:;" onclick="">
			        <div class="addr_item  
			         <#if addressId??>
			         <#else>
			       		  addr_item_selected
			         </#if>
			         ">
			          <input type="radio" value="${dps.deliveryPointId}" type-id="1" name="address" 
			          	<#if addressId??>
				         <#else>
				       		  checked
				         </#if>
			          >
			          <p><strong>[自提点]${(dps.name)!""}</strong></p>
			          <p>${(dps.temp1)!""}省${(dps.temp2)!""}市${(dps.temp3)!""}${(dps.address)!""}</p>
			          <a class="text-primary" href="bdmap.html?address=${(dps.temp1)!""}省${(dps.temp2)!""}市${(dps.temp3)!""}${(dps.address)!""}"><span class="glyphicon glyphicon-map-marker"></span></a>
			        </div>
			     </a>
			  </#if>
			 </div>
  <#if thirdIds?size!=0>
			 <div class="new_address">
			        <a href="javascript:;" class="submitD"><span class="glyphicon glyphicon-circle-arrow-down"></span>&nbsp;选择自提点</a>
	    	  </div>
  </#if>
	     </div>
	 
	  </form>
	 </div>
	 
	 <form class="subWXForm" method="post"> 
	 	<input type="hidden"  id="addressName" name="addressName"/>
		    <input type="hidden"  id="addressPhone" name="addressPhone" />
		    <input type="hidden"  id="addressDetail" name="addressDetail"/>
		    <input type="hidden"  id="addressDetailInfo" name="addressDetailInfo" />
		    <input type="hidden"  id="proviceFirstStageName" name="proviceFirstStageName" />
		    <input type="hidden"  id="addressCitySecondStageName" name="addressCitySecondStageName" />
		    <input type="hidden"  id="addressCountiesThirdStageName" name="addressCountiesThirdStageName" />
	  	 	<input type="hidden"  name="typeId" value="0">
	  	 	<input type="hidden"  name="ch_pay" value="${ch_pay!""}">
		   <input type="hidden" name="addressId"  value="${(addressId)!""}" class="addressId" >
		   <#list box as b>
	    		<input type="hidden" value="${b}" name="box"/>
	    	</#list>
	 </form>
	 
	<input type="hidden" value="${(appid)!''}" id="appid" />
    <input type="hidden" value="${(accessToken)!''}" id="accessToken" />
    <input type="hidden" value="${(state)!''}" id="state" />
    <input type="hidden" value="${(code)!''}" id="code" />
    <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->
    
   <#include "../common/smart_menu.ftl"/>
   
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/jquery.keleyi.js"></script>
    <script src="${basePath}/js/wxpay/sha2.js"></script>
    <script>
	  $(function(){
		FastClick.attach(document.body);
		$(".wxaddr").click(function(){
			getWxAddr();
		});
		$('.fill_item input').focus(function(){
		  $(this).parent().next().css('borderColor','#EB6122');
		});
		$('.fill_item input').blur(function(){
		  $(this).parent().next().css('borderColor','#EEEEEE');
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
		$('.addr_item').click(function(){
			 $('.addr_item').removeClass('addr_item_selected');
		  	$(".del_id").attr("value",$(this).find('input').attr('value'));
		  	$(this).addClass('addr_item_selected');
			  $('.addr_item input').removeAttr('checked');
			  $(this).find('input').attr('checked',true);
		
		  	   if($(this).find('input').attr('type-id')==0){
		  	   		$(".addressId").attr("value",$(this).find('input').attr('value'));
			   		$(".subForm").attr("action","suborder.html").submit();
		  	   }else{
		  	   		$(".addressId2").attr("value",$(this).find('input').attr('value'));
		  	   		$(".subF").attr("action","suborder.html").submit();
		  	   }
		  
		});
		  $(".submitD").click(function(){
		  	$(".subF").attr("action","querydelivery.html").submit();
		  });
	  });
	  
	  function subUpdate(obj){
	  	$(".addressId").val(obj);
		$(".subForm").attr("action","orderfilladdress.html").submit();
	  }
	  var appid = ($("#appid").val()).toString();
	  var timeStamp = new Date().getTime().toString();
	  var nonceStr = Math.floor(Math.random() * 10000).toString();
	  var state = ($("#state").val()).toString();
	  var code = ($("#code").val()).toString();
	  var accesstoken = ($("#accessToken").val()).toString();
	  var sign = "accesstoken=" + accesstoken +
		   		 "&appid=" + appid + 
		   		 "&noncestr=" + nonceStr + 
		   		 "&timestamp=" + timeStamp + 
		   		 "&url=" + window.location.href;
	  var sign1 = hex_sha1(sign).toString();
	  function getWxAddr(){
			WeixinJSBridge.invoke('editAddress', {
				"appId" : appid,
				"scope" : "jsapi_address",
				"signType" : "SHA1",
				"addrSign" : sign1,
				"timeStamp" : timeStamp,
				"nonceStr" : nonceStr,
			}, function(res) {
				// 若res 中所带的返回值不为空，则表示用户选择该返回值作为收货地 址。否则若返回空，则表示用户取消了这一次编辑收货地址。
				if(res.err_msg == 'edit_address:ok'){
					$("#addressName").val(res.userName);
					$("#addressPhone").val(res.telNumber);
					$("#addressDetail").val(res.proviceFirstStageName+res.addressCitySecondStageName+res.addressCountiesThirdStageName);
					$("#addressDetailInfo").val(res.addressDetailInfo);
					$("#proviceFirstStageName").val(res.proviceFirstStageName);
					$("#addressCitySecondStageName").val(res.addressCitySecondStageName);
					$("#addressCountiesThirdStageName").val(res.addressCountiesThirdStageName);
					$(".subWXForm").attr("action","suborder.html").submit();
				}
			});
	  }
	</script>
  </body>
</html>
