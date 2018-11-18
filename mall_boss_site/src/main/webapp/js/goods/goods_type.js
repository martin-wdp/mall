$(function(){
    $('.extendtip').popover({
        content : '商品的扩展属性，显示在前台商品列表中',
        trigger : 'hover'
    });
});

function showAddType() {
    /*查询所有的商品品牌  然后添加到ztree中*/
    $.get("loadAllBrand.htm?CSRFToken="+$("#CSRFToken").val(), function (data) {
        for (var i = 0; i < data.length; i++) {
            $("#relBrand").append("<option value="+data[i].brandId+" >"+data[i].brandName+"</option>");
        }
        $('select[data-live-search="true"]').select2();
    });
    toStep1("");
    $("#addType").modal("show");

}

function toStep1(location) {
    $('.add_type1'+location).show();
    $('.add_type2'+location).hide();
    $('.add_type3'+location).hide();
    $('.add_type4'+location).hide();
}
/**
 * 返回到第一步
 */
function backToStep1() {
    removeValid("step2_table");
    $('.add_type2').hide();
    $('.add_type1').show();
}
/**
 * 去第二步
 */
function toStep2() {
    if(!$("#saveTypeForm").valid()) return;
    addValid("step2_table");
    $('.add_type1').hide();
    $('.add_type2').show();
}
/**
 * 在这一步加验证
 * @param tableId 根据tableId在哪一步加验证
 */
function addValid(tableId) {
    $("#"+tableId+" input").each(function () {
        $(this).addClass($(this).attr("clazz"));
    });
}
/**
 *去掉这一步的验证
 * @param tableId 根据tableId判断去掉哪一步的验证
 */
function removeValid(tableId) {
    $("#"+tableId+" input").each(function () {
        $(this).removeClass($(this).attr("clazz"));
        if(!$(this).hasClass("tabinput")) {
            $(this).removeClass("error");
        }
    });
}
/**
 * 去第三步
 */
function toStep3() {
    $("input").each(function(){
        $(this).removeClass("error");
    });
    $(".error").remove();
    $(".value_tip").remove();
    checkInput("saveTypeForm","expandnames");
   // checkInput("saveTypeForm","expandnicknames");
    checkInput("saveTypeForm","expandparamsort", /^[0-9]*[1-9][0-9]*$/,"必须为正整数");
    $("#saveTypeForm").find("input[name=tabinput]").each(function() {
        if($(this).parent().find("input[name=tabinput]").length<2) {
            $(this).after('');
            $(this).blur(function() {
                if($(this).val()!='') {
                    $(this).next().next().remove();
                }
            });
            $(this).addClass("error");
            $(this).after('<label generated="true" class="error value_tip" id="expand_value_tip">不能为空</label>');
            return;
        }
        if($(this).next().length>0&&$(this).val()=='') {
            $(this).addClass("error");
        }
    });


    if($("#saveTypeForm .error").length>0) return;
    if(!$("#saveTypeForm").valid()) return;
    showSpecs("specs");
    $('.add_type2').hide();
    $('.add_type3').show();
}

/**
 * 去第四步
 * */
function toStep4(){
    if($("#specs").val() == null ){
        $("#specsTips").text("不能为空");
        return;
    }else{
        $("#specsTips").text("");
        $('.add_type3').hide();
        $('.add_type4').show();
    }
}

var expandCount = 0;
/**
 * 添加一个扩展属性
 */
