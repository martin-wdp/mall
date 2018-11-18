<!doctype html>
<html lang="en">
<head>
	<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>第三方店铺首页</title>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/store.style.css">
</head>
<body>
<input type="hidden" id="basePath" value="${basePath}"/>
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
<#include "newStoreHead.ftl">
<div id="slides" class="slides">
	<#if map.channelAdvs??>
		<#list map.channelAdvs as adv>
	    <a href="${(adv.adverHref)!''}" style="background-image:url(${(adv.adverPath)!''}) "></a>
	    </#list>
    </#if>
</div>

<div class="redempbox clearfix">
    <div class="comwidth">
        <div class="redemption clearfix ">
            <#--<ul>
                <li><a><img src="${basePath}/images/img001.jpg" width="238" height="62"/></a></li>
                <li><a href="#"><img src="${basePath}/images/img002.jpg" width="220" height="62"/></a></li>
                <li><a href="#"><img src="${basePath}/images/img003.jpg" width="220" height="62"/></a></li>
                <li><a href="#"><img src="${basePath}/images/img004.jpg" width="220" height="62"/></a></li>
            </ul>-->
        </div>
    </div>
</div>

<div class="clearfix avtivevalbox mt30">
    <div class="comwidth">
        <div class="shopredemp clearfix">
        <#if map.pageAdvs??>
            <ul>
            <#list map.pageAdvs as sadv>
	            <#if sadv.adverSort==2>
	                <li class="shopredemplif1"><a href="${(sadv.adverHref)!'#'}"><img src="${(sadv.adverPath)!''}" width="275" height="170"><h1>${(sadv.adverName)!''}</h1><p>${(sadv.temp2)!''}</p></a></li>
	            </#if>
            	<#if (sadv.adverSort>= 3) && (sadv.adverSort<=5)>
                <li><a href="${(sadv.adverHref)!''}"><img src="${(sadv.adverPath)!'#'}" width="275" height="170"><h1>${(sadv.adverName)!''}</h1><p>${(sadv.temp2)!''}</p></a></li>
                </#if>
                </#list>
            </ul>
        </#if>
        </div>
    </div>
</div>

<div class="shopfenleibox clearfix">
    <div class="comwidth ">
        <div class="shopfenlei clearfix">
        <#if map.pageAdvs??>
            <ul>
            <#list map.pageAdvs as sadv>
            <#if (sadv.adverSort== 6) >
                <li class="shopfenleili1"><a  href="${(sadv.adverHref)!'#'}"><img src="${(sadv.adverPath)!''}" width="128" height="174"/></a></li>
            </#if>
            <#if (sadv.adverSort>= 7) &&(sadv.adverSort<= 13) >
                <li><a href="${(sadv.adverHref)!'#'}"><img src="${(sadv.adverPath)!''}" width="128" height="174"/></a></li>
            </#if>
           	</#list>
            </ul>
        </#if>
        </div>
    </div>
</div>

 <#if (map.floor.floorList)?? && (map.floor.floorList?size > 0)>
 <#list map.floor.floorList as floors>
<div class="shoplistbox01 clearfix">
    <div class="comwidth">
        <div class="shopfloortitle clearfix">
            <ul>
                <li class="shopline"></li>
                <li class="shoptitletxt"><strong>${floors.storeyName!''}</strong></li>
                <li class="shopline"></li>
            </ul>
        </div>
        <div>
            <a href="${floors.storeyImgHref}"><img src="${floors.storeyImg}" width="1200" height="300" style="display: block;"/></a>
        </div>

		<#if (floors.indexGoodsList)?? &&(floors.indexGoodsList?size > 0)>
        <div class="shoplistlist">
        <#list floors.indexGoodsList as goods>
	        <#if (goods_index>=0) && (goods_index<8)>
	            <li>
	                <a class="shopimgs" href="${basePath}/item/${goods.id}.html" target="_blank"><img src="${(goods.urlpic)}" width="260" height="260" style="display: block;"/></a>
	                <p class="shopnames"><a href="${basePath}/item/${goods.id}.html" target="_blank" title="${(goods.name)!''}">
	                		<#if goods.name??>
	                			<#if goods.name?length gt 16>
	                				${goods.name?substring(0,16)}
	                			<#else>
	                				${(goods.name)!''}               			
	                			</#if>
	                		</#if></a></p>
	                <p class="shopprice">&yen;<strong>${goods.price}</strong></p>
	                <a class="listbuy" href="${basePath}/item/${goods.id}.html" target="_blank">立即抢购</a>
	            </li>
	       </#if>
       </#list>
        </div>
        </#if>
    </div>
