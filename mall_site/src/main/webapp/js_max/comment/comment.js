$(function(){
	$(".pro_sort").addClass("pro_sort_close");
	/*页面初始化加载所有评价*/
	if(window.location.href.indexOf("/comment/")!=-1){
		loadComment(1,3);
	}
});