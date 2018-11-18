

/*批量删除*/
function batchDelete(){
//	var chks=$("input[name='importCheck']");
//	for(var i=0;i<chks.length;i++){
//		alert(chks[i].checked);
//	}
	$(".dialogMsg").text("确认删除？");
	$(".dialogOK").attr("onclick","doBatchDelete()");
    dia(1);
}

/*下载模板*/
function downloadTemplate(){
	$(".downImportExcel").submit();
}

/*商品导入*/
function goodsImport(){
	$( ".importGoods").click();
	$(".dialogMsg").text("确认导入？");
	$(".dialogOK").attr("onclick","doGoodsImport()");
    dia(1);
}

function doBatchDelete(){
	$("#batchdeleteform").submit();
}

function doGoodsImport(){
	//alert($(".importGoods")[0].value);
	$(".goodsImport").submit();
}