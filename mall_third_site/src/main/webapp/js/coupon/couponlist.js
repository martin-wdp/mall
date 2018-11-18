/* 点击 获取链接  */
function getCouponAddress(couponId) {
    $("#coupon-confirm").remove();
    var couponConfirm =
    '<div class="modal fade" id="coupon-confirm" tabindex="-1" role="dialog">'+
    '   <div class="modal-dialog modal-lg">'+
    '        <div class="modal-content">'+
    '            <div class="modal-header">'+
    '               <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
    '               <h4 class="modal-title">领取链接</h4>'+
    '            </div>'+
    '            <div class="modal-body">'+
    '                <span id="coupon-tip"></span>'+
    '            </div>'+
    '        </div>'+
    '    </div>'+
    '</div>';
    $.ajax({
        url:'getCouponNoByCouponId.htm?couponId='+couponId,
        async : false,
        success:function(data) {
            if(data == "0"){
                $(document.body).append(couponConfirm);
                $("#coupon-tip").text("优惠券已过期");
                $('#coupon-confirm').modal('show');
            }else{
                $(document.body).append(couponConfirm);
                $("#coupon-tip").text(data);
                $('#coupon-confirm').modal('show');
            }

        }
    });
}


/* 优惠券详细信息列表 */
function getScanCoupons(couponId) {
    $.ajax({
        url:'searchCouponByIdAjax.htm?couponId='+couponId,
        async : false,
        success:function(data){
            if(data!="") {
                var coupon = data.coupon;
                var list = "";
                if(coupon != "") {
                    $("#couponName").text(notNull(coupon.couponName));
                    $("#couponRemark").text(notNull(coupon.couponRemark));
                    $("#couponPrice").text(notNull(coupon.reductionPrice));
                    if(coupon.couponRulesType == '3'){
                        $("#couponType").text("不受限");
                    }else if(coupon.couponRulesType == '2'){
                        $("#couponType").text("满"+coupon.couponFullReduction.fullPrice+"减"+coupon.couponFullReduction.reductionPrice);
                    }
                    for(var i=0;i<data.levelNamelist.length;i++) {
                        list = list+'  '+data.levelNamelist[i].levelName;
                    }
                    $("#couponLelvel").text(list );
                    $("#couponStartTime").text(timeObject(coupon.couponStartTime));
                    $("#couponEndTime").text(timeObject(coupon.couponEndTime));
                    $("#createTime").text(timeObject(coupon.createTime));
                    $("#coupouCount").text(notNull(coupon.couponCount)+"张");
                  //  $("#couponGetNo").text(notNull(coupon.modifyTime));
                }
                $("#couponScope").html("");
                $("#couponPage").html("");
                var str ="";
                var strpage = "";
                //计算页数（货品）
                if(data.skulist!=null) {

                    var num = pageRows(data.skulist, 3);
                    $("#couponTop").html('  <th width="80">图片</th>  <th width="150">货品编号</th> <th>货品名称</th>');
                    $.each(data.skulist, function (idx, item) {
                        if (idx < 3) {
                            str += '<tr id="couponsnum' + idx + '" class="">';
                        } else {
                            str += '<tr id="couponsnum' + idx + '" class="couponScope">';
                        }
                        str += '<td><img alt="" src="' + item.goodsInfoImgId;
                        str += '" height="50"></td>';
                        str += '<td>' + item.goodsInfoItemNo;
                        str += '</td><td>' + item.goodsInfoName;
                        str == '</td></tr>';
                    });
                }
                //计算页数（品牌）
                if(data.productbrand!=null){
                var num = pageRows(data.productbrand,3);

                    $("#couponTop").html('<th width="150">品牌编号</th> <th>品牌名称</th>');
                    $.each(data.productbrand, function (idx, item) {
                        if (idx < 3) {
                            str += '<tr id="couponsnum' + idx + '" class="">';
                        } else {
                            str += '<tr id="couponsnum' + idx + '" class="couponScope">';
                        }
                        str += '<td>' + item.brandId;
                        str += '</td><td>' + item.brandName;
                        str == '</td></tr>';
                    });
                }

                //计算页数（分类）

                if(data.productcate!=null){
                    var num = pageRows(data.productcate,3);
                    $("#couponTop").html('<th width="150">分类编号</th> <th>分类名称</th>');
                    $.each(data.productcate, function (idx, item) {
                        if (idx < 3) {
                            str += '<tr id="couponsnum' + idx + '" class="">';
                        } else {
                            str += '<tr id="couponsnum' + idx + '" class="couponScope">';
                        }
                        str += '<td>' + item.catId;
                        str += '</td><td>' + item.catName;
                        str == '</td></tr>';
                    });
                }
                $("#couponScope").html(str);
                var start = parseInt(getStart(1,num));
            	var end = parseInt(getEnd(1,num));
                strpage +='<li class="disabled"><a href="javascript:void(0);" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>';
                if(start>2){
             	     strpage +='<li class="active"><a href="javascript:;" onclick="pagecoupons(1,'+num+')">1</a></li>';
                     strpage +='<li class="active"><a href="javascript:void(0)">...</a></li>';
                 }
                 for(var i=start;i<=end;i++){
                 	if(1==i){
                 		 strpage +='<li class="active"><a href="javascript:void(0)">'+i+' </a></li>';
                 	}else{
                 		strpage +='<li><a href="javascript:void(0);" onclick="pagecoupons('+i+','+num+')">'+i+' </a></li>';
                 	}
                 }
                 
               if(num>end+1){
                 	 strpage +='<li class="active"><a href="javascript:void(0)">...</a></li>';
               	    strpage +='<li><a href="javascript:;" onclick="pagecoupons('+num+','+num+')">'+num+'</a></li>';
               }
               if(num>1){
                   strpage+='<li><a href="javascript:void(0);" aria-label="Next" onclick="pagecoupons(2,'+num+')"><span aria-hidden="true">&raquo;</span> </a></li>';
               }else{
                   strpage+='<li class="disabled"><a href="javascript:;" aria-label="Next"><span aria-hidden="true">&raquo;</span></li>';
               }
                $("#couponPage").html(strpage);
            }
            $('#scanCoupons').modal('show');
        }
    });
}

