$(function(){
    /* 表单项的值点击后转换为可编辑状态 */
    $('.form_value').click(function(){
        var formItem = $(this);
        if(!$('.form_btns').is(':visible')) {
            formItem.parent().addClass('form_open');
            $('.form_btns').show();
            $('.form_btns').css({
                'left': formItem.parent().position().left + (formItem.next().find(".form-control").width()==null?formItem.next().width():formItem.next().find(".form-control").width())+65 + 'px',
                'top': formItem.parent().position().top - 30 + 'px'
            });
            $('.form_sure,.form_cancel').click(function () {
                $('.form_btns').hide();
                formItem.parent().removeClass('form_open');
            });
        }
    });
    $("#editMessageForm").validate();
});
/**
 * 弹出显示短信接口设置
 * @param smsId 短信接口主键id
 * @param obj 编辑按钮
 */
function updateSms(smsId,obj){
    $("#smsId").val(smsId);
    $("#smsAddress").val($(obj).attr("smsAddress"));
    $("#smsProvider").val($(obj).attr("smsProvider"));
    $("#smsName").val($(obj).attr("smsName"));
    $("#smsUrl").val($(obj).attr("smsUrl"));
    $("#smsPass").val($(obj).attr("smsPass"));
    $("#smsGateway").val($(obj).attr("smsGateway"));
    if($(obj).attr("isOpen")=='1') {
        $("#option1").click();
    } else {
        $("#option2").click();
    }
    $('#editMessage').modal('show')
}
/**
 * 验证并提交编辑表单
 */
function submitMessageForm() {
    $("#editMessageForm").submit();
}