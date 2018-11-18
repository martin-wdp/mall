product_image = null;
$(function(){
	
	$("#dialog-about").hide();
	
	/* 加载第三方授权分类 */
	getAllGrandCateForThird();
	/* 加载品牌 */
	addGoodsBrand();
	/* 绑定事件 */
	bindEvent();
	
	
	
	
	var editor1 ;
	KindEditor.ready(function (K) 
	{
	    editor1 = K.create('textarea[name="goods_desc"]', 
	    {
	        width:"100%",
	        height:"425px",
	        cssPath : 'js/kindeditor/plugins/code/prettify.css', uploadJson : 'js/kindeditor/jsp/upload_json.jsp', 
	        fileManagerJson : 'js/kindeditor/jsp/file_manager_json.jsp', allowFileManager : true, 
	        afterCreate : function () 
	        {
	            var self = this;
	            K.ctrl(document, 13, function () 
	            {
	                self.sync();
	                document.forms['example'].submit();
	            });
	            K.ctrl(self.edit.doc, 13, function () 
	            {
	                self.sync();
	                document.forms['example'].submit();
	            });
	        }
	    });
	    // prettyPrint();
	    editor1.html($(".import_goodsdes").val());
	});
	
	
	var editor2 ;
	KindEditor.ready(function (K) 
	{
	    editor2 = K.create('textarea[name="mobile_desc"]', 
	    {
	        width:"100%",
	        height:"425px",
	        cssPath : 'js/kindeditor/plugins/code/prettify.css', uploadJson : 'js/kindeditor/jsp/upload_json.jsp', 
	        fileManagerJson : 'js/kindeditor/jsp/file_manager_json.jsp', allowFileManager : true, 
	        afterCreate : function () 
	        {
	            var self = this;
	            K.ctrl(document, 13, function () 
	            {
	                self.sync();
	                document.forms['example'].submit();
	            });
	            K.ctrl(self.edit.doc, 13, function () 
	            {
	                self.sync();
	                document.forms['example'].submit();
	            });
	        }
	    });
	    // prettyPrint();
	    editor2.html($(".import_goodsdes").val());
	});
	
	$(".ch_about").click(function(){
		 $("#dialog-about").dialog(
			        {
			            resizable : false, height : 600, width : 800, modal : true, autoOpen : true, buttons : {
			                "确定" : function () 
			                {
			                    $(this).dialog("close");
			                }
			       }
		});
		 $("#dialog-about").dialog("open");
	});
	
	$(".info_sub").click(function(){
		if(checkProInfo()){
			$(".save_goods_info").append("<input type='hidden' name='catId' value="+$(".ch_goods_cate").val()+" >");
			var name = $($(".name_input")[0]).val();
			$(".save_goods_info").append("<input type='hidden' name='goodsName' value='"+name+"' >");
			$(".save_goods_info").append("<input type='hidden' name='goodsSubtitle' value="+$($(".des_input")[0]).val()+" >");
			$(".save_goods_info").append("<input type='hidden' name='goodsNo' value="+$($(".no_input")[0]).val()+" >");
			$(".save_goods_info").append("<input type='hidden' name='brandId' value="+$("#goods_brand").val()+" >");
			$(".save_goods_info").append("<input type='hidden' name='goodsPrice' value="+$($(".sml_input")[0]).val()+" >");
			$(".save_goods_info").append("<input type='hidden' name='goodsAdded' value='1' >");
			$(".save_goods_info").append("<input type='hidden' name='goodsImg' value="+($($(".choose_pro_img")[0]).attr("src"))+" >");
			/*
			 * $(".save_goods_info").append("<input type='hidden'
			 * name='goodsDeno' value="+$(".dw_input").val()+" >");
			 */
			$(".save_goods_info").append("<input type='hidden' name='goodsSeoTitle' value="+$(".seo_title").val()+" >");
			$(".save_goods_info").append("<input type='hidden' name='goodsSeoKeyword' value="+$(".seo_key").val()+" >");
			$(".save_goods_info").append("<input type='hidden' name='goodsSeoDesc' value="+$(".seo_des").val()+" >");
			/*第三方分类*/
			$(".save_goods_info").append("<input type='hidden' name='goodsThirdCateId' value="+$(".ch_third_catid").val()+" >");
			
			var tags = $(".goods_tag:checked");
			if(null != tags && tags.length>0){
				for(var i =0;i<tags.length;i++){
					$(".save_goods_info").append("<input type='hidden' name='tags' value="+$(tags[i]).val()+" >");
				}
			}
			var type_param = $(".type_param");
			if(null != type_param && type_param.length>0){
				for(var i = 0;i<type_param.length;i++){
					$(".save_goods_info").append("<input type='hidden' name='paramId' value="+$(type_param[i]).val()+" >");
					$(".save_goods_info").append("<input type='hidden' name='paramValue' value="+$($(".type_param_val")[i]).val()+" >");
				}
			}
			var type_expand_param = $(".type_expand_param");
			if(null != type_expand_param && type_expand_param.length>0){
				for(var i = 0;i<type_expand_param.length;i++){
					$(".save_goods_info").append("<input type='hidden' name='expandParamId' value="+$(type_expand_param[i]).val()+" >");
					$(".save_goods_info").append("<input type='hidden' name='expandParamValue' value="+$($(".type_expand_sel")[i]).val()+" >");
				}
			}
			var specs = $(".type_spec:checked");
			if(null != specs && specs.length>0){
				for(var i =0;i<specs.length;i++){
					$(".save_goods_info").append("<input type='hidden' name='specs' value="+$(specs[i]).val()+" >");
				}
			}
			var specVal = $(".check_spec:checked");
			if(null != specVal && specVal.length>0){
				for(var i =0;i<specVal.length;i++){
					$(".save_goods_info").append("<input type='hidden' name='specsValue' value="+$(specVal[i]).attr("spec_value_id")+"-"+$(specVal[i]).attr("spec_id")+" >");
					$(".save_goods_info").append("<input type='hidden' name='specsValueImg' value="+$($(".up_spec_img_src")[i]).val()+" >");
					/* 保存规格别名 */
					$(".save_goods_info").append("<input type='hidden' name='specsValueRemark' value="+$($(".spe_text")[i]).val()+" >");
				}
			}
			var ch_about = $(".ch_about:checked");
			if(null != ch_about && ch_about.length>0){
				for(var i =0;i<ch_about.length;i++){
					$(".save_goods_info").append("<input type='hidden' name='about' value="+$(ch_about[i]).val()+" >");
				}
			}
			/* 验证通过并且数据加载到隐藏域中,提交表单 */
			$(".save_goods_info").submit();
			$(".goods_desc").val(editor1.html());
			$(".goods_mobile_desc").val(editor2.html());
			
		}
	});
});

