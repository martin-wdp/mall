/**
 * 待审核小组查询
 * 搜索
 */
function searchcheck(){
	if(checkInputContent()){
		var groupname = $(".search_text").val();
		groupname =  encodeURI(encodeURI(groupname));
		$('#search_check_from').attr("action", "querybycheckedgroup.htm?showflag=1&groupname="+groupname);
		$("#pageSize").val(5);
		$("#pageNo").val(1);
		$('#search_check_from').submit();
	}else{
	}
}

/**
 * 高级搜索
 * */
function searchadvancedcheck(){
	if(checkInputContent()){
		var groupname = $(".search_text").val();
		groupname =  encodeURI(encodeURI(groupname));
		$('#advanced_from').attr("action", "querybycheckedgroup.htm?showflag=1&groupname="+groupname);
		$("#advancedpageSize1").val(5);
		$("#advancedpageNo1").val(1);
		$('#advanced_from').submit();
	}else{
	}
}
