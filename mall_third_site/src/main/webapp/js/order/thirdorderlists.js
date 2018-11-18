/*
 * 批量废除订单
 */
function delThirdOIds(){
	var bool = false;
	 var checks = $(".check_box");
	 var checkGroup = new Array();
	 for (var i = 0; i < checks.length; i++) {
	        var e = checks[i];
	        if (e.checked == true) {
	            bool = true;
	            checkGroup.push(e);
	        }
	 }
	 if(bool==false){
		 $(".show_title").text("请至少选择一行数据!");
		 dia(3);
		 return null;
	 }
	 dia(2);
}

//拣货
function pickingGoods(obj){
	var bool = false;
	 var checks = $(".input_orderId");
	 var checkGroup = new Array();
	 for (var i = 0; i < checks.length; i++) {
	        var e = checks[i];
	        if (e.checked == true) {
	            bool = true;
	            checkGroup.push(e.value);
	        }
	 }
	 
	 if(bool==false){
		 $(".show_title").text("请至少选择一行数据!");
		 dia(3);
		 return null;
	 }
	 $(".order_p_id").attr("value",checkGroup);
	 if(obj==0){
		 $(".thirdorder_picking").attr("action","thirdorderpicking.html").submit();
	 }else{
		 $(".thirdorder_picking").attr("action","thirdorderdeliverys.html").submit();
	 }
}

function SuDelThirdOId(){
	$(".sub_delThirdOrder").attr("action","delThirdOrderByparams.htm").submit();
}
/*
 * 更改页数
 */
function changePageNo(obj){
	$(".searchThirdOrderList").append("<input type='hidden' name='pageNo' value='"+$(obj).attr("data-role")+"' />").submit();
}


/*
 * 单个删除订单
 */
function del(id){
	$("#thirdOrderId").val(id);
}
function delOrderById(){
	 $("#mform").submit();
}

//更新
function modifyThirdOrderbyId(id){
	$(".searchOrderList").hide();
	$.post("searcharOrderByParam.htm", { orderId: id},
	function(data){
		$(".upt_orderCode").html(data.orderCode);
		$(".upt_shippingPerson").html(data.shippingPerson);
		$(".upt_shippingPhone").html(data.shippingMobile);
		$("#orderPrice").val(data.orderPrice);
		//设置id
		$(".order_id").val(data.orderId);
	});
	$(".updateOrder").show();
}
//根据条件查询出库列表
function quertOutStock(){
    $('.searchThirdOrderList').submit();
}
//根据条件查询订单信息
function queryOrderList(){
    $('.searchThirdOrderList').submit();
}

//重置表单
function resetThirdOrder(){
	$(".searchThirdOrderList")[0].reset();
	$(".form-control").prop("value","");
}

//更新订单
function uptThirdOrder(){
	    var isT=true;
	    isT = checkRegexp( $("#orderPrice"),/^\d+(\.\d{1,2})?$/, "价格必须是正整数或两位小数点" , $(".upt_orderInfo")) && isT;
	    if(isT){
	    	$(".update_thirdorder").submit();
	    }
	    
}
$(function(){
	$("#iq_selByParam").val($(".order_status").val());
	
	$("#closeOrder").click(function(){
		$(".searchOrderList").show();
		$(".updateOrder").hide();
	});
});


//更新订单状态信息
function upOrderSta(orderId,sta){
	$(".ordersta").removeClass("none");
	$(".up_order_sta").val(orderId);
	var options = $(".old_order_sta").find("option");
	for(var i =0;i<options.length;i++){
		if($(options[i]).val()==sta){
			$(options[i]).prop("selected","selected");
			break;
		}
	}
    //如果是未付款
    if(sta==0){
        $(".option1").css("display","none");
        $(".option2").css("display","none");
        $(".option0").css("display","none");
        $(".option3").css("display","none");
    }else if(sta==1){
        $(".option0").css("display","none");
        $(".option1").css("display","none");
        $(".option3").css("display","none");
        $(".option4").css("display","none");
        $(".option6").css("display","none");
    }else if(sta==2){
        $(".option0").css("display","none");
        $(".option1").css("display","none");
        $(".option4").css("display","none");
        $(".option6").css("display","none");
    }else if(sta==3){
        $(".option0").css("display","none");
        $(".option1").css("display","none");
        $(".option2").css("display","none");
        $(".option4").css("display","none");
        $(".option6").css("display","none");
    }else if(sta == 4){
        $(".option0").css("display","none");
        $(".option2").css("display","none");
        $(".option3").css("display","none");
        $(".option6").css("display","none");
    }
}

//提交更改订单状态的表单
function subThirdOrderSta(){
    if($("#orderStatus").val()==-1){
        $('.select_tip').html("请选择状态");
        return;
    }
    $('.select_tip').html("");
    $(".up_order_sta").submit();
}

//查询状态改变
function changTbl(obj){
	if(obj==10){
		resetThirdOrder();
		$(".tabStatus").prop("value",obj);
		$(".searchThirdOrderList").submit();
	}else{
		resetThirdOrder();
		$(".tabStatus").prop("value",obj);
		$(".searchThirdOrderList").submit();
	}
	
}


//出库
function delivery(obj){
	location.href="thirdorderdeliverys.html?orderId="+obj;
}

//发货
function sendgoods(obj){
	location.href="thirdordersendgoods.html?orderId="+obj;
}

//加载事件
$(function(){
	if($(".tabStatus").val()==""){
		$(".tb_barterOrder").addClass("active");
	}else if($(".tabStatus").val()==0&&$(".tabStatus").val()!=""){
		$(".tb_backOrderSH").addClass("active");
	}else if($(".tabStatus").val()==1){
		$(".tb_backOrderTg").addClass("active");
	}else if($(".tabStatus").val()==2){
		$(".tb_backOrderJj").addClass("active");
	}else if($(".tabStatus").val()==3){
		$(".tb_backOrderYt").addClass("active");
	}else if($(".tabStatus").val()==4){
		$(".tb_backOrderover").addClass("active");
	}else if($(".tabStatus").val()==6){
		$(".tb_backOrderJh").addClass("active");
	}else if($(".tabStatus").val()==10){
		$(".tb_backOrderr").addClass("active");
	}else{
		$(".tb_backOrderCk").addClass("active");
	}
});