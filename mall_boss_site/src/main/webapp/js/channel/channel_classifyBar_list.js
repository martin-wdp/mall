/*执行AJAX查询分类信息*/
function doAjaxForCate(pageNo, pageSize)
{
    $('.set_box1').show();
    $('.fldh').hide();
    var tempId = $("#tempId").val();
    var channelId=$("#channelId").val();
    /*AJAX查询分类信息*/
    $.get("queryTempClassifyBar.htm?pageNo=" + pageNo + "&pageSize=" + pageSize
        + "&channelId="+channelId+"&CSRFToken="+$("#CSRFToken").val(),
        function (data)
        {

            getCateList(data.list);
            /*进行递归  GO*/
            /*把计算出来的结果放在表格的BODY中*/

            /* 下面是另一种带折叠的表格，没有分页 */
            var option = {
                theme:'vsStyle',
                //expandable:false,
                expandLevel : 2,//从哪一级开始收缩
                column : 1

            };
            $('#treeTable').treeTable(option);
            /* 下面是另一种带折叠的表格，没有分页 END */
        });

}
/*获取AJAX返回的分类列表，并进行反递归*/
function getCateList(data)
{
    var html = "";
    /*首先把内容置空*/
    $("#goodsCateHtml").html("");
    for (var i = 0; i < data.length; i++)
    {
        html = html + "<tr id="+data[i].classifyBarId+">" +
        "<td ><input type='checkbox' name='classifyBarIds' value='" + data[i].classifyBarId + "' /></td>" + "<td style='text-align:left'>";
        html = html + data[i].name + "</td>" + "<td >";
        if(data[i].temp2!=null){
            html = html + "<img src='"+data[i].temp2 + "' width='30px' height='30px'/>";
        }
        html = html + "</td><td class='tc'>";

        if(data[i].url!=null && data[i].url!='0'){
            html = html + data[i].url;
        }
        html = html  + "</td>";

        if(data[i].isUsing == '1'){
            html+="<td><a href='javascript:;'><span class='label label-success'>是</span></a></td>";
        }else{
            html+="<td><a href='javascript:;'><span class='label label-default'>否</span></a></td>";
        }

        html = html  + '<td> <div class="btn-group">' +
        '<button type="button" class="btn btn-default" onclick="showClassifyBar('+data[i].classifyBarId+')">编辑</button>'
        +'<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">'
        +' <span class="caret"></span><span class="sr-only">Toggle Dropdown</span> </button>'
        +'<ul class="dropdown-menu" role="menu">';
        html = html + ' <li><a  onclick="addSetboxPaternity('+data[i].classifyBarId+','+data[i].goodsCatId+')" href="#">添加子分类</a></li>';
        html = html + '  <li><a href="#" onclick="showClassfybarParentCate('+data[i].classifyBarId+')">设置广告</a></li>';
        html = html + '  <li><a href="#" onclick="onclickShowClassifyBrandParet('+data[i].classifyBarId+')">设置品牌</a></li>';
        html = html +
        ' <li><a href="#" onclick="delStoreyTrademarkById(\'deleteClassifyBarAndCateAndQuick.htm?CSRFToken='+$(CSRFToken).val()+'&classifyBarId='+data[i].classifyBarId+'\',10)">删除</a></li></ul> </div>   </td> </tr> ';
        /*把子分类列表传给反递归的方法*/
        html = html + calcSonCate(data[i].childs);
        $("#goodsCateHtml").html(html);
    }

}
function addSetboxPaternity(obj,id){
    $("#paternity_classifySort").val("");
    $("#paternity_addClasifyBarTitle").html("添加分类导航")
    $("#up_parentId").val(obj);
    $("#parentGoodsCate").hide();
    $("#paternity_classifyBarId").val("");
    $("#paternity_subClasifybarBtn").attr("onclick","suboodsCatePater()");
    $("#paternity_inlineRadio1").prop("checked","checked");
    if(id!=-1){
        $("#parentGoodsCate").show();
        $.post("showtempclassifybarandcateandquickajax.htm?CSRFToken="+$("#CSRFToken").val(), { parentId:obj },
        function(data) {
                $("#up_goodsCatId").html("");
                for(var i=0;i<data.cateList.length;i++){

                    if(i==0){
                        $("#up2_classifyName").val(data.cateList[i].catName);
                        $("#up_parGoodsCatId").val(data.cateList[i].catId);
                    }
                    $("#up_goodsCatId").append("<option value='"+data.cateList[i].catId+"'>"+data.cateList[i].catName+"</option>");
                }
        });
    }else{
        $("#up2_classifyName").val("");
        $("#up_parGoodsCatId").val(-1);
    }
        $('#addSetbox_paternity').modal('show');
}