/* 查看券码列表 */
function getScanCouponNums(couponId) {
    $.ajax({
        url:'newselectcouponnolistajax.htm?couponId='+couponId,
        async : false,
        success:function(data){
            if(data != ""){
                 if(data.pageBean.length > 0) {
                     var str ="";
                     var strpage="";
                     $("#couponNoList").html("");
                     $("#couponNoPage").html("");
                     var num = pageRows(data.pageBean,10);
                     $.each(data.pageBean,function(idx,item){
                         if(idx<10){
                             str+='<tr id="couponsnonum'+idx+'" class="">';
                         }else{
                             str+='<tr id="couponsnonum'+idx+'" class="couponNoList">';
                         }
                         str +='<td>'+item.codeNo;
                         if(item.codeStatus == '0') {
                             str +='</td><td>可领取';
                         }else if(item.codeStatus == '1') {
                             str +='</td><td>已被领取';
                         }else if(item.codeStatus == '2') {
                             str +='</td><td>已经使用';
                         }else if(item.codeStatus == '3') {
                             str +='</td><td>已经作废';
                         }
                         if(item.codeGetTime == null) {
                             str +='</td><td>';
                         }else{
                             str +='</td><td>'+timeObject(item.codeGetTime);
                         }
                         if(item.customerName == null) {
                             str +='</td><td>';
                         }else{
                             str +='</td><td>'+item.customerName;
                         }
                         str =='</td></tr>';
                     });
                     $("#couponNoList").html(str);
                     var start = parseInt(getStart(1,num));
                 	var end = parseInt(getEnd(1,num));
                     strpage +='<li class="disabled"><a href="javascript:void(0);" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>';
                     if(start>2){
                     	strpage +='<a href="javascript:;" onclick="pagecouponsno(1,'+parseInt(num)+')">1</a>';
                     	strpage +='<li class="active"><a href="javascript:void(0)">...</a></li>';
                     }
                     for(var i=start;i<=end;i++){
                     	if(1==i){
                     		strpage +='<li class="active"><a href="javascript:void(0)">'+i+' </a></li>';
                     	}else{
                     		strpage +='<li><a href="javascript:void(0);" onclick="pagecouponsno('+i+','+num+')">'+i+' </a></li>';
                     	}
                     }
                     
                     if(num>end+1){
                     	strpage +='<li class="active"><a href="javascript:void(0)">...</a></li>';
                     	strpage +='<li><a href="javascript:void(0);" onclick="pagecouponsno('+num+','+num+')">'+num+' </a></li>';
                     }
                     
                     if(num>1){
                         strpage+='<li><a href="javascript:void(0);" aria-label="Next" onclick="pagecouponsno(2,'+num+')"><span aria-hidden="true">&raquo;</span> </a></li>';
                     }else{
                         strpage+='<li class="disabled"><a href="javascript:;" aria-label="Next"><span aria-hidden="true">&raquo;</span></li>';
                     }
                     $("#couponNoPage").html(strpage);
                 }
                //未领取券码
                var list = new Array();
                for(var i=0;i<data.pageBean.length;i++){
                    if(data.pageBean[i].codeStatus == '0'){
                        list.push(data.pageBean[i]);
                    }
                }
                var strc ="";
                var strpagec="";
                $("#couponUnGot").html("");
                $("#couponUnGotPage").html("");
                var num = pageRows(list,10);
                $.each(list,function(idx,item){
                    if(idx<10){
                        strc+='<tr id="couponsungotnum'+idx+'" class="">';
                    }else{
                        strc+='<tr id="couponsungotnum'+idx+'" class="couponUnGot">';
                    }
                    strc +='<td>'+item.codeNo;
                    strc +='</td><td>可领取';
                    if(item.codeGetTime == null) {
                        strc +='</td><td>';
                    }else{
                        strc +='</td><td>'+timeObject(item.codeGetTime);
                    }
                    if(item.customerName == null) {
                        strc +='</td><td>';
                    }else{
                        strc +='</td><td>'+item.customerName;
                    }
                    strc =='</td></tr>';
                });
                $("#couponUnGot").html(strc);
                var start = parseInt(getStart(1,num));
             	var end = parseInt(getEnd(1,num));
             		strpagec +='<li class="disabled"><a href="javascript:void(0);" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>';
                 if(start>2){
                	 strpagec +='<a href="javascript:;" onclick="pageungotcouponsno(1,'+parseInt(num)+')">1</a>';
                	 strpagec +='<li class="active"><a href="javascript:void(0)">...</a></li>';
                 }
                 for(var i=start;i<=end;i++){
                 	if(1==i){
                 		strpagec +='<li class="active"><a href="javascript:void(0)">'+i+'</a> </li>';
                 	}else{
                 		strpagec +='<li><a href="javascript:void(0);" onclick="pageungotcouponsno('+i+','+num+')">'+i+' </a></li>';
                 	}
                 }
                 
                 if(num>end+1){
                	 strpagec +='<li class="active"><a href="javascript:void(0)">...</a></li>';
                	 strpagec +='<li><a href="javascript:void(0);" onclick="pageungotcouponsno('+num+','+num+')">'+num+' </a></li>';
                 }
                 
                 if(num>1){
                	 strpagec+='<li><a href="javascript:void(0);" aria-label="Next" onclick="pageungotcouponsno(2,'+num+')"><span aria-hidden="true">&raquo;</span> </a></li>';
                 }else{
                	 strpagec+='<li class="disabled"><a href="javascript:;" aria-label="Next"><span aria-hidden="true">&raquo;</span></li>';
                 }
                $("#couponUnGotPage").html(strpagec);
                //已领取券码
                var listg = new Array();
                for(var i=0;i<data.pageBean.length;i++){
                    if(data.pageBean[i].codeStatus == '1'){
                        listg.push(data.pageBean[i]);
                    }
                }
                var strg ="";
                var strpageg="";
                $("#couponGot").html("");
                $("#couponGotPage").html("");
                var num = pageRows(listg,10);
                $.each(listg,function(idx,item){
                    if(idx<10){
                        strg+='<tr id="couponsgotnum'+idx+'" class="">';
                    }else{
                        strg+='<tr id="couponsgotnum'+idx+'" class="couponGot">';
                    }
                    strg +='<td>'+item.codeNo;
                    strg +='</td><td>已被领取';
                    if(item.codeGetTime == null) {
                        strg +='</td><td>';
                    }else{
                        strg +='</td><td>'+timeObject(item.codeGetTime);
                    }
                    if(item.customerName == null) {
                        strg +='</td><td>';
                    }else{
                        strg +='</td><td>'+item.customerName;
                    }
                    strg =='</td></tr>';
                });
                $("#couponGot").html(strg);
                var start = parseInt(getStart(1,num));
             	var end = parseInt(getEnd(1,num));
             	    strpageg +='<li class="disabled"><a href="javascript:void(0);" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>';
                 if(start>2){
                	 strpageg +='<a href="javascript:;" onclick="pagegotcouponsno(1,'+parseInt(num)+')">1</a>';
                	 strpageg +='<li class="active"><a href="javascript:void(0)">...</a></li>';
                 }
                 for(var i=start;i<=end;i++){
                 	if(1==i){
                 		strpageg +='<li class="active"><a href="javascript:void(0)">'+i+'</a> </li>';
                 	}else{
                 		strpageg +='<li><a href="javascript:void(0);" onclick="pagegotcouponsno('+i+','+num+')">'+i+' </a></li>';
                 	}
                 }
                 
                 if(num>end+1){
                	 strpageg +='<li class="active"><a href="javascript:void(0)">...</a></li>';
                	 strpageg +='<li><a href="javascript:void(0);" onclick="pagegotcouponsno('+num+','+num+')">'+num+' </a></li>';
                 }
                 
                 if(num>1){
                	 strpageg+='<li><a href="javascript:void(0);" aria-label="Next" onclick="pagegotcouponsno(2,'+num+')"><span aria-hidden="true">&raquo;</span> </a></li>';
                 }else{
                	 strpageg+='<li class="disabled"><a href="javascript:;" aria-label="Next"><span aria-hidden="true">&raquo;</span></li>';
                 }
                $("#couponGotPage").html(strpageg);
                //已使用券码
                var listy = new Array();
                for(var i=0;i<data.pageBean.length;i++){
                    if(data.pageBean[i].codeStatus == '2'){
                        listy.push(data.pageBean[i]);
                    }
                }
                var stry ="";
                var strpagey="";
                $("#couponUsed").html("");
                $("#couponUsedPage").html("");
                var num = pageRows(listy,10);
                $.each(listy,function(idx,item){
                    if(idx<10){
                        stry+='<tr id="couponsusednum'+idx+'" class="">';
                    }else{
                        stry+='<tr id="couponsusednum'+idx+'" class="couponUsed">';
                    }
                    stry +='<td>'+item.codeNo;
                    stry +='</td><td>已使用券码';
                    if(item.codeGetTime == null) {
                        stry +='</td><td>';
                    }else{
                        stry +='</td><td>'+timeObject(item.codeGetTime);
                    }
                    if(item.customerName == null) {
                        stry +='</td><td>';
                    }else{
                        stry +='</td><td>'+item.customerName;
                    }
                    stry =='</td></tr>';
                });
                $("#couponUsed").html(stry);
                var start = parseInt(getStart(1,num));
             	var end = parseInt(getEnd(1,num));
             	strpagey +='<li class="disabled"><a href="javascript:void(0);" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>';
                 if(start>2){
                	 strpagey +='<a href="javascript:;" onclick="pageusedcouponsno(1,'+parseInt(num)+')">1</a>';
                	 strpagey +='<li class="active"><a href="javascript:void(0)">...</a></li>';
                 }
                 for(var i=start;i<=end;i++){
                 	if(1==i){
                 		strpagey +='<li class="active"><a href="javascript:void(0)">'+i+'</a> </li>';
                 	}else{
                 		strpagey +='<li><a href="javascript:void(0);" onclick="pageusedcouponsno('+i+','+num+')">'+i+' </a></li>';
                 	}
                 }
                 
                 if(num>end+1){
                	 strpagey +='<li class="active"><a href="javascript:void(0)">...</a></li>';
                	 strpagey +='<li><a href="javascript:void(0);" onclick="pageusedcouponsno('+num+','+num+')">'+num+' </a></li>';
                 }
                 
                 if(num>1){
                	 strpagey+='<li><a href="javascript:void(0);" aria-label="Next" onclick="pageusedcouponsno(2,'+num+')"><span aria-hidden="true">&raquo;</span> </a></li>';
                 }else{
                	 strpagey+='<li class="disabled"><a href="javascript:;" aria-label="Next"><span aria-hidden="true">&raquo;</span></li>';
                 }
                $("#couponUsedPage").html(strpagey);
            }
            $('#scanCouponNums').modal('show');
        }
    });
}

