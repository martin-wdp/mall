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
<input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">
<!-- 引用头 -->
<jsp:include page="../page/header.jsp"></jsp:include>

<div class="page_body container-fluid">
    <div class="row">
        <jsp:include page="../page/left.jsp"></jsp:include>



        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">收款单列表 <small>(共${pageBean.rows}条)</small></h2>

                <div class="common_data p20">

                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-left">
                            <input type="hidden" value="receivableForm" id="formId"/>
                            <input type="hidden" value="<%=basePath %>receivablesList.htm" id="formName"/>
                            <form action="receivablesList.htm" method="post" id="receivableForm">
                                <input type="hidden" name="CSRFToken" value="${token}">
                                <input type="hidden" name="status" id="status" value="${status}">
                            <div class="form-inline">
                                <div class="form-group">
                                    <select class="form-control" name="condition">
                                        <option value="1" <c:if test="${condition == '1'}">selected="selected" </c:if>>订单号</option>
                                        <option value="2" <c:if test="${condition == '2'}">selected="selected" </c:if>>收款单号</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" name="searchText" value="${searchText}">
                                </div>
                                <div class="form-group">
                                    <button class="btn btn-info" onclick="bindStatus();"><i class="glyphicon glyphicon-search"></i></button>
                                </div>
                            </div>
                            </form>
                        </div>
                        <div class="clr"></div>
                    </div>

                    <div class="table_tabs" id="cashRegister">
                        <ul>
                            <li class="<c:if test="${status == ''}">active</c:if>">
                                <a href="javascript:;" data-type="">全部</a>
                            </li>
                            <li class="<c:if test="${status == '0'}">active</c:if>">
                                <a href="javascript:;" data-type="0">成功</a>
                            </li>
                            <li class="<c:if test="${status == '1'}">active</c:if>">
                                <a href="javascript:;" data-type="1">失败</a>
                            </li>
                        </ul>
                    </div>
                    <form action="" id="queryReceivables" method="post">
                    <table class="table cash_register_table">
                        <thead>
                        <tr>
                            <%--<th>支付IP</th>--%>
                            <th>收款单号</th>
                            <th>支付完成时间</th>
                            <th>支付方式</th>
                            <th>支付金额</th>
                            <th>支付类型</th>
                            <th>支付状态</th>
                            <th>相关订单号</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>



                        <c:forEach items="${pageBean.list}" var="receivables">
                            <tr>
                                <%--<td>${receivables.payIp}</td>--%>
                                <td>${receivables.cashRegisterCode}</td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${receivables.payTime}" type="both"/></td>
                                <td>${receivables.payMode}</td>
                                <td>￥${receivables.payMoney}</td>
                                <td>${receivables.payType}</td>
                                <td>
                                    <c:if test="${receivables.payStatus eq 0}"><span class="label label-success">成功</span></c:if>
                                    <c:if test="${receivables.payStatus eq 1}"><span class="label label-default">失败</span></c:if>
                                </td>
                                <td>${receivables.orderCode}</td>
                                <td><button type="button" class="btn btn-default scan_cash_register" onclick="queryDetail(${receivables.cashRegisterId});">查看详情</button></td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                    </form>

                    <div class="table_foot">
                        <c:import url="../page/searchPag.jsp">
                            <c:param name="pageBean" value="${pb }"/>
                            <c:param name="path" value="../"></c:param>
                        </c:import>
                    </div>
                    </div>

                </div>

            </div>
    </div>
    </div>





<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=basePath %>vjs/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>js/common.js"></script>
<script src="<%=basePath %>js/common/common_date.js"></script>
<script>
    $(function(){

        /*收款单筛选交互演示用，无实际意义*/
        $('#cashRegister a').click(function(){
            $that = $(this);
            $that.parent().addClass('active');
            $("#status").val($that.attr("data-type"))
            $that.parent().siblings().removeClass('active');
            $("#receivableForm").submit();

        });

    });

    /**
     * 查看收款单详情
     *
     * */
    function queryDetail(obj){
        $.ajax({
            url:"receivablesDetail.htm?cashRegisterId="+obj,
            type:"post",
            success:function(data){
                var time = new Date(data.payTime);
                var receTime = new Date(data.receivablesTime)
                var now = time;
                var nowRece = receTime;
                var nowStr = now.format("yyyy-MM-dd hh:mm:ss");
                var receNowStr = nowRece.format("yyyy-MM-dd hh:mm:ss");
                var myDialog = art.dialog({width: '900px',lock:'true',opacity:'.3'});
                myDialog.content('<table cellpadding="0" cellspacing="0" border="0" width="100%" class="data_table"><tbody><tr><th><label>收款单号：</label></th><td>'+data.cashRegisterCode+'</td><th><label>订单号：</label></th><td>'+data.orderCode+'</td><th><label>支付方式：</label></th><td>'+data.payMode+'</td><th><label>生成时间：</label></th><td>'+nowStr+'</td></tr><tr><th><label>收款方式：</label></th><td>'+data.payMode+'</td><th><label>收款帐号：</label></th><td>'+data.payAccount+'</td><th><label>付款金额：</label></th><td>'+data.payMoney+'</td><th><label>付款时间：</label></th><td>'+receNowStr+'</td></tr></tbody></table>');
            }
        });
    }

    function bindStatus(){
        $("#status").val($("#cashRegister").find(".active").find("a").attr("data-type"));
        $("#receivableForm").submit();
    }
</script>
</body>
</html>

