function addCate(){
     $.get("getAllThirdCateForList.htm",function(data){
		 alert(111);
    	 if (null != data && data.length > 0)
         {
             $("#allcates").html("");
             var html = getCateList(data);
             $("#allcates").html(html);
         }
     });
     $("#addSorts").modal('show');
    }

/* 处理传递过来的数据,并添加到页面中 */
function getCateList(data)
{
    var html = "";
    for (var i = 0; i < data.length; i++)
    {
        html = html + "<li>";
        if (data[i].catGrade == 1) {
            html = html + "<div class='optional-item' data-id='"+data[i].catId+"' data-name='"+data[i].catName+"'><i class='glyphicon glyphicon-plus-sign item-switch'></i>"+data[i].catName+"</div>";
            if(data[i].cateVos.length >0){
            		html = html + "<ul class='sec-list'>";
            		for(var j = 0;j < data[i].cateVos.length;j++){
            			if(data[i].cateVos[j].catGrade==2){
            			html = html + "<li>";
            			html = html + "<div class='optional-item' data-id='"+data[i].cateVos[j].catId+"' data-name='"+data[i].cateVos[j].catName+"'><i class='glyphicon glyphicon-plus-sign item-switch'></i>"+data[i].cateVos[j].catName+"</div>";
            			if(data[i].cateVos[j].cateVos.length >0){
            				html = html + "<ul class='third-list'>";
            				for(var n = 0;n < data[i].cateVos[j].cateVos.length;n++){
            					if(data[i].cateVos[j].cateVos[n].catGrade==3){
            						html = html + "<li>";
            						html = html +"<div class='optional-item' data-id='"+data[i].cateVos[j].cateVos[n].catId+"' data-name='"+data[i].cateVos[j].cateVos[n].catName+"'>"+data[i].cateVos[j].cateVos[n].catName+"</div>";
            						html = html + "</li>";
            					}
            				}
            				html = html + "</ul>";
            			}
            			html = html + "</li>";
            		}
            	}
              html = html + "</ul>";
            }
        }
        html = html +"</li>";
    }
    return html;
}

function chooseCate(){
	html ="";
	$(".selected-box .third-list .optional-item").each(function(){
		html = html + "<tr>";
		html = html +"<td>"+$(this).attr("data-id")+"<input type='hidden' name='cateIdp' id='cate_Id_p"+$(this).attr("data-id")+"' value='"+$(this).attr("data-id")+"' /></td>";
		html = html +"<td>"+$(this).attr("data-name")+"</td>";
		html = html +"<td><button class='btn btn-primary btn-sm' type='button' onclick='del(this)'>移除</button></td>";
	});
	 $("#catelist tbody").html(html);
	 $("#addSorts").modal('hide');
}

function del(obj){
	$(obj).parents("tr").remove();
}

function addBrands(){
	$.get("queryGrandBrandByThirdId.htm",function(data){
		 if (null != data && data.length > 0)
         {
             $("#addAllBrands").html("");
             var html = getBrandList(data);
             $("#addAllBrands").html(html);
         }
	});
	$("#addBrands").modal('show');
}

function getBrandList(data){
	 var html = "";
	    for (var i = 0; i < data.length; i++)
	    {
	        html = html + "<li>";
	        html = html + "<div class='optional-item' data-id='"+data[i].brandId+"' data-name='"+data[i].brandName+"'>"+data[i].brandName+"</div>";
	        html = html + "</li>";
	    }
	    return html;
}

function chooseBrands(){
	html ="";
	$(".brand-box .main-list .optional-item").each(function(){
		html = html + "<tr>";
		html = html +"<td>"+$(this).attr("data-id")+"<input type='hidden' name='brandIdP' id='brandIdP"+$(this).attr("data-id")+"' value='"+$(this).attr("data-id")+"' /></td>";
		html = html +"<td>"+$(this).attr("data-name")+"</td>";
		html = html +"<td><button class='btn btn-primary btn-sm' type='button' onclick='del(this)'>移除</button></td>";
	});
	 $("#brandslist tbody").html(html);
	 $("#addBrands").modal('hide');
}