function pageRows(obj,pageSize){
    //计算页数
    var num = 0;
    if(obj.length<=pageSize){
        num =1;
    }else if(obj.length%pageSize == 0){
        num = obj.length/pageSize;
    }else{
        num = Math.ceil(obj.length/pageSize);
    }
    return num;
}


//查看详情分页显示
function pagecoupons(pageNo,rows){
	var start = parseInt(getStart(pageNo,rows));
	var end = parseInt(getEnd(pageNo,rows));
    $("#couponPage").html("");
    var strpage = "";
    if(pageNo>1){
        strpage +='<li><a href="javascript:void(0);" aria-label="Previous" onclick="pagecoupons('+(pageNo-1)+','+rows+')"><span aria-hidden="true">&laquo;</span> </a></li>';
    }else{
        strpage +='<li class="disabled"><a href="javascript:void(0);" aria-label="Previous"><span aria-hidden="true">&laquo;</span> </a></li>';
    }
    
    if(start>2){
 	    strpage +='<li><a href="javascript:void(0);" onclick="pagecoupons(1,'+parseInt(rows)+')">1</a></li>';
        strpage +='<li class="active">...</li>';
     }
     for(var i=start;i<=end;i++){
     	if(pageNo==i){
     		 strpage +='<li class="active"><a href="javascript:void(0);"> '+i+'</a></li>';
     	}else{
     		strpage +='<li><a href="javascript:void(0);" onclick="pagecoupons('+i+','+rows+')">'+i+' </a></li>';
     	}
     }
     
     if(rows>end+1){
     	 strpage +='<li class="active">...</li>';
   	     strpage +='<li><a href="javascript:void(0);" onclick="pagecoupons('+rows+','+rows+')">'+rows+'</a></li>';
     }
    
    
    if(pageNo<rows){
    	strpage+='<li><a href="javascript:void(0);" aria-label="Next" onclick="pagecoupons('+(pageNo+1)+','+rows+')"><span aria-hidden="true">&raquo;</span> </a></li>';
    }else{
    	strpage+='<li class="disabled"><a href="javascript:;" aria-label="Next"><span aria-hidden="true">&raquo;</span></li>';
    }
    $("#couponPage").html(strpage);

    $("#couponScope tr").attr("class","couponScope");
    $("#couponsnum"+(pageNo*3-1)).attr("class","");
    $("#couponsnum"+(pageNo*3-2)).attr("class","");
    $("#couponsnum"+(pageNo*3-3)).attr("class","");
}


