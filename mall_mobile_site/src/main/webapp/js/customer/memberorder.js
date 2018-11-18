$(function(){
	$(".order_filter li").click(function(){
		var t_param = "";
		if (!$(".t_param").val() == "商品名称或订单编号") {
			t_param = "-" + $(".t_param").val();
		}
		window.location.href = "../customer/myorder-"+ $(this).attr("data-val") + "-1" + t_param + ".html";
	});
	$(".rateit").each(function(){
		$(this).click(function(){
			$(this).next().val(parseInt($(this).find(".rateit-selected").css("width"))/16);
		});
	});
});

var comUrl="";
//确认收货
function comfirmgoods(url) {
	$('#confirm_order').modal('show');
//	$("#go_pay_01").prop("href", url);
	comUrl=url;
}
function comfirmGoodsSucc(){
	location.href=comUrl;
	$('#confirm_order').modal('hide');
}
//取消订单
var cUrl = "";
function cancelOrder(url) {
	$('#cancel_order').modal('show');
	cUrl = url;
}
function changeUrl(){
		var paramStr = "?reason=";
		if($(".sel_txa").val().trim().length < 10||$(".sel_txa").val()==""||$(".sel_txa").val()==null){
			$("#titlereason").html("不能为空，请输入10个以上字符!");
			return;
		}else{
			$("#titlereason").html("");
		}
		$("#titlereason").html("");
		paramStr+=$(".sel_txa").val();
		location.href=cUrl+paramStr;
		$('#cancel_order').modal('hide');
}
//清空提示
function clearmess(){
	$("#titlereason").html("");
	$("#sel_txa").val("");
}

//商品评论
function checkComment(id){
	if(! checkScore(id)){
		return;
	}
	if(! checkContext(id)){
		return;
	}
	$("#commForm"+id).submit();
}

//评分
function checkScore(id){
	var cts=".cts";
	if($(cts+id).val()==0){
		$("#commTip"+id).text("请选择评分！");
		$("#commTip"+id).addClass("err");
		return false;
	}
	$("#commTip"+id).text("");
	$("#commTip"+id).removeClass("err");
	return true;
}




//评论内容
function checkContext(id){
	var str = $("#complaincon"+id).val();
	var reg= /^([\u4e00-\u9fa5_A-Za-z0-9 \\`\\~\\!\\@\\#\\$\\^\\&\*\(\)\=\{\}\'\:\;\'\,[\]\.\/\?\~\！\@\#\￥\…\…\&\*\（\）\;\—\|\{\}\【\】\‘\；\：\”\“\'\。\，\、\？])+$/;
	//内容长度最多500
	if(str.trim().length>500 || str.trim().length<10) {
		$("#commTip"+id).text("字数在10-500之间!");
		$("#commTip"+id).addClass("err");
		return false;
	}
	if(!reg.test(str)){
		$("#commTip"+id).text("不能包含特殊字符!");
		$("#commTip"+id).addClass("err");
		return false;
	}
	$("#commTip"+id).text("");
	$("#commTip"+id).removeClass("err");
	return true;
}


