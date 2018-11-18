$(function(){
    /* 表单项的值点击后转换为可编辑状态 */
    $('.form_value').click(function(){
        var formItem = $(this);
        if(!$('.form_btns').is(':visible')) {
            formItem.parent().addClass('form_open');
            $('.form_btns').show();
            $('.form_btns').css({
                'left': formItem.next().offset().left + 70 + 'px',
                'top': formItem.next().offset().top - 30 + 'px'
            });
            $('.form_sure,.form_cancel').click(function () {
                $('.form_btns').hide();
                formItem.parent().removeClass('form_open');
            });
        }
    });

    /* 为选定的select下拉菜单开启搜索提示 */
    $('select[data-live-search="true"]').select2();
    /* 为选定的select下拉菜单开启搜索提示 END */

    /* 富文本编辑框 */
    $('.summernote').summernote({
        height: 300,
        tabsize: 2,
        lang: 'zh-CN'
    });

    /* 下面是表单里面的填写项提示相关的 */
    $('.zhuantiseokw').popover({
        content : '默认为文章名称(最大字数75)',
        trigger : 'hover'
    });
    $('.zhuantiseodesc').popover({
        content : '默认为文章名称(最大字数255)',
        trigger : 'hover'
    });

    /* 高级搜索 */
    $('.advanced_search').popover({
        html : true,
        title : '高级搜索',
        content : $('.advanced_search_cont').html(),
        trigger : 'click',
        placement : 'bottom'
    }).click(function(){
    	 $('select[data-live-search="true"]').select2();
    });

    /* 下面是表单里面的日期时间选择相关的 */
    $('.form_datetime').datetimepicker({
        format: 'yyyy-mm-dd hh:ii:00:00',
        weekStart : 1,
        autoclose : true,
        language : 'zh-CN',
        pickerPosition : 'bottom-left',
        todayBtn : true,
        viewSelect : 'hour'
    });
    $('.form_date').datetimepicker({
        format : 'yyyy-mm-dd',
        weekStart : 1,
        autoclose : true,
        language : 'zh-CN',
        pickerPosition : 'bottom-left',
        minView : 2,
        todayBtn : true
    });
    /* 下面是表单里面的日期时间选择相关的 END */

    $('#searchButton').button().click(function() {
        $("#pageSize").val(5);
        $("#pageNo").val(1);
        $('#search_from').submit();
    });
});

/**
 * 点击改变统计代码启用状态
 * @param staCodeId
 */
function changeUserdStatus(staCodeId){
    location.href = "changeUserdStatusForScode.htm?CSRFToken="+$("#CSRFToken").val()+"&scodeId="+staCodeId;
}

