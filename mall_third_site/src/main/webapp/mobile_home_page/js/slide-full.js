$(function(){
	/*mySwiper = new Swiper('.full-swiper-container',{
		//mode:'vertical',
		pagination: '.full-pagination',
		paginationClickable: true
	});*/
	
	$("#enable_music").change(function(){
		if($(this).prop("checked") == true) {
			$(".choose_music").show();
			//$(".music-ico").show();
		} else {
			$(".choose_music").text("选择音乐").hide();
			$(".music-name").text("").hide();
			$(".seled").removeClass("seled");
			//$(".music-ico").hide();
			$("#music").val("");
			$("#musicname").val("");
		};
	});
	//选择音乐
	$(".choose_music").click(function(){
	
		//$('#music-modal').modal('show');
		//清空示例音乐
		//ajax获取音乐并添加 
	$.ajax({
			url : "searchMp3.htm",
			async : false ,
			success:function(data){
				$(".music-list tbody").html("");
				for(var i=0;i<data.length;i++){
					var html = "<tr><td>" +
							"<audio src="+data[i].mp3address+"></audio>" +
							"<a class=\"audition iconfont\" href=\"javascript:;\">&#xe6ac;</a>" +
							"<a class=\"stop-audition iconfont none\" href=\"javascript:;\">&#xe640;</a>" +
							"<span class=\"mc-name\">"+data[i].mp3name+"</span></td>" +
							"<td>"+data[i].mp3time+"</td>" +
							"<td>"+data[i].mp3length+"kb</td>" +
							"<td class=\"tr\"><button class=\"choose-mc\">就她了</button>" +
							"<button class=\"mc-ok\">搞定</button></td></tr>";
					
					$(".music-list tbody").append(html);
				}
				bindMusicList();
			}
		});
	});
	//绑定音乐列表按钮事件
	function bindMusicList(){
		$(".music-list tr").each(function(){
			var _this = $(this);
			_this.find(".audition").click(function(){
				_this.find("audio")[0].play();
				$(this).hide();
				_this.find(".stop-audition").show();
				_this.find("audio")[0].addEventListener("ended",function(){
					_this.find(".stop-audition").hide();
					_this.find(".audition").show();
					});
				});
			_this.find(".stop-audition").click(function(){
				_this.find("audio")[0].pause();
				$(this).hide();
				_this.find(".audition").show();
				});
			
			_this.find(".choose-mc").click(function(){
				_this.siblings(".seled").removeClass("seled");
				_this.addClass("seled");
				$(".close").click();
				$(".music-name").show().text(_this.find(".mc-name").text());
				$("#music").val(_this.find("audio").attr("src"));
				$("#musicname").val(_this.find(".mc-name").text());
				$(".choose_music").text("修改");
				//$(this).hide().siblings(".mc-ok").show();
				});
			});
		
	}
	
	
	$(".music-list tr").each(function(){
		var _this = $(this);
		_this.find(".audition").click(function(){
			_this.find("audio")[0].play();
			$(this).hide();
			_this.find(".stop-audition").show();
			_this.find("audio")[0].addEventListener("ended",function(){
				_this.find(".stop-audition").hide();
				_this.find(".audition").show();
				});
			});
		_this.find(".stop-audition").click(function(){
			_this.find("audio")[0].pause();
			$(this).hide();
			_this.find(".audition").show();
			});
		
		_this.find(".choose-mc").click(function(){
			_this.siblings(".seled").removeClass("seled");
			_this.addClass("seled");
			$(".close").click();
			$(".music-name").show().text(_this.find(".mc-name").text());
			$("#music").val(_this.find("audio").prop("src"));
			$("#musicname").val(_this.find(".mc-name").text());
			$(".choose_music").text("修改");
			//$(this).hide().siblings(".mc-ok").show();
			});
		});
		
	/*$(".s-mode").change(function(){
		mySwiper.destroy();
		if($(".s-mode[value='sh']").prop("checked") == true){	
			mySwiper = new Swiper('.full-swiper-container',{
				mode:'horizontal',
				pagination: '.full-pagination',
				paginationClickable: true
			});
			$(".full-pagination").show();
			$(".vtc-img").hide();
			$(".swiper-wrapper").css({"-webkit-transform":"translate3d(0px, 0px, 0px)","transform":"translate3d(0px, 0px, 0px)"});
		} else {
			mySwiper = new Swiper('.full-swiper-container',{
				mode:'vertical',
				pagination: '.full-pagination',
				paginationClickable: true
			});
			$(".full-pagination").hide();
			$(".vtc-img").show();
			$(".swiper-wrapper").css({"-webkit-transform":"translate3d(0px, 0px, 0px)","transform":"translate3d(0px, 0px, 0px)"});
		};
	});*/
	

});