function addProducts(pageNo, pageSize,flag){
	var offValue = $('input[name="offValue"]').val();
	if(offValue == ''){
		$('.offValueTip').css("display","block");
		return;
	}
	//把单品直减的价格 赋值给标签 传到后台
	$("#offValue").val($('input[name="offValue"]').val());
	$("#allpro tbody").html("");
	$("#pageNo").val(pageNo);
	$("#pageSize").val(pageSize);
	$.ajax({
		url:"thirdproducts.htm",
		async:true,
    	dataType:"json",
    	data: $("#searchPro").serialize(),
    	success:function(data){
    		if(data !=null && data.list.length >0){
    			var html=getProList(data);
    			if(flag==0){
    				addresultpro();
    			}
    			$("#allpro tbody").html(html);
    			var footHtml =  getProFoot(data);
    			$("#profoot").html(footHtml);
    		}
    	} 
	});
	$("#addGoods").modal('show');
	/*$.get("thirdproducts.htm?pageNo="+pageNo+"&pageSize="+pageSize,function(data){
		if(data !=null && data.list.length >0){
			$("#allpro tbody").html("");
			var html=getProList(data);
			$("#allpro tbody").html(html);
			var footHtml =  getProFoot(data);
			$("#profoot").html(footHtml);
			$("#addGoods").modal('show');
		}
	});*/
}

function getProList(data){
	var list = data.list;
	var html="";
	var pro =  document.getElementsByName("goodsIdP");
	for(var i=0;i<list.length;i++){
		html += "<tr><input class='choosepro' type='hidden' data-id='"+list[i].goodsInfoId+"' data-no='"+list[i].goodsInfoItemNo+"' data-name='"+list[i].goodsInfoName+"' data-price='"+list[i].goodsInfoPreferPrice+"' data-stock='"+list[i].goodsInfoStock+"' />";
		html +="<td>"+list[i].goodsInfoItemNo+"</td>";
		html +="<td>"+list[i].goodsInfoName+"</td>";
		html +="<td>&yen;"+list[i].goodsInfoPreferPrice+"</td>";
		html +="<td>"+list[i].goodsInfoStock+"</td>";
		html +="<td><button class=";
		var b = 0; 
		if(pro.length>0){
			for(var j=0;j<pro.length;j++){
				if(pro[j].value==list[i].goodsInfoId){
					b++;
				}
			}
		}
		if(b==0){
			html +="'btn btn-primary btn-sm add-query' type='button'>添加</button></td>";
		}else{
			html +="'btn btn-default btn-sm'  disabled='disabled' type='button'>已添加</button></td>";
		}
	}
	
	return html;
}

function addresultpro(){
	var pro =  document.getElementsByName("goodsIdP");
	var productIds = new Array();
	if(pro.length>0){
		for(var j=0;j<pro.length;j++){
			if(pro[j].value !=null && pro[j].value !==''){
				productIds[j]=pro[j].value;
			}
		}
		$.ajax({
			url:"chooseProductByChecked.htm?productIds="+productIds,
			success:function(data){
				var rhtml="";
				if(data!=null && data.length >0){
					var list = data;
					for(var i=0;i<data.length;i++){
						rhtml += "<tr><input class='choosepro' type='hidden' data-id='"+list[i].goodsInfoId+"' data-no='"+list[i].goodsInfoItemNo+"' data-name='"+list[i].goodsInfoName+"' data-price='"+list[i].goodsInfoPreferPrice+"' data-stock='"+list[i].goodsInfoStock+"' />";
						rhtml +="<td>"+list[i].goodsInfoItemNo+"</td>";
						rhtml +="<td>"+list[i].goodsInfoName+"</td>";
						rhtml +="<td>&yen;"+list[i].goodsInfoPreferPrice+"</td>";
						rhtml +="<td>"+list[i].goodsInfoStock+"</td>";
						rhtml +="<td><button class='btn btn-primary btn-sm del-query' type='button'>删除</button></td>";	
					}
				}
				$(".result-table tbody").html(rhtml);
			}
		});
	}
}

