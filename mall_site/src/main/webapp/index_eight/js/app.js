$(function(){
	//判断是否有保存的搜索关键字
	if($("#oldsearchtext")){
		$(".inputSearch").val($("#oldsearchtext").val());
	}
	 //全部商品分类
    $(".dropdown-menu li:gt(11)").hide();
    $(".showlist").mouseover(function(){
        $(".dropdown-menu li:gt(11)").show();
    }).mouseout(function(){
        $(".dropdown-menu li:gt(11)").hide();
    });
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
    $("#slides .slide:first").show();
    var sh = setInterval(slides,5000);
    $("#slides").mouseover(function(){
        clearInterval(sh);
    }).mouseout(function(){
        sh = setInterval(slides,5000);
    });
    $(".dropdown-menu").on("mouseover","li",function(){
        clearInterval(sh);
        var $this = $(this);
        tm = setTimeout(function(){
            var $index = $this.index();
            $("#slides .slide:visible").hide();
            $("#slides .slide:eq("+$index+")").fadeIn();
        },300);
    });
    $(".dropdown-menu").on("mouseout","li",function(){
        clearTimeout(tm);
        sh = setInterval(slides,5000);
    });

    //热门品牌
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
    
  //购物车
    $(".cartfd").mouseover(function(){
    	$(".miniCart").show();
    }).mouseout(function(){
    	$(".miniCart").hide();
    });
    
    //全部商品分类
    $(".showlist").mouseover(function(){
		$(".dropdown-menu").show();
	}).mouseout(function(){
		$(".dropdown-menu").hide();
	});

});

function slides(){
    var $cur = $("#slides .slide:visible");
        //$index = $cur.index();
    if($cur.next(".slide").length > 0) {
        $cur.hide();
        $cur.next(".slide").fadeIn();
    } else {
        $cur.hide();
        $("#slides .slide:first").fadeIn();
    };
};


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
