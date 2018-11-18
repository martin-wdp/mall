/**
 * Created by Zhuoy on 2015/4/14.
 */

$(function(){
    /*查询栏目放在树形控件中*/
    $.get("selectAllInfoType.htm?CSRFToken="+$("#CSRFToken").val(), function (data) {
        addInfoTypes(data,1);
    });
    /*获取会员等级*/
    $.get("getPointLevel.htm?CSRFToken="+$("#CSRFToken").val(),function(data){
        for(var i = 0; i < data.length; i++){
            var op = $("<option></option>").val(data[i].pointLelvelId).text(data[i].pointLevelName);
            $(".browseable").append(op);
        }
    });
});

function addInfoTypes(infoTypes,grade){
    var info = "<option value='0'>--无--</option>";
    for(var i=0;i<infoTypes.length;i++){
        if(infoTypes[i].grade == grade){
            info += "<option value='"+infoTypes[i].infoTypeId+"'>";
            info += "&nbsp;&nbsp;"+infoTypes[i].name+"</option>";
            if(infoTypes[i].childs.length>0){
                info += addgradeInfo(infoTypes[i].childs,grade+1);
            }
        }
    }
    $(".informationtypes").html(info);
}

function addgradeInfo(infoTypes,grade){
    var str = "";
    for(var i=0;i<infoTypes.length;i++){
        if(infoTypes[i].grade == grade){
            str += "<option value='"+infoTypes[i].infoTypeId+"'>";
            for(var j=1;j<grade;j++){
                str += "&nbsp;&nbsp;";
            }
            str += "&nbsp;&nbsp;"+infoTypes[i].name+"</option>";
            if(infoTypes[i].childs.length>0){
                str += addgradeInfo(infoTypes[i].childs,grade+1,infoTypes[i].parentId);
            }
        }
    }
    return str;
}
var num=0;
function addinformationType(){
    if($("#saveInformationType").valid()){
        if(!checkAddInfoTypeName($("#name").val(),null)){
            updateTips( "栏目名称已存在!", $("#name_tips"));
            return;
        }
        if(!checkSpecSymb("name","name_tips")){
            return;
        }
        //验证seo关键字
        if($("#keyword").val()==""){
            $("#keyword").val($("#name").val());
        }
        if(!checkSpecSymb("keyword","keyword_tips")){
            return;
        }
        //验证seo内容摘要
        if($("#seoDesc").val()==""){
            $("#seoDesc").val($("#name").val());
        }
        if(!checkSpecSymb("seoDesc","seoDesc_tips")){
            return;
        }
        if(num==0){
            num+=1;
            $("#saveInformationType").submit();
        }

    }
}

/**
 * AJAX检查栏目名称是否存在
 * @param title 文章标题
 */
function checkAddInfoTypeName(name,infoTypeId){
    var a;
    $.ajax({
        url:"checkInfoTypeName.htm?CSRFToken="+$("#CSRFToken").val(),
        data:{name:name,infoTypeId:infoTypeId},
        success:function(data){
            a = data;
        },
        async: false
    });
    return a;
}

//更新错误提示框的状态
function updateTips( t, tip)
{
    tip .text( t ) .addClass( "error" );
    
}

//验证特殊字符
function checkSpecSymb(inputobj,Tipobj){
    var regexp=new RegExp("[''\\[\\]<>?\\\\!]");
    if (regexp.test( $("#"+inputobj).val() ) ) {
        $("#"+inputobj).addClass( "error" );
        updateTips( "输入的内容包含特殊字符!", $("#"+Tipobj));
        $("#"+inputobj).focus();
        return false;
    }
    else {
        $("#"+Tipobj).text("").removeClass( "error");
        $("#"+inputobj).removeClass( "error" );
        return true;
    }
}

function updateModal(informationId){
    $.ajax({
        type:"post",
        url:"showInfoTypeajax.htm?CSRFToken="+$("#CSRFToken").val(),
        data:"infoTypeId="+informationId,
        success:function(data){
            //填充值
            $("#up_infoTypeId").val(informationId);
            $("#up_name").val(data.name);
            $("#up_parentId").val(data.parentId);
            if(data.isShow ==1){
                $("#up_isShow1").attr("checked","checked");
            }else{
                $("#up_isShow0").attr("checked","checked");
            }
            $("#up_browseable").val(data.browseable);
            if(data.isThirdNews ==1){
                $("#up_isThirdNews1").attr("checked","checked");
            }else{
                $("#up_isThirdNews0").attr("checked","checked");
            }
            if(data.attribute ==1){
                $("#up_attribute1").attr("checked","checked");
            }else{
                $("#up_attribute0").attr("checked","checked");
            }
            $("#up_url").val(data.url);
            $("#up_keyword").val(data.seoKeyword);
            $("#up_seoDesc").val(data.seoDesc);
            $("#up_sort").val(data.sort);
        }
    });
    $('#updateArticleCate').modal('show');
}

function updateinformationType(){
    if($("#updateInformationType").valid()){
        if(!checkAddInfoTypeName($("#up_name").val(),$("#up_infoTypeId").val())){
            updateTips( "栏目名称已存在!", $("#up_name_tips"));
            return;
        }
        if(!checkSpecSymb("up_name","up_name_tips")){
            return;
        }
        //验证seo关键字
        if($("#up_keyword").val()==""){
            $("#up_keyword").val($("#up_name").val());
        }
        if(!checkSpecSymb("up_keyword","up_keyword_tips")){
            return;
        }
        //验证seo内容摘要
        if($("#up_seoDesc").val()==""){
            $("#up_seoDesc").val($("#up_name").val());
        }
        if(!checkSpecSymb("up_seoDesc","up_seoDesc_tips")){
            return;
        }
        $("#updateInformationType").submit();
    }
}

function delmodal(infomationId){
    $("#delinfoId").val(infomationId);
    $("#delinformation").modal("show");
}
function delinformation(){
    location.href = "delInfoType.htm?CSRFToken="+$("#CSRFToken").val()+"&infoTypeId="+$("#delinfoId").val();
}