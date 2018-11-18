/**
 * 
 */

$(function(){
	loadProvice();
	$(".tabBar li a").click(function(){
		var cur = $(this);
		$(".tabBar").find(".current").removeClass("current");
		cur.parent("li").addClass("current");
	});

	/**
	 * 省份改变时 触发此事件 开始加载下一级 --城市 select[id^='province']
	 */
	$("#infoProvince").change(function(){
		loadCity($(this).val());
	});
	/**
	 * 城市改变时 触发此事件 开始加载下一级 --区县
	 */
	$("#infoCity").change(function(){
		loadDistrict($(this).val());
	});
	
	/**
	 * 区县改变时 触发此事件 开始加载下一级 --街道
	 */
	$("#infoCounty").change(function(){
		loadStreet($(this).val());
	});

	/**
	 * 返回登录
	 */
	$("#goLogin").click(function(){
		window.location.href="gologin.htm";
	});
	/**
	 * 提示已为默认地址
	 */
	$(".default").click(function(){
		alert("当前地址为默认地址");
	});
	
	$(".itg_select").change(function(){
		$("#date").val($(this).val());
		$("#pageBeanShowPage").val(1);
		$("#searchForm").submit();
	});
});

/**
 * 选中 省市区街道
 * @param pid 省编号
 * @param cid 城市编号
 * @param did 区县编号
 * @param sid 街道编号
 * @param po  infoProvince
 * @param co  infoCity
 * @param dio infoCounty
 * @param so  infoStreet
 */
function selectLocationOption (pid,cid,did,sid,po,co,dio,so){
	if(pid==""){
		return;
	}
	$("#"+po+" option[value='"+pid+"']").prop("selected","selected"); //选中省份
	loadCity(pid);
	$("#"+co+" option[value='"+cid+"']").prop("selected","selected"); //选中城市
	loadDistrict(cid); 
	$("#"+dio+" option[value='"+did+"']").prop("selected","selected");//选中区县
	loadStreet(did);
	$("#"+so+" option[value='"+sid+"']").prop("selected","selected"); //选中街道
}


/**
 * 加载省份
 */
function loadProvice(){
	 $.ajax({
         type: 'post',
         url:$("#basePath").val()+'/customer/address/province',
         async:false,
         success: function(data) {
        	var options = "";
     		for( var i=0; i<data.length; i++){
     			var province=data[i];
     			options +=  "<option value='"+province.provinceId+"'>"+province.provinceName+"</option>";
     		}
     		$("select[id^='infoProvince']").html(options);
         }
	 });
	 loadCity($("#infoProvince").val());
};

/**
 * 加载城市
 * @param pid 省份编号
 */
function loadCity(pid){
	var paramStr="provinceId="+pid;
	 $.ajax({
         type: 'post',
         url:$("#basePath").val()+'/customer/address/city',
         data:paramStr,
         async:false,
         success: function(data) {
        	 if(data.length!=0){
     			var options = "";
     			for( var i=0; i<data.length; i++){
     				var city=data[i];
     				options +=  "<option value='"+city.cityId+"'>"+city.cityName+"</option>";
     			}
     			$("#infoCity").html(options);
     		}else{
     			$("#infoCity").html("<option value='' >"+"请选择"+"</option>");
     			$("#infoCounty").html("<option value='' >"+"请选择"+"</option>");
     			$("#infoStreet").html("<option value='' >"+"请选择"+"</option>");
     		}
         }
	 });
	loadDistrict($("#infoCity").val());
}
/**
 * 加载区县
 * @param pid 省份编号
 */
function loadDistrict(pid){
	 var paramStr="cityId="+pid;
	 $.ajax({
       type: 'post',
       url:$("#basePath").val()+'/customer/address/district',
       data:paramStr,
       async:false,
       success: function(data) {
    	   if(data.length!=0){
	   			var options = "";
	   			for( var i=0; i<data.length; i++){
	   				var district=data[i];
	   				options +=  "<option value='"+district.districtId+"'>"+district.districtName+"</option>";
	   			}
	   			$('#infoCounty').html(options);
   			}else{
   				$("#infoCounty").html("<option value='' >"+"请选择"+"</option>");
   			}
       }
	 });
	loadStreet($("#infoCounty").val());
}
/**
 * 加载街道
 * @param cid 城市编号
 */
function loadStreet(cid){
	var paramStr="dId="+cid;
	 $.ajax({
      type: 'post',
      url:$("#basePath").val()+'/customer/address/street',
      data:paramStr,
      async:false,
      success: function(data) {
    	  if(data.length!=0){
  			var options = "";
  			for( var i=0; i<data.length; i++){
  				var street=data[i];
  				options +=  "<option value='"+street.streetId+"'>"+street.streetName+"</option>";
  			}
  			$('#infoStreet').html(options);
	  	  }else{
	  			$("#infoStreet").html("<option value='' >"+"请选择"+"</option>");
	  	  }
      }
	 });
}
/**
 * 获取要修改的地址信息
 * @param objId
 */
function updateAddress(objId){
	$("#fill_address").show();
	var paramStr="addressId="+objId;
	$.ajax({
		type: 'post',
		url:$("#basePath").val()+'/customer/address/ajaxtoform',
		data:paramStr,
		async:false,
		success: function(data) {
			selectLocationOption(data.infoProvince, data.infoCity, data.infoCounty, data.infoStreet,'infoProvince','infoCity','infoCounty','infoStreet');
			$("#addressName").val(data.addressName);
			$("#addressDetail").val(data.addressDetail);
			$("#addressMoblie").val(data.addressMoblie);
			$("#addressPhone").val(data.addressPhone);
			$("#addressZip").val(data.addressZip);
			$("#addressAlias").val(data.addressAlias);
			$("#hiden_addressId").val(data.addressId);
			$("#addressName_tips").text("").removeClass("tipserror");
			$("#addressDetail_tips").text("").removeClass("tipserror");
			$("#addressMoblie_tips").text("").removeClass("tipserror");
			$("#addressPhone_tips").text("").removeClass("tipserror");
			$("#addressAlias_tips").text("例如“自己家”“公司地址”等").removeClass("tipserror").addClass("light");
            $("#diaTitle").html("修改收货地址");
            dia(2);
		}
	});
}

//修改为默认地址
function modifyDefault(addressId,customerId){
	 $(".info_tip_cancel").attr("href",$("#basePath").val()+"/customer/address/updateDefault/"+customerId+"-"+addressId);
	 dia(1);
//	 var result =confirm("设置此地址为默认地址的同时\n会将之前的默认地址清除!\n确定设置为默认吗?");
//	 if(result ==true){
//		 window.location.href=$("#basePath").val()+"/customer/address/updateDefault/"+customerId+"-"+addressId;
//	 }else{
//		 
//	 } 
}
