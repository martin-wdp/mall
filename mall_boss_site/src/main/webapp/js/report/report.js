$(function() {
	$("#dialog-settle-tip").hide();
	$("#dialog-query-tip").hide();
	$(".time").click(function () {
		$("#endDate").val($("#today").val());
		$("#startDate").val($(this).attr("time_end"));
	});

	$(".time_exp").click(function () {

		$("#endDateExp").val($("#today").val());
		$("#startDateExp").val($(this).attr("time_end"));

	});

	$(".export_type").click(function() {
		if($(this).val()=='0') {
			$("#expReportForm").attr("action","exportSumReport.htm");
		} else if($(this).val()=='1') {
			$("#expReportForm").attr("action","exportCateReport.htm");
		}
	});






    //商家对账列表查询
	$("#search-report").click(function() {
            var storeName=$("#storeName").val();
            var totalOrderMoney=$("#totalOrderMoney").val();
			//小于1大于0的小数
			var re1 = /^[0-9]+\.{0,1}[0-9]{0,2}$/;

			if(totalOrderMoney!='' && !re1.test(totalOrderMoney)) {
                showTipAlert("请输入大于0,且小数点后不超过两位的数字!")
				return false;
			}
        $("#search-form").submit();

	});

    //商家分类对账查询
    $("#search-catereport").click(function() {
            var storeName=$("#cateName").val();
            var totalOrderMoney=$("#totalOrderMoney").val();
			//小于1大于0的小数
			var re1 = /^[0-9]+\.{0,1}[0-9]{0,2}$/;

			if(totalOrderMoney!='' && !re1.test(totalOrderMoney)) {
                showTipAlert("请输入大于0,且小数点后不超过两位的数字!")
				return false;
			}
        $("#search-cateform").submit();

	});




});

//检查是否选中一行
function checkSelected(objId,modifyFlag){
    checkedList = new Array();
    $("input[name='"+objId+"']:checked").each(function() {
        checkedList.push($(this).val());
    });
    if(modifyFlag!=0){
        if(checkedList.length ==1){
            return true;
        }else{
            return false;
        }
    }

    if(checkedList.length > 0){
        return true;
    }else{
        return false;
    }
};

// 批量删除报表
function delReportBatch(){
    if(checkSelected('storeIds',0)==false){
       showTipAlert("最少选择一行!");
        return false;
    }else{
        doAjaxShowDeleteBatchConfirmAlert("delReportForm","storeIds","deleReportByStoreId.htm","1","您确认要删除选中的信息吗?")
    }
}
//结算
function doSettlement(storeId){
    var url='settleReportByStoreId.htm?storeId='+storeId+"&CSRFToken="+$("#token").val()
        settlementout("doAjaxsettlement",url,"操作提示","您确认要改变报表状态为<已结算>吗?")
}


//结算弹出页面
function settlementout(methord,url,title,tips){

    $("#modalDialogout").remove();
    var confirmDialog =
        '<div class="modal fade" id="modalDialogout" tabindex="-1" role="dialog">'+
        '    <div class="modal-dialog">'+
        '        <div class="modal-content">'+
        '            <div class="modal-header">'+
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '               <h4 class="modal-title">'+title+'</h4>'+
        '           </div>'+
        '           <div class="modal-body">';
    if(tips!='' && tips!=undefined){
        confirmDialog +=tips;
    }else{
        confirmDialog +='确认进行该操作吗？';
    }
    confirmDialog += '           </div>'+
    '           <div class="modal-footer">'+
    '             <button type="button" class="btn btn-primary" onclick="'+methord+'(\''+url+'\')">确定</button>'+
    '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialogout\').modal(\'hide\');">取消</button>'+
    '           </div>'+
    '       </div>'+
    '   </div>'+
    '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialogout').modal('show');
}
 function doAjaxsettlement(url){

     $.ajax({
         url:url,
         success:function(data) {
             if(data==1) {
                 location.reload();
             }
         }
     });
 }
/**
 * 批量删除记录谈出框，以Ajax的形式
 * @param deleteFormId 表单id
 * @param name checkbox的name
 * @param url ajax地址
 * @param status 当前页面1：页面导航
 */
