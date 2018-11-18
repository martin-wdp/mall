/**
 * 楼层标签列表 js
 * Created by NingPai-liangck on 2015/5/25.
 */

/*验证表单*/
function validateForm(){
    var flag = true;
    if($("#up_name").val()==""){
        $("#up_name").next().html( "请填写标签名称!");
        $("#up_name").focus();
        flag = flag && false;
    }else{
        flag = checkSpecSymb("up_name") && flag;
    }
    //验证排序
    if($("#up_sort").val()==""){
        $("#up_sort").next().html( "请填写排序!");
        $("#up_sort").focus();
        flag = flag && false;
    }else{
        flag = flag && checkNumAndDialog("up_sort");
    }
    return flag;
}

/*重置表单*/
function resetForm(){
    document.getElementById("addStoreyTagForm").reset();
    $("#up_name").next().html( "");
    $("#up_sort").next().html( "");
}

/*根据ID查询标签信息，填充修改表单*/
function fillFormData(tagId){
    $.get("getChannelStoreyTagById.htm",{storeyTagId:tagId},function(data){
        if(data!=null){
            $("#up_barId").val(data.channelStoreyTagId);
            $("#up_name").val(data.name);
            $("#up_sort").val(data.sort);
            $("input[name='userStatus'][value='"+data.userStatus+"']").attr("checked","checked");
        }
    },"json");
}

$(function(){

    /*添加标签*/
    $(".create-tag-btn").click(function(){
        /*重置表单*/
        resetForm();
        $(".tag-info-title").html("添加标签");
        /*设置表单提交地址*/
        $("#addStoreyTagForm").attr("action","createThirdTempStoreyTag.htm");
    });

    /*修改按钮*/
    $(".modify-tag-btn").click(function(){
        resetForm();
        $(".tag-info-title").html("修改广告");
        $("#addStoreyTagForm").attr("action",$("#basePath").val()+"/updateThirdTempStoreyTag.htm");
        fillFormData($(this).attr("data-key"));
        $("#addFloor").modal('show');
    });

    /*提交按钮*/
    $("#save").click(function(){
        if(validateForm()){
            $("#addStoreyTagForm").submit();
        }
    });

    /*刪除按鈕*/
    $(".delete-btn").click(function(){
        /*设置确定按钮的样式，用于事件控制*/
        $("#tip-submit-btn").removeClass("muilty-delete").addClass("single-delete");
        $("#deleteKey").val($(this).attr("data-key"));
        $("#delete-tip").modal('show');
    });

    /*批量删除按钮*/
    $("#muilty-delete-btn").click(function(){
        if(checkSelect("tagIds")){//检查是否选中了行记录
            /*设置确定按钮的样式，用于事件控制*/
            $("#tip-submit-btn").removeClass("single-delete").addClass("muilty-delete");
            $("#delete-tip").modal('show');
        }else{//如果未选中,弹出提示框
            $("#select-tip").modal('show');
        }
    });

    $("div.modal-footer").on("click","button.single-delete",function(){
        $("#singleDeleteForm").attr("action",$("#basePath").val()+"/deleteThirdTempStoreyTag.htm").submit();
    });

    /*批量删除*/
    $("div.modal-footer").on("click","button.muilty-delete",function(){
        $("#muilt-delete-form").attr("action",$("#basePath").val()+"/deleteThirdTempStoreyTag.htm").submit();
    });

});