/**
 * 职位管理js
 * Created by liangck on 15/5/26.
 */

/**
 * 加载权限列表
 * @param n
 * @param id
 */
function loadAuthority(n,id){

    /*
    <h4><label class="choose-label"><input type="checkbox"/>首页</label></h4>
    <dl>
    <dt><label class="choose-label"><input type="checkbox"/>商家首页</label></dt>
    </dl>
    <h4><label class="choose-label"><input type="checkbox"/>我的店铺</label></h4>
    <dl>
    <dt><label class="choose-label"><input type="checkbox"/>店铺管理</label></dt>
    <dt><label class="choose-label"><input type="checkbox"/>银行账号验证</label></dt>
    <dt><label class="choose-label"><input type="checkbox"/>信息接收设置</label></dt>
    </dl>
    */

    var str=null;
    var dataUrl="pid="+id;
    $.ajax({
        type: 'post',
        url:'loadallauthority.html',
        data:dataUrl,
        async:true,
        success: function(data) {
            $(".power-cont").html("");
            //加载所有权限
            if(data.pages != null){
                for (var i = 0; i < data.pages.length; i++) {
                    var obj=data.pages[i];
                    str ="<h4 ><label class='choose-label'><input class='p_inp' id='"+obj.id+"' type='checkbox' />"+obj.designation+"</label></h4><dl>";
                    for (var j = 0; j < obj.menuVos.length; j++) {
                        str += "<dt><label class='choose-label'><input id='"+obj.menuVos[j].id+"' type='checkbox' />"+obj.menuVos[j].designation+"</label></dt>";
                    }
                    str+="</dl>";
                    $(".power-cont").append(str);
                }

            }
            var idStr="";
            var flag=true;
            //以下为选中当前拥有的权限页面
            $(".power-cont h4").each(function(){
                var num=$(this).find("input").prop("id");
                for (var j = 0; j < data.rolePages.length; j++) {
                    var curpage =data.rolePages[j];
                    if(flag){
                        idStr+="|"+curpage.id;
                    }
                    if(curpage.id == num){
                        $(this).find("input").prop("checked","true");
                    }
                }
                flag=false;
            });

            $(".power-cont dl dt").each(function(){
                var num=$(this).find("input").prop("id");
                for (var j = 0; j < data.rolePages.length; j++) {
                    var curpage =data.rolePages[j];
                    if(flag){
                        idStr+="|"+curpage.id;
                    }
                    if(curpage.id == num){
                        $(this).find("input").prop("checked","true");
                    }
                }
                flag=false;
            });

            $(".power-cont").append('<input type="hidden" id="page_id" value="'+idStr+'" />');
            $(".power-cont").append('<input type="hidden" id="auth_id" value="'+id+'" />');
            //加载完毕之后  预加载方法 和事件
            $(function(){
                $(".p_inp").change(function(){
                    var cur_p=$(this).parent().parent().next();
                    var _this = $(this);
                    if($(this).prop("checked")){
                        $(cur_p).find("input").prop("checked","checked");
                    }else{
                        $(cur_p).find("input").prop("checked","");
                    };
                });
                $(".p_inp").each(function(){
                	var _this = $(this),
                		cur_p = _this.parent().parent().next(); 
                	$(cur_p).find("input").change(function(){
                    	var _checked = $(cur_p).find("input:checked").length,
                    		_all = $(cur_p).find("input").length;
                    	if(_checked > 0) {
                    		_this.prop("checked", "checked");
                    	} else if(_checked == 0) {
                    		_this.prop("checked", "");
                    	};
                    });
                });

                $(".power-cont").find("input").change(function(){
                    idStr="";
                    $(".power-cont h4").each(function(){
                        if($(this).find("input").prop("checked")){
                            idStr+="|"+$(this).find("input").prop("id");
                        }
                    });

                    $(".power-cont dl dt").each(function(){
                        if($(this).find("input").prop("checked")){
                            idStr+="|"+$(this).find("input").prop("id");
                        };
                    });
                    $("#page_id").val(idStr);
                });
            });
        },
        error:function(){
            //网络异常
        }
    });

}

/*修改权限*/
function updateAuth(path){
    var page_id = $("#page_id").val();
    var auth_id = $("#auth_id").val();
    var ss=page_id.substring(page_id.indexOf("|")+1).split('|');
    var paramStr="pagesId="+ss
        +"&authId="+auth_id;
    var flag=false;
    $.ajax({
        type: 'post',
        url:'updateauthority.htm',
        data:paramStr,
        async:false,
        success: function(data) {
        	if(data=="-1"){
        		flag=false;
        	}else{
        		flag=true;
        	}
        },
        error:function(){
            //网络异常
            //dia(5);
        }
    });
    if(flag){
        $("#addPower").modal('hide');
        $(".operate-tip-title").html('操作成功！');
        $(".operate-tip-title").css("color","green");
        $("#operate-tip").modal('show');
    }else{
    	$("#addPower").modal('hide');
    	$(".operate-tip-title").html('操作失败！');
    	$(".operate-tip-title").css("color","red");
    	$("#operate-tip").modal('show');
    }
}

