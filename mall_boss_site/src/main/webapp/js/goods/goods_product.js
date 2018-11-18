window.onload = function ()
{
    /*点击下一步的时候*/
    $("#next").button().click(function ()
    {
        if (checkBase())
        {
        	$(".save_productPrice").val($("#save_goodsInfoPreferPrice").val());
            $(".save_productVipPrice").val($("#save_goodsInfoVipPrice").val());
            $("#" + $(this).attr("tourl")).click();
            $(this).css('display', 'none');
            $("#pre").css('display', 'inline-block');
            $("#subForm").css('display', 'inline-block');
        }
        
        
    });
    /*点击上一步的时候*/
    $("#pre").button().click(function ()
    {
        $("#subForm").css('display', 'none');
        $("#next").css('display', 'inline-block');
        $("#" + $(this).attr("tourl")).click();
        if ($(this).attr("tourl") == "base") {
            $("#pre").css('display', 'none');
            $("#next").css('display', 'inline-block');
        }
    });
    /*点击下一步的时候*/
    $("#update_next").button().click(function ()
    {
        if($("#updateProduct").valid()){ //{rules:{goodsInfoOem:{required: false, minlength: 0}}}
            $('#addSku1').modal('hide');$('#addSku2').modal('show');
        }
    });

    /*点击上一步的时候*/
    $("#update_pre").button().click(function ()
    {
        $("#subUPForm").css('display', 'none');
        $("#update_next").css('display', 'inline-block');
        $("#" + $(this).attr("tourl")).click();
        if ($(this).attr("tourl") == "updateBase")
        {
            $("#update_pre").css('display', 'none');
            $("#update_next").css('display', 'inline-block');
        }
    });
};
/*验证基本信息*/
function checkBase()
{
    var bValid = true;
    $(".ui-state-error").removeClass("ui-state-error");
    $(".ui-state-highlight").removeClass("ui-state-highlight");
    bValid = checkSpecSymb("save_goodsInfoStock", "save_goodsInfoStock_Tips") && bValid;
    bValid = checkRegexp($("#save_goodsInfoItemNo"), /^[A-Za-z0-9]+$/, "货品编号必须为数字.",$(".save_goodsInfoItemNo_Tips")) && bValid;
    bValid = checkSpecSymb("save_goodsInfoName", "save_goodsInfoName_Tips") && bValid;
    bValid = checkSpecSymb("save_goodsInfoSubtitle", "save_goodsInfoSubtitle_Tips") && bValid;
    bValid = checkSpecSymb("save_goodsInfoWeight", "save_goodsInfoWeight_Tips") && bValid;
    if ( checkRegexp($("#save_goodsInfoItemNo"), /^[A-Za-z0-9]+$/, "货品编号必须为数字.",$(".save_goodsInfoItemNo_Tips")))
    {
        bValid = checkLength( $("#save_goodsInfoItemNo"), "货品编号", $(".save_goodsInfoItemNo_Tips"), 10, 
        32 ) && bValid;
    }
    if (checkSpecSymb("save_goodsInfoName", "save_goodsInfoName_Tips"))
    {
        bValid = checkLength( $("#save_goodsInfoName"), "货品名称", $(".save_goodsInfoName_Tips"), 3, 50 ) && bValid;
    }
    if (checkSpecSymb("save_goodsInfoStock", "save_goodsInfoStock_Tips"))
    {
        bValid = checkRegexp( $("#save_goodsInfoStock"), /^[0-9]+$/, "库存必须为正整数.", $(".save_goodsInfoStock_Tips") ) && bValid;
    }
    bValid = checkRegexp( $("#save_goodsInfoPreferPrice"),/^[0-9]+[.]{0,1}[0-9]{0,2}$/, "销售价格输入格式不正确.", $(".save_goodsInfoPreferPrice_Tips") ) && bValid;
    bValid = checkRegexp( $("#save_goodsInfoVipPrice"),/^[0-9]+[.]{0,1}[0-9]{0,2}$/, "会员价格输入格式不正确.", $(".save_goodsInfoVipPrice_Tips") ) && bValid;
    bValid = checkRegexp( $("#save_goodsInfoCostPrice"), /^[0-9]+[.]{0,1}[0-9]{0,2}$/, "成本价输入格式不正确.", $(".save_goodsInfoCostPrice_Tips") ) && bValid;
    bValid = checkRegexp( $("#save_goodsInfoMarketPrice"), /^[0-9]+[.]{0,1}[0-9]{0,2}$/, "市场价格输入格式不正确.", $(".save_goodsInfoMarketPrice_Tips") ) && bValid;
    bValid = checkRegexp( $("#save_goodsInfoWeight"), /^[0-9]+(.[0-9]+)?$/, "重量输入格式不正确.", $(".save_goodsInfoWeight_Tips") ) && bValid;
    if ($(".checkExistsFlag").val() == "0")
    {
        $("#save_goodsInfoItemNo").addClass("ui-state-error");
        $(".save_goodsInfoItemNo_Tips").addClass("ui-state-highlight");
        $(".save_goodsInfoItemNo_Tips").text("名称或编号已经存在!");
        bValid = false;
    }
    return bValid;
}

