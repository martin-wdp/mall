/*
 *商品模块通用JS 
 * author YuanKangKang
*/
/*商品详情页点击规格值*/
var allProductList = new Array();
var tempList = new Array();
/**
 * 加载所属商品下的所有的货品并初始化规格值按钮
 * @param productList 货品列表
 */
function loadAllProduct(productList)
{
    allProductList = productList;
    /*如果货品信息为空或者长度为0，设置所有的规格值为空*/
    if (allProductList == null || allProductList.length == 0) {
    	$("._sku").addClass("disabled");
    }
    else {
        //控制规格值得按钮
        controlSpecBtn(allProductList,null,true,false);
    }
}
/*点击规格值的时候*/
function clickSpecDetail(obj,bool)
{
    var self = $(obj);
    if (self.hasClass('disabled')) {
        return false;
    }else{
    	//选中自己，兄弟节点取消选中
        self.addClass('selected').siblings().removeClass('selected');
    }
    /*删除保存的以选中记录,并添加新的*/
    $(".sel_spec_"+$(obj).attr("data-parent")).html("“"+$(obj).attr("title")+"”");
    
    self.append("<i></i>");
    var specList = $("._sku");
    var selSpecList = new Array();
    /*获取已经选中的规格值*/
    for (var i = 0; i < specList.length; i++) {
        if ($(specList[i]).hasClass("selected")) {
            selSpecList.push(specList[i]);
        }
    }
    var tempProductList = new Array();
    /*根据已经选中的规格值循环所有的货品筛选出可以被选中的货品*/
    var allRunCount=0;
    for (var j = 0; j < allProductList.length; j++)
    {
    	//获取到货品的关联的规格
        var goodsSpec = allProductList[j].specVo;
        var count = 0;
        //循环货品的规格去选中的规格中匹配
        for (var k = 0; k < goodsSpec.length; k++)
        {
            for (var i = 0; i < selSpecList.length; i++)
            {
            	//如果当前循环的规格和循环到的货品的规格相匹配就给标记+1
                if (goodsSpec[k].goodsSpecDetail.specDetailId == $(selSpecList[i]).attr("data-value")) {
                    count = count - 1 + 2;
                    if (count == 0) {
                        $(specList[i]).addClass("disabled");
                        /*删除保存的以选中记录,并添加新的*/
                    }
                    else {
                        $(specList[i]).removeClass("disabled");
                    }
                };
            };
        };
        /*如果匹配的数量大于等于已经选中的数量则说明完全匹配，跳转到货品详情页*/
        if (count >= selSpecList.length) {
        	if($("#allSpecLength").val()==count){
        		location.href=allProductList[j].goodsInfoId+".html";
        	}
            tempProductList.push(allProductList[j]);
        }else{
        	//如果匹配的数量小于已经选中的数量则给标记+1
        	allRunCount+=1;
        };
    }
    //如果标记等于所有的货品的长度说明没有匹配的货品，就保留当前选中的按钮，重新计算其他可选的按钮
    if(allRunCount==allProductList.length){
    	//选中自己，其他的全部取消选中
    	$("._sku").removeClass('selected');
        /*self.removeClass("disable");*/
        //取消完所有的选中之后选中当前传递的对象
    	self.addClass('selected');
    	self.append("<i></i>");
    	//再次调用当前的方法进行计算
    	clickSpecDetail(self,false);
        //设置控制按钮的方法循环执行计算可选按钮的标记为true
        bool=true;
    }
}
/*控制规格值按钮*/
function controlSpecBtn(tempProductList,obj,bool,checkRunAgain)
{
    var specList = $("._sku");
    var canSelSpec = new Array();
    /*已经获取到匹配的货品取出所有的关联的规格值*/
    for (var i = 0; i < tempProductList.length; i++)
    {
    	//循环可选择的货品的集合获取所有的可选择的规格按钮
        var goodsSpec = tempProductList[i].specVo;
        for (var k = 0; k < goodsSpec.length; k++) {
            canSelSpec.push(goodsSpec[k].goodsSpecDetail.specDetailId);
        }
    }
    /*控制页面中的按钮*/
    for (var i = 0; i < specList.length; i++)
    {
        var count = 0;
        //循环去可选按钮中匹配是否存在
        for (var k = 0; k < canSelSpec.length; k++) {
            if (canSelSpec[k] == $(specList[i]).attr("data-value")) {
                count = count - 1 + 2;
            }
        }
        //如果循环到的按钮匹配可选按钮的数量为0，则标记为不可选中，否则就是可选中
        if (count == 0) {
            $(specList[i]).parent().remove();;
            /*删除保存的以选中记录,并添加新的*/
        }
        else {
        	$(specList[i]).removeClass("disabled");
        }
    }
    //如果传递过来的对象不为空并且，再次执行计算按钮的标记为真，并且方法标记为false时执行以下方法，重新计算可选择的按钮
    if(!bool && obj!=null && checkRunAgain){
    	$(obj).removeClass("disabled");
    	clickSpecDetail(obj,false);
    }
}
/*把已经选择的属性置为选中状态*/
function loadChooseParam(){
	var params=$(".chooseParamId");
	var specList = $("._sku");
	if(params.length>0){
		for(var i=0;i<params.length;i++){
		    for (var k = 0; k< specList.length; k++) {
                $(specList[k]).removeClass("disabled");
		    	if($(specList[k]).attr("data-value") == $(params[i]).val()){
		    		$(specList[k]).addClass("selected");
                    $(specList[k]).addClass("disabled");
		    	}
		    }
		}
	}
//	else{
//		if(allProductList.length>0){
//			 var goodsSpec = allProductList[0].specVo;
//			 for (var j= 0; j < goodsSpec.length; j++){
//				 for (var k = 0; k< specList.length; k++) {
//					 if (goodsSpec[j].goodsSpecDetail.specDetailId==$(specList[k]).attr("data-value")){
//						 $(specList[k]).addClass("selected");
//					 }
//				 }
//		    }
//		}
//	}
}
/**
 * 加载商品的促销信息
 */
