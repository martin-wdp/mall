<!DOCTYPE html>
<html lang="zh-cn">
  <head>
  	<#assign basePath=request.contextPath>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${seo.meteKey}">
    <meta name="description" content="${seo.meteDes}">
    <#if (sys.bsetName)??>
    	<title>购物车-${(sys.bsetName)!''}</title>
    	<input type="hidden" id="bsetName" value="${(sys.bsetName)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    <#else>
	    <title>购物车-${(seo.mete)!''}</title>
	    <input type="hidden" id="bsetName" value="${(seo.mete)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    </#if>
    <!-- Bootstrap -->
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">
    

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]> 
      <script src="${basePath}/js/html5shiv.min.js"></script>
      <script src="${basePath}/js/respond.min.js"></script>
    <![endif]-->
 <script type="text/javascript" src="${basePath}/js/shoppingcart/jsOperation.js"></script>
<script type="text/javascript">
    function checked(obj) {
    	if($("#check_btn"+obj).attr("c-status")==0){
    		$("#check_btn"+obj).attr("c-status","1");
    	}else{
			$("#check_btn"+obj).attr("c-status","0");
		}
		oncheck();
	}
	
	function oncheck(){
		var obj=document.getElementsByName('boxs');
		var boxsI=document.getElementsByName('boxsI');
		var price = 0;
		var truebuy = 0;
		if(boxsI.length==0){
			$(".wrap").show();
			$(".cart_list").hide();
		}else{
			$(".wrap").hide();
			$(".cart_list").show();
		}
		for(var i=0; i<obj.length; i++){    
		   if($(obj[i]).attr("c-status")==0){
		   	   truebuy++;
			   var s=$(obj[i]).parents(".cart_item").find(".productPrice").val();
			   var n=$(obj[i]).parents(".cart_item").find(".productNum").val();
			   price=accAdd(price,accMul(s,n)); 
		   }
		}   
		$("#suprice").html('¥'+formatCurrency(price));
		$("#truebuy").html(truebuy);
		
		for(var i=0; i<obj.length; i++){    
			if(obj[i].checked){
				$(".check_all").prop("checked","checked");
			}else{
		    	$(".check_all").prop("checked","");
		    	return null;
			}
		}
	}
	function formatCurrency(num) {
	    num = num.toString().replace(/\$|\,/g,'');
	    if(isNaN(num))
	    num = "0";
	    sign = (num == (num = Math.abs(num)));
	    num = Math.floor(num*100+0.50000000001);
	    cents = num%100;
	    num = Math.floor(num/100).toString();
	    if(cents<10)
	    cents = "0" + cents;
	    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
	    num = num.substring(0,num.length-(4*i+3))+','+
	    num.substring(num.length-(4*i+3));
	    return (((sign)?'':'-') + num + '.' + cents);
	}
	function chenckAll(cc){
			var obj=document.getElementsByName('boxs');
			if(cc==1){
				for(var i=0; i<obj.length; i++){    
					$(obj[i]).attr("c-status","0");	
				}
			}else{
				for(var i=0; i<obj.length; i++){    
					$(obj[i]).attr("c-status","1");	
				}
			}
			oncheck();
	}
