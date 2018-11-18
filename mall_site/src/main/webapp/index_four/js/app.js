$(function(){
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
    //促销轮播
    if($(".hot-promotion").length > 0) {
        $(".hot-promotion").jCarouselLite({
            btnNext: ".pm-next",
            btnPrev: ".pm-prev",
            speed: 500,
            visible: 4
        });
    };
    //楼层切换
    $(".proLists").each(function(){
        var $this = $(this),
            $tabs = $this.find(".flr-tabs"),
            $index = 0;
        $this.find(".flr-tabs li:first").addClass("cur");
        $this.find(".proCon:first").show();
        $tabs.find("a").click(function(){
            $index = $(this).parent("li").index();
            $this.find(".cur").removeClass("cur");
            $this.find(".proCon:visible").fadeOut();
            $tabs.find("li:eq("+$index+")").addClass("cur");
            $this.find(".proCon:eq("+$index+")").fadeIn();
        });
    });
    
  //购物车
    $(".cartfd").mouseover(function(){
        $(this).find(".miniCart").show();
    }).mouseout(function(){
        $(this).find(".miniCart").hide();
    });

});


function sdia(dname) {
    $(".bh-mask").fadeIn();
    $("#"+dname).fadeIn();
};
function scls(t) {
    $(".bh-mask").fadeOut();
    $(t).parents(".bh-dialog").fadeOut();
};

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