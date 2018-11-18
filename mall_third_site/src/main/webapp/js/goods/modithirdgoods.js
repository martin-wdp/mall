/**
 * 第三方商品页面js 
 * author yuankk time 2014-5-8 11:05
 */
$(function() {
	/*查询所有的一级分类,并添加到页面*/
	$.get("getAllThirdCateForList.htm", function(data) {
		tCates.length = 0;
		rec(data, 0);
		var html = "";
		for ( var i = 0; i < tCates.length; i++) {
			html += "<li><a href='javascript:;' onclick='loadSecondCat("
					+ tCates[i].catId + ",this);'>" + tCates[i].catName
					+ "</a></li>";
		}
		$(".cat_first").html(html);
		$(".cat_second").html("");
		$(".cat_third").html("");
	});
	/*查询曾经使用的分类信息,并添加到下拉中*/
	$.get("getCookieThirdCate.htm",function(data){
		if(null != data && data.length>0){
			for(var i=0;i<data.length;i++){
				$(".use_third_cat").append("<option value="+data[i].catId+">"+data[i].catName+"</option>");
			}
		}
	});
	/*当选择最近使用的分类的时候*/
	$(".use_third_cat").change(function(){
		/*设置选中的值*/
		$(".ch_third_catid").val($(this).val());
		$(".firstCate").text("已选择最近使用的分类:"+$(".use_third_cat option:selected").text());
		$(".secondCate").text("");
		$(".thirdCate").text("");
		$(".fouthCate").text("");
	});

   /*  富文本编辑框 
    $('.summernotedesc').summernote({
        height: 300,
        tabsize: 2,
        lang: 'zh-CN',
    	onImageUpload: function(files, editor, $editable) {
            sendFile(files,editor,$editable);
        }
    });

     富文本编辑框 
    $('.summernotemobile').summernote({
        height: 300,
        tabsize: 2,
        lang: 'zh-CN',
    	onImageUpload: function(files, editor, $editable) {
            sendFile(files,editor,$editable);
        }
    });*/
	
	//步骤
	$(".step_wp:first").show();
	$(".step_next").click(function(){
		/*如果点击的是第一阶段的下一步*/
		if($(this).attr("data-role")=="step_first"){
			if($(".ch_third_catid").val()=="-1"){
				$(".show_title").text("请选择商品所属分类!");
		         dia(1);
				return;
			}else{
				/*如果选择的分类验证通过,就加载所有签约的分类和签约的品牌*/
				/*加载签约的品牌*/
				$(".grand_brand_sel option").remove(); 
				$.get("queryGrandBrandByThirdId.htm",function(data){
					if(data!=null && data.length>0){
						for(var i=0;i<data.length;i++){
							$(".grand_brand_sel").append("<option value="+data[i].brandId+">"+data[i].brandName+"</option>");
						}
					}
					$(".grand_brand_sel").val($(".old_brand_id").val());
				});
				/*查询所有的签约的分类信息,并添加到树形菜单中显示*/
				$.get("queryAllGrandCateForThird.htm",function(data){
					if (null != data && data.length>0) {
						/*处理属性菜单*/
					    var setting = {
					        data : {
					            key : { }, simpleData : {
					                enable : true 
					            }
					        },
					        callback : {
					            onClick : onClick 
					        }
					    };
					    var tdata=data;
					    var zNodes = new Array();
			            for (var i = 0; i < tdata.length; i++) 
			            {
			                var node = {
			                    id : tdata[i].catId, pId : tdata[i].catParentId, name : tdata[i].catName, open : true 
			                };
			                zNodes.push(node);
			            }
			            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
					}
				});
				/*加载所有的商品标签*/
				$(".goods_tag_third").html("");
				$.get("queryAllTagForThird.htm",function(data){
					if(data != null && data.length > 0){
						for(var i=0;i<data.length;i++){
							var tagHtml="";
							tagHtml+="<label class='choose-label mr10'><input class='vm mr5'";
							var old_tag = $(".old_tag");
							for(var j = 0;j<old_tag.length;j++){
								if(data[i].tagId == $(old_tag[j]).val()){
									tagHtml+=" checked = checked ";
								}
							}
							tagHtml+=" type='checkbox' name='goods_tag' value='"+data[i].tagId+"' />"+data[i].tagName+"</label>";
							$(".goods_tag_third").append(tagHtml);
							$.material.init();
						}
					}
				});
			}
		}
		if($(this).attr("data-role")=="step_second"){
			/*验证所有的基本信息*/
			if(!checkBase()){
				return;
			}else{
				/*加载选中的宁派分类的关联的类型的扩展属性和详细参数*/
				/*loadAllParam();*/
			}
		}
		if($(this).attr("data-role")=="step_four"){
			/*验证介绍信息*/
			if(!checkDesc()){
				return;
			}else{
				$(".goodsDetailDesc").html(editor.html());
				$(".mobileDesc").html(editor2.html());
				/*加载所有的同分类下的第三方商品*/
				loadAllAboutGoodsByThirdCatId(1);
			}
		}
		var cur = $(this).parents(".step_wp");
		cur.next(".step_wp").show();
		cur.hide();
		});
	$(".step_prev").click(function(){
		var cur = $(this).parents(".step_wp");
		cur.prev(".step_wp").show();
		cur.hide();
	});

	
	/*提交按钮点击的时候*/
	$(".sa_goods").click(function(){
		if(checkSeo()){
			//如果验证通过就执行提交表单
            $(".goods_desc").val($(".summernotedesc").code());
            $(".goods_mobile_desc").val($(".summernotemobile").code());
			$(".sa_third_goods").submit();
		}else{
			return ;
		}
	});
	
	/*绑定清除最近使用的分类的点击事件*/
	$(".clear_third_cate_cookie").click(function(){
		$.get("clearThirdCateFromCookie.htm",function(data){
			if (data) {
				$(".show_title").text("清除最近使用的分类成功!");
		         dia(1);
			}else{
				$(".show_title").text("清除最近使用的分类异常,请重试!");
		         dia(1);
			}
		});
	});
	/*选择图片*/
	$(".choose_image_button").click(function(){
		art.dialog.open('queryImageManageByChoose.htm?size=10000', { 
			lock: true,
			width: '900px',
		    height: '620px',
            top:'33px',
			title: '选择图片'
		});
	});
});

