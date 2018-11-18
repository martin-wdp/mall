var checkedList;
/**
 * 搜索功能
 * */
function searchtype(){
	if(checkInputContent()){
		var typename = $(".search_text").val();
		typename =  encodeURI(encodeURI(typename));
		$('#search_from').attr("action", "querybygrouptype.htm?attr="+typename);
		$("#pageSize").val(5);
		$("#pageNo").val(1);
		$('#search_from').submit();
	}else{
	}
}

/**
 * 添加小组分类
 * */
function addGroupCate(){
	//var bValid=false;
	//var groupTypeName=$('#groupTypeName').val();
	//var groupTypeSort=$('#groupTypeSort').val();
	//bValid = checkSpecSymbNew(groupTypeName);
	//if(bValid==false){
	//	$(".groupTypeNameTips").text("请输入合法字符！");
	//}else{
	//	if(groupTypeName.trim().length <= 0){
	//		$(".groupTypeNameTips").text("请输入分类名称！");
	//	}else{
	//		$(".groupTypeNameTips").text("");
	//		bValid=checkRegexp(groupTypeSort,/^[0-9]+$/);
	//		if(bValid==false){
	//			$(".groupTypeSort").text("排序必须为正整数.");
	//		}else{
	//			$(".groupTypeSort").text("");
	if($("#dialog-form").valid()){
				$("#dialog-form").submit();
				$("#addGroupCate").modal('hide');
	}
			//}
		//}
	//

}

// 正则验证
function checkRegexp(o, regexp) {
	if (!(regexp.test(o))) {
		return false;
	} else {
		return true;
	}
}

//验证特殊字符，将调试返回给调用者
function checkSpecSymbNew(inputobj){
	var regexp=new RegExp("[''\\[\\]<>?!]");
	if (regexp.test( $("#"+inputobj).val() ) ) {
		$("#"+inputobj).addClass( "ui-state-error" );
		return false;
	}else {
		$("#"+inputobj).removeClass( "ui-state-error" );
		return true;
	}
}

/**
 * 非法字符验证
 * */
function checkInputContent(){
	var regx=/^[\u4e00-\u9fa5_A-Za-z0-9]*$/;
	var ff=regx.test($(".search_text").val());
	if(!ff){
		$("#dialog-wrong-tip").dialog({
			resizable : false,
			height : 150,
			width : 270,
			modal : true,
			autoOpen : true,
			buttons : {
				"确定" : function() {
					$(".search_text").val("");
					$(this).dialog("close");
				}
			}
		});
	}
	return ff;
}


//删除小组分类
function delGroupType(groupTypeId){
	showDeleteOneConfirmAlert('deletebyid.htm?groupTypeId=' + groupTypeId,'确定要删除此小组分类吗？');
}

//检查是否选中一行
function checkSelected(objId,modifyFlag){
	checkedList = new Array();
	$("input[name='"+objId+"']:checked").each(function() {
		checkedList.push($(this).val());
	});
	if(modifyFlag!=0){
		if(checkedList.length ==1){
			return true;
		}else{
			return false;
		}
	}

	if(checkedList.length > 0){
		return true;
	}else{
		return false;
	}
};

/**
 * 单个停用 通用
 * @param url 			停用路径
 * @param initUrl   	成功跳转路径
 * @param groupTypeId   小组分类id
 * */
function stopType(url,initUrl,groupTypeId){
	checkedList=new Array();
	checkedList.push(groupTypeId);
	$.post(url,{groupTypeIds:checkedList},function(result){
		if (result > 0){
			location.href=initUrl;
		} else {

		}
	},'json');
}

/**
 * 单个恢复 通用
 * @param url 			恢复路径
 * @param initUrl   	成功跳转路径
 * @param groupTypeId   小组分类id
 * */
function recoveryType(url,initUrl,groupTypeId){
	checkedList=new Array();
	checkedList.push(groupTypeId);
	$.post(url,{groupTypeIds:checkedList},function(result){
		if (result > 0){
			location.href=initUrl;
		} else {

		}
	},'json');
}

/**
 * 批量停用 通用
 * @param url 			停用路径
 * @param initUrl   	成功跳转路径
 */
function stopgroup(url,initUrl) {
	$.post(url,{groupTypeIds:checkedList},function(result){
		if (result > 0){
			location.href=initUrl;
		} else {

		}
	},'json');
}

/**
 * 批量恢复 通用
 * @param url 			恢复路径
 * @param initUrl   	成功跳转路径
 */
function recoverygroup(url,initUrl) {
	$.post(url,{groupTypeIds:checkedList},function(result){
		if (result > 0){
			location.href=initUrl;
		} else {

		}
	},'json');
}


/**
 * 编辑小组
 *
 * */
function modifiedType(groupTypeId){
	$('#dialog-form').attr('action', 'updategrouptype.htm');
	doSearchGroupType(groupTypeId);
}

/*ajax 通过ID查询并塞值到修改页面*/
function doSearchGroupType(groupTypeId){
	modified=1;
	$.post("querygrouptypebyid.htm?groupTypeId="+groupTypeId,function(data){
		$("#groupTypeId").val(data.groupTypeId);
		$("#oldgroupTypeName").val(data.groupTypeName);
		$("#groupTypeName").val(data.groupTypeName);
		$("#groupTypeSort").val(data.groupTypeSort);
		if(data.groupTypeStatus==0){
			$('#status1').prop('checked', 'checked');
		}else{
			$('#status2').prop('checked', 'checked');
		}
		$("#groupTypeRemark").val(data.groupTypeRemark);
		$("#seotitle").val(data.seoTitle);
		$("#seokeyword").val(data.seoKeyWord);
		$("#seodesc").val(data.seoRemark);
		$("#addForm").attr('action', 'updategrouptype.htm');
		$("#updateTitle").text("编辑小组分类");
		$("#addGroupCate").modal('show');
	});
}


$(function() {
	$("#stop-type").click(function() {
		if(checkSelected('groupTypeId',0)){
			stopgroup("disablegroup.htm","grouptype.htm");
		}else{
			showTipAlert("请先选择一行！");
		}
	});

	// 批量恢复小组分类
	$("#recovery-type").click(function() {
		if(checkSelected('groupTypeId',0)){
			recoverygroup("recoverygroup.htm","grouptype.htm");
		}else{
			showTipAlert("请先选择一行！");
		}
	});

});