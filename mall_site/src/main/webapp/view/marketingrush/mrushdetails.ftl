<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title><#if map.detailBean.productVo.goods.goodsSeoTitle?length &gt; 0> ${map.detailBean.productVo.goods.goodsSeoTitle!''}<#else>${map.detailBean.productVo.productName!''}</#if> -${topmap.systembase.bsetName}</title>
<meta name="description" content="${map.detailBean.productVo.goods.goodsSeoDesc!''}-${topmap.seo.meteDes}">
<meta name="Keywords" content="${map.detailBean.productVo.goods.goodsSeoKeyword}-${topmap.seo.meteKey}">
<#assign basePath=request.contextPath>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<#if (topmap.systembase.bsetHotline)??>
	<link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
	<link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
<style type="text/css">
        .yi_over{background: Gray;}
</style>
</head>
<body>
	<#--一引入头部 <#include "/index/topnew.ftl" /> -->	
		<#if (topmap.temp)??>
			<#if (topmap.temp.tempId==1)>
				<#include "../index/topnew.ftl">
			<#elseif (topmap.temp.tempId==3)>
				<#include "../index/newheader.ftl">
			<#elseif (topmap.temp.tempId==9)>
				<#include "../index/newheader4.ftl">
			<#elseif (topmap.temp.tempId==10)>
				<#include "../index/newheader5.ftl">
			<#elseif (topmap.temp.tempId==11)>
				<#include "../index/newheader6.ftl">
			<#elseif (topmap.temp.tempId==12)>
				<#include "../index/newheader7.ftl">
			<#elseif (topmap.temp.tempId==13)>
				<#include "../index/newheader8s.ftl">
			<#elseif (topmap.temp.tempId==14)>
				<#include "../index/newheader9.ftl">
			<#elseif (topmap.temp.tempId==15)>
				<#include "../index/newheader10.ftl">
			<#elseif (topmap.temp.tempId==16)>
				<#include "../index/newheader11.ftl">
			<#elseif (topmap.temp.tempId==17)>
				<#include "../index/newheader12.ftl">
			<#elseif (topmap.temp.tempId==18)>
				<#include "../index/newheader13.ftl">
			<#elseif (topmap.temp.tempId==19)>
				<#include "../index/newheader14.ftl">
            <#elseif (topmap.temp.tempId==20)>
                <#include "../index/newheader15.ftl">
			<#else>
				<#include "../index/newheader3.ftl">
			</#if>
		</#if>
	<div class="container">
		 <div class="page_locate mt10">
        	<input type="hidden" class="bread_crumb_cat_id" value="${map.detailBean.productVo.goods.catId}" data-role="detail" />
        	<input type="hidden" class="first_catId" value="" />
        	<input type="hidden" class="index_url">
            &nbsp;&gt;&nbsp;
            <span>${map.detailBean.productVo.productName}</span>
        </div><!--/page_locate-->
        
        <div class="product_wp mt10 clearfix">
                <input type="hidden" id="goodsId" value="${map.detailBean.productVo.goodsId}" />
                <input type="hidden" id="productId" value="${map.detailBean.productVo.goodsInfoId}" />
                <input type="hidden" id="brandId" value="${map.detailBean.brand.brandId}" />
                <input type="hidden" id="catId" value="${map.detailBean.productVo.goods.catId}" />
                <input type="hidden" class="bread_crumb_cat_id" value="${map.detailBean.productVo.goods.catId}" data-role="detail" />
               
        	<div class="img_preview fl">
        		<div class="big_img">
        			<a class="cloud-zoom" id="zoom" rel="adjustX:10,adjustY:0" href="<#if map.detailBean.productVo.imageList?? && map.detailBean.productVo.imageList[0]??>${map.detailBean.productVo.imageList[0].imageArtworkName!''}</#if>">
                        <img alt="" src="<#if map.detailBean.productVo.imageList?? && map.detailBean.productVo.imageList[0]??>${map.detailBean.productVo.imageList[0].imageBigName!''}</#if>" width="350" height="350" />
                    </a>
        		</div>
        		 <div class="thumb_img clearfix mt10 pr">
                    <div class="thumb_scroll_wp">
                        <ul class="clearfix">
                            	<#if map.detailBean.productVo.imageList??>
	                    			<#list map.detailBean.productVo.imageList as image>
	                    				 <li <#if image_index==0>class="cur"</#if>>
		                    				<a class="cloud-zoom-gallery" href="${image.imageArtworkName!''}" rel="useZoom:'zoom',smallImage:'${image.imageBigName!''}'">
	                                    		<img alt="" src="${image.imageThumName!''}" width="50" height="50" />
	                               			 </a>
                               			 </li>
	                       			 </#list>
                       			</#if>
                        </ul>
                    </div><!--/thumb_scroll_wp-->
                    <a class="thumb_scroll_prev disabled" href="javascript:;"></a>
                    <a class="thumb_scroll_next disabled" href="javascript:;"></a>
               	 </div><!--/thumb_img-->
        		<div class="thumb_op mt15 clearfix pr">
        			<div class="share_wp" style="left:0px;">
                        <div class="bdsharebuttonbox bdshare-button-style1-16" data-bd-bind="1417684433620">
                            <span class="vm">分享到：</span>    
                            <a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
                            <a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
                            <a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
                            <a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
                            <a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
                            <a href="#" class="bds_kaixin001" data-cmd="kaixin001" title="分享到开心网"></a>
                            <a href="#" class="bds_taobao" data-cmd="taobao" title="分享到我的淘宝"></a>
                            <a href="#" class="bds_fbook" data-cmd="fbook" title="分享到Facebook"></a>
                            <a href="#" class="bds_twi" data-cmd="twi" title="分享到Twitter"></a>
                        </div>
                        <a class="more_share" href="javascript:;"></a>
						<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"1","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
						</script>
                    </div>
        		</div>
        	</div>
        	<div class="product_info fl ml20">
        		 <div class="pd_title">
                    <h2>${map.detailBean.productVo.productName!''}</h2>
                    <strong>${map.detailBean.productVo.subtitle!''}</strong>
                </div><!--/pd_title-->
                <div class="pss_two_cona" style="float:none;">
	               		<div class="pss_jp_price" style=" margin-top:15px;">
		                    <div class="pss_big_sale"><span class="f24">¥</span>${(map.detailBean.productVo.goodsInfoPreferPrice*market.rushs[0].rushDiscount)?string("0.00")}</div>
		                    <div class="pss_dazhe">
		                        <div class="pss_court_d">${market.rushs[0].rushDiscount}折</div>
		                        <p>¥${map.detailBean.productVo.goodsInfoPreferPrice?string("0.00")}</p>
		                    </div>
	                	</div>
	                <#--  <input type="hidden" value="${market.marketingEnd?string("yyyy-MM-dd HH:mm:ss")}" id="time1">
	              
	              	<div class="pss_remain_a pt10"><span><#if market.groupon.participateCount??>${market.groupon.participateCount}<#else>0</#if></span></span>人已购买</div>
	             	
	                <div class="pss_count_down mt10">
	                    <div id="count1">
	                     	  <span class="day"></span>
	                        <span class="hour">6</span>&nbsp;时&nbsp;
	                        <span class="minute">29</span>&nbsp;分&nbsp;
	                        <span class="second">39</span>秒
	                    </div>
	                </div>
	                <#if rushTime=="1">
							   	<input type="hidden" value="${market.marketingBegin?string("yyyy-MM-dd HH:mm:ss")}" id="time1">
	                          	<div class="pss_count_down mt10">
	                              <div id="count1">
	                                    <span class="status">距开始</span>
	                                    <span class="day"></span>
	                                    <span class="hour"></span>&nbsp;时&nbsp;
	                                    <span class="minute"></span>&nbsp;分&nbsp;
	                                    <span class="second"></span>秒
	                                </div>
	                            </div>
                            <#elseif  rushTime=="2">
                            	<input type="hidden" value="${market.marketingEnd?string("yyyy-MM-dd HH:mm:ss")}" id="time1">
	                            <div class="pss_count_down mt10">
	                              <div id="count1">
	                                    <span class="status">距结束</span>
	                                    <span class="day"></span>
	                                    <span class="hour"></span>&nbsp;时&nbsp;
	                                    <span class="minute"></span>&nbsp;分&nbsp;
	                                    <span class="second"></span>秒
	                                </div>
	                            </div>
                            <#elseif  rushTime=="3">
							    <div class="pss_count_down mt20">
	                              <div id="count1">
	                                    <span class="status">已结束</span>
	                                </div>
	                            </div>
                            </#if>--> 
                            <input type="hidden" value="${rushTime}" name="rushTime" id="rush1">
                            <input type="hidden" value="${market.marketingBegin?string("yyyy-MM-dd HH:mm:ss")}" id="timebegin1">
							   	<input type="hidden" value="${market.marketingEnd?string("yyyy-MM-dd HH:mm:ss")}" id="time1">
                             <div class="pss_count_down mt20">
	                              <div id="count1">
	                                    <span class="status"></span>
	                                    <span class="day"></span>
	                                    <span class="hour"></span>
	                                </div>
	                            </div>
                            
                </div>
                <div class="pss_pp">
                	<p>
                	<dl class="pd_info clearfix mt10">
                		 <dt>配&nbsp;送&nbsp;至：</dt>
	                    <dd>
	                        <div class="choose_area fl pr">
	                            <div class="area_text ">${map.chAddress}<b></b></div>
	                            <div class="locate_cont">
	                                <ul class="locate_tabs clearfix">
	                                    <li class="cur show_province"><a href="javascript:;"><span class="province_text">${map.chProvince}</span><b></b></a></li>
	                                    <li class="show_city" ><a href="javascript:;" ><span class="city_text">${map.chCity}</span><b></b></a></li>
	                                    <li class="show_distinct"><a href="javascript:;" ><span class="distinct_text">${map.chDistinct}</span><b></b></a></li>
	                                </ul><!--/locate_tabs-->
	                                <div class="locate_wp">
	                                    <ul class="locate_list clearfix province_list">
	                                    </ul><!--/locate_list-->
	                                    <ul class="locate_list clearfix city_list">
	                                    </ul><!--/locate_list-->
	                                    <ul class="locate_list clearfix distinct_list">
	                                    </ul><!--/locate_list-->
	                                </div><!--/locate_wp-->
	           </div><!--/locate_cont-->
	                        </div><!--/choose_area-->
	                        <div class="store-prompt fl ml10"><em>
	                        	<inpu type="hidden" id="goods_info_stock" value="${map.detailBean.productVo.goodsInfoStock}">
	                        	<#if (map.detailBean.productVo.goodsInfoStock>0)>
	                        		有货
	                        	<#else>
	                        		无货
	                        	</#if></em><!--11:00前完成下单，预计今天（6月27日）送达--></div>
	                    </dd>
	                    </dl>
                	</p>
                	<#if rushTime=="2">
                		<a href="javascript:;" class="pss_hot_buy buy_goods">立即抢购</a>
                	<#elseif rushTime=="1"||rushTime=="3">
                		<a href="javascript:;" class="pss_hot_buy yi_over">立即抢购</a>
                	</#if>
                </div>
        	</div>
        </div>
	</div>
	
	<div class="container">
		<div class="product_detail">
                    <div class="tit_wp">
                        <div class="det_title detail_nav clearfix">
                            <ul class="nav_tabs fl clearfix">
                                <li class="cur"><a href="javascript:;">商品介绍</a></li>
                                <li><a href="javascript:;">规格参数</a></li>
                                <li class="product_comment"><a href="javascript:;">商品评价</a></li>
                            </ul><!--/nav_tabs-->
                            <a class="add-cart-btn fr buy_goods" href="javascript:;">马上抢购！</a>
                        </div><!--/det_title-->
                    </div><!--/tit_wp-->

                    <div class="details_box">
                         <ul class="pro_introduce clearfix">
                           <li>商品名称：${map.detailBean.productVo.productName!''}</li>
                           <li>商品编号：${map.detailBean.productVo.goodsInfoItemNo}</li>
                           <li>店铺：<a href="javascript:;"><#if map.detailBean.productVo.isThird='0'>${topmap.systembase.bsetName!''}<#else>${map.detailBean.productVo.thirdName!''}</#if></a></li>
                           <li>时间：${map.detailBean.productVo.goodsInfoAddedTime?string("yyyy-MM-dd HH:mm:ss")}</li>
                           <#list map.detailBean.expandPrams as expandParam>
                            	<li>${expandParam.expandParamVo.expandparamName}：${expandParam.expangparamValue.expandparamValueName}</li>
                            </#list>
                        </ul><!--/pro_introduce-->
                        <div class="intro_cont">
                            <div class="detail_show">
                                ${(map.detailBean.brand.brandDesc)!''}
                            </div>
                            <div class="detail_show">
                                 ${map.detailBean.productVo.goods.goodsDetailDesc}
                            </div><!--/detail_show-->
                            <div class="det_explain customer_service1">
                                <strong>售后保障</strong>
                                <p>本商家商品保证正品行货，严格按照国家三包政策提供售后服务，因质量问题或实物与描述不符产生的退换货服务运费由本店承担。</p>
                            </div><!--/det_explain-->
                            <div class="det_explain service_promise">
                                <strong>服务承诺</strong>
                                <p>向您保证所售商品均为正品行货，与您亲临商场选购的商品享受相同的质量保证。本站为您提供具有竞争力的商品价格和服务保障，请放心购买！<br/>
