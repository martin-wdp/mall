/**
 * Created by Zhuoy on 2015/4/7.
 */

$(function(){
    /*获取会员等级*/
    $.get("getPointLevel.htm?CSRFToken="+$("#CSRFToken").val(),function(data){
        for(var i = 0; i < data.length; i++){
            var op = $("<option></option>").val(data[i].pointLelvelId).text(data[i].pointLevelName);

            $("#browseable").append(op);
        }
    });
    /* 富文本编辑框 */
    $('.summernote').summernote({
        height: 300,
        tabsize: 2,
        lang: 'zh-CN',
        onImageUpload: function(files, editor, $editable) {
            sendFile(files,editor,$editable);
        },
    });

    $("#picFile").change(function(){
        $("#addinformation").attr("action","uploadImg.htm");
        $("#addinformation").attr("target","uploadFrame");
        $("#addinformation").submit();
    });

    $("#uppicFile").change(function(){
        $("#updateInformation").attr("action","uploadImg.htm");
        $("#updateInformation").attr("target","uploadFrame");
        $("#updateInformation").submit();
    });
    $('.helpcateimg').popover({
        content : '第三方公告显示在商家后台',
        trigger : 'hover'
    });
});

/**
 * 图片上传回调方法
 * @param data 图片链接或者信息
 */
function callback(data) {
    $("#picFileView").attr("src",data);
    $("#up_img").attr("src",data);
    $("#up_imgSrc").val(data);
    $("#imgSrc").val(data);
}


//搜索
function informationSearch(){
    $("#searchTextstr").val($("#searchText").val());
    $("#informationform").submit();
}
var num=0;
//保存
function saveinformation(){
    $("#content").val($("#contentstr").code());
    if($("#addinformation").valid()&&num==0){
        //验证特殊字符
        if(!checkSpecSymb("title","title_tips")){
            return;
        }
        if(!checkAddInfoTitle($("#title").val(),$("#infoId").val())){
            $("#title").addClass( "error" );
            updateTips( "文章标题已存在!", $("#title_tips"));
            $("#up_title").focus();
            return;
        }else{
            $("#title").removeClass( "error" );
            $("#title_tips").text("").removeClass( "error" );
        }
        //验证特殊字符
        if($("#shortTitle").val() !=""){
            if(!checkSpecSymb("shortTitle","shortTitle_tips")){
                return;
            }
        }
        if($("#author").val()==""){
            $("#author").val("佚名");
        }
        //验证关键字
        if($("#keyword").val()==""){
            $("#keyword").val($("#title").val());
        }
        if(!checkSpecSymb("keyword","keyword_tips")){
            return;
        }
        //验证内容摘要
        if($("#description").val()==""){
            $("#description").val($("#title").val());
        }
        if(!checkSpecSymb("description","description_tips")){
            return;
        }
        $("#addinformation").attr("action","addInformation.htm?CSRFToken="+$("#CSRFToken").val());
        $("#addinformation").attr("target","_self");
        num+=1;
        $("#addinformation").submit();
    }
}
//添加弹框
function addmodal(){
    $('#addArticle').modal('show');
    $("#picFileView").attr("src","");
    $("#imgSrc").val("");
}
//修改查询
function updatemodal(infoid){
    $.ajax({
        type:"POST",
        url:"showInformationajax.htm?CSRFToken="+$("#CSRFToken").val(),
        data:"infoId="+infoid,
        success:function(data){
            //填充值
            if(data == null){
                return;
            }
            $("#up_imgSrc").val(data.imgSrc);
            $("#up_infoId").val(infoid);
            $("#up_title").val(data.title);
            $("#up_url").val(data.url);
            $("#up_shortTitle").val(data.shortTitle);
            $("#up_infoTempId").val(data.infoTempId);
            if(data.userDefined != null && data.userDefined != "" && data.userDefined != undefined){
                var userDefineds = data.userDefined.split(',');
                $.each(userDefineds,function(index,us){
                    $("#up_infoUD input:checkbox").each(function(index,infoud){
                        if(us == $(this).val()){
                            $(this).attr("checked","checked");
                        }
                    });
                });
            }
            $("#up_tag").val(data.tag);
            $("#up_img").attr("src",data.imgSrc);
            $("#up_source").val(data.source);
            $("#up_author").val(data.author);
            $("#up_infoTypeId").val(data.infoTypeId);
            $("#up_browseable").val(data.browseable);
            if(data.isThirdNews ==1){
                $("#isThirdNews1").attr("checked","checked");
            }else{
                $("#isThirdNews0").attr("checked","checked");
            }
            if(data.isShow ==1){
                $("#isShow1").attr("checked","checked");
            }else{
                $("#isShow0").attr("checked","checked");
            }
            $("#up_sort").val(data.sort);
            $("#up_keyword").val(data.keyword);

            $("#up_description").val(data.description);
            $("#up_content").val(data.content);
            $("#up_contentstr").code(data.content);
        }
    });
    $('#updateArticle').modal('show');
}

