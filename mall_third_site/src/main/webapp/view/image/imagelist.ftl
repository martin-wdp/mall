<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>商城第三方后台-图片管理</title>
<#assign basePath=request.contextPath>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" >
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
    <link rel="stylesheet" href="${basePath}/css/third.css"/>
    <style type="text/css">
        .grey{
            background:#DDDDDD;
            color:#428bca;
        }
        .modal-backdrop {height: 100%;}
    </style>
    <script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery.zclip.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery.zclip.js"></script>
    <script type="text/javascript" src="${basePath}/js/bootstrap.min.js"></script>
    <script src="${basePath}/js/ripples.min.js"></script>
	<script src="${basePath}/js/material.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/app.js"></script>

    <script type="text/javascript">
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

        $(function(){

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

        /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
        function show(){
            $(".show_text").fadeOut(1000).fadeIn(1000);

            /*
            放在div 执行display: none 之后在调用对应的复制插件。如果在页面加载就调用该插件 ，
            页面执行display: none的时候插件已经调用过了，复制功能就会实效，
            所以要放在页面加载之后调用插件。
            */
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
        }
        setTimeout("show()",1000);

    </script>
</head>

<body>
<#--引入头部-->
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
                <li>我的店铺</a></li>
                <li class="active" style="color: #07d;">图片管理</li>
            </ol>

            <div class="app-content">
                <form class="simple_search" action="<#if map.pb??>${map.pb.url}<#else> </#if>" method="post">
                </form>
                <form class="high_search" action="<#if map.pb??>${map.pb.url}<#else> </#if>" method="post">
                </form>
                    <div>
                        <div class="tit-box">
                            <h3>当前图片<span>共${map.pb.rows}个</span></h3>
                        </div>
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
                                <th>操作</th>
                            </tr>
                            </thead>
                        <#if map.pb.list[0]??>
                        <form action="updateImage.htm" class="upd_Image" method="post">
                            <#list map.pb.list as image>
                              <#--<#if image_index gt 0>-->
                                <tbody>
                                <tr onclick="shareUrl('${image.imageManageUrl!''}')" style="cursor: pointer;" class="a_css">
                                    <td><input type="checkbox" class="check_box" value="${image.imageManageId}" name="imageManageIds"/></td>
                                    <td>${image_index+1}</td>
                                    <td><img alt="" src="${image.imageManageUrl!''}"  height="60"/></td>
                                    <#if image.imageManageUrl??>
                                    <td title="${image.imageManageUrl!}">
                                        <#if image.imageManageUrl?length gt 50 >
                                        ${image.imageManageUrl?substring(0,49)}......
                                        <#else>
                                        ${image.imageManageUrl}
                                        </#if>
                                    </td>
                                    <#else>
                                        <td></td>
                                    </#if>
                                    <td><#if image.imageOnlineDate??>${image.imageOnlineDate?string("yyyy-MM-dd HH:mm:ss") }</#if></td>
                                    <td><button class="btn btn-primary btn-sm"   onclick="removeImage(${image.imageManageId})"  type="button">删除</button></td>
                                    <input type="hidden" id="imageManageId" name="imageManageId" value="${image.imageManageId}"/>
                                </tr>
                                </tbody>
                              <#--</#if>-->
                            </#list>
                        </form>
                        </table>
                            <div class="footer-operation">
                                <div class="ops-left">
                                    <button class="btn btn-primary btn-xs"   onclick="delGrandImages()"  type="button">批量删除</button>
                                </div>
                                <div class="ops-right">
                                    <nav>
                                        <ul class="pagination">
                                            <li>
                                                <a href="javascript:;" aria-label="Previous" onclick="changePageNo(this);" data-role="${map.pb.prePageNo}">
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                            <#if (map.pb.startNo?number>1)>
                                                <li><a href="javascript:;">1</a></li>
                                            </#if>
                                            <#list map.pb.startNo?number .. map.pb.endNo as item>
                                                <li <#if item == map.pb.pageNo>class="active"</#if>><a href="javascript:;" onclick="changePageNo(this);" data-role="${item}">${item}</a></li>
                                            </#list>
                                            <#if (map.pb.totalPages?number>map.pb.endNo)>
                                                <li><a href="javascript:;" onclick="changePageNo(this);" data-role="${map.pb.totalPages}">${pageBean.totalPages}</a></li>
                                            </#if>
                                            <li>
                                                <a href="javascript:;" aria-label="Next" onclick="changePageNo(this);" data-role="${map.pb.nextPageNo}">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                            <script>
                                                /**
                                                 * 分页
                                                 * author IT_kang
                                                 */
                                                function changePageNo(obj){
                                                    /*获取查询的方式标记*/
                                                    var show_flag=$(".show_flag").val();
                                                    if(show_flag==1){
                                                        $(".simple_search").append("<input type='hidden' name='pageNo' value='"+$(obj).attr("data-role")+"' />").submit();
                                                    }else{
                                                        $(".high_search").append("<input type='hidden' name='pageNo' value='"+$(obj).attr("data-role")+"' />").submit();
                                                    }
                                                }
                                            </script>
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



<div class="modal fade" id="addImg" role="dialog" aria-hidden="true">
    <form id="addnewimage" action="${basePath}/saveImageAction.htm" method="post" enctype="multipart/form-data">
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
                            <input type="file" id="up_imgSrc" name="imgSrc" />
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
                    <button class="btn btn-primary" onclick="addImage()" type="button" id="upPic">上传图片</button>
                </div>
            </div>
        </div>
    </form>
</div>



<!--单个删除提示框-->
<div class="modal fade" id="addImg_img" role="dialog" aria-hidden="true" >
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


<!--选中一行弹出层-->
<div class="modal fade" id="delete_img_all" role="dialog" aria-hidden="true" >
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



<!--批量删除-->
<div class="modal fade" id="delete_all_image" role="dialog" aria-hidden="true" >
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

<div class="service-wrap">
    <span class="service-close">×</span>
    <a href="javascript:;" class="service-box">联系客服</a>
</div>

<div class="back-to-top">
    <a href="javascript:;"><i></i>返回顶部</a>
</div>

</body>
<script type="text/javascript">
    /*上传图片*/
    function addImage() {
        var flag = true;
        if($("#up_imgSrc").val()==""){
            $("#up_imgSrc").addClass( "ui-state-error" );
            updateTips( "请选择图片!", $("#imgSrc_tips"));
            $("#up_imgSrc").focus();
            flag = false;
        }else{
            $("#up_imgSrc").removeClass( "ui-state-error" );
            $("#imgSrc_tips").text("").removeClass( "ui-state-highlight" );
            flag = flag && true;
        }
        if(flag){
            $("#addnewimage").submit();
            $("#upPic").attr("disabled","disabled");
        }
    }

    /*删除单个图片弹出*/
    function removeImage(id){
        $("#imageManageId").prop("value",id);
        $('#addImg_img').modal('show');
    }

    var imageManageId = $("#imageManageId").val();
    //单个移除 确定
    function delete_img(){
        $.ajax({
            url:"updateImage.htm?imageManageId="+imageManageId,
            success:function(data){
                if(data>0){
                    location.reload();
                    //关闭弹出窗
                    $('.s_dia').modal('hide')
                }
            }
        });

    }
    // 批量移除
    function  delGrandImages(){
        var bool = false;
        var checks = $("input[type='checkbox']");
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
            $('#delete_img_all').modal('show')
            return null;
        }else{
            $('#delete_all_image').modal("show");
        }

    }

    /*批量删除图片*/
    function delThirdImagesByIds() {
        $(".upd_Image").prop("action","updateImages.htm").submit();
    }

    /*选中一行 增加样式*/
    $(".table tr").click(function(){
        //清空提示语
        $('#copy_inputs').html("");
        $(".table tr").removeClass('grey');
        $(this).addClass('grey');

    })

</script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />

</html>