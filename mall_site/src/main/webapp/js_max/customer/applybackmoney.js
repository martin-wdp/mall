var gid= "";
$(function(){
    //选中 没有其他凭证 就隐藏掉 质检报告 发票
    $('#applyCredentials0').click(function(){
        if("checked"== $('#applyCredentials0').attr("checked")){
            $('.item_f').css("display","none");
        }
    });


    $('#applyCredentials1').click(function(){
        if("checked"== $('#applyCredentials1').attr("checked")){
            $('.item_f').css("display","block");
        }
    });

    $('#applyCredentials2').click(function(){
        if("checked"== $('#applyCredentials2').attr("checked")){
            $('.item_f').css("display","block");
        }
    });




    $(".return-choose input").change(function(){
        var _chosen = $(".return-choose input[name='isBack']:checked").val();
        if(_chosen == "1") {
            $("#returnPrice02").hide();
            $(".back_goods").show();
            $("#returnPrice01, #backGoods").show();
        } else {
            $("#returnPrice02").show();
            $(".back_goods").hide();
            $("#returnPrice01, #backGoods").hide();
        };
    });

    $(".upload_pics").change(function() {
        if($(this).val()!=""){
            var orderId = $(this).attr("order-id");
            var shareImg = $(".share" + orderId);
            var waitImg = $("#wait_img"+orderId);
            gid = orderId;
            if(waitImg.length>0) return;
            if(shareImg.length==10) {
                $("#err_img_"+orderId).show();
                setTimeout(function(){
                    $("#err_img_"+orderId).hide();
                },3000);
                return;
            }
            $("#upload-form").attr("action","../back/upload.htm");
            $("#upload-form").submit();
            $("#show_pics"+orderId).append('<li id="wait_img'+orderId+'"><image src="../images/load.jpg" width="36px" height="36px"/></li>');
        }
    });

    /*退货*/
    $("#submitButton").click(function() {
        if($(".flag_saved").val()=="1"){
            return false;
        }else {
            $(".backReasonContent").text("");
            $(".applyCredentialsContent").text("");
            $(".backPriceContent").text("");
            $(".backRemarkContent").text("");
            $(".uploadDocumentContent").text("");
            var backReason = $("#backReason").val();
            if (backReason == '0') {
                $(".backReasonContent").text("请选择退单原因！");
                return;
            }
            // 选择申请凭据
            var typePics = $(".type-pics:checked");
            if (null == typePics || typePics.length == 0) {
                $(".applyCredentialsContent").text("请选择申请凭据！");
                return;
            }
            var backPrice = $("#backPrice").val();
            if (backPrice == '') {
                $(".backPriceContent").text("请填写退款金额！");
                return;
            }
            var backRemark = $("#backRemark").val();
            if (backRemark == '') {
                $(".backRemarkContent").text("问题说明不能为空！");
                return;
            }
            var showPics = $(".share" + $(".upload_pics").attr("order-id"));
            var imgs = '';
            for (var i = 0; i < showPics.length; i++) {
                imgs += $(showPics[i]).val() + ",";
            }
            //1：退单 2：退款
            var _chosen = $(".return-choose input[name='isBack']:checked").val();
            var _checkStatus=$("#staCheck").val();
            if(_checkStatus=='0'){
                //退款不验证货品信息
                if(_chosen ==1){
                    var type = $(".back_goods_id:checked");
                    if (null != type && type.length > 0) {
                        for (var j = 0; j < type.length; j++) {
                            $("#upload-form").append("<input type='hidden' name='goodsInfoIds' value=" + $(type[j]).val() + ">");
                        }
                    } else {
                        $(".backGoodsContent").text("请选择退货商品！");
                        return;
                    }
                }
            }


            if ($("#backPrice").val() == '') {
                $("#backPrice").attr("value", $(".back_price").val());
            }
            $(".flag_saved").val("1");
            $(".upload_documents").attr("value", imgs);
            $("#upload-form").removeAttr("target");
            $("#upload-form").attr("action", "../back/keepBackOrderInfo.htm");
            $("#upload-form").submit();
        }
    });


    /*退款*/
    $("#submitButtonprice").click(function() {
        if($(".flag_saved").val()=="1"){
            return false;
        }else {
            $(".backReasonContent").text("");
            $(".applyCredentialsContent").text("");
            $(".backPriceContent").text("");
            $(".backRemarkContent").text("");
            $(".uploadDocumentContent").text("");
            var backReason = $("#backReason").val();
            if (backReason == '0') {
                $(".backReasonContent").text("请选择退单原因！");
                return;
            }
            // 选择申请凭据
            var typePics = $(".type-pics:checked");
            if (null == typePics || typePics.length == 0) {
                $(".applyCredentialsContent").text("请选择申请凭据！");
                return;
            }
            var backPrice = $("#backPrice").val();
            if (backPrice == '') {
                $(".backPriceContent").text("请填写退款金额！");
                return;
            }
            var backRemark = $("#backRemark").val();
            if (backRemark == '') {
                $(".backRemarkContent").text("问题说明不能为空！");
                return;
            }
            //$(".upload_pics").attr("order-id")
            var showPics = $(".share" + $(".upload_pics").attr("order-id"));
            var imgs = '';
            for (var i = 0; i < showPics.length; i++) {
                imgs += $(showPics[i]).val() + ",";
            }

            if ($("#backPrice").val() == '') {
                $("#backPrice").attr("value", $(".back_price").val());
            }
            $(".flag_saved").val("1");
            $(".upload_documents").attr("value", imgs);
            $("#upload-form").removeAttr("target");
            $("#upload-form").attr("action", "../back/keepBackOrderInfoprice.htm");
            $("#upload-form").submit();
        }
    });



});

