$(function(){
    $('.couponImg').popover({
        content : '建议465*252px',
        trigger : 'hover'
    });

    $("#brandShow").hide();
    $("#cateShow").hide();
    $("#subFormOne").validate();

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


    /* 下面是表单里面的日期时间选择相关的 */
    $('.form_datetime').datetimepicker({
        format: 'yyyy-mm-dd hh:ii:00',
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


    /*下面是时间选择器开始时间不能大于结束时间设置  START*/
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    $('#endpicker').datetimepicker('setStartDate', startTime);
    $('#startpicker').datetimepicker('setEndDate', endTime);
    $('#endpicker')
        .datetimepicker()
        .on('show', function (ev) {
            startTime = $("#startTime").val();
            endTime = $("#endTime").val();
            $('#endpicker').datetimepicker('setStartDate', startTime);
            $('#startpicker').datetimepicker('setEndDate', endTime);
        });
    $('#startpicker')
        .datetimepicker()
        .on('show', function (ev) {
            endTime = $("#endTime").val();
            startTime = $("#startTime").val();
            $('#startpicker').datetimepicker('setEndDate', endTime);
            $('#endpicker').datetimepicker('setStartDate', startTime);
        });
    /*下面是时间选择器开始时间不能大于结束时间设置  END*/
    /* 富文本编辑框 */
    $('.summernote').summernote({
        height: 300,
        tabsize: 2,
        lang: 'zh-CN',
        onImageUpload: function(files, editor, $editable) {
            sendFile(files,editor,$editable);
        }
    });

    /* 富文本编辑框 */
    $('.summernoteTwo').summernote({
        height: 300,
        tabsize: 2,
        lang: 'zh-CN',
        onImageUpload: function(files, editor, $editable) {
            sendFile(files,editor,$editable);
        }
    });

    /* 富文本编辑框 */
    $('.summernoteThree').summernote({
        height: 300,
        tabsize: 2,
        lang: 'zh-CN',
        onImageUpload: function(files, editor, $editable) {
            sendFile(files,editor,$editable);
        }
    });

    /* 下面是关于树形菜单 END */

    /* 下面是表单里面的填写项提示相关的 */
    $('.cuxiaomingchen').popover({
        content : '促销名称',
        trigger : 'hover'
    });
});


function chooseProduct(){
    doAjax(1,8);
    $('#chooseGoods').modal('show');
}

$("#choose").click(function(){
    i=1;
    art.dialog.open('queryImageManageByPbAndCidForChoose.htm?CSRFToken='+$("#CSRFToken").val()+'&size=10000', {
        lock: true,
        opacity:0.3,
        width: '900px',
        height: '620px',
        title: '选择图片'
    });
});

function changeStatus(obj){


    if(obj==0){
        $("#brandShow").hide();
        $("#cateShow").hide();
        $("#goodsShow").show();
    }else if(obj==1){
        $("#brandShow").hide();
        $("#cateShow").show();
        $("#goodsShow").hide();
    }else{
        $("#brandShow").show();
        $("#cateShow").hide();
        $("#goodsShow").hide();

    }
    $("#c_statu").val(obj);
}

//图片回调
function saveChoooseImage(url) {
    if(typeof (url) != 'string') {
        url = url[0];
    }
    if(url.indexOf(',')!=-1){
        url=url.substring(0,url.indexOf(','));
    }
    $("#img").attr("src",url);
    $("#couponImg").val(url);

}

/*点击添加货品的时候*/
/*选择规则*/
function selectChange(obj){
    var str="";
    $(hiddenPrice).html("");
    if($(obj).val()==2){
        str+='<div class="form-group">';
        str+=' <label class="control-label col-sm-4"><span style="color: #ff0000;">*</span>满：</label>';
        str+='<div class="col-sm-1"></div>'
        str+='<div class="col-sm-5">'
        str+='<input name="fullPrice" class="form-control required number">'
        str+='</div></div>';
        str+='<div class="form-group">';
        str+=' <label class="control-label col-sm-4"><span style="color: red;">*</span>减：</label>';
        str+='<div class="col-sm-1"></div>'
        str+='<div class="col-sm-5">'
        str+='<input name="reductionPrice" class="form-control required number">'
        str+='</div></div>';
    }else{

        str+='<div class="form-group">';
        str+=' <label class="control-label col-sm-4"><span style="color: #ff0000;">*</span>减：</label>';
        str+='<div class="col-sm-1"></div>'
        str+='<div class="col-sm-5">'
        str+='<input name="downPrice" class="form-control required number">'
        str+='</div></div>';
    }
    $(hiddenPrice).html(str);
}

/*优惠券列表底下分页页码点击doAjax*/
function doAjax(pageNo, pageSize)
{

    var productName = $("#searchGoodsName").val();
    if(productName!=''){
        productName = encodeURI(encodeURI(productName));
    }

    $("#chooseAllPro").attr("checked",false);
    /*AJAX查询所有的货品列表*/
    $.get("queryProductForCoupon.htm?CSRFToken="+$(CSRFToken).val()+"&pageNo="+pageNo+"&pageSize="+pageSize+"&productName="+productName,
        function (data)
        {
            var list = data.list;
            var productListHtml = "";
            for (var i = 0; i < list.length; i++)
            {
                productListHtml = productListHtml + "<tr>" +"<td class='tc'><input type='checkbox' class='productId' name='productId' onclick='addpro(this);'";

                var pro =  document.getElementsByName("goodsIdP");
                for(var j=0;j<pro.length;j++){
                    if(pro[j].value==list[i].goodsInfoId){
                        productListHtml = productListHtml +' checked="checked" ';
                    }
                }
                productListHtml = productListHtml+" value='" + list[i].goodsInfoId + "'/></td>";
                productListHtml+='<td><img src="'+list[i].goodsInfoImgId+'" class="goodsImg"  width="50" height="50"/></td>';
                productListHtml+= "<td  class='goodsTag' >" ;
                if (list[i].specVo.length > 0)
                {
                    for (var k = 0; k < list[i].specVo.length; k++)
                    {
                        productListHtml = productListHtml + list[i].specVo[k].spec.specName;
                        productListHtml = productListHtml + ":" + list[i].specVo[k].goodsSpecDetail.specDetailName + "<br/>";
                    }
                }
                productListHtml = productListHtml + "</td>" + "<td class='goodsNo'>" + list[i].goodsInfoItemNo+ "</td>" + "<td  class='goodsName' title='"+list[i].goodsInfoName+"' >" + list[i].goodsInfoName + "</td>"  + "</tr>";
            }
            $("#productAddList tbody").html(productListHtml);
            $("input[type=button]").button();
            /*添加页脚*/
            $("#productAddList .meneame").html("");
            var foot = "";
            var i = 1;
            for (var l = data.startNo; l <= data.endNo; l++)
            {
                if ((i - 1 + data.startNo) == data.pageNo)
                {
                    foot = foot + "<span class='current'> " + (i - 1 + data.startNo) + "</span>";
                }
                else
                {
                    foot = foot + "<a onclick='doAjax(" + (i - 1 + data.startNo) + "," + (data.pageSize) + ")' href='javascript:void(0)'>" + (i - 1 + data.startNo) + "</a>";
                }
                i++;
            }
            foot = foot + "";
            /*添加tfoot分页信息*/
            $("#productAddList_table_foot .meneame").html(foot);
        });

}
/*添加货品的时候 分页*/
/*改变每页显示的行数*/
function changePageShow()
{
    doAjax( 1, $("#list_size").val());
}
/*跳转到某一页*/
function changeShowPage( pageSize)
{
    doAjax( $("#list_pageno").val(), pageSize);

}

/*选择商品时添加商品*/
function addpro(obj){
    var productId=$(obj).val();
    var goodsImg=$(obj).parents("tr").find(".goodsImg").attr("src");
    var goodsNo=$(obj).parents("tr").find(".goodsNo").text();
    var goodsName=$(obj).parents("tr").find(".goodsName").text();
    var goodsTag=$(obj).parents("tr").find(".goodsTag").html();
    if(obj.checked==true){
        var htm = "<tr id='goods_tr_"+productId+"'>";
        htm+='<td width="92"><img src="'+goodsImg+'" width="50" height="50"/><input type="hidden" name="goodsIdP" id="goods_Id_'+productId+'" value="'+productId+'"/></td>';
        htm+='<td width="98">'+goodsTag+'</td>';
        htm+='<td width="120">'+goodsNo+'</td>';
        htm+='<td  width="300">'+goodsName+'</td>';
        htm+='<td width="70"><button onclick="removeTr(this);">移除</button></td>';
        htm+="</tr>";
        $("#readproduct tbody").append(htm);
    }else{
        $("#goods_tr_"+productId).remove();

    }
}

/*移除已选择商品*/
function removeTr(obj){
    $(obj).parents("tr").remove();
}

/*添加商品时全选*/
function chooseAllPro(obj){
    if(obj.checked){
        $("input[name='productId']").each(function(){
            this.checked=true;
            $("#goods_tr_"+$(this).val()).remove();
            addpro(this);
        });
    }else{
        $("input[name='productId']").each(function(){
            this.checked=false;
            addpro(this);
        });
    }
}

//保存商品促销
var num=0;
function subFormOne(){
    if($("#summernote").code().replace("<p>","").replace("</p>","")!="<br>"){
        $("#countId").val($("#summernote").code().replace("<p>","").replace("</p>",""));
    }
    var couponCount = $("#couponCount").val();
    if(parseInt($("#couponGetNo").val()) > parseInt(couponCount)){
        showTipAlert("领取张数不能大于生成张数！");
        $("#couponCount").focus();
    }else{
        if(num==0){
            num+=1;
            $("#subFormOne").submit();
        }
    }
}

/*******************类目促销**************************/
function chooseCate(){
    if(!$("#isallCate").prop("checked")){
        $.get("queryallgoodcate.htm?catId=0&CSRFToken="+$(CSRFToken).val(),function(data){
            if(null != data && data.length>0){
                var zNodes = new Array();
                /* 下面是关于树形菜单 */
                var setting = {
                    check: {
                        enable: true,
                        chkboxType : {"Y":"ps","N":"ps"}
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    }
                };
                for(var i =0;i<data.length;i++){
                    for (var i = 0; i < data.length; i++)
                    {
                        if(data[i].catParentId!=null){
                            var node = {
                                id : data[i].catId, pId : data[i].catParentId, name : data[i].catName, open : false
                            };
                            zNodes.push(node);
                        }
                    }
                }
                var zTree;
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                var pro =  document.getElementsByName("cateIdp");
                var treeObject = $.fn.zTree.getZTreeObj("treeDemo");
                for(var i=0;i<pro.length;i++){
                    var treeNode= treeObject.getNodeByParam("id", pro[i].value);
                    treeObject.checkNode(treeNode,true,true);
                }
                $('#chooseCates').modal('show');
            }
        });
    }
}

function trueCate(){
    var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
        nodes=treeObj.getCheckedNodes(true);
    var htm="";
    for(var i=0;i<nodes.length;i++){
        if(!nodes[i].isParent){
            htm+="<tr>";
            htm+='<td width="110">'+nodes[i].id+'<input type="hidden" name="cateIdp" id="cate_Id_p'+nodes[i].id+'" value="'+nodes[i].id+'"></td>';
            htm+='<td width="285">'+nodes[i].name+'</td>';
            htm+='<td width="100"><button onclick="removeTr(this);">移除</button></td>';
            htm+="</tr>";
        }
    }
    $("#list tbody").html(htm);
    $('#chooseCates').modal('hide');
}


function checkIsAll(obj){
    if(obj.checked==true){
        $("#isAlls").val(1);
        $("#towCate").hide();
    }else{
        $("#isAlls").val(0);
        $("#towCate").show();
    }
}

function subFormTwo(){
    var f = true;
    if(!$("#isallCate").prop('checked')){
        var pro =  document.getElementsByName("cateIdp");
        if(pro.length==0){
            $("#pc").html('请选择范围');
            $("#pc").addClass("error");
            f=false&&f;
        }else{
            f=true&&f;
            $("#pc").html('');
        }
    }

    var codexType=$("#addFormTwo").find("input[name='codexType']").val();
    if(codexType=='3'){
        var coup = $("#addFormTwo").find("input[name='couponIds']");
        if(coup.length==0){
            $(".cp2").html('请选择优惠券');
            $(".cp2").addClass("error");
            f=false&&f;
        }else{
            $(".cp2").html('');
            $(".cp2").removeClass("error");
        }
    }

    if($("#addFormTwo").valid()&&f){
        $("#marketingDesTwo").val($(".summernoteTwo").code());
        $("#addFormTwo").submit();
    }

}

/***********************************品牌促销***********************************/

function isAllThree(obj){
    if(obj.checked==true){
        $("#isAllsThree").val(1);
        $("#threeBrand").hide();
    }else{
        $("#isAllsThree").val(0);
        $("#threeBrand").show();
    }
}
function subFormThree(){
    var f = true;
    if(!$("#isAllsThreeChecked").prop('checked')){
        var pro =  document.getElementsByName("brandIdP");
        if(pro.length==0){
            $("#pb").html('请选择范围');
            $("#pb").addClass("error");
            f=false&&f;
        }else{
            f=true&&f;
            $("#pb").html('');
        }
    }

    var codexType=$("#addFormThree").find("input[name='codexType']").val();
    if(codexType=='3'){
        var coup = $("#addFormThree").find("input[name='couponIds']");
        if(coup.length==0){
            $(".cp3").html('请选择优惠券');
            $(".cp3").addClass("error");
            f=false&&f;
        }else{
            $(".cp3").html('');
            $(".cp3").removeClass("error");
        }
    }

    if($("#addFormThree").valid()&&f){

        $("#marketingDesThree").val($(".summernoteThree").code());
        $("#addFormThree").submit();
    }
}

function chooseBrandsView(){
    if(!$("#isAllsThreeChecked").prop('checked')){
        var pro =  document.getElementsByName("brandIdP");
        if(pro!=null){
            var ids="";
            for(var i=0;i<pro.length;i++){
                ids+=pro[i].value;
                if(i<pro.length-1){
                    ids+=",";
                }
            }
            $("#brand").val(ids);
            /* 为选定的select下拉菜单开启搜索提示 */
            $('select[data-live-search="true"]').selectpicker('refresh');
        }else{
            $("#brand").val('');
            /* 为选定的select下拉菜单开启搜索提示 */
            $('select[data-live-search="true"]').selectpicker('refresh');
        }
        $('#chooseBrands').modal('show');
    }
}

/*选择品牌*/
function chooseBrand(){
    var $arr = new Array();
    var brandstr=$("#brand").val();
    if(brandstr!=null){
        brandstr=brandstr.toString();
        $arr= brandstr.split(",");
        var htm="";
        for(var i=0;i<$arr.length;i++){
            htm+="<tr>";
            htm+='<td width="110">'+$arr[i]+'<input type="hidden" name="brandIdP" id="brandIdP'+$arr[i]+'" value="'+$arr[i]+'"></td>';
            htm+='<td width="285">'+$("#brand option[value='"+$arr[i]+"']").text()+'</td>';
            htm+='<td width="100"><button onclick="removeTr(this);">移除</button></td>';
            htm+="</tr>";
        }
    }
    $("#brandlist tbody").html('');
    $("#brandlist tbody").html(htm);
    $('#chooseBrands').modal('hide');
}


/*处理时间格式化*/
function timeStamp2String(time)
{
    var date = new Date(parseFloat(time));
    var datetime = new Date();
    datetime.setTime(date);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}