</div>
</#list>
</#if>
<#--要跟上面的div留一些空隙-->
<div style="height: 6px;"></div>
<div class=" clearfix comwidth">
    <div class="page_left fl ">
        <div class="clearfix">
            <form action="${basePath}/thirdstoreindex/${map.thirdId}.html"  id="searchForm" method = "POST" >
            	<input type="hidden" name="pageNo" class="pageNo" value="${map.pb.pageNo}">
               	<input type="hidden" name="sort" class="list_sort" value="${map.search.sort!''}">
               	<input type="hidden" name="showStock" class="show_stock" autocomplete="off" value="${map.search.showStock!''}">
                <input type="hidden" name="top" id="top"  value="${map.top!''}">
        		<input type="hidden" name="thirdId" id="thirdId"  value="${map.thirdId!''}">
        		<input type="hidden" name="cateId" id="cateId"  value="${map.search.cateId!''}">
        		<input type="hidden" name="parentCatId" id="parentCatId" value="">
                <input class="bottom_input fl" type="text" id="title" name="title" value="${(map.search.title)!''}" placeholder="请输入关键词" />
                <button class="bottom_search fl" id="sub" type="button">搜索</button>
            </form>
        </div>
        <div class="page_topline"></div>
        <#if map.thirdCate??>
        <#assign indexs=0>
        <#list map.thirdCate as cate>
        <div class="sub_cate">
            <div class="fir_level">
            	<#assign indexs=indexs?number+1>
                <a href="javascript:void(0);"  onclick="searchone(${map.thirdId},${cate.catId});" >${cate.catName}</a>
            </div>
            <#if cate.cateVos??>
            <#list cate.cateVos as cat>
            <#assign indexs=indexs?number+1>
            <ul class="clearfix">
                <li class="sub_two <#if (map.nowcate.catGrade==1 || map.nowcate.catGrade==0) && indexs==2>open" 
                <#elseif map.nowcate??&& map.nowcate.catId!=0 &&(cat.catId==map.nowcate.catId || cat.catId==map.nowcate.catParentId  )>open"<#else>close"</#if>>
                    <a href="javascript:void(0);" title="${cat.catName}" onclick="searchtwo(${map.thirdId},${cat.catId},${cate.catId});">${cat.catName}</a>
                </li>
                <ul class="sub_third clearfix" <#if (map.nowcate.catGrade==1 || map.nowcate.catGrade==0) && indexs==2><#elseif map.nowcate??&& map.nowcate.catId!=0 &&(cat.catId==map.nowcate.catId || cat.catId==map.nowcate.catParentId  )><#else> style="display: none;"</#if>>
                	<#if cate.cateVos??>
	                	<#list cat.cateVos as ca>
	                    <li><a href="javascript:void(0);" title="${ca.catName}" onclick="searchtwo(${map.thirdId},${ca.catId},${cat.catId});">${ca.catName}</a></li>
	                    </#list>
                    </#if>
                </ul>
            </ul><!--clearfix-->
            </#list>
            </#if>
        </div><!--sub_cate--> 
    </#list>
    </#if>       
    </div><!--page_left-->

    <div class="page_right fr  operation_bar">
        <div class="operation_wp clearfix">
            <div class="sorting_box fl">
                <em>排序：</em>
                <a val="2D" attr="${map.search.sort}" class="change_sort <#if map.search.sort='22D'>s_up<#elseif map.search.sort='2D'>s_down</#if>  <#if map.search.sort='22D' || map.search.sort='2D'>checked</#if> " href="javascript:;"><span>销量</span></a>
                <a val="1D" attr="${map.search.sort}" class="change_sort <#if map.search.sort='11D'>s_up <#elseif map.search.sort='1D'>s_down</#if> <#if map.search.sort='11D' || map.search.sort='1D'>checked</#if> " href="javascript:;"><span>价格</span></a>
                <a val="4D" attr="${map.search.sort}" class="change_sort <#if map.search.sort='44D'>s_up <#elseif map.search.sort='4D'>s_down</#if> <#if map.search.sort='44D' || map.search.sort='4D'>checked</#if>" href="javascript:;"><span>人气</span></a>
                <a val="3D" attr="${map.search.sort}" class="change_sort <#if map.search.sort='33D'>s_up <#elseif map.search.sort='3D'>s_down</#if> <#if map.search.sort='33D' || map.search.sort='3D'>checked</#if>" href="javascript:;"><span>上架时间</span></a>
            </div><!--/sorting_box-->
            <div class="op_pages fr">
                <span class="mr10"><b>${map.pb.pageNo}</b>/${map.pb.totalPages}</span>
                <#if (map.pb.pageNo==1)>
                <a class="op_prev no_pages" href="javascript:;">上一页</a>
                <#else>
                <a class="op_prev" pages="${map.pb.prePageNo}"  href="javascript:;" onclick="prevpage(${map.pb.prePageNo});">上一页</a>
                </#if>
                <#if (map.pb.lastPageNo > map.pb.pageNo)>
                <a class="op_next  changePages" pages="${map.pb.nextPageNo}" href="javascript:void(0);" onclick="nextpage(${map.pb.nextPageNo})">下一页</a>
                <#else>
                <a class="op_next no_pages"  href="javascript:void(0);" >下一页</a>
                </#if>
            </div><!--/op_pages-->
            <span class="goods_num fr">共<b>${map.pb.rows}</b>个商品</span>
        </div><!--/operation_wp-->
        <div class="goodsTip ml15">
            <label class="m_check mr20 <#if map.search.showStock='1'>checked</#if> "><input class="vm mr5 check_show_stock" type="checkbox">仅显示有货</label>
            <!--<label class="m_check mr20"><input class="vm mr5" type="checkbox" />自营商品</label>-->
        </div><!--/goodsTip-->
        <div class="goods_wp mt20">
            <ul class="goodsList clearfix">
            <#if (map.pb.data)?? && (map.pb.data?size>0)>
            	<#list map.pb.data as product>
                <li class="goodsBox">
                    <div class="gd_wp">
                        <div class="g-img">
                            <a target="_blank" href="${basePath}/item/${product.goodsInfoId}.html" alt="${product.productName!''}" title="${product.productName!''}"><img class="lazy" alt="${product.productName!''}" title="${product.productName!''}" data-original="<#if  product.imageList?? && product.imageList?size &gt; 0 >${product.imageList[0].imageBigName!''}</#if>" width="226" height="226" src="" style="display: inline;"></a>
                        </div><!--/g-img-->
                        <div class="g-scroll  clearfix">
                            <a class="g-scroll-prev disabled" href="javascript:;" style="display: none;"></a>
                            <!--/g-scroll-wrap-->
                            <a class="g-scroll-next" href="javascript:;" style="display: none;"></a>
                        </div><!--/g-scroll-->
                        <p class="g-name mt5"><a target="_blank" href="${basePath}/item/${product.goodsInfoId}.html" alt="${product.productName!''}" title="A${product.productName!''}">${product.productName!''}</a></p>
                        <div class="clearfix mt10 lh200">
                            <span class="g-price fl">¥ ${product.goodsInfoPreferPrice?string('0.00')}</span>
                            <#if product.isThird??&&product.isThird ==1>
                            
                            <span class="<#if product.goodsInfoStockThird &gt;0><#else>no-goods</#if> fr">
                            <#if product.goodsInfoStockThird &gt;0>
                            	有货
                           	<#else>
                           		无货
                           	</#if>
                            </span>
                            <#else>
                            <span class="<#if product.goodsInfoStock &gt;0><#else>no-goods</#if> fr">
	                         <#if product.goodsInfoStock &gt;0>有货<#else>无货</#if>
	                        </span>
                            
                            </#if>
                        </div>
                        <div class="good-operation tc mt15">
                            <a class="add_shop_cart" product_id="${product.goodsInfoId}" product_stock="${product.goodsInfoStock}" distinct_id="${(map.distinctId)!''}" href="javascript:;">加入购物车</a>
                            <a class="cllect_btn" product_id="${product.goodsInfoId}" href="javascript:;">收藏</a>
                            <a href="javascript:;"><label class="m_check compare" id="compare${product.goodsInfoId}" product_id="${product.goodsInfoId}">对比</label></a>
                        </div><!--/good-operation-->
                    </div><!--/gd_wp-->
                </li><!--/goodsBox-->
         		</#list>
		    </#if>
            </ul><!--/goodsList-->
			
            <div class="pages mt10 tr">
                    		<#if ((map.pb.pageNo-2)>0)>
									<#assign pageNo="${map.pb.pageNo-2}" />
								<#else>
									<#assign pageNo="${map.pb.firstPageNo}" />
								</#if>
								<#if ((map.pb.lastPageNo-1)>0)>
									<#assign endNo="${map.pb.lastPageNo-2}" />
								<#else>
									<#assign endNo="1" />
								</#if>
	                        	<#if (map.pb.pageNo==1)>
			                        <a class="pg_prev no_pages" href="javascript:;">上一页</a>
			                    <#else>
			                    	<a class="pg_prev changePages" pages="${map.pb.prePageNo}" href="javascript:;">上一页</a>
			                    </#if>
			                    
			                    <#list pageNo?number .. map.pb.endNo as item>
										<#if (item_index<=4)>
							               <#if (item=map.pb.pageNo)>
							               		<a class="cur" href="javascript:;">${item}</a>
							               <#else>
							               		<a class="changePages" pages="${item}" href="javascript:;">${item}</a>
							               </#if>
					            		 </#if>
								</#list>
								<#if map.pb.pageNo!=map.pb.lastPageNo>
								     <#if ((map.pb.lastPageNo-map.pb.pageNo)>3) >
								     	<#if (map.pb.lastPageNo>5)>
								           ...
								        </#if>
								     </#if>
							    </#if>
							     <#if (map.pb.pageNo == map.pb.lastPageNo || map.pb.endNo <= 1)>
							    	 <#if (map.pb.lastPageNo > map.pb.pageNo)>
							    	 	<a class="cur" href="javascript:;">${map.pb.lastPageNo}</a>
							         </#if>
							         	<a class="pg_next no_pages" href="javascript:void(0);">下一页</a>
								<#else>
									 <#if ((map.pb.lastPageNo - map.pb.pageNo)>=3)>
									 	<#if (map.pb.lastPageNo>5)>
									 		<a class="pg_next changePages" pages="${map.pb.lastPageNo}" href="javascript:;">${map.pb.lastPageNo}</a>
									 	</#if>
									 </#if>
									 <a class="pg_next changePages"  pages="${map.pb.nextPageNo}"  href="javascript:void(0);">下一页</a>
								</#if>
                  	  </div><!--/pages-->
        </div><!--/goods_wp-->
    </div><!--/page_right-->
