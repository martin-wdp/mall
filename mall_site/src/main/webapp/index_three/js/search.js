var basePath;
$(function(){
	basePath = $("#basePath").val();
	//判断是否有保存的搜索关键字
	if($("#oldsearchtext")){
		$(".search_text").val($("#oldsearchtext").val());
	}	
	//预加载省份
	loadInitProvince();
	//滚动箭头显示
	$(".jscroll").mouseenter(function(){
		$(".j_prev, .j_next").fadeIn();
	}).mouseleave(function(){
		$(".j_prev, .j_next").fadeOut();
	});

//	//图片延迟加载
//	$("img.lazy").lazyload({
//		threshold: 200,
//		effect: "fadeIn"
//	});
//	$(".flr_cont img.lazy").lazyload({
//		skip_invisible: false
//	});

	//切换
	function tabs(t1, t2, t3) {
		$("."+ t1).find("li:first").addClass("cur");
		$("."+ t2).find("."+ t3 +":first").show().addClass("show");
		$("."+ t1 +" li").each(function(n){
			var current = $(this);
			$(this).find("a").mouseover(function(){
				var cur = $(this);
				j_t = setTimeout(function(){
					$("."+ t1).find("li.cur").removeClass("cur");
					$("."+ t2).find("."+ t3 +".show").hide().removeClass("show");
					current.addClass("cur");
					$("."+ t2 +" ."+ t3 +":eq("+ n +")").show().addClass("show");
					},200); 
				}).mouseout(function(){
					clearTimeout(j_t);
					});
			});
		};
		
	tabs('recharge_tabs','recharge_cont','recharge_box');
//搜索历史开始
	//搜索历史
	$(".search_text").focus(function (){
		$(".search_text").attr("placeholder","");
		//Ajax获取cookie中的搜索历史，填充到ul中
		$.ajax({ url: basePath+"/getSearchCookieTop10.htm", async:false ,success: function(date){
			$("#searchList").html("");
			for(var i=0;(i<date.length && i<10);i++){
				var hots = "<li><a href='javascript:;'><span>搜索历史</span>"+date[i].substring(0,28)+"</a><a class='del_history' href='javascript:;'>删除</a></li>";
				$("#searchList").append(hots);
			}
			loadsearch();
		}});
		if($(".ex_search li").length > 0) {
			$(".ex_search").show();
		};
	}).blur(function(){
		$(".search_text").attr("placeholder",$("#firsthotsearch").val());
	});
	$(".search_wp").mouseleave(function(){
		setTimeout(function(){
			$(".ex_search").hide();
//			$(".search_text").blur();
		},200);
	});
	loadsearch();
	$(".close_ex").click(function(){
		$(".ex_search").hide();
		$(".search_text").blur();
	});
//搜索历史结束
	
	//Ajax加载公告
	loadInfor();
	
	//楼层切换
	$(".floor_wp").each(function(){
		var cur = $(this);
		var tm = 0;
		cur.find(".flr_tabs li:first").addClass("cur");
		cur.find(".flr_box:first").show().addClass("show");
		cur.find(".flr_tabs li").each(function(n){
			var current = $(this);
			current.find("a").mouseover(function(){
				tm = setTimeout(function(){
					cur.find(".tabs_arrow").animate({
						left: 200*n
					},300);
					cur.find("li.cur").removeClass("cur");
					cur.find(".flr_box.show").hide().removeClass("show");
					current.addClass("cur");
					cur.find(".flr_box:eq("+n+")").show().addClass("show");
				},200);
			});
			current.find("a").mouseout(function(){
				clearTimeout(tm);
			});
		});
	});

	//品牌列表高度调整
	$(".brands_list").each(function(){
		var li = $(this).children("li");
		li.each(function(){
			if($(this).index() < 12) {
				$(this).height(62);
			};
		});
	});

	
	//mini购物车
	$("#mminicart").live("mouseover",minicartonload);
	$("#delect_minicart").live("click", function(){
		var emp1 = $(this).next().val();
		var emp2 = $(this).next().next().val();
	
		$.ajax({ url: basePath+"/delshoppingcartbyid.htm?shoppingCartId="+emp1+"&goodsInfoId="+emp2,async:false, success: function(dats){
			if(dats == 1){
				minicartonload();	
			}
		}});
	});
	//预加载mini购物车
	minicartonload();
	
	//弹窗
	win();
	$(window).resize(function(){
		win();
	});
});
//给搜索历史绑定鼠标事件
function loadsearch(){
	$(".ex_search li").each(function(){
		var _this = $(this);
		var title = _this.find("label").text();
		//点击搜索历史，进入搜索页面
		_this.click(function(){
			$(".search_text").val(title);
			$("#topSearchForm").submit();
		}).mouseover(function(){
			_this.find("span").hide();
			_this.find(".del_history").show();
			_this.find(".del_history").click(function(){
				$.post(basePath+"/delSearchCookie.htm",{title:title},function(){
				});
				_this.remove();
				if($(".ex_search li").length == 0) {
					$(".ex_search").hide();
				}
			});
		}).mouseout(function(){
			_this.find(".del_history").hide();
			_this.find("span").show();
		});
	});
}
//点击热门搜索的时候
function changeSearchKey(obj){
		$(".search_text").val($(obj).html());
		$("#topSearchForm").submit();
}
//加载mini购物车
function minicartonload(){
		$.ajax({ url: basePath+"/minicart.htm",  async:false ,success: function(datee){
	  		var cartgoods = datee.shopcart.miniGoodsList;
	  		var empvalue = 0;
			//设置我的购物车显示购物车商品数量
	  		if(cartgoods != null){
				$(".sc_num em").html(cartgoods.length);
			}else{
				$(".sc_num em").html("0");
			}
            //比如一件商品买了3件,详细统计购买商品数量
            var countgoods=0;
			//购物车中有商品
			if(cartgoods != null && cartgoods.length>0){
				$(".cart_empty").addClass("none");
				$(".cart_cont").removeClass("none");
				//循环输出购物车中的商品
				$(".cart_list").html("");
				for(var i = 0 ; i < cartgoods.length ; i++){
                    countgoods+=cartgoods[i].buNum;
					var cartgood = "<li class='clearfix'>"+
										"<a class='ct_img fl ml5' href='"+basePath+"/item/"+cartgoods[i].goodsInfoId+"'><img alt='' src='"+cartgoods[i].productPic+"' width='60px' height='60px'/></a>"+
										"<div class='ct_info fl ml10'>"+
										"<p class='ct_name'><a href='"+basePath+"/item/"+cartgoods[i].goodsInfoId+"'>"+cartgoods[i].productName+"</a></p>"+
										"</div>"+
										"<div class='fr'><span class='ct_price'><b>¥</b>"+cartgoods[i].productPrice+"</span>×"+cartgoods[i].buNum+"</div>"	+
										"<div class='cout_text fr'>"+
										"<a id='delect_minicart' class='cout_text fr'  href='javascript:;'>删除</a>"+
										"<input type= 'hidden' class= 'goodsid' value = '"+cartgoods[i].shoppingCartId+"'>"+
										"<input type= 'hidden' class= 'goodsinfo' value = '"+cartgoods[i].goodsInfoId+"'>"+
										"</div>"+
	            					"</li>";
					$(".cart_list").append(cartgood);
					//计算总价格
					empvalue =accAdd(empvalue,accMul(cartgoods[i].productPrice,cartgoods[i].buNum));
				}
				//设置mini购物车底部
				$(".p10 .settle_accounts span").html("共"+countgoods+"件商品");
				$(".p10 .settle_accounts em").html("<b>¥</b>"+empvalue);
				
			}else{
				$(".cart_cont").addClass("none");
				$(".cart_empty").removeClass("none");
			}
			if($(".cart_list li").length>3) {
				$(".cart_list").css({"height":"218px","overflow-y":"scroll"});
			} else {
				$(".cart_list").css({"height":"","overflow-y":""});
			}
		}});

}