注：因厂家会在没有任何提前通知的情况下更改产品包装、产地或者一些附件，本司不能确保客户收到的货物与商城图片、产地、附件说明完全一致。只能确保为原厂正货！并且保证与当时市场上同样主流新品一致。若本商城没有及时更新，请大家谅解！</p>
                            </div><!--/det_explain-->
                            <div class="det_explain res_statement">
                                <strong>责任声明</strong>
                                <p>所有商品信息、客户评价、商品咨询、网友讨论等内容，是商城重要的经营资源，未经许可，禁止非法转载使用。<br/>
注：本站商品信息均来自于厂商，其真实性、准确性和合法性由信息拥有者（厂商）负责。本站不提供任何保证，并不承担任何法律责任。</p>
                            </div><!--/det_explain-->
                        </div><!--/intro_cont-->
                    </div><!--/details_box-->

                    <div class="details_box">
                       <table class="parameter_tb w">
                            <tr>
                                <th colspan="2">详细参数</th>
                            </tr>
                            <#list map.detailBean.param as param>
	                            <tr>
	                                <td width="20%">${param.param.paramName}：</td>
	                                <td>${param.paramValue}</td>
	                            </tr>
                            </#list>
                            
                        </table><!--/parameter_tb-->
                    </div><!--/details_box-->

                    <div class="details_box">
                        <div class="evaluation_info clearfix mt10">
                            <div class="rate fl tc">
                                <strong><span class="bigHaoPercent">95<span><b>%</b></strong>
                                <span>好评度</span>
                            </div><!--/rate-->
                            <div class="percent fl ml20">
                                <dl class="per_info clearfix">
                                    <dt>好评<em class="per">(<span class="haoPercent"></span>)</em></dt>
                                    <dd><div class="per_bar"><span class="haoPercentLine"></span></div></dd>
                                </dl>
                                <dl class="per_info clearfix">
                                    <dt>中评<em class="per">(<span class="zhongPercent"></span>)</em></dt>
                                    <dd><div class="per_bar"><span class="zhongPercentLine"></span></div></dd>
                                </dl>
                                <dl class="per_info clearfix">
                                    <dt>差评<em class="per">(<span class="chaPercent"></span>)</em></dt>
                                    <dd><div class="per_bar"><span class="chaPercentLine"></span></div></dd>
                                </dl>
                            </div><!--/percent-->
                            <div class="recommend_point fl">
	                            <#if map.tags?size  &gt; 0>
	                              	  推荐点：
	                                <ul class="rec_points mt10 clearfix">
	                                	<#list map.tags as tag>
	                                    	<li>${tag.goodsTag.tagName}</li>
	                                    </#list>
	                                </ul><!--/rec_points-->
	                            </#if>
                            </div><!--/recommend_point-->
                            <div class="publish_cmt fl">
                                <p>您可对已购的商品进行评价</p>
                                <a class="pb_cmt pub_comment" target="_blank" href="${basePath}/customer/myorder.html">发表评论拿积分</a>
                                <p>1积分=1元&nbsp;&nbsp;<a class="cmt_rule" href="${basePath}/help/55">评论规则</a></p>
                            </div><!--/publish_cmt-->
                        </div><!--/evaluation_info-->

                        <div class="det_title mt10 clearfix">
                            <ul class="cmt_tabs fl clearfix">
                                <li class="cur"><a href="javascript:;" class="commentTab" data-role="3">全部评论（<span class="allCount"></span>）</a></li>
                                <li><a href="javascript:;" class="commentTab" data-role="0">好评（<span class="haoCount"></span>）</a></li>
                                <li><a href="javascript:;" class="commentTab" data-role="1">中评（<span class="zhongCount"></span>）</a></li>
                                <li><a href="javascript:;" class="commentTab" data-role="2">差评（<span class="chaCount"></span>）</a></li>
                            </ul><!--/cmt_tabs-->
                        </div><!--/det_title-->

                        <div class="comment_wp">
                            <div class="comment_cont" style="display:block">
                            	<div class="comment_list">
                            		<!-- 评论主体 -->
                            	</div>
                                <div class="clearfix mt10">
                                    <a class="all_cmt fl" target="_blank" href="${basePath}/comment/${map.detailBean.productVo.goodsInfoId}.html">[查看全部评价]</a>
                                    <div class="pages tr fr comment_pages">
                                    </div><!--/pages-->
                                </div>
                            </div><!--/comment_cont-->
                        </div><!--/comment_wp-->
                    </div><!--/details_box-->
                </div><!--/product_detail-->

	</div>
    
	<!-- 保存商品参数部分相关参数 -->
     <form id="paramGoodsForm" action="${map.detailBean.productVo.goodsInfoId}" method="post">
             <input  type="hidden" name="chAddress" class="ch_address" value="${map.chAddress}" />
             <input type="hidden" name="chProvince" class="ch_province" value="${map.chProvince}" />
             <input type="hidden" name="chCity" class="ch_city" value="${map.chCity}" />
             <input type="hidden" name="chDistinct" class="ch_distinct" value="${map.chDistinct}" />
             <input type="hidden" name="distinctId" class="ch_distinctId" value="${map.distinctId}"  />
             <input type="hidden" class="productStock" value="${map.detailBean.productVo.goodsInfoStock}" />
      </form>
      
      <form id="paramGrouponForm" method="post" action="${basePath}/order/submrorder.html">
      		<input type="hidden" name="distinctId"  value="${map.distinctId}"  />
      		<input type="hidden" name="productId" id="productId" value="${map.detailBean.productVo.goodsInfoId}" />
      </form>
        
    <div class="container">
        <!--这是底部-->
        <div class="pt30"><img src="${basePath}/images/foot.jpg"/></div>
    </div>
    <div class="dialog s_dia dia2">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" id="f_img" alt="" src="${basePath}/images/mod_war.png" />
            	<em id="con_00">库存不足！</em>
            </div>
            <div class="dia_ops mt20 tr">       
                 <a class="go_pay" id="go_pay_00" href="javascript:cls();">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
	<script type="text/javascript" src="${basePath}/js/jquery.lazyload.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/cloud-zoom.1.0.2.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/index.js"></script>
	<script>
	$(document).ready(function(){
		<!-- 加载规格值区域 -->
		var productList=null;
		$.get("${basePath}/all/"+$("#goodsId").val()+".html",function(data){
			productList=data;
			<!-- 把查询到的数据传递到js方法中 -->
			loadAllProduct(productList);
			loadChooseParam();
		});
	
		<!-- 加载商品促销的信息 -->
		loadGoodsMark();
	
		$(".pro_sort").addClass("pro_sort_close");
	});
	
	}
