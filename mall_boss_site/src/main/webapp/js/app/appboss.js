$(function() {
    loadApps();
    /*添加弹出框表单验证*/
    //app名称
    $("#checkAppName").focus(function(){
        $(this).parent().find(".help-block").show().html("请输入应用名称").css("color","#dd6330");
    });
    $("#checkAppName").blur(function(){
        checkAppName();
    });
    //上传文件不能为空
    $("#checkAppLocation").blur(function(){
        checkAppLocation();
    });
    //app菜单endMenuId范围
    $("#checkEndMenuId").focus(function(){
        $(this).parent().find(".help-block").show().html("请输入结束菜单Id").css("color","#dd6330");
    });
    $("#checkEndMenuId").blur(function(){
        checkEndMenuId();
    });
    /* 富文本编辑框 */
    $('.summernote').summernote({
        height: 300,
        tabsize: 2,
        width:600,
        lang: 'zh-CN',
        onImageUpload: function(files, editor, $editable) {
            sendFile(files,editor,$editable);
        }
    });

    /*修改弹出框表单验证*/
    //app名称
    $("#updateAppName").focus(function(){
        $(this).parent().find(".help-block").show().html("请输入应用名称").css("color","#dd6330");
    });
    $("#updateAppName").blur(function(){
        checkUpdateAppName();
    });
    //选择图片
    $("#choose").click(function(){
        i=1;
        art.dialog.open('queryImageManageByPbAndCidForChoose.htm', {
            lock: true,
            opacity:0.3,
            width: '900px',
            height: '400px',
            title: '选择图片'
        });
    });
    //修改选择图片
    $("#updateChoose").click(function(){
        i=1;
        art.dialog.open('queryImageManageByPbAndCidForChoose.htm', {
            lock: true,
            opacity:0.3,
            width: '900px',
            height: '400px',
            title: '选择图片'
        });
    });
});

//图片回调
function saveChoooseImage(url) {
    if(typeof (url) != 'string') {
        url = url[0];
    }
    if(url.indexOf(',')!=-1){
        url=url.substring(0,url.indexOf(','));
    }
    $("#img").attr("src",url);
    $("#appLogo").val(url);
    $("#updateImg").attr("src",url);
    $("#updateAppLogo").val(url);
}
//app详细信息
function showAppDetail(id) {
    $.ajax({
        url:'appdetailforserver.htm?id='+id+"&CSRFToken="+$("#CSRFToken").val(),
        success:function(data) {
            $("#appNameDiv").html(data.appName);
            $("#appDescDiv").html('<p>'+data.appDesc+'</p>');
        }
    });
}
//添加APP
function addAppBoss(){
    var bValid = true;
    bValid &= checkValid();
    if(bValid) {
        $("#addAppDesc").text($("#addAppDescHtml").code());
        $("#addAppBoss").submit();
        $("#submitAddAppBoss").disable("disabled",true);
    }
}
//删除App
function delAppBoss(id){
    $.ajax({
        type: 'post',
        url: "delAppBoss.htm?id="+id,
        async:false,
        success: function(data) {
            if (data>0){
                location.reload();
            }
        }
    });
}

/*ajax 通过ID查询并塞值到修改页面*/
function selectAppBoss(id){
    $.ajax({
        type: 'post',
        url: "selectAppBoss.htm?id="+id,
        async:false,
        success: function(data) {
            if (data!=null){
                $("#id").val(data.id);
                $("#updateAppName").val(data.appName);
                $("#updateAppLogo").val(data.appLogo);
                $("#updateImg").prop('src',data.appLogo);
                $("#updateAppVersion").val(data.appVersion);
                if(data.publishStatus=='0'){
                    $('#publishStatus1').prop('checked', 'checked');
                }else if(data.publishStatus=='1'){
                    $('#publishStatus2').prop('checked', 'checked');
                }
                $("#updateLoadSql").text(data.loadSql);
                $("#updateStartSql").text(data.startSql);
                $("#updateStopSql").text(data.stopSql);
                $("#updateUnloadSql").text(data.unloadSql);
                $("#updateAppIntroduction").text(data.appIntroduction);
                $("#updateAppDescHtml").code(data.appDesc);
            }
        }
    });
}
//更新app
function updateAppBoss(){
    var bValid = true;
    bValid &= checkUpdateValid();
    if(bValid) {
        $("#updateAppDesc").text($("#updateAppDescHtml").code());
        $("#updateAppBoss").submit();
        $("#submitUpdateAppBoss").disable("disabled",true);
    }
}
//下架App
function downAppBoss(id){
    location.href="downAppBoss.htm?id="+id;


}
//上架App
function upAppBoss(id){
    location.href="upAppBoss.htm?id="+id;

}

