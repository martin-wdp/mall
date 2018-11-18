/**
 * 
 */

$(function(){
    /*加载省份*/
    //loadProvice();


	
	/**
	 * 省份改变时 触发此事件 开始加载下一级 --城市 select[id^='province']
	 */
	$("#province").change(function(){
        $("#province").removeClass("n_error");
        $("#city").removeClass("n_error");
        $("#district").removeClass("n_error");
		loadCity($(this).val());
	});
	/**
	 * 城市改变时 触发此事件 开始加载下一级 --区县
	 */
	$("#city").change(function(){
		loadDistrict($(this).val());
	});







    /*$(".location a:eq(0)").click(function(){
     var href=$(".menu_list .menu_cont:visible").eq(0).find("a").prop("href");
     location.href=href;
     });*/



    /*

	 /**
	 * 省份改变时 触发此事件 开始加载下一级 --城市 select[id^='province']
	 */
	$(".province").change(function(){
		loadCity($(this).val());
	});
	/**
	 * 城市改变时 触发此事件 开始加载下一级 --区县
	 */
	$(".city").change(function(){
		loadDistrict($(this).val());
	});
	/**
	 * 区县改变时 触发此事件 开始加载下一级 --街道
	 */
	$(".district").change(function(){
		loadStreet($(this).val());
	});

	/**
	 * 省份改变时 触发此事件 开始加载下一级 --城市 select[id^='province']
	 */
	$(".cprovince").change(function(){
		loadCCity($(this).val());
	});
	/**
	 * 城市改变时 触发此事件 开始加载下一级 --区县
	 */
	$(".ccity").change(function(){
		loadCDistrict($(this).val());
	});
	
	/**
	 * 区县改变时 触发此事件 开始加载下一级 --街道
	 */
	$(".cdistrict").change(function(){
		loadCStreet($(this).val());
	});
	
	
	/**
	 * 省份改变时 触发此事件 开始加载下一级 --城市 select[id^='province']
	 */
	$(".bprovince").change(function(){
		loadBCity($(this).val());
	});
	/**
	 * 城市改变时 触发此事件 开始加载下一级 --区县
	 */
	$(".bccity").change(function(){
		loadBDistrict($(this).val());
	});
	
	/**
	 * 区县改变时 触发此事件 开始加载下一级 --街道
	 */
	$(".bdistrict").change(function(){
		loadBStreet($(this).val());
	});



	/*
	$(".role_setting").each(function(){
		var cur = $(this);
		cur.find("b").click(function(){
			cur.find(".st_box").show();
			});
		$(document).click(function(event){
			if(!$(event.target).isChildAndSelfOf(cur)) {
				cur.find(".st_box").hide();
				};
			});
	});
	
	$(".ot_ops").each(function(){
		var cur = $(this);
		cur.click(function(){
			cur.parents(".sd_lb").next("p").show();
			});
		});
	*/
	/*保存修改公司營業執照信息*/
	$("#com_btn").click(function(){
        check();
        function check() {
            var flag = true;
            var regu = "^.+(.gif|.jpg|.jpeg|.GIF|.JPG|.JPEG|.png)$";
            var re = new RegExp(regu);
            var cardUrlE = $("#cardUrlE").val();
            var bussUrlE = $("#bussUrlE").val();
            if (!re.test(cardUrlE) && cardUrlE != null && cardUrlE != '') {
                flag = false;
                showTipAlert("图片文件格式错误！");
                return;
            }
            if (!re.test(bussUrlE) && bussUrlE != null && bussUrlE != '') {
                flag = false;
                showTipAlert("图片文件格式错误！");
                return;
            }
            var companyContactTel = $("#companyContactTel").val();
            var reg = /^(1[0-9]{10})$/;
            var reg2=/^([0-9][0-9]*(\.[0-9]{1,2})?|0\.(?!0+$)[0-9]{1,2})$/;
            if($("#companyCapital").val()!=""&&!reg2.test($("#companyCapital").val())){
                showTipAlert("请输入正确数字!");
                return;
            }
            if (reg.test(companyContactTel) && flag == true) {
                var province = $(".province").find("option:selected").text();
                var city = $(".city").find("option:selected").text();
                var district = $(".district").find("option:selected").text();

                var cprovince = $(".cprovince").find("option:selected").text();
                var ccity = $(".ccity").find("option:selected").text();
                var cdistrict = $(".cdistrict").find("option:selected").text();

                $("#bussAddr").val(province + "" + city + "" + district);
                $("#companyAddr").val(cprovince + "" + ccity + "" + cdistrict);
                $("#com_form").submit();
            } else {
                showTipAlert("请输入正确的公司紧急联系人手机！");
                return;
            }
        }
	});
	/*保存公司组织机构代码证*/
	$("#ora_btn").click(function(){
        check();
        function check(){
            var flag = true;
            var regu="^.+(.gif|.jpg|.jpeg|.GIF|.JPG|.JPEG|.png|.PNG)$";
            var re = new RegExp(regu);
            var companyResearchUrlE = $("#companyResearchUrlE").val();
            if( !re.test(companyResearchUrlE) && companyResearchUrlE != null &&  companyResearchUrlE != ''){
                flag = false;
                showTipAlert("图片文件格式错误！");
                return;
            }
            if(flag == true) {
                $("#ora_form").submit();
            }
        }
	});
	/*保存修改公司稅務登記證信息*/
	$("#tax_btn").click(function(){
        check();
        function check(){
            var flag = true;
            var regu="^.+(.gif|.jpg|.jpeg|.GIF|.JPG|.JPEG|.png)$";
            var re = new RegExp(regu);
            var bussTaxRegistUrlE = $("#bussTaxRegistUrlE").val();
            var bussTaxCredUrlE = $("#bussTaxCredUrlE").val();
            if( !re.test(bussTaxCredUrlE) && bussTaxCredUrlE != null  && bussTaxCredUrlE != ''){
                flag = false;
                showTipAlert("图片文件格式错误！");
                return;
            }
            if(!re.test(bussTaxRegistUrlE) && bussTaxRegistUrlE != null && bussTaxRegistUrlE != ''){
                flag = false;
                showTipAlert("图片文件格式错误！");
                return;
            }
            if(flag == true) {
                $("#tax_form").submit();
            }
        }
	});
	/*結算銀行登記信息*/
	$("#bank_btn").click(function(){
        check();
        function check(){
            var flag = true;
            var regu="^.+(.gif|.jpg|.jpeg|.GIF|.JPG|.JPEG|.png)$";
            var re = new RegExp(regu);
            var bankUrlE = $("#bankUrlE").val();
            if( !re.test(bankUrlE) && bankUrlE != null && bankUrlE != ''){
                flag = false;
                showTipAlert("图片文件格式错误！");
                return;
            }
            if(flag == true) {
                $("#bank_form").submit();
            }
        }
	});
});


