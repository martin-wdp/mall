/**页面导航*/
<!--页面导航列表-->
function showTempBar(pageNo){
    var pageSize=10;
    //if($("#showTempBarOpetion").val()!=null){
    //    pageSize=$(showTempBarOpetion).val();
    //}
    $.ajax({
        url:"/queryChannelBarByPageBean.htm",
        type:"post",
        data:"channelId="+$("#channelId").val(),
        dataType:"json",
        async:false,
        success:function(msg){
            $(barHtml).html("");
            if(msg.pb.list.length==0){
                $(barHtml).append('<tr><td colspan="6"><p class="text-center">暂无可用数据</p> </td></tr>');
            }
            for (var i = 0; i < msg.pb.list.length; i++) {
                var str = "<tr>";
                str += " <td><input  name='barId'  value=" + msg.pb.list[i].barId + " type='checkbox'></td>";
                str += " <td>" + msg.pb.list[i].barName + "</td>";
                str += " <td>" + msg.pb.list[i].barUrl + "</td>";
                str += " <td>" + msg.pb.list[i].barSort + "</td>";
                if (msg.pb.list[i].usedStatus == 1) {
                    str += "<td><a href='javascript:;'><span class='label label-success'>是</span></a></td>";
                } else {
                    str += "<td><a href='javascript:;'><span class='label label-default'>否</span></a></td>";
                }

                str += '<td><div class="btn-group"><button type="button" class="btn btn-default" onclick="updateBar(' + msg.pb.list[i].barId + ')">编辑</button>' +
                '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">' +
                '<span class="caret"></span>' +
                '<span class="sr-only">Toggle Dropdown</span>' +
                '</button><ul class="dropdown-menu" role="menu"><li><a onclick="delBarById(\'deleteTempBarAjxs.htm?barId=' + msg.pb.list[i].barId + '\')" href="javascript:;">删除</a></li></ul></div></td>';
                str += "</tr>";
                $(barHtml).append(str);

            }

            pageBean(msg);
        }
        //error:function(XMLHttpRequest,textStatus,errorThrown){
        //    alert(XMLHttpRequest.status);
        //    alert(XMLHttpRequest.readyState);
        //    alert(textStatus);
        //}

    });
}

function delBarById(barId){
        showAjaxDeleteConfirmAlertByTemp(barId,1);
}

<!--页面导航分页-->
function pageBean(data){
    var footStr='<div class="table_pagenav pull-right">';
    footStr+='<div class="meneame">';
    if(data.pb.pageNo==1){
        footStr+='<span class="disabled"> 上一页 </span>';
    }else{
        footStr+='<a  href="javascript:;" onclick="showTempBar('+(data.pb.pageNo-1)+')"> 上一页 </a>';
    }
    $(barFootHtml).html("");
    for(var i = data.pb.startNo;i<data.pb.endNo+1;i++){
        if(i==data.pb.pageNo){
            footStr+=' <span class="current"> '+i+'</span>';
        }else{
            footStr+='<a href="javascript:;"  onclick="showTempBar('+i+')">'+i+'</a>';
        }
    }
    if(data.pb.pageNo==data.pb.totalPages){
        footStr+='<span class="disabled"> 下一页 </span>';
    }else{
        footStr+='   <a href="javascript:;" onclick="showTempBar('+(data.pb.pageNo+1)+')"> 下一页 </a>';
    }

    footStr+='</div>';
    footStr+='</div>';
    footStr+=' <div class="table_ctrl pull-left">';
    footStr+='<form role="form" class="form-inline">';
    footStr+=' <label class="control-label">每页显示：</label>';
    footStr+=' <select class="form-control" id="showTempBarOpetion" onchange="showTempBar('+data.pb.pageNo+')">';
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

    $(barFootHtml).append(footStr);
    cls();
}

function submitBar(){
    if(!$("#subForm_Bar").valid()) return;
    $.ajax({
        //cache: true,
        type: "POST",
        url:"/createChannelBar.htm",
        data:$('#subForm_Bar').serialize(),// 你的formid
        async: false,
        error: function(request) {
            showTipAlert("Connection error");
        },
        success: function(data) {
            if(data==1){
                $(barRest).click();
                showTipAlert("添加成功！");
                showTempBar();
            }else{
                $(barRest).click();
                showTipAlert("添加失败！");
            }
        }
    });
}

function cls(){
    clsBtnCss();
    $(up_barName).val("");
    $(up_barUrl).val("");
    $(up_des_bar).val("");
    $(up_barSort).val("");
    $(up_barId).val("");
    $(up_bar_title).html("添加页面导航");
    $(up_userStatus1).prop("checked","checked");
    $(subBarButton).attr("onclick","submitBar()");
}

$(function () {
    changeGoodsCateName(1);
    showTempBar();
    $("#subForm_Bar").validate();
    $("#deleteAllBar").click(function(){
        doAjaxShowDeleteBatchConfirmAlert("delBarHtml","barId","deleteTempBarAjxs.htm",1);
    });

    $('.bigAdv').popover({
        content : '轮播大广告1423*470px；轮播小广告280*280px；页面广告150*80px；楼层广告320*480px、210*480；团购抢购页广告1200*400px；',
        trigger : 'hover'
    });
});


function updateBar(barId){
    $.post("showtempbarbyid.htm", { barId:barId },
        function(data){
            $(up_barName).val(data.barName);
            $(up_barUrl).val(data.barUrl);
            $(up_des_bar).val(data.des);
            $(up_barSort).val(data.barSort);
            if(data.usedStatus==0){
                $(up_userStatus0).attr("checked","checked");
            }else{
                $(up_userStatus1).attr("checked","checked");
            }
            $(up_barId).val(data.barId);
            $(up_bar_title).html("修改页面导航");

            $(subBarButton).attr("onclick","updateBarSubmit()");
            $(addPageNav).modal("show");
     });

}


function updateBarSubmit(){
    if(!$("#subForm_Bar").valid()) return;
    $.ajax({
        cache: true,
        type: "POST",
        url: "updatetempbarbyajax.htm",
        data: $('#subForm_Bar').serialize(),// 你的formid
        async: false,
        error: function (request) {
            showTipAlert("Connection error");
        },
        success: function (data) {
            if (data == 1) {
                $(barRest).click();
                showTipAlert("修改成功！");
                showTempBar();

            } else {
                $(barRest).click();
                showTipAlert("修改失败！");

            }
        }
    });
}
/**页面导航*/


/**分类导航*/
function  showTempCategory(){
    $.post("getsystempdetailbyid.htm", { tempId: $(tempId).val(),CSRFToken:$(CSRFToken).val()},
     function(data) {
         if(data.expFleid5==1){
             $(setShowTemp1).prop("checked","checked");
         }else{
             $(setShowTemp2).prop("checked","checked");
         }

             $(setTempLevel).val(3);
         if(data.expFleid3==1){
             $('#set_Advert').show();
             $(setAdvert1).prop("checked","checked");
         }else{
             $('#set_Advert').hide();
             $(setAdvert2).prop("checked","checked");
         }
         if(data.expFleid4==1){
             $('#set_TempCate').show();
             $(setTempBrand1).prop("checked","checked");
         }else{
             $('#set_TempCate').hide();
             $(setTempBrand2).prop("checked","checked");
         }
     });
}
function updSetAdvertForm(){
    $.ajax({
        url: "updateChannelAndTemp.htm",
        type: "POST",
        data: $('#setAdvertForm').serialize(),// 你的formid
        async: false,
        success: function (data) {
            if (data > 1) {
                showTipAlert("修改成功！！");
                location.href="showChannelSet.htm?channelId="+$("#channelId").val();
                //showTempCategory()
            } else {
                showTipAlert("修改失败！！");
            }
        },
        error: function (request) {
            showTipAlert("Connection error");
        }
    });
}
/**分类导航*/

