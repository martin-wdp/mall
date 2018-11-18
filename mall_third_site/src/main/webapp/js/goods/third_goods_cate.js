$(function(){
	/*加载一级商品分类*/
	loadThirdFirstCategory(0);
	
	//添加分类时 当分类名称失去焦点验证分类名称是否存在
	$("#cate_name").blur(function(){
		if (checkNull($("#cate_name"), $(".catNameTip")))
        {
            if (checkSpecSymb($("#cate_name"), $(".catNameTip")))
            {
                if ($(".oldCatName").val() != "")
                {
                    checkExists('checkThirdCateName', 'cateName', $(this), 'catNameTip', 1, "oldCatName");
                }
                else {
                    checkExists('checkThirdCateName', 'cateName', $(this), 'catNameTip', 0, null);
                }
            }
        }
		
	});
	$("#catSort").blur(function(){
		if(checkNull($("#catSort"), $(".catSortTip"))){
			checkRegexp( $("#catSort"), /^[0-9]+$/, "排序必须为正整数." , $(".catSortTip"));
		}
	});
		
	
	/*修改分类时 当分类名称失去焦点验证分类名称是否存在*/
	$("#update_cate_name").blur(function(){
		if (checkNull($("#update_cate_name"), $(".catNameTipEdit")))
        {
            if (checkSpecSymb($("#update_cate_name"), $(".catNameTipEdit")))
            {
                if ($(".oldCatName").val() != "")
                {
                    checkExists('checkThirdCateName', 'cateName', $(this), 'catNameTipEdit', 1, "oldCatName");
                }
                else {
                    checkExists('checkThirdCateName', 'cateName', $(this), 'catNameTipEdit', 0, null);
                }
            }
        }
		
	});
	
	$("#update_catSort").blur(function(){
		if(checkNull($("#update_catSort"), $(".catSortTipEdit"))){
			checkRegexp( $("#update_catSort"), /^[0-9]+$/, "排序必须为正整数." , $(".catSortTipEdit"));
		}
	});
	
	/*关闭添加的弹出框*/
    $("#closeBtn01").click(function ()
    {
        $(".upCatId").remove();
        $(".oldCatName").remove();
        $(".addThirdCate")[0].reset();
        $(".catparentId").val(0);
        $(".ui-state-error").removeClass("ui-state-error");
        $(".ui-state-highlight").text("").removeClass("ui-state-highlight");
    });
    
    /*关闭添加的弹出框*/
    $("#closeBtn02").click(function ()
    {
        $(".upCatId").remove();
        $(".oldCatName").remove();
        $(".addThirdCate")[0].reset();
        $(".catparentId").val(0);
        $(".ui-state-error").removeClass("ui-state-error");
        $(".ui-state-highlight").text("").removeClass("ui-state-highlight");
    });
   
	
});

/**
 * 加载一级商品分类
 * @param parentId
 * @param cateName
 */
function loadThirdFirstCategory(parentId,cateName){
	var url = 'queryThirdSonCateByCateIdAndName.htm?thirdCateId='+parentId;
	if(cateName!=''&&cateName!=undefined){
		url += '&thirdCateName=' + cateName;
	}
	
	$('#third_cate_list1').html('');
	$('#third_cate_list2').html('');
	$('#third_cate_list3').html('');
	
	$.ajax({
		url:url,
		success:function(data){
			var html = '';
			for(var i=0;i<data.length;i++){
				var thirdCate = data[i];
				html +='<li id="cate_item'+thirdCate.catId+'" onclick="loadThirdSecondCategory(this,'+thirdCate.catId+')">'+
				'<h4 style="cursor: default">'+thirdCate.catName+'</h4>'+
				'<div class="c-btns" >'+
				'<a href="javascript:;"><i data-toggle="modal" class="glyphicon glyphicon-edit" onclick="showEditCate(1,'+thirdCate.catId+')"></i></a>'+
				'<a href="javascript:;"><i class="glyphicon glyphicon-trash" onclick="delThirdCate('+thirdCate.catId+')"></i></a>'+
				'</div>';
			}
			
			$('#third_cate_list1').html(html);
			if($("#third_cate_list1").find("li").length>0) {
                $("#third_cate_list1").find("li")[0].click();
            }
		}
	});
	
}

/**
 * 加载二级分类
 * @param obj
 * @param parentId
 * @param cateName
 */