/**
 * 加载省份
 */
function loadProvice(){
    //var options = "<option value='' >"+"请选择"+"</option>";
    $.ajax({
        type: 'post',
        url:'getAllProvince.htm',
        async:false,
        success: function(data) {
            var options = "";
            options +=  "<option value=''>"+"请选择"+"</option>";
            for( var i=0; i<data.length; i++){
                var province=data[i];
                options +=  "<option value='"+province.provinceId+"'>"+province.provinceName+"</option>";
            }
            $(".province").html(options);
        }
    });
    loadCity($(".province").val());
};

/**
 * 加载城市
 * @param pid 省份编号
 */
function loadCity(pid){
    var paramStr="provinceId="+pid;
    $.ajax({
        type: 'post',
        url:'getAllCityByPid.htm',
        data:paramStr,
        async:false,
        success: function(data) {
            if(data.length!=0){
                var options = "";
                for( var i=0; i<data.length; i++){
                    var city=data[i];
                    options +=  "<option value='"+city.cityId+"'>"+city.cityName+"</option>";
                }
                $(".city").html(options);
            }else{
                $(".city").html("<option value='' >"+"请选择"+"</option>");
                $(".district").html("<option value='' >"+"请选择"+"</option>");
                /*$("#street").html("<option value='' >"+"请选择"+"</option>");*/
            }
        }
    });
    loadDistrict($(".city").val());
}
/**
 * 加载区县
 * @param pid 省份编号
 */
function loadDistrict(pid){
    var paramStr = "cityId=" + pid;
    $.ajax({
        type: 'post',
        url:'getAllDistrictByCid.htm',
        data:paramStr,
        async:false,
        success: function(data) {
            if(data.length != 0){
                var options = "";
                for( var i=0; i<data.length; i++){
                    var district=data[i];
                    options +=  "<option value='"+district.districtId+"'>"+district.districtName+"</option>";
                }
                $('.district').html(options);
            }else{
                $(".district").html("<option value='' >"+"请选择"+"</option>");
            }
        }
    });
    //loadStreet($(".district").val());
}












