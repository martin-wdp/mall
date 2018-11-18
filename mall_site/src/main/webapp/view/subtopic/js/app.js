$(function(){
    $(".dropdown-menu li:gt(7)").hide();
    $(".showlist").mouseover(function(){
        $(".dropdown-menu").show();
        $(".dropdown-menu li:gt(7)").show();
    }).mouseout(function(){
        $(".dropdown-menu li:gt(7)").hide();
        $(".dropdown-menu").hide();
    });
    var t = "";
    $(".dropdown-menu li").mouseover(function(){
        var cur = $(this);
        var index = $(this).index();
        var dl = $(".dropdown-menu").children("li");
        t = setTimeout(function(){
            dl.removeClass("hover").eq(index).addClass("hover");
            $(".menuView").hide();
            $(".menuView").eq(index).show();
        },100);
    }).mouseout(function(){
        clearTimeout(t);
    });
    function hideSort(){
        $(".dropdown-menu").find(".hover").removeClass("hover");
        $(".menuView").hide();
    };
    $(".showlist").mouseleave(function(){
        hideSort();

    });
    $(".menucat").mouseover(function(){
        hideSort();
    });


    //购物车
    $(".cartfd").mouseover(function(){
        $(this).find(".miniCart").show();
    }).mouseout(function(){
        $(this).find(".miniCart").hide();
    });
    $(".mcOrder").each(function(){
        var $this = $(this);
        $this.mouseover(function(){
            $this.find(".minus, .plus").css("visibility","visible");
            $this.find(".del").show();
        }).mouseout(function(){
            $this.find(".minus, .plus").css("visibility","hidden");
            $this.find(".del").hide();
        });
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
    if($(".on-sale").length > 0) {
        $(".jscroll-wp").jCarouselLite({
            btnNext: ".j-next",
            btnPrev: ".j-prev",
            auto: 3600,
            speed: 500,
            visible: 4,
            onMouse: true,
            scroll: 4
        });
    };

    //楼层
    $(".proLists").each(function(){
        var $this = $(this);
        $this.find(".proTabs a:first").addClass("cur");
        $this.find(".proCon .proItem:first").show();
        $this.find(".proTabs a").mouseover(function(){
            var $cur = $(this);
            $tm = setTimeout(function(){
                var $index = $cur.index();
                $cur.addClass("cur").siblings(".cur").removeClass("cur");
                $this.find(".line").stop().animate({
                    left: 200*$index
                },300);
                $this.find(".proItem:eq("+$index+")").fadeIn().siblings(".proItem:visible").hide();
            },200);
        });
        $this.find(".proTabs a").mouseout(function(){
            clearTimeout($tm);
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

    //客服弹窗
    $(".customer_service").click(function(){
        var $top = $(this).prev("ul").height() + 85;
        $(".customer-box").show().css("top",$top);
    });
    $(".close-cs").click(function(){
        $(".customer-box").hide();
    });


});
