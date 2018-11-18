$(document).ready(function(){

	$('.consignee_edit_btn').click(function(){
	      
		$('.consignee_show').hide();
		$('.consignee_edit').show();
	});
	$('.consignee_close_btn').click(function(){
		$('.consignee_show').show();
		$('.consignee_edit').hide();
		$(".save_tb_div").hide();
	});
	$('.payment_edit_btn').click(function(){
		$('.payment_show').hide();
		$('.payment_edit').show();
	});
	$('.payment_close_btn').click(function(){
		$('.payment_show').show();
		$('.payment_edit').hide();
	});
	$('.invoice_edit_btn').click(function(){
		$('.invoice_show').hide();
		$('.invoice_edit').show();
	});
	$('.invoice_close_btn').click(function(){
		$('.invoice_show').show();
		$('.invoice_edit').hide();
	});
	var str;
	var strstrs;
	var yz;
	//使用优惠券
	$('.open_coupon').click(function(){
		str = $(this).attr("class");   
		strstrs = str.split(" "); 
		yz = $.inArray("close_coupon", strstrs);
		if(yz > 0){
			$(this).removeClass('close_coupon');
			$('.use_coupon').hide();
		}
		else{
			$(this).addClass('close_coupon');
			$('.use_coupon').show();
		}
	});
	
	
	//使用积分
	$('.open_coupon_point').click(function(){
		str = $(this).attr("class");   
		strstrs = str.split(" "); 
		yz = $.inArray("close_coupon", strstrs);
		if(yz > 0){
			$(this).removeClass('close_coupon');
			$('.use_coupon_point').hide();
		}
		else{
			$(this).addClass('close_coupon');
			$('.use_coupon_point').show();
		}
	});
	
	
	
	
	$('.use_ent').click(function(){
		$('.ent_coupon').toggle('slow');
	});
	
	//关闭使用优惠券抵消部分金额
	$('.use_coupon .close_btn').click(function(){
		$('.open_coupon').removeClass('close_coupon');
		$(this).parent().hide();
	});
	
	//关闭使用会员积分抵消部分金额
	$('.use_coupon_point .close_btn').click(function(){
		$('.open_coupon_point').removeClass('close_coupon');
		$(this).parent().hide();
	});
	
	
	$(".save_tb_div").hide();
	/*加载收货地址*/
	loadAllAddress();
	/*加载所有的配送方式*/

			loadInvinceAndExpressInfo();

	/*点击配送方式选择的时候*/
	$(".save_express_pay").click(function(){
		/*获取所有的支付方式,并判断是否被选中,如果选中就更改页面上的元素值*/
		var count=document.getElementsByName("thirdIds").length;
		var s = $('input[name="paysets"]:checked').attr("pay-name");
			$(".chectedPay").html(s);
			var distributionId = $('input[name="distributionId"]:checked').val();
			
					var thirdIds= "";
					var cartIds = "";
					var bosscartIds="";
					var baoyoucartIds="";
					var bossnum=0;
					var thirdnum=0;
					
					var yfprice = 0;
					var payprice = $("#payPrice").val();
					var addsId = $(".ch_address").val();
					var boxs=new Array();
					//获取订单数量
					  $('input[name="thirdIds"]').each(function(){ 
						  if("0"==$(this).val()){
							  $(this).parent().next(".goods_list").find('input[name="box"]').each(function(){
								  	//判断是否包邮
								  	if($(this).next().val()=="0"){
										bosscartIds+=$(this).val();
										bosscartIds+="-";
										boxs.push($(this).val());
									}	 
									 bossnum+=1;
								  });
							  bosscartIds+=",";
						  }else{
							  thirdIds+=$(this).val();
							  thirdIds+=",";
							  $(this).parent().next(".goods_list").find('input[name="box"]').each(function(){
									  //判断是否包邮
								  	  if($(this).next().val()=="0"){
										  cartIds+=$(this).val();
										  cartIds+="-";
										  boxs.push($(this).val());
									  }
									 thirdnum+=1;
								  });
							  cartIds+=",";
						  }
						 
						
					  });
					  if(bossnum=='0'){
						  $("#checkedExpressBoss").remove();
						  $("#express_boss").remove();
					  }else{ 
						  $(".checkedExpress_boss").html( $('input[name="distributionId"]:checked').attr("expressname"));
						  $("#bossnum").html('其中有'+bossnum+'样商品');
							 //计算此次购买商品总运费价格
							$.ajax({
									type:"post",
									url:"../getexpressprice.htm",
									async : false,
									data  : {addressId:addsId,thirdIds:0,cartIds:bosscartIds,distributionId:distributionId,box:boxs},
									success:function(data){
										yfprice = accAdd(data,yfprice);
									}	
							});
							
						
					  }
					  
					  if(thirdnum=='0'){
						  $("#checkedExpressThird").remove();
						  $("#express_third").remove();
					  }else{
						  $(".checkedExpress_third").html('其中有'+thirdnum+'样商品,由商家选择合作快递为您配送');
						  $("#thirdnum").html('其中有'+thirdnum+'样商品');
						  //计算此次购买商品总运费价格
							$.ajax({
									type:"post",
									url:"../getexpressprice.htm",
									async : false,
									data  : {addressId:addsId,thirdIds:thirdIds,cartIds:cartIds},
									success:function(data){
										yfprice = accAdd(data,yfprice);
									}	
							});
					  }
					
					    $("#ep").html("运费"+yfprice+"元");
						$("#yfprice").val(yfprice);
						$('.payment_show').show();
						$('.payment_edit').hide();
						
						
						$("#lastpay").html(accAdd(yfprice,payprice));
					    $("#sumPrice").val(accAdd(yfprice,payprice));
				
						$(".ch_pay").val($('input[name="paysets"]:checked').val());
						
						
					
					
		
		
	});
	/*设为默认*/
	$(".set_del_address").click(function(){
		/*获取所有的收货地址选项*/
		var addresses=$(".all_cust_address");
		var isO=false;
		for(var i=0;i<addresses.length;i++){
			/*循环并获取到选中的地址,设置页面上的值*/
			var address=addresses[i];
			if($(address).attr("checked")){
				isO=true;
				/*设置默认地址*/
				$.get("../goods/modifyIsDefaultAddressForOrder.html?addressId="+$(address).val(),function(data){});
				/*加载显示的页面*/
				$(".ch_address").val($(address).val());
				 if($(address).attr("add-name")!="null"){
					 $(".addressName").html($(address).attr("add-name"));
					 if($(".invoiceTit_1").attr("checked")){
						 $(".invoiceTitle").val($(address).attr("add-name"));
					 }
					 $(".ch_invoiceTitle").html($(address).attr("add-name"));
					 $(".ch_invoceTitleValue").val($(address).attr("add-name"));
				 }

				 if($(address).attr("add-city")!="null"){
					 $(".addressCity").html($(address).attr("add-city"));
					}
				 if($(address).attr("add-detail")!="null"){
					 $(".addressDetail").html($(address).attr("add-detail"));
					}
				 if($(address).attr("add-mobi")!="null"){
					 $(".addressMobi").html($(address).attr("add-mobi"));
					}
				 if($(address).attr("add-phone")!="null"){
					 $(".addressPhone").html($(address).attr("add-phone"));
					}
				 if($(address).attr("add-post")!="null"){
					 $(".addressPost").html($(address).attr("add-post"));
				}
				 
				 
			}
		}
		if(isO){
			$(".consignee_close_btn").click();
		}else{
			 $("#con_00").html("请选择收货地址!");
     		 dia(2);
		}
		$('.payment_show').hide();
		$('.payment_edit').show();
		$('.payment_close_btn').parent().append("<span style='font-size: 12px;font-weight: normal;color: #185D94;'>[由于您更改了收货人信息，请重新设置并<a class='save_express_pay' style='color:#FF6600;cursor:pointer;'>保存支付及配送方式</a>]<span>");
		$('.payment_close_btn').remove();
	});

	/*点击保存发票信息*/
	$(".save_invoice").click(function(){
		
		/*获取所有的发票类型并获取到选中的,改变页面上的值*/
		//var invoiceType=$(".invoiceType");
		//for(var i=0;i<invoiceType.length;i++){
		//	if($(invoiceType[i]).attr("checked")){
		//		$(".ch_invoiceType").html($(invoiceType[i]).next("label").html());
		//		$(".ch_invoceTypeValue").val($(invoiceType[i]).val());
		//	}
		//}
        var selectedInvoceType = $("input[name='invoiceType']:checked");
        if(selectedInvoceType.val() == 0){
            $(".ch_invoiceType").html(selectedInvoceType.next("label").html());
            $(".ch_invoceTypeValue").val(selectedInvoceType.val());
            $(".tr_invoiceTitleView").hide();
            $(".tr_invoiceContentView").hide();
            $(".invoice_close_btn").click();
            return;
        }else {
            $(".ch_invoiceType").html(selectedInvoceType.next("label").html());
            $(".ch_invoceTypeValue").val(selectedInvoceType.val());
        }
		/*获取到所有的发票抬头,并获取到当前选择的发票抬头*/
		var invoiceTitle=$(".invoiceTit");
		for(var i=0;i<invoiceTitle.length;i++){
			if($(invoiceTitle[i]).attr("checked")){
				if($(invoiceTitle[i]).val()=="2"){
					if($(".invoiceTitle").val()=="" || $(".invoiceTitle").val()==null){
						$(".invoiceTitleTip").addClass("tipserror");
						$(".invoiceTitleTip").text("发票抬头不允许为空");
						return;
					}else{
						$(".invoiceTitleTip").removeClass("tipserror");
						$(".invoiceTitleTip").text("");
					}
                    $(".ch_invoiceTitle").html($(".invoiceTitle").val());
                    $(".ch_invoceTitleValue").val($(".invoiceTitle").val());
				}else{
					$(".ch_addressinfo").click();
					$(".ch_invoiceTitle").html("个人");
					$(".ch_invoceTitleValue").val("个人");
				}
                $(".tr_invoiceTitleView").show();
			}
		}
		/*获取所有的发票内容的选项,并获取选中的哪一个*/
		var invoiceContent=$(".invoiceContent");
		for(var i=0;i<invoiceContent.length;i++){
			if($(invoiceContent[i]).attr("checked")){
                $(".tr_invoiceContentView").show();
				$("#invoiceContentMing").html($(invoiceContent[i]).next("label").html());
				$(".ch_invoiceContentValue").val($(invoiceContent[i]).next("label").html());
			}
		}
		$(".invoice_close_btn").click();
	});

    $("input[name='invoiceType']").change(function(){
        if($(this).val() == 0){
            $(".tr_invoiceTitle").hide();
            $(".tr_invoiceContent").hide();
        }else {
            $(".tr_invoiceTitle").show();
            $(".tr_invoiceContent").show();
            $(".invoiceTitle").val($(".addressName").html());
        }
    });

	$(".add_ch_addressinfo").click(function(){
		$(".save_update_add_id").attr("value","0");
		$(".save_tb_div").show();
		setDefaultForm();
		
	});
	/*点击保存收货人信息的时候*/
	$(".ch_addressinfo").click(function(){
		/*获取所有的收货地址选项*/
		var addresses=$(".all_cust_address");
		for(var i=0;i<addresses.length;i++){
			/*循环并获取到选中的地址,设置页面上的值*/
			var address=addresses[i];
			if($(address).attr("checked")){
				/*设置默认地址*/
				$.get("../goods/modifyIsDefaultAddressForOrder.html?addressId="+$(address).val(),function(data){});
				/*加载显示的页面*/
				$(".ch_address").val($(address).val());
				 if($(address).attr("add-name")!="null"){
					 $(".addressName").html($(address).attr("add-name"));
					 if($(".invoiceTit_1").attr("checked")){
						 $(".invoiceTitle").val($(address).attr("add-name"));
					 }
					 $(".ch_invoiceTitle").html($(address).attr("add-name"));
					 $(".ch_invoceTitleValue").val($(address).attr("add-name"));
				 }
				 if($(address).attr("add-city")!="null"){
					 $(".addressCity").html($(address).attr("add-city"));
					}
				 if($(address).attr("add-detail")!="null"){
					 $(".addressDetail").html($(address).attr("add-detail"));
					}
				 if($(address).attr("add-mobi")!="null"){
					 $(".addressMobi").html($(address).attr("add-mobi"));
					}
				 if($(address).attr("add-phone")!="null"){
					 $(".addressPhone").html($(address).attr("add-phone"));
					}
				 if($(address).attr("add-post")!="null"){
					 $(".addressPost").html($(address).attr("add-post"));
				}
			}
		}
			$(".save_tb_div").hide();
	});
	/*点击保存收货地址的时候*/
	$(".save_address").click(function(){
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
		if($(addressName).val()!=null &&$.trim($(addressName).val())!=""){
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
		
		if($(addressDetail).val()!=null && $.trim($(addressDetail).val())!=""){
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
		
		//判断临时用户的手机是否已经存在
		var conditions="mobel="+addressMoblie.val();
		$.ajax({
			   type: "POST",
			   url: "../isshowmobelexist.htm",
			   data: conditions,
			   async: false,
			   success: function(msg){
				   if(msg=="1"){
					   bool=false;
					   $(".addressMobiTip").html("手机号码已存在,请更换号码，或者<a href='../login.html'>登陆 </a>").addClass("tipserror");
				   }
			   }
		});
		//判断临时用户的手机是否已经存在end
		
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
			if($(".save_update_add_id").val()>0){
				params+="&addressId="+$(".save_update_add_id").val();
				if($(".save_add_post").val()==""){
					params+="&addressZip=''";
				}
				$.ajax({
			         type: 'post',
			         url:'../goods/modiCustAddress.html?CSRFToken='+$("#csrfNo").val(),
			        data: encodeURI(params),
			         async:false,
			         success: function(data) {
			        	 if(data){
			        		 loadAllAddress();
			        		 $("#con_01").html("更新地址成功!");
			     			dia(1);
			        	 }else{
			        		 $("#con_00").html("更新地址失败!");
				     		 dia(2);
			        	 }
			         }
				 });
			}else{
				if($(".all_cust_address").length>=9){
					 $("#con_00").html("收货地址最多只能保存9个!");
					 dia(2);
					return false;
				}
				$.ajax({
			         type: 'post',
			         url:'../goods/ajaxAddCustomerAddressFromOrder.html?CSRFToken='+$("#csrfNo").val(),
			         data:encodeURI(params),
			         async:false,
			         success: function(data) {
			        	 if(data){
			        		 loadAllAddress();
			        		 $("#con_01").html("新增地址成功!");
			     			dia(1);
			     			//把新增成功的地址 直接设置为默认地址
			     			setAddress();
			        	 }else{
			        		 $("#con_00").html("新增地址失败!");
				     			dia(2);
			        	 }
			         }
				 });
			}
		}
	});
	
	/*加载发票以及支付配送方式信息*/
	loadInvinceAndExpressInfo();
	
	

	//把新增成功的地址 直接设置为默认地址
	function setAddress(){
		/*获取所有的收货地址选项*/
		var addresses=$(".all_cust_address");
		var isO=false;
		for(var i=0;i<addresses.length;i++){
			/*循环并获取到选中的地址,设置页面上的值*/
			var address=addresses[i];
			if($(address).attr("checked")){
				isO=true;
				/*设置默认地址*/
				$.get("../goods/modifyIsDefaultAddressForOrder.html?addressId="+$(address).val(),function(data){});
				/*加载显示的页面*/
				$(".ch_address").val($(address).val());
				 if($(address).attr("add-name")!="null"){
					 $(".addressName").html($(address).attr("add-name"));
					 if($(".invoiceTit_1").attr("checked")){
						 $(".invoiceTitle").val($(address).attr("add-name"));
					 }
					 $(".ch_invoiceTitle").html($(address).attr("add-name"));
					 $(".ch_invoceTitleValue").val($(address).attr("add-name"));
				 }
				 if($(address).attr("add-city")!="null"){
					 $(".addressCity").html($(address).attr("add-city"));
					}
				 if($(address).attr("add-detail")!="null"){
					 $(".addressDetail").html($(address).attr("add-detail"));
					}
				 if($(address).attr("add-mobi")!="null"){
					 $(".addressMobi").html($(address).attr("add-mobi"));
					}
				 if($(address).attr("add-phone")!="null"){
					 $(".addressPhone").html($(address).attr("add-phone"));
					}
				 if($(address).attr("add-post")!="null"){
					 $(".addressPost").html($(address).attr("add-post"));
				}
			}
		}
		
		if(isO){
			$(".consignee_close_btn").click();
		}else{
			 $("#con_00").html("请选择收货地址!");
	 		 dia(2);
		}
		$('.payment_show').hide();
		$('.payment_edit').show();
		$('.payment_close_btn').parent().append("<span style='font-size: 12px;font-weight: normal;color: #185D94;'>[由于您更改了收货人信息，请重新设置并<a class='save_express_pay' style='color:#FF6600;cursor:pointer;'>保存支付及配送方式</a>]<span>");
		$('.payment_close_btn').remove();
	}

	
	
	
	  
	
	
	
	
	
});
/*加载所有的收货地址*/
function loadAllAddress(){
	setDefaultForm();
	$(".save_update_add_id").val("");
	
	var addsId="";
	/*查询所有的收货地址信息*/
	$.ajax({type:"post",url:"../goods/queryCustAddressForSubOrder.html",async : false,success:function(data){
			var address_html="";
            //没有登录
            if(data==''){
            return;
            }
			$(".cust_allAddress").html("");
			/*如果收货地址记录大于等于10条,就隐藏添加收货地址的table*/
			if(data.addresses.length>=10){
				$(".save_address_tb").hide();
			}else{
				$(".save_address_tb").show();
			}
			for(var i=0;i<data.addresses.length;i++){
				var address=data.addresses[i];
				 address_html+="<li>" + 
				 		"<p><input class='all_cust_address cust_address_"+address.addressId+"' type='radio'";
				 /*如果是默认的地址就默认选中并且改变页面上的值*/
				 if(address.isDefault=="1"){
					addsId=address.addressId;
					 address_html+=" checked='checked' ";
					 $(".ch_address").val(address.addressId);
					 $(".addressName").html(address.addressName);
					 if($(".invoiceTit").attr("checked")){
						 $(".invoiceTitle").val(address.addressName);
					 }
					 $(".ch_invoiceTitle").html(address.addressName);
					 $(".ch_invoceTitleValue").val(address.addressName);
					 $(".ch_invoiceTitle").val(address.addressName);
					 var addHtml=address.province.provinceName+" "+address.city.cityName+" "+address.district.districtName+" ";
					 if(null != address.street && address.street.streetName!=""){
						 addHtml=addHtml+address.street.streetName;
					 }
					 $(".addressCity").html(addHtml);
					 $(".addressDetail").html(address.addressDetail);
					 $(".addressMobi").html(address.addressMoblie);
					 $(".addressPhone").html(address.addressPhone);
					 if(address.addressZip!="''"){
						 $(".addressPost").html(address.addressZip);
					 }
					 $(".ch_invoiceTitle").html(address.addressName);
				 }
				 /*把所有的属性放在checkbox的自定义属性当中*/
				 address_html+="name='address_in' value='"+address.addressId+"' add-name='"+address.addressName+"'" +
				 		" add-city='"+address.province.provinceName+address.city.cityName+address.district.districtName+"'";
				 		if(null != address.street && address.street.streetName!=""){
				 			address_html+=address.street.streetName;
				 		}
				 address_html+=" add-detail='"+address.addressDetail+"'" +
				 		" add-mobi='"+address.addressMoblie+"'" +
				 		" add-phone='"+address.addressPhone+"'" +
				 		" add-province='"+address.province.provinceId+"'" +
				 		" add-cityid='"+address.city.cityId+"'" +
				 		" add-district='"+address.district.districtId+"'";
						 if(null != address.street && address.street.streetId!=""){
							 address_html+=" add-street='"+address.street.streetId+"'";
						 }else{
							 address_html+=" add-street='-1'";
						 }
				 		address_html+=" add-alias='"+address.addressAlias+"'" +
				 		" add-post='"+address.addressZip+"' />" +
				 		"<label><b>"+address.addressName+"</b>" +
				 		"&nbsp;&nbsp;&nbsp;"+address.province.provinceName+" "+address.city.cityName+" "+address.district.districtName+" ";
						 if(null != address.street && address.street.streetName!=""){
							 address_html=address_html+address.street.streetName;
						 }
				 address_html+= " "+address.addressDetail+"</label>" +
				 		"<a href='javascript:void(0);' onclick='modiAddress("+address.addressId+");'>修改</a><a style='padding-left:10px;' href='javascript:void(0);' onclick='delAddress("+address.addressId+");'>删除</a>" +
				 		"</p>" +
				 		"</li>";
			}
			$(".cust_allAddress").html(address_html);
			loadInvinceAndExpressInfo();
	}
	});
	
	
}

//选择发票抬头是个人的时候 
function checkGeRen(obj){
	$(".invoiceTitleTip").text("");
	if($(obj).prop("checked")){
		$(".invoiceTitle").val($(".addressName").html()).addClass("none");
		$(".ch_invoiceTitle").val($(".addressName").html());
	}
}
//选择发票抬头是单位的时候 
function checkDanWei(obj){
	if($(obj).prop("checked")){
		$(".invoiceTitle").val("").removeClass("none");
	}
}

//验证添加收货地址的姓名
function checkAddressName(){
	var addressName=$(".save_add_name");
	/*判断参数不为空就拼接参数*/
	if($(addressName).val()!=""){
		$(".addressNameTip").text("").removeClass("tipserror");
	}else{
		$(".addressNameTip").text("收货人名称不能为空").addClass("tipserror");
	}
}
//详细地址
function checkAddressDetail(){
	var addressDetail=$(".save_add_detail");
	
	if($(addressDetail).val()!=""){
		var regexp=new RegExp("[`~!@#$^&*()={}':;',\\[\\]<>/?~！@#￥……&*（）{}【】‘；：”“'。，、？]");
		if (regexp.test(addressDetail.val() ) ) {
			$(".addressDetailTip").text("包含特殊字符!").addClass("tipserror");
			return false;
		}
		$(".addressDetailTip").text("").removeClass("tipserror");
	}else{
		$(".addressDetailTip").text("详细地址不能为空!").addClass("tipserror");
	}
	
}

//手机号码
function checkAddressMobile(){
	var addressMoblie=$(".save_add_mobile");
	if (/1\d{10}/.test( $(addressMoblie).val() ) ) {
		if($(addressMoblie).val()!=""){
			$(".addressMobiTip").text("").removeClass("tipserror");
		}
	}else{
		$(".addressMobiTip").text("手机号码格式不对!").addClass("tipserror");
		
	}
	//判断临时用户的手机是否已经存在
	var conditions="mobel="+addressMoblie.val();
	$.ajax({
		   type: "POST",
		   url: "../isshowmobelexist.htm",
		   data: conditions,
		   success: function(msg){
			   if(msg=="1"){
				   $(".addressMobiTip").html("手机号码已存在,请更换号码，或者<a href='../login.html'>登陆 </a>").addClass("tipserror");
			   }
		     
		   }
	});
	//判断临时用户的手机是否已经存在end
}
//固定电话
function checkAddressPhone(){
	var addressPhone=$(".save_add_phone");
	if( $(addressPhone).val() !=""){
		if (/(\d{4}-?\d{7,8})|(\d{3}-?\d{8})/.test( $(addressPhone).val() ) ) {
			if($(addressPhone).val()!=""){
				$(".addressPhoneTip").text("").removeClass("tipserror");
			}
		}else{
			$(".addressPhoneTip").text("电话格式不正确!").addClass("tipserror");
		}
	}
}

function checkAddressPost(){
	if($(".save_add_post").val() != "" && $(".save_add_post").val()!=null){
		if(/^\d{6}$/.test($(".save_add_post").val())){
			$(".addPostTips").text("").removeClass("tipserror");
			params+="&addressZip="+$(addressZip).val();
		}else{
			$(".addPostTips").text("邮政编码格式不正确!").addClass("tipserror");
		}
	}
}

//删除收货地址
function delAddress(id){
	$.get("../goods/delCustAddressById.html?addressId="+id,function(data){
		if(data){
			loadAllAddress();
		}else{
			 $("#con_00").html("删除失败,请重试!");
			 dia(2);
		}
	});
}

//重置添加地址的表单
function setDefaultForm(){
	$(".save_add_name").val("");
	$(".save_add_detail").val("");
	$(".save_add_mobile").val("");
	$(".save_add_phone").val("");
	$(".save_add_post").val("");
	$(".addressNameTip").text("");
	$(".addressDetailTip").text("");
	$(".addressMobiTip").text("");
	$(".addressPhoneTip").text("");
	$(".save_add_alias").val("");
}
//编辑地址
function modiAddress(id){
	
	$(".save_update_add_id").val(id);
	var address=$(".cust_address_"+id);
	$(".save_add_name").val($(address).attr("add-name"));
	$(".save_add_detail").val($(address).attr("add-detail"));
	$(".save_add_mobile").val($(address).attr("add-mobi"));
	$(".save_add_phone").val($(address).attr("add-phone"));
	if($(".save_add_phone").val()=="null"){
	$(".save_add_phone").val("");
	}
	$(".save_add_post").val($(address).attr("add-post"));
	if($(".save_add_post").val()=="null"){
		$(".save_add_post").val("");
	}
	$(".save_add_alias").val($(address).attr("add-alias"));
	if($(".save_add_alias").val()=="null"){
		$(".save_add_alias").val("");
	}
	selectLocationOption($(address).attr("add-province"),$(address).attr("add-cityid"),$(address).attr("add-district"),$(address).attr("add-street"),"infoProvince","infoCity","infoCounty","infoStreet");
	$(".save_tb_div").show();
}

/*加载已经选中的发票信息和支付信息*/
function loadInvinceAndExpressInfo(){
	var infos = $(".backCheckInfos");
	for(var i = 0;i<infos.length;i++){
		var info = $(infos[i]);
		if(info.attr("role")=="paysets"){
			$(".payset_radio_"+info.val()).prop("checked",true);
		}
		if(info.attr("role")=="express"){
			$(".express_radio_"+info.val()).prop("checked",true);
		}
		if(info.attr("role")=="sendTime"){
			$(".distTime_"+info.val()).prop("checked",true);
		}
		if(info.attr("role")=="timeSure"){
			$(".distTrue_"+info.val()).prop("checked",true);
		}
		if(info.attr("role")=="invoiceType"){
			$(".invoiceType_"+info.val()).prop("checked",true);
		}
		if(info.attr("role")=="invoiceTit"){
			$(".invoiceTit_"+info.val()).prop("checked",true);
			if(info.val()=="2"){
				$(".invoiceTitle").val(info.attr("invoiceTitle")).removeClass("none");
			}else{
				$(".invoiceTitle").val($(".addressName").val());
			}
		}
		if(info.attr("role")=="invoiceContent"){
			$(".invoiceContent_"+info.val()).prop("checked",true);
		}
		
		$(".save_tb_div").hide();
		$(".save_invoice").click();
	}	
	$(".save_express_pay").click();
}


	//积分兑换
	function jifen(){
		var sum = $('#sumPrice').val();              //订单总价格
		var pointSet = $('#pointSet').val();          //积分兑换规则
		var amount = $('#amount').val();                //需要兑换的积分
		var customerPoint =$('#jifen1').val();   //总积分
		var zhekou = accDiv(accMul(amount, pointSet),10); //积分兑换的折扣价格
		var zhekoujiage = Subtr(sum,zhekou);                //积分兑换后的价格
		var muqianjifen = Subtr(customerPoint,amount);        //目前积分数量
		if(amount==""||amount==null){
			$("#tishi").html('注：请填写要兑换的积分数量！');
			$('#amount').val('');
		}else{
			//当订单金额小于1时  禁止积分兑换
			if(sum<1){
				$("#tishi").html('注：目前订单金额不支持使用积分兑换功能！');
				$('#amount').val('');
			}else{
				//判断用户的积分情况
				if(parseInt(customerPoint) < parseInt(amount) || parseInt(amount) > parseInt(customerPoint)){
					$("#tishi").html('注：亲您没有那么多可用积分！');
					$('#amount').val('');
				}else{
					if(amount %10 !=0){
						$("#tishi").html('注：兑换的积分必须为10的倍数!');
						$('#amount').val('');
					}else{
						//积分兑换的金额不能大于订单总金额
						if(zhekou>sum){
							var keyishiyong = accMul(accMul(sum,pointSet),10);
							$("#tishi").html("注：此次订单价格最多可以使用【"+keyishiyong+"】的积分！</span>");
							$('#amount').val('');
						}else{
							$('#muqianjifen').html(muqianjifen);           //设置目前有的积分
							$('#muqianjifen1').html(muqianjifen);          //设置目前可用的积分
							//转换为字符串
							var jifenString = zhekoujiage.toString();
							var zheKouString = zhekou.toString();
							if (jifenString.indexOf('.')<0){
								$('#lastpay').html(zhekoujiage+'.00');         //设置应付总金额
								//给总价格赋值  用于同优惠券一起使用
								$('#payPrice').val(zhekoujiage+'.00');
							}else{
								//如果应付总金额小数点后面只有一位 就补一个0 否则就去当前计算得出的价格
								if(jifenString.length-jifenString.indexOf('.')==2){
									$('#lastpay').html(zhekoujiage+'0');         //设置应付总金额
									//给总价格赋值  用于同优惠券一起使用
									$('#payPrice').val(zhekoujiage+'0');
								}else{
									$('#lastpay').html(zhekoujiage);         //设置应付总金额
									//给总价格赋值  用于同优惠券一起使用
									$('#payPrice').val(zhekoujiage);
								}
							}
							//如果金额长度大于保留的两位小数的长度 就截取前面4位
							if(zheKouString.length>4){
								zheKouString = zheKouString.substring(0,4);
							}
							$('#jf').html(zheKouString);         //设置商品金额减去的积分兑换的金额
							
							
							//如果金额小数点太多 进行处理 保留两位小数
//							if($('#lastpay').html().length>5){
//								var str = $('#lastpay').html().substring(0,$('#lastpay').html().indexOf("."));
//								$('#lastpay').html(str+'.00');         //设置应付总金额
//								$('#payPrice').val(str+'.00');
//							}
							//禁掉使用按钮和输入框
							//$("#shiyong").attr("disabled","disabled");
							//$("#amount").attr("disabled","true");
							$("#tishi").html('');
						}
					}
			    }
			
			}
		}
		return;
	}