function updateSetboxPaternity(obj,id,cId){
    $("#paternity_addClasifyBarTitle").html("修改分类导航")
    $("#up_parentId").val(obj);
    $("#parentGoodsCate").hide();
    $("#up_goodsCatId").html("");
    $("#paternity_subClasifybarBtn").attr("onclick","updateCatePater()");
        $.post("showtempclassifybarandcateandquickajax.htm?CSRFToken="+$("#CSRFToken").val(), { parentId:obj , classifyBarId:cId },
            function(data) {
                $("#paternity_classifyBarId").val(data.classifyBar.classifyBarId);
                if(data.classifyBar.isUsing==1){
                       $("#paternity_inlineRadio1").prop("checked","checked");
                }else{
                    $("#paternity_inlineRadio2").prop("checked","checked");
                }

                if(id!=-1){
                    for(var i=0;i<data.cateList.length;i++) {
                        $("#up_goodsCatId").append("<option value='"+data.cateList[i].catId+"'>"+data.cateList[i].catName+"</option>");
                    }
                    $("#parentGoodsCate").show();
                    $("#up_parGoodsCatId").val(data.classifyBar.goodsCatId);
                    $("#up_goodsCatId").val(data.classifyBar.goodsCatId);
                }else{
                    $("#up_parGoodsCatId").val(-1);
                }

                $("#paternity_classifySort").val(data.classifyBar.sort);
                $("#up2_classifyName").val(data.classifyBar.name);

            });
    $('#addSetbox_paternity').modal('show');
}


function changePaternGoodsCateName(){
    $("#up2_classifyName").val($("#up_goodsCatId").find("option:selected").text());
    $("#up_parGoodsCatId").val($("#up_goodsCatId").val());
}

/*根据子分类列表反递归 */
function calcSonCate(data)
{
    var html = "";
    for (var i = 0; i < data.length; i++)
    {
        html = html + "<tr  id="+data[i].classifyBarId+" pId="+data[i].parentId+">" +
//	        "<td class='sec_td' colspan='6'>" + "<table class='sec_tb'><tr>" +
        "<td ><input type='checkbox' name='classifyBarIds' value='"
        + data[i].classifyBarId + "' /></td>" + "<td style='text-align:left'>";
        html = html + data[i].name + "</td>" + "<td>";
        if(data[i].temp2!=null){
            html = html + "<img src='"+data[i].temp2 + "' width='30px' height='30px'/>";
        }
        html = html + "</td><td>";

        if(data[i].url!=null && data[i].url!='0'){
            html = html + data[i].url;
        }
        html = html  + "</td>";

        if(data[i].isUsing == '1'){
            html+="<td><a href='javascript:;'><span class='label label-success'>是</span></a></td>";
        }else{
            html+="<td><a href='javascript:;'><span class='label label-default'>否</span></a></td>";
        }

        html = html  + '<td> <div class="btn-group">' +
        '<button type="button" class="btn btn-default" onclick="updateSetboxPaternity('+data[i].parentId+','+data[i].goodsCatId+','+data[i].classifyBarId+')" >编辑</button>'
        +'<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">'
        +' <span class="caret"></span><span class="sr-only">Toggle Dropdown</span> </button>'
        +'<ul class="dropdown-menu" role="menu">';
        if($("#channelId").val()==""){
            if(data[i].grade<$("#tempClassifyLevel").val()){
                html = html + "<li>添加子分类</li>";
            }
        }else{
            if(data[i].grade<$("#channelClassifyLevel").val()){
                html = html + "<li>添加子分类</li>";
            }
        }
        html = html + ' <li><a  onclick="addSetboxPaternity('+data[i].classifyBarId+','+data[i].goodsCatId+')" href="#">添加子分类</a></li>';
        html = html +
        ' <li><a href="#" onclick="delStoreyTrademarkById(\'deleteClassifyBarAndCateAndQuick.htm?CSRFToken='+$(CSRFToken).val()+'&classifyBarId='+data[i].classifyBarId+'\',10)">删除</a></li></ul> </div>   </td> </tr> ';

        html+=calcSonCateCh(data[i].childs);
    }
    return html;
}

function calcSonCateCh(data){
    var html = "";
    for (var i = 0; i < data.length; i++)
    {
        html = html + "<tr  id="+data[i].classifyBarId+" pId="+data[i].parentId+">" +
//	        "<td class='sec_td' colspan='6'>" + "<table class='sec_tb'><tr>" +
        "<td ><input type='checkbox' name='classifyBarIds' value='"
        + data[i].classifyBarId + "' /></td>" + "<td style='text-align:left'>";
        html = html + data[i].name + "</td>" + "<td>";
        if(data[i].temp2!=null){
            html = html + "<img src='"+data[i].temp2 + "' width='30px' height='30px'/>";
        }
        html = html + "</td><td>";

        if(data[i].url!=null && data[i].url!='0'){
            html = html + data[i].url;
        }
        html = html  + "</td>";

        if(data[i].isUsing == '1'){
            html+="<td><a href='javascript:;'><span class='label label-success'>是</span></a></td>";
        }else{
            html+="<td><a href='javascript:;'><span class='label label-default'>否</span></a></td>";
        }

        html = html  + '<td> <div class="btn-group">' +
        '<button type="button" class="btn btn-default" onclick="updateSetboxPaternity('+data[i].parentId+','+data[i].goodsCatId+','+data[i].classifyBarId+')" >编辑</button>'
        +'<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">'
        +' <span class="caret"></span><span class="sr-only">Toggle Dropdown</span> </button>'
        +'<ul class="dropdown-menu" role="menu">';
        html = html +
        ' <li><a href="#" onclick="delStoreyTrademarkById(\'deleteClassifyBarAndCateAndQuick.htm?CSRFToken='+$(CSRFToken).val()+'&classifyBarId='+data[i].classifyBarId+'\',10)">删除</a></li></ul> </div>   </td> </tr> ';
        html+=calcSonCateCh(data[i].childs);
    }
    return html;
}

function changeGoodsCateName(obj){
    if($("#goodsCate"+obj).val()!=0){
        $("#goodsCateName"+obj).val($("#goodsCate"+obj).find("option:selected").text());
    }else{
        $("#goodsCateName"+obj).val("");
    }
}

