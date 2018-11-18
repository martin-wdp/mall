//===========================
//		页面效果
//===========================
showHigh();
$(window).resize(function(){
	showHigh();
});
$(".genre li").bind("click",function(){
	$(".genre li").removeAttr("style");
	$(this).css({"border":"2px solid #F5AC02","background-color":"#fff","background-image":"url(images/ok.png)","color":"#F5AC02"});
    //点击后给属性赋值
    var index = $(this).attr("data-em");
    $('input[name="companyType"]').val(index);
	//$(this).parents(".must").attr("num","1");
	Write.Must.Ok(this);
});

$("#old").bind("click",function(){
	$(this).parent().hide();
	$(".homeNew").show();
	$(".comCert").height(120);
});

$("#new").bind("click",function(){
	$(this).parent().hide();
	$(".homeOld").show();
	$(".comCert").height(150);
});


$("#area").bind("click",function(){
	$(".addrBox").show();
	$(".city ul").attr("data-count","0");
	setTimeout(function(){
		$(".district").css("bottom","0");
	},30);
	$("#line").removeAttr("style").attr("data-left","40%");
});
$(".addrBox").delegate("click",function(){
	$(".district").css("bottom","-50%");
	$(".city ul").attr("data-count","0");
	var _this = this;
	setTimeout(function(){
		$(_this).hide();
	},600);
});

/*$("#addressInit").delegate("li","click",function(){
	var n = parseInt($("#addressInit").attr("data-count"))+1;
    $("#addressInit").attr("data-count",n);
	var lt = $("#line").attr("data-left");
	$("#line").attr("data-left","74%").css("left",lt);//7% 40% 74%
	var name = $(this).text();
	if(n == 3){
		setTimeout(function(){
			$(".district").css("bottom","-50%");
		},30);
		setTimeout(function(){
			$(".addrBox").hide();
		},600);
	}
	switch(n){
		case 1: $("#Province").text($(this).text());break;
		case 2: $("#prefecture").text($(this).text());break;
		case 3: $("#county").text($(this).text());break;
	}
	event.stopPropagation();//阻止冒泡事件
});*/

$(".district p").click(function(){
	event.stopPropagation();//阻止冒泡事件
});
$("#continue").click(function(){
	$(".auth").hide();
});
function showHigh(){
	var height = $(".genre ul").height()+32;
	$(".genre").height(height).css("line-height",height+"px");
}
/*判断浏览器是否为UC*/
if(Browser.Navigate()){
	$(".comAddr map").css("top","0");
}

$("input[type='file']").attr("accept",".png,.jpg,.jpeg");