</div><!--comwidth-->
<#include "../goods/compare_box.ftl"/>
<#include "../infotips.ftl">	
    <div class="side_tools">
        <a class="backtotop" href="javascript:;"><em>返回顶部</em><b></b></a>
    </div><!--/side_tools-->
<#include "newStoreBottom.ftl"/>
<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.slides.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/goods_compare.js"></script>
<script type="text/javascript" src="${basePath}/js/index.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/new_list_common.js"></script>
<script>

        //页面加载查询 商家设置的功能路径
        var thirdstoreurl;
        var a = false;
        $(document).ready(function(){
            $(document).scrollTop(parseFloat("${map.top}"));
            if($("#slides").length > 0 && $("#slides a").length > 1) {
                $("#slides").slidesjs({
                    play: {
                        active: false,
                        effect: "fade",
                        auto: true,
                        interval: 4000,
                        pauseOnHover: true,
                        restartDelay: 2500
                    },
                    navigation: {
                        active: false
                    },
                    pagination: {
                        active: true,
                        effect: "fade"
                    }
                });
            };

            $(".pro_sort").addClass("pro_sort_close");
            $.ajax({
                url:$('#basePath').val()+"/getThirdStoreURL.htm",
                success:function(data){
                    thirdstoreurl = data;
                },
                async: false
            });
            $.ajax({
                url:$('#basePath').val()+"/checkThirdIndex.htm?thirdId="+$('#thirdId').val(),
                success:function(data){
                    a = data.state;
                },
                async: false
            });
            if(!a){
                window.location.href=$('#basePath').val()+'/thirdstoreindexfalse.htm?thirdId='+$('#thirdId').val();
            }
        });

