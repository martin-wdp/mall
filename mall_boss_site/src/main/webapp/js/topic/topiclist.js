var checkedList;

/**
 * 搜索
 *
 * */
function advancesearchtopic(){
    $("#advancedpageNo").val("1");
    $("#advancedpageSize").val("10");
    $("#advancedForm").submit();
}

/**
 * 批量删除记录谈出框，
 * @param
 * @param name checkbox的name
 */
function showDeleteBatchConfirmAlerts(name,tips) {
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
    '             <button type="button" class="btn btn-primary" onclick="deleteMultiWin(\'deletegrouptopic.htm\',\'publictopic.htm\')">确定</button>'+
    '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
    '           </div>'+
    '       </div>'+
    '   </div>'+
    '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}

var checkedList;
/**
 *
 * 验证是否选中
 * */
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


/**
 * 批量删除 通用
 * @param url 			删除路径
 * @param initUrl   	成功跳转路径
 */
function deleteMultiWin(url,initUrl) {
    checkSelected('topicId',0);
    $.post(url,{topicId:checkedList},function(result){
        if (result > 0){
            location.href=initUrl;
        } else {

        }
    },'json');
}

/**
 * 单个记录删除
 * @param url    删除路径
 * @param initUrl  成功后跳转路径
 * @param topicId  删除标识
 * */
function deleteMultiWinOne(url,initUrl,topicId) {
    checkedList=new Array();
    checkedList.push(topicId);
    $.post(url,{topicId:checkedList},function(result){
        if (result > 0){
            location.href=initUrl;
        } else {

        }
    },'json');
}

/**
 *
 * 查看详情
 * */
function topicDetail(topicContent){
    $('#topicContent').text(topicContent);
    $('#scanTopic').modal('show');
}

/**
 * 根据groupLabelId查询出数据，并绑定到修改页面
 * @param groupLabelId    标签id
 * */
function topicEdit(topicId){
    modified=1;
    $.post("privatetopicdetailNew.htm?topicId="+topicId,function(data){
        $("#topicId").val(data.topicId);
        $("#topicTitle").val(data.topicTitle);
        $("#customerName").text(data.customerName);
        $("#groupName").text(data.groupName);
        $(".note-editable").text(data.topicContent)
        if(data.topicEssence==0){
            $('#topicEssence1').prop('checked', 'checked');
        }else{
            $('#topicEssence2').prop('checked', 'checked');
        }
        if(data.topicFever==0){
            $('#topicFever1').prop('checked', 'checked');
        }else{
            $('#topicFever2').prop('checked', 'checked');
        }
        if(data.topicTopView==0){
            $('#topicTopView1').prop('checked', 'checked');
        }else if(data.topicTopView==1){
            $('#topicTopView2').prop('checked', 'checked');
        }else{
            $('#topicTopView3').prop('checked', 'checked');
        }
        $("#topicSeoTitle").val(data.topicSeoTitle);
        $("#topicSeoKeyword").val(data.topicSeoKeyword);
        $("#topicSeoDescription").val(data.topicSeoDescription);
        $("#editTopic").modal('show');
    });
}

function editTopic(){
    var topicIds=$('#topicId').val();
    var topicTitles=$('#topicTitle').val();
    var customerNames=$('#customerName').text();
    var groupNames=$('#groupName').text();
    var topicEssences=$('input:radio[name="topicEssence"]:checked').val();
    var topicFevers=$('input:radio[name="topicFever"]:checked').val();
    var topicTopViews=$('input:radio[name="topicTopView"]:checked').val();
    var topicSeoTitles=$('#topicSeoTitle').val();
    var topicSeoKeywords=$('#topicSeoKeyword').val();
    var topicSeoDescriptions=$('#topicSeoDescription').val();
    var topicContents=$('.note-editable').text();
    if($("#editTopicForm").valid()){
            $.ajax({
                url:'savegrouptopic.htm',
                type:'post',
                data:{topicId:topicIds,topicTitle:topicTitles,customerName:customerNames,groupName:groupNames,
                    topicEssence:topicEssences,topicFever:topicFevers,topicTopView:topicTopViews,
                    topicSeoTitle:topicSeoTitles,topicSeoKeyword:topicSeoKeywords,
                    topicSeoDescription:topicSeoDescriptions,topicContent:topicContents},
                success:function(data){
                    if(data > 0){
                        location.href="publictopic.htm";
                    }else{
                        alert("修改失败！");
                    }
                }
            });
        }
}

//验证特殊字符，将调试返回给调用者
function checkSpecSymbNew(inputobj){
    var regexp=new RegExp("[''\\[\\]<>?!]");
    if (regexp.test( $("#"+inputobj).val() ) ) {
        $("#"+inputobj).addClass( "ui-state-error" );
        return false;
    }else {
        $("#"+inputobj).removeClass( "ui-state-error" );
        return true;
    }
}