$(function(){
	
	//搜索选择
	$(".search_sel span").text($(".search_sel select").find("option:selected").text());
	$(".search_sel select").change(function() {
		var val = $(this).find("option:selected").text();
		$(".search_sel span").text(val);
	});
	
	 $("#dialog-confirm").hide();
	 $("#dialog-tip").hide();
	 $("#dialog-err-tip").hide();
	 $("#dialog-wrong-tip").hide();
	
});
//判断是否是正整数的正则表达式
var numReg = /^\d+$/;
/*改变每页显示的行数*/
function changePageShow(){
		//每页显示行数
	  var size=$("#list_size").val();
	  

	  if(size=="" || size=="0" || isNaN(size) || size < 0 || !numReg.test(size)){
		  $("#dialog-err-tip").dialog(
			        {
			            resizable : false, height : 150, width : 270, modal : false, autoOpen : true, buttons : {
			                "确定" : function () 
			                {
			                    $(this).dialog("close");
			                }
			            }
			        });
	  }else{
		  $("input[name='pageNo']").val("1");
		  $("input[name='pageSize']").val(size);
		  $("#searchForm").submit();
	  }
}
/*跳转分页*/
function changeShowPage(totalPages){
	  var list_pageno=$("#list_pageno").val();
	  //如果要跳转到的页数大于总页数，则跳到最后一页
	  if(list_pageno>totalPages){
		  list_pageno=totalPages;
	  }
	  list_pageno=(list_pageno<1?1:list_pageno);
	  var list_pageSize=$("#list_size").val();
	  if(list_pageno=="" || list_pageno=="0" ||  isNaN(list_pageno) || !numReg.test(list_pageno) || list_pageSize=="" || list_pageSize=="0" || isNaN(list_pageSize) || list_pageSize < 0 || !numReg.test(list_pageSize)){
		  $("#dialog-err-tip").dialog(
			        {
			            resizable : false, height : 150, width : 270, modal : false, autoOpen : true, buttons : {
			                "确定" : function () 
			                {
			                    $(this).dialog("close");
			                }
			            }
			        });
	  }else{
		  
		  $("input[name='pageNo']").val(list_pageno);
		  $("input[name='pageSize']").val(list_pageSize);
		  $("#searchForm").submit();
	  }
}



//提交表单
function searchSubmit(){
	 var list_pageSize=$("#list_size").val();
	$("input[name='pageNo']").val("1");
	 $("input[name='pageSize']").val(list_pageSize);
	
	$("#searchForm").submit();
}

//分页出发函数
function showPage(pageNo,pageSize){
	
	 $("input[name='pageNo']").val(pageNo);
	 $("input[name='pageSize']").val(pageSize);
	  
	 $("#searchForm").submit();
	
	
}


//反选
//function unSelectAll(obj) {
//	var checkboxs = document.getElementsByName(obj);
//	for (var i = 0; i < checkboxs.length; i++) {
//		var e = checkboxs[i];
//		e.checked = !e.checked;
//	}
//}
// 全选
//function selectAll(obj) {
//	var checkboxs = document.getElementsByName(obj);
//	for (var i = 0; i < checkboxs.length; i++) {
//		var e = checkboxs[i];
//		e.checked = true;
//	}
//}


//判断删除时是否选中一条
function oneCheck(name){
	//判断是否选中多个按钮
	  var count = $("input[name='"+name+"']:checked").size();
	  if(count>1){
		  
		  $("#dialog-tip").dialog(
				  {
					  resizable : false, height : 150, width : 270, modal : false, autoOpen : true, buttons : {
					      "确定" : function () 
					        {
					                $(this).dialog("close");
					        }
					 }
				 });
		  return false;
	  }
	  return true;
}


