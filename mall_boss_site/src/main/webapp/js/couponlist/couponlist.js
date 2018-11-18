$(function(){
    var flag="";
    $('.copy').zclip({
        path:'js/ZeroClipboard.swf',
        copy:function(){
            var couponId=$(this).attr("attr-id");
            $.ajax({
                url:'getCouponNoByCouponId.htm?couponId='+couponId,
                async : false,
                success:function(data) {
                    flag=data;
                }
            });
                return flag;
        },
        afterCopy:function(){
            if(flag=='0'){
              showTipAlert("优惠券已过期!");
            }else{
                showTipAlert("复制成功!");
            }
        }
    });

    /* 表单项的值点击后转换为可编辑状态 */
    $('.form_value').click(function(){
        var formItem = $(this);
        if(!$('.form_btns').is(':visible')) {
            formItem.parent().addClass('form_open');
            $('.form_btns').show();
            $('.form_btns').css({
                'left': formItem.next().offset().left + 70 + 'px',
                'top': formItem.next().offset().top - 30 + 'px'
            });
            $('.form_sure,.form_cancel').click(function () {
                $('.form_btns').hide();
                formItem.parent().removeClass('form_open');
            });
        }
    });

    /* 富文本编辑框 */
    $('.summernote').summernote({
        height: 300,
        tabsize: 2,
        lang: 'zh-CN'
    });

    /* 选择规格值 */
    $('.spec_set input').change(function(){
        if($(this).is(':checked')){
            $(this).parent().parent().next().slideDown('fast');
        }
        else {
            $(this).parent().parent().next().slideUp('fast');
        }
    });

    /* 下面是表单里面的填写项提示相关的 */
    $('.xiaoshoujia').popover({
        content : '此价格只用于显示，以商品定价为商品销售价',
        trigger : 'hover'
    });

    /* 点击搜索按钮 */
    $("#search-button").button().click(function(){
        if($("#search_form").valid()){
            $('#search_form').submit();
        }
    });

    /* 点击批量删除按钮 */
    $("#batch-button").button().click(function(){
        showDeleteBatchConfirmAlert("delete_form","couponId");
    });
});

