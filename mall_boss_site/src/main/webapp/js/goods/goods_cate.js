$(function () {
    loadFirstCate(0);
    $('select[data-live-search="true"]').select2();
})
/**
 * 加载一级分类
 * @param parentId 父类id
 */
function loadFirstCate(parentId,cateName) {
    var url = 'querySonCateByCatIdAndName.htm?CSRFToken='+$("#CSRFToken").val()+'&catId='+parentId;
    if(cateName!=''&&cateName!=undefined) {
        url += '&cateName='+cateName;
    }
    $("#cate_list1").html('');
    $.ajax({
        url:url,
        success: function (data) {
            var html = '';
            html += '<a class="add_cart_btn" href="javascript:;" onclick="showAddCate()"><i class="glyphicon glyphicon-plus"></i> 添加一级分类</a>';
            for(var i=0;i<data.length;i++) {
                var cate = data[i];
                html += '<div class="cate_item" id="cate_item'+cate.catId+'" onclick="loadSecondCate(this,'+cate.catId+')">'+
                '<h4>'+cate.catName+'</h4>'+
                '<div class="c_btns">'+
                '<a href="javascript:;"><span class="glyphicon glyphicon-edit" onclick="showEditCate(1,'+cate.catId+')"></span></a>'+
                '<a href="javascript:;" onclick="deleteGoodsCate('+cate.catId+')"><span class="glyphicon glyphicon-trash"></span></a>'+
                '</div>'+
                '</div>';
            }

            $("#cate_list1").html(html);
            $("#cate_list1").find("div.cate_item")[0].click();
        }
    });
}

/**
 * 加载二级分类
 * @param obj 按钮对象
 * @param parentId 父类id
 */
function loadSecondCate(obj,parentId,cateName) {
    clickItem(obj);
    $("#parentId1").val(parentId);
    $("#cate_list2").html('');
    var url = 'querySonCateByCatIdAndName.htm?CSRFToken='+$("#CSRFToken").val()+'&catId='+parentId;
    if(cateName!=''&& cateName!=undefined) {
        url += '&cateName='+cateName;
    }
    $.ajax({
        url:url,
        success: function (data) {
            var html = '';
            html += '<a class="add_cart_btn" href="javascript:;" onclick="showAddCate(0)"><i class="glyphicon glyphicon-plus"></i> 添加二级分类</a>';
            for(var i=0;i<data.length;i++) {
                var cate = data[i];
                html += '<div class="cate_item" id="cate_item'+cate.catId+'" onclick="loadThirdCate(this,'+cate.catId+')">'+
                '<h4>'+cate.catName+'</h4>'+
                '<div class="c_btns">'+
                '<a href="javascript:;"><span class="glyphicon glyphicon-edit" onclick="showEditCate(2,'+cate.catId+')"></span></a>'+
                '<a href="javascript:;" onclick="deleteGoodsCate('+cate.catId+')"><span class="glyphicon glyphicon-trash"></span></a>'+
                '</div>'+
                '</div>';
            }
            $("#cate_list2").html(html);
            if($("#cate_list2").find("div.cate_item").length>0) {
                $("#cate_list2").find("div.cate_item")[0].click();
            } else {

            }

        }
    });
}

/**
 * 加载三级分类
 * @param obj 按钮对象
 * @param parentId 父类id
 */