function submitgoodsCate(){
    if(!$("#goodsCateForm").valid()) return;
    $.ajax({
        //cache: true,
        type: "POST",
        url:"createtempclassifybarajax.htm",
        data:$('#goodsCateForm').serialize(),// 你的formid
        async: false,
        success: function(data) {
            if(data==1){

                $("#clsClassifyBarBtn").click();
                var option = {
                    theme:'vsStyle',
                    expandLevel : 2,//从哪一级开始收缩
                    column : 1
                };
                $('#treeTable').treeTable(option);
                $("#goodsCateHtml").html('<tr><td colspan="7"><p class="text-center">加载中</p> </td></tr>');
                doAjaxForCate(1,10000);

            }else{
                $(cls_adver).click();
                showTipAlert("添加失败！！");
            }
        },
        error:function(request){
            alert("Connect error");
        }
    });
}

function suboodsCatePater(){
    if(!$("#paternity_goodsCateForm").valid()) return;
    $.ajax({
        cache: true,
        type: "POST",
        url:"createtempclassifybarsubclassi.htm",
        data:$('#paternity_goodsCateForm').serialize(),// 你的formid
        async: false,
        success: function(data) {
            if(data==1){
                $("#paternity_clsClassifyBarBtn").click();
                var option = {
                    theme:'vsStyle',
                    expandLevel : 1,//从哪一级开始收缩
                    column : 1
                };
                $('#treeTable').treeTable(option);
                $("#goodsCateHtml").html('<tr><td colspan="7"><p class="text-center">加载中</p> </td></tr>');
                doAjaxForCate(1,10000);

            }else{
                $(cls_adver).click();
                showTipAlert("添加失败！！");
            }
        }
    });
}

function updateCatePater(){
    if(!$("#paternity_goodsCateForm").valid()) return;
    $.ajax({
        cache: true,
        type: "POST",
        url:"updatetempclassifybarajax.htm",
        data:$('#paternity_goodsCateForm').serialize(),// 你的formid
        async: false,
        success: function(data) {
            if(data==1){
                $("#paternity_clsClassifyBarBtn").click();
                var option = {
                    theme:'vsStyle',
                    expandLevel : 1,//从哪一级开始收缩
                    column : 1
                };
                $('#treeTable').treeTable(option);
                $("#goodsCateHtml").html('<tr><td colspan="7"><p class="text-center">加载中</p> </td></tr>');
                doAjaxForCate(1,10000);

            }else{
                $(cls_adver).click();
                showTipAlert("修改失败！！");
            }
        }
    });
}

function updategoodsCate(){
    if(!$("#goodsCateForm").valid()) return;
    $.ajax({
        cache: true,
        type: "POST",
        url:"updatetempclassifybarandcateandquickajax.htm",
        data:$('#goodsCateForm').serialize(),// 你的formid
        async: false,
        success: function(data) {
            if(data==1){

                $("#clsClassifyBarBtn").click();
                var option = {
                    theme:'vsStyle',
                    expandLevel : 1,//从哪一级开始收缩
                    column : 1
                };
                $('#treeTable').treeTable(option);
                $("#goodsCateHtml").html('<tr><td colspan="7"><p class="text-center">加载中</p> </td></tr>');
                doAjaxForCate(1,10000);

            }else{
                $(cls_adver).click();
                showTipAlert("修改失败！！");
            }
        }
    });
}

function clsClassifyBar(){
    clsBtnCss();
    $("#up_classifyName").val("");
    $("#goodsCatePath").val("");
    $("#goodsCate2").val(-1);
    $("#goodsCate3").val(-1);
    $("#goodsCate4").val(-1);
    $("#goodsCateName2").val("");
    $("#goodsCateName3").val("");
    $("#goodsCateName4").val("");
    $("#bartemps1").val("");
    $("#bartemps2").val("");
    $("#bartemps3").val("");
    $("#bartemps4").val("");
    $("#barCNames1").val("");
    $("#barCNames2").val("");
    $("#barCNames3").val("");
    $("#barCNames4").val("");
    $("#barQNames1").val("");
    $("#barQNames2").val("");
    $("#barQNames3").val("");
    $("#barQNames4").val("");
    $("#barQTemps1").val("");
    $("#barQTemps2").val("");
    $("#barQTemps3").val("");
    $("#barQTemps4").val("");
    $("#up_classifyBarId").val("");
    $("#classifySort").val("");
    $("#addClasifyBarTitle").html("添加分类导航");
    $("#inlineRadio1").prop("checked","checked")
    $("#goodsCatePath_y").attr("src","images/default_head.jpg");
    $("#subClasifybarBtn").attr("onclick","submitgoodsCate()")
    $("#zdyspfl1").click();

}


