$(function(){
	function inputchange(){
		$(".plus").click(function(){
			var inputvalue = parseInt($(this).siblings(".min_text").val());
			inputvalue = inputvalue + 1;
			$(this).siblings(".min_text").attr("value",inputvalue);
			$(this).siblings(".min_text").trigger("change");
			});
			
		$(".minus").click(function(){
			var inputvalue = parseInt($(this).siblings(".min_text").val());
			if (inputvalue > 1) {
				inputvalue = inputvalue - 1;
				$(this).siblings(".min_text").attr("value",inputvalue);
				$(this).siblings(".min_text").trigger("change");
				}
			});	
		}
	inputchange();
	
	$(".min_text").keyup(function(){
        $(this).val($(this).val().replace(/\D|^0/g,''));
		if ($(this).val() == '') {$(this).val('1');} 
    }).bind("paste",function(){ 
        $(this).val($(this).val().replace(/\D|^0/g,''));  
    }).css("ime-mode", "disabled");
})