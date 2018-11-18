/**
 * 第三方商品页面js author yuankk time 2014-5-8 11:05
 */
$(function () {
    /* 查询所有的一级分类,并添加到页面 */
    $.get("getAllThirdCateForList.htm", function (data) {
        tCates.length = 0;
        rec(data, 0);
        var html = "";
        for (var i = 0; i < tCates.length; i++) {
            html += "<li><a href='javascript:;' onclick='loadSecondCat(" + tCates[i].catId
            + ",this);'>" + tCates[i].catName + "</a></li>";
        }
        $(".cat_first").html(html);
        $(".cat_second").html("");
        $(".cat_third").html("");
    });
    /* 查询曾经使用的分类信息,并添加到下拉中 */
    $.get("getCookieThirdCate.htm", function (data) {
        if (null != data && data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                $(".use_third_cat").append(
                    "<option value=" + data[i].catId + ">" + data[i].catName + "</option>");
            }
        }
    });
    /* 当选择最近使用的分类的时候 */
    $(".use_third_cat").change(function () {
        /* 设置选中的值 */
        $(".ch_third_catid").val($(this).val());
        $(".firstCate").text("已选择最近使用的分类:" + $(".use_third_cat option:selected").text());
        $(".secondCate").text("");
        $(".thirdCate").text("");
    });

    // 步骤
    $(".step_wp:first").show();
    $(".step_next").click(
        function () {
            /* 如果点击的是第一阶段的下一步 */
            if ($(this).attr("data-role") == "step_first") {
                if ($(".ch_third_catid").val() == "-1") {
                    $(".show_title").text("请选择商品所属分类！");
                    $("#select-tip").modal('show');
                    return;
                } else {

                    /* 如果选择的分类验证通过,就加载所有签约的分类和签约的品牌 */
                    /* 加载签约的品牌 */
                    $(".grand_brand_sel option").remove();
                    /*$.get("queryGrandBrandByThirdId.htm", function (data) {
                        if (data != null && data.length > 0) {
                            for (var i = 0; i < data.length; i++) {
                               // $(".grand_brand_sel").append(
                                //    "<option value=" + data[i].brandId + ">"
                                //    + data[i].brandName + "</option>");
                            }
                        }
                    });*/
                }
            } else if ($(this).attr("data-role") == "step_second") {
                // 验证第二步
                var isPass = false;
                var msg = "验证未通过！";

                if ($(".ch_goods_cate").val() == "") {
                    msg = "请选择商品类目！";
                } else if ($(".type_spec:checked").length <= 0
                    || $(".check_spec:checked").length <= 0) {
                    msg = "请至少选择一个商品规格!";
                } else if (!$("#goods_info_form").valid()) {
                    return;
                } else {
                    isPass = true;
                }

                // 不通过则
                if (!isPass) {
                    $(".show_title").text(msg);
                    $("#select-tip").modal('show');
                    return;
                }
            }
            var cur = $(this).parents(".step_wp");
            cur.next(".step_wp").show();
            cur.hide();
        });

    $(".prev_step").click(function () {
        var cur = $(this).parents(".step_wp");
        cur.prev(".step_wp").show();
        cur.hide();
    });

    /* 绑定清除最近使用的分类的点击事件 */
    $(".clear_third_cate_cookie").click(function () {
        $.get("clearThirdCateFromCookie.htm", function (data) {
            if (data) {
                $(".show_title").text("清除最近使用的分类成功!");
                dia(1);
            } else {
                $(".show_title").text("清除最近使用的分类异常,请重试!");
                dia(1);
            }
        });
    });

});
/* 树形菜单的点击事件 */
function onClick(event, treeId, treeNode, clickFlag) {
    $(".goodsCatId").val(treeNode.id);
    $(".ch_cat_name").val(treeNode.name);
    $(".menuContent").fadeOut("slow");
    $(".aboutGoodsId").remove();
}
/* 显示树形控件 */
function showMenu() {
    var selObj = $(".goodsCatId");
    var businessOffset = $(".goodsCatId").offset();
    $(".menuContent").css({
        left: businessOffset.left + 800 + "px",
        top: businessOffset.top - selObj.outerHeight() + 300 + "px"
    }).slideDown("fast");
    onBodyDownForArea();
}
/* 隐藏树形控件 */
function onBodyDownForArea() {
    jQuery.fn.isChildAndSelfOf = function (b) {
        return (this.closest(b).length > 0);
    };
    $(document).click(
        function (event) {
            if (!($(event.target).isChildAndSelfOf(".menuContent"))
                && !($(event.target).hasClass("st_choose"))) {
                $(".menuContent").fadeOut("slow");
            }
            ;
        });
}
var tCates = new Array(0);
/* 递归 */
function rec(data, pid) {
    for (var i = 0; i < data.length; i++) {
        var cpid = data[i].catParentId;
        if ((cpid + "") == (pid + "")) {
            tCates[tCates.length] = data[i];
        }
        var cateVos = data[i].cateVos;
        rec(cateVos, pid);
    }
}

