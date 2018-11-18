var CSRFToken = $("#CSRFToken").val();
var formItem,oldValue;
$(function(){
    /* 表单项的值点击后转换为可编辑状态 */
    $('.form_value').click(function(){
        if(formItem!=undefined) {
            $('.form_cancel').click();
        }
        formItem = $(this);
        oldValue = $(this).text();
        var flag = "";
        if(!$('.form_btns').is(':visible')) {
            formItem.parent().addClass('form_open');
            if (formItem.attr("attr_flag") == 1) {
                $('.form_btns2').show();
                flag = '.form_btns2';
            }
            if (formItem.attr("attr_flag") == 2) {
                $('.receipt_btns').show();
                flag = '.receipt_btns';
            }
            if (formItem.attr("attr_flag") == 3) {
                $('.form_btns').show();
                flag = '.form_btns';
            }
            $(flag).css({
                'left': formItem.parent().position().left + (formItem.next().find(".form-control").width()==null?formItem.next().width():formItem.next().find(".form-control").width())+65 + 'px',
                'top': formItem.parent().position().top - 30 + 'px'
            });

        }
    });
    $('.form_cancel').click(function () {
        if(oldValue.trim()=='否') {
            $("input[name=isBackOrder]:eq(1)").click();
        } else {
            $("input[name=isBackOrder]:eq(0)").click();
        }
        $('.form_btns2').hide();
        $('.form_btns').hide();
        $('.receipt_btns').hide();
        formItem.parent().removeClass('form_open');
    });
    $('.btn-save').click(function() {
        var newValue =$('input[name="isBackOrder"]:checked').val();
        $.post(
            "updatesetBackOrder.htm?CSRFToken="+CSRFToken,
            {
                isBackOrder:$('input[name="isBackOrder"]:checked').val(),
                setId:$("#setId").val(),
                CSRFToken:CSRFToken
            },
            function(data){
                if(data.result==1){
                    $(formItem).html(newValue=='1'?'<span class="label label-success">是</span>':'<span class="label label-default">否</span>');
                }else{
                    $(formItem).html(oldValue=='1'?'<span class="label label-success">是</span>':'<span class="label label-default">否</span>');
                }
            }
        );
        $('.form_btns2').hide();
        formItem.parent().removeClass('form_open');
        window.location.reload();
    });
    $('.btn-time-save').click(function() {
        if(!isNaN($("#bsetOrderTime").val())&&$("#bsetOrderTime").val() != "") {
            $.post(
                "updatesetOrderTime.htm?CSRFToken="+CSRFToken,
                {
                    bsetOrderTime:$("#bsetOrderTime").val()
                },
                function(data){
                    if(data.result==1){
                        $(formItem).text($("#bsetOrderTime").val()+'小时');
                    }
                });
        }
        $('.form_btns').hide();
        formItem.parent().removeClass('form_open');
        //  window.location.reload()
    })
    $('.btn-receipt-save').click(function() {
        if(!isNaN($("#receiptTime").val())&&$("#receiptTime").val() != "") {
            $.post(
                "updatesetBackOrder.htm?CSRFToken="+CSRFToken,
                {
                    receiptTime:$("#receiptTime").val(),
                    setId:$("#setId").val()
                },
                function(data){
                    if(data.result==1){
                        $(formItem).text($("#receiptTime").val()+'天');
                    }
                });
        }
        $('.receipt_btns').hide();
        formItem.parent().removeClass('form_open');
        //  window.location.reload()
    })
    /* 富文本编辑框 */
    $('.summernote').summernote({
        height: 300,
        tabsize: 2,
        lang: 'zh-CN',
        onImageUpload: function(files, editor, $editable) {
            sendFile(files,editor,$editable);
        }
    });
});

function saveOrderRemark(textid,obj) {
    var sHtml = $("#"+textid).code();
    var arg = {};
    arg[textid] = sHtml;
    arg['CSRFToken']=CSRFToken;
    $.ajax({
        url:'updatebackremark.htm',
        type:'POST',
        data:arg,
        success:function(result) {
            $(obj).next().click();
        }
    });
}