</script>
  </head>
    <body>
    
    <div class="cart_list">
    <form name="subForm" id="subForm" action="${basePath}/order/suborder.html" method="post">
    <!--客户id-->
    <input type="hidden" value="<#if customerId??>${customerId}</#if>" id="customerId"/>
        <input type="hidden" value="${basePath}" id="basePath"/>
    <!--客户id-->
    <#if pb.list??>
     <#list pb.list as cart>
     <#if cart.goodsDetailBean??>
     <!--商品-->
      <div id="activity${cart.shoppingCartId}" class="cart_good cart_good_hover cart_item">
      <input name="boxsI" type="hidden">
      <#if cart.goodsDetailBean.productVo.goodsInfoStock!=0 & cart.goodsDetailBean.productVo.goodsInfoDelflag='0' &  cart.goodsDetailBean.productVo.goodsInfoAdded!='0'>
    	 <a class="check_btn" name="boxs" cid="${cart.shoppingCartId}" c-status="0"  id="check_btn${cart.shoppingCartId}"  onclick="checked(${cart.shoppingCartId})"></a>
      </#if>
        <div class="img"><a href="${basePath}/item/${cart.goodsDetailBean.productVo.goodsInfoId}.html" target="_blank" title="${basePath}/item/${cart.goodsDetailBean.productVo.goodsInfoId}.html"><img title="${cart.goodsDetailBean.productVo.productName}" alt="${cart.goodsDetailBean.productVo.productName}" src="<#if cart.goodsDetailBean.productVo.goodsInfoImgId??>${cart.goodsDetailBean.productVo.goodsInfoImgId}</#if>" /></a></div>
        <div class="word">
          <p>
      	    <a href="${basePath}/item/${cart.goodsDetailBean.productVo.goodsInfoId}.html" target="_blank" title="${cart.goodsDetailBean.productVo.productName}">
      	    	<#if cart.goodsDetailBean.productVo.productName?length gt 28>
	        					${cart.goodsDetailBean.productVo.productName[0..28]}...
	        	<#else>
	        					${cart.goodsDetailBean.productVo.productName}
	        	</#if>
      	    </a>
           </p>
           <input type="hidden" value="${cart.goodsDetailBean.productVo.goodsInfoId}" class="pro_id${cart.shoppingCartId}">
          <p class="price">￥${(cart.goodsDetailBean.productVo.goodsInfoPreferPrice?number)?string("0.00")}</p>
          <input type="hidden" class="productPrice" value="${cart.goodsDetailBean.productVo.goodsInfoPreferPrice?number}" >
        </div>
        <!--库存-->
        <div class="count">
          <a href="javascript:;"  onclick="mit(this,${cart.shoppingCartId});" class="minus"><span>-</span></a>
          <input type="text" id="goods_num_${cart.shoppingCartId}" onblur="opblur(this,${cart.shoppingCartId},${cart.goodsDetailBean.productVo.goodsInfoStock});" class="count_num productNum" value="<#if cart.goodsNum??>${cart.goodsNum}</#if>">
          <input type="hidden" id="goods_onb_${cart.shoppingCartId}" class="count_num productNum" value="${cart.goodsDetailBean.productVo.goodsInfoStock}">
         <input type="hidden" class="sumNum" value="${cart.goodsDetailBean.productVo.goodsInfoStock}"/>
         <input type="hidden" class="xzNum" value="<#if cart.goodsNum??>${cart.goodsNum}</#if>"/>
          <a href="javascript:;" class="plus" onclick="add(this,${cart.shoppingCartId});"><span>+</span></a>
        </div>
		<a class="del" onclick="dodel(${cart.shoppingCartId},${cart.goodsDetailBean.productVo.goodsInfoId})" href="javascript:;"><span class="glyphicon glyphicon-trash"></span></a>
		</div>
     <!--商品end-->
     </#if>
      </#list>
     <#else>
	    
     </#if>
      <div class="cart_total">
        <p class="total"><strong>合计:<span id="suprice"></span>元</strong></p>
      </div>
    </div><!-- /cart_list -->
      <div class="wrap">
      <div class="cart_empty">
        <div class="cart_icon"></div>
        <p>您的购物车是空的，赶紧选购吧</p>
      </div>
      <div class="go_buy">
        <button type="button" class="btn btn-warning btn-lg btn-block" onclick="goShop()">去购物</button>
      </div>
    </div>
    
    <div class="cart_check container-fluid">
      <div class="row">
        <div class="col-xs-6">
          <a href="javascript:;" onclick="chenckAll(1)" class="all_check"><i></i>全部选择</a>
          <a href="javascript:;" onclick="chenckAll(0)" class="all_not_check"><i></i>取消全选</a>
        </div>
        <div class="col-xs-6">
          <a href="javascript:;" onclick="onpay()" class="go_check">结算</a>
        </div>
      </div>
    </div><!-- /cart_check -->
    </form>
    <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->
     <a href="javascript:;" class="none buy_now_tip"  data-toggle="modal" data-target="#goods_tips"></a>
	<div class="modal fade" role="dialog" id="goods_tips">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">商城消息</h4>
          </div>
          <div class="modal-body">
            <p class="tip_text"></p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onClick="$('#goods_tips').modal('hide');">确定</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    
    
    <div class="modal fade" role="dialog" id="goods_tips_del">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">商城消息</h4>
          </div>
          <div class="modal-body">
            <p class="tip_text"></p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearmess()">取消</button>
            <button type="button" class="btn btn-primary" onClick="delc();">确定</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    
    
    <div class="modal fade" role="dialog" id="goods_tips2">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">商城消息</h4>
          </div>
          <div class="modal-body">
            <p class="tip_text"></p>
          </div>
          <div class="modal-footer">
             <button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearmess()">取消</button>
    		 <button type="button" class="btn btn-primary" onClick="changeUrl();">确定</button>
   		 </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    
    
   	<a href="javascript:;" class="none tan_k"  data-toggle="modal" data-target="#goods_tips"></a>
    <a href="javascript:;" class="none tan_k2"  data-toggle="modal" data-target="#goods_tips2"></a>
     <a href="javascript:;" class="none tan_k3"  data-toggle="modal" data-target="#goods_tips_del"></a>
    
 	 <input type="hidden" value="0" id="shoppingCartId"/>
     <input type="hidden" value="0" id="goodsInfoId"/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/customer/wxforward.js"></script>
    <script>
	  $(function(){
	    oncheck();
		FastClick.attach(document.body);
		$('.check_btn').click(function(){
		  if($(this).parent().attr('class').indexOf('hover')>=0){
			$(this).parent().removeClass('cart_good_hover');
			$('.all_check').css('display','block');
			$('.all_not_check').hide();
		  }
		  else{
			$(this).parent().addClass('cart_good_hover');
			if($('.cart_good').length == $('.cart_good_hover').length){
			  $('.all_not_check').css('display','block');
			  $('.all_check').hide();
			}
		  }
		});
		$('.all_check').click(function(){
		  $('.cart_good').addClass('cart_good_hover');
		  $(this).hide();
		  $('.all_not_check').css('display','block');
		});
		$('.all_not_check').click(function(){
		  $('.cart_good').removeClass('cart_good_hover');
		  $(this).hide();
		  $('.all_check').css('display','block');
		});
	  });
	  
	  function add(obj,id){   
		var custId = $("#customerId").val();
		var sumNum = $(obj).parent().find(".sumNum").val();
		var num = $(obj).parent().find(".productNum").val();
		var xzNum = $(obj).parent().find(".xzNum").val();
		if(num>=99){
			$(obj).parent().find(".productNum").val(num);
			if(custId==""){
					$(".xzNum").attr("value",Subtr($(obj).parent().find(".productNum").val(),0));	
			}
			return null;
		}
			if(Subtr(sumNum,num)>0){
				if(custId==""){
					var goodsInfoId=$(".pro_id"+id).val();
						changeNumCoo(goodsInfoId,1);		
					$(".xzNum").attr("value",accAddInt(num,1));
				}else{
				
					changeNum(id,accAddInt(num,1)); 
				}
					var count=accAddInt(num,1);
					
					$(obj).parent().find(".productNum").val(accAddInt(num,1)); 
					
			}
		oncheck();
	}
	function mit(obj,id){
	var custId = $("#customerId").val();
	var num = $(obj).parent().find(".productNum").val();
		if($(obj).parent().find(".productNum").val()<=1){
			$(obj).parent().find(".productNum").val(1);
		}else{
			if(custId==""){
					var goodsInfoId=$(".pro_id"+id).val();
					changeNumCoo(goodsInfoId,-1);
					$(".xzNum").attr("value",Subtr($(obj).parent().find(".productNum").val(),1));
			}else{
				changeNum(id,Subtr(num,1)); 
			}
				$(obj).parent().find(".productNum").val(Subtr($(obj).parent().find(".productNum").val(),1));
		}
	    
		oncheck();
	}
	
	
	function opblur(obj,id,count){
		var nums = $(obj).parent().find(".productNum").val();
		var custId = $("#customerId").val();
	    var num = $(obj).val();
		var sumNum = $(obj).parent().find(".sumNum").val();
		var xzNum = $(obj).parent().find(".xzNum").val();
		if(nums>99){
			$(".tip_text").html("数量最高为99");
			$(".tan_k").click();
			$(obj).parent().find(".productNum").val(xzNum);
			return false;
		}
		if(/^\d+$/.test(num))    
			{    
			}else{
			if(custId==""){
					var goodsInfoId=$(".pro_id"+id).val();
					xzNum=xzNum-1;
					changeNumCoo(goodsInfoId,-xzNum);		
			}else{
			   changeNum(id,1);
			}
			   $(obj).parent().find(".productNum").val(1);
			   return false;
		}
		if(count>=num){
			
			if(num<=0){
					if(custId==""){
						var goodsInfoId=$(".pro_id"+id).val();
						xzNum=xzNum-1;
						changeNumCoo(goodsInfoId,-xzNum);		
					}else{
						changeNum(id,1);
					}
					$(obj).parent().find(".productNum").val(1);
			} 
			if(num>sumNum){
					if(custId==""){
						var goodsInfoId=$(".pro_id"+id).val();
						xzNum=nums-xzNum;
						changeNumCoo(goodsInfoId,xzNum);
						$(".xzNum").attr("value",Subtr(xzNum));		
					}else{
						changeNum(id,num);
						$(obj).parent().find(".productNum").val(num);
					}
			}else{ 
				if(custId==""){
						var goodsInfoId=$(".pro_id"+id).val();
						xzNum=nums-xzNum;
						changeNumCoo(goodsInfoId,xzNum);		
						$(".xzNum").attr("value",Subtr(xzNum));	
						
				}else{
					changeNum(id,num);
				}
			}
				
		oncheck();
		
		}else{
			$(obj).parent().find(".productNum").val(xzNum);
			return false;
		}
		return true;
	}
	function dodel(id,infoId){
		$('#shoppingCartId').val(id);
		$('#goodsInfoId').val(infoId);
		
		$(".tip_text").html("是否删除!");
		$(".tan_k3").click();
	  	  
	}
	function delc(){
		var id = $('#shoppingCartId').val();
		var infoId=$("#goodsInfoId").val();
		$.post("${basePath}/delshoppingcartbyid/"+id+"-"+infoId, function (data)
		    {
		        if (data==1)
		        {
		        	$('#activity'+id).remove();
		        	oncheck();
		        	$('#goods_tips_del').modal('hide');
		        }
		 });
	}
	<!--未登录修改数量-->
	function changeNumCoo(id,num){
	    $.post("${basePath}/addProductToShopmCar.htm", { productId: id, goodsNum: num },
		   function(data){
		   });
	}
	<!--已登录修改数量-->
	function changeNum(id,num){
		 $.post("${basePath}/changeshoppingmcartbyid/"+id+"-"+num, function (data)
	    { 
	        if (data==1)
	        {
	        }
	    });
	}
	
	function goShop(){
		location.href="${basePath}/main.html";
	}
	
	function onpay(){
		var obj=document.getElementsByName('boxs');
		var preprice=0; 
		var f = false;
		var s=true;
		if(obj!=null&&obj.length!=0){
			for(var i=0; i<obj.length; i++){    
			    if($(obj[i]).attr("c-status")==0){
			    if(opblur($("#goods_num_"+$(obj[i]).attr("cid")),$(obj[i]).attr("cid"),$("#goods_onb_"+$(obj[i]).attr("cid")).val())==false){
			    	s=false;
			    }else{
			    	f=true;
			    }
			    }
			}
		}
		if(s){
		
			if(f){
			
					var vHtm="";   
					for(var i=0; i<obj.length; i++){
						if($(obj[i]).attr("c-status")==0){
							vHtm+="<input type='hidden' value="+$(obj[i]).attr("cid")+" name='box'/>";
						}
					}
					$("#subForm").append(vHtm);
					$("#subForm").submit();
			}else{
				$(".tip_text").html("购物车没有选中商品，是否跳转到首页");
				$(".tan_k2").click();
			}
		}else{
				$(".tip_text").html("数量不正确");
				$(".tan_k").click();
		}
	}
	function changeUrl(){
		location.href="main.html"
	}
	</script>
  </body>
</html>