function sel(id){
	$(".lk-sel").each(function(){
		var _this = $(this),
			_word = _this.find(".sel-word"),
			_ul = _this.find("ul");
		_word.text(_this.find(".s-selected a").text());
		_word.click(function(){
			_ul.show();
		});
		_this.bind("mouseleave",function(){
			_ul.hide();
		});
		_ul.find("a").click(function(){
			_word.text($(this).text());
			_ul.hide();
		});
	});



    if(typeof(id) !="undefined") {
      //  $(".del-tags").each(function () {
            $(".del-tags").click(function () {
                if($(this).attr("attr-value")==id){
                   $("#"+id).val("");
                $(this).prev(".sel-tags").html("");

                $(this).parents(".nlink-wp").prev(".nlink-wp").find(".gn-chs li:first a").click();
                $(this).parents(".nlink-wp").hide();
                }
            });
      //  });
    }

};

function addFun(url){
	var _src = url,
	 	_cont = '<div class="add_slides">' +
                '<div class="sd_view clearfix">' +
                '<a class="view_img fl" href="javascript:;"><img alt="" src="'+_src+'">'+
                '<span id="fullRoll_'+$(".add_slides").length+'" class="fullRollAgain">重新上传</span></a>' +
                '<dl class="nlink-wp fl clearfix mt20 ml20">' +
                '<dt class="dt-box">链接地址：</dt>' +
                '<dd class="dd-box">'+
                //'<a href="#ctModal" role="button" class="btn" onclick="md(\'fullRollHref_'+$(".add_slides").length+'\')">链接地址</a>'+
                '<div class="lk-sel lk-chs"><span class="sel-word"></span><ul><li class="s-selected"><a data-href="lk-01">功能链接</a></li><li><a data-href="lk-02">自定义链接</a></li></ul></div>'+
      		  	'<div class="lk-sel gn-chs"><span class="sel-word"></span><ul>'+
      		  	'<li class="s-selected"><a>--请选择--</a></li>'+
      		  	'<li><a data-href="choose">商品</a></li>'+
      		  	'<li><a data-href="list/allproduct.html">所有商品</a></li>'+
      		  	'<li><a data-href="main.html">微店首页</a></li>'+
      		  	'</ul></div>'+
                '<input name="fullRollHref" id="fullRollHref_'+$(".add_slides").length+'" class="form-control custom-input" type="text" >'+
                '<input name="fullRollImgSrc" id="fullRollImgSrc_'+$(".add_slides").length+'" class="form-control" type="hidden" value="'+_src+'"></dd></dl>' +
                '<dl class="nlink-wp fl clearfix ml20 none" style="display: none"><dt class="dt-box">已选择：</dt><dd class="dd-box"><span class="sel-tags"></span><a class="del-tags" href="javascript:;">删除</a></dd></dl>'+
                '</div><a class="remove-slide" href="javascript:;" onclick="remove_sd(this)"><span class="glyphicon glyphicon-remove"></span></a></div>';
	
	
//		newSlide = mySwiper.createSlide('<a href="javascript:;"><img id="fullRollImg_'+$(".add_slides").length+'" alt="" src="'+_src+'"></a>');
//		newSlide.append();
		if($(".add_slides").length < 5) {
			$(".imgAdd").before(_cont);
			$(".sd_num").text(5-$(".add_slides").length);
			sel();
		};
		rbundLink('fullRollHref_'+($(".add_slides").length-1)); 
		//设置重新上传图片选择框
		$(".fullRollAgain").click(function(){
			art.dialog.open('${basePath}/queryImageManageByChoose.htm', {
				id: $(this).prop("id"),
				lock: true,
				width: '800px',
				height: '400px',
	
				title: '选择图片'
			});
		});
};
	
function remove_sd(t) {
	var _index = $(t).parents(".add_slides").index(".add_slides"),
		newNum = $(".form_cube").find(".sd_num:visible");
	$(t).parents(".add_slides").remove();
//	mySwiper.removeSlide(_index);
//	mySwiper.resizeFix();
	newNum.text(parseInt(newNum.text()) + 1);
	//$(".swiper-wrapper").css({"-webkit-transform":"translate3d(0px, 0px, 0px)","transform":"translate3d(0px, 0px, 0px)"});
	};

$(function(){
	$(".dd-box").on("click",".del-tags",function(){
		$(this).prev(".sel-tags").html("");
		$(this).parents(".nlink-wp").hide();
	});
});