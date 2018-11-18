<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>商品管理</title>
    <#assign basePath=request.contextPath>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
    <style>
    	.inquiry_box {margin-left:0!important; margin-right:0!important; margin-bottom:10px;}
    	.input-group[class*=col-] {float:left!important;padding-right:10px!important;}
    </style>
</head>
<body>

<#--引入头部-->
<#include "../index/indextop.ftl">

<div class="wp">
    <div class="ui-sidebar">
        <div class="sidebar-nav">
            <div class="sidebar-nav">
            <#import "../index/indexleft.ftl" as leftmenu>
            <@leftmenu.leftmenu '${basePath}' />
            </div>
        </div>
    </div>

    <div class="app show_text" style="display: none;"">
        <div class="app-container">
            <ol class="breadcrumb">
                <li>您所在的位置</li>
                <li>商品管理</li>
                <li class="active" style="color: #07d;">商品组合</a></li>
            </ol>

            <div class="app-content">
                <div>
                    <div class="search-block">
                        <form action="thirdGoodsGroupManager.htm" method="post">
                            <div class="filter-groups">
                                <div class="control-group">
	                                    	<div class="search_sel">
			                                    <span class="iqy_val"></span>
			                                    <label class="control-label">商品组合：</label>
			                                    <div class="controls">
			                                    	<input class="form-control" type="text" value="${map.selectBean.searchText}" name="searchText"/>
			                                    </div>
	                                		</div>
		                                </div><!--/search_sel-->
                                </div>
                                <div class="search-operation">
                                <button class="btn btn-primary btn-sm" type="submit">查询<i class="glyphicon glyphicon-search"></i></button>
                            	</div>
                            </div>
                        </form>
                    </div>

                    <div class="cfg-content">
                        <div class="ops-bar"><button class="btn btn-primary btn-sm" type="button" data-toggle="modal" data-target="#addGroup" onclick="addproduct(1,5);"><i class="glyphicon glyphicon-plus"></i>添加商品组合</button></div>
                        <table class="table">
                            <thead>
                            <tr>
                                <th><input type="checkbox" onclick="selectAll(this,'thirdGoodsId')"/></th>
                                <th>序号</th>
                                <th width="300">商品组合</th>
                                <th>组合类型</th>
                                <th>套装价</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if (map.pb.list?size) &gt; 0 >
                            	<form id="GroupsList" method="post">
                            	<#list map.pb.list as goodsgourp>
                            <tr>
                                <td><input type="checkbox" value="${goodsgourp.groupId}" name="thirdGoodsId" class="ch_goods"/></td>
                                <td>${goodsgourp_index+1}</td>
                                <td>
                                    ${goodsgourp.groupName}
                                </td>
                                <td><#if goodsgourp.groupPrefertype?? && goodsgourp.groupPrefertype =="0">特惠套装<#else>人气组合</#if></td>
                                <td>
	                          		<#if goodsgourp.groupPrefertype?? && goodsgourp.groupPrefertype=="0">
	                            		¥<span class="pr_price">${goodsgourp.groupPreferamount?string("0.00")}</span>
	                            	<#else>
	                            		<span class="pr_price">组合无优惠</span>
	                            	</#if>
	                            </td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addGroup" onclick="modifyGroup(${goodsgourp.groupId});">修改组合</button>
                                        <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false" onclick="menu_btn(this)">
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li>
                                            <a href="javascript:;" onclick="del(${goodsgourp.groupId});" data-toggle="modal">删除</a>
                                            </li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                            	</#list>
                            	</form>
                            <#else>
	                        	 <td colspan="8"><center>暂无相关商品组合信息</center></td>
                            </#if>
                            </tbody>
                        </table>
                        <div class="footer-operation">
                                <#if map.pb??>
                                <#if (map.pb.list?size) &gt; 0 >
                                    <div class="ops-right">
                                    <nav>
                                        <ul class="pagination">
                                            <li>
                                                <a href="javascript:;" aria-label="Previous" onclick="changePageNo(this);" data-role="${map.pb.prePageNo}">
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                            <#if (map.pb.startNo?number>1)>
                                                <li><a href="javascript:;">1</a></li>
                                            </#if>
                                            <#list map.pb.startNo?number .. map.pb.endNo as item>
                                                <li <#if item == map.pb.pageNo>class="active"</#if>><a href="javascript:;" onclick="changePageNo(this);" data-role="${item}">${item}</a></li>
                                            </#list>
                                            <#if (map.pb.totalPages?number>map.pb.endNo)>
                                                <li><a href="javascript:;" onclick="changePageNo(this);" data-role="${pb.totalPages}">${pageBean.totalPages}</a></li>
                                            </#if>
                                            <li>
                                                <a href="javascript:;" aria-label="Next" onclick="changePageNo(this);" data-role="${map.pb.nextPageNo}">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
          						</#if>
          						</#if>   
                            </div>
                            <button class="btn btn-primary btn-xs" type="button" onclick="delThirdGroups()">批量删除</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "../common/leftfooter.ftl">