function loadGoodsMark(){
	$.get("../gmarket/"+$("#productId").val()+"-"+$("#brandId").val()+"-"+$("#catId").val()+".html",function(data){
			if(null==data || data.length<=0){
				$(".pro_market").append("<li><em>无</em>此商品暂无促销信息</li>");
				return;
			}else{
				
				
				
				var count=0;
				for(var i=0;i<data.length;i++){
					var marketing=data[i];
					//直降促销
					 if(marketing.codexType==1){
							
						 $(".pro_market").append("<li><em class='sales_label'>直降</em>"+marketing.marketingName+"<li>");	 
					 }else if(marketing.codexType==15){
						//如果是折扣促销
						if(marketing.preDiscountMarketings!=null&&marketing.preDiscountMarketings.length!=0){
							for(var j=0;j<marketing.preDiscountMarketings.length;j++){
								if($("#productId").val()==marketing.preDiscountMarketings[j].goodsId){
                                    $(".price").attr("style","display:none");
                                    $(".oldprice").text("￥"+$("#oldPrice").val()).parent().show();
									$(".mark_price").text("￥"+marketing.preDiscountMarketings[j].discountPrice.toFixed(2)).parent().show();

								}
							}
							
						}
						//满减促销
					}else if(marketing.codexType==5){
						
						if(marketing.fullbuyReduceMarketings!=null&&marketing.fullbuyReduceMarketings.length!=0){
							for(var j=0;j<marketing.fullbuyReduceMarketings.length;j++){
								
									$(".pro_market").append("<li><em class='sales_label'>满减</em>满"+marketing.fullbuyReduceMarketings[j].fullPrice+"减"+marketing.fullbuyReduceMarketings[j].reducePrice+"<li>");
							}
							
						}
						//满折促销
					}else if(marketing.codexType==8){
						
						if(marketing.fullbuyDiscountMarketings!=null&&marketing.fullbuyDiscountMarketings.length!=0){
							for(var j=0;j<marketing.fullbuyDiscountMarketings.length;j++){
								
									$(".pro_market").append("<li><em class='sales_label'>满折</em>满"+marketing.fullbuyDiscountMarketings[j].fullPrice+"打"+(marketing.fullbuyDiscountMarketings[j].fullbuyDiscount*10)+"折</em><li>");
							}
							
						}
					}else if(marketing.codexType==12){
						
						$(".pro_market").append("支持"+marketing.shippingMoney+"免运费");
					}
					
				}
				
				
				
				
				
				
				
				
				
				
/*
				
				for(var i=0;i<data.length;i++){
					var marketing=data[i];
					//如果促销类型是1表明是直降
					if(marketing.codexType==1){
						$(".pro_market").append("<li><em class='sales_label'>直降"+marketing.priceOffMarketing.offValue.toFixed(2)+"</em>"+marketing.marketingName+"</li>");
					}else if(marketing.codexType==2){ //如果促销类型是2 表明是赠品
						if(null != marketing.giftList && marketing.giftList.length>0){
							$(".pro_market").append("<li><em class='sales_label'>赠品</em>"+marketing.marketingName+"</li>");
						}
					}else if(marketing.codexType==3){ //如果促销类型是3 表明是送优惠券
						$(".pro_market").append("<li><em class='sales_label'>送券</em>"+marketing.marketingName+"</li>");
					}else if(marketing.codexType==4){ //如果促销类型是4 表明是买折
						$(".pro_market").append("<li><em class='sales_label'>折扣</em>"+marketing.marketingName+"</li>");
					}else if(marketing.codexType==5){ //如果促销类型是5 表明是满减
						$(".pro_market").append("<li><em class='sales_label'>满减</em>"+marketing.marketingName+"</li>");
					}else if(marketing.codexType==6){ //如果促销类型是6 表明是满赠赠品
						$(".pro_market").append("<li><em class='sales_label'>满赠</em>"+marketing.marketingName+"</li>");
					}else if(marketing.codexType==7){ //如果促销类型是7表明是满送优惠优惠券
						$(".pro_market").append("<li><em class='sales_label'>满送券</em>"+marketing.marketingName+"</li>");
					}else if(marketing.codexType==8){ //如果促销类型是8 表明是满折
						$(".pro_market").append("<li><em class='sales_label'>满折</em>"+marketing.marketingName+"</li>");
					}
				}*/
			}
	});
}


