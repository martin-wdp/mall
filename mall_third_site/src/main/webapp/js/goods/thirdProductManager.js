/**
 * 货品管理JS
 * author yuankk
 */
$(function () {
    /*当货品编号失去焦点的时候验证货品编号是否已经使用*/
    $(".goodsInfoItemNo").blur(function () {
        if (checkNull($(".goodsInfoItemNo"), $(".goodsInfoItemNo_tip"))) {
            if (checkSpecSymb($(".goodsInfoItemNo"), $(".goodsInfoItemNo_tip"))) {
                if ($(".oldGoodsInfoItemNo").val() != "") {
                    checkExists('checkProductNo', 'productNo', $(this), 'goodsInfoItemNo_tip', 1, "oldGoodsInfoItemNo");
                }
                else {
                    checkExists('checkProductNo', 'productNo', $(this), 'goodsInfoItemNo_tip', 0, null);
                }
            }
        }
    });
    /*点击保存的时候*/
    $(".sub_product_form").click(function () {
        checkProductForm();
    });
    /*点击关闭的时候*/
    $(".close_sub_product").click(function () {
        $(".sub_product")[0].reset();
        $(".ui-state-error").removeClass("ui-state-error");
        $(".ui-state-highlight").text("").removeClass("ui-state-highlight text-danger");
        /*移除更新时保存的临时参数*/
        $(".old_param_div").html("");
    });
    /*点击批量上架的时候*/
    $(".batch_upload_product").click(function () {
        var ch_product = $(".ch_product");
        var bool = 0;
        for (var i = 0; i < ch_product.length; i++) {
            if ($(ch_product[i]).prop("checked")) {
                bool = bool - 1 + 2;
            }
        }
        if (bool > 0) {
            $(".list_form").attr("action", "batchUploadThirdProduct.htm").submit();
        } else {
            $(".show_title").text("请至少选择一行进行操作!");
            dia(2);
        }
    });
    /*点击批量下架的时候*/
    $(".batch_down_product").click(function () {
        var ch_product = $(".ch_product");
        var bool = 0;
        for (var i = 0; i < ch_product.length; i++) {
            if ($(ch_product[i]).prop("checked")) {
                bool = bool - 1 + 2;
            }
        }
        if (bool > 0) {
            $(".list_form").attr("action", "batchDownThirdProduct.htm").submit();
        } else {
            $(".show_title").text("请至少选择一行进行操作!");
            $("#refuse-tip").modal('show');
        }
    });

    /*点击修改按钮*/
    $(".modi-goods").click(function () {
        $(".sub_product").attr("action", "updateThirdProduct.htm");
        $(".exec_flag").val("1");
        $.get("queryThirdProductVoByProductId.htm?thirdProductId=" + $(this).attr("data-role"), function (data) {

            $(".suppchecked").each(function(){
                for(var i=0;i<data.productSuppList.length;i++){
                    if(data.productSuppList[i].suppId==$(this).val()){
                        $(this).attr("checked",true);
                    }
                        }
            });

            data=data.product;
            $(".oldGoodsInfoItemNo").remove();
            $(".sub_product").append("<input type='hidden' class='old_product_id' name='goodsInfoId' value='" + data.goodsInfoId + "'  />");
            $(".sub_product").append("<input type='hidden' class='oldGoodsInfoItemNo'  value='" + data.goodsInfoItemNo + "'  />");
            $(".goodsInfoItemNo").val(data.goodsInfoItemNo);
            $(".goodsInfoName").val(data.goodsInfoName);
            $(".goodsInfoSubtitle").val(data.goodsInfoSubtitle);
            $(".goodsInfoStock").val(data.goodsInfoStock);
            $(".goodsInfoPreferPrice").val(data.goodsInfoPreferPrice);
            $(".goodsInfoCostPrice").val(data.goodsInfoCostPrice);
            $(".goodsInfoMarketPrice").val(data.goodsInfoMarketPrice);
            $(".goodsInfoWeight").val(data.goodsInfoWeight);
            $(".goodsInfoId").val(data.goodsInfoId);
            if (data.isCustomerDiscount == 0) {
                $("#isCustomer").attr("checked", true);
            } else {
                $("#isCustomerAdded").attr("checked", true);
            }
            if (data.showList == 1) {
                $(".show_list").prop("checked", "checked");
            } else {
                $(".unshow_list").prop("checked", "checked");
            }
            if (data.showMobile == 1) {
                $(".show_mobile").prop("checked", "checked");
            } else {
                $(".unshow_mobile").prop("checked", "checked");
            }
            if (data.goodsInfoAdded == "1") {
                $(".added_sta").prop("checked", "checked");
            } else {
                $(".un_added_sta").prop("checked", "checked");
            }
            for (var i = 0; i < data.specVo.length; i++) {
                $(".sel" + data.specVo[i].spec.specId).val(data.specVo[i].goodsSpecDetail.specDetailId);
                $(".old_param_div").append("<input type='hidden' class='old_param' value='" + data.specVo[i].goodsSpecDetail.specDetailId + "' />");
            }


        });
    });
    /*点击保存货品图片的时候触发*/
    $(".save_img").click(function () {
        $(".modi_third_product_img").submit();
    });

    /*选择图片*/
    $(".choose_image_button").click(function () {
        art.dialog.open('queryImageManageByChoose.htm?size=10000', {
            lock: true,
            width: '800px',
            height: '400px',
            title: '选择图片'
        });
    });

    //重置搜索
    $(".rst_btn").click(function () {
        $(".search_product_form")[0].reset();
        $(".form-control").val("");
    });

});
/*验证货品表单*/
function checkProductForm() {
    var bValid = true;

    bValid = checkRegexp($(".goodsInfoItemNo"), /^[A-Za-z0-9]+$/, "货品编号只能为数字.", $(".goodsInfoItemNo_tip")) && bValid;
    if (checkRegexp($(".goodsInfoItemNo"), /^[A-Za-z0-9]+$/, "货品编号只能为数字.", $(".goodsInfoItemNo_tip"))) {
        bValid = checkLength($(".goodsInfoItemNo"), "货品编号", $(".goodsInfoItemNo_tip"), 10, 32) && bValid;
    }
    bValid = checkNull($(".goodsInfoName"), $(".goodsInfoName_tip")) && bValid;
    bValid = checkSpecSymb($(".goodsInfoSubtitle"), $(".goodsInfoSubtitle_tip")) && bValid;
    bValid = checkSpecSymb($(".goodsInfoStock"), $(".goodsInfoStock_tip")) && bValid;
    if (checkSpecSymb($(".goodsInfoStock"), $(".goodsInfoStock_tip"))) {
        bValid = checkRegexp($(".goodsInfoStock"), /^[0-9]+$/, "库存必须为正整数.", $(".goodsInfoStock_tip")) && bValid;
    }
    bValid = checkSpecSymb($(".goodsInfoPreferPrice"), $(".goodsInfoPreferPrice_tip")) && bValid;
    if (checkSpecSymb($(".goodsInfoPreferPrice"), $(".goodsInfoPreferPrice_tip"))) {
        bValid = checkRegexp($(".goodsInfoPreferPrice"), /^[0-9]+[.]{0,1}[0-9]{0,2}$/, "销售价格输入格式不正确.", $(".goodsInfoPreferPrice_tip")) && bValid;
    }
    bValid = checkSpecSymb($(".goodsInfoCostPrice"), $(".goodsInfoCostPrice_tip")) && bValid;
    if (checkSpecSymb($(".goodsInfoCostPrice"), $(".goodsInfoCostPrice_tip"))) {
        bValid = checkRegexp($(".goodsInfoCostPrice"), /^[0-9]+[.]{0,1}[0-9]{0,2}$/, "成本价格输入格式不正确.", $(".goodsInfoCostPrice_tip")) && bValid;
    }
    bValid = checkSpecSymb($(".goodsInfoMarketPrice"), $(".goodsInfoMarketPrice_tip")) && bValid;
    if (checkSpecSymb($(".goodsInfoMarketPrice"), $(".goodsInfoMarketPrice_tip"))) {
        bValid = checkRegexp($(".goodsInfoMarketPrice"), /^[0-9]+[.]{0,1}[0-9]{0,2}$/, "市场价格输入格式不正确.", $(".goodsInfoMarketPrice_tip")) && bValid;
    }
    bValid = checkSpecSymb($(".goodsInfoWeight"), $(".goodsInfoWeight_tip")) && bValid;
    if (checkSpecSymb($(".goodsInfoWeight"), $(".goodsInfoWeight_tip"))) {
        bValid = checkRegexp($(".goodsInfoWeight"), /^[0-9]+[.]{0,1}[0-9]{0,2}$/, "重量输入格式不正确.", $(".goodsInfoWeight_tip")) && bValid;
    }

    if ($(".checkExistsFlag").val() == "0") {
        $(".goodsInfoItemNo").addClass("ui-state-error");
        updateTips("名称或编号已经存在!", $(".goodsInfoItemNo_tip"));
        bValid = false;
    }

    selProductParam('update_param_Tips', $(".exec_flag").val());
    if ($(".checkProdcutExists").val() == "0") {
        $(".spec_param_Tips").addClass("ui-state-highlight text-danger");
        updateTips("所选参数已经生成货品,请重新选择!", $(".spec_param_Tips"));
        bValid = false;
    }
    if (bValid) {
        $(".sub_product").submit();
    }
}
/*点击页面上的页码的时候触发*/
function changePageNo(obj) {
    /*获取查询的方式标记*/
    $(".search_product_form").append("<input type='hidden' name='pageNo' value='" + $(obj).attr("data-role") + "' />").submit();
}