//添加页脚
function getProFoot(data){
   var i = 1;
   var footHtml="<div class='ops-right'>"; 
    footHtml +="<nav>";
    footHtml +="<ul class='pagination pagination-sm'>";
    if((data.pageNo-2)>0){
    	pageNo = data.pageNo-2;
    }else{
    	pageNo = data.firstPageNo;
    }
    if((data.lastPageNo-1)>0){
    	endNo = data.lasePageNo-2;
    }else{
    	endNo = 1;
    }
    if(data.startNo==1){
    	footHtml +="<li class='disabled'>";
    	footHtml +="<a href='javascript:void(0);' ria-label='Previous'>";
    	footHtml +="<span aria-hidden='true'>&laquo;</span>";
    	footHtml +="</a>";
        footHtml +="</li>";
    }else{
    	footHtml +="<li>";
    	var preNo= data.pageNo-1;
    	footHtml +="<a href='javascript:void(0);' ria-label='Previous'> onclick='addProducts("+preNo+","+data.pageSize+",1)'";
    	footHtml +="<span aria-hidden='true'>&laquo;</span>";
    	footHtml +="</a>";
        footHtml +="</li>";
    }
    
    for (var i= data.startNo; i <= data.endNo; i++){
    	if(i<=4){
    		if(i==data.pageNo){
    			 footHtml +="<li class='active'><a href='javascript:void(0);'>"+i+"</a></li>";
    		}else{
    			footHtml +="<li><a href='javascript:void(0);'  onclick='addProducts("+i+","+data.pageSize+",1)'>"+i+"</a></li>";
    		}
    	}
    }
    if(data.pageNo!=data.lastPageNo){
       if((data.lastPageNo-data.pageNo)>5){
    	   footHtml +="<li><a href='javascript:void(0);'>...</a></li>";
       }
    }
    if((data.pageNo == data.lastPageNo || data.endNo <= 1)){
    	if(data.lastPageNo>data.pageNo){
    		 footHtml +="<li><a href='javascript:void(0);'>"+data.lastPageNo+"</a></li>";
    	}
    	 footHtml +="<li class='disabled'>";
    	 footHtml +="<a href='javascript:void(0);' aria-label='Next'>";
    	 footHtml +="<span aria-hidden='true'>&raquo;</span>";
    	 footHtml +="</a>";
    	 footHtml +="</li>";
    }else{
    	if((data.lastPageNo-data.pageNo)>5){
    		footHtml +="<li><a href='javascript:void(0);' onclick='addProducts("+data.lastPageNo+","+data.pageSize+",1)'>"+data.lastPageNo+"</a></li>";
    	}
    	footHtml +="<li>";
    	var nextNo= data.pageNo+1;
    	footHtml +="<a href='javascript:void(0);' aria-label='Next' onclick='addProducts("+nextNo+","+data.pageSize+",1)'>";
    	footHtml +="<span aria-hidden='true'>&raquo;</span>";
    	footHtml +="</a>";
    	footHtml +="</li>";
    }
    return footHtml;
}

function choosePro(){
	html ="";
	$(".result-table .choosepro").each(function(){
		html += "<tr><input type='hidden' name='goodsIdP' value='"+$(this).attr("data-id")+"'>";
		html +="<td>"+$(this).attr("data-no")+"</td>";
		html +="<td>"+$(this).attr("data-name")+"</td>";
		html +="<td>&yen;"+$(this).attr("data-price")+"</td>";
		html +="<td>"+$(this).attr("data-stock")+"</td>";
		html +="<td><button class='btn btn-primary btn-sm' type='button' onclick='removeTr(this)'>移除</button></td>";
		html +="</tr>";
	});
	 $("#prolist tbody").html(html);
	 $("#addGoods").modal('hide');
}

