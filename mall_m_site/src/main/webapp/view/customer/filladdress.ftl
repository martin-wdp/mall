<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${(seo.meteKey)!''}">
    <meta name="description" content="${(seo.meteDes)!''}">
    <#if (sys.bsetName)??>
    	<title>收货地址-${(sys.bsetName)!''}</title>
    	<input type="hidden" id="bsetName" value="${(sys.bsetName)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    <#else>
	    <title>收货地址-${(seo.mete)!''}</title>
	    <input type="hidden" id="bsetName" value="${(seo.mete)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    </#if>
	<#assign basePath=request.contextPath>
    <!-- Bootstrap -->
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]> 
      <script src="js/html5shiv.min.js"></script>
      <script src="js/respond.min.js"></script>
    <![endif]-->
   <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script> <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
  <body>
    
    <div class="fill_address"  style="padding:1em;">
    <#if addr??>
    	<form action="${basePath}/customer/updateaddress.htm" method="post">
    			  <input type="hidden" value="${addr.addressId}" name="addressId" />
		          <div class="form-group">
		            <input type="text" class="form-control" name="addressAlias" id="as" value="${(addr.addressAlias)!''}" placeholder="例如：自己家">
		            <p class="help-block" id="as_err"></p>
		          </div>
		          <div class="form-group">
		            <input type="text" class="form-control" name="addressName" id="ae" value="${(addr.addressName)!''}"  placeholder="姓名">
		            <p class="help-block" id="as_err"></p>
		          </div>
		          <div class="form-group">
		            <input type="text" class="form-control" name="addressMoblie" id="mo" value="${addr.addressMoblie}"  placeholder="手机或固话">
		            <p class="help-block" id="as_err"></p>
		          </div>
		          <div class="form-group">
		            <div class="row">
			            <div class="col-xs-6" style="padding-left:0;">
			              <select name="infoProvince" class="form-control" id="infoProvince">
			                <option>请选择省</option>
			              </select>
			            </div>
			            <div class="col-xs-6" style="padding-right:0;">
			              <select name="infoCity"  class="form-control" id="infoCity">
			                <option>请选择市</option>
			              </select>
			            </div>
		            </div>
		            <p class="help-block" id="as_err"></p>
		          </div>
		          <div class="form-group">
		            <div class="row">
			            <div class="col-xs-6" style="padding-left:0;">
			              <select name="infoCounty"  class="form-control" id="infoCounty">
			                <option>请选择区县</option>
			              </select>
			            </div>
			            <div class="col-xs-6" style="padding-right:0;">
			              <select name="infoStreet"  class="form-control" id="infoStreet">
			                <option>请选择街道</option>
			              </select>
			            </div>
		            </div>
		            <p class="help-block" id="as_err"></p>
		          </div>
		          <div class="form-group">
		            <input type="text" class="form-control" name="addressDetail" id="ad" value="${(addr.addressDetail)!''}"  placeholder="街道门牌信息">
		            <p class="help-block" id="as_err"></p>
		          </div>
		          <div class="form-group">
		            <input type="text" class="form-control" name="addressZip" id="az" value="${(addr.addressZip)!''}"  placeholder="邮政编码">
		            <p class="help-block" id="as_err"></p>
		          </div>
		          <a href="javascript:void(0);" class="btn btn-warning btn-lg btn-block sub_up">保存修改</a>
		          <#--<input type="text" class="form-control" id="" value="${(addr.province.provinceName)!''}${(addr.city.cityName)!''}${(addr.district.districtName)!''}${(addr.street.streetName)!''}"  placeholder="地区信息">-->
		          <input type=hidden id=Province value=
					<#if addr.infoProvince??>
						${addr.infoProvince}
					</#if> />
					<input type=hidden id=City value=
					<#if addr.infoCity??>
						${addr.infoCity}
					</#if> />
					<input type=hidden id=County value=
					<#if addr.infoCounty??>
						${addr.infoCounty}
					</#if> />
					<input type=hidden id=Street value=
					<#if addr.infoStreet??>
						${addr.infoStreet}
					</#if> />
	   </form>
   	<#else>
	   	<form action="${basePath}/customer/addaddress.htm" method="post">
		          <div class="form-group">
		            <input type="text" class="form-control" name="addressAlias" id="as"  placeholder="例如：自己家">
		            <p class="help-block" id="as_err"></p>
		          </div>
		          <div class="form-group">
		            <input type="text" class="form-control" name="addressName" id="ae" placeholder="姓名">
		            <p class="help-block" id="ae_err"></p>
		          </div>
		          <div class="form-group">
		            <input type="text" class="form-control" name="addressMoblie" id="mo"  placeholder="手机或固话">
		            <p class="help-block" id="mo_err"></p>
		          </div>
		          <div class="form-group">
		            <div class="row">
			            <div class="col-xs-6" style="padding-left:0;">
			              <select name="infoProvince" class="form-control" id="infoProvince">
			                <option>请选择省</option>
			              </select>
			            </div>
			            <div class="col-xs-6" style="padding-right:0;">
			              <select name="infoCity"  class="form-control" id="infoCity">
			                <option>请选择市</option>
			              </select>
			            </div>
		            </div>
		            <p class="help-block" id="as_err"></p>
		          </div>
		          <div class="form-group">
		            <div class="row">
			            <div class="col-xs-6" style="padding-left:0;">
			              <select name="infoCounty"  class="form-control" id="infoCounty">
			                <option>请选择区县</option>
			              </select>
			            </div>
			            <div class="col-xs-6" style="padding-right:0;">
			              <select name="infoStreet"  class="form-control" id="infoStreet">
			                <option>请选择街道</option>
			              </select>
			            </div>
		            </div>
		            <p class="help-block" id="as_err"></p>
		          </div>
		          <div class="form-group">
		            <input type="text" class="form-control" name="addressDetail" id="ad"  placeholder="街道门牌信息">
		            <p class="help-block" id="ad_err"></p>
		          </div>
		          <div class="form-group">
		            <input type="text" class="form-control" name="addressZip" id="az"  placeholder="邮政编码">
		            <p class="help-block" id="az_err"></p>
		          </div>
		          <a href="javascript:void(0);" class="btn btn-warning btn-lg btn-block sub_add">添加</a>
		</form>
    </#if>
      
    </div><!-- /fill_address -->

    <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->
    <#include '/common/smart_menu.ftl' />
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/jquery.keleyi.js"></script>
    <script src="${basePath}/js/customer/asvalidate.js"></script>
    <script src="${basePath}/js/customer/allvalid.js"></script>
    <script src="${basePath}/js/customer/address.js"></script>
    <script src="${basePath}/js/customer/wxforward.js"></script>
    <script src="${basePath}/js/publicModel.js"></script>
    <script>
	  loadProvice();
	  selectLocationOption($("#Province").val(),$("#City").val(),$("#County").val(),$("#Street").val(),'infoProvince','infoCity','infoCounty','infoStreet');
	</script>
  </body>
</html>
