/**
 * Created by Zhuoy on 2015/5/5.
 */

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
                    }else if(order.orderStatus == '7'){
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
                    $("#detailcouponNo").html("使用优惠券："+notNull(order.couponNo));
                    $("#detailorderOldPrice").html("订单原始金额："+order.orderOldPrice.toFixed(2));
                    $("#detailorderPrePrice").html("订单优惠金额："+order.orderPrePrice.toFixed(2));
                    $("#detailorderPrice").html(order.orderPrice.toFixed(2));
                    if(order.orderIntegral == null){
                        $("#detailorderIntegral").html("积分：0");
                    }else{
                        $("#detailorderIntegral").html("积分："+order.orderIntegral);
                    }
                    //物流信息
                    if(order.orderExpress.expressTypeId==0){
                        $("#detailorderExpress").html("配送方式：快递配送");
                    }else if(order.orderExpress.expressTypeId==1){
                        $("#detailorderExpress").html("配送方式：上门自提");
                    }
                    $("#detailexpressPrice").html("运费："+order.expressPrice.toFixed(2));
                    
                    //收货信息
                    $("#detailshippingaddress").html("收货地址："+notNull(order.shippingProvince)+notNull(order.shippingCity)+notNull(order.shippingCounty));
                    $("#detailaddress").html("详细地址："+notNull(order.shippingAddress));
                    $("#detailshippingPerson").html("收货人："+notNull(order.shippingPerson));
                    $("#detailshippingPhone").html("联系电话："+notNull(order.shippingPhone));
                    $("#detailshippingMobile").html("手机："+notNull(order.shippingMobile));
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

function ssorder(){
    $("#orderCodestr").val($("#orderCode").val());
    $("#shippingPersonstr").val($("#shippingPerson").val());
    $("#shippingMobilestr").val($("#shippingMobile").val());
    $("#printoverlist").submit();
}

//拣货
function pickingOrderIds(){
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
    $(".order_p_id").attr("value",checkGroup);
    if(bool){
        $.ajax({
            type : "POST",
            url:"orderpickingajax.htm?orderId=" + checkGroup+"&CSRFToken="+$("#csrf_token").val(),
            success:function(data){
                $("#orderGoodsInfolist").html("");
                if(data != null && data != undefined && data != ""){
                    var orderGoodsInfos = data.orderGoodsInfos;
                    var str= '';
                    if(orderGoodsInfos != null && orderGoodsInfos != undefined && orderGoodsInfos != null){
                        str += '<div class="mb20"><div class="border_bottom mb10 row">'+
                        '<div class="col-sm-1 col-xs-1"><p>序号</p></div>'+
                        '<div class="col-sm-3 col-xs-3"> <p>商品编号</p> </div>'+
                        '<div class="col-sm-11 col-xs-11"><p class="text-center">商品名称</p></div>'+
                        '<div class="col-sm-3 col-xs-3"> <p class="text-center">数量</p> </div>'+
                        '<div class="col-sm-4 col-xs-4"> <p>商品属性</p> </div>'+
                        '<div class="col-sm-2 col-xs-2"> <p>价格</p> </div>'+
                        '</div>';
                        $.each(orderGoodsInfos,function(idx,item){
                            str += '<div class="border_bottom mb10 row"><div class="col-xs-1"><p>'+(idx+1)+'</p></div>';
                            str += '<div class="col-xs-3"><p>'+item.goodsProductVo.goodsInfoItemNo+'</p></div>';
                            str += '<div class="col-xs-11"><p>'+item.goodsProductVo.goodsInfoName+'</p></div>';
                            str += '<div class="col-xs-3"><p class="text-center">'+item.goodsInfoNum+'</p></div>';
                            str += '<div class="col-xs-4"><p>';
                            if(item.goodsProductVo.goodsSpecVo != null){
	                            $.each(item.goodsProductVo.goodsSpecVo,function(idxs,specVo){
	                                str+= specVo.specName+':'+specVo.specValue+'</br>';
	                            });
                            }
                            str+='</p></div>';
                            str += '<div class="col-xs-2"><p>'+item.goodsInfoPrice+'</p></div></div>';
                        });
                        str += '</div>';
                    }
                    
                    $("#orderGoodsInfolist").html(str);
                }
                $('#pickUp').modal('show')
            }
        });
    }else{
        showTipAlert("请选择一行");
    }

}

/**
 * 打印
 */


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

function pickingOrderId(id){
    var checkGroup = new Array();
    checkGroup.push(id);
    $(".order_p_id").attr("value",checkGroup);
        $.ajax({
            type : "POST",
            url:"orderpickingajax.htm?orderId=" + checkGroup+"&CSRFToken="+$("#csrf_token").val(),
            success:function(data){
                $("#orderGoodsInfolist").html("");
                if(data != null && data != undefined && data != ""){
                    var orderGoodsInfos = data.orderGoodsInfos;
                    var str= '';
                    if(orderGoodsInfos != null && orderGoodsInfos != undefined && orderGoodsInfos != null){
                        str += '<div class="mb20"><div class="border_bottom mb10 row">'+
                        '<div class="col-sm-1 col-xs-1"><p>序号</p></div>'+
                        '<div class="col-sm-3 col-xs-3"> <p>商品编号</p> </div>'+
                        '<div class="col-sm-11 col-xs-11"><p class="text-center">商品名称</p></div>'+
                        '<div class="col-sm-3 col-xs-3"> <p class="text-center">数量</p> </div>'+
                        '<div class="col-sm-4 col-xs-4"> <p>商品属性</p> </div>'+
                        '<div class="col-sm-2 col-xs-2"> <p>价格</p> </div>'+
                        '</div>';
                        $.each(orderGoodsInfos,function(idx,item){
                            str += '<div class="border_bottom mb10 row"><div class="col-xs-1"><p>'+(idx+1)+'</p></div>';
                            str += '<div class="col-xs-3"><p>'+item.goodsProductVo.goodsInfoItemNo+'</p></div>';
                            str += '<div class="col-xs-11"><p>'+item.goodsProductVo.goodsInfoName+'</p></div>';
                            str += '<div class="col-xs-3"><p class="text-center">'+item.goodsInfoNum+'</p></div>';
                            str += '<div class="col-xs-4"><p>';
                            $.each(item.goodsProductVo.goodsSpecVo,function(idxs,specVo){
                                str+= specVo.specName+':'+specVo.specValue+'</br>';
                            });
                            str+='</p></div>';
                            str += '<div class="col-xs-2"><p>'+item.goodsInfoPrice+'</p></div></div>';
                        });
                        str += '</div>';
                    }
                    
                    $("#orderGoodsInfolist").html(str);
                }
                $('#pickUp').modal('show')
            }
        });
}