/*
 *资讯模块通用JS 
 * author WangHaiYang
*/
/*资讯筛选点击展示方式等*/
function changeSearch(obj, objName, valu)
{
    //如果点击的对象已经是当前选中的 就不进行操作
//    if ($(obj).hasClass("current")) {
//        return;
//    }
//    if (valu == "-1")
//    {
//        if (searchCheckShowStock.checked) {
//            $("#" + objName).val(1);
//        }
//        else {
//            $("#" + objName).val(0);
//        }
//        $("#pageBeanShowPage").val(1);
//        
//    }else if(valu == "-11"){
//    	$("#" + objName).val($(obj).val());
//    	
//    	if($("#pageBeanShowPage").val()>1 && $("#" + objName).val()>$("#rows").val()){
//    		$("#pageBeanShowPage").val(1);
//    	}
//    }
//    else {
//        $("#" + objName).val(valu);
//    }
//    //获取到参数列表中所有的选中的节点，放在隐藏域中，提交查询表单
//    var params = $(".current");
//    for (var i = 0; i < params.length; i++)
//    {
//        if ($(params[i]).hasClass("param"))
//        {
//            $("#searchForm").append("<input type='hidden' name=params value='" + $(params[i]).attr("id") + "' />");
//        }
//    }
	$("#pageBeanShowPage").val(valu);
    $("#searchForm").submit();
}
$(function(){
	$(".acSide dl dt").each(function(){
		var cur = $(this);
		cur.find("a").click(function(){
			cur.next("dd").slideToggle("fast");
			if(cur.find("a").hasClass("un")){
				cur.find("a").removeClass("un");
			}else{
				cur.find("a").addClass("un");
			}
		});
	});
});
