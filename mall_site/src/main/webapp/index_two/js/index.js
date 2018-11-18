var basePath;
$(function(){
	basePath = $("#basePath").val();
	//判断是否有保存的搜索关键字
	if($("#oldsearchtext")){
		$(".search_text").val($("#oldsearchtext").val());
	}	
	//首页展开隐藏
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
	//我的商城
	$(".my_store, .shopping_cart dl").bind("mouseenter",function(){
		var cur = $(this);
		t = setTimeout(function(){
			cur.addClass("hover");
			},100);
		});
	$(".my_store, .shopping_cart dl").bind("mouseleave",function(){
		clearTimeout(t);
		$(this).removeClass("hover");
		});

	//加减
	$(".cout_text").each(function(){
		$(this).html($(this).prev().find(".min_text").val());
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
				var hots = "<li><a href='javascript:;'><span>搜索历史</span><label class='c999'>"+date[i].substring(0,28)+"</label></a><a class='del_history' href='javascript:;'>删除</a></li>";
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
			//$(".search_text").blur();
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
	$("#mminicart").on("mouseover",minicartonload);
	$("#delect_minicart").on("click", function(){
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
	//客服弹窗
	$(".customer_service").click(function(){
		var $top = $(this).prev("ul").height() + 5;
		$(".customer-box").show().css("top",$top);
	});
	$(".close-cs").click(function(){
		$(".customer-box").hide();
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
				};
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
	
		$.ajaxSetup({ cache: false });
		$.ajax({ url: basePath+"/minisscart.htm",  async:false ,success: function(datee){
	  		var cartgoods = datee.shopcart.miniGoodsList;
	  		var cust= datee.cust;
	  		if(cust!=null){
	  			$(".cart_empty").html("<p style='height:35px;'>您的购物车是空的<br /></p>");
	  		}
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
				//mini购物车头部
//				var tit = "<label class='select_all fl ml10'><input id='allcheck' class='vm mr5' type='checkbox' onclick='selectAll(\"carts\");'/>全选，共"+cartgoods.length+"件商品</label>"
//				+"<a id='delAll_minicart' class='cart_delete fr mr10' href='javascript:;'>删除</a>";
//				$(".cart_cont .cart_tit").html(tit);
				//循环输出购物车中的商品
				$(".cart_list").html("");
				for(var i = 0 ; i < cartgoods.length ; i++){
                    countgoods+=cartgoods[i].buNum;
					var cartgood="";
					if(cartgoods[i].fitId==null){
						var cartgood = "<li class='clearfix'>"+
//						"<input class='ct_check fl' type='checkbox' name='carts' value='"+cartgoods[i].shoppingCartId+"_"+cartgoods[i].goodsInfoId+"'/>"+
						"<div class='ct_check fl'><b style='margin-right:5px;'>"+(i+1)+"</b></div>"+
						"<a class='ct_img fl ml5' href='"+basePath+"/item/"+cartgoods[i].goodsInfoId+"'><img alt='' src='"+cartgoods[i].productPic+"' width='60px' height='60px'/></a>"+
						"<div class='ct_info fl ml10 mt15'>"+
							"<p class='ct_name'><a href='"+basePath+"/item/"+cartgoods[i].goodsInfoId+"'>"+cartgoods[i].productName+"</a></p>"+
							"<span class='ct_price'><b>¥</b>"+cartgoods[i].productPrice+"<em style='color:#666'>×"+cartgoods[i].buNum+"</em></span>"+
						"</div>"+
						"<div class='cart_cout clearfix pa'>"+
							"<input class='min_text_a fl' type='text' value='"+cartgoods[i].buNum+"' readonly='readonly' style='border: 1px solid #ddd;'/>"+
						"</div>"+
						"<div class='cout_text pa'></div>"+
						"<a id='delect_minicart' class='cart_del pa' href='javascript:;'></a>"+
						"<input type= 'hidden' class= 'goodsid' value = '"+cartgoods[i].shoppingCartId+"'>"+
						"<input type= 'hidden' class= 'goodsinfo' value = '"+cartgoods[i].goodsInfoId+"'>"+
						"</li>";
						$(".cart_list").append(cartgood);
					}else{
						var cartgood = "<li class='clearfix'>"+
//						"<input class='ct_check fl' type='checkbox' name='carts' value='"+cartgoods[i].shoppingCartId+"_"+cartgoods[i].goodsInfoId+"'/>"+
						"<div class='ct_check fl'><b style='margin-right:5px;'>"+(i+1)+"</b></div>"+
						"<div class='ct_info fl ml10 mt15'>[套装]"+
							cartgoods[i].miniFit.fitName+"</a></p>"+
							"<span class='ct_price'><b>¥</b>"+cartgoods[i].miniFit.fitPrice+"<em style='color:#666'>×"+cartgoods[i].buNum+"</em></span>"+
						"</div>"+
						"<div class='cart_cout clearfix pa'>"+
							"<input class='min_text_a fl' type='text' value='"+cartgoods[i].buNum+"' readonly='readonly' style='border: 1px solid #ddd;'/>"+
						"</div>"+
						"<div class='cout_text pa'></div>"+
						"<a id='delect_minicart' class='cart_del pa' href='javascript:;'></a>"+
						"<input type= 'hidden' class= 'goodsid' value = '"+cartgoods[i].shoppingCartId+"'>"+
						"<input type= 'hidden' class= 'goodsinfo' value = '"+cartgoods[i].goodsInfoId+"'>"+
						"</li>";
						$(".cart_list").append(cartgood);
						var goodsList=cartgoods[i].miniFit.miniGoods;
						
						for(var j = 0 ; j < goodsList.length ; j++){
						    cartgood ="<li class='clearfix'>"+
							"<div class='ct_check fl'><b style='margin-right:5px;'></b></div>"+
							"<a class='ct_img fl ml5' href='"+basePath+"/item/"+goodsList[j].goodsInfoId+"'><img alt='' src='"+goodsList[j].productPic+"' width='60px' height='60px'/></a>"+
							"<div class='ct_info fl ml10 mt15'>"+
								"<p class='ct_name'><a href='"+basePath+"/item/"+goodsList[j].goodsInfoId+"'>"+goodsList[j].productName+"</a></p>"+
								"<span class='ct_price'></span>"+
							"</div>"+
							"<div class='cart_cout clearfix pa'>"+
							"</div>"+
							"<div class='cout_text pa'></div>"+
							"</li>";
						    $(".cart_list").append(cartgood);
						}
					}
					
					if(cartgoods[i].fitId==null){
						//计算总价格
						empvalue =accAdd(empvalue,accMul(cartgoods[i].productPrice,cartgoods[i].buNum));
					}else{
						empvalue=accAdd(empvalue,accMul(cartgoods[i].miniFit.fitPrice,cartgoods[i].buNum));
					}
				}
				//设置mini购物车底部
				$(".settle_accounts span").html("共"+countgoods+"件商品");
				$(".settle_accounts em").html("<b>¥</b>"+empvalue);
				
			}else{
				$(".cart_cont").addClass("none");
				$(".cart_empty").removeClass("none");
			}
			if($(".cart_list li").length>3) {
				$(".cart_list").css({"height":"218px","overflow-y":"scroll"});
			} else {
				$(".cart_list").css({"height":"","overflow-y":""});
			};
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
