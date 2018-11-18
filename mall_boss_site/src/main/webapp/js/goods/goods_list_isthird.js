/*提交高级查询的表单*/
function subForm(formName, show)
{
	$("#searchPageSize").remove();
	$("#searchPageNo").remove();
	$("#showFlag").val(show);
	$("#" + formName + "").submit();
}

function subFormG(formName, show)
{
	$("#searchPageSizeG").remove();
	$("#searchPageNoG").remove();
	$("#showFlagG").val(show);
	$("#" + formName + "").submit();
}

/*点击生成索引的时候 */
function createIndex(){
	$.get("createIndex.htm?CSRFToken="+$(".token_val").val(),function(data){
		if (data)
		{
			showTipAlert("索引生成成功!");
		}else {
			showTipAlert("索引生成失败!");
		}
	});
}

/*点击导出按钮的时候*/
function exportList(){
	$("#adv_form").attr("action","exportGoodsList.htm?CSRFToken="+$(".token_val").val());
	$("#adv_form").submit();
	//$("#adv_form").attr("action","queryByParamisthird.htm?CSRFToken="+$(".token_val").val());
}

/*点击导出当前页的时候 */
function exportPage(){

	var checkboxs = document.getElementsByName("goodsIds");
	for ( var i = 0; i < checkboxs.length; i++) {
		var e = checkboxs[i];
		e.checked = true;
	}
	$("#goodsisthirdList").attr("action","exportGoodsCheck.htm?CSRFToken="+$(".token_val").val());
	$("#goodsisthirdList").submit();
}

/*导出选中的记录*/
function exportCheck(){
	var bool = false;
	var brandIds = document.getElementsByName("goodsIds");
	for (var i = 0; i < brandIds.length; i++) {
		var e = brandIds[i];
		if (e.checked == true) {
			bool = true;
		};
	}
	if (bool == false)
	{
		$("#dialog-tip").find(".tip").text("请先选择一行!");
		$("#dialog-tip").dialog(
			{
				resizable : false, height : 150, width : 270, modal : true, autoOpen : true, buttons : {
				"确定" : function ()
				{
					$(this).dialog("close");
				}
			}
			});
	}
	else
	{
		$("#goodsisthirdList").attr("action","exportGoodsCheck.htm?CSRFToken="+$(".token_val").val());
		$("#goodsisthirdList").submit();
		$("#goodsisthirdList").attr("action","batchDelGoodsisthird.htm?CSRFToken="+$(".token_val").val());
	};
}

