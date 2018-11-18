<#assign basePath=request.contextPath>
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
            if ( bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {
                //跳转到移动版
                <#if ((mobProjectUrl??) && (mobProjectUrl?length>0))>
                	location = "${mobProjectUrl}";
                </#if>
            }
        }

        browserRedirect();
</script>
<#include "newtop15.ftl"/>
<#include "newheader15.html"/>
<style>
.showlist {z-index:9997!important;}
.menuImg .bd-list img {width:84px; height:36px;}
.tz-bar {background:#d3ebff;height:25px;line-height:25px;overflow:hidden;padding:0 5px;}
.tz-bar .ct_info {float:left;}
.tz-bar .del {float:right;}
.tzOrder {background:#f8f8f8;}
.ct_price {margin-left:10px;color:#dc0002;font-weight:700;}
.ct_price b {font-weight:500;font-family:arial, tahoma;margin-right:5px;}
.ct_price em {font-style:normal;}
</style>
<input type="hidden" id="basePath" value="${basePath}"/>
<input type="hidden" id="currentProvince" value="${chProvince!''}" />
<input type="hidden" id="oldsearchtext" value="${(keyWorlds)!''}"/>

<script>
	$(function(){
	$(".cartit").live("mouseover",minicartonload);
	$("#delect_minicart").live("click", function(){
			var emp1 = $(this).next().val();
			var emp2 = $(this).next().next().val();
			$.ajax({ url: "${basePath}/delshoppingcartbyid.htm?shoppingCartId="+emp1+"&goodsInfoId="+emp2,async:false, success: function(dats){
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
                        '<a href="" class="del"  id = "delect_minicart">删除</a>' +
                        "<input type= 'hidden' class= 'goodsid' value = '" + cartgoods[i].shoppingCartId + "'>" +
                        "<input type= 'hidden' class= 'goodsinfo' value = '" + cartgoods[i].goodsInfoId + "'>" +
                        '<span class="mcPrice">&yen;' + cartgoods[i].productPrice + '<em style="color:#666">×' + cartgoods[i].buNum + '</em></span>' +
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

    };
</script>
