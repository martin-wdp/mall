$(function(){
	$('body').css('minHeight',$(window).height()+'px');
	$('.top_item').mouseover(function(){
		$(this).addClass('top_item_hover');
	});
	$('.top_item').mouseout(function(){
		$(this).removeClass('top_item_hover');
	});
	$('.top_cart').mouseover(function(){
		$(this).addClass('top_cart_hover');
	});
	$('.top_cart').mouseout(function(){
		$(this).removeClass('top_cart_hover');
	});
	$('.cities').mouseover(function(){
		$(this).addClass('cities_hover');
	});
	$('.cities').mouseout(function(){
		$(this).removeClass('cities_hover');
	});
	$('.extend').click(function(){
		var index = $(this).index('.extend');
		$('.extend_cont').not(':eq('+index+')').hide();
		$('.extend_cont:eq('+index+')').toggle();
		return;
	});
	$('.cate_lv1').mouseover(function(){
		$(this).addClass('cate_lv1_hover');
	});
	$('.cate_lv1').mouseout(function(){
		$(this).removeClass('cate_lv1_hover');
	});
	$('.cate').mouseover(function(){
		$(this).addClass('cate_hover');
	});
	$('.cate').mouseout(function(){
		$(this).removeClass('cate_hover');
	});
	$('.for_radio_btn').click(function(){
		$(this).prev().attr('checked',true);
	});
	$('.for_check_btn').click(function(){
		if($(this).prev().attr('checked')){
			$(this).prev().removeAttr('checked');
		}
		else{
			$(this).prev().attr('checked',true);
		}
	});
	$('.sale_rank li').mouseover(function(){
		$('.sale_rank li').removeClass('hover');
		$(this).addClass('hover');
	});
	$('.dialog').css('height',$('body').height()+'px');
	//var elm = $('.detail_tab');  
//	var startPos = $(elm).offset().top;
//	$.event.add(window, "scroll", function() { 
//		var p = $(window).scrollTop(); 
//		$(elm).css('position',((p) > startPos) ? 'fixed' : 'static'); 
//		$(elm).css('top',((p) > startPos) ? '0px' : ''); 
//	});
});

function countDown(date,count){
	var now = Date.parse(new Date());
	//alert(now);
	var target = Date.parse(date);
	var time = target - now;
	time = parseInt(time / 1000);
	var hour = Math.floor(time /(60*60));
	time -= hour * (60*60);
	var minute = Math.floor(time / 60);
	var second = time - (minute * 60);
	if(hour<10){hour = '0' + hour;}
	if(minute<10){minute = '0' + minute;}
	if(second<10){second = '0' + second;}
	$(count).find('.hour').html(hour);
	$(count).find('.minute').html(minute);
	$(count).find('.second').html(second);
	window.setTimeout(function(){countDown(date,count);},1000);
}

function showDialog(ele){
	$(ele).show();
	$(ele).find('.dialog_box').css('top',$(window).scrollTop() + 100 + 'px');
}


























