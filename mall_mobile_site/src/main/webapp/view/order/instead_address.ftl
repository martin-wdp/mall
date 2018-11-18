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

  </head>
  <body>
  <form action="" class="subForm" method="post">
	<input type="hidden" value="" class="del_id" name="deliveryPointId"/>
    <input type=hidden name="provinceId" id=Province value=${Province!""} />
	<input type=hidden name="cityId" id=City value=${cityId!""} />
	<input type=hidden name="districtId" id=County value=${districtId!""} />
	 <#list box as b>
	    		<input type="hidden" value="${b}" name="box"/>
	 </#list>
    <div class="address_filter container-fluid">
      <div class="row">
        <form role="form">
          <div class="col-xs-4">
            <select name="infoProvince" class="form-control" id="infoProvince">
			   <option>请选择省</option>
			</select>
          </div>
          <div class="col-xs-4">
           <select name="infoCity"  class="form-control" id="infoCity">
			    <option>请选择市</option>
			</select>
          </div>
          <div class="col-xs-4">
            <select name="infoCounty"  class="form-control" id="infoCounty">
			      <option>请选择区县</option>
			</select>
          </div>
        </form>
      </div>
    </div>
    <#if delivery[0]??>
    <div class="address_choose">
      <div class="address_list">
	       <#list delivery as del>
	        <div class="addr_item">
	         <a href="javascript:;" class="sub_delivery"> <input type="radio"   value="${del.deliveryPointId!""}">
	          <p><strong>[自提点]${del.name!""}</strong></p>
	          <p>${(del.temp1)!""}省${(del.temp2)!""}市${(del.temp3)!""}${(del.address)!""}</p></a>
	          <a class="text-primary" href="bdmap.html?address=${(del.temp1)!""}省${(del.temp2)!""}市${(del.temp3)!""}${(del.address)!""}"><span class="glyphicon glyphicon-map-marker"></span></a>
	        </div>
	      </#list>
      </div>
    </div>
    </#if>
    </from>
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
    <script src="${basePath}/js/pay/address.js"></script>
    <script>
	  $(function(){
		FastClick.attach(document.body);
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
			$(".subForm").attr("action","addresslist.html").submit();

		});
		$(".sub_delivery").click(function(){
		});
	  });
	   loadProvice();
	  selectLocationOption($("#Province").val(),$("#City").val(),$("#County").val(),'infoProvince','infoCity','infoCounty');
	</script>
  </body>
</html>
