<#assign basePath=request.contextPath>
<link rel="stylesheet" type="text/css" href="${basePath}/index_four/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/index_four/css/style.css.map" />

<input type="hidden" id="basePath" value="${basePath}"/>

<div class="section_headerTop">
    <div class="slot slot_headerTop01">
        <ul class="left">
            <li><a class="favorite" href="javascript:;" onclick="addToFavorite('${topmap.systembase.bsetName}');"><s></s>加入收藏</a></li>
            <li><span class="welcome">您好<#if cust??>${cust.customerNickname!''}</#if>，欢迎来到${topmap.systembase.bsetName}!</span></li>
            <li class="login_info">
                <a class="site_login" href="${basePath}/login.html">请登录</a>
                <a class="site_register" href="${basePath}/register.html">免费注册</a>
            </li>
            <li class="loginout_info hide">
                <a class="site_loginout" href="${basePath}/logout.html">退出</a>
            </li>
        </ul>
        <ul class="siteNav">
            <li class="nav_item"><a href="${basePath}/customer/myorder.html">我的订单</a></li>
            <li class="nav_item"><a href="">会员俱乐部</a></li>
            <li class="nav_item navCenter">
                <a href="javascript:;">收藏夹<s></s></a>
                <div class="navInfo">
                    <div class="navPanel">
                        <a href="javascript:;">收藏的店铺</a>
                        <a href="javascript:;">收藏的商品</a>
                    </div>
                </div>
            </li>
            <li class="nav_item navCenter">
                <a href="javascript:;">客户服务<s></s></a>
                <div class="navInfo">
                    <div class="navPanel">
                        <a href="${basePath}/help/2">帮助中心</a>
                        <a href="javascript:;">售后服务</a>
                        <a href="javascript:;">在线客服</a>
                        <a href="javascript:;">投诉中心</a>
                        <a href="javascript:;">客服邮箱</a>
                    </div>
                </div>
            </li>
        </ul>
    </div>
    <div class="slot slot_headerTop02"></div>
</div> 
<div class="bh-mask"></div>
    <div id="ctDia" class="bh-dialog">
        <div class="dia-tit">
            <h4>加入收藏</h4>
            <a class="dia-close" href="javascript:;" onclick="cls(this)"></a>
        </div>
        <div class="dia-cont">
            <p>加入收藏失败，请使用菜单栏或Ctrl+D进行添加！</p>
        </div>
        <div class="dia-btn"><a href="javascript:;" onclick="cls(this)">确定</a></div>
    </div>
    <script src="${basePath}/index_four/js/jquery-1.7.2.min.js"></script>
	<script src="${basePath}/index_four/js/jquery.lazyload.min.js"></script>
    <script src="${basePath}/index_four/js/angular.min.js"></script>
    <script src="${basePath}/index_four/js/jquery.slides.min.js"></script>
    <script src="${basePath}/index_four/js/jquery.jcarousellite.min.js"></script>
    <script src="${basePath}/js/jsOperation.js"></script>
    <script src="${basePath}/index_four/js/app.js"></script>
<script>
//加入收藏
	function addToFavorite(siteName){
		try {   
			window.external.AddFavorite($("#basePath").val(),siteName);
	    } catch (e) {   
	        try {   
	            window.sidebar.addPanel($("#basePath").val(), siteName, "");   
	        } catch (e) {   
	            $(".collect_tip_cancel").click(function(){
	            	cls();
	            });
	            dia('ctDia');
	            
	        }   
	    }   
	}
	$(function(){
	$(".cartit").live("mouseover",minicartonload);
	$("#delect_minicart").live("click", function(){
			var emp1 = $(this).next().val();
			var emp2 = $(this).next().next().val();
			$.ajax({ url: "../delshoppingcartbyid.htm?shoppingCartId="+emp1+"&goodsInfoId="+emp2, success: function(dats){
				if(dats == 1){
					minicartonload();	
				}
			}});
		});
		//预加载mini购物车
		minicartonload();
	
});
	

function minicartonload(){;
		$.ajaxSetup({ cache: false });
		$.ajax({ url: "../minisscart.htm",  async:false ,success: function(datee){
	  		var cartgoods = datee.shopcart.miniGoodsList;
	  		var cust= datee.cust;  
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
				$(".cart_list").html("");

				for(var i = 0 ; i < cartgoods.length ; i++){
					var cartgood=' <div class="mcOrder clearfix">'+
                               
                               ' <div class="mcItem">'+
                               	'<a  href="../item/'+cartgoods[i].goodsInfoId+'" class="img">'+
                                        '<div class="table-cell">'+
                                         '   <i></i><img src="'+cartgoods[i].productPic+'" alt="">'+
                                      '  </div>'+
                                   ' </a>'+
                                '</div>'+
                                '<div class="mcSqe">'+
                                    '<p><a  href="../item/'+cartgoods[i].goodsInfoId+'">'+cartgoods[i].productName+'</a></p>'+
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
				
				
					$(".cart_list").append(cartgood);
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
    
    