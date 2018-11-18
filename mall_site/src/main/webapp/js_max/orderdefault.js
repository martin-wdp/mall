
/*左菜单*/
    $('.item_title').each(function(){
			$(this).click(function(){
				$(this).next().toggle('fast',function(){
					if($(this).is(':visible')){
						$(this).prev().removeClass('up');
						$(this).prev().addClass('down');
					}
					else{
						$(this).prev().removeClass('down');
						$(this).prev().addClass('up');
					}
				});
			});
		});
		
	$('.sort_item').mouseover(function(){
		$(this).addClass('sort_hover');
		$(this).find('.lv2_item').show();
	});
	$('.sort_item').mouseout(function(){
		$(this).removeClass('sort_hover');
		$(this).find('.lv2_item').hide();
	});
//弹窗
win();
$(window).resize(function(){
	win();
});
function win(){
	var _wd = $(window).width();
	var _hd = $(window).height();
	$(".member-dialog").css("top",(_hd - $(".member-dialog").height())/2).css("left",(_wd - $(".member-dialog").width())/2);
};

function dia(n){
	$(".mask").fadeIn();
	$(".dia"+n).fadeIn();
};

function cls(){
	$(".member-dialog").fadeOut();
	$(".mask").fadeOut();
};