/**
 * 宁派电子商务平台后台会员部分页面js文件 
 * 作者 ：NINGPAI-zhangqiang
 * 时间：2013年12月12日10:23 
 * 版本：0.0.01版
 */
jQuery.fn.isChildOf = function(b){ return (this.parents(b).length > 0); };
jQuery.fn.isChildAndSelfOf = function(b){ return (this.closest(b).length > 0); };


$(function() {
	$(".sub").button({ icons: { primary: "ui-icon-zoomout"  } });
	tabCounter = 2;
	modified=0;
	$("input[id^='datepicker']").datepicker();
	$("tr[id^='expand']").hide();
	$("#dialog-wrong-tip").hide();
	$("#expand").hide();
	$(".advancedsearch").hide();
	$("#dialog-confirm").hide();
	$("#dialog-tip").hide();
	$("#dialog-tip1").hide();
	$("#dialog-err-tip").hide();
	$("#city").hide();
	$("#district").hide();
	$("#street").hide();
	$("#city_a").hide();
	$("#district_a").hide();
	$("#tabs").tabs();
	$("#expand_content").hide();
	$("#dialog-wrong-tip").hide();
	$("#dialog-authority").hide();
	$("#dialog-consult-confirm").hide();
	$("input[type=submit],button").button().click(function(event) {
		event.preventDefault();
	});
	$("#combobox, #moneyTypes").combobox();
	$("#toggle").click(function() {
		$("#combobox, #moneyTypes").toggle();
	});
	$(document).tooltip();
		username = $("#customerUsername"),
	    email = $("#email"), 
	    password = $("#password"), 
	    repassword = $("#repassword"), 
	    gender = $("#gender"),
	    realname = $("#realname"), 
	    area = $("#area"), 
	    mobile = $("#mobile"), 
	    cardid = $("#card_id"), 
	    address_detail = $("#address_detail"), 
	    usernametip = $(".customerUsernametip"), 
	    emailtip = $(".emailtip"), 
	    passwordtip = $(".passwordtip"),
	    repasswordtip = $(".repasswordtip"), 
	    mobiletip=$(".mobiletip"),
	    info_detail=$("#info_detail"),
	    realnametip=$(".realnametip"),
	    cardidtip=$(".cardidtip"),
	    allFields = $([]).add(username).add(email).add(password).add(repassword)
	    				 .add(gender).add(realname).add(area).add(mobile).add(address_detail)
	    				 .add(usernametip).add(emailtip).add(passwordtip).add(repasswordtip).add(mobile).add(cardid).add(info_detail),
	    alltips=$([]).add(usernametip).add(emailtip).add(passwordtip).add(repasswordtip).add(mobiletip).add(realnametip).add(cardidtip);
	//添加 编辑会员对话框属性设置
	$("#dialog-form").dialog(
	{
		autoOpen : false,
		height : 640,
		width : 715,
		modal : true,
		buttons : {
			"确定" : function() {
					var bValid = true;
					allFields.removeClass("ui-state-error");
					allFields.removeClass("ui-state-highlight");
					alltips.text("").removeClass("ui-state-error");
					alltips.removeClass("ui-state-highlight");
//					if(modified!=1&&$("#isExist").val()=="false"){
//						updateTips("用户名已存在", usernametip);
//					}
					bValid =  checkLength(username, "用户名", 4, 16, usernametip) && bValid;
					bValid =  bValid && checkRegexp(username, /^[a-z]([0-9a-z_])+$/i, "用户名必须以字母开头,可包含a-z, 0-9,下划线", usernametip) ;
					if(modified != 1){
						bValid =  bValid && checkExist("customerUsername",$("#customerUsername").val(),"checkExistCustomerUsername.htm","用户名");
					}
					bValid =  checkLength(email, "邮箱", 6, 80,emailtip) && bValid;
					bValid =  bValid &&  checkRegexp( email, /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/i,"请输入格式正确的Email,如 eg.ui@jquery.com",emailtip) ;
					if(modified != 1){
						bValid =  checkLength(password, "密码", 6, 16, passwordtip) && bValid;
						bValid =  chackPassword(password,repassword,repasswordtip) && bValid;
						bValid =  bValid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9", passwordtip) ;
					}
					if(realname.val()!=""){
						bValid = checkRegexp(realname, /^([\u4e00-\u9fa5]+|([a-z]+\s?)+)$/, "请输入正确的姓名(中文或者英文)", realnametip) && bValid;
					}
					if(mobile.val()!=""){
						bValid = checkRegexp(mobile, /^(13|14|15|18)\d{9}$/, "请输入正确的手机号码", mobiletip) && bValid;
					}
					if(cardid.val()!=""){
						bValid = checkRegexp(cardid, /^\d{15}(\d{2}[A-Za-z0-9])?$/, "请输入正确的身份证号码", cardidtip) && bValid;
					}
					if (bValid) {
						$('#addForm').submit();
						$(this).dialog("close");
					}
				},
			'取消' : function() {
						$(this).dialog("close");
					}
		},
		close : function() {
					usernametip.text("");
					emailtip.text("");
					passwordtip.text("");
					repasswordtip.text("");
					$('#gender1').prop('checked', 'checked');
					$("select[id^='city']").html("");
					$("select[id^='district']").html("");
					$("select[id^='street']").html("");
					$("select[id^='city']").hide();
					$("select[id^='district']").hide();
					$("select[id^='street']").hide();
					$("input[id^='customerUsername']").val("");
					allFields.val("").removeClass("ui-state-error");
					allFields.removeClass("ui-state-highlight");
					$(".p_div").show();
					$("#photoImg").attr("src","");
					$("input[name='customerId']").remove();
					$("#customerImg").val("");
				}
	});
	
	// 删除会员
	$("#del-userbatch").button().click(function() {
		if(checkSelected('customerid',0)){
			$("#dialog-confirm").dialog("open");
		}else{
			 $("#dialog-tip").dialog({
	     			resizable : false,
	     			height : 150,
	     			width : 270,
	     			modal : true,
	     			autoOpen : true,
	     			buttons : {
	     				"确定" : function() {
	     					$(this).dialog("close");
	     				}
	     			}
	     		});
		}
	});
	
	// 删除会员
	$("#del-user").button().click(function() {
		if(checkSelected('customerid',1)){
			$("#dialog-confirm").dialog("open");
		}else{
			 $("#dialog-tip").dialog({
	     			resizable : false,
	     			height : 150,
	     			width : 270,
	     			modal : true,
	     			autoOpen : true,
	     			buttons : {
	     				"确定" : function() {
	     					$(this).dialog("close");
	     				}
	     			}
	     		});
		}
	});
	
	// 编辑会员
	$("#modified-user").button().click(function() {
		if(checkSelected('customerid',1)){
//			$(".user_dl").find(".p_div ").remove();
			$(".p_div").hide();
			$('#addForm').attr('action', 'updateCustomer.htm?CSRFToken='+$("#hi_token").val());
			doSearchCustomer($("input[name='customerid']:checked").val());
		}else{
			 $("#dialog-tip").dialog({
	     			resizable : false,
	     			height : 150,
	     			width : 270,
	     			modal : true,
	     			autoOpen : true,
	     			buttons : {
	     				"确定" : function() {
	     					$(this).dialog("close");
	     				}
	     			}
	     		});
		}
	});
	
	//会员 搜索
	$("#search-user").button({
	      icons: {
		        primary: "ui-icon-search",//ui-icon-zoomout
		      }
		}).click(function() {
		if(checkInputContent()){
			$('#search_from').attr('action', 'queryByCustomerAllInfo.htm?showFlag=0&attr='+$(".search_sel span").text()+"&attr="+$(".search_text").val());
			$("#pageSize").val(5);
			$("#pageNo").val(1);
			$('#search_from').submit();
		}else{
			
		}
	});
	
	//会员 搜索
	$("#search-comm").button({
	      icons: {
		        primary: "ui-icon-search",//ui-icon-zoomout
		      }
		}).click(function() {
		if(checkInputContent()){
			$('#search_from').attr('action', 'queryByComment.htm?attr=0&attr='+$(".search_sel span").text()+"&attr="+$(".search_text").val());
			$("#pageSize").val(5);
			$("#pageNo").val(1);
			$('#search_from').submit();
		}
	});
	//会员 搜索
	$("#search-share").button({
		icons: {
			primary: "ui-icon-search",//ui-icon-zoomout
		}
	}).click(function() {
		if(checkInputContent()){
			$('#search_from').attr('action', 'initsharelist.htm?attr=0&attr='+$(".search_sel span").text()+"&attr="+$(".search_text").val());
			$("#pageSize").val(5);
			$("#pageNo").val(1);
			$('#search_from').submit();
		}
	});
	
	//清空搜索框
	$("#clear").button().click(function() {
		$(".advancedsearch input, .sp_search input").each(
			function(){
				$(this).val("");
			}
		);
		$(".advancedsearch select, .sp_search select").each(
			function(){
				$("option[value='']").prop("selected","selected");
			}
		);
	});
	
	//提示框设置
	$("#dialog-confirm").dialog({
		resizable : false,
		height : 150,
		width : 270,
		modal : true,
		autoOpen : false,
		buttons : {
			"确定" : function() {
				delMultiWin('deleteCustomer.htm?CSRFToken='+$("#hi_token").val(),"initCustomer.htm");
				$(this).dialog("close");
			},
			'取消' : function() {
				$(this).dialog("close");
			}
		}
	});
	
});


//检查长度 o:指定标签 n:属性中文名 min:最小值 max:最小值
function checkLength(o, n, min, max, obj) {
	if (o.val().length > max || o.val().length < min) {
		o.addClass("ui-state-error");
		updateTips(n + "长度必须在 " + min + " ~ " + max + "字符之间.", obj);
		return false;
	} else {
		obj.text('').removeClass("ui-state-highlight");
		return true;
	}
}

//改变错误提示内容 t 提示内容 obj 目标
function updateTips(t, obj) {
	obj.text(t).addClass("ui-state-highlight");
//	obj(function() {
//		obj.removeClass("ui-state-highlight");
//	}, 500);
}

// 正则验证
function checkRegexp(o, regexp, n, obj) {
	if (!(regexp.test(o.val()))) {
		o.addClass("ui-state-error");
		updateTips(n, obj);
		return false;
	} else {
		return true;
	}
}


