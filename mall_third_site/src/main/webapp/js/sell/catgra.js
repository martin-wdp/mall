
$(function(){
    /*主营类目*/
    $(".btn-sm-man").click(function(){
        $.ajax({
            type: 'post',
            url:'queryallgoodcate.htm',
            async:true,
            success: function(data) {
                var optgroup = "<option>--请选择--</option>";
                for( var i=0; i<data.length; i++){
                    var cat=data[i];
                    if(cat.catParentId == 0){
                        //清空上次的数据
                        var options = null;
                        for(var j=0; j<data.length; j++){
                            var subcat=data[j];
                            if(subcat.catParentId == cat.catId){
                                options +=  "<option value='"+subcat.catId+"'>"+subcat.catName+"</option>";
                            }
                        }
                        optgroup += "<optgroup label='"+cat.catName+"'>"+options+"</optgroup>";
                    }
                }
                $("#cate").html(optgroup);
                $("#addCategory").modal('show');
            }
        });
    });



    /*首选类目*/
	 $("#cate").change(function(){
		 var paramStr="catId="+$(this).val();
		 $(".check-cont").html("");
		 $("#pc_id").val($(this).val());
		 $.ajax({
	         type: 'post',
	         url:'querySonCateByCatId.htm',
	         data:paramStr,
	         async:true,
	         success: function(data) {
	        	var options = "";
	     		for( var i=0; i<data.length; i++){
	     			var cat=data[i];
	     			options +='<label><input class="vm mr5 c_c input" value="'+cat.catId+'" type="checkbox" />'+cat.catName+'</label>';
	     		}
	     		$(".check-cont").append(options);
	     		 $(".c_c").click(function(){
	     			 var sc_id="",count=0;
	     			 $(".input").each(function(){
	     				 if($(this).prop("checked")){
	     					 sc_id += $(this).val()+"|";
	     					count++;
	     				 }
	     				if(!$(this).prop("checked")){
	     					$(".sel_all").prop("checked",false);
	     				}
	     			 });
	     			 if(count == $(".input").length){
	     				$(".sel_all").prop("checked",true);
	     			 }
	     			$("#sc_id").val(sc_id);
	     		 });
	         }
		 });
	 });

    /*全选*/
	 $(".sel_all").click(function(){
		 var sc_id="";
		 if($(this).prop("checked")){
			 $(".input").each(function(){
					if(! $(this).prop("checked")){
						$(this).prop("checked",true);	
					}
			 });
			 $(".input").each(function(){
				 sc_id += $(this).val()+"|";
			 });
			 $("#sc_id").val(sc_id);
		 }else{
			 $(".input").each(function(){
					if($(this).prop("checked")){
						$(this).prop("checked",false);	
					}
			 });
			 $("#sc_id").val("");
		 }
	 });
	 
	
	 
	 
	 //品牌添加
	$(".btn-sms").click(function(){
        $('#pinpaierror').html('')
        $('#chaxunpinpai').val('');
			$.ajax({
		        type: 'post',
		        url:'queryallbrand.htm',
		        async:true,
		        success: function(data) {
		        var options = "<option>请选择</option>";
		     	for( var i=0; i<data.length; i++){
		     		var cat=data[i];
		     		options +=  "<option value='"+cat.brandId+"'>"+cat.brandName+"</option>";
		     	}
		     	    $("#bra").html(options);
                    $("#mainBrands").modal('show');
                    $('select[data-live-search="true"]').select2();

                }
			});
	});
	
	$("#bra").change(function(){
		$(".b_id").val($(this).val());
	});
});

