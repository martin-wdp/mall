
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<#assign basePath=request.contextPath>
<head>
<meta property="qc:admins" content="274167644576616701163757" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/old_top_style.css" />
<style>
#nocart{
	src="${basePath}/images/no_cart_img.jpg";
}
.w100b{
	width=100%;
}
.c999{
	color: #999;
}
.searchli a:hover {
	text-decoration: none;
	background-color: #F63;	
}
</style>
<script src="http://l.tbcdn.cn/apps/top/x/sdk.js?appkey=21790336"></script>
<script type="text/javascript">
	/*//加入收藏
	function addToFavorite(){
		try {   
			window.external.AddFavorite('${basePath}/npstore_H_web/',"福气商城");
	    } catch (e) {   
	        try {   
	            window.sidebar.addPanel('${basePath}/npstore_H_web/', "福气商城", "");   
	        } catch (e) {   
	            alert("加入收藏失败，请使用菜单栏或Ctrl+D进行添加");   
	        }   
	    }   
	}*/
	
	var empbo = 0;
	window.onload = function(){
		$("#mminicart").live("mouseover",minicartonload);
		$("#delect_minicart").live("click", function(){
			var emp1 = $(this).next().val();
			var emp2 = $(this).next().next().val();
		
			$.ajax({ url: "${basePath}/delshoppingcartbyid.htm?shoppingCartId="+emp1+"&goodsInfoId="+emp2, async:false,success: function(dats){
				if(dats == 1){
					//removenimicart();	
					minicartonload();	
				}
			}});
		});
		
		$(".shopping_cart").live("mouseleave",removenimicart);
		
		
		<!-- 设置搜索参数 -->
		if($("#search_title")){
			$(".search_text").val($("#search_title").val());
		}
		<!-- end -->
		$.ajax({ url: "${basePath}/tops_1.html", async:false ,success: function(date){
	        var logo = date.systembase;
	        $("title").html(logo.bsetName);
	        $("#logo").append("<a class='logo fl' href='"+logo.bsetAddress+"'><img alt=''src='"+logo.bsetLogo+"' /></a>");
	     	<!-- 获取导航数据 -->
	     	var bar = date.navList;
	     	if(bar != null){
		     	for(var z = 0 ; z < bar.length; z++){
		     		if(bar[z].barName=="首页" || bar[z].barUrl=="index.html"){
						$("#bar").append("<li><a href='${basePath}/"+bar[z].barUrl+"'>"+bar[z].barName+"</a></li>");
		     		}else{
						$("#bar").append("<li><a href='${basePath}/barchannelview/"+bar[z].barId+"'>"+bar[z].barName+"</a></li>");
		     		}
		     	}
		    }
		    <!-- 获取分类导航数据 -->
	     	var s = date.classifyBar.classifyBarList;
			if(s != null){

				for(var i = 0 ; i < s.length ; i++){
					var cate = "<div class='sort_item' id = 'cateid"+i+"'>";
						//+"<a class='s_item' href='${basePath}/list/"+s[i].goodsCatId+"-"+s[i].goodsCatId+".html'>"+s[i].name+"</a><div class='lv2_item'></div>"											
					<#--
					if(s[i].imgSrc!=null){
						cate = cate +"<img src='"+s[i].imgSrc+"' width='30px' height='30px'/>";
					}
					-->
					cate = cate +"<a class='s_item' href='${basePath}/channelview/"+s[i].goodsCatId+"'>"+s[i].name+"</a><div class='lv2_item'></div></div>";
					
					$("#catelist").append(cate);
					<#--
					新页面一行显示多个分类
					var cates = s[i].name.split("、");
					var urls = s[i].url.split("、");
					if(cates.length>1){
						for(var n=0;n<cates.length;n++){
							$("#cateid"+i).append("<a class='s_item' href='${basePath}/"+urls[n]+"'>"+cates[n]+"</a><div class='lv2_item'></div>");
						}
					}else{
						$("#cateid"+i).append("<a class='s_item' href='${basePath}/channelview/"+s[i].goodsCatId+"'>"+s[i].name+"</a><div class='lv2_item'></div>");
					}
					-->
					<!-- 获取分类导航子分类数据 -->
					var e = s[i].childs;
					if(e != null){
						$("#cateid"+i+" .lv2_item").append("<div class='lv2_band fr'></div>");
						<!-- 获取分类导航广告 -->
						var cateAdvertList = s[i].indexAdvertList;
						if(cateAdvertList.length>0){
							for(var j=0;j<cateAdvertList.length && j<2;j++){
								$("#cateid"+i+" .lv2_band").append("<div>"
															+"<a href='${basePath}/"+cateAdvertList[j].adverHref+"'>"
																+"<img alt='' src='"+cateAdvertList[j].adverPath+"'/>"
															+"</a>"
													+"</div>");
							}
						}
						<!-- 获取分类导航品牌 -->
						var cateBrandList = s[i].indexBrandList;
						if(cateBrandList.length>0){
							$("#cateid"+i+" .lv2_band").append("<div><h4>品牌推荐</h4><ul class='brandId'></ul></div>");
							for(var j=0;j<cateBrandList.length && j<4;j++){
								$("#cateid"+i+" .lv2_band .brandId").append("<li><a href='#'>"
								+"<img alt='' src='"+cateBrandList[j].logoSrc+"'/></a></li>");
							}
						}
						<!-- 获取二级分类 -->
						for(var j = 0; j < e.length; j ++){
							
							$("#cateid"+i+" .lv2_item").append("<div class='lv2_cate fl' id = 'catelv2"+i+"t"+j+"'>"
													+"<dl>"
														+"<dt>"
															+"<a href='${basePath}/list/"+e[j].goodsCatId+"-"+s[i].goodsCatId+".html'>"+e[j].name+"</a>"
														+"</dt><dd></dd>"
													+"</dl>"
												+"</div>");
							var x = e[j].childs;
					
							if(x != null){

								for (var k = 0 ; k < x.length; k++){
								
									$("#catelv2"+i+"t"+j+" dl dd").append("<a href='${basePath}/list/"+x[k].goodsCatId+"-"+s[i].goodsCatId+".html'>"+x[k].name+"</a><span>|");
								}
							}
						}
						
						
					}
					
				}	
			}
			<!-- 获取分类导航框广告 -->
			var boxAdvertList = date.classifyBar.indexAdvertList;
			if(boxAdvertList != null){
				for(var i=0;i<boxAdvertList.length;i++){
					$("#catelist").append("<div class='sort_gg tc' id = 'boxAdvert"+i+"'>"
										 	+"<a href='${basePath}/"+boxAdvertList[i].adverHref+"'><img alt='' src='"+boxAdvertList[i].adverPath+"'></a>"											
										 +"</div>");
				}
			}
			
			
			
			<!-- 获取热门搜索 -->
			var hotsearch = date.hotsearch;
			
			<!-- 获取乱序热门搜索 -->
			var num = Math.ceil(Math.random()*(hotsearch.length));
			var defkeyword = ${(searchBean.title)!'""'};
			if($("#dirvalue").val()=="" || $("#dirvalue").val()=="家电疯抢4折起"){
				if(num>=0 && num<hotsearch.length){
					defkeyword=hotsearch[num].keyword;
					$("#dirvalue").val(defkeyword);
				}else{
					$("#dirvalue").val("家电疯抢4折起");
				}
			}
			loadSearch(hotsearch);
			<#--
			$.ajax({ url: "${basePath}/getSearchCookieTop10.htm", async:false ,success: function(date){
				if(date.length>0){
					for(var i=0;i<10 && i<date.length;i++){
						//var hots = "<li class='searchli' onmouseover='onmouseoverSearch("+i+",\""+date[i]+"\")' onblur='onmouseoutSearch("+i+")' style='clear: both;'><a href='javascript:;'><p >"+date[i]+"<span id='"+i+"' style='float: right;'>搜索历史</span></p></a></li>";
						var hots = "<li class='searchli' style='clear: both;'><a class='a1' href='javascript:;' onclick='clicka1()'><p style='float: left;'>"+date[i]+"</p></a><a href='javascript:;' onclick='delsearch(\""+date[i]+"\")' style='float: right;'>删除</a></li>";
						$(".schList").append(hots);
					}
				}else{
					for(var i=0;i<10 && i<hotsearch.length;i++){
						//var hots = "<li class='searchli' style='clear: both;'><a href='javascript:;' ><p style='float: left;'>"+hotsearch[i].keyword+"</p><span style='float: right;'>热门搜索</span></a></li>";
						var hots = "<li class='searchli' style='clear: both;'><a href='javascript:;' ><p style='float: left;'>"+hotsearch[i].keyword+"</p></a></li>";
						$(".schList").append(hots);
					}
				}
				sch();
			}});
			-->
			
			if(hotsearch != null && hotsearch.length>0){
			
				for(var i=0;i<10 && i<hotsearch.length;i++){
					var hots = "<a href='javascript:;' onclick='changeSearchKey(this);'>"+hotsearch[i].keyword+"</a>";
					$("#hotsearch").append(hots);
				}
				
			}
	     }});
	     
	     minicartonload();
	  };
	  
	  function minicartonload(){
	 
			removenimicart();
	  		$.ajax({ url: "${basePath}/minicart.htm",  async:false ,success: function(datee){
	  		
		  		var cartgoods = datee.shopcart.miniGoodsList;
				var empvalue = 0;
                //比如一件商品买了3件,详细统计购买商品数量
                var countgoods=0;
				if(cartgoods != null){
					$(".sc_num").html(cartgoods.length);
				}else{
					$(".sc_num").html("0");
				}
				if(cartgoods != null && cartgoods.length>0){
                    countgoods+=cartgoods[i].buNum;
					$("#cartdd").html("");
					$("#cartdd").append("<h4 class='fb'>最新加入的商品</h4><div class='sc_box' id ='cartmini'></div><div class='sc_info tr' id ='cartinfo'></div>");
					for(var i = 0 ; i < cartgoods.length ; i++){
						$("#cartmini").append("<ul class='sc_list'>"+
													"<li class='clearfix'>"+
														"<a class='sc_img fl' href='${basePath}/item/"+cartgoods[i].goodsInfoId+".html'><img alt='' src='"+cartgoods[i].productPic+"' width='52px' height='52px'/></a> "+
														"<a class='sc_name fl ml10' href='${basePath}/item/"+cartgoods[i].goodsInfoId+".html'>"+cartgoods[i].productName+"</a>"+
														"<div class='sc_op fr tr'>"+
															"<p class='sc_price mb10'>"+
																"<em>&yen;"+cartgoods[i].productPrice+"</em>×"+cartgoods[i].buNum+
															"</p>"+
															"<a class='sc_delete'  id = 'delect_minicart' style='cursor:pointer;'>删除</a>"+
															"<input type= 'hidden' class= 'goodsid' value = '"+cartgoods[i].shoppingCartId+"'>"+
															"<input type= 'hidden' class= 'goodsinfo' value = '"+cartgoods[i].goodsInfoId+"'>"+
														"</div>"+
													"</li>"+
													"<div class='taozhuang'>"+
														"<div class='fr'></div>"+
														"<div class='fl' id = 'youhui"+i+"'>"+													
														"</div>"+
													"</div>"+
											 "</ul>");
							if(cartgoods[i].marketing != null){
								$("#youhui"+i).append("<span>"+cartgoods[i].marketing.codexName+"</span>")						
							}
							empvalue =accAdd(empvalue,accMul(cartgoods[i].productPrice,cartgoods[i].buNum));
						}
					
						$("#cartinfo").append("<p> 共<span>"+countgoods+"</span>件商品&nbsp;&nbsp;<span></span>&nbsp;&nbsp;&nbsp;共计<em>&yen;"+empvalue+"</em></p><a class='sc_btn mt5' href='${basePath}/myshoppingcart.html'>去购物车结算</a>");
						
					}
				}
			});
		
	 }
	  
	  
	  function removenimicart(){
	  	 empbo = 0;
	  	 var basePath = $("#basePath").val();
	  	 $("#cartdd").html("");
		 $("#cartdd").append("<img id='nocart' src='"+basePath+"/images/no_cart_img.jpg'/>");
	 	 //$("#cartmini").html('');
	 	 //$("#cartinfo").html('');
	  }
	  
	  function dirc(){
	  		var emp = $("#dirvalue").val();
	     	alert(emp);
	     	window.location.href = "goods/searchProduct.html?title="+emp;
	  }
	  <!-- 点击热门搜索的时候 -->
	  function changeSearchKey(obj){
	  		$(".search_text").val($(obj).html());
	  		$("#topSearchForm").submit();
	  }
	  
	
	function loadSearch(hotsearch){
		for(var i=0;i<10 && i<hotsearch.length;i++){
			var hots = "<li class='searchli' style='clear: both;'><a href='javascript:;' ><p>"+hotsearch[i].keyword+"</p></a></li>";
			$(".schList").append(hots);
		}
		sch();
	}