function showClassifyBar(classifyBarId){
    $.post("showtempclassifybarandcateandquickajax.htm?CSRFToken="+$("#CSRFToken").val(), { classifyBarId:classifyBarId },
        function(data){
            $("#up_classifyName").val(data.classifyBar.name);
            $("#goodsCatePath").val(data.classifyBar.temp2);
            $("#goodsCatePath_y").attr("src",data.classifyBar.temp2);
            $("#up_classifyBarId").val(data.classifyBar.classifyBarId);
            $("#classifySort").val(data.classifyBar.sort);
            $("#subClasifybarBtn").attr("onclick","updategoodsCate()")
            if(data.classifyBar.isUsing==1){
                $("#inlineRadio1").prop("checked","checked");
            }else{
                $("#inlineRadio2").prop("checked","checked");
            }
            if(data.classifyBar.goodsCatId==-1){
                $("#barQNames1").val(data.barQuicks[0].cateName);
                $("#barQNames2").val(data.barQuicks[1].cateName);
                $("#barQNames3").val(data.barQuicks[2].cateName);
                $("#barQNames4").val(data.barQuicks[3].cateName);
                $("#barQTemps1").val(data.barQuicks[0].temp2);
                $("#barQTemps2").val(data.barQuicks[1].temp2);
                $("#barQTemps3").val(data.barQuicks[2].temp2);
                $("#barQTemps4").val(data.barQuicks[3].temp2);
                $("#barCNames1").val(data.barCates[0].cateName);
                $("#barCNames2").val(data.barCates[0].cateName);
                $("#barCNames3").val(data.barCates[0].cateName);
                $("#barCNames4").val(data.barCates[0].cateName);
                $("#bartemps1").val(data.barCates[0].temp2);
                $("#bartemps2").val(data.barCates[1].temp2);
                $("#bartemps3").val(data.barCates[2].temp2);
                $("#bartemps4").val(data.barCates[3].temp2);

                $("#zdyspfl2").click();
            }else{

                if(data.barCates[0]!=null){
                    $("#goodsCate1").val(data.barCates[0].cateId);
                    $("#goodsCateName1").val(data.barCates[0].cateName);
                }
                if(data.barCates[1]!=null){
                    $("#goodsCateName2").val(data.barCates[1].cateName);
                    $("#goodsCate2").val(data.barCates[1].cateId);

                }
                if(data.barCates[2]!=null){
                    $("#goodsCateName3").val(data.barCates[2].cateName);
                    $("#goodsCate3").val(data.barCates[2].cateId);


                }
                if(data.barCates[3]!=null){
                    $("#goodsCate4").val(data.barCates[3].cateId);
                    $("#goodsCateName4").val(data.barCates[3].cateName);
                }
                if(data.barQuicks[0]!=null){
                    $("#barQuickName1").val(data.barQuicks[0].cateName);
                    $("#barQuickIds1").val(data.barQuicks[0].cateId);
                }

                if(data.barQuicks[1]!=null){
                    $("#barQuickName2").val(data.barQuicks[1].cateName);
                    $("#barQuickIds2").val(data.barQuicks[1].cateId);
                }
                if(data.barQuicks[2]!=null){
                    $("#barQuickName3").val(data.barQuicks[2].cateName);
                    $("#barQuickIds3").val(data.barQuicks[2].cateId);
                }
                if(data.barQuicks[3]!=null){
                    $("#barQuickName4").val(data.barQuicks[3].cateName);
                    $("#barQuickIds4").val(data.barQuicks[3].cateId);
                }

            }
            $("#addClasifyBarTitle").html("修改分类导航");

            $('#addSetbox1').modal('show');
        });

}

