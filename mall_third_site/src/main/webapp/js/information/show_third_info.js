/**
 * 文章添加／修改 页面js
 * Created by liangck on 15/5/29.
 */

/**
 * 设置提示信息
 * @param msg 提示信息
 * @param obj 需要提示的文本域
 */
function updateTips(msg,obj){
    $("#"+obj).next().html(msg);
}
/**
 * 验证表单数据
 */
function checkFormData(){
    var flag = true;
    //验证文章标题
    if($("#up_title").val()==""){
        $("#up_title").next().html( "请填写文章标题!");
        $("#up_title").focus();
        flag = flag && false;
    }else{
        if(!checkAddInfoTitle($("#up_title").val(),$("#up_infoId").val())){
            $("#up_title").next().html( "文章标题已存在!");
            $("#up_title").focus();
            flag = flag && false;
        }else{
            $("#up_title").next().html("");
            flag = flag && true;
        }
    }

    //验证作者
    if($("#up_author").val()==""){
        $("#up_author").val("佚名");
    }
    //验证排序
    if($("#sort").val()==""){
        $("#sort").next().html( "请填写排序!");
        $("#sort").focus();
        flag = flag && false;
    }else{
        flag = flag && checkNumAndDialog("sort");
    }
    //验证关键字
    if($("#keyword").val()==""){
        $("#keyword").val($("#up_title").val());
    }
    //验证内容摘要
    if($("#description").val()==""){
        $("#description").val($("#up_title").val());
    }
    return flag;
}

/**
 * AJAX检查文章标题是否存在
 * @param title 文章标题
 */
function checkAddInfoTitle(title,infoId){
    var a;
    $.ajax({
        url:"checkThirdInformationByTitle.htm",
        data:{title:title,infoId:infoId},
        success:function(data){
            a = data;
        },
        async: false
    });
    return a;
}

$(function(){
    /*保存按钮*/
    $("#save").click(function(){
        if(checkFormData()){//验证表单
            /*设置文章内容*/
            $("#up_content").val($("#articleCont").code());
            /*设置表单提交地址*/
            var infourl = "";
            var up_infoId = $("#up_infoId").val();
            if(up_infoId==""){
                infourl = "addThirdInformation.htm";
            }else{
                infourl = "updateThirdInformation.htm";
            }
            $('#infoForm').attr('action',infourl).submit();
        }
    });

    //返回列表页
    $('#back').click(function() {
        location = "queryThirdInfoVoList.htm";
    });
});