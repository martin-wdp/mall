$(function(){
    $("#logoImage").change(function() {
        $("#saveLogoForm").attr("action","uploadImg.htm");
        $("#saveLogoForm").attr("target","uploadFrame");
        $("#saveLogoForm").submit();
    });

        $("#logoImage_update").change(function() {
        $("#updateLogoForm").attr("action","uploadImg.htm");
        $("#updateLogoForm").attr("target","uploadFrame");
        $("#updateLogoForm").submit();
    });
    $('.tagLogo').popover({
        content : '建议129*50px',
        trigger : 'hover'
    });
});

function showAddLogo() {
    $("#preview_image").removeAttr("src");
    $('#logoAdd').modal('show');
}

/**
 * 图片上传回调方法
 * @param data 图片链接或者信息
 */
function callback(data) {
    $("#preview_image").attr("src",data);
    $("#preview_image_update").attr("src",data);
}


function checkLogoNameExists(obj) {
    $(obj).removeClass("error");
    $(".value_tip").remove();
    if($(obj).val()!='') {
        $.ajax({
        	type:'post',
            url:"checklogoname.htm?promotionLogoName="+$(obj).val()+"&CSRFToken="+$("#CSRFToken").val(),
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
function submitSaveLogoForm() {
   /* $("#saveTagForm").find("input[name=tagName]").removeClass("error");
    $(".value_tip").remove();
    if($("#saveTagForm").find("input[name=tagName]").val()=='') {
        $("#saveTagForm").find("input[name=tagName]").addClass("error");
        $("#saveTagForm").find("input[name=tagName]").after('<label generated="true" class="error value_tip" id="expand_value_tip">不能为空</label>');
        return;
    }*/
	if($("#saveLogoForm").valid()){
		checkLogoNameExists($("#saveLogoForm").find("input[name=promotionLogoName]"));
	}
    if($("#saveLogoForm").find(".error").length>0) return;
    $("#saveLogoForm").attr("action","addlogo.htm?CSRFToken="+$("#CSRFToken").val());
    $("#saveLogoForm").attr("target","_self");
    $("#logoAdd").modal("hide");
    if(num==0){
        num+=1;
        $("#saveLogoForm").submit();
    }

}

function showEditLogoForm(logoId, logoName,logoImg) {
    $("#promotionLogoId").val(logoId);
    $("#logoName").val(logoName);
    $("#oldLogoName").val(logoName);
    $("#preview_image_update").attr("src",logoImg);
    $("#logoEdit" ).modal("show");
}

function submitUpdateLogoForm() {
   /* $("#updateLogoForm").find("input[name=Name]").removeClass("error");
    $(".value_tip").remove();
    if($(obj).val()=='') {
        $(obj).addClass("error");
        $(obj).after('<label generated="true" class="error value_tip" id="expand_value_tip">不能为空</label>');
        return;
    }*/
	var obj = $("#updateLogoForm").find("input[name=promotionLogoName]");
    if($("#updateLogoForm").valid()) {
    	if($(obj).val()!=$("#oldLogoName").val()){
    		checkLogoNameExists($("#updateLogoForm").find("input[name=promotionLogoName]"));
    	}else{
    		 $(obj).removeClass("error");
             $(obj).next().remove();
    	}
    }
    if($("#updateLogoForm").find(".error").length>0) return;
    /*$.ajax({
    	type:'post',
    	url:'updatelogo.htm?CSRFToken='+$("#CSRFToken").val(),
        data:$("#updateLogoForm").serialize(),
        success: function (data) {
            location.reload();
        }
    });*/
    $("#updateLogoForm").attr("action","updatelogo.htm?CSRFToken="+$("#CSRFToken").val());
    $("#updateLogoForm").attr("target","_self");
    $("#logoEdit").modal("hide");
    $("#updateLogoForm").submit();
}