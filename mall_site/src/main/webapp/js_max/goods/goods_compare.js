$(function() {
	refreshCompare();
	/*点击“对比”事件*/
	$(".compare").click(function(){
		var goodsId = $(this).attr("product_id");
		if($(this).hasClass("checked")) {
			$(".goods_contrast").show();
			unCompare(goodsId);
		} else {
			$(".goods_contrast").show();
			compare(goodsId);
		}
	});
	
	/*在对比栏中点击“删除”事件*/
	$(".delete").click(function(){
		unCompare($(this).attr("product_id"));
	});

});
function compareGoodsList(obj){
	var goodsId = $(obj).attr("product_id");
	if($(obj).hasClass("checked")) {
		$(".goods_contrast").show();
		unCompare(goodsId);
	} else {
		$(".goods_contrast").show();
		compare(goodsId);
	}
}
var compare_cookie_name = "_npstore_compare_goodsId";
/* 勾选对比，将货品id放入cookie*/
function compare(goodsId) {
	var goodsIds = getCookie(compare_cookie_name);
	if(goodsIds!=null) {
		var ids = goodsIds.split(".");
		for(var i=0;i<ids.length;i++) {
			if(ids[i]==goodsId) {
				loadCompareGoods();
				$(".compare_box").show();
				return;
			} 
		}
	}
	
	if(goodsIds!=null&&goodsIds!=""&&goodsIds.toString().split(".").length==4) {
		/*初始化弹框样式*/
		$(".info_tip_content2").html("对比栏已满！");
		$(".info_tip_img2").attr("src","../images/error.png");
		$(".info_tip_cancel2").attr("href","javascript:;").hide();
		$(".info_tip_submit2").attr("href","javascript:;").show().html("确定").unbind("click");
		$(".info_tip_submit2").click(function(){
			cls();
		});
		dia(2);
		
		$("#compare"+goodsId).removeClass("checked");
		loadCompareGoods();
		$(".compare_box").show();
		return; 
	}
	if(goodsIds==null||goodsIds=="") {
		setCookie(compare_cookie_name,goodsId);
	} else {
		setCookie(compare_cookie_name,goodsIds.toString()+"."+goodsId);
	}
	//放入cookie后，刷新“对比”按钮和对比栏
	refreshCompare();
	$(".compare_box").show();
}

/*勾掉对比，将货品id从cookie中删除*/
function unCompare(goodsId) {
	var goodsIds = getCookie(compare_cookie_name).toString().split(".");
	var newGoodsIds = "";
	delCookie(compare_cookie_name);
	//把当前goodsId从cookie中删除
	for(var i=0,j=0;i<goodsIds.length;i++) {
		if(goodsIds[i]==goodsId) continue;
		if(j==0) {
			newGoodsIds += goodsIds[i];
		} else {
			newGoodsIds +="."+goodsIds[i];
		}
		j++;
	}
	//删除完，重新放入cookie
	setCookie(compare_cookie_name,newGoodsIds);
	refreshCompare();
	$(".compare_box").show();
}
/*隐藏对比框*/
function hideCompareBox() {
	$(".goods_contrast").hide();
}
/*从cookie中获取对比货品的id，然后刷新对比按钮样式*/
function refreshCompare() {
	var cookieValue = getCookie(compare_cookie_name);
	if(cookieValue!=null) {
		$(".compare").removeClass("checked");
		var goodsIds = cookieValue.toString().split(".");
		//改变对比按钮的样式（勾选）
		for(var i=0;i<goodsIds.length;i++) {
			$("#compare"+goodsIds[i]).addClass("checked");
		}
	}
	loadCompareGoods();
}

