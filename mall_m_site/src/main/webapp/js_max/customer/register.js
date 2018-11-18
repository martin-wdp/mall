//提交
$("#ck").click(function(){
	if($(this).is(":checked")){
		$(".btn").css("background","#f53f3e").bind("click",Submit);
	}else{
		$(".btn").css("background","#FA5F5F").unbind("click");
	}
});



function Submit(){
	Sub(function(){
        $.ajax({
            type: "post",
            url: $('input[id="basePath"]').val() + "/customer/addcustomerAJAXM.htm",
            async: false,
            dataType: "json",
            data: {customerUsername: $('input[id="mobileRegig"]').val(),customerPassword:$('input[id="r_pass"]').val(),
                code:$('input[id="capform"]').val()},
            success: function (data) {
                if (data == "1")
                    $(".w-suc").show();
            }
        });
	});
}

