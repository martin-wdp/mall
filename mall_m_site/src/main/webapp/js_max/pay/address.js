/**
 * 
 */

$(function(){
	/**
	 * 省份改变时 触发此事件 开始加载下一级 --城市 select[id^='province']
	 */
	$("#infoProvince").change(function() {
		$(Province).attr("value",$(this).val());
		loadCity($(this).val());
	});
	/**
	 * 城市改变时 触发此事件 开始加载下一级 --区县
	 */
	$("#infoCity").change(function() {
		$(City).attr("value",$(this).val());
		loadDistrict($(this).val());
	});

	/**
	 * 区县改变时 触发此事件 开始加载下一级 --街道
	 */
	$("#infoCounty").change(function() {
		$(County).attr("value",$(this).val());
		$(".subForm").attr("action","querydelivery.html").submit();
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
function selectLocationOption(pid, cid, did, po, co, dio) {
	if (pid == "") {
		return;
	}
	$("#" + po + " option[value='" + pid + "']").prop("selected", "selected"); // 选中省份
	loadCity(pid);
	$("#" + co + " option[value='" + cid + "']").prop("selected", "selected"); // 选中城市
	loadDistrict(cid);
	$("#" + dio + " option[value='" + did + "']").prop("selected", "selected");// 选中区县
}

/**
 * 加载省份
 */
function loadProvice() {
	$.ajax({
		type : 'post',
		url : '../getAllProvince.htm',
		async : false,
		success : function(data) {
			var options = "<option value='' >"+"请选择"+"</option>";
			for (var i = 0; i < data.length; i++) {
				var province = data[i];
				options += "<option value='" + province.provinceId + "'>"
						+ province.provinceName + "</option>";
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
	var paramStr = "provinceId=" + pid;
	$
			.ajax({
				type : 'post',
				url : '../getAllCityByPid.htm',
				data : paramStr,
				async : false,
				success : function(data) {
					var options = "<option value='' >"+"请选择"+"</option>";
					if (data.length != 0) {
						for (var i = 0; i < data.length; i++) {
							var city = data[i];
							options += "<option value='" + city.cityId + "'>"
									+ city.cityName + "</option>";
						}
						$("#infoCity").html(options);
					} else {
						$("#infoCity").html(
								"<option value='' >" + "请选择" + "</option>");
						$("#infoCounty").html(
								"<option value='' >" + "请选择" + "</option>");
						$("#infoStreet").html(
								"<option value='' >" + "请选择" + "</option>");
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
	var paramStr = "cityId=" + pid;
	$.ajax({
		type : 'post',
		url : '../getAllDistrictByCid.htm',
		data : paramStr,
		async : false,
		success : function(data) {
			if (data.length != 0) {
				var options = "<option value='' >"+"请选择"+"</option>";
				for (var i = 0; i < data.length; i++) {
					var district = data[i];
					options += "<option value='" + district.districtId + "'>"
							+ district.districtName + "</option>";
				}
				$('#infoCounty').html(options);
			} else {
				$("#infoCounty").html(
						"<option value='' >" + "请选择" + "</option>");
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
	$.ajax({
		type : 'post',
		url : '../getAllStreetByDid.htm',
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