</script>
</head>
<body>
	<div class="body_top">
		<div class="top_wp clearfix">
			<div class="top_left fl">
				<a class="collect_me mr10" href="javascript:;" onclick="addToFavorite()">收藏福气商城</a>
                <a href="javascript:;" onclick="shareUrl()">分享给好友</a>
				<input type="hidden" id="basePath" value="${basePath}"/> 
				<span class="t_line">|</span> 
				<span class="ml10">您好 <#if cust??>${cust.customerNickname!''}</#if>，欢迎来到福气商城！</span>
                <input type="hidden" id="customer_id" value="<#if cust??>${cust.customerId!''}</#if>" />
				<#if cust?? >
					<a class="t_login ml10" href="${basePath}/logout.html">退出</a>
				<#else>
					<a class="t_login ml10" href="${basePath}/login.html">请登录</a>
					<a class="t_reg ml10" href="${basePath}/register.html">免费注册</a>
				</#if>
			</div>
			<!--/top_left-->
			<ul class="top_nav fr clearfix">
			<li>
            	<#if sCodeList?? && (sCodeList?size>0)>
			    	<#list sCodeList as sCode>
			    		<#if (sCode.code)??>
						<#if (sCode.code?starts_with("<script") && sCode.code?ends_with("</script>"))>
							${(sCode.code)!''}
						</#if>
			    		</#if>
			    	</#list>
				</#if>
				</li>
				<li><a href="${basePath}/systemmsg.html" class="fl mes_tips" target="_blank">
				 <span id="noreadmsg" class="noread"></span><img src="${basePath}/images/mail_red.png" alt=""/>
				 </a></li>
				<li><a href="${basePath}/customer/myorder.html">我的订单</a></li>
				<li class="my_hao24 pr"><span class="t_line">|</span> <span class="outline"></span> <a href="javascript:;">我的福气商城</a>
					<div class="mh_cont pa">
						<a href="${basePath}/myshoppingcart.html">我的购物车</a><!-- <a href="javascript:;">我的购物金</a> -->
						<!--<a href="javascript:;">我的积分卡</a><a href="javascript:;">你推荐我送金</a>-->
					</div> <!--/mh_cont--></li>
				<li><span class="t_line">|</span><a href="${basePath}/help/2"><span style="text-decoration:line-through;">帮助中心</span></a></li>
			</ul>
			<!--/top_nav-->
			<!--<a class="to_reg fr mr10" href="javascript:;">签到有金</a>-->
		</div>
		<!--/top_wp-->
	</div>
	<!--/top-->

	<div class="container">
	<#--
		<a class="gg_box pr" href="javascript:;" ><img alt="" src="${basePath}/images/images_01.jpg" /><b></b></a>
	-->
		<div class="header clearfix">
			<div id = "logo" style="width:165px;height:39px;float:left;">
			<!--
			<a class="logo fl" href="#"><img alt=""
				src="${basePath}/images/logo.gif" /></a>
			-->			
			</div>
			<div class="search_wp fl">
				<div class="search_box">
					<#--
					<form id="topSearchForm" action = "${basePath}/goods/searchProduct.html" method = "POST">
					-->
					<form id="topSearchForm" action = "${basePath}/goods/searchproduct.html" method = "POST">
						<input class="search_text fl c999" type="text" name = "title" placeholder="家电疯抢4折起" 
						id = "dirvalue" autocomplete="off"/>
						<input class="search_btn fr" type="submit" value="" id = "dirbutton"/>
						<ul class="schList">
	                    </ul><!--/schList-->
					</form>
				</div>
				<!--/search_box-->
				<div class="hot_word mt10" id="hotsearch">
					热门搜索：
				</div>
				<!--/hot_word-->
			</div>
			<!--/search_wp-->
			<a class="fl ml10" href="javascript:;"></a>
			<div class="shopping_cart fr mt10" >
				<dl class="pr" id = "loadcart">
					<dt class="pr" id ="mminicart">
						<span class="sc_num">0</span> <a href="${basePath}/myshoppingcart.html" >购物车</a> <b></b>
					</dt>
					<dd id="cartdd">
						
						<img id="nocart" />
					</dd>
				</dl>
			</div>
			<!--/shopping_cart-->
		</div>
		<!--/header-->

		<div class="nav clearfix">
			<div class="pro_sort fl pr">
				<h3>全部商品分类</h3>
				<div class="sort_cont pa" id = "catelist">
	 				
				</div>
				<!--/sort_cont-->
			</div>
			<!--/pro_sort-->
			<ul class="nav_list fl clearfix" id = "bar">
				
			</ul>
			<!--/nav_list-->
			<#--
			<a class="nv_bn fr" href="javascript:;" style="text-decoration:line-through;">今日节目表</a> <a class="nv_bn fr"
				href="javascript:;" style="text-decoration:line-through;">电视直播</a>
			-->
		</div>
		<!--/nav-->


		<!--/live_wp-->
	</div>
	<!--/slide_wp-->
    <div class="mask"></div><!--/mask-->
    <div class="dialog dia9" style="height:auto!important">
        <div class="dia_tit clearfix">
            <h4 class="fl collect_title">加入收藏</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont" style="margin-top: 30px;">
            <div class="dia_collect_intro tc pt30"><img class="vm collect_img" alt="" src="" />
                <em class="collect_content" style="font-size: 16px;">加入收藏失败，请使用菜单栏或Ctrl+D进行添加!</em></div>
            <div class="dia_ops mt20 tc" style="margin-top: 60px;">
                <a class="info_tip_submit2 go_pay" href="javascript:;" onclick="cls()">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->


    <div class="mask"></div><!--/分享链接给好友-->
    <div id="fenxiang" class="dialog dia19" style="height:auto!important">
        <div class="dia_tit clearfix">
            <h4 class="fl collect_title">分享好友</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div>
        <div class="dia_cont">
            <div  class="dia_collect_intro tc pt30">
                <span id="content" style="font-size:16px"></span>
            </div>
            <div class="dia_ops mt20 tc" style="margin-top: 30px;">
                <a class="info_tip_submit2 go_pay"  id="copy"  data-clipboard-target="content" href="javascript:;">复制</a>
                <a class="info_tip_submit2 go_pay"  onclick="cls()" href="javascript:;">取消</a>
                <br/>
                <span>&nbsp;</span>
                <span style="color: red; font-size:12px">亲！如果您的好友成功注册为会员，会有积分送给您哦！</span>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->


    <div class="mask"></div><!--/分享链接提示先登录-->
    <div id="fenxiang" class="dialog dia20" style="height:auto!important">
        <div class="dia_tit clearfix">
            <h4 class="fl collect_title">登录提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div>
        <div class="dia_cont">
            <div  class="dia_collect_intro tc pt30" style="margin-top: 30px;">
                <span id="content" style="font-size:16px">您未登录！现在确认要登录吗？</span>
            </div>
            <div class="dia_ops mt20 tc" style="margin-top: 50px;">
                <a class="info_tip_submit2 go_pay" onclick="login()" href="javascript:;">确定</a>
                <a class="info_tip_submit2 go_pay" onclick="cls()" href="javascript:;">取消</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->


    <div class="mask"></div><!--/分享链接复制成功提示-->
    <div id="fenxiang" class="dialog dia21" style="height:auto!important">
        <div class="dia_tit clearfix">
            <h4 class="fl collect_title">操作提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div>
        <div class="dia_cont">
            <div  class="dia_collect_intro tc pt30" style="margin-top: 30px;">
                <span style="font-size:16px">复制成功！</span>
            </div>
            <div class="dia_ops mt20 tc" style="margin-top: 50px;">
                <a class="info_tip_submit2 go_pay" onclick="cls()" href="javascript:;">关闭</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->





