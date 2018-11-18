//模板页面
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

function doAjaxDeleteBatch(deleteFormId,formUrl,status){
    $.ajax({
        cache: true,
        type: "POST",
        url:formUrl,
        data:$("#"+deleteFormId).serialize(),// 你的formid
        async: false,
        error: function(request) {
            showTipAlert("Connection error");
        },
        success: function(data) {
            if(status==1){
                showTempBar();
            }else if(status==2){
                var atId=$(upAtId).val();
                showAdverPage(atId);
            }else if(status==3){
                showHotSearch();
            }else if(status==4){
                shoMegawizard();
            }else if(status==5){
                if($("#storeyTagId").val()!=""){
                    showTagyGoods();
                }else{
                    showStoreyGoods();
                }
            }else if(status==6){
                showTempStorey();
            }else if(status==7){
                showChannelStoreyGoods();
            }else if(status==8){
                showfloorAdverPage();
            }else if(status==9){
                if($("#isBrand").val()==4){
                    showTagTrademark();
                }else{
                    showStoreyTrademark();
                }
            }else if(status==11){
                if($("#isAdver").val()==5){
                    showTagAdverPage();
                }else{
                    showClassifyBarAdverPage();
                }
            }else if(status==12){
                if($("#isBrand").val()==1){
                    showClassifyStoreyTrademark();
                }else if( $("#isBrand").val()==2){
                    showClassifyStoreyTrademarkParet();
                }
            }else if(status==13){
                showClassifyParentBarAdverPage();
            }else if(status==14){
                if( $("#tag_storeyId").val()!=""){
                    showHTagList();
                }else{
                    showTagList();
                }
            }else if(status==15){
                storeliststreet();
            }
            $('#modalDialog').modal('hide');
        }
    });
}





function doAjaxDeleteOne(deleteUrl,status) {
    $.ajax({
        url: deleteUrl,
        success: function (data) {
            if(status==1){
                showTempBar();
            }else if(status==2){
                var atId=$(upAtId).val();
                showAdverPage(atId);
            }else if(status==3){
                showHotSearch();
            }else if(status==4){
                shoMegawizard();
            }else if(status==5){
                if($("#storeyTagId").val()!=""){
                    showTagyGoods();
                }else{
                    showStoreyGoods();
                }

            }else if(status==6){
                showTempStorey();
            }else if(status==7){
                showChannelStoreyGoods();
            }else if(status==8){
                showfloorAdverPage();
            }else if(status==9){
                if($("#isBrand").val()==4){
                    showTagTrademark();
                }else{
                    showStoreyTrademark();
                }
            }else if(status==10){
                var option = {
                    theme:'vsStyle',
                    expandLevel : 1,//从哪一级开始收缩
                    column : 1
                };
                $('#treeTable').treeTable(option);
                $("#goodsCateHtml").html('<tr><td colspan="7"><p class="text-center">加载中</p> </td></tr>');
                doAjaxForCate(1,10000);
            }else if(status==11){
                if($("#isAdver").val()==5){
                    showTagAdverPage();
                }else{
                    showClassifyBarAdverPage();
                }
            }else if(status==12){
                if($("#isBrand").val()==1){
                    showClassifyStoreyTrademark();
                }else if( $("#isBrand").val()==2){
                    showClassifyStoreyTrademarkParet();
                }
            }else if(status==13){
                showClassifyParentBarAdverPage();
            }else if(status==14){
                showDeleteOneConfirmAlert();
                //店铺街 大图片
            }else if(status==15){
                storeliststreet();
            }

            $('#modalDialog').modal('hide');
        }
    });
}



/**
 * 删除单个记录的确认框
 * @param deleteUrl 删除链接。
 */
function showAjaxDeleteConfirmAlertByTemp(deleteUrl,status,tips) {
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
    '               <button type="button" class="btn btn-primary" onclick="doAjaxDeleteOne(\''+deleteUrl+'\',\''+status+'\');">确定</button>'+
    '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
    '           </div>'+
    '       </div>'+
    '   </div>'+
    '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}