/**轮播大广告*/
/**页面导航*/
<!--页面导航列表-->
function showAdverPage(atId,pageNo){
    $("#showFloorAdver").show();
    $("#storeyAdverId").val("");
    $("#upChannelAdverId").val("");
    $("#up_adverType").val(151);
    $("#advFlag").val(0);
    $("#showAdverTitle").html("添加模板广告");
    $(upAtId).attr("value",atId);
    $("#up_temp3").val(0);
    $("#up_temp1").val("");
    $("#up_floorId").val("");
    $("#upAtId").val(atId);
    $("#isAdver").val(1);
    //$("#up_storeyAdverChannelId").val( $(channelId).val());
    var pageSize=10;
    if($("#showAdverOpetion").val()!=null){
        pageSize=$(showAdverOpetion).val();
    }
    $.ajax({
        url:"queryChannelAdverByPageBean.htm",
        type:"POST",
        async:false,
        dataType:"json",
        data:{channelId: $(channelId).val(),CSRFToken:$(CSRFToken).val(),pageSize:pageSize,pageNo:pageNo,adverType:151,atId:atId},
        success:function(msg){
            $(adverHtml).html("");
            if(msg.pb.list.length==0){
                $(adverHtml).append('<tr><td colspan="7"><p class="text-center">暂无可用数据</p></td></tr>');
            }
            for(var i=0;i<msg.pb.list.length;i++){
                var str="<tr>";
                str+=" <td><input  name='adverId'  value="+msg.pb.list[i].channelAdverId+" type='checkbox'></td>";
                if(msg.pb.list[i].temp5 == 0 || msg.pb.list[i].temp5==null){
                    str+=" <td>首页广告</td>";
                }else if(msg.pb.list[i].temp5 == 1){
                    str+=" <td>商品列表页广告</td>";
                }else if(msg.pb.list[i].temp5 == 2){
                    str+=" <td>搜索列表页广告</td>";
                }else if(msg.pb.list[i].temp5 == 3){
                    str+=" <td>资讯页广告</td>";
                }else if(msg.pb.list[i].temp5 == 4){
                    str+=" <td>其他。。。</td>";
                }else if(msg.pb.list[i].temp5 == 5){
                    str+=" <td>团购页广告</td>";
                }else if(msg.pb.list[i].temp5 == 6){
                    str+=" <td>抢购页广告</td>";
                }
                str+='<td><img width="150px;" alt="" src="'+msg.pb.list[i].adverPath+'" height="50"></td>';
                str+=" <td>"+msg.pb.list[i].adverHref +"</td>";
                str+=" <td>"+msg.pb.list[i].adverSort +"</td>";
                if(msg.pb.list[i].userStatus==1){
                    str+="<td><a href='javascript:;'><span class='label label-success'>是</span></a></td>";
                }else{
                    str+="<td><a href='javascript:;'><span class='label label-default'>否</span></a></td>";
                }

                str+= '<td><div class="btn-group"><button type="button" class="btn btn-default" onclick="showAdver('+msg.pb.list[i].channelAdverId+')">编辑</button>'+
                '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">'+
                '<span class="caret"></span>'+
                '<span class="sr-only">Toggle Dropdown</span>'+
                '</button><ul class="dropdown-menu" role="menu"><li><a onclick="delAdverById(\'deletetempadverajax.htm?CSRFToken='+$(CSRFToken).val()+'&adverId='+msg.pb.list[i].channelAdverId+'\')" href="javascript:;">删除</a></li></ul></div></td>';
                str+="</tr>";
                $(adverHtml).append(str);

            }
            pageBeanAdver(msg,atId);
        }
    });
    //$.post("queryChannelAdverByPageBean.htm", { channelId: $(channelId).val(),CSRFToken:$(CSRFToken).val(),pageSize:pageSize,pageNo:pageNo,adverType:151,atId:atId},
    //    function(data){
    //        $(adverHtml).html("");
    //        if(data.pb.list.length==0){
    //            $(adverHtml).append('<tr><td colspan="7"><p class="text-center">暂无可用数据</p> </td></tr>');
    //        }
    //        for(var i=0;i<data.pb.list.length;i++){
    //            var str="<tr>";
    //            str+=" <td><input  name='adverId'  value="+data.pb.list[i].channelAdverId+" type='checkbox'></td>";
    //            if(data.pb.list[i].tempVal == 0 || data.pb.list[i].tempVal==null){
    //                str+=" <td>首页广告</td>";
    //            }else if(data.pb.list[i].tempVal == 1){
    //                str+=" <td>商品列表页广告</td>";
    //            }else if(data.pb.list[i].tempVal == 2){
    //                str+=" <td>搜索列表页广告</td>";
    //            }else if(data.pb.list[i].tempVal == 3){
    //                str+=" <td>资讯页广告</td>";
    //            }else if(data.pb.list[i].tempVal == 4){
    //                str+=" <td>其他。。。</td>";
    //            }else if(data.pb.list[i].tempVal == 5){
    //                str+=" <td>团购页广告</td>";
    //            }else if(data.pb.list[i].tempVal == 6){
    //                str+=" <td>抢购页广告</td>";
    //            }
    //            str+='<td><img width="150px;" alt="" src="'+data.pb.list[i].adverPath+'" height="50"></td>';
    //            str+=" <td>"+data.pb.list[i].adverHref +"</td>";
    //            str+=" <td>"+data.pb.list[i].adverSort +"</td>";
    //            if(data.pb.list[i].userStatus==1){
    //                str+="<td><a href='javascript:;'><span class='label label-success'>是</span></a></td>";
    //            }else{
    //                str+="<td><a href='javascript:;'><span class='label label-default'>否</span></a></td>";
    //            }
    //
    //            str+= '<td><div class="btn-group"><button type="button" class="btn btn-default" onclick="showAdver('+data.pb.list[i].channelAdverId+')">编辑</button>'+
    //            '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">'+
    //            '<span class="caret"></span>'+
    //            '<span class="sr-only">Toggle Dropdown</span>'+
    //            '</button><ul class="dropdown-menu" role="menu"><li><a onclick="delAdverById(\'deletetempadverajax.htm?CSRFToken='+$(CSRFToken).val()+'&adverId='+data.pb.list[i].channelAdverId+'\')" href="javascript:;">删除</a></li></ul></div></td>';
    //            str+="</tr>";
    //            $(adverHtml).append(str);
    //
    //        }
    //        pageBeanAdver(data,atId);
    //    });

}

function delAdverById(adverId){
     showAjaxDeleteConfirmAlertByTemp(adverId,2);
}
<!--页面导航分页-->
function pageBeanAdver(data,atId){
    $("#storeyAdverId").val("");
    var footStr='<div class="table_pagenav pull-right">';
    footStr+='<div class="meneame">';
    if(data.pb.pageNo==1){
        footStr+='<span class="disabled"> 上一页 </span>';
    }else{
        footStr+='<a  href="javascript:;" onclick="showAdverPage('+atId+','+(data.pb.pageNo-1)+')"> 上一页 </a>';
    }
    $(adverFootHtml).html("");
    for(var i = data.pb.startNo;i<data.pb.endNo+1;i++){
        if(i==data.pb.pageNo){
            footStr+=' <span class="current"> '+i+'</span>';
        }else{
            footStr+='<a href="javascript:;"  onclick="showAdverPage('+atId+','+i+')">'+i+'</a>';
        }
    }
    if(data.pb.pageNo==data.pb.totalPages){
        footStr+='<span class="disabled"> 下一页 </span>';
    }else{
        footStr+='   <a href="javascript:;" onclick="showAdverPage('+atId+','+(data.pb.pageNo+1)+')"> 下一页 </a>';
    }

    footStr+='</div>';
    footStr+='</div>';
    footStr+=' <div class="table_ctrl pull-left">';
    footStr+='<form role="form" class="form-inline">';
    footStr+=' <label class="control-label">每页显示：</label>';
    footStr+=' <select class="form-control" id="showAdverOpetion" onchange="showAdverPage('+atId+','+data.pb.pageNo+')">';
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

    $(adverFootHtml).append(footStr);
    cls();
}



function saveImg(obj){
    $("#imgPath").val(obj);
    var i=1;
    art.dialog.open('queryImageManageByPbAndCidForChoose.htm?CSRFToken='+$(CSRFToken).val()+'&size=10000', {
        lock: true,
        opacity:0.3,
        width: '900px',
        height: '620px',
        title: '选择图片'
    });
}

//图片回调 1:楼层图片 2楼层右侧导航图片 3添加模板广告 广告图片
function saveChoooseImage(url) {
    if(typeof (url) != 'string') {
        url = url[0];
    }
    if( $("#imgPath").val()==1){
        $("#upStoreyImg_y").attr("src",url);
        $("#upStoreyImg").val(url);
    }else if($("#imgPath").val()==2){

        $("#temp2Img_y").attr("src",url);
        $("#temp2Img").val(url);
    }else if($("#imgPath").val()==3){

        $("#adverPath_y").attr("src",url);
        $("#adverPath").val(url);
    }else if($("#imgPath").val()==4){

        $("#goodsCatePath_y").attr("src",url);
        $("#goodsCatePath").val(url);
    }

}

function submitAdver(){
    if(!$("#subForm_Adver").valid()) return;
    $.ajax({
        cache: true,
        type: "POST",
        url:"createtempadverajax.htm",
        data:$('#subForm_Adver').serialize(),// 你的formid
        async: false,
            success: function(data) {
            if(data==1){
                $(cls_adver).click();
                showTipAlert("添加成功！！");
                if($("#isAdver").val()==3){
                    showfloorAdverPage();
                }else if($("#isAdver").val()==1){
                    showAdverPage($(upAtId).val());
                }else if($("#isAdver").val()==2){
                    showClassifyBarAdverPage();
                }else if($("#isAdver").val()==4){
                    showClassifyParentBarAdverPage();
                }else if($("#isAdver").val()==5){
                    showTagAdverPage();
                }

            }else{
                $(cls_adver).click();
                showTipAlert("添加失败！！");
            }
        }
    });
}