function checkNull(o, n, obj) {
	if (o.val() == null || o.val()=='') {
		o.addClass("ui-state-error");
		updateTips(n, obj);
		return false;    
	} else {
		obj.text('').removeClass("ui-state-highlight");
		return true;
	}
}

function chackPassword(p,rep,reptip){
	if(rep.val().trim().length==0){
		rep.addClass("ui-state-error");
		updateTips("您两次输入的密码不相同!",reptip);
		return false;
	}
	if(p.val()!=rep.val()){
		rep.addClass("ui-state-error");
		updateTips("您两次输入的密码不相同!",reptip);
		return false;
	}else{
		reptip.text('').removeClass("ui-state-highlight");
		return true;
	}
}
/*ajax 通过ID查询并塞值到修改页面*/
function doSearchCustomer(customerId){
	fillPandL();
	modified=1;
	$.post("queryCustomerById.htm?CSRFToken="+$("#hi_token").val()+"&customerId="+customerId,function(data){
		 	 $("input[id^='customerUsername']").val(data.customerUsername);
		 	 $('#customerUsername').prop('readonly', 'readonly');
		 	 $("#email").val(data.infoEmail);
		 	 $("#password").val(data.customerPassword);
		 	 $("#repassword").val(data.customerPassword);
		 	 $("#repassword").val(data.customerPassword);
		 	 $("#mobile").val(data.infoMobile);
		 	 $("#photoImg").prop('src',data.customerImg);
		 	 if(data.infoGender==0){
		 		$('#gender1').prop('checked', 'checked');
		 	 }else if(data.infoGender==1){
		 		$('#gender2').prop('checked', 'checked');
		 	 }else{
		 		$('#gender3').prop('checked', 'checked');
		 	 }
		 	 //选中会员等级
		 	 $("#pointLevel option[value='"+data.pointLevelId+"']").prop("selected","selected");
		 	 //将会员省份 城市 区县选中
		 	 selectLocationOption(data, "province", "city", "district", "street");
		 	 //将收货地址 省份 城市 区县 选中
//		 	 if(data.customerAddress!=undefined){
//		 		selectLocationOption(data.customerAddress, "province_a", "city_a", "district_a", "street_a");
//		 		$("#customerAddressId").val(data.customerAddress.addressId);
//		 		$("#address_detail").val(data.customerAddress.addressDetail);
//		 	 }
		 	 $("#realname").val(data.infoRealname);
		 	 $("#mobile").val(data.infoMobile);
		 	 $("#card_id").val(data.infoCardid);
		 	 $("#info_detail").val(data.infoAddress);
		 	 $("#customerUsername").append("<input type='hiden' name='customerId' value='"+customerId+"' />");
			 $("#dialog-form").dialog("open").dialog("option", { title : "编辑会员" });
	});
}

function selectLocationOption (data,po,co,dio,so){
	$("#"+po+" option").each(
	 		function(i){
	 			if($(this).val()==data.infoProvince){
	 				$("#"+po+" option[value='"+i+"']").prop("selected","selected");//选中省份
	 				$.post("getAllCityByPid.htm?CSRFToken="+$("#hi_token").val()+"&provinceId="+data.infoProvince,function(cities){
	 						var options = "";
	 						for( var i=0; i<cities.length; i++){
	 							var city=cities[i];
	 							options +=  "<option value='"+city.cityId+"'>"+city.cityName+"</option>";
	 						}
	 						$('#'+co).html(options);
	 						$("#"+co).show();
	 					 	$("#"+co+" option[value='"+(data.infoCity)+"']").prop("selected","selected");//选中城市
	 					 	$.post("getAllDistrictByCid.htm?CSRFToken="+$("#hi_token").val()+"&cityId="+data.infoCity,function(districts){
	 					 	   if(districts.length!=0){
	 					 		 var optio = "";
		 					 	 for( var i=0; i<districts.length; i++){
		 					 		 var district=districts[i];
		 					 	     optio +=  "<option value='"+district.districtId+"'>"+district.districtName+"</option>";
		 					 	 }
		 					 	$('#'+dio).html(optio);
		 					 	$("#"+dio).show();
				 				$("#"+dio+" option[value='"+data.infoCounty+"']").prop("selected","selected"); //选中区县
				 				$.post("getAllStreetByDid.htm?CSRFToken="+$("#hi_token").val()+"&dId="+data.infoCounty,function(streets){
				 					if(streets.length!=0){
			 					 		 var optio = "";
				 					 	 for( var i=0; i<streets.length; i++){
				 					 		 var street=streets[i];
				 					 	     optio +=  "<option value='"+street.streetId+"'>"+street.streetName+"</option>";
				 					 	 }
				 					 	$('#'+so).html(optio);
				 					 	$("#"+so).show();
						 				$("#"+so+" option[value='"+data.infoStreet+"']").prop("selected","selected"); //选中区县
				 					}else{
				 						$('#'+so).html("");
			 					 		$("#"+so).hide();
				 					}
				 				});
	 					 	  }else{
	 					 		$('#'+dio).html("");
	 					 		$("#"+dio).hide();
	 					 	  }
	 					   });
	 				});
	 			}else{
	 				$('#'+co).html("");
	 				$('#'+dio).html("");
	 				$('#'+so).html("");
					$("#"+co).hide();
					$("#"+dio).hide();
					$("#"+so).hide();
	 			}
	 		}
	 	 );
}

// 反选
function unSelectAll(obj) {
	var checkboxs = document.getElementsByName(obj);
	for (var i = 0; i < checkboxs.length; i++) {
		var e = checkboxs[i];
		e.checked = !e.checked;
	}
	for (var j = 0; j < checkboxs.length; j++) {
		if(checkboxs[j].checked){
			$(checkboxs[j]).parent().parent().addClass("trbcak");
		}else{
			$(checkboxs[j]).parent().parent().removeClass("trbcak");
		}
	}
}

// 全选
function selectAll(obj) {
	var checkboxs = document.getElementsByName(obj);
	for (var i = 0; i < checkboxs.length; i++) {
		var e = checkboxs[i];
		e.checked = true;
		$(e).parent().parent().addClass("trbcak");
	}
}

function checkInputContent(){
	var regx=/^[\u4e00-\u9fa5_A-Za-z0-9]*$/;
	var ff=regx.test($(".search_text").val());
	if(!ff){
		$("#dialog-wrong-tip").dialog({
 			resizable : false,
 			height : 150,
 			width : 270,
 			modal : true,
 			autoOpen : true,
 			buttons : {
 				"确定" : function() {
 					$(".search_text").val("");
 					$(this).dialog("close");
 				}
 			}
 		});
	}
	return ff;
}

// 展开详细信息 收回详细信息
function expandDiv(obj,o,url,hei) {
	var divid="#expand"+obj;
	var expand = $(divid);
	if (expand.is(":hidden")) {
		$(divid +" td").html("");
		$(divid +" td").append("<iframe frameborder='0' scrolling='yes' width='100%' height='"+hei+"' src='"+url+"="+obj+"'></iframe>");
//		$(divid +" td").append($("#expand_content").html());
		expand.show("slow");
//		$("#expand_content").html("");
		o.src = "images/finder_up_arrow.gif";
	} else {
		o.src = "images/finder_drop_arrow.gif";
		$(divid +" td").html("");
		expand.hide();
	}
}


/*改变每页显示的行数*/
function changePageShowC(url,totalPages){
	  var size=$("#list_size").val();
	  var pageno=$("#list_pageno").val();
	  if(pageno>totalPages){
		  pageno=totalPages;
	  }
	  pageno=(pageno==0?1:pageno);
	  if(isNaN(pageno)|isNaN(size)|size<=0|pageno<=0|pageno.indexOf(".") != -1 |size.indexOf(".") != -1){
		  $("#dialog-err-tip").dialog({
				resizable : false,
				height : 150,
				width : 270,
				modal : true,
				autoOpen : true,
				buttons : {
					"确定" : function() {
						$(this).dialog("close");
					}
				}
		});
	  }else{
		  test(url, pageno, size);
	  }
}

//点击a实现分页
function test(url,pageNo,pageSize){
	$('#pageFoot').attr('action', url);
	$("#pageSize").val("");
	$("#pageNo").val("");
	$("#pageSize").val(pageSize);
	$("#pageNo").val(pageNo);
	selectOption();
	$('#advanced_from').submit();
}

function selectOption(){
	//会员
	$("#mobilet option[value='"+$("#mobileis").val()+"']").prop("selected","selected");
	$("#emailt option[value='"+$("#mailis").val()+"']").prop("selected","selected");
	$("#isFlagt option[value='"+$("#isflags").val()+"']").prop("selected","selected");
	//评论
	$("#isFlagt option[value='"+$("#isDisplays").val()+"']").prop("selected","selected");
}
// 显示高级搜索
function showadvancedsearch(t) {
	var advs = $(".sp_search");
	if (advs.is(":hidden")) {
		$(t).find("span").text("关闭搜索");
		selectOption();
		advs.show();
		advs.animate({
			right: 0
		},300);
	} else {
		$(t).find("span").text("高级搜索");
		advs.fadeOut("fast",function(){
			advs.css("right",-(advs.width()+42));
		});
	}
	$(document).click(function(event){
		if($(event.target).isChildAndSelfOf($(".sp_search, .sub"))) {
			
		} else {
			$(".sp_search").fadeOut("fast",function(){
				$(t).find("span").text("高级搜索");
				$(".sp_search").css("right",-($(".sp_search").width()+42));
			});
		};
	});
}

//检查是否选中一行
function checkSelected(objId,modifyFlag){
	checkedList = new Array();
  	$("input[name='"+objId+"']:checked").each(function() {
  		checkedList.push($(this).val());
  	});
  	if(modifyFlag!=0){
  		if(checkedList.length ==1){
  	  		return true;
  	  	}else{
  	  		return false;
  	  	}
  	}
  	
  	if(checkedList.length > 0){
  		return true;
  	}else{
  		return false;
  	}
}; 

/**
 * 批量删除 通用
 * @param url 			删除路径
 * @param initUrl   	成功跳转路径
 */
function delMultiWin(url,initUrl) {
      $.post(url,{parameterIds:checkedList},function(result){  
	      if (result > 0){
	          location.href=initUrl;
	      } else { 
	    	  
          }
      },'json');
}

function jumpIndex(){
	window.parent.location.href='index.htm';
}

