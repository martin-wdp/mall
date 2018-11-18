$(function(){
    /* 为选定的select下拉菜单开启搜索提示 */
    $('select[data-live-search="true"]').select2();
    /* 为选定的select下拉菜单开启搜索提示 END */

    /* 下面是另一种带折叠的表格，没有分页 */
    // 这里要说明一下
    // 比如现在有5行，其中第二行和第一行平级，第三行是第二行的下级行，第四行是第三行的下级
    // 第五行和第三行平级，也就是说是第二行的下级行，应该如下所示：
    //      1
    //      2
    //         3
    //            4
    //         5
    // 这样的话，map应该是这样的
    // 行号：1					 2				 3                 4				 5
    // map： 0    ,              0,              2,                3,                2
    // 说明：1行的上级的行为0    2行的上级也为0  3行的上级是第2行  4行的上级是第3行  第5行的上级是第2行
    //
    var map = [];
    $(".tr").each(function(){
        map.push($(this).attr("parent_index"));
    });

    $("#treetable").jqTreeTable(map, {
        openImg: "images/TreeTable/tv-collapsable.gif",
        shutImg: "images/TreeTable/tv-expandable.gif",
        leafImg: "images/TreeTable/tv-item.gif",
        lastOpenImg: "images/TreeTable/tv-collapsable-last.gif",
        lastShutImg: "images/TreeTable/tv-expandable-last.gif",
        lastLeafImg: "images/TreeTable/tv-item-last.gif",
        vertLineImg: "images/TreeTable/vertline.gif",
        blankImg: "images/TreeTable/blank.gif",
        collapse: false,
        column: 2,
        striped: true,
        highlight: true,
        state:false
    });
    /* 下面是另一种带折叠的表格，没有分页 END */

    $("#saveProvinceForm").validate();
    $("#saveCityForm").validate();
    $("#saveDistrictForm").validate();
    $("#saveStreetForm").validate();
    $("#updateAddressForm").validate();
});

/**
 * t弹框显示省份添加框
 */
function showAddProvince() {
    $('#addProvince').modal('show');
}
/**
 * 弹框显示城市添加框
 */
function showAddCity() {
    queryAllProvince("provinces_city");
    $('#addCity').modal('show');
}
/**
 * 弹框显示县区添加框
 */
function showAddCounty() {
    queryAllProvince("provinces_county");
    $("#cities_county").html("<option value=''>--请选择--</option>");
    $('select[data-live-search="true"]').select2();
    $('#addCounty').modal('show');
}
/**
 * 弹框显示街道添加框
 */
function showAddStreet() {
    queryAllProvince("provinces_street");
    $("#cities_street").html("<option value=''>--请选择--</option>");
    $("#districts").html("<option value=''>--请选择--</option>");
    $('select[data-live-search="true"]').select2();
    $('#addStreet').modal('show');
}
/*查询所有的省份*/
function queryAllProvince(selectId){
    $.get("queryAllProvince.htm?CSRFToken="+$("#CSRFToken").val(),function(data){
        var provinces="<option value=''>--请选择--</option>";
        //$("#cities_county").html(provinces);
        for(var i=0;i<data.length;i++){
            provinces+="<option value='"+data[i].provinceId+"'>"+data[i].provinceName+"</option>";
        }
        $("#"+selectId).html(provinces);
        $('select[data-live-search="true"]').select2();
    });
}

/*根据省份ID查询城市ID*/
function queryCityByProvinceId(province,selectId){
    $.get("queryCityByProvinceId.htm?CSRFToken="+$("#CSRFToken").val()+"&provinceId="+($(province).val()),function(data){
        var provinces="<option value=''>--请选择--</option>";
        //$("#districts").html(provinces);
        for(var i=0;i<data.length;i++){
            provinces+="<option value='"+data[i].cityId+"'>"+data[i].cityName+"</option>";
        }
        $("#"+selectId).html(provinces);
        $('select[data-live-search="true"]').select2();
    });
}
/*根据城市ID查询区县ID*/
function queryDistrictByCityId(city){
    $.get("queryDistrictByCityId.htm?CSRFToken="+$("#CSRFToken").val()+"&cityId="+($(city).val()),function(data){
        var provinces="<option value=''>--请选择--</option>";
        for(var i=0;i<data.length;i++){
            provinces+="<option value='"+data[i].districtId+"'>"+data[i].districtName+"</option>";
        }
        $("#districts").html(provinces);
        $('select[data-live-search="true"]').select2();
    });
}

/**
 * 提交地区添加表单
 * @param formId 表单id
 */
function submitAreaAddForm(formId) {
    $("#"+formId).submit();
}