/**分类导航*/
<!--页面导航列表-->
function showClassfybarCate(){
    showClassifyBarAdverPage();
    $('.set_box2').show();
    $('.fldh').hide();
    $("#clasifycz1").show();
    $("#clasifycz2").hide();

}
function showClassifyBarAdverPage(pageNo){
    $("#showFloorAdver").hide();
    $("#storeyAdverId").val("");
    $("#upChannelAdverId").val("");
    $("#up_adverType").val(151);
    $("#advFlag").val(0);
    $("#showAdverTitle").html("添加分类导航广告");
    $("#up_storeyAdverTempId").val("");
    $(upAtId).attr("value",161);
    $("#up_temp3").val(1);
    $("#up_temp1").val("");
    $("#up_floorId").val("");
    var pageSize=10;
    $("#isAdver").val(2);
    if($("#showClassifyOpetion").val()!=null){
        pageSize=$(showClassifyOpetion).val();
    }
    $("#advFlag").val("");
    $.post("queryclassifyadverbypagebeanajax.htm", { tempId: $(tempId).val(),CSRFToken:$(CSRFToken).val(),pageSize:pageSize,pageNo:pageNo,temp3:1},
        function(data){

            $(showClassifyBarCateHtml).html("");
            if(data.pb.list.length==0){
                $(showClassifyBarCateHtml).append('<tr><td colspan="6"><p class="text-center">暂无可用数据</p> </td></tr>');
            }
            for(var i=0;i<data.pb.list.length;i++){
                var str="<tr>";
                str+=" <td><input  name='adverId'  value="+data.pb.list[i].channelAdverId+" type='checkbox'></td>";
                str+='<td><img width="150px;" alt="" src="'+data.pb.list[i].adverPath+'" height="50"></td>';
                str+=" <td>"+data.pb.list[i].adverHref +"</td>";
                str+=" <td>"+data.pb.list[i].adverSort +"</td>";
                if(data.pb.list[i].userStatus==1){
                    str+="<td><a href='javascript:;'><span class='label label-success'>是</span></a></td>";
                }else{
                    str+="<td><a href='javascript:;'><span class='label label-default'>否</span></a></td>";
                }

                str+= '<td><div class="btn-group"><button type="button" class="btn btn-default" onclick="showAdver('+data.pb.list[i].channelAdverId+')">编辑</button>'+
                '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">'+
                '<span class="caret"></span>'+
                '<span class="sr-only">Toggle Dropdown</span>'+
                '</button><ul class="dropdown-menu" role="menu"><li><a onclick="delStoreyTrademarkById(\'deletetempadverajax.htm?CSRFToken='+$(CSRFToken).val()+'&adverId='+data.pb.list[i].channelAdverId+'\',11)" href="javascript:;">删除</a></li></ul></div></td>';
                str+="</tr>";
                $(showClassifyBarCateHtml).append(str);

            }
            pageBeanClassifyBarAdver(data);
        });

}
<!--页面导航分页-->
function pageBeanClassifyBarAdver(data){
    var footStr='<div class="table_pagenav pull-right">';
    footStr+='<div class="meneame">';
    if(data.pb.pageNo==1){
        footStr+='<span class="disabled"> 上一页 </span>';
    }else{
        footStr+='<a  href="javascript:;" onclick="showClassifyBarAdverPage('+(data.pb.pageNo-1)+')"> 上一页 </a>';
    }

    $(showClassifyBarCateHtmlFoot).html("");
    for(var i = data.pb.startNo;i<data.pb.endNo+1;i++){
        if(i==data.pb.pageNo){
            footStr+=' <span class="current"> '+i+'</span>';
        }else{
            footStr+='<a href="javascript:;"  onclick="showClassifyBarAdverPage('+i+')">'+i+'</a>';
        }
    }
    if(data.pb.pageNo==data.pb.totalPages){
        footStr+='<span class="disabled"> 下一页 </span>';
    }else{
        footStr+='   <a href="javascript:;" onclick="showClassifyBarAdverPage('+(data.pb.pageNo+1)+')"> 下一页 </a>';
    }

    footStr+='</div>';
    footStr+='</div>';
    footStr+=' <div class="table_ctrl pull-left">';
    footStr+='<form role="form" class="form-inline"">';
    footStr+=' <label class="control-label ">每页显示：</label>';
    footStr+=' <select class="form-control" id="showClassifyOpetion" onchange="showClassifyBarAdverPage('+data.pb.pageNo+')">';
    if(data.pb.pageSize==10){
        footStr+='<option value="10" selected="selected">10</option>';
    }else{
        footStr+='<option value="10">10</option>';
    }
    if(data.pb.pageSize==20){
        footStr+='<option value="20" selected="selected">20</option>';
    }else{
        footStr+='<option value="20">20</option>';
    }
    if(data.pb.pageSize==50){
        footStr+='<option value="50" selected="selected">50</option>';
    }else{
        footStr+='<option value="50">50</option>';
    }
    if(data.pb.pageSize==100){
        footStr+='<option value="100" selected="selected">100</option>';
    }else{
        footStr+='<option value="100">100</option>';
    }
    footStr+='</select>';
    footStr+='</form>';
    footStr+='</div>';
    footStr+='<div class="clr"></div>';

    $(showClassifyBarCateHtmlFoot).append(footStr);
}

/**分类导航*/

/**分类导航品牌*/
function onclickShowClassifyBrand(){
    showClassifyStoreyTrademark();
    $("#up_temp5").val("");
    $('.set_box3').show();$('.fldh').hide();
    $("#classifybtn2").hide();
    $("#classifybtn1").show();
}

function showClassifyStoreyTrademark(pageNo){
    $("#isBrand").val(1);
    $("#trademarkClassify").val($("#trademark"+$("#classifyTrademarkBrand").val()).val());
    $("#classifyYLSrc").attr("src",$("#trademark"+$("#classifyTrademarkBrand").val()).val());
    $("#zslx2").click();
    var pageSize=10;
    if($("#showClassifyTrademarkOpetion").val()!=null){
        pageSize=$(showClassifyTrademarkOpetion).val();
    }
    $.post("queryclassifybarbrandbypagebeanajax.htm", { tempId: $(tempId).val(),CSRFToken:$(CSRFToken).val(),pageSize:pageSize,pageNo:pageNo,temp2:1},
        function(data){
            $(classifyBarBrandHtml).html("");
            if(data.pb.list.length==0){
                $(classifyBarBrandHtml).append('<tr><td colspan="7"><p class="text-center">暂无可用数据</p> </td></tr>');
            }
            for(var i=0;i<data.pb.list.length;i++){
                var str="<tr>";
                str+=" <td><input  name='trademarkId'  value="+data.pb.list[i].channelTrademarkId+" type='checkbox'></td>";

                if(data.pb.list[i].showType==1){
                    str+='<td><img width="150px;" alt="" src="'+data.pb.list[i].logoSrc+'" height="50"></td>';
                    str+=" <td>图片</td>";
                }else{
                    str+='<td>'+data.pb.list[i].title+'</td>';
                    str+=" <td>文字</td>";
                }
                str+=" <td>"+data.pb.list[i].sort +"</td>";
                if(data.pb.list[i].userStatus==1){
                    str+="<td><a href='javascript:;'><span class='label label-success'>是</span></a></td>";
                }else{
                    str+="<td><a href='javascript:;'><span class='label label-default'>否</span></a></td>";
                }

                str+= '<td><div class="btn-group"><button type="button" class="btn btn-default" onclick="updateClassTrademarkify('+data.pb.list[i].channelTrademarkId+')">编辑</button>'+
                '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">'+
                '<span class="caret"></span>'+
                '<span class="sr-only">Toggle Dropdown</span>'+
                '</button><ul class="dropdown-menu" role="menu"><li><a onclick="delStoreyTrademarkById(\'deletechannelstoreytrademarkajax.htm?CSRFToken='+$(CSRFToken).val()+'&trademarkId='+data.pb.list[i].channelTrademarkId+'\',12)" href="javascript:;">删除</a></li></ul></div></td>';
                str+="</tr>";
                $(classifyBarBrandHtml).append(str);

            }
            classifyStoreyTrademarkBean(data);
        });

}


