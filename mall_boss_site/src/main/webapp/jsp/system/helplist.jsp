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
                                    <span class="input-group-addon">帮助标题</span>
                                    <input type="text" class="form-control" name="searchText" value="${selectBean.searchText }" id="searchText">
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
                            <button type="button" class="btn btn-info" onclick="addmodal();">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="delhelpsYN();">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>

                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th><input type="checkbox" name="helpId" onclick="allunchecked(this,'helpId');"></th>
                             <th>帮助分类</th>
                             <th>帮助标题</th>
                            <th>作者</th>
                            <th>排序</th>
                            <th class="w100">创建时间</th> 
                            <th width="150">操作</th>  
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="helpcenter" items="${pb.list }" varStatus="status">
                            <tr>
                                <td><input type="checkbox" name="helpId"  value="${helpcenter.helpId }"></td>
                                 <td>${helpcenter.helpcateName }</td>
                                 <td style="text-align:left;">${helpcenter.helpTitle }</td>
                                <td>${helpcenter.helpAuthor }</td>
                                <td>${helpcenter.helpSort }</td>
                                <td>
                                    <fmt:formatDate value="${helpcenter.createTime }" var="cdate" type="both"/>
                                        ${cdate }
                                </td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default" onclick="updatemodal(${helpcenter.helpId});">编辑</button>
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                            <span class="caret"></span>
                                            <span class="sr-only">Toggle Dropdown</span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="#" onclick="delhelpYN(${helpcenter.helpId });">删除</a></li>
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
<div class="modal fade" id="addHelpArticle"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加帮助</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="helpForm" method="POST"  enctype="multipart/form-data" action="addhelp.htm?CSRFToken=${token}">
                    <div class="form-group">
                        <label class="control-label col-sm-5">所属分类：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                            <select class="form-control" name="helpcateId" id="helpcateId">
                                <c:forEach items="${helpcate}" var="helpcate">
                                    <option value="${helpcate.helpcateId }" <c:if test="${help.helpcateId==helpcate.helpcateId }">selected="selected"</c:if>>${helpcate.helpcateName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>帮助标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" class="form-control required" name="helpTitle" id="helpTitle">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">帮助图标：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file" name="picFile" id="picFile"></p>
                            <input type="hidden" name="helpImg" id="helpImg">
                            <iframe id="uploadFrame" name="uploadFrame" style="display:none;"></iframe>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">预览图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img alt="" src="" id="addhelpPic" height="50">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>作者：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <input type="text" class="form-control required" name="helpAuthor" id="helpAuthor">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>帮助排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-2">
                            <input type="text" class="form-control digits required" value="1" id="helpSort" maxlength="3" name="helpSort">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否在底部显示：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="isFoot" value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isFoot" checked="checked" value="0"> 否
                            </label>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">帮助内容：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-15">
                            <div class="summernote" id="helpContentstr"></div>
                        </div>
                        <input type="hidden" id="helpContent"  name="helpContent" >
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="addhelp();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="updateHelpArticle"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">修改帮助</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="up_helpForm" method="POST"  enctype="multipart/form-data" action="updatehelp.htm?CSRFToken=${token}">
                    <div class="form-group">
                        <input type="hidden" name="helpId" id="up_helpid" value="">
                        <label class="control-label col-sm-5">所属分类：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                            <select class="form-control" name="helpcateId" id="up_helpcateId">
                                <c:forEach items="${helpcate}" var="helpcate">
                                    <option value="${helpcate.helpcateId }" <c:if test="${help.helpcateId==helpcate.helpcateId }">selected="selected"</c:if>>${helpcate.helpcateName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>帮助标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" class="form-control required" name="helpTitle" id="up_helpTitle">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">帮助图标：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file" name="picFile" id="up_picFile"></p>
                            <input type="hidden" name="helpImg" id="up_helpImg">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">预览图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img alt="" id="up_helpPic" src="" height="50">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>作者：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <input type="text" class="form-control required" name="helpAuthor" id="up_helpAuthor">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>帮助排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-2">
                            <input type="text" class="form-control digits required" value="1" id="up_helpSort" maxlength="3" name="helpSort">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">是否在底部显示：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" id="isFoot1" name="isFoot" value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" id="isFoot0" name="isFoot" checked="checked" value="0"> 否
                            </label>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">帮助内容：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-15">
                            <div class="summernote" id="up_helpContentstr"></div>
                        </div>
                        <input type="hidden" id="up_helpContent"  name="helpContent" >
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="updatehelp();">确定</button>
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

<input type="hidden" value="tohelpcenterform" id="formId"/>
<input type="hidden" value="findcenter.htm" id="formName"/>
<form role="form" action="findcenter.htm" id="tohelpcenterform" method="post">
    <input type="hidden" name="searchText" value="${selectBean.searchText }" id="searchTextstr" />
    <input type="hidden" name="condition" value="1" id="condition" />
</form>
<input id="CSRFToken" type="hidden" name="CSRFToken" value="${token}" />
<input id="basePath" type="hidden"  value="${basePath}" />
<input id="baseUrl" type="hidden"  value="${baseUrl}" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/information/bootstrap.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath%>js/bootstrap-select.min.js"></script>
<script src="<%=basePath%>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath%>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath%>js/system/helplist.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath %>js/common/preview_image.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
</body>
</html>
