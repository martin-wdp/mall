<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>商品管理</title>
    <#assign basePath=request.contextPath />
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
    <script type="text/javascript">
    $(function(){
        $('.datepicker').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            language: 'zh-CN'
        })
    });
    
    //根据id查询进行修改
   function selectstock(infoid,goodname,stock,number,salprice,buyprice){
	       if(goodname != '') {
	           $("#goodname").html(goodname);
	       }
		$("#stock").val(stock);
		$("#infoid").val(infoid);
		$("#numbers").html(number);
		$("#salprice").html(salprice);
		$("#buyprice").html(buyprice);
		$(".group_wp").hide();
		$(".updateGroup").show();
	}
	
	function updateform(){
     var stock = $("#stock").val();
	   var g=/^[0-9]\d*$/;
				if(stock != null && stock.trim().length != 0){
					if(isNaN(stock)==false && g.test(stock)==true){
				$('#updateForm').submit();
				$(".stockTips").text("修改的库存成功!");
				$(".stockTips").addClass("ui-state-highlight");
				$("#stock").addClass("ui-state-error");
					}else{		
						$(".stockTips").text("输入的库存必须为数字并且为正整数!");
						$(".stockTips").addClass("ui-state-highlight");
						$("#stock").addClass("ui-state-error");
						return;
					}						
				}else{
					$(".stockTips").text("请输要设置的库存!");
					$(".stockTips").addClass("ui-state-highlight");
					$("#stock").addClass("ui-state-error");
					return;
				}	
}  	

</script>
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
                <li class="active" style="color: #07d;">货品预警信息</li>
            </ol>

            <div class="app-content">
                <div>
                    <form class="search_product_form" action="<#if pb??>${pb.url}</#if>" method="post">
                    <input type="hidden" name="showFlag" value="2" />
                    <div class="app-content">
                    <div>
                        <div class="search-block">
                                <div class="filter-groups">
                                   	<div class="control-group">
		                                <label class="control-label">货品名称：</label>
		                                <div class="controls">
		                                    <input class="form-control" type="text" value="<#if stockWarningVo?? && stockWarningVo.goodname??>${stockWarningVo.goodname}</#if>" name="goodname"/>
		                                </div>
                            		</div>
		                            <div class="control-group">
		                                <label class="control-label">货品编码：</label>
		                                <div class="controls">
		                                    <input class="form-control" type="text" value="<#if stockWarningVo?? && stockWarningVo.number??>${stockWarningVo.number}</#if>" name="number"/>
		                                </div>
		                            </div>
                                </div>
                                <div class="search-operation">
		                            <button class="btn btn-primary btn-sm query_btn" type="submit">查询<i class="glyphicon glyphicon-search"></i></button>
		                            <button class="btn btn-default btn-sm rst_btn" type="button">重置<i class="glyphicon glyphicon-refresh"></i></button>
                        		</div>
                                
                        </div>
                        </div>
						</form>
                    <div class="cfg-content">
                        <div class="ops-bar"></div>
                        <table class="table">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th width="300">货品</th>
                                <th>货品编号</th>
                                <th>货品库存</th>
                                <th>货品进价</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <#if pb??>
                            <#if (pb.list?size) &gt; 0 >
                            	<form class="list_form" method="post">
		                            <tbody>
		                            	<#list pb.list as lis>
				                            <tr>
				                                <td>${lis_index+1}</td>
				                                <td>
				                                    <div class="goods-intro">
				                                    	<#if lis.goodsInfoImgId??>
	                                                        <#list lis.goodsInfoImgId?split(",") as img>
				                                        		<img class="gds-img" alt="" src="<#if img??>${img}</#if>"/>
				                                       		</#list>
                                                    	</#if>
				                                        <div class="gds-info">
				                                            <p class="gds-name"><a href="javascript:;">${lis.goodname!'' }</a></p>
				                                            <p class="gds-price"><#if lis.salprice??>&yen;${lis.salprice}</#if></p>
				                                        </div>
                                    				</div>
			                                	</td>
				                                <td>${lis.number!'' }</td>
				                                <td>${lis.stock!''}</td>
				                                <td>&yen;${lis.buyprice!''}</td>
				                                <td><button class="btn btn-primary btn-sm" type="button" onclick="selectstock(${lis.infoid },'${lis.goodname!'' }',${lis.stock },'${lis.number }'
						                        	,'¥${lis.salprice}','¥${lis.buyprice}');" data-toggle="modal" data-target="#modifyGd">修改</button></td>
			                            	</tr>
		                            	</#list>
	                            	</tbody>
	                           </form>
	                        <#else>
	                        	 <td colspan="8"><center>暂无相关商品信息</center></td>
	                        </#if>
	                        </#if>    
                        </table>
                        <div class="footer-operation">
                                <#if pb??>
                                <#if (pb.list?size) &gt; 0 >
                                    <div class="ops-right">
                                    <nav>
                                        <ul class="pagination">
                                            <li>
                                                <a href="javascript:;" aria-label="Previous" onclick="changePageNo(this);" data-role="${pb.prePageNo}">
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                            <#if (pb.startNo?number>1)>
                                                <li><a href="javascript:;">1</a></li>
                                            </#if>
                                            <#list pb.startNo?number .. pb.endNo as item>
                                                <li <#if item == pb.pageNo>class="active"</#if>><a href="javascript:;" onclick="changePageNo(this);" data-role="${item}">${item}</a></li>
                                            </#list>
                                            <#if (pb.totalPages?number>pb.endNo)>
                                                <li><a href="javascript:;" onclick="changePageNo(this);" data-role="${pb.totalPages}">${pageBean.totalPages}</a></li>
                                            </#if>
                                            <li>
                                                <a href="javascript:;" aria-label="Next" onclick="changePageNo(this);" data-role="${pb.nextPageNo}">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
          						</#if>
          						</#if>   
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "../common/leftfooter.ftl">

<input type="hidden" id="attrValues" value="${stockWarningVo.goodname!''}">
    <input type="hidden" id="attrValue" value="${stockWarningVo.number!''}">
    
<div class="modal fade" id="modifyGd" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改库存</h4>
            </div>
            <div class="modal-body">
             <form action="updatewarnstock.htm" class="sub_delThirdOrder" method="post" id="updateForm">
            	<input id="infoid" name="infoid" type="hidden"/>
                <div class="form-item">
                    <label class="control-label">货品编号：</label>
                    <div class="controls checkWp">
                        <span id="numbers"></span>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label">货品名字：</label>
                    <div class="controls checkWp">
                       	<span id="goodname"></span>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label">销售价格：</label>
                    <div class="controls checkWp">
                        <span id="salprice"></span>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label">进货价：</label>
                    <div class="controls checkWp">
                        <span id="buyprice"></span>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label"><b>*</b>库存：</label>
                    <div class="controls">
                        <input type="text" name="stock"  id="stock" class="pg_text stock form-control input-xs">
						<font class="stockTips"></font>
                    </div>
                </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary" type="button" onclick="updateform();">保存</button>
            </div>
        </div>
    </div>
</div>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/goods/thirdProductManager.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<script>
    $(function(){
    	$.material.init();
    });

    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);
</script>
	<#import "../common/selectindex.ftl" as selectindex>
	<@selectindex.selectindex "${n!''}" "${l!''}" />
</body>
</html>