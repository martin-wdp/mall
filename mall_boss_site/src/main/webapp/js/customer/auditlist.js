$(function(){
	$("#addStoreForm").validate();
	//添加支付
	$("#addPay").click(function(){
		var str = "",pay=$("#payway"),pFlag =true;
		$("#pay_tb tr input").each(function(){
			if($(this).val() == pay.val()){
				showTipAlert("请不要重复添加!");
				pFlag = false;
				//return;
			}
		});
		if(pFlag){
			str = '<tr><input type="hidden" name="payIds" value="'+$("#payway").val()+'" /><td>'+$("#payway").find("option:selected").text()+'</td><td><div class="form-inline"><div class="form-group"><input type="text" class="form-control checkNum" id="deduction'+$("#payway").val()+'" name="deduction" /><label class="control-label"></label></div></div></td><td><div class="form-inline"><div class="form-group"><input type="text" class="form-control checkNum" id="brokerage'+$("#payway").val()+'" name="brokerage"/><label class="control-label"></label></div></div></td><td><button type="button" class="btn btn-default" onclick="delpay(this)">删除</button></td></tr>';
			$("#pay_tb").append(str);
		}
	});
	
	//保存审核信息
	$("#savepay").click(function(){
		if($("#pay_tb tr").length < 1  ){
			showTipAlert("请添加支付方式!");
			return;
		}if($("span[name='tab']").length < 1){
			showTipAlert("请添加结算周期!");
			return;
		}
		else{
		   if($("#pay_form").valid()){
			  	var cTime = new Array();
			  	$($("span[name='tab']")).each(function(){
			  		cTime.push($(this).find('b').html());
			  	});
			  	$("#aTime").val(cTime);
               var falg=true;
               var nums=$(".checkNum");
               for(var i=0;i<nums.length;i++){
                   var num=Number($(nums[i]).val());
                   if(!isNaN(num)){
                       if(num<=0||num>=1){
                           $($(".checkNum")[i]).addClass("error");
                           $($(".checkNum")[i]).next().html("请输入0-1之间的小数").addClass("error").show();
                           falg=false;
                       }
                   }else{
                       $($(".checkNum")[i]).addClass("error");
                       $($(".checkNum")[i]).next().html("请输入0-1之间的小数").addClass("error").show();
                       falg=false;
                   }
               }
                if(falg){
                    $.ajax({
                        url:"newupdateclassifypay.htm?CSRFToken="+$("#CSRFToken").val(),
                        data:$("#pay_form").serialize(),
                        success: function (data) {
                            location.reload();
                        }
                    });
                }
		   }
		}
	});
	//保存审核信息
	$("#savecheck").click(function(){
		if($("#pay_tb tr").length < 1  ){
			showTipAlert("请添加支付方式!");
			return;
		}
        if($(".tabinput").length < 2){
			showTipAlert("请添加结算周期!");
			return;
		}
		else{
            var falg=true;
            var nums=$(".checkNum");
            for(var i=0;i<nums.length;i++){
                var num=Number($(nums[i]).val());
                if(!isNaN(num)){
                    if(num<=0||num>=1){
                        $($(".checkNum")[i]).addClass("error");
                        $($(".checkNum")[i]).next().html("请输入0-1之间的小数").addClass("error");
                        falg=false;
                    }
                }else{
                    $($(".checkNum")[i]).addClass("error");
                    $($(".checkNum")[i]).next().html("请输入0-1之间的小数").addClass("error");
                    falg=false;
                }
            }
		   if(falg){
			  	var cTime = new Array();
			  	$($("span[name='tab']")).each(function(){
			  		cTime.push($(this).find('b').html());
			  	});
			  	var checkedList = new Array();
			  	$("input[name='storeId']:checked").each(function() {
			  		checkedList.push($(this).val());
			  	});
			  	if(checkedList.length >0){
			  		$("#addStoreForm").append('<input value="'+checkedList+'" type="hidden" name="thirdIds" />');
			  	}
			  	$("#addStoreForm").append('<input value="'+cTime+'" type="hidden" name="cellTime" />');
			  // $("#addStoreForm").submit();
               $("#savecheck").attr("disabled", true);
			   $.ajax({
			        url:"newupdatestore.htm?CSRFToken="+$("#CSRFToken").val(),
			        data:$("#addStoreForm").serialize(),
			        success: function (data) {

			            location.reload();
			        }
		    });
		   }
		}
	});
	//保存打回原因
	$("#saveRefuse").click(function(){
		var checkedList = new Array();
	  	$("input[name='storeId']:checked").each(function() {
	  		checkedList.push($(this).val());
	  	});
		 if($("#refuseForm").valid()){
			 if(checkedList.length>0){
				 $("#refuseForm").append('<input value="'+checkedList+'" type="hidden" name="thirdIds" />');
			 }
			 $.ajax({
			        url:"newrefusestore.htm?CSRFToken="+$("#CSRFToken").val(),
			        data:$("#refuseForm").serialize(),
			        type:"post",
			        success: function (data) {
			            location.reload();
			        }
		    });
		 }
	});

    //取消按钮事件
    $(".hycCancle").click(function(){
        $("#savecheck").attr("disabled", false);
    });
});


