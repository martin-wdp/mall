<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/25
  Time: 17:08
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
<div class="page_body container-fluid">
    <div class="row">
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>
                <h2 class="main_title">${pageNameChild}</h2>

                <div class="common_data p20">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>注意!</strong> ERP接口，若修改不当，会影响ERP接口订单同步，在不了解的情况下请联系我们的工作人员进行修改。
                    </div>
                    <div class="common_form">
                        <form class="form-horizontal" id="editEstoreForm">
                            <input type="hidden" id="CSRFToken" value="${token}">
                            <input type="hidden" name="estoreid" id="estoreid" value="${estore.estoreid }">
                            <div class="form-group">
                                <label class="control-label col-sm-6">是否开启：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="isopen" attr_type="radio">
                                        <c:if test="${estore.isopen==0}"><span class="label label-default">否</span></c:if>
                                        <c:if test="${estore.isopen==1}"><span class="label label-success">是</span></c:if>
                                    </span>
                                    <div class="form_fill">
                                        <label class="radio-inline">
                                            <input type="radio" name="isopen" id="open1" value="1" <c:if test="${estore.isopen==1}">checked</c:if>> 是
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="isopen" id="open2" value="0" <c:if test="${estore.isopen==0}">checked</c:if>> 否
                                        </label>
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">提供商：</label>

                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="provider">${estore.provider }&nbsp;</span>

                                    <div class="form_fill">
                                        <input type="text" class="form-control w300 required"
                                               value="${estore.provider }" name="provider" id="provider">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">地址：</label>

                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="address">${estore.address }&nbsp;</span>

                                    <div class="form_fill">
                                        <input type="text" class="form-control w300" value="${estore.address }"
                                               name="address" id="address">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">图片地址：</label>

                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 ">
                                    <div class="col-sm-35">
                                        <img alt="" src="${estore.image}" id="img"
                                             style="max-height: 200px;max-width: 250px"><input style="margin-left:5px;"
                                                                                               type="button" id="choose"
                                                                                               value="选择"/>
                                        <input type="hidden" name="image" id="image"/>
                                    </div>
                                </div>

                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">服务器：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="server">${estore.server }&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w300 required" value="${estore.server }" name="server" id="server">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">主账号：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="dbhost">${estore.dbhost }&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w300 required specstr" value="${estore.dbhost }" name="dbhost" id="dbhost">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">AppKey：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="appkey">${estore.appkey }&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w200 required specstr" value="${estore.appkey }" name="appkey" id="appkey">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">AppSecret：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="appscret">${estore.appscret }&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w300 required specstr" value="${estore.appscret }" name="appscret" id="appscret">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">Token：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="token">${estore.token }&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w300 required specstr" value="${estore.token }" name="token" id="token">
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
<script src="<%=basePath%>js/system/estore.js"></script>
</body>
</html>

