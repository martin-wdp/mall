<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
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
    <link href="<%=basePath %>/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>/css/font-awesome.min.css">
    <link href="<%=basePath %>/iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>/css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath %>/css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- 引用头 -->
<jsp:include page="../page/header.jsp"></jsp:include>

<div class="container-fluid page_body">
    <div class="row">

        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                 <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">编辑模板信息</h2>

                <div class="common_form common_form_lg">
                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="javascript:location.href='queryTempByType.htm'">
                                <i class="icon iconfont">&#xe614;</i> 返回模板列表
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form id="updateTmep" class="form-horizontal" role="form"  method="post" action="updateTempInfo.htm?CSRFToken=${token}"
                          enctype="multipart/form-data">

                        <input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">
                        <input type="hidden" name="tempId" value="${temp.tempId }">
                        <div class="form-group">
                            <label class="control-label col-sm-4">模板名称：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-12">
                                <input type="text" name="tempName" id="tempNameUpdate"
                                       value="${temp.tempName}" class="form-control required specstr">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-4">图片URL：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-12">
                                <%--<p class="pt5">--%>
                                <input type="file" name="imgSrc" id="imgChoose" class="form-control">
                                <iframe id="uploadFrame" name="uploadFrame" style="display:none;"></iframe>
                                <%--<a href="javascript:;" id="choose">--%>
                                <%--<img src="${temp.tempImageUrl}" id="updateImg" style="width:30px;height:30px;"/></a>--%>
                                <%--</p>--%>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-4">预览图片：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-12">
                                <%--<p class="pt5"><input type="hidden" name="tempImageUrl" id="tempImageUrlUpdate"--%>
                                <%--value="${temp.tempImageUrl}">--%>
                                <%--<a href="javascript:;" id="choose">--%>
                                <%--<img src="${temp.tempImageUrl}" id="updateImg" style="width:30px;height:30px;"/></a>--%>
                                <%--</p>--%>
                                <img id="preview_image" src="${temp.tempImageUrl}" width="108" height="36"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-4">描述信息：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-12">
                                <textarea  name="des" class="form-control required specstr" rows="5">${temp.des }</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-4">版本信息：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-8">
                                <label class="control-label col-sm-3"> ${temp.version}</label>
                                <input type="hidden"  name="version" id="versionUpdate"
                                       value="${temp.version}"  class="form-control" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-4">模板类型：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-8">

                                <input type="hidden" name="tempType" value="${temp.tempType}"/>
                                <c:if test="${137 == temp.tempType}">
                                   <label class="control-label col-sm-5"> 首页模板</label>
                                </c:if>
                                <c:if test="${139 == temp.tempType}">
                                    <label class="control-label col-sm-5" style="width: 70px;"> 频道页模板</label>
                                    <%--<input type="text" class="form-control" value="" readonly>--%>
                                </c:if>

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-4">头部区域：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-15">

                                <label class="radio-inline"><input type="radio"  value="0"
                                                           <c:if test="${temp.headArea == 0 }">checked="true"</c:if>
                                                           name="headArea" >不显示</label>
                                <label class="radio-inline"><input type="radio" value="1"
                                                           <c:if test="${temp.headArea == 1 }">checked="true"</c:if>
                                                           name="headArea" >显示</label>
                            </div>
                        </div>
                        <%--<div class="form-group">--%>
                            <%--<label class="control-label col-sm-4">页面导航：</label>--%>
                            <%--<div class="col-sm-1"></div>--%>
                            <%--<div class="col-sm-15">--%>
                                <%--<label class="radio-inline"><input type="radio"  value="0"--%>
                                                           <%--<c:if test="${temp.pageNav == 0 }">checked="true"</c:if>--%>
                                                           <%--name="pageNav">不显示</label>--%>
                                <%--<label class="radio-inline"><input type="radio" value="1"--%>
                                                           <%--<c:if test="${temp.pageNav == 1 }">checked="true"</c:if>--%>
                                                           <%--name="pageNav">显示</label>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="form-group">
                            <label class="control-label col-sm-4">商品分类区域：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-15">
                                <label class="radio-inline"><input type="radio" value="0"
                                                           <c:if test="${temp.goodClassifyArea == 0 }">checked="true"</c:if>
                                                           name="goodClassifyArea">不显示</label> <label
                                    class="radio-inline"><input type="radio"  value="1"
                                                        <c:if test="${temp.goodClassifyArea == 1 }">checked="true"</c:if>
                                                        name="goodClassifyArea">显示</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-4">轮播大图广告：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-15">

                                <label class="radio-inline"><input type="radio" value="0"
                                                           <c:if test="${temp.rollBigAdImage == 0 }">checked="true"</c:if>
                                                           name="rollBigAdImage" >不显示</label>
                                <label class="radio-inline"><input type="radio" value="1"
                                                        <c:if test="${temp.rollBigAdImage == 1 }">checked="true"</c:if>
                                                        name="rollBigAdImage">显示</label>
                            </div>
                        </div>
                        <%--<div class="form-group">--%>
                            <%--<label class="control-label col-sm-4">轮播小图广告：</label>--%>
                            <%--<div class="col-sm-1"></div>--%>
                            <%--<div class="col-sm-15">--%>
                                <%--<label class="radio-inline"><input type="radio"  value="0"--%>
                                                           <%--<c:if test="${temp.rollSmallAdImage == 0 }">checked="true"</c:if>--%>
                                                           <%--name="rollSmallAdImage">不显示</label>--%>
                                <%--<label class="radio-inline"><input type="radio"  value="1"--%>
                                                        <%--<c:if test="${temp.rollSmallAdImage == 1 }">checked="true"</c:if>--%>
                                                        <%--name="rollSmallAdImage">显示</label>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="control-label col-sm-4">新闻公告：</label>--%>
                            <%--<div class="col-sm-1"></div>--%>
                            <%--<div class="col-sm-15">--%>
                                <%--<label class="radio-inline"><input type="radio"  value="0"--%>
                                                           <%--<c:if test="${temp.newNotice == 0 }">checked="true"</c:if>--%>
                                                           <%--name="newNotice" >不显示</label>--%>
                                <%--<label class="radio-inline"><input type="radio" value="1"--%>
                                                           <%--<c:if test="${temp.newNotice == 1 }">checked="true"</c:if>--%>
                                                           <%--name="newNotice">显示</label>--%>

                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="form-group">
                            <label class="control-label col-sm-4">首页图片：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-15">
                                <label class="radio-inline"><input type="radio"  value="0"
                                                           <c:if test="${temp.indexImage == 0 }">checked="true"</c:if>
                                                           name="indexImage" >不显示</label>
                                <label class="radio-inline"><input type="radio" value="1"
                                                           <c:if test="${temp.indexImage == 1 }">checked="true"</c:if>
                                                           name="indexImage">显示</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-4">品牌：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-15">
                                <label class="radio-inline"><input type="radio" value="0"
                                                           <c:if test="${temp.trademark == 0 }">checked="true"</c:if>
                                                           name="trademark" >不显示</label>
                                <label class="radio-inline"><input type="radio" value="1"
                                                           <c:if test="${temp.trademark == 1 }">checked="true"</c:if>
                                                           name="trademark">显示</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-4">楼层：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-15">
                                <label class="radio-inline"><input type="radio" value="0"
                                                           <c:if test="${temp.floor == 0 }">checked="true"</c:if> name="floor">不显示</label> <label
                                    class="radio-inline"><input type="radio"value="1"
                                                        <c:if test="${temp.floor == 1 }">checked="true"</c:if> name="floor">显示</label>
                            </div>
                        </div>



                        <div class="form-group">
                            <div class="col-sm-offset-5 col-sm-8">
                                <button type="button" class="btn btn-primary" id="channelUpdate">确定</button>
                            </div>
                        </div>
                    </form>
                </div>

            </div>

        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addPickPoint"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加自提点</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-5">自提点名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <input type="text" class="form-control w200">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">自提点地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control w200">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">选择地区：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-18">
                            <select class="inline" data-live-search="true">
                                <option>选择省份</option>
                                <option>江苏省</option>
                            </select>
                            <select class="inline" data-live-search="true">
                                <option>选择城市</option>
                                <option>南京市</option>
                            </select>
                            <select class="inline" data-live-search="true">
                                <option>选择区县</option>
                                <option>建邺区</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <label class="radio-inline">
                                <input type="radio" name="open" id="open3" value="option1" checked> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="open" id="open4" value="option2"> 否
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>/js/bootstrap.min.js"></script>
<script src="<%=basePath %>/js/summernote.min.js"></script>
<script src="<%=basePath %>/js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>/js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/temp/update_temp.js"></script>
<script src="<%=basePath %>js/select2.min.js"></script>
<script>
    $(function(){
        /* 为选定的select下拉菜单开启搜索提示 */
        $('select[data-live-search="true"]').select2();
        /* 为选定的select下拉菜单开启搜索提示 END */
        //上传图片
        $("#choose").click(function(){
            art.dialog.open('queryImageManageByPbAndCidForChoose.htm?CSRFToken=0e0a85a0-8db7-471c-9035-900e855b3bb7&size=10000', {
                lock: true,
                opacity:0.3,
                width: '900px',
                height: '400px',
                title: '选择图片'
            });
        });
    });
    //图片回调
    function saveChoooseImage(url) {
        if(typeof (url) != 'string') {
            url = url[0];
        }
        if(url.indexOf(',')!=-1){
            url=url.substring(0,url.indexOf(','));
        }
        $("#tempImageUrlUpdate").val(url);
        $("#updateImg").attr("src",url);
    }
</script>
</body>
</html>