function addOneExpand() {
    expandCount ++;
    var html =
        '<tr id="expand_tr'+expandCount+'">'+
            '<td>'+
                '<span class="text-danger">*</span>'+
                '<input type="text" name="expandnames" class="w100">'+
            '</td>'+
            '<td><input type="text" name="expandnicknames" class="w100"></td>'+
            '<td><span class="text-danger">*</span><div class="tags w300" id="tag'+expandCount+'"><input type="hidden" name="expandvalues"/></div></td>'+
            '<td><a href="javascript:;" onclick="changeIsShow(this)"><input type="hidden" name="expandparamisshow" value="1"><span class="label label-success"> 是</span> </a></td>'+
            '<td><span class="text-danger">*</span><input type="text" name="expandparamsort" class="w50"></td>'+
            '<td><a href="javascript:;"  onclick="removeExpand('+expandCount+')">删除</a></td>'+
        '</tr>';
    $("#expandTbody").append(html);
    $('#tag'+expandCount).tabControl({maxTabCount:99/*最大标签数*/,tabW:80/*标签最大长度*/,tabH : 25},""/*预置标签值*/);
}
/**
 * 改变扩展属性的显示与否
 * @param obj 按钮
 */
function changeIsShow(obj) {
    if($(obj).find("input").val()=='0') {
        $(obj).html('<input type="hidden" name="expandparamisshow" value="1"><span class="label label-success"> 是</span>');
    } else {
        $(obj).html('<input type="hidden" name="expandparamisshow" value="0"><span class="label label-default"> 否</span>');
    }
}
/**
 * 改变扩展属性的显示与否
 * @param obj 按钮
 */
function changeIsShowUpdate(obj) {
    if($(obj).find("input").val()=='0') {
        $(obj).html('<input type="hidden" name="expandparamIsshow" value="1"><span class="label label-success"> 是</span>');
    } else {
        $(obj).html('<input type="hidden" name="expandparamIsshow" value="0"><span class="label label-default"> 否</span>');
    }
}
/**
 * 删除扩展属性
 * @param trId 行号
 */
function removeExpand(trId) {
    $("#expand_tr"+trId).remove();
}

function showSpecs(locationId) {
    $("#" + locationId).html('');
    $.ajax({
        url:"queryAllSpec.htm?CSRFToken="+$("#CSRFToken").val(),
        async:false,
        success:function (data) {
            for (var i = 0; i < data.length; i++) {
            	if(data[i].specDelflag=='0'){
            	    $("#" + locationId).append("<option value=" + data[i].specId + ">" + data[i].specRemark + "</option>");
            	}
            }
            $('select[data-live-search="true"]').select2();
        }
    });
}

function addSpec() {
    var html = '';
    var selectSpecNames = $("#specs").find("option:selected").text();
    var selectSpecIds = $("#specs").val();
    if(selectSpecIds!=undefined) {
        for(var i=0;i<selectSpecIds.length;i++) {
            var specName = $("#specs option[value="+selectSpecIds[i]+"]");
            html +=
                '<tr id="spec_tr'+selectSpecIds[i]+'">'+
                '<td>'+specName.html()+'</td><td><a href="javascript:;" onclick="deleteSpec('+selectSpecIds[i]+')">删除</a><input type="hidden" name="specIds" value="'+selectSpecIds[i]+'" </td>'+
                '</tr>';
        }
    }
    $("#step3_table tbody").html(html);
}

/**
 * 删除
 * @param specId
 */
function deleteSpec(specId) {
    $("#spec_tr"+specId).remove();
    $("#specs option[value="+specId+"]").attr("selected",false);

}
var paramCount = 0;
function addParam() {
    paramCount ++;
    var html =
        '<tr id="param_tr'+paramCount+'">'+
        '    <td><span class="text-danger">*</span><input type="text" name="paramname" class="w200 required specstr"></td>'+
        '   <td><input type="text" name="paramnickname" class="w200 specstr"></td>'+
        '   <td><a href="javascript:;" onclick="deleteParam('+paramCount+')">删除</a></td>'+
        '</tr>';
    $("#step4_table tbody").append(html);
}

function deleteParam(trId) {
    $("#param_tr"+trId).remove();
}
var num=0;
function submitSaveTypeForm() {
    $(".value_tip").remove();
    checkInput("saveTypeForm",'paramname');
    //checkInput("saveTypeForm",'paramnickname');

    if($(".error").length==0&&num==0) {
        num+=1;
        $("#saveTypeForm").submit();
    }

}






