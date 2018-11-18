
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<#assign basePath=request.contextPath>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />

<script type="text/javascript">
	window.onload = function(){
		
		$("#delect_minicart").live("click", function(){
			var emp1 = $(this).next().val();
		
			$.ajax({ url: "${basePath}/delshoppingcartbyid.htm?shoppingCartId="+emp1,async:false, success: function(dats){
			
				if(dats == 1){
					removenimicart();	
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
		$.ajax({ url: "${basePath}/tops_1.html", success: function(date){
	        
	     	var bar = date.navList;
	     	
	     	if(bar != null){
		     	for(var z = 0 ; z < bar.length; z++){
					$("#bar").append("<li><a href='${basePath}/"+bar[z].barUrl+"'>"+bar[z].barName+"</a></li>");
		     	}
		    }
	     	var s = date.catList;
			if(s != null){

				for(var i = 0 ; i < s.length ; i++){
				
					$("#catelist").append("<div class='sort_item' id = 'cateid"+i+"'>"
										 	+"<a class='s_item' href='${basePath}/list/"+s[i].catId+"-"+s[i].catId+".html'>"+s[i].catName+"</a><div class='lv2_item'></div>"											
										 +"</div>");
					var e = s[i].cateVos;
					
					if(e != null){
						
						for(var j = 0; j < e.length; j ++){
							
							$("#cateid"+i+" .lv2_item").append("<div class='lv2_cate fl' id = 'catelv2"+i+"t"+j+"'>"
													+"<dl>"
														+"<dt>"
															+"<a href='${basePath}/list/"+e[j].catId+"-"+e[j].catParentId+".html'>"+e[j].catName+"</a>"
														+"</dt><dd></dd>"
													+"</dl>"
												+"</div></div>");
							var x = e[j].cateVos;
					
							if(x != null){

								for (var k = 0 ; k < x.length; k++){
								
									$("#catelv2"+i+"t"+j+" dl dd").append("<a href='${basePath}/list/"+x[k].catParentId+"-"+x[k].catId+".html'>"+x[k].catName+"</a><span>|");
								}
							}
						}
					}
				}	
				
				
			} 

			
	     }});
	  };
	 
	  function minicartonload(){
		removenimicart();
	  	$.ajax({ url: "${basePath}/minicart.htm", success: function(datee){
	  		var cartgoods = datee.shopcart.miniGoodsList;
			var empvalue = 0;
            //比如一件商品买了3件,详细统计购买商品数量
            var countgoods=0;
			for(var i = 0 ; i < cartgoods.length ; i++){
                countgoods+=cartgoods[i].buNum;
				$("#cartmini").append("<ul class='sc_list'>"+
											"<li class='clearfix'>"+
												"<a class='sc_img fl' href='item/"+cartgoods[i].goodsInfoId+".html'><img alt='' src='"+cartgoods[i].productPic+"' width='52px' height='52px'/></a> "+
												"<a class='sc_name fl ml10' href='item/"+cartgoods[i].goodsInfoId+".html'>"+cartgoods[i].productName+"</a>"+
												"<div class='sc_op fr tr'>"+
													"<p class='sc_price mb10'>"+
														"<em> &yen;"+cartgoods[i].productPrice+"</em>×"+cartgoods[i].buNum+
													"</p>"+
													"<a class='sc_delete'  id = 'delect_minicart'>删除</a>"+
													"<input type= 'hidden' class= 'goodsid' value = '"+cartgoods[i].shoppingCartId+"'>"+
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
				
				empvalue += cartgoods[i].productPrice;
			}
			
			$("#cartinfo").append("<p> 共<span>"+countgoods+"</span>件商品&nbsp;&nbsp;<span></span>&nbsp;&nbsp;&nbsp;共计<em>￥"+empvalue+"</em></p><a class='sc_btn mt5' href='myshoppingcart.html'>去购物车结算</a>");
			$(".sc_num").html(countgoods);
		}});
	 }
	  
	  
	  function removenimicart(){
	  	
	 	 $("#cartmini").html('');
	 	 $("#cartinfo").html('');
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
</script>
</head>
<body>
	<div class="body_top">
		<div class="top_wp clearfix">
			<div class="top_left fl">
				<a class="collect_me mr10" href="javascript:;">收藏好享商城</a> 
				<span class="t_line">|</span> 
				<span class="ml10">您好 <#if cust??>${cust.customerNickname!''}</#if>，欢迎来到好享商城！</span> 
				<#if cust?? >
					<a class="t_login ml10" href="${basePath}/logout.html">退出</a>
				<#else>
					<a class="t_login ml10" href="${basePath}/login.html">请登录</a>
					<a class="t_reg ml10" href="${basePath}/register.html">免费注册</a>
				</#if>
			</div>
			<!--/top_left-->
			<ul class="top_nav fr clearfix">
			
				<li><a href="${basePath}/customer/myorder.html">我的订单</a></li>
				<li class="my_hao24 pr"><span class="t_line">|</span> <span class="outline"></span> <a href="javascript:;">我的好享商城</a>
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
	<!--
	<div class="container">
		<a class="gg_box pr" href="javascript:;"><img alt="" src="${basePath}/images/images_01.jpg" /><b></b></a>
		<div class="header clearfix">
			<a class="logo fl" href="#"><img alt=""
				src="${basePath}/images/logo.gif" /></a>
			<div class="search_wp fl">
				<div class="search_box">
					<form id="topSearchForm" action = "${basePath}/goods/searchProduct.html" method = "POST">
						<input class="search_text fl" type="text" name = "title" placeholder="家电疯抢4折起" id = "dirvalue"/>
						<input class="search_btn fr" type="submit" value="" id = "dirbutton"/>
					</form>
				</div>
				<!--/search_box-->
				<!--
				<div class="hot_word mt10">
					热门搜索： <a href="javascript:;" onclick="changeSearchKey(this);">空气净化器</a> 
					<a href="javascript:;" onclick="changeSearchKey(this);">酒</a>
					<a href="javascript:;" onclick="changeSearchKey(this);">足浴器</a>
					 <a href="javascript:;" onclick="changeSearchKey(this);">保暖内衣</a> <a
						href="javascript:;" onclick="changeSearchKey(this);">手机</a>
						 <a href="javascript:;" onclick="changeSearchKey(this);">乐扣</a>
				</div>
				
			</div>
		
			<a class="fl ml10" href="javascript:;"></a>
			<div class="shopping_cart fr mt10" >
				<dl class="pr" id = "loadcart">
					<dt class="pr">
						<span class="sc_num">?</span> <a href="javascript:;"onMouseOver="minicartonload()" >购物车</a> <b></b>
					</dt>
					<dd >
						<h4 class="fb">最新加入的商品</h4>
						<div class="sc_box" id ="cartmini">
						<!--
							<ul class="sc_list">
								<li class="clearfix">
									<a class="sc_img fl" href="javascript:;"><img alt="" src="${basePath}/images/images_03.jpg" /></a> 
									<a class="sc_name fl ml10" href="javascript:;">2013新款秋装男士长袖衬衫 含加绒加厚保暖格子韩版修身衬衣 潮男装</a>
									<div class="sc_op fr tr">
										<p class="sc_price mb10">
											<em>￥89.00</em>×1
										</p>
										<a class="sc_delete" href="javascript:;">删除</a>
									</div> 
								</li>
							</ul>
							
							<div class="taozhuang">
								<div class="fr">小计：￥638.00</div>
								<div class="fl">
									<span>套装</span>开年特惠
								</div>
							</div>
							<ul class="sc_list">
								<li class="clearfix"><a class="sc_img fl"
									href="javascript:;"><img alt=""
										src="${basePath}/images/images_03.jpg" /></a> <a class="sc_name fl ml10"
									href="javascript:;">2013新款秋装男士长袖衬衫 含加绒加厚保暖格子韩版修身衬衣 潮男装</a>
									<div class="sc_op fr tr">
										<p class="sc_price mb10">
											<em>￥89.00</em>×1
										</p>
										<a class="sc_delete" href="javascript:;">删除</a>
									</div> </li>
							</ul>
							
							<div class="cuxiao">
								<div class="fr">小计：￥638.00</div>
								<div class="fl">
									<span>满赠</span>购物满39元，即可领取赠品
								</div>
							</div>
							<ul class="sc_list">
								<li class="clearfix"><a class="sc_img fl" href="javascript:;"><img alt="" src="../${basePath}/images/images_03.jpg" /></a> <a class="sc_name fl ml10" href="javascript:;">2013新款秋装男士长袖衬衫 含加绒加厚保暖格子韩版修身衬衣 潮男装</a>
									<div class="sc_op fr tr">
										<p class="sc_price mb10">
											<em>￥89.00</em>×1
										</p>
										<a class="sc_delete" href="javascript:;">删除</a>
									</div> </li>
								<li class="clearfix"><a class="sc_img fl"href="javascript:;"><img alt="" src="../${basePath}/images/images_03.jpg" /></a> <a class="sc_name fl ml10" href="javascript:;">2013新款秋装男士长袖衬衫 含加绒加厚保暖格子韩版修身衬衣 潮男装</a>
									<div class="sc_op fr tr">
										<p class="sc_price mb10">
											<em>￥89.00</em>×1
										</p>
										<a class="sc_delete" href="javascript:;">删除</a>
									</div> </li>
								<li class="clearfix"><a class="sc_img fl"
									href="javascript:;"><img alt=""
										src="${basePath}/images/images_03.jpg" /></a> <a class="sc_name fl ml10"
									href="javascript:;">2013新款秋装男士长袖衬衫 含加绒加厚保暖格子韩版修身衬衣 潮男装</a>
									<div class="sc_op fr tr">
										<p class="sc_price mb10">
											<em>￥89.00</em>×1
										</p>
										<a class="sc_delete" href="javascript:;">删除</a>
									</div> </li>
							</ul>
						
						</div>
						<div class="sc_info tr" id ="cartinfo">
							
						</div>
						
					</dd>
				</dl>
			</div>
			
		</div>
	

		<div class="nav clearfix">
			<div class="pro_sort fl pr">
				<h3>全部商品分类</h3>
				<div class="sort_cont pa" id = "catelist">
	 				<!--
					<div class="sort_item">
						<a class="s_item" href="javascript:;">生活用品、生活家电</a>
						<div class="lv2_item">
							<div class="lv2_band fr">
								<div>
									<a href="#"><img alt="" src="${basePath}/images/images_29.jpg" /></a>
								</div>
								<div>
									<a href="#"><img alt="" src="${basePath}/images/images_28.jpg" /></a>
								</div>
								<div>
									<h4>品牌推荐</h4>
									<ul>
										<li><a href="#"><img alt=""
												src="../${basePath}/images/images_20.jpg" /></a></li>
										<li><a href="#"><img alt=""
												src="../${basePath}/images/images_21.jpg" /></a></li>
										<li><a href="#"><img alt=""
												src="../${basePath}/images/images_22.jpg" /></a></li>
									</ul>
								</div>
							</div>
							<div class="lv2_cate fl">
								<dl>
									<dt>
										<a href="#">生活用品</a>
									</dt>
									<dd>
										<a href="#">清洁</a><span>|</span> <a href="#">收纳</a><span>|</span>
										<a href="#">水杯</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<a href="#">生活家电</a>
									</dt>
									<dd>
										<a href="#">挂烫机</a><span>|</span> <a href="#">吸尘器</a><span>|</span>
										<a href="#">取暖器</a><span>|</span> <a href="#">净水器</a><span>|</span>
										<a href="#">加湿器</a><span>|</span> <a href="#">晾晒架</a><span>|</span>
										<a href="#">衣架衣罩</a><span>|</span> <a href="#">收纳层架</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<a href="#">生活用品</a>
									</dt>
									<dd>
										<a href="#">清洁</a><span>|</span> <a href="#">收纳</a><span>|</span>
										<a href="#">水杯</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<a href="#">生活家电</a>
									</dt>
									<dd>
										<a href="#">挂烫机</a><span>|</span> <a href="#">吸尘器</a><span>|</span>
										<a href="#">取暖器</a><span>|</span> <a href="#">净水器</a><span>|</span>
										<a href="#">加湿器</a><span>|</span> <a href="#">晾晒架</a><span>|</span>
										<a href="#">衣架衣罩</a><span>|</span> <a href="#">收纳层架</a>
									</dd>
								</dl>
							</div>
						</div>
					</div>
			
					<div class="sort_item">
						<a class="s_item" href="javascript:;">厨房用品、厨房家电</a>
						<div class="lv2_item">
							<div class="lv2_band fr"></div>
							<div class="lv2_cate fl">
								<dl>
									<dt>
										<a href="#">生活用品</a>
									</dt>
									<dd>
										<a href="#">清洁</a><span>|</span> <a href="#">收纳</a><span>|</span>
										<a href="#">水杯</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<a href="#">生活家电</a>
									</dt>
									<dd>
										<a href="#">挂烫机</a><span>|</span> <a href="#">吸尘器</a><span>|</span>
										<a href="#">取暖器</a><span>|</span> <a href="#">净水器</a><span>|</span>
										<a href="#">加湿器</a><span>|</span> <a href="#">晾晒架</a><span>|</span>
										<a href="#">衣架衣罩</a><span>|</span> <a href="#">收纳层架</a>
									</dd>
								</dl>
							</div>
						</div>
					</div>
			
					<div class="sort_item">
						<a class="s_item" href="javascript:;">电脑数码、大家电</a>
						<div class="lv2_item">
							<div class="lv2_band fr"></div>
							<div class="lv2_cate fl">
								<dl>
									<dt>
										<a href="#">生活用品</a>
									</dt>
									<dd>
										<a href="#">清洁</a><span>|</span> <a href="#">收纳</a><span>|</span>
										<a href="#">水杯</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<a href="#">生活家电</a>
									</dt>
									<dd>
										<a href="#">挂烫机</a><span>|</span> <a href="#">吸尘器</a><span>|</span>
										<a href="#">取暖器</a><span>|</span> <a href="#">净水器</a><span>|</span>
										<a href="#">加湿器</a><span>|</span> <a href="#">晾晒架</a><span>|</span>
										<a href="#">衣架衣罩</a><span>|</span> <a href="#">收纳层架</a>
									</dd>
								</dl>
							</div>
						</div>
					</div>
				
					<div class="sort_item">
						<a class="s_item" href="javascript:;">家纺、床品、家具</a>
						<div class="lv2_item">
							<div class="lv2_band fr"></div>
							<div class="lv2_cate fl">
								<dl>
									<dt>
										<a href="#">生活用品</a>
									</dt>
									<dd>
										<a href="#">清洁</a><span>|</span> <a href="#">收纳</a><span>|</span>
										<a href="#">水杯</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<a href="#">生活家电</a>
									</dt>
									<dd>
										<a href="#">挂烫机</a><span>|</span> <a href="#">吸尘器</a><span>|</span>
										<a href="#">取暖器</a><span>|</span> <a href="#">净水器</a><span>|</span>
										<a href="#">加湿器</a><span>|</span> <a href="#">晾晒架</a><span>|</span>
										<a href="#">衣架衣罩</a><span>|</span> <a href="#">收纳层架</a>
									</dd>
								</dl>
							</div>
						</div>
					</div>
				
					<div class="sort_item">
						<a class="s_item" href="javascript:;">美容护肤、美妆工具</a>
						<div class="lv2_item">
							<div class="lv2_band fr"></div>
							<div class="lv2_cate fl">
								<dl>
									<dt>
										<a href="#">生活用品</a>
									</dt>
									<dd>
										<a href="#">清洁</a><span>|</span> <a href="#">收纳</a><span>|</span>
										<a href="#">水杯</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<a href="#">生活家电</a>
									</dt>
									<dd>
										<a href="#">挂烫机</a><span>|</span> <a href="#">吸尘器</a><span>|</span>
										<a href="#">取暖器</a><span>|</span> <a href="#">净水器</a><span>|</span>
										<a href="#">加湿器</a><span>|</span> <a href="#">晾晒架</a><span>|</span>
										<a href="#">衣架衣罩</a><span>|</span> <a href="#">收纳层架</a>
									</dd>
								</dl>
							</div>
						</div>
					</div>
					
					<div class="sort_item">
						<a class="s_item" href="javascript:;">运动器械、保健器械</a>
						<div class="lv2_item">
							<div class="lv2_band fr"></div>
							<div class="lv2_cate fl">
								<dl>
									<dt>
										<a href="#">生活用品</a>
									</dt>
									<dd>
										<a href="#">清洁</a><span>|</span> <a href="#">收纳</a><span>|</span>
										<a href="#">水杯</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<a href="#">生活家电</a>
									</dt>
									<dd>
										<a href="#">挂烫机</a><span>|</span> <a href="#">吸尘器</a><span>|</span>
										<a href="#">取暖器</a><span>|</span> <a href="#">净水器</a><span>|</span>
										<a href="#">加湿器</a><span>|</span> <a href="#">晾晒架</a><span>|</span>
										<a href="#">衣架衣罩</a><span>|</span> <a href="#">收纳层架</a>
									</dd>
								</dl>
							</div>
						</div>
					</div>
				
					<div class="sort_item">
						<a class="s_item" href="javascript:;">美食、母婴</a>
						<div class="lv2_item">
							<div class="lv2_band fr"></div>
							<div class="lv2_cate fl">
								<dl>
									<dt>
										<a href="#">生活用品</a>
									</dt>
									<dd>
										<a href="#">清洁</a><span>|</span> <a href="#">收纳</a><span>|</span>
										<a href="#">水杯</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<a href="#">生活家电</a>
									</dt>
									<dd>
										<a href="#">挂烫机</a><span>|</span> <a href="#">吸尘器</a><span>|</span>
										<a href="#">取暖器</a><span>|</span> <a href="#">净水器</a><span>|</span>
										<a href="#">加湿器</a><span>|</span> <a href="#">晾晒架</a><span>|</span>
										<a href="#">衣架衣罩</a><span>|</span> <a href="#">收纳层架</a>
									</dd>
								</dl>
							</div>
						</div>
					</div>
				
					<div class="sort_item">
						<a class="s_item" href="javascript:;">服装配饰</a>
						<div class="lv2_item">
							<div class="lv2_band fr"></div>
							<div class="lv2_cate fl">
								<dl>
									<dt>
										<a href="#">生活用品</a>
									</dt>
									<dd>
										<a href="#">清洁</a><span>|</span> <a href="#">收纳</a><span>|</span>
										<a href="#">水杯</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<a href="#">生活家电</a>
									</dt>
									<dd>
										<a href="#">挂烫机</a><span>|</span> <a href="#">吸尘器</a><span>|</span>
										<a href="#">取暖器</a><span>|</span> <a href="#">净水器</a><span>|</span>
										<a href="#">加湿器</a><span>|</span> <a href="#">晾晒架</a><span>|</span>
										<a href="#">衣架衣罩</a><span>|</span> <a href="#">收纳层架</a>
									</dd>
								</dl>
							</div>
						</div>
					</div>
			
					<div class="sort_item">
						<a class="s_item" href="javascript:;">珠宝、饰品</a>
						<div class="lv2_item">
							<div class="lv2_band fr"></div>
							<div class="lv2_cate fl">
								<dl>
									<dt>
										<a href="#">生活用品</a>
									</dt>
									<dd>
										<a href="#">清洁</a><span>|</span> <a href="#">收纳</a><span>|</span>
										<a href="#">水杯</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<a href="#">生活家电</a>
									</dt>
									<dd>
										<a href="#">挂烫机</a><span>|</span> <a href="#">吸尘器</a><span>|</span>
										<a href="#">取暖器</a><span>|</span> <a href="#">净水器</a><span>|</span>
										<a href="#">加湿器</a><span>|</span> <a href="#">晾晒架</a><span>|</span>
										<a href="#">衣架衣罩</a><span>|</span> <a href="#">收纳层架</a>
									</dd>
								</dl>
							</div>
						</div>
					</div>
				
					<div class="sort_item">
						<a class="s_item" href="javascript:;">户外用品、旅游</a>
						<div class="lv2_item">
							<div class="lv2_band fr"></div>
							<div class="lv2_cate fl">
								<dl>
									<dt>
										<a href="#">生活用品</a>
									</dt>
									<dd>
										<a href="#">清洁</a><span>|</span> <a href="#">收纳</a><span>|</span>
										<a href="#">水杯</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<a href="#">生活家电</a>
									</dt>
									<dd>
										<a href="#">挂烫机</a><span>|</span> <a href="#">吸尘器</a><span>|</span>
										<a href="#">取暖器</a><span>|</span> <a href="#">净水器</a><span>|</span>
										<a href="#">加湿器</a><span>|</span> <a href="#">晾晒架</a><span>|</span>
										<a href="#">衣架衣罩</a><span>|</span> <a href="#">收纳层架</a>
									</dd>
								</dl>
							</div>
						</div>
					</div>
			
					<div class="sort_item st_01">
						<a class="s_item" href="javascript:;"><img class="vm mr5"
							alt="" src="../${basePath}/images/st_01.gif" />TV热卖</a>
						<div class="lv2_item">
							<div class="lv2_band fr"></div>
							<div class="lv2_cate fl">
								<dl>
									<dt>
										<a href="#">生活用品</a>
									</dt>
									<dd>
										<a href="#">清洁</a><span>|</span> <a href="#">收纳</a><span>|</span>
										<a href="#">水杯</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<a href="#">生活家电</a>
									</dt>
									<dd>
										<a href="#">挂烫机</a><span>|</span> <a href="#">吸尘器</a><span>|</span>
										<a href="#">取暖器</a><span>|</span> <a href="#">净水器</a><span>|</span>
										<a href="#">加湿器</a><span>|</span> <a href="#">晾晒架</a><span>|</span>
										<a href="#">衣架衣罩</a><span>|</span> <a href="#">收纳层架</a>
									</dd>
								</dl>
							</div>
						</div>
					</div>
				
					<div class="sort_item st_02">
						<a class="s_item" href="javascript:;"><img class="vm mr5"
							alt="" src="../${basePath}/images/st_02.gif" />进口美食馆</a>
						<div class="lv2_item">
							<div class="lv2_band fr"></div>
							<div class="lv2_cate fl">
								<dl>
									<dt>
										<a href="#">生活用品</a>
									</dt>
									<dd>
										<a href="#">清洁</a><span>|</span> <a href="#">收纳</a><span>|</span>
										<a href="#">水杯</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<a href="#">生活家电</a>
									</dt>
									<dd>
										<a href="#">挂烫机</a><span>|</span> <a href="#">吸尘器</a><span>|</span>
										<a href="#">取暖器</a><span>|</span> <a href="#">净水器</a><span>|</span>
										<a href="#">加湿器</a><span>|</span> <a href="#">晾晒架</a><span>|</span>
										<a href="#">衣架衣罩</a><span>|</span> <a href="#">收纳层架</a>
									</dd>
								</dl>
							</div>
						</div>
					</div>
			
					<div class="sort_item st_03">
						<a class="s_item" href="javascript:;"><img class="vm mr5"
							alt="" src="../${basePath}/images/st_03.gif" />时尚手机馆</a>
						<div class="lv2_item">
							<div class="lv2_band fr"></div>
							<div class="lv2_cate fl">
								<dl>
									<dt>
										<a href="#">生活用品</a>
									</dt>
									<dd>
										<a href="#">清洁</a><span>|</span> <a href="#">收纳</a><span>|</span>
										<a href="#">水杯</a>
									</dd>
								</dl>
								<dl>
									<dt>
										<a href="#">生活家电</a>
									</dt>
									<dd>
										<a href="#">挂烫机</a><span>|</span> <a href="#">吸尘器</a><span>|</span>
										<a href="#">取暖器</a><span>|</span> <a href="#">净水器</a><span>|</span>
										<a href="#">加湿器</a><span>|</span> <a href="#">晾晒架</a><span>|</span>
										<a href="#">衣架衣罩</a><span>|</span> <a href="#">收纳层架</a>
									</dd>
								</dl>
							</div>
						</div>
					</div>
					<!--
					<div class="sort_gg tc">
						<a href="javascript:;"><img alt="" src="${basePath}/images/images_04.jpg" /></a>
					</div>
					-->
					<!--
				</div>
			</div>
			<ul class="nav_list fl clearfix" id = "bar">
				<li class="cur"><a href="javascript:;">首页</a></li>
			
			</ul>
			<a class="nv_bn fr" href="javascript:;" style="text-decoration:line-through;">今日节目表</a> <a class="nv_bn fr"
				href="javascript:;" style="text-decoration:line-through;">电视直播</a>
		</div>
		
	</div>
	-->





</body>
<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.slides.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/index.js"></script>

</html>