var formItem,oldValue;
$(function(){
    /* 表单项的值点击后转换为可编辑状态 */
    $('.form_value').click(function(){
        if(formItem!=undefined) {
            $('.form_cancel').click();
        }
        formItem = $(this);
        oldValue = $(this).text();
        if(!$('.form_btns').is(':visible')) {
            formItem.parent().addClass('form_open');
            $('.form_btns').show();
            $('.form_btns').css({
                'left': formItem.parent().position().left + (formItem.next().find(".form-control").width()==null?formItem.next().width():formItem.next().find(".form-control").width())+65 + 'px',
                'top': formItem.parent().position().top - 30 + 'px'
            });
        }
    });
    //点击确定
    $('.form_sure').click(function () {
        if(!$("#stockForm").valid()) return;
        var newValue = $("#swValue").val();
        $.post(
            "updatesw.htm",
            {
                swValue:$('#swValue').val(),
                swId:$("#swId").val(),
                CSRFToken:$("#CSRFToken").val()
            },
            function(data){
                $(formItem).text(newValue);
            });
        $('.form_btns').hide();
        formItem.parent().removeClass('form_open');
        window.location.reload();
    });
    //点击取消
    $('.form_cancel').click(function () {
        $("#swValue").val(oldValue);
        $('.form_btns').hide();
        formItem.parent().removeClass('form_open');
    });

    /* 下面是表单里面的填写项提示相关的 */
    $('.kucunyujing').popover({
        content : '当商品库存低于该值则进行预警',
        trigger : 'hover'
    });
});