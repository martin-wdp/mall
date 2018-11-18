$(function() {
	/*查询所有的一级分类,并添加到页面*/
	$.get("queryAllGrandCateForThirdtwo.htm?cateId=" + 0, function(data) {
		var html = "<option value=''>-请选择-</option>";
		for ( var i = 0; i < data.length; i++) {
			html += "<option value='"+ data[i].catId +"'>"
					 + data[i].catName
					+ "</option>";
		}
		$("#cat_first").html(html);
		$("#cat_second").html("<option value=''>-请选择-</option>");
		$("#cat_third").html("<option value=''>-请选择-</option>");
	});
	
});

/*选择分类*/
function loadSecondCat(obj){
	$.get("queryAllGrandCateForThirdThree.htm?cateId=" + obj.value, function(data) {
		var html = "<option value=''>-请选择-</option>";
		for ( var i = 0; i < data.length; i++) {
			html += "<option value='"+ data[i].catId +"'>"
			 + data[i].catName
			+ "</option>";
		}
		$("#cat_second").html(html);
		$("#cat_third").html("<option value=''>-请选择-</option>");
	});  
	
}


function loadThirdCat(obj){
	$.get("queryAllGrandCateForThirdThree.htm?cateId=" + obj.value, function(data) {
		var html = "<option value=''>-请选择-</option>";
		for ( var i = 0; i < data.length; i++) {
			html += "<option value='"+ data[i].catId +"'>"
			 + data[i].catName
			+ "</option>";
		}
		$("#cat_third").html(html);
	});
	
}


function loadcateParam(obj){
	  $.get("queryTypeVoByCatId.htm?catId=" +obj.value, function (data){
		  var expandParam = data.expandParams;
		  var expandParamHtml = "<tr class='type_params'>";
		  if( expandParam.length>0){
	        	for (var i = 0; i < expandParam.length; i++)
		        {
		            if (expandParam[i].valueList.length > 0)
		            {
		                expandParamHtml = expandParamHtml + "<th><span class='sp'>*</span>" + expandParam[i].expandparamName + "</th><td>" + "<input type='hidden' name='expandParamId' value='" + expandParam[i].expandparamId + "'>" + "<select name='expandparamValue'>";
		                for (var k = 0; k < expandParam[i].valueList.length; k++)
		                {
		                    expandParamHtml = expandParamHtml + "<option value='" + expandParam[i].valueList[k].expandparamValueId + "'>" + expandParam[i].valueList[k].expandparamValueName + "</option>";
		                };
		                expandParamHtml = expandParamHtml + "</select></td>";
		                
		            }else{
			        	 expandParamHtml = expandParamHtml + "您选择的商品分类下没有扩展参数!";   
			        }
		        }
	        	expandParamHtml = expandParamHtml +'  <td><button class="queding" style="float:none;" onclick="queding();">搜索</button></td>';
	        }
		  expandParamHtml = expandParamHtml +'</tr>';
			$(".paramTable").html(expandParamHtml);   
		  
	  });
	
}