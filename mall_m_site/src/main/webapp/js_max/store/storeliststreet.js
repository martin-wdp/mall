$(function(){
    FastClick.attach(document.body);
    $("#keleyi-menu").keleyi({
        width: '100%',
        item_background_color: '#FAFAFA',
        item_background_color_hover: '#FAFAFA',
        item_margin: '0',
        bar_background_color: '#FAFAFA'
    });
});
/*根据一级商品分类ID获取 包含此分类的店铺*/
function selectstorebycateid(cateId){
    location.href=$('#basePath').val()+"/storeliststreet.htm?cateId="+cateId;
}

/*关注商店*/
function addcollectionsellerStore(id){

    $.ajax({
        type: 'post',
        url:$('#basePath').val()+'/addcollectionsellerStore.htm?storeId='+id,
        async:false,
        success: function(data) {
            if(data.status==2){
                location.href = $('#basePath').val()+'/login.html';
            }else if(data.status==1){
                $('#collection_'+id).html('<i  class="iconfont">&#xe661;</i>取消关注');
            }else if(data.status==0){
                $('#collection_'+id).html('<i  class="iconfont">&#xe661;</i>关注此店');
            }

        }
    });
}

/*点击修改页码的时候触发*/
$(".changePages").click(function (){
    /*设置页码为点击的自定义属性pages,并提交查询表单*/
    $(".pageNo").val($(this).attr("pages"));
    $("#searchForm").submit();
});

/*去店铺最新上架的商品列表*/
function storenewproduct(storeId){
    location.href = $('#basePath').val()+'/storenewproduct.htm?storeId='+storeId;
}

