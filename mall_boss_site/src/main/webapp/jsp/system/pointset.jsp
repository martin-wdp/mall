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
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>注意!</strong> 积分设置，若修改不当，会造成用户对积分的获得和使用的混乱，在不了解的情况下请联系我们的工作人员进行修改。
                    </div>
                    <div class="common_form">
                        <form class="form-horizontal" id="editPointForm">
                            <input type="hidden" id="psetId" value="${pointset.psetId}"/>
                            <input type="hidden" id="CSRFToken" value="${token}"/>
                            <div class="form-group">
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
                            </div>
                            <%--<div class="form-group">
                                <label class="control-label col-sm-6">注册积分：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="psetRegister">${pointset.psetRegister}&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w100" value="${pointset.psetRegister}" id="psetRegister" clazz="required number">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>--%>
                            <div class="form-group">
                                <label class="control-label col-sm-6">每日登录积分：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="psetLogin">${pointset.psetLogin}&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w100" value="${pointset.psetLogin}" id="psetLogin" clazz="required number">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">邮箱验证积分：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="psetEmail">${pointset.psetEmail}&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w100" value="${pointset.psetEmail}" id="psetEmail" clazz="required number">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">手机验证积分：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="psetPhone" >${pointset.psetPhone}&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w100" value="${pointset.psetPhone}" id="psetPhone" clazz="required number">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">发表评论积分：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="psetComment">${pointset.psetComment}&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w100" value="${pointset.psetComment}" id="psetComment" clazz="required number">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">推荐用户积分：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="psetUser">${pointset.psetUser}&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w100" value="${pointset.psetUser}" id="psetUser" clazz="required number">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">晒单积分：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_id="psetOnline">${pointset.psetOnline}&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w100" value="${pointset.psetOnline}" id="psetOnline" clazz="required number">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                           
                            <div class="form-group">
                                <label class="control-label col-sm-6">积分兑换规则：</label>
                                <label class="control-label col-sm-9" style="text-align: left;margin-left:24px;">每消费100元，可获得积分</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-7 form_item">
                                    <span class="form_value" attr_id="exchange">${pointset.exchange}&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w100" value="${pointset.exchange}" id="exchange" clazz="required number">
                                    </div>
                                </div>
                                <div class="col-sm-1"></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-6">积分消费规则：</label>
                                <label class="control-label col-sm-9" style="text-align: left;margin-left:24px;">每消费10积分，可抵消金额(元)</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-7 form_item">
                                    <span class="form_value" attr_id="consumption">${pointset.consumption}&nbsp;</span>
                                    <div class="form_fill">
                                        <input type="text" class="form-control w100" value="${pointset.consumption}" id="consumption">
                                    </div>
                                </div>
                                <div class="col-sm-1"></div>
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

