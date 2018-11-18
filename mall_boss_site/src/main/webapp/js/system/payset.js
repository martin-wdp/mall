$(function(){
	    /* 富文本编辑框 */
	    $('.summernote').summernote({
	        height: 300,
	        tabsize: 2,
	        lang: 'zh-CN',
	        onImageUpload: function(files, editor, $editable) {
	            sendFile(files,editor,$editable);
	        }
	    });
	
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

    $("#editForm1").validate();
    $("#editForm2").validate();
    $("#editForm3").validate();
});
/**
 * 修改启用状态
 * @param payId 支付接口id
 */
function changeUserdStatus(payId){
    var CSRFToken = $("#CSRFToken").val();
    location.href = "updateUserdStatusForPay.htm?CSRFToken="+CSRFToken+"&payId="+payId;
}
/**
 * 修改默认状态
 * @param payId 支付接口id
 */
function changeDefault(payId){
    var CSRFToken = $("#CSRFToken").val();
    location.href = "changeDefaultForPay.htm?CSRFToken="+CSRFToken+"&payId="+payId;
}
/**
 * 弹框显示支付接口设置
 * @param payType 支付类型
 * @param payId 支付接口id
 */
function showEditForm(payType,payId) {
    $("#payId"+payType).val(payId);
    $.ajax({
        type:"POST",
        url:"findpayone.htm?CSRFToken="+$("#CSRFToken").val(),
        data:"payid="+payId,
        success:function(data){
            //填充值
            $("#up_payid"+payType).val(data.payId);
            $("#up_payname"+payType).val(data.payName);
            $("#up_apikey"+payType).val(data.apiKey);
            $("#up_secrect"+payType).val(data.secretKey);
            $("#up_account"+payType).val(data.payAccount);
            $("#up_payurl"+payType).val(data.payUrl);
            $("#up_backurl"+payType).val(data.backUrl);
            $("#up_comment"+payType).val(data.payComment);


            $("#up_partner"+payType).val(data.partner);
            $("#up_partnerKey"+payType).val(data.partnerKey);

            $("#preview_pic"+payType).attr("src",data.payImage);
            $("#payHelp").html(data.payHelp);
            $("#payHelp").code(data.payHelp);

            if(data.payDefault==1) {
                $("#open"+payType+"1").click();
            } else {
                $("#open"+payType+"2").click();
            }
            if(data.isOpen==1) {
                $("#open"+payType+"3").click();
            } else {
                $("#open"+payType+"4").click();
            }
            $("#editModal"+payType).modal("show");
        }
    });
}

function submitEditForm(payType) {
    $("#editForm"+payType).submit();
}

function editpayhelp(payType){
	var payId =  $("#payId"+payType).val();
	$("#helpPayId").val(payId);
	$('#rightinfo').modal('show');
}

function savePayHelp(textid,obj){
	var CSRFToken = $("#CSRFToken").val();
	var sHtml = $("#"+textid).code();
    var arg = {};
    arg[textid] = sHtml;
    arg['payId'] =$("#helpPayId").val();
    arg['CSRFToken']=CSRFToken;
    $.ajax({
        url:'updatepayhelp.htm',
        type:'POST',
        data:arg,
        success:function(result) {
            $(obj).next().click();
        }
    });
}