function delpay(obj){
	$(obj).parent().parent().remove();
}
//设置商铺在店铺街 状态以及排序
function updatestore(storeId){

    //获取单个店铺的信息
    $.ajax({
        type: 'post',
        url:'queryStoreBalanceByThirdId.htm?CSRFToken='+$("#CSRFToken").val()+"&thirdId="+storeId,
        async:false,
        success: function(data) {
            $('.set_tore').val(data.storeStreetort); //排序
            if(data.storeStreetIsShow==0||data.storeStreetIsShow=='0'){
                $('.is_Show0').attr("checked",true);
            }else{
                $('.is_Show1').attr("checked",true);
            }
        }
    });
    //设置要操作的店铺ID
    $('#storeId_update').val(storeId);
    //指定action的请求路径
    $('.save_store').attr("action","setstore.htm");
    //弹出设置的窗体
    $('#set_store_street').modal('show');
}

//保存设置的店铺信息
function save_store(){
    //设置选中的 单选框的value
    $('#is_Show').val($("input[name='show']:checked").val());
    if('' == $('.set_tore').val()){
        $("#set_tore_tip").html("排序不能为空！");
        return;
    }
    $('.save_store').submit();
}

function updatePay(oId,bCycle){
	$(".tags").html("");
	  $.ajax({
	         type: 'post',
	         url:'findpayAll.htm?CSRFToken='+$("#CSRFToken").val(),
	         async:false,
	         success: function(data) {
	        	 var ss = "";
	        	 for(var i = 0 ;i< data.length;i++){
	        		 ss+='<option value="'+data[i].payId+'">'+data[i].payName+'</option>';
	        	 }
	        	 $("#payway").html(ss);
	         }
		 }); 
	  $.ajax({
			url:'selectDeduByStoreId.htm?storeId='+oId+"&CSRFToken="+$("#hi_token").val(), 
			success:function(data) {
				var html = '';
				for(var i=0;i<data.length;i++) {
					html += '<tr><input type="hidden" name="payIds" value="'+data[i].payId+'" /><td>'+data[i].payName+'</td><td><div class="form-inline">',
                  html +='<div class="form-group"><input type="text" class="form-control checkNum" name="deduction" value='+data[i].deduction+'><label class="control-label"></label></div></div></td>',
                  html +='<td><div class="form-inline"><div class="form-group"><input type="text" class="form-control checkNum" name="brokerage" value='+data[i].brokerage+'><label class="control-label"></label></td><td><button type="button" class="btn btn-default" onclick="delpay(this)">删除</button></td></tr>';
				}
				$("#pay_tb").html(html);
			}
		});
	  //var emp = $(".tabinput").val();
		$("#payTime").val(bCycle);
		var times = bCycle.split("|");
		var ts = new Array();
		/*for(var i = 0;i<times.length;i++){
			if(times[i] == emp){
				return;
			}else{
				$(".tags").append('<input class="tabinput" name="tabinput" style="width: 80px; height: 25px; display: none;" type="text">');
				var t="<span name='tab' id='radius'><b>" + times[i] + "</b><a id='deltab'>×</a></span>";
				$(".tags").append(t);
			}
		}
		var i='<input class="tabinput" name="tabinput" style="width:80px;height:25px;" type="text">';
		$(".tags").append(i);*/
		for(var i = 0;i<times.length;i++){
			ts.push(times[i]);
		}
		 $('.tags').tabControl({
	          regularEx: '^[A-Z]+$',
	          maxTabCount:5/*最大标签数*/,
	          tabW:80/*标签最大长度*/,
	          tabH : 25,errorcontent:'请输入1-31之间的正整数',regularEx:/^([1-9]|[1-2]\d|3[0-1])$/ /*匹配正则*/},ts+",");
		//$("#aTime").val(bCycle);
		$("#storeId").val(oId);
//	    $("#pageNo2").val($("#list_pageno").val());
		//$("#audit_from").attr("action","updateClassifyPay.htm");
	    $('#settleInfo').modal('show');
}
