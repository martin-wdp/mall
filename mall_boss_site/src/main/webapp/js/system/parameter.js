var picId = '';
var CSRFToken = $("#CSRFToken").val();
var formItem,oldValue,inputId,attrType;
$(function(){
    var bsetId = $("#bsetId").val();
    /* 表单项的值点击后转换为可编辑状态 */
    $('.form_value_parameter').click(function(){
        if(formItem!=undefined) {
            $('#form_cancel_parameter').click();
        }
        formItem = $(this);
        oldValue = $(this).text();
        inputId = $(this).attr("attr_id");
        if(!$('.form_btns').is(':visible')) {
            formItem.parent().addClass('form_open');
            $('#form_btns_parameter').show();
            $('#form_btns_parameter').css({
                'left': formItem.parent().position().left + (formItem.next().find(".form-control").width()==null?formItem.next().width():formItem.next().find(".form-control").width())+65 + 'px',
                'top': formItem.parent().position().top - 30 + 'px'
            });
            $("#"+inputId).addClass($("#"+inputId).attr("clazz"));
        }
    });
    /*后台基本信息表单项的值点击后转换为可编辑状态*/
    $('.form_value_parameter1').click(function(){
        if(formItem!=undefined) {
            $('#form_cancel_parameter1').click();
        }
        formItem = $(this);
        oldValue = $(this).text();
        inputId = $(this).attr("attr_id");
        if(!$('.form_btns').is(':visible')) {
            formItem.parent().addClass('form_open');
            $('#form_btns_parameter1').show();
            $('#form_btns_parameter1').css({
                'left': formItem.parent().position().left + (formItem.next().find(".form-control").width()==null?formItem.next().width():formItem.next().find(".form-control").width())+65 + 'px',
                'top': formItem.parent().position().top - 30 + 'px'
            });
            $("#"+inputId).addClass($("#"+inputId).attr("clazz"));
        }
    });
    //后台基本信息表单项-点击确定
    $('#form_sure_parameter1').click(function () {
        if(!$("#editParameterForm1").valid()) return;
        var newValue = $("#"+inputId).val();
        $.ajax({
            url:'updatebasicnew.htm?'+inputId+'='+newValue+"&bsetId="+bsetId+"&CSRFToken="+CSRFToken,
            success:function(data) {
                $(formItem).text(newValue);
                oldValue = newValue;
            }
        });
        $('.form_btns').hide();
        formItem.parent().removeClass('form_open');
    });
    //后台基本信息表单项-点击取消
    $('#form_cancel_parameter1').click(function () {
        $("#"+inputId).val(oldValue);
        $('.form_btns').hide();
        formItem.parent().removeClass('form_open');
        $("#"+inputId).removeClass($("#"+inputId).attr("clazz"));
    });

    $('.form_value_basic').click(function(){
        if(formItem!=undefined) {
            $('#form_cancel_basic').click();
        }
        formItem = $(this);
        oldValue = $(this).text();
        inputId = $(this).attr("attr_id");
        attrType = $(this).attr("attr_type");
        if(!$('#form_btns_basic').is(':visible')) {
            formItem.parent().addClass('form_open');
            $('#form_btns_basic').show();
            $('#form_btns_basic').css({
                'left': formItem.parent().position().left + (formItem.next().find(".form-control").width()==null?formItem.next().width():formItem.next().find(".form-control").width())+65 + 'px',
                'top': formItem.parent().position().top - 30 + 'px'
            });

        }
    });

    //点击确定
    $('#form_sure_parameter').click(function () {
        if(!$("#editParameterForm").valid()) return;
        var newValue = $("#"+inputId).val();
        $.ajax({
            url:'updatebasicnew.htm?'+inputId+'='+newValue+"&bsetId="+bsetId+"&CSRFToken="+CSRFToken,
            success:function(data) {
                $(formItem).text(newValue);
                oldValue = newValue;
            }
        });
        $('.form_btns').hide();
        formItem.parent().removeClass('form_open');
    });

    //后台设置
    $("#form_sure_basic").click(function () {
        var CSRFToken = $("#token").val();
        var newValue = $("#"+inputId).val();
        var args = {};
        args[inputId] = newValue;
        args['basicId'] = $("#basicId").val();
        $.post("ajaxUpdateSysBasic.htm?CSRFToken="+CSRFToken,args,
            function(data){
                $(formItem).text(newValue);
                oldValue = newValue;
            }
        );

        $('.form_btns').hide();
        formItem.parent().removeClass('form_open');
    });
    //点击取消
    $('#form_cancel_parameter').click(function () {
        $("#"+inputId).val(oldValue);
        $('.form_btns').hide();
        formItem.parent().removeClass('form_open');
        $("#"+inputId).removeClass($("#"+inputId).attr("clazz"));
    });

    $('#form_cancel_basic').click(function () {
        if("radio"==attrType) {

        } else {
            $("#"+inputId).val(oldValue);
        }
        $('.form_btns').hide();
        formItem.parent().removeClass('form_open');
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

    $(".choose_img_btn").click(function(){
        picId = $(this).attr("attr_id");
        art.dialog.open('queryImageManageByPbAndCidForChoose.htm?CSRFToken='+CSRFToken+'&size=1', {
            lock: true,
            opacity:0.3,
            width: '900px',
            height: '620px',
            title: '选择图片'
        });
    });

    $(".choose_img_btn_third").click(function(){
        picId = $(this).attr("attr_id");
        art.dialog.open('queryImageManageByPbAndCidForChoose.htm?CSRFToken='+CSRFToken+'&size=1', {
            lock: true,
            opacity:0.3,
            width: '900px',
            height: '620px',
            title: '选择图片'
        });
    });

    /* 下面是表单里面的填写项提示相关的 */
    $('.weblogo').popover({
        content : '建议jpg,jpeg,png.尺寸165*40px',
        trigger : 'hover'
    });
    $('.weblogothird').popover({
        content : '建议jpg,jpeg,png.尺寸130*35px',
        trigger : 'hover'
    });
    $('.bsetHotline').popover({
        content : '建议ico.尺寸16*16px',
        trigger : 'hover'
    });
    $('.loginImg').popover({
        content : '显示在前台会员登陆页面左侧的图片，'+'建议jpg,jpeg,png.尺寸1920*420px',
        trigger : 'hover'
    });
    $('.regImg').popover({
        content : '显示在前台会员注册页面左侧的图片，'+'建议jpg,jpeg,png.尺寸1920*480px',
        trigger : 'hover'
    });
    $('.loginImg_third').popover({
        content : '显示在新商家登陆页右侧图片，'+'建议jpg,jpeg,png.尺寸1920*420px',
        trigger : 'hover'
    });
    $('.bsetThirdAddress').popover({
        content : '商家在前台的登录入口',
        trigger : 'hover'
    });

    /* 下面是表单里面的填写项提示相关的 */
    $('.indexlogo').popover({
        content : '建议jpg,jpeg,png.尺寸160*40px',
        trigger : 'hover'
    });
    $('.loginlogo').popover({
        content : '建议jpg,jpeg,png.尺寸165*39px',
        trigger : 'hover'
    });
});

function saveChoooseImage(url) {
    if(typeof (url) != 'string') {
        url = url[0];
    }
    if(url.indexOf(',')!=-1){
        url=url.substring(0,url.indexOf(','));
    }
    if(picId=='loginLogo'||picId=='indexLogo') {
        if(typeof (url) != 'string') {
            url = url[0];
        }
        if(url.indexOf(',')!=-1){
            url=url.substring(0,url.indexOf(','));
        }
        var args = {};
        args[picId] = url;
        args['basicId'] = $("#basicId").val();
        args['CSRFToken']=CSRFToken;
        $.ajax({
            url:'ajaxUpdateSysBasic.htm',
            type:'POST',
            data:args,
            success:function(data) {
                    $("#"+picId+"_pic").attr("src",url);
            }
        });
    } else {
        var bsetId = $("#bsetId").val();
        var arg = {};
        arg[picId] = url;
        arg['bsetId'] = bsetId;
        arg['CSRFToken']=CSRFToken;
        $.ajax({
            url:'updatebasicnew.htm',
            type:'POST',
            data:arg,
            success:function(result) {
                $("#"+picId+"_pic").attr("src",url);
            }
        });
    }


}
function saveBigText(textid,obj) {
    var sHtml = $("#"+textid).code();
    var arg = {};
    arg[textid] = sHtml;
    arg['bsetId'] =$("#bsetId").val();
    arg['CSRFToken']=CSRFToken;
    $.ajax({
        url:'updatebasicnew.htm',
        type:'POST',
        data:arg,
        success:function(result) {
            $(obj).next().click();
        }
    });
}
function showPicModal(obj) {
    $('#imgUpload').modal('show');
}