function call_save_goods(data){
	if(parseFloat(data)>0){
		/* 保存完基本信息之后保存详细介绍,完成之后保存货品信息 */
		$(".new_goods_id").val(data);
		$(".save_goods_desc").submit();
	}
}

/* 保存商品详细回调函数 data:商品ID */
function call_save_desc(data){
	savePro(data);
}

/* 绑定事件 */
function bindEvent(){
	$(".qf_box:first").show();
	$(".spec_list li").each(function(){});
	$(".spec_list li").each(function(n){
		var _this = $(this);
		_this.find("input[type='checkbox']").change(function(){
			if($(this).prop("checked") == true) {
				$(".spec_box:eq("+n+")").show();
			} else {
				$(".spec_box:eq("+n+")").hide();
				$(".openSpecValue_"+$(this).val()).prop("checked",false);
				$(".spec_img_form_"+$(this).val()).remove();
			};
		});
	});
	$(".dw_box a").click(function(){});
	$(".dw_box a").click(function(){
		var _this = $(this);
		_this.parent().prev("input[type='text']").val(_this.text());
	});

	// $(".qf_box:first").show();
	// $(".prev_step").click(function(){});
	$(".prev_step").click(function(){
		/*
		 * $("body,html").animate({ scrollTop: 0 },0);
		 * $(".dinfo_tabs").html(""); $(".dinfo_wp").html("");
		 * $(".qf_box:last").hide(); $(".qf_box:first").show();
		 * $(".scg_dl").show();
		 */
	});
	$(".sb_wp input[type='checkbox']").change(function(){});
	$(".sb_wp input[type='checkbox']").change(function(){
		$(".spec_box").each(function(){
			var _this = $(this);
			if(_this.find("input:checkbox[checked]").length > 0) {
				_this.attr("name","ok");
			} else {
				_this.attr("name","no");
			};
		});
	});
	$(".sb_wp input[type='checkbox']").change(function(){});
	$(".sb_wp input[type='checkbox']").change(function(){
		$(".new_box").each(function(){
			var _this = $(this);
			if(_this.find("input:checked").length > 0) {
				_this.attr("name","ok");
			} else {
				_this.attr("name","no");
			};
		});
	});
	// $("#create_gds").click(function(){});
	$("#create_gds").click(function(){
		$("body,html").animate({
            scrollTop: 0
        },0);
		// if(checkBase()){
			//$(".name_input").val("");
			$(".des_input").val("");
			$(".dinfo_tabs").html("");
			$(".dinfo_wp").html("");
			test();
			$(".qf_box:first").hide();
			$(".scg_dl").hide();
			$(".qf_box:last").show();
		// }
	});
	
	/* 当选择规格图片的按钮发生改变时触发 */
	$(".spec_img").change(function(){});
	$(".spec_img").change(function(){
		$(".specValue_"+$(this).attr("spec_val_id")).submit();
	});
	
	
	/* 当选择货品图片的按钮发生改变时触发 */
	$(".up_pro_img").change(function(){});
	$(".up_pro_img").change(function(){
		product_image = $(this);
		$(product_image).parent().submit();
	});
	
	/* 选择图片 */
	$(".choose_img_btn").click(function(){});
	$(".choose_img_btn").click(function(){
		art.dialog.open('queryImageManageByChoose.htm?size=10000', {
			lock: true,
			width: '800px',
		    height: '400px',
			title: '选择图片'
	 });
	});
}

// 保存选择的图片信息
function saveChoooseTrademark(id, path){
	var index = $(".dinfo_tabs li").find(".cur").index();
	if(path.toString().indexOf(",")>-1){
		var paths = path.toString().split(",");
		for(var i = 0;i<paths.length;i++){
			var path2 = paths[i];
			$(".dinfo_wp").find(".show").find(".choose_imgs").append('<li><a class="del_ts" href="javascript:;" onclick="del_ts(this)"></a><input type="hidden" class="choose_img" value='+path2+'><a href="javascript:;"><img alt="" class="choose_pro_img" src='+path2+' width="200" height="200" /></a></li>');
		}
	}else{
		$(".dinfo_wp").find(".show").find(".choose_imgs").append('<li><a class="del_ts" href="javascript:;" onclick="del_ts(this)"></a><input type="hidden" class="choose_img" value='+path+'><a href="javascript:;"><img alt="" class="choose_pro_img" src='+path+' width="200" height="200" /></a></li>');
	}
}

function del_ts(obj) {
	$(obj).parent("li").remove();
}

/* 点击复制到全部名称 */
function copyAllName(obj){
	$(".name_input").val($(obj).parent().find(".name_input").val());
}
/* 点击复制到全部名称 */
function copyAllSubTitle(obj){
	$(".des_input").val($(obj).parent().find(".des_input").val());
}

