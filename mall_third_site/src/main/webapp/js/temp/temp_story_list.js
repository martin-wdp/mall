/**
 * 模板樓層js
 * Created by NingPai-liangck on 2015/5/21.
 */

/**
 * 验证 新增/修改 表单
 */
function validateForm(){
    var flag = true;
    if($("#up_storeyName").val()==""){
        $("#up_storeyName").val($("#catId").find("option:selected").text());
    }
    flag = checkSpecSymb("up_storeyName") && flag;

    //验证seo优化
    if($("#up_storeySeo").val()==""){
        $("#up_storeySeo").val($("#up_storeyName").val());
    }
    return checkSpecSymb("up_storeySeo") && flag;
}

//验证特殊字符，将调试显示到页面中
/*function checkSpecSymb(inputobj){
    var regexp=new RegExp("[`~!@#$^&*()={}':;',\\[\\]<>/?~！@#￥……&*（）{}【】‘；：”“'。，、？]");
    if (regexp.test( $("#"+inputobj).val() ) ) {
        $("#"+inputobj).next().html("输入的内容包含特殊字符!");
        $("#"+inputobj).focus();
        return false;
    }
    else {
        $("#"+inputobj).next().html("");
        return true;
    }
}*/

/*修改楼层选择图片时，隐藏当前图片，显示选择图片路径*/
function changeFile(){
    $("#stotyPath").hide();
    $("#imageSrc").val($("#up_imgSrc").val());
}

/**
 * 根据id查询楼层信息，填充表单
 * @param sid
 */
function fillFormData(sid){
    $.getJSON($("#basePath").val()+"/getChannelStoreyById.htm",{storyId:sid},function(data){
        if(data!=null){
            $("#up_barId").val(data.channelStoreyId);
            $("#catId").val(data.goodsCatId);
            $("#up_storeyName").val(data.storeyName);
            //$("#up_imgSrc").val(data.storeyImg);
            $("#imageSrc").val(data.storeyImg);
            $("#up_storeyImgHref").val(data.storeyImgHref);
            $("#floorId").val(data.floorId);
            $("#up_storeySeo").val(data.storeySeo);
            $("input[name='userStatus'][value='"+data.userStatus+"']").attr("checked",true);
            $("#stotyPath").attr("src",data.storeyImg).show();
        }
    });
}

/*重置表单*/
function resetForm(){
    document.getElementById("storyInfoForm").reset();
    $("#up_storeyName").next().html("");
    $("#up_imgSrc").next().html("");
    $("#imageSrc").attr("type","hidden").hide();
    $("#stotyPath").hide();
    $("#up_storeyImgHref").next().hide();
    $("#up_storeySeo").next().html("");
}

$(function(){

    /*添加樓層按鈕*/
    $(".create-floor-btn").click(function(){
        /*重置表单*/
        resetForm();
        /*設置彈出框title*/
        $(".story-info-title").html("添加楼层");
        $("#stotyPath").hide();
        $("#storyInfoForm").attr("action",$("#basePath").val()+"/createThirdTempStorey.htm");
    });

    /*修改楼层按钮*/
    $(".modify-story-btn").click(function(){
        /*重置表單*/
        resetForm();
        /*填充表单数据*/
        fillFormData($(this).attr("data-key"));
        /*設置彈出框title*/
        $(".story-info-title").html("修改楼层");
        $("#storyInfoForm").attr("action",$("#basePath").val()+"/updateThirdTempStorey.htm");
    });

    /*表单提交按钮*/
    $("#save").click(function(){
        if(validateForm()){
            $("#storyInfoForm").submit();
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
        if(checkSelect("channelStoreyId")){//检查是否选中了行记录
            /*设置确定按钮的样式，用于事件控制*/
            $("#tip-submit-btn").removeClass("single-delete").addClass("muilty-delete");
            $("#delete-tip").modal('show');
        }else{//如果未选中,弹出提示框
            $("#select-tip").modal('show');
        }
    });

    $("div.modal-footer").on("click","button.single-delete",function(){
        $("#singleDeleteForm").attr("action",$("#basePath").val()+"/deleteThirdTempStorey.htm").submit();
    });

    /*批量删除*/
    $("div.modal-footer").on("click","button.muilty-delete",function(){
        $("#muilt-delete-form").attr("action",$("#basePath").val()+"/deleteThirdTempStorey.htm").submit();
    });
});
