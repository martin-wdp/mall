$(function(){
	//判断是否有保存的搜索关键字
	if($("#oldsearchtext")){
		$(".inputSearch").val($("#oldsearchtext").val());
	}
	basePath = $("#basePath").val();
	//全部商品分类
    $(".dropdown-menu li:gt(7)").hide();
    $(".showlist").mouseover(function(){
        $(".dropdown-menu li:gt(7)").show();
    }).mouseout(function(){
        $(".dropdown-menu li:gt(7)").hide();
    });

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
    };
    //首页判断
    if($("#isIndex").val()) {
        //判断是否显示分类导航
        if($("#tempcbshowflag").val()==0){
            $(".showlist").show();
        }
    }else{
        proSort();
    };

    var t = "";
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
            width: 770,
            height: 320,
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
    if($("#proScroll").length > 0) {
        $(".jscroll-wp").jCarouselLite({
            btnNext: ".j-next",
            btnPrev: ".j-prev",
            auto: 3600,
            speed: 500,
            visible: 3,
            onMouse: true,
            scroll: 3
        });
    };

    //品牌列表
    $(".brands-list li:gt(19)").hide();
    $("#replace-btn").click(function(){
        var $num = $(".brands-list li:visible:last").index();
        if($(".brands-list li:visible").length == 20) {
            for(var i=$num+1;i<$num+20;i++) {
                $(".brands-list li:lt("+($num+1)+")").fadeOut();
                $(".brands-list li:eq("+i+")").fadeIn();
            };
        } else {
            $(".brands-list li:gt(19)").fadeOut();
            $(".brands-list li:lt(20)").fadeIn();
        };
    });


    //楼层
    $(".proLists").each(function(){
        var $this = $(this);
        $this.find(".proTabs a:first").addClass("cur");
        $this.find(".proWp .proCon:first").show();
        $this.find(".proTabs a").click(function(){
            var $index = $(this).index();
            $(this).addClass("cur").siblings(".cur").removeClass("cur");
            $this.find(".proCon:eq("+$index+")").fadeIn().siblings(".proCon:visible").fadeOut();
        });
    });

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
        $(window).off("scroll");
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
        $(window).off("scroll");
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
    
    //显示全部商品分类
    $(".showlist").mouseover(function(){
		$(".dropdown-menu").show();
	}).mouseout(function(){
		$(".dropdown-menu").hide();
	});

});

//点击热门搜索的时候
function changeSearchKey(obj){
		$(".inputSearch").val($(obj).html());
		$(".mallSearch-form").submit();
}

//搜索
function checkSearch(){
	var search=$.trim($(".inputSearch").val());
	if(search==""){
		$(".inputSearch").val($(".inputSearch").attr("placeholder"));
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