function cp_all(t){
	$(".choose_img_btn").unbind('click');
	var _t = 0;
	for(var i=1;i<$(".dinfo_wp .dinfo_box").length;i++) {
		_t = $(t).parents(".dinfo_box").find("dl").clone();
		_t.find("input[type='radio']").attr("name","_rad"+i);
		_t.find(".p_code").val("");
		$(".dinfo_wp .dinfo_box:eq("+i+") dl").remove();
		$(".dinfo_wp .dinfo_box:eq("+i+")").prepend(_t);
		$(t).parents(".dinfo_box").find(".sec_des").click();
	};
	$(".choose_img_btn").click(function(){
		art.dialog.open('queryImageManageByPbAndCidForChoose.htm?size=10000', {
			lock: true,
			width: '800px',
		    height: '400px',
			title: '选择图片'
	 });
	});
}

function test(){
	/* 如果只选择开启了一个规格 */
	var ch_spec = $(".type_spec:checked");
	if(ch_spec.length==1){
		$(".dinfo_tabs").html("");
		$(".dinfo_wp").html("");
		var spec_id = $(ch_spec[0]).val();
		var spec_vals = $(".openSpecValue_"+spec_id);
		if(spec_vals.length>0){
			for(var i = 0;i<spec_vals.length;i++){
				if($(spec_vals[i]).prop("checked")){
					var _tabs = '<li><a href="javascript:;">'+ $(spec_vals[i]).val(); +'</a></li>';
					var hidearea ="<input type='hidden' class='product_spec' value="+$(spec_vals[i]).attr("spec_id")+"-"+$(spec_vals[i]).attr("spec_value_id")+"-"+$(spec_vals[i]).next(".spe_text").val()+">";
					var ct = $(".demo_box").clone();
					ct.removeClass("demo_box");
					$(".dinfo_tabs").append(_tabs);
					ct.append(hidearea);
					$(".dinfo_wp").append(ct);
					ct.find(".nameWp input").attr("name","_rad"+ct.index());
				}
			}
		}
	}else{
		var empArray2 = new Array();
		var empArray3 = new Array();
		var emp = 0;
		var emp1 = $(".new_box").length;
		for (var i = 0; i < emp1; i++) {
			if($("#emp" + i).attr("name") != "ok"){
				continue;
			}
			var arrayA = new Array();

			$("#emp" + i + "[name ='ok'] input:checked").each(function() {
				arrayA.push($(this).val()+"-"+$(this).attr("spec_id")+"-"+$(this).attr("spec_value_id")+"_"+$(this).next(".spe_text").val()+"-");
			});

			// $("#emp" + i + "[name ='ok']").attr("name", "no");
			
			while($("#emp" + (i+1) ).attr("name") == "no"){
				i++;
			}
			
			if (i < $(".new_box").length - 1 && emp == 0) {
				emp = 1;
				i++;
				var arrayB = new Array();
				$("#emp" + i + "[name = 'ok'] input:checked").each(function() {

					arrayB.push($(this).val()+"-"+$(this).attr("spec_id")+"-"+$(this).attr("spec_value_id")+"_"+$(this).next(".spe_text").val()+"-");

				});

				// $("#emp" + i + "[name ='ok']").attr("name", "no");

				for (var j = 0; j < arrayA.length; j++) {
					var arrayAValue = arrayA[j];

					for (var k = 0; k < arrayB.length; k++) {
						var arrayBValue = arrayB[k];
						empArray2.push(arrayAValue + arrayBValue);
					}

				}

			} else {

				if (empArray2.length != 0 && empArray3.length == 0) {

					for (var j = 0; j < arrayA.length; j++) {
						var arrayAValue = arrayA[j];

						for (var k = 0; k < empArray2.length; k++) {
							var arrayBValue = empArray2[k];
							empArray3.push(arrayAValue + arrayBValue);
						}

					}

					empArray2 = new Array();
				} else {

					for (var j = 0; j < arrayA.length; j++) {

						var arrayAValue = arrayA[j];

						for (var k = 0; k < empArray3.length; k++) {
							var arrayBValue = empArray3[k];
							empArray2.push(arrayAValue + arrayBValue);
						}

					}
					empArray3 = new Array();
				}

			}
		}


		if (empArray2.length == 0) {
			for (var x = 0; x < empArray3.length; x++) {
				var titles = empArray3[x].split("-");
				var title = "";
				var hidearea="";
				for(var i =0;i<titles.length;i++){
					if(i==0){
						title+=titles[i];
					}else if(i%3==0){
						title+=titles[i];
						hidearea+="<input type='hidden' class='product_spec' value="+titles[i-2]+"-"+titles[i-1].split("_")[0]+"-"+titles[i-1].split("_")[1]+">";
					}
				}
				var _tabs = '<li><a href="javascript:;">'+ title +'</a></li>';
				var ct = $(".demo_box").clone();
				ct.removeClass("demo_box");
				$(".dinfo_tabs").append(_tabs);
				ct.append(hidearea);
				$(".dinfo_wp").append(ct);
				ct.find(".nameWp input").attr("name","_rad"+ct.index());
			}

		}else{
			for (var x = 0; x < empArray2.length; x++) {
				var titles = empArray2[x].split("-");
				var title = "";
				var hidearea="";
				for(var i =0;i<titles.length;i++){
					if(i==0){
						title+=titles[i];
					}else if(i%3==0){
						title+=titles[i];
						hidearea+="<input type='hidden' class='product_spec' value="+titles[i-2]+"-"+titles[i-1].split("_")[0]+"-"+titles[i-1].split("_")[1]+">";
					}
				}
				var _tabs = '<li><a href="javascript:;">'+ title +'</a></li>';
				var ct = $(".demo_box").clone();
				ct.removeClass("demo_box");
				$(".dinfo_tabs").append(_tabs);
				ct.append(hidearea);
				$(".dinfo_wp").append(ct);
				ct.find(".nameWp input").attr("name","_rad"+ct.index());
			}
			
		}
	}
	ctabs('dinfo_tabs','dinfo_wp','dinfo_box');
	$(".copytoall").remove();
	$(".dinfo_box:eq(0)").append('<div class="tc copytoall"><a class="j_btn cp_all" href="javascript:;" onclick="cp_all(this);">复制到全部</a></div>');
	/* 绑定事件 */
	bindEvent();
	
};

