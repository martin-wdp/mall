<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/12
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

                <h2 class="main_title">${pageNameChild}</h2>
                <div class="common_data p20">
                    <div class="common_form">
                        <form class="form-horizontal" id="editPointForm">
                            <input type="hidden" id="psetId" value="${pointset.psetId}"/>
                            <input type="hidden" id="CSRFToken" value="${token}"/>
                           <%--  <div class="form-group">
                                <label class="control-label col-sm-6">是否开启：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value " attr_id="isOpen" attr_type="radio">
                                        <c:if test="${pointset.isOpen=='0'}"><span class="label label-default">否</span></c:if>
                                        <c:if test="${pointset.isOpen=='1'}"><span class="label label-success">是</span></c:if>
                                    </span>
                                    <div class="form_fill">
                                        <label class="radio-inline">
                                            <input type="radio" name="isOpen" id="open1" value="1" <c:if test="${pointset.isOpen=='1'}">checked</c:if>> 是
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="isOpen" id="open2" value="0" <c:if test="${pointset.isOpen=='0'}">checked</c:if>> 否
                                        </label>
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div> --%>
                            
                            <div class="form-group">
                                <label class="control-label col-sm-6">发表话题积分：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="psetPubtopic">${pointset.psetPubtopic}&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w100" value="${pointset.psetPubtopic}" id="psetPubtopic" clazz="required number">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">热门话题积分：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="psetHottopic">${pointset.psetHottopic}&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w100" value="${pointset.psetHottopic}" id="psetHottopic" clazz="required number">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">精选话题积分：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="psetEssencetopic">${pointset.psetEssencetopic}&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w100" value="${pointset.psetEssencetopic}" id="psetEssencetopic" clazz="required number">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">首页推荐话题：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="psetIndextopic">${pointset.psetIndextopic}&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w100" value="${pointset.psetIndextopic}" id="psetIndextopic" clazz="required number">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form_btns popover right">
                                <div class="arrow" style="top:50%"></div>
                                <h3 class="popover-title">确定修改？</h3>
                                <div class="popover-content">
                                    <div class="text-center">
                                        <button type="button" class="btn btn-primary form_sure">确定</button>
                                        &nbsp;&nbsp;
                                        <button type="button" class="btn btn-default form_cancel">取消</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/system/pointset.js"></script>
</body>
</html>

