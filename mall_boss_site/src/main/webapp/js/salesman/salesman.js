var oldmobile;
$(function(){
   /**
	 * 检查用户名是否存在
	 */
	$("#customerUsername").blur(function(){
		if(modified !=1 && $(this).val()!=""){
			checkExist("customerUsername",$("#customerUsername").val(),"checkExistCustomerUsername.htm","用户名");
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

/*ajax 通过ID查询并塞值到修改页面*/
function doSearchCustomer(customerId){
    $.post("queryCustomerById.htm?CSRFToken="+$("#hi_token").val()+"&customerId="+customerId,function(data){
        $("input[id^='customerUsername']").val(data.customerUsername);

        $('#customerUsername').attr('readonly', 'readonly');
        $('#customerUsername').hide();
        $("#nameDiv").text(data.customerUsername);

        $("#nameDiv").show();

        $("#mobile").val(data.infoMobile);
        $('#mobile').attr('readonly', 'readonly');
        $('#mobile').hide();
        if(data.infoMobile != null){
            $("#mobileDiv").text(data.infoMobile);
        }else{
            $("#mobileDiv").text("");
        }
         $("#oldmobile").val(data.infoMobile);
         if(data.infoGender==0){
            $('#gender1').prop('checked', 'checked');
         }else if(data.infoGender==1){
            $('#gender2').prop('checked', 'checked');
         }else{
            $('#gender3').prop('checked', 'checked');
         }

         //将收货地址 省份 城市 区县 选中
         $("#realname").val(data.infoRealname);
         $("#mobile").val(data.infoMobile);
         $("#customerId").val(customerId);
         oldmobile=$("#oldmobile").val();
             
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
			    	  aa="1";
			    	bValid = false;
			      } else {
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



