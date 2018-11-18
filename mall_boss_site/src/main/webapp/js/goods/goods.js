<!--全选-->
function selectAll(obj) {
    var checkboxs = document.getElementsByName(obj);
    for (var i = 0; i < checkboxs.length; i++) {
        var e = checkboxs[i];
        e.checked = !e.checked;
    }
    for (var j = 0; j < checkboxs.length; j++) {
        if (checkboxs[j].checked) {
            $(checkboxs[j]).parent().parent().addClass("trbcak");
        } else {
            $(checkboxs[j]).parent().parent().removeClass("trbcak");
        }
    }
}


/*点击生成索引的时候 */
function createIndex() {
    $.get("createIndex.htm?CSRFToken=" + $(".token_val").val(), function (data) {
        if (data) {
            showTipAlert("索引生成成功!");
        } else {
            showTipAlert("索引生成失败!");
        }
    });
}


/*点击生成索引的时候 */
function createNewIndex() {
    showTipAlert("索引正在生成中，请稍后...");
    var checkboxs = $("input[name='goodsIds']");
    var goodsIds = new Array();
    var oneSelect = false;
    for (var j = 0; j < checkboxs.length; j++) {
        if ($(checkboxs[j]).is(':checked') == true) {
            oneSelect = true;
            goodsIds.push($(checkboxs[j]).val());
        }
    }
    if (!oneSelect) {
        showTipAlert("请至少选择一条记录！");
        return;
    } else {
          $.ajax({
         url: "createIndex.htm?CSRFToken="+$(".token_val").val(),
         data: {"goodsIds": goodsIds},
         dataType: "json",
         type: "GET",
         success: function (data){
         if (data)
         {
         showTipAlert("索引生成成功!");
         }else {
         showTipAlert("索引生成失败!");
         }
         }
         });
       /* $.get("insertelasticgoods.htm?CSRFToken="+$(".token_val").val(),function(data){
         if (data==1)
         {
         showTipAlert("索引生成成功!");
         }else {
         showTipAlert("索引生成失败!");
         }
         });*/
    }
}


/*点击导出按钮的时候*/
function exportList() {
    $("#adv_form").attr("action", "exportGoodsList.htm");
    $("#adv_form").submit();
    $("#adv_form").attr("action", "findAllGoods.htm");
}

/*点击导出当前页的时候 */
function exportPage() {

    var checkboxs = document.getElementsByName("goodsIds");
    for (var i = 0; i < checkboxs.length; i++) {
        var e = checkboxs[i];
        e.checked = true;
    }
    $("#goodsList").attr("action", "exportGoodsCheck.htm?CSRFToken=" + $("#token_val").val());
    $("#goodsList").submit();
}
/*导出选中的记录*/
function exportCheck() {
    var bool = false;
    var brandIds = document.getElementsByName("goodsIds");
    for (var i = 0; i < brandIds.length; i++) {
        var e = brandIds[i];
        if (e.checked == true) {
            bool = true;
        }
        ;
    }
    if (bool == false) {
        $("#dialog-tip").find(".tip").text("请先选择一行!");
        $("#dialog-tip").dialog(
            {
                resizable: false, height: 150, width: 270, modal: true, autoOpen: true, buttons: {
                "确定": function () {
                    $(this).dialog("close");
                }
            }
            });
    }
    else {
        $("#goodsList").attr("action", "exportGoodsCheck.htm?CSRFToken=" + $(".token_val").val());
        $("#goodsList").submit();
        $("#goodsList").attr("action", "batchDelGoods.htm?CSRFToken=" + $(".token_val").val());
    }
    ;
}

/*批量上架*/
function batchUp(obj) {
    var bool = false;
    var brandIds = document.getElementsByName(obj);
    for (var i = 0; i < brandIds.length; i++) {
        var e = brandIds[i];
        if (e.checked == true) {
            bool = true;
        }
        ;
    }
    if (bool == false) {
        showTipAlert("请先选择一行!");
    }
    else {
        $("#goodsList").attr("action", "batchUpGoods.htm?CSRFToken=${token }");
        $("#goodsList").submit();
    }
}

/*批量下架*/
function batchDown(obj) {
    var bool = false;
    var brandIds = document.getElementsByName(obj);
    for (var i = 0; i < brandIds.length; i++) {
        var e = brandIds[i];
        if (e.checked == true) {
            bool = true;
        }
        ;
    }
    if (bool == false) {
        showTipAlert("请先选择一行!");
    }
    else {
        var token = $("#token").val();
        $("#goodsList").attr("action", "batchDownGoods.htm?CSRFToken=" + token);
        $("#goodsList").submit();
    }
}

/* 批量修改库存 */
function batchUpStock(obj) {
    var bool = false;
    var brandIds = document.getElementsByName(obj);
    for (var i = 0; i < brandIds.length; i++) {
        var e = brandIds[i];
        if (e.checked == true) {
            bool = true;
        }
        ;
    }
    if (bool == false) {
        showTipAlert("请先选择一行!");
    }
    else {
        art.dialog({
            width: '500px',
            content: '<div class="alert alert-warning alert-dismissible fade in" role="alert"> <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button><strong>注意!</strong> 您正在统一修改商品库存，请谨慎操作！</div><div class="form-horizontal"></div><div class="form-group batch_type1"><label class="control-label col-xs-6">统一库存值：</label><div class="col-xs-1"></div><div class="col-xs-7"><input class="form-control" type="text" name="stock" id="stock"><br/><stong id="kcError" style="color: red;"></stong></div></div><div class="form-group batch_type2" style="display:none;"><label class="control-label col-xs-6">计算库存值：</label><div class="col-xs-1"></div><div class="col-xs-4"><select class="form-control text-center"><option>+</option><option>-</option><option>×</option><option>÷</option></select></div><div class="col-xs-1"></div><div class="col-xs-7"><input class="form-control" type="text"></div></div></div>',
            lock: true,
            opacity: '.3',
            init: function () {
                $('#batchType').change(function () {
                    if ($(this).val() == 1) {
                        $('.batch_type2').hide();
                        $('.batch_type1').show();
                    }
                    else if ($(this).val() == 2) {
                        $('.batch_type1').hide();
                        $('.batch_type2').show();
                    }
                });
            },
            ok: function () {
                var stock = $("#stock").val();
                //声明正则表达式
                var t = /^[1-9]+[0-9]*]*$/;
                //验证
                if (!t.test(stock)) {
                    $("#kcError").html("请输入正整数!");
                    return false;
                } else {
                    $("#kcError").html("");
                    $("#stocks").val($("#stock").val());

                    $("#goodsList").attr("action", "batchUpdateStock.htm?CSRFToken=${token }");
                    $("#goodsList").submit();
                    return true;
                }
            },
            cancel: function () {
                return true;
            }
        });
    }
}