var tCates = new Array(0);
/* 递归 */
function rec(data, pid) {
	for (var i = 0; i < data.length; i++) {
		var cpid = data[i].catParentId;
		if ((cpid + "") == (pid + "")) {
			tCates[tCates.length] = data[i];
		}
		var cateVos = data[i].cateVos;
		rec(cateVos, pid);
	}
}
//--------------------------------------------------
var tData = new Array(0);
function splitData(data){
	for (var i = 0; i < data.length; i++) {
		tData = data[i];
		splitData(tData);
	}
}

/*树形菜单的点击事件*/
function onClick(event, treeId, treeNode, clickFlag) 
{
    $(".goodsCatId").val(treeNode.id);
    $(".ch_cat_name").val(treeNode.name);
    $(".menuContent").fadeOut("slow");
    $(".aboutGoodsId").remove();
}
/*显示树形控件*/
function showMenu() 
{
    var selObj = $(".goodsCatId");
    var businessOffset = $(".goodsCatId").offset();
    $(".menuContent").css( 
    {
        left : businessOffset.left + 800 + "px", top : businessOffset.top - selObj.outerHeight() + 300 + "px" 
    }).slideDown("fast");
    onBodyDownForArea();
}
/*隐藏树形控件*/
function onBodyDownForArea()
{
    jQuery.fn.isChildAndSelfOf = function (b)
    {
        return (this.closest(b).length > 0);
    };
    $(document).click(function (event)
    {
        if (!($(event.target).isChildAndSelfOf(".menuContent")) && !($(event.target).hasClass("st_choose"))) {
            $(".menuContent").fadeOut("slow");
        };
    });
}
/* 点击一级分类的时候加载第二级分类 */
function loadSecondCat(catId, obj) {
	/*设置选中的值*/
	$(".ch_third_catid").val(catId);
    $("#parentId1").val(catId);
	/*添加分类选中的样式*/
	$($(obj).parent()).addClass('cur').siblings().removeClass('cur');
	$(".firstCate").html("<span>" + $(obj).html()+ "</span>");
	$(".secondCate").html("");
	$(".thirdCate").html("");
	$(".fouthCate").html("");
	/*清空所有的查询*/
	$(".sear_second").val("");
	$(".sear_third").val("");
	$(".sear_fouth").val("");
	/*设置二级分类的全局变量为空*/
	allSecondThirdCat = null;
	allThirdThirdCat = null;
	allFouthThirdCat = null;
	/*查询子级分类*/
	$.get("getAllThirdCateForList.htm", function(data) {
		tCates.length = 0;
		rec(data, catId);
		var html = "";
		for ( var i = 0; i < tCates.length; i++) {
			html += "<li><a href='javascript:;' onclick='loadThirdCat("
					+ tCates[i].catId + ",this);'>" + tCates[i].catName
					+ "</a></li>";
		}
		$(".cat_second").html(html);
		$(".cat_third").html("");
		$(".cat_fourth").html("");
	});
}
/* 点击二级分类的时候加载第三级分类 */
function loadThirdCat(catId, obj) {
	/*设置选中的值*/
	$(".ch_third_catid").val(catId);
    $("#parentId2").val(catId);
	/*添加选中的样式*/
	$($(obj).parent()).addClass('cur').siblings().removeClass('cur');
	$(".secondCate").html("<span class='chs_line'>&gt;</span><span>" + $(obj).html()+ "</span>");
	$(".thirdCate").html("");
	$(".fouthCate").html("");
	/*清空第三四级的查询参数并设置临时参数为空*/
	$(".sear_third").val("");
	$(".sear_fouth").val("");
	allThirdThirdCat = null;
	allFouthThirdCat = null;
	/*加载子级分类*/
	$.get("getAllThirdCateForList.htm", function(data) {
		tCates.length = 0;
		rec(data, catId);
		var html = "";
		for ( var i = 0; i < tCates.length; i++) {
			html += "<li><a href='javascript:;' onclick='loadFourthCat("
					+ tCates[i].catId + ",this);'>" + tCates[i].catName
					+ "</a></li>";
		}
		$(".cat_third").html(html);
		$(".cat_fourth").html("");
	});
}
/* 点击第三级分类的时候 加载第四季分类 */
function loadFourthCat(catId, obj) {
	/*设置选中的值*/
	$(".ch_third_catid").val(catId);
	/*添加选中的样式*/
	$($(obj).parent()).addClass('cur').siblings().removeClass('cur');
	$(".thirdCate").html("<span class='chs_line'>&gt;</span><span>" + $(obj).html()+ "</span>");
	$(".fouthCate").html("");
	/*清空第四级的查询参数并设置临时参数为空*/
	$(".sear_fouth").val("");
	allFouthThirdCat = null;
	/*加载子级分类*/
	$.get("getAllThirdCateForList.htm", function(data) {
		tCates.length = 0;
		rec(data, catId);
		var html = "";
		for ( var i = 0; i < tCates.length; i++) {
			html += "<li><a href='javascript:;' onclick='checkFourthCat("
					+ tCates[i].catId + ",this);'>" + tCates[i].catName
					+ "</a></li>";
		}
		$(".cat_fourth").html(html);
	});
}
/*点击第四级分类的时候*/
function checkFourthCat(catId, obj) {
	/*添加选中的样式*/
	$($(obj).parent()).addClass('cur').siblings().removeClass('cur');
	$(".fouthCate").html("<span class='chs_line'>&gt;</span><span>" + $(obj).html()+ "</span>");
	$(".ch_third_catid").val(catId);
}