function pagecouponsno(pageNo,rows) {
	var start = parseInt(getStart(pageNo,rows));
	var end = parseInt(getEndpageNo(pageNo,rows));
    $("#couponNoPage").html("");
    var strpage = "";
    if(pageNo>1){
        strpage +='<li><a href="javascript:void(0);" aria-label="Previous" onclick="pagecouponsno('+(pageNo-1)+','+rows+')"><span aria-hidden="true">&laquo;</span> </a></li>';
    }else{
        strpage +='<li class="disabled"><a href="javascript:void(0);" aria-label="Previous"><span aria-hidden="true">&laquo;</span> </a></li>';
    }
    if(start>2){
 	   strpage +='<li><a href="javascript:void(0);" onclick="pagecouponsno(1,'+parseInt(rows)+')">1</a></li>';
        strpage +='<li class="active"><a href="javascript:void(0)">...</a></li>';
     }
    for(var i=start;i<=end;i++){
    	if(pageNo==i){
    		strpage +='<li class="active"><a href="javascript:void(0);"> '+i+'</a></li>';
    	}else{
    		strpage +='<li><a href="javascript:void();" onclick="pagecouponsno('+i+','+parseInt(rows)+')">'+i+' </a></li>';
    	}
    }
    
    if(rows>end+1){
    	 strpage +='<li class="active"><a href="javascript:void(0)">...</a></li>';
  	     strpage +='<li><a href="javascript:void(0);" onclick="pagecouponsno('+parseInt(rows)+','+parseInt(rows)+')">'+parseInt(rows)+'</a></li>';
    }
    if(pageNo<rows){
    	strpage+='<li><a href="javascript:void(0);" aria-label="Next" onclick="pagecouponsno('+(pageNo+1)+','+rows+')"><span aria-hidden="true">&raquo;</span> </a></li>';
    }else{
    	strpage+='<li class="disabled"><a href="javascript:;" aria-label="Next"><span aria-hidden="true">&raquo;</span></li>';
    }
    $("#couponNoPage").html(strpage);
    $("#couponNoList tr").attr("class","couponNoList");
    $("#couponsnonum"+(pageNo*10-1)).attr("class","");
    $("#couponsnonum"+(pageNo*10-2)).attr("class","");
    $("#couponsnonum"+(pageNo*10-3)).attr("class","");
    $("#couponsnonum"+(pageNo*10-4)).attr("class","");
    $("#couponsnonum"+(pageNo*10-5)).attr("class","");
    $("#couponsnonum"+(pageNo*10-6)).attr("class","");
    $("#couponsnonum"+(pageNo*10-7)).attr("class","");
    $("#couponsnonum"+(pageNo*10-8)).attr("class","");
    $("#couponsnonum"+(pageNo*10-9)).attr("class","");
    $("#couponsnonum"+(pageNo*10-10)).attr("class","");
}

