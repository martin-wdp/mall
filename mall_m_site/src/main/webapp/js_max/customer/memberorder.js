var basePath = $("#basePath").val();
$(function(){
	$(".order_filter li").click(function(){
		var t_param = "";
		if (!$(".t_param").val() == "商品名称或订单编号") {
			t_param = "-" + $(".t_param").val();
		}
		window.location.href = "../customer/myorder-"+ $(this).attr("data-val") + "-1" + t_param + ".html";
	});
	$(".rateit li").click(function(){
        $(".rateit li").css("background-position","left top");
        var n = $(this).index();var m = 0;
        $(this).parent().children("li").each(function(){
            if(m > n){
                return 0;
            }else{
                $(this).css("background-position","left -16px");
            }
            m += 1;
        });
        $(this).parents(".rateit").next().val(n+1);
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
function changeUrl(orderId){
    var orderid=$("#canorder").val();
        cUrl ='/m/customer/cancelorder-myorder-'+orderid+'.html'
		var paramStr = "?reason=";
		/*if($(".sel_txa").val().trim().length < 2||$(".sel_txa").val()==""||$(".sel_txa").val()==null){
			$("#titlereason").html("不能为空，请输入2个以上字符!");
			return;
		}else{
			$("#titlereason").html("");
		}*/
		$("#titlereason").html("");
		paramStr+=$(".sel_txa").val();
		location.href=cUrl;
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
//商品评论 跳转商品的提交评价页
function toAddComment(id,basePath,orderId,goodsPrice,goodsName,orderNo,goodsImg,goodsNum,commentId,orderGoodsId){
	if(! checkScore(id)){
		return;
	}
    var url = basePath+"/toInitComment.htm?productId="+id+"&orderId="+orderId+"&goodsPrice="+goodsPrice+
        "&goodsName="+goodsName+"&orderNo="+orderNo+"&goodsImg="+goodsImg+"&goodsNum="+goodsNum
        +"&orderGoodsId="+orderGoodsId;

    if(commentId !=null && commentId != ""){
        url += "&commentId="+commentId;
    }

	/*if(! checkContext(id)){
		return;
	}*/
	window.location.href = url;
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
	if(str.trim().length>500 || str.trim().length<2) {
		$("#commTip"+id).text("字数在2-500之间!");
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


