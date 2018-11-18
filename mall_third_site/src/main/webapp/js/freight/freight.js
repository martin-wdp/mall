
var chooseNumber="";
var chooseCode="";
var choose_radio="";
var choose_value = 0;
$(function(){
    //alert($('input[id="sc_ct"]').filter(':checked').val());
    //如果进入修改页面的时候 卖家承担运费为选中状态
    if($('input[id="sc_ct"]').filter(':checked').val()==1){
        choose_value = 1;
        //如果进入修改页面的时候 卖家承担运费为选中状态 禁止修改 快递公司下面的所有input框
        $("input[name='logComId']").each(function(){
            var $logComCode = $(this).attr('codename');
            $("input[name='"+$logComCode+"_start']").each(function(){
                $(this).val(1);
                $(this).attr('readonly',true);
            });
            $("input[name='"+$logComCode+"_postage']").each(function(){
                $(this).val(0);
                $(this).attr('readonly',true);
            });
            $("input[name='"+$logComCode+"_plus']").each(function(){
                $(this).val(1);
                $(this).attr('readonly',true);
            });
            $("input[name='"+$logComCode+"_postageplus']").each(function(){
                $(this).val(0);
                $(this).attr('readonly',true);
            });
        });
    }

    $("#savatemp").click(function(){

        loadProvice();
    });
	


    /*添加城市*/
    $(".J_AddRule").click(function(){
    	var s =$("#detail-express"+$(this).attr('logComId')).find(".table tbody tr:last").attr("data-group-value");
    	if(s==undefined){
    		s=0;
    	}
    	var number=parseInt(s)+1;
    	var code = $(this).attr('logCode');
    	var htm='';
    	
    	htm+='<tr data-group="n'+number+'" data-group-value="'+number+'" data-code="'+code+'">';
    	htm+='<td>';
        htm+='<span title="" id="'+code+'_cityName'+number+'">未选择城市</span>';
    	htm+='<a class="edit-cities chooseedit" onclick="editCity(this);" ><i class="glyphicon glyphicon-pencil"></i></a>';
    	htm+='<input class="area-group-values" type="hidden" id="'+code+'_areas_n'+number+'" name="'+code+'_areas" value="" >';
    	htm+='   </td>';
    	htm+=' <td>';
    	htm+='      <input type="text" value="1" name="'+code+'_start" id="'+code+'_start_n'+number+'" style="width:100px;" class="form-control">';
    	htm+='  </td>';
    	htm+='  <td>';
    	htm+='      <input type="text" value=" " name="'+code+'_postage" id="'+code+'_postage_n'+number+'" style="width:100px;" class="form-control">';
    	htm+='  </td>';
    	htm+='  <td>';
    	htm+='       <input type="text" value="1" name="'+code+'_plus" id="'+code+'_plus_n'+number+'" style="width:100px;" class="form-control">';
    	htm+='   </td>';
    	htm+='   <td>';
    	htm+='    <input type="text" value=" " name="'+code+'_postageplus" id="'+code+'_postageplus_n'+number+'" style="width:100px;" class="form-control">';
    	htm+=' </td>';
    	htm+='  <td>';
    	htm+='<button class="btn btn-primary btn-sm areaFeeDelete" type="button">删除</button>';
    	htm+='   </td>';
    	htm+=' </tr>';
        //拼接城市
        $("#detail-express"+$(this).attr('logComId')).find(".table tbody").append(htm);
        if($('input[id="sc_ct"]').filter(':checked').val()==1){
           checkMail(choose_radio,choose_value);
        }


        //删除城市
    	$(".areaFeeDelete").click(function(){
        	$(this).parents('tr').remove();
        	
        });
    });

    /*删除单个城市的运费设置*/
    $(".areaFeeDelete").click(function(){
    	$(this).parents('tr').remove();
    	
    });
    
    $(".caret").click(function(){
    		$(this).parent(".area-select-wrapper").find(".area-select-body").show();
    });
    
});



/**
 * 加载省份
 */
function loadProvice(){
    $.ajax({
        type: 'post',
        url:'getAllProvince.htm',
        async:false,
        success: function(data) {
            var options = "<option value='' >"+"请选择"+"</option>";
            for( var i=0; i<data.length; i++){
                var province=data[i];
                options +=  "<option value='"+province.provinceId+"'>"+province.provinceName+"</option>";
            }
            $("#province").html(options);
        }
    });
    loadCity($("#province").val());
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
                $("#city").html(options);
            }else{
                $("#city").html("<option value='' >"+"请选择"+"</option>");
                $("#district").html("<option value='' >"+"请选择"+"</option>");
                /*$("#street").html("<option value='' >"+"请选择"+"</option>");*/
            }
        }
    });
    loadDistrict($("#city").val());
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
                $('#district').html(options);
            }else{
                $("#district").html("<option value='' >"+"请选择"+"</option>");
            }
        }
    });
}



