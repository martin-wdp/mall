$(function () {
    loadProvince();
    $('select[data-live-search="true"]').select2();
    $("#saveProvinceForm").validate();
    $("#saveCityForm").validate();
    $("#saveDistrictForm").validate();
    $("#saveStreetForm").validate();
    $("#updateAddressForm").validate();
})
/**
 * 加载一级分类
 * @param parentId 父类id
 */
function loadProvince(provinceName) {
    var url = 'queryAllProvince.htm?CSRFToken='+$("#CSRFToken").val();
    if(provinceName!=''&&provinceName!=undefined) {
        url += '&provinceName='+provinceName;
    }
    $("#province_list").html('');
    $.ajax({
        url:url,
        success: function (data) {
            var html = '';
            html += '<a class="add_cart_btn" href="javascript:;" onclick="showAddProvince()"><i class="glyphicon glyphicon-plus"></i> 添加省份</a>';
            for(var i=0;i<data.length;i++) {
                var province = data[i];
                html += '<div class="cate_item" id="province_item'+province.provinceId+'" onclick="loadCity(this,'+province.provinceId+')">'+
                '<h4>'+province.provinceName+'</h4>'+
                '<div class="c_btns">'+
                '<a href="javascript:;"><span class="glyphicon glyphicon-edit" onclick="showEditProvince('+province.provinceId+')"></span></a>'+
                '<a href="javascript:;" onclick="delAddress('+province.provinceId+',1)"><span class="glyphicon glyphicon-trash"</span></a>'+
                '</div>'+
                '</div>';
            }
            $("#province_list").html(html);
            $("#province_list").find("div.cate_item")[0].click();
        }
    });
}

/**
 * 加载城市
 * @param obj 按钮对象
 * @param provinceId 省份idid
 */
function loadCity(obj,provinceId,cityName) {
    clickItem(obj);
    $("#provinceId").val(provinceId);
    $("#city_list").html('');
    var url = 'queryCityByProvinceId.htm?CSRFToken='+$("#CSRFToken").val()+'&provinceId='+provinceId;
    if(cityName!=''&& cityName!=undefined) {
        url += '&cityName='+cityName;
    }
    $.ajax({
        url:url,
        success: function (data) {
            var html = '';
            html += '<a class="add_cart_btn" href="javascript:;" onclick="showAddCity()"><i class="glyphicon glyphicon-plus"></i> 添加城市</a>';
            for(var i=0;i<data.length;i++) {
                var city = data[i];
                html += '<div class="cate_item" id="city_item'+city.cityId+'" onclick="loadDistrict(this,'+city.cityId+')">'+
                '<h4>'+city.cityName+'</h4>'+
                '<div class="c_btns">'+
                '<a href="javascript:;"><span class="glyphicon glyphicon-edit" onclick="showEditCity('+city.cityId+')"></span></a>'+
                '<a href="javascript:;" onclick="delAddress('+city.cityId+',2)"><span class="glyphicon glyphicon-trash"</span></a>'+
                '</div>'+
                '</div>';
            }
            $("#city_list").html(html);
            if($("#city_list").find("div.cate_item").length>0) {
                $("#city_list").find("div.cate_item")[0].click();
                $("#modifyCityBtn").attr("disabled",false);
            } else {
                $("#district_list").html("");
                $("#street_list").html("");
                $("#modifyCityBtn").attr("disabled",true);
                $("#modifyDistrictBtn").attr("disabled",true);
                $("#modifyStreetBtn").attr("disabled",true);
            }

        }
    });
}
/**
 * 加载三级分类
 * @param obj 按钮对象
 * @param cityId 父类id
 */
function loadDistrict(obj,cityId,districtName) {
    clickItem(obj);
    $("#cityId").val(cityId);
    $("#district_list").html('');
    var url = 'queryDistrictByCityId.htm?CSRFToken='+$("#CSRFToken").val()+'&cityId='+cityId;
    if(districtName!=''&& districtName!=undefined) {
        url += '&districtName='+districtName;
    }
    $.ajax({
        url:url,
        success: function (data) {
            var html = '';
            html += '<a class="add_cart_btn" href="javascript:;" onclick="showAddCounty()"><i class="glyphicon glyphicon-plus"></i> 添加区县</a>';
            for(var i=0;i<data.length;i++) {
                var district = data[i];
                html += '<div class="cate_item" id="district_item'+district.districtId+'" onclick="loadStreet(this,'+district.districtId+')">'+
                '<h4>'+district.districtName+'</h4>'+
                '<div class="c_btns">'+
                '<a href="javascript:;"><span class="glyphicon glyphicon-edit" onclick="showEditDistrict('+district.districtId+')"></span></a>'+
                '<a href="javascript:;" onclick="delAddress('+district.districtId+',3)"><span class="glyphicon glyphicon-trash"</span></a>'+
                '</div>'+
                '</div>';
            }
            $("#district_list").html(html);
            if($("#district_list").find("div.cate_item").length>0) {
                $("#district_list").find("div.cate_item")[0].click();
                $("#modifyDistrictBtn").attr("disabled",false);
            } else {
                $("#street_list").html("");
                $("#modifyDistrictBtn").attr("disabled",true);
                $("#modifyStreetBtn").attr("disabled",true);
            }
        }
    });
}

