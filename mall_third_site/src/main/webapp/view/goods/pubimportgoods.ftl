<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城第三方后台-商品管理</title>
<#assign basePath=request.contextPath />
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/third.css" />
<link rel="stylesheet" href="${basePath}/css/chosen.css">
<script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
<link rel="stylesheet" href="${basePath}/css/ztree/zTreeStyle.css">
<script type="text/javascript" src="${basePath}/js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${basePath}/js/third.js"></script>
<link rel="stylesheet" href="${basePath}/plugin/kindeditor/themes/default/default.css" />
<script charset="utf-8" src="${basePath}/plugin/kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="${basePath}/plugin/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${basePath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/goods_vali.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/thirdgoods.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/chosen.jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/pubimportgoods.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
<script type="text/javascript" src="${basePath}/js/artDialog4.1.7/plugins/iframeTools.js"></script>
</head>

<body>
	<div id="anchor_top"></div>
	<#-- 引入头部 -->
    <#import "../common/head.ftl" as head>
	<@head.head '${basePath}' />
    
    <div class="container clearfix">
    	<div class="t_menu fl">
        	<#-- 引入左侧菜单 -->
        	<#import "../common/leftmenu.ftl" as leftmenu>
			<@leftmenu.leftmenu '${basePath}' />
        </div><!--/t_menu-->
        
        <!-- 保存商品的表单 -->
		<form class="save_goods_info" action="sathirdgoods.htm" method="post" target="hidden_frame" enctype="multipart/form-data">
			<input type="hidden" name="goodsSeoTitle" value="${importGoods.seoTit}" />
			<input type="hidden" name="goodsSeoKeyword" value="${importGoods.seoKey}" />
			<input type="hidden" name="goodsSeoDesc" value="${importGoods.seoDes}" />
		</form>
		<!-- 保存商品详情的表单 -->
		<form class="save_goods_desc" action="tNewUploadSaveGoodsDesc.htm" method="post" target="hidden_frame" enctype="multipart/form-data">
							<input type="hidden" class="new_goods_id" name="goodsId" value="">
							<input type="hidden" class="goods_desc" name="goodsDetailDesc" value="">
							<input type="hidden" class="goods_mobile_desc" name="goodsMobileDesc" value="">
		</form>
        <form class="sa_third_goods" action="sathirdgoods.htm" enctype="multipart/form-data" method="post" >
        <div class="wrapper fr">
        	<!-- 验证存在的标记变量  start-->
				<input type="hidden" class="checkExistsFlag" value="1" />
				<input type="hidden" class="token_val" value="1" />
			<!-- end -->
        	<div class="location">
            	您所在的位置
                <span>&gt;</span>
                <a href="thirdgoodsmanager.htm?l='27'&n='3'">商品管理</a>
                <span>&gt;</span>
                <a href="javascript:;">发布导入商品</a>
            </div><!--/location-->
            <!-- 商品导入临时表主键ID -->
			<input type="hidden" class="import_id" value="${importGoods.id }" />
            
            <div class="step_wp step_01">
                <div class="upload_wp mt20">
                    <h4>请选择您要上传的商品分类</h4>
                    <div class="sort_bar mt10">
                      	  您最近使用的分类
                        <select class="ml10 use_third_cat">
                        	<option value="-1">-----请选择-----</option>
                        </select>
                        <a class="clean_st ml10 clear_third_cate_cookie" href="javascript:;">清除最近使用的分类</a>
                    </div><!--/sort_bar-->
                    
                    <div class="up_wp clearfix mt10">
                        <div class="up_box fl">
                        	<input class="up_sch sear_first" onKeyUp="searfirst(this);" type="text" />
                            <ul class="st_list cat_first">
                            </ul><!--/st_list-->
                        </div><!--/up_box-->
                        <div class="up_box fl">
                            <input class="up_sch sear_second" onKeyUp="searSecond(this);" type="text" />
                            <ul class="st_list cat_second">
                            </ul><!--/st_list-->
                        </div><!--/up_box-->
                        <div class="up_box fl">
                            <input class="up_sch sear_third" onKeyUp="searThird(this);" type="text" />
                            <ul class="st_list cat_third">
                            </ul><!--/st_list-->
                        </div><!--/up_box-->
                        <div class="up_box fl">
                            <input class="up_sch sear_fouth" onKeyUp="searFouth(this);" type="text" />
                            <ul class="st_list cat_fourth">
                            </ul><!--/st_list-->
                        </div><!--/up_box-->
                    </div><!--/up_wp-->
                    
                    <div class="n_choose">
                        <b></b>
                        <div class="chs_box">
                            <em class="fb">您当前选择的是：</em>
                            <span class="firstCate">请选择商品所属分类</span>
                            <span class="secondCate"></span>
                            <span class="thirdCate"></span>
                            <span class="fouthCate"></span>
                        </div><!--/chs_box-->
                    </div><!--/n_choose-->
                    
                    <div class="tc mt20">
                        <a class="op_btn step_next" data-role="step_first" href="javascript:;">下一步</a>
                    </div>
                </div><!--/upload_wp-->
            </div><!--/step_01-->
            
            <!-- --------------------------------------------------------------------------------------- -->
            
            <div class="step_wp step_02 none">
            <input type="hidden" name="thirdCateId" class="ch_third_catid" value="-1" />
					<dl class="tabs_dl clearfix mt10">
						<dt><span class='sp'>*</span>商品类目：</dt>
						<input type="hidden" name="catId" class="ch_goods_cate" value="">
						<dd>
							<div class="up_wp clearfix mt10">
						        <div class="up_box fl">
						            <input class="up_sch fir_search" onKeyUp="searfirst(this);" type="text" />
						            <ul class="st_list fir_list">
						            </ul><!--/st_list-->
						        </div><!--/up_box-->
						    </div><!--/up_wp-->
						</dd>
						<dt>商品推荐点：</dt>
						<dd>
							<#list tagList as tag>
								<label class="mr30"><input class="vm mr5 goods_tag" type="checkbox" name="goods_tags" value="${tag.tagId }" />${tag.tagName }</label>
							</#list>
						</dd>
						<dt><span class='sp'>*</span>品牌：</dt>
						<dd>
							<select class="tb_select" name="brandId" id="goods_brand" style="width:204px;height:34px">
								
							</select>
						</dd>
						<dt>商品属性：</dt>
						<dd class="type_attribute">
							请选择分类
						</dd>
						<dt>规格：</dt>
						<font class="spec_remark_input_Tips"></font>
						<dd class="type_spec">
							请选择分类
						</dd>	
					</dl><!--/tabs_dl-->
				<div class="tc">
	                <a class="op_btn prev_step" href="javascript:;">上一步</a>
	                <a class="op_btn step_next" data-role="step_second" id="create_gds" href="javascript:;">下一步</a>
                </div>
            </div><!--/step_02-->
            
            <div class="step_wp step_03 none">
           		<div class="detail_info">
					<ul class="dinfo_tabs clearfix">
						
					</ul><!--/dinfo_tabs-->
					<div class="dinfo_wp">
						
					</div><!--/dinfo_wp-->
				</div><!--/detail_info-->
            
            	<dl class="none tabs_dl ntabs_dl clearfix mt10" style="padding:15px 15px 0; border:1px solid #cdcdcd; ">
					<dt>关联商品：</dt>
					<dd><button class="ch_about"><b></b>选择关联商品</button></dd>
				</dl>
				<!-- 导入商品的商品详情隐藏域 -->
				<input type="hidden" class="import_goodsdes" value="${importGoods.goodsDes}"  />
				<dl class="tabs_dl ntabs_dl clearfix mt10">
					<dt style="float:none;">PC版详情：</dt>
					<dd style="margin-left:0;">
							<textarea name="goods_desc" cols="100" rows="8" style="width: 100%; height: 200px; visibility: hidden;"></textarea>
							<textarea style="display:none" name="goodsDetailDesc" id="saveGoodsDesc"></textarea>
					</dd>
					<dt style="float:none;">移动版详情：</dt>
					<dd style="margin-left:0;">
							<textarea name="mobile_desc" cols="100" rows="8" style="width: 100%; height: 200px; visibility: hidden;"></textarea>
							<textarea style="display:none" name="goodsMobileDesc" id="saveGoodsMobileDesc"></textarea>
					</dd>
					<dt class="none">SEO信息：</dt>
					<dd class="none">
						<div class="seo_cont mb10">
							<span>标题：</span>
							<textarea class="seo_title"></textarea><font class="seo_title_Tips"></font>
						</div><!--/seo_cont-->
						<div class="seo_cont mb10">
							<span>关键词：</span>
							<textarea class="seo_key"></textarea><font class="seo_key_Tips"></font>
						</div><!--/seo_cont-->
						<div class="seo_cont mb10">
							<span>简介描述：</span>
							<textarea class="seo_des"></textarea><font class="seo_des_Tips"></font>
						</div><!--/seo_cont-->
					</dd>
				</dl><!--/tabs_dl-->
            	
            	<div class="tc">
	                <a class="op_btn prev_step" href="javascript:;">上一步</a>
	                <a class="op_btn info_sub" href="javascript:;">提交</a>
                </div>
            </div><!--/step_03-->
            	
            	
			<div class="dinfo_box demo_box">
				<dl class="tabs_dl clearfix">
					<dt><span class='sp'>*</span>商品编号：</dt>
					<dd><input class="no_input p_code" type="text" onblur="checkProNo(this);" /><font class="no_input_Tips"></font><input type="hidden" class="exist_flag" value="0" /></dd>
					<dt><span class='sp'>*</span>商品名称：</dt>
					<dd><input class="name_input" type="text" value="${importGoods.goodsName}" /><a class="copy_all" onclick="copyAllName(this);"><font class="name_input_Tips"></font></a></dd>
					<dt>副标题：</dt>
					<dd><textarea class="des_input">${importGoods.goodsSubtitle}</textarea><a class="copy_all sec_des" onclick="copyAllSubTitle(this);"><font class="des_input_Tips"></font></a></dd>
					<dt>服务支持：</dt>
					<dd>
						<#list support as supp>
							<label class="mr30"><input class="vm mr5 pro_supp" type="checkbox" value="${supp.id }" />${supp.serviceName }</label>
						</#list>
					</dd>
					<dt><span class='sp'>*</span>销售价格：</dt>
					<dd><input class="sml_input pro_price" type="text" onblur="price_default(this);" value="${importGoods.goodsPrice}" /><label class="ml10">此价格只用于显示，以规格定价为商品销售价</label><font class="pro_price_Tips"></font></dd>
					<dt><span class='sp'>*</span>成本价格：</dt> 
					<dd><input class="sml_input pro_cost_price" type="text"  value="${importGoods.goodsCostPrice}" /><font class="pro_cost_price_Tips"></font></dd>
					
					<dt><span class='sp'>*</span>市场价格：</dt>
					<dd><input class="sml_input pro_mark_price" type="text" value="${importGoods.goodsMarketPrice}" /><font class="pro_mark_price_Tips"></font></dd>
					<dt><span class='sp'>*</span>库存：</dt>
					<dd><input class="sml_input pro_stock" type="text" /><font class="pro_stock_Tips"></font></dd>
					
					<dt><span class='sp'>*</span>商品图片：</dt>
					<dd>
						<a class="choose_img_btn">选择图库图片</a>
						<!--<form action="uploadProductImageSingle.htm" method="post" target="hidden_frame" enctype="multipart/form-data">
							<input type="file" name="prouctImage" class="up_pro_img" style="margin-top:7px;" />
						</form>
						<a class="copy_all none">复制到全部</a>-->
						<font class="pro_image_Tips"></font>
						<ul class="choose_imgs mt20 clearfix">
							
						</ul>
					</dd>
					<dt>商品状态：</dt>
					<dd>
						<label class="mr30"><input class="vm mr5 show_list" type="checkbox" />类目页推荐</label>
						<label class="mr30"><input class="vm mr5 show_mobile" type="checkbox" />手机版推荐</label>
					</dd>
					<dt><span class='sp'>*</span>商品上架：</dt>
					<dd class="p_shelves">
						<div class="nameWp">
						<label class="mr30 fb f14" style="color:red;"><input class="vm mr5 pro_status" type="radio" value="0" checked=checked  />下架</label>
						<label class="mr30 fb f14" style="color:green;"><input class="vm mr5 pro_status" type="radio" value="1" />立即上架</label>
						<!--  <label class="mr30"><input class="vm mr5 pro_status" type="radio" value="4" />定时上架</label>-->
						</div>
						<!-- <input class="ga_text up_time" type="text" onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>-->
					</dd>	
				</dl>
			</div>
			
			<!-- --------------------------------------------------------------------------------------- -->
			
        </div><!--/wrapper-->
    </form>
    </div><!--/container-->
    <!-- 模拟无刷新上传图片用到的 -->
	<iframe name="hidden_frame" style="display:none"></iframe>
    <div class="mask"></div>
    <div class="dialog s_dia dia1" style="width: 300px; height: 150px; ">
		    <div class="sd_tit clearfix"><h3 class="fl">操作提示</h3>
		    	<a class="sd_close fr" href="javascript:;" onclick="cls()"></a>
		    </div>
		    <div class="pmt_wp" style="height: 50px;">
		    	<p class="tc f14 show_title" style="line-height:70px;">确认删除？</p>
		    </div>
		    <div class="tc mt20">
		    	<a class="sop_btn" href="javascript:;" onclick="cls()">确定</a>
		    </div>
		 </form>
    </div>
    
    <div class="dialog s_dia dia1" style="width: 300px; height: 150px; ">
		    <div class="sd_tit clearfix"><h3 class="fl">操作提示</h3>
		    	<!--<a class="sd_close fr" href="javascript:;" onclick="cls()"></a>-->
		    </div>
		    <div class="pmt_wp" style="height: 50px;">
		    	<p class="tc f14 dialogMsg" style="line-height:70px;"></p>
		    </div>
		    <div class="tc mt20">
		    	<a class="sop_btn dialogOK" href="javascript:void(0);" onclick="doBatchDelete()">确定</a>
		    	<!--<a class="sop_btn" href="javascript:;" onclick="cls()">取消</a>-->
		    </div>
    </div>
    
    <script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
	<#import "../common/selectindex.ftl" as selectindex>
	<@selectindex.selectindex "${n!''}" "${l!''}" />	
    
</body>
</html>