/**查询模板广告详情**/
function showAdver(channelAdverId){
    $.post("showtempadverajax.htm", { channelAdverId: channelAdverId,CSRFToken:$(CSRFToken).val()},
        function(data) {

            if($("#storeyAdverId").val()!=""){
                $("#showAdverTitle").html("修改楼层广告");
            }else if($("#upAtId").val()!=""){
                $("#showAdverTitle").html("修改模板广告");

            }
            if($("#isAdver").val()==5){
                $("#showAdverTitle").html("修改标签广告");
            }
            if(data.userStatus==1){
                $(up_adverStatus1).prop("checked","checked");
            }else{
                $(up_adverStatus0).prop("checked","checked");
            }
            $("#adverPath_y").attr("src",data.adverPath);
            $("#adverPath").val(data.adverPath);
            $(up_adverSort).val(data.adverSort);
            $(up_adverHerf).val(data.adverHref);
            $(up_adver_temp2).val(data.temp2);
            $(up_adverName).val(data.adverName);
            $(advFlag).val(data.temp5);
            $(upChannelAdverId).val(data.channelAdverId);
            $(up_adverDes).val(data.des);
            $(subAdverButton).attr("onclick","updateAdver()");
            $('#addSlideAd').modal('show');
        });

}
/**修改模板广告**/
function updateAdver(){
    if(!$("#subForm_Adver").valid()) return;
    $.ajax({
        cache: true,
        type: "POST",
        url:"updatetempadverajax.htm",
        data:$('#subForm_Adver').serialize(),// 你的formid
        async: false,
        error: function(request) {
            showTipAlert("Connection error");
        },
        success: function(data) {
            if(data==1){
                $(cls_adver).click();
                if($("#isAdver").val()==3){
                    showfloorAdverPage();
                }else if($("#isAdver").val()==1){
                    showAdverPage($(upAtId).val());
                }else if($("#isAdver").val()==2){
                    showClassifyBarAdverPage();
                }else if($("#isAdver").val()==4){
                    showClassifyParentBarAdverPage();
                }else if($("#isAdver").val()==5){
                    showTagAdverPage();
                }
                showTipAlert("修改成功！！");

            }else{
                $(cls_adver).click();
                showTipAlert("修改失败！！");
            }
        }
    });
}
/***清空模板广告*/
function clsAdver(){
    clsBtnCss();
    $(up_adverStatus1).prop("checked","checked");
    $("#adverPath_y").attr("src","images/default_head.jpg");
    $(".adverPath").val("");
    $(up_adverSort).val("");
    $(up_adverHerf).val("");
    $(up_adver_temp2).val("");
    $(up_adverName).val("");
    if($("#isAdver")==1){
        $(advFlag).val(0);
    }
    $(upChannelAdverId).val("");
    $(up_adverDes).val("");
    $(subAdverButton).attr("onclick","submitAdver()");
    if($("#storeyAdverId").val()!=""){
        $("showAdverTitle").html("添加楼层广告");
    }else if($("#upAtId").val()!=""){
        $("showAdverTitle").html("添加模板广告");

    }
    if($("#isAdver").val()==5){
        $("showAdverTitle").html("添加标签广告");
    }
}
///**轮播大广告*/
///**新闻公告**/
//function showTempInfoType(){
//    $.post("getsystempdetailbyid.htm", { tempId: $(tempId).val(),CSRFToken:$(CSRFToken).val()},
//        function(data) {
//            $(infoTypeName).val(data.expFleid2);
//            $(infoTypeId).val(data.expFleid1);
//        });
//}

function updateInfoType(){
    alert("123");
    if($("#infoTypeName").val()==""){
        $("#infoTypeName").val($("#infoTypeId").find("option:selected").text());
    }
    $("#updateInfoType").submit();
    //$.ajax({
    //    cache: true,
    //    type: "POST",
    //    url: "updatetempinfotypeajax.htm",
    //    data: $('#updateInfoType').serialize(),// 你的formid
    //    async: false,
    //    error: function (request) {
    //        showTipAlert("Connection error");
    //    },
    //    success: function (data) {
    //        if (data == 1) {
    //            showTipAlert("修改成功！！");
    //            showTempCategory()
    //
    //        } else {
    //            showTipAlert("修改失败！！");
    //        }
    //    }
    //});
}
/**新闻公告**/

/**活动**/
function showTempInfoOPTag(){
    $.post("getsystempdetailbyid.htm", { tempId: $(tempId).val(),CSRFToken:$(CSRFToken).val()},
        function(data) {
            $(infoOPTagId).val(data.standby4);
            $(infoOPTagName).val(data.standby5);
        });
}

function updateTempInfoOPTag(){
    $.ajax({
        cache: true,
        type: "POST",
        url: "updatetempinfotypeajax.htm",
        data: $('#tempInfoOPTagForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            showTipAlert("Connection error");
        },
        success: function (data) {
            if (data == 1) {
                showTipAlert("修改成功！！");
                showTempCategory()

            } else {
                showTipAlert("修改失败！！");
            }
        }
    });
}
/**活动**/


/**页面说明**/
function shoMegawizard(pageNo){
    var pageSize=10;
    if($("#showMegOpetion").val()!=null){
        pageSize=$(showMegOpetion).val();
    }
    $.post("querymegawizardbypagebeanbyajax.htm", { tempId: $(tempId).val(),CSRFToken:$(CSRFToken).val(),pageSize:pageSize,pageNo:pageNo},
        function(data){
            var selectHtml="";
            for (var prop in data.types) {
                if (data.types.hasOwnProperty(prop)) {
                    selectHtml+="<option value="+prop+">" + data.types[prop]
                    "</option>"
                }
            }
            $(msgSelect).html(selectHtml);
            $(megawizardHtml).html("");

            if(data.pb.list.length==0){
                $(megawizardHtml).append('<tr><td colspan="5"><p class="text-center">暂无可用数据</p> </td></tr>');
            }
            for(var i=0;i<data.pb.list.length;i++){
                var str="<tr>";
                str+=" <td><input  name='id'  value="+data.pb.list[i].id+" type='checkbox'></td>";
                    for (var prop in data.types) {
                        if (data.types.hasOwnProperty(prop)) {
                           if(prop==data.pb.list[i].sort){
                               str+=" <td>"+prop+"</td>";
                           }
                        }
                    }

                str+=" <td>"+data.pb.list[i].megawizardName +"</td>";
                str+=" <td>"+timeObject(data.pb.list[i].createTime)+"</td>";

                str+= '<td><div class="btn-group"><button type="button" class="btn btn-default" ';
                if(data.pb.list[i].content!=""){
                    str+=' content="'+data.pb.list[i].content+'"';
                }
                str+= 'megawizardName='+ data.pb.list[i].megawizardName+' msgSort='+data.pb.list[i].sort+' onclick="updateMsg(this,'+data.pb.list[i].id+')">编辑</button>'+
                '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">'+
                    '<span class="caret"></span>'+
                    '<span class="sr-only">Toggle Dropdown</span>'+
                '</button><ul class="dropdown-menu" role="menu"><li><a onclick="delMsgById(\'delectmegawizardajax.htm?CSRFToken='+$(CSRFToken).val()+'&id='+data.pb.list[i].id+'\')" href="javascript:;">删除</a></li></ul></div></td>';
                str+="</tr>";
                $(megawizardHtml).append(str);
            }

            pageBeanMeg(data);
        });

}

function delMsgById(url){
    showAjaxDeleteConfirmAlertByTemp(url,4);
}

function updateMsg(obj,id){
    $(upMegawizardName).val($(obj).attr("megawizardName"));
    $(upMsgContent).val($(obj).attr("content"));
    $(msgSelect).val($(obj).attr("msgSort"));
    $(msgTitle).html("修改页面说明");
    $(msgId).val(id);
    $(msgFormId).val("updatemegawizardajax.htm");
    $('#addPageIntro').modal('show');
}

function pageBeanMeg(data){
    var footStr='<div class="table_pagenav pull-right">';
    footStr+='<div class="meneame">';
    if(data.pb.pageNo==1){
        footStr+='<span class="disabled"> 上一页 </span>';
    }else{
        footStr+='<a  href="javascript:;" onclick="shoMegawizard('+(data.pb.pageNo-1)+')"> 上一页 </a>';
    }
    $(msgFootHtml).html("");
    for(var i = data.pb.startNo;i<data.pb.endNo+1;i++){
        if(i==data.pb.pageNo){
            footStr+=' <span class="current"> '+i+'</span>';
        }else{
            footStr+='<a href="javascript:;"  onclick="shoMegawizard('+i+')">'+i+'</a>';
        }
    }
    if(data.pb.pageNo==data.pb.totalPages){
        footStr+='<span class="disabled"> 下一页 </span>';
    }else{
        footStr+='   <a href="javascript:;" onclick="shoMegawizard('+(data.pb.pageNo+1)+')"> 下一页 </a>';
    }

    footStr+='</div>';
    footStr+='</div>';
    footStr+=' <div class="table_ctrl pull-left">';
    footStr+='<form role="form" class="form-inline">';
    footStr+=' <label class="control-label">每页显示：</label>';
    footStr+=' <select class="form-control" id="showMegOpetion" onchange="shoMegawizard('+data.pb.pageNo+')">';
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

    $(msgFootHtml).append(footStr);

}

