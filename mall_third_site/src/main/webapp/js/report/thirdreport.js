//重置
function resetReportForm() {
	$(".form-control").val("");
}
//查询
function queryReport(){ 
	$("#reportForm").attr("action","queryCheckReport.html");
	$("#reportForm").submit();
}
//导出
function exportReport() {
	$("#reportForm").attr("action","exportThirdReport.htm");
	$("#reportForm").submit();
}