/*添加商品步骤,验证第二阶段的基本信息*/
function checkBase(){
	var bValid=true;
	bValid = checkRegexp( $(".goods_no"), /^[0-9]+$/, "商品编号只能为数字.", $(".goods_no_tips") ) && bValid;
	//bValid = checkSpecSymb($(".goods_name"),$(".goods_name_tips")) && bValid;
    bValid = checkSpecSymb($(".goods_subtitle"),$(".goods_subtitle_tips")) && bValid;
    if($("#goods_brand").val()==null){
        $("#goods_brand").addClass( "ui-state-error" );
        bValid = false;
    }

    if(!$(".grand_brand_sel").val()>0){
    	$(".grand_brand_sel").addClass( "ui-state-error" );
    	 updateTips("请选择商品品牌!", $(".goods_brand_tips"));
    }else{
    	$(".grand_brand_sel").removeClass( "ui-state-error" );
    	updateTipsSucc(null,$(".goods_brand_tips"));
    }
    
    if ($(".goodsCatId").val() == "0")
    {
        $(".ch_cat_name").addClass( "ui-state-error" );
        updateTips("请选择宁派分类!", $(".goodsCate_tips"));
        bValid = false;
    }else{
    	$(".ch_cat_name").removeClass( "ui-state-error" );
    	updateTipsSucc(null,$(".goodsCate_tips"));
    }
    bValid = checkSpecSymb($(".goods_keywords"),$(".goods_keywords_tips")) && bValid;
    if(checkRegexp( $(".goods_no"), /^[0-9]+$/, "商品编号只能为数字.", $(".goods_no_tips") )){
    	bValid = checkLength( $(".goods_no"), "商品编号", $(".goods_no_tips"), 10, 32 ) && bValid;
    }
//    if(checkSpecSymb($(".goods_name"),$(".goods_name_tips"))){
//    	 bValid = checkLength( $(".goods_name"), "商品名称", $(".goods_name_tips"), 2, 50 ) && bValid;
//    }
    
   
   	bValid = checkLength( $(".goods_name"), "商品名称", $(".goods_name_tips"), 2, 50 ) && bValid;  
    bValid = checkRegexp( $(".goods_price"), /^[0-9]+[.]{0,1}[0-9]{0,2}$/, "商品价格输入格式不正确.", $(".goods_price_tips") ) && bValid;
   // bValid = checkLength( $(".goods_deno"), "计价单位", $(".goods_deno_tips"), 1, 5 ) && bValid;
    /*如果验证编号的标记为已经存在,就更新状态*/
    if($(".checkExistsFlag").val()=="0"){
    	$(".goods_no").addClass("ui-state-error");
    	$(".goods_no_tips").addClass("ui-state-highlight");
    	$(".goods_no_tips").text("名称或编号已经存在!");
    	bValid = false;
    }
	return bValid;
}
/*加载所有的选中的分类的关联的类型的详细参数和详细参数*/
function loadAllParam(){
	/*加载类型中的参数*/
    $.get("queryTypeVoByCatId.htm?catId=" + $(".goodsCatId").val(), function (data)
    {
    	$(".type_params").remove();
        var expandParam = data.expandParams;
        var params = data.params;
        var expandParamHtml = "";
        if( expandParam.length>0){
        	for (var i = 0; i < expandParam.length; i++)
	        {
	            if (expandParam[i].valueList.length > 0)
	            {
	                expandParamHtml = expandParamHtml + "<tr class='type_params'><th><span class='sp'>*</span>" + expandParam[i].expandparamName + "</th><td>" + "<input type='hidden' name='expandParamId' value='" + expandParam[i].expandparamId + "'>" + "<select name='expandparamValue'>";
	                for (var k = 0; k < expandParam[i].valueList.length; k++)
	                {
	                    expandParamHtml = expandParamHtml + "<option value='" + expandParam[i].valueList[k].expandparamValueId + "'>" + expandParam[i].valueList[k].expandparamValueName + "</option>";
	                };
	                expandParamHtml = expandParamHtml + "</select></td></tr>";
	            }else{
		        	 expandParamHtml = expandParamHtml + "您选择的商品分类下没有扩展参数!";
		        }
	        }
        }
        if(expandParamHtml.length>20){
        	 expandParamHtml = expandParamHtml +"</dl>";
        }
        $(".expand_param_tit").after(expandParamHtml);
        var paramHtml = "";
        if(params.length>0){
        	for (var i = 0; i < params.length; i++)
	        {
	            paramHtml = paramHtml + "<tr class='type_params'><th>" + params[i].paramName + "</th><td><input type='hidden' name='paramId' value='" + params[i].paramId + "'/><input type='text' value='' class='up_text' name='paramValue'/></td></tr>";
	        }
        	 paramHtml = paramHtml + "";
        }else{
        	 paramHtml = paramHtml + "您选择的商品分类下没有详细参数!";
        }
        $(".detail_param_tit").after(paramHtml);
    });
}
/*验证商品介绍*/
function checkDesc(){
	var bValid=true;
	bValid = checkSpecSymb($(".goods_brief"),$(".goods_brief_tips")) && bValid;
	return bValid;
}
/*根据第三方分类ID 查询相关的商品*/
function loadAllAboutGoodsByThirdCatId(pageNo){
	$.get("queryAboutGoodsByThirdInfo.htm?goodsCatId="+$(".goodsCatId").val()+"&pageNo="+pageNo,function(data){
		var goodsList= data.list;
		/*加载相关商品列表*/
		var html="";
		var footHtml="";
		if(null != goodsList && goodsList.length>0){
			for(var i=0;i<goodsList.length;i++){
				if(goodsList[i].goodsId != $(".now_goodsId").val()){
					html+="<tr>"+
					"<td><input type='checkbox' name='catChkAboutGoods' class='chk_about_goods chk_about_goods"+goodsList[i].goodsId+"' onclick='chkGoods(this);'  value='"+goodsList[i].goodsId+"'  /></td>"+
					"<td>"+(i-1+2)+"</td>"+
					"<td><img alt='' width='50px' height='50px' src='"+goodsList[i].goodsImg+"' /></td>"+
					"<td class='pr_name'>"+goodsList[i].goodsName+"</td>"+
					"<td>"+goodsList[i].goodsNo+"</td>"+
					"<td>¥<span class='pr_price'>"+goodsList[i].goodsPrice+"</span></td>"+
					"<td>"+goodsList[i].goodsCate.catName+"</td>"+
					"<td>"+goodsList[i].goodsBrand.brandName+"</td>"+
					"</tr>";
				}
			}
			/*加载表格foot*/
			footHtml+="<a class='p_prev' onclick='loadAllAboutGoodsByThirdCatId("+data.prePageNo+");' href='javascript:;'>&nbsp;</a>";
			for(var j=data.startNo;j<=data.endNo;j++){
				footHtml+="<a href='javascript:;'  onclick='loadAllAboutGoodsByThirdCatId("+j+");' >"+j+"</a>";
			}
			footHtml+="<a class='p_next' onclick='loadAllAboutGoodsByThirdCatId("+data.nextPageNo+");' href='javascript:;'>&nbsp;</a>";
		}else{
			html+="<tr><td colspan='8'><center>您选择的分类下暂无商品信息!</center></td></tr>";
		}
		$(".about_goods_list_tab tbody").html(html);
		$(".about_goods_list_foot").html(footHtml);
		
		/*获取已经选中的相关商品的集合,然后去匹配页面上的显示的列表,保存选中状态*/
		var alch_about_goods = $(".aboutGoodsId");
		if(alch_about_goods != null && alch_about_goods.length>0){
			for(var i =0;i<alch_about_goods.length;i++){
				$(".chk_about_goods"+$(alch_about_goods[i]).val()).attr("checked","checked");
			}
		}
	});
}
/*选中关联商品的时候触发*/
function chkGoods(obj){
	var chec = $(obj);
	if($(chec).prop("checked")){
		$(".sa_third_goods").append("<input type='hidden' name='aboutGoodsId' class='aboutGoodsId aboutGoodsId"+chec.val()+"' value='"+chec.val()+"' />");
	}else{
		$(".aboutGoodsId"+chec.val()).remove();
	}
}
/*验证SEO设置*/
function checkSeo(){
	var bValid=true;
	bValid = checkSpecSymb($(".goodsSeoTitle"),$(".goods_seo_title_tip")) && bValid;
	bValid = checkSpecSymb($(".goodsSeoKeyword"),$(".goods_seo_keyword_tips")) && bValid;
	bValid = checkSpecSymb($(".goodsSeoDesc"),$(".goods_seo_desc_tips")) && bValid;
	return bValid;
}
/*点击保存图片*/
function saveChoooseTrademark(id,url){
	if(Object.prototype.toString.call(url) === '[object Array]') {//如果是数组(图片库上传)
		$(".goodsImage").val(url[0]);
		$("#goodsImage").attr("src",url[0]);
	} else {//如果是字符串(本地上传)
		if(url.lastIndexOf(",")!= -1){
			$(".goodsImage").val(url.substr(0,url.indexOf(",")));
			$("#goodsImage").attr("src",url.substr(0,url.indexOf(",")));
		}else{
			$(".goodsImage").val(url);
			$("#goodsImage").attr("src",url);
		}
	}
}

