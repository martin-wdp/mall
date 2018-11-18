$(function () {
    //加载一级分类
    loadFirstCate(0);
    $("#goods_info_form").validate();
    $('.pro_weight_tip').popover({
        content : '单位：克',
        trigger : 'hover'
    });
    $('.pro_weight_tip_jd').popover({
        content : '京东商品详情页的链接',
        trigger : 'hover'
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
                //自动点击第一个分类
               
                $("#cate_list1").find("div.cate_item")[0].click(); 
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
   if(chooseName!=null){ $("#productName1").text(chooseName);}
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

            //自动点击第一个分类，
            if($("#cate_list2").find("div.cate_item").length>0) {
               $("#cate_list2").find("div.cate_item")[0].click();
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
               $("#cate_list3").find("div.cate_item")[0].click();
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
                    if(i%3==0) {
                        expandParamHtml = expandParamHtml + "<tr>";
                    }
                    expandParamHtml = expandParamHtml + "<td><span class='text-danger'>*</span>" + expandParam[i].expandparamName + "</td><td>" + "<input type='hidden' class='type_expand_param' name='expandParamId' value='" + expandParam[i].expandparamId + "'>" + "<select class='form-control type_expand_sel' name='expandparamValue'>";
                    for (var k = 0; k < expandParam[i].valueList.length; k++)
                    {
                        expandParamHtml = expandParamHtml + "<option value='" + expandParam[i].valueList[k].expandparamValueId + "'>" + expandParam[i].valueList[k].expandparamValueName + "</option>";
                    };
                    expandParamHtml = expandParamHtml + "</select></td>";
                    if((i+1)%3==0 || i==expandParam.length-1) {
                        expandParamHtml = expandParamHtml + "</tr>";
                    }
                }else{
                     expandParamHtml = expandParamHtml + "<tr><td>您选择的商品分类下没有扩展参数!</td></tr>";
                }
            }
        }
        $("#attribute tbody").html(expandParamHtml);
        /*扩展参数  END*/
        var paramHtml = "";
        if(params.length>0){
            for (var i = 0; i < params.length; i++)
            {
                if(i%3==0) {
                    paramHtml = paramHtml + "<tr>";
                }
                paramHtml = paramHtml + "<td>" + params[i].paramName + "</td><td class='"+params[i].paramName+"'><input type='hidden' class='type_param' name='paramId' value='" + params[i].paramId + "'/><input type='text' value='' class='form-control type_param_val' name='paramValue'/></td>";
                if((i+1)%3==0 || i==params.length-1) {
                    paramHtml = paramHtml + "</tr>";
                }
            }
        }else{
             paramHtml = paramHtml + "您选择的商品分类下没有详细参数!";
        }
        $("#attribute tbody").append(paramHtml);
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
           
                specHtml+='  </tr>';
                specHtml+='  </thead>';
                specHtml+='  <tbody>';
                
                
                for(var j = 0;j<specs[i].goodsSpec.specDetails.length;j++){
                   
                        if(j%5==0){
                            specHtml+='<tr>';
                        }
                           
                            specHtml+='<td><input type="checkbox" class="check_spec openSpecValue_'+specs[i].goodsSpec.specId+'" spec_id="'+specs[i].goodsSpec.specId+'" spec_value_id="'+specs[i].goodsSpec.specDetails[j].specDetailId+'" value="'+specs[i].goodsSpec.specDetails[j].specDetailName+'"  onchange="chImg(this);"></td>';
                            specHtml+='<td> <input class="form-control w100" type="text" value="'+specs[i].goodsSpec.specDetails[j].specDetailName+'" onchange="changeValueId(this)"></td>';
                           
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
                        
                            if(spleng==j){
	                                 specHtml+=' </tr>';
	                        }else if(spleng>=5){
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

function loadProduct(){
    /*如果只选择开启了一个规格*/
    var ch_spec = $(".type_spec:checked");
    if(ch_spec.length==1){
        $(".dinfo_tabs").html("");
        $(".dinfo_wp").html("");
        var spec_id = $(ch_spec[0]).val();
        //$(".openSpecValue_"+spec_id).val($(".openSpecValue_"+spec_id).next("input").val());
        var spec_vals = $(".openSpecValue_"+spec_id);
        if(spec_vals.length>0){
            for(var i = 0;i<spec_vals.length;i++){
                if($(spec_vals[i]).prop("checked")){
                    var _tabs = '<li role="presentation"';
                    _tabs+='><a href="#tab'+(i+1)+'" aria-controls="tab'+(i+1)+'" role="tab" data-toggle="tab">'+ $(spec_vals[i]).val() +'</a></li> ';
                    var hidearea ="<input type='hidden' class='product_spec' value="+$(spec_vals[i]).attr("spec_id")+"-"+$(spec_vals[i]).attr("spec_value_id")+"-"+$(spec_vals[i]).val()+">";
                    var ct = $(".demo_box .tab-pane").clone();
                    ct.removeClass("demo_box");
                    $(".dinfo_tabs").append(_tabs);
                    
                    ct.attr("id","tab"+(i+1));
                    ct.append(hidearea);
                    $(".dinfo_wp").append(ct);

                    $("#tab"+(i+1)).find(".name_input").each(function(){
                        $(this).val($(this).val()+'('+$(spec_vals[i]).val()+')');
                    });

                    $("#tab"+(i+1)).find(".batch_set_stock_ctrl").attr("spe_id",i+1);
                    $("#tab"+(i+1)).find(".do_batch_set_stock_ctrl").attr("spe_id",i+1);
                    $("#tab"+(i+1)).find(".cancel_batch_set_stock_ctrl").attr("spe_id",i+1);
                    $("#tab"+(i+1)).find(".copy_ware_to_other_spec_ctrl").attr("spe_id",i+1);

                    //销售价格
                    $("#tab"+(i+1)).find(".batch_set_price_ctrl").attr("spe_id",i+1);
                    $("#tab"+(i+1)).find(".do_batch_set_price_ctrl").attr("spe_id",i+1);
                    $("#tab"+(i+1)).find(".cancel_batch_set_price_ctrl").attr("spe_id",i+1);
                    //会员价格
                    $("#tab"+(i+1)).find(".batch_set_vip_price_ctrl").attr("spe_id",i+1);
                    $("#tab"+(i+1)).find(".do_batch_set_vip_price_ctrl").attr("spe_id",i+1);
                    $("#tab"+(i+1)).find(".cancel_batch_set_vip_price_ctrl").attr("spe_id",i+1);

                    $("#tab"+(i+1)).find(".batc_set_stock").attr("id","batc_set_stock"+(i+1));
                    $("#tab"+(i+1)).find(".batch_set_price").attr("id","batc_set_price"+(i+1));
                    $("#tab"+(i+1)).find(".batch_set_vip_price").attr("id","batc_set_vip_price"+(i+1));

                    $("#tab"+(i+1)).find(".batch_set_stock_ctrl").attr("id","batch_set_stock_ctrl"+(i+1));
                    $("#tab"+(i+1)).find(".do_batch_set_stock_ctrl").attr("id","do_batch_set_stock_ctrl"+(i+1));
                    $("#tab"+(i+1)).find(".cancel_batch_set_stock_ctrl").attr("id","cancel_batch_set_stock_ctrl"+(i+1));
                    $("#tab"+(i+1)).find(".batch_set_price_ctrl").attr("id","batch_set_price_ctrl"+(i+1));
                    $("#tab"+(i+1)).find(".do_batch_set_price_ctrl").attr("id","do_batch_set_price_ctrl"+(i+1));
                    $("#tab"+(i+1)).find(".cancel_batch_set_price_ctrl").attr("id","cancel_batch_set_price_ctrl"+(i+1));
                    $("#tab"+(i+1)).find(".batch_set_vip_price_ctrl").attr("id","batch_set_vip_price_ctrl"+(i+1));
                    $("#tab"+(i+1)).find(".do_batch_set_vip_price_ctrl").attr("id","do_batch_set_vip_price_ctrl"+(i+1));
                    $("#tab"+(i+1)).find(".cancel_batch_set_vip_price_ctrl").attr("id","cancel_batch_set_vip_price_ctrl"+(i+1));

                    $('.productsImg').popover({
                        content : '建议800*800px',
                        trigger : 'hover'
                    });


                  /*  ct.find(".nameWp input").attr("name","_rad"+ct.index());
                    ct.find(".customerDiscount input").attr("name","_discount"+ct.index());*/
                }
            }
        }
    }else{
        var empArray2 = new Array();
        var empArray3 = new Array();
        var emp = 0;
        var emp1 = $(".type_spec").length;
        $(".dinfo_tabs").html("");
        $(".dinfo_wp").html("");
        for (var i = 0; i < emp1; i++) {
            if($("#emp" + i).attr("name") != "ok"){
            
                continue;
            }
            var arrayA = new Array();

            $("#emp" + i + "[name ='ok'] input:checked").each(function() {
                arrayA.push($(this).val()+"-"+$(this).attr("spec_id")+"-"+$(this).attr("spec_value_id")+"_"+$(this).val()+"-");
            });

            //$("#emp" + i + "[name ='ok']").attr("name", "no");
            
            while($("#emp" + (i+1) ).attr("name") == "no"){
                i++;
            }
            
            if (i < $(".type_spec").length - 1 && emp == 0) {
                emp = 1;
                i++;
                var arrayB = new Array();
                $("#emp" + i + "[name = 'ok'] input:checked").each(function() {

                    arrayB.push($(this).val()+"-"+$(this).attr("spec_id")+"-"+$(this).attr("spec_value_id")+"_"+$(this).val()+"-");

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
                var _tabs = '<li role="presentation"';
                _tabs+='><a href="#tab'+(x+1)+'" aria-controls="tab'+(x+1)+'" role="tab" data-toggle="tab">'+title +'</a></li> ';

                var ct = $(".demo_box .tab-pane").clone();
                ct.removeClass("demo_box");
                $(".dinfo_tabs").append(_tabs);
                
                ct.attr("id","tab"+(x+1));
                ct.append(hidearea);
                $(".dinfo_wp").append(ct);

                $("#tab"+(i+1)).find(".name_input").each(function(){
                    $(this).val($(this).val()+'('+title+')');
                });

                $("#tab"+(i+1)).find(".copy_ware_to_other_spec_ctrl").attr("spe_id",i+1);
                $("#tab"+(i+1)).find(".batch_set_stock_ctrl").attr("spe_id",i+1);
                $("#tab"+(i+1)).find(".do_batch_set_stock_ctrl").attr("spe_id",i+1);
                $("#tab"+(i+1)).find(".cancel_batch_set_stock_ctrl").attr("spe_id",i+1);

                $("#tab"+(i+1)).find(".batch_set_price_ctrl").attr("spe_id",i+1);
                $("#tab"+(i+1)).find(".do_batch_set_price_ctrl").attr("spe_id",i+1);
                $("#tab"+(i+1)).find(".cancel_batch_set_price_ctrl").attr("spe_id",i+1);
                //会员价格
                $("#tab"+(i+1)).find(".batch_set_vip_price_ctrl").attr("spe_id",i+1);
                $("#tab"+(i+1)).find(".do_batch_set_vip_price_ctrl").attr("spe_id",i+1);
                $("#tab"+(i+1)).find(".cancel_batch_set_vip_price_ctrl").attr("spe_id",i+1);

                $("#tab"+(i+1)).find(".batc_set_stock").attr("id","batc_set_stock"+(i+1));
                $("#tab"+(i+1)).find(".batch_set_price").attr("id","batc_set_price"+(i+1));
                $("#tab"+(i+1)).find(".batch_set_vip_price").attr("id","batc_set_vip_price"+(i+1));

                $("#tab"+(i+1)).find(".batch_set_stock_ctrl").attr("id","batch_set_stock_ctrl"+(i+1));
                $("#tab"+(i+1)).find(".do_batch_set_stock_ctrl").attr("id","do_batch_set_stock_ctrl"+(i+1));
                $("#tab"+(i+1)).find(".cancel_batch_set_stock_ctrl").attr("id","cancel_batch_set_stock_ctrl"+(i+1));
                $("#tab"+(i+1)).find(".batch_set_price_ctrl").attr("id","batch_set_price_ctrl"+(i+1));
                $("#tab"+(i+1)).find(".do_batch_set_price_ctrl").attr("id","do_batch_set_price_ctrl"+(i+1));
                $("#tab"+(i+1)).find(".cancel_batch_set_price_ctrl").attr("id","cancel_batch_set_price_ctrl"+(i+1));
                $("#tab"+(i+1)).find(".batch_set_vip_price_ctrl").attr("id","batch_set_vip_price_ctrl"+(i+1));
                $("#tab"+(i+1)).find(".do_batch_set_vip_price_ctrl").attr("id","do_batch_set_vip_price_ctrl"+(i+1));
                $("#tab"+(i+1)).find(".cancel_batch_set_vip_price_ctrl").attr("id","cancel_batch_set_vip_price_ctrl"+(i+1));

                $('.productsImg').popover({
                    content : '建议800*800px',
                    trigger : 'hover'
                });

            
                /*
                
                var _tabs = '<li><a href="javascript:;">'+ title +'</a></li>';
                var ct = $(".demo_box").clone();
                ct.removeClass("demo_box");
                $(".dinfo_tabs").append(_tabs);
                ct.append(hidearea);
                $(".dinfo_wp").append(ct);
                ct.find(".nameWp input").attr("name","_rad"+ct.index());
                ct.find(".customerDiscount input").attr("name","_discount"+ct.index());*/
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
                var _tabs = '<li role="presentation"';
                _tabs+='><a href="#tab'+(x+1)+'" aria-controls="tab'+(x+1)+'" role="tab" data-toggle="tab">'+title +'</a></li> ';
                var ct = $(".demo_box .tab-pane").clone();
                ct.removeClass("demo_box");
                $(".dinfo_tabs").append(_tabs);
                ct.attr("id","tab"+(x+1));
                ct.append(hidearea);
                $(".dinfo_wp").append(ct);

                $("#tab"+(x+1)).find(".name_input").each(function(){
                    $(this).val($(this).val()+'('+title+')');
                });

                $("#tab"+(x+1)).find(".copy_ware_to_other_spec_ctrl").attr("spe_id",x+1);
                $("#tab"+(x+1)).find(".batch_set_stock_ctrl").attr("spe_id",x+1);
                $("#tab"+(x+1)).find(".do_batch_set_stock_ctrl").attr("spe_id",x+1);
                $("#tab"+(x+1)).find(".cancel_batch_set_stock_ctrl").attr("spe_id",x+1);

                $("#tab"+(x+1)).find(".batch_set_price_ctrl").attr("spe_id",x+1);
                $("#tab"+(x+1)).find(".do_batch_set_price_ctrl").attr("spe_id",x+1);
                $("#tab"+(x+1)).find(".cancel_batch_set_price_ctrl").attr("spe_id",x+1);
                //会员价格
                $("#tab"+(x+1)).find(".batch_set_vip_price_ctrl").attr("spe_id",x+1);
                $("#tab"+(x+1)).find(".do_batch_set_vip_price_ctrl").attr("spe_id",x+1);
                $("#tab"+(x+1)).find(".cancel_batch_set_vip_price_ctrl").attr("spe_id",x+1);

                $("#tab"+(x+1)).find(".batc_set_stock").attr("id","batc_set_stock"+(x+1));
                $("#tab"+(x+1)).find(".batch_set_price").attr("id","batc_set_price"+(x+1));
                $("#tab"+(x+1)).find(".batch_set_vip_price").attr("id","batc_set_vip_price"+(x+1));

                $("#tab"+(x+1)).find(".batch_set_stock_ctrl").attr("id","batch_set_stock_ctrl"+(x+1));
                $("#tab"+(x+1)).find(".do_batch_set_stock_ctrl").attr("id","do_batch_set_stock_ctrl"+(x+1));
                $("#tab"+(x+1)).find(".cancel_batch_set_stock_ctrl").attr("id","cancel_batch_set_stock_ctrl"+(x+1));
                $("#tab"+(x+1)).find(".batch_set_price_ctrl").attr("id","batch_set_price_ctrl"+(x+1));
                $("#tab"+(x+1)).find(".do_batch_set_price_ctrl").attr("id","do_batch_set_price_ctrl"+(x+1));
                $("#tab"+(x+1)).find(".cancel_batch_set_price_ctrl").attr("id","cancel_batch_set_price_ctrl"+(x+1));
                $("#tab"+(x+1)).find(".batch_set_vip_price_ctrl").attr("id","batch_set_vip_price_ctrl"+(x+1));
                $("#tab"+(x+1)).find(".do_batch_set_vip_price_ctrl").attr("id","do_batch_set_vip_price_ctrl"+(x+1));
                $("#tab"+(x+1)).find(".cancel_batch_set_vip_price_ctrl").attr("id","cancel_batch_set_vip_price_ctrl"+(x+1));
                $('.productsImg').popover({
                    content : '建议800*800px',
                    trigger : 'hover'
                });
            }
            
        }
    }
   // ctabs('dinfo_tabs','dinfo_wp','dinfo_box');
   // $(".dinfo_box:eq(0)").append('<div class="tc"><a class="j_btn cp_all" href="javascript:;" onclick="cp_all(this);">复制到全部</a></div>');
    /*绑定事件*/
   // bindEvent();
    addEvent();
    defaultActive();
}



function saveProduct(){
    //获取关联的商品ID
    var aboutGoodsId = document.getElementsByName("aboutGoodsId");
    //创建个数组用来保存获取到的关联商品ID
    var aboutGoodsIds = new Array();
    for(var i=0;i<aboutGoodsId.length;i++){
        //把获取的商品ID装载数组种
        aboutGoodsIds.push($(aboutGoodsId[i]).val());
    }
    $("input[name='importGoodsIdArray']").val(aboutGoodsIds);
    var isProNoValid = true;
    var pro_box = $(".dinfo_wp .tab-pane");
    if(null != pro_box && pro_box.length>0) {
        for (var j = 0; j < pro_box.length; j++) {
            var _pro = $(pro_box[j]);
            isProNoValid &= checkProNo(_pro.find(".no_input"));
        }
    }

    if(!isProNoValid || !checkProductForm() || !checkImage()){
        return;
    }
    $(".save_goods_info").append("<input type='hidden' name='catId' value="+$(".ch_goods_cate").val()+" >");
    var name = $($(".name_input")[0]).val();
    $(".save_goods_info").append("<input type='hidden' name='goodsName' value='"+name+"' >");
    $(".save_goods_info").append("<input type='hidden' name='goodsSubtitle' value="+$($(".des_input")[0]).val()+" >");
    $(".save_goods_info").append("<input type='hidden' name='goodsNo' value="+$($(".no_input")[0]).val()+" >");
    $(".save_goods_info").append("<input type='hidden' name='brandId' value="+$("#goods_brand").val()+" >");

    $(".save_goods_info").append("<input type='hidden' name='goodsPrice' value="+$($(".sml_input").filter(".pro_price")[0]).val()+" >");//销售价
    $(".save_goods_info").append("<input type='hidden' name='goodsVipPrice' value="+$($(".sml_input").filter(".pro_vip_price")[0]).val()+" >");//会员价 2015.10.22 wuyanbo
    $(".save_goods_info").append("<input type='hidden' name='goodsAdded' value='"+$(".pro_status:checked").val()+"' >");
    $(".save_goods_info").append("<input type='hidden' name='goodsImg' value="+$($(".choose_pro_img")[0]).attr("src")+" >");
    /*$(".save_goods_info").append("<input type='hidden' name='goodsDeno' value="+$(".dw_input").val()+" >");*/
    $(".save_goods_info").append("<input type='hidden' name='freightTemplateId' value="+$("input[name='freightTemplateId']").filter(':checked').val()+">");
    $(".save_goods_info").append("<input type='hidden' name='goodsSeoTitle' value="+$(".seo_title").val()+">");
    $(".save_goods_info").append("<input type='hidden' name='goodsSeoKeyword' value="+$(".seo_key").val()+">");
    $(".save_goods_info").append("<input type='hidden' name='goodsSeoDesc' value="+$(".seo_des").val()+">");
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
            $(".save_goods_info").append("<input type='hidden' name='expandparamValue' value="+$($(".type_expand_sel")[i]).val()+" >");
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
            
            $(".save_goods_info").append("<input type='hidden' name='specsValueImg' value="+$(".up_spec_img_src_"+$(specVal[i]).attr("spec_value_id")).val()+" >");
            /*保存规格别名*/
            $(".save_goods_info").append("<input type='hidden' name='specsValueRemark' value="+$($(specVal[i])).val()+" >");
        }
    }
    var ch_about = $(".ch_about:checked");
    if(null != ch_about && ch_about.length>0){
        for(var i =0;i<ch_about.length;i++){
            $(".save_goods_info").append("<input type='hidden' name='about' value="+$(ch_about[i]).val()+" >");
        }
    }
    /*验证通过并且数据加载到隐藏域中,提交表单*/
    $("#fbsp").attr("disabled", true);
    $(".save_goods_info").submit();

    $(".goods_desc").val($('.summernotedesc').code());
    $(".goods_mobile_desc").val($('.summernotemobile').code());
    
}



function call_save_goods(data){
    if(data>0){
        /*保存完基本信息之后保存详细介绍,完成之后保存货品信息*/
        $(".new_goods_id").val(data);
        $(".save_goods_desc").submit();
    }else{
        showNoDeleteConfirmAlert('出现异常,请重试!');
    }
}


/*保存商品详细回调函数  data:商品ID*/
function call_save_desc(data){
    savePro(data);
}


/*循环保存货品信息*/
function savePro(goodsId){
    var pro_box = $(".dinfo_wp .tab-pane");
    if(null != pro_box && pro_box.length>0){
        for(var j=0;j<pro_box.length;j++){
            var _pro = $(pro_box[j]);
            if(_pro.find(".product_spec").length>0){
                //var param = "newUploadProduct.htm";
                var param = "";
                param+="goodsId="+goodsId;
                param+="&goodsInfoItemNo="+_pro.find(".no_input").val();
                param+="&goodsInfoName="+encodeURI(_pro.find(".name_input").val());
                param+="&goodsInfoSubtitle="+encodeURI(_pro.find(".des_input").val());
                param+="&goodsInfoPreferPrice="+_pro.find(".pro_price").val();
                param+="&goodsInfoVipPrice="+_pro.find(".pro_vip_price").val();
                param+="&goodsInfoCostPrice="+_pro.find(".pro_cost_price").val();
                param+="&goodsInfoMarketPrice="+_pro.find(".pro_mark_price").val();
                param+="&goodsInfoStock=0";
                param+="&goodsInfoAutoPartsType="+$("#goodsInfoAutoPartsType").val();
                param+="&goodsInfoOem="+$("#goodsInfoOem").val();
                param+="&goodsInfoWeight="+_pro.find(".pro_weight").val();
                param+="&goodsInfoImgId="+$(_pro.find(".choose_pro_img")[0]).attr("src");
                var liyangID=[],yasuoID=[];
                var idArr =_pro.find(".choose_style_liYangID");
                if(idArr && idArr.length>0){
                    for(var i=0;i < idArr.length;i++){
                        liyangID.push($(idArr[i]).val());
                    }
                }
                var yasuoArr =_pro.find(".choose_style_yaSuoID");
                if(yasuoArr && yasuoArr.length>0){
                    for(var i=0;i < yasuoArr.length;i++){
                        yasuoID.push($(yasuoArr[i]).val());
                    }
                }
                param+="&autoStyleIdLiYangID="+liyangID.join(';');
                param+="&goodsInfoAutoStyle="+yasuoID.join(';');
                /*获取选择的商品状态*/
                var sta = _pro.find(".pro_status:checked").val();
               
                    param+="&goodsInfoAdded="+sta;
                 
                /*拼接规格信息*/
                var product_spec = _pro.find(".product_spec");
                if(null != product_spec && product_spec.length>0){
                    for(var i =0;i<product_spec.length;i++){
                        var specVal = $(product_spec[i]).val().split("-");
                        /*拼接规格信息以及规格值信息*/
                        param+="&specId="+specVal[0]+"&specDetailId="+specVal[1]+"&specRemark="+specVal[2];
                    }
                }
                /*获取是否参加会员折扣*/
                var discount = _pro.find(".customer_discount:checked").val();
                param+="&isCustomerDiscount="+discount;
                /*获取是否显示在列表页*/
                if(_pro.find(".show_list").prop("checked")){
                    param+="&showList=1";
                }else{
                    param+="&showList=0";
                }
                /*获取是否显示在手机版*/
                if(_pro.find(".show_mobile").prop("checked")){
                    param+="&showMobile=1";
                }else{
                    param+="&showMobile=0";
                }
                
                /*获取选择的服务支持*/
                var supp = _pro.find(".pro_supp:checked");
                if(null != supp && supp.length>0){
                    for(var i = 0;i<supp.length;i++){
                        param+="&support="+$(supp[i]).val();
                    }
                }
                /*拼接图片参数*/
                var imgs = _pro.find(".choose_img");
                if(null != imgs && imgs.length>0){
                    for(var i =0;i<imgs.length;i++){
                        param+="&image="+$(imgs[i]).val();
                    }
                }
                /*保存分舱信息*/
                var wares = _pro.find(".ware_id");
                if(null != wares && wares.length>0){
                    for(var i =0;i<wares.length;i++){
                        param+="&ware="+$(wares[i]).val();
                        param+="&wareStock="+$(_pro.find(".ware_stock")[i]).val();
                        param+="&warePrice="+$(_pro.find(".ware_price")[i]).val();
                        param+="&wareVipPrice="+$(_pro.find(".ware_vip_price")[i]).val();
                    }
                }
                var isMailBay=_pro.find(".isMailBay:checked");
               
                    param+="&isMailBay="+$(isMailBay).val();
                 
                
                param+="&CSRFToken="+$(".token_val").val();
                $.ajax({
                    type: 'post',                   
                    url:'newUploadProduct.htm',
                    data:param,
                    async:false,
                    success: function(data) {
                        if(data>0){
                           
                        }else{
                          
                        }
                    }
                });
            }
        }
       
    }
    
    saveGoodsOk("newUploadGoods.htm","findAllGoods.htm","添加商品成功！");
    
    
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





function addEvent(){
    $(".chooseProimg").click(function(){
        i=1;
        art.dialog.open('queryImageManageByPbAndCidForChoose.htm?CSRFToken='+$(".token_val").val()+'&size=10000', {
            lock: true,
            opacity:0.3,
            width: '900px',
            height: '620px',
            title: '选择图片'
        });
    });

    $(".chooseAutoStyle").click(function(){
        i=1;
        art.dialog.open('chooseAutoStyle.htm?CSRFToken='+$(".token_val").val(), {
            lock: true,
            opacity:0.3,
            width: '600px',
            height: '400px',
            title: '选择适配车型'
        });
    });

}


//保存选择的图片信息
function saveChoooseImage(path){
    var index = $(".dinfo_tabs li").find(".active").index();
    if(path.toString().indexOf(",")>-1){
        var paths = path.toString().split(",");
        for(var i = 0;i<paths.length;i++){
            var path2 = paths[i];
            $(".dinfo_wp").find(".active").find(".choose_imgs").append('<span class="inline"><a class="del_ts" href="javascript:;" onclick="del_ts(this)"><i class="glyphicon glyphicon-remove-sign"></i></a><input type="hidden" class="choose_img" value='+path2+'><a href="javascript:;"><img alt="" class="choose_pro_img" src='+path2+' width="100" height="100" /></a></span>');
        }
    }else{
        $(".dinfo_wp").find(".active").find(".choose_imgs").append('<span class="inline"><a class="del_ts" href="javascript:;" onclick="del_ts(this)"><i class="glyphicon glyphicon-remove-sign"></i></a><input type="hidden" class="choose_img" value='+path+'><a href="javascript:;"><img alt="" class="choose_pro_img" src='+path+' width="100" height="100" /></a></span>');
    }
    showConfirmAlert('要将这些图片应用到其他货品吗?','copyImageToOtherSpec(\''+path+'\')');
}

function saveChooseStyle(jsonArr){

        for(var i = 0;i<jsonArr.length;i++){
            var json = jsonArr[i];
            $(".dinfo_wp").find(".active").find("div.choose_style").append('<div><input type="hidden" class="choose_style_liYangID" value="'+json.liyangId+'"><input type="hidden" class="choose_style_yaSuoID" value="'+json.yasuoId+'">' +json.path.split(',').join('>>>>')+
                '<a class="del_ts" href="javascript:;" onclick="del_ts(this)"><i class="glyphicon glyphicon-remove-sign"></i></a></div>');
        }
    showConfirmAlert('要将这些型号应用到其他货品吗?','copyAutoStyleToOtherSpec('+JSON.stringify(jsonArr).replace(/"/ig,"'")+')');
}

function defaultActive(){
    $(".dinfo_tabs li:first-child").addClass("active");
    $(".dinfo_wp .tab-pane:first-child").addClass("active");
    
}

function del_ts(obj){
    $(obj).parent().remove();
}





/**
 * 添加商品成功 使用
 * @param addUrl 继续添加 
 */
function saveGoodsOk(addUrl,listUrl,tips) {
    $("#modalDialog").remove();
    var confirmDialog =
    '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">'+
    '    <div class="modal-dialog">'+
    '        <div class="modal-content">'+
    '            <div class="modal-header">'+
    '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
    '               <h4 class="modal-title">系统提示</h4>'+
    '           </div>'+
    '           <div class="modal-body">';
    if(tips!='' && tips!=undefined){
        confirmDialog +=tips;
    }else{
        confirmDialog +='添加成功，继续添加商品吗？';
    }
    confirmDialog += '           </div>'+
    '           <div class="modal-footer">'+
    '               <button type="button" class="btn btn-primary" onclick="jumpUrl(\''+addUrl+'\');">继续添加</button>'+
    '               <button type="button" class="btn btn-default" onclick="jumpUrl(\''+listUrl+'\');">商品列表</button>'+
    '           </div>'+
    '       </div>'+
    '   </div>'+
    '</div>';
    $(document.body).append(confirmDialog);
    $('#modalDialog').modal('show');
}

function jumpUrl(url){
    window.location.href=url;
}



function chImg(obj){
    if(obj.checked==true){
        $(obj).parents("td").next().next().find(".spec_value_img").show();
    }else{
        $(obj).parents("td").next().next().find(".spec_value_img").hide();
    }
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
                aboutGoodsHtml = aboutGoodsHtml + "<tr><td><input type='checkbox' goods_name='"+list[i].goodsName+"' goods_no='"+list[i].goodsNo+"' goods_img='"+list[i].goodsImg+"' class='form-control' name='aboutGoodsIdSelect' value='" + list[i].goodsId + "'/></td>" + "<td><img width='50' height='50' src=" + list[i].goodsImg + " /></td><td title='"+list[i].goodsNo+"'>" + list[i].goodsNo + "</td><td>" + list[i].goodsName + "</td>";
                aboutGoodsHtml = aboutGoodsHtml + "<td>" + list[i].goodsType.typeName + "</td><td>" + list[i].goodsBrand.brandName + "</td></tr>";
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

function showAddGoodsRelModal() {
    queryGoodsByParam(1);
    $("#addGoodsRelModal").modal("show");
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
                        '   <td><span><a href="javascript:;" onclick="delRelGood($(\'#rel_goods_tr'+$(this).val()+'\'))">删除</a></span></td>' +
                        '</tr>'
            }
        }
    });
    if(!selecteOne) {
        showTipAlert("至少选择一个商品，才能保存！");
        return;
    }
    $("#select_rel_goods tbody").append(html);
    $("#select_rel_goods thead").show();
    $("#addGoodsRelModal").modal("hide");
}


function displayBatchSetStock(obj) {
    var spe_id = $(obj).attr("spe_id");
    $("#batc_set_stock"+spe_id).css("display","inline-block");
    $(obj).hide();
    $("#do_batch_set_stock_ctrl"+spe_id).show();
    $("#cancel_batch_set_stock_ctrl"+spe_id).show();
}

function displayBatchSetPrice(obj, price_type) {
    var spe_id = $(obj).attr("spe_id");
    $("#batc_set_"+price_type+"price"+spe_id).css("display","inline-block");
    $(obj).hide();
    $("#do_batch_set_"+price_type+"price_ctrl"+spe_id).show();
    $("#cancel_batch_set_"+price_type+"price_ctrl"+spe_id).show();
}

function hideBatchSetStock(obj) {
    var spe_id = $(obj).attr("spe_id");
    $("#batc_set_stock"+spe_id).hide();
    $("#batch_set_stock_ctrl"+spe_id).show();

    $("#do_batch_set_stock_ctrl"+spe_id).hide();
    $("#cancel_batch_set_stock_ctrl"+spe_id).hide();
}

function hideBatchSetPrice(obj, price_type) {
    var spe_id = $(obj).attr("spe_id");
    $("#batc_set_"+price_type+"price"+spe_id).hide();
    $("#batch_set_"+price_type+"price_ctrl"+spe_id).show();

    $("#do_batch_set_"+price_type+"price_ctrl"+spe_id).hide();
    $("#cancel_batch_set_"+price_type+"price_ctrl"+spe_id).hide();
}

function doBatchSetStock(obj) {
    var spe_id = $(obj).attr("spe_id");
    var stock = $("#batc_set_stock"+spe_id).val();
    var digitsReg = /^[0-9]+$/;
    if(stock!=''&&(digitsReg.test(stock))){
        $(obj).removeClass('error');
        $(obj).prev('.error').remove();
    }else{
        $(obj).addClass('error');
        $(obj).prev('.error').remove();
        $(obj).before('<label class="error">请输入整数</label>');
        return;
    }
    $("#tab"+spe_id).find(".ware_stock").each(function () {
        $(this).val(stock);
    })
    checkProductForm();
    hideBatchSetStock(obj);
}

function doBatchSetPrice(obj, price_type) {
    var spe_id = $(obj).attr("spe_id");
    var tdIndex = $(obj).parent().index();
    var price = $("#batc_set_"+price_type+"price"+spe_id).val();
    var numberReg =/^[0-9]+[.]{0,1}[0-9]{0,2}$/;
    if(price!=''&&(numberReg.test(price))){
        $(obj).removeClass('error');
        $(obj).prev('.error').remove();
    }else{
        $(obj).addClass('error');
        $(obj).prev('.error').remove();
        $(obj).before('<label class="error">请输入数字</label>');
        return;
    }
    $("#tab"+spe_id+" tr").each(function () {
        $(this).find("td:eq("+tdIndex+") input[class*='ware_"+price_type+"price']").val(price);
    })

    checkProductForm();
    hideBatchSetPrice(obj, price_type);
}

function copyWareToOtherSpec(obj) {
    var spe_id = $(obj).attr("spe_id");
    var from_pro = $("#tab"+spe_id);
    var pro_box = $(".dinfo_wp .tab-pane");
    if(null != pro_box && pro_box.length>0) {
        for (var j = 0; j < pro_box.length; j++) {
            var _pro = $(pro_box[j]);
            var wares = _pro.find(".ware_id");
            if(null != wares && wares.length>0){
                for(var i =0;i<wares.length;i++){
                    $(_pro.find(".ware_stock")[i]).val(from_pro.find(".ware_stock")[i].value);
                    $(_pro.find(".ware_price")[i]).val(from_pro.find(".ware_price")[i].value);
                    $(_pro.find(".ware_vip_price")[i]).val(from_pro.find(".ware_vip_price")[i].value);//2015.10.21 wuyanbo 添加会员价
                }
            }
        }
    }
    showTipAlert("操作成功，已将此设置应用到其他货品！");
}


function checkProductForm(){
    var f =true;
    var forms =  $(".dinfo_wp .form-horizontal");
    var flag=$(".p_code");
    if(flag.val().length<10){
        flag.addClass('error');
        flag.next('.error').remove();
        flag.after('<label class="error">至少输入10个字符</label>');
        f=f&&false;
    }else{
        flag.removeClass('error');
        flag.next('.error').remove();
    }

    for(var i=0;i<forms.length;i++){
        var requireds = $(forms[i]).find(".required");
        for(var j=0;j<requireds.length;j++){
            if(requireds[j].value==''){
                $(requireds[j]).addClass('error');
                $(requireds[j]).next('.error').remove();
                $(requireds[j]).after('<label class="error">不能为空</label>');
                f=f&&false;
            }
            else{
                $(requireds[j]).removeClass('error');
                $(requireds[j]).next('.error').remove();
            }
        }

        var numbers = $(forms[i]).find(".number");
        var numberReg =/^[0-9]+[.]{0,1}[0-9]{0,2}$/;
        for(var j=0;j<numbers.length;j++){
            if(numbers[j].value!=''&&(numberReg.test(numbers[j].value))){
                $(numbers[j]).removeClass('error');
                $(numbers[j]).next('.error').remove();
            }else{
                $(numbers[j]).addClass('error');
                $(numbers[j]).next('.error').remove();
                $(numbers[j]).after('<label class="error">请输入合法的数字</label>');
                f=f&&false;
            }
        }

        var digits = $(forms[i]).find(".digits");
        var digitsReg = /^[0-9]+$/;
        for(var j=0;j<digits.length;j++){
            if(digits[j].value!=''&&(digitsReg.test(digits[j].value))){
                $(digits[j]).removeClass('error');
                $(digits[j]).next('.error').remove();
            }else{
                $(digits[j]).addClass('error');
                $(digits[j]).next('.error').remove();
                $(digits[j]).after('<label class="error">请输入整数</label>');
                f=f&&false;
            }
        }

        var exist_flag = $(forms[i]).find(".exist_flag").val();
        if(exist_flag=='0'){
            $(exist_flag).removeClass('error');
            $(exist_flag).next('.error').remove();
        }else{
            $(exist_flag).addClass('error');
            $(exist_flag).next('.error').remove();
            $(exist_flag).after('<label class="error">商品编号不正确</label>');
            f=f&&false;
        }
    }
    return f;
}

function checkImage(){
    var f =true;
    var forms =  $(".tab-pane");
    for(var i=0;i<forms.length-1;i++){
        var s =  $(forms[i]).find(".choose_imgs").html();
        if(s==''){
            showTipAlert('请添加对应货品的图片！');
            f=f&&false;
        }

    }
    return f;
}

/*验证货品编号是否已经存在*/
function checkProNo(obj){
    var isValid = true;
    if($(obj).val()!=''&& $(obj).val()!=undefined){
        $(obj).removeClass('error');
        $(obj).next('.error').remove();
    }else{
        $(obj).addClass('error');
        $(obj).next('.error').remove();
        $(obj).after('<label class="error">不能为空</label>');
        isValid = false;
        return isValid;
    }
    var numandletterReg = /^[0-9a-zA-Z]+$/;
    if($(obj).val()!=''&&(numandletterReg.test($(obj).val()))){
        $(obj).removeClass('error');
        $(obj).next('.error').remove();
    }else{
        $(obj).addClass('error');
        $(obj).next('.error').remove();
        $(obj).after('<label class="error">请输入字母或数字</label>');
        isValid = false;
        return isValid;
    }
    /*验证货品编号是否已经存在*/
    if($(obj).val()!=undefined && $(obj).val().trim().length>0){
        var param = "checkProductNo.htm?productNo="+$(obj).val()+"&time="+Math.random();
        $.ajax({
            type: 'post',
            url:param,
            async:false,
            success: function(data) {
                if(!data){
                    $(obj).addClass('error');
                    $(obj).next('.error').remove();
                    $(obj).after('<label class="error">编号已经存在</label>');
                    $(obj).parent().find(".exist_flag").val("1");
                    isValid = false;
                }else{
                    /*验证当前输入的是否有重复*/
                    var inputs = $(".dinfo_wp .no_input");
                    var count = 0;
                    for(var i =0;i<inputs.length;i++){
                        var no = $(inputs[i]).val();
                        if($(obj).val()==no){
                            count = parseInt(count)+parseInt(1);
                        }
                    }
                    /*如果数量大于1,其中有一个是自身*/
                    if(parseInt(count)>1){
                        $(obj).addClass('error');
                        $(obj).next('.error').remove();
                        $(obj).after('<label class="error">编号已经使用</label>');
                        $(obj).parent().find(".exist_flag").val("1");
                        isValid = false;
                    }else{
                        $(obj).removeClass('error');
                        $(obj).next('.error').remove();
                        $(obj).parent().find(".exist_flag").val("0");
                    }
                }
            }
        });
    }
    return isValid;
}

function copyImageToOtherSpec(path) {
    if(path.toString().indexOf(",")>-1){
        var paths = path.toString().split(",");
        for(var i = 0;i<paths.length;i++){
            var path2 = paths[i];
            $(".dinfo_wp").find(".tab-pane").not(".active").find(".choose_imgs").append('<span class="inline"><a class="del_ts" href="javascript:;" onclick="del_ts(this)"><i class="glyphicon glyphicon-remove-sign"></i></a><input type="hidden" class="choose_img" value='+path2+'><a href="javascript:;"><img alt="" class="choose_pro_img" src='+path2+' width="100" height="100" /></a></span>');
        }
    }else{
        $(".dinfo_wp").find(".tab-pane").not(".active").find(".choose_imgs").append('<span class="inline"><a class="del_ts" href="javascript:;" onclick="del_ts(this)"><i class="glyphicon glyphicon-remove-sign"></i></a><input type="hidden" class="choose_img" value='+path+'><a href="javascript:;"><img alt="" class="choose_pro_img" src='+path+' width="100" height="100" /></a></span>');
    }
    $("#modalDialog").modal("hide");
}

function copyAutoStyleToOtherSpec(jsonArr) {
    for(var i = 0;i<jsonArr.length;i++){
        var json = jsonArr[i];
        $(".dinfo_wp").find(".tab-pane").not(".active").find("div.choose_style").append('<div><input type="hidden" class="choose_style_liYangID" value="'+json.liyangId+'"><input type="hidden" class="choose_style_yaSuoID" value="'+json.yasuoId+'">' +json.path.split(',').join('>>>>')+
            '<a class="del_ts" href="javascript:;" onclick="del_ts(this)"><i class="glyphicon glyphicon-remove-sign"></i></a></div>');
    }
    $("#modalDialog").modal("hide");
}

function generateProNo(obj) {
    var proNo = parseInt(new Date().format("yyyyMMddhhmmss")+"1");
    var proNoInput = $(".dinfo_wp").find(".active").find(".p_code");
    $(proNoInput).val(proNo);
    showConfirmAlert("要生成其他货品的编号吗?","generateOtherProNo("+proNo+")");
    checkProNo($(proNoInput));
}

function generateOtherProNo(proNo) {
    var proNoInput = $(".dinfo_wp").find(".tab-pane").not(".active").find(".p_code");
    for(var i=0;i<proNoInput.length;i++) {
        $(proNoInput[i]).val(proNo+(i+1));
    }
    $("#modalDialog").modal("hide");
}

//2015.10.21 wuyanbo 添加会员价
function changeAllPrice(obj,price_type){
    if($(obj).val()!=''&&/^([0-9][0-9]*(\.[0-9]{1,2})?|0\.(?!0+$)[0-9]{1,2})$/.test($(obj).val())){
        var panes = $(".tab-pane");
        var cprice = $(obj).val();
        if(panes.find(".ware_"+price_type+"price").length!=0){
            panes.find(".ware_"+price_type+"price").each(function(){
                $(this).val(cprice);
            });
        }
        if(price_type == ""){
            panes.find(".pro_cost_price").val(cprice);
            panes.find(".pro_mark_price").val(cprice);
            $('.pro_price').val($(obj).val());
        }else if(price_type == "vip_"){
            $('.pro_vip_price').val($(obj).val());
        }
    }else{
        //showTipAlert('请输入正确的价格！');
    }
}

function changeValueId(obj){
	var s = $(obj).val();
	$(obj).parents("td").prev().find(".check_spec").val(s);
	
}
//删除关联商品
function delRelGood(relGood){
    relGood.remove();
    if($("#select_rel_goods tbody").html()=='') {
        $("#select_rel_goods thead").hide();
    }
}