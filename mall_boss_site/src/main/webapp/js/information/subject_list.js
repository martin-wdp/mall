//图片回调
function saveChoooseImage(url) {
    if(typeof (url) != 'string') {
        url = url[0];
    }
    if(url.indexOf(',')!=-1){
        url=url.substring(0,url.indexOf(','));
    }
    $("#img").attr("src",url);
    $("#subjectImg").val(url);

}
/*新增*/
var num=0;
function subForm(){
    $(".required_add").val($(".upcountStr").code());
    if(  $("#addSubjectForm").valid()&&num==0){
        num+=1;
        $("#addSubjectForm").submit();
    }

}

/*修改*/
function subForm_update(){
    $('.required_update').val($('#countStr_update').code());
    $('#subSubjectForm').submit();
}

function updateSubject(subjectId){
    $.post("showinforsubjectajax.htm?CSRFToken="+$(CSRFToken).val(), { subjectId:subjectId },
        function(data){
            $('#up_test').val(data.title);
            $('#up_seoKeyword').val(data.seoKeyword);
            $('#up_infoOnePageid').val(subjectId);
            $('#content').val(data.content);
            $('#up_seoDesc').val(data.seoDesc);
            $("#countStr_update").code(data.content);
            if(data.temp1==1){
                $('#up_temp11').prop("checked","checked")
            }else{
                $('#up_temp10').prop("checked","checked")
            }
            if(data.isShow==1){
                $('#up_isShow1').prop("checked","checked")
            }else{
                $('#up_isShow0').prop("checked","checked")
            }

            $(img).attr("src",data.backgroundImg);
            $(subjectImg).val(data.backgroundImg);
        });
    $('#updateSpecialTopic').modal('show');
}

function cls(){
    $('#up_test').val("");
    $('#up_seoKeyword').val("");
    $('#up_infoOnePageid').val("");
    $("#countStr").code("");
    $('.required_update').val("");
    $('.required_add').val("");
    $('#up_seoDesc').val("");
    $('#up_temp11').prop("checked","checked")
    $('#up_isShow1').prop("checked","checked")
}

$(function () {
    //验证添加表单
    $("#subSubjectForm").validate();
    $("#addSubjectForm").validate();
});
