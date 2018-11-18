/**
 * 用户登陆
 */

    var emp = null;
    function getPatcha(){
        $.ajax({
            url: "patchcaSession.htm",
            context: document.body,
            success: function(data){
                emp = data;
            }});
    }

function login(){
    if(checkInput()){
        var url="checklogin-"+$("#managername").val()+"-"+$("#managerpassword").val();//+"-"+$("#urlhide").val()
        $.ajax({
            url: url,
            context: document.body,
            success: function(data){
                if(data.url == undefined){
                    $("#code_div").removeClass("hide");
                }
                if(data.status == '2'){
                    $("#managername").parents().addClass("error");
                    $(".username_tip").html("您输入的账户名不存在，请核对后重新输入");
                    refreshCode();//刷新验证码
                }else if(data.status == '3'){
                    //之前是开店流程 现在进入欢迎页
                    window.location.href='goenterpage.html';
                }else if(data.status == '4'||data.status == '10'){
                    location.href=data.createUrl;
                }else if(data.status == '6') {
                    $("#managername").parents().addClass("error");
                    $(".password_tip").html("您是供应商，请登录供应商后台系统");
                }
                else if(data.status == '0'){
                    $("#managerpassword").parents().addClass("error");
                    $(".password_tip").html("您输入的账户名和密码不匹配，请重新输入");
                    refreshCode();//刷新验证码
                }else if(data.status == '7'){
                    $("#managername").parents().addClass("error");
                    $(".username_tip").html("您输入的账户名被禁用");
                    refreshCode();//刷新验证码
                }else if(data.status == '11'){
                    $("#managername").parents().addClass("error");
                    $(".username_tip").html("您的账户没有登录商铺的权限");
                    refreshCode();//刷新验证码
                }else if(data.status == '66'){
                    $("#managername").parents().addClass("error");
                    $(".username_tip").html("您的商铺的有效时间已过期，请尽快联系平台！");
                    refreshCode();//刷新验证码
                }
                else {
                    //登陆成功 去我的店铺页面
                    window.location.href='goenterpage.html';
                }
            }});
    }else{
        $(".code_image").click();
    }

}

/***
 * 登陆信息验证
 * @returns {boolean}
 */
function checkInput(){
    if($.trim($("#managername").val()) == 0){
        $("#managername").parents().addClass("error");
        $(".username_tip").html("请输入邮箱/用户名/已验证手机");
        getPatcha();
        return false;
    }
    if($.trim($("#managerpassword").val()).length == 0){
        $("#managerpassword").parents().addClass("error");
        $(".password_tip").html("请输入密码");
        getPatcha();
        return false;
    }

    if($.trim($("#captcha").val()).length == 0){
        $("#captcha").parents().addClass("error");
        $(".captcha_tip").html("请输入验证码");
        getPatcha();
        return false;
    }
    var captcha = $("#captcha").val().toLowerCase();//录入的验证码  （转换大小写）
    //var sRand =  getCookie("sRand").toLowerCase(); //后台生成的验证码 大小写)
    if(captcha != emp){
        $("#captcha").parents().addClass("error");
        $(".captcha_tip").html("验证码不正确");
        getPatcha();
        return false;
    }

    return true;
}

//刷新验证码
function refreshCode(){
    $("#captcha").parents().removeClass("error");
    $(".captcha_tip").html("");
    //清空录入的验证码
    $('#captcha').val('');
    //清空验证码提示
    $(".code_image").click()

}
//获取cookie 中的值
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");

    if(arr=document.cookie.match(reg))

        return unescape(arr[2]);
    else
        return null;
}