function subMsgForm(){
    if(!$("#upMsgForm").valid()) return;
    $.ajax({
        cache: true,
        type: "POST",
        url: $(msgFormId).val(),
        data: $('#upMsgForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            showTipAlert("Connection error");
        },
        success: function (data) {
            if (data == 1) {
                if($(msgId).val()!=null&&$(msgId).val()!=""){
                    showTipAlert("修改成功！！");
                }else{
                    showTipAlert("添加成功！！");
                }
                $(msgButtonCls).click();
                shoMegawizard();

            } else {
                if($(msgId).val()!=""){
                    showTipAlert("修改失败！！");
                }else{
                    showTipAlert("添加失败！！");
                }
            }
        }
    });
}

function clsMsg(){
    clsBtnCss();
    $(msgFormId).val("newmegawizardajax.htm");
    $(upMegawizardName).val("");
    $(upMsgContent).val("");
    $(msgSelect).val(0);
    $(msgTitle).html("添加页面说明");
    $(msgId).val("");
}
/**页面说明**/


/**热门搜索**/
function showHotSearch(pageNo){
    var pageSize=10;
    if($("#showHotOpetion").val()!=null){
        pageSize=$(showHotOpetion).val();
    }
    $.post("queryHotPageSize.htm", { channelId: $(channelId).val(),pageSize:pageSize,pageNo:pageNo},
        function(data){
            $(hotHtml).html("");
            if(data.pb.list.length==0){
                $(hotHtml).append('<tr><td colspan="5"><p class="text-center">暂无可用数据</p> </td></tr>');
            }
            for(var i=0;i<data.pb.list.length;i++){
                var str="<tr>";
                str+=" <td><input  name='hots'  value="+data.pb.list[i].hotSearchId+" type='checkbox'></td>";
                str+=" <td>"+data.pb.list[i].keyword +"</td>";
                str+=" <td>"+data.pb.list[i].sort +"</td>";
                str+=" <td>"+timeObject(data.pb.list[i].updateDate)+"</td>";

                str+= '<td><div class="btn-group"><button type="button" class="btn btn-default" key-word="'+data.pb.list[i].keyword+'" onclick="updateHotSearch(this,'+data.pb.list[i].sort +','+data.pb.list[i].hotSearchId +')">编辑</button>'+
                '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">'+
                '<span class="caret"></span>'+
                '<span class="sr-only">Toggle Dropdown</span>'+
                '</button><ul class="dropdown-menu" role="menu"><li><a onclick="delHotById(\'removehotbykeyajax.htm?CSRFToken='+$(CSRFToken).val()+'&hotSearchId='+data.pb.list[i].hotSearchId+'\')" href="javascript:;">删除</a></li></ul></div></td>';
                str+="</tr>";
                $(hotHtml).append(str);
            }
            pageBeanHot(data);
        });
}

function delHotById(hotSearchId){
    showAjaxDeleteConfirmAlertByTemp(hotSearchId,3);
}

function pageBeanHot(data){
    var footStr='<div class="table_pagenav pull-right">';
    footStr+='<div class="meneame">';
    if(data.pb.pageNo==1){
        footStr+='<span class="disabled"> 上一页 </span>';
    }else{
        footStr+='<a  href="javascript:;" onclick="showHotSearch('+(data.pb.pageNo-1)+')"> 上一页 </a>';
    }
    $(hotFootHtml).html("");
    for(var i = data.pb.startNo;i<data.pb.endNo+1;i++){
        if(i==data.pb.pageNo){
            footStr+=' <span class="current"> '+i+'</span>';
        }else{
            footStr+='<a href="javascript:;"  onclick="showHotSearch('+i+')">'+i+'</a>';
        }
    }
    if(data.pb.pageNo==data.pb.totalPages){
        footStr+='<span class="disabled"> 下一页 </span>';
    }else{
        footStr+='   <a href="javascript:;" onclick="showHotSearch('+(data.pb.pageNo+1)+')"> 下一页 </a>';
    }

    footStr+='</div>';
    footStr+='</div>';
    footStr+=' <div class="table_ctrl pull-left">';
    footStr+='<form role="form" class="form-inline">';
    footStr+=' <label class="control-label">每页显示：</label>';
    footStr+=' <select class="form-control" id="showHotOpetion" onchange="showHotSearch('+data.pb.pageNo+')">';
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

    $(hotFootHtml).append(footStr);

}

function clsHot(){
    clsBtnCss();
    $(upHotKeyword).val("");
    $(upHotSort).val("");
    $(upHotSearchId).val("");
    $(subHotId).val("createhotselectiveajax.htm");
    $(hotTitle).html("添加热门搜索");
}

function updateHotSearch(obj,sort,searchId){
    $(upHotKeyword).val($(obj).attr("key-word"));
    $(upHotSearchId).val(searchId);
    $(upHotSort).val(sort);
    $(subHotId).val("modifyhotajax.htm");
    $(hotTitle).html("修改热门搜索");
    $('#addHotSearch').modal('show');
}


function submitHotSearch(){
    if(!$("#upHotForm").valid()) return;
    $.ajax({
        cache: true,
        type: "POST",
        url: $(subHotId).val(),
        data: $('#upHotForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            showTipAlert("Connection error");
        },
        success: function (data) {
            if (data == 1) {
                if($(upHotSearchId).val()!=null&&$(upHotSearchId).val()!=""){
                    showTipAlert("修改成功！！");
                }else{
                    showTipAlert("添加成功！！");
                }
                $(upHotClsBtn).click();
                showHotSearch();

            } else {
                if($(upHotSearchId).val()!=""){
                    showTipAlert("修改失败！！");
                }else{
                    showTipAlert("添加失败！！");
                }
            }
        }
    });
}
/**热门搜索**/


//转换时间格式
function timeObject(obj){
    var date = "/Date(" +notNull(obj)+")/";
    var time = new Date(parseInt(date.replace("/Date(", "").replace(")/", ""), 10));
    var result = time.getFullYear() + "-" + (time.getMonth() + 1 < 10 ? "0" + (time.getMonth() + 1) : time.getMonth() + 1) + "-" + (time.getDate() < 10 ? "0" + time.getDate() : time.getDate()) + " " + (time.getHours() < 10 ? "0" + time.getHours() : time.getHours()) + ":" + (time.getMinutes() < 10 ? "0" + time.getMinutes() : time.getMinutes())+ ":" + (time.getSeconds() < 10 ? "0" + time.getSeconds() : time.getSeconds());
    return result;
}

//判断数据是否为空为空返回“”
function notNull(obj){
    if(obj != null && obj != undefined){
        return obj;
    }else{
        return "";
    }
}

//热销推荐
function showStoreyGoods(pageNo){
    $("#storeyTitle").html("添加热销推荐商品");
    $("#storeyTagId").val("");
    $("#upStoreGoodsTempId").val($(tempId).val());
    var pageSize=10;
    if($("#showSotreyOpetion").val()!=null){
        pageSize=$(showSotreyOpetion).val();
    }
    $.post("querychannelgoodsbypagebeanajax.htm", { tempId: $(tempId).val(),CSRFToken:$(CSRFToken).val(),pageSize:pageSize,pageNo:pageNo},

        function(data){
            $(storeyGoodsHtml).html("");
            if(data.pb.list.length==0){
                $(storeyGoodsHtml).append('<tr><td colspan="8"><p class="text-center">暂无可用数据</p> </td></tr>');
            }
                for(var i=0;i<data.pb.list.length;i++){
                    var str="<tr>";
                    str+=" <td><input  name='storeyGoodsIds'  value="+data.pb.list[i].channelStoreyGoodsproductId+" type='checkbox'></td>";
                    str+=' <td>' +
                    '<div class="data_item"><img width="150px;" alt="" src="'+data.pb.list[i].goodsproductImgsrc+'" height="60px" >' +
                    '<p title="'+data.pb.list[i].goodsproductName +'">'+ data.pb.list[i].goodsproductName.substring(0,25)+'</p>  <p class="text-muted">'+data.pb.list[i].goodsproductPrice.toFixed(2) +'</p></div></td>';
                    str+=" <td>"+data.pb.list[i].goodsproductNo +"</td>";
                    str+=" <td>"+data.pb.list[i].sort +"</td>";
                    str+= '<td><div class="btn-group"><button type="button" class="btn btn-default" onclick="updateStorey('+data.pb.list[i].channelStoreyGoodsproductId+')">编辑</button>'+
                    '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">'+
                    '<span class="caret"></span>'+
                    '<span class="sr-only">Toggle Dropdown</span>'+
                    '</button><ul class="dropdown-menu" role="menu"><li><a onclick="delStoeryGoods(\'deleteChannelGoodsAjax.htm?CSRFToken='+$(CSRFToken).val()+'&storeyGoodsIds='+data.pb.list[i].channelStoreyGoodsproductId+'\',5)" href="javascript:;">删除</a></li></ul></div></td>';
                    str+="</tr>";
                $(storeyGoodsHtml).append(str);
            }
            pageBeanStorey(data);
        });
}