function doAjaxShowDeleteBatchConfirmAlert(deleteFormId,name,url,status,tips) {
    var checkboxs = $("input[name="+name+"]");
    var oneSelect = false;
    for(var j = 0; j < checkboxs.length; j++) {
        if($(checkboxs[j]).is(':checked')==true) {
            oneSelect = true;
        }
    }
    if(!oneSelect) {
        showTipAlert("请至少选择一条记录！");
        return;
    }
    $("#modalDialog").remove();
    var confirmDialog =
        '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">'+
        '    <div class="modal-dialog">'+
        '        <div class="modal-content">'+
        '            <div class="modal-header">'+
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '               <h4 class="modal-title">删除提示</h4>'+
        '           </div>'+
        '           <div class="modal-body">';
    if(tips!='' && tips!=undefined){
        confirmDialog +=tips;
    }else{
        confirmDialog +='确认要删除这条记录吗？';
    }
    confirmDialog += '           </div>'+
    '           <div class="modal-footer">'+
    '             <button type="button" class="btn btn-primary" onclick="doAjaxDeleteBatch(\''+deleteFormId+'\',\''+url+'\',\''+status+'\')">确定</button>'+
    '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
    '           </div>'+
    '       </div>'+
    '   </div>'+
    '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}
function doAjaxDeleteBatch(deleteFormId,formUrl,status) {
    $.ajax({
        cache: true,
        type: "POST",
        url: formUrl,
        data: $("#" + deleteFormId).serialize(),// 你的formid
        async: false,
        error: function (request) {
            showTipAlert("Connection error");
        },
        success: function (data) {
            if (status == 1) {

                $('#modalDialog').modal('hide');
                '${pb.url}${token }','${pb.pageSize}','${pb.nextPageNo}'
                window.location.href="checkReport.htm?CSRFToken="+$("#token").val()+"?pageNo="+$("#pageNo").val();
            }
        }
    });
}

//按天比较两个时间大小
function date_day(d1,d2){
    if (d1 != null && d2 != null && d1 != "" && d2 != "") {
        var qssj = new Date(d1.replace("-", "/").replace("-", "/"));
        var jssj = new Date(d2.replace("-", "/").replace("-", "/"));

        if(qssj>jssj){
            return false;
        }
        else{
            return true;
        }

    }
}


//按天时分秒比较两个时间大小
function date_hour_min(d1, d2) {

    if (d1 != null && d2 != null && d1 != "" && d2 != "") {
        var qssj = new Date(d1.replace("-", "/").replace("-", "/").replace(": ", "/").replace(": ", "/"));
        var jssj = new Date(d2.replace("-", "/").replace("-", "/").replace(": ", "/").replace(": ", "/"));
        if (qssj > jssj) {
            return false;
        }
        else {
            return true;
        }

    }
}
//导出报表
function excReport(){

    $("#expReportForm").submit();
    $("#outReport").modal("hide");
}
//生成报表
function submitExc(){


    $("#genReportForm").submit();

}
//展开详细信息 收回详细信息
function expandReportCate(obj,o,url,hei) {
	var divid="#expand"+obj;
	var expand = $(divid);
	if (expand.is(":hidden")) {
		$(divid +" td").html("");
		$(divid +" td").append("<iframe frameborder='0' scrolling='yes' width='100%' height='"+hei+"' src='"+url+"&storeId="+obj+"'></iframe>");
		expand.show("slow");
		o.src = "images/finder_up_arrow.gif";
	} else {
		o.src = "images/finder_drop_arrow.gif";
		$(divid +" td").html("");
		expand.hide();
	}
}


<!--全选-->
function selectAll(obj){
    var checkboxs = document.getElementsByName(obj);
    for (var i = 0; i < checkboxs.length; i++) {
        var e = checkboxs[i];
        e.checked = !e.checked;
    }
    for (var j = 0; j < checkboxs.length; j++) {
        if(checkboxs[j].checked){
            $(checkboxs[j]).parent().parent().addClass("trbcak");
        }else{
            $(checkboxs[j]).parent().parent().removeClass("trbcak");
        }
    }
}