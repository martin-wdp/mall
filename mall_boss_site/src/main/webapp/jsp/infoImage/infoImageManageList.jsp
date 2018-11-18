<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/4/1
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>

    <!-- Bootstrap -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.css" rel="stylesheet">
    <link href="<%=basePath %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        .main_cont{
            background: #fff;
            overflow-y: visible;
        }
    </style>
</head>
<body>
<!-- 引用头 -->
<jsp:include page="../page/header.jsp"></jsp:include>
<div class="page_body container-fluid">
    <div class="row">
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild} <small>(共${pageBean.rows}张)</small></h2>

                <div class="common_data p20">

                    <div class="filter_area" style="margin-left:8px;">
                        <input type="hidden" value="searchForm" id="formId">
                        <input type="hidden" value="queryImageManageByPbAndCid.htm" id="formName">
                        <form role="form" class="form-inline" id="searchForm" action="queryImageManageByPbAndCid.htm">
                            <input type="hidden" name="CSRFToken" value="${token}">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">图片分类</span>
                                    <input type="hidden" name="classifyId" id="search_classifyId" value="-1"/>
                                    <input type="text" class="form-control required" id="text_classifyId" data-toggle="dropdown"
                                           readonly>
                                    <div class="dropdown-menu" role="menu" id="cateChoose">
                                        <ul id="treeDemo" class="ztree"></ul>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">开始时间</span>

                                    <div class="input-group date form_datetime" id="startpicker">
                                        <input class="form-control" id="startDate" style="width: 150px" type="text"
                                               value="${startDate}" readonly>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">结束时间</span>

                                    <div class="input-group date form_datetime" id="endpicker">
                                        <input class="form-control" type="text" style="width: 150px" id="endDate"
                                               value="${endDate}" readonly>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-primary" onclick="queryNextBigPage(1);">搜索</button>
                            </div>
                        </form>
                    </div>

                    <div class="data_ctrl_area mb20" style="margin-left:8px;">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="$('#uploadImg').modal('show')">
                                <i class="glyphicon glyphicon-plus"></i> 添加图片
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>

                    <div class="pic_list" id="categoryProductContainer">

                    </div>

                    <div class="table_foot" style="display:none;">
                        <div class="table_pagenav ">
                            <div class="meneame" style="text-align: center;" id="pageDiv">

                            </div>
                        </div>
                        <div class="clr"></div>
                    </div>


                </div>
            </div>

        </div>
    </div>
</div>

<div class="modal fade" id="uploadImg">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">上传图片</h4>
            </div>
            <form id="uploadImgForm" action="saveImageManagerAction.htm?CSRFToken=${token}" method="post" enctype="multipart/form-data">
                <div class="modal-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-sm-5"><span class="text-danger">*</span>图片分类：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-6">
                                <input type="hidden" name="classifyId" id="update_search_classifyId" value="-1"/>
                                <input type="text" class="form-control required" id="update_text_classifyId" data-toggle="dropdown"
                                       readonly>
                                <div class="dropdown-menu" role="menu" id="cateChoose1">
                                    <ul id="treeDemo1" class="ztree"></ul>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5"><span class="text-danger">*</span>上传图片：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-6">
                                <p class="pt5"><input type="file" class="required" name="imgSrc"></p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="editImage">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改图片分类</h4>
            </div>
            <form id="updateImgForm" action="" method="post">
                <input type="hidden" name="CSRFToken" value="${token}" id="CSRFToken">
                <input type="hidden" name="imageManageId" id="up_imageManageId"/>
                <input type="hidden" name="oldClassifyId" id="oldClassifyId" value=""/>
                <div class="modal-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-sm-5"><span class="text-danger">*</span>选择图片分类</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-6">
                                <input type="hidden" name="classifyId" id="up_classifyId" value="-1"/>
                                <input type="text" class="form-control required" id="up_text_classifyId" data-toggle="dropdown"
                                       readonly>
                                <div class="dropdown-menu" role="menu" id="cateChoose2">
                                    <ul id="treeDemo2" class="ztree"></ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="submitUpdateImageForm()">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