function classifyStoreyTrademarkBean(data){
    var footStr='<div class="table_pagenav pull-right">';
    footStr+='<div class="meneame">';
    if(data.pb.pageNo==1){
        footStr+='<span class="disabled"> 上一页 </span>';
    }else{
        footStr+='<a  href="javascript:;" onclick="showClassifyStoreyTrademark('+(data.pb.pageNo-1)+')"> 上一页 </a>';
    }
    $(classifyBarBrandHtmlFot).html("");
    for(var i = data.pb.startNo;i<data.pb.endNo+1;i++){
        if(i==data.pb.pageNo){
            footStr+=' <span class="current"> '+i+'</span>';
        }else{
            footStr+='<a href="javascript:;"  onclick="showClassifyStoreyTrademark('+i+')">'+i+'</a>';
        }
    }
    if(data.pb.pageNo==data.pb.totalPages){
        footStr+='<span class="disabled"> 下一页 </span>';
    }else{
        footStr+='   <a href="javascript:;" onclick="showClassifyStoreyTrademark('+(data.pb.pageNo+1)+')"> 下一页 </a>';
    }

    footStr+='</div>';
    footStr+='</div>';
    footStr+=' <div class="table_ctrl pull-left">';
    footStr+='<form role="form" class="form-inline">';
    footStr+=' <label class="control-label">每页显示：</label>';
    footStr+=' <select class="form-control" id="showClassifyTrademarkOpetion" onchange="showClassifyStoreyTrademark('+data.pb.pageNo+')">';
    if(data.pb.pageSize==10){
        footStr+='<option value="10" selected="selected">10</option>';
    }else{
        footStr+='<option value="10">10</option>';
    }
    if(data.pb.pageSize==20){
        footStr+='<option value="20" selected="selected">20</option>';
    }else{
        footStr+='<option value="20">20</option>';
    }
    if(data.pb.pageSize==50){
        footStr+='<option value="50" selected="selected">50</option>';
    }else{
        footStr+='<option value="50">50</option>';
    }
    if(data.pb.pageSize==100){
        footStr+='<option value="100" selected="selected">100</option>';
    }else{
        footStr+='<option value="100">100</option>';
    }
    footStr+='</select>';
    footStr+='</form>';
    footStr+='</div>';
    footStr+='<div class="clr"></div>';

    $(classifyBarBrandHtmlFot).append(footStr);
}

function changeClassifyTrademarkBrand(obj){
    $("#trademarkClassify").val($("#trademark"+$("#classifyTrademarkBrand").val()).val());
    $("#classifyYLSrc").attr("src",$("#trademark"+$(obj).val()).val());
}

function subClassifyTradermark(){
    if(!$("#classfiyBrandAddForm").valid()) return;
    $.ajax({
        cache: true,
        type: "POST",
        url: $(tradermarkUrl).val(),
        data: $("#classfiyBrandAddForm").serialize(),// 你的formid
        async: false,
        error: function (request) {
            showTipAlert("Connection error");
        },
        success: function (data) {
            if (data == 1) {
                if($(classifyTrademarkId).val()!=null&&$(classifyTrademarkId).val()!=""){
                    showTipAlert("修改成功！！");
                }else{
                    showTipAlert("添加成功！！");
                }
                $(clsClassifyBrandbtn).click();
                if($("#isBrand").val()==1){
                    showClassifyStoreyTrademark();
                }else if( $("#isBrand").val()==2){
                    showClassifyStoreyTrademarkParet();
                }
            } else {
                if($(channelTrademarkId).val()!=""){
                    showTipAlert("修改失败！！");
                }else{
                    showTipAlert("添加失败！！");
                }
            }
        }
    });
}

function clsClassifyFuntion(){
    clsBtnCss();
    $("#zslx2").click();
    $("#up_url").val("");
    $("#up_trademarkSort").val("");
    $("#inlineRadio7").prop("checked","checked");
    $("#up_classifyCate").val("");
    $(tradermarkUrl).val("createchannelstoreytrademarkajax.htm");
    $("#classfiyBrandTitle").html("添加分类导航品牌");
    $("#classifyTrademarkId").val("");
}

function updateClassTrademarkify(id){
    $.post("showchannelstoreytrademarkajax.htm", { trademarkId: id,CSRFToken:$(CSRFToken).val()},
        function(data) {
            $("#trademarkTitle").html("修改分类导航品牌");
            $("#classifyTrademarkId").val(data.channelTrademarkId);
            $("#up_trademarkSort").val(data.sort);
            $("#up_url").val(data.url);
            if(data.showType==1){
                $("#zslx2").click();
                $("#classifyTrademarkBrand").val(data.trademarkName);
                $("#trademarkClassify").val($("#trademark"+$("#classifyTrademarkBrand").val()).val());
                $("#classifyYLSrc").attr("src",$("#trademark"+$("#classifyTrademarkBrand").val()).val());
            }else{
                $("#zslx1").click();
                $("#up_classifyCate").val(data.title);
            }

            if(data.userStatus==1){
                $("#inlineRadio7").prop("checked","checked");
            }else{
                $("#inlineRadio8").prop("checked","checked");
            }

        });
    $(tradermarkUrl).val("updatechannelstoreytrademarkajax.htm");
    $('#addSetbox3').modal('show')
}

