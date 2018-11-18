var n = 0;

$(".myMes").each(function(){
	for(i=1;i<=arr[n];i++){
		$($(this).find("ol li")[i-1]).css("background-image","url(images/qp_xqpl.png)");
	}
	n++;
});

/*
$("nav li").bind("click",function(){
	$("nav li").css({"color":"#000","border-bottom":"none"});
	$(this).css({"color":"#F6AB00","border-bottom":"2px solid #F6AB00"});
});*/