function loadThirdCate(obj,parentId,cateName) {
    clickItem(obj);
    $("#parentId2").val(parentId);
    $("#cate_list3").html('');
    var url = 'querySonCateByCatIdAndName.htm?CSRFToken='+$("#CSRFToken").val()+'&catId='+parentId;
    if(cateName!=''&& cateName!=undefined) {
        url += '&cateName='+cateName;
    }
    $.ajax({
        url:url,
        success: function (data) {
            var html = '';
            html += '<a class="add_cart_btn" href="javascript:;" onclick="showAddCate(1)"><i class="glyphicon glyphicon-plus"></i> 添加三级分类</a>';
            for(var i=0;i<data.length;i++) {
                var cate = data[i];
                html += '<div class="cate_item" id="cate_item'+cate.catId+'" onclick="clickThirdCate(this,'+cate.catId+')">'+
                '<h4>'+cate.catName+'['+(cate.goodsCount==null?0:cate.goodsCount)+'件商品]</h4>'+
                '<div class="c_btns">'+
                '<a href="javascript:;"><span class="glyphicon glyphicon-edit" onclick="showEditCate(3,'+cate.catId+')"></span></a>'+
                '<a href="javascript:;" onclick="deleteGoodsCate('+cate.catId+')"><span class="glyphicon glyphicon-trash"></span></a>'+
                '</div>'+
                '</div>';
            }
            $("#cate_list3").html(html);
            if($("#cate_list3").find("div.cate_item").length>0) {
                $("#cate_list3").find("div.cate_item")[0].click();
            } else {

            }
        }
    });
}

function clickThirdCate(obj,parentId) {
    $("#parentId3").val(parentId);
    clickItem(obj);
}

/**
 * 点击按钮时，改变按钮状态。
 * @param obj 按钮对象
 */
function clickItem(obj) {
    if(obj==null) return;
    $(obj).parent().find("div.cate_item").each(function () {
        $(this).removeClass("active");
    });
    $(obj).addClass("active");
}

/**
 * 查找一级分类
 */
function findFirstGoodsCate() {
    var cateName = $("#search_name1").val();
    loadFirstCate(0,cateName);
}

/**
 * 查找二级分类
 */
function findSecondGoodsCate() {
    var cateName = $("#search_name2").val();
    var parentId = $("#parentId1").val();
    loadSecondCate(null,parentId,cateName);
}

/**
 * 查找三级分类
 */
function findThirdGoodsCate() {
    var cateName = $("#search_name3").val();
    var parentId = $("#parentId2").val();
    loadThirdCate(null,parentId,cateName);
}

/**
 * 弹框显示添加分类
 * @param level 商品是属于几级分类（一级，二级，三级）
 */
