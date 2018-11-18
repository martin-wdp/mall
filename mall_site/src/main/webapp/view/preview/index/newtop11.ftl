<#assign basePath=request.contextPath>
<#--<script src="${basePath}/index_eleven/js/jquery-1.11.1.min.js"></script>-->
<link type="text/css" rel="stylesheet" href="${basePath}/index_eleven/css/header.css" />
<script src="${basePath}/js/jquery-1.7.2.min.js"></script>
<script src="${basePath}/js/jsOperation.js"></script>
<script src="${basePath}/index_eleven/js/jquery.slides.min.js"></script>
<script src="${basePath}/index_eleven/js/jcarousellite_1.0.1.js"></script>
<script src="${basePath}/index_eleven/js/angular.min.js"></script>
<script src="${basePath}/index_eleven/js/template.js"></script>
<script src="${basePath}/index_eleven/js/app.js"></script>
<input type="hidden" id="basePath" value="${basePath}"/>
<input type="hidden" id="currentProvince" value="${chProvince!''}" />
<div class="section_headerTop">
        <div class="slot slot_headerTop01">
            <ul class="left">
                <li><a class="favorite" href="javascript:;" onclick="addToFavorite('${topmap.systembase.bsetName}');"><s></s>加入收藏</a></li>
                <li><span class="welcome">您好<#if cust??>${cust.customerNickname!''}</#if>，欢迎来到${topmap.systembase.bsetName}!</span></li>
                <li class="login_info">
                	<#if cust??>
            		<#else>
                    <a class="site_login" href="${basePath}/login.html">请登录</a>
                    <a class="site_register" href="${basePath}/register.html">免费注册</a>
                    </#if>
                </li>
                <li class="local-choose">
                    <a href="javascript:;"><span class="province_text"></span><i class="iconfont"></i></a>
                    <div class="localInfo">
                        <div class="localPanel">
                        </div>
                    </div>
                </li>
                <li class="loginout_info hide">
                	<#if cust??>
                    <a class="site_loginout" href="${basePath}/logout.html">退出</a>
                    </#if>
                </li>
            </ul>
            <ul class="siteNav">
                <li class="nav_item"><a href="${basePath}/customer/myorder.html">我的订单</a></li>
                <li class="nav_item"><a href="${basePath}/customer/index.html">会员俱乐部</a></li>
                <li class="nav_item navCenter">
                    <a href="${basePath}/customer/myfollw.html">收藏夹<s></s></a>
                    <div class="navInfo">
                        <div class="navPanel">
                            <a href="${basePath}/customer/sellermyfollw.html">收藏的店铺</a>
                            <a href="${basePath}/customer/myfollw.html">收藏的商品</a>
                        </div>
                    </div>
                </li>
                <li class="nav_item navCenter">
                    <a href="${basePath}/help/2">客户服务<s></s></a>
                    <div class="navInfo">
                        <div class="navPanel">
                            <a href="${basePath}/help/2">帮助中心</a>
                            <a href="${basePath}/help/65">售后服务</a>
                            <!--<a href="javascript:;">在线客服</a>-->
                            <a href="${basePath}/customer/ordercomplain.html">投诉中心</a>
                            <!--<a href="javascript:;">客服邮箱</a>-->
                        </div>
                    </div>
                </li>
            </ul>
        </div><!--slot_headerTop01-->
        <div class="slot slot_headerTop02"></div>
    </div><!--section_headerTop-->
    
    <div class="bh-mask"></div>
	<div id="ctDia" class="bh-dialog dia0">
	    <div class="dia-tit">
	        <h4>加入收藏</h4>
	        <a class="dia-close" href="javascript:;" onclick="scls(this)"></a>
	    </div>
	    <div class="dia-cont">
	        <p style="text-align: center">请使用菜单栏或Ctrl+D进行添加！</p>
	    </div>
	    <div class="dia-btn"><a href="javascript:;" onclick="scls(this)">确定</a></div>
	</div>
	
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
	            sdia('ctDia');
	        }   
	    }   
	}	
</script>
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
			}else{				
			$(".emCartBox").show();
				
			}
		}});

};		
</script>   
	