//分类导航副分类广告
function showClassfybarParentCate(obj){
    $("#up_temp1").val(obj);
    showClassifyParentBarAdverPage();
    $('.set_box2').show();
    $('.set_box1').hide();
    $("#clasifycz1").hide();
    $("#clasifycz2").show();


}

function showClassifyParentBarAdverPage(pageNo){
    $("#up_temp3").val("");
    $("#showFloorAdver").hide();
    $("#storeyAdverId").val("");
    $("#upChannelAdverId").val("");
    $("#up_adverType").val(151);
    $("#advFlag").val(0);
    $("#showAdverTitle").html("添加分类导航广告");
    $("#up_storeyAdverTempId").val("");
    $(upAtId).attr("value",161);
    $("#up_floorId").val("");
    var pageSize=10;
    $("#isAdver").val(4);
    if($("#showClassifyParentOpetion").val()!=null){
        pageSize=$(showClassifyParentOpetion).val();
    }
    $("#advFlag").val("");
    $.post("queryclassifyadverbypagebeanajax.htm", {CSRFToken:$(CSRFToken).val(),pageSize:pageSize,pageNo:pageNo,temp1: $("#up_temp1").val()},
        function(data){

            $(showClassifyBarCateHtml).html("");
            if(data.pb.list.length==0){
                $(showClassifyBarCateHtml).append('<tr><td colspan="6"><p class="text-center">暂无可用数据</p> </td></tr>');
            }
            for(var i=0;i<data.pb.list.length;i++){
                var str="<tr>";
                str+=" <td><input  name='adverId'  value="+data.pb.list[i].channelAdverId+" type='checkbox'></td>";
                str+='<td><img width="150px;" alt="" src="'+data.pb.list[i].adverPath+'" height="50"></td>';
                str+=" <td>"+data.pb.list[i].adverHref +"</td>";
                str+=" <td>"+data.pb.list[i].adverSort +"</td>";
                if(data.pb.list[i].userStatus==1){
                    str+="<td><a href='javascript:;'><span class='label label-success'>是</span></a></td>";
                }else{
                    str+="<td><a href='javascript:;'><span class='label label-default'>否</span></a></td>";
                }

                str+= '<td><div class="btn-group"><button type="button" class="btn btn-default" onclick="showAdver('+data.pb.list[i].channelAdverId+')">编辑</button>'+
                '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">'+
                '<span class="caret"></span>'+
                '<span class="sr-only">Toggle Dropdown</span>'+
                '</button><ul class="dropdown-menu" role="menu"><li><a onclick="delStoreyTrademarkById(\'deletetempadverajax.htm?CSRFToken='+$(CSRFToken).val()+'&adverId='+data.pb.list[i].channelAdverId+'\',13)" href="javascript:;">删除</a></li></ul></div></td>';
                str+="</tr>";
                $(showClassifyBarCateHtml).append(str);

            }
            pageBeanClassifyBarParentAdver(data);
        });

}

function pageBeanClassifyBarParentAdver(data){
    var footStr='<div class="table_pagenav pull-right">';
    footStr+='<div class="meneame">';
    if(data.pb.pageNo==1){
        footStr+='<span class="disabled"> 上一页 </span>';
    }else{
        footStr+='<a  href="javascript:;" onclick="showClassifyParentBarAdverPage('+(data.pb.pageNo-1)+')"> 上一页 </a>';
    }

    $(showClassifyBarCateHtmlFoot).html("");
    for(var i = data.pb.startNo;i<data.pb.endNo+1;i++){
        if(i==data.pb.pageNo){
            footStr+=' <span class="current"> '+i+'</span>';
        }else{
            footStr+='<a href="javascript:;"  onclick="showClassifyParentBarAdverPage('+i+')">'+i+'</a>';
        }
    }
    if(data.pb.pageNo==data.pb.totalPages){
        footStr+='<span class="disabled"> 下一页 </span>';
    }else{
        footStr+='   <a href="javascript:;" onclick="showClassifyParentBarAdverPage('+(data.pb.pageNo+1)+')"> 下一页 </a>';
    }

    footStr+='</div>';
    footStr+='</div>';
    footStr+=' <div class="table_ctrl pull-left">';
    footStr+='<form role="form" class="form-inline"">';
    footStr+=' <label class="control-label ">每页显示：</label>';
    footStr+=' <select class="form-control" id="showClassifyParentOpetion" onchange="showClassifyParentBarAdverPage('+data.pb.pageNo+')">';
    if(data.pb.pageSize==10){
        footStr+='<option value="10" selected="selected">10</option>';
    }else{
        footStr+='<option value="10">10</option>';
    }
    if(data.pb.pageSize==20){
        footStr+='<option value="20" selected="selected">20</option>';
    }else{
        footStr+='<option value="20">20</option>';
    }
    if(data.pb.pageSize==50){
        footStr+='<option value="50" selected="selected">50</option>';
    }else{
        footStr+='<option value="50">50</option>';
    }
    if(data.pb.pageSize==100){
        footStr+='<option value="100" selected="selected">100</option>';
    }else{
        footStr+='<option value="100">100</option>';
    }
    footStr+='</select>';
    footStr+='</form>';
    footStr+='</div>';
    footStr+='<div class="clr"></div>';

    $(showClassifyBarCateHtmlFoot).append(footStr);
}
/**分类导航父类品牌*/
function onclickShowClassifyBrandParet(obj){
    $("#up_temp5").val(obj);
    showClassifyStoreyTrademarkParet();
    $('.set_box3').show();
    $('.set_box1').hide();
    $("#classifybtn1").hide();
    $("#classifybtn2").show();

}

