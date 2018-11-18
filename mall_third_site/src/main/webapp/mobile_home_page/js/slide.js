/*function editImg(t){
	$(t).next(".slide_edit").fadeIn();
};

function editOk(t){
	$(t).parents(".slide_edit").fadeOut();
};*/

function delSd(t){
	$(t).parents(".slide").remove();
	$(".slides_wp").width($(".slide").length * 358);
	if($(".slide").length == 0) {
		$("#sd_prev, #sd_next").hide();
	};
};

function chooseRollAdv(url){
	var _src = url;
	var slides = $("#rollAdvForm").find(".slide");
	var count = slides.length+1;
	_cont = '<div class="slide clearfix">' +
	'<a class="view_img fl rollAgain'+count+'" href="javascript:;"><img class="slide_img rolls'+count+'" alt="" src="' + _src + '"><span>重新上传</span></a>' +
	'<dl class="nlink-wp fl clearfix mt20 ml20">' +
	//'<dt>图片路径：</dt><dd><input class="form-control imgAdvSrc" type="text" name="imgAdvSrc" readonly="readonly"></dd>' +
	'<dt class="dt-box">链接地址：</dt><dd class="dd-box">'+
	'<div class="lk-sel lk-chs"><span class="sel-word"></span><ul><li class="s-selected"><a data-href="lk-01">功能链接</a></li><li><a data-href="lk-02">自定义链接</a></li></ul></div>'+
  	'<div class="lk-sel gn-chs"><span class="sel-word"></span><ul>'+
  	'<li class="s-selected"><a>--请选择--</a></li>'+
  	'<li><a data-href="choose">商品</a></li>'+
  	'<li><a data-href="list/allproduct.html">所有商品</a></li>'+
  	'<li><a data-href="main.html">微店首页</a></li>'+
  	'</ul></div>'+
	'<input class="form-control custom-input" type="text" id="rollImgAdvHref'+count+'" name="rollImgAdvHref"><div class="sel-tags"></div>'+
	'<input type="hidden" id="rollImgAdvSrc'+count+'" name="rollImgAdvSrc">'+
	'</dd></dl>' +
	'<dl class="nlink-wp fl clearfix ml20 none" style="display: none"><dt class="dt-box">已选择：</dt><dd class="dd-box"><span class="sel-tags"></span><a class="del-tags" href="javascript:;">删除</a></dd></dl>'+
	'<a class="remove-slide" href="javascript:;" onclick="remove_sd(this)"><span class="glyphicon glyphicon-remove"></span></a></div>';
	$(".slides_wp").append(_cont).find(".slide_img rolls"+count).prop("src",_src);
	$("#rollImgAdvSrc"+count).val(_src);
	
	rbundLink('rollImgAdvHref'+count);
	//设置重新上传图片选择框
	$(".rollAgain"+count).click(function(){
		art.dialog.open('${basePath}/queryImageManageByChoose.htm?size=10000', {
			id: "rollImgAdvSrc"+count,
			lock: true,
			width: '800px',
			height: '400px',

			title: '选择图片'
		});
	});
}

function updateRollAdvEdit(src,href){
	var _src = src;
	var slides = $("#rollAdvForm").find(".slide");
	var count = slides.length+1;
	_cont = '<div class="slide clearfix">' +
	'<a class="view_img fl rollAgain'+count+'" href="javascript:;"><img class="slide_img rolls'+count+'" alt="" src="' + _src + '"><span>重新上传</span></a>' +
	'<dl class="nlink-wp fl clearfix mt20 ml20">' +
	//'<dt>图片路径：</dt><dd><input class="form-control imgAdvSrc" type="text" name="imgAdvSrc" readonly="readonly"></dd>' +
	'<dt class="dt-box">链接地址：</dt><dd class="dd-box">'+
	'<div class="lk-sel lk-chs"><span class="sel-word"></span><ul><li class="s-selected"><a data-href="lk-01">功能链接</a></li><li><a data-href="lk-02">自定义链接</a></li></ul></div>'+
  	'<div class="lk-sel gn-chs"><span class="sel-word"></span><ul>'+
  	'<li class="s-selected"><a>--请选择--</a></li>'+
  	'<li><a data-href="choose">商品</a></li>'+
  	'<li><a data-href="list/allproduct.html">所有商品</a></li>'+
  	'<li><a data-href="main.html">微店首页</a></li>'+
  	'</ul></div>'+
	'<input class="form-control custom-input" type="text" id="rollImgAdvHref'+count+'" name="rollImgAdvHref" value="'+href+'"><div class="sel-tags"></div>'+
	'<input type="hidden" id="rollImgAdvSrc'+count+'" name="rollImgAdvSrc">'+
	'</dd></dl>' +
	'<dl class="nlink-wp fl clearfix ml20 none" style="display: none"><dt class="dt-box">已选择：</dt><dd class="dd-box"><span class="sel-tags"></span><a class="del-tags" href="javascript:;">删除</a></dd></dl>'+
	'<a class="remove-slide" href="javascript:;" onclick="remove_sd(this)"><span class="glyphicon glyphicon-remove"></span></a></div>';
	$(".slides_wp").append(_cont).find(".slide_img rolls"+count).prop("src",_src);
	$("#rollImgAdvSrc"+count).val(_src);
	
	rbundLink('rollImgAdvHref'+count);
	if(href){
		/*$("#rollImgAdvHref"+count).prev(".gn-chs").val(href);
		var reg = /^(list\/\d)|(item\/\d)/;
		var n =href.search(reg);
		if(n>-1){
			$("#rollImgAdvHref"+count).prev(".gn-chs").val("choose");
		}*/

        var isflag=true;
		//获取所有选项
		var lis = $("#rollImgAdvHref"+count).prev(".gn-chs").find("li");
		//遍历比对，找到对应href的选项，添加选中class
		for(var j=0;j<lis.length;j++){
			$(lis[j]).removeClass("s-selected");
			if(href == $(lis[j]).find("a").attr("data-href")){
				$(lis[j]).addClass("s-selected");
				$(lis[j]).find("a").click();
				sel();
                isflag=false;
			}
		}

		var reg = /^(list\/\d)|(item\/\d)/;
		var n =href.search(reg);
		if(n>-1){
			for(var j=0;j<lis.length;j++){
				if("choose" == $(lis[j]).find("a").attr("data-href")){
					$(lis[j]).addClass("s-selected");
					sel();
                    isflag=false;
				}
			}
		}
        if(href){


        if(isflag){
            var liss = $("#rollImgAdvHref"+count).prev(".gn-chs").prev(".lk-chs").find("li").find("a");

            $(liss[1]).click();
        }
        }
        $("#rollImgAdvHref"+count).val(href);

	}
	
	//设置重新上传图片选择框
	$(".rollAgain"+count).click(function(){
		art.dialog.open('${basePath}/queryImageManageByChoose.htm?size=10000', {
			id: "rollImgAdvSrc"+count,
			lock: true,
			width: '800px',
			height: '400px',

			title: '选择图片'
		});
	});
}