/*点击编辑图片的时候  */
function modiImg(productId) {
    $(".group_wp").hide();
    $(".modi_img").show();
    $("#modi_image").show();
    $(".img_productId_hide").val(productId);
    $.get("queryImageListByProductId.htm?productId=" + productId, function (data) {
        var imageListHtml = "";
        for (var i = 0; i < data.length; i++) {
            imageListHtml = imageListHtml + "<li id='imagediv" + i + "'><img alt='' id='image" + i + "' src='" + data[i].imageInName + "' />" + "<div class='img-ops'>"+"<button class='btn btn-primary btn-xs' type='button'onclick=" + "setDefaultImage(this" + "'" + data[i].imageInName
            + "',"+data[i].goodsImgId+");" + ">设为默认</button>"+"<button class='btn btn-default btn-xs' type='button' onclick=" + "delImage(" + "'" + data[i].goodsImgId + "',this);"+">删除</button></div></li>";
        }
        $(".edit-images-list").html(imageListHtml);
    });
    $.get("queryProductVoByProductId.htm?productId=" + productId, function (data) {
        $(".default_image").val(data.goodsInfoImgId);
    });
}
/*设置货品默认图片*/
function setDefaultImage(imageName,imgId) {

    $(".default_image").val(imageName);
    $(".goodsImgId").val(imgId)
}
/*删除图片*/
var del_imageId;
var del_img_btn;
function delImage(imageId, btn) {
    del_img_btn = $(btn);
    del_imageId = imageId;
    $("#delete-tip").modal('show');
}
/*执行删除货品图片*/
function delProductImg() {
    $(".del_image").append("<input type='hidden' name='delImages' value='" + del_imageId + "' />");
    $(del_img_btn).parent().parent().remove();
    cls();
}

