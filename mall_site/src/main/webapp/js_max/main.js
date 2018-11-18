$(function(){

	$(".bh_content").each(function(){
		var cur = $(this);
		cur.find(".f_tabs li:first").addClass("cur");
		cur.find(".bh_list:first").show().addClass("show");
		cur.find(".f_tabs li").each(function(n){
			var current = $(this);
			current.find("a").mouseover(function(){
				tm = setTimeout(function(){
					cur.find(".c_tabs").animate({
						left: 190*n
					},300);
					cur.find("li.cur").removeClass("cur");
					cur.find(".bh_list.show").hide().removeClass("show");
					current.addClass("current");
					cur.find(".bh_list:eq("+n+")").show().addClass("show");
				},300);
			});
			current.find("a").mouseout(function(){
				clearTimeout(tm);
			});
		});
	});

});