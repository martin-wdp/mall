var oldemail;
var oldmobile;
$(function(){
    $('.headPortrait').popover({
        content : '建议100*100px',
        trigger : 'hover'
    });

   /**
	 * 检查用户名是否存在
	 */
	$("#customerUsername").blur(function(){
		if(modified !=1 && $(this).val()!=""){
			checkExist("customerUsername",$("#customerUsername").val(),"checkExistCustomerUsername.htm","用户名");
		}
	});
      
    /**
     *
     */
    $("#email").blur(function(){
  		if(modified !=1 && $(this).val()!=""){
  			checkExist("email",$("#email").val(),"checkbossemailexist.htm","邮箱");
  		}else if(modified ==1){
  			 if($("#email").val()!="" && oldemail !=$("#email").val()){
    			  checkExist("email",$("#email").val(),"checkbossemailexist.htm","邮箱");
    		  }else{
    			  $('#email').removeClass('error');
    	          $("#emailtip").hide();
    		  }
  		}
  	});
    
    $("#mobile").blur(function(){
		if(modified !=1  && $(this).val()!=""){
			checkExist("mobile",$("#mobile").val(),"checkbossmobileexist.htm","手机号");
		}else if(modified ==1){
			if($("#mobile").val()!="" && oldmobile !=$("#mobile").val()){
	    		   checkExist("mobile",$("#mobile").val(),"checkbossmobileexist.htm","手机号");
	    	  }else{
	    		  $('#mobile').removeClass('error');
  	              $("#mobiletip").hide();
	    	  }
		}
	});
 }); 
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
        var options = "<option value='' >"+"选择省份"+"</option>";
        for( var i=0; i<data.length; i++){
            var province=data[i];
            options +=  "<option value='"+province.provinceId+"'>"+province.provinceName+"</option>";
        }
        $("#province").html(options); 
        $("#city").html("<option value='' >"+"选择城市"+"</option>");
        $("#district").html("<option value='' >"+"选择区县"+"</option>");
        $("#street").html("<option value='' >"+"选择街道"+"</option>");
        $('select[data-live-search="true"]').select2();
    });
 
}



/**
 * 省份改变时 触发此事件 开始加载下一级 --城市 select[id^='province']
 */
$("#province").change(function(){
    $.post("getAllCityByPid.htm?CSRFToken="+$("#hi_token").val()+"&provinceId="+$(this).val(),function(data){
            if(data!=''&&data.length!=0){
                var options = "<option value='' >"+"选择城市"+"</option>";
                for( var i=0; i<data.length; i++){
                    var city=data[i];
                    options +=  "<option value='"+city.cityId+"'>"+city.cityName+"</option>";
                }
                $('#city').html(options);
                $("#district").html("<option value='' >"+"选择区县"+"</option>");
                $("#street").html("<option value='' >"+"选择街道"+"</option>");
                $('select[data-live-search="true"]').select2();
            }else{
                $("#city").html("<option value='' >"+"选择城市"+"</option>");
                $("#district").html("<option value='' >"+"选择区县"+"</option>");
                $("#street").html("<option value='' >"+"选择街道"+"</option>");
                $('select[data-live-search="true"]').select2();
            }
    });
    
});


/**
 * 城市改变时 触发此事件 开始加载下一级 --区县
 */
$("#city").change(function(){
    $.post("getAllDistrictByCid.htm?CSRFToken="+$("#hi_token").val()+"&cityId="+$(this).val(),function(data){
      
        if(data!=''&&data.length!=0){
            var options = "<option value='' >"+"选择区县"+"</option>";
            for( var i=0; i<data.length; i++){
                var district=data[i];
                options +=  "<option value='"+district.districtId+"'>"+district.districtName+"</option>";
            }
            $('#district').html(options);
            $("#street").html("<option value='' >"+"选择街道"+"</option>");
            $('select[data-live-search="true"]').select2();
        }else{
            $("#district").html("<option value='' >"+"选择区县"+"</option>"); 
            $("#street").html("<option value='' >"+"选择街道"+"</option>");
            $('select[data-live-search="true"]').select2();
        }
    });
    
});


