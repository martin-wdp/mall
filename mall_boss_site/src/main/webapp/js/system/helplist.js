/**
 * 弹框显示单个删除
 * @param helpcateId
 */
function delhelpYN(helpcateId){
	$("#delhelpId").val(helpcateId);
	$("#delhelp").modal("show");
}

/**
 * 确定单个删除
 */
function delhelp(){
	var checkedList = new Array();
	checkedList.push($("#delhelpId").val());
	if(checkedList.length > 0){
              $.post('delhelp.htm?CSRFToken='+$("#CSRFToken").val(),{helpIds:checkedList},function(result){  
                  if (result > 0){
                	  location.href="findcenter.htm";
                  } else { 
                  }  
              },'json');   
      }
}

/**
 * 判断是否选中按钮
 * @returns {boolean}
 */
function checkCombox(){
	  var helpcateId = $("input[name='helpId']:checked");
	  //判断是否选择
	  if(typeof(helpcateId.val())=="undefined"){
		  showTipAlert("请至少选择一行!")	  
		  return false;
	  }else{
		  return true;
	  }
}

/**
 * 确定删除批量删除帮助
 */
function delhelps(){
	//删除数据
	var checkedList = new Array();
	$("input[name='helpId']:checked").each(function() {
		checkedList.push($(this).val());
	}); 
	
	if(checkedList.length > 0){
              $.post('delhelp.htm?CSRFToken='+$("#CSRFToken").val(),{helpIds:checkedList},function(result){  
            	  
                  if (result > 0){

                	  location.href="findcenter.htm";
                	  
                  } else { 
                  }  
              },'json');   
      }
	$("#delhelps").modal("hide");
}

/**
 * 弹框显示批量删除帮助
 * @returns {boolean}
 */
function delhelpsYN(){
	if(checkCombox()==false){
		  return false;
	  }
	$("#delhelps").modal("show");
}

/**
 * 修改
 */
function helpss(){
	$("#searchTextstr").val($("#searchText").val());
	$("#tohelpcenterform").submit();
}

/**
 * 弹框显示添加帮助
 */
function addmodal(){
	$('#addHelpArticle').modal('show');
    $("#addhelpPic").attr("src","");
    $("#helpImg").val("");
}

/**
 * 确定添加帮助
 */
var num=0;
function addhelp(){
    if($("#helpForm").valid()&&num==0){
        num+=1;
    	$("#helpForm").attr("action","addhelp.htm?CSRFToken="+$("#CSRFToken").val());
        $("#helpForm").attr("target","_self");
        $("#helpContent").val($("#helpContentstr").code());
        $("#helpForm").submit();
    }
}

/**
 * 弹框显示修改帮助
 * @param id
 */
function updatemodal(id){
    $.ajax({
        type:"POST",
        url:"tohelpcenterajax.htm?CSRFToken="+$("#CSRFToken").val(),

        data:"helpId="+id,
        success:function(data){
            //填充值
            $("#up_helpid").val(data.helpId);
            $("#up_helpcateId").val(data.helpcateId);
            $("#up_helpTitle").val(data.helpTitle);
            $("#up_helpAuthor").val(data.helpAuthor);
            $("#up_helpSort").val(data.helpSort);
            $("#up_helpContentstr").code(data.helpContent);
            $("#up_helpContent").val(data.helpContent);
            if(data.isFoot== 1){
                $("#isFoot1").attr("checked","checked");
            }else{
                $("#isFoot0").attr("checked","checked");
            }
            $("#up_helpImg").val(data.helpImg);
            if(data.helpImg!=null){
            	  $("#up_helpPic").attr("src",$("#baseUrl").val()+data.helpImg);
            }
        }
    });
    $('#updateHelpArticle').modal('show');
}

$(function(){
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
        $("#helpForm").attr("action","uploadImg.htm");
        $("#helpForm").attr("target","uploadFrame");
        $("#helpForm").submit();
    });
    $("#up_picFile").change(function(){
        $("#up_helpForm").attr("action","uploadImg.htm");
        $("#up_helpForm").attr("target","uploadFrame");
        $("#up_helpForm").submit();
    });
});

/**
 * 图片上传回调方法
 * @param data 图片链接或者信息
 */
function callback(data) {
    $("#addhelpPic").attr("src",data);
    $("#helpImg").val(data);
    $("#up_helpPic").attr("src",data);
    $("#up_helpImg").val(data);
}

/**
 * 确定修改帮助列表
 */
function updatehelp(){
    if($("#up_helpForm").valid()){
    	$("#up_helpForm").attr("action","updatehelp.htm?CSRFToken="+$("#CSRFToken").val());
        $("#up_helpForm").attr("target","_self");
        $("#up_helpContent").val($("#up_helpContentstr").code());
        $("#up_helpForm").submit();
    }
}