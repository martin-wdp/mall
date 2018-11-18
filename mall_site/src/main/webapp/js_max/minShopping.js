//购物车
$(function(){
    $(".cartfd-mem").mouseover(function(){
        $(this).find(".miniCart-mem").show();
    }).mouseout(function(){
        $(this).find(".miniCart-mem").hide();
    });
    $(".mcOrder-men").each(function(){
        var $this = $(this);
        $this.mouseover(function(){
            $this.find(".minus, .plus").css("visibility","visible");
            $this.find(".del").show();
        }).mouseout(function(){
            $this.find(".minus, .plus").css("visibility","hidden");
            $this.find(".del").hide();
        });
    });
    $(".cartit-mem").live("mouseover",minicartonloadmem);
    $("#delect_minicart-mem").live("click", function(){
        var emp1 = $(this).next().val();
        var emp2 = $(this).next().next().val();
        $.ajax({ url: "../delshoppingcartbyid.htm?shoppingCartId="+emp1+"&goodsInfoId="+emp2,async:false, success: function(dats){
            if(dats == 1){
                minicartonloadmem();
            }
        }});
    });
    //预加载mini购物车
    minicartonloadmem();

});


function minicartonloadmem(){
    $.ajaxSetup({ cache: false });
    $.ajax({ url: "../minisscart.htm",  async:false ,success: function(datee){
        var cartgoods = datee.shopcart.miniGoodsList;
        //alert("cartgoods"+cartgoods);
        var cust= datee.cust;
        //alert("cust"+cust);
        if(cust!=null){
            $(".cart_empty-mem").html("<p style='height:35px;'>您的购物车是空的<br /></p>");
        }
        var empvalue = 0;
        //设置我的购物车显示购物车商品数量
        if(cartgoods != null){
            $(".sc_num-mem em").html(cartgoods.length);
        }else{
            $(".sc_num-mem em").html("0");
        }
        //比如一件商品买了3件,详细统计购买商品数量
        var countgoods=0;
        //购物车中有商品
        if(cartgoods != null && cartgoods.length>0){
            $(".cart_empty-mem").addClass("none");
            $(".cart_cont-mem").removeClass("none");
            //mini购物车头部
            //循环输出购物车中的商品
            $(".mcBoxList-mem").html("");
            for(var i = 0 ; i < cartgoods.length ; i++) {
                countgoods += cartgoods[i].buNum;
                var cartgood = "";
                if (cartgoods[i].fitId == null) {
                    cartgood = ' <div class="mcOrder-mem clearfix">' +

                    ' <div class="mcItem-mem">' +
                    '<a  href="../item/' + cartgoods[i].goodsInfoId + '" class="img" >' +
                    '<div class="table-cell-mem">' +
                    '   <i></i><img src="' + cartgoods[i].productPic + '" alt=""  width="52">' +
                    '  </div>' +
                    ' </a>' +
                    '</div>' +
                    '<div class="mcSqe-mem">' +
                    '<p><a  href="../item/' + cartgoods[i].goodsInfoId + '">' + cartgoods[i].productName + '</a></p>' +
                    '</div>' +
                    ' <div class="mcAmount-mem" style="position:relative;left:30px;">' +
                        //'<span class="minus"></span>'+
                    '<span class="amount-mem">' + cartgoods[i].buNum + '</span>' +
                        //'<span class="plus"></span>'+
                    '</div>' +
                    '<div class="mcCost-mem">' +
                    '<a href="" class="del"  id = "delect_minicart-mem">删除</a>' +
                    "<input type= 'hidden' class= 'goodsid' value = '" + cartgoods[i].shoppingCartId + "'>" +
                    "<input type= 'hidden' class= 'goodsinfo' value = '" + cartgoods[i].goodsInfoId + "'>" +
                    '<span class="mcPrice-mem">&yen;' + cartgoods[i].productPrice + '<em style="color:#666">×' + cartgoods[i].buNum + '</em></span>' +
                    ' </div>' +
                    '</div>';
                    $(".mcBoxList-mem").append(cartgood);
                } else {
                    var cartgood = "<div class='tz-bar-mem clearfix'>" +

                        "<div class='ct_info-mem'>[套装]" +
                        cartgoods[i].miniFit.fitName +
                        "<span class='ct_price-mem ml10'><b>优惠：</b>&yen;"+cartgoods[i].miniFit.fitPrice+"<em style='color:#666'>×" + cartgoods[i].buNum + "</em></span>" +
                        "</div>" +
                        "<div class='cout_text-mem pa'></div>" +

                        '<a href="" class="del"  id = "delect_minicart-mem">删除</a>' +
                        "<input type= 'hidden' class= 'goodsid' value = '" + cartgoods[i].shoppingCartId + "'>" +
                        "<input type= 'hidden' class= 'goodsinfo' value = '" + cartgoods[i].goodsInfoId + "'>" +
                        "</div>";
                    $(".mcBoxList-mem").append(cartgood);

                    var goodsList = cartgoods[i].miniFit.miniGoods;
                    //套装中货品的销售价格
                    var suitPrice =0;
                    for (var j = 0; j < goodsList.length; j++) {
                        cartgood = ' <div class="mcOrder-mem tzOrder clearfix">' +

                        ' <div class="mcItem-mem">' +
                        '<a  href="../item/' + goodsList[j].goodsInfoId + '" class="img">' +
                        '<div class="table-cell-mem">' +
                        '   <i></i><img src="' + goodsList[j].productPic + '" alt=""  width="52">' +
                        '  </div>' +
                        ' </a>' +
                        '</div>' +
                        '<div class="mcSqe-mem">' +
                        '<p><a  href="../item/' + goodsList[j].goodsInfoId + '">' + goodsList[j].productName + '</a></p>' +
                        '</div>' +
                        '<div class="mcCost-mem">' +
                        '<span class="mcPrice-mem">&yen;' +goodsList[j].productPrice  +'<em style="color:#666">×' + cartgoods[i].buNum + '</em></span>' +
                        ' </div>' +
                        '</div>';
                        suitPrice=suitPrice+goodsList[j].productPrice;
                        $(".mcBoxList-mem").append(cartgood);

                    }
                }

                $(".mcOrder-mem").each(function(){
                    var $this = $(this);
                    $this.mouseover(function(){
                        $this.find(".minus-mem, .plus-mem").css("visibility","visible");
                        $this.find(".del-mem").show();
                    }).mouseout(function(){
                        $this.find(".minus-mem, .plus-mem").css("visibility","hidden");
                        $this.find(".del-mem").hide();
                    });
                });

                //计算总价格
                if(cartgoods[i].fitId==null){

                    empvalue =accAdd(empvalue,accMul(cartgoods[i].productPrice,cartgoods[i].buNum));
                }else{

                    empvalue=accAdd(empvalue,accMul(suitPrice-cartgoods[i].miniFit.fitPrice,cartgoods[i].buNum));
                }



            }
            //设置mini购物车底部

            $(".mcNumTotal-mem").text(countgoods);
            $(".mcNumChecked-mem").text(countgoods);
            $(".cartNum-mem").text(countgoods);
            $(".mcTotalFee-mem").text(empvalue);
            $(".emCartBox-mem").hide();
        }else{
            $(".emCartBox-mem").show();

        }

    }});

};
//加法函数
function accAdd(arg1, arg2) {
    var r1, r2, m;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2));

    return ((arg1 * m + arg2 * m) / m).toFixed(2);
}

