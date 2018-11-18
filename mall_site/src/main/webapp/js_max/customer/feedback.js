$(function() {
	win();
	$(window).resize(function() {
		win();
	});
});
/**
 * 发送邮件
 */
function sendEmailToStore() {
	var reg= /^([\u4e00-\u9fa5_A-Za-z0-9 \\`\\~\\!\\@\\#\\$\\^\\&\*\(\)\=\{\}\'\:\;\'\,[\]\.\/\?\~\！\@\#\￥\…\…\&\*\（\）\;\—\|\{\}\【\】\‘\；\：\”\“\'\。\，\、\？])+$/;
	var token=$("input[name='CSRFToken']").val();
	var feedbacktype = '';
	$('input[name="feedbacktype"]:checked').each(function(i) {
		if(i==0){
			feedbacktype += $(this).val();
		}else{
			feedbacktype += '、'+ $(this).val();
		}
	});
	var feedbackcontent = $("textarea[name='feedbackcontent']").val();
	var feedbackname = $("input[name='feedbackname']").val();
	if (feedbacktype == null || feedbacktype == "") {
		dia(3);
		return;
	}
	if (feedbackcontent == null || feedbackcontent == "") {
		dia(3);
		return;
	}
	if(feedbackcontent.trim().length<10||feedbackcontent.trim().length>=250){
		dia(3);
		return;
	}
	if(!reg.test(feedbackcontent)){
		dia(3);
		return false;
	}
	
	var datas = "1=1";
	datas += "&feedbacktype=" + feedbacktype;
	datas += "&feedbackcontent=" + feedbackcontent;
	datas+= "&feedbackname=" + feedbackname;
	jQuery.ajax({
		url : "../sendemailuser.htm?CSRFToken="+token,
		data : datas,
		success : function(html) {
			if (html == 1) {
				dia(2);
			} else {
				dia(4);
			}
		}
	});
}

function win() {
	var _wd = $(window).width();
	var _hd = $(window).height();
	$(".s_dia").css("top", (_hd - $(".dialog").height()) / 2).css("left",
			(_wd - $(".s_dia").width()) / 2);

};

function dia(n) {
	$(".mask").fadeIn();
	$(".dia" + n).fadeIn();
};

function cls() {
	$(".dialog").fadeOut();
	$(".mask").fadeOut();
};