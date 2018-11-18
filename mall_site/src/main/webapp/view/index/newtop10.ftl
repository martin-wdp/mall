<#assign basePath=request.contextPath>
<link rel="stylesheet" href="${basePath}/index_ten/css/BreakingNews.css"/>
<link type="text/css" rel="stylesheet" href="${basePath}/index_ten/css/head.css" />
<script src="${basePath}/js/jquery-1.7.2.min.js"></script>
<script src="${basePath}/index_ten/js/angular.min.js"></script>
<script src="${basePath}/index_ten/js/BreakingNews.js"></script>
<script src="${basePath}/js/jsOperation.js"></script>
<script src="${basePath}/index_ten/js/jquery.slides.min.js"></script>
<script src="${basePath}/index_seven/js/jquery.lazyload.min.js"></script>
<script src="${basePath}/index_ten/js/app.js"></script>
<script src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<!--<link type="text/css" rel="stylesheet" href="${basePath}/index_ten/css/style.css" />-->
<input type="hidden" id="basePath" value="${basePath}"/>
<input type="hidden" id="currentProvince" value="${chProvince!''}" />
<div class="section_headerTop">
            <div class="slot slot_headerTop01">
                <ul class="left">
                    <input type="hidden" id="customer_id" value="<#if cust??>${cust.customerId!''}</#if>" />
                    <li><a class="favorite" href="javascript:;" onclick="addToFavorite('${topmap.systembase.bsetName}');"><s></s>加入收藏</a></li>
                    <li><a href="javascript:;"  onclick="shareUrl();">分享给好友</a></li>
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
                        <span><s></s><a href="${basePath}/myshoppingcart.html">购物车</a><strong class="cartNum">0</strong>件</span>
                    </div>
                    <div class="miniCart hide">
                        <div class="mCartBox">
                            <!-- mcBoxTop 居上，滚动时依然居上mcFloat展示 -->
                            <!--<div class="mcBoxTop clearfix">
                                 <div class="mcChk"><input type="checkbox" /></div>
                                 <label for="" class="mcElect">全选</label>
                            </div>-->
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

<div id="ctDia" class="bh-dialog dia0">
    <div class="dia-tit">
        <h4>加入收藏</h4>
        <a class="dia-close" href="javascript:;" onclick="scls(this)"></a>
    </div>
    <div class="dia-cont">
        <p style="text-align: center;line-height:100px;">请使用菜单栏或Ctrl+D进行添加！</p>
    </div>
    <div class="dia-btn"><a href="javascript:;" onclick="scls(this)">确定</a></div>
</div>

<div id="ctDia" class="bh-dialog dia101">
    <div class="dia-tit">
        <h4>分享好友</h4>
        <a class="dia-close" href="javascript:;" onclick="scls(this)"></a>
    </div>
    <div class="dia-cont">
        <div  class="dia_collect_intro tc pt60" style="text-align: center;padding-top: 30px;">
            <span id="content" style="font-size:16px"></span>
        </div>
        <div class="dia-btn" style="padding-top: 30px;">
            <a   id="copy"  data-clipboard-target="content" href="javascript:;">复制</a>
            <#--<a   onclick="scls(this)" href="javascript:;">取消</a>-->
            <br/>
            <span style="color: red; font-size:12px">亲！如果您的好友成功注册为会员，会有积分送给您哦！</span>
        </div><!--/dia_ops-->
    </div><!--/dia_cont-->
</div><!--/dialog-->


<div id="ctDia" class="bh-dialog dia102">
    <div class="dia-tit">
        <h4>登录提示</h4>
        <a class="dia-close" href="javascript:;" onclick="scls(this)"></a>
    </div>
    <div class="dia-cont">
        <p style="text-align: center;line-height:100px;">您未登录！现在确认要登录吗？</p>
    </div>
    <div class="dia-btn">
        <a  onclick="login()" href="javascript:;">确定</a>
        <a href="javascript:;" onclick="scls(this)">取消</a>
    </div>
</div><!--/dialog-->



<div id="ctDia" class="bh-dialog dia103" >
    <div class="dia-tit">
        <h4>操作提示</h4>
        <a class="dia-close" href="javascript:;" onclick="scls(this)"></a>
    </div>
    <div class="dia-cont">
        <p style="text-align: center">复制成功！</p>
    </div>
    <div class="dia-btn"><a href="javascript:;" onclick="clss()">确定</a></div>

