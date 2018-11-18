

/**
 * 批量删除商品组合
 */
 function  delThirdGroups(){
	 var bool = false;
	 var checks = $(".ch_goods");
	 var checkGroup = new Array();
	 for (var i = 0; i < checks.length; i++) {
	        var e = checks[i];
	        if (e.checked == true) {
	            bool = true;
	            checkGroup.push(e);
	        }
	 }
	 if(bool==false){
		 $(".show_title").text("请至少选择一行数据!");
		 $('#select-tip').modal('show');
		 return null;
	 }
	 $('#delete-tip').modal('show');
 }
 
 
 function delThirdGIds(){
	 $("#GroupsList").attr("action", "thirdDelGoodsGroups.htm").submit();
	 
 }
 
 
/**
 * 删除商品组合
 */
 function delThirdGroup(id){
    $("#mform").submit();
 }

/**
 * 更改页数
 */
function changePageNo(obj){
	$(".simple_search_goods").append("<input type='hidden' name='pageNo' value='"+$(obj).attr("data-role")+"' />").submit();
}

/**
 * 根据特惠套装和人气组合进行一个分类
 * @param type
 */
function changGroupType(type){
	if(type==1){
 		$(".pgTextChange").hide();
	}else{
		/*表示选择的是特惠套装*/
		$(".pgTextChange").show();
	}
}


/**
 * 进行商品组合的保存
 */
var num=0;
function saveGroup(){
	 var bValid = true;
	 bValid= checkNull($("#page_text_name"),$(".pg_textName"))&&bValid;
	 if(checkNull($("#page_text_name"),$(".pg_textName"))){
		 bValid=checkSpecSymb($("#page_text_name"), $(".pg_textName"))&&bValid;
	 }
	 
	 if($("input[name='groupPrefertype']:checked").val()==0){
		 bValid = checkRegexp( $("#page_text_price"),/^[0-9]+.?[0-9]{0,2}$/, "价格必须是正整数或两位小数点" , $(".pg_textPrice")) && bValid;
	 }
	 if(bValid&&num==0){
         num+=1;
		$(".add_thirdGroups").submit();
		$(".add_thirdGroups").attr("action","");
	 }
}


/**
 * 在进行修改前进行值的填充
 * @param id 要修改的id
 */
function modifyGroup(id){
	  
	  //跳转到对应的控制器
	  $.get("tomodithirdgroupbygroupid.html", { groupId: id },
			  function(data){
			      $("#page_text_name").attr("value",data.groupName);
			     
			      //判断是哪种类型的组合商品
			      if(data.groupPrefertype=="0"){
			    	  $("#page_text_price").attr("value",data.groupPreferamount);
			    	  $(".pgTextChange").show();
			      }else{
			    	  $("#group_prefertype").attr("checked",null);
			    	  $("#groupPrefertype1").attr("checked","checked");
			    	  $(".pgTextChange").hide();
			   }
                  $("#fanwei").html("");
			   for(var i = 0;i<data.productList.length;i++){
				   var html="<div class='cp_choose' style='margin:8px;' id='fanwei"+data.productList[i].productDetail.goodsInfoId+"'>" +
				   		"<input type='hidden' name='productId' id='goodsId"+data.productList[i].productDetail.goodsInfoId+"' value='"+data.productList[i].productDetail.goodsInfoId+"'>" +
				   		"<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;货号："+data.productList[i].productDetail.goodsInfoItemNo+"</p>" +
				   		"<p>货品名称："+data.productList[i].productDetail.goodsInfoName+"<input type='button' class='op_btn' onclick='removeTr("+data.productList[i].productDetail.goodsInfoId+")' value='移除'></p></div>";
				   $("#fanwei").append(html);
			   }
			      
			      
			      
	  });
	  $(".modal-title").html('修改商品组合');
	  $("#pt_ben_save").html("修改组合");
	  //添加商品组合的id
	  $(".group_id").attr("value",id);
	  //修改form的action
	  $(".add_thirdGroups").attr("action","updateThirdGroup.htm");
	  $(".group_wp").hide();
	  $(".addGroup").show();
    addproduct(1, 5);
}

/**
 * 关闭修改商品组合和添加商品组合
 */
function closeGroup(){
	 $(".ui-state-error").val("").removeClass("ui-state-error");
	 $(".ui-state-highlight").text("").removeClass("ui-state-highlight");
	 $("#groupPrefertype1").attr("checked",null);
	 $("#group_prefertype").attr("checked","checked");
	 $("#pt_ben_save").html("保存组合");
	 $(".group_id").attr("value",null);
	 $(".add_thirdGroups").attr("action","thirdAddGoodsGroupManager.htm");
	 $("#page_text_name").attr("value","");
	 $("#page_text_price").attr("value","");
	 $(".add_thirdGroups")[0].reset();
	 $(".del_group_product_list ").html("");
	 $("#fanwei").html("");
	 $(".group_wp").show();
	 $(".addGroup").hide();
	
}

