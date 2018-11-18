/**
 * 删除单个记录的确认框
 * @param deleteUrl 删除链接。
 */
function showDeleteOneConfirmAlert(deleteUrl,tips) {
    $("#modalDialog").remove();
    var confirmDialog =
    '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">'+
    '    <div class="modal-dialog">'+
    '        <div class="modal-content">'+
    '            <div class="modal-header">'+
    '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
    '               <h4 class="modal-title">系统提示</h4>'+
    '           </div>'+
    '           <div class="modal-body">';
    if(tips!='' && tips!=undefined){
        confirmDialog +=tips;
    }else{
        confirmDialog +='确认要删除这条记录吗？';
    }
    confirmDialog += '           </div>'+
    '           <div class="modal-footer">'+
    '               <button type="button" class="btn btn-primary" onclick="doAjaxDeleteOne(\''+deleteUrl+'\');">确定</button>'+
    '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
    '           </div>'+
    '       </div>'+
    '   </div>'+
    '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}

/**
 * 批量删除记录谈出框，以表单的形式
 * @param deleteFormId 表单id
 * @param name checkbox的name
 */
function showDeleteBatchConfirmAlert(deleteFormId,name,tips) {
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
    '             <button type="button" class="btn btn-primary" onclick="doDeleteBatch(\''+deleteFormId+'\')">确定</button>'+
    '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
    '           </div>'+
    '       </div>'+
    '   </div>'+
    '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}

/**
 * 删除单个记录的确认框
 * @param deleteUrl 删除链接。
 */
function showNoDeleteConfirmAlert(tips) {
    $("#modalDialog").remove();
    var confirmDialog =
    '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">'+
    '    <div class="modal-dialog">'+
    '        <div class="modal-content">'+
    '            <div class="modal-header">'+
    '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
    '               <h4 class="modal-title">系统提示</h4>'+
    '           </div>'+
    '           <div class="modal-body">'+tips+
    '           </div>'+
    '           <div class="modal-footer">'+
    '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
    '           </div>'+
    '       </div>'+
    '   </div>'+
    '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}

function doDeleteOne(deleteUrl) {
    location.href=deleteUrl;
}

/**
 * 批量删除记录，以表单的形式
 * @param deleteFormId 表单id
 */
function doDeleteBatch(deleteFormId) {
    $("#"+deleteFormId).submit();
}
/**
 * 提示框
 * @param tip 提示内容
 */
function showTipAlert(tip) {
    $("#modalDialog").remove();
    var dialogHtml =
        '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog" style="z-index:99999;">'+
        '    <div class="modal-dialog">'+
        '        <div class="modal-content">'+
        '            <div class="modal-header">'+
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '               <h4 class="modal-title">操作提示</h4>'+
        '           </div>'+
        '           <div class="modal-body" style="text-align: center;">'+
        tip+
        '           </div>'+
        '           <div class="modal-footer">'+
        '               <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">确定</button>'+
        '           </div>'+
        '       </div>'+
        '   </div>'+
        '</div>';
    $(document.body).append(dialogHtml);
    $('#modalDialog').modal('show');
}

/**
 * ajax删除确认框，删除后，会刷新当前页面
 * @param deleteUrl 删除链接
 */
function showAjaxDeleteConfirmAlert(deleteUrl) {
    showDeleteOneConfirmAlert(deleteUrl);
}

function doAjaxDeleteOne(deleteUrl) {
    if(deleteUrl!=null){
        deleteUrl = deleteUrl+"&searchText="+$('input[name="searchText"]').val();
    }
    $.ajax({
        url:deleteUrl,
        success: function (data) {
            location.reload();
        }
    });
}

function doCallBackAjaxDelete(deleteUrl) {
    $.ajax({
        url:deleteUrl,
        success: function (data) {
            $("#modalDialog").modal("hide");
            delteCallback();
        }
    });
}


/**
 * 批量删除记录谈出框，以表单的形式
 * @param deleteFormId 表单id
 * @param name checkbox的name
 */
function showAjaxDeleteBatchConfirmAlert(deleteFormId,name,tips) {
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
    var url = $("#"+deleteFormId).attr("action")+"?"+$("#"+deleteFormId).serialize();
    var confirmDialog =
        '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">'+
        '    <div class="modal-dialog">'+
        '        <div class="modal-content">'+
        '            <div class="modal-header">'+
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '               <h4 class="modal-title">操作提示</h4>'+
        '           </div>'+
        '           <div class="modal-body">';
    if(tips!='' && tips!=undefined){
        confirmDialog +=tips;
    }else{
        confirmDialog +='确认要删除这条记录吗？';
    }
    confirmDialog += '           </div>'+
    '           <div class="modal-footer">'+
    '             <button type="button" class="btn btn-primary" onclick="doAjaxDeleteOne(\''+url+'\')">确定</button>'+
    '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
    '           </div>'+
    '       </div>'+
    '   </div>'+
    '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}


/**
 * 删除单个记录的确认框,只删除，删除后不做任何事
 * @param deleteUrl 删除链接。
 */
function showOnlyDeleteConfirmAlert(deleteUrl,tips) {
    $("#modalDialog").remove();
    var confirmDialog =
        '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">'+
        '    <div class="modal-dialog">'+
        '        <div class="modal-content">'+
        '            <div class="modal-header">'+
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '               <h4 class="modal-title">系统提示</h4>'+
        '           </div>'+
        '           <div class="modal-body">';
    if(tips!='' && tips!=undefined){
        confirmDialog +=tips;
    }else{
        confirmDialog +='确认要删除这条记录吗？';
    }
    confirmDialog += '           </div>'+
    '           <div class="modal-footer">'+
    '               <button type="button" class="btn btn-primary" onclick="doCallBackAjaxDelete(\''+deleteUrl+'\');">确定</button>'+
    '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
    '           </div>'+
    '       </div>'+
    '   </div>'+
    '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}

function checkselect(name,modifyFlag){
	var checkedList = new Array();
	var checkboxs = $("input[name="+name+"]");
    var oneSelect = false;
    for(var j = 0; j < checkboxs.length; j++) {
        if($(checkboxs[j]).is(':checked')==true) {
        	checkedList.push($(checkboxs[j]).val());
            oneSelect = true;
        }
    }
    if(modifyFlag!=0){
    	if(checkedList.length ==1){
    		return true;
    	}else{
    		showTipAlert("请先选择一条记录！");
    		return false;
    	}
    }
    if(!oneSelect) {
        showTipAlert("请先选择一条记录！");
        return false;
    }else{
       return true;
    }
}

/**
 * 简单的确认框，返回true或false
 */
function showConfirmAlert(tips,functionName) {
    $('#modalDialog').remove();
    var confirmDialog =
        '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">'+
        '    <div class="modal-dialog">'+
        '        <div class="modal-content">'+
        '            <div class="modal-header">'+
        '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
        '               <h4 class="modal-title">确认提示</h4>'+
        '           </div>'+
        '           <div class="modal-body">'
        +tips+
        '           </div>'+
        '           <div class="modal-footer">'+
        '             <button type="button" class="btn btn-primary" onclick="'+functionName+'">确定</button>'+
        '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
        '           </div>'+
        '       </div>'+
        '   </div>'+
        '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}
