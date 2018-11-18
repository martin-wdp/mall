$(function(){
    //加载收货地址
    loadAllAddress();

    //设置默认收货地址
    defaultAddress($(".ch_address").val());

    //彭磊2015.11.1加
    //getExpressprice();

    //$("#lastpay").html("￥"+$("#payPrice").val());
    $('input[name="isbaoyou"]').each(function(){
        if($(this).val()=='0'){
            var tid=$(this).attr("data-id");
            $(".freight-cont"+tid).hide();
        }
    });

    $(".qmark-tip").mouseover(function(){
        var a=$(this).parents("li").width();
        a=a-parseInt($(this).css("right"));
        var x=a;
        var y=$(this).css("top");
        x=parseInt(x)-35;
        y=parseInt(y)+21;
        var dd=$(this).attr("data-tips");

        $(".online_tips").css("left",x);
        $(".online_tips").css("top",y);
    });

    //配送方式
    $(".fretype").click(function(){
        if(!$(this).hasClass("disabled")) {
            $(".fretype").removeClass("curr ");
            $(this).addClass("curr ");
            if ($(this).attr("id") == 'ziti') {
                $("#selfpick_shipment").show();
            }
            var _this = $(this);
            $(".bossitem").each(function (index, item) {

              //自提点为空置灰
                if ($(this).attr("data-id") == 2) {
                    if (_this.attr("id") == 'ziti') {
                        $(this).addClass("disabled");
                    }
                    else {
                        $(this).removeClass("disabled");
                    }

                } else {
                    if (_this.attr("id") != 'ziti') {
                        $(this).removeClass("disabled");
                        $("#ep").html("￥" + $("#yfprice").val());
                        $("#lastpay").html("￥" + $("#sumpriceflag").val());
                        $("#lastpays").html("￥" + $("#sumpriceflag").val());

                    } else {
                        var bossyfprice=$("#bossyfprice").val();
                        //总运费减掉boss运费
                        var thirdprice=Subtr($("#yfprice").val(), $("#bossyfprice").val());
                        $("#ep").html("￥"+thirdprice);
                        $("#lastpay").html("￥" + Subtr($("#sumpriceflag").val(), bossyfprice));
                        $("#lastpays").html("￥" + Subtr($("#sumpriceflag").val(), bossyfprice));

                    }
                }

            });
                if ($(this).attr("id") == 'ziti') {
                    $("#selfpick_siteDiv .site-item").each(function (index, item) {
                        if ($(this).hasClass("site-item-selected")) {
                            var idflag = $(this).attr("pickId");
                            if (idflag != null) {
                                $("#deliveryPointId").val(idflag)
                            }
                        }
                    });
                    $("#selfpick_shipment").show();
                    $("#typeId").val(1);
                } else {
                    $("#selfpick_shipment").hide();
                    $("#typeId").val(0);
                   // $("#deliveryPointId").val(0);
                }
            }

    })


    $(".bossitem").mouseover(function() {
        $(this).addClass('payment-item-hover');

    });
    //第三方
    $(".thirditem").mouseover(function() {
        $(this).addClass('payment-item-hover');

    });
    $(".thirditem").mouseout(function() {
        $(this).removeClass('payment-item-hover');
    });

    $(".bossitem").mouseout(function() {
        $(this).removeClass('payment-item-hover');
    });

    /*支付方式*/
    $(".bossitem").click(function(){
        if(!$(this).hasClass("disabled")){
        $(".bossitem").removeClass('item-selected');
        $(this).addClass('item-selected');
        var paytype=$(this).attr("data-id")
        $(".ch_pay").val(paytype);

            $(".fretype").each(function(index,item){

                if($(this).attr("id")=='ziti'){
                    if(paytype==2){
                    $(this).addClass("disabled");

                }
                    else{
                        var deliveryPointId=$("#deliveryPointId").val();

                 if(deliveryPointId!=0&&deliveryPointId!=null&&deliveryPointId!=""){
                     $(this).removeClass("disabled");
                 }


                    }
                }else{
                    if(paytype!=2){
                        $(this).removeClass("disabled");
                    }

                }
            });

        }
    });
    /*第三方支付方式*/
    $(".thirditem").click(function(){
        $(".thirditem").removeClass('item-selected');
        $(this).addClass('item-selected');
        $(".ch_paythird").val($(this).attr("data-id"));
    });

    $(".bossitem").each(function(){
        if($(this).hasClass("item-selected")){
            $(".ch_pay").val($(this).attr("data-id"));
        }
    });

    $(".thirditem").each(function(){
        if($(this).hasClass("item-selected")){
            $(".ch_paythird").val($(this).attr("data-id"));
        }
    });



    /*点击保存收货地址的时候*/
    $(".save_address").click(function(){
        /*获取所有的收货地址选项*/

        var addressName=$(".save_add_name");
        var infoProvince=$("#infoProvince");
        var infoCity=$("#infoCity");
        var infoCounty=$("#infoCounty");
        var infoStreet=$("#infoStreet");
        var addressDetail=$(".save_add_detail");
        var addressMoblie=$(".save_add_mobile");
        var addressPhone=$(".save_add_phone");
//		var addressEmail=$(".save_add_email");
        var addressZip=$(".save_add_post");
        /*拼接参数*/
        var params="";
        var bool=true;
        /*判断参数不为空就拼接参数*/
        if($(addressName).val()!=null &&$.trim($(addressName).val())!=""){
            var regexp=new RegExp("[`~!@#$^&*()={}':;',\\[\\]<>/?~！@#￥……&*（）{}【】‘；：”“'。，、？]");
            if (regexp.test($(addressName).val()) ) {
                $(".addressNameTip").text("存在特殊字符").addClass("tipserror");
                $(".addressNameTip").text("存在特殊字符").removeClass("error-msg");
                return false;
            }
            $("addressNameTip").text("").removeClass("tipserror");
            params+="addressName="+$(addressName).val();
        }else{
            $(".addressNameTip").text("收货人名称不能为空!").addClass("tipserror");
            $(".addressNameTip").text("收货人名称不能为空!").removeClass("error-msg");
            bool=false;
            return;
        }


        if($(infoProvince).val()!="" && $(infoProvince).val()!=null){
            params+="&infoProvince="+$(infoProvince).val();
        }
        if($(infoCity).val()!="" && $(infoCity).val()!=null){
            params+="&infoCity="+$(infoCity).val();
        }
        if($(infoCounty).val()!="" && $(infoCounty).val()!=null){
            params+="&infoCounty="+$(infoCounty).val();
        }
        if($(infoStreet).val()!="" && $(infoStreet).val()!=null){
            params+="&infoStreet="+$(infoStreet).val();
        }

        if($(addressDetail).val()!=null && $.trim($(addressDetail).val())!=""){
            var regexp=new RegExp("[`~!@#$^&*()={}':;',\\[\\]<>/?~！@#￥……&*（）{}【】‘；：”“'。，、？]");
            if (regexp.test(addressDetail.val() ) ) {
                $(".addressDetailTip").text("包含特殊字符!").addClass("tipserror");
                $(".addressDetailTip").text("包含特殊字符!").removeClass("error-msg");
                return false;
            }
            $(".addressDetailTip").text("").removeClass("tipserror");
            $(".addressDetailTip").text("").addClass("error-msg");
            params+="&addressDetail="+$(addressDetail).val();
        }else{
            $(".addressDetailTip").text("详细地址不能为空!").addClass("tipserror");
            $(".addressDetailTip").text("详细地址不能为空!").removeClass("error-msg");
            bool=false;
            return;
        }
        if($(addressMoblie).val()!="" && $(addressMoblie).val()!=null){
            if (/^0?(13|15|18|14|17)[0-9]{9}$/.test( $(addressMoblie).val() ) ) {

                $(".addressPhoneTip").text("").removeClass("tipserror");
                $(".addressPhoneTip").text("").addClass("error-msg");
                params+="&addressMoblie="+$(addressMoblie).val();
            }else{
                $(".addressPhoneTip").text("手机号码格式不对!").addClass("tipserror");
                $(".addressPhoneTip").text("手机号码格式不对!").removeClass("error-msg");
            return;
            }
        }else{
            $(".addressPhoneTip").text("手机号码不能为空!").addClass("tipserror");
            $(".addressPhoneTip").text("手机号码不能为空!").removeClass("error-msg");
            bool=false;
            return;
        }

        if($("#cust_address_default").val()!=""){
            params+="&isDefault="+$("#cust_address_default").val();
        }
        //判断临时用户的手机是否已经存在
        var conditions="mobel="+addressMoblie.val();
        $.ajax({
            type: "POST",
            url: "../isshowmobelexist.htm",
            data: conditions,
            async: false,
            success: function(msg){
                if(msg=="1"){
                    bool=false;
                    $(".addressPhoneTip").html("手机号码已存在,请更换号码，或者<a href='../login.html'>登陆 </a>").addClass("tipserror");
                    $(".addressPhoneTip").html("手机号码已存在,请更换号码，或者<a href='../login.html'>登陆 </a>").removeClass("error-msg");
                }
            }
        });
        //判断临时用户的手机是否已经存在end

        if( $(addressPhone).val() !="" && $(addressPhone).val() !=null){
            if (/^((0[0-9]{2,3}\-)|(\(0[0-9]{2,3}\)))?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/.test( $(addressPhone).val() ) ) {
                if($(addressPhone).val()!="" && $(addressPhone).val()!=null){
                    $(".addressPhoneTip").text("").removeClass("tipserror");
                    $(".addressPhoneTip").text("").addClass("error-msg");
                    params+="&addressPhone="+$(addressPhone).val();
                }
            }else{
                $(".addressPhoneTip").text("电话格式不正确!").addClass("tipserror");
                $(".addressPhoneTip").text("电话格式不正确!").removeClass("error-msg");
                bool=false;
            }
        }
//		if($(".save_add_email").val() != "" && $(".save_add_email").val()!=null){
//			if(/^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/.test($(".save_add_email").val())){
//				$(".addressEmailTip").text("").removeClass("tipserror");
//				$(".addressEmailTip").text("").addClass("error-msg");
//				params+="&addressEmail="+$(addressEmail).val();
//			}else{
//				$(".addressEmailTip").text("邮箱格式不正确!").addClass("tipserror");
//				$(".addressEmailTip").text("邮箱格式不正确!").removeClass("error-msg");
//				bool=false;
//			}
//		}

        //判断邮政编码格式
        if($(".save_add_post").val() != "" && $(".save_add_post").val()!=null){
            if(/^[1-9]\d{5}(?!\d)$/.test($(".save_add_post").val())){
                $(".addPostTips").text("").removeClass("tipserror");
                $(".addPostTips").text("").addClass("error-msg");
                params+="&addressZip="+$(addressZip).val();
            }else{
                $(".addPostTips").text("邮政编码格式不正确").addClass("tipserror");
                $(".addPostTips").text("邮政编码格式不正确").removeClass("error-msg");
                bool=false;
            }
        }

        /*end*/
        if(bool){
            if($(".save_update_add_id").val()>0){
                params+="&addressId="+$(".save_update_add_id").val();
                if($(".save_add_post").val()==""){
                    params+="&addressZip=''";
                }
                $.ajax({
                    type: 'post',
                    url:'../goods/modiCustAddress.html?CSRFToken='+$("#csrfNo").val(),
                    data: encodeURI(params),
                    async:false,
                    success: function(data) {
                        if(data){
                            loadAllAddress();cls();
                            $("#con_flag").html("更新地址成功!");
                            $("#addressflag").val(1);
                            dia(10);
                        }else{
                            $("#con_00").html("更新地址失败!");
                            dia(6);
                        }
                    }
                });
            }else{
                if($(".all_cust_address").length>=9){
                    $("#con_00").html("收货地址最多只能保存9个!");
                    dia(6);
                    return false;
                }
                $.ajax({
                    type: 'post',
                    url:'../goods/ajaxAddCustomerAddressFromOrder.html?CSRFToken='+$("#csrfNo").val(),
                    data:encodeURI(params),
                    async:false,
                    success: function(data) {
                        if(data){

                            loadAllAddress();
                            cls();

                            $("#con_00").html("添加地址成功!");
                            dia(6);
                            //把新增成功的地址 直接设置为默认地址
//			     			setAddress();
                            //若新增的地址为默认地址，则重新计算运费
                            if($("#cust_address_default").val() == "1"){
                                getExpressprice();
                            }
                        }else{
                            $("#con_00").html("新增地址失败!");
                            dia(6);
                        }
                    }
                });
            }
        }
    });


    //保存单位名称
    $(".save-tit").click(function(){
        if($(".invoice_title").val()==""){
            $(".invoice_title").attr("placeholder","不能为空");
            return;
        } else {
            var reg= /^([\u4e00-\u9fa5_A-Za-z0-9 \\`\\~\\!\\@\\#\\$\\^\\&\*\(\)\=\{\}\'\:\;\'\,[\]\.\/\?\~\！\@\#\￥\…\…\&\*\（\）\;\—\|\{\}\【\】\‘\；\：\”\“\'\。\，\、\？])+$/;
            if(!reg.test($(".invoice_title").val())){
                $(".invoice_title").val("");
                $(".invoice_title").attr("placeholder","不能输入特殊字符！");
                return;
            }
        }
        $(this).addClass("hide");
        $(this).parent().parent().prepend("<b></b>");
        $(this).parents(".add-invoice-tit").find("input[name='invoiceTitle']").attr("readonly","readonly");
        $(".invoice-list").on("mouseover",".new_add",function(){
            $(".update-tit").removeClass("hide");
            $(".delete-tit").removeClass("hide");
        });
        $(".invoice-list").on("mouseout",".new_add",function(){
            $(".update-tit").addClass("hide");
            $(".delete-tit").addClass("hide");
        });
    });

    //修改单位名称
    $(".update-tit").click(function(){
        $(this).addClass("hide");
        $(this).next().removeClass("hide");
        $(this).parents(".add-invoice-tit").find("input[name='invoiceTitle']").removeAttr("readonly","readonly");
        $(".invoice-list").on("mouseover",".new_add",function(){
            $(".update-tit").addClass("hide");
            $(".delete-tit").removeClass("hide");
        });
        $(".invoice-list").on("mouseout",".new_add",function(){
            $(".update-tit").addClass("hide");
            $(".delete-tit").addClass("hide");
        });
    });


    /*点击保存发票信息*/
    $(".save_invoice").click(function(){
        /*判断是否需要发票*/
        if($(".invoiceType ").hasClass("tab-item-selected")){//不需要发票
            $(".ch_invoiceType").html($(".invoiceType").html());
            $(".ch_invoceTypeValue").val($(".invoiceType").val());
            $(".tr_invoiceTitleView").hide();
            $(".invoiceContentMing").hide();
            cls();
            return;
        }else {
            $(".ch_invoiceType").html($(".invoiceType").next().html());
            $(".ch_invoceTypeValue").val($(".invoiceType").next().val());
        }
        /*获取到所有的发票抬头,并获取到当前选择的发票抬头*/
        if($(".new_add").hasClass("invoice-item-selected")){//单位
            var reg= /^([\u4e00-\u9fa5_A-Za-z0-9 \\`\\~\\!\\@\\#\\$\\^\\&\*\(\)\=\{\}\'\:\;\'\,[\]\.\/\?\~\！\@\#\￥\…\…\&\*\（\）\;\—\|\{\}\【\】\‘\；\：\”\“\'\。\，\、\？])+$/;
            if(!reg.test($(".invoice_title").val())){
                $(".invoice_title").val("");
                $(".invoice_title").attr("placeholder","不能输入特殊字符！");
                return;
            }
            $(".tr_invoiceTitleView").html($(".invoice_title").val());
            $(".ch_invoceTitleValue").val($(".invoice_title").val());
        }else{
            $(".tr_invoiceTitleView").html("个人");
            $(".ch_invoceTitleValue").val("个人");
        }
        $(".tr_invoiceTitleView").show();

        /*获取所有的发票内容的选项,并获取选中的哪一个*/
        var invoiceContent=$("li[name=invoiceContent]");
        for(var i=0;i<invoiceContent.length;i++){
            if($(invoiceContent[i]).hasClass("invoice-item-selected")){
                $(".invoiceContentMing").show();
                $(".invoiceContentMing").html($(invoiceContent[i]).html());
                $(".ch_invoiceContentValue").val($(invoiceContent[i]).html());
            }
        }
        cls();
    });





    var bossSumPrice=$('#bosssumPrice').val();	//boss商品总金额
    var pointSet = $('#pointSet').val();          //积分兑换规则
    if(bossSumPrice>0){
        var keyishiyong = accDiv(accMul(bossSumPrice,10),pointSet);
        keyishiyong=parseInt(parseInt(accDiv(parseInt(keyishiyong),10))+"0");
        var jifen=$("#jifen1").val();					//会员拥有积分
        if(jifen<keyishiyong){
            $('#muqianjifen1').html(jifen);          //设置目前可用的积分
            $("#jifen2").val(jifen);
        }else{
            $('#muqianjifen1').html(keyishiyong);          //设置目前可用的积分
            $("#jifen2").val(keyishiyong);
        }
    }else{
        $('#muqianjifen1').html(0);          //设置目前可用的积分
        $("#jifen2").val(0);
    }

});


function closeAddresswindow(obj){
    cls();

    $(".consignee-item").each(function (index, item) {
        if($(this).hasClass("item-selected")){

            $(this).click();
        }
    });


}

function getExpressprice() {
    /*获取所有的支付方式,并判断是否被选中,如果选中就更改页面上的元素值*/
    var yfprice = 0;
    var bossyfprice=0;
    var payprice = 0;
    var addsId = $(".ch_address").val();
    var boxs = new Array();
    //获取订单数量

    $('input[name="box"]').each(function () {
        boxs.push(parseInt($(this).val()));
    });

    //计算此次购买商品总运费价格
    $.ajax({
        type: "post",
        url: "../getnewexpressprice.htm",
        async: false,
        data: {addressId: addsId, shopIds: boxs},
        dataType: "json",
        success: function (data) {
            //优惠金额
            var totalprice=0;
            var subprice=0;
            var vip = $("#vip").val();
            //key值为Map的键值
            $.each(data,function(key,value){


                if(data!=null&&data!=''){
                    //总金额
                    if(key=="sumOldPrice"){
                        $("#sumOldPrice").html("￥"+accAdd(value, 0));
                        totalprice=value;
                    }
                    //实际金额
                    if(key=="sumPrice"){
                        $("#lastpays").html("￥"+accAdd(value, 0));
                        payprice=value ;
                        subprice=value;
                        $("#payPrice").val(value);
                    }
                      //平台金额
                    if(key=="bossSumPrice"){
                        $("#bosssumPrice").val(value);

                    }
                    //会员等级折扣价
                    if(key=="discountPrice"){
                        $("#discount").val(value);
                        $("#discount").html("-￥"+accAdd(value, 0));
                    }
                    //总运费
                    if(key=="freightmoney"){
                        yfprice = accAdd(value, yfprice);
                    }
                    //boss运费
                    if(key=="bossfreight"){
                        bossyfprice = accAdd(value, bossyfprice);
                    }
                    //不同地区货品的价格,库存不同
                    if(key=="detailBean"){
                        if(value!=null){

                            $.each(value,function(i,gooddata){

                               if (value!=null){

                                   if(vip == 1){
                                       $(".price_"+gooddata.productVo.goodsInfoId).html("￥" + (gooddata.productVo.goodsInfoVipPrice).toFixed(2));
                                   }else{
                                       $(".price_"+gooddata.productVo.goodsInfoId).html("￥" + (gooddata.productVo.goodsInfoPreferPrice).toFixed(2));
                                   }


                                   if(gooddata.productVo.goodsInfoStock<=0){
                                       $(".stock_"+gooddata.productVo.goodsInfoId).html("无货");
                                   }else{
                                       $(".stock_"+gooddata.productVo.goodsInfoId).html("现货");
                                   }

                               }
                            });
                        }
                    }
                }
          });
            //优惠金额
            $("#yhprice").html("-￥"+ accAdd( Subtr(totalprice,subprice),0));
        }
    });

    $("#ep").html("￥" + yfprice);
    $("#yfprice").val(yfprice);
    //boss运费
    $("#bossyfprice").val(bossyfprice);
    $("#lastpay").html("￥" + accAdd(Subtr(accAdd(yfprice, payprice),$("#discount").val()),0));
    $("#lastpays").html("￥" + accAdd(Subtr(accAdd(yfprice, payprice),$("#discount").val()),0));
    $("#sumPrice").val(accAdd(yfprice, payprice));
    $("#sumpriceflag").val(accAdd(yfprice, payprice));

}

//取消发票信息
function quxiao(){
    cls();
}

/*加载所有的收货地址*/
function loadAllAddress(){
    setDefaultForm();
    $(".save_update_add_id").val("");
    var addsId="";
    /*查询所有的收货地址信息*/
    $.ajax({type:"post",url:"../goods/queryCustAddressForSubOrder.html",async : false,success:function(data) {
        var address_html = "";
        //没有登录
        if (data == '') {
            return;
        }
        $(".cust_allAddress").html("");
        for (var i = 0; i < data.addresses.length; i++) {
            //根据个数更改样式
            updateadd(data.addresses.length);
            var address = data.addresses[i];

            address_html += "<li>" +
            "<div class='consignee-item cust_address_" + address.addressId;

            var addHtml = address.province.provinceName + " " + address.city.cityName + " " + address.district.districtName + " ";
            if (null != address.street && address.street.streetName != "") {
                addHtml = addHtml + address.street.streetName;
            }
            addHtml = addHtml + " " + address.addressDetail;

            /*如果是默认的地址就默认选中并且改变页面上的值*/
            if (address.isDefault == "1") {
                addsId = address.addressId;
                address_html += " item-selected";
                //底部收货人信息
                $(".consignee-foot").html("<p>寄送至：" + addHtml + "<br>收货人：" + address.addressName + " " + address.addressMoblie.substring(0, 3) + "****" + address.addressMoblie.substring(8, 11) + "</p>");
                $(".ch_address").val(address.addressId);
                $(".ch_distinctId").val(address.district.districtId);
            }
            var addHtml = address.province.provinceName + " " + address.city.cityName + " " + address.district.districtName + " ";
            if (null != address.street && address.street.streetName != "") {
                addHtml = addHtml + address.street.streetName;
            }
            addHtml = addHtml + " " + address.addressDetail;
            if(addHtml.length>=40){
                addHtml=addHtml.substring(0,38);
                addHtml+="...";
            }

            /*把所有的属性放在checkbox的自定义属性当中*/
            address_html += "'name='address_in' value='" + address.addressId + "' add-name='" + address.addressName + "'" +
            " add-city='" + address.province.provinceName + address.city.cityName + address.district.districtName + "'";
            if (null != address.street && address.street.streetName != "") {
                address_html += address.street.streetName;
            }
            address_html += " add-detail='" + address.addressDetail + "'" +
            " add-mobi='" + address.addressMoblie + "'" +
            " add-phone='" + address.addressPhone + "'" +
            " add-province='" + address.province.provinceId + "'" +
            " add-cityid='" + address.city.cityId + "'" +
            " add-district='" + address.district.districtId + "'" +
            " add_post='" + address.addressZip + "'";
            if (null != address.street && address.street.streetId != "") {
                address_html += " add-street='" + address.street.streetId + "'";
            } else {
                address_html += " add-street='-1'";
            }
            var strName = "";
            if (address.addressName.length > 5) {
                strName = address.addressName.substring(0, 2) + "..." + address.addressName.substring(address.addressName.length - 1, address.addressName.length);
            } else {
                strName = address.addressName;
            }

            address_html += " add-email='" + address.addressEmail + "'" +
            "><span limit='3' title='" + address.addressName + " " + address.province.provinceName + "'>" + strName + " " + address.province.provinceName + "</span><b></b></div> " +
            "<div class='addr-detail'><span class='addr-name ml10' limit='6' title='" + address.addressName + "'>" + address.addressName + "</span>" +
            "<span class='addr-info ml10' limit='32' title='" + addHtml + " '>" + addHtml + "</span>" +
            "<span class='addr-tel ml10'>" + address.addressMoblie.substring(0, 3) + "****" + address.addressMoblie.substring(8, 11) + "</span></div>" +
            "<div class='op-btns' consigneeid='137873025'>" +
            "<a class='setdefault-consignee' href='javascript:void(0);' id='addressflag' onclick='defaultAddress(" + address.addressId + ");'>设为默认地址</a>" +
            "<a class='edit-consignee' href='javascript:void(0);' onclick='modiAddress(" + address.addressId + ");'>编辑</a>" +
            "<a class='del-consignee ' href='javascript:void(0);' onclick='checkdel(" + address.addressId + ");'>删除</a></div></li>";
            //  $("#go_pay_01").attr("onclick","delAddress("+address.addressId+");");
        }

        $(".cust_allAddress").html(address_html);
        var flagchoose = 0;
        if ($(".cust_allAddress").find(".consignee-item").length > 0) {

            $(".cust_allAddress").find(".consignee-item").each(function (index, item) {

                if ($(this).hasClass("item-selected")) {

                    flagchoose = 1;
                }

            });
        }

        //删除地址时自提点的设置
        if ($(".cust_allAddress").find(".consignee-item").length == 0 || flagchoose == 0) {
            $("#ziti").addClass("disabled");
            $("#ziti").removeClass("curr");
            $("#selfpick_shipment").hide();
            if (!$(".bossfretype").hasClass("curr")) {
                $(".bossfretype").addClass("curr")
            }
            //当删除选中的地址且选中自提点,把支付方式置灰的恢复
            $(".bossitem").each(function (index, item) {
                if ($(this).hasClass("disabled")) {
                    $(this).removeClass("disabled");
                }
            });
        }


        $(".consignee-item").click(function(){

            $(".consignee-item").removeClass('item-selected');
            $(this).addClass('item-selected');
            defaultAddress($(this).attr("value"));


        });
//


        //详情页商品图片展示
        var li_n = $(".consignee-cont li").length - 5;
        var li_step = 0;
        if(li_n > 0) {
            $(".consignee-cont ul").height($(".consignee-cont li").length*40);
            $(".addr-down").removeClass("disabled");
            $(".addr-down").click(function(){
                $(".addr-up").removeClass("disabled");
                if(li_step < li_n) {
                    li_step++;
                    $(".consignee-cont ul").animate({
                        top: -40*li_step
                    },300);
                    if(li_step == li_n) {
                        $(".addr-down").addClass("disabled");
                    };
                };
            });
            $(".addr-up").click(function(){
                $(".addr-down").removeClass("disabled");
                if(li_step > 0) {
                    li_step--;
                    $(".consignee-cont ul").animate({
                        top: -40*li_step
                    },300);
                    if(li_step == 0) {
                        $(".addr-up").addClass("disabled");
                    };
                };
            });
        };
    }
    });


}


function checkdel(obj){

    $("#deladdress").val(obj);
    dia(7);
}
/*设为默认*/
function defaultAddress(id){
    if(id==null||id==""){
        $("#ziti").addClass("disabled");
        return;
    }
    $(".ch_address").val(id);
    $.ajax({
        type: "POST",
        url: "../goods/modifyDefaultAddressForOrder.html",
        async: false,
        data:{"addressId":id},
        success: function (data) {
            if(data){
                loadAllAddress();
                checkProductByAddress();
                //计算运费
                getExpressprice();
            }else{
                $("#con_00").html("设置失败,请重试!");
                dia(6);
            }
        }
    });

    initziti();
}

 function initziti(){
     var id=$(".ch_address").val();
     if(id==null||id==""){
         $("#ziti").addClass("disabled");
         return;
     }

     $.get("../ajaxgetSince.htm",function(data){

         $("#ziti").show();
         if(data!=null&&data!=''){

             $(".fretype").each(function (index, item) {

                 if ($(this).attr("id") == 'ziti') {
                     $(this).removeClass("disabled");
                     $("#selfpick_name").text(data[0].name);
                 }

             });
             var since='';

             since+='<div class="pick-sites pick-sites-more" id="pick-sites">';

             $.each(data,function(index,dps){
                 var classflag="";
                 var linkflag="";
                 if(index==0){
                     classflag="site-item-selected";
                     $("#deliveryPointId").val(dps.deliveryPointId);

                 }

                 if(dps.linkman!=null&&dps.linkman!=""){
                     linkflag="&nbsp;&nbsp; 联系人:"+dps.linkman+"&nbsp; 联系电话:"+dps.telephone;
                 }

                 since+='<div  pickid="'+dps.deliveryPointId+'" onclick="doSelectPicksite(this)" pickname="'+dps.name+'" ' +
                 'class="site-item '+classflag+'" >'
                 +' <div class="site-in-short" style="cursor:pointer"  > '+dps.name+' <b></b> </div>'
                 +'<div class="field">'
                 +'<span class="tip">'+'地址：'+dps.temp2+dps.temp3+dps.address+linkflag+'</span>  </div> <div class="clr"></div> </div> ';

             });

             since+='</div>';
             $(".since").html(since);
             if(data.length<3){
                 $("#pick-sites").removeClass("overflow");
             }else{
                 $("#pick-sites").addClass("overflow");
             }

             $(".bossitem").each(function (index, item) {

                 //支付方式为货到付款自提置灰
                 if($(this).hasClass("item-selected")&&$(this).attr("data-id") == 2) {

                         $('#ziti').addClass("disabled");

                 }
             });

         }else{

             $("#ziti").hide();
             $("#selfpick_shipment").hide();
             $("#deliveryPointId").val(0);
             $(".fretype").each(function (index, item) {

                 //自提点为空置灰
                 if ($("#deliveryPointId").val() == 0) {
                     if ($(this).attr("id") == 'ziti') {
                         $(this).addClass("disabled");
                         $(this).removeClass('curr');
                         $(this).prev().addClass('curr');



                         $(".bossitem").each(function (index, item) {

                             $(this).removeClass("disabled");

                         });


                     }

                 }

             });

         }
     });
 }

//根据收货地址判断该地区仓库是否有库存
function checkProductByAddress(){
    var distinctId=$(".ch_distinctId").val();
    var _list = new Array;
    $('input[name="box"]').each(function () {
        _list.push(parseInt($(this).val()));
    });


    $.ajax({
        url: '../checkWareByCity.htm?shoppingCartId=' + _list + '&distinctId=' + distinctId,
        type: 'post',
        success: function (data) {
            if(data!=null){
                for(var i=0;i<data.warestockList.length;i++){
                    if(data.warestockList[i]>0){
                        $("#product"+data.productIdList[i]).html("现货").removeClass("red");
                    }else{
                        $("#product"+data.productIdList[i]).html("无货").addClass("red");
                    }
                }
            }else{
                $("#con_00").html("网络延迟,请重试!");
                dia(6);
            }
        }
    });

}

//编辑地址
function modiAddress(id){
    $(".save_update_add_id").val(id);
    var address=$(".cust_address_"+id);
    $(".save_add_name").val($(address).attr("add-name"));
    $(".save_add_detail").val($(address).attr("add-detail"));
    $(".save_add_mobile").val($(address).attr("add-mobi"));
    $(".save_add_phone").val($(address).attr("add-phone"));
    $(".save_add_post").val($(address).attr("add_post"));
    if($(".save_add_post").val()=="null"){
        $(".save_add_post").val("");
    }
    if($(".save_add_phone").val()=="null"){
        $(".save_add_phone").val("");
    }
    $(".save_add_email").val($(address).attr("add-email"));
    if($(".save_add_email").val()=="null"){
        $(".save_add_email").val("");
    }
    selectLocationOption($(address).attr("add-province"),$(address).attr("add-cityid"),$(address).attr("add-district"),$(address).attr("add-street"),"infoProvince","infoCity","infoCounty","infoStreet");



    dia(1);
}

//删除收货地址
function delAddress(){
  var id= $("#deladdress").val();
    $.get("../goods/delCustAddressById.html?addressId="+id,function(data){
        if(data){
            loadAllAddress();
        }else{
            $("#con_00").html("删除失败,请重试!");
            dia(6);
        }
    });
}


//验证添加收货地址的姓名
function checkAddressName(){
    var addressName=$(".save_add_name");
    /*判断参数不为空就拼接参数*/
    if($(addressName).val()!=""){
        $(".addressNameTip").text("").removeClass("tipserror");
        $(".addressNameTip").text("").addClass("error-msg");
    }else{
        $(".addressNameTip").text("收货人名称不能为空!").addClass("tipserror");
        $(".addressNameTip").text("收货人名称不能为空!").removeClass("error-msg");
    }
}
//详细地址
function checkAddressDetail(){
    var addressDetail=$(".save_add_detail");

    if($(addressDetail).val()!=""){
        var regexp=new RegExp("[`~!@#$^&*()={}':;',\\[\\]<>/?~！@#￥……&*（）{}【】‘；：”“'。，、？]");
        if (regexp.test(addressDetail.val() ) ) {
            $(".addressDetailTip").text("包含特殊字符!").addClass("tipserror");
            $(".addressDetailTip").text("包含特殊字符!").removeClass("error-msg");
            return false;
        }
        $(".addressDetailTip").text("").removeClass("tipserror");
        $(".addressDetailTip").text("").addClass("error-msg");
    }else{
        $(".addressDetailTip").text("详细地址不能为空!").addClass("tipserror");
        $(".addressDetailTip").text("详细地址不能为空!").removeClass("error-msg");
    }

}

//手机号码
function checkAddressMobile(){
    var addressMoblie=$(".save_add_mobile");
    if($(addressMoblie).val()!=""){
        if (/^0?(13|15|18|14|17)[0-9]{9}$/.test( $(addressMoblie).val() ) ) {
            $(".addressPhoneTip").text("").removeClass("tipserror");
            $(".addressPhoneTip").text("").addClass("error-msg");
        }else{
            $(".addressPhoneTip").text("手机号码格式不对!").addClass("tipserror");
            $(".addressPhoneTip").text("手机号码格式不对!").removeClass("error-msg");
            return;
        }
    }else{
        $(".addressPhoneTip").text("手机号码不能为空!").addClass("tipserror");
        $(".addressPhoneTip").text("手机号码不能为空!").removeClass("error-msg");
        return;

    }
    //判断临时用户的手机是否已经存在
    var conditions="mobel="+addressMoblie.val();
    $.ajax({
        type: "POST",
        url: "../isshowmobelexist.htm",
        data: conditions,
        success: function(msg){
            if(msg=="1"){
                $(".addressPhoneTip").html("手机号码已存在,请更换号码，或者<a href='../login.html'>登陆 </a>").addClass("tipserror");
                $(".addressPhoneTip").html("手机号码已存在,请更换号码，或者<a href='../login.html'>登陆 </a>").removeClass("error-msg");
            }

        }
    });
    //判断临时用户的手机是否已经存在end
}
//固定电话
function checkAddressPhone(){
    var addressPhone=$(".save_add_phone");
    if( $(addressPhone).val() !=""){
        if (/(\d{4}-?\d{7,8})|(\d{3}-?\d{8})/.test( $(addressPhone).val() ) ) {
            if($(addressPhone).val()!=""){
                $(".addressPhoneTip").text("").removeClass("tipserror");
                $(".addressPhoneTip").text("").addClass("error-msg");
            }
        }else{
            $(".addressPhoneTip").text("电话格式不正确!").addClass("tipserror");
            $(".addressPhoneTip").text("电话格式不正确!").removeClass("error-msg");
        }
    }else{
        $(".addressPhoneTip").text("电话号码不能为空!").addClass("tipserror");
        $(".addressPhoneTip").text("电话号码不能为空!").removeClass("error-msg");
    }
}

//邮箱
function checkAddressEmail(){
    if($(".save_add_email").val() != "" && $(".save_add_email").val()!=null){
        if(/^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/.test($(".addEmailTips").val())){
            $(".addEmailTips").text("").removeClass("tipserror");
            $(".addEmailTips").text("").addClass("error-msg");
            params+="&addressEmail="+$(addressZip).val();
        }else{
            $(".addEmailTips").text("邮箱格式不正确!").addClass("tipserror");
            $(".addEmailTips").text("邮箱格式不正确!").removeClass("error-msg");
        }
    }
}

//邮政编码
function checkAddressPost(){
    if($(".save_add_post").val() != "" && $(".save_add_post").val()!=null){
        if(/^\d{6}$/.test($(".save_add_post").val())){
            $(".addPostTips").text("").removeClass("tipserror");
            params+="&addressZip="+$(addressZip).val();
        }else{
            $(".addPostTips").text("邮政编码格式不正确!").addClass("tipserror");
        }
    }
}

//重置添加地址的表单
function setDefaultForm(){
    $(".save_add_name").val("");
    $(".save_add_detail").val("");
    $(".save_add_mobile").val("");
    $(".save_add_phone").val("");
    $(".save_add_post").val("");
    $(".save_add_email").val("");
    $(".addressNameTip").text("");
    $(".addressDetailTip").text("");
    $(".addressPhoneTip").text("");
    $(".addressEmailTip").text("");
}

//选择发票抬头是个人的时候 
function checkGeRen(obj){
    $(".invoiceTitleTip").text("");
    if($(obj).prop("checked")){
        $(".invoiceTitle").val($(".addressName").html()).addClass("none");
        $(".ch_invoiceTitle").val($(".addressName").html());
    }
}
//选择发票抬头是单位的时候 
function checkDanWei(obj){
    if($(obj).prop("checked")){
        $(".invoiceTitle").val("").removeClass("none");
    }
}

function changeScre() {
    var amount = $("#amount").val(0);
    jifen();
    $("#amount").val("");
}

function clearCoupon(){
    $("#useCoupon").val("");
    checkCoupon();
    $("#errorTips").html("");
}

//使用优惠券
function checkCoupon(){
    var boxs = new Array();
    //获取订单数量

    $('input[name="box"]').each(function () {
        boxs.push(parseInt($(this).val()));
    });
    $("#codeNo").val($("#useCoupon").val());
    $.ajax({
        type: 'post',
        url:'/getUsedCouponBycodeNo.htm?codeNo='+$("#useCoupon").val()+"&box="+boxs,
        async:false,
        dataType:"json",
        success: function(data) {
            var discountPrice = $("#discount").val()    //会员折扣价
            var amount = $('#amount').val();                //需要兑换的积分
            var pointSet = $('#pointSet').val();          //积分兑换规则
            var zhekou = amount * pointSet / 10;                //积分兑换的折扣价格
            $("#yh").html("-￥" + data+".00");
            $("#youhui").val(data);
            var sumPrice = $("#sumPrice").val();

            var sum = Subtr(Subtr(sumPrice, data),discountPrice);
            var lastpay = sum;
            //判断是否有积分兑换 如果有总价格减去积分兑换的金额
            if (zhekou != 0) {
                lastpay = lastpay - zhekou;
            }

            $("#lastpay").html("￥" +lastpay);
            $("#lastpays").html("￥" + lastpay);
            if (data==0) {
                $(".us").html('0');
            } else {
                $(".us").html('1');
            }
            $(".up").html(data);
            var sum = $('#lastpay').html();
            //金额小于1  设置总订单的价格为1分钱
            if (sum < 1) {
                $('#lastpay').html("￥" +'0.01')
            }
            if(data==0){
                $("#errorTips").html("请输入正确且未使用的优惠券");

            }else{
                $("#errorTips").html("");
            }
        }
    });

}

//积分兑换
function jifen(){
    var flag=true;
//	$("#tishi").html('此功能正在完善，敬请期待！');
//	$('#amount').val('');
    var sum = $('#sumPrice').val();              //订单总价格
    var bosssum= $('#bosssumPrice').val();  		//boss订单总价格
    var pointSet = $('#pointSet').val();          //积分兑换规则
    var amount = $('#amount').val();                //需要兑换的积分
    var customerPoint =$('#jifen1').val();   //总积分
    var keyongSumPoint=$('#jifen2').val();	//可用总积分
    var zhekou = amount * pointSet/10;                //积分兑换的折扣价格
    var youhui = $("#youhui").val();
    var hydiscount = $("#discount").val();  //会员折扣价
    var zhekoujiage = Subtr(Subtr(Subtr(sum, zhekou), youhui),hydiscount);                //积分兑换后的价格且减去优惠劵的价格
    var muqianjifen = Subtr(customerPoint,amount);        //目前积分数量
    var muqiankeyongjf=Subtr(keyongSumPoint,amount);		//目前可用积分
   if($("#isOpen").val()=='1'){


    if(amount==""||amount==null){
      //  $("#tishi").html('注：请填写要兑换的积分数量！');
      //  $('#amount').val(0);
        return;
    }else{
        //当订单金额小于1时  禁止积分兑换
        if(bosssum<1){
            flag=false;
            $("#tishi").html('注：目前订单金额不支持使用积分兑换功能!');
            $('#amount').val('');
        }else{
            if(parseInt(amount)>10000){
                flag=false;
                $("#tishi").html('注：亲积分每次使用最多不可以超过10000！');
                $('#amount').val('');
            } else if (parseInt(customerPoint) < parseInt(amount)) {//判断用户的积分情况
                flag=false;
                $("#tishi").html('注：亲您没有那么多可用积分！');
                $('#amount').val('');
            }else{
                if(amount %10 !=0){
                    flag=false;
                    $("#tishi").html('注：兑换的积分必须为10的倍数!');
                    $('#amount').val('');
                }else{
                    //积分兑换的金额不能大于订单总金额
                    if(zhekou>bosssum){
                        flag=false;
                        var keyishiyong = accDiv(accMul(bosssum,10),pointSet);
                        keyishiyong=parseInt(parseInt(accDiv(parseInt(keyishiyong),10))+"0");
                        $("#tishi").html("注：此次订单价格最多可以使用【"+keyishiyong+"】的积分！</span>");
                        $('#amount').val('');
                    }else{
                        $('#muqianjifen').html(muqianjifen);           //设置目前有的积分
                        $('#muqianjifen1').html(muqiankeyongjf);          //设置目前可用的积分
                        //转换为字符串
                        var jifenString = zhekoujiage.toString();
                        var zheKouString = zhekou.toString();
                        //如果没有小数
                        if (jifenString.indexOf('.')<0){
                            $('#lastpay').html("￥"+zhekoujiage+'.00');         //设置应付总金额
                            $('#lastpays').html("￥"+zhekoujiage+'.00');
                            //给总价格赋值  用于同优惠券一起使用
                            $('#payPrice').val(zhekoujiage+'.00');
                        }else{
                            //如果应付总金额小数点后面只有一位 就补一个0 否则就去当前计算得出的价格
                            if(jifenString.length-jifenString.indexOf('.')==1){
                                $('#lastpay').html("￥"+zhekoujiage+'0');         //设置应付总金额
                                $('#lastpays').html("￥"+zhekoujiage+'0');
                                //给总价格赋值  用于同优惠券一起使用
                                $('#payPrice').val(zhekoujiage+'0');
                            }else if(jifenString.length-jifenString.indexOf('.')>2){
                                $('#lastpay').html("￥"+(zhekoujiage+"").substr(0,jifenString.indexOf('.')+3));         //设置应付总金额
                                $('#lastpays').html("￥"+(zhekoujiage+"").substr(0,jifenString.indexOf('.')+3));
                                //给总价格赋值  用于同优惠券一起使用
                                $('#payPrice').val((zhekoujiage+"").substr(0,jifenString.indexOf('.')+3));
                            }else{
                                $('#lastpay').html("￥"+zhekoujiage);         //设置应付总金额
                                $('#lastpays').html("￥"+zhekoujiage);
                                //给总价格赋值  用于同优惠券一起使用
                                $('#payPrice').val(zhekoujiage);
                            }
                        }
                        //如果金额长度大于保留的两位小数的长度 就截取前面4位
                        //如果没有小数
                        if (zheKouString.indexOf('.')<0){
                            zheKouString = zheKouString+".00";
                        }else {
                            //如果应付总金额小数点后面只有一位 就补一个0 否则就去当前计算得出的价格
                            if (zheKouString.length - zheKouString.indexOf('.') == 1) {
                                zheKouString = zheKouString + "0";
                            } else if (zheKouString.length - zheKouString.indexOf('.') > 2) {
                                zheKouString = (zheKouString + "").substr(0, zheKouString.indexOf('.') + 3);
                            } else {
                                zheKouString = zheKouString;
                            }
                        }
                        $('#jf').html("-￥"+zheKouString);         //设置商品金额减去的积分兑换的金额
                        $("#tishi").html('');
                    }
                }
            }

        }
    }
   }else{
       $("#tishi").html('注：不允许使用积分兑换金额,如有疑问请联系客服!');
       $('#amount').val('');
       return ;
   }

    return flag;

}

//根据收货人地址自适应样式
function updateadd(count){
    if(count>5){
        $(".consignee-cont").css("height","210px");
        $(".addr-up").show();
        $(".addr-down").show();
    }else{
        $(".consignee-cont, .cust_allAddress").css("height","auto");
        $(".addr-up").hide();
        $(".addr-down").hide();
    }

}


