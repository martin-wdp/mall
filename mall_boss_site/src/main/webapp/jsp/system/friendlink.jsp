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
    <link href="<%=basePath%>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.css" rel="stylesheet">

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
<div class="page_body container-fluid">
    <div class="row">
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                 <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild} <small>(共${pb.rows}条)</small></h2>

                <div class="common_data p20">

                    <div class="filter_area">
                        <form role="form" class="form-inline">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">链接名称</span>
                                    <input type="text" class="form-control" id="searchText" value="${selectBean.searchText }">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">链接地址</span>
                                    <input type="text" class="form-control" id="searchTextTwo" value="${selectBean.searchTextTwo }">
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-primary" onclick="friendlinkss()">搜索</button>
                            </div>
                        </form>
                    </div>
                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="$('#addLinks').modal('show')">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="dellinksYN()">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>

                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th><input type="checkbox" name="linkId" onclick="allunchecked(this,'linkId');"></th>
                            <th>链接名称</th>
                            <th>链接地址</th>
                            <th>链接图片</th>
                            <th>排序</th>
                            <th class="w100">是否显示</th>
                            <th class="w100">修改时间</th>
                            <th width="150">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="friendlink" items="${pb.list }" varStatus="status">
                            <tr>
                                <td><input type="checkbox" name="linkId" value="${friendlink.linkId }"></td>
                                <td>${friendlink.linkName }</td>
                                <td>${friendlink.linkUrl }</td>
                                <td><img alt="" src="${friendlink.linkLogo }" height="50"></td>
                                <td>${friendlink.linkSort }</td>
                                <td>
                                	<c:if test="${friendlink.isHidden eq 1 }">
                                    <span class="label label-default">否</span>
                                    </c:if>
                                    <c:if test="${friendlink.isHidden eq 0 }">
                                    <span class="label label-success">是</span>
                                    </c:if>
                                </td>
                                <td>
                                    <fmt:formatDate value="${friendlink.modifyTime }" type="both" var="mdate"/>
                                        ${mdate }
                                </td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default" onclick="updatelinkmodal(${friendlink.linkId })">编辑</button>
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                            <span class="caret"></span>
                                            <span class="sr-only">Toggle Dropdown</span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="#" onclick="dellinkYN(${friendlink.linkId })">删除</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>

                    <c:import url="../page/searchPag.jsp">
                        <c:param name="pageBean" value="${pb }"/>
                        <c:param name="path" value="../"></c:param>
                    </c:import>

                </div>

            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="dellink"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" id="dellinkId" />
                    <div class="form-group">
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <p>
                                你确定要删除吗？
                            </p>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="dellink();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="dellinks"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">

                    <div class="form-group">
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <p>
                                你确定要删除吗？
                            </p>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="dellinks();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addLinks"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加友情链接</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="addform" method="post" enctype="multipart/form-data" action="addlink.htm?CSRFToken=${token}">
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>链接名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control required" name="linkName" id="add_linkname">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>链接地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control required url" name="linkUrl" id="add_linkurl">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">友情链接图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                            <input type="hidden" name="linkLogo" id="linkLogoImg" class="linkLogoImg">
                            <button type="button" class="btn btn-default choose_img_btn">选择图片</button>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">图片预览</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <img alt="" src="" width="100%" id="img" class="lookImg">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3">
                            <input type="text" class="form-control required digits" maxlength="3" name="linkSort" value="1" id="add_linksort">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否显示：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="isHidden" value="0" checked="checked"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isHidden" value="1"> 否
                            </label>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="addfriendlink()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="updateLinks"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">修改友情链接</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="updateform" method="post" enctype="multipart/form-data" action="updatelink.htm?CSRFToken=${token}">
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>链接名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="hidden" name="linkId" id="up_linkid">
                            <input type="text" class="form-control required" name="linkName" id="up_linkname">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>链接地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control required url" name="linkUrl" id="up_linkurl">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">友情链接图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                            <input type="hidden" name="linkLogo" id="up_linkLogoImg" class="linkLogoImg">
                            <button type="button" class="btn btn-default choose_img_btn">选择图片</button>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">图片预览</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <img alt="" src="" width="100%" id="up_Img" class="lookImg">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-3">
                            <input type="text" class="form-control required digits" maxlength="3" name="linkSort" value="1" id="up_linksort">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否显示：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="isHidden" id="isHidden0" value="0"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isHidden" id="isHidden1" value="1" checked="checked"> 否
                            </label>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="updatelink()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<input type="hidden" value="friendlinkform" id="formId"/>
<input type="hidden" value="friendlink.htm" id="formName"/>
<form role="form"  method="post" action="friendlink.htm" id="friendlinkform">
    <input type="hidden" name="searchText" value="${selectBean.searchText }" id="searchTextstr" />
    <input type="hidden" name="searchTextTwo" value="${selectBean.searchTextTwo }" id="searchTextTwostr" />
    <input type="hidden" name="condition" value="1" id="condition" />
</form>
<input id="CSRFToken" type="hidden" name="CSRFToken" value="${token}" />
<input id="basePath" type="hidden"  value="${basePath}" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath%>js/bootstrap-select.min.js"></script>
<script src="<%=basePath%>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath%>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/system/friendlink.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
</body>
</html>