function updateStorey(channelStoreyGoodsproductId){
    $.post("showchannelgoodsajax.htm?CSRFToken="+$(CSRFToken).val(), { channelStoreyGoodsproductId:channelStoreyGoodsproductId },
        function(data){
            $(up_goodsproductImgsrc).attr("src",data.goodsproductImgsrc);
            $(up_goodsproductImgsrcValue).val(data.goodsproductImgsrc);
            $(up_goodsproductPrice).val(data.goodsproductPrice);
            $(up_goodsproductName).val(data.goodsproductName);
            $(up_goodsproductNo).val(data.goodsproductNo);
            $(up_goodsSort).val(data.sort);
            $(up_channelStoreyGoodsproductId).val(channelStoreyGoodsproductId);
            $(up_channelStoreyGoodsProductId1).val(data.goodsproductId);
            if($("#storeyTagId").val()!=""){
                $("#storeyTitle").html("修改标签商品");
            }else{
                $(goodsListTitle).html("修改热销推荐商品");
            }
            $('#upStoreyForm').val("updatechannelgoodsajax.htm");
        });
    $('#addHotGoods').modal('show');
}
function delStoeryGoods(url,status){
    showAjaxDeleteConfirmAlertByTemp(url,status);
}

function pageBeanStorey(data){
    var footStr='<div class="table_pagenav pull-right">';
    footStr+='<div class="meneame">';
    if(data.pb.pageNo==1){
        footStr+='<span class="disabled"> 上一页 </span>';
    }else{
        footStr+='<a  href="javascript:;" onclick="showStoreyGoods('+(data.pb.pageNo-1)+')"> 上一页 </a>';
    }
    $(storeyGoodsFootHtml).html("");
    for(var i = data.pb.startNo;i<data.pb.endNo+1;i++){
        if(i==data.pb.pageNo){
            footStr+=' <span class="current"> '+i+'</span>';
        }else{
            footStr+='<a href="javascript:;"  onclick="showStoreyGoods('+i+')">'+i+'</a>';
        }
    }
    if(data.pb.pageNo==data.pb.totalPages){
        footStr+='<span class="disabled"> 下一页 </span>';
    }else{
        footStr+='   <a href="javascript:;" onclick="showStoreyGoods('+(data.pb.pageNo+1)+')"> 下一页 </a>';
    }

    footStr+='</div>';
    footStr+='</div>';
    footStr+=' <div class="table_ctrl pull-left">';
    footStr+='<form role="form" class="form-inline"">';
    footStr+=' <label class="control-label ">每页显示：</label>';
    footStr+=' <select class="form-control" id="showSotreyOpetion" onchange="showStoreyGoods('+data.pb.pageNo+')">';
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

    $(storeyGoodsFootHtml).append(footStr);
}

function  showStoreyGoodsList(){
    doByAjax();
    $('#addGoodsChoose').modal('show');
    if($("#storeyTagId").val()!=""){
        $("#storeyTitle").html("添加标签商品");
    }else{
        $(goodsListTitle).html("添加热销推荐商品");
    }

    $(singlButton).attr("onclick","checkGoodsId()");

}

function checkGoodsId(){
    var radionObj=document.getElementsByName("floorgoods");
    for(var i = 0; i < radionObj.length; i ++) {
        if (radionObj[i].checked) {
            str = radionObj[i];
            break;
        }
    }
    $(up_goodsproductImgsrc).attr("src",$(str).attr("goodsImg"));
    $(up_goodsproductImgsrcValue).val($(str).attr("goodsImg"));
    $(up_goodsproductPrice).val($(str).attr("goodsPrice"));
    $(up_StoreyChannelGoodsProductId).val($(str).val());
    $(up_goodsproductName).val($(str).attr("goodsName"));
    $(up_goodsproductNo).val($(str).attr("goodsInfoNo"));
    $(singlQ).click();
}

function clsStorey(){
    clsBtnCss();
    $(up_goodsproductImgsrc).attr("src","");
    $(up_goodsproductImgsrcValue).val("");
    $(up_goodsproductPrice).val("");
    $(up_goodsproductName).val("");
    $(up_goodsproductNo).val("");
    $(up_goodsSort).val("");
    $(up_channelStoreyGoodsproductId).val("");
    $(up_channelStoreyGoodsProductId1).val("");
    if($("#storeyTagId").val()!=""){
        $("#storeyTitle").html("添加标签商品");
    }else{
        $(goodsListTitle).html("添加热销推荐商品");
    }
    $('#upStoreyForm').val("createchannelgoodsajax.htm");
}

function subStorey(){
    if(!$("#storeyGoodsForm").valid()) return;
    $.ajax({
        cache: true,
        type: "POST",
        url: $(upStoreyForm).val(),
        data: $(storeyGoodsForm).serialize(),// 你的formid
        async: false,
        error: function (request) {
            showTipAlert("Connection error");
        },
        success: function (data) {
            if (data == 1) {
                if($(up_channelStoreyGoodsproductId).val()!=null&&$(up_channelStoreyGoodsproductId).val()!=""){
                    showTipAlert("修改成功！！");
                }else{
                    showTipAlert("添加成功！！");
                }
                $(clsStoreButton).click();
                if($("#storeyTagId").val()!=""){
                    showTagyGoods();
                }else{
                    showStoreyGoods();
                }

            } else {
                if($(up_channelStoreyGoodsproductId).val()!=""){
                    showTipAlert("修改失败！！");
                }else{
                    showTipAlert("添加失败！！");
                }
            }
        }
    });
}

function doByAjax(pageNo) {
    var productName =$("#searchText").val();
    var proHtml = "";
    if(productName != "") {
        proHtml="&productName="+productName;
    }else{
        proHtml="";
    }
    if(pageNo==null){
        pageNo=1;
    }
    var token = $("#csrf_token").val();
    $.get("queryProductForCoupon.htm?CSRFToken="+$(CSRFToken).val()+proHtml+"&pageNo="+pageNo+"&pageSize=5",

        function(data){
            if(data != "") {
                $("#singlHtml").html("");
                var str ="";
                for(var i=0;i<data.list.length;i++){
                    str+="<tr>";
                    str +=' <td><input value="'+data.list[i].goodsInfoId+'" name="floorgoods" type="radio"   goodsImg="'+data.list[i].goodsInfoImgId+'" goodsPrice="'+data.list[i].goodsInfoPreferPrice+'" goodsName="'+data.list[i].goodsInfoName+'" goodsInfoNo="'+data.list[i].goodsInfoItemNo+'"></td>';

                    str +=' <td><img height="50" alt="" src="'+data.list[i].goodsInfoImgId+'"></td>';
                    str +=' <td>'+data.list[i].goodsInfoName+' </td>';
                    str +=' <td>'+data.list[i].goodsInfoItemNo+' </td>';
                    str +=' <td>'+data.list[i].catName+' </td>';
                    str +=' <td>'+data.list[i].brandName+' </td>';
                    str+="</tr>";
                }
                $(singlHtml).append(str);
            }
            pageBeanSingl(data);
        });
}

function pageBeanSingl(data) {
    var footStr = '<div class="table_pagenav pull-right">';
    footStr += '<div class="meneame">';
    if (data.pageNo == 1) {
        footStr += '<span class="disabled"> 上一页 </span>';
    } else {
        footStr += '<a  href="javascript:;" onclick="doByAjax(' + (data.pageNo - 1) + ')"> 上一页 </a>';
    }
    $(singlFootHtml).html("");
    for (var i = data.startNo; i < data.endNo + 1; i++) {
        if (i == data.pageNo) {
            footStr += ' <span class="current"> ' + i + '</span>';
        } else {
            footStr += '<a href="javascript:;"  onclick="doByAjax(' + i + ')">' + i + '</a>';
        }
    }
    if (data.pageNo == data.totalPages) {
        footStr += '<span class="disabled"> 下一页 </span>';
    } else {
        footStr += '   <a href="javascript:;" onclick="doByAjax(' + (data.pageNo + 1) + ')"> 下一页 </a>';
    }
    footStr += '</div>';
    footStr += '</div>  <div class="clr"></div>';

    $(singlFootHtml).append(footStr);
}




//热门搜索


