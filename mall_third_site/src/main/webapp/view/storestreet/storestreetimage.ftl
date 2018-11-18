<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城第三方后台-店铺街</title>
<#assign basePath=request.contextPath>

<link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css">
<link href="${basePath}/css/material.css" rel="stylesheet">
<link href="${basePath}/css/ripples.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/third.css"/>
<style type="text/css">
    .grey{
        background:#DDDDDD;
        color:#428bca;
    }
</style>
<script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.zclip.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.zclip.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/third.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
</head>

<body>
<#-- 引入头部 -->
<#include "../index/indextop.ftl">
<div class="wp">
    <div class="ui-sidebar">
        <div class="sidebar-nav">
            <#import "../index/indexleft.ftl" as leftmenu>
            <@leftmenu.leftmenu '${basePath}' />
        </div>
    </div>

    <div class="app show_text" style="display: none;"">
        <div class="app-container">
            <ol class="breadcrumb">
                <li>您所在的位置</li>
                <li><a href="javascript:;">我的店铺</a></li>
                <li class="active">店铺街</li>
            </ol>

            <div class="app-content">
                <div>
                    <ul class="nav nav-tabs">
                        <li role="presentation" class="active">
                            <a href="javascript:;">店铺街广告图</a>
                        </li>
                    </ul>
                    <div class="cfg-content">
                        <div class="ops-bar">
                            <button class="btn btn-primary btn-sm"  type="button" data-toggle="modal" data-target="#addImg"><i class="glyphicon glyphicon-plus"></i>添加图片</button>
                            <button class="btn btn-primary btn-sm" data-clipboard-target="copy_input" onclick="shareUrls()" id="copy" class="copy" type="button">复制图片地址</button>
                            <span style="display: none; "  id="copy_input"></span>
                            <span style="font-family: 微软雅黑;font-size: 16px; "  id="copy_inputs"></span>
                        </div>
                        <table class="table">
                            <thead>
                            <tr>
                                <th><input type="checkbox" id="check_boxs"/></th>
                                <th>序号</th>
                                <th>图片</th>
                                <th>地址</th>
                                <th>上传时间</th>
                                <th>排序</th>
                                <th>是否开启</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                               <#if storeInfoImageList[0]??>
                               <form action="${basePath}/delAllImage.htm" class="upd_Image_All" method="post">
                                   <#list storeInfoImageList as imagelist>
                                     <tbody>
                                         <tr class="a_css" onclick="shareUrl('${imagelist.imageAddress}')">
                                             <td><input type="checkbox" class="check_box" value="${imagelist.imageId}" id="store_imageId"  name="imageId"/></td>
                                             <td>${imagelist_index+1}</td>
                                             <td><img src="<#if imagelist.imageAddress??>${imagelist.imageAddress}</#if>" height="60px" /></td>
                                             <td title="${imagelist.imageAddress}">
                                                 <#if imagelist.imageAddress?length gt 15 >
                                                 ${imagelist.imageAddress?substring(0,14)}......
                                                 <#else>
                                                 ${imagelist.imageAddress}
                                                 </#if>
                                             </td>
                                             <td><#if imagelist.createtime??>${imagelist.createtime?string("yyyy-MM-dd HH:mm:ss") }</#if></td>
                                             <td>${imagelist.stort!''}</td>
                                             <td>
                                                 <#if imagelist.status??>
                                                     <#if imagelist.status == '0' >
                                                         <span class="tb_right"></span>
                                                     <#else>
                                                         <span class="tb_error"></span>
                                                     </#if>
                                                 </#if>
                                             </td>
                                             <td>
                                                 <div class="btn-group">
                                                 <#if imagelist.status??>
                                                     <#if imagelist.status == '0' >
                                                         <button type="button" data-toggle="modal" data-target="#update_flag" onclick="modifyemptodisable('${imagelist.imageId}','1')"  class="btn btn-primary btn-sm">关闭</button>
                                                     <#else>
                                                         <button type="button" data-toggle="modal" data-target="#update_flag" onclick="modifyemptodisable('${imagelist.imageId}','0')"  class="btn btn-primary btn-sm">开启</button>
                                                     </#if>
                                                 </#if>
                                                 <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                                     <span class="caret"></span>
                                                 </button>
                                                 <ul class="dropdown-menu" role="menu">
                                                     <li><a href="javascript:;" onclick="updateexp(${imagelist.imageId})" >修改</a></li>
                                                     <li><a href="javascript:;" onclick="deleimg(${imagelist.imageId})">删除</a></li>
                                                 </ul>
                                                 </div>
                                             </td>
                                         </tr>
                                    </tbody>
                                </#list>
                            </form>
                        </table>
                        <div class="footer-operation">
                            <div class="ops-left">
                                <button class="btn btn-primary btn-xs" onclick="delGrandImages()"  type="button">批量删除</button>
                            </div>
                            <div class="ops-right">
                                <nav>
                                    <ul class="pagination">
                                        <li class="disabled">
                                            <a href="javascript:;" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                        <li class="active"><a href="javascript:;">1</a></li>
                                        <li>
                                            <a href="javascript:;" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                        <#else>
                          <tbody>
                          <tr>
                              <td colspan="8">暂无信息~</td>
                          </tr>
                          </tbody>
                      </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="service-wrap">
    <span class="service-close">×</span>
    <a href="javascript:;" class="service-box">联系客服</a>