function loadThirdSecondCategory(obj,parentId,cateName){
	clickItem(obj);
	$("#parentId1").val(parentId);
	$('#third_cate_list2').html('');
	$('#third_cate_list3').html('');
	var url = 'queryThirdSonCateByCateIdAndName.htm?thirdCateId='+parentId;
	if(cateName!=''&& cateName!=undefined) {
        url += '&thirdCateName='+cateName;
    }
	$.ajax({
		url:url,
		success:function(data){
			var html = '';
			for(var i=0;i<data.length;i++){
				var thirdCate = data[i];
				html +='<li id="cate_item'+thirdCate.catId+'" onclick="loadThirdThirdCategory(this,'+thirdCate.catId+')">'+
				'<h4 style="cursor: default">'+thirdCate.catName+'</h4>'+
				'<div class="c-btns" >'+
				'<a href="javascript:;"><i data-toggle="modal" class="glyphicon glyphicon-edit" onclick="showEditCate(2,'+thirdCate.catId+')"></i></a>'+
				'<a href="javascript:;"><i class="glyphicon glyphicon-trash" onclick="delThirdCate('+thirdCate.catId+')"></i></a>'+
				'</div>';
			}
			
			$('#third_cate_list2').html(html);
			
            if($("#third_cate_list2").find("li").length>0) {
                $("#third_cate_list2").find("li")[0].click();
            }
		}
	}); 
}

/**
 * 加载三级分类
 * @param obj
 * @param parentId
 * @param cateName
 */
function loadThirdThirdCategory(obj,parentId,cateName){
	clickItem(obj);
	$("#parentId2").val(parentId);
	$('#third_cate_list3').html('');
	
	var url = 'queryThirdSonCateByCateIdAndName.htm?thirdCateId='+parentId;
	if(cateName!=''&& cateName!=undefined) {
        url += '&thirdCateName='+cateName;
    }
	$.ajax({
		url:url,
		success:function(data){
			var html = '';
			for(var i=0;i<data.length;i++){
				var thirdCate = data[i];
				html +='<li id="cate_item'+thirdCate.catId+'" onclick="clickThirdCate(this,'+thirdCate.catId+')">'+
				'<h4 style="cursor: default">'+thirdCate.catName+'</h4>'+
				'<div class="c-btns" >'+
				'<a href="javascript:;"><i data-toggle="modal" class="glyphicon glyphicon-edit" onclick="showEditCate(3,'+thirdCate.catId+')"></i></a>'+
				'<a href="javascript:;"><i class="glyphicon glyphicon-trash" onclick="delThirdCate('+thirdCate.catId+')"></i></a>'+
				'</div>';
			}
			
			$('#third_cate_list3').html(html);
			
            if($("#third_cate_list3").find("li").length>0) {
                $("#third_cate_list3").find("li")[0].click();
            }
		}
	}); 
}
/**
 * 点击按钮时，改变按钮状态。
 * @param obj 按钮对象
 */
function clickItem(obj) {
    if(obj==null) return;
    $(obj).parent().find("li").each(function () {
        $(this).removeClass("active");
    });
    $(obj).addClass("active");
}

function clickThirdCate(obj,parentId) {
    $("#parentId3").val(parentId);
    clickItem(obj);
}

function findFirstGoodsCate() {
    var cateName = $("#search_name1").val();
    loadThirdFirstCategory(0,cateName);
}

function findSecondGoodsCate() {
    var cateName = $("#search_name2").val();
    var parentId = $("#parentId1").val();
    loadThirdSecondCategory(null,parentId,cateName);
}

function findThirdGoodsCate() {
    var cateName = $("#search_name3").val();
    var parentId = $("#parentId2").val();
    loadThirdThirdCategory(null,parentId,cateName);
}


/*当表单提交的时候验证*/
var num=0;
function chkThirdCateForm()
{
	
    var bValid = true;
    bValid = checkSpecSymb($("#cate_name"), $(".catNameTip")) && bValid;
    bValid = checkRegexp( $("#catSort"), /^[0-9]+$/, "排序必须为正整数." , $(".catSortTip")) && bValid;
    bValid = checkSpecSymb($("#catSeoTitle"), $(".catSeoTitleTip")) && bValid;
    bValid = checkSpecSymb($("#catSeoKeyword"), $(".catSeoKeywordTip")) && bValid;
    bValid = checkSpecSymb($("#catSeoDesc"), $(".catSeoDescTip")) && bValid;
    if(checkSpecSymb($("#cate_name"), $(".catNameTip"))){
    	 bValid = checkNull($("#cate_name"), $(".catNameTip")) && bValid;
    }
    if ($(".checkExistsFlag").val() == "0") {
    	$("#cate_name").addClass("ui-state-error");
		updateTips("名称已经存在!",$(".catNameTip"));
        bValid = false;
    }
    if (bValid&&num==0) {
        num+=1;
    	$(".addThirdCate").attr("action", "saveThirdCate.htm");
        $(".addThirdCate").submit();
        $(".addThirdCate").attr("action","");
    }
}

