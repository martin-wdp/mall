/**
 * 宁派电子商务平台前台会员部分页面js文件 
 * 作者 ：NINGPAI-zhangqiang
 * 时间：2014年4月11日16:23:39
 * 版本：0.0.1版
 */
/**
 */
$(function() {
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
	/**
	 * 月份改变时改天当前余月天数
	 */
	$("#month").change(function() {
		loadMonthDay($("#year").val(), $(this).val());
	});
	/**
	 * 年份改变时改变当前年 当前月份的天数
	 */
	$("#year").change(function() {
		loadMonthDay($("#year").val(), $("#month").val());
	});

	$(".ss").change(
			function() {
				var t_param = "";
				if (!$(".t_param").val() == "商品名称或订单编号") {
					t_param = "-" + $(".t_param").val();
				}
				window.location.href = "../customer/myorder-" + $(".s1").val()
						+ "-" + $(".tagMenu .current").attr("data-val") + "-1" + t_param + ".html";
	});
	$(".tagMenu .ods_tabs li").click(function(){
		var t_param = "";
		if (!$(".t_param").val() == "商品名称或订单编号") {
			t_param = "-" + $(".t_param").val();
		}
		window.location.href = "../customer/myorder-" + $(".s1").val() + "-" + $(this).attr("data-val") + "-1" + t_param + ".html";
	});
	
	$(".tagMenu .gift_tabs li").click(function(){
		window.location.href = "../customer/giftorder-" + $(".s1").val() + "-" + $(this).attr("data-val") + "-1" + ".html";
	});
	
	$(".gifts").change(function() {
		window.location.href = "../customer/giftorder-" + $(".s1").val()
						+ "-" + $(".tagMenu .current").attr("data-val") + "-1" + ".html";
	});
	
	$(".tagMenu .rush_tabs li").click(function(){
		window.location.href = "../customer/marketorders-" + $(".s1").val() + "-" + $(this).attr("data-val") + "-1" + ".html";
	});
	
	$(".rush").change(function() {
		window.location.href = "../customer/marketorders-" + $(".s1").val()
						+ "-" + $(".tagMenu .current").attr("data-val") + "-1" + ".html";
	});

	$(".tagMenu .groupon_tabs li").click(function(){
		window.location.href = "../customer/marketordergrounp-" + $(".s1").val() + "-" + $(this).attr("data-val") + "-1" + ".html";
	});
	
	$(".groupon").change(function() {
		window.location.href = "../customer/marketordergrounp-" + $(".s1").val()
						+ "-" + $(".tagMenu .current").attr("data-val") + "-1" + ".html";
	});
	
	/* 点击重新购买 */
	$(".rebuy").click(
			function() {
				var groupPro = $(this).next().val();
				var ids = groupPro.split(',');
				var count= 0;
				for (var i = 0; i < ids.length; i++) {
					if (ids[i].length != 0) {
						if (!isNaN(ids[i])) {
							var url ="../goods/addProductToShopCar.html?productId="+ ids[i]+ "&goodsNum=1";
							$.ajax({
								type : 'post',
								url : url,
								async : false,
								success : function(data) {
									if(data){
										location.href = "../myshoppingcart.html";
									}
									count++;
								}
							});
							
//							$.get(
//									"../goods/addProductToShopCar.html?productId="
//											+ ids[i] + "&goodsNum=1", function(
//											data) {
//										if (data) {
//											bool = true;
//										} else {
//											bool = false;
//										}
//									});
						}
					}
				}
				
				
				
				if(count == ids.length-1){
				location.href = "../myshoppingcart.html";
				}
				
				
				
			});

//	win();
//	$(window).resize(function() {
//		win();
//	});

});
function buy(obj) {
	$.get("../goods/addProductToShopCar.html?productId=" + $(obj).next().val()
			+ "&goodsNum=1", function(data) {
		if (data) {
			location.href = "../myshoppingcart.html";
		} else {
			dia(3);
		}
	});
}
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
 * 选中生日
 * 
 * @param birthday
 */
function selectBirthday(birthday) {
	if (birthday == null) {
		return;
	}
	var birthArray = birthday.split('-');
	$("#year option[value='" + birthArray[0] + "']").prop("selected",
			"selected"); // 选中年份
	$("#month option[value='" + birthArray[1] + "']").prop("selected",
			"selected"); // 选中月份
	$("#day option[value='" + birthArray[2] + "']")
			.prop("selected", "selected"); // 选中当月天
}

function selectSalary(obj) {
	$("#infoSalary option[value='" + obj + "']").prop("selected", "selected");
}

