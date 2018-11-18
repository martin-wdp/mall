//页面标签
function showTagList(pageNo){
    $('.floor_label_ad_set').hide();
    $('.floor_label_set').show();
    $('.floor_label_good_set').hide();
    $('.floor_label_ad_set').hide();
    $('.floor_label_brand_set').hide();
    $("#tag_storeyId").val("");
    var pageSize=10;
    $("#showTagBtn").hide();
    if($("#showTagOpetion").val()!=null){
        pageSize=$(showTagOpetion).val();
    }
    $("#up_tempO").val( $(tempId).val());
    $.post("querypagetagbypagebeanajax.htm", { channelId: $(channelId).val(),pageSize:pageSize,pageNo:pageNo},

        function(data){
             $(tagHtml).html("");
            if(data.pb.list.length==0){
                $(tagHtml).append('<tr><td colspan="5"><p class="text-center">暂无可用数据</p> </td></tr>');
            }
            for(var i=0;i<data.pb.list.length;i++){
                var str="<tr>";
                str+=" <td><input  name='tagId'  value="+data.pb.list[i].channelStoreyTagId+" type='checkbox'></td>";
                str+=" <td>"+data.pb.list[i].name +"</td>";
                str+=" <td>"+data.pb.list[i].sort +"</td>";
                if(data.pb.list[i].userStatus==1){
                    str+="<td><a href='javascript:;'><span class='label label-success'>是</span></a></td>";
                }else{
                    str+="<td><a href='javascript:;'><span class='label label-default'>否</span></a></td>";
                }
                str+= '<td> <div class="btn-group"><button type="button" class="btn btn-default" onclick="showTagDetil('+data.pb.list[i].channelStoreyTagId+')">修改页面标签</button>'+
                '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">'+
                '<span class="caret"></span>'+
                '<span class="sr-only">Toggle Dropdown</span>';
                str+='</button>';
                str+='<ul class="dropdown-menu" role="menu">';
                str+='<li><a href="javascript:;" onclick="showTagGoodsList('+data.pb.list[i].channelStoreyTagId+')">设置标签商品</a></li>';
                if(data.pb.list[i].showAdver==1){
                    str+='<li><a href="javascript:;" onclick="showTagAdver('+data.pb.list[i].channelStoreyTagId+')">设置标签广告</a></li>';
                }
                if(data.pb.list[i].showTrademark==1){
                    str+=' <li><a href="javascript:;" onclick="setTagTrademark('+data.pb.list[i].channelStoreyTagId+')">设置标签品牌</a></li>';
                }


                str+='</ul> </div></td>';
                str+="</tr>";
                $(tagHtml).append(str);
            }
            pageBeanTag(data);
        });
}

function pageBeanTag(data){
    var footStr='<div class="table_pagenav pull-right">';
    footStr+='<div class="meneame">';
    if(data.pb.pageNo==1){
        footStr+='<span class="disabled"> 上一页 </span>';
    }else{
        footStr+='<a  href="javascript:;" onclick="showTagList('+(data.pb.pageNo-1)+')"> 上一页 </a>';
    }
    $(tagFootHtml).html("");
    for(var i = data.pb.startNo;i<data.pb.endNo+1;i++){
        if(i==data.pb.pageNo){
            footStr+=' <span class="current"> '+i+'</span>';
        }else{
            footStr+='<a href="javascript:;"  onclick="showTagList('+i+')">'+i+'</a>';
        }
    }
    if(data.pb.pageNo==data.pb.totalPages){
        footStr+='<span class="disabled"> 下一页 </span>';
    }else{
        footStr+='   <a href="javascript:;" onclick="showTagList('+(data.pb.pageNo+1)+')"> 下一页 </a>';
    }

    footStr+='</div>';
    footStr+='</div>';
    footStr+=' <div class="table_ctrl pull-left">';
    footStr+='<form role="form" class="form-inline"">';
    footStr+=' <label class="control-label ">每页显示：</label>';
    footStr+=' <select class="form-control" id="showTagOpetion" onchange="showTagList('+data.pb.pageNo+')">';
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

    $(tagFootHtml).append(footStr);
}

