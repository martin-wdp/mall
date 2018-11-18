$(function () {
    $("#editPaymentForm").validate();
    $("#addPaymentForm").validate();
});
/**
 * 弹框显示支付方式编辑框
 * @param obj 编辑按钮
 */
function showEditPayment(obj) {
    $("#paymentId").val($(obj).attr("payment_id"));
    $("#payname").val($(obj).attr("payment_name"));
    var isOpen = $(obj).attr("is_open");
    if(isOpen=='1') {
        $("#open1").click();
    } else {
        $("#open2").click();
    }
    $('#editPayment').modal('show');
}

/**
 * 提交表单
 * @param formId 表单id
 */
function submitPaymentForm(formId) {
    $("#"+formId).submit();
}
/**
 * 改变启用状态
 * @param payId 支付方式id
 */
function changeUserdStatus(paymentId){
    $.ajax({
        type:"POST",
        url:"ajaxCheckOpenCount.htm?CSRFToken="+$("#CSRFToken").val(),
        data:"paymentId="+paymentId,
        success:function(data){
            if(data){
                location.href = "updateOpenStatus.htm?CSRFToken=${token}&paymentId="+paymentId;
            }else{
                showTipAlert("请保留一个已启用的支付方式！");
            }
        }
    });
}