<div class="modal fade" id="addGroup" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
    <form class="add_thirdGroups form-horizontal" action="thirdAddGoodsGroupManager.htm" method="post">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="closeGroup()"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加商品组合</h4>
            </div>
            <div class="modal-body">
            	<input type="hidden" name="groupId" class="group_id" value="" />
                <div class="form-group">
                    <label class="control-label col-xs-2"><b>*</b>组合名称：</label>
                    <div class="controls col-xs-8">
                        <input type="text" class="form-control" id="page_text_name" name="groupName"/>
                        <label class="pg_textName"></label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-2"><b>*</b>组合类型：</label>
                    <div class="controls radio radio-primary col-xs-8">
                        <label><input name="groupPrefertype" type="radio" value="1" checked="checked"/>人气组合</label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-2">选择商品：</label>
                    <div class="controls col-xs-10">
                    	<div class="inquiry_wp">
							<!--/sd_tit-->
							<div class="inquiry_box row">
								<div class="input-group col-md-4">
									<span class="input-group-addon">编号：</span>
									<input class="iq_text sel_product_no form-control" type="text" value=""/>
								</div>
								<div class="input-group col-md-4">
									<span class="input-group-addon">品牌：</span>
									<select class="iq_text form-control" type="text" name="brandId" id="brandId">
										<option value="">全部</option>
									</select>
								</div>
								<div class="col-md-1">
									<button class="btn btn-primary btn-sm" type="button" onclick="addproduct(1,5);">查询</button>
								</div>
								
								<!--/iqy_tb-->
							</div>
							<!--/inquiry_box-->
						</div>
                        <table class="table" id="productAddList">
							<thead>
							<tr>
								<th class="tc">
									货号
								</th>
								<th class="tc">
									名称
								</th>
								<th class="tc">
									规格
								</th>
								<th class="tc">
									商品编号
								</th>
								<th class="tc">
									商品名称
								</th>
							</tr>
							</thead>
							<tbody>
							</tbody>
							<tfoot>
							</tfoot>
						</table>
						<div class="footer-operation text-right" id="dia_footer">
						</div>
                    </div>
                </div>
                <div class="form-group">
                    	<label class="control-label col-xs-2">已选择商品：</label>
                    	<div style="padding-top:10px;" id="fanwei" class="controls col-xs-10">
                    	</div>
                    	<div class="del_group_product_list none">
                    	</div>
                    </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal" onclick="closeGroup()">关闭</button>
                <button class="btn btn-primary" type="button" onclick="saveGroup()">保存</button>
            </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="delGroup" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
    <form id="mform" id="mform" name="mform" action="thirdDelGoodsGroup.htm" method="post">
        <div class="modal-content">
        	<input type="hidden" id="thirdGroupId" name="groupId" value=""/>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item error">
                    <label class="control-label">确认删除吗？</label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal" onclick="cls()">取消</button>
                <button class="btn btn-primary" type="button" data-dismiss="modal" id="tip-submit-btn" onclick="delThirdGroup();">确定</button>
            </div>
        </div>
    </form>
    </div>
</div>

<#--各种提示框-->

<#--确认删除提示框-->
<div class="modal fade" id="delete-tip" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item error">
                    <label class="control-label">确认删除吗？</label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal" onclick="cls()">取消</button>
                <button class="btn btn-primary" type="button" data-dismiss="modal" id="tip-submit-btn" onclick="delThirdGIds();">确定</button>
            </div>
        </div>
    </div>
</div>
<#--没有选中行提示框-->
<div class="modal fade" id="select-tip" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item error">
                    <label class="control-label">请至少选择一行！</label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>


<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/third.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/thirdgoodsgroup.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/goods_vali.js"></script>
<script>
    $(function(){
    	$.material.init();
        $('.datepicker').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            language: 'zh-CN'
        })
    });
    
        $(function(){
            $(".add-group").click(function(){
                $(".group_wp").hide();
                $(".addGroup").show();
            });
            
				/*加载签约的品牌*/
				$.get("queryGrandBrandByThirdId.htm",function(data){
					if(data!=null && data.length>0){
						for(var i=0;i<data.length;i++){
							$("#brandId").append("<option value="+data[i].brandId+">"+data[i].brandName+"</option>");
						}
					}
				});
        });
        
        function del(id){
    		$("#thirdGroupId").val(id);
    		$('#delGroup').modal('show');
    	}

        /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
        function show(){
            $(".show_text").fadeOut(1000).fadeIn(1000);
        }
        setTimeout("show()",1000);
</script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
	<#import "../common/selectindex.ftl" as selectindex>
	<@selectindex.selectindex "${n!''}" "${l!''}" />
</body>
</html>