function updateStoreInfo(str){
	
	$("."+str).hide();
	$("."+str+"_edit").show();
}
function cancelUpdate(str){
	$("."+str).show();
	$("."+str+"_edit").hide();
}


function selectLocationOption (pid,cid,did,sid,po,co,dio,so){
	if(pid==""){
		return;
	}
//	alert(pid+"  "+cid+" "+ did +" "+sid);
	$("."+po+" option[value='"+pid+"']").prop("selected","selected"); //选中省份
	loadCity(pid);
	$("."+co+" option[value='"+cid+"']").prop("selected","selected"); //选中城市
	loadDistrict(cid); 
	$("."+dio+" option[value='"+did+"']").prop("selected","selected");//选中区县
	loadStreet(did);
	$("."+so+" option[value='"+sid+"']").prop("selected","selected"); //选中街道
}
function selectLocationOptionC (pid,cid,did,sid,po,co,dio,so){
	if(pid==""){
		return;
	}
//	alert(pid+"  "+cid+" "+ did +" "+sid);
	$("."+po+" option[value='"+pid+"']").prop("selected","selected"); //选中省份
	loadCCity(pid);
	$("."+co+" option[value='"+cid+"']").prop("selected","selected"); //选中城市
	loadCDistrict(cid); 
	$("."+dio+" option[value='"+did+"']").prop("selected","selected");//选中区县
	loadCStreet(did);
	$("."+so+" option[value='"+sid+"']").prop("selected","selected"); //选中街道
}
function selectLocationOptionB (pid,cid,did,sid,po,co,dio,so){
	if(pid==""){
		return;
	}
	$("."+po+" option[value='"+pid+"']").prop("selected","selected"); //选中省份
	loadBCity(pid);
	$("."+co+" option[value='"+cid+"']").prop("selected","selected"); //选中城市
	loadBDistrict(cid); 
	$("."+dio+" option[value='"+did+"']").prop("selected","selected");//选中区县
	loadBStreet(did);
	$("."+so+" option[value='"+sid+"']").prop("selected","selected"); //选中街道
}


/**
 * 加载街道
 * @param cid 城市编号
 */

function loadStreet(cid){
	var paramStr="dId="+cid;
	 $.ajax({
      type: 'post',
      url:'getAllStreetByDid.htm',
      data:paramStr,
      async:false,
      success: function(data) {
    	  if(data.length!=0){
  			var options = "";
  			for( var i=0; i<data.length; i++){
  				var street=data[i];
  				options +=  "<option value='"+street.streetId+"'>"+street.streetName+"</option>";
  			}
  			$('.street').html(options);
	  	  }else{
	  			$(".street").html("<option value='' >"+"请选择"+"</option>");
	  	  }
      }
	 });
}


/***//*


*/
/**
 * 加载省份
 */

function loadCProvice(){
	//var options = "<option value='' >"+"请选择"+"</option>";
	$.ajax({
		type: 'post',
		url:'getAllProvince.htm',
		async:false,
		success: function(data) {
			var options = "";
			for( var i=0; i<data.length; i++){
				var province=data[i];
				options +=  "<option value='"+province.provinceId+"'>"+province.provinceName+"</option>";
			}
//     		$("select[class^='province']").html(options);
			$(".cprovince").html(options);
		}
	});
	loadCCity($(".cprovince").val());
};


/**
 * 加载城市
 * @param pid 省份编号
 */

function loadCCity(pid){
	var paramStr="provinceId="+pid;
	$.ajax({
		type: 'post',
		url:'getAllCityByPid.htm',
		data:paramStr,
		async:false,
		success: function(data) {
			if(data.length!=0){
				var options = "";
				for( var i=0; i<data.length; i++){
					var city=data[i];
					options +=  "<option value='"+city.cityId+"'>"+city.cityName+"</option>";
				}
				$(".ccity").html(options);
			}else{
				$(".ccity").html("<option value='' >"+"请选择"+"</option>");
				$(".cdistrict").html("<option value='' >"+"请选择"+"</option>");
				$(".cstreet").html("<option value='' >"+"请选择"+"</option>");
			}
		}
	});
	loadCDistrict($(".ccity").val());
}

/**
 * 加载区县
 * @param pid 省份编号
 */