</body>
<#--
<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
-->
<#--
<script type="text/javascript" src="${basePath}/js/jquery1.7.2.js"></script>
-->
<script type="text/javascript" src="${basePath}/js/jquery.slides.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.zclip.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.zclip.js"></script>

<script>

$(function(){
	jQuery.fn.isChildAndSelfOf = function(b){ return (this.closest(b).length > 0); };
	$('#slides').slidesjs({
		width: 790,
		height: 350,
		play: {
		  active: false,
		  effect: "fade",
		  auto: true,
		  interval: 4000
		},
		pagination: {
			effect: "fade"
		}
	  });
			
});

function sch(){
		var text;
		$(".search_text").focus(function(){
			text = $(".search_text").val();
			$(".search_text").val("").removeAttr("placeholder");
			$(".schList").show();
		});
		$(".schList li").each(function(){
			var cur = $(this);
			cur.find("a").click(function(){
				var v = $(this).text();
				text = v;
				$(".search_text").val(v);
				$(".schList").hide();
				$("#topSearchForm").submit();
			});
		});
			
		$(document).click(function(event){
			if(!$(event.target).isChildAndSelfOf(".search_box")){
				$(".search_text").val(text);
				$(".schList").hide();
			};
		});
};

function clicka1(){
	var cur = $(this);
			cur.find("a").click(function(){
				var v = $(this).text();
				text = v;
				$(".search_text").val(v);
				$(".schList").hide();
				$("#topSearchForm").submit();
			});
}
	 $.ajax({
		    url:'${basePath}/messagecount.html?'+Math.random(),
		    dataType:'json',
		    success:function(result){
		         if(result.noreadcount > 0){
		            $("#noreadmsg").html(result.noreadcount);
		         }else{
		         	$("#noreadmsg").hide();
		         }
		     }
		 });