/*复制模板*/
function copytemp(freightTemplateId){
	window.location.href="copyfreighttemplatethird.html?freightTemplateId="+freightTemplateId;
}  
  
/*删除单个运费模板 弹出框*/
function setDelete(freightTemplateId){
	 $("#freightTemplateId").val(freightTemplateId);
}

//设为默认模板弹出框
function setDefault(freightTemplateId){
	 $("#defaultFreightTemplateId").val(freightTemplateId);
}
/*去修改模板页面*/
function toupdate(freightTemplateId){
	window.location.href="toupdatefreighttemplatethird.html?freightTemplateId="+freightTemplateId;
} 

/*选择单个物流信息 弹出下面的信息*/
function checkchange(obj){
	if($(obj).prop("checked") == true){
        $(".delivery-info-bar").hide();
		$("#detail-express"+$(obj).val()).show();
	}else{
		$("#detail-express"+$(obj).val()).hide();

	}
	
}

function friehgtMethod(obj){
	if(obj.checked==true){
		if($(obj).val()==0){
			$(".methods").each(function(){
				$(this).text($(this).text().replace('g','件'));
			});
			$(".methodslist").each(function(){
				$(this).text($(this).text().replace('重（g）','件（个）'));
			});
			
		}
		if($(obj).val()==1){
			$(".methods").each(function(){
				$(this).text($(this).text().replace('件','g'));
			});
			$(".methodslist").each(function(){
				$(this).text($(this).text().replace('件（个）','重（g）'));
			});
		}
		
	}
}
/*选择城市*/
function editCity(obj){
    $('.city_text').html(''); //清空提示语句
	var $arr = new Array();
	var $val = $(obj).parents("tr").find(".area-group-values").val();
	if($val != undefined) {
		$arr = $val.split(",");		
	};

		var s=$(obj).parents("tr").attr("data-group-value");
		var choosec=$(obj).parents("tr").attr("data-code");
		chooseNumber=s;
		chooseCode=choosec;
		$.ajax({  
	         type: 'post', 
	         url:'selectprovincelist.htm',
	         async:false, 
	         success: function(data) {
	         	   if(data!=null){
                      var htm2= '';
	         		  var s=$(obj).next().val();
                      var arrs=s.split(",");
	         	   		for(var i=0;i<data.length;i++) {
                            var num=0;
                            var htm = '';
                            htm += ' <label class="main-city" onclick="proclick(this);"><input type="checkbox" name="bb'+data[i].provinceId+'"  onchange="checkPro(this);"/>' + data[i].provinceName + '</label>';
                            htm += '<div class="cities">';
                            if(data[i].cityList!=null){
                                for(var j=0;j<data[i].cityList.length;j++){
                                    //拼接城市
                                    htm += '<a href="javascript:;"';
                                    for(var p=0;p<arrs.length;p++){
                                        if(data[i].cityList[j].cityId==arrs[p]){
                                            htm+=' class="checked" ';
                                            num+=1;
                                        }
                                    }
                                    htm += '><input value="'+data[i].cityList[j] .cityId+'" dataname="'+data[i].cityList[j].cityName+'" name="box" type="checkbox" ';
                                    for(var q=0;q<$arr.length;q++) {
                                        if(data[i].cityList[j].cityId == $arr[q]) {
                                            htm+=' disabled = disabled ';
                                        }
                                    }
                                    for(var p=0;p<arrs.length;p++){
                                        if(data[i].cityList[j].cityId==arrs[p]){
                                            htm+=' checked=checked ';
                                        }
                                    }

                                    htm +='/>'+ data[i].cityList[j].cityName +'</a>';
                                }
                                htm += '  </div>';
                            }
                            htm += '  </div>';
                            if(num>0){
                                htm = '<div class="cities-item ch_hot_hover">'+ htm ;
                            }else {
                                htm = '<div class="cities-item">' + htm;
                            }
                            htm2+=htm;
                        }
                         //装载数据
                         $(".cities-box").html(htm2);

                        //弹出选择城市窗体
                         $("#chooseCities").modal({
                        	 backdrop: true
                         });

                       //全选
                       $(".main-city input[type='checkbox']").change(function(){
                           if($(this).prop("checked") == true) {
                               $(this).parent().next(".cities").find("a").addClass("checked");
                               $(this).parent().next(".cities").find("input[name='box']").prop("checked",true);
                           } else {
                               $(this).parent().next(".cities").find("a").removeClass("checked");
                               $(this).parent().next(".cities").find("input[name='box']").prop("checked",false);
                           };
                       });
                       $(".cities").each(function(){
                           var _this = $(this);
                           _this.find("a").click(function(){
                               $(this).toggleClass("checked");
                               if($(this).hasClass("checked")) {
                                   $(this).find("input").prop("checked",true);
                               } else {
                                   $(this).find("input").prop("checked",false);
                               };
                               if(_this.find(".checked").length == _this.find("a").length) {
                                   _this.prev("label").find("input").prop("checked",true);
                               } else {
                                   _this.prev("label").find("input").prop("checked",false);
                               }
                               if($(this).parent().find(".checked").length>0){
                                   $(this).parents(".cities-item").addClass("ch_hot_hover");
                               }else{
                                   $(this).parents(".cities-item").removeClass("ch_hot_hover");
                               }
                           });
                       });



		         	   	/*	$("#chooseCity .ch_hot").each(function(){
		         	   		var $this = $(this);
	         				if($this.find("input[name='box']:checked").length > 0) {
	         					var $num = $this.find("input[name='box']:checked").length;
	         					$this.find(".ct-num").html("("+$num+")");
	         					if ($this.find("input[name='box']:checked").length == $this.find("input[name='box']").length) {
		         					$(this).find(".pro input").prop("checked","checked");
		         				};
	         				};

	         				if($this.find("input[name='box']").attr("disabled") == "disabled" ) {
	         					$this.find(".pro input").attr("disabled","disabled");
	         				};
	         				$this.find("input[name='box']").change(function(){
	         					$num = $this.find(".pro").next("ul").find("input[name='box']:checked").length;
	         					$this.find(".ct-num").html("("+$num+")");
	         					if ($this.find("input[name='box']:checked").length == $this.find("input[name='box']").length) {
		         					$this.find(".pro input").prop("checked","checked");
		         				};
	         				});
	         			});*/
	         	   	}
	        	}
	        });
	}

