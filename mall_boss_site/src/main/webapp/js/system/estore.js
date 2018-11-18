var CSRFToken = $("#CSRFToken").val();
$(function(){

    $("#choose").click(function () {
        art.dialog.open('queryImageManageByPbAndCidForChoose.htm?CSRFToken=' + CSRFToken + '&size=10000', {
            lock: true,
            opacity: 0.3,
            width: '900px',
            height: '400px',
            title: '选择图片'
        });
    });
    /* 表单项的值点击后转换为可编辑状态 */
    $('.form_value').click(function(){
        $('.form_cancel').click();
        var formItem = $(this);
        var oldValue = $(this).text();
        var inputId = $(this).attr("attr_id");
        var attrType = $(this).attr("attr_type");
        if(!$('.form_btns').is(':visible')) {
            formItem.parent().addClass('form_open');
            $('.form_btns').show();
            $('.form_btns').css({
                'left': formItem.parent().position().left + (formItem.next().find(".form-control").width()==null?formItem.next().width():formItem.next().find(".form-control").width())+65 + 'px',
                'top': formItem.parent().position().top - 30 + 'px'
            });
            $('.form_sure').click(function () {
                if(!$("#editEstoreForm").valid()) return;
                var estoreid = $("#estoreid").val();
                var newValue = '';
                if("radio"==attrType) {
                    newValue =$('input[name="'+inputId+'"]:checked').val();
                } else {
                    newValue = $("#"+inputId).val();
                }

                $.ajax({
                    url:'updateestore.htm?'+inputId+'='+newValue+"&estoreid="+estoreid+"&CSRFToken="+CSRFToken,
                    success:function(data) {
                        if("radio"==attrType) {
                            $(formItem).html(newValue=='0'?'<span class="label label-default">否</span>':'<span class="label label-success">是</span>');
                            oldValue = newValue=='0'?'否':'是';
                        } else {
                            $(formItem).text(newValue);
                        }
                    }
                });
                $('.form_btns').hide();
                formItem.parent().removeClass('form_open');
                //如果不刷新,页面编辑保存之后再次编辑显示的是第一次的数据
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
            });
        }
    });
});

function saveChoooseImage(url) {
    if (typeof (url) != 'string') {
        url = url[0];
    }
    if (url.indexOf(',') != -1) {
        url = url.substring(0, url.indexOf(','));
    }
    $("#img").attr("src", url);
    var estoreid = $("#estoreid").val();
    $.ajax({
        url: 'updateestore.htm?image=' + url + "&estoreid=" + estoreid + "&CSRFToken=" + CSRFToken,
        success: function (data) {

        }
    });

}