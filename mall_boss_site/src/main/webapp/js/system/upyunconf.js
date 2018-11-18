var CSRFToken = $("#CSRFToken").val();
var formItem,oldValue,inputId,attrType;
$(function(){
    var upyunId = $("#upyunId").val();
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
            //给控件加上表单验证
            $("#"+inputId).addClass($("#"+inputId).attr("clazz"));
        }
    });

    //点击确定
    $('.form_sure').click(function () {
        if(!$("#updateUpyunForm").valid()) return;
        var newValue = '';
        if("radio"==attrType) {
            newValue =$('input[name="'+inputId+'"]:checked').val();
        } else {
            newValue = $("#"+inputId).val();
        }
        $.ajax({
            url:'updateUpyunConf.htm?'+inputId+'='+newValue+"&upyunId="+upyunId+"&CSRFToken="+CSRFToken,
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
    });
    //点击取消
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

/**
 * 弹框显示测试又拍云
 */
function showTestUpyun() {
    $("#testUpyunResult").html("");
    $('#testUpyun').modal('show');
}

/**
 * 测试又拍云 选择文件
 * @param obj
 */
function submitTestUpYunForm(obj) {
    if($(obj).val()=='') {
        return;
    }
    $('#testUpyunForm').submit();
}

function testCallback(resultCode,resultMsg) {
    $("#testUpyunResult").html("");
    if(resultCode=='200') {
        var images = resultMsg.split(",");
        $("#testUpyunResult").append(
            '<div class="form-group">'+
            '   <label class="control-label col-sm-6">原图：</label>'+
            '   <div class="col-sm-1"></div>'+
            '   <div class="col-sm-14 form_item">'+
            '       <img src="'+images[0]+'" style="max-width:100%;">'+
            '   </div>'+
            '</div>');
        $("#testUpyunResult").append(
            '<div class="form-group">'+
            '   <label class="control-label col-sm-6">大图：</label>'+
            '   <div class="col-sm-1"></div>'+
            '   <div class="col-sm-14 form_item">'+
            '       <img src="'+images[1]+'" style="max-width:100%;">'+
            '   </div>'+
            '</div>');
        $("#testUpyunResult").append(
            '<div class="form-group">'+
            '   <label class="control-label col-sm-6">中图：</label>'+
            '   <div class="col-sm-1"></div>'+
            '   <div class="col-sm-14 form_item">'+
            '       <img src="'+images[2]+'" style="max-width:100%;">'+
            '   </div>'+
            '</div>');
        $("#testUpyunResult").append(
            '<div class="form-group">'+
            '   <label class="control-label col-sm-6">小图：</label>'+
            '   <div class="col-sm-1"></div>'+
            '   <div class="col-sm-14 form_item">'+
            '       <img src="'+images[3]+'" style="max-width:100%;">'+
            '   </div>'+
            '</div>');
    } else if(resultCode=='401') {
        $("#testUpyunResult").append(
            '<div class="form-group">'+
            '   <label class="control-label col-sm-6">提示信息：</label>'+
            '   <div class="col-sm-1"></div>'+
            '   <div class="col-sm-14 form_item" style="margin-top:7px;">'
            +"401(用户名密码不正确)-"+resultMsg+
            '   </div>'+
            '</div>');
    } else if(resultCode=='404') {
        $("#testUpyunResult").append(
            '<div class="form-group">'+
            '   <label class="control-label col-sm-6">提示信息：</label>'+
            '   <div class="col-sm-1"></div>'+
            '   <div class="col-sm-14 form_item" style="margin-top:7px;">'
            +"404(又拍云尺寸模板未设置)-"+resultMsg+
            '   </div>'+
            '</div>');
    } else {
        $("#testUpyunResult").append(
            '<div class="form-group">'+
            '   <label class="control-label col-sm-6">提示信息：</label>'+
            '   <div class="col-sm-1"></div>'+
            '   <div class="col-sm-14 form_item" style="margin-top:7px;">'
            +"500-"+resultMsg+
            '   </div>'+
            '</div>');
    }
}