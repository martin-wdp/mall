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
        lang: 'zh-CN'
    });

    /* 下面是表单里面的填写项提示相关的 */
    $('.kucunyujing').popover({
        content : '当商品库存低于该值则进行预警',
        trigger : 'hover'
    });

    $("#editAreaPackageForm").validate();
    $("#addAreaPackageForm").validate();
});

/**
 * 添加地区包
 */
function submitAreaPackageAddForm() {
    $("#addAreaPackageForm").submit();
}

/**
 * 修改地区包
 */
function submitAreaPackageEditForm() {
    $("#editAreaPackageForm").submit();
}

/**
 * 弹框显示修改地区包
 * @param areaPackageId
 */
function showEditAreaPackageForm(areaPackageId) {
    $("#areaPackageId").val(areaPackageId);
    $.ajax({
        url:'selectAreaPackageById.htm?areaPackageId='+areaPackageId+'&CSRFToken='+$("#CSRFToken").val(),
        success: function (data) {
            $("#areaPackage").val(data.areaPackage);
            $("#area").val(data.area);
            $("#des").text(data.des);
            if(data.defaultPackage==1) {
                $("#option1").click();
            } else {
                $("#option2").click();
            }
            if(data.usedStatus==1) {
                $("#option3").click();
            } else {
                $("#option4").click();
            }
            $("#editArea").modal("show");
        }
    });
}
