var basePath;
$(function(){
	basePath = $("#basePath").val();
	//判断是否有保存的搜索关键字
	if($("#oldsearchtext")){
		$(".inputSearch").val($("#oldsearchtext").val());
	}
	loadInitProvince();
    //搜索
    $(".inputSearch").focus(function(){
        $(".search_link").hide();
    }).blur(function(){
        $(".search_link").show();
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

    //全部商品分类
    $(".dropdown-menu li:gt(7)").hide();
    $(".showlist").mouseover(function(){
        $(".dropdown-menu li:gt(7)").show();
    }).mouseout(function(){
        $(".dropdown-menu li:gt(7)").hide();
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

    //首页幻灯
    if($("#slides").length > 0 && $("#slides a").length > 1) {
        $("#slides").slidesjs({
            width: 990,
            height: 420,
            play: {
                active: false,
                effect: "fade",
                auto: true,
                interval: 4000,
                pauseOnHover: true,
                restartDelay: 2500
            },
            navigation: {
                active: true
            },
            pagination: {
                active: true,
                effect: "fade"
            }
        });
    };
    if($(".hd-show").length > 0 && $(".hd-show li").length > 3) {
        $(".hd-show").jCarouselLite({
            btnNext: ".s-next",
            btnPrev: ".s-prev",
            auto: 3600,
            speed: 500,
            visible: 3,
            onMouse: true,
            scroll: 3
        });
    };
    if($(".best-selling").length > 0) {
        $(".selling-wp").jCarouselLite({
            btnNext: ".t-next",
            btnPrev: ".t-prev",
            speed: 500,
            visible: 4,
            onMouse: true,
            scroll: 4
        });
    };
    if($(".brands-box li").length > 11) {
        $(".brands-box").jCarouselLite({
            btnNext: ".bd-next",
            btnPrev: ".bd-prev",
            speed: 500,
            visible: 11,
            onMouse: true,
            scroll: 1
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
    
  //全部商品分类
    $(".showlist").mouseover(function(){
		$(".dropdown-menu").show();
	}).mouseout(function(){
		$(".dropdown-menu").hide();
	});
    
  //客服弹窗
	$(".customer_service").click(function(){
		var $top = $(".sideBar .sideItem").length*56 + 10;
		$(".customer-box").show().css("top",$top);
	});
	$(".close-cs").click(function(){
		$(".customer-box").hide();
	});
	
	loadCurrentCity();

});


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

//点击热门搜索的时候
function changeSearchKey(obj){
		$(".inputSearch").val($(obj).html());
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


/*初始化已经选择的省份*/
function loadInitProvince(){
	$.ajax({
		type: 'post',
		url:basePath+"/getAllProvince.htm",
		async:false,
		success: function(data) {
			var provinceHtml="";
			for(var i=0;i<data.length;i++){
				provinceHtml+="<li><a class='check_province' onClick='loadCitys("+data[i].provinceId+",this);' href='javascript:;'>"+data[i].provinceName+"</a></li>";
				
			}
			$(".localPanel").html(provinceHtml);
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
	//$(".province_text").text($(pro).text());
	$.ajax({
        url:basePath+'/selectCity.htm?provinceId='+pid,
        success:function(data) {
            if(data==1) {
            	location.href=document.URL;
            }
        }
    });
	//$(".show_province").removeClass("cur");
	//$('#home_area').removeClass('hover');
}

function loadCurrentCity(){
    var city=$('#currentProvince').val();
    if(city==""){
    	$.ajax({
            url:basePath+'/defaulteProvince.htm',
            success:function(data) {
                if(data==1) {
                	$('.province_text').html("江苏");
                	 //location.reload();
                }
            }
        });
    	
    }
    $('.province_text').html(city);
}