/**
 * 加载街道
 * @param obj 按钮对象
 * @param cityId 父类id
 */
function loadStreet(obj,districtId,streetName) {
    clickItem(obj);
    $("#districtId").val(districtId);
    $("#street_list").html('');
    var url = 'queryStreetByDistrictId.htm?CSRFToken='+$("#CSRFToken").val()+'&districtId='+districtId;
    if(streetName!=''&& streetName!=undefined) {
        url += '&streetName='+streetName;
    }
    $.ajax({
        url:url,
        success: function (data) {
            var html = '';
            html += '<a class="add_cart_btn" href="javascript:;" onclick="showAddStreet()"><i class="glyphicon glyphicon-plus"></i> 添加街道</a>';
            for(var i=0;i<data.length;i++) {
                var street = data[i];
                html += '<div class="cate_item" id="cate_item'+street.streetId+'" onclick="clickStreet(this,'+street.streetId+')">'+
                '<h4>'+street.streetName+'</h4>'+
                '<div class="c_btns">'+
                '<a href="javascript:;"><span class="glyphicon glyphicon-edit" onclick="showEditStreet('+street.streetId+')"></span></a>'+
                '<a href="javascript:;" onclick="delAddress('+street.streetId+',4)"><span class="glyphicon glyphicon-trash"</span></a>'+
                '</div>'+
                '</div>';
            }
            $("#street_list").html(html);
            if($("#street_list").find("div.cate_item").length>0) {
                $("#street_list").find("div.cate_item")[0].click();
                $("#modifyStreetBtn").attr("disabled",false);
            } else {
                $("#modifyStreetBtn").attr("disabled",true);
            }
        }
    });
}