// 点击切换
function ctabs(t1, t2, t3) {
	$("."+ t1).find("li:first").addClass("cur");
	$("."+ t2).find("."+ t3 +":first").show().addClass("show");
	$("."+ t1 +" li").each(function(n){
		var current = $(this);
		$(this).find("a").click(function(){
			var cur = $(this);
			$("."+ t1).find("li.cur").removeClass("cur");
			$("."+ t2).find("."+ t3 +".show").hide().removeClass("show");
			current.addClass("cur");
			$("."+ t2 +" ."+ t3 +":eq("+ n +")").show().addClass("show");
		});
	});
};

/* 获取第三方授权分类 */
function getAllGrandCateForThird(){
	if($(".fir_list li").length<=0){
		$.get("queryAllGrandCateForThird.htm",function(data){
			if(null != data && data.length>0){
				$(".fir_list").html("");
				for(var i =0;i<data.length;i++){
					$(".fir_list").append(" <li><a onclick='loadTypeParam(this);' role-id="+data[i].catId+" href='javascript:;'>"+data[i].catName+"</a></li>");
				}
			}
		});
	}
}
/* 第三方已作废 *//* 加载第二级分类 */
function loadSecCate(catId,obj){
	$($(obj).parent()).addClass('cur').siblings().removeClass('cur');
	$(".sec_search").val("");
	$(".thi_search").val("");
	allSecondThirdCat = null;
	allThirdThirdCat = null;
	$.get("querySonCateByCatId.htm?catId="+catId+"&CSRFToken="+$(".token_val").val(),function(data){
		if(null != data && data.length>0){
			$(".sec_list").html("");
			$(".thi_list").html("");
			for(var i =0;i<data.length;i++){
				$(".sec_list").append(" <li><a role-id="+data[i].catId+" onclick='loadThiCate("+data[i].catId+",this)' href='javascript:;'>"+data[i].catName+"</a></li>");
			}
		}
	});
}
/* 第三方已作废 *//* 加载第三级分类 */
function loadThiCate(catId,obj){
	$($(obj).parent()).addClass('cur').siblings().removeClass('cur');
	$(".thi_search").val("");
	allThirdThirdCat = null;
	$.get("querySonCateByCatId.htm?catId=0&CSRFToken="+$(".token_val").val(),function(data){
		if(null != data && data.length>0){
			$(".thi_list").html("");
			for(var i =0;i<data.length;i++){
				/* 点击事件写在goods_list.js中 */
				$(".thi_list").append(" <li><a onclick='loadTypeParam(this);' role-id="+data[i].catId+" href='javascript:;'>"+data[i].catName+"</a></li>");
			}
		}
	});
}
/* 搜索分类 */
/* 搜索一级分类 */
var allFirstThirdCat = new Array();
function searfirst(obj){
	/* 设置二三级的搜素参数为空 */
	$(".sec_search").val("");
	$(".thi_search").val("");
	allSecondThirdCat = null;
	allThirdThirdCat = null;
	/* 如果一级分类的全局变量为空 */
	if(null ==allFirstThirdCat || allFirstThirdCat.length<=0){
		allFirstThirdCat = $(".fir_list li");
	}
	/* 循环去匹配记录 */
	if(null != allFirstThirdCat && allFirstThirdCat.length>0){
		var searchList = new Array();
		for(var i =0;i<allFirstThirdCat.length;i++){
			var second = $(allFirstThirdCat[i]);
			if($($(second).find("a")).html().indexOf($(obj).val().trim())>=0){
				searchList.push($(second));
			}
		}
		/* 如果查询参数是空,就显示全部 */
		if($(obj).val().trim()=="" || $(obj).val() == null){
			searchList = allFirstThirdCat;
		}
			/* 清空分类 */
			$(".fir_list").html("");
			$(".sec_list").html("");
			$(".thi_list").html("");
			/* 清空已经选中的分类 */
			$("#goodscateName").val("请选择商品分类");
		    $("#goodsCatId").val(null);
		/* 如果搜索到的记录不为空,就添加到页面 */
		for ( var i = 0; i < searchList.length; i++) {
			$(".fir_list").append(" <li><a onclick='loadSecCate("+$(searchList[i]).find("a").attr("role-id")+",this)' href='javascript:;'>"+$(searchList[i]).find("a").html()+"</a></li>");
		}
	}
}
/* 搜索二级分类 */
var allSecondThirdCat = new Array();
function searSecond(obj){
	/* 设置三级的搜素参数为空 */
	$(".thi_search").val("");
	allThirdThirdCat = null;
	/* 如果二级分类的全局变量为空 */
	if(null ==allSecondThirdCat || allSecondThirdCat.length<=0){
		allSecondThirdCat = $(".sec_list li");
	}
	/* 循环去匹配记录 */
	if(null != allSecondThirdCat && allSecondThirdCat.length>0){
		var searchList = new Array();
		for(var i =0;i<allSecondThirdCat.length;i++){
			var second = $(allSecondThirdCat[i]);
			if($($(second).find("a")).html().indexOf($(obj).val().trim())>=0){
				searchList.push($(second));
			}
		}
		/* 如果查询参数是空,就显示全部 */
		if($(obj).val().trim()=="" || $(obj).val() == null){
			searchList = allSecondThirdCat;
		}
			/* 清空三四级分类 */
			$(".sec_list").html("");
			$(".thi_list").html("");
			/* 清空已经选中的分类 */
			$("#goodscateName").val("请选择商品分类");
		    $("#goodsCatId").val(null);
		
		/* 如果搜索到的记录不为空,就添加到页面 */
		for ( var i = 0; i < searchList.length; i++) {
			$(".sec_list").append(" <li><a onclick='loadThiCate("+$(searchList[i]).find("a").attr("role-id")+",this)' href='javascript:;'>"+$(searchList[i]).find("a").html()+"</a></li>");
		}
	}
}
/* 搜索三级分类 */
var allThirdThirdCat = new Array();
function searThird(obj){
	/* 如果三级分类的全局变量为空 */
	if(null ==allThirdThirdCat || allThirdThirdCat.length<=0){
		allThirdThirdCat = $(".thi_list li");
	}
	/* 循环去匹配记录 */
	if(null != allThirdThirdCat && allThirdThirdCat.length>0){
		var searchList = new Array();
		for(var i =0;i<allThirdThirdCat.length;i++){
			var second = $(allThirdThirdCat[i]);
			if($($(second).find("a")).html().indexOf($(obj).val().trim())>=0){
				searchList.push($(second));
			}
		}
		/* 如果查询参数是空,就显示全部 */
		if($(obj).val().trim()=="" || $(obj).val() == null){
			searchList = allThirdThirdCat;
		}
			/* 清空四级分类 */
			$(".thi_list").html("");
			/* 清空已经选中的分类 */
			$("#goodscateName").val("请选择商品分类");
		    $("#goodsCatId").val(null);
		
		/* 如果搜索到的记录不为空,就添加到页面 */
		for ( var i = 0; i < searchList.length; i++) {
			/* 点击事件写在goods_list.js中 */
			$(".thi_list").append(" <li><a onclick='loadTypeParam(this);'  role-id="+$(searchList[i]).find("a").attr("role-id")+" href='javascript:;'>"+$(searchList[i]).find("a").html()+"</a></li>");
		}
	}
}

