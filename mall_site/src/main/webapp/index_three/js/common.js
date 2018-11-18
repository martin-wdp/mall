$(function(){
	$("#home_area dt").find("a").click(function() {
   	 $("#home_area").addClass('hover');
    });
   $(".ms-01, .ms-02, .sc-01, .sc-02").bind("mouseenter",function(){
		var cur = $(this);
		t = setTimeout(function(){
			cur.addClass("hover");
			},100);
		});
	$(".ms-01, .ms-02, .sc-01, .sc-02").bind("mouseleave",function(){
		clearTimeout(t);
		$(this).removeClass("hover");
		});

    //全部商品分类
	var firstMoveIn = true;
	$(".sort_cont dl").mouseover(function(){
		var cur = $(this);
		var index = $(this).index();
		var dl = $(".sort_cont").children("dl");
		t = setTimeout(function(){
			dl.removeClass("hover").eq(index).addClass("hover");
			$(".sort_open .ct_item:visible").hide();
			$(".sort_open .ct_item").eq(index).show();
			$(".sort_open").show().css("width","0");
			if(firstMoveIn) {
				$(".sub_brands").hide();
				$(".sort_open").animate({
					width: "738px"
				},200,function(){
					$(".sub_brands").show();
				});
			} else {
				$(".sort_open").css("width","738px");
			};
			firstMoveIn = false;	
		},100);
	}).mouseout(function(){
		clearTimeout(t);
	});
	function hideSort(){
		$(".sort_cont").find(".hover").removeClass("hover");
		$(".sort_open .ct_item:visible").hide();
		$(".sort_open").hide();
		firstMoveIn = true;
	};
	$(".pro_sort").mouseleave(function(){
		hideSort();
	});
	$(".pro_sort h2").mouseover(function(){
		hideSort();
	});
	$(".close_category").click(function(){
		hideSort();
	});
	function proSort(){
		var st = 0;
		$(".pro_sort").bind("mouseenter",function(){
			st = setTimeout(function(){
				$(".sort_cont").show();
			},200);
		});
		$(".pro_sort").bind("mouseleave",function(){
			clearTimeout(st);
			$(".sort_cont").hide();
		});
	};
    
    //首页判断，开发替换
//	if($("#main_mark").length == 0) {
//		$(".sort_cont").addClass("none");
//		proSort();
//	};
	//首页判断
	if($("#isIndex").val()) {
		//判断是否显示分类导航
		if($("#tempcbshowflag").val()==0){
			$(".pro_sort").show();
		}
		$(".sort_cont").removeClass("none");
	}else{
		proSort();
	};

	//搜索历史
	$(".search_text").focus(function(){
		if($(".ex_search li").length > 0) {
			$(".ex_search").show();
		};
	});
	$(".search_wp").mouseleave(function(){
		setTimeout(function(){
			$(".ex_search").hide();
//			$(".search_text").blur();
		},200);
	});
	$(".ex_search li").each(function(){
		var _this = $(this);
		_this.mouseover(function(){
			_this.find("span").hide();
			_this.find(".del_history").show();
			_this.find(".del_history").click(function(){
				_this.remove();
				if($(".ex_search li").length == 0) {
					$(".ex_search").hide();
				};
			});
		}).mouseout(function(){
			_this.find(".del_history").hide();
			_this.find("span").show();
		});
	});
	$(".close_ex").click(function(){
		$(".ex_search").hide();
		$(".search_text").blur();
	});
});