</div>

<div class="back-to-top">
    <a href="javascript:;"><i></i>返回顶部</a>
</div>

<#--<div class="notice-center">-->
    <#--<div class="notice-center-ring"><i></i></div>-->
    <#--<div class="notice-center-wrapper">-->
        <#--<div class="nt-header">-->
            <#--<h3>系统通知（<span>1</span>）</h3>-->
            <#--<a href="javascript:;" class="nt-close">收起》</a>-->
        <#--</div>-->
        <#--<ul class="nt-content">-->
            <#--<li>-->
                <#--<div class="nt-item unread">-->
                    <#--<p>刘仙升于2015-04-08 15:41:23申请提现1.00元，已提现成功，请注意查询您的银行账户。</p>-->
                    <#--<a href="javascript:;">查看提现记录 》</a>-->
                <#--</div>-->
            <#--</li>-->
        <#--</ul>-->
        <#--<div class="nt-footer">-->
            <#--<a href="javascript:;" class="mark-read">全部标记已读</a>-->
            <#--<a href="javascript:;" class="nt-all">查看全部信息</a>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->

<#--<div class="page-help-btn">帮助</div>-->
<div class="page-help-container">
    <div class="page-help-content">
        <p style="color:#f00;">不知道从哪里开始？</p>
        <p>完成掌柜任务，简简单单开店铺！</p>
        <p>点击开始》》<a href="javascript:;">掌柜成长之旅</a></p>
    </div>
    <div class="page-help-operation">
        <a href="javascript:;" class="btn btn-primary btn-sm">进入帮助中心</a>
        <a href="javascript:;" class="btn btn-default btn-sm">建议反馈</a>
    </div>
</div>

<#--至少选中一行弹出框-->
<div class="modal fade" id="delete_all_image" role="dialog" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label">至少选中一行</label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<!--批量删除选中-->
<div class="modal fade" id="addImg_img_all" role="dialog" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label">确认删除吗？</label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" id="primary" onclick="delThirdImagesByIds()" type="button">确定</button>
                <button class="btn btn-primary" type="button" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<#--修改图片信息-->
<div class="modal fade" id="update_third_image" role="dialog" aria-hidden="true">
    <form method='post' action="updateStoremage.htm" class="update_street_image" enctype="multipart/form-data">
        <input type="hidden" value="" name="imageId" class="update_image"/>
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">修改图片</h4>
                </div>
                <div class="modal-body">
                    <input class="shoreExpId_update " type="hidden" name="shoreExpId" value=""/>
                    <div class="form-item">
                        <label class="control-label">上传图片：</label>
                        <div class="controls">
                            <input type="file" id="up_imgSrc" name="imgSrc" />
                        </div>
                    </div>
                    <div class="form-item">
                        <label class="control-label"><b>*</b>排序：</label>
                        <div class="controls">
                            <input type="text" class="form-control stort_image" value="" name="stort" style="width: 80px;"/>
                            <label class="si_word"></label>
                        </div>
                    </div>
                    <div class="form-item">
                        <label class="control-label">是否开启：</label>
                        <div class="controls checkWp">
                            <label class="choose-label"><input name="status" class="status_image"  value="0" type="radio"/>是</label>
                            <label class="choose-label"><input name="status" class="statuss_image" value="1" type="radio"/>否</label>
                            <label class="si_word"></label></dd>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" type="button" onclick="back()" data-dismiss="modal">关闭</button>
                    <button class="btn btn-primary" id="updatebutton" type="button" onclick="update_express()">修改</button>
                </div>
            </div>
        </div>
    </form>
</div>
</div>




<#--更改图片状态弹出框-->
<div class="modal fade" id="update_flag" role="dialog" aria-hidden="true" >
    <form action="${basePath}/updateStoreStreetImage.htm" method="post" class="store_street_image">
        <input type="text" value="" name = "imageId" class="image_id_hide" />
        <input type="text" value="" name = "status" class="status_id_hide" />
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">操作提示</h4>
                </div>
                <div class="modal-body">
                    <div class="form-item">
                        <label class="control-label update_status"></label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" onclick="dodisable()" type="button">确定</button>
                    <button class="btn btn-primary" type="button" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </form>
</div>

<#--删除单个弹出框-->
<div class="modal fade" id="delete_image" role="dialog" aria-hidden="true" >
    <form action="${basePath}/updateStoreStreetImage.htm" method="post" class="delete_store_image">
        <input type="hidden" value="" name="imageId" class="exp_id_hide" />
        <input type="hidden" value="" name="delfage" class="flag_id_hide" />
    </form>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label">确认删除吗？</label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="delete_img()" type="button">确定</button>
                <button class="btn btn-primary" type="button" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<#--上传图片弹出框-->