function loadCDistrict(pid){
	var paramStr = "cityId=" + pid;
	$.ajax({
		type: 'post',
		url:'getAllDistrictByCid.htm',
		data:paramStr,
		async:false,
		success: function(data) {
			if(data.length != 0){
				var options = "";
				for( var i=0; i<data.length; i++){
					var district=data[i];
					options +=  "<option value='"+district.districtId+"'>"+district.districtName+"</option>";
				}
				$('.cdistrict').html(options);
			}else{
				$(".cdistrict").html("<option value='' >"+"请选择"+"</option>");
			}
		}
	});
	loadCStreet($(".cdistrict").val());
}

/**
 * 加载街道
 * @param cid 城市编号
 */

function loadCStreet(cid){
	var paramStr="dId="+cid;
	$.ajax({
		type: 'post',
		url:'getAllStreetByDid.htm',
		data:paramStr,
		async:false,
		success: function(data) {
			if(data.length!=0){
				var options = "";
				for( var i=0; i<data.length; i++){
					var street=data[i];
					options +=  "<option value='"+street.streetId+"'>"+street.streetName+"</option>";
				}
				$('.cstreet').html(options);
			}else{
				$(".cstreet").html("<option value='' >"+"请选择"+"</option>");
			}
		}
	});
} 

/**  bank **//*

*/
/**
 * 加载省份
 */

function loadBProvice(){
	//var options = "<option value='' >"+"请选择"+"</option>";
	$.ajax({
		type: 'post',
		url:'getAllProvince.htm',
		async:false,
		success: function(data) {
			var options = "";
			for( var i=0; i<data.length; i++){
				var province=data[i];
				options +=  "<option value='"+province.provinceId+"'>"+province.provinceName+"</option>";
			}
//     		$("select[class^='province']").html(options);
			$(".bprovince").html(options);
		}
	});
	loadBCity($(".bprovince").val());
};


/**
 * 加载城市
 * @param pid 省份编号
 */

function loadBCity(pid){
	var paramStr="provinceId="+pid;
	$.ajax({
		type: 'post',
		url:'getAllCityByPid.htm',
		data:paramStr,
		async:false,
		success: function(data) {
			if(data.length!=0){
				var options = "";
				for( var i=0; i<data.length; i++){
					var city=data[i];
					options +=  "<option value='"+city.cityId+"'>"+city.cityName+"</option>";
				}
				$(".bcity").html(options);
			}else{
				$(".bcity").html("<option value='' >"+"请选择"+"</option>");
				$(".bdistrict").html("<option value='' >"+"请选择"+"</option>");
				$(".bstreet").html("<option value='' >"+"请选择"+"</option>");
			}
		}
	});
	loadBDistrict($(".bcity").val());
}
/**
 * 加载区县
 * @param pid 省份编号
 */

function loadBDistrict(pid){
	var paramStr = "cityId=" + pid;
	$.ajax({
		type: 'post',
		url:'getAllDistrictByCid.htm',
		data:paramStr,
		async:false,
		success: function(data) {
			if(data.length != 0){
				var options = "";
				for( var i=0; i<data.length; i++){
					var district=data[i];
					options +=  "<option value='"+district.districtId+"'>"+district.districtName+"</option>";
				}
				$('.bdistrict').html(options);
			}else{
				$(".bdistrict").html("<option value='' >"+"请选择"+"</option>");
			}
		}
	});
	loadBStreet($(".bdistrict").val());
}
/**
 * 加载街道
 * @param cid 城市编号
 */

function loadBStreet(cid){
	var paramStr="dId="+cid;
	$.ajax({
		type: 'post',
		url:'getAllStreetByDid.htm',
		data:paramStr,
		async:false,
		success: function(data) {
			if(data.length!=0){
				var options = "";
				for( var i=0; i<data.length; i++){
					var street=data[i];
					options +=  "<option value='"+street.streetId+"'>"+street.streetName+"</option>";
				}
				$('.bstreet').html(options);
			}else{
				$(".bstreet").html("<option value='' >"+"请选择"+"</option>");
			}
		}
	});
}