/*添加主营类目*/
function addCate(){
	var ids=$("#sc_id").val(),p_cat="",p_cid=$("#pc_id").val(),dataPstr="cateId="+p_cid,hasa=$(".hasa").val();
	var idarr=ids.split("|"),count=0;
	var hasarr=hasa.split("|");
	count=hasarr.length-1;
	for (var i = 0; i < idarr.length; i++) {
		var flag = true;
		if(idarr[i] != ''){
			for (var j = 0; j < hasarr.length; j++) {
				if(hasarr[j] != ''){
					if(idarr[i] == hasarr[j]){
						flag= false;
						break;
					}
				}
			}
			if(flag){
				if(p_cat ==""){
					 $.ajax({
				         type: 'post',
				         url:'queryCateById.htm',
				         data:dataPstr,
				         async:false,
				         success: function(data) {
				        	 p_cat=data; 
				         }
					 });
				}
				count++;
				var paramStr="cateId="+idarr[i];
				 $.ajax({
			         type: 'post',
			         url:'queryCateById.htm',
			         data:paramStr,
			         async:false,
			         success: function(data) {
			        	 hasa+=idarr[i]+"|";
			        	var options = '<tr> <td>'+count+'</td> <td>'+p_cat.catName+'</td> <td>'+data.catName+'</td> <td>'+(data.catRate*100)+'%</td> <td><a class="blu_bt" href="javascript:;" onclick="delcate('+idarr[i]+',this)">删除</a></td><input type="hidden" class="hide_cid" value="'+idarr[i]+'"/> </tr>';
			     		$(".catbo").append(options);
			         }
				 });
			}
		}
	}
	$(".check-cont").html("");
	$(".hasa").val(hasa);
	$(".sel_all").prop("checked",false);
    $('.modal').modal('hide')
}

/*添加住经营类目关闭弹出框*/
function closeDia(){
	 $(".cat_dai").hide();
    $(".check-cont").html("");
}

/*关闭弹出窗*/
function closeBrDia(){
	$(".ba_dia").hide();
	$(".add_brand").hide();
}

function delcate(id,obj){
	$(obj).parent().parent().remove();
	$(".catbo tr").each(function(i){
		$(this).find("td").first().html(i+1);
	});
	var str="";
	$(".hide_cid").each(function(){
		if($(this).val() != ""){
			str+=$(this).val()+"|";
		}
	});
	$("#sc_id").val(str);
	$(".hasa").val(str);
	$(".sel_all").prop("checked",false);
}

	//根据关键字模糊查询品牌信息
function selectBraByName(){
     var chaxunpinpai = $('#chaxunpinpai').val();
        $.ajax({
            type: 'post',
            data:{brandName:chaxunpinpai},
            url:'queryallbrandbyName.htm',
            async:true,
            success: function(data) {
                if(data.length==0){
                    var options = "<option value=''>请选择</option>";
                    $("#bra").html(options);
                    $('#pinpaierror').html('没有对应的品牌');
                    $("#bra").html(options);
                }else{
                    $("#bra").html("");
                    var options = "";
                    for( var i=0; i<data.length; i++){
                        var cat=data[i];
                        options +=  "<option value='"+cat.brandId+"'>"+cat.brandName+"</option>";
                        //选中搜索出来的品牌信息
                        $(".b_id").val(cat.brandId);
                    }
                    $("#bra").html(options);
                }
            }
        });
	}

/*添加品牌*/
function addBra(){
	var ids=$(".b_id").val(),hasa=$(".hasb").val();
	if(hasa!="" && hasa.split("|").length == 6){
		dia(1);
	}else{
		var hasarr=hasa.split("|");
		count=hasarr.length-1;
			var flag = true;
			if(ids != ''){
				for (var j = 0; j < hasarr.length; j++) {
					if(hasarr[j] != ''){
						if(ids == hasarr[j]){
							flag= false;
							break;
						}
					}
				}
				if(flag){
					count++;
					var paramStr="brandId="+ids;
					 $.ajax({
				         type: 'get',
				         url:'queryBrandById.htm',
				         data:paramStr,
				         async:false,
				         success: function(data) {
				        	 hasa+=ids+"|";
				        	var options = '<tr> <td>'+count+'</td> <td>'+data.brandName+'</td> <td><img style="height: 40px" src="'+data.brandLogo+'" /></td> <td><a class="blu_bt" href="javascript:;" onclick="delBra('+ids+',this)">删除</a></td><input type="hidden" class="hide_bid" value="'+ids+'"/> </tr>';
				     		$(".bra_tb").append(options);
				         }
					 });
				}
			}
	}
	$(".hasb").val(hasa);
    $('.modal').modal('hide')
}

/*删除品牌*/
function delBra(id,obj){
	$(obj).parent().parent().remove();
	$(".bra_tb tr").each(function(i){
		$(this).find("td").first().html(i+1);
	});
	var str="";
	$(".hide_bid").each(function(){
		if($(this).val() != ""){
			str+=$(this).val()+"|";
		}
	});
	$(".hasb").val(str);
}