/*从cookie中获取对比货品信息，然后塞到对比栏中*/
function loadCompareGoods() {
	var cookieValue = getCookie(compare_cookie_name);
	var goodsIds = "0";
	if(cookieValue!=null && cookieValue!="") {
		goodsIds = cookieValue.toString().split(".");
	} else {
		//如果没有对比的货品，就隐藏对比栏
		$(".compare_box").hide();
	}
	//拼参数，0-0-0-0，四个货品id
	var param = "";
	for(var i=0;i<4;i++) {
		param += getGoodsIdParam(goodsIds,i);
	}
	$.ajax({
		url:'../goods/getCompareGoods.html?goodsIds='+param,
		success:function(data) {
			$("#compareBox").html("");
			var html = '';
			var productName = "";
			//加载对比栏
			for(var i=0;i<data.length;i++) {
				if(data[i].productVo.productName.length>18) {
					productName = data[i].productVo.productName.substring(0,18);
				} else {
					productName = data[i].productVo.productName;
				}
				html += '<dl class="has_item clearfix fl">';
				if(data[i].productVo.imageList.length>0) {
					html += '<dt><a href="../item/'+data[i].productVo.goodsInfoId+'.html" target="_blank"><img width="50" alt="'+data[i].productVo.productName+'" src="'+data[i].productVo.imageList[0].imageThumName+'" /></a></dt>';
				} else {
					html += '<dt>'+(i+1)+'</dt>';
				}
				html += '<dd><p class="ct-item-name"><a href="../item/'+data[i].productVo.goodsInfoId+'.html" target="_blank">'+productName+'</a></p>'
						+'<span class="ct-item-price">￥'+accAddInt(data[i].productVo.goodsInfoPreferPrice,0)+'</span>'
						+'<a href="javascript:void(0);" class="del-ct-item"  onclick="unCompare('+data[i].productVo.goodsInfoId+')">删除</a></dd></dl>';
			}
			for(var i=data.length+1;i<=4;i++) {
				html += '<dl class="empty_item clearfix fl"><dt>'+i+'</dt>'
						+'<dd>你还可以继续添加</dd></dl>';
			}
			html += '<div class="ct_operation fl tc">';
			if(goodsIds.length<2) {
				html += '<a class="contrast_btn dis_ct" href="javascript:;" class="compare_btn">对比</a>';
			} else {
				html += '<a class="contrast_btn" href="javascript:;" onclick="compareGoods();" class="compare_btn">对比</a>';
			}
			html += '<p class="del-items mt10"><a href="javascript:;" onclick="deleteAll();">清空对比栏</a></p>';
			$("#compareBox").html(html);
			if(data.length<=0){
				$(".goods_contrast").hide();
			}else{
				$(".goods_contrast").show();
			}
 		}
	});
}
/*拼接goodsids参数 0-0-0-0，四个goodsId*/
function getGoodsIdParam(goodsIds,position) {
	var param = "";
	if(position==0) {
		if(goodsIds[0]!=null&&goodsIds[0]!="undefined") {
			param += goodsIds[0];
		} else {
			param += "0";
		}
	} else {
		if(goodsIds[position]!=null&&goodsIds[position]!="undefined") {
			param += "-"+goodsIds[position];
		} else {
			param += "-0";
		}
	} 
	return param;
}

/*在对比栏中点击对比*/
function compareGoods() {
	var goodsIds = getCookie(compare_cookie_name).toString().split(".");
	if(goodsIds.length<2) {
		/*初始化弹框样式*/
		$(".info_tip_content2").html("至少选择2个商品进行对比！");
		$(".info_tip_img2").attr("src","../images/error.png");
		$(".info_tip_cancel2").attr("href","javascript:;").hide();
		$(".info_tip_submit2").attr("href","javascript:;").show().html("确定").unbind("click");
		$(".info_tip_submit2").click(function(){
			cls();
		});
		dia(2);
		return;
	}
	var param = "";
	for(var i=0;i<4;i++) {
		param += getGoodsIdParam(goodsIds,i);
	}
	window.open("../compare/"+param+".html");
}
/*点击“清空对比栏”*/
function deleteAll() {
	$(".compare").removeClass("checked");
	delCookie(compare_cookie_name);
	loadCompareGoods();
	$(".compare_box").show();
	$(".goods_contrast").hide();
}
/*添加cookie*/
function setCookie(name ,value) {
	var exp = new Date();    //new Date("December 31, 9998");
	exp.setTime(exp.getTime() + 7*24*60*60*1000);
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString()+";path=/";
}
//取cookies函数       
function getCookie(name) {
   var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
   if(arr != null) return unescape(arr[2]); return null;
}
//删除cookie
function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval=getCookie(name);
	if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString()+";path=/";
}


//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
  //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
  var curWwwPath=window.document.location.href;
  //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
  var pathName=window.document.location.pathname;
  var pos=curWwwPath.indexOf(pathName);
  //获取主机地址，如： http://localhost:8083
  var localhostPaht=curWwwPath.substring(0,pos);
  //获取带"/"的项目名，如：/uimcardprj
  var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
  return(localhostPaht+projectName);
}

