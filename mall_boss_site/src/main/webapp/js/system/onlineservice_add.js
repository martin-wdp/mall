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

    /* 为选定的select下拉菜单开启搜索提示 */
    $('select[data-live-search="true"]').select2();
    /* 为选定的select下拉菜单开启搜索提示 END */

    /* 富文本编辑框 */
    $('.summernote').summernote({
        height: 300,
        tabsize: 2,
        lang: 'zh-CN'
    });

    /* 下面是表单里面的填写项提示相关的 */
    $('.zhuantiseokw').popover({
        content : '默认为文章名称(最大字数75)',
        trigger : 'hover'
    });
    $('.zhuantiseodesc').popover({
        content : '默认为文章名称(最大字数255)',
        trigger : 'hover'
    });
    $('.kflb').popover({
        html : true,
        content : '<p>1.最多添加20条客服信息</p><p>2.客服名称不超过6个汉字</p>',
        trigger : 'hover'
    });

    /* 高级搜索 */
    $('.advanced_search').popover({
        html : true,
        title : '高级搜索',
        content : $('.advanced_search_cont').html(),
        trigger : 'click',
        placement : 'bottom'
    }).click(function(){
    	 $('select[data-live-search="true"]').select2();
    });

    /* 下面是表单里面的日期时间选择相关的 */
    $('.form_datetime').datetimepicker({
        format: 'yyyy-mm-dd hh:ii:00:00',
        weekStart : 1,
        autoclose : true,
        language : 'zh-CN',
        pickerPosition : 'bottom-left',
        todayBtn : true,
        viewSelect : 'hour'
    });
    $('.form_date').datetimepicker({
        format : 'yyyy-mm-dd',
        weekStart : 1,
        autoclose : true,
        language : 'zh-CN',
        pickerPosition : 'bottom-left',
        minView : 2,
        todayBtn : true
    });
    /* 下面是表单里面的日期时间选择相关的 END */

    $('#saveOnLine').button().click(function(){
        if($("#online_save").valid()) {
            $("#online_save").submit();
        }
    });

    $('#addQQ').button().click(function(){
        var addQQ=$("#serviceQQ").clone();
        $("#online_form").html(addQQ.show());
    });

    $('#addIphone').button().click(function(){
        var addTel=$("#serviceTel").clone();
        $("#online_form").html(addTel.show());
    });

    $('#addWW').button().click(function() {
        var addWW=$("#serviceWW").clone();
        $("#online_form").html(addWW.show());
    })
});


function cacleBtn(){
    $("#serviceQQ").remove();
}
//验证添加QQ
function checkQQ(){

    var flag = true;

    var qqNo = $("#addQQNo").val();
    var qqName = $("#addQQName").val();
    //判断QQ、名称是否为空
    if(qqNo != "" && qqName != ""){
        //判断qq格式、名称是否有特殊字符
        if(/^[1-9][0-9]{4,}$/.test(qqNo)){
            if(checkSpecSymb("addQQNo","addQQ_tips") && checkSpecSymb("addQQName","addQQ_tips")){
                //判断是否有重复
                var qqnos = new Array();
                qqnos = $(".qqNumber");
                for(var i = 0;i<qqnos.length;i++){
                    if(qqNo==qqnos[i].value){
                        updateTips("QQ号码有重复！","addQQ_tips");
                        flag = false && flag;
                    }
                }
            }else{
                updateTips("QQ号码或客服名称包含特殊字符！","addQQ_tips");
                flag = false && flag;
            }
        }else{
            updateTips("QQ号码格式错误","addQQ_tips");
            flag = false && flag;
        }


    }else{
        updateTips("请填写QQ和名称！","addQQ_tips");
        flag = false && flag;
    }
    return flag;
}

//更新错误提示框的状态
function updateTips( t, tip) {
    $("#"+tip) .text( t ) .addClass( "ui-state-highlight" );
}

/* 删除操作 */
function removeItem(id){
    $.post("deleteOnLineServiceItem.htm?CSRFToken="+$("#CSRFToken").val(),{id:id}, function(data) {
        if(data){
            $("#item_"+id).remove();
        }else{
            alert("删除失败！");
        }
    });
}


//验证特殊字符，将调试显示到页面中
function checkSpecSymb(inputobj,Tipobj){
    var regexp=new RegExp("[''\\[\\]<>?\\\\!]");
    if (regexp.test( $("#"+inputobj).val() ) ) {
        $("#"+inputobj).addClass( "ui-state-error");
        $("#"+Tipobj).text("输入的内容包含特殊字符!").addClass( "ui-state-highlight");
        return false;
    }else {
        $("#"+inputobj).removeClass( "ui-state-error");
        $("#"+Tipobj).text("").removeClass( "ui-state-highlight");
        return true;
    }
}