function updateinformation(){
    $("#up_content").val($("#up_contentstr").code());
    if($("#updateInformation").valid()){
        //验证特殊字符
        if(!checkSpecSymb("up_title","up_title_tips")){
            return;
        }
        if(!checkAddInfoTitle($("#up_title").val(),$("#up_infoId").val())){
            $("#up_title").addClass( "error" );
            updateTips( "文章标题已存在!", $("#up_title_tips"));
            $("#up_title").focus();
            return;
        }else{
            $("#up_title").removeClass( "error" );
            $("#up_title_tips").text("").removeClass( "error" );
        }
        //验证特殊字符
        if($("#up_shortTitle").val() !=""){
            if(!checkSpecSymb("up_shortTitle","up_shortTitle_tips")){
                return;
            }
        }
        if($("#up_author").val()==""){
            $("#up_author").val("佚名");
        }
        //验证关键字
        if($("#up_keyword").val()==""){
            $("#up_keyword").val($("#up_title").val());
        }
        if(!checkSpecSymb("up_keyword","up_keyword_tips")){
            return;
        }
        //验证内容摘要
        if($("#up_description").val()==""){
            $("#up_description").val($("#up_title").val());
        }
        if(!checkSpecSymb("up_description","up_description_tips")){
            return;
        }
        $("#updateInformation").attr("action","updateInformation.htm?CSRFToken="+$("#CSRFToken").val());
        $("#updateInformation").attr("target","_self");
        $("#updateInformation").submit();
    }
}


function delmodal(infoid){
    $("#delinfoId").val(infoid);
    $("#delinformation").modal();
}

function delinformation(){
    var checkedList = new Array();
    checkedList.push($("#delinfoId").val());
    if(checkedList.length > 0){
        $.post('delInformation.htm?CSRFToken='+$("#CSRFToken").val(),{infoIds:checkedList},function(){
            location.href="queryInfoVoList.htm";
        });
    }
}

//删除确认
function delsmodal(){
    if(checkCombox()==false){
        return false;
    }
    $("#delinformations").modal("show");
}

function delinformations(){
    var checkedList = new Array();
    $("input[name='infoId']:checked").each(function() {
        checkedList.push($(this).val());
    });
    if(checkedList.length > 0){
        $.post('delInformation.htm?CSRFToken='+$("#CSRFToken").val(),{infoIds:checkedList},function(){
            location.href="queryInfoVoList.htm";
        });
    }
}

//判断是否选中按钮
function checkCombox(){
    var helpcateId = $("input[name='infoId']:checked");
    //判断是否选择
    if(helpcateId.length>0){
        return true;

    }else{
        showTipAlert("请至少选择一行!")
        return false;
    }
}
/**
 * AJAX检查文章标题是否存在
 * @param title 文章标题
 */
function checkAddInfoTitle(title,infoId){
    var a;
    $.ajax({
        type:'post',
        url:"checkInformationByTitle.htm?CSRFToken="+$("#CSRFToken").val(),
        data:{title:title,infoId:infoId},
        success:function(data){
            a = data;
        },
        async: false
    });
    return a;
}

//验证特殊字符，将调试显示到页面中
function checkSpecSymb(inputobj,Tipobj){
    var regexp=new RegExp("[''\\[\\]<>?\\\\!]");
    if (regexp.test( $("#"+inputobj).val() ) ) {
        $("#"+inputobj).addClass( "error" );
        updateTips( "输入的内容包含特殊字符!", $("#"+Tipobj));
        $("#"+inputobj).focus();
        return false;
    }else {
        $("#"+Tipobj).text("").removeClass( "ui-state-highlight");
        $("#"+inputobj).removeClass( "error" );
        return true;
    }
}

//更新错误提示框的状态
function updateTips( t, tip)
{
    tip .text( t ) .addClass( "error" );
    
}