function clearInfo(){
	$("#goodsName").val("");
	$("#goodsNo").val("");
	$("#productNo").val("");
	$("#lowgoodsInfoPrice").val("");
	$("#highgoodsInfoPrice").val("");
}

function removeTr(obj){
	$(obj).parents("tr").remove();
}

function chooseZKPro(){
	html ="";
	$(".result-table .choosepro").each(function(){
		html += "<tr><input type='hidden' name='goodsIdP' value='"+$(this).attr("data-id")+"'>";
		html +="<td>"+$(this).attr("data-no")+"</td>";
		html +="<td>"+$(this).attr("data-name")+"</td>";
		html +="<td>&yen;"+$(this).attr("data-price")+"<input type='hidden' class='goodsPrices' value='"+$(this).attr("data-price")+"'></td>";
		html +="<td><input class='xs-input required zeroOne discountInfos' type='text'  name='discountInfo' id='discount_info_"+$(this).attr("data-id")+"'  onblur='zkchange("+$(this).attr("data-price")+",this);'/></td>";
		html +="<td>&yen;<span class='zhj'>"+$(this).attr("data-price")+"</span><input type='hidden' name='discountPrice' value='"+$(this).attr("data-price")+"'><input type='hidden' class='form-control' name='discountFlag' id='discount_prices_"+$(this).attr("data-id")+"' value='0' readonly  ></td>";
		html +="<td width='40%'><button class='btn btn-primary btn-sm' onclick='mofensingle("+$(this).attr("data-price")+",this,1);' type='button'>移除分</button>";
		html +="&nbsp;<button class='btn btn-primary btn-sm' onclick='mofensingle("+$(this).attr("data-price")+",this,0);' type='button'>移除角</button></td>";
		html +="<td><button class='btn btn-primary btn-sm' type='button' onclick='removeTr(this)'>移除</button></td>";
		html +="</tr>";
	});
	 $("#prolist tbody").html(html);
	 $("#addGoods").modal('hide');
}

function mofensingle(price,obj,fixed){ 
	  var obp = $(obj).parents("tr").find("input[name='discountInfo']").val();
	  var b = (price*obp).toFixed(fixed);
	  if(fixed==1){
	 	 	$(obj).parents("tr").find("input[name='discountFlag']").val(1); 
	  }else if(fixed==0){
	  		$(obj).parents("tr").find("input[name='discountFlag']").val(2); 
	  }
	  $(obj).parents("tr").find(".zhj").text(b); 
	  $(obj).parents("tr").find("input[name='discountPrice']").val(b);
	}

function zkchange(price,obj){ 
	  var b = (price*$(obj).val()).toFixed(2);
	  $(obj).parents("tr").find("input[name='discountFlag']").val(0); 
	  $(obj).parents("tr").find(".zhj").text(b);
	  $(obj).parents("tr").find("input[name='discountPrice']").val(b);
}

function plzhekou(obj){
	var s = new RegExp(/^0\.[0-9]*[0-9]$/);
	if($(obj).val()!=''&&s.test($(obj).val())){
		if($(".discountInfos").length!=0){
			$(".discountInfos").each(function(){
				$(this).val($(obj).val());
				zkchange($(this).parents("tr").find(".goodsPrices").val(),this);
			});
		}
	}
}


function yichus(fixed){
	if($(".goodsPrices").length!=0){
		$(".goodsPrices").each(function(){
			  var obp = $(this).parents("tr").find("input[name='discountInfo']").val();
			  var b = ($(this).val()*obp).toFixed(fixed);
			  if(fixed==1){
		 	     $(this).parents("tr").find("input[name='discountFlag']").val(1); 
			  }else if(fixed==0){
		 	     $(this).parents("tr").find("input[name='discountFlag']").val(2); 
			  }
		 	  $(this).parents("tr").find(".zhj").text(b); 
		 	  $(this).parents("tr").find("input[name='discountPrice']").val(b);
		});
	}
}