/*编辑组合内商品*/
function addproduct(pageNo, pageSize)
{
	var brandId =$("#brandId").val();
    /*AJAX查询所有的货品列表*/
	var productNo ="";
	if(!isNaN($(".sel_product_no").val())){
		productNo="&productNo="+$(".sel_product_no").val();
	}
    $.get("queryProductForCoupon.htm?groupId=&pageNo="+pageNo+"&pageSize="+pageSize+"&brandIds="+brandId+productNo, 
    function (data) 
    {
        var list = data.list;
        var productListHtml = "";
        for (var i = 0; i < list.length; i++)
        {  
            productListHtml = productListHtml + "<tr>" + "<td class='tc'>" + (i - 1 + 2) + "</td>" +"<td class='tc'><input type='checkbox' class='productId'  onclick='addpro(this);'";
           
            	var pro =  document.getElementsByName("productId");
            	for(var j=0;j<pro.length;j++){
            		if(pro[j].value==list[i].goodsInfoId){ 
            			 productListHtml = productListHtml +' checked="checked" ';
            		}
            	}
            
            productListHtml = productListHtml+"id='delete_"+list[i].goodsInfoId+"' value='" + list[i].goodsInfoId + "'/></td>"+ "<td class='tc specName'>" ;
            if (list[i].specVo.length > 0)
            {
                for (var k = 0; k < list[i].specVo.length; k++)
                {
                    productListHtml = productListHtml + list[i].specVo[k].spec.specName;
                    productListHtml = productListHtml + ":" + list[i].specVo[k].goodsSpecDetail.specDetailName + "<br/>";
                }
            }
            productListHtml = productListHtml + "</td>" + "<td class='tc goodsInfoItemNo'>" + list[i].goodsInfoItemNo + "</td>" + "<td class='tc goodsInfoName' title='"+list[i].goodsInfoName+"'>" + list[i].goodsInfoName.substring(0,10) + "...</td>" + "<td class='tc' id='opear'>" + "</tr>";
        }
        $("#productAddList tbody").html(productListHtml);  
        /*添加页脚*/  
        $("#productAddList tfoot").html("");
        
        var foot = '<div class="ops-right"><nav><ul class="pagination">' +
        	'<li>'+
        	'<a href="javascript:;" aria-label="Previous" onclick="addproduct('+data.prePageNo+',5);" data-role="'+ data.prePageNo +'">'+
        	'<span aria-hidden="true">&laquo;</span>'+
        	'</a>';
        if(data.startNo>1){
        	foot = foot + '<li><a href="javascript:;">'+1+'</a></li>';
        }
        
        for(var l = data.startNo;l <=data.endNo;l++){
        	if(l == data.pageNo){
        		foot = foot +'<li class="active"><a href="javascript:;" onclick="addproduct('+l+',5);" data-role="+ l +">'+ l+'</a></li>';
        	}else{
        		foot = foot +'<li><a href="javascript:;" onclick="addproduct('+l+',5);" data-role="'+ l +'">'+ l +'</a></li>';
        	}
        }
        
        if(data.totalPages>data.endNo){
        	foot = foot + '<li><a href="javascript:;" onclick="changePageNo(this)" data-role="+ data.totalPages+">'+data.totalPages+'</a><li>';
        }
        
        foot = foot +'<li>'+
        				'<a href="javascript:;" aria-lable"Next" onclick="addproduct('+data.nextPageNo+',5);" data-role="'+ data.nextPageNo+'">'+
        					'<span aria-hidden="true">&raquo;</span>'+
        				'</a>'+
        			  '</li>'+
        			  '</ul>'+
        			  '</nav>'+
        			  '</div>';
        $("#dia_footer").html(foot);
        
    }); 
    dia("pro");
}



/*点击添加货品列表前的checkbox*/
function addpro(obj)
{
    if (obj.checked == true)
    {
        var guige = $(obj).parent().parent().find(".specName").html().replace("<br>", "");
        var huohao = $(obj).parent().parent().find(".goodsInfoItemNo").html().replace("<br>", "");
        var name = $(obj).parent().parent().find(".goodsInfoName").attr("title").replace("<br>", "");
        html = '<div  class="cp_choose" style="margin:8px;" id="fanwei' + obj.value + '">';
        html += '<input type="hidden" name="productId" id="goodsId' + obj.value + '" value="' + obj.value + '"/>';
        html += '<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;货号：' + huohao + '</p>';
        html += '<p>货品名称：' + name + '<input type="button" style="margin-left: 30px;" class="btn btn-primary btn-xs" onclick="removeTr(\'' + obj.value + '\')" value="移除"></p>';
        html += "</div>";
        $('#fanwei').append(html);
        backRemove(obj.value);
    }
    else {
        removeTr(obj.value);
    }
}

/*移除组合关联的货品*/
function removeTr(objId){
    $("#delete_"+objId).attr("checked",false);
	$('#fanwei'+objId).remove();
	var delProIds = $(".del_group_product_id");
	for(var i = 0;i<delProIds.length;i++){
		if($(delProIds[i]).val()==objId){
			return ;
		}
	}
	$(".del_group_product_list").append("<input type='hidden' class='del_group_product_id' name='delproId' value='"+objId+"' />");
}
/*撤销删除关联的货品*/
function backRemove(objId){
	var delProIds = $(".del_group_product_id");
	for(var i = 0;i<delProIds.length;i++){
		if($(delProIds[i]).val()==objId){
			$(delProIds[i]).remove();
		}
	}
}