function fillPandL(){
	//填充会员等级
	$.post("getPointLevel.htm?CSRFToken="+$("#hi_token").val(),function(data){
		var options = '';
		for( var i=0; i<data.length; i++){
			var pointLevel=data[i];
			options +=  "<option value='"+pointLevel.pointLelvelId+"'>"+pointLevel.pointLevelName+"</option>";
		}
		$('#pointLevel').html(options);
	});
	//填充省份
	$.post("getAllProvince.htm?CSRFToken="+$("#hi_token").val(),function(data){
		var options = "<option value='' >"+"请选择"+"</option>";
		for( var i=0; i<data.length; i++){
			var province=data[i];
			options +=  "<option value='"+province.provinceId+"'>"+province.provinceName+"</option>";
		}
		$("select[id^='province']").html(options);
	});
}
isExist=false;
/**
 * propName 属性名
 * value 	属性值
 * url 		查询路径
 * tipStr	提示内容
 */
function checkExist(propName,value,url,tipStr){
	if(value!=""){
//		$.post(url+"?"+propName+"="+value,function(result){  
//		      if (result > 0){
//		    	  $("#isExist").val("false");
//		    	  $("#"+propName).addClass("ui-state-error");
//		    	  $("."+propName+"tip").text(tipStr+"已存在").addClass("ui-state-highlight");
//		    	  $("."+propName+"tip")(function() {
//						$("#"+propName).removeClass("ui-state-highlight");
//				    }, 500);
//		      } else {
//		    	  $("#isExist").val("true");
//		    	  $("."+propName+"tip").text(tipStr+"可用").addClass("ui-state-highlight").addClass("property_useable");
//		    	  $("#"+propName).removeClass("ui-state-error");
//	          }
//	      },'json');
		url=url+"?CSRFToken="+$("#hi_token").val()+"&"+propName+"="+value;
		var aa="";
		$.ajax({
	         type: 'post',
	         url:url,
	         async:false,
	         success: function(data) {
	        	 if (data > 0){
			    	  $("#"+propName).addClass("ui-state-error");
			    	  $("."+propName+"tip").text(tipStr+"已存在").addClass("ui-state-highlight");
//			    	  $("."+propName+"tip")(function() {
//							$("#"+propName).removeClass("ui-state-highlight");
//					    }, 500);
			    	  aa="1";
			      } else {
			    	  $("."+propName+"tip").text(tipStr+"可用").addClass("ui-state-highlight").addClass("property_useable");
			    	  $("#"+propName).removeClass("ui-state-error");
			    	  aa="2";
		          }
	         }
		 });
		if(aa=="1"){
			return false;
		}else{
			return true;
		}
	}
	
	 return true;
	
}


