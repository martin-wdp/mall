$(function(){
	$("#cat_btn").click(function(){
		$('#addSignCate').modal('show');
//		$("#sel_all").prop("checked",false);
		 $.ajax({
	         type: 'post',
	         url:'queryallgoodcate.htm',
	         async:true,
	         success: function(data) {
	        	 //var optgroup = "<option value=''>请选择</option>";
	        	 var optgroup = "";
	     		for( var i=0; i<data.length; i++){
	     			var cat=data[i];
	     			if(cat.catParentId == 0){
	     				var options = "";
		     			for(var j=0; j<data.length; j++){
		     				var subcat=data[j];
		     				if(subcat.catParentId == cat.catId){
			     				options +=  "<option value='"+subcat.catId+"'>"+subcat.catName+"</option>";
			     			} 
		     			}
		     			optgroup += "<optgroup value='"+cat.catId+"' label='"+cat.catName+"'>"+options+"</optgroup>";
	     			}
	     		}
	     		$("#cate").html(optgroup);
	     		 changeCate();
	         }
	}); 	
});
		 
 $("#cate").change(function(){
		 //<tr> <td>1</td> <td>手机配件</td> <td>蓝牙耳机</td> <td>0%</td> <td><a class="blu_bt" href="javascript:;" onclick="delcate(603,this)">删除</a></td><input type="hidden" class="hide_cid" value="603"> </tr>

		  $("#belongcate").html("");
		 $(".bbb").html("");
         if( $("#cate option:selected").val()==''){
             return;
         }
		 //var cc='<label><input class="vm mr5 c_c" id="p1" value="'+$(this).find("option:selected").parent("optgroup").attr("value")+'" type="checkbox" />'+$(this).find("option:selected").parent("optgroup").attr("label")+'</label>';
		 //cc +='<label><input class="vm mr5 c_c" id="p2" value="'+$(this).val()+'" type="checkbox" />'+$(this).find("option:selected").text()+'</label>';
         //   一级和二级菜单不用显示
         //
			// $("#belongcate").append(cc);
		 var paramStr="catId="+$(this).val();
		 
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
	     			options +='<label class="checkbox-inline"><input class="c_c" value="'+cat.catId+'" type="checkbox" />'+cat.catName+'</label>';
	     		}
	     		
	     		$("#belongcate").append(options);
	     		 $(".c_c").click(function(){
	     			 var sc_id="",count=0;
	     			 $("#belongcate input").each(function(){
	     				 if($(this).prop("checked")){
	     					 sc_id += $(this).val()+"|";
	     					count++;
	     				 }
	     				/*if(!$(this).prop("checked")){
	     					$("#sel_all").prop("checked",false);
	     				}*/
	     			 });
	     			/* if(count == $("#belongcate input").length){
	     				$("#sel_all").prop("checked",true);
	     			 }*/
	     			$("#sc_id").val(sc_id);
	     			
	     			
	     			 $(".c_c").parent("label").each(function(){
	    				 if($(this).index()==0){         
	    					   
	    					 
	    					 var sc_id="",count=0;
	    	     			 $("#belongcate input").each(function(){
	    	     				 if($(this).prop("checked")){
	    	     					 sc_id += $(this).val()+"|";
	    	     					count++;
	    	     				 }
	    	     				/*if(!$(this).prop("checked")){
	    	     					$("#sel_all").prop("checked",false);
	    	     				}*/
	    	     			 });
	    	     			 /*if(count == $("#belongcate input").length){
	    	     				$("#sel_all").prop("checked",true);
	    	     			 }*/
	    	     			$("#sc_id").val(sc_id);
	    				 }else if($(this).index()==1){
	    					 $("#p1").prop("checked","checked")  ;
	    					 
	    					 var sc_id="",count=0;
	    	     			 $("#belongcate input").each(function(){
	    	     				 if($(this).prop("checked")){
	    	     					 sc_id += $(this).val()+"|";
	    	     					count++;
	    	     				 }
	    	     				/*if(!$(this).prop("checked")){
	    	     					$("#sel_all").prop("checked",false);
	    	     				}*/
	    	     			 });
	    	     			 /*if(count == $("#belongcate input").length){
	    	     				$("#sel_all").prop("checked",true);
	    	     			 }*/
	    	     			$("#sc_id").val(sc_id);
	    				 }else if($(this).index()>1){
	    					 $("#p1").prop("checked","checked");
	    					 $("#p2").prop("checked","checked");
	    					 
	    					 var sc_id="",count=0;
	    	     			 $("#belongcate input").each(function(){
	    	     				 if($(this).prop("checked")){ 
	    	     					 sc_id += $(this).val()+"|";
	    	     					count++;
	    	     				 }
	    	     				/*if(!$(this).prop("checked")){
	    	     					$("#sel_all").prop("checked",false);
	    	     				}*/
	    	     			 });
	    	     			/* if(count == $("#belongcate input").length){
	    	     				$("#sel_all").prop("checked",true);
	    	     			 }*/
	    	     			$("#sc_id").val(sc_id);
	    				 }
	    				 
	    			 });
	     		 });
	         }
		 });
		 
	 });
 
 $("#sel_all").click(function(){
		 var sc_id="";
//		 if($(this).prop("checked")){
			 $("#belongcate input").each(function(){
					if(! $(this).prop("checked")){
						$(this).prop("checked",true);	
					}
			 });
			 $("#belongcate input").each(function(){
				 sc_id += $(this).val()+"|";
			 });
			 $("#sc_id").val(sc_id);
		/* }else{
			 $("#belongcate input").each(function(){
					if($(this).prop("checked")){
						$(this).prop("checked",false);	
					}
			 });
			 $("#sc_id").val("");
		 }*/
	 });
	/*
	$(".ba_btn").click(function(){
		$(this).siblings(".ba_dia").show();
		  var config = {
			      '.chosen-select'           : {},
			      '.chosen-select-deselect'  : {allow_single_deselect:true},
			      '.chosen-select-no-single' : {disable_search_threshold:10},
			      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
			      '.chosen-select-width'     : {width:"95%"}
			    } 
	    for (var selector in config) {
	        $(selector).chosen(config[selector]);
	      }
	});    */
	
	/**$("#bra").change(function(){  
		alert($(this).val());
		$(".b_id").val($(this).val());
	});**/
});

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
				 var options = '<input type="hidden" class="hide_cid" name="thirdCateId" value="'+idarr[i]+'"/>';
				 $("#updateForm").append(options);
				
				/*if(p_cat ==""){
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
			        	var options = '<tr class="tableListTr"> <td>'+count+'</td> <td class="tc">'+p_cat.catName+'</td> <td class="tc">'+data.catName+'</td><td><button type="button" class="btn btn-default" onclick="delcate('+idarr[i]+',this)">删除</button><input type="hidden" class="hide_cid" name="thirdCateId" value="'+idarr[i]+'"/> </tr>';
			     		$(".catbo").append(options);
			         }
				 });*/
			}
		}
	}
	$("#belongcate").html("");
	$(".hasa").val(hasa);
	$("#updateForm").submit();
	$('#addSignCate').modal('hide');
	//$(".cat_dia").hide();
	//$("#sel_all").prop("checked",false);
}