</script>
<script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
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
                dia(9);

            }
        }
    }
    //表单提交
    function loginthird(){
        $("#loginthirds").submit();
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
            dia(20);
        }else{
            //给会员id加密
            var b = new Base64();
            var result = 'customer_id='+customerId;
            var str = b.encode('"'+result+'"');
            var url = local+basePath+'/register.html?'+str;
            $('#content').html(url);
            dia(19);
        }
        //复制成功弹出复制成功窗口
        $('#copy').zclip({
            path:'${basePath}/js/ZeroClipboard.swf',
            copy:function(){
                return $("#content").html();
            },
            afterCopy:function(){
                dia(21);
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
            string = string.replace(/\r\n/g,"\n");
            var utftext = "";
            for (var n = 0; n < string.length; n++) {
                var c = string.charCodeAt(n);
                if (c < 128) {
                    utftext += String.fromCharCode(c);
                } else if((c > 127) && (c < 2048)) {
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
            while ( i < utftext.length ) {
                c = utftext.charCodeAt(i);
                if (c < 128) {
                    string += String.fromCharCode(c);
                    i++;
                } else if((c > 191) && (c < 224)) {
                    c2 = utftext.charCodeAt(i+1);
                    string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                    i += 2;
                } else {
                    c2 = utftext.charCodeAt(i+1);
                    c3 = utftext.charCodeAt(i+2);
                    string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                    i += 3;
                }
            }
            return string;
        }
    }
</script>
</html>