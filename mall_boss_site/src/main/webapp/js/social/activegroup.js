/**
 * 活跃小组查询
 */
function searchactive() {
	if (checkInputContent()) {
		var groupname = $(".search_text").val();
		groupname = encodeURI(encodeURI(groupname));
		var groupisactive = $("#groupIsActive  option:selected").val();
		//groupisactive =  encodeURI(encodeURI(groupisactive));
		$('#search_active_from').attr("action", "querybyactivegroup.htm?groupname=" + groupname + "&groupisactive=" + groupisactive);
		$("#pageSize").val(5);
		$("#pageNo").val(1);
		$('#search_active_from').submit();
	} else {
	}
}