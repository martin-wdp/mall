$(function() {
    /* 为选定的select下拉菜单开启搜索提示 */
    $('select[data-live-search="true"]').select2();
    /* 为选定的select下拉菜单开启搜索提示 END */
    $("#editExpressConfForm").validate();
    $("#addExpressConfForm").validate();
})

/**
 * 搜索上门自提
 */
function searchExpressConf() {
    var price = $("#searchPrice").val();
    if(price=='') {
        $("#searchPrice").val(0);
    }else if(!/^[0-9]+\.{0,1}[0-9]{0,2}$/.test(price)) {
        showTipAlert("运费必须为整数或小数！");
        return;
    }
    $("#searchForm").submit();
}


/**
 * 添加配送方式
 */
function addExpressConf() {
    $("#addExpressConfForm").submit();
}
/**
 * 弹框显示修改配送方式
 * @param expressId
 */
function showEditExpressConfForm(expressId) {
    $("#expressId").val(expressId);
    $.ajax({
       url:'selectExpressConfById.htm?CSRFToken='+$("#CSRFToken").val()+"&expressId="+expressId,
       success:function(data) {
            $("#expressNameEdit").val(data.name);
           $("#expressPriceEdit").val(data.price);
           $("#desEdit").val(data.des);
           if(data.pickupFlag==1) {
               $("#open1").click();
           } else {
               $("#open2").click();
           }
           if(data.usedStatus==1) {
               $("#open3").click();
           } else {
               $("#open4").click();
           }

           $.ajax({
               url:'selectAllLogistics.htm',
               success:function(result) {
                   var expressOptionHtml = '';
                   var sendExpressOptionHtml = '';
                   for(var i=0;i<result.length;i++) {
                       if(result[i].logComId==data.express) {
                           expressOptionHtml += '<option value="'+result[i].logComId+'" selected>'+result[i].name+'</option>';
                       } else {
                           expressOptionHtml += '<option value="'+result[i].logComId+'">'+result[i].name+'</option>';
                       }
                   }

                   for(var i=0;i<result.length;i++) {
                       if(result[i].logComId==data.sendExpress) {
                           sendExpressOptionHtml += '<option value="'+result[i].logComId+'" selected>'+result[i].name+'</option>';
                       } else {
                           sendExpressOptionHtml += '<option value="'+result[i].logComId+'">'+result[i].name+'</option>';
                       }
                   }
                   $("#expressEdit").html(expressOptionHtml);
                   $("#sendExpressEdit").html(sendExpressOptionHtml);

                   $('select[data-live-search="true"]').select2();
               }
           });
       }
    });
    $('#editShip').modal('show');
}

/**
 * 添加配送方式
 */
function updateExpressConf() {
    $("#editExpressConfForm").submit();
}