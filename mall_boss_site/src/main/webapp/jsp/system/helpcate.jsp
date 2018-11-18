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
<div class="container-fluid page_body">
    <div class="row">
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">

            <div class="main_cont">
                 <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild}<small>(共${pb.rows }条)</small></h2>

                <div class="common_data p20">

                    <div class="filter_area">
                        <form role="form" class="form-inline">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">分类名称</span>
                                    <input type="text" class="form-control" value="${selectBean.searchText }" id="searchText">
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-primary" onclick="helpss();">搜索</button>
                            </div>
                        </form>
                    </div>
                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="addModal()">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" onclick="delhelpsYN();" class="btn btn-info">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>

                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th><input type="checkbox" name="helpcateId" onclick="allunchecked(this,'helpcateId');"></th>
                            <th>分类图片</th>
                            <th>分类名称</th>
                            <th>分类排序</th>
                            <th class="w100">创建时间</th>
                            <th>是否显示在底部</th>
                            <th width="150">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="helpcate" items="${pb.list }" varStatus="status">
                            <tr>
                                <td><input type="checkbox" id="helpcateId" name="helpcateId" value="${helpcate.helpcateId }" /></td>
                                <td><img alt="" height="50" src="${helpcate.helpcateImg }"></td>
                                <td>${helpcate.helpcateName }</td>
                                <td>${helpcate.helpcateSort }</td>
                                <td><fmt:formatDate value="${helpcate.createTime }" var="cdate" type="both"/>
                                        ${cdate }</td>
                                <td>
                                    <c:if test="${helpcate.homeFloor eq 0}"><a href="javascript:;"><span class="label label-default">否</span></a></c:if>
                                    <c:if test="${helpcate.homeFloor eq 1}"><a href="javascript:;"><span class="label label-success">是</span></a></c:if>

                                </td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default" onclick="updatemodal(${helpcate.helpcateId })">编辑</button>
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                            <span class="caret"></span>
                                            <span class="sr-only">Toggle Dropdown</span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="#" onclick="delhelpYN(${helpcate.helpcateId });">删除</a></li>
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
<div class="modal fade" id="addHelpCate"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加帮助分类</h4>
            </div>
            <div class="modal-body">
                <form id="addform" class="form-horizontal" method="post" enctype="multipart/form-data" action="addhelpcate.htm?CSRFToken=${token}">
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>分类名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control required" name="helpcateName" id="add_catename">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>分类排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <input type="text" class="form-control digits required" maxlength="3" id="add_catesort" value="1" name="helpcateSort">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">分类图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file" name="picFile" id="picFile"></p>
                            <input type="hidden" name="helpcateImg" id="helpcateImg">
                            <iframe id="uploadFrame" name="uploadFrame" style="display:none;"></iframe>
                        </div>
                        <div class="col-sm-3">
		                    <a href="javascript:;" class="helpcateimg help_tips" data-original-title="" title="">
		                        <i class="icon iconfont"></i>
		                    </a>
		           		</div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">预览图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img id="imgView" width="80" src="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否在底部显示：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" checked="checked" name="homeFloor" value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="homeFloor" value="0"> 否
                            </label>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="addhelp()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="updateHelpCate"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">修改帮助分类</h4>
            </div>
            <div class="modal-body">
                <form id="updateform" class="form-horizontal" method="post" enctype="multipart/form-data" action="updatehelpcate.htm?CSRFToken=${token}">
                    <input type="hidden" name="helpcateId" id="up_cateid">
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>分类名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control required" name="helpcateName" id="up_catename">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>分类排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <input type="text" class="form-control digits required" maxlength="3" id="up_catesort" value="1" name="helpcateSort">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">分类图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file" name="picFile" id="up_picFile"></p>
                            <input type="hidden" name="helpcateImg" id="up_helpcateImg">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">预览图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img id="up_imghead" width="80" src="">
                        </div>
                        <div class="col-sm-3">
		                    <a href="javascript:;" class="helpcateimg help_tips" data-original-title="" title="">
		                        <i class="icon iconfont"></i>
		                    </a>
		           		</div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否在底部显示：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" id="radio1" checked="checked" name="homeFloor" value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" id="radio0" name="homeFloor" value="0"> 否
                            </label>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="updatehelp()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="delhelp"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" id="delhelpId" />
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
                <button type="button" class="btn btn-primary" onclick="delhelp();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="delhelps"  role="dialog">
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
                <button type="button" class="btn btn-primary" onclick="delhelps();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<input type="hidden" value="tohelpform" id="formId"/>
<input type="hidden" value="tohelpcate.htm" id="formName"/>
<form role="form"  method="post" action="tohelpcate.htm" id="tohelpform">
    <input type="hidden" name="searchText" value="${selectBean.searchText }" id="searchTextstr" />
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
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath%>js/system/helpcate.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath %>js/common/preview_image.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
</body>
</html>