</div><!--/dialog-->
<div class="bh-mask"></div>
<script type="text/javascript" src="${basePath}/js/jquery.zclip.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.zclip.js"></script>
<style>
    .tz-bar {background:#d3ebff;height:25px;line-height:25px;overflow:hidden;padding:0 5px;}
    .tz-bar .ct_info {float:left;}
    .tz-bar .del {float:right;}
    .tzOrder {background:#f8f8f8;}
    .ct_price {margin-left:10px;color:#dc0002;font-weight:700;}
    .ct_price b {font-weight:500;font-family:arial, tahoma;margin-right:5px;}
    .ct_price em {font-style:normal;}


</style>
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
                dias(0);

            }
        }
    }
    function dias(n){
        $(".bh-mask").fadeIn();
        $(".dia"+n).fadeIn();
    };
    function clss(){
        $(".bh-dialog").fadeOut();
        $(".bh-mask").fadeOut();
    }
    //分享链接给好友 弹出层
    function shareUrl(){
        var basePath = $('#basePath').val();
        var str = window.location.href;
        var b = str.indexOf('/',str.indexOf('/')+2);
        //		var a = str.indexOf("//")+2;
        var local = str.substring(0,b);
        var customerId = $('#customer_id').val(); //当前登录的用户ID
        if(customerId==null||customerId==""){
            dias(102);
        }else{
            //给会员id加密
            var b = new Base64();
            var result = 'customer_id='+customerId;
            var str = b.encode('"'+result+'"');
            var url = local+basePath+'/register.html?'+str;
            $('#content').html(url);
            dias(101);
        }
        //复制成功弹出复制成功窗口
            $('#copy').zclip({
                path:'${basePath}/js/ZeroClipboard.swf',
                copy:function(){
                    return $("#content").html();
                },
                afterCopy:function(){
                    dias(103);
                }
            });
    }

    //推荐该网站给好友- 登录
    function login(){
        var basePath = $('#basePath').val();
        //获取页面登录的 href
        window.location.href=basePath+'/login.html';

    }

    //对会员ID进行加密
    function Base64() {
        _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
        this.encode = function (input) {
            var output = "";
            var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
            var i = 0;
            input = _utf8_encode(input);
            while (i < input.length) {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);
                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;
                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }
                output = output +
                _keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
                _keyStr.charAt(enc3) + _keyStr.charAt(enc4);
            }
            return output;
        }

        // public method for decoding
        this.decode = function (input) {
            var output = "";
            var chr1, chr2, chr3;
            var enc1, enc2, enc3, enc4;
            var i = 0;
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
            while (i < input.length) {
                enc1 = _keyStr.indexOf(input.charAt(i++));
                enc2 = _keyStr.indexOf(input.charAt(i++));
                enc3 = _keyStr.indexOf(input.charAt(i++));
                enc4 = _keyStr.indexOf(input.charAt(i++));
                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;
                output = output + String.fromCharCode(chr1);
                if (enc3 != 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 != 64) {
                    output = output + String.fromCharCode(chr3);
                }
            }
            output = _utf8_decode(output);
            return output;
        }

        // private method for UTF-8 encoding
        _utf8_encode = function (string) {
            string = string.replace(/\r\n/g, "\n");
            var utftext = "";
            for (var n = 0; n < string.length; n++) {
                var c = string.charCodeAt(n);
                if (c < 128) {
                    utftext += String.fromCharCode(c);
                } else if ((c > 127) && (c < 2048)) {
                    utftext += String.fromCharCode((c >> 6) | 192);
                    utftext += String.fromCharCode((c & 63) | 128);
                } else {
                    utftext += String.fromCharCode((c >> 12) | 224);
                    utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                    utftext += String.fromCharCode((c & 63) | 128);
                }

            }
            return utftext;
        }

        // private method for UTF-8 decoding
        _utf8_decode = function (utftext) {
            var string = "";
            var i = 0;
            var c = c1 = c2 = 0;
            while (i < utftext.length) {
                c = utftext.charCodeAt(i);
                if (c < 128) {
                    string += String.fromCharCode(c);
                    i++;
                } else if ((c > 191) && (c < 224)) {
                    c2 = utftext.charCodeAt(i + 1);
                    string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                    i += 2;
                } else {
                    c2 = utftext.charCodeAt(i + 1);
                    c3 = utftext.charCodeAt(i + 2);
                    string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                    i += 3;
                }
            }
            return string;
        }
    }
</script>
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
                            ' <div class="mcAmount" style="position:relative;left:30px;">'+
                            '<span class="amount">'+cartgoods[i].buNum+'</span>'+
                            '</div>'+
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


    
        