//楼层设置
function showTempStorey(pageNo) {
    var pageSize = 10;
    $("#up_brand_storeyTagId").val("");
    $('.floor_ad_set').hide();
    $('.floor_good_set').hide();
    $('.floor_label_set').hide();
    $('.floor_brand_set').hide();
    $('.floor_set').show();
    $('.floor_label_brand_set').hide();
    if ($("#showTempStoreyOpetion").val() != null) {
        pageSize = $(showTempStoreyOpetion).val();
    }
    $.post("queryChannelStoreyByPageBean.htm", {
            channelId: $("#channelId").val(),
            pageSize: pageSize,
            pageNo: pageNo
        },
        function (data) {
            $(showTempStoreyHtml).html("");
            if (data.pb.list.length == 0) {
                $(showTempStoreyHtml).append('<tr><td colspan="6"><p class="text-center">暂无可用数据</p> </td></tr>');
            }
            for (var i = 0; i < data.pb.list.length; i++) {
                var str = "<tr>";
                str += " <td><input  name='storeyId'  value=" + data.pb.list[i].channelStoreyId + " type='checkbox'></td>";
                str += " <td>" + data.pb.list[i].storeyName + "</td>";
                str += " <td><img  src='" + data.pb.list[i].storeyImg + "' height='50' width='150'> </td>";
                str += " <td>" + data.pb.list[i].floorId + "F</td>";
                if (data.pb.list[i].userStatus == 1) {
                    str += "<td><a href='javascript:;'><span class='label label-success'>是</span></a></td>";
                } else {
                    str += "<td><a href='javascript:;'><span class='label label-default'>否</span></a></td>";
                }

                str += '<td><div class="btn-group">';
                str += '<button type="button" class="btn btn-default" onclick="showtempstoreynew(' + data.pb.list[i].channelStoreyId + ')">编辑楼层模板</button>';
                str += '  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="caret"></span> <span class="sr-only">Toggle Dropdown</span></button>';
                str += ' <ul class="dropdown-menu" role="menu">';
                str += '  <li><a href="javascript:;" onclick="showStoreyList(' + data.pb.list[i].channelStoreyId + ')">设置楼层商品</a><input type="hidden" id="hycStoreId" value="'+data.pb.list[i].channelStoreyId+'"/></li>';
                if (data.pb.list[i].showAdver == 1) {
                    str += '  <li><a href="javascript:;" onclick="showfloorAdver(' + data.pb.list[i].channelStoreyId + ')" onclick="">设置楼层广告</a></li>';
                }
                if (data.pb.list[i].showTag == 1) {
                    str += '  <li><a href="javascript:;" onclick="showStoreyTag(' + data.pb.list[i].channelStoreyId + ')">设置楼层标签</a></li>';
                }
                if (data.pb.list[i].showTrademark == 1) {
                    str += '  <li><a href="javascript:;" onclick="setStoreyTrademark(' + data.pb.list[i].channelStoreyId + ')">设置楼层品牌</a></li>';
                }

                str += ' </ul>';
                str += ' </div></td></tr>';
                $(showTempStoreyHtml).append(str);

            }
            pageBeanTempStorey(data);
            $("#goodsCate").html("")
            $("#floorId").html("");
            for (var i = 0; i < data.goodsCate.length; i++) {
                $("#goodsCate").append("<option value=" + data.goodsCate[i].catId + ">" + data.goodsCate[i].catName + "</option>");
            }
            for(var i =0;i<20;i++){
                $("#floorId").append("<option value=" + (i + 1) + ">" + (i + 1) + "F</option>");
            }
        });
}

function showtempstoreynew(storeId){
    $.post("showtempstoreynew.htm", { storeyId: storeId,CSRFToken:$(CSRFToken).val()},
        function(data) {
            $("#store_model_title").html("修改模板楼层");
            $("#storeyName").val(data.storeyName);
            $("#storeyFormUrl").val("updatetempstoreyajax.htm");
            $("#upChannelStoreyId").val(data.channelStoreyId);
            $("#upStoreyImg").val(data.storeyImg);
            $("#upStoreyImg_y").attr("src",data.storeyImg);
            $("#temp2Img").val(data.temp2);
            $("#temp2Img_y").attr("src",data.temp2);
            $("#storeyHref").val(data.storeyImgHref);
            $("#floorId").val(data.floorId);
            $("#goodsCate").val(data.goodsCatId);
            if(data.showAdver==1) {
                $("#up_showImg1").prop("checked", "checked")
            }else{
                $("#up_showImg0").prop("checked", "checked")
            }
            if(data.showTag==1) {
                $("#showTag1").prop("checked","checked")
            }else{
                $("#showTag0").prop("checked","checked")
            }
            if(data.showTrademark==1) {
                $("#showTrademark1").prop("checked","checked")
            }else{
                $("#showTrademark0").prop("checked","checked")
            }
            if(data.userStatus==1) {
                $("#userStatus1").prop("checked","checked")
            }else{
                $("#userStatus0").prop("checked","checked")
            }
            $("#flag").attr("attr_flag",2);
            $('#addFloor').modal('show');
        });

}

function pageBeanTempStorey(data){
        var footStr='<div class="table_pagenav pull-right">';
        footStr+='<div class="meneame">';
        if(data.pb.pageNo==1){
            footStr+='<span class="disabled"> 上一页 </span>';
        }else{
            footStr+='<a  href="javascript:;" onclick="showTempStorey('+(data.pb.pageNo-1)+')"> 上一页 </a>';
        }
        $(showTempStoreyFootHtml).html("");
        for(var i = data.pb.startNo;i<data.pb.endNo+1;i++){
            if(i==data.pb.pageNo){
                footStr+=' <span class="current"> '+i+'</span>';
            }else{
                footStr+='<a href="javascript:;"  onclick="showTempStorey('+i+')">'+i+'</a>';
            }
        }
        if(data.pb.pageNo==data.pb.totalPages){
            footStr+='<span class="disabled"> 下一页 </span>';
        }else{
            footStr+='   <a href="javascript:;" onclick="showTempStorey('+(data.pb.pageNo+1)+')"> 下一页 </a>';
        }

        footStr+='</div>';
        footStr+='</div>';
        footStr+=' <div class="table_ctrl pull-left">';
        footStr+='<form role="form" class="form-inline"">';
        footStr+=' <label class="control-label ">每页显示：</label>';
        footStr+=' <select class="form-control" id="showTempStoreyOpetion" onchange="showTempStorey('+data.pb.pageNo+')">';

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

        $(showTempStoreyFootHtml).append(footStr);
    }


function clsStoreForm(){
    clsBtnCss();
    $("#store_model_title").html("添加模板楼层");
    $("#storeyName").val("");
    $("#upStoreyImg").val("");
    $("#upStoreyImg_y").attr("src","");
    $("#temp2Img").val("");
    $("#temp2Img_y").attr("src","");
    $("#storeyHref").val("");
    $("#up_showImg1").prop("checked","checked")
    $("#showTag1").prop("checked","checked")
    $("#showTrademark1").prop("checked","checked")
    $("#userStatus1").prop("checked","checked")
    $("#storeyFormUrl").val("createtempstoreyajax.htm");
}


//保存频道楼层 弹出层
function addChannel(){

    $('#addFloor').modal('show');
    $("#flag").attr("attr_flag",1);
}

// 保存-更新楼层 方法
function subStoreyForm(obj){
    if(!$("#up_storeyForm").valid()) return;
    //$("#storeyFormUrl").val("createChannelStorey.htm");

    if($(obj).attr("attr_flag")==1){
        //保存
        $("#upChannelStoreyId").val('');
    }

    $.ajax({
        type: "POST",
        url: $("#storeyFormUrl").val(),
        data: $("#up_storeyForm").serialize(),// 你的formid
        async: false,
        error: function (request) {
            showTipAlert("Connection error");
        },
        success: function (msg) {
            if (msg == 1) {
                if($("#upChannelStoreyId").val()!=null&&$("#upChannelStoreyId").val()!=""){
                    showTipAlert("修改成功！！");
                    $("#storeyFormUrl").val("createChannelStoreyForChannel.htm");
                    $("#upChannelStoreyId").val("");
                }else{
                    showTipAlert("添加成功！！");
                }
                $(clsStoreyBtn).click();
                showTempStorey();
            } else {
                if($(upChannelStoreyId).val()!=""){
                    showTipAlert("修改失败！！");
                }else{
                    showTipAlert("添加失败！！");
                }
            }
        }
    });
}
//楼层设置
//保存当前的楼层ID
var floorId  = null;

//楼层商品设置
function showStoreyList(obj){
    floorId=obj;
    $("#storeyId").val(obj);
    $("#storeyTagId").val("");
    $('.floor_good_set').show();
    $('.floor_set').hide();
    showChannelStoreyGoods();
}