/**
 * 区县改变时 触发此事件 开始加载下一级 --街道
 */
$("#district").change(function(){
    $.post("getAllStreetByDid.htm?CSRFToken="+$("#hi_token").val()+"&dId="+$(this).val(),function(data){
        if(data!=''&&data.length!=0){
            var options = "<option value='' >"+"选择街道"+"</option>";
            for( var i=0; i<data.length; i++){
                var street=data[i];
                options +=  "<option value='"+street.streetId+"'>"+street.streetName+"</option>";
            }
            $('#street').html(options);
            $('select[data-live-search="true"]').select2();
        }else{
            $("#street").html("<option value='' >"+"选择街道"+"</option>");
            $('select[data-live-search="true"]').select2();
        }
    });
    
});



/*ajax 通过ID查询并塞值到修改页面*/
function doSearchCustomer(customerId){
    fillPandL();
    $.post("queryCustomerById.htm?CSRFToken="+$("#hi_token").val()+"&customerId="+customerId,function(data){
             $("input[id^='customerUsername']").val(data.customerUsername);

             $('#customerUsername').attr('readonly', 'readonly');
             $('#customerUsername').hide();
             $('#password').hide();
             $('#repassword').hide();
             $("#nameDiv").text(data.customerUsername);
             $("#passDiv").text("******");
             $("#repassDiv").text("******");
        $("#nameDiv").show();
        $("#passDiv").show();
        $("#repassDiv").show();
             $("#email").val(data.infoEmail);
            $('#email').attr('readonly', 'readonly');
        $('#email').hide();
        if(data.infoEmail != null){
            $("#emailDiv").text(data.infoEmail);
        }else{
            $("#emailDiv").text("");
        }

             $("#oldemail").val(data.infoEmail);
        $('#password').val("******");
        $('#repassword').val("******");
             $('#password').attr('readonly', 'readonly');

             $('#repassword').attr('readonly', 'readonly');  
             $("#mobile").val(data.infoMobile);
        $('#mobile').attr('readonly', 'readonly');
        $('#mobile').hide();
        if(data.infoMobile != null){
            $("#mobileDiv").text(data.infoMobile);
        }else{
            $("#mobileDiv").text("");
        }
             $("#oldmobile").val(data.infoMobile);
        if(data.customerImg!=null&&data.customerImg!=""){

            $("#img").attr("src",data.customerImg);
        }
             $("#customerImg").val(data.customerImg);
             if(data.infoGender==0){
                $('#gender1').prop('checked', 'checked');
             }else if(data.infoGender==1){
                $('#gender2').prop('checked', 'checked');
             }else{
                $('#gender3').prop('checked', 'checked');
             }
             //选中会员等级
             $("#pointLevel option[value='"+data.pointLevelId+"']").prop("selected","selected");
			 if(data.infoProvince!=null){
			  //将会员省份 城市 区县选中
             selectLocationOption(data, "province", "city", "district", "street");
			 }
             //将收货地址 省份 城市 区县 选中
             $("#realname").val(data.infoRealname); 
             $("#mobile").val(data.infoMobile);
             $("#card_id").val(data.infoCardid);
             $("#info_detail").val(data.infoAddress);
             $("#customerId").val(customerId);
        if (data.isEnterprise == "0") {
            $('#enableSalaList').hide();
        }
        if (data.isEnterprise == "1") {
            $('#enableSalaList').show();
        }
             oldemail=$("#oldemail").val();
             oldmobile=$("#oldmobile").val();
             
    });
}

