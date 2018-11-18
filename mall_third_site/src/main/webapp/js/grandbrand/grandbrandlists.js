$(function(){
    /**
     * 全选
     */
    $("#check_boxs").click(function(){
        var sc_id="";
        if($(this).prop("checked")){
            $(".brandIds").each(function(){
                if(! $(this).prop("checked")){
                    $(this).prop("checked",true);
                }
            });
            $(".brandIds").each(function(){
                sc_id += $(this).val()+"|";
            });
        }else{
            $(".brandIds").each(function(){
                if($(this).prop("checked")){
                    $(this).prop("checked",false);
                }
            });
        }
    });
});



//查询状态改变
function changTbl(obj){
	resetThirdOrder();
	$(".tab_status").prop("value",obj);
	$(".queryThirdGrandBrandLists").submit();
}
function changTbl2(obj){
	resetThirdOrder();
	$(".tab_status").prop("value",obj);
	$(".queryThirdGrand").submit();
}

//品牌列表与申请品牌重置表单
function resetThirdOrder(){
	$(".queryThirdGrandBrandLists")[0].reset();
	$(".form-control").prop("value","");
}

//品牌列表与申请品牌重置表单
function resetThirdApply(){
    $(".queryapplybrand")[0].reset();
    $(".form-control").prop("value","");
}

/**
 * 品牌列表与申请品牌提交查询
 */
function submitBrand(){
    $(".queryThirdGrandBrandLists").submit();
}
/**
 * 申请自定义提交查询
 */
function submitApplyBrand(){
    $(".queryapplybrand").submit();
}



/**
 * 申请品牌方法
 * @param pageNo
 * @param beginNo
 */
function forThirdBrand(pageNo,beginNo){
	var brand_Name=$("#brand_Name").val();
	if(pageNo<=0){
		pageNo=1;
	}
	$(".iqy_div").html("");
	$(".pg_forband").html("");
	$.post("queryForGrandBrandLists.htm", { pageNo:pageNo,brandName:brand_Name },
			   function(data){
		         $(".iqy_div").append("<div class='iqy_h_div checkbox checkbox-primary'>");
			     $(data.list).each(function(index,element){
			    	 $(".iqy_h_div").append("<label style='width: 138px;'><input class='for_brandId' value="+element.brandId+" name='brandId' type='checkbox'/><img src="+element.brandLogo+"  width='90' height='40' /><br/><strong>"+element.brandName+
			    	 "</strong></label>");
			    	 $.material.init();
			     });
			     $(".pg_forband").append("<div style='margin-left:25px;width:500px;text-align:center;'></div>");
			     var status=0;
			     var pagIndex;
			     //上一页
				    if(data.pageNo!=2&&data.pageNo%5==2&&data.pageNo>4&&beginNo-4>0){
						   $(".pagination").html("<li><a class='shangyiye' onclick='forThirdBrand("+(data.pageNo-1)+","+(beginNo-5)+");' href='javascript:;'></a></li>");
					 }else{
						   $(".pagination").html("<li><a class='shangyiye' onclick='forThirdBrand("+(data.pageNo-1)+","+(beginNo)+");' href='javascript:;'></a></li>");
					 }
                   $('.shangyiye').html('<span aria-hidden="true">&laquo;</span>');
			     //页码
			    for(var i=0;i<data.totalPages;i++){
			    	if(i>=beginNo  && i<beginNo+10){
			    		if(i==beginNo&&beginNo!=0){
			    			status=-5;
			    		}else if(i<beginNo+9){
			    			status=0;		    	
			    		} else{
			    			status=5;
			    		}
			    		if(i==data.pageNo-1){
			    			$(".pagination").append("<li class='active'><a id='no"+(i+1)+"' onclick='forThirdBrand("+(i+1)+",+"+(beginNo-status)+");' href='javascript:;'>"+(i+1)+"</a></li>");
			    		}else{
			    			$(".pagination").append("<li><a id='no"+(i+1)+"' onclick='forThirdBrand("+(i+1)+",+"+(beginNo+status)+");' href='javascript:;'>"+(i+1)+"</a></li>");
			    		}	
			    	}
			    }
			    //下一页
			    if(data.pageNo!=4&&data.pageNo%5==4){
			    	status=5;
			    	if(data.pageNo!=data.totalPages){
			    		pagIndex=1;
			    	}else{
			    		pagIndex=0;
			    	}
			    }else{
			    	status=0;
			    	if(data.pageNo!=data.totalPages){
			    		pagIndex=1;
			    	}else{
			    		pagIndex=0;
			    	}
				}
                   if(beginNo+status<data.totalPages){
                       $(".pagination").append("<li><a class='xiayiye' onclick='forThirdBrand("+(data.pageNo+pagIndex)+","+(beginNo+status)+");' href='javascript:;'></a></li>");
                   }else{
                       $(".pagination").append("<li><a class='xiayiye' onclick='forThirdBrand("+(data.pageNo+pagIndex)+","+(beginNo)+");' href='javascript:;'></a></li>");
                   }

                $(".xiayiye").html('<span aria-hidden="true">&raquo;</span>');

	});
}
function closeForGrandBrand(){
	$("#brand_Name").prop("value","");
	cls();
	
}
/**
 * 批量移除品牌
 * @returns {null}
 */
 function  delGrandBrands(id){
	 var bool = false;
	 var checks = $(".brandIds");
	 var checkGroup = new Array();
	 for (var i = 0; i < checks.length; i++) {
	        var e = checks[i];
	        if (e.checked == true) {
	            bool = true;
	            checkGroup.push(e);
	        }
	 }
	 if(bool==false){
         $('#delete_brand_check').modal('show');
		 return null;
	 }
    //自定义批量删除
    if(id==0){
        //显示确定按钮
        $('#delete_brand_all').modal('show');
        $("#primary").attr("onclick","delThirdApplyBrandIds();");
    }else{
        $('#delete_brand_all').modal('show');
        //为按钮绑定事件
    }
 }