/* 未领取券码分页 */
function pageungotcouponsno(pageNo,rows) {
	var start = parseInt(getStart(pageNo,rows));
	var end = parseInt(getEnd(pageNo,rows));
    $("#couponUnGotPage").html("");
    var strpage = "";
    if(pageNo>1){
        strpage +='<li><a href="javascript:void(0);" aria-label="Previous" onclick="pageungotcouponsno('+(pageNo-1)+','+rows+')"><span aria-hidden="true">&laquo;</span> </a></li>';
    }else{
        strpage +='<li class="disabled"><a href="javascript:void(0);" aria-label="Previous"><span aria-hidden="true">&laquo;</span> </a></li>';
    }
    if(start>2){
 	    strpage +='<li><a href="javascript:void(0);" onclick="pageungotcouponsno(1,'+parseInt(rows)+')">1</a></li>';
        strpage +='<li class="active"><a href="javascript:void(0)">...</a></li>';
     }
    for(var i=start;i<=end;i++){
    	if(pageNo==i){
    		strpage +='<li class="active"><a href="javascript:void(0);"> '+i+'</a></li>';
    	}else{
    		strpage +='<li><a href="javascript:void();" onclick="pageungotcouponsno('+i+','+parseInt(rows)+')">'+i+' </a></li>';
    	}
    }
    
    if(rows>end+1){
    	 strpage +='<li class="active"><a href="javascript:void(0)">...</a></li>';
  	     strpage +='<li><a href="javascript:void(0);" onclick="pageungotcouponsno('+parseInt(rows)+','+parseInt(rows)+')">'+parseInt(rows)+'</a></li>';
    }
    if(pageNo<rows){
    	strpage+='<li><a href="javascript:void(0);" aria-label="Next" onclick="pageungotcouponsno('+(pageNo+1)+','+rows+')"><span aria-hidden="true">&raquo;</span> </a></li>';
    }else{
    	strpage+='<li class="disabled"><a href="javascript:;" aria-label="Next"><span aria-hidden="true">&raquo;</span></li>';
    }
    $("#couponUnGotPage").html(strpage);
    $("#couponUnGot tr").attr("class","couponUnGot");
    $("#couponsungotnum"+(pageNo*10-1)).attr("class","");
    $("#couponsungotnum"+(pageNo*10-2)).attr("class","");
    $("#couponsungotnum"+(pageNo*10-3)).attr("class","");
    $("#couponsungotnum"+(pageNo*10-4)).attr("class","");
    $("#couponsungotnum"+(pageNo*10-5)).attr("class","");
    $("#couponsungotnum"+(pageNo*10-6)).attr("class","");
    $("#couponsungotnum"+(pageNo*10-7)).attr("class","");
    $("#couponsungotnum"+(pageNo*10-8)).attr("class","");
    $("#couponsungotnum"+(pageNo*10-9)).attr("class","");
    $("#couponsungotnum"+(pageNo*10-10)).attr("class","");
}