/* 点击一级分类的时候加载第二级分类 */
function loadSecondCat(catId, obj) {
    /* 设置选中的值 */
    $(".ch_third_catid").val(catId);
    $("#parentId1").val(catId);
    /* 添加分类选中的样式 */
    $($(obj).parent()).addClass('cur').siblings().removeClass('cur');
    $(".firstCate").html(
        "<span class='cateName'>" + $(obj).html()
        + "</span><input type='hidden' class='firstCateId' value='" + catId + "'/>");
    $(".secondCate").html("");
    $(".thirdCate").html("");
    $(".fouthCate").html("");
    /* 清空所有的查询 */
    $(".sear_second").val("");
    $(".sear_third").val("");
    $(".sear_fouth").val("");
    /* 设置二级分类的全局变量为空 */
    allSecondThirdCat = null;
    allThirdThirdCat = null;
    allFouthThirdCat = null;
    /* 查询子级分类 */
    $.get("getAllThirdCateForList.htm", function (data) {
        tCates.length = 0;
        rec(data, catId);
        var html = "";
        for (var i = 0; i < tCates.length; i++) {
            html += "<li><a href='javascript:;' onclick='loadThirdCat(" + tCates[i].catId
            + ",this);'>" + tCates[i].catName + "</a></li>";
        }
        $(".cat_second").html(html);
        $(".cat_third").html("");
        $(".cat_fourth").html("");
    });
}
/* 点击二级分类的时候加载第三级分类 */
function loadThirdCat(catId, obj) {
    /* 设置选中的值 */
    $(".ch_third_catid").val(catId);
    $("#parentId2").val(catId);
    /* 添加选中的样式 */
    $($(obj).parent()).addClass('cur').siblings().removeClass('cur');
    $(".secondCate").html(
        "<span class='chs_line'>&gt;</span><span class='cateName'>" + $(obj).html()
        + "</span><input type='hidden' class='secondCateId' value='" + catId + "'/>");
    $(".thirdCate").html("");
    $(".fouthCate").html("");
    /* 清空第三四级的查询参数并设置临时参数为空 */
    $(".sear_third").val("");
    $(".sear_fouth").val("");
    allThirdThirdCat = null;
    allFouthThirdCat = null;
    /* 加载子级分类 */
    $.get("getAllThirdCateForList.htm", function (data) {
        tCates.length = 0;
        rec(data, catId);
        var html = "";
        for (var i = 0; i < tCates.length; i++) {
            html += "<li><a href='javascript:;' onclick='loadFourthCat(" + tCates[i].catId
            + ",this);'>" + tCates[i].catName + "</a></li>";
        }
        $(".cat_third").html(html);
        $(".cat_fourth").html("");
    });
}
/* 点击第三级分类的时候 加载第四季分类 */
function loadFourthCat(catId, obj) {
    /* 设置选中的值 */
    $(".ch_third_catid").val(catId);
    /* 添加选中的样式 */
    $($(obj).parent()).addClass('cur').siblings().removeClass('cur');
    $(".thirdCate").html(
        "<span class='chs_line'>&gt;</span><span class='cateName'>" + $(obj).html()
        + "</span><input type='hidden' class='thirdCateId' value='" + catId + "'/>");
    $(".fouthCate").html("");
    /* 清空第四级的查询参数并设置临时参数为空 */
    $(".sear_fouth").val("");
    allFouthThirdCat = null;
    /* 加载子级分类 */
    $.get("getAllThirdCateForList.htm", function (data) {
        tCates.length = 0;
        rec(data, catId);
        var html = "";
        for (var i = 0; i < tCates.length; i++) {
            html += "<li><a href='javascript:;' onclick='checkFourthCat(" + tCates[i].catId
            + ",this);'>" + tCates[i].catName + "</a></li>";
        }
        $(".cat_fourth").html(html);
    });
}
/* 点击第四级分类的时候 */
function checkFourthCat(catId, obj) {
    /* 添加选中的样式 */
    $($(obj).parent()).addClass('cur').siblings().removeClass('cur');
    $(".fouthCate").html(
        "<span class='chs_line'>&gt;</span><span class='cateName'>" + $(obj).html()
        + "</span><input type='hidden' class='fouthCateId' value='" + catId + "'/>");
    $(".ch_third_catid").val(catId);
}
/* 搜索一级分类 */
function searfirst() {
    var obj = $("#firstText");
    $("#cat_first").html('');
    var html = "";
    $.ajax({
        url: "queryThirdSonCateByCateIdAndName.htm",
        Type: "POST",
        data: {thirdCateId: 0, thirdCateName: $(obj).val()},
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                html += "<li><a href='javascript:;' onclick='loadSecondCat(" + data[i].catId
                + ",this);'>" + data[i].catName + "</a></li>";
            }
            /* 清空三四级分类 */
            $(".cat_second").html("");
            $(".cat_third").html("");
            $("#cat_first").html(html);
        }
    });
}
/* 搜索二级分类 */
function searSecond() {
    var obj = $("#secondText");
    $("#cat_second").html('');
    var parentId = $("#parentId1").val();
    var html = "";
    $.ajax({
        url: "queryThirdSonCateByCateIdAndName.htm",
        Type: "POST",
        data: {thirdCateId: parentId, thirdCateName: $(obj).val()},
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                html += "<li><a href='javascript:;' onclick='loadThirdCat(" + data[i].catId
                + ",this);'>" + data[i].catName + "</a></li>";
            }
            /* 清空三四级分类 */
            $(".cat_second").html("");
            $(".cat_third").html("");
            $("#cat_second").html(html);
        }
    });
}
/* 搜索三级分类 */
function searThird() {
    var obj = $("#thirdText");
    $("#cat_third").html('');
    var parentId = $("#parentId2").val();
    var html = "";
    $.ajax({
        url: "queryThirdSonCateByCateIdAndName.htm",
        Type: "POST",
        data: {thirdCateId: parentId, thirdCateName: $(obj).val()},
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                html += "<li><a href='javascript:;' onclick='loadFourthCat(" + data[i].catId
                + ",this);'>" + data[i].catName + "</a></li>";
            }
            /* 清空三四级分类 */
            $(".cat_third").html("");
            $("#cat_third").html(html);
        }
    });
}