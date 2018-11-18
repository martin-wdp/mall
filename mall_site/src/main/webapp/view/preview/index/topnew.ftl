
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<#assign basePath=request.contextPath> 
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta property="qc:admins" content="274167644576616701163757" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
	//加入收藏
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
	}
	
	var empbo = 0;
	window.onload = function(){
		$("#mminicart").live("mouseover",minicartonload);
		$("#delect_minicart").live("click", function(){
			var emp1 = $(this).next().val();
			var emp2 = $(this).next().next().val();
		
			$.ajax({ url: "${basePath}/delshoppingcartbyid.htm?shoppingCartId="+emp1+"&goodsInfoId="+emp2, success: function(dats){
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
				if(cartgoods != null){
					$(".sc_num").html(cartgoods.length);
				}else{
					$(".sc_num").html("0");
				}
				if(cartgoods != null && cartgoods.length>0){
					$("#cartdd").html("");
					$("#cartdd").append("<h4 class='fb'>最新加入的商品</h4><div class='sc_box' id ='cartmini'></div><div class='sc_info tr' id ='cartinfo'></div>");
					for(var i = 0 ; i < cartgoods.length ; i++){
						$("#cartmini").append("<ul class='sc_list'>"+
													"<li class='clearfix'>"+
														"<a class='sc_img fl' href='${basePath}/item/"+cartgoods[i].goodsInfoId+".html'><img alt='' src='"+cartgoods[i].productPic+"' width='52px' height='52px'/></a> "+
														"<a class='sc_name fl ml10' href='${basePath}/item/"+cartgoods[i].goodsInfoId+".html'>"+cartgoods[i].productName+"</a>"+
														"<div class='sc_op fr tr'>"+
															"<p class='sc_price mb10'>"+
																"<em>"+cartgoods[i].productPrice+"</em>×"+cartgoods[i].buNum+
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
					
						$("#cartinfo").append("<p> 共<span>"+cartgoods.length+"</span>件商品&nbsp;&nbsp;<span></span>&nbsp;&nbsp;&nbsp;共计<em>￥"+empvalue+"</em></p><a class='sc_btn mt5' href='${basePath}/myshoppingcart.html'>去购物车结算</a>");
						
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
				<input type="hidden" id="basePath" value="${basePath}"/> 
				<span class="t_line">|</span> 
				<span class="ml10">您好 <#if cust??>${cust.customerNickname!''}</#if>，欢迎来到福气商城！</span> 
				<#if cust?? >
					<a class="t_login ml10" href="${basePath}/logout.html">退出</a>
				<#else>
					<a class="t_login ml10" href="${basePath}/login.html">请登录</a>
					<a class="t_reg ml10" href="${basePath}/register.html">免费注册</a>
				</#if>
			</div>
			<!--/top_left-->
			<ul class="top_nav fr clearfix">
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





</body>
<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.slides.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/default.js"></script>

<script type="text/javascript">

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
</html>