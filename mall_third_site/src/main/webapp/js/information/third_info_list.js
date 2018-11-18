/**
 * 文章列表js
 * Created by liangck on 15/5/29.
 */

/*分页*/
function changePageNo(obj){
    $("#pageNo").val($(obj).attr("data-role")).submit();
    $("#infoForm").submit();
}

//添加按钮
function createInfo(){
    var a;
    $.ajax({
        url:"getThirdInfoTypeCount.htm",
        success:function(data){
            a = data;
        },
        async: false
    });
    if(a>0){
        location.href="showThirdInformation.htm";
    }else{
        $(".error-tip-title").text("还没有栏目，请先添加栏目！");
        $("#error-tip").modal('show');
    }
}

//修改按钮
function updateInfo(data){
    location.href="showThirdInformation.htm?infoId="+data;
}

/*单个删除*/
function delInfo(data)
{
//	var infoIds = new Array();
//	infoIds.push(data);
    $("#delete-from").attr("action","delThirdInformation.htm?infoIds=" + data);
    $("#delete-tip").modal('show');
}

/*批量删除*/
function batchDelInfo(obj)
{
    //判断是否有按钮选中
    var bool = false;
    var brandIds = document.getElementsByName(obj);
    var checkedBrand = new Array();
    for (var i = 0; i < brandIds.length; i++) {
        var e = brandIds[i];
        if (e.checked == true) {
            bool = true;
            checkedBrand.push(e.value);
        }
    }
    if (bool == false)
    {
        //选择数据
        $(".error-tip-title").text("请至少选择一行数据！");
        $("#error-tip").modal('show');
    }
    else
    {
        $("#delete-from").attr("action","delThirdInformation.htm?infoIds=" + checkedBrand);
        $("#delete-tip").modal('show');    }
}

$(function(){
    /*确认删除*/
    $("#tip-submit-btn").click(function(){
            $("#delete-from").submit()

        }
    );
    /*查询*/
    $(".submit-search-btn").click(function(){
        $("#infoForm").submit();
    });

    /*重置表单*/
    $(".reset-search-btn").click(function(){
        $("#typeId").val("");
        $("#infoName").val("");
    });
});