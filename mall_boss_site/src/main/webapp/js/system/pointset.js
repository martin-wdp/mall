var CSRFToken = $("#CSRFToken").val();
var formItem,oldValue,inputId,attrType;
$(function(){
    /* 表单项的值点击后转换为可编辑状态 */
    $('.form_value').click(function(){
        if(formItem!=undefined) {
            $('.form_cancel').click();
        }
        formItem = $(this);
        oldValue = $(this).text();
        inputId = $(this).attr("attr_id");
        attrType = $(this).attr("attr_type");
        if(!$('.form_btns').is(':visible')) {
            formItem.parent().addClass('form_open');
            $('.form_btns').show();
            $('.form_btns').css({
                'left': formItem.parent().position().left + (formItem.next().find(".form-control").width()==null?formItem.next().width():formItem.next().find(".form-control").width())+65 + 'px',
                'top': formItem.parent().position().top - 30 + 'px'
            });
            //给输入控件加上验证的class
            $("#"+inputId).addClass($("#"+inputId).attr("clazz"));
        }
    });

    $('.form_sure').click(function () {
        if(!$("#editPointForm").valid()) return;
        var psetId = $("#psetId").val();
        var newValue = '';
        if("radio"==attrType) {
            newValue =$('input[name="'+inputId+'"]:checked').val();
        } else {
            newValue = $("#"+inputId).val();
        }

        $.ajax({
            url:'updatpointset.htm?'+inputId+'='+newValue+"&psetId="+psetId+"&CSRFToken="+CSRFToken,
            success:function(data) {
                if("radio"==attrType) {
                    $(formItem).html(newValue=='0'?'<span class="label label-default">否</span>':'<span class="label label-success">是</span>');
                    oldValue = newValue=='0'?'否':'是';
                } else {
                    $(formItem).html(newValue);
                }
            }
        });
        $('.form_btns').hide();
        formItem.parent().removeClass('form_open');
        window.location.reload();
    });
    $('.form_cancel').click(function () {

        if("radio"==attrType) {
            if(oldValue.trim()=='否') {
                $("input[name="+inputId+"]:eq(1)").click();
            } else {
                $("input[name="+inputId+"]:eq(0)").click();
            }
        } else {
            $("#"+inputId).val(oldValue);
        }
        $('.form_btns').hide();
        formItem.parent().removeClass('form_open');
        $("#"+inputId).removeClass($("#"+inputId).attr("clazz"));
    });

});