$(function() {
	$("#spinner").spinner({
		spin : function(event, ui) {
			if (ui.value > 1) {
				$(this).spinner("value", 1);
				return false;
			} else if (ui.value < 0) {
				$(this).spinner("value", 0);
				return false;
			}
		},
		step: 0.01,
	    numberFormat: "n"
	});
	
	/**
	 * 检查用户名是否存在
	 */
	$("#customerUsername").blur(function(){
		if($(this).val()!=""){
			if(checkLength($("#customerUsername"), "用户名", 4, 16, $(".customerUsernametip"))){
				if(checkRegexp(username, /^[a-zA-Z]([0-9a-z_])+$/i, "用户名必须以字母开头,可包含a-z, 0-9,下划线", usernametip)){
					if(modified!=1){
						checkExist("customerUsername",$("#customerUsername").val(),"checkExistCustomerUsername.htm","用户名");
					}
				}
			}
		}
	});
	
	// 添加会员
	$("#create-user").button().click(function() {
		modified = 0;
		$('#customerUsername').removeAttr("readonly");
		$('#addForm').attr('action', 'addCustomer.htm?CSRFToken='+$("#hi_token").val());
		fillPandL();
		$("#dialog-form").dialog("open").dialog("option", { title : "添加会员" });
	});
	
	/**
	 * 省份改变时 触发此事件 开始加载下一级 --城市 select[id^='province']
	 */
	$("#province").change(function(){
		$.post("getAllCityByPid.htm?CSRFToken="+$("#hi_token").val()+"&provinceId="+$(this).val(),function(data){
				if(data.length!=0){
					var options = "<option value='' >"+"请选择"+"</option>";
					for( var i=0; i<data.length; i++){
						var city=data[i];
						options +=  "<option value='"+city.cityId+"'>"+city.cityName+"</option>";
					}
					$('#city').html(options);
					$("#city").show();
				}else{
					$("#city").html("");
					$("#district").html("");
					$("#street").html("");
					$("#city").hide();
					$("#district").hide();
					$("#street").hide();
				}
		});
		
	});
	$("#province_a").change(function(){
		$.post("getAllCityByPid.htm?provinceId="+$(this).val(),function(data){
			if(data.length!=0){
				var options = "<option value='' >"+"请选择"+"</option>";
				for( var i=0; i<data.length; i++){
					var city=data[i];
					options +=  "<option value='"+city.cityId+"'>"+city.cityName+"</option>";
				}
				$('#city_a').html(options);
				$("#city_a").show();
			}else{
				$("#city_a").html("");
				$("#district_a").html("");
				$("#city_a").hide();
				$("#district_a").hide();
			}
		});
		
	});
	
	/**
	 * 城市改变时 触发此事件 开始加载下一级 --区县
	 */
	$("#city").change(function(){
		$.post("getAllDistrictByCid.htm?CSRFToken="+$("#hi_token").val()+"&cityId="+$(this).val(),function(data){
			if(data.length!=0){
				var options = "<option value='' >"+"请选择"+"</option>";
				for( var i=0; i<data.length; i++){
					var district=data[i];
					options +=  "<option value='"+district.districtId+"'>"+district.districtName+"</option>";
				}
				$('#district').html(options);
				$("#district").show();
			}else{
				$("#district").html("");
				$("#district").hide();
			}
		});
		
	});
	$("#city_a").change(function(){
		$.post("getAllDistrictByCid.htm?cityId="+$(this).val(),function(data){
			if(data.length!=0){
				var options = "<option value='' >"+"请选择"+"</option>";
				for( var i=0; i<data.length; i++){
					var district=data[i];
					options +=  "<option value='"+district.districtId+"'>"+district.districtName+"</option>";
				}
				$('#district_a').html(options);
				$("#district_a").show();
			}else{
				$("#district_a").html("");
				$("#district_a").hide();
			}
		});
		
	});
	
	/**
	 * 区县改变时 触发此事件 开始加载下一级 --街道
	 */
	$("#district").change(function(){
		$.post("getAllStreetByDid.htm?CSRFToken="+$("#hi_token").val()+"&dId="+$(this).val(),function(data){
			if(data.length!=0){
				var options = "<option value='' >"+"请选择"+"</option>";
				for( var i=0; i<data.length; i++){
					var street=data[i];
					options +=  "<option value='"+street.streetId+"'>"+street.streetName+"</option>";
				}
				$('#street').html(options);
				$("#street").show();
			}else{
				$("#street").html("");
				$("#street").hide();
			}
		});
		
	});
	$("#search_adv").button({
	      icons: {
		        primary: "ui-icon-search",//ui-icon-zoomout
		      }
		}).click(
			function (){
				$("#advanced_from").attr("action","queryByCustomerAllInfo.htm?showFlag=1&attr="+$('.search_sel span').text()+"&attr="+$(".search_text").val());
				$("#pageSize").val(15);
				$("#pageNo").val(1);
				$("#advanced_from").submit();
			}
	);
	
	$("#search_comm").button().click(
			function (){
				$("#advanced_from").attr("action","queryByComment.htm?attr=1");
				$("#pageSize").val(15);
				$("#pageNo").val(1);
				$("#advanced_from").submit();
			}
	);
	$("#search_share").button().click(
			function (){
				$("#advanced_from").attr("action","initsharelist.htm?attr=1");
				$("#pageSize").val(15);
				$("#pageNo").val(1);
				$("#advanced_from").submit();
			}
	);
	
	$("#search_con").button({
	      icons: {
		        primary: "ui-icon-search",//ui-icon-zoomout
		      }
		}).click(
			function (){
				if(checkInputContent()){
					$("#search_from").attr("action","queryByConsult.htm?attr=0&attr="+$(".search_sel span").text()+"&attr="+$(".search_text").val());
					$("#pageSize").val(15);
					$("#pageNo").val(1);
					$("#search_from").submit();
					//$(".advancedsearch").show();
				}
			}
	);
	
	$("#search_consult").button().click(
			function (){
				$("#advanced_from").attr("action","queryByConsult.htm?attr=1&attr="+$(".search_sel span").text()+"&attr="+$(".search_text").val());
				$("#pageSize").val(15);
				$("#pageNo").val(1);
				$("#advanced_from").submit();
			}
	);
	
	//搜索选择
	$(".search_sel span").text($(".search_sel select").find("option:selected").text());
	$(".search_sel select").change(function() {
		var val = $(this).find("option:selected").text();
		/** 会员 **/
		if(val=="用户名"){
			$(".search_text").attr("name","customerUsername");
		}else if(val=="真实姓名"){
			$(".search_text").attr("name","infoRealname");
		}else if(val=="手机号码"){
			$(".search_text").attr("name","infoMobile");
		}
		/** 评论 **/
		else if(val=="发表人昵称"){
			if($("#share_hd").val()){
				$(".search_text").attr("name","customerName");
			}else{
				$(".search_text").attr("name","customerNickname");
			}
			
		}else if(val=="商品名称"){
			$(".search_text").attr("name","goodsName");
		}else if(val=="权限名称"){
			$(".search_text").attr("name","designation");
		}else if(val=="管理员用户名"){
			$(".search_text").attr("name","username");
		}
		else if(val=="公司名称"){
			$(".search_text").attr("name","companyName");
		}
		/** 咨询 **/
		$(".search_sel span").text(val);
	});
	
	var pointLevelName=$("#pointLevelName"),
	pointLevelNametip=$(".pointLevelNametip"),
	pointDiscount=$("#spinner"),
	pointDiscounttip=$(".pointDiscounttip"),
	pointNeed=$("#pointNeed"),
	pointNeed1=$("#pointNeed1"),
	pointNeedtip=$(".pointNeedtip"),
	isDefaulttip=$("#isDefaulttip"),
	allFieldss=$([]).add(pointLevelName).add(pointDiscount).add(pointNeed).add(pointNeed1).add(pointDiscounttip)
		.add(pointNeedtip).add(pointLevelNametip).add(isDefaulttip),
	alltipss=$([]).add(pointDiscounttip).add(pointNeedtip).add(pointLevelNametip).add(isDefaulttip);
	//添加会员等级会话框属性设置
	$("#dialog_level_form").dialog(
	{
		autoOpen : false,
		height : 327,
		width : 950,
		modal : true,
		buttons : {
			"确定" : function() {
					alltips.text("").removeClass("ui-state-error");
					alltips.removeClass("ui-state-highlight");
					var bValid = true;
					if($("#pointLevelName").val()==""){
						bValid=false;
						$("#pointLevelName").addClass("ui-state-error");
						updateTips("名称不能为空", $(".pointLevelNametip"));
					} if(!checkLength($("#pointLevelName"), "等级名称", 4, 16, $(".pointLevelNametip"))){
						bValid=false;
					} if(!checkRegexp($("#pointLevelName"), /^([\u4e00-\u9fa5_]+|([A-Za-z]+)+)$/, "等级名称必须为纯中文或者纯英文", $(".pointLevelNametip"))){
						bValid=false;
					} 
					if(modified!=1&&bValid==true){
//						if($("#isExist").val()=="false"){
//							bValid=false;
//							updateTips("等级名称已存在", $(".pointLevelNametip"));
//						}
						bValid=checkExist("pointLevelName",$("#pointLevelName").val(),"checkExistPointLevelName.htm","等级名称");
					} 
					if($("#spinner").val()==""||isNaN($("#spinner").val())||0>$("#spinner").val()||$("#spinner").val()>1){
						bValid=false;
						$("#spinner").addClass("ui-state-error");
						updateTips("非空,必须为数字,范围0~1", $(".pointDiscounttip"));
					}else{
						$("#spinner").removeClass("ui-state-error");
					}
//					if($("#isdefault").val()==1){
//						bValid=false;
//						updateTips("已存在默认值", $("#isDefaulttip"));
//					} 
					if($("#pointNeed").val()==""||isNaN($("#pointNeed").val())||0>$("#pointNeed").val()||$("#pointNeed").val()>1000000){
						bValid=false;
						$("#pointNeed").addClass("ui-state-error");
						updateTips("非空,必须为数字,在0~1000000", $(".pointNeedtip"));
					}else{
						$("#pointNeed").removeClass("ui-state-error");
					}
					if($("#pointNeed1").val()==""||isNaN($("#pointNeed1").val())||parseFloat($("#pointNeed").val()) >= parseFloat($("#pointNeed1").val())||$("#pointNeed1").val()>1000000){
						bValid=false;
						$("#pointNeed1").addClass("ui-state-error");
						updateTips("非空,必须为数字,在0~1000000,且第二个数值不能小于第一个", $(".pointNeedtip"));
					}else{
						$("#pointNeed1").removeClass("ui-state-error");
					}
					
//					bValid =  checkLength($("#pointLevelName"), "等级名称", 4, 16, $(".pointLevelNametip")) && bValid;
//					bValid =  bValid && checkRegexp($("#pointLevelName"), /^([\u4e00-\u9fa5_]+|([A-Za-z0-9]+)+)$/, "等级名称必须为纯中文或者纯英文", $(".pointLevelNametip"))
//					
//					if($("#spinner").val()==""||isNaN($("#spinner").val())||0>$("#spinner").val()||$("#spinner").val()>1){
//						bValid=false;
//						updateTips("非空,必须为数字,范围0~1", $(".pointDiscounttip"));
//					}if($("#isdefault").val()==1){
//						bValid=false;
//						updateTips("已存在默认值", $("#isDefaulttip"));
//					}
//					
//					bValid =  checkLength(email, "邮箱", 6, 80,emailtip) && bValid;
//					bValid =  bValid &&  checkRegexp( email, /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/i,"请输入格式正确的Email,如 eg.ui@jquery.com",emailtip) ;
//					bValid =  checkLength(password, "密码", 6, 16, passwordtip) && bValid;
//					bValid =  chackPassword(password,repassword,repasswordtip) && bValid;
//					bValid =  bValid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9", passwordtip) ;
					if (bValid) {
						$('#add_levle_from').submit();
						$(this).dialog("close");
					}
			},
			'取消' : function() {
				allFieldss.text("").removeClass("ui-state-error");
				$('#isDefaultNo').prop('checked', true);
				$(this).dialog("close");
			}
		},
		close : function() {
			allFieldss.val("").removeClass("ui-state-error");
			allFieldss.removeClass("ui-state-highlight");
			alltipss.text("").removeClass("ui-state-highlight");
			$('#isDefaultNo').prop('checked', true);
			$("#spinner").val(1);
//			pointLevelName.val("");
//			pointNeed.val("");
//			pointNeed1.val("");
			modified=0;
		}
	});
	/*ajax 通过ID查询并塞值到会员等级修改页面*/
	function doSearchPointLevel(pointLevelId){
		$.post("queryPointLevelById.htm?CSRFToken="+$("#hi_token").val()+"&pointLevelId="+pointLevelId,function(data){
			 	 $("#pointLevelName").val(data.pointLevelName);
			 	 $("#pointLevelName").append("<input type='hidden' name='pointLelvelId' value='"+data.pointLelvelId+"'  />");
			 	 $("#spinner").val(data.pointDiscount);
			 	 if(data.isDefault==0){
			 		$('#isDefaultNo').prop('checked', true);
			 	 }else{
			 		$('#isDefaultYes').prop('checked', true);
			 	 }
			 	 $("#is_d_hide").val(data.isDefault);
			 	 $("#pointNeed").val(data.pointNeed.split("~")[0]);
			 	 $("#pointNeed1").val(data.pointNeed.split("~")[1]);
				 $("#dialog_level_form").dialog("open").dialog("option", { title : "编辑会员等级" });
		});
	}
	$("tr").click(function(){
			var cb=$(this).find(":checkbox");
			cb.prop("checked",!cb.prop("checked"));
			if(cb.prop("checked")){
				$(this).addClass("trbcak");
			}else{
				$(this).removeClass("trbcak");
			}
		}
	);
	
	$("input[type='checkbox']").click(function(){
		var cr = $(this);
		if(cr.parent()[0].tagName=="TD"){
			cr.prop("checked",!cr.prop("checked"));
			}				
		}
	);
	
	/** 会员等级 **/
	
	/**
	 * 检查等级名称是否存在
	 */
	$("#pointLevelName").blur(function(){
		if($("#pointLevelName").val()!=""){
			if(checkLength($("#pointLevelName"), "等级名称", 4, 16, $(".pointLevelNametip"))){
				if(checkRegexp($("#pointLevelName"), /^([\u4e00-\u9fa5_]+|([A-Za-z]+)+)$/, "等级名称必须为纯中文或者纯英文", $(".pointLevelNametip"))){
					if(modified!=1){
						checkExist("pointLevelName",$("#pointLevelName").val(),"checkExistPointLevelName.htm","等级名称");
					}
				}
			}
		}
	});
	
	/**
	 * 检查会员等级默认
	 */
	$("#isDefaultYes").click(
		function(){
			if($("#is_d_hide").val() != "1"){
				$.post("checkExistDefaultPointLevel.htm",function(result){  
					if(result>0){
						$("#isdefault").val(1);
						updateTips("已存在默认值,确认保存将取消之前默认设置", $("#isDefaulttip"));
					}
				});
			}
		}
	 );
	
	/**
	 * 检查会员等级默认
	 */
	$("#isDefaultNo").click(
		function(){
			$("#isdefault").val(0);
			$("#isDefaulttip").text('').removeClass("ui-state-highlight");
		}
	);
	
	// 添加会员等级
	$("#create_level").button().click(function() {
		$('#add_levle_from').attr('action', 'addPointLevel.htm?CSRFToken='+$("#hi_token").val());
		$('#pointLevelName').removeAttr('disabled');
		$("#dialog_level_form").dialog("open").dialog("option", { title : "添加会员等级" });
	});
	
	// 编辑会员等级
	$("#modified_level").button().click(function() {
		modified=1;
		if(checkSelected('pointLevelid',1)){
			$('#add_levle_from').attr('action', 'updatePointLevel.htm?CSRFToken='+$("#hi_token").val());
			$('#pointLevelName').prop('disabled', 'disabled');
			doSearchPointLevel(($("input[name='pointLevelid']:checked").val()));
		}else{
			 $("#dialog-tip").dialog({
	     			resizable : false,
	     			height : 150,
	     			width : 270,
	     			modal : true,
	     			autoOpen : true,
	     			buttons : {
	     				"确定" : function() {
	     					$(this).dialog("close");
	     				}
	     			}
	     	});
		}
	});
	
	//删除会员等级提示框设置
	$("#dialog-level-confirm").dialog({
		resizable : false,
		height : 150,
		width : 270,
		modal : true,
		autoOpen : false,
		buttons : {
			"确定" : function() {
				delMultiWin('deletePointLevel.htm?CSRFToken='+$("#hi_token").val(),'initPointLevel.htm ');
				$(this).dialog("close");
			},
			'取消' : function() {
				$(this).dialog("close");
			}
		}
	});
	// 删除会员等级
	$("#del_level").button().click(function() {
		if(checkSelected('pointLevelid',1)){
			$("#dialog-level-confirm").dialog("open");
		}else{
			 $("#dialog-tip").dialog({
	     			resizable : false,
	     			height : 150,
	     			width : 270,
	     			modal : true,
	     			autoOpen : true,
	     			buttons : {
	     				"确定" : function() {
	     					$(this).dialog("close");
	     				}
	     			}
	     		});
		}
	});
	/**2013年12月21日15:03:39**/
	
	
	//删除会员评论提示框设置
	$("#dialog-comment-confirm").dialog({
		resizable : false,
		height : 150,
		width : 270,
		modal : true,
		autoOpen : false,
		buttons : {
			"确定" : function() {
				delMultiWin('deleteComment.htm?CSRFToken='+$("#hi_token").val(),'initComment.htm ');
				$(this).dialog("close");
			},
			'取消' : function() {
				$(this).dialog("close");
			}
		}
	});
	//删除会员晒单提示框设置
	$("#dialog-share-confirm").dialog({
		resizable : false,
		height : 150,
		width : 270,
		modal : true,
		autoOpen : false,
		buttons : {
			"确定" : function() {
				delMultiWin('deleteshare.htm?CSRFToken='+$("#hi_token").val(),'initsharelist.htm ');
				$(this).dialog("close");
			},
			'取消' : function() {
				$(this).dialog("close");
			}
		}
	});
	//删除会员晒单提示框设置
	$("#dialog-index-confirm").dialog({
		resizable : false,
		height : 150,
		width : 270,
		modal : true,
		autoOpen : false,
		buttons : {
			"确定" : function() {
				delMultiWin('updatesharetoindex.htm?CSRFToken='+$("#hi_token").val(),'initsharelist.htm ');
				$(this).dialog("close");
			},
			'取消' : function() {
				$(this).dialog("close");
			}
		}
	});
	//删除会员晒单提示框设置
	$("#dialog-err-confirm").dialog({
		resizable : false,
		height : 150,
		width : 270,
		modal : true,
		autoOpen : false,
		buttons : {
			"确定" : function() {
				$(this).dialog("close");
			}
		}
	});
	//删除会员评论提示框设置
	$("#dialog-consult-confirm").dialog({
		resizable : false,
		height : 150,
		width : 270,
		modal : true,
		autoOpen : false,
		buttons : {
			"确定" : function() {
				delMultiWin('deleteComment.htm?CSRFToken='+$("#hi_token").val(),'initConsult.htm ');
				$(this).dialog("close");
			},
			'取消' : function() {
				$(this).dialog("close");
			}
		}
	});
	
	// 删除评论 单
	$("button[id='del-comm']").button().click(function() {
		if(checkSelected('commentid',1)){
			$("#dialog-comment-confirm").dialog("open");
		}else{
			 $("#dialog-tip").dialog({
	     			resizable : false,
	     			height : 150,
	     			width : 270,
	     			modal : true,
	     			autoOpen : true,
	     			buttons : {
	     				"确定" : function() {
	     					$(this).dialog("close");
	     				}
	     			}
	     		});
		}
	});
	//删除评论 批量
	$("button[id='del-commMu']").button().click(function() {
		if(checkSelected('commentid',0)){
			$("#dialog-comment-confirm").dialog("open");
		}else{
			 $("#dialog-tip").dialog({
	     			resizable : false,
	     			height : 150,
	     			width : 270,
	     			modal : true,
	     			autoOpen : true,
	     			buttons : {
	     				"确定" : function() {
	     					$(this).dialog("close");
	     				}
	     			}
	     		});
		}
	});
	
	// 删除晒单 单
	$("button[id='del-share']").button().click(function() {
		if(checkSelected('shareId',1)){
			$("#dialog-share-confirm").dialog("open");
		}else{
			 $("#dialog-tip").dialog({
	     			resizable : false,
	     			height : 150,
	     			width : 270,
	     			modal : true,
	     			autoOpen : true,
	     			buttons : {
	     				"确定" : function() {
	     					$(this).dialog("close");
	     				}
	     			}
	     		});
		}
	});
	//删除晒单 批量
	$("button[id='del-shareMu']").button().click(function() {
		if(checkSelected('shareId',0)){
			$("#dialog-share-confirm").dialog("open");
		}else{
			 $("#dialog-tip").dialog({
	     			resizable : false,
	     			height : 150,
	     			width : 270,
	     			modal : true,
	     			autoOpen : true,
	     			buttons : {
	     				"确定" : function() {
	     					$(this).dialog("close");
	     				}
	     			}
	     		});
		}
	});
	//推荐到首页 批量
	$("button[id='btn-toIndex']").button().click(function() {
		if(checkSelected('shareId',0)){
			var url = 'checkindexsharecount.htm?count='+checkedList.length,iFlag =false;
			$.ajax({
		         type: 'post',
		         url:url,
		         async:false,
		         success: function(data) {
		        	 if (data <= 0){
		        		 $("#dialog-err-confirm").dialog("open");
		        		 iFlag =true;
				      }
		         }
			 });
			if(!iFlag){
				$("#dialog-index-confirm").dialog("open");
			}
		}else{
			$("#dialog-tip").dialog({
				resizable : false,
				height : 150,
				width : 270,
				modal : true,
				autoOpen : true,
				buttons : {
					"确定" : function() {
						$(this).dialog("close");
					}
				}
			});
		}
	});
	
	// 删除咨询 单
	$("button[id='del-consult']").button().click(function() {
		if(checkSelected('commentid',1)){
			$("#dialog-consult-confirm").dialog("open");
		}else{
			$("#dialog-tip").dialog({
				resizable : false,
				height : 150,
				width : 270,
				modal : true,
				autoOpen : true,
				buttons : {
					"确定" : function() {
						$(this).dialog("close");
					}
				}
			});
		}
	});
	//删除咨询 批量
	$("button[id='del-consultMu']").button().click(function() {
		if(checkSelected('commentid',0)){
			$("#dialog-consult-confirm").dialog("open");
		}else{
			$("#dialog-tip").dialog({
				resizable : false,
				height : 150,
				width : 270,
				modal : true,
				autoOpen : true,
				buttons : {
					"确定" : function() {
						$(this).dialog("close");
					}
				}
			});
		}
	});
	
	//回复评论 咨询
	$("#btn_reply").button().click(
			function(){
				
				$("#comm_rep_from").attr("action", "addCommReplay.htm?CSRFToken="+$("#hi_token").val());
				if($(".cmt_txa").val()!=""){
					$("#comm_rep_from").submit();
				}
			}
	);
	
	//订单查看提示框设置
	$("#dialog_order_form").dialog({
		resizable : false,
		height : 500,
		width : 570,
		modal : true,
		autoOpen : false,
		buttons : {
			"确定" : function() {
				$(this).dialog("close");
			}
		}
	});
	
	$("a[id^='order']").click(function(){
		 $("#dialog_order_form").dialog("open").dialog("option", { title : "订单详情" });
		  $.post("queryAllInfoByOrderId.htm?orderId="+this.id.substr(5),function(data){
			 	 $("#pointLevelName").val(data.pointLevelName);
				
		});
	  });
	
	$("#btn_common").button().click(
			function(){
				$("#common_from").attr("action", "updateInfoSetting.htm");
				$("#common_from").submit();
			}
	);
	
	$("#btn_consult").button().click(
			function(){
				$("#consult_from").attr("action", "updateInfoSetting.htm");
				$("#consult_from").submit();
			}
	);
	
	$("#btn_comment").button().click(
			function(){
				$("#comment_from").attr("action", "updateInfoSetting.htm");
				$("#comment_from").submit();
			}
	);
	/**2013年12月21日15:03:47**/
	var manaFlag = false;
	var username=$("#usernamen"),
	usernametip=$(".usernamentip"),
	userkeyn=$("#userkeyn"),
	userkeyntip=$(".userkeyntip"),
	reuserkey=$("#reuserkey"),
	reuserkeytip=$(".reuserkeytip"),
	allFields=$([]).add(username).add(userkeyn).add(reuserkey).add(usernametip).add(userkeytip).add(reuserkeytip),
	alltips=$([]).add(usernametip).add(userkeyntip).add(reuserkeytip);
	//添加管理员弹出框设置
	$("#dialog-manager").dialog(
	{
				autoOpen : false,
				height : 495,
				width : 683,
				modal : true,
				buttons : {
					"确定" : function() {
							allFields.removeClass("ui-state-error");	
							alltips.text("").removeClass("ui-state-highlight");
							var mobile = $("#mobile").val();
							var reg = /^0?(13|15|18|14)[0-9]{9}$/;
							var bValid = true;
							if(!manaFlag){
								bValid =  checkExist("usernamen",username.val(),"checkmanagerexist.htm","管理员名") && bValid;
								bValid =  bValid && checkLength(username, "管理员名", 4, 16, usernametip) && bValid;
								bValid =  bValid && checkRegexp(username, /^[a-z]([0-9a-z_])+$/i, "用户名必须以字母开头,可包含a-z, 0-9,下划线", usernametip) ;
								bValid =  checkLength(userkeyn, "密码", 6, 16, userkeyntip) && bValid;
								bValid =  chackPassword(userkeyn,reuserkey,reuserkeytip) && bValid;
								bValid =  bValid && checkRegexp( userkeyn, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9", reuserkeytip) ;
							}
							if(mobile != null && mobile.trim().length != 0){
								if(! reg.test(mobile)){
									$(".mobiletip").text("手机号码格式不正确!");
									$(".mobiletip").addClass("ui-state-highlight");
									$("#mobile").addClass("ui-state-error");
									bValid = false;
								}else{
									$(".mobiletip").text("");
									$(".mobiletip").removeClass("ui-state-highlight");
									$("#mobile").removeClass("ui-state-error");
								}
							}
							if (bValid) {
								$('#addForm').submit();
								$(this).dialog("close");
							}
						},
					'取消' : function() {
								$(this).dialog("close");
					}
				},
				close : function() {
							allFields.val("").removeClass("ui-state-error");
							alltips.text("").removeClass("ui-state-highlight");
							$(".mobiletip").text("");
							$(".mobiletip").removeClass("ui-state-highlight");
							$("#mobile").val("").removeClass("ui-state-error");
				}
			});
	
	$("#usernamen").blur(function(){
		if(username.val() != null && username.val().trim().length != 0){
			if(checkLength(username, "管理员名", 4, 16, usernametip) && checkRegexp(username, /^[a-z]([0-9a-z_])+$/i, "用户名必须以字母开头,可包含a-z, 0-9,下划线", usernametip)){
				checkExist("usernamen",username.val(),"checkmanagerexist.htm","管理员名");
			}
		}
	});

	/** 权限-管理员模块 2014年1月9日00:44:57 **/
	// 添加管理员
	$("#create_manager").button().click(function() {
		manaFlag =false;
		$(".p_div").show();
		username.attr("name","username");
		userkeyn.attr("name","userkey");
		username.removeAttr("disabled");
		$('#addForm').attr('action', 'addManager.htm?CSRFToken='+$("#hi_token").val());
		loadAuthority();
		$("#dialog-manager").dialog("open").dialog("option", { title : "添加管理员" });
	});
	//
	$("#medify_manager").button().click(
			function(){
				if(checkSelected("managerid", 1)){
					manaFlag =true;
					$(".p_div").hide();
					username.attr("disabled","disabled");
					username.removeAttr("name");
					userkeyn.removeAttr("name");
					loadAuthority();
					$('#addForm').attr('action', 'updateManager.htm?CSRFToken='+$("#hi_token").val());
					doSearchManager(($("input[name='managerid']:checked").val()));
				}else{
					$("#dialog-tip").dialog({
		     			resizable : false,
		     			height : 150,
		     			width : 270,
		     			modal : true,
		     			autoOpen : true,
		     			buttons : {
		     				"确定" : function() {
		     					$(this).dialog("close");
		     				}
		     			}
		     		});
				}
			}
	);
	
	var versionCode=$("#versionCode"),
	versionTime=$("#versionTime"),
	versionContent=$("#versionContent"),
	versionCodetip=$(".versionCodetip"),
	versionTimetip=$(".versionTimetip"),
	versionContenttip=$(".versionContenttip"),
	fields=$([]).add(versionCode).add(versionCode).add(versionContent),
	tips=$([]).add(versionCodetip).add(versionTimetip).add(versionContenttip);
	$("#dialog-version").dialog(
			{
				autoOpen : false,
				height : 430,
				width : 860,
				modal : true,
				buttons : {
					"确定" : function() {
							fields.removeClass("ui-state-error");
							tips.text("");
							tips.removeClass("ui-state-highlight");
							
							versionTime.removeClass("ui-state-error");
							var bValid = true;
							if(versionCode.val().trim() == ""){
								updateTips("请输入版本号", versionCodetip);
								versionCode.addClass("ui-state-error");
								bValid = false;
							}
							if(versionTime.val().trim() == ""){
								updateTips("请输入更新时间", versionTimetip);
								versionTime.addClass("ui-state-error");
								bValid = false;
							}
							if(versionContent.val().trim() == ""){
								updateTips("请输入版本内容", versionContenttip);
								versionContent.addClass("ui-state-error");
								bValid = false;
							}
							if (bValid) {
								$('#addForm').submit();
								$(this).dialog("close");
							}
						},
					'取消' : function() {
								$(this).dialog("close");
							}
				},
				close : function() {
					versionCode.val("");
					versionTime.val("");
					versionContent.html("");
					$("#versionId").val("");
					fields.text("").removeClass("ui-state-error");
					tips.text("");
					tips.removeClass("ui-state-highlight");
				}
			});
	
	// 添加版本
	$("#create_version").button().click(function() {
		$('#addForm').attr('action', 'addversion.htm?CSRFToken='+$("#hi_token").val());
		$("#dialog-version").dialog("open").dialog("option", { title : "添加版本信息" });
	});
	
	$("#medify_version").button().click(
			function(){
				if(checkSelected("versionId", 1)){
					loadAuthority();
					$('#addForm').attr('action', 'updateversion.htm?CSRFToken='+$("#hi_token").val());
					doSearchVersion(($("input[name='versionId']:checked").val()));
				}else{
					$("#dialog-tip").dialog({
		     			resizable : false,
		     			height : 150,
		     			width : 270,
		     			modal : true,
		     			autoOpen : true,
		     			buttons : {
		     				"确定" : function() {
		     					$(this).dialog("close");
		     				}
		     			}
		     		});
				}
			}
	);
	
	/*ajax 通过ID查询并塞值到管理员修改页面*/
	function doSearchVersion(id){
		$.post("showversion.htm?versionId="+id+'&CSRFToken='+$("#hi_token").val(),function(data){
			versionCode.val(data.versionCode);
			versionTime.val(dateToComm(data.versionTime));
			$("#versionId").val(data.versionId);
			$(".ke-edit-iframe").contents().find(".ke-content").html(data.versionContent);
			$("#dialog-version").dialog("open").dialog("option", { title : "修改版本信息" });
		});
	}
	
	function dateToComm(d){
		 var datetime =new Date(d);
		 var year = datetime.getFullYear();
		 var month = datetime.getMonth()+1;
		 var date = datetime.getDate(); 
		 var hour = datetime.getHours(); 
		 var minutes = datetime.getMinutes(); 
		 var second = datetime.getSeconds();
		 
		 if(month<10){
		  month = "0" + month;
		 }
		 if(date<10){
		  date = "0" + date;
		 }
		 if(hour <10){
		  hour = "0" + hour;
		 }
		 if(minutes <10){
		  minutes = "0" + minutes;
		 }
		 if(second <10){
		  second = "0" + second ;
		 }
		 year = year.toString();
		 var time1 = year+"-"+month+"-"+date+" ";
		 var time2 = hour+":"+minutes+":"+second;//09年06月12日 17时18分
		 return time1+time2; 
		}
	
	//提示框设置
	$("#dialog-manager-confirm").dialog({
		resizable : false,
		height : 170,
		width : 270,
		modal : true,
		autoOpen : false,
		buttons : {
			"确定" : function() {
				delMultiWin('deleteManager.htm?CSRFToken='+$("#hi_token").val(),"initManager.htm");
				$(this).dialog("close");
			},
			'取消' : function() {
				$(this).dialog("close");
			}
		}
	});
	
	
	// 删除管理员 多
	$("#muldel_manager").button().click(function() {
		if(checkSelected('managerid',0)){
			$("#dialog-manager-confirm").dialog("open");
		}else{
			 $("#dialog-tip").dialog({
	     			resizable : false,
	     			height : 150,
	     			width : 270,
	     			modal : true,
	     			autoOpen : true,
	     			buttons : {
	     				"确定" : function() {
	     					$(this).dialog("close");
	     				}
	     			}
	     		});
		}
	});
	
	// 删除管理员 单
	$("#del_manager").button().click(function() {
		if(checkSelected('managerid',1)){
			$("#dialog-manager-confirm").dialog("open");
		}else{
			 $("#dialog-tip").dialog({
	     			resizable : false,
	     			height : 150,
	     			width : 270,
	     			modal : true,
	     			autoOpen : true,
	     			buttons : {
	     				"确定" : function() {
	     					$(this).dialog("close");
	     				}
	     			}
	     		});
		}
	});
	
	/*ajax 通过ID查询并塞值到管理员修改页面*/
	function doSearchManager(id){
		$.post("queryManagerById.htm?id="+id+"&CSRFToken="+$("#hi_token").val(),function(data){
			$.post("queryAuthorByManagerId.htm?mid="+data.id+"&CSRFToken="+$("#hi_token").val(),function(auth){
				$(".username").val(data.username);
				$("#mobile").val(data.mobile);
				$("#photoImg").attr("src",data.photoImg);
			 	 $(".username").append("<input type='hidden' name='id' value='"+data.id+"'  />");
			 	 if(data.flag!=0){
			 		$('#flag1').prop('checked', true);
			 	 }else{
			 		$('#flag2').prop('checked', true);
			 	 }
			 	$("#auth_list_s option[value='"+auth.authorityId+"']").prop("selected","selected");
				$("#dialog-manager").dialog("open").dialog("option", { title : "修改管理员信息" });
			});
		});
	}
	
	/**加载权限列表*/
	function loadAuthority(){
		$.post("queryAllAuthority.htm?CSRFToken="+$("#hi_token").val(),function(data){
			var str = '';
			var options = "";
			for( var i=0; i<data.length; i++){
				var auth=data[i];
//				str +=  "<input type='radio' name='authorityId' value='"+auth.id+"'>"+auth.designation+"</input><br>";
				options +=  "<option value='"+auth.id+"'>"+auth.designation+"</option>";
			}
			$('#auth_list_s').html(options);
		});
	}
	$("#desi").blur(function(){
		if(!authFlag){
			var desi = $("#desi").val();
			var de = $("#desi");
			var detip = $(".desitip");
			if(desi != null && desi.trim().length!=0){
				if(checkRegexp(de, /^([0-9a-zA-Z_\u4e00-\u9fa5]){4,20}$/i, "不能包含特殊字符，长度4-20.", detip)){
					var url = 'checkauthexist.htm?authName='+desi+"&CSRFToken="+$("#hi_token").val();
					$.ajax({
				         type: 'post',
				         url:url,
				         async:false,
				         success: function(data) {
				        	 if (data > 0){
				        		 updateTips("角色已存在", $(".desitip"));
								 $("#desi").addClass("ui-state-error");
				        		 bValid=false;
						      }else{
						    	  $(".desitip").text("").removeClass("ui-state-highlight");
						    	  $("#desi").removeClass("ui-state-error");
						      }
				         }
					 });
					
				}
			}
		}
	});
	
	
	//添加权限弹出框设置
	$("#dialog-authority").dialog({
				autoOpen : false,
				height : 530,
				width : 630,
				modal : true,
				buttons : {
					"确定" : function() {
							var bValid = true;
							var desi = $("#desi").val();
							var de = $("#desi");
							var detip = $(".desitip");
							var catId = $("#catId").val();
							$("#desi").removeClass("ui-state-error");
							detip.text("").removeClass("ui-state-highlight");
							$("#catId").removeClass("ui-state-error");
							$(".catIdtip").text("").removeClass("ui-state-highlight");
							if(!authFlag){
								if(desi != null && desi.trim().length!=0){
									if(checkRegexp(de, /^([0-9a-zA-Z_\u4e00-\u9fa5]){4,20}$/i, "不能包含特殊字符，长度4-20.", detip)){
										var url = 'checkauthexist.htm?authName='+desi+"&CSRFToken="+$("#hi_token").val();
										$.ajax({
									         type: 'post',
									         url:url,
									         async:false,
									         success: function(data) {
									        	 if (data > 0){
									        		 updateTips("角色已存在", $(".desitip"));
													 $("#desi").addClass("ui-state-error");
									        		 bValid=false;
											      }else{
											    	  $(".desitip").text("").removeClass("ui-state-highlight");
											    	  $("#desi").removeClass("ui-state-error");
											      }
									         }
										 });
										
									}else{
										bValid =false;
									}
								}else{
									updateTips("请输入角色名", $(".desitip"));
									$("#desi").addClass("ui-state-error");
									bValid =false;
								}
							}
							if(catId != null && catId.trim().length!=0){
								$(".catIdtip").text("").removeClass("ui-state-highlight");
								$("#catName").removeClass("ui-state-error");
							}else{
								updateTips("请选择权限", $(".catIdtip"));
								$("#catName").addClass("ui-state-error");
								bValid =false;
							}
							if (bValid) {
								$('#addForm').submit();
								$(this).dialog("close");
							}
						},
					'取消' : function() {
								$(this).dialog("close");
							}
				},
				close : function() {
					$("#desi").removeClass("ui-state-error");
					$(".desitip").text("").removeClass("ui-state-highlight");
					$("#catName").removeClass("ui-state-error");
					$(".catIdtip").text("").removeClass("ui-state-highlight");	
				}
	});
	//提示框设置
	$("#dialog-authority-confirm").dialog({
		resizable : false,
		height : 150,
		width : 270,
		modal : true,
		autoOpen : false,
		buttons : {
			"确定" : function() {
				delMultiWin('deleteAuthority.htm?CSRFToken='+$("#hi_token").val(),"initAuthority.htm");
				$(this).dialog("close");
			},
			'取消' : function() {
				$(this).dialog("close");
			}
		}
	});
	// 删除权限 多
	$("#muldel_auth").button().click(function() {
		if(checkSelected('authorid',0)){
			$("#dialog-authority-confirm").dialog("open");
		}else{
			 $("#dialog-tip").dialog({
	     			resizable : false,
	     			height : 150,
	     			width : 270,
	     			modal : true,
	     			autoOpen : true,
	     			buttons : {
	     				"确定" : function() {
	     					$(this).dialog("close");
	     				}
	     			}
	     	 });
		}
	});
	
	// 删除权限 单
	$("#del_auth").button().click(function() {
		if(checkSelected('authorid',1)){
			$("#dialog-authority-confirm").dialog("open");
		}else{
			 $("#dialog-tip").dialog({
	     			resizable : false,
	     			height : 150,
	     			width : 270,
	     			modal : true,
	     			autoOpen : true,
	     			buttons : {
	     				"确定" : function() {
	     					$(this).dialog("close");
	     				}
	     			}
	     		});
		}
	});
	var authFlag = false;
	// 添加权限
	$("#create_auth").button().click(function() {
		aaaaa();
		authFlag = false;
		$('#addForm').attr('action', 'addAuthority.htm?CSRFToken='+$("#hi_token").val());
		$("#dialog-authority").dialog("open").dialog("option", { title : "添加权限" });
	});
	//
	$("#medify_auth").button().click(function(){
		if(checkSelected("authorid", 1)){
			authFlag = true;
			$('#addForm').attr('action', 'updateAuthority.htm?CSRFToken='+$("#hi_token").val());
			doSearchAuthority(($("input[name='authorid']:checked").val()));
		}else{
			$("#dialog-tip").dialog({
		     	resizable : false,
		     	height : 150,
		     	width : 270,
		     	modal : true,
		     	autoOpen : true,
		     	buttons : {
		     		"确定" : function() {
		     			$(this).dialog("close");
		     		 }
		     	}
		    });
		}
	});
	
	/*ajax 通过ID查询并塞值到管理员修改页面*/
	function doSearchAuthority(id){
		$.post("queryAuthorityById.htm?id="+id+"&CSRFToken="+$("#hi_token").val(),function(data){
			$("#desi").val(data.designation);
			$("#desi").append("<input type='hidden' value='"+data.id+"' name='id'></input>");
			bbbbb(data.id);
			$("#dialog-authority").dialog("open").dialog("option", { title : "修改权限" });
		});
	}
	
	
	$("#search_authority").button({
	      icons: {
		        primary: "ui-icon-search",//ui-icon-zoomout
		      }
		}).click(function(){
		if(checkInputContent()){
			$('#search_from').attr('action', 'queryAuthority.htm?attr='+$(".search_sel span").text()+"&attr="+$(".search_text").val()+"&CSRFToken="+$("#hi_token").val());
			$("#pageSize").val(15);
			$("#pageNo").val(1);
			$('#search_from').submit();
		}
	});
	$("#search_manager").button({
	      icons: {
		        primary: "ui-icon-search",//ui-icon-zoomout
		      }
		}).click(function(){
		if(checkInputContent()){
			$('#search_from').attr('action', 'queryByManager.htm?attr='+$(".search_sel span").text()+"&attr="+$(".search_text").val()+"&CSRFToken="+$("#hi_token").val());
			$("#pageSize").val(15);
			$("#pageNo").val(1);
			$('#search_from').submit();
		}
	});
	
	function aaaaa(){
		$.post("queryAllMenu.htm?CSRFToken="+$("#hi_token").val(),function(data){
			  var zNodes=new Array();
			  for(var i=0;i<data.length;i++){
				  var node={
							id:data[i].id,pId:data[i].parentId, name:data[i].designation, open:true
				  };
				  zNodes.push(node);
			  }
			  $.fn.zTree.init($("#spectree"), setting, zNodes);
		  });
		showMenu();
	}
	function bbbbb(authId){
		var ids="";
		$.post("queryAllMenu.htm?CSRFToken="+$("#hi_token").val(),function(data){
			$.post("queryAuthorityByAId.htm?id="+authId+"&CSRFToken="+$("#hi_token").val(),function(menuIds){
				var zNodes=new Array();
				for(var i=0;i<data.length;i++){
					var node={id:data[i].id,pId:data[i].parentId, name:data[i].designation, open:true};
					for(var j=0;j<menuIds.length;j++){
						if(menuIds[j].id==data[i].id){
							ids+=menuIds[j].id+",";
							node.checked=true;
						}
					}
					zNodes.push(node);
				}
				$("#catId").val(ids.substring(0, ids.length-1));
				$.fn.zTree.init($("#spectree"), setting, zNodes);
			});
		});
		showMenu();
	}
	
	//提示框设置
	$("#dialog-point-confirm").dialog({
		resizable : false,
		height : 150,
		width : 270,
		modal : true,
		autoOpen : false,
		buttons : {
			"确定" : function() {
				delMultiWin('deletecustomerpoint.htm?CSRFToken='+$("#hi_token").val(),"querycustpointbycustpoint.htm");
				$(this).dialog("close");
			},
			'取消' : function() {
				$(this).dialog("close");
			}
		}
	});
	
	// 删除会员积分 多
	$("#del-pointbatch").button().click(function() {
		if(checkSelected('pointId',0)){
			$("#dialog-point-confirm").dialog("open");
		}else{
			 $("#dialog-tip").dialog({
	     			resizable : false,
	     			height : 150,
	     			width : 270,
	     			modal : true,
	     			autoOpen : true,
	     			buttons : {
	     				"确定" : function() {
	     					$(this).dialog("close");
	     				}
	     			}
	     		});
		}
	});
	
	// 删除会员积分 单
	$("#del-point").button().click(function() {
		if(checkSelected('pointId',1)){
			$("#dialog-point-confirm").dialog("open");
		}else{
			 $("#dialog-tip").dialog({
	     			resizable : false,
	     			height : 150,
	     			width : 270,
	     			modal : true,
	     			autoOpen : true,
	     			buttons : {
	     				"确定" : function() {
	     					$(this).dialog("close");
	     				}
	     			}
	     		});
		}
	});
	//搜索会员积分记录
	$("#search-point").button({
	      icons: {
		        primary: "ui-icon-search",//ui-icon-zoomout
		      }
		}).click(function(){
		if(checkInputContent()){
			$('#search_from').attr('action', 'querycustpointbycustpoint.htm?attr='+$(".search_sel span").text()+"&attr="+$(".search_text").val());
			$("#pageSize").val(15);
			$("#pageNo").val(1);
			$('#search_from').submit();
		}
	});
	//搜索会员积分记录 高级搜索
	$("#search_pon").button().click(
			function (){
				$("#advanced_from").attr("action","querycustpointbycustpoint.htm?showFlag=1");
				$("#pageSize").val(15);
				$("#pageNo").val(1);
				$("#advanced_from").submit();
			}
	);
	//搜索会员消费 记录
	$("#search-consume").button({
	      icons: {
		        primary: "ui-icon-search",//ui-icon-zoomout
		      }
		}).click(function(){
		if(checkInputContent()){
			$('#search_from').attr('action', 'querycustomerconsume.htm?attr='+$(".search_sel span").text()+"&attr="+$(".search_text").val());
			$("#pageSize").val(15);
			$("#pageNo").val(1);
			$('#search_from').submit();
		}
	});
	//搜索会员消费 记录 高级搜索
	$("#search_consume").button().click(
			function (){
				$("#advanced_from").attr("action","querycustomerconsume.htm?showFlag=1");
				$("#pageSize").val(15);
				$("#pageNo").val(1);
				$("#advanced_from").submit();
			}
	);
	//搜索会员消费 记录
	$("#search-recharge").button({
	      icons: {
		        primary: "ui-icon-search",//ui-icon-zoomout
		      }
		}).click(function(){
		if(checkInputContent()){
			$('#search_from').attr('action', 'initrecharge.htm?attr='+$(".search_sel span").text()+"&attr="+$(".search_text").val());
			$("#pageSize").val(15);
			$("#pageNo").val(1);
			$('#search_from').submit();
		}
	});
	
	
	
	//搜索会员消费 记录 高级搜索
	$("#search_recharge").button().click(
			function (){
				$("#advanced_from").attr("action","initrecharge.htm?showFlag=1");
				$("#pageSize").val(15);
				$("#pageNo").val(1);
				$("#advanced_from").submit();
			}
	);
	
	//消费 提示框设置
	$("#dialog-consume-confirm").dialog({
		resizable : false,
		height : 150,
		width : 270,
		modal : true,
		autoOpen : false,
		buttons : {
			"确定" : function() {
				delMultiWin('deletecustomerconsume.htm?CSRFToken='+$("#hi_token").val(),"querycustomerconsume.htm");
				$(this).dialog("close");
			},
			'取消' : function() {
				$(this).dialog("close");
			}
		}
	});
	//消费 提示框设置
	$("#dialog-recharge-confirm").dialog({
		resizable : false,
		height : 150,
		width : 270,
		modal : true,
		autoOpen : false,
		buttons : {
			"确定" : function() {
				delMultiWin('deletecustomerconsume.htm?CSRFToken='+$("#hi_token").val(),"initrecharge.htm");
				$(this).dialog("close");
			},
			'取消' : function() {
				$(this).dialog("close");
			}
		}
	});
	
	// 删除会员消费  多
	$("#del-consumebatch").button().click(function() {
		if(checkSelected('balanceId',0)){
			$("#dialog-consume-confirm").dialog("open");
		}else{
			 $("#dialog-tip").dialog({
	     			resizable : false,
	     			height : 150,
	     			width : 270,
	     			modal : true,
	     			autoOpen : true,
	     			buttons : {
	     				"确定" : function() {
	     					$(this).dialog("close");
	     				}
	     			}
	     		});
		}
	});
	
	// 删除会员消费 单
	$("#del-consume").button().click(function() {
		if(checkSelected('balanceId',1)){
			$("#dialog-consume-confirm").dialog("open");
		}else{
			 $("#dialog-tip").dialog({
	     			resizable : false,
	     			height : 150,
	     			width : 270,
	     			modal : true,
	     			autoOpen : true,
	     			buttons : {
	     				"确定" : function() {
	     					$(this).dialog("close");
	     				}
	     			}
	     		});
		}
	});
	// 删除会员充值记录  多
	$("#del-rechargebatch").button().click(function() {
		if(checkSelected('balanceId',0)){
			$("#dialog-recharge-confirm").dialog("open");
		}else{
			$("#dialog-tip").dialog({
				resizable : false,
				height : 150,
				width : 270,
				modal : true,
				autoOpen : true,
				buttons : {
					"确定" : function() {
						$(this).dialog("close");
					}
				}
			});
		}
	});
	
	// 删除会员充值记录 单
	$("#del-recharge").button().click(function() {
		if(checkSelected('balanceId',1)){
			$("#dialog-recharge-confirm").dialog("open");
		}else{
			$("#dialog-tip").dialog({
				resizable : false,
				height : 150,
				width : 270,
				modal : true,
				autoOpen : true,
				buttons : {
					"确定" : function() {
						$(this).dialog("close");
					}
				}
			});
		}
	});
	
	//密码私改成功 提示框设置
	$("#dialog-success-tip").dialog({
		resizable : false,
		height : 150,
		width : 270,
		modal : true,
		autoOpen : false,
		buttons : {
			"确定" : function() {
				$(this).dialog("close");
			}
		}
	});
	
	//密码修改失败 提示框设置
	$("#dialog-failure-tip").dialog({
		resizable : false,
		height : 150,
		width : 270,
		modal : true,
		autoOpen : false,
		buttons : {
			"确定" : function() {
				$(this).dialog("close");
			}
		}
	});
	
	var userkey=$("#userkey"),
	userkeytip=$(".userkeytip"),
	newuserkey=$("#newuserkey"),
	newuserkeytip=$(".newuserkeytip"),
	renewuserkey=$("#renewuserkey"),
	renewuserkeytip=$(".renewuserkeytip"),
	allPwFields=$([]).add(userkey).add(renewuserkey).add(newuserkey).add(userkeytip).add(newuserkeytip).add(renewuserkeytip);
//	allPwFields=$([]).add(userkeytip).add(newuserkeytip).add(renewuserkeytip);
	
	//修改密码框设置
	$("#dalog-modify-pw").dialog({
		resizable : false,
		height : 275,
		width : 600,
		modal : true,
		autoOpen : false,
		buttons : {
			"确定" : function() {
				allPwFields.removeClass("ui-state-error");
				allPwFields.removeClass("ui-state-highlight");
				allPwFields.text("").removeClass("ui-state-error");
				allPwFields.removeClass("ui-state-highlight");
				var bValid=true;
				bValid =  checkLength(userkey, "密码", 4, 16, userkeytip) && bValid;
				bValid =  bValid && checkRegexp( userkey, /^([0-9a-zA-Z])+$/, "密码格式错误 : a-z A-Z 0-9",userkeytip) ;
				bValid =  checkLength(newuserkey, "新密码", 6, 16, newuserkeytip) && bValid;
				bValid =  checkRegexp( newuserkey, /^([0-9a-zA-Z])+$/, "密码格式错误 : a-z A-Z 0-9",newuserkeytip) && bValid;
				bValid =  chackPassword(newuserkey,renewuserkey,renewuserkeytip) && bValid;
				if(bValid){
					var url="checkUserKey.htm?userKey="+userkey.val()+"?CSRFToken="+$("#hi_token").val();
					$.ajax({
				         type: 'post',
				         url:url,
				         async:false,
				         success: function(data) {
				        	 if (data > 0){
				        		 updateTips("原始密码正确!",userkeytip);
				        		 bValid=true;
						      } else {
						    	  userkey.addClass("ui-state-error");
						    	  updateTips("原始密码错误!",userkeytip);
						    	  bValid=false;
					          }
				         }
					 });
					if(bValid){
//						$("#mod_pwd_from").submit();
						$(this).dialog("close");
						var url="modifiedUserKey.htm?userKey="+userkey.val()+"&newuserkey="+newuserkey.val()+"&CSRFToken="+$("#hi_token").val();
						$.ajax({
					         type: 'post',
					         url:url,
					         async:false,
					         success: function(data) {
					        	 if (data > 0){
					        		 $("#dialog-success-tip").dialog("open");
					        		 allPwFields.val("");
					 				allPwFields.removeClass("ui-state-error");
					 				allPwFields.removeClass("ui-state-highlight");
					 				allPwFields.text("").removeClass("ui-state-error");
					 				allPwFields.removeClass("ui-state-highlight");
							      } else {
							    	  $("#dialog-failure-tip").dialog("open");
						          }
					         }
						 });
						
					}
					
				}
				
			},
			'取消' : function() {
				allPwFields.val("");
				allPwFields.removeClass("ui-state-error");
				allPwFields.removeClass("ui-state-highlight");
				allPwFields.text("").removeClass("ui-state-error");
				allPwFields.removeClass("ui-state-highlight");
				$(this).dialog("close");
			}
		}
	});
	
	//密码修改失败 提示框设置
	$("#dialog-audit-confirm").dialog({
		resizable : false,
		height : 500,
		width : 500,
		modal : true,
		autoOpen : false,
		buttons : {
			"确定" : function() {
//				delMultiWin('updatestore.htm',"auditlist.htm?checkStatus=0");
				if($("#pay_tb tr").length < 1 || $("#aTime").val().trim().length < 1){
					$("#dialog-tip1").dialog({
						resizable : false,
						height : 150,
						width : 270,
						modal : true,
						autoOpen : true,
						buttons : {
							"确定" : function() {
								$(this).dialog("close");
							}
						}
					});
				}else{
					$("#audit_from").prop("action","updatestore.htm?CSRFToken="+$("#hi_token").val());
					$("#audit_from").append('<input value="'+checkedList+'" type="hidden" name="thirdIds" />');
					$("#audit_from").submit();
					$(this).dialog("close");
				}
				
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		}
	});
	
	// 审核商家
	$("#audit-store").button().click(function() {
		if(checkSelected('storeId',0)){
			 $.ajax({
		         type: 'post',
		         url:'findpayAll.htm?CSRFToken='+$("#hi_token").val(),
		         async:false,
		         success: function(data) {
		        	 var ss = "";
		        	 for(var i = 0 ;i< data.length;i++){
		        		 ss+='<option value="'+data[i].payId+'">'+data[i].payName+'</option>';
		        	 }
		        	 $("#payway").html(ss);
		         }
			 });
			 
			 $("#dialog-audit-confirm").dialog("open");
		}else{
			$("#dialog-tip").dialog({
				resizable : false,
				height : 150,
				width : 270,
				modal : true,
				autoOpen : true,
				buttons : {
					"确定" : function() {
						$(this).dialog("close");
					}
				}
			});
		}
	});
	
	//商家 搜索
	$("#search-audit").button({
	      icons: {
		        primary: "ui-icon-search",//ui-icon-zoomout
		      }
		}).click(function() {
		if(checkInputContent()){
			$('#search_from').attr('action', 'auditlist.htm?checkStatus=0&showFlag=0&attr='+$(".search_sel span").text()+"&attr="+$(".search_text").val());
			$("#pageSize").val(5);
			$("#pageNo").val(1);
			$('#search_from').submit();
		}else{
			
		}
	});
	//商家 搜索
	$("#search-audit1").button({
		icons: {
			primary: "ui-icon-search",//ui-icon-zoomout
		}
	}).click(function() {
		if(checkInputContent()){
			$('#search_from').attr('action', 'auditlist.htm?checkStatus=0&showFlag=0&attr='+$(".search_sel span").text()+"&attr="+$(".search_text").val());
			$("#pageSize").val(5);
			$("#pageNo").val(1);
			$('#search_from').submit();
		}else{
			
		}
	});
	
});

(function($) {
	$.widget("custom.combobox", {
		_create : function() {
		this.wrapper = $("<span>").addClass(
		"custom-combobox").insertAfter(this.element);
							this.element.hide();
							this._createAutocomplete();
							this._createShowAllButton();
		},
		_createAutocomplete : function() {
				var selected = this.element.children(":selected"), value = selected.val() ? selected.text() : "";
				this.input = $("<input>").appendTo(this.wrapper).val(value).attr("title", "")
				.addClass("custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left")
				.autocomplete({delay : 0,minLength : 0,source : $.proxy(this, "_source")
				}).tooltip({
						tooltipClass : "ui-state-highlight"
				});
				this._on(this.input, {
					autocompleteselect : function(event, ui) {
					ui.item.option.selected = true;
					this._trigger("select", event, {
						item : ui.item.option
					});
					},
					autocompletechange : "_removeIfInvalid"
			});
		},
		_createShowAllButton : function() {
				var input = this.input, wasOpen = false;
				$("<a>").attr("tabIndex", -1).attr("title",
					"Show All Items").tooltip().appendTo(
					this.wrapper).button({
					icons : {
							primary : "ui-icon-triangle-1-s"
					},
					text : false
					}).removeClass("ui-corner-all").addClass(
						"custom-combobox-toggle ui-corner-right") .mousedown(
								function() {
									wasOpen = input.autocomplete(
										"widget").is(":visible");
								}).click(function() {
										input.focus();
										if (wasOpen) {
											return;
										}
										input.autocomplete("search", "");
									});
						},
						_source : function(request, response) {
							var matcher = new RegExp($.ui.autocomplete
									.escapeRegex(request.term), "i");
							response(this.element.children("option").map(
									function() {
										var text = $(this).text();
										if (this.value
												&& (!request.term || matcher
														.test(text)))
											return {
												label : text,
												value : text,
												option : this
											};
									}));
						},

						_removeIfInvalid : function(event, ui) {
							if (ui.item) {
								return;
							}
							var value = this.input.val(), valueLowerCase = value
									.toLowerCase(), valid = false;
							this.element
									.children("option")
									.each(
											function() {
												if ($(this).text()
														.toLowerCase() === valueLowerCase) {
													this.selected = valid = true;
													return false;
												}
											});
							if (valid) {
								return;
							}
							this.input.val("").attr("title",
									value + " didn't match any item").tooltip(
									"open");
							this.element.val("");
							this._delay(function() {
								this.input.tooltip("close").attr("title", "");
							}, 2500);
							this.input.data("ui-autocomplete").term = "";
						},

						_destroy : function() {
							this.wrapper.remove();
							this.element.show();
						}
					});
	
})(jQuery);
