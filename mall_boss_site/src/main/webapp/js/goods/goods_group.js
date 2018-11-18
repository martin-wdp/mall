$(function() {
    $("#saveGroup").validate();
    $("#editGroup").validate();
});
/*进行特惠价格的隐藏*/
function changGroupType(type){
    if(type==1){
        $(".group_preferamountType").hide();
        $("#group_preferamount").removeClass("required number");
    }else{
        $("#group_preferamount").addClass("required number");
        $(".group_preferamountType").show();
    }
}

/*修改商品品牌*/
function updateGroup(groupId) {
    /*ajax 通过ID查询并塞值到修改页面*/
    $.get("queryGoodsGroupById.htm?CSRFToken="+$("#CSRFToken").val()+"&groupId=" + groupId, function (data) {
        $("#update_group_name").val(data.groupName);
        $("#update_groupId").val(data.groupId);
        $("#update_group_preferamount").val(data.groupPreferamount);
        if (data.groupPrefertype == 0) {
            $("#prefertype0").click();
            $("#update_group_preferamount").addClass("required number");
        } else {
            $("#prefertype1").click();
            $("#update_group_preferamount").removeClass("required number");
        }
        $( "#editCombination" ).modal("show");
    });
}