//加法函数
function accAddInt(arg1, arg2) {
    var r1, r2, m;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2));

    return (arg1 * m + arg2 * m) / m;
}
//给Number类型增加一个add方法，，使用时直接用 .add 即可完成计算。
Number.prototype.add = function (arg) {
    return accAdd(arg, this);
};

//减法函数
function Subtr(arg1, arg2) {
    var r1, r2, m, n;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2));
    //last modify by deeka
    //动态控制精度长度
    n = (r1 >= r2) ? r1 : r2;
    return ((arg1 * m - arg2 * m) / m).toFixed(n);
}



//减法函数
function SubtrInt(arg1, arg2) {
    var r1, r2, m, n;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2));
    //last modify by deeka
    //动态控制精度长度
    n = (r1 >= r2) ? r1 : r2;
    return ((arg1 * m - arg2 * m) / m);
}

//给Number类型增加一个add方法，，使用时直接用 .sub 即可完成计算。
Number.prototype.sub = function (arg) {
    return Subtr(this, arg);
};

//乘法函数
function accMul(arg1, arg2) {
    if(arg1==null){
        return;
    }
    if(arg2==null){
        return;
    }
    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
    try {
        m += s1.split(".")[1].length;
    }
    catch (e) {
    }
    try {
        m += s2.split(".")[1].length;
    }
    catch (e) {
    }
    return (Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m)).toFixed(2);
}
//给Number类型增加一个mul方法，使用时直接用 .mul 即可完成计算。
Number.prototype.mul = function (arg) {
    return accMul(arg, this);
};

//除法函数
function accDiv(arg1, arg2) {
    var t1 = 0, t2 = 0, r1, r2;
    try {
        t1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
    }
    try {
        t2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
    }
    with (Math) {
        r1 = Number(arg1.toString().replace(".", ""));
        r2 = Number(arg2.toString().replace(".", ""));
        return ((r1 / r2) * pow(10, t2 - t1)).toFixed(2);
    }
}
//给Number类型增加一个div方法，，使用时直接用 .div 即可完成计算。
Number.prototype.div = function (arg) {
    return accDiv(this, arg);
};