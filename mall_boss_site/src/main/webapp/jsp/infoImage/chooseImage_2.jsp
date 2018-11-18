<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE html>   
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<style>
	body{background:none}
    .upload_body{padding:10px;}
    .table_foot .table_ctrl{width:220px;}
</style>
<body>

<div role="tabpanel" class="upload_body">

    <!-- Nav tabs -->
    <ul class="nav nav-tabs mb20" role="tablist">
        <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">上传本地图片</a></li>
        <li role="presentation"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab" onclick="doAjaxForCate(1);">选择图库图片</a></li>
        <li role="presentation"><a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">添加网络图片</a></li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="tab1">
            <form id="uploadForm" action="uploadFileOneForManage.htm" method="post" enctype="multipart/form-data"  target="hidden_frame">
                <input type="hidden" id="size" value="${size}"/>
            <p><button type="button" class="btn btn-info" id="image">选择图片</button></p>
            <input type="file" id="imgUpload" name="file" style="display: none" multiple />
            </form>
            <ul class="imgs_list clearfix">
            </ul>
            <input id="fileName" type="hidden" name="fileName" />
            <iframe name="hidden_frame" style="display:none"></iframe>
        </div>
        <div role="tabpanel" class="tab-pane" id="tab2">
            <div class="mb20">
                <div class="form-inline">
                    <label class="control-label">图片分类：</label>
                    <select class="form-control" id="up_classifyId">
                        <option value="-1">全部</option>
                        <c:forEach var="classify" items="${classifyList}">
                            <option value="${classify.classifyId }">${classify.classifyName }</option>
                        </c:forEach>
                    </select>
                    <button type="button" class="btn btn-default" onclick="doAjaxForCate(1);">搜索</button>
                </div>
            </div>
              <div class="uploaded_imgs mb10 container-fluid">
                    <div class="row" id="list">
                   
                   
                    </div>
                  </div>
            <div class="table_foot" id="tf">
                <div class="table_pagenav pull-right">
                    <div class="meneame">
                        <span class="disabled"> 上一页 </span>
                        <span class="current"> 1 </span>
                        <a href="#?page=2"> 2 </a>
                        <a href="#?page=3"> 3 </a>
                        <a href="#?page=4"> 4 </a>
                        <a href="#?page=5"> 5 </a>
                        <a href="#?page=6"> 6 </a>
                        <a href="#?page=7"> 7 </a>
                        ...
                        <a href="#?page=199"> 199 </a>
                        <a href="#?page=200"> 200 </a>
                        <a href="#?page=2"> 下一页 </a>
                    </div>
                </div>
                <div class="table_ctrl pull-left">
                    <form role="form" class="form-horizontal">
                        <label class="control-label col-sm-12">每页显示：</label>
                        <div class="col-sm-12">
                            <select class="form-control">
                                <option>10</option>
                                <option>20</option>
                                <option>50</option>
                                <option>100</option>
                            </select>
                        </div>
                    </form>
                </div>
                <div class="clr"></div>
            </div>
        </div>
        <div role="tabpanel" class="tab-pane" id="tab3">
            <div class="form-inline mt20 mb20">
                <label class="control-label">网络图片路径：</label>
                <input type="text" class="form-control w500" id="imgUrl" name="imgUrl">
            </div>
        </div>
    </div>