function showTagDetil(tagId){
    $.post("showpagetagajax.htm?CSRFToken="+$(CSRFToken).val(), { tagId:tagId },
        function(data){
            $("#pagTagFormUrl").val("updatepagetagajax.htm");
            $("#up_channelStoreyTagId").val(data.channelStoreyTagId);
            if(data.userStatus==1){
                $("#pageTageUserStatus1").prop("checked","checked");
            }else{
                $("#pageTageUserStatus0").prop("checked","checked");
            }

            if(data.showTrademark==1){
                $("#pageShowTrademark1").prop("checked","checked");
            }else{
                $("#pageShowTrademark0").prop("checked","checked");
            }
            if(data.showAdver==1){
                $("#pageTagShowAdver1").prop("checked","checked");
            }else{
                $("#pageTagShowAdver0").prop("checked","checked");
            }

            $("#pagTagName").val(data.name);
            $("#pagTagSort").val(data.sort);
            if( $("#tag_storeyId").val()!=""){
                $("#pageTagTitle").html("修改楼层标签");
            }else{
                $("#pageTagTitle").html("修改页面标签");
            }
        });
    $('#addFloorLabel').modal('show');
}


function subPagTagFrom(){
    if(!$("#pageTagForm").valid()) return;
    $.ajax({
        cache: true,
        type: "POST",
        url: $(pagTagFormUrl).val(),
        data: $("#pageTagForm").serialize(),// 你的formid
        async: false,
        error: function (request) {
            showTipAlert("Connection error");
        },
        success: function (data) {
            if (data == 1) {
                if($(up_channelStoreyTagId).val()!=""){
                    showTipAlert("修改成功！！");
                }else{
                    showTipAlert("添加成功！！");
                }
                $(pagTagCls).click();
                if( $("#tag_storeyId").val()!=""){
                    showHTagList();
                }else{
                    showTagList();
                }

            } else {
                if($(up_channelStoreyTagId).val()!=""){
                    showTipAlert("修改失败！！");
                }else{
                    showTipAlert("添加失败！！");
                }
            }
        }
    });
}

function clsPagTag(){
    clsBtnCss();
    $("#up_channelStoreyTagId").val("");
    $("#pagTagFormUrl").val("createpagetagajax.htm");
    $("#pageTageUserStatus1").prop("checked","checked");
    $("#pageTagShowAdver1").prop("checked","checked");
    $("#pageShowTrademark1").prop("checked","checked");
    $("#pagTagName").val("");
    $("#pagTagSort").val("");
    if( $("#tag_storeyId").val()!=""){
        $("#pageTagTitle").html("添加楼层标签");
    }else{
        $("#pageTagTitle").html("添加页面标签");
    }
}
//页面标签
//页面标签商品
//楼层商品设置
function showTagGoodsList(obj){
    $("#upStoreGoodsTempId").val("");
    $("#storeyTitle").html("添加标签商品");
    $("#storeyTagId").val(obj);
    $('.floor_label_good_set').show();
    $('.floor_label_set').hide()

    $("#up_channelStoreyGoodsProductId1").val("");
    showTagyGoods();
}

function showTagyGoods(pageNo){
    var pageSize=10;
    if($("#showTagGoodsOpetion").val()!=null){
        pageSize=$(showTagGoodsOpetion).val();
    }
    $.post("querychannelstoreygoodsbypagebeanajax.htm", { storeyTagId: $(storeyTagId).val(),CSRFToken:$(CSRFToken).val(),pageSize:pageSize,pageNo:pageNo},

        function(data){
            $(tagGoodsHtml).html("");
            if(data.pb.list.length==0){
                $(tagGoodsHtml).append('<tr><td colspan="8"><p class="text-center">暂无可用数据</p> </td></tr>');
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
                $(tagGoodsHtml).append(str);
            }
            pageBeanTagStorey(data);
        });
}


function pageBeanTagStorey(data){
    var footStr='<div class="table_pagenav pull-right">';
    footStr+='<div class="meneame">';
    if(data.pb.pageNo==1){
        footStr+='<span class="disabled"> 上一页 </span>';
    }else{
        footStr+='<a  href="javascript:;" onclick="showTagyGoods('+(data.pb.pageNo-1)+')"> 上一页 </a>';
    }
    $(tagGoodsFootHtml).html("");
    for(var i = data.pb.startNo;i<data.pb.endNo+1;i++){
        if(i==data.pb.pageNo){
            footStr+=' <span class="current"> '+i+'</span>';
        }else{
            footStr+='<a href="javascript:;"  onclick="showTagyGoods('+i+')">'+i+'</a>';
        }
    }
    if(data.pb.pageNo==data.pb.totalPages){
        footStr+='<span class="disabled"> 下一页 </span>';
    }else{
        footStr+='   <a href="javascript:;" onclick="showTagyGoods('+(data.pb.pageNo+1)+')"> 下一页 </a>';
    }

    footStr+='</div>';
    footStr+='</div>';
    footStr+=' <div class="table_ctrl pull-left">';
    footStr+='<form role="form" class="form-inline"">';
    footStr+=' <label class="control-label ">每页显示：</label>';
    footStr+=' <select class="form-control" id="showTagGoodsOpetion" onchange="showTagyGoods('+data.pb.pageNo+')">';
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

    $(tagGoodsFootHtml).append(footStr);
}


