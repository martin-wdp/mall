$(function(){

	$(".fb_check").each(function(){
		var _this = $(this);
		_this.find("input[type='checkbox']").change(function(){
			if($(this).prop("checked") == true) {
				_this.addClass("fb_checked");
			} else {
				_this.removeClass("fb_checked");
			};
		});
	});

});