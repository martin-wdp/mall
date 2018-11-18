<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/4/17
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
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
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>


        .cur img {
            border-color: #df1738!important;}
        .od_list p{line-height:220%;}
        .od_list p:after{content:'';display:block;clear:both }
        .od_list a {display:inline-block; margin:5px 10px 0 0;vertical-align:top;}
        .od_list img { border:1px solid #dedede;}
        .od_list {margin-bottom:20px;}
        .od_list a {cursor:url(../images/zoom_in.png),url(../images/zoom_in.cur),auto;}
        .od_list a img {display:block;}
        .od_list .cur {position:relative;}
        .od_list .cur  {cursor:url(../images/zoom_out.png),url(../images/zoom_out.cur),auto;}
        .od_list .cur  img {padding:2px; border-width:2px; border-style:solid;}
        /*.od_list .cur+b {background:url(../images/photo_arrow.png) no-repeat; width:15px; height:6px; position:absolute; bottom:-6px; left:26px;}*/
        .photo_viewer {display:none;}
        .photo_viewer img {display:block; padding:3px; border:1px solid #ddd; cursor:url(../images/zoom_out.png),url(../images/zoom_out.cur),auto;}


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
                <h2 class="main_title">${pageNameChild} <small>(共${pageBean.rows }条)</small></h2>

                <div class="common_data p20">
                    <div class="filter_area">
                        <input type="hidden" id="formId" value="searchForm"/>
                        <input type="hidden" id="formName" value="initsharelist.htm"/>
                        <form role="form" class="form-inline" id="searchForm" action="initsharelist.htm" method="post">
                            <input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">发表人</span>
                                    <input type="text" class="form-control" name="customerName" value="${share.customerName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">商品名称</span>
                                    <input type="text" class="form-control" name="goodsName" value="${share.goodsName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">搜索</button>
                            </div>
                        </form>
                    </div>
                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="batchDeleteShare()">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                            <button type="button" class="btn btn-info" onclick="batchDisplayToIndex()">
                               <i class="glyphicon glyphicon-list-alt"></i> 显示到首页
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form id="shareListForm">
                        <input type="hidden" name="CSRFToken" value="${token}"/>
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th width="10"><input type="checkbox" onclick="allunchecked(this,'parameterIds')"></th>
                            <th class="w100">商品名称</th>
                            <th class="w100">发表人</th>
                            <th class="w100">标题</th>
                            <th class="w200">内容</th>
                            <th class="w100">前台显示</th>
                            <th class="w100">发表时间</th>
                            <th width="130">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageBean.list}" var="share" varStatus="i">
                        <fmt:formatDate value="${share.createTime }" pattern="yyyy-MM-dd HH:mm:ss" var="publishTime" />
                        <tr>
                            <td><input type="checkbox" name="parameterIds"  value="${share.shareId }"></td>
                            <td title="${share.goodsName }">
                            
                            <div class="data_item">
			             			 <img  height="60" src='${share.goodsImg }'/>
				                      <p title="${share.goodsName}"><c:if test="${fn:length(share.goodsName)>25}">${fn:substring(share.goodsName , 0, 25)}</c:if>
				                      <c:if test="${fn:length(share.goodsName)<=25}">${share.goodsName}</c:if>
				                      </p>
				                      <p></p>
				                    </div>
				                    
                            </td>
                            <td title="${share.customerName }">
                                ${share.customerName}
                            </td>
                            <td title="${share.shareTitle }">
                                ${share.shareTitle}
                            </td>
                            <td title="${share.shareContent }">${share.shareContent}</td>
                            <td><a href="javascript:;">
                                <c:if test="${share.isDisplay ==0}">
                                    <span class="label label-default" id="share_display${share.shareId}">否</span></a>
                                </c:if>
                                <c:if test="${share.isDisplay ==1 || share.isDisplay ==2}">
                                    <span class="label label-success" id="share_display${share.shareId}">是</span></a>
                                </c:if>
                            </td>
                            <td>${publishTime }</td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" onclick="showShareDetail(${share.shareId})">查看</button>
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="javascript:;" onclick="showDeleteOneConfirmAlert('deleteshare.htm?parameterIds=${share.shareId}&CSRFToken=${token}')">删除</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    </form>
                    <c:import url="../page/searchPag.jsp">
                        <c:param name="pageBean" value="${pageBean }"/>
                        <c:param name="path" value="../"></c:param>
                    </c:import>

                </div>

            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="scanAdvisory"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">晒单详情</h4>
            </div>
            <div class="modal-body">
                <div class="rec_example">
                    <div class="recommend_edit" id="share_detail">
                        <img class="good_img" alt="" src="images/good_1.jpg">
                        <div class="recommend_cont">
                            <h4>苹果（APPLE）iPhone 6 A1589 16G版 4G手机（银色）TD-LTE/TD-SCDMA/GSM</h4>
                            <p>
                                <i class="glyphicon glyphicon-star orange"></i>
                                <i class="glyphicon glyphicon-star orange"></i>
                                <i class="glyphicon glyphicon-star orange"></i>
                                <i class="glyphicon glyphicon-star orange"></i>
                                <i class="glyphicon glyphicon-star-empty orange"></i>
                            </p>
                            <p>发表人：<em class="text-info">时过境迁x：</em></p>
                            <p>
                                <span>时间：2014-11-04 14:30:32</span>
                                <span>IP：220.95.224.121</span>
                            </p>
                        </div>
                        <div class="recommend_cont">
                            <p>内容：
                                <a role="button" class="btn btn-sm btn-default">取消显示</a>
                                <a role="button" class="btn btn-sm btn-default">显示到首页</a>
                            </p>
                            <p>另外一家店铺和这个是同款，人家5099包邮，我们同事就是在那家买的，和我在这家买的质量一模一样，我买贵了！</p>
                        </div>
                        <div class="mt20">
                            <p>晒单：
                                <img class="img-thumbnail" alt="" src="images/good_1.jpg" width="100">
                                <img class="img-thumbnail" alt="" src="images/good_1.jpg" width="100">
                                <img class="img-thumbnail" alt="" src="images/good_1.jpg" width="100">
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="importComments"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">导入评论</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-6">下载分类模板：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="control-label"><a href="#">评论模板文件.xls</a></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">下载商品编号文件：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="control-label"><a href="#">商品编号文件.xls</a></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">选择分类数据文件：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="file">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>js/common.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>js/customer/share.js"></script>
</body>
</html>
