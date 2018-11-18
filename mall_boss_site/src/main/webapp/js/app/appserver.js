$(function(){
    /* 为选定的select下拉菜单开启搜索提示 END */
    $("#addAppServerForm").validate();
    $("#editAppServerForm").validate();
});
/**
 * 添加物流公司
 */
function submitAddAppServerForm() {
    $("#addAppServerForm").submit();
}
/**
 * 弹框显示物流公司编辑框
 * @param companyId 物流公司id
 */
function showEditAppServerForm(appServerId) {
    $("#appServerId").val(appServerId);
    var CSRFToken = $("#CSRFToken").val();
    $.ajax({
        url:'selectAppServerById.htm?CSRFToken='+CSRFToken+'&appServerId='+appServerId,
        success:function(data) {
            $("#appServerRoot").val(data.appServerRoot);
            $("#open"+data.appServerType).click();
            $("#isMain"+data.isMain).click();
        }
    });
    $('#editAppServerModal').modal('show')
}
/**
 * 修改物流公司信息
 */
function submitEditAppServerForm() {
    $("#editAppServerForm").submit();
}