function deleteSingleCoupon(couponId) {
    showDeleteOneConfirmAlert("delcoupon.htm?couponId="+couponId+'&CSRFToken='+$("#csrf_token").val());
}


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
    '            </div>'+'<input type="text" id="content"/>'+
    '           <div class="modal-footer">'+
    '             <button type="button" class="btn btn-primary"  onclick="copymethord()">复制</button>'+
    '   </div>'+
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
                $("#content").val(data);
                $(this).attr("attr_value",data);
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
                    $("#couponCount").text(coupon.couponCount);
                    $("#couponGetNo").text(coupon.couponGetNo);
                    $("#couponName").text(notNull(coupon.couponName));
                    $("#couponRemark").text(notNull(coupon.couponRemark));
                    if(coupon.couponRulesType == '1'){
                        $("#couponType").text("直降");
                    }else if(coupon.couponRulesType == '2'){
                        $("#couponType").text("满减");
                    }
                    for(var i=0;i<data.levelNamelist.length;i++) {
                        list = list+'  '+data.levelNamelist[i].levelName;
                    }
                    $("#couponLelvel").text(list );
                    $("#couponStartTime").text(timeObject(coupon.couponStartTime));
                    $("#couponEndTime").text(timeObject(coupon.couponEndTime));
                    $("#createTime").text(timeObject(coupon.createTime));
                    $("#modifyTime").text(timeObject(coupon.modifyTime));
                    if(coupon.couponRulesType=='1') {
                        $("#couponRulesType").text("直降金额："+coupon.couponStraightDown.downPrice);
                    }else if(coupon.couponRulesType=='2') {
                        $("#couponRulesType").text("满"+coupon.couponFullReduction.fullPrice+"减"+coupon.couponFullReduction.reductionPrice);
                    }
                }
                $("#couponScope").html("");
                $("#couponPage").html("");
                var str ="";
                var strpage = "";
                //计算页数

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
                //计算页数
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

                //计算页数
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
                strpage +='<span class="disabled"> 上一页 </span>';
                /*for(var i=2;i<=num;i++){
                    strpage +='<a href="javascript:;" onclick="pagecoupons('+i+','+num+')">'+i+' </a>';
                }*/
                 if(start>2){
              	     strpage +='<a href="javascript:;" onclick="pagecoupons(1,'+parseInt(num)+')">1</a>';
                     strpage +='<span class="current">...</span>';
                  }
                  for(var i=start;i<=end;i++){
                  	if(1==i){
                  		 strpage +='<span class="current">'+i+' </span>';
                  	}else{
                  		strpage +='<a href="javascript:;" onclick="pagecoupons('+i+','+parseInt(num)+')">'+i+' </a>';
                  	}
                  }
                  if(num>end+1){
                  	 strpage +='<span class="current">...</span>';
                	    strpage +='<a href="javascript:;" onclick="pagecoupons('+parseInt(num)+','+parseInt(num)+')">'+parseInt(num)+'</a>';
                  }
                if(num>1){
                    strpage+='<a href="javascript:;" onclick="pagecoupons(2,'+num+')">下一页 </a>';
                }else{
                    strpage+='<span class="disabled"> 下一页 </span>';
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
                 if(data.pageBean != "") {
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
                     strpage +='<span class="disabled"> 上一页 </span>';
                     if(start>2){
                  	     strpage +='<a href="javascript:;" onclick="pagecouponsno(1,'+parseInt(num)+')">1</a>';
                         strpage +='<span class="current">...</span>';
                      }
                      for(var i=start;i<=end;i++){
                      	if(1==i){
                      		 strpage +='<span class="current">'+i+' </span>';
                      	}else{
                      		strpage +='<a href="javascript:;" onclick="pagecouponsno('+i+','+parseInt(num)+')">'+i+' </a>';
                      	}
                      }
                      
                      if(num>end+1){
                      	 strpage +='<span class="current">...</span>';
                    	    strpage +='<a href="javascript:;" onclick="pagecouponsno('+parseInt(num)+','+parseInt(num)+')">'+parseInt(num)+'</a>';
                      }
                    if(num>1){
                        strpage+='<a href="javascript:;" onclick="pagecouponsno(2,'+num+')">下一页 </a>';
                    }else{
                        strpage+='<span class="disabled"> 下一页 </span>';
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
                strpagec +='<span class="disabled"> 上一页 </span>';
                if(start>2){
                	strpagec +='<a href="javascript:;" onclick="pageungotcouponsno(1,'+parseInt(num)+')">1</a>';
                	strpagec +='<span class="current">...</span>';
                 }
                 for(var i=start;i<=end;i++){
                 	if(1==i){
                 		strpagec +='<span class="current">'+i+' </span>';
                 	}else{
                 		strpagec +='<a href="javascript:;" onclick="pageungotcouponsno('+i+','+parseInt(num)+')">'+i+' </a>';
                 	}
                 }
                 if(num>end+1){
                	 strpagec +='<span class="current">...</span>';
                	 strpagec +='<a href="javascript:;" onclick="pageungotcouponsno('+parseInt(num)+','+parseInt(num)+')">'+parseInt(num)+'</a>';
                 }
               if(num>1){
            	   strpagec+='<a href="javascript:;" onclick="pageungotcouponsno(2,'+num+')">下一页 </a>';
               }else{
            	   strpagec+='<span class="disabled"> 下一页 </span>';
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
                strpageg +='<span class="disabled"> 上一页 </span>';
                if(start>2){
                	strpageg +='<a href="javascript:;" onclick="pagegotcouponsno(1,'+parseInt(num)+')">1</a>';
                	strpageg +='<span class="current">...</span>';
                 }
                 for(var i=start;i<=end;i++){
                 	if(1==i){
                 		strpageg +='<span class="current">'+i+' </span>';
                 	}else{
                 		strpageg +='<a href="javascript:;" onclick="pagegotcouponsno('+i+','+parseInt(num)+')">'+i+' </a>';
                 	}
                 }
                 
                 if(num>end+1){
                	 strpageg +='<span class="current">...</span>';
                	 strpageg +='<a href="javascript:;" onclick="pagegotcouponsno('+parseInt(num)+','+parseInt(num)+')">'+parseInt(num)+'</a>';
                 }
               if(num>1){
            	   strpageg+='<a href="javascript:;" onclick="pagegotcouponsno(2,'+num+')">下一页 </a>';
               }else{
            	   strpageg+='<span class="disabled"> 下一页 </span>';
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
                strpagey +='<span class="disabled"> 上一页 </span>';
                
                if(start>2){
                	strpagey +='<a href="javascript:;" onclick="pageusedcouponsno(1,'+parseInt(num)+')">1</a>';
                	strpagey +='<span class="current">...</span>';
                }
                for(var i=start;i<=end;i++){
                	if(1==i){
                		strpagey +='<span class="current">'+i+' </span>';
                	}else{
                		strpagey +='<a href="javascript:;" onclick="pageusedcouponsno('+i+','+parseInt(num)+')">'+i+' </a>';
                	}
                }
                
                if(num>end+1){
                	strpagey +='<span class="current">...</span>';
                	strpagey +='<a href="javascript:;" onclick="pageusedcouponsno('+parseInt(num)+','+parseInt(num)+')">'+parseInt(num)+'</a>';
                }
              if(num>1){
            	  strpagey+='<a href="javascript:;" onclick="pageusedcouponsno(2,'+num+')">下一页 </a>';
              }else{
            	  strpagey+='<span class="disabled"> 下一页 </span>';
              }
                $("#couponUsedPage").html(strpagey);
            }
            $('#scanCouponNums').modal('show');
        }
    });
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

function pageRows(obj,pageSize){
    //计算页数
    var num = 0;
    if(obj.length<=pageSize){
        num =1;
    }else if(obj.length%pageSize == 0){
        num = obj.length/pageSize;
    }else{
        num = obj.length/pageSize+1;
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
        strpage +='<a href="javascript:;" onclick="pagecoupons('+(pageNo-1)+','+rows+')"> 上一页 </a>';
    }else{
        strpage +='<span class="disabled"> 上一页 </span>';
    }
    if(start>2){
	   strpage +='<a href="javascript:;" onclick="pagecoupons(1,'+parseInt(rows)+')">1</a>';
       strpage +='<span class="current">...</span>';
    }
    for(var i=start;i<=end;i++){
    	if(pageNo==i){
    		 strpage +='<span class="current">'+i+' </span>';
    	}else{
    		strpage +='<a href="javascript:;" onclick="pagecoupons('+i+','+parseInt(rows)+')">'+i+' </a>';
    	}
    }
    
    if(rows>end+1){
    	 strpage +='<span class="current">...</span>';
  	     strpage +='<a href="javascript:;" onclick="pagecoupons('+parseInt(rows)+','+parseInt(rows)+')">'+parseInt(rows)+'</a>';
    }
    
    if(pageNo<rows){
        strpage+='<a href="javascript:;" onclick="pagecoupons('+(pageNo+1)+','+rows+')"> 下一页 </a>';
    }else{
        strpage+='<span class="disabled"> 下一页 </span>';
    }
    $("#couponPage").html(strpage);

    $("#couponScope tr").attr("class","couponScope");
    $("#couponsnum"+(pageNo*3-1)).attr("class","");
    $("#couponsnum"+(pageNo*3-2)).attr("class","");
    $("#couponsnum"+(pageNo*3-3)).attr("class","");
}

function pagecouponsno(pageNo,rows) {
	var start = parseInt(getStart(pageNo,rows));
	var end = parseInt(getEnd(pageNo,rows));
    $("#couponNoPage").html("");
    var strpage = "";
    if(pageNo>1){
        strpage +='<a href="javascript:;" onclick="pagecouponsno('+(pageNo-1)+','+rows+')"> 上一页 </a>';
    }else{
        strpage +='<span class="disabled"> 上一页 </span>';
    }
    if(start>2){
	   strpage +='<a href="javascript:;" onclick="pagecouponsno(1,'+parseInt(rows)+')">1</a>';
       strpage +='<span class="current">...</span>';
    }
    for(var i=start;i<=end;i++){
    	if(pageNo==i){
    		 strpage +='<span class="current">'+i+' </span>';
    	}else{
    		strpage +='<a href="javascript:;" onclick="pagecouponsno('+i+','+parseInt(rows)+')">'+i+' </a>';
    	}
    }
    
    if(rows>end+1){
    	 strpage +='<span class="current">...</span>';
  	     strpage +='<a href="javascript:;" onclick="pagecouponsno('+parseInt(rows)+','+parseInt(rows)+')">'+parseInt(rows)+'</a>';
    }
    
    if(pageNo<rows){
        strpage+='<a href="javascript:;" onclick="pagecouponsno('+(pageNo+1)+','+rows+')"> 下一页 </a>';
    }else{
        strpage+='<span class="disabled"> 下一页 </span>';
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
        strpage +='<a href="javascript:;" onclick="pageungotcouponsno('+(pageNo-1)+','+rows+')"> 上一页 </a>';
    }else{
        strpage +='<span class="disabled"> 上一页 </span>';
    }
    if(start>2){
	   strpage +='<a href="javascript:;" onclick="pageungotcouponsno(1,'+parseInt(rows)+')">1</a>';
       strpage +='<span class="current">...</span>';
    }
    for(var i=start;i<=end;i++){
    	if(pageNo==i){
    		 strpage +='<span class="current">'+i+' </span>';
    	}else{
    		strpage +='<a href="javascript:;" onclick="pageungotcouponsno('+i+','+parseInt(rows)+')">'+i+' </a>';
    	}
    }
    
    if(rows>end+1){
    	 strpage +='<span class="current">...</span>';
  	     strpage +='<a href="javascript:;" onclick="pageungotcouponsno('+parseInt(rows)+','+parseInt(rows)+')">'+parseInt(rows)+'</a>';
    }
    
    if(pageNo<rows){
        strpage+='<a href="javascript:;" onclick="pageungotcouponsno('+(pageNo+1)+','+rows+')"> 下一页 </a>';
    }else{
        strpage+='<span class="disabled"> 下一页 </span>';
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
        strpage +='<a href="javascript:;" onclick="pagegotcouponsno('+(pageNo-1)+','+rows+')"> 上一页 </a>';
    }else{
        strpage +='<span class="disabled"> 上一页 </span>';
    }
    if(start>2){
	   strpage +='<a href="javascript:;" onclick="pagegotcouponsno(1,'+parseInt(rows)+')">1</a>';
       strpage +='<span class="current">...</span>';
    }
    for(var i=start;i<=end;i++){
    	if(pageNo==i){
    		 strpage +='<span class="current">'+i+' </span>';
    	}else{
    		strpage +='<a href="javascript:;" onclick="pagegotcouponsno('+i+','+parseInt(rows)+')">'+i+' </a>';
    	}
    }
    
    if(rows>end+1){
    	 strpage +='<span class="current">...</span>';
  	     strpage +='<a href="javascript:;" onclick="pagegotcouponsno('+parseInt(rows)+','+parseInt(rows)+')">'+parseInt(rows)+'</a>';
    }
    
    if(pageNo<rows){
        strpage+='<a href="javascript:;" onclick="pagegotcouponsno('+(pageNo+1)+','+rows+')"> 下一页 </a>';
    }else{
        strpage+='<span class="disabled"> 下一页 </span>';
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
        strpage +='<a href="javascript:;" onclick="pageusedcouponsno('+(pageNo-1)+','+rows+')"> 上一页 </a>';
    }else{
        strpage +='<span class="disabled"> 上一页 </span>';
    }
    if(start>2){
	   strpage +='<a href="javascript:;" onclick="pageusedcouponsno(1,'+parseInt(rows)+')">1</a>';
       strpage +='<span class="current">...</span>';
    }
    for(var i=start;i<=end;i++){
    	if(pageNo==i){
    		 strpage +='<span class="current">'+i+' </span>';
    	}else{
    		strpage +='<a href="javascript:void(0);" onclick="pageusedcouponsno('+i+','+parseInt(rows)+')">'+i+' </a>';
    	}
    }
    
    if(rows>end+1){
    	 strpage +='<span class="current">...</span>';
  	     strpage +='<a href="javascript:void(0);" onclick="pageusedcouponsno('+parseInt(rows)+','+parseInt(rows)+')">'+parseInt(rows)+'</a>';
    }
    
    if(pageNo<rows){
        strpage+='<a href="javascript:void(0);" onclick="pageusedcouponsno('+(pageNo+1)+','+rows+')"> 下一页 </a>';
    }else{
        strpage+='<span class="disabled"> 下一页 </span>';
    }
    $("#couponUsedPage").html(strpage);
    $("#couponUsed tr").attr("class","couponUsed");
    $("#couponsusednum"+(pageNo*10-1)).attr("class","");
    $("#couponsusednum"+(pageNo*10-2)).attr("class","");
    $("#couponsusednum"+(pageNo*10-3)).attr("class","");
    $("#couponsusednum"+(pageNo*10-4)).attr("class","");
    $("#couponsusednum"+(pageNo*10-5)).attr("class","");
    $("#couponsusednum"+(pageNo*20-6)).attr("class","");
    $("#couponsusednum"+(pageNo*10-7)).attr("class","");
    $("#couponsusednum"+(pageNo*10-8)).attr("class","");
    $("#couponsusednum"+(pageNo*10-9)).attr("class","");
    $("#couponsusednum"+(pageNo*10-10)).attr("class","");
}


// 数字 15
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