/*更新时的基本信息验证*/
function checkUpdateBase()
{
    var bValid = true;
    $(".ui-state-error").removeClass("ui-state-error");
    $(".ui-state-highlight").removeClass("ui-state-highlight");
    bValid = checkSpecSymb("update_goodsInfoName", "update_goodsInfoName_Tips") && bValid;
    bValid = checkSpecSymb("update_goodsInfoSubtitle", "update_goodsInfoSubtitle_Tips") && bValid;
    bValid = checkSpecSymb("update_goodsInfoStock", "update_goodsInfoStock_Tips") && bValid;
    bValid = checkRegexp($("#update_goodsInfoItemNo"), /^[A-Za-z0-9]+$/, "货品编号必须为数字.", $(".update_goodsInfoItemNo_Tips")) && bValid;
    bValid = checkSpecSymb("update_goodsInfoWeight", "update_goodsInfoWeight_Tips") && bValid;
    if (checkRegexp($("#update_goodsInfoItemNo"), /^[A-Za-z0-9]+$/, "货品编号必须为数字.", $(".update_goodsInfoItemNo_Tips")))
    {
        bValid = checkLength( $("#update_goodsInfoItemNo"), "货品编号", $(".update_goodsInfoItemNo_Tips"), 
        10, 32 ) && bValid;
    }
    if (checkSpecSymb("update_goodsInfoName", "update_goodsInfoName_Tips"))
    {
        bValid = checkLength( $("#update_goodsInfoName"), "货品名称", $(".update_goodsInfoName_Tips"), 3, 
        50 ) && bValid;
    }
    if (checkSpecSymb("update_goodsInfoStock", "update_goodsInfoStock_Tips"))
    {
        bValid = checkRegexp( $("#update_goodsInfoStock"), /^[0-9]+$/, "库存必须为正整数.", $(".update_goodsInfoStock_Tips") ) && bValid;
    }
    bValid = checkRegexp( $("#update_goodsInfoPreferPrice"), /^[0-9]+[.]{0,1}[0-9]{0,2}$/, "销售价格输入格式不正确.", $(".update_goodsInfoPreferPrice_Tips") ) && bValid;
    bValid = checkRegexp( $("#update_goodsInfoVipPrice"), /^[0-9]+[.]{0,1}[0-9]{0,2}$/, "会员价格输入格式不正确.", $(".update_goodsInfoVipPrice_Tips") ) && bValid;
    bValid = checkRegexp( $("#update_goodsInfoCostPrice"), /^[0-9]+[.]{0,1}[0-9]{0,2}$/, "成本价输入格式不正确.", $(".update_goodsInfoCostPrice_Tips") ) && bValid;
    bValid = checkRegexp( $("#update_goodsInfoMarketPrice"),/^[0-9]+[.]{0,1}[0-9]{0,2}$/, "市场价格输入格式不正确.", $(".update_goodsInfoMarketPrice_Tips") ) && bValid;
    bValid = checkRegexp( $("#update_goodsInfoWeight"), /^[0-9]+(.[0-9]+)?$/, "重量输入格式不正确.", $(".update_goodsInfoWeight_Tips") ) && bValid;
    if ($(".checkExistsFlag").val() == "0")
    {
        $("#update_goodsInfoItemNo").addClass("ui-state-error");
        $(".update_goodsInfoItemNo_Tips").addClass("ui-state-highlight");
        $(".update_goodsInfoItemNo_Tips").text("名称或编号已经存在!");
        bValid = false;
    }
    return bValid;
}
/*更新时的规格验证*/
function checkUpdateSpec()
{
    var bValid = true;
    selProductParam('update_param_Tips', 1);
    if ($(".checkProdcutExists").val() == "0")
    {
        $(".update_param_Tips").addClass("ui-state-highlight");
        updateTips("所选参数已经生成货品,请重新选择!", $(".update_param_Tips"));
        bValid = false;
    }
    var params = $(".up_sel_spec");
	for(var i=0;i<params.length;i++){
		$(".up_remark_"+$(params[i]).attr("spec_id")).val($(params[i]).find("option:selected").text().trim());
	}
    /*验证分仓的信息*/
    var productStock = $(".update_productStock");
    for(var i = 0;i<productStock.length;i++){
    	bValid = checkRegexp( $(productStock[i]), /^[0-9]+$/, "",null ) && bValid;
    }
    var productPrice = $("input[class*='update_product'");//var productPrice = $(".update_productPrice"); 2015.10.22 wuyanbo 修改价格校验
    for(var i = 0;i<productPrice.length;i++){
    	bValid = checkRegexp( $(productPrice[i]), /^[0-9]+(.[0-9]{0,2})?$/, "",null ) && bValid;
    }
    return bValid;
}
/*导出货品列表*/
function exportProductList()
{
    $("#searchForm").attr("action", "exportProductList.htm?CSRFToken="+$(".token_val").val());
    $("#searchForm").submit();
    $("#searchForm").attr("action", "queryAllByGoodsId.htm?CSRFToken="+$(".token_val").val());
}
/*导出当前页的数据*/
function exportProductPage()
{
    $("#searchForm").attr("action", "exportProductPage.htm?CSRFToken="+$(".token_val").val());
    $("#searchForm").submit();
    $("#searchForm").attr("action", "queryAllByGoodsId.htm?CSRFToken="+$(".token_val").val());
}
/*导出当前选中的数据*/
function exportProductCheck()
{
    var bool = false;
    var brandIds = document.getElementsByName("productIds");
    for (var i = 0; i < brandIds.length; i++) {
        var e = brandIds[i];
        if (e.checked == true) {
            bool = true;
        }
    }
    if (bool == false) 
    {
        $("#dialog-tip").dialog( 
        {
            resizable : false, height : 150, width : 270, modal : true, autoOpen : true, buttons : {
                "确定" : function () 
                {
                    $(this).dialog("close");
                }
            }
        });
    }
    else 
    {
        $("#productList").attr("action", "exportProductByChecked.htm?CSRFToken="+$(".token_val").val());
        $("#productList").submit();
        $("#productList").attr("action", "");
    }
}