/* 已领取券码分页 */
function pagegotcouponsno(pageNo,rows) {
	var start = parseInt(getStart(pageNo,rows));
	var end = parseInt(getEnd(pageNo,rows));
    $("#couponGotPage").html("");
    var strpage = "";
    if(pageNo>1){
        strpage +='<li><a href="javascript:void(0);" aria-label="Previous" onclick="pagegotcouponsno('+(pageNo-1)+','+rows+')"><span aria-hidden="true">&laquo;</span> </a></li>';
    }else{
        strpage +='<li class="disabled"><a href="javascript:void(0);" aria-label="Previous"><span aria-hidden="true">&laquo;</span> </a></li>';
    }
    if(start>2){
 	    strpage +='<li><a href="javascript:void(0);" onclick="pagegotcouponsno(1,'+parseInt(rows)+')">1</a></li>';
        strpage +='<li class="active"><a href="javascript:void(0);>...</a></li>';
     }
    for(var i=start;i<=end;i++){
    	if(pageNo==i){
    		strpage +='<li class="active"><a href="javascript:void(0);"> '+i+'</a></li>';
    	}else{
    		strpage +='<li><a href="javascript:void();" onclick="pagegotcouponsno('+i+','+parseInt(rows)+')">'+i+' </a></li>';
    	}
    }
    
    if(rows>end+1){
    	 strpage +='<li class="active"><a href="javascript:void(0)">...</a></li>';
  	     strpage +='<li><a href="javascript:void(0);" onclick="pagegotcouponsno('+parseInt(rows)+','+parseInt(rows)+')">'+parseInt(rows)+'</a></li>';
    }
    if(pageNo<rows){
    	strpage+='<li><a href="javascript:void(0);" aria-label="Next" onclick="pagegotcouponsno('+(pageNo+1)+','+rows+')"><span aria-hidden="true">&raquo;</span> </a></li>';
    }else{
    	strpage+='<li class="disabled"><a href="javascript:;" aria-label="Next"><span aria-hidden="true">&raquo;</span></li>';
    }
    $("#couponGotPage").html(strpage);
    $("#couponGot tr").attr("class","couponGot");
    $("#couponsgotnum"+(pageNo*10-1)).attr("class","");
    $("#couponsgotnum"+(pageNo*10-2)).attr("class","");
    $("#couponsgotnum"+(pageNo*10-3)).attr("class","");
    $("#couponsgotnum"+(pageNo*10-4)).attr("class","");
    $("#couponsgotnum"+(pageNo*10-5)).attr("class","");
    $("#couponsgotnum"+(pageNo*10-6)).attr("class","");
    $("#couponsgotnum"+(pageNo*10-7)).attr("class","");
    $("#couponsgotnum"+(pageNo*10-8)).attr("class","");
    $("#couponsgotnum"+(pageNo*10-9)).attr("class","");
    $("#couponsgotnum"+(pageNo*10-10)).attr("class","");
}