function showUpdateType(typeId) {
    toStep1("_update");
    $.get("findTypeVoByTypeId.htm?CSRFToken="+$("#CSRFToken").val()+"&typeId=" + typeId, function (data) {
        $("#typeId").val(data.typeId);
        $("#typeName").val(data.typeName);
        $("#typeNickname").val(data.typeNickname);
        /*if (data.typeIsreal == "1") {
            $("#inlineRadio1").click();
        }
        else {
            $("#inlineRadio2").click();
        }*/
        /*查询所有的商品品牌  然后添加到ztree中*/
        $.get("loadAllBrand.htm?CSRFToken=" + $("#CSRFToken").val(), function (brandList) {
            $("#relBrand_update").html("");
            for (var i = 0; i < brandList.length; i++) {
                $("#relBrand_update").append("<option value=" + brandList[i].brandId + " >" + brandList[i].brandName + "</option>");
            }
            for (var l = 0; l < data.brands.length; l++) {
                $("#relBrand_update option[value=" + data.brands[l].brand.brandId + "]").attr("selected", true);
            }
            ;
            $('select[data-live-search="true"]').select2();
        });

        /*设置扩展属性*/
        var expandParamHtml = "";
        $("#step2_table_update tbody").html("");
        for (var i = 0; i < data.expandParams.length; i++)
        {
            expandParamHtml = '';
            expandParamHtml = expandParamHtml + "<tr id='expand_update_tr_"+data.expandParams[i].expandparamId+"'>" +
            "<td>" + "<input type='hidden' name='expandparamId' value='" + data.expandParams[i].expandparamId + "'/>" + "<input type='hidden' name='expandparamDelflag' value='" + data.expandParams[i].expandparamDelflag + "'/>" + "<span class='text-danger'>*</span><input type='text' name='expandnames' value='" + data.expandParams[i].expandparamName + "' ></td>" +
            "<td><input type='text' name='expandnicknames' value='" + data.expandParams[i].expandparamNickname + "'></td>" +
            '<td style="text-align:left;"><span class="text-danger">*</span><div class="tags w300" id="tag_'+data.expandParams[i].expandparamId+'"> <input type="hidden" name="paramId" value="'+data.expandParams[i].expandparamId+'"/>';
            var expandValue = '';
            for (var k = 0; k < data.expandParams[i].valueList.length; k++) {
                expandParamHtml +=
                    '<input class="tabinput valid tabinput_defautl" name="tabinput" style="width: 80px; height: 25px; display: none;" type="text" value="'+data.expandParams[i].valueList[k].expandparamValueName+'">' +
                    '<span name="tab" id="radius">' +
                    '   <input type="hidden" name="expandParamId" value="'+data.expandParams[i].expandparamId+'"/>' +
                    '   <input type="hidden" name="expandParamValueDelFlag'+data.expandParams[i].expandparamId+'" value="0"/>' +
                    '   <input type="hidden" name="expandParamValueId'+data.expandParams[i].expandparamId+'" value="'+data.expandParams[i].valueList[k].expandparamValueId+'"/> ' +
                    '   <input type="hidden" name="expandvalues'+data.expandParams[i].expandparamId+'" value="'+data.expandParams[i].valueList[k].expandparamValueName+'"/> '+
                    '   <input type="hidden" name="expandvaluesort'+data.expandParams[i].expandparamId+'" value="'+data.expandParams[i].valueList[k].expandparamValueSort+'"/> '+
                    '   <b>'+ data.expandParams[i].valueList[k].expandparamValueName +'</b>' +
                    '<a id="deltab">×</a></span>';
                expandValue = expandValue + data.expandParams[i].valueList[k].expandparamValueName + "|";
            }
            expandParamHtml += '<input type="hidden" name="expandvalues" value="'+expandValue+'"></div>';


            expandParamHtml += '</td>';

            expandParamHtml +='<td>';
            if (data.expandParams[i].expanparamIsshow == '1') {
                expandParamHtml += '<a href="javascript:;" onclick="changeIsShowUpdate(this)"><input type="hidden" name="expandparamIsshow" value="1"> <span class="label label-success">是</span> </a>';
            } else {
                expandParamHtml += '<a href="javascript:;" onclick="changeIsShowUpdate(this)"><input type="hidden" name="expandparamIsshow" value="0"> <span class="label label-default">否</span> </a>';
            }
            expandParamHtml += '</td>';
            expandParamHtml += "<td><input maxlength='3' name='expandparamsort' value='" + data.expandParams[i].expandparamSort + "' class='w50' ></td>";
            expandParamHtml += '<td><a href="javascript:;" onclick="removeUpdateExpand(\'_'+data.expandParams[i].expandparamId+'\')">删除</a></td>' + "</tr>";
            /*给扩展属性列表设置值*/
            $("#step2_table_update tbody").append(expandParamHtml);
            $('#tag_'+data.expandParams[i].expandparamId).tabControl({maxTabCount:15/*最大标签数*/,tabW:80/*标签最大长度*/,tabH : 25},""/*预置标签值*/);
        }
        //$('.tags').tabControl({maxTabCount:15/*最大标签数*/,tabW:80/*标签最大长度*/,tabH : 25},""/*预置标签值*/);
        $(".tabinput_defautl").each(function() {
            initDefaultTab($(this).parent(),$(this),$(this).val());
        });


        /*绑定商品规格*/
        showSpecs("specs_update");
        var html = "";
        for (var i = 0; i < data.specVos.length; i++)
        {
            if(data.specVos[i].goodsSpec != null){
                html +=
                    '<tr id="spec_update_tr_'+data.specVos[i].goodsSpec.specId+'">'+
                    '<td>'+data.specVos[i].goodsSpec.specRemark+'</td><td><a href="javascript:;" onclick="deleteUpdateSpec(\'_'+data.specVos[i].goodsSpec.specId+'\')">删除</a><input type="hidden" name="specIds" value="'+data.specVos[i].goodsSpec.specId+'"/> </td>'+
                    '</tr>';
                $("#specs_update option[value="+data.specVos[i].goodsSpec.specId+"]").attr("selected",true);
            }else{

            }


        }

        $("#step3_table_update tbody").html(html);


        /*绑定商品详细参数*/
        var detailhtml = "";
        for (var i = 0; i < data.params.length; i++)
        {
            detailhtml = detailhtml + '<tr id="param_update_tr_'+data.params[i].paramId+'">' +
            "<td>" +
            "   <input type='hidden' name='typeParamId' value='" + data.params[i].paramId + "'/>" +
            "   <input type='hidden' name='paramDelflag' value='" + data.params[i].paramDelflag + "'/>" +
            '   <span class="text-danger">*</span>'+
            "   <input type='text' name='paramname' value='" + data.params[i].paramName + "'>" +
            "</td>" +
            "<td>" +
            "   <input type='text' name='paramnickname' value='" + data.params[i].paramNickname + "' >" +
            "</td>" +
            "<td>" +
            '   <a href="javascript:;" onclick="deleteUpdateParam(\'_'+data.params[i].paramId+'\')">删除</a>' +
            '</td>'+
            "</tr>";
        }
        $("#step4_table_update tbody").html(detailhtml);
    });
    $("#updateType").modal("show");
}
/**
 * 返回到第一步
 */
