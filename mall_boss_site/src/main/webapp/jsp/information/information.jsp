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
    <title>文章列表</title>

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
<body id="bodymodal">
<!-- 引用头 -->
<jsp:include page="../page/header.jsp"></jsp:include>
<div class="container-fluid page_body">
    <div class="row">
        <!-- 引用左侧导航 -->
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">

            <div class="main_cont">
                 <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild}<small>(共${pb.rows}条)</small></h2>

                <div class="common_data p20">

                    <div class="filter_area">
                        <form role="form" class="form-inline">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">文章名称</span>
                                    <input type="text" class="form-control" value="${selectBean.searchText }" id="searchText">
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-primary" onclick="informationSearch()">搜索</button>
                            </div>
                        </form>
                    </div>
                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="addmodal();">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="delsmodal()">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>

                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th><input type="checkbox" name="infoId" onclick="allunchecked(this,'infoId');"></th>
                            <th>图片</th>
                            <th>文章标题</th>
                            <th>文章栏目</th>
                            <th class="w100">更新时间</th>
                            <th>作者</th>
                            <th>点击数</th>
                            <th>排序</th>
                            <th>第三方公告</th>
                            <th>是否发布</th>
                            <th width="150">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="infoVo" items="${pb.list }" varStatus="status">
                        <tr>
                            <td><input type="checkbox" name="infoId"  value="${infoVo.infoId }"></td>
                            <td>
                            <c:if test="${infoVo.imgSrc!=null}">
                                <img alt="" src="${infoVo.imgSrc }" height="30">
                            </c:if>
                            </td>
                            <td style="text-align:left;">${infoVo.title }</td> 
                            <td>${infoVo.infoType.name }</td>
                            <td>
                                <fmt:formatDate value="${infoVo.updateDate }" var="cdate" type="both"/>
                                    ${cdate }
                            </td>
                            <td>${infoVo.author }</td>
                            <td>${infoVo.hits }</td>
                            <td>${infoVo.sort }</td>
                            <td>
                                <c:if test="${infoVo.isThirdNews==0}"><a href="javascript:;"><span class="label label-default">否</span></a></c:if>
                                <c:if test="${infoVo.isThirdNews==1}"><a href="javascript:;"><span class="label label-success">是</span></a></c:if>
                            </td>
                            <td>
                                <c:if test="${infoVo.isShow==0}"><a href="javascript:;"><span class="label label-default">否</span></a></c:if>
                                <c:if test="${infoVo.isShow==1}"><a href="javascript:;"><span class="label label-success">是</span></a></c:if>

                            </td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" onclick="updatemodal(${infoVo.infoId })">编辑</button>
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="#" onclick="delmodal(${infoVo.infoId })">删除</a></li>
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
<div class="modal fade" id="addArticle"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加文章</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" enctype="multipart/form-data" id="addinformation" action="addInformation.htm?CSRFToken=${token}">
                    <div class="form-group">
                        <label class="control-label col-sm-4"><span class="text-danger">*</span>文章标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control required" name="title" id="title">
                            <label id="title_tips"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">链接地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control url"  name="url" id="url">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">简略标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="shortTitle" id="shortTitle">
                            <label id="shortTitle_tips"></label>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <c:if test="${!empty infoUDs }">
                    <div class="form-group">
                        <label class="control-label col-sm-4">自定义属性：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <c:forEach var="infoUD" items="${infoUDs }">
                            <label class="checkbox-inline">
                                <input type="checkbox" name="userDefined" value="${infoUD.name }"
                                <c:forEach var="userDefined" items="${userDefineds }">
                                       <c:if test="${userDefined==infoUD.name }">checked="checked"</c:if>
                                </c:forEach>
                                        > ${infoUD.name }
                            </label>
                            </c:forEach>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    </c:if>
                    <div class="form-group">
                        <label class="control-label col-sm-4">TAG标签：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="tag" id="tag">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">文章配图：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file" name="picFile" id="picFile"></p>
                            <input type="hidden" name="imgSrc" id="imgSrc">
                            <iframe id="uploadFrame" name="uploadFrame" style="display:none;"></iframe>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">预览图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img alt="" src=""  width="80" id="picFileView">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">文章来源：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="source" id="source">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">作者：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="author" id="author">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">所属栏目：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <select  name="infoTypeId" id="infoTypeId" class="form-control">
                                <c:forEach items="${infoTypes }" var="infoType">
                                <option value="${infoType.infoTypeId}">${infoType.name }</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">浏览权限：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <select name="browseable" id="browseable" class="form-control">
                            </select>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">是否第三方公告：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="isThirdNews" value="1" checked="checked"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isThirdNews" value="0"> 否
                            </label>
                        </div>
                        <div class="col-sm-3">
		                    <a href="javascript:;" class="helpisThirdNews help_tips" data-original-title="" title="">
		                        <i class="icon iconfont"></i>
		                    </a>
		           		</div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">是否发布：</label>
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
                        <label class="control-label col-sm-4"><span class="text-danger">*</span>排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-2">
                            <input type="text" class="form-control required digits" id="sort" name="sort" maxlength="3" >
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">SEO关键字：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <textarea rows="5" class="form-control" name="keyword" id="keyword"></textarea>
                            <label id="keyword_tips"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">SEO内容：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <textarea rows="5" class="form-control" id="description"  name="description"></textarea>
                            <label id="description_tips"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">文章内容：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-17">
                            <div class="summernote" id="contentstr"></div>
                            <input type="hidden" id="content" class="required" name="content">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="saveinformation();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="updateArticle"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">修改文章</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" enctype="multipart/form-data" id="updateInformation" action="updateInformation.htm?CSRFToken=${token}">
                    <div class="form-group">
                        <label class="control-label col-sm-4"><span class="text-danger">*</span>文章标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="hidden" name="infoId" id="up_infoId">
                            <input type="text" class="form-control required" name="title" id="up_title">
                            <label id="up_title_tips"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">链接地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control url"  name="url" id="up_url">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">简略标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="shortTitle" id="up_shortTitle">
                            <label id="up_shortTitle_tips"></label>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <c:if test="${!empty infoUDs }">
                    <div class="form-group">
                        <label class="control-label col-sm-4">自定义属性：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10" id="up_infoUD">
                            <c:forEach var="infoUD" items="${infoUDs }">
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="userDefined" value="${infoUD.name }"
                                    <c:forEach var="userDefined" items="${userDefineds }">
                                           <c:if test="${userDefined==infoUD.name }">checked="checked"</c:if>
                                    </c:forEach>
                                            > ${infoUD.name }
                                </label>
                            </c:forEach>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    </c:if>
                    <div class="form-group">
                        <label class="control-label col-sm-4">TAG标签：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="tag" id="up_tag">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">文章配图：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file" name="picFile" id="uppicFile"></p>
                            <input type="hidden" name="imgSrc" id="up_imgSrc">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">预览图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img alt="" src="" id="up_img" width="80">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">文章来源：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="source" id="up_source">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">作者：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="author" id="up_author">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">所属栏目：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <select  name="infoTypeId" id="up_infoTypeId" class="form-control">
                                <c:forEach items="${infoTypes }" var="infoType">
                                    <option value="${infoType.infoTypeId}">${infoType.name }</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">浏览权限：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <select name="browseable" id="up_browseable" class="form-control">
                            </select>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">是否第三方公告：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" id="isThirdNews1" name="isThirdNews" value="1" checked="checked"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" id="isThirdNews0" name="isThirdNews" value="0"> 否
                            </label>
                        </div>
                        <div class="col-sm-3">
		                    <a href="javascript:;" class="helpisThirdNews help_tips" data-original-title="" title="">
		                        <i class="icon iconfont"></i>
		                    </a>
		           		</div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">是否发布：</label>
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
                        <label class="control-label col-sm-4"><span class="text-danger">*</span>排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-2">
                            <input type="text" class="form-control required digits" id="up_sort" name="sort" maxlength="3" >
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">SEO关键字：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <textarea rows="5" class="form-control" name="keyword" id="up_keyword"></textarea>
                            <label id="up_keyword_tips"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">SEO内容：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-14">
                            <textarea rows="5" class="form-control" id="up_description"  name="description"></textarea>
                            <label id="up_description_tips"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">文章内容：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-17">
                            <div class="summernote" id="up_contentstr"></div>
                            <input type="hidden" id="up_content" class="required" name="content">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="updateinformation();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="delinformation"  role="dialog">
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
                <button type="button" class="btn btn-primary" onclick="delinformation();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="delinformations"  role="dialog">
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
                <button type="button" class="btn btn-primary" onclick="delinformations();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">
<input type="hidden" value="informationform" id="formId"/>
<input type="hidden" value="queryInfoVoList.htm" id="formName"/>
<form id="informationform" action="queryInfoVoList.htm" method="post">
    <input type="hidden" name="condition" value="1" />
    <input type="hidden" name="searchText" id="searchTextstr" value="${selectBean.searchText}" />
</form>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/information/bootstrap.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>js/common.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
<script src="<%=basePath %>js/information/information.js"></script>
<script src="<%=basePath %>/js/common/common_alert.js"></script>
</body>
</html>