/*加载商品评论*/
/*加载商品评论*/
function loadComment(pageNo,type){
	/*清空相关的div*/
	$(".comment_item_list").html("");
	$(".comment_pages").html("");
	var productId=$("#productId").val();
	var params="&productId="+productId;
	params+="&pageNo="+pageNo+"&pageSize=1";
	var haoCount=0;
	var zhongCount=0;
	var chaCount=0;
	/*AJAX查询商品好评*/
	$.get("../goods/queryProducCommentForDetail.html?type=0"+params,function(data){
		/*设置所有的行数*/
		haoCount=data.rows;
		if(type==0){
			putPageComment(type,data);
		}
		$(".hao_count").text(haoCount);
	});
	/*AJAX查询商品中评*/
	$.get("../goods/queryProducCommentForDetail.html?type=1"+params,function(data){
		/*设置所有的行数*/
		zhongCount=data.rows;
		if(type==1){
			putPageComment(type,data);
		}
		$(".zhong_count").text(zhongCount);
	});
	/*AJAX查询商品差评*/
	$.get("../goods/queryProducCommentForDetail.html?type=2"+params,function(data){
		/*设置所有的行数*/
		chaCount=data.rows;
		if(type==2){
			putPageComment(type,data);
		}
		$(".cha_count").text(chaCount);
	});
}

