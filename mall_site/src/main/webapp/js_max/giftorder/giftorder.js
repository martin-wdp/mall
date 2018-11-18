var basePath = $("#basePath").val();
function checkAddressPost1(){
	if($(".save_add_post").val() != "" && $(".save_add_post").val()!=null){
		if(/^\d{6}$/.test($(".save_add_post").val())){
			$(".addPostTips").text("").removeClass("tipserror");
		}else{
			$(".addPostTips").text("邮政编码格式不正确!").addClass("tipserror");
		}
	}
}

/*提交订单*/
function tijiao(){
//$(".go_pay").click(function(){
		/*获取所有的收货地址选项*/
		var addressName=$(".save_add_name");
		var infoProvince=$("#infoProvince");
		var infoCity=$("#infoCity");
		var infoCounty=$("#infoCounty");
		var infoStreet=$("#infoStreet");
		var addressDetail=$(".save_add_detail");
		var addressMoblie=$(".save_add_mobile");
		var addressPhone=$(".save_add_phone");
		var addressZip=$(".save_add_post");
		var addressAlias=$(".save_add_alias");
		/*拼接参数*/
		var params="";
		var bool=true;
		/*判断参数不为空就拼接参数*/
		if($(addressName).val()!="" && $(addressName).val()!=null){
			var regexp=new RegExp("[`~!@#$^&*()={}':;',\\[\\]<>/?~！@#￥……&*（）{}【】‘；：”“'。，、？]");
			if (regexp.test($(addressName).val()) ) {
				$(".addressNameTip").text("存在特殊字符").addClass("tipserror");
				return false;
			}
			$("addressNameTip").text("").removeClass("tipserror");
			params+="addressName="+$(addressName).val();
		}else{
			$(".addressNameTip").text("收货人名称不能为空").addClass("tipserror");
			bool=false;
		}
		if($(infoProvince).val()!="" && $(infoProvince).val()!=null){
			params+="&infoProvince="+$(infoProvince).val();
		}
		if($(infoCity).val()!="" && $(infoCity).val()!=null){
			params+="&infoCity="+$(infoCity).val();
		}
		if($(infoCounty).val()!="" && $(infoCounty).val()!=null){
			params+="&infoCounty="+$(infoCounty).val();
		}
		if($(infoStreet).val()!="" && $(infoStreet).val()!=null){
			params+="&infoStreet="+$(infoStreet).val();
		}
		
		if($(addressDetail).val()!="" && $(addressDetail).val()!=null){
			var regexp=new RegExp("[`~!@#$^&*()={}':;',\\[\\]<>/?~！@#￥……&*（）{}【】‘；：”“'。，、？]");
			if (regexp.test(addressDetail.val() ) ) {
				$(".addressDetailTip").text("包含特殊字符!").addClass("tipserror");
				return false;
			}
			$(".addressDetailTip").text("").removeClass("tipserror");
			params+="&addressDetail="+$(addressDetail).val();
		}else{
			$(".addressDetailTip").text("详细地址不能为空!").addClass("tipserror");
			bool=false;
		}
		
		if (/^0?(13|15|18|14)[0-9]{9}$/.test( $(addressMoblie).val() ) ) {
			if($(addressMoblie).val()!="" && $(addressMoblie).val()!=null){
				$(".addressMobiTip").text("").removeClass("tipserror");
				params+="&addressMoblie="+$(addressMoblie).val();
			}
		}else{
			$(".addressMobiTip").text("手机号码格式不对!").addClass("tipserror");
			bool=false;
		}
		if( $(addressPhone).val() !="" && $(addressPhone).val() !=null){
			if (/^((0[0-9]{2,3}\-)|(\(0[0-9]{2,3}\)))?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/.test( $(addressPhone).val() ) ) {
				if($(addressPhone).val()!="" && $(addressPhone).val()!=null){
					$(".addressPhoneTip").text("").removeClass("tipserror");
					params+="&addressPhone="+$(addressPhone).val();
				}
			}else{
				$(".addressPhoneTip").text("电话格式不正确!").addClass("tipserror");
				bool=false;
			}
		}
		if($(".save_add_post").val() != "" && $(".save_add_post").val()!=null){
			if(/^\d{6}$/.test($(".save_add_post").val())){
				$(".addPostTips").text("").removeClass("tipserror");
				params+="&addressZip="+$(addressZip).val();
			}else{
				$(".addPostTips").text("邮政编码格式不正确!").addClass("tipserror");
				bool=false;
			}
		}
		if($(addressAlias).val()!="" && $(addressAlias).val()!=null){
			params+="&addressAlias="+$(addressAlias).val();
		}
		/*end*/
		if(bool){
			$('.go_pay').removeAttr("onclick");
				$.ajax({
			         type: 'post',
			         url:basePath+'/subgiftorder.htm',
			         data:$("#giftorder").serialize(),
			         async:false,
			         success: function(data) {
			        	 if(data>0){
			        		 $("#message").html("订单提交成功!");
			        		 $("#index").hide();
			        		 close(2);
			     			 dia(1);
			        	 }else{
			        		 $("#message").html("订单提交失败!");
			        		 $("#index").hide();
			        		 close(2);
				     		dia(1);
			        	 }
			         }
				 });
			}
	//	});
}


function close(n) {
	$(".dia" + n).fadeOut();
	$(".mask").fadeOut();
};

