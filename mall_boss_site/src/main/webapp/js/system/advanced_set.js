var formItem,oldValue,inputId,attrType,picId;
$(function(){
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

        }
    });
    $('.form_cancel').click(function () {
        if("radio"==attrType) {

        } else {
            $("#"+inputId).val(oldValue);
        }
        $('.form_btns').hide();
        formItem.parent().removeClass('form_open');
    });
    //保存FastDfs设置
    $('#form_fastdfs').click(function () {
        var newValue = '';
        if("radio"==attrType) {
            newValue =$('input[name="'+inputId+'"]:checked').val();
        } else {
            newValue = $("#"+inputId).val();
        }
        var CSRFToken = $("#token").val();
        if(inputId=='httpPort') {
            //验证端口号
            if($.trim($("#httpPort").val())==""){
                showTipAlert('请填写端口号!');
                return;
            }
            if(!/^\d+$/.test($("#httpPort").val())){
                showTipAlert('端口号格式不正确!');
                return;
            }
        }
        if(inputId=='serverPath') {
            //验证FastDFS的IP地址
            if($.trim($("#serverPath").val())==""){
                showTipAlert('请填写FastDFS的IP地址!');
                return;
            }
            if(!/^(\d+)\.(\d+)\.(\d+)\.(\d+)\:(\d+)$/.test($("#serverPath").val())){
                showTipAlert('IP地址格式不正确!');
                return;
            }
        }
        if(inputId=='resultPath') {
            //验证返回路径
            if($.trim($("#resultPath").val())==""){
                showTipAlert('请填写返回路径!');
                return;
            }
            if(!/^http:\/\/(\d+)\.(\d+)\.(\d+)\.(\d+)\:(\d+)\/$/.test($("#resultPath").val())){
                showTipAlert('IP地址格式不正确!');
                return;
            }
        }
        var args = {};
        args[inputId] = newValue;
        args['fastdfsId'] = $("#fastdfsId").val();
        $.post("updateFastDFSPathAjax.htm?CSRFToken="+CSRFToken,args,
            function(data){
                if(data.flag){
                    if("radio"==attrType) {
                        if(newValue=='0') {
                            $(formItem).html('<span class="label label-default">不启用</span>');
                            $("#open4").click();
                        } else {
                            $(formItem).html( '<span class="label label-success">启用</span>');
                            $("#open3").click();
                        }

                    } else {
                        $(formItem).html(newValue);
                        oldValue = newValue;
                    }
                }else{
                    showTipAlert('fastDFS修改失败!');
                }
            }
        );
        $('.form_btns').hide();
        formItem.parent().removeClass('form_open');
    });

    //文件上传设置
    $("#form_sure_upload").click(function () {
        var CSRFToken = $("#token").val();
        var newValue = $("#"+inputId).val();
        if(inputId=='up_maxSize') {
            if($.trim($("#up_maxSize").val())==""){
                showTipAlert('请填写可上传文件大小!');
                return;
            }
            if(!/^\d+$/.test($("#up_maxSize").val())){
                showTipAlert('可上传文件大小不是数字格式!');
                return;
            }
        }
        if(inputId=='suffixArray') {
            newValue = newValue.join(',');
        }
        var args = {};
        args[inputId] = newValue;
        args['uploadfilesetId'] = $("#uploadfilesetId").val();
        $.post("updateUploadFileSetNew.htm?CSRFToken="+CSRFToken,args,
            function(data){
                $(formItem).text(newValue);
                oldValue = newValue;
            }
        );

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
    //后台设置图片修改
    $(".choose_img_btn").click(function(){
        var CSRFToken = $("#token").val();
        picId = $(this).attr("attr_id");
        art.dialog.open('queryImageManageByPbAndCidForChoose.htm?CSRFToken='+CSRFToken+'&size=10000', {
            lock: true,
            opacity:0.3,
            width: '900px',
            height: '620px',
            title: '选择图片'
        });
    })
    /* 为选定的select下拉菜单开启搜索提示 */
    $('select[data-live-search="true"]').selectpicker();
    /* 为选定的select下拉菜单开启搜索提示 END */

    $(".nav-tabs li").click(function() {
        if(formItem!=undefined) {
            $('.form_cancel').click();
        }
    });

    /* 下面是表单里面的填写项提示相关的 */
    $('.indexlogo').popover({
        content : 'jpg,png,jpeg,建议160*40px',
        trigger : 'hover'
    });
    $('.loginlogo').popover({
        content : 'jpg,png,jpeg,建议140*40px',
        trigger : 'hover'
    });
});
/**
 * 保存分页行数设置
 * @param obj
 */
function savePageRows(obj){
    var CSRFToken = $("#token").val();
    $.post("updatePageRowsAjax.htm?CSRFToken="+CSRFToken,{pageRows:$("#up_pageRows").val()},function(data){
        $("#pageRowsSpan").text($("#up_pageRows").val());
        $(obj).next().click();
    });
};
/**
 * 保存验证码开关设置
 * @param obj
 */
function savePathcSet(obj) {
    var CSRFToken = $("#token").val();
    var showpathca = $("input[name=showpatcha]:checked").val();
    $.post("updateLogPatcha.htm?CSRFToken="+CSRFToken,{"loginPatcha":showpathca,bsetId:1},function(data){
        if(showpathca==0) {
            $("#showpatchaSpan").html('<span class="label label-default">不启用</span>');
        } else {
            $("#showpatchaSpan").html('<span class="label label-success">启用</span>');
        }
        $(obj).next().click();
    });
}

/**
 * 保存系统设置图片
 * @param url
 */
function saveChoooseImage(url) {
    var CSRFToken = $("#token").val();
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
        success:function(result) {
            $("#"+picId+"_pic").attr("src",url);
        }
    });

}
