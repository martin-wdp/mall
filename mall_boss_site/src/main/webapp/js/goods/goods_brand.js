$(function(){
    $("#importGoodsBrandForm").validate();
    $("#saveBrandForm").validate({ ignore:".ignore"});
    $("#editBrandForm").validate({ ignore:".ignore"})
    //添加品牌框里的图片预览
    $("#logoFile").change(function() {
        $("#saveBrandForm input").each(function () {
            $(this).addClass("ignore");
        });
        $("#saveBrandForm").attr("action","uploadImg.htm");
        $("#saveBrandForm").attr("target","uploadFrame");
        $("#saveBrandForm").submit();
    });
    //编辑品牌框里的图片预览
    $("#logoFile_update").change(function() {
        $("#editBrandForm input").each(function () {
            $(this).addClass("ignore");
        });
        $("#editBrandForm").attr("action","uploadImg.htm");
        $("#editBrandForm").attr("target","uploadFrame");
        $("#editBrandForm").submit();
    });
    $('.brandLogo').popover({
        content : $("#suffixArray").val()+',建议119*60px',
        trigger : 'hover'
    });
    $("#addBrandName").keyup(function() {
        var brandName = $("#addBrandName").val();
        if (brandName == '') {
            $("#helpTip").hide();
        }
    });
    $("#addBrandName").blur(function() {
        var brandName=$("#addBrandName").val();
        $.ajax({
            url: 'selectByBrandName.htm?brandName=' + brandName + "&CSRFToken=" + $("#CSRFToken").val(),
            success: function (data) {
                if (data == 0) {
                    //不重复去掉错误提示
                    $("#addBrandName").removeClass("error");
                    $("#helpTip").hide();
                } else {
                    //重复错误提示
                    $("#addBrandName").addClass("error");
                    $("#helpTip").show();
                }
            }
        });
    });
});

/**
 * 图片上传回调方法
 * @param data 图片链接或者信息
 */
function callback(data) {
    $("#preview_image").attr("src",data);
    $("#preview_image_update").attr("src",data);
}

/**
 * 提交添加品牌form
 */
var num=0;
function submitSaveBrandForm() {
    var brandName=$("#addBrandName").val();
    if(brandName==''){
        $("#helpTip").hide();
    }
    $("#saveBrandForm input").each(function () {
        $(this).removeClass("ignore");
    });
    $("#saveBrandForm").attr("action","saveBrand.htm");
    $("#saveBrandForm").attr("target","_self");
    if(brandName!=null && brandName!=''&&num==0){
        $.ajax({
            url:'selectByBrandName.htm?brandName='+brandName+"&CSRFToken="+$("#CSRFToken").val(),
            success:function(data) {
                if(data==0) {
                    num+=1;
                   //不重复则提交表单
                    $("#addBrandName").removeClass("error");
                    $("#helpTip").hide();
                    $("#saveBrandForm").submit();
                }else{
                    //重复错误提示
                    $("#addBrandName").addClass("error");
                    $("#helpTip").show();
                }
            }
        });
    }else{
        $("#addBrandName").removeClass("error");
        $("#helpTip").hide();
        $("#saveBrandForm").submit();
    }
}

/**
 * 提交编辑品牌form
 */
function submitUpdateBrandForm() {
    $("#editBrandForm input").each(function () {
        $(this).removeClass("ignore");
    });
    $("#editBrandForm").attr("action","updateBrand.htm");
    $("#editBrandForm").attr("target","_self");
    $("#editBrandForm").submit();
}

/**
 * 弹出显示编辑品牌
 * @param brandId
 */
function showEditBrandForm(brandId) {
    $("#brandId").val(brandId);
    $.ajax({
        url:'queryBrandById.htm?brandId='+brandId+"&CSRFToken="+$("#CSRFToken").val(),
        success:function(data) {
            $("#brandName").val(data.brandName);
            $("#brandNickname").val(data.brandNickname);
            $("#preview_image_update").attr("src",data.brandLogo);
            $("#brandUrl").val(data.brandUrl);
            $("#brandSort").val(data.brandSort);
            $("#promIndex"+data.promIndex).click();
            $("#editBrand").modal("show");
        }
    });
}

function showImport() {
    $("#importBrand").modal("show");
}

function importBrand() {
    $("#importGoodsBrandForm").submit();
}

function import_callback(result) {
    if(result=="200") {
        showTipAlert("导入成功");
        location.reload();
    }else if(result == "" || result == null){
        showTipAlert("导入失败")
    } else {
        showTipAlert(result);
    }
}