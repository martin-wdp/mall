<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${(seo.meteKey)!''}">
    <meta name="description" content="${(seo.meteDes)!''}">
    <#if (sys.bsetName)??>
    	<title>${(sys.bsetName)!''}</title>
    	<input type="hidden" id="bsetName" value="${(sys.bsetName)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    <#else>
	    <title>${(seo.mete)!''}</title>
	    <input type="hidden" id="bsetName" value="${(seo.mete)!''}">
    	<input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
    </#if> 
	<#assign basePath=request.contextPath>
    <!-- Bootstrap -->
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]> 
      <script src="js/html5shiv.min.js"></script>
      <script src="js/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  
	<div class="pay_detail">
		<!--代付订单-->
		<div class="pay_title">来自${order.shippingPerson}的代付订单</div>
		<div class="check_goods">
	   <#list order.orderGoodsList as orderGoods>
	      <div class="check_goods_item">
	        <div class="img"><a href="${basePath}/item/${orderGoods.goodsProductVo.goodsInfoId}.html"><img alt="" src="<#if orderGoods.goodsProductVo.goodsInfoImgId??>${orderGoods.goodsProductVo.goodsInfoImgId}</#if>"></a></div>
	        <div class="word">
	          <p><a href="${basePath}/item/${orderGoods.goodsProductVo.goodsInfoId}.html">${orderGoods.goodsProductVo.goodsInfoName}</a></p>
	        </div>
	        <div class="price">
	          <p>￥${(orderGoods.goodsInfoPrice)?string("0.00")}</p>
	          <p>×${orderGoods.goodsInfoNum}</p>
	        </div>
	      </div>
	     </#list>
      <div class="total_price">
        <span>商品总价：</span>
        <p>￥${(order.orderPrice)?string("0.00")}</p>
      </div>
    </div>
	</div><!--pay_detail-->
	<div class="pay_another clearfix">
		<ul class="pay_one_title nav nav-tabs clearfix" role="tablist">
			<li class="active"><a href="#single" onclick="changeType('0')" role="tab" data-toggle="tab">单人代付</a></li>
			<li><a href="#multi" role="tab" onclick="changeType('1')" data-toggle="tab">多人代付</a></li>
		</ul>
	<form action="" method="post" class="subForm">
	 <input type="hidden" value="${order.orderCode}" name="orderCode">
     <input type="hidden" value="${order.orderId}" name="orderId">
     <input type="hidden" value="${order.orderPrice}" name="orderPayPrice">
     <input type="hidden" value="${order.orderPrice}" name="orderResiduePrice">
     <input type="hidden" value="1" name="status" class="status">
     <input type="hidden" value="0" name="orderPayType" class="orderpaytype">
        <div class="tab-content">
        	<div class="tab-pane active" id="single">
        		<div class="line">
                    <div class="container-fluid"> 
        			<div class="row">
        				<div class="col-sm-4">
        					<div class="wrapper">
        						<div class="hold hold1">
                                    <div class="pie pie1"></div>
                                </div>
							    <div class="hold hold2">
							        <div class="pie pie2"></div>
							    </div>
                                
							    <div class="bg"></div>
							    <div class="circle">1</div>
        					</div><!--wrapper--><br/>
                            留言并分享
        				</div>
                        <div class="col-sm-4">
                            <div class="wrapper">
                                <div class="hold hold1">
                                    <div class="pie pie1"></div>
                                </div>
                                <div class="hold hold2">
                                    <div class="pie pie2"></div>
                                </div>
                                
                                <div class="bg"></div>
                                <div class="circle">2</div>
                            </div><!--wrapper--><br/>
                            朋友全部付款

                        
                        </div>
                        <div class="col-sm-4">
                            <div class="wrapper">
                                <div class="hold">
                                    <div class="pie"></div>
                                </div>
                                <div class="hold hold2">
                                    <div class="pie pie2"></div>
                                </div>
                                
                                <div class="bg"></div>
                                <div class="circle">3</div>
                            </div><!--wrapper--><br/>
                            代付成功

                        </div>
        			</div><!--row-->
                </div>

        		</div><!--line-->

        	</div><!--tab-pane-->
            <div class="tab-pane" id="multi">
                <div class="line">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-sm-3">
                            <div class="wrapper">
                                <div class="hold hold0">
                                    <div class="pie"></div>
                                </div>
                                <div class="hold hold2">
                                    <div class="pie pie2"></div>
                                </div>
                                
                                <div class="bg"></div>
                                <div class="circle">1</div>
                            </div><!--wrapper--><br/>
                            留言并分享

                        </div>
                        <div class="col-sm-3">
                            <div class="wrapper">
                                <div class="hold hold1">
                                    <div class="pie"></div>
                                </div>
                                <div class="hold hold2">
                                    <div class="pie pie2"></div>
                                </div>
                                
                                <div class="bg"></div>
                                <div class="circle">2</div>
                            </div><!--wrapper--><br/>
                            多人参与付款

                        </div>
                        <div class="col-sm-3">
                            <div class="wrapper">
                                <div class="hold hold1">
                                    <div class="pie"></div>
                                </div>
                                <div class="hold hold3">
                                    <div class="pie"></div>
                                </div>
                                
                                <div class="bg"></div>
                                <div class="circle">3</div>
                            </div><!--wrapper--><br/>
                            筹集完金额


                        </div>
                        <div class="col-sm-3">
                            <div class="wrapper">
                                <div class="hold">
                                    <div class="pie"></div>
                                </div>
                                <div class="hold hold2">
                                    <div class="pie pie2"></div>
                                </div>
                                
                                <div class="bg"></div>
                                <div class="circle">4</div>
                            </div><!--wrapper--><br/>
                            代付成功

                        </div>
                    </div>
                </div>
                </div><!--line-->



            </div><!--tab-pane-->
        </div><!--tab-content-->

	</div><!--pay_another-->
    <div style="margin:1em;">
        <div class="form-group">
              <textarea rows="3" class="form-control" name="orderRemark">蛋蛋的忧伤，钱不够了，你能不能帮我先垫付下？</textarea>
        </div>
    </div>
    <div class="pay_area">
      <a href="javascript:;" class="btn-success btn-block" role="button" onclick="subForm(${order.orderId})">下一步</a>
    </div>
    <div class="pay_area">
        <a href="../customer/detail-${(order.orderId)!""}.html" class="btn look_d">查看订单详情</a>
    </div>
    <div class="pay_tishi">
        <p>如10天内代付没凑齐，订单将被取消</p>
        <p>所付款项将退还到付款人账户</p>
    </div>
    <div class="foot">
      <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
    </div><!-- /foot -->

    <script src="${basePath}/js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/fastclick.min.js"></script>
    <script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/js/jquery.lazyload.js"></script>
    <script>
      $(function(){
        FastClick.attach(document.body);
      });
      
      function subForm(obj){
      if($(".orderpaytype").val()==0){
      	$(".subForm").attr("action","../otherpaysingle-"+obj+".html").submit();
      }else{
      	$(".subForm").attr("action","../otherpay-"+obj+".html").submit();
      }
      }
	  function changeType(obj){
	  	$(".orderpaytype").attr("value",obj);
	  }      
    </script>
</body>
</html>