//标签广告
function showTagAdver(tagId){
    $('.floor_label_ad_set').show();
    $('.floor_label_set').hide()

    $("#storeyTagAdverId").val(tagId);
    showTagAdverPage();

}
function showTagAdverPage(pageNo){
    $("#showFloorAdver").hide();
    $("#storeyAdverId").val("");
    $("#upChannelAdverId").val("");
    $("#up_adverType").val(151);
    $("#advFlag").val(0);
    $("#showAdverTitle").html("添加标签广告");
    $("#up_storeyAdverTempId").val("");
    $(upAtId).attr("value",161);
    $("#up_temp3").val(0);
    $("#up_temp1").val("");
    $("#up_floorId").val("");
    var pageSize=10;
    $("#isAdver").val(5);
    if($("#showTagTrademarkOpetion").val()!=null){
        pageSize=$(showTagTrademarkOpetion).val();
    }
    $("#advFlag").val("");
    $.post("querytempadverbypagebeanajax.htm", { storeyTagId: $(storeyTagAdverId).val(),CSRFToken:$(CSRFToken).val(),pageSize:pageSize,pageNo:pageNo,temp3:1},
        function(data){
            $(tagAdverHtml).html("");
            if(data.pb.list.length==0){
                $(tagAdverHtml).append('<tr><td colspan="8"><p class="text-center">暂无可用数据</p> </td></tr>');
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
                '</button><ul class="dropdown-menu" role="menu"><li><a onclick="delStoreyTrademarkById(\'deletetempadverajax.htm?CSRFToken='+$(CSRFToken).val()+'&adverId='+data.pb.list[i].channelAdverId+'\',11)" href="javascript:;">删除</a></li></ul></div></td>';
                str+="</tr>";
                $(tagAdverHtml).append(str);

            }
            pageBeanTagAdver(data);
        });

}
<!--页面导航分页-->
function pageBeanTagAdver(data){
    var footStr='<div class="table_pagenav pull-right">';
    footStr+='<div class="meneame">';
    if(data.pb.pageNo==1){
        footStr+='<span class="disabled"> 上一页 </span>';
    }else{
        footStr+='<a  href="javascript:;" onclick="showTagAdverPage('+(data.pb.pageNo-1)+')"> 上一页 </a>';
    }

    $(tagAdverFootHtml).html("");
    for(var i = data.pb.startNo;i<data.pb.endNo+1;i++){
        if(i==data.pb.pageNo){
            footStr+=' <span class="current"> '+i+'</span>';
        }else{
            footStr+='<a href="javascript:;"  onclick="showTagAdverPage('+i+')">'+i+'</a>';
        }
    }
    if(data.pb.pageNo==data.pb.totalPages){
        footStr+='<span class="disabled"> 下一页 </span>';
    }else{
        footStr+='   <a href="javascript:;" onclick="showTagAdverPage('+(data.pb.pageNo+1)+')"> 下一页 </a>';
    }

    footStr+='</div>';
    footStr+='</div>';
    footStr+=' <div class="table_ctrl pull-left">';
    footStr+='<form role="form" class="form-inline"">';
    footStr+=' <label class="control-label ">每页显示：</label>';
    footStr+=' <select class="form-control" id="showTagAdverOpetion" onchange="showTagAdverPage('+data.pb.pageNo+')">';
    footStr+='<option value="1" selected="selected">1</option>';
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

    $(tagAdverFootHtml).append(footStr);
}

//标签品牌

function setTagTrademark(obj){
    $("#up_brand_storeyTagId").val(obj);
    $('.floor_label_brand_set').show();
    $('.floor_label_set').hide()
    showTagTrademark();
}