function clickStreet(obj,streetId) {
    $("#streetId").val(streetId);
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
 * 搜索省份，并按照输入的省份名称加载信息
 */
function findProvince() {
    var provinceName = $("#provinceName").val();
    loadProvince(provinceName);
}

/**
 * 搜索城市，并按照输入的城市名称加载信息
 */
function findCity() {
    var cityName = $("#cityName").val();
    var provinceId = $("#provinceId").val();
    loadCity(null,provinceId,cityName);
}

/**
 * 搜索区县，并按照输入的区县名称加载信息
 */
function findDistrict() {
    var districtName = $("#districtName").val();
    var cityId = $("#cityId").val();
    loadDistrict(null,cityId,districtName);
}

/**
 * 搜索街道，并按照输入的街道名称加载信息
 */
function findStreet() {
    var streetName = $("#streetName").val();
    var districtId = $("#districtId").val();
    loadStreet(null,districtId,streetName);
}

/**
 * 弹框显示省份添加框
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
        var curProvinceId = $("#provinceId").val();
        $("#"+selectId+" option[value="+curProvinceId+"]").attr("selected",true);
        $('select[data-live-search="true"]').select2();
        if(selectId=='provinces_county') {
            queryCityByProvinceId($("#"+selectId),'cities_county');
        } else {
            queryCityByProvinceId($("#"+selectId),'cities_street');
        }

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
        var curCityId = $("#cityId").val();
        $("#"+selectId+" option[value="+curCityId+"]").attr("selected",true);
        $('select[data-live-search="true"]').select2();
        queryDistrictByCityId($("#"+selectId));
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
        var curDistrictId = $("#districtId").val();
        $("#districts option[value="+curDistrictId+"]").attr("selected",true);
        $('select[data-live-search="true"]').select2();
    });
}

/**
 * 提交地区添加表单
 * @param formId 表单id
 */
function submitAreaAddForm(formId,btnObj) {

    if(!$("#"+formId).valid()) return;
    var errors = $("#"+formId).find(".error");
    if(errors!=null&&errors.size()>0){
        for(var i=0;i<errors.size();i++){
            if($(errors[i]).attr("style")!="display: none;") return;
        }
    }
    var url = $("#"+formId).attr("action");
    var data = $("#"+formId).serialize();
    $(btnObj).attr("disabled",true);
    $.ajax({
        url:url,
        type:'POST',
        data:data,
        success:function(result) {
            if(formId=='saveProvinceForm') {
                loadProvince();
                $('#addProvince').modal('hide');
            } else if(formId=='saveCityForm') {
                var curProvinceId = $("#provinceId").val();
                $("#province_item"+curProvinceId).click();
                $('#addCity').modal('hide');
            } else if(formId=='saveDistrictForm') {
                var curCityId = $("#cityId").val();
                $("#city_item"+curCityId).click();
                $('#addCounty').modal('hide');
            } else if(formId=='saveStreetForm') {
                var curDistrict = $("#districtId").val();
                $("#district_item"+curDistrict).click();
                $('#addStreet').modal('hide');
            } else if(formId=='updateAddressForm') {
                $('#editAddressModal').modal('hide');
                var parentLocation = $("#parentLocation").val();
                if(parentLocation=='') {
                    $(btnObj).attr("disabled",false);
                    loadProvince();
                    return;
                }
                $("#"+parentLocation).click();
            }
            $(btnObj).attr("disabled",false);
        }
    });
}

/**
 * 弹框显示编辑省份信息
 * @param addressId
 */
function showEditProvince(addressId) {
    $("#updateAddressId").val(addressId);
    $("#updateAddressForm").attr("action","updateProvince.htm");
    $("#parentAddress").text("全国");
    $("#parentLocation").val('');
    $.get("searchProvinceById.htm?CSRFToken="+$("#CSRFToken").val()+"&provinceId="+addressId,function(data){
        $("#update_address_name").val(data.provinceName);
        $("#update_addressSort").val(data.provinceSort);
    });
    $("#editAddressModal" ).modal( "show" );
}

/**
 * 弹框显示编辑城市信息
 * @param addressId
 */
function showEditCity(addressId) {
    $("#updateAddressId").val(addressId);
    $("#updateAddressForm").attr("action","updateCity.htm");
    $("#parentLocation").val('province_item'+$("#provinceId").val());
    $.get("queryAddressUtilByCityId.htm?CSRFToken="+$("#CSRFToken").val()+"&cityId="+addressId,function(data){
        $("#parentAddress").text(data.provinceName);
        $("#update_address_name").val(data.cityName);
        $("#update_addressSort").val(data.sort);
    });
    $("#editAddressModal" ).modal( "show" );
}

/**
 * 弹框显示编辑区县信息
 * @param addressId
 */
function showEditDistrict(addressId) {
    $("#updateAddressId").val(addressId);
    $("#updateAddressForm").attr("action","updateDistrict.htm");
    $("#parentLocation").val('city_item'+$("#cityId").val());
    $.get("queryAddressNameByDistrictId.htm?CSRFToken="+$("#CSRFToken").val()+"&districtId="+addressId,function(data){
        $("#parentAddress").text(data.provinceName+"-"+data.cityName);
        $("#update_address_name").val(data.districtName);
        $("#update_addressSort").val(data.sort);
    });
    $("#editAddressModal" ).modal( "show" );
}

/**
 * 弹框显示编辑街道信息
 * @param addressId
 */
function showEditStreet(addressId) {
    $("#updateAddressId").val(addressId);
    $("#updateAddressForm").attr("action","updateStreet.htm");
    $("#parentLocation").val('district_item'+$("#districtId").val());
    $.get("queryAddressNameByStreetId.htm?CSRFToken="+$("#CSRFToken").val()+"&streetId="+addressId,function(data){
        $("#parentAddress").text(data.provinceName+"-"+data.cityName+"-"+data.districtName);
        $("#update_address_name").val(data.streetName);
        $("#update_addressSort").val(data.sort);
    });
    $("#editAddressModal" ).modal( "show" );
}

/*点击删除按钮*/
function delAddress(addressId,flag) {
    $("#deleteLocation").val(flag);
    if(flag==1){
        $.get("queryCityByProvinceId.htm?CSRFToken="+$("#CSRFToken").val()+"&provinceId="+addressId,function(data){
            if(data.length>0){
                showTipAlert("该省份下存在城市信息，不允许删除!");
            }else{
                showOnlyDeleteConfirmAlert("delProvince.htm?CSRFToken="+$("#CSRFToken").val()+"&provinceId=" + addressId);
            }
        });
    }else if(flag==2){
        $.get("queryDistrictByCityId.htm?CSRFToken="+$("#CSRFToken").val()+"&cityId="+addressId,function(data){
            if(data.length>0){
                showTipAlert("该城市下存在区县信息，不允许删除!");
            }else{
                showOnlyDeleteConfirmAlert("delCity.htm?CSRFToken="+$("#CSRFToken").val()+"&cityId=" + addressId);
            }
        });
    }else if(flag==3){
        $.get("queryStreetByDistrictId.htm?CSRFToken="+$("#CSRFToken").val()+"&districtId="+addressId,function(data){
            if(data.length>0){
                showTipAlert("该区县下存在街道信息，不允许删除!");
            }else{
                showOnlyDeleteConfirmAlert("delDistrict.htm?CSRFToken="+$("#CSRFToken").val()+"&districtId=" + addressId);
            }
        });
    }else if(flag==4){
        showOnlyDeleteConfirmAlert("delStreet.htm?CSRFToken="+$("#CSRFToken").val()+"&streetId=" + addressId);
    }
}

function delteCallback() {
    var deleteLocation = $("#deleteLocation").val();
    if(deleteLocation==1){
        loadProvince();
    }else if(deleteLocation==2){
        var curProvinceId = $("#provinceId").val();
        $("#province_item"+curProvinceId).click();
    }else if(deleteLocation==3){
        var curCityId = $("#cityId").val();
        $("#city_item"+curCityId).click();
    }else if(deleteLocation==4){
        var curDistrict = $("#districtId").val();
        $("#district_item"+curDistrict).click();
    }
}