/**
 * 加载上架分类
 * @param level
 */
function showAddCate(level) {
    //$("#addThirdCate")[0].reset();
    num=0;
    var parentId = $("#parentId"+level).val();
    $("#cur_level").val(level);
    if(parentId!=undefined) {
        $.ajax({
            url:'queryThirdSonCateByCateIdAndName.htm?thirdCateId='+parentId,
            success: function (data) {
                var html = '';
                for(var i=0;i<data.length;i++) {
                    var cate = data[i];
                    html += '<option value="'+cate.catId+'">'+cate.catName+'</option>';
                }
                $('#parentCate_add').html(html);

                $("#parentCate_add option[value="+$("#parentId"+parseInt(level+1)).val()+"]").attr("selected",true);
                $('select[data-live-search="true"]').select2();
            }
        });
    } else {
        $('#parentCate_add').html('<option value="0">所有</option>');
        $("#parentCate_add option[value=0]").attr("selected",true);
        $('select[data-live-search="true"]').select2();
    }

    //$('#cateAdd').modal("show");
}

function showEditCate(level,cateId) {
    $("#cur_level").val(level);
    var parentId = $("#parentId"+(parseInt(level)-2)).val();
    $(".upCatId").val(cateId);
    //$(".editThirdCate").append("<input type='hidden' name='catId' class='upCatId' value='" + cateId + "' />");
    $.get("queryThirdCateByCateId.htm?catId=" + cateId, function (data) {
    	//$(".editThirdCate").append("<input type='hidden' class='oldCatName' value='" + data.catName + "' />");
    	$(".oldCatName").val(data.catName);
    	$("#update_catId").val(data.catId);
    	$("#update_cate_name").val(data.catName);
        $(".catparentId").val(data.catParentId);
        $("#update_catSort").val(data.catSort);
        $("#update_catSeoTitle").val(data.catSeoTitle);
        $("#update_cateSeoKeyword").val(data.catSeoKeyword);
        $("#update_catSeoDesc").val(data.catSeoDesc);
        if(level!=1) {
            $.ajax({
                url:'queryThirdSonCateByCateIdAndName.htm?thirdCateId='+parentId,
                success: function (result) {
                    var html = '';
                    for(var i=0;i<result.length;i++) {
                        var cate = result[i];
                        html += '<option value="'+cate.catId+'">'+cate.catName+'</option>';
                    }
                    $('#update_parent_cate').html(html);
                    $("#update_parent_cate option[value="+data.catParentId+"]").attr("selected",true);
                }
            });
        }else {
            $('#update_parent_cate').html('<option value="0">所有</option>');
            $("#update_parent_cate option[value=0]").attr("selected",true);
        }
        
        $("#cateEdit").modal('show');
    });
}

/*删除第三方分类*/
function delThirdCate(catId)
{
    $.get("checkThirdCateDel.htm?thirdCateId=" + catId, function (data)
    {
        if (!data) {
            $("#operate-tip").modal('show');
        }
        else {
        	$("#thirdCateId").val(catId);
        	$("#delCate").attr("action","delThirdCate.htm");
        	$("#delete-tip").modal('show');
        }
    });
}

function delThirdGIds(){
	$("#delCate").submit();
}

/*当表单提交的时候验证*/
function chkEditThirdCateForm()
{
	
    var bValid = true;
    bValid = checkSpecSymb($("#update_cate_name"), $(".catNameTipEdit")) && bValid;
    bValid = checkRegexp( $("#update_catSort"), /^[0-9]+$/, "排序必须为正整数." , $(".catSortTipEdit")) && bValid;
    bValid = checkSpecSymb($("#update_catSeoTitle"), $(".catSeoTitleTipEdit")) && bValid;
    bValid = checkSpecSymb($("#update_cateSeoKeyword"), $(".catSeoKeywordTipEdit")) && bValid;
    bValid = checkSpecSymb($("#update_catSeoDesc"), $(".catSeoDescTipEdit")) && bValid;
    if(checkSpecSymb($("#update_cate_name"), $(".catNameTipEdit"))){
    	 bValid = checkNull($("#update_cate_name"), $(".catNameTipEdit")) && bValid;
    }
    if ($(".checkExistsFlag").val() == "0") {
    	$("#update_cate_name").addClass("ui-state-error");
		updateTips("名称已经存在!",$(".catNameTipEdit"));
        bValid = false;
    }
    if (bValid) {
    	$(".editThirdCate").attr("action", "updateThirdCate.htm");
        $(".editThirdCate").submit();
        $(".editThirdCate").attr("action","");
    }
}
