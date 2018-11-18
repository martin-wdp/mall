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
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">

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
        <!-- 引用左侧导航 -->
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">

            <div class="main_cont">
                 <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild} <small>(共${pb.rows }条)</small></h2>

                <div class="common_data p20">

                    <div class="filter_area">
                        <form role="form" class="form-inline">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">单页名称</span>
                                    <input type="text" class="form-control" name="searchText" value="${selectBean.searchText }" id="searchText">
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-primary" onclick="infoOnePagess()">搜索</button>
                            </div>
                        </form>
                    </div>
                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="addmodal()">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="delinfosYN()">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>

                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th><input type="checkbox" name="infoOnePageId" onclick="allunchecked(this,'infoOnePageId');"></th>
                            <th>图片</th>
                            <th>单页标题</th>
                            <th>单页地址</th>
                            <th>标签</th>
                            <th>是否显示到首页</th>
                            <th class="w100">更新时间</th>
                            <th width="150">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="infoOnePageVo" items="${pb.list }" varStatus="status">
                            <tr>
                                <td><input type="checkbox" name="infoOnePageId"  value="${infoOnePageVo.infoOPId }"></td>
                                <td>
                                    <c:if test="${infoOnePageVo.imgSrc!=null}">
                                        <img alt="" src="${infoOnePageVo.imgSrc }" height="30">
                                    </c:if>
                                </td>
                                <td style="text-align:left;">${infoOnePageVo.title }</td>
                                <td><a href="${bsetaddress }/onepage/${infoOnePageVo.infoOPId }.html" target="_blank">${bsetaddress }/onepage/${infoOnePageVo.infoOPId }.html</a></td>
                                <td>${infoOnePageVo.infoOPTag.name }</td>
                                <td><a href="javascript:;">
                                    <c:if test="${infoOnePageVo.isShow==0}"><span class="label label-default">否</span></c:if>
                                    <c:if test="${infoOnePageVo.isShow==1}"><span class="label label-success">是</span></c:if>
                                    </a>
                                </td>
                                <td>
                                    <fmt:formatDate value="${infoOnePageVo.updateDate }" var="cdate" type="both"/>
                                        ${cdate }
                                </td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default" onclick="updateinfoModal(${infoOnePageVo.infoOPId })">编辑</button>
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                            <span class="caret"></span>
                                            <span class="sr-only">Toggle Dropdown</span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu" onclick="delinfoYN(${infoOnePageVo.infoOPId })">
                                            <li><a href="#">删除</a></li>
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
<div class="modal fade" id="updateSinglePage"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">修改单页</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="updateInfoOnePage" action="updateInfoOnePage.htm?CSRFToken=${token}" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <input type="hidden" name="infoOPId" id="up_infoOnePageid" />
                        <label class="control-label col-sm-4"><span class="text-danger">*</span>单页标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control required" name="title" id="up_title">
                            <label id="up_titletip"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">简略标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="shortTitle" id="up_shortTitle">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">模板：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                            <select class="form-control" id="up_infoTempId" name="infoTempId" onchange="changeSelectup(this)">
                                <c:forEach var="sysTemp" items="${sysTempList }">
                                    <option value="${sysTemp.tempId }">${sysTemp.tempName }</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">单页标签：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                            <select class="form-control" name="infoOPTagId" id="up_infoOPTagId">

                            </select>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">单页图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file" type="file" name="picFile" id="up_picFile"></p>
                            <input type="hidden" name="imgSrc" id="up_imgSrc">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">预览图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img alt="" id="up_img" src="" width="80">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">是否显示到首页：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" id="isShow1" name="isShow"  value="1" checked="checked"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" id="isShow0" name="isShow"  value="0"> 否
                            </label>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">SEO关键字：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <textarea rows="5" class="form-control" name="keyword" id="up_keyword"></textarea>
                        </div>
                        <!-- <div class="col-sm-2">
                            <a href="javascript:;" class="gjz help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div> -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">SEO描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <textarea rows="5" class="form-control" maxlength="255" id="up_description" name="description"></textarea>
                        </div>
                        <!-- <div class="col-sm-2">
                            <a href="javascript:;" class="nrzy help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div> -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">单页内容：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-17">
                            <div class="summernote" id="up_contentstr"></div>
                            <input type="hidden" id="up_content" class="required" name="content">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="update()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addSinglePage"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加单页</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="addInfoOnePage" action="addInfoOnePage.htm?CSRFToken=${token}" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="control-label col-sm-4"><span class="text-danger">*</span>标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control required" name="title" id="title">
                            <label id="titletip"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">简略标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="shortTitle" id="shortTitle">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">模板：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                            <select class="form-control" id="infoTempId" name="infoTempId" onchange="changeSelect(this)">
                                <c:forEach var="sysTemp" items="${sysTempList }">
                                    <option value="${sysTemp.tempId }">${sysTemp.tempName }</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">单页标签：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                            <select class="form-control" name="infoOPTagId" id="infoOPTagId">

                            </select>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">单页图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file" type="file" name="picFile" id="picFile"></p>
                            <input type="hidden" name="imgSrc" id="imgSrc">
                            <iframe id="uploadFrame" name="uploadFrame" style="display:none;"></iframe>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">预览图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img alt="" src="" id="imgView"  width="80">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">是否显示到首页：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="isShow"  value="1" checked="checked"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isShow"  value="0"> 否
                            </label>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">	SEO关键字：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <textarea rows="5" class="form-control" name="keyword" id="keyword"></textarea>
                        </div>
                        <!-- <div class="col-sm-2">
                            <a href="javascript:;" class="gjz help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div> -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">SEO描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <textarea rows="5" class="form-control" maxlength="255" id="description" name="description"></textarea>
                        </div>
                        <!--<div class="col-sm-2">
                            <a href="javascript:;" class="nrzy help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div> -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">单页内容：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-17">
                            <div class="summernote" id="contentstr"></div>
                            <input type="hidden" id="content" class="required" name="content">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="addSinglePage()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="delinfo"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" id="delinfoId" />
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
                <button type="button" class="btn btn-primary" onclick="delinfo();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="delinfos"  role="dialog">
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
                <button type="button" class="btn btn-primary" onclick="delinfos();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">
<input type="hidden" value="infoOnePageform" id="formId"/>
<input type="hidden" value="queryInfoOnePagesByPageBean.htm" id="formName"/>
<form id="infoOnePageform" action="queryInfoOnePagesByPageBean.htm" method="post">
    <input type="hidden" name="condition" value="1" />
    <input type="hidden" name="searchText" id="searchTextstr" value="${selectBean.searchText}" />
</form>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>js/common.js"></script>
<script src="<%=basePath %>js/information/infoOnePageList.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
</body>
</html>