/* 已使用券码分页 */
function pageusedcouponsno(pageNo,rows) {
	var start = parseInt(getStart(pageNo,rows));
	var end = parseInt(getEnd(pageNo,rows));
    $("#couponUsedPage").html("");
    var strpage = "";
    if(pageNo>1){
        strpage +='<li><a href="javascript:void(0);" aria-label="Previous" onclick="pageusedcouponsno('+(pageNo-1)+','+rows+')"><span aria-hidden="true">&laquo;</span> </a></li>';
    }else{
        strpage +='<li class="disabled"><a href="javascript:void(0);" aria-label="Previous"><span aria-hidden="true">&laquo;</span> </a></li>';
    }
    if(start>2){
 	   strpage +='<li><a href="javascript:void(0);" onclick="pageusedcouponsno(1,'+parseInt(rows)+')">1</a></li>';
        strpage +='<li class="active"><a href="javascript:void(0)">...</a></li>';
     }
    for(var i=start;i<=end;i++){
    	if(pageNo==i){
    		strpage +='<li class="active"><a href="javascript:void(0);"> '+i+'</a></li>';
    	}else{
    		strpage +='<li><a href="javascript:void();" onclick="pageusedcouponsno('+i+','+parseInt(rows)+')">'+i+' </a></li>';
    	}
    }
    
    if(rows>end+1){
    	 strpage +='<li class="active"><a href="javascript:void(0)">...</a></li>';
  	     strpage +='<li><a href="javascript:void(0);" onclick="pageusedcouponsno('+parseInt(rows)+','+parseInt(rows)+')">'+parseInt(rows)+'</a></li>';
    }
    if(pageNo<rows){
    	strpage+='<li><a href="javascript:void(0);" aria-label="Next" onclick="pageusedcouponsno('+(pageNo+1)+','+rows+')"><span aria-hidden="true">&raquo;</span> </a></li>';
    }else{
    	strpage+='<li class="disabled"><a href="javascript:;" aria-label="Next"><span aria-hidden="true">&raquo;</span></li>';
    }
    $("#couponUsedPage").html(strpage);
    $("#couponUsed tr").attr("class","couponUsed");
    $("#couponsusednum"+(pageNo*10-1)).attr("class","");
    $("#couponsusednum"+(pageNo*10-2)).attr("class","");
    $("#couponsusednum"+(pageNo*10-3)).attr("class","");
    $("#couponsusednum"+(pageNo*10-4)).attr("class","");
    $("#couponsusednum"+(pageNo*10-5)).attr("class","");
    $("#couponsusednum"+(pageNo*10-6)).attr("class","");
    $("#couponsusednum"+(pageNo*10-7)).attr("class","");
    $("#couponsusednum"+(pageNo*10-8)).attr("class","");
    $("#couponsusednum"+(pageNo*10-9)).attr("class","");
    $("#couponsusednum"+(pageNo*10-10)).attr("class","");
}


