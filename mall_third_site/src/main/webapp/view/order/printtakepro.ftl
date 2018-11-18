<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城第三方后台-拣货管理</title>
<#assign basePath=request.contextPath>
<link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
<link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
<link rel="stylesheet" href="${basePath}/css/style.css"/>


<script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${basePath}/js/third.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${basePath}/js/order/printpicking.js"></script>
<script type="text/javascript" src="${basePath}/js/third.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/print_common.js"></script>
<script type="text/javascript" src="${basePath}/js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
<script type="text/javascript" src="${basePath}/js/artDialog4.1.7/plugins/iframeTools.js"></script>

<script type="text/javascript"> 
 

function doPrintSetup(){
    //打印设置
    document.all.WebBrowser.ExecWB(8,1)
}
function doPrintPreview(){
    
    //打印预览
    document.all.WebBrowser.ExecWB(7,1)
}
function doPrint() {   
    window.print();  
}    

function clsWindow(){
	window.opener.location.href="querythirdoutstock.html?orderCargoStatus=0";
	window.close();
	
}


</script>
<style>
		* {margin:0; padding:0;}
		body {font:12px tahoma,arial,\5b8b\4f53; color:#333;}
		.print_wp {width:700px; margin:0 auto; font-size:14px;}
		table {width:100%; border-collapse:collapse; border-spacing:0;}
		.print_info {margin-top:50px;}
		.print_info td {text-align:left; line-height:30px;}
		.print_tb {margin:10px 0;}
		.print_tb th, .print_tb td {border:1px solid #ddd; padding:5px 15px; text-align:center;}
		.settle_tb td {line-height:30px;}
	</style>
</head>
<body>

	<div class="dialog s_dia dia1" style="width: 300px; height: 150px; display: none; padding-left: 200px; padding-top: 300px;">
	    <form action="delThirdOrderByparam.htm" method="post" id="mform" name="delform">
		    <input type="hidden" id="thirdOrderId" name="orderId" value=""/>
		    <div class="sd_tit clearfix"><h3 class="fl">操作提示</h3>
		    	<a class="sd_close fr" href="javascript:;" onclick="cls()"></a>
		    </div>
		    <div class="pmt_wp" style="height: 50px;">
		    	<p class="tc f14" style="line-height:70px;">拣货成功。</p>
		    </div>
		    <div class="tc mt20">
		    	<a class="sop_btn" href="javascript:;" onclick="clsWindow()" >确定</a>

		    </div>
		 </form>
    </div>

	<div class="print_wp"  id ="" style="margin-top:50px;">
        <!--startprint1-->
        <div class="modal-header">
            <h4 class="modal-title">拣货管理</h4>
        </div>
        <div class="modal-body">
            <div class="picking-info">
                <span><b>拣货人：</b><b class="pickingName">${map.orderPicking.pickingName}</b></span>
                <span><b>拣货时间：</b><b class="pickingTiem">${map.orderPicking.pickingTime?date}</b></span>
            </div>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>商品编号</th>
                    <th>商品名</th>
                    <th>规格</th>
                    <th>数量</th>
                </tr>
                </thead>
                <tbody class="tbody">
                    <#list map.orderGoodsInfos as orderGoodsInfos>
                        <tr>
                            <td>${(orderGoodsInfos.goodsProductVo.goodsInfoItemNo)!""}</td>
                            <td>${(orderGoodsInfos.goodsProductVo.goodsInfoName)!""}</td>
                            <td>
                                 <#list orderGoodsInfos.goodsProductVo.specVo as specVo>
                                    ${specVo.spec.specName}: ${specVo.goodsSpecDetail.specDetailName}<br/>
                                 </#list>
                            </td>
                            <td>${(orderGoodsInfos.goodsInfoNum)!""}</td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
        <!--endprint1-->
        <div class="modal-footer">
            <button class="btn btn-primary" onclick="preview(1)" type="button">打印</button>
            <button class="btn btn-primary" onclick="subFormJh()" type="button" >拣货</button>
            <button class="btn btn-primary" onclick="window.close();" type="button">关闭</button>

        </div>
    </div><!--/print_wp-->
	</div>

    <div class="modal fade" id="JHout_success" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel">操作提示</h4>
                </div>
                <div class="modal-body jian">
                    请先打印！
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" onclick="window.close()"  data-dismiss="modal">确定</button>
                </div>
            </div>
        </div>
    </div>



	<form action="" method="post" class="subFom" id="yourformid">
	<#list map.orders as order>
		<input type="hidden" name="orderId" value="${order.orderId}">
    </#list>
	</form>

    <input type="hidden" id="printNo" value="0" />
    <input type="hidden" id="printNo2" value="0" />
    <input type="hidden" id="printNo3" value="0" />
	<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>

 	<#import "../common/selectindex.ftl" as selectindex>
	<@selectindex.selectindex "${n!''}" "${l!''}" />
</body>
</html>
