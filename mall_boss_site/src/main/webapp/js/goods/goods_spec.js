$(function(){

    $("#saveSpecForm").validate();
    $("#updateSpecDetailForm").validate();
    /* 为选定的select下拉菜单开启搜索提示 */
    $('select[data-live-search="true"]').select2();
    /* 为选定的select下拉菜单开启搜索提示 END */

    /* 下面是表单里面的填写项提示相关的 */
    $('.guigemingchen').popover({
        content : '展示商品详情页规格名称(为了前台样式对齐比如:颜色;版本;两个字组合为标准)',
        trigger : 'hover'
    });
    $('.guigebeizhu').popover({
        content : '在后台添加商品类型时使用',
        trigger : 'hover'
    });

    /* 双击编辑分类 */
    $('.cate_item').dblclick(function(){
        $('#cateEdit').modal('show');
    });

});

function showEditSpec(specId) {
    /*ajax 通过ID查询并塞值到修改页面*/
    $.get("queryCateVoById.htm?CSRFToken="+$("#CSRFToken").val()+"&specId=" + specId, function (data)
    {
        $("#update_specId").val(data.specId);
        $("#update_spec_name").val(data.specName);
        $("#update_remark").val(data.specRemark);
        $("#update_nickname").val(data.specNickname);
        $("#specShowmode"+data.specShowmode).click();
    });
    $("#editSpecification").modal("show");
}


function querySpecDetail(specId) {

    $("#specId").val(specId);
    /*ajax 通过ID查询并塞值到修改页面*/
    $.get("queryCateVoById.htm?CSRFToken="+$("#CSRFToken").val()+"&specId=" + specId, function (data) {
        /*设置规格值*/
        var html = "";
        for (var i = 0; i < data.specDetails.length; i++) {
            html+=
                '<tr id="spec_detail_'+data.specDetails[i].specDetailId+'">'+
                '   <input type="hidden" name="detail_specId" class="update_specId" value="' + data.specDetails[i].specId + '"/>'+
                '   <input type="hidden" name="specDetailId" value="' + data.specDetails[i].specDetailId + '"/>'+
                '   <input type="hidden" name="specDetailDelflag" class="update_specDetailDelFlag" value="' + data.specDetails[i].specDetailDelflag + '"/>'+
                '   <td><input type="checkbox" name="specDetail" value="_'+data.specDetails[i].specDetailId+'"></td>'+
                '   <td><input type="text" class="form-control required specstr" name="specDetailName" value="'+ data.specDetails[i].specDetailName + '"></td>'+
                '   <td><input type="text" class="form-control required number" name="specDetailSort" value="' + data.specDetails[i].specDetailSort + '"></td>'+
                '   <td><button type="button" class="btn btn-default" onclick="deleteOneSpecDetail(\'_'+data.specDetails[i].specDetailId+'\')">删除</button></td>'+
                '</tr>';
        }
        $("#spec_detail_table tbody").html(html);
        $('#manageSpecification').modal('show')
    });
}
var detailIndex = 0;
function addOneSpecDetail() {
    detailIndex ++;
    var specId = $("#specId").val();
   var  html =
        '<tr id="spec_detail'+detailIndex+'">'+
        '   <input type="hidden" name="detail_specId" class="update_specId" value="' +specId + '"/>'+
        '   <input type="hidden" name="specDetailId" value="-1"/>'+
        '   <input type="hidden" name="specDetailDelflag" class="update_specDetailDelFlag" value="0"/>'+
        '   <td><input type="checkbox" name="specDetail" value="'+detailIndex+'"></td>'+
        '   <td><input type="text" class="form-control required specstr" name="specDetailName"></td>'+
        '   <td><input type="text" class="form-control number" name="specDetailSort" value="0"></td>'+
        '   <td><button type="button" class="btn btn-default" onclick="deleteOneSpecDetail('+detailIndex+')">删除</button></td>'+
        '</tr>';
    $("#spec_detail_table tbody").append(html);
}


function deleteOneSpecDetail(index) {
    if($("#spec_detail"+index).find("input[name=specDetailId]").val()=='-1') {
        $("#spec_detail"+index).remove();
    } else {
        $("#spec_detail"+index).hide();
        $("#spec_detail"+index).find("input[name=specDetailDelflag]").val(1);
    }
}

function batchDeleteSpecDetail() {
    $("input[name=specDetail]").each(function () {
        if($("#spec_detail"+$(this).val()).find("input[name=specDetailId]").val()=='-1') {
            $("#spec_detail"+$(this).val()).remove();
        } else {
            $("#spec_detail"+$(this).val()).hide();
            $("#spec_detail"+$(this).val()).find("input[name=specDetailDelflag]").val(1);
        }
    });
}