/*修改职位名称*/
function updateRileName(obj,id){
    var $cur=$(obj).parent().parent().parent().parent();
    $cur.prepend("<input class='role_name form-control' value='"+$cur.find("a.role-des").html()+"'/>");
    $cur.find("a.role-des").hide();
    $cur.find("div.dropdown").hide();
    $cur.find("input.role_name").blur(function(){
        var paramStr="id="+id+"&designation="+$(this).val();
        //职位只能是汉字和字母 （设置白名单 可以允许那些）
        var re=/^([\u4E00-\uFA29]*[a-z]*[A-Z]*)+$/;
        if (re.test($(this).val())&&$(this).val()!="") {
            $.ajax({
                type: 'post',
                url:'updateauthorityname.htm',
                data:paramStr,
                async:false,
                success: function(data) {
                    if(data==1){
                        $(".operate-tip-title").html("操作成功！");
                        $(".operate-tip-title").css("color","green");
                    }else if(data==0){
                        $(".operate-tip-title").html("该角色名已经存在！");
                        $(".operate-tip-title").css("color","red");
                    }else{
                    	 $(".operate-tip-title").html("操作错误！");
                         $(".operate-tip-title").css("color","red");
                    }
                    $("#operate-tip").modal('show');
                },
                error:function(){
                    //网络异常
                    $(".operate-tip-title").html("网络错误！");
                    $("#operate-tip").modal('show');
                }
            });
        }else{
            $(".operate-tip-title").html("职位名称只可以是汉字或字母");
            $(".operate-tip-title").css("color","red");
            $("#operate-tip").modal('show');
        }
    });
}


/*删除职位*/
function deleteRile(id){
    $("#deleteKey").val(id);
    $("#delete-tip").modal('show');
}

$(function(){
    /*添加权限*/
    $(".add-power").click(function(){
        var rid=$(".role-list li.active").find("input").val();
        if(rid==null||rid==undefined){
            $(".operate-tip-title").html("请先添加职位！");
            $(".operate-tip-title").css("color","red");
            $("#operate-tip").modal('show');
        }else{
            loadAuthority(2,rid);
            $("#addPower").modal('show');
        }
    });

    $("#addRole").click(function(){
        var _cont = '<li><input class="role_name form-control" placeholder="新职位"/><div class="dropdown"><i class="glyphicon glyphicon-cog" data-toggle="dropdown" aria-expanded="false"></i>'+
                    '<ul class="dropdown-menu" role="menu">'+
                    '<li><a href="javascript:;">重命名</a></li>'+
                    '<li><a href="javascript:;">删除</a></li>'+
                    '</ul>'+
                    '</div>'+
                    '</li>';
        $(".role-list").prepend(_cont);

        var role='<div class="role-item hide">'+
                 '<p>暂未给此角色添加权限</p>'+
                '</div>';
        $(".role-cont").prepend(role);

        $(".role-list li:eq(0) input.role_name").focus();
        $(".role-list li:eq(0) input.role_name").blur(function(){
            var paramStr = "designation="+ $(this).val();
            var str=$(this).val();
            //职位只能是汉字和字母 （设置白名单 可以允许那些）
            var re=/^([\u4E00-\uFA29]*[a-z]*[A-Z]*)+$/;
            if (re.test(str)&&str!="") {
                $.ajax({
                    type: 'post',
                    url:'checkauthorityexist.html',
                    data:paramStr,
                    async:false,
                    success: function(data) {
                        if(data == 0){
                            //异步添加
                            $.ajax({
                                type: 'post',
                                url:'addauthority.html',
                                data:paramStr,
                                async:false,
                                success: function(data) {
                                    if(data == 1){
                                        //$(".role_list li:eq(0)").prepend("<span class='role_name fl'></span>");
                                        //$(".role_list li:eq(0) input").hide();
                                        //$(".opera_tip").text("添加成功!");
                                        $(".operate-tip-title").html("添加成功！");
                                        $(".operate-tip-title").css("color","green");
                                        $("#operate-tip").modal('show');
                                    }else{
                                        //$(".opera_tip").text("请重试!");
                                        $(".operate-tip-title").html("请重试！");
                                        $(".operate-tip-title").css("color","red");
                                        $("#operate-tip").modal('show');
                                    }
                                },
                                error:function(){
                                    //网络异常
                                    $(".operate-tip-title").html("网络错误！");
                                    $(".operate-tip-title").css("color","red");
                                    $("#operate-tip").modal('show');
                                }
                            });
                        }else{
                           $("#operate-tip-dupname").modal('show');
                        }
                    },
                    error:function(){
                        //网络异常
                        //dia(5);
                        $(".operate-tip-title").html("网络错误！");
                        $(".operate-tip-title").css("color","red");
                        $("#operate-tip").modal('show');
                    }
                });

            }else{
                $(".operate-tip-title").html("职位名称只可以是汉字或字母");
                $(".operate-tip-title").css("color","red");
                $("#operate-tip").modal('show');
            }
        });
    });


    /*删除按钮*/
    $(".delete-confirm-btn").click(function(){
        var paramStr="id="+$("#deleteKey").val();
        //异步添加
        $.ajax({
            type: 'post',
            url: 'delauthority.html',
            data: paramStr,
            async: false,
            success: function (data) {
                $(".operate-tip-title").html("操作成功！");
                $(".operate-tip-title").css("color","green");
                $("#operate-tip").modal('show');
            },
            error: function () {
                //网络异常
                //dia(5);
            }
        });
    });

    /*取消删除*/
    $(".delete-cancel-btn").click(function(){
        $("#delete-tip").modal('hide');
        $("#deleteKey").val("");
    });

    /*确认按钮*/
    /*$(".result-confirm-btn").click(function(){

    });*/
});