</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
<script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/plugins/iframeTools.js"></script>
<script>
    //选择图片
    $(function () {
        var parent = art.dialog.parent,				// 父页面window对象
                api = art.dialog.open.api;	// 			art.dialog.open扩展方法
        if (!api) return;
        // 操作对话框
        api.title('选择图片')
            // 自定义按钮
                .button(
                {
                    name: '保存',
                    callback: function () {
                        var win = art.dialog.open.origin;//来源页面
                        //验证是否有上传文件
                        var size = $("#size").val()>0?$("#size").val():1;
                        var url = '';
                        switch($("li.active a").attr("href")){
                            case '#tab1':
                                var filePaths = $('#fileName').val();
                                if(filePaths){
                                    if((filePaths+"").split(",").length>size){
                                        art.dialog.alert('上传的图片大于'+size+'张！');
                                    }else{
                                        url = filePaths;
                                    }
                                }else{
                                    art.dialog.alert('请上传图片！');
                                }
                                break;
                            case '#tab2':
                                var resoult = checkSelected('imageManageId',size);

                                //-1：未选中，0：选中的大于可选择的，1：成功
                                if(resoult==1){
                                    url = selectImgUrl("imageManageId");
                                }else if(resoult==0){
                                    art.dialog.alert('选择的图片大于'+size+'张！');
                                }else{
                                    art.dialog.alert('请选择图片！');
                                }

                                break;
                            case '#tab3':

                                var urls = $('#imgUrl').val();
                                if(urls){
                                    if((urls+"").split(",").length>size){
                                        art.dialog.alert('设置的网络图片大于'+size+'张！');
                                    }else{
                                        url = urls;
                                    }
                                }else{
                                    art.dialog.alert('请设置网络图片！');
                                }

                                break;
                        }

                      

                        if(url){
                            win.window.saveChoooseImage2(url,api.config.id);
                            art.dialog.close();
                        }else{
                            return false;
                        }
                    },
                    focus: true
                },
                {
                    name: '取消',
                    callback: function () {
                        art.dialog.close();
                    }
                }
        );

        if($(".sp_search").length > 0 || $(".table_box").length > 0) {

            $(".sp_search").css("top",$(".hd_bar").offset().top + 45).css("right",-($(".sp_search").width()+42));

            var tb_top = parseInt($(".table_box").offset().top + 50);
            var tb_h = $(window).height() - (tb_top);
            $(".table_box").height(tb_h).attr("data-height",tb_h);

            scroll("table_box");

            $(window).resize(function(){
                var tb_top = parseInt($(".table_box").offset().top + 50);
                var tb_h = $(window).height() - (tb_top);
                $(".table_box").height(tb_h).attr("data-height",tb_h);

                $('.table_box').slimScroll({
                    destroy: true
                });
                scroll("table_box");
            });
        };

        $("#image").click(function(){
            $("#imgUpload").click();
        });
        $("#imgUpload").change(function(){
            $('#uploadForm').submit();

        });
    });

    /**
     * 检查是否选中一行，-1：未选中，0：选中的大于可选择的，1：成功
     * @param _objId      checkbox节点name属性名
     * @param size 可选中的数量
     * NINGPAI_xiaomm
     * 2014-03-04 14:22
     * */
    function checkSelected(_objId,size){
        checkedList = new Array();
        $("input[name='"+_objId+"']:checked").each(function() {
            checkedList.push($(this).val());
        });
        if(checkedList.length > 0){
            if(checkedList.length <= size){
                return 1;
            }else{
                return 0;
            }
        }else{
            return -1;
        }

    }
    /**
     * 查询选中的图片路径
     * @param obj 可选中的数量
     * NINGPAI_xiaomm
     * 2014-03-04 14:22
     * */
    function selectImgUrl(obj){
        var urlList = new Array();
        $("input[name='"+obj+"']:checked").each(function() {
            checkedList.push($(this).val());
            urlList.push($("#img_" + $(this).val()).val());
        });
        return urlList;
    }
    /*执行AJAX查询分类信息*/
    function doAjaxForCate(pageNo, pageSize)
    {
        /*AJAX查询分类信息*/
        $.get("ajaxQueryImageForChoose.htm?classifyId="+$("#up_classifyId").val()+"&pageNo=" + pageNo + (pageSize==undefined?"":("&pageSize=" + pageSize))+"&CSRFToken="+$("#CSRFToken").val(),
                function (data)
                {
                    //设置条数
                    //$("#totalCount").text("(共" + data.rows + "条)");
                    /*置空*/
                    $("#tf").html("");
                    //var foot = "<tr><td colspan='6'>每页显示:" + "<input class='p_text' id='list_size' size='1' value='" + data.pageSize + "' type='text' onblur='changePageShow()' />条" + "<span class='ml20 mr20'>共" + data.totalPages + "页/当前" + data.pageNo + "页</span>" + "跳转到" + "<input class='p_text' id='list_pageno' size='1' value='" + data.pageNo + "' type='text'" + "onblur='changeShowPage(" + data.totalPages + ")' />页 " + "<div class='pages ml20'>" + "<a href='javascript:void(0)' onclick='doAjaxForCate(" + data.prePageNo + "," + data.pageSize + ")'>«</a>";
                    var i = 1;
                    var foot = '<div class="table_pagenav pull-right"><div class="meneame">';
                     if(data.pageNo==1){
                    	  foot = foot + '<span class="disabled">上一页</span>';
                     }else{
                    	 foot = foot + '<a href="javascript:void(0)" onclick="doAjaxForCate(' + data.prePageNo + ',' + data.pageSize + ')">上一页</a>';
                     }
                    for (var l = data.startNo; l <= data.endNo; l++)
                    {
                        if ((i - 1 + data.startNo) == data.pageNo)
                        {
                            foot = foot + "<span class='current'  href='javascript:void(0)'>" + (i - 1 + data.startNo) + "</span>";
                        }
                        else
                        {
                            foot = foot + "<a onclick='doAjaxForCate(" + (i - 1 + data.startNo) + "," + (data.pageSize) + ")' href='javascript:void(0)'>" + (i - 1 + data.startNo) + "</a>";
                        }
                        i++;
                    }
                    foot = foot + "<a onclick='doAjaxForCate(" + data.nextPageNo + "," + data.pageSize + ")' href='javascript:void(0)'>下一页</a></div></div>";
                    foot = foot +'<div class="table_ctrl pull-left"> <label class="control-label col-sm-8" style="padding-top:8px;">每页显示：</label><div class="col-sm-10">';
                    foot = foot + "<input class='form-control' id='list_size' style='width:50px;' size='1' value='" + data.pageSize + "' type='text' onblur='changePageShow()' /></div></div>";
                    /*添加tfoot分页信息*/
                    $("#tf").html(foot);
                    /*进行递归  GO*/
                    getCateList(data.list);
                });
    }
    /*获取AJAX返回的分类列表，并进行反递归*/
    function getCateList(data)
    {
        var html = "";
        /*首先把内容置空*/
        $("#list tbody").html("");
        for (var i = 0; i < data.length; i++)
        {
        	
             
            html = html + '<div class="col-sm-6">';
            html = html + '<div class="img_item">';
            html = html + "<input type='checkbox' name='imageManageId' value='" + data[i].imageManageId + "' />";
            html = html + "<input type='hidden' id='img_" + data[i].imageManageId + "' value='" + data[i].imageManageUrl + "' />";
            
            html = html + "<img alt='' src='"+data[i].imageManageUrl+"' width='' height='' />";
            if(data[i].imgClassify!=null){
                html = html + "<p>"+data[i].imgClassify.classifyName + "</p>";
            }else{
                html = html + "<p>未分类</p>";
            }
         	
         /*    
            if(data[i].imgClassify!=null){
                html = html + "<td class='tc'>"+data[i].imgClassify.classifyName + "</td></tr>";
            }else{
                html = html + "<td class='tc'>分类不存在</td></tr>";
            } */
            
            html = html + '</div></div>';
        }
        /*把计算出来的结果放在表格的BODY中*/
        $("#list").html(html);
        $('.img_item').click(function(){

            if($(this).attr('class').indexOf('selected')>0){
              $(this).removeClass('selected');
              $(this).find('input[type="checkbox"]').removeAttr('checked');
            }
            else{
              $(this).addClass('selected');
              $(this).find('input[type="checkbox"]').attr('checked',true);
              $(this).find('input[type="checkbox"]').prop('checked',true);
            }
          });
    }

    /*改变每页显示的行数*/
    function changePageShow()
    {
        var size = $("#list_size").val();
        if (size == "" || size == "0" || isNaN(size) || size <= 0)
        {
            //输入错误
            $("#dialog-err-tip").dialog(
                    {
                        resizable : false, height : 150, width : 270, modal : true, autoOpen : true, buttons : {
                        "确定" : function ()
                        {
                            $(this).dialog("close");
                        }
                    }
                    });
        }
        else {
            doAjaxForCate(1, size);
        }
    }

    function callback(msg){
        if(msg=="-1"){
            art.dialog.alert('请上传图片！');
        }else if(msg=="-2"){
            art.dialog.alert('上传图片不得大于10485760！');
        }else if(msg=="-3"){
            art.dialog.alert('请选择图片上传！');
        }else{
            var n = msg.indexOf(",");
            if(n>-1){
                var imgs = msg.split(",");
                for(var i = 0;i<imgs.length;i++){
                    if(imgs[i].length>0){
                        var _li = '<li><img alt="" src="'+imgs[i]+'" style="max-width: 230px; width: auto; height: 230px;"></li>';
                        $(".imgs_list").append(_li);
                    }
                }
                var imglist = msg.substring(0,msg.lastIndexOf(","));
                if($("#fileName").val().length>0){
                    $("#fileName").val($("#fileName").val()+","+imglist);
                }else{
                    $("#fileName").val(imglist);
                }
            }else{
                var _li = '<li><img alt="" src="'+msg+'" style="max-width: 230px; width: auto; height: 230px;"></li>';
                $(".imgs_list").append(_li);
                if($("#fileName").val().length>0){
                    $("#fileName").val($("#fileName").val()+","+msg);
                }else{
                    $("#fileName").val(msg);
                }
            }
        }

    }
</script>
</body>
</html>