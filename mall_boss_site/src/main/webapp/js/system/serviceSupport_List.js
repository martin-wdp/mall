$(function(){
    $("#picFile").change(function() {
        $("#showserviceSupportForm").attr("action","uploadImg.htm");
        $("#showserviceSupportForm").attr("target","uploadFrame");
        $("#showserviceSupportForm").submit();
    });
    $("#up_picFile").change(function() {
        $("#upshowserviceSupportForm").attr("action","uploadImg.htm");
        $("#upshowserviceSupportForm").attr("target","uploadFrame");
        $("#upshowserviceSupportForm").submit();
    });
    //服务支持图标右侧问号
    $('.serviceimg').popover({
        content : '建议16*16px',
        trigger : 'hover'
    });
});

/**
 * 图片上传回调方法
 * @param data 图片链接或者信息
 */
function callback(data) {
    $("#imgView").attr("src",data);
    $("#serviceImageUrl").val(data);
    $("#img_up").attr("src",data);
    $("#up_serviceImageUrl").val(data);
}

/**
 * 弹框显示删除单个服务支持
 * @param helpcateId
 */
function delsupportYN(helpcateId){
	$("#delserid").val(helpcateId);
	$("#delsupport").modal("show");
}

/**
 * 确定删除单个服务支持
 */
function delsupport(){
	var checkedList = new Array();
	checkedList.push($("#delserid").val());
	$.post('updateServiceSupportDelfalg.htm?CSRFToken='+$("#CSRFToken").val(),{id:checkedList},function(){  
    	location.href="selectServiceSupportList.htm";
    });   
	$("#delsupport").modal("hide");
}

/**
 * 确定批量删除服务支持
 */
function delsupports(){
	//删除数据
	var checkedList = new Array();
	$("input[name='id']:checked").each(function() {
		checkedList.push($(this).val());
	}); 
	
	if(checkedList.length > 0){
		$.post('updateServiceSupportDelfalg.htm?CSRFToken='+$("#CSRFToken").val(),{id:checkedList},function(){  
	    	location.href="selectServiceSupportList.htm";
	    }); 
      }
	$("#delsupports").modal("hide");
}

/**
 * 弹框显示批量删除服务支持
 * @returns {boolean}
 */
function delsupportsYN(){
	if(checkCombox()==false){
		  return false;
	  }
	$("#delsupports").modal("show");
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
 * 弹框显示添加服务支持
 */
function addmodal(){
    $('#addSupport').modal('show');
    $("#imgView").attr("src","");
    $("#serviceImageUrl").val("");
}

/**
 * 确定添加服务支持
 */
var num=0;
function addsupport(){
    if($("#showserviceSupportForm").valid()&&num==0){
        num+=1;
        $("#showserviceSupportForm").attr("action","newServiceSupport.htm?CSRFToken="+$("#CSRFToken").val());
        $("#showserviceSupportForm").attr("target","_self");
        $("#showserviceSupportForm").submit();
    }
}

/**
 * 确定修改服务支持
 */
function upsupport(){
    if($("#upshowserviceSupportForm").valid()){
        $("#upshowserviceSupportForm").attr("action","updateServiceSupport.htm?CSRFToken="+$("#CSRFToken").val());
        $("#upshowserviceSupportForm").attr("target","_self");
        $("#upshowserviceSupportForm").submit();
    }
}

/**
 * 弹框显示编辑服务支持
 * @param id
 */
function upmodal(id){
    $.ajax({
        type:"POST",
        url:"selectservicesupportbyidajax.htm?CSRFToken="+$("#CSRFToken").val(),

        data:"id="+id,
        success:function(data){
            //填充值
            $("#up_serviceName").val(data.serviceName);
            $("#up_id").val(data.id);
            //$("#up_catesort").val(data.helpcateSort);
            $("#up_serviceImageUrl").val(data.serviceImageUrl);
            $("#img_up").attr("src",$("#basePath").val()+data.serviceImageUrl);
        }
    });
    $('#upSupport').modal('show');
}