/**
 * 添加关联商品
 */
function showAddGoodsRelModal() {
    queryGoodsByParam2(1);
    $("#addGoodsRelModal").modal("show");
}
/**
 * 修改商品时用
 * @param pageNo
 */
function queryGoodsByParam2(pageNo) {
    var goodsName = $("#goods_name").val();
    var goodsNo = $("#goods_no").val();
    var goodsBrandId = $("#goodsBrandId").val();
    /*根据选中分类加载相关商品*/
    $.get("queryThirdGoodsByParamAjax.htm?goodsCateId=" + $('.goodsCatId').val() + "&pageNo=" + pageNo + "&goodsNo=" + goodsNo + "&goodsName=" + goodsName + "&goodsBrandId=" + goodsBrandId + "&showFlag=1", function (data)
    {
        var aboutGoodsHtml = "";
        var list = data.list;
        if (list.length > 0)
        {
            for (var i = 0; i < list.length; i++)
            {
                aboutGoodsHtml = aboutGoodsHtml + "<tr><td><input type='checkbox' goods_name='"+list[i].goodsName+"'goods_brand='"+list[i].goodsBrand.brandName+"' cate_name='"+list[i].goodsCate.catName+"' goods_no='"+list[i].goodsNo+"' goods_img='"+list[i].goodsImg+"' class='ch_about' name='aboutGoodsIdSelect' value='" + list[i].goodsId + "'/></td>" + "<td><img width='50' height='50' src=" + list[i].goodsImg + " /></td><td title='"+list[i].goodsNo+"'>" + list[i].goodsNo + "</td><td>" + list[i].goodsName + "</td>";
                aboutGoodsHtml = aboutGoodsHtml + "<td>" + list[i].goodsCate.catName + "</td><td>" + list[i].goodsBrand.brandName + "</td></tr>";
            }
        }
        else
        {
            aboutGoodsHtml = aboutGoodsHtml + "<tr><td colspan='6'>选择的分类下暂时还没有商品</td></tr>";
        }
        $("#aboutGoodsList tbody").html(aboutGoodsHtml);


        /*添加页脚*/
        $("#pageFoot").html("");
        var foot =  "";
        if(data.pageNo==1) {
            foot += '<span class="disabled"> 上一页 </span>';
        } else {
            foot += "<a href='javascript:void(0)' onclick='queryGoodsByParam(" + data.prePageNo + ")'>上一页</a>";
        }
        var i = 1;
        for (var l = data.startNo; l <= data.endNo; l++)
        {
            if ((i - 1 + data.startNo) == data.pageNo)
            {
                foot = foot + '<span class="current"> '+data.pageNo+' </span>';
            }
            else
            {
                foot = foot + "<a onclick='queryGoodsByParam(" + (i - 1 + data.startNo) + ")' href='javascript:void(0)'>" + (i - 1 + data.startNo) + "</a>";
            }
            i++;
        }
        foot = foot + "<a onclick='queryGoodsByParam(" + data.nextPageNo +  ")' href='javascript:void(0)'>下一页</a>" ;
        /*添加tfoot分页信息*/
        $("#pageFoot").html(foot);
    });

}
function queryGoodsByParam(pageNo) {
    var goodsName = $("#goods_name").val();
    var goodsNo = $("#goods_no").val();
    var goodsBrandId = $("#goodsBrandId").val();
    /*根据选中分类加载相关商品*/
    $.get("queryThirdGoodsByParamAjax.htm?goodsCateId=" + $('#parentId3').val() + "&pageNo=" + pageNo + "&goodsNo=" + goodsNo + "&goodsName=" + goodsName + "&goodsBrandId=" + goodsBrandId + "&showFlag=1", function (data)
    {
        var aboutGoodsHtml = "";
        var list = data.list;
        if (list.length > 0)
        {
            for (var i = 0; i < list.length; i++)
            {
                aboutGoodsHtml = aboutGoodsHtml + "<tr><td><input type='checkbox' goods_name='"+list[i].goodsName+"'goods_brand='"+list[i].goodsBrand.brandName+"' cate_name='"+list[i].goodsCate.catName+"' goods_no='"+list[i].goodsNo+"' goods_img='"+list[i].goodsImg+"' class='ch_about' name='aboutGoodsIdSelect' value='" + list[i].goodsId + "'/></td>" + "<td><img width='50' height='50' src=" + list[i].goodsImg + " /></td><td title='"+list[i].goodsNo+"'>" + list[i].goodsNo + "</td><td>" + list[i].goodsName + "</td>";
                aboutGoodsHtml = aboutGoodsHtml + "<td>" + list[i].goodsCate.catName + "</td><td>" + list[i].goodsBrand.brandName + "</td></tr>";
            }
        }
        else
        {
            aboutGoodsHtml = aboutGoodsHtml + "<tr><td colspan='6'>选择的分类下暂时还没有商品</td></tr>";
        }
        $("#aboutGoodsList tbody").html(aboutGoodsHtml);


        /*添加页脚*/
        $("#pageFoot").html("");
        var foot =  "";
        if(data.pageNo==1) {
            foot += '<span class="disabled"> 上一页 </span>';
        } else {
            foot += "<a href='javascript:void(0)' onclick='queryGoodsByParam(" + data.prePageNo + ")'>上一页</a>";
        }
        var i = 1;
        for (var l = data.startNo; l <= data.endNo; l++)
        {
            if ((i - 1 + data.startNo) == data.pageNo)
            {
                foot = foot + '<span class="current"> '+data.pageNo+' </span>';
            }
            else
            {
                foot = foot + "<a onclick='queryGoodsByParam(" + (i - 1 + data.startNo) + ")' href='javascript:void(0)'>" + (i - 1 + data.startNo) + "</a>";
            }
            i++;
        }
        foot = foot + "<a onclick='queryGoodsByParam(" + data.nextPageNo +  ")' href='javascript:void(0)'>下一页</a>" ;
        /*添加tfoot分页信息*/
        $("#pageFoot").html(foot);
    });

}