<div class="modal fade" id="addImg" role="dialog" aria-hidden="true">
    <form class="add_store_image" action="saveStoreStreetImage.htm" method="post" enctype="multipart/form-data">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加图片</h4>
                </div>
                <div class="modal-body">
                    <div class="form-item">
                        <label class="control-label">上传图片：</label>
                        <div class="controls">
                            <input type="file" id="save_imgSrc" name="imgSrc" />
                            <label class="si_imgSrc"></label>
                        </div>
                    </div>
                    <div class="form-item">
                        <label class="control-label"><b>*</b>排序：</label>
                        <div class="controls">
                            <input type="text" class="form-control save_stort_image"  value="" name="stort" style="width: 80px;"/>
                            <label class="si_word"></label>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
                    <button class="btn btn-primary" onclick="addStoreImage()" type="button">上传图片</button>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">

    $(function(){
    	$.material.init();
    
        //复制成功弹出复制成功窗口
        $('#copy').zclip({
            path:'${basePath}/js/ZeroClipboard.swf',
            copy:function(){
                return  $('#copy_input').html();
            },
            afterCopy:function(){
            }
        });



        /*全选*/
        $("#check_boxs").click(function(){
            var sc_id="";
            if($(this).prop("checked")){
                $(".check_box").each(function(){
                    if(! $(this).prop("checked")){
                        $(this).prop("checked",true);
                    }
                });
                $(".check_box").each(function(){
                    sc_id += $(this).val()+"|";
                });
            }else{
                $(".check_box").each(function(){
                    if($(this).prop("checked")){
                        $(this).prop("checked",false);
                    }
                });
            }
        });
    });

    /*选中一行 增加样式*/
    $(".table tr").click(function(){
        //清空提示语
        $('#copy_inputs').html("");
        $(".table tr").removeClass('grey');
        $(this).addClass('grey');

    })

    //复制图片链接
    function shareUrl(url){
        $('#copy_input').html(url);
    }
    //判断是否选中图片 给出相应的提示语句
    function shareUrls(){
        if("" == $('#copy_input').html()|| $('#copy_input').html() == "请选择要复制的文件"){
            $('#copy_inputs').html("请选择要复制的文件");
        }else{
            $('#copy_inputs').html("复制成功");
        }
    }
    //修改图片信息
    function update_express(){
        $('.update_street_image').submit();
    }

    /*根据ID删除单个图片信息*/
    function deleimg(id){
      $('#addImg_img_all').modal("hide");
       $('.exp_id_hide').val(id);
       $('.flag_id_hide').val(1);
      $('#delete_image').modal("show");
    }
    //单个移除
    function delete_img(){
        $('.delete_store_image').submit();
    }
    //根据主键获取单个图片的详细信息用于修改
    function updateexp(id){
        $(".update_image").val(id);
        var url="selectStoreStreetImageById.htm?imageId="+id;
        $.ajax({
            type: 'post',
            url:url,
            async:true,
            success: function(data) {
                if(data!=null){
                    /*设置物流属性的值*/
                    $('.stort_image').val(data.stort);
                    if(data.status==0){
                        $(".status_image").attr("checked","checked");
                    }else{
                        $(".statuss_image").attr("checked","checked");
                    }

                }
                $('#update_third_image').modal('show')
            }

        });
    }



    /*上传图片*/
    function addStoreImage() {
        var flag = true;
        if($("#save_imgSrc").val()==""){
           $('.si_imgSrc').html("未选择图片");
           $('.si_imgSrc').css("color","red");
            flag = false;
            return false;
        }else{
            $('.si_imgSrc').html("");
            flag == true;
        }

        if($(".save_stort_image").val()==""){
            $('.si_word').html("未填写排序");
            $('.si_word').css("color","red");
            flag = false;
            return false;
        }else{
            $('.si_word').html("");
            flag == true;
        }

        if(flag){
            $(".add_store_image").submit();
        }
    }

    //控制图片默认与否，删除按钮
    function modifyemptodisable(custId,flag){
        $(".image_id_hide").val(custId);
        $(".status_id_hide").val(flag);
        //判断默认条数
        if(flag == 0 ){
            $(".update_status").html("你确定要开启吗?");
        }else {
            $(".update_status").html("你确定要关闭吗?");
        }

    }


    /*更改图片状态*/
    function dodisable(){
        $('.store_street_image').submit();
    }

    /*删除图片*/
    function removeImage(id){
        $(".control-label").html("确认删除吗？");
        $("#imageManageId").prop("value",id);
    }
    var imageManageId = $("#imageManageId").val();
    // 批量移除
    function  delGrandImages(){
        var bool = false;
        var checks = $("#store_imageId");
        var checkGroup = new Array();
        for (var i = 0; i < checks.length; i++) {
            var e = checks[i];
            if (e.checked == true) {
                bool = true;
                checkGroup.push(e);
            }
        }
        //是否选中
        if(bool==false){
            $('#delete_all_image').modal();
            return null;
        }else{
            $('#addImg_img_all').modal();
        }
    }

    /*批量删除图片*/
    function delThirdImagesByIds() {
        $(".upd_Image_All").submit();
    }

    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);

</script>
</div>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
</body>
</html>