</script>
<script>

    //幻灯
    if($("#slides").length > 0) {
        $("#slides").slidesjs({
            play: {
                active: false,
                effect: "fade",
                auto: true,
                interval: 4000,
                pauseOnHover: true,
                restartDelay: 2500
            },
            navigation: {
                active: false
            },
            pagination: {
                active: true,
                effect: "fade"
            }
        });
    };
    //page_left  open close
    $(".sub_cate").each(function(){
        $(this).find(".sub_two").last().css(" ")
    })

    $('.sub_cate .sub_two').click(function(){
        if($(this).hasClass("open")){
            $(this).removeClass('open');
            $(this).prepend().addClass('close');
            $(this).addClass('close').next().hide();
        }
        else{
            $(this).removeClass('open').addClass('close').next().hide();
            $(this).removeClass('close');
            $(this).addClass('open');
            $(this).prepend().removeClass('close');
            $(this).next().show();

        }
    });
    
    		function searchone(thirdId,cateId){
					$("#thirdId").val(thirdId);
					$("#cateId").val(cateId);

	            var h=$(document).scrollTop();
	            $("#top").val(h);
					$("#searchForm").submit();
		
			}
			
			
			function searchtwo(thirdId,cateId,catId){
					$("#thirdId").val(thirdId);
					$("#cateId").val(cateId);
					$("#parentCatId").val(catId);
                   var h=$(document).scrollTop();
                    $("#top").val(h);
					$("#searchForm").submit();
			}
			
			function nextpage(pageNo){
				$(".pageNo").val(pageNo);
				$("#searchForm").submit();
			}
			
			function prevpage(pageNo){
				$(".pageNo").val(pageNo);
				$("#searchForm").submit();
			}
			
			
			$("#sub").click(function(){
			$("#searchForm").submit();
			});
			
			function searchTitleHead(obj){
				$("#title").val($(obj).val());
				$("#searchForm").submit();
			}

</script>
</body>
</html>
