/**
 * Created by Zhuoy on 2015/4/14.
 */
var num=0;
function saveinfoUD(){
    if($("#infoUDForm").valid()&&num==0){
        num+=1;
        $("#infoUDForm").submit();
    }
}

function updateModal(infoUDid){
    $("#up_infoUdId").val(infoUDid);
    $("#up_sort").val($("#sort_"+infoUDid).html());
    $("#up_name").val($("#name_"+infoUDid).html());
    $("#updateArticleProperty").modal("show");
}

function updateinfoUD(){
    if($("#up_infoUDForm").valid()){
        $("#up_infoUDForm").submit();
    }
}

function delModel(delinfoUdId){
    $("#delinfoUdId").val(delinfoUdId);
    $("#delinfoUserDefined").modal("show");
}

function delinfoUserDefined(){
    location.href = "delInfoUserDefined.htm?CSRFToken="+$("#CSRFToken").val()+"&infoUDId="+$("#delinfoUdId").val();
}