/* 根据选中的第三级分类加载类型参数 */
function loadTypeParam(obj){
	/* 设置选中样式并且加载类型参数 */
	$($(obj).parent()).addClass('cur').siblings().removeClass('cur');
	$(".ch_goods_cate").val($(obj).attr("role-id"));
	
	/* 加载类型中的参数 */
    $.get("queryTypeVoByCatId.htm?catId=" + $(obj).attr("role-id")+"&CSRFToken="+$(".token_val").val(), function (data)
    {
        var expandParam = data.expandParams;
        var params = data.params;
        var specs = data.specVos;
        var expandParamHtml = "<table class='para_tb'><thead><tr><th width='30%'>参数名</th><th>内容</th></tr></thead><tbody>";
        if( expandParam.length>0){
        	for (var i = 0; i < expandParam.length; i++)
	        {
	            if (expandParam[i].valueList.length > 0)
	            {
	                expandParamHtml = expandParamHtml + "<tr><td><span class='sp'>*</span>" + expandParam[i].expandparamName + "</td><td>" + "<input type='hidden' class='type_expand_param' name='expandParamId' value='" + expandParam[i].expandparamId + "'>" + "<select class='tb_select type_expand_sel' name='expandparamValue'>";
	                for (var k = 0; k < expandParam[i].valueList.length; k++)
	                {
	                    expandParamHtml = expandParamHtml + "<option value='" + expandParam[i].valueList[k].expandparamValueId + "'>" + expandParam[i].valueList[k].expandparamValueName + "</option>";
	                };
	                expandParamHtml = expandParamHtml + "</select></td></tr>";
	            }else{
		        	 expandParamHtml = expandParamHtml + "<tr><td>您选择的商品分类下没有扩展参数!</td></tr>";
		        }
	        }
        }
        expandParamHtml = expandParamHtml +"</tbody></table>";
        $(".type_expandparam").html(expandParamHtml);
        /* 扩展参数 END */
        var paramHtml = "<table class='para_tb'><tbody>";
        if(params.length>0){
        	for (var i = 0; i < params.length; i++)
	        {
	            paramHtml = paramHtml + "<tr><td width='30%'>" + params[i].paramName + "</td><td><input type='hidden' class='type_param' name='paramId' value='" + params[i].paramId + "'/><input type='text' value='' class='text type_param_val' name='paramValue'/></td></tr>";
	        }
        }else{
        	 paramHtml = paramHtml + "您选择的商品分类下没有详细参数!";
        }
        paramHtml = paramHtml +"</tbody></table>";
        $(".type_params").html(paramHtml);
        /* 详细参数END */
        $(".type_attribute").html(expandParamHtml+paramHtml);
        
        /* 加载规格信息 */
        if(specs.length>0){
        	var specHtml="<div class='have_spec'>";
        	specHtml+="<ul class='spec_list clearfix'>";
        	for(var i = 0;i<specs.length;i++){
        		specHtml+="<li><label><input class='vm mr5 type_spec' type='checkbox' value="+specs[i].goodsSpec.specId+" />"+specs[i].goodsSpec.specName+"</label></li>";
        	}
        	specHtml+="</ul><!--/spec_list--><div class='sb_wp'>";
        	for(var i = 0;i<specs.length;i++){
        		specHtml+="<div id='emp"+i+"' class='new_box spec_box clearfix mt10'><em class='fl'>"+specs[i].goodsSpec.specName+"：</em><ul class='spec_cont clearfix fl ml10'>";
        			for(var j = 0;j<specs[i].goodsSpec.specDetails.length;j++){
        				specHtml+="<li><label class='mr10'><input class='vm mr5 check_spec openSpecValue_"+specs[i].goodsSpec.specId+"' type='checkbox' onclick='checkSpecValue(this);' spec_id="+specs[i].goodsSpec.specId+" spec_value_id="+specs[i].goodsSpec.specDetails[j].specDetailId+" value="+specs[i].goodsSpec.specDetails[j].specDetailName+" /><span>"+specs[i].goodsSpec.specDetails[j].specDetailName+"</span></label></li>";
        				// specHtml+="<p><label class='mr10
						// check_spec_val_tit_"+specs[i].goodsSpec.specDetails[j].specDetailId+"-"+specs[i].goodsSpec.specId+"'><input
						// type='checkbox' onclick='checkSpecValue(this)'
						// class='vm mr5
						// openSpecValue_"+specs[i].goodsSpec.specId+"'
						// name='openSpecValue'
						// value="+specs[i].goodsSpec.specDetails[j].specDetailId+"-"+specs[i].goodsSpec.specId+"
						// />"+specs[i].goodsSpec.specDetails[j].specDetailName+"</label></p>";
        			}
        			specHtml+="</ul><!--/spec_cont--></div><!--/spec_box-->";
        	}
        	$(".type_spec").html(specHtml);
        }else{
        	$(".type_spec").html("<p class='no_spec'>没有规格</p>");
        }
        /* 绑定事件 */
    	bindEvent();
    	tx();
    });
    
    
    /* 根据选中分类加载相关商品 */
    $.get("findGoodByCatId.htm?catId=" +$(obj).attr("role-id")+"&CSRFToken="+$(".token_val").val(), function (data)
    {
        var aboutGoodsHtml = "";
        if (data.length > 0)
        {
            for (var i = 0; i < data.length; i++)
            {
                aboutGoodsHtml = aboutGoodsHtml + "<tr><td class='tc'>" + (i - 1 + 2) + "</td><td class='tc'><input type='checkbox' class='ch_about' name='aboutGoodsId' value='" + data[i].goodsId + "'/></td>" + "<td class='tc'><img width='20px' height='20px' src=" + data[i].goodsImg + " /></td><td class='tc'>" + data[i].goodsNo.substring(0, 
                15) + "...</td><td class='tc' title='"+data[i].goodsName+"'>" + data[i].goodsName.substring(0,15) + "...</td>";
                aboutGoodsHtml = aboutGoodsHtml + "<td class='tc'>" + data[i].goodsType.typeName + "</td><td class='tc'>" + data[i].goodsBrand.brandName + "</td></tr>";
            }
        }
        else
        {
            aboutGoodsHtml = aboutGoodsHtml + "<tr><td class='tc' colspan='9'>选择的分类下暂时还没有商品</td></tr>";
        }
        $("#aboutGoodsList tbody").html(aboutGoodsHtml);
    });
}
/* 点击开启规格值的时候 */
function checkSpecValue(obj){
	if($(obj).prop("checked")){
		$(obj).parent().append("<form action='uploadImgSingle.htm' method='post' target='hidden_frame'  enctype='multipart/form-data'  class='specValue_"+$(obj).attr("spec_value_id")+" spec_img_form_"+$(obj).attr("spec_id")+"'>" +
				"<input type='hidden' name='specValId' value="+$(obj).attr('spec_value_id')+">" +
				"<input type='hidden' class='up_spec_img_src up_spec_img_src_"+$(obj).attr("spec_value_id")+"' value=''>" +
				"<input style='margin-left:5px' type='file' name='specImg' spec_val_id="+$(obj).attr("spec_value_id")+" class='spec_img spec_img_"+$(obj).attr("spec_value_id")+"' />");
		bindEvent();
	}else{
		$(".up_spec_img_"+$(obj).attr("spec_value_id")).remove();
		$(".specValue_"+$(obj).attr("spec_value_id")).remove();
	}
}