/**
 * 加载省份
 */
function loadProvice() {
	// var options = "<option value='' >"+"请选择"+"</option>";
	$.ajax({
		type : 'post',
		url : '../getAllProvince.htm',
		async : false,
		success : function(data) {
			var options = "";
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
					if (data.length != 0) {
						var options = "";
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
				var options = "";
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
//function win() {
//	var _wd = $(window).width();
//	var _hd = $(window).height();
//	$(".s_dia").css("top", (_hd - $(".dialog").height()) / 2).css("left",
//			(_wd - $(".s_dia").width()) / 2);
//};
//
//function dia(n) {
//	$(".mask").fadeIn();
//	$(".dia" + n).fadeIn();
//};
//
//function cls() {
//	$(".dialog").fadeOut();
//	$(".mask").fadeOut();
//};


//申请退款
function returnmoney(url){
	cUrl = url;
	$(".err_tuikuan").html("<img src='../images/gantanhao_1.gif'/>注：退款原因不能超过100个字！");
	dia(16);
}


//退款原因失去焦点
function returnmoney1(){
	var tuikuanyuanyin = $.trim($('#tuikuanyuanyin').val());//退款原因
	if(tuikuanyuanyin ==''){
		$(".err_tuikuan").addClass("red");
		$(".err_tuikuan").html("<img src='../images/red_cha.png'/>退款原因不能为空！");
		bool = false;
	}else{
		$(".err_yuanyin").html("<img src='../images/duihao_1.png'/>");
	}
	
}

//验证退货信息
function checkMoney(tuikuanyuanyin){
	var bool = true;
	
	if(tuikuanyuanyin ==''){
		$(".err_tuikuan").addClass("red");
		$(".err_tuikuan").html("<img src='../images/red_cha.png'/>退款原因不能为空！");
		bool = false;
	}else{
		$(".err_tuikuan").html("<img src='../images/duihao_1.png'/>");
	}
	return bool;
}




//退款确定
function returnmoney_queding(flag){//订单页退款还是会员中心首页退款
	var tuikuanyuanyin = $.trim($('#tuikuanyuanyin').val());//退款原因
	var paramStr = '?tuikuanyuanyin='+tuikuanyuanyin;
	//信息验证
	if(checkMoney(tuikuanyuanyin)){
		var url = window.location.href;  //当前Http路径
		var index = url.substring(url.length-6,url.length-5);//截取当前的页数
		var yueindex = url.substring(url.length-10,url.length-9);//截取一月前订单
		$.ajax({
			type:'post',
			url:cUrl+encodeURI(encodeURI(paramStr))+"&CSRFToken="+$("#hi_token").val(),
			success:function(data){
				//提交请求之后  删除onclick事件  避免网络延迟 多次提交
				$('.go_pay').removeAttr("onclick");
				if(flag=='01'){
					if(data==1){
						$('#returnYuanyin').val(''); //清空 退货原因原因
						window.location.href ='../customer/index.html';
					}else{
						//随便给一个错的路径 报异常
						window.location.href ='../customer/index-1.html';
					}
				}else{
					if(yueindex==0){  //近一个月
						if(data==1){
							$('#returnYuanyin').val(''); //清空 退货原因原因
							if(!isNaN(index)){
								window.location.href ='../customer/myorder-'+yueindex+'-5-'+index+'.html';
							}else{
								//页数截取失败 就指明是第一页
								window.location.href ='../customer/myorder-'+yueindex+'-'+1+'.html';
							}
						}else{
							//随便给一个错的路径 报异常
							window.location.href = '../error/myorder-'+index+'.html';
						}
					}else{ //一月前
						if(data==1){
							$('#returnYuanyin').val(''); //清空 退货原因原因
							if(!isNaN(index)){
								if(!isNaN(yueindex)){
									window.location.href ='../customer/myorder-'+yueindex+'-5-'+index+'.html';
								}else{
									window.location.href = '../customer/myorder-'+index+'.html';
								}
								
							}else{
								//页数截取失败 就指明是第一页
								window.location.href ='../customer/myorder-'+1+'.html';
							}
						}else{
							//随便给一个错的路径 报异常
							window.location.href = '../error/myorder-'+index+'.html';
						}
					}
					
				}
				
			}
			
		});
	}

}









//原因失去焦点
function jiaodianyuanyin(){
	var returnyuanyin = $.trim($('#returnyuanyin').val());//退货原因
	if(returnyuanyin ==''){
		$(".err_yuanyin").addClass("red");
		$(".err_yuanyin").html("<img src='../images/red_cha.png'/>退货原因不能为空！");
		bool = false;
	}else{
		$(".err_yuanyin").html("<img src='../images/duihao_1.png'/>");
	}
	
}


//退单物流
function setwuliu(orderNo){
	$(".err_yuanyin").html("<img src='../images/gantanhao_1.gif'/>注：退单原因不能超过100个字！");
	$("#orderNo").val(orderNo);
	dia(15);
}


//取消填写物流信息窗口
function quxiaowuliu(){
	//清空样式
	$(".dialog").fadeOut();
	$(".mask").fadeOut();
	$(".yanzhengname").addClass("black");
	$(".yanzhengname").html("&nbsp;&nbsp;请填写正确的物流公司！");
	$(".yanzhengno").addClass("black");
	$(".yanzhengno").html("&nbsp;&nbsp;单号必须正确有效的数字！");
	$("#wlname").val('');//物流名称
	$("#wlno").val(''); //物流单号
	$("#orderNo").val(''); //订单号
}

//物流名称失去焦点
function wuliuname(){
	var value = $("#wlname").val();//物流名称
	if(value==''||value.length<0){
		$(".yanzhengname").addClass("red");
		$(".yanzhengname").html("&nbsp;<img src='../images/red_cha.png'/>物流公司名称不能为空！");
	}else{
		$(".yanzhengname").html("&nbsp;<img src='../images/duihao_1.png'/>");
	}
}


//物流单号失去焦点
function wuliudanhao(){
    var reg = /^[0-9a-zA-Z]+$/;
	var no = $("#wlno").val(); //物流单号

	if(no==''||no.length<0){
		$(".yanzhengno").addClass("red");
		$(".yanzhengno").html("&nbsp;<img src='../images/red_cha.png'/>物流公司单号不能为空！");

	}else{
        if(!reg.test(no)){
            $(".yanzhengno").addClass("red");
            $(".yanzhengno").html("&nbsp;<img src='../images/red_cha.png'/>只能为数字或字母！");
        }else{
            $(".yanzhengno").html("&nbsp;<img src='../images/duihao_1.png'/>");
        }

	}
}


//验证物流信息
function checkValue(value,no){
    var reg = /^[0-9]|[a-z]|[A-Z]+$/;
	var result = 0;
	if(value==''||value.length<0){
		$(".yanzhengname").addClass("red");
		$(".yanzhengname").html("&nbsp;<img src='../images/red_cha.png'/>物流公司名称不能为空！");
		result=1;
	}
	if(no==''||no.length<0){
		$(".yanzhengno").addClass("red");
		$(".yanzhengno").html("&nbsp;<img src='../images/red_cha.png'/>物流公司单号不能为空！");
		result=1;
	}else{
        if(!reg.test(no)){
            $(".yanzhengno").addClass("red");
            $(".yanzhengno").html("&nbsp;<img src='../images/red_cha.png'/>只能为数字或字符！");
        }else{
            $(".yanzhengno").html("&nbsp;<img src='../images/duihao_1.png'/>");
        }
    }
	return result;
}
//提交物流信息
function quedingwl(flag){//判断是我的订单跳转还是会员中心首页跳转
	var wlname = $("#wlname").val();//物流名称
	var wlno = $("#wlno").val(); //物流单号
	var orderNo = $("#orderNo").val(); //订单号
	if(checkValue(wlname,wlno)==0){//验证物流信息
		var url = window.location.href;  //当前Http路径
		var index = url.substring(url.length-6,url.length-5);//截取当前的页数
		var yueindex = url.substring(url.length-10,url.length-9);//截取一月前订单
		$("#go_pay").attr("disabled","disabled"); //进入ajax 后禁用掉 按钮 避免网络延迟 用户重复点击提交多次请求
		 $.ajax({
	         //提交数据的类型 POST GET
	         type:"POST",
	         //提交的网址
	         url:"../saveBackOrderGeneral.htm",
	         //提交的数据
	         data:{wlname:wlname,wlno:wlno,orderNo:orderNo},
	         async:false,
	         //返回数据的格式
	         datatype: "post",
	         //成功返回之后调用的函数             
	         success:function(data){
					if(flag=='01'){
						if(data==1){
							$('#returnYuanyin').val(''); //清空 退货原因原因
							window.location.href ='../customer/index.html';
						}else{
							window.location.href ='../customer/index-1.html';
						}
					}else{
						if(yueindex==0){  //近一个月
							if(data==1){
								$('#returnYuanyin').val(''); //清空 退货原因原因
								if(!isNaN(index)){
									window.location.href ='../customer/myorder-'+yueindex+'-5-'+index+'.html';
								}else{
									//页数截取失败 就指明是第一页
									window.location.href ='../customer/myorder-'+yueindex+'-'+1+'.html';
								}
							}else{
								//随便给一个错的路径 报异常
								window.location.href = '../error/myorder-'+index+'.html';
							}
						}else{ //一月前
							if(data==1){
								$('#returnYuanyin').val(''); //清空 退货原因原因
								if(!isNaN(index)){
									if(!isNaN(yueindex)){
										window.location.href ='../customer/myorder-'+yueindex+'-5-'+index+'.html';
									}else{
										window.location.href = '../customer/myorder-'+index+'.html';
									}
									
								}else{
									//页数截取失败 就指明是第一页
									window.location.href ='../customer/myorder-'+1+'.html';
								}
							}else{
								//随便给一个错的路径 报异常
								window.location.href = '../error/myorder-'+index+'.html';
							}
						}
					}
					
					
				}
		 });
	}
}


var cUrl = "";

//退货
function returngoods(url){
	$(".err_yuanyin").html("<img src='../images/gantanhao_1.gif'/>注：退单原因不能超过100个字！");
	cUrl = url;
	dia(5);
}

//退货弹窗  取消
function quxiao(){
		$(".dialog").fadeOut();
		$(".mask").fadeOut();
		$(".err_yuanyin").addClass("black");
		$(".err_yuanyin").html("<img src='../images/gantanhao_1.gif'/>收件原因不能超过100个字！");
		
}

//退货弹窗  确定
function returngoods_queding(){
	var returnyuanyin = $.trim($('#returnyuanyin').val());//退货原因
	var returnperson = $.trim($('#returnperson').val());
	var returnphone = $.trim($('#returnphone').val());
	var returnaddres = $.trim($('#returnaddres').val());
	var paramStr = '?returnyuanyin='+returnyuanyin;
	//信息验证
	if(checkText(returnyuanyin)){
		var url = window.location.href;  //当前Http路径
		var index = url.substring(url.length-6,url.length-5);//截取当前的页数
		var yueindex = url.substring(url.length-10,url.length-9);//截取一月前订单
		/*$("#go_pay").attr("disabled","disabled"); //进入ajax 后禁用掉 按钮 避免网络延迟 用户重复点击提交多次请求*/
		$.ajax({
			type:'post',
			url:cUrl+encodeURI(encodeURI(paramStr))+"&CSRFToken="+$("#hi_token").val(),
			success:function(data){
				//提交请求之后  删除onclick事件  避免网络延迟 多次提交
				$('.go_pay').removeAttr("onclick");
				if(yueindex==0){  //近一个月
					if(data==1){
						$('#returnYuanyin').val(''); //清空 退货原因原因
						if(!isNaN(index)){
							window.location.href ='../customer/myorder-'+yueindex+'-5-'+index+'.html';
						}else{
							//页数截取失败 就指明是第一页
							window.location.href ='../customer/myorder-'+yueindex+'-'+1+'.html';
						}
						
					}else{
						//随便给一个错的路径 报异常
						window.location.href = '../error/myorder-'+index+'.html';
					}
				}else{ //一月前
					if(data==1){
						$('#returnYuanyin').val(''); //清空 退货原因原因
						if(!isNaN(index)){
							if(!isNaN(yueindex)){
								window.location.href ='../customer/myorder-'+yueindex+'-5-'+index+'.html';
							}else{
								window.location.href = '../customer/myorder-'+index+'.html';
							}
							
						}else{
							//页数截取失败 就指明是第一页
							window.location.href ='../customer/myorder-'+1+'.html';
						}
						
					}else{
						//随便给一个错的路径 报异常
						window.location.href = '../error/myorder-'+index+'.html';
					}
				}
				
				
			}
			
		});
	}
}

//退单提示 失去焦点事件
function jiaodian(){
	var returnperson = $.trim($('#returnperson').val());
	if(returnperson ==''){
		$(".err_returnperson").addClass("red");
		$(".err_returnperson").html("<img src='../images/red_cha.png'/>退货收件人不能为空！");
		bool = false;
	}else{
		$(".err_returnperson").html("<img src='../images/duihao_1.png'/>");
	}
	
}
         


//验证退货信息
function checkText(returnyuanyin){
	var bool = true;
	
	if(returnyuanyin ==''){
		$(".err_yuanyin").addClass("red");
		$(".err_yuanyin").html("<img src='../images/red_cha.png'/>退货原因不能为空！");
		bool = false;
	}else{
		$(".err_yuanyin").html("<img src='../images/duihao_1.png'/>");
	}
	return bool;
}



function cancelOrder(url) {
	//$("#go_pay_00").prop("href", url);
	cUrl = url;
	dia(2);
}
function comfirmgoods(url) {
	$("#go_pay_01").prop("href", url);
	dia(3);
}
function delorder(url) {
	$("#delorder").prop("href", url+"?&CSRFToken="+$("#hi_token").val());
	dia(2);
}

//取消
function quxiao(){
	$(".err_tip").hide();
	$(".input_tip").hide();
	cls();
}

function comfirmgift(url) {
	$("#go_pay_01").prop("href", url);
	dia(3);
}

//removeAttr("onclick");
function changeUrl(){
	var cFlag = false;
	$(".selCont input").each(function(){
    	if($(this).prop("checked")){
    		cFlag = true;
    	}
    });
	if(cFlag){
		$(".s-err_tip").hide();
		var paramStr = "?reason=";
		//if($("#other_yy").prop("checked")){
		//	if($.trim($(".sel_txa").val()).length < 10){
		//		$(".input_tip").show();
		//		return;
		//	}else{
		//		$(".input_tip").hide();
		//	}
		//	paramStr+=$(".sel_txa").val();
		//}else{
			paramStr+=$(".mn_sel").html();
		//}
		
		location.href=cUrl+paramStr+"&CSRFToken="+$("#hi_token").val();
		////提交请求之后  删除onclick事件  避免网络延迟 多次提交
		$('.go_pay').removeAttr("onclick");
		
	}else{
		$(".s-err_tip").show();
		return;
	}
}

function cancelfollow(url) {
	$(".go_pay").prop("href", url+"?&CSRFToken="+$("#hi_token").val());
	dia(2);
}
function cancelbrowse(url) {
	// var truthBeTold = window.confirm("确定取消收藏该商品吗?");
	// if (truthBeTold) {
	window.location.href = url+"?&CSRFToken="+$("#hi_token").val();
	// }
}
function onselectMonth(obj) {
	window.location.href = "../customer/myintegral-" + $(obj).val() + "-1.html";
}
function addattr(obj, gid) {
	url = "../atte/" + gid + ".html";
	$.ajax({
		type : 'post',
		url : url,
		async : false,
		success : function(data) {
			if (data >= 1) {
				$("#f_img").prop("src","../images/mod_succ.png");
				$("#con_00").text("收藏成功！");
				dia(2);
			} else {
				$("#f_img").prop("src","../images/mod_war.png");
				$("#con_00").text("你已经收藏过该商品！");
				dia(2);
			}
		}
	});
}

function onselectOrder(obj) {
	window.location.href = "../customer/myintegral-" + $(obj).val() + "-1.html";
}
$(".c_tip").removeClass("m_tip");
function addcomplain() {
	//var reg= /^([\u4e00-\u9fa5_A-Za-z0-9 \\`\\~\\!\\@\\#\\$\\^\\&\*\(\)\=\{\}\'\:\;\'\,[\]\.\/\?\~\！\@\#\￥\…\…\&\*\（\）\;\—\|\{\}\【\】\‘\；\：\”\“\'\。\，\、\？])+$/;
	var reg1=/^(([^\^\.<>%&',;=?$"'#~\]\[{}\\/`\|])*)$/;
	var orderNo = $("#od").text();
	var comType = $("#complainty option:selected").text();
	var comCon = $("#complaincon").val();
	var customerId=$("#customer_id").val();

	if (comCon == null || $.trim(comCon).length < 10 || $.trim(comCon).length > 499) {
        $("#errorContent").html("字数在10-500字内!");
		dia(3)
		return;
	} else {
		if(!reg1.test(comCon)){
            $("#errorContent").html("不能包含特殊字符!");
            dia(3)
			return;
		}
		$(".c_tip").removeClass("m_tip").html("不能包含特殊字符请您如实填写原因及商品情况，字数在10-500字内且不要包含特殊字符。");
		
	}
	var paramStr = "complainType=" + comType;
	paramStr += "&complainContext=" + comCon;
	paramStr += "&orderNo=" + orderNo;
	paramStr += "&customerId=" + customerId;
	paramStr += "&CSRFToken="+$("#hi_token").val();
	$.ajax({
		type : 'post',
		url : '../customer/addcomplain.html',
		data : paramStr,
		async : false,
		success : function(data) {
			if (data > 0) {
				location.href = "../customer/complainsuccess.html";
			} else {
				dia(2);
			}
		},
		error : function(){
			dia(2);
		}
	});
}