//验证添加
function checkValid(){
    var bValid = true;
    //app名称
    var appName=$("#checkAppName").val();
    var appNameReg=/^[A-Za-z0-9\u4e00-\u9fa5]{2,20}/;
    if(appName==''){
        $("#checkAppName").parent().find(".help-block").show().html("App名称不能为空").css("color","#dd6330");
        bValid &= checkAppName();
    }
    if(appName!='' && !appNameReg.test(appName)){
        $("#checkAppName").parent().find(".help-block").show().html("由2-20位字符的中文或英文组成").css("color","#dd6330");
        bValid &= checkAppName();
    }
    //上传文件
    var appLocation=$("#checkAppLocation").val();
    if(appLocation==''){
        $("#checkAppLocation").parent().find(".help-block").show().html("请上传文件").css("color","#dd6330");
        bValid &= checkAppLocation();
    }else {
        $("#checkAppLocation").parent().find(".help-block").hide();
        bValid &= checkAppLocation();
    }
    //验证endMenuId
    var startMenuId=$("#checkStartMenuId").val();
    var endMenuId=$("#checkEndMenuId").val();
    if(endMenuId==''){
        $("#checkEndMenuId").parent().find(".help-block").show().html("请输入结束菜单Id").css("color","#dd6330");
        bValid &= checkEndMenuId();
    }else {
        if(parseInt(endMenuId)<=parseInt(startMenuId)){
            $("#checkEndMenuId").parent().find(".help-block").show().html("结束菜单Id应大于开始菜单Id").css("color","#dd6330");
            bValid &= checkEndMenuId();
        }else{
            $("#checkEndMenuId").parent().find(".help-block").hide();
            bValid &= checkEndMenuId();
        }
    }
    return bValid;
}
//验证app名称
function checkAppName(){
    var appName=$("#checkAppName").val();
    var appNameReg=/^[A-Za-z0-9\u4e00-\u9fa5]{2,20}/;
    if(appName==''){
        $("#checkAppName").parent().find(".help-block").show().html("App名称不能为空").css("color","#dd6330");
        return false;
    } else if(appName!='' && !appNameReg.test(appName)){
        $("#checkAppName").parent().find(".help-block").show().html("由2-20位字符的中文或英文组成").css("color","#dd6330");
        return false;
    }else if(appName!='' && appNameReg.test(appName)){
        $.ajax({
            type: 'post',
            url: "checkAppName.htm?appName="+appName,
            async:false,
            success: function(data) {
                if (data>0){
                    $("#checkAppName").parent().find(".help-block").show().html("App名称不能重复，请重新输入！").css("color","#dd6330");
                    return false;
                }else{
                    $("#checkAppName").parent().find(".help-block").hide();
                    return true;
                }
            }
        });
    }
}
//验证文件
function checkAppLocation(){
    var appLocation=$("#checkAppLocation").val();
    if(appLocation==''){
        $("#checkAppLocation").parent().find(".help-block").show().html("请上传文件").css("color","#dd6330");
        return false;
    }else {
        $("#checkAppLocation").parent().find(".help-block").hide();
        return true;
    }

}
//验证endMenuId
function checkEndMenuId(){
    var startMenuId=$("#checkStartMenuId").val();
    var endMenuId=$("#checkEndMenuId").val();
    if(endMenuId==''){
        $("#checkEndMenuId").parent().find(".help-block").show().html("请输入结束菜单Id").css("color","#dd6330");
        return false;
    }else {
        if(parseInt(endMenuId)<=parseInt(startMenuId)){
            $("#checkEndMenuId").parent().find(".help-block").show().html("结束菜单Id应大于开始菜单Id").css("color","#dd6330");
            return false;
        }else{
            $("#checkEndMenuId").parent().find(".help-block").hide();
            return true;
        }
    }
}