/* 上传规格图片回调 */
function specImgSucc(msg){
	if(msg=="111"){
		openDialog("不支持的图片格式");
	}else{
		var id = msg.substring(msg.indexOf("-")+1,msg.length);
		var url = msg.substring(0,msg.indexOf("-"));
		// 如果该规格已经上传过图片就替换已经上传的图片
		if($(".up_spec_img_"+id)[0]){
			$(".up_spec_img_"+id).attr("src",url);
		}else{
			$(".specValue_"+id).before("<img src="+url+" class='up_spec_img_"+id+"' width='20px' height='20px' />");
		}
		$(".up_spec_img_src_"+id).val(url);
	}
}
/* 上传货品图片SUCC */
function productImgSucc(msg){
	if(msg=="111"){
		openDialog("不支持的图片格式");
	}else{
		var params = msg.split("-");
		$(product_image).parent().before("<img class='vm up_pro_img' src="+params[1]+"  width='50' height='50' /><input type='hidden' class='product_up_img' value="+params[0]+">");
	}
}

/* 循环保存货品信息 */
function savePro(goodsId){
	var pro_box = $(".dinfo_box");
	if(null != pro_box && pro_box.length>0){
		for(var j=0;j<pro_box.length;j++){
			var _pro = $(pro_box[j]);
			if(_pro.find(".product_spec").length>0){
				var param = "tNewUploadProduct.htm";
				param+="?goodsId="+goodsId;
				param+="&goodsInfoItemNo="+_pro.find(".no_input").val();
				param+="&goodsInfoName="+_pro.find(".name_input").val();
				param+="&goodsInfoSubtitle="+_pro.find(".des_input").val();
				param+="&goodsInfoPreferPrice="+_pro.find(".pro_price").val();
				param+="&goodsInfoCostPrice="+_pro.find(".pro_cost_price").val();
				param+="&goodsInfoMarketPrice="+_pro.find(".pro_mark_price").val();
				param+="&goodsInfoStock="+_pro.find(".pro_stock").val();
				param+="&goodsInfoWeight=0";
				param+="&goodsInfoImgId="+$(_pro.find(".choose_pro_img")[0]).attr("src");
				/* 获取选择的商品状态 */
				var sta = _pro.find(".pro_status:checked").val();
				if(sta==4){
					param+="&goodsInfoAdded="+sta;
					param+="&addedTime="+_pro.find(".up_time").val();
				}else{
					param+="&goodsInfoAdded="+sta;
				}
				/* 拼接规格信息 */
				var product_spec = _pro.find(".product_spec");
				if(null != product_spec && product_spec.length>0){
					for(var i =0;i<product_spec.length;i++){
						var specVal = $(product_spec[i]).val().split("-");
						/* 拼接规格信息以及规格值信息 */
						param+="&specId="+specVal[0]+"&specDetailId="+specVal[1]+"&specRemark="+specVal[2];
					}
				}
				/* 获取是否显示在列表页 */
				if(_pro.find(".show_list").prop("checked")){
					param+="&showList=1";
				}else{
					param+="&showList=0";
				}
				/* 获取是否显示在手机版 */
				if(_pro.find(".show_mobile").prop("checked")){
					param+="&showMobile=1";
				}else{
					param+="&showMobile=0";
				}
				
				/* 获取选择的服务支持 */
				var supp = _pro.find(".pro_supp:checked");
				if(null != supp && supp.length>0){
					for(var i = 0;i<supp.length;i++){
						param+="&support="+$(supp[i]).val();
					}
				}
				/* 拼接图片参数 */
				var imgs = _pro.find(".choose_img");
				if(null != imgs && imgs.length>0){
					for(var i =0;i<imgs.length;i++){
						param+="&image="+$(imgs[i]).val();
					}
				}
				$.ajax({
					type: 'post',
					url:param,
					async:false,
					success: function(data) {
						if(data>0){
							 /*更新商品导入临时表的状态*/
							 var param = "updateImportAdded.htm?id="+$(".import_id").val();
							 $.ajax({
									type: 'post',
									url:param,
									async:false,
									success: function(data) {
									}
								});
						}else{
						}
					}
				});
			}
		}
		$(".dialogMsg").text("发布成功！");
		$(".dialogOK").attr("onclick","javascript:location.href = 'thirdImport.html';");
	    dia(1);
	}
}

