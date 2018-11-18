$(function() {
    $("#tabs").tabs();
    $("input[type=submit],button").button().click(function(event) {
        event.preventDefault();
    });
    $(document).tooltip();
    
    /* 导入商品弹出层 */
    $( "#dialog-importform" ).dialog(
    {
        resizable:false, autoOpen : false, height : 400, width : 750, modal : true, buttons : 
        {
        	 "确定" : function () 
	            {
        		 	$(".importGoods").submit();
	            },
	            '取消' : function () 
	            {
	            	location.href="";
	            }
        },
        close : function () 
        {
        	location.href="";
        }
    });
});

/*改变每页显示的行数*/
function changePageShow(url) {
    var size = $("#list_size").val();
    $("#searchPageSize").val(size);
    $("#searchPageNo").remove();
    if (size == "" || size == "0" || isNaN(size) || size <= 0) {
        $("#dialog-err-tip").dialog({
            resizable: false,
            height: 150,
            width: 270,
            modal: true,
            autoOpen: true,
            buttons: {
                "确定": function() {
                    $(this).dialog("close");
                }
            }
        });
    } else {
        $("#search_form").attr("action", url);
        $("#search_form").submit();
    };
}
/*跳转分页*/
function changeShowPage(url, totalPages) {
    var list_pageno = $("#list_pageno").val();
    if(list_pageno>totalPages){
    	list_pageno=totalPages;
    }
    $("#searchPageSize").val($("#list_size").val());
    $("#searchPageNo").val(list_pageno);
    if (list_pageno == "" || list_pageno == "0" || isNaN(list_pageno) || list_pageno <= 0) {
        $("#dialog-err-tip").dialog({
            resizable: false,
            height: 150,
            width: 270,
            modal: true,
            autoOpen: true,
            buttons: {
                "确定": function() {
                    $(this).dialog("close");
                }
            }
        });
    } else {
        $("#search_form").attr("action", url);
        $("#search_form").submit();
    };
}
/*点击跳转页码*/
function clickShowPage(url, pageSize, pageNo) {
    $("#searchPageSize").val(pageSize);
    $("#searchPageNo").val(pageNo);
    if (pageNo == "" || pageNo == "0" || isNaN(pageSize) || isNaN(pageNo) || pageNo <= 0) {
        //提示框
        $("#dialog-err-tip").dialog({
            resizable: false,
            height: 150,
            width: 270,
            modal: true,
            autoOpen: true,
            buttons: {
                "确定": function() {
                    $(this).dialog("close");
                }
            }
        });
    } else {
        $("#search_form").attr("action", url);
        $("#search_form").submit();
    };
}


/*点击批量删除按钮时触发*/
function batchDel(obj) {
    var bool = false;
    var brandIds = document.getElementsByName(obj);
    for (var i = 0; i < brandIds.length; i++) {
        var e = brandIds[i];
        if (e.checked == true) {
            bool = true;
        }
    }
    if (bool == false) {
        $("#dialog-tip").dialog({
            resizable: false,
            height: 150,
            width: 270,
            modal: true,
            autoOpen: true,
            buttons: {
                "确定": function() {
                    $(this).dialog("close");
                }
            }
        });
    } else {
    	$("#dialog-confirm").html("<span>您确定要删除吗？</span>");
        $("#dialog-confirm").dialog({
            resizable: false,
            height: 150,
            width: 270,
            modal: true,
            autoOpen: false,
            buttons: {
                "确定": function() {
                    $("#laclList").attr("action","batchDelImport.htm?CSRFToken="+$(".token_val").val());
                    $("#laclList").submit();
                    $(this).dialog("close");
                },
                '取消': function() {
                    $(this).dialog("close");
                }
            }
        });
        $("#dialog-confirm").dialog("open");
    };
}
/*提交表单*/
function subForm(show) {
    $("#search_form").submit();
}
/*下载导入商品模板*/
function downImportExcel(){
	$(".downImportExcel").submit();
}
/*点击商品导入按钮,弹出导入框*/
function importGoods(){
	 $( "#dialog-importform" ).dialog("open");
}