var num=0;
function showAddCate(level) {
    num=0;
    $("#saveGoodsCateForm")[0].reset();
    var parentId = $("#parentId"+level).val();
    $("#cur_level").val(level);
    if(parentId!=undefined) {
        $.ajax({
            url:'querySonCateByCatIdAndName.htm?CSRFToken='+$("#CSRFToken").val()+'&catId='+parentId,
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
    $('#cateAdd').modal("show");
}

/**
 * 确定提交添加商品分类
 */
function submitSaveGoodsCateForm() {
    //if($("#saveGoodsCateForm").find(".error").length>0) return;
    var hasNotError = true;
    $("#saveGoodsCateForm").find(".error").each(function() {
            if($(this).css("display")!='none') {
                hasNotError = false;
            }
    });
    if(!hasNotError) return;
    if(!$("#saveGoodsCateForm").valid()) return;
    var level = $("#cur_level").val();
    var parentId = $("#parentId"+(parseInt(level)+1)).val();
    $("#add_cate_rate").val($("#add_cate_rate").val()/100);
    if(num==0){
        $.ajax({
            url:'addGoodsCate.htm?CSRFToken='+$("#CSRFToken").val()+'&'+$("#saveGoodsCateForm").serialize(),
            success: function (data) {
                if(level=='') {
                    location.reload();
                } else {
                    num+=1;
                    $("#cate_item"+parentId).click();
                }
            }
        });
    }

    $('#cateAdd').modal("hide");
}

/**
 * 删除商品分类
 * @param cateId 分类Id
 */
function deleteGoodsCate(cateId) {
    $.get("checkDel.htm?CSRFToken="+$("#CSRFToken").val()+"&cateId=" + cateId, function (data) {
        if (data) {
            showDeleteOneConfirmAlert("delGoodsCate.htm?CSRFToken="+$("#CSRFToken").val()+"&cateId=" + cateId);
        } else {
            //输入错误
           showTipAlert("该分类下包含子分类，不能删除！");
        }
    });
}

/**
 * 弹框显示编辑商品分类
 * @param level 商品是属于几级分类
 * @param cateId 分类Id
 */
function showEditCate(level,cateId) {
    $("#cur_level").val(level);
    var parentId = $("#parentId"+(parseInt(level)-2)).val();
    $.get("queryCateById.htm?CSRFToken="+$("#CSRFToken").val()+"&cateId=" + cateId, function (data) {
        $("#update_catId").val(data.catId);
        $("#update_cate_name").val(data.catName);
        $("#oldCatName").val(data.catName);
        $("#update_goods_type").val(data.typeId);
        $("#update_cate_sort").val(data.catSort);
        $("#update_cat_rate").val(data.catRate * 100);
        if(level!=1) {
            $.ajax({
                url:'querySonCateByCatIdAndName.htm?CSRFToken='+$("#CSRFToken").val()+'&catId='+parentId,
                success: function (result) {
                    var html = '';
                    for(var i=0;i<result.length;i++) {
                        var cate = result[i];
                        html += '<option value="'+cate.catId+'">'+cate.catName+'</option>';
                    }
                    $('#update_parent_cate').html(html);
                    $("#update_parent_cate option[value="+data.catParentId+"]").attr("selected",true);
                    $('select[data-live-search="true"]').select2();
                }
            });
        }else {
            $('#update_parent_cate').html('<option value="0">所有</option>');
            $("#update_parent_cate option[value=0]").attr("selected",true);
            $('select[data-live-search="true"]').select2();
        }

        $('select[data-live-search="true"]').select2();
        $( "#cateEdit" ).modal( "show" );
    });
}

/**
 * 编辑商品分类
 */
function submitUpdateGoodsCateForm() {
    var hasNotError = true;
    $("#updateCateForm").find(".error").each(function() {
        if($(this).css("display")!='none') {
            hasNotError = false;
        }
    });
    if(!hasNotError) return;
    if(!$("#updateCateForm").valid()) return;
    var level = $("#cur_level").val();
    var parentId = $("#parentId"+(parseInt(level)-1)).val();
    $("#update_cat_rate").val($("#update_cat_rate").val()/100);
    $.ajax({
        url:'updateGoodsCate.htm?CSRFToken='+$("#CSRFToken").val()+'&'+$("#updateCateForm").serialize(),
        success: function (data) {
            if(level=='1') {
                location.reload();
            } else {
                $("#cate_item"+parentId).click();
            }
        }
    });
    $('#cateEdit').modal("hide");
}

/**
 * 添加分类时，分类名称onblur，检查分类名是否重复
 * @param obj
 */
function checkCatNameExist(obj) {
    $(obj).removeClass( "error" );
    $("#cateName_exist_tip").remove();
    var cateName = $(obj).val();
    $.post("checkCateName.htm?CSRFToken="+$("#CSRFToken").val()+"&cateName=" + cateName, function (data) {
        if (data == false) {
            $(obj).addClass("error");
            $(obj).after('<label generated="true" class="error value_tip" id="cateName_exist_tip">分类名称已存在</label>');
        }
    });
}

/**
 * 编辑分类时，分类名称onblur，检查分类名是否重复
 * @param obj
 */
function checkUpdateCatNameExist(obj) {
    $(obj).removeClass( "error" );
    $("#cateName_exist_tip").remove();
    var oldCatName = $("#oldCatName").val();
    var cateName = $(obj).val();
    if(cateName!=oldCatName) {
        $.post("checkCateName.htm?CSRFToken="+$("#CSRFToken").val()+"&cateName=" + cateName, function (data) {
            if (data == false) {
                $(obj).addClass("error");
                $(obj).after('<label generated="true" class="error value_tip" id="cateName_exist_tip">分类名称已存在</label>');
            }
        });
    }
}