function showTagTrademark(pageNo){
    $("#isBrand").val(4);
    var pageSize=10;
    $("#trademarkBrandImg").attr("src",$("#trademark"+$("#trademarkBrand").val()).val());
    $("#trademarBrandValue").val($("#trademark"+$("#trademarkBrand").val()).val());
    if($("#showTagTrademarkOpetion").val()!=null){
        pageSize=$(showTagTrademarkOpetion).val();
    }

    $.post("querychannelstoreytrademarkbypagebeanajax.htm", { storeyTagId: $(up_brand_storeyTagId).val(),CSRFToken:$(CSRFToken).val(),pageSize:pageSize,pageNo:pageNo},
        function(data){
            $(tagBrandHtml).html("");
            if(data.pb.list.length==0){
                $(tagBrandHtml).append('<tr><td colspan="7"><p class="text-center">暂无可用数据</p> </td></tr>');
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
                $(tagBrandHtml).append(str);

            }
            tagTrademarkBean(data);
        });

}
function tagTrademarkBean(data){
    var footStr='<div class="table_pagenav pull-right">';
    footStr+='<div class="meneame">';
    if(data.pb.pageNo==1){
        footStr+='<span class="disabled"> 上一页 </span>';
    }else{
        footStr+='<a  href="javascript:;" onclick="showTagTrademark('+(data.pb.pageNo-1)+')"> 上一页 </a>';
    }
    $(tagBrandFootHtml).html("");
    for(var i = data.pb.startNo;i<data.pb.endNo+1;i++){
        if(i==data.pb.pageNo){
            footStr+=' <span class="current"> '+i+'</span>';
        }else{
            footStr+='<a href="javascript:;"  onclick="showTagTrademark('+i+')">'+i+'</a>';
        }
    }
    if(data.pb.pageNo==data.pb.totalPages){
        footStr+='<span class="disabled"> 下一页 </span>';
    }else{
        footStr+='   <a href="javascript:;" onclick="showTagTrademark('+(data.pb.pageNo+1)+')"> 下一页 </a>';
    }

    footStr+='</div>';
    footStr+='</div>';
    footStr+=' <div class="table_ctrl pull-left">';
    footStr+='<form role="form" class="form-inline">';
    footStr+=' <label class="control-label">每页显示：</label>';
    footStr+=' <select class="form-control" id="showTagTrademarkOpetion" onchange="showTagTrademark('+data.pb.pageNo+')">';
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

    $(tagBrandFootHtml).append(footStr);
}

//楼层标签
function showStoreyTag(obj){
    $(".tab-pane").removeClass("active");
    $("#tab10").addClass("active");
    $('.floor_label_set').show();
    $("#tag_storeyId").val(obj);
    showHTagList()
    $("#up_tempO").val("");
}


function showHTagList(pageNo){
    $('.floor_label_ad_set').hide();
    $('.floor_label_set').show();
    $('.floor_label_good_set').hide();
    $('.floor_label_ad_set').hide();
    $("#showTagBtn").show();
    $('.floor_label_brand_set').hide();
    var pageSize=10;
    if($("#showTagOpetion").val()!=null){
        pageSize=$(showTagOpetion).val();
    }
    $.post("querypagetagbypagebeanajax.htm", { storeyId: $(tag_storeyId).val(),CSRFToken:$(CSRFToken).val(),pageSize:pageSize,pageNo:pageNo},

        function(data){
            $(tagHtml).html("");
            if(data.pb.list.length==0){
                $(tagHtml).append('<tr><td colspan="5"><p class="text-center">暂无可用数据</p> </td></tr>');
            }
            for(var i=0;i<data.pb.list.length;i++){
                var str="<tr>";
                str+=" <td><input  name='tagId'  value="+data.pb.list[i].channelStoreyTagId+" type='checkbox'></td>";
                str+=" <td>"+data.pb.list[i].name +"</td>";
                str+=" <td>"+data.pb.list[i].sort +"</td>";
                if(data.pb.list[i].userStatus==1){
                    str+="<td><a href='javascript:;'><span class='label label-success'>是</span></a></td>";
                }else{
                    str+="<td><a href='javascript:;'><span class='label label-default'>否</span></a></td>";
                }
                str+= '<td> <div class="btn-group"><button type="button" class="btn btn-default" onclick="showTagDetil('+data.pb.list[i].channelStoreyTagId+')">修改页面标签</button>'+
                '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">'+
                '<span class="caret"></span>'+
                '<span class="sr-only">Toggle Dropdown</span>';
                str+='</button>';
                str+='<ul class="dropdown-menu" role="menu">';
                str+='<li><a href="javascript:;" onclick="showTagGoodsList('+data.pb.list[i].channelStoreyTagId+')">设置标签商品</a></li>';
                if(data.pb.list[i].showAdver==1){
                    str+='<li><a href="javascript:;" onclick="showTagAdver('+data.pb.list[i].channelStoreyTagId+')">设置标签广告</a></li>';
                }
                if(data.pb.list[i].showTrademark==1){
                    str+=' <li><a href="javascript:;" onclick="setTagTrademark('+data.pb.list[i].channelStoreyTagId+')">设置标签品牌</a></li>';
                }


                str+='</ul> </div></td>';
                str+="</tr>";
                $(tagHtml).append(str);
            }
            pageBeanTag(data);
        });
}

function returnFoolrHtml(){
    $(".tab-pane").removeClass("active");
    $("#tab7").addClass("active");
    showTempStorey();
}