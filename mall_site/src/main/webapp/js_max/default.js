$(function(){
	$('.sort_item').live("mouseover",function(){
		$(this).addClass('sort_hover');
		$(this).find('.lv2_item').show();
	});
	$('.sort_item').live("mouseout",function(){
		$(this).removeClass('sort_hover');
		$(this).find('.lv2_item').hide();
	});
	//样式调整
	$(".brand_list").each(function(){
		$(this).find("li:last").css("border-right","none");
		});
	$(".rmd_list").each(function(){
		$(this).find("li:last a").css("border-right","none");
		});
	$(".rmd_pros").each(function(){
		$(this).find("li:first").css("padding-right","485px").css("border-right","none");
		});
	$(".ep_list li:last").css("background","none");
	
	//我的好享商城
	$(".my_hao24, .shopping_cart dl").bind("mouseenter",function(){
		var cur = $(this);
		t = setTimeout(function(){
			cur.addClass("hover");
			},300);
		});
	$(".my_hao24, .shopping_cart dl").bind("mouseleave",function(){
		clearTimeout(t);
		$(this).removeClass("hover");
		});
		
	$(".gg_box b").click(function(){
		$(this).parent(".gg_box").hide();
		});

	//切换
	function tabs(t1, t2, t3) {
		$("."+ t1).find("li:first").addClass("cur");
		$("."+ t2).find("."+ t3 +":first").fadeIn('slow').addClass("show");
		$("."+ t1 +" li").each(function(n){
			var current = $(this);
			$(this).find("a").mouseover(function(){
				j_t = setTimeout(function(){
					var cur = $(this);
					$("."+ t1).find("li.cur").removeClass("cur");
					$("."+ t2).find("."+ t3 +".show").hide().removeClass("show");
					current.addClass("cur");
					$("."+ t2 +" ."+ t3 +":eq("+ n +")").fadeIn('slow').addClass("show");
					},300); 
				}).mouseout(function(){
					clearTimeout(j_t);
					});
			});
		};
		
	tabs('tp_tabs','program_cont','program_box');
	tabs('dk_tabs','dk_content','dk_pros');
	tabs('lct_tabs','lct_cont','lct_list');
	
	//排行榜
	$(".ranking_list").each(function(){
		var cur = $(this);
		cur.find("li:first").addClass("cur");
		cur.find("li").each(function(){
			var c_li = $(this);
			c_li.bind("mouseenter",function(){
				r_t = setTimeout(function(){
					cur.find(".cur").removeClass("cur");
					c_li.addClass("cur");
					},300);
				});
			c_li.bind("mouseleave",function(){
				clearTimeout(r_t);
				});
			});
		});
		
	$('.rank li').mouseover(function(){
		$('.rank li').removeClass('hover');
		$(this).addClass('hover');
	});
	$('.choose_area').mouseover(function(){
		$(this).addClass("choose_hover");
	});
	$('.choose_area').mouseout(function(){
		$(this).removeClass("choose_hover");
	});
	if($('.filter_item:visible').length > 5){
		$('.filter_item:gt(4)').hide();
		$('.filter_item:eq(4)').css('border','0');
		$('.extend_filter').html('展开');
	}
	$('.extend_filter').click(function(){
		if($('.filter_item:visible').length > 5){
			$('.filter_item:gt(4)').hide();
			$('.filter_item:eq(4)').css('border','0');
			$(this).css('backgroundPosition','55px bottom');
			$(this).html('展开');
		}
		else{
			$('.filter_item:gt(4)').show();
			$('.filter_item:eq(4)').css('borderBottom','1px dotted #E7CDAE');
			$(this).css('backgroundPosition','55px top');
			$(this).html('收起');
		}
	});
	$('.goods_contrast').css('left',((($(window).width()-1200)/2)+220)+'px');	
	
	$(function() {
		if($(".thumb_img_list").length > 0) {
			$(".thumb_img_list").jCarouselLite({
				btnNext: ".roll_left",
				btnPrev: ".roll_right",
				visible: 5,
				speed: 500,
				circular: false
			}); 
		};	
	});
	$('.share .open').click(function(){
		if($(this).parent().css('width') == '125px'){
			$(this).parent().css('width','auto');
			$(this).html('&lt');
		}
		else{
			$(this).parent().css('width','125px');
			$(this).html('&gt');
		}
	});
	
	

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
	$(".dialog").css("top",(_hd - $(".dialog").height())/2).css("left",(_wd - $(".dialog").width())/2);
};

/*function win(n){
    var _wd = $(window).width();
    var _hd = $(window).height();
    $(".member-dialog").css("top",(_hd - $(".member-dialog").height())/2).css("left",(_wd - $(".member-dialog").width())/2);
    $(".dia"+n).css("top",(_hd - $(".dia"+n).height())/2).css("left",(_wd - $(".dia"+n).width())/2);
};*/

function dia(n){
    win();
	$(".mask").fadeIn();
	$(".dia"+n).fadeIn();
};

function closeDialog(n){
    $(".mask").fadeOut("fast",function(){});
    $(".dia"+n).fadeOut("fast",function(){});
};

function cls(){
	$(".dialog").fadeOut();
    $(".member-dialog").fadeOut();
	$(".mask").fadeOut();
};