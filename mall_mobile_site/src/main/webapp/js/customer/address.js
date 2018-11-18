/**
 * 
 */

$(function(){
	/**
	 * 省份改变时 触发此事件 开始加载下一级 --城市 select[id^='province']
	 */
	$("#infoProvince").change(function() {
		loadCity($(this).val());
	});
	/**
	 * 城市改变时 触发此事件 开始加载下一级 --区县
	 */
	$("#infoCity").change(function() {
		loadDistrict($(this).val());
	});

	/**
	 * 区县改变时 触发此事件 开始加载下一级 --街道
	 */
	$("#infoCounty").change(function() {
		loadStreet($(this).val());
	});
});


/**
 * 选中 省市区街道
 * 
 * @param pid
 *            省编号
 * @param cid
 *            城市编号
 * @param did
 *            区县编号
 * @param sid
 *            街道编号
 * @param po
 *            infoProvince
 * @param co
 *            infoCity
 * @param dio
 *            infoCounty
 * @param so
 *            infoStreet
 */
function selectLocationOption(pid, cid, did, sid, po, co, dio, so) {
	if (pid == "") {
		return;
	}
	$("#" + po + " option[value='" + pid + "']").prop("selected", "selected"); // 选中省份
	loadCity(pid);
	$("#" + co + " option[value='" + cid + "']").prop("selected", "selected"); // 选中城市
	loadDistrict(cid);
	$("#" + dio + " option[value='" + did + "']").prop("selected", "selected");// 选中区县
	loadStreet(did);
	$("#" + so + " option[value='" + sid + "']").prop("selected", "selected"); // 选中街道
}

/**
 * 加载省份
 */
function loadProvice() {
    //获取省份
    var province_map = $('.province_map').val();
    var basePath=$("#basePath").val();
	$.ajax({
		type : 'post',
		url : basePath+'/getAllProvince.htm',
		async : false,
		success : function(data) {
			var options = "<option  value='0' >-请选择-</option>";
			for (var i = 0; i < data.length; i++) {
				var province = data[i];
                if(""!=province_map&&province_map==province.provinceId){
                    options += "<option selected='selected' value='" + province.provinceId + "'>"+ province.provinceName + "</option>";
                }else{
                    options += "<option value='" + province.provinceId + "'>"+ province.provinceName + "</option>";
                }

			}
			$("select[id^='infoProvince']").html(options);
		}
	});
	loadCity($("#infoProvince").val());
};

/**
 * 加载城市
 *
 * @param pid
 *            省份编号
 */
function loadCity(pid) {
    //获取城市
    var city_map = $('.city_map').val();
    var basePath=$("#basePath").val();
	var paramStr = "provinceId=" + pid;
	$.ajax({
				type : 'post',
				url :basePath+ '/getAllCityByPid.htm',
				data : paramStr,
				async : false,
				success : function(data) {
					if (data.length != 0) {
						var options = "";
						for (var i = 0; i < data.length; i++) {
							var city = data[i];
							/*options += "<option value='" + city.cityId + "'>"+ city.cityName + "</option>";*/
                            if(""!=city_map&&city_map==city.cityId){

                                options += "<option selected='selected' value='" + city.cityId + "'>"+ city.cityName + "</option>";
                            }else{
                                options += "<option value='" + city.cityId + "'>"+ city.cityName + "</option>";
                            }
						}
						$("#infoCity").html(options);
					} else {
						$("#infoCity").html(
								"<option value='0' >" + "请选择" + "</option>");
						$("#infoCounty").html(
								"<option value='0' >" + "请选择" + "</option>");
						$("#infoStreet").html(
								"<option value='0' >" + "请选择" + "</option>");
					}
				}
			});
	loadDistrict($("#infoCity").val());
}
/**
 * 加载区县
 *
 * @param pid
 *            省份编号
 */
function loadDistrict(pid) {
    //获取区县
    var district_map = $('.district_map').val();
	var paramStr = "cityId=" + pid;
    var basePath=$("#basePath").val();
	$.ajax({
		type : 'post',
		url : basePath+'/getAllDistrictByCid.htm',
		data : paramStr,
		async : false,
		success : function(data) {
			if (data.length != 0) {
				var options = "";
				for (var i = 0; i < data.length; i++) {
					var district = data[i];
                    if(""!=district_map&&district_map==district.districtId){

                        options += "<option selected='selected' value='" + district.districtId + "'>"+ district.districtName + "</option>";
                    }else{
                        options += "<option value='" + district.districtId + "'>"+ district.districtName + "</option>";
                    }
				}
				$('#infoCounty').html(options);
			} else {
				$("#infoCounty").html(
						"<option value='0' >" + "请选择" + "</option>");
			}
		}
	});
	loadStreet($("#infoCounty").val());
}
/**
 * 加载街道
 *
 * @param cid
 *            城市编号
 */
function loadStreet(cid) {
	var paramStr = "dId=" + cid;
    var basePath=$("#basePath").val();
	$.ajax({
		type : 'post',
		url :basePath+ '/getAllStreetByDid.htm',
		data : paramStr,
		async : false,
		success : function(data) {
			if (data.length != 0) {
				var options = "";
				for (var i = 0; i < data.length; i++) {
					var street = data[i];
					options += "<option value='" + street.streetId + "'>"
							+ street.streetName + "</option>";
				}
				$('#infoStreet').html(options);
			} else {
				$("#infoStreet").html(
						"<option value='' >" + "请选择" + "</option>");
			}
		}
	});
}

/**
 * 根据地址搜索店铺信息
 */
function searchStoreByAddress(){
    var infoProvince = $('#infoProvince').val(); //省份
    var infoCity = $('#infoCity').val();  //城市
    var infoCounty = $('#infoCounty').val(); //街道
    // 请选择的value 的值是0
    $('.search_tip').html('');
    var bussAddrId = infoProvince+','+infoCity+','+infoCounty+',';
    $('.bussAddrId').val(bussAddrId);
    $('.searchStoreByAddress').submit();
}

/**
 * 选中省份
 */
function infoProvince_check(){
    $('.search_tip').html('');
    var infoProvince = $('#infoProvince').val(); //省份
    var infoCity = $('#infoCity').val();  //城市
    var infoCounty = $('#infoCounty').val(); //街道
    if(infoProvince != null && infoCity !=null && infoCounty!=null){
        var bussAddrId = infoProvince+','+infoCity+','+infoCounty+',';
        $('.bussAddrId').val(bussAddrId);
    }
}