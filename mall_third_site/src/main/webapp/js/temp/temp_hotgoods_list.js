/**
 * 热销商品列表js
 * Created by NingPai-liangck on 2015/5/25.
 */

/**
 * 异步查询加载商品列表
 * @param pageNo 当前页码
 * @param pageSize 每页条数
 */
function addProduct(pageNo,pageSize){
    var brandId=$("#brandId").val();
    var goodsInfoItemNo =$("#goodsInfoItemNo").val();
    $.get("queryProductForCoupon.htm?groupId=&pageNo="+pageNo+"&pageSize="+pageSize+"&brandIds="+brandId+"&productNo="+goodsInfoItemNo,function(data){
        var list=data.list;
        if(list!=null&&list.length>0){
            $("#goods_list").html("");
            var goods="";

            for(var i=0;i<list.length;i++){
                var g=list[i];
                goods+="<tr>";
                goods+=" <td><input type='checkbox' class='productId' name='productId' onclick='addpro(this);' value='"+ g.goodsInfoId+"'/></td>";
                goods+="<td>"+(i+1)+"</td>";
                goods+="<td class='goodsInfoItemNo'>"+ g.goodsInfoItemNo+"</td>";
                goods+="<td class='goodsInfoName'>" + list[i].goodsInfoName +"</td>";
                goods+="<td><img class='goodsInfoImgId' alt='' src='"+ g.goodsInfoImgId+"' width='120' height='60'></td>";
                goods+="<td class='goodsInfoPreferPrice'>"+ g.goodsInfoPreferPrice+"</td>";
                goods+="</tr>";
            }
            $("#goods_list").html(goods);
            /*脚部分页*/
            $("#goodspages").html("");
            var foot="<div class='ops-right'><nav><ul class='pagination'><li ";
            if(data.pageNo<=1){
                foot+="class='disabled'";
            }
            foot+="><a ";
            if(data.pageNo>1){
                foot+="onclick='addproduct("+data.prePageNo+"," + data.pageSize + ")'";
            }
            foot+=" href='javascript:;' aria-label='Previous' data-role='"+data.prePageNo+"'";
            foot+="<span aria-hidden='true'>&laquo;</span></a></li>";
            if(data.startNo>1){
                foot+="<li><a href='javascript:void(0);' onclick='addproduct(1," + data.pageSize + ")' data-role='1'>1</a></li>...";
            }
            for(var i=data.startNo;i<=data.endNo;i++){
                foot+="<li ";
                if(data.pageNo==i){
                    foot+="class='active'";
                }
                foot+="><a href='javascript:;'";
                if(i!=data.pageNo){
                    foot+="onclick='addproduct(" + i + "," + (data.pageSize) + ")'";
                }
                foot+=">"+i+"</a></li>";
            }
            if(data.totalPages>data.endNo){
                foot+="...<li><a href='javascript:void(0);' onclick='addproduct("+data.totalPages+","+data.pageSize+");' data-role='"+data.totalPages+"'>"+data.totalPages+"</a></li>";
            }
            foot+="<li ";
            if(data.pageNo >= data.endNo){
                foot+="class='disabled'";
            }
            foot+="><a ";
            if(data.pageNo<data.endNo){
                foot+="onclick='addproduct("+data.nextPageNo+","+data.pageSize+");'";
            }
            foot+=" data-role='"+data.nextPageNo+"' href='javascript:;' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>";
            foot+="</ul></nav></div>";
            $("#goodspages").html(foot);
        }else{
            $("#goods_list").html("");
            $("#goodspages").html("<center>暂无数据</center>");
        }

    },"json");

}

/**
 * 选中货品，填充楼层商品弹出框
 * @param obj
 */
function addpro(obj){
    if(obj.checked==true){
        var productId=$(obj).parent().parent().find(".productId").val();
        var goodsInfoItemNo=$(obj).parent().parent().find(".goodsInfoItemNo").html();
        var goodsInfoName=$(obj).parent().parent().find(".goodsInfoName").html();
        var goodsInfoImgId=$(obj).parent().parent().find(".goodsInfoImgId").attr("src");
        var goodsInfoPreferPrice=$(obj).parent().parent().find(".goodsInfoPreferPrice").html();

        $("#up_goodsproductId").val(productId);
        $("#up_goodsproductNo").val(goodsInfoItemNo);
        $("#up_goodsproductName").val(goodsInfoName);
        $("#up_goodsproductImgsrc").val(goodsInfoImgId);
        $("#image").attr("src",goodsInfoImgId).attr("style","display: block");
        $("#up_goodsproductPrice").val(goodsInfoPreferPrice);

        /*隐藏货品列表*/
        $(".choose-goods").hide();
        $(".chooseGoods").text("选择货品");
    }
}