/*选择城市*/
function selectCity(obj){
			$.ajax({
	         type: 'post',
	         url:'selectcityuseselect.htm?provinceId='+$(obj).val(),
	         async:false,
	         success: function(data) {
	         	var html='<option value="">请选择</option>';
	         	   if(data!=null){
	         	   		for(var i=0;i<data.length;i++){
	         	   				html+='<option value="'+data[i].cityId+'">'+data[i].cityName+'</option>';
	         	   			}
	         	   	}
	         	   	$("#city").html(html);
                    $("#district").html("<option>请选择</option>");
	        	}
	        });
	}
	

	function selectDistrict(obj){
			$.ajax({
	         type: 'post',  
	         url:'selectdistrictlistuserselect.htm?cityId='+$(obj).val(),
	         async:false,
	         success: function(data) {
	         	var html='<option value="">请选择</option>';
	         	   if(data!=null){
	         	   		for(var i=0;i<data.length;i++){
	         	   				html+='<option value="'+data[i].districtId+'">'+data[i].districtName+'</option>';
	         	   			}
	         	   	}
	         	   	$("#district").html(html);
	        	}
	        });
	}
	

function proclick(obj){
	$(obj).parent().addClass('ch_hot_hover');
	//$(obj).parent().siblings().removeClass('ch_hot_hover');
	//$(":checkbox").prop("checked",false);
	
}	
function ch_closeclick(obj){
	
	$(obj).parent().removeClass('ch_hot_hover');
}


function provice(id,name){
	
	
}