/*验证修改*/
function checkUpdateValid(){
    var bValid = true;
    //app名称
    var appName=$("#updateAppName").val();
    var appNameReg=/^[A-Za-z0-9\u4e00-\u9fa5]{2,20}/;
    if(appName==''){
        $("#updateAppName").parent().find(".help-block").show().html("App名称不能为空").css("color","#dd6330");
        bValid &= checkUpdateAppName();
    }
    if(appName!='' && !appNameReg.test(appName)){
        $("#updateAppName").parent().find(".help-block").show().html("由2-20位字符的中文或英文组成").css("color","#dd6330");
        bValid &= checkUpdateAppName();
    }
   /* //上传文件
    var appLocation=$("#updateAppLocation").val();
    if(appLocation==''){
        $("#updateAppLocation").parent().find(".help-block").show().html("请上传文件").css("color","#dd6330");
        bValid &= checkUpdateAppLocation();
    }else {
        $("#updateAppLocation").parent().find(".help-block").hide();
        bValid &= checkUpdateAppLocation();
    }
    */
    return bValid;
}
//验证app名称
function checkUpdateAppName(){
    var appName=$("#updateAppName").val();
    var appNameReg=/^[A-Za-z0-9\u4e00-\u9fa5]{2,20}/;
    if(appName==''){
        $("#updateAppName").parent().find(".help-block").show().html("App名称不能为空").css("color","#dd6330");
        return false;
    } else if(appName!='' && !appNameReg.test(appName)){
        $("#updateAppName").parent().find(".help-block").show().html("由2-20位字符的中文或英文组成").css("color","#dd6330");
        return false;
    }else if(appName!='' && appNameReg.test(appName)){
        $.ajax({
            type: 'post',
            url: "checkAppName.htm?appName="+appName,
            async:false,
            success: function(data) {
                if (data>0){
                    $("#updateAppName").parent().find(".help-block").show().html("App名称不能重复，请重新输入！").css("color","#dd6330");
                    return false;
                }else{
                    $("#updateAppName").parent().find(".help-block").hide();
                    return true;
                }
            }
        });
    }
}
//查询最大endMenuId
function selectMenuId(){
    $.ajax({
        type: 'post',
        url: "selectMenuId.htm",
        async:false,
        success: function(data) {
            if(data=='' || data==null){
                $("#checkStartMenuId").val(10000);
            }else{
                $("#checkStartMenuId").val(data);
            }
        }
    });

}
/*//验证文件
function checkUpdateAppLocation(){
    var appLocation=$("#updateAppLocation").val();
    if(appLocation==''){
        $("#updateAppLocation").parent().find(".help-block").show().html("请上传文件").css("color","#dd6330");
        return false;
    }else {
        $("#updateAppLocation").parent().find(".help-block").hide();
        return true;
    }

}*/