//判断数据是否为空为空返回“”
function notNull(obj){
    if(obj != null && obj != undefined){
        return obj;
    }else{
        return "";
    }
}

//转换时间格式
function timeObject(obj){
    var date = "/Date(" +notNull(obj)+")/";
    var time = new Date(parseInt(date.replace("/Date(", "").replace(")/", ""), 10));
    var result = time.getFullYear() + "-" + (time.getMonth() + 1 < 10 ? "0" + (time.getMonth() + 1) : time.getMonth() + 1) + "-" + (time.getDate() < 10 ? "0" + time.getDate() : time.getDate()) + " " + (time.getHours() < 10 ? "0" + time.getHours() : time.getHours()) + ":" + (time.getMinutes() < 10 ? "0" + time.getMinutes() : time.getMinutes())+ ":" + (time.getSeconds() < 10 ? "0" + time.getSeconds() : time.getSeconds());
    return result;
}


//数字 15
var FIFTEEN = 15;
// 数字 10
var TEN = 10;
// 数字 9
var  NINE = 9;
// 数字 5
var  FIVE = 5;
// 数字 4
var  FOUR = 4;
/**
 * 获取显示的开始页码数与 @see {@link com.ningpai.util.PageBean#getEnd()} 相对存在
 * 
 * @return 页码显示10个 {@link java.lang.Integer}
 */
function getStart(pageNo,totalPages) {
    if (pageNo / TEN < 1) {
        return 1;
    } else {
        if (pageNo == totalPages) {
            return pageNo - NINE;
        } else {
            if (totalPages - pageNo < TEN) {
                return totalPages - NINE;
            } else {
                return pageNo - FOUR;
            }
        }

    }
}



/**
 * 获取显示的结束页码数 {@link com.ningpai.util.PageBean#getStart()}
 *
 * @return 结束的页码数
 */
function  getEndpageNo(pageNo,totalPages) {
    if (pageNo / TEN < 1) {
        if (totalPages > TEN) {
            return TEN;
        } else {
            return totalPages;
        }
    } else {
        if (pageNo == totalPages) {
            return totalPages;
        } else {
            if (totalPages - pageNo < TEN) {
                return totalPages;
            } else {
                return pageNo + FIVE;
            }
        }
    }
}





/**
 * 获取显示的结束页码数 {@link com.ningpai.util.PageBean#getStart()}
 * 
 * @return 结束的页码数
 */
function  getEnd(pageNo,totalPages) {
    if (pageNo / TEN < 1) {
        if (totalPages > TEN) {
            return TEN;
        } else {
            return totalPages;
        }
    } else {
        if (pageNo == totalPages) {
            return totalPages;
        } else {
            if (totalPages - pageNo < TEN) {
            	if (totalPages - pageNo < TEN) {
                return totalPages;
            } else {
                return pageNo + FIVE;
                 }
            }
        }
    }
 }
