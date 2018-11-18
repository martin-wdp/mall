<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城第三方后台-商品复制</title>
<#assign basePath=request.contextPath />
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/third.css" />
<script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${basePath}/js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="${basePath}/css/ztree/zTreeStyle.css">
<script type="text/javascript" src="${basePath}/js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${basePath}/js/third.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/thirdGoodsList.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/thirdgoodsimport.js"></script>
</head>
<body>
	<#-- 引入头部 -->
    <#import "../common/head.ftl" as head>
	<@head.head '${basePath}' />
    
    <div class="container clearfix">
    	<div class="t_menu fl">
        	<#-- 引入左侧菜单 -->
        	<#import "../common/leftmenu.ftl" as leftmenu>
			<@leftmenu.leftmenu '${basePath}' />
        </div><!--/t_menu-->
        
        <div class="wrapper fr">
        	<div class="location">
            	您所在的位置
                <span>&gt;</span>
                <a href="thirdgoodsmanager.htm?n=3&l=27">商品管理</a>
                <span>&gt;</span>
                <a href="javascript:;">商品复制</a>
            </div><!--/location-->
			<ul class="iq_tabs clearfix mt10">
            	<li class="cur"><a href="javascript:;">查询</a></li>
                <li><a href="javascript:;">高级查询</a></li>
            </ul><!--/iq_tabs-->
            <div class="iqy_wp">
            	<form class="simple_search" action="${map.pb.url}" method="post">
	            	<div class="iqy_cont">
	            	<style type="text/css">
					.business_filter{margin-bottom:10px;line-height:30px;}
					.business_filter select{padding:5px;border:1px solid #DADADA;}
					</style>
                	<div class="business_filter">
                    	<label>所属商家：</label>
                        <select name="thirdId">
                        	<option value="">请选择</option>
                            <option value="0" <#if map.thirdId??&&map.thirdId==0>selected=selected</#if>>BOSS</option>
                            <#if map.storeList??>
                          	<#list map.storeList as store>
	                            <option value="${store.customerid}" <#if map.thirdId??&&map.thirdId==store.customerid>selected=selected</#if>>${store.storeName}</option>
                          	</#list>
                          	</#if>
                        </select>
                    </div>
	                	<div class="iqy_box">
	                    	<div class="search_sel">
	                            <span class="iqy_val"></span>
	                            <select class="iqy_sel" name="condition">
	                                <option <#if map.searchBean??><#if map.searchBean.condition?? && map.searchBean.condition == "1">selected==selected</#if></#if> value="1">商品名称</option>
	                                <option <#if map.searchBean??><#if map.searchBean.condition?? && map.searchBean.condition == "2">selected==selected</#if></#if> value="2">商品编码</option>
	                            </select>
	                        </div><!--/search_sel-->
	                        <input class="search_word" name="searchText" value="<#if map.searchBean?? && map.searchBean.searchText??>${map.searchBean.searchText}</#if>" type="text" />
	                    </div><!--/iqy_box-->
	                    <input type="hidden" name="showFlag" value="1" />
	                    <div class="tc mt20"><input class="query_btn" type="submit" value="查询" /></div>
	                </div><!--/iqy_cont-->
                </form>
                <form class="high_search" action="${map.pb.url}" method="post">
                	<input type="hidden" name="showFlag" value="2" />
                	
                <div class="iqy_cont" >
                	<table class="iqy_tb w">
                    	<tr>
                        	<td>
                            	商品名称：
  								<input class="iqy_text"  value="<#if map.searchBean?? && map.searchBean.goodsName??>${map.searchBean.goodsName}</#if>" type="text" name="goodsName" />        	
                            </td>
                            <td>
                            	商品编码：
                                <input class="iqy_text" value="<#if map.searchBean?? && map.searchBean.goodsNo??>${map.searchBean.goodsNo}</#if>" type="text" name="goodsNo" />
                            </td>
                            <td>
                            	宁派分类：
                                <input class="iqy_text np_cate_name" readonly=readonly type="text" />
                                <input type="hidden" name="npCateId" class="np_cate_id" value="<#if map.searchBean?? && map.searchBean.npCateId??>${map.searchBean.npCateId}</#if>"  />
		                        <a class="st_choose" onclick="javascript:showChNpCate();" href="javascript:;">选择</a>
		                        <div id="menuContent" class="npmenuContent" style=" border:1px solid #ccc;width:150px;height:300px;overflow:scroll;scroll-y;display:none;position: absolute; background-color:#fcfcfc;">
									<ul id="nptreeDemo" class="ztree">
								</ul>
							</div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            	宁派价格：
                                <input class="iqy_text s_txt" value="<#if map.searchBean?? && map.searchBean.lowPrice??>${map.searchBean.lowPrice}</#if>" type="text" name="lowPrice" />
                                ~
                                <input class="iqy_text s_txt" value="<#if map.searchBean?? && map.searchBean.highPrice??>${map.searchBean.highPrice}</#if>" type="text" name="highPrice" />
                            </td>
                            <td>
                            	上架时间：
                                <input class="Wdate iqy_text m_txt" style="" value="<#if map.searchBean?? && map.searchBean.lowCreateTime??>${map.searchBean.lowCreateTime}</#if>"  type="text" name="lowCreateTime" id="sTime"  onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
                              	  至
                                <input class="Wdate iqy_text m_txt" style="" value="<#if map.searchBean?? && map.searchBean.highCreateTime??>${map.searchBean.highCreateTime}</#if>"   type="text" name="highCreateTime" id="sTime"  onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
                            </td>
                            <td>
                            	店内分类：
                                <input class="iqy_text third_cate_name" readonly=readonly type="text" />
                                <input type="hidden" name="thirdCateId" class="third_cate_id" value="<#if map.searchBean?? && map.searchBean.thirdCateId??>${map.searchBean.thirdCateId}</#if>"  />
		                        <a class="st_choose" onclick="javascript:showChThirdCate();" href="javascript:;">选择</a>
		                        <div id="thirdmenuContent" class="thirdmenuContent" style=" border:1px solid #ccc;width:150px;height:300px;overflow:scroll;scroll-y;display:none;position: absolute; background-color:#fcfcfc;">
									<ul id="thirdtreeDemo" class="ztree">
								</ul>
                            </td>
                        </tr>
                    </table><!--/iqy_tb-->
                    <div class="mt10 tc">
                    	<input class="query_btn" type="submit" value="查询" />
                        <input class="rst_btn" type="reset" value="重置" />
                    </div>
                </div><!--/iqy_cont-->
            </form>
            </div><!--/iqy_wp-->
            
            <div class="tr mt20">
            	<a class="all_off copy_off" href="javascript:;">复制已选商品</a>
            </div>
	            <table class="pr_tb w mt10">
	                <thead>
	                    <tr>
	                        <th width="5%"></th>
	                        <th>序号</th>
	                        <th>图片</th>
	                        <th width="20%">宝贝名称</th>
	                        <th>商品编码</th>
	                        <th>价格</th>
	                        <th width="10%">发布时间</th>
	                        <th>所属商家</th>
	                    </tr>
	                </thead>
	                <#if (map.pb.list?size) &gt; 0 >
	                	<form class="list_form" method="post">
			                <tbody>
			                	<#list map.pb.list as goods>
				                    <tr>
				                        <td><input type="checkbox" value="${goods.goodsId}" name="thirdGoodsId" class="ch_goods" /></td>
				                        <td>${goods_index+1}</td>
				                        <td>
				                        	<a href="javascript:;">
				                        		<#if goods.goodsImg??>
				                        			<#list goods.goodsImg?split(",") as img>
				                        				<img width='50px' height="50px" alt="" src="<#if img??>${img}</#if>" />
				                        				<#break>
				                        			</#list>
				                        		</#if>
				                        	</a>
				                        </td>
				                        <td class="pr_name"><a href="javascript:;">${goods.goodsName}</a></td>
				                        <td>${goods.goodsNo}</td>
				                        <td>¥<span class="pr_price">#{goods.goodsPrice;m2M2}</span></td>
				                        <td>${(goods.goodsCreateTime)!""}</td>
				                        <td>
				                        	${(goods.thirdName)!""}
				                        </td>
				                    </tr>
			                    </#list>
			                </tbody>
		                </form>
		                <tfoot>
		                    <tr>
		                        <td colspan="2"><label style="margin-left:7px;"><input class="vm mr5"  onclick="selectAll(this,'thirdGoodsId')" type="checkbox" />全选</label></td>
		                        <td class="pg_td" colspan="7" style="text-align:right;">
		                            <a class="p_prev" onclick="changePageNo(this);" data-role="${map.pb.prePageNo}" href="javascript:;">&nbsp;</a>
		                            <#if (map.pb.startNo?number>1)> 
		 							 	<a href="javascript:void(0);" onclick="changePageNo(this);" data-role="1">1</a> ...
		 							</#if>
		                            <#list map.pb.startNo?number .. map.pb.endNo as item>
		                          	  <a <#if item == map.pb.pageNo>class="cur" style="background:#3994f6;color:#fff"</#if> onclick="changePageNo(this);" data-role="${item}"  href="javascript:;">${item}</a>
		                          	</#list>
		                          	<#if (map.pb.totalPages?number>map.pb.endNo)>
			 							 	 ...<a href="javascript:void(0);" onclick="changePageNo(this);" data-role="${map.pb.totalPages}">${pageBean.totalPages}</a>
			 							  
			 				    	</#if>
		                            <a class="p_next" onclick="changePageNo(this);" data-role="${map.pb.nextPageNo}" href="javascript:;">&nbsp;</a>
		                        </td>
		                    </tr>
		                </tfoot>
		            <#else>
           				 <td colspan="8"><center>暂无相关商品信息</center></td>
          			</#if>
	            </table><!--/pr_tb-->
        </div><!--/wrapper-->
    </div><!--/container-->
    
    
    <div class="dialog s_dia dia2" style="width: 300px; height: 150px; ">
		    <div class="sd_tit clearfix"><h3 class="fl">操作提示</h3>
		    	<a class="sd_close fr" href="javascript:;" onclick="cls()"></a>
		    </div>
		    <div class="pmt_wp" style="height: 50px;">
		    	<p class="tc f14 show_title" style="line-height:70px;"></p>
		    </div>
		    <div class="tc mt20">
		    	<a class="sop_btn" href="javascript:;" onclick="cls()">确定</a>
		    </div>
		 </form>
    </div>
    
    
    
    <!-- 保存查询方式  -->
    <#if map.searchBean?? && map.searchBean.showFlag??>
    	<input type="hidden" class="show_flag" value="${map.searchBean.showFlag}"/>
    </#if>
    <script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
	<#import "../common/selectindex.ftl" as selectindex>
	<@selectindex.selectindex "${n!''}" "${l!''}" />
	<script>
		$(function(){
			var showFlag = $(".show_flag").val();
			if(showFlag == 1){
				$(".iq_tabs").find("li:last").removeClass("cur");
				$(".iqy_wp").find(".iqy_cont:last").hide().removeClass("show");
				$(".iq_tabs").find("li:first").addClass("cur");
				$(".iqy_wp").find(".iqy_cont:first").fadeIn('slow').addClass("show");
			}else{
				$(".iq_tabs").find("li:first").removeClass("cur");
				$(".iqy_wp").find(".iqy_cont:first").hide().removeClass("show");
				$(".iq_tabs").find("li:last").addClass("cur");
				$(".iqy_wp").find(".iqy_cont:last").fadeIn('slow').addClass("show");
			}
			
            /*点击复制商品的时候*/
            $(".copy_off").click(function(){
                var ch_goods = $(".ch_goods");
                var bool = 0;
                for(var i = 0;i<ch_goods.length;i++){
                    if($(ch_goods[i]).prop("checked")){
                        bool=bool-1+2;
                    }
                }
                if(bool>0){
                    $(".list_form").attr("action","copygoodsbygoodsids.html").submit();
                }else{
                    $(".show_title").text("请至少选择一行进行操作!");
                    dia(2);
                }
            });
		});

        /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
        function show(){
            $(".show_text").fadeOut(1000).fadeIn(1000);
        }
        setTimeout("show()",1000);
	</script>
</body>
</html>
