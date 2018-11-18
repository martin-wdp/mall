$(function(){
    /* 为选定的select下拉菜单开启搜索提示 */
    $('select[data-live-search="true"]').select2();
    /* 为选定的select下拉菜单开启搜索提示 END */

    $("#addDeliveryPointForm").validate();
    $("#editDeliveryPointForm").validate();
});

/**
 * 修改上门自提启用状态
 * @param id
 */
function changeUserdStatus(id){
    var CSRFToken = $("#CSRFToken").val();
    location = "changeDelDeliveryPointUserdStatus.htm?CSRFToken="+$("#CSRFToken").val()+"&deliveryPointId="+id;
}

/**
 * 弹框显示添加上门自提设置
 */
function showAddPoint() {
    queryAllProvince();
    $("#cities").html("<option value=''>选择城市</option>");
    $("#districts").html("<option value=''>选择区县</option>");
    $('select[data-live-search="true"]').select2();
    $('select[data-live-search="true"]').select2();
    $('#addPickPoint').modal('show');
}

/*查询所有的省份*/
function queryAllProvince(){
    $.get("queryAllProvince.htm?CSRFToken="+$("#CSRFToken").val(),function(data){
        var provinces="<option value=''>选择省份</option>";
        //$("#cities_county").html(provinces);
        for(var i=0;i<data.length;i++){
            provinces+="<option value='"+data[i].provinceId+"'>"+data[i].provinceName+"</option>";
        }
        $("#provinces").html(provinces);
        $('select[data-live-search="true"]').select2();
    });
}


/*根据省份ID查询城市ID*/
function queryCityByProvinceId(province){
    //设置省份名称
    $("#temp1").val($(province).find("option:selected").text());
    $("#temp4").val($(province).find("option:selected").val());

    $.get("queryCityByProvinceId.htm?CSRFToken="+$("#CSRFToken").val()+"&provinceId="+($(province).val()),function(data){
        var provinces="<option value=''>选择城市</option>";
        //$("#districts").html(provinces);
        for(var i=0;i<data.length;i++){
            provinces+="<option value='"+data[i].cityId+"'>"+data[i].cityName+"</option>";
        }
        $("#cities").html(provinces);
        $('select[data-live-search="true"]').select2();
    });
}

/*根据城市ID查询区县ID*/
function queryDistrictByCityId(city){
    //设置城市名称
    $("#temp2").val($(city).find("option:selected").text());
    //城市id
    $("#temp5").val($(city).find("option:selected").val());


    $.get("queryDistrictByCityId.htm?CSRFToken="+$("#CSRFToken").val()+"&cityId="+($(city).val()),function(data){
        var provinces="<option value=''>选择区县</option>";
        for(var i=0;i<data.length;i++){

            provinces+="<option value='"+data[i].districtId+"'>"+data[i].districtName+"</option>";
        }

        $("#districts").html(provinces);
        $('select[data-live-search="true"]').select2();
    });
}

/*编辑时查询所有的省份*/
function editQueryAllProvince(obj){
    if(obj==-1){
        provinceId=$("#editTemp4").val()
    }else{
        provinceId=$(obj).val();
    }
    $.get("queryAllProvince.htm?CSRFToken="+$("#CSRFToken").val(),function(data){
        var provinces="<option value=''>选择省份</option>";
        //$("#cities_county").html(provinces);

        for(var i=0;i<data.length;i++){
            if(provinceId==data[i].provinceId){
                provinces+="<option selected value='"+data[i].provinceId+"' >"+data[i].provinceName+"</option>";
                editQueryCityByProvinceId();
            }
            else{
                provinces+="<option value='"+data[i].provinceId+"' >"+data[i].provinceName+"</option>";
            }
        }
        $("#province").html(provinces);
        $('select[data-live-search="true"]').select2();
    });
}