/**
 * 保存关联商品
 */
function saveRelGoods() {
    var html = '';
    var selecteOne = false;
    $("input[name=aboutGoodsIdSelect]:checkbox").each(function () {
        if($(this).is(':checked')==true) {
            selecteOne = true;
            if($("#selectedGoods"+$(this).val())==undefined || $("#selectedGoods"+$(this).val()).length<=0) {
                html += '<tr id="rel_goods_tr'+$(this).val()+'">' +
                '   <td><img src="'+$(this).attr("goods_img")+'" width="50px"></td>' +
                '   <td><input type="hidden" name="aboutGoodsId" value="'+$(this).val()+'"/><input type="hidden" id="selectedGoods'+$(this).val()+'" value="'+$(this).val()+'"/>' +
                '       <span>'+$(this).attr("goods_no")+'</span>' +
                '   </td>' +
                '   <td><span>'+$(this).attr("goods_name")+'</span></td>' +
                '   <td><span>'+$(this).attr("cate_name")+'</span></td>' +
                '   <td><span>'+$(this).attr("goods_brand")+'</span></td>' +
                '   <td><span><a href="javascript:;" onclick="$(\'#rel_goods_tr'+$(this).val()+'\').remove();">删除</a></span></td>' +
                '</tr>'
            }
        }
    });
    if(!selecteOne) {
        showTipAlert("至少选择一个商品，才能保存！");
        return;
    }
    $("#oldAboutGoodsList tbody").append(html);
    $("#addGoodsRelModal").modal("hide");
}
