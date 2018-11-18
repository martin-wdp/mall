$(function(){
    $("#picFile").change(function(){
        $("#addform").attr("action","uploadImg.htm");
        $("#addform").attr("target","uploadFrame");
        $("#addform").submit();
    });
    $("#up_picFile").change(function(){
        $("#updateform").attr("action","uploadImg.htm");
        $("#updateform").attr("target","uploadFrame");
        $("#updateform").submit();
    });
    $('.helpcateimg').popover({
        content : '建议36*36px',
        trigger : 'hover'
    });
    
});

/**
 * 图片上传回调方法
 * @param data 图片链接或者信息
 */
function callback(data) {
    $("#imgView").attr("src",data);
    $("#helpcateImg").val(data);
    $("#up_imghead").attr("src",data);
    $("#up_helpcateImg").val(data);
}

/**
 * 弹框显示单个删除帮助分类
 * @param helpcateId
 */
function delhelpYN(helpcateId){
	$("#delhelpId").val(helpcateId);
	$("#delhelp").modal("show");
}

/**
 * 确定单个删除帮助分类
 */
function delhelp(){
	var checkedList = new Array();
	checkedList.push($("#delhelpId").val());
	$.post('delhelpcate.htm?CSRFToken='+$("#CSRFToken").val(),{cateIds:checkedList},function(result){    
        if (result > 0){
      	  location.href="tohelpcate.htm"; 
        } else { 
        }  
    },'json');
	$("#delhelp").modal("hide");
}

/**
 * 确定批量删除帮助分类
 */
function delhelps(){
	//删除数据
	var checkedList = new Array();
	$("input[name='helpcateId']:checked").each(function() {
		checkedList.push($(this).val());
	}); 
	
	if(checkedList.length > 0){
              $.post('delhelpcate.htm?CSRFToken='+$("#CSRFToken").val(),{cateIds:checkedList},function(result){  
            	  
                  if (result > 0){

                	  location.href="tohelpcate.htm";
                	  
                  } else { 
                  }  
              },'json');   
      }
	$("#delhelps").modal("hide");
}

/**
 * 弹框显示批量删除帮助分类
 * @returns {boolean}
 */
function delhelpsYN(){
	if(checkCombox()==false){
		  return false;
	  }
	$("#delhelps").modal("show");
}

/**
 * 判断是否选中按钮
 * @returns {boolean}
 */
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

/**
 * 确定添加帮助分类
 */
var num=0;
function addhelp(){
	if($("#addform").valid()&&num==0){
        num+=1;
        $("#addform").attr("action","addhelpcate.htm?CSRFToken="+$("#CSRFToken").val());
        $("#addform").attr("target","_self");
		$("#addform").submit();
	}
}

/**
 * 弹框显示添加帮助分类
 */
function addModal(){
    $('#addHelpCate').modal('show');
    $("#imgView").attr("src","");
    $("#helpcateImg").val("");
}

/**
 * 搜索帮助分类
 */
function helpss(){
	$("#searchTextstr").val($("#searchText").val());
	$("#tohelpform").submit();
}

/**
 * 弹框显示编辑帮助分类
 * @param helpcateId
 */
function updatemodal(helpcateId){
	$.ajax({
		type:"POST",
		url:"findhelpcateone.htm?CSRFToken="+$("#CSRFToken").val(),
		 
		data:"helpcateId="+helpcateId,
		success:function(data){
			//填充值 
			$("#up_cateid").val(data.helpcateId);
			$("#up_catename").val(data.helpcateName);
			$("#up_catesort").val(data.helpcateSort);
			if(data.homeFloor== 1){
				$("#radio1").attr("checked","checked");
			}else{
				$("#radio0").attr("checked","checked");
			}
			$("#up_helpcateImg").val(data.helpcateImg);
			$("#up_imghead").attr("src",$("#basePath").val()+data.helpcateImg);
		}
	}); 
	$('#updateHelpCate').modal('show');
}

/**
 * 确定修改帮助分类
 */
function updatehelp(){
	if($("#updateform").valid()){
        $("#updateform").attr("action","updatehelpcate.htm?CSRFToken="+$("#CSRFToken").val());
        $("#updateform").attr("target","_self");
		$("#updateform").submit();
	}
}