/*编辑时根据省份ID查询城市ID*/
function editQueryCityByProvinceId(obj){
   var  provinceId=$(obj).val();
    var flag=0;
    if(isNaN(provinceId)){
       flag=1;
        provinceId=$("#editTemp4").val();
    }
        var cityId=$("#editTemp5").val();
    $.get("queryCityByProvinceId.htm?CSRFToken="+$("#CSRFToken").val()+"&provinceId="+provinceId,function(data){
        var provinces="";
        //$("#districts").html(provinces);
        for(var i=0;i<data.length;i++){
            if(cityId==data[i].cityId){
                provinces+="<option selected value='"+data[i].cityId+"'>"+data[i].cityName+"</option>";
                editQueryDistrictByCityId(obj);
            }else{
                provinces+="<option value='"+data[i].cityId+"'>"+data[i].cityName+"</option>";
            }
        }
        $("#city").html(provinces);
        $('select[data-live-search="true"]').select2();

        //城市名称
        $("#editTemp2").val($("#city").find("option:selected").text());

        //城市id
        $("#editTemp5").val($("#city").find("option:selected").val());
        //省份名称
        $("#editTemp1").val($("#province").find("option:selected").text());
        //省份id
        $("#editTemp4").val($("#province").find("option:selected").val());
        //强制掉onchange 事件
        if(flag==0) {
            $("#city").change();
        }
    });
}

/*编辑时根据城市ID查询区县ID*/
function editQueryDistrictByCityId(obj){
    var  cityId=$(obj).val();
    var flag=0;
    if(isNaN(cityId)){
       cityId=$("#editTemp5").val();
        var districtId=$("#districtId").val();
       flag=1;
    }
    if(!isNaN(cityId)&& "undefined" != typeof cityId && cityId !=null) {
    $.get("queryDistrictByCityId.htm?CSRFToken="+$("#CSRFToken").val()+"&cityId="+cityId,function(data){
        var provinces="";
        for(var i=0;i<data.length;i++){
            if(districtId==data[i].districtId){

                provinces+="<option selected value='"+data[i].districtId+"'>"+data[i].districtName+"</option>";

            }else{
                provinces+="<option value='"+data[i].districtId+"'>"+data[i].districtName+"</option>";
            }
        }
        $("#district").html(provinces);
        $('select[data-live-search="true"]').select2();
        //设置区县名称
        $("#editTemp3").val($("#district").find("option:selected").text());
        $("#districtId").val($("#district").find("option:selected").val());
        //城市名称
        $("#editTemp2").val($("#city").find("option:selected").text());
        //城市id
        $("#editTemp5").val($("#city").find("option:selected").val());
       if(flag=0){
           $("#district").change(obj);
       }
    });
    }
}

/**
 * 选择地区时，设置地区名称
 * @param district
 */
function selectDistrict(district) {
    //设置县区名称-添加用
    $("#temp3").val($(district).find("option:selected").text());
    //编辑用
    $("#editTemp3").val($(district).find("option:selected").text());
    $("#districtId").val($(district).find("option:selected").val());
}

/**
 * 提交添加上门自提form
 */
var num=0;
function submitPointForm() {
    if( $("#addDeliveryPointForm").valid()&&num==0){
        if($("#districts").val()==''||$("#districts").val()==null){
            $(".districts_valid").addClass("error");
            $(".districts_valid").html("不能为空");
            $(".districts_valid").attr("style","");
        }else{
            $(".districts_valid").removeClass("error");
            $(".districts_valid").html("");
            num+=1;
            $("#addDeliveryPointForm").submit();
        }

    }

}

/**
 * 弹框显示物流公司编辑框
 * @param deliveryPointId 自提点id
 */
function showEditDeliveryPointForm(deliveryPointId) {
    $("#deliveryPointId").val(deliveryPointId);
    var CSRFToken = $("#CSRFToken").val();
    $.ajax({
        url:'selectDeliveryPointById.htm?CSRFToken='+CSRFToken+'&deliveryPointId='+deliveryPointId,
        success:function(data1) {
            $("#name").val(data1.name);
            $("#linkman").val(data1.linkman);
            $("#telephone").val(data1.telephone);
            $("#address").val(data1.address);
            $("#province").val(data1.temp1);
            $("#city").val(data1.temp2);
            $("#districtId").val(data1.districtId);
            if(data1.isUserd==0) {
                $("#open6").click();
            } else {
                $("#open5").click();
            }
            $("#editTemp3").val(data1.temp3);
            $("#editTemp4").val(data1.temp4);
            $("#editTemp5").val(data1.temp5);
            $("#deliveryPointId").val(data1.deliveryPointId);
            editQueryAllProvince(-1);
        }
    });
    $('#editDeliveryPoint').modal('show');

}
/**
 * 修改自提点信息
 */
function submitEditDeliveryPointForm() {
        $("#editDeliveryPointForm").submit();
}