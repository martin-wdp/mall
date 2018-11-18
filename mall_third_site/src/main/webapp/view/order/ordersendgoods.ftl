<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城第三方后台-发货</title>
<#assign basePath=request.contextPath>
<link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
<link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
<link rel="stylesheet" href="${basePath}/css/style.css"/>

<script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${basePath}/js/third.js"></script>
<script type="text/javascript" src="${basePath}/js/seller/express.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-ui/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="${basePath}/js/system/system.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script src="${basePath}/js/drag.jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/customer.js"></script>
<script charset="utf-8" src="${basePath}/js/themes/themes.js"></script>
<script type="text/javascript" src="${basePath}/js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
<script type="text/javascript" src="${basePath}/js/artDialog4.1.7/plugins/iframeTools.js"></script>


<style media=print>     
.Noprint{display:none;} 
.PageNext{page-break-after:always;}
</style>  

<style>
		* {margin:0; padding:0;}
		body {font:12px tahoma,arial,\5b8b\4f53; color:#333;margin:10px;padding:20px;}
		.print_wp {width:700px; margin:0 auto; font-size:14px;}
		table {width:100%; border-collapse:collapse; border-spacing:0;}
		.print_info {margin-top:50px;}
		.print_info td {text-align:left; line-height:30px;}
		.print_tb {margin:10px 0;}
		.print_tb th, .print_tb td {border:1px solid #ddd; padding:5px 15px; text-align:center;}
		.settle_tb td {line-height:30px;}
		.express_board{position:relative;min-height:600px;}
		.express_board .express_img{position:absolute;}
  
	</style>	
<script type="text/javascript">
    //判断数据是否为空为空返回“”
    function notNull(obj){
        if(obj != null && obj != undefined){
            return obj;
        }else{
            return "";
        }
    }
		$(function(){
			$.ajax({
				type:'post',
				url:'thirdordersendgoods.htm?orderId=${orderId}',
				async:false,
				success:function(data) {
                    var html = '<input  type="hidden" name="token" value="L" />' + '<input name="orderId" class="order_id_wx" type="hidden" value="' + data.order.orderId + '"/>';
                    for (var i = 0; i < data.relations.length; i++) {
                        var name = "";
                        for (var j = 0; j < data.relations[i].containers.length; j++) {
                            name += data.relations[i].containers[j].goodsProductDetailViewVo.goodsInfoName + "<br/>";
                        }
                        if (data.logisticsSingle == null) {
                            $('#Logistics_windows').modal('show')
                            return;
                        }
                        var content = data.logisticsSingle.logisticsSingleContent;
                        if(content != null){
                            content = content.replace("收件人-姓名", notNull(data.order.shippingPerson));
                            content = content.replace("收件人-地区1级", notNull(data.order.shippingProvince));
                            content = content.replace("收件人-地区2级", notNull(data.order.shippingCity));
                            content = content.replace("收件人-地区3级", notNull(data.order.shippingCounty));
                            content = content.replace("收件人-地址", notNull(data.order.shippingAddress));
                            content = content.replace("货品名称", notNull(name));
                            content = content.replace("收件人-联系电话", notNull(data.order.shippingPhone));
                            content = content.replace("收件人-手机号码", notNull(data.order.shippingMobile));
                            content = content.replace("收件人-邮政编号", notNull(data.order.shippingPostcode));
                            content = content.replace("发件人-姓名", notNull(data.store.companyContactName));
                            content = content.replace("发件人-地址", notNull(data.store.companyAddrDel));
                            /*content = content.replace("发件人-地区1级",'');
                             content = content.replace("发件人-地区2级",'');
                             content = content.replace("发件人-地区3级",'');*/
                            content = content.replace("发件人-手机号码",notNull(data.store.companyContactTel));
                            content = content.replace("发件人-联系电话",notNull(data.store.companyTel));
                            content = content.replace(/express_label/g,"");
                        }
                        /*var option='';
                        for(var k=0;k<data.expressList.length;k++){
                            option+='<option value="'+data.expressList[k].shoreExpId +'">'+data.expressList[k].expName+'</option>';
                        }*/
                        html = html + '<h3>包裹' + (i + 1) + ' </h3> <table class="print_info s_print" style="margin-top:10px;">' +
                        '<tr><td>物流公司：' +
                        '<span>' + data.express.expressName + '</sapn>' +
                        '</td></tr>' +
                        '<tr><td>运单号：<input class="ihtext" id="iq_text' + i + '"  type="text" name="expressNo"><span class="express_No_info"></span></td></tr>' +
                        '</table>' +
                        '<input value="' + data.express.expressId + '" type="hidden" name="expressId"/>' +
                        '<input value="' + data.relations[i].relationId + '" type="hidden" name="relationIds"/>' +

                        ' <div class="express_board">' +
                        '<!--startprint' + i + '-->' +
                        '<img  class="express_img" src="' + data.logisticsSingle.logisticsSingleImg + '" style="position:absolute;width:' + data.logisticsSingle.logisticsSingleWidth + 'px;height:' + data.logisticsSingle.logisticsSingleHeight + 'px;"/>' +
                        '<div class="express_print">'+
                        '<div class="express_container" style="width:' + data.logisticsSingle.logisticsSingleWidth + 'px;height:' + data.logisticsSingle.logisticsSingleHeight + 'px;">' +

                        content
                        + '</div><!--endprint' + i + '--></div>'
                        ;

                    }
                    $("#addForm").html("");
					$("#addForm").append(html);
				}
			});
		});
function clsWindow(){
	window.opener.location.href="querythirdoutstock.html?orderCargoStatus=2";
	window.close();

}
</script>
</head>
<body>
	<form action=""   id="addForm" method="post" class="sub_sendGoods">

	</form>
	 <div class="mt20 tc">
         <button class="btn btn-primary" onclick="printY();" type="button">打印</button>
         <button class="btn btn-primary" onclick="subSendGoods();" type="button">发货</button>
         <button class="btn btn-primary" onclick="javascript:window.close();" type="button">关闭</button>
	</div>
    <input type="hidden" value="0" class="status_print">
    <!--请填写物流单号 弹出框-->
    <div class="modal fade" id="Logistics_number" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel">操作提示</h4>
                </div>
                <div class="modal-body">
                    请填写运单号！
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"  data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>


    <!--出库成功弹出框-->
    <div class="modal fade" id="out_success" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel">操作提示</h4>
                </div>
                <div class="modal-body">
                    出库成功
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" onclick="clsWindow()"  data-dismiss="modal">确定</button>
                </div>
            </div>
        </div>
    </div>

    <!--没有配置物流单 模板弹出框-->
    <div class="modal fade" id="Logistics_windows" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel">操作提示</h4>
                </div>
                <div class="modal-body">
                    没有配置物流单模板！
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
<script type="text/javascript">
	/*$(function(){
        $(".expressId").change(function(){
            toExpress($(this).val())
        });
		if($(".status").val()=="0"){
			  returnList();
			  window.print();  
		}
	});

    function toExpress(eId){
        $.ajax({
            type:'post',
            url:'thirdordersendgoods.htm?orderId=${orderId}',
            async:false,
            success:function(data) {

                var html = '<input  type="hidden" name="token" value="L" />' + '<input name="orderId" class="order_id_wx" type="hidden" value="' + data.order.orderId + '"/>';
                for (var i = 0; i < data.relations.length; i++) {
                    var name = "";
                    for (var j = 0; j < data.relations[i].containers.length; j++) {
                        name += data.relations[i].containers[j].goodsProductDetailViewVo.goodsInfoName + "<br/>";
                    }
                    if (data.logisticsSingle == null) {
                        $('#Logistics_windows').modal('show')
                        return;
                    }
                    var content = data.logisticsSingle.logisticsSingleContent;
                    content = content.replace("收件人-姓名", data.order.shippingPerson);
                    content = content.replace("收件人-地区1级", data.order.shippingProvince);
                    content = content.replace("收件人-地区2级", data.order.shippingCity);
                    content = content.replace("收件人-地区3级", data.order.shippingCounty);
                    content = content.replace("收件人-地址", data.order.shippingPostcode);
                    content = content.replace("货品名称", name);
                    content = content.replace("收件人-联系电话", data.order.shippingPhone);
                    content = content.replace("收件人-手机号码", data.order.shippingMobile);
                    content = content.replace("收件人-邮政编号", data.order.shippingPostcode);
                    content = content.replace("发件人-姓名", '');
                    content = content.replace("发件人-地址", data.basicSet.bsetName);
                    *//*var option='';
                    for(var k=0;k<data.expressList.length;k++){
                        option+='<option value="'+data.expressList[k].shoreExpId +'">'+data.expressList[k].expName+'</option>';
                    }*//*
                    html = html + '<h3>包裹' + (i + 1) + ' </h3> <table class="print_info s_print" style="margin-top:10px;">' +
                    '<tr><td>物流公司：' +
                    '<span>' + data.express.expressName + '</sapn>' +
                    '</td></tr>' +
                    '<tr><td>运单号：<input class="ihtext" id="iq_text' + i + '"  type="text" name="expressNo"><span class="express_No_info"></span></td></tr>' +
                    '</table>' +
                    '<input value="' + data.express.expressId + '" type="hidden" name="expressId"/>' +
                    '<input value="' + data.relations[i].relationId + '" type="hidden" name="relationIds"/>' +

                    ' <div class="express_board">' +
                    '<!--startprint' + i + '-->' +
                    '<img  class="express_img" src="' + data.logisticsSingle.logisticsSingleImg + '" style="position:absolute;width:' + data.logisticsSingle.logisticsSingleWidth + 'px;height:' + data.logisticsSingle.logisticsSingleHeight + 'px;"/>' +
                    '<div class="express_print">'+
                    '<div class="express_container" style="width:' + data.logisticsSingle.logisticsSingleWidth + 'px;height:' + data.logisticsSingle.logisticsSingleHeight + 'px;">' +

                    content
                    + '</div><!--endprint' + i + '--></div>'
                    ;
                }
                alert(html);
                $("#addForm").html("");
                $("#addForm").append(html);


            }
        });
    }
*/


    /*出库打印*/
	function printY(){
        var bdhtml=window.document.body.innerHTML;
        var prnhtml='';
        var printhtml=  ' <div class="express_board">';
        //给物流单编号赋值
        var intext=$(".ihtext");
        for(var i=0;i<intext.length;i++){
            var sprnstr="<!--startprint"+i+"-->";
            var eprnstr="<!--endprint"+i+"-->";
            //var mail=$("select").find("option:selected");
            prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18);
            prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
            prnhtml=prnhtml.substring(0,prnhtml.indexOf("iq_text"+i)+9)+"value='"+$("#iq_text"+i).val()+"'"+prnhtml.substring(prnhtml.indexOf("iq_text"+i)+9,prnhtml.length);
            bdhtml=bdhtml.substring(0,bdhtml.indexOf("iq_text"+i)+9)+"value='"+$("#iq_text"+i).val()+"'"+bdhtml.substring(bdhtml.indexOf("iq_text"+i)+9,bdhtml.length);
            printhtml=printhtml+$(".express_print").get(i).innerHTML;
        }
        window.document.body.innerHTML=printhtml+'</div>';
        window.print();
        window.document.body.innerHTML=bdhtml;

        $(".status_print").attr("value","1");
	}
    //出库
	function subSendGoods(){
        if($(".status_print").val()!="1"){
            $('#out_success').modal('show');
            $(".modal-body").text("请先打印！");

            return;
        }
			var expressNo=document.getElementsByName("expressNo");
			for(var i =0;i<expressNo.length;i++){
				if(expressNo[i].value==""){
                    $('#Logistics_number').modal('show')
					return null;
				}
			}
			
		$.ajax({
	         type: 'post',
	         url:'thirdsubsendgoodsorder.htm',
	         async:false,
	         data:$('#addForm').serialize(),
	         error:function(){
	         
	         },
	         success: function(data) {
                 $('#out_success').modal('show');
		      }
	        });
		}

	 function clsWindow(){
	 	window.opener.location.href="querythirdoutstock.html?orderCargoStatus=2";
	 	window.close();
	 }
		
</script>
<script type="text/javascript" src="/third/js/navmenu/navmenu.js"></script>
<script>
			$(".nav_list li a").removeClass("cur");
			$(".nav_list li a").each(function(){
				if($(this).attr('navid') == 9){
					$(this).parent().addClass("cur");
					$(".menu_cont").hide();
					$(".leftmenuxx"+ $(this).attr('navid') ).show();
				}
			});
			
			$(".menu_list li a").removeClass("cur");
			$(".menu_list li a").each(function(){
				if($(this).attr('leftmenuid')== 61){
					$(this).parent().addClass("cur");
				}
			});
			
</script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
 	<#import "../common/selectindex.ftl" as selectindex>
	<@selectindex.selectindex "${n!''}" "${l!''}" />
</body>
</html>