/*验证表单*/
function validateForm(){
    var flag = true;
    //验证是否选中商品
    if($("#up_goodsproductId").val()==""){
        $("#up_goodsproductNo").next().html("请选择货品！");
        flag = false && flag;
    }else{
        $("#up_goodsproductNo").next().html("");
        flag = true && flag;
    }

    //验证排序
    if($("#up_sort").val()==""){
        $("#up_sort").next().html("请填写排序！");
        flag = false && flag;
    }else{
        flag = checkNumAndDialog("up_sort") && flag;
    }
    return flag;
}

/**
 * 根據樓層商品id獲取商品信息填充表單內容
 * @param storeygoodsid
 */
function fillFormData(storeygoodsid){
    $.get("getHotGoodsById.htm",{hotGoodsId:storeygoodsid},function(data){
        if(data!=null){
            $("#up_barId").val(data.channelStoreyGoodsproductId);
            $("#up_goodsproductId").val(data.goodsproductId);
            $("#up_goodsproductNo").val(data.goodsproductNo);
            $("#up_goodsproductName").val(data.goodsproductName);
            $("#image").attr("src",data.goodsproductImgsrc);
            $("#up_goodsproductImgsrc").val(data.goodsproductImgsrc);
            $("#up_goodsproductPrice").val(data.goodsproductPrice);
            $("#up_sort").val(data.sort);
        }
    },"json");
}

/*重置表单*/
function resetForm(){
    document.getElementById("hotgoodsInfoForm").reset();
    $("#up_barId").val("");
    $("#up_goodsproductId").val("");
    $("#up_goodsproductNo").next().html("");
    $("#up_sort").next().html("");
}

function resetModal(){
    $(".choose-goods").hide();
    $(this).text("选择货品");
}

$(function(){

    /*選擇商品按钮,加载新增楼层商品时的商品列表*/
    $(".chooseGoods").click(function(){
        if($(".choose-goods").is(":hidden")){
            addProduct(1,5);
            $(".choose-goods").show();
            $(this).text("关闭");
        }else{
            $(".choose-goods").hide();
            $(this).text("选择货品");
        }
    });

    /*添加按钮*/
    $(".create-hotgoods-btn").click(function(){
        /*重置表單*/
        resetForm();
        /*设置弹出框标题*/
        $(".hotgoods-info-title").html("添加货品");
        /*设置表单action*/
        $("#hotgoodsInfoForm").attr("action","createthirdhotgoods.htm");
    });

    /*修改按钮*/
    $(".modify-hotgoods-btn").click(function(){
        /*重置表单*/
        resetForm();
        /*填充表單數據*/
        fillFormData($(this).attr("data-key"));
        /*設置彈出框標題*/
        $(".hotgoods-info-title").html("修改货品");
        /*設置表單action*/
        $("#hotgoodsInfoForm").attr("action","updatethirdhotgoods.htm");
        $("#addpro").modal('show');
    });

    /*保存按钮*/
    $("#save").click(function(){
        if(validateForm()){
            $("#hotgoodsInfoForm").submit();
        }
    });

    /*刪除按鈕*/
    $(".delete-btn").click(function(){
        /*设置确定按钮的样式，用于事件控制*/
        $("#tip-submit-btn").removeClass("muilty-delete").addClass("single-delete");
        $("#deleteKey").val($(this).attr("data-key"));
        $("#delete-tip").modal('show');
    });
    /*单个删除*/
    $("div.modal-footer").on("click","button.single-delete",function(){
        $("#singleDeleteForm").attr("action",$("#basePath").val()+"/deletethirdhotgoods.htm").submit();
    });

    /*批量删除按钮*/
    $("#muilty-delete-btn").click(function(){
        if(checkSelect("storeyGoodsIds")){//检查是否选中了行记录
            /*设置确定按钮的样式，用于事件控制*/
            $("#tip-submit-btn").removeClass("single-delete").addClass("muilty-delete");
            $("#delete-tip").modal('show');
        }else{//如果未选中,弹出提示框
            $("#select-tip").modal('show');
        }
    });
    /*批量删除*/
    $("div.modal-footer").on("click","button.muilty-delete",function(){
        $("#muilt-delete-form").attr("action",$("#basePath").val()+"/deletethirdhotgoods.htm").submit();
    });
});
