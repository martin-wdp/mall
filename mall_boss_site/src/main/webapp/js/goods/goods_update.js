$(function () {
    //加载一级分类
    loadFirstCate(0);

    /* 富文本编辑框 */
    $('.summernotedesc').summernote({
        height: 300,
        tabsize: 2,
        lang: 'zh-CN',
        onImageUpload: function(files, editor, $editable) {
            sendFile(files,editor,$editable);
        }
    });

    /* 富文本编辑框 */
    $('.summernotemobile').summernote({
        height: 300,
        tabsize: 2,
        lang: 'zh-CN',
        onImageUpload: function(files, editor, $editable) {
            sendFile(files,editor,$editable);
        }
    });
});
/**
 * 加载一级分类
 * @param parentId 父类id
 */
function loadFirstCate(parentId,cateName) {

    var url = 'querySonCateByCatIdAndName.htm?CSRFToken='+$("#CSRFToken").val()+'&catId='+parentId;
    if(cateName!=''&&cateName!=undefined) {
        url += '&cateName='+cateName;
    }
    //清空三个分类框
    $("#cate_list1").html('');
    $("#cate_list2").html('');
    $("#cate_list3").html('');

    $.ajax({
        url:url,
        success: function (data) {
            var html = '';
            for(var i=0;i<data.length;i++) {
                var cate = data[i];
                html += '<div class="cate_item" id="cate_item'+cate.catId+'" onclick="loadSecondCate(this,'+cate.catId+',null,\''+cate.catName+'\')">'+
                '<h4>'+cate.catName+'</h4>'+
                '</div>';
            }
            $("#cate_list1").html(html);
            if($("#cate_list1").find("div.cate_item").length>0) {
                var cateGrade = $("#cateGrade").val();
                //如果是三级分类
                if(cateGrade=='3') {
                    //查询一级分类
                    $.ajax({
                        url:'queryCateById.htm?CSRFToken='+$("#CSRFToken").val()+'&cateId='+$("#cateParentId").val(),
                        async:false,
                        success:function(data) {
                            $("#cate_item"+data.catParentId).click();
                        }
                    });
                } else {
                    //自动点击第一个分类
                    $("#cate_list1").find("div.cate_item")[0].click();
                }

                $("#productName1").text($("#cate_list1").find("div.active h4").html());
                
               
            } else {
                $("#cate_list2").html("");
                $("#cate_list3").html("");
                $("#parentId1").val('');
                $("#parentId2").val('');
                $("#parentId3").val('');
            }

        }
    });
}
/**
 * 加载二级分类
 * @param obj 按钮对象
 * @param parentId 父类id
 */
function loadSecondCate(obj,parentId,cateName,chooseName) {
    clickItem(obj);
    $("#productName1").text(chooseName);
    $("#parentId1").val(parentId);
    $("#cate_list2").html('');
    $("#cate_list3").html('');
    var url = 'querySonCateByCatIdAndName.htm?CSRFToken='+$("#CSRFToken").val()+'&catId='+parentId;
    if(cateName!=''&& cateName!=undefined) {
        url += '&cateName='+cateName;
    }
    $.ajax({
        url:url,
        success: function (data) {
            var html = '';
            for(var i=0;i<data.length;i++) {
                var cate = data[i];
                html += '<div class="cate_item" id="cate_item'+cate.catId+'" onclick="loadThirdCate(this,'+cate.catId+',null,\''+cate.catName+'\')">'+
                '<h4>'+cate.catName+'</h4>'+
                '</div>';
            }
            $("#cate_list2").html(html);

            if($("#cate_list2").find("div.cate_item").length>0) {
                var cateGrade = $("#cateGrade").val();
                //如果是三级分类
                if(cateGrade=='3' && $("#cate_item"+$("#cateParentId").val()).length>0) {
                    $("#cate_item"+$("#cateParentId").val()).click();
                } else {
                    //自动点击第一个分类，
                    $("#cate_list2").find("div.cate_item")[0].click();
                }

               $("#productName2").text($("#cate_list2").find("div.active h4").html());
            } else {
                $("#cate_list3").html("");
                $("#parentId2").val('');
                $("#parentId3").val('');
            }

        }
    });
}
/**
 * 加载三级分类
 * @param obj 按钮对象
 * @param parentId 父类id
 */
