$(function(){
	$(".choose-style input[type='radio']").change(function(){
		if($("input[value='st-01']").prop("checked") == true) {
			$(".app-goods").prop("id","gd-01");
		} else if($("input[value='st-02']").prop("checked") == true) {
			$(".app-goods").prop("id","gd-02");
		} else if($("input[value='st-03']").prop("checked") == true) {
			$(".app-goods").prop("id","gd-03");
		};
	});
});