$(function(){
	
	
	$('.pic_roll_r').click(function(){
		if($(this).parent().next().length > 0){
			$(this).parent().hide();
			$(this).parent().next().show();
		}
	});
	$('.pic_roll_l').click(function(){
		if($(this).parent().prev().length > 0){
			$(this).parent().hide();
			$(this).parent().prev().show();
		}
	});
	$('.pic_thumb_list').jCarouselLite({
		speed : 500,
		visible : 5,
		vertical : true,
		btnPrev : '.pic_thumb_l',
		btnNext : '.pic_thumb_r',
		circular : false
	});
	$('.pic_thumb_list li').click(function(){
		var num = $(this).index();
		$('.pic_show li').hide();
		$('.pic_show li:eq(' + num + ')').show();
	});
	
	//下面是大图展示页面
	var thumb_pics = $('.goods_thumb_cont li').length;
	var current_page = 1;
	var all_page = Math.ceil(thumb_pics / 10);
	if(Math.ceil(thumb_pics / 10) > 0){
		$('#goods_thumb_num').html('1/' + all_page + '页');
	}
	else{
		$('#goods_thumb_num').html('1/1页');
	}
	
	if(thumb_pics <= 1){
		$('.album_roll_r').addClass('disabled');
	}
	$('.goods_thumb_l').click(function(){
		if($(this).attr('class').indexOf('disabled') < 0){
			var mt = (parseInt($('.goods_thumb_cont ul').css('marginTop')));
			$('.goods_thumb_cont ul').css('marginTop',(mt + 490) + 'px');
			current_page -= 1;
			$('#goods_thumb_num').html(current_page + '/' + all_page + '页');
			$('.goods_thumb_r').removeClass('disabled');
			if(current_page <= 1){
				$(this).addClass('disabled');
			}
		}
	});
	$('.goods_thumb_r').click(function(){
		if($(this).attr('class').indexOf('disabled') < 0){
			var mt = (parseInt($('.goods_thumb_cont ul').css('marginTop')));
			$('.goods_thumb_cont ul').css('marginTop',(mt - 490) + 'px');
			current_page += 1;
			$('#goods_thumb_num').html(current_page + '/' + all_page + '页');
			$('.goods_thumb_l').removeClass('disabled');
			if(current_page >= all_page){
				$(this).addClass('disabled');
			}
		}
	});
	$('.goods_thumb_cont li').click(function(){
		$('.goods_thumb_cont li').removeClass('cur');
		$(this).addClass('cur');
		$('.album_big_img').css('backgroundImage','url(' + $(this).find('img').attr('large') + ')');
		if($(this).index() == ($('.goods_thumb_cont li').length - 1)){
			$('.album_roll_l').removeClass('album_roll_l_disabled');
			$('.album_roll_r').addClass('album_roll_r_disabled');
		}
		else if($(this).index() == 0){
			$('.album_roll_r').removeClass('album_roll_r_disabled');
			$('.album_roll_l').addClass('album_roll_l_disabled');
		}
		else{
			$('.album_roll_l').removeClass('album_roll_l_disabled');
			$('.album_roll_r').removeClass('album_roll_r_disabled');
		}
	});
	$('.album_roll_r').click(function(){
		if($(this).attr('class').indexOf('disabled') < 0){
			$('.goods_thumb_cont .cur').next().addClass('cur');
			$('.goods_thumb_cont .cur:first').removeClass('cur');
			$('.album_big_img').css('backgroundImage','url(' + $('.goods_thumb_cont .cur').find('img').attr('large') + ')');
			$('.album_roll_l').removeClass('album_roll_l_disabled');
			if($('.goods_thumb_cont .cur').next().length < 1){
				$(this).addClass('album_roll_r_disabled');
			}
		}
	});
	$('.album_roll_l').click(function(){
		if($(this).attr('class').indexOf('disabled') < 0){
			$('.goods_thumb_cont .cur').prev().addClass('cur');
			$('.goods_thumb_cont .cur:last').removeClass('cur');
			$('.album_big_img').css('backgroundImage','url(' + $('.goods_thumb_cont .cur').find('img').attr('large') + ')');
			$('.album_roll_r').removeClass('album_roll_r_disabled');
			if($('.goods_thumb_cont .cur').prev().length < 1){
				$(this).addClass('album_roll_l_disabled');
			}
		}
	});
    var scoTop;
	$(".share_reply_btn").click(function(){
        scoTop=$(document).scrollTop();
		var con = $("#replyContent").val();
		var reg= /^([\u4e00-\u9fa5_A-Za-z0-9 \\`\\~\\!\\@\\#\\$\\^\\&\*\(\)\=\{\}\'\:\;\'\,[\]\.\/\?\~\！\@\#\￥\…\…\&\*\（\）\;\—\|\{\}\【\】\‘\；\：\”\“\'\。\，\、\？])+$/;
        if(!reg.test(con)){
            $("#con_00").html("回复信息中包含特殊字符！");
            dia(2);
            win(scoTop);
            return;
        }
        if(con.trim().length == 0 || con.trim().length < 10){
            $("#con_00").html("回复信息至少10个字符！");
            dia(2);
            win(scoTop);
            return;
        }
        if(con.trim().length == 0 || con.trim().length < 10 || !reg.test(con)){
			dia(2);
            win(scoTop);
			return;
		}
		var datas = "1=1";
		datas += "&replyContent=" + con;
		datas += "&shareId=" + $("#shareId").val();
		jQuery.ajax({
			type : "post",
			url : "../saveShareReply.htm",
			data : datas,
			timeout : 10000,
			success : function(html) {
				if (html == 'ok') {
					location.reload();
				}else{
					dia(3);
				}
			},
			error:function(){
				
			}
		});
	});
	$(window).resize(function() {
		win(scoTop);
	});
	/*加载面包屑信息*/
	$.get("../loadgoodsbreadcrumb/"+$(".bread_crumb_cat_id").val(),function(data){
		
		if($(".bread_crumb_cat_id").attr("data-role") == "list"){
			$(".index_url").after(">&nbsp;<span>"+data.catName+"</span>");
		}else{
			takeFirstCat(data);
			$(".index_url").after("&nbsp;>&nbsp;<a href='../list/"+data.catId+"-"+$(".first_catId").val()+".html'>"+data.catName+"</a>");
		}
		loadBreadCrumb(data.parentCat);
	});
});

/*取出第一级分类ID*/
function takeFirstCat(cat){
	if(cat.parentCat != null && cat.parentCat.catName=="所有"){
		$(".first_catId").val(cat.catId);
	}
	if(cat.parentCat!=null){
		takeFirstCat(cat.parentCat);
	}
}

/*递归加载面包屑信息*/
function loadBreadCrumb(cat){
	if(null != cat && null != cat.parentCat && cat.parentCat.catId==0){
		$(".index_url").after("<em><a href='../list/jumplist.html?catId="+cat.catId+"&level=1'>"+cat.catName+"</a></em>");
	}else if(cat != null && cat.catName!="所有"){
		$(".index_url").after("&nbsp;>&nbsp;<a href='../list/jumplist.html?catId="+cat.catId+"&level=2''>"+cat.catName+"</a>");
	}
	if(cat.parentCat!=null){
		loadBreadCrumb(cat.parentCat);
	}
}
function win(scoTop) {
	var _wd = $(window).width();
	var _hd = $(window).height();
	$(".s_dia").css("top", (_hd - $(".dialog").height()) / 2+scoTop).css("left",
			(_wd - $(".s_dia").width()) / 2);
};

function dia(n) {
	$(".mask").fadeIn();
	$(".dia" + n).fadeIn();
};

function cls() {
	$(".dialog").fadeOut();
	$(".mask").fadeOut();
}