<#assign basePath=request.contextPath>
<link rel="stylesheet" href="${basePath}/index_seven/css/style.css"/>
<!--<script src="${basePath}/index_seven/js/jquery-1.11.1.min.js"></script>-->
<script src="${basePath}/js/jquery-1.7.2.min.js"></script>
<script src="${basePath}/index_seven/js/jquery.slides.min.js"></script>
<script src="${basePath}/index_seven/js/jcarousellite_1.0.1.js"></script>
<script src="${basePath}/index_seven/js/angular.min.js"></script>
<script src="${basePath}/index_seven/js/template.js"></script>
<script src="${basePath}/js/jsOperation.js"></script>
<script src="${basePath}/index_seven/js/jquery.lazyload.min.js"></script>
<script src="${basePath}/index_seven/js/app.js"></script>
<#include "newtop7.ftl"/>
<#include "newheader7_ftl.ftl"/>
<input type="hidden" id="basePath" value="${basePath}"/>
<input type="hidden" id="oldsearchtext" value="${(map.searchBean.title)!''}"/>
<style>
	.mainnav .showlist .menuView ul li h3 {width:90px;}
	.mainnav .showlist .menuView ul li p {width:610px;}
</style>
<style>
	.showlist {z-index:9997!important;}
	.mcOrder .mcCost .del {white-space:nowrap;}
	.mcGo a {color:#fff!important; text-decoration:none!important;}
	.section-header {background:none!important;}
	.menuImg .bd-list img {width:84px; height:36px;}
</style>
<script>
	$(function(){
	$(".cartit").live("mouseover",minicartonload);
	$("#delect_minicart").live("click", function(){
			var emp1 = $(this).next().val();
			var emp2 = $(this).next().next().val();
			$.ajax({ url: "${basePath}/delshoppingcartbyid.htm?shoppingCartId="+emp1+"&goodsInfoId="+emp2, success: function(dats){
				if(dats == 1){
					minicartonload();	
				}
			}});
		});
		//预加载mini购物车
		minicartonload();
	
});
	

function minicartonload(){
		$.ajaxSetup({ cache: false });
		$.ajax({ url: "${basePath}/minisscart.htm",  async:false ,success: function(datee){
	  		var cartgoods = datee.shopcart.miniGoodsList;
	  		//alert("cartgoods"+cartgoods);	  		
	  		var cust= datee.cust; 
	  		//alert("cust"+cust); 
	  		if(cust!=null){
	  			$(".cart_empty").html("<p style='height:35px;'>您的购物车是空的<br /></p>");
	  		}
	  		var empvalue = 0;
			//设置我的购物车显示购物车商品数量
	  		if(cartgoods != null){
				$(".sc_num em").html(cartgoods.length);
			}else{
				$(".sc_num em").html("0");
			}
			//购物车中有商品
			if(cartgoods != null && cartgoods.length>0){
				$(".cart_empty").addClass("none");
				$(".cart_cont").removeClass("none");
				//mini购物车头部
				//循环输出购物车中的商品
				$(".mcBoxList").html("");
				for(var i = 0 ; i < cartgoods.length ; i++){
					var cartgood=' <div class="mcOrder clearfix">'+
                               
                               ' <div class="mcItem">'+
                               	'<a  href="${basePath}/item/'+cartgoods[i].goodsInfoId+'" class="img">'+
                                        '<div class="table-cell">'+
                                         '   <i></i><img src="'+cartgoods[i].productPic+'" alt="">'+
                                      '  </div>'+
                                   ' </a>'+
                                '</div>'+
                                '<div class="mcSqe">'+
                                    '<p><a  href="${basePath}/item/'+cartgoods[i].goodsInfoId+'">'+cartgoods[i].productName+'</a></p>'+
                                '</div>'+
                               ' <div class="mcAmount" style="position:relative;left:30px;">'+
                                    //'<span class="minus"></span>'+
                                    '<span class="amount">'+cartgoods[i].buNum+'</span>'+
                                    //'<span class="plus"></span>'+
                                '</div>'+
                                '<div class="mcCost" style="width:90px;">'+
                                    '<a href="" class="del"  id = "delect_minicart">删除</a>'+
                                    	"<input type= 'hidden' class= 'goodsid' value = '"+cartgoods[i].shoppingCartId+"'>"+
                                    "<input type= 'hidden' class= 'goodsinfo' value = '"+cartgoods[i].goodsInfoId+"'>"+
                                    '<span class="mcPrice">'+accMul(cartgoods[i].productPrice,cartgoods[i].buNum)+'</span>'+
                               ' </div>'+
                            '</div>';
				
				
					$(".mcBoxList").append(cartgood);
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
					//计算总价格
					empvalue =accAdd(empvalue,accMul(cartgoods[i].productPrice,cartgoods[i].buNum));
				}
				//设置mini购物车底部
				$(".mcNumTotal").text(cartgoods.length);
				$(".mcNumChecked").text(cartgoods.length);
				$(".cartNum").text(cartgoods.length);				
				$(".mcTotalFee").text(empvalue);
					$(".emCartBox").hide();
			}else{
				$(".emCartBox").show();
				
			}
			
		}});

};	
	
	
</script>