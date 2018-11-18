var checkedList;
/**
 * 搜索功能
 * */
function searchtype(){
	if(checkInputContent()){
		//$('#search_from').attr("action", "labellist.htm");
		$("#pageSize").val(5);
		$("#pageNo").val(1);
		$('#search_from').submit();
	}else{
	}
}

/**
 * 添加修改共用功能
 * */

function groupLabelSub(){
	if($("#addForm").valid()){
				$("#addForm").submit();
				$("#addGroupLabel").modal('hide');
			}
}

/**
 * 根据groupLabelId查询出数据，并绑定到修改页面
 * @param groupLabelId    标签id
 * */
function labelEdit(groupLabelId){
	modified=1;
	$.post("querygrouplabelbyid.htm?groupLabelId="+groupLabelId,function(data){
		$("#grouplabelId").val(data.groupLabelId);
		$("#oldgrouplabelName").val(data.groupLabelName);
		$("#groupLabelName").val(data.groupLabelName);
		$("#groupLabelSort").val(data.groupLabelSort);
		if(data.groupLabelStatus==0){
			$('#status1').prop('checked', 'checked');
		}else{
			$('#status2').prop('checked', 'checked');
		}
		$("#seotitle").val(data.seoTitle);
		$("#seokeyword").val(data.seoKeyWord);
		$("#seodesc").val(data.seoRemark);
		$("#addForm").attr('action', 'modifylabel.htm');
		$("#updateTitle").text("编辑小组分类");
		$("#addGroupLabel").modal('show');
	});
}

/**
 * 批量停用 通用
 * @param url 			停用路径
 * @param initUrl   	成功跳转路径
 */
function stopgroup(url,initUrl) {
	$.post(url,{groupLabelIds:checkedList},function(result){
		if (result > 0){
			location.href=initUrl;
		} else {

		}
	},'json');
}

/**
 * 单个停用 通用
 * @param url 			停用路径
 * @param initUrl   	成功跳转路径
 */
function stopgroupOne(url,initUrl,groupLabelId) {
	checkedList=new Array();
	checkedList.push(groupLabelId);
	$.post(url,{groupLabelIds:checkedList},function(result){
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
	$.post(url,{groupLabelIds:checkedList},function(result){
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
 */
function recoverygroupOne(url,initUrl,groupLabelId) {
	checkedList=new Array();
	checkedList.push(groupLabelId);
	$.post(url,{groupLabelIds:checkedList},function(result){
		if (result > 0){
			location.href=initUrl;
		} else {

		}
	},'json');
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
// 正则验证
function checkRegexp(o, regexp) {
	if (!(regexp.test(o))) {
		return false;
	} else {
		return true;
	}
}

$(function(){
	/**
	 * 显示添加弹出层
	 * */
	$("#create-label").click(function(){
		$("#addForm").attr('action','addlabel.htm');
		$("#grouplabelId").val("");
		$("#oldgrouplabelName").val("");
		$("#groupLabelName").val("");
		$("#groupLabelSort").val("");

		$("#seotitle").val("");
		$("#seokeyword").val("");
		$("#seodesc").val("");
		$('#addGroupLabel').modal('show');
	});

	/**
	 * 批量停用
	 * */
	$("#unuse-label").click(function(){
		if(checkSelected('groupLabelId',0)){
			stopgroup("unuselabel.htm","labellist.htm");
		}else{
			showTipAlert("请先选择一行！");
		}
	});

	/**
	 * 批量恢复
	 * */
	$("#recovery-label").click(function() {
		if(checkSelected('groupLabelId',0)){
			recoverygroup("recoverylabel.htm","labellist.htm");
		}else{
			showTipAlert("请先选择一行！")
		}
	});
});