function closeDia(){
	 $(".cat_dia").hide();
	 $("#belongcate").html("");
}
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
	//$("#sel_all").prop("checked",false);
}

function addBra(){
	var ids=$("#bra").val(),hasa=$(".hasb").val();
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
				        	var options = '<tr> <td>'+count+'</td> <td>'+data.brandName+'</td> <td>'+data.brandUrl+'</td> <td><img src="'+baseUrl+data.brandLogo+'" width="150" height="60"/></td> <td><a class="blu_bt" href="javascript:;" onclick="delBra('+ids+',this)">删除</a></td><input type="hidden" class="hide_bid" value="'+ids+'"/> </tr>';
				     		$(".bra_tb").append(options);
				         }
					 });
				}
			}
	}
	$(".hasb").val(hasa);
	$(".ba_dia").hide();
}

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





function changeCate(){

   	 $("#belongcate").html("");
     $(".bbb").html("");
    if( $("#cate option:selected").val()==''){
        return;
    }
		 var cc='<label class="checkbox-inline"><input id="p1" value="'+$("#cate").find("option:selected").parent("optgroup").attr("value")+'" type="checkbox" />'+$("#cate").find("option:selected").parent("optgroup").attr("label")+'</label>';
		 cc +='<label class="checkbox-inline"><input id="p2" value="'+$("#cate").val()+'" type="checkbox" />'+$("#cate").find("option:selected").text()+'</label>';
		
		
		//$("#belongcate").append(cc);
		 var paramStr="catId="+$("#cate").val();
		 $("#pc_id").val($("#cate").val());
		 $.ajax({  
	         type: 'post',
	         url:'querySonCateByCatId.htm',      
	         data:paramStr,
	         async:true,  
	         success: function(data) {
	        	var options = "";
	     		for( var i=0; i<data.length; i++){
	     			var cat=data[i];
	     			options +='<label class="checkbox-inline"><input class="c_c" value="'+cat.catId+'" type="checkbox" />'+cat.catName+'</label>';
	     		}
	     		
	     		$("#belongcate").append(options);
	     		 $(".c_c").click(function(){
	     			 var sc_id="",count=0;
	     			 $("#belongcate input").each(function(){
	     				 if($(this).prop("checked")){
	     					 sc_id += $(this).val()+"|";
	     					count++;
	     				 }
	     				/*if(!$("#cate").prop("checked")){
	     					$("#sel_all").prop("checked",false);
	     				}*/
	     			 });
	     			 /*if(count == $("#belongcate input").length){
	     				$("#sel_all").prop("checked",true);
	     			 }*/
	     			$("#sc_id").val(sc_id);
	     			 $(".c_c").parent("label").each(function(){
	    				 if($(this).index()==0){         
	    					 var sc_id="",count=0;
	    	     			 $("#belongcate input").each(function(){
	    	     				 if($(this).prop("checked")){
	    	     					 sc_id += $(this).val()+"|";
	    	     					count++;
	    	     				 }
	    	     				/*if(!$("#cate").prop("checked")){
	    	     					$("#sel_all").prop("checked",false);
	    	     				}*/
	    	     			 });
	    	     			 /*if(count == $("#belongcate input").length){
	    	     				$("#sel_all").prop("checked",true);
	    	     			 }*/
	    	     			$("#sc_id").val(sc_id);
	    				 }else if($(this).index()==1){
	    					 $("#p1").prop("checked","checked")  ;
	    					 
	    					 var sc_id="",count=0;
	    	     			 $("#belongcate input").each(function(){
	    	     				 if($(this).prop("checked")){
	    	     					 sc_id += $(this).val()+"|";
	    	     					count++;
	    	     				 }
	    	     				/*if(!$("#cate").prop("checked")){
	    	     					$("#sel_all").prop("checked",false);
	    	     				}*/
	    	     			 });
	    	     			 /*if(count == $("#belongcate input").length){
	    	     				$("#sel_all").prop("checked",true);
	    	     			 }*/
	    	     			$("#sc_id").val(sc_id);
	    				 }else if($(this).index()>1){
	    					 $("#p1").prop("checked","checked");
	    					 $("#p2").prop("checked","checked");
	    					 
	    					 var sc_id="",count=0;
	    	     			 $("#belongcate input").each(function(){
	    	     				 if($(this).prop("checked")){ 
	    	     					 sc_id += $(this).val()+"|";
	    	     					count++;
	    	     				 }
	    	     				/*if(!$(this).prop("checked")){
	    	     					$("#sel_all").prop("checked",false);
	    	     				}*/
	    	     			 });
	    	     			 /*if(count == $("#belongcate input").length){
	    	     				$("#sel_all").prop("checked",true);
	    	     			 }*/
	    	     			$("#sc_id").val(sc_id);
	    				 }
	    			 });
	     		 });
	         }
		 });
		 
	 }
	 
	 
function delstorecate(cateId){
	$.ajax({
		url:'newdeletesellerinfocate.htm?thirdId='+$("#storeId").val()+'&cateId='+cateId,
		success:function(data){
			location.reload();
		}	
	});
}
	 
	 
	 
	 

	
	


	 
