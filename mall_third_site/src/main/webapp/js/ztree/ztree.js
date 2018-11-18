function click(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("tree");
	nodes = zTree.getSelectedNodes();
	$("#textInput").val(nodes[0].name);
	$("#checkedIds").val(nodes[0].id);
	$("#textInput").focus();
}
 	
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDownForArea);
}
		
function onBodyDownForArea(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}
		
