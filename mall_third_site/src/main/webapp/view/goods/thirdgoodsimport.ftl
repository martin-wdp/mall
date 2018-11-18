<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城第三方后台-商品导入</title>
<#assign basePath=request.contextPath>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/third.css" />
<script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${basePath}/js/third.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/thirdgoodsgroup.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/goods_vali.js"></script>
<script type="text/javascript" src="${basePath}/js/ztree/jquery.ztree.core-3.5.js"></script>
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
                <a href="javascript:;">商品导入</a>
            </div><!--/location-->
            
            <div class="group_wp">
                <div class="inquiry_wp mt10">
                	<div class="c_tit clearfix">
                    	<h3 class="fl">查询</h3>
                    </div><!--/c_tit-->
                    <!--进行商品名称的查询-->
                    <form  class="simple_search_goods" action="thirdImport.htm" method="post">
	                    <div class="inquiry_box">
	                        <div class="iqy_cont" style="display:block;">
	                            <div class="iqy_box">
	                                <div class="search_sel">
	                                    <span class="iqy_val"></span>
	                                    <select class="iqy_sel">
	                                        <option>商品名称</option>
	                                    </select>
	                                </div><!--/search_sel-->
	                                <input class="search_word" value="${map.selectBean.searchText!''}" type="text" 	name="searchText"/>
	                            </div><!--/iqy_box-->
	                            <div class="tc mt20"><input class="query_btn" type="submit" value="查询" /></div>
	                        </div><!--/iqy_cont-->
	                        </div><!--/inquiry_box-->
	                    </div><!--/inquiry_wp-->
                	</form>
                <div class="mt20 clearfix">
                <div class="pr_ops fl">
                    <a href="javascript:batchDelete();">批量删除</a>
                    <a href="javascript:downloadTemplate();">下载模板</a>
                    <a href="javascript:goodsImport();">商品导入</a>
                </div>
                <form id="batchdeleteform" action="thirdBatchDelImport.htm" method="post" >
                <table class="pr_tb w mt10">
                    <thead>
                        <tr class="ui-widget-header tableListTr" style="width: 100%;">
                        	<th class="tc" style="width:50px;">
								
							</th>
							<th class="tc">
								序号
							</th>
							<th class="tc">
								商品名称
							</th>
							<th class="tc">
								商品副标题
							</th>
							<th class="tc">
								销售价格
							</th>
							<th class="tc">
								成本价格
							</th>
							<th class="tc">
								市场价格
							</th>
							<th class="tc">
								SEO标题
							</th>
							<th class="tc">
								SEO关键字
							</th>
							<th class="tc">
								SEO详细
							</th>
							<th class="tc">
								所属商家
							</th>
							<th class="tc">
								发布状态
							</th>
						</tr>
                	</thead>
                	<#if (map.pageBean.list?size) &gt; 0 >
	                   	<tbody>
							<#list map.pageBean.list as goods>
								<tr class="tableListTr">
									<td class="tc">
										<input type="checkbox" class="cssche" name="importCheck" value="${goods.id}"/>
									</td>
									<td class="tc">
										${goods_index+1}
									</td>
									<td class="tc">
										${goods.goodsName }
									</td>
									<td class="tc">
										${goods.goodsSubtitle }
									</td>
									<td class="tc">
										${goods.goodsPrice}
									</td>
									<td class="tc">
										${goods.goodsCostPrice}
									</td>
									<td class="tc">
										${goods.goodsMarketPrice}
									</td>
									<td class="tc">
										${goods.seoTit}
									</td>
									<td class="tc">
										${goods.seoKey}
									</td>
									<td class="tc">
										${goods.seoDes}
									</td>
									<td class="tc">
										${goods.thirdName}
									</td>
									<td class="tc">
										<#if (goods.addFlag?number==1)>
											已发布
										</#if>
										<#if (goods.addFlag?number==0)>
											<div class="btn-group clearfix">
											<a class="btn" href="thirdtopubimportgoods.html?id=${goods.id}"><i class="icon"></i>发布</a>
										</div>
										</#if>
									</td>
								</tr>
							</#list>
	                   	</tbody>
	                   	<tfoot>
							<tr>
								<td colspan="2">
									<label style="margin-left:7px;">
									<input class="vm mr5"  onclick="selectAll(this,'importCheck')" type="checkbox" />全选</label>
								</td>
			                   	<td class="pg_td" colspan="10" style="text-align:right;">
			                    	<a class="p_prev" onclick="changePageNo(this);" data-role="${map.pageBean.prePageNo}" href="javascript:;">&nbsp;</a>
			                    	<#if (map.pageBean.startNo?number>1)> 
			 							<a href="javascript:void(0);" onclick="changePageNo(this);" data-role="1">1</a> ...
			 						</#if>
			                        <#list map.pageBean.startNo?number .. map.pageBean.endNo as item>
			                          	<a <#if item == map.pageBean.pageNo>class="cur" style="background:#3994f6;color:#fff"</#if> onclick="changePageNo(this);" data-role="${item}"  href="javascript:;">${item}</a>
			                        </#list>
			                        <#if (map.pageBean.totalPages?number>map.pageBean.endNo)>
				 						...<a href="javascript:void(0);" onclick="changePageNo(this);" data-role="${map.pageBean.totalPages}">${map.pageBean.totalPages}</a>
				 					</#if>
			                 		<a class="p_next" onclick="changePageNo(this);" data-role="${map.pageBean.nextPageNo}" href="javascript:;">&nbsp;</a>
			                 	</td>
			              	</tr>
			           	</tfoot>
		           	<#else>
           				 <td colspan="12"><center>暂无相关商品信息</center></td>
          			</#if>
                </table><!--/pr_tb-->
                </form>
            </div>

        </div><!--/wrapper-->
    </div><!--/container-->
</div>
<!--/dia1-->
    	
    <script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
	<#import "../common/selectindex.ftl" as selectindex>
	<@selectindex.selectindex "${n!''}" "${l!''}" />
	
	<!--提示框-->
	<div class="mask"></div>
	<div class="dialog s_dia dia1" style="width: 300px; height: 150px; ">
		    <div class="sd_tit clearfix"><h3 class="fl">操作提示</h3>
		    	<a class="sd_close fr" href="javascript:;" onclick="cls()"></a>
		    </div>
		    <div class="pmt_wp" style="height: 50px;">
		    	<p class="tc f14 dialogMsg" style="line-height:70px;"></p>
		    </div>
		    <div class="tc mt20">
		    	<a class="sop_btn dialogOK" href="javascript:void(0);" onclick="doBatchDelete()">确定</a>
		    	<a class="sop_btn" href="javascript:;" onclick="cls()">取消</a>
		    </div>
    </div>
	
	<form class="goodsImport" enctype="multipart/form-data" action="thirdImportGoods.htm?CSRFToken=ef23f26e-8129-4143-b108-fce116360eac" method="post" style="display:none;">
		选择EXCEL文件
		<input class="importGoods" name="importExcel" type="file">
	</form>
    
    <!-- 下载导入模板 -->
	<form action="thirdDownImportExcel.htm?CSRFToken=222c2ee2-d06e-4107-9d50-86b32ccde153" method="post" class="downImportExcel">
	</form>
</body>
</html>