//Ajax加载公告
function loadInfor(){
	$.ajax({ url: basePath+"/ajaxLoadInfor.htm", async:false ,success: function(data){
		$(".notice_list").html("");
		for(var i=0;(i<data.length && i<5);i++){
			var hots = "<li><a href='information/"+data[i].infoId+"' title='"+data[i].title+"'>"+data[i].title+"</a></li>";
			$(".notice_list").append(hots);
		}
	}});
}

function win(){
	var _wd = $(window).width();
	var _hd = $(window).height();
	$(".dialog").css("top",(_hd - $(".dialog").height())/2).css("left",(_wd - $(".dialog").width())/2);
};

function dia(n){
	$(".mask").fadeIn();
	$(".dia"+n).fadeIn();
	};
	
function cls(){
	$(".dialog").fadeOut();
	$(".mask").fadeOut();
	};

//搜索
function checkSearch(){
	var search=$.trim($(".search_text").val());
	if(search==""){
		$(".search_text").val($(".search_text").attr("placeholder"));
	}else if(search.length>100){
		//限制长度，过长截取
		$(".search_text").val(search.substring(0,100));
	}
	$("#topSearchForm").submit();
}

/*初始化已经选择的省份*/
function loadInitProvince(){
	$.ajax({
		type: 'post',
		url:basePath+"/getAllProvince.htm",
		async:false,
		success: function(data) {
			var provinceHtml="";
			for(var i=0;i<data.length;i++){
				provinceHtml+="<li><a class='check_province'  onClick='loadCitys("+data[i].provinceId+",this);' href='javascript:;'>"+data[i].provinceName+"</a></li>";
			}
			$(".province_list").html(provinceHtml);
		}
	});
	var provinces = $(".check_province");
	for(var i = 0;i<provinces.length;i++){
		if($(provinces[i]).html()==$(".ch_province").val()){
			var click = $(provinces[i]).attr("onclick");
		}
	}
}

/*根据点击的省份加载省份*/
function loadCitys(pid,pro){
	$(".province_text").text($(pro).text());
	$(".show_province").removeClass("cur");
	$('#home_area').removeClass('hover');
}
