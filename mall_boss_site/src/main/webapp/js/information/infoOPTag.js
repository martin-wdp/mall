/**
 * Created by Zhuoy on 2015/4/2.
 */
//删除提示框
function delinfoOpTagYN(infoopTagId){
    $("#delinfoopTagId").val(infoopTagId);
    $("#delinfoOPTag").modal("show");
}

function delinfoOpTag(){
    $.ajax({
        url:"checkDelTag.htm?CSRFToken="+$("#CSRFToken").val(),
        data:{infoOPTagId:$("#delinfoopTagId").val()},
        success:function(data){
            location='delInfoOPTag.htm?infoOPTagId='+$("#delinfoopTagId").val()+'&CSRFToken='+$("#CSRFToken").val();
        },
        async: false
    });
}

//添加
var num=0;
function addinfoOPTag(){
    if($("#addinfoOPTag").valid()&&num==0){
        num+=1;
        $("#addinfoOPTag").submit();
    }
}

//修改弹框
function updateInfoOPTagModal(infoopTagId){
    $("#up_infoopTagId").val(infoopTagId);
    $("#up_sort").val($("#sort_"+infoopTagId).html());
    $("#up_name").val($("#name_"+infoopTagId).html());
    $("#up_temp").val($("#temp_"+infoopTagId).val());
    $("#updatePageTag").modal("show");
}
//修改
function updateinfoOPTag(){
    if($("#updateinfoOPTag").valid()){
        $("#updateinfoOPTag").submit();
    }
}
