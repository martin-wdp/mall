/**
 * 列表全选
 * @param obj 多选元素this
 * @param name 字符串
 */ 
function selectAll(obj,name){
	 var a = $("input[name='"+name+"']");
	 if(obj.checked==true){
		  for(var i = 0;i<a.length;i++){
		       a[i].checked = true; 
		   }
	 }else{
		 for(var i = 0;i<a.length;i++){
		     a[i].checked = false;
		    }
	 }
   
  }

/**
 * 检查是否选中行
 * @param obj
 * @returns {boolean}
 */
function checkSelect(obj){
	//判断是否有按钮选中
	var bool = false;
	var brandIds = document.getElementsByName(obj);
	//var checkedBrand = new Array();
	for (var i = 0; i < brandIds.length; i++) {
		var e = brandIds[i];
		if (e.checked == true) {
			bool = true;
			//checkedBrand.push(e.value);
			break;
		}
	}
	return bool;
}

//根据传过来的对象验证是否为数字
function checkNumAndDialog(inputobj){
	if (isNaN($("#"+inputobj).val() )) {
		$("#"+inputobj).next().html("请填写正确格式的数字");
        $("#"+inputobj).next().addClass("error");
		$("#"+inputobj).focus();
		return false;
	}
	else {
		$("#"+inputobj).next().html("");
		return true;
	}
}

//验证特殊字符，将调试显示到页面中
function checkSpecSymb(inputobj){
	var regexp=new RegExp("[`~!@#$^&*()={}':;',\\[\\]<>/?~！@#￥……&*（）{}【】‘；：”“'。，、？]");
	if (regexp.test( $("#"+inputobj).val() ) ) {
        $("#"+inputobj).next().addClass("error");
		$("#"+inputobj).next().html("输入的内容包含特殊字符!");
		$("#"+inputobj).focus();
		return false;
	}
	else {
		$("#"+inputobj).next().html("");
		return true;
	}
}