function backToUpdateStep1() {
    removeValid("step2_table_update");
    $('.add_type2_update').hide();
    $('.add_type1_update').show();
}
/**
 * 去第二步
 */
function toUpdateStep2() {
    if(!$("#updateTypeForm").valid()) return;
    addValid("step2_table_update");
    $('.add_type1_update').hide();
    $('.add_type2_update').show();
}
/**
 * 去第三步
 */
function toUpdateStep3() {
    $("input").each(function(){
        if(!$(this).hasClass("tabinput")) {
            $(this).removeClass("error");
        }
    });
    $(".error").each(function(){
        if(!$(this).hasClass("tabinput")) {
            $(this).remove();
        }

    });
    $(".value_tip").remove();
    checkInput("updateTypeForm","expandnames");
    //checkInput("updateTypeForm","expandnicknames");
    checkInput("updateTypeForm","expandparamsort",/^[0-9]*[1-9][0-9]*$/,"必须为正整数");

    $("#updateTypeForm").find("input[name=tabinput]").each(function() {
        //alert($(this));
        if($(this).parent().parent().parent().css('display')=="none") return;
        if($(this).parent().find("span[name=tab]:visible").length<1) {
            if($(this).val()=='') {
                $(this).after('');
                $(this).blur(function() {
                    if($(this).val()!='') {
                        $(this).next().next().remove();
                    }
                });
                $(this).addClass("error");
                $(this).after('<label generated="true" class="error value_tip" id="expand_value_tip">不能为空</label>');
            }

            return;
        }

        if($(this).next().length>0&&$(this).val()=='') {
            $(this).addClass("error");
        }
    })


    if($("#updateTypeForm").find(".error").length>0) return;
    if(!$("#updateTypeForm").valid()) return;
    //showUpdateSpecs();
    $('.add_type2_update').hide();
    $('.add_type3_update').show();
}