function loadApps(pageNo) {
    if(pageNo==undefined) pageNo=1;
    $.ajax({
        url:'applistforserver.htm?CSRFToken='+$("#CSRFToken").val()+'&pageNo='+pageNo+'&appName='+$("#appName").val(),
        success:function(data) {
            var appList = data.list;
            var html = '';
            for(var i=0;i<appList.length;i++) {
                var app = appList[i]; 
                if(app.publishStatus=='1') {
                    html += '<li>' +
                    '<div class="app-icon"><img alt="" src="' + app.appLogo + '"/></div>' +
                    '<p class="app-name">' + app.appName + '</p>' +
                    '<div class="app-cont">' +
                    '   <p class="full-name"><span>Name:</span>' + app.appName + '</p>' +
                    '   <p class="app-version"><span>Version:</span>' + app.appVersion + '</p>' +
                    '   <div class="app-operation">' +
                    '      <a href="javascript:;" class="ui-state-default ui-corner-all" onclick="downAppBoss(' + app.id + ')"><span class="ui-icon ui-icon-check"></span></a>' +
                    '      <a href="javascript:;" class="ui-state-default ui-corner-all" data-toggle="modal" data-target="#updateApp" onclick="selectAppBoss(' + app.id + ')"><span class="ui-icon ui-icon-pencil"></span></a>' +
                    '      <a href="javascript:;" class="ui-state-default ui-corner-all" onclick="delAppBoss(' + app.id + ')"><span class="ui-icon ui-icon-trash"></span></a>' +
                    '   </div>' +
                    '</div>' +
                    '</li>';
               }else{
                    html += '<li>' +
                    '<div class="app-icon"><img alt="" src="' + app.appLogo + '"/></div>' +
                    '<p class="app-name">' + app.appName + '</p>' +
                    '<div class="app-cont">' +
                    '   <p class="full-name"><span>Name:</span>' + app.appName + '</p>' +
                    '   <p class="app-version"><span>Version:</span>' + app.appVersion + '</p>' +
                        //'   <p class="app-status"><span>Status:</span>Active</p>'+
                    '   <div class="app-operation">' +
                    '      <a href="javascript:;" class="ui-state-default ui-corner-all" onclick="upAppBoss(' + app.id + ')" onclick="upAppBoss(' + app.id + ');"><span class="ui-icon ui-icon-closethick"></span></a>' +
                    '      <a href="javascript:;" class="ui-state-default ui-corner-all" data-toggle="modal" data-target="#updateApp" onclick="selectAppBoss(' + app.id + ')"><span class="ui-icon ui-icon-pencil"></span></a>' +
                    '      <a href="javascript:;" class="ui-state-default ui-corner-all" onclick="delAppBoss(' + app.id + ')"><span class="ui-icon ui-icon-trash"></span></a>' +
                    '   </div>' +
                    '</div>' +
                    '</li>';

                }
            }
            $("#appListUL").html(html);


            /*添加页脚*/
            $("#pageFoot").html("");
            var foot =  "";
            if(data.pageNo==1) {
                foot += '<span class="disabled"> 上一页 </span>';
            } else {
                foot += "<a href='javascript:void(0)' onclick='loadApps(" + data.prePageNo + ")'>上一页</a>";
            }
            var i = 1;
            if(data.startNo>1){
                foot = foot + "<a onclick='loadApps(1)' href='javascript:void(0)'>1</a>";
                foot = foot + '<span class="current">... </span>';
            }
            for (var l = data.startNo; l <= data.endNo; l++)
            {
                if ((i - 1 + data.startNo) == data.pageNo)
                {
                    foot = foot + '<span class="current"> '+data.pageNo+' </span>';
                }
                else
                {
                    foot = foot + "<a onclick='loadApps(" + (i - 1 + data.startNo) + ")' href='javascript:void(0)'>" + (i - 1 + data.startNo) + "</a>";
                }
                i++;
            }
            if(data.endNo<data.totalPages){
                foot = foot + '<span class="current">... </span>';
                foot = foot + "<a onclick='loadApps("+data.totalPages+")' href='javascript:void(0)'>"+data.totalPages+"</a>";
            }
            foot = foot + "<a onclick='loadApps(" + data.nextPageNo +  ")' href='javascript:void(0)'>下一页</a>" ;
            /*添加tfoot分页信息*/
            $("#pageFoot").html(foot);
        }
    });
}