/*点击删除按钮*/
function delAddress(addressId,flag) {
    if(flag==1){
        $.get("queryCityByProvinceId.htm?CSRFToken="+$("#CSRFToken").val()+"&provinceId="+addressId,function(data){
            if(data.length>0){
                showTipAlert("该省份下存在城市信息，不允许删除!");
            }else{
                showAjaxDeleteConfirmAlert("delProvince.htm?CSRFToken="+$("#CSRFToken").val()+"&provinceId=" + addressId);
            }
        });
    }else if(flag==2){
        $.get("queryDistrictByCityId.htm?CSRFToken="+$("#CSRFToken").val()+"&cityId="+addressId,function(data){
            if(data.length>0){
                showTipAlert("该城市下存在区县信息，不允许删除!");
            }else{
                showAjaxDeleteConfirmAlert("delCity.htm?CSRFToken="+$("#CSRFToken").val()+"&cityId=" + addressId);
            }
        });
    }else if(flag==3){
        $.get("queryStreetByDistrictId.htm?CSRFToken="+$("#CSRFToken").val()+"&districtId="+addressId,function(data){
            if(data.length>0){
                showTipAlert("该区县下存在街道信息，不允许删除!");
            }else{
                showAjaxDeleteConfirmAlert("delDistrict.htm?CSRFToken="+$("#CSRFToken").val()+"&districtId=" + addressId);
            }
        });
    }else if(flag==4){
        showAjaxDeleteConfirmAlert("delStreet.htm?CSRFToken="+$("#CSRFToken").val()+"&streetId=" + addressId);
    }
}


/*修改地区*/
function updateAddress(addressId, flag) {
    $("#updateAddressId").val(addressId);
    if(flag==1){
        $("#updateAddressForm").attr("action","updateProvince.htm");
        $("#parentAddress").text("全国");
        $.get("searchProvinceById.htm?CSRFToken="+$("#CSRFToken").val()+"&provinceId="+addressId,function(data){
            $("#update_address_name").val(data.provinceName);
            $("#update_addressSort").val(data.provinceSort);
        });
    }else if(flag==2){
        $("#updateAddressForm").attr("action","updateCity.htm");
        $.get("queryAddressUtilByCityId.htm?CSRFToken="+$("#CSRFToken").val()+"&cityId="+addressId,function(data){
            $("#parentAddress").text(data.provinceName);
            $("#update_address_name").val(data.cityName);
            $("#update_addressSort").val(data.sort);
        });
    }else if(flag==3){
        $("#updateAddressForm").attr("action","updateDistrict.htm");
        $.get("queryAddressNameByDistrictId.htm?CSRFToken="+$("#CSRFToken").val()+"&districtId="+addressId,function(data){
            $("#parentAddress").text(data.provinceName+"-"+data.cityName);
            $("#update_address_name").val(data.districtName);
            $("#update_addressSort").val(data.sort);
        });
    }else if(flag==4){
        $("#updateAddressForm").attr("action","updateStreet.htm");
        $.get("queryAddressNameByStreetId.htm?CSRFToken="+$("#CSRFToken").val()+"&streetId="+addressId,function(data){
            $("#parentAddress").text(data.provinceName+"-"+data.cityName+"-"+data.districtName);
            $("#update_address_name").val(data.streetName);
            $("#update_addressSort").val(data.sort);
        });
    }
    $("#editAddressModal" ).modal( "show" );
}

function batchDeleteArea() {
    var checkboxs = $("input[name=areaId]");
    var selectCount = false;
    var areaType,areaId;
    for(var j = 0; j < checkboxs.length; j++) {
        if($(checkboxs[j]).is(':checked')==true) {
            areaType = $(checkboxs[j]).attr("area_type");
            areaId = $(checkboxs[j]).val();
            selectCount ++;
        }
    }
    if(selectCount<1) {
        showTipAlert("请选择一条记录！");
        return;
    }
    if(selectCount>1) {
        showTipAlert("最多只能选择一条记录删除！")
        return;
    }

    if(areaType=='1') {
        showDeleteOneConfirmAlert('deleteProvinceAndChilds.htm?CSRFToken='+$("#CSRFToken").val()+'&provinceId='+areaId,'此操作会删除该省份及下属城市、县区、街道，确认操作吗？');
    }
    if(areaType=='2') {
        showDeleteOneConfirmAlert('deleteCityAndChilds.htm?CSRFToken='+$("#CSRFToken").val()+'&cityId='+areaId,'此操作会删除该城市及下属县区、街道，确认操作吗？');
    }
    if(areaType=='3') {
        showDeleteOneConfirmAlert('deleteDistrictAndChilds.htm?CSRFToken='+$("#CSRFToken").val()+'&districtId='+areaId,'此操作会删除该县区及下属街道，确认操作吗？');
    }
    if(areaType=='4') {
        showDeleteOneConfirmAlert('delStreet.htm?CSRFToken='+$("#CSRFToken").val()+'&streetId='+areaId,'此操作会删除该省份及下属地区，确认操作吗？');
    }


}