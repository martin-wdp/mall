var complainId="";
$(function() {
	var token = $("input[name='CSRFToken']").val();
	//为id为search_text文本添加name属性
	$(".search_text").attr("name", "orderNo");
	//为class为search_select下拉框添加name属性
	$("#search_select").attr("name","complainType");

	/**
	 * 未处理投诉列表确认回复方法
	 * */
	$("#okSubmit").click(function() {
		//定义正则表达式
		var reg= /^([\u4e00-\u9fa5_A-Za-z0-9 \\`\\~\\!\\@\\#\\$\\^\\&\*\(\)\=\{\}\'\:\;\'\,[\]\.\/\?\~\！\@\#\￥\…\…\&\*\（\）\;\—\|\{\}\【\】\‘\；\：\”\“\'\。\，\、\？])+$/;
		//从页面获取replayContext的值
		var replayContext=$("textarea[name='replayContext']").val();
		//判断replayContext的值是否为空
		if(replayContext==null || replayContext == "") {
			//replayContext为空 提示错误信息
			$("#treplay").text("请填写回复内容");
			return;
		}
		//判断replayContext的值是否合法
		if(!reg.test(replayContext)){
			//不合法提示错误信息
			$("#treplay").text("不能输入非法字符");
			return false;
		}
		$("#treplay").text("");
		var datas = "1=1";
		datas += "&complainId="+complainId;
		datas += "&replayContext="+replayContext;

		jQuery.ajax({
			//提交路径
			url:"replaycomplain.htm?CSRFToken="+token,
			//提交方式
			type:"post",
			//数据源
			data:datas,
			//回调函数
			success:function(html){
				//如果html返回的值为1代表操作成功
				//否则说明操作失败
				if(html==1){
					$("#replyComplaints").modal("hide");
					showTipAlert("回复成功！");
					//刷新页面
					window.location.href="ordercomplainback.htm?CSRFToken="+token;
				}else{
					showTipAlert("回复失败！");
				}
			}
		});
	});

	/**
	 *未处理投诉页面
	 * 搜索
	 */
	$("#search_com").button({
		icons: {
			primary: "ui-icon-search"//ui-icon-zoomout
		}
	}).click(function () {
		if (checkInputContent()) {
			//从页面获取id为search_select下拉框的值
			var selectVal=$("#search_select").val();
			//如果下拉框的值是“选择投诉类型”
			if(selectVal=="选择投诉类型"){
				//就把下拉框的name属性名变为空
				$("#search_select").attr("name","");
			}
			//为id为search_from的form表单设置action属性值
			$('#search_from').attr('action', 'ordercomplainback.htm?CSRFToken=' + token + 'attr=0&attr=' + $("#search_select").val() + "&attr=" + $(".search_text").val());
			//设置每页显示条数为15
			$("#pageSize").val(15);
			//设置为第一页
			$("#pageNo").val(1);
			//提交form表单
			$('#search_from').submit();
		}
	});

	/**
	 * 已处理投诉页面
	 * 搜索
	 */
	$("#search_comhad").button({
		icons: {
			primary: "ui-icon-search"//ui-icon-zoomout
		}
	}).click(function() {
		if(checkInputContent()){
			//从页面获取id为search_select下拉框的值
			var selectVal=$("#search_select").val();
			//如果下拉框的值是“选择投诉类型”
			if(selectVal=="选择投诉类型"){
				//就把下拉框的name属性名变为空
				$("#search_select").attr("name","");
			}
			//为id为search_fromhad的form表单设置action属性值
			$('#search_fromhad').attr('action', 'complainhad.htm?CSRFToken='+token+'attr=0&attr='+$("#search_select").val()+"&attr="+$(".search_text").val());
			//设置每页显示条数为15
			$("#pageSize").val(15);
			//设置为第一页
			$("#pageNo").val(1);
			//提交form表单
			$('#search_fromhad').submit();
		}
	});

});

/**
 * 点击每行后的回复按钮调用方法
 * */
function replyComplaintsShow(cpId,orderNo,complainTime,complainType,complainContext){
	//获取此行的complainId
	//并赋值给全局变量complainId
	complainId=cpId;
	$("#replayOrderNo").text(""+orderNo);
	$("#replaycomplainTime").text(""+complainTime);
	$("#replaycomplainType").text(""+complainType);
	$("#replaycomplainContext").text(""+complainContext);
	//弹出回复弹出框
	$("#replyComplaints").modal("show");
}

/**
 * 点击每行后的查看按钮调用方法
 * */
function replyComplaintsDetail(cpId,orderNo,complainTime,complainType,complainContext,replayContext){
	//获取此行的complainId
	//并赋值给全局变量complainId
	$("#replayOrderNoDetail").text(""+orderNo);
	$("#replaycomplainTimeDetail").text(""+complainTime);
	$("#replaycomplainTypeDetail").text(""+complainType);
	$("#replaycomplainContextDetail").text(""+complainContext);
	$("#replayContext").text(""+replayContext);
	//弹出回复弹出框
	$("#replyComplaintsDetail").modal("show");
}

//检查是否选中一行
function checkSelected(objId, modifyFlag) {
	checkedList = new Array();
	$("input[name='" + objId + "']:checked").each(function () {
		checkedList.push($(this).val());
	});
	if (modifyFlag != 0) {
		if (checkedList.length == 1) {
			return true;
		} else {
			return false;
		}
	}

	if (checkedList.length > 0) {
		return true;
	} else {
		return false;
	}
};

//检查值是否合法
function checkInputContent() {
	//定义正则表达式
	var regx = /^[\u4e00-\u9fa5_A-Za-z0-9]*$/;
	//把获得的值与正则表达式匹配
	var ff = regx.test($(".search_text").val());
	if (!ff) {
		$("#dialog-wrong-tip").dialog({
			resizable: false,
			height: 150,
			width: 270,
			modal: true,
			autoOpen: true,
			buttons: {
				"确定": function () {
					$(".search_text").val("");
					$(this).dialog("close");
				}
			}
		});
	}
	return ff;
}
