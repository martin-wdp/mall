$(function(){
	//判断是否有保存的搜索关键字
    if($("#oldsearchtext")){
        if($("#oldsearchtext").val() != "") {
            $(".inputSearch").val($("#oldsearchtext").val());
        }
    }

    //首页展开隐藏
    function proSort(){
        var st = 0;
        $(".showlist").bind("mouseenter",function(){
            st = setTimeout(function(){
            },200);
        });
        $(".showlist").bind("mouseleave",function(){
            clearTimeout(st);
        });
    }
    //首页判断
    if($("#isIndex").val()) {
        //判断是否显示分类导航
        if($("#tempcbshowflag").val()==0){
            $(".showlist").show();
        }
    }else{
        proSort();
        $(".dropdown-menu").hide();
    }
    //全部商品分类
    //2015.10.31 wuyanbo 修改li个数6为10
    $(".dropdown-menu li:gt(10)").hide();
    $(".showlist").mouseover(function(){
        $(".dropdown-menu li:gt(10)").show();
    }).mouseout(function(){
        $(".dropdown-menu li:gt(10)").hide();
    });
    var t = "1";
    $(".dropdown-menu li").mouseover(function(){
        var cur = $(this);
        var index = $(this).index();
        var dl = $(".dropdown-menu").children("li");
        t = setTimeout(function(){
            dl.removeClass("hover").eq(index).addClass("hover");
            $(".menuView:visible").hide();
            $(".menuView").eq(index).show();
        },100);
    }).mouseout(function(){
        clearTimeout(t);
    });
    function hideSort(){
        $(".dropdown-menu").find(".hover").removeClass("hover");
        $(".menuView:visible").hide();
    };
    $(".showlist").mouseleave(function(){
        hideSort();
    });
    $(".menucat").mouseover(function(){
        hideSort();
    });

    //幻灯
    if($("#slides").length > 0 && $("#slides a").length > 1) {
        $("#slides").slidesjs({
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
        });
    };
    if($(".recommend").length > 0 && $("#proScroll li").length > 4) {
        $("#proScroll").jCarouselLite({
            btnNext: ".j-next",
            btnPrev: ".j-prev",
            auto: 3600,
            speed: 500,
            visible: 4,
            onMouse: true,
            scroll: 1
        });
    };

    //猜你喜欢
    $(".like-pros li:gt(5)").hide();
    $("#replace-btn").click(function(){
        var $num = $(".like-pros li:visible:last").index();
        if($(".like-pros li:visible").length == 6 && $(".like-pros li:visible:last").next("li").length > 0) {
            for(var i=$num+1;i<$num+7;i++) {
                $(".like-pros li:lt("+($num+1)+")").hide();
                $(".like-pros li:eq("+i+")").fadeIn();
            };
        } else {
            $(".like-pros li:gt(5)").hide();
            $(".like-pros li:lt(6)").fadeIn();
        };
    });

    //楼层
    $(".proLists").each(function(){
        var $this = $(this);
        $this.find(".proTabs li:first").addClass("cur");
        $this.find(".proCon .proItem:first").show();
        $this.find(".proTabs a").click(function(){
            var $index = $(this).parent("li").index();
            $(this).parent("li").addClass("cur").siblings(".cur").removeClass("cur");
            $this.find(".proItem:eq("+$index+")").fadeIn().siblings(".proItem:visible").hide();
        });
    });

    //楼层幻灯
    $(".proLists").each(function(){
        var $this = $(this).find(".p-slides"),
            _this = $(this).find(".v-slides");
        if($this.find(".slide").length > 1) {
        	$this.slidesjs({
                width: 440,
                height: 240,
                navigation: {
                    active: true
                },
                pagination: {
                    active: true,
                    effect: "slide"
                }
            });
        };
        if(_this.find(".slide").length > 1) {
        	_this.slidesjs({
                width: 395,
                height: 480,
                navigation: {
                    active: true
                },
                pagination: {
                    active: true,
                    effect: "slide"
                }
            });
        };
    });

    if($("#shareSlides").length > 0 && $("#shareSlides .slide").length > 1) {
        $("#shareSlides").slidesjs({
            width: 678,
            height:405,
            navigation: {
                active: true,
                effect: "slide"
            },
            pagination: {
                active: false
            },
            callback: {
                loaded: function(number) {
                    $("#allNum").html($("#shareSlides .slide").length);
                    $("#actNum").html(number);
                },
                complete: function(number) {
                    $("#actNum").html(number);
                }
            }
        });
    };

    //侧边导航
    var scrollHandle = function(){
        var scrollTop = $(document).scrollTop();
        var contentItems = $(".proLists");
        var currentItem = "";
        contentItems.each(function(){
            var contentItem = $(this);
            var offsetTop = contentItem.offset().top;
            if(scrollTop > offsetTop-200){
                currentItem = "#" + contentItem.attr("id");
            }
        });
        if(currentItem != $(".sideBar").find(".cur").attr("href") && currentItem != ""){
            $(".sideBar").find(".cur").removeClass("cur");
            $(".sideBar").find("[href="+currentItem+"]").addClass("cur");
        } else if(currentItem == "") {
            $(".sideBar").find(".cur").removeClass("cur");
        };
    };
    $(".sideBar .sideItem").click(function(){
        //$(window).off("scroll");
        $(this).addClass("cur").siblings(".cur").removeClass("cur");
        var $anchor = $(this).attr("href"),
            $distance = $($anchor).offset().top;
        $("html, body").stop().animate({
            scrollTop: $distance
        },function(){
            $(window).scroll(scrollHandle);
        });
        return false;
    });
    $("#backtoTop").click(function(){
        $(".sideBar").find(".cur").removeClass("cur");
        //$(window).off("scroll");
        $("html, body").stop().animate({
            scrollTop: 0
        },function(){
            $(window).scroll(scrollHandle);
        });
    });

    $(window).scroll(function(){
        scrollHandle();
    });
    
  //购物车
    $(".cartfd").mouseover(function(){
    	$(".miniCart").show();
    }).mouseout(function(){
    	$(".miniCart").hide();
    });
    
    
    $(".showlist").mouseover(function(){
		$(".dropdown-menu").show();
	}).mouseout(function(){
		$(".dropdown-menu").hide();
	});
    
    
  //客服弹窗
	$(".customer_service").click(function(){
		var $top = $(this).prev("ul").height() + 85;
		$(".customer-box").show().css("top",$top);
	});
	$(".close-cs").click(function(){
		$(".customer-box").hide();
	});

});

//点击热门搜索的时候
function changeSearchKey(obj){
		//$(".inputSearch").val($(obj).html());
    $(".inputSearch").val($(obj).text());
    $(".mallSearch-form").submit();
}

//搜索
function checkSearch(){
	var search=$.trim($(".inputSearch").val());
    if(search == "请输入商品名称、OE码"){
        search = "";
    }
	if(search==""){
        //2015.11.03 luyong 屏蔽提示信息搜索功能
        return false;
		/*$(".inputSearch").val($(".inputSearch").attr("placeholder"));*/
	}else if(search.length>100){
		//限制长度，过长截取
		$(".inputSearch").val(search.substring(0,100));
	}
	$(".mallSearch-form").submit();
}



//加入收藏
function sdia(dname) {
    $(".bh-mask").fadeIn();
    $("#"+dname).fadeIn();
};
function scls(t) {
    $(".bh-mask").fadeOut();
    $(t).parents(".bh-dialog").fadeOut();
};

