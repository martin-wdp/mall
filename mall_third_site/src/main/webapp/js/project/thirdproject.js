/**
 * 第三方模版js
 * Created by liangck on 15/5/29.
 */

function validataForm(){
    var flag = true;
    //验证文章标题
    if($("#up_name").val()==""){
        $("#up_name").next().html( "请填写专题名称!");
        $("#up_name").focus();
        flag = flag && false;
    }
    return flag;
}

/*重置表单*/
function resetForm(){
    $("#up_infoId").val("");
    $("#up_name").val("");
    $("#thirdProjectContext").text("");
    $("#topicCont").code("");
}

/*根据ID查询专题信息*/
function fillFormDate(pid){
    $.get("getProjectInfoById.htm",{thirdProjectId:pid},function(data){
        if(data!=null){
            $("#up_infoId").val(data.thirdProjectId);
            $("#up_name").val(data.thirdProjectName);
            $("#thirdProjectContext").text(data.thirdProjectContext);
            $("#topicCont").code(data.thirdProjectContext);
        }
    },'json');
}

/*删除专题*/
function del(projectId){
    $("#deleteProjectId").val(projectId);
    $("#delete-tip").modal('show');
}


$(function (){

    /*添加按钮*/
    $(".create-project-btn").click(function(){
        resetForm();
        $("#thirdprojectinfoForm").attr("action","addThirdProject.htm");
    });

    /*修改按钮*/
    $(".modify-project-btn").click(function(){
        resetForm();
        $("#thirdprojectinfoForm").attr("action","updateThirdProject.htm");
        /*填充表单数据*/
        fillFormDate($(this).attr("data-key"));
        $("#project_sub").html("修改专题");
        /*弹出框*/
        $("#addTopic").modal('show');
    });

    $(".create-project-btn").click(function(){
        $("#project_sub").html("添加专题");
        /*弹出框*/
        $("#addTopic").modal('show');
    });

    /*保存*/
    var num=0;
    $("#save").click(function (){
        if(validataForm()&&num==0){
            num+=1;
            $("#thirdProjectContext").val($("#topicCont").code());
            $("#thirdprojectinfoForm").submit();
        }
    });


    //*刪除按鈕*/
    $(".delete-btn").click(function(){
        /*设置确定按钮的样式，用于事件控制*/
        $("#tip-submit-btn").removeClass("muilty-delete").addClass("single-delete");
        $("#deleteKey").val($(this).attr("data-key"));
        $("#delete-tip").modal('show');
    });

    /*单个删除*/
    /*$(".single-delete").on("click",function(){
     alert("hello");
     //$("#singleDeleteForm").attr("action",$("#basePath").val()+"/deleteThirdTempAdver.htm").submit();
     });*/
    $("div.modal-footer").on("click","button.single-delete",function(){
        $("#single-delete-form").attr("action",$("#basePath").val()+"/deleteThirdProject.htm").submit();
    });

    /*查询按钮*/
    $("#searchBtn").click(function(){
        $("#selectfrom").submit();
    });

    /*批量删除按钮*/
    $("#muilty-delete-btn").click(function(){
        if(checkSelect("channelAdverId")){//检查是否选中了行记录
            /*设置确定按钮的样式，用于事件控制*/
            $("#tip-submit-btn").removeClass("single-delete").addClass("muilty-delete");
            $("#delete-tip").modal('show');
        }else{//如果未选中,弹出提示框
            $("#select-tip").modal('show');
        }
    });
    /*批量删除*/
    $("div.modal-footer").on("click","button.muilty-delete",function(){
        $("#muilt-delete-form").attr("action",$("#basePath").val()+"/deleteThirdTempAdver.htm").submit();
    });

});
