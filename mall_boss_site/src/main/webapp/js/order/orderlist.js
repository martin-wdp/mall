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
        format: 'yyyy-mm-dd hh:ii:00',
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

    /*下面是时间选择器开始时间不能大于结束时间设置  START*/
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    $('#endpicker').datetimepicker('setStartDate', startTime);
    $('#startpicker').datetimepicker('setEndDate', endTime);
    $('#endpicker')
        .datetimepicker()
        .on('show', function (ev) {
            startTime = $("#startTime").val();
            endTime = $("#endTime").val();
            $('#endpicker').datetimepicker('setStartDate', startTime);
            $('#startpicker').datetimepicker('setEndDate', endTime);
        });
    $('#startpicker')
        .datetimepicker()
        .on('show', function (ev) {
            endTime = $("#endTime").val();
            startTime = $("#startTime").val();
            $('#startpicker').datetimepicker('setEndDate', endTime);
            $('#endpicker').datetimepicker('setStartDate', startTime);
        });
    /*下面是时间选择器开始时间不能大于结束时间设置  END*/

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
        url:"orderdetailajax.htm",
        data : "orderId=" + oId,
        success:function(data){
            //清空列表
            $("#ordergoods").html("");
            $("#pageorder").html("");
            $("#orderLog").html("");
            //退款日志
            $("#ordertk").html("");
            if(data!="" && data!= null && data != undefined){
                /*等的详情*/
                //订单详细信息
                var order = data.order;
                var relations = data.relations;
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
                	$("#detailorderCodeNo").html("订单号："+notNull(order.orderCode));
                    $("#detailorderCode").html("订单编号："+notNull(order.orderCode));
                    $("#detailcreateTime").html("下单时间："+timeObject(order.createTime));
                    if(order.orderCancelTime == null) {
                        $("#detailorederRetime").html('');
                    }else{
                        $("#detailorederRetime").html("订单取消时间："+timeObject(order.orderCancelTime));
                    }
                    //判断状态
                    if(order.orderStatus == '0'){
                        $("#detailorderStatus").html("未付款");
                    }else if(order.orderStatus == '1'){
                        $("#detailorderStatus").html("已付款未发货");
                    }else if(order.orderStatus == '2'){
                        $("#detailorderStatus").html("已发货");
                    }else if(order.orderStatus == '3'){
                        $("#detailorderStatus").html("已完成");
                    }else if(order.orderStatus == '4'){
                        $("#detailorderStatus").html("已取消");
                    }else if(order.orderStatus == '14'){
                        $("#detailorderStatus").html("退单审核中");
                    }else if(order.orderStatus == '8'){
                        $("#detailorderStatus").html("同意退货");
                    }else if(order.orderStatus == '9'){
                        $("#detailorderStatus").html("拒绝退货");
                    }else if(order.orderStatus == '10'){
                        $("#detailorderStatus").html("待商家收货");
                    }else if(order.orderStatus == '11'){
                        $("#detailorderStatus").html("退单结束");
                    }else if(order.orderStatus == '15'){
                        $("#detailorderStatus").html("退款审核中");
                    }else if(order.orderStatus == '13'){
                        $("#detailorderStatus").html("拒绝退款");
                    }else if(order.orderStatus == '14'){
                        $("#detailorderStatus").html("已提交退货审核");
                    }else if(order.orderStatus == '16'){
                        $("#detailorderStatus").html("商家收货失败");
                    }else if(order.orderStatus == '17'){
                        $("#detailorderStatus").html("已退款");
                    }else if(order.orderStatus == '18'){
                        $("#detailorderStatus").html("退款成功");
                    }
                    $("#status").html(status);
                    $("#detailcouponNo").html("使用优惠券："+notNull(order.couponNo));
                    $("#detailorderOldPrice").html("订单原始金额："+order.orderOldPrice.toFixed(2));
                    $("#detailorderPrePrice").html("订单优惠金额："+order.orderPrePrice.toFixed(2));
                    $("#detailorderPrice").html(order.orderPrice.toFixed(2));
                    if(order.orderIntegral == null){
                        $("#detailorderIntegral").html("订单使用积分：0");
                    }else{
                        $("#detailorderIntegral").html("订单使用积分："+order.orderIntegral);
                    }
                    if(order.orderCancelRemark == null){
                        $("#detailorederRemark").html("");
                    }else{
                        $("#detailorederRemark").html("取消订单原因："+order.orderCancelRemark);
                    }

                    //物流信息
                    var str = ""
                    var count = 0;
                    for(var i=0;i<relations.length;i++){
                        count++;
                        str+="<p>包裹["+count+"]</p>";
                        if(relations[i].expressName==null){
                            str+="<div class='col-sm-12'><p>物流公司：</p></div>"
                        }else{
                            str+="<div class='col-sm-12'><p>物流公司："+relations[i].expressName+"</p></div>"
                        }
                        if(relations[i].expressNo==null){
                            str+="<div class='col-sm-12'><p>物流单号：</p></div>"
                        }else{
                            str+="<div class='col-sm-12'><p>物流单号："+relations[i].expressNo+"</p></div>"
                        }
                    }

                    $("#relations").html(str);
                    if(order.orderExpress.expressTypeId==0){
                        $("#detailorderExpress").html("配送方式：快递配送");
                    }else if(order.orderExpress.expressTypeId==1){
                        $("#detailorderExpress").html("配送方式：上门自提");
                    }
                    if(order.invoiceType =="0"){
                    	var invoicestr = '<div class="col-sm-12">'+
                        '<p>发票类型：不需要发票</p>'+
                        '</div>'+
                        '<div class="col-sm-12">';
                   	 if(order.orderLinePay=='1'){
                   		 invoicestr+='<p>支付类型：在线支付</p>';   
                        }else if(order.orderLinePay=='0'){
                       	 invoicestr+='<p>支付类型：货到付款</p>';
                        }      
                   	 invoicestr +='</div>';
                    	$("#invoiceType").html(invoicestr);
                    }else if(order.invoiceType =="1"){
                    	var invoicestr = '<div class="col-sm-12">'+
                        '<p>发票类型：普通发票</p>'+
                        '</div>'+
                        '<div class="col-sm-12">'+
                          '<p>发票抬头：'+notNull(order.invoiceTitle)+'</p>'+
                        '</div>'+
                        '<div class="col-sm-12">'+
                          '<p>发票内容：'+notNull(order.invoiceContent)+'</p>'+
                        '</div>'+
                        '<div class="col-sm-12">';
                    	 if(order.orderLinePay=='1'){
                    		 invoicestr+='<p>支付类型：在线支付</p>';
                             
                         }else if(order.orderLinePay=='0'){
                        	 invoicestr+='<p>支付类型：货到付款</p>';
                         }
                          
                    	 invoicestr +='</div>';
                    	$("#invoiceType").html(invoicestr);
                    }
                    
                    $("#detailexpressPrice").html("运费："+order.expressPrice.toFixed(2));
                    
                    //收货信息
                    $("#detailshippingaddress").html("收货地址："+notNull(order.shippingProvince)+notNull(order.shippingCity)+notNull(order.shippingCounty));
                    $("#detailaddress").html("详细地址："+notNull(order.shippingAddress));
                    $("#detailshippingPerson").html("收货人："+notNull(order.shippingPerson));
                    $("#detailshippingPhone").html("联系电话："+notNull(order.shippingPhone));
                    $("#detailshippingMobile").html("手机："+notNull(order.shippingMobile));
                    $("#detailshippingPostcode").html("邮编："+notNull(order.shippingPostcode));
                    $("#detailcustomerRemark").html("客户留言："+notNull(order.customerRemark));
                    /*订单商品信息*/

                    var str ="";
                    $.each(order.orderGoodsList,function(idx,item){
                        str+='<tr id="goodsnum'+idx+'" class="">';
                        str +='<td><div class="data_item"><img alt="" src="'+notNull(item.goodsProductVo.goodsInfoImgId)+'" height="50" >';
                        str += '<p>'+notNull(item.goodsProductVo.goodsInfoName)+'</p></div>'; 
                      	str += '</td>';
                      	str+='<td>'+item.goodsInfoOldPrice.toFixed(2)+'</td>';
                      	str+='<td>'+item.goodsInfoNum+'</td>';
                        if(item.goodsProductReleSpecVoList!=null) {
                            str += '<td>';
                            for(var s = 0;s<item.goodsProductReleSpecVoList.length;s++){
                                    str+=item.goodsProductReleSpecVoList[s].spec.specName+':'+item.goodsProductReleSpecVoList[s].goodsSpecDetail.specDetailName
                            }
                            str += '</td>';
                        }
                        
                        str +='<td>'+item.goodsInfoPrice.toFixed(2);
                        str +='</td><td>'+item.goodsInfoSumPrice.toFixed(2);
                        str =='</td></tr>';
                    });
                    $("#ordergoods").html(str);
                }
            }
            $('#orderDetails').modal('show');
        }
    });
}