</script>

 <script type="text/javascript">

   $(function(){
        countDown($("#time1").val(),$("#timebegin1").val(),'#count1',$("#rush1").val());
        $('.buy_goods').click(function(){
			if($(".productStock").val()==0){
				$("#con_00").html("库存不足");
				dia(2);
			}else{
				$("#paramGrouponForm").submit();
			}
		});	
    });
    
	function countDown(dateend,datebegin,count,rush){
        var now = Date.parse(new Date());
        //alert(now);
        var date;
        if(rush==1){
        	date=datebegin;
        }else if(rush==2){
        	date=dateend;
        }else if(rush==3){
            date=dateend;
        }
        //var target = Date.parse(date);
        str =  date.replace(/-/g,"/");
        var target = Date.parse(new Date(str));
        var time = target - now;
        time = parseInt(time / 1000);
        var day = Math.floor(time / (60*60*24));
        time -= day * (60*60*24);
        var hour = Math.floor(time /(60*60));
        time -= hour * (60*60);
        var minute = Math.floor(time / 60);
        var second = time - (minute * 60);
        if(day==0&&hour==0&&minute==0&&second==0){
        	if(rush==1){
        		rush= 2;
        		$(count).parents(".pss_two_cona").next().find(".pss_hot_buy").removeClass("yi_over");
        		$(count).parents(".pss_two_cona").next().find(".pss_hot_buy").addClass("buy_goods");
        		countDown(dateend,datebegin,count,rush);
        		return;
        	}else if(rush==2){
        		 rush=3;
        		$(count).parents(".pss_two_cona").next().find(".pss_hot_buy").removeClass("buy_goods");
        		$(count).parents(".pss_two_cona").next().find(".pss_hot_buy").addClass("yi_over");
        		$(count).find('.hour').html("");
        		 countDown(dateend,datebegin,count,rush);
        		 return;
        	}
        }else{
         	if(rush==1){
	        	$(count).find('.status').html("距开始");
	        	$(count).find('.hour').html(hour+"&nbsp;时"+minute+"&nbsp;分"+second+"&nbsp;秒");
	        }else if(rush==2){
	        	$(count).find('.status').html("距结束");
	        	$(count).find('.hour').html(hour+"&nbsp;时"+minute+"&nbsp;分"+second+"&nbsp;秒");
	        }else if(rush==3){
	        	$(count).find('.status').html("已结束");
	        }
	        if(day>0){
		        $(count).find('.day').html(day+"&nbsp;天");
	        }
		window.setTimeout(function(){countDown(dateend,datebegin,count,rush);},1000);
        }
    }
    
 </script>

	<script type="text/javascript" src="${basePath}/js/goods/goods_compare.js"></script>
	<script type="text/javascript" src="${basePath}/js/goods/goods_comm.js"></script>
	<script type="text/javascript" src="${basePath}/js/goods/new_list_common.js"></script>
	<script type="text/javascript" src="${basePath}/js/goods/new_goods_detail.js"></script>
</body>
</html>