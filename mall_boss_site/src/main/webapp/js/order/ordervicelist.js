/**
 * Created by Zhuoy on 2015/4/17.
 */
$(function(){
    /* 高级搜索 */
    $('.advanced_search').popover({
        html : true,
        title : '高级搜索',
        content : $('.advanced_search_cont').html(),
        trigger : 'click',
        placement : 'bottom'
    }).click(function(){
    	 $('select[data-live-search="true"]').select2();
    });
    /* 下面是表单里面的日期时间选择相关的 */
    $('.form_datetime').datetimepicker({
        format: 'yyyy-mm-dd hh:ii:00:00',
        weekStart : 1,
        autoclose : true,
        language : 'zh-CN',
        pickerPosition : 'bottom-left',
        todayBtn : true,
        viewSelect : 'hour'
    });
    $('.form_date').datetimepicker({
        format : 'yyyy-mm-dd',
        weekStart : 1,
        autoclose : true,
        language : 'zh-CN',
        pickerPosition : 'bottom-left',
        minView : 2,
        todayBtn : true
    });
    /* 下面是表单里面的日期时间选择相关的 END */
});

//拼接订单状态显示
function addstatusstr(str,time,num,cla){
	if(time != null){
		return '<li class="'+cla+'">'+
	    '<p class="name">'+str+'</p>'+
	    '<p class="bar"><i>'+num+'</i></p>'+
	    '<p class="time">'+timeObject(time)+'</p>'+
	    '</li>';
	}else{
		return '<li class="'+cla+'">'+
	    '<p class="name">'+str+'</p>'+
	    '<p class="bar"><i>'+num+'</i></p>'+
	    '<p class="time"></p>'+
	    '</li>';
	}
	
}

/*查看详细*/
function fnOpen(oId) {
    $.ajax({
        type : "POST",
        url:"selectdetailsajax.htm?CSRFToken="+$("#CSRFToken").val(),
        data : "orderId=" + oId,
        success:function(data){
        	var order = data;
            if(order!= null){
            	var status = addstatusstr('已下单',order.createTime,1,'active');
            	if(order.payTime != null){
            		status += addstatusstr('已付款',order.payTime,2,'active');
            	}else{
            		status += addstatusstr('已付款',null,2,'');
            	}
            	if(order.sendExpressTime != null){
            		status += addstatusstr('已发货',order.sendExpressTime,3,'active');
            	}else{
            		status += addstatusstr('已发货',null,3,'');
            	}
            	if(order.getGoodsTime != null){
            		status += addstatusstr('已完成',order.getGoodsTime,4,'active');
            	}else{
            		status += addstatusstr('已完成',null,4,'');
            	}
            	$("#status").html(status);
            	$("#detailorderCodeNo").html("订单号："+notNull(order.orderCode));
                $("#detailorderCode").html("订单编号："+notNull(order.orderCode));
                $("#detailcreateTime").html("下单时间："+timeObject(order.createTime));
                //判断状态
                if(order.orderStatus == '1'){
                    $("#detailorderStatus").html("已付款");
                }else if(order.orderStatus == '2'){
                    $("#detailorderStatus").html("已发货");
                }else if(order.orderStatus == '3'){
                    $("#detailorderStatus").html("已完成");
                }else if(order.orderStatus == '4'){
                    $("#detailorderStatus").html("已取消");
                }
                $("#detailcouponNo").html("使用优惠券："+notNull(order.couponNo));
                $("#detailorderPrePrice").html("订单优惠金额："+order.orderPrePrice.toFixed(2));
                $("#detailorderPrice").html(order.orderPrice.toFixed(2));
                if(order.orderIntegral == null){
                    $("#detailorderIntegral").html("积分：0");
                }else{
                    $("#detailorderIntegral").html("积分："+order.orderIntegral);
                }
                //物流信息
                
                $("#detailorderExpress").html("运单号："+notNull(order.expressNo));
                $("#detailexpressPrice").html("物流公司："+notNull(order.expressName));
                $("#detailorderLinePay").html("支付类型：在线支付");
                
                //收货信息
                $("#detailshippingaddress").html("收货地址："+notNull(order.shippingAddressDetail));
                $("#detailaddress").html("详细地址："+notNull(order.shippingAddress));
                $("#detailshippingPerson").html("收货人："+notNull(order.shippingPerson));
                $("#detailshippingPhone").html("联系电话："+notNull(order.shippingPhone));
                $("#detailshippingMobile").html("手机："+notNull(order.shippingMobile));
                $("#detailcustomerRemark").html("客户留言：");
                /*订单商品信息*/

                var str ="";
                
                str+='<tr>';
                str +='<td><div class="data_item"><img alt="" src="'+notNull(order.goodsProductVo.goodsInfoImgId)+'" height="50" >';
                str += '<p>'+notNull(order.goodsProductVo.goodsInfoName)+'</p></div>'; 
              	str += '</td>';
              	str+='<td>'+order.goodsProductVo.goodsInfoPreferPrice.toFixed(2)+'</td>';
              	str+='<td>'+order.goodsNum+'</td>';
                if(order.goodsProductVo.goodsSpecVo !=null) {
                    str += '<td>';
                    for(var s = 0;s<order.goodsProductVo.goodsSpecVo.length;s++){
                            str+=order.goodsProductVo.goodsSpecVo[s].specName+':'+order.goodsProductVo.goodsSpecVo[s].specValue
                    }
                    str += '</td>';
                }
                
                str +='<td>'+order.orderPrice.toFixed(2);
                str +='</td><td>'+order.orderPrice.toFixed(2);
                str =='</td></tr>';
                $("#ordergoods").html(str);
            }
            $('#orderDetails').modal('show');
        }
    });
}

//判断数据是否为空为空返回“”
function notNull(obj){
    if(obj != null && obj != undefined){
        return obj;
    }else{
        return "";
    }
}





//转换时间格式
function timeObject(obj){
    var date = "/Date(" +notNull(obj)+")/";
    var time = new Date(parseInt(date.replace("/Date(", "").replace(")/", ""), 10));
    var result = time.getFullYear() + "-" + (time.getMonth() + 1 < 10 ? "0" + (time.getMonth() + 1) : time.getMonth() + 1) + "-" + (time.getDate() < 10 ? "0" + time.getDate() : time.getDate()) + " " + (time.getHours() < 10 ? "0" + time.getHours() : time.getHours()) + ":" + (time.getMinutes() < 10 ? "0" + time.getMinutes() : time.getMinutes())+ ":" + (time.getSeconds() < 10 ? "0" + time.getSeconds() : time.getSeconds());
    return result;
}

function delivergoodsModal(orderId){
    $("#deliverorderId").val(orderId);
    $("#udpateExpressmodal").modal("show");
}

function deliverorder(){
    if($("#udpateExpress").valid()){
        $("#udpateExpress").submit();
    }
}

function submitOrderCode(){
    $("#orderCodestr").val($("#orderCode").val());
    $("#shippingPersonstr").val($("#shippingPerson").val());
    $("#shippingMobilestr").val($("#shippingMobile").val());
    $("#orderStatusstr").val($("#backCheck").val());
    $("#ordercxform").submit();  
}

function submitOrder(){
    $("#orderCodestr").val($("#orderCode").val());
    $("#shippingPersonstr").val($("#shippingPerson").val());
    $("#shippingMobilestr").val($("#shippingMobile").val());
    $("#orderStatusstr").val($("#backCheck").val());
    $("#salesmanIdstr").val($("#salesmanId").val());
    $('#ordercxform').submit();
}