//退单金额的价格
var back_price = 0;
//保存要退的商品Id
var backGoodsId_sum = 0;

/*
 goodsId:商品ID与商品数量 -隔开
 price：退单的价格 zhanghl
 goodsNum：要退的商品的数量
* */
function getprice(goodsId,price,goodsNum){

    //要退单的商品ID
    var backGoodsId = $('#backGoodsId').val();
    //退单的金额
    var backPrice = $("[name='backPrice']").val();
    //判断是否选中要退单的商品
    var isChecked = $("#"+goodsId+"_back").attr("checked");
    //选中就把价格存入到退单金额
    if(isChecked=="checked" ){
        if(backPrice != ''){
            back_price = accAddInt(backPrice,price);
            backGoodsId_sum =  backGoodsId_sum+goodsId+","+goodsNum+"-";
        }else{
            back_price = price;
            backGoodsId_sum = goodsId+","+goodsNum+"-";
        }
    }else{
        if(backPrice != ''){
            back_price = SubtrInt(backPrice,price);
            var replace_text = goodsId+","+goodsNum+"-";
            if(backGoodsId.indexOf(replace_text)!=-1){
                backGoodsId_sum = backGoodsId.replace(replace_text,'');
            }

        }else{
            back_price = price;
        }
    }
    //把得到的价格  显示到页面
    if((back_price+"").indexOf(".")==-1){
        $("[name='backPrice']").val(back_price+".00");
    }else if((back_price+"").length-1-(back_price+"").indexOf(".")==1){
        $("[name='backPrice']").val(back_price+"0");
    }else{
        $("[name='backPrice']").val(back_price);
    }
    $("#backGoodsId").val(backGoodsId_sum);
}

/**
 * 上传文件成功后触发事件
 * @param msg
 */
function callback(msg) {
    //上传失败
    if(msg.split(",").length<2) {
        if(msg=='101') {
            $("#wait_img"+gid).remove();
            dia(5);
        } else if(msg=='102') {
            $("#wait_img"+gid).remove();
            dia(4);

        }
        return;
    }
    //上传成功
    var imageName = msg.split(",")[0],newImg ="";
    var orderId = msg.split(",")[1];
    $("#wait_img"+orderId).remove();
    if(imageName.indexOf("!")!= -1){
        newImg = imageName.substring(0,imageName.indexOf("!")+1)+56;
    }else{
        newImg = imageName;
    }
    $("#show_pics"+orderId).append('<li class="share_img"><input class="share'+orderId+'" type="hidden" value="'+imageName+'" ></input><span style="display:none;" class="del-img"><a href="javascript:;"  onclick="show_delPic('+orderId+');">x</a></span><img id="share'+orderId+'"  width="36" height="36" alt="" src="'+newImg+'" /></li>');
    $(".share_img").click(function() {
        $(".share_img").removeClass("selected");
        $(this).addClass("selected");
        $(this).find(".del-img").show();
    });
}


var orderIds = null;
function show_delPic(orderId){
    orderIds = orderId;
    $(".promotion_dialog_2").fadeIn();

}

function delPic() {
    var shareImg = $(".share"+orderIds);
    $(".upload_pics").val("");
    for(var i=0; i<shareImg.length; i++) {
        if(shareImg[i].parentElement.className.indexOf("selected")>0) {
            shareImg[i].parentElement.remove();
            $(".promotion_dialog_2").fadeOut();

        }
    }
}