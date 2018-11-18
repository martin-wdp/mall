$(function(){
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
	$('.track').mouseover(function(){
		$(this).addClass('track_hover');
	});
	$('.track').mouseout(function(){
		$(this).removeClass('track_hover');
	});
	$('.history_cont').jCarouselLite({
		auto : 3000,
		speed : 500,
		visible : 3,
		btnPrev : '.history_l',
		btnNext : '.history_r'
	});
	$('.evaluate_link').click(function(){
		if($(this).next().is(':visible')){
			$('.evalate_cont').hide();
//			$('.evalate_cont').hide();
			$("textarea[id^='complaincon']").val("");
			$("label[id^='commTip']").text("");
			$("input[name='commentScore']").eq(0).prop("checked",true);
		}
		else{
			if($(this).attr("data-f") == undefined){
				var gid= $(this).attr("data-gid");
				var oid= $(this).attr("data-oid"),cFlag=true;
				var url = "checkcommgoodflag.htm?CSRFToken="+$("#hi_token").val()+"&orderId="+oid+"&goodsId="+gid;
				$.ajax({
					type : 'post',
					url : url,
					async : false,
					success : function(data) {
						if(data > 0){
							cFlag =false;
						}
					}
				});
				if(cFlag){
					$(".evaluate_link").next().hide();
					var gid= $(this).attr("data-gid");
					var tUrl = "loadgoodstag.htm?CSRFToken="+$("#hi_token").val()+"&goodsId="+$(this).attr("data-gid");
					$.ajax({
						type : 'post',
						url : tUrl,
						async : false,
						success : function(data) {
							var s = "";
							for(var i = 0;i< data.length;i++){
								s+='<label><input type="checkbox" name="commentTag" value="'+data[i].goodsTag.tagName+'"  class="vm mr5"/><strong>'+data[i].goodsTag.tagName+'</strong></label>';
							}
							$("#g_tag"+gid).html('');
							if(s.trim().length == 0 ){
								$("#g_tag"+gid).append("&nbsp;");
							}else{
								$("#g_tag"+gid).append(s);
							}
							
						}
					});
					$(this).next().show();
				}else{
					dia(2);
				}
			}else{
				$(this).next().show();
			}
		}
	});
	$('.state_box').mouseover(function(){
		$(this).addClass('state_box_hover');
	});
	$('.state_box').mouseout(function(){
		$(this).removeClass('state_box_hover');
	});
	$('.history_cont2').jCarouselLite({
		auto : 3000,
		speed : 500,
		visible : 4,
		btnPrev : '.history_l',
		btnNext : '.history_r'
	});
	$('.add_education').click(function(){
		$('.education_edit').slideDown('fast');
	});
	$('.education_cancel').click(function(){
		$('.education_edit').slideUp('fast');
	});
	$('.add_job').click(function(){
		$('.job_edit').slideDown('fast');
	});
	$('.job_cancel').click(function(){
		$('.job_edit').slideUp('fast');
	});
});