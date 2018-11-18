/**
 * 商品筛选过滤操作
 * Created by liangck on 15/8/20.
 */

$(function () {

    //商品对比
    $(".goods_contrast").css("left",($(window).width()-1200)/2+210);
    $(".hide_ct").click(function(){
        $(".goods_contrast").hide();
    });

    //图片延迟加载
    $("img.lazy").lazyload({
        threshold: 600,
        effect: "fadeIn",
        failurelimit : 10,

        skip_invisible: false
    });

    //商品筛选
    $(".filter_item").each(function () {
        var _this = $(this);
        var f_name = _this.find("dt").text();
        var f_num = _this.attr("data-num");

        /* 检查是否有更多内容，有就显示更多按钮 */
        if (_this.find(".filterList ul").height() > 22) {
            _this.find(".f_more").removeClass("none");
        };

        /* 点击添加每一个筛选类型操作 */
        _this.find("ul a").click(function () {
            var _a = $(this);
            /*判断点击的参数类型*/
            var role = _a.attr("role");
            var f_value = _a.text();
            var p_value = _a.attr("param");
            var _selected = '<li data-num="' + f_num + '">';
            if (role == "brand") {
                /*如果是品牌属性添加品牌的隐藏域*/
                _selected += '<input type="hidden" name="brands" value="' + p_value + '">';
            } else if (role == "eparam") {
                /*如果是扩展属性添加扩展属性的隐藏域*/
                _selected += '<input type="hidden" name="params" value="' + p_value + '">';
            } else if (role == "spec") {
                /*如果是规格属性添加规格的隐藏域*/
                _selected += '<input type="hidden" name="spec" value="' + p_value + '">';
            }
            _selected += '<a href="javascript:;">' + f_name + '<em>' + f_value + '</em><b></b></a></li>';
            $("#selected_filter").show();
            $("#selected_filter ul").append(_selected);
            $(".more_filter:first").show().removeClass("more_filter");
            if (_this.find(".f_less").css("display") != "none") {
                _this.find(".filterList").height("");
                _this.find(".f_less").hide();
                _this.find(".f_more").show();
            }
            ;
            _this.addClass("selected").hide();
            if ($(".filter_item:visible").length < 4 || ($(".filter_item:visible").length == 4 && $(".more_filter").length == 0)) {
                $(".show_more, .show_less").hide()
            }
            ;
            /*提交表单*/
            $("#pageBeanShowPage").val("1");
            if($("#AutoStyle").val()==""){
                $("#AutoStyle").val($("#carstyleID").val());
            }
            $("#searchForm").submit();
        });
    });

    /* 点击删除每一个筛选类型操作 */
    $("#selected_filter ul a").click(function () {
        var _num = $(this).parent("li").attr("data-num");
        $(this).parent("li").remove();
        $(".pro_filter").find("dl[data-num=" + _num + "]").show().removeClass("selected");
        if ($(".filter_item:visible").length > 4 && $(".show_less").css("display") == "none") {
            $(".filter_item:visible:last").addClass("more_filter").hide();
            $(".show_more").show();
        }
        ;
        if ($("#selected_filter ul li").length == 0) {
            $("#selected_filter").hide()
        }
        ;
        /*提交表单*/
        $("#pageBeanShowPage").val("1");
        $("#AutoStyle").val("");
        $("#searchForm").submit();
    });

    $(".pro_filter .filter_item:eq(3)").nextAll(".filter_item").addClass("more_filter");	//默认显示4项，其余隐藏


    /* 点击展开按钮操作 */
    $(".show_more").click(function(){
        $(".more_filter").removeClass("more_filter").show();
        $(this).hide();
        $(".show_less").show();
    });
    /* 点击收起按钮操作 */
    $(".show_less").click(function(){
        $(".filter_item:visible:eq(3)").nextAll(".filter_item:visible").addClass("more_filter").hide();
        $(this).hide();
        $(".show_more").show();
    });

    /* 点击更多、收起按钮操作 */
    $(".f_more").click(function(){
        $(this).siblings(".filterList").height("auto");
        $(this).siblings(".f_less").show();
        $(this).hide();
    });
    $(".f_less").click(function(){
        $(this).siblings(".filterList").height("");
        $(this).siblings(".f_more").show();
        $(this).hide();
    });

    /* 点击全部撤销操作 */
    $(".cancel_filter").click(function(){
        $("#selected_filter ul li").remove();
        $("#selected_filter").hide();
        $(".filter_item").show();
        $(".pro_filter .filter_item:eq(3)").nextAll(".filter_item").addClass("more_filter").hide();
        $(".show_less").hide();
        $(".show_more").show();
        /*设置页码为1,并提交查询表单*/
        $("#pageBeanShowPage").val("1");
        $("#AutoStyle").val("");
        $("#searchForm").submit();
    });

    /*点击修改页码的时候触发*/
    $(".changePages").click(function(){
        /*设置页码为点击的自定义属性pages,并提交查询表单*/
        $("#pageBeanShowPage").val($(this).attr("pages"));
        if($("#AutoStyle").val()==""){
            $("#AutoStyle").val($("#carstyleID").val());
        }
        $("#searchForm").submit();
    });

});
/*商品筛选点击展示方式等*/
function changeSearch(obj, objName, valu)
{
    //如果点击的对象已经是当前选中的 就不进行操作
    if(objName=="searchSort"){
        var sort = $("#" + objName);
        if(sort.val()==""){
            sort.val(valu);
        }else if(valu=="2D"){
            if(sort.val()=="2D"){
                sort.val("22D");
            }else{
                sort.val(valu);
            }
        }else if(valu=="22D"){
            if(sort.val()=="22D"){
                sort.val("2D");
            }else{
                sort.val(valu);
            }
        }else if(valu=="1D"){
            if(sort.val()=="1D"){
                sort.val("11D");
            }else{
                sort.val(valu);
            }
        }else if(valu=="11D"){
            if(sort.val()=="11D"){
                sort.val("1D");
            }else{
                sort.val(valu);
            }
        }else if(valu=="3D"){
            if(sort.val()=="3D"){
                sort.val("33D");
            }else{
                sort.val(valu);
            }
        }else if(valu=="33D"){
            if(sort.val()=="33D"){
                sort.val("3D");
            }else{
                sort.val(valu);
            }
        }else if(valu=="4D"){
            if(sort.val()=="4D"){
                sort.val("44D");
            }else{
                sort.val(valu);
            }
        }else if($(this).attr("val")=="44D"){
            if(sort.val()=="44D"){
                sort.val("4D");
            }else{
                sort.val(valu);
            }
        }
        $("#pageBeanShowPage").val(1);

    }
    if (valu == "-1")
    {
        if (searchCheckShowStock.checked) {
            $("#" + objName).val(1);
        }
        else {
            $("#" + objName).val(0);
        }
        $("#pageBeanShowPage").val(1);

    }else if(valu == "-11"){
        $("#" + objName).val($(obj).val());
        $("#pageBeanShowPage").val(1);
    } else {
        if(objName!="searchSort"){
            $("#" + objName).val(valu);
        }else{
            if(!$(obj).hasClass("checked")){
                $("#" + objName).val(valu);
            }
        }

    }
    //获取到参数列表中所有的选中的节点，放在隐藏域中，提交查询表单
    var params = $(".selected");
    for (var i = 0; i < params.length; i++)
    {
        if ($(params[i]).find("a").hasClass("param"))
        {
            $("#searchForm").append("<input type='hidden' name=params value='" + $(params[i]).find("a").attr("id") + "' />");
        }
    }
    if($("#AutoStyle").val()==""){
        $("#AutoStyle").val($("#carstyleID").val());
    }
    $("#searchForm").submit();
}

//点击热门搜索的时候
function changeSearchKey(obj){
    //$(".search_text").val($(obj).html());
    window.location.href=$("#appBasePath").val()+"/searchProduct2.htm?title="+$(obj).html();
}

function win(){
    var _wd = $(window).width();
    var _hd = $(window).height();
    $(".dialog").css("top",(_hd - $(".dialog").height())/2).css("left",(_wd - $(".dialog").width())/2);
};

function dia(n){
    win();
    $(".mask").fadeIn();
    $(".dia"+n).fadeIn();
};

function cls(){
    $(".dialog").fadeOut();
    $(".mask").fadeOut();
};

