<!DOCTYPE html>
<html lang="zh-cn">
  <head>
  	<#assign basePath=request.contextPath>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<meta name="keywords" content="${seo.meteKey}">
    <meta name="description" content="${seo.meteDes}">
    <meta name="format-detection" content="telephone=no" />
    <#if (sys.bsetName)??>
    	<title>订单详情-${(sys.bsetName)!''}</title>
    	<input type="hidden" id="bsetName" value="${(sys.bsetName)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    <#else>
	    <title>订单详情-${(seo.mete)!''}</title>
	    <input type="hidden" id="bsetName" value="${(seo.mete)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    </#if>
	
    <!-- Bootstrap -->
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]> 
      <script src="${basePath}/js/html5shiv.min.js"></script>
      <script src="${basePath}/js/respond.min.js"></script>
    <![endif]-->
    <style>
    	.order_details_good{height:71px;}
    	.sel_txa {width:340px;height:100px;padding:5px;border:1px solid #ddd;margin-top:5px;}
    </style>
  </head>
  <body>
    <#if order??>
    <div class="order_details_status">
      <span class="glyphicon glyphicon-list-alt"></span>
      <h4>
      	<#assign cFlag=0 />	
      	<#list order.goods as good>
			        	<#if good.evaluateFlag== '0'>
			                 <#assign cFlag=cFlag+1 />
			            </#if>
			         </#list>
      		<#if order.orderStatus??>
								<#if order.orderStatus=="0">
									未付款
								<#elseif (order.orderStatus=="1" | order.orderStatus=="5" | order.orderStatus=="6") >
									待发货
								<#elseif order.orderStatus=="2">
									待收货
								<#elseif (order.orderStatus=="3" & cFlag>0) >
	                            	待评价
								<#elseif order.orderStatus=="3">
									已完成
								<#elseif (order.orderStatus=="4") >
									已取消
								</#if>
							</#if>
      
      		<#--
			等待收货-->
      </h4>
      <p>订单号：
      		<#if order.orderNo??>
                 ${order.orderNo}
            </#if></p>
      <p>订单金额(含运费)：￥<#if order.moneyPaid??> ${(order.moneyPaid)?string('0.00')}<#else>0.00</#if></p>
      <p>运费：￥<#if order.shippingFee??> ${(order.shippingFee)?string('0.00')} <#else>0.00</#if></p>
    </div><!-- /order_details_status -->
    
    <div class="order_details_consignee">
    <#if order.orderExpressType??>
    <span class="glyphicon glyphicon-map-marker"></span>
    	<#if order.orderExpressType==1>
	    	<h4>
	        <span>[自提点]<#if order.shippingPerson??>
							${order.shippingPerson}
						</#if></span>
	       <!--<a href="${basePath}/order/bdmap.html?address=${order.shippingProvince?default('')}${order.shippingCity?default('')}${order.shippingCounty?default('')}${order.shippingAddress?default('')}" role="button" class="btn btn-default btn-xs pull-right">查看地图</a>-->
	      </h4>
	      <!--<p>江苏省南京市玄武区中央路102号小区3栋2单元202室</p>-->
	      <p id="mapaddress"><#if order.shippingProvince??>${order.shippingProvince?default('')}</#if><#if order.shippingCity??>${order.shippingCity?default('')}</#if><#if order.shippingCounty??>${order.shippingCounty?default('')}</#if><#if order.shippingAddress??>${order.shippingAddress?default('')}</#if></p>
		<#else>
		      <h4>
		        <span>收货人：
						<#if order.shippingPerson??>
							${order.shippingPerson}
						</#if>
				</span>
		        <span class="pull-right">
		                <#if order.shippingMobile??>
							${order.shippingMobile?default('')}
						</#if>
		        </span>
		      </h4>
		      <p>
		      <#if order.shippingProvince??>
		                                    ${order.shippingProvince?default('')}-</#if><#if order.shippingCity??>${order.shippingCity?default('')}-</#if><#if order.shippingCounty??>${order.shippingCounty?default('')}-</#if><#if order.shippingAddress??>${order.shippingAddress?default('')}</#if>
		                                
			  </p>
    	</#if>
    	
    </#if>
     
    </div><!-- /order_details_consignee -->
   
   	<#if order.orderStatus??>
   		<#if (order.orderStatus=="2"|order.orderStatus=="3")>
			<#--物流信息-->
		    <div class="express_info_simple">
		      <span></span>
		      <a href="http://www.kuaidi100.com/">
		        <h4>物流信息</h4>
		       
		        <#-- <p class="green">江苏省南京市建邺三部公司 已签收 签收人：林</p>
		       <p class="light">2014-08-02 14:33</p>-->
		       <#if order.expressno??>
		       		<#list order.expressno as express>
		       			 <p class="green">物流单号:${(express.expressNo)!'   无单号'}</p>
		       		</#list>
		       </#if>
		      </a>
		    </div><!-- /express_info_simple -->
   		</#if>
   	</#if>
    
    <div class="order_details_info">
      <#--<a class="business_title" href="javascript:;">百草味旗舰店</a>-->
      <#list order.goods as good>
      <div class="order_details_good">
        <a class="order_good_link"  href="${basePath}/item/${good.goodsId}.html">
				          <img width="50" height="50" title="${(good.goodsName)!''}" alt="${(good.goodsName)!''}" src="${(good.goodsImg)!''}" />
				          <h5 class="name"><span style="display:block;height:32px;overflow:hidden;">${good.goodsName}</span>
				          	<small>
				          			<#if (good.specVo??)>
										<#list good.specVo as spec>
											<#if spec_index=0>
												${spec.spec.specName!''}:${spec.specValueRemark!''}
											</#if>
										</#list>
									</#if>
						          	<#--规格：12个装-->
						     </small></h5>
				          <p class="pull-right text-right">
				             <strong>￥<#if good.goodsPrice??>
												${good.goodsPrice?string('0.00')}
											</#if> </strong><br>
					              <span class="light">×${good.goodsNum}</span>
				          </p>
			          </a>
        <#--<a href="javascript:;" class="btn btn-xs btn-warning">申请售后</a>-->
      </div>
      </#list>
      <div class="order_details_total container-fluid">
        <div class="row">
          <div class="col-xs-6">
            <p class="text-left">运费：</p>
          </div>
          <div class="col-xs-6">
            <p class="text-right"><strong>￥<#if order.shippingFee??> ${(order.shippingFee)?string('0.00')} <#else>0.00</#if></strong></p>
          </div>
        </div>
        <div class="row">
          <div class="col-xs-6">
            <p class="text-left">实付款(含运费)：</p>
          </div>
          <div class="col-xs-6">
            <p class="text-right orange"><strong>￥<#if order.moneyPaid??> ${(order.moneyPaid)?string('0.00')}<#else>0.00</#if></strong></p>
          </div>
        </div>
      </div>
      <div class="number_info">
        <h5>支付及发票信息</h5>
        <p class="light">支付方式：
        		<#if order.payId??>
                      <#if order.payId==2>
							货到付款	                                  	
                      <#else>
                         	在线支付
                      </#if>
                </#if></p>
        <p class="light">成交日期：
        		<#if order.addTime??>
					 ${order.addTime?string("yyyy-MM-dd HH:mm:ss")}
				</#if>
        </p>
          <#if order.orderIntegral??>
              <p class="light">使用积分：

                 ${order.orderIntegral}

              </p>
          </#if>
          <#if order.invoiceType??&&order.invoiceType!='0'>
                <p class="light">发票类型：
                            <#if order.invoiceType=='1'>
                                    普通
                            <#elseif order.invoiceType=='2'>
                                     增值
                            </#if>
                </p>
                <p class="light">发票抬头：
                        <#if order.invoiceTitle??>
                             ${order.invoiceTitle?default('')}
                        </#if>
                </p>
                <p class="light">发票内容：
                        <#if order.invoiceContent??>
                             ${order.invoiceContent?default('')}
                        </#if>
                </p>
          </#if>
      </div>
    </div><!-- /order_details_info -->
    <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->
    
    <div class="order_details_ctrl text-right">
      <a href="${basePath}/customer/myorder.html" class="btn btn-default">返回列表</a>
     	<#if order.orderStatus??>
								<#if order.orderStatus=="0">
			          				<a href="javascript:;" class="btn btn-default" onClick="cancelOrder('${basePath}/customer/cancelorder-myorder-${order.orderId}.html')">取消订单</a>
			          				<a href="${basePath}/orderdetailpay-${order.orderId}.html" class="btn btn-warning">立即支付</a>
								<#elseif order.orderStatus=="2">
									<#--<a href="javascript:;" class="btn btn-default">查看物流</a>-->
			          				<a href="javascript:;" class="btn btn-warning" onClick="comfirmgoods('${basePath}/customer/comfirmofgoods-myorder-${order.orderId}.html')">确认收货</a>
								<#elseif (order.orderStatus=="3" & cFlag>0)>
									<#--<a href="javascript:;" class="btn btn-default">查看物流</a>-->
          							<a  href="${basePath}/comment-${order.orderId}.html" class="btn btn-default">评价商品</a>
								</#if>
							</#if>
    </div><!-- /order_details_ctrl -->
	 <#else>
    	<div style="width: 100%;text-align:center;padding-top:100px;">
		    	<div class="notice2">
		        	订单不存在！<span>
		        </div>
		        <div class="notice3">
		            <strong><span id="time">5</span>秒自动进入<a href="${basePath}/main.html">“首页”</a></strong></span> 
		        </div>
		    </div>
    </#if>
	<div class="modal fade" role="dialog" id="cancel_order">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">商城消息</h4>
          </div>
          <div class="modal-body">
        	<div class="dia_intro no_tc pt30" style="height:170px;/*margin-left:20px;*/">
        		<img class="vm mr10" alt="" src="../images/q_mark.png" />
            	<em>取消订单原因:</em><br>
            	<#--<div class="mn_sel"></div>
            	<div class="selCont">
            		<label><input class="vm mr5" name="res" type="radio" value="现在不想买" />现在不想买</label>
            		<label><input class="vm mr5" name="res" type="radio" value="商品价格较贵" />商品价格较贵</label>
            		<label><input class="vm mr5" name="res" type="radio" value="价格波动"/>价格波动</label>
            		<label><input class="vm mr5" name="res" type="radio" value="商品缺货"/>商品缺货</label>
            		<label><input class="vm mr5" name="res" type="radio" value="重复下单"/>重复下单</label>
            		<label><input class="vm mr5" name="res" type="radio" value="添加或删除商品"/>添加或删除商品</label>
            		<label><input class="vm mr5" name="res" type="radio" value="收货人信息有误"/>收货人信息有误</label>
            		<label><input class="vm mr5" name="res" type="radio" value="发票信息有误/发票未开"/>发票信息有误/发票未开</label>
            		<label><input class="vm mr5" name="res" type="radio" value="送货时间过长"/>送货时间过长</label>
            		<label><input class="vm mr5" name="res" id="other_yy" type="radio" value="其他原因" />其他原因</label>
            	</div>
            	<div class="err_tip" style="color:red;width: 350px;text-align:right;margin-top: 10px;display:none;">请选择取消原因!</div>-->
            	<textarea class="sel_txa" id="sel_txa" style="width:98%;"></textarea>
            	<p id="titlereason" style="color:red;"></p>
            	<#--<div class="input_tip" style="color:red;text-align:right;margin-top: 10px;display:none;"></div>-->
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearmess()">取消</button>
            <button type="button" class="btn btn-primary" onClick="changeUrl();">确定</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    
    <div class="modal fade" role="dialog" id="confirm_order">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">商城消息</h4>
          </div>
          <div modal-body>
            <p>确定确认收货吗？小心钱货两空哦！</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button type="button" class="btn btn-primary" onclick="comfirmGoodsSucc()">确定</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/customer/memberorder.js"></script>
    <script src="${basePath}/js/customer/wxforward.js"></script>
    <script>
	  $(function(){
		FastClick.attach(document.body);
	  });
	  setTimeout(countDown, 1000);
	function countDown(){
		var time = $("#time").text();
		$("#time").text(time - 1);
		if (time == 1) {
			location.href='${basePath}/main.html';
		} else {
			setTimeout(countDown, 1000);
		}
	}
	</script>
  </body>
</html>