function selectLocationOption (data,po,co,dio,so){

    
    $("#"+po+" option[value='"+data.infoProvince+"']").prop("selected","selected");//选中省份
    $('select[data-live-search="true"]').select2();
    $.post("getAllCityByPid.htm?CSRFToken="+$("#hi_token").val()+"&provinceId="+data.infoProvince,function(data1){
        if(data1!=''&&data1.length!=0){
            var options = "<option value='' >"+"选择城市"+"</option>";
            for( var i=0; i<data1.length; i++){
                var city=data1[i];
                options +=  "<option value='"+city.cityId+"'>"+city.cityName+"</option>";
            }  
            $('#city').html(options);
            $("#"+co+" option[value='"+(data.infoCity)+"']").prop("selected","selected");//选中城市
            $("#district").html("<option value='' >"+"选择区县"+"</option>");
            $("#street").html("<option value='' >"+"选择街道"+"</option>");
            $('select[data-live-search="true"]').select2();
            
            
            
            $.post("getAllDistrictByCid.htm?CSRFToken="+$("#hi_token").val()+"&cityId="+data.infoCity,function(data1){
                
                if(data1!=''&&data1.length!=0){
                    var options = "<option value='' >"+"选择区县"+"</option>";
                    for( var i=0; i<data1.length; i++){
                        var district=data1[i];
                        options +=  "<option value='"+district.districtId+"'>"+district.districtName+"</option>";
                    }
                    $('#district').html(options);
                    $("#"+dio+" option[value='"+data.infoCounty+"']").prop("selected","selected"); //选中区县
                    $("#street").html("<option value='' >"+"选择街道"+"</option>");
                    $('select[data-live-search="true"]').select2();
                    
                    $.post("getAllStreetByDid.htm?CSRFToken="+$("#hi_token").val()+"&dId="+data.infoCounty,function(data1){
                        if(data1!=''&&data1.length!=0){
                            var options = "<option value='' >"+"选择街道"+"</option>";
                            for( var i=0; i<data1.length; i++){
                                var street=data1[i];
                                options +=  "<option value='"+street.streetId+"'>"+street.streetName+"</option>";
                            }
                            $('#street').html(options);
                            $("#"+so+" option[value='"+data.infoStreet+"']").prop("selected","selected"); //选中区县  
                            $('select[data-live-search="true"]').select2();
                        }else{
                            $("#street").html("<option value='' >"+"选择街道"+"</option>");
                            $('select[data-live-search="true"]').select2();
                        }
                    });
                    
                    
                }else{
                    $("#district").html("<option value='' >"+"选择区县"+"</option>"); 
                    $("#street").html("<option value='' >"+"选择街道"+"</option>");
                    $('select[data-live-search="true"]').select2();
                }
            }); 
            
            
        }else{
            $("#city").html("<option value='' >"+"选择城市"+"</option>");
            $("#district").html("<option value='' >"+"选择区县"+"</option>");
            $("#street").html("<option value='' >"+"选择街道"+"</option>");
            $('select[data-live-search="true"]').select2();
        }
        
        
        
});
    
   
    
   
}

/**
 * propName 属性名
 * value 	属性值
 * url 		查询路径
 * tipStr	提示内容
 */
function checkExist(propName,value,url,tipStr){
	if(value!=""&&  !$("#"+propName).hasClass("error")){
		url=url+"?CSRFToken="+$("#hi_token").val()+"&"+propName+"="+value;
		var aa="";
		$.ajax({
	         type: 'post',
	         url:url,
	         async:false,
	         success: function(data) {
	        	 if (data > 0){
			    	   $("#"+propName).addClass("error");
			    	   $("#"+propName+"tip").show();
			    	 // $("."+propName+"tip").text(tipStr+"已存在").addClass("ui-state-highlight"); 
			    	  aa="1";
			    	//nameFlag=false;
			    	bValid = false;
			      } else {
			    	 // $("."+propName+"tip").text(tipStr+"可用").addClass("ui-state-highlight").addClass("property_useable");
			    	 $("#"+propName).removeClass("error");
			    	 $("#"+propName+"tip").hide();
			    	  aa="2";
			    	bValid = true;
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