/**
 * 验证添加商品基本信息
 */
function checkBase(){
	var bool = true;
	if(!$(".ch_goods_cate").val()>0){
		openDialog("请选择商品分类!");
		bool = false;
	}
	/*
	 * bool = checkLengthForJQ($(".dw_input"),$(".dw_input_Tips"),"计价单位不能为空!") &&
	 * bool;
	 * if(checkLengthForJQ($(".dw_input"),$(".dw_input_Tips"),"计价单位不能为空!")){
	 * bool = checkSymbForJQ($(".dw_input"),$(".dw_input_Tips")) && bool; }
	 */
	/* 如果选择过了分类就验证是否选择规格 */
	if($(".ch_goods_cate").val()>0){
		if($(".type_spec:checked").length<=0 || $(".check_spec:checked").length<=0){
			openDialog("请至少选择一个商品规格!");
			bool = false;
		}else{
			var spe_text = $(".spe_text");
			for(var i =0;i<spe_text.length;i++){
				bool = checkLengthForJQ($(spe_text[i]),$(".spec_remark_input_Tips"),"规格值不能为空!") && bool;
				if(checkLengthForJQ($(spe_text[i]),$(".spec_remark_input_Tips"),"规格值不能为空!")){
					bool = checkSymbForJQ($(spe_text[i]),$(".spec_remark_input_Tips")) && bool;
				}
			}
		}
	}
	return bool;
}

/* 验证货品等信息 */
function checkProInfo(){
	/* 如果已经点击过保存就返回false */
	if($(".flag_saved").val()=="1"){
		return false;
	}else{
		$(".flag_saved").val("1");
		var bool = true;
		/* 获取每一个TAB页 */
		var pro_box = $(".dinfo_wp").find(".dinfo_box");
		if(null != pro_box && pro_box.length>0){
			/* 逐个验证每一个TAB */
			for(var _i = 0;_i<pro_box.length;_i++){
				var _pro = $(pro_box[_i]);
				bool = checkLength( _pro.find(".no_input"), "　商品编号", _pro.find(".no_input_Tips"), 10, 32 ) && bool;
				if(checkLength( _pro.find(".no_input"), "　商品编号", _pro.find(".no_input_Tips"), 10, 32 )){
					bool =checkRegexp(  _pro.find(".no_input"), /^[0-9]+$/, "　商品编号输入格式不正确.",  _pro.find(".no_input_Tips") ) && bool;
				}
				bool = checkLength(  _pro.find(".name_input"), "商品名称",  _pro.find(".name_input_Tips"), 3, 50 ) && bool;
				/* if(checkLength(  _pro.find(".name_input"), "商品名称",  _pro.find(".name_input_Tips"), 3, 50 )){
				 * bool = checkSymbForJQ( _pro.find(".name_input"), _pro.find(".name_input_Tips")) && bool;
				 * }
				 */   
				bool = checkSymbForJQ( _pro.find(".des_input"), _pro.find(".des_input_Tips")) && bool;
				bool = checkRegexp( _pro.find(".pro_price"), /^[0-9]+[.]{0,1}[0-9]{0,2}$/, "　销售价格输入格式不正确.",  _pro.find(".pro_price_Tips") ) && bool;
				bool = checkRegexp( _pro.find(".pro_cost_price"), /^[0-9]+[.]{0,1}[0-9]{0,2}$/, "　成本价格输入格式不正确.",  _pro.find(".pro_cost_price_Tips") ) && bool;
				bool = checkRegexp( _pro.find(".pro_mark_price"), /^[0-9]+[.]{0,1}[0-9]{0,2}$/, "　市场价格输入格式不正确.",  _pro.find(".pro_mark_price_Tips") ) && bool;
				bool = checkRegexp( _pro.find(".pro_stock"), /^[0-9]+$/, "　库存输入格式不正确.",  _pro.find(".pro_stock_Tips") ) && bool;
				/* 验证是否上传图片 */
				/*
				 * var images = _pro.find(".choose_img"); if(null != images &&
				 * images.length>0){
				 * updateTipsSucc(_pro.find(".pro_image_Tips"),_pro.find(".pro_image_Tips"));
				 * }else{ updateTips("请至少选择一张图片!",_pro.find(".pro_image_Tips"));
				 * bool = false && bool; }
				 */
				
				/* 验证分仓信息 */
//				var stock=_pro.find(".ware_stock");
//				if(null != stock && stock.length>0){
//					for(var i =0;i<stock.length;i++){
//						var _sto=$(stock[i]);
//						bool = checkRegexp(_sto, /^[0-9]+$/, "库存必须为正整数.", _sto.parent().parent().find(".pro_ware_Tips") ) && bool;
//					}
//				}
//				var price=_pro.find(".ware_price");
//				if(null != price && price.length>0){
//					for(var i =0;i<price.length;i++){
//						var _price=$(price[i]);
//						bool = checkRegexp(_price, /^[0-9]+[.]{0,1}[0-9]{0,2}$/, "价格输入格式不正确.", _price.parent().parent().find(".pro_ware_pri_Tips") ) && bool;
//					}
//				}
//				/* 验证分仓信息 END */
				/* 判断编号是否已经存在 */
				if(_pro.find(".exist_flag").val()=="1"){
					_pro.find(".no_input").addClass("ui-state-error");
					_pro.find(".no_input_Tips").addClass("ui-state-highlight");
					_pro.find(".no_input_Tips").text("名称或编号已经存在!");
					bool = false && bool;
				}
				/* 如果验证不通过就显示当前TAB */
				if(!bool){
					location.hash="anchor_top";
					$(".show_title").text("商品信息验证未通过！");
					dia(1);
					// alert(4);
					// alert(_pro.html());
					show_tab(_pro.parent());
				}
			}
		}
		if(!bool){
			$(".flag_saved").val("0");
		}
		return bool;
	}
}

