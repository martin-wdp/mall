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
function viewdetail(oId) {
	$.ajax({
        type : "POST",
        url:"backorderdetailajax.htm?CSRFToken="+$("#CSRFToken").val(),
        data : "backOrderId=" + oId,
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
                	if(order.endTime != null){
                		status += addstatusstr('已完成',order.endTime,4,'active');
                	}else{
                		status += addstatusstr('已完成',null,4,'');
                	}
                	$("#status").html(status);
                	$("#detailorderCodeNo").html("订单号："+notNull(order.orderCode));
                    $("#detailorderCode").html("订单编号："+notNull(order.orderCode));
                    $("#detailcreateTime").html("下单时间："+timeObject(order.createTime));
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
                    }else if(order.orderStatus == '12'){
                        $("#detailorderStatus").html("退款审核中");
                    }else if(order.orderStatus == '13'){
                        $("#detailorderStatus").html("拒绝退款");
                    }
                    $("#detailcouponNo").html("使用优惠券："+notNull(order.couponNo));
                    $("#detailorderOldPrice").html("订单原始金额："+order.orderOldPrice.toFixed(2));
                    $("#detailorderPrePrice").html("订单优惠金额："+order.orderPrePrice.toFixed(2));
                    $("#detailorderPrice").html(order.orderPrice.toFixed(2));
                    if(order.orderIntegral == null){
                        $("#detailorderIntegral").html("订单使用积分：0");
                    }else{
                        $("#detailorderIntegral").html("订单使用积分："+order.orderIntegral);
                    }
                    //物流信息
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
                    $("#detailshippingaddress").html("收货地址："+notNull(order.shippingAddress));
                    $("#detailaddress").html("详细地址："+notNull(order.address));
                    $("#detailshippingPerson").html("收货人："+notNull(order.shippingPerson));
                    $("#detailshippingPhone").html("联系电话："+notNull(order.shippingPhone));
                    $("#detailshippingMobile").html("手机："+notNull(order.shippingMobile));
                    $("#detailcustomerRemark").html("客户留言："+notNull(order.customerRemark));
                    /*订单商品信息*/

                    var str ="";
                    $.each(order.orderGoodsList,function(idx,item){
                        str+='<tr id="goodsnum'+idx+'" class="">';
                        str +='<td><div class="data_item"><img alt="" src="'+notNull(item.goodsProductVo.goodsInfoImgId)+'"  height="50">';
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

function orderlog(oId,bId){
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
                        strLog +='<td>拣货完成</td>';
                    }else if(item.orderLogStatus =='3'){
                        strLog +='<td>装箱完成</td>';
                    }else if(item.orderLogStatus =='4'){
                        strLog +='<td>出库完成</td>';
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
    $.ajax({
        type: "POST",
        url: "backorderlog.htm?backOrderId="+bId,
        success: function (data) {
            $("#pageLog").html("");
            var s = "<tr>";
            if(data != null){
             for(var i=0;i<data.length;i++) {
                if(data[i].backLogStatus == 7){
                        s += '<td>退款成功</td><td>admin</td>'
                    }
                 if(data[i].backLogStatus == 6){
                     s += '<td>收货失败</td><td>admin</td>'
                 }
                 if(data[i].backLogStatus == 8){
                     s += '<td>同意退款</td><td>admin</td>'
                 }
                 if(data[i].backLogStatus == 9){
                     s += '<td>拒绝退款</td><td>admin</td>'
                 }
                 if(data[i].backLogStatus == 5){
                     s += '<td>确认收货</td><td>admin</td>'
                 }
                 if(data[i].backLogStatus == 4){
                     s += '<td>待填写快递单号</td><td>顾客</td>'
                 }if(data[i].backLogStatus == 3){
                     s += '<td>申请审核不通过</td><td>admin</td>'
                 }if(data[i].backLogStatus == 2){
                     s += '<td>申请审核通过</td><td>admin</td>'
                 }if(data[i].backLogStatus == 1){
                     s += '<td>申请退货</td><td>顾客</td>'
                 }
                 s+='<td>'+timeObject(data[i].backLogTime)+'</td>';
                 s+='</tr>';

                }
            }else{
                $("#pageLog").html('<tr><td colspan="4"><p class="text-center">暂无可用数据！</p></td></tr>');
            }
            $("#pageLog").html(s);
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
//退货审核
function  fnModifyBackOrder(backId){
    if($("#businessId").val() == 0){
        $("#updateOrderbackCheck").attr("action","modifyBackOrder.htm?backOrderId="+backId);
    }else{
        $("#updateOrderbackCheck").attr("action","modifyBackOrderisthird.htm?backOrderId="+backId);
    }
    $("#dialog-Back-backCheck").modal("show");
}

// 退货操作
function backdetail(backId,orderId) {
    window.frames["mainFrame"].document.body.innerHTML = "";
    $(".remark_content").text("");
    $("#mainFrame").attr("src",'newbackdetail.htm?CSRFToken='+$("#CSRFToken").val()+'&backId='+backId+'&orderId='+orderId);
    $('#dialog-Back-backCheck').modal('show');
    $('#mainFrame').css('minHeight','700px');
}


// 退款操作
function backprice(backId,orderId) {
    window.frames["mainFrames"].document.body.innerHTML = "";
    $(".remark_contents").text("");
    $("#mainFrames").attr("src",'newbackprice.htm?CSRFToken='+$("#CSRFToken").val()+'&backId='+backId+'&orderId='+orderId);
    $('#dialog-Back-price').modal('show');
    $('#mainFrames').css('minHeight','500px');
}


function saveorder() {
    if($(".flag_saved").val()=="1"){
        return false;
    }else {
        var backLogStatus = $(".backLogStatus:checked");
        if (null == backLogStatus || backLogStatus.length == 0) {
            $(".remark_content").text("请选择操作！");
            return;
        }
        //是否是退单成功 进入退款流程
        var backCheck_check = $('#backCheck_check').val();
        //退单成功后 填写的退款金额
        var checkBackPrice = $('.checkBackPrice').val();
        if(checkBackPrice==""){
            $(".remark_contents").text("请填写退款金额！");
            return;
        }
        $(".flag_saved").val("1");
        window.parent.$('#dialog-Back-backCheck').modal('hide');
        $("#submitForm").submit();
    }
}

function showPrice(obj){
    if(obj==1){
        $("#orderProceStatus").hide();
    }else{
        $("#orderProceStatus").show();
    }
}


//退款弹窗保存
function saveorderprice() {
    if($(".flag_saved").val()=="1"){
        return false;
    }else {
        var backLogStatus = $(".backLogStatus:checked");
        if (null == backLogStatus || backLogStatus.length == 0) {
            $(".remark_contents").text("请选择操作！");
            return;
        }
        var status = $("input[name='backLogStatus']:checked").val();
        var backPrice = $("input[name='backPrice']").val();
        if(status == 8 && backPrice==""){
            $(".remark_contents").text("请填写退款金额！");
            return;
        }
        if(status == 8){
            backorder("saveorderpriceOk()","你确定要退款吗？")
        }
        if(status == 9){
            backorder("saveorderpriceOk()","你确定要拒绝退款吗？")
        }
    }
}

function saveorderpriceOk(){
    $(".flag_saved").val("1");
    window.parent.$('#dialog-Back-price').modal('hide');
    $("#submitForm").submit();
}
/**
 * 弹框
 * @param deleteUrl 删除链接。
 */
function backorder(deleteUrl,text) {
    $("#modalDialog").remove();
    var confirmDialog =
        '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">'+
        '    <div class="modal-dialog">'+
        '        <div class="modal-content">'+
        '            <div class="modal-header">'+
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '               <h4 class="modal-title">操作提示</h4>'+
        '           </div>'+
        '           <div class="modal-body">'+
        '               '+text+
        '           </div>'+
        '           <div class="modal-footer">'+
        '               <button type="button" class="btn btn-primary" onclick="'+deleteUrl+';">确定</button>'+
        '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
        '           </div>'+
        '       </div>'+
        '   </div>'+
        '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}

function cancle() {
    window.parent.$('#dialog-Back-backCheck').modal('hide');
}
//关闭退款弹窗
function closeprice(){
    window.parent.$('#dialog-Back-price').modal('hide');
}

function fnModifybackCheckup(){
    if($("#modifyOrder").val()==""){
        return;
    }
    $("#updateOrderbackCheck").submit();
}

//确认收货
function getGoods(backId){
    if($("#businessId").val() == 0){
        $("#querenBackOrder").attr("action","modifyBackOrder.htm?backOrderId="+backId);
    }else{
        $("#querenBackOrder").attr("action","modifyBackOrderisthird.htm?backOrderId="+backId);
    }
    updateOrder("#querenBackOrder","是否确定收货？");
}

//取消订单
function  delOrder(backId){
    if($("#businessId").val() == 0){
        $("#delOrder").attr("action","modifyBackOrder.htm?backOrderId="+backId);
    }else{
        $("#delOrder").attr("action","modifyBackOrderisthird.htm?backOrderId="+backId);
    }
    updateOrder("#delOrder","订单不能随意取消，请确认!");
}

//退款审核
function  fnModifyBackmoney(backId){
    if($("#businessId").val() == 0){
        $("#updateOrderMoeny").attr("action","modifyBackOrder.htm?backOrderId="+backId);
    }else{
        $("#updateOrderMoeny").attr("action","modifyBackOrderisthird.htm?backOrderId="+backId);
    }
    $("#dialog-Back-Money").modal("show");
}
function fnModifyBackmoneyup(){
    if($("#modifyOrderMoney").val()==""){
        return;
    }
    $("#updateOrderMoeny").submit();
}
/**
 * 弹框
 * @param deleteUrl 删除链接。
 */
function updateOrder(deleteUrl,text) {
    $("#modalDialog").remove();
    var confirmDialog =
        '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">'+
        '    <div class="modal-dialog">'+
        '        <div class="modal-content">'+
        '            <div class="modal-header">'+
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '               <h4 class="modal-title">操作提示</h4>'+
        '           </div>'+
        '           <div class="modal-body">'+
        '               '+text+
        '           </div>'+
        '           <div class="modal-footer">'+
        '               <button type="button" class="btn btn-primary" onclick="doupdateorder(\''+deleteUrl+'\');">确定</button>'+
        '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
        '           </div>'+
        '       </div>'+
        '   </div>'+
        '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}
function doupdateorder(deleteUrl){
    $(deleteUrl).submit();
}

function submitOrderCode(){
    $("#backOrderCodestr").val($("#backOrderCode").val());
    $("#shippingPersonstr").val($("#shippingPerson").val());
    $("#shippingMobilestr").val($("#shippingMobile").val());
    $("#backCheckstr").val($("#up_backCheck").val());
    $("#ordercxform").submit();
}

function submitOrder(){
    $("#backOrderCodestr").val($("#backOrderCode").val());
    $("#shippingPersonstr").val($("#shippingPerson").val());
    $("#shippingMobilestr").val($("#shippingMobile").val());
    $("#backCheckstr").val($("#up_backCheck").val());
    $("#salesmanIdstr").val($("#salesmanId").val());
    $('#ordercxform').submit();
}

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

});