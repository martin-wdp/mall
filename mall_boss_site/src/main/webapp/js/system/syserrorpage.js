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

/**
 * 弹框显示编辑异常页面
 * @param pageId
 */
function showEditPage(pageId) {
    $("#errorPageId").val(pageId);
    var CSRFToken = $("#CSRFToken").val();
    $.ajax({
        url:'queryErrorPageById.htm?errorPageId='+pageId+"&CSRFToken="+CSRFToken,
        success:function(data) {
            //填充值
            $("#pageNameLabel").text(data.pageName);
            $("#pageTitle").val(data.pageTitle);
            $(".summernote").code(data.pageDes);
            if(data.usedStatus==0) {
                $("input[name=usedStatus]:eq(1)").click();
            } else {
                $("input[name=usedStatus]:eq(0)").click();
            }
            $('#pageEdit').modal('show')
        }
    });
}

/**
 * 确定编辑异常页面
 */
function submitEditForm() {
    $("#pageDes").text($(".summernote").code());
    $("#pageEditForm").submit();
}