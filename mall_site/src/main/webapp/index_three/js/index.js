    
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

	
        $(function(){
            $('#slides').slidesjs({
             
                height: 340,
                play: {
                    active: false,
                    effect: "fade",
                    auto: true,
                    interval: 4000,
                    pauseOnHover: true,
                    restartDelay: 2500
                },
                navigation: {
                    active: false
                },
                pagination: {
                    active: true,
                    effect: "fade"
                }
            })              
          })
          


          $(function(){
            $('#left_slide').slidesjs({
                width: 252,
                height: 600,
                play: {
                    active: false,
                    effect: "fade",
                    auto: true,
                    interval: 4000,
                    pauseOnHover: true,
                    restartDelay: 2500
                },
                navigation: {
                    active: false
                },
                pagination: {
                    active: true,
                    effect: "fade"
                }
            })              
          })

    $(function(){

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
    })
          //超市公告
          $(function(){

            var right;
            var cha;
            cha=$(window).width()-$(".container").width();
            right=cha/2;
            $(".cs_notice").css("right",right);
          })
          //轮播图
          $(".jscroll_box").jCarouselLite({
                btnNext: ".j_next",
                btnPrev: ".j_prev",
                auto: 3600,
                speed: 500,
                visible: 3,
                onMouse: true,
                scroll: 3
            });
            

            //图片延迟加载
    if($("img.lazy").length > 0) {
        $("img.lazy").lazyload({
            threshold: 200,
            effect: "fadeIn",
            failurelimit : 10,
            placeholder: "../images/lazy_img.png",
            skip_invisible: false
        });
    };


    $(".floor_wp").each(function(){
                var cur = $(this);
                cur.find(".f_slides").slidesjs({
                    width: 600,
                    height: 300,
                    navigation: {
                        active: false
                    },
                    pagination: {
                        active: true,
                        effect: "slide"
                    }
                });
                cur.find(".up_slides, .below_slides").slidesjs({
                    width: 400,
                    height: 250,
                    navigation: {
                        active: false
                    },
                    pagination: {
                        active: true,
                        effect: "slide"
                    }
                });
            });


   