function showClassifyStoreyTrademarkParet(pageNo){
    $("#isBrand").val(2);
    $("#trademarkClassify").val($("#trademark"+$("#classifyTrademarkBrand").val()).val());
    $("#classifyYLSrc").attr("src",$("#trademark"+$("#classifyTrademarkBrand").val()).val());
    $("#zslx2").click();
    var pageSize=10;
    if($("#showClassifyTrademarkParetOpetion").val()!=null){
        pageSize=$(showClassifyTrademarkParetOpetion).val();
    }
    $.post("queryclassifybarbrandbypagebeanajax.htm", { CSRFToken:$(CSRFToken).val(),pageSize:pageSize,pageNo:pageNo,temp1: $("#up_temp5").val()},
        function(data){
            $(classifyBarBrandHtml).html("");
            if(data.pb.list.length==0){
                $(classifyBarBrandHtml).append('<tr><td colspan="7"><p class="text-center">暂无可用数据</p> </td></tr>');
            }
            for(var i=0;i<data.pb.list.length;i++){
                var str="<tr>";
                str+=" <td><input  name='trademarkId'  value="+data.pb.list[i].channelTrademarkId+" type='checkbox'></td>";

                if(data.pb.list[i].showType==1){
                    str+='<td><img width="150px;" alt="" src="'+data.pb.list[i].logoSrc+'" height="50"></td>';
                    str+=" <td>图片</td>";
                }else{
                    str+='<td>'+data.pb.list[i].title+'</td>';
                    str+=" <td>文字</td>";
                }
                str+=" <td>"+data.pb.list[i].sort +"</td>";
                if(data.pb.list[i].userStatus==1){
                    str+="<td><a href='javascript:;'><span class='label label-success'>是</span></a></td>";
                }else{
                    str+="<td><a href='javascript:;'><span class='label label-default'>否</span></a></td>";
                }

                str+= '<td><div class="btn-group"><button type="button" class="btn btn-default" onclick="updateClassTrademarkify('+data.pb.list[i].channelTrademarkId+')">编辑</button>'+
                '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">'+
                '<span class="caret"></span>'+
                '<span class="sr-only">Toggle Dropdown</span>'+
                '</button><ul class="dropdown-menu" role="menu"><li><a onclick="delStoreyTrademarkById(\'deletechannelstoreytrademarkajax.htm?CSRFToken='+$(CSRFToken).val()+'&trademarkId='+data.pb.list[i].channelTrademarkId+'\',12)" href="javascript:;">删除</a></li></ul></div></td>';
                str+="</tr>";
                $(classifyBarBrandHtml).append(str);

            }
            classifyStoreyTrademarkBeanParet(data);
        });

}


function classifyStoreyTrademarkBeanParet(data){
    var footStr='<div class="table_pagenav pull-right">';
    footStr+='<div class="meneame">';
    if(data.pb.pageNo==1){
        footStr+='<span class="disabled"> 上一页 </span>';
    }else{
        footStr+='<a  href="javascript:;" onclick="showClassifyStoreyTrademarkParet('+(data.pb.pageNo-1)+')"> 上一页 </a>';
    }
    $(classifyBarBrandHtmlFot).html("");
    for(var i = data.pb.startNo;i<data.pb.endNo+1;i++){
        if(i==data.pb.pageNo){
            footStr+=' <span class="current"> '+i+'</span>';
        }else{
            footStr+='<a href="javascript:;"  onclick="showClassifyStoreyTrademarkParet('+i+')">'+i+'</a>';
        }
    }
    if(data.pb.pageNo==data.pb.totalPages){
        footStr+='<span class="disabled"> 下一页 </span>';
    }else{
        footStr+='   <a href="javascript:;" onclick="showClassifyStoreyTrademarkParet('+(data.pb.pageNo+1)+')"> 下一页 </a>';
    }

    footStr+='</div>';
    footStr+='</div>';
    footStr+=' <div class="table_ctrl pull-left">';
    footStr+='<form role="form" class="form-inline">';
    footStr+=' <label class="control-label">每页显示：</label>';
    footStr+=' <select class="form-control" id="showClassifyTrademarkParetOpetion" onchange="showClassifyStoreyTrademarkParet('+data.pb.pageNo+')">';
    if(data.pb.pageSize==10){
        footStr+='<option value="10" selected="selected">10</option>';
    }else{
        footStr+='<option value="10">10</option>';
    }
    if(data.pb.pageSize==20){
        footStr+='<option value="20" selected="selected">20</option>';
    }else{
        footStr+='<option value="20">20</option>';
    }
    if(data.pb.pageSize==50){
        footStr+='<option value="50" selected="selected">50</option>';
    }else{
        footStr+='<option value="50">50</option>';
    }
    if(data.pb.pageSize==100){
        footStr+='<option value="100" selected="selected">100</option>';
    }else{
        footStr+='<option value="100">100</option>';
    }
    footStr+='</select>';
    footStr+='</form>';
    footStr+='</div>';
    footStr+='<div class="clr"></div>';

    $(classifyBarBrandHtmlFot).append(footStr);
}