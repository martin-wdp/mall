
$(function(){
    $('#saveAndEdit').click(function(){
        var advId=$('#advId').val();
        if($("#addandeditAdvForm").valid()){
            if(advId.trim().length == 0) {
                $('#addandeditAdvForm').attr("target","_self");
                $('#addandeditAdvForm').attr("action","addbbadv.htm?CSRFToken="+$('.token_val').val());
                $('#addandeditAdvForm').submit();
            }else {
                $('#addandeditAdvForm').attr("target","_self");
                $('#addandeditAdvForm').attr("action","updatebbadv.htm?CSRFToken="+$('.token_val').val());
                $('#addandeditAdvForm').submit();
            }
        }
    });


    $("#picFile").change(function(){
        $("#addandeditAdvForm").attr("action","uploadImg.htm");
        $("#addandeditAdvForm").attr("target","uploadFrame");
        $("#addandeditAdvForm").submit();
    });
});

/**
 * 图片上传回调方法
 * @param data 图片链接或者信息
 */
function callback(data) {
    $("#advImgi").attr("src",data);
    $("#advImg").val(data);
}


/**
 * 根据advId查询详细信息并绑定到弹出层中
 * */
function editAdv(advId){
    $("#title").text("修改页面广告");
    $.post("toeditbbadvNew.htm?advId="+advId,function(data){
            $("#advId").val(data.advId);
            $("#advName").val(data.advName);
            $("#advImgi").attr("src",data.advImg);
            $("#advImg").val(data.advImg);
            if(data.advPosition == 1){
                $("#advPosition1").attr("selected","selected");
            }else if(data.advPosition == 2){
                $("#advPosition2").attr("selected","selected");
            }else{
                $("#advPosition3").attr("selected","selected");
            }
            $("#advUrl").val(data.advUrl);
            $("#advSort").val(data.advSort);
            if(data.advShowFlag == 1){
                $('#advShowFlag1').prop('checked', 'checked');
            }else{
                $('#advShowFlag2').prop('checked', 'checked');
            }
            $('#addGroupAd').modal('show');
        }

    );
}

/**
 * 弹出添加页面广告弹出层
 * */
function addAdv(){
    $("#title").text("添加页面广告");
    $('#advId').val("");
    $('#advName').val("");
    $('#advImgInput').val("");
    $('#advPosition').val("");
    $('#advUrl').val("");
    $('#advSort').val("");
    $('#advImg').attr("src","");

    $('#addGroupAd').modal('show');
}

//验证特殊字符，将调试返回给调用者
function checkSpecSymbNew(inputobj){
    var regexp=new RegExp("[''\\[\\]<>?!]");
    if (regexp.test( $("#"+inputobj).val() ) ) {
        $("#"+inputobj).addClass( "ui-state-error" );
        return false;
    }else {
        $("#"+inputobj).removeClass( "ui-state-error" );
        return true;
    }
}

// 正则验证
function checkRegexp(o, regexp) {
    if (!(regexp.test(o))) {
        return false;
    } else {
        return true;
    }
}