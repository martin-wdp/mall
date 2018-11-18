<#assign basePath=request.contextPath>
<link rel="stylesheet" href="${basePath}/index_ten/css/BreakingNews.css"/>
<link type="text/css" rel="stylesheet" href="${basePath}/index_ten/css/head.css" />
<script src="${basePath}/js/jquery-1.7.2.min.js"></script>
<script src="${basePath}/index_ten/js/angular.min.js"></script>
<script src="${basePath}/index_ten/js/BreakingNews.js"></script>
<script src="${basePath}/js/jsOperation.js"></script>
<script src="${basePath}/index_ten/js/jquery.slides.min.js"></script>
<script src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script src="${basePath}/index_seven/js/jquery.lazyload.min.js"></script>
<script src="${basePath}/index_ten/js/app.js"></script>
<!--<link type="text/css" rel="stylesheet" href="${basePath}/index_ten/css/style.css" />-->
<input type="hidden" id="basePath" value="${basePath}"/>
<input type="hidden" id="currentProvince" value="${chProvince!''}" />
<div class="section_headerTop">
            <div class="slot slot_headerTop01">
                <ul class="left">
                    <li><a class="favorite" href="javascript:;" onclick="addToFavorite('${topmap.systembase.bsetName}');"><s></s>加入收藏</a></li>
                    <li class="login_info">
                    	<#if cust??>
            			<#else>
                        <a class="site_login site_register" href="${basePath}/login.html">请登录</a>
                        <a class="site_register" href="${basePath}/register.html">免费注册</a>
                        </#if>
                    </li>
                    <li>
                    <a href="${basePath}/customer/index.html" class="site_register">会员中心</a>                  
                    </li>
                    <li class="local-choose">
                        <a href="javascript:;"><span class="province_text"></span><i class="iconfont">&#xe607;</i></a>
                        <div class="localInfo">
                            <div class="localPanel">
                            </div>
                        </div>
                    </li>
                    <#if cust??>
                    <li class="loginout_info">                    
                        <a class="site_loginout" href="${basePath}/logout.html">退出</a>
                    </li>
                    </#if>
                </ul>
                <div class="cartfd">
                    <s class="cartBanner"></s>
                    <div class="cartit">
                        <span><s></s>购物车<strong class="cartNum">0</strong>件</span>
                    </div>
                    <div class="miniCart hide">
                        <div class="mCartBox">
                            <!-- mcBoxTop 居上，滚动时依然居上mcFloat展示 -->
                            <div class="mcBoxTop clearfix">
                                <!--<div class="mcChk"><input type="checkbox" /></div>
                                <label for="" class="mcElect">全选</label>-->
                            </div>
                            <div class="mcBoxList">
                                
                            </div>
                            <div class="emCartBox hide">
                                <span>购物车中还没有商品，再去逛逛吧！</span>
                            </div>
                        </div>
                        <div class="mcGenius bmcGenius"></div><!-- 展开加class"bmcGenius" -->
                        <div class="mCartError"><!-- 显示错误时bottom:41px -->
                            <p>正在为您加载数量！</p>
                        </div>
                        <div class="mCartHandler clearfix">
                            <span class="mcCashier">
                                <span class="mcTotal">
                                    <span class="mcRmb">¥</span>
                                    <span class="mcTotalFee">0.00</span>
                                </span>
                                <span class="mcGo"><a href="${basePath}/myshoppingcart.html">结算</a></span><!-- no-mcGo置灰状态 -->
                            </span>
                            <h3><!-- "mc_e1与mcNumTotal与展开时的emCartBox"/"mc_e2与mcNumChecked与mcCashier"同时显示 -->
                                <span class="mc_e1">购物车</span>
                                <span class="mc_e2">共</span>
                                <strong class="mcNumTotal">0</strong>
                                <strong class="mcNumChecked">0</strong>
                                <span class="mc_e2">件</span>
                            </h3>
                        </div>
                    </div>
                </div>

            </div>
            <div id="logo_n"><a href="${topmap.systembase.bsetAddress}"><img src="${topmap.systembase.bsetLogo}"/></a></div>
            <div class="slot slot_headerTop02"></div>
        </div>

<div class="bh-mask"></div>
    <div id="ctDia" class="bh-dialog">
        <div class="dia-tit">
            <h4>加入收藏</h4>
            <a class="dia-close" href="javascript:;" onclick="scls(this)"></a>
        </div>
        <div class="dia-cont">
            <p>请使用菜单栏或Ctrl+D进行添加！</p>
        </div>
        <div class="dia-btn"><a href="javascript:;" onclick="scls(this)">确定</a></div>
    </div> 
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
	  		var cust= datee.cust; 	  		
	  		var empvalue = 0;
			//购物车中有商品
			if(cartgoods != null && cartgoods.length>0){
				$(".cart_empty").addClass("none");
				$(".cart_cont").removeClass("none");
				//mini购物车头部
				//循环输出购物车中的商品
				$(".mcBoxList").html("");
				for(var i = 0 ; i < cartgoods.length ; i++){
					var cartgood=' <div class="mcOrder clearfix">'+
                                 // '<div class="mcChk">'+
                                  //'<input type="checkbox" />'+
                                  //'</div>'+
                               ' <div class="mcItem">'+
                               	'<a  href="${basePath}/item/'+cartgoods[i].goodsInfoId+'" class="img">'+
                                        '<div class="table-cell">'+
                                         '   <i></i><img src="'+cartgoods[i].productPic+'" alt="" height="60px" width="60px">'+
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
                                '<div class="mcCost">'+
                                    '<a href="" class="del"  id = "delect_minicart">删除</a>'+
                                    	"<input type= 'hidden' class= 'goodsid' value = '"+cartgoods[i].shoppingCartId+"'>"+
                                    "<input type= 'hidden' class= 'goodsinfo' value = '"+cartgoods[i].goodsInfoId+"'>"+
                                    '<span class="mcPrice">&yen;'+accMul(cartgoods[i].productPrice,cartgoods[i].buNum)+'</span>'+
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
			}else{				$(".emCartBox").show();
				
			}
		}});

};		
</script>   


    
        