$(document).ready(function(){
    //验证提示层数组（即保存验证提示信息的节点元素，这个添加的顺序和待验证元素数组一致）
    var allFields = $([]),
    //待验证元素数组（即保存用户输入信息的节点元素）
        allClass = $([]);
    //统计方案标题
    var staTitleAddTip = $("#staTitleAddTip");
    var staTitleAdd = $("#staTitleAdd");
    //统计方案代码
    var staStyleAddTip = $("#staStyleAddTip");
    var staStyleAdd = $("#staStyleAdd");
    //模块名
    var moduleAddTip = $("#moduleAddTip");
    var moduleAdd = $("#moduleAdd");
    var codeAddTip   = $("#codeAddTip");
    var codeAdd= $("#codeAdd");
    //统计方案标题
    allFields.add(staTitleAddTip);
    allClass.add(staTitleAdd);
    //统计方案代码
    allFields.add(staStyleAddTip);
    allClass.add(staStyleAdd);
    //模块名
    allFields.add(moduleAddTip);
    allClass.add(moduleAdd);
    allFields.add(codeAddTip);
    allClass.add(codeAdd);


    /* 验证表单信息 */
    function check(){
        var bValid = true;
        allFields.removeClass("ui-state-highlight" );
        allClass.removeClass("ui-state-error");
        allFields.text("");
        var reg = /[\'\'\[\]<>?\\!]/;
        //统计方案标题
        bValid = checkLength(staTitleAdd, "统计方案标题", 1, 100,  staTitleAddTip)
        && checkRegexp(staTitleAdd, reg, "输入的内容包含特殊字符!", staTitleAddTip ) && bValid;
        //统计方案代码
        bValid = checkLength(staStyleAdd, "统计方案代码", 1, 50,  staStyleAddTip)
        && checkRegexp(staStyleAdd, reg, "输入的内容包含特殊字符!", staStyleAddTip ) && bValid;
        //模块名
        bValid = checkLength(moduleAdd, "模块名", 1, 100,  moduleAddTip)
        && checkRegexp(moduleAdd, reg, "输入的内容包含特殊字符!", moduleAddTip ) && bValid;
        bValid = checkLength(codeAdd, "代码块", 1, 4000,  codeAddTip) && bValid;
        if(bValid){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 检验字符串长度
     * 即验证一个字符的长度是否符合规定的长度
     * @param _object         待验证对象
     * @param _title          提示信息名称
     * @param _min            最小长度值
     * @param _max            最大长度值
     * @param _showLabelName  错误提示显示节点元素
     * @return true:验证通过  false:验证失败
     */
    function checkLength( _object, _title, _min, _max, _showLabelName ) {
        //验证是否的长度是否超过给定的限定值
        if ( _object.val().length > _max || _object.val().length < _min ) {
            _object.addClass( "ui-state-error" );
            //如果不通过显示提示信息
            updateTips(_showLabelName, _title + " 长度必须在 " +
            _min + " ~ " + _max + "字符之间." );
            return false;
        } else {
            return true;
        }
    }


    /**
     * 使用正则表达式检验字符串
     * @param _object         待验证对象
     * @param _regexp         正则表达式
     * @param _title          提示信息名称
     * @param _showLabelName  错误提示显示节点元素
     * @return true:验证通过  false:验证失败
     */
    function checkRegexp( _object, _regexp, _title, _showLabelName ) {
        allFields.text("");
        _object.removeClass("ui-state-error");
        if ( ( _regexp.test( _object.val() ) ) ) {
            _object.addClass( "ui-state-error" );
            //如果不通过显示提示信息
            updateTips(_showLabelName, _title );
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * 即验证一个字符的长度是否符合规定的长度
     * @param _object         待验证对象
     * @param _title          提示信息名称
     */
    function updateTips(_showLabelName, _title) {
        _showLabelName.removeClass("ui-state-highlight");
        _showLabelName.text(_title).addClass( "ui-state-highlight" );
    }
  var num=0;
    /* “添加”表单的绑定函数 */
    $('#save').button().click(function() {
        //if(check()){
        if($("#addForm").valid()&&num==0){
            num+=1;
            $('#addForm').submit();
        }else{
            return false;
        }
    });

    /* "编辑" 表单的绑定函数 */
    $('#update').button().click(function() {
        if($('#updateForm').valid()){
            $('#updateForm').submit();
        }
    });
});

/**
 * 弹框显示编辑统计代码
 * @param id
 */
function updateButton(id) {
    $.ajax({
        url:"openUpdateStatisticsCodePageAjax.htm?CSRFToken="+$('#CSRFToken').val()+"&id="+id,
        async : false,
        success:function(data){
            //填充值
            if(data != "") {
                $("#staCodeIdUpdate").val(data.statisticsCode.staCodeId);
                $("#staTitleUpdate").val(data.statisticsCode.staTitle);
                $("#staStyleUpdate").val(data.statisticsCode.staStyle);
                $("#moduleUpdate").val(data.statisticsCode.module);
                $("#code").text(data.statisticsCode.code);
                if(data.statisticsCode.usedStatus == 0) {
                    $("#radioN").attr("checked","true");
                }
                if(data.statisticsCode.usedStatus == 1) {
                    $("#radioY").attr("checked","true");
                }
                $("#addSpecialTopic").modal('show');
            }
        }
    });
}

/**
 * 弹框显示删除单个统计代码记录
 * @param id
 */
function deleteSingle(id) {
    showDeleteOneConfirmAlert("deleteStatisticsCode.htm?CSRFToken="+$("#CSRFToken").val()+"&deleteStatus="+$("#deleteStatus").val()+"&ids="+id);
}

/**
 * 批量删除统计代码
 * @param deleteFormId
 * @param name
 */
var checkedList;
function deleteBatch(deleteFormId,name) {
    checkedList = new Array();
    $("input[name=staCodeId]:checked").each(function() {
        checkedList.push($(this).val());
    });
    $('#bodyForm').attr("action","deleteStatisticsCode.htm?CSRFToken="+$("#CSRFToken").val()+"&deleteStatus="+$("#deleteStatus").val()+"&ids="+checkedList+"");
    showDeleteBatchConfirmAlert(deleteFormId,name);
}

function checkSelected(_objId,_modifyFlag) {

    if(_modifyFlag!=0){
        if(checkedList.length ==1){
            return true;
        }else{
            return false;
        }
    }
    if(checkedList.length > 0){
        return true;
    }else{
        return false;
    }

}

