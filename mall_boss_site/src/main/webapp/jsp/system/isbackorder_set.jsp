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
                <input type="hidden" id="CSRFToken" value="${token}"/>
                <input type="hidden" id="setId" value="${systemsSet.setId}"/>
                <div class="common_data p20">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>注意!</strong> 订单设置，请根据实际情况选择是否允许退单，否则会给买家造成不必要的麻烦，在不了解的情况下请联系我们的工作人员进行修改。
                    </div>
                    <div class="common_form p20">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-sm-7">是否允许退单：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value" attr_flag="1">
                                        <c:if test="${systemsSet.isBackOrder==1 }"><span class="label label-success">是</span></c:if>
                                        <c:if test="${systemsSet.isBackOrder==2 }"><span class="label label-default">否</span></c:if>
                                    </span>
                                    <div class="form_fill">
                                        <label class="radio-inline">
                                            <input type="radio" name="isBackOrder" id="open1" value="1" <c:if test="${systemsSet.isBackOrder==1 }">checked</c:if>> 是
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="isBackOrder" id="open2" value="2" <c:if test="${systemsSet.isBackOrder==2 }">checked</c:if>> 否
                                        </label>
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>

                            <div class="form_btns2 popover right">
                                <div class="arrow" style="top:50%"></div>
                                <h3 class="popover-title">确定修改？</h3>
                                <div class="popover-content">
                                    <div class="text-center">
                                        <button type="button" class="btn btn-primary btn-save">确定</button>
                                        &nbsp;&nbsp;
                                        <button type="button" class="btn btn-default form_cancel">取消</button>
                                    </div>
                                </div>
                            </div>

                        </form>

                        <form class="form-horizontal" id="editParameterForm">
                            <div class="form-group">
                                <label class="control-label col-sm-7">订单自动取消时间：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value form_value_parameter" attr_id="bsetOrderTime" attr_flag="3">${systemsSet.bsetOrderTime }&nbsp;小时</span>
                                    <div class="form_fill">
                                        <input type="text" id="bsetOrderTime"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" name="bsetOrderTime" class="form-control w300" value="${systemsSet.bsetOrderTime }" clazz="required specstr">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>

                            <div class="form_btns popover right">
                                <div class="arrow" style="top:50%"></div>
                                <h3 class="popover-title">确定修改？</h3>
                                <div class="popover-content">
                                    <div class="text-center">
                                        <button type="button" class="btn btn-primary btn-time-save">确定</button>
                                        &nbsp;&nbsp;
                                        <button type="button" class="btn btn-default form_cancel">取消</button>
                                    </div>
                                </div>
                            </div>
                        </form>


                        <form class="form-horizontal" >
                            <div class="form-group">
                                <label class="control-label col-sm-7">订单自动收货时间设置：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <span class="form_value form_value_parameter" attr_flag="2">${systemsSet.receiptTime }&nbsp;天</span>
                                    <div class="form_fill">
                                        <input type="text" id="receiptTime" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" name="receiptTime" class="form-control w300" value="${systemsSet.receiptTime }" clazz="required">
                                    </div>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>

                            <div class="receipt_btns popover right">
                                <div class="arrow" style="top:50%"></div>
                                <h3 class="popover-title">确定修改？</h3>
                                <div class="popover-content">
                                    <div class="text-center">
                                        <button type="button" class="btn btn-primary btn-receipt-save">确定</button>
                                        &nbsp;&nbsp;
                                        <button type="button" class="btn btn-default form_cancel">取消</button>
                                    </div>
                                </div>
                            </div>
                        </form>

                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-sm-7">退货说明：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <button type="button" class="btn btn-default" onclick="$('#backOrderRemark').modal('show')">查看并修改</button>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                        </form>
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-sm-7">退款说明：</label>
                                <div class="col-sm-1"></div>
                                <div class="col-sm-14 form_item">
                                    <button type="button" class="btn btn-default" onclick="$('#backOrderRemark2').modal('show')">查看并修改</button>
                                </div>
                                <div class="col-sm-3"></div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="operTipModal"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">订单设置</h4>
            </div>
            <div class="modal-body">
                <span id="oper_tip"></span>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary btn-sure">确定</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="backOrderRemark"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">退货说明</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="summernote" id="backInfoRemark">${systemsSet.backInfoRemark}</div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="saveOrderRemark('backInfoRemark',this)">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="backOrderRemark2"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">退款说明</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="summernote" id="backPriceRemark">${systemsSet.backPriceRemark}</div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="saveOrderRemark('backPriceRemark',this)">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
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
<script src="<%=basePath%>js/system/orderset.js"></script>

</body>
</html>