/*修改模板信息*/
function subTempForm(){
    var flag=true;
    //清空错误提示信息
    $(".spansendmail").text("")
    $("#province").text("");
    if($.trim($("#freightTemplateName").val())!=""){
        flag=true&&flag;
        $("#freightTemplateName").removeClass("errorinput");
        $("#freightTemplateName").addClass("j_txa");

    }else{
        flag=false&&flag;
        $("#freightTemplateName").removeClass("j_txa");
        $("#freightTemplateName").addClass("errorinput");
        $(".spansendmail").text("请填写模板名称！").css("color","red");
        return;
    }
    //发货地区
    var pro =$('#freightProvinceId').val();
    var city =$('#city').val();
    var district =$('#district').val();
    if(pro =='' || city == '' || district == ''){
        $("#province").text("请填写完整的地区信息！").css("color","red");
        flag=false&&flag;
        return;
    }

    $("#province").text("");
    //判断是否是卖家包邮
    var freightPackageMail  =  $('input[id="sc_ct"]').filter(':checked').val();
    if(freightPackageMail==1){
        $("#errType").text("");
    }else{
        if( $('input[name="logComId"]:checked').length==0){
            flag=false&&flag;
            $("#errType").text("请选择配送方式！").css("color","red");
            return;
        }
        $("#errType").text("");
    }
    $("#errType").text("");
    //判断是否是卖家包邮
    var freightPackageMail  =  $('input[id="sc_ct"]').filter(':checked').val();
    //卖家承担运费不验证未选择城市信息
    if(freightPackageMail != 1){
         $('input[name="logComId"]:checked').each(function(){
            var codename=$(this).attr("codename");
             flag = checkInput($("#"+codename+"_start"))&&flag;
             flag = checkInput($("#"+codename+"_postage"))&&flag;
             flag = checkInput($("#"+codename+"_plus"))&&flag;
             flag = checkInput($("#"+codename+"_postageplus"))&&flag;


             $(this).parents(".radio").next(".test").find("tbody tr").each(function(){
                var cod=$(this).attr("data-code");
                var numbe=$(this).attr("data-group-value");
                if($("#"+cod+"_areas_n"+numbe).val()==""){
                    $("#"+cod+"_cityName"+numbe).text("请选择城市");
                    $("#"+cod+"_cityName"+numbe).css("color","red");
                    flag=false&&flag;
                }else {
                    $("#"+cod+"_cityName"+numbe).css("color","#666");
                };
                flag=checkInput($("#"+cod+"_start_n"+numbe))&&flag;
                flag=checkInput($("#"+cod+"_postage_n"+numbe))&&flag;
                flag=checkInput($("#"+cod+"_plus_n"+numbe))&&flag;
                flag=checkInput($("#"+cod+"_postageplus_n"+numbe))&&flag;
            });

          });
     }
	 if(flag){
		 $("#freightFormUpdate").submit();
	 }

}

function comeback(){
	window.location.href="freighttemplatelist.htm?CSRFToken="+$("#token").val();
}



var num=0;
//添加模板
function addTempForm(){
    var flag=true;
    //清空错误提示信息
    $(".spansendmail").text("") //模板名称提示语句
    $("#province").text("");    //区域提示语句

    //模板名称
    if($.trim($("#freightTemplateName").val())!=""){
        flag=true&&flag;
        $("#freightTemplateName").removeClass("errorinput");
        $("#freightTemplateName").addClass("j_txa");

    }else{
        flag=false&&flag;
        $("#freightTemplateName").removeClass("j_txa");
        $("#freightTemplateName").addClass("errorinput");
        $(".spansendmail").text("请填写模板名称！").css("color","red");
        return;
    }

    //发货地区
    var pro =$('#freightProvinceId').val();
    var city =$('#city').val();
    var district =$('#district').val();
    if(pro =='' || city == '' || district == ''){
        $("#province").text("请填写完整的地区信息！").css("color","red");
        flag=false&&flag;
        return;
    }
    $("#province").text("");

    if( $('input[name="logComId"]:checked').length==0){
        flag=false&&flag;
        $("#errType").text("请选择配送方式！").css("color","red");
        return;
    }
    $("#errType").text("");

    //判断是否是卖家包邮
    var freightPackageMail  =  $('input[name="freightPackageMail"]').filter(':checked').val();
    //卖家承担运费不验证未选择城市信息
    if(freightPackageMail != 1){
        //判断是否选中运送方式
        $('input[name="logComId"]:checked').each(function(){
            var codename=$(this).attr("codename");
            flag = checkInput($("#"+codename+"_start"))&&flag;
            flag = checkInput($("#"+codename+"_postage"))&&flag;
            flag = checkInput($("#"+codename+"_plus"))&&flag;
            flag = checkInput($("#"+codename+"_postageplus"))&&flag;
            $(this).parents(".radio").next(".test").find("tbody tr").each(function(){
                var numbe=$(this).attr("data-group-value");
                var cod=$(this).attr("data-code");
                if($("#"+cod+"_areas_n"+numbe).val()==""){
                   $("#"+cod+"_cityName"+numbe).text("请选择城市");
                    $("#"+cod+"_cityName"+numbe).css("color","#cd0a0a");
                    flag=false&&flag;
                }else {
                    $("#"+cod+"_cityName"+numbe).css("color","#666");
                };

                flag=checkInput($("#"+cod+"_start_n"+numbe))&&flag;
                flag=checkInput($("#"+cod+"_postage_n"+numbe))&&flag;
                flag=checkInput($("#"+cod+"_plus_n"+numbe))&&flag;
                flag=checkInput($("#"+cod+"_postageplus_n"+numbe))&&flag;
            });

        });
    }
    if(flag&&num==0){
        num+=1;
        $("#freightForm").submit();
    }



}

