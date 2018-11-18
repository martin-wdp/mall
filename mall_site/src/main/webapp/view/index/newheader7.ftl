<#assign basePath=request.contextPath>
<link rel="stylesheet" href="${basePath}/index_seven/css/style.css"/>
<script src="${basePath}/index_seven/js/jquery.slides.min.js"></script>
<script src="${basePath}/index_seven/js/jcarousellite_1.0.1.js"></script>
<script src="${basePath}/index_seven/js/angular.min.js"></script>
<script src="${basePath}/index_seven/js/template.js"></script>
<script src="${basePath}/js/jsOperation.js"></script>
<script src="${basePath}/index_seven/js/jquery.lazyload.min.js"></script>
<script src="${basePath}/index_seven/js/app.js"></script>
<script>
    function browserRedirect() {
       var url = window.location.href;
       if(url.indexOf("weixin")>-1){
       		url = url.replace(/item/,"mobile/getwxcode3.htm?toUrl=item");
       		if(url.lastIndexOf(".html")==-1){
       			url = url.replace(/#/,".html#");
       		}
       		url = url.substring(0,url.indexOf("#"));
       		location = url;
       }
        var sUserAgent = navigator.userAgent.toLowerCase();
        var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
        var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
        var bIsMidp = sUserAgent.match(/midp/i) == "midp";
        var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
        var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
        var bIsAndroid = sUserAgent.match(/android/i) == "android";
        var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
        var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
        if ( bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM || bIsIpad) {
            //跳转到移动版
            <#if ((mobProjectUrl??) && (mobProjectUrl?length>0))>
                location = "${mobProjectUrl}";
            </#if>
        }
    }
    //2015-12-10 暂时屏蔽通过PC端地址访问移动端
    browserRedirect();
</script>

<#include "newtop7.ftl"/>
<#include "newselectcarstyle.ftl">
<#include "newheader7.html"/>
<input type="hidden" id="basePath" value="${basePath}"/>
<input type="hidden" id="oldsearchtext" value="${(keyWorlds)!''}"/>

<style>
	.mainnav .showlist .menuView ul li h3 {width:90px;}
	.mainnav .showlist .menuView ul li p {width:610px;}
	.dropdown-menu li > a {margin-right:10px;}
    .tz-bar {background:#d3ebff;height:25px;line-height:25px;overflow:hidden;padding:0 5px;}
    .tz-bar .ct_info {float:left;}
    .tz-bar .del {float:right;}
    .tzOrder {background:#f8f8f8;}
    .ct_price {margin-left:10px;color:#dc0002;font-weight:700;}
    .ct_price b {font-weight:500;font-family:arial, tahoma;margin-right:5px;}
    .ct_price em {font-style:normal;}
</style>
<style>
	.showlist {z-index:9997!important;}
	.mcOrder .mcCost .del {white-space:nowrap;}
	.mcGo a {color:#fff!important; text-decoration:none!important;}
	.menuImg .bd-list img {width:84px; height:36px;}
    /*suixiangjun1212调兼容*/
    .cartfd{background-color:rgb(229, 72, 67);}
    .miniCart{position: absolute;top:39px; right:-1px;}
    .search .inputSearch{ line-height:34px\9\0;/*ie9*/ line-height:34px\0;/*ie8*/ *line-height:34px;/*ie7*/ }
</style>
<script>
	$(function(){
	    $(".cartit").on("mouseover",minicartonload);
	    $("#delect_minicart").on("click", function(){
            var emp1 = $(this).next().val();
            var emp2 = $(this).next().next().val();
            $(this).parents(".mcOrder").remove();
            $(".cartNum").val(parseInt($(".cartNum").val()) - 1);
            $.ajax({ url: "${basePath}/delshoppingcartbyid.htm?shoppingCartId="+emp1+"&goodsInfoId="+emp2,
                async:false,
                success: function(dats){
                if(dats == 1){
                    minicartonload();
                }
            }});
        });
        //预加载mini购物车
        minicartonload();
        /*20160120 suixiangjun 左侧第一资讯换文字*/
        //$(".show-box .content .new-side .news .news-list").find("li span").eq(0).text("【春节发货】");
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
            //比如一件商品买了3件,详细统计购买商品数量
            var countgoods=0;
			//购物车中有商品
			if(cartgoods != null && cartgoods.length>0){
				$(".cart_empty").addClass("none");
				$(".cart_cont").removeClass("none");
				//mini购物车头部
				//循环输出购物车中的商品
				$(".mcBoxList").html("");
				for(var i = 0 ; i < cartgoods.length ; i++) {
                    countgoods += cartgoods[i].buNum;
                    var cartgood = "";
                    if (cartgoods[i].fitId == null) {
                        cartgood = ' <div class="mcOrder clearfix">' +

                        ' <div class="mcItem">' +
                        '<a  href="${basePath}/item/' + cartgoods[i].goodsInfoId + '" class="img">' +
                        '<div class="table-cell">' +
                        '   <i></i><img src="' + cartgoods[i].productPic + '" alt="">' +
                        '  </div>' +
                        ' </a>' +
                        '</div>' +
                        '<div class="mcSqe">' +
                        '<p><a  href="${basePath}/item/' + cartgoods[i].goodsInfoId + '">' + cartgoods[i].productName + '</a></p>' +
                        '</div>' +
                        ' <div class="mcAmount" style="position:relative;left:30px;">' +
                            //'<span class="minus"></span>'+
                        '<span class="amount">' + cartgoods[i].buNum + '</span>' +
                            //'<span class="plus"></span>'+
                        '</div>' +
                        '<div class="mcCost">' +
                        '<a class="del"  id = "delect_minicart">删除</a>' +
                        "<input type= 'hidden' class= 'goodsid' value = '" + cartgoods[i].shoppingCartId + "'>" +
                        "<input type= 'hidden' class= 'goodsinfo' value = '" + cartgoods[i].goodsInfoId + "'>" +
                        '<span class="mcPrice">&yen;' +
                            <#if vip?? && vip == "1">cartgoods[i].productVipPrice<#else>cartgoods[i].productPrice</#if>
                         + '<em style="color:#666">×' + cartgoods[i].buNum + '</em></span>' +
                        ' </div>' +
                        '</div>';
                        $(".mcBoxList").append(cartgood);
                    } else {
                        var cartgood = "<div class='tz-bar clearfix'>" +

                                "<div class='ct_info'>[套装]" +
                                cartgoods[i].miniFit.fitName +
                                "<span class='ct_price ml10'><b>优惠：</b>&yen;"+cartgoods[i].miniFit.fitPrice+"<em style='color:#666'>×" + cartgoods[i].buNum + "</em></span>" +
                                "</div>" +
                                "<div class='cout_text pa'></div>" +

                                '<a href="" class="del"  id = "delect_minicart">删除</a>' +
                               "<input type= 'hidden' class= 'goodsid' value = '" + cartgoods[i].shoppingCartId + "'>" +
                               "<input type= 'hidden' class= 'goodsinfo' value = '" + cartgoods[i].goodsInfoId + "'>" +
                                "</div>";
                        $(".mcBoxList").append(cartgood);

                        var goodsList = cartgoods[i].miniFit.miniGoods;
                            //套装中货品的销售价格
                            var suitPrice =0;
                        for (var j = 0; j < goodsList.length; j++) {
                            cartgood = ' <div class="mcOrder tzOrder clearfix">' +

                            ' <div class="mcItem">' +
                            '<a  href="${basePath}/item/' + goodsList[j].goodsInfoId + '" class="img">' +
                            '<div class="table-cell">' +
                            '   <i></i><img src="' + goodsList[j].productPic + '" alt="">' +
                            '  </div>' +
                            ' </a>' +
                            '</div>' +
                            '<div class="mcSqe">' +
                            '<p><a  href="${basePath}/item/' + goodsList[j].goodsInfoId + '">' + goodsList[j].productName + '</a></p>' +
                            '</div>' +
                            '<div class="mcCost">' +
                            '<span class="mcPrice">&yen;' +goodsList[j].productPrice  +'<em style="color:#666">×' + cartgoods[i].buNum + '</em></span>' +
                            ' </div>' +
                            '</div>';
                            suitPrice=suitPrice+goodsList[j].productPrice;
                            $(".mcBoxList").append(cartgood);

                        }
                    }

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
                    if(cartgoods[i].fitId==null){

                        empvalue =accAdd(empvalue,accMul(cartgoods[i].productPrice,cartgoods[i].buNum));
                    }else{

                     empvalue=accAdd(empvalue,accMul(suitPrice-cartgoods[i].miniFit.fitPrice,cartgoods[i].buNum));
                    }



				}
				//设置mini购物车底部

				$(".mcNumTotal").text(countgoods);
				$(".mcNumChecked").text(countgoods);
				$(".cartNum").text(countgoods);
				$(".mcTotalFee").text(empvalue);
					$(".emCartBox").hide();
			}else{
				$(".emCartBox").show();

			}

		}});

}


</script>

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