function loadThirdCate(obj,parentId,cateName,chooseName) {
    clickItem(obj);
    $("#parentId2").val(parentId);
    $("#cate_list3").html('');
    $("#productName2").text(chooseName);
    var url = 'querySonCateByCatIdAndName.htm?CSRFToken='+$("#CSRFToken").val()+'&catId='+parentId;
    if(cateName!=''&& cateName!=undefined) {
        url += '&cateName='+cateName;
    }
    $.ajax({
        url:url,
        success: function (data) {
            var html = '';
            for(var i=0;i<data.length;i++) {
                var cate = data[i];
                html += '<div class="cate_item" id="cate_item'+cate.catId+'" onclick="clickThirdCate(this,'+cate.catId+',\''+cate.catName+'\')">'+
                '<h4>'+cate.catName+'['+(cate.goodsCount==null?0:cate.goodsCount)+'件商品]</h4>'+
                '</div>';
            }
            $("#cate_list3").html(html);
            //自动点击第一个分类
            if($("#cate_list3").find("div.cate_item").length>0) {
                var cateGrade = $("#cateGrade").val();
                //如果是三级分类
                if(cateGrade=='3' && $("#cate_item"+$("#cateId").val()).length>0) {
                    $("#cate_item"+$("#cateId").val()).click();
                } else {
                    $("#cate_list3").find("div.cate_item")[0].click();
                }
               $("#productName3").text($("#cate_list3").find("div.active h4").html());
            } else {
                $("#cate_list3").html("");
                $("#parentId3").val('');
            }
        }
    });
}

