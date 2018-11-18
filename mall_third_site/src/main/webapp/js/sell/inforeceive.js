/**
 * 信息接收设置js
 * Created by liangck on 15/5/26.
 */

function setMess(objId){
    $("#mid_hide").val(objId);
    var dataUrl="mid="+objId;
    $.ajax({
        type: 'post',
        url:'querystoremess.htm',
        data:dataUrl,
        success: function(data) {

            if(data != ""){
                $("#mess_set_from").prop("action","updatestoremess.htm");
                var ss=data.messRecType.split(",");
                for (var i = 0; i < ss.length; i++) {
                    if(ss[i]== '0'){
                        $("#im_r").prop("checked",true);
                    }
                    if(ss[i]== '1'){
                        $("#email_r").prop("checked",true);
                        $("#email_t").val(data.relaEmail);
                    }
                    if(ss[i]== '2'){
                        $("#mobile_r").prop("checked",true);
                        $("#mobile_t").val(data.relaMobile);
                    }
                }
            }else{
                $("#mess_set_from").prop("action","addstoremess.htm");
            }
        }
    });
}

/*验证表单*/
function validateForm(){
    var bool = true;
    var email_t = $('#email_t').val();//邮箱
    var mobile_t = $('#mobile_t').val();//手机号码

    //清空错误信息
    $(".mobile_t_tip").html("");
    $(".email_t_tip").html("");
    //判断是否选中手机
    if($('#mobile_r').is(':checked')){
        //验证手机
        if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(mobile_t))){
            $("#mobile_t").next().html("请输入有效的手机号码");
            bool &= false;
        }
    }
    //判断是否选中邮箱
    if($('#email_r').is(':checked')) {
        //验证邮箱
        var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.(?:com|cn)$/;
        if (!reg.test(email_t)) {
            $("#email_t").next().html("请输入有效的电子邮箱");
            bool &= false;
        }
    }
    return bool;
}

/*重置表单*/
function resetForm(){
    document.getElementById("mess_set_from").reset();
    $("#mobile_t").next().html("");
    $("#email_t").next().html("");
}


$(function(){

    $(".receive-setting").click(function(){
        $(this).parent().find(".form-control").removeClass("hide");
    });

    /*设置按钮*/
    $('.infoset-btn').click(function(){
        resetForm();
        setMess($(this).attr('data-key'));
    });

    /*确定*/
    $("#confirm").click(function(){
        if(validateForm()){
            $("#mess_set_from").submit();
        }
    });

});
