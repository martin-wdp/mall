/**
 * 
 */

function checkComment (id){
	if(! checkScore(id)){
		return;
	}
	if(! checkContext(id)){
		return;
	}
	$("#commForm"+id).submit();
}

function checkScore(id){
	if(!$(".des li").hasClass("on")){
		alert("请您给商品描述评分");
		return false;
	}
	return true;
}

function checkContext(id){
	var str = $("#complaincon"+id).val();
	var reg= /^([\u4e00-\u9fa5_A-Za-z0-9 \\`\\~\\!\\@\\#\\$\\^\\&\*\(\)\=\{\}\'\:\;\'\,[\]\.\/\?\~\！\@\#\￥\…\…\&\*\（\）\;\—\|\{\}\【\】\‘\；\：\”\“\'\。\，\、\？])+$/;
	//内容长度最多500
	if(str.trim().length>500 || str.trim().length<10) {
		$("#commTip"+id).text("请输入评论内容!字数在10-500之间");
		$("#commTip"+id).addClass("err");
		return false;
	}
	if(!reg.test(str)){
		$("#commTip"+id).text("评论内容不合法!不能包含特殊字符!");
		$("#commTip"+id).addClass("err");
		return false;
	}
	$("#commTip"+id).text("");
	return true;
}