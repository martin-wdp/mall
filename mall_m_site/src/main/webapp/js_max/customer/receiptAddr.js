//删除收货地址
$(".remove").bind("click",function(){
    var addressId = $(this).parent().parent().find('input[type="hidden"]').val();
    var url = $('input[id="basePath"]').val()+"/delAddressAJAX.htm";
    var flag = false ;
    $.ajax({
        url:url,
        async:false,
        dataType:"json",
        data:{addressId:addressId},
        success:function(data){
            flag = true ;
        },
        error:function(){
        }
    });
    if(flag){
        $(this).parents(".inpbox").remove();
    }
});

//跳转添加新地址
$('button[id="addNewAddress"]').bind("click",function(){
    window.location.href = $('input[id="basePath"]').val()+"/customer/toAddNewAddress.htm";
});


$(".checkAddrBox").bind("click",function(){
    var addressId = $(this).parent().parent(".inpbox").find('input[type="hidden"]').val();
    var url = $('input[id="basePath"]').val()+"/customer/uodateAddressNewM.htm";
    $.ajax({
        url:url,
        async:false,
        dataType:"json",
        data:{addressId:addressId,checkbox:"1"},
        success:function(data){
        },
        error:function(){
        }
    });
});

$(".edit").bind("click",function(){
    window.location.href = $('input[id="basePath"]').val()+"/customer/toUpdateAddrNewM.htm?addressId="+$(this).attr("addressId");
});