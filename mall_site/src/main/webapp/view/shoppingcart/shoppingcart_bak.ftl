<#include "../include/common.ftl">
<@htmlHead title='${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/index_two/css/header.css" />
<script type="text/javascript" src="${basePath}/js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="${basePath}/js/cloud-zoom.1.0.2.min.js"></script>
<script type="text/javascript">
    function oncheck(){
        var obj=document.getElementsByName('box');
        var chk= $("input[name='box']:checked");
        var price = 0;
        var preprice=0;
        var truebuy = 0;
        var pointDiscount=0;
        var reduceMoney=0;
        var countMoney=0;
        var list = new Array();
        var c=0;
        var _chk = new Array();
        $(".cart_item").each(function(){
            var _this = $(this);
            if(_this.find("input[name='box']").prop("checked") == true && _this.find(".codexType").val() == "14") {
                _chk.push(_this);
            }
        });

        for(var i=0; i<obj.length; i++){
            $(".group_goods"+$(obj[i]).val()).attr("style","");
            if(obj[i].checked){
                $(".group_goods"+$(obj[i]).val()).attr("style","background-color: rgb(255, 253, 238);");
                truebuy+=1;
                var s=$(obj[i]).parents(".cart_item").find(".productPrice").val();
                var n=$(obj[i]).parents(".cart_item").find(".productNum").val();
                var d=$(obj[i]).parents(".cart_item").find(".point_discount").val();
                if(d==null){
                    d=0;
                }
                pointDiscount=accAdd(pointDiscount,accMul(d,n));
                var giftStatus=$(obj[i]).parents(".cart_item").find(".gift_status").val();
                var couponStatus=$(obj[i]).parents(".cart_item").find(".coupon_status").val();
                price=accAdd(price,accMul(s,n));
                var codexType=$(obj[i]).parents(".cart_item").find(".codexType");

                if(codexType.length>0){
                    if(codexType.val()=='1'){
                    }else if(codexType.val()=='4'){
                    }else if(codexType.val()=='5'){
                        var fullPrice=$(obj[i]).parents(".cart_item").find(".fullPrice").val();
                        var reducePrice=$(obj[i]).parents(".cart_item").find(".reducePrice").val();
                        var sumPr=accMul(s,n);
                        if(Subtr(sumPr,fullPrice)>=0){
                            $(obj[i]).parents(".cart_item").find(".preText").html("返现："+reducePrice);
                            if(Subtr(sumPr,reducePrice)>0){
                                preprice=accAdd(preprice,reducePrice);
                            }else{
                                preprice=accAdd(preprice,Subtr(sumPr,0.01));
                            }

                        }else{
                            $(obj[i]).parents(".cart_item").find(".preText").html("");
                        }
                    }else if(codexType.val()=='8'){
                        var fullPrice=$(obj[i]).parents(".cart_item").find(".fullPrice").val();
                        var fullbuyDiscount=$(obj[i]).parents(".cart_item").find(".fullbuyDiscount").val();
                        var sum=accMul(s,n);
                        var f=Subtr(sum,fullPrice);
                        $(obj[i]).parents(".cart_item").find(".preText").html("");
                        if(f>=0){
                            $(obj[i]).parents(".cart_item").find(".preText").html("折扣："+fullbuyDiscount);
                            if(Subtr(sum,accMul(sum,Subtr(1,fullbuyDiscount)))<0.01){
                                preprice=accAdd(preprice,Subtr(sum,0.01));
                            }else{
                                preprice=accAdd(preprice,accMul(sum,Subtr(1,fullbuyDiscount)));
                            }
                        }
                    }else if(codexType.val()=='13'){

                        var $index = 0;
                        $(".cart_goods").each(function(){
                            if($(this).find(".packagebuyDiscount").val() != undefined && $(this).find(".g_ckeck:checked").length > 0) {
                                //alert(1);
                                $index += 1;
                            }
                        });

                        var fullNo=$(obj[i]).parents(".cart_item").find(".packagesNo").val();
                        var fullbuyNoDiscount=$(obj[i]).parents(".cart_item").find(".packagebuyDiscount").val();
                        var isMeetCondition=$(obj[i]).parents(".cart_item").find(".isMeetCondition").val();
                        var sum=accMul(s,n);
                        $(obj[i]).parents(".cart_item").find(".preText").html("");
                        if(isMeetCondition==1 && fullNo <= $index){
                            $(obj[i]).parents(".cart_item").find(".preText").html("折扣："+fullbuyNoDiscount);
                            if(Subtr(sum,accMul(sum,Subtr(1,fullbuyNoDiscount)))<0.01){
                                preprice=accAdd(preprice,Subtr(sum,0.01));
                            }else{
                                reduceMoney=accAdd(reduceMoney,accMul(sum,Subtr(1,fullbuyNoDiscount)));
                                preprice=accAdd(preprice,accMul(sum,Subtr(1,fullbuyNoDiscount)));
                            }
                        }
                    }else if(codexType.val()=='14'){
                        var fullNo=$(obj[i]).parents(".cart_item").find(".countNo").val();
                        var fullbuyCountMoney=$(obj[i]).parents(".cart_item").find(".countMoney").val();
                        var isCondition=$(obj[i]).parents(".cart_item").find(".isCondition").val();
                        var marketingId=$(obj[i]).parents(".cart_item").find(".marketingId").val();
                        $(obj[i]).parents(".cart_item").find(".preText").html("");
                        if(isCondition==1){
                            if(n==1){
                                c+=1;
                                if(fullNo >= c) {
                                    list.push(marketingId);
                                }
                            }
                        }
                    }else if(codexType.val()=='15'){
                        var discountInfo=$(obj[i]).parents(".cart_item").find(".discountInfo").val();
                        var discountFlag=$(obj[i]).parents(".cart_item").find(".discountFlag").val();
                        var discountPrice=$(obj[i]).parents(".cart_item").find(".discountPrice").val();
                        var sum=accMul(s,n);
                        $(obj[i]).parents(".cart_item").find(".preText").html("");
                        $(obj[i]).parents(".cart_item").find(".preText").html("折扣："+discountInfo);
                        if(Subtr(sum,accMul(sum,Subtr(1,discountInfo)))<0.01){
                            preprice=accAdd(preprice,Subtr(sum,0.01));
                        }else{
                            //if(discountFlag == '0') {
                            if(discountPrice != '-1') {
                                preprice=accAdd(preprice,Subtr(sum,accMul(discountPrice,n)));
                            }else{
                                preprice=accAdd(preprice,accMul(sum,Subtr(1,discountInfo)));
                            }

                            /**}else if(discountFlag == '1') {
											if(accMul(s,discountInfo).split(".")[1][1] != 0) {
									 			preprice=accAdd(preprice,Subtr(s,Subtr(parseInt(accMul(s,discountInfo)*100),accMul(s,discountInfo).split(".")[1][1])/100)*n);
									 		}else{
									 			preprice=accAdd(preprice,accMul(sum,Subtr(1,discountInfo)));
									 		}
										}else if(discountFlag == '2') {
											if(accMul(s,discountInfo).split(".")[1][0] != 0) {
												preprice=accAdd(preprice,Subtr(s,Subtr(Subtr(parseInt(accMul(s,discountInfo)*100),accMul(s,discountInfo).split(".")[1][1]),accMul(s,discountInfo).split(".")[1][0]*10)/100)*n);
											}else{
												preprice=accAdd(preprice,accMul(sum,Subtr(1,discountInfo)));
											}
										}*/
                        }
                    }
                }else{
                    preprice=preprice;
                }
            }
            <!--优惠劵-->
            if(couponStatus!=null){
                var sum=accMul(s,n)
                var prices=	Subtr(sum,preprice);
                var fullPrice=$(obj[i]).parents(".cart_item").find(".coupon_price").val();
                $(obj[i]).parents(".cart_item").find(".coupon_list").hide();
                if(Subtr(prices,fullPrice)>=0){
                    $(".couponText"+$(obj[i]).val()).html("已满"+fullPrice+" 送优惠劵")
                    $(obj[i]).parents(".cart_item").find(".coupon_list").show();
                }else{
                    $(".couponText"+$(obj[i]).val()).html("未满"+fullPrice+" 不送优惠劵")
                }
            }

        }

        if(list != null) {
            for(var k=0;k<unique(list).length; k++) {
                var countfullmoney=0;
                var cut =0;
                var num= _chk.length;
                var sum=0;
                var count = 0;
                for(var i=0; i<chk.length; i++) {
                    var codexType=$(chk[i]).parents(".cart_item").find(".codexType");
                    var s=$(chk[i]).parents(".cart_item").find(".productPrice").val();
                    var n=$(chk[i]).parents(".cart_item").find(".productNum").val();

                    if(codexType.val()=='14') {
                        var fullNo=$(chk[i]).parents(".cart_item").find(".countNo").val();
                        var marketingId=$(chk[i]).parents(".cart_item").find(".marketingId").val();
                        if(marketingId == list[k]) {
                            count += 1;

                            if(num==fullNo){
                                var fullbuyCountMoney = $(chk[i]).parents(".cart_item").find(".countMoney").val();
                                countMoney = fullbuyCountMoney;
                                sum = accMul(s, n);
                                countfullmoney = accAdd(countfullmoney, sum);

                            }else if(num<fullNo){

                                countfullmoney=0
                            }else{
                                var fullbuyCountMoney = $(chk[i]).parents(".cart_item").find(".countMoney").val();
                                countMoney = fullbuyCountMoney;
                                sum = accMul(s, n);
                                countfullmoney=accAdd(countfullmoney,sum);
                                if(count>fullNo){
                                    cut = accAdd(cut,sum);
                                }
                            }
                        }
                    }
                }
                if(countfullmoney != 0) {
                    reduceMoney=accAdd(reduceMoney,Subtr(Subtr(countfullmoney,countMoney),cut));
                    preprice=accAdd(preprice,Subtr(Subtr(countfullmoney,countMoney),cut));
                }
            }
        }


        $("#zj").html('¥'+formatCurrency(price));
        $("#fx").html('¥-'+formatCurrency(preprice));
        $("#zk").html('¥-'+formatCurrency(pointDiscount));
        $("#suprice").html('¥'+formatCurrency(Subtr(Subtr(price,preprice),pointDiscount)));
        $("#truebuy").html(truebuy);

        for(var i=0; i<obj.length; i++){
            if(obj[i].checked){
                $(".check_all").prop("checked","checked");
            }else{
                $(".check_all").prop("checked","");
                return null;
            }
        }
    }
    function formatCurrency(num) {
        num = num.toString().replace(/\$|\,/g,'');
        if(isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num*100+0.50000000001);
        cents = num%100;
        num = Math.floor(num/100).toString();
        if(cents<10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
            num = num.substring(0,num.length-(4*i+3))+','+
            num.substring(num.length-(4*i+3));
        return (((sign)?'':'-') + num + '.' + cents);
    }
    function unique(arr) {
        var ret = [];
        var hash = {};
        for (var i = 0; i < arr.length; i++) {
            var item = arr[i];
            var key = typeof(item) + item;
            if (hash[key] !== 1) {
                ret.push(item);
                hash[key] = 1;
            }
        }
        return ret;
    }

    function oncheckAll(obj){

        var str="";
        var status="";
        var objs=document.getElementsByName('box');
        if(objs.length==0){
            return ture;
        }
        if(obj.checked==true){
            status=1;
            for(var i=0;i<objs.length;i++){
                if($(objs)[i].checked==false){
                    $(objs)[i].checked = true;
                }
                str+="shoppingId="+$(objs)[i].value+"&";
            }
        }else{
            status=0;
            for(var i=0;i<objs.length;i++){
                if($(objs)[i].checked==true){
                    $(objs)[i].checked = false;
                }
                str+="shoppingId="+$(objs)[i].value+"&";

            }
        }
        $.ajax({
            type: "POST",
            url: "changeshopstatusbyparams.htm",
            data: str+"&status="+status,
            success: function(msg){
                var objs2=document.getElementsByName('box');
                for(var i=0;i<objs2.length;i++){
                    $($(objs2)[i]).attr("role-status",msg);
                }
            }
        });
        oncheck();
    }
</script>
</@htmlHead>
<@htmlBody>
  <#if (topmap.temp)??>
            <#if (topmap.temp.tempId==8)>
                <#include "../index/newtop3.ftl">
            <#elseif (topmap.temp.tempId==9)>
                <#include "../index/newtop4.ftl">
            <#elseif (topmap.temp.tempId==10)>
                <#include "../index/newtop5.ftl">
            <#elseif (topmap.temp.tempId==11)>
                <#include "../index/newtop6.ftl">
            <#elseif (topmap.temp.tempId==12)>
                <#include "../index/newtop7.ftl">
            <#elseif (topmap.temp.tempId==13)>
                <#include "../index/newtop8.ftl">
            <#elseif (topmap.temp.tempId==14)>
                <#include "../index/newtop9.ftl">
            <#elseif (topmap.temp.tempId==15)>
                <#include "../index/newtop10.ftl">
            <#elseif (topmap.temp.tempId==16)>
				<#include "../index/newtop11.ftl">
			<#elseif (topmap.temp.tempId==17)>
				<#include "../index/newtop12.ftl">
			<#elseif (topmap.temp.tempId==18)>
				<#include "../index/newtop13.ftl">
			<#elseif (topmap.temp.tempId==19)>
				<#include "../index/newtop14.ftl">
            <#else>
                <#include "../index/newtop.ftl"/>
            </#if>
        </#if>
    <div class="container">
	<div class = "logo fl head2">
		<!--
        <a href="${basePath}/index.html"><img src="${basePath}/index_two/images/logo.png" alt="" /></a>
		-->

		<#if (topmap.temp)??>
		 <#if (topmap.temp.tempId!=10)>
        <a href="${topmap.systembase.bsetAddress}"><img src="${topmap.systembase.bsetLogo}" alt="" style="width:165px;height:40px;"/></a>
        </#if>
        </#if>

    </div>
    <div style="font-family: arial;">
        <div class="head_s mb20">
            <div class="fr w700 pt10">
                <div class="flow_progress1">
                    <ul>
                        <li class="step1">1.查看购物车</li>
                        <li class="step2">2.填写核对订单信息</li>
                        <li class="step3">3.提交订单成功</li>
                    </ul>
                </div>
            </div>
            
            <div class="cb"></div>
        </div><!-- /head_s -->
        <div class="flow_title">
        	<b></b>
        	<span>
        		<#if customerId??>
        		<#else>
        		建议您立即<a href="login.html">登录</a>，以确保顺利进行购物。
        	</#if>
        	</span>
        </div>
        <div class="cart_box">
        	<div class="cart_head">
            	<div class="thead t_check">
                	<input type="checkbox" class="check_all" onclick="oncheckAll(this);" />
                    <label>全选</label>
                </div>
                <div class="thead t_goods">商品</div> 
                <div class="thead t_price">销售价</div>
                <div class="thead t_promotion">优惠</div>
                <div class="choose_area fl pr">
                            <div class="area_text ">${wareUtil.provinceName}<b></b></div>
                            <div class="locate_cont">
                                <ul class="locate_tabs clearfix">
                                    <li class="cur show_province"><a href="javascript:;"><span class="province_text">${wareUtil.provinceName}</span><b></b></a></li>
                                    <li class="show_city" ><a href="javascript:;" ><span class="city_text">${wareUtil.cityName}</span><b></b></a></li>
                                    <li class="show_distinct"><a href="javascript:;" ><span class="distinct_text">${wareUtil.distinctName}</span><b></b></a></li>
                                </ul><!--/locate_tabs-->
                                <div class="locate_wp">
                                    <ul style="display: block;" class="locate_list clearfix province_list show">
                                    </ul><!--/locate_list-->
                                    <ul class="locate_list clearfix city_list">
                                    </ul><!--/locate_list-->
                                    <ul class="locate_list clearfix distinct_list">
                                    </ul><!--/locate_list-->
                                </div><!--/locate_wp-->
                                <a class="close_area" href="javascript:;"></a>
                            </div><!--/locate_cont-->
                        </div><!--/choose_area-->
                <div class="thead t_count">数量</div>
                <div class="thead t_action">操作</div>  
            </div><!-- /cart_head --> 
      <form name="subForm" id="subForm" action="${basePath}/order/suborder.html" method="post">
      <#if pb.list??>
          <#list pb.list as cart>
          <#if cart.fitId??>
          <div  id="cart${cart.shoppingCartId}" >
               <div class="cart_goods" <#if cart.shoppingStatus?? && cart.shoppingStatus=='1'>style="background-color: rgb(255, 253, 238);"</#if>>
                    	<div class="cart_item">
                        	<div class="cell g_check">
                        		<#if cart.goodsGroupVo.groupDelflag!='1' && cart.goodsGroupVo.stock gt 0 >
                        		 	     <input type="checkbox" class="g_ckeck" onclick="oncheck();"
                                         <#if cart.shoppingStatus?? && cart.shoppingStatus=='1'>
                                            checked="true"
                                         </#if>  role-status="<#if cart.shoppingStatus??>${cart.shoppingStatus}</#if>"
                                         onchange="changeStatus(this)";
                                           name="box" value="${cart.shoppingCartId}"/>
                        		<#else>
                        			&nbsp;
                        		</#if>
                        	</div>
                            <div class="cell g_goods"><b>[套装] ${cart.goodsGroupVo.groupName}</b></div>
                            <div class="cell g_price"><b>
		                        	¥${(cart.goodsGroupVo.groupPreferamount)?string("0.00")} 
                            <input type="hidden" value="100" id="goodsInfoStock_${cart.shoppingCartId}">
                            <input type="hidden" class="productPrice" value="${cart.goodsGroupVo.groupPreferamount}">
                            </b></div>
                            <div class="cell g_promotion" style="height:10px;"></div>
                            <div class="cell g_stock">&nbsp;</div>
                            <div class="cell g_count">
                                <div class="count">
                                    <a href="javascript:void(0);" class="decrement" onclick="mit(this,${cart.shoppingCartId});"> - </a>
                                    <input type="text" class="text w30 productNum" value="${cart.goodsNum}" id="goods_num_${cart.shoppingCartId}"  onblur="opblur(this,${cart.shoppingCartId},1000);">
                                    <a href="javascript:void(0);" class="increment" onclick="add(this,${cart.shoppingCartId});"> + </a>
                                    <input type="hidden" class="sumNum" value="${cart.goodsGroupVo.stock}">
                                     <input type="hidden" class="xzNum" value="${cart.goodsNum}"/>
                                </div>
                            </div>
                             
                            <div class="cell g_action">
                                <div><a class="g_delete" href="javascript:void(0);" onclick="del(${cart.shoppingCartId},${cart.goodsInfoId})">删除</a></div>
                            </div>
                            <div class="cb"></div>
                        </div><!--/cart_item-->
                    </div><!--/cart_goods-->
                    <#list cart.goodsGroupVo.productList as products>
                    <div class="cart_goods group_goods${cart.shoppingCartId}"   <#if cart.shoppingStatus?? && cart.shoppingStatus=='1'>style="background-color: rgb(255, 253, 238);"</#if> >
                        <div class="cart_item">
                            <div class="cell g_check">
                                <!---->
                            </div>
                            <div class="cell g_goods">
                                <div class="img">
                                    <a href="${basePath}/item/${products.productDetail.goodsInfoId}.html" target="_blank" title="${basePath}/item/${products.productDetail.goodsInfoId}.html">
                                        <img style="width:50px;height:50px;" title="${products.productDetail.goodsInfoName}" alt="${products.productDetail.goodsInfoName}" src="${products.productDetail.goodsInfoImgId}">
                                    </a>
                                </div>
                                <div class="name">
                                
                                    <a href="${basePath}/item/${products.productDetail.goodsInfoId}.html" target="_blank" title="${basePath}/item/${products.productDetail.goodsInfoId}.html">
                                        ${products.productDetail.goodsInfoName}
                                    </a>
                                </div>
                                <div class="cb">
                                </div>
                                <div class="gift mt10">
                                </div>
                            </div>
                            <div class="cell g_price">
                            <div>
                            	
                            </div>
                                 &nbsp;
                               	
                            </div>
                            <div class="cell g_promotion">
                            </div>
                            <div class="cell g_stock">
                                <span class="light">
                               
                                </span>
                            </div>
                            <div class="cell g_count">
                                <div class="count">
                                    1*1
                                </div>
                                <div class="red">
                                    	<#if products.productDetail.goodsInfoStock<=20 >   
				                    	仅剩${products.productDetail.goodsInfoStock}件！
				           		 </#if>
                                </div>
                            </div>
                            <div class="cell g_action">
                                
                            </div>
                            <div class="cb">
                            </div>
                        </div>
                        
                        </div>
                        </#list>
                 </div>
          <#else>
        
    				<!--单品商品-->
          			<#if cart.marketing??>
          			
          			<div class="activity">
			            	<div class="fl w500 ml15">
			                  asdfasdfasdfasdfs
			                   </div>
			                   
			                   </div>
          			
                	</#if>
	            <div class="cart_goods" id="cart${cart.shoppingCartId}" <#if cart.shoppingStatus?? && cart.shoppingStatus=='1'>style="background-color: rgb(255, 253, 238);"</#if>>
            	<div class="cart_item">
                    <div class="cell g_check">
                    		<!--<#if (cart.goodsDetailBean.productVo.goodsInfoStock>0) >-->              
                    		<#if cart.goodsDetailBean.productVo.goodsInfoStock!=0 && cart.goodsDetailBean.productVo.goodsInfoDelflag='0' && cart.goodsDetailBean.productVo.goodsInfoAdded=='1'>
				                    	 <input type="checkbox" class="g_ckeck" onclick="oncheck();"
				                    	 <#if cart.shoppingStatus?? && cart.shoppingStatus=='1'>
					                    	checked="true"
				                    	 </#if>  role-status="<#if cart.shoppingStatus??>${cart.shoppingStatus}</#if>"
				                    	 onchange="changeStatus(this)";
				                    	   name="box" value="${cart.shoppingCartId}"/>
				            </#if>
				        	<!--</#if>--> 
                    </div>
                    <div class="cell g_goods">
                        <div class="img">
                            <a href="${basePath}/item/${cart.goodsDetailBean.productVo.goodsInfoId}.html" target="_blank" title="${basePath}/item/${cart.goodsDetailBean.productVo.goodsInfoId}.html"><img style="width:50px;height:50px;" title="${cart.goodsDetailBean.productVo.productName}" alt="${cart.goodsDetailBean.productVo.productName}" src="<#if cart.goodsDetailBean.productVo.goodsInfoImgId??>${cart.goodsDetailBean.productVo.goodsInfoImgId}</#if>" /></a>
                        </div>
                        <div class="name">  
                            <a href="${basePath}/item/${cart.goodsDetailBean.productVo.goodsInfoId}.html" target="_blank" title="${basePath}/item/${cart.goodsDetailBean.productVo.goodsInfoId}.html">${cart.goodsDetailBean.productVo.productName}</a>
                            <input type="hidden" value="${cart.goodsDetailBean.productVo.goodsInfoId}" class="pro_id${cart.shoppingCartId}">
                        </div>
                        <div class="cb"></div>  
                         <#assign preprice=0>
                    	<#if cart.marketing??>
				          			
							  		 <#if (cart.marketing.priceOffMarketing?? )>
							  		 	 	<input type="hidden" class="codexType" value="1"/>
							  		 	 	<input type="hidden" class="offValue" value="${cart.marketing.priceOffMarketing.offValue}"/>
				              	 	 		 <#assign preprice="${cart.marketing.priceOffMarketing.offValue*cart.goodsNum}">
				              	 	 </#if>
				                    <#if cart.marketing.discountMarketing??> 
				                    		<input type="hidden" class="codexType" value="4"/>
							  		 	 	<input type="hidden" class="discountValue" value="${cart.marketing.discountMarketing.discountValue}"/>
				              				<#assign preprice="${(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-(((cart.marketing.discountMarketing.discountValue)*cart.goodsDetailBean.productVo.goodsInfoPreferPrice)*cart.goodsNum)}">
				              		 </#if>
				                 	<#if cart.marketing.fullbuyReduceMarketing??>
					                		<input type="hidden" class="codexType" value="5"/>
					                		<input type="hidden" class="fullPrice" value="${cart.marketing.fullbuyReduceMarketing.fullPrice}"/>
							  		 	 	<input type="hidden" class="reducePrice" value="${cart.marketing.fullbuyReduceMarketing.reducePrice}"/>
									</#if>
				            
				                    <#if cart.marketing.fullbuyDiscountMarketing??>
					               			<input type="hidden" class="codexType" value="8"/>
					                		<input type="hidden" class="fullPrice" value="${cart.marketing.fullbuyDiscountMarketing.fullPrice}"/>
							  		 	 	<input type="hidden" class="fullbuyDiscount" value="${cart.marketing.fullbuyDiscountMarketing.fullbuyDiscount}"/>
					               			<#assign preprice="${(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-(((cart.marketing.fullbuyDiscountMarketing.fullbuyDiscount)*cart.goodsDetailBean.productVo.goodsInfoPreferPrice)*cart.goodsNum)}">
				 				    </#if>
				 				    
				 				    <#if cart.marketing.fullbuyNoDiscountMarketing??>
					 				    <#if cart.marketing.fullbuyNoDiscountMarketing.isMeetCondition??>
					 				    	<#if cart.marketing.fullbuyNoDiscountMarketing.isMeetCondition = '1'>
						 				    	<input type="hidden" class="codexType" value="13"/>
						 				    	<input type="hidden" class="packagesNo" value="${cart.marketing.fullbuyNoDiscountMarketing.packagesNo}"/>
						 				    	<input type="hidden" class="packagebuyDiscount" value="${cart.marketing.fullbuyNoDiscountMarketing.packagebuyDiscount}"/>
						 				    	<input type="hidden" class="isMeetCondition" value="${cart.marketing.fullbuyNoDiscountMarketing.isMeetCondition}"/>
						 				    	<#assign preprice="${(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-(((cart.marketing.fullbuyNoDiscountMarketing.packagebuyDiscount)*cart.goodsDetailBean.productVo.goodsInfoPreferPrice)*cart.goodsNum)}">
						 				    </#if>	
					 				    </#if> 
				 				    </#if>
				 				    
				 				    <#if cart.marketing.fullbuyNoCountMarketing??>
				 				    	<#if cart.marketing.fullbuyNoCountMarketing.isMeetCondition??>
				 				    		<#if cart.marketing.fullbuyNoCountMarketing.isMeetCondition = '1'>
				 				    			<input type="hidden" class="codexType" value="14"/>
				 				    			<input type="hidden" class="countNo" value="${cart.marketing.fullbuyNoCountMarketing.countNo}"/>
				 				    			<input type="hidden" class="countMoney" value="${cart.marketing.fullbuyNoCountMarketing.countMoney}"/>
				 				    			<input type="hidden" class="isCondition" value="${cart.marketing.fullbuyNoCountMarketing.isMeetCondition}"/>
				 				    			<input type="hidden" class="marketingId" value="${cart.marketing.fullbuyNoCountMarketing.marketingId}"/>
				 				    		</#if>
				 				    	</#if>
				 				    </#if>
				 				    
				 				    <#if cart.marketing.preDiscountMarketing??>
				 				    		<input type="hidden" class="codexType" value="15"/>
				 				    		<input type="hidden" class="goodsId" value="${cart.marketing.preDiscountMarketing.goodsId}"/>
				 				    		<input type="hidden" class="discountInfo" value="${cart.marketing.preDiscountMarketing.discountInfo}"/>
				 				    		<input type="hidden" class="discountFlag" value="${cart.marketing.preDiscountMarketing.discountFlag}"/>
                                            <#if cart.marketing.preDiscountMarketing.discountPrice??>
                                                <input type="hidden" class="discountPrice" value="${cart.marketing.preDiscountMarketing.discountPrice}"/>
                                                <#assign preprice="${(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-(cart.marketing.preDiscountMarketing.discountPrice*cart.goodsNum)}">
                                            <#else>
                                                <input type="hidden" class="discountPrice" value="-1"/>
                                                <#assign preprice="${(cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)-(cart.marketing.preDiscountMarketing.discountInfo*cart.goodsDetailBean.productVo.goodsInfoPreferPrice*cart.goodsNum)}">
                                            </#if>
				 				    </#if>
			   
          					</#if>     
                        <div class="gift mt10">
                        		<#if cart.marketing??>
				          			
							  		
						                 <#if cart.marketing.giftList??>
							              <#if cart.marketing.fullbuyPresentMarketing??>
						                 <#else> 	
							                	  <#list cart.marketing.giftList as gift>
							                		<p class="light">[赠品] 
							                		${gift.giftName }${gift.giftDesc }</p>
							                	  </#list>
						              	</#if>
						              	</#if>
						                <#if cart.marketing.couponList??>
						                 <#if cart.marketing.fullbuyCouponMarketing??>
						                <#else>		
						                 		<#list cart.marketing.couponList as coupon>
							                		<p class="light">[优惠券]${coupon.couponName }</p>
							                	  </#list>
							                </td>
						                </#if>
						                </#if>
						                  <#if cart.marketing.fullbuyPresentMarketing??>
						                  <#if cart.marketing.couponList??>
						                  <#else>
						                  			<input type="hidden" value="1" class="gift_status">
						                  			<input type="hidden" value="${cart.marketing.fullbuyPresentMarketing.fullPrice}" class="gift_price">
							                	  		<div class="gift_list">
							                	  		<#list cart.marketing.giftList as gift>
							                				<p class="light">[赠品]${gift.giftName }${gift.giftDesc }</p>
							                	  		</#list>
							                	  	 	</div>
						              	</#if >
						              	</#if>
						                <#if cart.marketing.fullbuyCouponMarketing??>
						                  		<input type="hidden" value="1" class="coupon_status">
						                  		<input type="hidden" value="${cart.marketing.fullbuyCouponMarketing.fullPrice}" class="coupon_price">
						                  		<div class="coupon_list">
							                 		  <#list cart.marketing.couponList as coupon>
								                		<p class="light">[优惠券]${coupon.couponName }</p>
								                	  </#list>
							                	  </div>
							                </td>
						        </#if>
						</#if>
                        </div>
                    </div>
                    <div class="cell g_price">
                       	<#assign offValue=0>
                       		<#if cart.marketing??>
                   					  <#if (cart.marketing.priceOffMarketing?? )>
                   					  		 <#if ((cart.goodsDetailBean.productVo.goodsInfoPreferPrice-cart.marketing.priceOffMarketing.offValue)?number &gt; 0 ) > 
									               <#assign offValue="${cart.marketing.priceOffMarketing.offValue}">
											 <#else>
													<#assign offValue="${cart.goodsDetailBean.productVo.goodsInfoPreferPrice-0.01}">
											 </#if>	
							  		 	 	<input type="hidden" class="codexType" value="1"/>
				              	 	 </#if>
				                    <#if cart.marketing.discountMarketing??> 
				              				 <#if (cart.goodsDetailBean.productVo.goodsInfoPreferPrice-((1-cart.marketing.discountMarketing.discountValue)*cart.goodsDetailBean.productVo.goodsInfoPreferPrice))?number!=0>
											   	<#assign offValue="${(cart.goodsDetailBean.productVo.goodsInfoPreferPrice)-(((cart.marketing.discountMarketing.discountValue)*cart.goodsDetailBean.productVo.goodsInfoPreferPrice))}">
											</#if>
				              		 </#if>
				              </#if>
                        <!--折扣价格-->
                            ¥ <span class="goodsPrice">
                            <#if cart.pointDiscount??>
                                <input type="hidden" class="point_discount" value="${(cart.goodsDetailBean.productVo.goodsInfoPreferPrice-offValue)?number-((cart.goodsDetailBean.productVo.goodsInfoPreferPrice-offValue)*cart.pointDiscount)?number}">
                            ${(((cart.goodsDetailBean.productVo.goodsInfoPreferPrice-offValue)*cart.pointDiscount)?number)?string("0.00")}
                                <input type="hidden" class="productPrice" value="${((cart.goodsDetailBean.productVo.goodsInfoPreferPrice-offValue)*cart.pointDiscount)?number}" >
                            <#else>
                                <input type="hidden" class="point_discount" value="0">
                            ${(cart.goodsDetailBean.productVo.goodsInfoPreferPrice-offValue?number)?string("0.00")}
                                <input type="hidden" class="productPrice" value="${cart.goodsDetailBean.productVo.goodsInfoPreferPrice-offValue?number}" >
                            </#if>
                            </span>
                        <#--<!--折扣价格&ndash;&gt;-->
                              <#--¥ <span class="goodsPrice">-->
                              <#--<#if cart.pointDiscount??>-->
                                <#--<input type="hidden" class="point_discount" value="${(cart.goodsDetailBean.productVo.goodsInfoPreferPrice-offValue)?number-((cart.goodsDetailBean.productVo.goodsInfoPreferPrice-offValue)*cart.pointDiscount)?number}">-->
                              <#--<#else>-->
                                <#--<input type="hidden" class="point_discount" value="0">-->
                              <#--</#if>-->
                                 <#--${(cart.goodsDetailBean.productVo.goodsInfoPreferPrice?number)?string("0.00")}-->
                                 <#--<input type="hidden" class="productPrice" value="${cart.goodsDetailBean.productVo.goodsInfoPreferPrice?number}" >-->
                              <#--</span> -->
                       
                    </div>
                   
                    <div class="cell g_promotion">
                    	<#if cart.marketing??>
		                    	<span class="preText" style="background-color:#7ABD54;">
                    		<#if cart.marketing.fullbuyReduceMarketing??>
		                    	<#if cart.marketing.fullbuyReduceMarketing.fullPrice<(cart.goodsDetailBean.productVo.goodsInfoPreferPrice)>
		                    		返现：¥${preprice}
		                    		</#if>
		                    </#if>
		                    <#if cart.marketing.fullbuyNoDiscountMarketing??>
		                    <#if cart.marketing.isMeetCondition??>
								<#if cart.marketing.isMeetCondition=='1'>
									打折：¥${preprice}
								</#if>
							</#if>	
							</#if>
		                    	</span>
                    	</#if>
                    </div>
                    <div class="cell g_stock">
                        <span class="light">
                        <#if cart.goodsDetailBean.productVo.goodsInfoDelflag='1' || cart.goodsDetailBean.productVo.goodsInfoAdded!='1' >
                        	已下架
                        <#else>
                        	<#if (cart.goodsDetailBean.productVo.goodsInfoStock>0)>
                        		有货
                        	<#else>
                        		无货
                        	</#if>    
                        </#if>
          				          
                    	</span>
                    </div>
                    <div class="cell g_count"> 
                        <div class="count">
                            <a href="javascript:void(0);" class="decrement" onclick="mit(this,${cart.shoppingCartId});">-</a>
                            <input type="text" class="text w30 productNum" value="<#if cart.goodsNum??>${cart.goodsNum}</#if>" id="goods_num_${cart.shoppingCartId}" onblur="opblur(this,${cart.shoppingCartId},${cart.goodsDetailBean.productVo.goodsInfoStock});" />
                            <a href="javascript:void(0);" class="increment" onclick="add(this,${cart.shoppingCartId});">+</a>
                            <input type="hidden" class="sumNum" value="${cart.goodsDetailBean.productVo.goodsInfoStock}"/>
                            <input type="hidden" class="xzNum" value="<#if cart.goodsNum??>${cart.goodsNum}</#if>"/>
                             <#if cart.marketing??>
                          	 <#if cart.marketing.fullbuyNoCountMarketing?? >
                         		<input type="hidden" class="codexType" value="14"/> 
                        	</#if>
                        	</#if>
                        </div>
                        <div class="red">
                       		 <#if cart.marketing??>
                          	 <#if cart.marketing.limitBuyMarketing?? >
                         	<input type="text" value="${cart.marketing.limitBuyMarketing.limitCount}"> 
                        	</#if>
                        	</#if>
				          	<#if  cart.goodsDetailBean.productVo.goodsInfoStock<=20 >   
				          
				                    	仅剩${cart.goodsDetailBean.productVo.goodsInfoStock}件！
				            </#if>
				            <input type="hidden" value="${cart.goodsDetailBean.productVo.goodsInfoStock}" id="goodsInfoStock_${cart.shoppingCartId}">
                        </div>
                    </div>  
                    <div class="cell g_action">   
                        <div>
                            <a class="g_delete" href="javascript:void(0);" onclick="del(${cart.shoppingCartId},${cart.goodsDetailBean.productVo.goodsInfoId})">删除</a>
                        </div>
                        <div>
                        
                        	
                            <a class="change_promotion" href="javascript:void(0);" onclick="javascript:$('.promotion_dialog').hide();">修改优惠</a>
                               <div class="dialog promotion_dialog">
                               <div class="dialog-outer">
                                    <span class="dialog-bg dialog-bg-n">
                                    </span>
                                    <span class="dialog-bg dialog-bg-ne">
                                    </span>
                                    <span class="dialog-bg dialog-bg-e">
                                    </span>
                                    <span class="dialog-bg dialog-bg-se">
                                    </span>
                                    <span class="dialog-bg dialog-bg-s">
                                    </span>
                                    <span class="dialog-bg dialog-bg-sw">
                                    </span>
                                    <span class="dialog-bg dialog-bg-w">
                                    </span>
                                    <span class="dialog-bg dialog-bg-nw">
                                    </span>
                                    <div class="dialog-inner">
                                        <div class="dialog-toolbar clearfix">
                                            <a class="dialog-close" href="javascript:void(0);" title="关闭" onclick="javascript:$('.promotion_dialog').hide();">
                                                关闭
                                            </a>
                                            <h3 class="dialog-title">
                                                请选择优惠
                                            </h3>
                                        </div>
                                        <div class="dialog-content clearfix">
                                        <#list pro as pr>
                                        <#if pr.isShow=="0">
                                            <div class="p10 tc w300">
                                                <label>${pr.preferentialName}:</label>
                                                
                                                <select style="width:120px;"  id="market${pr.groupId}${cart.shoppingCartId}">
                                                    		<option value="">不使用优惠</option>
											                  <#if  (cart.marketingList?? && cart.marketingList?size>0)>    
											                  <#list cart.marketingList as marketing>
											                  <#if marketing??>
											                    		<#if pr.groupId==marketing.groupId> 
											                    			<option value="${marketing.marketingId}" <#if cart.marketingId??>
											                    			<#if cart.marketingId=marketing.marketingId>selected="selected"</#if></#if>
											                    			<#if cart.marketingActivityId??>
											                    			<#if cart.marketingActivityId=marketing.marketingId>selected="selected"</#if></#if>
											                    			
											                    			>${marketing.marketingName}</option>
											                  			</#if>
											                  	</#if>
											                  </#list>
											                  </#if>
                                                </select>
                                            </div>
                                            </#if>
                                         </#list>
                                            <div class="tc p15">
                                                <a class="red_btn" href="javascript:void(0);" onclick="changemarketing(${cart.shoppingCartId});">确定</a>
                                                <a class="light_btn2" href="javascript:void(0);" onclick="javascript:$('.promotion_dialog').hide();">取消</a>
                                            </div>
                                        </div><!-- dialog-content -->
                                    </div><!-- dialog-inner -->
                                </div><!-- dialog-outer -->
                            </div><!-- /dialog -->
                            
                        </div>
                    </div><!-- cell g_action -->
                    <div class="cb"></div>
                </div><!-- cart_item -->
            </div><!-- /cart_goods -->
            </#if>
          </#list>  
          </#if>
          
      	<input  type="hidden" id="csrfNo" name="CSRFToken" value="${sx}" />
         </form> 
        
          
            <div class="cart_tools p10 lh180">
            <div class="fl w200">
            </div>
            	<div class="fr w200">
                	<p><span class="fr" id="zj"></span>总计：</p>
                    <p><span class="fr" id="fx">-¥20.00</span>返现：</p>
                    <p><span class="fr" id="zk"></span>会员折扣：</p>
                </div>
                <div class="fr mr10">
                	<b class="red" id="truebuy"></b>件商品
                </div>
                <div class="fl"> 
                	<!--<a href="javascript:void(0);" class="delete">删除所选商品</a>-->
                   <!--<a href="javascript:void(0);" class="piece">凑单商品</a>-->
                  
                </div>  
                <div class="cb"></div>
            </div><!-- /cart_tools -->
            
            <div class="cart_total">
            	<div class="fr f14 fb mr20 w300">
                	<span class="fr f20 red" id="suprice"></span>
                    总计（不含运费）：
                </div>
            </div><!-- /cart_total -->
            
        </div><!-- /cart_box -->
        
        <div class="cart_btn mt10">
        	<a class="check_btn fr" href="javascript:void(0);" onclick="onpay();">去结算</a>
            <a class="continue_shopping" href="${basePath}/index.html">继续购物</a>
        </div><!-- /cart_btn --> 
        <!--
        <div class="cart_removed" style="">
            <div class="r_title">
                已删除商品，您可以重新购买或加关注：
            </div>
            <div class="r_item clearfix">
                <div class="r_name">
                    <a href="#">
                        惠普（HP）Pavilion M4-1016TX 14.0英寸笔...
                    </a>
                </div>
                <div class="r_price">
                    ¥4699.00
                </div>
                <div class="r_quantity">
                    1
                </div>
                <div class="r_control">
                    <a href="javascript:void(0);">
                        重新购买
                    </a>
                    <span>
                        |
                    </span>
                    <a href="javascript:void(0);">
                        关注
                    </a>
                </div>
            </div>
        </div> /cart_remove -->
        <!--
        <div class="cart_recommond mt50 p15">
        	<div class="title">
            	<h4 class="f14 fb red">购买同样商品的顾客还购买了</h4>
            </div>
            <div class="body">
            	<a href="javascript:void(0);" class="cart_prev"></a>
                <a href="javascript:void(0);" class="cart_next"></a>
                <div class="cart_rec_list">
                    <ul>
                        <li>
                            <p class="img"><a href="#"><img alt="" src="../images/images_24.jpg" /></a></p>
                            <p class="name"><a href="#">奇克摩克 TPU硅胶自带防尘塞手机壳/保护壳 适用于苹果iPhone5s 白色</a></p>
                            <p class="price">¥49.00</p>
                            <p class="btn"><a class="add_cart" href="#">加入购物车</a></p>
                        </li>
                        <li>
                            <p class="img"><a href="#"><img alt="" src="../images/images_24.jpg" /></a></p>
                            <p class="name"><a href="#">奇克摩克 TPU硅胶自带防尘塞手机壳/保护壳 适用于苹果iPhone5s 白色</a></p>
                            <p class="price">¥49.00</p>
                            <p class="btn"><a class="add_cart" href="#">加入购物车</a></p>
                        </li>
                        <li>
                            <p class="img"><a href="#"><img alt="" src="../images/images_24.jpg" /></a></p>
                            <p class="name"><a href="#">奇克摩克 TPU硅胶自带防尘塞手机壳/保护壳 适用于苹果iPhone5s 白色</a></p>
                            <p class="price">¥49.00</p>
                            <p class="btn"><a class="add_cart" href="#">加入购物车</a></p>
                        </li>
                        <li>
                            <p class="img"><a href="#"><img alt="" src="../images/images_24.jpg" /></a></p>
                            <p class="name"><a href="#">奇克摩克 TPU硅胶自带防尘塞手机壳/保护壳 适用于苹果iPhone5s 白色</a></p>
                            <p class="price">¥49.00</p>
                            <p class="btn"><a class="add_cart" href="#">加入购物车</a></p>
                        </li>
                        <li>
                            <p class="img"><a href="#"><img alt="" src="../images/images_24.jpg" /></a></p>
                            <p class="name"><a href="#">奇克摩克 TPU硅胶自带防尘塞手机壳/保护壳 适用于苹果iPhone5s 白色</a></p>
                            <p class="price">¥49.00</p>
                            <p class="btn"><a class="add_cart" href="#">加入购物车</a></p>
                        </li>
                        <li>
                            <p class="img"><a href="#"><img alt="" src="../images/images_24.jpg" /></a></p>
                            <p class="name"><a href="#">奇克摩克 TPU硅胶自带防尘塞手机壳/保护壳 适用于苹果iPhone5s 白色</a></p>
                            <p class="price">¥49.00</p>
                            <p class="btn"><a class="add_cart" href="#">加入购物车</a></p>
                        </li>
                        <li>
                            <p class="img"><a href="#"><img alt="" src="../images/images_24.jpg" /></a></p>
                            <p class="name"><a href="#">奇克摩克 TPU硅胶自带防尘塞手机壳/保护壳 适用于苹果iPhone5s 白色</a></p>
                            <p class="price">¥49.00</p>
                            <p class="btn"><a class="add_cart" href="#">加入购物车</a></p>
                        </li>
                        <li>
                            <p class="img"><a href="#"><img alt="" src="../images/images_24.jpg" /></a></p>
                            <p class="name"><a href="#">奇克摩克 TPU硅胶自带防尘塞手机壳/保护壳 适用于苹果iPhone5s 白色</a></p>
                            <p class="price">¥49.00</p>
                            <p class="btn"><a class="add_cart" href="#">加入购物车</a></p>
                        </li>
                        <li>
                            <p class="img"><a href="#"><img alt="" src="../images/images_24.jpg" /></a></p>
                            <p class="name"><a href="#">奇克摩克 TPU硅胶自带防尘塞手机壳/保护壳 适用于苹果iPhone5s 白色</a></p>
                            <p class="price">¥49.00</p>
                            <p class="btn"><a class="add_cart" href="#">加入购物车</a></p>
                        </li>
                    </ul>
                </div>
            </div>
        </div><!-- /cart_recommond -->
        
    </div><!-- /container -->
    <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>

<div id="delete_dialog" class="dialog">
    <div class="dialog-outer">
        <span class="dialog-bg dialog-bg-n">
        </span>
        <span class="dialog-bg dialog-bg-ne">
        </span>
        <span class="dialog-bg dialog-bg-e">
        </span>
        <span class="dialog-bg dialog-bg-se">
        </span>
        <span class="dialog-bg dialog-bg-s">
        </span>
        <span class="dialog-bg dialog-bg-sw">
        </span>
        <span class="dialog-bg dialog-bg-w">
        </span>
        <span class="dialog-bg dialog-bg-nw">
        </span>
        <div class="dialog-inner">
            <div class="dialog-toolbar clearfix">
                <a class="dialog-close" href="javascript:void(0);" title="关闭" onclick="hideDia()">
                    关闭	
                </a>
                <h3 class="dialog-title">
                    删除商品
                </h3>
            </div>
            <div class="dialog-content clearfix">
                <div class="p10 tc red w200" id="diaText">
                	确定从购物车中删除此商品？
                </div>
                <div class="tc p15">
                <form method="post" action="delshoppingcatgoodsgroup.htm" id="delGroupFil">
                	<input type="hidden" name="shoppingCartId" id="shoppingCartId"/>
                	<input type="hidden" name="goodsInfoId" id="goodsInfoId"/>
                	<input type="hidden" name="fitId" id="fitId"/>
                 </form>
                	<a class="red_btn" href="javascript:void(0);" onclick="dodel();">确定</a>
                    <a class="light_btn2" href="javascript:void(0);" onclick="hideDia()">取消</a>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<div class="dialog s_dia dia2">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" id="f_img" alt="" src="${basePath}/images/mod_war.png" />
            	<em id="con_00">修改成功！</em>
            </div>
            <div class="dia_ops mt20 tr">       
                 <a class="go_pay" id="go_pay_00" href="javascript:cls();">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
    
    <div class="dialog s_dia dia3">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        	<em>购物车内没有商品，是否跳转到首页!!	</em>
            </div>
            <div class="dia_ops mt20 tr">       
                <a class="go_pay" id="go_pay_00" href="javascript:location.href='index.html';">确定</a>
                <a class="go_shopping" href="javascript:cls();">取消</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
    
    <div class="dialog s_dia dia4">
        <div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
            <div class="dia_intro no_tc pt30" style="text-align: center;">
            <em>是否一键下单？</em>
            </div>
            <div class="dia_ops mt20 tr">       
                <a class="go_pay" id="go_pay_00" href="javascript:;" onclick="goForm()">确定</a>
                <a class="go_shopping" href="javascript:cls();">取消</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
<form action ="updateprovince.htm" method="post" class="subDis">
<!--存放地址信息-->
	<input type="hidden" value=<#if wareUtil.districtId??>${wareUtil.districtId}</#if> name="distinctId" class="ch_distinctId">
	<input type="hidden" value=<#if wareUtil.provinceName??>${wareUtil.provinceName}</#if> name="chProvince" class="ch_province">
	<input type="hidden" value=<#if wareUtil.cityName??>${wareUtil.cityName}</#if> name="chCity" class="ch_city">
	<input type="hidden" value=<#if wareUtil.distinctName??>${wareUtil.distinctName}</#if> name="chDistinct" class="ch_distinct">
	<input type="hidden" name="chAddress" class="ch_address">
</form>
<!--修改优惠-->
<form method="post" action="changeshoppingcartmarts.htm" class="change_shopping">
	<input type="hidden" name="shoppingCartId" class="shopping_cart_id">
	<input type="hidden" name="marketingActivityId" class="marketing_activity_id">
	<input type="hidden" name="marketingId" class="marketing_id">
</form>

<input type="hidden" value="<#if customerId??>${customerId}</#if>" id="customerId"/>

<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/shoppingcart/shoppingcart.js"></script>
<script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
<script type="text/javascript" src="${basePath}/index_two/js/index.js"></script>

<script type="text/javascript">
function goForm(){
    $("#subForm").submit();
}
<!--改变购物车状态-->
function changeStatus(obj){
	var sta=$(obj).attr("role-status");
	$(obj).attr("role-status",sta);
	if(sta==0){
		sta=1;
	}else{
		sta=0;
	}
	 $(obj).attr("role-status",sta);
	$.post("changeshopstatus.htm", { shoppingId: $(obj).val(), status:sta  },
	   function(data){
           location.reload();
   });
}
function hideDia(){
	$("#fitId").attr("value","");
	$('#delete_dialog').hide();
}
$(function(){
		oncheck();
		$('.select_gift').click(function(){
			if($('#gift_dialog').is(':hidden')){
				var p_x = $(this).offset().left;
				var p_y = $(this).offset().top;
				$('#gift_dialog').css({
					left : p_x +'px',
					top : p_y + 25 + 'px'
				});
				$('#gift_dialog').show();
			}
			else{
				$('#gift_dialog').hide();
			}
		});
		$('.g_delete').click(function(){
			if($('#delete_dialog').is(':hidden')){
				var p_x = ($(window).width()-1200)/2+1200-250;
				var p_y = $(this).offset().top;
				$('#delete_dialog').css({
					left : p_x,
					top : p_y + 20 + 'px'
				});
				$('#diaText').html("确定从购物车中删除此商品？");
				$('#delete_dialog').show();
			}
			else{
				$('#delete_dialog').hide();
			}
		});
		
		$('.g_group_delete').click(function(){
			if($('#delete_dialog').is(':hidden')){
				var p_x = $(this).offset().left;
				var p_y = $(this).offset().top;
				$('#delete_dialog').css({
					left : p_x - 100 +'px',
					top : p_y + 15 + 'px'
				});
				$('#diaText').html("删除后，其它商品不享受套装优惠，确定从购物车中删除此商品？");
				$('#delete_dialog').show();
			}
			else{
				$('#delete_dialog').hide();
			}
		});
		$('.change_promotion').click(function(){
			var p_x = ($(window).width()-1200)/2+1200-350;
			var p_y = $(this).offset().top;
			$(this).next().show();
			$(this).next(".promotion_dialog").css({
				left: p_x,
				top: p_y + 20 + 'px'
			});
		});
		$('.gift_check input').change(function(){
			if($(this).is(':checked')){
				$(this).parent().parent().css('backgroundColor','#FFFDEE');
			}
			else{
				$(this).parent().parent().css('backgroundColor','#FFFFFF');
			}
		});
		$('.check_all').change(function(){
			if($(this).is(':checked')){
				$('.g_ckeck').attr('checked','true');
				$('.cart_goods').css('backgroundColor','#FFFDEE');
			}
			else{
				$('.g_ckeck').removeAttr('checked');
				$('.cart_goods').css('backgroundColor','#FFFFFF');
			}
		});
		$('.g_ckeck').change(function(){
			if($(this).is(':checked')){
				$(this).parent().parent().parent().css('backgroundColor','#FFFDEE');
			}
			else{
				$(this).parent().parent().parent().css('backgroundColor','#FFFFFF');
			}
		});
		$('.cart_rec_list').jCarouselLite({
			btnNext :　'.cart_next',
			btnPrev : '.cart_prev',
			visible :　6,
			auto : 3000,
			speed : 800
		});
	});
	function add(obj,id){   
		var codeType = $(obj).parent().find(".codexType").val();
		if(codeType == '14'){
			location.reload();
		}
		var custId = $("#customerId").val();
		var sumNum = $(obj).parent().find(".sumNum").val();
		var num = $(obj).parent().find(".productNum").val();
		var xzNum = $(obj).parent().find(".xzNum").val();
		if(num>=99){
			$("#con_00").html("购买数量不超过99！！");
			dia(2);
			$(obj).parent().find(".productNum").val(num);
			if(custId==""){
						$(".xzNum").attr("value",Subtr($(obj).parent().find(".productNum").val(),0));									
			}
			return null;
		}
			if(Subtr(sumNum,num)>0){
				if(custId==""){
					var goodsInfoId=$(".pro_id"+id).val();
						changeNumCoo(goodsInfoId,1);		
					$(".xzNum").attr("value",accAddInt(num,1));
				}else{
					changeNum(id,accAddInt(num,1)); 
				}
					var count=accAddInt(num,1);
					
					$(obj).parent().find(".productNum").val(accAddInt(num,1)); 
            }
		oncheck();
	}
	function mit(obj,id){
	var codeType = $(obj).parent().find(".codexType").val();
		if(codeType == '14'){
			location.reload();
		}
	var custId = $("#customerId").val();
	var num = $(obj).parent().find(".productNum").val();
		if($(obj).parent().find(".productNum").val()<=1){
			$(obj).parent().find(".productNum").val(1);
		}else{
			if(custId==""){
					var goodsInfoId=$(".pro_id"+id).val();
					changeNumCoo(goodsInfoId,-1);
					$(".xzNum").attr("value",Subtr($(obj).parent().find(".productNum").val(),1));					
			}else{
				changeNum(id,Subtr(num,1)); 
			}
				$(obj).parent().find(".productNum").val(Subtr($(obj).parent().find(".productNum").val(),1));
		}
		oncheck();
		
	}
	function opblur(obj,id,count){
		var nums = $(obj).parent().find(".productNum").val();
		var custId = $("#customerId").val();
	    var num = $(obj).val();
		var sumNum = $(obj).parent().find(".sumNum").val();
		var xzNum = $(obj).parent().find(".xzNum").val();
//		if(nums>99){
//			$("#con_00").html("购买数量不能超过99！！");
//			dia(2);
//			$(obj).parent().find(".productNum").val(xzNum);
//			return false;
//		}
		if(/^\d+$/.test(num))    
			{    
			}else{
			if(custId==""){
					var goodsInfoId=$(".pro_id"+id).val();
					xzNum=xzNum-1;
					changeNumCoo(goodsInfoId,-xzNum);		
			}else{
			   changeNum(id,1);
			}
			   $(obj).parent().find(".productNum").val(1);
			   return false;
		}
		if(Subtr(count,num)>=0){
			if(num<=0){
					if(custId==""){
						var goodsInfoId=$(".pro_id"+id).val();
						xzNum=xzNum-1;
						changeNumCoo(goodsInfoId,-xzNum);		
					}else{
						changeNum(id,1);
					}
					$(obj).parent().find(".productNum").val(1);
			} 
			if(num>sumNum){
					if(custId==""){
						var goodsInfoId=$(".pro_id"+id).val();
						xzNum=nums-xzNum;
						changeNumCoo(goodsInfoId,xzNum);
						$(".xzNum").attr("value",Subtr(xzNum));		
					}else{
						changeNum(id,num);
						$(obj).parent().find(".productNum").val(num);
					}
			}else{ 
				if(custId==""){
						var goodsInfoId=$(".pro_id"+id).val();
						xzNum=nums-xzNum;
						changeNumCoo(goodsInfoId,xzNum);		
						$(".xzNum").attr("value",Subtr(xzNum));	
						
				}else{
					changeNum(id,num);
				}
			}
		oncheck();
		}else{
			$("#con_00").html("数量不足,剩余库存："+sumNum);
			dia(2);
			$(obj).parent().find(".productNum").val(xzNum);
			return false;
		}
		return true;
	}
	function del(id,infoId){
		$('#shoppingCartId').val(id);
		$('#goodsInfoId').val(infoId);
	}
	function dodel(){
		var id = $('#shoppingCartId').val();
		var infoId=$("#goodsInfoId").val();
		var fitId=$("#fitId").val();
		if(fitId==""){
		
			  $.post("delshoppingcartbyid/"+id+"-"+infoId, function (data)
		    {
		        if (data==1)
		        {
		        	$('#shoppingCartId').val('');
		        	$('#delete_dialog').hide();
		        	$('#activity'+id).remove();
		        	$('#cart'+id).remove();
		        	oncheck();
		        }else{
		        	//删除失败
		        	$("#con_00").html("删除失败！");
		        	dia(2);
		        }
		    });
		}else{
		 var inId="110012"+fitId;
		$.post("delshoppingcartbyid/"+id+"-"+inId, function (data)
		 {
		    	 if (data==1){	
		    	 	 $("#delGroupFil").submit();
				}
		    });
		 
		} 
	}
	function changeNum(id,num){
		 $.post("changeshoppingcartbyid/"+id+"-"+num, function (data) 
	    { 
	        if (data==1)
	        {
	        	
	        }
	    });
	}
	function changeNumCoo(id,num){
	    $.post("addProductToShopCar.htm", { productId: id, goodsNum: num },
		   function(data){
		   });
	}
	function changemarketing(id){
		var custId = $("#customerId").val();
		var marketingId = $("#market1"+id).val();
		var marketingActivityId = $("#market2"+id).val();
		if(marketingActivityId==''){
			marketingActivityId=0;
		}
		if(marketingId==""){
			marketingId=0;
		}
		if(custId==""){
		 var goodsInfoId=$(".pro_id"+id).val();
			$(".shopping_cart_id").attr("value",goodsInfoId);
		}else{
			$(".shopping_cart_id").attr("value",id);
		}
		$(".marketing_activity_id").attr("value",marketingActivityId);
		$(".marketing_id").attr("value",marketingId);
		$(".change_shopping").submit();
	}
	function onpay(){
		var obj=document.getElementsByName('box');
		var preprice=0; 
		var f = false;
		var s=true;
		if(obj!=null&&obj.length!=0){
			for(var i=0; i<obj.length; i++){    
			    if(obj[i].checked){
			    if(opblur($("#goods_num_"+obj[i].value),obj[i].value,$("#goodsInfoStock_"+obj[i].value).val())==false){
			    	s=false;
			    }else{
			    	f=true;
			    }
			    }
			}
		}
		if(s){
		
			if(f){
    				$("#subForm").submit();
			     
			}else{
				dia(3);
				
			}
		}
	}
	<!--删除优惠分组-->
	function delgroup(shoppingCartId,fitId,goodsIoId){
		$('#shoppingCartId').val(shoppingCartId);
		$('#goodsInfoId').val(goodsIoId);
		$('#fitId').val(fitId);
	}
</script>
</@htmlBody>