function addmanager(){
	$(".role_list li").removeClass("cur");
	
	var str="<li onclick='ct(this);'>" +
			"<input class='role_name vm fl iq_text mt10' value='新职位'/>" +
				"<div class='role_setting fr pr'>" +
					"<b></b>" +
					"<div class='st_box pa none'>" +
						"<p><a href='javascript:;'>重命名</a></p>" +
						"<p><a href='javascript:;' onclick='delNewRole(this)'>删除</a></p>" +
					"</div>" +
				"</div>" +
			"</li>";
	$(".role_list").prepend(str);
	
	var con='<div class="auth_wp"><div class="auth_tit clearfix"><h4 class="fl">设置权限</h4><a class="add_auth fr" href="javascript:;" onclick="dia(2)">添加权限</a></div><div class="auth_cont mt10"><p class="f14">暂未给此角色添加权限</p></div>';
	$(".account_cont").prepend(con);
	
	$(".role_setting").each(function(){
		var cur = $(this);
		cur.find("b").click(function(){
			cur.find(".st_box").show();
			});
		$(document).click(function(event){
			if(!$(event.target).isChildAndSelfOf(cur)) {
				cur.find(".st_box").hide();
				};
			});
	});
	
	$(".role_list li:eq(0) input").focus();
	$(".role_list li:eq(0) input").blur(function(){
		var paramStr = "designation="+ $(this).val();
		var str=$(this).val();
		
		//职位只能是汉字和字母 （设置白名单 可以允许那些）
		var re=/^([\u4E00-\uFA29]*[a-z]*[A-Z]*)+$/;
	    if (re.test(str)) {   
			$.ajax({
				type: 'post',
				url:'checkauthorityexist.html',
				data:paramStr,
				async:false,
				success: function(data) {
					if(data == 0){
						//异步添加
						$.ajax({
							type: 'post',
							url:'addauthority.html',
							data:paramStr,
							async:false,
							success: function(data) {
								if(data == 1){
									$(".role_list li:eq(0)").prepend("<span class='role_name fl'></span>");
									$(".role_list li:eq(0) input").hide();
									$(".opera_tip").text("添加成功!");
									dia(4);
								}else{
									$(".opera_tip").text("请重试!");
									dia(4);
								}
							},
							error:function(){
								//网络异常
								dia(5);
							}
						});
					}else{
						dia(6);
					}
				},
				error:function(){
					//网络异常
					dia(5);
				}
			});
	    }else{
	    	//录入数据的筛选
	    	dia(20);
	    }
	});
}


/**
 * 删除新增角色
 * @param obj
 *

function delNewRole(obj){
	$(obj).parent().parent().parent().parent().remove();
}


/**
 * 执行删除角色
 * @param obj
 */

function dodelrole(obj){
	var paramStr="id="+emp_id;
	//异步添加
	$.ajax({
		type: 'post',
		url:'delauthority.html',
		data:paramStr,
		async:false,
		success: function(data) {
			$(emp_obj).parent().parent().parent().parent().next().click();
			//ajax删除
			$(emp_obj).parent().parent().parent().parent().remove();
		},
		error:function(){
			//网络异常
			dia(5);
		}
	});
	
	//关闭弹出层
	cls();
}

function updateRileName(obj,id){
	var cur=$(obj).parent().parent().parent().parent();
	cur.find("input").remove();
	cur.prepend("<input class='role_name vm fl iq_text mt10' value='"+$(obj).parent().parent().parent().parent().find("span").text()+"'></input>");
	cur.find("span").remove();
	$(obj).parent().parent().parent().hide();
	cur.find("input").blur(function(){
		var paramStr="id="+id+"&designation="+$(this).val();
		$.ajax({
			type: 'post',
			url:'updateauthorityname.htm',
			data:paramStr,
			async:false,
			success: function(data) {
                if(data==1){
                    flag=true;
                }else{
                    dia(6);
                }
			},
			error:function(){
				//网络异常
				dia(5);
			}
		});
		
		if(flag){
			$(".mask").fadeIn();
			$(".dia5").fadeIn();
		}
	});
}

function diaAuthrity(n,id,obj){
	
};


/**
 * 添加权限按钮事件
 * @param n 显示框ID
 * @param id 权限ID
 */

