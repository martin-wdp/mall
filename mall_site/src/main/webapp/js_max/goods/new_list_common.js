$(function(){

   var isFollow=$("#isFollow").val();
    if(isFollow=="1"){
        $(".cllect_btn").addClass("liked");

    }else{
        $(".cllect_btn").removeClass("liked");
    }



	/*商品列表页点击收藏商品事件*/
	$(".cllect_btn_list").click(function(){
		var districtId = $("#disId").val();
		var goodsprice = $("#followPrice").val();
		var _this=$(this);
		$.post("../atte/"+$(this).attr("product_id")+".html",{districtId:districtId,goodsprice:goodsprice},function(data){
			if(data=="-1"){
				/*初始化弹框样式*/
				$(".info_tip_content2").html("您已经收藏过该商品！");
				$(".info_tip_img2").attr("src","../images/collect.png");
				$(".info_tip_cancel2").attr("href","javascript:;").hide();
				$(".info_tip_submit2").attr("href","javascript:;").show().html("确定").unbind("click");
				$(".info_tip_submit2").click(function(){
					cls();
				});
				dia(2);
			}else if(data=="-2"){
				/*初始化弹框样式*/
				$(".info_tip_content2").html("是否跳转到登录页?");
				$(".info_tip_img2").attr("src","../images/index_ques.png");
				$(".info_tip_cancel2").attr("href","javascript:;").html("取消").show();
				$(".info_tip_submit2").attr("href","../login.html").show().html("确定").unbind("click");
				$(".info_tip_cancel2").click(function(){
					cls();
				});
				dia(2);
			}else{
				/*初始化弹框样式*/
				$(".info_tip_content2").html("恭喜您收藏成功！");
				$(".info_tip_img2").attr("src","../images/collect.png");
				$(".info_tip_cancel2").attr("href","javascript:;").hide();
				$(".info_tip_submit2").attr("href","javascript:;").show().html("确定").unbind("click");
				$(".info_tip_submit2").click(function(){
					cls();
				});
				dia(2);

			}

			if(data=="-2"){
				/*初始化弹框样式*/
				$(".info_tip_content2").html("您还未登录不能关注该商品");
				$(".info_tip_content2_1").html("是否跳转到登录页?");
				$(".info_tip_img2").attr("src","../images/index_ques.png");
				$(".info_tip_cancel2").attr("href","javascript:;").html("取消").show();
				$(".info_tip_submit2").attr("href","../login.html").show().html("确定").unbind("click");
				$(".info_tip_cancel2").click(function(){
					cls();
				});
				dia(2);
			}
			else{
				if(_this.hasClass("liked")) {
					_this.removeClass("liked");
				} else {
					_this.addClass("liked");}
			}
		});
	});


	/*商品详情页点击收藏商品事件*/
    $(".cllect_btn").click(function(){
    	var districtId = $("#disId").val();
    	var goodsprice = $("#followPrice").val();
        var _this=$(this);
    	$.post("../atte/"+$(this).attr("product_id")+".html",{districtId:districtId,goodsprice:goodsprice},function(data){
            if(data=="-2"){
                	/*初始化弹框样式*/
                	$(".info_tip_content2").html("您还未登录不能关注该商品");
                	$(".info_tip_content2_1").html("是否跳转到登录页?");
                	$(".info_tip_img2").attr("src","../images/index_ques.png");
                	$(".info_tip_cancel2").attr("href","javascript:;").html("取消").show();
                	$(".info_tip_submit2").attr("href","../login.html").show().html("确定").unbind("click");
                	$(".info_tip_cancel2").click(function(){
                		cls();
                	});
                	dia(2);
                }
            else{
                if(_this.hasClass("liked")) {
                    _this.removeClass("liked");
                } else {
                    _this.addClass("liked");}
            }
    	});
    });
    
    /*加入购物车绑定事件*/
	$(".add_shop_cart").click(function(){
		if($(this).attr("product_stock")<=0){
			/*初始化弹框样式*/
			$(".info_tip_content2").html("库存不足！");
			$(".info_tip_img2").attr("src","../images/outstock.png");
			$(".info_tip_cancel2").attr("href","javascript:;").hide();
			$(".info_tip_submit2").attr("href","javascript:;").show().html("确定").unbind("click");
			$(".info_tip_submit2").click(function(){
				cls();
			});
			dia(2);
			return 0;
		}
		var num = 1;
		var productId = $(this).attr("product_id");
		var params = "goodsNum="+num;
		params += "&productId="+productId;
		if($(this).attr("distinct_id")>0){
			params += "&distinctId="+$(this).attr("distinct_id");
		}
		/*请求加入购物车控制器*/
		$.post("../goods/addProductToShopCar.html?"+params,function(data){
			if(data){
				/*初始化弹框样式*/
				$(".info_tip_title").html("加入购物车");
				$(".info_tip_content").html("加入购物车成功！");
				$(".info_tip_img").attr("src","../images/cart_img.png");
				$(".info_tip_cancel").attr("href","javascript:;").html("继续购物").show();
				$(".info_tip_submit").attr("href","../myshoppingcart.html").html("立即结算").show().unbind("click");
				$(".info_tip_cancel").click(function(){
					cls();
				});
				dia(1);
                $(".cartNum").text(parseInt($(".cartNum").text())+1);
			}else{
				/*初始化弹框样式*/
				$(".info_tip_title").html("加入购物车");
				$(".info_tip_content").html("加入购物车失败,请重试");
				$(".info_tip_img").attr("src","../images/error.png");
				$(".info_tip_cancel").attr("href","javascript:;").hide();
				$(".info_tip_submit").attr("href","javascript:;").html("确定").show().unbind("click");
				$(".info_tip_submit").click(function(){
					cls();
				});
				dia(1);
			}
		});
	});
	
});

function cllectbtnlist(goodsInfoId,goodsInfoPreferPrice,obj){
	var districtId = $("#disId").val();
    var _this=$(obj);
	$.post("../atte/"+goodsInfoId+".html",{districtId:districtId,goodsprice:goodsInfoPreferPrice},function(data){
		if(data=="-2"){
			/*初始化弹框样式*/
			$(".info_tip_content2").html("您还未登录不能关注该商品");
			$(".info_tip_content2_1").html("是否跳转到登录页?");
			$(".info_tip_img2").attr("src","../images/index_ques.png");
			$(".info_tip_cancel2").attr("href","javascript:;").html("取消").show();
			$(".info_tip_submit2").attr("href","../login.html").show().html("确定").unbind("click");
			$(".info_tip_cancel2").click(function(){
				cls();
			});
			dia(2);
		}
		else{
			if(_this.hasClass("liked")) {
				_this.removeClass("liked");
			} else {
				_this.addClass("liked");}
		}
	});

}
