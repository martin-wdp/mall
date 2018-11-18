var CSRFToken = $("#CSRFToken").val();
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

    $("#editAuthsetForm").validate();
});

/**
 * 修改登录接口启用状态
 * @param authId
 */
function changeUserdStatus(authId){
    location.href = "updateUserdStatusForAuth.htm?CSRFToken="+CSRFToken+"&authId="+authId;
}

/**
 * 弹框显示修改登录接口设置
 * @param authId
 */
function showAuthUpdate(authId) {
    $.ajax({
        type:"POST",
        url:"findauthone.htm",
        data:{authId:authId,CSRFToken:CSRFToken},
        success:function(data){
            //填充值
            $("#up_authid").val(data.authId);
            $("#up_name").val(data.authName);
            $("#up_clientid").val(data.authClientId);
            $("#up_secret").val(data.authClientSecret);
            $("#up_uri").val(data.authRedirectUri);
            $(".selector").val(data.authName);
            $("#up_authsort").val(data.authSort);
            $("#up_login").val(data.authLogin);
            if(data.authPic!=''&&data.authPic!=null){
                $("#preview").html('<img src="'+data.authPic+'" style="width:50px;height:50px;" />');
            }
            if(data.authShow==0){
                $("#open2").click();
            }else{
                $("#open1").click();
            }
            $('#editLoginAPI').modal('show');
        }
    });
}