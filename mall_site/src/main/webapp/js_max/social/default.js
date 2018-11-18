$(function(){
	
	//样式调整
	$(".ft_nav li:last").css('border-right','none');
	$(".topic_list").each(function(){
		$(this).find("li:odd").css('background','#f7f7f7');
		});
	$(".ta_mood_list li:first").css('border-top','none');
	
	//弹出层
	var hg = $(document).height();
	var wg = $(document).width();
	var s_hg = $(window).height();
	$(".mask1").height(hg + 115);
	$(".dialog1").each(function(){
		var d_hg = $(this).height();
		var d_wd = $(this).width();
		$(this).css('left',(wg-d_wd)/2);
		$(this).css('top',(s_hg-d_hg)/2);
		});
	
	//好友选择
	$(".friends_list li").click(function(){
		var cur = $(this);
		var l_n = cur.index();
		var isAdd=!cur.hasClass("choose");
		if(cur.hasClass("choose")) {
			cur.removeClass("choose");
			cur.find(".choose_bg").remove();
			$(".selected_list").find("#li_"+l_n).remove();			
			
		} else {
			var c = $(this).addClass("choose").append("<div class='choose_bg'></div>");
			c.clone().appendTo(".selected_list").attr('id','li_'+l_n);		
			$(".selected_list li").click(function(){
				var s_cur = $(this);
				var s_id = s_cur.attr("id");
				var s_b = parseInt(s_id.substr(3));
				var f_li=$(".friends_list li:eq("+s_b+")");
				(!f_li.hasClass("choose"))&&(f_li.addClass("choose"));	
				f_li.click();
			});
		};
		update_li_status();
		update_can_next();
		update_can_prev();
		
		if(isAdd){
			//toNext();
		}else{
			toPrev();
		}
	});
	function toNext(){
		if($(".s_next").hasClass("no_li")||$(".selected_list").is(":animated")){
			return false;
		}		
		var lf = parseInt($(".selected_list").css("left"));		
		if(!$(this).hasClass("no_li")) {
			$(".selected_list").animate({
				left: lf-165
			},300,function(){
				update_can_next();
				update_can_prev();		
			});
		};		
	}
	
	function toPrev(){
		if($(".s_prev").hasClass("no_li")||$(".selected_list").is(":animated")){
			return false;
		}
		var lf = parseInt($(".selected_list").css("left"));
		if(lf < 0) {
			$(".selected_list").animate({
				left: lf+165
			},300,function(){
				update_can_next();
				update_can_prev();		
			});
		}
	};

	function update_li_status() {
		var n = $(".selected_list li").length;
		$(".selected_list").width(n*145+n*20);
		$(".s_next,.s_prev")[n<=4?'addClass':'removeClass']("no_li");
	}

	function update_can_next () {
		var wd=$(".selected_list").width();		
		var lf=parseInt($(".selected_list").css("left"));		
		var isCanNext=(wd-(Math.abs(lf)+165*5))>=0;	
		$(".s_next")[isCanNext?"removeClass":"addClass"]("no_li");
	}

	function update_can_prev () {
		var lf=parseInt($(".selected_list").css("left"));		
		var isCanPrev=lf<0;
		$(".s_prev")[isCanPrev?"removeClass":"addClass"]("no_li");
	}
	
	$(function(){
		$(".s_next").click(toNext);
		$(".s_prev").click(toPrev);
	});
		
	//相册删除图片
	$(".item").each(function(){
		var i_cur = $(this);
		i_cur.bind('mouseenter',function(){
			i_time = setTimeout(function(){
				i_cur.find(".photo_ops").fadeIn();
				},300);
			});
		i_cur.bind('mouseleave',function(){
			clearTimeout(i_time);
			i_cur.find(".photo_ops").fadeOut();
			});
		});
	
	//用户信息浮动层
	var d = $(".user_detail");
	$(".head_box").each(function(){
		var cur = $(this);
		cur.bind('mouseenter',function(){
			t = setTimeout(function(){
				cur.append(d);
				d.fadeIn("fast");
				},200);
			});
		cur.bind('mouseleave',function(){
			clearTimeout(t);
			cur.find(".user_detail").fadeOut("fast",function(){
				cur.find(".user_detail").remove();
				});
			});
		});
		
	//下拉模拟
	$(".down_wp").each(function(){
		var li_txt = $(this).find(".down_list .cur").text();
		$(this).find("em").text(li_txt);
		});
	$(".down_wp").bind('mouseenter',function(){
		var cur = $(this);
		ft = setTimeout(function(){
			cur.find(".down_list").slideDown('fast');
			cur.find("li a").each(function(){
				var c = $(this);
				c.click(function(){
					cur.find(".cur").removeClass("cur");
					c.parent("li").addClass("cur");
					var c_f = cur.find(".cur a").text();
					cur.find("em").text(c_f);
					cur.find(".down_list").slideUp('fast');
					});
				});
			},300);
		});
	$(".down_wp").bind('mouseleave',function(){
		clearTimeout(ft);
		$(this).find(".down_list").slideUp('fast');
		});
		
	//小组管理菜单
	$(".menu_wp .cur .menu_list").slideDown();
	$(".menu_wp .menu_box").each(function(){
		var cur = $(this);
		cur.find("h3 a").click(function(){
			if(cur.hasClass("cur")) {
				cur.find(".menu_list").slideUp();
				cur.removeClass("cur");
			} else {
				$(".menu_wp").find(".cur").removeClass("cur");
				$(".menu_wp").find(".menu_list").slideUp();
				cur.addClass("cur");
				cur.find(".menu_list").slideDown();
				};
			});
		});
		
	//私密小组说明
	$(".private_warning label").mouseover(function(){
		var p_c = $(this);
		p_t = setTimeout(function(){
			p_c.find(".pv_wp").show();
			},300);
		}).mouseout(function(){
			clearTimeout(p_t);
			$(this).find(".pv_wp").hide();
			});
	
	//管理员选择
	$(".manager_list li").click(function(){
		var m_cur = $(this);
		var cw = m_cur.find(".jqTransformCheckboxWrapper a");
		if(cw.hasClass("jqTransformChecked")) {
			cw.removeClass("jqTransformChecked");
			} else {
				cw.addClass("jqTransformChecked");
				};
		});
	$(".add_list li").click(function(){
		var a_cur = $(this);
		var acw = a_cur.find(".jqTransformRadioWrapper a");
		if(acw.hasClass("jqTransformChecked")) {
			acw.removeClass("jqTransformChecked");
			} else {
				$(".add_list").find(".jqTransformChecked").removeClass("jqTransformChecked");
				acw.addClass("jqTransformChecked");
				};
		});
		
	//间隔背景
	$(".ban_list li:odd .ban_cont").css('background','#f7f7f7');

	//评论列表
	$(".comment_list li").each(function(){
		var cur = $(this);
		cur.bind('mouseenter',function(){
			cmt_time = setTimeout(function(){
				cur.find(".h_v").css('visibility','visible');
				},200);
			});
		cur.bind('mouseleave',function(){
			clearTimeout(cmt_time);
			cur.find(".h_v").css('visibility','hidden');
			});
		});
		
	//图片详细页展示
	$(".p_slides .slide").each(function(){
		var p_img = $(this).find("img");
		var p_b = p_img.width()/p_img.height();
		if(p_img.width() > 585) {
			p_img.attr('width','585').attr('height',585/p_b);
			};
		if(p_img.height() < 435) {
			p_img.css('margin-top',(435 - p_img.height())/2);
			};
		});
	
	//我的相册编辑
	$(".my_albums li").bind('mouseenter',function(){
		var ma_cur = $(this);
		ma_time = setTimeout(function(){
			ma_cur.find(".albums_opt").show();
			},300);
		});
	$(".my_albums li").bind('mouseleave',function(){
			clearTimeout(ma_time);
			$(this).find(".albums_opt").hide();
			});
			
	//心情删除，回复
/*	$(".mood_list li").bind('mouseenter',function(){
		var cur = $(this);
		mood_t = setTimeout(function(){
			cur.find(".mood_delete, .mood_reply").show();
			},200);
		});
	$(".mood_list li").bind('mouseleave',function(){
		clearTimeout(mood_t);
		$(this).find(".mood_delete, .mood_reply").hide();
		});*/
/*	$(".mood_reply").click(function(){
		var c_pt = $(this).parent().parent();
		if(c_pt.find(".m_reply").hasClass("none")) {
			c_pt.find(".m_reply").removeClass("none");
			} else {
				c_pt.find(".m_reply").addClass("none");
				};
		});*/
		
	$(".mood_list li").each(function(){
		var cur = $(this);
		if(cur.find(".mp_wp").length > 0) {
			cur.find(".m_reply").show();
			};
		cur.find(".mood_reply").click(function(){
			if(cur.find(".m_reply").hasClass("none")) {
				cur.find(".m_reply").removeClass("none");
				};
			cur.find(".show").removeClass("show").hide();
			cur.find(".t_rpy").addClass("show").show();
			});
		cur.find(".mp_wp").each(function(){
			var c = $(this);
			c.find(".sec_reply").click(function(){
				cur.find(".show").removeClass("show").hide();
				c.find(".rpy_box").addClass("show").show();
				});
			});
		});
	
	//消息列表删除
	$(".msg_list li").bind('mouseenter',function(){
		var cur = $(this);
		msg_t = setTimeout(function(){
			cur.find(".msg_det, .msg_reply").show();
			},200);
		});
	$(".msg_list li").bind('mouseleave',function(){
		clearTimeout(msg_t);
		$(this).find(".msg_det, .msg_reply").hide();
		});
		
	//添加删除标签
	$(".add_tag").click(function(){
		var tag = $(this).prev().find("input").val();
		if(tag == '') {
			alert("请输入标签内容！");
			} else {
				$(".tagsList").append('<li>'+tag+'<a class="tag_close" href="javascript:;"></a></li>');
				$(".tag_close").click(function(){
					$(this).parent().remove();
					});
				};
		});
	$(".tag_close").click(function(){
		$(this).parent().remove();
		});
		
	//new
	//样式修改
	$(".sort_box .qs_list:last").css('margin-bottom','0');
	$(".c_nav li:last").css('background','none');
	$(".k_nav li:last").css('border-right','none');
	$(".k_nav li").css('width',736/3);
	
	//切换
	function tabs(t1, t2, t3) {
		  $("."+ t1).find("li:first").addClass("cur");
		  $("."+ t2).find("."+ t3 +":first").fadeIn('slow').addClass("show");
		  $("."+ t1 +" li").each(function(n){
			  var current = $(this);
			  $(this).find("a").click(function(){
				  var cur = $(this);
				  $("."+ t1).find("li.cur").removeClass("cur");
				  $("."+ t2).find("."+ t3 +".show").removeClass("show").hide().removeClass("show");
				  current.addClass("cur");
				  $("."+ t2 +" ."+ t3 +":eq("+ n +")").fadeIn('slow').addClass("show");
				  });
			  });
		};
			
	tabs('kn_tabs','kn_wp','kn_box');
	tabs('cp_tabs','coupons_wp','coupons_cont');
	
	//加载优惠券
	$(".more_coupons").nextAll(".coupons_box").hide();
	$(".more_coupons a").click(function(){
		$(this).parent().nextAll(".coupons_box").fadeIn();
		$(this).parent().hide();
		});
		
	//提示框
	$(".dialog1").each(function(){
		var cd = $(this);
		cd.find(".n_prompt").click(function(){
			cd.fadeOut();
			$(".dia_p").fadeIn();
			setTimeout(function(){
				$(".dia_p").fadeOut();
				$(".mask1").fadeOut();
				},3000);
			});
		});
		
	//评分条
	$(".eva_wp").each(function(){
		var s = $(this).find("em").text();
		$(this).find(".eva_bar span").css('width',s);
		});
		
	});
	
function dia1(n){
	$(".mask1").fadeIn();
	$(".dia"+n).fadeIn();
	};
	
function cls1(){
	$(".dialog1").fadeOut();
	$(".mask1").fadeOut();
	};
	
	function dia(n){
		$(".mask").fadeIn();
		$(".dia"+n).fadeIn();
	};
	
	function cls(){
		$(".dialog").fadeOut();
		$(".mask").fadeOut();
	};
	

	//全反选
	function switchAll(obj,name) {
		var checkboxs = document.getElementsByName(name);
		for ( var i = 0; i < checkboxs.length; i++) {
			var e = checkboxs[i];
				e.checked=!obj.checked;   
				if(!obj.checked){
					$(".jqTransformCheckbox").addClass("jqTransformChecked");
				}else{
					$(".jqTransformCheckbox").removeClass("jqTransformChecked");
				}
				
			
		}
	}