/*验证input的值  之前那个样式添加不上 又重新写了个验证*/
function check_Input(obj){
    if($.trim($(obj).val())!="" &&isNaN($(obj).val())!=true){
        $(obj).css('border','');
        return true;
    }else{
        $(obj).css('border','1px solid #cd0a0a');
        return false;
    }
}


function checkInput(obj){
	if($.trim($(obj).val())!="" &&isNaN($(obj).val())!=true){
		$(obj).removeClass("errorinput");
		$(obj).addClass("j_txa");
        $(obj).css('border','');
		return true;
	}else{
		$(obj).removeClass("j_txa");
        $(obj).css('border','1px solid #cd0a0a');
		$(obj).addClass("errorinput");
		return false;
	}
}

/*选择城市 确认按钮*/
function quecity(){

	 var tid="";   
	 var tname = "";
	  $('input[name="box"]:checked').each(function(){
		   tid+=$(this).val()+",";
		   tname+=$(this).attr("dataname")+",";
	  });
      if(tid == '' || tname == ''){
          $('.city_text').html("至少选择一个城市");
          return;
      }
	  var tids=tid.substring(0, (tid.length-1));
	  var tnames=tname.substring(0,(tname.length-1));

      //创建一个数组 用于用于保存分割的城市字符串
      var array = new Array();
      //分割城市字符串
      array = tnames.split(',');
    var t_names='';
    if(array.length>3){
        //只显示前三个城市
        t_names = array[0]+','+array[1]+','+array[2]+'......';
    }else{
        t_names=array[0];
        if(array.length>1){
            for(var i=1;i<array.length;i++){
                t_names+=','+array[i];
            }
        }
    }


      //设置鼠标悬浮要显示 全部的城市链接
      $("#"+chooseCode+"_cityName"+chooseNumber).attr("title",tnames);
      //要显示前三个城市的 数据
	  $("#"+chooseCode+"_cityName"+chooseNumber).text(t_names);
	  $("#"+chooseCode+"_cityName"+chooseNumber).removeClass("errortip");
	  $("#"+chooseCode+"_areas_n"+chooseNumber).val(tids);
      $('.modal').modal('hide')


      $("#"+cod+"_cityName"+numbe).css("color","#666");
}
/*删除单个运费模板*/
function deltemplate(){
	$("#deleteTemplate").submit();
}

/*设置默认模板*/
function defaulttemplate(){
	$("#defaultTemplate").submit();	
}

/*如果卖家承担运费 就不验证配送方式*/
function checkMail(obj,choose){
    //区别 设置未指定的城市 是卖家承担运费 还是买家承担运费
    choose_radio = obj;
    choose_value = choose;
    if($(obj).val()=='0'||choose==0){
        $("input[name='logComId']").each(function(){
            var $logComCode = $(this).attr('codename');
            $("input[name='"+$logComCode+"_start']").each(function(){
                $(this).val(1);
                $(this).attr('readonly',false);
            });
            $("input[name='"+$logComCode+"_postage']").each(function(){
                $(this).val('');
                $(this).attr('readonly',false);
            });
            $("input[name='"+$logComCode+"_plus']").each(function(){
                $(this).val(1);
                $(this).attr('readonly',false);
            });
            $("input[name='"+$logComCode+"_postageplus']").each(function(){
                $(this).val('');
                $(this).attr('readonly',false);
            });
        });
    }else{
        $("input[name='logComId']").each(function(){
            var $logComCode = $(this).attr('codename');
            $("input[name='"+$logComCode+"_start']").each(function(){
                $(this).val(1);
                $(this).attr('readonly',true);
            });
            $("input[name='"+$logComCode+"_postage']").each(function(){
                $(this).val(0);
                $(this).attr('readonly',true);
            });
            $("input[name='"+$logComCode+"_plus']").each(function(){
                $(this).val(1);
                $(this).attr('readonly',true);
            });
            $("input[name='"+$logComCode+"_postageplus']").each(function(){
                $(this).val(0);
                $(this).attr('readonly',true);
            });
        });
    }















    /*if($(obj).val()=='0'){
    	$('input[name="logComId"]').each(function(){
    		
    		$(this).attr("disabled",false);
    		
    	});
    	
    }else{
    	$('input[name="logComId"]').each(function(){
    		if(this.checked){
    			this.checked=false;
                $('.delivery-info-bar').css("display","none");
    			$("#detail-express"+$(this).val()).css('display','none');
    		}
    		$(this).attr("disabled",true);
    		
    	});
    	
    }*/
}