<!-- Include all compiled plugins (below), or include individual files as needed -->
<%--<script type="text/javascript" src="<%=basePath%>js/jquery.min.js" ></script>--%>
<%--<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>--%>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js" ></script>
<script type="text/javascript" src="<%=basePath%>js/summernote.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.pinbox.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common/common_alert.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common/common_date.js"></script>
<script type="text/javascript" src="<%=basePath%>js/system/image_list.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.zclip.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.zclip.js"></script>
<script type="text/javascript" src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" type="text/javascript">
    var setting = {
        data : {
            key : { },
            simpleData :{ enable : true }
        },
        callback:{ beforeClick:beforeClick, onClick: zTreeOnClick }
    };

    $(function(){
        $('.copyClass').zclip({
            path:'<%=basePath%>js/ZeroClipboard.swf',
            copy:function(){
                return $(this).attr("rot-value");
            },
            afterCopy:function(){
                showTipAlert("已复制到剪贴板");
            }
        });

        $('#categoryProductContainer').pinbox().hide(0).fadeIn(1000);
        $(window).scroll(function(){
            if($(window).scrollTop()>=$(document).height()-$(window).height()){
                queryNextPageImage();
            }
        });
        queryNextBigPage(1);

        $("#text_classifyId").val('全部');
        $("#search_classifyId").val(-1);
        $("#update_text_classifyId").val('全部');
        $("#update_search_classifyId").val(-1);
        $("#up_text_classifyId").val('全部');
        $("#up_classifyId").val(-1);
        /**查询商品分类放在树形控件中*/
        $.get("ajaxQueryImageCateForAdd.htm?CSRFToken=${token}", function (data){
            var zNodes = new Array();
            var no={ id:-1,pId:null,name:'全部',open:true }
            zNodes.push(no);
            for (var i = 0; i < data.length; i++){
                var node;
                if(data[i].isHasImg==0){
                    node = { id : data[i].classifyId, pId : data[i].parentId, name : data[i].classifyName, open : true,click:false };
                }else{
                    node = { id : data[i].classifyId, pId : data[i].parentId, name : data[i].classifyName, open : true };
                }
                zNodes.push(node);
            }
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            $.fn.zTree.init($("#treeDemo1"), setting, zNodes);
            $.fn.zTree.init($("#treeDemo2"), setting, zNodes);
        });

        //点击新增出现下拉内容,光标移开下拉内容消失
        $('#text_classifyId').click(function(){
            $('#cateChoose').show().css(
                    { 'left' : '80px', 'minWidth' : '180px' }
            );
            $('#cateChoose').mouseleave(function()
                    { $(this).hide(); }
            );
        });
        $('#update_text_classifyId').click(function(){
            $('#cateChoose1').show().css(
                    { 'left' : '0px', 'minWidth' : '180px' }
            );
            $('#cateChoose1').mouseleave(function()
                    { $(this).hide(); }
            );
        });
        $('#up_text_classifyId').click(function(){
            $('#cateChoose2').show().css(
                    { 'left' : '0px', 'minWidth' : '180px' }
            );
            $('#cateChoose2').mouseleave(function()
                    { $(this).hide(); }
            );
        });

    });

    function zTreeOnClick(event, treeId, treeNode) {
        if(treeNode.id==-1){
            $("#search_classifyId").val(-1);
            $("#update_search_classifyId").val(-1);
            $("#up_classifyId").val(-1);
        }
        else{
            $("#search_classifyId").val(treeNode.id);
            $("#update_search_classifyId").val(treeNode.id);
            $("#up_classifyId").val(treeNode.id);
        }
        $("#text_classifyId").val(treeNode.name);
        $("#update_text_classifyId").val(treeNode.name);
        $("#up_text_classifyId").val(treeNode.name);
    };

    function beforeClick(treeId, treeNode, clickFlag) {
        return (treeNode.click != false);
    }
</script>
</body>
</html>