//申请品牌 批量删除
 function delThirdGoodsBrandIds(){
     //装在选中的品牌id
     var arr=new Array();
     var checkbox=document.getElementsByName("brandIds");
     //判断选中的id
     for(var i=0;i<checkbox.length; i++){
         if(checkbox[i].checked==true){
             arr.push(checkbox[i].value);
         }
     }
     $('.brandIds').val(arr);
	 $(".upd_GrandBrand").submit();
 }

//自定义品牌批量删除
 function delThirdApplyBrandIds(){
     //装在选中的品牌id
     var arr=new Array();
     var checkbox=document.getElementsByName("applyBrandId");
     //判断选中的id
     for(var i=0;i<checkbox.length; i++){
         if(checkbox[i].checked==true){
             arr.push(checkbox[i].value);
         }
     }
     $.ajax({
         type:"POST",
         url:"third/deleteapplybrands.htm",
         data:{"applyBrandId":arr},
         traditional:true,
         success:function(data){
             if(data==1){
                 location.reload();
             }
         }
     });
 }
 function refusalreason(text){
	 $("#applyRefusalReason").html("拒绝原因："+text);
	 $("#brand_reason").modal('show');
 }
 function clsoseRefusalReason(){
	 $("#applyRefusalReason").html("");
	 $(".dia4").hide();
	 cls();
 }
//单个移除品牌(1)
 function sub_update(){
     //表单重置
	resetThirdOrder();
 	$(".queryThirdGrandBrandLists").prop("action","updateGrandBrand.htm").submit();
 }

/*删除单个自定义品牌*/
 function sub_updateapply(){
	 	$.ajax({
	 		type:"POST",
	 		url:"third/deleteapplybrand.htm",
	 		data:{"applyBrandId":$("#applyBrandIddel").val(),"applyThirdId":$("#applyThirdId").val()},
	 		success:function(data){
	 			location.reload();
	 		}
	 	});
	 }

/**
 * 移除单个品牌
 * @param id
 */
 function removeGrandBrand(id){
    $(".brandId").prop("value",id);
    $.ajax({
        type:"POST",
        url:"checkGoods.htm?brandId="+id,
        success:function(data){
            $('#delete_brand').modal('show');
            /*if(data == 0){
                $('#delete_brand').modal('show');
            }else{
                $('#cant_delete_brand').modal('show');

            }*/
        }
    });


 }

