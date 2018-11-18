/**
 * 员工列表js
 * Created by liangck on 15/5/27.
 */

/**
 * 加载角色
 */
function loadauth(){
    $.ajax({
        type: 'post',
        url:'loadmanager.htm',
        async:false,
        success: function(data) {
            var str = '';
            for (var i = 0; i < data.roles.length; i++) {
            //<label class="choose-label"><input name="role" type="radio"/>新职位</label>

                str+='<label class="choose-label"><input type="radio" value="'+data.roles[i].id+'" name="authId" />'+data.roles[i].designation+'</label>';
            }
            $(".authlist").prepend(str);
        },
        error:function(){
            //网络异常
            //dia(5);
        }
    });
}


/**
 * 验证表单
 */
function validateForm(){
    //var flag= validateChkFun.formSubmit([ '#customerUsername', '#password', '#realname']);//'#bankUsername',
    var flag=true;
    var name=$("#customerUsername").val();
    var usernameReg= /^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$/; //户名

    if(name==null||$.trim(name)==""){
        $("#customerUsername").next().html("请输入用户名");
        flag&=false;
    }else if(name.length<4||(!usernameReg.test(name))){
        $("#customerUsername").next().html("用户名长度至少为4位，且不能包含特殊字符");
        flag&=false;
    }else{
        $.ajax({
            type: 'post',
            async:false,
            url: 'checkUsernameExitOrNot.htm?customerUsername='+name,
            success:function(data) {
                if(data > 0) {
                    $("#customerUsername").next().html("用户名已存在");
                    flag&=false;
                }else {
                    $("#customerUsername").next().html("");
                    flag&=true;
                }
            }
        });
    }



    var password=$("#password").val();
    var passwordReg= /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/;//密码
    var regS=/\s/g; //空格
    if(password==null||password==""||$.trim(password)==null|| $.trim(password)==""){
        $("#password").next().html("请输入密码");
        flag&=false;
    }else if(regS.test(password)){
    	$("#password").next().html("请勿输入空格");
        flag&=false;
    }else if(password.length<6||password.length>20){
        $("#password").next().html("密码必须是6-20位");
        flag&=false;
    }else if(!passwordReg.test(password)){
    	 $("#password").next().html("密码必须是数字和英文字母组合");
         flag&=false;
    }else{
        $("#password").next().html("");
        flag&=true;
    }

    if($("#repassword").val().trim().length == 0 ){
        $("#repassword").next().html("请输入确认密码");
        flag &= false;
    }else if(($("#password").val() != $("#repassword").val())){
        $("#repassword").next().html("两次输入密码不相同");
        flag &= false;
    }else{
        $("#repassword").next().html("");
        flag &= true;
    }

    if($("#realname").val().trim().length == 0 ){
        $("#realname").next().html("请输入员工姓名");
        flag &= false;
    }
    var authId=$("input[name=authId]:checked").val();
    if(authId == null || authId == ""){
        $(".authlist").find("label.error-tip").html("请选择角色");
        flag &= false;
    }else{
        $(".authlist").find("label.error-tip").html("");
        flag &= true;
    }

    return flag;
}

$(function(){
    /*确认添加*/
    var num=0;
    $("#addEmp").click(function(){
        if(validateForm()&&num==0){
            num+=1;
            $("#empForm").submit();
        }
    });

    /*修改权限*/
    $(".modify-auth-btn").click(function(){
        $("#updatecustid").val($(this).attr("data-key"));
        $("select[name='authorityId'] option[value='"+$(this).attr("auth-key")+"']").attr("selected","selected");
    });
    /*取消修改*/
    $(".cancel-modify-auth").click(function(){
        $("#updatecustid").val("");
    });

    /*停用*/
    $(".disable-btn").click(function(){
        $("#operateKey").val($(this).attr("data-key"));
        $("#cusFlag").val("disable");
        $(".opearte-info-title").html("确定禁用该员工吗？");
        $("#operate-form").attr("action","modifyemptodisable.htm");
        $("#operate-tip").modal('show');
    });

    /*启用*/
    $(".disable-btn-s").click(function(){
        $("#operateKey").val($(this).attr("data-key"));
        $("#cusFlag").val("");
        $(".opearte-info-title").html("确定启用该员工吗？");
        $("#operate-form").attr("action","modifyemptodisable.htm");
        $("#operate-tip").modal('show');
    });

    /*删除*/
    $(".delete-btn").click(function(){
        $("#operateKey").val($(this).attr("data-key"));
        $("#cusFlag").val("");
        $("#operate-form").attr("action","delemp.htm");
        $(".opearte-info-title").html("确定删除该员工吗？");
        $("#operate-tip").modal('show');
    });

    /*确定*/
    $("#confirm-operate").click(function(){
        $("#operate-form").submit();
    });
});