/* 验证货品编号是否已经存在 */
function checkProNo(obj){
	/* 验证货品编号是否已经存在 */
	if($(obj).val().trim().length>0){
		var param = "checkProductNo.htm?productNo="+$(obj).val()+"&time="+Math.random();
		$.ajax({
			type: 'post',
			url:param,
			async:false,
			success: function(data) {
				if(!data){
					$(obj).addClass("ui-state-error");
					$(obj).parent().find(".no_input_Tips").addClass("ui-state-highlight");
					$(obj).parent().find(".no_input_Tips").text("名称或编号已经存在!");
					$(obj).parent().find(".exist_flag").val("1");
				}else{
					/* 验证当前输入的是否有重复 */
					var inputs = $(".no_input");
					var count = 0;
					for(var i =0;i<inputs.length;i++){
						var no = $(inputs[i]).val();
						if($(obj).val()==no){
							count = parseInt(count)+parseInt(1);
						}
					}
					/* 如果数量大于1,其中有一个是自身 */
					if(parseInt(count)>1){
						$(obj).addClass("ui-state-error");
						$(obj).parent().find(".no_input_Tips").addClass("ui-state-highlight");
						$(obj).parent().find(".no_input_Tips").text("编号已经输入过!");
						$(obj).parent().find(".exist_flag").val("1");
					}else{
						$(obj).removeClass("ui-state-error");
						$(obj).parent().find(".no_input_Tips").removeClass("ui-state-highlight");
						$(obj).parent().find(".no_input_Tips").text("");
						$(obj).parent().find(".exist_flag").val("0");
					}
				}
			}
		});
	}
}
/* 设置库房默认价格 */
function price_default(obj){
	if(/^[0-9]+[.]{0,1}[0-9]{0,2}$/.test($(obj).val())){
		$(obj).parent().parent().find(".ware_price").val($(obj).val());
	}
}


/* 开启弹出框 */
function openDialog(tipValue){
	 $(".dia_tip").text(tipValue);
	 $("#dialog-tip").dialog(
		        {
		            resizable : false, height : 150, width : 270, modal : true, autoOpen : true, buttons : {
		                "确定" : function () 
		                {
		                    $(this).dialog("close");
		                }
		       }
	});
	 $("#dialog-tip").dialog("open");
}

/* 根据传递的tab对象显示对应的tab窗体 */
function show_tab(tab_box){
	// $(".dinfo_wp").find(".dinfo_box").hide();
	$(tab_box).show();
	var _n= tab_box.index() - 1;
	$(".dinfo_tabs li").removeClass("cur");
	$(".dinfo_tabs li:eq("+_n+")").addClass("cur");
}

function tx(){
	$(".spec_cont input[type='checkbox']").each(function(){
		var _this = $(this);
		var _v = _this.next("span").text();
		var _text = '<input class="spe_text" type="text" value="'+_v+'" />';
		_this.change(function(){
			if(_this.prop("checked") == true) {
				_this.next("span").hide();
				_this.after(_text);
			} else {
				_this.next().next("span").show().text(_this.next("input").val());
				_this.next("input").remove();
				
			}
		});
	});
}

function choose_img(c){
	if($(c).hasClass("choose")) {
		$(c).removeClass("choose");
	} else {
		$(c).addClass("choose");
	}	
};

/*-------------------------------------------------------------------*/
// 根据jq对象验证特殊字符，将调试显示到页面中
function checkSymbForJQ(inputobj,Tipobj){
	 var regexp=new RegExp("[''\\[\\]<>?!]");
	 if (regexp.test( $(inputobj).val() ) ) {
		 $(inputobj).addClass( "ui-state-error" );
         updateTips( "输入的内容包含特殊字符!", $(Tipobj) );
         return false;
     }else {
    	 $(inputobj).removeClass( "ui-state-error" );
     	 updateTipsSucc(null,$(Tipobj));
         return true;
     }
}

/*--------------------------------------------------------------------------------------------------------*/

function addGoodsBrand(){
	$.get("queryGrandBrandByThirdId.htm",function(data){
		if(data!=null && data.length>0){
			for(var i=0;i<data.length;i++){
				$("#goods_brand").append("<option value='"+data[i].brandId+"'>"+data[i].brandName+"</option>");
			}
			/* 初始化样式 */
			$("#goods_brand").chosen();
		}
	});
}

