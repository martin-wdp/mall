/**
 * 标签广告列表js
 * Created by NingPai-liangck on 2015/5/25.
 */

/*验证表单*/
function validateForm(){
    var flag = true;
    //验证广告名称
    if($("#up_adverName").val()==""){
        $("#up_adverName").next().html("请填写广告名称");
        $("#up_adverName").focus();
        flag = flag && false;
    }else{
        $("#up_adverName").next().html("");
        flag = flag && true;
    }
    //验证广告图片
    if($("#up_imgSrc").val()==""){
        if($("#imageSrc").val()==""){
            $("#up_imgSrc").next().html( "请选择广告图片" );
            $("#up_imgSrc").focus();
            flag = false  && flag;
        }else{
            $("#up_imgSrc").next().html("");
            flag = true && flag;
        }
    }else{
        $("#up_imgSrc").next().html("");
        flag = true && flag;
    }
    //验证排序
    if($("#adverSort").val()==""){
        $("#adverSort").next().html( "请填写正确格式的数字" );
        $("#adverSort").focus();
        flag = flag && false;
    }else{
        flag = flag && checkNumAndDialog("adverSort");
    }
    return flag;
}

function changeFile(){
    var imgsrc = $("#up_imgSrc").val();
    $("#imageSrc").val(imgsrc);
    $("#adverPath").hide();
}

/*重置表單*/
function resetForm(){
    document.getElementById("adverInfoForm").reset();
    $("#up_adverName").next().html("");
    $("#up_imgSrc").next().html( "" );
    $("#adverSort").next().html( "" );
    $("#imageSrc").val("").attr("type","hidden");
}

/*填充form表单数据*/
function fillFormData(adverId){
    /*請求地址*/
    var queryUrl=$("#basePath").val()+"/getStoreyTagAdverById.htm";
    /*获取数据*/
    $.getJSON(queryUrl,{tagAvderId:adverId},function(data){
        /*填充表单数据*/
        if(data!=null){
            $("#up_barId").val(data.channelAdverId);
            $("#up_adverName").val(data.adverName);
            $("#up_temp2").val(data.temp2);
            $("#imageSrc").val(data.adverPath);
            $("#adverPath").attr("src",data.adverPath).show();
            $("#up_adverHref").val(data.adverHref);
            $("#adverSort").val(data.adverSort);
            $("input[name='userStatus'][value='"+data.userStatus+"']").attr("checked",true);
            $("#up_des").val(data.des);
        }
    });
}

$(function(){

    /*添加按鈕*/
    $("#createAdertBtn").click(function(){
        resetForm();
        $(".advet-info-title").html("添加广告");
        $("#adverPath").hide();
        $("#adverInfoForm").attr("action",$("#basePath").val()+"/createThirdTempStoreyTagAdver.htm");
    });

    /*修改按钮*/
    $(".modify-btn").click(function(){
        resetForm();
        $(".advet-info-title").html("修改广告");
        $("#adverInfoForm").attr("action",$("#basePath").val()+"/updateThirdTempStoreyTagAdver.htm");
        fillFormData($(this).attr("data-key"));
    });

    /*保存按钮*/
    $("#save").click(function(){
        if(validateForm()){
            $("#adverInfoForm").submit();
        }
    });

    /*刪除按鈕*/
    $(".delete-btn").click(function(){
        /*设置确定按钮的样式，用于事件控制*/
        $("#tip-submit-btn").removeClass("muilty-delete").addClass("single-delete");
        $("#deleteKey").val($(this).attr("data-key"));
    });

    /*批量删除按钮*/
    $("#muilty-delete-btn").click(function(){
        if(checkSelect("adverIds")){//检查是否选中了行记录
            /*设置确定按钮的样式，用于事件控制*/
            $("#tip-submit-btn").removeClass("single-delete").addClass("muilty-delete");
            $("#delete-tip").modal('show');
        }else{//如果未选中,弹出提示框
            $("#select-tip").modal('show');
        }
    });

    $("div.modal-footer").on("click","button.single-delete",function(){
        $("#singleDeleteForm").attr("action",$("#basePath").val()+"/deleteThirdTempStoreyTagAdver.htm").submit();
    });

    /*批量删除*/
    $("div.modal-footer").on("click","button.muilty-delete",function(){
        $("#muilt-delete-form").attr("action",$("#basePath").val()+"/deleteThirdTempStoreyTagAdver.htm").submit();
    });

});