function showChannelStoreyGoods(pageNo){
    $("#storeyId").val(floorId);
    var pageSize=10;
    if($("#showChannelSotreyOpetion").val()!=null){
        pageSize=$(showChannelSotreyOpetion).val();
    }
    $.post("querychannelstoreygoodsbypagebeanajax.htm", { channelId: $(channelId).val(),tempId: $(tempId).val(),CSRFToken:$(CSRFToken).val(),pageSize:pageSize,pageNo:pageNo ,storeyId:$("#storeyId").val()},

        function(data){
            $(channelStoreyGoodsHtml).html("");
            if(data.pb.list.length==0){
                $(channelStoreyGoodsHtml).append('<tr><td colspan="8"><p class="text-center">暂无可用数据</p> </td></tr>');
            }
            for(var i=0;i<data.pb.list.length;i++){
                var str="<tr>";
                str+=" <td><input  name='storeyGoodsId'  value="+data.pb.list[i].channelStoreyGoodsproductId+" type='checkbox'></td>";
                if(data.pb.list[i].temp3==null||data.pb.list[i].temp3==0){
                    str+=' <td>排行榜</td>';
                }else  if(data.pb.list[i].temp3==1){
                    str+=' <td>最新降价</td>';
                }else  if(data.pb.list[i].temp3==2){
                    str+=' <td>新品</td>';
                }else  if(data.pb.list[i].temp3==3){
                    str+=' <td>类型4</td>';
                }else  if(data.pb.list[i].temp3==4){
                    str+=' <td>类型5</td>';
                }else  if(data.pb.list[i].temp3==5){
                    str+=' <td>类型6</td>';
                }
                str+=' <td>' +
                '<div class="data_item"><img width="150px;" alt="" src="'+data.pb.list[i].goodsproductImgsrc+'" height="60px" >' +
                '<p title="'+data.pb.list[i].goodsproductName +'">'+ data.pb.list[i].goodsproductName.substring(0,25)+'</p>  <p class="text-muted">'+data.pb.list[i].goodsproductPrice.toFixed(2) +'</p></div></td>';
                str+=" <td>"+data.pb.list[i].goodsproductNo +"</td>";
                str+=" <td>"+data.pb.list[i].sort +"</td>";
                str+= '<td><div class="btn-group"><button type="button" class="btn btn-default" onclick="updateChannelStorey('+data.pb.list[i].channelStoreyGoodsproductId+')">编辑</button>'+
                '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">'+
                '<span class="caret"></span>'+
                '<span class="sr-only">Toggle Dropdown</span>'+
                '</button><ul class="dropdown-menu" role="menu"><li><a onclick="delStoeryGoods(\'deleteChannelGoodsAjax.htm?CSRFToken='+$(CSRFToken).val()+'&storeyGoodsIds='+data.pb.list[i].channelStoreyGoodsproductId+'\',7)" href="javascript:;">删除</a></li></ul></div></td>';
                str+="</tr>";
                $(channelStoreyGoodsHtml).append(str);
            }
            pageBeanChannelStorey(data);
        });
}


function pageBeanChannelStorey(data){
    var footStr='<div class="table_pagenav pull-right">';
    footStr+='<div class="meneame">';
    if(data.pb.pageNo==1){
        footStr+='<span class="disabled"> 上一页 </span>';
    }else{
        footStr+='<a  href="javascript:;" onclick="showChannelStoreyGoods('+(data.pb.pageNo-1)+')"> 上一页 </a>';
    }
    $(channelStoreyGoodsFootHtml).html("");
    for(var i = data.pb.startNo;i<data.pb.endNo+1;i++){
        if(i==data.pb.pageNo){
            footStr+=' <span class="current"> '+i+'</span>';
        }else{
            footStr+='<a href="javascript:;"  onclick="showChannelStoreyGoods('+i+')">'+i+'</a>';
        }
    }
    if(data.pb.pageNo==data.pb.totalPages){
        footStr+='<span class="disabled"> 下一页 </span>';
    }else{
        footStr+='   <a href="javascript:;" onclick="showChannelStoreyGoods('+(data.pb.pageNo+1)+')"> 下一页 </a>';
    }

    footStr+='</div>';
    footStr+='</div>';
    footStr+=' <div class="table_ctrl pull-left">';
    footStr+='<form role="form" class="form-inline"">';
    footStr+=' <label class="control-label ">每页显示：</label>';
    footStr+=' <select class="form-control" id="showChannelSotreyOpetion" onchange="showChannelStoreyGoods('+data.pb.pageNo+')">';
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

    $(channelStoreyGoodsFootHtml).append(footStr);
}


function updateChannelStorey(channelStoreyGoodsproductId){
    $.post("showchannelgoodsajax.htm?CSRFToken="+$(CSRFToken).val(), { channelStoreyGoodsproductId:channelStoreyGoodsproductId },
        function(data){
            $(up_goodsproductImgsrc1).attr("src",data.goodsproductImgsrc);
            $(up_goodsproductImgsrcValue1).val(data.goodsproductImgsrc);
            $(up_goodsproductPrice1).val(data.goodsproductPrice);
            $(up_goodsproductName1).val(data.goodsproductName);
            $(up_goodsproductNo1).val(data.goodsproductNo);
            $(up_goodsSort1).val(data.sort);
            $(up_channelStoreyGoodsproductId).val(channelStoreyGoodsproductId);
            $(floorGoods).html("修改楼层商品");
            $(floorGoodsType).val(data.temp3);
            $(upStoreyForm).val("updatechannelgoodsajax.htm");
        });
    //$('#floorGoods').html("修改楼层商品");
    $('#addFloorGoods').modal('show');
}


function  showChanneelStoreyGoodsList(){
    doByAjax();
    $('#addGoodsChoose').modal('show');
    $(goodsListTitle).html("添加楼层商品");
    $(singlButton).attr("onclick","checkFloorGoodsId()");

}

function checkFloorGoodsId(){
    var radionObj=document.getElementsByName("floorgoods");
    for(var i = 0; i < radionObj.length; i ++) {
        if (radionObj[i].checked) {
            str = radionObj[i];
            break;
        }
    }
    $(up_goodsproductImgsrc1).attr("src",$(str).attr("goodsImg"));
    $(up_goodsproductImgsrcValue1).val($(str).attr("goodsImg"));
    $(up_channelStoreyGoodsproductId1).val($(str).val());
    //$("#up_goodsproductId").val($(str).val());
    $(up_goodsproductPrice1).val($(str).attr("goodsPrice"));
    $(up_goodsproductName1).val($(str).attr("goodsName"));
    $(up_goodsproductNo1).val($(str).attr("goodsInfoNo"));
    $(singlQ).click();
}


function clsFloorStorey(){
    clsBtnCss();
    $(up_goodsproductImgsrc1).attr("src","");
    $(up_goodsproductImgsrcValue1).val("");
    $(up_goodsproductPrice1).val("");
    $(up_goodsproductName1).val("");
    $(up_goodsproductNo1).val("");
    $(storeyId).val("");
    $(up_channelStoreyGoodsproductId1).val("");
    $(up_goodsSort1).val("");
    $(up_channelStoreyGoodsproductId1).val("");
    $(floorGoods).html("添加楼层商品");
    $(floorGoodsType).val(0);
    $('#upStoreyForm').val("createchannelgoodsajax.htm");
}

function subFloorStorey(){
    if(!$("#FloorStoreyGoodsForm").valid()) return;
    $.ajax({
        cache: true,
        type: "POST",
        url: $("#upStoreyForm").val(),
        data: $("#FloorStoreyGoodsForm").serialize(),// 你的formid
        async: false,
        error: function (request) {
            showTipAlert("Connection error");
        },
        success: function (data) {
            if (data == 1) {
                if($("#floorGoods").text() == "添加楼层商品"){
                    showTipAlert("添加成功！！");
                }else{
                    showTipAlert("修改成功！！");
                }
                $(floorGoodsBtn).click();
                showChannelStoreyGoods();
            } else {
                if($("#floorGoods").text() == "添加楼层商品"){
                    showTipAlert("添加失败！！");
                }else{
                    showTipAlert("修改失败！！");
                }
            }
        }
    });
}
//楼层商品设置

//楼层广告设置

function showfloorAdver(obj){
    $("#storeyAdverId").val(obj);
    $('.floor_ad_set').show();
    $('.floor_set').hide();

    $("#showFloorAdver").hide();
    $("#showAdverTitle").html("添加楼层广告");
    $("#up_floorId").val(obj);
    $("#up_storeyAdverTempId").val("");
    $("#upAtId").val(161);
    $("#up_temp3").val(0);
    $("#up_temp3").val(0);
    $("#up_temp1").val("");
    $("#storeyTagAdverId").val("");
    $("#isAdver").val(3);
    showfloorAdverPage();
}
function showfloorAdverPage(pageNo){
    var pageSize=10;
    if($("#showFloorAdverOpetion").val()!=null){
        pageSize=$(showFloorAdverOpetion).val();
    }
    $.post("querychannelstoreyadverbypagebeanajax.htm", { tempId: $(tempId).val(),CSRFToken:$(CSRFToken).val(),pageSize:pageSize,pageNo:pageNo,storeyId:$("#storeyAdverId").val()},
        function(data){
            $(floorAdverHtml).html("");
            if(data.pb.list.length==0){
                $(floorAdverHtml).append('<tr><td colspan="7"><p class="text-center">暂无可用数据</p> </td></tr>');
            }
            for(var i=0;i<data.pb.list.length;i++){
                var str="<tr>";
                str+=" <td><input  name='adverId'  value="+data.pb.list[i].channelAdverId+" type='checkbox'></td>";
                str+='<td><img width="150px;" alt="" src="'+data.pb.list[i].adverPath+'" height="50"></td>';
                str+=" <td>"+data.pb.list[i].adverName +"</td>";
                str+=" <td>"+data.pb.list[i].temp2 +"</td>";
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
                '</button><ul class="dropdown-menu" role="menu"><li><a onclick="delFloorAdverById(\'deletetempadverajax.htm?CSRFToken='+$(CSRFToken).val()+'&adverId='+data.pb.list[i].channelAdverId+'\')" href="javascript:;">删除</a></li></ul></div></td>';
                str+="</tr>";
                $(floorAdverHtml).append(str);

            }
            floorAdverPageBean(data);
        });

}