function loadAuthority (n,id){
	var str = null;
	var dataUrl="pid="+id;
	$.ajax({
		type: 'post',
		url:'loadallauthority.html',
		data:dataUrl,
		async:true,
		success: function(data) {
			$(".dia_cont").html("");
			//加载所有权限
			if(data.pages != null){
		          for (var i = 0; i < data.pages.length; i++) {
			           	var obj=data.pages[i];
			           	str ="<h4 class=\"qx_tit\"><label><input class=\"vm mr5 p_inp\" id="+obj.id+" type=\"checkbox\" />"+obj.designation+"</label></h4><dl class=\"dia_dl\">";
			           	for (var j = 0; j < obj.menuVos.length; j++) {
							str += '<dt><label><input class="vm mr5" id='+obj.menuVos[j].id+' type="checkbox" />'+obj.menuVos[j].designation+'</label></dt>';
						}
						$(".dia_cont").append(str);	
		          }
		         
			}
			var idStr="";
			var flag=true;
			//以下为选中当前拥有的权限页面
			$(".dia_cont h4").each(function(){
				var num=$(this).find("input").prop("id");
				for (var j = 0; j < data.rolePages.length; j++) {
					var curpage =data.rolePages[j];
					if(flag){
						idStr+="|"+curpage.id;
					}
					if(curpage.id == num){
						$(this).find("input").prop("checked","true");
					}
				}
				flag=false;
			});
			
			$(".dia_cont dl dt").each(function(){
				var num=$(this).find("input").prop("id");
				for (var j = 0; j < data.rolePages.length; j++) {
					var curpage =data.rolePages[j];
					if(flag){
						idStr+="|"+curpage.id;
					}
					if(curpage.id == num){
						$(this).find("input").prop("checked","true");
					}
				}
				flag=false;
			});
			
			$(".dia_cont").append('<input type="hidden" id="page_id" value="'+idStr+'" />');
			$(".dia_cont").append('<input type="hidden" id="auth_id" value="'+id+'" />');
			//加载完毕之后  预加载方法 和事件
			$(function(){
				$(".p_inp").click(function(){
					var cur_p=$(this).parent().parent().next();
					if($(this).prop("checked")){
						$(cur_p).find("input").prop("checked","checked");
					}else{
						$(cur_p).find("input").prop("checked","");
					}
				});
				
				$(".dia_cont").find("input").click(function(){
					idStr="";
					$(".dia_cont h4").each(function(){
						if($(this).find("input").prop("checked")){
							idStr+="|"+$(this).find("input").prop("id");
						}
					});
					
					$(".dia_cont dl dt").each(function(){
						if($(this).find("input").prop("checked")){
							idStr+="|"+$(this).find("input").prop("id");
						}
					});
					$("#page_id").val(idStr);
				});
			});
		},
		error:function(){
			//网络异常
			dia(5);
		}
	});
	$(".mask").fadeIn();
	$(".dia"+n).fadeIn();
}


function updateAuth(path){
	var page_id = $("#page_id").val();
	var auth_id = $("#auth_id").val();
	var ss=page_id.substring(page_id.indexOf("|")+1).split('|');
	var paramStr="pagesId="+ss
		+"&authId="+auth_id;
	var flag=false;
	$.ajax({
		type: 'post',
		url:'updateauthority.htm',
		data:paramStr,
		async:false,
		success: function(data) {
			flag=true;
		},
		error:function(){
			//网络异常
			dia(5);
		}
	});
	if(flag){
		$(".dialog").fadeOut();
		$(".mask").fadeIn();
		$(".dia5").fadeIn();
	}
	
}

function clsDia(){
	var url =$(".menu_cont").find(".cur").find("a").prop("href");
	location.href=url;
}

function modifyemptodisable(custId,flag){
	$("#cust_id_hide").val(custId);
	$("#flag_id_hide").val(flag);
	if(flag == 0){
		$(".flag_tip").text("你确定要禁用该用户吗?");
	}else{
		$(".flag_tip").text("你确定要启用该用户吗?");
	}
	dia(1);
	
}

function dodisable(){
	var url="modifyemptodisable.htm?custId="+$("#cust_id_hide").val()+"&flag=";
	if($("#flag_id_hide").val()==0){
		url += "disable";
	}
	location.href = url;
	$("#cust_id_hide").val("");
}

function delemp(custId){
	$("#cust_id_hide").val(custId);
	dia(2);
}
function dodelemp(obj){
	var url="delemp.htm?custId="+$("#cust_id_hide").val();
	location.href = url;
	$("#cust_id_hide").val("");
}


function toaddemp(){
	location.href="addemp.html";
}

function checkEmp(){
	$("#empForm").submit();
}


function loadauth(){
	$.ajax({
		type: 'post',
		url:'loadmanager.htm',
		async:false,
		success: function(data) {
			var str = '';
			for (var i = 0; i < data.roles.length; i++) {
				str+='<label><input class="vm mr5" type="radio" value="'+data.roles[i].id+'" name="authId" />'+data.roles[i].designation+'</label>';
			}
			$(".authlist").append(str);
		},
		error:function(){
			//网络异常
			dia(5);
		}
	});
}

function updaterols(customerId){
	$("#updatecustid").val(customerId);
	dia(3);
}
















