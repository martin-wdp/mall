$(function(){
		if($(".jscroll_box").length > 0) {
			$(".jscroll_box").jCarouselLite({
				btnNext: ".j_next",
				btnPrev: ".j_prev",
				speed: 500,
				scroll: 3
				});
		};
		if($("#deals_slides").length > 0) {
			$('#deals_slides').slidesjs({
				width: 193,
				height: 223,
				play: {
				  active: false,
				  effect: "fade",
				  auto: true,
				  interval: 4000
				},
				pagination: {
					effect: "fade"
					}
			  	});
		};
		
		$(".p_tit li:first").addClass("cur");
		$(".pro_content .pro_cont:first").fadeIn("slow").addClass("show");
		if($(".pc_01").length > 0) {
			$(".pc_01").jCarouselLite({
				btnNext: ".pro_next",
				btnPrev: ".pro_prev",
				speed: 500,
				visible: 4,
				scroll: 4
			});
		};
		if($(".rmd_slides").length > 0) {
			$('.rmd_slides').slidesjs({
				width: 470,
				height: 228,
				play: {
				  active: false,
				  effect: "fade",
				  auto: true,
				  interval: 4000
				},
				pagination: {
					effect: "fade"
					}
			});
		};
		
		//搜索历史
		loadSearchtext();
		
		
		
		
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
		$(".min_text").keyup(function(){
			$(this).val($(this).val().replace(/\D|^0/g,''));
			if ($(this).val() == '' || $(this).val() > b || $(this).val() < s) {$(this).val('1');} 
		}).bind("paste",function(){ 
			$(this).val($(this).val().replace(/\D|^0/g,''));  
		}).css("ime-mode", "disabled");

		//全部商品分类
//		var firstMoveIn = true;
//		$(".sort_cont dl").mouseover(function(){
//			var cur = $(this);
//			var index = $(this).index();
//			var dl = $(".sort_cont").children("dl");
//			t = setTimeout(function(){
//				dl.removeClass("hover").eq(index).addClass("hover");
//				$(".sort_open .ct_item:visible").hide();
//				$(".sort_open .ct_item").eq(index).show();
//				$(".sort_open").show().css("width","0");
//				if(firstMoveIn) {
//					$(".sub_brands").hide();
//					$(".sort_open").animate({
//						width: "738px"
//					},200,function(){
//						$(".sub_brands").show();
//					});
//				} else {
//					$(".sort_open").css("width","738px");
//				};
//				firstMoveIn = false;	
//			},100);
//		}).mouseout(function(){
//			clearTimeout(t);
//		});
//		function hideSort(){
//			$(".sort_cont").find(".hover").removeClass("hover");
//			$(".sort_open .ct_item:visible").hide();
//			$(".sort_open").hide();
//			firstMoveIn = true;
//		};
//		$(".pro_sort").mouseleave(function(){
//			hideSort();
//		});
//		$(".pro_sort h2").mouseover(function(){
//			hideSort();
//		});
//		$(".close_category").click(function(){
//			hideSort();
//		});
//		function proSort(){
//			var st = 0;
//			$(".pro_sort").bind("mouseenter",function(){
//				st = setTimeout(function(){
//					$(".sort_cont").show();
//				},200);
//			});
//			$(".pro_sort").bind("mouseleave",function(){
//				clearTimeout(st);
//				$(".sort_cont").hide();
//			});
//		};
//		//首页判断，开发替换
//		if($("#main_mark").length == 0) {
//			$(".sort_cont").addClass("none");
//			proSort();
//		};

		//滚动箭头显示
		$(".jscroll").mouseenter(function(){
			$(".j_prev, .j_next").fadeIn();
		}).mouseleave(function(){
			$(".j_prev, .j_next").fadeOut();
		});

		//图片延迟加载
		$("img.lazy").lazyload({
			threshold: 200,
			effect: "fadeIn",
			failurelimit : 10,
			placeholder: "../images/lazy_img.png",
			skip_invisible: false
		});

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
		tabs('disc_tabs','disc_wp','disc_cont');
		tabs('pop_tabs','pop_wp','pop_cont');

		//搜索历史
		$(".search_text").focus(function(){
			if($(".ex_search li").length > 0) {
				$(".ex_search").show();
			};
		});
		$(".search_wp").mouseleave(function(){
			setTimeout(function(){
				$(".ex_search").hide();
//				$(".search_text").blur();
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

		//右侧导航
		if($(".side_tools li").length > 0) {
			$(".side_tools li").each(function(n){
				var cur = $(this);
				var t_top = $(".floor_wp:eq("+n+")").offset().top;
				cur.mouseover(function(){
					cur.addClass("hover");
				}).mouseout(function(){
					cur.removeClass("hover");
				});
				cur.click(function(){
					$("body,html").animate({
		                    scrollTop: t_top
		                },500);
				});
			});
			$(".side_tools").css("top",($(window).height() - $(".side_tools").height()) / 2);
			var dis = $(".floor_wp:first").offset().top / 2;
			$(window).scroll(function() {
				if ($(document.documentElement).scrollTop() > dis || $(document.body).scrollTop() > dis) {
		            $(".side_tools").css('display','block');
		            $(".floor_wp").each(function(n){
		            	var _this = $(this);
		            	var to_top = $(".side_tools").offset().top;
		            	if(to_top > _this.offset().top && to_top < _this.next().offset().top) {
		            		$(".side_tools").find(".cur").removeClass("cur");
		            		$(".side_tools li:eq("+n+")").addClass("cur");
		            	};
		            });
		            $(".backtotop").die().live("click",
		            function() {
		                $("body,html").animate({
		                    scrollTop: 0
		                },0);
		             	$(".side_tools").find(".cur").removeClass("cur");
		            });
		        } else {
		            $(".side_tools").css('display','none');
		        }
		    });
		} else {
			$(".side_tools").css("bottom","20%");
			$(window).scroll(function() {
				if ($(document.documentElement).scrollTop() > 300 || $(document.body).scrollTop() > 300) {
		            $(".side_tools").css('display','block');
		            $(".backtotop").die().live("click",
		            function() {
		                $("body,html").animate({
		                    scrollTop: 0
		                },0);
		            });
		        } else {
		            $(".side_tools").css('display','none');
		        }
		    });
		};

		//列表页左侧菜单
		$(".sortItem").each(function(){
			var cur = $(this);
			cur.find("h3").click(function(){
				if(cur.hasClass("current")) {
					cur.removeClass("current");
				} else {
					cur.siblings(".current").removeClass("current");
					cur.addClass("current");
				};
			});
		});
		$(".sortItem:last").css("border-bottom","none");
		$(".left_box").each(function(){
			$(this).find("li:last").css("border-bottom","none");
		});

		//商品筛选
		$(".filter_item").each(function(){
			var _this = $(this);
			var f_name = _this.find("dt").text();
			var f_num = _this.attr("data-num");

			/* 检查是否有更多内容，有就显示更多按钮 */
			if(_this.find(".filterList ul").height() > 22) {
				_this.find(".f_more").removeClass("none");
			};

			/* 点击添加每一个筛选类型操作 */
			_this.find("ul a").click(function(){
				var _a = $(this);
				/*判断点击的参数类型*/
				var role= _a.attr("role");
				var f_value = _a.text();
				var p_value = _a.attr("param");
				var _selected = '<li data-num="'+f_num+'">';
				if(role=="brand"){
					/*如果是品牌属性添加品牌的隐藏域*/
					_selected+='<input type="hidden" name="params" value=b'+p_value+'>';
				}else if(role=="eparam"){
					/*如果是扩展属性添加扩展属性的隐藏域*/
					_selected+='<input type="hidden" name="params" value=e'+p_value+'>';
				}else if(role=="spec"){
					/*如果是规格属性添加规格的隐藏域*/
					_selected+='<input type="hidden" name="params" value=s'+p_value+'>';
				}
				_selected+='<a href="javascript:;">'+f_name+'<em>'+f_value+'</em><b></b></a></li>';
				$("#selected_filter").show();
				$("#selected_filter ul").append(_selected);
				$(".more_filter:first").show().removeClass("more_filter");
				if(_this.find(".f_less").css("display") != "none") {
					_this.find(".filterList").height("");
					_this.find(".f_less").hide();
					_this.find(".f_more").show();
				};
				_this.addClass("selected").hide();
				if($(".filter_item:visible").length < 4 || ($(".filter_item:visible").length == 4 && $(".more_filter").length == 0)) {$(".show_more, .show_less").hide()};
				/*提交表单*/
				$(".pageNo").val("1");
				$("#searchForm").submit();
			});
		});
		/* 点击删除每一个筛选类型操作 */
		$("#selected_filter ul a").click(function(){
			var _num = $(this).parent("li").attr("data-num");
			$(this).parent("li").remove();
			$(".pro_filter").find("dl[data-num="+_num+"]").show().removeClass("selected");
			if($(".filter_item:visible").length > 4 && $(".show_less").css("display") == "none") {
				$(".filter_item:visible:last").addClass("more_filter").hide();
				$(".show_more").show();
			};
			if($("#selected_filter ul li").length == 0) {$("#selected_filter").hide()};
			/*提交表单*/
			$(".pageNo").val("1");
			$("#searchForm").submit();
		});

		$(".pro_filter .filter_item:eq(3)").nextAll(".filter_item").addClass("more_filter");	//默认显示4项，其余隐藏
		
		/*点击显示有货*/
		$(".check_show_stock").change(function(){
			var cur = $(this);
			if(cur.parent("label").hasClass("checked")){
				cur.parent("label").removeClass("checked");
				$(".show_stock").val("0");
			}else{
				cur.parent("label").addClass("checked");
				$(".show_stock").val("1");
			}
			/*提交表单*/
			$(".pageNo").val("1");
			$("#searchForm").submit();
		});
		
		/*点击排序按钮操作*/
		$(".change_sort").click(function(){
			var sort = $(".list_sort");
			if(sort.val()==""){
				sort.val($(this).attr("val"));
			}else if($(this).attr("val")=="2D"){
				if(sort.val()=="2D"){
					sort.val("22D");
				}else{
					sort.val($(this).attr("val"));
				}
			}else if($(this).attr("val")=="22D"){
				if(sort.val()=="22D"){
					sort.val("2D");
				}else{
					sort.val($(this).attr("val"));
				}
			}else if($(this).attr("val")=="1D"){
				if(sort.val()=="1D"){
					sort.val("11D");
				}else{
					sort.val($(this).attr("val"));
				}
			}else if($(this).attr("val")=="11D"){
				if(sort.val()=="11D"){
					sort.val("1D");
				}else{
					sort.val($(this).attr("val"));
				}
			}else if($(this).attr("val")=="3D"){
				if(sort.val()=="3D"){
					sort.val("33D");
				}else{
					sort.val($(this).attr("val"));
				}
			}else if($(this).attr("val")=="33D"){
				if(sort.val()=="33D"){
					sort.val("3D");
				}else{
					sort.val($(this).attr("val"));
				}
			}else if($(this).attr("val")=="4D"){
				if(sort.val()=="4D"){
					sort.val("44D");
				}else{
					sort.val($(this).attr("val"));
				}
			}else if($(this).attr("val")=="44D"){
				if(sort.val()=="44D"){
					sort.val("4D");
				}else{
					sort.val($(this).attr("val"));
				}
			}
			/*提交表单*/
			$(".pageNo").val("1");
			$("#searchForm").submit();
		});
		
		
		
		/* 点击展开按钮操作 */
		$(".show_more").click(function(){
			$(".more_filter").removeClass("more_filter").show();
			$(this).hide();
			$(".show_less").show();
		});
		/* 点击收起按钮操作 */
		$(".show_less").click(function(){
			$(".filter_item:visible:eq(3)").nextAll(".filter_item:visible").addClass("more_filter").hide();
			$(this).hide();
			$(".show_more").show();
		});

		/* 点击更多、收起按钮操作 */
		$(".f_more").click(function(){
			$(this).siblings(".filterList").height("auto");
			$(this).siblings(".f_less").show();
			$(this).hide();
		});
		$(".f_less").click(function(){
			$(this).siblings(".filterList").height("");
			$(this).siblings(".f_more").show();
			$(this).hide();
		});

		/* 点击全部撤销操作 */
		$(".cancel_filter").click(function(){
			$("#selected_filter ul li").remove();
			$("#selected_filter").hide();
			$(".filter_item").show();
			$(".pro_filter .filter_item:eq(3)").nextAll(".filter_item").addClass("more_filter").hide();
			$(".show_less").hide();
			$(".show_more").show();
			/*设置页码为1,并提交查询表单*/
			$(".pageNo").val("1");
			$("#searchForm").submit();
		});
		
		/*点击修改页码的时候触发*/
		$(".changePages").click(function(){
			/*设置页码为点击的自定义属性pages,并提交查询表单*/
			$(".pageNo").val($(this).attr("pages"));
			$("#searchForm").submit();
		});
		

		//商品排序
		$(".sorting_box a").click(function(){
			$(this).siblings(".checked").removeClass("checked");
			$(this).addClass("checked");
		});
		//自定义checkbox
		$(".m_check").each(function(){
			var _this = $(this);
			_this.find("input[type='checkbox']").change(function(){
				if($(this).attr("checked") == "checked") {
					_this.addClass("checked");
				} else {
					_this.removeClass("checked");
				};
			});
		});

		//商品对比
		$(".goods_contrast").css("left",($(window).width()-1200)/2+210);
		$(".hide_ct").click(function(){
			$(".goods_contrast").hide();
		});

		//列表商品图
		$(".goodsBox").each(function(){
			var _this = $(this);
			var _next = _this.find(".g-scroll-next");
			var _prev = _this.find(".g-scroll-prev");
			var _ul = _this.find(".g-scroll-wrap ul");
			var _width = _ul.find("li").length *31;
			var n = _ul.find("li").length - 5;
			var _step = 0;
			var _time = 0;
			_ul.find("li").mouseover(function(){
				var _li = $(this);
				var _src = _li.attr("data-big");
				_time = setTimeout(function(){
					_li.siblings(".cur").removeClass("cur");
					_li.addClass("cur");
					_this.find(".g-img img").attr({"src":_src},{"data-original":_src});
				},200);
			}).mouseout(function(){
				clearTimeout(_time);
			});
			if(n <= 0) {
				_this.find(".g-scroll-prev, .g-scroll-next").hide();
			} else {
				_this.find(".g-scroll-wrap ul").width(_width);

				_next.click(function(){
					_prev.removeClass("disabled");
					if(_step < n) {
						_step++;
						go_next();
					};
				});

				_prev.click(function(){
					_next.removeClass("disabled");
					if(_step > 0) {
						_step--;
						go_prev();
					};
				});

				function go_next(){
					_ul.animate({
						left: -31*_step
					});
					if(_step == n) {
						_next.addClass("disabled");
					};
				};
				function go_prev(){
					_ul.animate({
						left: -31*_step
					});
					if(_step == 0) {
						_prev.addClass("disabled");
					};
				};
			};
		});
		//详情页商品图片展示
		$(".thumb_scroll_wp li:first").addClass("cur");
		var li_n = $(".thumb_scroll_wp li").length - 5;
		var li_step = 0;
		$(".thumb_scroll_wp li a").mouseover(function(){
			var _li = $(this).parent("li");
			_li.siblings(".cur").removeClass("cur");
			_li.addClass("cur");
		});
		$(".thumb_scroll_wp li a").click(function(){
			return false;
		});
		if(li_n > 0) {
			$(".thumb_scroll_wp ul").width($(".thumb_scroll_wp li").length*59);
			$(".thumb_scroll_next").removeClass("disabled");
			$(".thumb_scroll_next").click(function(){
				$(".thumb_scroll_prev").removeClass("disabled");
				if(li_step < li_n) {
					li_step++;
					$(".thumb_scroll_wp ul").animate({
						left: -59*li_step
					},300);
					if(li_step == li_n) {
						$(".thumb_scroll_next").addClass("disabled");
					};
				};
			});
			$(".thumb_scroll_prev").click(function(){
				$(".thumb_scroll_next").removeClass("disabled");
				if(li_step > 0) {
					li_step--;
					$(".thumb_scroll_wp ul").animate({
						left: -59*li_step
					},300);
					if(li_step == 0) {
						$(".thumb_scroll_prev").addClass("disabled");
					};
				};
			});
		};

		$(".more_share").click(function(){
			$(this).parent(".share_wp").toggleClass("sh_open");
		});

		//点击切换
		function ctabs(t1, t2, t3) {
			$("."+ t1).find("li:first").addClass("cur");
			$("."+ t2).find("."+ t3 +":first").show().addClass("show");
			$("."+ t1 +" li").each(function(n){
				var current = $(this);
				$(this).find("a").click(function(){
					var cur = $(this);
					$("."+ t1).find("li.cur").removeClass("cur");
					$("."+ t2).find("."+ t3 +".show").hide().removeClass("show");
					current.addClass("cur");
					$("."+ t2 +" ."+ t3 +":eq("+ n +")").show().addClass("show");
				});
			});
		};
			
		ctabs('locate_tabs','locate_wp','locate_list');
		ctabs('nav_tabs','product_detail','details_box');

		//地区选择
		jQuery.fn.isChildAndSelfOf = function(b){ return (this.closest(b).length > 0); };
		$(".choose_area").mouseenter(function(){
			var cur = $(this);
			c_tm = setTimeout(function(){
				cur.addClass("choose_area_hover");
			},200);
		});
		$(document).click(function(event){
			if(!$(event.target).isChildAndSelfOf(".choose_area")) {
				$(".choose_area").removeClass("choose_area_hover");
			};
		});
		$(".close_area").click(function(){
			$(".choose_area").removeClass("choose_area_hover");
		});

		//评分明细
		$(".score_btn").click(function(){
			var cur = $(this);
			if($(".score_detail").css("display") == "block") {
				$(".score_detail").hide();
				cur.addClass("score_down");
			} else {
				$(".score_detail").show();
				cur.removeClass("score_down");
			};
		});

		//优质商家
		$(".gd-shops li").mouseenter(function(){
			var cur = $(this);
			gd_t = setTimeout(function(){
				cur.find(".shop_extra").show();
			},200);
		}).mouseleave(function(){
			clearTimeout(gd_t);
			$(this).find(".shop_extra").hide();
		});

		//商品详情导航条
		if($(".detail_nav").length > 0) {
			var _y = $(".detail_nav").offset().top;
			function onScroll(e){
				if($(document.body).scrollTop() >= _y || $(document.documentElement).scrollTop() >= _y || window.scrollY >= _y) {
					$(".detail_nav").addClass("sticky");
				} else {
					$(".detail_nav").removeClass("sticky");
				};
			};
			$(window).scroll(function(){
				onScroll();
				});
			$(".nav_tabs a").click(function(){
				if($(this).parents(".det_title").hasClass("sticky")) {
					$("body,html").animate({
			            scrollTop: _y
			        },0);
				};
			});
		};

		//好评度
		$(".per_info").each(function(){
			var cur = $(this);
			var _w = cur.find(".per b").text();
			cur.find(".per_bar span").width(_w);
		});

		//评论回复
		$(".reply_btn").click(function(){
			$(this).parent().next(".reply_wp").toggleClass("none");
		});
		$(".rp_btn").click(function(){
			$(this).parents(".reply_ct").find(".reply_wp").toggleClass("none");
		});
		//弹窗
		win();
		$(window).resize(function(){
			win();
		});
		//咨询默认文本
		var _txa = "如何能尽快找到咨询答案？\n1、使用本页面上方的搜索功能\n2、仔细查看我们的提示信息及帮助，一般能找到答案";
		function d_txt(){
			if($(".pb_txa").val() == "") {	
				$(".pb_txa").val(_txa).css("color","#999");
			};
		};
		d_txt();
		$(".pb_txa").focus(function(){
			if($(this).val() == _txa) {
				$(this).val("").css("color","#666");
			};	
		}).blur(function(){
			d_txt();
		});
		
	});

function loadSearchtext(){
	//搜索历史
	$(".search_text").focus(function(){
		if($(".ex_search li").length > 0) {
			$(".ex_search").show();
		};
	});
	$(".search_wp").mouseleave(function(){
		s_t = setTimeout(function(){
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
				//删除搜索历史
				//delsearch(_this.find(".tex").html());
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
	
	$(".showlist").mouseover(function(){
		$(".dropdown-menu").show();
	}).mouseout(function(){
		$(".dropdown-menu").hide();
	});
	
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