function clickThirdCate(obj,parentId,chooseName) {
    $("#parentId3").val(parentId);
    $("#productName3").text(chooseName);
    clickItem(obj);
    loadTypeParam();
    if(parentId == $("#cateId").val()) {
        $("#spec_form").hide();
    } else {
        $("#spec_form").show();
    }
    $("#product_form").hide();
    //将所有货品的更新标记都去掉
    $(".updateFlag").remove();
    //将已经选好的规格都去掉
    $(".select_specs").remove();
    //给原有的规格加红色标记，表示未重置规格
    $(".spec_info").addClass("text-danger");
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
 * 搜索一级分类
 */
function findFirstGoodsCate() {
    var cateName = $("#search_name1").val();
    loadFirstCate(0,cateName);
}
/**
 * 搜索二级分类
 */
function findSecondGoodsCate() {
    var cateName = $("#search_name2").val();
    var parentId = $("#parentId1").val();
    loadSecondCate(null,parentId,cateName,null);
}
/**
 * 搜索三级分类
 */
function findThirdGoodsCate() {
    var cateName = $("#search_name3").val();
    var parentId = $("#parentId2").val();
    loadThirdCate(null,parentId,cateName);
}

//判断分类
function checkCate(){
    if($("#parentId3").val()==''){
        return false;
    }else{
        return true;
    }
}

function panelNext(hidepanel,showpanel){
    if(hidepanel=='#catepanel') {
        if($("#parentId3").val()!=$("#cateId").val()) {
            var productSize = $("#productSize").val();
            var selectSpecs = loadSelectSpecs();
            if(parseInt(productSize)>selectSpecs) {
                showTipAlert("您已经修改了分类，需要重新设置原有货品的规格。<br/>但根据选择的规格进行组合后，仍然不够原有货品选择。<br/>组合后可生成"+selectSpecs+"个,实际货品"+productSize+"个。请重新选择规格或分类!<br/>");
                return ;
            }
            var allProductsUpdated = true;
            var noUpdateCount = 0;
            $("#goods_info_table tbody tr").each(function () {
                if($(this).find(".updateFlag").val()!='1') {
                    noUpdateCount ++;
                    allProductsUpdated = false;
                }
            });
            if(!allProductsUpdated) {
                showTipAlert("您已经修改了分类，需要将所有货品的规格进行重置。<br/>检测到仍有"+noUpdateCount+"个货品未重置规格！")
                return;
            }
        }

    }
    if(hidepanel=='#panel1') {
        if(!$("#goodsBaseForm").valid()) {
            return;
        }
    }

    $(hidepanel).hide();
    $(showpanel).show();
  //  getResize();
}

function showOnePanel(showpanel) {
    $(".panel").hide();
    $(showpanel).show();
}

function showModifyProduct() {
    var productSize = $("#productSize").val();
    var selectSpecs = loadSelectSpecs();
    if(parseInt(productSize)>selectSpecs) {
        showTipAlert("选择的规格组合后，仍然不够原有货品选择，请重新选择规格或分类!<br/>组合后可生成"+selectSpecs+"个,实际货品"+productSize+"个");
        return ;
    }
    $("#product_form").show();
}

/*根据选中的第三级分类加载类型参数*/
function loadTypeParam(){
    $("#attribute tbody").html('');
    /*设置选中样式并且加载类型参数*/
    var catId = $("#parentId3").val();

    /*加载类型中的参数*/
    $.get("queryTypeVoByCatId.htm?catId=" +catId+"&CSRFToken="+$("#token_val").val(), function (data)
    {
        var expandParam = data.expandParams;
        var params = data.params;
        var specs = data.specVos;
        var expandParamHtml = "";

        /*    <tr>
         <td>图案文化</td>
         <td>
         <select class="form-control">
         <option>电影</option>
         <option>创意</option>
         <option>青春</option>
         </select>
         </td>
         </tr>*/
        if( expandParam.length>0){
            for (var i = 0; i < expandParam.length; i++)
            {
                if (expandParam[i].valueList.length > 0)
                {
                    if(i%2==0) {
                        expandParamHtml = expandParamHtml + "<tr>";
                    }
                    expandParamHtml = expandParamHtml + "<td><span class='text-danger'>*</span>" + expandParam[i].expandparamName + "</td><td>" + "<input type='hidden' class='type_expand_param' name='expandParamId' value='" + expandParam[i].expandparamId + "'>" + "<select class='form-control type_expand_sel' name='expandparamValue' id='expandparamValue"+expandParam[i].expandparamId+"'>";
                    for (var k = 0; k < expandParam[i].valueList.length; k++)
                    {
                        expandParamHtml = expandParamHtml + "<option value='" + expandParam[i].valueList[k].expandparamValueId + "'>" + expandParam[i].valueList[k].expandparamValueName + "</option>";
                    };
                    expandParamHtml = expandParamHtml + "</select></td>";
                    if((i+1)%2==0 || i==expandParam.length-1) {
                        expandParamHtml = expandParamHtml + "</tr>";
                    }
                }else{
                    expandParamHtml = expandParamHtml + "<tr><td>您选择的商品分类下没有扩展参数!</td></tr>";
                }
            }
        }
        $("#attribute tbody").html(expandParamHtml);
        //商品扩展属性初始值
        $(".expand_param_values").each(function() {
            $("#expandparamValue"+$(this).attr("expand_param_id")).find("option[value="+$(this).val()+"]").attr("selected",true);
        });
        /*扩展参数  END*/
        var paramHtml = "";
        if(params.length>0){
            for (var i = 0; i < params.length; i++)
            {
                if(i%2==0) {
                    paramHtml = paramHtml + "<tr>";
                }
                paramHtml = paramHtml + "<td>" + params[i].paramName + "</td><td><input type='hidden' class='type_param' name='paramId' value='" + params[i].paramId + "'/><input type='text' value='' class='form-control type_param_val' name='paramValue' id='paramValue"+params[i].paramId+"'/></td>";
                if((i+1)%2==0 || i==params.length-1) {
                    paramHtml = paramHtml + "</tr>";
                }
            }
        }else{
            paramHtml = paramHtml + "您选择的商品分类下没有详细参数!";
        }
        $("#attribute tbody").append(paramHtml);
        $(".param_values").each(function () {
            $("#paramValue"+$(this).attr("param_id")).val($(this).val());
        });
        /*详细参数END*/
        //$(".type_attribute").html(expandParamHtml+paramHtml);

        /*加载规格信息*/
        if(specs.length>0){
            var specHtml = '';

            for(var i = 0;i<specs.length;i++){
                specHtml+='<div class="checkbox spec_set">';
                specHtml+=' <label> <input type="checkbox" class="type_spec"  value="'+specs[i].goodsSpec.specId+'">'+specs[i].goodsSpec.specName+'</label></div>';
                specHtml+='<div class="spec_set_details" style="display:none"  id="emp'+(i+1)+'" name="no">';
                specHtml+='<table class="table  table-striped table-hover">';
                specHtml+=' <thead>';
                specHtml+=' <tr>';
                var spleng = specs[i].goodsSpec.specDetails.length;
                /*   if(spleng!=0){
                 for(var s=0;s<spleng;s++){
                 specHtml+='  <th></th>';
                 specHtml+='  <th width="100">规格值</th>';
                 specHtml+='   <th width="110">规格图片</th>';
                 if(s==2){
                 break;
                 }
                 }
                 }else{
                 specHtml+='  <th></th>';
                 specHtml+='  <th width="100">规格值</th>';
                 specHtml+='   <th  width="110">规格图片</th>';
                 }*/
                specHtml+='  </tr>';
                specHtml+='  </thead>';
                specHtml+='  <tbody>';





                for(var j = 0;j<specs[i].goodsSpec.specDetails.length;j++){
                    // specHtml+="<li><label class='mr10'><input class='vm mr5 check_spec openSpecValue_"+specs[i].goodsSpec.specId+"' type='checkbox' onclick='checkSpecValue(this);' spec_id="+specs[i].goodsSpec.specId+" spec_value_id="+specs[i].goodsSpec.specDetails[j].specDetailId+" value="+specs[i].goodsSpec.specDetails[j].specDetailName+" /><span>"+specs[i].goodsSpec.specDetails[j].specDetailName+"</span></label></li>";
                    //specHtml+="<p><label class='mr10 check_spec_val_tit_"+specs[i].goodsSpec.specDetails[j].specDetailId+"-"+specs[i].goodsSpec.specId+"'><input type='checkbox' onclick='checkSpecValue(this)'  class='vm mr5 openSpecValue_"+specs[i].goodsSpec.specId+"' name='openSpecValue' value="+specs[i].goodsSpec.specDetails[j].specDetailId+"-"+specs[i].goodsSpec.specId+" />"+specs[i].goodsSpec.specDetails[j].specDetailName+"</label></p>";
                    if(spleng==1){
                        specHtml+='<tr>';
                        specHtml+='<td><input type="checkbox" class="check_spec openSpecValue_'+specs[i].goodsSpec.specId+'" spec_name="'+specs[i].goodsSpec.specName+'" spec_id="'+specs[i].goodsSpec.specId+'" spec_value_id="'+specs[i].goodsSpec.specDetails[j].specDetailId+'" value="'+specs[i].goodsSpec.specDetails[j].specDetailName+'" onchange="chImg(this);"></td>';
                        specHtml+='<td>'+specs[i].goodsSpec.specDetails[j].specDetailName+'</td>';
                        specHtml+='<td>';
                        specHtml+=' <div class="spec_value_img" style="display:none;">';
                        specHtml+='<a href="javascript:;" class="add_img" onclick="">上传图片</a>';
                        specHtml+='<form action="uploadImgSingle.htm" method="post" target="hidden_frame" enctype="multipart/form-data" class="specValue_'+specs[i].goodsSpec.specDetails[j].specDetailId+' spec_img_form_'+specs[i].goodsSpec.specId+'">';
                        specHtml+='<input type="hidden" name="specValId" value="'+specs[i].goodsSpec.specDetails[j].specDetailId+'">';
                        specHtml+='<input type="hidden" class="up_spec_img_src up_spec_img_src_'+specs[i].goodsSpec.specDetails[j].specDetailId+'" value="">';
                        specHtml+='<input style="position:relative;z-index:99;display:block;width:36px;height:36px;opacity:0;filter:Alpha(opacity=0);overflow:hidden;" type="file" name="specImg" spec_val_id="'+specs[i].goodsSpec.specDetails[j].specDetailId+'" class="spec_img spec_img_'+specs[i].goodsSpec.specDetails[j].specDetailId+'">';
                        specHtml+='</form>';
                        specHtml+='</div>';
                        specHtml+=' </td>';
                        specHtml+=' <td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>';
                        specHtml+=' </tr>';
                    }else if(spleng==2){
                        if(j==0){
                            specHtml+='<tr>';
                        }
                        specHtml+='<td><input type="checkbox" class="check_spec openSpecValue_'+specs[i].goodsSpec.specId+'" spec_name="'+specs[i].goodsSpec.specName+'" spec_id="'+specs[i].goodsSpec.specId+'" spec_value_id="'+specs[i].goodsSpec.specDetails[j].specDetailId+'" value="'+specs[i].goodsSpec.specDetails[j].specDetailName+'"  onchange="chImg(this);"></td>';
                        specHtml+='<td>'+specs[i].goodsSpec.specDetails[j].specDetailName+'</td>';
                        specHtml+='<td>';
                        specHtml+=' <div class="spec_value_img" style="display:none;">';
                        specHtml+='<a href="javascript:;" class="add_img" onclick="">上传图片</a>';
                        specHtml+='<form action="uploadImgSingle.htm" method="post" target="hidden_frame" enctype="multipart/form-data" class="specValue_'+specs[i].goodsSpec.specDetails[j].specDetailId+' spec_img_form_'+specs[i].goodsSpec.specId+'">';
                        specHtml+='<input type="hidden" name="specValId" value="'+specs[i].goodsSpec.specDetails[j].specDetailId+'">';
                        specHtml+='<input type="hidden" class="up_spec_img_src up_spec_img_src_'+specs[i].goodsSpec.specDetails[j].specDetailId+'" value="">';
                        specHtml+='<input style="position:relative;z-index:99;display:block;width:36px;height:36px;opacity:0;filter:Alpha(opacity=0);overflow:hidden;" type="file" name="specImg" spec_val_id="'+specs[i].goodsSpec.specDetails[j].specDetailId+'" class="spec_img spec_img_'+specs[i].goodsSpec.specDetails[j].specDetailId+'" >';
                        specHtml+='</form>';
                        specHtml+='</div>';
                        specHtml+=' </td>';
                        specHtml+=' <td>&nbsp;</td><td>&nbsp;</td>';
                        if(j==1){
                            specHtml+=' </tr>';
                        }
                    }else if(spleng>=3){
                        if(j%5==0){
                            specHtml+='<tr>';
                        }

                        specHtml+='<td><input type="checkbox" class="check_spec openSpecValue_'+specs[i].goodsSpec.specId+'" spec_name="'+specs[i].goodsSpec.specName+'" spec_id="'+specs[i].goodsSpec.specId+'" spec_value_id="'+specs[i].goodsSpec.specDetails[j].specDetailId+'" value="'+specs[i].goodsSpec.specDetails[j].specDetailName+'"  onchange="chImg(this);"></td>';
                        specHtml+='<td>'+specs[i].goodsSpec.specDetails[j].specDetailName+'</td>';
                        specHtml+='<td>';
                        specHtml+=' <div class="spec_value_img" style="display:none;">';
                        specHtml+='<a href="javascript:;" class="add_img" onclick="">上传图片</a>';
                        specHtml+='<form action="uploadImgSingle.htm" method="post" target="hidden_frame" enctype="multipart/form-data" class="specValue_'+specs[i].goodsSpec.specDetails[j].specDetailId+' spec_img_form_'+specs[i].goodsSpec.specId+'">';
                        specHtml+='<input type="hidden" name="specValId" value="'+specs[i].goodsSpec.specDetails[j].specDetailId+'">';
                        specHtml+='<input type="hidden" class="up_spec_img_src up_spec_img_src_'+specs[i].goodsSpec.specDetails[j].specDetailId+'" value="">';
                        specHtml+='<input style="position:relative;z-index:99;display:block;width:36px;height:36px;opacity:0;filter:Alpha(opacity=0);overflow:hidden;" type="file" name="specImg" spec_val_id="'+specs[i].goodsSpec.specDetails[j].specDetailId+'" class="spec_img spec_img_'+specs[i].goodsSpec.specDetails[j].specDetailId+'">';
                        specHtml+='</form>';
                        specHtml+='</div>';
                        specHtml+=' </td>';
                        if(j==(parseInt(spleng/5)*5)+1) {
                            for(n=0;n<5-spleng%5;n++) {
                                specHtml +=' <td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>';
                            }
                        }

                        if((j+1)%5==0){
                            specHtml+=' </tr>';
                        }
                    }


                }
                specHtml+=' </tbody></table></div>';
            }
            $(".type_spec").html(specHtml);
            $(".spec_img").change(function(){
                $(".specValue_"+$(this).attr("spec_val_id")).submit();
            });
        }else{
            $(".type_spec").html("<p class='no_spec'>没有规格</p>");
        }
        /*绑定事件*/
        //  bindEvent();
        //tx();
        $('.spec_set input').change(function(){
            if($(this).is(':checked')){
                $(this).parent().parent().next().attr('name','ok');
                $(this).parent().parent().next().slideDown('fast');
            }
            else {
                $(this).parent().parent().next().attr('name','no');
                $(this).parent().parent().next().slideUp('fast');
            }
        });
    });
}


/**
 * 计算所选规格组合
 */
function loadSelectSpecs(){
    $(".selectSpecs").html("");
    var selectSpecLength = 0;
    /*如果只选择开启了一个规格*/
    var ch_spec = $(".type_spec:checked");
    if(ch_spec.length==1){
        $(".dinfo_tabs").html("");
        $(".dinfo_wp").html("");
        var spec_id = $(ch_spec[0]).val();
        var spec_vals = $(".openSpecValue_"+spec_id);
        spectr = '';
        if(spec_vals.length>0){
            //表头——规格名称
            spectr += '<tr><th>选择</th>';
            spectr += '<th>'+$(spec_vals[0]).attr("spec_name")+'</th>';
            spectr += '</tr>';
            $("#select_spec_talbe thead").html(spectr);
            spectr = '';

            //表体——规格值循环
            for(var i=0;i<spec_vals.length;i++) {
                if($(spec_vals[i]).prop("checked")){
                    selectSpecLength ++;
                    var specdetailname = $(spec_vals[i]).val();
                    var specid = $(spec_vals[i]).attr("spec_id");
                    var specdetailid = $(spec_vals[i]).attr("spec_value_id");
                    var specname = $(spec_vals[i]).attr("spec_name");
                    spectr += '<tr id="tr_'+specid+'_'+specdetailid+'-">';
                    spectr += '<td><input type="radio" name="specgroup"></td>';
                    spectr += '<td>' +
                    specdetailname+'<img src="'+$('.up_spec_img_src_'+specdetailid).val()+'"/>'+
                    '<input type="hidden" class="selectSpecId" value="'+specid+'"/>' +
                    '<input type="hidden" class="selectSpecdetailid" value="'+specdetailid+'"/>' +
                    '<input type="hidden" class="selectSpecname" value="'+specname+'"/>' +
                    '<input type="hidden" class="selectSpecdetailname" value="'+specdetailname+'"/>' +
                    '</td>';
                    spectr += '</tr>';
                }
            }
            $("#select_spec_talbe tbody").html(spectr);
        }
    }else{
        var empArray2 = new Array();
        var empArray3 = new Array();
        var emp = 0;
        var emp1 = $(".type_spec").length;
        for (var i = 0; i < emp1; i++) {
            if($("#emp" + i).attr("name") != "ok"){
                continue;
            }
            var arrayA = new Array();

            $("#emp" + i + "[name ='ok'] input:checked").each(function() {
                arrayA.push($(this).val()+"-"+$(this).attr("spec_id")+"-"+$(this).attr("spec_value_id")+"-"+$(this).attr("spec_name")+"_");
            });

            while($("#emp" + (i+1) ).attr("name") == "no"){
                i++;
            }

            if (i < $(".type_spec").length - 1 && emp == 0) {
                emp = 1;
                i++;
                var arrayB = new Array();
                $("#emp" + i + "[name = 'ok'] input:checked").each(function() {

                    arrayB.push($(this).val()+"-"+$(this).attr("spec_id")+"-"+$(this).attr("spec_value_id")+"-"+$(this).attr("spec_name")+"_");

                });

                //$("#emp" + i + "[name ='ok']").attr("name", "no");

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
            selectSpecLength = empArray3.length;
            for (var x = 0; x < empArray3.length; x++) {
                var specs = empArray3[x].split("_");
                if(x==0) {
                    spectr += '<tr><th>选择</th>';
                    for(var n=0;n<specs.length-1;n++) {
                        var specinfo = specs[n].split("-");
                        //选中的规格值名称
                        var specdetailname = specinfo[0];
                        //选中的规格id
                        var specid = specinfo[1];
                        //选中的规格值id
                        var specdetailid = specinfo[2];
                        //选中的规格名称
                        var specname = specinfo[3];
                        spectr += '<th>'+specname+'</th>';
                    }
                    spectr += '</tr>';
                    $("#select_spec_talbe thead").html(spectr);
                    spectr = '';
                }

                var speciddetailids = '';
                for(var n=0;n<specs.length-1;n++) {
                    var specinfo = specs[n].split("-");
                    var specdetailname = specinfo[0];
                    var specid = specinfo[1];
                    var specdetailid = specinfo[2];
                    var specname = specinfo[3];
                    speciddetailids +=specid+'_' +specdetailid+'-';
                }
                spectr += '<tr id="tr_'+speciddetailids+'">';
                for(var n=0;n<specs.length-1;n++) {
                    var specinfo = specs[n].split("-");
                    var specdetailname = specinfo[0];
                    var specid = specinfo[1];
                    var specdetailid = specinfo[2];
                    var specname = specinfo[3];
                    if(n==0) {
                        spectr += '<td><input type="radio" name="specgroup"></td>';
                    }
                    spectr += '<td>' +
                    specdetailname+'<img src="'+$('.up_spec_img_src_'+specdetailid).val()+'"/>'+
                    '<input type="hidden" class="selectSpecId" value="'+specid+'"/>' +
                    '<input type="hidden" class="selectSpecdetailid" value="'+specdetailid+'"/>' +
                    '<input type="hidden" class="selectSpecname" value="'+specname+'"/>' +
                    '<input type="hidden" class="selectSpecdetailname" value="'+specdetailname+'"/>' +
                    '</td>';
                }
            }
            $("#select_spec_talbe tbody").html(spectr);

        }else{
            selectSpecLength = empArray2.length;
            var spectr = '';
            for (var x = 0; x < empArray2.length; x++) {
                var specs = empArray2[x].split("_");
                if(x==0) {
                    spectr += '<tr><th>选择</th>';
                    for(var n=0;n<specs.length-1;n++) {
                        var specinfo = specs[n].split("-");
                        var specdetailname = specinfo[0];
                        var specid = specinfo[1];
                        var specdetailid = specinfo[2];
                        var specname = specinfo[3];
                        spectr += '<th>'+specname+'</th>'
                    }
                    spectr += '</tr>'
                    $("#select_spec_talbe thead").html(spectr);
                    spectr = '';
                }


                var speciddetailids = '';
                for(var n=0;n<specs.length-1;n++) {
                    var specinfo = specs[n].split("-");
                    var specdetailname = specinfo[0];
                    var specid = specinfo[1];
                    var specdetailid = specinfo[2];
                    var specname = specinfo[3];
                    speciddetailids +=specid+'_' +specdetailid+'-';
                }
                spectr += '<tr id="tr_'+speciddetailids+'">';
                for(var n=0;n<specs.length-1;n++) {
                    var specinfo = specs[n].split("-");
                    var specdetailname = specinfo[0];
                    var specid = specinfo[1];
                    var specdetailid = specinfo[2];
                    var specname = specinfo[3];
                    if(n==0) {
                        spectr += '<td><input type="radio" name="specgroup"></td>';
                    }
                    spectr += '<td>' +
                    specdetailname+'<img src="'+$('.up_spec_img_src_'+specdetailid).val()+'"/>'+
                    '<input type="hidden" class="selectSpecId" value="'+specid+'"/>' +
                    '<input type="hidden" class="selectSpecdetailid" value="'+specdetailid+'"/>' +
                    '<input type="hidden" class="selectSpecname" value="'+specname+'"/>' +
                    '<input type="hidden" class="selectSpecdetailname" value="'+specdetailname+'"/>' +
                    '</td>';
                }
                spectr += '</tr>';
            }
            $("#select_spec_talbe tbody").html(spectr);
        }
    }
    return selectSpecLength;
}

function chImg(obj){
    if(obj.checked==true){
        $(obj).parents("td").next().next().find(".spec_value_img").show();
    }else{
        $(obj).parents("td").next().next().find(".spec_value_img").hide();
    }
}

/*上传规格图片回调*/
function specImgSucc(msg){
    if(msg=="111"){
        showNoDeleteConfirmAlert('图片格式不正确');
    }else{
        var id = msg.substring(msg.lastIndexOf("-")+1,msg.length);
        var url = msg.substring(0,msg.lastIndexOf("-"));
        //如果该规格已经上传过图片就替换已经上传的图片
        if($(".up_spec_img_"+id)[0]){
            $(".up_spec_img_"+id).attr("style","background:#e2e2e2 url("+url+") no-repeat center center");
        }else{
            $(".specValue_"+id).parent().before("<div class='spec_value_img up_spec_img_"+id+"' style='background:#e2e2e2 url("+url+") no-repeat center center'></div> ");
        }
        $(".up_spec_img_src_"+id).val(url);
    }
}



function showSelectSpec(obj) {
    $("#select_spec_talbe tbody tr").removeAttr("style");
    $("#goods_info_table tbody tr").each(function () {
        var selectSpecs = $(this).find(".select_specs");
        if(selectSpecs!=undefined) {
            $("#tr_"+$(selectSpecs).val()).css("color","#C9C9C9");
        }
    });
    $("#goodsInfoId").val($(obj).attr("goods_info_id"));
    $("#selectSpecModal").modal("show");
}

/**
 * 在生成的规格组合弹框中，选中一条组合后，的事件
 */
function selectSpecGroup() {
    var goodsInfoId = $("#goodsInfoId").val();
    var selectSpecGroup = $('input[name="specgroup"]:checked');

    //从选中的规格组合中，获取所有规格id，放到数组
    var selectSpecIdArray = new Array();
    $(selectSpecGroup.parent()).parent().find(".selectSpecId").each(function() {
        selectSpecIdArray.push($(this).val());
    });

    //从选中的规格组合中，获取所有规格名称，放到数组
    var selectSpecnameArray = new Array();
    $(selectSpecGroup.parent()).parent().find(".selectSpecname").each(function() {
        selectSpecnameArray.push($(this).val());
    });
    //从选中的规格组合中，获取所有规格值id，放到数组
    var selectSpecdetailidArray = new Array();
    $(selectSpecGroup.parent()).parent().find(".selectSpecdetailid").each(function() {
        selectSpecdetailidArray.push($(this).val());
    });

    //从选中的规格组合中，获取所有的规格值名称，放到数组
    var selectSpecdetailnameArray = new Array();
    $(selectSpecGroup.parent()).parent().find(".selectSpecdetailname").each(function() {
        selectSpecdetailnameArray.push($(this).val());
    });
    //新选择的规格名称和规格值名称
    var selectHtml = '';

    var selectSpecs ='';
    var selectSpecIdSpecdetailIdSpecremark = '';
    for(var i=0;i<selectSpecdetailidArray.length;i++) {
        //新选择的规格名称和规格值名称
        selectHtml += selectSpecnameArray[i]+":"+selectSpecdetailnameArray[i]+"<br/>";
        //新选择的规格Id和规格值Id
        selectSpecs += selectSpecIdArray[i]+'_'+selectSpecdetailidArray[i]+'-';
        selectSpecIdSpecdetailIdSpecremark += selectSpecIdArray[i]+'_'+selectSpecdetailidArray[i]+'_'+selectSpecdetailnameArray[i]+'_'+$(".up_spec_img_src_"+selectSpecdetailidArray[i]).val()+'-';
    }
    if($('.select_specs'+selectSpecs).length>0) {
        showTipAlert("该规格已经被分配，请重新选择");
        return;
    }
    var selectSpecsHtml =
        //这个隐藏域用来判断生成的规格中，有哪些被分配过了
        '<input type="hidden" class="select_specs select_specs'+selectSpecs+'" value="'+selectSpecs+'"/>' +
            //这个隐藏域用来标记这个货品已经被重置过规格了
        '<input type="hidden" class="updateFlag" value="1"/>' +
            //这个要传到后台用的，货品id-规格id_规格值id_规格值名称-规格id_规格值id_规格值名称。。。。
        '<input type="hidden" name="productIdSpecIdSpecdetailId" value="'+goodsInfoId+'-'+selectSpecIdSpecdetailIdSpecremark+'"/>';
    //将新选中的规格组合（包括规格名称和规格值名称）更新到页面货品记录汇总
    $("#spec_info"+goodsInfoId).html(selectHtml+selectSpecsHtml);
    $("#spec_info"+goodsInfoId).removeClass("text-danger");

    $("#selectSpecModal").modal("hide");

}

function updateGoods() {
    $("#goodsParamForm").submit();
}

function call_update_goods() {
    $(".goods_desc").val($(".summernotedesc").code());
    $(".goods_mobile_desc").val($(".summernotemobile").code());
    $("#goodsBaseForm").submit();
}



function showAddGoodsRelModal() {
    queryGoodsByParam(1);
    $("#addGoodsRelModal").modal("show");
}

function queryGoodsByParam(pageNo) {
    var goodsName = $("#goods_name").val();
    var goodsNo = $("#goods_no").val();
    var goodsBrandId = $("#goodsBrandId").val();
    /*根据选中分类加载相关商品*/
    $.get("queryByParamAjax.htm?goodsCateId=" +$('#parentId3').val()+"&pageNo="+pageNo+"&goodsNo="+goodsNo+"&goodsName="+goodsName+"&goodsBrandId="+goodsBrandId+"&showFlag=1&CSRFToken="+$("#CSRFToken").val(), function (data)
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
