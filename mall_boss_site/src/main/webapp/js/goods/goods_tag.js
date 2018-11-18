$(function(){
    $("#tagImage").change(function() {
        $("#saveTagForm").attr("action","uploadImg.htm");
        $("#saveTagForm").attr("target","uploadFrame");
        $("#saveTagForm").submit();
    });

    $("#tagImage_update").change(function() {
        $("#updateTagForm").attr("action","uploadImg.htm");
        $("#updateTagForm").attr("target","uploadFrame");
        $("#updateTagForm").submit();
    });
    $('.tagLogo').popover({
        content : '建议129*50px',
        trigger : 'hover'
    });
});

function showAddTag() {
    $("#preview_image").removeAttr("src");
    $('#tagAdd').modal('show');
}

/**
 * 图片上传回调方法
 * @param data 图片链接或者信息
 */
function callback(data) {
    $("#preview_image").attr("src",data);
    $("#preview_image_update").attr("src",data);
}


function checkTagNameExists(obj) {
    $(obj).removeClass("error");
    $(".value_tip").remove();
    if($(obj).val()!='') {
        $.ajax({
            url:"checkTagName.htm?tagName="+$(obj).val()+"&CSRFToken="+$("#CSRFToken").val(),
            async:false,
            success:function(data) {
                if (data == false) {
                    $(obj).addClass("error");
                    $(obj).after('<label generated="true" class="error value_tip" id="expand_value_tip">名称已存在</label>');
                } else {
                    $(obj).removeClass("error");
                    $(obj).next().remove();
                }
            }
        });
    }
}
var num=0;
function submitSaveTagForm() {
    $("#saveTagForm").find("input[name=tagName]").removeClass("error");
    $(".value_tip").remove();
    if($("#saveTagForm").find("input[name=tagName]").val()=='') {
        $("#saveTagForm").find("input[name=tagName]").addClass("error");
        $("#saveTagForm").find("input[name=tagName]").after('<label generated="true" class="error value_tip" id="expand_value_tip">不能为空</label>');
        return;
    }
    checkTagNameExists($("#saveTagForm").find("input[name=tagName]"));
    if($("#saveTagForm").find(".error").length>0) return;
    if(num==0){
        num+=1;
        $("#saveTagForm").attr("action","addTag.htm?CSRFToken="+$("#CSRFToken").val());
        $("#saveTagForm").attr("target","_self");
        $("#tagAdd").modal("hide");
        $("#saveTagForm").submit();
    }

}

function showEditTagForm(tagId, tagName,tagImg) {
    $("#tagId").val(tagId);
    $("#tagName").val(tagName);
    $("#oldTagName").val(tagName);
    $("#preview_image_update").attr("src",tagImg);
    $("#tagEdit" ).modal("show");
}

function submitUpdateTagForm() {
    $("#updateTagForm").find("input[name=tagName]").removeClass("error");
    $(".value_tip").remove();
    var obj = $("#updateTagForm").find("input[name=tagName]");
    if($(obj).val()=='') {
        $(obj).addClass("error");
        $(obj).after('<label generated="true" class="error value_tip" id="expand_value_tip">不能为空</label>');
        return;
    }
    if($(obj).val()!=$("#oldTagName").val()) {
        checkTagNameExists($("#updateTagForm").find("input[name=tagName]"));
    }

    if($("#updateTagForm").find(".error").length>0) return;
    $("#updateTagForm").attr("action","updateTag.htm?CSRFToken="+$("#CSRFToken").val());
    $("#updateTagForm").attr("target","_self");
    $("#tagEdit").modal("hide");
    $("#updateTagForm").submit();
}