function toUpdateStep4(){
    if($("#specs_update").val() == null){
        $("#specsTipsUpdate").text("不能为空");
        return;
    }else{
        $('.add_type3_update').hide();
        $('.add_type4_update').show();
    }
}

var expandUpdateCount = 0;
/**
 * 添加一个扩展属性
 */
function addOneUpdateExpand() {
    expandUpdateCount ++;
    var html =
        '<tr id="expand_update_tr'+expandUpdateCount+'">'+
        '<td>'+
        '<span class="text-danger">*</span>'+
        '<input type="hidden" name="expandparamId" value="-1"/><input type="hidden" class="expandparamDelflag" name="expandparamDelflag" value="0"/><input type="text" name="expandnames" class="w100" clazz="required specstr">'+
        '</td>'+
        '<td><input type="text" name="expandnicknames" class="w100"></td>'+
        '<td style="text-align:left;"><span class="text-danger">*</span><div class="tags w300" id="tag'+expandUpdateCount+'"><input type="hidden" name="paramId" value="-1"/></div></td>'+
        '<td><a href="javascript:;" onclick="changeIsShow(this)"><input type="hidden" name="expandparamIsshow" value="1"><span class="label label-success"> 是</span> </a></td>'+
        '<td><span class="text-danger">*</span><input type="text" name="expandparamsort" class="w50"></td>'+
        '<td><a href="javascript:;"  onclick="removeUpdateExpand('+expandUpdateCount+')">删除</a></td>'+
        '</tr>';
    $("#step2_table_update tbody").append(html);
    $('#tag'+expandUpdateCount).tabControl({maxTabCount:15/*最大标签数*/,tabW:80/*标签最大长度*/,tabH : 25},""/*预置标签值*/);
    $('#tag'+expandUpdateCount).append('<input type="hidden" name="expandvalues"/>');
}
/**
 * 删除扩展属性
 * @param trId 行号
 */
function removeUpdateExpand(trId) {
    if($("#expand_update_tr"+trId).find("input[name=expandparamId]").val()=='-1') {
        $("#expand_update_tr"+trId).remove();
    } else {
        if($("#step2_table_update tbody tr:visible").length>1) {
            $("#expand_update_tr" + trId).find("input[name=expandparamDelflag]").val('1');
            $("#expand_update_tr" + trId).hide();
        }else{
            showTipAlert("请至少保留一个扩展属性！");
        }
    }

}

function showUpdateSpecs() {
    var spec=$("input[name=specIds]");
    $("#specs_update").html('');
    $.get("queryAllSpec.htm?CSRFToken="+$("#CSRFToken").val(), function (data){
        for (var i = 0; i < data.length; i++) {
            $("#specs_update").append("<option value="+data[i].specId+" >"+data[i].specRemark+"</option>");
        }
        $('select[data-live-search="true"]').select2();
    });
}