/*自定义品牌弹出框*/
 function removeApplyBrand(id){
	 $("#applyBrandIddel").prop("value",id);
     $('#delete_apply_brand').modal("show");
 }
 
//提交表单
function forThirdBrand_form(){

    //装在选中的品牌id
    var arr=new Array();
    var checkbox=document.getElementsByName("brandId");
    //判断选中的id
    for(var i=0;i<checkbox.length; i++){
        if(checkbox[i].checked==true){
            arr.push(checkbox[i].value);
        }
    }
    $('.check_brandId').val(arr);
     var bool = false;
	 var checks = $(".for_brandId");
	 var checkGroup = new Array();
	 for (var i = 0; i < checks.length; i++) {
	        var e = checks[i];
	        if (e.checked == true) {
	            bool = true;
	            checkGroup.push(e);
	        }
	 }

	 if(bool==false){
         $('.brand_text').html("请至少选择一个品牌");
	 }else{
         $('.brand_text').html("");
         $(".for_ThirdGrandBrand").submit();
	}
}
//窗体加载时间
$(function(){
	//标签页的状态
    if($(".tab_status").val()==1){
		$(".grandbrand_lists").addClass("cur");
	}else if($(".tab_status").val()==0){
		$(".add_grandbrand").addClass("cur");
	}
    $(".add_brand").hide();
    $(".close_add").click(function(){
    	$(".add_brand").hide();
    	cls();
    });
});
//验证品牌
function checkOtherName(obj){
	  if(obj.value!=''){
	   		checkbrandName(obj.value);
	   		if(flag==0){
  			$("#brandtip").html('该品牌已存在于主营品牌');
  		}else{
  			$("#brandtip").html('');
  		}
	  }

}
//查询品牌名称是否存在
function checkbrandName(name){
	 $.post("checkThirdBrandName.htm?brandName="+encodeURI(encodeURI(name)),function(data){
		 if(data){
			flag=1;
		 }else{
			flag=0;
		 }
	});
 }
//添加自定义品牌
function addApply(){

		var boo = true;
			var name = $("#applyBrandName").val();
			var url = $("#applyUrl").val();
			var f = $("#applyPic").val();
			var c = $("#certificate").val();
			var fi ="applyPic,certificate";
		if(name==''){
			$("#brandtip").html('品牌名称不能为空');
			boo = false&&boo;
		}

		if(url==''){
			$("#applyUrltip").html('品牌网址不能为空');
			boo = false&&boo;
		}else{
            $("#applyUrltip").html('');
        }
		if(f==''){
			$("#applyPictip").html('未选择文件');
			boo = false&&boo;
		}else{
            $("#applyPictip").html('');
        }
		if(c==''){
			$("#certificatetip").html('未选择文件');
			boo = false&&boo;
		}else{
            $("#certificatetip").html('');
        }
		checkbrandName(name);
		if(flag==0){
			$("#brandtip").html('该品牌已存在于主营品牌');
			boo=false&&boo;
		}else{
            $("#brandtip").html('');
        }
		if(f==0){
			boo=false&&boo;
		}else{
			boo=true&&boo;
		}
		if(boo){
            var param = 'third/addapplybrand.htm?applyBrandName='+name+'&applyUrl='+url;

            $.ajaxMutiUpload({
                url:param,
                fileElementId: fi, async: false,
                success: function(data){
                    if(data!=null){
                        clearTip();
                        location.reload();
                        $('#applyBrand').modal('show');
                    }else{
                        clearTip();
                    }
                }
            });
		
		}
}
//清空自定义品牌填写类容
function clearTip(){
	$("#applyBrandName").val('');
	$("#applyUrl").val('');
	$("#applyPic").val('');
	$("#brandtip").html('');
    $("#applyUrltip").html('');
    $("#applyPictip").html('');
}
//关闭自定义品牌弹框
function closeBrDia(){
	cls();
	$(".add_brand").hide();
}

function newThirdBrand(){
	dia(10);
}