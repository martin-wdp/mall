
var numberchecked=1;
/*收藏商品方法*/
function attentionGuanzhu(obj){
    var districtId = $("#disId").val();
    var goodsprice = $("#followPrice").val();
    $.post("../atte/" + $(obj).attr("product_id") + ".html", {
        districtId: districtId,
        goodsprice: goodsprice

    }, function (data) {
        if (data == "-1") {
            /*初始化弹框样式*/
            $(".info_tip_content2").html("您已经收藏过该商品！");
            $(".info_tip_img2").attr("src", "../images/collect.png");
            $(".info_tip_cancel2").attr("href", "javascript:;").hide();
            $(".info_tip_submit2").attr("href", "javascript:;").show().html("确定").unbind("click");
            $(".info_tip_submit2").click(function () {
                cls();
            });
            dia(2);
        } else if (data == "-2") {
            /*初始化弹框样式*/
            $(".info_tip_content2").html("是否跳转到登陆页?");
            $(".info_tip_img2").attr("src", "../images/index_ques.png");
            $(".info_tip_cancel2").attr("href", "javascript:;").html("取消").show();
            $(".info_tip_submit2").attr("href", "../login.html").show().html("确定").unbind("click");
            $(".info_tip_cancel2").click(function () {
                cls();
            });
            dia(2);
        } else {
            if($(this).hasClass("liked")) {
                $(this).removeClass("liked");
            } else {
                $(this).addClass("liked");
            }
            /*初始化弹框样式*/
            $(".info_tip_content2").html("恭喜您收藏成功！");
            $(".info_tip_img2").attr("src", "../images/collect.png");
            $(".info_tip_cancel2").attr("href", "javascript:;").hide();
            $(".info_tip_submit2").attr("href", "javascript:;").show().html("确定").unbind("click");
            $(".info_tip_submit2").click(function () {
                cls();
            });
            dia(2);

        }
    });
}
function addShopGoodCart(obj){
    if($(obj).attr("product_stock")<=0){
        /*初始化弹框样式*/
        $(".info_tip_content2").html("库存不足！");
        $(".info_tip_img2").attr("src","../images/outstock.png");
        $(".info_tip_cancel2").attr("href","javascript:;").hide();
        $(".info_tip_submit2").attr("href","javascript:;").show().html("确定").unbind("click");
        $(".info_tip_submit2").click(function(){
            cls();
        });
        dia(2);
        return 0;
    }
    var num = 1;
    var productId = $(obj).attr("product_id");
    var params = "goodsNum="+num;
    params += "&productId="+productId;
    if($(obj).attr("distinct_id")>0){
        params += "&distinctId="+$(obj).attr("distinct_id");
    }
    /*请求加入购物车控制器*/
    $.post("../goods/addProductToShopCar.html?"+params,function(data){
        if(data){
            /*初始化弹框样式*/
            $(".info_tip_title").html("加入购物车");
            $(".info_tip_content").html("加入购物车成功！");
            $(".info_tip_img").attr("src","../images/cart_img.png");
            $(".info_tip_cancel").attr("href","javascript:;").html("继续购物").show();
            $(".info_tip_submit").attr("href","../myshoppingcart.html").html("立即结算").show().unbind("click");
            $(".info_tip_cancel").click(function(){
                cls();
            });
            dia(1);
        }else{
            /*初始化弹框样式*/
            $(".info_tip_title").html("加入购物车");
            $(".info_tip_content").html("加入购物车失败,请重试");
            $(".info_tip_img").attr("src","../images/error.png");
            $(".info_tip_cancel").attr("href","javascript:;").hide();
            $(".info_tip_submit").attr("href","javascript:;").html("确定").show().unbind("click");
            $(".info_tip_submit").click(function(){
                cls();
            });
            dia(1);
        }
    });
}
function changePagesFunction(obj){
    /*设置页码为点击的自定义属性pages,并提交查询表单*/
    if(obj!=null){
        $(".pageNo").val($(obj).attr("pages"));
    }
    $.post("../channel/channelViewByAjax.html", {
        goodsCateId:$("#goodsCateId").val(),
        sort:$(".list_sort").val(),
        showStock:$(".show_stock").val(),
        pageNo:$(".pageNo").val()
    }, function (msg){
            $("#floorGoods").html("");
            $("#shangPageBean").html("");
            $("#xiaPageBeans").html("");
            $("#goodsTipCheck").html("");
            $(".pageNo").val(msg.indexFloor.pageBean.pageNo);
            $(".list_sort").val(msg.searchBean.sort);
            $(".show_stock").val(msg.searchBean.showStock);
            //上面的分页
            var shangPageBean="";
            shangPageBean+="<span class='mr10'><b>"+msg.indexFloor.pageBean.pageNo+"</b>/"+msg.indexFloor.pageBean.lastPageNo+"</span>";
            if(msg.indexFloor.pageBean.pageNo==1){
                shangPageBean+="<a class='op_prev no_pages' href='javascript:;'>上一页</a>";
            }else{
                shangPageBean+="<a class='op_prev changePages' onclick='changePagesFunction(this)'  pages='"+msg.indexFloor.pageBean.prePageNo+"'  href='javascript:;'>上一页</a>";
            }
            if(msg.indexFloor.pageBean.lastPageNo>msg.indexFloor.pageBean.pageNo){
                shangPageBean+="<a class='op_next  changePages' onclick='changePagesFunction(this)'  pages='"+msg.indexFloor.pageBean.nextPageNo+"' href='javascript:;'>下一页</a>";
            }else{
                shangPageBean+="<a class='op_next no_pages' href='javascript:void(0);'>下一页</a>";
            }
            $("#shangPageBean").html(shangPageBean);
            //商品的显示
            var floorGoods="";
            for(var i=0;i<msg.indexFloor.pageBean.list.length;i++){
                if(msg.indexFloor.pageBean.list[i]!=null) {
                    floorGoods += "<div class='brandItem'><div class='b-img'><a href='";
                    if (msg.indexFloor.pageBean.list[i].goodsproductId > 0) {
                        floorGoods += "../item/" + msg.indexFloor.pageBean.list[i].goodsproductId + ".html'>";
                    } else {
                        floorGoods += "#'>";
                    }
                    floorGoods += "<img src='" + msg.indexFloor.pageBean.list[i].goodsproductImgsrc + "'/></a></div>";
                    floorGoods += "<div class='ml20 mr20'><div class='b-price mt10'><span>￥</span>" + msg.indexFloor.pageBean.list[i].goodsInfoPreferPrice + "</div><div class='b-name'><a href='";
                    if (msg.indexFloor.pageBean.list[i].goodsproductId > 0) {
                        floorGoods += "../item/" + msg.indexFloor.pageBean.list[i].goodsproductId + ".html'>" + msg.indexFloor.pageBean.list[i].goodsproductName + "</a></div>";
                    } else {
                        floorGoods += "#'>" + msg.indexFloor.pageBean.list[i].goodsproductName + "</a></div>";
                    }

                    floorGoods += "<div class='nw-btn clearfix mt10'><a href='javascript:void(0);' ><label class='n_check compare' onclick='compareGoodsList(this)'  id='compare" + msg.indexFloor.pageBean.list[i].goodsproductId + "'  product_id='" + msg.indexFloor.pageBean.list[i].goodsproductId + "'>对比</label></a>";
                    floorGoods += "<a href='javascript:;' class='attentionG' onclick='attentionGuanzhu(this)'  product_id='" + msg.indexFloor.pageBean.list[i].goodsproductId + "'><i></i>关注</a>";
                    if (msg.indexFloor.pageBean.list[i].isThird == 1) {
                        floorGoods += "<a class='add_shop_cart Joincart' onclick='addShopGoodCart(this)'  product_id='" + msg.indexFloor.pageBean.list[i].goodsproductId + "'  product_stock='" + msg.indexFloor.pageBean.list[i].goodsInfoStockThird + "'  distinct_id='" + msg.distinctId + "' href='javascript:;'><i></i>加入购物车</a>";
                    } else {
                        floorGoods += "<a class='add_shop_cart Joincart' onclick='addShopGoodCart(this)'  product_id='" + msg.indexFloor.pageBean.list[i].goodsproductId + "'  product_stock='" + msg.indexFloor.pageBean.list[i].goodsInfoStock + "'  distinct_id='" + msg.distinctId + "' href='javascript:;'><i></i>加入购物车</a>";
                    }
                    floorGoods += "</div></div></div>";
                }
            }
            $("#floorGoods").html(floorGoods);
            //下面的分页
            var xiaPageBeans="";
            var pageNo="";
            var endNo="";
            if((msg.indexFloor.pageBean.pageNo-2)>0){
                pageNo=msg.indexFloor.pageBean.pageNo-2;
            }else{
                pageNo=msg.indexFloor.pageBean.firstPageNo;
            }
            if((msg.indexFloor.pageBean.lastPageNo-1)>0){
                endNo=msg.indexFloor.pageBean.lastPageNo-2;
            }else{
                endNo="1";
            }
            if(msg.indexFloor.pageBean.pageNo==1){
                xiaPageBeans+="<a class='pg_prev no_pages' href='javascript:;'>上一页</a>";
            }else{
                xiaPageBeans+="<a class='pg_prev changePages' onclick='changePagesFunction(this)' pages='"+msg.indexFloor.pageBean.prePageNo+"'  href='javascript:;'>上一页</a>";
            }
            if(msg.indexFloor.pageBean.pageNo>3){
                xiaPageBeans+="<a class='changePages' onclick='changePagesFunction(this)' pages='1' href='javascript:;'>1</a>";
                if(msg.indexFloor.pageBean.pageNo>4){
                    xiaPageBeans+="...";
                }
            }
            for(var i=0;i<=(msg.indexFloor.pageBean.endNo-parseInt(pageNo));i++){
                var item=parseInt(pageNo)+i;
                if(i<=4){
                    if(item==msg.indexFloor.pageBean.pageNo){
                        xiaPageBeans+="<a class='cur' href='javascript:;'>"+item+"</a>";
                    }else{
                        xiaPageBeans+="<a class='changePages' onclick='changePagesFunction(this)' pages='"+item+"' href='javascript:;'>"+item+"</a>";
                    }
                }
            }
            if(msg.indexFloor.pageBean.pageNo!=msg.indexFloor.pageBean.lastPageNo){
                if((msg.indexFloor.pageBean.lastPageNo-msg.indexFloor.pageBean.pageNo)>3){
                    if(msg.indexFloor.pageBean.lastPageNo>5){
                        xiaPageBeans+="...";
                    }
                }
            }
            if(msg.indexFloor.pageBean.pageNo==msg.indexFloor.pageBean.lastPageNo || msg.indexFloor.pageBean.endNo<=1){
                if(msg.indexFloor.pageBean.lastPageNo>msg.indexFloor.pageBean.pageNo){
                    xiaPageBeans+="<a class='cur' href='javascript:;'>"+msg.indexFloor.pageBean.lastPageNo+"</a>";
                }
                xiaPageBeans+="<a class='pg_next no_pages' href='javascript:void(0);'>下一页</a>";
            }else{
                if((msg.indexFloor.pageBean.lastPageNo-msg.indexFloor.pageBean.pageNo)>=3){
                    if(msg.indexFloor.pageBean.lastPageNo>5){
                        xiaPageBeans+="<a class='pg_next changePages' onclick='changePagesFunction(this)' pages='"+msg.indexFloor.pageBean.lastPageNo+"' href='javascript:;'>"+msg.indexFloor.pageBean.lastPageNo+"</a>";
                    }
                }
                xiaPageBeans+="<a class='pg_next changePages' onclick='changePagesFunction(this)'  pages='"+msg.indexFloor.pageBean.nextPageNo+"'  href='javascript:void(0);'>下一页</a>";
            }
            $("#xiaPageBeans").html(xiaPageBeans);
            var goodsTipCheck="";
            if(msg.searchBean.showStock==1){
                goodsTipCheck="<label class='m_check mr20 checked' ><input class='vm mr5' id='check_show_stock' type='checkbox' onchange='goodsTipCheckFunction()'/>仅显示有货</label>";
            }else{
                goodsTipCheck="<label class='m_check mr20' ><input class='vm mr5' id='check_show_stock' type='checkbox' onchange='goodsTipCheckFunction()'/>仅显示有货</label>";
            }
            $("#goodsTipCheck").html(goodsTipCheck);
    });
}
function goodsTipCheckFunction(){
    if((numberchecked%2)==0){
        $(".show_stock").val("0");
        numberchecked++;
    }else{
        $(".show_stock").val("1");
        numberchecked++;
    }
    /*提交表单*/
    $(".pageNo").val("1");
    changePagesFunction();
}
$(function(){
    /*点击排序按钮操作*/
    $(".change_sort").click(function(){
        var sort = $(".list_sort");
        if(sort.val()==""){
            sort.val($(this).attr("val"));
        }else if($(this).attr("val")=="2D"){
            if(sort.val()=="2D"){
                sort.val("22D");
            }else{
                sort.val($(this).attr("val"));
            }
        }else if($(this).attr("val")=="22D"){
            if(sort.val()=="22D"){
                sort.val("2D");
            }else{
                sort.val($(this).attr("val"));
            }
        }else if($(this).attr("val")=="1D"){
            if(sort.val()=="1D"){
                sort.val("11D");
            }else{
                sort.val($(this).attr("val"));
            }
        }else if($(this).attr("val")=="11D"){
            if(sort.val()=="11D"){
                sort.val("1D");
            }else{
                sort.val($(this).attr("val"));
            }
        }else if($(this).attr("val")=="3D"){
            if(sort.val()=="3D"){
                sort.val("33D");
            }else{
                sort.val($(this).attr("val"));
            }
        }else if($(this).attr("val")=="33D"){
            if(sort.val()=="33D"){
                sort.val("3D");
            }else{
                sort.val($(this).attr("val"));
            }
        }else if($(this).attr("val")=="4D"){
            if(sort.val()=="4D"){
                sort.val("44D");
            }else{
                sort.val($(this).attr("val"));
            }
        }else if($(this).attr("val")=="44D"){
            if(sort.val()=="44D"){
                sort.val("4D");
            }else{
                sort.val($(this).attr("val"));
            }
        }
        var classthis=$(this);
        ///*提交表单*/
        $(".pageNo").val("1");
        //$("#searchForm").submit();
        $.post("../channel/channelViewByAjax.html", {
            goodsCateId:$("#goodsCateId").val(),
            sort:$(".list_sort").val(),
            showStock:$(".show_stock").val(),
            pageNo:$(".pageNo").val()
        }, function (msg){
                classthis.addClass("checked");
                if(msg.searchBean.sort=='22D' || msg.searchBean.sort=='11D' || msg.searchBean.sort=='44D' || msg.searchBean.sort=='33D'){
                    classthis.removeClass("s_down");
                    classthis.addClass("s_up");
                }
                if(msg.searchBean.sort=='2D' || msg.searchBean.sort=='1D' || msg.searchBean.sort=='4D' || msg.searchBean.sort=='3D'){
                    classthis.removeClass("s_up");
                    classthis.addClass("s_down");
                }
            $("#floorGoods").html("");
            $("#shangPageBean").html("");
            $("#xiaPageBeans").html("");
            $("#goodsTipCheck").html("");
            $(".pageNo").val(msg.indexFloor.pageBean.pageNo);
            $(".list_sort").val(msg.searchBean.sort);
            $(".show_stock").val(msg.searchBean.showStock);
            //上面的分页
            var shangPageBean="";
            shangPageBean+="<span class='mr10'><b>"+msg.indexFloor.pageBean.pageNo+"</b>/"+msg.indexFloor.pageBean.lastPageNo+"</span>";
            if(msg.indexFloor.pageBean.pageNo==1){
                shangPageBean+="<a class='op_prev no_pages' href='javascript:;'>上一页</a>";
            }else{
                shangPageBean+="<a class='op_prev changePages' onclick='changePagesFunction(this)'  pages='"+msg.indexFloor.pageBean.prePageNo+"'  href='javascript:;'>上一页</a>";
            }
            if(msg.indexFloor.pageBean.lastPageNo>msg.indexFloor.pageBean.pageNo){
                shangPageBean+="<a class='op_next  changePages' onclick='changePagesFunction(this)'  pages='"+msg.indexFloor.pageBean.nextPageNo+"' href='javascript:;'>下一页</a>";
            }else{
                shangPageBean+="<a class='op_next no_pages' href='javascript:void(0);'>下一页</a>";
            }
            $("#shangPageBean").html(shangPageBean);
            //商品的显示
            var floorGoods="";
            for(var i=0;i<msg.indexFloor.pageBean.list.length;i++){
                if(msg.indexFloor.pageBean.list[i]!=null) {
                    floorGoods += "<div class='brandItem'><div class='b-img'><a href='";
                    if (msg.indexFloor.pageBean.list[i].goodsproductId > 0) {
                        floorGoods += "../item/" + msg.indexFloor.pageBean.list[i].goodsproductId + ".html'>";
                    } else {
                        floorGoods += "#'>";
                    }
                    floorGoods += "<img src='" + msg.indexFloor.pageBean.list[i].goodsproductImgsrc + "'/></a></div>";
                    floorGoods += "<div class='ml20 mr20'><div class='b-price mt10'><span>￥</span>" + msg.indexFloor.pageBean.list[i].goodsInfoPreferPrice + "</div><div class='b-name'><a href='";
                    if (msg.indexFloor.pageBean.list[i].goodsproductId > 0) {
                        floorGoods += "../item/" + msg.indexFloor.pageBean.list[i].goodsproductId + ".html'>" + msg.indexFloor.pageBean.list[i].goodsproductName + "</a></div>";
                    } else {
                        floorGoods += "#'>" + msg.indexFloor.pageBean.list[i].goodsproductName + "</a></div>";
                    }

                    floorGoods += "<div class='nw-btn clearfix mt10'><a href='javascript:void(0);' ><label class='n_check compare' onclick='compareGoodsList(this)'  id='compare" + msg.indexFloor.pageBean.list[i].goodsproductId + "'  product_id='" + msg.indexFloor.pageBean.list[i].goodsproductId + "'>对比</label></a>";
                    floorGoods += "<a href='javascript:;' class='attentionG' onclick='attentionGuanzhu(this)'  product_id='" + msg.indexFloor.pageBean.list[i].goodsproductId + "'><i></i>关注</a>";
                    if (msg.indexFloor.pageBean.list[i].isThird == 1) {
                        floorGoods += "<a class='add_shop_cart Joincart' onclick='addShopGoodCart(this)'  product_id='" + msg.indexFloor.pageBean.list[i].goodsproductId + "'  product_stock='" + msg.indexFloor.pageBean.list[i].goodsInfoStockThird + "'  distinct_id='" + msg.distinctId + "' href='javascript:;'><i></i>加入购物车</a>";
                    } else {
                        floorGoods += "<a class='add_shop_cart Joincart' onclick='addShopGoodCart(this)'  product_id='" + msg.indexFloor.pageBean.list[i].goodsproductId + "'  product_stock='" + msg.indexFloor.pageBean.list[i].goodsInfoStock + "'  distinct_id='" + msg.distinctId + "' href='javascript:;'><i></i>加入购物车</a>";
                    }
                    floorGoods += "</div></div></div>";
                }
            }
            $("#floorGoods").html(floorGoods);
            //下面的分页
            var xiaPageBeans="";
            var pageNo="";
            var endNo="";
            if((msg.indexFloor.pageBean.pageNo-2)>0){
                pageNo=msg.indexFloor.pageBean.pageNo-2;
            }else{
                pageNo=msg.indexFloor.pageBean.firstPageNo;
            }
            if((msg.indexFloor.pageBean.lastPageNo-1)>0){
                endNo=msg.indexFloor.pageBean.lastPageNo-2;
            }else{
                endNo="1";
            }
            if(msg.indexFloor.pageBean.pageNo==1){
                xiaPageBeans+="<a class='pg_prev no_pages' href='javascript:;'>上一页</a>";
            }else{
                xiaPageBeans+="<a class='pg_prev changePages' onclick='changePagesFunction(this)' pages='"+msg.indexFloor.pageBean.prePageNo+"'  href='javascript:;'>上一页</a>";
            }
            if(msg.indexFloor.pageBean.pageNo>3){
                xiaPageBeans+="<a class='changePages' onclick='changePagesFunction(this)' pages='1' href='javascript:;'>1</a>";
                if(msg.indexFloor.pageBean.pageNo>4){
                    xiaPageBeans+="...";
                }
            }
            for(var i=0;i<=(msg.indexFloor.pageBean.endNo-parseInt(pageNo));i++){
                var item=parseInt(pageNo)+i;
                if(i<=4){
                    if(item==msg.indexFloor.pageBean.pageNo){
                        xiaPageBeans+="<a class='cur' href='javascript:;'>"+item+"</a>";
                    }else{
                        xiaPageBeans+="<a class='changePages' onclick='changePagesFunction(this)' pages='"+item+"' href='javascript:;'>"+item+"</a>";
                    }
                }
            }
            if(msg.indexFloor.pageBean.pageNo!=msg.indexFloor.pageBean.lastPageNo){
                if((msg.indexFloor.pageBean.lastPageNo-msg.indexFloor.pageBean.pageNo)>3){
                    if(msg.indexFloor.pageBean.lastPageNo>5){
                        xiaPageBeans+="...";
                    }
                }
            }
            if(msg.indexFloor.pageBean.pageNo==msg.indexFloor.pageBean.lastPageNo || msg.indexFloor.pageBean.endNo<=1){
                if(msg.indexFloor.pageBean.lastPageNo>msg.indexFloor.pageBean.pageNo){
                    xiaPageBeans+="<a class='cur' href='javascript:;'>"+msg.indexFloor.pageBean.lastPageNo+"</a>";
                }
                xiaPageBeans+="<a class='pg_next no_pages' href='javascript:void(0);'>下一页</a>";
            }else{
                if((msg.indexFloor.pageBean.lastPageNo-msg.indexFloor.pageBean.pageNo)>=3){
                    if(msg.indexFloor.pageBean.lastPageNo>5){
                        xiaPageBeans+="<a class='pg_next changePages' onclick='changePagesFunction(this)' pages='"+msg.indexFloor.pageBean.lastPageNo+"' href='javascript:;'>"+msg.indexFloor.pageBean.lastPageNo+"</a>";
                    }
                }
                xiaPageBeans+="<a class='pg_next changePages' onclick='changePagesFunction(this)'  pages='"+msg.indexFloor.pageBean.nextPageNo+"'  href='javascript:void(0);'>下一页</a>";
            }
            $("#xiaPageBeans").html(xiaPageBeans);
            var goodsTipCheck="";
            if(msg.searchBean.showStock==1){
                goodsTipCheck="<label class='m_check mr20 checked' ><input class='vm mr5' id='check_show_stock' type='checkbox' onchange='goodsTipCheckFunction()'/>仅显示有货</label>";
            }else{
                goodsTipCheck="<label class='m_check mr20' ><input class='vm mr5' id='check_show_stock' type='checkbox' onchange='goodsTipCheckFunction()'/>仅显示有货</label>";
            }
            $("#goodsTipCheck").html(goodsTipCheck);

        });
    });

    /*点击显示有货*/
    $("#check_show_stock").change(function(){
        //var cur = $(this);
        //if((cur.parent("label").hasClass("checked"))){
        //    cur.parent("label").removeClass("checked");
        //    $(".show_stock").val("0");
        //}else{
        //    cur.parent("label").addClass("checked");
        //    $(".show_stock").val("1");
        //}

        if((numberchecked%2)==0){
            $(".show_stock").val("0");
            numberchecked++;
        }else{
            $(".show_stock").val("1");
            numberchecked++;
        }
        /*提交表单*/
        $(".pageNo").val("1");
        changePagesFunction();
        //$("#searchForm").submit();
    });

    /*点击修改页码的时候触发*/
    $(".changePages").click(function(){
        /*设置页码为点击的自定义属性pages,并提交查询表单*/
        $(".pageNo").val($(this).attr("pages"));
        $.post("../channel/channelViewByAjax.html", {
            goodsCateId:$("#goodsCateId").val(),
            sort:$(".list_sort").val(),
            showStock:$(".show_stock").val(),
            pageNo:$(".pageNo").val()
        }, function (msg){
                $("#floorGoods").html("");
                $("#shangPageBean").html("");
                $("#xiaPageBeans").html("");
                $("#goodsTipCheck").html("");
                $(".pageNo").val(msg.indexFloor.pageBean.pageNo);
                $(".list_sort").val(msg.searchBean.sort);
                $(".show_stock").val(msg.searchBean.showStock);
                //上面的分页
                var shangPageBean="";
                shangPageBean+="<span class='mr10'><b>"+msg.indexFloor.pageBean.pageNo+"</b>/"+msg.indexFloor.pageBean.lastPageNo+"</span>";
                if(msg.indexFloor.pageBean.pageNo==1){
                    shangPageBean+="<a class='op_prev no_pages' href='javascript:;'>上一页</a>";
                }else{
                    shangPageBean+="<a class='op_prev changePages' onclick='changePagesFunction(this)'  pages='"+msg.indexFloor.pageBean.prePageNo+"'  href='javascript:;'>上一页</a>";
                }
                if(msg.indexFloor.pageBean.lastPageNo>msg.indexFloor.pageBean.pageNo){
                    shangPageBean+="<a class='op_next  changePages' onclick='changePagesFunction(this)'  pages='"+msg.indexFloor.pageBean.nextPageNo+"' href='javascript:;'>下一页</a>";
                }else{
                    shangPageBean+="<a class='op_next no_pages' href='javascript:void(0);'>下一页</a>";
                }
                $("#shangPageBean").html(shangPageBean);
                //商品的显示
                var floorGoods="";
                for(var i=0;i<msg.indexFloor.pageBean.list.length;i++){
                    if(msg.indexFloor.pageBean.list[i]!=null) {
                        floorGoods += "<div class='brandItem'><div class='b-img'><a href='";
                        if (msg.indexFloor.pageBean.list[i].goodsproductId > 0) {
                            floorGoods += "../item/" + msg.indexFloor.pageBean.list[i].goodsproductId + ".html'>";
                        } else {
                            floorGoods += "#'>";
                        }
                        floorGoods += "<img src='" + msg.indexFloor.pageBean.list[i].goodsproductImgsrc + "'/></a></div>";
                        floorGoods += "<div class='ml20 mr20'><div class='b-price mt10'><span>￥</span>" + msg.indexFloor.pageBean.list[i].goodsInfoPreferPrice + "</div><div class='b-name'><a href='";
                        if (msg.indexFloor.pageBean.list[i].goodsproductId > 0) {
                            floorGoods += "../item/" + msg.indexFloor.pageBean.list[i].goodsproductId + ".html'>" + msg.indexFloor.pageBean.list[i].goodsproductName + "</a></div>";
                        } else {
                            floorGoods += "#'>" + msg.indexFloor.pageBean.list[i].goodsproductName + "</a></div>";
                        }

                        floorGoods += "<div class='nw-btn clearfix mt10'><a href='javascript:void(0);' ><label class='n_check compare' onclick='compareGoodsList(this)'  id='compare" + msg.indexFloor.pageBean.list[i].goodsproductId + "'  product_id='" + msg.indexFloor.pageBean.list[i].goodsproductId + "'>对比</label></a>";
                        floorGoods += "<a href='javascript:;' class='attentionG' onclick='attentionGuanzhu(this)'  product_id='" + msg.indexFloor.pageBean.list[i].goodsproductId + "'><i></i>关注</a>";
                        if (msg.indexFloor.pageBean.list[i].isThird == 1) {
                            floorGoods += "<a class='add_shop_cart Joincart' onclick='addShopGoodCart(this)'  product_id='" + msg.indexFloor.pageBean.list[i].goodsproductId + "'  product_stock='" + msg.indexFloor.pageBean.list[i].goodsInfoStockThird + "'  distinct_id='" + msg.distinctId + "' href='javascript:;'><i></i>加入购物车</a>";
                        } else {
                            floorGoods += "<a class='add_shop_cart Joincart' onclick='addShopGoodCart(this)'  product_id='" + msg.indexFloor.pageBean.list[i].goodsproductId + "'  product_stock='" + msg.indexFloor.pageBean.list[i].goodsInfoStock + "'  distinct_id='" + msg.distinctId + "' href='javascript:;'><i></i>加入购物车</a>";
                        }
                        floorGoods += "</div></div></div>";
                    }
                }
                $("#floorGoods").html(floorGoods);
                //下面的分页
                var xiaPageBeans="";
                var pageNo="";
                var endNo="";
                if((msg.indexFloor.pageBean.pageNo-2)>0){
                    pageNo=msg.indexFloor.pageBean.pageNo-2;
                }else{
                    pageNo=msg.indexFloor.pageBean.firstPageNo;
                }
                if((msg.indexFloor.pageBean.lastPageNo-1)>0){
                    endNo=msg.indexFloor.pageBean.lastPageNo-2;
                }else{
                    endNo="1";
                }
                if(msg.indexFloor.pageBean.pageNo==1){
                    xiaPageBeans+="<a class='pg_prev no_pages' href='javascript:;'>上一页</a>";
                }else{
                    xiaPageBeans+="<a class='pg_prev changePages' onclick='changePagesFunction(this)' pages='"+msg.indexFloor.pageBean.prePageNo+"'  href='javascript:;'>上一页</a>";
                }
                if(msg.indexFloor.pageBean.pageNo>3){
                    xiaPageBeans+="<a class='changePages' onclick='changePagesFunction(this)' pages='1' href='javascript:;'>1</a>";
                    if(msg.indexFloor.pageBean.pageNo>4){
                        xiaPageBeans+="...";
                    }
                }
                for(var i=0;i<=(msg.indexFloor.pageBean.endNo-parseInt(pageNo));i++){
                    var item=parseInt(pageNo)+i;
                    if(i<=4){
                        if(item==msg.indexFloor.pageBean.pageNo){
                            xiaPageBeans+="<a class='cur' href='javascript:;'>"+item+"</a>";
                        }else{
                            xiaPageBeans+="<a class='changePages' onclick='changePagesFunction(this)' pages='"+item+"' href='javascript:;'>"+item+"</a>";
                        }
                    }
                }
                if(msg.indexFloor.pageBean.pageNo!=msg.indexFloor.pageBean.lastPageNo){
                   if((msg.indexFloor.pageBean.lastPageNo-msg.indexFloor.pageBean.pageNo)>3){
                       if(msg.indexFloor.pageBean.lastPageNo>5){
                           xiaPageBeans+="...";
                       }
                   }
                }
                if(msg.indexFloor.pageBean.pageNo==msg.indexFloor.pageBean.lastPageNo || msg.indexFloor.pageBean.endNo<=1){
                    if(msg.indexFloor.pageBean.lastPageNo>msg.indexFloor.pageBean.pageNo){
                        xiaPageBeans+="<a class='cur' href='javascript:;'>"+msg.indexFloor.pageBean.lastPageNo+"</a>";
                    }
                    xiaPageBeans+="<a class='pg_next no_pages' href='javascript:void(0);'>下一页</a>";
                }else{
                    if((msg.indexFloor.pageBean.lastPageNo-msg.indexFloor.pageBean.pageNo)>=3){
                        if(msg.indexFloor.pageBean.lastPageNo>5){
                            xiaPageBeans+="<a class='pg_next changePages' onclick='changePagesFunction(this)' pages='"+msg.indexFloor.pageBean.lastPageNo+"' href='javascript:;'>"+msg.indexFloor.pageBean.lastPageNo+"</a>";
                        }
                    }
                    xiaPageBeans+="<a class='pg_next changePages' onclick='changePagesFunction(this)'  pages='"+msg.indexFloor.pageBean.nextPageNo+"'  href='javascript:void(0);'>下一页</a>";
                }
                $("#xiaPageBeans").html(xiaPageBeans);
                var goodsTipCheck="";
                if(msg.searchBean.showStock==1){
                    goodsTipCheck="<label class='m_check mr20 checked'><input class='vm mr5' id='check_show_stock' type='checkbox'/>仅显示有货</label>";
                }else{
                    goodsTipCheck="<label class='m_check mr20'><input class='vm mr5' id='check_show_stock' type='checkbox'/>仅显示有货</label>";
                }
                $("#goodsTipCheck").html(goodsTipCheck);

        });
        //$("#searchForm").submit();
    });

    /*点击收藏商品事件*/
    $(".attentionG").click(function() {

        var districtId = $("#disId").val();
        var goodsprice = $("#followPrice").val();
        $.post("../atte/" + $(this).attr("product_id") + ".html", {
            districtId: districtId,
            goodsprice: goodsprice
        }, function (data) {
            if (data == "-1") {
                /*初始化弹框样式*/
                $(".info_tip_content2").html("您已经收藏过该商品！");
                $(".info_tip_img2").attr("src", "../images/collect.png");
                $(".info_tip_cancel2").attr("href", "javascript:;").hide();
                $(".info_tip_submit2").attr("href", "javascript:;").show().html("确定").unbind("click");
                $(".info_tip_submit2").click(function () {
                    cls();
                });
                dia(2);
            } else if (data == "-2") {
                /*初始化弹框样式*/
                $(".info_tip_content2").html("是否跳转到登陆页?");
                $(".info_tip_img2").attr("src", "../images/index_ques.png");
                $(".info_tip_cancel2").attr("href", "javascript:;").html("取消").show();
                $(".info_tip_submit2").attr("href", "../login.html").show().html("确定").unbind("click");
                $(".info_tip_cancel2").click(function () {
                    cls();
                });
                dia(2);
            } else {
                if($(this).hasClass("liked")) {
                    $(this).removeClass("liked");
                } else {
                    $(this).addClass("liked");
                }
                /*初始化弹框样式*/
                $(".info_tip_content2").html("恭喜您收藏成功！");
                $(".info_tip_img2").attr("src", "../images/collect.png");
                $(".info_tip_cancel2").attr("href", "javascript:;").hide();
                $(".info_tip_submit2").attr("href", "javascript:;").show().html("确定").unbind("click");
                $(".info_tip_submit2").click(function () {
                    cls();
                });
                dia(2);

            }
        });
    });
    /*加入购物车绑定事件*/
    $(".add_shop_cart").click(function() {
        if ($(this).attr("product_stock") <= 0) {
            /*初始化弹框样式*/
            $(".info_tip_content2").html("库存不足！");
            $(".info_tip_img2").attr("src", "../images/outstock.png");
            $(".info_tip_cancel2").attr("href", "javascript:;").hide();
            $(".info_tip_submit2").attr("href", "javascript:;").show().html("确定").unbind("click");
            $(".info_tip_submit2").click(function () {
                cls();
            });
            dia(2);
            return 0;
        }
        var num = 1;
        var productId = $(this).attr("product_id");
        var params = "goodsNum=" + num;
        params += "&productId=" + productId;
        if ($(this).attr("distinct_id") > 0) {
            params += "&distinctId=" + $(this).attr("distinct_id");
        }
        /*请求加入购物车控制器*/
        $.post("../goods/addProductToShopCar.html?" + params, function (data) {
            if (data) {
                /*初始化弹框样式*/
                $(".info_tip_title").html("加入购物车");
                $(".info_tip_content").html("加入购物车成功！");
                $(".info_tip_img").attr("src", "../images/cart_img.png");
                $(".info_tip_cancel").attr("href", "javascript:;").html("继续购物").show();
                $(".info_tip_submit").attr("href", "../myshoppingcart.html").html("立即结算").show().unbind("click");
                $(".info_tip_cancel").click(function () {
                    cls();
                });
                dia(1);
                $(".cartNum").text(parseInt($(".cartNum").text())+1);
            } else {
                /*初始化弹框样式*/
                $(".info_tip_title").html("加入购物车");
                $(".info_tip_content").html("加入购物车失败,请重试");
                $(".info_tip_img").attr("src", "../images/error.png");
                $(".info_tip_cancel").attr("href", "javascript:;").hide();
                $(".info_tip_submit").attr("href", "javascript:;").html("确定").show().unbind("click");
                $(".info_tip_submit").click(function () {
                    cls();
                });
                dia(1);
            }
        });
    });
});
