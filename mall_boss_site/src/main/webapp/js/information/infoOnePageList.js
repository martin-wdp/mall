$(function(){
    changeSelect($("#infoTempId"));
    /* 富文本编辑框 */
    $('.summernote').summernote({
        height: 300,
        tabsize: 2,
        lang: 'zh-CN',
        onImageUpload: function(files, editor, $editable) {
            sendFile(files,editor,$editable);
        }
    });

    $("#picFile").change(function(){
        $("#addInfoOnePage").attr("action","uploadImg.htm");
        $("#addInfoOnePage").attr("target","uploadFrame");
        $("#addInfoOnePage").submit();
    });
    $("#up_picFile").change(function(){
        $("#updateInfoOnePage").attr("action","uploadImg.htm");
        $("#updateInfoOnePage").attr("target","uploadFrame");
        $("#updateInfoOnePage").submit();
    });
});

/**
 * 图片上传回调方法e
 * @param data 图片链接或者信息
 */
function callback(data) {
    $("#imgView").attr("src",data);
    $("#imgSrc").val(data);
    $("#up_img").attr("src",data);
    $("#up_imgSrc").val(data);
}

/**
 * Created by Zhuoy on 2015/4/1.
 */
//搜索
function infoOnePagess(){
    $("#searchTextstr").val($("#searchText").val());
    $("#infoOnePageform").submit();
}

function changeSelect(temp){
    var tempId = $(temp).val();
    $.ajax({
        url:"getInfoOPTagByTempId.htm?CSRFToken="+$("#CSRFToken").val(),
        data:{tempId:tempId},
        success:function(data){
            $("#infoOPTagId").html("");
            if(data.length > 0){
                for(var i = 0; i < data.length; i++){
                    var option = $("<option>").val(data[i].infoopTagId).text(data[i].name);
                    $("#infoOPTagId").append(option);
                }
            }else{
                var option = $("<option>").val("").text("没有标签");
                $("#infoOPTagId").append(option);
            }
        },
        async: false
    });
}
var num=0;
function addSinglePage(){
    $("#content").val($("#contentstr").code());
    $("#titletip").html("");
    $("#titletip").removeClass("error");
    if($("#addInfoOnePage").valid()){
        if(!checkAddInfoOPTitle($("#title").val(),null)){
            $("#titletip").html("单页标题已存在！");
            $("#titletip").addClass("error");
            return;
        }
        if($("#keyword").val() == ""){
            $("#keyword").val($("#title").val());
        }
        if($("#description").val() == ""){
            $("#description").val($("#title").val());
        }
        if(num==0){
            num+=1;
            $("#addInfoOnePage").attr("action","addInfoOnePage.htm?CSRFToken="+$("#CSRFToken").val());
            $("#addInfoOnePage").attr("target","_self");
            $("#addInfoOnePage").submit();
        }

    }
}

/**
 * AJAX检查文章标题是否存在
 * @param title 文章标题
 */
function checkAddInfoOPTitle(title,infoOPId){
    var a;
    $.ajax({
        type: 'POST',
        url:"checkInfoOPByTitle.htm?CSRFToken="+$("#CSRFToken").val(),
        data:{title:title,infoOPId:infoOPId},
        success:function(data){
            a = data;
        },
        async: false
    });
    return a;
}
//删除确认
function delinfoYN(infoOPId){
    $("#delinfoId").val(infoOPId);
    $("#delinfo").modal("show");
}

function delinfo(){
    var checkedList = new Array();
    checkedList.push($("#delinfoId").val());
    if(checkedList.length > 0){
        $.post('delInfoOnePage.htm?CSRFToken='+$("#CSRFToken").val(),{infoOnePageIds:checkedList},function(){
            location.href="queryInfoOnePagesByPageBean.htm";
        });
    }
}

//删除确认
function delinfosYN(){
    if(checkCombox()==false){
        return false;
    }
    $("#delinfos").modal("show");
}

function delinfos(){
    var checkedList = new Array();
    $("input[name='infoOnePageId']:checked").each(function() {
        checkedList.push($(this).val());
    });
    if(checkedList.length > 0){
        $.post('delInfoOnePage.htm?CSRFToken='+$("#CSRFToken").val(),{infoOnePageIds:checkedList},function(){
            location.href="queryInfoOnePagesByPageBean.htm";
        });
    }
}

//判断是否选中按钮
function checkCombox(){
    var helpcateId = $("input[type='checkbox']:checked");
    //判断是否选择
    if(typeof(helpcateId.val())=="undefined"){
        showTipAlert("请至少选择一行!")
        return false;
    }else{
        return true;
    }
}

function addmodal(){
    $('#addSinglePage').modal('show');
    $("#imgView").attr("src","");
    $("#imgSrc").val("");
}
function update(){
    $("#up_content").val($("#up_contentstr").code());
    $("#up_titletip").html("");
    $("#up_titletip").removeClass("error");
    if($("#updateInfoOnePage").valid()){
        if(!checkAddInfoOPTitle($("#up_title").val(),$("#up_infoOnePageid").val())){
            $("#up_titletip").html("单页标题已存在！");
            $("#up_titletip").addClass("error");
            return;
        }
        if($("#up_keyword").val() == ""){
            $("#up_keyword").val($("#up_title").val());
        }
        if($("#up_description").val() == ""){
            $("#up_description").val($("#up_title").val());
        }
        $("#updateInfoOnePage").attr("action","updateInfoOnePage.htm?CSRFToken="+$("#CSRFToken").val());
        $("#updateInfoOnePage").attr("target","_self");
        $("#updateInfoOnePage").submit();
    }
}

function updateinfoModal(infoId){
    $.ajax({
        type:"POST",
        url:"showinfoonepageajax.htm?CSRFToken="+$("#CSRFToken").val(),
        data:"infoOnePageId="+infoId,
        success:function(data){
            //填充值
            $("#up_infoOnePageid").val(infoId);
            $("#up_title").val(data.title);
            $("#up_shortTitle").val(data.shortTitle);
            $("#up_infoTempId").val(data.infoOPTag.temp1);
            changeSelectup($("#up_infoTempId"));
            $("#up_infoOPTagId").val(data.infoOPTagId);
            $("#up_img").attr("src",data.imgSrc);
            $("#up_imgSrc").val(data.imgSrc);
            $("#up_keyword").val(data.keyword);
            if(data.isShow ==1){
                $("#isShow1").attr("checked","checked");
            }else{
                $("#isShow0").attr("checked","checked");
            }
            $("#up_description").val(data.description);
            $("#up_content").val(data.content);
            $("#up_contentstr").code(data.content);
        }
    });
    $('#updateSinglePage').modal('show');
}

function changeSelectup(temp){
    var tempId = $(temp).val();
    $.ajax({
        url:"getInfoOPTagByTempId.htm?CSRFToken="+$("#CSRFToken").val(),
        data:{tempId:tempId},
        success:function(data){
            $("#up_infoOPTagId").html("");
            if(data.length > 0){
                for(var i = 0; i < data.length; i++){
                    var option = $("<option>").val(data[i].infoopTagId).text(data[i].name);
                    $("#up_infoOPTagId").append(option);
                }
            }else{
                var option = $("<option>").val("").text("没有标签");
                $("#up_infoOPTagId").append(option);
            }
        },
        async: false
    });
}

