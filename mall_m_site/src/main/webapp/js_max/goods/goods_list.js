/*点击修改页码的时候触发*/
$(".changePages").click(function ()
{
    /*设置页码为点击的自定义属性pages,并提交查询表单*/
    $(".pageNo").val($(this).attr("pages"));
    $("#searchForm").submit();
});


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
	}else if($(this).attr("val")=="5D"){
		sort.val("5D");
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
	/*提交表单*/
	$(".pageNo").val("1");
	$("#searchForm").submit();
});