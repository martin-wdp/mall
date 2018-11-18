$(function(){
    $("#logoImage").change(function() {
        $("#saveLogoForm").attr("action","uploadImg.htm");
        $("#saveLogoForm").attr("target","uploadFrame");
        $("#saveLogoForm").submit();
    });

    $('.tagLogo').popover({
        content : '建议129*50px',
        trigger : 'hover'
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

function submitSaveLogoForm() {
	if($("#saveLogoForm").valid()){
		checkLogoNameExists($("#saveLogoForm").find("input[name=promotionLogoName]"));
	}
    if($("#saveLogoForm").find(".error").length>0) return;
//    $("#saveLogoForm").attr("action","addthirdlogo.htm");
    $("#saveLogoForm").attr("target","_self");
    $("#addPromotions").modal("hide");
    $('#saveLogoForm').form('submit',{ 
    	type:"post",
    	url:'addthirdlogo.htm',
    	async:true,
    	dataType:"json",
    	data: $("#saveLogoForm").serialize(),
    	success: function (result) {
             result=eval('('+result+')');
             var html = "<label class='choose-label'>";
             	 html +="<input type='checkbox' name='promotionLogoId' value='"+result.promotionLogoId+"'>";
	             html +="<img alt='"+result.promotionLogoName+"' title='"+result.promotionLogoName+"' src='"+result.promotionLogoUrl+"' height='20'>";                           
	             html +="</label>";
	             $("#logolabel").prepend(html);
         }
    	
    });
}