function addUpdateSpec() {
    var html = '';
    var selectSpecNames = $("#specs_update").find("option:selected").text();
    var selectSpecIds = $("#specs_update").val();
    if(selectSpecIds!=undefined) {
        for(var i=0;i<selectSpecIds.length;i++) {
            var specName = $("#specs_update option[value="+selectSpecIds[i]+"]");
            html +=
                '<tr id="spec_update_tr'+selectSpecIds[i]+'">'+
                '<td>'+specName.html()+'</td><td><a href="javascript:;" onclick="deleteUpdateSpec('+selectSpecIds[i]+')">删除</a><input type="hidden" name="specIds" value="'+selectSpecIds[i]+'" /></td>'+
                '</tr>';
        }
    }
    $("#step3_table_update tbody").html(html);
}

/**
 * 删除
 * @param specId
 */
function deleteUpdateSpec(specId) {
    $("#spec_update_tr"+specId).remove();
    $("#specs_update option[value="+specId+"]").attr("selected",false);
    $('select[data-live-search="true"]').selectpicker('refresh');
}
var paramUpdateCount = 0;
function addUpdateParam() {
    paramUpdateCount ++;
    var html =
        '<tr id="param_update_tr'+paramUpdateCount+'">'+
        '  <input type="hidden" name="paramDelflag" value="0"/><input type="hidden" name="typeParamId" value="-1">' +
        '    <td><span class="text-danger">*</span><input type="text" name="paramname" class="w200 required specstr"></td>'+
        '   <td><input type="text" name="paramnickname" class="w200 specstr"></td>'+
        '   <td><a href="javascript:;" onclick="deleteUpdateParam('+paramUpdateCount+')">删除</a></td>'+
        '</tr>';
    $("#step4_table_update tbody").append(html);
}

function deleteUpdateParam(trId) {
    if($("#param_update_tr"+trId).find("input[name=typeParamId]").val()=='-1') {
        $("#param_update_tr"+trId).remove();
    } else {
        if($("#step4_table_update tbody tr:visible").length>1) {
            $("#param_update_tr" + trId).find("input[name=paramDelflag]").val('1');
            $("#param_update_tr" + trId).hide();
        }else{
            showTipAlert("请至少保留一个规格参数！");
        }
    }
}

function submitUpdateTypeForm() {
    //把搜索的条件装到 修改 用于的form 表单里
    $('#searchText').val($('input[name="searchText"]').val());
    $(".value_tip").remove();
    checkInput("updateTypeForm",'paramname');
    //checkInput("updateTypeForm",'paramnickname');

    if($("#updateTypeForm").find(".error").length>0) return;
    //if($(".error").length>0) return;
    $("#updateTypeForm").submit();
}




function checkInput(formId,name,regExp,message) {
    $("#"+formId).find("input[name="+name+"]").each(function() {
        if($(this).val()=='') {
            $(this).addClass("error");
            $(this).after('<label generated="true" class="error value_tip" id="expand_value_tip">不能为空</label>');
        } else if(regExp!='' && regExp!=undefined && !regExp.test($(this).val())) {
            $(this).removeClass("error");
            $(this).next().remove();
            $(this).addClass("error");
        } else {
            $(this).removeClass("error");
            $(this).next().remove();
        }
        $(this).unbind("blur");
        $(this).blur(function() {
            if($(this).val()=='') {
                $(this).next().remove();
                $(this).removeClass("error");
                $(this).addClass("error");
                $(this).after('<label generated="true" class="error value_tip" id="expand_value_tip">不能为空</label>');
            } else {
                if(regExp!='' && regExp!=undefined && !regExp.test($(this).val())) {
                    $(this).removeClass("error");
                    $(this).next().remove();
                    $(this).addClass("error");
                    $(this).after('<label generated="true" class="error value_tip" id="expand_value_tip">'+message+'</label>');
                } else {
                    $(this).removeClass("error");
                    $(this).next().remove();
                }
            }
        });
    });
}