$(function(){
	
	
	/*点击加减按钮*/
	$(".num_minus").click(function(){
		var count = parseFloat($(".product_count").val());
		if(count >1){
			$(".product_count").val(parseFloat(count - parseFloat(1)));
		}
	});
	
	/*点击加减按钮*/
	$(".num_plus").click(function(){
		var count = parseFloat($(".product_count").val());
		$(".product_count").val(parseFloat(count + parseFloat(1)));
	});
	
	$(".product_count").keyup(function(){
		  var count = $(".product_count").val();
		  if(isNaN(count)){
			  $(".product_count").val("1");
		  }
	});
	
	
	
	
	$(".load_comment_tit").click(function(){
		 $($(".load_comment")[0]).addClass('cur').siblings().removeClass('cur');
		loadComment(1,0);
	});
	$(".load_comment").click(function(){
		//选中自己，兄弟节点取消选中
        $(this).addClass('cur').siblings().removeClass('cur');
		loadComment(1,$(this).attr("role"));
	});
	
	var addCartNum=0;
	$(".add_cart").click(function(){
		/*如果选中的规格小于所有的规格,表示规格选择不完全,不允许加入购物车*/
		if($(".selected").length<parseFloat($("#allSpecLength").val())){
			$(".tip_text").html("请选择规格!");
			$(".buy_now_tip").click();
			return ;
		}
		/*如果购买数量大于剩余库存不允许购买*/
		$(".tip_text").html("库存不足!");
		var count = parseFloat($(".product_count").val());
		var stock = parseFloat($("#productStock").val());
        var goodsInfoAdded=parseFloat($("#goodsInfoAdded").val());
        if(goodsInfoAdded!=1){
            $(".tip_text").html("货品已下架!");
        }
		if(count<=stock){
            addCartNum=addCartNum+1;
			location.href="../addproducttoshopcarnew.htm?goodsNum="+$(".product_count").val()+"&productId="+$("#productId").val()+"&distinctId=1103";
		}else{
            //if(addCartNum==0){
                $(".buy_now_tip").click();
            //}

		}
	});
	var buyNum=0;
	/*立即购买时间*/
	$(".buy_now").click(function(){
		/*如果选中的规格小于所有的规格,表示规格选择不完全,不允许加入购物车*/
		if($(".selected").length<parseFloat($("#allSpecLength").val())){
			$(".tip_text").html("请选择规格!");
			$(".buy_now_tip").click();
			return ;
		}
		/*如果购买数量大于剩余库存不允许购买*/
		$(".tip_text").html("库存不足!");
		var count = parseFloat($(".product_count").val());
		var stock = parseFloat($("#productStock").val());
		if(count<=stock){
            buyNum=buyNum+1;
			location.href="../addproducttoshopmcarl.htm?goodsNum="+$(".product_count").val()+"&productId="+$("#productId").val()+"&distinctId=1103";
		}else{

            //if(buyNum==0){
                $(".buy_now_tip").click();
            //}

		}
	});
	
	
});

/*将查询到的评论加载到页面中*/
function putPageComment(type,data){
	var commentHtml="";
	if(data.list!=null && data.list.length>0){
		for(var l=0;l<data.list.length;l++){
			var comment = data.list[l];
			commentHtml+="<div class='comment_item'><p><strong>"+comment.commentContent+"</strong></p>" +
					"<p class='light spec_buys'></p><p class='light'>普通会员 "+comment.customerNickname+"</p><span>"+timeStamp2String(comment.buyTime)+"</span></div>";
		}
		/*加载分页部分*/
		var paging="";
		var pageNo=0;
		if((data.pageNo-2)>0){
			pageNo=(data.pageNo-2);
		}else{
			pageNo=data.firstPageNo;
		}
		var endNo=0;
		
		if((data.lastPageNo-1)>0){
			endNo=(data.lastPageNo-2);
		}else{
			endNo=1;
		}
		if(data.pageNo==1){
			
			paging+="<div class='col-xs-6'><a class='disabled' href='javascript:;'>&lt;&nbsp;上一页</a></div>";
		}else{
			paging+="<div class='col-xs-6'><a ";
			if(data.nextPageNo>0){
				paging+="onClick='loadComment("+data.prePageNo+","+type+");'" ;
			}
			paging+=" href='javascript:;'>&lt;&nbsp;上一页</a></div>";
		}
		if((data.pageNo==data.lastPageNo || data.endNo<=1)){
			paging+="<div class='col-xs-6'> <a href='javascript:;' class='disabled'>下一页&nbsp;&gt;</a> </div>";
		}else{
			paging+="<div class='col-xs-6'> <a href='javascript:;' ";
			if(data.nextPageNo>0){
				paging+=" onClick='loadComment("+data.nextPageNo+","+type+");'";
			}
			paging+=" >下一页&nbsp;&gt;</a> </div>";
		}
		$(".comment_pages").html(paging);
		/*加载分页部分 END*/
	}else{
		commentHtml+="<div class='comment_item'><p><strong>暂无商品评价！</strong></p></div>";
	}
	$(".comment_item_list").html(commentHtml);
		var spec =$(".chooseParamId");
		var html="";
		if(null != spec && spec.length>0){
			html+="";
			   for(var i=0;i<spec.length;i++){
				   html+=""+$(spec[i]).attr('data-spec')+"："+$(spec[i]).attr('data-detail')+"&nbsp;&nbsp;&nbsp;";
			   }
			   html+="";
			   $(".spec_buys").html(html);
		}
}
/*处理时间格式化*/
function timeStamp2String(time){
		var date=new Date(parseFloat(time));
	    var datetime = new Date();
	    datetime.setTime(date);
	    var year = datetime.getFullYear();
	    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
	    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
	    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
	    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
	    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
	    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
}