function orderlog(oId){
	$.ajax({
        type : "POST",
        url:"orderLogAjax.htm",
        data : "orderId=" + oId,
        success:function(data){
        	$("#orderLog").html("");
        	//订单操作日志
            var orderLogs = data.orderLogs;
            var lognum=pageRows(orderLogs,4);
            if(orderLogs !=null && orderLogs != undefined){
                //清空列表
            	var str ="";
                var strLog = "";
                var strtk="";
                var lognum=pageRows(orderLogs,4);
                var strpagelog = "";
                $.each(orderLogs,function(idx,item){
                    if(idx<4){
                    	strLog+='<tr id="orderLognum'+idx+'" class="">';
                    }else{
                    	strLog+='<tr id="orderLognum'+idx+'" class="ordergoods">';
                    }
                    if(item.orderLogStatus =='0'){
                        strLog +='<td>修改金额</td>';
                    }else if(item.orderLogStatus =='1'){
                        strLog +='<td>中断订单</td>';
                    }else if(item.orderLogStatus =='2'){
                        strLog +='<td>进行拣货</td>';
                    }else if(item.orderLogStatus =='3'){
                        strLog +='<td>进行出库</td>';
                    }else if(item.orderLogStatus =='4'){
                        strLog +='<td>进行发货</td>';
                    }
                    else if(item.orderLogStatus =='5'){
                        strtk +='<tr><td>退款</td>';
                        strtk +='<td>'+timeObject(item.orderLogTime)+'</td>';
                        strtk +='<td>'+item.orderLogReason+'</td></tr>';
                    }else if(item.orderLogStatus =='6'){
                        strLog +='<td>修改状态</td>';
                    }
                    strLog +='<td>'+item.orderLogPerson+'</td>';
                    strLog +='<td>'+timeObject(item.orderLogTime)+'</td>';
                    strLog +='<td>'+item.orderLogReason+'</td></tr>';
                });
                if(strtk != ""){
                    $("#ordertk").html(strtk);
                }else{
                    $("#ordertk").html('<tr><td colspan="3"><p class="text-center">暂无可用数据！</p></td></tr>');
                }
                if(strLog != ""){
                    $("#orderLog").html(strLog);
                }else{
                    $("#orderLog").html('<tr><td colspan="4"><p class="text-center">暂无可用数据！</p></td></tr>');
                }
            }
            $('#orderLogmodal').modal('show');
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

//订单商品信息分页显示
function pagegoods(pageNo,rows){
    $("#pageorder").html("");
    var strpage = "";
    if(pageNo>1){
        strpage +='<a href="javascript:;" onclick="pagegoods('+(pageNo-1)+','+rows+')"> 上一页 </a>';
    }else{
        strpage +='<span class="disabled"> 上一页 </span>';
    }
    for(var i=1;i<=rows;i++){
        if(i== pageNo){
            strpage +='<span class="current"> '+i+'</span>';
        }else{
            strpage +='<a href="javascript:;" onclick="pagegoods('+i+','+rows+')">'+i+' </a>';
        }
    }
    if(pageNo<rows){
        strpage+='<a href="javascript:;" onclick="pagegoods('+(pageNo+1)+','+rows+')"> 下一页 </a>';
    }else{
        strpage+='<span class="disabled"> 下一页 </span>';
    }
    $("#pageorder").html(strpage);
    $("#ordergoods tr").attr("class","ordergoods");
    $("#goodsnum"+(pageNo*4-1)).attr("class","");
    $("#goodsnum"+(pageNo*4-2)).attr("class","");
    $("#goodsnum"+(pageNo*4-3)).attr("class","");
    $("#goodsnum"+(pageNo*4-4)).attr("class","");
}

//订单操作日志分页显示
function pagelogs(pageNo,rows){
    $("#pagelog").html("");
    var strpage = "";
    if(pageNo>1){
        strpage +='<a href="javascript:;" onclick="pagelogs('+(pageNo-1)+','+rows+')"> 上一页 </a>';
    }else{
        strpage +='<span class="disabled"> 上一页 </span>';
    }
    for(var i=1;i<=rows;i++){
        if(i== pageNo){
            strpage +='<span class="current"> '+i+'</span>';
        }else{
            strpage +='<a href="javascript:;" onclick="pagelogs('+i+','+rows+')">'+i+' </a>';
        }
    }
    if(pageNo<rows){
        strpage+='<a href="javascript:;" onclick="pagelogs('+(pageNo+1)+','+rows+')"> 下一页 </a>';
    }else{
        strpage+='<span class="disabled"> 下一页 </span>';
    }
    $("#pagelog").html(strpage);
    $("#orderLog tr").attr("class","ordergoods");
    $("#orderLognum"+(pageNo*4-1)).attr("class","");
    $("#orderLognum"+(pageNo*4-2)).attr("class","");
    $("#orderLognum"+(pageNo*4-3)).attr("class","");
    $("#orderLognum"+(pageNo*4-4)).attr("class","");
}

//转换时间格式
function timeObject(obj){
    var date = "/Date(" +notNull(obj)+")/";
    var time = new Date(parseInt(date.replace("/Date(", "").replace(")/", ""), 10));
    var result = time.getFullYear() + "-" + (time.getMonth() + 1 < 10 ? "0" + (time.getMonth() + 1) : time.getMonth() + 1) + "-" + (time.getDate() < 10 ? "0" + time.getDate() : time.getDate()) + " " + (time.getHours() < 10 ? "0" + time.getHours() : time.getHours()) + ":" + (time.getMinutes() < 10 ? "0" + time.getMinutes() : time.getMinutes())+ ":" + (time.getSeconds() < 10 ? "0" + time.getSeconds() : time.getSeconds());
    return result;
}

function pageRows(obj,pageSize){
    //计算页数
    var num = 0;
    if(obj.length<=pageSize){
        num =1;
    }else if(obj.length%pageSize == 0){
        num = obj.length/pageSize;
    }else{
        num = obj.length/pageSize+1;
    }
    return num;
}

//修改状态
function fnModifyOrder(oId,sta,orderLinePay){
    var h='';
    $("#oStatusst option").remove();
    if(orderLinePay==0){
        if(sta==3){
            h+='<option value="3">已收货完成</option>';
        }
    }else{
        h+='<option value="0">未付款</option>';
        h+='<option value="1">已付款</option>';
    }
    $("#oStatusst").html(h);
    $("#oStatusst").val(sta);
    $("#updateOrderst").attr("action","modifyOrderStatus.htm?orderId="+oId+'&CSRFToken='+$("#CSRFToken").val());
    $('#changeOrderStatus').modal('show');
}

function updateOrdersub(){
    $("#updateOrderst").submit();
}

//修改金额
function modifyPrice(orderId,orderCodex,orderPrice){
    $("#o_order_price").attr("value",orderPrice);
    $(".modify_order_id").attr("value",orderId);
    $(".modify_order_codex").attr("value",orderCodex);
    $('#upmoneyYN').modal('show');
}
//提示框确定
function upmoneyYN(){
    $('#upmoneyYN').modal('hide');
    $('#changeOrderMoney').modal('show');
}
function upmoney(){
    //非空判断
    if($("#order_price").val()==''){
        $("#moneytip").html("不能为空");
        if($("#up_orReason").val()==''){
            $("#reasontip").html("不能为空");
            return false;
        }else{
            $("#reasontip").html("");
            return false;
        }
    }
    if($("#order_price").val()!='' && $("#order_price").val().match(/^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/) == null){
        $("#moneytip").html("金额格式不正确");
        $("#moneytip").attr("style","");
        if($("#up_orReason").val()==''){
            $("#reasontip").html("不能为空");
            return false;
        }else{
            $("#reasontip").html("");
            return false;
        }
    }
    $("#modify_order_price").attr("value",$("#order_price").val());
    var price=$("#o_order_price").val()-$("#modify_order_price").val();
    if(price>0 && checkReason()){
        $("#changeOrderMoney").modal("hide");
        $("#changeOrderMoneypwd").modal("show");
    }else if(price<0){
        $("#moneytip").html("金额不能高于订单金额");
        $("#moneytip").attr("style","");
        checkReason();
    }else if(price>0){
        $("#moneytip").html("");
    }
}
function checkReason(){
    if($("#up_orReason").val()==''){
        $("#reasontip").html("不能为空");
        return false;
    }else{
        $("#reasontip").html("");
        return true;
    }
}

//验证身份
function upmoneypwd(){
    var reg = /^.*[A-Za-z0-9\\w_-]+.*$/;
    var pwd = $("#changepwd").val();
    var token=$("#CSRFToken").val();
    if(pwd != null && pwd.trim().length != 0){
        var url = 'checkUserKey.htm?userKey='+pwd+'&CSRFToken='+token;
        $.ajax({
            type : 'post',
            url : url,
            async : false,
            success : function(data) {
                if (data > 0) {
                    $("#upmoneyform").attr("action","modifyorderprice.htm").submit();
                } else if(data == 0) {
                    $(".pwdtip").text("密码错误!");
                    $(".pwdtip").addClass("alert-danger");
                    $(".col-sm-10").addClass("has-error");
                    return;
                }
            },
            error:function(){
                $(".pwdtip").text("网络连接异常!");
                $(".pwdtip").addClass("alert-danger");
                $(".col-sm-10").addClass("has-error");
            }
        });
    }else{
        $(".pwdtip").text("请输登录密码!");
        $(".pwdtip").addClass("alert-danger");
        $(".col-sm-10").addClass("has-error");
    }
}

/**
 * 中断订单
 * @param oId
 * @param sta
 * @param code
 */
function fnModifyOrderCancel(oId,sta,code){
    $("#orReason").val("");
    $("#oStatus").val(sta);
    $("#orderCodex").val(code);
    $("#orId").val(oId);
    //确认中断框
    $('#updateOrderStatusYN').modal('show');
}

/**
 * 提示是否中断
 */
function updateOrderoYN(){
    $('#updateOrderStatusYN').modal('hide');
    $('#updateOrderStatus').modal('show');

}

/**
 * 验证登陆
 */
function updateOrderoStatus(){
    if($("#updateOrder").valid()){
        $('#updateOrderStatus').modal('hide');
        //验证登陆框
        $('#verifylogin').modal('show');
    }

}

/**
 * 验证登陆
 */
function verifylogin(){
    var reg = /^.*[A-Za-z0-9\\w_-]+.*$/;
    var pwd = $("#pwd").val();
    if(pwd != null && pwd.trim().length != 0){
        var url = 'checkUserKey.htm?userKey='+pwd+'&CSRFToken='+$("#CSRFToken").val();
        $.ajax({
            type : 'post',
            url : url,
            async : false,
            success : function(data) {
                if (data > 0) {
                    $("#updateOrder").attr("action","suspendorder.htm").submit();
                } else if(data == 0) {
                    $(".pwdtip").text("密码错误!");
                    $(".col-sm-10").addClass("has-error");
                    $(".pwdtip").addClass("alert-danger");
                    return;
                }
            },
            error:function(){
                $(".pwdtip").text("网络连接异常!");
                $(".col-sm-10").addClass("has-error");
                $(".pwdtip").addClass("alert-danger");
            }
        });
    }else{
        $(".pwdtip").text("请输登录密码!");
        $(".col-sm-10").addClass("has-error");
        $(".pwdtip").addClass("alert-danger");
    }
}

function submitOrderCode(){
    $("#orderCodestr").val($("#orderCode").val());
    $("#shippingPersonstr").val($("#shippingPerson").val());
    $("#storeNamestr").val($("#storeName").val());
    $("#shippingMobilestr").val($("#shippingMobile").val());
    $("#startTimestr").val($("#startTime").val());
    $("#orderStatusstr").val($("#orderStatus").val());
    $("#customerUsernamestr").val($("#customerUsername").val());
    $("#endTimestr").val($("#endTime").val());
    $("#ordercxform").submit();
}

function submitOrder(){
    $("#orderCodestr").val($("#orderCode").val());
    $("#shippingPersonstr").val($("#shippingPerson").val());
    $("#storeNamestr").val($("#storeName").val());
    $("#shippingMobilestr").val($("#shippingMobile").val());
    $("#startTimestr").val($("#startTime").val());
    $("#orderStatusstr").val($("#orderStatus").val());
    $("#customerUsernamestr").val($("#customerUsername").val());
    $("#endTimestr").val($("#endTime").val());
    $("#wareIdstr").val($("#wareId").val());
    $("#salesmanIdstr").val($("#salesmanId").val());
    $("#ordercxform").submit();
}
/**
 * 打印订单详情
 * */
function doPrint() {
    bdhtml=window.document.body.innerHTML;//获取当前页的html代码
    sprnstr="<!--startprint1-->";//设置打印开始区域
    eprnstr="<!--endprint1-->";//设置打印结束区域
    prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html
    prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html
    //window.open($("#basePath").val()+"printorderpickinglist.htm?str="+prnhtml);
    $("#str").val(prnhtml);
    $("#printView").submit();
}