function delFloorAdverById(adverId){
    showAjaxDeleteConfirmAlertByTemp(adverId,8);
}

<!--页面导航分页-->
function floorAdverPageBean(data){
    var footStr='<div class="table_pagenav pull-right">';
    footStr+='<div class="meneame">';
    if(data.pb.pageNo==1){
        footStr+='<span class="disabled"> 上一页 </span>';
    }else{
        footStr+='<a  href="javascript:;" onclick="showfloorAdverPage('+(data.pb.pageNo-1)+')"> 上一页 </a>';
    }
    $(floorAdverFootHtml).html("");
    for(var i = data.pb.startNo;i<data.pb.endNo+1;i++){
        if(i==data.pb.pageNo){
            footStr+=' <span class="current"> '+i+'</span>';
        }else{
            footStr+='<a href="javascript:;"  onclick="showfloorAdverPage('+i+')">'+i+'</a>';
        }
    }
    if(data.pb.pageNo==data.pb.totalPages){
        footStr+='<span class="disabled"> 下一页 </span>';
    }else{
        footStr+='   <a href="javascript:;" onclick="showfloorAdverPage('+(data.pb.pageNo+1)+')"> 下一页 </a>';
    }

    footStr+='</div>';
    footStr+='</div>';
    footStr+=' <div class="table_ctrl pull-left">';
    footStr+='<form role="form" class="form-inline">';
    footStr+=' <label class="control-label">每页显示：</label>';
    footStr+=' <select class="form-control" id="showFloorAdverOpetion" onchange="showfloorAdverPage('+data.pb.pageNo+')">';
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

    $(floorAdverFootHtml).append(footStr);
    cls();
}
//楼层广告设置


//楼层品牌

function setStoreyTrademark(obj){
    $("#trademarkAdverId").val(obj);
    showStoreyTrademark();
    $('.floor_brand_set').show();
    $('.floor_set').hide();

}

function showStoreyTrademark(pageNo){
    var pageSize=10;
    $("#trademarkBrandImg").attr("src",$("#trademark"+$("#trademarkBrand").val()).val());
    $("#trademarBrandValue").val($("#trademark"+$("#trademarkBrand").val()).val());
    if($("#showStoreyTrademarkOpetion").val()!=null){
        pageSize=$(showStoreyTrademarkOpetion).val();
    }
    $.post("querychannelstoreytrademarkbypagebeanajax.htm", { tempId: $(tempId).val(),CSRFToken:$(CSRFToken).val(),pageSize:pageSize,pageNo:pageNo,storeyId:$("#trademarkAdverId").val()},
        function(data){
            $(storeyTrademarkHtml).html("");
            if(data.pb.list.length==0){
                $(storeyTrademarkHtml).append('<tr><td colspan="7"><p class="text-center">暂无可用数据</p> </td></tr>');
            }
            for(var i=0;i<data.pb.list.length;i++){
                var str="<tr>";
                str+=" <td><input  name='trademarkId'  value="+data.pb.list[i].channelTrademarkId+" type='checkbox'></td>";
                str+='<td><img width="150px;" alt="" src="'+data.pb.list[i].logoSrc+'" height="50"></td>';
                str+=" <td>"+data.pb.list[i].trademarkName +"</td>";
                str+=" <td>"+data.pb.list[i].sort +"</td>";
                if(data.pb.list[i].userStatus==1){
                    str+="<td><a href='javascript:;'><span class='label label-success'>是</span></a></td>";
                }else{
                    str+="<td><a href='javascript:;'><span class='label label-default'>否</span></a></td>";
                }

    str+= '<td><div class="btn-group"><button type="button" class="btn btn-default" onclick="updateTrademark('+data.pb.list[i].channelTrademarkId+')">编辑</button>'+
    '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">'+
    '<span class="caret"></span>'+
    '<span class="sr-only">Toggle Dropdown</span>'+
    '</button><ul class="dropdown-menu" role="menu"><li><a onclick="delStoreyTrademarkById(\'deletechannelstoreytrademarkajax.htm?CSRFToken='+$(CSRFToken).val()+'&trademarkId='+data.pb.list[i].channelTrademarkId+'\',9)" href="javascript:;">删除</a></li></ul></div></td>';
    str+="</tr>";
    $(storeyTrademarkHtml).append(str);

}
storeyTrademarkBean(data);
});

}

function delStoreyTrademarkById(adverId,status){
    showAjaxDeleteConfirmAlertByTemp(adverId,status);
}

<!--页面导航分页-->
function storeyTrademarkBean(data){
    var footStr='<div class="table_pagenav pull-right">';
    footStr+='<div class="meneame">';
    if(data.pb.pageNo==1){
        footStr+='<span class="disabled"> 上一页 </span>';
    }else{
        footStr+='<a  href="javascript:;" onclick="showStoreyTrademark('+(data.pb.pageNo-1)+')"> 上一页 </a>';
    }
    $(storeyTrademarkFootHtml).html("");
    for(var i = data.pb.startNo;i<data.pb.endNo+1;i++){
        if(i==data.pb.pageNo){
            footStr+=' <span class="current"> '+i+'</span>';
        }else{
            footStr+='<a href="javascript:;"  onclick="showStoreyTrademark('+i+')">'+i+'</a>';
        }
    }
    if(data.pb.pageNo==data.pb.totalPages){
        footStr+='<span class="disabled"> 下一页 </span>';
    }else{
        footStr+='   <a href="javascript:;" onclick="showStoreyTrademark('+(data.pb.pageNo+1)+')"> 下一页 </a>';
    }

    footStr+='</div>';
    footStr+='</div>';
    footStr+=' <div class="table_ctrl pull-left">';
    footStr+='<form role="form" class="form-inline">';
    footStr+=' <label class="control-label">每页显示：</label>';
    footStr+=' <select class="form-control" id="showStoreyTrademarkOpetion" onchange="showStoreyTrademark('+data.pb.pageNo+')">';
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

    $(storeyTrademarkFootHtml).append(footStr);
    cls();
}

function changeTrademarkBrand(obj){
    $("#trademarBrandValue").val($("#trademark"+$("#trademarkBrand").val()).val());
    $("#trademarkBrandImg").attr("src",$("#trademark"+$(obj).val()).val());
}

function subTradermark(){
    if(!$("#trademarkForm").valid()) return;
    $.ajax({
        cache: true,
        type: "POST",
        url: $(tradermarkUrl).val(),
        data: $("#trademarkForm").serialize(),// 你的formid
        async: false,
        error: function (request) {
            showTipAlert("Connection error");
        },
        success: function (data) {
            if (data == 1) {
                if($(channelTrademarkId).val()!=null&&$(channelTrademarkId).val()!=""){
                    showTipAlert("修改成功！！");
                }else{
                    showTipAlert("添加成功！！");
                }
                if($("#isBrand").val()==4){
                    showTagTrademark();
                }else{
                    showStoreyTrademark();
                }
                $(clsTrademark).click();

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

function clsTradermark(){
    clsBtnCss();
    if($("#isBrand").val()==4){
        $("#trademarkTitle").html("添加标签品牌");
    }else{
        $("#trademarkTitle").html("添加楼层品牌");
    }

    $("#channelTrademarkId").val("");
    $("#trademarkLogoSrc").val("");
    $("#trademarkStatus1").prop("checked","checked");
    $(tradermarkUrl).val("createchannelstoreytrademarkajax.htm");
}

function updateTrademark(id){
    $.post("showchannelstoreytrademarkajax.htm", { trademarkId: id,CSRFToken:$(CSRFToken).val()},
        function(data) {
            if($("#isBrand").val()==4){
                $("#trademarkTitle").html("修改标签品牌");
            }else{
                $("#trademarkTitle").html("修改楼层品牌");
            }

            $("#channelTrademarkId").val(data.channelTrademarkId);
            $("#trademarkLogoSrc").val(data.sort);
            $("#trademarkLogoSrc").val(data.sort);
            if(data.userStatus==1){
                $("#trademarkStatus1").prop("checked","checked");
            }else{
                $("#trademarkStatus0").prop("checked","checked");
            }

        });
        $(tradermarkUrl).val("updatechannelstoreytrademarkajax.htm");
        $('#addFloorBrands').modal('show');
}

//楼层品牌
//楼层商品标签
function clsBtnCss(){
    $(".form-control").removeClass("error");
    $(".error").remove();
}
